package com.google.firebase.package_8;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Base64;
import android.util.Log;
import com.google.android.android.common.util.PlatformVersion;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.List;
import javax.annotation.concurrent.GuardedBy;

public final class zzan
{
  private final Context dataService;
  @GuardedBy("this")
  private String zzci;
  @GuardedBy("this")
  private String zzcj;
  @GuardedBy("this")
  private int zzck;
  @GuardedBy("this")
  private int zzcl = 0;
  
  public zzan(Context paramContext)
  {
    dataService = paramContext;
  }
  
  public static String digest(KeyPair paramKeyPair)
  {
    paramKeyPair = paramKeyPair.getPublic().getEncoded();
    try
    {
      paramKeyPair = MessageDigest.getInstance("SHA1").digest(paramKeyPair);
      paramKeyPair[0] = ((byte)((paramKeyPair[0] & 0xF) + 112));
      paramKeyPair = Base64.encodeToString(paramKeyPair, 0, 8, 11);
      return paramKeyPair;
    }
    catch (NoSuchAlgorithmException paramKeyPair)
    {
      for (;;) {}
    }
    Log.w("FirebaseInstanceId", "Unexpected error, device missing required algorithms");
    return null;
  }
  
  private final PackageInfo doInBackground(String paramString)
  {
    Object localObject = dataService;
    try
    {
      paramString = ((Context)localObject).getPackageManager().getPackageInfo(paramString, 0);
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      paramString = String.valueOf(paramString);
      localObject = new StringBuilder(String.valueOf(paramString).length() + 23);
      ((StringBuilder)localObject).append("Failed to find package ");
      ((StringBuilder)localObject).append(paramString);
      Log.w("FirebaseInstanceId", ((StringBuilder)localObject).toString());
    }
    return null;
  }
  
  public static String get(FirebaseApp paramFirebaseApp)
  {
    String str = paramFirebaseApp.getOptions().getGcmSenderId();
    if (str != null) {
      return str;
    }
    paramFirebaseApp = paramFirebaseApp.getOptions().getApplicationId();
    if (!paramFirebaseApp.startsWith("1:")) {
      return paramFirebaseApp;
    }
    paramFirebaseApp = paramFirebaseApp.split(":");
    if (paramFirebaseApp.length < 2) {
      return null;
    }
    paramFirebaseApp = paramFirebaseApp[1];
    if (paramFirebaseApp.isEmpty()) {
      return null;
    }
    return paramFirebaseApp;
  }
  
  private final void zzag()
  {
    try
    {
      PackageInfo localPackageInfo = doInBackground(dataService.getPackageName());
      if (localPackageInfo != null)
      {
        zzci = Integer.toString(versionCode);
        zzcj = versionName;
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public final int zzac()
  {
    try
    {
      if (zzcl != 0)
      {
        i = zzcl;
        return i;
      }
      Object localObject1 = dataService.getPackageManager();
      if (((PackageManager)localObject1).checkPermission("com.google.android.c2dm.permission.SEND", "com.google.android.gms") == -1)
      {
        Log.e("FirebaseInstanceId", "Google Play services missing or without correct permission.");
        return 0;
      }
      if (!PlatformVersion.isAtLeastO())
      {
        localObject2 = new Intent("com.google.android.c2dm.intent.REGISTER");
        ((Intent)localObject2).setPackage("com.google.android.gms");
        localObject2 = ((PackageManager)localObject1).queryIntentServices((Intent)localObject2, 0);
        if ((localObject2 != null) && (((List)localObject2).size() > 0))
        {
          zzcl = 1;
          i = zzcl;
          return i;
        }
      }
      Object localObject2 = new Intent("com.google.iid.TOKEN_REQUEST");
      ((Intent)localObject2).setPackage("com.google.android.gms");
      localObject1 = ((PackageManager)localObject1).queryBroadcastReceivers((Intent)localObject2, 0);
      if ((localObject1 != null) && (((List)localObject1).size() > 0))
      {
        zzcl = 2;
        i = zzcl;
        return i;
      }
      Log.w("FirebaseInstanceId", "Failed to resolve IID implementation package, falling back");
      if (PlatformVersion.isAtLeastO()) {
        zzcl = 2;
      } else {
        zzcl = 1;
      }
      int i = zzcl;
      return i;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public final String zzad()
  {
    try
    {
      if (zzci == null) {
        zzag();
      }
      String str = zzci;
      return str;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public final String zzae()
  {
    try
    {
      if (zzcj == null) {
        zzag();
      }
      String str = zzcj;
      return str;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public final int zzaf()
  {
    try
    {
      if (zzck == 0)
      {
        PackageInfo localPackageInfo = doInBackground("com.google.android.gms");
        if (localPackageInfo != null) {
          zzck = versionCode;
        }
      }
      int i = zzck;
      return i;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
}
