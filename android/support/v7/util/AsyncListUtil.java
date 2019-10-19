package android.support.v7.util;

import android.util.Log;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;

public class AsyncListUtil<T>
{
  static final boolean DEBUG = false;
  static final String TAG = "AsyncListUtil";
  boolean mAllowScrollHints;
  private final ThreadUtil.BackgroundCallback<T> mBackgroundCallback = new ThreadUtil.BackgroundCallback()
  {
    private int mFirstRequiredTileStart;
    private int mGeneration;
    private int mItemCount;
    private int mLastRequiredTileStart;
    final SparseBooleanArray mLoadedTiles = new SparseBooleanArray();
    private TileList.Tile<T> mRecycledRoot;
    
    private TileList.Tile acquireTile()
    {
      if (mRecycledRoot != null)
      {
        TileList.Tile localTile = mRecycledRoot;
        mRecycledRoot = mRecycledRoot.mNext;
        return localTile;
      }
      return new TileList.Tile(mTClass, mTileSize);
    }
    
    private void addTile(TileList.Tile paramAnonymousTile)
    {
      mLoadedTiles.put(mStartPosition, true);
      mMainThreadProxy.addTile(mGeneration, paramAnonymousTile);
    }
    
    private void flushTileCache(int paramAnonymousInt)
    {
      int i = mDataCallback.getMaxCachedTiles();
      while (mLoadedTiles.size() >= i)
      {
        int j = mLoadedTiles.keyAt(0);
        int k = mLoadedTiles.keyAt(mLoadedTiles.size() - 1);
        int m = mFirstRequiredTileStart - j;
        int n = k - mLastRequiredTileStart;
        if ((m > 0) && ((m >= n) || (paramAnonymousInt == 2)))
        {
          removeTile(j);
        }
        else
        {
          if ((n <= 0) || ((m >= n) && (paramAnonymousInt != 1))) {
            break;
          }
          removeTile(k);
        }
      }
    }
    
    private int getTileStart(int paramAnonymousInt)
    {
      return paramAnonymousInt - paramAnonymousInt % mTileSize;
    }
    
    private boolean isTileLoaded(int paramAnonymousInt)
    {
      return mLoadedTiles.get(paramAnonymousInt);
    }
    
    private void log(String paramAnonymousString, Object... paramAnonymousVarArgs)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("[BKGR] ");
      localStringBuilder.append(String.format(paramAnonymousString, paramAnonymousVarArgs));
      Log.d("AsyncListUtil", localStringBuilder.toString());
    }
    
    private void removeTile(int paramAnonymousInt)
    {
      mLoadedTiles.delete(paramAnonymousInt);
      mMainThreadProxy.removeTile(mGeneration, paramAnonymousInt);
    }
    
    private void requestTiles(int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, boolean paramAnonymousBoolean)
    {
      int i = paramAnonymousInt1;
      while (i <= paramAnonymousInt2)
      {
        int j;
        if (paramAnonymousBoolean) {
          j = paramAnonymousInt2 + paramAnonymousInt1 - i;
        } else {
          j = i;
        }
        mBackgroundProxy.loadTile(j, paramAnonymousInt3);
        i += mTileSize;
      }
    }
    
    public void loadTile(int paramAnonymousInt1, int paramAnonymousInt2)
    {
      if (isTileLoaded(paramAnonymousInt1)) {
        return;
      }
      TileList.Tile localTile = acquireTile();
      mStartPosition = paramAnonymousInt1;
      mItemCount = Math.min(mTileSize, mItemCount - mStartPosition);
      mDataCallback.fillData(mItems, mStartPosition, mItemCount);
      flushTileCache(paramAnonymousInt2);
      addTile(localTile);
    }
    
    public void recycleTile(TileList.Tile paramAnonymousTile)
    {
      mDataCallback.recycleData(mItems, mItemCount);
      mNext = mRecycledRoot;
      mRecycledRoot = paramAnonymousTile;
    }
    
    public void refresh(int paramAnonymousInt)
    {
      mGeneration = paramAnonymousInt;
      mLoadedTiles.clear();
      mItemCount = mDataCallback.refreshData();
      mMainThreadProxy.updateItemCount(mGeneration, mItemCount);
    }
    
