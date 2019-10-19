package android.support.constraint.motion;

import android.content.Context;
import android.support.constraint.ConstraintAttribute;
import android.util.AttributeSet;
import java.util.HashMap;
import java.util.HashSet;

public abstract class FieldInfo
{
  static final String ALPHA = "alpha";
  static final String CUSTOM = "CUSTOM";
  static final String ELEVATION = "elevation";
  static final String PROGRESS = "progress";
  static final String ROTATION = "rotation";
  static final String ROTATION_X = "rotationX";
  static final String ROTATION_Y = "rotationY";
  static final String SCALE_X = "scaleX";
  static final String SCALE_Y = "scaleY";
  static final String TRANSITION_PATH_ROTATE = "transitionPathRotate";
  static final String TRANSLATION_X = "translationX";
  static final String TRANSLATION_Y = "translationY";
  static final String TRANSLATION_Z = "translationZ";
  public static int UNSET = 0;
  static final String WAVE_OFFSET = "waveOffset";
  static final String WAVE_PERIOD = "wavePeriod";
  static final String WAVE_VARIES_BY = "waveVariesBy";
  HashMap<String, ConstraintAttribute> mCustomConstraints;
  int mFramePosition = UNSET;
  int mTargetId = UNSET;
  protected int mType;
  
  public FieldInfo() {}
  
  public abstract void addValues(HashMap paramHashMap);
  
  abstract void getAttributeNames(HashSet paramHashSet);
  
  abstract void load(Context paramContext, AttributeSet paramAttributeSet);
  
  public void setInterpolation(HashMap paramHashMap) {}
  
  public abstract void setValue(String paramString, Object paramObject);
  
  boolean toBoolean(Object paramObject)
  {
    if ((paramObject instanceof Boolean)) {
      return ((Boolean)paramObject).booleanValue();
    }
    return Boolean.parseBoolean(paramObject.toString());
  }
  
  float toFloat(Object paramObject)
  {
    if ((paramObject instanceof Float)) {
      return ((Float)paramObject).floatValue();
    }
    return Float.parseFloat(paramObject.toString());
  }
  
  int toInt(Object paramObject)
  {
    if ((paramObject instanceof Integer)) {
      return ((Integer)paramObject).intValue();
    }
    return Integer.parseInt(paramObject.toString());
  }
}
