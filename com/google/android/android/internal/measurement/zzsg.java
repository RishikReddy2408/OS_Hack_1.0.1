package com.google.android.android.internal.measurement;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class zzsg
{
  private static final Uri CONTENT_URI = Uri.parse("content://com.google.android.gsf.gservices");
  private static final Uri zzbqd = Uri.parse("content://com.google.android.gsf.gservices/prefix");
  public static final Pattern zzbqe = Pattern.compile("^(1|true|t|on|yes|y)$", 2);
  public static final Pattern zzbqf = Pattern.compile("^(0|false|f|off|no|n)$", 2);
  private static final AtomicBoolean zzbqg = new AtomicBoolean();
  private static HashMap<String, String> zzbqh;
  private static final HashMap<String, Boolean> zzbqi = new HashMap();
  private static final HashMap<String, Integer> zzbqj = new HashMap();
  private static final HashMap<String, Long> zzbqk = new HashMap();
  private static final HashMap<String, Float> zzbql = new HashMap();
  private static Object zzbqm;
  private static boolean zzbqn;
  private static String[] zzbqo = new String[0];
  
  public zzsg() {}
  
  private static void accept(Object paramObject1, HashMap paramHashMap, String paramString, Object paramObject2)
  {
    try
    {
      if (paramObject1 == zzbqm)
      {
        paramHashMap.put(paramString, paramObject2);
        zzbqh.remove(paramString);
      }
      return;
    }
    catch (Throwable paramObject1)
    {
      throw paramObject1;
    }
  }
  
  private static void debug(Object paramObject, String paramString1, String paramString2)
  {
    try
    {
      if (paramObject == zzbqm) {
        zzbqh.put(paramString1, paramString2);
      }
      return;
    }
    catch (Throwable paramObject)
    {
      throw paramObject;
    }
  }
  
  public static boolean get(ContentResolver paramContentResolver, String paramString, boolean paramBoolean)
  {
    Object localObject = query(paramContentResolver);
    Boolean localBoolean = (Boolean)getObject(zzbqi, paramString, Boolean.valueOf(paramBoolean));
    if (localBoolean != null) {
      return localBoolean.booleanValue();
    }
    String str = match(paramContentResolver, paramString, null);
    paramContentResolver = localBoolean;
    boolean bool = paramBoolean;
    if (str != null) {
      if (str.equals(""))
      {
        paramContentResolver = localBoolean;
        bool = paramBoolean;
      }
      else if (zzbqe.matcher(str).matches())
      {
        paramContentResolver = Boolean.valueOf(true);
        bool = true;
      }
      else if (zzbqf.matcher(str).matches())
      {
        paramContentResolver = Boolean.valueOf(false);
        bool = false;
      }
      else
      {
        paramContentResolver = new StringBuilder("attempt to read gservices key ");
        paramContentResolver.append(paramString);
        paramContentResolver.append(" (value \"");
        paramContentResolver.append(str);
        paramContentResolver.append("\") as boolean");
        Log.w("Gservices", paramContentResolver.toString());
        bool = paramBoolean;
        paramContentResolver = localBoolean;
      }
    }
    accept(localObject, zzbqi, paramString, paramContentResolver);
    return bool;
  }
  
  private static Object getObject(HashMap paramHashMap, String paramString, Object paramObject)
  {
    for (;;)
    {
      try
      {
        if (paramHashMap.containsKey(paramString))
        {
          paramString = paramHashMap.get(paramString);
          paramHashMap = paramString;
          if (paramString != null) {
            return paramHashMap;
          }
        }
        else
        {
          return null;
        }
      }
      catch (Throwable paramHashMap)
      {
        throw paramHashMap;
      }
      paramHashMap = paramObject;
    }
  }
  
  private static Map getTags(ContentResolver paramContentResolver, String... paramVarArgs)
  {
    paramContentResolver = paramContentResolver.query(zzbqd, null, null, paramVarArgs, null);
    paramVarArgs = new TreeMap();
    if (paramContentResolver == null) {
      return paramVarArgs;
    }
    try
    {
      for (;;)
      {
        boolean bool = paramContentResolver.moveToNext();
        if (!bool) {
          break;
        }
        paramVarArgs.put(paramContentResolver.getString(0), paramContentResolver.getString(1));
      }
      paramContentResolver.close();
      return paramVarArgs;
    }
    catch (Throwable paramVarArgs)
    {
      paramContentResolver.close();
      throw paramVarArgs;
    }
  }
  
  public static String match(ContentResolver paramContentResolver, String paramString1, String paramString2)
  {
    for (;;)
    {
      try
      {
        runQuery(paramContentResolver);
        Object localObject = zzbqm;
        if (zzbqh.containsKey(paramString1))
        {
          paramContentResolver = (String)zzbqh.get(paramString1);
          if (paramContentResolver == null) {
            break label319;
          }
          return paramContentResolver;
        }
        paramString2 = zzbqo;
        int j = paramString2.length;
        int i = 0;
        if (i < j)
        {
          if (paramString1.startsWith(paramString2[i]))
          {
            if ((!zzbqn) || (zzbqh.isEmpty()))
            {
              paramString2 = zzbqo;
              zzbqh.putAll(getTags(paramContentResolver, paramString2));
              zzbqn = true;
              if (zzbqh.containsKey(paramString1))
              {
                paramContentResolver = (String)zzbqh.get(paramString1);
                if (paramContentResolver == null) {
                  break label324;
                }
                return paramContentResolver;
              }
            }
            return null;
          }
          i += 1;
          continue;
        }
        Cursor localCursor = paramContentResolver.query(CONTENT_URI, null, null, new String[] { paramString1 }, null);
        if (localCursor == null)
        {
          if (localCursor != null)
          {
            localCursor.close();
            return null;
          }
        }
        else {
          try
          {
            boolean bool = localCursor.moveToFirst();
            if (!bool)
            {
              debug(localObject, paramString1, null);
              if (localCursor != null)
              {
                localCursor.close();
                return null;
              }
            }
            else
            {
              String str = localCursor.getString(1);
              paramString2 = str;
              paramContentResolver = paramString2;
              if (str != null)
              {
                bool = str.equals(null);
                paramContentResolver = paramString2;
                if (bool) {
                  paramContentResolver = null;
                }
              }
              debug(localObject, paramString1, paramContentResolver);
              if (paramContentResolver == null) {
                paramContentResolver = null;
              }
              if (localCursor == null) {
                break label317;
              }
              localCursor.close();
              return paramContentResolver;
            }
          }
          catch (Throwable paramContentResolver)
          {
            if (localCursor != null) {
              localCursor.close();
            }
            throw paramContentResolver;
          }
        }
        return null;
      }
      catch (Throwable paramContentResolver)
      {
        throw paramContentResolver;
      }
      label317:
      return paramContentResolver;
      label319:
      paramContentResolver = null;
      continue;
      label324:
      paramContentResolver = null;
    }
  }
  
  private static Object query(ContentResolver paramContentResolver)
  {
    try
    {
      runQuery(paramContentResolver);
      paramContentResolver = zzbqm;
      return paramContentResolver;
    }
    catch (Throwable paramContentResolver)
    {
      throw paramContentResolver;
    }
  }
  
  private static void runQuery(ContentResolver paramContentResolver)
  {
    if (zzbqh == null)
    {
      zzbqg.set(false);
      zzbqh = new HashMap();
      zzbqm = new Object();
      zzbqn = false;
      paramContentResolver.registerContentObserver(CONTENT_URI, true, new zzsh(null));
      return;
    }
    if (zzbqg.getAndSet(false))
    {
      zzbqh.clear();
      zzbqi.clear();
      zzbqj.clear();
      zzbqk.clear();
      zzbql.clear();
      zzbqm = new Object();
      zzbqn = false;
    }
  }
}
