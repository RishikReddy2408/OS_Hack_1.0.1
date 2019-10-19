package android.support.v7.recyclerview.extensions;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.AdapterListUpdateCallback;
import android.support.v7.util.DiffUtil;
import android.support.v7.util.DiffUtil.Callback;
import android.support.v7.util.DiffUtil.DiffResult;
import android.support.v7.util.DiffUtil.ItemCallback;
import android.support.v7.util.ListUpdateCallback;
import android.support.v7.widget.RecyclerView.Adapter;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;

public class AsyncListDiffer<T>
{
  private final AsyncDifferConfig<T> mConfig;
  @Nullable
  private List<T> mList;
  private int mMaxScheduledGeneration;
  @NonNull
  private List<T> mReadOnlyList = Collections.emptyList();
  private final ListUpdateCallback mUpdateCallback;
  
  public AsyncListDiffer(ListUpdateCallback paramListUpdateCallback, AsyncDifferConfig paramAsyncDifferConfig)
  {
    mUpdateCallback = paramListUpdateCallback;
    mConfig = paramAsyncDifferConfig;
  }
  
  public AsyncListDiffer(RecyclerView.Adapter paramAdapter, DiffUtil.ItemCallback paramItemCallback)
  {
    mUpdateCallback = new AdapterListUpdateCallback(paramAdapter);
    mConfig = new AsyncDifferConfig.Builder(paramItemCallback).build();
  }
  
  private void latchList(List paramList, DiffUtil.DiffResult paramDiffResult)
  {
    mList = paramList;
    mReadOnlyList = Collections.unmodifiableList(paramList);
    paramDiffResult.dispatchUpdatesTo(mUpdateCallback);
  }
  
  public List getCurrentList()
  {
    return mReadOnlyList;
  }
  
  public void submitList(final List paramList)
  {
    if (paramList == mList) {
      return;
    }
    final int i = mMaxScheduledGeneration + 1;
    mMaxScheduledGeneration = i;
    if (paramList == null)
    {
      i = mList.size();
      mList = null;
      mReadOnlyList = Collections.emptyList();
      mUpdateCallback.onRemoved(0, i);
      return;
    }
    if (mList == null)
    {
      mList = paramList;
      mReadOnlyList = Collections.unmodifiableList(paramList);
      mUpdateCallback.onInserted(0, paramList.size());
      return;
    }
    final List localList = mList;
    mConfig.getBackgroundThreadExecutor().execute(new Runnable()
    {
      public void run()
      {
        final DiffUtil.DiffResult localDiffResult = DiffUtil.calculateDiff(new DiffUtil.Callback()
        {
          public boolean areContentsTheSame(int paramAnonymous2Int1, int paramAnonymous2Int2)
          {
            return mConfig.getDiffCallback().areContentsTheSame(val$oldList.get(paramAnonymous2Int1), val$newList.get(paramAnonymous2Int2));
          }
          
          public boolean areItemsTheSame(int paramAnonymous2Int1, int paramAnonymous2Int2)
          {
            return mConfig.getDiffCallback().areItemsTheSame(val$oldList.get(paramAnonymous2Int1), val$newList.get(paramAnonymous2Int2));
          }
          
          public Object getChangePayload(int paramAnonymous2Int1, int paramAnonymous2Int2)
          {
            return mConfig.getDiffCallback().getChangePayload(val$oldList.get(paramAnonymous2Int1), val$newList.get(paramAnonymous2Int2));
          }
          
          public int getNewListSize()
          {
            return val$newList.size();
          }
          
          public int getOldListSize()
          {
            return val$oldList.size();
          }
        });
        mConfig.getMainThreadExecutor().execute(new Runnable()
        {
          public void run()
          {
            if (mMaxScheduledGeneration == val$runGeneration) {
              AsyncListDiffer.this.latchList(val$newList, localDiffResult);
            }
          }
        });
      }
    });
  }
}
