package com.google.android.android.common.aimsicd;

import android.support.v4.util.ArrayMap;
import android.support.v4.util.SimpleArrayMap;
import android.text.TextUtils;
import com.google.android.android.common.aimsicd.internal.Msg;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.gms.common.api.internal.zai;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class AvailabilityException
  extends Exception
{
  private final ArrayMap<zai<?>, com.google.android.gms.common.ConnectionResult> zaay;
  
  public AvailabilityException(ArrayMap paramArrayMap)
  {
    zaay = paramArrayMap;
  }
  
  public com.google.android.android.common.ConnectionResult getConnectionResult(GoogleApi paramGoogleApi)
  {
    paramGoogleApi = paramGoogleApi.get();
    boolean bool;
    if (zaay.get(paramGoogleApi) != null) {
      bool = true;
    } else {
      bool = false;
    }
    Preconditions.checkArgument(bool, "The given API was not part of the availability request.");
    return (com.google.android.android.common.ConnectionResult)zaay.get(paramGoogleApi);
  }
  
  public String getMessage()
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = zaay.keySet().iterator();
    int i = 1;
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject2 = (Msg)((Iterator)localObject1).next();
      Object localObject3 = (com.google.android.android.common.ConnectionResult)zaay.get(localObject2);
      if (((com.google.android.android.common.ConnectionResult)localObject3).isSuccess()) {
        i = 0;
      }
      localObject2 = ((Msg)localObject2).get();
      localObject3 = String.valueOf(localObject3);
      StringBuilder localStringBuilder = new StringBuilder(String.valueOf(localObject2).length() + 2 + String.valueOf(localObject3).length());
      localStringBuilder.append((String)localObject2);
      localStringBuilder.append(": ");
      localStringBuilder.append((String)localObject3);
      localArrayList.add(localStringBuilder.toString());
    }
    localObject1 = new StringBuilder();
    if (i != 0) {
      ((StringBuilder)localObject1).append("None of the queried APIs are available. ");
    } else {
      ((StringBuilder)localObject1).append("Some of the queried APIs are unavailable. ");
    }
    ((StringBuilder)localObject1).append(TextUtils.join("; ", localArrayList));
    return ((StringBuilder)localObject1).toString();
  }
  
  public final ArrayMap getString()
  {
    return zaay;
  }
}
