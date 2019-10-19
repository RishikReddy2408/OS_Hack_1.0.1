package com.google.android.android.common.aimsicd.internal;

import com.google.android.android.common.aimsicd.Releasable;
import com.google.android.android.common.aimsicd.Result;
import com.google.android.android.common.aimsicd.Status;
import com.google.android.android.common.data.DataHolder;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class DataHolderResult
  implements Releasable, Result
{
  @KeepForSdk
  protected final DataHolder mDataHolder;
  @KeepForSdk
  protected final Status mStatus;
  
  protected DataHolderResult(DataHolder paramDataHolder)
  {
    this(paramDataHolder, new Status(paramDataHolder.getStatusCode()));
  }
  
  protected DataHolderResult(DataHolder paramDataHolder, Status paramStatus)
  {
    mStatus = paramStatus;
    mDataHolder = paramDataHolder;
  }
  
  public Status getStatus()
  {
    return mStatus;
  }
  
  public void release()
  {
    if (mDataHolder != null) {
      mDataHolder.close();
    }
  }
}
