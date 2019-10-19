package android.support.v4.print;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.CancellationSignal.OnCancelListener;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintAttributes.Builder;
import android.print.PrintAttributes.Margins;
import android.print.PrintAttributes.MediaSize;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentAdapter.LayoutResultCallback;
import android.print.PrintDocumentAdapter.WriteResultCallback;
import android.print.PrintDocumentInfo.Builder;
import android.print.PrintManager;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.io.FileNotFoundException;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class PrintHelper
{
  public static final int COLOR_MODE_COLOR = 2;
  public static final int COLOR_MODE_MONOCHROME = 1;
  public static final int ORIENTATION_LANDSCAPE = 1;
  public static final int ORIENTATION_PORTRAIT = 2;
  public static final int SCALE_MODE_FILL = 2;
  public static final int SCALE_MODE_FIT = 1;
  private final PrintHelperVersionImpl mImpl;
  
  public PrintHelper(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 24)
    {
      mImpl = new PrintHelperApi24(paramContext);
      return;
    }
    if (Build.VERSION.SDK_INT >= 23)
    {
      mImpl = new PrintHelperApi23(paramContext);
      return;
    }
    if (Build.VERSION.SDK_INT >= 20)
    {
      mImpl = new PrintHelperApi20(paramContext);
      return;
    }
    if (Build.VERSION.SDK_INT >= 19)
    {
      mImpl = new PrintHelperApi19(paramContext);
      return;
    }
    mImpl = new PrintHelperStub(null);
  }
  
  public static boolean systemSupportsPrint()
  {
    return Build.VERSION.SDK_INT >= 19;
  }
  
  public int getColorMode()
  {
    return mImpl.getColorMode();
  }
  
  public int getOrientation()
  {
    return mImpl.getOrientation();
  }
  
  public int getScaleMode()
  {
    return mImpl.getScaleMode();
  }
  
  public void printBitmap(String paramString, Bitmap paramBitmap)
  {
    mImpl.printBitmap(paramString, paramBitmap, null);
  }
  
  public void printBitmap(String paramString, Bitmap paramBitmap, OnPrintFinishCallback paramOnPrintFinishCallback)
  {
    mImpl.printBitmap(paramString, paramBitmap, paramOnPrintFinishCallback);
  }
  
  public void printBitmap(String paramString, Uri paramUri)
    throws FileNotFoundException
  {
    mImpl.printBitmap(paramString, paramUri, null);
  }
  
  public void printBitmap(String paramString, Uri paramUri, OnPrintFinishCallback paramOnPrintFinishCallback)
    throws FileNotFoundException
  {
    mImpl.printBitmap(paramString, paramUri, paramOnPrintFinishCallback);
  }
  
  public void setColorMode(int paramInt)
  {
    mImpl.setColorMode(paramInt);
  }
  
  public void setOrientation(int paramInt)
  {
    mImpl.setOrientation(paramInt);
  }
  
  public void setScaleMode(int paramInt)
  {
    mImpl.setScaleMode(paramInt);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  private static @interface ColorMode {}
  
  public static abstract interface OnPrintFinishCallback
  {
    public abstract void onFinish();
  }
  
  @Retention(RetentionPolicy.SOURCE)
  private static @interface Orientation {}
  
  @RequiresApi(19)
  private static class PrintHelperApi19
    implements PrintHelper.PrintHelperVersionImpl
  {
    private static final String LOG_TAG = "PrintHelperApi19";
    private static final int MAX_PRINT_SIZE = 3500;
    int mColorMode = 2;
    final Context mContext;
    BitmapFactory.Options mDecodeOptions = null;
    protected boolean mIsMinMarginsHandlingCorrect = true;
    private final Object mLock = new Object();
    int mOrientation;
    protected boolean mPrintActivityRespectsOrientation = true;
    int mScaleMode = 2;
    
    PrintHelperApi19(Context paramContext)
    {
      mContext = paramContext;
    }
    
    private Bitmap convertBitmapForColorMode(Bitmap paramBitmap, int paramInt)
    {
      if (paramInt != 1) {
        return paramBitmap;
      }
      Bitmap localBitmap = Bitmap.createBitmap(paramBitmap.getWidth(), paramBitmap.getHeight(), Bitmap.Config.ARGB_8888);
      Canvas localCanvas = new Canvas(localBitmap);
      Paint localPaint = new Paint();
      ColorMatrix localColorMatrix = new ColorMatrix();
      localColorMatrix.setSaturation(0.0F);
      localPaint.setColorFilter(new ColorMatrixColorFilter(localColorMatrix));
      localCanvas.drawBitmap(paramBitmap, 0.0F, 0.0F, localPaint);
      localCanvas.setBitmap(null);
      return localBitmap;
    }
    
    private Matrix getMatrix(int paramInt1, int paramInt2, RectF paramRectF, int paramInt3)
    {
      Matrix localMatrix = new Matrix();
      float f1 = paramRectF.width();
      float f2 = paramInt1;
      f1 /= f2;
      if (paramInt3 == 2) {
        f1 = Math.max(f1, paramRectF.height() / paramInt2);
      } else {
        f1 = Math.min(f1, paramRectF.height() / paramInt2);
      }
      localMatrix.postScale(f1, f1);
      localMatrix.postTranslate((paramRectF.width() - f2 * f1) / 2.0F, (paramRectF.height() - paramInt2 * f1) / 2.0F);
      return localMatrix;
    }
    
    private static boolean isPortrait(Bitmap paramBitmap)
    {
      return paramBitmap.getWidth() <= paramBitmap.getHeight();
    }
    
    /* Error */
    private Bitmap loadBitmap(Uri paramUri, BitmapFactory.Options paramOptions)
      throws FileNotFoundException
    {
      // Byte code:
      //   0: aload_1
      //   1: ifnull +87 -> 88
      //   4: aload_0
      //   5: getfield 59	android/support/v4/print/PrintHelper$PrintHelperApi19:mContext	Landroid/content/Context;
      //   8: ifnull +80 -> 88
      //   11: aconst_null
      //   12: astore_3
      //   13: aload_0
      //   14: getfield 59	android/support/v4/print/PrintHelper$PrintHelperApi19:mContext	Landroid/content/Context;
      //   17: invokevirtual 184	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
      //   20: aload_1
      //   21: invokevirtual 190	android/content/ContentResolver:openInputStream	(Landroid/net/Uri;)Ljava/io/InputStream;
      //   24: astore_1
      //   25: aload_1
      //   26: aconst_null
      //   27: aload_2
      //   28: invokestatic 196	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
      //   31: astore_2
      //   32: aload_1
      //   33: ifnull +65 -> 98
      //   36: aload_1
      //   37: invokevirtual 201	java/io/InputStream:close	()V
      //   40: aload_2
      //   41: areturn
      //   42: astore_1
      //   43: ldc 25
      //   45: ldc -53
      //   47: aload_1
      //   48: invokestatic 209	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   51: pop
      //   52: aload_2
      //   53: areturn
      //   54: astore_3
      //   55: aload_1
      //   56: astore_2
      //   57: aload_3
      //   58: astore_1
      //   59: goto +6 -> 65
      //   62: astore_1
      //   63: aload_3
      //   64: astore_2
      //   65: aload_2
      //   66: ifnull +20 -> 86
      //   69: aload_2
      //   70: invokevirtual 201	java/io/InputStream:close	()V
      //   73: goto +13 -> 86
      //   76: astore_2
      //   77: ldc 25
      //   79: ldc -53
      //   81: aload_2
      //   82: invokestatic 209	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   85: pop
      //   86: aload_1
      //   87: athrow
      //   88: new 211	java/lang/IllegalArgumentException
      //   91: dup
      //   92: ldc -43
      //   94: invokespecial 216	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
      //   97: athrow
      //   98: aload_2
      //   99: areturn
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	100	0	this	PrintHelperApi19
      //   0	100	1	paramUri	Uri
      //   0	100	2	paramOptions	BitmapFactory.Options
      //   12	1	3	localObject	Object
      //   54	10	3	localThrowable	Throwable
      // Exception table:
      //   from	to	target	type
      //   36	40	42	java/io/IOException
      //   25	32	54	java/lang/Throwable
      //   13	25	62	java/lang/Throwable
      //   69	73	76	java/io/IOException
    }
    
    /* Error */
    private Bitmap loadConstrainedBitmap(Uri paramUri)
      throws FileNotFoundException
    {
      // Byte code:
      //   0: aload_1
      //   1: ifnull +217 -> 218
      //   4: aload_0
      //   5: getfield 59	android/support/v4/print/PrintHelper$PrintHelperApi19:mContext	Landroid/content/Context;
      //   8: ifnull +210 -> 218
      //   11: new 218	android/graphics/BitmapFactory$Options
      //   14: dup
      //   15: invokespecial 219	android/graphics/BitmapFactory$Options:<init>	()V
      //   18: astore 6
      //   20: aload 6
      //   22: iconst_1
      //   23: putfield 222	android/graphics/BitmapFactory$Options:inJustDecodeBounds	Z
      //   26: aload_0
      //   27: aload_1
      //   28: aload 6
      //   30: invokespecial 224	android/support/v4/print/PrintHelper$PrintHelperApi19:loadBitmap	(Landroid/net/Uri;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
      //   33: pop
      //   34: aload 6
      //   36: getfield 227	android/graphics/BitmapFactory$Options:outWidth	I
      //   39: istore 4
      //   41: aload 6
      //   43: getfield 230	android/graphics/BitmapFactory$Options:outHeight	I
      //   46: istore 5
      //   48: iload 4
      //   50: ifle +166 -> 216
      //   53: iload 5
      //   55: ifgt +5 -> 60
      //   58: aconst_null
      //   59: areturn
      //   60: iload 4
      //   62: iload 5
      //   64: invokestatic 233	java/lang/Math:max	(II)I
      //   67: istore_3
      //   68: iconst_1
      //   69: istore_2
      //   70: iload_3
      //   71: sipush 3500
      //   74: if_icmple +14 -> 88
      //   77: iload_3
      //   78: iconst_1
      //   79: iushr
      //   80: istore_3
      //   81: iload_2
      //   82: iconst_1
      //   83: ishl
      //   84: istore_2
      //   85: goto -15 -> 70
      //   88: iload_2
      //   89: ifle +139 -> 228
      //   92: iload 4
      //   94: iload 5
      //   96: invokestatic 235	java/lang/Math:min	(II)I
      //   99: iload_2
      //   100: idiv
      //   101: ifgt +5 -> 106
      //   104: aconst_null
      //   105: areturn
      //   106: aload_0
      //   107: getfield 49	android/support/v4/print/PrintHelper$PrintHelperApi19:mLock	Ljava/lang/Object;
      //   110: astore 6
      //   112: aload 6
      //   114: monitorenter
      //   115: aload_0
      //   116: new 218	android/graphics/BitmapFactory$Options
      //   119: dup
      //   120: invokespecial 219	android/graphics/BitmapFactory$Options:<init>	()V
      //   123: putfield 47	android/support/v4/print/PrintHelper$PrintHelperApi19:mDecodeOptions	Landroid/graphics/BitmapFactory$Options;
      //   126: aload_0
      //   127: getfield 47	android/support/v4/print/PrintHelper$PrintHelperApi19:mDecodeOptions	Landroid/graphics/BitmapFactory$Options;
      //   130: iconst_1
      //   131: putfield 238	android/graphics/BitmapFactory$Options:inMutable	Z
      //   134: aload_0
      //   135: getfield 47	android/support/v4/print/PrintHelper$PrintHelperApi19:mDecodeOptions	Landroid/graphics/BitmapFactory$Options;
      //   138: iload_2
      //   139: putfield 241	android/graphics/BitmapFactory$Options:inSampleSize	I
      //   142: aload_0
      //   143: getfield 47	android/support/v4/print/PrintHelper$PrintHelperApi19:mDecodeOptions	Landroid/graphics/BitmapFactory$Options;
      //   146: astore 7
      //   148: aload 6
      //   150: monitorexit
      //   151: aload_0
      //   152: aload_1
      //   153: aload 7
      //   155: invokespecial 224	android/support/v4/print/PrintHelper$PrintHelperApi19:loadBitmap	(Landroid/net/Uri;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
      //   158: astore 6
      //   160: aload_0
      //   161: getfield 49	android/support/v4/print/PrintHelper$PrintHelperApi19:mLock	Ljava/lang/Object;
      //   164: astore_1
      //   165: aload_1
      //   166: monitorenter
      //   167: aload_0
      //   168: aconst_null
      //   169: putfield 47	android/support/v4/print/PrintHelper$PrintHelperApi19:mDecodeOptions	Landroid/graphics/BitmapFactory$Options;
      //   172: aload_1
      //   173: monitorexit
      //   174: aload 6
      //   176: areturn
      //   177: astore 6
      //   179: aload_1
      //   180: monitorexit
      //   181: aload 6
      //   183: athrow
      //   184: astore 6
      //   186: aload_0
      //   187: getfield 49	android/support/v4/print/PrintHelper$PrintHelperApi19:mLock	Ljava/lang/Object;
      //   190: astore_1
      //   191: aload_1
      //   192: monitorenter
      //   193: aload_0
      //   194: aconst_null
      //   195: putfield 47	android/support/v4/print/PrintHelper$PrintHelperApi19:mDecodeOptions	Landroid/graphics/BitmapFactory$Options;
      //   198: aload_1
      //   199: monitorexit
      //   200: aload 6
      //   202: athrow
      //   203: astore 6
      //   205: aload_1
      //   206: monitorexit
      //   207: aload 6
      //   209: athrow
      //   210: astore_1
      //   211: aload 6
      //   213: monitorexit
      //   214: aload_1
      //   215: athrow
      //   216: aconst_null
      //   217: areturn
      //   218: new 211	java/lang/IllegalArgumentException
      //   221: dup
      //   222: ldc -13
      //   224: invokespecial 216	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
      //   227: athrow
      //   228: aconst_null
      //   229: areturn
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	230	0	this	PrintHelperApi19
      //   0	230	1	paramUri	Uri
      //   69	70	2	i	int
      //   67	14	3	j	int
      //   39	54	4	k	int
      //   46	49	5	m	int
      //   18	157	6	localObject	Object
      //   177	5	6	localThrowable1	Throwable
      //   184	17	6	localThrowable2	Throwable
      //   203	9	6	localThrowable3	Throwable
      //   146	8	7	localOptions	BitmapFactory.Options
      // Exception table:
      //   from	to	target	type
      //   167	174	177	java/lang/Throwable
      //   179	181	177	java/lang/Throwable
      //   151	160	184	java/lang/Throwable
      //   193	200	203	java/lang/Throwable
      //   205	207	203	java/lang/Throwable
      //   115	151	210	java/lang/Throwable
      //   211	214	210	java/lang/Throwable
    }
    
    private void writeBitmap(final PrintAttributes paramPrintAttributes, final int paramInt, final Bitmap paramBitmap, final ParcelFileDescriptor paramParcelFileDescriptor, final CancellationSignal paramCancellationSignal, final PrintDocumentAdapter.WriteResultCallback paramWriteResultCallback)
    {
      final PrintAttributes localPrintAttributes;
      if (mIsMinMarginsHandlingCorrect) {
        localPrintAttributes = paramPrintAttributes;
      } else {
        localPrintAttributes = copyAttributes(paramPrintAttributes).setMinMargins(new PrintAttributes.Margins(0, 0, 0, 0)).build();
      }
      new AsyncTask()
      {
        /* Error */
        protected Throwable doInBackground(Void... paramAnonymousVarArgs)
        {
          // Byte code:
          //   0: aload_0
          //   1: getfield 34	android/support/v4/print/PrintHelper$PrintHelperApi19$2:val$cancellationSignal	Landroid/os/CancellationSignal;
          //   4: invokevirtual 67	android/os/CancellationSignal:isCanceled	()Z
          //   7: istore_2
          //   8: iload_2
          //   9: ifeq +5 -> 14
          //   12: aconst_null
          //   13: areturn
          //   14: new 69	android/print/pdf/PrintedPdfDocument
          //   17: dup
          //   18: aload_0
          //   19: getfield 32	android/support/v4/print/PrintHelper$PrintHelperApi19$2:this$0	Landroid/support/v4/print/PrintHelper$PrintHelperApi19;
          //   22: getfield 73	android/support/v4/print/PrintHelper$PrintHelperApi19:mContext	Landroid/content/Context;
          //   25: aload_0
          //   26: getfield 36	android/support/v4/print/PrintHelper$PrintHelperApi19$2:val$pdfAttributes	Landroid/print/PrintAttributes;
          //   29: invokespecial 76	android/print/pdf/PrintedPdfDocument:<init>	(Landroid/content/Context;Landroid/print/PrintAttributes;)V
          //   32: astore 4
          //   34: aload_0
          //   35: getfield 32	android/support/v4/print/PrintHelper$PrintHelperApi19$2:this$0	Landroid/support/v4/print/PrintHelper$PrintHelperApi19;
          //   38: aload_0
          //   39: getfield 38	android/support/v4/print/PrintHelper$PrintHelperApi19$2:val$bitmap	Landroid/graphics/Bitmap;
          //   42: aload_0
          //   43: getfield 36	android/support/v4/print/PrintHelper$PrintHelperApi19$2:val$pdfAttributes	Landroid/print/PrintAttributes;
          //   46: invokevirtual 82	android/print/PrintAttributes:getColorMode	()I
          //   49: invokestatic 86	android/support/v4/print/PrintHelper$PrintHelperApi19:access$100	(Landroid/support/v4/print/PrintHelper$PrintHelperApi19;Landroid/graphics/Bitmap;I)Landroid/graphics/Bitmap;
          //   52: astore_3
          //   53: aload_0
          //   54: getfield 34	android/support/v4/print/PrintHelper$PrintHelperApi19$2:val$cancellationSignal	Landroid/os/CancellationSignal;
          //   57: invokevirtual 67	android/os/CancellationSignal:isCanceled	()Z
          //   60: istore_2
          //   61: iload_2
          //   62: ifeq +5 -> 67
          //   65: aconst_null
          //   66: areturn
          //   67: aload 4
          //   69: iconst_1
          //   70: invokevirtual 90	android/print/pdf/PrintedPdfDocument:startPage	(I)Landroid/graphics/pdf/PdfDocument$Page;
          //   73: astore 5
          //   75: aload_0
          //   76: getfield 32	android/support/v4/print/PrintHelper$PrintHelperApi19$2:this$0	Landroid/support/v4/print/PrintHelper$PrintHelperApi19;
          //   79: getfield 94	android/support/v4/print/PrintHelper$PrintHelperApi19:mIsMinMarginsHandlingCorrect	Z
          //   82: istore_2
          //   83: iload_2
          //   84: ifeq +22 -> 106
          //   87: new 96	android/graphics/RectF
          //   90: dup
          //   91: aload 5
          //   93: invokevirtual 102	android/graphics/pdf/PdfDocument$Page:getInfo	()Landroid/graphics/pdf/PdfDocument$PageInfo;
          //   96: invokevirtual 108	android/graphics/pdf/PdfDocument$PageInfo:getContentRect	()Landroid/graphics/Rect;
          //   99: invokespecial 111	android/graphics/RectF:<init>	(Landroid/graphics/Rect;)V
          //   102: astore_1
          //   103: goto +59 -> 162
          //   106: new 69	android/print/pdf/PrintedPdfDocument
          //   109: dup
          //   110: aload_0
          //   111: getfield 32	android/support/v4/print/PrintHelper$PrintHelperApi19$2:this$0	Landroid/support/v4/print/PrintHelper$PrintHelperApi19;
          //   114: getfield 73	android/support/v4/print/PrintHelper$PrintHelperApi19:mContext	Landroid/content/Context;
          //   117: aload_0
          //   118: getfield 40	android/support/v4/print/PrintHelper$PrintHelperApi19$2:val$attributes	Landroid/print/PrintAttributes;
          //   121: invokespecial 76	android/print/pdf/PrintedPdfDocument:<init>	(Landroid/content/Context;Landroid/print/PrintAttributes;)V
          //   124: astore 6
          //   126: aload 6
          //   128: iconst_1
          //   129: invokevirtual 90	android/print/pdf/PrintedPdfDocument:startPage	(I)Landroid/graphics/pdf/PdfDocument$Page;
          //   132: astore 7
          //   134: new 96	android/graphics/RectF
          //   137: dup
          //   138: aload 7
          //   140: invokevirtual 102	android/graphics/pdf/PdfDocument$Page:getInfo	()Landroid/graphics/pdf/PdfDocument$PageInfo;
          //   143: invokevirtual 108	android/graphics/pdf/PdfDocument$PageInfo:getContentRect	()Landroid/graphics/Rect;
          //   146: invokespecial 111	android/graphics/RectF:<init>	(Landroid/graphics/Rect;)V
          //   149: astore_1
          //   150: aload 6
          //   152: aload 7
          //   154: invokevirtual 115	android/print/pdf/PrintedPdfDocument:finishPage	(Landroid/graphics/pdf/PdfDocument$Page;)V
          //   157: aload 6
          //   159: invokevirtual 118	android/print/pdf/PrintedPdfDocument:close	()V
          //   162: aload_0
          //   163: getfield 32	android/support/v4/print/PrintHelper$PrintHelperApi19$2:this$0	Landroid/support/v4/print/PrintHelper$PrintHelperApi19;
          //   166: aload_3
          //   167: invokevirtual 123	android/graphics/Bitmap:getWidth	()I
          //   170: aload_3
          //   171: invokevirtual 126	android/graphics/Bitmap:getHeight	()I
          //   174: aload_1
          //   175: aload_0
          //   176: getfield 42	android/support/v4/print/PrintHelper$PrintHelperApi19$2:val$fittingMode	I
          //   179: invokestatic 130	android/support/v4/print/PrintHelper$PrintHelperApi19:access$200	(Landroid/support/v4/print/PrintHelper$PrintHelperApi19;IILandroid/graphics/RectF;I)Landroid/graphics/Matrix;
          //   182: astore 6
          //   184: aload_0
          //   185: getfield 32	android/support/v4/print/PrintHelper$PrintHelperApi19$2:this$0	Landroid/support/v4/print/PrintHelper$PrintHelperApi19;
          //   188: getfield 94	android/support/v4/print/PrintHelper$PrintHelperApi19:mIsMinMarginsHandlingCorrect	Z
          //   191: istore_2
          //   192: iload_2
          //   193: ifeq +6 -> 199
          //   196: goto +27 -> 223
          //   199: aload 6
          //   201: aload_1
          //   202: getfield 134	android/graphics/RectF:left	F
          //   205: aload_1
          //   206: getfield 137	android/graphics/RectF:top	F
          //   209: invokevirtual 143	android/graphics/Matrix:postTranslate	(FF)Z
          //   212: pop
          //   213: aload 5
          //   215: invokevirtual 147	android/graphics/pdf/PdfDocument$Page:getCanvas	()Landroid/graphics/Canvas;
          //   218: aload_1
          //   219: invokevirtual 153	android/graphics/Canvas:clipRect	(Landroid/graphics/RectF;)Z
          //   222: pop
          //   223: aload 5
          //   225: invokevirtual 147	android/graphics/pdf/PdfDocument$Page:getCanvas	()Landroid/graphics/Canvas;
          //   228: aload_3
          //   229: aload 6
          //   231: aconst_null
          //   232: invokevirtual 157	android/graphics/Canvas:drawBitmap	(Landroid/graphics/Bitmap;Landroid/graphics/Matrix;Landroid/graphics/Paint;)V
          //   235: aload 4
          //   237: aload 5
          //   239: invokevirtual 115	android/print/pdf/PrintedPdfDocument:finishPage	(Landroid/graphics/pdf/PdfDocument$Page;)V
          //   242: aload_0
          //   243: getfield 34	android/support/v4/print/PrintHelper$PrintHelperApi19$2:val$cancellationSignal	Landroid/os/CancellationSignal;
          //   246: invokevirtual 67	android/os/CancellationSignal:isCanceled	()Z
          //   249: istore_2
          //   250: iload_2
          //   251: ifeq +42 -> 293
          //   254: aload 4
          //   256: invokevirtual 118	android/print/pdf/PrintedPdfDocument:close	()V
          //   259: aload_0
          //   260: getfield 44	android/support/v4/print/PrintHelper$PrintHelperApi19$2:val$fileDescriptor	Landroid/os/ParcelFileDescriptor;
          //   263: astore_1
          //   264: aload_1
          //   265: ifnull +12 -> 277
          //   268: aload_0
          //   269: getfield 44	android/support/v4/print/PrintHelper$PrintHelperApi19$2:val$fileDescriptor	Landroid/os/ParcelFileDescriptor;
          //   272: astore_1
          //   273: aload_1
          //   274: invokevirtual 160	android/os/ParcelFileDescriptor:close	()V
          //   277: aload_0
          //   278: getfield 38	android/support/v4/print/PrintHelper$PrintHelperApi19$2:val$bitmap	Landroid/graphics/Bitmap;
          //   281: astore_1
          //   282: aload_3
          //   283: aload_1
          //   284: if_acmpeq +129 -> 413
          //   287: aload_3
          //   288: invokevirtual 163	android/graphics/Bitmap:recycle	()V
          //   291: aconst_null
          //   292: areturn
          //   293: aload 4
          //   295: new 165	java/io/FileOutputStream
          //   298: dup
          //   299: aload_0
          //   300: getfield 44	android/support/v4/print/PrintHelper$PrintHelperApi19$2:val$fileDescriptor	Landroid/os/ParcelFileDescriptor;
          //   303: invokevirtual 169	android/os/ParcelFileDescriptor:getFileDescriptor	()Ljava/io/FileDescriptor;
          //   306: invokespecial 172	java/io/FileOutputStream:<init>	(Ljava/io/FileDescriptor;)V
          //   309: invokevirtual 176	android/print/pdf/PrintedPdfDocument:writeTo	(Ljava/io/OutputStream;)V
          //   312: aload 4
          //   314: invokevirtual 118	android/print/pdf/PrintedPdfDocument:close	()V
          //   317: aload_0
          //   318: getfield 44	android/support/v4/print/PrintHelper$PrintHelperApi19$2:val$fileDescriptor	Landroid/os/ParcelFileDescriptor;
          //   321: astore_1
          //   322: aload_1
          //   323: ifnull +12 -> 335
          //   326: aload_0
          //   327: getfield 44	android/support/v4/print/PrintHelper$PrintHelperApi19$2:val$fileDescriptor	Landroid/os/ParcelFileDescriptor;
          //   330: astore_1
          //   331: aload_1
          //   332: invokevirtual 160	android/os/ParcelFileDescriptor:close	()V
          //   335: aload_0
          //   336: getfield 38	android/support/v4/print/PrintHelper$PrintHelperApi19$2:val$bitmap	Landroid/graphics/Bitmap;
          //   339: astore_1
          //   340: aload_3
          //   341: aload_1
          //   342: if_acmpeq +71 -> 413
          //   345: aload_3
          //   346: invokevirtual 163	android/graphics/Bitmap:recycle	()V
          //   349: aconst_null
          //   350: areturn
          //   351: astore_1
          //   352: aload 4
          //   354: invokevirtual 118	android/print/pdf/PrintedPdfDocument:close	()V
          //   357: aload_0
          //   358: getfield 44	android/support/v4/print/PrintHelper$PrintHelperApi19$2:val$fileDescriptor	Landroid/os/ParcelFileDescriptor;
          //   361: astore 4
          //   363: aload 4
          //   365: ifnull +14 -> 379
          //   368: aload_0
          //   369: getfield 44	android/support/v4/print/PrintHelper$PrintHelperApi19$2:val$fileDescriptor	Landroid/os/ParcelFileDescriptor;
          //   372: astore 4
          //   374: aload 4
          //   376: invokevirtual 160	android/os/ParcelFileDescriptor:close	()V
          //   379: aload_0
          //   380: getfield 38	android/support/v4/print/PrintHelper$PrintHelperApi19$2:val$bitmap	Landroid/graphics/Bitmap;
          //   383: astore 4
          //   385: aload_3
          //   386: aload 4
          //   388: if_acmpeq +7 -> 395
          //   391: aload_3
          //   392: invokevirtual 163	android/graphics/Bitmap:recycle	()V
          //   395: aload_1
          //   396: athrow
          //   397: astore_1
          //   398: aload_1
          //   399: areturn
          //   400: astore_1
          //   401: goto -124 -> 277
          //   404: astore_1
          //   405: goto -70 -> 335
          //   408: astore 4
          //   410: goto -31 -> 379
          //   413: aconst_null
          //   414: areturn
          // Local variable table:
          //   start	length	slot	name	signature
          //   0	415	0	this	2
          //   0	415	1	paramAnonymousVarArgs	Void[]
          //   7	244	2	bool	boolean
          //   52	340	3	localBitmap	Bitmap
          //   32	355	4	localObject1	Object
          //   408	1	4	localIOException	java.io.IOException
          //   73	165	5	localPage1	android.graphics.pdf.PdfDocument.Page
          //   124	106	6	localObject2	Object
          //   132	21	7	localPage2	android.graphics.pdf.PdfDocument.Page
          // Exception table:
          //   from	to	target	type
          //   67	83	351	java/lang/Throwable
          //   87	103	351	java/lang/Throwable
          //   106	162	351	java/lang/Throwable
          //   162	192	351	java/lang/Throwable
          //   199	223	351	java/lang/Throwable
          //   223	250	351	java/lang/Throwable
          //   293	312	351	java/lang/Throwable
          //   0	8	397	java/lang/Throwable
          //   14	61	397	java/lang/Throwable
          //   254	264	397	java/lang/Throwable
          //   273	277	397	java/lang/Throwable
          //   277	282	397	java/lang/Throwable
          //   287	291	397	java/lang/Throwable
          //   312	322	397	java/lang/Throwable
          //   331	335	397	java/lang/Throwable
          //   335	340	397	java/lang/Throwable
          //   345	349	397	java/lang/Throwable
          //   352	363	397	java/lang/Throwable
          //   374	379	397	java/lang/Throwable
          //   379	385	397	java/lang/Throwable
          //   391	395	397	java/lang/Throwable
          //   395	397	397	java/lang/Throwable
          //   273	277	400	java/io/IOException
          //   331	335	404	java/io/IOException
          //   374	379	408	java/io/IOException
        }
        
        protected void onPostExecute(Throwable paramAnonymousThrowable)
        {
          if (paramCancellationSignal.isCanceled())
          {
            paramWriteResultCallback.onWriteCancelled();
            return;
          }
          if (paramAnonymousThrowable == null)
          {
            paramWriteResultCallback.onWriteFinished(new PageRange[] { PageRange.ALL_PAGES });
            return;
          }
          Log.e("PrintHelperApi19", "Error writing printed content", paramAnonymousThrowable);
          paramWriteResultCallback.onWriteFailed(null);
        }
      }.execute(new Void[0]);
    }
    
    protected PrintAttributes.Builder copyAttributes(PrintAttributes paramPrintAttributes)
    {
      PrintAttributes.Builder localBuilder = new PrintAttributes.Builder().setMediaSize(paramPrintAttributes.getMediaSize()).setResolution(paramPrintAttributes.getResolution()).setMinMargins(paramPrintAttributes.getMinMargins());
      if (paramPrintAttributes.getColorMode() != 0) {
        localBuilder.setColorMode(paramPrintAttributes.getColorMode());
      }
      return localBuilder;
    }
    
    public int getColorMode()
    {
      return mColorMode;
    }
    
    public int getOrientation()
    {
      if (mOrientation == 0) {
        return 1;
      }
      return mOrientation;
    }
    
    public int getScaleMode()
    {
      return mScaleMode;
    }
    
    public void printBitmap(final String paramString, final Bitmap paramBitmap, final PrintHelper.OnPrintFinishCallback paramOnPrintFinishCallback)
    {
      if (paramBitmap == null) {
        return;
      }
      final int i = mScaleMode;
      PrintManager localPrintManager = (PrintManager)mContext.getSystemService("print");
      if (isPortrait(paramBitmap)) {
        localObject = PrintAttributes.MediaSize.UNKNOWN_PORTRAIT;
      } else {
        localObject = PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE;
      }
      Object localObject = new PrintAttributes.Builder().setMediaSize((PrintAttributes.MediaSize)localObject).setColorMode(mColorMode).build();
      localPrintManager.print(paramString, new PrintDocumentAdapter()
      {
        private PrintAttributes mAttributes;
        
        public void onFinish()
        {
          if (paramOnPrintFinishCallback != null) {
            paramOnPrintFinishCallback.onFinish();
          }
        }
        
        public void onLayout(PrintAttributes paramAnonymousPrintAttributes1, PrintAttributes paramAnonymousPrintAttributes2, CancellationSignal paramAnonymousCancellationSignal, PrintDocumentAdapter.LayoutResultCallback paramAnonymousLayoutResultCallback, Bundle paramAnonymousBundle)
        {
          mAttributes = paramAnonymousPrintAttributes2;
          paramAnonymousLayoutResultCallback.onLayoutFinished(new PrintDocumentInfo.Builder(paramString).setContentType(1).setPageCount(1).build(), paramAnonymousPrintAttributes2.equals(paramAnonymousPrintAttributes1) ^ true);
        }
        
        public void onWrite(PageRange[] paramAnonymousArrayOfPageRange, ParcelFileDescriptor paramAnonymousParcelFileDescriptor, CancellationSignal paramAnonymousCancellationSignal, PrintDocumentAdapter.WriteResultCallback paramAnonymousWriteResultCallback)
        {
          PrintHelper.PrintHelperApi19.this.writeBitmap(mAttributes, i, paramBitmap, paramAnonymousParcelFileDescriptor, paramAnonymousCancellationSignal, paramAnonymousWriteResultCallback);
        }
      }, (PrintAttributes)localObject);
    }
    
    public void printBitmap(final String paramString, final Uri paramUri, final PrintHelper.OnPrintFinishCallback paramOnPrintFinishCallback)
      throws FileNotFoundException
    {
      paramUri = new PrintDocumentAdapter()
      {
        private PrintAttributes mAttributes;
        Bitmap mBitmap = null;
        AsyncTask<Uri, Boolean, Bitmap> mLoadBitmap;
        
        private void cancelLoad()
        {
          Object localObject = mLock;
          try
          {
            if (mDecodeOptions != null)
            {
              mDecodeOptions.requestCancelDecode();
              mDecodeOptions = null;
            }
            return;
          }
          catch (Throwable localThrowable)
          {
            throw localThrowable;
          }
        }
        
        public void onFinish()
        {
          super.onFinish();
          cancelLoad();
          if (mLoadBitmap != null) {
            mLoadBitmap.cancel(true);
          }
          if (paramOnPrintFinishCallback != null) {
            paramOnPrintFinishCallback.onFinish();
          }
          if (mBitmap != null)
          {
            mBitmap.recycle();
            mBitmap = null;
          }
        }
        
        public void onLayout(final PrintAttributes paramAnonymousPrintAttributes1, final PrintAttributes paramAnonymousPrintAttributes2, final CancellationSignal paramAnonymousCancellationSignal, final PrintDocumentAdapter.LayoutResultCallback paramAnonymousLayoutResultCallback, Bundle paramAnonymousBundle)
        {
          try
          {
            mAttributes = paramAnonymousPrintAttributes2;
            if (paramAnonymousCancellationSignal.isCanceled())
            {
              paramAnonymousLayoutResultCallback.onLayoutCancelled();
              return;
            }
            if (mBitmap != null)
            {
              paramAnonymousLayoutResultCallback.onLayoutFinished(new PrintDocumentInfo.Builder(paramString).setContentType(1).setPageCount(1).build(), paramAnonymousPrintAttributes2.equals(paramAnonymousPrintAttributes1) ^ true);
              return;
            }
            mLoadBitmap = new AsyncTask()
            {
              protected Bitmap doInBackground(Uri... paramAnonymous2VarArgs)
              {
                paramAnonymous2VarArgs = PrintHelper.PrintHelperApi19.this;
                Uri localUri = val$imageFile;
                try
                {
                  paramAnonymous2VarArgs = paramAnonymous2VarArgs.loadConstrainedBitmap(localUri);
                  return paramAnonymous2VarArgs;
                }
                catch (FileNotFoundException paramAnonymous2VarArgs)
                {
                  for (;;) {}
                }
                return null;
              }
              
              protected void onCancelled(Bitmap paramAnonymous2Bitmap)
              {
                paramAnonymousLayoutResultCallback.onLayoutCancelled();
                mLoadBitmap = null;
              }
              
              protected void onPostExecute(Bitmap paramAnonymous2Bitmap)
              {
                super.onPostExecute(paramAnonymous2Bitmap);
                Object localObject = paramAnonymous2Bitmap;
                if (paramAnonymous2Bitmap != null) {
                  if (mPrintActivityRespectsOrientation)
                  {
                    localObject = paramAnonymous2Bitmap;
                    if (mOrientation != 0) {}
                  }
                  else
                  {
                    try
                    {
                      PrintAttributes.MediaSize localMediaSize = mAttributes.getMediaSize();
                      localObject = paramAnonymous2Bitmap;
                      if (localMediaSize != null)
                      {
                        localObject = paramAnonymous2Bitmap;
                        if (localMediaSize.isPortrait() != PrintHelper.PrintHelperApi19.isPortrait(paramAnonymous2Bitmap))
                        {
                          localObject = new Matrix();
                          ((Matrix)localObject).postRotate(90.0F);
                          localObject = Bitmap.createBitmap(paramAnonymous2Bitmap, 0, 0, paramAnonymous2Bitmap.getWidth(), paramAnonymous2Bitmap.getHeight(), (Matrix)localObject, true);
                        }
                      }
                    }
                    catch (Throwable paramAnonymous2Bitmap)
                    {
                      throw paramAnonymous2Bitmap;
                    }
                  }
                }
                mBitmap = ((Bitmap)localObject);
                if (localObject != null)
                {
                  paramAnonymous2Bitmap = new PrintDocumentInfo.Builder(val$jobName).setContentType(1).setPageCount(1).build();
                  boolean bool = paramAnonymousPrintAttributes2.equals(paramAnonymousPrintAttributes1);
                  paramAnonymousLayoutResultCallback.onLayoutFinished(paramAnonymous2Bitmap, true ^ bool);
                }
                else
                {
                  paramAnonymousLayoutResultCallback.onLayoutFailed(null);
                }
                mLoadBitmap = null;
              }
              
              protected void onPreExecute()
              {
                paramAnonymousCancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener()
                {
                  public void onCancel()
                  {
                    PrintHelper.PrintHelperApi19.3.this.cancelLoad();
                    cancel(false);
                  }
                });
              }
            }.execute(new Uri[0]);
            return;
          }
          catch (Throwable paramAnonymousPrintAttributes1)
          {
            throw paramAnonymousPrintAttributes1;
          }
        }
        
        public void onWrite(PageRange[] paramAnonymousArrayOfPageRange, ParcelFileDescriptor paramAnonymousParcelFileDescriptor, CancellationSignal paramAnonymousCancellationSignal, PrintDocumentAdapter.WriteResultCallback paramAnonymousWriteResultCallback)
        {
          PrintHelper.PrintHelperApi19.this.writeBitmap(mAttributes, val$fittingMode, mBitmap, paramAnonymousParcelFileDescriptor, paramAnonymousCancellationSignal, paramAnonymousWriteResultCallback);
        }
      };
      paramOnPrintFinishCallback = (PrintManager)mContext.getSystemService("print");
      PrintAttributes.Builder localBuilder = new PrintAttributes.Builder();
      localBuilder.setColorMode(mColorMode);
      if ((mOrientation != 1) && (mOrientation != 0))
      {
        if (mOrientation == 2) {
          localBuilder.setMediaSize(PrintAttributes.MediaSize.UNKNOWN_PORTRAIT);
        }
      }
      else {
        localBuilder.setMediaSize(PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE);
      }
      paramOnPrintFinishCallback.print(paramString, paramUri, localBuilder.build());
    }
    
    public void setColorMode(int paramInt)
    {
      mColorMode = paramInt;
    }
    
    public void setOrientation(int paramInt)
    {
      mOrientation = paramInt;
    }
    
    public void setScaleMode(int paramInt)
    {
      mScaleMode = paramInt;
    }
  }
  
  @RequiresApi(20)
  private static class PrintHelperApi20
    extends PrintHelper.PrintHelperApi19
  {
    PrintHelperApi20(Context paramContext)
    {
      super();
      mPrintActivityRespectsOrientation = false;
    }
  }
  
  @RequiresApi(23)
  private static class PrintHelperApi23
    extends PrintHelper.PrintHelperApi20
  {
    PrintHelperApi23(Context paramContext)
    {
      super();
      mIsMinMarginsHandlingCorrect = false;
    }
    
    protected PrintAttributes.Builder copyAttributes(PrintAttributes paramPrintAttributes)
    {
      PrintAttributes.Builder localBuilder = super.copyAttributes(paramPrintAttributes);
      if (paramPrintAttributes.getDuplexMode() != 0) {
        localBuilder.setDuplexMode(paramPrintAttributes.getDuplexMode());
      }
      return localBuilder;
    }
  }
  
  @RequiresApi(24)
  private static class PrintHelperApi24
    extends PrintHelper.PrintHelperApi23
  {
    PrintHelperApi24(Context paramContext)
    {
      super();
      mIsMinMarginsHandlingCorrect = true;
      mPrintActivityRespectsOrientation = true;
    }
  }
  
  private static final class PrintHelperStub
    implements PrintHelper.PrintHelperVersionImpl
  {
    int mColorMode = 2;
    int mOrientation = 1;
    int mScaleMode = 2;
    
    private PrintHelperStub() {}
    
    public int getColorMode()
    {
      return mColorMode;
    }
    
    public int getOrientation()
    {
      return mOrientation;
    }
    
    public int getScaleMode()
    {
      return mScaleMode;
    }
    
    public void printBitmap(String paramString, Bitmap paramBitmap, PrintHelper.OnPrintFinishCallback paramOnPrintFinishCallback) {}
    
    public void printBitmap(String paramString, Uri paramUri, PrintHelper.OnPrintFinishCallback paramOnPrintFinishCallback) {}
    
    public void setColorMode(int paramInt)
    {
      mColorMode = paramInt;
    }
    
    public void setOrientation(int paramInt)
    {
      mOrientation = paramInt;
    }
    
    public void setScaleMode(int paramInt)
    {
      mScaleMode = paramInt;
    }
  }
  
  static abstract interface PrintHelperVersionImpl
  {
    public abstract int getColorMode();
    
    public abstract int getOrientation();
    
    public abstract int getScaleMode();
    
    public abstract void printBitmap(String paramString, Bitmap paramBitmap, PrintHelper.OnPrintFinishCallback paramOnPrintFinishCallback);
    
    public abstract void printBitmap(String paramString, Uri paramUri, PrintHelper.OnPrintFinishCallback paramOnPrintFinishCallback)
      throws FileNotFoundException;
    
    public abstract void setColorMode(int paramInt);
    
    public abstract void setOrientation(int paramInt);
    
    public abstract void setScaleMode(int paramInt);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  private static @interface ScaleMode {}
}
