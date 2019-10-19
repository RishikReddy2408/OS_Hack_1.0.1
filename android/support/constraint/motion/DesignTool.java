package android.support.constraint.motion;

import android.support.constraint.ConstraintSet;
import android.support.constraint.R.id;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import java.util.HashMap;

public class DesignTool
{
  private static final boolean DEBUG = false;
  private static final String PAGE_KEY = "MotionLayout";
  static final HashMap<Pair<Integer, Integer>, String> allAttributes = new HashMap();
  static final HashMap<String, String> allMargins = new HashMap();
  private final MotionLayout mMotionLayout;
  
  static
  {
    allAttributes.put(Pair.create(Integer.valueOf(4), Integer.valueOf(4)), "layout_constraintBottom_toBottomOf");
    allAttributes.put(Pair.create(Integer.valueOf(4), Integer.valueOf(3)), "layout_constraintBottom_toTopOf");
    allAttributes.put(Pair.create(Integer.valueOf(3), Integer.valueOf(4)), "layout_constraintTop_toBottomOf");
    allAttributes.put(Pair.create(Integer.valueOf(3), Integer.valueOf(3)), "layout_constraintTop_toTopOf");
    allAttributes.put(Pair.create(Integer.valueOf(6), Integer.valueOf(6)), "layout_constraintStart_toStartOf");
    allAttributes.put(Pair.create(Integer.valueOf(6), Integer.valueOf(7)), "layout_constraintStart_toEndOf");
    allAttributes.put(Pair.create(Integer.valueOf(7), Integer.valueOf(6)), "layout_constraintEnd_toStartOf");
    allAttributes.put(Pair.create(Integer.valueOf(7), Integer.valueOf(7)), "layout_constraintEnd_toEndOf");
    allAttributes.put(Pair.create(Integer.valueOf(1), Integer.valueOf(1)), "layout_constraintLeft_toLeftOf");
    allAttributes.put(Pair.create(Integer.valueOf(1), Integer.valueOf(2)), "layout_constraintLeft_toRightOf");
    allAttributes.put(Pair.create(Integer.valueOf(2), Integer.valueOf(2)), "layout_constraintRight_toRightOf");
    allAttributes.put(Pair.create(Integer.valueOf(2), Integer.valueOf(1)), "layout_constraintRight_toLeftOf");
    allAttributes.put(Pair.create(Integer.valueOf(5), Integer.valueOf(5)), "layout_constraintBaseline_toBaselineOf");
    allMargins.put("layout_constraintBottom_toBottomOf", "layout_marginBottom");
    allMargins.put("layout_constraintBottom_toTopOf", "layout_marginBottom");
    allMargins.put("layout_constraintTop_toBottomOf", "layout_marginTop");
    allMargins.put("layout_constraintTop_toTopOf", "layout_marginTop");
    allMargins.put("layout_constraintStart_toStartOf", "layout_marginStart");
    allMargins.put("layout_constraintStart_toEndOf", "layout_marginStart");
    allMargins.put("layout_constraintEnd_toStartOf", "layout_marginEnd");
    allMargins.put("layout_constraintEnd_toEndOf", "layout_marginEnd");
    allMargins.put("layout_constraintLeft_toLeftOf", "layout_marginLeft");
    allMargins.put("layout_constraintLeft_toRightOf", "layout_marginLeft");
    allMargins.put("layout_constraintRight_toRightOf", "layout_marginRight");
    allMargins.put("layout_constraintRight_toLeftOf", "layout_marginRight");
  }
  
  public DesignTool(MotionLayout paramMotionLayout)
  {
    mMotionLayout = paramMotionLayout;
  }
  
  private static void Connect(int paramInt1, ConstraintSet paramConstraintSet, View paramView, HashMap paramHashMap, int paramInt2, int paramInt3)
  {
    String str2 = (String)allAttributes.get(Pair.create(Integer.valueOf(paramInt2), Integer.valueOf(paramInt3)));
    String str1 = (String)paramHashMap.get(str2);
    if (str1 != null)
    {
      str2 = (String)allMargins.get(str2);
      if (str2 != null) {
        paramInt1 = GetPxFromDp(paramInt1, (String)paramHashMap.get(str2));
      } else {
        paramInt1 = 0;
      }
      int i = Integer.parseInt(str1);
      paramConstraintSet.connect(paramView.getId(), paramInt2, i, paramInt3, paramInt1);
    }
  }
  
