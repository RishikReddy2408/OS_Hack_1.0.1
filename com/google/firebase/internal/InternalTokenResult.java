package com.google.firebase.internal;

import com.google.android.android.common.internal.Objects;
import com.google.android.android.common.internal.Objects.ToStringHelper;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class InternalTokenResult
{
  private String type;
  
  public InternalTokenResult(String paramString)
  {
    type = paramString;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof InternalTokenResult)) {
      return false;
    }
    paramObject = (InternalTokenResult)paramObject;
    return Objects.equal(type, type);
  }
  
  public String getToken()
  {
    return type;
  }
  
  public int hashCode()
  {
    return Objects.hashCode(new Object[] { type });
  }
  
  public String toString()
  {
    return Objects.toStringHelper(this).add("token", type).toString();
  }
}
