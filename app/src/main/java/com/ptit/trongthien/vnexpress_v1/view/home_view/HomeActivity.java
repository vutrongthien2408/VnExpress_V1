package com.ptit.trongthien.vnexpress_v1.view.home_view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ListView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.ptit.trongthien.vnexpress_v1.R;
import com.ptit.trongthien.vnexpress_v1.adapter.MenuAdapter;
import com.ptit.trongthien.vnexpress_v1.adapter.RvHomeAdapter;
import com.ptit.trongthien.vnexpress_v1.model.entity.ItemMenu;
import com.ptit.trongthien.vnexpress_v1.model.entity.ItemNews;
import com.ptit.trongthien.vnexpress_v1.presenter.home_presenter.HomePresenterImp;
import com.ptit.trongthien.vnexpress_v1.rv_click.RecyclerItemClickListener;
import com.ptit.trongthien.vnexpress_v1.view.MainActivity;
import com.ptit.trongthien.vnexpress_v1.view.detail_view.DetailActivity;
import com.google.android.gms.ads.MobileAds;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by TrongThien on 7/21/2017.
 */

public class HomeActivity extends AppCompatActivity implements HomeView, SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener {

    private static final String TAG = "HomeActivity" ;
    private final int THREAT_SHOT = 5;
    private RecyclerView rvListNews;
    private SwipeRefreshLayout srlListNews;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;
    private ListView lvDrMenu;
    private View footerView;
    private boolean isLoading = false;
    private Set<ItemNews> itemNewses = new HashSet<>();
    private ArrayList<ItemNews> itemNewsesArr = new ArrayList<>();
    private HomePresenterImp homePresenterImp;
    private String url = "http://vnexpress.net/rss/tin-moi-nhat.rss";
    private MenuAdapter menuAdapter;
    private ArrayList<ItemMenu> itemMenus = new ArrayList<>();
    private ProgressDialog progressDialog;
    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
        initData();
        initNav();
        initInterstitialAd();
        initBannerAd();

        final LinearLayoutManager layoutManager = (LinearLayoutManager) rvListNews.getLayoutManager();
        rvListNews.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!isLoading &&
                        layoutManager.getItemCount() - THREAT_SHOT == layoutManager.findLastVisibleItemPosition()) {
//                    goi load more data
//                    rvListNews.addView();
                    homePresenterImp.loadData(url);
                }
            }
        });
    }


    private void initData() {
        Intent intent = getIntent();
        if(intent.getStringExtra("url") == null){
            url = "http://vnexpress.net/rss/tin-moi-nhat.rss";
            Log.d(TAG,"null------------------");

        }else {
            url = intent.getStringExtra("url");
            Log.d(TAG,"url------------------");
        }
    }

    private void initInterstitialAd() {
        MobileAds.initialize(this,"ca-app-pub-9038105707086281~6083634579");
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-9038105707086281/5023807538");
        interstitialAd.loadAd(new AdRequest.Builder().addTestDevice("C46A7257395518A8FB37CCD232A47E65").build());

      interstitialAd.setAdListener(new AdListener() {
          @Override
          public void onAdClosed() {
//              url = "http://vnexpress.net/rss/the-thao.rss";
              homePresenterImp.loadData(url);
              interstitialAd.loadAd(new AdRequest.Builder().addTestDevice("C46A7257395518A8FB37CCD232A47E65").build());
          }
      });
    }

    private void initBannerAd() {
        MobileAds.initialize(this,"ca-app-pub-9038105707086281~6083634579");
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
//        adView.setAdUnitId();
        adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("C46A7257395518A8FB37CCD232A47E65").build();
        adView.loadAd(adRequest);
    }


    private void initView() {
//        progressbar dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading..." );
        progressDialog.show();
//        RecyclerView
        rvListNews = (RecyclerView) findViewById(R.id.rvListNews);
        layoutManager = new GridLayoutManager(this, 1);
        rvListNews.setLayoutManager(layoutManager);
        rvListNews.setHasFixedSize(true);
        adapter = new RvHomeAdapter(itemNewsesArr);
        rvListNews.setAdapter(adapter);
        rvListNews.addOnItemTouchListener(new RecyclerItemClickListener(this,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(HomeActivity.this, DetailActivity.class);
                        intent.putExtra("itemLink", itemNewsesArr.get(position).getLinkDetail());
                        startActivity(intent);
                    }
                })
        );
