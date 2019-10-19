package com.google.firebase.package_8;

import android.os.Bundle;

final class zzam
  extends com.google.firebase.iid.zzak<Bundle>
{
  zzam(int paramInt1, int paramInt2, Bundle paramBundle)
  {
    super(paramInt1, 1, paramBundle);
  }
  
  final void update(Bundle paramBundle)
  {
    Bundle localBundle = paramBundle.getBundle("data");
    paramBundle = localBundle;
    if (localBundle == null) {
      paramBundle = Bundle.EMPTY;
    }
    finish(paramBundle);
  }
  
  final boolean zzab()
  {
    return false;
  }
}
