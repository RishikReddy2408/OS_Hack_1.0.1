package android.support.v4.package_7;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProvider.Factory;
import android.arch.lifecycle.ViewModelStore;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;
import android.support.v4.content.Loader.OnLoadCompleteListener;
import android.support.v4.util.DebugUtils;
import android.support.v4.util.SparseArrayCompat;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;

class LoaderManagerImpl
  extends LoaderManager
{
  static boolean DEBUG = false;
  static final String TAG = "LoaderManager";
  private boolean mCreatingLoader;
  @NonNull
  private final LifecycleOwner mLifecycleOwner;
  @NonNull
  private final LoaderViewModel mLoaderViewModel;
  
  LoaderManagerImpl(LifecycleOwner paramLifecycleOwner, ViewModelStore paramViewModelStore)
  {
    mLifecycleOwner = paramLifecycleOwner;
    mLoaderViewModel = LoaderViewModel.getInstance(paramViewModelStore);
  }
  
  private Loader createAndInstallLoader(int paramInt, Bundle paramBundle, LoaderManager.LoaderCallbacks paramLoaderCallbacks, Loader paramLoader)
  {
    try
    {
      mCreatingLoader = true;
      Loader localLoader = paramLoaderCallbacks.onCreateLoader(paramInt, paramBundle);
      boolean bool = localLoader.getClass().isMemberClass();
      if (bool)
      {
        bool = Modifier.isStatic(localLoader.getClass().getModifiers());
        if (!bool)
        {
          paramBundle = new StringBuilder();
          paramBundle.append("Object returned from onCreateLoader must not be a non-static inner member class: ");
          paramBundle.append(localLoader);
          throw new IllegalArgumentException(paramBundle.toString());
        }
      }
      paramBundle = new LoaderInfo(paramInt, paramBundle, localLoader, paramLoader);
      bool = DEBUG;
      if (bool)
      {
        paramLoader = new StringBuilder();
        paramLoader.append("  Created new loader ");
        paramLoader.append(paramBundle);
        Log.v("LoaderManager", paramLoader.toString());
      }
      mLoaderViewModel.putLoader(paramInt, paramBundle);
      mCreatingLoader = false;
      return paramBundle.setCallback(mLifecycleOwner, paramLoaderCallbacks);
    }
    catch (Throwable paramBundle)
    {
      mCreatingLoader = false;
      throw paramBundle;
    }
  }
  
  public void destroyLoader(int paramInt)
  {
    if (!mCreatingLoader)
    {
      if (Looper.getMainLooper() == Looper.myLooper())
      {
        if (DEBUG)
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("destroyLoader in ");
          ((StringBuilder)localObject).append(this);
          ((StringBuilder)localObject).append(" of ");
          ((StringBuilder)localObject).append(paramInt);
          Log.v("LoaderManager", ((StringBuilder)localObject).toString());
        }
        Object localObject = mLoaderViewModel.getLoader(paramInt);
        if (localObject != null)
        {
          ((LoaderInfo)localObject).destroy(true);
          mLoaderViewModel.removeLoader(paramInt);
        }
      }
      else
      {
        throw new IllegalStateException("destroyLoader must be called on the main thread");
      }
    }
    else {
      throw new IllegalStateException("Called while creating a loader");
    }
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    mLoaderViewModel.dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
  }
  
  public Loader getLoader(int paramInt)
  {
    if (!mCreatingLoader)
    {
      LoaderInfo localLoaderInfo = mLoaderViewModel.getLoader(paramInt);
      if (localLoaderInfo != null) {
        return localLoaderInfo.getLoader();
      }
      return null;
    }
    throw new IllegalStateException("Called while creating a loader");
  }
  
  public boolean hasRunningLoaders()
  {
    return mLoaderViewModel.hasRunningLoaders();
  }
  
  public Loader initLoader(int paramInt, Bundle paramBundle, LoaderManager.LoaderCallbacks paramLoaderCallbacks)
  {
    if (!mCreatingLoader)
    {
      if (Looper.getMainLooper() == Looper.myLooper())
      {
        LoaderInfo localLoaderInfo = mLoaderViewModel.getLoader(paramInt);
        if (DEBUG)
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("initLoader in ");
          localStringBuilder.append(this);
          localStringBuilder.append(": args=");
          localStringBuilder.append(paramBundle);
          Log.v("LoaderManager", localStringBuilder.toString());
        }
        if (localLoaderInfo == null) {
          return createAndInstallLoader(paramInt, paramBundle, paramLoaderCallbacks, null);
        }
        if (DEBUG)
        {
          paramBundle = new StringBuilder();
          paramBundle.append("  Re-using existing loader ");
          paramBundle.append(localLoaderInfo);
          Log.v("LoaderManager", paramBundle.toString());
        }
        return localLoaderInfo.setCallback(mLifecycleOwner, paramLoaderCallbacks);
      }
      throw new IllegalStateException("initLoader must be called on the main thread");
    }
    throw new IllegalStateException("Called while creating a loader");
  }
  
  void markForRedelivery()
  {
    mLoaderViewModel.markForRedelivery();
  }
  
  public Loader restartLoader(int paramInt, Bundle paramBundle, LoaderManager.LoaderCallbacks paramLoaderCallbacks)
  {
    if (!mCreatingLoader)
    {
      if (Looper.getMainLooper() == Looper.myLooper())
      {
        if (DEBUG)
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("restartLoader in ");
          ((StringBuilder)localObject).append(this);
          ((StringBuilder)localObject).append(": args=");
          ((StringBuilder)localObject).append(paramBundle);
          Log.v("LoaderManager", ((StringBuilder)localObject).toString());
        }
        LoaderInfo localLoaderInfo = mLoaderViewModel.getLoader(paramInt);
        Object localObject = null;
        if (localLoaderInfo != null) {
          localObject = localLoaderInfo.destroy(false);
        }
        return createAndInstallLoader(paramInt, paramBundle, paramLoaderCallbacks, (Loader)localObject);
      }
      throw new IllegalStateException("restartLoader must be called on the main thread");
    }
    throw new IllegalStateException("Called while creating a loader");
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(128);
    localStringBuilder.append("LoaderManager{");
    localStringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    localStringBuilder.append(" in ");
    DebugUtils.buildShortClassTag(mLifecycleOwner, localStringBuilder);
    localStringBuilder.append("}}");
    return localStringBuilder.toString();
  }
  
  public class LoaderInfo<D>
    extends MutableLiveData<D>
    implements Loader.OnLoadCompleteListener<D>
  {
    @Nullable
    private final Bundle mArgs;
    private final int mId;
    private LifecycleOwner mLifecycleOwner;
    @NonNull
    private final Loader<D> mLoader;
    private android.support.v4.app.LoaderManagerImpl.LoaderObserver<D> mObserver;
    private Loader<D> mPriorLoader;
    
    LoaderInfo(Bundle paramBundle, Loader paramLoader1, Loader paramLoader2)
    {
      mId = this$1;
      mArgs = paramBundle;
      mLoader = paramLoader1;
      mPriorLoader = paramLoader2;
      mLoader.registerListener(this$1, this);
    }
    
    Loader destroy(boolean paramBoolean)
    {
      if (LoaderManagerImpl.DEBUG)
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("  Destroying: ");
        ((StringBuilder)localObject).append(this);
        Log.v("LoaderManager", ((StringBuilder)localObject).toString());
      }
      mLoader.cancelLoad();
      mLoader.abandon();
      Object localObject = mObserver;
      if (localObject != null)
      {
        removeObserver((Observer)localObject);
        if (paramBoolean) {
          ((LoaderManagerImpl.LoaderObserver)localObject).reset();
        }
      }
      mLoader.unregisterListener(this);
      if (((localObject != null) && (!((LoaderManagerImpl.LoaderObserver)localObject).hasDeliveredData())) || (paramBoolean))
      {
        mLoader.reset();
        return mPriorLoader;
      }
      return mLoader;
    }
    
    public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mId=");
      paramPrintWriter.print(mId);
      paramPrintWriter.print(" mArgs=");
      paramPrintWriter.println(mArgs);
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mLoader=");
      paramPrintWriter.println(mLoader);
      Loader localLoader = mLoader;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString);
      localStringBuilder.append("  ");
      localLoader.dump(localStringBuilder.toString(), paramFileDescriptor, paramPrintWriter, paramArrayOfString);
      if (mObserver != null)
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("mCallbacks=");
        paramPrintWriter.println(mObserver);
        paramFileDescriptor = mObserver;
        paramArrayOfString = new StringBuilder();
        paramArrayOfString.append(paramString);
        paramArrayOfString.append("  ");
        paramFileDescriptor.dump(paramArrayOfString.toString(), paramPrintWriter);
      }
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mData=");
      paramPrintWriter.println(getLoader().dataToString(getValue()));
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mStarted=");
      paramPrintWriter.println(hasActiveObservers());
    }
    
    Loader getLoader()
    {
      return mLoader;
    }
    
    boolean isCallbackWaitingForData()
    {
      if (!hasActiveObservers()) {
        return false;
      }
      return (mObserver != null) && (!mObserver.hasDeliveredData());
    }
    
    void markForRedelivery()
    {
      LifecycleOwner localLifecycleOwner = mLifecycleOwner;
      LoaderManagerImpl.LoaderObserver localLoaderObserver = mObserver;
      if ((localLifecycleOwner != null) && (localLoaderObserver != null))
      {
        super.removeObserver(localLoaderObserver);
        observe(localLifecycleOwner, localLoaderObserver);
      }
    }
    
    protected void onActive()
    {
      if (LoaderManagerImpl.DEBUG)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("  Starting: ");
        localStringBuilder.append(this);
        Log.v("LoaderManager", localStringBuilder.toString());
      }
      mLoader.startLoading();
    }
    
    protected void onInactive()
    {
      if (LoaderManagerImpl.DEBUG)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("  Stopping: ");
        localStringBuilder.append(this);
        Log.v("LoaderManager", localStringBuilder.toString());
      }
      mLoader.stopLoading();
    }
    
    public void onLoadComplete(Loader paramLoader, Object paramObject)
    {
      if (LoaderManagerImpl.DEBUG)
      {
        paramLoader = new StringBuilder();
        paramLoader.append("onLoadComplete: ");
        paramLoader.append(this);
        Log.v("LoaderManager", paramLoader.toString());
      }
      if (Looper.myLooper() == Looper.getMainLooper())
      {
        setValue(paramObject);
        return;
      }
      if (LoaderManagerImpl.DEBUG) {
        Log.w("LoaderManager", "onLoadComplete was incorrectly called on a background thread");
      }
      postValue(paramObject);
    }
    
    public void removeObserver(Observer paramObserver)
    {
      super.removeObserver(paramObserver);
      mLifecycleOwner = null;
      mObserver = null;
    }
    
    Loader setCallback(LifecycleOwner paramLifecycleOwner, LoaderManager.LoaderCallbacks paramLoaderCallbacks)
    {
      paramLoaderCallbacks = new LoaderManagerImpl.LoaderObserver(mLoader, paramLoaderCallbacks);
      observe(paramLifecycleOwner, paramLoaderCallbacks);
      if (mObserver != null) {
        removeObserver(mObserver);
      }
      mLifecycleOwner = paramLifecycleOwner;
      mObserver = paramLoaderCallbacks;
      return mLoader;
    }
    
    public void setValue(Object paramObject)
    {
      super.setValue(paramObject);
      if (mPriorLoader != null)
      {
        mPriorLoader.reset();
        mPriorLoader = null;
      }
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder(64);
      localStringBuilder.append("LoaderInfo{");
      localStringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
      localStringBuilder.append(" #");
      localStringBuilder.append(mId);
      localStringBuilder.append(" : ");
      DebugUtils.buildShortClassTag(mLoader, localStringBuilder);
      localStringBuilder.append("}}");
      return localStringBuilder.toString();
    }
  }
  
  class LoaderObserver<D>
    implements Observer<D>
  {
    @NonNull
    private final android.support.v4.app.LoaderManager.LoaderCallbacks<D> mCallback;
    private boolean mDeliveredData = false;
    
    LoaderObserver(LoaderManager.LoaderCallbacks paramLoaderCallbacks)
    {
      mCallback = paramLoaderCallbacks;
    }
    
    public void dump(String paramString, PrintWriter paramPrintWriter)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mDeliveredData=");
      paramPrintWriter.println(mDeliveredData);
    }
    
    boolean hasDeliveredData()
    {
      return mDeliveredData;
    }
    
    public void onChanged(Object paramObject)
    {
      if (LoaderManagerImpl.DEBUG)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("  onLoadFinished in ");
        localStringBuilder.append(LoaderManagerImpl.this);
        localStringBuilder.append(": ");
        localStringBuilder.append(dataToString(paramObject));
        Log.v("LoaderManager", localStringBuilder.toString());
      }
      mCallback.onLoadFinished(LoaderManagerImpl.this, paramObject);
      mDeliveredData = true;
    }
    
    void reset()
    {
      if (mDeliveredData)
      {
        if (LoaderManagerImpl.DEBUG)
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("  Resetting: ");
          localStringBuilder.append(LoaderManagerImpl.this);
          Log.v("LoaderManager", localStringBuilder.toString());
        }
        mCallback.onLoaderReset(LoaderManagerImpl.this);
      }
    }
    
    public String toString()
    {
      return mCallback.toString();
    }
  }
  
  class LoaderViewModel
    extends ViewModel
  {
    private static final ViewModelProvider.Factory FACTORY = new ViewModelProvider.Factory()
    {
      public ViewModel create(Class paramAnonymousClass)
      {
        return new LoaderManagerImpl.LoaderViewModel();
      }
    };
    private SparseArrayCompat<android.support.v4.app.LoaderManagerImpl.LoaderInfo> mLoaders = new SparseArrayCompat();
    
    LoaderViewModel() {}
    
    static LoaderViewModel getInstance(ViewModelStore paramViewModelStore)
    {
      return (LoaderViewModel)new ViewModelProvider(paramViewModelStore, FACTORY).getTag(android.support.v4.app.LoaderManagerImpl.LoaderViewModel.class);
    }
    
    public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
    {
      if (mLoaders.size() > 0)
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.println("Loaders:");
        Object localObject = new StringBuilder();
        ((StringBuilder)localObject).append(paramString);
        ((StringBuilder)localObject).append("    ");
        localObject = ((StringBuilder)localObject).toString();
        int i = 0;
        while (i < mLoaders.size())
        {
          LoaderManagerImpl.LoaderInfo localLoaderInfo = (LoaderManagerImpl.LoaderInfo)mLoaders.valueAt(i);
          paramPrintWriter.print(paramString);
          paramPrintWriter.print("  #");
          paramPrintWriter.print(mLoaders.keyAt(i));
          paramPrintWriter.print(": ");
          paramPrintWriter.println(localLoaderInfo.toString());
          localLoaderInfo.dump((String)localObject, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
          i += 1;
        }
      }
    }
    
    LoaderManagerImpl.LoaderInfo getLoader(int paramInt)
    {
      return (LoaderManagerImpl.LoaderInfo)mLoaders.get(paramInt);
    }
    
    boolean hasRunningLoaders()
    {
      int j = mLoaders.size();
      int i = 0;
      while (i < j)
      {
        if (((LoaderManagerImpl.LoaderInfo)mLoaders.valueAt(i)).isCallbackWaitingForData()) {
          return true;
        }
        i += 1;
      }
      return false;
    }
    
    void markForRedelivery()
    {
      int j = mLoaders.size();
      int i = 0;
      while (i < j)
      {
        ((LoaderManagerImpl.LoaderInfo)mLoaders.valueAt(i)).markForRedelivery();
        i += 1;
      }
    }
    
    protected void onCleared()
    {
      super.onCleared();
      int j = mLoaders.size();
      int i = 0;
      while (i < j)
      {
        ((LoaderManagerImpl.LoaderInfo)mLoaders.valueAt(i)).destroy(true);
        i += 1;
      }
      mLoaders.clear();
    }
    
    void putLoader(int paramInt, LoaderManagerImpl.LoaderInfo paramLoaderInfo)
    {
      mLoaders.put(paramInt, paramLoaderInfo);
    }
    
    void removeLoader(int paramInt)
    {
      mLoaders.remove(paramInt);
    }
  }
}
