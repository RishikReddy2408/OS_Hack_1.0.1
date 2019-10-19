package com.google.firebase.package_8;

import android.support.annotation.GuardedBy;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.io.IOException;
import java.util.Map;

final class zzba
{
  @GuardedBy("itself")
  private final zzaw zzaj;
  @GuardedBy("this")
  private int zzdl = 0;
  @GuardedBy("this")
  private final Map<Integer, TaskCompletionSource<Void>> zzdm = new ArrayMap();
  
  zzba(zzaw paramZzaw)
  {
    zzaj = paramZzaw;
  }
  
  /* Error */
  private final boolean processMessage(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 29	com/google/firebase/package_8/zzba:zzaj	Lcom/google/firebase/package_8/zzaw;
    //   6: astore_3
    //   7: aload_3
    //   8: monitorenter
    //   9: aload_0
    //   10: getfield 29	com/google/firebase/package_8/zzba:zzaj	Lcom/google/firebase/package_8/zzaw;
    //   13: invokevirtual 40	com/google/firebase/package_8/zzaw:zzak	()Ljava/lang/String;
    //   16: astore 4
    //   18: ldc 42
    //   20: invokestatic 48	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   23: astore_2
    //   24: aload_1
    //   25: invokestatic 48	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   28: astore 5
    //   30: aload 5
    //   32: invokevirtual 52	java/lang/String:length	()I
    //   35: ifeq +13 -> 48
    //   38: aload_2
    //   39: aload 5
    //   41: invokevirtual 56	java/lang/String:concat	(Ljava/lang/String;)Ljava/lang/String;
    //   44: astore_2
    //   45: goto +12 -> 57
    //   48: new 44	java/lang/String
    //   51: dup
    //   52: aload_2
    //   53: invokespecial 59	java/lang/String:<init>	(Ljava/lang/String;)V
    //   56: astore_2
    //   57: aload 4
    //   59: aload_2
    //   60: invokevirtual 62	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   63: ifeq +63 -> 126
    //   66: ldc 42
    //   68: invokestatic 48	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   71: astore_2
    //   72: aload_1
    //   73: invokestatic 48	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   76: astore_1
    //   77: aload_1
    //   78: invokevirtual 52	java/lang/String:length	()I
    //   81: ifeq +12 -> 93
    //   84: aload_2
    //   85: aload_1
    //   86: invokevirtual 56	java/lang/String:concat	(Ljava/lang/String;)Ljava/lang/String;
    //   89: astore_1
    //   90: goto +12 -> 102
    //   93: new 44	java/lang/String
    //   96: dup
    //   97: aload_2
    //   98: invokespecial 59	java/lang/String:<init>	(Ljava/lang/String;)V
    //   101: astore_1
    //   102: aload 4
    //   104: aload_1
    //   105: invokevirtual 52	java/lang/String:length	()I
    //   108: invokevirtual 66	java/lang/String:substring	(I)Ljava/lang/String;
    //   111: astore_1
    //   112: aload_0
    //   113: getfield 29	com/google/firebase/package_8/zzba:zzaj	Lcom/google/firebase/package_8/zzaw;
    //   116: aload_1
    //   117: invokevirtual 69	com/google/firebase/package_8/zzaw:setCurrentTheme	(Ljava/lang/String;)V
    //   120: aload_3
    //   121: monitorexit
    //   122: aload_0
    //   123: monitorexit
    //   124: iconst_1
    //   125: ireturn
    //   126: aload_3
    //   127: monitorexit
    //   128: aload_0
    //   129: monitorexit
    //   130: iconst_0
    //   131: ireturn
    //   132: astore_1
    //   133: aload_3
    //   134: monitorexit
    //   135: aload_1
    //   136: athrow
    //   137: astore_1
    //   138: aload_0
    //   139: monitorexit
    //   140: aload_1
    //   141: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	142	0	this	zzba
    //   0	142	1	paramString	String
    //   23	75	2	str1	String
    //   6	128	3	localZzaw	zzaw
    //   16	87	4	str2	String
    //   28	12	5	str3	String
    // Exception table:
    //   from	to	target	type
    //   9	45	132	java/lang/Throwable
    //   48	57	132	java/lang/Throwable
    //   57	90	132	java/lang/Throwable
    //   93	102	132	java/lang/Throwable
    //   102	122	132	java/lang/Throwable
    //   126	128	132	java/lang/Throwable
    //   133	135	132	java/lang/Throwable
    //   2	9	137	java/lang/Throwable
    //   135	137	137	java/lang/Throwable
  }
  
