package android.support.constraint;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.support.constraint.motion.Debug;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ConstraintSet
{
  private static final int ALPHA = 43;
  private static final int ANIMATE_RELATIVE_TO = 64;
  private static final int BARRIER_ALLOWS_GONE_WIDGETS = 74;
  private static final int BARRIER_DIRECTION = 72;
  private static final int BARRIER_TYPE = 1;
  public static final int BASELINE = 5;
  private static final int BASELINE_TO_BASELINE = 1;
  public static final int BOTTOM = 4;
  private static final int BOTTOM_MARGIN = 2;
  private static final int BOTTOM_TO_BOTTOM = 3;
  private static final int BOTTOM_TO_TOP = 4;
  public static final int CHAIN_PACKED = 2;
  public static final int CHAIN_SPREAD = 0;
  public static final int CHAIN_SPREAD_INSIDE = 1;
  private static final int CHAIN_USE_RTL = 71;
  private static final int CIRCLE = 61;
  private static final int CIRCLE_ANGLE = 63;
  private static final int CIRCLE_RADIUS = 62;
  private static final int CONSTRAINT_REFERENCED_IDS = 73;
  private static final boolean DEBUG = false;
  private static final int DIMENSION_RATIO = 5;
  private static final int DRAW_PATH = 66;
  private static final int EDITOR_ABSOLUTE_X = 6;
  private static final int EDITOR_ABSOLUTE_Y = 7;
  private static final int ELEVATION = 44;
  private static final int END_MARGIN = 8;
  private static final int END_TO_END = 9;
  private static final int END_TO_START = 10;
  public static final int GONE = 8;
  private static final int GONE_BOTTOM_MARGIN = 11;
  private static final int GONE_END_MARGIN = 12;
  private static final int GONE_LEFT_MARGIN = 13;
  private static final int GONE_RIGHT_MARGIN = 14;
  private static final int GONE_START_MARGIN = 15;
  private static final int GONE_TOP_MARGIN = 16;
  private static final int GUIDE_BEGIN = 17;
  private static final int GUIDE_END = 18;
  private static final int GUIDE_PERCENT = 19;
  private static final int HEIGHT_DEFAULT = 55;
  private static final int HEIGHT_MAX = 57;
  private static final int HEIGHT_MIN = 59;
  private static final int HEIGHT_PERCENT = 70;
  public static final int HORIZONTAL = 0;
  private static final int HORIZONTAL_BIAS = 20;
  public static final int HORIZONTAL_GUIDELINE = 0;
  private static final int HORIZONTAL_STYLE = 41;
  private static final int HORIZONTAL_WEIGHT = 39;
  public static final int INVISIBLE = 4;
  private static final int LAYOUT_HEIGHT = 21;
  private static final int LAYOUT_VISIBILITY = 22;
  private static final int LAYOUT_WIDTH = 23;
  public static final int LEFT = 1;
  private static final int LEFT_MARGIN = 24;
  private static final int LEFT_TO_LEFT = 25;
  private static final int LEFT_TO_RIGHT = 26;
  public static final int MATCH_CONSTRAINT = 0;
  public static final int MATCH_CONSTRAINT_SPREAD = 0;
  public static final int MATCH_CONSTRAINT_WRAP = 1;
  private static final int ORIENTATION = 27;
  private static final String PAGE_KEY = "ConstraintSet";
  public static final int PARENT_ID = 0;
  private static final int PATH_MOTION_ARC = 75;
  public static final int PRIORITY_MIDHIGH = 7;
  private static final int PROGRESS = 68;
  public static final int RIGHT = 2;
  private static final int RIGHT_MARGIN = 28;
  private static final int RIGHT_TO_LEFT = 29;
  private static final int RIGHT_TO_RIGHT = 30;
  private static final int ROTATION = 60;
  private static final int ROTATION_X = 45;
  private static final int ROTATION_Y = 46;
  private static final int SCALE_X = 47;
  private static final int SCALE_Y = 48;
  public static final int SORT_MENU_ITEM = 3;
  public static final int START = 6;
  private static final int START_MARGIN = 31;
  private static final int START_TO_END = 32;
  private static final int START_TO_START = 33;
  private static final int TOP_MARGIN = 34;
  private static final int TOP_TO_BOTTOM = 35;
  private static final int TOP_TO_TOP = 36;
  private static final int TRANSFORM_PIVOT_X = 49;
  private static final int TRANSFORM_PIVOT_Y = 50;
  private static final int TRANSITION_EASING = 65;
  private static final int TRANSITION_PATH_ROTATE = 67;
  private static final int TRANSLATION_X = 51;
  private static final int TRANSLATION_Y = 52;
  private static final int TRANSLATION_Z = 53;
  public static final int UNSET = -1;
  private static final int UNUSED = 76;
  public static final int VERTICAL = 1;
  private static final int VERTICAL_BIAS = 37;
  public static final int VERTICAL_GUIDELINE = 1;
  private static final int VERTICAL_STYLE = 42;
  private static final int VERTICAL_WEIGHT = 40;
  private static final int VIEW_ID = 38;
  private static final int[] VISIBILITY_FLAGS = { 0, 4, 8 };
  public static final int VISIBLE = 0;
  private static final int WIDTH_DEFAULT = 54;
  private static final int WIDTH_MAX = 56;
  private static final int WIDTH_MIN = 58;
  private static final int WIDTH_PERCENT = 69;
  public static final int WRAP_CONTENT = -2;
  private static SparseIntArray mapToConstant = new SparseIntArray();
  private HashMap<Integer, Constraint> mConstraints = new HashMap();
  private HashMap<String, ConstraintAttribute> mSavedAttributes = new HashMap();
  
  static
  {
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintLeft_toLeftOf, 25);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintLeft_toRightOf, 26);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintRight_toLeftOf, 29);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintRight_toRightOf, 30);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintTop_toTopOf, 36);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintTop_toBottomOf, 35);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintBottom_toTopOf, 4);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintBottom_toBottomOf, 3);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintBaseline_toBaselineOf, 1);
    mapToConstant.append(R.styleable.ConstraintSet_layout_editor_absoluteX, 6);
    mapToConstant.append(R.styleable.ConstraintSet_layout_editor_absoluteY, 7);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintGuide_begin, 17);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintGuide_end, 18);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintGuide_percent, 19);
    mapToConstant.append(R.styleable.ConstraintSet_android_orientation, 27);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintStart_toEndOf, 32);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintStart_toStartOf, 33);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintEnd_toStartOf, 10);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintEnd_toEndOf, 9);
    mapToConstant.append(R.styleable.ConstraintSet_layout_goneMarginLeft, 13);
    mapToConstant.append(R.styleable.ConstraintSet_layout_goneMarginTop, 16);
    mapToConstant.append(R.styleable.ConstraintSet_layout_goneMarginRight, 14);
    mapToConstant.append(R.styleable.ConstraintSet_layout_goneMarginBottom, 11);
    mapToConstant.append(R.styleable.ConstraintSet_layout_goneMarginStart, 15);
    mapToConstant.append(R.styleable.ConstraintSet_layout_goneMarginEnd, 12);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintVertical_weight, 40);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintHorizontal_weight, 39);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintHorizontal_chainStyle, 41);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintVertical_chainStyle, 42);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintHorizontal_bias, 20);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintVertical_bias, 37);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintDimensionRatio, 5);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintLeft_creator, 76);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintTop_creator, 76);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintRight_creator, 76);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintBottom_creator, 76);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintBaseline_creator, 76);
    mapToConstant.append(R.styleable.ConstraintSet_android_layout_marginLeft, 24);
    mapToConstant.append(R.styleable.ConstraintSet_android_layout_marginRight, 28);
    mapToConstant.append(R.styleable.ConstraintSet_android_layout_marginStart, 31);
    mapToConstant.append(R.styleable.ConstraintSet_android_layout_marginEnd, 8);
    mapToConstant.append(R.styleable.ConstraintSet_android_layout_marginTop, 34);
    mapToConstant.append(R.styleable.ConstraintSet_android_layout_marginBottom, 2);
    mapToConstant.append(R.styleable.ConstraintSet_android_layout_width, 23);
    mapToConstant.append(R.styleable.ConstraintSet_android_layout_height, 21);
    mapToConstant.append(R.styleable.ConstraintSet_android_visibility, 22);
    mapToConstant.append(R.styleable.ConstraintSet_android_alpha, 43);
    mapToConstant.append(R.styleable.ConstraintSet_android_elevation, 44);
    mapToConstant.append(R.styleable.ConstraintSet_android_rotationX, 45);
    mapToConstant.append(R.styleable.ConstraintSet_android_rotationY, 46);
    mapToConstant.append(R.styleable.ConstraintSet_android_rotation, 60);
    mapToConstant.append(R.styleable.ConstraintSet_android_scaleX, 47);
    mapToConstant.append(R.styleable.ConstraintSet_android_scaleY, 48);
    mapToConstant.append(R.styleable.ConstraintSet_android_transformPivotX, 49);
    mapToConstant.append(R.styleable.ConstraintSet_android_transformPivotY, 50);
    mapToConstant.append(R.styleable.ConstraintSet_android_translationX, 51);
    mapToConstant.append(R.styleable.ConstraintSet_android_translationY, 52);
    mapToConstant.append(R.styleable.ConstraintSet_android_translationZ, 53);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintWidth_default, 54);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintHeight_default, 55);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintWidth_max, 56);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintHeight_max, 57);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintWidth_min, 58);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintHeight_min, 59);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintCircle, 61);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintCircleRadius, 62);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintCircleAngle, 63);
    mapToConstant.append(R.styleable.ConstraintSet_animate_relativeTo, 64);
    mapToConstant.append(R.styleable.ConstraintSet_transitionEasing, 65);
    mapToConstant.append(R.styleable.ConstraintSet_drawPath, 66);
    mapToConstant.append(R.styleable.ConstraintSet_transitionPathRotate, 67);
    mapToConstant.append(R.styleable.ConstraintSet_android_id, 38);
    mapToConstant.append(R.styleable.ConstraintSet_progress, 68);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintWidth_percent, 69);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintHeight_percent, 70);
    mapToConstant.append(R.styleable.ConstraintSet_chainUseRtl, 71);
    mapToConstant.append(R.styleable.ConstraintSet_barrierDirection, 72);
    mapToConstant.append(R.styleable.ConstraintSet_constraint_referenced_ids, 73);
    mapToConstant.append(R.styleable.ConstraintSet_barrierAllowsGoneWidgets, 74);
    mapToConstant.append(R.styleable.ConstraintSet_pathMotionArc, 75);
  }
  
  public ConstraintSet() {}
  
  private void addAttributes(ConstraintAttribute.AttributeType paramAttributeType, String... paramVarArgs)
  {
    int i = 0;
    while (i < paramVarArgs.length)
    {
      ConstraintAttribute localConstraintAttribute;
      if (mSavedAttributes.containsKey(paramVarArgs[i]))
      {
        localConstraintAttribute = (ConstraintAttribute)mSavedAttributes.get(paramVarArgs[i]);
        if (localConstraintAttribute.getType() != paramAttributeType)
        {
          paramAttributeType = new StringBuilder();
          paramAttributeType.append("ConstraintAttribute is already a ");
          paramAttributeType.append(localConstraintAttribute.getType().name());
          throw new IllegalArgumentException(paramAttributeType.toString());
        }
      }
      else
      {
        localConstraintAttribute = new ConstraintAttribute(paramVarArgs[i], paramAttributeType);
        mSavedAttributes.put(paramVarArgs[i], localConstraintAttribute);
      }
      i += 1;
    }
  }
  
  private int[] convertReferenceString(View paramView, String paramString)
  {
    paramString = paramString.split(",");
    Context localContext = paramView.getContext();
    int[] arrayOfInt = new int[paramString.length];
    int m = 0;
    int k = 0;
    while (m < paramString.length)
    {
      Object localObject = paramString[m].trim();
      try
      {
        j = R.id.class.getField((String)localObject).getInt(null);
      }
      catch (Exception localException)
      {
        int j;
        int i;
        for (;;) {}
      }
      j = 0;
      i = j;
      if (j == 0) {
        i = localContext.getResources().getIdentifier((String)localObject, "id", localContext.getPackageName());
      }
      j = i;
      if (i == 0)
      {
        j = i;
        if (paramView.isInEditMode())
        {
          j = i;
          if ((paramView.getParent() instanceof ConstraintLayout))
          {
            localObject = ((ConstraintLayout)paramView.getParent()).getDesignInformation(0, localObject);
            j = i;
            if (localObject != null)
            {
              j = i;
              if ((localObject instanceof Integer)) {
                j = ((Integer)localObject).intValue();
              }
            }
          }
        }
      }
      arrayOfInt[k] = j;
      m += 1;
      k += 1;
    }
    if (k != paramString.length) {
      return Arrays.copyOf(arrayOfInt, k);
    }
    return arrayOfInt;
  }
  
  private void createHorizontalChain(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt, float[] paramArrayOfFloat, int paramInt5, int paramInt6, int paramInt7)
  {
    if (paramArrayOfInt.length >= 2)
    {
      if ((paramArrayOfFloat != null) && (paramArrayOfFloat.length != paramArrayOfInt.length)) {
        throw new IllegalArgumentException("must have 2 or more widgets in a chain");
      }
      if (paramArrayOfFloat != null) {
        prepareRecord0horizontalWeight = paramArrayOfFloat[0];
      }
      prepareRecord0horizontalChainStyle = paramInt5;
      connect(paramArrayOfInt[0], paramInt6, paramInt1, paramInt2, -1);
      paramInt1 = 1;
      while (paramInt1 < paramArrayOfInt.length)
      {
        paramInt2 = paramArrayOfInt[paramInt1];
        paramInt2 = paramArrayOfInt[paramInt1];
        paramInt5 = paramInt1 - 1;
        connect(paramInt2, paramInt6, paramArrayOfInt[paramInt5], paramInt7, -1);
        connect(paramArrayOfInt[paramInt5], paramInt7, paramArrayOfInt[paramInt1], paramInt6, -1);
        if (paramArrayOfFloat != null) {
          prepareRecordhorizontalWeight = paramArrayOfFloat[paramInt1];
        }
        paramInt1 += 1;
      }
      connect(paramArrayOfInt[(paramArrayOfInt.length - 1)], paramInt7, paramInt3, paramInt4, -1);
      return;
    }
    throw new IllegalArgumentException("must have 2 or more widgets in a chain");
  }
  
  private Constraint fillFromAttributeList(Context paramContext, AttributeSet paramAttributeSet)
  {
    Constraint localConstraint = new Constraint();
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ConstraintSet);
    populateConstraint(localConstraint, paramContext);
    paramContext.recycle();
    return localConstraint;
  }
  
  private static int lookupID(TypedArray paramTypedArray, int paramInt1, int paramInt2)
  {
    paramInt2 = paramTypedArray.getResourceId(paramInt1, paramInt2);
    if (paramInt2 == -1) {
      return paramTypedArray.getInt(paramInt1, -1);
    }
    return paramInt2;
  }
  
  private void populateConstraint(Constraint paramConstraint, TypedArray paramTypedArray)
  {
    int j = paramTypedArray.getIndexCount();
    int i = 0;
    while (i < j)
    {
      int k = paramTypedArray.getIndex(i);
      int m = mapToConstant.get(k);
      switch (m)
      {
      default: 
        StringBuilder localStringBuilder;
        switch (m)
        {
        default: 
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("Unknown attribute 0x");
          localStringBuilder.append(Integer.toHexString(k));
          localStringBuilder.append("   ");
          localStringBuilder.append(mapToConstant.get(k));
          Log.w("ConstraintSet", localStringBuilder.toString());
          break;
        case 76: 
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("unused attribute 0x");
          localStringBuilder.append(Integer.toHexString(k));
          localStringBuilder.append("   ");
          localStringBuilder.append(mapToConstant.get(k));
          Log.w("ConstraintSet", localStringBuilder.toString());
          break;
        case 75: 
          mPathMotionArc = paramTypedArray.getInt(k, mPathMotionArc);
          break;
        case 74: 
          mBarrierAllowsGoneWidgets = paramTypedArray.getBoolean(k, mBarrierAllowsGoneWidgets);
          break;
        case 73: 
          mReferenceIdString = paramTypedArray.getString(k);
          break;
        case 72: 
          mBarrierDirection = paramTypedArray.getInt(k, mBarrierDirection);
          break;
        case 71: 
          Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
          break;
        case 70: 
          heightPercent = paramTypedArray.getFloat(k, 1.0F);
          break;
        case 69: 
          widthPercent = paramTypedArray.getFloat(k, 1.0F);
          break;
        case 68: 
          mProgress = paramTypedArray.getFloat(k, mProgress);
          break;
        case 67: 
          mPathRotate = paramTypedArray.getFloat(k, mPathRotate);
          break;
        case 66: 
          mDrawPath = paramTypedArray.getInt(k, 0);
          break;
        case 65: 
          if (peekValuetype == 3) {
            mTransitionEasing = paramTypedArray.getString(k);
          } else {
            mTransitionEasing = android.support.constraint.motion.utils.Easing.NAMED_EASING[paramTypedArray.getInteger(k, 0)];
          }
          break;
        case 64: 
          mAnimateRelativeTo = lookupID(paramTypedArray, k, mAnimateRelativeTo);
          break;
        case 63: 
          circleAngle = paramTypedArray.getFloat(k, circleAngle);
          break;
        case 62: 
          circleRadius = paramTypedArray.getDimensionPixelSize(k, circleRadius);
          break;
        case 61: 
          circleConstraint = lookupID(paramTypedArray, k, circleConstraint);
          break;
        case 60: 
          rotation = paramTypedArray.getFloat(k, rotation);
        }
        break;
      case 53: 
        if (Build.VERSION.SDK_INT >= 21) {
          translationZ = paramTypedArray.getDimension(k, translationZ);
        }
        break;
      case 52: 
        translationY = paramTypedArray.getDimension(k, translationY);
        break;
      case 51: 
        translationX = paramTypedArray.getDimension(k, translationX);
        break;
      case 50: 
        transformPivotY = paramTypedArray.getFloat(k, transformPivotY);
        break;
      case 49: 
        transformPivotX = paramTypedArray.getFloat(k, transformPivotX);
        break;
      case 48: 
        scaleY = paramTypedArray.getFloat(k, scaleY);
        break;
      case 47: 
        scaleX = paramTypedArray.getFloat(k, scaleX);
        break;
      case 46: 
        rotationY = paramTypedArray.getFloat(k, rotationY);
        break;
      case 45: 
        rotationX = paramTypedArray.getFloat(k, rotationX);
        break;
      case 44: 
        applyElevation = true;
        elevation = paramTypedArray.getDimension(k, elevation);
        break;
      case 43: 
        alpha = paramTypedArray.getFloat(k, alpha);
        break;
      case 42: 
        verticalChainStyle = paramTypedArray.getInt(k, verticalChainStyle);
        break;
      case 41: 
        horizontalChainStyle = paramTypedArray.getInt(k, horizontalChainStyle);
        break;
      case 40: 
        verticalWeight = paramTypedArray.getFloat(k, verticalWeight);
        break;
      case 39: 
        horizontalWeight = paramTypedArray.getFloat(k, horizontalWeight);
        break;
      case 38: 
        mViewId = paramTypedArray.getResourceId(k, mViewId);
        break;
      case 37: 
        verticalBias = paramTypedArray.getFloat(k, verticalBias);
        break;
      case 36: 
        topToTop = lookupID(paramTypedArray, k, topToTop);
        break;
      case 35: 
        topToBottom = lookupID(paramTypedArray, k, topToBottom);
        break;
      case 34: 
        topMargin = paramTypedArray.getDimensionPixelSize(k, topMargin);
        break;
      case 33: 
        startToStart = lookupID(paramTypedArray, k, startToStart);
        break;
      case 32: 
        startToEnd = lookupID(paramTypedArray, k, startToEnd);
        break;
      case 31: 
        startMargin = paramTypedArray.getDimensionPixelSize(k, startMargin);
        break;
      case 30: 
        rightToRight = lookupID(paramTypedArray, k, rightToRight);
        break;
      case 29: 
        rightToLeft = lookupID(paramTypedArray, k, rightToLeft);
        break;
      case 28: 
        rightMargin = paramTypedArray.getDimensionPixelSize(k, rightMargin);
        break;
      case 27: 
        orientation = paramTypedArray.getInt(k, orientation);
        break;
      case 26: 
        leftToRight = lookupID(paramTypedArray, k, leftToRight);
        break;
      case 25: 
        leftToLeft = lookupID(paramTypedArray, k, leftToLeft);
        break;
      case 24: 
        leftMargin = paramTypedArray.getDimensionPixelSize(k, leftMargin);
        break;
      case 23: 
        mWidth = paramTypedArray.getLayoutDimension(k, mWidth);
        break;
      case 22: 
        visibility = paramTypedArray.getInt(k, visibility);
        visibility = VISIBILITY_FLAGS[visibility];
        break;
      case 21: 
        mHeight = paramTypedArray.getLayoutDimension(k, mHeight);
        break;
      case 20: 
        horizontalBias = paramTypedArray.getFloat(k, horizontalBias);
        break;
      case 19: 
        guidePercent = paramTypedArray.getFloat(k, guidePercent);
        break;
      case 18: 
        guideEnd = paramTypedArray.getDimensionPixelOffset(k, guideEnd);
        break;
      case 17: 
        guideBegin = paramTypedArray.getDimensionPixelOffset(k, guideBegin);
        break;
      case 16: 
        goneTopMargin = paramTypedArray.getDimensionPixelSize(k, goneTopMargin);
        break;
      case 15: 
        goneStartMargin = paramTypedArray.getDimensionPixelSize(k, goneStartMargin);
        break;
      case 14: 
        goneRightMargin = paramTypedArray.getDimensionPixelSize(k, goneRightMargin);
        break;
      case 13: 
        goneLeftMargin = paramTypedArray.getDimensionPixelSize(k, goneLeftMargin);
        break;
      case 12: 
        goneEndMargin = paramTypedArray.getDimensionPixelSize(k, goneEndMargin);
        break;
      case 11: 
        goneBottomMargin = paramTypedArray.getDimensionPixelSize(k, goneBottomMargin);
        break;
      case 10: 
        endToStart = lookupID(paramTypedArray, k, endToStart);
        break;
      case 9: 
        endToEnd = lookupID(paramTypedArray, k, endToEnd);
        break;
      case 8: 
        endMargin = paramTypedArray.getDimensionPixelSize(k, endMargin);
        break;
      case 7: 
        editorAbsoluteY = paramTypedArray.getDimensionPixelOffset(k, editorAbsoluteY);
        break;
      case 6: 
        editorAbsoluteX = paramTypedArray.getDimensionPixelOffset(k, editorAbsoluteX);
        break;
      case 5: 
        dimensionRatio = paramTypedArray.getString(k);
        break;
      case 4: 
        bottomToTop = lookupID(paramTypedArray, k, bottomToTop);
        break;
      case 3: 
        bottomToBottom = lookupID(paramTypedArray, k, bottomToBottom);
        break;
      case 2: 
        bottomMargin = paramTypedArray.getDimensionPixelSize(k, bottomMargin);
        break;
      case 1: 
        baselineToBaseline = lookupID(paramTypedArray, k, baselineToBaseline);
      }
      i += 1;
    }
  }
  
  private Constraint prepareRecord(int paramInt)
  {
    if (!mConstraints.containsKey(Integer.valueOf(paramInt))) {
      mConstraints.put(Integer.valueOf(paramInt), new Constraint());
    }
    return (Constraint)mConstraints.get(Integer.valueOf(paramInt));
  }
  
  private String sideToString(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "undefined";
    case 7: 
      return "end";
    case 6: 
      return "start";
    case 5: 
      return "baseline";
    case 4: 
      return "bottom";
    case 3: 
      return "top";
    case 2: 
      return "right";
    }
    return "left";
  }
  
  private static String[] splitString(String paramString)
  {
    paramString = paramString.toCharArray();
    ArrayList localArrayList = new ArrayList();
    int j = 0;
    int k = 0;
    int n;
    for (int i = 0; j < paramString.length; i = n)
    {
      int m;
      if ((paramString[j] == ',') && (i == 0))
      {
        localArrayList.add(new String(paramString, k, j - k));
        m = j + 1;
        n = i;
      }
      else
      {
        m = k;
        n = i;
        if (paramString[j] == '"')
        {
          n = i ^ 0x1;
          m = k;
        }
      }
      j += 1;
      k = m;
    }
    localArrayList.add(new String(paramString, k, paramString.length - k));
    return (String[])localArrayList.toArray(new String[localArrayList.size()]);
  }
  
  public void addColorAttributes(String... paramVarArgs)
  {
    addAttributes(ConstraintAttribute.AttributeType.COLOR_TYPE, paramVarArgs);
  }
  
  public void addFloatAttributes(String... paramVarArgs)
  {
    addAttributes(ConstraintAttribute.AttributeType.FLOAT_TYPE, paramVarArgs);
  }
  
  public void addIntAttributes(String... paramVarArgs)
  {
    addAttributes(ConstraintAttribute.AttributeType.INT_TYPE, paramVarArgs);
  }
  
  public void addStringAttributes(String... paramVarArgs)
  {
    addAttributes(ConstraintAttribute.AttributeType.STRING_TYPE, paramVarArgs);
  }
  
  public void addToHorizontalChain(int paramInt1, int paramInt2, int paramInt3)
  {
    int i;
    if (paramInt2 == 0) {
      i = 1;
    } else {
      i = 2;
    }
    connect(paramInt1, 1, paramInt2, i, 0);
    if (paramInt3 == 0) {
      i = 2;
    } else {
      i = 1;
    }
    connect(paramInt1, 2, paramInt3, i, 0);
    if (paramInt2 != 0) {
      connect(paramInt2, 2, paramInt1, 1, 0);
    }
    if (paramInt3 != 0) {
      connect(paramInt3, 1, paramInt1, 2, 0);
    }
  }
  
  public void addToHorizontalChainRTL(int paramInt1, int paramInt2, int paramInt3)
  {
    int i;
    if (paramInt2 == 0) {
      i = 6;
    } else {
      i = 7;
    }
    connect(paramInt1, 6, paramInt2, i, 0);
    if (paramInt3 == 0) {
      i = 7;
    } else {
      i = 6;
    }
    connect(paramInt1, 7, paramInt3, i, 0);
    if (paramInt2 != 0) {
      connect(paramInt2, 7, paramInt1, 6, 0);
    }
    if (paramInt3 != 0) {
      connect(paramInt3, 6, paramInt1, 7, 0);
    }
  }
  
  public void addToVerticalChain(int paramInt1, int paramInt2, int paramInt3)
  {
    int i;
    if (paramInt2 == 0) {
      i = 3;
    } else {
      i = 4;
    }
    connect(paramInt1, 3, paramInt2, i, 0);
    if (paramInt3 == 0) {
      i = 4;
    } else {
      i = 3;
    }
    connect(paramInt1, 4, paramInt3, i, 0);
    if (paramInt2 != 0) {
      connect(paramInt2, 4, paramInt1, 3, 0);
    }
    if (paramInt2 != 0) {
      connect(paramInt3, 3, paramInt1, 4, 0);
    }
  }
  
  public void applyTo(ConstraintLayout paramConstraintLayout)
  {
    applyToInternal(paramConstraintLayout);
    paramConstraintLayout.setConstraintSet(null);
  }
  
  void applyToInternal(ConstraintLayout paramConstraintLayout)
  {
    int j = paramConstraintLayout.getChildCount();
    Object localObject1 = new HashSet(mConstraints.keySet());
    int i = 0;
    Object localObject2;
    Object localObject3;
    Object localObject4;
    while (i < j)
    {
      localObject2 = paramConstraintLayout.getChildAt(i);
      int k = ((View)localObject2).getId();
      if (!mConstraints.containsKey(Integer.valueOf(k)))
      {
        localObject3 = new StringBuilder();
        ((StringBuilder)localObject3).append("id unknown ");
        ((StringBuilder)localObject3).append(Debug.getName((View)localObject2));
        Log.v("ConstraintSet", ((StringBuilder)localObject3).toString());
      }
      else
      {
        if (k == -1) {
          break label530;
        }
        if (mConstraints.containsKey(Integer.valueOf(k)))
        {
          ((HashSet)localObject1).remove(Integer.valueOf(k));
          localObject3 = (Constraint)mConstraints.get(Integer.valueOf(k));
          if ((localObject2 instanceof Barrier)) {
            mHelperType = 1;
          }
          if ((mHelperType != -1) && (mHelperType == 1))
          {
            localObject4 = (Barrier)localObject2;
            ((View)localObject4).setId(k);
            ((Barrier)localObject4).setType(mBarrierDirection);
            ((Barrier)localObject4).setAllowsGoneWidget(mBarrierAllowsGoneWidgets);
            if (mReferenceIds != null)
            {
              ((ConstraintHelper)localObject4).setReferencedIds(mReferenceIds);
            }
            else if (mReferenceIdString != null)
            {
              mReferenceIds = convertReferenceString((View)localObject4, mReferenceIdString);
              ((ConstraintHelper)localObject4).setReferencedIds(mReferenceIds);
            }
          }
          localObject4 = (ConstraintLayout.LayoutParams)((View)localObject2).getLayoutParams();
          ((Constraint)localObject3).applyTo((ConstraintLayout.LayoutParams)localObject4);
          ConstraintAttribute.setAttributes((View)localObject2, mCustomConstraints);
          ((View)localObject2).setLayoutParams((ViewGroup.LayoutParams)localObject4);
          ((View)localObject2).setVisibility(visibility);
          if (Build.VERSION.SDK_INT >= 17)
          {
            ((View)localObject2).setAlpha(alpha);
            ((View)localObject2).setRotation(rotation);
            ((View)localObject2).setRotationX(rotationX);
            ((View)localObject2).setRotationY(rotationY);
            ((View)localObject2).setScaleX(scaleX);
            ((View)localObject2).setScaleY(scaleY);
            if (!Float.isNaN(transformPivotX)) {
              ((View)localObject2).setPivotX(transformPivotX);
            }
            if (!Float.isNaN(transformPivotY)) {
              ((View)localObject2).setPivotY(transformPivotY);
            }
            ((View)localObject2).setTranslationX(translationX);
            ((View)localObject2).setTranslationY(translationY);
            if (Build.VERSION.SDK_INT >= 21)
            {
              ((View)localObject2).setTranslationZ(translationZ);
              if (applyElevation) {
                ((View)localObject2).setElevation(elevation);
              }
            }
          }
        }
        else
        {
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append("WARNING NO CONSTRAINTS for view ");
          ((StringBuilder)localObject2).append(k);
          Log.v("ConstraintSet", ((StringBuilder)localObject2).toString());
        }
      }
      i += 1;
      continue;
      label530:
      throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
    }
    localObject1 = ((HashSet)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject3 = (Integer)((Iterator)localObject1).next();
      localObject2 = (Constraint)mConstraints.get(localObject3);
      if ((mHelperType != -1) && (mHelperType == 1))
      {
        localObject4 = new Barrier(paramConstraintLayout.getContext());
        ((View)localObject4).setId(((Integer)localObject3).intValue());
        if (mReferenceIds != null)
        {
          ((ConstraintHelper)localObject4).setReferencedIds(mReferenceIds);
        }
        else if (mReferenceIdString != null)
        {
          mReferenceIds = convertReferenceString((View)localObject4, mReferenceIdString);
          ((ConstraintHelper)localObject4).setReferencedIds(mReferenceIds);
        }
        ((Barrier)localObject4).setType(mBarrierDirection);
        ConstraintLayout.LayoutParams localLayoutParams = paramConstraintLayout.generateDefaultLayoutParams();
        ((ConstraintHelper)localObject4).validateParams();
        ((Constraint)localObject2).applyTo(localLayoutParams);
        paramConstraintLayout.addView((View)localObject4, localLayoutParams);
      }
      if (mIsGuideline)
      {
        localObject4 = new Guideline(paramConstraintLayout.getContext());
        ((View)localObject4).setId(((Integer)localObject3).intValue());
        localObject3 = paramConstraintLayout.generateDefaultLayoutParams();
        ((Constraint)localObject2).applyTo((ConstraintLayout.LayoutParams)localObject3);
        paramConstraintLayout.addView((View)localObject4, (ViewGroup.LayoutParams)localObject3);
      }
    }
  }
  
  public void center(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, float paramFloat)
  {
    if (paramInt4 >= 0)
    {
      if (paramInt7 >= 0)
      {
        if ((paramFloat > 0.0F) && (paramFloat <= 1.0F))
        {
          if ((paramInt3 != 1) && (paramInt3 != 2))
          {
            if ((paramInt3 != 6) && (paramInt3 != 7))
            {
              connect(paramInt1, 3, paramInt2, paramInt3, paramInt4);
              connect(paramInt1, 4, paramInt5, paramInt6, paramInt7);
              mConstraints.get(Integer.valueOf(paramInt1))).verticalBias = paramFloat;
              return;
            }
            connect(paramInt1, 6, paramInt2, paramInt3, paramInt4);
            connect(paramInt1, 7, paramInt5, paramInt6, paramInt7);
            mConstraints.get(Integer.valueOf(paramInt1))).horizontalBias = paramFloat;
            return;
          }
          connect(paramInt1, 1, paramInt2, paramInt3, paramInt4);
          connect(paramInt1, 2, paramInt5, paramInt6, paramInt7);
          mConstraints.get(Integer.valueOf(paramInt1))).horizontalBias = paramFloat;
          return;
        }
        throw new IllegalArgumentException("bias must be between 0 and 1 inclusive");
      }
      throw new IllegalArgumentException("margin must be > 0");
    }
    throw new IllegalArgumentException("margin must be > 0");
  }
  
  public void centerHorizontally(int paramInt1, int paramInt2)
  {
    if (paramInt2 == 0)
    {
      center(paramInt1, 0, 1, 0, 0, 2, 0, 0.5F);
      return;
    }
    center(paramInt1, paramInt2, 2, 0, paramInt2, 1, 0, 0.5F);
  }
  
  public void centerHorizontally(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, float paramFloat)
  {
    connect(paramInt1, 1, paramInt2, paramInt3, paramInt4);
    connect(paramInt1, 2, paramInt5, paramInt6, paramInt7);
    mConstraints.get(Integer.valueOf(paramInt1))).horizontalBias = paramFloat;
  }
  
  public void centerHorizontallyRtl(int paramInt1, int paramInt2)
  {
    if (paramInt2 == 0)
    {
      center(paramInt1, 0, 6, 0, 0, 7, 0, 0.5F);
      return;
    }
    center(paramInt1, paramInt2, 7, 0, paramInt2, 6, 0, 0.5F);
  }
  
  public void centerHorizontallyRtl(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, float paramFloat)
  {
    connect(paramInt1, 6, paramInt2, paramInt3, paramInt4);
    connect(paramInt1, 7, paramInt5, paramInt6, paramInt7);
    mConstraints.get(Integer.valueOf(paramInt1))).horizontalBias = paramFloat;
  }
  
  public void centerVertically(int paramInt1, int paramInt2)
  {
    if (paramInt2 == 0)
    {
      center(paramInt1, 0, 3, 0, 0, 4, 0, 0.5F);
      return;
    }
    center(paramInt1, paramInt2, 4, 0, paramInt2, 3, 0, 0.5F);
  }
  
  public void centerVertically(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, float paramFloat)
  {
    connect(paramInt1, 3, paramInt2, paramInt3, paramInt4);
    connect(paramInt1, 4, paramInt5, paramInt6, paramInt7);
    mConstraints.get(Integer.valueOf(paramInt1))).verticalBias = paramFloat;
  }
  
  public void clear(int paramInt)
  {
    mConstraints.remove(Integer.valueOf(paramInt));
  }
  
  public void clear(int paramInt1, int paramInt2)
  {
    if (mConstraints.containsKey(Integer.valueOf(paramInt1)))
    {
      Constraint localConstraint = (Constraint)mConstraints.get(Integer.valueOf(paramInt1));
      switch (paramInt2)
      {
      default: 
        throw new IllegalArgumentException("unknown constraint");
      case 7: 
        endToStart = -1;
        endToEnd = -1;
        endMargin = -1;
        goneEndMargin = -1;
        return;
      case 6: 
        startToEnd = -1;
        startToStart = -1;
        startMargin = -1;
        goneStartMargin = -1;
        return;
      case 5: 
        baselineToBaseline = -1;
        return;
      case 4: 
        bottomToTop = -1;
        bottomToBottom = -1;
        bottomMargin = -1;
        goneBottomMargin = -1;
        return;
      case 3: 
        topToBottom = -1;
        topToTop = -1;
        topMargin = -1;
        goneTopMargin = -1;
        return;
      case 2: 
        rightToRight = -1;
        rightToLeft = -1;
        rightMargin = -1;
        goneRightMargin = -1;
        return;
      }
      leftToRight = -1;
      leftToLeft = -1;
      leftMargin = -1;
      goneLeftMargin = -1;
    }
  }
  
  public void clone(Context paramContext, int paramInt)
  {
    clone((ConstraintLayout)LayoutInflater.from(paramContext).inflate(paramInt, null));
  }
  
  public void clone(ConstraintLayout paramConstraintLayout)
  {
    int j = paramConstraintLayout.getChildCount();
    mConstraints.clear();
    int i = 0;
    while (i < j)
    {
      Object localObject = paramConstraintLayout.getChildAt(i);
      ConstraintLayout.LayoutParams localLayoutParams = (ConstraintLayout.LayoutParams)((View)localObject).getLayoutParams();
      int k = ((View)localObject).getId();
      if (k != -1)
      {
        if (!mConstraints.containsKey(Integer.valueOf(k))) {
          mConstraints.put(Integer.valueOf(k), new Constraint());
        }
        Constraint localConstraint = (Constraint)mConstraints.get(Integer.valueOf(k));
        mCustomConstraints = ConstraintAttribute.extractAttributes(mSavedAttributes, (View)localObject);
        localConstraint.fillFrom(k, localLayoutParams);
        visibility = ((View)localObject).getVisibility();
        if (Build.VERSION.SDK_INT >= 17)
        {
          alpha = ((View)localObject).getAlpha();
          rotation = ((View)localObject).getRotation();
          rotationX = ((View)localObject).getRotationX();
          rotationY = ((View)localObject).getRotationY();
          scaleX = ((View)localObject).getScaleX();
          scaleY = ((View)localObject).getScaleY();
          float f1 = ((View)localObject).getPivotX();
          float f2 = ((View)localObject).getPivotY();
          if ((f1 != 0.0D) || (f2 != 0.0D))
          {
            transformPivotX = f1;
            transformPivotY = f2;
          }
          translationX = ((View)localObject).getTranslationX();
          translationY = ((View)localObject).getTranslationY();
          if (Build.VERSION.SDK_INT >= 21)
          {
            translationZ = ((View)localObject).getTranslationZ();
            if (applyElevation) {
              elevation = ((View)localObject).getElevation();
            }
          }
        }
        if ((localObject instanceof Barrier))
        {
          localObject = (Barrier)localObject;
          mBarrierAllowsGoneWidgets = ((Barrier)localObject).allowsGoneWidget();
          mReferenceIds = ((ConstraintHelper)localObject).getReferencedIds();
          mBarrierDirection = ((Barrier)localObject).getType();
        }
        i += 1;
      }
      else
      {
        throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
      }
    }
  }
  
  public void clone(ConstraintSet paramConstraintSet)
  {
    mConstraints.clear();
    Iterator localIterator = mConstraints.keySet().iterator();
    while (localIterator.hasNext())
    {
      Integer localInteger = (Integer)localIterator.next();
      mConstraints.put(localInteger, ((Constraint)mConstraints.get(localInteger)).clone());
    }
  }
  
  public void clone(Constraints paramConstraints)
  {
    int j = paramConstraints.getChildCount();
    mConstraints.clear();
    int i = 0;
    while (i < j)
    {
      View localView = paramConstraints.getChildAt(i);
      Constraints.LayoutParams localLayoutParams = (Constraints.LayoutParams)localView.getLayoutParams();
      int k = localView.getId();
      if (k != -1)
      {
        if (!mConstraints.containsKey(Integer.valueOf(k))) {
          mConstraints.put(Integer.valueOf(k), new Constraint());
        }
        Constraint localConstraint = (Constraint)mConstraints.get(Integer.valueOf(k));
        if ((localView instanceof ConstraintHelper)) {
          localConstraint.fillFromConstraints((ConstraintHelper)localView, k, localLayoutParams);
        }
        localConstraint.fillFromConstraints(k, localLayoutParams);
        i += 1;
      }
      else
      {
        throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
      }
    }
  }
  
  public void connect(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (!mConstraints.containsKey(Integer.valueOf(paramInt1))) {
      mConstraints.put(Integer.valueOf(paramInt1), new Constraint());
    }
    Object localObject = (Constraint)mConstraints.get(Integer.valueOf(paramInt1));
    switch (paramInt2)
    {
    default: 
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(sideToString(paramInt2));
      ((StringBuilder)localObject).append(" to ");
      ((StringBuilder)localObject).append(sideToString(paramInt4));
      ((StringBuilder)localObject).append(" unknown");
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    case 7: 
      if (paramInt4 == 7)
      {
        endToEnd = paramInt3;
        endToStart = -1;
        return;
      }
      if (paramInt4 == 6)
      {
        endToStart = paramInt3;
        endToEnd = -1;
        return;
      }
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("right to ");
      ((StringBuilder)localObject).append(sideToString(paramInt4));
      ((StringBuilder)localObject).append(" undefined");
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    case 6: 
      if (paramInt4 == 6)
      {
        startToStart = paramInt3;
        startToEnd = -1;
        return;
      }
      if (paramInt4 == 7)
      {
        startToEnd = paramInt3;
        startToStart = -1;
        return;
      }
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("right to ");
      ((StringBuilder)localObject).append(sideToString(paramInt4));
      ((StringBuilder)localObject).append(" undefined");
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    case 5: 
      if (paramInt4 == 5)
      {
        baselineToBaseline = paramInt3;
        bottomToBottom = -1;
        bottomToTop = -1;
        topToTop = -1;
        topToBottom = -1;
        return;
      }
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("right to ");
      ((StringBuilder)localObject).append(sideToString(paramInt4));
      ((StringBuilder)localObject).append(" undefined");
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    case 4: 
      if (paramInt4 == 4)
      {
        bottomToBottom = paramInt3;
        bottomToTop = -1;
        baselineToBaseline = -1;
        return;
      }
      if (paramInt4 == 3)
      {
        bottomToTop = paramInt3;
        bottomToBottom = -1;
        baselineToBaseline = -1;
        return;
      }
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("right to ");
      ((StringBuilder)localObject).append(sideToString(paramInt4));
      ((StringBuilder)localObject).append(" undefined");
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    case 3: 
      if (paramInt4 == 3)
      {
        topToTop = paramInt3;
        topToBottom = -1;
        baselineToBaseline = -1;
        return;
      }
      if (paramInt4 == 4)
      {
        topToBottom = paramInt3;
        topToTop = -1;
        baselineToBaseline = -1;
        return;
      }
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("right to ");
      ((StringBuilder)localObject).append(sideToString(paramInt4));
      ((StringBuilder)localObject).append(" undefined");
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    case 2: 
      if (paramInt4 == 1)
      {
        rightToLeft = paramInt3;
        rightToRight = -1;
        return;
      }
      if (paramInt4 == 2)
      {
        rightToRight = paramInt3;
        rightToLeft = -1;
        return;
      }
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("right to ");
      ((StringBuilder)localObject).append(sideToString(paramInt4));
      ((StringBuilder)localObject).append(" undefined");
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    }
    if (paramInt4 == 1)
    {
      leftToLeft = paramInt3;
      leftToRight = -1;
      return;
    }
    if (paramInt4 == 2)
    {
      leftToRight = paramInt3;
      leftToLeft = -1;
      return;
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("left to ");
    ((StringBuilder)localObject).append(sideToString(paramInt4));
    ((StringBuilder)localObject).append(" undefined");
    throw new IllegalArgumentException(((StringBuilder)localObject).toString());
  }
  
  public void connect(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    if (!mConstraints.containsKey(Integer.valueOf(paramInt1))) {
      mConstraints.put(Integer.valueOf(paramInt1), new Constraint());
    }
    Object localObject = (Constraint)mConstraints.get(Integer.valueOf(paramInt1));
    switch (paramInt2)
    {
    default: 
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(sideToString(paramInt2));
      ((StringBuilder)localObject).append(" to ");
      ((StringBuilder)localObject).append(sideToString(paramInt4));
      ((StringBuilder)localObject).append(" unknown");
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    case 7: 
      if (paramInt4 == 7)
      {
        endToEnd = paramInt3;
        endToStart = -1;
      }
      else
      {
        if (paramInt4 != 6) {
          break label231;
        }
        endToStart = paramInt3;
        endToEnd = -1;
      }
      endMargin = paramInt5;
      return;
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("right to ");
      ((StringBuilder)localObject).append(sideToString(paramInt4));
      ((StringBuilder)localObject).append(" undefined");
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    case 6: 
      if (paramInt4 == 6)
      {
        startToStart = paramInt3;
        startToEnd = -1;
      }
      else
      {
        if (paramInt4 != 7) {
          break label332;
        }
        startToEnd = paramInt3;
        startToStart = -1;
      }
      startMargin = paramInt5;
      return;
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("right to ");
      ((StringBuilder)localObject).append(sideToString(paramInt4));
      ((StringBuilder)localObject).append(" undefined");
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    case 5: 
      if (paramInt4 == 5)
      {
        baselineToBaseline = paramInt3;
        bottomToBottom = -1;
        bottomToTop = -1;
        topToTop = -1;
        topToBottom = -1;
        return;
      }
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("right to ");
      ((StringBuilder)localObject).append(sideToString(paramInt4));
      ((StringBuilder)localObject).append(" undefined");
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    case 4: 
      if (paramInt4 == 4)
      {
        bottomToBottom = paramInt3;
        bottomToTop = -1;
        baselineToBaseline = -1;
      }
      else
      {
        if (paramInt4 != 3) {
          break label532;
        }
        bottomToTop = paramInt3;
        bottomToBottom = -1;
        baselineToBaseline = -1;
      }
      bottomMargin = paramInt5;
      return;
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("right to ");
      ((StringBuilder)localObject).append(sideToString(paramInt4));
      ((StringBuilder)localObject).append(" undefined");
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    case 3: 
      if (paramInt4 == 3)
      {
        topToTop = paramInt3;
        topToBottom = -1;
        baselineToBaseline = -1;
      }
      else
      {
        if (paramInt4 != 4) {
          break label643;
        }
        topToBottom = paramInt3;
        topToTop = -1;
        baselineToBaseline = -1;
      }
      topMargin = paramInt5;
      return;
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("right to ");
      ((StringBuilder)localObject).append(sideToString(paramInt4));
      ((StringBuilder)localObject).append(" undefined");
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    case 2: 
      label231:
      label332:
      label532:
      label643:
      if (paramInt4 == 1)
      {
        rightToLeft = paramInt3;
        rightToRight = -1;
      }
      else
      {
        if (paramInt4 != 2) {
          break label742;
        }
        rightToRight = paramInt3;
        rightToLeft = -1;
      }
      rightMargin = paramInt5;
      return;
      label742:
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("right to ");
      ((StringBuilder)localObject).append(sideToString(paramInt4));
      ((StringBuilder)localObject).append(" undefined");
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    }
    if (paramInt4 == 1)
    {
      leftToLeft = paramInt3;
      leftToRight = -1;
    }
    else
    {
      if (paramInt4 != 2) {
        break label841;
      }
      leftToRight = paramInt3;
      leftToLeft = -1;
    }
    leftMargin = paramInt5;
    return;
    label841:
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Left to ");
    ((StringBuilder)localObject).append(sideToString(paramInt4));
    ((StringBuilder)localObject).append(" undefined");
    throw new IllegalArgumentException(((StringBuilder)localObject).toString());
  }
  
  public void constrainCircle(int paramInt1, int paramInt2, int paramInt3, float paramFloat)
  {
    Constraint localConstraint = prepareRecord(paramInt1);
    circleConstraint = paramInt2;
    circleRadius = paramInt3;
    circleAngle = paramFloat;
  }
  
  public void constrainDefaultHeight(int paramInt1, int paramInt2)
  {
    prepareRecordheightDefault = paramInt2;
  }
  
  public void constrainDefaultWidth(int paramInt1, int paramInt2)
  {
    prepareRecordwidthDefault = paramInt2;
  }
  
  public void constrainHeight(int paramInt1, int paramInt2)
  {
    prepareRecordmHeight = paramInt2;
  }
  
  public void constrainMaxHeight(int paramInt1, int paramInt2)
  {
    prepareRecordheightMax = paramInt2;
  }
  
  public void constrainMaxWidth(int paramInt1, int paramInt2)
  {
    prepareRecordwidthMax = paramInt2;
  }
  
  public void constrainMinHeight(int paramInt1, int paramInt2)
  {
    prepareRecordheightMin = paramInt2;
  }
  
  public void constrainMinWidth(int paramInt1, int paramInt2)
  {
    prepareRecordwidthMin = paramInt2;
  }
  
  public void constrainPercentHeight(int paramInt, float paramFloat)
  {
    prepareRecordheightPercent = paramFloat;
  }
  
  public void constrainPercentWidth(int paramInt, float paramFloat)
  {
    prepareRecordwidthPercent = paramFloat;
  }
  
  public void constrainWidth(int paramInt1, int paramInt2)
  {
    prepareRecordmWidth = paramInt2;
  }
  
  public void create(int paramInt1, int paramInt2)
  {
    Constraint localConstraint = prepareRecord(paramInt1);
    mIsGuideline = true;
    orientation = paramInt2;
  }
  
  public void createBarrier(int paramInt1, int paramInt2, int... paramVarArgs)
  {
    Constraint localConstraint = prepareRecord(paramInt1);
    mHelperType = 1;
    mBarrierDirection = paramInt2;
    mIsGuideline = false;
    mReferenceIds = paramVarArgs;
  }
  
  public void createHorizontalChain(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt, float[] paramArrayOfFloat, int paramInt5)
  {
    createHorizontalChain(paramInt1, paramInt2, paramInt3, paramInt4, paramArrayOfInt, paramArrayOfFloat, paramInt5, 1, 2);
  }
  
  public void createHorizontalChainRtl(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt, float[] paramArrayOfFloat, int paramInt5)
  {
    createHorizontalChain(paramInt1, paramInt2, paramInt3, paramInt4, paramArrayOfInt, paramArrayOfFloat, paramInt5, 6, 7);
  }
  
  public void createVerticalChain(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt, float[] paramArrayOfFloat, int paramInt5)
  {
    if (paramArrayOfInt.length >= 2)
    {
      if ((paramArrayOfFloat != null) && (paramArrayOfFloat.length != paramArrayOfInt.length)) {
        throw new IllegalArgumentException("must have 2 or more widgets in a chain");
      }
      if (paramArrayOfFloat != null) {
        prepareRecord0verticalWeight = paramArrayOfFloat[0];
      }
      prepareRecord0verticalChainStyle = paramInt5;
      connect(paramArrayOfInt[0], 3, paramInt1, paramInt2, 0);
      paramInt1 = 1;
      while (paramInt1 < paramArrayOfInt.length)
      {
        paramInt2 = paramArrayOfInt[paramInt1];
        paramInt2 = paramArrayOfInt[paramInt1];
        paramInt5 = paramInt1 - 1;
        connect(paramInt2, 3, paramArrayOfInt[paramInt5], 4, 0);
        connect(paramArrayOfInt[paramInt5], 4, paramArrayOfInt[paramInt1], 3, 0);
        if (paramArrayOfFloat != null) {
          prepareRecordverticalWeight = paramArrayOfFloat[paramInt1];
        }
        paramInt1 += 1;
      }
      connect(paramArrayOfInt[(paramArrayOfInt.length - 1)], 4, paramInt3, paramInt4, 0);
      return;
    }
    throw new IllegalArgumentException("must have 2 or more widgets in a chain");
  }
  
  public boolean getApplyElevation(int paramInt)
  {
    return prepareRecordapplyElevation;
  }
  
  public HashMap getCustomAttributeSet()
  {
    return mSavedAttributes;
  }
  
  public Constraint getParameters(int paramInt)
  {
    return prepareRecord(paramInt);
  }
  
  public void load(Context paramContext, int paramInt)
  {
    XmlResourceParser localXmlResourceParser = paramContext.getResources().getXml(paramInt);
    try
    {
      for (paramInt = localXmlResourceParser.getEventType(); paramInt != 1; paramInt = localXmlResourceParser.next())
      {
        if (paramInt != 0) {}
        switch (paramInt)
        {
        default: 
          break;
        case 2: 
          Object localObject = localXmlResourceParser.getName();
          Constraint localConstraint = fillFromAttributeList(paramContext, Xml.asAttributeSet(localXmlResourceParser));
          boolean bool = ((String)localObject).equalsIgnoreCase("Guideline");
          if (bool) {
            mIsGuideline = true;
          }
          localObject = mConstraints;
          paramInt = mViewId;
          ((HashMap)localObject).put(Integer.valueOf(paramInt), localConstraint);
          continue;
          localXmlResourceParser.getName();
        }
      }
      return;
    }
    catch (IOException paramContext)
    {
      paramContext.printStackTrace();
      return;
    }
    catch (XmlPullParserException paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public void load(Context paramContext, XmlPullParser paramXmlPullParser)
  {
    try
    {
      int i = paramXmlPullParser.getEventType();
      Object localObject1;
      for (Object localObject2 = null; i != 1; localObject2 = localObject1)
      {
        if (i != 0)
        {
          boolean bool;
          switch (i)
          {
          default: 
            localObject1 = localObject2;
            break;
          case 3: 
            localObject1 = paramXmlPullParser.getName();
            bool = "ConstraintSet".equals(localObject1);
            if (bool) {
              return;
            }
            bool = ((String)localObject1).equalsIgnoreCase("Constraint");
            localObject1 = localObject2;
            if (!bool) {
              break;
            }
            localObject1 = mConstraints;
            i = mViewId;
            ((HashMap)localObject1).put(Integer.valueOf(i), localObject2);
            localObject1 = null;
            break;
          case 2: 
            localObject1 = paramXmlPullParser.getName();
            bool = ((String)localObject1).equalsIgnoreCase("Constraint");
            if (bool) {
              localObject1 = fillFromAttributeList(paramContext, Xml.asAttributeSet(paramXmlPullParser));
            }
            for (;;)
            {
              break label257;
              bool = ((String)localObject1).equalsIgnoreCase("Guideline");
              if (!bool) {
                break;
              }
              localObject2 = fillFromAttributeList(paramContext, Xml.asAttributeSet(paramXmlPullParser));
              localObject1 = localObject2;
              mIsGuideline = true;
            }
            bool = ((String)localObject1).equalsIgnoreCase("CustomAttribute");
            localObject1 = localObject2;
            if (!bool) {
              break;
            }
            localObject1 = mCustomConstraints;
            ConstraintAttribute.parse(paramContext, paramXmlPullParser, (HashMap)localObject1);
            localObject1 = localObject2;
            break;
          }
        }
        else
        {
          paramXmlPullParser.getName();
          localObject1 = localObject2;
        }
        label257:
        i = paramXmlPullParser.next();
      }
      return;
    }
    catch (IOException paramContext)
    {
      paramContext.printStackTrace();
      return;
    }
    catch (XmlPullParserException paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public void parseColorAttributes(Constraint paramConstraint, String paramString)
  {
    paramString = paramString.split(",");
    int i = 0;
    while (i < paramString.length)
    {
      Object localObject = paramString[i].split("=");
      if (localObject.length != 2)
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append(" Unable to parse ");
        ((StringBuilder)localObject).append(paramString[i]);
        Log.w("ConstraintSet", ((StringBuilder)localObject).toString());
      }
      else
      {
        paramConstraint.setColorValue(localObject[0], Color.parseColor(localObject[1]));
      }
      i += 1;
    }
  }
  
  public void parseFloatAttributes(Constraint paramConstraint, String paramString)
  {
    paramString = paramString.split(",");
    int i = 0;
    while (i < paramString.length)
    {
      Object localObject = paramString[i].split("=");
      if (localObject.length != 2)
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append(" Unable to parse ");
        ((StringBuilder)localObject).append(paramString[i]);
        Log.w("ConstraintSet", ((StringBuilder)localObject).toString());
      }
      else
      {
        paramConstraint.setFloatValue(localObject[0], Float.parseFloat(localObject[1]));
      }
      i += 1;
    }
  }
  
  public void parseIntAttributes(Constraint paramConstraint, String paramString)
  {
    paramString = paramString.split(",");
    int i = 0;
    while (i < paramString.length)
    {
      Object localObject = paramString[i].split("=");
      if (localObject.length != 2)
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append(" Unable to parse ");
        ((StringBuilder)localObject).append(paramString[i]);
        Log.w("ConstraintSet", ((StringBuilder)localObject).toString());
      }
      else
      {
        paramConstraint.setFloatValue(localObject[0], Integer.decode(localObject[1]).intValue());
      }
      i += 1;
    }
  }
  
  public void parseStringAttributes(Constraint paramConstraint, String paramString)
  {
    paramString = splitString(paramString);
    int i = 0;
    while (i < paramString.length)
    {
      String[] arrayOfString = paramString[i].split("=");
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(" Unable to parse ");
      localStringBuilder.append(paramString[i]);
      Log.w("ConstraintSet", localStringBuilder.toString());
      paramConstraint.setStringValue(arrayOfString[0], arrayOfString[1]);
      i += 1;
    }
  }
  
  public void readFallback(ConstraintLayout paramConstraintLayout)
  {
    int j = paramConstraintLayout.getChildCount();
    int i = 0;
    while (i < j)
    {
      View localView = paramConstraintLayout.getChildAt(i);
      if (!mConstraints.containsKey(Integer.valueOf(localView.getId())))
      {
        ConstraintLayout.LayoutParams localLayoutParams = (ConstraintLayout.LayoutParams)localView.getLayoutParams();
        int k = localView.getId();
        if (k == -1) {
          break label324;
        }
        if (!mConstraints.containsKey(Integer.valueOf(k))) {
          mConstraints.put(Integer.valueOf(k), new Constraint());
        }
        Constraint localConstraint = (Constraint)mConstraints.get(Integer.valueOf(k));
        mCustomConstraints = ConstraintAttribute.extractAttributes(mSavedAttributes, localView);
        localConstraint.fillFrom(k, localLayoutParams);
        visibility = localView.getVisibility();
        if (Build.VERSION.SDK_INT >= 17)
        {
          alpha = localView.getAlpha();
          rotation = localView.getRotation();
          rotationX = localView.getRotationX();
          rotationY = localView.getRotationY();
          scaleX = localView.getScaleX();
          scaleY = localView.getScaleY();
          float f1 = localView.getPivotX();
          float f2 = localView.getPivotY();
          if ((f1 != 0.0D) || (f2 != 0.0D))
          {
            transformPivotX = f1;
            transformPivotY = f2;
          }
          translationX = localView.getTranslationX();
          translationY = localView.getTranslationY();
          if (Build.VERSION.SDK_INT >= 21)
          {
            translationZ = localView.getTranslationZ();
            if (applyElevation) {
              elevation = localView.getElevation();
            }
          }
        }
      }
      i += 1;
      continue;
      label324:
      throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
    }
  }
  
  public void removeAttribute(String paramString)
  {
    mSavedAttributes.remove(paramString);
  }
  
  public void removeFromHorizontalChain(int paramInt)
  {
    if (mConstraints.containsKey(Integer.valueOf(paramInt)))
    {
      Constraint localConstraint = (Constraint)mConstraints.get(Integer.valueOf(paramInt));
      int i = leftToRight;
      int j = rightToLeft;
      if ((i == -1) && (j == -1))
      {
        j = startToEnd;
        int k = endToStart;
        if ((j != -1) || (k != -1)) {
          if ((j != -1) && (k != -1))
          {
            connect(j, 7, k, 6, 0);
            connect(k, 6, i, 7, 0);
          }
          else if ((i != -1) || (k != -1))
          {
            if (rightToRight != -1) {
              connect(i, 7, rightToRight, 7, 0);
            } else if (leftToLeft != -1) {
              connect(k, 6, leftToLeft, 6, 0);
            }
          }
        }
        clear(paramInt, 6);
        clear(paramInt, 7);
        return;
      }
      if ((i != -1) && (j != -1))
      {
        connect(i, 2, j, 1, 0);
        connect(j, 1, i, 2, 0);
      }
      else if ((i != -1) || (j != -1))
      {
        if (rightToRight != -1) {
          connect(i, 2, rightToRight, 2, 0);
        } else if (leftToLeft != -1) {
          connect(j, 1, leftToLeft, 1, 0);
        }
      }
      clear(paramInt, 1);
      clear(paramInt, 2);
    }
  }
  
  public void removeFromVerticalChain(int paramInt)
  {
    if (mConstraints.containsKey(Integer.valueOf(paramInt)))
    {
      Constraint localConstraint = (Constraint)mConstraints.get(Integer.valueOf(paramInt));
      int i = topToBottom;
      int j = bottomToTop;
      if ((i != -1) || (j != -1)) {
        if ((i != -1) && (j != -1))
        {
          connect(i, 4, j, 3, 0);
          connect(j, 3, i, 4, 0);
        }
        else if ((i != -1) || (j != -1))
        {
          if (bottomToBottom != -1) {
            connect(i, 4, bottomToBottom, 4, 0);
          } else if (topToTop != -1) {
            connect(j, 3, topToTop, 3, 0);
          }
        }
      }
    }
    clear(paramInt, 3);
    clear(paramInt, 4);
  }
  
  public void setAlpha(int paramInt, float paramFloat)
  {
    prepareRecordalpha = paramFloat;
  }
  
  public void setApplyElevation(int paramInt, boolean paramBoolean)
  {
    prepareRecordapplyElevation = paramBoolean;
  }
  
  public void setBarrierType(int paramInt1, int paramInt2)
  {
    prepareRecordmHelperType = paramInt2;
  }
  
  public void setColorValue(int paramInt1, String paramString, int paramInt2)
  {
    prepareRecord(paramInt1).setColorValue(paramString, paramInt2);
  }
  
  public void setDimensionRatio(int paramInt, String paramString)
  {
    prepareRecorddimensionRatio = paramString;
  }
  
  public void setEditorAbsoluteX(int paramInt1, int paramInt2)
  {
    prepareRecordeditorAbsoluteX = paramInt2;
  }
  
  public void setEditorAbsoluteY(int paramInt1, int paramInt2)
  {
    prepareRecordeditorAbsoluteY = paramInt2;
  }
  
  public void setElevation(int paramInt, float paramFloat)
  {
    prepareRecordelevation = paramFloat;
    prepareRecordapplyElevation = true;
  }
  
  public void setFloatValue(int paramInt, String paramString, float paramFloat)
  {
    prepareRecord(paramInt).setFloatValue(paramString, paramFloat);
  }
  
  public void setGoneMargin(int paramInt1, int paramInt2, int paramInt3)
  {
    Constraint localConstraint = prepareRecord(paramInt1);
    switch (paramInt2)
    {
    default: 
      throw new IllegalArgumentException("unknown constraint");
    case 7: 
      goneEndMargin = paramInt3;
      return;
    case 6: 
      goneStartMargin = paramInt3;
      return;
    case 5: 
      throw new IllegalArgumentException("baseline does not support margins");
    case 4: 
      goneBottomMargin = paramInt3;
      return;
    case 3: 
      goneTopMargin = paramInt3;
      return;
    case 2: 
      goneRightMargin = paramInt3;
      return;
    }
    goneLeftMargin = paramInt3;
  }
  
  public void setGuidelineBegin(int paramInt1, int paramInt2)
  {
    prepareRecordguideBegin = paramInt2;
    prepareRecordguideEnd = -1;
    prepareRecordguidePercent = -1.0F;
  }
  
  public void setGuidelineEnd(int paramInt1, int paramInt2)
  {
    prepareRecordguideEnd = paramInt2;
    prepareRecordguideBegin = -1;
    prepareRecordguidePercent = -1.0F;
  }
  
  public void setGuidelinePercent(int paramInt, float paramFloat)
  {
    prepareRecordguidePercent = paramFloat;
    prepareRecordguideEnd = -1;
    prepareRecordguideBegin = -1;
  }
  
  public void setHorizontalBias(int paramInt, float paramFloat)
  {
    prepareRecordhorizontalBias = paramFloat;
  }
  
  public void setHorizontalChainStyle(int paramInt1, int paramInt2)
  {
    prepareRecordhorizontalChainStyle = paramInt2;
  }
  
  public void setHorizontalWeight(int paramInt, float paramFloat)
  {
    prepareRecordhorizontalWeight = paramFloat;
  }
  
  public void setIntValue(int paramInt1, String paramString, int paramInt2)
  {
    prepareRecord(paramInt1).setIntValue(paramString, paramInt2);
  }
  
  public void setMargin(int paramInt1, int paramInt2, int paramInt3)
  {
    Constraint localConstraint = prepareRecord(paramInt1);
    switch (paramInt2)
    {
    default: 
      throw new IllegalArgumentException("unknown constraint");
    case 7: 
      endMargin = paramInt3;
      return;
    case 6: 
      startMargin = paramInt3;
      return;
    case 5: 
      throw new IllegalArgumentException("baseline does not support margins");
    case 4: 
      bottomMargin = paramInt3;
      return;
    case 3: 
      topMargin = paramInt3;
      return;
    case 2: 
      rightMargin = paramInt3;
      return;
    }
    leftMargin = paramInt3;
  }
  
  public void setRotation(int paramInt, float paramFloat)
  {
    prepareRecordrotation = paramFloat;
  }
  
  public void setRotationX(int paramInt, float paramFloat)
  {
    prepareRecordrotationX = paramFloat;
  }
  
  public void setRotationY(int paramInt, float paramFloat)
  {
    prepareRecordrotationY = paramFloat;
  }
  
  public void setScaleX(int paramInt, float paramFloat)
  {
    prepareRecordscaleX = paramFloat;
  }
  
  public void setScaleY(int paramInt, float paramFloat)
  {
    prepareRecordscaleY = paramFloat;
  }
  
  public void setStringValue(int paramInt, String paramString1, String paramString2)
  {
    prepareRecord(paramInt).setStringValue(paramString1, paramString2);
  }
  
  public void setTransformPivot(int paramInt, float paramFloat1, float paramFloat2)
  {
    Constraint localConstraint = prepareRecord(paramInt);
    transformPivotY = paramFloat2;
    transformPivotX = paramFloat1;
  }
  
  public void setTransformPivotX(int paramInt, float paramFloat)
  {
    prepareRecordtransformPivotX = paramFloat;
  }
  
  public void setTransformPivotY(int paramInt, float paramFloat)
  {
    prepareRecordtransformPivotY = paramFloat;
  }
  
  public void setTranslation(int paramInt, float paramFloat1, float paramFloat2)
  {
    Constraint localConstraint = prepareRecord(paramInt);
    translationX = paramFloat1;
    translationY = paramFloat2;
  }
  
  public void setTranslationX(int paramInt, float paramFloat)
  {
    prepareRecordtranslationX = paramFloat;
  }
  
  public void setTranslationY(int paramInt, float paramFloat)
  {
    prepareRecordtranslationY = paramFloat;
  }
  
  public void setTranslationZ(int paramInt, float paramFloat)
  {
    prepareRecordtranslationZ = paramFloat;
  }
  
  public void setVerticalBias(int paramInt, float paramFloat)
  {
    prepareRecordverticalBias = paramFloat;
  }
  
  public void setVerticalChainStyle(int paramInt1, int paramInt2)
  {
    prepareRecordverticalChainStyle = paramInt2;
  }
  
  public void setVerticalWeight(int paramInt, float paramFloat)
  {
    prepareRecordverticalWeight = paramFloat;
  }
  
  public void setVisibility(int paramInt1, int paramInt2)
  {
    prepareRecordvisibility = paramInt2;
  }
  
  public static class Constraint
  {
    static final int UNSET = -1;
    public float alpha = 1.0F;
    public boolean applyElevation = false;
    public int baselineToBaseline = -1;
    public int bottomMargin = -1;
    public int bottomToBottom = -1;
    public int bottomToTop = -1;
    public float circleAngle = 0.0F;
    public int circleConstraint = -1;
    public int circleRadius = 0;
    public boolean constrainedHeight = false;
    public boolean constrainedWidth = false;
    public String dimensionRatio = null;
    public int editorAbsoluteX = -1;
    public int editorAbsoluteY = -1;
    public float elevation = 0.0F;
    public int endMargin = -1;
    public int endToEnd = -1;
    public int endToStart = -1;
    public int goneBottomMargin = -1;
    public int goneEndMargin = -1;
    public int goneLeftMargin = -1;
    public int goneRightMargin = -1;
    public int goneStartMargin = -1;
    public int goneTopMargin = -1;
    public int guideBegin = -1;
    public int guideEnd = -1;
    public float guidePercent = -1.0F;
    public int heightDefault = 0;
    public int heightMax = -1;
    public int heightMin = -1;
    public float heightPercent = 1.0F;
    public float horizontalBias = 0.5F;
    public int horizontalChainStyle = 0;
    public float horizontalWeight = 0.0F;
    public int leftMargin = -1;
    public int leftToLeft = -1;
    public int leftToRight = -1;
    public int mAnimateRelativeTo = -1;
    public boolean mBarrierAllowsGoneWidgets = true;
    public int mBarrierDirection = -1;
    public HashMap<String, ConstraintAttribute> mCustomConstraints = new HashMap();
    public int mDrawPath = 0;
    public int mHeight;
    public int mHelperType = -1;
    boolean mIsGuideline = false;
    public int mPathMotionArc = -1;
    public float mPathRotate = NaN.0F;
    public float mProgress = NaN.0F;
    public String mReferenceIdString;
    public int[] mReferenceIds;
    public String mTransitionEasing = null;
    int mViewId;
    public int mWidth;
    public int orientation = -1;
    public int rightMargin = -1;
    public int rightToLeft = -1;
    public int rightToRight = -1;
    public float rotation = 0.0F;
    public float rotationX = 0.0F;
    public float rotationY = 0.0F;
    public float scaleX = 1.0F;
    public float scaleY = 1.0F;
    public int startMargin = -1;
    public int startToEnd = -1;
    public int startToStart = -1;
    public int topMargin = -1;
    public int topToBottom = -1;
    public int topToTop = -1;
    public float transformPivotX = NaN.0F;
    public float transformPivotY = NaN.0F;
    public float translationX = 0.0F;
    public float translationY = 0.0F;
    public float translationZ = 0.0F;
    public float verticalBias = 0.5F;
    public int verticalChainStyle = 0;
    public float verticalWeight = 0.0F;
    public int visibility = 0;
    public int widthDefault = 0;
    public int widthMax = -1;
    public int widthMin = -1;
    public float widthPercent = 1.0F;
    
    public Constraint() {}
    
    private ConstraintAttribute add(String paramString, ConstraintAttribute.AttributeType paramAttributeType)
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
    
    private void fillFrom(int paramInt, ConstraintLayout.LayoutParams paramLayoutParams)
    {
      mViewId = paramInt;
      leftToLeft = leftToLeft;
      leftToRight = leftToRight;
      rightToLeft = rightToLeft;
      rightToRight = rightToRight;
      topToTop = topToTop;
      topToBottom = topToBottom;
      bottomToTop = bottomToTop;
      bottomToBottom = bottomToBottom;
      baselineToBaseline = baselineToBaseline;
      startToEnd = startToEnd;
      startToStart = startToStart;
      endToStart = endToStart;
      endToEnd = endToEnd;
      horizontalBias = horizontalBias;
      verticalBias = verticalBias;
      dimensionRatio = dimensionRatio;
      circleConstraint = circleConstraint;
      circleRadius = circleRadius;
      circleAngle = circleAngle;
      editorAbsoluteX = editorAbsoluteX;
      editorAbsoluteY = editorAbsoluteY;
      orientation = orientation;
      guidePercent = guidePercent;
      guideBegin = guideBegin;
      guideEnd = guideEnd;
      mWidth = width;
      mHeight = height;
      leftMargin = leftMargin;
      rightMargin = rightMargin;
      topMargin = topMargin;
      bottomMargin = bottomMargin;
      verticalWeight = verticalWeight;
      horizontalWeight = horizontalWeight;
      verticalChainStyle = verticalChainStyle;
      horizontalChainStyle = horizontalChainStyle;
      constrainedWidth = constrainedWidth;
      constrainedHeight = constrainedHeight;
      widthDefault = matchConstraintDefaultWidth;
      heightDefault = matchConstraintDefaultHeight;
      constrainedWidth = constrainedWidth;
      widthMax = matchConstraintMaxWidth;
      heightMax = matchConstraintMaxHeight;
      widthMin = matchConstraintMinWidth;
      heightMin = matchConstraintMinHeight;
      widthPercent = matchConstraintPercentWidth;
      heightPercent = matchConstraintPercentHeight;
      if (Build.VERSION.SDK_INT >= 17)
      {
        endMargin = paramLayoutParams.getMarginEnd();
        startMargin = paramLayoutParams.getMarginStart();
      }
    }
    
    private void fillFromConstraints(int paramInt, Constraints.LayoutParams paramLayoutParams)
    {
      fillFrom(paramInt, paramLayoutParams);
      alpha = alpha;
      rotation = rotation;
      rotationX = rotationX;
      rotationY = rotationY;
      scaleX = scaleX;
      scaleY = scaleY;
      transformPivotX = transformPivotX;
      transformPivotY = transformPivotY;
      translationX = translationX;
      translationY = translationY;
      translationZ = translationZ;
      elevation = elevation;
      applyElevation = applyElevation;
    }
    
    private void fillFromConstraints(ConstraintHelper paramConstraintHelper, int paramInt, Constraints.LayoutParams paramLayoutParams)
    {
      fillFromConstraints(paramInt, paramLayoutParams);
      if ((paramConstraintHelper instanceof Barrier))
      {
        mHelperType = 1;
        paramConstraintHelper = (Barrier)paramConstraintHelper;
        mBarrierDirection = paramConstraintHelper.getType();
        mReferenceIds = paramConstraintHelper.getReferencedIds();
      }
    }
    
    private void setColorValue(String paramString, int paramInt)
    {
      add(paramString, ConstraintAttribute.AttributeType.COLOR_TYPE).setColorValue(paramInt);
    }
    
    private void setFloatValue(String paramString, float paramFloat)
    {
      add(paramString, ConstraintAttribute.AttributeType.FLOAT_TYPE).setFloatValue(paramFloat);
    }
    
    private void setIntValue(String paramString, int paramInt)
    {
      add(paramString, ConstraintAttribute.AttributeType.INT_TYPE).setIntValue(paramInt);
    }
    
    private void setStringValue(String paramString1, String paramString2)
    {
      add(paramString1, ConstraintAttribute.AttributeType.STRING_TYPE).setStringValue(paramString2);
    }
    
    public void applyTo(ConstraintLayout.LayoutParams paramLayoutParams)
    {
      leftToLeft = leftToLeft;
      leftToRight = leftToRight;
      rightToLeft = rightToLeft;
      rightToRight = rightToRight;
      topToTop = topToTop;
      topToBottom = topToBottom;
      bottomToTop = bottomToTop;
      bottomToBottom = bottomToBottom;
      baselineToBaseline = baselineToBaseline;
      startToEnd = startToEnd;
      startToStart = startToStart;
      endToStart = endToStart;
      endToEnd = endToEnd;
      leftMargin = leftMargin;
      rightMargin = rightMargin;
      topMargin = topMargin;
      bottomMargin = bottomMargin;
      goneStartMargin = goneStartMargin;
      goneEndMargin = goneEndMargin;
      horizontalBias = horizontalBias;
      verticalBias = verticalBias;
      circleConstraint = circleConstraint;
      circleRadius = circleRadius;
      circleAngle = circleAngle;
      dimensionRatio = dimensionRatio;
      editorAbsoluteX = editorAbsoluteX;
      editorAbsoluteY = editorAbsoluteY;
      verticalWeight = verticalWeight;
      horizontalWeight = horizontalWeight;
      verticalChainStyle = verticalChainStyle;
      horizontalChainStyle = horizontalChainStyle;
      constrainedWidth = constrainedWidth;
      constrainedHeight = constrainedHeight;
      matchConstraintDefaultWidth = widthDefault;
      matchConstraintDefaultHeight = heightDefault;
      matchConstraintMaxWidth = widthMax;
      matchConstraintMaxHeight = heightMax;
      matchConstraintMinWidth = widthMin;
      matchConstraintMinHeight = heightMin;
      matchConstraintPercentWidth = widthPercent;
      matchConstraintPercentHeight = heightPercent;
      orientation = orientation;
      guidePercent = guidePercent;
      guideBegin = guideBegin;
      guideEnd = guideEnd;
      width = mWidth;
      height = mHeight;
      if (Build.VERSION.SDK_INT >= 17)
      {
        paramLayoutParams.setMarginStart(startMargin);
        paramLayoutParams.setMarginEnd(endMargin);
      }
      paramLayoutParams.validate();
    }
    
    public Constraint clone()
    {
      Constraint localConstraint = new Constraint();
      mIsGuideline = mIsGuideline;
      mWidth = mWidth;
      mHeight = mHeight;
      guideBegin = guideBegin;
      guideEnd = guideEnd;
      guidePercent = guidePercent;
      leftToLeft = leftToLeft;
      leftToRight = leftToRight;
      rightToLeft = rightToLeft;
      rightToRight = rightToRight;
      topToTop = topToTop;
      topToBottom = topToBottom;
      bottomToTop = bottomToTop;
      bottomToBottom = bottomToBottom;
      baselineToBaseline = baselineToBaseline;
      startToEnd = startToEnd;
      startToStart = startToStart;
      endToStart = endToStart;
      endToEnd = endToEnd;
      horizontalBias = horizontalBias;
      verticalBias = verticalBias;
      dimensionRatio = dimensionRatio;
      editorAbsoluteX = editorAbsoluteX;
      editorAbsoluteY = editorAbsoluteY;
      horizontalBias = horizontalBias;
      horizontalBias = horizontalBias;
      horizontalBias = horizontalBias;
      horizontalBias = horizontalBias;
      horizontalBias = horizontalBias;
      orientation = orientation;
      leftMargin = leftMargin;
      rightMargin = rightMargin;
      topMargin = topMargin;
      bottomMargin = bottomMargin;
      endMargin = endMargin;
      startMargin = startMargin;
      visibility = visibility;
      goneLeftMargin = goneLeftMargin;
      goneTopMargin = goneTopMargin;
      goneRightMargin = goneRightMargin;
      goneBottomMargin = goneBottomMargin;
      goneEndMargin = goneEndMargin;
      goneStartMargin = goneStartMargin;
      verticalWeight = verticalWeight;
      horizontalWeight = horizontalWeight;
      horizontalChainStyle = horizontalChainStyle;
      verticalChainStyle = verticalChainStyle;
      alpha = alpha;
      applyElevation = applyElevation;
      elevation = elevation;
      rotation = rotation;
      rotationX = rotationX;
      rotationY = rotationY;
      scaleX = scaleX;
      scaleY = scaleY;
      transformPivotX = transformPivotX;
      transformPivotY = transformPivotY;
      translationX = translationX;
      translationY = translationY;
      translationZ = translationZ;
      constrainedWidth = constrainedWidth;
      constrainedHeight = constrainedHeight;
      widthDefault = widthDefault;
      heightDefault = heightDefault;
      widthMax = widthMax;
      heightMax = heightMax;
      widthMin = widthMin;
      heightMin = heightMin;
      widthPercent = widthPercent;
      heightPercent = heightPercent;
      mBarrierDirection = mBarrierDirection;
      mHelperType = mHelperType;
      if (mReferenceIds != null) {
        mReferenceIds = Arrays.copyOf(mReferenceIds, mReferenceIds.length);
      }
      circleConstraint = circleConstraint;
      circleRadius = circleRadius;
      circleAngle = circleAngle;
      mAnimateRelativeTo = mAnimateRelativeTo;
      mTransitionEasing = mTransitionEasing;
      mPathMotionArc = mPathMotionArc;
      mDrawPath = mDrawPath;
      mBarrierAllowsGoneWidgets = mBarrierAllowsGoneWidgets;
      return localConstraint;
    }
  }
}
