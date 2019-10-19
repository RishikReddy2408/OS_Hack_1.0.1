package com.google.firebase.messaging;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.android.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.package_8.FirebaseInstanceId;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FirebaseMessaging
{
  public static final String INSTANCE_ID_SCOPE = "FCM";
  private static final Pattern zzdp = Pattern.compile("[a-zA-Z0-9-_.~%]{1,900}");
  private static FirebaseMessaging zzdq;
  private final FirebaseInstanceId zzdj;
  
  private FirebaseMessaging(FirebaseInstanceId paramFirebaseInstanceId)
  {
    zzdj = paramFirebaseInstanceId;
  }
  
  public static FirebaseMessaging getInstance()
  {
    try
    {
      if (zzdq == null) {
        zzdq = new FirebaseMessaging(FirebaseInstanceId.getInstance());
      }
      FirebaseMessaging localFirebaseMessaging = zzdq;
      return localFirebaseMessaging;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public boolean isAutoInitEnabled()
  {
    return zzdj.isAuthenticated();
  }
  
  public void send(RemoteMessage paramRemoteMessage)
  {
    if (!TextUtils.isEmpty(paramRemoteMessage.getTo()))
    {
      Context localContext = FirebaseApp.getInstance().getApplicationContext();
      Intent localIntent1 = new Intent("com.google.android.gcm.intent.SEND");
      Intent localIntent2 = new Intent();
      localIntent2.setPackage("com.google.example.invalidpackage");
      localIntent1.putExtra("app", PendingIntent.getBroadcast(localContext, 0, localIntent2, 0));
      localIntent1.setPackage("com.google.android.gms");
      localIntent1.putExtras(zzds);
      localContext.sendOrderedBroadcast(localIntent1, "com.google.android.gtalkservice.permission.GTALK_SERVICE");
      return;
    }
    throw new IllegalArgumentException("Missing 'to'");
  }
  
  public void setAutoInitEnabled(boolean paramBoolean)
  {
    zzdj.setSorting(paramBoolean);
  }
  
  public Task subscribeToTopic(String paramString)
  {
    String str = paramString;
    if (paramString != null)
    {
      str = paramString;
      if (paramString.startsWith("/topics/"))
      {
        Log.w("FirebaseMessaging", "Format /topics/topic-name is deprecated. Only 'topic-name' should be used in subscribeToTopic.");
        str = paramString.substring(8);
      }
    }
    if ((str != null) && (zzdp.matcher(str).matches()))
    {
      FirebaseInstanceId localFirebaseInstanceId = zzdj;
      paramString = String.valueOf("S!");
      str = String.valueOf(str);
      if (str.length() != 0) {
        paramString = paramString.concat(str);
      } else {
        paramString = new String(paramString);
      }
      return localFirebaseInstanceId.exchange(paramString);
    }
    paramString = new StringBuilder(String.valueOf(str).length() + 78);
    paramString.append("Invalid topic name: ");
    paramString.append(str);
    paramString.append(" does not match the allowed format [a-zA-Z0-9-_.~%]{1,900}");
    throw new IllegalArgumentException(paramString.toString());
  }
  
  public Task unsubscribeFromTopic(String paramString)
  {
    String str = paramString;
    if (paramString != null)
    {
      str = paramString;
      if (paramString.startsWith("/topics/"))
      {
        Log.w("FirebaseMessaging", "Format /topics/topic-name is deprecated. Only 'topic-name' should be used in unsubscribeFromTopic.");
        str = paramString.substring(8);
      }
    }
    if ((str != null) && (zzdp.matcher(str).matches()))
    {
      FirebaseInstanceId localFirebaseInstanceId = zzdj;
      paramString = String.valueOf("U!");
      str = String.valueOf(str);
      if (str.length() != 0) {
        paramString = paramString.concat(str);
      } else {
        paramString = new String(paramString);
      }
      return localFirebaseInstanceId.exchange(paramString);
    }
    paramString = new StringBuilder(String.valueOf(str).length() + 78);
    paramString.append("Invalid topic name: ");
    paramString.append(str);
    paramString.append(" does not match the allowed format [a-zA-Z0-9-_.~%]{1,900}");
    throw new IllegalArgumentException(paramString.toString());
  }
}
