package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.Metrics;

public class Optimizer
{
  static final int FLAG_CHAIN_DANGLING = 1;
  static final int FLAG_RECOMPUTE_BOUNDS = 2;
  static final int FLAG_USE_OPTIMIZE = 0;
  public static final int OPTIMIZATION_BARRIER = 2;
  public static final int OPTIMIZATION_CHAIN = 4;
  public static final int OPTIMIZATION_DIMENSIONS = 8;
  public static final int OPTIMIZATION_DIRECT = 1;
  public static final int OPTIMIZATION_GROUPS = 32;
  public static final int OPTIMIZATION_NONE = 0;
  public static final int OPTIMIZATION_RATIO = 16;
  public static final int OPTIMIZATION_STANDARD = 3;
  static boolean[] flags = new boolean[3];
  
  public Optimizer() {}
  
  static void analyze(int paramInt, ConstraintWidget paramConstraintWidget)
  {
    paramConstraintWidget.updateResolutionNodes();
    ResolutionAnchor localResolutionAnchor1 = mLeft.getResolutionNode();
    ResolutionAnchor localResolutionAnchor2 = mTop.getResolutionNode();
    ResolutionAnchor localResolutionAnchor3 = mRight.getResolutionNode();
    ResolutionAnchor localResolutionAnchor4 = mBottom.getResolutionNode();
    if ((paramInt & 0x8) == 8) {
      paramInt = 1;
    } else {
      paramInt = 0;
    }
    int i;
    if ((mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) && (optimizableMatchConstraint(paramConstraintWidget, 0))) {
      i = 1;
    } else {
      i = 0;
    }
    if ((type != 4) && (type != 4)) {
      if ((mListDimensionBehaviors[0] != ConstraintWidget.DimensionBehaviour.FIXED) && ((i == 0) || (paramConstraintWidget.getVisibility() != 8)))
      {
        if (i != 0)
        {
          i = paramConstraintWidget.getWidth();
          localResolutionAnchor1.setType(1);
          localResolutionAnchor3.setType(1);
          if ((mLeft.mTarget == null) && (mRight.mTarget == null))
          {
            if (paramInt != 0) {
              localResolutionAnchor3.dependsOn(localResolutionAnchor1, 1, paramConstraintWidget.getResolutionWidth());
            } else {
              localResolutionAnchor3.dependsOn(localResolutionAnchor1, i);
            }
          }
          else if ((mLeft.mTarget != null) && (mRight.mTarget == null))
          {
            if (paramInt != 0) {
              localResolutionAnchor3.dependsOn(localResolutionAnchor1, 1, paramConstraintWidget.getResolutionWidth());
            } else {
              localResolutionAnchor3.dependsOn(localResolutionAnchor1, i);
            }
          }
          else if ((mLeft.mTarget == null) && (mRight.mTarget != null))
          {
            if (paramInt != 0) {
              localResolutionAnchor1.dependsOn(localResolutionAnchor3, -1, paramConstraintWidget.getResolutionWidth());
            } else {
              localResolutionAnchor1.dependsOn(localResolutionAnchor3, -i);
            }
          }
          else if ((mLeft.mTarget != null) && (mRight.mTarget != null))
          {
            if (paramInt != 0)
            {
              paramConstraintWidget.getResolutionWidth().addDependent(localResolutionAnchor1);
              paramConstraintWidget.getResolutionWidth().addDependent(localResolutionAnchor3);
            }
            if (mDimensionRatio == 0.0F)
            {
              localResolutionAnchor1.setType(3);
              localResolutionAnchor3.setType(3);
              localResolutionAnchor1.setOpposite(localResolutionAnchor3, 0.0F);
              localResolutionAnchor3.setOpposite(localResolutionAnchor1, 0.0F);
            }
            else
            {
              localResolutionAnchor1.setType(2);
              localResolutionAnchor3.setType(2);
              localResolutionAnchor1.setOpposite(localResolutionAnchor3, -i);
              localResolutionAnchor3.setOpposite(localResolutionAnchor1, i);
              paramConstraintWidget.setWidth(i);
            }
          }
        }
      }
      else if ((mLeft.mTarget == null) && (mRight.mTarget == null))
      {
        localResolutionAnchor1.setType(1);
        localResolutionAnchor3.setType(1);
        if (paramInt != 0) {
          localResolutionAnchor3.dependsOn(localResolutionAnchor1, 1, paramConstraintWidget.getResolutionWidth());
        } else {
          localResolutionAnchor3.dependsOn(localResolutionAnchor1, paramConstraintWidget.getWidth());
        }
      }
      else if ((mLeft.mTarget != null) && (mRight.mTarget == null))
      {
        localResolutionAnchor1.setType(1);
        localResolutionAnchor3.setType(1);
        if (paramInt != 0) {
          localResolutionAnchor3.dependsOn(localResolutionAnchor1, 1, paramConstraintWidget.getResolutionWidth());
        } else {
          localResolutionAnchor3.dependsOn(localResolutionAnchor1, paramConstraintWidget.getWidth());
        }
      }
      else if ((mLeft.mTarget == null) && (mRight.mTarget != null))
      {
        localResolutionAnchor1.setType(1);
        localResolutionAnchor3.setType(1);
        localResolutionAnchor1.dependsOn(localResolutionAnchor3, -paramConstraintWidget.getWidth());
        if (paramInt != 0) {
          localResolutionAnchor1.dependsOn(localResolutionAnchor3, -1, paramConstraintWidget.getResolutionWidth());
        } else {
          localResolutionAnchor1.dependsOn(localResolutionAnchor3, -paramConstraintWidget.getWidth());
        }
      }
      else if ((mLeft.mTarget != null) && (mRight.mTarget != null))
      {
        localResolutionAnchor1.setType(2);
        localResolutionAnchor3.setType(2);
        if (paramInt != 0)
        {
          paramConstraintWidget.getResolutionWidth().addDependent(localResolutionAnchor1);
          paramConstraintWidget.getResolutionWidth().addDependent(localResolutionAnchor3);
          localResolutionAnchor1.setOpposite(localResolutionAnchor3, -1, paramConstraintWidget.getResolutionWidth());
          localResolutionAnchor3.setOpposite(localResolutionAnchor1, 1, paramConstraintWidget.getResolutionWidth());
        }
        else
        {
          localResolutionAnchor1.setOpposite(localResolutionAnchor3, -paramConstraintWidget.getWidth());
          localResolutionAnchor3.setOpposite(localResolutionAnchor1, paramConstraintWidget.getWidth());
        }
      }
    }
    if ((mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) && (optimizableMatchConstraint(paramConstraintWidget, 1))) {
      i = 1;
    } else {
      i = 0;
    }
    if ((type != 4) && (type != 4)) {
      if ((mListDimensionBehaviors[1] != ConstraintWidget.DimensionBehaviour.FIXED) && ((i == 0) || (paramConstraintWidget.getVisibility() != 8)))
      {
        if (i != 0)
        {
          i = paramConstraintWidget.getHeight();
          localResolutionAnchor2.setType(1);
          localResolutionAnchor4.setType(1);
          if ((mTop.mTarget == null) && (mBottom.mTarget == null))
          {
            if (paramInt != 0)
            {
              localResolutionAnchor4.dependsOn(localResolutionAnchor2, 1, paramConstraintWidget.getResolutionHeight());
              return;
            }
            localResolutionAnchor4.dependsOn(localResolutionAnchor2, i);
            return;
          }
          if ((mTop.mTarget != null) && (mBottom.mTarget == null))
          {
            if (paramInt != 0)
            {
              localResolutionAnchor4.dependsOn(localResolutionAnchor2, 1, paramConstraintWidget.getResolutionHeight());
              return;
            }
            localResolutionAnchor4.dependsOn(localResolutionAnchor2, i);
            return;
          }
          if ((mTop.mTarget == null) && (mBottom.mTarget != null))
          {
            if (paramInt != 0)
            {
              localResolutionAnchor2.dependsOn(localResolutionAnchor4, -1, paramConstraintWidget.getResolutionHeight());
              return;
            }
            localResolutionAnchor2.dependsOn(localResolutionAnchor4, -i);
            return;
          }
          if ((mTop.mTarget != null) && (mBottom.mTarget != null))
          {
            if (paramInt != 0)
            {
              paramConstraintWidget.getResolutionHeight().addDependent(localResolutionAnchor2);
              paramConstraintWidget.getResolutionWidth().addDependent(localResolutionAnchor4);
            }
            if (mDimensionRatio == 0.0F)
            {
              localResolutionAnchor2.setType(3);
              localResolutionAnchor4.setType(3);
              localResolutionAnchor2.setOpposite(localResolutionAnchor4, 0.0F);
              localResolutionAnchor4.setOpposite(localResolutionAnchor2, 0.0F);
              return;
            }
            localResolutionAnchor2.setType(2);
            localResolutionAnchor4.setType(2);
            localResolutionAnchor2.setOpposite(localResolutionAnchor4, -i);
            localResolutionAnchor4.setOpposite(localResolutionAnchor2, i);
            paramConstraintWidget.setHeight(i);
            if (mBaselineDistance > 0) {
              mBaseline.getResolutionNode().dependsOn(1, localResolutionAnchor2, mBaselineDistance);
            }
          }
        }
      }
      else if ((mTop.mTarget == null) && (mBottom.mTarget == null))
      {
        localResolutionAnchor2.setType(1);
        localResolutionAnchor4.setType(1);
        if (paramInt != 0) {
          localResolutionAnchor4.dependsOn(localResolutionAnchor2, 1, paramConstraintWidget.getResolutionHeight());
        } else {
          localResolutionAnchor4.dependsOn(localResolutionAnchor2, paramConstraintWidget.getHeight());
        }
        if (mBaseline.mTarget != null)
        {
          mBaseline.getResolutionNode().setType(1);
          localResolutionAnchor2.dependsOn(1, mBaseline.getResolutionNode(), -mBaselineDistance);
        }
      }
      else if ((mTop.mTarget != null) && (mBottom.mTarget == null))
      {
        localResolutionAnchor2.setType(1);
        localResolutionAnchor4.setType(1);
        if (paramInt != 0) {
          localResolutionAnchor4.dependsOn(localResolutionAnchor2, 1, paramConstraintWidget.getResolutionHeight());
        } else {
          localResolutionAnchor4.dependsOn(localResolutionAnchor2, paramConstraintWidget.getHeight());
        }
        if (mBaselineDistance > 0) {
          mBaseline.getResolutionNode().dependsOn(1, localResolutionAnchor2, mBaselineDistance);
        }
      }
      else if ((mTop.mTarget == null) && (mBottom.mTarget != null))
      {
        localResolutionAnchor2.setType(1);
        localResolutionAnchor4.setType(1);
        if (paramInt != 0) {
          localResolutionAnchor2.dependsOn(localResolutionAnchor4, -1, paramConstraintWidget.getResolutionHeight());
        } else {
          localResolutionAnchor2.dependsOn(localResolutionAnchor4, -paramConstraintWidget.getHeight());
        }
        if (mBaselineDistance > 0) {
          mBaseline.getResolutionNode().dependsOn(1, localResolutionAnchor2, mBaselineDistance);
        }
      }
      else if ((mTop.mTarget != null) && (mBottom.mTarget != null))
      {
        localResolutionAnchor2.setType(2);
        localResolutionAnchor4.setType(2);
        if (paramInt != 0)
        {
          localResolutionAnchor2.setOpposite(localResolutionAnchor4, -1, paramConstraintWidget.getResolutionHeight());
          localResolutionAnchor4.setOpposite(localResolutionAnchor2, 1, paramConstraintWidget.getResolutionHeight());
          paramConstraintWidget.getResolutionHeight().addDependent(localResolutionAnchor2);
          paramConstraintWidget.getResolutionWidth().addDependent(localResolutionAnchor4);
        }
        else
        {
          localResolutionAnchor2.setOpposite(localResolutionAnchor4, -paramConstraintWidget.getHeight());
          localResolutionAnchor4.setOpposite(localResolutionAnchor2, paramConstraintWidget.getHeight());
        }
        if (mBaselineDistance > 0) {
          mBaseline.getResolutionNode().dependsOn(1, localResolutionAnchor2, mBaselineDistance);
        }
      }
    }
  }
  
