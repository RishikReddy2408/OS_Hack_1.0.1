package android.support.constraint.solver;

public class ArrayRow
  implements LinearSystem.Row
{
  private static final boolean DEBUG = false;
  private static final float epsilon = 0.001F;
  float constantValue = 0.0F;
  boolean isSimpleDefinition = false;
  boolean used = false;
  SolverVariable variable = null;
  public final ArrayLinkedVariables variables;
  
  public ArrayRow(Cache paramCache)
  {
    variables = new ArrayLinkedVariables(this, paramCache);
  }
  
  public ArrayRow addError(LinearSystem paramLinearSystem, int paramInt)
  {
    variables.combine(paramLinearSystem.createErrorVariable(paramInt, "ep"), 1.0F);
    variables.combine(paramLinearSystem.createErrorVariable(paramInt, "em"), -1.0F);
    return this;
  }
  
  public void addError(SolverVariable paramSolverVariable)
  {
    int i = strength;
    float f = 1.0F;
    if (i != 1) {
      if (strength == 2) {
        f = 1000.0F;
      } else if (strength == 3) {
        f = 1000000.0F;
      } else if (strength == 4) {
        f = 1.0E9F;
      } else if (strength == 5) {
        f = 1.0E12F;
      }
    }
    variables.combine(paramSolverVariable, f);
  }
  
  ArrayRow addSingleError(SolverVariable paramSolverVariable, int paramInt)
  {
    variables.combine(paramSolverVariable, paramInt);
    return this;
  }
  
  boolean chooseSubject(LinearSystem paramLinearSystem)
  {
    paramLinearSystem = variables.chooseSubject(paramLinearSystem);
    boolean bool;
    if (paramLinearSystem == null)
    {
      bool = true;
    }
    else
    {
      pivot(paramLinearSystem);
      bool = false;
    }
    if (variables.currentSize == 0) {
      isSimpleDefinition = true;
    }
    return bool;
  }
  
  public void clear()
  {
    variables.clear();
    variable = null;
    constantValue = 0.0F;
  }
  
  ArrayRow createRowCentering(SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, int paramInt1, float paramFloat, SolverVariable paramSolverVariable3, SolverVariable paramSolverVariable4, int paramInt2)
  {
    if (paramSolverVariable2 == paramSolverVariable3)
    {
      variables.combine(paramSolverVariable1, 1.0F);
      variables.combine(paramSolverVariable4, 1.0F);
      variables.combine(paramSolverVariable2, -2.0F);
      return this;
    }
    if (paramFloat == 0.5F)
    {
      variables.combine(paramSolverVariable1, 1.0F);
      variables.combine(paramSolverVariable2, -1.0F);
      variables.combine(paramSolverVariable3, -1.0F);
      variables.combine(paramSolverVariable4, 1.0F);
      if ((paramInt1 > 0) || (paramInt2 > 0))
      {
        constantValue = (-paramInt1 + paramInt2);
        return this;
      }
    }
    else
    {
      if (paramFloat <= 0.0F)
      {
        variables.combine(paramSolverVariable1, -1.0F);
        variables.combine(paramSolverVariable2, 1.0F);
        constantValue = paramInt1;
        return this;
      }
      if (paramFloat >= 1.0F)
      {
        variables.combine(paramSolverVariable3, -1.0F);
        variables.combine(paramSolverVariable4, 1.0F);
        constantValue = paramInt2;
        return this;
      }
      ArrayLinkedVariables localArrayLinkedVariables = variables;
      float f = 1.0F - paramFloat;
      localArrayLinkedVariables.combine(paramSolverVariable1, f * 1.0F);
      variables.combine(paramSolverVariable2, f * -1.0F);
      variables.combine(paramSolverVariable3, -1.0F * paramFloat);
      variables.combine(paramSolverVariable4, 1.0F * paramFloat);
      if ((paramInt1 > 0) || (paramInt2 > 0)) {
        constantValue = (-paramInt1 * f + paramInt2 * paramFloat);
      }
    }
    return this;
  }
  
  ArrayRow createRowDefinition(SolverVariable paramSolverVariable, int paramInt)
  {
    variable = paramSolverVariable;
    float f = paramInt;
    computedValue = f;
    constantValue = f;
    isSimpleDefinition = true;
    return this;
  }
  
  ArrayRow createRowDimensionPercent(SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, SolverVariable paramSolverVariable3, float paramFloat)
  {
    variables.combine(paramSolverVariable1, -1.0F);
    variables.combine(paramSolverVariable2, 1.0F - paramFloat);
    variables.combine(paramSolverVariable3, paramFloat);
    return this;
  }
  
  public ArrayRow createRowDimensionRatio(SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, SolverVariable paramSolverVariable3, SolverVariable paramSolverVariable4, float paramFloat)
  {
    variables.combine(paramSolverVariable1, -1.0F);
    variables.combine(paramSolverVariable2, 1.0F);
    variables.combine(paramSolverVariable3, paramFloat);
    variables.combine(paramSolverVariable4, -paramFloat);
    return this;
  }
  
  public ArrayRow createRowEqualDimension(float paramFloat1, float paramFloat2, float paramFloat3, SolverVariable paramSolverVariable1, int paramInt1, SolverVariable paramSolverVariable2, int paramInt2, SolverVariable paramSolverVariable3, int paramInt3, SolverVariable paramSolverVariable4, int paramInt4)
  {
    if ((paramFloat2 != 0.0F) && (paramFloat1 != paramFloat3))
    {
      paramFloat1 = paramFloat1 / paramFloat2 / (paramFloat3 / paramFloat2);
      constantValue = (-paramInt1 - paramInt2 + paramInt3 * paramFloat1 + paramInt4 * paramFloat1);
      variables.combine(paramSolverVariable1, 1.0F);
      variables.combine(paramSolverVariable2, -1.0F);
      variables.combine(paramSolverVariable4, paramFloat1);
      variables.combine(paramSolverVariable3, -paramFloat1);
      return this;
    }
    constantValue = (-paramInt1 - paramInt2 + paramInt3 + paramInt4);
    variables.combine(paramSolverVariable1, 1.0F);
    variables.combine(paramSolverVariable2, -1.0F);
    variables.combine(paramSolverVariable4, 1.0F);
    variables.combine(paramSolverVariable3, -1.0F);
    return this;
  }
  
  public ArrayRow createRowEqualMatchDimensions(float paramFloat1, float paramFloat2, float paramFloat3, SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, SolverVariable paramSolverVariable3, SolverVariable paramSolverVariable4)
  {
    constantValue = 0.0F;
    if ((paramFloat2 != 0.0F) && (paramFloat1 != paramFloat3))
    {
      if (paramFloat1 == 0.0F)
      {
        variables.combine(paramSolverVariable1, 1.0F);
        variables.combine(paramSolverVariable2, -1.0F);
        return this;
      }
      if (paramFloat3 == 0.0F)
      {
        variables.combine(paramSolverVariable3, 1.0F);
        variables.combine(paramSolverVariable4, -1.0F);
        return this;
      }
      paramFloat1 = paramFloat1 / paramFloat2 / (paramFloat3 / paramFloat2);
      variables.combine(paramSolverVariable1, 1.0F);
      variables.combine(paramSolverVariable2, -1.0F);
      variables.combine(paramSolverVariable4, paramFloat1);
      variables.combine(paramSolverVariable3, -paramFloat1);
      return this;
    }
    variables.combine(paramSolverVariable1, 1.0F);
    variables.combine(paramSolverVariable2, -1.0F);
    variables.combine(paramSolverVariable4, 1.0F);
    variables.combine(paramSolverVariable3, -1.0F);
    return this;
  }
  
  public ArrayRow createRowEquals(SolverVariable paramSolverVariable, int paramInt)
  {
    if (paramInt < 0)
    {
      constantValue = (paramInt * -1);
      variables.combine(paramSolverVariable, 1.0F);
      return this;
    }
    constantValue = paramInt;
    variables.combine(paramSolverVariable, -1.0F);
    return this;
  }
  
  public ArrayRow createRowEquals(SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, int paramInt)
  {
    int i = 0;
    int j = 0;
    if (paramInt != 0)
    {
      i = j;
      j = paramInt;
      if (paramInt < 0)
      {
        j = paramInt * -1;
        i = 1;
      }
      constantValue = j;
    }
    if (i == 0)
    {
      variables.combine(paramSolverVariable1, -1.0F);
      variables.combine(paramSolverVariable2, 1.0F);
      return this;
    }
    variables.combine(paramSolverVariable1, 1.0F);
    variables.combine(paramSolverVariable2, -1.0F);
    return this;
  }
  
  public ArrayRow createRowGreaterThan(SolverVariable paramSolverVariable1, int paramInt, SolverVariable paramSolverVariable2)
  {
    constantValue = paramInt;
    variables.combine(paramSolverVariable1, -1.0F);
    return this;
  }
  
  public ArrayRow createRowGreaterThan(SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, SolverVariable paramSolverVariable3, int paramInt)
  {
    int i = 0;
    int j = 0;
    if (paramInt != 0)
    {
      i = j;
      j = paramInt;
      if (paramInt < 0)
      {
        j = paramInt * -1;
        i = 1;
      }
      constantValue = j;
    }
    if (i == 0)
    {
      variables.combine(paramSolverVariable1, -1.0F);
      variables.combine(paramSolverVariable2, 1.0F);
      variables.combine(paramSolverVariable3, 1.0F);
      return this;
    }
    variables.combine(paramSolverVariable1, 1.0F);
    variables.combine(paramSolverVariable2, -1.0F);
    variables.combine(paramSolverVariable3, -1.0F);
    return this;
  }
  
  public ArrayRow createRowLowerThan(SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, SolverVariable paramSolverVariable3, int paramInt)
  {
    int i = 0;
    int j = 0;
    if (paramInt != 0)
    {
      i = j;
      j = paramInt;
      if (paramInt < 0)
      {
        j = paramInt * -1;
        i = 1;
      }
      constantValue = j;
    }
    if (i == 0)
    {
      variables.combine(paramSolverVariable1, -1.0F);
      variables.combine(paramSolverVariable2, 1.0F);
      variables.combine(paramSolverVariable3, -1.0F);
      return this;
    }
    variables.combine(paramSolverVariable1, 1.0F);
    variables.combine(paramSolverVariable2, -1.0F);
    variables.combine(paramSolverVariable3, 1.0F);
    return this;
  }
  
  public ArrayRow createRowWithAngle(SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, SolverVariable paramSolverVariable3, SolverVariable paramSolverVariable4, float paramFloat)
  {
    variables.combine(paramSolverVariable3, 0.5F);
    variables.combine(paramSolverVariable4, 0.5F);
    variables.combine(paramSolverVariable1, -0.5F);
    variables.combine(paramSolverVariable2, -0.5F);
    constantValue = (-paramFloat);
    return this;
  }
  
  void ensurePositiveConstant()
  {
    if (constantValue < 0.0F)
    {
      constantValue *= -1.0F;
      variables.invert();
    }
  }
  
  public SolverVariable getKey()
  {
    return variable;
  }
  
  public SolverVariable getPivotCandidate(LinearSystem paramLinearSystem, boolean[] paramArrayOfBoolean)
  {
    return variables.getPivotCandidate(paramArrayOfBoolean, null);
  }
  
  boolean hasKeyVariable()
  {
    return (variable != null) && ((variable.mType == SolverVariable.Type.UNRESTRICTED) || (constantValue >= 0.0F));
  }
  
  boolean hasVariable(SolverVariable paramSolverVariable)
  {
    return variables.containsKey(paramSolverVariable);
  }
  
  public void initFromRow(LinearSystem.Row paramRow)
  {
    if ((paramRow instanceof ArrayRow))
    {
      paramRow = (ArrayRow)paramRow;
      variable = null;
      variables.clear();
      int i = 0;
      while (i < variables.currentSize)
      {
        SolverVariable localSolverVariable = variables.getVariable(i);
        float f = variables.getVariableValue(i);
        variables.ensureCapacity(localSolverVariable, f, true);
        i += 1;
      }
    }
  }
  
  public boolean isEmpty()
  {
    return (variable == null) && (constantValue == 0.0F) && (variables.currentSize == 0);
  }
  
  SolverVariable pickPivot(SolverVariable paramSolverVariable)
  {
    return variables.getPivotCandidate(null, paramSolverVariable);
  }
  
  void pivot(SolverVariable paramSolverVariable)
  {
    if (variable != null)
    {
      variables.combine(variable, -1.0F);
      variable = null;
    }
    float f = variables.remove(paramSolverVariable, true) * -1.0F;
    variable = paramSolverVariable;
    if (f == 1.0F) {
      return;
    }
    constantValue /= f;
    variables.divideByAmount(f);
  }
  
  public void reset()
  {
    variable = null;
    variables.clear();
    constantValue = 0.0F;
    isSimpleDefinition = false;
  }
  
  int sizeInBytes()
  {
    int i;
    if (variable != null) {
      i = 4;
    } else {
      i = 0;
    }
    return i + 4 + 4 + variables.sizeInBytes();
  }
  
  String toReadableString()
  {
    if (variable == null)
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("");
      ((StringBuilder)localObject1).append("0");
      localObject1 = ((StringBuilder)localObject1).toString();
    }
    else
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("");
      ((StringBuilder)localObject1).append(variable);
      localObject1 = ((StringBuilder)localObject1).toString();
    }
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append((String)localObject1);
    ((StringBuilder)localObject2).append(" = ");
    Object localObject1 = ((StringBuilder)localObject2).toString();
    localObject2 = localObject1;
    float f1 = constantValue;
    int k = 0;
    if (f1 != 0.0F)
    {
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append((String)localObject1);
      ((StringBuilder)localObject2).append(constantValue);
      localObject2 = ((StringBuilder)localObject2).toString();
      i = 1;
    }
    else
    {
      i = 0;
    }
    int m = variables.currentSize;
    int j = i;
    int i = k;
    while (i < m)
    {
      localObject1 = variables.getVariable(i);
      if (localObject1 == null)
      {
        localObject1 = localObject2;
      }
      else
      {
        float f2 = variables.getVariableValue(i);
        f1 = f2;
        if (f2 == 0.0F)
        {
          localObject1 = localObject2;
        }
        else
        {
          String str = ((SolverVariable)localObject1).toString();
          if (j == 0)
          {
            localObject1 = localObject2;
            if (f2 < 0.0F)
            {
              localObject1 = new StringBuilder();
              ((StringBuilder)localObject1).append((String)localObject2);
              ((StringBuilder)localObject1).append("- ");
              localObject1 = ((StringBuilder)localObject1).toString();
              f1 = f2 * -1.0F;
            }
          }
          else if (f2 > 0.0F)
          {
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append((String)localObject2);
            ((StringBuilder)localObject1).append(" + ");
            localObject1 = ((StringBuilder)localObject1).toString();
          }
          else
          {
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append((String)localObject2);
            ((StringBuilder)localObject1).append(" - ");
            localObject1 = ((StringBuilder)localObject1).toString();
            f1 = f2 * -1.0F;
          }
          if (f1 == 1.0F)
          {
            localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append((String)localObject1);
            ((StringBuilder)localObject2).append(str);
            localObject1 = ((StringBuilder)localObject2).toString();
          }
          else
          {
            localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append((String)localObject1);
            ((StringBuilder)localObject2).append(f1);
            ((StringBuilder)localObject2).append(" ");
            ((StringBuilder)localObject2).append(str);
            localObject1 = ((StringBuilder)localObject2).toString();
          }
          j = 1;
        }
      }
      i += 1;
      localObject2 = localObject1;
    }
    if (j == 0)
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append((String)localObject2);
      ((StringBuilder)localObject1).append("0.0");
      return ((StringBuilder)localObject1).toString();
    }
    return localObject2;
  }
  
  public String toString()
  {
    return toReadableString();
  }
}
