package com.ptit.trongthien.vnexpress_v1.view.detail_view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.ptit.trongthien.vnexpress_v1.R;


/**
 * Created by TrongThien on 7/30/2017.
 */

public class DetailActivity extends AppCompatActivity {
    private WebView webView;
    private String link = "";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        link = intent.getStringExtra("itemLink");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        loadDataFromLink();
    }

    private void loadDataFromLink() {
        webView = (WebView) findViewById(R.id.wvDetail);
        progressDialog = new ProgressDialog(this);
        getWindow().setFeatureInt(Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON);

        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                setTitle("Loading..." + progress + "%");
                setProgress(progress * 100);
                progressDialog.setMessage("Loading..." + progress + "%");

                if (progress == 100){
                    setTitle(R.string.app_name);
                    progressDialog.dismiss();
                }

            }
        });
        webView.getSettings().setJavaScriptEnabled(true);

        progressDialog.show();
        webView.loadUrl(link);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
