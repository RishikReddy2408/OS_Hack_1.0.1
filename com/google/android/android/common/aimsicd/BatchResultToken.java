package com.google.android.android.common.aimsicd;

import com.google.android.gms.common.api.Result;

public final class BatchResultToken<R extends Result>
{
  protected final int _capacity;
  
  BatchResultToken(int paramInt)
  {
    _capacity = paramInt;
  }
}
