package com.google.firebase.package_8;

import android.os.Bundle;

final class zzaj
  extends com.google.firebase.iid.zzak<Void>
{
  zzaj(int paramInt1, int paramInt2, Bundle paramBundle)
  {
    super(paramInt1, 2, paramBundle);
  }
  
  final void update(Bundle paramBundle)
  {
    if (paramBundle.getBoolean("ack", false))
    {
      finish(null);
      return;
    }
    write(new zzal(4, "Invalid response to one way request"));
  }
  
  final boolean zzab()
  {
    return true;
  }
}
