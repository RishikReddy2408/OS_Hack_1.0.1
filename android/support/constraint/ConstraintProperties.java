package android.support.constraint;

import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

public class ConstraintProperties
{
  public static final int BASELINE = 5;
  public static final int BOTTOM = 4;
  public static final int LEFT = 1;
  public static final int MATCH_CONSTRAINT = 0;
  public static final int MATCH_CONSTRAINT_SPREAD = 0;
  public static final int MATCH_CONSTRAINT_WRAP = 1;
  public static final int PARENT_ID = 0;
  public static final int PRIORITY_MIDHIGH = 7;
  public static final int RIGHT = 2;
  public static final int SORT_MENU_ITEM = 3;
  public static final int START = 6;
  public static final int UNSET = -1;
  public static final int WRAP_CONTENT = -2;
  ConstraintLayout.LayoutParams mParams;
  View mView;
  
  public ConstraintProperties(View paramView)
  {
    ViewGroup.LayoutParams localLayoutParams = paramView.getLayoutParams();
    if ((localLayoutParams instanceof ConstraintLayout.LayoutParams))
    {
      mParams = ((ConstraintLayout.LayoutParams)localLayoutParams);
      mView = paramView;
      return;
    }
    throw new RuntimeException("Only children of ConstraintLayout.LayoutParams supported");
  }
  
