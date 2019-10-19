package android.support.constraint.motion.utils;

public class LinearCurveFit
  extends CurveFit
{
  private static final String PAGE_KEY = "LinearCurveFit";
  private double mTotalLength = NaN.0D;
  private double[] record;
  private double[][] this$0;
  
  public LinearCurveFit(double[] paramArrayOfDouble, double[][] paramArrayOfDouble1)
  {
    int i = paramArrayOfDouble1[0].length;
    record = paramArrayOfDouble;
    this$0 = paramArrayOfDouble1;
    if (i > 2)
    {
      double d1 = 0.0D;
      double d2 = 0.0D;
      i = 0;
      while (i < paramArrayOfDouble.length)
      {
        double d4 = paramArrayOfDouble1[i][0];
        double d3 = paramArrayOfDouble1[i][0];
        if (i > 0) {
          Math.hypot(d4 - d1, d3 - d2);
        }
        i += 1;
        d1 = d4;
        d2 = d3;
      }
      mTotalLength = 0.0D;
    }
  }
  
  private double getLength2D(double paramDouble)
  {
    if (Double.isNaN(mTotalLength)) {
      return 0.0D;
    }
    int i = record.length;
    if (paramDouble <= record[0]) {
      return 0.0D;
    }
    double[] arrayOfDouble = record;
    int k = i - 1;
    if (paramDouble >= arrayOfDouble[k]) {
      return mTotalLength;
    }
    double d2 = 0.0D;
    double d3 = 0.0D;
    double d4 = 0.0D;
    i = 0;
    while (i < k)
    {
      double d6 = this$0[i][0];
      double d5 = this$0[i][1];
      double d1 = d2;
      if (i > 0) {
        d1 = d2 + Math.hypot(d6 - d3, d5 - d4);
      }
      if (paramDouble == record[i]) {
        return d1;
      }
      arrayOfDouble = record;
      int j = i + 1;
      if (paramDouble < arrayOfDouble[j])
      {
        d2 = record[j];
        d3 = record[i];
        paramDouble = (paramDouble - record[i]) / (d2 - d3);
        d2 = this$0[i][0];
        d3 = this$0[j][0];
        d4 = this$0[i][1];
        double d7 = this$0[j][1];
        double d8 = 1.0D - paramDouble;
        return d1 + Math.hypot(d5 - (d4 * d8 + d7 * paramDouble), d6 - (d2 * d8 + d3 * paramDouble));
      }
      i = j;
      d3 = d6;
      d4 = d5;
      d2 = d1;
    }
    return 0.0D;
  }
  
  public double getPos(double paramDouble, int paramInt)
  {
    int j = record.length;
    double[] arrayOfDouble = record;
    int i = 0;
    if (paramDouble <= arrayOfDouble[0]) {
      return this$0[0][paramInt];
    }
    arrayOfDouble = record;
    int k = j - 1;
    if (paramDouble >= arrayOfDouble[k]) {
      return this$0[k][paramInt];
    }
    while (i < k)
    {
      if (paramDouble == record[i]) {
        return this$0[i][paramInt];
      }
      arrayOfDouble = record;
      j = i + 1;
      if (paramDouble < arrayOfDouble[j])
      {
        double d1 = record[j];
        double d2 = record[i];
        paramDouble = (paramDouble - record[i]) / (d1 - d2);
        return this$0[i][paramInt] * (1.0D - paramDouble) + this$0[j][paramInt] * paramDouble;
      }
      i = j;
    }
    return 0.0D;
  }
  
  public void getPos(double paramDouble, double[] paramArrayOfDouble)
  {
    int j = record.length;
    Object localObject = this$0;
    int k = 0;
    int i = 0;
    int n = localObject[0].length;
    if (paramDouble <= record[0])
    {
      i = 0;
      while (i < n)
      {
        paramArrayOfDouble[i] = this$0[0][i];
        i += 1;
      }
      return;
    }
    localObject = record;
    int i1 = j - 1;
    if (paramDouble >= localObject[i1])
    {
      while (i < n)
      {
        paramArrayOfDouble[i] = this$0[i1][i];
        i += 1;
      }
      return;
    }
    int m;
    for (i = 0; i < i1; i = m)
    {
      if (paramDouble == record[i])
      {
        j = 0;
        while (j < n)
        {
          paramArrayOfDouble[j] = this$0[i][j];
          j += 1;
        }
      }
      localObject = record;
      m = i + 1;
      if (paramDouble < localObject[m])
      {
        double d1 = record[m];
        double d2 = record[i];
        paramDouble = (paramDouble - record[i]) / (d1 - d2);
        j = k;
        while (j < n)
        {
          paramArrayOfDouble[j] = (this$0[i][j] * (1.0D - paramDouble) + this$0[m][j] * paramDouble);
          j += 1;
        }
        return;
      }
    }
  }
  
  public void getPos(double paramDouble, float[] paramArrayOfFloat)
  {
    int j = record.length;
    Object localObject = this$0;
    int k = 0;
    int i = 0;
    int n = localObject[0].length;
    if (paramDouble <= record[0])
    {
      i = 0;
      while (i < n)
      {
        paramArrayOfFloat[i] = ((float)this$0[0][i]);
        i += 1;
      }
      return;
    }
    localObject = record;
    int i1 = j - 1;
    if (paramDouble >= localObject[i1])
    {
      while (i < n)
      {
        paramArrayOfFloat[i] = ((float)this$0[i1][i]);
        i += 1;
      }
      return;
    }
    int m;
    for (i = 0; i < i1; i = m)
    {
      if (paramDouble == record[i])
      {
        j = 0;
        while (j < n)
        {
          paramArrayOfFloat[j] = ((float)this$0[i][j]);
          j += 1;
        }
      }
      localObject = record;
      m = i + 1;
      if (paramDouble < localObject[m])
      {
        double d1 = record[m];
        double d2 = record[i];
        paramDouble = (paramDouble - record[i]) / (d1 - d2);
        j = k;
        while (j < n)
        {
          paramArrayOfFloat[j] = ((float)(this$0[i][j] * (1.0D - paramDouble) + this$0[m][j] * paramDouble));
          j += 1;
        }
        return;
      }
    }
  }
  
  public double getSlope(double paramDouble, int paramInt)
  {
    int k = record.length;
    double[] arrayOfDouble = record;
    int i = 0;
    int j;
    if (paramDouble < arrayOfDouble[0])
    {
      paramDouble = record[0];
    }
    else
    {
      arrayOfDouble = record;
      j = k - 1;
      if (paramDouble >= arrayOfDouble[j]) {
        paramDouble = record[j];
      }
    }
    while (i < k - 1)
    {
      arrayOfDouble = record;
      j = i + 1;
      if (paramDouble <= arrayOfDouble[j])
      {
        paramDouble = record[j];
        double d1 = record[i];
        double d2 = record[i];
        d2 = this$0[i][paramInt];
        return (this$0[j][paramInt] - d2) / (paramDouble - d1);
      }
      i = j;
    }
    return 0.0D;
  }
  
  public void getSlope(double paramDouble, double[] paramArrayOfDouble)
  {
    int n = record.length;
    Object localObject = this$0;
    int j = 0;
    int m = localObject[0].length;
    double d1;
    if (paramDouble <= record[0])
    {
      d1 = record[0];
    }
    else
    {
      localObject = record;
      i = n - 1;
      d1 = paramDouble;
      if (paramDouble >= localObject[i]) {
        d1 = record[i];
      }
    }
    int k;
    for (int i = 0; i < n - 1; i = k)
    {
      localObject = record;
      k = i + 1;
      if (d1 <= localObject[k])
      {
        paramDouble = record[k];
        d1 = record[i];
        double d2 = record[i];
        while (j < m)
        {
          d2 = this$0[i][j];
          paramArrayOfDouble[j] = ((this$0[k][j] - d2) / (paramDouble - d1));
          j += 1;
        }
      }
    }
  }
  
  public double[] getTimePoints()
  {
    return record;
  }
}
