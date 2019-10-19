package com.google.android.android.common.aimsicd.internal;

import com.google.android.android.common.aimsicd.Sample;
import com.google.android.android.common.internal.Objects;
import com.google.android.gms.common.api.Api;

public final class Msg<O extends com.google.android.gms.common.api.Api.ApiOptions>
{
  private final Api<O> mApi;
  private final O zabh;
  private final boolean zact;
  private final int zacu;
  
  private Msg(Sample paramSample)
  {
    zact = true;
    mApi = paramSample;
    zabh = null;
    zacu = System.identityHashCode(this);
  }
  
  private Msg(Sample paramSample, com.google.android.android.common.aimsicd.Api.ApiOptions paramApiOptions)
  {
    zact = false;
    mApi = paramSample;
    zabh = paramApiOptions;
    zacu = Objects.hashCode(new Object[] { mApi, zabh });
  }
  
  public static Msg readMessage(Sample paramSample)
  {
    return new Msg(paramSample);
  }
  
  public static Msg readMessage(Sample paramSample, com.google.android.android.common.aimsicd.Api.ApiOptions paramApiOptions)
  {
    return new Msg(paramSample, paramApiOptions);
  }
  
  public final boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof Msg)) {
      return false;
    }
    paramObject = (Msg)paramObject;
    return (!zact) && (!zact) && (Objects.equal(mApi, mApi)) && (Objects.equal(zabh, zabh));
  }
  
  public final String get()
  {
    return mApi.getName();
  }
  
  public final int hashCode()
  {
    return zacu;
  }
}
