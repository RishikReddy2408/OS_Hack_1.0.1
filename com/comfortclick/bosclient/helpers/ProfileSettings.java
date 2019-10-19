package com.comfortclick.bosclient.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.comfortclick.bosclient.profiles.Profile;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Iterator;

public class ProfileSettings
{
  public ProfileSettings() {}
  
  public static ArrayList loadProfiles(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      paramContext = paramContext.getSharedPreferences("CMClientProfileList", 0).getString("json", "");
      Gson localGson = new Gson();
      paramContext = localGson.fromJson(paramContext, new TypeToken() {}.getType());
      paramContext = (ArrayList)paramContext;
      if (paramContext != null) {
        return paramContext;
      }
      paramContext = new ArrayList();
      try
      {
        paramContext.add(new Profile(Boolean.valueOf(true), "Demo", "https://demo.comfortclick.com", "User", "Pass"));
        return paramContext;
      }
      catch (Exception localException1) {}
      localException2.printStackTrace();
    }
    catch (Exception localException2)
    {
      paramContext = localArrayList;
    }
    return paramContext;
  }
  
  public static void saveProfiles(Context paramContext, ArrayList paramArrayList)
  {
    try
    {
      ArrayList localArrayList = new ArrayList(paramArrayList.size());
      paramArrayList = paramArrayList.iterator();
      boolean bool;
      Object localObject;
      for (;;)
      {
        bool = paramArrayList.hasNext();
        if (!bool) {
          break;
        }
        localObject = paramArrayList.next();
        localObject = (Profile)localObject;
        localArrayList.add(new Profile((Profile)localObject));
      }
      paramArrayList = localArrayList.iterator();
      for (;;)
      {
        bool = paramArrayList.hasNext();
        if (!bool) {
          break;
        }
        localObject = paramArrayList.next();
        localObject = (Profile)localObject;
        if (!rememberMe) {
          password = "";
        }
      }
      paramContext = paramContext.getSharedPreferences("CMClientProfileList", 0).edit();
      paramContext.putString("json", new Gson().toJson(localArrayList));
      paramContext.commit();
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void updateProfile(Context paramContext, Profile paramProfile)
  {
    try
    {
      ArrayList localArrayList = loadProfiles(paramContext);
      Iterator localIterator = localArrayList.iterator();
      for (;;)
      {
        boolean bool = localIterator.hasNext();
        if (!bool) {
          break;
        }
        Object localObject = localIterator.next();
        localObject = (Profile)localObject;
        String str1;
        String str2;
        if (manual)
        {
          str1 = serverAddress;
          str2 = serverAddress;
          int i = str1.compareTo(str2);
          if (i == 0)
          {
            serverAddress = serverAddress;
            localAddress = localAddress;
            publicKey = publicKey;
            gatewayAddress = gatewayAddress;
            continue;
          }
        }
        if (!manual)
        {
          str1 = accessID;
          str2 = accessID;
          bool = str1.equals(str2);
          if (bool)
          {
            serverAddress = serverAddress;
            localAddress = localAddress;
            publicKey = publicKey;
            gatewayAddress = gatewayAddress;
          }
        }
      }
      saveProfiles(paramContext, localArrayList);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
}
