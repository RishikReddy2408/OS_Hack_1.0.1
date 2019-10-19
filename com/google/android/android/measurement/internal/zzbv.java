package com.google.android.android.measurement.internal;

import android.os.Binder;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.android.common.GooglePlayServicesUtilLight;
import com.google.android.android.common.GoogleSignatureVerifier;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.common.util.Clock;
import com.google.android.android.common.util.UidVerifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public final class zzbv
  extends zzah
{
  private final zzfa zzamz;
  private Boolean zzaql;
  @Nullable
  private String zzaqm;
  
  public zzbv(zzfa paramZzfa)
  {
    this(paramZzfa, null);
  }
  
  private zzbv(zzfa paramZzfa, String paramString)
  {
    Preconditions.checkNotNull(paramZzfa);
    zzamz = paramZzfa;
    zzaqm = null;
  }
  
  private final void e(Runnable paramRunnable)
  {
    Preconditions.checkNotNull(paramRunnable);
    if ((((Boolean)zzaf.zzakv.getDefaultValue()).booleanValue()) && (zzamz.zzgn().zzkb()))
    {
      paramRunnable.run();
      return;
    }
    zzamz.zzgn().get(paramRunnable);
  }
  
  private final void e(String paramString, boolean paramBoolean)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      if (paramBoolean)
      {
        Object localObject1;
        if (zzaql == null) {
          localObject1 = zzaqm;
        }
        try
        {
          paramBoolean = "com.google.android.gms".equals(localObject1);
          if (!paramBoolean)
          {
            localObject1 = zzamz;
            paramBoolean = UidVerifier.isGooglePlayServicesUid(((zzfa)localObject1).getContext(), Binder.getCallingUid());
            if (!paramBoolean)
            {
              localObject1 = zzamz;
              paramBoolean = GoogleSignatureVerifier.getInstance(((zzfa)localObject1).getContext()).isUidGoogleSigned(Binder.getCallingUid());
              if (!paramBoolean)
              {
                paramBoolean = false;
                break label87;
              }
            }
          }
          paramBoolean = true;
          label87:
          zzaql = Boolean.valueOf(paramBoolean);
          localObject1 = zzaql;
          paramBoolean = ((Boolean)localObject1).booleanValue();
          if (paramBoolean) {
            return;
          }
        }
        catch (SecurityException localSecurityException)
        {
          break label186;
        }
      }
      if (zzaqm == null)
      {
        localObject2 = zzamz;
        paramBoolean = GooglePlayServicesUtilLight.uidHasPackageName(((zzfa)localObject2).getContext(), Binder.getCallingUid(), paramString);
        if (paramBoolean) {
          zzaqm = paramString;
        }
      }
      Object localObject2 = zzaqm;
      paramBoolean = paramString.equals(localObject2);
      if (paramBoolean) {
        return;
      }
      throw new SecurityException(String.format("Unknown calling package name '%s'.", new Object[] { paramString }));
      label186:
      zzamz.zzgo().zzjd().append("Measurement Service called with invalid calling package. appId", zzap.zzbv(paramString));
      throw ((Throwable)localObject2);
    }
    zzamz.zzgo().zzjd().zzbx("Measurement Service called without app package");
    throw new SecurityException("Measurement Service called without app package");
  }
  
  private final void toString(ApplicationInfo paramApplicationInfo, boolean paramBoolean)
  {
    Preconditions.checkNotNull(paramApplicationInfo);
    e(packageName, false);
    zzamz.zzgm().getDisplayTitle(zzafx, zzagk);
  }
  
  public final void chmod(ApplicationInfo paramApplicationInfo)
  {
    toString(paramApplicationInfo, false);
    e(new zzcm(this, paramApplicationInfo));
  }
  
  public final void createShortcut(ApplicationInfo paramApplicationInfo)
  {
    e(packageName, false);
    e(new zzcf(this, paramApplicationInfo));
  }
  
  public final byte[] get(zzad paramZzad, String paramString)
  {
    Preconditions.checkNotEmpty(paramString);
    Preconditions.checkNotNull(paramZzad);
    e(paramString, true);
    zzamz.zzgo().zzjk().append("Log and bundle. event", zzamz.zzgl().zzbs(name));
    long l1 = zzamz.zzbx().nanoTime() / 1000000L;
    Object localObject1 = zzamz.zzgn().get(new zzci(this, paramZzad, paramString));
    try
    {
      localObject1 = ((Future)localObject1).get();
      Object localObject2 = (byte[])localObject1;
      localObject1 = localObject2;
      if (localObject2 == null)
      {
        localObject1 = zzamz;
        ((zzfa)localObject1).zzgo().zzjd().append("Log and bundle returned null. appId", zzap.zzbv(paramString));
        localObject1 = new byte[0];
      }
      localObject2 = zzamz;
      long l2 = ((zzfa)localObject2).zzbx().nanoTime();
      l2 /= 1000000L;
      localObject2 = zzamz;
      localObject2 = ((zzfa)localObject2).zzgo().zzjk();
      Object localObject3 = zzamz;
      localObject3 = ((zzfa)localObject3).zzgl();
      String str = name;
      localObject3 = ((zzan)localObject3).zzbs(str);
      int i = localObject1.length;
      ((zzar)localObject2).append("Log and bundle processed. event, size, time_ms", localObject3, Integer.valueOf(i), Long.valueOf(l2 - l1));
      return localObject1;
    }
    catch (InterruptedException|ExecutionException localInterruptedException)
    {
      zzamz.zzgo().zzjd().append("Failed to log and bundle. appId, event, error", zzap.zzbv(paramString), zzamz.zzgl().zzbs(name), localInterruptedException);
    }
    return null;
  }
  
  public final String getAbsoluteUrl(ApplicationInfo paramApplicationInfo)
  {
    toString(paramApplicationInfo, false);
    return zzamz.getAbsoluteUrl(paramApplicationInfo);
  }
  
  public final List getFromLocationName(ApplicationInfo paramApplicationInfo, boolean paramBoolean)
  {
    toString(paramApplicationInfo, false);
    Object localObject1 = zzamz.zzgn().d(new zzcl(this, paramApplicationInfo));
    try
    {
      localObject1 = ((Future)localObject1).get();
      Object localObject2 = (List)localObject1;
      localObject1 = new ArrayList(((List)localObject2).size());
      localObject2 = ((List)localObject2).iterator();
      for (;;)
      {
        boolean bool = ((Iterator)localObject2).hasNext();
        if (!bool) {
          break;
        }
        Object localObject3 = ((Iterator)localObject2).next();
        localObject3 = (zzfj)localObject3;
        if (!paramBoolean)
        {
          String str = name;
          bool = zzfk.zzcv(str);
          if (bool) {}
        }
        else
        {
          ((List)localObject1).add(new zzfh((zzfj)localObject3));
        }
      }
      return localObject1;
    }
    catch (InterruptedException|ExecutionException localInterruptedException)
    {
      zzamz.zzgo().zzjd().append("Failed to get user attributes. appId", zzap.zzbv(packageName), localInterruptedException);
    }
    return null;
  }
  
  public final List getFromLocationName(String paramString1, String paramString2, ApplicationInfo paramApplicationInfo)
  {
    toString(paramApplicationInfo, false);
    paramString1 = zzamz.zzgn().d(new zzcd(this, paramApplicationInfo, paramString1, paramString2));
    try
    {
      paramString1 = paramString1.get();
      return (List)paramString1;
    }
    catch (InterruptedException|ExecutionException paramString1)
    {
      zzamz.zzgo().zzjd().append("Failed to get conditional user properties", paramString1);
    }
    return Collections.emptyList();
  }
  
  public final List getFromLocationName(String paramString1, String paramString2, String paramString3)
  {
    e(paramString1, true);
    paramString1 = zzamz.zzgn().d(new zzce(this, paramString1, paramString2, paramString3));
    try
    {
      paramString1 = paramString1.get();
      return (List)paramString1;
    }
    catch (InterruptedException|ExecutionException paramString1)
    {
      zzamz.zzgo().zzjd().append("Failed to get conditional user properties", paramString1);
    }
    return Collections.emptyList();
  }
  
  public final List getFromLocationName(String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    e(paramString1, true);
    paramString2 = zzamz.zzgn().d(new zzcc(this, paramString1, paramString2, paramString3));
    try
    {
      paramString2 = paramString2.get();
      paramString3 = (List)paramString2;
      paramString2 = new ArrayList(paramString3.size());
      paramString3 = paramString3.iterator();
      for (;;)
      {
        boolean bool = paramString3.hasNext();
        if (!bool) {
          break;
        }
        Object localObject = paramString3.next();
        localObject = (zzfj)localObject;
        if (!paramBoolean)
        {
          String str = name;
          bool = zzfk.zzcv(str);
          if (bool) {}
        }
        else
        {
          paramString2.add(new zzfh((zzfj)localObject));
        }
      }
      return paramString2;
    }
    catch (InterruptedException|ExecutionException paramString2)
    {
      zzamz.zzgo().zzjd().append("Failed to get user attributes. appId", zzap.zzbv(paramString1), paramString2);
    }
    return Collections.emptyList();
  }
  
  public final List getFromLocationName(String paramString1, String paramString2, boolean paramBoolean, ApplicationInfo paramApplicationInfo)
  {
    toString(paramApplicationInfo, false);
    paramString1 = zzamz.zzgn().d(new zzcb(this, paramApplicationInfo, paramString1, paramString2));
    try
    {
      paramString1 = paramString1.get();
      paramString2 = (List)paramString1;
      paramString1 = new ArrayList(paramString2.size());
      paramString2 = paramString2.iterator();
      for (;;)
      {
        boolean bool = paramString2.hasNext();
        if (!bool) {
          break;
        }
        Object localObject = paramString2.next();
        localObject = (zzfj)localObject;
        if (!paramBoolean)
        {
          String str = name;
          bool = zzfk.zzcv(str);
          if (bool) {}
        }
        else
        {
          paramString1.add(new zzfh((zzfj)localObject));
        }
      }
      return paramString1;
    }
    catch (InterruptedException|ExecutionException paramString1)
    {
      zzamz.zzgo().zzjd().append("Failed to get user attributes. appId", zzap.zzbv(packageName), paramString1);
    }
    return Collections.emptyList();
  }
  
  public final void getPackageInfo(ComponentInfo paramComponentInfo, ApplicationInfo paramApplicationInfo)
  {
    Preconditions.checkNotNull(paramComponentInfo);
    Preconditions.checkNotNull(zzahb);
    toString(paramApplicationInfo, false);
    ComponentInfo localComponentInfo = new ComponentInfo(paramComponentInfo);
    packageName = packageName;
    if (zzahb.getValue() == null)
    {
      e(new zzbx(this, localComponentInfo, paramApplicationInfo));
      return;
    }
    e(new zzby(this, localComponentInfo, paramApplicationInfo));
  }
  
  public final void handleResult(ApplicationInfo paramApplicationInfo)
  {
    toString(paramApplicationInfo, false);
    e(new zzbw(this, paramApplicationInfo));
  }
  
  public final void handleResult(zzad paramZzad, ApplicationInfo paramApplicationInfo)
  {
    Preconditions.checkNotNull(paramZzad);
    toString(paramApplicationInfo, false);
    e(new zzcg(this, paramZzad, paramApplicationInfo));
  }
  
  public final void handleResult(zzad paramZzad, String paramString1, String paramString2)
  {
    Preconditions.checkNotNull(paramZzad);
    Preconditions.checkNotEmpty(paramString1);
    e(paramString1, true);
    e(new zzch(this, paramZzad, paramString1));
  }
  
  public final void setPresence(long paramLong, String paramString1, String paramString2, String paramString3)
  {
    e(new zzcn(this, paramString2, paramString3, paramString1, paramLong));
  }
  
  final zzad toJSONObject(zzad paramZzad, ApplicationInfo paramApplicationInfo)
  {
    boolean bool = "_cmp".equals(name);
    int j = 0;
    int i = j;
    if (bool)
    {
      i = j;
      if (zzaid != null) {
        if (zzaid.size() == 0)
        {
          i = j;
        }
        else
        {
          String str = zzaid.getString("_cis");
          i = j;
          if (!TextUtils.isEmpty(str)) {
            if (!"referrer broadcast".equals(str))
            {
              i = j;
              if (!"referrer API".equals(str)) {}
            }
            else
            {
              i = j;
              if (zzamz.zzgq().zzbg(packageName)) {
                i = 1;
              }
            }
          }
        }
      }
    }
    if (i != 0)
    {
      zzamz.zzgo().zzjj().append("Event has been filtered ", paramZzad.toString());
      return new zzad("_cmpx", zzaid, origin, zzaip);
    }
    return paramZzad;
  }
  
  public final void toggleState(ComponentInfo paramComponentInfo)
  {
    Preconditions.checkNotNull(paramComponentInfo);
    Preconditions.checkNotNull(zzahb);
    e(packageName, true);
    ComponentInfo localComponentInfo = new ComponentInfo(paramComponentInfo);
    if (zzahb.getValue() == null)
    {
      e(new zzbz(this, localComponentInfo));
      return;
    }
    e(new zzca(this, localComponentInfo));
  }
  
  public final void write(zzfh paramZzfh, ApplicationInfo paramApplicationInfo)
  {
    Preconditions.checkNotNull(paramZzfh);
    toString(paramApplicationInfo, false);
    if (paramZzfh.getValue() == null)
    {
      e(new zzcj(this, paramZzfh, paramApplicationInfo));
      return;
    }
    e(new zzck(this, paramZzfh, paramApplicationInfo));
  }
}
