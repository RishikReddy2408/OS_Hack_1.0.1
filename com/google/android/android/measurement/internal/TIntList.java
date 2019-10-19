package com.google.android.android.measurement.internal;

import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.internal.measurement.zzfv;
import com.google.android.android.internal.measurement.zzfw;
import com.google.android.android.internal.measurement.zzfx;
import com.google.android.android.internal.measurement.zzfy;
import com.google.android.android.internal.measurement.zzfz;
import com.google.android.android.internal.measurement.zzgd;
import com.google.android.android.internal.measurement.zzge;
import com.google.android.android.internal.measurement.zzgf;
import com.google.android.android.internal.measurement.zzgg;
import com.google.android.android.internal.measurement.zzgl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

final class TIntList
  extends zzez
{
  TIntList(zzfa paramZzfa)
  {
    super(paramZzfa);
  }
  
  private final Boolean add(double paramDouble, zzfx paramZzfx)
  {
    try
    {
      paramZzfx = add(new BigDecimal(paramDouble), paramZzfx, Math.ulp(paramDouble));
      return paramZzfx;
    }
    catch (NumberFormatException paramZzfx)
    {
      for (;;) {}
    }
    return null;
  }
  
  private final Boolean add(long paramLong, zzfx paramZzfx)
  {
    try
    {
      paramZzfx = add(new BigDecimal(paramLong), paramZzfx, 0.0D);
      return paramZzfx;
    }
    catch (NumberFormatException paramZzfx)
    {
      for (;;) {}
    }
    return null;
  }
  
  private final Boolean add(String paramString, zzfx paramZzfx)
  {
    if (!zzfg.zzcp(paramString)) {
      return null;
    }
    try
    {
      paramString = add(new BigDecimal(paramString), paramZzfx, 0.0D);
      return paramString;
    }
    catch (NumberFormatException paramString) {}
    return null;
  }
  
  /* Error */
  private static Boolean add(BigDecimal paramBigDecimal, zzfx paramZzfx, double paramDouble)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 47	com/google/android/android/common/internal/Preconditions:checkNotNull	(Ljava/lang/Object;)Ljava/lang/Object;
    //   4: pop
    //   5: aload_1
    //   6: getfield 53	com/google/android/android/internal/measurement/zzfx:zzavo	Ljava/lang/Integer;
    //   9: ifnull +435 -> 444
    //   12: aload_1
    //   13: getfield 53	com/google/android/android/internal/measurement/zzfx:zzavo	Ljava/lang/Integer;
    //   16: invokevirtual 59	java/lang/Integer:intValue	()I
    //   19: ifne +5 -> 24
    //   22: aconst_null
    //   23: areturn
    //   24: aload_1
    //   25: getfield 53	com/google/android/android/internal/measurement/zzfx:zzavo	Ljava/lang/Integer;
    //   28: invokevirtual 59	java/lang/Integer:intValue	()I
    //   31: iconst_4
    //   32: if_icmpne +19 -> 51
    //   35: aload_1
    //   36: getfield 63	com/google/android/android/internal/measurement/zzfx:zzavr	Ljava/lang/String;
    //   39: ifnull +411 -> 450
    //   42: aload_1
    //   43: getfield 66	com/google/android/android/internal/measurement/zzfx:zzavs	Ljava/lang/String;
    //   46: ifnonnull +14 -> 60
    //   49: aconst_null
    //   50: areturn
    //   51: aload_1
    //   52: getfield 69	com/google/android/android/internal/measurement/zzfx:zzavq	Ljava/lang/String;
    //   55: ifnonnull +5 -> 60
    //   58: aconst_null
    //   59: areturn
    //   60: aload_1
    //   61: getfield 53	com/google/android/android/internal/measurement/zzfx:zzavo	Ljava/lang/Integer;
    //   64: invokevirtual 59	java/lang/Integer:intValue	()I
    //   67: istore 4
    //   69: aload_1
    //   70: getfield 53	com/google/android/android/internal/measurement/zzfx:zzavo	Ljava/lang/Integer;
    //   73: invokevirtual 59	java/lang/Integer:intValue	()I
    //   76: iconst_4
    //   77: if_icmpne +68 -> 145
    //   80: aload_1
    //   81: getfield 63	com/google/android/android/internal/measurement/zzfx:zzavr	Ljava/lang/String;
    //   84: invokestatic 38	com/google/android/android/measurement/internal/zzfg:zzcp	(Ljava/lang/String;)Z
    //   87: ifeq +56 -> 143
    //   90: aload_1
    //   91: getfield 66	com/google/android/android/internal/measurement/zzfx:zzavs	Ljava/lang/String;
    //   94: invokestatic 38	com/google/android/android/measurement/internal/zzfg:zzcp	(Ljava/lang/String;)Z
    //   97: ifne +5 -> 102
    //   100: aconst_null
    //   101: areturn
    //   102: aload_1
    //   103: getfield 63	com/google/android/android/internal/measurement/zzfx:zzavr	Ljava/lang/String;
    //   106: astore 10
    //   108: new 15	java/math/BigDecimal
    //   111: dup
    //   112: aload 10
    //   114: invokespecial 41	java/math/BigDecimal:<init>	(Ljava/lang/String;)V
    //   117: astore 10
    //   119: aload_1
    //   120: getfield 66	com/google/android/android/internal/measurement/zzfx:zzavs	Ljava/lang/String;
    //   123: astore_1
    //   124: new 15	java/math/BigDecimal
    //   127: dup
    //   128: aload_1
    //   129: invokespecial 41	java/math/BigDecimal:<init>	(Ljava/lang/String;)V
    //   132: astore 11
    //   134: aload 10
    //   136: astore_1
    //   137: aconst_null
    //   138: astore 10
    //   140: goto +37 -> 177
    //   143: aconst_null
    //   144: areturn
    //   145: aload_1
    //   146: getfield 69	com/google/android/android/internal/measurement/zzfx:zzavq	Ljava/lang/String;
    //   149: invokestatic 38	com/google/android/android/measurement/internal/zzfg:zzcp	(Ljava/lang/String;)Z
    //   152: ifne +5 -> 157
    //   155: aconst_null
    //   156: areturn
    //   157: aload_1
    //   158: getfield 69	com/google/android/android/internal/measurement/zzfx:zzavq	Ljava/lang/String;
    //   161: astore_1
    //   162: new 15	java/math/BigDecimal
    //   165: dup
    //   166: aload_1
    //   167: invokespecial 41	java/math/BigDecimal:<init>	(Ljava/lang/String;)V
    //   170: astore 10
    //   172: aconst_null
    //   173: astore_1
    //   174: aconst_null
    //   175: astore 11
    //   177: iload 4
    //   179: iconst_4
    //   180: if_icmpne +9 -> 189
    //   183: aload_1
    //   184: ifnonnull +10 -> 194
    //   187: aconst_null
    //   188: areturn
    //   189: aload 10
    //   191: ifnull +253 -> 444
    //   194: iconst_0
    //   195: istore 7
    //   197: iconst_0
    //   198: istore 5
    //   200: iconst_0
    //   201: istore 8
    //   203: iconst_0
    //   204: istore 9
    //   206: iconst_0
    //   207: istore 6
    //   209: iload 4
    //   211: lookupswitch	default:+41->252, 1:+210->421, 2:+187->398, 3:+82->293, 4:+46->257
    //   252: goto +3 -> 255
    //   255: aconst_null
    //   256: areturn
    //   257: iload 6
    //   259: istore 5
    //   261: aload_0
    //   262: aload_1
    //   263: invokevirtual 73	java/math/BigDecimal:compareTo	(Ljava/math/BigDecimal;)I
    //   266: iconst_m1
    //   267: if_icmpeq +20 -> 287
    //   270: iload 6
    //   272: istore 5
    //   274: aload_0
    //   275: aload 11
    //   277: invokevirtual 73	java/math/BigDecimal:compareTo	(Ljava/math/BigDecimal;)I
    //   280: iconst_1
    //   281: if_icmpeq +6 -> 287
    //   284: iconst_1
    //   285: istore 5
    //   287: iload 5
    //   289: invokestatic 79	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   292: areturn
    //   293: dload_2
    //   294: dconst_0
    //   295: dcmpl
    //   296: ifeq +84 -> 380
    //   299: iload 7
    //   301: istore 5
    //   303: aload_0
    //   304: aload 10
    //   306: new 15	java/math/BigDecimal
    //   309: dup
    //   310: dload_2
    //   311: invokespecial 18	java/math/BigDecimal:<init>	(D)V
    //   314: new 15	java/math/BigDecimal
    //   317: dup
    //   318: iconst_2
    //   319: invokespecial 82	java/math/BigDecimal:<init>	(I)V
    //   322: invokevirtual 86	java/math/BigDecimal:multiply	(Ljava/math/BigDecimal;)Ljava/math/BigDecimal;
    //   325: invokevirtual 89	java/math/BigDecimal:subtract	(Ljava/math/BigDecimal;)Ljava/math/BigDecimal;
    //   328: invokevirtual 73	java/math/BigDecimal:compareTo	(Ljava/math/BigDecimal;)I
    //   331: iconst_1
    //   332: if_icmpne +42 -> 374
    //   335: iload 7
    //   337: istore 5
    //   339: aload_0
    //   340: aload 10
    //   342: new 15	java/math/BigDecimal
    //   345: dup
    //   346: dload_2
    //   347: invokespecial 18	java/math/BigDecimal:<init>	(D)V
    //   350: new 15	java/math/BigDecimal
    //   353: dup
    //   354: iconst_2
    //   355: invokespecial 82	java/math/BigDecimal:<init>	(I)V
    //   358: invokevirtual 86	java/math/BigDecimal:multiply	(Ljava/math/BigDecimal;)Ljava/math/BigDecimal;
    //   361: invokevirtual 91	java/math/BigDecimal:add	(Ljava/math/BigDecimal;)Ljava/math/BigDecimal;
    //   364: invokevirtual 73	java/math/BigDecimal:compareTo	(Ljava/math/BigDecimal;)I
    //   367: iconst_m1
    //   368: if_icmpne +6 -> 374
    //   371: iconst_1
    //   372: istore 5
    //   374: iload 5
    //   376: invokestatic 79	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   379: areturn
    //   380: aload_0
    //   381: aload 10
    //   383: invokevirtual 73	java/math/BigDecimal:compareTo	(Ljava/math/BigDecimal;)I
    //   386: ifne +6 -> 392
    //   389: iconst_1
    //   390: istore 5
    //   392: iload 5
    //   394: invokestatic 79	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   397: areturn
    //   398: iload 8
    //   400: istore 5
    //   402: aload_0
    //   403: aload 10
    //   405: invokevirtual 73	java/math/BigDecimal:compareTo	(Ljava/math/BigDecimal;)I
    //   408: iconst_1
    //   409: if_icmpne +6 -> 415
    //   412: iconst_1
    //   413: istore 5
    //   415: iload 5
    //   417: invokestatic 79	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   420: areturn
    //   421: iload 9
    //   423: istore 5
    //   425: aload_0
    //   426: aload 10
    //   428: invokevirtual 73	java/math/BigDecimal:compareTo	(Ljava/math/BigDecimal;)I
    //   431: iconst_m1
    //   432: if_icmpne +6 -> 438
    //   435: iconst_1
    //   436: istore 5
    //   438: iload 5
    //   440: invokestatic 79	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   443: areturn
    //   444: aconst_null
    //   445: areturn
    //   446: astore_0
    //   447: aconst_null
    //   448: areturn
    //   449: astore_0
    //   450: aconst_null
    //   451: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	452	0	paramBigDecimal	BigDecimal
    //   0	452	1	paramZzfx	zzfx
    //   0	452	2	paramDouble	double
    //   67	143	4	i	int
    //   198	241	5	bool1	boolean
    //   207	64	6	bool2	boolean
    //   195	141	7	bool3	boolean
    //   201	198	8	bool4	boolean
    //   204	218	9	bool5	boolean
    //   106	321	10	localObject	Object
    //   132	144	11	localBigDecimal	BigDecimal
    // Exception table:
    //   from	to	target	type
    //   108	119	446	java/lang/NumberFormatException
    //   124	134	446	java/lang/NumberFormatException
    //   162	172	449	java/lang/NumberFormatException
  }
  
  private static void add(Map paramMap, int paramInt, long paramLong)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: fail exe a5 = a4\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:92)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.dfs(Cfg.java:255)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze0(BaseAnalyze.java:75)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze(BaseAnalyze.java:69)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer.transform(UnSSATransformer.java:274)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:163)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\nCaused by: java.lang.NullPointerException\n");
  }
  
  private final Boolean create(String paramString1, int paramInt, boolean paramBoolean, String paramString2, List paramList, String paramString3)
  {
    if (paramString1 == null) {
      return null;
    }
    if (paramInt == 6)
    {
      if (paramList == null) {
        break label238;
      }
      if (paramList.size() == 0) {
        return null;
      }
    }
    else if (paramString2 == null)
    {
      return null;
    }
    String str = paramString1;
    if (!paramBoolean) {
      if (paramInt == 1) {
        str = paramString1;
      } else {
        str = paramString1.toUpperCase(Locale.ENGLISH);
      }
    }
    switch (paramInt)
    {
    default: 
      return null;
    case 6: 
      return Boolean.valueOf(paramList.contains(str));
    case 5: 
      return Boolean.valueOf(str.equals(paramString2));
    case 4: 
      return Boolean.valueOf(str.contains(paramString2));
    case 3: 
      return Boolean.valueOf(str.endsWith(paramString2));
    case 2: 
      return Boolean.valueOf(str.startsWith(paramString2));
    }
    if (paramBoolean) {
      paramInt = 0;
    } else {
      paramInt = 66;
    }
    try
    {
      paramBoolean = Pattern.compile(paramString3, paramInt).matcher(str).matches();
      return Boolean.valueOf(paramBoolean);
    }
    catch (PatternSyntaxException paramString1)
    {
      for (;;) {}
    }
    zzgo().zzjg().append("Invalid regular expression in REGEXP audience filter. expression", paramString3);
    return null;
    label238:
    return null;
  }
  
  private final Boolean create(String paramString, zzfz paramZzfz)
  {
    Preconditions.checkNotNull(paramZzfz);
    if (paramString == null) {
      return null;
    }
    if (zzavw != null)
    {
      if (zzavw.intValue() == 0) {
        return null;
      }
      if (zzavw.intValue() == 6)
      {
        if (zzavz == null) {
          break label260;
        }
        if (zzavz.length == 0) {
          return null;
        }
      }
      else if (zzavx == null)
      {
        return null;
      }
      int j = zzavw.intValue();
      Object localObject1 = zzavy;
      int i = 0;
      boolean bool;
      if ((localObject1 != null) && (zzavy.booleanValue())) {
        bool = true;
      } else {
        bool = false;
      }
      if ((!bool) && (j != 1) && (j != 6)) {
        localObject1 = zzavx.toUpperCase(Locale.ENGLISH);
      } else {
        localObject1 = zzavx;
      }
      Object localObject2;
      if (zzavz == null)
      {
        paramZzfz = null;
      }
      else
      {
        localObject2 = zzavz;
        if (bool)
        {
          paramZzfz = Arrays.asList((Object[])localObject2);
        }
        else
        {
          paramZzfz = new ArrayList();
          int k = localObject2.length;
          while (i < k)
          {
            paramZzfz.add(localObject2[i].toUpperCase(Locale.ENGLISH));
            i += 1;
          }
        }
      }
      if (j == 1) {
        localObject2 = localObject1;
      } else {
        localObject2 = null;
      }
      return create(paramString, j, bool, (String)localObject1, (List)paramZzfz, (String)localObject2);
    }
    label260:
    return null;
  }
  
  private final Boolean get(zzfv paramZzfv, String paramString, zzgg[] paramArrayOfZzgg, long paramLong)
  {
    if (zzavi != null)
    {
      localObject1 = add(paramLong, zzavi);
      if (localObject1 == null) {
        return null;
      }
      if (!((Boolean)localObject1).booleanValue()) {
        return Boolean.valueOf(false);
      }
    }
    Object localObject2 = new HashSet();
    Object localObject1 = zzavg;
    int j = localObject1.length;
    int i = 0;
    Object localObject3;
    while (i < j)
    {
      localObject3 = localObject1[i];
      if (TextUtils.isEmpty(zzavn))
      {
        zzgo().zzjg().append("null or empty param name in filter. event", zzgl().zzbs(paramString));
        return null;
      }
      ((Set)localObject2).add(zzavn);
      i += 1;
    }
    localObject1 = new ArrayMap();
    j = paramArrayOfZzgg.length;
    i = 0;
    while (i < j)
    {
      localObject3 = paramArrayOfZzgg[i];
      if (((Set)localObject2).contains(name)) {
        if (zzawx != null)
        {
          ((Map)localObject1).put(name, zzawx);
        }
        else if (zzauh != null)
        {
          ((Map)localObject1).put(name, zzauh);
        }
        else if (zzamp != null)
        {
          ((Map)localObject1).put(name, zzamp);
        }
        else
        {
          zzgo().zzjg().append("Unknown value for param. event, param", zzgl().zzbs(paramString), zzgl().zzbt(name));
          return null;
        }
      }
      i += 1;
    }
    paramArrayOfZzgg = zzavg;
    j = paramArrayOfZzgg.length;
    i = 0;
    while (i < j)
    {
      paramZzfv = paramArrayOfZzgg[i];
      boolean bool = Boolean.TRUE.equals(zzavm);
      localObject2 = zzavn;
      if (TextUtils.isEmpty((CharSequence)localObject2))
      {
        zzgo().zzjg().append("Event has empty param name. event", zzgl().zzbs(paramString));
        return null;
      }
      localObject3 = ((Map)localObject1).get(localObject2);
      if ((localObject3 instanceof Long))
      {
        if (zzavl == null)
        {
          zzgo().zzjg().append("No number filter for long param. event, param", zzgl().zzbs(paramString), zzgl().zzbt((String)localObject2));
          return null;
        }
        paramZzfv = add(((Long)localObject3).longValue(), zzavl);
        if (paramZzfv == null) {
          return null;
        }
        if ((true ^ paramZzfv.booleanValue() ^ bool)) {
          return Boolean.valueOf(false);
        }
      }
      else if ((localObject3 instanceof Double))
      {
        if (zzavl == null)
        {
          zzgo().zzjg().append("No number filter for double param. event, param", zzgl().zzbs(paramString), zzgl().zzbt((String)localObject2));
          return null;
        }
        paramZzfv = add(((Double)localObject3).doubleValue(), zzavl);
        if (paramZzfv == null) {
          return null;
        }
        if ((true ^ paramZzfv.booleanValue() ^ bool)) {
          return Boolean.valueOf(false);
        }
      }
      else
      {
        if (!(localObject3 instanceof String)) {
          break label725;
        }
        if (zzavk != null)
        {
          paramZzfv = create((String)localObject3, zzavk);
        }
        else
        {
          if (zzavl == null) {
            break label693;
          }
          localObject3 = (String)localObject3;
          if (!zzfg.zzcp((String)localObject3)) {
            break label661;
          }
          paramZzfv = add((String)localObject3, zzavl);
        }
        if (paramZzfv == null) {
          return null;
        }
        if ((true ^ paramZzfv.booleanValue() ^ bool)) {
          return Boolean.valueOf(false);
        }
      }
      i += 1;
      continue;
      label661:
      zzgo().zzjg().append("Invalid param value for number filter. event, param", zzgl().zzbs(paramString), zzgl().zzbt((String)localObject2));
      return null;
      label693:
      zzgo().zzjg().append("No filter for String param. event, param", zzgl().zzbs(paramString), zzgl().zzbt((String)localObject2));
      return null;
      label725:
      if (localObject3 == null)
      {
        zzgo().zzjl().append("Missing param for filter. event, param", zzgl().zzbs(paramString), zzgl().zzbt((String)localObject2));
        return Boolean.valueOf(false);
      }
      zzgo().zzjg().append("Unknown param type. event, param", zzgl().zzbs(paramString), zzgl().zzbt((String)localObject2));
      return null;
    }
    return Boolean.valueOf(true);
  }
  
  private final Boolean get(zzfy paramZzfy, zzgl paramZzgl)
  {
    paramZzfy = zzavv;
    if (paramZzfy == null)
    {
      zzgo().zzjg().append("Missing property filter. property", zzgl().zzbu(name));
      return null;
    }
    boolean bool = Boolean.TRUE.equals(zzavm);
    if (zzawx != null)
    {
      if (zzavl == null)
      {
        zzgo().zzjg().append("No number filter for long property. property", zzgl().zzbu(name));
        return null;
      }
      return set(add(zzawx.longValue(), zzavl), bool);
    }
    if (zzauh != null)
    {
      if (zzavl == null)
      {
        zzgo().zzjg().append("No number filter for double property. property", zzgl().zzbu(name));
        return null;
      }
      return set(add(zzauh.doubleValue(), zzavl), bool);
    }
    if (zzamp != null)
    {
      if (zzavk == null)
      {
        if (zzavl == null)
        {
          zzgo().zzjg().append("No string or number filter defined. property", zzgl().zzbu(name));
          return null;
        }
        if (zzfg.zzcp(zzamp)) {
          return set(add(zzamp, zzavl), bool);
        }
        zzgo().zzjg().append("Invalid user property value for Numeric number filter. property, value", zzgl().zzbu(name), zzamp);
        return null;
      }
      return set(create(zzamp, zzavk), bool);
    }
    zzgo().zzjg().append("User property has no value, property", zzgl().zzbu(name));
    return null;
  }
  
  private static zzge[] replace(Map paramMap)
  {
    if (paramMap == null) {
      return null;
    }
    int i = 0;
    zzge[] arrayOfZzge = new zzge[paramMap.size()];
    Iterator localIterator = paramMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      Integer localInteger = (Integer)localIterator.next();
      zzge localZzge = new zzge();
      zzawq = localInteger;
      zzawr = ((Long)paramMap.get(localInteger));
      arrayOfZzge[i] = localZzge;
      i += 1;
    }
    return arrayOfZzge;
  }
  
  private static Boolean set(Boolean paramBoolean, boolean paramBoolean1)
  {
    if (paramBoolean == null) {
      return null;
    }
    return Boolean.valueOf(paramBoolean.booleanValue() ^ paramBoolean1);
  }
  
  private static void set(Map paramMap, int paramInt, long paramLong)
  {
    Long localLong = (Long)paramMap.get(Integer.valueOf(paramInt));
    paramLong /= 1000L;
    if ((localLong == null) || (paramLong > localLong.longValue())) {
      paramMap.put(Integer.valueOf(paramInt), Long.valueOf(paramLong));
    }
  }
  
  final zzgd[] replace(String paramString, zzgf[] paramArrayOfZzgf, zzgl[] paramArrayOfZzgl)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: fail exe a5 = a4\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:92)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.dfs(Cfg.java:255)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze0(BaseAnalyze.java:75)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze(BaseAnalyze.java:69)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer.transform(UnSSATransformer.java:274)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:163)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\nCaused by: java.lang.NullPointerException\n");
  }
  
  protected final boolean zzgt()
  {
    return false;
  }
}
