package com.google.android.android.measurement.internal;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Parcel;

public final class zzal
  extends Log
{
  private final zzam zzalq = new zzam(this, getContext(), "google_app_measurement_local.db");
  private boolean zzalr;
  
  zzal(zzbt paramZzbt)
  {
    super(paramZzbt);
  }
  
  /* Error */
  private final boolean delete(int paramInt, byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 42	com/google/android/android/measurement/internal/zzco:zzgb	()V
    //   4: aload_0
    //   5: invokevirtual 45	com/google/android/android/measurement/internal/zzco:zzaf	()V
    //   8: aload_0
    //   9: getfield 47	com/google/android/android/measurement/internal/zzal:zzalr	Z
    //   12: ifeq +5 -> 17
    //   15: iconst_0
    //   16: ireturn
    //   17: new 49	android/content/ContentValues
    //   20: dup
    //   21: invokespecial 51	android/content/ContentValues:<init>	()V
    //   24: astore 16
    //   26: aload 16
    //   28: ldc 53
    //   30: iload_1
    //   31: invokestatic 59	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   34: invokevirtual 63	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   37: aload 16
    //   39: ldc 65
    //   41: aload_2
    //   42: invokevirtual 68	android/content/ContentValues:put	(Ljava/lang/String;[B)V
    //   45: iconst_0
    //   46: istore_1
    //   47: iconst_5
    //   48: istore_3
    //   49: iload_1
    //   50: iconst_5
    //   51: if_icmpge +603 -> 654
    //   54: aconst_null
    //   55: astore 15
    //   57: aconst_null
    //   58: astore 14
    //   60: aconst_null
    //   61: astore 12
    //   63: aconst_null
    //   64: astore 10
    //   66: aload_0
    //   67: invokespecial 72	com/google/android/android/measurement/internal/zzal:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   70: astore 13
    //   72: aload 13
    //   74: astore_2
    //   75: aload 13
    //   77: ifnonnull +47 -> 124
    //   80: aload 12
    //   82: astore 11
    //   84: aload_2
    //   85: astore 10
    //   87: aload_0
    //   88: iconst_1
    //   89: putfield 47	com/google/android/android/measurement/internal/zzal:zzalr	Z
    //   92: aload 13
    //   94: ifnull +592 -> 686
    //   97: aload 13
    //   99: invokevirtual 77	android/database/sqlite/SQLiteDatabase:close	()V
    //   102: iconst_0
    //   103: ireturn
    //   104: astore_2
    //   105: aconst_null
    //   106: astore 11
    //   108: aload 13
    //   110: astore 10
    //   112: goto +269 -> 381
    //   115: astore 13
    //   117: aload 14
    //   119: astore 12
    //   121: goto +424 -> 545
    //   124: aload 12
    //   126: astore 11
    //   128: aload_2
    //   129: astore 10
    //   131: aload 13
    //   133: invokevirtual 80	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   136: lconst_0
    //   137: lstore 7
    //   139: aload 12
    //   141: astore 11
    //   143: aload_2
    //   144: astore 10
    //   146: aload 13
    //   148: ldc 82
    //   150: aconst_null
    //   151: invokevirtual 86	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   154: astore 12
    //   156: aload 12
    //   158: astore 11
    //   160: lload 7
    //   162: lstore 5
    //   164: aload 12
    //   166: ifnull +48 -> 214
    //   169: aload 12
    //   171: invokeinterface 92 1 0
    //   176: istore 9
    //   178: lload 7
    //   180: lstore 5
    //   182: iload 9
    //   184: ifeq +30 -> 214
    //   187: aload 12
    //   189: iconst_0
    //   190: invokeinterface 96 2 0
    //   195: lstore 5
    //   197: goto +17 -> 214
    //   200: astore 12
    //   202: goto +423 -> 625
    //   205: astore_2
    //   206: goto -98 -> 108
    //   209: astore 13
    //   211: goto +334 -> 545
    //   214: lload 5
    //   216: ldc2_w 97
    //   219: lcmp
    //   220: iflt +99 -> 319
    //   223: aload_0
    //   224: invokevirtual 102	com/google/android/android/measurement/internal/zzco:zzgo	()Lcom/google/android/android/measurement/internal/zzap;
    //   227: invokevirtual 108	com/google/android/android/measurement/internal/zzap:zzjd	()Lcom/google/android/android/measurement/internal/zzar;
    //   230: ldc 110
    //   232: invokevirtual 116	com/google/android/android/measurement/internal/zzar:zzbx	(Ljava/lang/String;)V
    //   235: ldc2_w 97
    //   238: lload 5
    //   240: lsub
    //   241: lconst_1
    //   242: ladd
    //   243: lstore 5
    //   245: lload 5
    //   247: invokestatic 122	java/lang/Long:toString	(J)Ljava/lang/String;
    //   250: astore 10
    //   252: aload 13
    //   254: ldc 124
    //   256: ldc 126
    //   258: iconst_1
    //   259: anewarray 128	java/lang/String
    //   262: dup
    //   263: iconst_0
    //   264: aload 10
    //   266: aastore
    //   267: invokevirtual 131	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   270: istore 4
    //   272: iload 4
    //   274: i2l
    //   275: lstore 7
    //   277: lload 7
    //   279: lload 5
    //   281: lcmp
    //   282: ifeq +37 -> 319
    //   285: aload_0
    //   286: invokevirtual 102	com/google/android/android/measurement/internal/zzco:zzgo	()Lcom/google/android/android/measurement/internal/zzap;
    //   289: invokevirtual 108	com/google/android/android/measurement/internal/zzap:zzjd	()Lcom/google/android/android/measurement/internal/zzar;
    //   292: astore 10
    //   294: aload 10
    //   296: ldc -123
    //   298: lload 5
    //   300: invokestatic 136	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   303: lload 7
    //   305: invokestatic 136	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   308: lload 5
    //   310: lload 7
    //   312: lsub
    //   313: invokestatic 136	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   316: invokevirtual 140	com/google/android/android/measurement/internal/zzar:append	(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V
    //   319: aload 13
    //   321: ldc 124
    //   323: aconst_null
    //   324: aload 16
    //   326: invokevirtual 144	android/database/sqlite/SQLiteDatabase:insertOrThrow	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   329: pop2
    //   330: aload 13
    //   332: invokevirtual 147	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   335: aload 13
    //   337: invokevirtual 150	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   340: aload 12
    //   342: ifnull +10 -> 352
    //   345: aload 12
    //   347: invokeinterface 151 1 0
    //   352: aload 13
    //   354: ifnull +334 -> 688
    //   357: aload 13
    //   359: invokevirtual 77	android/database/sqlite/SQLiteDatabase:close	()V
    //   362: iconst_1
    //   363: ireturn
    //   364: goto +117 -> 481
    //   367: astore 12
    //   369: aconst_null
    //   370: astore_2
    //   371: aconst_null
    //   372: astore 11
    //   374: goto +251 -> 625
    //   377: astore_2
    //   378: aconst_null
    //   379: astore 11
    //   381: aload 10
    //   383: ifnull +37 -> 420
    //   386: aload 10
    //   388: checkcast 74	android/database/sqlite/SQLiteDatabase
    //   391: invokevirtual 154	android/database/sqlite/SQLiteDatabase:inTransaction	()Z
    //   394: istore 9
    //   396: iload 9
    //   398: ifeq +22 -> 420
    //   401: aload 10
    //   403: checkcast 74	android/database/sqlite/SQLiteDatabase
    //   406: invokevirtual 150	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   409: goto +11 -> 420
    //   412: astore 12
    //   414: aload 10
    //   416: astore_2
    //   417: goto +208 -> 625
    //   420: aload_0
    //   421: invokevirtual 102	com/google/android/android/measurement/internal/zzco:zzgo	()Lcom/google/android/android/measurement/internal/zzap;
    //   424: invokevirtual 108	com/google/android/android/measurement/internal/zzap:zzjd	()Lcom/google/android/android/measurement/internal/zzar;
    //   427: ldc -100
    //   429: aload_2
    //   430: invokevirtual 159	com/google/android/android/measurement/internal/zzar:append	(Ljava/lang/String;Ljava/lang/Object;)V
    //   433: aload_0
    //   434: iconst_1
    //   435: putfield 47	com/google/android/android/measurement/internal/zzal:zzalr	Z
    //   438: aload 11
    //   440: ifnull +13 -> 453
    //   443: aload 11
    //   445: checkcast 88	android/database/Cursor
    //   448: invokeinterface 151 1 0
    //   453: iload_3
    //   454: istore 4
    //   456: aload 10
    //   458: ifnull +152 -> 610
    //   461: aload 10
    //   463: checkcast 74	android/database/sqlite/SQLiteDatabase
    //   466: invokevirtual 77	android/database/sqlite/SQLiteDatabase:close	()V
    //   469: iload_3
    //   470: istore 4
    //   472: goto +138 -> 610
    //   475: aconst_null
    //   476: astore_2
    //   477: aload 15
    //   479: astore 12
    //   481: iload_3
    //   482: i2l
    //   483: lstore 5
    //   485: aload 12
    //   487: astore 11
    //   489: aload_2
    //   490: astore 10
    //   492: lload 5
    //   494: invokestatic 165	android/os/SystemClock:sleep	(J)V
    //   497: iload_3
    //   498: bipush 20
    //   500: iadd
    //   501: istore_3
    //   502: aload 12
    //   504: ifnull +13 -> 517
    //   507: aload 12
    //   509: checkcast 88	android/database/Cursor
    //   512: invokeinterface 151 1 0
    //   517: iload_3
    //   518: istore 4
    //   520: aload_2
    //   521: ifnull +89 -> 610
    //   524: aload_2
    //   525: checkcast 74	android/database/sqlite/SQLiteDatabase
    //   528: invokevirtual 77	android/database/sqlite/SQLiteDatabase:close	()V
    //   531: iload_3
    //   532: istore 4
    //   534: goto +76 -> 610
    //   537: astore 13
    //   539: aconst_null
    //   540: astore_2
    //   541: aload 14
    //   543: astore 12
    //   545: aload 12
    //   547: astore 11
    //   549: aload_2
    //   550: astore 10
    //   552: aload_0
    //   553: invokevirtual 102	com/google/android/android/measurement/internal/zzco:zzgo	()Lcom/google/android/android/measurement/internal/zzap;
    //   556: invokevirtual 108	com/google/android/android/measurement/internal/zzap:zzjd	()Lcom/google/android/android/measurement/internal/zzar;
    //   559: ldc -100
    //   561: aload 13
    //   563: invokevirtual 159	com/google/android/android/measurement/internal/zzar:append	(Ljava/lang/String;Ljava/lang/Object;)V
    //   566: aload 12
    //   568: astore 11
    //   570: aload_2
    //   571: astore 10
    //   573: aload_0
    //   574: iconst_1
    //   575: putfield 47	com/google/android/android/measurement/internal/zzal:zzalr	Z
    //   578: aload 12
    //   580: ifnull +13 -> 593
    //   583: aload 12
    //   585: checkcast 88	android/database/Cursor
    //   588: invokeinterface 151 1 0
    //   593: iload_3
    //   594: istore 4
    //   596: aload_2
    //   597: ifnull +13 -> 610
    //   600: aload_2
    //   601: checkcast 74	android/database/sqlite/SQLiteDatabase
    //   604: invokevirtual 77	android/database/sqlite/SQLiteDatabase:close	()V
    //   607: iload_3
    //   608: istore 4
    //   610: iload_1
    //   611: iconst_1
    //   612: iadd
    //   613: istore_1
    //   614: iload 4
    //   616: istore_3
    //   617: goto -568 -> 49
    //   620: astore 12
    //   622: aload 10
    //   624: astore_2
    //   625: aload 11
    //   627: ifnull +13 -> 640
    //   630: aload 11
    //   632: checkcast 88	android/database/Cursor
    //   635: invokeinterface 151 1 0
    //   640: aload_2
    //   641: ifnull +10 -> 651
    //   644: aload_2
    //   645: checkcast 74	android/database/sqlite/SQLiteDatabase
    //   648: invokevirtual 77	android/database/sqlite/SQLiteDatabase:close	()V
    //   651: aload 12
    //   653: athrow
    //   654: aload_0
    //   655: invokevirtual 102	com/google/android/android/measurement/internal/zzco:zzgo	()Lcom/google/android/android/measurement/internal/zzap;
    //   658: invokevirtual 168	com/google/android/android/measurement/internal/zzap:zzjg	()Lcom/google/android/android/measurement/internal/zzar;
    //   661: ldc -86
    //   663: invokevirtual 116	com/google/android/android/measurement/internal/zzar:zzbx	(Ljava/lang/String;)V
    //   666: iconst_0
    //   667: ireturn
    //   668: astore_2
    //   669: goto -194 -> 475
    //   672: astore 10
    //   674: aload 15
    //   676: astore 12
    //   678: goto -197 -> 481
    //   681: astore 10
    //   683: goto -319 -> 364
    //   686: iconst_0
    //   687: ireturn
    //   688: iconst_1
    //   689: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	690	0	this	zzal
    //   0	690	1	paramInt	int
    //   0	690	2	paramArrayOfByte	byte[]
    //   48	569	3	i	int
    //   270	345	4	j	int
    //   162	331	5	l1	long
    //   137	174	7	l2	long
    //   176	221	9	bool	boolean
    //   64	559	10	localObject1	Object
    //   672	1	10	localSQLiteDatabaseLockedException1	android.database.sqlite.SQLiteDatabaseLockedException
    //   681	1	10	localSQLiteDatabaseLockedException2	android.database.sqlite.SQLiteDatabaseLockedException
    //   82	549	11	localObject2	Object
    //   61	127	12	localObject3	Object
    //   200	146	12	localThrowable1	Throwable
    //   367	1	12	localThrowable2	Throwable
    //   412	1	12	localThrowable3	Throwable
    //   479	105	12	localObject4	Object
    //   620	32	12	localThrowable4	Throwable
    //   676	1	12	localObject5	Object
    //   70	39	13	localSQLiteDatabase	SQLiteDatabase
    //   115	32	13	localSQLiteFullException1	android.database.sqlite.SQLiteFullException
    //   209	149	13	localSQLiteFullException2	android.database.sqlite.SQLiteFullException
    //   537	25	13	localSQLiteFullException3	android.database.sqlite.SQLiteFullException
    //   58	484	14	localObject6	Object
    //   55	620	15	localObject7	Object
    //   24	301	16	localContentValues	android.content.ContentValues
    // Exception table:
    //   from	to	target	type
    //   131	136	104	android/database/sqlite/SQLiteException
    //   146	156	104	android/database/sqlite/SQLiteException
    //   131	136	115	android/database/sqlite/SQLiteFullException
    //   146	156	115	android/database/sqlite/SQLiteFullException
    //   169	178	200	java/lang/Throwable
    //   187	197	200	java/lang/Throwable
    //   223	235	200	java/lang/Throwable
    //   245	252	200	java/lang/Throwable
    //   252	272	200	java/lang/Throwable
    //   285	294	200	java/lang/Throwable
    //   294	319	200	java/lang/Throwable
    //   319	340	200	java/lang/Throwable
    //   169	178	205	android/database/sqlite/SQLiteException
    //   187	197	205	android/database/sqlite/SQLiteException
    //   223	235	205	android/database/sqlite/SQLiteException
    //   245	252	205	android/database/sqlite/SQLiteException
    //   252	272	205	android/database/sqlite/SQLiteException
    //   285	294	205	android/database/sqlite/SQLiteException
    //   294	319	205	android/database/sqlite/SQLiteException
    //   319	340	205	android/database/sqlite/SQLiteException
    //   169	178	209	android/database/sqlite/SQLiteFullException
    //   187	197	209	android/database/sqlite/SQLiteFullException
    //   223	235	209	android/database/sqlite/SQLiteFullException
    //   245	252	209	android/database/sqlite/SQLiteFullException
    //   252	272	209	android/database/sqlite/SQLiteFullException
    //   285	294	209	android/database/sqlite/SQLiteFullException
    //   294	319	209	android/database/sqlite/SQLiteFullException
    //   319	340	209	android/database/sqlite/SQLiteFullException
    //   66	72	367	java/lang/Throwable
    //   66	72	377	android/database/sqlite/SQLiteException
    //   386	396	412	java/lang/Throwable
    //   401	409	412	java/lang/Throwable
    //   420	438	412	java/lang/Throwable
    //   66	72	537	android/database/sqlite/SQLiteFullException
    //   87	92	620	java/lang/Throwable
    //   131	136	620	java/lang/Throwable
    //   146	156	620	java/lang/Throwable
    //   492	497	620	java/lang/Throwable
    //   552	566	620	java/lang/Throwable
    //   573	578	620	java/lang/Throwable
    //   66	72	668	android/database/sqlite/SQLiteDatabaseLockedException
    //   131	136	672	android/database/sqlite/SQLiteDatabaseLockedException
    //   146	156	672	android/database/sqlite/SQLiteDatabaseLockedException
    //   169	178	681	android/database/sqlite/SQLiteDatabaseLockedException
    //   187	197	681	android/database/sqlite/SQLiteDatabaseLockedException
    //   223	235	681	android/database/sqlite/SQLiteDatabaseLockedException
    //   245	252	681	android/database/sqlite/SQLiteDatabaseLockedException
    //   252	272	681	android/database/sqlite/SQLiteDatabaseLockedException
    //   285	294	681	android/database/sqlite/SQLiteDatabaseLockedException
    //   294	319	681	android/database/sqlite/SQLiteDatabaseLockedException
    //   319	340	681	android/database/sqlite/SQLiteDatabaseLockedException
  }
  
  private final SQLiteDatabase getWritableDatabase()
    throws SQLiteException
  {
    if (zzalr) {
      return null;
    }
    SQLiteDatabase localSQLiteDatabase = zzalq.getWritableDatabase();
    if (localSQLiteDatabase == null)
    {
      zzalr = true;
      return null;
    }
    return localSQLiteDatabase;
  }
  
  /* Error */
  public final java.util.List doInBackground(int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 45	com/google/android/android/measurement/internal/zzco:zzaf	()V
    //   4: aload_0
    //   5: invokevirtual 42	com/google/android/android/measurement/internal/zzco:zzgb	()V
    //   8: aload_0
    //   9: getfield 47	com/google/android/android/measurement/internal/zzal:zzalr	Z
    //   12: ifeq +5 -> 17
    //   15: aconst_null
    //   16: areturn
    //   17: new 178	java/util/ArrayList
    //   20: dup
    //   21: invokespecial 179	java/util/ArrayList:<init>	()V
    //   24: astore 17
    //   26: aload_0
    //   27: invokevirtual 20	com/google/android/android/measurement/internal/zzco:getContext	()Landroid/content/Context;
    //   30: ldc 22
    //   32: invokevirtual 185	android/content/Context:getDatabasePath	(Ljava/lang/String;)Ljava/io/File;
    //   35: invokevirtual 190	java/io/File:exists	()Z
    //   38: ifne +6 -> 44
    //   41: aload 17
    //   43: areturn
    //   44: iconst_0
    //   45: istore_1
    //   46: iconst_5
    //   47: istore_2
    //   48: aconst_null
    //   49: astore 12
    //   51: iload_1
    //   52: iconst_5
    //   53: if_icmpge +1245 -> 1298
    //   56: aload_0
    //   57: invokespecial 72	com/google/android/android/measurement/internal/zzal:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   60: astore 13
    //   62: aload 13
    //   64: astore 10
    //   66: aload 10
    //   68: astore 11
    //   70: aload 10
    //   72: ifnonnull +27 -> 99
    //   75: aload 11
    //   77: astore 13
    //   79: aload_0
    //   80: iconst_1
    //   81: putfield 47	com/google/android/android/measurement/internal/zzal:zzalr	Z
    //   84: aload 10
    //   86: ifnull +1292 -> 1378
    //   89: aload 10
    //   91: invokevirtual 77	android/database/sqlite/SQLiteDatabase:close	()V
    //   94: aconst_null
    //   95: areturn
    //   96: goto +870 -> 966
    //   99: aload 10
    //   101: invokevirtual 80	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   104: bipush 100
    //   106: invokestatic 193	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   109: astore 12
    //   111: aload 10
    //   113: astore 11
    //   115: aload 10
    //   117: ldc 124
    //   119: iconst_3
    //   120: anewarray 128	java/lang/String
    //   123: dup
    //   124: iconst_0
    //   125: ldc -61
    //   127: aastore
    //   128: dup
    //   129: iconst_1
    //   130: ldc 53
    //   132: aastore
    //   133: dup
    //   134: iconst_2
    //   135: ldc 65
    //   137: aastore
    //   138: aconst_null
    //   139: aconst_null
    //   140: aconst_null
    //   141: aconst_null
    //   142: ldc -59
    //   144: aload 12
    //   146: invokevirtual 201	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   149: astore 12
    //   151: aload 12
    //   153: astore 13
    //   155: ldc2_w 202
    //   158: lstore 5
    //   160: aload 11
    //   162: astore 15
    //   164: aload 13
    //   166: astore 14
    //   168: aload 12
    //   170: invokeinterface 206 1 0
    //   175: istore 9
    //   177: iload 9
    //   179: ifeq +587 -> 766
    //   182: aload 11
    //   184: astore 15
    //   186: aload 13
    //   188: astore 14
    //   190: aload 12
    //   192: iconst_0
    //   193: invokeinterface 96 2 0
    //   198: lstore 7
    //   200: aload 11
    //   202: astore 15
    //   204: aload 13
    //   206: astore 14
    //   208: aload 12
    //   210: iconst_1
    //   211: invokeinterface 210 2 0
    //   216: istore_3
    //   217: aload 11
    //   219: astore 15
    //   221: aload 13
    //   223: astore 14
    //   225: aload 12
    //   227: iconst_2
    //   228: invokeinterface 214 2 0
    //   233: astore 19
    //   235: iload_3
    //   236: ifne +166 -> 402
    //   239: aload 11
    //   241: astore 15
    //   243: aload 13
    //   245: astore 14
    //   247: invokestatic 220	android/os/Parcel:obtain	()Landroid/os/Parcel;
    //   250: astore 16
    //   252: aload 19
    //   254: arraylength
    //   255: istore_3
    //   256: aload 16
    //   258: aload 19
    //   260: iconst_0
    //   261: iload_3
    //   262: invokevirtual 224	android/os/Parcel:unmarshall	([BII)V
    //   265: aload 16
    //   267: iconst_0
    //   268: invokevirtual 228	android/os/Parcel:setDataPosition	(I)V
    //   271: getstatic 234	com/google/android/android/measurement/internal/zzad:CREATOR	Landroid/os/Parcelable$Creator;
    //   274: astore 14
    //   276: aload 14
    //   278: aload 16
    //   280: invokeinterface 240 2 0
    //   285: astore 14
    //   287: aload 14
    //   289: checkcast 230	com/google/android/android/measurement/internal/zzad
    //   292: astore 18
    //   294: aload 11
    //   296: astore 15
    //   298: aload 13
    //   300: astore 14
    //   302: aload 16
    //   304: invokevirtual 243	android/os/Parcel:recycle	()V
    //   307: lload 7
    //   309: lstore 5
    //   311: aload 18
    //   313: ifnull -153 -> 160
    //   316: aload 11
    //   318: astore 15
    //   320: aload 13
    //   322: astore 14
    //   324: aload 17
    //   326: aload 18
    //   328: invokeinterface 249 2 0
    //   333: pop
    //   334: lload 7
    //   336: lstore 5
    //   338: goto -178 -> 160
    //   341: astore 18
    //   343: goto +35 -> 378
    //   346: aload_0
    //   347: invokevirtual 102	com/google/android/android/measurement/internal/zzco:zzgo	()Lcom/google/android/android/measurement/internal/zzap;
    //   350: invokevirtual 108	com/google/android/android/measurement/internal/zzap:zzjd	()Lcom/google/android/android/measurement/internal/zzar;
    //   353: ldc -5
    //   355: invokevirtual 116	com/google/android/android/measurement/internal/zzar:zzbx	(Ljava/lang/String;)V
    //   358: aload 11
    //   360: astore 15
    //   362: aload 13
    //   364: astore 14
    //   366: aload 16
    //   368: invokevirtual 243	android/os/Parcel:recycle	()V
    //   371: lload 7
    //   373: lstore 5
    //   375: goto -215 -> 160
    //   378: aload 11
    //   380: astore 15
    //   382: aload 13
    //   384: astore 14
    //   386: aload 16
    //   388: invokevirtual 243	android/os/Parcel:recycle	()V
    //   391: aload 11
    //   393: astore 15
    //   395: aload 13
    //   397: astore 14
    //   399: aload 18
    //   401: athrow
    //   402: iload_3
    //   403: iconst_1
    //   404: if_icmpne +166 -> 570
    //   407: aload 11
    //   409: astore 15
    //   411: aload 13
    //   413: astore 14
    //   415: invokestatic 220	android/os/Parcel:obtain	()Landroid/os/Parcel;
    //   418: astore 18
    //   420: aload 19
    //   422: arraylength
    //   423: istore_3
    //   424: aload 18
    //   426: aload 19
    //   428: iconst_0
    //   429: iload_3
    //   430: invokevirtual 224	android/os/Parcel:unmarshall	([BII)V
    //   433: aload 18
    //   435: iconst_0
    //   436: invokevirtual 228	android/os/Parcel:setDataPosition	(I)V
    //   439: getstatic 254	com/google/android/android/measurement/internal/zzfh:CREATOR	Landroid/os/Parcelable$Creator;
    //   442: astore 14
    //   444: aload 14
    //   446: aload 18
    //   448: invokeinterface 240 2 0
    //   453: astore 14
    //   455: aload 14
    //   457: checkcast 253	com/google/android/android/measurement/internal/zzfh
    //   460: astore 16
    //   462: aload 11
    //   464: astore 15
    //   466: aload 13
    //   468: astore 14
    //   470: aload 18
    //   472: invokevirtual 243	android/os/Parcel:recycle	()V
    //   475: goto +37 -> 512
    //   478: astore 16
    //   480: goto +66 -> 546
    //   483: aload_0
    //   484: invokevirtual 102	com/google/android/android/measurement/internal/zzco:zzgo	()Lcom/google/android/android/measurement/internal/zzap;
    //   487: invokevirtual 108	com/google/android/android/measurement/internal/zzap:zzjd	()Lcom/google/android/android/measurement/internal/zzar;
    //   490: ldc_w 256
    //   493: invokevirtual 116	com/google/android/android/measurement/internal/zzar:zzbx	(Ljava/lang/String;)V
    //   496: aload 11
    //   498: astore 15
    //   500: aload 13
    //   502: astore 14
    //   504: aload 18
    //   506: invokevirtual 243	android/os/Parcel:recycle	()V
    //   509: aconst_null
    //   510: astore 16
    //   512: lload 7
    //   514: lstore 5
    //   516: aload 16
    //   518: ifnull -358 -> 160
    //   521: aload 11
    //   523: astore 15
    //   525: aload 13
    //   527: astore 14
    //   529: aload 17
    //   531: aload 16
    //   533: invokeinterface 249 2 0
    //   538: pop
    //   539: lload 7
    //   541: lstore 5
    //   543: goto -383 -> 160
    //   546: aload 11
    //   548: astore 15
    //   550: aload 13
    //   552: astore 14
    //   554: aload 18
    //   556: invokevirtual 243	android/os/Parcel:recycle	()V
    //   559: aload 11
    //   561: astore 15
    //   563: aload 13
    //   565: astore 14
    //   567: aload 16
    //   569: athrow
    //   570: iload_3
    //   571: iconst_2
    //   572: if_icmpne +166 -> 738
    //   575: aload 11
    //   577: astore 15
    //   579: aload 13
    //   581: astore 14
    //   583: invokestatic 220	android/os/Parcel:obtain	()Landroid/os/Parcel;
    //   586: astore 18
    //   588: aload 19
    //   590: arraylength
    //   591: istore_3
    //   592: aload 18
    //   594: aload 19
    //   596: iconst_0
    //   597: iload_3
    //   598: invokevirtual 224	android/os/Parcel:unmarshall	([BII)V
    //   601: aload 18
    //   603: iconst_0
    //   604: invokevirtual 228	android/os/Parcel:setDataPosition	(I)V
    //   607: getstatic 259	com/google/android/android/measurement/internal/ComponentInfo:CREATOR	Landroid/os/Parcelable$Creator;
    //   610: astore 14
    //   612: aload 14
    //   614: aload 18
    //   616: invokeinterface 240 2 0
    //   621: astore 14
    //   623: aload 14
    //   625: checkcast 258	com/google/android/android/measurement/internal/ComponentInfo
    //   628: astore 16
    //   630: aload 11
    //   632: astore 15
    //   634: aload 13
    //   636: astore 14
    //   638: aload 18
    //   640: invokevirtual 243	android/os/Parcel:recycle	()V
    //   643: goto +37 -> 680
    //   646: astore 16
    //   648: goto +66 -> 714
    //   651: aload_0
    //   652: invokevirtual 102	com/google/android/android/measurement/internal/zzco:zzgo	()Lcom/google/android/android/measurement/internal/zzap;
    //   655: invokevirtual 108	com/google/android/android/measurement/internal/zzap:zzjd	()Lcom/google/android/android/measurement/internal/zzar;
    //   658: ldc_w 256
    //   661: invokevirtual 116	com/google/android/android/measurement/internal/zzar:zzbx	(Ljava/lang/String;)V
    //   664: aload 11
    //   666: astore 15
    //   668: aload 13
    //   670: astore 14
    //   672: aload 18
    //   674: invokevirtual 243	android/os/Parcel:recycle	()V
    //   677: aconst_null
    //   678: astore 16
    //   680: lload 7
    //   682: lstore 5
    //   684: aload 16
    //   686: ifnull -526 -> 160
    //   689: aload 11
    //   691: astore 15
    //   693: aload 13
    //   695: astore 14
    //   697: aload 17
    //   699: aload 16
    //   701: invokeinterface 249 2 0
    //   706: pop
    //   707: lload 7
    //   709: lstore 5
    //   711: goto -551 -> 160
    //   714: aload 11
    //   716: astore 15
    //   718: aload 13
    //   720: astore 14
    //   722: aload 18
    //   724: invokevirtual 243	android/os/Parcel:recycle	()V
    //   727: aload 11
    //   729: astore 15
    //   731: aload 13
    //   733: astore 14
    //   735: aload 16
    //   737: athrow
    //   738: aload 11
    //   740: astore 15
    //   742: aload 13
    //   744: astore 14
    //   746: aload_0
    //   747: invokevirtual 102	com/google/android/android/measurement/internal/zzco:zzgo	()Lcom/google/android/android/measurement/internal/zzap;
    //   750: invokevirtual 108	com/google/android/android/measurement/internal/zzap:zzjd	()Lcom/google/android/android/measurement/internal/zzar;
    //   753: ldc_w 261
    //   756: invokevirtual 116	com/google/android/android/measurement/internal/zzar:zzbx	(Ljava/lang/String;)V
    //   759: lload 7
    //   761: lstore 5
    //   763: goto -603 -> 160
    //   766: aload 11
    //   768: astore 15
    //   770: aload 13
    //   772: astore 14
    //   774: lload 5
    //   776: invokestatic 122	java/lang/Long:toString	(J)Ljava/lang/String;
    //   779: astore 16
    //   781: aload 11
    //   783: astore 15
    //   785: aload 13
    //   787: astore 14
    //   789: aload 10
    //   791: ldc 124
    //   793: ldc_w 263
    //   796: iconst_1
    //   797: anewarray 128	java/lang/String
    //   800: dup
    //   801: iconst_0
    //   802: aload 16
    //   804: aastore
    //   805: invokevirtual 131	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   808: istore_3
    //   809: aload 11
    //   811: astore 15
    //   813: aload 13
    //   815: astore 14
    //   817: aload 17
    //   819: invokeinterface 267 1 0
    //   824: istore 4
    //   826: iload_3
    //   827: iload 4
    //   829: if_icmpge +24 -> 853
    //   832: aload 11
    //   834: astore 15
    //   836: aload 13
    //   838: astore 14
    //   840: aload_0
    //   841: invokevirtual 102	com/google/android/android/measurement/internal/zzco:zzgo	()Lcom/google/android/android/measurement/internal/zzap;
    //   844: invokevirtual 108	com/google/android/android/measurement/internal/zzap:zzjd	()Lcom/google/android/android/measurement/internal/zzar;
    //   847: ldc_w 269
    //   850: invokevirtual 116	com/google/android/android/measurement/internal/zzar:zzbx	(Ljava/lang/String;)V
    //   853: aload 11
    //   855: astore 15
    //   857: aload 13
    //   859: astore 14
    //   861: aload 10
    //   863: invokevirtual 147	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   866: aload 11
    //   868: astore 15
    //   870: aload 13
    //   872: astore 14
    //   874: aload 10
    //   876: invokevirtual 150	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   879: aload 12
    //   881: ifnull +10 -> 891
    //   884: aload 12
    //   886: invokeinterface 151 1 0
    //   891: aload 10
    //   893: ifnull +487 -> 1380
    //   896: aload 10
    //   898: invokevirtual 77	android/database/sqlite/SQLiteDatabase:close	()V
    //   901: aload 17
    //   903: areturn
    //   904: astore 10
    //   906: goto +101 -> 1007
    //   909: aload 12
    //   911: astore 11
    //   913: goto +219 -> 1132
    //   916: astore 14
    //   918: aload 10
    //   920: astore 11
    //   922: aload 12
    //   924: astore 10
    //   926: goto +262 -> 1188
    //   929: astore 12
    //   931: aload 11
    //   933: astore 10
    //   935: goto +58 -> 993
    //   938: astore 10
    //   940: goto +64 -> 1004
    //   943: astore 14
    //   945: goto +33 -> 978
    //   948: astore 12
    //   950: goto +43 -> 993
    //   953: astore 12
    //   955: aload 10
    //   957: astore 11
    //   959: aload 12
    //   961: astore 10
    //   963: goto +41 -> 1004
    //   966: aload 13
    //   968: astore 10
    //   970: aconst_null
    //   971: astore 11
    //   973: goto +159 -> 1132
    //   976: astore 14
    //   978: aload 13
    //   980: astore 11
    //   982: aconst_null
    //   983: astore 10
    //   985: goto +203 -> 1188
    //   988: astore 12
    //   990: aconst_null
    //   991: astore 10
    //   993: aconst_null
    //   994: astore 11
    //   996: goto +277 -> 1273
    //   999: astore 10
    //   1001: aconst_null
    //   1002: astore 11
    //   1004: aconst_null
    //   1005: astore 13
    //   1007: aload 11
    //   1009: ifnull +52 -> 1061
    //   1012: aload 11
    //   1014: astore 15
    //   1016: aload 13
    //   1018: astore 14
    //   1020: aload 11
    //   1022: invokevirtual 154	android/database/sqlite/SQLiteDatabase:inTransaction	()Z
    //   1025: istore 9
    //   1027: iload 9
    //   1029: ifeq +32 -> 1061
    //   1032: aload 11
    //   1034: astore 15
    //   1036: aload 13
    //   1038: astore 14
    //   1040: aload 11
    //   1042: invokevirtual 150	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   1045: goto +16 -> 1061
    //   1048: astore 12
    //   1050: aload 15
    //   1052: astore 10
    //   1054: aload 14
    //   1056: astore 11
    //   1058: goto +215 -> 1273
    //   1061: aload 11
    //   1063: astore 15
    //   1065: aload 13
    //   1067: astore 14
    //   1069: aload_0
    //   1070: invokevirtual 102	com/google/android/android/measurement/internal/zzco:zzgo	()Lcom/google/android/android/measurement/internal/zzap;
    //   1073: invokevirtual 108	com/google/android/android/measurement/internal/zzap:zzjd	()Lcom/google/android/android/measurement/internal/zzar;
    //   1076: ldc_w 271
    //   1079: aload 10
    //   1081: invokevirtual 159	com/google/android/android/measurement/internal/zzar:append	(Ljava/lang/String;Ljava/lang/Object;)V
    //   1084: aload 11
    //   1086: astore 15
    //   1088: aload 13
    //   1090: astore 14
    //   1092: aload_0
    //   1093: iconst_1
    //   1094: putfield 47	com/google/android/android/measurement/internal/zzal:zzalr	Z
    //   1097: aload 13
    //   1099: ifnull +10 -> 1109
    //   1102: aload 13
    //   1104: invokeinterface 151 1 0
    //   1109: iload_2
    //   1110: istore_3
    //   1111: aload 11
    //   1113: ifnull +137 -> 1250
    //   1116: aload 11
    //   1118: invokevirtual 77	android/database/sqlite/SQLiteDatabase:close	()V
    //   1121: iload_2
    //   1122: istore_3
    //   1123: goto +127 -> 1250
    //   1126: aconst_null
    //   1127: astore 11
    //   1129: aconst_null
    //   1130: astore 10
    //   1132: iload_2
    //   1133: i2l
    //   1134: lstore 5
    //   1136: lload 5
    //   1138: invokestatic 165	android/os/SystemClock:sleep	(J)V
    //   1141: iload_2
    //   1142: bipush 20
    //   1144: iadd
    //   1145: istore_2
    //   1146: aload 11
    //   1148: ifnull +10 -> 1158
    //   1151: aload 11
    //   1153: invokeinterface 151 1 0
    //   1158: iload_2
    //   1159: istore_3
    //   1160: aload 10
    //   1162: ifnull +88 -> 1250
    //   1165: aload 10
    //   1167: invokevirtual 77	android/database/sqlite/SQLiteDatabase:close	()V
    //   1170: iload_2
    //   1171: istore_3
    //   1172: goto +78 -> 1250
    //   1175: astore 12
    //   1177: goto +96 -> 1273
    //   1180: astore 14
    //   1182: aconst_null
    //   1183: astore 10
    //   1185: aconst_null
    //   1186: astore 11
    //   1188: aload 10
    //   1190: astore 12
    //   1192: aload 11
    //   1194: astore 13
    //   1196: aload_0
    //   1197: invokevirtual 102	com/google/android/android/measurement/internal/zzco:zzgo	()Lcom/google/android/android/measurement/internal/zzap;
    //   1200: invokevirtual 108	com/google/android/android/measurement/internal/zzap:zzjd	()Lcom/google/android/android/measurement/internal/zzar;
    //   1203: ldc_w 271
    //   1206: aload 14
    //   1208: invokevirtual 159	com/google/android/android/measurement/internal/zzar:append	(Ljava/lang/String;Ljava/lang/Object;)V
    //   1211: aload 10
    //   1213: astore 12
    //   1215: aload 11
    //   1217: astore 13
    //   1219: aload_0
    //   1220: iconst_1
    //   1221: putfield 47	com/google/android/android/measurement/internal/zzal:zzalr	Z
    //   1224: aload 10
    //   1226: ifnull +10 -> 1236
    //   1229: aload 10
    //   1231: invokeinterface 151 1 0
    //   1236: iload_2
    //   1237: istore_3
    //   1238: aload 11
    //   1240: ifnull +10 -> 1250
    //   1243: aload 11
    //   1245: invokevirtual 77	android/database/sqlite/SQLiteDatabase:close	()V
    //   1248: iload_2
    //   1249: istore_3
    //   1250: iload_1
    //   1251: iconst_1
    //   1252: iadd
    //   1253: istore_1
    //   1254: iload_3
    //   1255: istore_2
    //   1256: goto -1208 -> 48
    //   1259: astore 10
    //   1261: aload 12
    //   1263: astore 11
    //   1265: aload 10
    //   1267: astore 12
    //   1269: aload 13
    //   1271: astore 10
    //   1273: aload 11
    //   1275: ifnull +10 -> 1285
    //   1278: aload 11
    //   1280: invokeinterface 151 1 0
    //   1285: aload 10
    //   1287: ifnull +8 -> 1295
    //   1290: aload 10
    //   1292: invokevirtual 77	android/database/sqlite/SQLiteDatabase:close	()V
    //   1295: aload 12
    //   1297: athrow
    //   1298: aload_0
    //   1299: invokevirtual 102	com/google/android/android/measurement/internal/zzco:zzgo	()Lcom/google/android/android/measurement/internal/zzap;
    //   1302: invokevirtual 168	com/google/android/android/measurement/internal/zzap:zzjg	()Lcom/google/android/android/measurement/internal/zzar;
    //   1305: ldc_w 273
    //   1308: invokevirtual 116	com/google/android/android/measurement/internal/zzar:zzbx	(Ljava/lang/String;)V
    //   1311: aconst_null
    //   1312: areturn
    //   1313: astore 10
    //   1315: goto -189 -> 1126
    //   1318: astore 10
    //   1320: goto -1224 -> 96
    //   1323: astore 10
    //   1325: goto -359 -> 966
    //   1328: astore 11
    //   1330: goto -421 -> 909
    //   1333: astore 14
    //   1335: goto -989 -> 346
    //   1338: astore 11
    //   1340: goto -431 -> 909
    //   1343: astore 11
    //   1345: goto -436 -> 909
    //   1348: astore 14
    //   1350: goto -867 -> 483
    //   1353: astore 11
    //   1355: goto -446 -> 909
    //   1358: astore 11
    //   1360: goto -451 -> 909
    //   1363: astore 14
    //   1365: goto -714 -> 651
    //   1368: astore 11
    //   1370: goto -461 -> 909
    //   1373: astore 11
    //   1375: goto -466 -> 909
    //   1378: aconst_null
    //   1379: areturn
    //   1380: aload 17
    //   1382: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1383	0	this	zzal
    //   0	1383	1	paramInt	int
    //   47	1209	2	i	int
    //   216	1039	3	j	int
    //   824	6	4	k	int
    //   158	979	5	l1	long
    //   198	562	7	l2	long
    //   175	853	9	bool	boolean
    //   64	833	10	localObject1	Object
    //   904	15	10	localSQLiteException1	SQLiteException
    //   924	10	10	localObject2	Object
    //   938	18	10	localSQLiteException2	SQLiteException
    //   961	31	10	localObject3	Object
    //   999	1	10	localSQLiteException3	SQLiteException
    //   1052	178	10	localObject4	Object
    //   1259	7	10	localThrowable1	Throwable
    //   1271	20	10	localObject5	Object
    //   1313	1	10	localSQLiteDatabaseLockedException1	android.database.sqlite.SQLiteDatabaseLockedException
    //   1318	1	10	localSQLiteDatabaseLockedException2	android.database.sqlite.SQLiteDatabaseLockedException
    //   1323	1	10	localSQLiteDatabaseLockedException3	android.database.sqlite.SQLiteDatabaseLockedException
    //   68	1211	11	localObject6	Object
    //   1328	1	11	localSQLiteDatabaseLockedException4	android.database.sqlite.SQLiteDatabaseLockedException
    //   1338	1	11	localSQLiteDatabaseLockedException5	android.database.sqlite.SQLiteDatabaseLockedException
    //   1343	1	11	localSQLiteDatabaseLockedException6	android.database.sqlite.SQLiteDatabaseLockedException
    //   1353	1	11	localSQLiteDatabaseLockedException7	android.database.sqlite.SQLiteDatabaseLockedException
    //   1358	1	11	localSQLiteDatabaseLockedException8	android.database.sqlite.SQLiteDatabaseLockedException
    //   1368	1	11	localSQLiteDatabaseLockedException9	android.database.sqlite.SQLiteDatabaseLockedException
    //   1373	1	11	localSQLiteDatabaseLockedException10	android.database.sqlite.SQLiteDatabaseLockedException
    //   49	874	12	localObject7	Object
    //   929	1	12	localThrowable2	Throwable
    //   948	1	12	localThrowable3	Throwable
    //   953	7	12	localSQLiteException4	SQLiteException
    //   988	1	12	localThrowable4	Throwable
    //   1048	1	12	localThrowable5	Throwable
    //   1175	1	12	localThrowable6	Throwable
    //   1190	106	12	localObject8	Object
    //   60	1210	13	localObject9	Object
    //   166	707	14	localObject10	Object
    //   916	1	14	localSQLiteFullException1	android.database.sqlite.SQLiteFullException
    //   943	1	14	localSQLiteFullException2	android.database.sqlite.SQLiteFullException
    //   976	1	14	localSQLiteFullException3	android.database.sqlite.SQLiteFullException
    //   1018	73	14	localObject11	Object
    //   1180	27	14	localSQLiteFullException4	android.database.sqlite.SQLiteFullException
    //   1333	1	14	localParseException1	com.google.android.android.common.internal.safeparcel.SafeParcelReader.ParseException
    //   1348	1	14	localParseException2	com.google.android.android.common.internal.safeparcel.SafeParcelReader.ParseException
    //   1363	1	14	localParseException3	com.google.android.android.common.internal.safeparcel.SafeParcelReader.ParseException
    //   162	925	15	localObject12	Object
    //   250	211	16	localObject13	Object
    //   478	1	16	localThrowable7	Throwable
    //   510	119	16	localObject14	Object
    //   646	1	16	localThrowable8	Throwable
    //   678	125	16	localObject15	Object
    //   24	1357	17	localArrayList	java.util.ArrayList
    //   292	35	18	localZzad	zzad
    //   341	59	18	localThrowable9	Throwable
    //   418	305	18	localParcel	Parcel
    //   233	362	19	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   252	256	341	java/lang/Throwable
    //   256	271	341	java/lang/Throwable
    //   271	276	341	java/lang/Throwable
    //   276	287	341	java/lang/Throwable
    //   287	294	341	java/lang/Throwable
    //   346	358	341	java/lang/Throwable
    //   420	424	478	java/lang/Throwable
    //   424	439	478	java/lang/Throwable
    //   439	444	478	java/lang/Throwable
    //   444	455	478	java/lang/Throwable
    //   455	462	478	java/lang/Throwable
    //   483	496	478	java/lang/Throwable
    //   588	592	646	java/lang/Throwable
    //   592	607	646	java/lang/Throwable
    //   607	612	646	java/lang/Throwable
    //   612	623	646	java/lang/Throwable
    //   623	630	646	java/lang/Throwable
    //   651	664	646	java/lang/Throwable
    //   168	177	904	android/database/sqlite/SQLiteException
    //   190	200	904	android/database/sqlite/SQLiteException
    //   208	217	904	android/database/sqlite/SQLiteException
    //   225	235	904	android/database/sqlite/SQLiteException
    //   247	252	904	android/database/sqlite/SQLiteException
    //   302	307	904	android/database/sqlite/SQLiteException
    //   324	334	904	android/database/sqlite/SQLiteException
    //   366	371	904	android/database/sqlite/SQLiteException
    //   386	391	904	android/database/sqlite/SQLiteException
    //   399	402	904	android/database/sqlite/SQLiteException
    //   415	420	904	android/database/sqlite/SQLiteException
    //   470	475	904	android/database/sqlite/SQLiteException
    //   504	509	904	android/database/sqlite/SQLiteException
    //   529	539	904	android/database/sqlite/SQLiteException
    //   554	559	904	android/database/sqlite/SQLiteException
    //   567	570	904	android/database/sqlite/SQLiteException
    //   583	588	904	android/database/sqlite/SQLiteException
    //   638	643	904	android/database/sqlite/SQLiteException
    //   672	677	904	android/database/sqlite/SQLiteException
    //   697	707	904	android/database/sqlite/SQLiteException
    //   722	727	904	android/database/sqlite/SQLiteException
    //   735	738	904	android/database/sqlite/SQLiteException
    //   746	759	904	android/database/sqlite/SQLiteException
    //   774	781	904	android/database/sqlite/SQLiteException
    //   789	809	904	android/database/sqlite/SQLiteException
    //   817	826	904	android/database/sqlite/SQLiteException
    //   840	853	904	android/database/sqlite/SQLiteException
    //   861	866	904	android/database/sqlite/SQLiteException
    //   874	879	904	android/database/sqlite/SQLiteException
    //   168	177	916	android/database/sqlite/SQLiteFullException
    //   190	200	916	android/database/sqlite/SQLiteFullException
    //   208	217	916	android/database/sqlite/SQLiteFullException
    //   225	235	916	android/database/sqlite/SQLiteFullException
    //   247	252	916	android/database/sqlite/SQLiteFullException
    //   302	307	916	android/database/sqlite/SQLiteFullException
    //   324	334	916	android/database/sqlite/SQLiteFullException
    //   366	371	916	android/database/sqlite/SQLiteFullException
    //   386	391	916	android/database/sqlite/SQLiteFullException
    //   399	402	916	android/database/sqlite/SQLiteFullException
    //   415	420	916	android/database/sqlite/SQLiteFullException
    //   470	475	916	android/database/sqlite/SQLiteFullException
    //   504	509	916	android/database/sqlite/SQLiteFullException
    //   529	539	916	android/database/sqlite/SQLiteFullException
    //   554	559	916	android/database/sqlite/SQLiteFullException
    //   567	570	916	android/database/sqlite/SQLiteFullException
    //   583	588	916	android/database/sqlite/SQLiteFullException
    //   638	643	916	android/database/sqlite/SQLiteFullException
    //   672	677	916	android/database/sqlite/SQLiteFullException
    //   697	707	916	android/database/sqlite/SQLiteFullException
    //   722	727	916	android/database/sqlite/SQLiteFullException
    //   735	738	916	android/database/sqlite/SQLiteFullException
    //   746	759	916	android/database/sqlite/SQLiteFullException
    //   774	781	916	android/database/sqlite/SQLiteFullException
    //   789	809	916	android/database/sqlite/SQLiteFullException
    //   817	826	916	android/database/sqlite/SQLiteFullException
    //   840	853	916	android/database/sqlite/SQLiteFullException
    //   861	866	916	android/database/sqlite/SQLiteFullException
    //   874	879	916	android/database/sqlite/SQLiteFullException
    //   115	151	929	java/lang/Throwable
    //   115	151	938	android/database/sqlite/SQLiteException
    //   115	151	943	android/database/sqlite/SQLiteFullException
    //   99	104	948	java/lang/Throwable
    //   104	111	948	java/lang/Throwable
    //   99	104	953	android/database/sqlite/SQLiteException
    //   104	111	953	android/database/sqlite/SQLiteException
    //   99	104	976	android/database/sqlite/SQLiteFullException
    //   104	111	976	android/database/sqlite/SQLiteFullException
    //   56	62	988	java/lang/Throwable
    //   56	62	999	android/database/sqlite/SQLiteException
    //   168	177	1048	java/lang/Throwable
    //   190	200	1048	java/lang/Throwable
    //   208	217	1048	java/lang/Throwable
    //   225	235	1048	java/lang/Throwable
    //   247	252	1048	java/lang/Throwable
    //   302	307	1048	java/lang/Throwable
    //   324	334	1048	java/lang/Throwable
    //   366	371	1048	java/lang/Throwable
    //   386	391	1048	java/lang/Throwable
    //   399	402	1048	java/lang/Throwable
    //   415	420	1048	java/lang/Throwable
    //   470	475	1048	java/lang/Throwable
    //   504	509	1048	java/lang/Throwable
    //   529	539	1048	java/lang/Throwable
    //   554	559	1048	java/lang/Throwable
    //   567	570	1048	java/lang/Throwable
    //   583	588	1048	java/lang/Throwable
    //   638	643	1048	java/lang/Throwable
    //   672	677	1048	java/lang/Throwable
    //   697	707	1048	java/lang/Throwable
    //   722	727	1048	java/lang/Throwable
    //   735	738	1048	java/lang/Throwable
    //   746	759	1048	java/lang/Throwable
    //   774	781	1048	java/lang/Throwable
    //   789	809	1048	java/lang/Throwable
    //   817	826	1048	java/lang/Throwable
    //   840	853	1048	java/lang/Throwable
    //   861	866	1048	java/lang/Throwable
    //   874	879	1048	java/lang/Throwable
    //   1020	1027	1048	java/lang/Throwable
    //   1040	1045	1048	java/lang/Throwable
    //   1069	1084	1048	java/lang/Throwable
    //   1092	1097	1048	java/lang/Throwable
    //   1136	1141	1175	java/lang/Throwable
    //   56	62	1180	android/database/sqlite/SQLiteFullException
    //   79	84	1259	java/lang/Throwable
    //   1196	1211	1259	java/lang/Throwable
    //   1219	1224	1259	java/lang/Throwable
    //   56	62	1313	android/database/sqlite/SQLiteDatabaseLockedException
    //   99	104	1318	android/database/sqlite/SQLiteDatabaseLockedException
    //   104	111	1318	android/database/sqlite/SQLiteDatabaseLockedException
    //   115	151	1323	android/database/sqlite/SQLiteDatabaseLockedException
    //   168	177	1328	android/database/sqlite/SQLiteDatabaseLockedException
    //   190	200	1328	android/database/sqlite/SQLiteDatabaseLockedException
    //   208	217	1328	android/database/sqlite/SQLiteDatabaseLockedException
    //   225	235	1328	android/database/sqlite/SQLiteDatabaseLockedException
    //   247	252	1328	android/database/sqlite/SQLiteDatabaseLockedException
    //   256	271	1333	com/google/android/android/common/internal/safeparcel/SafeParcelReader$ParseException
    //   276	287	1333	com/google/android/android/common/internal/safeparcel/SafeParcelReader$ParseException
    //   302	307	1338	android/database/sqlite/SQLiteDatabaseLockedException
    //   324	334	1338	android/database/sqlite/SQLiteDatabaseLockedException
    //   366	371	1343	android/database/sqlite/SQLiteDatabaseLockedException
    //   386	391	1343	android/database/sqlite/SQLiteDatabaseLockedException
    //   399	402	1343	android/database/sqlite/SQLiteDatabaseLockedException
    //   415	420	1343	android/database/sqlite/SQLiteDatabaseLockedException
    //   424	439	1348	com/google/android/android/common/internal/safeparcel/SafeParcelReader$ParseException
    //   444	455	1348	com/google/android/android/common/internal/safeparcel/SafeParcelReader$ParseException
    //   470	475	1353	android/database/sqlite/SQLiteDatabaseLockedException
    //   504	509	1358	android/database/sqlite/SQLiteDatabaseLockedException
    //   529	539	1358	android/database/sqlite/SQLiteDatabaseLockedException
    //   554	559	1358	android/database/sqlite/SQLiteDatabaseLockedException
    //   567	570	1358	android/database/sqlite/SQLiteDatabaseLockedException
    //   583	588	1358	android/database/sqlite/SQLiteDatabaseLockedException
    //   592	607	1363	com/google/android/android/common/internal/safeparcel/SafeParcelReader$ParseException
    //   612	623	1363	com/google/android/android/common/internal/safeparcel/SafeParcelReader$ParseException
    //   638	643	1368	android/database/sqlite/SQLiteDatabaseLockedException
    //   672	677	1373	android/database/sqlite/SQLiteDatabaseLockedException
    //   697	707	1373	android/database/sqlite/SQLiteDatabaseLockedException
    //   722	727	1373	android/database/sqlite/SQLiteDatabaseLockedException
    //   735	738	1373	android/database/sqlite/SQLiteDatabaseLockedException
    //   746	759	1373	android/database/sqlite/SQLiteDatabaseLockedException
    //   774	781	1373	android/database/sqlite/SQLiteDatabaseLockedException
    //   789	809	1373	android/database/sqlite/SQLiteDatabaseLockedException
    //   817	826	1373	android/database/sqlite/SQLiteDatabaseLockedException
    //   840	853	1373	android/database/sqlite/SQLiteDatabaseLockedException
    //   861	866	1373	android/database/sqlite/SQLiteDatabaseLockedException
    //   874	879	1373	android/database/sqlite/SQLiteDatabaseLockedException
  }
  
  public final void resetAnalyticsData()
  {
    zzgb();
    zzaf();
    try
    {
      int i = getWritableDatabase().delete("messages", null, null);
      i += 0;
      if (i > 0)
      {
        zzgo().zzjl().append("Reset local analytics data. records", Integer.valueOf(i));
        return;
      }
    }
    catch (SQLiteException localSQLiteException)
    {
      zzgo().zzjd().append("Error resetting local analytics data. error", localSQLiteException);
    }
  }
  
  public final boolean rmdir(zzad paramZzad)
  {
    Parcel localParcel = Parcel.obtain();
    paramZzad.writeToParcel(localParcel, 0);
    paramZzad = localParcel.marshall();
    localParcel.recycle();
    if (paramZzad.length > 131072)
    {
      zzgo().zzjg().zzbx("Event is too long for local database. Sending event directly to service");
      return false;
    }
    return delete(0, paramZzad);
  }
  
  public final boolean rmdir(zzfh paramZzfh)
  {
    Parcel localParcel = Parcel.obtain();
    paramZzfh.writeToParcel(localParcel, 0);
    paramZzfh = localParcel.marshall();
    localParcel.recycle();
    if (paramZzfh.length > 131072)
    {
      zzgo().zzjg().zzbx("User property too long for local database. Sending directly to service");
      return false;
    }
    return delete(1, paramZzfh);
  }
  
  public final boolean updateRows(ComponentInfo paramComponentInfo)
  {
    zzgm();
    paramComponentInfo = zzfk.marshall(paramComponentInfo);
    if (paramComponentInfo.length > 131072)
    {
      zzgo().zzjg().zzbx("Conditional user property too long for local database. Sending directly to service");
      return false;
    }
    return delete(2, paramComponentInfo);
  }
  
  protected final boolean zzgt()
  {
    return false;
  }
}
