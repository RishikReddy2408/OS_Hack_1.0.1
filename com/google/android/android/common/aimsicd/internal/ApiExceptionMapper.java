package com.google.android.android.common.aimsicd.internal;

import com.google.android.android.common.aimsicd.Status;
import com.google.android.android.common.internal.ApiExceptionUtil;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class ApiExceptionMapper
  implements StatusExceptionMapper
{
  public ApiExceptionMapper() {}
  
  public Exception getException(Status paramStatus)
  {
    return ApiExceptionUtil.fromStatus(paramStatus);
  }
}
