package com.google.android.android.common.util;

import android.os.Process;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@KeepForSdk
public class ProcessUtils
{
  private static String zzhd;
  private static int zzhe;
  
  private ProcessUtils() {}
  
  public static String getMyProcessName()
  {
    if (zzhd == null)
    {
      if (zzhe == 0) {
        zzhe = Process.myPid();
      }
      zzhd = read(zzhe);
    }
    return zzhd;
  }
  
  private static BufferedReader open(String paramString)
    throws IOException
  {
    StrictMode.ThreadPolicy localThreadPolicy = StrictMode.allowThreadDiskReads();
    try
    {
      paramString = new BufferedReader(new FileReader(paramString));
      StrictMode.setThreadPolicy(localThreadPolicy);
      return paramString;
    }
    catch (Throwable paramString)
    {
      StrictMode.setThreadPolicy(localThreadPolicy);
      throw paramString;
    }
  }
  
  /* Error */
  private static String read(int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: iload_0
    //   3: ifgt +5 -> 8
    //   6: aconst_null
    //   7: areturn
    //   8: new 60	java/lang/StringBuilder
    //   11: dup
    //   12: bipush 25
    //   14: invokespecial 63	java/lang/StringBuilder:<init>	(I)V
    //   17: astore_1
    //   18: aload_1
    //   19: ldc 65
    //   21: invokevirtual 69	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   24: pop
    //   25: aload_1
    //   26: iload_0
    //   27: invokevirtual 72	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   30: pop
    //   31: aload_1
    //   32: ldc 74
    //   34: invokevirtual 69	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   37: pop
    //   38: aload_1
    //   39: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   42: invokestatic 79	com/google/android/android/common/util/ProcessUtils:open	(Ljava/lang/String;)Ljava/io/BufferedReader;
    //   45: astore_1
    //   46: aload_1
    //   47: astore_2
    //   48: aload_1
    //   49: invokevirtual 82	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   52: invokevirtual 87	java/lang/String:trim	()Ljava/lang/String;
    //   55: astore_3
    //   56: aload_1
    //   57: invokestatic 93	com/google/android/android/common/util/IOUtils:closeQuietly	(Ljava/io/Closeable;)V
    //   60: aload_3
    //   61: areturn
    //   62: astore_2
    //   63: goto +6 -> 69
    //   66: astore_2
    //   67: aload_3
    //   68: astore_1
    //   69: aload_1
    //   70: invokestatic 93	com/google/android/android/common/util/IOUtils:closeQuietly	(Ljava/io/Closeable;)V
    //   73: aload_2
    //   74: athrow
    //   75: aconst_null
    //   76: astore_2
    //   77: aload_2
    //   78: invokestatic 93	com/google/android/android/common/util/IOUtils:closeQuietly	(Ljava/io/Closeable;)V
    //   81: aconst_null
    //   82: areturn
    //   83: astore_1
    //   84: goto -9 -> 75
    //   87: astore_1
    //   88: goto -11 -> 77
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	91	0	paramInt	int
    //   17	53	1	localObject1	Object
    //   83	1	1	localIOException1	IOException
    //   87	1	1	localIOException2	IOException
    //   47	1	2	localObject2	Object
    //   62	1	2	localThrowable1	Throwable
    //   66	8	2	localThrowable2	Throwable
    //   76	2	2	localCloseable	java.io.Closeable
    //   1	67	3	str	String
    // Exception table:
    //   from	to	target	type
    //   48	56	62	java/lang/Throwable
    //   8	46	66	java/lang/Throwable
    //   8	46	83	java/io/IOException
    //   48	56	87	java/io/IOException
  }
}
