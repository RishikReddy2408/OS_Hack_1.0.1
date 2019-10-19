package com.google.firebase.components;

import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class MissingDependencyException
  extends DependencyException
{
  public MissingDependencyException(String paramString)
  {
    super(paramString);
  }
}
