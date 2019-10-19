package com.google.android.android.dynamic;

import com.google.android.gms.dynamic.OnDelegateCreatedListener;
import java.util.Iterator;
import java.util.LinkedList;

final class ContentSource
  implements OnDelegateCreatedListener<T>
{
  ContentSource(DeferredLifecycleHelper paramDeferredLifecycleHelper) {}
  
  public final void onDelegateCreated(LifecycleDelegate paramLifecycleDelegate)
  {
    DeferredLifecycleHelper.xor(zari, paramLifecycleDelegate);
    paramLifecycleDelegate = DeferredLifecycleHelper.getResults(zari).iterator();
    while (paramLifecycleDelegate.hasNext()) {
      ((DeferredLifecycleHelper.zaa)paramLifecycleDelegate.next()).makeView(DeferredLifecycleHelper.method_5(zari));
    }
    DeferredLifecycleHelper.getResults(zari).clear();
    DeferredLifecycleHelper.getSession(zari, null);
  }
}
