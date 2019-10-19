package android.support.v7.widget;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.LongSparseArray;
import android.support.v4.util.Pools.Pool;
import android.support.v4.util.Pools.SimplePool;

class ViewInfoStore
{
  private static final boolean DEBUG = false;
  @VisibleForTesting
  final ArrayMap<RecyclerView.ViewHolder, InfoRecord> mLayoutHolderMap = new ArrayMap();
  @VisibleForTesting
  final LongSparseArray<RecyclerView.ViewHolder> mOldChangedHolders = new LongSparseArray();
  
  ViewInfoStore() {}
  
  private RecyclerView.ItemAnimator.ItemHolderInfo popFromLayoutStep(RecyclerView.ViewHolder paramViewHolder, int paramInt)
  {
    int i = mLayoutHolderMap.indexOfKey(paramViewHolder);
    if (i < 0) {
      return null;
    }
    InfoRecord localInfoRecord = (InfoRecord)mLayoutHolderMap.valueAt(i);
    if ((localInfoRecord != null) && ((flags & paramInt) != 0))
    {
      flags &= (paramInt ^ 0xFFFFFFFF);
      if (paramInt == 4)
      {
        paramViewHolder = preInfo;
      }
      else
      {
        if (paramInt != 8) {
          break label110;
        }
        paramViewHolder = postInfo;
      }
      if ((flags & 0xC) == 0)
      {
        mLayoutHolderMap.removeAt(i);
        InfoRecord.recycle(localInfoRecord);
      }
      return paramViewHolder;
      label110:
      throw new IllegalArgumentException("Must provide flag PRE or POST");
    }
    return null;
  }
  
  void addToAppearedInPreLayoutHolders(RecyclerView.ViewHolder paramViewHolder, RecyclerView.ItemAnimator.ItemHolderInfo paramItemHolderInfo)
  {
    InfoRecord localInfoRecord2 = (InfoRecord)mLayoutHolderMap.get(paramViewHolder);
    InfoRecord localInfoRecord1 = localInfoRecord2;
    if (localInfoRecord2 == null)
    {
      localInfoRecord1 = InfoRecord.obtain();
      mLayoutHolderMap.put(paramViewHolder, localInfoRecord1);
    }
    flags |= 0x2;
    preInfo = paramItemHolderInfo;
  }
  
  void addToDisappearedInLayout(RecyclerView.ViewHolder paramViewHolder)
  {
    InfoRecord localInfoRecord2 = (InfoRecord)mLayoutHolderMap.get(paramViewHolder);
    InfoRecord localInfoRecord1 = localInfoRecord2;
    if (localInfoRecord2 == null)
    {
      localInfoRecord1 = InfoRecord.obtain();
      mLayoutHolderMap.put(paramViewHolder, localInfoRecord1);
    }
    flags |= 0x1;
  }
  
  void addToOldChangeHolders(long paramLong, RecyclerView.ViewHolder paramViewHolder)
  {
    mOldChangedHolders.put(paramLong, paramViewHolder);
  }
  
  void addToPostLayout(RecyclerView.ViewHolder paramViewHolder, RecyclerView.ItemAnimator.ItemHolderInfo paramItemHolderInfo)
  {
    InfoRecord localInfoRecord2 = (InfoRecord)mLayoutHolderMap.get(paramViewHolder);
    InfoRecord localInfoRecord1 = localInfoRecord2;
    if (localInfoRecord2 == null)
    {
      localInfoRecord1 = InfoRecord.obtain();
      mLayoutHolderMap.put(paramViewHolder, localInfoRecord1);
    }
    postInfo = paramItemHolderInfo;
    flags |= 0x8;
  }
  
  void addToPreLayout(RecyclerView.ViewHolder paramViewHolder, RecyclerView.ItemAnimator.ItemHolderInfo paramItemHolderInfo)
  {
    InfoRecord localInfoRecord2 = (InfoRecord)mLayoutHolderMap.get(paramViewHolder);
    InfoRecord localInfoRecord1 = localInfoRecord2;
    if (localInfoRecord2 == null)
    {
      localInfoRecord1 = InfoRecord.obtain();
      mLayoutHolderMap.put(paramViewHolder, localInfoRecord1);
    }
    preInfo = paramItemHolderInfo;
    flags |= 0x4;
  }
  
  void clear()
  {
    mLayoutHolderMap.clear();
    mOldChangedHolders.clear();
  }
  
  RecyclerView.ViewHolder getFromOldChangeHolders(long paramLong)
  {
    return (RecyclerView.ViewHolder)mOldChangedHolders.get(paramLong);
  }
  
  boolean isDisappearing(RecyclerView.ViewHolder paramViewHolder)
  {
    paramViewHolder = (InfoRecord)mLayoutHolderMap.get(paramViewHolder);
    return (paramViewHolder != null) && ((flags & 0x1) != 0);
  }
  
  boolean isInPreLayout(RecyclerView.ViewHolder paramViewHolder)
  {
    paramViewHolder = (InfoRecord)mLayoutHolderMap.get(paramViewHolder);
    return (paramViewHolder != null) && ((flags & 0x4) != 0);
  }
  
