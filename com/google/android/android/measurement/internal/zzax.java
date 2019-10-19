package com.google.android.android.measurement.internal;

import android.support.annotation.WorkerThread;
import com.google.android.android.common.internal.Preconditions;
import java.net.URL;
import java.util.Map;

@WorkerThread
final class zzax
  implements Runnable
{
  private final URL mURL;
  private final String packageName;
  private final byte[] zzamv;
  private final zzav zzamw;
  private final Map<String, String> zzamx;
  
  public zzax(zzat paramZzat, String paramString, URL paramURL, byte[] paramArrayOfByte, Map paramMap, zzav paramZzav)
  {
    Preconditions.checkNotEmpty(paramString);
    Preconditions.checkNotNull(paramURL);
    Preconditions.checkNotNull(paramZzav);
    mURL = paramURL;
    zzamv = paramArrayOfByte;
    zzamw = paramZzav;
    packageName = paramString;
    zzamx = paramMap;
  }
  
  /* Error */
  public final void run()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 24	com/google/android/android/measurement/internal/zzax:zzamy	Lcom/google/android/android/measurement/internal/zzat;
    //   4: invokevirtual 58	com/google/android/android/measurement/internal/zzco:zzgc	()V
    //   7: aconst_null
    //   8: astore 6
    //   10: aconst_null
    //   11: astore 7
    //   13: aconst_null
    //   14: astore 8
    //   16: aconst_null
    //   17: astore 9
    //   19: aload_0
    //   20: getfield 24	com/google/android/android/measurement/internal/zzax:zzamy	Lcom/google/android/android/measurement/internal/zzat;
    //   23: astore 4
    //   25: aload_0
    //   26: getfield 39	com/google/android/android/measurement/internal/zzax:mURL	Ljava/net/URL;
    //   29: astore 5
    //   31: aload 4
    //   33: aload 5
    //   35: invokevirtual 64	com/google/android/android/measurement/internal/zzat:createConnection	(Ljava/net/URL;)Ljava/net/HttpURLConnection;
    //   38: astore 10
    //   40: aload 10
    //   42: astore 4
    //   44: aload_0
    //   45: getfield 47	com/google/android/android/measurement/internal/zzax:zzamx	Ljava/util/Map;
    //   48: astore 5
    //   50: aload 5
    //   52: ifnull +95 -> 147
    //   55: aload_0
    //   56: getfield 47	com/google/android/android/measurement/internal/zzax:zzamx	Ljava/util/Map;
    //   59: astore 5
    //   61: aload 5
    //   63: invokeinterface 70 1 0
    //   68: invokeinterface 76 1 0
    //   73: astore 5
    //   75: aload 5
    //   77: invokeinterface 82 1 0
    //   82: istore_3
    //   83: iload_3
    //   84: ifeq +63 -> 147
    //   87: aload 5
    //   89: invokeinterface 86 1 0
    //   94: astore 11
    //   96: aload 11
    //   98: checkcast 88	java/util/Map$Entry
    //   101: astore 12
    //   103: aload 12
    //   105: invokeinterface 91 1 0
    //   110: astore 11
    //   112: aload 11
    //   114: checkcast 93	java/lang/String
    //   117: astore 11
    //   119: aload 12
    //   121: invokeinterface 96 1 0
    //   126: astore 12
    //   128: aload 12
    //   130: checkcast 93	java/lang/String
    //   133: astore 12
    //   135: aload 10
    //   137: aload 11
    //   139: aload 12
    //   141: invokevirtual 102	java/net/HttpURLConnection:addRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   144: goto -69 -> 75
    //   147: aload_0
    //   148: getfield 41	com/google/android/android/measurement/internal/zzax:zzamv	[B
    //   151: astore 5
    //   153: aload 5
    //   155: ifnull +132 -> 287
    //   158: aload_0
    //   159: getfield 24	com/google/android/android/measurement/internal/zzax:zzamy	Lcom/google/android/android/measurement/internal/zzat;
    //   162: astore 5
    //   164: aload 5
    //   166: invokevirtual 108	com/google/android/android/measurement/internal/zzey:zzjo	()Lcom/google/android/android/measurement/internal/zzfg;
    //   169: astore 5
    //   171: aload_0
    //   172: getfield 41	com/google/android/android/measurement/internal/zzax:zzamv	[B
    //   175: astore 11
    //   177: aload 5
    //   179: aload 11
    //   181: invokevirtual 114	com/google/android/android/measurement/internal/zzfg:compress	([B)[B
    //   184: astore 11
    //   186: aload_0
    //   187: getfield 24	com/google/android/android/measurement/internal/zzax:zzamy	Lcom/google/android/android/measurement/internal/zzat;
    //   190: astore 5
    //   192: aload 5
    //   194: invokevirtual 118	com/google/android/android/measurement/internal/zzco:zzgo	()Lcom/google/android/android/measurement/internal/zzap;
    //   197: invokevirtual 124	com/google/android/android/measurement/internal/zzap:zzjl	()Lcom/google/android/android/measurement/internal/zzar;
    //   200: astore 5
    //   202: aload 11
    //   204: arraylength
    //   205: istore_1
    //   206: aload 5
    //   208: ldc 126
    //   210: iload_1
    //   211: invokestatic 132	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   214: invokevirtual 138	com/google/android/android/measurement/internal/zzar:append	(Ljava/lang/String;Ljava/lang/Object;)V
    //   217: aload 10
    //   219: iconst_1
    //   220: invokevirtual 142	java/net/HttpURLConnection:setDoOutput	(Z)V
    //   223: aload 10
    //   225: ldc -112
    //   227: ldc -110
    //   229: invokevirtual 102	java/net/HttpURLConnection:addRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   232: aload 11
    //   234: arraylength
    //   235: istore_1
    //   236: aload 10
    //   238: iload_1
    //   239: invokevirtual 150	java/net/HttpURLConnection:setFixedLengthStreamingMode	(I)V
    //   242: aload 10
    //   244: invokevirtual 153	java/net/HttpURLConnection:connect	()V
    //   247: aload 10
    //   249: invokevirtual 157	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   252: astore 5
    //   254: aload 5
    //   256: aload 11
    //   258: invokevirtual 163	java/io/OutputStream:write	([B)V
    //   261: aload 5
    //   263: invokevirtual 166	java/io/OutputStream:close	()V
    //   266: goto +21 -> 287
    //   269: astore 7
    //   271: aload 4
    //   273: astore 6
    //   275: aload 7
    //   277: astore 4
    //   279: goto +174 -> 453
    //   282: astore 6
    //   284: goto +282 -> 566
    //   287: aload 10
    //   289: invokevirtual 170	java/net/HttpURLConnection:getResponseCode	()I
    //   292: istore_2
    //   293: iload_2
    //   294: istore_1
    //   295: aload 10
    //   297: invokevirtual 174	java/net/HttpURLConnection:getHeaderFields	()Ljava/util/Map;
    //   300: astore 7
    //   302: aload 7
    //   304: astore 5
    //   306: aload_0
    //   307: getfield 24	com/google/android/android/measurement/internal/zzax:zzamy	Lcom/google/android/android/measurement/internal/zzat;
    //   310: astore 9
    //   312: aload 9
    //   314: aload 10
    //   316: invokestatic 178	com/google/android/android/measurement/internal/zzat:fetch	(Lcom/google/android/android/measurement/internal/zzat;Ljava/net/HttpURLConnection;)[B
    //   319: astore 9
    //   321: aload 10
    //   323: ifnull +8 -> 331
    //   326: aload 10
    //   328: invokevirtual 181	java/net/HttpURLConnection:disconnect	()V
    //   331: aload_0
    //   332: getfield 24	com/google/android/android/measurement/internal/zzax:zzamy	Lcom/google/android/android/measurement/internal/zzat;
    //   335: invokevirtual 185	com/google/android/android/measurement/internal/zzco:zzgn	()Lcom/google/android/android/measurement/internal/zzbo;
    //   338: new 187	com/google/android/android/measurement/internal/zzaw
    //   341: dup
    //   342: aload_0
    //   343: getfield 45	com/google/android/android/measurement/internal/zzax:packageName	Ljava/lang/String;
    //   346: aload_0
    //   347: getfield 43	com/google/android/android/measurement/internal/zzax:zzamw	Lcom/google/android/android/measurement/internal/zzav;
    //   350: iload_2
    //   351: aconst_null
    //   352: aload 9
    //   354: aload 7
    //   356: aconst_null
    //   357: invokespecial 190	com/google/android/android/measurement/internal/zzaw:<init>	(Ljava/lang/String;Lcom/google/android/android/measurement/internal/zzav;ILjava/lang/Throwable;[BLjava/util/Map;Lcom/google/android/android/measurement/internal/zzau;)V
    //   360: invokevirtual 196	com/google/android/android/measurement/internal/zzbo:get	(Ljava/lang/Runnable;)V
    //   363: return
    //   364: astore 7
    //   366: aload 6
    //   368: astore 8
    //   370: aload 4
    //   372: astore 6
    //   374: aload 5
    //   376: astore 4
    //   378: goto +92 -> 470
    //   381: astore 6
    //   383: goto +28 -> 411
    //   386: astore 7
    //   388: aconst_null
    //   389: astore 5
    //   391: aload 6
    //   393: astore 8
    //   395: aload 4
    //   397: astore 6
    //   399: aload 5
    //   401: astore 4
    //   403: goto +67 -> 470
    //   406: astore 6
    //   408: aconst_null
    //   409: astore 5
    //   411: aload 6
    //   413: astore 7
    //   415: aload 5
    //   417: astore 6
    //   419: goto +164 -> 583
    //   422: astore 7
    //   424: aload 9
    //   426: astore 5
    //   428: aload 4
    //   430: astore 6
    //   432: aload 7
    //   434: astore 4
    //   436: goto +17 -> 453
    //   439: astore 5
    //   441: goto +117 -> 558
    //   444: astore 4
    //   446: aconst_null
    //   447: astore 6
    //   449: aload 9
    //   451: astore 5
    //   453: aconst_null
    //   454: astore 8
    //   456: iconst_0
    //   457: istore_1
    //   458: aload 4
    //   460: astore 7
    //   462: aload 8
    //   464: astore 4
    //   466: aload 5
    //   468: astore 8
    //   470: aload 8
    //   472: ifnull +37 -> 509
    //   475: aload 8
    //   477: invokevirtual 166	java/io/OutputStream:close	()V
    //   480: goto +29 -> 509
    //   483: astore 5
    //   485: aload_0
    //   486: getfield 24	com/google/android/android/measurement/internal/zzax:zzamy	Lcom/google/android/android/measurement/internal/zzat;
    //   489: invokevirtual 118	com/google/android/android/measurement/internal/zzco:zzgo	()Lcom/google/android/android/measurement/internal/zzap;
    //   492: invokevirtual 199	com/google/android/android/measurement/internal/zzap:zzjd	()Lcom/google/android/android/measurement/internal/zzar;
    //   495: ldc -55
    //   497: aload_0
    //   498: getfield 45	com/google/android/android/measurement/internal/zzax:packageName	Ljava/lang/String;
    //   501: invokestatic 205	com/google/android/android/measurement/internal/zzap:zzbv	(Ljava/lang/String;)Ljava/lang/Object;
    //   504: aload 5
    //   506: invokevirtual 208	com/google/android/android/measurement/internal/zzar:append	(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    //   509: aload 6
    //   511: ifnull +8 -> 519
    //   514: aload 6
    //   516: invokevirtual 181	java/net/HttpURLConnection:disconnect	()V
    //   519: aload_0
    //   520: getfield 24	com/google/android/android/measurement/internal/zzax:zzamy	Lcom/google/android/android/measurement/internal/zzat;
    //   523: invokevirtual 185	com/google/android/android/measurement/internal/zzco:zzgn	()Lcom/google/android/android/measurement/internal/zzbo;
    //   526: new 187	com/google/android/android/measurement/internal/zzaw
    //   529: dup
    //   530: aload_0
    //   531: getfield 45	com/google/android/android/measurement/internal/zzax:packageName	Ljava/lang/String;
    //   534: aload_0
    //   535: getfield 43	com/google/android/android/measurement/internal/zzax:zzamw	Lcom/google/android/android/measurement/internal/zzav;
    //   538: iload_1
    //   539: aconst_null
    //   540: aconst_null
    //   541: aload 4
    //   543: aconst_null
    //   544: invokespecial 190	com/google/android/android/measurement/internal/zzaw:<init>	(Ljava/lang/String;Lcom/google/android/android/measurement/internal/zzav;ILjava/lang/Throwable;[BLjava/util/Map;Lcom/google/android/android/measurement/internal/zzau;)V
    //   547: invokevirtual 196	com/google/android/android/measurement/internal/zzbo:get	(Ljava/lang/Runnable;)V
    //   550: aload 7
    //   552: athrow
    //   553: astore 5
    //   555: aconst_null
    //   556: astore 4
    //   558: aload 5
    //   560: astore 6
    //   562: aload 7
    //   564: astore 5
    //   566: aconst_null
    //   567: astore 8
    //   569: iconst_0
    //   570: istore_1
    //   571: aload 6
    //   573: astore 7
    //   575: aload 8
    //   577: astore 6
    //   579: aload 5
    //   581: astore 8
    //   583: aload 8
    //   585: ifnull +37 -> 622
    //   588: aload 8
    //   590: invokevirtual 166	java/io/OutputStream:close	()V
    //   593: goto +29 -> 622
    //   596: astore 5
    //   598: aload_0
    //   599: getfield 24	com/google/android/android/measurement/internal/zzax:zzamy	Lcom/google/android/android/measurement/internal/zzat;
    //   602: invokevirtual 118	com/google/android/android/measurement/internal/zzco:zzgo	()Lcom/google/android/android/measurement/internal/zzap;
    //   605: invokevirtual 199	com/google/android/android/measurement/internal/zzap:zzjd	()Lcom/google/android/android/measurement/internal/zzar;
    //   608: ldc -55
    //   610: aload_0
    //   611: getfield 45	com/google/android/android/measurement/internal/zzax:packageName	Ljava/lang/String;
    //   614: invokestatic 205	com/google/android/android/measurement/internal/zzap:zzbv	(Ljava/lang/String;)Ljava/lang/Object;
    //   617: aload 5
    //   619: invokevirtual 208	com/google/android/android/measurement/internal/zzar:append	(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    //   622: aload 4
    //   624: ifnull +8 -> 632
    //   627: aload 4
    //   629: invokevirtual 181	java/net/HttpURLConnection:disconnect	()V
    //   632: aload_0
    //   633: getfield 24	com/google/android/android/measurement/internal/zzax:zzamy	Lcom/google/android/android/measurement/internal/zzat;
    //   636: invokevirtual 185	com/google/android/android/measurement/internal/zzco:zzgn	()Lcom/google/android/android/measurement/internal/zzbo;
    //   639: new 187	com/google/android/android/measurement/internal/zzaw
    //   642: dup
    //   643: aload_0
    //   644: getfield 45	com/google/android/android/measurement/internal/zzax:packageName	Ljava/lang/String;
    //   647: aload_0
    //   648: getfield 43	com/google/android/android/measurement/internal/zzax:zzamw	Lcom/google/android/android/measurement/internal/zzav;
    //   651: iload_1
    //   652: aload 7
    //   654: checkcast 53	java/lang/Throwable
    //   657: aconst_null
    //   658: aload 6
    //   660: aconst_null
    //   661: invokespecial 190	com/google/android/android/measurement/internal/zzaw:<init>	(Ljava/lang/String;Lcom/google/android/android/measurement/internal/zzav;ILjava/lang/Throwable;[BLjava/util/Map;Lcom/google/android/android/measurement/internal/zzau;)V
    //   664: invokevirtual 196	com/google/android/android/measurement/internal/zzbo:get	(Ljava/lang/Runnable;)V
    //   667: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	668	0	this	zzax
    //   205	447	1	i	int
    //   292	59	2	j	int
    //   82	2	3	bool	boolean
    //   23	412	4	localObject1	Object
    //   444	15	4	localThrowable1	Throwable
    //   464	164	4	localObject2	Object
    //   29	398	5	localObject3	Object
    //   439	1	5	localIOException1	java.io.IOException
    //   451	16	5	localObject4	Object
    //   483	22	5	localIOException2	java.io.IOException
    //   553	6	5	localIOException3	java.io.IOException
    //   564	16	5	localObject5	Object
    //   596	22	5	localIOException4	java.io.IOException
    //   8	266	6	localObject6	Object
    //   282	85	6	localIOException5	java.io.IOException
    //   372	1	6	localObject7	Object
    //   381	11	6	localIOException6	java.io.IOException
    //   397	1	6	localObject8	Object
    //   406	6	6	localIOException7	java.io.IOException
    //   417	242	6	localObject9	Object
    //   11	1	7	localObject10	Object
    //   269	7	7	localThrowable2	Throwable
    //   300	55	7	localMap	Map
    //   364	1	7	localThrowable3	Throwable
    //   386	1	7	localThrowable4	Throwable
    //   413	1	7	localIOException8	java.io.IOException
    //   422	11	7	localThrowable5	Throwable
    //   460	193	7	localObject11	Object
    //   14	575	8	localObject12	Object
    //   17	433	9	localObject13	Object
    //   38	289	10	localHttpURLConnection	java.net.HttpURLConnection
    //   94	163	11	localObject14	Object
    //   101	39	12	localObject15	Object
    // Exception table:
    //   from	to	target	type
    //   254	266	269	java/lang/Throwable
    //   254	266	282	java/io/IOException
    //   312	321	364	java/lang/Throwable
    //   312	321	381	java/io/IOException
    //   295	302	386	java/lang/Throwable
    //   295	302	406	java/io/IOException
    //   44	50	422	java/lang/Throwable
    //   61	75	422	java/lang/Throwable
    //   75	83	422	java/lang/Throwable
    //   87	96	422	java/lang/Throwable
    //   96	103	422	java/lang/Throwable
    //   103	112	422	java/lang/Throwable
    //   112	119	422	java/lang/Throwable
    //   119	128	422	java/lang/Throwable
    //   135	144	422	java/lang/Throwable
    //   147	153	422	java/lang/Throwable
    //   158	164	422	java/lang/Throwable
    //   164	171	422	java/lang/Throwable
    //   171	177	422	java/lang/Throwable
    //   177	186	422	java/lang/Throwable
    //   186	192	422	java/lang/Throwable
    //   192	202	422	java/lang/Throwable
    //   202	206	422	java/lang/Throwable
    //   206	232	422	java/lang/Throwable
    //   236	254	422	java/lang/Throwable
    //   287	293	422	java/lang/Throwable
    //   61	75	439	java/io/IOException
    //   75	83	439	java/io/IOException
    //   87	96	439	java/io/IOException
    //   103	112	439	java/io/IOException
    //   119	128	439	java/io/IOException
    //   135	144	439	java/io/IOException
    //   164	171	439	java/io/IOException
    //   177	186	439	java/io/IOException
    //   192	202	439	java/io/IOException
    //   206	232	439	java/io/IOException
    //   236	254	439	java/io/IOException
    //   287	293	439	java/io/IOException
    //   31	40	444	java/lang/Throwable
    //   475	480	483	java/io/IOException
    //   31	40	553	java/io/IOException
    //   588	593	596	java/io/IOException
  }
}
