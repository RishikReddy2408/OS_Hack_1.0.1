package com.google.android.android.common.internal;

import android.content.Intent;
import android.support.v4.package_7.Fragment;

final class Call
  extends DialogRedirect
{
  Call(Intent paramIntent, Fragment paramFragment, int paramInt) {}
  
  public final void redirect()
  {
    if (zaog != null) {
      val$fragment.startActivityForResult(zaog, val$requestCode);
    }
  }
}
