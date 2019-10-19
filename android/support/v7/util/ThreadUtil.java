package android.support.v7.util;

abstract interface ThreadUtil<T>
{
  public abstract BackgroundCallback getBackgroundProxy(BackgroundCallback paramBackgroundCallback);
  
  public abstract MainThreadCallback getMainThreadProxy(MainThreadCallback paramMainThreadCallback);
  
  public static abstract interface BackgroundCallback<T>
  {
    public abstract void loadTile(int paramInt1, int paramInt2);
    
    public abstract void recycleTile(TileList.Tile paramTile);
    
    public abstract void refresh(int paramInt);
    
    public abstract void updateRange(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5);
  }
  
  public static abstract interface MainThreadCallback<T>
  {
    public abstract void addTile(int paramInt, TileList.Tile paramTile);
    
    public abstract void removeTile(int paramInt1, int paramInt2);
    
    public abstract void updateItemCount(int paramInt1, int paramInt2);
  }
}