  void onDetach() {}
  
  public void onViewDetached(RecyclerView.ViewHolder paramViewHolder)
  {
    removeFromDisappearedInLayout(paramViewHolder);
  }
  
  @Nullable
  RecyclerView.ItemAnimator.ItemHolderInfo popFromPostLayout(RecyclerView.ViewHolder paramViewHolder)
  {
    return popFromLayoutStep(paramViewHolder, 8);
  }
  
  @Nullable
  RecyclerView.ItemAnimator.ItemHolderInfo popFromPreLayout(RecyclerView.ViewHolder paramViewHolder)
  {
    return popFromLayoutStep(paramViewHolder, 4);
  }
  
  void process(ProcessCallback paramProcessCallback)
  {
    int i = mLayoutHolderMap.size() - 1;
    while (i >= 0)
    {
      RecyclerView.ViewHolder localViewHolder = (RecyclerView.ViewHolder)mLayoutHolderMap.keyAt(i);
      InfoRecord localInfoRecord = (InfoRecord)mLayoutHolderMap.removeAt(i);
      if ((flags & 0x3) == 3) {
        paramProcessCallback.unused(localViewHolder);
      } else if ((flags & 0x1) != 0)
      {
        if (preInfo == null) {
          paramProcessCallback.unused(localViewHolder);
        } else {
          paramProcessCallback.processDisappeared(localViewHolder, preInfo, postInfo);
        }
      }
      else if ((flags & 0xE) == 14) {
        paramProcessCallback.processAppeared(localViewHolder, preInfo, postInfo);
      } else if ((flags & 0xC) == 12) {
        paramProcessCallback.processPersistent(localViewHolder, preInfo, postInfo);
      } else if ((flags & 0x4) != 0) {
        paramProcessCallback.processDisappeared(localViewHolder, preInfo, null);
      } else if ((flags & 0x8) != 0) {
        paramProcessCallback.processAppeared(localViewHolder, preInfo, postInfo);
      } else {
        int j = flags;
      }
      InfoRecord.recycle(localInfoRecord);
      i -= 1;
    }
  }
  
  void removeFromDisappearedInLayout(RecyclerView.ViewHolder paramViewHolder)
  {
    paramViewHolder = (InfoRecord)mLayoutHolderMap.get(paramViewHolder);
    if (paramViewHolder == null) {
      return;
    }
    flags &= 0xFFFFFFFE;
  }
  
  void removeViewHolder(RecyclerView.ViewHolder paramViewHolder)
  {
    int i = mOldChangedHolders.size() - 1;
    while (i >= 0)
    {
      if (paramViewHolder == mOldChangedHolders.valueAt(i))
      {
        mOldChangedHolders.removeAt(i);
        break;
      }
      i -= 1;
    }
    paramViewHolder = (InfoRecord)mLayoutHolderMap.remove(paramViewHolder);
    if (paramViewHolder != null) {
      InfoRecord.recycle(paramViewHolder);
    }
  }
  
  static class InfoRecord
  {
    static final int FLAG_APPEAR = 2;
    static final int FLAG_APPEAR_AND_DISAPPEAR = 3;
    static final int FLAG_APPEAR_PRE_AND_POST = 14;
    static final int FLAG_DISAPPEARED = 1;
    static final int FLAG_POST = 8;
    static final int FLAG_PRE = 4;
    static final int FLAG_PRE_AND_POST = 12;
    static Pools.Pool<InfoRecord> sPool = new Pools.SimplePool(20);
    int flags;
    @Nullable
    RecyclerView.ItemAnimator.ItemHolderInfo postInfo;
    @Nullable
    RecyclerView.ItemAnimator.ItemHolderInfo preInfo;
    
    private InfoRecord() {}
    
    static void drainCache()
    {
      while (sPool.acquire() != null) {}
    }
    
    static InfoRecord obtain()
    {
      InfoRecord localInfoRecord2 = (InfoRecord)sPool.acquire();
      InfoRecord localInfoRecord1 = localInfoRecord2;
      if (localInfoRecord2 == null) {
        localInfoRecord1 = new InfoRecord();
      }
      return localInfoRecord1;
    }
    
    static void recycle(InfoRecord paramInfoRecord)
    {
      flags = 0;
      preInfo = null;
      postInfo = null;
      sPool.release(paramInfoRecord);
    }
  }
  
  static abstract interface ProcessCallback
  {
    public abstract void processAppeared(RecyclerView.ViewHolder paramViewHolder, @Nullable RecyclerView.ItemAnimator.ItemHolderInfo paramItemHolderInfo1, RecyclerView.ItemAnimator.ItemHolderInfo paramItemHolderInfo2);
    
    public abstract void processDisappeared(RecyclerView.ViewHolder paramViewHolder, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo paramItemHolderInfo1, @Nullable RecyclerView.ItemAnimator.ItemHolderInfo paramItemHolderInfo2);
    
    public abstract void processPersistent(RecyclerView.ViewHolder paramViewHolder, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo paramItemHolderInfo1, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo paramItemHolderInfo2);
    
    public abstract void unused(RecyclerView.ViewHolder paramViewHolder);
  }
}
