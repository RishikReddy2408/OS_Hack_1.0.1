package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.os.Process;
import android.support.annotation.RestrictTo;
import android.util.Log;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class TypefaceCompatUtil
{
  private static final String CACHE_FILE_PREFIX = ".font";
  private static final String PAGE_KEY = "TypefaceCompatUtil";
  
  private TypefaceCompatUtil() {}
  
  public static void closeQuietly(Closeable paramCloseable)
  {
    if (paramCloseable != null) {
      try
      {
        paramCloseable.close();
        return;
      }
      catch (IOException paramCloseable) {}
    }
  }
  
  public static ByteBuffer copyToDirectBuffer(Context paramContext, Resources paramResources, int paramInt)
  {
    paramContext = getTempFile(paramContext);
    if (paramContext == null) {
      return null;
    }
    try
    {
      boolean bool = copyToFile(paramContext, paramResources, paramInt);
      if (!bool)
      {
        paramContext.delete();
        return null;
      }
      paramResources = mmap(paramContext);
      paramContext.delete();
      return paramResources;
    }
    catch (Throwable paramResources)
    {
      paramContext.delete();
      throw paramResources;
    }
  }
  
  public static boolean copyToFile(File paramFile, Resources paramResources, int paramInt)
  {
    try
    {
      InputStream localInputStream = paramResources.openRawResource(paramInt);
      paramResources = localInputStream;
      try
      {
        boolean bool = copyToFile(paramFile, localInputStream);
        closeQuietly(localInputStream);
        return bool;
      }
      catch (Throwable paramFile) {}
      closeQuietly(paramResources);
    }
    catch (Throwable paramFile)
    {
      paramResources = null;
    }
    throw paramFile;
  }
  
  public static boolean copyToFile(File paramFile, InputStream paramInputStream)
  {
    StringBuilder localStringBuilder = null;
    Object localObject = null;
    try
    {
      paramFile = new FileOutputStream(paramFile, false);
      localObject = new byte['?'];
      try
      {
        for (;;)
        {
          int i = paramInputStream.read((byte[])localObject);
          if (i == -1) {
            break;
          }
          paramFile.write((byte[])localObject, 0, i);
        }
        closeQuietly(paramFile);
        return true;
      }
      catch (Throwable paramInputStream)
      {
        localObject = paramFile;
        paramFile = paramInputStream;
        break label120;
      }
      catch (IOException paramInputStream) {}
      localObject = paramFile;
    }
    catch (Throwable paramFile) {}catch (IOException paramInputStream)
    {
      paramFile = localStringBuilder;
    }
    localStringBuilder = new StringBuilder();
    localObject = paramFile;
    localStringBuilder.append("Error copying resource contents to temp file: ");
    localObject = paramFile;
    localStringBuilder.append(paramInputStream.getMessage());
    localObject = paramFile;
    Log.e("TypefaceCompatUtil", localStringBuilder.toString());
    closeQuietly(paramFile);
    return false;
    label120:
    closeQuietly((Closeable)localObject);
    throw paramFile;
  }
  
  public static File getTempFile(Context paramContext)
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(".font");
    ((StringBuilder)localObject).append(Process.myPid());
    ((StringBuilder)localObject).append("-");
    ((StringBuilder)localObject).append(Process.myTid());
    ((StringBuilder)localObject).append("-");
    localObject = ((StringBuilder)localObject).toString();
    int i = 0;
    while (i < 100)
    {
      File localFile = paramContext.getCacheDir();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append((String)localObject);
      localStringBuilder.append(i);
      localFile = new File(localFile, localStringBuilder.toString());
      try
      {
        boolean bool = localFile.createNewFile();
        if (bool) {
          return localFile;
        }
      }
      catch (IOException localIOException)
      {
        for (;;) {}
      }
      i += 1;
    }
    return null;
  }
  
  /* Error */
  public static ByteBuffer mmap(Context paramContext, android.os.CancellationSignal paramCancellationSignal, android.net.Uri paramUri)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 130	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   4: astore_0
    //   5: aload_0
    //   6: aload_2
    //   7: ldc -124
    //   9: aload_1
    //   10: invokevirtual 138	android/content/ContentResolver:openFileDescriptor	(Landroid/net/Uri;Ljava/lang/String;Landroid/os/CancellationSignal;)Landroid/os/ParcelFileDescriptor;
    //   13: astore_0
    //   14: aload_0
    //   15: ifnonnull +13 -> 28
    //   18: aload_0
    //   19: ifnull +128 -> 147
    //   22: aload_0
    //   23: invokevirtual 141	android/os/ParcelFileDescriptor:close	()V
    //   26: aconst_null
    //   27: areturn
    //   28: new 143	java/io/FileInputStream
    //   31: dup
    //   32: aload_0
    //   33: invokevirtual 147	android/os/ParcelFileDescriptor:getFileDescriptor	()Ljava/io/FileDescriptor;
    //   36: invokespecial 150	java/io/FileInputStream:<init>	(Ljava/io/FileDescriptor;)V
    //   39: astore_1
    //   40: aload_1
    //   41: invokevirtual 154	java/io/FileInputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   44: astore_2
    //   45: aload_2
    //   46: invokevirtual 160	java/nio/channels/FileChannel:size	()J
    //   49: lstore_3
    //   50: aload_2
    //   51: getstatic 166	java/nio/channels/FileChannel$MapMode:READ_ONLY	Ljava/nio/channels/FileChannel$MapMode;
    //   54: lconst_0
    //   55: lload_3
    //   56: invokevirtual 170	java/nio/channels/FileChannel:map	(Ljava/nio/channels/FileChannel$MapMode;JJ)Ljava/nio/MappedByteBuffer;
    //   59: astore_2
    //   60: aload_1
    //   61: invokevirtual 171	java/io/FileInputStream:close	()V
    //   64: aload_0
    //   65: ifnull +84 -> 149
    //   68: aload_0
    //   69: invokevirtual 141	android/os/ParcelFileDescriptor:close	()V
    //   72: aload_2
    //   73: areturn
    //   74: astore_2
    //   75: aload_2
    //   76: athrow
    //   77: astore 5
    //   79: aload_2
    //   80: ifnull +19 -> 99
    //   83: aload_1
    //   84: invokevirtual 171	java/io/FileInputStream:close	()V
    //   87: goto +16 -> 103
    //   90: astore_1
    //   91: aload_2
    //   92: aload_1
    //   93: invokevirtual 175	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   96: goto +7 -> 103
    //   99: aload_1
    //   100: invokevirtual 171	java/io/FileInputStream:close	()V
    //   103: aload 5
    //   105: athrow
    //   106: astore_1
    //   107: aload_1
    //   108: athrow
    //   109: astore_2
    //   110: aload_0
    //   111: ifnull +27 -> 138
    //   114: aload_1
    //   115: ifnull +19 -> 134
    //   118: aload_0
    //   119: invokevirtual 141	android/os/ParcelFileDescriptor:close	()V
    //   122: goto +16 -> 138
    //   125: astore_0
    //   126: aload_1
    //   127: aload_0
    //   128: invokevirtual 175	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   131: goto +7 -> 138
    //   134: aload_0
    //   135: invokevirtual 141	android/os/ParcelFileDescriptor:close	()V
    //   138: aload_2
    //   139: athrow
    //   140: astore_0
    //   141: aconst_null
    //   142: areturn
    //   143: astore_0
    //   144: aconst_null
    //   145: areturn
    //   146: astore_0
    //   147: aconst_null
    //   148: areturn
    //   149: aload_2
    //   150: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	151	0	paramContext	Context
    //   0	151	1	paramCancellationSignal	android.os.CancellationSignal
    //   0	151	2	paramUri	android.net.Uri
    //   49	7	3	l	long
    //   77	27	5	localThrowable	Throwable
    // Exception table:
    //   from	to	target	type
    //   40	60	74	java/lang/Throwable
    //   75	77	77	java/lang/Throwable
    //   83	87	90	java/lang/Throwable
    //   28	40	106	java/lang/Throwable
    //   60	64	106	java/lang/Throwable
    //   91	96	106	java/lang/Throwable
    //   99	103	106	java/lang/Throwable
    //   103	106	106	java/lang/Throwable
    //   107	109	109	java/lang/Throwable
    //   118	122	125	java/lang/Throwable
    //   5	14	140	java/io/IOException
    //   22	26	140	java/io/IOException
    //   68	72	143	java/io/IOException
    //   126	131	146	java/io/IOException
    //   134	138	146	java/io/IOException
    //   138	140	146	java/io/IOException
  }
  
  private static ByteBuffer mmap(File paramFile)
  {
    try
    {
      paramFile = new FileInputStream(paramFile);
      try
      {
        Object localObject = paramFile.getChannel();
        long l = ((FileChannel)localObject).size();
        localObject = ((FileChannel)localObject).map(FileChannel.MapMode.READ_ONLY, 0L, l);
        try
        {
          localThrowable1.addSuppressed(paramFile);
          break label64;
          paramFile.close();
          throw localThrowable2;
        }
        catch (IOException paramFile) {}
      }
      catch (Throwable localThrowable1)
      {
        try
        {
          paramFile.close();
          return localObject;
        }
        catch (IOException paramFile)
        {
          return null;
        }
        localThrowable1 = localThrowable1;
        try
        {
          throw localThrowable1;
        }
        catch (Throwable localThrowable2)
        {
          if (localThrowable1 != null) {
            try
            {
              paramFile.close();
            }
            catch (Throwable paramFile) {}
          }
        }
      }
      label64:
      return null;
    }
    catch (IOException paramFile)
    {
      return null;
    }
  }
}
