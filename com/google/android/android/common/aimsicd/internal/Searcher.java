package com.google.android.android.common.aimsicd.internal;

import android.app.Dialog;

final class Searcher
  extends zabr
{
  Searcher(SyncCampaign paramSyncCampaign, Dialog paramDialog) {}
  
  public final void cancel()
  {
    zadl.zadj.onActivityResult();
    if (zadk.isShowing()) {
      zadk.dismiss();
    }
  }
}
