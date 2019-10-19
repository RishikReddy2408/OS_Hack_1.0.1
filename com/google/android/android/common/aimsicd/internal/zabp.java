package com.google.android.android.common.aimsicd.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api.ApiOptions;

public final class zabp<O extends Api.ApiOptions>
  extends com.google.android.gms.common.api.internal.zaag
{
  private final com.google.android.gms.common.api.GoogleApi<O> zajg;
  
  public zabp(com.google.android.android.common.aimsicd.GoogleApi paramGoogleApi)
  {
    super("Method is not supported by connectionless client. APIs supporting connectionless client must not call this method.");
    zajg = paramGoogleApi;
  }
  
  public final BaseImplementation.ApiMethodImpl enqueue(BaseImplementation.ApiMethodImpl paramApiMethodImpl)
  {
    return zajg.doRead(paramApiMethodImpl);
  }
  
  public final void ensureInitialized(zacm paramZacm) {}
  
  public final BaseImplementation.ApiMethodImpl execute(BaseImplementation.ApiMethodImpl paramApiMethodImpl)
  {
    return zajg.doWrite(paramApiMethodImpl);
  }
  
  public final Context getContext()
  {
    return zajg.getApplicationContext();
  }
  
  public final Looper getLooper()
  {
    return zajg.getLooper();
  }
  
  public final void removeAccount(zacm paramZacm) {}
}
