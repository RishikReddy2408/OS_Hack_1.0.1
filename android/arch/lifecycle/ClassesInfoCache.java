package android.arch.lifecycle;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class ClassesInfoCache
{
  private static final int CALL_TYPE_NO_ARG = 0;
  private static final int CALL_TYPE_PROVIDER = 1;
  private static final int CALL_TYPE_PROVIDER_WITH_EVENT = 2;
  static ClassesInfoCache sInstance = new ClassesInfoCache();
  private final Map<Class, CallbackInfo> mCallbackMap = new HashMap();
  private final Map<Class, Boolean> mHasLifecycleMethods = new HashMap();
  
  ClassesInfoCache() {}
  
  private CallbackInfo createInfo(Class paramClass, Method[] paramArrayOfMethod)
  {
    Object localObject1 = paramClass.getSuperclass();
    HashMap localHashMap = new HashMap();
    if (localObject1 != null)
    {
      localObject1 = getInfo((Class)localObject1);
      if (localObject1 != null) {
        localHashMap.putAll(mHandlerToEvent);
      }
    }
    localObject1 = paramClass.getInterfaces();
    int j = localObject1.length;
    int i = 0;
    Object localObject2;
    Object localObject3;
    while (i < j)
    {
      localObject2 = getInfomHandlerToEvent.entrySet().iterator();
      while (((Iterator)localObject2).hasNext())
      {
        localObject3 = (Map.Entry)((Iterator)localObject2).next();
        verifyAndPutHandler(localHashMap, (MethodReference)((Map.Entry)localObject3).getKey(), (Lifecycle.Event)((Map.Entry)localObject3).getValue(), paramClass);
      }
      i += 1;
    }
    if (paramArrayOfMethod == null) {
      paramArrayOfMethod = getDeclaredMethods(paramClass);
    }
    int k = paramArrayOfMethod.length;
    j = 0;
    boolean bool = false;
    while (j < k)
    {
      localObject1 = paramArrayOfMethod[j];
      localObject3 = (OnLifecycleEvent)((Method)localObject1).getAnnotation(OnLifecycleEvent.class);
      if (localObject3 != null)
      {
        localObject2 = ((Method)localObject1).getParameterTypes();
        if (localObject2.length > 0)
        {
          if (localObject2[0].isAssignableFrom(LifecycleOwner.class)) {
            i = 1;
          } else {
            throw new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner");
          }
        }
        else {
          i = 0;
        }
        localObject3 = ((OnLifecycleEvent)localObject3).value();
        if (localObject2.length > 1) {
          if (localObject2[1].isAssignableFrom(Lifecycle.Event.class))
          {
            if (localObject3 == Lifecycle.Event.ON_ANY) {
              i = 2;
            } else {
              throw new IllegalArgumentException("Second arg is supported only for ON_ANY value");
            }
          }
          else {
            throw new IllegalArgumentException("invalid parameter type. second arg must be an event");
          }
        }
        if (localObject2.length <= 2)
        {
          verifyAndPutHandler(localHashMap, new MethodReference(i, (Method)localObject1), (Lifecycle.Event)localObject3, paramClass);
          bool = true;
        }
      }
      else
      {
        j += 1;
        continue;
      }
      throw new IllegalArgumentException("cannot have more than 2 params");
    }
    paramArrayOfMethod = new CallbackInfo(localHashMap);
    mCallbackMap.put(paramClass, paramArrayOfMethod);
    mHasLifecycleMethods.put(paramClass, Boolean.valueOf(bool));
    return paramArrayOfMethod;
  }
  
  private Method[] getDeclaredMethods(Class paramClass)
  {
    try
    {
      paramClass = paramClass.getDeclaredMethods();
      return paramClass;
    }
    catch (NoClassDefFoundError paramClass)
    {
      throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", paramClass);
    }
  }
  
  private void verifyAndPutHandler(Map paramMap, MethodReference paramMethodReference, Lifecycle.Event paramEvent, Class paramClass)
  {
    Lifecycle.Event localEvent = (Lifecycle.Event)paramMap.get(paramMethodReference);
    if ((localEvent != null) && (paramEvent != localEvent))
    {
      paramMap = mMethod;
      paramMethodReference = new StringBuilder();
      paramMethodReference.append("Method ");
      paramMethodReference.append(paramMap.getName());
      paramMethodReference.append(" in ");
      paramMethodReference.append(paramClass.getName());
      paramMethodReference.append(" already declared with different @OnLifecycleEvent value: previous");
      paramMethodReference.append(" value ");
      paramMethodReference.append(localEvent);
      paramMethodReference.append(", new value ");
      paramMethodReference.append(paramEvent);
      throw new IllegalArgumentException(paramMethodReference.toString());
    }
    if (localEvent == null) {
      paramMap.put(paramMethodReference, paramEvent);
    }
  }
  
  CallbackInfo getInfo(Class paramClass)
  {
    CallbackInfo localCallbackInfo = (CallbackInfo)mCallbackMap.get(paramClass);
    if (localCallbackInfo != null) {
      return localCallbackInfo;
    }
    return createInfo(paramClass, null);
  }
  
  boolean hasLifecycleMethods(Class paramClass)
  {
    if (mHasLifecycleMethods.containsKey(paramClass)) {
      return ((Boolean)mHasLifecycleMethods.get(paramClass)).booleanValue();
    }
    Method[] arrayOfMethod = getDeclaredMethods(paramClass);
    int j = arrayOfMethod.length;
    int i = 0;
    while (i < j)
    {
      if ((OnLifecycleEvent)arrayOfMethod[i].getAnnotation(OnLifecycleEvent.class) != null)
      {
        createInfo(paramClass, arrayOfMethod);
        return true;
      }
      i += 1;
    }
    mHasLifecycleMethods.put(paramClass, Boolean.valueOf(false));
    return false;
  }
  
  static class CallbackInfo
  {
    final Map<Lifecycle.Event, List<ClassesInfoCache.MethodReference>> mEventToHandlers;
    final Map<ClassesInfoCache.MethodReference, Lifecycle.Event> mHandlerToEvent;
    
    CallbackInfo(Map paramMap) {}
    
    private static void invokeMethodsForEvent(List paramList, LifecycleOwner paramLifecycleOwner, Lifecycle.Event paramEvent, Object paramObject)
    {
      if (paramList != null)
      {
        int i = paramList.size() - 1;
        while (i >= 0)
        {
          ((ClassesInfoCache.MethodReference)paramList.get(i)).invokeCallback(paramLifecycleOwner, paramEvent, paramObject);
          i -= 1;
        }
      }
    }
    
    void invokeCallbacks(LifecycleOwner paramLifecycleOwner, Lifecycle.Event paramEvent, Object paramObject)
    {
      invokeMethodsForEvent((List)mEventToHandlers.get(paramEvent), paramLifecycleOwner, paramEvent, paramObject);
      invokeMethodsForEvent((List)mEventToHandlers.get(Lifecycle.Event.ON_ANY), paramLifecycleOwner, paramEvent, paramObject);
    }
  }
  
  static class MethodReference
  {
    final int mCallType;
    final Method mMethod;
    
    MethodReference(int paramInt, Method paramMethod)
    {
      mCallType = paramInt;
      mMethod = paramMethod;
      mMethod.setAccessible(true);
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {
        return true;
      }
      if (paramObject != null)
      {
        if (getClass() != paramObject.getClass()) {
          return false;
        }
        paramObject = (MethodReference)paramObject;
        return (mCallType == mCallType) && (mMethod.getName().equals(mMethod.getName()));
      }
      return false;
    }
    
    public int hashCode()
    {
      return mCallType * 31 + mMethod.getName().hashCode();
    }
    
    void invokeCallback(LifecycleOwner paramLifecycleOwner, Lifecycle.Event paramEvent, Object paramObject)
    {
      Method localMethod;
      switch (mCallType)
      {
      default: 
        return;
      case 2: 
        localMethod = mMethod;
      }
      try
      {
        localMethod.invoke(paramObject, new Object[] { paramLifecycleOwner, paramEvent });
        return;
      }
      catch (IllegalAccessException paramLifecycleOwner)
      {
        throw new RuntimeException(paramLifecycleOwner);
      }
      catch (InvocationTargetException paramLifecycleOwner)
      {
        throw new RuntimeException("Failed to call observer method", paramLifecycleOwner.getCause());
      }
      paramEvent = mMethod;
      paramEvent.invoke(paramObject, new Object[] { paramLifecycleOwner });
      return;
      paramLifecycleOwner = mMethod;
      paramLifecycleOwner.invoke(paramObject, new Object[0]);
    }
  }
}
