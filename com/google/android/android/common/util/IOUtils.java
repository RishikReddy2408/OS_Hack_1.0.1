package com.google.android.android.common.util;

import android.os.ParcelFileDescriptor;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@KeepForSdk
@ShowFirstParty
public final class IOUtils
{
  private IOUtils() {}
  
  public static void closeQuietly(ParcelFileDescriptor paramParcelFileDescriptor)
  {
    if (paramParcelFileDescriptor != null) {}
    try
    {
      paramParcelFileDescriptor.close();
      return;
    }
    catch (IOException paramParcelFileDescriptor) {}
  }
  
  public static void closeQuietly(Closeable paramCloseable)
  {
    if (paramCloseable != null) {}
    try
    {
      paramCloseable.close();
      return;
    }
    catch (IOException paramCloseable) {}
  }
  
  public static long copyStream(InputStream paramInputStream, OutputStream paramOutputStream)
    throws IOException
  {
    return copyStream(paramInputStream, paramOutputStream, false);
  }
  
  private static long copyStream(InputStream paramInputStream, OutputStream paramOutputStream, boolean paramBoolean)
    throws IOException
  {
    return copyStream(paramInputStream, paramOutputStream, paramBoolean, 1024);
  }
  
  public static long copyStream(InputStream paramInputStream, OutputStream paramOutputStream, boolean paramBoolean, int paramInt)
    throws IOException
  {
    byte[] arrayOfByte = new byte[paramInt];
    long l = 0L;
    try
    {
      for (;;)
      {
        int i = paramInputStream.read(arrayOfByte, 0, paramInt);
        if (i == -1) {
          break;
        }
        l += i;
        paramOutputStream.write(arrayOfByte, 0, i);
      }
      if (paramBoolean)
      {
        closeQuietly(paramInputStream);
        closeQuietly(paramOutputStream);
        return l;
      }
    }
    catch (Throwable localThrowable)
    {
      if (paramBoolean)
      {
        closeQuietly(paramInputStream);
        closeQuietly(paramOutputStream);
      }
      throw localThrowable;
    }
    return l;
  }
  
  public static boolean isGzipByteBuffer(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte.length > 1)
    {
      int i = paramArrayOfByte[0];
      if (((paramArrayOfByte[1] & 0xFF) << 8 | i & 0xFF) == 35615) {
        return true;
      }
    }
    return false;
  }
  
  public static byte[] readInputStreamFully(InputStream paramInputStream)
    throws IOException
  {
    return readInputStreamFully(paramInputStream, true);
  }
  
  public static byte[] readInputStreamFully(InputStream paramInputStream, boolean paramBoolean)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    copyStream(paramInputStream, localByteArrayOutputStream, paramBoolean);
    return localByteArrayOutputStream.toByteArray();
  }
  
  public static byte[] toByteArray(InputStream paramInputStream)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    Preconditions.checkNotNull(paramInputStream);
    Preconditions.checkNotNull(localByteArrayOutputStream);
    byte[] arrayOfByte = new byte['?'];
    for (;;)
    {
      int i = paramInputStream.read(arrayOfByte);
      if (i == -1) {
        break;
      }
      localByteArrayOutputStream.write(arrayOfByte, 0, i);
    }
    return localByteArrayOutputStream.toByteArray();
  }
}
