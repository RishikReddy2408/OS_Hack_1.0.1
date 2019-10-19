package com.google.android.android.common.aimsicd.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.android.common.aimsicd.GoogleApiClient;
import com.google.android.android.common.aimsicd.Sample;
import com.google.android.android.common.aimsicd.Status;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.common.internal.SimpleClientAdapter;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;

@KeepForSdk
public class BaseImplementation
{
  public BaseImplementation() {}
  
  @KeepForSdk
  public abstract class ApiMethodImpl<R extends com.google.android.gms.common.api.Result, A extends com.google.android.gms.common.api.Api.AnyClient>
    extends com.google.android.gms.common.api.internal.BasePendingResult<R>
    implements com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder<R>
  {
    @KeepForSdk
    private final Api<?> mApi;
    @KeepForSdk
    private final com.google.android.gms.common.api.Api.AnyClientKey<A> mClientKey;
    
    protected ApiMethodImpl(GoogleApiClient paramGoogleApiClient)
    {
      super();
      mClientKey = ((com.google.android.android.common.aimsicd.Api.AnyClientKey)Preconditions.checkNotNull(this$1));
      mApi = null;
    }
    
    protected ApiMethodImpl(GoogleApiClient paramGoogleApiClient)
    {
      super();
      Preconditions.checkNotNull(this$1, "Api must not be null");
      mClientKey = this$1.getClientKey();
      mApi = this$1;
    }
    
    protected ApiMethodImpl()
    {
      super();
      mClientKey = null;
      mApi = null;
    }
    
    private void setFailedResult(RemoteException paramRemoteException)
    {
      setFailedResult(new Status(8, paramRemoteException.getLocalizedMessage(), null));
    }
    
    protected abstract void doExecute(com.google.android.android.common.aimsicd.Api.AnyClient paramAnyClient)
      throws RemoteException;
    
    public final Sample getApi()
    {
      return mApi;
    }
    
    public final com.google.android.android.common.aimsicd.Api.AnyClientKey getClientKey()
    {
      return mClientKey;
    }
    
    public final void mkdir(com.google.android.android.common.aimsicd.Api.AnyClient paramAnyClient)
      throws DeadObjectException
    {
      Object localObject = paramAnyClient;
      if ((paramAnyClient instanceof SimpleClientAdapter)) {
        localObject = ((SimpleClientAdapter)paramAnyClient).getClient();
      }
      try
      {
        doExecute((com.google.android.android.common.aimsicd.Api.AnyClient)localObject);
        return;
      }
      catch (RemoteException paramAnyClient)
      {
        setFailedResult(paramAnyClient);
        return;
      }
      catch (DeadObjectException paramAnyClient)
      {
        setFailedResult(paramAnyClient);
        throw paramAnyClient;
      }
    }
    
    protected void onSetFailedResult(com.google.android.android.common.aimsicd.Result paramResult) {}
    
    public final void setFailedResult(Status paramStatus)
    {
      Preconditions.checkArgument(paramStatus.isSuccess() ^ true, "Failed result must not be success");
      paramStatus = createFailedResult(paramStatus);
      setResult(paramStatus);
      onSetFailedResult(paramStatus);
    }
  }
  
  @KeepForSdk
  public abstract interface ResultHolder<R>
  {
    public abstract void setFailedResult(Status paramStatus);
    
    public abstract void setResult(Object paramObject);
  }
}
