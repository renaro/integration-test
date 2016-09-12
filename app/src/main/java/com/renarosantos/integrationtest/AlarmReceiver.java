package com.renarosantos.integrationtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by renarosantos on 23/08/16.
 */
public class AlarmReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(final Context context, final Intent intent) {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = getNotification("Teste", context);
        notificationManager.notify(2, notification);
    }

    private Notification getNotification(String content, Context context) {
        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentTitle("Scheduled Notification");
        builder.setContentText(content);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        final Uri defaultUri = Uri.parse("android.resource://com.renarosantos.integrationtest/" + R.raw.birl);
        builder.setSound(defaultUri);
        return builder.build();
    }
}
