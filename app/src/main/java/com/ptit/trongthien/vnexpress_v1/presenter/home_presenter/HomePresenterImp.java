package com.ptit.trongthien.vnexpress_v1.presenter.home_presenter;

import android.content.Context;
import android.widget.Toast;

import com.ptit.trongthien.vnexpress_v1.model.entity.ItemNews;
import com.ptit.trongthien.vnexpress_v1.model.home_model.HomeModel;
import com.ptit.trongthien.vnexpress_v1.model.home_model.VolleyCallback;
import com.ptit.trongthien.vnexpress_v1.view.home_view.HomeView;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by TrongThien on 7/23/2017.
 */

public class HomePresenterImp implements HomePresenter {
    private HomeView homeView;
    private Context context;
    public HomePresenterImp(HomeView homeView, Context context) {
        this.homeView = homeView;
        this.context = context;
    }

    @Override
    public void loadData(String url) {
//        final Set<ItemNews> itemNewses1 = new HashSet<>();
        HomeModel homeModel = new HomeModel(context);
        homeModel.getListNews(url,new VolleyCallback() {
            @Override
            public void onSuccess(Set<ItemNews> itemNewses) {
//                itemNewses1.addAll(itemNewses);
//                Toast.makeText(context, itemNews.getTitle(), Toast.LENGTH_SHORT).show();
                homeView.loadDataSuccess(itemNewses);
            }

            @Override
            public void onLoadDetail(ItemNews itemNews) {

            }
        });

    }
}
