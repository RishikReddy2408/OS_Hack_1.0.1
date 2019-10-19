package com.google.firebase.components;

import android.support.annotation.GuardedBy;
import com.google.android.android.common.internal.Preconditions;
import com.google.firebase.events.Event;
import com.google.firebase.events.EventHandler;
import com.google.firebase.events.Publisher;
import com.google.firebase.events.Subscriber;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

class Node
  implements Publisher, Subscriber
{
  @GuardedBy("this")
  private final Map<Class<?>, ConcurrentHashMap<EventHandler<Object>, Executor>> cache = new HashMap();
  private final Executor name;
  @GuardedBy("this")
  private Queue<Event<?>> queue = new ArrayDeque();
  
  Node(Executor paramExecutor)
  {
    name = paramExecutor;
  }
  
  private Set get(Event paramEvent)
  {
    try
    {
      paramEvent = (Map)cache.get(paramEvent.getType());
      if (paramEvent == null)
      {
        paramEvent = Collections.emptySet();
        return paramEvent;
      }
      paramEvent = paramEvent.entrySet();
      return paramEvent;
    }
    catch (Throwable paramEvent)
    {
      throw paramEvent;
    }
  }
  
  final void append()
  {
    for (;;)
    {
      try
      {
        if (queue == null) {
          break label67;
        }
        Object localObject1 = queue;
        queue = null;
        if (localObject1 != null)
        {
          localObject1 = ((Queue)localObject1).iterator();
          if (((Iterator)localObject1).hasNext())
          {
            publish((Event)((Iterator)localObject1).next());
            continue;
          }
        }
        else
        {
          return;
        }
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
      return;
      label67:
      Object localObject2 = null;
    }
  }
  
  public void publish(Event paramEvent)
  {
    Preconditions.checkNotNull(paramEvent);
    try
    {
      if (queue != null)
      {
        queue.add(paramEvent);
        return;
      }
      Iterator localIterator = get(paramEvent).iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        ((Executor)localEntry.getValue()).execute(DelayedMapListener.CallbackTask.getValue(localEntry, paramEvent));
      }
      return;
    }
    catch (Throwable paramEvent)
    {
      throw paramEvent;
    }
  }
  
  public void subscribe(Class paramClass, EventHandler paramEventHandler)
  {
    subscribe(paramClass, name, paramEventHandler);
  }
  
  public void subscribe(Class paramClass, Executor paramExecutor, EventHandler paramEventHandler)
  {
    try
    {
      Preconditions.checkNotNull(paramClass);
      Preconditions.checkNotNull(paramEventHandler);
      Preconditions.checkNotNull(paramExecutor);
      if (!cache.containsKey(paramClass)) {
        cache.put(paramClass, new ConcurrentHashMap());
      }
      ((ConcurrentHashMap)cache.get(paramClass)).put(paramEventHandler, paramExecutor);
      return;
    }
    catch (Throwable paramClass)
    {
      throw paramClass;
    }
  }
  
  public void unsubscribe(Class paramClass, EventHandler paramEventHandler)
  {
    try
    {
      Preconditions.checkNotNull(paramClass);
      Preconditions.checkNotNull(paramEventHandler);
      boolean bool = cache.containsKey(paramClass);
      if (!bool) {
        return;
      }
      ConcurrentHashMap localConcurrentHashMap = (ConcurrentHashMap)cache.get(paramClass);
      localConcurrentHashMap.remove(paramEventHandler);
      if (localConcurrentHashMap.isEmpty()) {
        cache.remove(paramClass);
      }
      return;
    }
    catch (Throwable paramClass)
    {
      throw paramClass;
    }
  }
}
