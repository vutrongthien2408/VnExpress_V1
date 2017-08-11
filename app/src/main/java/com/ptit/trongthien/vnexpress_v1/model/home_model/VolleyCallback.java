package com.ptit.trongthien.vnexpress_v1.model.home_model;


import com.ptit.trongthien.vnexpress_v1.model.entity.ItemNews;

import java.util.Set;

/**
 * Created by TrongThien on 7/29/2017.
 */

public interface VolleyCallback {
    void onSuccess(Set<ItemNews> itemNewses);
    void onLoadDetail(ItemNews itemNews);
}
