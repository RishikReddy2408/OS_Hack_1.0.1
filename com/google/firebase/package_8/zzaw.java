package com.google.firebase.package_8;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.GuardedBy;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import com.google.firebase.iid.zzz;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

final class zzaw
{
  private final Context mcontext;
  private final SharedPreferences zzdc;
  private final Cache zzdd;
  @GuardedBy("this")
  private final Map<String, zzz> zzde = new ArrayMap();
  
  public zzaw(Context paramContext)
  {
    this(paramContext, new Cache());
  }
  
  private zzaw(Context paramContext, Cache paramCache)
  {
    mcontext = paramContext;
    zzdc = paramContext.getSharedPreferences("com.google.android.gms.appid", 0);
    zzdd = paramCache;
    paramContext = new File(ContextCompat.getNoBackupFilesDir(mcontext), "com.google.android.gms.appid-no-backup");
    if (!paramContext.exists()) {
      try
      {
        boolean bool = paramContext.createNewFile();
        if (bool)
        {
          bool = isEmpty();
          if (!bool)
          {
            Log.i("FirebaseInstanceId", "App restored, clearing state");
            zzal();
            FirebaseInstanceId.getInstance().getFullPath();
            return;
          }
        }
      }
      catch (IOException paramContext)
      {
        if (Log.isLoggable("FirebaseInstanceId", 3))
        {
          paramContext = String.valueOf(paramContext.getMessage());
          if (paramContext.length() != 0) {
            paramContext = "Error creating file in no backup dir: ".concat(paramContext);
          } else {
            paramContext = new String("Error creating file in no backup dir: ");
          }
          Log.d("FirebaseInstanceId", paramContext);
        }
      }
    }
  }
  
  static String getName(String paramString1, String paramString2)
  {
    StringBuilder localStringBuilder = new StringBuilder(String.valueOf(paramString1).length() + 3 + String.valueOf(paramString2).length());
    localStringBuilder.append(paramString1);
    localStringBuilder.append("|S|");
    localStringBuilder.append(paramString2);
    return localStringBuilder.toString();
  }
  
  private final boolean isEmpty()
  {
    try
    {
      boolean bool = zzdc.getAll().isEmpty();
      return bool;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  private static String serialize(String paramString1, String paramString2, String paramString3)
  {
    StringBuilder localStringBuilder = new StringBuilder(String.valueOf(paramString1).length() + 4 + String.valueOf(paramString2).length() + String.valueOf(paramString3).length());
    localStringBuilder.append(paramString1);
    localStringBuilder.append("|T|");
    localStringBuilder.append(paramString2);
    localStringBuilder.append("|");
    localStringBuilder.append(paramString3);
    return localStringBuilder.toString();
  }
  
  public final void delete(String paramString)
  {
    try
    {
      paramString = String.valueOf(paramString).concat("|T|");
      SharedPreferences.Editor localEditor = zzdc.edit();
      Iterator localIterator = zzdc.getAll().keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        if (str.startsWith(paramString)) {
          localEditor.remove(str);
        }
      }
      localEditor.commit();
      return;
    }
    catch (Throwable paramString)
    {
      throw paramString;
    }
  }
  
  public final zzax get(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      paramString1 = zzax.init(zzdc.getString(serialize(paramString1, paramString2, paramString3), null));
      return paramString1;
    }
    catch (Throwable paramString1)
    {
      throw paramString1;
    }
  }
  
  public final Buffer lookup(String paramString)
  {
    for (;;)
    {
      try
      {
        localObject = (Buffer)zzde.get(paramString);
        if (localObject != null) {
          return localObject;
        }
        localObject = zzdd;
        localContext = mcontext;
      }
      catch (Throwable paramString)
      {
        Object localObject;
        Context localContext;
        throw paramString;
      }
      try
      {
        localObject = ((Cache)localObject).lookup(localContext, paramString);
      }
      catch (zzaa localZzaa) {}
    }
    Log.w("FirebaseInstanceId", "Stored data is corrupt, generating new identity");
    FirebaseInstanceId.getInstance().getFullPath();
    localObject = zzdd.get(mcontext, paramString);
    zzde.put(paramString, localObject);
    return localObject;
  }
  
  public final void setCurrentTheme(String paramString)
  {
    try
    {
      zzdc.edit().putString("topic_operaion_queue", paramString).apply();
      return;
    }
    catch (Throwable paramString)
    {
      throw paramString;
    }
  }
  
  public final void update(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    try
    {
      paramString4 = zzax.save(paramString4, paramString5, System.currentTimeMillis());
      if (paramString4 == null) {
        return;
      }
      paramString5 = zzdc.edit();
      paramString5.putString(serialize(paramString1, paramString2, paramString3), paramString4);
      paramString5.commit();
      return;
    }
    catch (Throwable paramString1)
    {
      throw paramString1;
    }
  }
  
  public final void write(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      paramString1 = serialize(paramString1, paramString2, paramString3);
      paramString2 = zzdc.edit();
      paramString2.remove(paramString1);
      paramString2.commit();
      return;
    }
    catch (Throwable paramString1)
    {
      throw paramString1;
    }
  }
  
  public final String zzak()
  {
    try
    {
      String str = zzdc.getString("topic_operaion_queue", "");
      return str;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public final void zzal()
  {
    try
    {
      zzde.clear();
      Cache.initialize(mcontext);
      zzdc.edit().clear().commit();
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
}
