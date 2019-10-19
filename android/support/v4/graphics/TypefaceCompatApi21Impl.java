package android.support.v4.graphics;

import android.os.ParcelFileDescriptor;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.system.StructStat;
import java.io.File;

@RequiresApi(21)
@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
class TypefaceCompatApi21Impl
  extends TypefaceCompatBaseImpl
{
  private static final String PAGE_KEY = "TypefaceCompatApi21Impl";
  
  TypefaceCompatApi21Impl() {}
  
  private File getFile(ParcelFileDescriptor paramParcelFileDescriptor)
  {
    try
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("/proc/self/fd/");
      ((StringBuilder)localObject).append(paramParcelFileDescriptor.getFd());
      paramParcelFileDescriptor = Os.readlink(((StringBuilder)localObject).toString());
      localObject = Os.stat(paramParcelFileDescriptor);
      int i = st_mode;
      boolean bool = OsConstants.S_ISREG(i);
      if (bool)
      {
        paramParcelFileDescriptor = new File(paramParcelFileDescriptor);
        return paramParcelFileDescriptor;
      }
      return null;
    }
    catch (ErrnoException paramParcelFileDescriptor) {}
    return null;
  }
  
  /* Error */
  public android.graphics.Typeface createFromFontInfo(android.content.Context paramContext, android.os.CancellationSignal paramCancellationSignal, android.support.v4.provider.FontsContractCompat.FontInfo[] paramArrayOfFontInfo, int paramInt)
  {
    // Byte code:
    //   0: aload_3
    //   1: arraylength
    //   2: iconst_1
    //   3: if_icmpge +5 -> 8
    //   6: aconst_null
    //   7: areturn
    //   8: aload_0
    //   9: aload_3
    //   10: iload 4
    //   12: invokevirtual 82	android/support/v4/graphics/TypefaceCompatBaseImpl:findBestInfo	([Landroid/support/v4/provider/FontsContractCompat$FontInfo;I)Landroid/support/v4/provider/FontsContractCompat$FontInfo;
    //   15: astore_3
    //   16: aload_1
    //   17: invokevirtual 88	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   20: astore 6
    //   22: aload 6
    //   24: aload_3
    //   25: invokevirtual 94	android/support/v4/provider/FontsContractCompat$FontInfo:getUri	()Landroid/net/Uri;
    //   28: ldc 96
    //   30: aload_2
    //   31: invokevirtual 102	android/content/ContentResolver:openFileDescriptor	(Landroid/net/Uri;Ljava/lang/String;Landroid/os/CancellationSignal;)Landroid/os/ParcelFileDescriptor;
    //   34: astore_3
    //   35: aload_0
    //   36: aload_3
    //   37: invokespecial 104	android/support/v4/graphics/TypefaceCompatApi21Impl:getFile	(Landroid/os/ParcelFileDescriptor;)Ljava/io/File;
    //   40: astore_2
    //   41: aload_2
    //   42: ifnull +34 -> 76
    //   45: aload_2
    //   46: invokevirtual 108	java/io/File:canRead	()Z
    //   49: istore 5
    //   51: iload 5
    //   53: ifne +6 -> 59
    //   56: goto +20 -> 76
    //   59: aload_2
    //   60: invokestatic 114	android/graphics/Typeface:createFromFile	(Ljava/io/File;)Landroid/graphics/Typeface;
    //   63: astore_2
    //   64: aload_2
    //   65: astore_1
    //   66: aload_3
    //   67: ifnull +127 -> 194
    //   70: aload_3
    //   71: invokevirtual 117	android/os/ParcelFileDescriptor:close	()V
    //   74: aload_2
    //   75: areturn
    //   76: new 119	java/io/FileInputStream
    //   79: dup
    //   80: aload_3
    //   81: invokevirtual 123	android/os/ParcelFileDescriptor:getFileDescriptor	()Ljava/io/FileDescriptor;
    //   84: invokespecial 126	java/io/FileInputStream:<init>	(Ljava/io/FileDescriptor;)V
    //   87: astore 6
    //   89: aload_0
    //   90: aload_1
    //   91: aload 6
    //   93: invokespecial 130	android/support/v4/graphics/TypefaceCompatBaseImpl:createFromInputStream	(Landroid/content/Context;Ljava/io/InputStream;)Landroid/graphics/Typeface;
    //   96: astore_2
    //   97: aload 6
    //   99: invokevirtual 131	java/io/FileInputStream:close	()V
    //   102: aload_2
    //   103: astore_1
    //   104: aload_3
    //   105: ifnull +89 -> 194
    //   108: aload_3
    //   109: invokevirtual 117	android/os/ParcelFileDescriptor:close	()V
    //   112: aload_2
    //   113: areturn
    //   114: astore_1
    //   115: aload_1
    //   116: athrow
    //   117: astore_2
    //   118: aload_1
    //   119: ifnull +22 -> 141
    //   122: aload 6
    //   124: invokevirtual 131	java/io/FileInputStream:close	()V
    //   127: goto +19 -> 146
    //   130: astore 6
    //   132: aload_1
    //   133: aload 6
    //   135: invokevirtual 135	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   138: goto +8 -> 146
    //   141: aload 6
    //   143: invokevirtual 131	java/io/FileInputStream:close	()V
    //   146: aload_2
    //   147: athrow
    //   148: astore_1
    //   149: aload_1
    //   150: athrow
    //   151: astore_2
    //   152: aload_3
    //   153: ifnull +27 -> 180
    //   156: aload_1
    //   157: ifnull +19 -> 176
    //   160: aload_3
    //   161: invokevirtual 117	android/os/ParcelFileDescriptor:close	()V
    //   164: goto +16 -> 180
    //   167: astore_3
    //   168: aload_1
    //   169: aload_3
    //   170: invokevirtual 135	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   173: goto +7 -> 180
    //   176: aload_3
    //   177: invokevirtual 117	android/os/ParcelFileDescriptor:close	()V
    //   180: aload_2
    //   181: athrow
    //   182: astore_1
    //   183: aconst_null
    //   184: areturn
    //   185: astore_1
    //   186: aconst_null
    //   187: areturn
    //   188: astore_1
    //   189: aconst_null
    //   190: areturn
    //   191: astore_1
    //   192: aconst_null
    //   193: areturn
    //   194: aload_1
    //   195: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	196	0	this	TypefaceCompatApi21Impl
    //   0	196	1	paramContext	android.content.Context
    //   0	196	2	paramCancellationSignal	android.os.CancellationSignal
    //   0	196	3	paramArrayOfFontInfo	android.support.v4.provider.FontsContractCompat.FontInfo[]
    //   0	196	4	paramInt	int
    //   49	3	5	bool	boolean
    //   20	103	6	localObject	Object
    //   130	12	6	localThrowable	Throwable
    // Exception table:
    //   from	to	target	type
    //   89	97	114	java/lang/Throwable
    //   115	117	117	java/lang/Throwable
    //   122	127	130	java/lang/Throwable
    //   35	41	148	java/lang/Throwable
    //   45	51	148	java/lang/Throwable
    //   59	64	148	java/lang/Throwable
    //   76	89	148	java/lang/Throwable
    //   97	102	148	java/lang/Throwable
    //   132	138	148	java/lang/Throwable
    //   141	146	148	java/lang/Throwable
    //   146	148	148	java/lang/Throwable
    //   149	151	151	java/lang/Throwable
    //   160	164	167	java/lang/Throwable
    //   22	35	182	java/io/IOException
    //   70	74	185	java/io/IOException
    //   108	112	188	java/io/IOException
    //   168	173	191	java/io/IOException
    //   176	180	191	java/io/IOException
    //   180	182	191	java/io/IOException
  }
}
