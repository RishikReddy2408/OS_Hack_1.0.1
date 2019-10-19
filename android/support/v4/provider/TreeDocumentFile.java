package android.support.v4.provider;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.util.ArrayList;

@RequiresApi(21)
class TreeDocumentFile
  extends DocumentFile
{
  private Context mContext;
  private Uri mUri;
  
  TreeDocumentFile(DocumentFile paramDocumentFile, Context paramContext, Uri paramUri)
  {
    super(paramDocumentFile);
    mContext = paramContext;
    mUri = paramUri;
  }
  
  private static void closeQuietly(AutoCloseable paramAutoCloseable)
  {
    if (paramAutoCloseable != null) {
      try
      {
        paramAutoCloseable.close();
        return;
      }
      catch (RuntimeException paramAutoCloseable)
      {
        throw paramAutoCloseable;
      }
      catch (Exception paramAutoCloseable) {}
    }
  }
  
  private static Uri createFile(Context paramContext, Uri paramUri, String paramString1, String paramString2)
  {
    try
    {
      paramContext = DocumentsContract.createDocument(paramContext.getContentResolver(), paramUri, paramString1, paramString2);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  public boolean canRead()
  {
    return DocumentsContractApi19.canRead(mContext, mUri);
  }
  
  public boolean canWrite()
  {
    return DocumentsContractApi19.canWrite(mContext, mUri);
  }
  
  public DocumentFile createDirectory(String paramString)
  {
    paramString = createFile(mContext, mUri, "vnd.android.document/directory", paramString);
    if (paramString != null) {
      return new TreeDocumentFile(this, mContext, paramString);
    }
    return null;
  }
  
  public DocumentFile createFile(String paramString1, String paramString2)
  {
    paramString1 = createFile(mContext, mUri, paramString1, paramString2);
    if (paramString1 != null) {
      return new TreeDocumentFile(this, mContext, paramString1);
    }
    return null;
  }
  
  public boolean delete()
  {
    Object localObject = mContext;
    try
    {
      localObject = ((Context)localObject).getContentResolver();
      Uri localUri = mUri;
      boolean bool = DocumentsContract.deleteDocument((ContentResolver)localObject, localUri);
      return bool;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return false;
  }
  
  public boolean exists()
  {
    return DocumentsContractApi19.exists(mContext, mUri);
  }
  
  public String getName()
  {
    return DocumentsContractApi19.getName(mContext, mUri);
  }
  
  public String getType()
  {
    return DocumentsContractApi19.getType(mContext, mUri);
  }
  
  public Uri getUri()
  {
    return mUri;
  }
  
  public boolean isDirectory()
  {
    return DocumentsContractApi19.isDirectory(mContext, mUri);
  }
  
  public boolean isFile()
  {
    return DocumentsContractApi19.isFile(mContext, mUri);
  }
  
  public boolean isVirtual()
  {
    return DocumentsContractApi19.isVirtual(mContext, mUri);
  }
  
  public long lastModified()
  {
    return DocumentsContractApi19.lastModified(mContext, mUri);
  }
  
  public long length()
  {
    return DocumentsContractApi19.length(mContext, mUri);
  }
  
  public DocumentFile[] listFiles()
  {
    Object localObject2 = mContext.getContentResolver();
    Uri localUri = DocumentsContract.buildChildDocumentsUriUsingTree(mUri, DocumentsContract.getDocumentId(mUri));
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    StringBuilder localStringBuilder = null;
    Object localObject1 = null;
    try
    {
      localObject2 = ((ContentResolver)localObject2).query(localUri, new String[] { "document_id" }, null, null, null);
      try
      {
        for (;;)
        {
          boolean bool = ((Cursor)localObject2).moveToNext();
          if (!bool) {
            break;
          }
          localObject1 = ((Cursor)localObject2).getString(0);
          localUri = mUri;
          localArrayList.add(DocumentsContract.buildDocumentUriUsingTree(localUri, (String)localObject1));
        }
        closeQuietly((AutoCloseable)localObject2);
      }
      catch (Throwable localThrowable2)
      {
        localObject1 = localObject2;
        localObject2 = localThrowable2;
        break label258;
      }
      catch (Exception localException1) {}
      localObject1 = localObject3;
    }
    catch (Throwable localThrowable1) {}catch (Exception localException2)
    {
      localObject3 = localStringBuilder;
    }
    localStringBuilder = new StringBuilder();
    localObject1 = localObject3;
    localStringBuilder.append("Failed query: ");
    localObject1 = localObject3;
    localStringBuilder.append(localException2);
    localObject1 = localObject3;
    Log.w("DocumentFile", localStringBuilder.toString());
    closeQuietly((AutoCloseable)localObject3);
    localObject1 = (Uri[])localArrayList.toArray(new Uri[localArrayList.size()]);
    Object localObject3 = new DocumentFile[localObject1.length];
    while (i < localObject1.length)
    {
      localObject3[i] = new TreeDocumentFile(this, mContext, localObject1[i]);
      i += 1;
    }
    return localObject3;
    label258:
    closeQuietly((AutoCloseable)localObject1);
    throw ((Throwable)localObject3);
  }
  
  public boolean renameTo(String paramString)
  {
    Object localObject = mContext;
    try
    {
      localObject = ((Context)localObject).getContentResolver();
      Uri localUri = mUri;
      paramString = DocumentsContract.renameDocument((ContentResolver)localObject, localUri, paramString);
      if (paramString != null)
      {
        mUri = paramString;
        return true;
      }
      return false;
    }
    catch (Exception paramString) {}
    return false;
  }
}
