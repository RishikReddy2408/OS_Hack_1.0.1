package com.google.firebase.events;

import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.concurrent.Executor;

@KeepForSdk
public abstract interface Subscriber
{
  public abstract void subscribe(Class paramClass, EventHandler paramEventHandler);
  
  public abstract void subscribe(Class paramClass, Executor paramExecutor, EventHandler paramEventHandler);
  
  public abstract void unsubscribe(Class paramClass, EventHandler paramEventHandler);
}
