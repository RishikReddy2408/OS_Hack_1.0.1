package android.support.v7.widget;

import android.content.Context;
import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.RestrictTo;
import android.support.v7.widget.helper.ItemTouchHelper.ViewDropHandler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityRecord;
import java.util.List;

public class LinearLayoutManager
  extends RecyclerView.LayoutManager
  implements ItemTouchHelper.ViewDropHandler, RecyclerView.SmoothScroller.ScrollVectorProvider
{
  static final boolean DEBUG = false;
  public static final int HORIZONTAL = 0;
  public static final int INVALID_OFFSET = Integer.MIN_VALUE;
  private static final float MAX_SCROLL_FACTOR = 0.33333334F;
  private static final String TAG = "LinearLayoutManager";
  public static final int VERTICAL = 1;
  final AnchorInfo mAnchorInfo = new AnchorInfo();
  private int mInitialPrefetchItemCount = 2;
  private boolean mLastStackFromEnd;
  private final LayoutChunkResult mLayoutChunkResult = new LayoutChunkResult();
  private LayoutState mLayoutState;
  int mOrientation = 1;
  OrientationHelper mOrientationHelper;
  SavedState mPendingSavedState = null;
  int mPendingScrollPosition = -1;
  int mPendingScrollPositionOffset = Integer.MIN_VALUE;
  private boolean mRecycleChildrenOnDetach;
  private boolean mReverseLayout = false;
  boolean mShouldReverseLayout = false;
  private boolean mSmoothScrollbarEnabled = true;
  private boolean mStackFromEnd = false;
  
  public LinearLayoutManager(Context paramContext)
  {
    this(paramContext, 1, false);
  }
  
  public LinearLayoutManager(Context paramContext, int paramInt, boolean paramBoolean)
  {
    setOrientation(paramInt);
    setReverseLayout(paramBoolean);
  }
  
  public LinearLayoutManager(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    paramContext = RecyclerView.LayoutManager.getProperties(paramContext, paramAttributeSet, paramInt1, paramInt2);
    setOrientation(orientation);
    setReverseLayout(reverseLayout);
    setStackFromEnd(stackFromEnd);
  }
  
  private int computeScrollExtent(RecyclerView.State paramState)
  {
    if (getChildCount() == 0) {
      return 0;
    }
    ensureLayoutState();
    return ScrollbarHelper.computeScrollExtent(paramState, mOrientationHelper, findFirstVisibleChildClosestToStart(mSmoothScrollbarEnabled ^ true, true), findFirstVisibleChildClosestToEnd(mSmoothScrollbarEnabled ^ true, true), this, mSmoothScrollbarEnabled);
  }
  
  private int computeScrollOffset(RecyclerView.State paramState)
  {
    if (getChildCount() == 0) {
      return 0;
    }
    ensureLayoutState();
    return ScrollbarHelper.computeScrollOffset(paramState, mOrientationHelper, findFirstVisibleChildClosestToStart(mSmoothScrollbarEnabled ^ true, true), findFirstVisibleChildClosestToEnd(mSmoothScrollbarEnabled ^ true, true), this, mSmoothScrollbarEnabled, mShouldReverseLayout);
  }
  
  private int computeScrollRange(RecyclerView.State paramState)
  {
    if (getChildCount() == 0) {
      return 0;
    }
    ensureLayoutState();
    return ScrollbarHelper.computeScrollRange(paramState, mOrientationHelper, findFirstVisibleChildClosestToStart(mSmoothScrollbarEnabled ^ true, true), findFirstVisibleChildClosestToEnd(mSmoothScrollbarEnabled ^ true, true), this, mSmoothScrollbarEnabled);
  }
  
  private View findFirstPartiallyOrCompletelyInvisibleChild(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState)
  {
    return findOnePartiallyOrCompletelyInvisibleChild(0, getChildCount());
  }
  
  private View findFirstReferenceChild(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState)
  {
    return findReferenceChild(paramRecycler, paramState, 0, getChildCount(), paramState.getItemCount());
  }
  
  private View findFirstVisibleChildClosestToEnd(boolean paramBoolean1, boolean paramBoolean2)
  {
    if (mShouldReverseLayout) {
      return findOneVisibleChild(0, getChildCount(), paramBoolean1, paramBoolean2);
    }
    return findOneVisibleChild(getChildCount() - 1, -1, paramBoolean1, paramBoolean2);
  }
  
  private View findFirstVisibleChildClosestToStart(boolean paramBoolean1, boolean paramBoolean2)
  {
    if (mShouldReverseLayout) {
      return findOneVisibleChild(getChildCount() - 1, -1, paramBoolean1, paramBoolean2);
    }
    return findOneVisibleChild(0, getChildCount(), paramBoolean1, paramBoolean2);
  }
  
  private View findLastPartiallyOrCompletelyInvisibleChild(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState)
  {
    return findOnePartiallyOrCompletelyInvisibleChild(getChildCount() - 1, -1);
  }
  
  private View findLastReferenceChild(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState)
  {
    return findReferenceChild(paramRecycler, paramState, getChildCount() - 1, -1, paramState.getItemCount());
  }
  
  private View findPartiallyOrCompletelyInvisibleChildClosestToEnd(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState)
  {
    if (mShouldReverseLayout) {
      return findFirstPartiallyOrCompletelyInvisibleChild(paramRecycler, paramState);
    }
    return findLastPartiallyOrCompletelyInvisibleChild(paramRecycler, paramState);
  }
  
  private View findPartiallyOrCompletelyInvisibleChildClosestToStart(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState)
  {
    if (mShouldReverseLayout) {
      return findLastPartiallyOrCompletelyInvisibleChild(paramRecycler, paramState);
    }
    return findFirstPartiallyOrCompletelyInvisibleChild(paramRecycler, paramState);
  }
  
  private View findReferenceChildClosestToEnd(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState)
  {
    if (mShouldReverseLayout) {
      return findFirstReferenceChild(paramRecycler, paramState);
    }
    return findLastReferenceChild(paramRecycler, paramState);
  }
  
  private View findReferenceChildClosestToStart(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState)
  {
    if (mShouldReverseLayout) {
      return findLastReferenceChild(paramRecycler, paramState);
    }
    return findFirstReferenceChild(paramRecycler, paramState);
  }
  
  private int fixLayoutEndGap(int paramInt, RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, boolean paramBoolean)
  {
    int i = mOrientationHelper.getEndAfterPadding() - paramInt;
    if (i > 0)
    {
      i = -scrollBy(-i, paramRecycler, paramState);
      if (paramBoolean)
      {
        paramInt = mOrientationHelper.getEndAfterPadding() - (paramInt + i);
        if (paramInt > 0)
        {
          mOrientationHelper.offsetChildren(paramInt);
          return paramInt + i;
        }
      }
      else
      {
        return i;
      }
    }
    else
    {
      return 0;
    }
    return i;
  }
  
  private int fixLayoutStartGap(int paramInt, RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, boolean paramBoolean)
  {
    int i = paramInt - mOrientationHelper.getStartAfterPadding();
    if (i > 0)
    {
      i = -scrollBy(i, paramRecycler, paramState);
      if (paramBoolean)
      {
        paramInt = paramInt + i - mOrientationHelper.getStartAfterPadding();
        if (paramInt > 0)
        {
          mOrientationHelper.offsetChildren(-paramInt);
          return i - paramInt;
        }
      }
      else
      {
        return i;
      }
    }
    else
    {
      return 0;
    }
    return i;
  }
  
  private View getChildClosestToEnd()
  {
    int i;
    if (mShouldReverseLayout) {
      i = 0;
    } else {
      i = getChildCount() - 1;
    }
    return getChildAt(i);
  }
  
  private View getChildClosestToStart()
  {
    int i;
    if (mShouldReverseLayout) {
      i = getChildCount() - 1;
    } else {
      i = 0;
    }
    return getChildAt(i);
  }
  
  private void layoutForPredictiveAnimations(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, int paramInt1, int paramInt2)
  {
    if ((paramState.willRunPredictiveAnimations()) && (getChildCount() != 0) && (!paramState.isPreLayout()))
    {
      if (!supportsPredictiveItemAnimations()) {
        return;
      }
      List localList = paramRecycler.getScrapList();
      int n = localList.size();
      int i1 = getPosition(getChildAt(0));
      int i = 0;
      int j = 0;
      int k = 0;
      while (i < n)
      {
        RecyclerView.ViewHolder localViewHolder = (RecyclerView.ViewHolder)localList.get(i);
        if (!localViewHolder.isRemoved())
        {
          int i2 = localViewHolder.getLayoutPosition();
          int m = 1;
          int i3;
          if (i2 < i1) {
            i3 = 1;
          } else {
            i3 = 0;
          }
          if (i3 != mShouldReverseLayout) {
            m = -1;
          }
          if (m == -1) {
            j += mOrientationHelper.getDecoratedMeasurement(itemView);
          } else {
            k += mOrientationHelper.getDecoratedMeasurement(itemView);
          }
        }
        i += 1;
      }
      mLayoutState.mScrapList = localList;
      if (j > 0)
      {
        updateLayoutStateToFillStart(getPosition(getChildClosestToStart()), paramInt1);
        mLayoutState.mExtra = j;
        mLayoutState.mAvailable = 0;
        mLayoutState.assignPositionFromScrapList();
        fill(paramRecycler, mLayoutState, paramState, false);
      }
      if (k > 0)
      {
        updateLayoutStateToFillEnd(getPosition(getChildClosestToEnd()), paramInt2);
        mLayoutState.mExtra = k;
        mLayoutState.mAvailable = 0;
        mLayoutState.assignPositionFromScrapList();
        fill(paramRecycler, mLayoutState, paramState, false);
      }
      mLayoutState.mScrapList = null;
    }
  }
  
  private void logChildren()
  {
    Log.d("LinearLayoutManager", "internal representation of views on the screen");
    int i = 0;
    while (i < getChildCount())
    {
      View localView = getChildAt(i);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("item ");
      localStringBuilder.append(getPosition(localView));
      localStringBuilder.append(", coord:");
      localStringBuilder.append(mOrientationHelper.getDecoratedStart(localView));
      Log.d("LinearLayoutManager", localStringBuilder.toString());
      i += 1;
    }
    Log.d("LinearLayoutManager", "==============");
  }
  
  private void recycleByLayoutState(RecyclerView.Recycler paramRecycler, LayoutState paramLayoutState)
  {
    if (mRecycle)
    {
      if (mInfinite) {
        return;
      }
      if (mLayoutDirection == -1)
      {
        recycleViewsFromEnd(paramRecycler, mScrollingOffset);
        return;
      }
      recycleViewsFromStart(paramRecycler, mScrollingOffset);
    }
  }
  
  private void recycleChildren(RecyclerView.Recycler paramRecycler, int paramInt1, int paramInt2)
  {
    if (paramInt1 == paramInt2) {
      return;
    }
    int i = paramInt1;
    if (paramInt2 > paramInt1)
    {
      paramInt2 -= 1;
      while (paramInt2 >= paramInt1)
      {
        removeAndRecycleViewAt(paramInt2, paramRecycler);
        paramInt2 -= 1;
      }
    }
    while (i > paramInt2)
    {
      removeAndRecycleViewAt(i, paramRecycler);
      i -= 1;
    }
  }
  
  private void recycleViewsFromEnd(RecyclerView.Recycler paramRecycler, int paramInt)
  {
    int i = getChildCount();
    if (paramInt < 0) {
      return;
    }
    int j = mOrientationHelper.getEnd() - paramInt;
    View localView;
    if (mShouldReverseLayout)
    {
      paramInt = 0;
      for (;;)
      {
        if (paramInt >= i) {
          return;
        }
        localView = getChildAt(paramInt);
        if ((mOrientationHelper.getDecoratedStart(localView) < j) || (mOrientationHelper.getTransformedStartWithDecoration(localView) < j)) {
          break;
        }
        paramInt += 1;
      }
      recycleChildren(paramRecycler, 0, paramInt);
      return;
    }
    i -= 1;
    paramInt = i;
    while (paramInt >= 0)
    {
      localView = getChildAt(paramInt);
      if ((mOrientationHelper.getDecoratedStart(localView) >= j) && (mOrientationHelper.getTransformedStartWithDecoration(localView) >= j)) {
        paramInt -= 1;
      } else {
        recycleChildren(paramRecycler, i, paramInt);
      }
    }
  }
  
  private void recycleViewsFromStart(RecyclerView.Recycler paramRecycler, int paramInt)
  {
    if (paramInt < 0) {
      return;
    }
    int j = getChildCount();
    View localView;
    if (mShouldReverseLayout)
    {
      j -= 1;
      i = j;
      for (;;)
      {
        if (i < 0) {
          return;
        }
        localView = getChildAt(i);
        if ((mOrientationHelper.getDecoratedEnd(localView) > paramInt) || (mOrientationHelper.getTransformedEndWithDecoration(localView) > paramInt)) {
          break;
        }
        i -= 1;
      }
      recycleChildren(paramRecycler, j, i);
      return;
    }
    int i = 0;
    while (i < j)
    {
      localView = getChildAt(i);
      if ((mOrientationHelper.getDecoratedEnd(localView) <= paramInt) && (mOrientationHelper.getTransformedEndWithDecoration(localView) <= paramInt)) {
        i += 1;
      } else {
        recycleChildren(paramRecycler, 0, i);
      }
    }
  }
  
  private void resolveShouldLayoutReverse()
  {
    if ((mOrientation != 1) && (isLayoutRTL()))
    {
      mShouldReverseLayout = (mReverseLayout ^ true);
      return;
    }
    mShouldReverseLayout = mReverseLayout;
  }
  
  private boolean updateAnchorFromChildren(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, AnchorInfo paramAnchorInfo)
  {
    int j = getChildCount();
    int i = 0;
    if (j == 0) {
      return false;
    }
    View localView = getFocusedChild();
    if ((localView != null) && (paramAnchorInfo.isViewValidAsAnchor(localView, paramState)))
    {
      paramAnchorInfo.assignFromViewAndKeepVisibleRect(localView, getPosition(localView));
      return true;
    }
    if (mLastStackFromEnd != mStackFromEnd) {
      return false;
    }
    if (mLayoutFromEnd) {
      paramRecycler = findReferenceChildClosestToEnd(paramRecycler, paramState);
    } else {
      paramRecycler = findReferenceChildClosestToStart(paramRecycler, paramState);
    }
    if (paramRecycler != null)
    {
      paramAnchorInfo.assignFromView(paramRecycler, getPosition(paramRecycler));
      if ((!paramState.isPreLayout()) && (supportsPredictiveItemAnimations()))
      {
        if ((mOrientationHelper.getDecoratedStart(paramRecycler) >= mOrientationHelper.getEndAfterPadding()) || (mOrientationHelper.getDecoratedEnd(paramRecycler) < mOrientationHelper.getStartAfterPadding())) {
          i = 1;
        }
        if (i != 0)
        {
          if (mLayoutFromEnd) {
            i = mOrientationHelper.getEndAfterPadding();
          } else {
            i = mOrientationHelper.getStartAfterPadding();
          }
          mCoordinate = i;
          return true;
        }
      }
    }
    else
    {
      return false;
    }
    return true;
  }
  
  private boolean updateAnchorFromPendingData(RecyclerView.State paramState, AnchorInfo paramAnchorInfo)
  {
    boolean bool1 = paramState.isPreLayout();
    boolean bool2 = false;
    if (!bool1)
    {
      if (mPendingScrollPosition == -1) {
        return false;
      }
      if ((mPendingScrollPosition >= 0) && (mPendingScrollPosition < paramState.getItemCount()))
      {
        mPosition = mPendingScrollPosition;
        if ((mPendingSavedState != null) && (mPendingSavedState.hasValidAnchor()))
        {
          mLayoutFromEnd = mPendingSavedState.mAnchorLayoutFromEnd;
          if (mLayoutFromEnd)
          {
            mCoordinate = (mOrientationHelper.getEndAfterPadding() - mPendingSavedState.mAnchorOffset);
            return true;
          }
          mCoordinate = (mOrientationHelper.getStartAfterPadding() + mPendingSavedState.mAnchorOffset);
          return true;
        }
        if (mPendingScrollPositionOffset == Integer.MIN_VALUE)
        {
          paramState = findViewByPosition(mPendingScrollPosition);
          int i;
          if (paramState != null)
          {
            if (mOrientationHelper.getDecoratedMeasurement(paramState) > mOrientationHelper.getTotalSpace())
            {
              paramAnchorInfo.assignCoordinateFromPadding();
              return true;
            }
            if (mOrientationHelper.getDecoratedStart(paramState) - mOrientationHelper.getStartAfterPadding() < 0)
            {
              mCoordinate = mOrientationHelper.getStartAfterPadding();
              mLayoutFromEnd = false;
              return true;
            }
            if (mOrientationHelper.getEndAfterPadding() - mOrientationHelper.getDecoratedEnd(paramState) < 0)
            {
              mCoordinate = mOrientationHelper.getEndAfterPadding();
              mLayoutFromEnd = true;
              return true;
            }
            if (mLayoutFromEnd) {
              i = mOrientationHelper.getDecoratedEnd(paramState) + mOrientationHelper.getTotalSpaceChange();
            } else {
              i = mOrientationHelper.getDecoratedStart(paramState);
            }
            mCoordinate = i;
            return true;
          }
          if (getChildCount() > 0)
          {
            i = getPosition(getChildAt(0));
            if (mPendingScrollPosition < i) {
              bool1 = true;
            } else {
              bool1 = false;
            }
            if (bool1 == mShouldReverseLayout) {
              bool2 = true;
            }
            mLayoutFromEnd = bool2;
          }
          paramAnchorInfo.assignCoordinateFromPadding();
          return true;
        }
        mLayoutFromEnd = mShouldReverseLayout;
        if (mShouldReverseLayout)
        {
          mCoordinate = (mOrientationHelper.getEndAfterPadding() - mPendingScrollPositionOffset);
          return true;
        }
        mCoordinate = (mOrientationHelper.getStartAfterPadding() + mPendingScrollPositionOffset);
        return true;
      }
      mPendingScrollPosition = -1;
      mPendingScrollPositionOffset = Integer.MIN_VALUE;
    }
    return false;
  }
  
  private void updateAnchorInfoForLayout(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, AnchorInfo paramAnchorInfo)
  {
    if (updateAnchorFromPendingData(paramState, paramAnchorInfo)) {
      return;
    }
    if (updateAnchorFromChildren(paramRecycler, paramState, paramAnchorInfo)) {
      return;
    }
    paramAnchorInfo.assignCoordinateFromPadding();
    int i;
    if (mStackFromEnd) {
      i = paramState.getItemCount() - 1;
    } else {
      i = 0;
    }
    mPosition = i;
  }
  
  private void updateLayoutState(int paramInt1, int paramInt2, boolean paramBoolean, RecyclerView.State paramState)
  {
    mLayoutState.mInfinite = resolveIsInfinite();
    mLayoutState.mExtra = getExtraLayoutSpace(paramState);
    mLayoutState.mLayoutDirection = paramInt1;
    int i = -1;
    LayoutState localLayoutState;
    if (paramInt1 == 1)
    {
      paramState = mLayoutState;
      mExtra += mOrientationHelper.getEndPadding();
      paramState = getChildClosestToEnd();
      localLayoutState = mLayoutState;
      if (!mShouldReverseLayout) {
        i = 1;
      }
      mItemDirection = i;
      mLayoutState.mCurrentPosition = (getPosition(paramState) + mLayoutState.mItemDirection);
      mLayoutState.mOffset = mOrientationHelper.getDecoratedEnd(paramState);
      paramInt1 = mOrientationHelper.getDecoratedEnd(paramState) - mOrientationHelper.getEndAfterPadding();
    }
    else
    {
      paramState = getChildClosestToStart();
      localLayoutState = mLayoutState;
      mExtra += mOrientationHelper.getStartAfterPadding();
      localLayoutState = mLayoutState;
      if (mShouldReverseLayout) {
        i = 1;
      }
      mItemDirection = i;
      mLayoutState.mCurrentPosition = (getPosition(paramState) + mLayoutState.mItemDirection);
      mLayoutState.mOffset = mOrientationHelper.getDecoratedStart(paramState);
      paramInt1 = -mOrientationHelper.getDecoratedStart(paramState) + mOrientationHelper.getStartAfterPadding();
    }
    mLayoutState.mAvailable = paramInt2;
    if (paramBoolean)
    {
      paramState = mLayoutState;
      mAvailable -= paramInt1;
    }
    mLayoutState.mScrollingOffset = paramInt1;
  }
  
  private void updateLayoutStateToFillEnd(int paramInt1, int paramInt2)
  {
    mLayoutState.mAvailable = (mOrientationHelper.getEndAfterPadding() - paramInt2);
    LayoutState localLayoutState = mLayoutState;
    int i;
    if (mShouldReverseLayout) {
      i = -1;
    } else {
      i = 1;
    }
    mItemDirection = i;
    mLayoutState.mCurrentPosition = paramInt1;
    mLayoutState.mLayoutDirection = 1;
    mLayoutState.mOffset = paramInt2;
    mLayoutState.mScrollingOffset = Integer.MIN_VALUE;
  }
  
  private void updateLayoutStateToFillEnd(AnchorInfo paramAnchorInfo)
  {
    updateLayoutStateToFillEnd(mPosition, mCoordinate);
  }
  
  private void updateLayoutStateToFillStart(int paramInt1, int paramInt2)
  {
    mLayoutState.mAvailable = (paramInt2 - mOrientationHelper.getStartAfterPadding());
    mLayoutState.mCurrentPosition = paramInt1;
    LayoutState localLayoutState = mLayoutState;
    if (mShouldReverseLayout) {
      paramInt1 = 1;
    } else {
      paramInt1 = -1;
    }
    mItemDirection = paramInt1;
    mLayoutState.mLayoutDirection = -1;
    mLayoutState.mOffset = paramInt2;
    mLayoutState.mScrollingOffset = Integer.MIN_VALUE;
  }
  
  private void updateLayoutStateToFillStart(AnchorInfo paramAnchorInfo)
  {
    updateLayoutStateToFillStart(mPosition, mCoordinate);
  }
  
  public void assertNotInLayoutOrScroll(String paramString)
  {
    if (mPendingSavedState == null) {
      super.assertNotInLayoutOrScroll(paramString);
    }
  }
  
  public boolean canScrollHorizontally()
  {
    return mOrientation == 0;
  }
  
  public boolean canScrollVertically()
  {
    return mOrientation == 1;
  }
  
  public void collectAdjacentPrefetchPositions(int paramInt1, int paramInt2, RecyclerView.State paramState, RecyclerView.LayoutManager.LayoutPrefetchRegistry paramLayoutPrefetchRegistry)
  {
    if (mOrientation != 0) {
      paramInt1 = paramInt2;
    }
    if (getChildCount() != 0)
    {
      if (paramInt1 == 0) {
        return;
      }
      ensureLayoutState();
      if (paramInt1 > 0) {
        paramInt2 = 1;
      } else {
        paramInt2 = -1;
      }
      updateLayoutState(paramInt2, Math.abs(paramInt1), true, paramState);
      collectPrefetchPositionsForLayoutState(paramState, mLayoutState, paramLayoutPrefetchRegistry);
    }
  }
  
  public void collectInitialPrefetchPositions(int paramInt, RecyclerView.LayoutManager.LayoutPrefetchRegistry paramLayoutPrefetchRegistry)
  {
    SavedState localSavedState = mPendingSavedState;
    int j = -1;
    boolean bool;
    int i;
    if ((localSavedState != null) && (mPendingSavedState.hasValidAnchor()))
    {
      bool = mPendingSavedState.mAnchorLayoutFromEnd;
      i = mPendingSavedState.mAnchorPosition;
    }
    else
    {
      resolveShouldLayoutReverse();
      bool = mShouldReverseLayout;
      if (mPendingScrollPosition == -1)
      {
        if (bool) {
          i = paramInt - 1;
        } else {
          i = 0;
        }
      }
      else {
        i = mPendingScrollPosition;
      }
    }
    if (!bool) {
      j = 1;
    }
    int k = 0;
    while ((k < mInitialPrefetchItemCount) && (i >= 0) && (i < paramInt))
    {
      paramLayoutPrefetchRegistry.addPosition(i, 0);
      i += j;
      k += 1;
    }
  }
  
  void collectPrefetchPositionsForLayoutState(RecyclerView.State paramState, LayoutState paramLayoutState, RecyclerView.LayoutManager.LayoutPrefetchRegistry paramLayoutPrefetchRegistry)
  {
    int i = mCurrentPosition;
    if ((i >= 0) && (i < paramState.getItemCount())) {
      paramLayoutPrefetchRegistry.addPosition(i, Math.max(0, mScrollingOffset));
    }
  }
  
  public int computeHorizontalScrollExtent(RecyclerView.State paramState)
  {
    return computeScrollExtent(paramState);
  }
  
  public int computeHorizontalScrollOffset(RecyclerView.State paramState)
  {
    return computeScrollOffset(paramState);
  }
  
  public int computeHorizontalScrollRange(RecyclerView.State paramState)
  {
    return computeScrollRange(paramState);
  }
  
  public PointF computeScrollVectorForPosition(int paramInt)
  {
    if (getChildCount() == 0) {
      return null;
    }
    int k = 0;
    int j = getPosition(getChildAt(0));
    int i = 1;
    if (paramInt < j) {
      k = 1;
    }
    paramInt = i;
    if (k != mShouldReverseLayout) {
      paramInt = -1;
    }
    if (mOrientation == 0) {
      return new PointF(paramInt, 0.0F);
    }
    return new PointF(0.0F, paramInt);
  }
  
  public int computeVerticalScrollExtent(RecyclerView.State paramState)
  {
    return computeScrollExtent(paramState);
  }
  
  public int computeVerticalScrollOffset(RecyclerView.State paramState)
  {
    return computeScrollOffset(paramState);
  }
  
  public int computeVerticalScrollRange(RecyclerView.State paramState)
  {
    return computeScrollRange(paramState);
  }
  
  int convertFocusDirectionToLayoutDirection(int paramInt)
  {
    if (paramInt != 17)
    {
      if (paramInt != 33)
      {
        if (paramInt != 66)
        {
          if (paramInt != 130)
          {
            switch (paramInt)
            {
            default: 
              return Integer.MIN_VALUE;
            case 2: 
              if (mOrientation == 1) {
                return 1;
              }
              if (isLayoutRTL()) {
                return -1;
              }
              return 1;
            }
            if (mOrientation == 1) {
              return -1;
            }
            if (isLayoutRTL()) {
              return 1;
            }
            return -1;
          }
          if (mOrientation == 1) {
            return 1;
          }
        }
        else if (mOrientation == 0)
        {
          return 1;
        }
      }
      else
      {
        if (mOrientation == 1) {
          return -1;
        }
        return Integer.MIN_VALUE;
      }
    }
    else
    {
      if (mOrientation == 0) {
        return -1;
      }
      return Integer.MIN_VALUE;
    }
    return Integer.MIN_VALUE;
  }
  
  LayoutState createLayoutState()
  {
    return new LayoutState();
  }
  
  void ensureLayoutState()
  {
    if (mLayoutState == null) {
      mLayoutState = createLayoutState();
    }
  }
  
  int fill(RecyclerView.Recycler paramRecycler, LayoutState paramLayoutState, RecyclerView.State paramState, boolean paramBoolean)
  {
    int k = mAvailable;
    if (mScrollingOffset != Integer.MIN_VALUE)
    {
      if (mAvailable < 0) {
        mScrollingOffset += mAvailable;
      }
      recycleByLayoutState(paramRecycler, paramLayoutState);
    }
    int i = mAvailable + mExtra;
    LayoutChunkResult localLayoutChunkResult = mLayoutChunkResult;
    do
    {
      int j;
      do
      {
        if (((!mInfinite) && (i <= 0)) || (!paramLayoutState.hasMore(paramState))) {
          break;
        }
        localLayoutChunkResult.resetInternal();
        layoutChunk(paramRecycler, paramState, paramLayoutState, localLayoutChunkResult);
        if (mFinished) {
          break;
        }
        mOffset += mConsumed * mLayoutDirection;
        if ((mIgnoreConsumed) && (mLayoutState.mScrapList == null))
        {
          j = i;
          if (paramState.isPreLayout()) {}
        }
        else
        {
          mAvailable -= mConsumed;
          j = i - mConsumed;
        }
        if (mScrollingOffset != Integer.MIN_VALUE)
        {
          mScrollingOffset += mConsumed;
          if (mAvailable < 0) {
            mScrollingOffset += mAvailable;
          }
          recycleByLayoutState(paramRecycler, paramLayoutState);
        }
        i = j;
      } while (!paramBoolean);
      i = j;
    } while (!mFocusable);
    return k - mAvailable;
  }
  
  public int findFirstCompletelyVisibleItemPosition()
  {
    View localView = findOneVisibleChild(0, getChildCount(), true, false);
    if (localView == null) {
      return -1;
    }
    return getPosition(localView);
  }
  
  public int findFirstVisibleItemPosition()
  {
    View localView = findOneVisibleChild(0, getChildCount(), false, true);
    if (localView == null) {
      return -1;
    }
    return getPosition(localView);
  }
  
  public int findLastCompletelyVisibleItemPosition()
  {
    View localView = findOneVisibleChild(getChildCount() - 1, -1, true, false);
    if (localView == null) {
      return -1;
    }
    return getPosition(localView);
  }
  
  public int findLastVisibleItemPosition()
  {
    View localView = findOneVisibleChild(getChildCount() - 1, -1, false, true);
    if (localView == null) {
      return -1;
    }
    return getPosition(localView);
  }
  
  View findOnePartiallyOrCompletelyInvisibleChild(int paramInt1, int paramInt2)
  {
    ensureLayoutState();
    int i;
    if (paramInt2 > paramInt1) {
      i = 1;
    } else if (paramInt2 < paramInt1) {
      i = -1;
    } else {
      i = 0;
    }
    if (i == 0) {
      return getChildAt(paramInt1);
    }
    int j;
    if (mOrientationHelper.getDecoratedStart(getChildAt(paramInt1)) < mOrientationHelper.getStartAfterPadding())
    {
      i = 16644;
      j = 16388;
    }
    else
    {
      i = 4161;
      j = 4097;
    }
    if (mOrientation == 0) {
      return mHorizontalBoundCheck.findOneViewWithinBoundFlags(paramInt1, paramInt2, i, j);
    }
    return mVerticalBoundCheck.findOneViewWithinBoundFlags(paramInt1, paramInt2, i, j);
  }
  
  View findOneVisibleChild(int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2)
  {
    ensureLayoutState();
    int j = 320;
    int i;
    if (paramBoolean1) {
      i = 24579;
    } else {
      i = 320;
    }
    if (!paramBoolean2) {
      j = 0;
    }
    if (mOrientation == 0) {
      return mHorizontalBoundCheck.findOneViewWithinBoundFlags(paramInt1, paramInt2, i, j);
    }
    return mVerticalBoundCheck.findOneViewWithinBoundFlags(paramInt1, paramInt2, i, j);
  }
  
  View findReferenceChild(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, int paramInt1, int paramInt2, int paramInt3)
  {
    ensureLayoutState();
    int j = mOrientationHelper.getStartAfterPadding();
    int k = mOrientationHelper.getEndAfterPadding();
    int i;
    if (paramInt2 > paramInt1) {
      i = 1;
    } else {
      i = -1;
    }
    paramState = null;
    Object localObject2;
    for (paramRecycler = null; paramInt1 != paramInt2; paramRecycler = (RecyclerView.Recycler)localObject2)
    {
      View localView = getChildAt(paramInt1);
      int m = getPosition(localView);
      Object localObject1 = paramState;
      localObject2 = paramRecycler;
      if (m >= 0)
      {
        localObject1 = paramState;
        localObject2 = paramRecycler;
        if (m < paramInt3) {
          if (((RecyclerView.LayoutParams)localView.getLayoutParams()).isItemRemoved())
          {
            localObject1 = paramState;
            localObject2 = paramRecycler;
            if (paramRecycler == null)
            {
              localObject2 = localView;
              localObject1 = paramState;
            }
          }
          else
          {
            if ((mOrientationHelper.getDecoratedStart(localView) < k) && (mOrientationHelper.getDecoratedEnd(localView) >= j)) {
              return localView;
            }
            localObject1 = paramState;
            localObject2 = paramRecycler;
            if (paramState == null)
            {
              localObject1 = localView;
              localObject2 = paramRecycler;
            }
          }
        }
      }
      paramInt1 += i;
      paramState = (RecyclerView.State)localObject1;
    }
    if (paramState != null) {
      return paramState;
    }
    return paramRecycler;
  }
  
  public View findViewByPosition(int paramInt)
  {
    int i = getChildCount();
    if (i == 0) {
      return null;
    }
    int j = paramInt - getPosition(getChildAt(0));
    if ((j >= 0) && (j < i))
    {
      View localView = getChildAt(j);
      if (getPosition(localView) == paramInt) {
        return localView;
      }
    }
    return super.findViewByPosition(paramInt);
  }
  
  public RecyclerView.LayoutParams generateDefaultLayoutParams()
  {
    return new RecyclerView.LayoutParams(-2, -2);
  }
  
  protected int getExtraLayoutSpace(RecyclerView.State paramState)
  {
    if (paramState.hasTargetScrollPosition()) {
      return mOrientationHelper.getTotalSpace();
    }
    return 0;
  }
  
  public int getInitialPrefetchItemCount()
  {
    return mInitialPrefetchItemCount;
  }
  
  public int getOrientation()
  {
    return mOrientation;
  }
  
  public boolean getRecycleChildrenOnDetach()
  {
    return mRecycleChildrenOnDetach;
  }
  
  public boolean getReverseLayout()
  {
    return mReverseLayout;
  }
  
  public boolean getStackFromEnd()
  {
    return mStackFromEnd;
  }
  
  public boolean isAutoMeasureEnabled()
  {
    return true;
  }
  
  protected boolean isLayoutRTL()
  {
    return getLayoutDirection() == 1;
  }
  
  public boolean isSmoothScrollbarEnabled()
  {
    return mSmoothScrollbarEnabled;
  }
  
  void layoutChunk(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, LayoutState paramLayoutState, LayoutChunkResult paramLayoutChunkResult)
  {
    paramRecycler = paramLayoutState.next(paramRecycler);
    if (paramRecycler == null)
    {
      mFinished = true;
      return;
    }
    paramState = (RecyclerView.LayoutParams)paramRecycler.getLayoutParams();
    boolean bool2;
    boolean bool1;
    if (mScrapList == null)
    {
      bool2 = mShouldReverseLayout;
      if (mLayoutDirection == -1) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      if (bool2 == bool1) {
        addView(paramRecycler);
      } else {
        addView(paramRecycler, 0);
      }
    }
    else
    {
      bool2 = mShouldReverseLayout;
      if (mLayoutDirection == -1) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      if (bool2 == bool1) {
        addDisappearingView(paramRecycler);
      } else {
        addDisappearingView(paramRecycler, 0);
      }
    }
    measureChildWithMargins(paramRecycler, 0, 0);
    mConsumed = mOrientationHelper.getDecoratedMeasurement(paramRecycler);
    int i;
    int m;
    int n;
    int j;
    int k;
    if (mOrientation == 1)
    {
      if (isLayoutRTL())
      {
        i = getWidth() - getPaddingRight();
        m = i - mOrientationHelper.getDecoratedMeasurementInOther(paramRecycler);
      }
      else
      {
        i = getPaddingLeft();
        m = i;
        i = mOrientationHelper.getDecoratedMeasurementInOther(paramRecycler) + i;
      }
      if (mLayoutDirection == -1)
      {
        n = mOffset - mConsumed;
        j = i;
        k = mOffset;
        i = n;
      }
      else
      {
        k = mOffset + mConsumed;
        j = i;
        i = mOffset;
      }
    }
    else
    {
      i = getPaddingTop();
      k = mOrientationHelper.getDecoratedMeasurementInOther(paramRecycler) + i;
      if (mLayoutDirection == -1)
      {
        m = mOffset;
        n = mConsumed;
        j = mOffset;
        m -= n;
      }
      else
      {
        j = mOffset + mConsumed;
        m = mOffset;
      }
    }
    layoutDecoratedWithMargins(paramRecycler, m, i, j, k);
    if ((paramState.isItemRemoved()) || (paramState.isItemChanged())) {
      mIgnoreConsumed = true;
    }
    mFocusable = paramRecycler.hasFocusable();
  }
  
  void onAnchorReady(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState, AnchorInfo paramAnchorInfo, int paramInt) {}
  
  public void onDetachedFromWindow(RecyclerView paramRecyclerView, RecyclerView.Recycler paramRecycler)
  {
    super.onDetachedFromWindow(paramRecyclerView, paramRecycler);
    if (mRecycleChildrenOnDetach)
    {
      removeAndRecycleAllViews(paramRecycler);
      paramRecycler.clear();
    }
  }
  
  public View onFocusSearchFailed(View paramView, int paramInt, RecyclerView.Recycler paramRecycler, RecyclerView.State paramState)
  {
    resolveShouldLayoutReverse();
    if (getChildCount() == 0) {
      return null;
    }
    paramInt = convertFocusDirectionToLayoutDirection(paramInt);
    if (paramInt == Integer.MIN_VALUE) {
      return null;
    }
    ensureLayoutState();
    ensureLayoutState();
    updateLayoutState(paramInt, (int)(mOrientationHelper.getTotalSpace() * 0.33333334F), false, paramState);
    mLayoutState.mScrollingOffset = Integer.MIN_VALUE;
    mLayoutState.mRecycle = false;
    fill(paramRecycler, mLayoutState, paramState, true);
    if (paramInt == -1) {
      paramView = findPartiallyOrCompletelyInvisibleChildClosestToStart(paramRecycler, paramState);
    } else {
      paramView = findPartiallyOrCompletelyInvisibleChildClosestToEnd(paramRecycler, paramState);
    }
    if (paramInt == -1) {
      paramRecycler = getChildClosestToStart();
    } else {
      paramRecycler = getChildClosestToEnd();
    }
    if (paramRecycler.hasFocusable())
    {
      if (paramView == null) {
        return null;
      }
      return paramRecycler;
    }
    return paramView;
  }
  
  public void onInitializeAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    super.onInitializeAccessibilityEvent(paramAccessibilityEvent);
    if (getChildCount() > 0)
    {
      paramAccessibilityEvent.setFromIndex(findFirstVisibleItemPosition());
      paramAccessibilityEvent.setToIndex(findLastVisibleItemPosition());
    }
  }
  
  public void onLayoutChildren(RecyclerView.Recycler paramRecycler, RecyclerView.State paramState)
  {
    Object localObject = mPendingSavedState;
    int k = -1;
    if (((localObject != null) || (mPendingScrollPosition != -1)) && (paramState.getItemCount() == 0))
    {
      removeAndRecycleAllViews(paramRecycler);
      return;
    }
    if ((mPendingSavedState != null) && (mPendingSavedState.hasValidAnchor())) {
      mPendingScrollPosition = mPendingSavedState.mAnchorPosition;
    }
    ensureLayoutState();
    mLayoutState.mRecycle = false;
    resolveShouldLayoutReverse();
    localObject = getFocusedChild();
    if ((mAnchorInfo.mValid) && (mPendingScrollPosition == -1) && (mPendingSavedState == null))
    {
      if ((localObject != null) && ((mOrientationHelper.getDecoratedStart((View)localObject) >= mOrientationHelper.getEndAfterPadding()) || (mOrientationHelper.getDecoratedEnd((View)localObject) <= mOrientationHelper.getStartAfterPadding()))) {
        mAnchorInfo.assignFromViewAndKeepVisibleRect((View)localObject, getPosition((View)localObject));
      }
    }
    else
    {
      mAnchorInfo.reset();
      mAnchorInfo.mLayoutFromEnd = (mShouldReverseLayout ^ mStackFromEnd);
      updateAnchorInfoForLayout(paramRecycler, paramState, mAnchorInfo);
      mAnchorInfo.mValid = true;
    }
    int i = getExtraLayoutSpace(paramState);
    int j = i;
    if (mLayoutState.mLastScrollDelta >= 0) {
      j = 0;
    } else {
      i = 0;
    }
    int m = j + mOrientationHelper.getStartAfterPadding();
    int n = i + mOrientationHelper.getEndPadding();
    i = m;
    j = n;
    if (paramState.isPreLayout())
    {
      i = m;
      j = n;
      if (mPendingScrollPosition != -1)
      {
        i = m;
        j = n;
        if (mPendingScrollPositionOffset != Integer.MIN_VALUE)
        {
          localObject = findViewByPosition(mPendingScrollPosition);
          i = m;
          j = n;
          if (localObject != null)
          {
            if (mShouldReverseLayout)
            {
              i = mOrientationHelper.getEndAfterPadding() - mOrientationHelper.getDecoratedEnd((View)localObject) - mPendingScrollPositionOffset;
            }
            else
            {
              i = mOrientationHelper.getDecoratedStart((View)localObject);
              j = mOrientationHelper.getStartAfterPadding();
              i = mPendingScrollPositionOffset - (i - j);
            }
            if (i > 0)
            {
              i = m + i;
              j = n;
            }
            else
            {
              j = n - i;
              i = m;
            }
          }
        }
      }
    }
    if (mAnchorInfo.mLayoutFromEnd)
    {
      if (!mShouldReverseLayout) {}
    }
    else {
      while (!mShouldReverseLayout)
      {
        k = 1;
        break;
      }
    }
    onAnchorReady(paramRecycler, paramState, mAnchorInfo, k);
    detachAndScrapAttachedViews(paramRecycler);
    mLayoutState.mInfinite = resolveIsInfinite();
    mLayoutState.mIsPreLayout = paramState.isPreLayout();
    if (mAnchorInfo.mLayoutFromEnd)
    {
      updateLayoutStateToFillStart(mAnchorInfo);
      mLayoutState.mExtra = i;
      fill(paramRecycler, mLayoutState, paramState, false);
      k = mLayoutState.mOffset;
      n = mLayoutState.mCurrentPosition;
      i = j;
      if (mLayoutState.mAvailable > 0) {
        i = j + mLayoutState.mAvailable;
      }
      updateLayoutStateToFillEnd(mAnchorInfo);
      mLayoutState.mExtra = i;
      localObject = mLayoutState;
      mCurrentPosition += mLayoutState.mItemDirection;
      fill(paramRecycler, mLayoutState, paramState, false);
      m = mLayoutState.mOffset;
      j = k;
      i = m;
      if (mLayoutState.mAvailable > 0)
      {
        i = mLayoutState.mAvailable;
        updateLayoutStateToFillStart(n, k);
        mLayoutState.mExtra = i;
        fill(paramRecycler, mLayoutState, paramState, false);
        j = mLayoutState.mOffset;
        i = m;
      }
    }
    else
    {
      updateLayoutStateToFillEnd(mAnchorInfo);
      mLayoutState.mExtra = j;
      fill(paramRecycler, mLayoutState, paramState, false);
      k = mLayoutState.mOffset;
      n = mLayoutState.mCurrentPosition;
      j = i;
      if (mLayoutState.mAvailable > 0) {
        j = i + mLayoutState.mAvailable;
      }
      updateLayoutStateToFillStart(mAnchorInfo);
      mLayoutState.mExtra = j;
      localObject = mLayoutState;
      mCurrentPosition += mLayoutState.mItemDirection;
      fill(paramRecycler, mLayoutState, paramState, false);
      m = mLayoutState.mOffset;
      j = m;
      i = k;
      if (mLayoutState.mAvailable > 0)
      {
        i = mLayoutState.mAvailable;
        updateLayoutStateToFillEnd(n, k);
        mLayoutState.mExtra = i;
        fill(paramRecycler, mLayoutState, paramState, false);
        i = mLayoutState.mOffset;
        j = m;
      }
    }
    k = j;
    m = i;
    if (getChildCount() > 0) {
      if ((mShouldReverseLayout ^ mStackFromEnd))
      {
        m = fixLayoutEndGap(i, paramRecycler, paramState, true);
        k = j + m;
        j = fixLayoutStartGap(k, paramRecycler, paramState, false);
        k += j;
        m = i + m + j;
      }
      else
      {
        k = fixLayoutStartGap(j, paramRecycler, paramState, true);
        i += k;
        m = fixLayoutEndGap(i, paramRecycler, paramState, false);
        k = j + k + m;
        m = i + m;
      }
    }
    layoutForPredictiveAnimations(paramRecycler, paramState, k, m);
    if (!paramState.isPreLayout()) {
      mOrientationHelper.onLayoutComplete();
    } else {
      mAnchorInfo.reset();
    }
    mLastStackFromEnd = mStackFromEnd;
  }
  
  public void onLayoutCompleted(RecyclerView.State paramState)
  {
    super.onLayoutCompleted(paramState);
    mPendingSavedState = null;
    mPendingScrollPosition = -1;
    mPendingScrollPositionOffset = Integer.MIN_VALUE;
    mAnchorInfo.reset();
  }
  
  public void onRestoreInstanceState(Parcelable paramParcelable)
  {
    if ((paramParcelable instanceof SavedState))
    {
      mPendingSavedState = ((SavedState)paramParcelable);
      requestLayout();
    }
  }
  
  public Parcelable onSaveInstanceState()
  {
    if (mPendingSavedState != null) {
      return new SavedState(mPendingSavedState);
    }
    SavedState localSavedState = new SavedState();
    if (getChildCount() > 0)
    {
      ensureLayoutState();
      boolean bool = mLastStackFromEnd ^ mShouldReverseLayout;
      mAnchorLayoutFromEnd = bool;
      if (bool)
      {
        localView = getChildClosestToEnd();
        mAnchorOffset = (mOrientationHelper.getEndAfterPadding() - mOrientationHelper.getDecoratedEnd(localView));
        mAnchorPosition = getPosition(localView);
        return localSavedState;
      }
      View localView = getChildClosestToStart();
      mAnchorPosition = getPosition(localView);
      mAnchorOffset = (mOrientationHelper.getDecoratedStart(localView) - mOrientationHelper.getStartAfterPadding());
      return localSavedState;
    }
    localSavedState.invalidateAnchor();
    return localSavedState;
  }
  
  public void prepareForDrop(View paramView1, View paramView2, int paramInt1, int paramInt2)
  {
    assertNotInLayoutOrScroll("Cannot drop a view during a scroll or layout calculation");
    ensureLayoutState();
    resolveShouldLayoutReverse();
    paramInt1 = getPosition(paramView1);
    paramInt2 = getPosition(paramView2);
    if (paramInt1 < paramInt2) {
      paramInt1 = 1;
    } else {
      paramInt1 = -1;
    }
    if (mShouldReverseLayout)
    {
      if (paramInt1 == 1)
      {
        scrollToPositionWithOffset(paramInt2, mOrientationHelper.getEndAfterPadding() - (mOrientationHelper.getDecoratedStart(paramView2) + mOrientationHelper.getDecoratedMeasurement(paramView1)));
        return;
      }
      scrollToPositionWithOffset(paramInt2, mOrientationHelper.getEndAfterPadding() - mOrientationHelper.getDecoratedEnd(paramView2));
      return;
    }
    if (paramInt1 == -1)
    {
      scrollToPositionWithOffset(paramInt2, mOrientationHelper.getDecoratedStart(paramView2));
      return;
    }
    scrollToPositionWithOffset(paramInt2, mOrientationHelper.getDecoratedEnd(paramView2) - mOrientationHelper.getDecoratedMeasurement(paramView1));
  }
  
  boolean resolveIsInfinite()
  {
    return (mOrientationHelper.getMode() == 0) && (mOrientationHelper.getEnd() == 0);
  }
  
  int scrollBy(int paramInt, RecyclerView.Recycler paramRecycler, RecyclerView.State paramState)
  {
    if (getChildCount() != 0)
    {
      if (paramInt == 0) {
        return 0;
      }
      mLayoutState.mRecycle = true;
      ensureLayoutState();
      int i;
      if (paramInt > 0) {
        i = 1;
      } else {
        i = -1;
      }
      int j = Math.abs(paramInt);
      updateLayoutState(i, j, true, paramState);
      int k = mLayoutState.mScrollingOffset + fill(paramRecycler, mLayoutState, paramState, false);
      if (k < 0) {
        return 0;
      }
      if (j > k) {
        paramInt = i * k;
      }
      mOrientationHelper.offsetChildren(-paramInt);
      mLayoutState.mLastScrollDelta = paramInt;
      return paramInt;
    }
    return 0;
  }
  
  public int scrollHorizontallyBy(int paramInt, RecyclerView.Recycler paramRecycler, RecyclerView.State paramState)
  {
    if (mOrientation == 1) {
      return 0;
    }
    return scrollBy(paramInt, paramRecycler, paramState);
  }
  
  public void scrollToPosition(int paramInt)
  {
    mPendingScrollPosition = paramInt;
    mPendingScrollPositionOffset = Integer.MIN_VALUE;
    if (mPendingSavedState != null) {
      mPendingSavedState.invalidateAnchor();
    }
    requestLayout();
  }
  
  public void scrollToPositionWithOffset(int paramInt1, int paramInt2)
  {
    mPendingScrollPosition = paramInt1;
    mPendingScrollPositionOffset = paramInt2;
    if (mPendingSavedState != null) {
      mPendingSavedState.invalidateAnchor();
    }
    requestLayout();
  }
  
  public int scrollVerticallyBy(int paramInt, RecyclerView.Recycler paramRecycler, RecyclerView.State paramState)
  {
    if (mOrientation == 0) {
      return 0;
    }
    return scrollBy(paramInt, paramRecycler, paramState);
  }
  
  public void setInitialPrefetchItemCount(int paramInt)
  {
    mInitialPrefetchItemCount = paramInt;
  }
  
  public void setOrientation(int paramInt)
  {
    if ((paramInt != 0) && (paramInt != 1))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("invalid orientation:");
      localStringBuilder.append(paramInt);
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    assertNotInLayoutOrScroll(null);
    if ((paramInt != mOrientation) || (mOrientationHelper == null))
    {
      mOrientationHelper = OrientationHelper.createOrientationHelper(this, paramInt);
      mAnchorInfo.mOrientationHelper = mOrientationHelper;
      mOrientation = paramInt;
      requestLayout();
    }
  }
  
  public void setRecycleChildrenOnDetach(boolean paramBoolean)
  {
    mRecycleChildrenOnDetach = paramBoolean;
  }
  
  public void setReverseLayout(boolean paramBoolean)
  {
    assertNotInLayoutOrScroll(null);
    if (paramBoolean == mReverseLayout) {
      return;
    }
    mReverseLayout = paramBoolean;
    requestLayout();
  }
  
  public void setSmoothScrollbarEnabled(boolean paramBoolean)
  {
    mSmoothScrollbarEnabled = paramBoolean;
  }
  
  public void setStackFromEnd(boolean paramBoolean)
  {
    assertNotInLayoutOrScroll(null);
    if (mStackFromEnd == paramBoolean) {
      return;
    }
    mStackFromEnd = paramBoolean;
    requestLayout();
  }
  
  boolean shouldMeasureTwice()
  {
    return (getHeightMode() != 1073741824) && (getWidthMode() != 1073741824) && (hasFlexibleChildInBothOrientations());
  }
  
  public void smoothScrollToPosition(RecyclerView paramRecyclerView, RecyclerView.State paramState, int paramInt)
  {
    paramRecyclerView = new LinearSmoothScroller(paramRecyclerView.getContext());
    paramRecyclerView.setTargetPosition(paramInt);
    startSmoothScroll(paramRecyclerView);
  }
  
  public boolean supportsPredictiveItemAnimations()
  {
    return (mPendingSavedState == null) && (mLastStackFromEnd == mStackFromEnd);
  }
  
  void validateChildOrder()
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("validating child count ");
    ((StringBuilder)localObject).append(getChildCount());
    Log.d("LinearLayoutManager", ((StringBuilder)localObject).toString());
    if (getChildCount() < 1) {
      return;
    }
    boolean bool2 = false;
    boolean bool1 = false;
    int j = getPosition(getChildAt(0));
    int k = mOrientationHelper.getDecoratedStart(getChildAt(0));
    int m;
    int n;
    if (mShouldReverseLayout)
    {
      i = 1;
      for (;;)
      {
        if (i >= getChildCount()) {
          return;
        }
        localObject = getChildAt(i);
        m = getPosition((View)localObject);
        n = mOrientationHelper.getDecoratedStart((View)localObject);
        if (m < j)
        {
          logChildren();
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("detected invalid position. loc invalid? ");
          if (n < k) {
            bool1 = true;
          }
          ((StringBuilder)localObject).append(bool1);
          throw new RuntimeException(((StringBuilder)localObject).toString());
        }
        if (n > k) {
          break;
        }
        i += 1;
      }
      logChildren();
      throw new RuntimeException("detected invalid location");
    }
    int i = 1;
    while (i < getChildCount())
    {
      localObject = getChildAt(i);
      m = getPosition((View)localObject);
      n = mOrientationHelper.getDecoratedStart((View)localObject);
      if (m < j)
      {
        logChildren();
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("detected invalid position. loc invalid? ");
        bool1 = bool2;
        if (n < k) {
          bool1 = true;
        }
        ((StringBuilder)localObject).append(bool1);
        throw new RuntimeException(((StringBuilder)localObject).toString());
      }
      if (n >= k)
      {
        i += 1;
      }
      else
      {
        logChildren();
        throw new RuntimeException("detected invalid location");
      }
    }
  }
  
  static class AnchorInfo
  {
    int mCoordinate;
    boolean mLayoutFromEnd;
    OrientationHelper mOrientationHelper;
    int mPosition;
    boolean mValid;
    
    AnchorInfo()
    {
      reset();
    }
    
    void assignCoordinateFromPadding()
    {
      int i;
      if (mLayoutFromEnd) {
        i = mOrientationHelper.getEndAfterPadding();
      } else {
        i = mOrientationHelper.getStartAfterPadding();
      }
      mCoordinate = i;
    }
    
    public void assignFromView(View paramView, int paramInt)
    {
      if (mLayoutFromEnd) {
        mCoordinate = (mOrientationHelper.getDecoratedEnd(paramView) + mOrientationHelper.getTotalSpaceChange());
      } else {
        mCoordinate = mOrientationHelper.getDecoratedStart(paramView);
      }
      mPosition = paramInt;
    }
    
    public void assignFromViewAndKeepVisibleRect(View paramView, int paramInt)
    {
      int i = mOrientationHelper.getTotalSpaceChange();
      if (i >= 0)
      {
        assignFromView(paramView, paramInt);
        return;
      }
      mPosition = paramInt;
      int j;
      int k;
      if (mLayoutFromEnd)
      {
        paramInt = mOrientationHelper.getEndAfterPadding() - i - mOrientationHelper.getDecoratedEnd(paramView);
        mCoordinate = (mOrientationHelper.getEndAfterPadding() - paramInt);
        if (paramInt > 0)
        {
          i = mOrientationHelper.getDecoratedMeasurement(paramView);
          j = mCoordinate;
          k = mOrientationHelper.getStartAfterPadding();
          i = j - i - (k + Math.min(mOrientationHelper.getDecoratedStart(paramView) - k, 0));
          if (i < 0) {
            mCoordinate += Math.min(paramInt, -i);
          }
        }
      }
      else
      {
        j = mOrientationHelper.getDecoratedStart(paramView);
        paramInt = j - mOrientationHelper.getStartAfterPadding();
        mCoordinate = j;
        if (paramInt > 0)
        {
          k = mOrientationHelper.getDecoratedMeasurement(paramView);
          int m = mOrientationHelper.getEndAfterPadding();
          int n = mOrientationHelper.getDecoratedEnd(paramView);
          i = mOrientationHelper.getEndAfterPadding() - Math.min(0, m - i - n) - (j + k);
          if (i < 0) {
            mCoordinate -= Math.min(paramInt, -i);
          }
        }
      }
    }
    
    boolean isViewValidAsAnchor(View paramView, RecyclerView.State paramState)
    {
      paramView = (RecyclerView.LayoutParams)paramView.getLayoutParams();
      return (!paramView.isItemRemoved()) && (paramView.getViewLayoutPosition() >= 0) && (paramView.getViewLayoutPosition() < paramState.getItemCount());
    }
    
    void reset()
    {
      mPosition = -1;
      mCoordinate = Integer.MIN_VALUE;
      mLayoutFromEnd = false;
      mValid = false;
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("AnchorInfo{mPosition=");
      localStringBuilder.append(mPosition);
      localStringBuilder.append(", mCoordinate=");
      localStringBuilder.append(mCoordinate);
      localStringBuilder.append(", mLayoutFromEnd=");
      localStringBuilder.append(mLayoutFromEnd);
      localStringBuilder.append(", mValid=");
      localStringBuilder.append(mValid);
      localStringBuilder.append('}');
      return localStringBuilder.toString();
    }
  }
  
  protected static class LayoutChunkResult
  {
    public int mConsumed;
    public boolean mFinished;
    public boolean mFocusable;
    public boolean mIgnoreConsumed;
    
    protected LayoutChunkResult() {}
    
    void resetInternal()
    {
      mConsumed = 0;
      mFinished = false;
      mIgnoreConsumed = false;
      mFocusable = false;
    }
  }
  
  static class LayoutState
  {
    static final int INVALID_LAYOUT = Integer.MIN_VALUE;
    static final int ITEM_DIRECTION_HEAD = -1;
    static final int ITEM_DIRECTION_TAIL = 1;
    static final int LAYOUT_END = 1;
    static final int LAYOUT_START = -1;
    static final int SCROLLING_OFFSET_NaN = Integer.MIN_VALUE;
    static final String TAG = "LLM#LayoutState";
    int mAvailable;
    int mCurrentPosition;
    int mExtra = 0;
    boolean mInfinite;
    boolean mIsPreLayout = false;
    int mItemDirection;
    int mLastScrollDelta;
    int mLayoutDirection;
    int mOffset;
    boolean mRecycle = true;
    List<RecyclerView.ViewHolder> mScrapList = null;
    int mScrollingOffset;
    
    LayoutState() {}
    
    private View nextViewFromScrapList()
    {
      int j = mScrapList.size();
      int i = 0;
      while (i < j)
      {
        View localView = mScrapList.get(i)).itemView;
        RecyclerView.LayoutParams localLayoutParams = (RecyclerView.LayoutParams)localView.getLayoutParams();
        if ((!localLayoutParams.isItemRemoved()) && (mCurrentPosition == localLayoutParams.getViewLayoutPosition()))
        {
          assignPositionFromScrapList(localView);
          return localView;
        }
        i += 1;
      }
      return null;
    }
    
    public void assignPositionFromScrapList()
    {
      assignPositionFromScrapList(null);
    }
    
    public void assignPositionFromScrapList(View paramView)
    {
      paramView = nextViewInLimitedList(paramView);
      if (paramView == null)
      {
        mCurrentPosition = -1;
        return;
      }
      mCurrentPosition = ((RecyclerView.LayoutParams)paramView.getLayoutParams()).getViewLayoutPosition();
    }
    
    boolean hasMore(RecyclerView.State paramState)
    {
      return (mCurrentPosition >= 0) && (mCurrentPosition < paramState.getItemCount());
    }
    
    void log()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("avail:");
      localStringBuilder.append(mAvailable);
      localStringBuilder.append(", ind:");
      localStringBuilder.append(mCurrentPosition);
      localStringBuilder.append(", dir:");
      localStringBuilder.append(mItemDirection);
      localStringBuilder.append(", offset:");
      localStringBuilder.append(mOffset);
      localStringBuilder.append(", layoutDir:");
      localStringBuilder.append(mLayoutDirection);
      Log.d("LLM#LayoutState", localStringBuilder.toString());
    }
    
    View next(RecyclerView.Recycler paramRecycler)
    {
      if (mScrapList != null) {
        return nextViewFromScrapList();
      }
      paramRecycler = paramRecycler.getViewForPosition(mCurrentPosition);
      mCurrentPosition += mItemDirection;
      return paramRecycler;
    }
    
    public View nextViewInLimitedList(View paramView)
    {
      Object localObject1 = mScrapList;
      Object localObject2 = this;
      int n = ((List)localObject1).size();
      localObject1 = null;
      int j = Integer.MAX_VALUE;
      int i = 0;
      while (i < n)
      {
        Object localObject4 = mScrapList;
        Object localObject3 = localObject2;
        localObject4 = getitemView;
        RecyclerView.LayoutParams localLayoutParams = (RecyclerView.LayoutParams)((View)localObject4).getLayoutParams();
        localObject2 = localObject1;
        int k = j;
        if (localObject4 != paramView) {
          if (localLayoutParams.isItemRemoved())
          {
            localObject2 = localObject1;
            k = j;
          }
          else
          {
            int m = (localLayoutParams.getViewLayoutPosition() - mCurrentPosition) * mItemDirection;
            if (m < 0)
            {
              localObject2 = localObject1;
              k = j;
            }
            else
            {
              localObject2 = localObject1;
              k = j;
              if (m < j)
              {
                if (m == 0) {
                  return localObject4;
                }
                localObject2 = localObject4;
                k = m;
              }
            }
          }
        }
        i += 1;
        localObject1 = localObject2;
        j = k;
        localObject2 = localObject3;
      }
      return localObject1;
    }
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static class SavedState
    implements Parcelable
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator()
    {
      public LinearLayoutManager.SavedState createFromParcel(Parcel paramAnonymousParcel)
      {
        return new LinearLayoutManager.SavedState(paramAnonymousParcel);
      }
      
      public LinearLayoutManager.SavedState[] newArray(int paramAnonymousInt)
      {
        return new LinearLayoutManager.SavedState[paramAnonymousInt];
      }
    };
    boolean mAnchorLayoutFromEnd;
    int mAnchorOffset;
    int mAnchorPosition;
    
    public SavedState() {}
    
    SavedState(Parcel paramParcel)
    {
      mAnchorPosition = paramParcel.readInt();
      mAnchorOffset = paramParcel.readInt();
      int i = paramParcel.readInt();
      boolean bool = true;
      if (i != 1) {
        bool = false;
      }
      mAnchorLayoutFromEnd = bool;
    }
    
    public SavedState(SavedState paramSavedState)
    {
      mAnchorPosition = mAnchorPosition;
      mAnchorOffset = mAnchorOffset;
      mAnchorLayoutFromEnd = mAnchorLayoutFromEnd;
    }
    
    public int describeContents()
    {
      return 0;
    }
    
    boolean hasValidAnchor()
    {
      return mAnchorPosition >= 0;
    }
    
    void invalidateAnchor()
    {
      mAnchorPosition = -1;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e1expr(TypeTransformer.java:496)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:713)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:698)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
    }
  }
}
