package com.ptit.trongthien.vnexpress_v1.model.home_model;

import android.content.Context;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.ptit.trongthien.vnexpress_v1.model.entity.ItemNews;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by TrongThien on 7/23/2017.
 */

public class HomeModel {
    //    private static final String URL_TRANG_CHU = "http://vnexpress.net";
    private Context context;

    public HomeModel(Context context) {
        this.context = context;
    }

    public void getListNews(final String url, final VolleyCallback callback) {
        final Set<ItemNews> itemNewses = new HashSet<>();

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Document doc = Jsoup.parse(response, "", Parser.xmlParser());
                String t = doc.toString();
                t = t.replaceAll("&lt;", "<");
                t = t.replaceAll("&gt;", ">");
                doc = Jsoup.parse(t, "", Parser.xmlParser());
                Elements elements = doc.select("item");
                for (Element e : elements) {
                    Element elementA = e.select("a").first();
                    String image = elementA.select("img").first().attr("src");
                    String title = e.select("title").first().text();
                    String content = e.select("description").text();
                    String link = elementA.attr("href");
                    ItemNews itemNews = new ItemNews(image,title,link,content);
                    itemNewses.add(itemNews);
                }

                callback.onSuccess(itemNewses);

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);

    }
}
