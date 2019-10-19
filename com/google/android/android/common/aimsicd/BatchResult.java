package com.google.android.android.common.aimsicd;

import com.google.android.android.common.internal.Preconditions;
import java.util.concurrent.TimeUnit;

public final class BatchResult
  implements Result
{
  private final Status mStatus;
  private final com.google.android.gms.common.api.PendingResult<?>[] zabc;
  
  BatchResult(Status paramStatus, PendingResult[] paramArrayOfPendingResult)
  {
    mStatus = paramStatus;
    zabc = paramArrayOfPendingResult;
  }
  
  public final Status getStatus()
  {
    return mStatus;
  }
  
  public final Result take(BatchResultToken paramBatchResultToken)
  {
    boolean bool;
    if (_capacity < zabc.length) {
      bool = true;
    } else {
      bool = false;
    }
    Preconditions.checkArgument(bool, "The result token does not belong to this batch");
    return zabc[_capacity].await(0L, TimeUnit.MILLISECONDS);
  }
}