  private static boolean put(FirebaseInstanceId paramFirebaseInstanceId, String paramString)
  {
    Object localObject = paramString.split("!");
    if (localObject.length == 2)
    {
      paramString = localObject[0];
      localObject = localObject[1];
      int i = -1;
      try
      {
        int j = paramString.hashCode();
        boolean bool;
        if (j != 83)
        {
          if (j == 85)
          {
            bool = paramString.equals("U");
            if (bool) {
              i = 1;
            }
          }
        }
        else
        {
          bool = paramString.equals("S");
          if (bool) {
            i = 0;
          }
        }
        switch (i)
        {
        default: 
          return true;
        case 1: 
          paramFirebaseInstanceId.createNote((String)localObject);
          bool = FirebaseInstanceId.get();
          if (bool)
          {
            Log.d("FirebaseInstanceId", "unsubscribe operation succeeded");
            return true;
          }
          break;
        case 0: 
          paramFirebaseInstanceId.sign((String)localObject);
          bool = FirebaseInstanceId.get();
          if (bool)
          {
            Log.d("FirebaseInstanceId", "subscribe operation succeeded");
            return true;
          }
          break;
        }
      }
      catch (IOException paramFirebaseInstanceId)
      {
        paramFirebaseInstanceId = String.valueOf(paramFirebaseInstanceId.getMessage());
        if (paramFirebaseInstanceId.length() != 0) {
          paramFirebaseInstanceId = "Topic sync failed: ".concat(paramFirebaseInstanceId);
        } else {
          paramFirebaseInstanceId = new String("Topic sync failed: ");
        }
        Log.e("FirebaseInstanceId", paramFirebaseInstanceId);
        return false;
      }
    }
    return true;
  }
  
