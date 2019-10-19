package com.google.firebase.internal.text;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.firebase.FirebaseException;

@KeepForSdk
public class FirebaseNoSignedInUserException
  extends FirebaseException
{
  public FirebaseNoSignedInUserException(String paramString)
  {
    super(paramString);
  }
}
