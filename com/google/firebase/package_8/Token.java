package com.google.firebase.package_8;

final class Token
  implements InstanceIdResult
{
  private final String zzbp;
  private final String zzbq;
  
  Token(String paramString1, String paramString2)
  {
    zzbp = paramString1;
    zzbq = paramString2;
  }
  
  public final String getId()
  {
    return zzbp;
  }
  
  public final String getToken()
  {
    return zzbq;
  }
}
