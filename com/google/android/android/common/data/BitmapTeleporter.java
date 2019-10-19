package com.google.android.android.common.data;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.ParcelFileDescriptor.AutoCloseInputStream;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.android.common.internal.ReflectedParcelable;
import com.google.android.android.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.android.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;

@KeepForSdk
@ShowFirstParty
@SafeParcelable.Class(creator="BitmapTeleporterCreator")
public class BitmapTeleporter
  extends AbstractSafeParcelable
  implements ReflectedParcelable
{
  @KeepForSdk
  public static final Parcelable.Creator<com.google.android.gms.common.data.BitmapTeleporter> CREATOR = new DiscreteSeekBar.CustomState.1();
  @SafeParcelable.Field(id=3)
  private final int mType;
  @SafeParcelable.VersionField(id=1)
  private final int zale;
  @SafeParcelable.Field(id=2)
  private ParcelFileDescriptor zalf;
  private Bitmap zalg;
  private boolean zalh;
  private File zali;
  
  BitmapTeleporter(int paramInt1, ParcelFileDescriptor paramParcelFileDescriptor, int paramInt2)
  {
    zale = paramInt1;
    zalf = paramParcelFileDescriptor;
    mType = paramInt2;
    zalg = null;
    zalh = false;
  }
  
  public BitmapTeleporter(Bitmap paramBitmap)
  {
    zale = 1;
    zalf = null;
    mType = 0;
    zalg = paramBitmap;
    zalh = true;
  }
  
  private static void closeQuietly(Closeable paramCloseable)
  {
    try
    {
      paramCloseable.close();
      return;
    }
    catch (IOException paramCloseable)
    {
      Log.w("BitmapTeleporter", "Could not close stream", paramCloseable);
    }
  }
  
  /* Error */
  private final java.io.FileOutputStream zabz()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 79	com/google/android/android/common/data/BitmapTeleporter:zali	Ljava/io/File;
    //   4: ifnull +67 -> 71
    //   7: aload_0
    //   8: getfield 79	com/google/android/android/common/data/BitmapTeleporter:zali	Ljava/io/File;
    //   11: astore_1
    //   12: ldc 81
    //   14: ldc 83
    //   16: aload_1
    //   17: invokestatic 89	java/io/File:createTempFile	(Ljava/lang/String;Ljava/lang/String;Ljava/io/File;)Ljava/io/File;
    //   20: astore_1
    //   21: new 91	java/io/FileOutputStream
    //   24: dup
    //   25: aload_1
    //   26: invokespecial 94	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   29: astore_2
    //   30: aload_1
    //   31: ldc 95
    //   33: invokestatic 101	android/os/ParcelFileDescriptor:open	(Ljava/io/File;I)Landroid/os/ParcelFileDescriptor;
    //   36: astore_3
    //   37: aload_0
    //   38: aload_3
    //   39: putfield 47	com/google/android/android/common/data/BitmapTeleporter:zalf	Landroid/os/ParcelFileDescriptor;
    //   42: aload_1
    //   43: invokevirtual 105	java/io/File:delete	()Z
    //   46: pop
    //   47: aload_2
    //   48: areturn
    //   49: new 107	java/lang/IllegalStateException
    //   52: dup
    //   53: ldc 109
    //   55: invokespecial 112	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   58: athrow
    //   59: astore_1
    //   60: new 107	java/lang/IllegalStateException
    //   63: dup
    //   64: ldc 114
    //   66: aload_1
    //   67: invokespecial 117	java/lang/IllegalStateException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   70: athrow
    //   71: new 107	java/lang/IllegalStateException
    //   74: dup
    //   75: ldc 119
    //   77: invokespecial 112	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   80: athrow
    //   81: astore_1
    //   82: goto -33 -> 49
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	85	0	this	BitmapTeleporter
    //   11	32	1	localFile	File
    //   59	8	1	localIOException	IOException
    //   81	1	1	localFileNotFoundException	java.io.FileNotFoundException
    //   29	19	2	localFileOutputStream	java.io.FileOutputStream
    //   36	3	3	localParcelFileDescriptor	ParcelFileDescriptor
    // Exception table:
    //   from	to	target	type
    //   12	21	59	java/io/IOException
    //   21	37	81	java/io/FileNotFoundException
  }
  
  public Bitmap get()
  {
    if (!zalh)
    {
      Object localObject1 = new DataInputStream(new ParcelFileDescriptor.AutoCloseInputStream(zalf));
      try
      {
        int i = ((DataInputStream)localObject1).readInt();
        byte[] arrayOfByte = new byte[i];
        i = ((DataInputStream)localObject1).readInt();
        int j = ((DataInputStream)localObject1).readInt();
        Object localObject2 = Bitmap.Config.valueOf(((DataInputStream)localObject1).readUTF());
        ((DataInputStream)localObject1).read(arrayOfByte);
        closeQuietly((Closeable)localObject1);
        localObject1 = ByteBuffer.wrap(arrayOfByte);
        localObject2 = Bitmap.createBitmap(i, j, (Bitmap.Config)localObject2);
        ((Bitmap)localObject2).copyPixelsFromBuffer((Buffer)localObject1);
        zalg = ((Bitmap)localObject2);
        zalh = true;
      }
      catch (Throwable localThrowable) {}catch (IOException localIOException)
      {
        throw new IllegalStateException("Could not read from parcel file descriptor", localIOException);
      }
      closeQuietly((Closeable)localObject1);
      throw localIOException;
    }
    return zalg;
  }
  
  public void release()
  {
    if (!zalh)
    {
      ParcelFileDescriptor localParcelFileDescriptor = zalf;
      try
      {
        localParcelFileDescriptor.close();
        return;
      }
      catch (IOException localIOException)
      {
        Log.w("BitmapTeleporter", "Could not close PFD", localIOException);
      }
    }
  }
  
  public void setTempDir(File paramFile)
  {
    if (paramFile != null)
    {
      zali = paramFile;
      return;
    }
    throw new NullPointerException("Cannot set null temp directory");
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    if (zalf == null)
    {
      Bitmap localBitmap = zalg;
      Object localObject = ByteBuffer.allocate(localBitmap.getRowBytes() * localBitmap.getHeight());
      localBitmap.copyPixelsToBuffer((Buffer)localObject);
      byte[] arrayOfByte = ((ByteBuffer)localObject).array();
      localObject = new DataOutputStream(new BufferedOutputStream(zabz()));
      i = arrayOfByte.length;
      try
      {
        ((DataOutputStream)localObject).writeInt(i);
        ((DataOutputStream)localObject).writeInt(localBitmap.getWidth());
        ((DataOutputStream)localObject).writeInt(localBitmap.getHeight());
        ((DataOutputStream)localObject).writeUTF(localBitmap.getConfig().toString());
        ((DataOutputStream)localObject).write(arrayOfByte);
        closeQuietly((Closeable)localObject);
      }
      catch (Throwable paramParcel) {}catch (IOException paramParcel)
      {
        throw new IllegalStateException("Could not write into unlinked file", paramParcel);
      }
      closeQuietly((Closeable)localObject);
      throw paramParcel;
    }
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 1, zale);
    SafeParcelWriter.writeParcelable(paramParcel, 2, zalf, paramInt | 0x1, false);
    SafeParcelWriter.writeInt(paramParcel, 3, mType);
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
    zalf = null;
  }
}
