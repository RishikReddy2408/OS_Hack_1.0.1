package android.support.constraint.motion.utils;

import D;
import java.lang.reflect.Array;

public class MonotonicCurveFit
  extends CurveFit
{
  private static final String PAGE_KEY = "MonotonicCurveFit";
  private double[] data;
  private double[][] mTangent;
  private double[][] pos;
  
  public MonotonicCurveFit(double[] paramArrayOfDouble, double[][] paramArrayOfDouble1)
  {
    int i1 = paramArrayOfDouble.length;
    int m = paramArrayOfDouble1[0].length;
    int n = i1 - 1;
    double[][] arrayOfDouble1 = (double[][])Array.newInstance(D.class, new int[] { n, m });
    double[][] arrayOfDouble2 = (double[][])Array.newInstance(D.class, new int[] { i1, m });
    int i = 0;
    int j;
    int k;
    double d1;
    double d2;
    while (i < m)
    {
      for (j = 0; j < n; j = k)
      {
        k = j + 1;
        d1 = paramArrayOfDouble[k];
        d2 = paramArrayOfDouble[j];
        arrayOfDouble1[j][i] = ((paramArrayOfDouble1[k][i] - paramArrayOfDouble1[j][i]) / (d1 - d2));
        if (j == 0) {
          arrayOfDouble2[j][i] = arrayOfDouble1[j][i];
        } else {
          arrayOfDouble2[j][i] = ((arrayOfDouble1[(j - 1)][i] + arrayOfDouble1[j][i]) * 0.5D);
        }
      }
      arrayOfDouble2[n][i] = arrayOfDouble1[(i1 - 2)][i];
      i += 1;
    }
    i = 0;
    while (i < n)
    {
      j = 0;
      while (j < m)
      {
        if (arrayOfDouble1[i][j] == 0.0D)
        {
          arrayOfDouble2[i][j] = 0L;
          arrayOfDouble2[(i + 1)][j] = 0L;
        }
        else
        {
          d1 = arrayOfDouble2[i][j] / arrayOfDouble1[i][j];
          k = i + 1;
          d2 = arrayOfDouble2[k][j] / arrayOfDouble1[i][j];
          double d3 = Math.hypot(d1, d2);
          if (d3 > 9.0D)
          {
            d3 = 3.0D / d3;
            arrayOfDouble2[i][j] = (d1 * d3 * arrayOfDouble1[i][j]);
            arrayOfDouble2[k][j] = (d3 * d2 * arrayOfDouble1[i][j]);
          }
        }
        j += 1;
      }
      i += 1;
    }
    data = paramArrayOfDouble;
    pos = paramArrayOfDouble1;
    mTangent = arrayOfDouble2;
  }
  
  private static double diff(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6)
  {
    double d1 = paramDouble2 * paramDouble2;
    double d2 = paramDouble2 * 6.0D;
    double d3 = 3.0D * paramDouble1;
    return -6.0D * d1 * paramDouble4 + d2 * paramDouble4 + 6.0D * d1 * paramDouble3 - d2 * paramDouble3 + d3 * paramDouble6 * d1 + d3 * paramDouble5 * d1 - 2.0D * paramDouble1 * paramDouble6 * paramDouble2 - 4.0D * paramDouble1 * paramDouble5 * paramDouble2 + paramDouble1 * paramDouble5;
  }
  
  private static double interpolate(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6)
  {
    double d1 = paramDouble2 * paramDouble2;
    double d2 = d1 * paramDouble2;
    double d3 = 3.0D * d1;
    paramDouble6 = paramDouble1 * paramDouble6;
    double d4 = paramDouble1 * paramDouble5;
    return -2.0D * d2 * paramDouble4 + d3 * paramDouble4 + d2 * 2.0D * paramDouble3 - d3 * paramDouble3 + paramDouble3 + paramDouble6 * d2 + d2 * d4 - paramDouble6 * d1 - paramDouble1 * 2.0D * paramDouble5 * d1 + d4 * paramDouble2;
  }
  
  public double getPos(double paramDouble, int paramInt)
  {
    int j = data.length;
    double[] arrayOfDouble = data;
    int i = 0;
    if (paramDouble <= arrayOfDouble[0]) {
      return pos[0][paramInt];
    }
    arrayOfDouble = data;
    int k = j - 1;
    if (paramDouble >= arrayOfDouble[k]) {
      return pos[k][paramInt];
    }
    while (i < k)
    {
      if (paramDouble == data[i]) {
        return pos[i][paramInt];
      }
      arrayOfDouble = data;
      j = i + 1;
      if (paramDouble < arrayOfDouble[j])
      {
        double d = data[j] - data[i];
        return interpolate(d, (paramDouble - data[i]) / d, pos[i][paramInt], pos[j][paramInt], mTangent[i][paramInt], mTangent[j][paramInt]);
      }
      i = j;
    }
    return 0.0D;
  }
  
  public void getPos(double paramDouble, double[] paramArrayOfDouble)
  {
    int j = data.length;
    Object localObject = pos;
    int k = 0;
    int i = 0;
    int n = localObject[0].length;
    if (paramDouble <= data[0])
    {
      i = 0;
      while (i < n)
      {
        paramArrayOfDouble[i] = pos[0][i];
        i += 1;
      }
      return;
    }
    localObject = data;
    int i1 = j - 1;
    if (paramDouble >= localObject[i1])
    {
      while (i < n)
      {
        paramArrayOfDouble[i] = pos[i1][i];
        i += 1;
      }
      return;
    }
    int m;
    for (i = 0; i < i1; i = m)
    {
      if (paramDouble == data[i])
      {
        j = 0;
        while (j < n)
        {
          paramArrayOfDouble[j] = pos[i][j];
          j += 1;
        }
      }
      localObject = data;
      m = i + 1;
      if (paramDouble < localObject[m])
      {
        double d = data[m] - data[i];
        paramDouble = (paramDouble - data[i]) / d;
        j = k;
        while (j < n)
        {
          paramArrayOfDouble[j] = interpolate(d, paramDouble, pos[i][j], pos[m][j], mTangent[i][j], mTangent[m][j]);
          j += 1;
        }
        return;
      }
    }
  }
  
  public void getPos(double paramDouble, float[] paramArrayOfFloat)
  {
    int j = data.length;
    Object localObject = pos;
    int k = 0;
    int i = 0;
    int n = localObject[0].length;
    if (paramDouble <= data[0])
    {
      i = 0;
      while (i < n)
      {
        paramArrayOfFloat[i] = ((float)pos[0][i]);
        i += 1;
      }
      return;
    }
    localObject = data;
    int i1 = j - 1;
    if (paramDouble >= localObject[i1])
    {
      while (i < n)
      {
        paramArrayOfFloat[i] = ((float)pos[i1][i]);
        i += 1;
      }
      return;
    }
    int m;
    for (i = 0; i < i1; i = m)
    {
      if (paramDouble == data[i])
      {
        j = 0;
        while (j < n)
        {
          paramArrayOfFloat[j] = ((float)pos[i][j]);
          j += 1;
        }
      }
      localObject = data;
      m = i + 1;
      if (paramDouble < localObject[m])
      {
        double d = data[m] - data[i];
        paramDouble = (paramDouble - data[i]) / d;
        j = k;
        while (j < n)
        {
          paramArrayOfFloat[j] = ((float)interpolate(d, paramDouble, pos[i][j], pos[m][j], mTangent[i][j], mTangent[m][j]));
          j += 1;
        }
        return;
      }
    }
  }
  
  public double getSlope(double paramDouble, int paramInt)
  {
    int k = data.length;
    double[] arrayOfDouble = data;
    int i = 0;
    int j;
    if (paramDouble < arrayOfDouble[0])
    {
      paramDouble = data[0];
    }
    else
    {
      arrayOfDouble = data;
      j = k - 1;
      if (paramDouble >= arrayOfDouble[j]) {
        paramDouble = data[j];
      }
    }
    while (i < k - 1)
    {
      arrayOfDouble = data;
      j = i + 1;
      if (paramDouble <= arrayOfDouble[j])
      {
        double d = data[j] - data[i];
        return diff(d, (paramDouble - data[i]) / d, pos[i][paramInt], pos[j][paramInt], mTangent[i][paramInt], mTangent[j][paramInt]) / d;
      }
      i = j;
    }
    return 0.0D;
  }
  
  public void getSlope(double paramDouble, double[] paramArrayOfDouble)
  {
    int n = data.length;
    Object localObject = pos;
    int j = 0;
    int m = localObject[0].length;
    if (paramDouble <= data[0])
    {
      paramDouble = data[0];
    }
    else
    {
      localObject = data;
      i = n - 1;
      if (paramDouble >= localObject[i]) {
        paramDouble = data[i];
      }
    }
    int k;
    for (int i = 0; i < n - 1; i = k)
    {
      localObject = data;
      k = i + 1;
      if (paramDouble <= localObject[k])
      {
        double d = data[k] - data[i];
        paramDouble = (paramDouble - data[i]) / d;
        while (j < m)
        {
          paramArrayOfDouble[j] = (diff(d, paramDouble, pos[i][j], pos[k][j], mTangent[i][j], mTangent[k][j]) / d);
          j += 1;
        }
      }
    }
  }
  
  public double[] getTimePoints()
  {
    return data;
  }
}
