package com.google.android.gms.common.api;

import android.support.annotation.NonNull;

public abstract class TransformedResult<R extends Result>
{
  public TransformedResult() {}
  
  public abstract void andFinally(@NonNull ResultCallbacks<? super R> paramResultCallbacks);
  
  @NonNull
  public abstract <S extends Result> TransformedResult<S> then(@NonNull ResultTransform<? super R, ? extends S> paramResultTransform);
}
