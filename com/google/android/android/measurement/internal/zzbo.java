package com.google.android.android.measurement.internal;

import com.google.android.android.common.internal.Preconditions;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public final class zzbo
  extends zzcp
{
  private static final AtomicLong zzape = new AtomicLong(Long.MIN_VALUE);
  private ExecutorService zzaea;
  private zzbs zzaov;
  private zzbs zzaow;
  private final PriorityBlockingQueue<com.google.android.gms.measurement.internal.zzbr<?>> zzaox = new PriorityBlockingQueue();
  private final BlockingQueue<com.google.android.gms.measurement.internal.zzbr<?>> zzaoy = new LinkedBlockingQueue();
  private final Thread.UncaughtExceptionHandler zzaoz = new zzbq(this, "Thread death: Uncaught exception on worker thread");
  private final Thread.UncaughtExceptionHandler zzapa = new zzbq(this, "Thread death: Uncaught exception on network thread");
  private final Object zzapb = new Object();
  private final Semaphore zzapc = new Semaphore(2);
  private volatile boolean zzapd;
  
  zzbo(zzbt paramZzbt)
  {
    super(paramZzbt);
  }
  
  private final void enqueue(zzbr paramZzbr)
  {
    Object localObject = zzapb;
    try
    {
      zzaox.add(paramZzbr);
      if (zzaov == null)
      {
        zzaov = new zzbs(this, "Measurement Worker", zzaox);
        zzaov.setUncaughtExceptionHandler(zzaoz);
        zzaov.start();
      }
      else
      {
        zzaov.zzke();
      }
      return;
    }
    catch (Throwable paramZzbr)
    {
      throw paramZzbr;
    }
  }
  
  public final Future d(Callable paramCallable)
    throws IllegalStateException
  {
    zzcl();
    Preconditions.checkNotNull(paramCallable);
    paramCallable = new zzbr(this, paramCallable, false, "Task exception on worker thread");
    if (Thread.currentThread() == zzaov)
    {
      if (!zzaox.isEmpty()) {
        zzgo().zzjg().zzbx("Callable skipped the worker queue.");
      }
      paramCallable.run();
      return paramCallable;
    }
    enqueue(paramCallable);
    return paramCallable;
  }
  
  final Object download(AtomicReference paramAtomicReference, long paramLong, String paramString, Runnable paramRunnable)
  {
    for (;;)
    {
      try
      {
        zzgn().get(paramRunnable);
      }
      catch (Throwable paramString)
      {
        zzar localZzar;
        throw paramString;
      }
      try
      {
        paramAtomicReference.wait(15000L);
        paramRunnable = paramAtomicReference.get();
        if (paramRunnable != null) {
          break label152;
        }
        localZzar = zzgo().zzjg();
        paramAtomicReference = String.valueOf(paramString);
        if (paramAtomicReference.length() != 0) {
          paramAtomicReference = "Timed out waiting for ".concat(paramAtomicReference);
        } else {
          paramAtomicReference = new String("Timed out waiting for ");
        }
        localZzar.zzbx(paramAtomicReference);
        return paramRunnable;
      }
      catch (InterruptedException paramRunnable) {}
    }
    paramRunnable = zzgo().zzjg();
    paramString = String.valueOf(paramString);
    if (paramString.length() != 0) {
      paramString = "Interrupted waiting for ".concat(paramString);
    } else {
      paramString = new String("Interrupted waiting for ");
    }
    paramRunnable.zzbx(paramString);
    return null;
    label152:
    return paramRunnable;
  }
  
  public final void enqueue(Runnable paramRunnable)
    throws IllegalStateException
  {
    zzcl();
    Preconditions.checkNotNull(paramRunnable);
    zzbr localZzbr = new zzbr(this, paramRunnable, false, "Task exception on network thread");
    paramRunnable = zzapb;
    try
    {
      zzaoy.add(localZzbr);
      if (zzaow == null)
      {
        zzaow = new zzbs(this, "Measurement Network", zzaoy);
        zzaow.setUncaughtExceptionHandler(zzapa);
        zzaow.start();
      }
      else
      {
        zzaow.zzke();
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public final Future get(Callable paramCallable)
    throws IllegalStateException
  {
    zzcl();
    Preconditions.checkNotNull(paramCallable);
    paramCallable = new zzbr(this, paramCallable, true, "Task exception on worker thread");
    if (Thread.currentThread() == zzaov)
    {
      paramCallable.run();
      return paramCallable;
    }
    enqueue(paramCallable);
    return paramCallable;
  }
  
  public final void get(Runnable paramRunnable)
    throws IllegalStateException
  {
    zzcl();
    Preconditions.checkNotNull(paramRunnable);
    enqueue(new zzbr(this, paramRunnable, false, "Task exception on worker thread"));
  }
  
  public final void zzaf()
  {
    if (Thread.currentThread() == zzaov) {
      return;
    }
    throw new IllegalStateException("Call expected from worker thread");
  }
  
  public final void zzgc()
  {
    if (Thread.currentThread() == zzaow) {
      return;
    }
    throw new IllegalStateException("Call expected from network thread");
  }
  
  protected final boolean zzgt()
  {
    return false;
  }
  
  public final boolean zzkb()
  {
    return Thread.currentThread() == zzaov;
  }
  
  public final ExecutorService zzkc()
  {
    Object localObject = zzapb;
    try
    {
      if (zzaea == null) {
        zzaea = new ThreadPoolExecutor(0, 1, 30L, TimeUnit.SECONDS, new ArrayBlockingQueue(100));
      }
      ExecutorService localExecutorService = zzaea;
      return localExecutorService;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
}
