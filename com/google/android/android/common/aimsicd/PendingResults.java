package com.google.android.android.common.aimsicd;

import android.os.Looper;
import com.google.android.android.common.aimsicd.internal.OptionalPendingResultImpl;
import com.google.android.android.common.aimsicd.internal.StatusPendingResult;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class PendingResults
{
  private PendingResults() {}
  
  public static PendingResult canceledPendingResult()
  {
    StatusPendingResult localStatusPendingResult = new StatusPendingResult(Looper.getMainLooper());
    localStatusPendingResult.cancel();
    return localStatusPendingResult;
  }
  
  public static PendingResult canceledPendingResult(Result paramResult)
  {
    Preconditions.checkNotNull(paramResult, "Result must not be null");
    boolean bool;
    if (paramResult.getStatus().getStatusCode() == 16) {
      bool = true;
    } else {
      bool = false;
    }
    Preconditions.checkArgument(bool, "Status code must be CommonStatusCodes.CANCELED");
    paramResult = new zaa();
    paramResult.cancel();
    return paramResult;
  }
  
  public static PendingResult immediateFailedResult(Result paramResult, GoogleApiClient paramGoogleApiClient)
  {
    Preconditions.checkNotNull(paramResult, "Result must not be null");
    Preconditions.checkArgument(paramResult.getStatus().isSuccess() ^ true, "Status code must not be SUCCESS");
    paramGoogleApiClient = new zab(paramGoogleApiClient, paramResult);
    paramGoogleApiClient.setResult(paramResult);
    return paramGoogleApiClient;
  }
  
  public static OptionalPendingResult immediatePendingResult(Result paramResult)
  {
    Preconditions.checkNotNull(paramResult, "Result must not be null");
    zac localZac = new zac(null);
    localZac.setResult(paramResult);
    return new OptionalPendingResultImpl(localZac);
  }
  
  public static OptionalPendingResult immediatePendingResult(Result paramResult, GoogleApiClient paramGoogleApiClient)
  {
    Preconditions.checkNotNull(paramResult, "Result must not be null");
    paramGoogleApiClient = new zac(paramGoogleApiClient);
    paramGoogleApiClient.setResult(paramResult);
    return new OptionalPendingResultImpl(paramGoogleApiClient);
  }
  
  public static PendingResult immediatePendingResult(Status paramStatus)
  {
    Preconditions.checkNotNull(paramStatus, "Result must not be null");
    StatusPendingResult localStatusPendingResult = new StatusPendingResult(Looper.getMainLooper());
    localStatusPendingResult.setResult(paramStatus);
    return localStatusPendingResult;
  }
  
  public static PendingResult immediatePendingResult(Status paramStatus, GoogleApiClient paramGoogleApiClient)
  {
    Preconditions.checkNotNull(paramStatus, "Result must not be null");
    paramGoogleApiClient = new StatusPendingResult(paramGoogleApiClient);
    paramGoogleApiClient.setResult(paramStatus);
    return paramGoogleApiClient;
  }
  
  final class zaa<R extends com.google.android.gms.common.api.Result>
    extends com.google.android.gms.common.api.internal.BasePendingResult<R>
  {
    public zaa()
    {
      super();
    }
    
    protected final Result createFailedResult(Status paramStatus)
    {
      if (paramStatus.getStatusCode() == getStatus().getStatusCode()) {
        return PendingResults.this;
      }
      throw new UnsupportedOperationException("Creating failed results is not supported");
    }
  }
  
  final class zab<R extends com.google.android.gms.common.api.Result>
    extends com.google.android.gms.common.api.internal.BasePendingResult<R>
  {
    private final R zaci;
    
    public zab(Result paramResult)
    {
      super();
      zaci = paramResult;
    }
    
    protected final Result createFailedResult(Status paramStatus)
    {
      return zaci;
    }
  }
  
  final class zac<R extends com.google.android.gms.common.api.Result>
    extends com.google.android.gms.common.api.internal.BasePendingResult<R>
  {
    public zac()
    {
      super();
    }
    
    protected final Result createFailedResult(Status paramStatus)
    {
      throw new UnsupportedOperationException("Creating failed results is not supported");
    }
  }
}