  static boolean applyChainOptimized(ConstraintWidgetContainer paramConstraintWidgetContainer, LinearSystem paramLinearSystem, int paramInt1, int paramInt2, ChainHead paramChainHead)
  {
    Object localObject2 = mFirst;
    ConstraintWidget localConstraintWidget2 = mLast;
    Object localObject1 = mFirstVisibleWidget;
    ConstraintWidget localConstraintWidget1 = mLastVisibleWidget;
    Object localObject3 = mHead;
    float f6 = mTotalWeight;
    ConstraintWidget localConstraintWidget3 = mFirstMatchConstraintWidget;
    paramChainHead = mLastMatchConstraintWidget;
    paramConstraintWidgetContainer = mListDimensionBehaviors[paramInt1];
    paramConstraintWidgetContainer = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
    int i;
    int j;
    int k;
    if (paramInt1 == 0)
    {
      if (mHorizontalChainStyle == 0) {
        i = 1;
      } else {
        i = 0;
      }
      if (mHorizontalChainStyle == 1) {
        j = 1;
      } else {
        j = 0;
      }
      m = i;
      k = j;
      if (mHorizontalChainStyle != 2) {}
    }
    for (;;)
    {
      k = 1;
      break;
      do
      {
        n = 0;
        i = m;
        j = k;
        k = n;
        break;
        if (mVerticalChainStyle == 0) {
          i = 1;
        } else {
          i = 0;
        }
        if (mVerticalChainStyle == 1) {
          j = 1;
        } else {
          j = 0;
        }
        m = i;
        k = j;
      } while (mVerticalChainStyle != 2);
    }
    paramChainHead = (ChainHead)localObject2;
    int i1 = 0;
    int n = 0;
    int m = 0;
    float f4 = 0.0F;
    float f3 = 0.0F;
    float f1;
    float f2;
    while (m == 0)
    {
      int i2 = i1;
      f1 = f4;
      f2 = f3;
      if (paramChainHead.getVisibility() != 8)
      {
        i2 = i1 + 1;
        if (paramInt1 == 0) {
          f2 = f4 + paramChainHead.getWidth();
        } else {
          f2 = f4 + paramChainHead.getHeight();
        }
        f1 = f2;
        if (paramChainHead != localObject1) {
          f1 = f2 + mListAnchors[paramInt2].getMargin();
        }
        f2 = f3 + mListAnchors[paramInt2].getMargin() + mListAnchors[(paramInt2 + 1)].getMargin();
      }
      paramConstraintWidgetContainer = mListAnchors[paramInt2];
      i1 = n;
      if (paramChainHead.getVisibility() != 8)
      {
        i1 = n;
        if (mListDimensionBehaviors[paramInt1] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT)
        {
          i1 = n + 1;
          if (paramInt1 == 0)
          {
            if (mMatchConstraintDefaultWidth != 0) {
              return false;
            }
            if (mMatchConstraintMinWidth != 0) {
              break label1757;
            }
            if (mMatchConstraintMaxWidth != 0) {
              return false;
            }
          }
          else
          {
            if (mMatchConstraintDefaultHeight != 0) {
              return false;
            }
            if (mMatchConstraintMinHeight != 0) {
              break label1759;
            }
            if (mMatchConstraintMaxHeight != 0) {
              return false;
            }
          }
        }
      }
      paramConstraintWidgetContainer = mListAnchors[(paramInt2 + 1)].mTarget;
      if (paramConstraintWidgetContainer != null)
      {
        paramConstraintWidgetContainer = mOwner;
        if ((mListAnchors[paramInt2].mTarget != null) && (mListAnchors[paramInt2].mTarget.mOwner == paramChainHead)) {
          break label504;
        }
      }
      paramConstraintWidgetContainer = null;
      label504:
      if (paramConstraintWidgetContainer != null)
      {
        paramChainHead = paramConstraintWidgetContainer;
        n = i1;
        i1 = i2;
        f4 = f1;
        f3 = f2;
      }
      else
      {
        m = 1;
        n = i1;
        i1 = i2;
        f4 = f1;
        f3 = f2;
      }
    }
    localObject3 = mListAnchors[paramInt2].getResolutionNode();
    paramConstraintWidgetContainer = mListAnchors;
    m = paramInt2 + 1;
    paramConstraintWidgetContainer = paramConstraintWidgetContainer[m].getResolutionNode();
    if ((target != null) && (target != null))
    {
      if ((target.state != 1) && (target.state != 1)) {
        return false;
      }
      if ((n > 0) && (n != i1)) {
        return false;
      }
      if ((k == 0) && (i == 0) && (j == 0))
      {
        f1 = 0.0F;
      }
      else
      {
        if (localObject1 != null) {
          f2 = mListAnchors[paramInt2].getMargin();
        } else {
          f2 = 0.0F;
        }
        f1 = f2;
        if (localConstraintWidget1 != null) {
          f1 = f2 + mListAnchors[m].getMargin();
        }
      }
      float f5 = target.resolvedOffset;
      f2 = target.resolvedOffset;
      if (f5 < f2) {
        f2 = f2 - f5 - f4;
      } else {
        f2 = f5 - f2 - f4;
      }
      if ((n > 0) && (n == i1))
      {
        if ((paramChainHead.getParent() != null) && (getParentmListDimensionBehaviors[paramInt1] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)) {
          return false;
        }
        f4 = f2 + f4 - f3;
        f2 = f4;
        if (i != 0) {
          f2 = f4 - (f3 - f1);
        }
        paramConstraintWidgetContainer = (ConstraintWidgetContainer)localObject1;
        f1 = f5;
        if (i != 0)
        {
          f3 = f5 + mListAnchors[m].getMargin();
          paramChainHead = mListNextVisibleWidget[paramInt1];
          paramConstraintWidgetContainer = (ConstraintWidgetContainer)localObject1;
          f1 = f3;
          if (paramChainHead != null) {
            f1 = f3 + mListAnchors[paramInt2].getMargin();
          }
        }
        for (paramConstraintWidgetContainer = (ConstraintWidgetContainer)localObject1; paramConstraintWidgetContainer != null; paramConstraintWidgetContainer = paramChainHead)
        {
          if (LinearSystem.sMetrics != null)
          {
            paramChainHead = LinearSystem.sMetrics;
            nonresolvedWidgets -= 1L;
            paramChainHead = LinearSystem.sMetrics;
            resolvedWidgets += 1L;
            paramChainHead = LinearSystem.sMetrics;
            chainConnectionResolved += 1L;
          }
          paramChainHead = mListNextVisibleWidget[paramInt1];
          if ((paramChainHead == null) && (paramConstraintWidgetContainer != localConstraintWidget1)) {
            continue;
          }
          f3 = f2 / n;
          if (f6 > 0.0F) {
            f3 = mWeight[paramInt1] * f2 / f6;
          }
          f1 += mListAnchors[paramInt2].getMargin();
          mListAnchors[paramInt2].getResolutionNode().resolve(resolvedTarget, f1);
          localObject1 = mListAnchors[m].getResolutionNode();
          localObject2 = resolvedTarget;
          f1 += f3;
          ((ResolutionAnchor)localObject1).resolve((ResolutionAnchor)localObject2, f1);
          mListAnchors[paramInt2].getResolutionNode().addResolvedValue(paramLinearSystem);
          mListAnchors[m].getResolutionNode().addResolvedValue(paramLinearSystem);
          f1 += mListAnchors[m].getMargin();
        }
        return true;
      }
      if (f2 < f4) {
        return false;
      }
      if (k != 0) {
        for (f1 = f5 + (f2 - f1) * ((ConstraintWidget)localObject2).getHorizontalBiasPercent(); localObject1 != null; f1 = f2)
        {
          if (LinearSystem.sMetrics != null)
          {
            paramConstraintWidgetContainer = LinearSystem.sMetrics;
            nonresolvedWidgets -= 1L;
            paramConstraintWidgetContainer = LinearSystem.sMetrics;
            resolvedWidgets += 1L;
            paramConstraintWidgetContainer = LinearSystem.sMetrics;
            chainConnectionResolved += 1L;
          }
          paramConstraintWidgetContainer = mListNextVisibleWidget[paramInt1];
          if (paramConstraintWidgetContainer == null)
          {
            f2 = f1;
            if (localObject1 != localConstraintWidget1) {}
          }
          else
          {
            if (paramInt1 == 0) {
              f2 = ((ConstraintWidget)localObject1).getWidth();
            } else {
              f2 = ((ConstraintWidget)localObject1).getHeight();
            }
            f1 += mListAnchors[paramInt2].getMargin();
            mListAnchors[paramInt2].getResolutionNode().resolve(resolvedTarget, f1);
            paramChainHead = mListAnchors[m].getResolutionNode();
            localObject2 = resolvedTarget;
            f1 += f2;
            paramChainHead.resolve((ResolutionAnchor)localObject2, f1);
            mListAnchors[paramInt2].getResolutionNode().addResolvedValue(paramLinearSystem);
            mListAnchors[m].getResolutionNode().addResolvedValue(paramLinearSystem);
            f2 = f1 + mListAnchors[m].getMargin();
          }
          localObject1 = paramConstraintWidgetContainer;
        }
      }
      for (;;)
      {
        return true;
        if ((i != 0) || (j != 0))
        {
          if (i != 0)
          {
            f3 = f2 - f1;
          }
          else
          {
            f3 = f2;
            if (j != 0) {
              f3 = f2 - f1;
            }
          }
          f1 = f3 / (i1 + 1);
          if (j != 0) {
            if (i1 > 1) {
              f1 = f3 / (i1 - 1);
            } else {
              f1 = f3 / 2.0F;
            }
          }
          f2 = f5 + f1;
          f3 = f2;
          if (j != 0)
          {
            f3 = f2;
            if (i1 > 1) {
              f3 = mListAnchors[paramInt2].getMargin() + f5;
            }
          }
          paramConstraintWidgetContainer = (ConstraintWidgetContainer)localObject1;
          f2 = f3;
          if (i != 0)
          {
            paramConstraintWidgetContainer = (ConstraintWidgetContainer)localObject1;
            f2 = f3;
            if (localObject1 != null)
            {
              f2 = f3 + mListAnchors[paramInt2].getMargin();
              paramConstraintWidgetContainer = (ConstraintWidgetContainer)localObject1;
            }
          }
          while (paramConstraintWidgetContainer != null)
          {
            if (LinearSystem.sMetrics != null)
            {
              paramChainHead = LinearSystem.sMetrics;
              nonresolvedWidgets -= 1L;
              paramChainHead = LinearSystem.sMetrics;
              resolvedWidgets += 1L;
              paramChainHead = LinearSystem.sMetrics;
              chainConnectionResolved += 1L;
            }
            paramChainHead = mListNextVisibleWidget[paramInt1];
            if (paramChainHead == null)
            {
              f3 = f2;
              if (paramConstraintWidgetContainer != localConstraintWidget1) {}
            }
            else
            {
              if (paramInt1 == 0) {
                f3 = paramConstraintWidgetContainer.getWidth();
              } else {
                f3 = paramConstraintWidgetContainer.getHeight();
              }
              mListAnchors[paramInt2].getResolutionNode().resolve(resolvedTarget, f2);
              mListAnchors[m].getResolutionNode().resolve(resolvedTarget, f2 + f3);
              mListAnchors[paramInt2].getResolutionNode().addResolvedValue(paramLinearSystem);
              mListAnchors[m].getResolutionNode().addResolvedValue(paramLinearSystem);
              f3 = f2 + (f3 + f1);
            }
            paramConstraintWidgetContainer = paramChainHead;
            f2 = f3;
          }
        }
      }
    }
    return false;
    label1757:
    return false;
    label1759:
    return false;
  }
  
