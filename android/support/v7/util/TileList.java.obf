package android.support.v7.util;

import android.util.SparseArray;
import java.lang.reflect.Array;

class TileList<T>
{
  Tile<T> mLastAccessedTile;
  final int mTileSize;
  private final SparseArray<Tile<T>> mTiles = new SparseArray(10);
  
  public TileList(int paramInt)
  {
    mTileSize = paramInt;
  }
  
  public Tile<T> addOrReplace(Tile<T> paramTile)
  {
    int i = mTiles.indexOfKey(mStartPosition);
    if (i < 0)
    {
      mTiles.put(mStartPosition, paramTile);
      return null;
    }
    Tile localTile = (Tile)mTiles.valueAt(i);
    mTiles.setValueAt(i, paramTile);
    if (mLastAccessedTile == localTile) {
      mLastAccessedTile = paramTile;
    }
    return localTile;
  }
  
  public void clear()
  {
    mTiles.clear();
  }
  
  public Tile<T> getAtIndex(int paramInt)
  {
    return (Tile)mTiles.valueAt(paramInt);
  }
  
  public T getItemAt(int paramInt)
  {
    if ((mLastAccessedTile == null) || (!mLastAccessedTile.containsPosition(paramInt)))
    {
      int i = mTileSize;
      i = mTiles.indexOfKey(paramInt - paramInt % i);
      if (i < 0) {
        return null;
      }
      mLastAccessedTile = ((Tile)mTiles.valueAt(i));
    }
    return mLastAccessedTile.getByPosition(paramInt);
  }
  
  public Tile<T> removeAtPos(int paramInt)
  {
    Tile localTile = (Tile)mTiles.get(paramInt);
    if (mLastAccessedTile == localTile) {
      mLastAccessedTile = null;
    }
    mTiles.delete(paramInt);
    return localTile;
  }
  
  public int size()
  {
    return mTiles.size();
  }
  
  public static class Tile<T>
  {
    public int mItemCount;
    public final T[] mItems;
    Tile<T> mNext;
    public int mStartPosition;
    
    public Tile(Class<T> paramClass, int paramInt)
    {
      mItems = ((Object[])Array.newInstance(paramClass, paramInt));
    }
    
    boolean containsPosition(int paramInt)
    {
      return (mStartPosition <= paramInt) && (paramInt < mStartPosition + mItemCount);
    }
    
    T getByPosition(int paramInt)
    {
      return mItems[(paramInt - mStartPosition)];
    }
  }
}
