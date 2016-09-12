package com.renarosantos.integrationtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by renarosantos on 23/08/16.
 */
public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = getNotification("DESLIGOU!", context);
        notificationManager.notify(2, notification);
    }

    private Notification getNotification(String content, Context context) {
        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentTitle("Shut Down");
        builder.setContentText(content);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        return builder.build();
    }
}