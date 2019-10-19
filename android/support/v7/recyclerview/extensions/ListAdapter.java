package android.support.v7.recyclerview.extensions;

import android.support.v7.util.AdapterListUpdateCallback;
import android.support.v7.util.DiffUtil.ItemCallback;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import java.util.List;

public abstract class ListAdapter<T, VH extends RecyclerView.ViewHolder>
  extends RecyclerView.Adapter<VH>
{
  private final AsyncListDiffer<T> mHelper;
  
  protected ListAdapter(AsyncDifferConfig paramAsyncDifferConfig)
  {
    mHelper = new AsyncListDiffer(new AdapterListUpdateCallback(this), paramAsyncDifferConfig);
  }
  
  protected ListAdapter(DiffUtil.ItemCallback paramItemCallback)
  {
    mHelper = new AsyncListDiffer(new AdapterListUpdateCallback(this), new AsyncDifferConfig.Builder(paramItemCallback).build());
  }
  
  protected Object getItem(int paramInt)
  {
    return mHelper.getCurrentList().get(paramInt);
  }
  
  public int getItemCount()
  {
    return mHelper.getCurrentList().size();
  }
  
  public void submitList(List paramList)
  {
    mHelper.submitList(paramList);
  }
}
