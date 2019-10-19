package com.google.firebase.components;

import com.google.firebase.events.Publisher;
import com.google.firebase.inject.Provider;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

final class Settings
  extends SynchronizedHashtable4
{
  private final ComponentContainer context;
  private final Set<Class<?>> instance;
  private final Set<Class<?>> path;
  private final Set<Class<?>> set;
  
  Settings(Component paramComponent, ComponentContainer paramComponentContainer)
  {
    HashSet localHashSet1 = new HashSet();
    HashSet localHashSet2 = new HashSet();
    Iterator localIterator = paramComponent.getName().iterator();
    while (localIterator.hasNext())
    {
      Dependency localDependency = (Dependency)localIterator.next();
      if (localDependency.get()) {
        localHashSet1.add(localDependency.getType());
      } else {
        localHashSet2.add(localDependency.getType());
      }
    }
    if (!paramComponent.getProperties().isEmpty()) {
      localHashSet1.add(Publisher.class);
    }
    set = Collections.unmodifiableSet(localHashSet1);
    path = Collections.unmodifiableSet(localHashSet2);
    instance = paramComponent.getProperties();
    context = paramComponentContainer;
  }
  
  public final Object get(Class paramClass)
  {
    if (set.contains(paramClass))
    {
      Object localObject = context.get(paramClass);
      if (!paramClass.equals(Publisher.class)) {
        return localObject;
      }
      return new zzl.zza(instance, (Publisher)localObject);
    }
    throw new IllegalArgumentException(String.format("Requesting %s is not allowed.", new Object[] { paramClass }));
  }
  
  public final Provider getProvider(Class paramClass)
  {
    if (path.contains(paramClass)) {
      return context.getProvider(paramClass);
    }
    throw new IllegalArgumentException(String.format("Requesting Provider<%s> is not allowed.", new Object[] { paramClass }));
  }
}
