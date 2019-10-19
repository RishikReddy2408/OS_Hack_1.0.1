package com.google.firebase.package_8;

import com.google.android.android.tasks.Task;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public abstract interface MessagingChannel
{
  public abstract Task ackMessage(String paramString);
  
  public abstract Task buildChannel(String paramString1, String paramString2);
  
  public abstract Task deleteInstanceId(String paramString);
  
  public abstract Task deleteToken(String paramString1, String paramString2, String paramString3, String paramString4);
  
  public abstract Task getToken(String paramString1, String paramString2, String paramString3, String paramString4);
  
  public abstract boolean isAvailable();
  
  public abstract boolean isChannelBuilt();
  
  public abstract Task subscribeToTopic(String paramString1, String paramString2, String paramString3);
  
  public abstract Task unsubscribeFromTopic(String paramString1, String paramString2, String paramString3);
}
