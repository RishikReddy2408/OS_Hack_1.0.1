package com.google.android.android.common.data;

import android.content.ContentValues;
import java.util.HashMap;

final class Image
  extends DataHolder.Builder
{
  Image(String[] paramArrayOfString, String paramString)
  {
    super(paramArrayOfString, null, null);
  }
  
  public final DataHolder.Builder intersect(HashMap paramHashMap)
  {
    throw new UnsupportedOperationException("Cannot add data to empty builder");
  }
  
  public final DataHolder.Builder withRow(ContentValues paramContentValues)
  {
    throw new UnsupportedOperationException("Cannot add data to empty builder");
  }
}
