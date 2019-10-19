package com.google.firebase;

import com.google.android.android.common.aimsicd.Status;
import com.google.android.android.common.aimsicd.internal.StatusExceptionMapper;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class FirebaseExceptionMapper
  implements StatusExceptionMapper
{
  public FirebaseExceptionMapper() {}
  
  public Exception getException(Status paramStatus)
  {
    if (paramStatus.getStatusCode() == 8) {
      return new FirebaseException(paramStatus.getMessage());
    }
    return new FirebaseApiNotAvailableException(paramStatus.getMessage());
  }
}
