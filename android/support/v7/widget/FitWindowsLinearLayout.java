package android.support.v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.RestrictTo;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class FitWindowsLinearLayout
  extends LinearLayout
  implements FitWindowsViewGroup
{
  private FitWindowsViewGroup.OnFitSystemWindowsListener mListener;
  
  public FitWindowsLinearLayout(Context paramContext)
  {
    super(paramContext);
  }
  
  public FitWindowsLinearLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  protected boolean fitSystemWindows(Rect paramRect)
  {
    if (mListener != null) {
      mListener.onFitSystemWindows(paramRect);
    }
    return super.fitSystemWindows(paramRect);
  }
  
  public void setOnFitSystemWindowsListener(FitWindowsViewGroup.OnFitSystemWindowsListener paramOnFitSystemWindowsListener)
  {
    mListener = paramOnFitSystemWindowsListener;
  }
}
