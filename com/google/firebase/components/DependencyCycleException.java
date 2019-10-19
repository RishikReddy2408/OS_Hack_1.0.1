package com.google.firebase.components;

import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.List;

@KeepForSdk
public class DependencyCycleException
  extends DependencyException
{
  private final List<Component<?>> accountNames;
  
  public DependencyCycleException(List paramList)
  {
    super(localStringBuilder.toString());
    accountNames = paramList;
  }
  
  public List getComponentsInCycle()
  {
    return accountNames;
  }
}
