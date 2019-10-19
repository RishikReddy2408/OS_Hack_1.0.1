package com.google.android.android.common.aimsicd;

public class Response<T extends com.google.android.gms.common.api.Result>
{
  private T zzao;
  
  public Response() {}
  
  protected Response(Result paramResult)
  {
    zzao = paramResult;
  }
  
  protected Result getResult()
  {
    return zzao;
  }
  
  public void setResult(Result paramResult)
  {
    zzao = paramResult;
  }
}
