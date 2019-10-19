package android.support.constraint.motion;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintAttribute;
import android.support.constraint.ConstraintAttribute.AttributeType;
import android.support.constraint.R.styleable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.util.TypedValue;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class KeyCycle
  extends FieldInfo
{
  public static final int KEY_TYPE = 4;
  static final String NAME = "KeyCycle";
  private static final String PAGE_KEY = "KeyCycle";
  private float mAlpha = NaN.0F;
  private int mCurveFit = 0;
  private float mElevation = NaN.0F;
  private float mProgress = NaN.0F;
  private float mRotation = NaN.0F;
  private float mRotationX = NaN.0F;
  private float mRotationY = NaN.0F;
  private float mScaleX = NaN.0F;
  private float mScaleY = NaN.0F;
  private String mTransitionEasing = null;
  private float mTransitionPathRotate = NaN.0F;
  private float mTranslationX = NaN.0F;
  private float mTranslationY = NaN.0F;
  private float mTranslationZ = NaN.0F;
  private float mWaveOffset = 0.0F;
  private float mWavePeriod = NaN.0F;
  private int mWaveShape = -1;
  private int mWaveVariesBy = -1;
  
  public KeyCycle()
  {
    mType = 4;
    mCustomConstraints = new HashMap();
  }
  
  public void addCycleValues(HashMap paramHashMap)
  {
    Iterator localIterator = paramHashMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (str.startsWith("CUSTOM"))
      {
        Object localObject = str.substring("CUSTOM".length() + 1);
        localObject = (ConstraintAttribute)mCustomConstraints.get(localObject);
        if ((localObject != null) && (((ConstraintAttribute)localObject).getType() == ConstraintAttribute.AttributeType.FLOAT_TYPE)) {
          ((KeyCycleOscillator)paramHashMap.get(str)).setPoint(mFramePosition, mWaveShape, mWaveVariesBy, mWavePeriod, mWaveOffset, ((ConstraintAttribute)localObject).getValueToInterpolate(), (ConstraintAttribute)localObject);
        }
      }
      float f = getValue(str);
      if (!Float.isNaN(f)) {
        ((KeyCycleOscillator)paramHashMap.get(str)).setPoint(mFramePosition, mWaveShape, mWaveVariesBy, mWavePeriod, mWaveOffset, f);
      }
    }
  }
  
  public void addValues(HashMap paramHashMap)
  {
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("add ");
    ((StringBuilder)localObject1).append(paramHashMap.size());
    ((StringBuilder)localObject1).append(" values");
    Debug.logStack("KeyCycle", ((StringBuilder)localObject1).toString(), 2);
    localObject1 = paramHashMap.keySet().iterator();
    while (((Iterator)localObject1).hasNext())
    {
      String str = (String)((Iterator)localObject1).next();
      Object localObject2 = (SplineSet)paramHashMap.get(str);
      int i = -1;
      switch (str.hashCode())
      {
      default: 
        break;
      case 156108012: 
        if (str.equals("waveOffset")) {
          i = 11;
        }
        break;
      case 92909918: 
        if (str.equals("alpha")) {
          i = 0;
        }
        break;
      case 37232917: 
        if (str.equals("transitionPathRotate")) {
          i = 5;
        }
        break;
      case -4379043: 
        if (str.equals("elevation")) {
          i = 1;
        }
        break;
      case -40300674: 
        if (str.equals("rotation")) {
          i = 2;
        }
        break;
      case -908189617: 
        if (str.equals("scaleY")) {
          i = 7;
        }
        break;
      case -908189618: 
        if (str.equals("scaleX")) {
          i = 6;
        }
        break;
      case -1001078227: 
        if (str.equals("progress")) {
          i = 12;
        }
        break;
      case -1225497655: 
        if (str.equals("translationZ")) {
          i = 10;
        }
        break;
      case -1225497656: 
        if (str.equals("translationY")) {
          i = 9;
        }
        break;
      case -1225497657: 
        if (str.equals("translationX")) {
          i = 8;
        }
        break;
      case -1249320805: 
        if (str.equals("rotationY")) {
          i = 4;
        }
        break;
      case -1249320806: 
        if (str.equals("rotationX")) {
          i = 3;
        }
        break;
      }
      switch (i)
      {
      default: 
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("  UNKNOWN  ");
        ((StringBuilder)localObject2).append(str);
        Log.v("WARNING KeyCycle", ((StringBuilder)localObject2).toString());
        break;
      case 12: 
        ((SplineSet)localObject2).setPoint(mFramePosition, mProgress);
        break;
      case 11: 
        ((SplineSet)localObject2).setPoint(mFramePosition, mWaveOffset);
        break;
      case 10: 
        ((SplineSet)localObject2).setPoint(mFramePosition, mTranslationZ);
        break;
      case 9: 
        ((SplineSet)localObject2).setPoint(mFramePosition, mTranslationY);
        break;
      case 8: 
        ((SplineSet)localObject2).setPoint(mFramePosition, mTranslationX);
        break;
      case 7: 
        ((SplineSet)localObject2).setPoint(mFramePosition, mScaleY);
        break;
      case 6: 
        ((SplineSet)localObject2).setPoint(mFramePosition, mScaleX);
        break;
      case 5: 
        ((SplineSet)localObject2).setPoint(mFramePosition, mTransitionPathRotate);
        break;
      case 4: 
        ((SplineSet)localObject2).setPoint(mFramePosition, mRotationY);
        break;
      case 3: 
        ((SplineSet)localObject2).setPoint(mFramePosition, mRotationX);
        break;
      case 2: 
        ((SplineSet)localObject2).setPoint(mFramePosition, mRotation);
        break;
      case 1: 
        ((SplineSet)localObject2).setPoint(mFramePosition, mElevation);
        break;
      case 0: 
        ((SplineSet)localObject2).setPoint(mFramePosition, mAlpha);
      }
    }
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
    if (!Float.isNaN(mScaleX)) {
      paramHashSet.add("scaleX");
    }
    if (!Float.isNaN(mScaleY)) {
      paramHashSet.add("scaleY");
    }
    if (!Float.isNaN(mTransitionPathRotate)) {
      paramHashSet.add("transitionPathRotate");
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
  
  public float getValue(String paramString)
  {
    switch (paramString.hashCode())
    {
    default: 
      break;
    case 156108012: 
      if (paramString.equals("waveOffset")) {
        i = 11;
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
        i = 12;
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
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("  UNKNOWN  ");
      localStringBuilder.append(paramString);
      Log.v("WARNING! KeyCycle", localStringBuilder.toString());
      return NaN.0F;
    case 12: 
      return mProgress;
    case 11: 
      return mWaveOffset;
    case 10: 
      return mTranslationZ;
    case 9: 
      return mTranslationY;
    case 8: 
      return mTranslationX;
    case 7: 
      return mScaleY;
    case 6: 
      return mScaleX;
    case 5: 
      return mTransitionPathRotate;
    case 4: 
      return mRotationY;
    case 3: 
      return mRotationX;
    case 2: 
      return mRotation;
    case 1: 
      return mElevation;
    }
    return mAlpha;
  }
  
  public void load(Context paramContext, AttributeSet paramAttributeSet)
  {
    Loader.read(this, paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.KeyCycle));
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
    case 184161818: 
      if (paramString.equals("wavePeriod")) {
        i = 14;
      }
      break;
    case 156108012: 
      if (paramString.equals("waveOffset")) {
        i = 15;
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
    case 15: 
      mWaveOffset = toFloat(paramObject);
      return;
    case 14: 
      mWavePeriod = toFloat(paramObject);
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
    private static final int ANDROID_ALPHA = 9;
    private static final int ANDROID_ELEVATION = 10;
    private static final int ANDROID_ROTATION = 11;
    private static final int ANDROID_ROTATION_X = 12;
    private static final int ANDROID_ROTATION_Y = 13;
    private static final int ANDROID_SCALE_X = 15;
    private static final int ANDROID_SCALE_Y = 16;
    private static final int ANDROID_TRANSLATION_X = 17;
    private static final int ANDROID_TRANSLATION_Y = 18;
    private static final int ANDROID_TRANSLATION_Z = 19;
    private static final int CURVE_FIT = 4;
    private static final int FRAME_POSITION = 2;
    private static final int PROGRESS = 20;
    private static final int TARGET_ID = 1;
    private static final int TRANSITION_EASING = 3;
    private static final int TRANSITION_PATH_ROTATE = 14;
    private static final int WAVE_OFFSET = 7;
    private static final int WAVE_PERIOD = 6;
    private static final int WAVE_SHAPE = 5;
    private static final int WAVE_VARIES_BY = 8;
    private static SparseIntArray mAttrMap = new SparseIntArray();
    
    static
    {
      mAttrMap.append(R.styleable.KeyCycle_target, 1);
      mAttrMap.append(R.styleable.KeyCycle_framePosition, 2);
      mAttrMap.append(R.styleable.KeyCycle_transitionEasing, 3);
      mAttrMap.append(R.styleable.KeyCycle_curveFit, 4);
      mAttrMap.append(R.styleable.KeyCycle_waveShape, 5);
      mAttrMap.append(R.styleable.KeyCycle_wavePeriod, 6);
      mAttrMap.append(R.styleable.KeyCycle_waveOffset, 7);
      mAttrMap.append(R.styleable.KeyCycle_waveVariesBy, 8);
      mAttrMap.append(R.styleable.KeyCycle_android_alpha, 9);
      mAttrMap.append(R.styleable.KeyCycle_android_elevation, 10);
      mAttrMap.append(R.styleable.KeyCycle_android_rotation, 11);
      mAttrMap.append(R.styleable.KeyCycle_android_rotationX, 12);
      mAttrMap.append(R.styleable.KeyCycle_android_rotationY, 13);
      mAttrMap.append(R.styleable.KeyCycle_transitionPathRotate, 14);
      mAttrMap.append(R.styleable.KeyCycle_android_scaleX, 15);
      mAttrMap.append(R.styleable.KeyCycle_android_scaleY, 16);
      mAttrMap.append(R.styleable.KeyCycle_android_translationX, 17);
      mAttrMap.append(R.styleable.KeyCycle_android_translationY, 18);
      mAttrMap.append(R.styleable.KeyCycle_android_translationZ, 19);
      mAttrMap.append(R.styleable.KeyCycle_progress, 20);
    }
    
    private Loader() {}
    
    private static void read(KeyCycle paramKeyCycle, TypedArray paramTypedArray)
    {
      int j = paramTypedArray.getIndexCount();
      int i = 0;
      while (i < j)
      {
        int k = paramTypedArray.getIndex(i);
        switch (mAttrMap.get(k))
        {
        default: 
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("unused attribute 0x");
          localStringBuilder.append(Integer.toHexString(k));
          localStringBuilder.append("   ");
          localStringBuilder.append(mAttrMap.get(k));
          Log.e("KeyCycle", localStringBuilder.toString());
          break;
        case 20: 
          KeyCycle.access$1802(paramKeyCycle, paramTypedArray.getFloat(k, mProgress));
          break;
        case 19: 
          KeyCycle.access$1702(paramKeyCycle, paramTypedArray.getDimension(k, mTranslationZ));
          break;
        case 18: 
          KeyCycle.access$1602(paramKeyCycle, paramTypedArray.getDimension(k, mTranslationY));
          break;
        case 17: 
          KeyCycle.access$1502(paramKeyCycle, paramTypedArray.getDimension(k, mTranslationX));
          break;
        case 16: 
          KeyCycle.access$1402(paramKeyCycle, paramTypedArray.getFloat(k, mScaleY));
          break;
        case 15: 
          KeyCycle.access$1302(paramKeyCycle, paramTypedArray.getFloat(k, mScaleX));
          break;
        case 14: 
          KeyCycle.access$1202(paramKeyCycle, paramTypedArray.getFloat(k, mTransitionPathRotate));
          break;
        case 13: 
          KeyCycle.access$1102(paramKeyCycle, paramTypedArray.getFloat(k, mRotationY));
          break;
        case 12: 
          KeyCycle.access$1002(paramKeyCycle, paramTypedArray.getFloat(k, mRotationX));
          break;
        case 11: 
          KeyCycle.access$902(paramKeyCycle, paramTypedArray.getFloat(k, mRotation));
          break;
        case 10: 
          KeyCycle.access$802(paramKeyCycle, paramTypedArray.getDimension(k, mElevation));
          break;
        case 9: 
          KeyCycle.access$702(paramKeyCycle, paramTypedArray.getFloat(k, mAlpha));
          break;
        case 8: 
          KeyCycle.access$602(paramKeyCycle, paramTypedArray.getInt(k, mWaveVariesBy));
          break;
        case 7: 
          if (peekValuetype == 5) {
            KeyCycle.access$502(paramKeyCycle, paramTypedArray.getDimension(k, mWaveOffset));
          } else {
            KeyCycle.access$502(paramKeyCycle, paramTypedArray.getFloat(k, mWaveOffset));
          }
          break;
        case 6: 
          KeyCycle.access$402(paramKeyCycle, paramTypedArray.getFloat(k, mWavePeriod));
          break;
        case 5: 
          KeyCycle.access$302(paramKeyCycle, paramTypedArray.getInt(k, mWaveShape));
          break;
        case 4: 
          KeyCycle.access$202(paramKeyCycle, paramTypedArray.getInteger(k, mCurveFit));
          break;
        case 3: 
          KeyCycle.access$102(paramKeyCycle, paramTypedArray.getString(k));
          break;
        case 2: 
          mFramePosition = paramTypedArray.getInt(k, mFramePosition);
          break;
        case 1: 
          mTargetId = paramTypedArray.getResourceId(k, mTargetId);
        }
        i += 1;
      }
    }
  }
}
