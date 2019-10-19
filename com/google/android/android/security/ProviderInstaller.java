package com.google.android.android.security;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.os.AsyncTask;
import android.util.Log;
import com.google.android.android.common.GoogleApiAvailabilityLight;
import com.google.android.android.common.GooglePlayServicesNotAvailableException;
import com.google.android.android.common.GooglePlayServicesRepairableException;
import com.google.android.android.common.GooglePlayServicesUtilLight;
import com.google.android.android.common.internal.Preconditions;
import java.lang.reflect.Method;

public class ProviderInstaller
{
  public static final String PROVIDER_NAME = "GmsCore_OpenSSL";
  private static final Object lock = new Object();
  private static final GoogleApiAvailabilityLight zziu = ;
  private static Method zziv = null;
  
  public ProviderInstaller() {}
  
  public static void installIfNeeded(Context paramContext)
    throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException
  {
    Preconditions.checkNotNull(paramContext, "Context must not be null");
    zziu.verifyGooglePlayServicesIsAvailable(paramContext, 11925000);
    try
    {
      paramContext = GooglePlayServicesUtilLight.getRemoteContext(paramContext);
      if (paramContext == null)
      {
        if (Log.isLoggable("ProviderInstaller", 6)) {
          Log.e("ProviderInstaller", "Failed to get remote context");
        }
        throw new GooglePlayServicesNotAvailableException(8);
      }
      Object localObject1 = lock;
      try
      {
        if (zziv == null) {}
        Object localObject2;
        label148:
        label153:
        label175:
        label185:
        label192:
        if (!Log.isLoggable("ProviderInstaller", 6)) {
          break label224;
        }
      }
      catch (Throwable paramContext)
      {
        try
        {
          localObject2 = paramContext.getClassLoader().loadClass("com.google.android.gms.common.security.ProviderInstallerImpl");
          localObject2 = ((Class)localObject2).getMethod("insertProvider", new Class[] { Context.class });
          zziv = (Method)localObject2;
          localObject2 = zziv;
          ((Method)localObject2).invoke(null, new Object[] { paramContext });
          return;
        }
        catch (Exception paramContext)
        {
          localObject2 = paramContext.getCause();
          if (!Log.isLoggable("ProviderInstaller", 6)) {
            break label192;
          }
          if (localObject2 != null) {
            break label148;
          }
          paramContext = paramContext.getMessage();
          break label153;
          paramContext = ((Throwable)localObject2).getMessage();
          paramContext = String.valueOf(paramContext);
          if (paramContext.length() == 0) {
            break label175;
          }
          paramContext = "Failed to install provider: ".concat(paramContext);
          break label185;
          paramContext = new String("Failed to install provider: ");
          Log.e("ProviderInstaller", paramContext);
          throw new GooglePlayServicesNotAvailableException(8);
        }
        paramContext = paramContext;
        throw paramContext;
      }
    }
    catch (Resources.NotFoundException paramContext)
    {
      label224:
      for (;;) {}
    }
    Log.e("ProviderInstaller", "Failed to get remote context - resource not found");
    throw new GooglePlayServicesNotAvailableException(8);
  }
  
  public static void installIfNeededAsync(Context paramContext, ProviderInstallListener paramProviderInstallListener)
  {
    Preconditions.checkNotNull(paramContext, "Context must not be null");
    Preconditions.checkNotNull(paramProviderInstallListener, "Listener must not be null");
    Preconditions.checkMainThread("Must be called on the UI thread");
    new Preferences.5(paramContext, paramProviderInstallListener).execute(new Void[0]);
  }
  
  public abstract interface ProviderInstallListener
  {
    public abstract void onProviderInstallFailed(int paramInt, Intent paramIntent);
    
    public abstract void onProviderInstalled();
  }
}
