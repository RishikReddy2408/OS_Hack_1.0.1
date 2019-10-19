package com.google.android.android.common.internal;

import android.support.annotation.NonNull;

public final class ProcessUtil
{
  @NonNull
  private final String mPackageName;
  private final int zzdt;
  @NonNull
  private final String zzej;
  private final boolean zzek;
  
  public ProcessUtil(String paramString1, String paramString2, boolean paramBoolean, int paramInt)
  {
    mPackageName = paramString1;
    zzej = paramString2;
    zzek = paramBoolean;
    zzdt = 129;
  }
  
  final int getInstance()
  {
    return zzdt;
  }
  
  final String getPackageName()
  {
    return mPackageName;
  }
  
  final String getResultPath()
  {
    return zzej;
  }
}
