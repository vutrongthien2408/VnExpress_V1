package com.ptit.trongthien.vnexpress_v1.firebase_notify;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.ptit.trongthien.vnexpress_v1.R;
import com.ptit.trongthien.vnexpress_v1.view.home_view.HomeActivity;

/**
 * Created by TrongThien on 8/2/2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "TAG";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String url = "";
        String msg = remoteMessage.getNotification().getBody();
        if(msg.matches("(.*)the thao(.*)")){
            url = "http://vnexpress.net/rss/the-thao.rss";
        }else if(msg.matches("(.*)thoi su(.*)")){
            url = "http://vnexpress.net/rss/thoi-su.rss";
        }else if (msg.matches("(.*)khoa hoc(.*)")){
            url = "http://vnexpress.net/rss/khoa-hoc.rss";
        }else if (msg.matches("(.*)the gioi(.*)")){
            url = "http://vnexpress.net/rss/the-gioi.rss";
        }else if (msg.matches("(.*)kinh doanh(.*)")){
            url = "http://vnexpress.net/rss/kinh-doanh.rss";
        }else if (msg.matches("(.*)giai tri(.*)")){
            url = "http://vnexpress.net/rss/giai-tri.rss";
        }else if (msg.matches("(.*)phap luat(.*)")){
            url = "http://vnexpress.net/rss/phap-luat.rss";
        }
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("url", url);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(this);
        nBuilder.setContentTitle("VnExpress Notifycation");
        nBuilder.setContentText(msg);
        nBuilder.setAutoCancel(true);
        nBuilder.setSmallIcon(R.mipmap.ic_launcher_round);
        nBuilder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, nBuilder.build());
        Log.d(TAG, "From: " + remoteMessage.getFrom());


    }
}