    public void updateRange(int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, int paramAnonymousInt4, int paramAnonymousInt5)
    {
      if (paramAnonymousInt1 > paramAnonymousInt2) {
        return;
      }
      paramAnonymousInt1 = getTileStart(paramAnonymousInt1);
      paramAnonymousInt2 = getTileStart(paramAnonymousInt2);
      mFirstRequiredTileStart = getTileStart(paramAnonymousInt3);
      mLastRequiredTileStart = getTileStart(paramAnonymousInt4);
      if (paramAnonymousInt5 == 1)
      {
        requestTiles(mFirstRequiredTileStart, paramAnonymousInt2, paramAnonymousInt5, true);
        requestTiles(paramAnonymousInt2 + mTileSize, mLastRequiredTileStart, paramAnonymousInt5, false);
        return;
      }
      requestTiles(paramAnonymousInt1, mLastRequiredTileStart, paramAnonymousInt5, false);
      requestTiles(mFirstRequiredTileStart, paramAnonymousInt1 - mTileSize, paramAnonymousInt5, true);
    }
  };
  final ThreadUtil.BackgroundCallback<T> mBackgroundProxy;
  final DataCallback<T> mDataCallback;
  int mDisplayedGeneration = 0;
  int mItemCount = 0;
  private final ThreadUtil.MainThreadCallback<T> mMainThreadCallback = new ThreadUtil.MainThreadCallback()
  {
    private boolean isRequestedGeneration(int paramAnonymousInt)
    {
      return paramAnonymousInt == mRequestedGeneration;
    }
    
    private void recycleAllTiles()
    {
      int i = 0;
      while (i < mTileList.size())
      {
        mBackgroundProxy.recycleTile(mTileList.getAtIndex(i));
        i += 1;
      }
      mTileList.clear();
    }
    
    public void addTile(int paramAnonymousInt, TileList.Tile paramAnonymousTile)
    {
      if (!isRequestedGeneration(paramAnonymousInt))
      {
        mBackgroundProxy.recycleTile(paramAnonymousTile);
        return;
      }
      TileList.Tile localTile = mTileList.addOrReplace(paramAnonymousTile);
      if (localTile != null)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("duplicate tile @");
        localStringBuilder.append(mStartPosition);
        Log.e("AsyncListUtil", localStringBuilder.toString());
        mBackgroundProxy.recycleTile(localTile);
      }
      int i = mStartPosition;
      int j = mItemCount;
      paramAnonymousInt = 0;
      while (paramAnonymousInt < mMissingPositions.size())
      {
        int k = mMissingPositions.keyAt(paramAnonymousInt);
        if ((mStartPosition <= k) && (k < i + j))
        {
          mMissingPositions.removeAt(paramAnonymousInt);
          mViewCallback.onItemLoaded(k);
        }
        else
        {
          paramAnonymousInt += 1;
        }
      }
    }
    
    public void removeTile(int paramAnonymousInt1, int paramAnonymousInt2)
    {
      if (!isRequestedGeneration(paramAnonymousInt1)) {
        return;
      }
      Object localObject = mTileList.removeAtPos(paramAnonymousInt2);
      if (localObject == null)
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("tile not found @");
        ((StringBuilder)localObject).append(paramAnonymousInt2);
        Log.e("AsyncListUtil", ((StringBuilder)localObject).toString());
        return;
      }
      mBackgroundProxy.recycleTile((TileList.Tile)localObject);
    }
    
    public void updateItemCount(int paramAnonymousInt1, int paramAnonymousInt2)
    {
      if (!isRequestedGeneration(paramAnonymousInt1)) {
        return;
      }
      mItemCount = paramAnonymousInt2;
      mViewCallback.onDataRefresh();
      mDisplayedGeneration = mRequestedGeneration;
      recycleAllTiles();
      mAllowScrollHints = false;
      updateRange();
    }
  };
  final ThreadUtil.MainThreadCallback<T> mMainThreadProxy;
  final SparseIntArray mMissingPositions = new SparseIntArray();
  final int[] mPrevRange = new int[2];
  int mRequestedGeneration = mDisplayedGeneration;
  private int mScrollHint = 0;
  final Class<T> mTClass;
  final TileList<T> mTileList;
  final int mTileSize;
  final int[] mTmpRange = new int[2];
  final int[] mTmpRangeExtended = new int[2];
  final ViewCallback mViewCallback;
  
  public AsyncListUtil(Class paramClass, int paramInt, DataCallback paramDataCallback, ViewCallback paramViewCallback)
  {
    mTClass = paramClass;
    mTileSize = paramInt;
    mDataCallback = paramDataCallback;
    mViewCallback = paramViewCallback;
    mTileList = new TileList(mTileSize);
    paramClass = new MessageThreadUtil();
    mMainThreadProxy = paramClass.getMainThreadProxy(mMainThreadCallback);
    mBackgroundProxy = paramClass.getBackgroundProxy(mBackgroundCallback);
    refresh();
  }
  
  private boolean isRefreshPending()
  {
    return mRequestedGeneration != mDisplayedGeneration;
  }
  
  public Object getItem(int paramInt)
  {
    Object localObject;
    if ((paramInt >= 0) && (paramInt < mItemCount))
    {
      localObject = mTileList.getItemAt(paramInt);
      if ((localObject == null) && (!isRefreshPending()))
      {
        mMissingPositions.put(paramInt, 0);
        return localObject;
      }
    }
    else
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramInt);
      ((StringBuilder)localObject).append(" is not within 0 and ");
      ((StringBuilder)localObject).append(mItemCount);
      throw new IndexOutOfBoundsException(((StringBuilder)localObject).toString());
    }
    return localObject;
  }
  
  public int getItemCount()
  {
    return mItemCount;
  }
  
  void log(String paramString, Object... paramVarArgs)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[MAIN] ");
    localStringBuilder.append(String.format(paramString, paramVarArgs));
    Log.d("AsyncListUtil", localStringBuilder.toString());
  }
  
  public void onRangeChanged()
  {
    if (isRefreshPending()) {
      return;
    }
    updateRange();
    mAllowScrollHints = true;
  }
  
  public void refresh()
  {
    mMissingPositions.clear();
    ThreadUtil.BackgroundCallback localBackgroundCallback = mBackgroundProxy;
    int i = mRequestedGeneration + 1;
    mRequestedGeneration = i;
    localBackgroundCallback.refresh(i);
  }
  
  void updateRange()
  {
    mViewCallback.getItemRangeInto(mTmpRange);
    if (mTmpRange[0] <= mTmpRange[1])
    {
      if (mTmpRange[0] < 0) {
        return;
      }
      if (mTmpRange[1] >= mItemCount) {
        return;
      }
      if (!mAllowScrollHints) {
        mScrollHint = 0;
      } else if ((mTmpRange[0] <= mPrevRange[1]) && (mPrevRange[0] <= mTmpRange[1]))
      {
        if (mTmpRange[0] < mPrevRange[0]) {
          mScrollHint = 1;
        } else if (mTmpRange[0] > mPrevRange[0]) {
          mScrollHint = 2;
        }
      }
      else {
        mScrollHint = 0;
      }
      mPrevRange[0] = mTmpRange[0];
      mPrevRange[1] = mTmpRange[1];
      mViewCallback.extendRangeInto(mTmpRange, mTmpRangeExtended, mScrollHint);
      mTmpRangeExtended[0] = Math.min(mTmpRange[0], Math.max(mTmpRangeExtended[0], 0));
      mTmpRangeExtended[1] = Math.max(mTmpRange[1], Math.min(mTmpRangeExtended[1], mItemCount - 1));
      mBackgroundProxy.updateRange(mTmpRange[0], mTmpRange[1], mTmpRangeExtended[0], mTmpRangeExtended[1], mScrollHint);
    }
  }
  
  public static abstract class DataCallback<T>
  {
    public DataCallback() {}
    
    public abstract void fillData(Object[] paramArrayOfObject, int paramInt1, int paramInt2);
    
    public int getMaxCachedTiles()
    {
      return 10;
    }
    
    public void recycleData(Object[] paramArrayOfObject, int paramInt) {}
    
    public abstract int refreshData();
  }
  
  public static abstract class ViewCallback
  {
    public static final int HINT_SCROLL_ASC = 2;
    public static final int HINT_SCROLL_DESC = 1;
    public static final int HINT_SCROLL_NONE = 0;
    
    public ViewCallback() {}
    
    public void extendRangeInto(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt)
    {
      int i = paramArrayOfInt1[1] - paramArrayOfInt1[0] + 1;
      int j = i / 2;
      int m = paramArrayOfInt1[0];
      if (paramInt == 1) {
        k = i;
      } else {
        k = j;
      }
      paramArrayOfInt2[0] = (m - k);
      int k = paramArrayOfInt1[1];
      if (paramInt != 2) {
        i = j;
      }
      paramArrayOfInt2[1] = (k + i);
    }
    
    public abstract void getItemRangeInto(int[] paramArrayOfInt);
    
    public abstract void onDataRefresh();
    
    public abstract void onItemLoaded(int paramInt);
  }
}
