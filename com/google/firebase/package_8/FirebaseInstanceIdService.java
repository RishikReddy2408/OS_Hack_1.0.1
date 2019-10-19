package com.google.firebase.package_8;

import android.content.Intent;
import android.util.Log;
import java.util.Queue;

@Deprecated
public class FirebaseInstanceIdService
  extends IRCService
{
  public FirebaseInstanceIdService() {}
  
  public final void doInBackground(Intent paramIntent)
  {
    if ("com.google.firebase.iid.TOKEN_REFRESH".equals(paramIntent.getAction()))
    {
      onTokenRefresh();
      return;
    }
    String str = paramIntent.getStringExtra("CMD");
    if (str != null)
    {
      if (Log.isLoggable("FirebaseInstanceId", 3))
      {
        paramIntent = String.valueOf(paramIntent.getExtras());
        StringBuilder localStringBuilder = new StringBuilder(String.valueOf(str).length() + 21 + String.valueOf(paramIntent).length());
        localStringBuilder.append("Received command: ");
        localStringBuilder.append(str);
        localStringBuilder.append(" - ");
        localStringBuilder.append(paramIntent);
        Log.d("FirebaseInstanceId", localStringBuilder.toString());
      }
      if ((!"RST".equals(str)) && (!"RST_FULL".equals(str)))
      {
        if ("SYNC".equals(str)) {
          FirebaseInstanceId.getInstance().deleteWorkout();
        }
      }
      else {
        FirebaseInstanceId.getInstance().getFullPath();
      }
    }
  }
  
  protected final Intent execute(Intent paramIntent)
  {
    return (Intent)zzaizzda.poll();
  }
  
  public void onTokenRefresh() {}
}
