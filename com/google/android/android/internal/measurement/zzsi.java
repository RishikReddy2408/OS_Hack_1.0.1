package com.google.android.android.internal.measurement;

import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.GuardedBy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class zzsi
{
  private static final Object zzbqp = new Object();
  @GuardedBy("loadersLock")
  private static final Map<Uri, com.google.android.gms.internal.measurement.zzsi> zzbqq = new HashMap();
  private static final String[] zzbqw = { "key", "value" };
  private final Uri sourceName;
  private final ContentResolver zzbqr;
  private final Object zzbqs = new Object();
  private volatile Map<String, String> zzbqt;
  private final Object zzbqu = new Object();
  @GuardedBy("listenersLock")
  private final List<com.google.android.gms.internal.measurement.zzsk> zzbqv = new ArrayList();
  
  private zzsi(ContentResolver paramContentResolver, Uri paramUri)
  {
    zzbqr = paramContentResolver;
    sourceName = paramUri;
    zzbqr.registerContentObserver(paramUri, false, new zzsj(this, null));
  }
  
  public static zzsi addImage(ContentResolver paramContentResolver, Uri paramUri)
  {
    Object localObject = zzbqp;
    try
    {
      zzsi localZzsi2 = (zzsi)zzbqq.get(paramUri);
      zzsi localZzsi1 = localZzsi2;
      if (localZzsi2 == null)
      {
        localZzsi1 = new zzsi(paramContentResolver, paramUri);
        zzbqq.put(paramUri, localZzsi1);
      }
      return localZzsi1;
    }
    catch (Throwable paramContentResolver)
    {
      throw paramContentResolver;
    }
  }
  
  /* Error */
  private final Map zztb()
  {
    // Byte code:
    //   0: new 35	java/util/HashMap
    //   3: dup
    //   4: invokespecial 36	java/util/HashMap:<init>	()V
    //   7: astore_2
    //   8: aload_0
    //   9: getfield 58	com/google/android/android/internal/measurement/zzsi:zzbqr	Landroid/content/ContentResolver;
    //   12: astore_3
    //   13: aload_0
    //   14: getfield 60	com/google/android/android/internal/measurement/zzsi:sourceName	Landroid/net/Uri;
    //   17: astore 4
    //   19: getstatic 45	com/google/android/android/internal/measurement/zzsi:zzbqw	[Ljava/lang/String;
    //   22: astore 5
    //   24: aload_3
    //   25: aload 4
    //   27: aload 5
    //   29: aconst_null
    //   30: aconst_null
    //   31: aconst_null
    //   32: invokevirtual 102	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   35: astore_3
    //   36: aload_3
    //   37: ifnull +81 -> 118
    //   40: aload_3
    //   41: invokeinterface 108 1 0
    //   46: istore_1
    //   47: iload_1
    //   48: ifeq +27 -> 75
    //   51: aload_2
    //   52: aload_3
    //   53: iconst_0
    //   54: invokeinterface 112 2 0
    //   59: aload_3
    //   60: iconst_1
    //   61: invokeinterface 112 2 0
    //   66: invokeinterface 87 3 0
    //   71: pop
    //   72: goto -32 -> 40
    //   75: aload_3
    //   76: invokeinterface 115 1 0
    //   81: aload_2
    //   82: areturn
    //   83: astore_2
    //   84: aload_3
    //   85: invokeinterface 115 1 0
    //   90: aload_2
    //   91: athrow
    //   92: ldc 117
    //   94: ldc 119
    //   96: invokestatic 125	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   99: pop
    //   100: aconst_null
    //   101: areturn
    //   102: astore_2
    //   103: goto -11 -> 92
    //   106: astore_2
    //   107: goto -15 -> 92
    //   110: astore_2
    //   111: goto -19 -> 92
    //   114: astore_2
    //   115: goto -23 -> 92
    //   118: aload_2
    //   119: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	120	0	this	zzsi
    //   46	2	1	bool	boolean
    //   7	75	2	localHashMap	HashMap
    //   83	8	2	localThrowable	Throwable
    //   102	1	2	localSecurityException1	SecurityException
    //   106	1	2	localSQLiteException1	android.database.sqlite.SQLiteException
    //   110	1	2	localSecurityException2	SecurityException
    //   114	5	2	localSQLiteException2	android.database.sqlite.SQLiteException
    //   12	73	3	localObject	Object
    //   17	9	4	localUri	Uri
    //   22	6	5	arrayOfString	String[]
    // Exception table:
    //   from	to	target	type
    //   40	47	83	java/lang/Throwable
    //   51	72	83	java/lang/Throwable
    //   0	8	102	java/lang/SecurityException
    //   24	36	102	java/lang/SecurityException
    //   0	8	106	android/database/sqlite/SQLiteException
    //   24	36	106	android/database/sqlite/SQLiteException
    //   75	81	110	java/lang/SecurityException
    //   84	92	110	java/lang/SecurityException
    //   75	81	114	android/database/sqlite/SQLiteException
    //   84	92	114	android/database/sqlite/SQLiteException
  }
  
  private final void zztc()
  {
    Object localObject = zzbqu;
    try
    {
      Iterator localIterator = zzbqv.iterator();
      while (localIterator.hasNext()) {
        ((zzsk)localIterator.next()).zztd();
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public final Map zzsz()
  {
    Object localObject1;
    if (zzsl.invoke("gms:phenotype:phenotype_flag:debug_disable_caching", false)) {
      localObject1 = zztb();
    } else {
      localObject1 = zzbqt;
    }
    Object localObject2 = localObject1;
    if (localObject1 == null)
    {
      Object localObject3 = zzbqs;
      try
      {
        localObject2 = zzbqt;
        localObject1 = localObject2;
        if (localObject2 == null)
        {
          localObject2 = zztb();
          localObject1 = localObject2;
          zzbqt = ((Map)localObject2);
        }
        localObject2 = localObject1;
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
    if (localObject2 != null) {
      return localObject2;
    }
    return Collections.emptyMap();
  }
  
  public final void zzta()
  {
    Object localObject = zzbqs;
    try
    {
      zzbqt = null;
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
}
