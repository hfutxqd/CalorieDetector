/*
 * Copyright 2016 The TensorFlow Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hack.define.caloriedetector.ui.activity;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.Image;
import android.media.Image.Plane;
import android.media.ImageReader;
import android.media.ImageReader.OnImageAvailableListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.util.Size;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.hack.define.caloriedetector.MyApplication;
import com.hack.define.caloriedetector.R;
import com.hack.define.caloriedetector.data.DetectResult;
import com.hack.define.caloriedetector.env.BorderedText;
import com.hack.define.caloriedetector.env.ImageUtils;
import com.hack.define.caloriedetector.env.Logger;
import com.hack.define.caloriedetector.model.Classifier;
import com.hack.define.caloriedetector.model.TensorFlowImageClassifier;
import com.hack.define.caloriedetector.widget.OverlayView;

import java.util.List;
import java.util.Vector;

public class ClassifierActivity extends CameraActivity implements OnImageAvailableListener {
    private static final Logger LOGGER = new Logger();

    // These are the settings for the original v1 Inception model. If you want to
    // use a model that's been produced from the TensorFlow for Poets codelab,
    // you'll need to set IMAGE_SIZE = 299, IMAGE_MEAN = 128, IMAGE_STD = 128,
    // INPUT_NAME = "Mul", and OUTPUT_NAME = "final_result".
    // You'll also need to update the MODEL_FILE and LABEL_FILE paths to point to
    // the ones you produced.
    //
    // To use v3 Inception model, strip the DecodeJpeg Op from your retrained
    // model first:
    //
    // python strip_unused.py \
    // --input_graph=<retrained-pb-file> \
    // --output_graph=<your-stripped-pb-file> \
    // --input_node_names="Mul" \
    // --output_node_names="final_result" \
    // --input_binary=true
    private static final int INPUT_SIZE = 224;
    private static final int IMAGE_MEAN = 117;
    private static final float IMAGE_STD = 1;
    private static final String INPUT_NAME = "input";
    private static final String OUTPUT_NAME = "output";

    private static final String MODEL_FILE = "file:///android_asset/tensorflow_inception_graph.pb";
    private static final String LABEL_FILE =
            "file:///android_asset/imagenet_comp_graph_label_strings.txt";

    private static final boolean SAVE_PREVIEW_BITMAP = false;

    private static final boolean MAINTAIN_ASPECT = true;

    private static final Size DESIRED_PREVIEW_SIZE = new Size(640, 480);

    private Classifier classifier;

    private Integer sensorOrientation;

    private int previewWidth = 0;
    private int previewHeight = 0;
    private byte[][] yuvBytes;
    private int[] rgbBytes = null;
    private Bitmap rgbFrameBitmap = null;
    private Bitmap croppedBitmap = null;

    private Bitmap cropCopyBitmap;

    private boolean computing = false;

    private Matrix frameToCropTransform;
    private Matrix cropToFrameTransform;

//  private ResultsView resultsView;


    private BorderedText borderedText;

    private long lastProcessingTimeMs;
    private View mTipView;
    private TextView mTipTitle;
    private TextView mTipHint;
    private ImageButton mBtnNxt;
    private DetectResult mDetectResult;
    private float mBestMatchRate = -1;

    private HandlerThread mThread = new HandlerThread("delay");
    private Handler mHander;

    @Override
    protected int getLayoutId() {
        mThread.start();
        mHander = new Handler(mThread.getLooper());
        return R.layout.camera_connection_fragment;
    }

    @Override
    protected Size getDesiredPreviewFrameSize() {
        return DESIRED_PREVIEW_SIZE;
    }

    private static final float TEXT_SIZE_DIP = 10;

    @Override
    public void onPreviewSizeChosen(final Size size, final int rotation) {
        final float textSizePx =
                TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, TEXT_SIZE_DIP, getResources().getDisplayMetrics());
        borderedText = new BorderedText(textSizePx);
        borderedText.setTypeface(Typeface.MONOSPACE);

        classifier =
                TensorFlowImageClassifier.create(
                        getAssets(),
                        MODEL_FILE,
                        LABEL_FILE,
                        INPUT_SIZE,
                        IMAGE_MEAN,
                        IMAGE_STD,
                        INPUT_NAME,
                        OUTPUT_NAME);

//    resultsView = (ResultsView) findViewById(R.id.results);
        previewWidth = size.getWidth();
        previewHeight = size.getHeight();
        mTipView = findViewById(R.id.tip_view);
        mTipTitle = (TextView) findViewById(R.id.tip_title);
        mTipHint = (TextView) findViewById(R.id.tip_hint);

        mBtnNxt = (ImageButton) findViewById(R.id.btn_bottom_nxt);
        mBtnNxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDetectResult != null) {
                    if (mDetectResult.detailUrl != null) {
                        String subTitle = mDetectResult.calorie + " cal / 100g";
                        mDetectResult.save();
                        FoodDetailActivity.startActivity(ClassifierActivity.this,mDetectResult.detailUrl,mDetectResult.name,subTitle,mDetectResult);
                    } else {
                        Toast.makeText(ClassifierActivity.this, "暂无详情", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        final Display display = getWindowManager().getDefaultDisplay();
        final int screenOrientation = display.getRotation();

        LOGGER.i("Sensor orientation: %d, Screen orientation: %d", rotation, screenOrientation);

        sensorOrientation = rotation + screenOrientation;

        LOGGER.i("Initializing at size %dx%d", previewWidth, previewHeight);
        rgbBytes = new int[previewWidth * previewHeight];
        rgbFrameBitmap = Bitmap.createBitmap(previewWidth, previewHeight, Config.ARGB_8888);
        croppedBitmap = Bitmap.createBitmap(INPUT_SIZE, INPUT_SIZE, Config.ARGB_8888);

        frameToCropTransform =
                ImageUtils.getTransformationMatrix(
                        previewWidth, previewHeight,
                        INPUT_SIZE, INPUT_SIZE,
                        sensorOrientation, MAINTAIN_ASPECT);

        cropToFrameTransform = new Matrix();
        frameToCropTransform.invert(cropToFrameTransform);

        yuvBytes = new byte[3][];

        addCallback(
                new OverlayView.DrawCallback() {
                    @Override
                    public void drawCallback(final Canvas canvas) {
                        renderDebug(canvas);
                    }
                });
    }

    @Override
    public void onImageAvailable(final ImageReader reader) {
        Image image = null;

        try {
            image = reader.acquireLatestImage();

            if (image == null) {
                return;
            }

            if (computing) {
                image.close();
                return;
            }
            computing = true;


            final Plane[] planes = image.getPlanes();
            fillBytes(planes, yuvBytes);

            final int yRowStride = planes[0].getRowStride();
            final int uvRowStride = planes[1].getRowStride();
            final int uvPixelStride = planes[1].getPixelStride();
            ImageUtils.convertYUV420ToARGB8888(
                    yuvBytes[0],
                    yuvBytes[1],
                    yuvBytes[2],
                    previewWidth,
                    previewHeight,
                    yRowStride,
                    uvRowStride,
                    uvPixelStride,
                    rgbBytes);

            image.close();
        } catch (final Exception e) {
            if (image != null) {
                image.close();
            }
            LOGGER.e(e, "Exception!");
            return;
        }

        rgbFrameBitmap.setPixels(rgbBytes, 0, previewWidth, 0, 0, previewWidth, previewHeight);
        final Canvas canvas = new Canvas(croppedBitmap);
        canvas.drawBitmap(rgbFrameBitmap, frameToCropTransform, null);

        // For examining the actual TF input.
        if (SAVE_PREVIEW_BITMAP) {
            ImageUtils.saveBitmap(croppedBitmap);
        }

        runInBackground(
                new Runnable() {
                    @Override
                    public void run() {
                        final long startTime = SystemClock.uptimeMillis();
                        final List<Classifier.Recognition> results = classifier.recognizeImage(croppedBitmap);
                        lastProcessingTimeMs = SystemClock.uptimeMillis() - startTime;

                        cropCopyBitmap = Bitmap.createBitmap(croppedBitmap);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (results != null && results.size() > 0) {
                                    Classifier.Recognition bestMatch;
                                    bestMatch = results.get(0);
                                    for (final Classifier.Recognition recog : results) {
                                        if (recog.getConfidence() > bestMatch.getConfidence())
                                            bestMatch = recog;
                                    }
                                    DetectResult food = MyApplication.getCacheData().get(bestMatch.getTitle());
                                    if (food != null) {
                                        String possibleHint = null;

                                        // draw tip
                                        if (food.detailUrl != null) {
                                            mTipTitle.setText(food.name);
                                            possibleHint = "可能性 " + (int)(bestMatch.getConfidence() * 100) + " %       卡路里：" + food.calorie;
                                            mBtnNxt.setImageResource(R.drawable.ic_done_black_24dp);
                                            mBestMatchRate = bestMatch.getConfidence();
                                            mDetectResult = food;
                                            mTipHint.setText(possibleHint);
                                            mHander.removeCallbacksAndMessages(null);
                                            mHander.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    mDetectResult = null;
                                                }
                                            }, 3000);
                                        } else if (mDetectResult == null) {
                                            mTipTitle.setText(food.name);
                                            possibleHint = " 这个好像不能吃";
                                            mBtnNxt.setImageResource(R.drawable.selector_sad_happy);
                                            mBestMatchRate = -1;
                                            mTipHint.setText(possibleHint);
                                        }

                                    }

                                }
                            }
                        });
//                        resultsView.setResults(results);
                        requestRender();
                        computing = false;
                    }
                });

    }

    @Override
    public void onSetDebug(boolean debug) {
        classifier.enableStatLogging(debug);
    }

    private void renderDebug(final Canvas canvas) {
        if (!isDebug()) {
            return;
        }
        final Bitmap copy = cropCopyBitmap;
        if (copy != null) {
            final Matrix matrix = new Matrix();
            final float scaleFactor = 2;
            matrix.postScale(scaleFactor, scaleFactor);
            matrix.postTranslate(
                    canvas.getWidth() - copy.getWidth() * scaleFactor,
                    canvas.getHeight() - copy.getHeight() * scaleFactor);
            canvas.drawBitmap(copy, matrix, new Paint());

            final Vector<String> lines = new Vector<String>();
            if (classifier != null) {
                String statString = classifier.getStatString();
                String[] statLines = statString.split("\n");
                for (String line : statLines) {
                    lines.add(line);
                }
            }

            lines.add("Frame: " + previewWidth + "x" + previewHeight);
            lines.add("Crop: " + copy.getWidth() + "x" + copy.getHeight());
            lines.add("View: " + canvas.getWidth() + "x" + canvas.getHeight());
            lines.add("Rotation: " + sensorOrientation);
            lines.add("Inference time: " + lastProcessingTimeMs + "ms");

            borderedText.drawLines(canvas, 10, canvas.getHeight() - 10, lines);
        }
    }

    @Override
    public synchronized void onDestroy() {
        classifier.close();
        cropCopyBitmap.recycle();
        croppedBitmap.recycle();
        rgbFrameBitmap.recycle();
        super.onDestroy();
    }
}