  private final String zzar()
  {
    Object localObject = zzaj;
    try
    {
      String str = zzaj.zzak();
      if (!TextUtils.isEmpty(str))
      {
        localObject = str.split(",");
        if ((localObject.length > 1) && (!TextUtils.isEmpty(localObject[1]))) {
          return localObject[1];
        }
      }
      return null;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  /* Error */
  final boolean doRun(FirebaseInstanceId paramFirebaseInstanceId)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial 133	com/google/firebase/package_8/zzba:zzar	()Ljava/lang/String;
    //   6: astore_2
    //   7: aload_2
    //   8: ifnonnull +21 -> 29
    //   11: invokestatic 99	com/google/firebase/package_8/FirebaseInstanceId:get	()Z
    //   14: ifeq +11 -> 25
    //   17: ldc 101
    //   19: ldc -121
    //   21: invokestatic 109	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   24: pop
    //   25: aload_0
    //   26: monitorexit
    //   27: iconst_1
    //   28: ireturn
    //   29: aload_0
    //   30: monitorexit
    //   31: aload_1
    //   32: aload_2
    //   33: invokestatic 137	com/google/firebase/package_8/zzba:put	(Lcom/google/firebase/package_8/FirebaseInstanceId;Ljava/lang/String;)Z
    //   36: ifne +5 -> 41
    //   39: iconst_0
    //   40: ireturn
    //   41: aload_0
    //   42: monitorenter
    //   43: aload_0
    //   44: getfield 27	com/google/firebase/package_8/zzba:zzdm	Ljava/util/Map;
    //   47: aload_0
    //   48: getfield 22	com/google/firebase/package_8/zzba:zzdl	I
    //   51: invokestatic 142	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   54: invokeinterface 148 2 0
    //   59: checkcast 150	com/google/android/android/tasks/TaskCompletionSource
    //   62: astore_3
    //   63: aload_0
    //   64: aload_2
    //   65: invokespecial 152	com/google/firebase/package_8/zzba:processMessage	(Ljava/lang/String;)Z
    //   68: pop
    //   69: aload_0
    //   70: aload_0
    //   71: getfield 22	com/google/firebase/package_8/zzba:zzdl	I
    //   74: iconst_1
    //   75: iadd
    //   76: putfield 22	com/google/firebase/package_8/zzba:zzdl	I
    //   79: aload_0
    //   80: monitorexit
    //   81: aload_3
    //   82: ifnull -82 -> 0
    //   85: aload_3
    //   86: aconst_null
    //   87: invokevirtual 156	com/google/android/android/tasks/TaskCompletionSource:setResult	(Ljava/lang/Object;)V
    //   90: goto -90 -> 0
    //   93: astore_1
    //   94: aload_0
    //   95: monitorexit
    //   96: aload_1
    //   97: athrow
    //   98: astore_1
    //   99: aload_0
    //   100: monitorexit
    //   101: aload_1
    //   102: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	103	0	this	zzba
    //   0	103	1	paramFirebaseInstanceId	FirebaseInstanceId
    //   6	59	2	str	String
    //   62	24	3	localTaskCompletionSource	com.google.android.android.tasks.TaskCompletionSource
    // Exception table:
    //   from	to	target	type
    //   43	81	93	java/lang/Throwable
    //   94	96	93	java/lang/Throwable
    //   2	7	98	java/lang/Throwable
    //   11	25	98	java/lang/Throwable
    //   25	27	98	java/lang/Throwable
    //   29	31	98	java/lang/Throwable
    //   99	101	98	java/lang/Throwable
  }
  
  /* Error */
  final com.google.android.android.tasks.Task execute(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: astore 6
    //   5: aload_0
    //   6: getfield 29	com/google/firebase/package_8/zzba:zzaj	Lcom/google/firebase/package_8/zzaw;
    //   9: astore 8
    //   11: aload_0
    //   12: astore 5
    //   14: aload 5
    //   16: astore 6
    //   18: aload 8
    //   20: monitorenter
    //   21: aload 5
    //   23: getfield 29	com/google/firebase/package_8/zzba:zzaj	Lcom/google/firebase/package_8/zzaw;
    //   26: invokevirtual 40	com/google/firebase/package_8/zzaw:zzak	()Ljava/lang/String;
    //   29: astore 7
    //   31: aload 5
    //   33: getfield 29	com/google/firebase/package_8/zzba:zzaj	Lcom/google/firebase/package_8/zzaw;
    //   36: astore 6
    //   38: new 160	java/lang/StringBuilder
    //   41: dup
    //   42: aload 7
    //   44: invokestatic 48	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   47: invokevirtual 52	java/lang/String:length	()I
    //   50: iconst_1
    //   51: iadd
    //   52: aload_1
    //   53: invokestatic 48	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   56: invokevirtual 52	java/lang/String:length	()I
    //   59: iadd
    //   60: invokespecial 163	java/lang/StringBuilder:<init>	(I)V
    //   63: astore 9
    //   65: aload 9
    //   67: aload 7
    //   69: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   72: pop
    //   73: aload 9
    //   75: ldc 42
    //   77: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   80: pop
    //   81: aload 9
    //   83: aload_1
    //   84: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   87: pop
    //   88: aload 6
    //   90: aload 9
    //   92: invokevirtual 170	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   95: invokevirtual 69	com/google/firebase/package_8/zzaw:setCurrentTheme	(Ljava/lang/String;)V
    //   98: aload 8
    //   100: monitorexit
    //   101: aload 5
    //   103: astore 6
    //   105: new 150	com/google/android/android/tasks/TaskCompletionSource
    //   108: dup
    //   109: invokespecial 171	com/google/android/android/tasks/TaskCompletionSource:<init>	()V
    //   112: astore_1
    //   113: aload 5
    //   115: astore 6
    //   117: aload 5
    //   119: getfield 27	com/google/firebase/package_8/zzba:zzdm	Ljava/util/Map;
    //   122: astore 8
    //   124: aload 5
    //   126: astore 6
    //   128: aload 7
    //   130: invokestatic 129	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   133: istore 4
    //   135: iload 4
    //   137: ifeq +8 -> 145
    //   140: iconst_0
    //   141: istore_2
    //   142: goto +20 -> 162
    //   145: aload 5
    //   147: astore 6
    //   149: aload 7
    //   151: ldc 42
    //   153: invokevirtual 79	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   156: arraylength
    //   157: istore_2
    //   158: iload_2
    //   159: iconst_1
    //   160: isub
    //   161: istore_2
    //   162: aload 5
    //   164: astore 6
    //   166: aload 5
    //   168: getfield 22	com/google/firebase/package_8/zzba:zzdl	I
    //   171: istore_3
    //   172: aload 5
    //   174: astore 6
    //   176: aload 8
    //   178: iload_3
    //   179: iload_2
    //   180: iadd
    //   181: invokestatic 142	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   184: aload_1
    //   185: invokeinterface 174 3 0
    //   190: pop
    //   191: aload 5
    //   193: astore 6
    //   195: aload_1
    //   196: invokevirtual 178	com/google/android/android/tasks/TaskCompletionSource:getTask	()Lcom/google/android/android/tasks/Task;
    //   199: astore_1
    //   200: aload 5
    //   202: monitorexit
    //   203: aload_1
    //   204: areturn
    //   205: astore_1
    //   206: aload 8
    //   208: monitorexit
    //   209: aload 5
    //   211: astore 6
    //   213: aload_1
    //   214: athrow
    //   215: astore_1
    //   216: aload 6
    //   218: monitorexit
    //   219: aload_1
    //   220: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	221	0	this	zzba
    //   0	221	1	paramString	String
    //   141	40	2	i	int
    //   171	10	3	j	int
    //   133	3	4	bool	boolean
    //   12	198	5	localZzba	zzba
    //   3	214	6	localObject1	Object
    //   29	121	7	str	String
    //   9	198	8	localObject2	Object
    //   63	28	9	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   21	101	205	java/lang/Throwable
    //   206	209	205	java/lang/Throwable
    //   5	11	215	java/lang/Throwable
    //   18	21	215	java/lang/Throwable
    //   105	113	215	java/lang/Throwable
    //   117	124	215	java/lang/Throwable
    //   128	135	215	java/lang/Throwable
    //   149	158	215	java/lang/Throwable
    //   166	172	215	java/lang/Throwable
    //   176	191	215	java/lang/Throwable
    //   195	200	215	java/lang/Throwable
    //   213	215	215	java/lang/Throwable
  }
  
  /* Error */
  final boolean zzaq()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial 133	com/google/firebase/package_8/zzba:zzar	()Ljava/lang/String;
    //   6: astore_2
    //   7: aload_2
    //   8: ifnull +9 -> 17
    //   11: iconst_1
    //   12: istore_1
    //   13: aload_0
    //   14: monitorexit
    //   15: iload_1
    //   16: ireturn
    //   17: iconst_0
    //   18: istore_1
    //   19: goto -6 -> 13
    //   22: astore_2
    //   23: aload_0
    //   24: monitorexit
    //   25: aload_2
    //   26: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	27	0	this	zzba
    //   12	7	1	bool	boolean
    //   6	2	2	str	String
    //   22	4	2	localThrowable	Throwable
    // Exception table:
    //   from	to	target	type
    //   2	7	22	java/lang/Throwable
  }
}
