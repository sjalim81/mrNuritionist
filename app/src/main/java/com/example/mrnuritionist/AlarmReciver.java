package com.example.mrnuritionist;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int notificationId=intent.getIntExtra("notificationId",0);
        String message=intent.getStringExtra("todo");
        Intent reminderIntent=new Intent(context,ReminderActivity.class);
        PendingIntent contentIntent=PendingIntent.getActivity(context,0,reminderIntent,0);

        NotificationManager myNotificationManager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        Notification.Builder builder=new Notification.Builder(context);
        builder.setSmallIcon(android.R.drawable.ic_dialog_info).setContentTitle("It,s time")
                .setContentText(message)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentIntent(contentIntent);

        myNotificationManager.notify(notificationId,builder.build());
    }
}
