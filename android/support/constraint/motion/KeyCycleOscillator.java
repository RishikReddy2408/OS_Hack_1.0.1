package android.support.constraint.motion;

import D;
import android.os.Build.VERSION;
import android.support.constraint.ConstraintAttribute;
import android.support.constraint.ConstraintAttribute.AttributeType;
import android.support.constraint.motion.utils.CurveFit;
import android.support.constraint.motion.utils.Oscillator;
import android.util.Log;
import android.view.View;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

public abstract class KeyCycleOscillator
{
  private static final String PAGE_KEY = "KeyCycleOscillator";
  private CurveFit mCurveFit;
  protected ConstraintAttribute mCustom;
  private CycleOscillator mCycleOscillator;
  private String mType;
  public int mVariesBy = 0;
  ArrayList<WavePoint> mWavePoints = new ArrayList();
  private int mWaveShape = 0;
  
  public KeyCycleOscillator() {}
  
  static KeyCycleOscillator makeSpline(String paramString)
  {
    if (paramString.startsWith("CUSTOM")) {
      return new CustomSet();
    }
    int i = -1;
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
      return new PathRotateSet();
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
  
  public CurveFit getCurveFit()
  {
    return mCurveFit;
  }
  
  public float scale(float paramFloat)
  {
    return (float)mCycleOscillator.getValues(paramFloat);
  }
  
  public void setPoint(int paramInt1, int paramInt2, int paramInt3, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    mWavePoints.add(new WavePoint(paramInt1, paramFloat1, paramFloat2, paramFloat3));
    if (paramInt3 != -1) {
      mVariesBy = paramInt3;
    }
    mWaveShape = paramInt2;
  }
  
  public void setPoint(int paramInt1, int paramInt2, int paramInt3, float paramFloat1, float paramFloat2, float paramFloat3, ConstraintAttribute paramConstraintAttribute)
  {
    mWavePoints.add(new WavePoint(paramInt1, paramFloat1, paramFloat2, paramFloat3));
    if (paramInt3 != -1) {
      mVariesBy = paramInt3;
    }
    mWaveShape = paramInt2;
    mCustom = paramConstraintAttribute;
  }
  
  public abstract void setProperty(View paramView, float paramFloat);
  
  public void setType(String paramString)
  {
    mType = paramString;
  }
  
  public void setup(float paramFloat)
  {
    int i = mWavePoints.size();
    if (i == 0) {
      return;
    }
    Collections.sort(mWavePoints, new Comparator()
    {
      public int compare(KeyCycleOscillator.WavePoint paramAnonymousWavePoint1, KeyCycleOscillator.WavePoint paramAnonymousWavePoint2)
      {
        return Integer.compare(mPosition, mPosition);
      }
    });
    double[] arrayOfDouble = new double[i];
    double[][] arrayOfDouble1 = (double[][])Array.newInstance(D.class, new int[] { i, 2 });
    mCycleOscillator = new CycleOscillator(mWaveShape, mVariesBy, i);
    Iterator localIterator = mWavePoints.iterator();
    i = 0;
    while (localIterator.hasNext())
    {
      WavePoint localWavePoint = (WavePoint)localIterator.next();
      double d = mPeriod;
      Double.isNaN(d);
      arrayOfDouble[i] = (d * 0.01D);
      arrayOfDouble1[i][0] = mValue;
      arrayOfDouble1[i][1] = mOffset;
      mCycleOscillator.setPoint(i, mPosition, mPeriod, mOffset, mValue);
      i += 1;
    }
    mCycleOscillator.setup(paramFloat);
    mCurveFit = CurveFit.get(0, arrayOfDouble, arrayOfDouble1);
  }
  
  public String toString()
  {
    String str = mType;
    DecimalFormat localDecimalFormat = new DecimalFormat("##.##");
    Iterator localIterator = mWavePoints.iterator();
    while (localIterator.hasNext())
    {
      WavePoint localWavePoint = (WavePoint)localIterator.next();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(str);
      localStringBuilder.append("[");
      localStringBuilder.append(mPosition);
      localStringBuilder.append(" , ");
      localStringBuilder.append(localDecimalFormat.format(mValue));
      localStringBuilder.append("] ");
      str = localStringBuilder.toString();
    }
    return str;
  }
  
  public boolean variesByPath()
  {
    return mVariesBy == 1;
  }
  
  static class AlphaSet
    extends KeyCycleOscillator
  {
    AlphaSet() {}
    
    public void setProperty(View paramView, float paramFloat)
    {
      paramView.setAlpha(scale(paramFloat));
    }
  }
  
  static class CustomSet
    extends KeyCycleOscillator
  {
    float[] value = new float[1];
    
    CustomSet() {}
    
    public void setProperty(View paramView, float paramFloat)
    {
      value[0] = scale(paramFloat);
      mCustom.setInterpolatedValue(paramView, value);
    }
  }
  
  static class CycleOscillator
  {
    private static final String PAGE_KEY = "CycleOscillator";
    static final int UNSET = -1;
    CurveFit mCurveFit;
    public HashMap<String, ConstraintAttribute> mCustomConstraints = new HashMap();
    float[] mOffset;
    Oscillator mOscillator = new Oscillator();
    float mPathLength;
    float[] mPeriod;
    double[] mPosition;
    float[] mScale;
    double[] mSplineValueCache;
    long mStartTimeNano;
    float[] mValues;
    private final int mVariesBy;
    int mWaveShape;
    
    CycleOscillator(int paramInt1, int paramInt2, int paramInt3)
    {
      mWaveShape = paramInt1;
      mVariesBy = paramInt2;
      mOscillator.setType(paramInt1);
      mValues = new float[paramInt3];
      mPosition = new double[paramInt3];
      mPeriod = new float[paramInt3];
      mOffset = new float[paramInt3];
      mScale = new float[paramInt3];
    }
    
    private ConstraintAttribute get(String paramString, ConstraintAttribute.AttributeType paramAttributeType)
    {
      if (mCustomConstraints.containsKey(paramString))
      {
        paramString = (ConstraintAttribute)mCustomConstraints.get(paramString);
        if (paramString.getType() == paramAttributeType) {
          return paramString;
        }
        paramAttributeType = new StringBuilder();
        paramAttributeType.append("ConstraintAttribute is already a ");
        paramAttributeType.append(paramString.getType().name());
        throw new IllegalArgumentException(paramAttributeType.toString());
      }
      paramAttributeType = new ConstraintAttribute(paramString, paramAttributeType);
      mCustomConstraints.put(paramString, paramAttributeType);
      return paramAttributeType;
    }
    
    public double getValues(float paramFloat)
    {
      if (mCurveFit != null)
      {
        mCurveFit.getPos(paramFloat, mSplineValueCache);
      }
      else
      {
        mSplineValueCache[0] = mOffset[0];
        mSplineValueCache[1] = mValues[0];
      }
      return mSplineValueCache[0] + mOscillator.getValue(paramFloat) * mSplineValueCache[1];
    }
    
    public void setPoint(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3)
    {
      double[] arrayOfDouble = mPosition;
      double d = paramInt2;
      Double.isNaN(d);
      arrayOfDouble[paramInt1] = (d / 100.0D);
      mPeriod[paramInt1] = paramFloat1;
      mOffset[paramInt1] = paramFloat2;
      mValues[paramInt1] = paramFloat3;
    }
    
    public void setup(float paramFloat)
    {
      mStartTimeNano = System.nanoTime();
      mPathLength = paramFloat;
      double[][] arrayOfDouble = (double[][])Array.newInstance(D.class, new int[] { mPosition.length, 2 });
      mSplineValueCache = new double[mValues.length + 1];
      if (mPosition[0] > 0.0D) {
        mOscillator.addPoint(0.0D, mPeriod[0]);
      }
      int i = mPosition.length - 1;
      if (mPosition[i] < 1.0D) {
        mOscillator.addPoint(1.0D, mPeriod[i]);
      }
      i = 0;
      while (i < arrayOfDouble.length)
      {
        arrayOfDouble[i][0] = mOffset[i];
        int j = 0;
        while (j < mValues.length)
        {
          arrayOfDouble[j][1] = mValues[j];
          j += 1;
        }
        mOscillator.addPoint(mPosition[i], mPeriod[i]);
        i += 1;
      }
      mOscillator.normalize();
      if (mPosition.length > 1)
      {
        mCurveFit = CurveFit.get(0, mPosition, arrayOfDouble);
        return;
      }
      mCurveFit = null;
    }
  }
  
  static class ElevationSet
    extends KeyCycleOscillator
  {
    ElevationSet() {}
    
    public void setProperty(View paramView, float paramFloat)
    {
      if (Build.VERSION.SDK_INT >= 21) {
        paramView.setElevation(scale(paramFloat));
      }
    }
  }
  
  private static class IntDoubleSort
  {
    private IntDoubleSort() {}
    
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
    
    static void sort(int[] paramArrayOfInt, float[] paramArrayOfFloat, int paramInt1, int paramInt2)
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
  
  private static class IntFloatFloatSort
  {
    private IntFloatFloatSort() {}
    
    private static int partition(int[] paramArrayOfInt, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, int paramInt1, int paramInt2)
    {
      int k = paramArrayOfInt[paramInt2];
      int j;
      for (int i = paramInt1; paramInt1 < paramInt2; i = j)
      {
        j = i;
        if (paramArrayOfInt[paramInt1] <= k)
        {
          swap(paramArrayOfInt, paramArrayOfFloat1, paramArrayOfFloat2, i, paramInt1);
          j = i + 1;
        }
        paramInt1 += 1;
      }
      swap(paramArrayOfInt, paramArrayOfFloat1, paramArrayOfFloat2, i, paramInt2);
      return i;
    }
    
    static void sort(int[] paramArrayOfInt, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, int paramInt1, int paramInt2)
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
          int k = partition(paramArrayOfInt, paramArrayOfFloat1, paramArrayOfFloat2, i, j);
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
    
    private static void swap(int[] paramArrayOfInt, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, int paramInt1, int paramInt2)
    {
      int i = paramArrayOfInt[paramInt1];
      paramArrayOfInt[paramInt1] = paramArrayOfInt[paramInt2];
      paramArrayOfInt[paramInt2] = i;
      float f = paramArrayOfFloat1[paramInt1];
      paramArrayOfFloat1[paramInt1] = paramArrayOfFloat1[paramInt2];
      paramArrayOfFloat1[paramInt2] = f;
      f = paramArrayOfFloat2[paramInt1];
      paramArrayOfFloat2[paramInt1] = paramArrayOfFloat2[paramInt2];
      paramArrayOfFloat2[paramInt2] = f;
    }
  }
  
  static class PathRotateSet
    extends KeyCycleOscillator
  {
    PathRotateSet() {}
    
    public void setPathRotate(View paramView, float paramFloat, double paramDouble1, double paramDouble2)
    {
      paramView.setRotation(scale(paramFloat) + (float)Math.toDegrees(Math.atan2(paramDouble2, paramDouble1)));
    }
    
    public void setProperty(View paramView, float paramFloat) {}
  }
  
  static class ProgressSet
    extends KeyCycleOscillator
  {
    boolean mNoMethod = false;
    
    ProgressSet() {}
    
    public void setProperty(View paramView, float paramFloat)
    {
      if ((paramView instanceof MotionLayout))
      {
        ((MotionLayout)paramView).setProgress(scale(paramFloat));
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
          paramFloat = scale(paramFloat);
          localObject1.invoke(paramView, new Object[] { Float.valueOf(paramFloat) });
          return;
        }
        catch (InvocationTargetException paramView)
        {
          Log.e("KeyCycleOscillator", "unable to setProgress", paramView);
          return;
        }
        catch (IllegalAccessException paramView)
        {
          Log.e("KeyCycleOscillator", "unable to setProgress", paramView);
          return;
        }
      }
    }
  }
  
  static class RotationSet
    extends KeyCycleOscillator
  {
    RotationSet() {}
    
    public void setProperty(View paramView, float paramFloat)
    {
      paramView.setRotation(scale(paramFloat));
    }
  }
  
  static class RotationXset
    extends KeyCycleOscillator
  {
    RotationXset() {}
    
    public void setProperty(View paramView, float paramFloat)
    {
      paramView.setRotationX(scale(paramFloat));
    }
  }
  
  static class RotationYset
    extends KeyCycleOscillator
  {
    RotationYset() {}
    
    public void setProperty(View paramView, float paramFloat)
    {
      paramView.setRotationY(scale(paramFloat));
    }
  }
  
  static class ScaleXset
    extends KeyCycleOscillator
  {
    ScaleXset() {}
    
    public void setProperty(View paramView, float paramFloat)
    {
      paramView.setScaleX(scale(paramFloat));
    }
  }
  
  static class ScaleYset
    extends KeyCycleOscillator
  {
    ScaleYset() {}
    
    public void setProperty(View paramView, float paramFloat)
    {
      paramView.setScaleY(scale(paramFloat));
    }
  }
  
  static class TranslationXset
    extends KeyCycleOscillator
  {
    TranslationXset() {}
    
    public void setProperty(View paramView, float paramFloat)
    {
      paramView.setTranslationX(scale(paramFloat));
    }
  }
  
  static class TranslationYset
    extends KeyCycleOscillator
  {
    TranslationYset() {}
    
    public void setProperty(View paramView, float paramFloat)
    {
      paramView.setTranslationY(scale(paramFloat));
    }
  }
  
  static class TranslationZset
    extends KeyCycleOscillator
  {
    TranslationZset() {}
    
    public void setProperty(View paramView, float paramFloat)
    {
      if (Build.VERSION.SDK_INT >= 21) {
        paramView.setTranslationZ(scale(paramFloat));
      }
    }
  }
  
  static class WavePoint
  {
    float mOffset;
    float mPeriod;
    int mPosition;
    float mValue;
    
    public WavePoint(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3)
    {
      mPosition = paramInt;
      mValue = paramFloat3;
      mOffset = paramFloat2;
      mPeriod = paramFloat1;
    }
  }
}
