package com.google.firebase.package_8;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.VisibleForTesting;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceIdReceiver;
import java.util.ArrayDeque;
import java.util.Queue;
import javax.annotation.concurrent.GuardedBy;

public final class zzav
{
  private static zzav zzcx;
  @GuardedBy("serviceClassNames")
  private final SimpleArrayMap<String, String> zzcy = new SimpleArrayMap();
  private Boolean zzcz = null;
  @VisibleForTesting
  final Queue<Intent> zzda = new ArrayDeque();
  @VisibleForTesting
  private final Queue<Intent> zzdb = new ArrayDeque();
  
  private zzav() {}
  
  public static void execute(Context paramContext, Intent paramIntent)
  {
    paramContext.sendBroadcast(intent(paramContext, "com.google.firebase.MESSAGING_EVENT", paramIntent));
  }
  
  /* Error */
  private final int init(Context paramContext, Intent paramIntent)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 28	com/google/firebase/package_8/zzav:zzcy	Landroid/support/v4/util/SimpleArrayMap;
    //   4: astore 5
    //   6: aload 5
    //   8: monitorenter
    //   9: aload_0
    //   10: getfield 28	com/google/firebase/package_8/zzav:zzcy	Landroid/support/v4/util/SimpleArrayMap;
    //   13: aload_2
    //   14: invokevirtual 66	android/content/Intent:getAction	()Ljava/lang/String;
    //   17: invokevirtual 70	android/support/v4/util/SimpleArrayMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   20: checkcast 72	java/lang/String
    //   23: astore 6
    //   25: aload 5
    //   27: monitorexit
    //   28: iconst_0
    //   29: istore 4
    //   31: aload 6
    //   33: astore 5
    //   35: aload 6
    //   37: ifnonnull +266 -> 303
    //   40: aload_1
    //   41: invokevirtual 76	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   44: aload_2
    //   45: iconst_0
    //   46: invokevirtual 82	android/content/pm/PackageManager:resolveService	(Landroid/content/Intent;I)Landroid/content/pm/ResolveInfo;
    //   49: astore 5
    //   51: aload 5
    //   53: ifnull +239 -> 292
    //   56: aload 5
    //   58: getfield 88	android/content/pm/ResolveInfo:serviceInfo	Landroid/content/pm/ServiceInfo;
    //   61: ifnonnull +6 -> 67
    //   64: goto +228 -> 292
    //   67: aload 5
    //   69: getfield 88	android/content/pm/ResolveInfo:serviceInfo	Landroid/content/pm/ServiceInfo;
    //   72: astore 6
    //   74: aload_1
    //   75: invokevirtual 91	android/content/Context:getPackageName	()Ljava/lang/String;
    //   78: aload 6
    //   80: getfield 97	android/content/pm/PackageItemInfo:packageName	Ljava/lang/String;
    //   83: invokevirtual 101	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   86: ifeq +117 -> 203
    //   89: aload 6
    //   91: getfield 104	android/content/pm/PackageItemInfo:name	Ljava/lang/String;
    //   94: ifnonnull +6 -> 100
    //   97: goto +106 -> 203
    //   100: aload 6
    //   102: getfield 104	android/content/pm/PackageItemInfo:name	Ljava/lang/String;
    //   105: astore 6
    //   107: aload 6
    //   109: astore 5
    //   111: aload 6
    //   113: ldc 106
    //   115: invokevirtual 110	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   118: ifeq +50 -> 168
    //   121: aload_1
    //   122: invokevirtual 91	android/content/Context:getPackageName	()Ljava/lang/String;
    //   125: invokestatic 114	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   128: astore 5
    //   130: aload 6
    //   132: invokestatic 114	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   135: astore 6
    //   137: aload 6
    //   139: invokevirtual 118	java/lang/String:length	()I
    //   142: ifeq +15 -> 157
    //   145: aload 5
    //   147: aload 6
    //   149: invokevirtual 122	java/lang/String:concat	(Ljava/lang/String;)Ljava/lang/String;
    //   152: astore 5
    //   154: goto +14 -> 168
    //   157: new 72	java/lang/String
    //   160: dup
    //   161: aload 5
    //   163: invokespecial 125	java/lang/String:<init>	(Ljava/lang/String;)V
    //   166: astore 5
    //   168: aload_0
    //   169: getfield 28	com/google/firebase/package_8/zzav:zzcy	Landroid/support/v4/util/SimpleArrayMap;
    //   172: astore 6
    //   174: aload 6
    //   176: monitorenter
    //   177: aload_0
    //   178: getfield 28	com/google/firebase/package_8/zzav:zzcy	Landroid/support/v4/util/SimpleArrayMap;
    //   181: aload_2
    //   182: invokevirtual 66	android/content/Intent:getAction	()Ljava/lang/String;
    //   185: aload 5
    //   187: invokevirtual 129	android/support/v4/util/SimpleArrayMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   190: pop
    //   191: aload 6
    //   193: monitorexit
    //   194: goto +109 -> 303
    //   197: astore_1
    //   198: aload 6
    //   200: monitorexit
    //   201: aload_1
    //   202: athrow
    //   203: aload 6
    //   205: getfield 97	android/content/pm/PackageItemInfo:packageName	Ljava/lang/String;
    //   208: astore 5
    //   210: aload 6
    //   212: getfield 104	android/content/pm/PackageItemInfo:name	Ljava/lang/String;
    //   215: astore 6
    //   217: new 131	java/lang/StringBuilder
    //   220: dup
    //   221: aload 5
    //   223: invokestatic 114	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   226: invokevirtual 118	java/lang/String:length	()I
    //   229: bipush 94
    //   231: iadd
    //   232: aload 6
    //   234: invokestatic 114	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   237: invokevirtual 118	java/lang/String:length	()I
    //   240: iadd
    //   241: invokespecial 134	java/lang/StringBuilder:<init>	(I)V
    //   244: astore 7
    //   246: aload 7
    //   248: ldc -120
    //   250: invokevirtual 140	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   253: pop
    //   254: aload 7
    //   256: aload 5
    //   258: invokevirtual 140	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   261: pop
    //   262: aload 7
    //   264: ldc -114
    //   266: invokevirtual 140	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   269: pop
    //   270: aload 7
    //   272: aload 6
    //   274: invokevirtual 140	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   277: pop
    //   278: ldc -112
    //   280: aload 7
    //   282: invokevirtual 147	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   285: invokestatic 153	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   288: pop
    //   289: goto +80 -> 369
    //   292: ldc -112
    //   294: ldc -101
    //   296: invokestatic 153	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   299: pop
    //   300: goto +69 -> 369
    //   303: ldc -112
    //   305: iconst_3
    //   306: invokestatic 159	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   309: ifeq +49 -> 358
    //   312: aload 5
    //   314: invokestatic 114	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   317: astore 6
    //   319: aload 6
    //   321: invokevirtual 118	java/lang/String:length	()I
    //   324: ifeq +15 -> 339
    //   327: ldc -95
    //   329: aload 6
    //   331: invokevirtual 122	java/lang/String:concat	(Ljava/lang/String;)Ljava/lang/String;
    //   334: astore 6
    //   336: goto +14 -> 350
    //   339: new 72	java/lang/String
    //   342: dup
    //   343: ldc -95
    //   345: invokespecial 125	java/lang/String:<init>	(Ljava/lang/String;)V
    //   348: astore 6
    //   350: ldc -112
    //   352: aload 6
    //   354: invokestatic 164	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   357: pop
    //   358: aload_2
    //   359: aload_1
    //   360: invokevirtual 91	android/content/Context:getPackageName	()Ljava/lang/String;
    //   363: aload 5
    //   365: invokevirtual 168	android/content/Intent:setClassName	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   368: pop
    //   369: aload_0
    //   370: getfield 30	com/google/firebase/package_8/zzav:zzcz	Ljava/lang/Boolean;
    //   373: ifnonnull +26 -> 399
    //   376: aload_1
    //   377: ldc -86
    //   379: invokevirtual 174	android/content/Context:checkCallingOrSelfPermission	(Ljava/lang/String;)I
    //   382: istore_3
    //   383: iload_3
    //   384: ifne +6 -> 390
    //   387: iconst_1
    //   388: istore 4
    //   390: aload_0
    //   391: iload 4
    //   393: invokestatic 179	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   396: putfield 30	com/google/firebase/package_8/zzav:zzcz	Ljava/lang/Boolean;
    //   399: aload_0
    //   400: getfield 30	com/google/firebase/package_8/zzav:zzcz	Ljava/lang/Boolean;
    //   403: astore 5
    //   405: aload 5
    //   407: invokevirtual 183	java/lang/Boolean:booleanValue	()Z
    //   410: istore 4
    //   412: iload 4
    //   414: ifeq +12 -> 426
    //   417: aload_1
    //   418: aload_2
    //   419: invokestatic 189	android/support/v4/content/WakefulBroadcastReceiver:startWakefulService	(Landroid/content/Context;Landroid/content/Intent;)Landroid/content/ComponentName;
    //   422: astore_1
    //   423: goto +17 -> 440
    //   426: aload_1
    //   427: aload_2
    //   428: invokevirtual 193	android/content/Context:startService	(Landroid/content/Intent;)Landroid/content/ComponentName;
    //   431: astore_1
    //   432: ldc -112
    //   434: ldc -61
    //   436: invokestatic 164	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   439: pop
    //   440: aload_1
    //   441: ifnonnull +15 -> 456
    //   444: ldc -112
    //   446: ldc -59
    //   448: invokestatic 153	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   451: pop
    //   452: sipush 404
    //   455: ireturn
    //   456: iconst_m1
    //   457: ireturn
    //   458: astore_1
    //   459: aload_1
    //   460: invokestatic 114	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   463: astore_1
    //   464: new 131	java/lang/StringBuilder
    //   467: dup
    //   468: aload_1
    //   469: invokestatic 114	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   472: invokevirtual 118	java/lang/String:length	()I
    //   475: bipush 45
    //   477: iadd
    //   478: invokespecial 134	java/lang/StringBuilder:<init>	(I)V
    //   481: astore_2
    //   482: aload_2
    //   483: ldc -57
    //   485: invokevirtual 140	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   488: pop
    //   489: aload_2
    //   490: aload_1
    //   491: invokevirtual 140	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   494: pop
    //   495: ldc -112
    //   497: aload_2
    //   498: invokevirtual 147	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   501: invokestatic 153	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   504: pop
    //   505: sipush 402
    //   508: ireturn
    //   509: astore_1
    //   510: ldc -112
    //   512: ldc -55
    //   514: aload_1
    //   515: invokestatic 204	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   518: pop
    //   519: sipush 401
    //   522: ireturn
    //   523: astore_1
    //   524: aload 5
    //   526: monitorexit
    //   527: aload_1
    //   528: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	529	0	this	zzav
    //   0	529	1	paramContext	Context
    //   0	529	2	paramIntent	Intent
    //   382	2	3	i	int
    //   29	384	4	bool	boolean
    //   4	521	5	localObject1	Object
    //   23	330	6	localObject2	Object
    //   244	37	7	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   177	194	197	java/lang/Throwable
    //   198	201	197	java/lang/Throwable
    //   376	383	458	java/lang/IllegalStateException
    //   405	412	458	java/lang/IllegalStateException
    //   417	423	458	java/lang/IllegalStateException
    //   426	440	458	java/lang/IllegalStateException
    //   444	452	458	java/lang/IllegalStateException
    //   376	383	509	java/lang/SecurityException
    //   405	412	509	java/lang/SecurityException
    //   417	423	509	java/lang/SecurityException
    //   426	440	509	java/lang/SecurityException
    //   444	452	509	java/lang/SecurityException
    //   9	28	523	java/lang/Throwable
    //   524	527	523	java/lang/Throwable
  }
  
  private static Intent intent(Context paramContext, String paramString, Intent paramIntent)
  {
    paramContext = new Intent(paramContext, FirebaseInstanceIdReceiver.class);
    paramContext.setAction(paramString);
    paramContext.putExtra("wrapped_intent", paramIntent);
    return paramContext;
  }
  
  public static void sendIntent(Context paramContext, Intent paramIntent)
  {
    paramContext.sendBroadcast(intent(paramContext, "com.google.firebase.INSTANCE_ID_EVENT", paramIntent));
  }
  
  public static PendingIntent setAlarm(Context paramContext, int paramInt1, Intent paramIntent, int paramInt2)
  {
    return PendingIntent.getBroadcast(paramContext, paramInt1, intent(paramContext, "com.google.firebase.MESSAGING_EVENT", paramIntent), 1073741824);
  }
  
  public static zzav zzai()
  {
    try
    {
      if (zzcx == null) {
        zzcx = new zzav();
      }
      zzav localZzav = zzcx;
      return localZzav;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public final int execute(Context paramContext, String paramString, Intent paramIntent)
  {
    if (Log.isLoggable("FirebaseInstanceId", 3))
    {
      String str = String.valueOf(paramString);
      if (str.length() != 0) {
        str = "Starting service: ".concat(str);
      } else {
        str = new String("Starting service: ");
      }
      Log.d("FirebaseInstanceId", str);
    }
    int i = -1;
    int j = paramString.hashCode();
    if (j != -842411455)
    {
      if ((j == 41532704) && (paramString.equals("com.google.firebase.MESSAGING_EVENT"))) {
        i = 1;
      }
    }
    else if (paramString.equals("com.google.firebase.INSTANCE_ID_EVENT")) {
      i = 0;
    }
    switch (i)
    {
    default: 
      paramContext = String.valueOf(paramString);
      if (paramContext.length() == 0) {
        break label211;
      }
      paramContext = "Unknown service action: ".concat(paramContext);
      break;
    case 1: 
      zzdb.offer(paramIntent);
      break;
    case 0: 
      zzda.offer(paramIntent);
    }
    paramString = new Intent(paramString);
    paramString.setPackage(paramContext.getPackageName());
    return init(paramContext, paramString);
    label211:
    paramContext = new String("Unknown service action: ");
    Log.w("FirebaseInstanceId", paramContext);
    return 500;
  }
  
  public final Intent zzaj()
  {
    return (Intent)zzdb.poll();
  }
}
