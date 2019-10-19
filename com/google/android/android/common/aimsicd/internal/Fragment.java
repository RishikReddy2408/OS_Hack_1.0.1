package com.google.android.android.common.aimsicd.internal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.package_7.FragmentManager;
import android.support.v4.package_7.FragmentTransaction;
import android.support.v4.util.ArrayMap;
import com.google.android.android.internal.common.Timer;
import com.google.android.gms.common.api.internal.zzc;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.WeakHashMap;

public final class Fragment
  extends android.support.v4.package_7.Fragment
  implements LifecycleFragment
{
  private static WeakHashMap<android.support.v4.app.FragmentActivity, WeakReference<zzc>> zzbd = new WeakHashMap();
  private Map<String, com.google.android.gms.common.api.internal.LifecycleCallback> zzbe = new ArrayMap();
  private int zzbf = 0;
  private Bundle zzbg;
  
  public Fragment() {}
  
  public static Fragment getFragment(android.support.v4.package_7.FragmentActivity paramFragmentActivity)
  {
    Object localObject = (WeakReference)zzbd.get(paramFragmentActivity);
    if (localObject != null)
    {
      localObject = (Fragment)((WeakReference)localObject).get();
      if (localObject != null) {
        return localObject;
      }
    }
    try
    {
      Fragment localFragment = (Fragment)paramFragmentActivity.getSupportFragmentManager().findFragmentByTag("SupportLifecycleFragmentImpl");
      if (localFragment != null)
      {
        localObject = localFragment;
        if (!localFragment.isRemoving()) {}
      }
      else
      {
        localObject = new Fragment();
        paramFragmentActivity.getSupportFragmentManager().beginTransaction().add((android.support.v4.package_7.Fragment)localObject, "SupportLifecycleFragmentImpl").commitAllowingStateLoss();
      }
      zzbd.put(paramFragmentActivity, new WeakReference(localObject));
      return localObject;
    }
    catch (ClassCastException paramFragmentActivity)
    {
      throw new IllegalStateException("Fragment with tag SupportLifecycleFragmentImpl is not a SupportLifecycleFragmentImpl", paramFragmentActivity);
    }
  }
  
  public final void addCallback(String paramString, LifecycleCallback paramLifecycleCallback)
  {
    if (!zzbe.containsKey(paramString))
    {
      zzbe.put(paramString, paramLifecycleCallback);
      if (zzbf > 0) {
        new Timer(Looper.getMainLooper()).post(new IonBitmapRequestBuilder.1(this, paramLifecycleCallback, paramString));
      }
    }
    else
    {
      paramLifecycleCallback = new StringBuilder(String.valueOf(paramString).length() + 59);
      paramLifecycleCallback.append("LifecycleCallback with tag ");
      paramLifecycleCallback.append(paramString);
      paramLifecycleCallback.append(" already added to this fragment.");
      throw new IllegalArgumentException(paramLifecycleCallback.toString());
    }
  }
  
  public final void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    super.dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    Iterator localIterator = zzbe.values().iterator();
    while (localIterator.hasNext()) {
      ((LifecycleCallback)localIterator.next()).dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    }
  }
  
  public final LifecycleCallback getCallbackOrNull(String paramString, Class paramClass)
  {
    return (LifecycleCallback)paramClass.cast(zzbe.get(paramString));
  }
  
  public final boolean isCreated()
  {
    return zzbf > 0;
  }
  
  public final boolean isStarted()
  {
    return zzbf >= 2;
  }
  
  public final void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    Iterator localIterator = zzbe.values().iterator();
    while (localIterator.hasNext()) {
      ((LifecycleCallback)localIterator.next()).onActivityResult(paramInt1, paramInt2, paramIntent);
    }
  }
  
  public final void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    zzbf = 1;
    zzbg = paramBundle;
    Iterator localIterator = zzbe.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (Map.Entry)localIterator.next();
      LifecycleCallback localLifecycleCallback = (LifecycleCallback)((Map.Entry)localObject).getValue();
      if (paramBundle != null) {
        localObject = paramBundle.getBundle((String)((Map.Entry)localObject).getKey());
      } else {
        localObject = null;
      }
      localLifecycleCallback.onCreate((Bundle)localObject);
    }
  }
  
  public final void onDestroy()
  {
    super.onDestroy();
    zzbf = 5;
    Iterator localIterator = zzbe.values().iterator();
    while (localIterator.hasNext()) {
      ((LifecycleCallback)localIterator.next()).onDestroy();
    }
  }
  
  public final void onResume()
  {
    super.onResume();
    zzbf = 3;
    Iterator localIterator = zzbe.values().iterator();
    while (localIterator.hasNext()) {
      ((LifecycleCallback)localIterator.next()).onResume();
    }
  }
  
  public final void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    if (paramBundle == null) {
      return;
    }
    Iterator localIterator = zzbe.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      Bundle localBundle = new Bundle();
      ((LifecycleCallback)localEntry.getValue()).onSaveInstanceState(localBundle);
      paramBundle.putBundle((String)localEntry.getKey(), localBundle);
    }
  }
  
  public final void onStart()
  {
    super.onStart();
    zzbf = 2;
    Iterator localIterator = zzbe.values().iterator();
    while (localIterator.hasNext()) {
      ((LifecycleCallback)localIterator.next()).onStart();
    }
  }
  
  public final void onStop()
  {
    super.onStop();
    zzbf = 4;
    Iterator localIterator = zzbe.values().iterator();
    while (localIterator.hasNext()) {
      ((LifecycleCallback)localIterator.next()).onStop();
    }
  }
}
