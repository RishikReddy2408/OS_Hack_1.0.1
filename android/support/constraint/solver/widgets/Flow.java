package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;
import java.util.ArrayList;

public class Flow
  extends HelperWidget
{
  public static final int BOTTOM = 3;
  public static final int HINT_CONTEXT_CAR_HOME = 2;
  public static final int LEFT = 0;
  public static final int RIGHT = 1;
  private int mBarrierType = 0;
  
  public Flow() {}
  
  private void createChain(ConstraintWidget[] paramArrayOfConstraintWidget, int paramInt1, int paramInt2) {}
  
  public void addToSolver(LinearSystem paramLinearSystem)
  {
    mListAnchors[0] = mLeft;
    mListAnchors[2] = mTop;
    mListAnchors[1] = mRight;
    mListAnchors[3] = mBottom;
    int i = mParent.getWidth();
    Object localObject1 = mParent.mLeft;
    paramLinearSystem = mParent;
    int m = (int)(i * 0.8F);
    ConstraintAnchor localConstraintAnchor = mTop;
    paramLinearSystem = null;
    int j = 0;
    i = 0;
    while (j < mWidgetsCount)
    {
      ConstraintWidget localConstraintWidget = mWidgets[j];
      Object localObject2 = localObject1;
      localObject1 = paramLinearSystem;
      int k = i;
      if (localConstraintWidget.getWidth() + i > m)
      {
        localObject2 = mParent.mLeft;
        if (paramLinearSystem != null) {
          mRight.connect(mParent.mRight, 0);
        }
        localConstraintAnchor = mBottom;
        localObject1 = null;
        k = 0;
      }
      if (localObject1 != null)
      {
        mRight.connect(mLeft, 40);
      }
      else
      {
        localConstraintWidget.setHorizontalChainStyle(2);
        localConstraintWidget.setHorizontalBiasPercent(0.0F);
      }
      mTop.connect(localConstraintAnchor, 40);
      mLeft.connect((ConstraintAnchor)localObject2, 0);
      i = k + localConstraintWidget.getWidth();
      localObject1 = mRight;
      j += 1;
      paramLinearSystem = localConstraintWidget;
    }
    if (paramLinearSystem != null) {
      mRight.connect(mParent.mRight, 0);
    }
  }
  
  public void setBarrierType(int paramInt)
  {
    mBarrierType = paramInt;
  }
  
  class Linear
  {
    ConstraintWidget mOwner;
    ArrayList<ConstraintWidget> mWidgets = new ArrayList();
    
    Linear() {}
    
    public void apply() {}
  }
}
