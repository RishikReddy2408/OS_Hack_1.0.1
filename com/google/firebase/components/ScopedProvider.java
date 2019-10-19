package com.google.firebase.components;

import com.google.firebase.inject.Provider;

final class ScopedProvider<T>
  implements Provider<T>
{
  private static final Object UNINITIALIZED = new Object();
  private volatile Object instance = UNINITIALIZED;
  private volatile Provider<T> provider;
  
  ScopedProvider(ComponentFactory paramComponentFactory, ComponentContainer paramComponentContainer)
  {
    provider = ContextScope.1.getProvider(paramComponentFactory, paramComponentContainer);
  }
  
  public final Object get()
  {
    Object localObject1 = instance;
    if (localObject1 == UNINITIALIZED) {
      try
      {
        Object localObject2 = instance;
        localObject1 = localObject2;
        if (localObject2 == UNINITIALIZED)
        {
          localObject2 = provider.get();
          localObject1 = localObject2;
          instance = localObject2;
          provider = null;
        }
        return localObject1;
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
    return localThrowable;
  }
}
