package com.google.android.android.common.data;

import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.ArrayList;

@KeepForSdk
public abstract class EntityBuffer<T>
  extends com.google.android.gms.common.data.AbstractDataBuffer<T>
{
  private boolean zamd = false;
  private ArrayList<Integer> zame;
  
  protected EntityBuffer(DataHolder paramDataHolder)
  {
    super(paramDataHolder);
  }
  
  private final int getIndex(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < zame.size())) {
      return ((Integer)zame.get(paramInt)).intValue();
    }
    StringBuilder localStringBuilder = new StringBuilder(53);
    localStringBuilder.append("Position ");
    localStringBuilder.append(paramInt);
    localStringBuilder.append(" is out of bounds for this buffer");
    throw new IllegalArgumentException(localStringBuilder.toString());
  }
  
  private final void zacb()
  {
    for (;;)
    {
      int i;
      Object localObject3;
      try
      {
        if (!zamd)
        {
          int j = mDataHolder.getCount();
          zame = new ArrayList();
          if (j > 0)
          {
            zame.add(Integer.valueOf(0));
            String str2 = getPrimaryDataMarkerColumn();
            i = mDataHolder.getWindowIndex(0);
            Object localObject1 = mDataHolder.getString(str2, 0, i);
            i = 1;
            if (i < j)
            {
              int k = mDataHolder.getWindowIndex(i);
              String str1 = mDataHolder.getString(str2, i, k);
              if (str1 != null)
              {
                localObject3 = localObject1;
                if (str1.equals(localObject1)) {
                  break label233;
                }
                zame.add(Integer.valueOf(i));
                localObject3 = str1;
                break label233;
              }
              localObject1 = new StringBuilder(String.valueOf(str2).length() + 78);
              ((StringBuilder)localObject1).append("Missing value for markerColumn: ");
              ((StringBuilder)localObject1).append(str2);
              ((StringBuilder)localObject1).append(", at row: ");
              ((StringBuilder)localObject1).append(i);
              ((StringBuilder)localObject1).append(", for window: ");
              ((StringBuilder)localObject1).append(k);
              throw new NullPointerException(((StringBuilder)localObject1).toString());
            }
          }
          zamd = true;
        }
        else
        {
          return;
        }
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
      label233:
      i += 1;
      Object localObject2 = localObject3;
    }
  }
  
  public final Object get(int paramInt)
  {
    zacb();
    int k = getIndex(paramInt);
    int j = 0;
    int i = j;
    if (paramInt >= 0) {
      if (paramInt == zame.size())
      {
        i = j;
      }
      else
      {
        if (paramInt == zame.size() - 1) {
          i = mDataHolder.getCount() - ((Integer)zame.get(paramInt)).intValue();
        } else {
          i = ((Integer)zame.get(paramInt + 1)).intValue() - ((Integer)zame.get(paramInt)).intValue();
        }
        if (i == 1)
        {
          paramInt = getIndex(paramInt);
          int m = mDataHolder.getWindowIndex(paramInt);
          String str = getChildDataMarkerColumn();
          if ((str != null) && (mDataHolder.getString(str, paramInt, m) == null)) {
            i = j;
          }
        }
      }
    }
    return getEntry(k, i);
  }
  
  protected String getChildDataMarkerColumn()
  {
    return null;
  }
  
  public int getCount()
  {
    zacb();
    return zame.size();
  }
  
  protected abstract Object getEntry(int paramInt1, int paramInt2);
  
  protected abstract String getPrimaryDataMarkerColumn();
}
