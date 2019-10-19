package android.support.v7.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.view.ViewCompat;
import android.view.MotionEvent;
import android.view.View;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@VisibleForTesting
class FastScroller
  extends RecyclerView.ItemDecoration
  implements RecyclerView.OnItemTouchListener
{
  private static final int ANIMATION_STATE_FADING_IN = 1;
  private static final int ANIMATION_STATE_FADING_OUT = 3;
  private static final int ANIMATION_STATE_IN = 2;
  private static final int ANIMATION_STATE_OUT = 0;
  private static final int DRAG_NONE = 0;
  private static final int DRAG_X = 1;
  private static final int DRAG_Y = 2;
  private static final int[] EMPTY_STATE_SET = new int[0];
  private static final int HIDE_DELAY_AFTER_DRAGGING_MS = 1200;
  private static final int HIDE_DELAY_AFTER_VISIBLE_MS = 1500;
  private static final int HIDE_DURATION_MS = 500;
  private static final int[] PRESSED_STATE_SET = { 16842919 };
  private static final int SCROLLBAR_FULL_OPAQUE = 255;
  private static final int SHOW_DURATION_MS = 500;
  private static final int STATE_DRAGGING = 2;
  private static final int STATE_HIDDEN = 0;
  private static final int STATE_VISIBLE = 1;
  private int mAnimationState = 0;
  private int mDragState = 0;
  private final Runnable mHideRunnable = new Runnable()
  {
    public void run()
    {
      hide(500);
    }
  };
  @VisibleForTesting
  float mHorizontalDragX;
  private final int[] mHorizontalRange = new int[2];
  @VisibleForTesting
  int mHorizontalThumbCenterX;
  private final StateListDrawable mHorizontalThumbDrawable;
  private final int mHorizontalThumbHeight;
  @VisibleForTesting
  int mHorizontalThumbWidth;
  private final Drawable mHorizontalTrackDrawable;
  private final int mHorizontalTrackHeight;
  private final int mMargin;
  private boolean mNeedHorizontalScrollbar = false;
  private boolean mNeedVerticalScrollbar = false;
  private final RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener()
  {
    public void onScrolled(RecyclerView paramAnonymousRecyclerView, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      updateScrollPosition(paramAnonymousRecyclerView.computeHorizontalScrollOffset(), paramAnonymousRecyclerView.computeVerticalScrollOffset());
    }
  };
  private RecyclerView mRecyclerView;
  private int mRecyclerViewHeight = 0;
  private int mRecyclerViewWidth = 0;
  private final int mScrollbarMinimumRange;
  private final ValueAnimator mShowHideAnimator = ValueAnimator.ofFloat(new float[] { 0.0F, 1.0F });
  private int mState = 0;
  @VisibleForTesting
  float mVerticalDragY;
  private final int[] mVerticalRange = new int[2];
  @VisibleForTesting
  int mVerticalThumbCenterY;
  private final StateListDrawable mVerticalThumbDrawable;
  @VisibleForTesting
  int mVerticalThumbHeight;
  private final int mVerticalThumbWidth;
  private final Drawable mVerticalTrackDrawable;
  private final int mVerticalTrackWidth;
  
  FastScroller(RecyclerView paramRecyclerView, StateListDrawable paramStateListDrawable1, Drawable paramDrawable1, StateListDrawable paramStateListDrawable2, Drawable paramDrawable2, int paramInt1, int paramInt2, int paramInt3)
  {
    mVerticalThumbDrawable = paramStateListDrawable1;
    mVerticalTrackDrawable = paramDrawable1;
    mHorizontalThumbDrawable = paramStateListDrawable2;
    mHorizontalTrackDrawable = paramDrawable2;
    mVerticalThumbWidth = Math.max(paramInt1, paramStateListDrawable1.getIntrinsicWidth());
    mVerticalTrackWidth = Math.max(paramInt1, paramDrawable1.getIntrinsicWidth());
    mHorizontalThumbHeight = Math.max(paramInt1, paramStateListDrawable2.getIntrinsicWidth());
    mHorizontalTrackHeight = Math.max(paramInt1, paramDrawable2.getIntrinsicWidth());
    mScrollbarMinimumRange = paramInt2;
    mMargin = paramInt3;
    mVerticalThumbDrawable.setAlpha(255);
    mVerticalTrackDrawable.setAlpha(255);
    mShowHideAnimator.addListener(new AnimatorListener(null));
    mShowHideAnimator.addUpdateListener(new AnimatorUpdater(null));
    attachToRecyclerView(paramRecyclerView);
  }
  
  private void cancelHide()
  {
    mRecyclerView.removeCallbacks(mHideRunnable);
  }
  
  private void destroyCallbacks()
  {
    mRecyclerView.removeItemDecoration(this);
    mRecyclerView.removeOnItemTouchListener(this);
    mRecyclerView.removeOnScrollListener(mOnScrollListener);
    cancelHide();
  }
  
  private void drawHorizontalScrollbar(Canvas paramCanvas)
  {
    int i = mRecyclerViewHeight - mHorizontalThumbHeight;
    int j = mHorizontalThumbCenterX - mHorizontalThumbWidth / 2;
    mHorizontalThumbDrawable.setBounds(0, 0, mHorizontalThumbWidth, mHorizontalThumbHeight);
    mHorizontalTrackDrawable.setBounds(0, 0, mRecyclerViewWidth, mHorizontalTrackHeight);
    paramCanvas.translate(0.0F, i);
    mHorizontalTrackDrawable.draw(paramCanvas);
    paramCanvas.translate(j, 0.0F);
    mHorizontalThumbDrawable.draw(paramCanvas);
    paramCanvas.translate(-j, -i);
  }
  
  private void drawVerticalScrollbar(Canvas paramCanvas)
  {
    int i = mRecyclerViewWidth - mVerticalThumbWidth;
    int j = mVerticalThumbCenterY - mVerticalThumbHeight / 2;
    mVerticalThumbDrawable.setBounds(0, 0, mVerticalThumbWidth, mVerticalThumbHeight);
    mVerticalTrackDrawable.setBounds(0, 0, mVerticalTrackWidth, mRecyclerViewHeight);
    if (isLayoutRTL())
    {
      mVerticalTrackDrawable.draw(paramCanvas);
      paramCanvas.translate(mVerticalThumbWidth, j);
      paramCanvas.scale(-1.0F, 1.0F);
      mVerticalThumbDrawable.draw(paramCanvas);
      paramCanvas.scale(1.0F, 1.0F);
      paramCanvas.translate(-mVerticalThumbWidth, -j);
      return;
    }
    paramCanvas.translate(i, 0.0F);
    mVerticalTrackDrawable.draw(paramCanvas);
    paramCanvas.translate(0.0F, j);
    mVerticalThumbDrawable.draw(paramCanvas);
    paramCanvas.translate(-i, -j);
  }
  
  private int[] getHorizontalRange()
  {
    mHorizontalRange[0] = mMargin;
    mHorizontalRange[1] = (mRecyclerViewWidth - mMargin);
    return mHorizontalRange;
  }
  
  private int[] getVerticalRange()
  {
    mVerticalRange[0] = mMargin;
    mVerticalRange[1] = (mRecyclerViewHeight - mMargin);
    return mVerticalRange;
  }
  
  private void horizontalScrollTo(float paramFloat)
  {
    int[] arrayOfInt = getHorizontalRange();
    paramFloat = Math.max(arrayOfInt[0], Math.min(arrayOfInt[1], paramFloat));
    if (Math.abs(mHorizontalThumbCenterX - paramFloat) < 2.0F) {
      return;
    }
    int i = scrollTo(mHorizontalDragX, paramFloat, arrayOfInt, mRecyclerView.computeHorizontalScrollRange(), mRecyclerView.computeHorizontalScrollOffset(), mRecyclerViewWidth);
    if (i != 0) {
      mRecyclerView.scrollBy(i, 0);
    }
    mHorizontalDragX = paramFloat;
  }
  
  private boolean isLayoutRTL()
  {
    return ViewCompat.getLayoutDirection(mRecyclerView) == 1;
  }
  
  private void requestRedraw()
  {
    mRecyclerView.invalidate();
  }
  
  private void resetHideDelay(int paramInt)
  {
    cancelHide();
    mRecyclerView.postDelayed(mHideRunnable, paramInt);
  }
  
  private int scrollTo(float paramFloat1, float paramFloat2, int[] paramArrayOfInt, int paramInt1, int paramInt2, int paramInt3)
  {
    int i = paramArrayOfInt[1] - paramArrayOfInt[0];
    if (i == 0) {
      return 0;
    }
    paramFloat1 = (paramFloat2 - paramFloat1) / i;
    paramInt1 -= paramInt3;
    paramInt3 = (int)(paramFloat1 * paramInt1);
    paramInt2 += paramInt3;
    if ((paramInt2 < paramInt1) && (paramInt2 >= 0)) {
      return paramInt3;
    }
    return 0;
  }
  
  private void setState(int paramInt)
  {
    if ((paramInt == 2) && (mState != 2))
    {
      mVerticalThumbDrawable.setState(PRESSED_STATE_SET);
      cancelHide();
    }
    if (paramInt == 0) {
      requestRedraw();
    } else {
      show();
    }
    if ((mState == 2) && (paramInt != 2))
    {
      mVerticalThumbDrawable.setState(EMPTY_STATE_SET);
      resetHideDelay(1200);
    }
    else if (paramInt == 1)
    {
      resetHideDelay(1500);
    }
    mState = paramInt;
  }
  
  private void setupCallbacks()
  {
    mRecyclerView.addItemDecoration(this);
    mRecyclerView.addOnItemTouchListener(this);
    mRecyclerView.addOnScrollListener(mOnScrollListener);
  }
  
  private void verticalScrollTo(float paramFloat)
  {
    int[] arrayOfInt = getVerticalRange();
    paramFloat = Math.max(arrayOfInt[0], Math.min(arrayOfInt[1], paramFloat));
    if (Math.abs(mVerticalThumbCenterY - paramFloat) < 2.0F) {
      return;
    }
    int i = scrollTo(mVerticalDragY, paramFloat, arrayOfInt, mRecyclerView.computeVerticalScrollRange(), mRecyclerView.computeVerticalScrollOffset(), mRecyclerViewHeight);
    if (i != 0) {
      mRecyclerView.scrollBy(0, i);
    }
    mVerticalDragY = paramFloat;
  }
  
  public void attachToRecyclerView(RecyclerView paramRecyclerView)
  {
    if (mRecyclerView == paramRecyclerView) {
      return;
    }
    if (mRecyclerView != null) {
      destroyCallbacks();
    }
    mRecyclerView = paramRecyclerView;
    if (mRecyclerView != null) {
      setupCallbacks();
    }
  }
  
  Drawable getHorizontalThumbDrawable()
  {
    return mHorizontalThumbDrawable;
  }
  
  Drawable getHorizontalTrackDrawable()
  {
    return mHorizontalTrackDrawable;
  }
  
  Drawable getVerticalThumbDrawable()
  {
    return mVerticalThumbDrawable;
  }
  
  Drawable getVerticalTrackDrawable()
  {
    return mVerticalTrackDrawable;
  }
  
  public void hide()
  {
    hide(0);
  }
  
  void hide(int paramInt)
  {
    switch (mAnimationState)
    {
    default: 
      return;
    case 1: 
      mShowHideAnimator.cancel();
    }
    mAnimationState = 3;
    mShowHideAnimator.setFloatValues(new float[] { ((Float)mShowHideAnimator.getAnimatedValue()).floatValue(), 0.0F });
    mShowHideAnimator.setDuration(paramInt);
    mShowHideAnimator.start();
  }
  
  public boolean isDragging()
  {
    return mState == 2;
  }
  
  boolean isHidden()
  {
    return mState == 0;
  }
  
  boolean isPointInsideHorizontalThumb(float paramFloat1, float paramFloat2)
  {
    return (paramFloat2 >= mRecyclerViewHeight - mHorizontalThumbHeight) && (paramFloat1 >= mHorizontalThumbCenterX - mHorizontalThumbWidth / 2) && (paramFloat1 <= mHorizontalThumbCenterX + mHorizontalThumbWidth / 2);
  }
  
  boolean isPointInsideVerticalThumb(float paramFloat1, float paramFloat2)
  {
    return (isLayoutRTL() ? paramFloat1 <= mVerticalThumbWidth / 2 : paramFloat1 >= mRecyclerViewWidth - mVerticalThumbWidth) && (paramFloat2 >= mVerticalThumbCenterY - mVerticalThumbHeight / 2) && (paramFloat2 <= mVerticalThumbCenterY + mVerticalThumbHeight / 2);
  }
  
  boolean isVisible()
  {
    return mState == 1;
  }
  
  public void onDrawOver(Canvas paramCanvas, RecyclerView paramRecyclerView, RecyclerView.State paramState)
  {
    if ((mRecyclerViewWidth == mRecyclerView.getWidth()) && (mRecyclerViewHeight == mRecyclerView.getHeight()))
    {
      if (mAnimationState != 0)
      {
        if (mNeedVerticalScrollbar) {
          drawVerticalScrollbar(paramCanvas);
        }
        if (mNeedHorizontalScrollbar) {
          drawHorizontalScrollbar(paramCanvas);
        }
      }
    }
    else
    {
      mRecyclerViewWidth = mRecyclerView.getWidth();
      mRecyclerViewHeight = mRecyclerView.getHeight();
      setState(0);
    }
  }
  
  public boolean onInterceptTouchEvent(RecyclerView paramRecyclerView, MotionEvent paramMotionEvent)
  {
    if (mState == 1)
    {
      boolean bool1 = isPointInsideVerticalThumb(paramMotionEvent.getX(), paramMotionEvent.getY());
      boolean bool2 = isPointInsideHorizontalThumb(paramMotionEvent.getX(), paramMotionEvent.getY());
      if ((paramMotionEvent.getAction() != 0) || ((!bool1) && (!bool2))) {
        break label111;
      }
      if (bool2)
      {
        mDragState = 1;
        mHorizontalDragX = ((int)paramMotionEvent.getX());
      }
      else if (bool1)
      {
        mDragState = 2;
        mVerticalDragY = ((int)paramMotionEvent.getY());
      }
      setState(2);
    }
    else
    {
      if (mState != 2) {
        break label111;
      }
    }
    return true;
    label111:
    return false;
  }
  
  public void onRequestDisallowInterceptTouchEvent(boolean paramBoolean) {}
  
  public void onTouchEvent(RecyclerView paramRecyclerView, MotionEvent paramMotionEvent)
  {
    if (mState == 0) {
      return;
    }
    if (paramMotionEvent.getAction() == 0)
    {
      boolean bool1 = isPointInsideVerticalThumb(paramMotionEvent.getX(), paramMotionEvent.getY());
      boolean bool2 = isPointInsideHorizontalThumb(paramMotionEvent.getX(), paramMotionEvent.getY());
      if ((bool1) || (bool2))
      {
        if (bool2)
        {
          mDragState = 1;
          mHorizontalDragX = ((int)paramMotionEvent.getX());
        }
        else if (bool1)
        {
          mDragState = 2;
          mVerticalDragY = ((int)paramMotionEvent.getY());
        }
        setState(2);
      }
    }
    else
    {
      if ((paramMotionEvent.getAction() == 1) && (mState == 2))
      {
        mVerticalDragY = 0.0F;
        mHorizontalDragX = 0.0F;
        setState(1);
        mDragState = 0;
        return;
      }
      if ((paramMotionEvent.getAction() == 2) && (mState == 2))
      {
        show();
        if (mDragState == 1) {
          horizontalScrollTo(paramMotionEvent.getX());
        }
        if (mDragState == 2) {
          verticalScrollTo(paramMotionEvent.getY());
        }
      }
    }
  }
  
  public void show()
  {
    int i = mAnimationState;
    if (i != 0)
    {
      if (i != 3) {
        return;
      }
      mShowHideAnimator.cancel();
    }
    mAnimationState = 1;
    mShowHideAnimator.setFloatValues(new float[] { ((Float)mShowHideAnimator.getAnimatedValue()).floatValue(), 1.0F });
    mShowHideAnimator.setDuration(500L);
    mShowHideAnimator.setStartDelay(0L);
    mShowHideAnimator.start();
  }
  
  void updateScrollPosition(int paramInt1, int paramInt2)
  {
    int i = mRecyclerView.computeVerticalScrollRange();
    int j = mRecyclerViewHeight;
    boolean bool;
    if ((i - j > 0) && (mRecyclerViewHeight >= mScrollbarMinimumRange)) {
      bool = true;
    } else {
      bool = false;
    }
    mNeedVerticalScrollbar = bool;
    int k = mRecyclerView.computeHorizontalScrollRange();
    int m = mRecyclerViewWidth;
    if ((k - m > 0) && (mRecyclerViewWidth >= mScrollbarMinimumRange)) {
      bool = true;
    } else {
      bool = false;
    }
    mNeedHorizontalScrollbar = bool;
    if ((!mNeedVerticalScrollbar) && (!mNeedHorizontalScrollbar))
    {
      if (mState != 0) {
        setState(0);
      }
    }
    else
    {
      float f1;
      float f2;
      if (mNeedVerticalScrollbar)
      {
        f1 = paramInt2;
        f2 = j;
        mVerticalThumbCenterY = ((int)(f2 * (f1 + f2 / 2.0F) / i));
        mVerticalThumbHeight = Math.min(j, j * j / i);
      }
      if (mNeedHorizontalScrollbar)
      {
        f1 = paramInt1;
        f2 = m;
        mHorizontalThumbCenterX = ((int)(f2 * (f1 + f2 / 2.0F) / k));
        mHorizontalThumbWidth = Math.min(m, m * m / k);
      }
      if ((mState == 0) || (mState == 1)) {
        setState(1);
      }
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  private static @interface AnimationState {}
  
  private class AnimatorListener
    extends AnimatorListenerAdapter
  {
    private boolean mCanceled = false;
    
    private AnimatorListener() {}
    
    public void onAnimationCancel(Animator paramAnimator)
    {
      mCanceled = true;
    }
    
    public void onAnimationEnd(Animator paramAnimator)
    {
      if (mCanceled)
      {
        mCanceled = false;
        return;
      }
      if (((Float)mShowHideAnimator.getAnimatedValue()).floatValue() == 0.0F)
      {
        FastScroller.access$302(FastScroller.this, 0);
        FastScroller.this.setState(0);
        return;
      }
      FastScroller.access$302(FastScroller.this, 2);
      FastScroller.this.requestRedraw();
    }
  }
  
  private class AnimatorUpdater
    implements ValueAnimator.AnimatorUpdateListener
  {
    private AnimatorUpdater() {}
    
    public void onAnimationUpdate(ValueAnimator paramValueAnimator)
    {
      int i = (int)(((Float)paramValueAnimator.getAnimatedValue()).floatValue() * 255.0F);
      mVerticalThumbDrawable.setAlpha(i);
      mVerticalTrackDrawable.setAlpha(i);
      FastScroller.this.requestRedraw();
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  private static @interface DragState {}
  
  @Retention(RetentionPolicy.SOURCE)
  private static @interface State {}
}
