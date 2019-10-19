package android.support.v4.provider;

import android.support.v4.util.Preconditions;
import android.util.Base64;
import java.util.List;

public final class FontRequest
{
  private final List<List<byte[]>> mCertificates;
  private final int mCertificatesArray;
  private final String mIdentifier;
  private final String mProviderAuthority;
  private final String mProviderPackage;
  private final String mQuery;
  
  public FontRequest(String paramString1, String paramString2, String paramString3, int paramInt)
  {
    mProviderAuthority = ((String)Preconditions.checkNotNull(paramString1));
    mProviderPackage = ((String)Preconditions.checkNotNull(paramString2));
    mQuery = ((String)Preconditions.checkNotNull(paramString3));
    mCertificates = null;
    boolean bool;
    if (paramInt != 0) {
      bool = true;
    } else {
      bool = false;
    }
    Preconditions.checkArgument(bool);
    mCertificatesArray = paramInt;
    paramString1 = new StringBuilder(mProviderAuthority);
    paramString1.append("-");
    paramString1.append(mProviderPackage);
    paramString1.append("-");
    paramString1.append(mQuery);
    mIdentifier = paramString1.toString();
  }
  
  public FontRequest(String paramString1, String paramString2, String paramString3, List paramList)
  {
    mProviderAuthority = ((String)Preconditions.checkNotNull(paramString1));
    mProviderPackage = ((String)Preconditions.checkNotNull(paramString2));
    mQuery = ((String)Preconditions.checkNotNull(paramString3));
    mCertificates = ((List)Preconditions.checkNotNull(paramList));
    mCertificatesArray = 0;
    paramString1 = new StringBuilder(mProviderAuthority);
    paramString1.append("-");
    paramString1.append(mProviderPackage);
    paramString1.append("-");
    paramString1.append(mQuery);
    mIdentifier = paramString1.toString();
  }
  
  public List getCertificates()
  {
    return mCertificates;
  }
  
  public int getCertificatesArrayResId()
  {
    return mCertificatesArray;
  }
  
  public String getIdentifier()
  {
    return mIdentifier;
  }
  
  public String getProviderAuthority()
  {
    return mProviderAuthority;
  }
  
  public String getProviderPackage()
  {
    return mProviderPackage;
  }
  
  public String getQuery()
  {
    return mQuery;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("FontRequest {mProviderAuthority: ");
    ((StringBuilder)localObject).append(mProviderAuthority);
    ((StringBuilder)localObject).append(", mProviderPackage: ");
    ((StringBuilder)localObject).append(mProviderPackage);
    ((StringBuilder)localObject).append(", mQuery: ");
    ((StringBuilder)localObject).append(mQuery);
    ((StringBuilder)localObject).append(", mCertificates:");
    localStringBuilder.append(((StringBuilder)localObject).toString());
    int i = 0;
    while (i < mCertificates.size())
    {
      localStringBuilder.append(" [");
      localObject = (List)mCertificates.get(i);
      int j = 0;
      while (j < ((List)localObject).size())
      {
        localStringBuilder.append(" \"");
        localStringBuilder.append(Base64.encodeToString((byte[])((List)localObject).get(j), 0));
        localStringBuilder.append("\"");
        j += 1;
      }
      localStringBuilder.append(" ]");
      i += 1;
    }
    localStringBuilder.append("}");
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("mCertificatesArray: ");
    ((StringBuilder)localObject).append(mCertificatesArray);
    localStringBuilder.append(((StringBuilder)localObject).toString());
    return localStringBuilder.toString();
  }
}
