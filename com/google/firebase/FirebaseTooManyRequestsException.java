package com.google.firebase;

import com.google.firebase.annotations.PublicApi;

@PublicApi
public class FirebaseTooManyRequestsException
  extends FirebaseException
{
  public FirebaseTooManyRequestsException(String paramString)
  {
    super(paramString);
  }
}