  private static int GetPxFromDp(int paramInt, String paramString)
  {
    if (paramString == null) {
      return 0;
    }
    int i = paramString.indexOf('d');
    if (i == -1) {
      return 0;
    }
    return (int)(Integer.valueOf(paramString.substring(0, i)).intValue() * paramInt / 160.0F);
  }
  
  private static void SetAbsolutePositions(int paramInt, ConstraintSet paramConstraintSet, View paramView, HashMap paramHashMap)
  {
    String str = (String)paramHashMap.get("layout_editor_absoluteX");
    if (str != null) {
      paramConstraintSet.setEditorAbsoluteX(paramView.getId(), GetPxFromDp(paramInt, str));
    }
    paramHashMap = (String)paramHashMap.get("layout_editor_absoluteY");
    if (paramHashMap != null) {
      paramConstraintSet.setEditorAbsoluteY(paramView.getId(), GetPxFromDp(paramInt, paramHashMap));
    }
  }
  
  private static void SetBias(ConstraintSet paramConstraintSet, View paramView, HashMap paramHashMap, int paramInt)
  {
    String str = "layout_constraintHorizontal_bias";
    if (paramInt == 1) {
      str = "layout_constraintVertical_bias";
    }
    paramHashMap = (String)paramHashMap.get(str);
    if (paramHashMap != null)
    {
      if (paramInt == 0)
      {
        paramConstraintSet.setHorizontalBias(paramView.getId(), Float.parseFloat(paramHashMap));
        return;
      }
      if (paramInt == 1) {
        paramConstraintSet.setVerticalBias(paramView.getId(), Float.parseFloat(paramHashMap));
      }
    }
  }
  
  private static void SetDimensions(int paramInt1, ConstraintSet paramConstraintSet, View paramView, HashMap paramHashMap, int paramInt2)
  {
    String str = "layout_width";
    if (paramInt2 == 1) {
      str = "layout_height";
    }
    paramHashMap = (String)paramHashMap.get(str);
    if (paramHashMap != null)
    {
      int i = -2;
      if (!paramHashMap.equalsIgnoreCase("wrap_content")) {
        i = GetPxFromDp(paramInt1, paramHashMap);
      }
      if (paramInt2 == 0)
      {
        paramConstraintSet.constrainWidth(paramView.getId(), i);
        return;
      }
      paramConstraintSet.constrainHeight(paramView.getId(), i);
    }
  }
  
  public int designAccess(int paramInt1, String paramString, Object paramObject, float[] paramArrayOfFloat1, int paramInt2, float[] paramArrayOfFloat2, int paramInt3)
  {
    paramObject = (View)paramObject;
    if (paramInt1 != 0)
    {
      if (mMotionLayout.mScene == null) {
        return -1;
      }
      if (paramObject != null)
      {
        paramArrayOfFloat1 = (MotionController)mMotionLayout.mFrameArrayList.get(paramObject);
        paramObject = paramArrayOfFloat1;
        if (paramArrayOfFloat1 != null) {}
      }
      else
      {
        return -1;
      }
    }
    else
    {
      paramObject = null;
    }
    switch (paramInt1)
    {
    default: 
      return -1;
    case 3: 
      mMotionLayout.mScene.getDuration();
      return paramObject.getAttributeValues(paramString, paramArrayOfFloat2, paramInt3);
    case 2: 
      paramInt1 = mMotionLayout.mScene.getDuration() / 16;
      paramObject.buildKeyFrames(paramArrayOfFloat2, null);
      return paramInt1;
    case 1: 
      paramInt1 = mMotionLayout.mScene.getDuration() / 16;
      paramObject.buildPath(paramArrayOfFloat2, paramInt1);
      return paramInt1;
    }
    return 1;
  }
  
  public int getAnimationKeyFames(Object paramObject, float[] paramArrayOfFloat)
  {
    if (mMotionLayout.mScene == null) {
      return -1;
    }
    int i = mMotionLayout.mScene.getDuration() / 16;
    paramObject = (MotionController)mMotionLayout.mFrameArrayList.get(paramObject);
    if (paramObject == null) {
      return 0;
    }
    paramObject.buildKeyFrames(paramArrayOfFloat, null);
    return i;
  }
  
