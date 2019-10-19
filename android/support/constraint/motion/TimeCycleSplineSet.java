package android.support.constraint.motion;

import D;
import F;
import android.os.Build.VERSION;
import android.support.constraint.ConstraintAttribute;
import android.support.constraint.motion.utils.CurveFit;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;

public abstract class TimeCycleSplineSet
{
  private static final int CURVE_OFFSET = 2;
  private static final int CURVE_PERIOD = 1;
  private static final int CURVE_VALUE = 0;
  private static final String PAGE_KEY = "SplineSet";
  private static float VAL_2PI;
  private int count;
  float last_cycle = 0.0F;
  long last_time;
  private float[] mCache = new float[3];
  protected boolean mContinue = false;
  protected CurveFit mCurveFit;
  protected int[] mTimePoints = new int[10];
  private String mType;
  protected float[][] mValues = (float[][])Array.newInstance(F.class, new int[] { 10, 3 });
  protected int mWaveShape = 0;
  
  public TimeCycleSplineSet() {}
  
  static TimeCycleSplineSet makeCustomSpline(String paramString, SparseArray paramSparseArray)
  {
    return new CustomSet(paramString, paramSparseArray);
  }
  
  static TimeCycleSplineSet makeSpline(String paramString)
  {
    switch (paramString.hashCode())
    {
    default: 
      break;
    case 92909918: 
      if (paramString.equals("alpha")) {
        i = 0;
      }
      break;
    case 37232917: 
      if (paramString.equals("transitionPathRotate")) {
        i = 5;
      }
      break;
    case -4379043: 
      if (paramString.equals("elevation")) {
        i = 1;
      }
      break;
    case -40300674: 
      if (paramString.equals("rotation")) {
        i = 2;
      }
      break;
    case -908189617: 
      if (paramString.equals("scaleY")) {
        i = 7;
      }
      break;
    case -908189618: 
      if (paramString.equals("scaleX")) {
        i = 6;
      }
      break;
    case -1001078227: 
      if (paramString.equals("progress")) {
        i = 11;
      }
      break;
    case -1225497655: 
      if (paramString.equals("translationZ")) {
        i = 10;
      }
      break;
    case -1225497656: 
      if (paramString.equals("translationY")) {
        i = 9;
      }
      break;
    case -1225497657: 
      if (paramString.equals("translationX")) {
        i = 8;
      }
      break;
    case -1249320805: 
      if (paramString.equals("rotationY")) {
        i = 4;
      }
      break;
    case -1249320806: 
      if (paramString.equals("rotationX")) {
        i = 3;
      }
      break;
    }
    int i = -1;
    switch (i)
    {
    default: 
      return null;
    case 11: 
      return new ProgressSet();
    case 10: 
      return new TranslationZset();
    case 9: 
      return new TranslationYset();
    case 8: 
      return new TranslationXset();
    case 7: 
      return new ScaleYset();
    case 6: 
      return new ScaleXset();
    case 5: 
      return new PathRotate();
    case 4: 
      return new RotationYset();
    case 3: 
      return new RotationXset();
    case 2: 
      return new RotationSet();
    case 1: 
      return new ElevationSet();
    }
    return new AlphaSet();
  }
  
  protected float calcWave(float paramFloat)
  {
    switch (mWaveShape)
    {
    default: 
      return (float)Math.sin(paramFloat * VAL_2PI);
    case 6: 
      paramFloat = 1.0F - Math.abs(paramFloat * 4.0F % 4.0F - 2.0F);
      return 1.0F - paramFloat * paramFloat;
    case 5: 
      return (float)Math.cos(paramFloat * VAL_2PI);
    case 4: 
      return 1.0F - (paramFloat * 2.0F + 1.0F) % 2.0F;
    case 3: 
      return (paramFloat * 2.0F + 1.0F) % 2.0F - 1.0F;
    case 2: 
      return 1.0F - Math.abs(paramFloat);
    }
    return Math.signum(paramFloat * VAL_2PI);
  }
  
  public CurveFit getCurveFit()
  {
    return mCurveFit;
  }
  
  public float round(float paramFloat, long paramLong)
  {
    mCurveFit.getPos(paramFloat, mCache);
    float[] arrayOfFloat = mCache;
    boolean bool2 = true;
    paramFloat = arrayOfFloat[1];
    if (paramFloat == 0.0F)
    {
      mContinue = false;
      return mCache[2];
    }
    long l = last_time;
    double d1 = last_cycle;
    double d2 = paramLong - l;
    Double.isNaN(d2);
    double d3 = paramFloat;
    Double.isNaN(d3);
    Double.isNaN(d1);
    last_cycle = ((float)((d1 + d2 * 1.0E-9D * d3) % 1.0D));
    last_time = paramLong;
    float f1 = mCache[0];
    float f2 = calcWave(last_cycle);
    float f3 = mCache[2];
    boolean bool1 = bool2;
    if (f1 == 0.0F) {
      if (paramFloat != 0.0F) {
        bool1 = bool2;
      } else {
        bool1 = false;
      }
    }
    mContinue = bool1;
    return f2 * f1 + f3;
  }
  
