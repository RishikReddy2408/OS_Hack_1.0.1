package android.support.constraint.motion;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.support.constraint.R.styleable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import java.io.PrintStream;
import java.util.HashMap;

public class KeyPosition
  extends KeyPositionBase
{
  static final int KEY_TYPE = 2;
  static final String NAME = "KeyPosition";
  private static final String PAGE_KEY = "KeyPosition";
  private static final String PERCENT_X = "percentX";
  private static final String PERCENT_Y = "percentY";
  public static final int TYPE_CARTESIAN = 0;
  public static final int TYPE_PATH = 1;
  public static final int TYPE_SCREEN = 2;
  float mAltPercentX = NaN.0F;
  float mAltPercentY = NaN.0F;
  private float mCalculatedPositionX = NaN.0F;
  private float mCalculatedPositionY = NaN.0F;
  int mDrawPath = 0;
  int mPathMotionArc = FieldInfo.UNSET;
  float mPercentX = NaN.0F;
  float mPercentY = NaN.0F;
  int mPositionType = 0;
  float mSizePercent = NaN.0F;
  String mTransitionEasing = null;
  
  public KeyPosition()
  {
    mType = 2;
  }
  
  private void calcCartesianPosition(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    float f3 = paramFloat3 - paramFloat1;
    float f4 = paramFloat4 - paramFloat2;
    boolean bool = Float.isNaN(mPercentX);
    float f2 = 0.0F;
    if (bool) {
      paramFloat3 = 0.0F;
    } else {
      paramFloat3 = mPercentX;
    }
    if (Float.isNaN(mAltPercentY)) {
      paramFloat4 = 0.0F;
    } else {
      paramFloat4 = mAltPercentY;
    }
    float f1;
    if (Float.isNaN(mPercentY)) {
      f1 = 0.0F;
    } else {
      f1 = mPercentY;
    }
    if (!Float.isNaN(mAltPercentX)) {
      f2 = mAltPercentX;
    }
    mCalculatedPositionX = ((int)(paramFloat1 + paramFloat3 * f3 + f2 * f4));
    mCalculatedPositionY = ((int)(paramFloat2 + f3 * paramFloat4 + f4 * f1));
  }
  
  private void calcPathPosition(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    paramFloat3 -= paramFloat1;
    paramFloat4 -= paramFloat2;
    float f = -paramFloat4;
    mCalculatedPositionX = (paramFloat1 + mPercentX * paramFloat3 + f * mPercentY);
    mCalculatedPositionY = (paramFloat2 + paramFloat4 * mPercentX + paramFloat3 * mPercentY);
  }
  
  private void calcScreenPosition(int paramInt1, int paramInt2)
  {
    mCalculatedPositionX = ((paramInt1 - 0) * mPercentX + 0.0F);
    mCalculatedPositionY = ((paramInt2 - 0) * mPercentX + 0.0F);
  }
  
  public void addValues(HashMap paramHashMap) {}
  
  void calcPosition(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    switch (mPositionType)
    {
    default: 
      calcCartesianPosition(paramFloat1, paramFloat2, paramFloat3, paramFloat4);
      return;
    case 2: 
      calcScreenPosition(paramInt1, paramInt2);
      return;
    }
    calcPathPosition(paramFloat1, paramFloat2, paramFloat3, paramFloat4);
  }
  
  float getPositionX()
  {
    return mCalculatedPositionX;
  }
  
  float getPositionY()
  {
    return mCalculatedPositionY;
  }
  
  public boolean intersects(int paramInt1, int paramInt2, RectF paramRectF1, RectF paramRectF2, float paramFloat1, float paramFloat2)
  {
    calcPosition(paramInt1, paramInt2, paramRectF1.centerX(), paramRectF1.centerY(), paramRectF2.centerX(), paramRectF2.centerY());
    return (Math.abs(paramFloat1 - mCalculatedPositionX) < 20.0F) && (Math.abs(paramFloat2 - mCalculatedPositionY) < 20.0F);
  }
  
  public void load(Context paramContext, AttributeSet paramAttributeSet)
  {
    Loader.read(this, paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.KeyPosition));
  }
  
  public void positionAttributes(View paramView, RectF paramRectF1, RectF paramRectF2, float paramFloat1, float paramFloat2, String[] paramArrayOfString, float[] paramArrayOfFloat)
  {
    switch (mPositionType)
    {
    default: 
      positionCartAttributes(paramRectF1, paramRectF2, paramFloat1, paramFloat2, paramArrayOfString, paramArrayOfFloat);
      return;
    case 2: 
      positionScreenAttributes(paramView, paramRectF1, paramRectF2, paramFloat1, paramFloat2, paramArrayOfString, paramArrayOfFloat);
      return;
    }
    positionPathAttributes(paramRectF1, paramRectF2, paramFloat1, paramFloat2, paramArrayOfString, paramArrayOfFloat);
  }
  
  void positionCartAttributes(RectF paramRectF1, RectF paramRectF2, float paramFloat1, float paramFloat2, String[] paramArrayOfString, float[] paramArrayOfFloat)
  {
    float f1 = paramRectF1.centerX();
    float f2 = paramRectF1.centerY();
    float f4 = paramRectF2.centerX();
    float f3 = paramRectF2.centerY();
    f4 -= f1;
    f3 -= f2;
    if (paramArrayOfString[0] != null)
    {
      if ("percentX".equals(paramArrayOfString[0]))
      {
        paramArrayOfFloat[0] = ((paramFloat1 - f1) / f4);
        paramArrayOfFloat[1] = ((paramFloat2 - f2) / f3);
        return;
      }
      paramArrayOfFloat[1] = ((paramFloat1 - f1) / f4);
      paramArrayOfFloat[0] = ((paramFloat2 - f2) / f3);
      return;
    }
    paramArrayOfString[0] = "percentX";
    paramArrayOfFloat[0] = ((paramFloat1 - f1) / f4);
    paramArrayOfString[1] = "percentY";
    paramArrayOfFloat[1] = ((paramFloat2 - f2) / f3);
  }
  
  void positionPathAttributes(RectF paramRectF1, RectF paramRectF2, float paramFloat1, float paramFloat2, String[] paramArrayOfString, float[] paramArrayOfFloat)
  {
    float f1 = paramRectF1.centerX();
    float f3 = paramRectF1.centerY();
    float f4 = paramRectF2.centerX();
    float f2 = paramRectF2.centerY();
    f4 -= f1;
    float f5 = f2 - f3;
    f2 = (float)Math.hypot(f4, f5);
    if (f2 < 1.0E-4D)
    {
      System.out.println("distance ~ 0");
      paramArrayOfFloat[0] = 0.0F;
      paramArrayOfFloat[1] = 0.0F;
      return;
    }
    f4 /= f2;
    f5 /= f2;
    paramFloat2 -= f3;
    f1 = paramFloat1 - f1;
    paramFloat1 = (f4 * paramFloat2 - f1 * f5) / f2;
    paramFloat2 = (f4 * f1 + f5 * paramFloat2) / f2;
    if (paramArrayOfString[0] != null)
    {
      if ("percentX".equals(paramArrayOfString[0]))
      {
        paramArrayOfFloat[0] = paramFloat2;
        paramArrayOfFloat[1] = paramFloat1;
      }
    }
    else
    {
      paramArrayOfString[0] = "percentX";
      paramArrayOfString[1] = "percentY";
      paramArrayOfFloat[0] = paramFloat2;
      paramArrayOfFloat[1] = paramFloat1;
    }
  }
  
  void positionScreenAttributes(View paramView, RectF paramRectF1, RectF paramRectF2, float paramFloat1, float paramFloat2, String[] paramArrayOfString, float[] paramArrayOfFloat)
  {
    paramRectF1.centerX();
    paramRectF1.centerY();
    paramRectF2.centerX();
    paramRectF2.centerY();
    paramView = (ViewGroup)paramView.getParent();
    int i = paramView.getWidth();
    int j = paramView.getHeight();
    if (paramArrayOfString[0] != null)
    {
      if ("percentX".equals(paramArrayOfString[0]))
      {
        paramArrayOfFloat[0] = (paramFloat1 / i);
        paramArrayOfFloat[1] = (paramFloat2 / j);
        return;
      }
      paramArrayOfFloat[1] = (paramFloat1 / i);
      paramArrayOfFloat[0] = (paramFloat2 / j);
      return;
    }
    paramArrayOfString[0] = "percentX";
    paramArrayOfFloat[0] = (paramFloat1 / i);
    paramArrayOfString[1] = "percentY";
    paramArrayOfFloat[1] = (paramFloat2 / j);
  }
  
  public void setValue(String paramString, Object paramObject)
  {
    switch (paramString.hashCode())
    {
    default: 
      break;
    case 428090548: 
      if (paramString.equals("percentY")) {
        i = 4;
      }
      break;
    case 428090547: 
      if (paramString.equals("percentX")) {
        i = 3;
      }
      break;
    case -200259324: 
      if (paramString.equals("sizePercent")) {
        i = 2;
      }
      break;
    case -827014263: 
      if (paramString.equals("drawPath")) {
        i = 1;
      }
      break;
    case -1812823328: 
      if (paramString.equals("transitionEasing")) {
        i = 0;
      }
      break;
    }
    int i = -1;
    switch (i)
    {
    default: 
      return;
    case 4: 
      mPercentY = toFloat(paramObject);
      return;
    case 3: 
      mPercentX = toFloat(paramObject);
      return;
    case 2: 
      mSizePercent = toFloat(paramObject);
      return;
    case 1: 
      mDrawPath = toInt(paramObject);
      return;
    }
    mTransitionEasing = paramObject.toString();
  }
  
  private static class Loader
  {
    private static final int CURVE_FIT = 4;
    private static final int DRAW_PATH = 5;
    private static final int FRAME_POSITION = 2;
    private static final int PATH_MOTION_ARC = 10;
    private static final int PERCENT_X = 6;
    private static final int PERCENT_Y = 7;
    private static final int SIZE_PERCENT = 8;
    private static final int TARGET_ID = 1;
    private static final int TRANSITION_EASING = 3;
    private static final int TYPE = 9;
    private static SparseIntArray mAttrMap = new SparseIntArray();
    
    static
    {
      mAttrMap.append(R.styleable.KeyPosition_target, 1);
      mAttrMap.append(R.styleable.KeyPosition_framePosition, 2);
      mAttrMap.append(R.styleable.KeyPosition_transitionEasing, 3);
      mAttrMap.append(R.styleable.KeyPosition_curveFit, 4);
      mAttrMap.append(R.styleable.KeyPosition_drawPath, 5);
      mAttrMap.append(R.styleable.KeyPosition_percentX, 6);
      mAttrMap.append(R.styleable.KeyPosition_percentY, 7);
      mAttrMap.append(R.styleable.KeyPosition_keyPositionType, 9);
      mAttrMap.append(R.styleable.KeyPosition_sizePercent, 8);
      mAttrMap.append(R.styleable.KeyPosition_pathMotionArc, 10);
    }
    
    private Loader() {}
    
    private static void read(KeyPosition paramKeyPosition, TypedArray paramTypedArray)
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
          Log.e("KeyPosition", localStringBuilder.toString());
          break;
        case 10: 
          mPathMotionArc = paramTypedArray.getInt(k, mPathMotionArc);
          break;
        case 9: 
          mPositionType = paramTypedArray.getInt(k, mPositionType);
          break;
        case 8: 
          mSizePercent = paramTypedArray.getFloat(k, mSizePercent);
          break;
        case 7: 
          mPercentY = paramTypedArray.getFloat(k, mPercentY);
          break;
        case 6: 
          mPercentX = paramTypedArray.getFloat(k, mPercentX);
          break;
        case 5: 
          mDrawPath = paramTypedArray.getInt(k, mDrawPath);
          break;
        case 4: 
          mCurveFit = paramTypedArray.getInteger(k, mCurveFit);
          break;
        case 3: 
          if (peekValuetype == 3) {
            mTransitionEasing = paramTypedArray.getString(k);
          } else {
            mTransitionEasing = android.support.constraint.motion.utils.Easing.NAMED_EASING[paramTypedArray.getInteger(k, 0)];
          }
          break;
        case 2: 
          mFramePosition = paramTypedArray.getInt(k, mFramePosition);
          break;
        case 1: 
          mTargetId = paramTypedArray.getResourceId(k, mTargetId);
        }
        i += 1;
      }
      if (mFramePosition == -1) {
        Log.e("KeyPosition", "no frame position");
      }
    }
  }
}
