package com.google.android.android.signin.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.android.auth.params.signin.GoogleSignInAccount;
import com.google.android.android.common.ConnectionResult;
import com.google.android.android.common.aimsicd.Status;
import com.google.android.android.internal.base.IExtensionHost.Stub;
import com.google.android.android.internal.base.Os;

public abstract class AbstractSession
  extends IExtensionHost.Stub
  implements Session
{
  public AbstractSession()
  {
    super("com.google.android.gms.signin.internal.ISignInCallbacks");
  }
  
  protected boolean dispatchTransaction(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
    throws RemoteException
  {
    switch (paramInt1)
    {
    default: 
      break;
    case 5: 
      return false;
    case 8: 
      notifyProgress((Server)Os.unmarshall(paramParcel1, Server.CREATOR));
      break;
    case 7: 
      remove((Status)Os.unmarshall(paramParcel1, Status.CREATOR), (GoogleSignInAccount)Os.unmarshall(paramParcel1, GoogleSignInAccount.CREATOR));
      break;
    case 6: 
      sign((Status)Os.unmarshall(paramParcel1, Status.CREATOR));
      break;
    case 4: 
      remove((Status)Os.unmarshall(paramParcel1, Status.CREATOR));
      break;
    }
    sign((ConnectionResult)Os.unmarshall(paramParcel1, ConnectionResult.CREATOR), (Action)Os.unmarshall(paramParcel1, Action.CREATOR));
    paramParcel2.writeNoException();
    return true;
  }
}
