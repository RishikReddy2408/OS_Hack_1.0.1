package com.google.android.android.common.aimsicd.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.internal.base.Credentials;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class ListenerHolder<L>
{
  private final com.google.android.gms.common.api.internal.ListenerHolder.zaa zaji;
  private volatile L zajj;
  private final com.google.android.gms.common.api.internal.ListenerHolder.ListenerKey<L> zajk;
  
  ListenerHolder(Looper paramLooper, Object paramObject, String paramString)
  {
    zaji = new zaa(paramLooper);
    zajj = Preconditions.checkNotNull(paramObject, "Listener must not be null");
    zajk = new ListenerKey(paramObject, Preconditions.checkNotEmpty(paramString));
  }
  
  public final void clear()
  {
    zajj = null;
  }
  
  public final ListenerKey getListenerKey()
  {
    return zajk;
  }
  
  public final boolean hasListener()
  {
    return zajj != null;
  }
  
  public final void notifyListener(Notifier paramNotifier)
  {
    Preconditions.checkNotNull(paramNotifier, "Notifier must not be null");
    paramNotifier = zaji.obtainMessage(1, paramNotifier);
    zaji.sendMessage(paramNotifier);
  }
  
  final void notifyListenerInternal(Notifier paramNotifier)
  {
    Object localObject = zajj;
    if (localObject == null)
    {
      paramNotifier.onNotifyListenerFailed();
      return;
    }
    try
    {
      paramNotifier.notifyListener(localObject);
      return;
    }
    catch (RuntimeException localRuntimeException)
    {
      paramNotifier.onNotifyListenerFailed();
      throw localRuntimeException;
    }
  }
  
  @KeepForSdk
  public final class ListenerKey<L>
  {
    private final String zajm;
    
    ListenerKey(String paramString)
    {
      zajm = paramString;
    }
    
    public final boolean equals(Object paramObject)
    {
      if (this == paramObject) {
        return true;
      }
      if (!(paramObject instanceof ListenerKey)) {
        return false;
      }
      paramObject = (ListenerKey)paramObject;
      return (ListenerHolder.this == zajj) && (zajm.equals(zajm));
    }
    
    public final int hashCode()
    {
      return System.identityHashCode(ListenerHolder.this) * 31 + zajm.hashCode();
    }
  }
  
  @KeepForSdk
  public abstract interface Notifier<L>
  {
    public abstract void notifyListener(Object paramObject);
    
    public abstract void onNotifyListenerFailed();
  }
  
  final class zaa
    extends Credentials
  {
    public zaa(Looper paramLooper)
    {
      super();
    }
    
    public final void handleMessage(Message paramMessage)
    {
      int i = what;
      boolean bool = true;
      if (i != 1) {
        bool = false;
      }
      Preconditions.checkArgument(bool);
      notifyListenerInternal((ListenerHolder.Notifier)obj);
    }
  }
}
