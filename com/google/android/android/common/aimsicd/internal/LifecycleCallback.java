package com.google.android.android.common.aimsicd.internal;

import android.app.Activity;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.io.FileDescriptor;
import java.io.PrintWriter;

@KeepForSdk
public class LifecycleCallback
{
  @KeepForSdk
  protected final LifecycleFragment mLifecycleFragment;
  
  protected LifecycleCallback(LifecycleFragment paramLifecycleFragment)
  {
    mLifecycleFragment = paramLifecycleFragment;
  }
  
  private static LifecycleFragment getChimeraLifecycleFragmentImpl(LifecycleActivity paramLifecycleActivity)
  {
    throw new IllegalStateException("Method not available in SDK.");
  }
  
  public static LifecycleFragment getFragment(Activity paramActivity)
  {
    return getFragment(new LifecycleActivity(paramActivity));
  }
  
  public static LifecycleFragment getFragment(ContextWrapper paramContextWrapper)
  {
    throw new UnsupportedOperationException();
  }
  
  protected static LifecycleFragment getFragment(LifecycleActivity paramLifecycleActivity)
  {
    if (paramLifecycleActivity.isSupport()) {
      return Fragment.getFragment(paramLifecycleActivity.asFragmentActivity());
    }
    if (paramLifecycleActivity.join()) {
      return BrowserActivity.onPostExecute(paramLifecycleActivity.asActivity());
    }
    throw new IllegalArgumentException("Can't get fragment for unexpected activity.");
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {}
  
  public Activity getActivity()
  {
    return mLifecycleFragment.getLifecycleActivity();
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {}
  
  public void onCreate(Bundle paramBundle) {}
  
  public void onDestroy() {}
  
  public void onResume() {}
  
  public void onSaveInstanceState(Bundle paramBundle) {}
  
  public void onStart() {}
  
  public void onStop() {}
}
