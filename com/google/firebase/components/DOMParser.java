package com.google.firebase.components;

import com.google.android.android.common.internal.Preconditions;
import com.google.firebase.events.Publisher;
import com.google.firebase.events.Subscriber;
import com.google.firebase.inject.Provider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;

public final class DOMParser
  extends SynchronizedHashtable4
{
  private final Map<Class<?>, zzj<?>> context = new HashMap();
  private final List<Component<?>> controls;
  private final Node this$0;
  
  public DOMParser(Executor paramExecutor, Iterable paramIterable, Component... paramVarArgs)
  {
    this$0 = new Node(paramExecutor);
    paramExecutor = new ArrayList();
    paramExecutor.add(Component.start(this$0, zzh.class, new Class[] { Subscriber.class, Publisher.class }));
    paramIterable = paramIterable.iterator();
    while (paramIterable.hasNext()) {
      paramExecutor.addAll(((ComponentRegistrar)paramIterable.next()).getComponents());
    }
    Collections.addAll(paramExecutor, paramVarArgs);
    controls = Collections.unmodifiableList(Component.1.get(paramExecutor));
    paramExecutor = controls.iterator();
    while (paramExecutor.hasNext()) {
      validate((Component)paramExecutor.next());
    }
    validate();
  }
  
  private void validate()
  {
    Component localComponent;
    Dependency localDependency;
    do
    {
      Iterator localIterator1 = controls.iterator();
      Iterator localIterator2;
      while (!localIterator2.hasNext())
      {
        if (!localIterator1.hasNext()) {
          break;
        }
        localComponent = (Component)localIterator1.next();
        localIterator2 = localComponent.getName().iterator();
      }
      localDependency = (Dependency)localIterator2.next();
    } while ((!localDependency.equals()) || (context.containsKey(localDependency.getType())));
    throw new MissingDependencyException(String.format("Unsatisfied dependency for component %s: %s", new Object[] { localComponent, localDependency.getType() }));
  }
  
  private void validate(Component paramComponent)
  {
    ScopedProvider localScopedProvider = new ScopedProvider(paramComponent.getProperty(), new Settings(paramComponent, this));
    paramComponent = paramComponent.getType().iterator();
    while (paramComponent.hasNext())
    {
      Class localClass = (Class)paramComponent.next();
      context.put(localClass, localScopedProvider);
    }
  }
  
  public final Provider getProvider(Class paramClass)
  {
    Preconditions.checkNotNull(paramClass, "Null interface requested.");
    return (Provider)context.get(paramClass);
  }
  
  public final void validate(boolean paramBoolean)
  {
    Iterator localIterator = controls.iterator();
    while (localIterator.hasNext())
    {
      Component localComponent = (Component)localIterator.next();
      if ((localComponent.equals()) || ((localComponent.validate()) && (paramBoolean))) {
        get((Class)localComponent.getType().iterator().next());
      }
    }
    this$0.append();
  }
}
