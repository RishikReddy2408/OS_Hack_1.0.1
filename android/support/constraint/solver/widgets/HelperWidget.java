package android.support.constraint.solver.widgets;

import java.util.Arrays;

public class HelperWidget
  extends ConstraintWidget
  implements Helper
{
  protected ConstraintWidget[] mWidgets = new ConstraintWidget[4];
  protected int mWidgetsCount = 0;
  
  public HelperWidget() {}
  
  public void multiply(ConstraintWidget paramConstraintWidget)
  {
    if (mWidgetsCount + 1 > mWidgets.length) {
      mWidgets = ((ConstraintWidget[])Arrays.copyOf(mWidgets, mWidgets.length * 2));
    }
    mWidgets[mWidgetsCount] = paramConstraintWidget;
    mWidgetsCount += 1;
  }
  
  public void removeAllIds()
  {
    mWidgetsCount = 0;
  }
}
