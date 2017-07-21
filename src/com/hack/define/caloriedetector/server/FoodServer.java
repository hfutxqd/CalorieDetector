package com.hack.define.caloriedetector.server;


import com.hack.define.caloriedetector.server.model.Food;
import com.hack.define.caloriedetector.server.model.SearchRes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by IMXQD on 2017/7/21.
 */
public class FoodServer {
    public static boolean IMAGE_ZOOM = true;

    public static String getContentHtmlFrom39jianFei(String url) throws IOException, NullPointerException {
        Document doc = Jsoup.connect(url)
                .userAgent(StaticData.CONTENT_USER_AGENT)
                .get();
        Element test2 = doc.getElementById("contentText");
        if (test2 == null) {
            throw new IOException();
        }
        if (IMAGE_ZOOM) {
            Elements imgs = test2.select("img");
            System.out.println(imgs);
            for (Element i : imgs) {
                String tmp = i.attr("alt");
                if (tmp.contains("微信") || tmp.contains("关注") || tmp.contains("39减肥")) {
                    i.remove();
                    continue;
                }
                i.removeAttr("width");
                i.attr("width", "100%");
            }
        }
        return test2.toString();
    }


    protected static int Max_Page_Of_Last_Mode = 1;

    //39减肥可以获取准确值 减肥网只能获取当前页最大页码
    public static synchronized int getMax_Page_Of_Last_Mode() {
        return Max_Page_Of_Last_Mode;
    }

    public static synchronized void setMax_Page_Of_Last_Mode(int value) {
        Max_Page_Of_Last_Mode = value;
    }


    public static Food getSearchContent(String url) throws IOException {

        Document doc = Jsoup.connect(url)
                .userAgent(StaticData.CONTENT_USER_AGENT)
                .get();
        Food food = new Food();

        Elements content = doc.select("span[class=\"ju\"] p").not("p span");
        food.advantages = content.first().text();
        System.out.println(food.advantages);

        food.disadvantages = content.last().text();
        System.out.println(food.disadvantages);

        content = doc.select("span[id=\"food_to_sport\"] td");
        food.walk = content.get(1).text();
        food.running = content.get(3).text();
        food.skipping = content.get(5).text();
        food.aerobics = content.get(7).text();
        System.out.println(food.walk);
        System.out.println(food.running);
        System.out.println(food.skipping);
        System.out.println(food.aerobics);


        content = doc.select("div[id=\"chengfen\"] li");
        for (Element i : content) {
            food.componentTab.titles.add(i.child(0).text());
            food.componentTab.values.add(i.child(1).text());
        }

        for (int i = 0; i < food.componentTab.titles.size(); i++) {
            System.out.println(food.componentTab.titles.get(i));
            System.out.println(food.componentTab.values.get(i));
        }

        return food;
    }

    public static String getSearchContentHtml(String url) throws IOException {
        Document doc = Jsoup.connect(url)
                .userAgent(StaticData.USER_AGENT)
                .get();
        doc.head().select("link").remove();
        doc.head().appendElement("style").append(StaticData.SearchCSS);
        doc.select("script").remove();
        doc.select("header").remove();
        doc.select("div[class=\"title3 flexbox\"]").remove();
        doc.select("div[class=\"search\"]").remove();
        doc.select("div[class=\"hotkey\"]").remove();
        doc.select("footer").remove();
        doc.getElementsContainingOwnText("微信").remove();
        doc.getElementsContainingOwnText("关注").remove();
        doc.getElementsByTag("img").last().remove();
        return doc.html();
    }

    public static ArrayList<SearchRes> search(String key) throws IOException, NullPointerException {
        Document doc = Jsoup.connect(StaticData.SEARCH_URL + URLEncoder.encode(key, "utf8"))
                .userAgent(StaticData.USER_AGENT)
                .get();
        Elements res = doc.select("div[class=\"mapCon\"] a");
        int size = res.size();
        ArrayList<SearchRes> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            SearchRes tmp = new SearchRes();
            tmp.title = res.get(i).attr("title");
            tmp.url = res.get(i).attr("href");
            tmp.describe = res.get(i).child(0).child(0).text();
            try {
                tmp.calory = Float.valueOf
                        (res.get(i).child(0).child(0).child(0).text());
            } catch (Exception e) {
                tmp.calory = 0f;
            }
            if (tmp.url.contains("exercise")) {
                tmp.calory = -tmp.calory;
            }
            list.add(tmp);
        }
        return list;
    }

}