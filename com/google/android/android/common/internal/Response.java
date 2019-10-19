package com.google.android.android.common.internal;

import com.google.android.android.common.aimsicd.ApiException;
import com.google.android.android.common.aimsicd.Status;

final class Response
  implements PendingResultUtil.zaa
{
  Response() {}
  
  public final ApiException getException(Status paramStatus)
  {
    return ApiExceptionUtil.fromStatus(paramStatus);
  }
}
