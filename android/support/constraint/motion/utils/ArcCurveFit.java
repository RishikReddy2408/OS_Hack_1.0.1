package android.support.constraint.motion.utils;

import java.util.Arrays;

class ArcCurveFit
  extends CurveFit
{
  public static final int ARC_START_FLIP = 3;
  public static final int ARC_START_HORIZONTAL = 2;
  public static final int ARC_START_LINEAR = 0;
  public static final int ARC_START_VERTICAL = 1;
  private static final int START_HORIZONTAL = 2;
  private static final int START_LINEAR = 3;
  private static final int START_VERTICAL = 1;
  Arc[] mArcs;
  private final double[] mTime;
  
  public ArcCurveFit(int[] paramArrayOfInt, double[] paramArrayOfDouble, double[][] paramArrayOfDouble1)
  {
    mTime = paramArrayOfDouble;
    mArcs = new Arc[paramArrayOfDouble.length - 1];
    int k = 0;
    int j = 1;
    int i = 1;
    while (k < mArcs.length)
    {
      int n = paramArrayOfInt[k];
      int m = 2;
      switch (n)
      {
      default: 
        break;
      case 3: 
        if (j == 1) {
          i = m;
        } else {
          i = 1;
        }
        j = i;
        break;
      case 2: 
        j = 2;
        i = 2;
        break;
      case 1: 
        j = 1;
        i = 1;
        break;
      case 0: 
        i = 3;
      }
      Arc[] arrayOfArc = mArcs;
      double d = paramArrayOfDouble[k];
      m = k + 1;
      arrayOfArc[k] = new Arc(i, d, paramArrayOfDouble[m], paramArrayOfDouble1[k][0], paramArrayOfDouble1[k][1], paramArrayOfDouble1[m][0], paramArrayOfDouble1[m][1]);
      k = m;
    }
  }
  
  public double getPos(double paramDouble, int paramInt)
  {
    Arc[] arrayOfArc = mArcs;
    int j = 0;
    double d;
    int i;
    if (paramDouble < 0mTime1)
    {
      d = mArcs[0].mTime1;
      i = j;
    }
    else
    {
      i = j;
      d = paramDouble;
      if (paramDouble > mArcs[(mArcs.length - 1)].mTime2)
      {
        d = mArcs[(mArcs.length - 1)].mTime2;
        i = j;
      }
    }
    while (i < mArcs.length)
    {
      if (d <= mArcs[i].mTime2)
      {
        if (mArcs[i].linear)
        {
          if (paramInt == 0) {
            return mArcs[i].getLinearX(d);
          }
          return mArcs[i].getLinearY(d);
        }
        mArcs[i].setPoint(d);
        if (paramInt == 0) {
          return mArcs[i].getX();
        }
        return mArcs[i].getY();
      }
      i += 1;
    }
    return NaN.0D;
  }
  
  public void getPos(double paramDouble, double[] paramArrayOfDouble)
  {
    double d = paramDouble;
    if (paramDouble < mArcs[0].mTime1) {
      d = mArcs[0].mTime1;
    }
    paramDouble = d;
    if (d > mArcs[(mArcs.length - 1)].mTime2) {
      paramDouble = mArcs[(mArcs.length - 1)].mTime2;
    }
    int i = 0;
    while (i < mArcs.length)
    {
      if (paramDouble <= mArcs[i].mTime2)
      {
        if (mArcs[i].linear)
        {
          paramArrayOfDouble[0] = mArcs[i].getLinearX(paramDouble);
          paramArrayOfDouble[1] = mArcs[i].getLinearY(paramDouble);
          return;
        }
        mArcs[i].setPoint(paramDouble);
        paramArrayOfDouble[0] = mArcs[i].getX();
        paramArrayOfDouble[1] = mArcs[i].getY();
        return;
      }
      i += 1;
    }
  }
  
  public void getPos(double paramDouble, float[] paramArrayOfFloat)
  {
    double d;
    if (paramDouble < mArcs[0].mTime1)
    {
      d = mArcs[0].mTime1;
    }
    else
    {
      d = paramDouble;
      if (paramDouble > mArcs[(mArcs.length - 1)].mTime2) {
        d = mArcs[(mArcs.length - 1)].mTime2;
      }
    }
    int i = 0;
    while (i < mArcs.length)
    {
      if (d <= mArcs[i].mTime2)
      {
        if (mArcs[i].linear)
        {
          paramArrayOfFloat[0] = ((float)mArcs[i].getLinearX(d));
          paramArrayOfFloat[1] = ((float)mArcs[i].getLinearY(d));
          return;
        }
        mArcs[i].setPoint(d);
        paramArrayOfFloat[0] = ((float)mArcs[i].getX());
        paramArrayOfFloat[1] = ((float)mArcs[i].getY());
        return;
      }
      i += 1;
    }
  }
  
  public double getSlope(double paramDouble, int paramInt)
  {
    Arc[] arrayOfArc = mArcs;
    int j = 0;
    double d = paramDouble;
    if (paramDouble < 0mTime1) {
      d = mArcs[0].mTime1;
    }
    int i = j;
    paramDouble = d;
    if (d > mArcs[(mArcs.length - 1)].mTime2)
    {
      paramDouble = mArcs[(mArcs.length - 1)].mTime2;
      i = j;
    }
    while (i < mArcs.length)
    {
      if (paramDouble <= mArcs[i].mTime2)
      {
        if (mArcs[i].linear)
        {
          if (paramInt == 0) {
            return mArcs[i].getLinearDX(paramDouble);
          }
          return mArcs[i].getLinearDY(paramDouble);
        }
        mArcs[i].setPoint(paramDouble);
        if (paramInt == 0) {
          return mArcs[i].getDX();
        }
        return mArcs[i].getDY();
      }
      i += 1;
    }
    return NaN.0D;
  }
  
  public void getSlope(double paramDouble, double[] paramArrayOfDouble)
  {
    double d;
    if (paramDouble < mArcs[0].mTime1)
    {
      d = mArcs[0].mTime1;
    }
    else
    {
      d = paramDouble;
      if (paramDouble > mArcs[(mArcs.length - 1)].mTime2) {
        d = mArcs[(mArcs.length - 1)].mTime2;
      }
    }
    int i = 0;
    while (i < mArcs.length)
    {
      if (d <= mArcs[i].mTime2)
      {
        if (mArcs[i].linear)
        {
          paramArrayOfDouble[0] = mArcs[i].getLinearDX(d);
          paramArrayOfDouble[1] = mArcs[i].getLinearDY(d);
          return;
        }
        mArcs[i].setPoint(d);
        paramArrayOfDouble[0] = mArcs[i].getDX();
        paramArrayOfDouble[1] = mArcs[i].getDY();
        return;
      }
      i += 1;
    }
  }
  
  public double[] getTimePoints()
  {
    return mTime;
  }
  
  private static class Arc
  {
    private static final double EPSILON = 0.001D;
    private static final String PAGE_KEY = "Arc";
    private static double[] ourPercent = new double[91];
    double board;
    double latitude;
    boolean linear;
    double longitude;
    double mArcDistance;
    double mArcVelocity;
    double mEllipseA;
    double mEllipseB;
    double mEllipseCenterX;
    double mEllipseCenterY;
    double[] mLut;
    double mOneOverDeltaTime;
    double mTime1;
    double mTime2;
    double mTmpCosAngle;
    double mTmpSinAngle;
    boolean mVertical;
    double resto;
    
    Arc(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6)
    {
      boolean bool = false;
      linear = false;
      if (paramInt == 1) {
        bool = true;
      }
      mVertical = bool;
      mTime1 = paramDouble1;
      mTime2 = paramDouble2;
      mOneOverDeltaTime = (1.0D / (mTime2 - mTime1));
      if (3 == paramInt) {
        linear = true;
      }
      paramDouble2 = paramDouble5 - paramDouble3;
      paramDouble1 = paramDouble6 - paramDouble4;
      if ((!linear) && (Math.abs(paramDouble2) >= 0.001D) && (Math.abs(paramDouble1) >= 0.001D))
      {
        mLut = new double[101];
        if (mVertical) {
          paramInt = -1;
        } else {
          paramInt = 1;
        }
        double d = paramInt;
        Double.isNaN(d);
        mEllipseA = (paramDouble2 * d);
        if (mVertical) {
          paramInt = 1;
        } else {
          paramInt = -1;
        }
        paramDouble2 = paramInt;
        Double.isNaN(paramDouble2);
        mEllipseB = (paramDouble1 * paramDouble2);
        if (mVertical) {
          paramDouble1 = paramDouble5;
        } else {
          paramDouble1 = paramDouble3;
        }
        mEllipseCenterX = paramDouble1;
        if (mVertical) {
          paramDouble1 = paramDouble4;
        } else {
          paramDouble1 = paramDouble6;
        }
        mEllipseCenterY = paramDouble1;
        buildTable(paramDouble3, paramDouble4, paramDouble5, paramDouble6);
        mArcVelocity = (mArcDistance * mOneOverDeltaTime);
        return;
      }
      linear = true;
      longitude = paramDouble3;
      latitude = paramDouble5;
      board = paramDouble4;
      resto = paramDouble6;
      mArcDistance = Math.hypot(paramDouble1, paramDouble2);
      mArcVelocity = (mArcDistance * mOneOverDeltaTime);
      mEllipseCenterX = (paramDouble2 / (mTime2 - mTime1));
      mEllipseCenterY = (paramDouble1 / (mTime2 - mTime1));
    }
    
    private void buildTable(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
    {
      int i = 0;
      double d1 = 0.0D;
      double d3 = 0.0D;
      double d2 = 0.0D;
      while (i < ourPercent.length)
      {
        double d4 = i;
        Double.isNaN(d4);
        double d5 = ourPercent.length - 1;
        Double.isNaN(d5);
        d5 = Math.toRadians(d4 * 90.0D / d5);
        d4 = Math.sin(d5);
        double d6 = Math.cos(d5);
        d5 = d4 * (paramDouble3 - paramDouble1);
        d6 *= (paramDouble2 - paramDouble4);
        d4 = d1;
        if (i > 0)
        {
          d4 = d1 + Math.hypot(d5 - d3, d6 - d2);
          ourPercent[i] = d4;
        }
        i += 1;
        d2 = d6;
        d3 = d5;
        d1 = d4;
      }
      mArcDistance = d1;
      i = 0;
      while (i < ourPercent.length)
      {
        double[] arrayOfDouble = ourPercent;
        arrayOfDouble[i] /= d1;
        i += 1;
      }
      i = 0;
      while (i < mLut.length)
      {
        paramDouble1 = i;
        paramDouble2 = mLut.length - 1;
        Double.isNaN(paramDouble1);
        Double.isNaN(paramDouble2);
        paramDouble2 = paramDouble1 / paramDouble2;
        int j = Arrays.binarySearch(ourPercent, paramDouble2);
        if (j >= 0)
        {
          mLut[i] = (j / (ourPercent.length - 1));
        }
        else if (j == -1)
        {
          mLut[i] = 0.0D;
        }
        else
        {
          j = -j;
          int k = j - 2;
          paramDouble1 = k;
          paramDouble2 = (paramDouble2 - ourPercent[k]) / (ourPercent[(j - 1)] - ourPercent[k]);
          Double.isNaN(paramDouble1);
          paramDouble3 = ourPercent.length - 1;
          Double.isNaN(paramDouble3);
          paramDouble1 = (paramDouble1 + paramDouble2) / paramDouble3;
          mLut[i] = paramDouble1;
        }
        i += 1;
      }
    }
    
    double getDX()
    {
      double d2 = mEllipseA * mTmpCosAngle;
      double d1 = -mEllipseB;
      double d3 = mTmpSinAngle;
      d3 = mArcVelocity / Math.hypot(d2, d1 * d3);
      d1 = d2;
      if (mVertical) {
        d1 = -d2;
      }
      return d1 * d3;
    }
    
    double getDY()
    {
      double d2 = mEllipseA;
      double d3 = mTmpCosAngle;
      double d1 = -mEllipseB * mTmpSinAngle;
      d2 = mArcVelocity / Math.hypot(d2 * d3, d1);
      if (mVertical) {
        return -d1 * d2;
      }
      return d1 * d2;
    }
    
    public double getLinearDX(double paramDouble)
    {
      return mEllipseCenterX;
    }
    
    public double getLinearDY(double paramDouble)
    {
      return mEllipseCenterY;
    }
    
    public double getLinearX(double paramDouble)
    {
      double d1 = mTime1;
      double d2 = mOneOverDeltaTime;
      return longitude + (paramDouble - d1) * d2 * (latitude - longitude);
    }
    
    public double getLinearY(double paramDouble)
    {
      double d1 = mTime1;
      double d2 = mOneOverDeltaTime;
      return board + (paramDouble - d1) * d2 * (resto - board);
    }
    
    double getX()
    {
      return mEllipseCenterX + mEllipseA * mTmpSinAngle;
    }
    
    double getY()
    {
      return mEllipseCenterY + mEllipseB * mTmpCosAngle;
    }
    
    double lookup(double paramDouble)
    {
      if (paramDouble <= 0.0D) {
        return 0.0D;
      }
      if (paramDouble >= 1.0D) {
        return 1.0D;
      }
      double d = mLut.length - 1;
      Double.isNaN(d);
      paramDouble *= d;
      int i = (int)paramDouble;
      d = i;
      Double.isNaN(d);
      return mLut[i] + (paramDouble - d) * (mLut[(i + 1)] - mLut[i]);
    }
    
    void setPoint(double paramDouble)
    {
      if (mVertical) {
        paramDouble = mTime2 - paramDouble;
      } else {
        paramDouble -= mTime1;
      }
      paramDouble = lookup(paramDouble * mOneOverDeltaTime) * 1.5707963267948966D;
      mTmpSinAngle = Math.sin(paramDouble);
      mTmpCosAngle = Math.cos(paramDouble);
    }
  }
}
