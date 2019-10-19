package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.annotation.RestrictTo;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
public class ContentFrameLayout
  extends FrameLayout
{
  private OnAttachListener mAttachListener;
  private final Rect mDecorPadding = new Rect();
  private TypedValue mFixedHeightMajor;
  private TypedValue mFixedHeightMinor;
  private TypedValue mFixedWidthMajor;
  private TypedValue mFixedWidthMinor;
  private TypedValue mMinWidthMajor;
  private TypedValue mMinWidthMinor;
  
  public ContentFrameLayout(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public ContentFrameLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public ContentFrameLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public void dispatchFitSystemWindows(Rect paramRect)
  {
    fitSystemWindows(paramRect);
  }
  
  public TypedValue getFixedHeightMajor()
  {
    if (mFixedHeightMajor == null) {
      mFixedHeightMajor = new TypedValue();
    }
    return mFixedHeightMajor;
  }
  
  public TypedValue getFixedHeightMinor()
  {
    if (mFixedHeightMinor == null) {
      mFixedHeightMinor = new TypedValue();
    }
    return mFixedHeightMinor;
  }
  
  public TypedValue getFixedWidthMajor()
  {
    if (mFixedWidthMajor == null) {
      mFixedWidthMajor = new TypedValue();
    }
    return mFixedWidthMajor;
  }
  
  public TypedValue getFixedWidthMinor()
  {
    if (mFixedWidthMinor == null) {
      mFixedWidthMinor = new TypedValue();
    }
    return mFixedWidthMinor;
  }
  
  public TypedValue getMinWidthMajor()
  {
    if (mMinWidthMajor == null) {
      mMinWidthMajor = new TypedValue();
    }
    return mMinWidthMajor;
  }
  
  public TypedValue getMinWidthMinor()
  {
    if (mMinWidthMinor == null) {
      mMinWidthMinor = new TypedValue();
    }
    return mMinWidthMinor;
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    if (mAttachListener != null) {
      mAttachListener.onAttachedFromWindow();
    }
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    if (mAttachListener != null) {
      mAttachListener.onDetachedFromWindow();
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    DisplayMetrics localDisplayMetrics = getContext().getResources().getDisplayMetrics();
    int i = widthPixels;
    int j = heightPixels;
    int n = 1;
    if (i < j) {
      j = 1;
    } else {
      j = 0;
    }
    int i1 = View.MeasureSpec.getMode(paramInt1);
    int i2 = View.MeasureSpec.getMode(paramInt2);
    TypedValue localTypedValue;
    if (i1 == Integer.MIN_VALUE)
    {
      if (j != 0) {
        localTypedValue = mFixedWidthMinor;
      } else {
        localTypedValue = mFixedWidthMajor;
      }
      if ((localTypedValue != null) && (type != 0))
      {
        if (type == 5) {
          i = (int)localTypedValue.getDimension(localDisplayMetrics);
        } else if (type == 6) {
          i = (int)localTypedValue.getFraction(widthPixels, widthPixels);
        } else {
          i = 0;
        }
        if (i > 0)
        {
          m = View.MeasureSpec.makeMeasureSpec(Math.min(i - (mDecorPadding.left + mDecorPadding.right), View.MeasureSpec.getSize(paramInt1)), 1073741824);
          k = 1;
          break label197;
        }
      }
    }
    int k = 0;
    int m = paramInt1;
    label197:
    i = paramInt2;
    if (i2 == Integer.MIN_VALUE)
    {
      if (j != 0) {
        localTypedValue = mFixedHeightMajor;
      } else {
        localTypedValue = mFixedHeightMinor;
      }
      i = paramInt2;
      if (localTypedValue != null)
      {
        i = paramInt2;
        if (type != 0)
        {
          if (type == 5) {
            paramInt1 = (int)localTypedValue.getDimension(localDisplayMetrics);
          } else if (type == 6) {
            paramInt1 = (int)localTypedValue.getFraction(heightPixels, heightPixels);
          } else {
            paramInt1 = 0;
          }
          i = paramInt2;
          if (paramInt1 > 0) {
            i = View.MeasureSpec.makeMeasureSpec(Math.min(paramInt1 - (mDecorPadding.top + mDecorPadding.bottom), View.MeasureSpec.getSize(paramInt2)), 1073741824);
          }
        }
      }
    }
    super.onMeasure(m, i);
    i2 = getMeasuredWidth();
    m = View.MeasureSpec.makeMeasureSpec(i2, 1073741824);
    if ((k == 0) && (i1 == Integer.MIN_VALUE))
    {
      if (j != 0) {
        localTypedValue = mMinWidthMinor;
      } else {
        localTypedValue = mMinWidthMajor;
      }
      if ((localTypedValue != null) && (type != 0))
      {
        if (type == 5) {
          paramInt1 = (int)localTypedValue.getDimension(localDisplayMetrics);
        } else if (type == 6) {
          paramInt1 = (int)localTypedValue.getFraction(widthPixels, widthPixels);
        } else {
          paramInt1 = 0;
        }
        paramInt2 = paramInt1;
        if (paramInt1 > 0) {
          paramInt2 = paramInt1 - (mDecorPadding.left + mDecorPadding.right);
        }
        if (i2 < paramInt2)
        {
          paramInt2 = View.MeasureSpec.makeMeasureSpec(paramInt2, 1073741824);
          paramInt1 = n;
          break label504;
        }
      }
    }
    paramInt1 = 0;
    paramInt2 = m;
    label504:
    if (paramInt1 != 0) {
      super.onMeasure(paramInt2, i);
    }
  }
  
  public void setAttachListener(OnAttachListener paramOnAttachListener)
  {
    mAttachListener = paramOnAttachListener;
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public void setDecorPadding(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    mDecorPadding.set(paramInt1, paramInt2, paramInt3, paramInt4);
    if (ViewCompat.isLaidOut(this)) {
      requestLayout();
    }
  }
  
  public static abstract interface OnAttachListener
  {
    public abstract void onAttachedFromWindow();
    
    public abstract void onDetachedFromWindow();
  }
}