  public int getAnimationPath(Object paramObject, float[] paramArrayOfFloat)
  {
    if (mMotionLayout.mScene == null) {
      return -1;
    }
    int i = mMotionLayout.mScene.getDuration() / 16;
    paramObject = (MotionController)mMotionLayout.mFrameArrayList.get(paramObject);
    if (paramObject == null) {
      return 0;
    }
    paramObject.buildPath(paramArrayOfFloat, i);
    return i;
  }
  
  public void getAnimationRectangles(Object paramObject, float[] paramArrayOfFloat)
  {
    if (mMotionLayout.mScene == null) {
      return;
    }
    int i = mMotionLayout.mScene.getDuration() / 16;
    paramObject = (MotionController)mMotionLayout.mFrameArrayList.get(paramObject);
    if (paramObject == null) {
      return;
    }
    paramObject.buildRectangles(paramArrayOfFloat, i);
  }
  
  public float getKeyFramePosition(Object paramObject, int paramInt, float paramFloat1, float paramFloat2)
  {
    return ((MotionController)mMotionLayout.mFrameArrayList.get((View)paramObject)).getKeyFrameParameter(paramInt, paramFloat1, paramFloat2);
  }
  
  public Object getKeyframe(int paramInt1, int paramInt2, int paramInt3)
  {
    if (mMotionLayout.mScene == null) {
      return null;
    }
    return mMotionLayout.mScene.getKeyFrame(mMotionLayout.getContext(), paramInt1, paramInt2, paramInt3);
  }
  
  public Object getKeyframeAtLocation(Object paramObject, float paramFloat1, float paramFloat2)
  {
    Object localObject = (View)paramObject;
    if (mMotionLayout.mScene == null) {
      return Integer.valueOf(-1);
    }
    if (localObject != null)
    {
      paramObject = (MotionController)mMotionLayout.mFrameArrayList.get(localObject);
      if (paramObject == null) {
        return null;
      }
      localObject = (ViewGroup)((View)localObject).getParent();
      return paramObject.getPositionKeyframe(((View)localObject).getWidth(), ((View)localObject).getHeight(), paramFloat1, paramFloat2);
    }
    return null;
  }
  
  public Boolean getPositionKeyframe(Object paramObject1, Object paramObject2, float paramFloat1, float paramFloat2, String[] paramArrayOfString, float[] paramArrayOfFloat)
  {
    if ((paramObject1 instanceof KeyPositionBase))
    {
      paramObject1 = (KeyPositionBase)paramObject1;
      HashMap localHashMap = mMotionLayout.mFrameArrayList;
      paramObject2 = (View)paramObject2;
      ((MotionController)localHashMap.get(paramObject2)).positionKeyframe(paramObject2, paramObject1, paramFloat1, paramFloat2, paramArrayOfString, paramArrayOfFloat);
      mMotionLayout.mSetup = false;
      mMotionLayout.mInTransition = true;
      return Boolean.valueOf(true);
    }
    return Boolean.valueOf(false);
  }
  
  public long getTransitionTimeMs()
  {
    return mMotionLayout.getTransitionTimeMs();
  }
  
