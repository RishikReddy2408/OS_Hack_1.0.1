package com.google.android.android.common.aimsicd.internal;

import android.os.Handler;
import com.google.android.android.common.internal.BaseGmsClient.SignOutCallbacks;

final class zabm
  implements BaseGmsClient.SignOutCallbacks
{
  zabm(GoogleApiManager.zaa paramZaa) {}
  
  public final void onSignOutComplete()
  {
    GoogleApiManager.access$getHandler(zaix.zail).post(new zabn(this));
  }
}
