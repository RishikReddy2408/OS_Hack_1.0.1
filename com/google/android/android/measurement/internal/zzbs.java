package com.google.android.android.measurement.internal;

import com.google.android.android.common.internal.Preconditions;
import com.google.android.gms.measurement.internal.zzbr;
import java.util.concurrent.BlockingQueue;

final class zzbs
  extends Thread
{
  private final Object zzapj;
  private final BlockingQueue<zzbr<?>> zzapk;
  
  public zzbs(zzbo paramZzbo, String paramString, BlockingQueue paramBlockingQueue)
  {
    Preconditions.checkNotNull(paramString);
    Preconditions.checkNotNull(paramBlockingQueue);
    zzapj = new Object();
    zzapk = paramBlockingQueue;
    setName(paramString);
  }
  
  private final void loadFile(InterruptedException paramInterruptedException)
  {
    zzapg.zzgo().zzjg().append(String.valueOf(getName()).concat(" was interrupted"), paramInterruptedException);
  }
  
  /* Error */
  public final void run()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: iload_1
    //   3: ifne +33 -> 36
    //   6: aload_0
    //   7: getfield 15	com/google/android/android/measurement/internal/zzbs:zzapg	Lcom/google/android/android/measurement/internal/zzbo;
    //   10: astore 4
    //   12: aload 4
    //   14: invokestatic 83	com/google/android/android/measurement/internal/zzbo:access$getMIsAvailable	(Lcom/google/android/android/measurement/internal/zzbo;)Ljava/util/concurrent/Semaphore;
    //   17: invokevirtual 88	java/util/concurrent/Semaphore:acquire	()V
    //   20: iconst_1
    //   21: istore_1
    //   22: goto -20 -> 2
    //   25: astore 4
    //   27: aload_0
    //   28: aload 4
    //   30: invokespecial 90	com/google/android/android/measurement/internal/zzbs:loadFile	(Ljava/lang/InterruptedException;)V
    //   33: goto -31 -> 2
    //   36: invokestatic 96	android/os/Process:myTid	()I
    //   39: invokestatic 100	android/os/Process:getThreadPriority	(I)I
    //   42: istore_2
    //   43: aload_0
    //   44: getfield 31	com/google/android/android/measurement/internal/zzbs:zzapk	Ljava/util/concurrent/BlockingQueue;
    //   47: invokeinterface 106 1 0
    //   52: checkcast 108	com/google/android/android/measurement/internal/zzbr
    //   55: astore 4
    //   57: aload 4
    //   59: ifnull +33 -> 92
    //   62: aload 4
    //   64: getfield 112	com/google/android/android/measurement/internal/zzbr:zzapi	Z
    //   67: istore_3
    //   68: iload_3
    //   69: ifeq +8 -> 77
    //   72: iload_2
    //   73: istore_1
    //   74: goto +6 -> 80
    //   77: bipush 10
    //   79: istore_1
    //   80: iload_1
    //   81: invokestatic 116	android/os/Process:setThreadPriority	(I)V
    //   84: aload 4
    //   86: invokevirtual 120	java/util/concurrent/FutureTask:run	()V
    //   89: goto -46 -> 43
    //   92: aload_0
    //   93: getfield 29	com/google/android/android/measurement/internal/zzbs:zzapj	Ljava/lang/Object;
    //   96: astore 4
    //   98: aload 4
    //   100: monitorenter
    //   101: aload_0
    //   102: getfield 31	com/google/android/android/measurement/internal/zzbs:zzapk	Ljava/util/concurrent/BlockingQueue;
    //   105: invokeinterface 123 1 0
    //   110: ifnonnull +40 -> 150
    //   113: aload_0
    //   114: getfield 15	com/google/android/android/measurement/internal/zzbs:zzapg	Lcom/google/android/android/measurement/internal/zzbo;
    //   117: invokestatic 127	com/google/android/android/measurement/internal/zzbo:isImportant	(Lcom/google/android/android/measurement/internal/zzbo;)Z
    //   120: istore_3
    //   121: iload_3
    //   122: ifne +28 -> 150
    //   125: aload_0
    //   126: getfield 29	com/google/android/android/measurement/internal/zzbs:zzapj	Ljava/lang/Object;
    //   129: astore 5
    //   131: aload 5
    //   133: ldc2_w 128
    //   136: invokevirtual 133	java/lang/Object:wait	(J)V
    //   139: goto +11 -> 150
    //   142: astore 5
    //   144: aload_0
    //   145: aload 5
    //   147: invokespecial 90	com/google/android/android/measurement/internal/zzbs:loadFile	(Ljava/lang/InterruptedException;)V
    //   150: aload 4
    //   152: monitorexit
    //   153: aload_0
    //   154: getfield 15	com/google/android/android/measurement/internal/zzbs:zzapg	Lcom/google/android/android/measurement/internal/zzbo;
    //   157: invokestatic 137	com/google/android/android/measurement/internal/zzbo:access$getMDecoderLock	(Lcom/google/android/android/measurement/internal/zzbo;)Ljava/lang/Object;
    //   160: astore 4
    //   162: aload 4
    //   164: monitorenter
    //   165: aload_0
    //   166: getfield 31	com/google/android/android/measurement/internal/zzbs:zzapk	Ljava/util/concurrent/BlockingQueue;
    //   169: invokeinterface 123 1 0
    //   174: ifnonnull +111 -> 285
    //   177: aload 4
    //   179: monitorexit
    //   180: aload_0
    //   181: getfield 15	com/google/android/android/measurement/internal/zzbs:zzapg	Lcom/google/android/android/measurement/internal/zzbo;
    //   184: invokestatic 137	com/google/android/android/measurement/internal/zzbo:access$getMDecoderLock	(Lcom/google/android/android/measurement/internal/zzbo;)Ljava/lang/Object;
    //   187: astore 4
    //   189: aload 4
    //   191: monitorenter
    //   192: aload_0
    //   193: getfield 15	com/google/android/android/measurement/internal/zzbs:zzapg	Lcom/google/android/android/measurement/internal/zzbo;
    //   196: invokestatic 83	com/google/android/android/measurement/internal/zzbo:access$getMIsAvailable	(Lcom/google/android/android/measurement/internal/zzbo;)Ljava/util/concurrent/Semaphore;
    //   199: invokevirtual 140	java/util/concurrent/Semaphore:release	()V
    //   202: aload_0
    //   203: getfield 15	com/google/android/android/measurement/internal/zzbs:zzapg	Lcom/google/android/android/measurement/internal/zzbo;
    //   206: invokestatic 137	com/google/android/android/measurement/internal/zzbo:access$getMDecoderLock	(Lcom/google/android/android/measurement/internal/zzbo;)Ljava/lang/Object;
    //   209: invokevirtual 143	java/lang/Object:notifyAll	()V
    //   212: aload_0
    //   213: aload_0
    //   214: getfield 15	com/google/android/android/measurement/internal/zzbs:zzapg	Lcom/google/android/android/measurement/internal/zzbo;
    //   217: invokestatic 147	com/google/android/android/measurement/internal/zzbo:getBasePath	(Lcom/google/android/android/measurement/internal/zzbo;)Lcom/google/android/android/measurement/internal/zzbs;
    //   220: if_acmpne +15 -> 235
    //   223: aload_0
    //   224: getfield 15	com/google/android/android/measurement/internal/zzbs:zzapg	Lcom/google/android/android/measurement/internal/zzbo;
    //   227: aconst_null
    //   228: invokestatic 151	com/google/android/android/measurement/internal/zzbo:getWordRangeAtCursor	(Lcom/google/android/android/measurement/internal/zzbo;Lcom/google/android/android/measurement/internal/zzbs;)Lcom/google/android/android/measurement/internal/zzbs;
    //   231: pop
    //   232: goto +41 -> 273
    //   235: aload_0
    //   236: aload_0
    //   237: getfield 15	com/google/android/android/measurement/internal/zzbs:zzapg	Lcom/google/android/android/measurement/internal/zzbo;
    //   240: invokestatic 154	com/google/android/android/measurement/internal/zzbo:Zip	(Lcom/google/android/android/measurement/internal/zzbo;)Lcom/google/android/android/measurement/internal/zzbs;
    //   243: if_acmpne +15 -> 258
    //   246: aload_0
    //   247: getfield 15	com/google/android/android/measurement/internal/zzbs:zzapg	Lcom/google/android/android/measurement/internal/zzbo;
    //   250: aconst_null
    //   251: invokestatic 157	com/google/android/android/measurement/internal/zzbo:makeView	(Lcom/google/android/android/measurement/internal/zzbo;Lcom/google/android/android/measurement/internal/zzbs;)Lcom/google/android/android/measurement/internal/zzbs;
    //   254: pop
    //   255: goto +18 -> 273
    //   258: aload_0
    //   259: getfield 15	com/google/android/android/measurement/internal/zzbs:zzapg	Lcom/google/android/android/measurement/internal/zzbo;
    //   262: invokevirtual 44	com/google/android/android/measurement/internal/zzco:zzgo	()Lcom/google/android/android/measurement/internal/zzap;
    //   265: invokevirtual 160	com/google/android/android/measurement/internal/zzap:zzjd	()Lcom/google/android/android/measurement/internal/zzar;
    //   268: ldc -94
    //   270: invokevirtual 165	com/google/android/android/measurement/internal/zzar:zzbx	(Ljava/lang/String;)V
    //   273: aload 4
    //   275: monitorexit
    //   276: return
    //   277: astore 5
    //   279: aload 4
    //   281: monitorexit
    //   282: aload 5
    //   284: athrow
    //   285: aload 4
    //   287: monitorexit
    //   288: goto -245 -> 43
    //   291: astore 5
    //   293: aload 4
    //   295: monitorexit
    //   296: aload 5
    //   298: athrow
    //   299: astore 5
    //   301: aload 4
    //   303: monitorexit
    //   304: aload 5
    //   306: athrow
    //   307: astore 5
    //   309: aload_0
    //   310: getfield 15	com/google/android/android/measurement/internal/zzbs:zzapg	Lcom/google/android/android/measurement/internal/zzbo;
    //   313: invokestatic 137	com/google/android/android/measurement/internal/zzbo:access$getMDecoderLock	(Lcom/google/android/android/measurement/internal/zzbo;)Ljava/lang/Object;
    //   316: astore 4
    //   318: aload 4
    //   320: monitorenter
    //   321: aload_0
    //   322: getfield 15	com/google/android/android/measurement/internal/zzbs:zzapg	Lcom/google/android/android/measurement/internal/zzbo;
    //   325: invokestatic 83	com/google/android/android/measurement/internal/zzbo:access$getMIsAvailable	(Lcom/google/android/android/measurement/internal/zzbo;)Ljava/util/concurrent/Semaphore;
    //   328: invokevirtual 140	java/util/concurrent/Semaphore:release	()V
    //   331: aload_0
    //   332: getfield 15	com/google/android/android/measurement/internal/zzbs:zzapg	Lcom/google/android/android/measurement/internal/zzbo;
    //   335: invokestatic 137	com/google/android/android/measurement/internal/zzbo:access$getMDecoderLock	(Lcom/google/android/android/measurement/internal/zzbo;)Ljava/lang/Object;
    //   338: invokevirtual 143	java/lang/Object:notifyAll	()V
    //   341: aload_0
    //   342: aload_0
    //   343: getfield 15	com/google/android/android/measurement/internal/zzbs:zzapg	Lcom/google/android/android/measurement/internal/zzbo;
    //   346: invokestatic 147	com/google/android/android/measurement/internal/zzbo:getBasePath	(Lcom/google/android/android/measurement/internal/zzbo;)Lcom/google/android/android/measurement/internal/zzbs;
    //   349: if_acmpeq +44 -> 393
    //   352: aload_0
    //   353: aload_0
    //   354: getfield 15	com/google/android/android/measurement/internal/zzbs:zzapg	Lcom/google/android/android/measurement/internal/zzbo;
    //   357: invokestatic 154	com/google/android/android/measurement/internal/zzbo:Zip	(Lcom/google/android/android/measurement/internal/zzbo;)Lcom/google/android/android/measurement/internal/zzbs;
    //   360: if_acmpne +15 -> 375
    //   363: aload_0
    //   364: getfield 15	com/google/android/android/measurement/internal/zzbs:zzapg	Lcom/google/android/android/measurement/internal/zzbo;
    //   367: aconst_null
    //   368: invokestatic 157	com/google/android/android/measurement/internal/zzbo:makeView	(Lcom/google/android/android/measurement/internal/zzbo;Lcom/google/android/android/measurement/internal/zzbs;)Lcom/google/android/android/measurement/internal/zzbs;
    //   371: pop
    //   372: goto +30 -> 402
    //   375: aload_0
    //   376: getfield 15	com/google/android/android/measurement/internal/zzbs:zzapg	Lcom/google/android/android/measurement/internal/zzbo;
    //   379: invokevirtual 44	com/google/android/android/measurement/internal/zzco:zzgo	()Lcom/google/android/android/measurement/internal/zzap;
    //   382: invokevirtual 160	com/google/android/android/measurement/internal/zzap:zzjd	()Lcom/google/android/android/measurement/internal/zzar;
    //   385: ldc -94
    //   387: invokevirtual 165	com/google/android/android/measurement/internal/zzar:zzbx	(Ljava/lang/String;)V
    //   390: goto +12 -> 402
    //   393: aload_0
    //   394: getfield 15	com/google/android/android/measurement/internal/zzbs:zzapg	Lcom/google/android/android/measurement/internal/zzbo;
    //   397: aconst_null
    //   398: invokestatic 151	com/google/android/android/measurement/internal/zzbo:getWordRangeAtCursor	(Lcom/google/android/android/measurement/internal/zzbo;Lcom/google/android/android/measurement/internal/zzbs;)Lcom/google/android/android/measurement/internal/zzbs;
    //   401: pop
    //   402: aload 4
    //   404: monitorexit
    //   405: aload 5
    //   407: athrow
    //   408: astore 5
    //   410: aload 4
    //   412: monitorexit
    //   413: aload 5
    //   415: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	416	0	this	zzbs
    //   1	80	1	i	int
    //   42	31	2	j	int
    //   67	55	3	bool	boolean
    //   10	3	4	localZzbo	zzbo
    //   25	4	4	localInterruptedException1	InterruptedException
    //   55	356	4	localObject1	Object
    //   129	3	5	localObject2	Object
    //   142	4	5	localInterruptedException2	InterruptedException
    //   277	6	5	localThrowable1	Throwable
    //   291	6	5	localThrowable2	Throwable
    //   299	6	5	localThrowable3	Throwable
    //   307	99	5	localThrowable4	Throwable
    //   408	6	5	localThrowable5	Throwable
    // Exception table:
    //   from	to	target	type
    //   12	20	25	java/lang/InterruptedException
    //   131	139	142	java/lang/InterruptedException
    //   192	232	277	java/lang/Throwable
    //   235	255	277	java/lang/Throwable
    //   258	273	277	java/lang/Throwable
    //   273	276	277	java/lang/Throwable
    //   279	282	277	java/lang/Throwable
    //   165	180	291	java/lang/Throwable
    //   285	288	291	java/lang/Throwable
    //   293	296	291	java/lang/Throwable
    //   101	121	299	java/lang/Throwable
    //   131	139	299	java/lang/Throwable
    //   144	150	299	java/lang/Throwable
    //   150	153	299	java/lang/Throwable
    //   301	304	299	java/lang/Throwable
    //   36	43	307	java/lang/Throwable
    //   43	57	307	java/lang/Throwable
    //   62	68	307	java/lang/Throwable
    //   80	89	307	java/lang/Throwable
    //   92	101	307	java/lang/Throwable
    //   153	165	307	java/lang/Throwable
    //   296	299	307	java/lang/Throwable
    //   304	307	307	java/lang/Throwable
    //   321	372	408	java/lang/Throwable
    //   375	390	408	java/lang/Throwable
    //   393	402	408	java/lang/Throwable
    //   402	405	408	java/lang/Throwable
    //   410	413	408	java/lang/Throwable
  }
  
  public final void zzke()
  {
    Object localObject = zzapj;
    try
    {
      zzapj.notifyAll();
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
}
