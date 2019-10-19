package com.google.firebase.messaging;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.Color;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import java.util.Arrays;
import java.util.Iterator;
import java.util.MissingFormatArgumentException;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;

final class Model
{
  private static final AtomicInteger zzdn = new AtomicInteger((int)SystemClock.elapsedRealtime());
  private final Context context;
  private Bundle zzdo;
  
  public Model(Context paramContext)
  {
    context = paramContext.getApplicationContext();
  }
  
  private final Integer create(String paramString)
  {
    if (Build.VERSION.SDK_INT < 21) {
      return null;
    }
    if (!TextUtils.isEmpty(paramString)) {}
    try
    {
      i = Color.parseColor(paramString);
      return Integer.valueOf(i);
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      int i;
      StringBuilder localStringBuilder;
      for (;;) {}
    }
    localStringBuilder = new StringBuilder(String.valueOf(paramString).length() + 54);
    localStringBuilder.append("Color ");
    localStringBuilder.append(paramString);
    localStringBuilder.append(" not valid. Notification will use default color.");
    Log.w("FirebaseMessaging", localStringBuilder.toString());
    i = zzas().getInt("com.google.firebase.messaging.default_notification_color", 0);
    if (i != 0)
    {
      paramString = context;
      try
      {
        i = ContextCompat.getColor(paramString, i);
        return Integer.valueOf(i);
      }
      catch (Resources.NotFoundException paramString)
      {
        for (;;) {}
      }
      Log.w("FirebaseMessaging", "Cannot find the color resource referenced in AndroidManifest.");
      return null;
    }
    return null;
  }
  
  private final boolean get(int paramInt)
  {
    if (Build.VERSION.SDK_INT != 26) {
      return true;
    }
    Object localObject = context;
    try
    {
      localObject = ((Context)localObject).getResources().getDrawable(paramInt, null);
      if ((localObject instanceof AdaptiveIconDrawable))
      {
        localObject = new StringBuilder(77);
        ((StringBuilder)localObject).append("Adaptive icons cannot be used in notifications. Ignoring icon id: ");
        ((StringBuilder)localObject).append(paramInt);
        Log.e("FirebaseMessaging", ((StringBuilder)localObject).toString());
        return false;
      }
      return true;
    }
    catch (Resources.NotFoundException localNotFoundException) {}
    return false;
  }
  
  static String getData(Bundle paramBundle, String paramString)
  {
    paramString = String.valueOf(paramString);
    String str = String.valueOf("_loc_key");
    if (str.length() != 0) {
      paramString = paramString.concat(str);
    } else {
      paramString = new String(paramString);
    }
    return getString(paramBundle, paramString);
  }
  
  static String getName(Bundle paramBundle)
  {
    String str2 = getString(paramBundle, "gcm.n.sound2");
    String str1 = str2;
    if (TextUtils.isEmpty(str2)) {
      str1 = getString(paramBundle, "gcm.n.sound");
    }
    return str1;
  }
  
  static String getString(Bundle paramBundle, String paramString)
  {
    String str = paramBundle.getString(paramString);
    if (str == null) {
      return paramBundle.getString(paramString.replace("gcm.n.", "gcm.notification."));
    }
    return str;
  }
  
  private final String getText(Bundle paramBundle, String paramString)
  {
    String str = getString(paramBundle, paramString);
    if (!TextUtils.isEmpty(str)) {
      return str;
    }
    str = getData(paramBundle, paramString);
    if (TextUtils.isEmpty(str)) {
      return null;
    }
    Object localObject = context.getResources();
    int i = ((Resources)localObject).getIdentifier(str, "string", context.getPackageName());
    if (i == 0)
    {
      paramBundle = String.valueOf(paramString);
      paramString = String.valueOf("_loc_key");
      if (paramString.length() != 0) {
        paramBundle = paramBundle.concat(paramString);
      } else {
        paramBundle = new String(paramBundle);
      }
      paramBundle = paramBundle.substring(6);
      paramString = new StringBuilder(String.valueOf(paramBundle).length() + 49 + String.valueOf(str).length());
      paramString.append(paramBundle);
      paramString.append(" resource not found: ");
      paramString.append(str);
      paramString.append(" Default value will be used.");
      Log.w("FirebaseMessaging", paramString.toString());
      return null;
    }
    paramBundle = getValue(paramBundle, paramString);
    if (paramBundle == null) {
      return ((Resources)localObject).getString(i);
    }
    try
    {
      paramString = ((Resources)localObject).getString(i, paramBundle);
      return paramString;
    }
    catch (MissingFormatArgumentException paramString)
    {
      paramBundle = Arrays.toString(paramBundle);
      localObject = new StringBuilder(String.valueOf(str).length() + 58 + String.valueOf(paramBundle).length());
      ((StringBuilder)localObject).append("Missing format argument for ");
      ((StringBuilder)localObject).append(str);
      ((StringBuilder)localObject).append(": ");
      ((StringBuilder)localObject).append(paramBundle);
      ((StringBuilder)localObject).append(" Default value will be used.");
      Log.w("FirebaseMessaging", ((StringBuilder)localObject).toString(), paramString);
    }
    return null;
  }
  
