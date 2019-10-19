package com.google.firebase.components;

import com.google.android.android.common.internal.Preconditions;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class Dependency
{
  private final int key;
  private final int scope;
  private final Class<?> type;
  
  private Dependency(Class paramClass, int paramInt1, int paramInt2)
  {
    type = ((Class)Preconditions.checkNotNull(paramClass, "Null dependency anInterface."));
    scope = paramInt1;
    key = paramInt2;
  }
  
  public static Dependency optional(Class paramClass)
  {
    return new Dependency(paramClass, 0, 0);
  }
  
  public static Dependency optionalProvider(Class paramClass)
  {
    return new Dependency(paramClass, 0, 1);
  }
  
  public static Dependency required(Class paramClass)
  {
    return new Dependency(paramClass, 1, 0);
  }
  
  public static Dependency requiredProvider(Class paramClass)
  {
    return new Dependency(paramClass, 1, 1);
  }
  
  public final boolean equals()
  {
    return scope == 1;
  }
  
  public final boolean equals(Object paramObject)
  {
    if ((paramObject instanceof Dependency))
    {
      paramObject = (Dependency)paramObject;
      if ((type == type) && (scope == scope) && (key == key)) {
        return true;
      }
    }
    return false;
  }
  
  public final boolean get()
  {
    return key == 0;
  }
  
  public final Class getType()
  {
    return type;
  }
  
  public final int hashCode()
  {
    return ((type.hashCode() ^ 0xF4243) * 1000003 ^ scope) * 1000003 ^ key;
  }
  
  public final String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("Dependency{anInterface=");
    localStringBuilder.append(type);
    localStringBuilder.append(", required=");
    int i = scope;
    boolean bool2 = false;
    if (i == 1) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    localStringBuilder.append(bool1);
    localStringBuilder.append(", direct=");
    boolean bool1 = bool2;
    if (key == 0) {
      bool1 = true;
    }
    localStringBuilder.append(bool1);
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }
}
