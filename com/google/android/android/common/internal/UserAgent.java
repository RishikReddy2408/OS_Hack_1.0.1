package com.google.android.android.common.internal;

import android.app.Activity;
import android.content.Intent;

final class UserAgent
  extends DialogRedirect
{
  UserAgent(Intent paramIntent, Activity paramActivity, int paramInt) {}
  
  public final void redirect()
  {
    if (zaog != null) {
      val$activity.startActivityForResult(zaog, val$requestCode);
    }
  }
}
