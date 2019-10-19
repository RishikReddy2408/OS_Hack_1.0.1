package android.support.v4.provider;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.GuardedBy;
import android.support.annotation.RestrictTo;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class SelfDestructiveThread
{
  private static final int MSG_DESTRUCTION = 0;
  private static final int MSG_INVOKE_RUNNABLE = 1;
  private Handler.Callback mCallback = new Handler.Callback()
  {
    public boolean handleMessage(Message paramAnonymousMessage)
    {
      switch (what)
      {
      default: 
        return true;
      case 1: 
        SelfDestructiveThread.this.onInvokeRunnable((Runnable)obj);
        return true;
      }
      SelfDestructiveThread.this.onDestruction();
      return true;
    }
  };
  private final int mDestructAfterMillisec;
  @GuardedBy("mLock")
  private int mGeneration;
  @GuardedBy("mLock")
  private Handler mHandler;
  private final Object mLock = new Object();
  private final int mPriority;
  @GuardedBy("mLock")
  private HandlerThread mThread;
  private final String mThreadName;
  
  public SelfDestructiveThread(String paramString, int paramInt1, int paramInt2)
  {
    mThreadName = paramString;
    mPriority = paramInt1;
    mDestructAfterMillisec = paramInt2;
    mGeneration = 0;
  }
  
  private void onDestruction()
  {
    Object localObject = mLock;
    try
    {
      if (mHandler.hasMessages(1)) {
        return;
      }
      mThread.quit();
      mThread = null;
      mHandler = null;
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  private void onInvokeRunnable(Runnable paramRunnable)
  {
    paramRunnable.run();
    paramRunnable = mLock;
    try
    {
      mHandler.removeMessages(0);
      mHandler.sendMessageDelayed(mHandler.obtainMessage(0), mDestructAfterMillisec);
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  private void post(Runnable paramRunnable)
  {
    Object localObject = mLock;
    try
    {
      if (mThread == null)
      {
        mThread = new HandlerThread(mThreadName, mPriority);
        mThread.start();
        mHandler = new Handler(mThread.getLooper(), mCallback);
        mGeneration += 1;
      }
      mHandler.removeMessages(0);
      mHandler.sendMessage(mHandler.obtainMessage(1, paramRunnable));
      return;
    }
    catch (Throwable paramRunnable)
    {
      throw paramRunnable;
    }
  }
  
  public int getGeneration()
  {
    Object localObject = mLock;
    try
    {
      int i = mGeneration;
      return i;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public boolean isRunning()
  {
    Object localObject = mLock;
    for (;;)
    {
      try
      {
        if (mThread != null)
        {
          bool = true;
          return bool;
        }
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
      boolean bool = false;
    }
  }
  
  public void postAndReply(final Callable paramCallable, final ReplyCallback paramReplyCallback)
  {
    post(new Runnable()
    {
      public void run()
      {
        final Object localObject = paramCallable;
        try
        {
          localObject = ((Callable)localObject).call();
        }
        catch (Exception localException)
        {
          for (;;) {}
        }
        localObject = null;
        val$callingHandler.post(new Runnable()
        {
          public void run()
          {
            val$reply.onReply(localObject);
          }
        });
      }
    });
  }
  
  public Object postAndWait(final Callable paramCallable, int paramInt)
    throws InterruptedException
  {
    localReentrantLock = new ReentrantLock();
    final Condition localCondition = localReentrantLock.newCondition();
    final AtomicReference localAtomicReference = new AtomicReference();
    final AtomicBoolean localAtomicBoolean = new AtomicBoolean(true);
    post(new Runnable()
    {
      public void run()
      {
        AtomicReference localAtomicReference = localAtomicReference;
        Callable localCallable = paramCallable;
        try
        {
          localAtomicReference.set(localCallable.call());
          localReentrantLock.lock();
          try
          {
            localAtomicBoolean.set(false);
            localCondition.signal();
            localReentrantLock.unlock();
            return;
          }
          catch (Throwable localThrowable)
          {
            localReentrantLock.unlock();
            throw localThrowable;
          }
        }
        catch (Exception localException)
        {
          for (;;) {}
        }
      }
    });
    localReentrantLock.lock();
    label109:
    do
    {
      try
      {
        bool = localAtomicBoolean.get();
        if (!bool)
        {
          paramCallable = localAtomicReference.get();
          localReentrantLock.unlock();
          return paramCallable;
        }
        l1 = TimeUnit.MILLISECONDS.toNanos(paramInt);
      }
      catch (Throwable paramCallable)
      {
        boolean bool;
        long l1;
        long l2;
        localReentrantLock.unlock();
        throw paramCallable;
      }
      try
      {
        l2 = localCondition.awaitNanos(l1);
        l1 = l2;
      }
      catch (InterruptedException paramCallable)
      {
        break label109;
      }
      bool = localAtomicBoolean.get();
      if (!bool)
      {
        paramCallable = localAtomicReference.get();
        localReentrantLock.unlock();
        return paramCallable;
      }
    } while (l1 > 0L);
    throw new InterruptedException("timeout");
  }
  
  public static abstract interface ReplyCallback<T>
  {
    public abstract void onReply(Object paramObject);
  }
}
