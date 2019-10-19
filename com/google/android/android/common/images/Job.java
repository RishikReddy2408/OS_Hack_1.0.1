package com.google.android.android.common.images;

import android.net.Uri;
import com.google.android.android.common.internal.Objects;

final class Job
{
  public final Uri key;
  
  public Job(Uri paramUri)
  {
    key = paramUri;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof Job)) {
      return false;
    }
    if (this == paramObject) {
      return true;
    }
    return Objects.equal(key, key);
  }
  
  public final int hashCode()
  {
    return Objects.hashCode(new Object[] { key });
  }
}
