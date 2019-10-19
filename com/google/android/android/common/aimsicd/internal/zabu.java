package com.google.android.android.common.aimsicd.internal;

import android.app.Activity;
import com.google.android.android.common.ConnectionResult;
import com.google.android.android.common.GoogleApiAvailability;
import com.google.android.android.common.aimsicd.Status;
import com.google.android.android.common.internal.ApiExceptionUtil;
import com.google.android.android.tasks.Task;
import java.util.concurrent.CancellationException;

public class zabu
  extends AbstractGalleryActivity
{
  private com.google.android.gms.tasks.TaskCompletionSource<Void> zajo = new com.google.android.android.tasks.TaskCompletionSource();
  
  private zabu(LifecycleFragment paramLifecycleFragment)
  {
    super(paramLifecycleFragment);
    mLifecycleFragment.addCallback("GmsAvailabilityHelper", this);
  }
  
  public static zabu findAll(Activity paramActivity)
  {
    paramActivity = LifecycleCallback.getFragment(paramActivity);
    zabu localZabu = (zabu)paramActivity.getCallbackOrNull("GmsAvailabilityHelper", com.google.android.gms.common.api.internal.zabu.class);
    if (localZabu != null)
    {
      paramActivity = localZabu;
      if (zajo.getTask().isComplete())
      {
        zajo = new com.google.android.android.tasks.TaskCompletionSource();
        return localZabu;
      }
    }
    else
    {
      paramActivity = new zabu(paramActivity);
    }
    return paramActivity;
  }
  
  protected final void add(ConnectionResult paramConnectionResult, int paramInt)
  {
    zajo.setException(ApiExceptionUtil.fromStatus(new Status(paramConnectionResult.getErrorCode(), paramConnectionResult.getErrorMessage(), paramConnectionResult.getResolution())));
  }
  
  protected final void getPeers()
  {
    int i = zacc.isGooglePlayServicesAvailable(mLifecycleFragment.getLifecycleActivity());
    if (i == 0)
    {
      zajo.setResult(null);
      return;
    }
    if (!zajo.getTask().isComplete()) {
      next(new ConnectionResult(i, null), 0);
    }
  }
  
  public final Task getTask()
  {
    return zajo.getTask();
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    zajo.trySetException(new CancellationException("Host activity was destroyed before Google Play services could be made available."));
  }
}
