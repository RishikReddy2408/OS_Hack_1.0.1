package com.google.firebase.components;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class ComponentDiscoveryService
  extends Service
{
  public ComponentDiscoveryService() {}
  
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }
}
