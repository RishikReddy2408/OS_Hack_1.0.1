package com.google.firebase.auth;

import com.google.android.android.common.internal.Preconditions;
import com.google.firebase.FirebaseException;
import com.google.firebase.annotations.PublicApi;

@PublicApi
public class FirebaseAuthException
  extends FirebaseException
{
  private final String errorCode;
  
  public FirebaseAuthException(String paramString1, String paramString2)
  {
    super(paramString2);
    errorCode = Preconditions.checkNotEmpty(paramString1);
  }
  
  public String getErrorCode()
  {
    return errorCode;
  }
}