//        SwipeRefreshLayout
        srlListNews = (SwipeRefreshLayout) findViewById(R.id.srlListNews);
        srlListNews.setOnRefreshListener(this);
//          DrawerLayout
        drawerLayout = (DrawerLayout) findViewById(R.id.drLayout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, 0, 0);
        drawerLayout.addDrawerListener(drawerToggle);
        lvDrMenu = (ListView) findViewById(R.id.lvDrMenu);
//        Goi sang presenter
        homePresenterImp = new HomePresenterImp(this, HomeActivity.this);
        homePresenterImp.loadData(url);
//        lay footerView
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerView = inflater.inflate(R.layout.footer_view, null);
    }

    // tai du lieu thanh cong
    @Override
    public void loadDataSuccess(Set<ItemNews> itemLoadMore) {
//        itemNewses.addAll(itemLoadMore);
        itemNewsesArr.addAll(itemLoadMore);
        adapter.notifyDataSetChanged();
        progressDialog.dismiss();
    }

    // refresh newfeed
    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                lay them du lieu
                itemNewsesArr.clear();
                homePresenterImp.loadData(url);
                srlListNews.setRefreshing(false);
            }
        }, 1500);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (drawerToggle.onOptionsItemSelected(item)) {
                    return false;
                }
                break;
            case R.id.mnLogout:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (itemMenus.get(i).getName()) {
            case "Trang Chu":
                url = "http://vnexpress.net/rss/tin-moi-nhat.rss";
                itemNewsesArr.clear();
                homePresenterImp.loadData(url);
                break;
            case "The Thao":
                url = "http://vnexpress.net/rss/the-thao.rss";
                itemNewsesArr.clear();
                if (interstitialAd.isLoaded()){
                    interstitialAd.show();
                }
                else {
                    homePresenterImp.loadData(url);
                }

                break;
            case "Thoi Su":
                url = "http://vnexpress.net/rss/thoi-su.rss";
                itemNewsesArr.clear();
                homePresenterImp.loadData(url);
                break;
            case "Giai Tri":
                url = "http://vnexpress.net/rss/giai-tri.rss";
                itemNewsesArr.clear();
                homePresenterImp.loadData(url);
                break;
            case "The Gioi":
                url = "http://vnexpress.net/rss/the-gioi.rss";
                itemNewsesArr.clear();
                homePresenterImp.loadData(url);
                break;
            case "Phap Luat":
                url = "http://vnexpress.net/rss/phap-luat.rss";
                itemNewsesArr.clear();
                homePresenterImp.loadData(url);
                break;
            case "Kinh Doanh":
                url = "http://vnexpress.net/rss/kinh-doanh.rss";
                itemNewsesArr.clear();
                homePresenterImp.loadData(url);
                break;
            case "Khoa Hoc":
                url = "http://vnexpress.net/rss/khoa-hoc.rss";
                itemNewsesArr.clear();
                homePresenterImp.loadData(url);
                break;
        }
    }
    private void initNav() {
        lvDrMenu = (ListView) findViewById(R.id.lvDrMenu);
        itemMenus.add(new ItemMenu("Trang Chu"));
        itemMenus.add(new ItemMenu("The Thao"));
        itemMenus.add(new ItemMenu("Thoi Su"));
        itemMenus.add(new ItemMenu("Khoa Hoc"));
        itemMenus.add(new ItemMenu("The Gioi"));
        itemMenus.add(new ItemMenu("Kinh Doanh"));
        itemMenus.add(new ItemMenu("Giai Tri"));
        itemMenus.add(new ItemMenu("Phap Luat"));
        menuAdapter = new MenuAdapter(itemMenus, this);
        lvDrMenu.setAdapter(menuAdapter);
        lvDrMenu.setOnItemClickListener(this);
    }
}
