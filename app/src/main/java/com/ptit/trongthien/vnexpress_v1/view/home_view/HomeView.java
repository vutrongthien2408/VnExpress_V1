package com.ptit.trongthien.vnexpress_v1.view.home_view;

import com.ptit.trongthien.vnexpress_v1.model.entity.ItemNews;

import java.util.Set;

/**
 * Created by TrongThien on 7/23/2017.
 */

public interface HomeView {
    void loadDataSuccess(Set<ItemNews> itemNewses);
}
