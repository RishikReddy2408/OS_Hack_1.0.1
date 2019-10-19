package com.google.gson.internal;

import com.google.gson.InstanceCreator;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public final class ConstructorConstructor
{
  private final Map<Type, InstanceCreator<?>> instanceCreators;
  
  public ConstructorConstructor(Map paramMap)
  {
    instanceCreators = paramMap;
  }
  
  private ObjectConstructor newDefaultConstructor(final Class paramClass)
  {
    try
    {
      paramClass = paramClass.getDeclaredConstructor(new Class[0]);
      boolean bool = paramClass.isAccessible();
      if (!bool) {
        paramClass.setAccessible(true);
      }
      paramClass = new ObjectConstructor()
      {
        public Object construct()
        {
          Object localObject = paramClass;
          try
          {
            localObject = ((Constructor)localObject).newInstance(null);
            return localObject;
          }
          catch (IllegalAccessException localIllegalAccessException)
          {
            throw new AssertionError(localIllegalAccessException);
          }
          catch (InvocationTargetException localInvocationTargetException)
          {
            localStringBuilder = new StringBuilder();
            localStringBuilder.append("Failed to invoke ");
            localStringBuilder.append(paramClass);
            localStringBuilder.append(" with no args");
            throw new RuntimeException(localStringBuilder.toString(), localInvocationTargetException.getTargetException());
          }
          catch (InstantiationException localInstantiationException)
          {
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("Failed to invoke ");
            localStringBuilder.append(paramClass);
            localStringBuilder.append(" with no args");
            throw new RuntimeException(localStringBuilder.toString(), localInstantiationException);
          }
        }
      };
      return paramClass;
    }
    catch (NoSuchMethodException paramClass)
    {
      for (;;) {}
    }
    return null;
  }
  
  private ObjectConstructor newDefaultImplementationConstructor(final Type paramType, Class paramClass)
  {
    if (Collection.class.isAssignableFrom(paramClass))
    {
      if (SortedSet.class.isAssignableFrom(paramClass)) {
        new ObjectConstructor()
        {
          public Object construct()
          {
            return new TreeSet();
          }
        };
      }
      if (EnumSet.class.isAssignableFrom(paramClass)) {
        new ObjectConstructor()
        {
          public Object construct()
          {
            if ((paramType instanceof ParameterizedType))
            {
              localObject = ((ParameterizedType)paramType).getActualTypeArguments()[0];
              if ((localObject instanceof Class)) {
                return EnumSet.noneOf((Class)localObject);
              }
              localObject = new StringBuilder();
              ((StringBuilder)localObject).append("Invalid EnumSet type: ");
              ((StringBuilder)localObject).append(paramType.toString());
              throw new JsonIOException(((StringBuilder)localObject).toString());
            }
            Object localObject = new StringBuilder();
            ((StringBuilder)localObject).append("Invalid EnumSet type: ");
            ((StringBuilder)localObject).append(paramType.toString());
            throw new JsonIOException(((StringBuilder)localObject).toString());
          }
        };
      }
      if (Set.class.isAssignableFrom(paramClass)) {
        new ObjectConstructor()
        {
          public Object construct()
          {
            return new LinkedHashSet();
          }
        };
      }
      if (Queue.class.isAssignableFrom(paramClass)) {
        new ObjectConstructor()
        {
          public Object construct()
          {
            return new ArrayDeque();
          }
        };
      }
      new ObjectConstructor()
      {
        public Object construct()
        {
          return new ArrayList();
        }
      };
    }
    if (Map.class.isAssignableFrom(paramClass))
    {
      if (ConcurrentNavigableMap.class.isAssignableFrom(paramClass)) {
        new ObjectConstructor()
        {
          public Object construct()
          {
            return new ConcurrentSkipListMap();
          }
        };
      }
      if (ConcurrentMap.class.isAssignableFrom(paramClass)) {
        new ObjectConstructor()
        {
          public Object construct()
          {
            return new ConcurrentHashMap();
          }
        };
      }
      if (SortedMap.class.isAssignableFrom(paramClass)) {
        new ObjectConstructor()
        {
          public Object construct()
          {
            return new TreeMap();
          }
        };
      }
      if (((paramType instanceof ParameterizedType)) && (!String.class.isAssignableFrom(TypeToken.get(((ParameterizedType)paramType).getActualTypeArguments()[0]).getRawType()))) {
        new ObjectConstructor()
        {
          public Object construct()
          {
            return new LinkedHashMap();
          }
        };
      }
      new ObjectConstructor()
      {
        public Object construct()
        {
          return new LinkedTreeMap();
        }
      };
    }
    return null;
  }
  
  private ObjectConstructor newUnsafeAllocator(final Type paramType, final Class paramClass)
  {
    new ObjectConstructor()
    {
      private final UnsafeAllocator unsafeAllocator = UnsafeAllocator.create();
      
      public Object construct()
      {
        Object localObject1 = unsafeAllocator;
        Object localObject2 = paramClass;
        try
        {
          localObject1 = ((UnsafeAllocator)localObject1).newInstance((Class)localObject2);
          return localObject1;
        }
        catch (Exception localException)
        {
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append("Unable to invoke no-args constructor for ");
          ((StringBuilder)localObject2).append(paramType);
          ((StringBuilder)localObject2).append(". Registering an InstanceCreator with Gson for this type may fix this problem.");
          throw new RuntimeException(((StringBuilder)localObject2).toString(), localException);
        }
      }
    };
  }
  
  public ObjectConstructor get(TypeToken paramTypeToken)
  {
    final Type localType = paramTypeToken.getType();
    paramTypeToken = paramTypeToken.getRawType();
    Object localObject = (InstanceCreator)instanceCreators.get(localType);
    if (localObject != null) {
      new ObjectConstructor()
      {
        public Object construct()
        {
          return val$typeCreator.createInstance(localType);
        }
      };
    }
    localObject = (InstanceCreator)instanceCreators.get(paramTypeToken);
    if (localObject != null) {
      new ObjectConstructor()
      {
        public Object construct()
        {
          return val$rawTypeCreator.createInstance(localType);
        }
      };
    }
    localObject = newDefaultConstructor(paramTypeToken);
    if (localObject != null) {
      return localObject;
    }
    localObject = newDefaultImplementationConstructor(localType, paramTypeToken);
    if (localObject != null) {
      return localObject;
    }
    return newUnsafeAllocator(localType, paramTypeToken);
  }
  
  public String toString()
  {
    return instanceCreators.toString();
  }
}
