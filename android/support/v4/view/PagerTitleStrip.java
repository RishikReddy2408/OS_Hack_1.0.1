package android.support.v4.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.TextViewCompat;
import android.text.TextUtils.TruncateAt;
import android.text.method.ReplacementTransformationMethod;
import android.text.method.SingleLineTransformationMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.Locale;

@ViewPager.DecorView
public class PagerTitleStrip
  extends ViewGroup
{
  private static final int[] ATTRS = { 16842804, 16842901, 16842904, 16842927 };
  private static final float SIDE_ALPHA = 0.6F;
  private static final int[] TEXT_ATTRS = { 16843660 };
  private static final int TEXT_SPACING = 16;
  TextView mCurrText;
  private int mGravity;
  private int mLastKnownCurrentPage = -1;
  float mLastKnownPositionOffset = -1.0F;
  TextView mNextText;
  private int mNonPrimaryAlpha;
  private final PageListener mPageListener = new PageListener();
  ViewPager mPager;
  TextView mPrevText;
  private int mScaledTextSpacing;
  int mTextColor;
  private boolean mUpdatingPositions;
  private boolean mUpdatingText;
  private WeakReference<PagerAdapter> mWatchingAdapter;
  
  public PagerTitleStrip(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public PagerTitleStrip(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    TextView localTextView = new TextView(paramContext);
    mPrevText = localTextView;
    addView(localTextView);
    localTextView = new TextView(paramContext);
    mCurrText = localTextView;
    addView(localTextView);
    localTextView = new TextView(paramContext);
    mNextText = localTextView;
    addView(localTextView);
    paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, ATTRS);
    boolean bool = false;
    int i = paramAttributeSet.getResourceId(0, 0);
    if (i != 0)
    {
      TextViewCompat.setTextAppearance(mPrevText, i);
      TextViewCompat.setTextAppearance(mCurrText, i);
      TextViewCompat.setTextAppearance(mNextText, i);
    }
    int j = paramAttributeSet.getDimensionPixelSize(1, 0);
    if (j != 0) {
      setTextSize(0, j);
    }
    if (paramAttributeSet.hasValue(2))
    {
      j = paramAttributeSet.getColor(2, 0);
      mPrevText.setTextColor(j);
      mCurrText.setTextColor(j);
      mNextText.setTextColor(j);
    }
    mGravity = paramAttributeSet.getInteger(3, 80);
    paramAttributeSet.recycle();
    mTextColor = mCurrText.getTextColors().getDefaultColor();
    setNonPrimaryAlpha(0.6F);
    mPrevText.setEllipsize(TextUtils.TruncateAt.END);
    mCurrText.setEllipsize(TextUtils.TruncateAt.END);
    mNextText.setEllipsize(TextUtils.TruncateAt.END);
    if (i != 0)
    {
      paramAttributeSet = paramContext.obtainStyledAttributes(i, TEXT_ATTRS);
      bool = paramAttributeSet.getBoolean(0, false);
      paramAttributeSet.recycle();
    }
    if (bool)
    {
      setSingleLineAllCaps(mPrevText);
      setSingleLineAllCaps(mCurrText);
      setSingleLineAllCaps(mNextText);
    }
    else
    {
      mPrevText.setSingleLine();
      mCurrText.setSingleLine();
      mNextText.setSingleLine();
    }
    mScaledTextSpacing = ((int)(getResourcesgetDisplayMetricsdensity * 16.0F));
  }
  
  private static void setSingleLineAllCaps(TextView paramTextView)
  {
    paramTextView.setTransformationMethod(new SingleLineAllCapsTransform(paramTextView.getContext()));
  }
  
  int getMinHeight()
  {
    Drawable localDrawable = getBackground();
    if (localDrawable != null) {
      return localDrawable.getIntrinsicHeight();
    }
    return 0;
  }
  
  public int getTextSpacing()
  {
    return mScaledTextSpacing;
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    Object localObject = getParent();
    if ((localObject instanceof ViewPager))
    {
      localObject = (ViewPager)localObject;
      PagerAdapter localPagerAdapter = ((ViewPager)localObject).getAdapter();
      ((ViewPager)localObject).setInternalPageChangeListener(mPageListener);
      ((ViewPager)localObject).addOnAdapterChangeListener(mPageListener);
      mPager = ((ViewPager)localObject);
      if (mWatchingAdapter != null) {
        localObject = (PagerAdapter)mWatchingAdapter.get();
      } else {
        localObject = null;
      }
      updateAdapter((PagerAdapter)localObject, localPagerAdapter);
      return;
    }
    throw new IllegalStateException("PagerTitleStrip must be a direct child of a ViewPager.");
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    if (mPager != null)
    {
      updateAdapter(mPager.getAdapter(), null);
      mPager.setInternalPageChangeListener(null);
      mPager.removeOnAdapterChangeListener(mPageListener);
      mPager = null;
    }
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (mPager != null)
    {
      float f2 = mLastKnownPositionOffset;
      float f1 = 0.0F;
      if (f2 >= 0.0F) {
        f1 = mLastKnownPositionOffset;
      }
      updateTextPositions(mLastKnownCurrentPage, f1, true);
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    if (View.MeasureSpec.getMode(paramInt1) == 1073741824)
    {
      int j = getPaddingTop() + getPaddingBottom();
      int k = ViewGroup.getChildMeasureSpec(paramInt2, j, -2);
      int i = View.MeasureSpec.getSize(paramInt1);
      paramInt1 = ViewGroup.getChildMeasureSpec(paramInt1, (int)(i * 0.2F), -2);
      mPrevText.measure(paramInt1, k);
      mCurrText.measure(paramInt1, k);
      mNextText.measure(paramInt1, k);
      if (View.MeasureSpec.getMode(paramInt2) == 1073741824)
      {
        paramInt1 = View.MeasureSpec.getSize(paramInt2);
      }
      else
      {
        paramInt1 = mCurrText.getMeasuredHeight();
        paramInt1 = Math.max(getMinHeight(), paramInt1 + j);
      }
      setMeasuredDimension(i, View.resolveSizeAndState(paramInt1, paramInt2, mCurrText.getMeasuredState() << 16));
      return;
    }
    throw new IllegalStateException("Must measure with an exact width");
  }
  
  public void requestLayout()
  {
    if (!mUpdatingText) {
      super.requestLayout();
    }
  }
  
  public void setGravity(int paramInt)
  {
    mGravity = paramInt;
    requestLayout();
  }
  
  public void setNonPrimaryAlpha(float paramFloat)
  {
    mNonPrimaryAlpha = ((int)(paramFloat * 255.0F) & 0xFF);
    int i = mNonPrimaryAlpha << 24 | mTextColor & 0xFFFFFF;
    mPrevText.setTextColor(i);
    mNextText.setTextColor(i);
  }
  
  public void setTextColor(int paramInt)
  {
    mTextColor = paramInt;
    mCurrText.setTextColor(paramInt);
    paramInt = mNonPrimaryAlpha << 24 | mTextColor & 0xFFFFFF;
    mPrevText.setTextColor(paramInt);
    mNextText.setTextColor(paramInt);
  }
  
  public void setTextSize(int paramInt, float paramFloat)
  {
    mPrevText.setTextSize(paramInt, paramFloat);
    mCurrText.setTextSize(paramInt, paramFloat);
    mNextText.setTextSize(paramInt, paramFloat);
  }
  
  public void setTextSpacing(int paramInt)
  {
    mScaledTextSpacing = paramInt;
    requestLayout();
  }
  
  void updateAdapter(PagerAdapter paramPagerAdapter1, PagerAdapter paramPagerAdapter2)
  {
    if (paramPagerAdapter1 != null)
    {
      paramPagerAdapter1.unregisterDataSetObserver(mPageListener);
      mWatchingAdapter = null;
    }
    if (paramPagerAdapter2 != null)
    {
      paramPagerAdapter2.registerDataSetObserver(mPageListener);
      mWatchingAdapter = new WeakReference(paramPagerAdapter2);
    }
    if (mPager != null)
    {
      mLastKnownCurrentPage = -1;
      mLastKnownPositionOffset = -1.0F;
      updateText(mPager.getCurrentItem(), paramPagerAdapter2);
      requestLayout();
    }
  }
  
  void updateText(int paramInt, PagerAdapter paramPagerAdapter)
  {
    if (paramPagerAdapter != null) {
      i = paramPagerAdapter.getCount();
    } else {
      i = 0;
    }
    mUpdatingText = true;
    Object localObject2 = null;
    if ((paramInt >= 1) && (paramPagerAdapter != null)) {
      localObject1 = paramPagerAdapter.getPageTitle(paramInt - 1);
    } else {
      localObject1 = null;
    }
    mPrevText.setText((CharSequence)localObject1);
    TextView localTextView = mCurrText;
    if ((paramPagerAdapter != null) && (paramInt < i)) {
      localObject1 = paramPagerAdapter.getPageTitle(paramInt);
    } else {
      localObject1 = null;
    }
    localTextView.setText((CharSequence)localObject1);
    int j = paramInt + 1;
    Object localObject1 = localObject2;
    if (j < i)
    {
      localObject1 = localObject2;
      if (paramPagerAdapter != null) {
        localObject1 = paramPagerAdapter.getPageTitle(j);
      }
    }
    mNextText.setText((CharSequence)localObject1);
    int i = View.MeasureSpec.makeMeasureSpec(Math.max(0, (int)((getWidth() - getPaddingLeft() - getPaddingRight()) * 0.8F)), Integer.MIN_VALUE);
    j = View.MeasureSpec.makeMeasureSpec(Math.max(0, getHeight() - getPaddingTop() - getPaddingBottom()), Integer.MIN_VALUE);
    mPrevText.measure(i, j);
    mCurrText.measure(i, j);
    mNextText.measure(i, j);
    mLastKnownCurrentPage = paramInt;
    if (!mUpdatingPositions) {
      updateTextPositions(paramInt, mLastKnownPositionOffset, false);
    }
    mUpdatingText = false;
  }
  
  void updateTextPositions(int paramInt, float paramFloat, boolean paramBoolean)
  {
    if (paramInt != mLastKnownCurrentPage) {
      updateText(paramInt, mPager.getAdapter());
    } else if ((!paramBoolean) && (paramFloat == mLastKnownPositionOffset)) {
      return;
    }
    mUpdatingPositions = true;
    int m = mPrevText.getMeasuredWidth();
    int i4 = mCurrText.getMeasuredWidth();
    int k = mNextText.getMeasuredWidth();
    int i3 = i4 / 2;
    int n = getWidth();
    int i = getHeight();
    int i2 = getPaddingLeft();
    int i1 = getPaddingRight();
    paramInt = getPaddingTop();
    int j = getPaddingBottom();
    int i5 = i1 + i3;
    float f2 = 0.5F + paramFloat;
    float f1 = f2;
    if (f2 > 1.0F) {
      f1 = f2 - 1.0F;
    }
    i3 = n - i5 - (int)((n - (i2 + i3) - i5) * f1) - i3;
    i4 += i3;
    int i7 = mPrevText.getBaseline();
    int i6 = mCurrText.getBaseline();
    i5 = mNextText.getBaseline();
    int i8 = Math.max(Math.max(i7, i6), i5);
    i7 = i8 - i7;
    i6 = i8 - i6;
    i5 = i8 - i5;
    i8 = mPrevText.getMeasuredHeight();
    int i9 = mCurrText.getMeasuredHeight();
    int i10 = mNextText.getMeasuredHeight();
    i8 = Math.max(Math.max(i8 + i7, i9 + i6), i10 + i5);
    i9 = mGravity & 0x70;
    if (i9 != 16)
    {
      if (i9 != 80)
      {
        j = i7 + paramInt;
        i = i6 + paramInt;
        paramInt += i5;
      }
      else
      {
        paramInt = i - j - i8;
        j = i7 + paramInt;
        i = i6 + paramInt;
        paramInt += i5;
      }
    }
    else
    {
      paramInt = (i - paramInt - j - i8) / 2;
      j = i7 + paramInt;
      i = i6 + paramInt;
      paramInt += i5;
    }
    mCurrText.layout(i3, i, i4, mCurrText.getMeasuredHeight() + i);
    i = Math.min(i2, i3 - mScaledTextSpacing - m);
    mPrevText.layout(i, j, m + i, mPrevText.getMeasuredHeight() + j);
    i = Math.max(n - i1 - k, i4 + mScaledTextSpacing);
    mNextText.layout(i, paramInt, i + k, mNextText.getMeasuredHeight() + paramInt);
    mLastKnownPositionOffset = paramFloat;
    mUpdatingPositions = false;
  }
  
  private class PageListener
    extends DataSetObserver
    implements ViewPager.OnPageChangeListener, ViewPager.OnAdapterChangeListener
  {
    private int mScrollState;
    
    PageListener() {}
    
    public void onAdapterChanged(ViewPager paramViewPager, PagerAdapter paramPagerAdapter1, PagerAdapter paramPagerAdapter2)
    {
      updateAdapter(paramPagerAdapter1, paramPagerAdapter2);
    }
    
    public void onChanged()
    {
      updateText(mPager.getCurrentItem(), mPager.getAdapter());
      float f2 = mLastKnownPositionOffset;
      float f1 = 0.0F;
      if (f2 >= 0.0F) {
        f1 = mLastKnownPositionOffset;
      }
      updateTextPositions(mPager.getCurrentItem(), f1, true);
    }
    
    public void onPageScrollStateChanged(int paramInt)
    {
      mScrollState = paramInt;
    }
    
    public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
    {
      paramInt2 = paramInt1;
      if (paramFloat > 0.5F) {
        paramInt2 = paramInt1 + 1;
      }
      updateTextPositions(paramInt2, paramFloat, false);
    }
    
    public void onPageSelected(int paramInt)
    {
      if (mScrollState == 0)
      {
        updateText(mPager.getCurrentItem(), mPager.getAdapter());
        float f2 = mLastKnownPositionOffset;
        float f1 = 0.0F;
        if (f2 >= 0.0F) {
          f1 = mLastKnownPositionOffset;
        }
        updateTextPositions(mPager.getCurrentItem(), f1, true);
      }
    }
  }
  
  private static class SingleLineAllCapsTransform
    extends SingleLineTransformationMethod
  {
    private Locale mLocale;
    
    SingleLineAllCapsTransform(Context paramContext)
    {
      mLocale = getResourcesgetConfigurationlocale;
    }
    
    public CharSequence getTransformation(CharSequence paramCharSequence, View paramView)
    {
      paramCharSequence = super.getTransformation(paramCharSequence, paramView);
      if (paramCharSequence != null) {
        return paramCharSequence.toString().toUpperCase(mLocale);
      }
      return null;
    }
  }
}
