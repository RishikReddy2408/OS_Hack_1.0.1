package com.google.android.android.measurement.internal;

import android.content.Context;
import java.lang.reflect.Method;

public final class zzaj
  extends Log
{
  private String zzafx;
  private String zzage;
  private long zzagh;
  private String zzagk;
  private int zzagy;
  private int zzalo;
  private long zzalp;
  private String zztr;
  private String zzts;
  private String zztt;
  
  zzaj(zzbt paramZzbt)
  {
    super(paramZzbt);
  }
  
  private final String zziz()
  {
    for (;;)
    {
      try
      {
        localObject1 = getContext().getClassLoader().loadClass("com.google.firebase.analytics.FirebaseAnalytics");
        if (localObject1 == null) {
          return null;
        }
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        Object localObject1;
        Object localObject2;
        Context localContext;
        label86:
        return null;
      }
      try
      {
        localObject2 = ((Class)localObject1).getDeclaredMethod("getInstance", new Class[] { Context.class });
        localContext = getContext();
        localObject2 = ((Method)localObject2).invoke(null, new Object[] { localContext });
        if (localObject2 == null) {
          return null;
        }
      }
      catch (Exception localException1) {}
    }
    try
    {
      localObject1 = ((Class)localObject1).getDeclaredMethod("getFirebaseInstanceId", new Class[0]);
      localObject1 = ((Method)localObject1).invoke(localObject2, new Object[0]);
      return (String)localObject1;
    }
    catch (Exception localException2)
    {
      break label86;
    }
    zzgo().zzji().zzbx("Failed to retrieve Firebase Instance Id");
    return null;
    zzgo().zzjh().zzbx("Failed to obtain Firebase Analytics instance");
    return null;
  }
  
  final String getGmpAppId()
  {
    zzcl();
    return zzafx;
  }
  
  final String zzal()
  {
    zzcl();
    return zztt;
  }
  
  final ApplicationInfo zzbr(String paramString)
  {
    zzaf();
    zzgb();
    String str2 = zzal();
    String str3 = getGmpAppId();
    zzcl();
    String str4 = zzts;
    long l1 = zzja();
    zzcl();
    String str5 = zzage;
    long l2 = zzgq().zzhc();
    zzcl();
    zzaf();
    if (zzalp == 0L) {
      zzalp = zzadj.zzgm().create(getContext(), getContext().getPackageName());
    }
    long l3 = zzalp;
    boolean bool2 = zzadj.isEnabled();
    boolean bool3 = zzgpzzanv;
    zzaf();
    zzgb();
    String str1;
    if ((zzgq().zzbc(zztt)) && (!zzadj.isEnabled())) {
      str1 = null;
    } else {
      str1 = zziz();
    }
    zzcl();
    long l4 = zzagh;
    long l5 = zzadj.zzkp();
    int i = zzjb();
    Object localObject = zzgq();
    ((zzco)localObject).zzgb();
    localObject = ((class_3)localObject).zzau("google_analytics_adid_collection_enabled");
    boolean bool1;
    if ((localObject != null) && (!((Boolean)localObject).booleanValue())) {
      bool1 = false;
    } else {
      bool1 = true;
    }
    boolean bool4 = Boolean.valueOf(bool1).booleanValue();
    localObject = zzgq();
    ((zzco)localObject).zzgb();
    localObject = ((class_3)localObject).zzau("google_analytics_ssaid_collection_enabled");
    if ((localObject != null) && (!((Boolean)localObject).booleanValue())) {
      bool1 = false;
    } else {
      bool1 = true;
    }
    return new ApplicationInfo(str2, str3, str4, l1, str5, l2, l3, paramString, bool2, bool3 ^ true, str1, l4, l5, i, bool4, Boolean.valueOf(bool1).booleanValue(), zzgp().zzjx(), zzgw());
  }
  
  protected final boolean zzgt()
  {
    return true;
  }
  
  protected final void zzgu()
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
  }
  
  final String zzgw()
  {
    zzcl();
    return zzagk;
  }
  
  final int zzja()
  {
    zzcl();
    return zzalo;
  }
  
  final int zzjb()
  {
    zzcl();
    return zzagy;
  }
}
