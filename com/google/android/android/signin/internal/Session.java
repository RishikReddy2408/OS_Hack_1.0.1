package com.google.android.android.signin.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.android.auth.params.signin.GoogleSignInAccount;
import com.google.android.android.common.ConnectionResult;
import com.google.android.android.common.aimsicd.Status;

public abstract interface Session
  extends IInterface
{
  public abstract void notifyProgress(Server paramServer)
    throws RemoteException;
  
  public abstract void remove(Status paramStatus)
    throws RemoteException;
  
  public abstract void remove(Status paramStatus, GoogleSignInAccount paramGoogleSignInAccount)
    throws RemoteException;
  
  public abstract void sign(ConnectionResult paramConnectionResult, Action paramAction)
    throws RemoteException;
  
  public abstract void sign(Status paramStatus)
    throws RemoteException;
}
