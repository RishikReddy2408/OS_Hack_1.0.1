package android.support.v4.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

public class PagerTabStrip
  extends PagerTitleStrip
{
  private static final int FULL_UNDERLINE_HEIGHT = 1;
  private static final int INDICATOR_HEIGHT = 3;
  private static final int MIN_PADDING_BOTTOM = 6;
  private static final int MIN_STRIP_HEIGHT = 32;
  private static final int MIN_TEXT_SPACING = 64;
  private static final int TAB_PADDING = 16;
  private static final int TAB_SPACING = 32;
  private static final String TAG = "PagerTabStrip";
  private boolean mDrawFullUnderline = false;
  private boolean mDrawFullUnderlineSet = false;
  private int mFullUnderlineHeight;
  private boolean mIgnoreTap;
  private int mIndicatorColor = mTextColor;
  private int mIndicatorHeight;
  private float mInitialMotionX;
  private float mInitialMotionY;
  private int mMinPaddingBottom;
  private int mMinStripHeight;
  private int mMinTextSpacing;
  private int mTabAlpha = 255;
  private int mTabPadding;
  private final Paint mTabPaint = new Paint();
  private final Rect mTempRect = new Rect();
  private int mTouchSlop;
  
  public PagerTabStrip(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public PagerTabStrip(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    mTabPaint.setColor(mIndicatorColor);
    float f = getResourcesgetDisplayMetricsdensity;
    mIndicatorHeight = ((int)(3.0F * f + 0.5F));
    mMinPaddingBottom = ((int)(6.0F * f + 0.5F));
    mMinTextSpacing = ((int)(64.0F * f));
    mTabPadding = ((int)(16.0F * f + 0.5F));
    mFullUnderlineHeight = ((int)(1.0F * f + 0.5F));
    mMinStripHeight = ((int)(f * 32.0F + 0.5F));
    mTouchSlop = ViewConfiguration.get(paramContext).getScaledTouchSlop();
    setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
    setTextSpacing(getTextSpacing());
    setWillNotDraw(false);
    mPrevText.setFocusable(true);
    mPrevText.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        mPager.setCurrentItem(mPager.getCurrentItem() - 1);
      }
    });
    mNextText.setFocusable(true);
    mNextText.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        mPager.setCurrentItem(mPager.getCurrentItem() + 1);
      }
    });
    if (getBackground() == null) {
      mDrawFullUnderline = true;
    }
  }
  
  public boolean getDrawFullUnderline()
  {
    return mDrawFullUnderline;
  }
  
  int getMinHeight()
  {
    return Math.max(super.getMinHeight(), mMinStripHeight);
  }
  
  public int getTabIndicatorColor()
  {
    return mIndicatorColor;
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    int i = getHeight();
    int j = mCurrText.getLeft();
    int k = mTabPadding;
    int m = mCurrText.getRight();
    int n = mTabPadding;
    int i1 = mIndicatorHeight;
    mTabPaint.setColor(mTabAlpha << 24 | mIndicatorColor & 0xFFFFFF);
    float f1 = j - k;
    float f2 = i - i1;
    float f3 = m + n;
    float f4 = i;
    paramCanvas.drawRect(f1, f2, f3, f4, mTabPaint);
    if (mDrawFullUnderline)
    {
      mTabPaint.setColor(0xFF000000 | mIndicatorColor & 0xFFFFFF);
      paramCanvas.drawRect(getPaddingLeft(), i - mFullUnderlineHeight, getWidth() - getPaddingRight(), f4, mTabPaint);
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getAction();
    if ((i != 0) && (mIgnoreTap)) {
      return false;
    }
    float f1 = paramMotionEvent.getX();
    float f2 = paramMotionEvent.getY();
    switch (i)
    {
    default: 
      return true;
    case 2: 
      if ((Math.abs(f1 - mInitialMotionX) > mTouchSlop) || (Math.abs(f2 - mInitialMotionY) > mTouchSlop))
      {
        mIgnoreTap = true;
        return true;
      }
      break;
    case 1: 
      if (f1 < mCurrText.getLeft() - mTabPadding)
      {
        mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        return true;
      }
      if (f1 > mCurrText.getRight() + mTabPadding)
      {
        mPager.setCurrentItem(mPager.getCurrentItem() + 1);
        return true;
      }
      break;
    case 0: 
      mInitialMotionX = f1;
      mInitialMotionY = f2;
      mIgnoreTap = false;
    }
    return true;
  }
  
  public void setBackgroundColor(int paramInt)
  {
    super.setBackgroundColor(paramInt);
    if (!mDrawFullUnderlineSet)
    {
      boolean bool;
      if ((paramInt & 0xFF000000) == 0) {
        bool = true;
      } else {
        bool = false;
      }
      mDrawFullUnderline = bool;
    }
  }
  
  public void setBackgroundDrawable(Drawable paramDrawable)
  {
    super.setBackgroundDrawable(paramDrawable);
    if (!mDrawFullUnderlineSet)
    {
      boolean bool;
      if (paramDrawable == null) {
        bool = true;
      } else {
        bool = false;
      }
      mDrawFullUnderline = bool;
    }
  }
  
  public void setBackgroundResource(int paramInt)
  {
    super.setBackgroundResource(paramInt);
    if (!mDrawFullUnderlineSet)
    {
      boolean bool;
      if (paramInt == 0) {
        bool = true;
      } else {
        bool = false;
      }
      mDrawFullUnderline = bool;
    }
  }
  
  public void setDrawFullUnderline(boolean paramBoolean)
  {
    mDrawFullUnderline = paramBoolean;
    mDrawFullUnderlineSet = true;
    invalidate();
  }
  
  public void setPadding(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = paramInt4;
    if (paramInt4 < mMinPaddingBottom) {
      i = mMinPaddingBottom;
    }
    super.setPadding(paramInt1, paramInt2, paramInt3, i);
  }
  
  public void setTabIndicatorColor(int paramInt)
  {
    mIndicatorColor = paramInt;
    mTabPaint.setColor(mIndicatorColor);
    invalidate();
  }
  
  public void setTabIndicatorColorResource(int paramInt)
  {
    setTabIndicatorColor(ContextCompat.getColor(getContext(), paramInt));
  }
  
  public void setTextSpacing(int paramInt)
  {
    int i = paramInt;
    if (paramInt < mMinTextSpacing) {
      i = mMinTextSpacing;
    }
    super.setTextSpacing(i);
  }
  
  void updateTextPositions(int paramInt, float paramFloat, boolean paramBoolean)
  {
    Rect localRect = mTempRect;
    int i = getHeight();
    int j = mCurrText.getLeft();
    int k = mTabPadding;
    int m = mCurrText.getRight();
    int n = mTabPadding;
    int i1 = i - mIndicatorHeight;
    localRect.set(j - k, i1, m + n, i);
    super.updateTextPositions(paramInt, paramFloat, paramBoolean);
    mTabAlpha = ((int)(Math.abs(paramFloat - 0.5F) * 2.0F * 255.0F));
    localRect.union(mCurrText.getLeft() - mTabPadding, i1, mCurrText.getRight() + mTabPadding, i);
    invalidate(localRect);
  }
}
