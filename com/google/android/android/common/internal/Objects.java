package com.google.android.android.common.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@KeepForSdk
public final class Objects
{
  private Objects()
  {
    throw new AssertionError("Uninstantiable");
  }
  
  public static boolean equal(Object paramObject1, Object paramObject2)
  {
    return (paramObject1 == paramObject2) || ((paramObject1 != null) && (paramObject1.equals(paramObject2)));
  }
  
  public static int hashCode(Object... paramVarArgs)
  {
    return Arrays.hashCode(paramVarArgs);
  }
  
  public static ToStringHelper toStringHelper(Object paramObject)
  {
    return new ToStringHelper(null);
  }
  
  @KeepForSdk
  public final class ToStringHelper
  {
    private final List<String> zzer;
    private final Object zzes;
    
    private ToStringHelper()
    {
      zzes = Preconditions.checkNotNull(this$1);
      zzer = new ArrayList();
    }
    
    public final ToStringHelper add(String paramString, Object paramObject)
    {
      List localList = zzer;
      paramString = (String)Preconditions.checkNotNull(paramString);
      paramObject = String.valueOf(paramObject);
      StringBuilder localStringBuilder = new StringBuilder(String.valueOf(paramString).length() + 1 + String.valueOf(paramObject).length());
      localStringBuilder.append(paramString);
      localStringBuilder.append("=");
      localStringBuilder.append(paramObject);
      localList.add(localStringBuilder.toString());
      return this;
    }
    
    public final String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder(100);
      localStringBuilder.append(zzes.getClass().getSimpleName());
      localStringBuilder.append('{');
      int j = zzer.size();
      int i = 0;
      while (i < j)
      {
        localStringBuilder.append((String)zzer.get(i));
        if (i < j - 1) {
          localStringBuilder.append(", ");
        }
        i += 1;
      }
      localStringBuilder.append('}');
      return localStringBuilder.toString();
    }
  }
}
