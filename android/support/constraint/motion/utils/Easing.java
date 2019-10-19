package android.support.constraint.motion.utils;

import android.util.Log;
import java.util.Arrays;

public class Easing
{
  private static final String ACCELERATE = "cubic(0.4, 0.05, 0.8, 0.7)";
  private static final String ACCELERATE_NAME = "accelerate";
  private static final String DECELERATE = "cubic(0.0, 0.0, 0.2, 0.95)";
  private static final String DECELERATE_NAME = "decelerate";
  private static final String LINEAR = "cubic(1, 1, 0, 0)";
  private static final String LINEAR_NAME = "linear";
  public static String[] NAMED_EASING = { "standard", "accelerate", "decelerate", "linear" };
  private static final String STANDARD = "cubic(0.4, 0.0, 0.2, 1)";
  private static final String STANDARD_NAME = "standard";
  static Easing sDefault = new Easing();
  String symbol = "identity";
  
  public Easing() {}
  
  public static Easing getInterpolator(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    if (paramString.startsWith("cubic")) {
      return new CubicEasing(paramString);
    }
    int i = -1;
    int j = paramString.hashCode();
    if (j != -1354466595)
    {
      if (j != -1263948740)
      {
        if (j != -1102672091)
        {
          if ((j == 1312628413) && (paramString.equals("standard"))) {
            i = 0;
          }
        }
        else if (paramString.equals("linear")) {
          i = 3;
        }
      }
      else if (paramString.equals("decelerate")) {
        i = 2;
      }
    }
    else if (paramString.equals("accelerate")) {
      i = 1;
    }
    switch (i)
    {
    default: 
      paramString = new StringBuilder();
      paramString.append("transitionEasing syntax error syntax:transitionEasing=\"cubic(1.0,0.5,0.0,0.6)\" or ");
      paramString.append(Arrays.toString(NAMED_EASING));
      Log.e("ConstraintSet", paramString.toString());
      return sDefault;
    case 3: 
      return new CubicEasing("cubic(1, 1, 0, 0)");
    case 2: 
      return new CubicEasing("cubic(0.0, 0.0, 0.2, 0.95)");
    case 1: 
      return new CubicEasing("cubic(0.4, 0.05, 0.8, 0.7)");
    }
    return new CubicEasing("cubic(0.4, 0.0, 0.2, 1)");
  }
  
  public double getDiff(double paramDouble)
  {
    return 1.0D;
  }
  
  public String toString()
  {
    return symbol;
  }
  
  public double transform(double paramDouble)
  {
    return paramDouble;
  }
  
  static class CubicEasing
    extends Easing
  {
    private static double d_error;
    private static double error;
    double maxX;
    double maxY;
    double x;
    double y;
    
    public CubicEasing(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
    {
      setup(paramDouble1, paramDouble2, paramDouble3, paramDouble4);
    }
    
    CubicEasing(String paramString)
    {
      symbol = paramString;
      int i = paramString.indexOf('(');
      int j = paramString.indexOf(',', i);
      x = Double.parseDouble(paramString.substring(i + 1, j).trim());
      i = j + 1;
      j = paramString.indexOf(',', i);
      y = Double.parseDouble(paramString.substring(i, j).trim());
      i = j + 1;
      j = paramString.indexOf(',', i);
      maxX = Double.parseDouble(paramString.substring(i, j).trim());
      i = j + 1;
      maxY = Double.parseDouble(paramString.substring(i, paramString.indexOf(')', i)).trim());
    }
    
    private double getDiffX(double paramDouble)
    {
      double d = 1.0D - paramDouble;
      return d * 3.0D * d * x + d * 6.0D * paramDouble * (maxX - x) + 3.0D * paramDouble * paramDouble * (1.0D - maxX);
    }
    
    private double getDiffY(double paramDouble)
    {
      double d = 1.0D - paramDouble;
      return d * 3.0D * d * y + d * 6.0D * paramDouble * (maxY - y) + 3.0D * paramDouble * paramDouble * (1.0D - maxY);
    }
    
    private double getX(double paramDouble)
    {
      double d1 = 1.0D - paramDouble;
      double d2 = 3.0D * d1;
      return x * (d1 * d2 * paramDouble) + maxX * (d2 * paramDouble * paramDouble) + paramDouble * paramDouble * paramDouble;
    }
    
    private double getY(double paramDouble)
    {
      double d1 = 1.0D - paramDouble;
      double d2 = 3.0D * d1;
      return y * (d1 * d2 * paramDouble) + maxY * (d2 * paramDouble * paramDouble) + paramDouble * paramDouble * paramDouble;
    }
    
    public double getDiff(double paramDouble)
    {
      double d2 = 0.5D;
      double d1 = 0.5D;
      while (d2 > d_error)
      {
        d3 = getX(d1);
        d2 *= 0.5D;
        if (d3 < paramDouble) {
          d1 += d2;
        } else {
          d1 -= d2;
        }
      }
      double d3 = d1 - d2;
      paramDouble = getX(d3);
      d1 += d2;
      d2 = getX(d1);
      d3 = getY(d3);
      return (getY(d1) - d3) / (d2 - paramDouble);
    }
    
    void setup(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
    {
      x = paramDouble1;
      y = paramDouble2;
      maxX = paramDouble3;
      maxY = paramDouble4;
    }
    
    public double transform(double paramDouble)
    {
      if (paramDouble <= 0.0D) {
        return 0.0D;
      }
      if (paramDouble >= 1.0D) {
        return 1.0D;
      }
      double d2 = 0.5D;
      double d1 = 0.5D;
      while (d2 > error)
      {
        d3 = getX(d1);
        d2 *= 0.5D;
        if (d3 < paramDouble) {
          d1 += d2;
        } else {
          d1 -= d2;
        }
      }
      double d4 = d1 - d2;
      double d3 = getX(d4);
      d1 += d2;
      d2 = getX(d1);
      d4 = getY(d4);
      return (getY(d1) - d4) * (paramDouble - d3) / (d2 - d3) + d4;
    }
  }
}
