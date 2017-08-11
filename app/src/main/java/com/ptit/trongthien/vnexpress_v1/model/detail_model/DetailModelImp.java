package com.ptit.trongthien.vnexpress_v1.model.detail_model;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ptit.trongthien.vnexpress_v1.model.entity.ItemNews;
import com.ptit.trongthien.vnexpress_v1.model.home_model.VolleyCallback;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by TrongThien on 7/30/2017.
 */

public class DetailModelImp implements DetailModel {
    private Context context;

    public DetailModelImp(Context context) {
        this.context = context;
    }

    @Override
    public void loadDetailData(String link, final VolleyCallback callback) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                Document doc = Jsoup.parse(response);
                if (doc == null) {
                    return;
                }
                Element element = doc.select("div.title_news").first();
                Element elementTitle = element.select("h1").first();
                Element element1 = doc.select("div#left_calculator").first();
                Element elementImage = element1.select("img").first();
                Elements elements = element1.select("p");
                String content = "";
                for (Element e : elements) {
                    content+= e.text();
                }
                String image = elementImage.attr("src");
                String title = elementTitle.text();
                ItemNews itemNews = new ItemNews(image, title, content);

                callback.onLoadDetail(itemNews);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }

}
