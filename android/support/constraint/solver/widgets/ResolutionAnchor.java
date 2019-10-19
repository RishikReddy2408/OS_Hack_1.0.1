package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.Metrics;
import android.support.constraint.solver.SolverVariable;

public class ResolutionAnchor
  extends ResolutionNode
{
  public static final int BARRIER_CONNECTION = 5;
  public static final int CENTER_CONNECTION = 2;
  public static final int CHAIN_CONNECTION = 4;
  public static final int DIRECT_CONNECTION = 1;
  public static final int MATCH_CONNECTION = 3;
  public static final int UNCONNECTED = 0;
  float computedValue;
  private ResolutionDimension dimension = null;
  private int dimensionMultiplier = 1;
  ConstraintAnchor myAnchor;
  float offset;
  private ResolutionAnchor opposite;
  private ResolutionDimension oppositeDimension = null;
  private int oppositeDimensionMultiplier = 1;
  private float oppositeOffset;
  float resolvedOffset;
  ResolutionAnchor resolvedTarget;
  ResolutionAnchor target;
  int type = 0;
  
  public ResolutionAnchor(ConstraintAnchor paramConstraintAnchor)
  {
    myAnchor = paramConstraintAnchor;
  }
  
  void addResolvedValue(LinearSystem paramLinearSystem)
  {
    SolverVariable localSolverVariable = myAnchor.getSolverVariable();
    if (resolvedTarget == null)
    {
      paramLinearSystem.addEquality(localSolverVariable, (int)resolvedOffset);
      return;
    }
    paramLinearSystem.addEquality(localSolverVariable, paramLinearSystem.createObjectVariable(resolvedTarget.myAnchor), (int)resolvedOffset, 6);
  }
  
  public void dependsOn(int paramInt1, ResolutionAnchor paramResolutionAnchor, int paramInt2)
  {
    type = paramInt1;
    target = paramResolutionAnchor;
    offset = paramInt2;
    target.addDependent(this);
  }
  
  public void dependsOn(ResolutionAnchor paramResolutionAnchor, int paramInt)
  {
    target = paramResolutionAnchor;
    offset = paramInt;
    target.addDependent(this);
  }
  
  public void dependsOn(ResolutionAnchor paramResolutionAnchor, int paramInt, ResolutionDimension paramResolutionDimension)
  {
    target = paramResolutionAnchor;
    target.addDependent(this);
    dimension = paramResolutionDimension;
    dimensionMultiplier = paramInt;
    dimension.addDependent(this);
  }
  
  public float getResolvedValue()
  {
    return resolvedOffset;
  }
  
  public void remove(ResolutionDimension paramResolutionDimension)
  {
    if (dimension == paramResolutionDimension)
    {
      dimension = null;
      offset = dimensionMultiplier;
    }
    else if (dimension == oppositeDimension)
    {
      oppositeDimension = null;
      oppositeOffset = oppositeDimensionMultiplier;
    }
    resolve();
  }
  
  public void reset()
  {
    super.reset();
    target = null;
    offset = 0.0F;
    dimension = null;
    dimensionMultiplier = 1;
    oppositeDimension = null;
    oppositeDimensionMultiplier = 1;
    resolvedTarget = null;
    resolvedOffset = 0.0F;
    computedValue = 0.0F;
    opposite = null;
    oppositeOffset = 0.0F;
    type = 0;
  }
  
  public void resolve()
  {
    int i = state;
    int k = 1;
    if (i == 1) {
      return;
    }
    if (type == 4) {
      return;
    }
    if (dimension != null)
    {
      if (dimension.state != 1) {
        return;
      }
      offset = (dimensionMultiplier * dimension.value);
    }
    if (oppositeDimension != null)
    {
      if (oppositeDimension.state != 1) {
        return;
      }
      oppositeOffset = (oppositeDimensionMultiplier * oppositeDimension.value);
    }
    if ((type == 1) && ((target == null) || (target.state == 1)))
    {
      if (target == null)
      {
        resolvedTarget = this;
        resolvedOffset = offset;
      }
      else
      {
        resolvedTarget = target.resolvedTarget;
        resolvedOffset = (target.resolvedOffset + offset);
      }
      didResolve();
      return;
    }
    Object localObject;
    if ((type == 2) && (target != null) && (target.state == 1) && (opposite != null) && (opposite.target != null) && (opposite.target.state == 1))
    {
      if (LinearSystem.getMetrics() != null)
      {
        localObject = LinearSystem.getMetrics();
        centerConnectionResolved += 1L;
      }
      resolvedTarget = target.resolvedTarget;
      opposite.resolvedTarget = opposite.target.resolvedTarget;
      localObject = myAnchor.mType;
      ConstraintAnchor.Type localType = ConstraintAnchor.Type.RIGHT;
      int j = 0;
      i = k;
      if (localObject != localType) {
        if (myAnchor.mType == ConstraintAnchor.Type.BOTTOM) {
          i = k;
        } else {
          i = 0;
        }
      }
      float f1;
      if (i != 0) {
        f1 = target.resolvedOffset - opposite.target.resolvedOffset;
      } else {
        f1 = opposite.target.resolvedOffset - target.resolvedOffset;
      }
      if ((myAnchor.mType != ConstraintAnchor.Type.LEFT) && (myAnchor.mType != ConstraintAnchor.Type.RIGHT))
      {
        f2 = f1 - myAnchor.mOwner.getHeight();
        f1 = myAnchor.mOwner.mVerticalBiasPercent;
      }
      else
      {
        f2 = f1 - myAnchor.mOwner.getWidth();
        f1 = myAnchor.mOwner.mHorizontalBiasPercent;
      }
      int m = myAnchor.getMargin();
      k = opposite.myAnchor.getMargin();
      if (myAnchor.getTarget() == opposite.myAnchor.getTarget())
      {
        f1 = 0.5F;
        k = 0;
      }
      else
      {
        j = m;
      }
      float f3 = j;
      float f4 = k;
      float f2 = f2 - f3 - f4;
      if (i != 0)
      {
        opposite.resolvedOffset = (opposite.target.resolvedOffset + f4 + f2 * f1);
        resolvedOffset = (target.resolvedOffset - f3 - f2 * (1.0F - f1));
      }
      else
      {
        resolvedOffset = (target.resolvedOffset + f3 + f2 * f1);
        opposite.resolvedOffset = (opposite.target.resolvedOffset - f4 - f2 * (1.0F - f1));
      }
      didResolve();
      opposite.didResolve();
      return;
    }
    if ((type == 3) && (target != null) && (target.state == 1) && (opposite != null) && (opposite.target != null) && (opposite.target.state == 1))
    {
      if (LinearSystem.getMetrics() != null)
      {
        localObject = LinearSystem.getMetrics();
        matchConnectionResolved += 1L;
      }
      resolvedTarget = target.resolvedTarget;
      opposite.resolvedTarget = opposite.target.resolvedTarget;
      resolvedOffset = (target.resolvedOffset + offset);
      opposite.resolvedOffset = (opposite.target.resolvedOffset + opposite.offset);
      didResolve();
      opposite.didResolve();
      return;
    }
    if (type == 5) {
      myAnchor.mOwner.resolve();
    }
  }
  
  public void resolve(ResolutionAnchor paramResolutionAnchor, float paramFloat)
  {
    if ((state == 0) || ((resolvedTarget != paramResolutionAnchor) && (resolvedOffset != paramFloat)))
    {
      resolvedTarget = paramResolutionAnchor;
      resolvedOffset = paramFloat;
      if (state == 1) {
        invalidate();
      }
      didResolve();
    }
  }
  
  String sType(int paramInt)
  {
    if (paramInt == 1) {
      return "DIRECT";
    }
    if (paramInt == 2) {
      return "CENTER";
    }
    if (paramInt == 3) {
      return "MATCH";
    }
    if (paramInt == 4) {
      return "CHAIN";
    }
    if (paramInt == 5) {
      return "BARRIER";
    }
    return "UNCONNECTED";
  }
  
  public void setOpposite(ResolutionAnchor paramResolutionAnchor, float paramFloat)
  {
    opposite = paramResolutionAnchor;
    oppositeOffset = paramFloat;
  }
  
  public void setOpposite(ResolutionAnchor paramResolutionAnchor, int paramInt, ResolutionDimension paramResolutionDimension)
  {
    opposite = paramResolutionAnchor;
    oppositeDimension = paramResolutionDimension;
    oppositeDimensionMultiplier = paramInt;
  }
  
  public void setType(int paramInt)
  {
    type = paramInt;
  }
  
  public String toString()
  {
    if (state == 1)
    {
      if (resolvedTarget == this)
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("[");
        localStringBuilder.append(myAnchor);
        localStringBuilder.append(", RESOLVED: ");
        localStringBuilder.append(resolvedOffset);
        localStringBuilder.append("]  type: ");
        localStringBuilder.append(sType(type));
        return localStringBuilder.toString();
      }
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("[");
      localStringBuilder.append(myAnchor);
      localStringBuilder.append(", RESOLVED: ");
      localStringBuilder.append(resolvedTarget);
      localStringBuilder.append(":");
      localStringBuilder.append(resolvedOffset);
      localStringBuilder.append("] type: ");
      localStringBuilder.append(sType(type));
      return localStringBuilder.toString();
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("{ ");
    localStringBuilder.append(myAnchor);
    localStringBuilder.append(" UNRESOLVED} type: ");
    localStringBuilder.append(sType(type));
    return localStringBuilder.toString();
  }
  
  public void update()
  {
    ConstraintAnchor localConstraintAnchor = myAnchor.getTarget();
    if (localConstraintAnchor == null) {
      return;
    }
    if (localConstraintAnchor.getTarget() == myAnchor)
    {
      type = 4;
      getResolutionNodetype = 4;
    }
    int j = myAnchor.getMargin();
    int i = j;
    if ((myAnchor.mType == ConstraintAnchor.Type.RIGHT) || (myAnchor.mType == ConstraintAnchor.Type.BOTTOM)) {
      i = -j;
    }
    dependsOn(localConstraintAnchor.getResolutionNode(), i);
  }
}
