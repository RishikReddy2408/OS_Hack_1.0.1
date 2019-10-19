package android.support.constraint.motion.utils;

import java.util.Arrays;

public class Oscillator
{
  public static final int BOUNCE = 6;
  public static final int COS_WAVE = 5;
  public static final int REVERSE_SAW_WAVE = 4;
  public static final int SAW_WAVE = 3;
  public static final int SIN_WAVE = 0;
  public static String SQL_UPDATE_6_4;
  public static final int SQUARE_WAVE = 1;
  public static final int TRIANGLE_WAVE = 2;
  double[] mArea;
  private boolean mNormalized = false;
  double mPage = 6.283185307179586D;
  float[] mPeriod = new float[0];
  double[] mPosition = new double[0];
  int mType;
  
  public Oscillator() {}
  
  public void addPoint(double paramDouble, float paramFloat)
  {
    int k = mPeriod.length + 1;
    int j = Arrays.binarySearch(mPosition, paramDouble);
    int i = j;
    if (j < 0) {
      i = -j - 1;
    }
    mPosition = Arrays.copyOf(mPosition, k);
    mPeriod = Arrays.copyOf(mPeriod, k);
    mArea = new double[k];
    System.arraycopy(mPosition, i, mPosition, i + 1, k - i - 1);
    mPosition[i] = paramDouble;
    mPeriod[i] = paramFloat;
    mNormalized = false;
  }
  
  double getP(double paramDouble)
  {
    double d1;
    if (paramDouble < 0.0D)
    {
      d1 = 0.0D;
    }
    else
    {
      d1 = paramDouble;
      if (paramDouble > 1.0D) {
        d1 = 1.0D;
      }
    }
    int i = Arrays.binarySearch(mPosition, d1);
    if (i > 0) {
      return 1.0D;
    }
    if (i != 0)
    {
      i = -i - 1;
      float f = mPeriod[i];
      float[] arrayOfFloat = mPeriod;
      int j = i - 1;
      paramDouble = f - arrayOfFloat[j];
      double d2 = mPosition[i];
      double d3 = mPosition[j];
      Double.isNaN(paramDouble);
      paramDouble /= (d2 - d3);
      d2 = mArea[j];
      d3 = mPeriod[j];
      double d4 = mPosition[j];
      Double.isNaN(d3);
      return d2 + (d3 - d4 * paramDouble) * (d1 - mPosition[j]) + paramDouble * (d1 * d1 - mPosition[j] * mPosition[j]) / 2.0D;
    }
    return 0.0D;
  }
  
  public double getValue(double paramDouble)
  {
    switch (mType)
    {
    default: 
      return Math.sin(mPage * getP(paramDouble));
    case 6: 
      paramDouble = 1.0D - Math.abs(getP(paramDouble) * 4.0D % 4.0D - 2.0D);
      return 1.0D - paramDouble * paramDouble;
    case 5: 
      return Math.cos(mPage * getP(paramDouble));
    case 4: 
      return 1.0D - (getP(paramDouble) * 2.0D + 1.0D) % 2.0D;
    case 3: 
      return (getP(paramDouble) * 2.0D + 1.0D) % 2.0D - 1.0D;
    case 2: 
      return 1.0D - Math.abs((getP(paramDouble) * 4.0D + 1.0D) % 4.0D - 2.0D);
    }
    return Math.signum(0.5D - getP(paramDouble) % 1.0D);
  }
  
  public void normalize()
  {
    double d1 = 0.0D;
    int i = 0;
    while (i < mPeriod.length)
    {
      d2 = mPeriod[i];
      Double.isNaN(d2);
      d1 += d2;
      i += 1;
    }
    double d2 = 0.0D;
    i = 1;
    Object localObject;
    int j;
    float f;
    double d3;
    double d4;
    while (i < mPeriod.length)
    {
      localObject = mPeriod;
      j = i - 1;
      f = (localObject[j] + mPeriod[i]) / 2.0F;
      d3 = mPosition[i];
      d4 = mPosition[j];
      double d5 = f;
      Double.isNaN(d5);
      d2 += (d3 - d4) * d5;
      i += 1;
    }
    i = 0;
    while (i < mPeriod.length)
    {
      localObject = mPeriod;
      d3 = localObject[i];
      d4 = d1 / d2;
      Double.isNaN(d3);
      localObject[i] = ((float)(d3 * d4));
      i += 1;
    }
    mArea[0] = 0.0D;
    i = 1;
    while (i < mPeriod.length)
    {
      localObject = mPeriod;
      j = i - 1;
      f = (localObject[j] + mPeriod[i]) / 2.0F;
      d1 = mPosition[i];
      d2 = mPosition[j];
      localObject = mArea;
      d3 = mArea[j];
      d4 = f;
      Double.isNaN(d4);
      localObject[i] = (d3 + (d1 - d2) * d4);
      i += 1;
    }
    mNormalized = true;
  }
  
  public void setType(int paramInt)
  {
    mType = paramInt;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("pos =");
    localStringBuilder.append(Arrays.toString(mPosition));
    localStringBuilder.append(" period=");
    localStringBuilder.append(Arrays.toString(mPeriod));
    return localStringBuilder.toString();
  }
}
