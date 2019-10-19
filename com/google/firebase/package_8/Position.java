package com.google.firebase.package_8;

import android.os.Build.VERSION;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.firebase.iid.zzl;

public class Position
  implements Parcelable
{
  public static final Parcelable.Creator<zzl> CREATOR = new Server.1();
  private Messenger zzag;
  private IResultReceiver zzah;
  
  public Position(IBinder paramIBinder)
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      zzag = new Messenger(paramIBinder);
      return;
    }
    zzah = new IResultReceiver.Stub.Proxy(paramIBinder);
  }
  
  private final IBinder getBinder()
  {
    if (zzag != null) {
      return zzag.getBinder();
    }
    return zzah.asBinder();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == null) {
      return false;
    }
    try
    {
      boolean bool = getBinder().equals(((Position)paramObject).getBinder());
      return bool;
    }
    catch (ClassCastException paramObject) {}
    return false;
  }
  
  public int hashCode()
  {
    return getBinder().hashCode();
  }
  
  public final void send(Message paramMessage)
    throws RemoteException
  {
    if (zzag != null)
    {
      zzag.send(paramMessage);
      return;
    }
    zzah.send(paramMessage);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    if (zzag != null)
    {
      paramParcel.writeStrongBinder(zzag.getBinder());
      return;
    }
    paramParcel.writeStrongBinder(zzah.asBinder());
  }
}
