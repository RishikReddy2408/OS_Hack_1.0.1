package android.support.v4.os;

import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import android.support.annotation.RestrictTo;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class ResultReceiver
  implements Parcelable
{
  public static final Parcelable.Creator<ResultReceiver> CREATOR = new Parcelable.Creator()
  {
    public ResultReceiver createFromParcel(Parcel paramAnonymousParcel)
    {
      return new ResultReceiver(paramAnonymousParcel);
    }
    
    public ResultReceiver[] newArray(int paramAnonymousInt)
    {
      return new ResultReceiver[paramAnonymousInt];
    }
  };
  final Handler mHandler;
  final boolean mLocal;
  IResultReceiver mReceiver;
  
  public ResultReceiver(Handler paramHandler)
  {
    mLocal = true;
    mHandler = paramHandler;
  }
  
  ResultReceiver(Parcel paramParcel)
  {
    mLocal = false;
    mHandler = null;
    mReceiver = IResultReceiver.Stub.asInterface(paramParcel.readStrongBinder());
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  protected void onReceiveResult(int paramInt, Bundle paramBundle) {}
  
  public void send(int paramInt, Bundle paramBundle)
  {
    if (mLocal)
    {
      if (mHandler != null)
      {
        mHandler.post(new MyRunnable(paramInt, paramBundle));
        return;
      }
      onReceiveResult(paramInt, paramBundle);
      return;
    }
    if (mReceiver != null) {}
    try
    {
      mReceiver.send(paramInt, paramBundle);
      return;
    }
    catch (RemoteException paramBundle) {}
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    try
    {
      if (mReceiver == null) {
        mReceiver = new MyResultReceiver();
      }
      paramParcel.writeStrongBinder(mReceiver.asBinder());
      return;
    }
    finally {}
  }
  
  class MyResultReceiver
    extends IResultReceiver.Stub
  {
    MyResultReceiver() {}
    
    public void send(int paramInt, Bundle paramBundle)
    {
      if (mHandler != null)
      {
        mHandler.post(new ResultReceiver.MyRunnable(ResultReceiver.this, paramInt, paramBundle));
        return;
      }
      onReceiveResult(paramInt, paramBundle);
    }
  }
  
  class MyRunnable
    implements Runnable
  {
    final int mResultCode;
    final Bundle mResultData;
    
    MyRunnable(int paramInt, Bundle paramBundle)
    {
      mResultCode = paramInt;
      mResultData = paramBundle;
    }
    
    public void run()
    {
      onReceiveResult(mResultCode, mResultData);
    }
  }
}
