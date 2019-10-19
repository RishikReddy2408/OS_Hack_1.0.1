package com.google.android.android.common;

import java.util.concurrent.Callable;

final class Result
  extends Message
{
  private final Callable<String> zzae;
  
  private Result(Callable paramCallable)
  {
    super(false, null, null);
    zzae = paramCallable;
  }
  
  final String getErrorMessage()
  {
    Object localObject = zzae;
    try
    {
      localObject = ((Callable)localObject).call();
      return (String)localObject;
    }
    catch (Exception localException)
    {
      throw new RuntimeException(localException);
    }
  }
}
