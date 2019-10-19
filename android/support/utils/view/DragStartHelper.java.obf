package android.support.v13.view;

import android.graphics.Point;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;

public class DragStartHelper
{
  private boolean mDragging;
  private int mLastTouchX;
  private int mLastTouchY;
  private final OnDragStartListener mListener;
  private final View.OnLongClickListener mLongClickListener = new View.OnLongClickListener()
  {
    public boolean onLongClick(View paramAnonymousView)
    {
      return DragStartHelper.this.onLongClick(paramAnonymousView);
    }
  };
  private final View.OnTouchListener mTouchListener = new View.OnTouchListener()
  {
    public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
    {
      return DragStartHelper.this.onTouch(paramAnonymousView, paramAnonymousMotionEvent);
    }
  };
  private final View mView;
  
  public DragStartHelper(View paramView, OnDragStartListener paramOnDragStartListener)
  {
    mView = paramView;
    mListener = paramOnDragStartListener;
  }
  
  public void attach()
  {
    mView.setOnLongClickListener(mLongClickListener);
    mView.setOnTouchListener(mTouchListener);
  }
  
  public void detach()
  {
    mView.setOnLongClickListener(null);
    mView.setOnTouchListener(null);
  }
  
  public void getTouchPosition(Point paramPoint)
  {
    paramPoint.set(mLastTouchX, mLastTouchY);
  }
  
  public boolean onLongClick(View paramView)
  {
    return mListener.onDragStart(paramView, this);
  }
  
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    int i = (int)paramMotionEvent.getX();
    int j = (int)paramMotionEvent.getY();
    switch (paramMotionEvent.getAction())
    {
    default: 
      return false;
    case 2: 
      if (MotionEventCompat.isFromSource(paramMotionEvent, 8194))
      {
        if ((paramMotionEvent.getButtonState() & 0x1) == 0) {
          return false;
        }
        if (mDragging) {
          return false;
        }
        if ((mLastTouchX == i) && (mLastTouchY == j)) {
          return false;
        }
        mLastTouchX = i;
        mLastTouchY = j;
        mDragging = mListener.onDragStart(paramView, this);
        return mDragging;
      }
      break;
    case 1: 
    case 3: 
      mDragging = false;
      return false;
    case 0: 
      mLastTouchX = i;
      mLastTouchY = j;
    }
    return false;
  }
  
  public static abstract interface OnDragStartListener
  {
    public abstract boolean onDragStart(View paramView, DragStartHelper paramDragStartHelper);
  }
}