  static Uri getUrl(Bundle paramBundle)
  {
    String str2 = getString(paramBundle, "gcm.n.link_android");
    String str1 = str2;
    if (TextUtils.isEmpty(str2)) {
      str1 = getString(paramBundle, "gcm.n.link");
    }
    if (!TextUtils.isEmpty(str1)) {
      return Uri.parse(str1);
    }
    return null;
  }
  
  static Object[] getValue(Bundle paramBundle, String paramString)
  {
    String str = String.valueOf(paramString);
    Object localObject1 = String.valueOf("_loc_args");
    if (((String)localObject1).length() != 0) {
      str = str.concat((String)localObject1);
    } else {
      str = new String(str);
    }
    str = getString(paramBundle, str);
    if (TextUtils.isEmpty(str)) {
      return null;
    }
    try
    {
      paramBundle = new JSONArray(str);
      int i = paramBundle.length();
      localObject1 = new String[i];
      i = 0;
      while (i < localObject1.length)
      {
        Object localObject2 = paramBundle.opt(i);
        localObject1[i] = localObject2;
        i += 1;
      }
      return localObject1;
    }
    catch (JSONException paramBundle)
    {
      for (;;) {}
    }
    paramBundle = String.valueOf(paramString);
    paramString = String.valueOf("_loc_args");
    if (paramString.length() != 0) {
      paramBundle = paramBundle.concat(paramString);
    } else {
      paramBundle = new String(paramBundle);
    }
    paramBundle = paramBundle.substring(6);
    paramString = new StringBuilder(String.valueOf(paramBundle).length() + 41 + String.valueOf(str).length());
    paramString.append("Malformed ");
    paramString.append(paramBundle);
    paramString.append(": ");
    paramString.append(str);
    paramString.append("  Default value will be used.");
    Log.w("FirebaseMessaging", paramString.toString());
    return null;
  }
  
  private static void load(Intent paramIntent, Bundle paramBundle)
  {
    Iterator localIterator = paramBundle.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if ((str.startsWith("google.c.a.")) || (str.equals("from"))) {
        paramIntent.putExtra(str, paramBundle.getString(str));
      }
    }
  }
  
  static boolean load(Bundle paramBundle)
  {
    return ("1".equals(getString(paramBundle, "gcm.n.e"))) || (getString(paramBundle, "gcm.n.icon") != null);
  }
  
  private final Bundle zzas()
  {
    if (zzdo != null) {
      return zzdo;
    }
    Object localObject1 = null;
    Object localObject2 = context;
    try
    {
      localObject2 = ((Context)localObject2).getPackageManager();
      Context localContext = context;
      localObject2 = ((PackageManager)localObject2).getApplicationInfo(localContext.getPackageName(), 128);
      localObject1 = localObject2;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;) {}
    }
    if ((localObject1 != null) && (metaData != null))
    {
      zzdo = metaData;
      return zzdo;
    }
    return Bundle.EMPTY;
  }
  
  final boolean onPostExecute(Bundle paramBundle)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: fail exe a40 = a39\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:92)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.dfs(Cfg.java:255)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze0(BaseAnalyze.java:75)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze(BaseAnalyze.java:69)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer.transform(UnSSATransformer.java:274)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:163)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\nCaused by: java.lang.NullPointerException\n");
  }
}
