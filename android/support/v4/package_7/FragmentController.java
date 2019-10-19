package android.support.v4.package_7;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.support.v4.util.SimpleArrayMap;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

public class FragmentController
{
  private final android.support.v4.app.FragmentHostCallback<?> mHost;
  
  private FragmentController(FragmentHostCallback paramFragmentHostCallback)
  {
    mHost = paramFragmentHostCallback;
  }
  
  public static FragmentController createController(FragmentHostCallback paramFragmentHostCallback)
  {
    return new FragmentController(paramFragmentHostCallback);
  }
  
  public void attachHost(Fragment paramFragment)
  {
    mHost.mFragmentManager.attachController(mHost, mHost, paramFragment);
  }
  
  public void dispatchActivityCreated()
  {
    mHost.mFragmentManager.dispatchActivityCreated();
  }
  
  public void dispatchConfigurationChanged(Configuration paramConfiguration)
  {
    mHost.mFragmentManager.dispatchConfigurationChanged(paramConfiguration);
  }
  
  public boolean dispatchContextItemSelected(MenuItem paramMenuItem)
  {
    return mHost.mFragmentManager.dispatchContextItemSelected(paramMenuItem);
  }
  
  public void dispatchCreate()
  {
    mHost.mFragmentManager.dispatchCreate();
  }
  
  public boolean dispatchCreateOptionsMenu(Menu paramMenu, MenuInflater paramMenuInflater)
  {
    return mHost.mFragmentManager.dispatchCreateOptionsMenu(paramMenu, paramMenuInflater);
  }
  
  public void dispatchDestroy()
  {
    mHost.mFragmentManager.dispatchDestroy();
  }
  
  public void dispatchDestroyView()
  {
    mHost.mFragmentManager.dispatchDestroyView();
  }
  
  public void dispatchLowMemory()
  {
    mHost.mFragmentManager.dispatchLowMemory();
  }
  
  public void dispatchMultiWindowModeChanged(boolean paramBoolean)
  {
    mHost.mFragmentManager.dispatchMultiWindowModeChanged(paramBoolean);
  }
  
  public boolean dispatchOptionsItemSelected(MenuItem paramMenuItem)
  {
    return mHost.mFragmentManager.dispatchOptionsItemSelected(paramMenuItem);
  }
  
  public void dispatchOptionsMenuClosed(Menu paramMenu)
  {
    mHost.mFragmentManager.dispatchOptionsMenuClosed(paramMenu);
  }
  
  public void dispatchPause()
  {
    mHost.mFragmentManager.dispatchPause();
  }
  
  public void dispatchPictureInPictureModeChanged(boolean paramBoolean)
  {
    mHost.mFragmentManager.dispatchPictureInPictureModeChanged(paramBoolean);
  }
  
  public boolean dispatchPrepareOptionsMenu(Menu paramMenu)
  {
    return mHost.mFragmentManager.dispatchPrepareOptionsMenu(paramMenu);
  }
  
  public void dispatchReallyStop()
  {
    mHost.mFragmentManager.dispatchReallyStop();
  }
  
  public void dispatchResume()
  {
    mHost.mFragmentManager.dispatchResume();
  }
  
  public void dispatchStart()
  {
    mHost.mFragmentManager.dispatchStart();
  }
  
  public void dispatchStop()
  {
    mHost.mFragmentManager.dispatchStop();
  }
  
  public void doLoaderDestroy() {}
  
  public void doLoaderRetain() {}
  
  public void doLoaderStart() {}
  
  public void doLoaderStop(boolean paramBoolean) {}
  
  public void dumpLoaders(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {}
  
  public boolean execPendingActions()
  {
    return mHost.mFragmentManager.execPendingActions();
  }
  
  public Fragment findFragmentByWho(String paramString)
  {
    return mHost.mFragmentManager.findFragmentByWho(paramString);
  }
  
  public List getActiveFragments(List paramList)
  {
    return mHost.mFragmentManager.getActiveFragments();
  }
  
  public int getActiveFragmentsCount()
  {
    return mHost.mFragmentManager.getActiveFragmentCount();
  }
  
  public FragmentManager getSupportFragmentManager()
  {
    return mHost.getFragmentManagerImpl();
  }
  
  public LoaderManager getSupportLoaderManager()
  {
    return null;
  }
  
  public void noteStateNotSaved()
  {
    mHost.mFragmentManager.noteStateNotSaved();
  }
  
  public View onCreateView(View paramView, String paramString, Context paramContext, AttributeSet paramAttributeSet)
  {
    return mHost.mFragmentManager.onCreateView(paramView, paramString, paramContext, paramAttributeSet);
  }
  
  public void reportLoaderStart() {}
  
  public void restoreAllState(Parcelable paramParcelable, FragmentManagerNonConfig paramFragmentManagerNonConfig)
  {
    mHost.mFragmentManager.restoreAllState(paramParcelable, paramFragmentManagerNonConfig);
  }
  
  public void restoreAllState(Parcelable paramParcelable, List paramList)
  {
    mHost.mFragmentManager.restoreAllState(paramParcelable, new FragmentManagerNonConfig(paramList, null, null));
  }
  
  public void restoreLoaderNonConfig(SimpleArrayMap paramSimpleArrayMap) {}
  
  public SimpleArrayMap retainLoaderNonConfig()
  {
    return null;
  }
  
  public FragmentManagerNonConfig retainNestedNonConfig()
  {
    return mHost.mFragmentManager.retainNonConfig();
  }
  
  public List retainNonConfig()
  {
    FragmentManagerNonConfig localFragmentManagerNonConfig = mHost.mFragmentManager.retainNonConfig();
    if (localFragmentManagerNonConfig != null) {
      return localFragmentManagerNonConfig.getFragments();
    }
    return null;
  }
  
  public Parcelable saveAllState()
  {
    return mHost.mFragmentManager.saveAllState();
  }
}
