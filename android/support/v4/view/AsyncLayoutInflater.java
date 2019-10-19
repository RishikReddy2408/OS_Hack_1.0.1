package android.support.v4.view;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.util.Pools.SynchronizedPool;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.concurrent.ArrayBlockingQueue;

public final class AsyncLayoutInflater
{
  private static final String PAGE_KEY = "AsyncLayoutInflater";
  Handler mHandler;
  private Handler.Callback mHandlerCallback = new Handler.Callback()
  {
    public boolean handleMessage(Message paramAnonymousMessage)
    {
      paramAnonymousMessage = (AsyncLayoutInflater.InflateRequest)obj;
      if (view == null) {
        view = mInflater.inflate(resid, parent, false);
      }
      callback.onInflateFinished(view, resid, parent);
      mInflateThread.releaseRequest(paramAnonymousMessage);
      return true;
    }
  };
  InflateThread mInflateThread;
  LayoutInflater mInflater;
  
  public AsyncLayoutInflater(Context paramContext)
  {
    mInflater = new BasicInflater(paramContext);
    mHandler = new Handler(mHandlerCallback);
    mInflateThread = InflateThread.getInstance();
  }
  
  public void inflate(int paramInt, ViewGroup paramViewGroup, OnInflateFinishedListener paramOnInflateFinishedListener)
  {
    if (paramOnInflateFinishedListener != null)
    {
      InflateRequest localInflateRequest = mInflateThread.obtainRequest();
      inflater = this;
      resid = paramInt;
      parent = paramViewGroup;
      callback = paramOnInflateFinishedListener;
      mInflateThread.enqueue(localInflateRequest);
      return;
    }
    throw new NullPointerException("callback argument may not be null!");
  }
  
  private static class BasicInflater
    extends LayoutInflater
  {
    private static final String[] sClassPrefixList = { "android.widget.", "android.webkit.", "android.app." };
    
    BasicInflater(Context paramContext)
    {
      super();
    }
    
    public LayoutInflater cloneInContext(Context paramContext)
    {
      return new BasicInflater(paramContext);
    }
    
    protected View onCreateView(String paramString, AttributeSet paramAttributeSet)
      throws ClassNotFoundException
    {
      String[] arrayOfString = sClassPrefixList;
      int j = arrayOfString.length;
      int i = 0;
      while (i < j)
      {
        Object localObject = arrayOfString[i];
        try
        {
          localObject = createView(paramString, (String)localObject, paramAttributeSet);
          if (localObject != null) {
            return localObject;
          }
        }
        catch (ClassNotFoundException localClassNotFoundException)
        {
          for (;;) {}
        }
        i += 1;
      }
      return super.onCreateView(paramString, paramAttributeSet);
    }
  }
  
  private static class InflateRequest
  {
    AsyncLayoutInflater.OnInflateFinishedListener callback;
    AsyncLayoutInflater inflater;
    ViewGroup parent;
    int resid;
    View view;
    
    InflateRequest() {}
  }
  
  private static class InflateThread
    extends Thread
  {
    private static final InflateThread sInstance = new InflateThread();
    private ArrayBlockingQueue<AsyncLayoutInflater.InflateRequest> mQueue = new ArrayBlockingQueue(10);
    private Pools.SynchronizedPool<AsyncLayoutInflater.InflateRequest> mRequestPool = new Pools.SynchronizedPool(10);
    
    static
    {
      sInstance.start();
    }
    
    private InflateThread() {}
    
    public static InflateThread getInstance()
    {
      return sInstance;
    }
    
    public void enqueue(AsyncLayoutInflater.InflateRequest paramInflateRequest)
    {
      ArrayBlockingQueue localArrayBlockingQueue = mQueue;
      try
      {
        localArrayBlockingQueue.put(paramInflateRequest);
        return;
      }
      catch (InterruptedException paramInflateRequest)
      {
        throw new RuntimeException("Failed to enqueue async inflate request", paramInflateRequest);
      }
    }
    
    public AsyncLayoutInflater.InflateRequest obtainRequest()
    {
      AsyncLayoutInflater.InflateRequest localInflateRequest2 = (AsyncLayoutInflater.InflateRequest)mRequestPool.acquire();
      AsyncLayoutInflater.InflateRequest localInflateRequest1 = localInflateRequest2;
      if (localInflateRequest2 == null) {
        localInflateRequest1 = new AsyncLayoutInflater.InflateRequest();
      }
      return localInflateRequest1;
    }
    
    public void releaseRequest(AsyncLayoutInflater.InflateRequest paramInflateRequest)
    {
      callback = null;
      inflater = null;
      parent = null;
      resid = 0;
      view = null;
      mRequestPool.release(paramInflateRequest);
    }
    
    public void run()
    {
      for (;;)
      {
        runInner();
      }
    }
    
    public void runInner()
    {
      Object localObject = mQueue;
      try
      {
        localObject = ((ArrayBlockingQueue)localObject).take();
        localObject = (AsyncLayoutInflater.InflateRequest)localObject;
        try
        {
          view = inflater.mInflater.inflate(resid, parent, false);
        }
        catch (RuntimeException localRuntimeException)
        {
          Log.w("AsyncLayoutInflater", "Failed to inflate resource in the background! Retrying on the UI thread", localRuntimeException);
        }
        Message.obtain(inflater.mHandler, 0, localObject).sendToTarget();
        return;
      }
      catch (InterruptedException localInterruptedException)
      {
        Log.w("AsyncLayoutInflater", localInterruptedException);
      }
    }
  }
  
  public static abstract interface OnInflateFinishedListener
  {
    public abstract void onInflateFinished(View paramView, int paramInt, ViewGroup paramViewGroup);
  }
}
