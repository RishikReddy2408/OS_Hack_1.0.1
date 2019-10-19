package com.google.android.android.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.android.internal.common.Context;
import com.google.android.android.internal.common.IExtensionHost.Stub;
import com.google.android.android.internal.common.Log;

public abstract interface IFragmentWrapper
  extends IInterface
{
  public abstract Bundle getArguments()
    throws RemoteException;
  
  public abstract int getId()
    throws RemoteException;
  
  public abstract boolean getRetainInstance()
    throws RemoteException;
  
  public abstract String getTag()
    throws RemoteException;
  
  public abstract int getTargetRequestCode()
    throws RemoteException;
  
  public abstract boolean getUserVisibleHint()
    throws RemoteException;
  
  public abstract boolean isAdded()
    throws RemoteException;
  
  public abstract boolean isDetached()
    throws RemoteException;
  
  public abstract boolean isHidden()
    throws RemoteException;
  
  public abstract boolean isInLayout()
    throws RemoteException;
  
  public abstract boolean isRemoving()
    throws RemoteException;
  
  public abstract boolean isResumed()
    throws RemoteException;
  
  public abstract boolean isVisible()
    throws RemoteException;
  
  public abstract void refreshViews(IObjectWrapper paramIObjectWrapper)
    throws RemoteException;
  
  public abstract void register(IObjectWrapper paramIObjectWrapper)
    throws RemoteException;
  
  public abstract void setHasOptionsMenu(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void setMenuVisibility(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void setRetainInstance(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void setUserVisibleHint(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void startActivity(Intent paramIntent)
    throws RemoteException;
  
  public abstract void startActivityForResult(Intent paramIntent, int paramInt)
    throws RemoteException;
  
  public abstract IObjectWrapper zzad()
    throws RemoteException;
  
  public abstract IFragmentWrapper zzae()
    throws RemoteException;
  
  public abstract IObjectWrapper zzaf()
    throws RemoteException;
  
  public abstract IFragmentWrapper zzag()
    throws RemoteException;
  
  public abstract IObjectWrapper zzah()
    throws RemoteException;
  
  public abstract class Stub
    extends IExtensionHost.Stub
    implements IFragmentWrapper
  {
    public Stub()
    {
      super();
    }
    
    public static IFragmentWrapper asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.dynamic.IFragmentWrapper");
      if ((localIInterface instanceof IFragmentWrapper)) {
        return (IFragmentWrapper)localIInterface;
      }
      return new zza();
    }
    
    protected final boolean execute(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      boolean bool;
      switch (paramInt1)
      {
      default: 
        return false;
      case 27: 
        refreshViews(IObjectWrapper.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        break;
      case 26: 
        startActivityForResult((Intent)Context.get(paramParcel1, Intent.CREATOR), paramParcel1.readInt());
        paramParcel2.writeNoException();
        break;
      case 25: 
        startActivity((Intent)Context.get(paramParcel1, Intent.CREATOR));
        paramParcel2.writeNoException();
        break;
      case 24: 
        setUserVisibleHint(Context.contains(paramParcel1));
        paramParcel2.writeNoException();
        break;
      case 23: 
        setRetainInstance(Context.contains(paramParcel1));
        paramParcel2.writeNoException();
        break;
      case 22: 
        setMenuVisibility(Context.contains(paramParcel1));
        paramParcel2.writeNoException();
        break;
      case 21: 
        setHasOptionsMenu(Context.contains(paramParcel1));
        paramParcel2.writeNoException();
        break;
      case 20: 
        register(IObjectWrapper.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        break;
      case 19: 
        bool = isVisible();
        paramParcel2.writeNoException();
        Context.writeBoolean(paramParcel2, bool);
        break;
      case 18: 
        bool = isResumed();
        paramParcel2.writeNoException();
        Context.writeBoolean(paramParcel2, bool);
        break;
      case 17: 
        bool = isRemoving();
        paramParcel2.writeNoException();
        Context.writeBoolean(paramParcel2, bool);
        break;
      case 16: 
        bool = isInLayout();
        paramParcel2.writeNoException();
        Context.writeBoolean(paramParcel2, bool);
        break;
      case 15: 
        bool = isHidden();
        paramParcel2.writeNoException();
        Context.writeBoolean(paramParcel2, bool);
        break;
      case 14: 
        bool = isDetached();
        paramParcel2.writeNoException();
        Context.writeBoolean(paramParcel2, bool);
        break;
      case 13: 
        bool = isAdded();
        paramParcel2.writeNoException();
        Context.writeBoolean(paramParcel2, bool);
        break;
      case 12: 
        paramParcel1 = zzah();
        paramParcel2.writeNoException();
        Context.get(paramParcel2, paramParcel1);
        break;
      case 11: 
        bool = getUserVisibleHint();
        paramParcel2.writeNoException();
        Context.writeBoolean(paramParcel2, bool);
        break;
      case 10: 
        paramInt1 = getTargetRequestCode();
        paramParcel2.writeNoException();
        paramParcel2.writeInt(paramInt1);
        break;
      case 9: 
        paramParcel1 = zzag();
        paramParcel2.writeNoException();
        Context.get(paramParcel2, paramParcel1);
        break;
      case 8: 
        paramParcel1 = getTag();
        paramParcel2.writeNoException();
        paramParcel2.writeString(paramParcel1);
        break;
      case 7: 
        bool = getRetainInstance();
        paramParcel2.writeNoException();
        Context.writeBoolean(paramParcel2, bool);
        break;
      case 6: 
        paramParcel1 = zzaf();
        paramParcel2.writeNoException();
        Context.get(paramParcel2, paramParcel1);
        break;
      case 5: 
        paramParcel1 = zzae();
        paramParcel2.writeNoException();
        Context.get(paramParcel2, paramParcel1);
        break;
      case 4: 
        paramInt1 = getId();
        paramParcel2.writeNoException();
        paramParcel2.writeInt(paramInt1);
        break;
      case 3: 
        paramParcel1 = getArguments();
        paramParcel2.writeNoException();
        Context.marshall(paramParcel2, paramParcel1);
        break;
      case 2: 
        paramParcel1 = zzad();
        paramParcel2.writeNoException();
        Context.get(paramParcel2, paramParcel1);
      }
      return true;
    }
    
    public final class zza
      extends Log
      implements IFragmentWrapper
    {
      zza()
      {
        super("com.google.android.gms.dynamic.IFragmentWrapper");
      }
      
      public final Bundle getArguments()
        throws RemoteException
      {
        Parcel localParcel = get(3, next());
        Bundle localBundle = (Bundle)Context.get(localParcel, Bundle.CREATOR);
        localParcel.recycle();
        return localBundle;
      }
      
      public final int getId()
        throws RemoteException
      {
        Parcel localParcel = get(4, next());
        int i = localParcel.readInt();
        localParcel.recycle();
        return i;
      }
      
      public final boolean getRetainInstance()
        throws RemoteException
      {
        Parcel localParcel = get(7, next());
        boolean bool = Context.contains(localParcel);
        localParcel.recycle();
        return bool;
      }
      
      public final String getTag()
        throws RemoteException
      {
        Parcel localParcel = get(8, next());
        String str = localParcel.readString();
        localParcel.recycle();
        return str;
      }
      
      public final int getTargetRequestCode()
        throws RemoteException
      {
        Parcel localParcel = get(10, next());
        int i = localParcel.readInt();
        localParcel.recycle();
        return i;
      }
      
      public final boolean getUserVisibleHint()
        throws RemoteException
      {
        Parcel localParcel = get(11, next());
        boolean bool = Context.contains(localParcel);
        localParcel.recycle();
        return bool;
      }
      
      public final boolean isAdded()
        throws RemoteException
      {
        Parcel localParcel = get(13, next());
        boolean bool = Context.contains(localParcel);
        localParcel.recycle();
        return bool;
      }
      
      public final boolean isDetached()
        throws RemoteException
      {
        Parcel localParcel = get(14, next());
        boolean bool = Context.contains(localParcel);
        localParcel.recycle();
        return bool;
      }
      
      public final boolean isHidden()
        throws RemoteException
      {
        Parcel localParcel = get(15, next());
        boolean bool = Context.contains(localParcel);
        localParcel.recycle();
        return bool;
      }
      
      public final boolean isInLayout()
        throws RemoteException
      {
        Parcel localParcel = get(16, next());
        boolean bool = Context.contains(localParcel);
        localParcel.recycle();
        return bool;
      }
      
      public final boolean isRemoving()
        throws RemoteException
      {
        Parcel localParcel = get(17, next());
        boolean bool = Context.contains(localParcel);
        localParcel.recycle();
        return bool;
      }
      
      public final boolean isResumed()
        throws RemoteException
      {
        Parcel localParcel = get(18, next());
        boolean bool = Context.contains(localParcel);
        localParcel.recycle();
        return bool;
      }
      
      public final boolean isVisible()
        throws RemoteException
      {
        Parcel localParcel = get(19, next());
        boolean bool = Context.contains(localParcel);
        localParcel.recycle();
        return bool;
      }
      
      public final void refreshViews(IObjectWrapper paramIObjectWrapper)
        throws RemoteException
      {
        Parcel localParcel = next();
        Context.get(localParcel, paramIObjectWrapper);
        register(27, localParcel);
      }
      
      public final void register(IObjectWrapper paramIObjectWrapper)
        throws RemoteException
      {
        Parcel localParcel = next();
        Context.get(localParcel, paramIObjectWrapper);
        register(20, localParcel);
      }
      
      public final void setHasOptionsMenu(boolean paramBoolean)
        throws RemoteException
      {
        Parcel localParcel = next();
        Context.writeBoolean(localParcel, paramBoolean);
        register(21, localParcel);
      }
      
      public final void setMenuVisibility(boolean paramBoolean)
        throws RemoteException
      {
        Parcel localParcel = next();
        Context.writeBoolean(localParcel, paramBoolean);
        register(22, localParcel);
      }
      
      public final void setRetainInstance(boolean paramBoolean)
        throws RemoteException
      {
        Parcel localParcel = next();
        Context.writeBoolean(localParcel, paramBoolean);
        register(23, localParcel);
      }
      
      public final void setUserVisibleHint(boolean paramBoolean)
        throws RemoteException
      {
        Parcel localParcel = next();
        Context.writeBoolean(localParcel, paramBoolean);
        register(24, localParcel);
      }
      
      public final void startActivity(Intent paramIntent)
        throws RemoteException
      {
        Parcel localParcel = next();
        Context.writeValue(localParcel, paramIntent);
        register(25, localParcel);
      }
      
      public final void startActivityForResult(Intent paramIntent, int paramInt)
        throws RemoteException
      {
        Parcel localParcel = next();
        Context.writeValue(localParcel, paramIntent);
        localParcel.writeInt(paramInt);
        register(26, localParcel);
      }
      
      public final IObjectWrapper zzad()
        throws RemoteException
      {
        Parcel localParcel = get(2, next());
        IObjectWrapper localIObjectWrapper = IObjectWrapper.Stub.asInterface(localParcel.readStrongBinder());
        localParcel.recycle();
        return localIObjectWrapper;
      }
      
      public final IFragmentWrapper zzae()
        throws RemoteException
      {
        Parcel localParcel = get(5, next());
        IFragmentWrapper localIFragmentWrapper = IFragmentWrapper.Stub.asInterface(localParcel.readStrongBinder());
        localParcel.recycle();
        return localIFragmentWrapper;
      }
      
      public final IObjectWrapper zzaf()
        throws RemoteException
      {
        Parcel localParcel = get(6, next());
        IObjectWrapper localIObjectWrapper = IObjectWrapper.Stub.asInterface(localParcel.readStrongBinder());
        localParcel.recycle();
        return localIObjectWrapper;
      }
      
      public final IFragmentWrapper zzag()
        throws RemoteException
      {
        Parcel localParcel = get(9, next());
        IFragmentWrapper localIFragmentWrapper = IFragmentWrapper.Stub.asInterface(localParcel.readStrongBinder());
        localParcel.recycle();
        return localIFragmentWrapper;
      }
      
      public final IObjectWrapper zzah()
        throws RemoteException
      {
        Parcel localParcel = get(12, next());
        IObjectWrapper localIObjectWrapper = IObjectWrapper.Stub.asInterface(localParcel.readStrongBinder());
        localParcel.recycle();
        return localIObjectWrapper;
      }
    }
  }
}