  public void setAttributes(int paramInt, String paramString, Object paramObject1, Object paramObject2)
  {
    paramObject1 = (View)paramObject1;
    paramObject2 = (HashMap)paramObject2;
    if (paramString.equals("@+id/start")) {
      paramString = mMotionLayout.mScene.getConstraintSet(R.id.start);
    } else if (paramString.equals("@+id/end")) {
      paramString = mMotionLayout.mScene.getConstraintSet(R.id.genre);
    } else {
      paramString = null;
    }
    if (paramString == null) {
      return;
    }
    paramString.clear(paramObject1.getId());
    SetDimensions(paramInt, paramString, paramObject1, paramObject2, 0);
    SetDimensions(paramInt, paramString, paramObject1, paramObject2, 1);
    Connect(paramInt, paramString, paramObject1, paramObject2, 6, 6);
    Connect(paramInt, paramString, paramObject1, paramObject2, 6, 7);
    Connect(paramInt, paramString, paramObject1, paramObject2, 7, 7);
    Connect(paramInt, paramString, paramObject1, paramObject2, 7, 6);
    Connect(paramInt, paramString, paramObject1, paramObject2, 1, 1);
    Connect(paramInt, paramString, paramObject1, paramObject2, 1, 2);
    Connect(paramInt, paramString, paramObject1, paramObject2, 2, 2);
    Connect(paramInt, paramString, paramObject1, paramObject2, 2, 1);
    Connect(paramInt, paramString, paramObject1, paramObject2, 3, 3);
    Connect(paramInt, paramString, paramObject1, paramObject2, 3, 4);
    Connect(paramInt, paramString, paramObject1, paramObject2, 4, 3);
    Connect(paramInt, paramString, paramObject1, paramObject2, 4, 4);
    Connect(paramInt, paramString, paramObject1, paramObject2, 5, 5);
    SetBias(paramString, paramObject1, paramObject2, 0);
    SetBias(paramString, paramObject1, paramObject2, 1);
    SetAbsolutePositions(paramInt, paramString, paramObject1, paramObject2);
    mMotionLayout.mStartConstraintSetComputed = false;
    mMotionLayout.mEndConstraintSetComputed = false;
    mMotionLayout.mTransitionLastPosition = -1.0F;
    mMotionLayout.mSetup = false;
  }
  
  public void setKeyFrame(Object paramObject1, int paramInt, String paramString, Object paramObject2)
  {
    if (mMotionLayout.mScene != null)
    {
      mMotionLayout.mScene.setKeyframe((View)paramObject1, paramInt, paramString, paramObject2);
      mMotionLayout.mTransitionGoalPosition = (paramInt / 100.0F);
      mMotionLayout.mTransitionLastPosition = 0.0F;
      mMotionLayout.mSetup = false;
      mMotionLayout.evaluate(true);
    }
  }
  
  public boolean setKeyFramePosition(Object paramObject, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2)
  {
    if (mMotionLayout.mScene != null)
    {
      MotionController localMotionController = (MotionController)mMotionLayout.mFrameArrayList.get(paramObject);
      float f = mMotionLayout.mTransitionPosition;
      if (localMotionController != null)
      {
        MotionScene localMotionScene = mMotionLayout.mScene;
        paramObject = (View)paramObject;
        if (localMotionScene.hasKeyFramePosition(paramObject, 30))
        {
          f = localMotionController.getKeyFrameParameter(2, paramFloat1, paramFloat2);
          paramFloat1 = localMotionController.getKeyFrameParameter(5, paramFloat1, paramFloat2);
          mMotionLayout.mScene.setKeyframe(paramObject, 30, "motion:percentX", Float.valueOf(f));
          mMotionLayout.mScene.setKeyframe(paramObject, 30, "motion:percentY", Float.valueOf(paramFloat1));
          mMotionLayout.mSetup = false;
          mMotionLayout.evaluate(true);
          mMotionLayout.invalidate();
          return true;
        }
      }
    }
    return false;
  }
  
  public void setKeyframe(Object paramObject1, String paramString, Object paramObject2)
  {
    if ((paramObject1 instanceof FieldInfo))
    {
      ((FieldInfo)paramObject1).setValue(paramString, paramObject2);
      mMotionLayout.mSetup = false;
      mMotionLayout.mInTransition = true;
    }
  }
  
  public void setToolPosition(float paramFloat)
  {
    mMotionLayout.mIndirectTransition = true;
    mMotionLayout.setProgress(paramFloat);
    Object localObject = mMotionLayout;
    int i = 0;
    ((MotionLayout)localObject).evaluate(false);
    while (i < mMotionLayout.getChildCount())
    {
      localObject = mMotionLayout.getChildAt(i);
      if ((localObject instanceof MotionLayout)) {
        ((MotionLayout)localObject).setProgress(paramFloat);
      }
      i += 1;
    }
  }
  
  public void setViewDebug(Object paramObject, int paramInt)
  {
    paramObject = (MotionController)mMotionLayout.mFrameArrayList.get(paramObject);
    if (paramObject != null)
    {
      paramObject.setDrawPath(paramInt);
      mMotionLayout.invalidate();
    }
  }
}
