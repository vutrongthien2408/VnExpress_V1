package com.ptit.trongthien.vnexpress_v1.presenter.detail_presenter;

import android.content.Context;

import com.ptit.trongthien.vnexpress_v1.model.detail_model.DetailModelImp;
import com.ptit.trongthien.vnexpress_v1.model.entity.ItemNews;
import com.ptit.trongthien.vnexpress_v1.model.home_model.VolleyCallback;
import com.ptit.trongthien.vnexpress_v1.view.detail_view.DetailView;

import java.util.Set;

/**
 * Created by TrongThien on 7/30/2017.
 */

public class DetailPresenterImp implements DetailPresenter {
    private Context context;
    private DetailView detailView;
    public DetailPresenterImp(DetailView detailView,Context context) {
        this.context = context;
        this.detailView = detailView;
    }

    @Override
    public void loadDetailData(String link) {
        DetailModelImp detailModelImp = new DetailModelImp(context);
        detailModelImp.loadDetailData(link, new VolleyCallback() {
            @Override
            public void onSuccess(Set<ItemNews> itemNewses) {

            }

            @Override
            public void onLoadDetail(ItemNews itemNews) {
                detailView.loadDataSuccess(itemNews);
            }
        });
    }
}
