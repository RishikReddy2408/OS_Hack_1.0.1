package android.support.v7.widget;

import android.view.View;

class LayoutState
{
  static final int INVALID_LAYOUT = Integer.MIN_VALUE;
  static final int ITEM_DIRECTION_HEAD = -1;
  static final int ITEM_DIRECTION_TAIL = 1;
  static final int LAYOUT_END = 1;
  static final int LAYOUT_START = -1;
  static final String TAG = "LayoutState";
  int mAvailable;
  int mCurrentPosition;
  int mEndLine = 0;
  boolean mInfinite;
  int mItemDirection;
  int mLayoutDirection;
  boolean mRecycle = true;
  int mStartLine = 0;
  boolean mStopInFocusable;
  
  LayoutState() {}
  
  boolean hasMore(RecyclerView.State paramState)
  {
    return (mCurrentPosition >= 0) && (mCurrentPosition < paramState.getItemCount());
  }
  
  View next(RecyclerView.Recycler paramRecycler)
  {
    paramRecycler = paramRecycler.getViewForPosition(mCurrentPosition);
    mCurrentPosition += mItemDirection;
    return paramRecycler;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("LayoutState{mAvailable=");
    localStringBuilder.append(mAvailable);
    localStringBuilder.append(", mCurrentPosition=");
    localStringBuilder.append(mCurrentPosition);
    localStringBuilder.append(", mItemDirection=");
    localStringBuilder.append(mItemDirection);
    localStringBuilder.append(", mLayoutDirection=");
    localStringBuilder.append(mLayoutDirection);
    localStringBuilder.append(", mStartLine=");
    localStringBuilder.append(mStartLine);
    localStringBuilder.append(", mEndLine=");
    localStringBuilder.append(mEndLine);
    localStringBuilder.append('}');
    return localStringBuilder.toString();
  }
}
