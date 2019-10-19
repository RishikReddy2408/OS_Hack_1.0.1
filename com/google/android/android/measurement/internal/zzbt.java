package com.google.android.android.measurement.internal;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.android.common.aimsicd.internal.GoogleServices;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.common.util.Clock;
import com.google.android.android.common.util.DefaultClock;
import com.google.android.android.common.wrappers.PackageManagerWrapper;
import com.google.android.android.common.wrappers.Wrappers;
import com.google.android.android.internal.measurement.zzsl;
import com.google.android.android.measurement.AppMeasurement;
import java.util.concurrent.atomic.AtomicReference;

public class zzbt
  implements zzcq
{
  private static volatile zzbt zzapl;
  private final boolean zzadv;
  private final String zzadx;
  private final long zzagx;
  private final MultiMap zzaiq;
  private final String zzapm;
  private final String zzapn;
  private final class_3 zzapo;
  private final zzba zzapp;
  private final zzap zzapq;
  private final zzbo zzapr;
  private final zzeq zzaps;
  private final AppMeasurement zzapt;
  private final zzfk zzapu;
  private final zzan zzapv;
  private final zzdo zzapw;
  private final zzcs zzapx;
  private final FileTransfer zzapy;
  private zzal zzapz;
  private zzdr zzaqa;
  private class_6 zzaqb;
  private zzaj zzaqc;
  private zzbg zzaqd;
  private Boolean zzaqe;
  private long zzaqf;
  private volatile Boolean zzaqg;
  private int zzaqh;
  private int zzaqi;
  private final Context zzri;
  private final Clock zzrz;
  private boolean zzvz = false;
  
  private zzbt(zzcr paramZzcr)
  {
    Preconditions.checkNotNull(paramZzcr);
    zzaiq = new MultiMap(zzri);
    zzaf.setHeaders(zzaiq);
    zzri = zzri;
    zzadx = zzadx;
    zzapm = zzapm;
    zzapn = zzapn;
    zzadv = zzadv;
    zzaqg = zzaqg;
    zzsl.init(zzri);
    zzrz = DefaultClock.getInstance();
    zzagx = zzrz.currentTimeMillis();
    zzapo = new class_3(this);
    Object localObject = new zzba(this);
    ((zzcp)localObject).reloadPreferences();
    zzapp = ((zzba)localObject);
    localObject = new zzap(this);
    ((zzcp)localObject).reloadPreferences();
    zzapq = ((zzap)localObject);
    localObject = new zzfk(this);
    ((zzcp)localObject).reloadPreferences();
    zzapu = ((zzfk)localObject);
    localObject = new zzan(this);
    ((zzcp)localObject).reloadPreferences();
    zzapv = ((zzan)localObject);
    zzapy = new FileTransfer(this);
    localObject = new zzdo(this);
    ((Log)localObject).logError();
    zzapw = ((zzdo)localObject);
    localObject = new zzcs(this);
    ((Log)localObject).logError();
    zzapx = ((zzcs)localObject);
    zzapt = new AppMeasurement(this);
    localObject = new zzeq(this);
    ((Log)localObject).logError();
    zzaps = ((zzeq)localObject);
    localObject = new zzbo(this);
    ((zzcp)localObject).reloadPreferences();
    zzapr = ((zzbo)localObject);
    if ((zzri.getApplicationContext() instanceof Application))
    {
      localObject = zzge();
      if ((((zzco)localObject).getContext().getApplicationContext() instanceof Application))
      {
        Application localApplication = (Application)((zzco)localObject).getContext().getApplicationContext();
        if (zzaqv == null) {
          zzaqv = new zzdm((zzcs)localObject, null);
        }
        localApplication.unregisterActivityLifecycleCallbacks(zzaqv);
        localApplication.registerActivityLifecycleCallbacks(zzaqv);
        ((zzco)localObject).zzgo().zzjl().zzbx("Registered activity lifecycle callback");
      }
    }
    else
    {
      zzgo().zzjg().zzbx("Application context is not an Application");
    }
    zzapr.get(new zzbu(this, paramZzcr));
  }
  
  public static zzbt register(Context paramContext, zzak paramZzak)
  {
    zzak localZzak = paramZzak;
    if (paramZzak != null) {
      if (origin != null)
      {
        localZzak = paramZzak;
        if (zzadx != null) {}
      }
      else
      {
        localZzak = new zzak(zzadt, zzadu, zzadv, zzadw, null, null, zzady);
      }
    }
    Preconditions.checkNotNull(paramContext);
    Preconditions.checkNotNull(paramContext.getApplicationContext());
    if (zzapl == null) {
      try
      {
        if (zzapl == null) {
          zzapl = new zzbt(new zzcr(paramContext, localZzak));
        }
      }
      catch (Throwable paramContext)
      {
        throw paramContext;
      }
    } else if ((localZzak != null) && (zzady != null) && (zzady.containsKey("dataCollectionDefaultEnabled"))) {
      zzapl.checkState(zzady.getBoolean("dataCollectionDefaultEnabled"));
    }
    return zzapl;
  }
  
  private static void seek(Log paramLog)
  {
    if (paramLog != null)
    {
      if (paramLog.isInitialized()) {
        return;
      }
      paramLog = String.valueOf(paramLog.getClass());
      StringBuilder localStringBuilder = new StringBuilder(String.valueOf(paramLog).length() + 27);
      localStringBuilder.append("Component not initialized: ");
      localStringBuilder.append(paramLog);
      throw new IllegalStateException(localStringBuilder.toString());
    }
    throw new IllegalStateException("Component not created");
  }
  
  private static void seek(zzcp paramZzcp)
  {
    if (paramZzcp != null)
    {
      if (paramZzcp.isInitialized()) {
        return;
      }
      paramZzcp = String.valueOf(paramZzcp.getClass());
      StringBuilder localStringBuilder = new StringBuilder(String.valueOf(paramZzcp).length() + 27);
      localStringBuilder.append("Component not initialized: ");
      localStringBuilder.append(paramZzcp);
      throw new IllegalStateException(localStringBuilder.toString());
    }
    throw new IllegalStateException("Component not created");
  }
  
  private final void setPreference(zzcr paramZzcr)
  {
    zzgn().zzaf();
    class_3.zzht();
    paramZzcr = new class_6(this);
    paramZzcr.reloadPreferences();
    zzaqb = paramZzcr;
    paramZzcr = new zzaj(this);
    paramZzcr.logError();
    zzaqc = paramZzcr;
    Object localObject = new zzal(this);
    ((Log)localObject).logError();
    zzapz = ((zzal)localObject);
    localObject = new zzdr(this);
    ((Log)localObject).logError();
    zzaqa = ((zzdr)localObject);
    zzapu.zzgs();
    zzapp.zzgs();
    zzaqd = new zzbg(this);
    zzaqc.zzgs();
    zzgo().zzjj().append("App measurement is starting up, version", Long.valueOf(zzapo.zzhc()));
    zzgo().zzjj().zzbx("To enable debug logging run: adb shell setprop log.tag.FA VERBOSE");
    paramZzcr = paramZzcr.zzal();
    if (TextUtils.isEmpty(zzadx))
    {
      if (zzgm().zzcw(paramZzcr))
      {
        localObject = zzgo().zzjj();
        paramZzcr = "Faster debug mode event logging enabled. To disable, run:\n  adb shell setprop debug.firebase.analytics.app .none.";
      }
      else
      {
        localObject = zzgo().zzjj();
        paramZzcr = String.valueOf(paramZzcr);
        if (paramZzcr.length() != 0) {
          paramZzcr = "To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app ".concat(paramZzcr);
        } else {
          paramZzcr = new String("To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app ");
        }
      }
      ((zzar)localObject).zzbx(paramZzcr);
    }
    zzgo().zzjk().zzbx("Debug-level message logging enabled");
    if (zzaqh != zzaqi) {
      zzgo().zzjd().append("Not all components initialized", Integer.valueOf(zzaqh), Integer.valueOf(zzaqi));
    }
    zzvz = true;
  }
  
  private static void toJsonString(zzco paramZzco)
  {
    if (paramZzco != null) {
      return;
    }
    throw new IllegalStateException("Component not created");
  }
  
  private final void zzcl()
  {
    if (zzvz) {
      return;
    }
    throw new IllegalStateException("AppMeasurement is not initialized");
  }
  
  final void attribute(boolean paramBoolean)
  {
    zzgn().zzaf();
    Object localObject = zzgpzzans.zzjz();
    if ((!paramBoolean) && (localObject != null)) {
      if ("unset".equals(localObject))
      {
        zzge().attribute("app", "_ap", null, zzrz.currentTimeMillis());
      }
      else
      {
        zzge().attribute("app", "_ap", localObject, zzrz.currentTimeMillis());
        i = 0;
        break label96;
      }
    }
    int i = 1;
    label96:
    if (i != 0)
    {
      localObject = zzapo.zzau("google_analytics_default_allow_ad_personalization_signals");
      if (localObject != null)
      {
        zzcs localZzcs = zzge();
        long l;
        if (((Boolean)localObject).booleanValue()) {
          l = 1L;
        } else {
          l = 0L;
        }
        localZzcs.attribute("auto", "_ap", Long.valueOf(l), zzrz.currentTimeMillis());
        return;
      }
      zzge().attribute("auto", "_ap", null, zzrz.currentTimeMillis());
    }
  }
  
  final void checkState(boolean paramBoolean)
  {
    zzaqg = Boolean.valueOf(paramBoolean);
  }
  
  public final Context getContext()
  {
    return zzri;
  }
  
  final void intersect(zzcp paramZzcp)
  {
    zzaqh += 1;
  }
  
  public final boolean isEnabled()
  {
    zzgn().zzaf();
    zzcl();
    if (zzapo.zzhu()) {
      return false;
    }
    Boolean localBoolean = zzapo.zzhv();
    boolean bool1;
    if (localBoolean != null)
    {
      bool1 = localBoolean.booleanValue();
    }
    else
    {
      boolean bool2 = GoogleServices.isMeasurementExplicitlyDisabled() ^ true;
      bool1 = bool2;
      if (bool2)
      {
        bool1 = bool2;
        if (zzaqg != null)
        {
          bool1 = bool2;
          if (((Boolean)zzaf.zzalh.getDefaultValue()).booleanValue()) {
            bool1 = zzaqg.booleanValue();
          }
        }
      }
    }
    return zzgp().getBool(bool1);
  }
  
  protected final void start()
  {
    zzgn().zzaf();
    if (zzgpzzane.readLong() == 0L) {
      zzgpzzane.getFolder(zzrz.currentTimeMillis());
    }
    if (Long.valueOf(zzgpzzanj.readLong()).longValue() == 0L)
    {
      zzgo().zzjl().append("Persisting first open", Long.valueOf(zzagx));
      zzgpzzanj.getFolder(zzagx);
    }
    if (!zzkr())
    {
      if (isEnabled())
      {
        if (!zzgm().checkVersion("android.permission.INTERNET")) {
          zzgo().zzjd().zzbx("App is missing INTERNET permission");
        }
        if (!zzgm().checkVersion("android.permission.ACCESS_NETWORK_STATE")) {
          zzgo().zzjd().zzbx("App is missing ACCESS_NETWORK_STATE permission");
        }
        if ((!Wrappers.packageManager(zzri).isCallerInstantApp()) && (!zzapo.zzib()))
        {
          if (!zzbj.isSystemApp(zzri)) {
            zzgo().zzjd().zzbx("AppMeasurementReceiver not registered/enabled");
          }
          if (!zzfk.set(zzri, false)) {
            zzgo().zzjd().zzbx("AppMeasurementService not registered/enabled");
          }
        }
        zzgo().zzjd().zzbx("Uploading is not possible. App measurement disabled");
      }
    }
    else
    {
      if ((!TextUtils.isEmpty(zzgf().getGmpAppId())) || (!TextUtils.isEmpty(zzgf().zzgw())))
      {
        zzgm();
        if (zzfk.next(zzgf().getGmpAppId(), zzgp().zzjs(), zzgf().zzgw(), zzgp().zzjt()))
        {
          zzgo().zzjj().zzbx("Rechecking which service to use due to a GMP App Id change");
          zzgp().zzjv();
          if (zzapo.isTrue(zzaf.zzalc)) {
            zzgi().resetAnalyticsData();
          }
          zzaqa.disconnect();
          zzaqa.zzdj();
          zzgpzzanj.getFolder(zzagx);
          zzgpzzanl.zzcc(null);
        }
        zzgp().zzca(zzgf().getGmpAppId());
        zzgp().zzcb(zzgf().zzgw());
        if (zzapo.zzbj(zzgf().zzal())) {
          zzaps.zzam(zzagx);
        }
      }
      zzge().zzcm(zzgpzzanl.zzjz());
      if ((!TextUtils.isEmpty(zzgf().getGmpAppId())) || (!TextUtils.isEmpty(zzgf().zzgw())))
      {
        boolean bool = isEnabled();
        if ((!zzgp().zzjy()) && (!zzapo.zzhu())) {
          zzgp().startService(bool ^ true);
        }
        if (zzapo.attribute(zzgf().zzal(), zzaf.zzalj)) {
          attribute(false);
        }
        if ((!zzapo.zzbd(zzgf().zzal())) || (bool)) {
          zzge().zzkz();
        }
        zzgg().terminate(new AtomicReference());
      }
    }
  }
  
  final void writeShort(Log paramLog)
  {
    zzaqh += 1;
  }
  
  public final Clock zzbx()
  {
    return zzrz;
  }
  
  final void zzga()
  {
    throw new IllegalStateException("Unexpected call on client side");
  }
  
  final void zzgb() {}
  
  public final FileTransfer zzgd()
  {
    if (zzapy != null) {
      return zzapy;
    }
    throw new IllegalStateException("Component not created");
  }
  
  public final zzcs zzge()
  {
    seek(zzapx);
    return zzapx;
  }
  
  public final zzaj zzgf()
  {
    seek(zzaqc);
    return zzaqc;
  }
  
  public final zzdr zzgg()
  {
    seek(zzaqa);
    return zzaqa;
  }
  
  public final zzdo zzgh()
  {
    seek(zzapw);
    return zzapw;
  }
  
  public final zzal zzgi()
  {
    seek(zzapz);
    return zzapz;
  }
  
  public final zzeq zzgj()
  {
    seek(zzaps);
    return zzaps;
  }
  
  public final class_6 zzgk()
  {
    seek(zzaqb);
    return zzaqb;
  }
  
  public final zzan zzgl()
  {
    toJsonString(zzapv);
    return zzapv;
  }
  
  public final zzfk zzgm()
  {
    toJsonString(zzapu);
    return zzapu;
  }
  
  public final zzbo zzgn()
  {
    seek(zzapr);
    return zzapr;
  }
  
  public final zzap zzgo()
  {
    seek(zzapq);
    return zzapq;
  }
  
  public final zzba zzgp()
  {
    toJsonString(zzapp);
    return zzapp;
  }
  
  public final class_3 zzgq()
  {
    return zzapo;
  }
  
  public final MultiMap zzgr()
  {
    return zzaiq;
  }
  
  public final zzap zzkf()
  {
    if ((zzapq != null) && (zzapq.isInitialized())) {
      return zzapq;
    }
    return null;
  }
  
  public final zzbg zzkg()
  {
    return zzaqd;
  }
  
  final zzbo zzkh()
  {
    return zzapr;
  }
  
  public final AppMeasurement zzki()
  {
    return zzapt;
  }
  
  public final boolean zzkj()
  {
    return TextUtils.isEmpty(zzadx);
  }
  
  public final String zzkk()
  {
    return zzadx;
  }
  
  public final String zzkl()
  {
    return zzapm;
  }
  
  public final String zzkm()
  {
    return zzapn;
  }
  
  public final boolean zzkn()
  {
    return zzadv;
  }
  
  public final boolean zzko()
  {
    return (zzaqg != null) && (zzaqg.booleanValue());
  }
  
  final long zzkp()
  {
    Long localLong = Long.valueOf(zzgpzzanj.readLong());
    if (localLong.longValue() == 0L) {
      return zzagx;
    }
    return Math.min(zzagx, localLong.longValue());
  }
  
  final void zzkq()
  {
    zzaqi += 1;
  }
  
  protected final boolean zzkr()
  {
    zzcl();
    zzgn().zzaf();
    if ((zzaqe == null) || (zzaqf == 0L) || ((zzaqe != null) && (!zzaqe.booleanValue()) && (Math.abs(zzrz.elapsedRealtime() - zzaqf) > 1000L)))
    {
      zzaqf = zzrz.elapsedRealtime();
      boolean bool1 = zzgm().checkVersion("android.permission.INTERNET");
      boolean bool2 = true;
      if ((bool1) && (zzgm().checkVersion("android.permission.ACCESS_NETWORK_STATE")) && ((Wrappers.packageManager(zzri).isCallerInstantApp()) || (zzapo.zzib()) || ((zzbj.isSystemApp(zzri)) && (zzfk.set(zzri, false))))) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      zzaqe = Boolean.valueOf(bool1);
      if (zzaqe.booleanValue())
      {
        bool1 = bool2;
        if (!zzgm().getDisplayTitle(zzgf().getGmpAppId(), zzgf().zzgw())) {
          if (!TextUtils.isEmpty(zzgf().zzgw())) {
            bool1 = bool2;
          } else {
            bool1 = false;
          }
        }
        zzaqe = Boolean.valueOf(bool1);
      }
    }
    return zzaqe.booleanValue();
  }
}