  private String sideToString(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "undefined";
    case 7: 
      return "end";
    case 6: 
      return "start";
    case 5: 
      return "baseline";
    case 4: 
      return "bottom";
    case 3: 
      return "top";
    case 2: 
      return "right";
    }
    return "left";
  }
  
  public ConstraintProperties addToHorizontalChain(int paramInt1, int paramInt2)
  {
    int i;
    if (paramInt1 == 0) {
      i = 1;
    } else {
      i = 2;
    }
    connect(1, paramInt1, i, 0);
    if (paramInt2 == 0) {
      i = 2;
    } else {
      i = 1;
    }
    connect(2, paramInt2, i, 0);
    if (paramInt1 != 0) {
      new ConstraintProperties(((ViewGroup)mView.getParent()).findViewById(paramInt1)).connect(2, mView.getId(), 1, 0);
    }
    if (paramInt2 != 0) {
      new ConstraintProperties(((ViewGroup)mView.getParent()).findViewById(paramInt2)).connect(1, mView.getId(), 2, 0);
    }
    return this;
  }
  
  public ConstraintProperties addToHorizontalChainRTL(int paramInt1, int paramInt2)
  {
    int i;
    if (paramInt1 == 0) {
      i = 6;
    } else {
      i = 7;
    }
    connect(6, paramInt1, i, 0);
    if (paramInt2 == 0) {
      i = 7;
    } else {
      i = 6;
    }
    connect(7, paramInt2, i, 0);
    if (paramInt1 != 0) {
      new ConstraintProperties(((ViewGroup)mView.getParent()).findViewById(paramInt1)).connect(7, mView.getId(), 6, 0);
    }
    if (paramInt2 != 0) {
      new ConstraintProperties(((ViewGroup)mView.getParent()).findViewById(paramInt2)).connect(6, mView.getId(), 7, 0);
    }
    return this;
  }
  
  public ConstraintProperties addToVerticalChain(int paramInt1, int paramInt2)
  {
    int i;
    if (paramInt1 == 0) {
      i = 3;
    } else {
      i = 4;
    }
    connect(3, paramInt1, i, 0);
    if (paramInt2 == 0) {
      i = 4;
    } else {
      i = 3;
    }
    connect(4, paramInt2, i, 0);
    if (paramInt1 != 0) {
      new ConstraintProperties(((ViewGroup)mView.getParent()).findViewById(paramInt1)).connect(4, mView.getId(), 3, 0);
    }
    if (paramInt2 != 0) {
      new ConstraintProperties(((ViewGroup)mView.getParent()).findViewById(paramInt2)).connect(3, mView.getId(), 4, 0);
    }
    return this;
  }
  
  public ConstraintProperties alpha(float paramFloat)
  {
    mView.setAlpha(paramFloat);
    return this;
  }
  
  public void apply() {}
  
  public ConstraintProperties center(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, float paramFloat)
  {
    if (paramInt3 >= 0)
    {
      if (paramInt6 >= 0)
      {
        if ((paramFloat > 0.0F) && (paramFloat <= 1.0F))
        {
          if ((paramInt2 != 1) && (paramInt2 != 2))
          {
            if ((paramInt2 != 6) && (paramInt2 != 7))
            {
              connect(3, paramInt1, paramInt2, paramInt3);
              connect(4, paramInt4, paramInt5, paramInt6);
              mParams.verticalBias = paramFloat;
              return this;
            }
            connect(6, paramInt1, paramInt2, paramInt3);
            connect(7, paramInt4, paramInt5, paramInt6);
            mParams.horizontalBias = paramFloat;
            return this;
          }
          connect(1, paramInt1, paramInt2, paramInt3);
          connect(2, paramInt4, paramInt5, paramInt6);
          mParams.horizontalBias = paramFloat;
          return this;
        }
        throw new IllegalArgumentException("bias must be between 0 and 1 inclusive");
      }
      throw new IllegalArgumentException("margin must be > 0");
    }
    throw new IllegalArgumentException("margin must be > 0");
  }
  
  public ConstraintProperties centerHorizontally(int paramInt)
  {
    if (paramInt == 0)
    {
      center(0, 1, 0, 0, 2, 0, 0.5F);
      return this;
    }
    center(paramInt, 2, 0, paramInt, 1, 0, 0.5F);
    return this;
  }
  
  public ConstraintProperties centerHorizontally(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, float paramFloat)
  {
    connect(1, paramInt1, paramInt2, paramInt3);
    connect(2, paramInt4, paramInt5, paramInt6);
    mParams.horizontalBias = paramFloat;
    return this;
  }
  
  public ConstraintProperties centerHorizontallyRtl(int paramInt)
  {
    if (paramInt == 0)
    {
      center(0, 6, 0, 0, 7, 0, 0.5F);
      return this;
    }
    center(paramInt, 7, 0, paramInt, 6, 0, 0.5F);
    return this;
  }
  
  public ConstraintProperties centerHorizontallyRtl(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, float paramFloat)
  {
    connect(6, paramInt1, paramInt2, paramInt3);
    connect(7, paramInt4, paramInt5, paramInt6);
    mParams.horizontalBias = paramFloat;
    return this;
  }
  
  public ConstraintProperties centerVertically(int paramInt)
  {
    if (paramInt == 0)
    {
      center(0, 3, 0, 0, 4, 0, 0.5F);
      return this;
    }
    center(paramInt, 4, 0, paramInt, 3, 0, 0.5F);
    return this;
  }
  
  public ConstraintProperties centerVertically(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, float paramFloat)
  {
    connect(3, paramInt1, paramInt2, paramInt3);
    connect(4, paramInt4, paramInt5, paramInt6);
    mParams.verticalBias = paramFloat;
    return this;
  }
  
  public ConstraintProperties connect(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    StringBuilder localStringBuilder;
    switch (paramInt1)
    {
    default: 
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(sideToString(paramInt1));
      localStringBuilder.append(" to ");
      localStringBuilder.append(sideToString(paramInt3));
      localStringBuilder.append(" unknown");
      throw new IllegalArgumentException(localStringBuilder.toString());
    case 7: 
      if (paramInt3 == 7)
      {
        mParams.endToEnd = paramInt2;
        mParams.endToStart = -1;
      }
      else
      {
        if (paramInt3 != 6) {
          break label197;
        }
        mParams.endToStart = paramInt2;
        mParams.endToEnd = -1;
      }
      if (Build.VERSION.SDK_INT >= 17)
      {
        mParams.setMarginEnd(paramInt4);
        return this;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("right to ");
        localStringBuilder.append(sideToString(paramInt3));
        localStringBuilder.append(" undefined");
        throw new IllegalArgumentException(localStringBuilder.toString());
      }
      break;
    case 6: 
      if (paramInt3 == 6)
      {
        mParams.startToStart = paramInt2;
        mParams.startToEnd = -1;
      }
      else
      {
        if (paramInt3 != 7) {
          break label312;
        }
        mParams.startToEnd = paramInt2;
        mParams.startToStart = -1;
      }
      if (Build.VERSION.SDK_INT >= 17)
      {
        mParams.setMarginStart(paramInt4);
        return this;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("right to ");
        localStringBuilder.append(sideToString(paramInt3));
        localStringBuilder.append(" undefined");
        throw new IllegalArgumentException(localStringBuilder.toString());
      }
      break;
    case 5: 
      if (paramInt3 == 5)
      {
        mParams.baselineToBaseline = paramInt2;
        mParams.bottomToBottom = -1;
        mParams.bottomToTop = -1;
        mParams.topToTop = -1;
        mParams.topToBottom = -1;
        return this;
      }
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("right to ");
      localStringBuilder.append(sideToString(paramInt3));
      localStringBuilder.append(" undefined");
      throw new IllegalArgumentException(localStringBuilder.toString());
    case 4: 
      if (paramInt3 == 4)
      {
        mParams.bottomToBottom = paramInt2;
        mParams.bottomToTop = -1;
        mParams.baselineToBaseline = -1;
      }
      else
      {
        if (paramInt3 != 3) {
          break label529;
        }
        mParams.bottomToTop = paramInt2;
        mParams.bottomToBottom = -1;
        mParams.baselineToBaseline = -1;
      }
      mParams.bottomMargin = paramInt4;
      return this;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("right to ");
      localStringBuilder.append(sideToString(paramInt3));
      localStringBuilder.append(" undefined");
      throw new IllegalArgumentException(localStringBuilder.toString());
    case 3: 
      if (paramInt3 == 3)
      {
        mParams.topToTop = paramInt2;
        mParams.topToBottom = -1;
        mParams.baselineToBaseline = -1;
      }
      else
      {
        if (paramInt3 != 4) {
          break label650;
        }
        mParams.topToBottom = paramInt2;
        mParams.topToTop = -1;
        mParams.baselineToBaseline = -1;
      }
      mParams.topMargin = paramInt4;
      return this;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("right to ");
      localStringBuilder.append(sideToString(paramInt3));
      localStringBuilder.append(" undefined");
      throw new IllegalArgumentException(localStringBuilder.toString());
    case 2: 
      if (paramInt3 == 1)
      {
        mParams.rightToLeft = paramInt2;
        mParams.rightToRight = -1;
      }
      else
      {
        if (paramInt3 != 2) {
          break label755;
        }
        mParams.rightToRight = paramInt2;
        mParams.rightToLeft = -1;
      }
      mParams.rightMargin = paramInt4;
      return this;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("right to ");
      localStringBuilder.append(sideToString(paramInt3));
      localStringBuilder.append(" undefined");
      throw new IllegalArgumentException(localStringBuilder.toString());
    case 1: 
      label197:
      label312:
      label529:
      label650:
      label755:
      if (paramInt3 == 1)
      {
        mParams.leftToLeft = paramInt2;
        mParams.leftToRight = -1;
      }
      else
      {
        if (paramInt3 != 2) {
          break label860;
        }
        mParams.leftToRight = paramInt2;
        mParams.leftToLeft = -1;
      }
      mParams.leftMargin = paramInt4;
      return this;
      label860:
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Left to ");
      localStringBuilder.append(sideToString(paramInt3));
      localStringBuilder.append(" undefined");
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    return this;
  }
  
  public ConstraintProperties constrainDefaultHeight(int paramInt)
  {
    mParams.matchConstraintDefaultHeight = paramInt;
    return this;
  }
  
  public ConstraintProperties constrainDefaultWidth(int paramInt)
  {
    mParams.matchConstraintDefaultWidth = paramInt;
    return this;
  }
  
  public ConstraintProperties constrainHeight(int paramInt)
  {
    mParams.height = paramInt;
    return this;
  }
  
  public ConstraintProperties constrainMaxHeight(int paramInt)
  {
    mParams.matchConstraintMaxHeight = paramInt;
    return this;
  }
  
  public ConstraintProperties constrainMaxWidth(int paramInt)
  {
    mParams.matchConstraintMaxWidth = paramInt;
    return this;
  }
  
  public ConstraintProperties constrainMinHeight(int paramInt)
  {
    mParams.matchConstraintMinHeight = paramInt;
    return this;
  }
  
  public ConstraintProperties constrainMinWidth(int paramInt)
  {
    mParams.matchConstraintMinWidth = paramInt;
    return this;
  }
  
  public ConstraintProperties constrainWidth(int paramInt)
  {
    mParams.width = paramInt;
    return this;
  }
  
  public ConstraintProperties dimensionRatio(String paramString)
  {
    mParams.dimensionRatio = paramString;
    return this;
  }
  
  public ConstraintProperties elevation(float paramFloat)
  {
    mView.setElevation(paramFloat);
    return this;
  }
  
  public ConstraintProperties goneMargin(int paramInt1, int paramInt2)
  {
    switch (paramInt1)
    {
    default: 
      throw new IllegalArgumentException("unknown constraint");
    case 7: 
      mParams.goneEndMargin = paramInt2;
      return this;
    case 6: 
      mParams.goneStartMargin = paramInt2;
      return this;
    case 5: 
      throw new IllegalArgumentException("baseline does not support margins");
    case 4: 
      mParams.goneBottomMargin = paramInt2;
      return this;
    case 3: 
      mParams.goneTopMargin = paramInt2;
      return this;
    case 2: 
      mParams.goneRightMargin = paramInt2;
      return this;
    }
    mParams.goneLeftMargin = paramInt2;
    return this;
  }
  
  public ConstraintProperties horizontalBias(float paramFloat)
  {
    mParams.horizontalBias = paramFloat;
    return this;
  }
  
  public ConstraintProperties horizontalChainStyle(int paramInt)
  {
    mParams.horizontalChainStyle = paramInt;
    return this;
  }
  
  public ConstraintProperties horizontalWeight(float paramFloat)
  {
    mParams.horizontalWeight = paramFloat;
    return this;
  }
  
  public ConstraintProperties margin(int paramInt1, int paramInt2)
  {
    switch (paramInt1)
    {
    default: 
      throw new IllegalArgumentException("unknown constraint");
    case 7: 
      mParams.setMarginEnd(paramInt2);
      return this;
    case 6: 
      mParams.setMarginStart(paramInt2);
      return this;
    case 5: 
      throw new IllegalArgumentException("baseline does not support margins");
    case 4: 
      mParams.bottomMargin = paramInt2;
      return this;
    case 3: 
      mParams.topMargin = paramInt2;
      return this;
    case 2: 
      mParams.rightMargin = paramInt2;
      return this;
    }
    mParams.leftMargin = paramInt2;
    return this;
  }
  
  public ConstraintProperties removeConstraints(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      throw new IllegalArgumentException("unknown constraint");
    case 7: 
      mParams.endToStart = -1;
      mParams.endToEnd = -1;
      mParams.setMarginEnd(-1);
      mParams.goneEndMargin = -1;
      return this;
    case 6: 
      mParams.startToEnd = -1;
      mParams.startToStart = -1;
      mParams.setMarginStart(-1);
      mParams.goneStartMargin = -1;
      return this;
    case 5: 
      mParams.baselineToBaseline = -1;
      return this;
    case 4: 
      mParams.bottomToTop = -1;
      mParams.bottomToBottom = -1;
      mParams.bottomMargin = -1;
      mParams.goneBottomMargin = -1;
      return this;
    case 3: 
      mParams.topToBottom = -1;
      mParams.topToTop = -1;
      mParams.topMargin = -1;
      mParams.goneTopMargin = -1;
      return this;
    case 2: 
      mParams.rightToRight = -1;
      mParams.rightToLeft = -1;
      mParams.rightMargin = -1;
      mParams.goneRightMargin = -1;
      return this;
    }
    mParams.leftToRight = -1;
    mParams.leftToLeft = -1;
    mParams.leftMargin = -1;
    mParams.goneLeftMargin = -1;
    return this;
  }
  
  public ConstraintProperties removeFromHorizontalChain()
  {
    int i = mParams.leftToRight;
    Object localObject1 = mParams;
    ConstraintProperties localConstraintProperties = this;
    int j = rightToLeft;
    if ((i == -1) && (j == -1))
    {
      j = mParams.startToEnd;
      localObject1 = mParams;
      int k = endToStart;
      if ((j != -1) || (k != -1))
      {
        localObject2 = new ConstraintProperties(((ViewGroup)mView.getParent()).findViewById(j));
        localObject1 = new ConstraintProperties(((ViewGroup)mView.getParent()).findViewById(k));
        if ((j != -1) && (k != -1))
        {
          ((ConstraintProperties)localObject2).connect(7, k, 6, 0);
          ((ConstraintProperties)localObject1).connect(6, i, 7, 0);
        }
        else if ((i != -1) || (k != -1))
        {
          if (mParams.rightToRight != -1)
          {
            ((ConstraintProperties)localObject2).connect(7, mParams.rightToRight, 7, 0);
          }
          else
          {
            localObject2 = mParams;
            if (leftToLeft != -1) {
              ((ConstraintProperties)localObject1).connect(6, mParams.leftToLeft, 6, 0);
            }
          }
        }
      }
      localConstraintProperties = this;
      localConstraintProperties.removeConstraints(6);
      localConstraintProperties.removeConstraints(7);
      return localConstraintProperties;
    }
    Object localObject2 = new ConstraintProperties(((ViewGroup)mView.getParent()).findViewById(i));
    localObject1 = new ConstraintProperties(((ViewGroup)mView.getParent()).findViewById(j));
    if ((i != -1) && (j != -1))
    {
      ((ConstraintProperties)localObject2).connect(2, j, 1, 0);
      ((ConstraintProperties)localObject1).connect(1, i, 2, 0);
    }
    else if ((i != -1) || (j != -1))
    {
      if (mParams.rightToRight != -1)
      {
        ((ConstraintProperties)localObject2).connect(2, mParams.rightToRight, 2, 0);
      }
      else
      {
        localObject2 = mParams;
        if (leftToLeft != -1) {
          ((ConstraintProperties)localObject1).connect(1, mParams.leftToLeft, 1, 0);
        }
      }
    }
    localConstraintProperties = this;
    localConstraintProperties.removeConstraints(1);
    localConstraintProperties.removeConstraints(2);
    return localConstraintProperties;
  }
  
  public ConstraintProperties removeFromVerticalChain()
  {
    int i = mParams.topToBottom;
    int j = mParams.bottomToTop;
    if ((i != -1) || (j != -1))
    {
      ConstraintProperties localConstraintProperties1 = new ConstraintProperties(((ViewGroup)mView.getParent()).findViewById(i));
      ConstraintProperties localConstraintProperties2 = new ConstraintProperties(((ViewGroup)mView.getParent()).findViewById(j));
      if ((i != -1) && (j != -1))
      {
        localConstraintProperties1.connect(4, j, 3, 0);
        localConstraintProperties2.connect(3, i, 4, 0);
      }
      else if ((i != -1) || (j != -1))
      {
        if (mParams.bottomToBottom != -1) {
          localConstraintProperties1.connect(4, mParams.bottomToBottom, 4, 0);
        } else if (mParams.topToTop != -1) {
          localConstraintProperties2.connect(3, mParams.topToTop, 3, 0);
        }
      }
    }
    removeConstraints(3);
    removeConstraints(4);
    return this;
  }
  
  public ConstraintProperties rotation(float paramFloat)
  {
    mView.setRotation(paramFloat);
    return this;
  }
  
  public ConstraintProperties rotationX(float paramFloat)
  {
    mView.setRotationX(paramFloat);
    return this;
  }
  
  public ConstraintProperties rotationY(float paramFloat)
  {
    mView.setRotationY(paramFloat);
    return this;
  }
  
  public ConstraintProperties scaleX(float paramFloat)
  {
    mView.setScaleY(paramFloat);
    return this;
  }
  
  public ConstraintProperties scaleY(float paramFloat)
  {
    return this;
  }
  
  public ConstraintProperties transformPivot(float paramFloat1, float paramFloat2)
  {
    mView.setPivotX(paramFloat1);
    mView.setPivotY(paramFloat2);
    return this;
  }
  
  public ConstraintProperties transformPivotX(float paramFloat)
  {
    mView.setPivotX(paramFloat);
    return this;
  }
  
  public ConstraintProperties transformPivotY(float paramFloat)
  {
    mView.setPivotY(paramFloat);
    return this;
  }
  
  public ConstraintProperties translation(float paramFloat1, float paramFloat2)
  {
    mView.setTranslationX(paramFloat1);
    mView.setTranslationY(paramFloat2);
    return this;
  }
  
  public ConstraintProperties translationX(float paramFloat)
  {
    mView.setTranslationX(paramFloat);
    return this;
  }
  
  public ConstraintProperties translationY(float paramFloat)
  {
    mView.setTranslationY(paramFloat);
    return this;
  }
  
  public ConstraintProperties translationZ(float paramFloat)
  {
    mView.setTranslationZ(paramFloat);
    return this;
  }
  
  public ConstraintProperties verticalBias(float paramFloat)
  {
    mParams.verticalBias = paramFloat;
    return this;
  }
  
  public ConstraintProperties verticalChainStyle(int paramInt)
  {
    mParams.verticalChainStyle = paramInt;
    return this;
  }
  
  public ConstraintProperties verticalWeight(float paramFloat)
  {
    mParams.verticalWeight = paramFloat;
    return this;
  }
  
  public ConstraintProperties visibility(int paramInt)
  {
    mView.setVisibility(paramInt);
    return this;
  }
}
