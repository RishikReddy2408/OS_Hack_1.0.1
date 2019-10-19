package android.support.constraint.motion;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintAttribute;
import android.support.constraint.R.styleable;
import android.support.constraint.motion.utils.CurveFit;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.util.TypedValue;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class KeyTimeCycle
  extends FieldInfo
{
  public static final int KEY_TYPE = 1;
  static final String NAME = "KeyTimeCycle";
  private static final String PAGE_KEY = "KeyTimeCycle";
  private float mAlpha = NaN.0F;
  private int mCurveFit = -1;
  private float mElevation = NaN.0F;
  private float mProgress = NaN.0F;
  private float mRotation = NaN.0F;
  private float mRotationX = NaN.0F;
  private float mRotationY = NaN.0F;
  private float mScaleX = NaN.0F;
  private float mScaleY = NaN.0F;
  private String mTransitionEasing;
  private float mTransitionPathRotate = NaN.0F;
  private float mTranslationX = NaN.0F;
  private float mTranslationY = NaN.0F;
  private float mTranslationZ = NaN.0F;
  private float mWaveOffset = 0.0F;
  private CurveFit mWaveOffsetSpline;
  private float mWavePeriod = NaN.0F;
  private CurveFit mWavePeriodSpline;
  private int mWaveShape = 0;
  
  public KeyTimeCycle()
  {
    mType = 1;
    mCustomConstraints = new HashMap();
  }
  
  public void addTimeValues(HashMap paramHashMap)
  {
    Iterator localIterator = paramHashMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject2 = (String)localIterator.next();
      Object localObject1 = (TimeCycleSplineSet)paramHashMap.get(localObject2);
      boolean bool = ((String)localObject2).startsWith("CUSTOM");
      int i = 1;
      if (bool)
      {
        localObject2 = ((String)localObject2).substring("CUSTOM".length() + 1);
        localObject2 = (ConstraintAttribute)mCustomConstraints.get(localObject2);
        if (localObject2 != null) {
          ((TimeCycleSplineSet.CustomSet)localObject1).setPoint(mFramePosition, (ConstraintAttribute)localObject2, mWavePeriod, mWaveShape, mWaveOffset);
        }
      }
      else
      {
        switch (((String)localObject2).hashCode())
        {
        default: 
          break;
        case 92909918: 
          if (((String)localObject2).equals("alpha")) {
            i = 0;
          }
          break;
        case 37232917: 
          if (((String)localObject2).equals("transitionPathRotate")) {
            i = 5;
          }
          break;
        case -4379043: 
          if (!((String)localObject2).equals("elevation")) {
            break;
          }
          break;
        case -40300674: 
          if (((String)localObject2).equals("rotation")) {
            i = 2;
          }
          break;
        case -908189617: 
          if (((String)localObject2).equals("scaleY")) {
            i = 7;
          }
          break;
        case -908189618: 
          if (((String)localObject2).equals("scaleX")) {
            i = 6;
          }
          break;
        case -1001078227: 
          if (((String)localObject2).equals("progress")) {
            i = 11;
          }
          break;
        case -1225497655: 
          if (((String)localObject2).equals("translationZ")) {
            i = 10;
          }
          break;
        case -1225497656: 
          if (((String)localObject2).equals("translationY")) {
            i = 9;
          }
          break;
        case -1225497657: 
          if (((String)localObject2).equals("translationX")) {
            i = 8;
          }
          break;
        case -1249320805: 
          if (((String)localObject2).equals("rotationY")) {
            i = 4;
          }
          break;
        case -1249320806: 
          if (((String)localObject2).equals("rotationX")) {
            i = 3;
          }
          break;
        }
        i = -1;
        switch (i)
        {
        default: 
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append("UNKNOWN addValues \"");
          ((StringBuilder)localObject1).append((String)localObject2);
          ((StringBuilder)localObject1).append("\"");
          Log.e("KeyTimeCycles", ((StringBuilder)localObject1).toString());
          break;
        case 11: 
          if (!Float.isNaN(mProgress)) {
            ((TimeCycleSplineSet)localObject1).setPoint(mFramePosition, mProgress, mWavePeriod, mWaveShape, mWaveOffset);
          }
          break;
        case 10: 
          if (!Float.isNaN(mTranslationZ)) {
            ((TimeCycleSplineSet)localObject1).setPoint(mFramePosition, mTranslationZ, mWavePeriod, mWaveShape, mWaveOffset);
          }
          break;
        case 9: 
          if (!Float.isNaN(mTranslationY)) {
            ((TimeCycleSplineSet)localObject1).setPoint(mFramePosition, mTranslationY, mWavePeriod, mWaveShape, mWaveOffset);
          }
          break;
        case 8: 
          if (!Float.isNaN(mTranslationX)) {
            ((TimeCycleSplineSet)localObject1).setPoint(mFramePosition, mTranslationX, mWavePeriod, mWaveShape, mWaveOffset);
          }
          break;
        case 7: 
          if (!Float.isNaN(mScaleY)) {
            ((TimeCycleSplineSet)localObject1).setPoint(mFramePosition, mScaleY, mWavePeriod, mWaveShape, mWaveOffset);
          }
          break;
        case 6: 
          if (!Float.isNaN(mScaleX)) {
            ((TimeCycleSplineSet)localObject1).setPoint(mFramePosition, mScaleX, mWavePeriod, mWaveShape, mWaveOffset);
          }
          break;
        case 5: 
          if (!Float.isNaN(mTransitionPathRotate)) {
            ((TimeCycleSplineSet)localObject1).setPoint(mFramePosition, mTransitionPathRotate, mWavePeriod, mWaveShape, mWaveOffset);
          }
          break;
        case 4: 
          if (!Float.isNaN(mRotationY)) {
            ((TimeCycleSplineSet)localObject1).setPoint(mFramePosition, mRotationY, mWavePeriod, mWaveShape, mWaveOffset);
          }
          break;
        case 3: 
          if (!Float.isNaN(mRotationX)) {
            ((TimeCycleSplineSet)localObject1).setPoint(mFramePosition, mRotationX, mWavePeriod, mWaveShape, mWaveOffset);
          }
          break;
        case 2: 
          if (!Float.isNaN(mRotation)) {
            ((TimeCycleSplineSet)localObject1).setPoint(mFramePosition, mRotation, mWavePeriod, mWaveShape, mWaveOffset);
          }
          break;
        case 1: 
          if (!Float.isNaN(mElevation)) {
            ((TimeCycleSplineSet)localObject1).setPoint(mFramePosition, mElevation, mWavePeriod, mWaveShape, mWaveOffset);
          }
          break;
        case 0: 
          if (!Float.isNaN(mAlpha)) {
            ((TimeCycleSplineSet)localObject1).setPoint(mFramePosition, mAlpha, mWavePeriod, mWaveShape, mWaveOffset);
          }
          break;
        }
      }
    }
  }
  
  public void addValues(HashMap paramHashMap)
  {
    throw new IllegalArgumentException(" KeyTimeCycles do not support SplineSet");
  }
  
  public void getAttributeNames(HashSet paramHashSet)
  {
    if (!Float.isNaN(mAlpha)) {
      paramHashSet.add("alpha");
    }
    if (!Float.isNaN(mElevation)) {
      paramHashSet.add("elevation");
    }
    if (!Float.isNaN(mRotation)) {
      paramHashSet.add("rotation");
    }
    if (!Float.isNaN(mRotationX)) {
      paramHashSet.add("rotationX");
    }
    if (!Float.isNaN(mRotationY)) {
      paramHashSet.add("rotationY");
    }
    if (!Float.isNaN(mTranslationX)) {
      paramHashSet.add("translationX");
    }
    if (!Float.isNaN(mTranslationY)) {
      paramHashSet.add("translationY");
    }
    if (!Float.isNaN(mTranslationZ)) {
      paramHashSet.add("translationZ");
    }
    if (!Float.isNaN(mTransitionPathRotate)) {
      paramHashSet.add("transitionPathRotate");
    }
    if (!Float.isNaN(mScaleX)) {
      paramHashSet.add("scaleX");
    }
    if (!Float.isNaN(mScaleY)) {
      paramHashSet.add("scaleY");
    }
    if (!Float.isNaN(mProgress)) {
      paramHashSet.add("progress");
    }
    if (mCustomConstraints.size() > 0)
    {
      Iterator localIterator = mCustomConstraints.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("CUSTOM,");
        localStringBuilder.append(str);
        paramHashSet.add(localStringBuilder.toString());
      }
    }
  }
  
  int getCurveFit()
  {
    return mCurveFit;
  }
  
  public void load(Context paramContext, AttributeSet paramAttributeSet)
  {
    Loader.read(this, paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.KeyTimeCycle));
  }
  
  public void setInterpolation(HashMap paramHashMap)
  {
    if (mCurveFit == -1) {
      return;
    }
    if (!Float.isNaN(mAlpha)) {
      paramHashMap.put("alpha", Integer.valueOf(mCurveFit));
    }
    if (!Float.isNaN(mElevation)) {
      paramHashMap.put("elevation", Integer.valueOf(mCurveFit));
    }
    if (!Float.isNaN(mRotation)) {
      paramHashMap.put("rotation", Integer.valueOf(mCurveFit));
    }
    if (!Float.isNaN(mRotationX)) {
      paramHashMap.put("rotationX", Integer.valueOf(mCurveFit));
    }
    if (!Float.isNaN(mRotationY)) {
      paramHashMap.put("rotationY", Integer.valueOf(mCurveFit));
    }
    if (!Float.isNaN(mTranslationX)) {
      paramHashMap.put("translationX", Integer.valueOf(mCurveFit));
    }
    if (!Float.isNaN(mTranslationY)) {
      paramHashMap.put("translationY", Integer.valueOf(mCurveFit));
    }
    if (!Float.isNaN(mTranslationZ)) {
      paramHashMap.put("translationZ", Integer.valueOf(mCurveFit));
    }
    if (!Float.isNaN(mTransitionPathRotate)) {
      paramHashMap.put("transitionPathRotate", Integer.valueOf(mCurveFit));
    }
    if (!Float.isNaN(mScaleX)) {
      paramHashMap.put("scaleX", Integer.valueOf(mCurveFit));
    }
    if (!Float.isNaN(mScaleX)) {
      paramHashMap.put("scaleY", Integer.valueOf(mCurveFit));
    }
    if (!Float.isNaN(mProgress)) {
      paramHashMap.put("progress", Integer.valueOf(mCurveFit));
    }
    if (mCustomConstraints.size() > 0)
    {
      Iterator localIterator = mCustomConstraints.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("CUSTOM,");
        localStringBuilder.append(str);
        paramHashMap.put(localStringBuilder.toString(), Integer.valueOf(mCurveFit));
      }
    }
  }
  
  public void setValue(String paramString, Object paramObject)
  {
    switch (paramString.hashCode())
    {
    default: 
      break;
    case 1317633238: 
      if (paramString.equals("mTranslationZ")) {
        i = 13;
      }
      break;
    case 579057826: 
      if (paramString.equals("curveFit")) {
        i = 1;
      }
      break;
    case 92909918: 
      if (paramString.equals("alpha")) {
        i = 0;
      }
      break;
    case 37232917: 
      if (paramString.equals("transitionPathRotate")) {
        i = 10;
      }
      break;
    case -4379043: 
      if (paramString.equals("elevation")) {
        i = 2;
      }
      break;
    case -40300674: 
      if (paramString.equals("rotation")) {
        i = 4;
      }
      break;
    case -908189617: 
      if (paramString.equals("scaleY")) {
        i = 8;
      }
      break;
    case -908189618: 
      if (paramString.equals("scaleX")) {
        i = 7;
      }
      break;
    case -1001078227: 
      if (paramString.equals("progress")) {
        i = 3;
      }
      break;
    case -1225497656: 
      if (paramString.equals("translationY")) {
        i = 12;
      }
      break;
    case -1225497657: 
      if (paramString.equals("translationX")) {
        i = 11;
      }
      break;
    case -1249320805: 
      if (paramString.equals("rotationY")) {
        i = 6;
      }
      break;
    case -1249320806: 
      if (paramString.equals("rotationX")) {
        i = 5;
      }
      break;
    case -1812823328: 
      if (paramString.equals("transitionEasing")) {
        i = 9;
      }
      break;
    }
    int i = -1;
    switch (i)
    {
    default: 
      return;
    case 13: 
      mTranslationZ = toFloat(paramObject);
      return;
    case 12: 
      mTranslationY = toFloat(paramObject);
      return;
    case 11: 
      mTranslationX = toFloat(paramObject);
      return;
    case 10: 
      mTransitionPathRotate = toFloat(paramObject);
      return;
    case 9: 
      mTransitionEasing = paramObject.toString();
      return;
    case 8: 
      mScaleY = toFloat(paramObject);
      return;
    case 7: 
      mScaleX = toFloat(paramObject);
      return;
    case 6: 
      mRotationY = toFloat(paramObject);
      return;
    case 5: 
      mRotationX = toFloat(paramObject);
      return;
    case 4: 
      mRotation = toFloat(paramObject);
      return;
    case 3: 
      mProgress = toFloat(paramObject);
      return;
    case 2: 
      mElevation = toFloat(paramObject);
      return;
    case 1: 
      mCurveFit = toInt(paramObject);
      return;
    }
    mAlpha = toFloat(paramObject);
  }
  
  private static class Loader
  {
    private static final int ANDROID_ALPHA = 1;
    private static final int ANDROID_ELEVATION = 2;
    private static final int ANDROID_ROTATION = 4;
    private static final int ANDROID_ROTATION_X = 5;
    private static final int ANDROID_ROTATION_Y = 6;
    private static final int ANDROID_SCALE_X = 7;
    private static final int ANDROID_SCALE_Y = 14;
    private static final int ANDROID_TRANSLATION_X = 15;
    private static final int ANDROID_TRANSLATION_Y = 16;
    private static final int ANDROID_TRANSLATION_Z = 17;
    private static final int CURVE_FIT = 13;
    private static final int FRAME_POSITION = 12;
    private static final int PROGRESS = 18;
    private static final int TARGET_ID = 10;
    private static final int TRANSITION_EASING = 9;
    private static final int TRANSITION_PATH_ROTATE = 8;
    private static final int WAVE_OFFSET = 21;
    private static final int WAVE_PERIOD = 20;
    private static final int WAVE_SHAPE = 19;
    private static SparseIntArray mAttrMap = new SparseIntArray();
    
    static
    {
      mAttrMap.append(R.styleable.KeyTimeCycle_android_alpha, 1);
      mAttrMap.append(R.styleable.KeyTimeCycle_android_elevation, 2);
      mAttrMap.append(R.styleable.KeyTimeCycle_android_rotation, 4);
      mAttrMap.append(R.styleable.KeyTimeCycle_android_rotationX, 5);
      mAttrMap.append(R.styleable.KeyTimeCycle_android_rotationY, 6);
      mAttrMap.append(R.styleable.KeyTimeCycle_android_scaleX, 7);
      mAttrMap.append(R.styleable.KeyTimeCycle_transitionPathRotate, 8);
      mAttrMap.append(R.styleable.KeyTimeCycle_transitionEasing, 9);
      mAttrMap.append(R.styleable.KeyTimeCycle_target, 10);
      mAttrMap.append(R.styleable.KeyTimeCycle_framePosition, 12);
      mAttrMap.append(R.styleable.KeyTimeCycle_curveFit, 13);
      mAttrMap.append(R.styleable.KeyTimeCycle_android_scaleY, 14);
      mAttrMap.append(R.styleable.KeyTimeCycle_android_translationX, 15);
      mAttrMap.append(R.styleable.KeyTimeCycle_android_translationY, 16);
      mAttrMap.append(R.styleable.KeyTimeCycle_android_translationZ, 17);
      mAttrMap.append(R.styleable.KeyTimeCycle_progress, 18);
      mAttrMap.append(R.styleable.KeyTimeCycle_wavePeriod, 20);
      mAttrMap.append(R.styleable.KeyTimeCycle_waveOffset, 21);
      mAttrMap.append(R.styleable.KeyTimeCycle_waveShape, 19);
    }
    
    private Loader() {}
    
    public static void read(KeyTimeCycle paramKeyTimeCycle, TypedArray paramTypedArray)
    {
      int j = paramTypedArray.getIndexCount();
      int i = 0;
      while (i < j)
      {
        int k = paramTypedArray.getIndex(i);
        switch (mAttrMap.get(k))
        {
        default: 
          break;
        case 3: 
        case 11: 
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("unused attribute 0x");
          localStringBuilder.append(Integer.toHexString(k));
          localStringBuilder.append("   ");
          localStringBuilder.append(mAttrMap.get(k));
          Log.e("KeyTimeCycle", localStringBuilder.toString());
          break;
        case 21: 
          if (peekValuetype == 5) {
            KeyTimeCycle.access$602(paramKeyTimeCycle, paramTypedArray.getDimension(k, mWaveOffset));
          } else {
            KeyTimeCycle.access$602(paramKeyTimeCycle, paramTypedArray.getFloat(k, mWaveOffset));
          }
          break;
        case 20: 
          KeyTimeCycle.access$502(paramKeyTimeCycle, paramTypedArray.getFloat(k, mWavePeriod));
          break;
        case 19: 
          KeyTimeCycle.access$402(paramKeyTimeCycle, paramTypedArray.getInt(k, mWaveShape));
          break;
        case 18: 
          KeyTimeCycle.access$1602(paramKeyTimeCycle, paramTypedArray.getFloat(k, mProgress));
          break;
        case 17: 
          KeyTimeCycle.access$1502(paramKeyTimeCycle, paramTypedArray.getDimension(k, mTranslationZ));
          break;
        case 16: 
          KeyTimeCycle.access$1402(paramKeyTimeCycle, paramTypedArray.getDimension(k, mTranslationY));
          break;
        case 15: 
          KeyTimeCycle.access$1302(paramKeyTimeCycle, paramTypedArray.getDimension(k, mTranslationX));
          break;
        case 14: 
          KeyTimeCycle.access$1102(paramKeyTimeCycle, paramTypedArray.getFloat(k, mScaleY));
          break;
        case 13: 
          KeyTimeCycle.access$302(paramKeyTimeCycle, paramTypedArray.getInteger(k, mCurveFit));
          break;
        case 12: 
          mFramePosition = paramTypedArray.getInt(k, mFramePosition);
          break;
        case 10: 
          mTargetId = paramTypedArray.getResourceId(k, mTargetId);
          break;
        case 9: 
          KeyTimeCycle.access$1002(paramKeyTimeCycle, paramTypedArray.getString(k));
          break;
        case 8: 
          KeyTimeCycle.access$1202(paramKeyTimeCycle, paramTypedArray.getFloat(k, mTransitionPathRotate));
          break;
        case 7: 
          KeyTimeCycle.access$702(paramKeyTimeCycle, paramTypedArray.getFloat(k, mScaleX));
          break;
        case 6: 
          KeyTimeCycle.access$902(paramKeyTimeCycle, paramTypedArray.getFloat(k, mRotationY));
          break;
        case 5: 
          KeyTimeCycle.access$802(paramKeyTimeCycle, paramTypedArray.getFloat(k, mRotationX));
          break;
        case 4: 
          KeyTimeCycle.access$202(paramKeyTimeCycle, paramTypedArray.getFloat(k, mRotation));
          break;
        case 2: 
          KeyTimeCycle.access$102(paramKeyTimeCycle, paramTypedArray.getDimension(k, mElevation));
          break;
        }
        KeyTimeCycle.access$002(paramKeyTimeCycle, paramTypedArray.getFloat(k, mAlpha));
        i += 1;
      }
    }
  }
}
