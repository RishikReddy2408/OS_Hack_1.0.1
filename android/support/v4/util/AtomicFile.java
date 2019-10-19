package android.support.v4.util;

import android.util.Log;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AtomicFile
{
  private final File mBackupName;
  private final File mBaseName;
  
  public AtomicFile(File paramFile)
  {
    mBaseName = paramFile;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramFile.getPath());
    localStringBuilder.append(".bak");
    mBackupName = new File(localStringBuilder.toString());
  }
  
  private static boolean sync(FileOutputStream paramFileOutputStream)
  {
    try
    {
      paramFileOutputStream.getFD().sync();
      return true;
    }
    catch (IOException paramFileOutputStream)
    {
      for (;;) {}
    }
    return false;
  }
  
  public void delete()
  {
    mBaseName.delete();
    mBackupName.delete();
  }
  
  public void failWrite(FileOutputStream paramFileOutputStream)
  {
    if (paramFileOutputStream != null)
    {
      sync(paramFileOutputStream);
      try
      {
        paramFileOutputStream.close();
        paramFileOutputStream = mBaseName;
        paramFileOutputStream.delete();
        paramFileOutputStream = mBackupName;
        File localFile = mBaseName;
        paramFileOutputStream.renameTo(localFile);
        return;
      }
      catch (IOException paramFileOutputStream)
      {
        Log.w("AtomicFile", "failWrite: Got exception:", paramFileOutputStream);
      }
    }
  }
  
  public void finishWrite(FileOutputStream paramFileOutputStream)
  {
    if (paramFileOutputStream != null)
    {
      sync(paramFileOutputStream);
      try
      {
        paramFileOutputStream.close();
        paramFileOutputStream = mBackupName;
        paramFileOutputStream.delete();
        return;
      }
      catch (IOException paramFileOutputStream)
      {
        Log.w("AtomicFile", "finishWrite: Got exception:", paramFileOutputStream);
      }
    }
  }
  
  public File getBaseFile()
  {
    return mBaseName;
  }
  
  public FileInputStream openRead()
    throws FileNotFoundException
  {
    if (mBackupName.exists())
    {
      mBaseName.delete();
      mBackupName.renameTo(mBaseName);
    }
    return new FileInputStream(mBaseName);
  }
  
  /* Error */
  public byte[] readFully()
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 100	android/support/v4/util/AtomicFile:openRead	()Ljava/io/FileInputStream;
    //   4: astore 7
    //   6: aload 7
    //   8: invokevirtual 104	java/io/FileInputStream:available	()I
    //   11: newarray byte
    //   13: astore 5
    //   15: iconst_0
    //   16: istore_1
    //   17: aload 5
    //   19: arraylength
    //   20: istore_2
    //   21: aload 7
    //   23: aload 5
    //   25: iload_1
    //   26: iload_2
    //   27: iload_1
    //   28: isub
    //   29: invokevirtual 108	java/io/FileInputStream:read	([BII)I
    //   32: istore_2
    //   33: iload_2
    //   34: ifgt +11 -> 45
    //   37: aload 7
    //   39: invokevirtual 109	java/io/FileInputStream:close	()V
    //   42: aload 5
    //   44: areturn
    //   45: iload_1
    //   46: iload_2
    //   47: iadd
    //   48: istore_2
    //   49: aload 7
    //   51: invokevirtual 104	java/io/FileInputStream:available	()I
    //   54: istore_3
    //   55: aload 5
    //   57: arraylength
    //   58: istore 4
    //   60: iload_2
    //   61: istore_1
    //   62: iload_3
    //   63: iload 4
    //   65: iload_2
    //   66: isub
    //   67: if_icmple -50 -> 17
    //   70: iload_3
    //   71: iload_2
    //   72: iadd
    //   73: newarray byte
    //   75: astore 6
    //   77: aload 5
    //   79: iconst_0
    //   80: aload 6
    //   82: iconst_0
    //   83: iload_2
    //   84: invokestatic 115	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
    //   87: aload 6
    //   89: astore 5
    //   91: iload_2
    //   92: istore_1
    //   93: goto -76 -> 17
    //   96: astore 5
    //   98: aload 7
    //   100: invokevirtual 109	java/io/FileInputStream:close	()V
    //   103: aload 5
    //   105: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	106	0	this	AtomicFile
    //   16	77	1	i	int
    //   20	72	2	j	int
    //   54	19	3	k	int
    //   58	9	4	m	int
    //   13	77	5	localObject	Object
    //   96	8	5	localThrowable	Throwable
    //   75	13	6	arrayOfByte	byte[]
    //   4	95	7	localFileInputStream	FileInputStream
    // Exception table:
    //   from	to	target	type
    //   6	15	96	java/lang/Throwable
    //   17	21	96	java/lang/Throwable
    //   21	33	96	java/lang/Throwable
    //   49	60	96	java/lang/Throwable
    //   70	87	96	java/lang/Throwable
  }
  
  public FileOutputStream startWrite()
    throws IOException
  {
    if (mBaseName.exists()) {
      if (!mBackupName.exists())
      {
        if (!mBaseName.renameTo(mBackupName))
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("Couldn't rename file ");
          ((StringBuilder)localObject).append(mBaseName);
          ((StringBuilder)localObject).append(" to backup file ");
          ((StringBuilder)localObject).append(mBackupName);
          Log.w("AtomicFile", ((StringBuilder)localObject).toString());
        }
      }
      else {
        mBaseName.delete();
      }
    }
    Object localObject = mBaseName;
    try
    {
      localObject = new FileOutputStream((File)localObject);
      return localObject;
    }
    catch (FileNotFoundException localFileNotFoundException1)
    {
      for (;;) {}
    }
    if (mBaseName.getParentFile().mkdirs()) {
      localObject = mBaseName;
    }
    try
    {
      localObject = new FileOutputStream((File)localObject);
      return localObject;
    }
    catch (FileNotFoundException localFileNotFoundException2)
    {
      for (;;) {}
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Couldn't create ");
    ((StringBuilder)localObject).append(mBaseName);
    throw new IOException(((StringBuilder)localObject).toString());
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Couldn't create directory ");
    ((StringBuilder)localObject).append(mBaseName);
    throw new IOException(((StringBuilder)localObject).toString());
  }
}
