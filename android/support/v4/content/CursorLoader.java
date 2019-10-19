package android.support.v4.content;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.os.CancellationSignal;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Arrays;

public class CursorLoader
  extends AsyncTaskLoader<Cursor>
{
  CancellationSignal mCancellationSignal;
  Cursor mCursor;
  final Loader<Cursor>.ForceLoadContentObserver mObserver = new Loader.ForceLoadContentObserver(this);
  String[] mProjection;
  String mSelection;
  String[] mSelectionArgs;
  String mSortOrder;
  Uri mUri;
  
  public CursorLoader(Context paramContext)
  {
    super(paramContext);
  }
  
  public CursorLoader(Context paramContext, Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2)
  {
    super(paramContext);
    mUri = paramUri;
    mProjection = paramArrayOfString1;
    mSelection = paramString1;
    mSelectionArgs = paramArrayOfString2;
    mSortOrder = paramString2;
  }
  
  public void cancelLoadInBackground()
  {
    super.cancelLoadInBackground();
    try
    {
      if (mCancellationSignal != null) {
        mCancellationSignal.cancel();
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public void deliverResult(Cursor paramCursor)
  {
    if (isReset())
    {
      if (paramCursor != null) {
        paramCursor.close();
      }
    }
    else
    {
      Cursor localCursor = mCursor;
      mCursor = paramCursor;
      if (isStarted()) {
        super.deliverResult(paramCursor);
      }
      if ((localCursor != null) && (localCursor != paramCursor) && (!localCursor.isClosed())) {
        localCursor.close();
      }
    }
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    super.dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mUri=");
    paramPrintWriter.println(mUri);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mProjection=");
    paramPrintWriter.println(Arrays.toString(mProjection));
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mSelection=");
    paramPrintWriter.println(mSelection);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mSelectionArgs=");
    paramPrintWriter.println(Arrays.toString(mSelectionArgs));
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mSortOrder=");
    paramPrintWriter.println(mSortOrder);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mCursor=");
    paramPrintWriter.println(mCursor);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mContentChanged=");
    paramPrintWriter.println(mContentChanged);
  }
  
  public String[] getProjection()
  {
    return mProjection;
  }
  
  public String getSelection()
  {
    return mSelection;
  }
  
  public String[] getSelectionArgs()
  {
    return mSelectionArgs;
  }
  
  public String getSortOrder()
  {
    return mSortOrder;
  }
  
  public Uri getUri()
  {
    return mUri;
  }
  
  /* Error */
  public Cursor loadInBackground()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual 139	android/support/v4/content/AsyncTaskLoader:isLoadInBackgroundCanceled	()Z
    //   6: ifne +117 -> 123
    //   9: aload_0
    //   10: new 53	android/support/v4/os/CancellationSignal
    //   13: dup
    //   14: invokespecial 141	android/support/v4/os/CancellationSignal:<init>	()V
    //   17: putfield 51	android/support/v4/content/CursorLoader:mCancellationSignal	Landroid/support/v4/os/CancellationSignal;
    //   20: aload_0
    //   21: monitorexit
    //   22: aload_0
    //   23: invokevirtual 145	android/support/v4/content/Loader:getContext	()Landroid/content/Context;
    //   26: invokevirtual 151	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   29: aload_0
    //   30: getfield 35	android/support/v4/content/CursorLoader:mUri	Landroid/net/Uri;
    //   33: aload_0
    //   34: getfield 37	android/support/v4/content/CursorLoader:mProjection	[Ljava/lang/String;
    //   37: aload_0
    //   38: getfield 39	android/support/v4/content/CursorLoader:mSelection	Ljava/lang/String;
    //   41: aload_0
    //   42: getfield 41	android/support/v4/content/CursorLoader:mSelectionArgs	[Ljava/lang/String;
    //   45: aload_0
    //   46: getfield 43	android/support/v4/content/CursorLoader:mSortOrder	Ljava/lang/String;
    //   49: aload_0
    //   50: getfield 51	android/support/v4/content/CursorLoader:mCancellationSignal	Landroid/support/v4/os/CancellationSignal;
    //   53: invokestatic 157	android/support/v4/content/ContentResolverCompat:query	(Landroid/content/ContentResolver;Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Landroid/support/v4/os/CancellationSignal;)Landroid/database/Cursor;
    //   56: astore_1
    //   57: aload_1
    //   58: ifnull +32 -> 90
    //   61: aload_1
    //   62: invokeinterface 161 1 0
    //   67: pop
    //   68: aload_1
    //   69: aload_0
    //   70: getfield 31	android/support/v4/content/CursorLoader:mObserver	Landroid/support/v4/content/Loader$ForceLoadContentObserver;
    //   73: invokeinterface 165 2 0
    //   78: goto +12 -> 90
    //   81: astore_2
    //   82: aload_1
    //   83: invokeinterface 69 1 0
    //   88: aload_2
    //   89: athrow
    //   90: aload_0
    //   91: monitorenter
    //   92: aload_0
    //   93: aconst_null
    //   94: putfield 51	android/support/v4/content/CursorLoader:mCancellationSignal	Landroid/support/v4/os/CancellationSignal;
    //   97: aload_0
    //   98: monitorexit
    //   99: aload_1
    //   100: areturn
    //   101: astore_1
    //   102: aload_0
    //   103: monitorexit
    //   104: aload_1
    //   105: athrow
    //   106: astore_1
    //   107: aload_0
    //   108: monitorenter
    //   109: aload_0
    //   110: aconst_null
    //   111: putfield 51	android/support/v4/content/CursorLoader:mCancellationSignal	Landroid/support/v4/os/CancellationSignal;
    //   114: aload_0
    //   115: monitorexit
    //   116: aload_1
    //   117: athrow
    //   118: astore_1
    //   119: aload_0
    //   120: monitorexit
    //   121: aload_1
    //   122: athrow
    //   123: new 167	android/support/v4/os/OperationCanceledException
    //   126: dup
    //   127: invokespecial 168	android/support/v4/os/OperationCanceledException:<init>	()V
    //   130: athrow
    //   131: astore_1
    //   132: aload_0
    //   133: monitorexit
    //   134: aload_1
    //   135: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	136	0	this	CursorLoader
    //   56	44	1	localCursor	Cursor
    //   101	4	1	localThrowable1	Throwable
    //   106	11	1	localThrowable2	Throwable
    //   118	4	1	localThrowable3	Throwable
    //   131	4	1	localThrowable4	Throwable
    //   81	8	2	localRuntimeException	RuntimeException
    // Exception table:
    //   from	to	target	type
    //   61	78	81	java/lang/RuntimeException
    //   92	99	101	java/lang/Throwable
    //   102	104	101	java/lang/Throwable
    //   22	57	106	java/lang/Throwable
    //   61	78	106	java/lang/Throwable
    //   82	90	106	java/lang/Throwable
    //   109	116	118	java/lang/Throwable
    //   119	121	118	java/lang/Throwable
    //   2	22	131	java/lang/Throwable
    //   123	131	131	java/lang/Throwable
    //   132	134	131	java/lang/Throwable
  }
  
  public void onCanceled(Cursor paramCursor)
  {
    if ((paramCursor != null) && (!paramCursor.isClosed())) {
      paramCursor.close();
    }
  }
  
  protected void onReset()
  {
    super.onReset();
    onStopLoading();
    if ((mCursor != null) && (!mCursor.isClosed())) {
      mCursor.close();
    }
    mCursor = null;
  }
  
  protected void onStartLoading()
  {
    if (mCursor != null) {
      deliverResult(mCursor);
    }
    if ((takeContentChanged()) || (mCursor == null)) {
      forceLoad();
    }
  }
  
  protected void onStopLoading()
  {
    cancelLoad();
  }
  
  public void setProjection(String[] paramArrayOfString)
  {
    mProjection = paramArrayOfString;
  }
  
  public void setSelection(String paramString)
  {
    mSelection = paramString;
  }
  
  public void setSelectionArgs(String[] paramArrayOfString)
  {
    mSelectionArgs = paramArrayOfString;
  }
  
  public void setSortOrder(String paramString)
  {
    mSortOrder = paramString;
  }
  
  public void setUri(Uri paramUri)
  {
    mUri = paramUri;
  }
}
