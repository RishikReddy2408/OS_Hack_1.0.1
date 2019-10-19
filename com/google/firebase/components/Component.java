package com.google.firebase.components;

import android.content.Context;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@KeepForSdk
public final class Component<T>
{
  private final ComponentFactory<T> id;
  private final int name;
  private final Set<Dependency> path;
  private final Set<Class<?>> properties;
  private final Set<Class<? super T>> set;
  
  private Component(Set paramSet1, Set paramSet2, int paramInt, ComponentFactory paramComponentFactory, Set paramSet3)
  {
    set = Collections.unmodifiableSet(paramSet1);
    path = Collections.unmodifiableSet(paramSet2);
    name = paramInt;
    id = paramComponentFactory;
    properties = Collections.unmodifiableSet(paramSet3);
  }
  
  public static Builder builder(Class paramClass)
  {
    return new Builder(paramClass, new Class[0], (byte)0);
  }
  
  public static Builder builder(Class paramClass, Class... paramVarArgs)
  {
    return new Builder(paramClass, paramVarArgs, (byte)0);
  }
  
  public static Component create(Class paramClass, Object paramObject)
  {
    return builder(paramClass).factory(ReflogWriter.create(paramObject)).build();
  }
  
  public static Component start(Object paramObject, Class paramClass, Class... paramVarArgs)
  {
    return builder(paramClass, paramVarArgs).factory(AbstractComponentFactory.setProperty(paramObject)).build();
  }
  
  public final boolean equals()
  {
    return name == 1;
  }
  
  public final Set getName()
  {
    return path;
  }
  
  public final Set getProperties()
  {
    return properties;
  }
  
  public final ComponentFactory getProperty()
  {
    return id;
  }
  
  public final Set getType()
  {
    return set;
  }
  
  public final String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("Component<");
    localStringBuilder.append(Arrays.toString(set.toArray()));
    localStringBuilder.append(">{");
    localStringBuilder.append(name);
    localStringBuilder.append(", deps=");
    localStringBuilder.append(Arrays.toString(path.toArray()));
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }
  
  public final boolean validate()
  {
    return name == 2;
  }
  
  @KeepForSdk
  public static class Builder<T>
  {
    private ComponentFactory<T> baseUrl;
    private int bitrate;
    private Set<Class<?>> channels;
    private final Set<Dependency> index = new HashSet();
    private final Set<Class<? super T>> path = new HashSet();
    
    private Builder(Class paramClass, Class... paramVarArgs)
    {
      int i = 0;
      bitrate = 0;
      channels = new HashSet();
      Preconditions.checkNotNull(paramClass, "Null interface");
      path.add(paramClass);
      int j = paramVarArgs.length;
      while (i < j)
      {
        Preconditions.checkNotNull(paramVarArgs[i], "Null interface");
        i += 1;
      }
      Collections.addAll(path, paramVarArgs);
    }
    
    private Builder compare(int paramInt)
    {
      boolean bool;
      if (bitrate == 0) {
        bool = true;
      } else {
        bool = false;
      }
      Preconditions.checkState(bool, "Instantiation type has already been set.");
      bitrate = paramInt;
      return this;
    }
    
    public Builder alwaysEager()
    {
      return compare(1);
    }
    
    public Component build()
    {
      boolean bool;
      if (baseUrl != null) {
        bool = true;
      } else {
        bool = false;
      }
      Preconditions.checkState(bool, "Missing required property: factory.");
      return new Component(new HashSet(path), new HashSet(index), bitrate, baseUrl, channels, (byte)0);
    }
    
    public Builder eagerInDefaultApp()
    {
      return compare(2);
    }
    
    public Builder factory(ComponentFactory paramComponentFactory)
    {
      baseUrl = ((ComponentFactory)Preconditions.checkNotNull(paramComponentFactory, "Null factory"));
      return this;
    }
    
    public Builder get(Dependency paramDependency)
    {
      Preconditions.checkNotNull(paramDependency, "Null dependency");
      Class localClass = paramDependency.getType();
      Preconditions.checkArgument(path.contains(localClass) ^ true, "Components are not allowed to depend on interfaces they themselves provide.");
      index.add(paramDependency);
      return this;
    }
    
    public Builder publishes(Class paramClass)
    {
      channels.add(paramClass);
      return this;
    }
  }
}
