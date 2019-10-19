package com.google.firebase.components;

import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class DependencyException
  extends RuntimeException
{
  public DependencyException(String paramString)
  {
    super(paramString);
  }
}
