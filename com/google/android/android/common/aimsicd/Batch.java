package com.google.android.android.common.aimsicd;

import java.util.ArrayList;
import java.util.List;

public final class Batch
  extends com.google.android.gms.common.api.internal.BasePendingResult<com.google.android.gms.common.api.BatchResult>
{
  private final Object mLock = new Object();
  private int zaaz;
  private boolean zaba;
  private boolean zabb;
  private final com.google.android.gms.common.api.PendingResult<?>[] zabc;
  
  private Batch(List paramList, GoogleApiClient paramGoogleApiClient)
  {
    super(paramGoogleApiClient);
    zaaz = paramList.size();
    zabc = new PendingResult[zaaz];
    if (paramList.isEmpty())
    {
      setResult(new BatchResult(Status.RESULT_SUCCESS, zabc));
      return;
    }
    int i = 0;
    while (i < paramList.size())
    {
      paramGoogleApiClient = (PendingResult)paramList.get(i);
      zabc[i] = paramGoogleApiClient;
      paramGoogleApiClient.addStatusListener(new LoginActivity.1(this));
      i += 1;
    }
  }
  
  public final void cancel()
  {
    super.cancel();
    PendingResult[] arrayOfPendingResult = zabc;
    int j = arrayOfPendingResult.length;
    int i = 0;
    while (i < j)
    {
      arrayOfPendingResult[i].cancel();
      i += 1;
    }
  }
  
  public final BatchResult createFailedResult(Status paramStatus)
  {
    return new BatchResult(paramStatus, zabc);
  }
  
  public final class Builder
  {
    private List<com.google.android.gms.common.api.PendingResult<?>> zabe = new ArrayList();
    
    public Builder() {}
    
    public final Batch build()
    {
      return new Batch(zabe, Batch.this, null);
    }
    
    public final BatchResultToken setPlaylist(PendingResult paramPendingResult)
    {
      BatchResultToken localBatchResultToken = new BatchResultToken(zabe.size());
      zabe.add(paramPendingResult);
      return localBatchResultToken;
    }
  }
}
