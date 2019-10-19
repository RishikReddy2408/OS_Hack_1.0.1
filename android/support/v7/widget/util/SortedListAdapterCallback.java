package android.support.v7.widget.util;

import android.support.v7.util.SortedList.Callback;
import android.support.v7.widget.RecyclerView.Adapter;

public abstract class SortedListAdapterCallback<T2>
  extends SortedList.Callback<T2>
{
  final RecyclerView.Adapter mAdapter;
  
  public SortedListAdapterCallback(RecyclerView.Adapter paramAdapter)
  {
    mAdapter = paramAdapter;
  }
  
  public void onChanged(int paramInt1, int paramInt2)
  {
    mAdapter.notifyItemRangeChanged(paramInt1, paramInt2);
  }
  
  public void onChanged(int paramInt1, int paramInt2, Object paramObject)
  {
    mAdapter.notifyItemRangeChanged(paramInt1, paramInt2, paramObject);
  }
  
  public void onInserted(int paramInt1, int paramInt2)
  {
    mAdapter.notifyItemRangeInserted(paramInt1, paramInt2);
  }
  
  public void onMoved(int paramInt1, int paramInt2)
  {
    mAdapter.notifyItemMoved(paramInt1, paramInt2);
  }
  
  public void onRemoved(int paramInt1, int paramInt2)
  {
    mAdapter.notifyItemRangeRemoved(paramInt1, paramInt2);
  }
}
