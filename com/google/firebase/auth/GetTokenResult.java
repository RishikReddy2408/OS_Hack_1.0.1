package com.google.firebase.auth;

import com.google.firebase.annotations.PublicApi;
import java.util.Map;

@PublicApi
public class GetTokenResult
{
  private String mToken;
  private Map<String, Object> reverseMap;
  
  public GetTokenResult(String paramString, Map paramMap)
  {
    mToken = paramString;
    reverseMap = paramMap;
  }
  
  private long put(String paramString)
  {
    paramString = (Integer)reverseMap.get(paramString);
    if (paramString == null) {
      return 0L;
    }
    return paramString.longValue();
  }
  
  public long getAuthTimestamp()
  {
    return put("auth_time");
  }
  
  public Map getClaims()
  {
    return reverseMap;
  }
  
  public long getExpirationTimestamp()
  {
    return put("exp");
  }
  
  public long getIssuedAtTimestamp()
  {
    return put("iat");
  }
  
  public String getSignInProvider()
  {
    Map localMap = (Map)reverseMap.get("firebase");
    if (localMap != null) {
      return (String)localMap.get("sign_in_provider");
    }
    return null;
  }
  
  public String getToken()
  {
    return mToken;
  }
}
