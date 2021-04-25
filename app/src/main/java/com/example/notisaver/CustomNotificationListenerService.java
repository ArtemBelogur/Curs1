package com.example.notisaver;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

import androidx.annotation.RequiresApi;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import com.example.notisaver.useful.CNLSHelper;


public class CustomNotificationListenerService extends NotificationListenerService {

    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onNotificationPosted(StatusBarNotification sbn){

        StatusBarNotification[] activeNotifications = this.getActiveNotifications();

        if(activeNotifications != null && activeNotifications.length > 0) {
            for (StatusBarNotification activeNotification : activeNotifications) {
                Bundle extras = sbn.getNotification().extras;
                Notification notification = sbn.getNotification();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    Icon largeIcon = notification.getLargeIcon();

                    if (largeIcon != null) {
                        largeIcon.loadDrawable(this);
                    }
                }
                String name = CNLSHelper.getAppNameFromPackage(this, sbn.getPackageName(), false);
                String user = extras.getString("android.title");
                String text = Objects.requireNonNull(extras.getCharSequence("android.text")).toString();


                @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a");
                String time =  simpleDateFormat.format(sbn.getPostTime());


                String chanelId = Integer.toString(sbn.getId());
                DatabaseHelper dbHelper = new DatabaseHelper(CustomNotificationListenerService.this);
                dbHelper.addMessage(sbn.getPackageName(), name, user, text, time, chanelId, sbn.getGroupKey());
                break;
            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onNotificationRemoved(StatusBarNotification sbn){
        StatusBarNotification[] activeNotifications = this.getActiveNotifications();

        if(activeNotifications != null && activeNotifications.length > 0) {
            for (StatusBarNotification activeNotification : activeNotifications) {
                Bundle extras = sbn.getNotification().extras;
                String name = CNLSHelper.getAppNameFromPackage(this, sbn.getPackageName(), false);
                String user = extras.getString("android.title");
                String text = Objects.requireNonNull(extras.getCharSequence("android.text")).toString();

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a");
                String time =  simpleDateFormat.format(sbn.getPostTime());

                String chanelId = Integer.toString(sbn.getId());
                DatabaseHelper dbHelper = new DatabaseHelper(CustomNotificationListenerService.this);
                dbHelper.addMessage(sbn.getPackageName(), name, user, text, time, chanelId, sbn.getGroupKey());
                break;
            }
        }
    }



}


