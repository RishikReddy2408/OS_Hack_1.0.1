package com.google.android.android.common.aimsicd.internal;

import com.google.android.android.common.Feature;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.common.util.BiConsumer;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.tasks.TaskCompletionSource;

@KeepForSdk
public class RegistrationMethods<A extends Api.AnyClient, L>
{
  public final com.google.android.gms.common.api.internal.RegisterListenerMethod<A, L> zajy;
  public final com.google.android.gms.common.api.internal.UnregisterListenerMethod<A, L> zajz;
  
  private RegistrationMethods(RegisterListenerMethod paramRegisterListenerMethod, UnregisterListenerMethod paramUnregisterListenerMethod)
  {
    zajy = paramRegisterListenerMethod;
    zajz = paramUnregisterListenerMethod;
  }
  
  public static Builder builder()
  {
    return new Builder(null);
  }
  
  @KeepForSdk
  public class Builder<A extends Api.AnyClient, L>
  {
    private boolean zajv = true;
    private com.google.android.gms.common.api.internal.RemoteCall<A, TaskCompletionSource<Void>> zaka;
    private com.google.android.gms.common.api.internal.RemoteCall<A, TaskCompletionSource<Boolean>> zakb;
    private com.google.android.gms.common.api.internal.ListenerHolder<L> zakc;
    private Feature[] zakd;
    
    private Builder() {}
    
    public RegistrationMethods build()
    {
      RemoteCall localRemoteCall = zaka;
      boolean bool2 = false;
      if (localRemoteCall != null) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      Preconditions.checkArgument(bool1, "Must set register function");
      if (zakb != null) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      Preconditions.checkArgument(bool1, "Must set unregister function");
      boolean bool1 = bool2;
      if (zakc != null) {
        bool1 = true;
      }
      Preconditions.checkArgument(bool1, "Must set holder");
      return new RegistrationMethods(new zaca(this, zakc, zakd, zajv), new zacb(this, zakc.getListenerKey()), null);
    }
    
    public Builder register(RemoteCall paramRemoteCall)
    {
      zaka = paramRemoteCall;
      return this;
    }
    
    public Builder register(BiConsumer paramBiConsumer)
    {
      zaka = new zaby(paramBiConsumer);
      return this;
    }
    
    public Builder setAutoResolveMissingFeatures(boolean paramBoolean)
    {
      zajv = paramBoolean;
      return this;
    }
    
    public Builder setFeatures(Feature[] paramArrayOfFeature)
    {
      zakd = paramArrayOfFeature;
      return this;
    }
    
    public Builder unregister(RemoteCall paramRemoteCall)
    {
      zakb = paramRemoteCall;
      return this;
    }
    
    public Builder unregister(BiConsumer paramBiConsumer)
    {
      zaka = new zabz(this);
      return this;
    }
    
    public Builder withHolder(ListenerHolder paramListenerHolder)
    {
      zakc = paramListenerHolder;
      return this;
    }
  }
}
