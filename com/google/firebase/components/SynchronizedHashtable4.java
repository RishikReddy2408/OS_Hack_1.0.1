package com.google.firebase.components;

import com.google.firebase.inject.Provider;

abstract class SynchronizedHashtable4
  implements ComponentContainer
{
  SynchronizedHashtable4() {}
  
  public Object get(Class paramClass)
  {
    paramClass = getProvider(paramClass);
    if (paramClass == null) {
      return null;
    }
    return paramClass.get();
  }
}
