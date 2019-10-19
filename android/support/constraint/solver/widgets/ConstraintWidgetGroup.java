package android.support.constraint.solver.widgets;

import java.util.HashSet;
import java.util.List;

public class ConstraintWidgetGroup
{
  public List<ConstraintWidget> mConstrainedGroup;
  public final int[] mGroupDimensions = { mGroupWidth, mGroupHeight };
  int mGroupHeight = -1;
  int mGroupWidth = -1;
  HashSet<ConstraintWidget> mStartHorizontalWidgets = new HashSet();
  HashSet<ConstraintWidget> mStartVerticalWidgets = new HashSet();
  HashSet<ConstraintWidget> mWidgetsToSetHorizontal = new HashSet();
  HashSet<ConstraintWidget> mWidgetsToSetVertical = new HashSet();
  
  ConstraintWidgetGroup(List paramList)
  {
    mConstrainedGroup = paramList;
  }
  
  public HashSet getStartWidgets(int paramInt)
  {
    if (paramInt == 0) {
      return mStartHorizontalWidgets;
    }
    if (paramInt == 1) {
      return mStartVerticalWidgets;
    }
    return null;
  }
  
  public HashSet getWidgetsToSet(int paramInt)
  {
    if (paramInt == 0) {
      return mWidgetsToSetHorizontal;
    }
    if (paramInt == 1) {
      return mWidgetsToSetVertical;
    }
    return null;
  }
}
