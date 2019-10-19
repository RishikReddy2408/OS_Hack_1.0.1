package android.support.v4.package_7;

import android.os.Bundle;
import android.support.v4.content.Loader;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public abstract class LoaderManager
{
  public LoaderManager() {}
  
  public static void enableDebugLogging(boolean paramBoolean)
  {
    LoaderManagerImpl.DEBUG = paramBoolean;
  }
  
  public abstract void destroyLoader(int paramInt);
  
  public abstract void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString);
  
  public abstract Loader getLoader(int paramInt);
  
  public boolean hasRunningLoaders()
  {
    return false;
  }
  
  public abstract Loader initLoader(int paramInt, Bundle paramBundle, LoaderCallbacks paramLoaderCallbacks);
  
  public abstract Loader restartLoader(int paramInt, Bundle paramBundle, LoaderCallbacks paramLoaderCallbacks);
  
  public abstract interface LoaderCallbacks<D>
  {
    public abstract Loader onCreateLoader(int paramInt, Bundle paramBundle);
    
    public abstract void onLoadFinished(Loader paramLoader, Object paramObject);
    
    public abstract void onLoaderReset(Loader paramLoader);
  }
}
