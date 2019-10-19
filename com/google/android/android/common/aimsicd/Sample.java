package com.google.android.android.common.aimsicd;

import com.google.android.android.common.internal.Preconditions;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.zaa;
import com.google.android.gms.common.api.Api.zab;

public final class Sample<O extends Api.ApiOptions>
{
  private final String mName;
  private final com.google.android.gms.common.api.Api.AbstractClientBuilder<?, O> zaau;
  private final Api.zaa<?, O> zaav;
  private final com.google.android.gms.common.api.Api.ClientKey<?> zaaw;
  private final Api.zab<?> zaax;
  
  public Sample(String paramString, Api.AbstractClientBuilder paramAbstractClientBuilder, Api.ClientKey paramClientKey)
  {
    Preconditions.checkNotNull(paramAbstractClientBuilder, "Cannot construct an Api with a null ClientBuilder");
    Preconditions.checkNotNull(paramClientKey, "Cannot construct an Api with a null ClientKey");
    mName = paramString;
    zaau = paramAbstractClientBuilder;
    zaav = null;
    zaaw = paramClientKey;
    zaax = null;
  }
  
  public final Api.AnyClientKey getClientKey()
  {
    if (zaaw != null) {
      return zaaw;
    }
    throw new IllegalStateException("This API was constructed with null client keys. This should not be possible.");
  }
  
  public final String getName()
  {
    return mName;
  }
  
  public final Api.BaseClientBuilder getValue()
  {
    return zaau;
  }
  
  public final Api.AbstractClientBuilder start()
  {
    boolean bool;
    if (zaau != null) {
      bool = true;
    } else {
      bool = false;
    }
    Preconditions.checkState(bool, "This API was constructed with a SimpleClientBuilder. Use getSimpleClientBuilder");
    return zaau;
  }
}
