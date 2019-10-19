package android.support.constraint.motion;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintHelper;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.R.styleable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class MotionHelper
  extends ConstraintHelper
  implements Animatable
{
  private float mProgress;
  private boolean mUseOnHide = false;
  private boolean mUseOnShow = false;
  protected View[] views;
  
  public MotionHelper(Context paramContext)
  {
    super(paramContext);
  }
  
  public MotionHelper(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramAttributeSet);
  }
  
  public MotionHelper(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramAttributeSet);
  }
  
  public float getProgress()
  {
    return mProgress;
  }
  
  protected void init(AttributeSet paramAttributeSet)
  {
    if (paramAttributeSet != null)
    {
      paramAttributeSet = getContext().obtainStyledAttributes(paramAttributeSet, R.styleable.MotionHelper);
      int j = paramAttributeSet.getIndexCount();
      int i = 0;
      while (i < j)
      {
        int k = paramAttributeSet.getIndex(i);
        if (k == R.styleable.MotionHelper_onShow) {
          mUseOnShow = paramAttributeSet.getBoolean(k, mUseOnShow);
        } else if (k == R.styleable.MotionHelper_onHide) {
          mUseOnHide = paramAttributeSet.getBoolean(k, mUseOnHide);
        }
        i += 1;
      }
    }
  }
  
  public boolean isUseOnHide()
  {
    return mUseOnHide;
  }
  
  public boolean isUsedOnShow()
  {
    return mUseOnShow;
  }
  
  public void setProgress(float paramFloat)
  {
    mProgress = paramFloat;
    int k = mCount;
    int i = 0;
    int j = 0;
    if (k > 0)
    {
      views = getViews((ConstraintLayout)getParent());
      i = j;
      while (i < mCount)
      {
        setProgress(views[i], paramFloat);
        i += 1;
      }
    }
    ViewGroup localViewGroup = (ViewGroup)getParent();
    j = localViewGroup.getChildCount();
    while (i < j)
    {
      View localView = localViewGroup.getChildAt(i);
      if (!(localView instanceof MotionHelper)) {
        setProgress(localView, paramFloat);
      }
      i += 1;
    }
  }
  
  public void setProgress(View paramView, float paramFloat) {}
}
