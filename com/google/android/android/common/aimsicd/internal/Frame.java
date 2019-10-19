package com.google.android.android.common.aimsicd.internal;

import android.os.DeadObjectException;
import com.google.android.android.common.aimsicd.Status;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.zab;

public final class Frame<A extends com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl<? extends Result, Api.AnyClient>>
  extends zab
{
  private final A zacn;
  
  public Frame(int paramInt, BaseImplementation.ApiMethodImpl paramApiMethodImpl)
  {
    super(paramInt);
    zacn = paramApiMethodImpl;
  }
  
  public final void readFrom(GoogleApiManager.zaa paramZaa)
    throws DeadObjectException
  {
    try
    {
      zacn.mkdir(paramZaa.zaab());
      return;
    }
    catch (RuntimeException paramZaa)
    {
      toString(paramZaa);
    }
  }
  
  public final void readFrom(zaab paramZaab, boolean paramBoolean)
  {
    paramZaab.readFrom(zacn, paramBoolean);
  }
  
  public final void toString(Status paramStatus)
  {
    zacn.setFailedResult(paramStatus);
  }
  
  public final void toString(RuntimeException paramRuntimeException)
  {
    String str = paramRuntimeException.getClass().getSimpleName();
    paramRuntimeException = paramRuntimeException.getLocalizedMessage();
    StringBuilder localStringBuilder = new StringBuilder(String.valueOf(str).length() + 2 + String.valueOf(paramRuntimeException).length());
    localStringBuilder.append(str);
    localStringBuilder.append(": ");
    localStringBuilder.append(paramRuntimeException);
    paramRuntimeException = new Status(10, localStringBuilder.toString());
    zacn.setFailedResult(paramRuntimeException);
  }
}
