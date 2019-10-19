package android.support.constraint;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.constraint.solver.widgets.ConstraintWidget;
import android.util.AttributeSet;
import android.view.View;

public class Group
  extends ConstraintHelper
{
  public Group(Context paramContext)
  {
    super(paramContext);
  }
  
  public Group(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public Group(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  protected void init(AttributeSet paramAttributeSet)
  {
    super.init(paramAttributeSet);
    mUseViewMeasure = false;
  }
  
  public void updatePostLayout(ConstraintLayout paramConstraintLayout)
  {
    paramConstraintLayout = (ConstraintLayout.LayoutParams)getLayoutParams();
    widget.setWidth(0);
    widget.setHeight(0);
  }
  
  public void updatePreLayout(ConstraintLayout paramConstraintLayout)
  {
    int j = getVisibility();
    float f;
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
