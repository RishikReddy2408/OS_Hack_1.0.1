package com.google.android.android.common.aimsicd.internal;

import android.app.Dialog;
import android.content.ContextWrapper;
import com.google.android.android.common.ConnectionResult;
import com.google.android.android.common.GoogleApiAvailability;
import com.google.android.android.common.aimsicd.GoogleApiActivity;

final class SyncCampaign
  implements Runnable
{
  private final Tag zadi;
  
  SyncCampaign(AbstractGalleryActivity paramAbstractGalleryActivity, Tag paramTag)
  {
    zadi = paramTag;
  }
  
  public final void run()
  {
    if (!zadj.mStarted) {
      return;
    }
    Object localObject = zadi.getConnectionResult();
    if (((ConnectionResult)localObject).hasResolution())
    {
      zadj.mLifecycleFragment.startActivityForResult(GoogleApiActivity.createIntent(zadj.getActivity(), ((ConnectionResult)localObject).getResolution(), zadi.getId(), false), 1);
      return;
    }
    if (zadj.zacc.isUserResolvableError(((ConnectionResult)localObject).getErrorCode()))
    {
      zadj.zacc.create(zadj.getActivity(), zadj.mLifecycleFragment, ((ConnectionResult)localObject).getErrorCode(), 2, zadj);
      return;
    }
    if (((ConnectionResult)localObject).getErrorCode() == 18)
    {
      localObject = GoogleApiAvailability.show(zadj.getActivity(), zadj);
      zadj.zacc.start(zadj.getActivity().getApplicationContext(), new Searcher(this, (Dialog)localObject));
      return;
    }
    zadj.add((ConnectionResult)localObject, zadi.getId());
  }
}
