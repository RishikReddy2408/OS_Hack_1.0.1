package com.google.firebase.package_8;

import android.text.TextUtils;
import android.util.Log;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

final class zzax
{
  private static final long zzdf = TimeUnit.DAYS.toMillis(7L);
  private final long timestamp;
  final String zzbq;
  private final String zzdg;
  
  private zzax(String paramString1, String paramString2, long paramLong)
  {
    zzbq = paramString1;
    zzdg = paramString2;
    timestamp = paramLong;
  }
  
  static zzax init(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    if (paramString.startsWith("{")) {
      try
      {
        paramString = new JSONObject(paramString);
        paramString = new zzax(paramString.getString("token"), paramString.getString("appVersion"), paramString.getLong("timestamp"));
        return paramString;
      }
      catch (JSONException paramString)
      {
        paramString = String.valueOf(paramString);
        StringBuilder localStringBuilder = new StringBuilder(String.valueOf(paramString).length() + 23);
        localStringBuilder.append("Failed to parse token: ");
        localStringBuilder.append(paramString);
        Log.w("FirebaseInstanceId", localStringBuilder.toString());
        return null;
      }
    }
    return new zzax(paramString, null, 0L);
  }
  
  static String save(String paramString1, String paramString2, long paramLong)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("token", paramString1);
      localJSONObject.put("appVersion", paramString2);
      localJSONObject.put("timestamp", paramLong);
      paramString1 = localJSONObject.toString();
      return paramString1;
    }
    catch (JSONException paramString1)
    {
      paramString1 = String.valueOf(paramString1);
      paramString2 = new StringBuilder(String.valueOf(paramString1).length() + 24);
      paramString2.append("Failed to encode token: ");
      paramString2.append(paramString1);
      Log.w("FirebaseInstanceId", paramString2.toString());
    }
    return null;
  }
  
  static String sign(zzax paramZzax)
  {
    if (paramZzax == null) {
      return null;
    }
    return zzbq;
  }
  
  final boolean get(String paramString)
  {
    return (System.currentTimeMillis() > timestamp + zzdf) || (!paramString.equals(zzdg));
  }
}
