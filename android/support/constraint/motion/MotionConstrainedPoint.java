package android.support.constraint.motion;

import android.os.Build.VERSION;
import android.support.constraint.ConstraintAttribute;
import android.support.constraint.ConstraintSet;
import android.support.constraint.ConstraintSet.Constraint;
import android.support.constraint.motion.utils.Easing;
import android.support.constraint.solver.widgets.ConstraintWidget;
import android.util.Log;
import android.view.View;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

class MotionConstrainedPoint
  implements Comparable<MotionConstrainedPoint>
{
  static final int CARTESIAN = 2;
  public static final boolean DEBUG = false;
  public static final String PAGE_KEY = "MotionPaths";
  static final int PERPENDICULAR = 1;
  static String[] names = { "position", "x", "y", "width", "height", "pathRotate" };
  private float alpha = 1.0F;
  private boolean applyElevation = false;
  LinkedHashMap<String, ConstraintAttribute> attributes = new LinkedHashMap();
  private float elevation = 0.0F;
  private float height;
  private int mDrawPath = 0;
  private Easing mKeyFrameEasing;
  int mMode = 0;
  private float mPathRotate = NaN.0F;
  private float mPivotX = NaN.0F;
  private float mPivotY = NaN.0F;
  private float mProgress = NaN.0F;
  double[] mTempDelta = new double[18];
  double[] mTempValue = new double[18];
  private float position;
  private float rotation = 0.0F;
  private float rotationX = 0.0F;
  public float rotationY = 0.0F;
  private float scaleX = 1.0F;
  private float scaleY = 1.0F;
  private float translationX = 0.0F;
  private float translationY = 0.0F;
  private float translationZ = 0.0F;
  int visibility;
  private float width;
  private float x;
  private float y;
  
  public MotionConstrainedPoint() {}
  
  private boolean diff(float paramFloat1, float paramFloat2)
  {
    if ((!Float.isNaN(paramFloat1)) && (!Float.isNaN(paramFloat2)))
    {
      if (Math.abs(paramFloat1 - paramFloat2) > 1.0E-6F) {
        return true;
      }
    }
    else if (Float.isNaN(paramFloat1) != Float.isNaN(paramFloat2)) {
      return true;
    }
    return false;
  }
  
  public void addValues(HashMap paramHashMap, int paramInt)
  {
    Iterator localIterator = paramHashMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject1 = (String)localIterator.next();
      Object localObject2 = (SplineSet)paramHashMap.get(localObject1);
      int i = -1;
      switch (((String)localObject1).hashCode())
      {
      default: 
        break;
      case 92909918: 
        if (((String)localObject1).equals("alpha")) {
          i = 0;
        }
        break;
      case 37232917: 
        if (((String)localObject1).equals("transitionPathRotate")) {
          i = 5;
        }
        break;
      case -4379043: 
        if (((String)localObject1).equals("elevation")) {
          i = 1;
        }
        break;
      case -40300674: 
        if (((String)localObject1).equals("rotation")) {
          i = 2;
        }
        break;
      case -908189617: 
        if (((String)localObject1).equals("scaleY")) {
          i = 8;
        }
        break;
      case -908189618: 
        if (((String)localObject1).equals("scaleX")) {
          i = 7;
        }
        break;
      case -1001078227: 
        if (((String)localObject1).equals("progress")) {
          i = 6;
        }
        break;
      case -1225497655: 
        if (((String)localObject1).equals("translationZ")) {
          i = 11;
        }
        break;
      case -1225497656: 
        if (((String)localObject1).equals("translationY")) {
          i = 10;
        }
        break;
      case -1225497657: 
        if (((String)localObject1).equals("translationX")) {
          i = 9;
        }
        break;
      case -1249320805: 
        if (((String)localObject1).equals("rotationY")) {
          i = 4;
        }
        break;
      case -1249320806: 
        if (((String)localObject1).equals("rotationX")) {
          i = 3;
        }
        break;
      }
      float f1 = 1.0F;
      float f3 = 0.0F;
      float f4 = 0.0F;
      float f5 = 0.0F;
      float f6 = 0.0F;
      float f7 = 0.0F;
      float f8 = 0.0F;
      float f9 = 0.0F;
      float f10 = 0.0F;
      float f2 = 0.0F;
      Object localObject3;
      switch (i)
      {
      default: 
        if (((String)localObject1).startsWith("CUSTOM"))
        {
          localObject3 = localObject1.split(",")[1];
          if (attributes.containsKey(localObject3))
          {
            localObject3 = (ConstraintAttribute)attributes.get(localObject3);
            if ((localObject2 instanceof SplineSet.CustomSet)) {
              ((SplineSet.CustomSet)localObject2).setPoint(paramInt, (ConstraintAttribute)localObject3);
            }
          }
        }
        break;
      case 11: 
        if (Float.isNaN(translationZ)) {
          f1 = f2;
        } else {
          f1 = translationZ;
        }
        ((SplineSet)localObject2).setPoint(paramInt, f1);
        break;
      case 10: 
        if (Float.isNaN(translationY)) {
          f1 = f3;
        } else {
          f1 = translationY;
        }
        ((SplineSet)localObject2).setPoint(paramInt, f1);
        break;
      case 9: 
        if (Float.isNaN(translationX)) {
          f1 = f4;
        } else {
          f1 = translationX;
        }
        ((SplineSet)localObject2).setPoint(paramInt, f1);
        break;
      case 8: 
        if (!Float.isNaN(scaleY)) {
          f1 = scaleY;
        }
        ((SplineSet)localObject2).setPoint(paramInt, f1);
        break;
      case 7: 
        if (!Float.isNaN(scaleX)) {
          f1 = scaleX;
        }
        ((SplineSet)localObject2).setPoint(paramInt, f1);
        break;
      case 6: 
        if (Float.isNaN(mProgress)) {
          f1 = f5;
        } else {
          f1 = mProgress;
        }
        ((SplineSet)localObject2).setPoint(paramInt, f1);
        break;
      case 5: 
        if (Float.isNaN(mPathRotate)) {
          f1 = f6;
        } else {
          f1 = mPathRotate;
        }
        ((SplineSet)localObject2).setPoint(paramInt, f1);
        break;
      case 4: 
        if (Float.isNaN(rotationY)) {
          f1 = f7;
        } else {
          f1 = rotationY;
        }
        ((SplineSet)localObject2).setPoint(paramInt, f1);
        break;
      case 3: 
        if (Float.isNaN(rotationX)) {
          f1 = f8;
        } else {
          f1 = rotationX;
        }
        ((SplineSet)localObject2).setPoint(paramInt, f1);
        break;
      case 2: 
        if (Float.isNaN(rotation)) {
          f1 = f9;
        } else {
          f1 = rotation;
        }
        ((SplineSet)localObject2).setPoint(paramInt, f1);
        break;
      case 1: 
        if (Float.isNaN(elevation)) {
          f1 = f10;
        } else {
          f1 = elevation;
        }
        ((SplineSet)localObject2).setPoint(paramInt, f1);
        break;
      case 0: 
        if (!Float.isNaN(alpha)) {
          f1 = alpha;
        }
        ((SplineSet)localObject2).setPoint(paramInt, f1);
        continue;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append((String)localObject1);
        localStringBuilder.append(" splineSet not a CustomSet frame = ");
        localStringBuilder.append(paramInt);
        localStringBuilder.append(", value");
        localStringBuilder.append(((ConstraintAttribute)localObject3).getValueToInterpolate());
        localStringBuilder.append(localObject2);
        Log.e("MotionPaths", localStringBuilder.toString());
        continue;
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("UNKNOWN customName ");
        ((StringBuilder)localObject1).append((String)localObject3);
        Log.e("MotionPaths", ((StringBuilder)localObject1).toString());
        continue;
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("UNKNOWN spline ");
        ((StringBuilder)localObject2).append((String)localObject1);
        Log.e("MotionPaths", ((StringBuilder)localObject2).toString());
      }
    }
  }
  
  public void applyParameters(ConstraintSet.Constraint paramConstraint)
  {
    visibility = visibility;
    float f;
    if (visibility != 0) {
      f = 0.0F;
    } else {
      f = alpha;
    }
    alpha = f;
    applyElevation = applyElevation;
    elevation = elevation;
    rotation = rotation;
    rotationX = rotationX;
    rotationY = rotationY;
    scaleX = scaleX;
    scaleY = scaleY;
    mPivotX = transformPivotX;
    mPivotY = transformPivotY;
    translationX = translationX;
    translationY = translationY;
    translationZ = translationZ;
    mKeyFrameEasing = Easing.getInterpolator(mTransitionEasing);
    mPathRotate = mPathRotate;
    mDrawPath = mDrawPath;
    mProgress = mProgress;
    attributes.putAll(mCustomConstraints);
  }
  
  public void applyParameters(View paramView)
  {
    visibility = paramView.getVisibility();
    float f;
    if (paramView.getVisibility() != 0) {
      f = 0.0F;
    } else {
      f = paramView.getAlpha();
    }
    alpha = f;
    applyElevation = false;
    if (Build.VERSION.SDK_INT >= 21) {
      elevation = paramView.getElevation();
    }
    rotation = paramView.getRotation();
    rotationX = paramView.getRotationX();
    rotationY = paramView.getRotationY();
    scaleX = paramView.getScaleX();
    scaleY = paramView.getScaleY();
    mPivotX = paramView.getPivotX();
    mPivotY = paramView.getPivotY();
    translationX = paramView.getTranslationX();
    translationY = paramView.getTranslationY();
    if (Build.VERSION.SDK_INT >= 21) {
      translationZ = paramView.getTranslationZ();
    }
  }
  
  public int compareTo(MotionConstrainedPoint paramMotionConstrainedPoint)
  {
    return Float.compare(position, position);
  }
  
  void different(MotionConstrainedPoint paramMotionConstrainedPoint, HashSet paramHashSet)
  {
    if (diff(alpha, alpha)) {
      paramHashSet.add("alpha");
    }
    if (diff(elevation, elevation)) {
      paramHashSet.add("elevation");
    }
    if (diff(alpha, alpha)) {
      paramHashSet.add("alpha");
    }
    if (diff(rotation, rotation)) {
      paramHashSet.add("rotation");
    }
    if ((!Float.isNaN(mPathRotate)) || (!Float.isNaN(mPathRotate))) {
      paramHashSet.add("transitionPathRotate");
    }
    if ((!Float.isNaN(mProgress)) || (!Float.isNaN(mProgress))) {
      paramHashSet.add("progress");
    }
    if (diff(rotationX, rotationX)) {
      paramHashSet.add("rotationX");
    }
    if (diff(rotationY, rotationY)) {
      paramHashSet.add("rotationY");
    }
    if (diff(scaleX, scaleX)) {
      paramHashSet.add("scaleX");
    }
    if (diff(scaleY, scaleY)) {
      paramHashSet.add("scaleY");
    }
    if (diff(translationX, translationX)) {
      paramHashSet.add("translationX");
    }
    if (diff(translationY, translationY)) {
      paramHashSet.add("translationY");
    }
    if (diff(translationZ, translationZ)) {
      paramHashSet.add("translationZ");
    }
  }
  
  void different(MotionConstrainedPoint paramMotionConstrainedPoint, boolean[] paramArrayOfBoolean, String[] paramArrayOfString)
  {
    paramArrayOfBoolean[0] |= diff(position, position);
    paramArrayOfBoolean[1] |= diff(x, x);
    paramArrayOfBoolean[2] |= diff(y, y);
    paramArrayOfBoolean[3] |= diff(width, width);
    int i = paramArrayOfBoolean[4];
    paramArrayOfBoolean[4] = (diff(height, height) | i);
  }
  
  void fillStandard(double[] paramArrayOfDouble, int[] paramArrayOfInt)
  {
    float[] arrayOfFloat = new float[18];
    float f = position;
    int i = 0;
    arrayOfFloat[0] = f;
    arrayOfFloat[1] = x;
    arrayOfFloat[2] = y;
    arrayOfFloat[3] = width;
    arrayOfFloat[4] = height;
    arrayOfFloat[5] = alpha;
    arrayOfFloat[6] = elevation;
    arrayOfFloat[7] = rotation;
    arrayOfFloat[8] = rotationX;
    arrayOfFloat[9] = rotationY;
    arrayOfFloat[10] = scaleX;
    arrayOfFloat[11] = scaleY;
    arrayOfFloat[12] = mPivotX;
    arrayOfFloat[13] = mPivotY;
    arrayOfFloat[14] = translationX;
    arrayOfFloat[15] = translationY;
    arrayOfFloat[16] = translationZ;
    arrayOfFloat[17] = mPathRotate;
    int k;
    for (int j = 0; i < paramArrayOfInt.length; j = k)
    {
      k = j;
      if (paramArrayOfInt[i] < arrayOfFloat.length)
      {
        paramArrayOfDouble[j] = arrayOfFloat[paramArrayOfInt[i]];
        k = j + 1;
      }
      i += 1;
    }
  }
  
  int getCustomData(String paramString, double[] paramArrayOfDouble, int paramInt)
  {
    paramString = (ConstraintAttribute)attributes.get(paramString);
    if (paramString.noOfInterpValues() == 1)
    {
      paramArrayOfDouble[paramInt] = paramString.getValueToInterpolate();
      return 1;
    }
    int j = paramString.noOfInterpValues();
    float[] arrayOfFloat = new float[j];
    paramString.getValuesToInterpolate(arrayOfFloat);
    int i = 0;
    while (i < j)
    {
      paramArrayOfDouble[paramInt] = arrayOfFloat[i];
      i += 1;
      paramInt += 1;
    }
    return j;
  }
  
  int getCustomDataCount(String paramString)
  {
    return ((ConstraintAttribute)attributes.get(paramString)).noOfInterpValues();
  }
  
  boolean hasCustomData(String paramString)
  {
    return attributes.containsKey(paramString);
  }
  
  void setBounds(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    x = paramFloat1;
    y = paramFloat2;
    width = paramFloat3;
    height = paramFloat4;
  }
  
  public void setState(ConstraintWidget paramConstraintWidget, ConstraintSet paramConstraintSet, int paramInt)
  {
    setBounds(paramConstraintWidget.getX(), paramConstraintWidget.getY(), paramConstraintWidget.getWidth(), paramConstraintWidget.getHeight());
    applyParameters(paramConstraintSet.getParameters(paramInt));
  }
  
  public void setState(View paramView)
  {
    setBounds(paramView.getX(), paramView.getY(), paramView.getWidth(), paramView.getHeight());
    applyParameters(paramView);
  }
}
