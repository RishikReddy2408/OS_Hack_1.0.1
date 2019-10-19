package com.google.gson.internal;

import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public abstract class UnsafeAllocator
{
  public UnsafeAllocator() {}
  
  static void assertInstantiable(Class paramClass)
  {
    int i = paramClass.getModifiers();
    if (!Modifier.isInterface(i))
    {
      if (!Modifier.isAbstract(i)) {
        return;
      }
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Abstract class can't be instantiated! Class name: ");
      localStringBuilder.append(paramClass.getName());
      throw new UnsupportedOperationException(localStringBuilder.toString());
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Interface can't be instantiated! Interface name: ");
    localStringBuilder.append(paramClass.getName());
    throw new UnsupportedOperationException(localStringBuilder.toString());
  }
  
  public static UnsafeAllocator create()
  {
    try
    {
      localObject1 = Class.forName("sun.misc.Unsafe");
      final Object localObject2 = ((Class)localObject1).getDeclaredField("theUnsafe");
      ((Field)localObject2).setAccessible(true);
      localObject2 = ((Field)localObject2).get(null);
      localObject1 = ((Class)localObject1).getMethod("allocateInstance", new Class[] { Class.class });
      localObject1 = new UnsafeAllocator()
      {
        public Object newInstance(Class paramAnonymousClass)
          throws Exception
        {
          UnsafeAllocator.assertInstantiable(paramAnonymousClass);
          return val$allocateInstance.invoke(localObject2, new Object[] { paramAnonymousClass });
        }
      };
      return localObject1;
    }
    catch (Exception localException1)
    {
      Object localObject1;
      final int i;
      label141:
      label179:
      for (;;) {}
    }
    try
    {
      localObject1 = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", new Class[] { Class.class });
      ((Method)localObject1).setAccessible(true);
      localObject1 = ((Method)localObject1).invoke(null, new Object[] { Object.class });
      localObject1 = (Integer)localObject1;
      i = ((Integer)localObject1).intValue();
      localObject1 = Integer.TYPE;
      localObject1 = ObjectStreamClass.class.getDeclaredMethod("newInstance", new Class[] { Class.class, localObject1 });
      ((Method)localObject1).setAccessible(true);
      localObject1 = new UnsafeAllocator()
      {
        public Object newInstance(Class paramAnonymousClass)
          throws Exception
        {
          UnsafeAllocator.assertInstantiable(paramAnonymousClass);
          return val$newInstance.invoke(null, new Object[] { paramAnonymousClass, Integer.valueOf(i) });
        }
      };
      return localObject1;
    }
    catch (Exception localException2)
    {
      break label141;
    }
    try
    {
      localObject1 = ObjectInputStream.class.getDeclaredMethod("newInstance", new Class[] { Class.class, Class.class });
      ((Method)localObject1).setAccessible(true);
      localObject1 = new UnsafeAllocator()
      {
        public Object newInstance(Class paramAnonymousClass)
          throws Exception
        {
          UnsafeAllocator.assertInstantiable(paramAnonymousClass);
          return val$newInstance.invoke(null, new Object[] { paramAnonymousClass, Object.class });
        }
      };
      return localObject1;
    }
    catch (Exception localException3)
    {
      break label179;
    }
    new UnsafeAllocator()
    {
      public Object newInstance(Class paramAnonymousClass)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Cannot allocate ");
        localStringBuilder.append(paramAnonymousClass);
        throw new UnsupportedOperationException(localStringBuilder.toString());
      }
    };
  }
  
  public abstract Object newInstance(Class paramClass)
    throws Exception;
}
