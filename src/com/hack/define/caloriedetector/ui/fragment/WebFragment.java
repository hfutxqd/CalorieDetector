package com.hack.define.caloriedetector.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.hack.define.caloriedetector.R;
import com.hack.define.caloriedetector.server.FoodServer;

import java.io.IOException;

/**
 * Created by imxqd on 2017/7/21.
 */

public class WebFragment extends Fragment {
    private static final String TAG = "WebFragment";

    private static final String ARG_URL = "URL";

    View rootView;
    LinearLayout webviewLayout;
    WebView webView;
    String mUrl;

    public static WebFragment newInstance(String url) {
        WebFragment fragment = new WebFragment();
        Bundle args = new Bundle();
        args.putString(ARG_URL, url);
        fragment.setArguments(args);
        return fragment;
    }

    public WebFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUrl = getArguments().getString(ARG_URL);
        }
    }

    Handler handler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_web, container, false);
        webviewLayout = (LinearLayout) rootView.findViewById(R.id.webview);
        //屏蔽长按事件
        webView = new WebView(getActivity().getApplicationContext());
        webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (isAdded() && !isDetached()) {
                    if (msg.what == 404) {
                        // TODO: 2017/7/21  tell activity the error.
                    } else {

                        // TODO: 2017/7/21 tell activity the data loaded successfully.
                        Bundle data = msg.getData();
                        String content = data.getString("content");
                        WebSettings settings = webView.getSettings();
                        settings.setLoadWithOverviewMode(true);
                        webView.loadData(content, "text/html; charset=UTF-8", null);
                    }
                }
                return true;
            }
        });
        Log.d(TAG, "onCreateView: " + "mUrl in WebView is :" + mUrl);
        new Thread(new PullSearchContent()).start();
        webviewLayout.addView(webView);
        return rootView;
    }

    @Override
    public void onDestroy() {
        webView.destroy();
        webView.destroyDrawingCache();
        webviewLayout.removeAllViews();
        webviewLayout.destroyDrawingCache();
        rootView.destroyDrawingCache();
        webView = null;
        webviewLayout = null;
        rootView = null;
        super.onDestroy();
    }

    private class PullSearchContent implements Runnable {
        @Override
        public void run() {
            try {
                String content = FoodServer.getSearchContentHtml(mUrl);
                Bundle data = new Bundle();
                data.putString("content", content);
                Message msg = Message.obtain(handler);
                msg.setData(data);
                handler.sendMessage(msg);

            } catch (IOException e) {
                handler.sendEmptyMessage(404);
            }
        }
    }
}
