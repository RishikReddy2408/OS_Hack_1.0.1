package android.support.constraint.motion;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.support.constraint.ConstraintSet;
import android.support.constraint.R.styleable;
import android.support.constraint.StateSet;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.Xml;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class MotionScene
{
  static final int ANTICIPATE = 4;
  static final int BOUNCE = 5;
  private static final boolean DEBUG = false;
  static final int EASE_IN = 1;
  static final int EASE_IN_OUT = 0;
  static final int EASE_OUT = 2;
  static final int LINEAR = 3;
  public static final String PAGE_KEY = "MotionScene";
  static final int TRANSITION_BACKWARD = 0;
  static final int TRANSITION_FORWARD = 1;
  public static final int UNSET = -1;
  private boolean DEBUG_DESKTOP = false;
  private SparseArray<ConstraintSet> mConstraintSetMap = new SparseArray();
  Transition mCurrentTransition = null;
  private int mDefaultDuration = 100;
  private final MotionLayout mMotionLayout;
  StateSet mStateSet = null;
  private ArrayList<Transition> mTransitionList = new ArrayList();
  
  MotionScene(Context paramContext, MotionLayout paramMotionLayout, int paramInt)
  {
    mMotionLayout = paramMotionLayout;
    load(paramContext, paramInt);
  }
  
  private void load(Context paramContext, int paramInt)
  {
    XmlResourceParser localXmlResourceParser = paramContext.getResources().getXml(paramInt);
    Object localObject1 = null;
    try
    {
      int i = localXmlResourceParser.getEventType();
      for (;;)
      {
        int j = 1;
        if (i == 1) {
          break;
        }
        Object localObject2;
        if (i != 0) {
          localObject2 = localObject1;
        }
        switch (i)
        {
        default: 
          localObject2 = localObject1;
          break;
        case 2: 
          localObject2 = localXmlResourceParser.getName();
          if (DEBUG_DESKTOP)
          {
            localObject3 = System.out;
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("parsing = ");
            localStringBuilder.append((String)localObject2);
            ((PrintStream)localObject3).println(localStringBuilder.toString());
          }
          i = ((String)localObject2).hashCode();
          boolean bool;
          switch (i)
          {
          default: 
            break;
          case 1382829617: 
            bool = ((String)localObject2).equals("StateSet");
            if (bool) {
              i = 4;
            }
            break;
          case 793277014: 
            bool = ((String)localObject2).equals("MotionScene");
            if (bool) {
              i = 0;
            }
            break;
          case 327855227: 
            bool = ((String)localObject2).equals("OnSwipe");
            if (bool) {
              i = 2;
            }
            break;
          case 312750793: 
            bool = ((String)localObject2).equals("OnClick");
            if (bool) {
              i = 3;
            }
            break;
          case 269306229: 
            bool = ((String)localObject2).equals("Transition");
            if (bool) {
              i = j;
            }
            break;
          case -1239391468: 
            bool = ((String)localObject2).equals("KeyFrameSet");
            if (bool) {
              i = 6;
            }
            break;
          case -1349929691: 
            bool = ((String)localObject2).equals("ConstraintSet");
            if (bool) {
              i = 5;
            }
            break;
          }
          i = -1;
          switch (i)
          {
          default: 
            break;
          case 6: 
            localObject2 = new KeyFrames(paramContext, localXmlResourceParser);
            mKeyFramesList.add(localObject2);
            localObject2 = localObject1;
            break;
          case 5: 
            parseConstraintSet(paramContext, localXmlResourceParser);
            localObject2 = localObject1;
            break;
          case 4: 
            localObject2 = new StateSet(paramContext, localXmlResourceParser);
            mStateSet = ((StateSet)localObject2);
            localObject2 = localObject1;
            break;
          case 3: 
            ((Transition)localObject1).addOnClick(paramContext, localXmlResourceParser);
            localObject2 = localObject1;
            break;
          case 2: 
            if (localObject1 == null)
            {
              localObject2 = paramContext.getResources().getResourceEntryName(paramInt);
              i = localXmlResourceParser.getLineNumber();
              localObject3 = new StringBuilder();
              ((StringBuilder)localObject3).append(" OnSwipe (");
              ((StringBuilder)localObject3).append((String)localObject2);
              ((StringBuilder)localObject3).append(".xml:");
              ((StringBuilder)localObject3).append(i);
              ((StringBuilder)localObject3).append(")");
              Log.v("MotionScene", ((StringBuilder)localObject3).toString());
            }
            localObject2 = mMotionLayout;
            Transition.access$702((Transition)localObject1, new TouchResponse(paramContext, (MotionLayout)localObject2, localXmlResourceParser));
            localObject2 = localObject1;
            break;
          case 1: 
            localObject1 = mTransitionList;
            localObject2 = new Transition(this, paramContext, localXmlResourceParser);
            ((ArrayList)localObject1).add(localObject2);
            if (mCurrentTransition == null) {
              mCurrentTransition = ((Transition)localObject2);
            }
            break;
          case 0: 
            parseMotionSceneTags(paramContext, localXmlResourceParser);
            localObject2 = localObject1;
            break;
          }
          Object localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append("WARNING UNKNOWN ATTRIBUTE ");
          ((StringBuilder)localObject3).append((String)localObject2);
          Log.v("MotionScene", ((StringBuilder)localObject3).toString());
          localObject2 = localObject1;
          break;
          localXmlResourceParser.getName();
          localObject2 = localObject1;
        }
        i = localXmlResourceParser.next();
        localObject1 = localObject2;
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
  
  private void parseConstraintSet(Context paramContext, XmlPullParser paramXmlPullParser)
  {
    ConstraintSet localConstraintSet = new ConstraintSet();
    int j = paramXmlPullParser.getAttributeCount();
    int i = 0;
    while (i < j)
    {
      if ("id".equals(paramXmlPullParser.getAttributeName(i)))
      {
        String str = paramXmlPullParser.getAttributeValue(i);
        Object localObject;
        StringBuilder localStringBuilder;
        if (DEBUG_DESKTOP)
        {
          localObject = System.out;
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("id string = ");
          localStringBuilder.append(str);
          ((PrintStream)localObject).println(localStringBuilder.toString());
        }
        if (str.contains("/"))
        {
          localObject = str.substring(str.indexOf('/') + 1);
          int k = paramContext.getResources().getIdentifier((String)localObject, "id", null);
          j = k;
          i = j;
          if (DEBUG_DESKTOP)
          {
            localObject = System.out;
            localStringBuilder = new StringBuilder();
            localStringBuilder.append("id getMap res = ");
            localStringBuilder.append(k);
            ((PrintStream)localObject).println(localStringBuilder.toString());
            i = j;
          }
        }
        else
        {
          i = -1;
        }
        j = i;
        if (i == -1) {
          if ((str != null) && (str.length() > 1))
          {
            j = Integer.parseInt(str.substring(1));
          }
          else
          {
            Log.e("MotionScene", "error in parsing id");
            j = i;
          }
        }
        localConstraintSet.load(paramContext, paramXmlPullParser);
        mConstraintSetMap.put(j, localConstraintSet);
        return;
      }
      i += 1;
    }
  }
  
  private void parseMotionSceneTags(Context paramContext, XmlPullParser paramXmlPullParser)
  {
    paramContext = paramContext.obtainStyledAttributes(Xml.asAttributeSet(paramXmlPullParser), R.styleable.MotionScene);
    int j = paramContext.getIndexCount();
    int i = 0;
    while (i < j)
    {
      int k = paramContext.getIndex(i);
      if (k == R.styleable.MotionScene_duration) {
        mDefaultDuration = paramContext.getInt(k, mDefaultDuration);
      }
      i += 1;
    }
    paramContext.recycle();
  }
  
  public void addOnClickListeners(MotionLayout paramMotionLayout)
  {
    Iterator localIterator1 = mTransitionList.iterator();
    while (localIterator1.hasNext())
    {
      Transition localTransition = (Transition)localIterator1.next();
      if (mOnClicks.size() > 0)
      {
        Iterator localIterator2 = mOnClicks.iterator();
        while (localIterator2.hasNext())
        {
          MotionScene.Transition.TransitionOnClick localTransitionOnClick = (MotionScene.Transition.TransitionOnClick)localIterator2.next();
          if ((mode == 3) || (localTransition == mCurrentTransition)) {
            localTransitionOnClick.addOnClickListeners(paramMotionLayout);
          }
        }
      }
    }
  }
  
  ConstraintSet getConstraintSet(int paramInt)
  {
    return getConstraintSet(paramInt, -1, -1);
  }
  
  ConstraintSet getConstraintSet(int paramInt1, int paramInt2, int paramInt3)
  {
    if (DEBUG_DESKTOP)
    {
      PrintStream localPrintStream = System.out;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("id ");
      localStringBuilder.append(paramInt1);
      localPrintStream.println(localStringBuilder.toString());
      localPrintStream = System.out;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("size ");
      localStringBuilder.append(mConstraintSetMap.size());
      localPrintStream.println(localStringBuilder.toString());
    }
    int i = paramInt1;
    if (mStateSet != null)
    {
      paramInt2 = mStateSet.stateGetConstraintID(paramInt1, paramInt2, paramInt3);
      i = paramInt1;
      if (paramInt2 != -1) {
        i = paramInt2;
      }
    }
    if (mConstraintSetMap.get(i) == null) {
      return (ConstraintSet)mConstraintSetMap.get(mConstraintSetMap.keyAt(0));
    }
    return (ConstraintSet)mConstraintSetMap.get(i);
  }
  
  public ConstraintSet getConstraintSet(Context paramContext, String paramString)
  {
    Object localObject1;
    Object localObject2;
    if (DEBUG_DESKTOP)
    {
      localObject1 = System.out;
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("id ");
      ((StringBuilder)localObject2).append(paramString);
      ((PrintStream)localObject1).println(((StringBuilder)localObject2).toString());
      localObject1 = System.out;
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("size ");
      ((StringBuilder)localObject2).append(mConstraintSetMap.size());
      ((PrintStream)localObject1).println(((StringBuilder)localObject2).toString());
    }
    int i = 0;
    while (i < mConstraintSetMap.size())
    {
      int j = mConstraintSetMap.keyAt(i);
      localObject1 = paramContext.getResources().getResourceName(j);
      localObject2 = System.out;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Id for <");
      localStringBuilder.append(i);
      localStringBuilder.append("> is <");
      localStringBuilder.append((String)localObject1);
      localStringBuilder.append("> looking for <");
      localStringBuilder.append(paramString);
      localStringBuilder.append(">");
      ((PrintStream)localObject2).println(localStringBuilder.toString());
      if (paramString.equals(localObject1)) {
        return (ConstraintSet)mConstraintSetMap.get(j);
      }
      i += 1;
    }
    return null;
  }
  
  int getDuration()
  {
    if (mCurrentTransition != null) {
      return mCurrentTransition.mDuration;
    }
    return 300;
  }
  
  int getEndId()
  {
    if (mCurrentTransition == null) {
      return -1;
    }
    return mCurrentTransition.mConstraintSetEnd;
  }
  
  public Interpolator getInterpolator()
  {
    switch (mTransitionList.get(0)).mDefaultInterpolator)
    {
    default: 
      return null;
    case 5: 
      return new BounceInterpolator();
    case 4: 
      return new AnticipateInterpolator();
    case 3: 
      return null;
    case 2: 
      return new DecelerateInterpolator();
    case 1: 
      return new AnticipateInterpolator();
    }
    return new AccelerateDecelerateInterpolator();
  }
  
  FieldInfo getKeyFrame(Context paramContext, int paramInt1, int paramInt2, int paramInt3)
  {
    if (mCurrentTransition == null) {
      return null;
    }
    paramContext = mCurrentTransition.mKeyFramesList.iterator();
    label20:
    if (paramContext.hasNext())
    {
      KeyFrames localKeyFrames = (KeyFrames)paramContext.next();
      Iterator localIterator = localKeyFrames.getKeys().iterator();
      label98:
      FieldInfo localFieldInfo;
      do
      {
        break label98;
        if (!localIterator.hasNext()) {
          break label20;
        }
        Object localObject = (Integer)localIterator.next();
        if (paramInt2 != ((Integer)localObject).intValue()) {
          break;
        }
        localObject = localKeyFrames.getMap(((Integer)localObject).intValue()).iterator();
        if (!((Iterator)localObject).hasNext()) {
          break;
        }
        localFieldInfo = (FieldInfo)((Iterator)localObject).next();
      } while ((mFramePosition != paramInt3) || (mType != paramInt1));
      return localFieldInfo;
    }
    return null;
  }
  
  public void getKeyFrames(MotionController paramMotionController)
  {
    if (mCurrentTransition == null) {
      return;
    }
    Iterator localIterator = mCurrentTransition.mKeyFramesList.iterator();
    while (localIterator.hasNext()) {
      ((KeyFrames)localIterator.next()).addFrames(paramMotionController);
    }
  }
  
  float getMaxAcceleration()
  {
    if ((mCurrentTransition != null) && (mCurrentTransition.mTouchResponse != null)) {
      return mCurrentTransition.mTouchResponse.getMaxAcceleration();
    }
    return 0.0F;
  }
  
  float getMaxVelocity()
  {
    if ((mCurrentTransition != null) && (mCurrentTransition.mTouchResponse != null)) {
      return mCurrentTransition.mTouchResponse.getMaxVelocity();
    }
    return 0.0F;
  }
  
  boolean getMoveWhenScrollAtTop()
  {
    if ((mCurrentTransition != null) && (mCurrentTransition.mTouchResponse != null)) {
      return mCurrentTransition.mTouchResponse.getMoveWhenScrollAtTop();
    }
    return false;
  }
  
  public float getPathPercent(View paramView, int paramInt)
  {
    return 0.0F;
  }
  
  float getStaggered()
  {
    if (mCurrentTransition != null) {
      return mCurrentTransition.mStagger;
    }
    return 0.0F;
  }
  
  int getStartId()
  {
    if (mCurrentTransition == null) {
      return -1;
    }
    return mCurrentTransition.mConstraintSetStart;
  }
  
  int getTransitionDirection(int paramInt)
  {
    Iterator localIterator = mTransitionList.iterator();
    while (localIterator.hasNext()) {
      if (nextmConstraintSetStart == paramInt) {
        return 0;
      }
    }
    return 1;
  }
  
  boolean hasKeyFramePosition(View paramView, int paramInt)
  {
    if (mCurrentTransition == null) {
      return false;
    }
    Iterator localIterator2;
    do
    {
      Iterator localIterator1 = mCurrentTransition.mKeyFramesList.iterator();
      while (!localIterator2.hasNext())
      {
        if (!localIterator1.hasNext()) {
          break;
        }
        localIterator2 = ((KeyFrames)localIterator1.next()).getMap(paramView.getId()).iterator();
      }
    } while (nextmFramePosition != paramInt);
    return true;
    return false;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {}
  
  void processScrollMove(float paramFloat1, float paramFloat2)
  {
    if ((mCurrentTransition != null) && (mCurrentTransition.mTouchResponse != null)) {
      mCurrentTransition.mTouchResponse.scrollMove(paramFloat1, paramFloat2);
    }
  }
  
  void processScrollUp(float paramFloat1, float paramFloat2)
  {
    if ((mCurrentTransition != null) && (mCurrentTransition.mTouchResponse != null)) {
      mCurrentTransition.mTouchResponse.scrollUp(paramFloat1, paramFloat2);
    }
  }
  
  void processTouchEvent(MotionEvent paramMotionEvent, MotionLayout paramMotionLayout)
  {
    if ((mCurrentTransition != null) && (mCurrentTransition.mTouchResponse != null)) {
      mCurrentTransition.mTouchResponse.processTouchEvent(paramMotionEvent, this);
    }
  }
  
  void readFallback(MotionLayout paramMotionLayout)
  {
    int i = 0;
    while (i < mConstraintSetMap.size())
    {
      ((ConstraintSet)mConstraintSetMap.valueAt(i)).readFallback(paramMotionLayout);
      i += 1;
    }
  }
  
  public void setKeyframe(View paramView, int paramInt, String paramString, Object paramObject)
  {
    if (mCurrentTransition == null) {
      return;
    }
    Iterator localIterator1 = mCurrentTransition.mKeyFramesList.iterator();
    while (localIterator1.hasNext())
    {
      Iterator localIterator2 = ((KeyFrames)localIterator1.next()).getMap(paramView.getId()).iterator();
      while (localIterator2.hasNext()) {
        if (nextmFramePosition == paramInt)
        {
          if (paramObject != null) {
            ((Float)paramObject).floatValue();
          }
          paramString.equalsIgnoreCase("app:PerpendicularPath_percent");
        }
      }
    }
  }
  
  void setTransition(int paramInt1, int paramInt2)
  {
    int j;
    if (mStateSet != null)
    {
      j = mStateSet.stateGetConstraintID(paramInt1, -1, -1);
      i = j;
      if (j == -1) {
        i = paramInt1;
      }
      int m = mStateSet.stateGetConstraintID(paramInt2, -1, -1);
      k = m;
      j = i;
      if (m != -1)
      {
        j = i;
        break label72;
      }
    }
    else
    {
      j = paramInt1;
    }
    int i = paramInt2;
    int k = i;
    label72:
    Object localObject = mTransitionList.iterator();
    while (((Iterator)localObject).hasNext())
    {
      Transition localTransition = (Transition)((Iterator)localObject).next();
      if (((mConstraintSetEnd == k) && (mConstraintSetStart == j)) || ((mConstraintSetEnd == paramInt2) && (mConstraintSetStart == paramInt1)))
      {
        mCurrentTransition = localTransition;
        return;
      }
    }
    localObject = new Transition(this);
    Transition.access$102((Transition)localObject, j);
    Transition.access$002((Transition)localObject, k);
    Transition.access$202((Transition)localObject, mDefaultDuration);
    mTransitionList.add(localObject);
    mCurrentTransition = ((Transition)localObject);
  }
  
  void setupTouch()
  {
    if ((mCurrentTransition != null) && (mCurrentTransition.mTouchResponse != null)) {
      mCurrentTransition.mTouchResponse.setupTouch();
    }
  }
  
  boolean shouldInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    if (supportTouch()) {
      return mCurrentTransition.mTouchResponse.shouldInterceptTouchEvent(paramMotionEvent);
    }
    return false;
  }
  
  boolean supportTouch()
  {
    return (mCurrentTransition != null) && (mCurrentTransition.mTouchResponse != null);
  }
  
  static class Transition
  {
    private int mConstraintSetEnd = 0;
    private int mConstraintSetStart = 0;
    private int mDefaultInterpolator = 0;
    private int mDuration = 400;
    private ArrayList<KeyFrames> mKeyFramesList = new ArrayList();
    private final MotionScene mMotionScene;
    private ArrayList<TransitionOnClick> mOnClicks = new ArrayList();
    private float mStagger = 0.0F;
    private TouchResponse mTouchResponse = null;
    
    Transition(MotionScene paramMotionScene)
    {
      mMotionScene = paramMotionScene;
    }
    
    Transition(MotionScene paramMotionScene, Context paramContext, XmlPullParser paramXmlPullParser)
    {
      mMotionScene = paramMotionScene;
      fillFromAttributeList(paramMotionScene, paramContext, Xml.asAttributeSet(paramXmlPullParser));
    }
    
    private void fill(MotionScene paramMotionScene, Context paramContext, TypedArray paramTypedArray)
    {
      int j = paramTypedArray.getIndexCount();
      int i = 0;
      while (i < j)
      {
        int k = paramTypedArray.getIndex(i);
        ConstraintSet localConstraintSet;
        if (k == R.styleable.Transition_constraintSetEnd)
        {
          mConstraintSetEnd = paramTypedArray.getResourceId(k, mConstraintSetEnd);
          if ("layout".equals(paramContext.getResources().getResourceTypeName(mConstraintSetEnd)))
          {
            localConstraintSet = new ConstraintSet();
            localConstraintSet.load(paramContext, mConstraintSetEnd);
            mConstraintSetMap.append(mConstraintSetEnd, localConstraintSet);
          }
        }
        else if (k == R.styleable.Transition_constraintSetStart)
        {
          mConstraintSetStart = paramTypedArray.getResourceId(k, mConstraintSetStart);
          if ("layout".equals(paramContext.getResources().getResourceTypeName(mConstraintSetStart)))
          {
            localConstraintSet = new ConstraintSet();
            localConstraintSet.load(paramContext, mConstraintSetStart);
            mConstraintSetMap.append(mConstraintSetStart, localConstraintSet);
          }
        }
        else if (k == R.styleable.Transition_interpolator)
        {
          mDefaultInterpolator = paramTypedArray.getInteger(k, mDefaultInterpolator);
        }
        else if (k == R.styleable.Transition_duration)
        {
          mDuration = paramTypedArray.getInt(k, mDuration);
        }
        else if (k == R.styleable.Transition_staggered)
        {
          mStagger = paramTypedArray.getFloat(k, mStagger);
        }
        i += 1;
      }
    }
    
    private void fillFromAttributeList(MotionScene paramMotionScene, Context paramContext, AttributeSet paramAttributeSet)
    {
      paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.Transition);
      fill(paramMotionScene, paramContext, paramAttributeSet);
      paramAttributeSet.recycle();
    }
    
    public void addOnClick(Context paramContext, XmlPullParser paramXmlPullParser)
    {
      mOnClicks.add(new TransitionOnClick(paramContext, this, paramXmlPullParser));
    }
    
    static class TransitionOnClick
      implements View.OnClickListener
    {
      public static final int BACKWARD = 2;
      public static final int FORWARD = 0;
      public static final int TOGGLE = 1;
      public static final int TO_END = 3;
      public static final int TO_START = 4;
      int mTargetId;
      private final MotionScene.Transition mTransition;
      int mode = 1;
      
      public TransitionOnClick(Context paramContext, MotionScene.Transition paramTransition, XmlPullParser paramXmlPullParser)
      {
        mTransition = paramTransition;
        paramContext = paramContext.obtainStyledAttributes(Xml.asAttributeSet(paramXmlPullParser), R.styleable.OnClick);
        int j = paramContext.getIndexCount();
        int i = 0;
        while (i < j)
        {
          int k = paramContext.getIndex(i);
          if (k == R.styleable.OnClick_target) {
            mTargetId = paramContext.getResourceId(k, mTargetId);
          } else if (k == R.styleable.OnClick_mode) {
            mode = paramContext.getInt(k, mode);
          }
          i += 1;
        }
        paramContext.recycle();
      }
      
      public void addOnClickListeners(MotionLayout paramMotionLayout)
      {
        paramMotionLayout = paramMotionLayout.findViewById(mTargetId);
        if (paramMotionLayout == null)
        {
          paramMotionLayout = new StringBuilder();
          paramMotionLayout.append(" (*)  could not find id ");
          paramMotionLayout.append(mTargetId);
          Log.e("MotionScene", paramMotionLayout.toString());
          return;
        }
        paramMotionLayout.setOnClickListener(this);
      }
      
      boolean isTransitionViable(MotionScene.Transition paramTransition, boolean paramBoolean, MotionLayout paramMotionLayout)
      {
        if (mTransition == paramTransition) {
          return true;
        }
        int i;
        if (paramBoolean) {
          i = mTransition.mConstraintSetEnd;
        } else {
          i = mTransition.mConstraintSetStart;
        }
        int j;
        if (paramBoolean) {
          j = mTransition.mConstraintSetStart;
        } else {
          j = mTransition.mConstraintSetEnd;
        }
        if (paramMotionLayout.getProgress() == 0.0F) {
          return mCurrentState == j;
        }
        if (paramMotionLayout.getProgress() == 1.0F) {
          return mCurrentState == i;
        }
        return false;
      }
      
      public void onClick(View paramView)
      {
        paramView = access$400mTransition).mMotionLayout;
        MotionScene.Transition localTransition = mTransition.mMotionScene.mCurrentTransition;
        switch (mode)
        {
        default: 
          
        case 4: 
          paramView.setState(mTransition.mConstraintSetEnd, -1, -1);
          return;
        case 3: 
          paramView.setState(mTransition.mConstraintSetEnd, -1, -1);
          return;
        case 2: 
          if (isTransitionViable(localTransition, false, paramView))
          {
            paramView.transitionToStart();
            return;
          }
          break;
        case 1: 
          if (mTransition.mMotionScene.mCurrentTransition == mTransition)
          {
            if (paramView.getProgress() > 0.5F)
            {
              paramView.transitionToStart();
              return;
            }
            paramView.transitionToEnd();
            return;
          }
          break;
        case 0: 
          if (isTransitionViable(localTransition, true, paramView)) {
            paramView.transitionToEnd();
          }
          break;
        }
      }
    }
  }
}