  static void checkMatchParent(ConstraintWidgetContainer paramConstraintWidgetContainer, LinearSystem paramLinearSystem, ConstraintWidget paramConstraintWidget)
  {
    int i;
    int j;
    if ((mListDimensionBehaviors[0] != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) && (mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT))
    {
      i = mLeft.mMargin;
      j = paramConstraintWidgetContainer.getWidth() - mRight.mMargin;
      mLeft.mSolverVariable = paramLinearSystem.createObjectVariable(mLeft);
      mRight.mSolverVariable = paramLinearSystem.createObjectVariable(mRight);
      paramLinearSystem.addEquality(mLeft.mSolverVariable, i);
      paramLinearSystem.addEquality(mRight.mSolverVariable, j);
      mHorizontalResolution = 2;
      paramConstraintWidget.setHorizontalDimension(i, j);
    }
    if ((mListDimensionBehaviors[1] != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) && (mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT))
    {
      i = mTop.mMargin;
      j = paramConstraintWidgetContainer.getHeight() - mBottom.mMargin;
      mTop.mSolverVariable = paramLinearSystem.createObjectVariable(mTop);
      mBottom.mSolverVariable = paramLinearSystem.createObjectVariable(mBottom);
      paramLinearSystem.addEquality(mTop.mSolverVariable, i);
      paramLinearSystem.addEquality(mBottom.mSolverVariable, j);
      if ((mBaselineDistance > 0) || (paramConstraintWidget.getVisibility() == 8))
      {
        mBaseline.mSolverVariable = paramLinearSystem.createObjectVariable(mBaseline);
        paramLinearSystem.addEquality(mBaseline.mSolverVariable, mBaselineDistance + i);
      }
      mVerticalResolution = 2;
      paramConstraintWidget.setVerticalDimension(i, j);
    }
  }
  
  private static boolean optimizableMatchConstraint(ConstraintWidget paramConstraintWidget, int paramInt)
  {
    if (mListDimensionBehaviors[paramInt] != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
      return false;
    }
    float f = mDimensionRatio;
    int i = 1;
    if (f != 0.0F)
    {
      paramConstraintWidget = mListDimensionBehaviors;
      if (paramInt == 0) {
        paramInt = i;
      } else {
        paramInt = 0;
      }
      if (paramConstraintWidget[paramInt] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {}
      return false;
    }
    if (paramInt == 0)
    {
      if (mMatchConstraintDefaultWidth != 0) {
        return false;
      }
      if (mMatchConstraintMinWidth != 0) {
        break label110;
      }
      if (mMatchConstraintMaxWidth != 0) {
        return false;
      }
    }
    else
    {
      if (mMatchConstraintDefaultHeight != 0) {
        return false;
      }
      if (mMatchConstraintMinHeight != 0) {
        break label110;
      }
      if (mMatchConstraintMaxHeight == 0) {
        break label112;
      }
      return false;
    }
    return true;
    label110:
    return false;
    label112:
    return true;
  }
}
