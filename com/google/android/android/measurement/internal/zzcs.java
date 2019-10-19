package com.google.android.android.measurement.internal;

import android.app.Application;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.android.common.aimsicd.internal.GoogleServices;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.common.util.Clock;
import com.google.android.android.common.util.CollectionUtils;
import com.google.android.android.measurement.AppMeasurement.ConditionalUserProperty;
import com.google.android.android.measurement.AppMeasurement.Event;
import com.google.android.android.measurement.AppMeasurement.EventInterceptor;
import com.google.android.android.measurement.AppMeasurement.UserProperty;
import com.google.android.gms.common.util.VisibleForTesting;
import java.lang.reflect.Method;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicReference;

public final class zzcs
  extends Log
{
  @VisibleForTesting
  protected zzdm zzaqv;
  private AppMeasurement.EventInterceptor zzaqw;
  private final Set<com.google.android.gms.measurement.AppMeasurement.OnEventListener> zzaqx = new CopyOnWriteArraySet();
  private boolean zzaqy;
  private final AtomicReference<String> zzaqz = new AtomicReference();
  @VisibleForTesting
  protected boolean zzara = true;
  
  protected zzcs(zzbt paramZzbt)
  {
    super(paramZzbt);
  }
  
  private final void authorize(AppMeasurement.ConditionalUserProperty paramConditionalUserProperty)
  {
    zzaf();
    zzcl();
    Preconditions.checkNotNull(paramConditionalUserProperty);
    Preconditions.checkNotEmpty(mName);
    if (!zzadj.isEnabled())
    {
      zzgo().zzjk().zzbx("Conditional property not cleared since collection is disabled");
      return;
    }
    zzfh localZzfh = new zzfh(mName, 0L, null, null);
    try
    {
      Object localObject = zzgm();
      String str1 = mAppId;
      String str2 = mExpiredEventName;
      Bundle localBundle = mExpiredEventParams;
      String str3 = mOrigin;
      long l = mCreationTimestamp;
      localObject = ((zzfk)localObject).e(str1, str2, localBundle, str3, l, true, false);
      paramConditionalUserProperty = new ComponentInfo(mAppId, mOrigin, localZzfh, mCreationTimestamp, mActive, mTriggerEventName, null, mTriggerTimeout, null, mTimeToLive, (zzad)localObject);
      zzgg().toggleState(paramConditionalUserProperty);
      return;
    }
    catch (IllegalArgumentException paramConditionalUserProperty) {}
  }
  
  private final void download(String paramString1, String paramString2, String paramString3, Bundle paramBundle)
  {
    long l = zzbx().currentTimeMillis();
    Preconditions.checkNotEmpty(paramString2);
    AppMeasurement.ConditionalUserProperty localConditionalUserProperty = new AppMeasurement.ConditionalUserProperty();
    mAppId = paramString1;
    mName = paramString2;
    mCreationTimestamp = l;
    if (paramString3 != null)
    {
      mExpiredEventName = paramString3;
      mExpiredEventParams = paramBundle;
    }
    zzgn().get(new zzdb(this, localConditionalUserProperty));
  }
  
  private final void e(String paramString1, String paramString2, long paramLong, Bundle paramBundle, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, String paramString3)
  {
    paramBundle = zzfk.set(paramBundle);
    zzgn().get(new zzcu(this, paramString1, paramString2, paramLong, paramBundle, paramBoolean1, paramBoolean2, paramBoolean3, paramString3));
  }
  
  private final void e(String paramString1, String paramString2, long paramLong, Object paramObject)
  {
    zzgn().get(new zzcv(this, paramString1, paramString2, paramObject, paramLong));
  }
  
  private final List getHeaders(String paramString1, String paramString2, String paramString3)
  {
    if (zzgn().zzkb())
    {
      zzgo().zzjd().zzbx("Cannot get conditional user properties from analytics worker thread");
      return Collections.emptyList();
    }
    if (MultiMap.isMainThread())
    {
      zzgo().zzjd().zzbx("Cannot get conditional user properties from main thread");
      return Collections.emptyList();
    }
    Object localObject = new AtomicReference();
    try
    {
      zzadj.zzgn().get(new zzdc(this, (AtomicReference)localObject, paramString1, paramString2, paramString3));
      try
      {
        localObject.wait(5000L);
      }
      catch (InterruptedException paramString2)
      {
        zzgo().zzjg().append("Interrupted waiting for get conditional user properties", paramString1, paramString2);
      }
      paramString2 = (List)((AtomicReference)localObject).get();
      if (paramString2 == null)
      {
        zzgo().zzjg().append("Timed out waiting for get conditional user properties", paramString1);
        return Collections.emptyList();
      }
      paramString1 = new ArrayList(paramString2.size());
      paramString2 = paramString2.iterator();
      while (paramString2.hasNext())
      {
        paramString3 = (ComponentInfo)paramString2.next();
        localObject = new AppMeasurement.ConditionalUserProperty();
        mAppId = packageName;
        mOrigin = origin;
        mCreationTimestamp = creationTimestamp;
        mName = zzahb.name;
        mValue = zzahb.getValue();
        mActive = active;
        mTriggerEventName = triggerEventName;
        if (zzahc != null)
        {
          mTimedOutEventName = zzahc.name;
          if (zzahc.zzaid != null) {
            mTimedOutEventParams = zzahc.zzaid.zziv();
          }
        }
        mTriggerTimeout = triggerTimeout;
        if (zzahd != null)
        {
          mTriggeredEventName = zzahd.name;
          if (zzahd.zzaid != null) {
            mTriggeredEventParams = zzahd.zzaid.zziv();
          }
        }
        mTriggeredTimestamp = zzahb.zzaue;
        mTimeToLive = timeToLive;
        if (zzahe != null)
        {
          mExpiredEventName = zzahe.name;
          if (zzahe.zzaid != null) {
            mExpiredEventParams = zzahe.zzaid.zziv();
          }
        }
        paramString1.add(localObject);
      }
      return paramString1;
    }
    catch (Throwable paramString1)
    {
      throw paramString1;
    }
  }
  
  private final Map getHeaders(String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    if (zzgn().zzkb())
    {
      zzgo().zzjd().zzbx("Cannot get user properties from analytics worker thread");
      return Collections.emptyMap();
    }
    if (MultiMap.isMainThread())
    {
      zzgo().zzjd().zzbx("Cannot get user properties from main thread");
      return Collections.emptyMap();
    }
    AtomicReference localAtomicReference = new AtomicReference();
    try
    {
      zzadj.zzgn().get(new zzde(this, localAtomicReference, paramString1, paramString2, paramString3, paramBoolean));
      try
      {
        localAtomicReference.wait(5000L);
      }
      catch (InterruptedException paramString1)
      {
        zzgo().zzjg().append("Interrupted waiting for get user properties", paramString1);
      }
      paramString2 = (List)localAtomicReference.get();
      if (paramString2 == null)
      {
        zzgo().zzjg().zzbx("Timed out waiting for get user properties");
        return Collections.emptyMap();
      }
      paramString1 = new ArrayMap(paramString2.size());
      paramString2 = paramString2.iterator();
      while (paramString2.hasNext())
      {
        paramString3 = (zzfh)paramString2.next();
        paramString1.put(name, paramString3.getValue());
      }
      return paramString1;
    }
    catch (Throwable paramString1)
    {
      throw paramString1;
    }
  }
  
  private final void newInstance(String paramString1, String paramString2, long paramLong, Bundle paramBundle, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, String paramString3)
  {
    Preconditions.checkNotEmpty(paramString1);
    Preconditions.checkNotEmpty(paramString2);
    Preconditions.checkNotNull(paramBundle);
    zzaf();
    zzcl();
    if (!zzadj.isEnabled())
    {
      zzgo().zzjk().zzbx("Event not sent since app measurement is disabled");
      return;
    }
    boolean bool1 = zzaqy;
    int j = 0;
    if (!bool1) {
      zzaqy = true;
    }
    for (;;)
    {
      try
      {
        localObject1 = Class.forName("com.google.android.gms.tagmanager.TagManagerService");
      }
      catch (ClassNotFoundException localClassNotFoundException1)
      {
        Object localObject1;
        Object localObject3;
        Object localObject2;
        int i;
        Object localObject4;
        boolean bool2;
        int k;
        Bundle localBundle1;
        long l;
        String[] arrayOfString;
        int m;
        continue;
      }
      try
      {
        try
        {
          localObject1 = ((Class)localObject1).getDeclaredMethod("initialize", new Class[] { Context.class });
          localObject3 = getContext();
          ((Method)localObject1).invoke(null, new Object[] { localObject3 });
        }
        catch (Exception localException) {}
      }
      catch (ClassNotFoundException localClassNotFoundException2)
      {
        continue;
      }
      try
      {
        zzgo().zzjg().append("Failed to invoke Tag Manager's initialize() method", localException);
      }
      catch (ClassNotFoundException localClassNotFoundException3) {}
    }
    zzgo().zzjj().zzbx("Tag Manager is not found and thus will not be used");
    if (paramBoolean3)
    {
      zzgr();
      if (!"_iap".equals(paramString2))
      {
        localObject2 = zzadj.zzgm();
        if (!((zzfk)localObject2).decode("event", paramString2)) {}
        do
        {
          i = 2;
          break;
          if (!((zzfk)localObject2).verify("event", AppMeasurement.Event.zzadk, paramString2))
          {
            i = 13;
            break;
          }
        } while (!((zzfk)localObject2).add("event", 40, paramString2));
        i = 0;
        if (i != 0)
        {
          zzgo().zzjf().append("Invalid public event name. Event will not be logged (FE)", zzgl().zzbs(paramString2));
          zzadj.zzgm();
          paramString1 = zzfk.get(paramString2, 40, true);
          if (paramString2 != null) {
            j = paramString2.length();
          } else {
            j = 0;
          }
          zzadj.zzgm().append(i, "_ev", paramString1, j);
          return;
        }
      }
    }
    zzgr();
    localObject4 = zzgh().zzla();
    if ((localObject4 != null) && (!paramBundle.containsKey("_sc"))) {
      zzarn = true;
    }
    if ((paramBoolean1) && (paramBoolean3)) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    zzdo.set((zzdn)localObject4, paramBundle, bool1);
    bool1 = "am".equals(paramString1);
    bool2 = zzfk.zzcv(paramString2);
    if ((paramBoolean1) && (zzaqw != null) && (!bool2) && (!bool1))
    {
      zzgo().zzjk().append("Passing event to registered event handler (FE)", zzgl().zzbs(paramString2), zzgl().toString(paramBundle));
      zzaqw.interceptEvent(paramString1, paramString2, paramBundle, paramLong);
      return;
    }
    if (!zzadj.zzkr()) {
      return;
    }
    k = zzgm().zzcr(paramString2);
    if (k != 0)
    {
      zzgo().zzjf().append("Invalid event name. Event will not be logged (FE)", zzgl().zzbs(paramString2));
      zzgm();
      paramString1 = zzfk.get(paramString2, 40, true);
      i = j;
      if (paramString2 != null) {
        i = paramString2.length();
      }
      zzadj.zzgm().add(paramString3, k, "_ev", paramString1, i);
      return;
    }
    localObject2 = CollectionUtils.listOf(new String[] { "_o", "_sn", "_sc", "_si" });
    localObject3 = localObject2;
    localBundle1 = zzgm().parse(paramString3, paramString2, paramBundle, (List)localObject2, paramBoolean3, true);
    if ((localBundle1 != null) && (localBundle1.containsKey("_sc")) && (localBundle1.containsKey("_si"))) {
      localObject2 = new zzdn(localBundle1.getString("_sn"), localBundle1.getString("_sc"), Long.valueOf(localBundle1.getLong("_si")).longValue());
    } else {
      localObject2 = null;
    }
    if (localObject2 == null) {
      localObject2 = localObject4;
    }
    localObject4 = new ArrayList();
    ((List)localObject4).add(localBundle1);
    l = zzgm().zzmd().nextLong();
    arrayOfString = (String[])localBundle1.keySet().toArray(new String[paramBundle.size()]);
    Arrays.sort(arrayOfString);
    m = arrayOfString.length;
    j = 0;
    i = 0;
    paramBundle = (Bundle)localObject4;
    while (i < m)
    {
      localObject4 = arrayOfString[i];
      Object localObject5 = localBundle1.get((String)localObject4);
      zzgm();
      localObject5 = zzfk.get(localObject5);
      if (localObject5 != null)
      {
        localBundle1.putInt((String)localObject4, localObject5.length);
        k = 0;
        while (k < localObject5.length)
        {
          Bundle localBundle2 = localObject5[k];
          zzdo.set((zzdn)localObject2, localBundle2, true);
          localBundle2 = zzgm().parse(paramString3, "_ep", localBundle2, (List)localObject3, paramBoolean3, false);
          localBundle2.putString("_en", paramString2);
          localBundle2.putLong("_eid", l);
          localBundle2.putString("_gn", (String)localObject4);
          localBundle2.putInt("_ll", localObject5.length);
          localBundle2.putInt("_i", k);
          paramBundle.add(localBundle2);
          k += 1;
        }
        j += localObject5.length;
      }
      i += 1;
    }
    if (j != 0)
    {
      localBundle1.putLong("_eid", l);
      localBundle1.putInt("_epc", j);
    }
    i = 0;
    while (i < paramBundle.size())
    {
      localObject4 = (Bundle)paramBundle.get(i);
      if (i != 0) {
        j = 1;
      } else {
        j = 0;
      }
      if (j != 0) {
        localObject3 = "_ep";
      } else {
        localObject3 = paramString2;
      }
      ((Bundle)localObject4).putString("_o", paramString1);
      localObject2 = localObject4;
      if (paramBoolean2) {
        localObject2 = zzgm().newInstance((Bundle)localObject4);
      }
      zzgo().zzjk().append("Logging event (FE)", zzgl().zzbs(paramString2), zzgl().toString((Bundle)localObject2));
      localObject3 = new zzad((String)localObject3, new zzaa((Bundle)localObject2), paramString1, paramLong);
      zzgg().addProperty((zzad)localObject3, paramString3);
      if (!bool1)
      {
        localObject3 = zzaqx.iterator();
        while (((Iterator)localObject3).hasNext()) {
          ((com.google.android.android.measurement.AppMeasurement.OnEventListener)((Iterator)localObject3).next()).onEvent(paramString1, paramString2, new Bundle((Bundle)localObject2), paramLong);
        }
      }
      i += 1;
    }
    zzgr();
    if ((zzgh().zzla() != null) && ("_ae".equals(paramString2)))
    {
      zzgj().setAlarm(true);
      return;
    }
  }
  
  private final void populateView(AppMeasurement.ConditionalUserProperty paramConditionalUserProperty)
  {
    long l = zzbx().currentTimeMillis();
    Preconditions.checkNotNull(paramConditionalUserProperty);
    Preconditions.checkNotEmpty(mName);
    Preconditions.checkNotEmpty(mOrigin);
    Preconditions.checkNotNull(mValue);
    mCreationTimestamp = l;
    String str = mName;
    Object localObject1 = mValue;
    if (zzgm().zzcs(str) != 0)
    {
      zzgo().zzjd().append("Invalid conditional user property name", zzgl().zzbu(str));
      return;
    }
    if (zzgm().get(str, localObject1) != 0)
    {
      zzgo().zzjd().append("Invalid conditional user property value", zzgl().zzbu(str), localObject1);
      return;
    }
    Object localObject2 = zzgm().toString(str, localObject1);
    if (localObject2 == null)
    {
      zzgo().zzjd().append("Unable to normalize conditional user property value", zzgl().zzbu(str), localObject1);
      return;
    }
    mValue = localObject2;
    l = mTriggerTimeout;
    if ((!TextUtils.isEmpty(mTriggerEventName)) && ((l > 15552000000L) || (l < 1L)))
    {
      zzgo().zzjd().append("Invalid conditional user property timeout", zzgl().zzbu(str), Long.valueOf(l));
      return;
    }
    l = mTimeToLive;
    if ((l <= 15552000000L) && (l >= 1L))
    {
      zzgn().get(new zzda(this, paramConditionalUserProperty));
      return;
    }
    zzgo().zzjd().append("Invalid conditional user property time to live", zzgl().zzbu(str), Long.valueOf(l));
  }
  
  private final void setPreference(AppMeasurement.ConditionalUserProperty paramConditionalUserProperty)
  {
    zzaf();
    zzcl();
    Preconditions.checkNotNull(paramConditionalUserProperty);
    Preconditions.checkNotEmpty(mName);
    Preconditions.checkNotEmpty(mOrigin);
    Preconditions.checkNotNull(mValue);
    if (!zzadj.isEnabled())
    {
      zzgo().zzjk().zzbx("Conditional property not sent since collection is disabled");
      return;
    }
    zzfh localZzfh = new zzfh(mName, mTriggeredTimestamp, mValue, mOrigin);
    try
    {
      Object localObject1 = zzgm();
      Object localObject2 = mAppId;
      Object localObject3 = mTriggeredEventName;
      Object localObject4 = mTriggeredEventParams;
      Object localObject5 = mOrigin;
      localObject1 = ((zzfk)localObject1).e((String)localObject2, (String)localObject3, (Bundle)localObject4, (String)localObject5, 0L, true, false);
      localObject2 = zzgm();
      localObject3 = mAppId;
      localObject4 = mTimedOutEventName;
      localObject5 = mTimedOutEventParams;
      Object localObject6 = mOrigin;
      localObject2 = ((zzfk)localObject2).e((String)localObject3, (String)localObject4, (Bundle)localObject5, (String)localObject6, 0L, true, false);
      localObject3 = zzgm();
      localObject4 = mAppId;
      localObject5 = mExpiredEventName;
      localObject6 = mExpiredEventParams;
      String str = mOrigin;
      localObject3 = ((zzfk)localObject3).e((String)localObject4, (String)localObject5, (Bundle)localObject6, str, 0L, true, false);
      paramConditionalUserProperty = new ComponentInfo(mAppId, mOrigin, localZzfh, mCreationTimestamp, false, mTriggerEventName, (zzad)localObject2, mTriggerTimeout, (zzad)localObject1, mTimeToLive, (zzad)localObject3);
      zzgg().toggleState(paramConditionalUserProperty);
      return;
    }
    catch (IllegalArgumentException paramConditionalUserProperty) {}
  }
  
  private final void updateSummary(boolean paramBoolean)
  {
    zzaf();
    zzgb();
    zzcl();
    zzgo().zzjk().append("Setting app measurement enabled (FE)", Boolean.valueOf(paramBoolean));
    zzgp().setMeasurementEnabled(paramBoolean);
    zzky();
  }
  
  private final String zzak(long paramLong)
  {
    localAtomicReference = new AtomicReference();
    try
    {
      zzgn().get(new zzcy(this, localAtomicReference));
    }
    catch (Throwable localThrowable)
    {
      label41:
      throw localThrowable;
    }
    try
    {
      localAtomicReference.wait(paramLong);
      return (String)localAtomicReference.get();
    }
    catch (InterruptedException localInterruptedException)
    {
      break label41;
    }
    zzgo().zzjg().zzbx("Interrupted waiting for app instance id");
    return null;
  }
  
  private final void zzky()
  {
    if (zzgq().attribute(zzgf().zzal(), zzaf.zzalj)) {
      zzadj.attribute(false);
    }
    if ((zzgq().zzbd(zzgf().zzal())) && (zzadj.isEnabled()) && (zzara))
    {
      zzgo().zzjk().zzbx("Recording app launch after enabling measurement for the first time (FE)");
      zzkz();
      return;
    }
    zzgo().zzjk().zzbx("Updating Scion state (FE)");
    zzgg().zzlc();
  }
  
  final void attribute(String paramString1, String paramString2, long paramLong, Bundle paramBundle)
  {
    zzgb();
    zzaf();
    boolean bool;
    if ((zzaqw != null) && (!zzfk.zzcv(paramString2))) {
      bool = false;
    } else {
      bool = true;
    }
    newInstance(paramString1, paramString2, paramLong, paramBundle, true, bool, false, null);
  }
  
  final void attribute(String paramString1, String paramString2, Object paramObject, long paramLong)
  {
    Preconditions.checkNotEmpty(paramString1);
    Preconditions.checkNotEmpty(paramString2);
    zzaf();
    zzgb();
    zzcl();
    Object localObject;
    if (zzgq().attribute(zzgf().zzal(), zzaf.zzalj))
    {
      localObject = paramObject;
      if ("_ap".equals(paramString2))
      {
        localObject = paramObject;
        if (!"auto".equals(paramString1))
        {
          if ((paramObject instanceof String))
          {
            localObject = (String)paramObject;
            if (!TextUtils.isEmpty((CharSequence)localObject))
            {
              long l;
              if ((!"true".equals(((String)localObject).toLowerCase(Locale.ENGLISH))) && (!"1".equals(paramObject))) {
                l = 0L;
              } else {
                l = 1L;
              }
              localObject = Long.valueOf(l);
              paramObject = localObject;
              zzbf localZzbf = zzgpzzans;
              if (((Long)localObject).longValue() == 1L) {
                localObject = "true";
              } else {
                localObject = "false";
              }
              localZzbf.zzcc((String)localObject);
              localObject = paramObject;
              break label241;
            }
          }
          localObject = paramObject;
          if (paramObject == null)
          {
            zzgpzzans.zzcc("unset");
            zzgn().get(new zzcw(this));
            localObject = paramObject;
          }
        }
      }
    }
    else
    {
      localObject = paramObject;
      if ("_ap".equals(paramString2)) {
        return;
      }
    }
    label241:
    if (!zzadj.isEnabled())
    {
      zzgo().zzjk().zzbx("User property not set since app measurement is disabled");
      return;
    }
    if (!zzadj.zzkr()) {
      return;
    }
    zzgo().zzjk().append("Setting user property (FE)", zzgl().zzbs(paramString2), localObject);
    paramString1 = new zzfh(paramString2, paramLong, localObject, paramString1);
    zzgg().setPreference(paramString1);
  }
  
  public final void clearConditionalUserProperty(String paramString1, String paramString2, Bundle paramBundle)
  {
    zzgb();
    download(null, paramString1, paramString2, paramBundle);
  }
  
  public final void clearConditionalUserPropertyAs(String paramString1, String paramString2, String paramString3, Bundle paramBundle)
  {
    Preconditions.checkNotEmpty(paramString1);
    zzga();
    download(paramString1, paramString2, paramString3, paramBundle);
  }
  
  public final void e(String paramString1, String paramString2, Bundle paramBundle, boolean paramBoolean)
  {
    logEvent(paramString1, paramString2, paramBundle, false, true, zzbx().currentTimeMillis());
  }
  
  public final void e(boolean paramBoolean)
  {
    zzcl();
    zzgb();
    zzgn().get(new zzdj(this, paramBoolean));
  }
  
  public final List getConditionalUserProperties(String paramString1, String paramString2)
  {
    zzgb();
    return getHeaders(null, paramString1, paramString2);
  }
  
  public final List getConditionalUserPropertiesAs(String paramString1, String paramString2, String paramString3)
  {
    Preconditions.checkNotEmpty(paramString1);
    zzga();
    return getHeaders(paramString1, paramString2, paramString3);
  }
  
  public final String getCurrentScreenClass()
  {
    zzdn localZzdn = zzadj.zzgh().zzlb();
    if (localZzdn != null) {
      return zzarl;
    }
    return null;
  }
  
  public final String getCurrentScreenName()
  {
    zzdn localZzdn = zzadj.zzgh().zzlb();
    if (localZzdn != null) {
      return zzuw;
    }
    return null;
  }
  
  public final String getGmpAppId()
  {
    if (zzadj.zzkk() != null) {
      return zzadj.zzkk();
    }
    try
    {
      String str = GoogleServices.getGoogleAppId();
      return str;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      zzadj.zzgo().zzjd().append("getGoogleAppId failed with exception", localIllegalStateException);
    }
    return null;
  }
  
  public final List getHeaders(boolean paramBoolean)
  {
    zzgb();
    zzcl();
    zzgo().zzjk().zzbx("Fetching user attributes (FE)");
    if (zzgn().zzkb())
    {
      zzgo().zzjd().zzbx("Cannot get all user properties from analytics worker thread");
      return Collections.emptyList();
    }
    if (MultiMap.isMainThread())
    {
      zzgo().zzjd().zzbx("Cannot get all user properties from main thread");
      return Collections.emptyList();
    }
    Object localObject = new AtomicReference();
    try
    {
      zzadj.zzgn().get(new zzcx(this, (AtomicReference)localObject, paramBoolean));
      try
      {
        localObject.wait(5000L);
      }
      catch (InterruptedException localInterruptedException)
      {
        zzgo().zzjg().append("Interrupted waiting for get user properties", localInterruptedException);
      }
      List localList = (List)((AtomicReference)localObject).get();
      localObject = localList;
      if (localList == null)
      {
        zzgo().zzjg().zzbx("Timed out waiting for get user properties");
        localObject = Collections.emptyList();
      }
      return localObject;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public final Map getUserProperties(String paramString1, String paramString2, boolean paramBoolean)
  {
    zzgb();
    return getHeaders(null, paramString1, paramString2, paramBoolean);
  }
  
  public final Map getUserPropertiesAs(String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    Preconditions.checkNotEmpty(paramString1);
    zzga();
    return getHeaders(paramString1, paramString2, paramString3, paramBoolean);
  }
  
  public final void load(String paramString1, String paramString2, Object paramObject, boolean paramBoolean, long paramLong)
  {
    String str = paramString1;
    if (paramString1 == null) {
      str = "app";
    }
    int i = 6;
    int j = 0;
    int k = 0;
    if ((!paramBoolean) && (!"_ap".equals(paramString2)))
    {
      paramString1 = zzgm();
      if (paramString1.decode("user property", paramString2)) {
        if (!paramString1.verify("user property", AppMeasurement.UserProperty.zzado, paramString2)) {
          i = 15;
        } else if (paramString1.add("user property", 24, paramString2)) {
          i = 0;
        }
      }
    }
    else
    {
      i = zzgm().zzcs(paramString2);
    }
    if (i != 0)
    {
      zzgm();
      paramString1 = zzfk.get(paramString2, 24, true);
      j = k;
      if (paramString2 != null) {
        j = paramString2.length();
      }
      zzadj.zzgm().append(i, "_ev", paramString1, j);
      return;
    }
    if (paramObject != null)
    {
      k = zzgm().get(paramString2, paramObject);
      if (k != 0)
      {
        zzgm();
        paramString1 = zzfk.get(paramString2, 24, true);
        if (!(paramObject instanceof String))
        {
          i = j;
          if (!(paramObject instanceof CharSequence)) {}
        }
        else
        {
          i = String.valueOf(paramObject).length();
        }
        zzadj.zzgm().append(k, "_ev", paramString1, i);
        return;
      }
      paramString1 = zzgm().toString(paramString2, paramObject);
      if (paramString1 != null) {
        e(str, paramString2, paramLong, paramString1);
      }
    }
    else
    {
      e(str, paramString2, paramLong, null);
    }
  }
  
  public final void logEvent(String paramString1, String paramString2, Bundle paramBundle)
  {
    logEvent(paramString1, paramString2, paramBundle, true, true, zzbx().currentTimeMillis());
  }
  
  public final void logEvent(String paramString1, String paramString2, Bundle paramBundle, boolean paramBoolean1, boolean paramBoolean2, long paramLong)
  {
    zzgb();
    String str = paramString1;
    if (paramString1 == null) {
      str = "app";
    }
    paramString1 = paramBundle;
    if (paramBundle == null) {
      paramString1 = new Bundle();
    }
    boolean bool;
    if ((paramBoolean2) && (zzaqw != null) && (!zzfk.zzcv(paramString2))) {
      bool = false;
    } else {
      bool = true;
    }
    e(str, paramString2, paramLong, paramString1, paramBoolean2, bool, paramBoolean1 ^ true, null);
  }
  
  public final void open(String paramString1, String paramString2, Object paramObject, boolean paramBoolean)
  {
    load(paramString1, paramString2, paramObject, paramBoolean, zzbx().currentTimeMillis());
  }
  
  public final void registerOnMeasurementEventListener(com.google.android.android.measurement.AppMeasurement.OnEventListener paramOnEventListener)
  {
    zzgb();
    zzcl();
    Preconditions.checkNotNull(paramOnEventListener);
    if (!zzaqx.add(paramOnEventListener)) {
      zzgo().zzjg().zzbx("OnEventListener already registered");
    }
  }
  
  public final void resetAnalyticsData(long paramLong)
  {
    if (zzgq().isTrue(zzaf.zzalk)) {
      zzcm(null);
    }
    zzgn().get(new zzcz(this, paramLong));
  }
  
  final void saveToFile(String paramString1, String paramString2, Bundle paramBundle)
  {
    zzgb();
    zzaf();
    attribute(paramString1, paramString2, zzbx().currentTimeMillis(), paramBundle);
  }
  
  public final void setConditionalUserProperty(AppMeasurement.ConditionalUserProperty paramConditionalUserProperty)
  {
    Preconditions.checkNotNull(paramConditionalUserProperty);
    zzgb();
    paramConditionalUserProperty = new AppMeasurement.ConditionalUserProperty(paramConditionalUserProperty);
    if (!TextUtils.isEmpty(mAppId)) {
      zzgo().zzjg().zzbx("Package name should be null when calling setConditionalUserProperty");
    }
    mAppId = null;
    populateView(paramConditionalUserProperty);
  }
  
  public final void setConditionalUserPropertyAs(AppMeasurement.ConditionalUserProperty paramConditionalUserProperty)
  {
    Preconditions.checkNotNull(paramConditionalUserProperty);
    Preconditions.checkNotEmpty(mAppId);
    zzga();
    populateView(new AppMeasurement.ConditionalUserProperty(paramConditionalUserProperty));
  }
  
  public final void setEventInterceptor(AppMeasurement.EventInterceptor paramEventInterceptor)
  {
    zzaf();
    zzgb();
    zzcl();
    if ((paramEventInterceptor != null) && (paramEventInterceptor != zzaqw))
    {
      boolean bool;
      if (zzaqw == null) {
        bool = true;
      } else {
        bool = false;
      }
      Preconditions.checkState(bool, "EventInterceptor already set.");
    }
    zzaqw = paramEventInterceptor;
  }
  
  public final void setMeasurementEnabled(boolean paramBoolean)
  {
    zzcl();
    zzgb();
    zzgn().get(new zzdi(this, paramBoolean));
  }
  
  public final void setMinimumSessionDuration(long paramLong)
  {
    zzgb();
    zzgn().get(new zzdk(this, paramLong));
  }
  
  public final void setSessionTimeoutDuration(long paramLong)
  {
    zzgb();
    zzgn().get(new zzdl(this, paramLong));
  }
  
  public final void unregisterOnMeasurementEventListener(com.google.android.android.measurement.AppMeasurement.OnEventListener paramOnEventListener)
  {
    zzgb();
    zzcl();
    Preconditions.checkNotNull(paramOnEventListener);
    if (!zzaqx.remove(paramOnEventListener)) {
      zzgo().zzjg().zzbx("OnEventListener had not been registered");
    }
  }
  
  public final String zzaj(long paramLong)
  {
    if (zzgn().zzkb())
    {
      zzgo().zzjd().zzbx("Cannot retrieve app instance id from analytics worker thread");
      return null;
    }
    if (MultiMap.isMainThread())
    {
      zzgo().zzjd().zzbx("Cannot retrieve app instance id from main thread");
      return null;
    }
    paramLong = zzbx().elapsedRealtime();
    String str2 = zzak(120000L);
    paramLong = zzbx().elapsedRealtime() - paramLong;
    String str1 = str2;
    if (str2 == null)
    {
      str1 = str2;
      if (paramLong < 120000L) {
        str1 = zzak(120000L - paramLong);
      }
    }
    return str1;
  }
  
  final void zzcm(String paramString)
  {
    zzaqz.set(paramString);
  }
  
  public final String zzfx()
  {
    zzgb();
    return (String)zzaqz.get();
  }
  
  protected final boolean zzgt()
  {
    return false;
  }
  
  public final void zzks()
  {
    if ((getContext().getApplicationContext() instanceof Application)) {
      ((Application)getContext().getApplicationContext()).unregisterActivityLifecycleCallbacks(zzaqv);
    }
  }
  
  public final Boolean zzkt()
  {
    AtomicReference localAtomicReference = new AtomicReference();
    return (Boolean)zzgn().download(localAtomicReference, 15000L, "boolean test flag value", new zzct(this, localAtomicReference));
  }
  
  public final String zzku()
  {
    AtomicReference localAtomicReference = new AtomicReference();
    return (String)zzgn().download(localAtomicReference, 15000L, "String test flag value", new zzdd(this, localAtomicReference));
  }
  
  public final Long zzkv()
  {
    AtomicReference localAtomicReference = new AtomicReference();
    return (Long)zzgn().download(localAtomicReference, 15000L, "long test flag value", new zzdf(this, localAtomicReference));
  }
  
  public final Integer zzkw()
  {
    AtomicReference localAtomicReference = new AtomicReference();
    return (Integer)zzgn().download(localAtomicReference, 15000L, "int test flag value", new zzdg(this, localAtomicReference));
  }
  
  public final Double zzkx()
  {
    AtomicReference localAtomicReference = new AtomicReference();
    return (Double)zzgn().download(localAtomicReference, 15000L, "double test flag value", new zzdh(this, localAtomicReference));
  }
  
  public final void zzkz()
  {
    zzaf();
    zzgb();
    zzcl();
    if (!zzadj.zzkr()) {
      return;
    }
    zzgg().zzkz();
    zzara = false;
    String str = zzgp().zzjw();
    if (!TextUtils.isEmpty(str))
    {
      zzgk().zzcl();
      if (!str.equals(Build.VERSION.RELEASE))
      {
        Bundle localBundle = new Bundle();
        localBundle.putString("_po", str);
        logEvent("auto", "_ou", localBundle);
      }
    }
  }
}
