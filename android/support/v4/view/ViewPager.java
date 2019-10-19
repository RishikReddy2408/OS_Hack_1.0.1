package android.support.v4.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityRecord;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.Scroller;
import java.lang.annotation.Annotation;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ViewPager
  extends ViewGroup
{
  private static final int CLOSE_ENOUGH = 2;
  private static final Comparator<ItemInfo> COMPARATOR = new Comparator()
  {
    public int compare(ViewPager.ItemInfo paramAnonymousItemInfo1, ViewPager.ItemInfo paramAnonymousItemInfo2)
    {
      return position - position;
    }
  };
  private static final boolean DEBUG = false;
  private static final int DEFAULT_GUTTER_SIZE = 16;
  private static final int DEFAULT_OFFSCREEN_PAGES = 1;
  private static final int DRAW_ORDER_DEFAULT = 0;
  private static final int DRAW_ORDER_FORWARD = 1;
  private static final int DRAW_ORDER_REVERSE = 2;
  private static final int INVALID_POINTER = -1;
  static final int[] LAYOUT_ATTRS = { 16842931 };
  private static final int MAX_SETTLE_DURATION = 600;
  private static final int MIN_DISTANCE_FOR_FLING = 25;
  private static final int MIN_FLING_VELOCITY = 400;
  public static final int SCROLL_STATE_DRAGGING = 1;
  public static final int SCROLL_STATE_IDLE = 0;
  public static final int SCROLL_STATE_SETTLING = 2;
  private static final String TAG = "ViewPager";
  private static final boolean USE_CACHE = false;
  private static final Interpolator sInterpolator = new Interpolator()
  {
    public float getInterpolation(float paramAnonymousFloat)
    {
      paramAnonymousFloat -= 1.0F;
      return paramAnonymousFloat * paramAnonymousFloat * paramAnonymousFloat * paramAnonymousFloat * paramAnonymousFloat + 1.0F;
    }
  };
  private static final ViewPositionComparator sPositionComparator = new ViewPositionComparator();
  private int mActivePointerId = -1;
  PagerAdapter mAdapter;
  private List<OnAdapterChangeListener> mAdapterChangeListeners;
  private int mBottomPageBounds;
  private boolean mCalledSuper;
  private int mChildHeightMeasureSpec;
  private int mChildWidthMeasureSpec;
  private int mCloseEnough;
  int mCurItem;
  private int mDecorChildCount;
  private int mDefaultGutterSize;
  private int mDrawingOrder;
  private ArrayList<View> mDrawingOrderedChildren;
  private final Runnable mEndScrollRunnable = new Runnable()
  {
    public void run()
    {
      setScrollState(0);
      populate();
    }
  };
  private int mExpectedAdapterCount;
  private long mFakeDragBeginTime;
  private boolean mFakeDragging;
  private boolean mFirstLayout = true;
  private float mFirstOffset = -3.4028235E38F;
  private int mFlingDistance;
  private int mGutterSize;
  private boolean mInLayout;
  private float mInitialMotionX;
  private float mInitialMotionY;
  private OnPageChangeListener mInternalPageChangeListener;
  private boolean mIsBeingDragged;
  private boolean mIsScrollStarted;
  private boolean mIsUnableToDrag;
  private final ArrayList<ItemInfo> mItems = new ArrayList();
  private float mLastMotionX;
  private float mLastMotionY;
  private float mLastOffset = Float.MAX_VALUE;
  private EdgeEffect mLeftEdge;
  private Drawable mMarginDrawable;
  private int mMaximumVelocity;
  private int mMinimumVelocity;
  private boolean mNeedCalculatePageOffsets = false;
  private PagerObserver mObserver;
  private int mOffscreenPageLimit = 1;
  private OnPageChangeListener mOnPageChangeListener;
  private List<OnPageChangeListener> mOnPageChangeListeners;
  private int mPageMargin;
  private PageTransformer mPageTransformer;
  private int mPageTransformerLayerType;
  private boolean mPopulatePending;
  private Parcelable mRestoredAdapterState = null;
  private ClassLoader mRestoredClassLoader = null;
  private int mRestoredCurItem = -1;
  private EdgeEffect mRightEdge;
  private int mScrollState = 0;
  private Scroller mScroller;
  private boolean mScrollingCacheEnabled;
  private final ItemInfo mTempItem = new ItemInfo();
  private final Rect mTempRect = new Rect();
  private int mTopPageBounds;
  private int mTouchSlop;
  private VelocityTracker mVelocityTracker;
  
  public ViewPager(Context paramContext)
  {
    super(paramContext);
    initViewPager();
  }
  
  public ViewPager(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initViewPager();
  }
  
  private void calculatePageOffsets(ItemInfo paramItemInfo1, int paramInt, ItemInfo paramItemInfo2)
  {
    int m = mAdapter.getCount();
    int i = getClientWidth();
    float f2;
    if (i > 0) {
      f2 = mPageMargin / i;
    } else {
      f2 = 0.0F;
    }
    if (paramItemInfo2 != null)
    {
      i = position;
      if (i < position)
      {
        f1 = offset + widthFactor + f2;
        i += 1;
        j = 0;
        while ((i <= position) && (j < mItems.size()))
        {
          for (paramItemInfo2 = (ItemInfo)mItems.get(j);; paramItemInfo2 = (ItemInfo)mItems.get(j))
          {
            k = i;
            f3 = f1;
            if (i <= position) {
              break;
            }
            k = i;
            f3 = f1;
            if (j >= mItems.size() - 1) {
              break;
            }
            j += 1;
          }
          while (k < position)
          {
            f3 += mAdapter.getPageWidth(k) + f2;
            k += 1;
          }
          offset = f3;
          f1 = f3 + (widthFactor + f2);
          i = k + 1;
        }
      }
      if (i > position)
      {
        j = mItems.size() - 1;
        f1 = offset;
        i -= 1;
        while ((i >= position) && (j >= 0))
        {
          for (paramItemInfo2 = (ItemInfo)mItems.get(j);; paramItemInfo2 = (ItemInfo)mItems.get(j))
          {
            k = i;
            f3 = f1;
            if (i >= position) {
              break;
            }
            k = i;
            f3 = f1;
            if (j <= 0) {
              break;
            }
            j -= 1;
          }
          while (k > position)
          {
            f3 -= mAdapter.getPageWidth(k) + f2;
            k -= 1;
          }
          f1 = f3 - (widthFactor + f2);
          offset = f1;
          i = k - 1;
        }
      }
    }
    int k = mItems.size();
    float f3 = offset;
    i = position - 1;
    if (position == 0) {
      f1 = offset;
    } else {
      f1 = -3.4028235E38F;
    }
    mFirstOffset = f1;
    int j = position;
    m -= 1;
    if (j == m) {
      f1 = offset + widthFactor - 1.0F;
    } else {
      f1 = Float.MAX_VALUE;
    }
    mLastOffset = f1;
    j = paramInt - 1;
    float f1 = f3;
    while (j >= 0)
    {
      paramItemInfo2 = (ItemInfo)mItems.get(j);
      while (i > position)
      {
        f1 -= mAdapter.getPageWidth(i) + f2;
        i -= 1;
      }
      f1 -= widthFactor + f2;
      offset = f1;
      if (position == 0) {
        mFirstOffset = f1;
      }
      j -= 1;
      i -= 1;
    }
    f1 = offset + widthFactor + f2;
    j = position + 1;
    i = paramInt + 1;
    paramInt = j;
    while (i < k)
    {
      paramItemInfo1 = (ItemInfo)mItems.get(i);
      while (paramInt < position)
      {
        f1 += mAdapter.getPageWidth(paramInt) + f2;
        paramInt += 1;
      }
      if (position == m) {
        mLastOffset = (widthFactor + f1 - 1.0F);
      }
      offset = f1;
      f1 += widthFactor + f2;
      i += 1;
      paramInt += 1;
    }
    mNeedCalculatePageOffsets = false;
  }
  
  private void completeScroll(boolean paramBoolean)
  {
    if (mScrollState == 2) {
      i = 1;
    } else {
      i = 0;
    }
    if (i != 0)
    {
      setScrollingCacheEnabled(false);
      if ((mScroller.isFinished() ^ true))
      {
        mScroller.abortAnimation();
        j = getScrollX();
        k = getScrollY();
        int m = mScroller.getCurrX();
        int n = mScroller.getCurrY();
        if ((j != m) || (k != n))
        {
          scrollTo(m, n);
          if (m != j) {
            pageScrolled(m);
          }
        }
      }
    }
    mPopulatePending = false;
    int k = 0;
    int j = i;
    int i = k;
    while (i < mItems.size())
    {
      ItemInfo localItemInfo = (ItemInfo)mItems.get(i);
      if (scrolling)
      {
        scrolling = false;
        j = 1;
      }
      i += 1;
    }
    if (j != 0)
    {
      if (paramBoolean)
      {
        ViewCompat.postOnAnimation(this, mEndScrollRunnable);
        return;
      }
      mEndScrollRunnable.run();
    }
  }
  
  private int determineTargetPage(int paramInt1, float paramFloat, int paramInt2, int paramInt3)
  {
    if ((Math.abs(paramInt3) > mFlingDistance) && (Math.abs(paramInt2) > mMinimumVelocity))
    {
      if (paramInt2 <= 0) {
        paramInt1 += 1;
      }
    }
    else
    {
      float f;
      if (paramInt1 >= mCurItem) {
        f = 0.4F;
      } else {
        f = 0.6F;
      }
      paramInt1 += (int)(paramFloat + f);
    }
    paramInt2 = paramInt1;
    if (mItems.size() > 0)
    {
      ItemInfo localItemInfo1 = (ItemInfo)mItems.get(0);
      ItemInfo localItemInfo2 = (ItemInfo)mItems.get(mItems.size() - 1);
      paramInt2 = Math.max(position, Math.min(paramInt1, position));
    }
    return paramInt2;
  }
  
  private void dispatchOnPageScrolled(int paramInt1, float paramFloat, int paramInt2)
  {
    if (mOnPageChangeListener != null) {
      mOnPageChangeListener.onPageScrolled(paramInt1, paramFloat, paramInt2);
    }
    if (mOnPageChangeListeners != null)
    {
      int i = 0;
      int j = mOnPageChangeListeners.size();
      while (i < j)
      {
        OnPageChangeListener localOnPageChangeListener = (OnPageChangeListener)mOnPageChangeListeners.get(i);
        if (localOnPageChangeListener != null) {
          localOnPageChangeListener.onPageScrolled(paramInt1, paramFloat, paramInt2);
        }
        i += 1;
      }
    }
    if (mInternalPageChangeListener != null) {
      mInternalPageChangeListener.onPageScrolled(paramInt1, paramFloat, paramInt2);
    }
  }
  
  private void dispatchOnPageSelected(int paramInt)
  {
    if (mOnPageChangeListener != null) {
      mOnPageChangeListener.onPageSelected(paramInt);
    }
    if (mOnPageChangeListeners != null)
    {
      int i = 0;
      int j = mOnPageChangeListeners.size();
      while (i < j)
      {
        OnPageChangeListener localOnPageChangeListener = (OnPageChangeListener)mOnPageChangeListeners.get(i);
        if (localOnPageChangeListener != null) {
          localOnPageChangeListener.onPageSelected(paramInt);
        }
        i += 1;
      }
    }
    if (mInternalPageChangeListener != null) {
      mInternalPageChangeListener.onPageSelected(paramInt);
    }
  }
  
  private void dispatchOnScrollStateChanged(int paramInt)
  {
    if (mOnPageChangeListener != null) {
      mOnPageChangeListener.onPageScrollStateChanged(paramInt);
    }
    if (mOnPageChangeListeners != null)
    {
      int i = 0;
      int j = mOnPageChangeListeners.size();
      while (i < j)
      {
        OnPageChangeListener localOnPageChangeListener = (OnPageChangeListener)mOnPageChangeListeners.get(i);
        if (localOnPageChangeListener != null) {
          localOnPageChangeListener.onPageScrollStateChanged(paramInt);
        }
        i += 1;
      }
    }
    if (mInternalPageChangeListener != null) {
      mInternalPageChangeListener.onPageScrollStateChanged(paramInt);
    }
  }
  
  private void enableLayers(boolean paramBoolean)
  {
    int k = getChildCount();
    int i = 0;
    while (i < k)
    {
      int j;
      if (paramBoolean) {
        j = mPageTransformerLayerType;
      } else {
        j = 0;
      }
      getChildAt(i).setLayerType(j, null);
      i += 1;
    }
  }
  
  private void endDrag()
  {
    mIsBeingDragged = false;
    mIsUnableToDrag = false;
    if (mVelocityTracker != null)
    {
      mVelocityTracker.recycle();
      mVelocityTracker = null;
    }
  }
  
  private Rect getChildRectInPagerCoordinates(Rect paramRect, View paramView)
  {
    Rect localRect = paramRect;
    if (paramRect == null) {
      localRect = new Rect();
    }
    if (paramView == null)
    {
      localRect.set(0, 0, 0, 0);
      return localRect;
    }
    left = paramView.getLeft();
    right = paramView.getRight();
    top = paramView.getTop();
    bottom = paramView.getBottom();
    for (paramRect = paramView.getParent(); ((paramRect instanceof ViewGroup)) && (paramRect != this); paramRect = paramRect.getParent())
    {
      paramRect = (ViewGroup)paramRect;
      left += paramRect.getLeft();
      right += paramRect.getRight();
      top += paramRect.getTop();
      bottom += paramRect.getBottom();
    }
    return localRect;
  }
  
  private int getClientWidth()
  {
    return getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
  }
  
  private ItemInfo infoForCurrentScrollPosition()
  {
    int i = getClientWidth();
    float f1;
    if (i > 0) {
      f1 = getScrollX() / i;
    } else {
      f1 = 0.0F;
    }
    float f2;
    if (i > 0) {
      f2 = mPageMargin / i;
    } else {
      f2 = 0.0F;
    }
    Object localObject = null;
    i = 0;
    int j = 1;
    int k = -1;
    float f3 = 0.0F;
    float f4 = 0.0F;
    while (i < mItems.size())
    {
      ItemInfo localItemInfo2 = (ItemInfo)mItems.get(i);
      int m = i;
      ItemInfo localItemInfo1 = localItemInfo2;
      if (j == 0)
      {
        int n = position;
        k += 1;
        m = i;
        localItemInfo1 = localItemInfo2;
        if (n != k)
        {
          localItemInfo1 = mTempItem;
          offset = (f3 + f4 + f2);
          position = k;
          widthFactor = mAdapter.getPageWidth(position);
          m = i - 1;
        }
      }
      f3 = offset;
      f4 = widthFactor;
      if ((j == 0) && (f1 < f3)) {
        return localObject;
      }
      if (f1 >= f4 + f3 + f2)
      {
        if (m == mItems.size() - 1) {
          return localItemInfo1;
        }
        k = position;
        f4 = widthFactor;
        i = m + 1;
        j = 0;
        localObject = localItemInfo1;
      }
      else
      {
        return localItemInfo1;
      }
    }
    return localObject;
  }
  
  private static boolean isDecorView(View paramView)
  {
    return paramView.getClass().getAnnotation(DecorView.class) != null;
  }
  
  private boolean isGutterDrag(float paramFloat1, float paramFloat2)
  {
    return ((paramFloat1 < mGutterSize) && (paramFloat2 > 0.0F)) || ((paramFloat1 > getWidth() - mGutterSize) && (paramFloat2 < 0.0F));
  }
  
  private void onSecondaryPointerUp(MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getActionIndex();
    if (paramMotionEvent.getPointerId(i) == mActivePointerId)
    {
      if (i == 0) {
        i = 1;
      } else {
        i = 0;
      }
      mLastMotionX = paramMotionEvent.getX(i);
      mActivePointerId = paramMotionEvent.getPointerId(i);
      if (mVelocityTracker != null) {
        mVelocityTracker.clear();
      }
    }
  }
  
  private boolean pageScrolled(int paramInt)
  {
    if (mItems.size() == 0)
    {
      if (mFirstLayout) {
        return false;
      }
      mCalledSuper = false;
      onPageScrolled(0, 0.0F, 0);
      if (mCalledSuper) {
        return false;
      }
      throw new IllegalStateException("onPageScrolled did not call superclass implementation");
    }
    ItemInfo localItemInfo = infoForCurrentScrollPosition();
    int j = getClientWidth();
    int k = mPageMargin;
    float f2 = mPageMargin;
    float f1 = j;
    f2 /= f1;
    int i = position;
    f1 = (paramInt / f1 - offset) / (widthFactor + f2);
    paramInt = (int)((k + j) * f1);
    mCalledSuper = false;
    onPageScrolled(i, f1, paramInt);
    if (mCalledSuper) {
      return true;
    }
    throw new IllegalStateException("onPageScrolled did not call superclass implementation");
  }
  
  private boolean performDrag(float paramFloat)
  {
    float f1 = mLastMotionX;
    mLastMotionX = paramFloat;
    float f2 = getScrollX() + (f1 - paramFloat);
    float f3 = getClientWidth();
    paramFloat = mFirstOffset * f3;
    f1 = mLastOffset * f3;
    Object localObject = mItems;
    boolean bool2 = false;
    boolean bool3 = false;
    boolean bool1 = false;
    localObject = (ItemInfo)((ArrayList)localObject).get(0);
    ItemInfo localItemInfo = (ItemInfo)mItems.get(mItems.size() - 1);
    if (position != 0)
    {
      paramFloat = offset * f3;
      i = 0;
    }
    else
    {
      i = 1;
    }
    int j;
    if (position != mAdapter.getCount() - 1)
    {
      f1 = offset * f3;
      j = 0;
    }
    else
    {
      j = 1;
    }
    if (f2 < paramFloat)
    {
      if (i != 0)
      {
        mLeftEdge.onPull(Math.abs(paramFloat - f2) / f3);
        bool1 = true;
      }
    }
    else
    {
      paramFloat = f2;
      bool1 = bool3;
      if (f2 > f1)
      {
        bool1 = bool2;
        if (j != 0)
        {
          mRightEdge.onPull(Math.abs(f2 - f1) / f3);
          bool1 = true;
        }
        paramFloat = f1;
      }
    }
    f1 = mLastMotionX;
    int i = (int)paramFloat;
    mLastMotionX = (f1 + (paramFloat - i));
    scrollTo(i, getScrollY());
    pageScrolled(i);
    return bool1;
  }
  
  private void recomputeScrollPosition(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if ((paramInt2 > 0) && (!mItems.isEmpty()))
    {
      if (!mScroller.isFinished())
      {
        mScroller.setFinalX(getCurrentItem() * getClientWidth());
        return;
      }
      int i = getPaddingLeft();
      int j = getPaddingRight();
      int k = getPaddingLeft();
      int m = getPaddingRight();
      scrollTo((int)(getScrollX() / (paramInt2 - k - m + paramInt4) * (paramInt1 - i - j + paramInt3)), getScrollY());
      return;
    }
    ItemInfo localItemInfo = infoForPosition(mCurItem);
    float f;
    if (localItemInfo != null) {
      f = Math.min(offset, mLastOffset);
    } else {
      f = 0.0F;
    }
    paramInt1 = (int)(f * (paramInt1 - getPaddingLeft() - getPaddingRight()));
    if (paramInt1 != getScrollX())
    {
      completeScroll(false);
      scrollTo(paramInt1, getScrollY());
    }
  }
  
  private void removeNonDecorViews()
  {
    int j;
    for (int i = 0; i < getChildCount(); i = j + 1)
    {
      j = i;
      if (!getChildAtgetLayoutParamsisDecor)
      {
        removeViewAt(i);
        j = i - 1;
      }
    }
  }
  
  private void requestParentDisallowInterceptTouchEvent(boolean paramBoolean)
  {
    ViewParent localViewParent = getParent();
    if (localViewParent != null) {
      localViewParent.requestDisallowInterceptTouchEvent(paramBoolean);
    }
  }
  
  private boolean resetTouch()
  {
    mActivePointerId = -1;
    endDrag();
    mLeftEdge.onRelease();
    mRightEdge.onRelease();
    return (mLeftEdge.isFinished()) || (mRightEdge.isFinished());
  }
  
  private void scrollToItem(int paramInt1, boolean paramBoolean1, int paramInt2, boolean paramBoolean2)
  {
    ItemInfo localItemInfo = infoForPosition(paramInt1);
    int i;
    if (localItemInfo != null) {
      i = (int)(getClientWidth() * Math.max(mFirstOffset, Math.min(offset, mLastOffset)));
    } else {
      i = 0;
    }
    if (paramBoolean1)
    {
      smoothScrollTo(i, 0, paramInt2);
      if (paramBoolean2) {
        dispatchOnPageSelected(paramInt1);
      }
    }
    else
    {
      if (paramBoolean2) {
        dispatchOnPageSelected(paramInt1);
      }
      completeScroll(false);
      scrollTo(i, 0);
      pageScrolled(i);
    }
  }
  
  private void setScrollingCacheEnabled(boolean paramBoolean)
  {
    if (mScrollingCacheEnabled != paramBoolean) {
      mScrollingCacheEnabled = paramBoolean;
    }
  }
  
  private void sortChildDrawingOrder()
  {
    if (mDrawingOrder != 0)
    {
      if (mDrawingOrderedChildren == null) {
        mDrawingOrderedChildren = new ArrayList();
      } else {
        mDrawingOrderedChildren.clear();
      }
      int j = getChildCount();
      int i = 0;
      while (i < j)
      {
        View localView = getChildAt(i);
        mDrawingOrderedChildren.add(localView);
        i += 1;
      }
      Collections.sort(mDrawingOrderedChildren, sPositionComparator);
    }
  }
  
  public void addFocusables(ArrayList paramArrayList, int paramInt1, int paramInt2)
  {
    int j = paramArrayList.size();
    int k = getDescendantFocusability();
    if (k != 393216)
    {
      int i = 0;
      while (i < getChildCount())
      {
        View localView = getChildAt(i);
        if (localView.getVisibility() == 0)
        {
          ItemInfo localItemInfo = infoForChild(localView);
          if ((localItemInfo != null) && (position == mCurItem)) {
            localView.addFocusables(paramArrayList, paramInt1, paramInt2);
          }
        }
        i += 1;
      }
    }
    if ((k != 262144) || (j == paramArrayList.size()))
    {
      if (!isFocusable()) {
        return;
      }
      if (((paramInt2 & 0x1) == 1) && (isInTouchMode()) && (!isFocusableInTouchMode())) {
        return;
      }
      if (paramArrayList != null) {
        paramArrayList.add(this);
      }
    }
  }
  
  ItemInfo addNewItem(int paramInt1, int paramInt2)
  {
    ItemInfo localItemInfo = new ItemInfo();
    position = paramInt1;
    object = mAdapter.instantiateItem(this, paramInt1);
    widthFactor = mAdapter.getPageWidth(paramInt1);
    if ((paramInt2 >= 0) && (paramInt2 < mItems.size()))
    {
      mItems.add(paramInt2, localItemInfo);
      return localItemInfo;
    }
    mItems.add(localItemInfo);
    return localItemInfo;
  }
  
  public void addOnAdapterChangeListener(OnAdapterChangeListener paramOnAdapterChangeListener)
  {
    if (mAdapterChangeListeners == null) {
      mAdapterChangeListeners = new ArrayList();
    }
    mAdapterChangeListeners.add(paramOnAdapterChangeListener);
  }
  
  public void addOnPageChangeListener(OnPageChangeListener paramOnPageChangeListener)
  {
    if (mOnPageChangeListeners == null) {
      mOnPageChangeListeners = new ArrayList();
    }
    mOnPageChangeListeners.add(paramOnPageChangeListener);
  }
  
  public void addTouchables(ArrayList paramArrayList)
  {
    int i = 0;
    while (i < getChildCount())
    {
      View localView = getChildAt(i);
      if (localView.getVisibility() == 0)
      {
        ItemInfo localItemInfo = infoForChild(localView);
        if ((localItemInfo != null) && (position == mCurItem)) {
          localView.addTouchables(paramArrayList);
        }
      }
      i += 1;
    }
  }
  
  public void addView(View paramView, int paramInt, ViewGroup.LayoutParams paramLayoutParams)
  {
    ViewGroup.LayoutParams localLayoutParams = paramLayoutParams;
    if (!checkLayoutParams(paramLayoutParams)) {
      localLayoutParams = generateLayoutParams(paramLayoutParams);
    }
    paramLayoutParams = (LayoutParams)localLayoutParams;
    isDecor |= isDecorView(paramView);
    if (mInLayout)
    {
      if ((paramLayoutParams != null) && (isDecor)) {
        throw new IllegalStateException("Cannot add pager decor view during layout");
      }
      needsMeasure = true;
      addViewInLayout(paramView, paramInt, localLayoutParams);
      return;
    }
    super.addView(paramView, paramInt, localLayoutParams);
  }
  
  public boolean arrowScroll(int paramInt)
  {
    View localView2 = findFocus();
    boolean bool = false;
    View localView1 = null;
    Object localObject;
    int i;
    if (localView2 == this)
    {
      localObject = localView1;
    }
    else
    {
      if (localView2 != null)
      {
        for (localObject = localView2.getParent(); (localObject instanceof ViewGroup); localObject = ((ViewParent)localObject).getParent()) {
          if (localObject == this)
          {
            i = 1;
            break label70;
          }
        }
        i = 0;
        label70:
        if (i == 0)
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append(localView2.getClass().getSimpleName());
          for (localObject = localView2.getParent(); (localObject instanceof ViewGroup); localObject = ((ViewParent)localObject).getParent())
          {
            localStringBuilder.append(" => ");
            localStringBuilder.append(localObject.getClass().getSimpleName());
          }
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("arrowScroll tried to find focus based on non-child current focused view ");
          ((StringBuilder)localObject).append(localStringBuilder.toString());
          Log.e("ViewPager", ((StringBuilder)localObject).toString());
          localObject = localView1;
          break label198;
        }
      }
      localObject = localView2;
    }
    label198:
    localView1 = FocusFinder.getInstance().findNextFocus(this, (View)localObject, paramInt);
    int j;
    if ((localView1 != null) && (localView1 != localObject)) {
      if (paramInt == 17)
      {
        i = getChildRectInPagerCoordinatesmTempRect, localView1).left;
        j = getChildRectInPagerCoordinatesmTempRect, (View)localObject).left;
        if ((localObject != null) && (i >= j)) {
          bool = pageLeft();
        } else {
          bool = localView1.requestFocus();
        }
      }
    }
    for (;;)
    {
      break;
      if (paramInt == 66)
      {
        i = getChildRectInPagerCoordinatesmTempRect, localView1).left;
        j = getChildRectInPagerCoordinatesmTempRect, (View)localObject).left;
        if ((localObject != null) && (i <= j))
        {
          bool = pageRight();
        }
        else
        {
          bool = localView1.requestFocus();
          continue;
          if ((paramInt != 17) && (paramInt != 1))
          {
            if ((paramInt == 66) || (paramInt == 2)) {
              bool = pageRight();
            }
          }
          else {
            bool = pageLeft();
          }
        }
      }
    }
    if (bool) {
      playSoundEffect(SoundEffectConstants.getContantForFocusDirection(paramInt));
    }
    return bool;
  }
  
  public boolean beginFakeDrag()
  {
    if (mIsBeingDragged) {
      return false;
    }
    mFakeDragging = true;
    setScrollState(1);
    mLastMotionX = 0.0F;
    mInitialMotionX = 0.0F;
    if (mVelocityTracker == null) {
      mVelocityTracker = VelocityTracker.obtain();
    } else {
      mVelocityTracker.clear();
    }
    long l = SystemClock.uptimeMillis();
    MotionEvent localMotionEvent = MotionEvent.obtain(l, l, 0, 0.0F, 0.0F, 0);
    mVelocityTracker.addMovement(localMotionEvent);
    localMotionEvent.recycle();
    mFakeDragBeginTime = l;
    return true;
  }
  
  protected boolean canScroll(View paramView, boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3)
  {
    if ((paramView instanceof ViewGroup))
    {
      ViewGroup localViewGroup = (ViewGroup)paramView;
      int j = paramView.getScrollX();
      int k = paramView.getScrollY();
      int i = localViewGroup.getChildCount() - 1;
      while (i >= 0)
      {
        View localView = localViewGroup.getChildAt(i);
        int m = paramInt2 + j;
        if ((m >= localView.getLeft()) && (m < localView.getRight()))
        {
          int n = paramInt3 + k;
          if ((n >= localView.getTop()) && (n < localView.getBottom()) && (canScroll(localView, true, paramInt1, m - localView.getLeft(), n - localView.getTop()))) {
            return true;
          }
        }
        i -= 1;
      }
    }
    return (paramBoolean) && (paramView.canScrollHorizontally(-paramInt1));
  }
  
  public boolean canScrollHorizontally(int paramInt)
  {
    if (mAdapter == null) {
      return false;
    }
    int i = getClientWidth();
    int j = getScrollX();
    if (paramInt < 0)
    {
      if (j > (int)(i * mFirstOffset)) {
        return true;
      }
    }
    else if ((paramInt > 0) && (j < (int)(i * mLastOffset))) {
      return true;
    }
    return false;
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return ((paramLayoutParams instanceof LayoutParams)) && (super.checkLayoutParams(paramLayoutParams));
  }
  
  public void clearOnPageChangeListeners()
  {
    if (mOnPageChangeListeners != null) {
      mOnPageChangeListeners.clear();
    }
  }
  
  public void computeScroll()
  {
    mIsScrollStarted = true;
    if ((!mScroller.isFinished()) && (mScroller.computeScrollOffset()))
    {
      int i = getScrollX();
      int j = getScrollY();
      int k = mScroller.getCurrX();
      int m = mScroller.getCurrY();
      if ((i != k) || (j != m))
      {
        scrollTo(k, m);
        if (!pageScrolled(k))
        {
          mScroller.abortAnimation();
          scrollTo(0, m);
        }
      }
      ViewCompat.postInvalidateOnAnimation(this);
      return;
    }
    completeScroll(true);
  }
  
  void dataSetChanged()
  {
    int i4 = mAdapter.getCount();
    mExpectedAdapterCount = i4;
    if ((mItems.size() < mOffscreenPageLimit * 2 + 1) && (mItems.size() < i4)) {
      i = 1;
    } else {
      i = 0;
    }
    int m = mCurItem;
    int k = 0;
    int j = 0;
    int i1 = i;
    int i = m;
    Object localObject;
    while (k < mItems.size())
    {
      localObject = (ItemInfo)mItems.get(k);
      int i3 = mAdapter.getItemPosition(object);
      int n;
      int i2;
      if (i3 == -1)
      {
        m = k;
        n = i;
        i2 = j;
      }
      else
      {
        if (i3 == -2)
        {
          mItems.remove(k);
          n = k - 1;
          m = j;
          if (j == 0)
          {
            mAdapter.startUpdate(this);
            m = 1;
          }
          mAdapter.destroyItem(this, position, object);
          k = n;
          j = m;
          if (mCurItem == position)
          {
            i = Math.max(0, Math.min(mCurItem, i4 - 1));
            j = m;
            k = n;
          }
        }
        for (;;)
        {
          i1 = 1;
          m = k;
          n = i;
          i2 = j;
          break;
          m = k;
          n = i;
          i2 = j;
          if (position == i3) {
            break;
          }
          if (position == mCurItem) {
            i = i3;
          }
          position = i3;
        }
      }
      k = m + 1;
      i = n;
      j = i2;
    }
    if (j != 0) {
      mAdapter.finishUpdate(this);
    }
    Collections.sort(mItems, COMPARATOR);
    if (i1 != 0)
    {
      k = getChildCount();
      j = 0;
      while (j < k)
      {
        localObject = (LayoutParams)getChildAt(j).getLayoutParams();
        if (!isDecor) {
          widthFactor = 0.0F;
        }
        j += 1;
      }
      setCurrentItemInternal(i, false, true);
      requestLayout();
    }
  }
  
  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    return (super.dispatchKeyEvent(paramKeyEvent)) || (executeKeyEvent(paramKeyEvent));
  }
  
  public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    if (paramAccessibilityEvent.getEventType() == 4096) {
      return super.dispatchPopulateAccessibilityEvent(paramAccessibilityEvent);
    }
    int j = getChildCount();
    int i = 0;
    while (i < j)
    {
      View localView = getChildAt(i);
      if (localView.getVisibility() == 0)
      {
        ItemInfo localItemInfo = infoForChild(localView);
        if ((localItemInfo != null) && (position == mCurItem) && (localView.dispatchPopulateAccessibilityEvent(paramAccessibilityEvent))) {
          return true;
        }
      }
      i += 1;
    }
    return false;
  }
  
  float distanceInfluenceForSnapDuration(float paramFloat)
  {
    return (float)Math.sin((paramFloat - 0.5F) * 0.47123894F);
  }
  
  public void draw(Canvas paramCanvas)
  {
    super.draw(paramCanvas);
    int m = getOverScrollMode();
    int k = 0;
    int i = 0;
    boolean bool;
    if ((m != 0) && ((m != 1) || (mAdapter == null) || (mAdapter.getCount() <= 1)))
    {
      mLeftEdge.finish();
      mRightEdge.finish();
    }
    else
    {
      int j;
      if (!mLeftEdge.isFinished())
      {
        k = paramCanvas.save();
        i = getHeight() - getPaddingTop() - getPaddingBottom();
        m = getWidth();
        paramCanvas.rotate(270.0F);
        paramCanvas.translate(-i + getPaddingTop(), mFirstOffset * m);
        mLeftEdge.setSize(i, m);
        j = false | mLeftEdge.draw(paramCanvas);
        paramCanvas.restoreToCount(k);
      }
      k = j;
      if (!mRightEdge.isFinished())
      {
        m = paramCanvas.save();
        k = getWidth();
        int n = getHeight();
        int i1 = getPaddingTop();
        int i2 = getPaddingBottom();
        paramCanvas.rotate(90.0F);
        paramCanvas.translate(-getPaddingTop(), -(mLastOffset + 1.0F) * k);
        mRightEdge.setSize(n - i1 - i2, k);
        bool = j | mRightEdge.draw(paramCanvas);
        paramCanvas.restoreToCount(m);
      }
    }
    if (bool) {
      ViewCompat.postInvalidateOnAnimation(this);
    }
  }
  
  protected void drawableStateChanged()
  {
    super.drawableStateChanged();
    Drawable localDrawable = mMarginDrawable;
    if ((localDrawable != null) && (localDrawable.isStateful())) {
      localDrawable.setState(getDrawableState());
    }
  }
  
  public void endFakeDrag()
  {
    if (mFakeDragging)
    {
      if (mAdapter != null)
      {
        Object localObject = mVelocityTracker;
        ((VelocityTracker)localObject).computeCurrentVelocity(1000, mMaximumVelocity);
        int i = (int)((VelocityTracker)localObject).getXVelocity(mActivePointerId);
        mPopulatePending = true;
        int j = getClientWidth();
        int k = getScrollX();
        localObject = infoForCurrentScrollPosition();
        setCurrentItemInternal(determineTargetPage(position, (k / j - offset) / widthFactor, i, (int)(mLastMotionX - mInitialMotionX)), true, true, i);
      }
      endDrag();
      mFakeDragging = false;
      return;
    }
    throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
  }
  
  public boolean executeKeyEvent(KeyEvent paramKeyEvent)
  {
    if (paramKeyEvent.getAction() == 0)
    {
      int i = paramKeyEvent.getKeyCode();
      if (i != 61)
      {
        switch (i)
        {
        default: 
          break;
        case 22: 
          if (paramKeyEvent.hasModifiers(2)) {
            return pageRight();
          }
          return arrowScroll(66);
        case 21: 
          if (paramKeyEvent.hasModifiers(2)) {
            return pageLeft();
          }
          return arrowScroll(17);
        }
      }
      else
      {
        if (paramKeyEvent.hasNoModifiers()) {
          return arrowScroll(2);
        }
        if (paramKeyEvent.hasModifiers(1)) {
          return arrowScroll(1);
        }
      }
    }
    return false;
  }
  
  public void fakeDragBy(float paramFloat)
  {
    if (mFakeDragging)
    {
      if (mAdapter == null) {
        return;
      }
      mLastMotionX += paramFloat;
      float f2 = getScrollX() - paramFloat;
      float f3 = getClientWidth();
      paramFloat = mFirstOffset * f3;
      float f1 = mLastOffset * f3;
      Object localObject = (ItemInfo)mItems.get(0);
      ItemInfo localItemInfo = (ItemInfo)mItems.get(mItems.size() - 1);
      if (position != 0) {
        paramFloat = offset * f3;
      }
      if (position != mAdapter.getCount() - 1) {
        f1 = offset * f3;
      }
      if (f2 >= paramFloat)
      {
        paramFloat = f2;
        if (f2 > f1) {
          paramFloat = f1;
        }
      }
      f1 = mLastMotionX;
      int i = (int)paramFloat;
      mLastMotionX = (f1 + (paramFloat - i));
      scrollTo(i, getScrollY());
      pageScrolled(i);
      long l = SystemClock.uptimeMillis();
      localObject = MotionEvent.obtain(mFakeDragBeginTime, l, 2, mLastMotionX, 0.0F, 0);
      mVelocityTracker.addMovement((MotionEvent)localObject);
      ((MotionEvent)localObject).recycle();
      return;
    }
    throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
  }
  
  protected ViewGroup.LayoutParams generateDefaultLayoutParams()
  {
    return new LayoutParams();
  }
  
  public ViewGroup.LayoutParams generateLayoutParams(AttributeSet paramAttributeSet)
  {
    return new LayoutParams(getContext(), paramAttributeSet);
  }
  
  protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return generateDefaultLayoutParams();
  }
  
  public PagerAdapter getAdapter()
  {
    return mAdapter;
  }
  
  protected int getChildDrawingOrder(int paramInt1, int paramInt2)
  {
    int i = paramInt2;
    if (mDrawingOrder == 2) {
      i = paramInt1 - 1 - paramInt2;
    }
    return mDrawingOrderedChildren.get(i)).getLayoutParams()).childIndex;
  }
  
  public int getCurrentItem()
  {
    return mCurItem;
  }
  
  public int getOffscreenPageLimit()
  {
    return mOffscreenPageLimit;
  }
  
  public int getPageMargin()
  {
    return mPageMargin;
  }
  
  ItemInfo infoForAnyChild(View paramView)
  {
    for (;;)
    {
      ViewParent localViewParent = paramView.getParent();
      if (localViewParent == this) {
        break label34;
      }
      if ((localViewParent == null) || (!(localViewParent instanceof View))) {
        break;
      }
      paramView = (View)localViewParent;
    }
    return null;
    label34:
    return infoForChild(paramView);
  }
  
  ItemInfo infoForChild(View paramView)
  {
    int i = 0;
    while (i < mItems.size())
    {
      ItemInfo localItemInfo = (ItemInfo)mItems.get(i);
      if (mAdapter.isViewFromObject(paramView, object)) {
        return localItemInfo;
      }
      i += 1;
    }
    return null;
  }
  
  ItemInfo infoForPosition(int paramInt)
  {
    int i = 0;
    while (i < mItems.size())
    {
      ItemInfo localItemInfo = (ItemInfo)mItems.get(i);
      if (position == paramInt) {
        return localItemInfo;
      }
      i += 1;
    }
    return null;
  }
  
  void initViewPager()
  {
    setWillNotDraw(false);
    setDescendantFocusability(262144);
    setFocusable(true);
    Context localContext = getContext();
    mScroller = new Scroller(localContext, sInterpolator);
    ViewConfiguration localViewConfiguration = ViewConfiguration.get(localContext);
    float f = getResourcesgetDisplayMetricsdensity;
    mTouchSlop = localViewConfiguration.getScaledPagingTouchSlop();
    mMinimumVelocity = ((int)(400.0F * f));
    mMaximumVelocity = localViewConfiguration.getScaledMaximumFlingVelocity();
    mLeftEdge = new EdgeEffect(localContext);
    mRightEdge = new EdgeEffect(localContext);
    mFlingDistance = ((int)(25.0F * f));
    mCloseEnough = ((int)(2.0F * f));
    mDefaultGutterSize = ((int)(f * 16.0F));
    ViewCompat.setAccessibilityDelegate(this, new MyAccessibilityDelegate());
    if (ViewCompat.getImportantForAccessibility(this) == 0) {
      ViewCompat.setImportantForAccessibility(this, 1);
    }
    ViewCompat.setOnApplyWindowInsetsListener(this, new OnApplyWindowInsetsListener()
    {
      private final Rect mTempRect = new Rect();
      
      public WindowInsetsCompat onApplyWindowInsets(View paramAnonymousView, WindowInsetsCompat paramAnonymousWindowInsetsCompat)
      {
        paramAnonymousView = ViewCompat.onApplyWindowInsets(paramAnonymousView, paramAnonymousWindowInsetsCompat);
        if (paramAnonymousView.isConsumed()) {
          return paramAnonymousView;
        }
        paramAnonymousWindowInsetsCompat = mTempRect;
        left = paramAnonymousView.getSystemWindowInsetLeft();
        top = paramAnonymousView.getSystemWindowInsetTop();
        right = paramAnonymousView.getSystemWindowInsetRight();
        bottom = paramAnonymousView.getSystemWindowInsetBottom();
        int i = 0;
        int j = getChildCount();
        while (i < j)
        {
          WindowInsetsCompat localWindowInsetsCompat = ViewCompat.dispatchApplyWindowInsets(getChildAt(i), paramAnonymousView);
          left = Math.min(localWindowInsetsCompat.getSystemWindowInsetLeft(), left);
          top = Math.min(localWindowInsetsCompat.getSystemWindowInsetTop(), top);
          right = Math.min(localWindowInsetsCompat.getSystemWindowInsetRight(), right);
          bottom = Math.min(localWindowInsetsCompat.getSystemWindowInsetBottom(), bottom);
          i += 1;
        }
        return paramAnonymousView.replaceSystemWindowInsets(left, top, right, bottom);
      }
    });
  }
  
  public boolean isFakeDragging()
  {
    return mFakeDragging;
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    mFirstLayout = true;
  }
  
  protected void onDetachedFromWindow()
  {
    removeCallbacks(mEndScrollRunnable);
    if ((mScroller != null) && (!mScroller.isFinished())) {
      mScroller.abortAnimation();
    }
    super.onDetachedFromWindow();
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if ((mPageMargin > 0) && (mMarginDrawable != null) && (mItems.size() > 0) && (mAdapter != null))
    {
      int k = getScrollX();
      int m = getWidth();
      float f1 = mPageMargin;
      float f4 = m;
      float f5 = f1 / f4;
      Object localObject = mItems;
      int j = 0;
      localObject = (ItemInfo)((ArrayList)localObject).get(0);
      f1 = offset;
      int n = mItems.size();
      int i = position;
      int i1 = mItems.get(n - 1)).position;
      while (i < i1)
      {
        while ((i > position) && (j < n))
        {
          localObject = mItems;
          j += 1;
          localObject = (ItemInfo)((ArrayList)localObject).get(j);
        }
        float f2;
        if (i == position)
        {
          f2 = (offset + widthFactor) * f4;
          f1 = offset + widthFactor + f5;
        }
        else
        {
          float f3 = mAdapter.getPageWidth(i);
          f2 = f1 + (f3 + f5);
          f3 = (f1 + f3) * f4;
          f1 = f2;
          f2 = f3;
        }
        if (mPageMargin + f2 > k)
        {
          mMarginDrawable.setBounds(Math.round(f2), mTopPageBounds, Math.round(mPageMargin + f2), mBottomPageBounds);
          mMarginDrawable.draw(paramCanvas);
        }
        if (f2 > k + m) {
          return;
        }
        i += 1;
      }
    }
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getAction() & 0xFF;
    if ((i != 3) && (i != 1))
    {
      if (i != 0)
      {
        if (mIsBeingDragged) {
          return true;
        }
        if (mIsUnableToDrag) {
          return false;
        }
      }
      float f1;
      if (i != 0)
      {
        if (i != 2)
        {
          if (i == 6) {
            onSecondaryPointerUp(paramMotionEvent);
          }
        }
        else
        {
          i = mActivePointerId;
          if (i != -1)
          {
            i = paramMotionEvent.findPointerIndex(i);
            float f2 = paramMotionEvent.getX(i);
            f1 = f2 - mLastMotionX;
            float f4 = Math.abs(f1);
            float f3 = paramMotionEvent.getY(i);
            float f5 = Math.abs(f3 - mInitialMotionY);
            if ((f1 != 0.0F) && (!isGutterDrag(mLastMotionX, f1)) && (canScroll(this, false, (int)f1, (int)f2, (int)f3)))
            {
              mLastMotionX = f2;
              mLastMotionY = f3;
              mIsUnableToDrag = true;
              return false;
            }
            if ((f4 > mTouchSlop) && (f4 * 0.5F > f5))
            {
              mIsBeingDragged = true;
              requestParentDisallowInterceptTouchEvent(true);
              setScrollState(1);
              if (f1 > 0.0F) {
                f1 = mInitialMotionX + mTouchSlop;
              } else {
                f1 = mInitialMotionX - mTouchSlop;
              }
              mLastMotionX = f1;
              mLastMotionY = f3;
              setScrollingCacheEnabled(true);
            }
            else if (f5 > mTouchSlop)
            {
              mIsUnableToDrag = true;
            }
            if ((mIsBeingDragged) && (performDrag(f2))) {
              ViewCompat.postInvalidateOnAnimation(this);
            }
          }
        }
      }
      else
      {
        f1 = paramMotionEvent.getX();
        mInitialMotionX = f1;
        mLastMotionX = f1;
        f1 = paramMotionEvent.getY();
        mInitialMotionY = f1;
        mLastMotionY = f1;
        mActivePointerId = paramMotionEvent.getPointerId(0);
        mIsUnableToDrag = false;
        mIsScrollStarted = true;
        mScroller.computeScrollOffset();
        if ((mScrollState == 2) && (Math.abs(mScroller.getFinalX() - mScroller.getCurrX()) > mCloseEnough))
        {
          mScroller.abortAnimation();
          mPopulatePending = false;
          populate();
          mIsBeingDragged = true;
          requestParentDisallowInterceptTouchEvent(true);
          setScrollState(1);
        }
        else
        {
          completeScroll(false);
          mIsBeingDragged = false;
        }
      }
      if (mVelocityTracker == null) {
        mVelocityTracker = VelocityTracker.obtain();
      }
      mVelocityTracker.addMovement(paramMotionEvent);
      return mIsBeingDragged;
    }
    resetTouch();
    return false;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i3 = getChildCount();
    int i4 = paramInt3 - paramInt1;
    int i5 = paramInt4 - paramInt2;
    paramInt2 = getPaddingLeft();
    paramInt1 = getPaddingTop();
    int j = getPaddingRight();
    paramInt4 = getPaddingBottom();
    int i6 = getScrollX();
    int k = 0;
    int m = 0;
    View localView;
    int i;
    LayoutParams localLayoutParams;
    while (m < i3)
    {
      localView = getChildAt(m);
      i = paramInt2;
      int i2 = j;
      int i1 = k;
      int n = paramInt4;
      paramInt3 = paramInt1;
      if (localView.getVisibility() != 8)
      {
        localLayoutParams = (LayoutParams)localView.getLayoutParams();
        i = paramInt2;
        i2 = j;
        i1 = k;
        n = paramInt4;
        paramInt3 = paramInt1;
        if (isDecor)
        {
          paramInt3 = gravity & 0x7;
          n = gravity & 0x70;
          if (paramInt3 != 1)
          {
            if (paramInt3 != 3)
            {
              if (paramInt3 != 5)
              {
                paramInt3 = paramInt2;
                i = paramInt2;
              }
              else
              {
                paramInt3 = i4 - j - localView.getMeasuredWidth();
                j += localView.getMeasuredWidth();
                i = paramInt2;
              }
            }
            else
            {
              i = localView.getMeasuredWidth();
              paramInt3 = paramInt2;
              i += paramInt2;
            }
          }
          else
          {
            paramInt3 = Math.max((i4 - localView.getMeasuredWidth()) / 2, paramInt2);
            i = paramInt2;
          }
          if (n != 16)
          {
            if (n != 48)
            {
              if (n != 80)
              {
                paramInt2 = paramInt1;
              }
              else
              {
                paramInt2 = i5 - paramInt4 - localView.getMeasuredHeight();
                paramInt4 += localView.getMeasuredHeight();
              }
            }
            else
            {
              n = localView.getMeasuredHeight();
              paramInt2 = paramInt1;
              paramInt1 = n + paramInt1;
            }
          }
          else {
            paramInt2 = Math.max((i5 - localView.getMeasuredHeight()) / 2, paramInt1);
          }
          paramInt3 += i6;
          localView.layout(paramInt3, paramInt2, localView.getMeasuredWidth() + paramInt3, paramInt2 + localView.getMeasuredHeight());
          i1 = k + 1;
          paramInt3 = paramInt1;
          n = paramInt4;
          i2 = j;
        }
      }
      m += 1;
      paramInt2 = i;
      j = i2;
      k = i1;
      paramInt4 = n;
      paramInt1 = paramInt3;
    }
    paramInt3 = 0;
    while (paramInt3 < i3)
    {
      localView = getChildAt(paramInt3);
      if (localView.getVisibility() != 8)
      {
        localLayoutParams = (LayoutParams)localView.getLayoutParams();
        if (!isDecor)
        {
          ItemInfo localItemInfo = infoForChild(localView);
          if (localItemInfo != null)
          {
            float f = i4 - paramInt2 - j;
            i = (int)(offset * f) + paramInt2;
            if (needsMeasure)
            {
              needsMeasure = false;
              localView.measure(View.MeasureSpec.makeMeasureSpec((int)(f * widthFactor), 1073741824), View.MeasureSpec.makeMeasureSpec(i5 - paramInt1 - paramInt4, 1073741824));
            }
            localView.layout(i, paramInt1, localView.getMeasuredWidth() + i, localView.getMeasuredHeight() + paramInt1);
          }
        }
      }
      paramInt3 += 1;
    }
    mTopPageBounds = paramInt1;
    mBottomPageBounds = (i5 - paramInt4);
    mDecorChildCount = k;
    if (mFirstLayout) {
      scrollToItem(mCurItem, false, 0, false);
    }
    mFirstLayout = false;
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    setMeasuredDimension(View.getDefaultSize(0, paramInt1), View.getDefaultSize(0, paramInt2));
    paramInt1 = getMeasuredWidth();
    mGutterSize = Math.min(paramInt1 / 10, mDefaultGutterSize);
    paramInt1 = paramInt1 - getPaddingLeft() - getPaddingRight();
    paramInt2 = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
    int i4 = getChildCount();
    int k = 0;
    View localView;
    LayoutParams localLayoutParams;
    for (;;)
    {
      int i1 = 1;
      int i3 = 1073741824;
      if (k >= i4) {
        break;
      }
      localView = getChildAt(k);
      i = paramInt2;
      int j = paramInt1;
      if (localView.getVisibility() != 8)
      {
        localLayoutParams = (LayoutParams)localView.getLayoutParams();
        i = paramInt2;
        j = paramInt1;
        if (localLayoutParams != null)
        {
          i = paramInt2;
          j = paramInt1;
          if (isDecor)
          {
            i = gravity & 0x7;
            j = gravity & 0x70;
            int n;
            if ((j != 48) && (j != 80)) {
              n = 0;
            } else {
              n = 1;
            }
            int m = i1;
            if (i != 3) {
              if (i == 5) {
                m = i1;
              } else {
                m = 0;
              }
            }
            i = Integer.MIN_VALUE;
            if (n != 0) {
              j = 1073741824;
            }
            do
            {
              i = Integer.MIN_VALUE;
              break;
              j = i;
            } while (m == 0);
            i1 = 1073741824;
            j = i;
            i = i1;
            int i2;
            if (width != -2)
            {
              if (width != -1) {
                j = width;
              } else {
                j = paramInt1;
              }
              i1 = 1073741824;
              i2 = j;
            }
            else
            {
              i2 = paramInt1;
              i1 = j;
            }
            if (height != -2)
            {
              if (height != -1)
              {
                i = height;
                j = i3;
              }
              else
              {
                i = paramInt2;
                j = i3;
              }
            }
            else
            {
              j = paramInt2;
              i3 = i;
              i = j;
              j = i3;
            }
            localView.measure(View.MeasureSpec.makeMeasureSpec(i2, i1), View.MeasureSpec.makeMeasureSpec(i, j));
            if (n != 0)
            {
              i = paramInt2 - localView.getMeasuredHeight();
              j = paramInt1;
            }
            else
            {
              i = paramInt2;
              j = paramInt1;
              if (m != 0)
              {
                j = paramInt1 - localView.getMeasuredWidth();
                i = paramInt2;
              }
            }
          }
        }
      }
      k += 1;
      paramInt2 = i;
      paramInt1 = j;
    }
    mChildWidthMeasureSpec = View.MeasureSpec.makeMeasureSpec(paramInt1, 1073741824);
    mChildHeightMeasureSpec = View.MeasureSpec.makeMeasureSpec(paramInt2, 1073741824);
    mInLayout = true;
    populate();
    paramInt2 = 0;
    mInLayout = false;
    int i = getChildCount();
    while (paramInt2 < i)
    {
      localView = getChildAt(paramInt2);
      if (localView.getVisibility() != 8)
      {
        localLayoutParams = (LayoutParams)localView.getLayoutParams();
        if ((localLayoutParams == null) || (!isDecor)) {
          localView.measure(View.MeasureSpec.makeMeasureSpec((int)(paramInt1 * widthFactor), 1073741824), mChildHeightMeasureSpec);
        }
      }
      paramInt2 += 1;
    }
  }
  
  protected void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
  {
    int i = mDecorChildCount;
    int i1 = 0;
    View localView;
    if (i > 0)
    {
      int i2 = getScrollX();
      i = getPaddingLeft();
      int j = getPaddingRight();
      int i3 = getWidth();
      int i4 = getChildCount();
      int m = 0;
      while (m < i4)
      {
        localView = getChildAt(m);
        LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
        if (isDecor)
        {
          int k = gravity & 0x7;
          if (k != 1)
          {
            int n;
            if (k != 3)
            {
              if (k != 5)
              {
                n = i;
                k = i;
                i = n;
              }
              else
              {
                k = i3 - j - localView.getMeasuredWidth();
                j += localView.getMeasuredWidth();
              }
            }
            else
            {
              n = localView.getWidth() + i;
              k = i;
              i = n;
            }
          }
          else
          {
            k = Math.max((i3 - localView.getMeasuredWidth()) / 2, i);
          }
          k = k + i2 - localView.getLeft();
          if (k != 0) {
            localView.offsetLeftAndRight(k);
          }
        }
        m += 1;
      }
    }
    dispatchOnPageScrolled(paramInt1, paramFloat, paramInt2);
    if (mPageTransformer != null)
    {
      paramInt2 = getScrollX();
      i = getChildCount();
      paramInt1 = i1;
      while (paramInt1 < i)
      {
        localView = getChildAt(paramInt1);
        if (!getLayoutParamsisDecor)
        {
          paramFloat = (localView.getLeft() - paramInt2) / getClientWidth();
          mPageTransformer.transformPage(localView, paramFloat);
        }
        paramInt1 += 1;
      }
    }
    mCalledSuper = true;
  }
  
  protected boolean onRequestFocusInDescendants(int paramInt, Rect paramRect)
  {
    int i = getChildCount();
    int j = -1;
    int k;
    if ((paramInt & 0x2) != 0)
    {
      j = i;
      i = 0;
      k = 1;
    }
    else
    {
      i -= 1;
      k = -1;
    }
    while (i != j)
    {
      View localView = getChildAt(i);
      if (localView.getVisibility() == 0)
      {
        ItemInfo localItemInfo = infoForChild(localView);
        if ((localItemInfo != null) && (position == mCurItem) && (localView.requestFocus(paramInt, paramRect))) {
          return true;
        }
      }
      i += k;
    }
    return false;
  }
  
  public void onRestoreInstanceState(Parcelable paramParcelable)
  {
    if (!(paramParcelable instanceof SavedState))
    {
      super.onRestoreInstanceState(paramParcelable);
      return;
    }
    paramParcelable = (SavedState)paramParcelable;
    super.onRestoreInstanceState(paramParcelable.getSuperState());
    if (mAdapter != null)
    {
      mAdapter.restoreState(adapterState, loader);
      setCurrentItemInternal(position, false, true);
      return;
    }
    mRestoredCurItem = position;
    mRestoredAdapterState = adapterState;
    mRestoredClassLoader = loader;
  }
  
  public Parcelable onSaveInstanceState()
  {
    SavedState localSavedState = new SavedState(super.onSaveInstanceState());
    position = mCurItem;
    if (mAdapter != null) {
      adapterState = mAdapter.saveState();
    }
    return localSavedState;
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (paramInt1 != paramInt3) {
      recomputeScrollPosition(paramInt1, paramInt3, mPageMargin, mPageMargin);
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (mFakeDragging) {
      return true;
    }
    int i = paramMotionEvent.getAction();
    boolean bool = false;
    if ((i == 0) && (paramMotionEvent.getEdgeFlags() != 0)) {
      return false;
    }
    if (mAdapter != null)
    {
      if (mAdapter.getCount() == 0) {
        return false;
      }
      if (mVelocityTracker == null) {
        mVelocityTracker = VelocityTracker.obtain();
      }
      mVelocityTracker.addMovement(paramMotionEvent);
      float f2;
      Object localObject;
      switch (paramMotionEvent.getAction() & 0xFF)
      {
      default: 
        break;
      case 4: 
        break;
      case 6: 
        onSecondaryPointerUp(paramMotionEvent);
        mLastMotionX = paramMotionEvent.getX(paramMotionEvent.findPointerIndex(mActivePointerId));
        break;
      case 5: 
        i = paramMotionEvent.getActionIndex();
        mLastMotionX = paramMotionEvent.getX(i);
        mActivePointerId = paramMotionEvent.getPointerId(i);
        break;
      case 3: 
        if (!mIsBeingDragged) {
          break;
        }
        scrollToItem(mCurItem, true, 0, false);
        bool = resetTouch();
        break;
      case 2: 
        if (!mIsBeingDragged)
        {
          i = paramMotionEvent.findPointerIndex(mActivePointerId);
          if (i == -1)
          {
            bool = resetTouch();
            break;
          }
          f1 = paramMotionEvent.getX(i);
          float f3 = Math.abs(f1 - mLastMotionX);
          f2 = paramMotionEvent.getY(i);
          float f4 = Math.abs(f2 - mLastMotionY);
          if ((f3 > mTouchSlop) && (f3 > f4))
          {
            mIsBeingDragged = true;
            requestParentDisallowInterceptTouchEvent(true);
            if (f1 - mInitialMotionX > 0.0F) {
              f1 = mInitialMotionX + mTouchSlop;
            } else {
              f1 = mInitialMotionX - mTouchSlop;
            }
            mLastMotionX = f1;
            mLastMotionY = f2;
            setScrollState(1);
            setScrollingCacheEnabled(true);
            localObject = getParent();
            if (localObject != null) {
              ((ViewParent)localObject).requestDisallowInterceptTouchEvent(true);
            }
          }
        }
        if (!mIsBeingDragged) {
          break;
        }
        bool = false | performDrag(paramMotionEvent.getX(paramMotionEvent.findPointerIndex(mActivePointerId)));
        break;
      case 1: 
        if (!mIsBeingDragged) {
          break;
        }
        localObject = mVelocityTracker;
        ((VelocityTracker)localObject).computeCurrentVelocity(1000, mMaximumVelocity);
        i = (int)((VelocityTracker)localObject).getXVelocity(mActivePointerId);
        mPopulatePending = true;
        int j = getClientWidth();
        int k = getScrollX();
        localObject = infoForCurrentScrollPosition();
        f2 = mPageMargin;
        f1 = j;
        f2 /= f1;
        setCurrentItemInternal(determineTargetPage(position, (k / f1 - offset) / (widthFactor + f2), i, (int)(paramMotionEvent.getX(paramMotionEvent.findPointerIndex(mActivePointerId)) - mInitialMotionX)), true, true, i);
        bool = resetTouch();
        break;
      }
      mScroller.abortAnimation();
      mPopulatePending = false;
      populate();
      float f1 = paramMotionEvent.getX();
      mInitialMotionX = f1;
      mLastMotionX = f1;
      f1 = paramMotionEvent.getY();
      mInitialMotionY = f1;
      mLastMotionY = f1;
      mActivePointerId = paramMotionEvent.getPointerId(0);
      if (bool)
      {
        ViewCompat.postInvalidateOnAnimation(this);
        return true;
      }
    }
    else
    {
      return false;
    }
    return true;
  }
  
  boolean pageLeft()
  {
    if (mCurItem > 0)
    {
      setCurrentItem(mCurItem - 1, true);
      return true;
    }
    return false;
  }
  
  boolean pageRight()
  {
    if ((mAdapter != null) && (mCurItem < mAdapter.getCount() - 1))
    {
      setCurrentItem(mCurItem + 1, true);
      return true;
    }
    return false;
  }
  
  void populate()
  {
    populate(mCurItem);
  }
  
  void populate(int paramInt)
  {
    Object localObject2;
    if (mCurItem != paramInt)
    {
      localObject2 = infoForPosition(mCurItem);
      mCurItem = paramInt;
    }
    else
    {
      localObject2 = null;
    }
    if (mAdapter == null)
    {
      sortChildDrawingOrder();
      return;
    }
    if (mPopulatePending)
    {
      sortChildDrawingOrder();
      return;
    }
    if (getWindowToken() == null) {
      return;
    }
    mAdapter.startUpdate(this);
    paramInt = mOffscreenPageLimit;
    int i2 = Math.max(0, mCurItem - paramInt);
    int n = mAdapter.getCount();
    int i1 = Math.min(n - 1, mCurItem + paramInt);
    Object localObject1;
    if (n == mExpectedAdapterCount)
    {
      int j = 0;
      while (j < mItems.size())
      {
        localObject1 = (ItemInfo)mItems.get(j);
        if (position >= mCurItem)
        {
          if (position != mCurItem) {
            break;
          }
          break label187;
        }
        j += 1;
      }
      localObject1 = null;
      label187:
      Object localObject3 = localObject1;
      if (localObject1 == null)
      {
        localObject3 = localObject1;
        if (n > 0) {
          localObject3 = addNewItem(mCurItem, j);
        }
      }
      if (localObject3 != null)
      {
        int k = j - 1;
        if (k >= 0) {
          localObject1 = (ItemInfo)mItems.get(k);
        } else {
          localObject1 = null;
        }
        int i3 = getClientWidth();
        float f3;
        if (i3 <= 0) {
          f3 = 0.0F;
        } else {
          f3 = 2.0F - widthFactor + getPaddingLeft() / i3;
        }
        int m = mCurItem - 1;
        float f2 = 0.0F;
        Object localObject4 = localObject1;
        while (m >= 0)
        {
          if ((f2 >= f3) && (m < i2))
          {
            if (localObject4 == null) {
              break;
            }
            paramInt = j;
            i = k;
            localObject1 = localObject4;
            f1 = f2;
            if (m != position) {
              break label585;
            }
            paramInt = j;
            i = k;
            localObject1 = localObject4;
            f1 = f2;
            if (scrolling) {
              break label585;
            }
            mItems.remove(k);
            mAdapter.destroyItem(this, m, object);
            k -= 1;
            j -= 1;
            paramInt = j;
            i = k;
            f1 = f2;
            if (k >= 0)
            {
              localObject1 = (ItemInfo)mItems.get(k);
              paramInt = j;
              i = k;
              f1 = f2;
              break label585;
            }
          }
          else if ((localObject4 != null) && (m == position))
          {
            f2 += widthFactor;
            k -= 1;
            paramInt = j;
            i = k;
            f1 = f2;
            if (k >= 0)
            {
              localObject1 = (ItemInfo)mItems.get(k);
              paramInt = j;
              i = k;
              f1 = f2;
              break label585;
            }
          }
          else
          {
            f2 += addNewItem1widthFactor;
            j += 1;
            paramInt = j;
            i = k;
            f1 = f2;
            if (k >= 0)
            {
              localObject1 = (ItemInfo)mItems.get(k);
              paramInt = j;
              i = k;
              f1 = f2;
              break label585;
            }
          }
          localObject1 = null;
          label585:
          m -= 1;
          j = paramInt;
          k = i;
          localObject4 = localObject1;
          f2 = f1;
        }
        float f1 = widthFactor;
        paramInt = j + 1;
        if (f1 < 2.0F)
        {
          if (paramInt < mItems.size()) {
            localObject1 = (ItemInfo)mItems.get(paramInt);
          } else {
            localObject1 = null;
          }
          if (i3 <= 0) {
            f3 = 0.0F;
          } else {
            f3 = getPaddingRight() / i3 + 2.0F;
          }
          i = mCurItem;
          for (;;)
          {
            k = i + 1;
            if (k >= n) {
              break;
            }
            if ((f1 >= f3) && (k > i1))
            {
              if (localObject1 == null) {
                break;
              }
              i = k;
              if (k != position) {
                continue;
              }
              i = k;
              if (scrolling) {
                continue;
              }
              mItems.remove(paramInt);
              mAdapter.destroyItem(this, k, object);
              i = paramInt;
              f2 = f1;
              if (paramInt < mItems.size())
              {
                localObject1 = (ItemInfo)mItems.get(paramInt);
                i = k;
                continue;
              }
            }
            label883:
            do
            {
              do
              {
                localObject1 = null;
                paramInt = i;
                i = k;
                f1 = f2;
                break;
                if ((localObject1 == null) || (k != position)) {
                  break label883;
                }
                f1 += widthFactor;
                paramInt += 1;
                i = paramInt;
                f2 = f1;
              } while (paramInt >= mItems.size());
              localObject1 = (ItemInfo)mItems.get(paramInt);
              i = k;
              break;
              localObject1 = addNewItem(k, paramInt);
              paramInt += 1;
              f1 += widthFactor;
              i = paramInt;
              f2 = f1;
            } while (paramInt >= mItems.size());
            localObject1 = (ItemInfo)mItems.get(paramInt);
            i = k;
          }
        }
        calculatePageOffsets((ItemInfo)localObject3, j, (ItemInfo)localObject2);
        mAdapter.setPrimaryItem(this, mCurItem, object);
      }
      mAdapter.finishUpdate(this);
      int i = getChildCount();
      paramInt = 0;
      while (paramInt < i)
      {
        localObject2 = getChildAt(paramInt);
        localObject1 = (LayoutParams)((View)localObject2).getLayoutParams();
        childIndex = paramInt;
        if ((!isDecor) && (widthFactor == 0.0F))
        {
          localObject2 = infoForChild((View)localObject2);
          if (localObject2 != null)
          {
            widthFactor = widthFactor;
            position = position;
          }
        }
        paramInt += 1;
      }
      sortChildDrawingOrder();
      if (hasFocus())
      {
        localObject1 = findFocus();
        if (localObject1 != null) {
          localObject1 = infoForAnyChild((View)localObject1);
        } else {
          localObject1 = null;
        }
        if ((localObject1 != null) && (position == mCurItem)) {
          return;
        }
        paramInt = 0;
        while (paramInt < getChildCount())
        {
          localObject1 = getChildAt(paramInt);
          localObject2 = infoForChild((View)localObject1);
          if ((localObject2 != null) && (position == mCurItem) && (((View)localObject1).requestFocus(2))) {
            return;
          }
          paramInt += 1;
        }
      }
    }
    else
    {
      try
      {
        localObject1 = getResources().getResourceName(getId());
      }
      catch (Resources.NotFoundException localNotFoundException)
      {
        for (;;) {}
      }
      localObject1 = Integer.toHexString(getId());
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("The application's PagerAdapter changed the adapter's contents without calling PagerAdapter#notifyDataSetChanged! Expected adapter item count: ");
      ((StringBuilder)localObject2).append(mExpectedAdapterCount);
      ((StringBuilder)localObject2).append(", found: ");
      ((StringBuilder)localObject2).append(n);
      ((StringBuilder)localObject2).append(" Pager id: ");
      ((StringBuilder)localObject2).append((String)localObject1);
      ((StringBuilder)localObject2).append(" Pager class: ");
      ((StringBuilder)localObject2).append(getClass());
      ((StringBuilder)localObject2).append(" Problematic adapter: ");
      ((StringBuilder)localObject2).append(mAdapter.getClass());
      throw new IllegalStateException(((StringBuilder)localObject2).toString());
    }
  }
  
  public void removeOnAdapterChangeListener(OnAdapterChangeListener paramOnAdapterChangeListener)
  {
    if (mAdapterChangeListeners != null) {
      mAdapterChangeListeners.remove(paramOnAdapterChangeListener);
    }
  }
  
  public void removeOnPageChangeListener(OnPageChangeListener paramOnPageChangeListener)
  {
    if (mOnPageChangeListeners != null) {
      mOnPageChangeListeners.remove(paramOnPageChangeListener);
    }
  }
  
  public void removeView(View paramView)
  {
    if (mInLayout)
    {
      removeViewInLayout(paramView);
      return;
    }
    super.removeView(paramView);
  }
  
  public void setAdapter(PagerAdapter paramPagerAdapter)
  {
    Object localObject = mAdapter;
    int j = 0;
    int i;
    if (localObject != null)
    {
      mAdapter.setViewPagerObserver(null);
      mAdapter.startUpdate(this);
      i = 0;
      while (i < mItems.size())
      {
        localObject = (ItemInfo)mItems.get(i);
        mAdapter.destroyItem(this, position, object);
        i += 1;
      }
      mAdapter.finishUpdate(this);
      mItems.clear();
      removeNonDecorViews();
      mCurItem = 0;
      scrollTo(0, 0);
    }
    localObject = mAdapter;
    mAdapter = paramPagerAdapter;
    mExpectedAdapterCount = 0;
    if (mAdapter != null)
    {
      if (mObserver == null) {
        mObserver = new PagerObserver();
      }
      mAdapter.setViewPagerObserver(mObserver);
      mPopulatePending = false;
      boolean bool = mFirstLayout;
      mFirstLayout = true;
      mExpectedAdapterCount = mAdapter.getCount();
      if (mRestoredCurItem >= 0)
      {
        mAdapter.restoreState(mRestoredAdapterState, mRestoredClassLoader);
        setCurrentItemInternal(mRestoredCurItem, false, true);
        mRestoredCurItem = -1;
        mRestoredAdapterState = null;
        mRestoredClassLoader = null;
      }
      else if (!bool)
      {
        populate();
      }
      else
      {
        requestLayout();
      }
    }
    if ((mAdapterChangeListeners != null) && (!mAdapterChangeListeners.isEmpty()))
    {
      int k = mAdapterChangeListeners.size();
      i = j;
      while (i < k)
      {
        ((OnAdapterChangeListener)mAdapterChangeListeners.get(i)).onAdapterChanged(this, (PagerAdapter)localObject, paramPagerAdapter);
        i += 1;
      }
    }
  }
  
  public void setCurrentItem(int paramInt)
  {
    mPopulatePending = false;
    setCurrentItemInternal(paramInt, mFirstLayout ^ true, false);
  }
  
  public void setCurrentItem(int paramInt, boolean paramBoolean)
  {
    mPopulatePending = false;
    setCurrentItemInternal(paramInt, paramBoolean, false);
  }
  
  void setCurrentItemInternal(int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    setCurrentItemInternal(paramInt, paramBoolean1, paramBoolean2, 0);
  }
  
  void setCurrentItemInternal(int paramInt1, boolean paramBoolean1, boolean paramBoolean2, int paramInt2)
  {
    if ((mAdapter != null) && (mAdapter.getCount() > 0))
    {
      if ((!paramBoolean2) && (mCurItem == paramInt1) && (mItems.size() != 0))
      {
        setScrollingCacheEnabled(false);
        return;
      }
      paramBoolean2 = true;
      int i;
      if (paramInt1 < 0)
      {
        i = 0;
      }
      else
      {
        i = paramInt1;
        if (paramInt1 >= mAdapter.getCount()) {
          i = mAdapter.getCount() - 1;
        }
      }
      paramInt1 = mOffscreenPageLimit;
      if ((i > mCurItem + paramInt1) || (i < mCurItem - paramInt1))
      {
        paramInt1 = 0;
        while (paramInt1 < mItems.size())
        {
          mItems.get(paramInt1)).scrolling = true;
          paramInt1 += 1;
        }
      }
      if (mCurItem == i) {
        paramBoolean2 = false;
      }
      if (mFirstLayout)
      {
        mCurItem = i;
        if (paramBoolean2) {
          dispatchOnPageSelected(i);
        }
        requestLayout();
        return;
      }
      populate(i);
      scrollToItem(i, paramBoolean1, paramInt2, paramBoolean2);
      return;
    }
    setScrollingCacheEnabled(false);
  }
  
  OnPageChangeListener setInternalPageChangeListener(OnPageChangeListener paramOnPageChangeListener)
  {
    OnPageChangeListener localOnPageChangeListener = mInternalPageChangeListener;
    mInternalPageChangeListener = paramOnPageChangeListener;
    return localOnPageChangeListener;
  }
  
  public void setOffscreenPageLimit(int paramInt)
  {
    int i = paramInt;
    if (paramInt < 1)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Requested offscreen page limit ");
      localStringBuilder.append(paramInt);
      localStringBuilder.append(" too small; defaulting to ");
      localStringBuilder.append(1);
      Log.w("ViewPager", localStringBuilder.toString());
      i = 1;
    }
    if (i != mOffscreenPageLimit)
    {
      mOffscreenPageLimit = i;
      populate();
    }
  }
  
  public void setOnPageChangeListener(OnPageChangeListener paramOnPageChangeListener)
  {
    mOnPageChangeListener = paramOnPageChangeListener;
  }
  
  public void setPageMargin(int paramInt)
  {
    int i = mPageMargin;
    mPageMargin = paramInt;
    int j = getWidth();
    recomputeScrollPosition(j, j, paramInt, i);
    requestLayout();
  }
  
  public void setPageMarginDrawable(int paramInt)
  {
    setPageMarginDrawable(ContextCompat.getDrawable(getContext(), paramInt));
  }
  
  public void setPageMarginDrawable(Drawable paramDrawable)
  {
    mMarginDrawable = paramDrawable;
    if (paramDrawable != null) {
      refreshDrawableState();
    }
    boolean bool;
    if (paramDrawable == null) {
      bool = true;
    } else {
      bool = false;
    }
    setWillNotDraw(bool);
    invalidate();
  }
  
  public void setPageTransformer(boolean paramBoolean, PageTransformer paramPageTransformer)
  {
    setPageTransformer(paramBoolean, paramPageTransformer, 2);
  }
  
  public void setPageTransformer(boolean paramBoolean, PageTransformer paramPageTransformer, int paramInt)
  {
    int j = 1;
    boolean bool1;
    if (paramPageTransformer != null) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    boolean bool2;
    if (mPageTransformer != null) {
      bool2 = true;
    } else {
      bool2 = false;
    }
    int i;
    if (bool1 != bool2) {
      i = 1;
    } else {
      i = 0;
    }
    mPageTransformer = paramPageTransformer;
    setChildrenDrawingOrderEnabled(bool1);
    if (bool1)
    {
      if (paramBoolean) {
        j = 2;
      }
      mDrawingOrder = j;
      mPageTransformerLayerType = paramInt;
    }
    else
    {
      mDrawingOrder = 0;
    }
    if (i != 0) {
      populate();
    }
  }
  
  void setScrollState(int paramInt)
  {
    if (mScrollState == paramInt) {
      return;
    }
    mScrollState = paramInt;
    if (mPageTransformer != null)
    {
      boolean bool;
      if (paramInt != 0) {
        bool = true;
      } else {
        bool = false;
      }
      enableLayers(bool);
    }
    dispatchOnScrollStateChanged(paramInt);
  }
  
  void smoothScrollTo(int paramInt1, int paramInt2)
  {
    smoothScrollTo(paramInt1, paramInt2, 0);
  }
  
  void smoothScrollTo(int paramInt1, int paramInt2, int paramInt3)
  {
    if (getChildCount() == 0)
    {
      setScrollingCacheEnabled(false);
      return;
    }
    int i;
    if ((mScroller != null) && (!mScroller.isFinished())) {
      i = 1;
    } else {
      i = 0;
    }
    if (i != 0)
    {
      if (mIsScrollStarted) {
        i = mScroller.getCurrX();
      } else {
        i = mScroller.getStartX();
      }
      mScroller.abortAnimation();
      setScrollingCacheEnabled(false);
    }
    else
    {
      i = getScrollX();
    }
    int j = getScrollY();
    int k = paramInt1 - i;
    paramInt2 -= j;
    if ((k == 0) && (paramInt2 == 0))
    {
      completeScroll(false);
      populate();
      setScrollState(0);
      return;
    }
    setScrollingCacheEnabled(true);
    setScrollState(2);
    paramInt1 = getClientWidth();
    int m = paramInt1 / 2;
    float f2 = Math.abs(k);
    float f1 = paramInt1;
    float f3 = Math.min(1.0F, f2 * 1.0F / f1);
    f2 = m;
    f3 = distanceInfluenceForSnapDuration(f3);
    paramInt1 = Math.abs(paramInt3);
    if (paramInt1 > 0)
    {
      paramInt1 = Math.round(Math.abs((f2 + f3 * f2) / paramInt1) * 1000.0F) * 4;
    }
    else
    {
      f2 = mAdapter.getPageWidth(mCurItem);
      paramInt1 = (int)((Math.abs(k) / (f1 * f2 + mPageMargin) + 1.0F) * 100.0F);
    }
    paramInt1 = Math.min(paramInt1, 600);
    mIsScrollStarted = false;
    mScroller.startScroll(i, j, k, paramInt2, paramInt1);
    ViewCompat.postInvalidateOnAnimation(this);
  }
  
  protected boolean verifyDrawable(Drawable paramDrawable)
  {
    return (super.verifyDrawable(paramDrawable)) || (paramDrawable == mMarginDrawable);
  }
  
  @Inherited
  @Retention(RetentionPolicy.RUNTIME)
  @Target({java.lang.annotation.ElementType.TYPE})
  public static @interface DecorView {}
  
  static class ItemInfo
  {
    Object object;
    float offset;
    int position;
    boolean scrolling;
    float widthFactor;
    
    ItemInfo() {}
  }
  
  public static class LayoutParams
    extends ViewGroup.LayoutParams
  {
    int childIndex;
    public int gravity;
    public boolean isDecor;
    boolean needsMeasure;
    int position;
    float widthFactor = 0.0F;
    
    public LayoutParams()
    {
      super(-1);
    }
    
    public LayoutParams(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
      paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, ViewPager.LAYOUT_ATTRS);
      gravity = paramContext.getInteger(0, 48);
      paramContext.recycle();
    }
  }
  
  class MyAccessibilityDelegate
    extends AccessibilityDelegateCompat
  {
    MyAccessibilityDelegate() {}
    
    private boolean canScroll()
    {
      return (mAdapter != null) && (mAdapter.getCount() > 1);
    }
    
    public void onInitializeAccessibilityEvent(View paramView, AccessibilityEvent paramAccessibilityEvent)
    {
      super.onInitializeAccessibilityEvent(paramView, paramAccessibilityEvent);
      paramAccessibilityEvent.setClassName(ViewPager.class.getName());
      paramAccessibilityEvent.setScrollable(canScroll());
      if ((paramAccessibilityEvent.getEventType() == 4096) && (mAdapter != null))
      {
        paramAccessibilityEvent.setItemCount(mAdapter.getCount());
        paramAccessibilityEvent.setFromIndex(mCurItem);
        paramAccessibilityEvent.setToIndex(mCurItem);
      }
    }
    
    public void onInitializeAccessibilityNodeInfo(View paramView, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat)
    {
      super.onInitializeAccessibilityNodeInfo(paramView, paramAccessibilityNodeInfoCompat);
      paramAccessibilityNodeInfoCompat.setClassName(ViewPager.class.getName());
      paramAccessibilityNodeInfoCompat.setScrollable(canScroll());
      if (canScrollHorizontally(1)) {
        paramAccessibilityNodeInfoCompat.addAction(4096);
      }
      if (canScrollHorizontally(-1)) {
        paramAccessibilityNodeInfoCompat.addAction(8192);
      }
    }
    
    public boolean performAccessibilityAction(View paramView, int paramInt, Bundle paramBundle)
    {
      if (super.performAccessibilityAction(paramView, paramInt, paramBundle)) {
        return true;
      }
      if (paramInt != 4096)
      {
        if (paramInt != 8192) {
          return false;
        }
        if (canScrollHorizontally(-1))
        {
          setCurrentItem(mCurItem - 1);
          return true;
        }
        return false;
      }
      if (canScrollHorizontally(1))
      {
        setCurrentItem(mCurItem + 1);
        return true;
      }
      return false;
    }
  }
  
  public static abstract interface OnAdapterChangeListener
  {
    public abstract void onAdapterChanged(ViewPager paramViewPager, PagerAdapter paramPagerAdapter1, PagerAdapter paramPagerAdapter2);
  }
  
  public static abstract interface OnPageChangeListener
  {
    public abstract void onPageScrollStateChanged(int paramInt);
    
    public abstract void onPageScrolled(int paramInt1, float paramFloat, int paramInt2);
    
    public abstract void onPageSelected(int paramInt);
  }
  
  public static abstract interface PageTransformer
  {
    public abstract void transformPage(View paramView, float paramFloat);
  }
  
  private class PagerObserver
    extends DataSetObserver
  {
    PagerObserver() {}
    
    public void onChanged()
    {
      dataSetChanged();
    }
    
    public void onInvalidated()
    {
      dataSetChanged();
    }
  }
  
  public static class SavedState
    extends AbsSavedState
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator()
    {
      public ViewPager.SavedState createFromParcel(Parcel paramAnonymousParcel)
      {
        return new ViewPager.SavedState(paramAnonymousParcel, null);
      }
      
      public ViewPager.SavedState createFromParcel(Parcel paramAnonymousParcel, ClassLoader paramAnonymousClassLoader)
      {
        return new ViewPager.SavedState(paramAnonymousParcel, paramAnonymousClassLoader);
      }
      
      public ViewPager.SavedState[] newArray(int paramAnonymousInt)
      {
        return new ViewPager.SavedState[paramAnonymousInt];
      }
    };
    Parcelable adapterState;
    ClassLoader loader;
    int position;
    
    SavedState(Parcel paramParcel, ClassLoader paramClassLoader)
    {
      super(paramClassLoader);
      ClassLoader localClassLoader = paramClassLoader;
      if (paramClassLoader == null) {
        localClassLoader = getClass().getClassLoader();
      }
      position = paramParcel.readInt();
      adapterState = paramParcel.readParcelable(localClassLoader);
      loader = localClassLoader;
    }
    
    public SavedState(Parcelable paramParcelable)
    {
      super();
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("FragmentPager.SavedState{");
      localStringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
      localStringBuilder.append(" position=");
      localStringBuilder.append(position);
      localStringBuilder.append("}");
      return localStringBuilder.toString();
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeInt(position);
      paramParcel.writeParcelable(adapterState, paramInt);
    }
  }
  
  public static class SimpleOnPageChangeListener
    implements ViewPager.OnPageChangeListener
  {
    public SimpleOnPageChangeListener() {}
    
    public void onPageScrollStateChanged(int paramInt) {}
    
    public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2) {}
    
    public void onPageSelected(int paramInt) {}
  }
  
  static class ViewPositionComparator
    implements Comparator<View>
  {
    ViewPositionComparator() {}
    
    public int compare(View paramView1, View paramView2)
    {
      paramView1 = (ViewPager.LayoutParams)paramView1.getLayoutParams();
      paramView2 = (ViewPager.LayoutParams)paramView2.getLayoutParams();
      if (isDecor != isDecor)
      {
        if (isDecor) {
          return 1;
        }
        return -1;
      }
      return position - position;
    }
  }
}
