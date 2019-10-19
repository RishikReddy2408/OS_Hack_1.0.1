package com.google.android.android.common.aimsicd;

import com.google.android.gms.common.api.zac;
import com.google.android.gms.common.internal.ShowFirstParty;
import java.util.Map;
import java.util.WeakHashMap;
import javax.annotation.concurrent.GuardedBy;

@ShowFirstParty
public abstract class Realm
{
  private static final Object sLock = new Object();
  @GuardedBy("sLock")
  private static final Map<Object, zac> zacj = new WeakHashMap();
  
  public Realm() {}
  
  public abstract void remove(int paramInt);
}
