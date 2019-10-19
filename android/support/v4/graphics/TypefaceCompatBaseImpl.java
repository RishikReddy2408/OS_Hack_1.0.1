package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.content.hack.FontResourcesParserCompat.FontFamilyFilesResourceEntry;
import android.support.v4.provider.FontsContractCompat.FontInfo;

@RequiresApi(14)
@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
class TypefaceCompatBaseImpl
  implements TypefaceCompat.TypefaceCompatImpl
{
  private static final String CACHE_FILE_PREFIX = "cached_font_";
  private static final String PAGE_KEY = "TypefaceCompatBaseImpl";
  
  TypefaceCompatBaseImpl() {}
  
  private android.support.v4.content.hack.FontResourcesParserCompat.FontFileResourceEntry findBestEntry(FontResourcesParserCompat.FontFamilyFilesResourceEntry paramFontFamilyFilesResourceEntry, int paramInt)
  {
    (android.support.v4.content.hack.FontResourcesParserCompat.FontFileResourceEntry)findBestFont(paramFontFamilyFilesResourceEntry.getEntries(), paramInt, new StyleExtractor()
    {
      public int getWeight(android.support.v4.content.hack.FontResourcesParserCompat.FontFileResourceEntry paramAnonymousFontFileResourceEntry)
      {
        return paramAnonymousFontFileResourceEntry.getWeight();
      }
      
      public boolean isItalic(android.support.v4.content.hack.FontResourcesParserCompat.FontFileResourceEntry paramAnonymousFontFileResourceEntry)
      {
        return paramAnonymousFontFileResourceEntry.isItalic();
      }
    });
  }
  
  private static Object findBestFont(Object[] paramArrayOfObject, int paramInt, StyleExtractor paramStyleExtractor)
  {
    int i;
    if ((paramInt & 0x1) == 0) {
      i = 400;
    } else {
      i = 700;
    }
    int i1;
    if ((paramInt & 0x2) != 0) {
      i1 = 1;
    } else {
      i1 = 0;
    }
    int n = paramArrayOfObject.length;
    Object localObject1 = null;
    paramInt = 0;
    int k;
    for (int j = Integer.MAX_VALUE; paramInt < n; j = k)
    {
      Object localObject2 = paramArrayOfObject[paramInt];
      int m = Math.abs(paramStyleExtractor.getWeight(localObject2) - i);
      if (paramStyleExtractor.isItalic(localObject2) == i1) {
        k = 0;
      } else {
        k = 1;
      }
      m = m * 2 + k;
      if (localObject1 != null)
      {
        k = j;
        if (j <= m) {}
      }
      else
      {
        localObject1 = localObject2;
        k = m;
      }
      paramInt += 1;
    }
    return localObject1;
  }
  
  public Typeface createFromFontFamilyFilesResourceEntry(Context paramContext, FontResourcesParserCompat.FontFamilyFilesResourceEntry paramFontFamilyFilesResourceEntry, Resources paramResources, int paramInt)
  {
    paramFontFamilyFilesResourceEntry = findBestEntry(paramFontFamilyFilesResourceEntry, paramInt);
    if (paramFontFamilyFilesResourceEntry == null) {
      return null;
    }
    return TypefaceCompat.createFromResourcesFontFile(paramContext, paramResources, paramFontFamilyFilesResourceEntry.getResourceId(), paramFontFamilyFilesResourceEntry.getFileName(), paramInt);
  }
  
  /* Error */
  public Typeface createFromFontInfo(Context paramContext, android.os.CancellationSignal paramCancellationSignal, FontsContractCompat.FontInfo[] paramArrayOfFontInfo, int paramInt)
  {
    // Byte code:
    //   0: aload_3
    //   1: arraylength
    //   2: istore 5
    //   4: aconst_null
    //   5: astore 6
    //   7: iload 5
    //   9: iconst_1
    //   10: if_icmpge +5 -> 15
    //   13: aconst_null
    //   14: areturn
    //   15: aload_0
    //   16: aload_3
    //   17: iload 4
    //   19: invokevirtual 91	android/support/v4/graphics/TypefaceCompatBaseImpl:findBestInfo	([Landroid/support/v4/provider/FontsContractCompat$FontInfo;I)Landroid/support/v4/provider/FontsContractCompat$FontInfo;
    //   22: astore_2
    //   23: aload_1
    //   24: invokevirtual 97	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   27: aload_2
    //   28: invokevirtual 103	android/support/v4/provider/FontsContractCompat$FontInfo:getUri	()Landroid/net/Uri;
    //   31: invokevirtual 109	android/content/ContentResolver:openInputStream	(Landroid/net/Uri;)Ljava/io/InputStream;
    //   34: astore_2
    //   35: aload_2
    //   36: astore_3
    //   37: aload_0
    //   38: aload_1
    //   39: aload_2
    //   40: invokevirtual 113	android/support/v4/graphics/TypefaceCompatBaseImpl:createFromInputStream	(Landroid/content/Context;Ljava/io/InputStream;)Landroid/graphics/Typeface;
    //   43: astore_1
    //   44: aload_2
    //   45: invokestatic 119	android/support/v4/graphics/TypefaceCompatUtil:closeQuietly	(Ljava/io/Closeable;)V
    //   48: aload_1
    //   49: areturn
    //   50: astore_3
    //   51: aload_2
    //   52: astore_1
    //   53: aload_3
    //   54: astore_2
    //   55: goto +7 -> 62
    //   58: astore_2
    //   59: aload 6
    //   61: astore_1
    //   62: aload_1
    //   63: invokestatic 119	android/support/v4/graphics/TypefaceCompatUtil:closeQuietly	(Ljava/io/Closeable;)V
    //   66: aload_2
    //   67: athrow
    //   68: aconst_null
    //   69: astore_3
    //   70: aload_3
    //   71: invokestatic 119	android/support/v4/graphics/TypefaceCompatUtil:closeQuietly	(Ljava/io/Closeable;)V
    //   74: aconst_null
    //   75: areturn
    //   76: astore_1
    //   77: goto -9 -> 68
    //   80: astore_1
    //   81: goto -11 -> 70
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	84	0	this	TypefaceCompatBaseImpl
    //   0	84	1	paramContext	Context
    //   0	84	2	paramCancellationSignal	android.os.CancellationSignal
    //   0	84	3	paramArrayOfFontInfo	FontsContractCompat.FontInfo[]
    //   0	84	4	paramInt	int
    //   2	9	5	i	int
    //   5	55	6	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   37	44	50	java/lang/Throwable
    //   23	35	58	java/lang/Throwable
    //   23	35	76	java/io/IOException
    //   37	44	80	java/io/IOException
  }
  
  /* Error */
  protected Typeface createFromInputStream(Context paramContext, java.io.InputStream paramInputStream)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 125	android/support/v4/graphics/TypefaceCompatUtil:getTempFile	(Landroid/content/Context;)Ljava/io/File;
    //   4: astore_1
    //   5: aload_1
    //   6: ifnonnull +5 -> 11
    //   9: aconst_null
    //   10: areturn
    //   11: aload_1
    //   12: aload_2
    //   13: invokestatic 129	android/support/v4/graphics/TypefaceCompatUtil:copyToFile	(Ljava/io/File;Ljava/io/InputStream;)Z
    //   16: istore_3
    //   17: iload_3
    //   18: ifne +10 -> 28
    //   21: aload_1
    //   22: invokevirtual 135	java/io/File:delete	()Z
    //   25: pop
    //   26: aconst_null
    //   27: areturn
    //   28: aload_1
    //   29: invokevirtual 138	java/io/File:getPath	()Ljava/lang/String;
    //   32: invokestatic 144	android/graphics/Typeface:createFromFile	(Ljava/lang/String;)Landroid/graphics/Typeface;
    //   35: astore_2
    //   36: aload_1
    //   37: invokevirtual 135	java/io/File:delete	()Z
    //   40: pop
    //   41: aload_2
    //   42: areturn
    //   43: astore_2
    //   44: aload_1
    //   45: invokevirtual 135	java/io/File:delete	()Z
    //   48: pop
    //   49: aload_2
    //   50: athrow
    //   51: aload_1
    //   52: invokevirtual 135	java/io/File:delete	()Z
    //   55: pop
    //   56: aconst_null
    //   57: areturn
    //   58: astore_2
    //   59: goto -8 -> 51
    //   62: astore_2
    //   63: goto -12 -> 51
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	66	0	this	TypefaceCompatBaseImpl
    //   0	66	1	paramContext	Context
    //   0	66	2	paramInputStream	java.io.InputStream
    //   16	2	3	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   11	17	43	java/lang/Throwable
    //   28	36	43	java/lang/Throwable
    //   11	17	58	java/lang/RuntimeException
    //   28	36	62	java/lang/RuntimeException
  }
  
  /* Error */
  public Typeface createFromResourcesFontFile(Context paramContext, Resources paramResources, int paramInt1, String paramString, int paramInt2)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 125	android/support/v4/graphics/TypefaceCompatUtil:getTempFile	(Landroid/content/Context;)Ljava/io/File;
    //   4: astore_1
    //   5: aload_1
    //   6: ifnonnull +5 -> 11
    //   9: aconst_null
    //   10: areturn
    //   11: aload_1
    //   12: aload_2
    //   13: iload_3
    //   14: invokestatic 147	android/support/v4/graphics/TypefaceCompatUtil:copyToFile	(Ljava/io/File;Landroid/content/res/Resources;I)Z
    //   17: istore 6
    //   19: iload 6
    //   21: ifne +10 -> 31
    //   24: aload_1
    //   25: invokevirtual 135	java/io/File:delete	()Z
    //   28: pop
    //   29: aconst_null
    //   30: areturn
    //   31: aload_1
    //   32: invokevirtual 138	java/io/File:getPath	()Ljava/lang/String;
    //   35: invokestatic 144	android/graphics/Typeface:createFromFile	(Ljava/lang/String;)Landroid/graphics/Typeface;
    //   38: astore_2
    //   39: aload_1
    //   40: invokevirtual 135	java/io/File:delete	()Z
    //   43: pop
    //   44: aload_2
    //   45: areturn
    //   46: astore_2
    //   47: aload_1
    //   48: invokevirtual 135	java/io/File:delete	()Z
    //   51: pop
    //   52: aload_2
    //   53: athrow
    //   54: aload_1
    //   55: invokevirtual 135	java/io/File:delete	()Z
    //   58: pop
    //   59: aconst_null
    //   60: areturn
    //   61: astore_2
    //   62: goto -8 -> 54
    //   65: astore_2
    //   66: goto -12 -> 54
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	69	0	this	TypefaceCompatBaseImpl
    //   0	69	1	paramContext	Context
    //   0	69	2	paramResources	Resources
    //   0	69	3	paramInt1	int
    //   0	69	4	paramString	String
    //   0	69	5	paramInt2	int
    //   17	3	6	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   11	19	46	java/lang/Throwable
    //   31	39	46	java/lang/Throwable
    //   11	19	61	java/lang/RuntimeException
    //   31	39	65	java/lang/RuntimeException
  }
  
  protected FontsContractCompat.FontInfo findBestInfo(FontsContractCompat.FontInfo[] paramArrayOfFontInfo, int paramInt)
  {
    (FontsContractCompat.FontInfo)findBestFont(paramArrayOfFontInfo, paramInt, new StyleExtractor()
    {
      public int getWeight(FontsContractCompat.FontInfo paramAnonymousFontInfo)
      {
        return paramAnonymousFontInfo.getWeight();
      }
      
      public boolean isItalic(FontsContractCompat.FontInfo paramAnonymousFontInfo)
      {
        return paramAnonymousFontInfo.isItalic();
      }
    });
  }
  
  private static abstract interface StyleExtractor<T>
  {
    public abstract int getWeight(Object paramObject);
    
    public abstract boolean isItalic(Object paramObject);
  }
}
