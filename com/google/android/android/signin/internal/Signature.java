package com.google.android.android.signin.internal;

import android.os.RemoteException;
import com.google.android.android.auth.params.signin.GoogleSignInAccount;
import com.google.android.android.common.ConnectionResult;
import com.google.android.android.common.aimsicd.Status;

public class Signature
  extends AbstractSession
{
  public Signature() {}
  
  public void notifyProgress(Server paramServer)
    throws RemoteException
  {}
  
  public final void remove(Status paramStatus)
    throws RemoteException
  {}
  
  public final void remove(Status paramStatus, GoogleSignInAccount paramGoogleSignInAccount)
    throws RemoteException
  {}
  
  public final void sign(ConnectionResult paramConnectionResult, Action paramAction)
    throws RemoteException
  {}
  
  public final void sign(Status paramStatus)
    throws RemoteException
  {}
}
