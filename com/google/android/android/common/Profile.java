package com.google.android.android.common;

import android.content.Context;
import android.os.RemoteException;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.util.Log;
import com.google.android.android.common.internal.IOpenPgpService;
import com.google.android.android.common.internal.IOpenPgpService.Stub;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.dynamic.ObjectWrapper;
import com.google.android.android.dynamite.DynamiteModule;
import com.google.android.android.dynamite.DynamiteModule.LoadingException;
import javax.annotation.CheckReturnValue;

@CheckReturnValue
final class Profile
{
  private static Context context;
  private static final Object id = new Object();
  private static volatile IOpenPgpService mService;
  
  private static Message get(String paramString, Type paramType, boolean paramBoolean)
  {
    Object localObject;
    if (mService == null) {
      localObject = context;
    }
    try
    {
      Preconditions.checkNotNull(localObject);
      localObject = id;
      try
      {
        if (mService == null) {
          mService = IOpenPgpService.Stub.asInterface(DynamiteModule.load(context, DynamiteModule.PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING, "com.google.android.gms.googlecertificates").instantiate("com.google.android.gms.common.GoogleCertificatesImpl"));
        }
      }
      catch (Throwable paramString)
      {
        throw paramString;
      }
      Preconditions.checkNotNull(context);
      localObject = new Command(paramString, paramType, paramBoolean);
      IOpenPgpService localIOpenPgpService = mService;
      Context localContext = context;
      try
      {
        boolean bool = localIOpenPgpService.execute((Command)localObject, ObjectWrapper.wrap(localContext.getPackageManager()));
        if (bool) {
          return Message.getInstance();
        }
        return Message.parse(new ConnectionPool.1(paramBoolean, paramString, paramType));
      }
      catch (RemoteException paramString)
      {
        Log.e("GoogleCertificates", "Failed to get Google certificates from remote", paramString);
        return Message.getMessage("module call", paramString);
      }
      return Message.getMessage(paramString, paramType);
    }
    catch (DynamiteModule.LoadingException paramType)
    {
      Log.e("GoogleCertificates", "Failed to get Google certificates from remote", paramType);
      paramString = String.valueOf(paramType.getMessage());
      if (paramString.length() != 0) {
        paramString = "module init: ".concat(paramString);
      } else {
        paramString = new String("module init: ");
      }
    }
  }
  
  static void init(Context paramContext)
  {
    try
    {
      if (context == null)
      {
        if (paramContext != null) {
          context = paramContext.getApplicationContext();
        }
      }
      else {
        Log.w("GoogleCertificates", "GoogleCertificates has been initialized already");
      }
      return;
    }
    catch (Throwable paramContext)
    {
      throw paramContext;
    }
  }
  
  static Message read(String paramString, Type paramType, boolean paramBoolean)
  {
    StrictMode.ThreadPolicy localThreadPolicy = StrictMode.allowThreadDiskReads();
    try
    {
      paramString = get(paramString, paramType, paramBoolean);
      StrictMode.setThreadPolicy(localThreadPolicy);
      return paramString;
    }
    catch (Throwable paramString)
    {
      StrictMode.setThreadPolicy(localThreadPolicy);
      throw paramString;
    }
  }
}
