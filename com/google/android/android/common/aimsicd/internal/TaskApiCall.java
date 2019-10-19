package com.google.android.android.common.aimsicd.internal;

import android.os.RemoteException;
import com.google.android.android.common.Feature;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.common.util.BiConsumer;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public abstract class TaskApiCall<A extends com.google.android.gms.common.api.Api.AnyClient, ResultT>
{
  private final Feature[] zakd;
  private final boolean zakk;
  
  public TaskApiCall()
  {
    zakd = null;
    zakk = false;
  }
  
  private TaskApiCall(Feature[] paramArrayOfFeature, boolean paramBoolean)
  {
    zakd = paramArrayOfFeature;
    zakk = paramBoolean;
  }
  
  public static Builder builder()
  {
    return new Builder(null);
  }
  
  protected abstract void doExecute(com.google.android.android.common.aimsicd.Api.AnyClient paramAnyClient, com.google.android.android.tasks.TaskCompletionSource paramTaskCompletionSource)
    throws RemoteException;
  
  public boolean shouldAutoResolveMissingFeatures()
  {
    return zakk;
  }
  
  public final Feature[] zabt()
  {
    return zakd;
  }
  
  @KeepForSdk
  public class Builder<A extends com.google.android.gms.common.api.Api.AnyClient, ResultT>
  {
    private Feature[] zakd;
    private boolean zakk = true;
    private com.google.android.gms.common.api.internal.RemoteCall<A, com.google.android.gms.tasks.TaskCompletionSource<ResultT>> zakl;
    
    private Builder() {}
    
    public TaskApiCall build()
    {
      boolean bool;
      if (zakl != null) {
        bool = true;
      } else {
        bool = false;
      }
      Preconditions.checkArgument(bool, "execute parameter required");
      return new zack(this, zakd, zakk);
    }
    
    public Builder execute(BiConsumer paramBiConsumer)
    {
      zakl = new zacj(paramBiConsumer);
      return this;
    }
    
    public Builder setAutoResolveMissingFeatures(boolean paramBoolean)
    {
      zakk = paramBoolean;
      return this;
    }
    
    public Builder setFeatures(Feature... paramVarArgs)
    {
      zakd = paramVarArgs;
      return this;
    }
    
    public Builder xor(RemoteCall paramRemoteCall)
    {
      zakl = paramRemoteCall;
      return this;
    }
  }
}
