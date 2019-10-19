package com.google.android.android.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.android.dynamic.IObjectWrapper;
import com.google.android.android.dynamic.IObjectWrapper.Stub;
import com.google.android.android.internal.base.Converter;
import com.google.android.android.internal.base.Os;

public final class RealmObject
  extends Converter
  implements ISignInButtonCreator
{
  RealmObject(IBinder paramIBinder)
  {
    super(paramIBinder, "com.google.android.gms.common.internal.ISignInButtonCreator");
  }
  
  public final IObjectWrapper newSignInButton(IObjectWrapper paramIObjectWrapper, int paramInt1, int paramInt2)
    throws RemoteException
  {
    Object localObject = get();
    Os.rename((Parcel)localObject, paramIObjectWrapper);
    ((Parcel)localObject).writeInt(paramInt1);
    ((Parcel)localObject).writeInt(paramInt2);
    paramIObjectWrapper = reset(1, (Parcel)localObject);
    localObject = IObjectWrapper.Stub.asInterface(paramIObjectWrapper.readStrongBinder());
    paramIObjectWrapper.recycle();
    return localObject;
  }
  
  public final IObjectWrapper newSignInButtonFromConfig(IObjectWrapper paramIObjectWrapper, SignInButtonConfig paramSignInButtonConfig)
    throws RemoteException
  {
    Parcel localParcel = get();
    Os.rename(localParcel, paramIObjectWrapper);
    Os.writeValue(localParcel, paramSignInButtonConfig);
    paramIObjectWrapper = reset(2, localParcel);
    paramSignInButtonConfig = IObjectWrapper.Stub.asInterface(paramIObjectWrapper.readStrongBinder());
    paramIObjectWrapper.recycle();
    return paramSignInButtonConfig;
  }
}
