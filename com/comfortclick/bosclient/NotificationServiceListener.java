package com.comfortclick.bosclient;

import android.app.Service;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import com.comfortclick.bosclient.helpers.ProfileSettings;
import com.comfortclick.bosclient.profiles.Profile;
import com.google.firebase.package_8.FirebaseInstanceId;
import com.google.firebase.package_8.FirebaseInstanceIdService;
import java.util.ArrayList;
import java.util.Iterator;

public class NotificationServiceListener
  extends FirebaseInstanceIdService
{
  public static String OldToken;
  public static String Token;
  
  public NotificationServiceListener() {}
  
  private void refreshToken()
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          FirebaseInstanceId.getInstance().deleteInstanceId();
          return;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
    }).start();
  }
  
  private void updateProfiles()
  {
    Iterator localIterator = ProfileSettings.loadProfiles(getApplicationContext()).iterator();
    while (localIterator.hasNext()) {
      ((Profile)localIterator.next()).updatePushToken(OldToken, Token);
    }
  }
  
  public void onCreate()
  {
    super.onCreate();
    Object localObject = getApplicationContext().getSharedPreferences("CMClientProfileList", 0);
    String str = ((SharedPreferences)localObject).getString("token", "");
    if (!str.isEmpty())
    {
      Token = str;
      OldToken = Token;
      return;
    }
    Token = FirebaseInstanceId.getInstance().getToken();
    OldToken = Token;
    localObject = ((SharedPreferences)localObject).edit();
    ((SharedPreferences.Editor)localObject).putString("token", Token);
    ((SharedPreferences.Editor)localObject).commit();
  }
  
  public void onTokenRefresh()
  {
    Token = FirebaseInstanceId.getInstance().getToken();
    Object localObject = getApplicationContext().getSharedPreferences("CMClientProfileList", 0).edit();
    ((SharedPreferences.Editor)localObject).putString("token", Token);
    ((SharedPreferences.Editor)localObject).commit();
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Refreshed token: ");
    ((StringBuilder)localObject).append(Token);
    Log.d("InstanceIdService", ((StringBuilder)localObject).toString());
    updateProfiles();
    OldToken = Token;
  }
}
