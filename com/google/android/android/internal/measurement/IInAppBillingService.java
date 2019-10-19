package com.google.android.android.internal.measurement;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;

public abstract interface IInAppBillingService
  extends IInterface
{
  public abstract Bundle getSkuDetails(Bundle paramBundle)
    throws RemoteException;
}
