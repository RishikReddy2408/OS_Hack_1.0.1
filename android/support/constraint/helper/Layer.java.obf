package android.support.constraint.helper;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.constraint.ConstraintHelper;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintLayout.LayoutParams;
import android.support.constraint.solver.widgets.ConstraintWidget;
import android.util.AttributeSet;
import android.view.View;

public class Layer
  extends ConstraintHelper
{
  private static final String TAG = "Layer";
  protected float mComputedCenterX = NaN.0F;
  protected float mComputedCenterY = NaN.0F;
  protected float mComputedMaxX = NaN.0F;
  protected float mComputedMaxY = NaN.0F;
  protected float mComputedMinX = NaN.0F;
  protected float mComputedMinY = NaN.0F;
  ConstraintLayout mContainer;
  private float mGroupRotateAngle = NaN.0F;
  boolean mNeedBounds = true;
  private float mRotationCenterX = NaN.0F;
  private float mRotationCenterY = NaN.0F;
  private float mScaleX = 1.0F;
  private float mScaleY = 1.0F;
  private float mShiftX = 0.0F;
  private float mShiftY = 0.0F;
  View[] mViews = null;
  
  public Layer(Context paramContext)
  {
    super(paramContext);
  }
  
  public Layer(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public Layer(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  private void reCacheViews()
  {
    if (mContainer == null) {
      return;
    }
    if (mCount == 0) {
      return;
    }
    if ((mViews == null) || (mViews.length != mCount)) {
      mViews = new View[mCount];
    }
    int i = 0;
    while (i < mCount)
    {
      int j = mIds[i];
      mViews[i] = mContainer.getViewById(j);
      i += 1;
    }
  }
  
  private void transform()
  {
    if (mContainer == null) {
      return;
    }
    if (mViews == null) {
      reCacheViews();
    }
    calcCenters();
    double d = Math.toRadians(mGroupRotateAngle);
    float f1 = (float)Math.sin(d);
    float f2 = (float)Math.cos(d);
    float f3 = mScaleX;
    float f4 = -mScaleY;
    float f5 = mScaleX;
    float f6 = mScaleY;
    int i = 0;
    while (i < mCount)
    {
      View localView = mViews[i];
      int j = (localView.getLeft() + localView.getRight()) / 2;
      int k = (localView.getTop() + localView.getBottom()) / 2;
      float f7 = j - mComputedCenterX;
      float f8 = k - mComputedCenterY;
      float f9 = mShiftX;
      float f10 = mShiftY;
      localView.setTranslationX(f3 * f2 * f7 + f4 * f1 * f8 - f7 + f9);
      localView.setTranslationY(f7 * (f5 * f1) + f6 * f2 * f8 - f8 + f10);
      localView.setScaleY(mScaleY);
      localView.setScaleX(mScaleX);
      localView.setRotation(mGroupRotateAngle);
      i += 1;
    }
  }
  
  protected void calcCenters()
  {
    if (mContainer == null) {
      return;
    }
    if ((!mNeedBounds) && (!Float.isNaN(mComputedCenterX)) && (!Float.isNaN(mComputedCenterY))) {
      return;
    }
    if ((!Float.isNaN(mRotationCenterX)) && (!Float.isNaN(mRotationCenterY)))
    {
      mComputedCenterY = mRotationCenterY;
      mComputedCenterX = mRotationCenterX;
      return;
    }
    View[] arrayOfView = getViews(mContainer);
    int m = 0;
    int n = arrayOfView[0].getLeft();
    int k = arrayOfView[0].getTop();
    int j = arrayOfView[0].getRight();
    int i = arrayOfView[0].getBottom();
    while (m < mCount)
    {
      View localView = arrayOfView[m];
      n = Math.min(n, localView.getLeft());
      k = Math.min(k, localView.getTop());
      j = Math.max(j, localView.getRight());
      i = Math.max(i, localView.getBottom());
      m += 1;
    }
    mComputedMaxX = j;
    mComputedMaxY = i;
    mComputedMinX = n;
    mComputedMinY = k;
    if (Float.isNaN(mRotationCenterX)) {
      mComputedCenterX = ((n + j) / 2);
    } else {
      mComputedCenterX = mRotationCenterX;
    }
    if (Float.isNaN(mRotationCenterY))
    {
      mComputedCenterY = ((k + i) / 2);
      return;
    }
    mComputedCenterY = mRotationCenterY;
  }
  
  protected void init(AttributeSet paramAttributeSet)
  {
    super.init(paramAttributeSet);
    mUseViewMeasure = false;
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    mContainer = ((ConstraintLayout)getParent());
  }
  
  public void setPivotX(float paramFloat)
  {
    mRotationCenterX = paramFloat;
    transform();
  }
  
  public void setPivotY(float paramFloat)
  {
    mRotationCenterY = paramFloat;
    transform();
  }
  
  public void setRotation(float paramFloat)
  {
    mGroupRotateAngle = paramFloat;
    transform();
  }
  
  public void setScaleX(float paramFloat)
  {
    mScaleX = paramFloat;
    transform();
  }
  
  public void setScaleY(float paramFloat)
  {
    mScaleY = paramFloat;
    transform();
  }
  
  public void setTranslationX(float paramFloat)
  {
    mShiftX = paramFloat;
    transform();
  }
  
  public void setTranslationY(float paramFloat)
  {
    mShiftY = paramFloat;
    transform();
  }
  
  public void updatePostLayout(ConstraintLayout paramConstraintLayout)
  {
    reCacheViews();
    mComputedCenterX = NaN.0F;
    mComputedCenterY = NaN.0F;
    paramConstraintLayout = ((ConstraintLayout.LayoutParams)getLayoutParams()).getConstraintWidget();
    paramConstraintLayout.setWidth(0);
    paramConstraintLayout.setHeight(0);
    calcCenters();
    layout((int)mComputedMinX - getPaddingLeft(), (int)mComputedMinY - getPaddingTop(), (int)mComputedMaxX + getPaddingRight(), (int)mComputedMaxY + getPaddingBottom());
    if (!Float.isNaN(mGroupRotateAngle)) {
      transform();
    }
  }
  
  public void updatePreLayout(ConstraintLayout paramConstraintLayout)
  {
    mContainer = paramConstraintLayout;
    int j = getVisibility();
    float f = getRotation();
    if (f == 0.0F)
    {
      if (!Float.isNaN(mGroupRotateAngle)) {
        mGroupRotateAngle = f;
      }
    }
    else {
      mGroupRotateAngle = f;
    }
    if (Build.VERSION.SDK_INT >= 21) {
      f = getElevation();
    } else {
      f = 0.0F;
    }
    int i = 0;
    while (i < mCount)
    {
      View localView = paramConstraintLayout.getViewById(mIds[i]);
      if (localView != null)
      {
        localView.setVisibility(j);
        if ((f > 0.0F) && (Build.VERSION.SDK_INT >= 21)) {
          localView.setElevation(f);
        }
      }
      i += 1;
    }
  }
}
