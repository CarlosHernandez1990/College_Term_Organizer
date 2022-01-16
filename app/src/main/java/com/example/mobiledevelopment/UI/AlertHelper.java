package com.example.mobiledevelopment.UI;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.example.mobiledevelopment.R;

public class AlertHelper extends ContextWrapper {
    public static final String channelID = "channelID";
    public static final String channelName = "Channel 1";
    public static final String channelID2 = "channelID2";
    public static final String channelName2 = "Channel 2";
    private NotificationManager mManager;
    public AlertHelper(Context base) {
        super(base);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
        createChannel();
        createChannel2();
        }
    }

    public void createChannel(){
        NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_DEFAULT);
        channel.enableLights(true);
        channel.enableVibration(true);
        channel.setLightColor(R.color.design_default_color_primary);
        channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        getNotificationManager().createNotificationChannel(channel);
    }
    public void createChannel2(){
        NotificationChannel channel2 = new NotificationChannel(channelID2, channelName2, NotificationManager.IMPORTANCE_DEFAULT);
        channel2.enableLights(true);
        channel2.enableVibration(true);
        channel2.setLightColor(R.color.design_default_color_primary);
        channel2.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        getNotificationManager().createNotificationChannel(channel2);
    }

    public NotificationManager getNotificationManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }

    public NotificationCompat.Builder getChannelNotification(String title, String message){
        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.grad);
    }

    public NotificationCompat.Builder getChannelNotification2(String title, String message){
        return new NotificationCompat.Builder(getApplicationContext(), channelID2)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.grad);
    }


}
