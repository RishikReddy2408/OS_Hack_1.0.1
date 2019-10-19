package com.google.firebase;

import com.google.android.android.common.internal.Preconditions;

public class FirebaseException
  extends Exception
{
  protected FirebaseException() {}
  
  public FirebaseException(String paramString)
  {
    super(Preconditions.checkNotEmpty(paramString, "Detail message must not be empty"));
  }
  
  public FirebaseException(String paramString, Throwable paramThrowable)
  {
    super(Preconditions.checkNotEmpty(paramString, "Detail message must not be empty"), paramThrowable);
  }
}
