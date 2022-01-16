package com.example.mobiledevelopment.UI;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.util.Random;

public class MyReceiver extends BroadcastReceiver {
    private String string;
    private String string2;
    private String string3;
    private String string4;
    @Override

    public void onReceive(Context context, Intent intent) {
        Random random = new Random();
        int m = random.nextInt(9999-1000)+1000;
        AlertHelper alertHelper = new AlertHelper(context);
        string = "Course: ";
        string2 = intent.getStringExtra("Date").toString();
        NotificationCompat.Builder nb = alertHelper.getChannelNotification(string, string2);
        NotificationCompat.Builder nb2 = alertHelper.getChannelNotification2(string, string2);
        Toast.makeText(context, intent.getStringExtra("Date"), Toast.LENGTH_SHORT).show();
        Toast.makeText(context, intent.getStringExtra("Date"), Toast.LENGTH_SHORT).show();
        alertHelper.getNotificationManager().notify(m, nb.build());
        alertHelper.getNotificationManager().notify(m, nb2.build());

    }
}
