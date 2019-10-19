package com.google.android.android.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import com.google.android.android.common.stats.ConnectionTracker;
import com.google.android.android.internal.common.Timer;
import com.google.android.gms.common.internal.zzf;
import java.util.HashMap;
import javax.annotation.concurrent.GuardedBy;

final class MainActivity
  extends GmsClientSupervisor
  implements Handler.Callback
{
  private final Handler mHandler;
  @GuardedBy("mConnectionStatus")
  private final HashMap<com.google.android.gms.common.internal.GmsClientSupervisor.zza, zzf> zzdu = new HashMap();
  private final Context zzdv;
  private final ConnectionTracker zzdw;
  private final long zzdx;
  private final long zzdy;
  
  MainActivity(Context paramContext)
  {
    zzdv = paramContext.getApplicationContext();
    mHandler = new Timer(paramContext.getMainLooper(), this);
    zzdw = ConnectionTracker.getInstance();
    zzdx = 5000L;
    zzdy = 300000L;
  }
  
  public final boolean handleMessage(Message paramMessage)
  {
    Object localObject2;
    switch (what)
    {
    default: 
      return false;
    case 1: 
      localObject2 = zzdu;
      try
      {
        GmsClientSupervisor.zza localZza = (GmsClientSupervisor.zza)obj;
        BaseActivity localBaseActivity = (BaseActivity)zzdu.get(localZza);
        if ((localBaseActivity != null) && (localBaseActivity.getState() == 3))
        {
          paramMessage = String.valueOf(localZza);
          localObject1 = new StringBuilder(String.valueOf(paramMessage).length() + 47);
          ((StringBuilder)localObject1).append("Timeout waiting for ServiceConnection callback ");
          ((StringBuilder)localObject1).append(paramMessage);
          Log.wtf("GmsClientSupervisor", ((StringBuilder)localObject1).toString(), new Exception());
          localObject1 = localBaseActivity.getComponentName();
          paramMessage = (Message)localObject1;
          if (localObject1 == null) {
            paramMessage = localZza.getComponentName();
          }
          localObject1 = paramMessage;
          if (paramMessage == null) {
            localObject1 = new ComponentName(localZza.getPackage(), "unknown");
          }
          localBaseActivity.onServiceDisconnected((ComponentName)localObject1);
        }
        return true;
      }
      catch (Throwable paramMessage)
      {
        throw paramMessage;
      }
    }
    Object localObject1 = zzdu;
    try
    {
      paramMessage = (GmsClientSupervisor.zza)obj;
      localObject2 = (BaseActivity)zzdu.get(paramMessage);
      if ((localObject2 != null) && (((BaseActivity)localObject2).isEmpty()))
      {
        if (((BaseActivity)localObject2).isBound()) {
          ((BaseActivity)localObject2).onServiceConnected("GmsClientSupervisor");
        }
        zzdu.remove(paramMessage);
      }
      return true;
    }
    catch (Throwable paramMessage)
    {
      throw paramMessage;
    }
  }
  
  protected final void init(GmsClientSupervisor.zza paramZza, ServiceConnection paramServiceConnection, String paramString)
  {
    Preconditions.checkNotNull(paramServiceConnection, "ServiceConnection must not be null");
    HashMap localHashMap = zzdu;
    try
    {
      BaseActivity localBaseActivity = (BaseActivity)zzdu.get(paramZza);
      if (localBaseActivity != null)
      {
        if (localBaseActivity.bind(paramServiceConnection))
        {
          localBaseActivity.bind(paramServiceConnection, paramString);
          if (localBaseActivity.isEmpty())
          {
            paramZza = mHandler.obtainMessage(0, paramZza);
            mHandler.sendMessageDelayed(paramZza, zzdx);
          }
          return;
        }
        paramZza = String.valueOf(paramZza);
        paramServiceConnection = new StringBuilder(String.valueOf(paramZza).length() + 76);
        paramServiceConnection.append("Trying to unbind a GmsServiceConnection  that was not bound before.  config=");
        paramServiceConnection.append(paramZza);
        throw new IllegalStateException(paramServiceConnection.toString());
      }
      paramZza = String.valueOf(paramZza);
      paramServiceConnection = new StringBuilder(String.valueOf(paramZza).length() + 50);
      paramServiceConnection.append("Nonexistent connection status for service config: ");
      paramServiceConnection.append(paramZza);
      throw new IllegalStateException(paramServiceConnection.toString());
    }
    catch (Throwable paramZza)
    {
      throw paramZza;
    }
  }
  
  protected final boolean start(GmsClientSupervisor.zza paramZza, ServiceConnection paramServiceConnection, String paramString)
  {
    Preconditions.checkNotNull(paramServiceConnection, "ServiceConnection must not be null");
    HashMap localHashMap = zzdu;
    for (;;)
    {
      BaseActivity localBaseActivity;
      try
      {
        localBaseActivity = (BaseActivity)zzdu.get(paramZza);
        if (localBaseActivity == null)
        {
          localBaseActivity = new BaseActivity(this, paramZza);
          localBaseActivity.onStart(paramServiceConnection, paramString);
          localBaseActivity.onStart(paramString);
          zzdu.put(paramZza, localBaseActivity);
          paramZza = localBaseActivity;
        }
        else
        {
          mHandler.removeMessages(0, paramZza);
          if (localBaseActivity.bind(paramServiceConnection)) {
            continue;
          }
          localBaseActivity.onStart(paramServiceConnection, paramString);
        }
        switch (localBaseActivity.getState())
        {
        case 2: 
          localBaseActivity.onStart(paramString);
          paramZza = localBaseActivity;
          break;
        case 1: 
          paramServiceConnection.onServiceConnected(localBaseActivity.getComponentName(), localBaseActivity.getBinder());
          paramZza = localBaseActivity;
          boolean bool = paramZza.isBound();
          return bool;
          paramZza = String.valueOf(paramZza);
          paramServiceConnection = new StringBuilder(String.valueOf(paramZza).length() + 81);
          paramServiceConnection.append("Trying to bind a GmsServiceConnection that was already connected before.  config=");
          paramServiceConnection.append(paramZza);
          throw new IllegalStateException(paramServiceConnection.toString());
        }
      }
      catch (Throwable paramZza)
      {
        throw paramZza;
      }
      paramZza = localBaseActivity;
    }
  }
}
