package com.google.android.android.common.internal;

import android.net.Uri;
import android.net.Uri.Builder;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class ResourceUtils
{
  private static final Uri zzet = new Uri.Builder().scheme("android.resource").authority("com.google.android.gms").appendPath("drawable").build();
  
  private ResourceUtils() {}
}
