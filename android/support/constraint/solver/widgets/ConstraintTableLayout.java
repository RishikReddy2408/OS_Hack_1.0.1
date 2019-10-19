package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;
import java.util.ArrayList;

public class ConstraintTableLayout
  extends ConstraintWidgetContainer
{
  public static final int ALIGN_CENTER = 0;
  private static final int ALIGN_FULL = 3;
  public static final int ALIGN_LEFT = 1;
  public static final int ALIGN_RIGHT = 2;
  private ArrayList<Guideline> mHorizontalGuidelines = new ArrayList();
  private ArrayList<HorizontalSlice> mHorizontalSlices = new ArrayList();
  private int mNumCols = 0;
  private int mNumRows = 0;
  private int mPadding = 8;
  private boolean mVerticalGrowth = true;
  private ArrayList<Guideline> mVerticalGuidelines = new ArrayList();
  private ArrayList<VerticalSlice> mVerticalSlices = new ArrayList();
  private LinearSystem system = null;
  
  public ConstraintTableLayout() {}
  
  public ConstraintTableLayout(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
  }
  
  public ConstraintTableLayout(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  private void setChildrenConnections()
  {
    int k = mChildren.size();
    int i = 0;
    int j = 0;
    while (i < k)
    {
      ConstraintWidget localConstraintWidget1 = (ConstraintWidget)mChildren.get(i);
      j += localConstraintWidget1.getContainerItemSkip();
      int m = mNumCols;
      int n = j / mNumCols;
      Object localObject = (HorizontalSlice)mHorizontalSlices.get(n);
      VerticalSlice localVerticalSlice = (VerticalSlice)mVerticalSlices.get(j % m);
      ConstraintWidget localConstraintWidget2 = left;
      ConstraintWidget localConstraintWidget3 = right;
      ConstraintWidget localConstraintWidget4 = top;
      localObject = bottom;
      localConstraintWidget1.getAnchor(ConstraintAnchor.Type.LEFT).connect(localConstraintWidget2.getAnchor(ConstraintAnchor.Type.LEFT), mPadding);
      if ((localConstraintWidget3 instanceof Guideline)) {
        localConstraintWidget1.getAnchor(ConstraintAnchor.Type.RIGHT).connect(localConstraintWidget3.getAnchor(ConstraintAnchor.Type.LEFT), mPadding);
      } else {
        localConstraintWidget1.getAnchor(ConstraintAnchor.Type.RIGHT).connect(localConstraintWidget3.getAnchor(ConstraintAnchor.Type.RIGHT), mPadding);
      }
      switch (alignment)
      {
      default: 
        break;
      case 3: 
        localConstraintWidget1.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
        break;
      case 2: 
        localConstraintWidget1.getAnchor(ConstraintAnchor.Type.LEFT).setStrength(ConstraintAnchor.Strength.WEAK);
        localConstraintWidget1.getAnchor(ConstraintAnchor.Type.RIGHT).setStrength(ConstraintAnchor.Strength.STRONG);
        break;
      case 1: 
        localConstraintWidget1.getAnchor(ConstraintAnchor.Type.LEFT).setStrength(ConstraintAnchor.Strength.STRONG);
        localConstraintWidget1.getAnchor(ConstraintAnchor.Type.RIGHT).setStrength(ConstraintAnchor.Strength.WEAK);
      }
      localConstraintWidget1.getAnchor(ConstraintAnchor.Type.MIDDLE).connect(localConstraintWidget4.getAnchor(ConstraintAnchor.Type.MIDDLE), mPadding);
      if ((localObject instanceof Guideline)) {
        localConstraintWidget1.getAnchor(ConstraintAnchor.Type.BOTTOM).connect(((ConstraintWidget)localObject).getAnchor(ConstraintAnchor.Type.MIDDLE), mPadding);
      } else {
        localConstraintWidget1.getAnchor(ConstraintAnchor.Type.BOTTOM).connect(((ConstraintWidget)localObject).getAnchor(ConstraintAnchor.Type.BOTTOM), mPadding);
      }
      j += 1;
      i += 1;
    }
  }
  
  private void setHorizontalSlices()
  {
    mHorizontalSlices.clear();
    float f2 = 100.0F / mNumRows;
    Object localObject = this;
    float f1 = f2;
    int i = 0;
    while (i < mNumRows)
    {
      HorizontalSlice localHorizontalSlice = new HorizontalSlice();
      top = ((ConstraintWidget)localObject);
      if (i < mNumRows - 1)
      {
        localObject = new Guideline();
        ((Guideline)localObject).setOrientation(0);
        ((ConstraintWidget)localObject).setParent(this);
        ((Guideline)localObject).setGuidePercent((int)f1);
        f1 += f2;
        bottom = ((ConstraintWidget)localObject);
        mHorizontalGuidelines.add(localObject);
      }
      else
      {
        bottom = this;
      }
      localObject = bottom;
      mHorizontalSlices.add(localHorizontalSlice);
      i += 1;
    }
    updateDebugSolverNames();
  }
  
  private void setVerticalSlices()
  {
    mVerticalSlices.clear();
    float f2 = 100.0F / mNumCols;
    int i = 0;
    Object localObject = this;
    float f1 = f2;
    while (i < mNumCols)
    {
      VerticalSlice localVerticalSlice = new VerticalSlice();
      left = ((ConstraintWidget)localObject);
      if (i < mNumCols - 1)
      {
        localObject = new Guideline();
        ((Guideline)localObject).setOrientation(1);
        ((ConstraintWidget)localObject).setParent(this);
        ((Guideline)localObject).setGuidePercent((int)f1);
        f1 += f2;
        right = ((ConstraintWidget)localObject);
        mVerticalGuidelines.add(localObject);
      }
      else
      {
        right = this;
      }
      localObject = right;
      mVerticalSlices.add(localVerticalSlice);
      i += 1;
    }
    updateDebugSolverNames();
  }
  
  private void updateDebugSolverNames()
  {
    if (system == null) {
      return;
    }
    int k = mVerticalGuidelines.size();
    int j = 0;
    int i = 0;
    Guideline localGuideline;
    LinearSystem localLinearSystem;
    StringBuilder localStringBuilder;
    while (i < k)
    {
      localGuideline = (Guideline)mVerticalGuidelines.get(i);
      localLinearSystem = system;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(getDebugName());
      localStringBuilder.append(".VG");
      localStringBuilder.append(i);
      localGuideline.setDebugSolverName(localLinearSystem, localStringBuilder.toString());
      i += 1;
    }
    k = mHorizontalGuidelines.size();
    i = j;
    while (i < k)
    {
      localGuideline = (Guideline)mHorizontalGuidelines.get(i);
      localLinearSystem = system;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(getDebugName());
      localStringBuilder.append(".HG");
      localStringBuilder.append(i);
      localGuideline.setDebugSolverName(localLinearSystem, localStringBuilder.toString());
      i += 1;
    }
  }
  
  public void addToSolver(LinearSystem paramLinearSystem)
  {
    super.addToSolver(paramLinearSystem);
    int m = mChildren.size();
    if (m == 0) {
      return;
    }
    setTableDimensions();
    if (paramLinearSystem == mSystem)
    {
      int j = mVerticalGuidelines.size();
      int k = 0;
      int i = 0;
      boolean bool;
      Guideline localGuideline;
      for (;;)
      {
        bool = true;
        if (i >= j) {
          break;
        }
        localGuideline = (Guideline)mVerticalGuidelines.get(i);
        if (getHorizontalDimensionBehaviour() != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
          bool = false;
        }
        localGuideline.setPositionRelaxed(bool);
        localGuideline.addToSolver(paramLinearSystem);
        i += 1;
      }
      int n = mHorizontalGuidelines.size();
      i = 0;
      for (;;)
      {
        j = k;
        if (i >= n) {
          break;
        }
        localGuideline = (Guideline)mHorizontalGuidelines.get(i);
        if (getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
          bool = true;
        } else {
          bool = false;
        }
        localGuideline.setPositionRelaxed(bool);
        localGuideline.addToSolver(paramLinearSystem);
        i += 1;
      }
      while (j < m)
      {
        ((ConstraintWidget)mChildren.get(j)).addToSolver(paramLinearSystem);
        j += 1;
      }
    }
  }
  
  public void computeGuidelinesPercentPositions()
  {
    int k = mVerticalGuidelines.size();
    int j = 0;
    int i = 0;
    while (i < k)
    {
      ((Guideline)mVerticalGuidelines.get(i)).inferRelativePercentPosition();
      i += 1;
    }
    k = mHorizontalGuidelines.size();
    i = j;
    while (i < k)
    {
      ((Guideline)mHorizontalGuidelines.get(i)).inferRelativePercentPosition();
      i += 1;
    }
  }
  
  public void cycleColumnAlignment(int paramInt)
  {
    VerticalSlice localVerticalSlice = (VerticalSlice)mVerticalSlices.get(paramInt);
    switch (alignment)
    {
    default: 
      break;
    case 2: 
      alignment = 1;
      break;
    case 1: 
      alignment = 0;
      break;
    case 0: 
      alignment = 2;
    }
    setChildrenConnections();
  }
  
  public String getColumnAlignmentRepresentation(int paramInt)
  {
    VerticalSlice localVerticalSlice = (VerticalSlice)mVerticalSlices.get(paramInt);
    if (alignment == 1) {
      return "L";
    }
    if (alignment == 0) {
      return "C";
    }
    if (alignment == 3) {
      return "F";
    }
    if (alignment == 2) {
      return "R";
    }
    return "!";
  }
  
  public String getColumnsAlignmentRepresentation()
  {
    int j = mVerticalSlices.size();
    Object localObject2 = "";
    int i = 0;
    while (i < j)
    {
      VerticalSlice localVerticalSlice = (VerticalSlice)mVerticalSlices.get(i);
      Object localObject1;
      if (alignment == 1)
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append((String)localObject2);
        ((StringBuilder)localObject1).append("L");
        localObject1 = ((StringBuilder)localObject1).toString();
      }
      else if (alignment == 0)
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append((String)localObject2);
        ((StringBuilder)localObject1).append("C");
        localObject1 = ((StringBuilder)localObject1).toString();
      }
      else if (alignment == 3)
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append((String)localObject2);
        ((StringBuilder)localObject1).append("F");
        localObject1 = ((StringBuilder)localObject1).toString();
      }
      else
      {
        localObject1 = localObject2;
        if (alignment == 2)
        {
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append((String)localObject2);
          ((StringBuilder)localObject1).append("R");
          localObject1 = ((StringBuilder)localObject1).toString();
        }
      }
      i += 1;
      localObject2 = localObject1;
    }
    return localObject2;
  }
  
  public ArrayList getHorizontalGuidelines()
  {
    return mHorizontalGuidelines;
  }
  
  public int getNumCols()
  {
    return mNumCols;
  }
  
  public int getNumRows()
  {
    return mNumRows;
  }
  
  public int getPadding()
  {
    return mPadding;
  }
  
  public String getType()
  {
    return "ConstraintTableLayout";
  }
  
  public ArrayList getVerticalGuidelines()
  {
    return mVerticalGuidelines;
  }
  
  public boolean handlesInternalConstraints()
  {
    return true;
  }
  
  public boolean isVerticalGrowth()
  {
    return mVerticalGrowth;
  }
  
  public void setColumnAlignment(int paramInt1, int paramInt2)
  {
    if (paramInt1 < mVerticalSlices.size())
    {
      mVerticalSlices.get(paramInt1)).alignment = paramInt2;
      setChildrenConnections();
    }
  }
  
  public void setColumnAlignment(String paramString)
  {
    int j = paramString.length();
    int i = 0;
    while (i < j)
    {
      int k = paramString.charAt(i);
      if (k == 76) {
        setColumnAlignment(i, 1);
      } else if (k == 67) {
        setColumnAlignment(i, 0);
      } else if (k == 70) {
        setColumnAlignment(i, 3);
      } else if (k == 82) {
        setColumnAlignment(i, 2);
      } else {
        setColumnAlignment(i, 0);
      }
      i += 1;
    }
  }
  
  public void setDebugSolverName(LinearSystem paramLinearSystem, String paramString)
  {
    system = paramLinearSystem;
    super.setDebugSolverName(paramLinearSystem, paramString);
    updateDebugSolverNames();
  }
  
  public void setNumCols(int paramInt)
  {
    if ((mVerticalGrowth) && (mNumCols != paramInt))
    {
      mNumCols = paramInt;
      setVerticalSlices();
      setTableDimensions();
    }
  }
  
  public void setNumRows(int paramInt)
  {
    if ((!mVerticalGrowth) && (mNumCols != paramInt))
    {
      mNumRows = paramInt;
      setHorizontalSlices();
      setTableDimensions();
    }
  }
  
  public void setPadding(int paramInt)
  {
    if (paramInt > 1) {
      mPadding = paramInt;
    }
  }
  
  public void setTableDimensions()
  {
    int k = mChildren.size();
    int i = 0;
    int j = 0;
    while (i < k)
    {
      j += ((ConstraintWidget)mChildren.get(i)).getContainerItemSkip();
      i += 1;
    }
    k += j;
    if (mVerticalGrowth)
    {
      if (mNumCols == 0) {
        setNumCols(1);
      }
      j = k / mNumCols;
      i = j;
      if (mNumCols * j < k) {
        i = j + 1;
      }
      if ((mNumRows == i) && (mVerticalGuidelines.size() == mNumCols - 1)) {
        return;
      }
      mNumRows = i;
      setHorizontalSlices();
    }
    else
    {
      if (mNumRows == 0) {
        setNumRows(1);
      }
      j = k / mNumRows;
      i = j;
      if (mNumRows * j < k) {
        i = j + 1;
      }
      if ((mNumCols == i) && (mHorizontalGuidelines.size() == mNumRows - 1)) {
        return;
      }
      mNumCols = i;
      setVerticalSlices();
    }
    setChildrenConnections();
  }
  
  public void setVerticalGrowth(boolean paramBoolean)
  {
    mVerticalGrowth = paramBoolean;
  }
  
  public void updateFromSolver(LinearSystem paramLinearSystem)
  {
    super.updateFromSolver(paramLinearSystem);
    if (paramLinearSystem == mSystem)
    {
      int k = mVerticalGuidelines.size();
      int j = 0;
      int i = 0;
      while (i < k)
      {
        ((Guideline)mVerticalGuidelines.get(i)).updateFromSolver(paramLinearSystem);
        i += 1;
      }
      k = mHorizontalGuidelines.size();
      i = j;
      while (i < k)
      {
        ((Guideline)mHorizontalGuidelines.get(i)).updateFromSolver(paramLinearSystem);
        i += 1;
      }
    }
  }
  
  class HorizontalSlice
  {
    ConstraintWidget bottom;
    int padding;
    ConstraintWidget top;
    
    HorizontalSlice() {}
  }
  
  class VerticalSlice
  {
    int alignment = 1;
    ConstraintWidget left;
    int padding;
    ConstraintWidget right;
    
    VerticalSlice() {}
  }
}
