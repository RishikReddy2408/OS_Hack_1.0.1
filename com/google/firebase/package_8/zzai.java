package com.google.firebase.package_8;

import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

final class zzai
{
  private final Messenger zzag;
  private final Position zzce;
  
  zzai(IBinder paramIBinder)
    throws RemoteException
  {
    String str = paramIBinder.getInterfaceDescriptor();
    if ("android.os.IMessenger".equals(str))
    {
      zzag = new Messenger(paramIBinder);
      zzce = null;
      return;
    }
    if ("com.google.android.gms.iid.IMessengerCompat".equals(str))
    {
      zzce = new Position(paramIBinder);
      zzag = null;
      return;
    }
    paramIBinder = String.valueOf(str);
    if (paramIBinder.length() != 0) {
      paramIBinder = "Invalid interface descriptor: ".concat(paramIBinder);
    } else {
      paramIBinder = new String("Invalid interface descriptor: ");
    }
    Log.w("MessengerIpcClient", paramIBinder);
    throw new RemoteException();
  }
  
  final void send(Message paramMessage)
    throws RemoteException
  {
    if (zzag != null)
    {
      zzag.send(paramMessage);
      return;
    }
    if (zzce != null)
    {
      zzce.send(paramMessage);
      return;
    }
    throw new IllegalStateException("Both messengers are null");
  }
}