  public void setPoint(int paramInt1, float paramFloat1, float paramFloat2, int paramInt2, float paramFloat3)
  {
    mTimePoints[count] = paramInt1;
    mValues[count][0] = paramFloat1;
    mValues[count][1] = paramFloat2;
    mValues[count][2] = paramFloat3;
    mWaveShape = Math.max(mWaveShape, paramInt2);
    count += 1;
  }
  
  public abstract boolean setProperty(View paramView, float paramFloat, long paramLong);
  
  public void setType(String paramString)
  {
    mType = paramString;
  }
  
  public void setup(int paramInt)
  {
    if (count == 0)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Error no points added to ");
      ((StringBuilder)localObject).append(mType);
      Log.e("SplineSet", ((StringBuilder)localObject).toString());
      return;
    }
    Sort.doubleQuickSort(mTimePoints, mValues, 0, count - 1);
    int i = 1;
    int k;
    for (int j = 0; i < mTimePoints.length; j = k)
    {
      k = j;
      if (mTimePoints[i] != mTimePoints[(i - 1)]) {
        k = j + 1;
      }
      i += 1;
    }
    Object localObject = new double[j];
    double[][] arrayOfDouble = (double[][])Array.newInstance(D.class, new int[] { j, 3 });
    i = 0;
    j = 0;
    while (i < count)
    {
      if ((i <= 0) || (mTimePoints[i] != mTimePoints[(i - 1)]))
      {
        double d = mTimePoints[i];
        Double.isNaN(d);
        localObject[j] = (d * 0.01D);
        arrayOfDouble[j][0] = mValues[i][0];
        arrayOfDouble[j][1] = mValues[i][1];
        arrayOfDouble[j][2] = mValues[i][2];
        j += 1;
      }
      i += 1;
    }
    mCurveFit = CurveFit.get(paramInt, (double[])localObject, arrayOfDouble);
  }
  
  public String toString()
  {
    String str = mType;
    DecimalFormat localDecimalFormat = new DecimalFormat("##.##");
    int i = 0;
    while (i < count)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(str);
      localStringBuilder.append("[");
      localStringBuilder.append(mTimePoints[i]);
      localStringBuilder.append(" , ");
      localStringBuilder.append(localDecimalFormat.format(mValues[i]));
      localStringBuilder.append("] ");
      str = localStringBuilder.toString();
      i += 1;
    }
    return str;
  }
  
  static class AlphaSet
    extends TimeCycleSplineSet
  {
    AlphaSet() {}
    
    public boolean setProperty(View paramView, float paramFloat, long paramLong)
    {
      paramView.setAlpha(round(paramFloat, paramLong));
      return mContinue;
    }
  }
  
  static class CustomSet
    extends TimeCycleSplineSet
  {
    String mAttributeName = paramString.split(",")[1];
    float[] mCache;
    SparseArray<ConstraintAttribute> mConstraintAttributeList;
    float[] mTempValues;
    SparseArray<float[]> mWaveProperties = new SparseArray();
    
    public CustomSet(String paramString, SparseArray paramSparseArray)
    {
      mConstraintAttributeList = paramSparseArray;
    }
    
    public void setPoint(int paramInt1, float paramFloat1, float paramFloat2, int paramInt2, float paramFloat3)
    {
      throw new RuntimeException("don't call for custom attribute call setPoint(pos, ConstraintAttribute,...)");
    }
    
    public void setPoint(int paramInt1, ConstraintAttribute paramConstraintAttribute, float paramFloat1, int paramInt2, float paramFloat2)
    {
      mConstraintAttributeList.append(paramInt1, paramConstraintAttribute);
      mWaveProperties.append(paramInt1, new float[] { paramFloat1, paramFloat2 });
      mWaveShape = Math.max(mWaveShape, paramInt2);
    }
    
    public boolean setProperty(View paramView, float paramFloat, long paramLong)
    {
      mCurveFit.getPos(paramFloat, mTempValues);
      paramFloat = mTempValues[(mTempValues.length - 2)];
      float f1 = mTempValues[(mTempValues.length - 1)];
      long l = last_time;
      double d1 = last_cycle;
      double d2 = paramLong - l;
      Double.isNaN(d2);
      double d3 = paramFloat;
      Double.isNaN(d3);
      Double.isNaN(d1);
      last_cycle = ((float)((d1 + d2 * 1.0E-9D * d3) % 1.0D));
      last_time = paramLong;
      float f2 = calcWave(last_cycle);
      mContinue = false;
      int i = 0;
      while (i < mCache.length)
      {
        boolean bool2 = mContinue;
        boolean bool1;
        if (mTempValues[i] != 0.0D) {
          bool1 = true;
        } else {
          bool1 = false;
        }
        mContinue = (bool2 | bool1);
        mCache[i] = (mTempValues[i] * f2 + f1);
        i += 1;
      }
      ((ConstraintAttribute)mConstraintAttributeList.valueAt(0)).setInterpolatedValue(paramView, mCache);
      if (paramFloat != 0.0F) {
        mContinue = false;
      }
      return mContinue;
    }
    
    public void setup(int paramInt)
    {
      int k = mConstraintAttributeList.size();
      int i = ((ConstraintAttribute)mConstraintAttributeList.valueAt(0)).noOfInterpValues();
      double[] arrayOfDouble = new double[k];
      int j = i + 2;
      mTempValues = new float[j];
      mCache = new float[i];
      double[][] arrayOfDouble1 = (double[][])Array.newInstance(D.class, new int[] { k, j });
      i = 0;
      while (i < k)
      {
        j = mConstraintAttributeList.keyAt(i);
        ConstraintAttribute localConstraintAttribute = (ConstraintAttribute)mConstraintAttributeList.valueAt(i);
        float[] arrayOfFloat = (float[])mWaveProperties.valueAt(i);
        double d = j;
        Double.isNaN(d);
        arrayOfDouble[i] = (d * 0.01D);
        localConstraintAttribute.getValuesToInterpolate(mTempValues);
        j = 0;
        while (j < mTempValues.length)
        {
          arrayOfDouble1[i][j] = mTempValues[j];
          j += 1;
        }
        arrayOfDouble1[i][mTempValues.length] = arrayOfFloat[0];
        arrayOfDouble1[i][(mTempValues.length + 1)] = arrayOfFloat[1];
        i += 1;
      }
      mCurveFit = CurveFit.get(paramInt, arrayOfDouble, arrayOfDouble1);
    }
  }
  
  static class ElevationSet
    extends TimeCycleSplineSet
  {
    ElevationSet() {}
    
    public boolean setProperty(View paramView, float paramFloat, long paramLong)
    {
      if (Build.VERSION.SDK_INT >= 21) {
        paramView.setElevation(round(paramFloat, paramLong));
      }
      return mContinue;
    }
  }
  
  static class PathRotate
    extends TimeCycleSplineSet
  {
    PathRotate() {}
    
    public boolean setPathRotate(View paramView, float paramFloat, long paramLong, double paramDouble1, double paramDouble2)
    {
      paramView.setRotation(round(paramFloat, paramLong) + (float)Math.toDegrees(Math.atan2(paramDouble2, paramDouble1)));
      return mContinue;
    }
    
    public boolean setProperty(View paramView, float paramFloat, long paramLong)
    {
      return mContinue;
    }
  }
  
  static class ProgressSet
    extends TimeCycleSplineSet
  {
    boolean mNoMethod = false;
    
    ProgressSet() {}
    
    public boolean setProperty(View paramView, float paramFloat, long paramLong)
    {
      Object localObject1;
      if ((paramView instanceof MotionLayout))
      {
        ((MotionLayout)paramView).setProgress(round(paramFloat, paramLong));
      }
      else
      {
        if (mNoMethod) {
          return false;
        }
        localObject1 = null;
      }
      try
      {
        Object localObject2 = paramView.getClass();
        Class localClass = Float.TYPE;
        localObject2 = ((Class)localObject2).getMethod("setProgress", new Class[] { localClass });
        localObject1 = localObject2;
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        for (;;) {}
      }
      mNoMethod = true;
      if (localObject1 != null) {
        try
        {
          paramFloat = round(paramFloat, paramLong);
          localObject1.invoke(paramView, new Object[] { Float.valueOf(paramFloat) });
        }
        catch (InvocationTargetException paramView)
        {
          Log.e("SplineSet", "unable to setProgress", paramView);
        }
        catch (IllegalAccessException paramView)
        {
          Log.e("SplineSet", "unable to setProgress", paramView);
        }
      }
      return mContinue;
    }
  }
  
  static class RotationSet
    extends TimeCycleSplineSet
  {
    RotationSet() {}
    
    public boolean setProperty(View paramView, float paramFloat, long paramLong)
    {
      paramView.setRotation(round(paramFloat, paramLong));
      return mContinue;
    }
  }
  
  static class RotationXset
    extends TimeCycleSplineSet
  {
    RotationXset() {}
    
    public boolean setProperty(View paramView, float paramFloat, long paramLong)
    {
      paramView.setRotationX(round(paramFloat, paramLong));
      return mContinue;
    }
  }
  
  static class RotationYset
    extends TimeCycleSplineSet
  {
    RotationYset() {}
    
    public boolean setProperty(View paramView, float paramFloat, long paramLong)
    {
      paramView.setRotationY(round(paramFloat, paramLong));
      return mContinue;
    }
  }
  
  static class ScaleXset
    extends TimeCycleSplineSet
  {
    ScaleXset() {}
    
    public boolean setProperty(View paramView, float paramFloat, long paramLong)
    {
      paramView.setScaleX(round(paramFloat, paramLong));
      return mContinue;
    }
  }
  
  static class ScaleYset
    extends TimeCycleSplineSet
  {
    ScaleYset() {}
    
    public boolean setProperty(View paramView, float paramFloat, long paramLong)
    {
      paramView.setScaleY(round(paramFloat, paramLong));
      return mContinue;
    }
  }
  
  private static class Sort
  {
    private Sort() {}
    
    static void doubleQuickSort(int[] paramArrayOfInt, float[][] paramArrayOfFloat, int paramInt1, int paramInt2)
    {
      int[] arrayOfInt = new int[paramArrayOfInt.length + 10];
      arrayOfInt[0] = paramInt2;
      arrayOfInt[1] = paramInt1;
      paramInt1 = 2;
      while (paramInt1 > 0)
      {
        paramInt1 -= 1;
        int i = arrayOfInt[paramInt1];
        paramInt2 = paramInt1 - 1;
        int j = arrayOfInt[paramInt2];
        paramInt1 = paramInt2;
        if (i < j)
        {
          int k = partition(paramArrayOfInt, paramArrayOfFloat, i, j);
          paramInt1 = paramInt2 + 1;
          arrayOfInt[paramInt2] = (k - 1);
          paramInt2 = paramInt1 + 1;
          arrayOfInt[paramInt1] = i;
          i = paramInt2 + 1;
          arrayOfInt[paramInt2] = j;
          paramInt1 = i + 1;
          arrayOfInt[i] = (k + 1);
        }
      }
    }
    
    private static int partition(int[] paramArrayOfInt, float[][] paramArrayOfFloat, int paramInt1, int paramInt2)
    {
      int k = paramArrayOfInt[paramInt2];
      int j;
      for (int i = paramInt1; paramInt1 < paramInt2; i = j)
      {
        j = i;
        if (paramArrayOfInt[paramInt1] <= k)
        {
          swap(paramArrayOfInt, paramArrayOfFloat, i, paramInt1);
          j = i + 1;
        }
        paramInt1 += 1;
      }
      swap(paramArrayOfInt, paramArrayOfFloat, i, paramInt2);
      return i;
    }
    
    private static void swap(int[] paramArrayOfInt, float[][] paramArrayOfFloat, int paramInt1, int paramInt2)
    {
      int i = paramArrayOfInt[paramInt1];
      paramArrayOfInt[paramInt1] = paramArrayOfInt[paramInt2];
      paramArrayOfInt[paramInt2] = i;
      paramArrayOfInt = paramArrayOfFloat[paramInt1];
      paramArrayOfFloat[paramInt1] = paramArrayOfFloat[paramInt2];
      paramArrayOfFloat[paramInt2] = paramArrayOfInt;
    }
  }
  
  static class TranslationXset
    extends TimeCycleSplineSet
  {
    TranslationXset() {}
    
    public boolean setProperty(View paramView, float paramFloat, long paramLong)
    {
      paramView.setTranslationX(round(paramFloat, paramLong));
      return mContinue;
    }
  }
  
  static class TranslationYset
    extends TimeCycleSplineSet
  {
    TranslationYset() {}
    
    public boolean setProperty(View paramView, float paramFloat, long paramLong)
    {
      paramView.setTranslationY(round(paramFloat, paramLong));
      return mContinue;
    }
  }
  
  static class TranslationZset
    extends TimeCycleSplineSet
  {
    TranslationZset() {}
    
    public boolean setProperty(View paramView, float paramFloat, long paramLong)
    {
      if (Build.VERSION.SDK_INT >= 21) {
        paramView.setTranslationZ(round(paramFloat, paramLong));
      }
      return mContinue;
    }
  }
}
