package com.google.firebase.components;

import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.List;

@KeepForSdk
public abstract interface ComponentRegistrar
{
  public abstract List getComponents();
}
