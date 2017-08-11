package com.ptit.trongthien.vnexpress_v1.model.detail_model;

import com.ptit.trongthien.vnexpress_v1.model.home_model.VolleyCallback;

/**
 * Created by TrongThien on 7/30/2017.
 */

public interface DetailModel {
    void loadDetailData(String link, final VolleyCallback callback);
}
