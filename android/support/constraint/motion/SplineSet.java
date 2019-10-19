package android.support.constraint.motion;

import D;
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
import java.util.Arrays;

public abstract class SplineSet
{
  private static final String PAGE_KEY = "SplineSet";
  private int count;
  protected CurveFit mCurveFit;
  protected int[] mTimePoints = new int[10];
  private String mType;
  protected float[] mValues = new float[10];
  
  public SplineSet() {}
  
  static SplineSet makeCustomSpline(String paramString, SparseArray paramSparseArray)
  {
    return new CustomSet(paramString, paramSparseArray);
  }
  
  static SplineSet makeSpline(String paramString)
  {
    switch (paramString.hashCode())
    {
    default: 
      break;
    case 156108012: 
      if (paramString.equals("waveOffset")) {
        i = 8;
      }
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
    case -797520672: 
      if (paramString.equals("waveVariesBy")) {
        i = 9;
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
        i = 13;
      }
      break;
    case -1225497655: 
      if (paramString.equals("translationZ")) {
        i = 12;
      }
      break;
    case -1225497656: 
      if (paramString.equals("translationY")) {
        i = 11;
      }
      break;
    case -1225497657: 
      if (paramString.equals("translationX")) {
        i = 10;
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
    case 13: 
      return new ProgressSet();
    case 12: 
      return new TranslationZset();
    case 11: 
      return new TranslationYset();
    case 10: 
      return new TranslationXset();
    case 9: 
      return new AlphaSet();
    case 8: 
      return new AlphaSet();
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
  
  public float floatValue(float paramFloat)
  {
    return (float)mCurveFit.getPos(paramFloat, 0);
  }
  
  public CurveFit getCurveFit()
  {
    return mCurveFit;
  }
  
  public void setPoint(int paramInt, float paramFloat)
  {
    if (mTimePoints.length < count + 1)
    {
      mTimePoints = Arrays.copyOf(mTimePoints, mTimePoints.length * 2);
      mValues = Arrays.copyOf(mValues, mValues.length * 2);
    }
    mTimePoints[count] = paramInt;
    mValues[count] = paramFloat;
    count += 1;
  }
  
  public abstract void setProperty(View paramView, float paramFloat);
  
  public void setType(String paramString)
  {
    mType = paramString;
  }
  
  public void setup(int paramInt)
  {
    if (count == 0) {
      return;
    }
    Sort.doubleQuickSort(mTimePoints, mValues, 0, count - 1);
    int i = 1;
    int k;
    for (int j = 1; i < count; j = k)
    {
      k = j;
      if (mTimePoints[(i - 1)] != mTimePoints[i]) {
        k = j + 1;
      }
      i += 1;
    }
    double[] arrayOfDouble = new double[j];
    double[][] arrayOfDouble1 = (double[][])Array.newInstance(D.class, new int[] { j, 1 });
    i = 0;
    j = 0;
    while (i < count)
    {
      if ((i <= 0) || (mTimePoints[i] != mTimePoints[(i - 1)]))
      {
        double d = mTimePoints[i];
        Double.isNaN(d);
        arrayOfDouble[j] = (d * 0.01D);
        arrayOfDouble1[j][0] = mValues[i];
        j += 1;
      }
      i += 1;
    }
    mCurveFit = CurveFit.get(paramInt, arrayOfDouble, arrayOfDouble1);
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
    extends SplineSet
  {
    AlphaSet() {}
    
    public void setProperty(View paramView, float paramFloat)
    {
      paramView.setAlpha(floatValue(paramFloat));
    }
  }
  
  static class CustomSet
    extends SplineSet
  {
    String mAttributeName = paramString.split(",")[1];
    SparseArray<ConstraintAttribute> mConstraintAttributeList;
    float[] mTempValues;
    
    public CustomSet(String paramString, SparseArray paramSparseArray)
    {
      mConstraintAttributeList = paramSparseArray;
    }
    
    public void setPoint(int paramInt, float paramFloat)
    {
      throw new RuntimeException("don't call for custom attribute call setPoint(pos, ConstraintAttribute)");
    }
    
    public void setPoint(int paramInt, ConstraintAttribute paramConstraintAttribute)
    {
      mConstraintAttributeList.append(paramInt, paramConstraintAttribute);
    }
    
    public void setProperty(View paramView, float paramFloat)
    {
      mCurveFit.getPos(paramFloat, mTempValues);
      ((ConstraintAttribute)mConstraintAttributeList.valueAt(0)).setInterpolatedValue(paramView, mTempValues);
    }
    
    public void setup(int paramInt)
    {
      int k = mConstraintAttributeList.size();
      int i = ((ConstraintAttribute)mConstraintAttributeList.valueAt(0)).noOfInterpValues();
      double[] arrayOfDouble = new double[k];
      mTempValues = new float[i];
      double[][] arrayOfDouble1 = (double[][])Array.newInstance(D.class, new int[] { k, i });
      i = 0;
      while (i < k)
      {
        int j = mConstraintAttributeList.keyAt(i);
        ConstraintAttribute localConstraintAttribute = (ConstraintAttribute)mConstraintAttributeList.valueAt(i);
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
        i += 1;
      }
      mCurveFit = CurveFit.get(paramInt, arrayOfDouble, arrayOfDouble1);
    }
  }
  
  static class ElevationSet
    extends SplineSet
  {
    ElevationSet() {}
    
    public void setProperty(View paramView, float paramFloat)
    {
      if (Build.VERSION.SDK_INT >= 21) {
        paramView.setElevation(floatValue(paramFloat));
      }
    }
  }
  
  static class PathRotate
    extends SplineSet
  {
    PathRotate() {}
    
    public void setPathRotate(View paramView, float paramFloat, double paramDouble1, double paramDouble2)
    {
      paramView.setRotation(floatValue(paramFloat) + (float)Math.toDegrees(Math.atan2(paramDouble2, paramDouble1)));
    }
    
    public void setProperty(View paramView, float paramFloat) {}
  }
  
  static class ProgressSet
    extends SplineSet
  {
    boolean mNoMethod = false;
    
    ProgressSet() {}
    
    public void setProperty(View paramView, float paramFloat)
    {
      if ((paramView instanceof MotionLayout))
      {
        ((MotionLayout)paramView).setProgress(floatValue(paramFloat));
        return;
      }
      if (mNoMethod) {
        return;
      }
      Object localObject1 = null;
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
          paramFloat = floatValue(paramFloat);
          localObject1.invoke(paramView, new Object[] { Float.valueOf(paramFloat) });
          return;
        }
        catch (InvocationTargetException paramView)
        {
          Log.e("SplineSet", "unable to setProgress", paramView);
          return;
        }
        catch (IllegalAccessException paramView)
        {
          Log.e("SplineSet", "unable to setProgress", paramView);
          return;
        }
      }
    }
  }
  
  static class RotationSet
    extends SplineSet
  {
    RotationSet() {}
    
    public void setProperty(View paramView, float paramFloat)
    {
      paramView.setRotation(floatValue(paramFloat));
    }
  }
  
  static class RotationXset
    extends SplineSet
  {
    RotationXset() {}
    
    public void setProperty(View paramView, float paramFloat)
    {
      paramView.setRotationX(floatValue(paramFloat));
    }
  }
  
  static class RotationYset
    extends SplineSet
  {
    RotationYset() {}
    
    public void setProperty(View paramView, float paramFloat)
    {
      paramView.setRotationY(floatValue(paramFloat));
    }
  }
  
  static class ScaleXset
    extends SplineSet
  {
    ScaleXset() {}
    
    public void setProperty(View paramView, float paramFloat)
    {
      paramView.setScaleX(floatValue(paramFloat));
    }
  }
  
  static class ScaleYset
    extends SplineSet
  {
    ScaleYset() {}
    
    public void setProperty(View paramView, float paramFloat)
    {
      paramView.setScaleY(floatValue(paramFloat));
    }
  }
  
  private static class Sort
  {
    private Sort() {}
    
    static void doubleQuickSort(int[] paramArrayOfInt, float[] paramArrayOfFloat, int paramInt1, int paramInt2)
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
    
    private static int partition(int[] paramArrayOfInt, float[] paramArrayOfFloat, int paramInt1, int paramInt2)
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
    
    private static void swap(int[] paramArrayOfInt, float[] paramArrayOfFloat, int paramInt1, int paramInt2)
    {
      int i = paramArrayOfInt[paramInt1];
      paramArrayOfInt[paramInt1] = paramArrayOfInt[paramInt2];
      paramArrayOfInt[paramInt2] = i;
      float f = paramArrayOfFloat[paramInt1];
      paramArrayOfFloat[paramInt1] = paramArrayOfFloat[paramInt2];
      paramArrayOfFloat[paramInt2] = f;
    }
  }
  
  static class TranslationXset
    extends SplineSet
  {
    TranslationXset() {}
    
    public void setProperty(View paramView, float paramFloat)
    {
      paramView.setTranslationX(floatValue(paramFloat));
    }
  }
  
  static class TranslationYset
    extends SplineSet
  {
    TranslationYset() {}
    
    public void setProperty(View paramView, float paramFloat)
    {
      paramView.setTranslationY(floatValue(paramFloat));
    }
  }
  
  static class TranslationZset
    extends SplineSet
  {
    TranslationZset() {}
    
    public void setProperty(View paramView, float paramFloat)
    {
      if (Build.VERSION.SDK_INT >= 21) {
        paramView.setTranslationZ(floatValue(paramFloat));
      }
    }
  }
}
