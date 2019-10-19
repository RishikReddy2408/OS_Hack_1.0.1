package com.google.android.android.common;

import android.content.Intent;

public class GooglePlayServicesRepairableException
  extends UserRecoverableException
{
  private final int zzaf;
  
  public GooglePlayServicesRepairableException(int paramInt, String paramString, Intent paramIntent)
  {
    super(paramString, paramIntent);
    zzaf = paramInt;
  }
  
  public int getConnectionStatusCode()
  {
    return zzaf;
  }
}
