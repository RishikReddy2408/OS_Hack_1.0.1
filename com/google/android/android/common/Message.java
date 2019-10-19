package com.google.android.android.common;

import android.util.Log;
import com.google.android.android.common.util.AndroidUtilsLight;
import com.google.android.android.common.util.IpAddress;
import java.security.MessageDigest;
import java.util.concurrent.Callable;
import javax.annotation.CheckReturnValue;

@CheckReturnValue
class Message
{
  private static final Message zzab = new Message(true, null, null);
  private final Throwable cause;
  final boolean zzac;
  private final String zzad;
  
  Message(boolean paramBoolean, String paramString, Throwable paramThrowable)
  {
    zzac = paramBoolean;
    zzad = paramString;
    cause = paramThrowable;
  }
  
  static Message getInstance()
  {
    return zzab;
  }
  
  static Message getMessage(String paramString, Throwable paramThrowable)
  {
    return new Message(false, paramString, paramThrowable);
  }
  
  static Message parse(Callable paramCallable)
  {
    return new Result(paramCallable, null);
  }
  
  static Message toString(String paramString)
  {
    return new Message(false, paramString, null);
  }
  
  static String toString(String paramString, Type paramType, boolean paramBoolean1, boolean paramBoolean2)
  {
    String str;
    if (paramBoolean2) {
      str = "debug cert rejected";
    } else {
      str = "not whitelisted";
    }
    return String.format("%s: pkg=%s, sha1=%s, atk=%s, ver=%s", new Object[] { str, paramString, IpAddress.toString(AndroidUtilsLight.getInstance("SHA-1").digest(paramType.getBytes())), Boolean.valueOf(paramBoolean1), "12451009.false" });
  }
  
  final void delete()
  {
    if ((!zzac) && (Log.isLoggable("GoogleCertificatesRslt", 3)))
    {
      if (cause != null)
      {
        Log.d("GoogleCertificatesRslt", getErrorMessage(), cause);
        return;
      }
      Log.d("GoogleCertificatesRslt", getErrorMessage());
    }
  }
  
  String getErrorMessage()
  {
    return zzad;
  }
}
