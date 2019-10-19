package com.google.android.android.common.aimsicd.internal;

import com.google.android.android.common.ConnectionResult;
import com.google.android.android.common.internal.Preconditions;

final class Tag
{
  private final int zadg;
  private final ConnectionResult zadh;
  
  Tag(ConnectionResult paramConnectionResult, int paramInt)
  {
    Preconditions.checkNotNull(paramConnectionResult);
    zadh = paramConnectionResult;
    zadg = paramInt;
  }
  
  final ConnectionResult getConnectionResult()
  {
    return zadh;
  }
  
  final int getId()
  {
    return zadg;
  }
}
