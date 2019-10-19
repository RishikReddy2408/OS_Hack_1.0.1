package com.comfortclick.bosclient;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContextWrapper;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.package_7.NotificationCompat.Builder;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import java.util.Map;

public class NotificationService
  extends FirebaseMessagingService
{
  public NotificationService() {}
  
  private void sendNotification(String paramString1, String paramString2, String paramString3)
  {
    Object localObject = new Intent(this, MainActivity.class);
    ((Intent)localObject).putExtra("NotificationServerID", paramString2);
    paramString2 = PendingIntent.getActivity(getApplicationContext(), 0, (Intent)localObject, 134217728);
    localObject = RingtoneManager.getDefaultUri(2);
    paramString1 = new NotificationCompat.Builder(this, "D1").setSmallIcon(2131099752).setContentTitle(paramString1).setContentText(paramString3).setAutoCancel(true).setSound((Uri)localObject).setContentIntent(paramString2);
    ((NotificationManager)getSystemService("notification")).notify(0, paramString1.build());
  }
  
  public void onMessageReceived(RemoteMessage paramRemoteMessage)
  {
    paramRemoteMessage.getFrom();
    paramRemoteMessage = paramRemoteMessage.getData();
    String str1 = (String)paramRemoteMessage.get("serverName");
    String str2 = (String)paramRemoteMessage.get("message");
    sendNotification(str1, (String)paramRemoteMessage.get("accessID"), str2);
  }
}
