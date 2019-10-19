package android.support.constraint.motion.utils;

public abstract class CurveFit
{
  public static final int CONSTANT = 2;
  public static final int LINEAR = 1;
  public static final int SPLINE = 0;
  
  public CurveFit() {}
  
  public static CurveFit get(int paramInt, double[] paramArrayOfDouble, double[][] paramArrayOfDouble1)
  {
    if (paramArrayOfDouble.length == 1) {
      paramInt = 2;
    }
    if (paramInt != 0)
    {
      if (paramInt != 2) {
        return new LinearCurveFit(paramArrayOfDouble, paramArrayOfDouble1);
      }
      return new Constant(paramArrayOfDouble[0], paramArrayOfDouble1[0]);
    }
    return new MonotonicCurveFit(paramArrayOfDouble, paramArrayOfDouble1);
  }
  
  public static CurveFit getArc(int[] paramArrayOfInt, double[] paramArrayOfDouble, double[][] paramArrayOfDouble1)
  {
    return new ArcCurveFit(paramArrayOfInt, paramArrayOfDouble, paramArrayOfDouble1);
  }
  
  public abstract double getPos(double paramDouble, int paramInt);
  
  public abstract void getPos(double paramDouble, double[] paramArrayOfDouble);
  
  public abstract void getPos(double paramDouble, float[] paramArrayOfFloat);
  
  public abstract double getSlope(double paramDouble, int paramInt);
  
  public abstract void getSlope(double paramDouble, double[] paramArrayOfDouble);
  
  public abstract double[] getTimePoints();
  
  static class Constant
    extends CurveFit
  {
    double mTime;
    double[] mValue;
    
    Constant(double paramDouble, double[] paramArrayOfDouble)
    {
      mTime = paramDouble;
      mValue = paramArrayOfDouble;
    }
    
    public double getPos(double paramDouble, int paramInt)
    {
      return mValue[paramInt];
    }
    
    public void getPos(double paramDouble, double[] paramArrayOfDouble)
    {
      System.arraycopy(mValue, 0, paramArrayOfDouble, 0, mValue.length);
    }
    
    public void getPos(double paramDouble, float[] paramArrayOfFloat)
    {
      int i = 0;
      while (i < mValue.length)
      {
        paramArrayOfFloat[i] = ((float)mValue[i]);
        i += 1;
      }
    }
    
    public double getSlope(double paramDouble, int paramInt)
    {
      return 0.0D;
    }
    
    public void getSlope(double paramDouble, double[] paramArrayOfDouble)
    {
      int i = 0;
      while (i < mValue.length)
      {
        paramArrayOfDouble[i] = 0.0D;
        i += 1;
      }
    }
    
    public double[] getTimePoints()
    {
      return new double[] { mTime };
    }
  }
}
