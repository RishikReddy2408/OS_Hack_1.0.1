package com.google.android.android.common.data;

import android.content.ContentValues;
import android.database.CharArrayBuffer;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.CursorWindow;
import android.database.sqlite.SQLiteClosable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.android.common.internal.Asserts;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.android.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

@KeepForSdk
@KeepName
@SafeParcelable.Class(creator="DataHolderCreator", validate=true)
public final class DataHolder
  extends AbstractSafeParcelable
  implements Closeable
{
  @KeepForSdk
  public static final Parcelable.Creator<com.google.android.gms.common.data.DataHolder> CREATOR = new VerticalProgressBar.SavedState.1();
  private static final Builder zalx = new Image(new String[0], null);
  private boolean mClosed = false;
  @SafeParcelable.VersionField(id=1000)
  private final int zale;
  @SafeParcelable.Field(getter="getColumns", id=1)
  private final String[] zalp;
  private Bundle zalq;
  @SafeParcelable.Field(getter="getWindows", id=2)
  private final CursorWindow[] zalr;
  @SafeParcelable.Field(getter="getStatusCode", id=3)
  private final int zals;
  @SafeParcelable.Field(getter="getMetadata", id=4)
  private final Bundle zalt;
  private int[] zalu;
  private int zalv;
  private boolean zalw = true;
  
  DataHolder(int paramInt1, String[] paramArrayOfString, CursorWindow[] paramArrayOfCursorWindow, int paramInt2, Bundle paramBundle)
  {
    zale = paramInt1;
    zalp = paramArrayOfString;
    zalr = paramArrayOfCursorWindow;
    zals = paramInt2;
    zalt = paramBundle;
  }
  
  public DataHolder(Cursor paramCursor, int paramInt, Bundle paramBundle)
  {
    this(new com.google.android.android.common.sqlite.CursorWrapper(paramCursor), paramInt, paramBundle);
  }
  
  private DataHolder(Builder paramBuilder, int paramInt, Bundle paramBundle)
  {
    this(Builder.access$getTaskService(paramBuilder), doInBackground(paramBuilder, -1), paramInt, null);
  }
  
  private DataHolder(Builder paramBuilder, int paramInt1, Bundle paramBundle, int paramInt2)
  {
    this(Builder.access$getTaskService(paramBuilder), doInBackground(paramBuilder, -1), paramInt1, paramBundle);
  }
  
  private DataHolder(com.google.android.android.common.sqlite.CursorWrapper paramCursorWrapper, int paramInt, Bundle paramBundle)
  {
    this(paramCursorWrapper.getColumnNames(), convert(paramCursorWrapper), paramInt, paramBundle);
  }
  
  public DataHolder(String[] paramArrayOfString, CursorWindow[] paramArrayOfCursorWindow, int paramInt, Bundle paramBundle)
  {
    zale = 1;
    zalp = ((String[])Preconditions.checkNotNull(paramArrayOfString));
    zalr = ((CursorWindow[])Preconditions.checkNotNull(paramArrayOfCursorWindow));
    zals = paramInt;
    zalt = paramBundle;
    zaca();
  }
  
  public static Builder builder(String[] paramArrayOfString)
  {
    return new Builder(null, null);
  }
  
  private static CursorWindow[] convert(com.google.android.android.common.sqlite.CursorWrapper paramCursorWrapper)
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      int j = paramCursorWrapper.getCount();
      Object localObject = paramCursorWrapper.getWindow();
      if (localObject != null)
      {
        i = ((CursorWindow)localObject).getStartPosition();
        if (i == 0)
        {
          ((SQLiteClosable)localObject).acquireReference();
          paramCursorWrapper.setWindow(null);
          localArrayList.add(localObject);
          i = ((CursorWindow)localObject).getNumRows();
          break label64;
        }
      }
      int i = 0;
      label64:
      while (i < j)
      {
        boolean bool = paramCursorWrapper.moveToPosition(i);
        if (!bool) {
          break;
        }
        CursorWindow localCursorWindow = paramCursorWrapper.getWindow();
        localObject = localCursorWindow;
        if (localCursorWindow != null)
        {
          localCursorWindow.acquireReference();
          paramCursorWrapper.setWindow(null);
        }
        else
        {
          localObject = new CursorWindow(false);
          ((CursorWindow)localObject).setStartPosition(i);
          paramCursorWrapper.fillWindow(i, (CursorWindow)localObject);
        }
        i = ((CursorWindow)localObject).getNumRows();
        if (i == 0) {
          break;
        }
        localArrayList.add(localObject);
        i = ((CursorWindow)localObject).getStartPosition();
        int k = ((CursorWindow)localObject).getNumRows();
        i += k;
      }
      paramCursorWrapper.close();
      return (CursorWindow[])localArrayList.toArray(new CursorWindow[localArrayList.size()]);
    }
    catch (Throwable localThrowable)
    {
      paramCursorWrapper.close();
      throw localThrowable;
    }
  }
  
  private static CursorWindow[] doInBackground(Builder paramBuilder, int paramInt)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: fail exe a9 = a8\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:92)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.dfs(Cfg.java:255)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze0(BaseAnalyze.java:75)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze(BaseAnalyze.java:69)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer.transform(UnSSATransformer.java:274)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:163)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\nCaused by: java.lang.NullPointerException\n");
  }
  
  public static DataHolder empty(int paramInt)
  {
    return new DataHolder(zalx, paramInt, null);
  }
  
  private final void get(String paramString, int paramInt)
  {
    if ((zalq != null) && (zalq.containsKey(paramString)))
    {
      if (!isClosed())
      {
        if ((paramInt >= 0) && (paramInt < zalv)) {
          return;
        }
        throw new CursorIndexOutOfBoundsException(paramInt, zalv);
      }
      throw new IllegalArgumentException("Buffer is closed.");
    }
    paramString = String.valueOf(paramString);
    if (paramString.length() != 0) {
      paramString = "No such column: ".concat(paramString);
    } else {
      paramString = new String("No such column: ");
    }
    throw new IllegalArgumentException(paramString);
  }
  
  public final void close()
  {
    try
    {
      if (!mClosed)
      {
        mClosed = true;
        int i = 0;
        while (i < zalr.length)
        {
          zalr[i].close();
          i += 1;
        }
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  protected final void finalize()
    throws Throwable
  {
    try
    {
      boolean bool = zalw;
      if (bool)
      {
        int i = zalr.length;
        if (i > 0)
        {
          bool = isClosed();
          if (!bool)
          {
            close();
            String str = toString();
            i = String.valueOf(str).length();
            StringBuilder localStringBuilder = new StringBuilder(i + 178);
            localStringBuilder.append("Internal data leak within a DataBuffer object detected!  Be sure to explicitly call release() on all DataBuffer extending objects when you are done with them. (internal object: ");
            localStringBuilder.append(str);
            localStringBuilder.append(")");
            Log.e("DataBuffer", localStringBuilder.toString());
          }
        }
      }
      super.finalize();
      return;
    }
    catch (Throwable localThrowable)
    {
      super.finalize();
      throw localThrowable;
    }
  }
  
  public final boolean getBoolean(String paramString, int paramInt1, int paramInt2)
  {
    get(paramString, paramInt1);
    return Long.valueOf(zalr[paramInt2].getLong(paramInt1, zalq.getInt(paramString))).longValue() == 1L;
  }
  
  public final byte[] getByteArray(String paramString, int paramInt1, int paramInt2)
  {
    get(paramString, paramInt1);
    return zalr[paramInt2].getBlob(paramInt1, zalq.getInt(paramString));
  }
  
  public final int getCount()
  {
    return zalv;
  }
  
  public final float getData(String paramString, int paramInt1, int paramInt2)
  {
    get(paramString, paramInt1);
    return zalr[paramInt2].getFloat(paramInt1, zalq.getInt(paramString));
  }
  
  public final double getDouble(String paramString, int paramInt1, int paramInt2)
  {
    get(paramString, paramInt1);
    return zalr[paramInt2].getDouble(paramInt1, zalq.getInt(paramString));
  }
  
  public final int getInteger(String paramString, int paramInt1, int paramInt2)
  {
    get(paramString, paramInt1);
    return zalr[paramInt2].getInt(paramInt1, zalq.getInt(paramString));
  }
  
  public final long getLong(String paramString, int paramInt1, int paramInt2)
  {
    get(paramString, paramInt1);
    return zalr[paramInt2].getLong(paramInt1, zalq.getInt(paramString));
  }
  
  public final Bundle getMetadata()
  {
    return zalt;
  }
  
  public final int getStatusCode()
  {
    return zals;
  }
  
  public final String getString(String paramString, int paramInt1, int paramInt2)
  {
    get(paramString, paramInt1);
    return zalr[paramInt2].getString(paramInt1, zalq.getInt(paramString));
  }
  
  public final int getWindowIndex(int paramInt)
  {
    int i = 0;
    boolean bool;
    if ((paramInt >= 0) && (paramInt < zalv)) {
      bool = true;
    } else {
      bool = false;
    }
    Preconditions.checkState(bool);
    int j;
    for (;;)
    {
      j = i;
      if (i >= zalu.length) {
        break;
      }
      if (paramInt < zalu[i])
      {
        j = i - 1;
        break;
      }
      i += 1;
    }
    if (j == zalu.length) {
      return j - 1;
    }
    return j;
  }
  
  public final boolean hasColumn(String paramString)
  {
    return zalq.containsKey(paramString);
  }
  
  public final boolean hasNull(String paramString, int paramInt1, int paramInt2)
  {
    get(paramString, paramInt1);
    return zalr[paramInt2].isNull(paramInt1, zalq.getInt(paramString));
  }
  
  public final boolean isClosed()
  {
    try
    {
      boolean bool = mClosed;
      return bool;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public final void put(String paramString, int paramInt1, int paramInt2, CharArrayBuffer paramCharArrayBuffer)
  {
    get(paramString, paramInt1);
    zalr[paramInt2].copyStringToBuffer(paramInt1, zalq.getInt(paramString), paramCharArrayBuffer);
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeStringArray(paramParcel, 1, zalp, false);
    SafeParcelWriter.writeTypedArray(paramParcel, 2, zalr, paramInt, false);
    SafeParcelWriter.writeInt(paramParcel, 3, getStatusCode());
    SafeParcelWriter.writeBundle(paramParcel, 4, getMetadata(), false);
    SafeParcelWriter.writeInt(paramParcel, 1000, zale);
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
    if ((paramInt & 0x1) != 0) {
      close();
    }
  }
  
  public final void zaca()
  {
    zalq = new Bundle();
    int k = 0;
    int i = 0;
    while (i < zalp.length)
    {
      zalq.putInt(zalp[i], i);
      i += 1;
    }
    zalu = new int[zalr.length];
    int j = 0;
    i = k;
    while (i < zalr.length)
    {
      zalu[i] = j;
      k = zalr[i].getStartPosition();
      j += zalr[i].getNumRows() - (j - k);
      i += 1;
    }
    zalv = j;
  }
  
  @KeepForSdk
  public class Builder
  {
    private final String[] zalp;
    private final ArrayList<HashMap<String, Object>> zaly;
    private final String zalz;
    private final HashMap<Object, Integer> zama;
    private boolean zamb;
    private String zamc;
    
    private Builder(String paramString)
    {
      zalp = ((String[])Preconditions.checkNotNull(this$1));
      zaly = new ArrayList();
      zalz = paramString;
      zama = new HashMap();
      zamb = false;
      zamc = null;
    }
    
    public DataHolder build(int paramInt)
    {
      return new DataHolder(this, paramInt, null, null);
    }
    
    public DataHolder build(int paramInt, Bundle paramBundle)
    {
      return new DataHolder(this, paramInt, paramBundle, -1, null);
    }
    
    public Builder intersect(HashMap paramHashMap)
    {
      Asserts.checkNotNull(paramHashMap);
      if (zalz == null) {}
      Integer localInteger;
      for (;;)
      {
        i = -1;
        break label78;
        Object localObject = paramHashMap.get(zalz);
        if (localObject != null)
        {
          localInteger = (Integer)zama.get(localObject);
          if (localInteger != null) {
            break;
          }
          zama.put(localObject, Integer.valueOf(zaly.size()));
        }
      }
      int i = localInteger.intValue();
      label78:
      if (i == -1)
      {
        zaly.add(paramHashMap);
      }
      else
      {
        zaly.remove(i);
        zaly.add(i, paramHashMap);
      }
      zamb = false;
      return this;
    }
    
    public Builder withRow(ContentValues paramContentValues)
    {
      Asserts.checkNotNull(paramContentValues);
      HashMap localHashMap = new HashMap(paramContentValues.size());
      paramContentValues = paramContentValues.valueSet().iterator();
      while (paramContentValues.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramContentValues.next();
        localHashMap.put((String)localEntry.getKey(), localEntry.getValue());
      }
      return intersect(localHashMap);
    }
  }
  
  public final class zaa
    extends RuntimeException
  {
    public zaa()
    {
      super();
    }
  }
}
