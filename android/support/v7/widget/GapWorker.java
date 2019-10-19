package android.support.v7.widget;

import android.support.v4.os.TraceCompat;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

final class GapWorker
  implements Runnable
{
  static final ThreadLocal<GapWorker> sGapWorker = new ThreadLocal();
  static Comparator<Task> sTaskComparator = new Comparator()
  {
    public int compare(GapWorker.Task paramAnonymousTask1, GapWorker.Task paramAnonymousTask2)
    {
      int i;
      if (view == null) {
        i = 1;
      } else {
        i = 0;
      }
      int j;
      if (view == null) {
        j = 1;
      } else {
        j = 0;
      }
      if (i != j)
      {
        if (view == null) {
          return 1;
        }
        return -1;
      }
      if (immediate != immediate)
      {
        if (immediate) {
          return -1;
        }
      }
      else
      {
        i = viewVelocity - viewVelocity;
        if (i != 0) {
          return i;
        }
        i = distanceToItem - distanceToItem;
        if (i != 0) {
          return i;
        }
        return 0;
      }
      return 1;
    }
  };
  long mFrameIntervalNs;
  long mPostTimeNs;
  ArrayList<RecyclerView> mRecyclerViews = new ArrayList();
  private ArrayList<Task> mTasks = new ArrayList();
  
  GapWorker() {}
  
  private void buildTaskList()
  {
    Object localObject2 = mRecyclerViews;
    Object localObject1 = this;
    int n = ((ArrayList)localObject2).size();
    int i = 0;
    int k;
    for (int j = 0; i < n; j = k)
    {
      localObject2 = mRecyclerViews;
      localObject2 = (RecyclerView)((ArrayList)localObject2).get(i);
      k = j;
      if (((View)localObject2).getWindowVisibility() == 0)
      {
        mPrefetchRegistry.collectPrefetchPositionsFromView((RecyclerView)localObject2, false);
        k = j + mPrefetchRegistry.mCount;
      }
      i += 1;
    }
    localObject2 = mTasks;
    ((ArrayList)localObject2).ensureCapacity(j);
    j = 0;
    i = 0;
    while (j < n)
    {
      localObject2 = mRecyclerViews;
      RecyclerView localRecyclerView = (RecyclerView)((ArrayList)localObject2).get(j);
      int m;
      if (localRecyclerView.getWindowVisibility() != 0)
      {
        m = i;
        localObject2 = localObject1;
      }
      else
      {
        LayoutPrefetchRegistryImpl localLayoutPrefetchRegistryImpl = mPrefetchRegistry;
        int i1 = Math.abs(mPrefetchDx) + Math.abs(mPrefetchDy);
        k = 0;
        for (;;)
        {
          m = i;
          localObject2 = localObject1;
          if (k >= mCount * 2) {
            break;
          }
          if (i >= mTasks.size())
          {
            localObject2 = new Task();
            mTasks.add(localObject2);
          }
          else
          {
            localObject2 = (Task)mTasks.get(i);
          }
          m = mPrefetchArray[(k + 1)];
          boolean bool;
          if (m <= i1) {
            bool = true;
          } else {
            bool = false;
          }
          immediate = bool;
          viewVelocity = i1;
          distanceToItem = m;
          view = localRecyclerView;
          position = mPrefetchArray[k];
          i += 1;
          k += 2;
        }
      }
      j += 1;
      i = m;
      localObject1 = localObject2;
    }
    Collections.sort(mTasks, sTaskComparator);
  }
  
  private void flushTaskWithDeadline(Task paramTask, long paramLong)
  {
    long l;
    if (immediate) {
      l = Long.MAX_VALUE;
    } else {
      l = paramLong;
    }
    paramTask = prefetchPositionWithDeadline(view, position, l);
    if ((paramTask != null) && (mNestedRecyclerView != null) && (paramTask.isBound()) && (!paramTask.isInvalid())) {
      prefetchInnerRecyclerViewWithDeadline((RecyclerView)mNestedRecyclerView.get(), paramLong);
    }
  }
  
  private void flushTasksWithDeadline(long paramLong)
  {
    int i = 0;
    while (i < mTasks.size())
    {
      Task localTask = (Task)mTasks.get(i);
      if (view == null) {
        return;
      }
      flushTaskWithDeadline(localTask, paramLong);
      localTask.clear();
      i += 1;
    }
  }
  
  static boolean isPrefetchPositionAttached(RecyclerView paramRecyclerView, int paramInt)
  {
    int j = mChildHelper.getUnfilteredChildCount();
    int i = 0;
    while (i < j)
    {
      RecyclerView.ViewHolder localViewHolder = RecyclerView.getChildViewHolderInt(mChildHelper.getUnfilteredChildAt(i));
      if ((mPosition == paramInt) && (!localViewHolder.isInvalid())) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  private void prefetchInnerRecyclerViewWithDeadline(RecyclerView paramRecyclerView, long paramLong)
  {
    if (paramRecyclerView == null) {
      return;
    }
    if ((mDataSetHasChangedAfterLayout) && (mChildHelper.getUnfilteredChildCount() != 0)) {
      paramRecyclerView.removeAndRecycleViews();
    }
    LayoutPrefetchRegistryImpl localLayoutPrefetchRegistryImpl = mPrefetchRegistry;
    localLayoutPrefetchRegistryImpl.collectPrefetchPositionsFromView(paramRecyclerView, true);
    if (mCount != 0) {
      try
      {
        TraceCompat.beginSection("RV Nested Prefetch");
        mState.prepareForNestedPrefetch(mAdapter);
        int i = 0;
        for (;;)
        {
          int j = mCount;
          if (i >= j * 2) {
            break;
          }
          prefetchPositionWithDeadline(paramRecyclerView, mPrefetchArray[i], paramLong);
          i += 2;
        }
        TraceCompat.endSection();
        return;
      }
      catch (Throwable paramRecyclerView)
      {
        TraceCompat.endSection();
        throw paramRecyclerView;
      }
    }
  }
  
  private RecyclerView.ViewHolder prefetchPositionWithDeadline(RecyclerView paramRecyclerView, int paramInt, long paramLong)
  {
    if (isPrefetchPositionAttached(paramRecyclerView, paramInt)) {
      return null;
    }
    RecyclerView.Recycler localRecycler = mRecycler;
    try
    {
      paramRecyclerView.onEnterLayoutOrScroll();
      RecyclerView.ViewHolder localViewHolder = localRecycler.tryGetViewHolderForPositionByDeadline(paramInt, false, paramLong);
      if (localViewHolder != null)
      {
        boolean bool = localViewHolder.isBound();
        if (bool)
        {
          bool = localViewHolder.isInvalid();
          if (!bool)
          {
            localRecycler.recycleView(itemView);
            break label80;
          }
        }
        localRecycler.addViewHolderToRecycledViewPool(localViewHolder, false);
      }
      label80:
      paramRecyclerView.onExitLayoutOrScroll(false);
      return localViewHolder;
    }
    catch (Throwable localThrowable)
    {
      paramRecyclerView.onExitLayoutOrScroll(false);
      throw localThrowable;
    }
  }
  
  public void addCallback(RecyclerView paramRecyclerView)
  {
    mRecyclerViews.add(paramRecyclerView);
  }
  
  void postFromTraversal(RecyclerView paramRecyclerView, int paramInt1, int paramInt2)
  {
    if ((paramRecyclerView.isAttachedToWindow()) && (mPostTimeNs == 0L))
    {
      mPostTimeNs = paramRecyclerView.getNanoTime();
      paramRecyclerView.post(this);
    }
    mPrefetchRegistry.setPrefetchVector(paramInt1, paramInt2);
  }
  
  void prefetch(long paramLong)
  {
    buildTaskList();
    flushTasksWithDeadline(paramLong);
  }
  
  public void remove(RecyclerView paramRecyclerView)
  {
    mRecyclerViews.remove(paramRecyclerView);
  }
  
  public void run()
  {
    try
    {
      TraceCompat.beginSection("RV Prefetch");
      boolean bool = mRecyclerViews.isEmpty();
      if (bool)
      {
        mPostTimeNs = 0L;
        TraceCompat.endSection();
        return;
      }
      int j = mRecyclerViews.size();
      int i = 0;
      for (long l1 = 0L; i < j; l1 = l2)
      {
        RecyclerView localRecyclerView = (RecyclerView)mRecyclerViews.get(i);
        int k = localRecyclerView.getWindowVisibility();
        l2 = l1;
        if (k == 0) {
          l2 = Math.max(localRecyclerView.getDrawingTime(), l1);
        }
        i += 1;
      }
      if (l1 == 0L)
      {
        mPostTimeNs = 0L;
        TraceCompat.endSection();
        return;
      }
      l1 = TimeUnit.MILLISECONDS.toNanos(l1);
      long l2 = mFrameIntervalNs;
      prefetch(l1 + l2);
      mPostTimeNs = 0L;
      TraceCompat.endSection();
      return;
    }
    catch (Throwable localThrowable)
    {
      mPostTimeNs = 0L;
      TraceCompat.endSection();
      throw localThrowable;
    }
  }
  
  static class LayoutPrefetchRegistryImpl
    implements RecyclerView.LayoutManager.LayoutPrefetchRegistry
  {
    int mCount;
    int[] mPrefetchArray;
    int mPrefetchDx;
    int mPrefetchDy;
    
    LayoutPrefetchRegistryImpl() {}
    
    public void addPosition(int paramInt1, int paramInt2)
    {
      if (paramInt1 >= 0)
      {
        if (paramInt2 >= 0)
        {
          int i = mCount * 2;
          if (mPrefetchArray == null)
          {
            mPrefetchArray = new int[4];
            Arrays.fill(mPrefetchArray, -1);
          }
          else if (i >= mPrefetchArray.length)
          {
            int[] arrayOfInt = mPrefetchArray;
            mPrefetchArray = new int[i * 2];
            System.arraycopy(arrayOfInt, 0, mPrefetchArray, 0, arrayOfInt.length);
          }
          mPrefetchArray[i] = paramInt1;
          mPrefetchArray[(i + 1)] = paramInt2;
          mCount += 1;
          return;
        }
        throw new IllegalArgumentException("Pixel distance must be non-negative");
      }
      throw new IllegalArgumentException("Layout positions must be non-negative");
    }
    
    void clearPrefetchPositions()
    {
      if (mPrefetchArray != null) {
        Arrays.fill(mPrefetchArray, -1);
      }
      mCount = 0;
    }
    
    void collectPrefetchPositionsFromView(RecyclerView paramRecyclerView, boolean paramBoolean)
    {
      mCount = 0;
      if (mPrefetchArray != null) {
        Arrays.fill(mPrefetchArray, -1);
      }
      RecyclerView.LayoutManager localLayoutManager = mLayout;
      if ((mAdapter != null) && (localLayoutManager != null) && (localLayoutManager.isItemPrefetchEnabled()))
      {
        if (paramBoolean)
        {
          if (!mAdapterHelper.hasPendingUpdates()) {
            localLayoutManager.collectInitialPrefetchPositions(mAdapter.getItemCount(), this);
          }
        }
        else if (!paramRecyclerView.hasPendingAdapterUpdates()) {
          localLayoutManager.collectAdjacentPrefetchPositions(mPrefetchDx, mPrefetchDy, mState, this);
        }
        if (mCount > mPrefetchMaxCountObserved)
        {
          mPrefetchMaxCountObserved = mCount;
          mPrefetchMaxObservedInInitialPrefetch = paramBoolean;
          mRecycler.updateViewCacheSize();
        }
      }
    }
    
    boolean lastPrefetchIncludedPosition(int paramInt)
    {
      if (mPrefetchArray != null)
      {
        int j = mCount;
        int i = 0;
        while (i < j * 2)
        {
          if (mPrefetchArray[i] == paramInt) {
            return true;
          }
          i += 2;
        }
      }
      return false;
    }
    
    void setPrefetchVector(int paramInt1, int paramInt2)
    {
      mPrefetchDx = paramInt1;
      mPrefetchDy = paramInt2;
    }
  }
  
  static class Task
  {
    public int distanceToItem;
    public boolean immediate;
    public int position;
    public RecyclerView view;
    public int viewVelocity;
    
    Task() {}
    
    public void clear()
    {
      immediate = false;
      viewVelocity = 0;
      distanceToItem = 0;
      view = null;
      position = 0;
    }
  }
}
