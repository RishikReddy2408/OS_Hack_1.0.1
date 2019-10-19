package com.google.android.android.measurement.internal;

import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import com.google.android.android.common.internal.Preconditions;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class FileTransfer
  extends class_2
{
  private final Map<String, Long> zzafq = new ArrayMap();
  private final Map<String, Integer> zzafr = new ArrayMap();
  private long zzafs;
  
  public FileTransfer(zzbt paramZzbt)
  {
    super(paramZzbt);
  }
  
  private final void execute(long paramLong)
  {
    Iterator localIterator = zzafq.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      zzafq.put(str, Long.valueOf(paramLong));
    }
    if (!zzafq.isEmpty()) {
      zzafs = paramLong;
    }
  }
  
  private final void getFileSize(String paramString, long paramLong)
  {
    zzgb();
    zzaf();
    Preconditions.checkNotEmpty(paramString);
    if (zzafr.isEmpty()) {
      zzafs = paramLong;
    }
    Integer localInteger = (Integer)zzafr.get(paramString);
    if (localInteger != null)
    {
      zzafr.put(paramString, Integer.valueOf(localInteger.intValue() + 1));
      return;
    }
    if (zzafr.size() >= 100)
    {
      zzgo().zzjg().zzbx("Too many ads visible");
      return;
    }
    zzafr.put(paramString, Integer.valueOf(1));
    zzafq.put(paramString, Long.valueOf(paramLong));
  }
  
  private final void setTimeout(long paramLong, zzdn paramZzdn)
  {
    if (paramZzdn == null)
    {
      zzgo().zzjl().zzbx("Not logging ad exposure. No active activity");
      return;
    }
    if (paramLong < 1000L)
    {
      zzgo().zzjl().append("Not logging ad exposure. Less than 1000 ms. exposure", Long.valueOf(paramLong));
      return;
    }
    Bundle localBundle = new Bundle();
    localBundle.putLong("_xt", paramLong);
    zzdo.set(paramZzdn, localBundle, true);
    zzge().logEvent("am", "_xa", localBundle);
  }
  
  private final void sleep(String paramString, long paramLong, zzdn paramZzdn)
  {
    if (paramZzdn == null)
    {
      zzgo().zzjl().zzbx("Not logging ad unit exposure. No active activity");
      return;
    }
    if (paramLong < 1000L)
    {
      zzgo().zzjl().append("Not logging ad unit exposure. Less than 1000 ms. exposure", Long.valueOf(paramLong));
      return;
    }
    Bundle localBundle = new Bundle();
    localBundle.putString("_ai", paramString);
    localBundle.putLong("_xt", paramLong);
    zzdo.set(paramZzdn, localBundle, true);
    zzge().logEvent("am", "_xu", localBundle);
  }
  
  private final void upload(String paramString, long paramLong)
  {
    zzgb();
    zzaf();
    Preconditions.checkNotEmpty(paramString);
    Object localObject = (Integer)zzafr.get(paramString);
    if (localObject != null)
    {
      zzdn localZzdn = zzgh().zzla();
      int i = ((Integer)localObject).intValue() - 1;
      if (i == 0)
      {
        zzafr.remove(paramString);
        localObject = (Long)zzafq.get(paramString);
        if (localObject == null)
        {
          zzgo().zzjd().zzbx("First ad unit exposure time was never set");
        }
        else
        {
          long l = ((Long)localObject).longValue();
          zzafq.remove(paramString);
          sleep(paramString, paramLong - l, localZzdn);
        }
        if (zzafr.isEmpty())
        {
          if (zzafs == 0L)
          {
            zzgo().zzjd().zzbx("First ad exposure time was never set");
            return;
          }
          setTimeout(paramLong - zzafs, localZzdn);
          zzafs = 0L;
        }
      }
      else
      {
        zzafr.put(paramString, Integer.valueOf(i));
      }
    }
    else
    {
      zzgo().zzjd().append("Call to endAdUnitExposure for unknown ad unit id", paramString);
    }
  }
  
  public final void beginAdUnitExposure(String paramString, long paramLong)
  {
    if ((paramString != null) && (paramString.length() != 0))
    {
      zzgn().get(new FileBrowser.11(this, paramString, paramLong));
      return;
    }
    zzgo().zzjd().zzbx("Ad unit id must be a non-empty string");
  }
  
  public final void endAdUnitExposure(String paramString, long paramLong)
  {
    if ((paramString != null) && (paramString.length() != 0))
    {
      zzgn().get(new LayerView.1(this, paramString, paramLong));
      return;
    }
    zzgo().zzjd().zzbx("Ad unit id must be a non-empty string");
  }
  
  public final void upload(long paramLong)
  {
    zzdn localZzdn = zzgh().zzla();
    Iterator localIterator = zzafq.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      sleep(str, paramLong - ((Long)zzafq.get(str)).longValue(), localZzdn);
    }
    if (!zzafq.isEmpty()) {
      setTimeout(paramLong - zzafs, localZzdn);
    }
    execute(paramLong);
  }
}
