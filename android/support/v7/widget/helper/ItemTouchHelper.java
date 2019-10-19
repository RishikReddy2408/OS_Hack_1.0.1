package android.support.v7.widget.helper;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.recyclerview.R.dimen;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ChildDrawingOrderCallback;
import android.support.v7.widget.RecyclerView.ItemAnimator;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.OnChildAttachStateChangeListener;
import android.support.v7.widget.RecyclerView.OnItemTouchListener;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.List;

public class ItemTouchHelper
  extends RecyclerView.ItemDecoration
  implements RecyclerView.OnChildAttachStateChangeListener
{
  static final int ACTION_MODE_DRAG_MASK = 16711680;
  private static final int ACTION_MODE_IDLE_MASK = 255;
  static final int ACTION_MODE_SWIPE_MASK = 65280;
  public static final int ACTION_STATE_DRAG = 2;
  public static final int ACTION_STATE_IDLE = 0;
  public static final int ACTION_STATE_SWIPE = 1;
  static final int ACTIVE_POINTER_ID_NONE = -1;
  public static final int ANIMATION_TYPE_DRAG = 8;
  public static final int ANIMATION_TYPE_SWIPE_CANCEL = 4;
  public static final int ANIMATION_TYPE_SWIPE_SUCCESS = 2;
  static final boolean DEBUG = false;
  static final int DIRECTION_FLAG_COUNT = 8;
  public static final int DOWN = 2;
  public static final int END = 32;
  public static final int LEFT = 4;
  private static final int PIXELS_PER_SECOND = 1000;
  public static final int RIGHT = 8;
  public static final int START = 16;
  static final String TAG = "ItemTouchHelper";
  public static final int UP = 1;
  int mActionState = 0;
  int mActivePointerId = -1;
  Callback mCallback;
  private RecyclerView.ChildDrawingOrderCallback mChildDrawingOrderCallback = null;
  private List<Integer> mDistances;
  private long mDragScrollStartTimeInMs;
  float mDx;
  float mDy;
  GestureDetectorCompat mGestureDetector;
  float mInitialTouchX;
  float mInitialTouchY;
  private ItemTouchHelperGestureListener mItemTouchHelperGestureListener;
  float mMaxSwipeVelocity;
  private final RecyclerView.OnItemTouchListener mOnItemTouchListener = new RecyclerView.OnItemTouchListener()
  {
    public boolean onInterceptTouchEvent(RecyclerView paramAnonymousRecyclerView, MotionEvent paramAnonymousMotionEvent)
    {
      mGestureDetector.onTouchEvent(paramAnonymousMotionEvent);
      int i = paramAnonymousMotionEvent.getActionMasked();
      if (i == 0)
      {
        mActivePointerId = paramAnonymousMotionEvent.getPointerId(0);
        mInitialTouchX = paramAnonymousMotionEvent.getX();
        mInitialTouchY = paramAnonymousMotionEvent.getY();
        obtainVelocityTracker();
        if (mSelected == null)
        {
          paramAnonymousRecyclerView = findAnimation(paramAnonymousMotionEvent);
          if (paramAnonymousRecyclerView != null)
          {
            ItemTouchHelper localItemTouchHelper = ItemTouchHelper.this;
            mInitialTouchX -= mX;
            localItemTouchHelper = ItemTouchHelper.this;
            mInitialTouchY -= mY;
            endRecoverAnimation(mViewHolder, true);
            if (mPendingCleanup.remove(mViewHolder.itemView)) {
              mCallback.clearView(mRecyclerView, mViewHolder);
            }
            select(mViewHolder, mActionState);
            updateDxDy(paramAnonymousMotionEvent, mSelectedFlags, 0);
          }
        }
      }
      else if ((i != 3) && (i != 1))
      {
        if (mActivePointerId != -1)
        {
          int j = paramAnonymousMotionEvent.findPointerIndex(mActivePointerId);
          if (j >= 0) {
            checkSelectForSwipe(i, paramAnonymousMotionEvent, j);
          }
        }
      }
      else
      {
        mActivePointerId = -1;
        select(null, 0);
      }
      if (mVelocityTracker != null) {
        mVelocityTracker.addMovement(paramAnonymousMotionEvent);
      }
      return mSelected != null;
    }
    
    public void onRequestDisallowInterceptTouchEvent(boolean paramAnonymousBoolean)
    {
      if (!paramAnonymousBoolean) {
        return;
      }
      select(null, 0);
    }
    
    public void onTouchEvent(RecyclerView paramAnonymousRecyclerView, MotionEvent paramAnonymousMotionEvent)
    {
      mGestureDetector.onTouchEvent(paramAnonymousMotionEvent);
      if (mVelocityTracker != null) {
        mVelocityTracker.addMovement(paramAnonymousMotionEvent);
      }
      if (mActivePointerId == -1) {
        return;
      }
      int j = paramAnonymousMotionEvent.getActionMasked();
      int k = paramAnonymousMotionEvent.findPointerIndex(mActivePointerId);
      if (k >= 0) {
        checkSelectForSwipe(j, paramAnonymousMotionEvent, k);
      }
      paramAnonymousRecyclerView = mSelected;
      if (paramAnonymousRecyclerView == null) {
        return;
      }
      int i = 0;
      if (j != 6)
      {
        switch (j)
        {
        default: 
          return;
        case 3: 
          if (mVelocityTracker != null) {
            mVelocityTracker.clear();
          }
          break;
        case 2: 
          if (k < 0) {
            return;
          }
          updateDxDy(paramAnonymousMotionEvent, mSelectedFlags, k);
          moveIfNecessary(paramAnonymousRecyclerView);
          mRecyclerView.removeCallbacks(mScrollRunnable);
          mScrollRunnable.run();
          mRecyclerView.invalidate();
          return;
        }
        select(null, 0);
        mActivePointerId = -1;
        return;
      }
      j = paramAnonymousMotionEvent.getActionIndex();
      if (paramAnonymousMotionEvent.getPointerId(j) == mActivePointerId)
      {
        if (j == 0) {
          i = 1;
        }
        mActivePointerId = paramAnonymousMotionEvent.getPointerId(i);
        updateDxDy(paramAnonymousMotionEvent, mSelectedFlags, j);
      }
    }
  };
  View mOverdrawChild = null;
  int mOverdrawChildPosition = -1;
  final List<View> mPendingCleanup = new ArrayList();
  List<RecoverAnimation> mRecoverAnimations = new ArrayList();
  RecyclerView mRecyclerView;
  final Runnable mScrollRunnable = new Runnable()
  {
    public void run()
    {
      if ((mSelected != null) && (scrollIfNecessary()))
      {
        if (mSelected != null) {
          moveIfNecessary(mSelected);
        }
        mRecyclerView.removeCallbacks(mScrollRunnable);
        ViewCompat.postOnAnimation(mRecyclerView, this);
      }
    }
  };
  RecyclerView.ViewHolder mSelected = null;
  int mSelectedFlags;
  float mSelectedStartX;
  float mSelectedStartY;
  private int mSlop;
  private List<RecyclerView.ViewHolder> mSwapTargets;
  float mSwipeEscapeVelocity;
  private final float[] mTmpPosition = new float[2];
  private Rect mTmpRect;
  VelocityTracker mVelocityTracker;
  
  public ItemTouchHelper(Callback paramCallback)
  {
    mCallback = paramCallback;
  }
  
  private void addChildDrawingOrderCallback()
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return;
    }
    if (mChildDrawingOrderCallback == null) {
      mChildDrawingOrderCallback = new RecyclerView.ChildDrawingOrderCallback()
      {
        public int onGetChildDrawingOrder(int paramAnonymousInt1, int paramAnonymousInt2)
        {
          if (mOverdrawChild == null) {
            return paramAnonymousInt2;
          }
          int j = mOverdrawChildPosition;
          int i = j;
          if (j == -1)
          {
            j = mRecyclerView.indexOfChild(mOverdrawChild);
            i = j;
            mOverdrawChildPosition = j;
          }
          if (paramAnonymousInt2 == paramAnonymousInt1 - 1) {
            return i;
          }
          if (paramAnonymousInt2 < i) {
            return paramAnonymousInt2;
          }
          return paramAnonymousInt2 + 1;
        }
      };
    }
    mRecyclerView.setChildDrawingOrderCallback(mChildDrawingOrderCallback);
  }
  
  private int checkHorizontalSwipe(RecyclerView.ViewHolder paramViewHolder, int paramInt)
  {
    if ((paramInt & 0xC) != 0)
    {
      float f1 = mDx;
      int j = 4;
      int i;
      if (f1 > 0.0F) {
        i = 8;
      } else {
        i = 4;
      }
      if ((mVelocityTracker != null) && (mActivePointerId > -1))
      {
        mVelocityTracker.computeCurrentVelocity(1000, mCallback.getSwipeVelocityThreshold(mMaxSwipeVelocity));
        f2 = mVelocityTracker.getXVelocity(mActivePointerId);
        f1 = mVelocityTracker.getYVelocity(mActivePointerId);
        if (f2 > 0.0F) {
          j = 8;
        }
        f2 = Math.abs(f2);
        if (((j & paramInt) != 0) && (i == j) && (f2 >= mCallback.getSwipeEscapeVelocity(mSwipeEscapeVelocity)) && (f2 > Math.abs(f1))) {
          return j;
        }
      }
      f1 = mRecyclerView.getWidth();
      float f2 = mCallback.getSwipeThreshold(paramViewHolder);
      if (((paramInt & i) != 0) && (Math.abs(mDx) > f1 * f2)) {
        return i;
      }
    }
    return 0;
  }
  
  private int checkVerticalSwipe(RecyclerView.ViewHolder paramViewHolder, int paramInt)
  {
    if ((paramInt & 0x3) != 0)
    {
      float f1 = mDy;
      int j = 1;
      int i;
      if (f1 > 0.0F) {
        i = 2;
      } else {
        i = 1;
      }
      if ((mVelocityTracker != null) && (mActivePointerId > -1))
      {
        mVelocityTracker.computeCurrentVelocity(1000, mCallback.getSwipeVelocityThreshold(mMaxSwipeVelocity));
        f1 = mVelocityTracker.getXVelocity(mActivePointerId);
        f2 = mVelocityTracker.getYVelocity(mActivePointerId);
        if (f2 > 0.0F) {
          j = 2;
        }
        f2 = Math.abs(f2);
        if (((j & paramInt) != 0) && (j == i) && (f2 >= mCallback.getSwipeEscapeVelocity(mSwipeEscapeVelocity)) && (f2 > Math.abs(f1))) {
          return j;
        }
      }
      f1 = mRecyclerView.getHeight();
      float f2 = mCallback.getSwipeThreshold(paramViewHolder);
      if (((paramInt & i) != 0) && (Math.abs(mDy) > f1 * f2)) {
        return i;
      }
    }
    return 0;
  }
  
  private void destroyCallbacks()
  {
    mRecyclerView.removeItemDecoration(this);
    mRecyclerView.removeOnItemTouchListener(mOnItemTouchListener);
    mRecyclerView.removeOnChildAttachStateChangeListener(this);
    int i = mRecoverAnimations.size() - 1;
    while (i >= 0)
    {
      RecoverAnimation localRecoverAnimation = (RecoverAnimation)mRecoverAnimations.get(0);
      mCallback.clearView(mRecyclerView, mViewHolder);
      i -= 1;
    }
    mRecoverAnimations.clear();
    mOverdrawChild = null;
    mOverdrawChildPosition = -1;
    releaseVelocityTracker();
    stopGestureDetection();
  }
  
  private List findSwapTargets(RecyclerView.ViewHolder paramViewHolder)
  {
    if (mSwapTargets == null)
    {
      mSwapTargets = new ArrayList();
      mDistances = new ArrayList();
    }
    else
    {
      mSwapTargets.clear();
      mDistances.clear();
    }
    int j = mCallback.getBoundingBoxMargin();
    int m = Math.round(mSelectedStartX + mDx) - j;
    int n = Math.round(mSelectedStartY + mDy) - j;
    int i = itemView.getWidth();
    j *= 2;
    int i1 = i + m + j;
    int i2 = itemView.getHeight() + n + j;
    int i3 = (m + i1) / 2;
    int i4 = (n + i2) / 2;
    RecyclerView.LayoutManager localLayoutManager = mRecyclerView.getLayoutManager();
    int i5 = localLayoutManager.getChildCount();
    i = 0;
    while (i < i5)
    {
      View localView = localLayoutManager.getChildAt(i);
      if ((localView != itemView) && (localView.getBottom() >= n) && (localView.getTop() <= i2) && (localView.getRight() >= m) && (localView.getLeft() <= i1))
      {
        RecyclerView.ViewHolder localViewHolder = mRecyclerView.getChildViewHolder(localView);
        if (mCallback.canDropOver(mRecyclerView, mSelected, localViewHolder))
        {
          j = Math.abs(i3 - (localView.getLeft() + localView.getRight()) / 2);
          int k = Math.abs(i4 - (localView.getTop() + localView.getBottom()) / 2);
          int i6 = j * j + k * k;
          int i7 = mSwapTargets.size();
          j = 0;
          k = 0;
          while ((j < i7) && (i6 > ((Integer)mDistances.get(j)).intValue()))
          {
            k += 1;
            j += 1;
          }
          mSwapTargets.add(k, localViewHolder);
          mDistances.add(k, Integer.valueOf(i6));
        }
      }
      i += 1;
    }
    return mSwapTargets;
  }
  
  private RecyclerView.ViewHolder findSwipedView(MotionEvent paramMotionEvent)
  {
    RecyclerView.LayoutManager localLayoutManager = mRecyclerView.getLayoutManager();
    if (mActivePointerId == -1) {
      return null;
    }
    int i = paramMotionEvent.findPointerIndex(mActivePointerId);
    float f3 = paramMotionEvent.getX(i);
    float f4 = mInitialTouchX;
    float f1 = paramMotionEvent.getY(i);
    float f2 = mInitialTouchY;
    f3 = Math.abs(f3 - f4);
    f1 = Math.abs(f1 - f2);
    if ((f3 < mSlop) && (f1 < mSlop)) {
      return null;
    }
    if ((f3 > f1) && (localLayoutManager.canScrollHorizontally())) {
      return null;
    }
    if ((f1 > f3) && (localLayoutManager.canScrollVertically())) {
      return null;
    }
    paramMotionEvent = findChildView(paramMotionEvent);
    if (paramMotionEvent == null) {
      return null;
    }
    return mRecyclerView.getChildViewHolder(paramMotionEvent);
  }
  
  private void getSelectedDxDy(float[] paramArrayOfFloat)
  {
    if ((mSelectedFlags & 0xC) != 0) {
      paramArrayOfFloat[0] = (mSelectedStartX + mDx - mSelected.itemView.getLeft());
    } else {
      paramArrayOfFloat[0] = mSelected.itemView.getTranslationX();
    }
    if ((mSelectedFlags & 0x3) != 0)
    {
      paramArrayOfFloat[1] = (mSelectedStartY + mDy - mSelected.itemView.getTop());
      return;
    }
    paramArrayOfFloat[1] = mSelected.itemView.getTranslationY();
  }
  
  private static boolean hitTest(View paramView, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    return (paramFloat1 >= paramFloat3) && (paramFloat1 <= paramFloat3 + paramView.getWidth()) && (paramFloat2 >= paramFloat4) && (paramFloat2 <= paramFloat4 + paramView.getHeight());
  }
  
  private void releaseVelocityTracker()
  {
    if (mVelocityTracker != null)
    {
      mVelocityTracker.recycle();
      mVelocityTracker = null;
    }
  }
  
  private void setupCallbacks()
  {
    mSlop = ViewConfiguration.get(mRecyclerView.getContext()).getScaledTouchSlop();
    mRecyclerView.addItemDecoration(this);
    mRecyclerView.addOnItemTouchListener(mOnItemTouchListener);
    mRecyclerView.addOnChildAttachStateChangeListener(this);
    startGestureDetection();
  }
  
  private void startGestureDetection()
  {
    mItemTouchHelperGestureListener = new ItemTouchHelperGestureListener();
    mGestureDetector = new GestureDetectorCompat(mRecyclerView.getContext(), mItemTouchHelperGestureListener);
  }
  
  private void stopGestureDetection()
  {
    if (mItemTouchHelperGestureListener != null)
    {
      mItemTouchHelperGestureListener.doNotReactToLongPress();
      mItemTouchHelperGestureListener = null;
    }
    if (mGestureDetector != null) {
      mGestureDetector = null;
    }
  }
  
  private int swipeIfNecessary(RecyclerView.ViewHolder paramViewHolder)
  {
    if (mActionState == 2) {
      return 0;
    }
    int j = mCallback.getMovementFlags(mRecyclerView, paramViewHolder);
    int i = (mCallback.convertToAbsoluteDirection(j, ViewCompat.getLayoutDirection(mRecyclerView)) & 0xFF00) >> 8;
    if (i == 0) {
      return 0;
    }
    int k = (j & 0xFF00) >> 8;
    if (Math.abs(mDx) > Math.abs(mDy))
    {
      j = checkHorizontalSwipe(paramViewHolder, i);
      if (j > 0)
      {
        if ((k & j) == 0) {
          return Callback.convertToRelativeDirection(j, ViewCompat.getLayoutDirection(mRecyclerView));
        }
        return j;
      }
      i = checkVerticalSwipe(paramViewHolder, i);
      if (i > 0) {
        return i;
      }
    }
    else
    {
      j = checkVerticalSwipe(paramViewHolder, i);
      if (j > 0) {
        return j;
      }
      j = checkHorizontalSwipe(paramViewHolder, i);
      if (j > 0)
      {
        i = j;
        if ((k & j) == 0) {
          i = Callback.convertToRelativeDirection(j, ViewCompat.getLayoutDirection(mRecyclerView));
        }
        return i;
      }
    }
    return 0;
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
    if (paramRecyclerView != null)
    {
      paramRecyclerView = paramRecyclerView.getResources();
      mSwipeEscapeVelocity = paramRecyclerView.getDimension(R.dimen.item_touch_helper_swipe_escape_velocity);
      mMaxSwipeVelocity = paramRecyclerView.getDimension(R.dimen.item_touch_helper_swipe_escape_max_velocity);
      setupCallbacks();
    }
  }
  
  boolean checkSelectForSwipe(int paramInt1, MotionEvent paramMotionEvent, int paramInt2)
  {
    if ((mSelected == null) && (paramInt1 == 2) && (mActionState != 2))
    {
      if (!mCallback.isItemViewSwipeEnabled()) {
        return false;
      }
      if (mRecyclerView.getScrollState() == 1) {
        return false;
      }
      RecyclerView.ViewHolder localViewHolder = findSwipedView(paramMotionEvent);
      if (localViewHolder == null) {
        return false;
      }
      paramInt1 = (mCallback.getAbsoluteMovementFlags(mRecyclerView, localViewHolder) & 0xFF00) >> 8;
      if (paramInt1 == 0) {
        return false;
      }
      float f1 = paramMotionEvent.getX(paramInt2);
      float f2 = paramMotionEvent.getY(paramInt2);
      f1 -= mInitialTouchX;
      f2 -= mInitialTouchY;
      float f3 = Math.abs(f1);
      float f4 = Math.abs(f2);
      if ((f3 < mSlop) && (f4 < mSlop)) {
        return false;
      }
      if (f3 > f4)
      {
        if ((f1 < 0.0F) && ((paramInt1 & 0x4) == 0)) {
          return false;
        }
        if ((f1 > 0.0F) && ((paramInt1 & 0x8) == 0)) {
          return false;
        }
      }
      else
      {
        if ((f2 < 0.0F) && ((paramInt1 & 0x1) == 0)) {
          return false;
        }
        if ((f2 > 0.0F) && ((paramInt1 & 0x2) == 0)) {
          return false;
        }
      }
      mDy = 0.0F;
      mDx = 0.0F;
      mActivePointerId = paramMotionEvent.getPointerId(0);
      select(localViewHolder, 1);
      return true;
    }
    return false;
  }
  
  int endRecoverAnimation(RecyclerView.ViewHolder paramViewHolder, boolean paramBoolean)
  {
    int i = mRecoverAnimations.size() - 1;
    while (i >= 0)
    {
      RecoverAnimation localRecoverAnimation = (RecoverAnimation)mRecoverAnimations.get(i);
      if (mViewHolder == paramViewHolder)
      {
        mOverridden |= paramBoolean;
        if (!mEnded) {
          localRecoverAnimation.cancel();
        }
        mRecoverAnimations.remove(i);
        return mAnimationType;
      }
      i -= 1;
    }
    return 0;
  }
  
  RecoverAnimation findAnimation(MotionEvent paramMotionEvent)
  {
    if (mRecoverAnimations.isEmpty()) {
      return null;
    }
    paramMotionEvent = findChildView(paramMotionEvent);
    int i = mRecoverAnimations.size() - 1;
    while (i >= 0)
    {
      RecoverAnimation localRecoverAnimation = (RecoverAnimation)mRecoverAnimations.get(i);
      if (mViewHolder.itemView == paramMotionEvent) {
        return localRecoverAnimation;
      }
      i -= 1;
    }
    return null;
  }
  
  View findChildView(MotionEvent paramMotionEvent)
  {
    float f1 = paramMotionEvent.getX();
    float f2 = paramMotionEvent.getY();
    if (mSelected != null)
    {
      paramMotionEvent = mSelected.itemView;
      if (hitTest(paramMotionEvent, f1, f2, mSelectedStartX + mDx, mSelectedStartY + mDy)) {
        return paramMotionEvent;
      }
    }
    int i = mRecoverAnimations.size() - 1;
    while (i >= 0)
    {
      paramMotionEvent = (RecoverAnimation)mRecoverAnimations.get(i);
      View localView = mViewHolder.itemView;
      if (hitTest(localView, f1, f2, mX, mY)) {
        return localView;
      }
      i -= 1;
    }
    return mRecyclerView.findChildViewUnder(f1, f2);
  }
  
  public void getItemOffsets(Rect paramRect, View paramView, RecyclerView paramRecyclerView, RecyclerView.State paramState)
  {
    paramRect.setEmpty();
  }
  
  boolean hasRunningRecoverAnim()
  {
    int j = mRecoverAnimations.size();
    int i = 0;
    while (i < j)
    {
      if (!mRecoverAnimations.get(i)).mEnded) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  void moveIfNecessary(RecyclerView.ViewHolder paramViewHolder)
  {
    if (mRecyclerView.isLayoutRequested()) {
      return;
    }
    if (mActionState != 2) {
      return;
    }
    float f = mCallback.getMoveThreshold(paramViewHolder);
    int i = (int)(mSelectedStartX + mDx);
    int j = (int)(mSelectedStartY + mDy);
    if ((Math.abs(j - itemView.getTop()) < itemView.getHeight() * f) && (Math.abs(i - itemView.getLeft()) < itemView.getWidth() * f)) {
      return;
    }
    Object localObject = findSwapTargets(paramViewHolder);
    if (((List)localObject).size() == 0) {
      return;
    }
    localObject = mCallback.chooseDropTarget(paramViewHolder, (List)localObject, i, j);
    if (localObject == null)
    {
      mSwapTargets.clear();
      mDistances.clear();
      return;
    }
    int k = ((RecyclerView.ViewHolder)localObject).getAdapterPosition();
    int m = paramViewHolder.getAdapterPosition();
    if (mCallback.onMove(mRecyclerView, paramViewHolder, (RecyclerView.ViewHolder)localObject)) {
      mCallback.onMoved(mRecyclerView, paramViewHolder, m, (RecyclerView.ViewHolder)localObject, k, i, j);
    }
  }
  
  void obtainVelocityTracker()
  {
    if (mVelocityTracker != null) {
      mVelocityTracker.recycle();
    }
    mVelocityTracker = VelocityTracker.obtain();
  }
  
  public void onChildViewAttachedToWindow(View paramView) {}
  
  public void onChildViewDetachedFromWindow(View paramView)
  {
    removeChildDrawingOrderCallbackIfNecessary(paramView);
    paramView = mRecyclerView.getChildViewHolder(paramView);
    if (paramView == null) {
      return;
    }
    if ((mSelected != null) && (paramView == mSelected))
    {
      select(null, 0);
      return;
    }
    endRecoverAnimation(paramView, false);
    if (mPendingCleanup.remove(itemView)) {
      mCallback.clearView(mRecyclerView, paramView);
    }
  }
  
  public void onDraw(Canvas paramCanvas, RecyclerView paramRecyclerView, RecyclerView.State paramState)
  {
    mOverdrawChildPosition = -1;
    float f1;
    float f2;
    if (mSelected != null)
    {
      getSelectedDxDy(mTmpPosition);
      f1 = mTmpPosition[0];
      f2 = mTmpPosition[1];
    }
    else
    {
      f1 = 0.0F;
      f2 = 0.0F;
    }
    mCallback.onDraw(paramCanvas, paramRecyclerView, mSelected, mRecoverAnimations, mActionState, f1, f2);
  }
  
  public void onDrawOver(Canvas paramCanvas, RecyclerView paramRecyclerView, RecyclerView.State paramState)
  {
    float f1;
    float f2;
    if (mSelected != null)
    {
      getSelectedDxDy(mTmpPosition);
      f1 = mTmpPosition[0];
      f2 = mTmpPosition[1];
    }
    else
    {
      f1 = 0.0F;
      f2 = 0.0F;
    }
    mCallback.onDrawOver(paramCanvas, paramRecyclerView, mSelected, mRecoverAnimations, mActionState, f1, f2);
  }
  
  void postDispatchSwipe(final RecoverAnimation paramRecoverAnimation, final int paramInt)
  {
    mRecyclerView.post(new Runnable()
    {
      public void run()
      {
        if ((mRecyclerView != null) && (mRecyclerView.isAttachedToWindow()) && (!paramRecoverAnimationmOverridden) && (paramRecoverAnimationmViewHolder.getAdapterPosition() != -1))
        {
          RecyclerView.ItemAnimator localItemAnimator = mRecyclerView.getItemAnimator();
          if (((localItemAnimator == null) || (!localItemAnimator.isRunning(null))) && (!hasRunningRecoverAnim()))
          {
            mCallback.onSwiped(paramRecoverAnimationmViewHolder, paramInt);
            return;
          }
          mRecyclerView.post(this);
        }
      }
    });
  }
  
  void removeChildDrawingOrderCallbackIfNecessary(View paramView)
  {
    if (paramView == mOverdrawChild)
    {
      mOverdrawChild = null;
      if (mChildDrawingOrderCallback != null) {
        mRecyclerView.setChildDrawingOrderCallback(null);
      }
    }
  }
  
  boolean scrollIfNecessary()
  {
    if (mSelected == null)
    {
      mDragScrollStartTimeInMs = Long.MIN_VALUE;
      return false;
    }
    long l2 = System.currentTimeMillis();
    long l1;
    if (mDragScrollStartTimeInMs == Long.MIN_VALUE) {
      l1 = 0L;
    } else {
      l1 = l2 - mDragScrollStartTimeInMs;
    }
    RecyclerView.LayoutManager localLayoutManager = mRecyclerView.getLayoutManager();
    if (mTmpRect == null) {
      mTmpRect = new Rect();
    }
    localLayoutManager.calculateItemDecorationsForChild(mSelected.itemView, mTmpRect);
    if (localLayoutManager.canScrollHorizontally())
    {
      j = (int)(mSelectedStartX + mDx);
      i = j - mTmpRect.left - mRecyclerView.getPaddingLeft();
      if ((mDx < 0.0F) && (i < 0)) {
        break label198;
      }
      if (mDx > 0.0F)
      {
        i = j + mSelected.itemView.getWidth() + mTmpRect.right - (mRecyclerView.getWidth() - mRecyclerView.getPaddingRight());
        if (i > 0) {
          break label198;
        }
      }
    }
    int i = 0;
    label198:
    if (localLayoutManager.canScrollVertically())
    {
      k = (int)(mSelectedStartY + mDy);
      j = k - mTmpRect.top - mRecyclerView.getPaddingTop();
      if ((mDy < 0.0F) && (j < 0)) {
        break label306;
      }
      if (mDy > 0.0F)
      {
        j = k + mSelected.itemView.getHeight() + mTmpRect.bottom - (mRecyclerView.getHeight() - mRecyclerView.getPaddingBottom());
        if (j > 0) {
          break label306;
        }
      }
    }
    int j = 0;
    label306:
    int k = i;
    if (i != 0) {
      k = mCallback.interpolateOutOfBoundsScroll(mRecyclerView, mSelected.itemView.getWidth(), i, mRecyclerView.getWidth(), l1);
    }
    i = j;
    if (j != 0) {
      i = mCallback.interpolateOutOfBoundsScroll(mRecyclerView, mSelected.itemView.getHeight(), j, mRecyclerView.getHeight(), l1);
    }
    if ((k == 0) && (i == 0))
    {
      mDragScrollStartTimeInMs = Long.MIN_VALUE;
      return false;
    }
    if (mDragScrollStartTimeInMs == Long.MIN_VALUE) {
      mDragScrollStartTimeInMs = l2;
    }
    mRecyclerView.scrollBy(k, i);
    return true;
  }
  
  void select(RecyclerView.ViewHolder paramViewHolder, int paramInt)
  {
    if ((paramViewHolder == mSelected) && (paramInt == mActionState)) {
      return;
    }
    mDragScrollStartTimeInMs = Long.MIN_VALUE;
    int k = mActionState;
    endRecoverAnimation(paramViewHolder, true);
    mActionState = paramInt;
    if (paramInt == 2)
    {
      mOverdrawChild = itemView;
      addChildDrawingOrderCallback();
    }
    int i;
    if (mSelected != null)
    {
      Object localObject = mSelected;
      if (itemView.getParent() != null)
      {
        final int j;
        if (k == 2) {
          j = 0;
        } else {
          j = swipeIfNecessary((RecyclerView.ViewHolder)localObject);
        }
        releaseVelocityTracker();
        if ((j != 4) && (j != 8) && (j != 16) && (j != 32)) {
          switch (j)
          {
          default: 
            break;
          }
        }
        float f2;
        for (float f1 = 0.0F;; f1 = Math.signum(mDx) * mRecyclerView.getWidth())
        {
          f2 = 0.0F;
          break;
          f2 = Math.signum(mDy) * mRecyclerView.getHeight();
          f1 = 0.0F;
          break;
        }
        if (k == 2) {
          i = 8;
        } else if (j > 0) {
          i = 2;
        } else {
          i = 4;
        }
        getSelectedDxDy(mTmpPosition);
        float f3 = mTmpPosition[0];
        float f4 = mTmpPosition[1];
        localObject = new RecoverAnimation((RecyclerView.ViewHolder)localObject, i, k, f3, f4, f1, f2)
        {
          public void onAnimationEnd(Animator paramAnonymousAnimator)
          {
            super.onAnimationEnd(paramAnonymousAnimator);
            if (mOverridden) {
              return;
            }
            if (j <= 0)
            {
              mCallback.clearView(mRecyclerView, val$prevSelected);
            }
            else
            {
              mPendingCleanup.add(val$prevSelected.itemView);
              mIsPendingCleanup = true;
              if (j > 0) {
                postDispatchSwipe(this, j);
              }
            }
            if (mOverdrawChild == val$prevSelected.itemView) {
              removeChildDrawingOrderCallbackIfNecessary(val$prevSelected.itemView);
            }
          }
        };
        ((RecoverAnimation)localObject).setDuration(mCallback.getAnimationDuration(mRecyclerView, i, f1 - f3, f2 - f4));
        mRecoverAnimations.add(localObject);
        ((RecoverAnimation)localObject).start();
        i = 1;
      }
      else
      {
        removeChildDrawingOrderCallbackIfNecessary(itemView);
        mCallback.clearView(mRecyclerView, (RecyclerView.ViewHolder)localObject);
        i = 0;
      }
      mSelected = null;
    }
    else
    {
      i = 0;
    }
    if (paramViewHolder != null)
    {
      mSelectedFlags = ((mCallback.getAbsoluteMovementFlags(mRecyclerView, paramViewHolder) & (1 << paramInt * 8 + 8) - 1) >> mActionState * 8);
      mSelectedStartX = itemView.getLeft();
      mSelectedStartY = itemView.getTop();
      mSelected = paramViewHolder;
      if (paramInt == 2) {
        mSelected.itemView.performHapticFeedback(0);
      }
    }
    boolean bool = false;
    paramViewHolder = mRecyclerView.getParent();
    if (paramViewHolder != null)
    {
      if (mSelected != null) {
        bool = true;
      }
      paramViewHolder.requestDisallowInterceptTouchEvent(bool);
    }
    if (i == 0) {
      mRecyclerView.getLayoutManager().requestSimpleAnimationsInNextLayout();
    }
    mCallback.onSelectedChanged(mSelected, mActionState);
    mRecyclerView.invalidate();
  }
  
  public void startDrag(RecyclerView.ViewHolder paramViewHolder)
  {
    if (!mCallback.hasDragFlag(mRecyclerView, paramViewHolder))
    {
      Log.e("ItemTouchHelper", "Start drag has been called but dragging is not enabled");
      return;
    }
    if (itemView.getParent() != mRecyclerView)
    {
      Log.e("ItemTouchHelper", "Start drag has been called with a view holder which is not a child of the RecyclerView which is controlled by this ItemTouchHelper.");
      return;
    }
    obtainVelocityTracker();
    mDy = 0.0F;
    mDx = 0.0F;
    select(paramViewHolder, 2);
  }
  
  public void startSwipe(RecyclerView.ViewHolder paramViewHolder)
  {
    if (!mCallback.hasSwipeFlag(mRecyclerView, paramViewHolder))
    {
      Log.e("ItemTouchHelper", "Start swipe has been called but swiping is not enabled");
      return;
    }
    if (itemView.getParent() != mRecyclerView)
    {
      Log.e("ItemTouchHelper", "Start swipe has been called with a view holder which is not a child of the RecyclerView controlled by this ItemTouchHelper.");
      return;
    }
    obtainVelocityTracker();
    mDy = 0.0F;
    mDx = 0.0F;
    select(paramViewHolder, 1);
  }
  
  void updateDxDy(MotionEvent paramMotionEvent, int paramInt1, int paramInt2)
  {
    float f1 = paramMotionEvent.getX(paramInt2);
    float f2 = paramMotionEvent.getY(paramInt2);
    mDx = (f1 - mInitialTouchX);
    mDy = (f2 - mInitialTouchY);
    if ((paramInt1 & 0x4) == 0) {
      mDx = Math.max(0.0F, mDx);
    }
    if ((paramInt1 & 0x8) == 0) {
      mDx = Math.min(0.0F, mDx);
    }
    if ((paramInt1 & 0x1) == 0) {
      mDy = Math.max(0.0F, mDy);
    }
    if ((paramInt1 & 0x2) == 0) {
      mDy = Math.min(0.0F, mDy);
    }
  }
  
  public static abstract class Callback
  {
    private static final int ABS_HORIZONTAL_DIR_FLAGS = 789516;
    public static final int DEFAULT_DRAG_ANIMATION_DURATION = 200;
    public static final int DEFAULT_SWIPE_ANIMATION_DURATION = 250;
    private static final long DRAG_SCROLL_ACCELERATION_LIMIT_TIME_MS = 2000L;
    static final int RELATIVE_DIR_FLAGS = 3158064;
    private static final Interpolator sDragScrollInterpolator = new Interpolator()
    {
      public float getInterpolation(float paramAnonymousFloat)
      {
        return paramAnonymousFloat * paramAnonymousFloat * paramAnonymousFloat * paramAnonymousFloat * paramAnonymousFloat;
      }
    };
    private static final Interpolator sDragViewScrollCapInterpolator = new Interpolator()
    {
      public float getInterpolation(float paramAnonymousFloat)
      {
        paramAnonymousFloat -= 1.0F;
        return paramAnonymousFloat * paramAnonymousFloat * paramAnonymousFloat * paramAnonymousFloat * paramAnonymousFloat + 1.0F;
      }
    };
    private static final ItemTouchUIUtil sUICallback = new ItemTouchUIUtilImpl.BaseImpl();
    private int mCachedMaxScrollSpeed = -1;
    
    static
    {
      if (Build.VERSION.SDK_INT >= 21)
      {
        sUICallback = new ItemTouchUIUtilImpl.Api21Impl();
        return;
      }
    }
    
    public Callback() {}
    
    public static int convertToRelativeDirection(int paramInt1, int paramInt2)
    {
      int i = paramInt1 & 0xC0C0C;
      if (i == 0) {
        return paramInt1;
      }
      paramInt1 &= i;
      if (paramInt2 == 0) {
        return paramInt1 | i << 2;
      }
      paramInt2 = i << 1;
      return paramInt1 | 0xFFF3F3F3 & paramInt2 | (paramInt2 & 0xC0C0C) << 2;
    }
    
    public static ItemTouchUIUtil getDefaultUIUtil()
    {
      return sUICallback;
    }
    
    private int getMaxDragScroll(RecyclerView paramRecyclerView)
    {
      if (mCachedMaxScrollSpeed == -1) {
        mCachedMaxScrollSpeed = paramRecyclerView.getResources().getDimensionPixelSize(R.dimen.item_touch_helper_max_drag_scroll_per_frame);
      }
      return mCachedMaxScrollSpeed;
    }
    
    public static int makeFlag(int paramInt1, int paramInt2)
    {
      return paramInt2 << paramInt1 * 8;
    }
    
    public static int makeMovementFlags(int paramInt1, int paramInt2)
    {
      int i = makeFlag(0, paramInt2 | paramInt1);
      paramInt2 = makeFlag(1, paramInt2);
      return makeFlag(2, paramInt1) | paramInt2 | i;
    }
    
    public boolean canDropOver(RecyclerView paramRecyclerView, RecyclerView.ViewHolder paramViewHolder1, RecyclerView.ViewHolder paramViewHolder2)
    {
      return true;
    }
    
    public RecyclerView.ViewHolder chooseDropTarget(RecyclerView.ViewHolder paramViewHolder, List paramList, int paramInt1, int paramInt2)
    {
      int n = itemView.getWidth();
      int i1 = itemView.getHeight();
      int i2 = paramInt1 - itemView.getLeft();
      Object localObject1 = itemView;
      int i3 = paramInt2 - ((View)localObject1).getTop();
      int i4 = paramList.size();
      Object localObject3 = null;
      int i = -1;
      int k = 0;
      localObject1 = paramViewHolder;
      while (k < i4)
      {
        paramViewHolder = (RecyclerView.ViewHolder)paramList.get(k);
        Object localObject2 = localObject1;
        int m;
        if (i2 > 0)
        {
          j = itemView.getRight() - (paramInt1 + n);
          localObject2 = localObject1;
          if (j < 0)
          {
            m = itemView.getRight();
            localObject4 = itemView;
            localObject2 = localObject1;
            if (m > ((View)localObject4).getRight())
            {
              m = Math.abs(j);
              j = m;
              localObject2 = localObject1;
              if (m > i)
              {
                localObject4 = paramViewHolder;
                localObject2 = localObject1;
                break label186;
              }
            }
          }
        }
        int j = i;
        Object localObject4 = localObject3;
        label186:
        localObject3 = localObject4;
        i = j;
        localObject1 = localObject2;
        int i5;
        View localView;
        if (i2 < 0)
        {
          m = itemView.getLeft() - paramInt1;
          localObject3 = localObject4;
          i = j;
          localObject1 = localObject2;
          if (m > 0)
          {
            i5 = itemView.getLeft();
            localView = itemView;
            localObject3 = localObject4;
            i = j;
            localObject1 = localObject2;
            if (i5 < localView.getLeft())
            {
              m = Math.abs(m);
              localObject3 = localObject4;
              i = j;
              localObject1 = localObject2;
              if (m > j)
              {
                i = m;
                localObject3 = paramViewHolder;
                localObject1 = localObject2;
              }
            }
          }
        }
        localObject4 = localObject3;
        j = i;
        localObject2 = localObject1;
        if (i3 < 0)
        {
          m = itemView.getTop() - paramInt2;
          localObject4 = localObject3;
          j = i;
          localObject2 = localObject1;
          if (m > 0)
          {
            i5 = itemView.getTop();
            localView = itemView;
            localObject4 = localObject3;
            j = i;
            localObject2 = localObject1;
            if (i5 < localView.getTop())
            {
              m = Math.abs(m);
              localObject4 = localObject3;
              j = i;
              localObject2 = localObject1;
              if (m > i)
              {
                j = m;
                localObject4 = paramViewHolder;
                localObject2 = localObject1;
              }
            }
          }
        }
        localObject1 = localObject2;
        if (i3 > 0)
        {
          i = itemView.getBottom() - (paramInt2 + i1);
          localObject1 = localObject2;
          if (i < 0)
          {
            m = itemView.getBottom();
            localObject3 = itemView;
            localObject1 = localObject2;
            if (m > ((View)localObject3).getBottom())
            {
              m = Math.abs(i);
              i = m;
              localObject1 = localObject2;
              if (m > j)
              {
                localObject3 = paramViewHolder;
                localObject1 = localObject2;
                break label530;
              }
            }
          }
        }
        i = j;
        localObject3 = localObject4;
        label530:
        k += 1;
      }
      return localObject3;
    }
    
    public void clearView(RecyclerView paramRecyclerView, RecyclerView.ViewHolder paramViewHolder)
    {
      sUICallback.clearView(itemView);
    }
    
    public int convertToAbsoluteDirection(int paramInt1, int paramInt2)
    {
      int i = paramInt1 & 0x303030;
      if (i == 0) {
        return paramInt1;
      }
      paramInt1 &= i;
      if (paramInt2 == 0) {
        return paramInt1 | i >> 2;
      }
      paramInt2 = i >> 1;
      return paramInt1 | 0xFFCFCFCF & paramInt2 | (paramInt2 & 0x303030) >> 2;
    }
    
    final int getAbsoluteMovementFlags(RecyclerView paramRecyclerView, RecyclerView.ViewHolder paramViewHolder)
    {
      return convertToAbsoluteDirection(getMovementFlags(paramRecyclerView, paramViewHolder), ViewCompat.getLayoutDirection(paramRecyclerView));
    }
    
    public long getAnimationDuration(RecyclerView paramRecyclerView, int paramInt, float paramFloat1, float paramFloat2)
    {
      paramRecyclerView = paramRecyclerView.getItemAnimator();
      if (paramRecyclerView == null)
      {
        if (paramInt == 8) {
          return 200L;
        }
        return 250L;
      }
      if (paramInt == 8) {
        return paramRecyclerView.getMoveDuration();
      }
      return paramRecyclerView.getRemoveDuration();
    }
    
    public int getBoundingBoxMargin()
    {
      return 0;
    }
    
    public float getMoveThreshold(RecyclerView.ViewHolder paramViewHolder)
    {
      return 0.5F;
    }
    
    public abstract int getMovementFlags(RecyclerView paramRecyclerView, RecyclerView.ViewHolder paramViewHolder);
    
    public float getSwipeEscapeVelocity(float paramFloat)
    {
      return paramFloat;
    }
    
    public float getSwipeThreshold(RecyclerView.ViewHolder paramViewHolder)
    {
      return 0.5F;
    }
    
    public float getSwipeVelocityThreshold(float paramFloat)
    {
      return paramFloat;
    }
    
    boolean hasDragFlag(RecyclerView paramRecyclerView, RecyclerView.ViewHolder paramViewHolder)
    {
      return (getAbsoluteMovementFlags(paramRecyclerView, paramViewHolder) & 0xFF0000) != 0;
    }
    
    boolean hasSwipeFlag(RecyclerView paramRecyclerView, RecyclerView.ViewHolder paramViewHolder)
    {
      return (getAbsoluteMovementFlags(paramRecyclerView, paramViewHolder) & 0xFF00) != 0;
    }
    
    public int interpolateOutOfBoundsScroll(RecyclerView paramRecyclerView, int paramInt1, int paramInt2, int paramInt3, long paramLong)
    {
      paramInt3 = getMaxDragScroll(paramRecyclerView);
      int i = Math.abs(paramInt2);
      int j = (int)Math.signum(paramInt2);
      float f2 = i;
      float f1 = 1.0F;
      f2 = Math.min(1.0F, f2 * 1.0F / paramInt1);
      paramInt1 = (int)(j * paramInt3 * sDragViewScrollCapInterpolator.getInterpolation(f2));
      if (paramLong <= 2000L) {
        f1 = (float)paramLong / 2000.0F;
      }
      paramInt1 = (int)(paramInt1 * sDragScrollInterpolator.getInterpolation(f1));
      if (paramInt1 == 0)
      {
        if (paramInt2 > 0) {
          return 1;
        }
        return -1;
      }
      return paramInt1;
    }
    
    public boolean isItemViewSwipeEnabled()
    {
      return true;
    }
    
    public boolean isLongPressDragEnabled()
    {
      return true;
    }
    
    public void onChildDraw(Canvas paramCanvas, RecyclerView paramRecyclerView, RecyclerView.ViewHolder paramViewHolder, float paramFloat1, float paramFloat2, int paramInt, boolean paramBoolean)
    {
      sUICallback.onDraw(paramCanvas, paramRecyclerView, itemView, paramFloat1, paramFloat2, paramInt, paramBoolean);
    }
    
    public void onChildDrawOver(Canvas paramCanvas, RecyclerView paramRecyclerView, RecyclerView.ViewHolder paramViewHolder, float paramFloat1, float paramFloat2, int paramInt, boolean paramBoolean)
    {
      sUICallback.onDrawOver(paramCanvas, paramRecyclerView, itemView, paramFloat1, paramFloat2, paramInt, paramBoolean);
    }
    
    void onDraw(Canvas paramCanvas, RecyclerView paramRecyclerView, RecyclerView.ViewHolder paramViewHolder, List paramList, int paramInt, float paramFloat1, float paramFloat2)
    {
      int j = paramList.size();
      int i = 0;
      while (i < j)
      {
        ItemTouchHelper.RecoverAnimation localRecoverAnimation = (ItemTouchHelper.RecoverAnimation)paramList.get(i);
        localRecoverAnimation.update();
        int k = paramCanvas.save();
        onChildDraw(paramCanvas, paramRecyclerView, mViewHolder, mX, mY, mActionState, false);
        paramCanvas.restoreToCount(k);
        i += 1;
      }
      if (paramViewHolder != null)
      {
        i = paramCanvas.save();
        onChildDraw(paramCanvas, paramRecyclerView, paramViewHolder, paramFloat1, paramFloat2, paramInt, true);
        paramCanvas.restoreToCount(i);
      }
    }
    
    void onDrawOver(Canvas paramCanvas, RecyclerView paramRecyclerView, RecyclerView.ViewHolder paramViewHolder, List paramList, int paramInt, float paramFloat1, float paramFloat2)
    {
      int k = paramList.size();
      int j = 0;
      int i = 0;
      while (i < k)
      {
        ItemTouchHelper.RecoverAnimation localRecoverAnimation = (ItemTouchHelper.RecoverAnimation)paramList.get(i);
        int m = paramCanvas.save();
        onChildDrawOver(paramCanvas, paramRecyclerView, mViewHolder, mX, mY, mActionState, false);
        paramCanvas.restoreToCount(m);
        i += 1;
      }
      if (paramViewHolder != null)
      {
        i = paramCanvas.save();
        onChildDrawOver(paramCanvas, paramRecyclerView, paramViewHolder, paramFloat1, paramFloat2, paramInt, true);
        paramCanvas.restoreToCount(i);
      }
      paramInt = k - 1;
      i = j;
      while (paramInt >= 0)
      {
        paramCanvas = (ItemTouchHelper.RecoverAnimation)paramList.get(paramInt);
        if ((mEnded) && (!mIsPendingCleanup)) {
          paramList.remove(paramInt);
        } else if (!mEnded) {
          i = 1;
        }
        paramInt -= 1;
      }
      if (i != 0) {
        paramRecyclerView.invalidate();
      }
    }
    
    public abstract boolean onMove(RecyclerView paramRecyclerView, RecyclerView.ViewHolder paramViewHolder1, RecyclerView.ViewHolder paramViewHolder2);
    
    public void onMoved(RecyclerView paramRecyclerView, RecyclerView.ViewHolder paramViewHolder1, int paramInt1, RecyclerView.ViewHolder paramViewHolder2, int paramInt2, int paramInt3, int paramInt4)
    {
      RecyclerView.LayoutManager localLayoutManager = paramRecyclerView.getLayoutManager();
      if ((localLayoutManager instanceof ItemTouchHelper.ViewDropHandler))
      {
        ((ItemTouchHelper.ViewDropHandler)localLayoutManager).prepareForDrop(itemView, itemView, paramInt3, paramInt4);
        return;
      }
      if (localLayoutManager.canScrollHorizontally())
      {
        if (localLayoutManager.getDecoratedLeft(itemView) <= paramRecyclerView.getPaddingLeft()) {
          paramRecyclerView.scrollToPosition(paramInt2);
        }
        if (localLayoutManager.getDecoratedRight(itemView) >= paramRecyclerView.getWidth() - paramRecyclerView.getPaddingRight()) {
          paramRecyclerView.scrollToPosition(paramInt2);
        }
      }
      if (localLayoutManager.canScrollVertically())
      {
        if (localLayoutManager.getDecoratedTop(itemView) <= paramRecyclerView.getPaddingTop()) {
          paramRecyclerView.scrollToPosition(paramInt2);
        }
        if (localLayoutManager.getDecoratedBottom(itemView) >= paramRecyclerView.getHeight() - paramRecyclerView.getPaddingBottom()) {
          paramRecyclerView.scrollToPosition(paramInt2);
        }
      }
    }
    
    public void onSelectedChanged(RecyclerView.ViewHolder paramViewHolder, int paramInt)
    {
      if (paramViewHolder != null) {
        sUICallback.onSelected(itemView);
      }
    }
    
    public abstract void onSwiped(RecyclerView.ViewHolder paramViewHolder, int paramInt);
  }
  
  private class ItemTouchHelperGestureListener
    extends GestureDetector.SimpleOnGestureListener
  {
    private boolean mShouldReactToLongPress = true;
    
    ItemTouchHelperGestureListener() {}
    
    void doNotReactToLongPress()
    {
      mShouldReactToLongPress = false;
    }
    
    public boolean onDown(MotionEvent paramMotionEvent)
    {
      return true;
    }
    
    public void onLongPress(MotionEvent paramMotionEvent)
    {
      if (!mShouldReactToLongPress) {
        return;
      }
      Object localObject = findChildView(paramMotionEvent);
      if (localObject != null)
      {
        localObject = mRecyclerView.getChildViewHolder((View)localObject);
        if (localObject != null)
        {
          if (!mCallback.hasDragFlag(mRecyclerView, (RecyclerView.ViewHolder)localObject)) {
            return;
          }
          if (paramMotionEvent.getPointerId(0) == mActivePointerId)
          {
            int i = paramMotionEvent.findPointerIndex(mActivePointerId);
            float f1 = paramMotionEvent.getX(i);
            float f2 = paramMotionEvent.getY(i);
            mInitialTouchX = f1;
            mInitialTouchY = f2;
            paramMotionEvent = ItemTouchHelper.this;
            mDy = 0.0F;
            mDx = 0.0F;
            if (mCallback.isLongPressDragEnabled()) {
              select((RecyclerView.ViewHolder)localObject, 2);
            }
          }
        }
      }
    }
  }
  
  private static class RecoverAnimation
    implements Animator.AnimatorListener
  {
    final int mActionState;
    final int mAnimationType;
    boolean mEnded = false;
    private float mFraction;
    public boolean mIsPendingCleanup;
    boolean mOverridden = false;
    final float mStartDx;
    final float mStartDy;
    final float mTargetX;
    final float mTargetY;
    private final ValueAnimator mValueAnimator;
    final RecyclerView.ViewHolder mViewHolder;
    float mX;
    float mY;
    
    RecoverAnimation(RecyclerView.ViewHolder paramViewHolder, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
    {
      mActionState = paramInt2;
      mAnimationType = paramInt1;
      mViewHolder = paramViewHolder;
      mStartDx = paramFloat1;
      mStartDy = paramFloat2;
      mTargetX = paramFloat3;
      mTargetY = paramFloat4;
      mValueAnimator = ValueAnimator.ofFloat(new float[] { 0.0F, 1.0F });
      mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
      {
        public void onAnimationUpdate(ValueAnimator paramAnonymousValueAnimator)
        {
          setFraction(paramAnonymousValueAnimator.getAnimatedFraction());
        }
      });
      mValueAnimator.setTarget(itemView);
      mValueAnimator.addListener(this);
      setFraction(0.0F);
    }
    
    public void cancel()
    {
      mValueAnimator.cancel();
    }
    
    public void onAnimationCancel(Animator paramAnimator)
    {
      setFraction(1.0F);
    }
    
    public void onAnimationEnd(Animator paramAnimator)
    {
      if (!mEnded) {
        mViewHolder.setIsRecyclable(true);
      }
      mEnded = true;
    }
    
    public void onAnimationRepeat(Animator paramAnimator) {}
    
    public void onAnimationStart(Animator paramAnimator) {}
    
    public void setDuration(long paramLong)
    {
      mValueAnimator.setDuration(paramLong);
    }
    
    public void setFraction(float paramFloat)
    {
      mFraction = paramFloat;
    }
    
    public void start()
    {
      mViewHolder.setIsRecyclable(false);
      mValueAnimator.start();
    }
    
    public void update()
    {
      if (mStartDx == mTargetX) {
        mX = mViewHolder.itemView.getTranslationX();
      } else {
        mX = (mStartDx + mFraction * (mTargetX - mStartDx));
      }
      if (mStartDy == mTargetY)
      {
        mY = mViewHolder.itemView.getTranslationY();
        return;
      }
      mY = (mStartDy + mFraction * (mTargetY - mStartDy));
    }
  }
  
  public static abstract class SimpleCallback
    extends ItemTouchHelper.Callback
  {
    private int mDefaultDragDirs;
    private int mDefaultSwipeDirs;
    
    public SimpleCallback(int paramInt1, int paramInt2)
    {
      mDefaultSwipeDirs = paramInt2;
      mDefaultDragDirs = paramInt1;
    }
    
    public int getDragDirs(RecyclerView paramRecyclerView, RecyclerView.ViewHolder paramViewHolder)
    {
      return mDefaultDragDirs;
    }
    
    public int getMovementFlags(RecyclerView paramRecyclerView, RecyclerView.ViewHolder paramViewHolder)
    {
      return ItemTouchHelper.Callback.makeMovementFlags(getDragDirs(paramRecyclerView, paramViewHolder), getSwipeDirs(paramRecyclerView, paramViewHolder));
    }
    
    public int getSwipeDirs(RecyclerView paramRecyclerView, RecyclerView.ViewHolder paramViewHolder)
    {
      return mDefaultSwipeDirs;
    }
    
    public void setDefaultDragDirs(int paramInt)
    {
      mDefaultDragDirs = paramInt;
    }
    
    public void setDefaultSwipeDirs(int paramInt)
    {
      mDefaultSwipeDirs = paramInt;
    }
  }
  
  public static abstract interface ViewDropHandler
  {
    public abstract void prepareForDrop(View paramView1, View paramView2, int paramInt1, int paramInt2);
  }
}
