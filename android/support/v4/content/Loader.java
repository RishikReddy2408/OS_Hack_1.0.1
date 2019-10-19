package android.support.v4.content;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.support.v4.util.DebugUtils;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class Loader<D>
{
  boolean mAbandoned = false;
  boolean mContentChanged = false;
  Context mContext;
  int mId;
  OnLoadCompleteListener<D> mListener;
  OnLoadCanceledListener<D> mOnLoadCanceledListener;
  boolean mProcessingChange = false;
  boolean mReset = true;
  boolean mStarted = false;
  
  public Loader(Context paramContext)
  {
    mContext = paramContext.getApplicationContext();
  }
  
  public void abandon()
  {
    mAbandoned = true;
    onAbandon();
  }
  
  public boolean cancelLoad()
  {
    return onCancelLoad();
  }
  
  public void commitContentChanged()
  {
    mProcessingChange = false;
  }
  
  public String dataToString(Object paramObject)
  {
    StringBuilder localStringBuilder = new StringBuilder(64);
    DebugUtils.buildShortClassTag(paramObject, localStringBuilder);
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }
  
  public void deliverCancellation()
  {
    if (mOnLoadCanceledListener != null) {
      mOnLoadCanceledListener.onLoadCanceled(this);
    }
  }
  
  public void deliverResult(Object paramObject)
  {
    if (mListener != null) {
      mListener.onLoadComplete(this, paramObject);
    }
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mId=");
    paramPrintWriter.print(mId);
    paramPrintWriter.print(" mListener=");
    paramPrintWriter.println(mListener);
    if ((mStarted) || (mContentChanged) || (mProcessingChange))
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mStarted=");
      paramPrintWriter.print(mStarted);
      paramPrintWriter.print(" mContentChanged=");
      paramPrintWriter.print(mContentChanged);
      paramPrintWriter.print(" mProcessingChange=");
      paramPrintWriter.println(mProcessingChange);
    }
    if ((mAbandoned) || (mReset))
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mAbandoned=");
      paramPrintWriter.print(mAbandoned);
      paramPrintWriter.print(" mReset=");
      paramPrintWriter.println(mReset);
    }
  }
  
  public void forceLoad()
  {
    onForceLoad();
  }
  
  public Context getContext()
  {
    return mContext;
  }
  
  public int getId()
  {
    return mId;
  }
  
  public boolean isAbandoned()
  {
    return mAbandoned;
  }
  
  public boolean isReset()
  {
    return mReset;
  }
  
  public boolean isStarted()
  {
    return mStarted;
  }
  
  protected void onAbandon() {}
  
  protected boolean onCancelLoad()
  {
    return false;
  }
  
  public void onContentChanged()
  {
    if (mStarted)
    {
      forceLoad();
      return;
    }
    mContentChanged = true;
  }
  
  protected void onForceLoad() {}
  
  protected void onReset() {}
  
  protected void onStartLoading() {}
  
  protected void onStopLoading() {}
  
  public void registerListener(int paramInt, OnLoadCompleteListener paramOnLoadCompleteListener)
  {
    if (mListener == null)
    {
      mListener = paramOnLoadCompleteListener;
      mId = paramInt;
      return;
    }
    throw new IllegalStateException("There is already a listener registered");
  }
  
  public void registerOnLoadCanceledListener(OnLoadCanceledListener paramOnLoadCanceledListener)
  {
    if (mOnLoadCanceledListener == null)
    {
      mOnLoadCanceledListener = paramOnLoadCanceledListener;
      return;
    }
    throw new IllegalStateException("There is already a listener registered");
  }
  
  public void reset()
  {
    onReset();
    mReset = true;
    mStarted = false;
    mAbandoned = false;
    mContentChanged = false;
    mProcessingChange = false;
  }
  
  public void rollbackContentChanged()
  {
    if (mProcessingChange) {
      onContentChanged();
    }
  }
  
  public final void startLoading()
  {
    mStarted = true;
    mReset = false;
    mAbandoned = false;
    onStartLoading();
  }
  
  public void stopLoading()
  {
    mStarted = false;
    onStopLoading();
  }
  
  public boolean takeContentChanged()
  {
    boolean bool = mContentChanged;
    mContentChanged = false;
    mProcessingChange |= bool;
    return bool;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(64);
    DebugUtils.buildShortClassTag(this, localStringBuilder);
    localStringBuilder.append(" id=");
    localStringBuilder.append(mId);
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }
  
  public void unregisterListener(OnLoadCompleteListener paramOnLoadCompleteListener)
  {
    if (mListener != null)
    {
      if (mListener == paramOnLoadCompleteListener)
      {
        mListener = null;
        return;
      }
      throw new IllegalArgumentException("Attempting to unregister the wrong listener");
    }
    throw new IllegalStateException("No listener register");
  }
  
  public void unregisterOnLoadCanceledListener(OnLoadCanceledListener paramOnLoadCanceledListener)
  {
    if (mOnLoadCanceledListener != null)
    {
      if (mOnLoadCanceledListener == paramOnLoadCanceledListener)
      {
        mOnLoadCanceledListener = null;
        return;
      }
      throw new IllegalArgumentException("Attempting to unregister the wrong listener");
    }
    throw new IllegalStateException("No listener register");
  }
  
  public final class ForceLoadContentObserver
    extends ContentObserver
  {
    public ForceLoadContentObserver()
    {
      super();
    }
    
    public boolean deliverSelfNotifications()
    {
      return true;
    }
    
    public void onChange(boolean paramBoolean)
    {
      onContentChanged();
    }
  }
  
  public static abstract interface OnLoadCanceledListener<D>
  {
    public abstract void onLoadCanceled(Loader paramLoader);
  }
  
  public static abstract interface OnLoadCompleteListener<D>
  {
    public abstract void onLoadComplete(Loader paramLoader, Object paramObject);
  }
}
