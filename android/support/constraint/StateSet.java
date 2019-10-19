package android.support.constraint;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.Log;
import android.util.SparseArray;
import android.util.Xml;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class StateSet
{
  private static final boolean DEBUG = false;
  public static final String EVENTLOG_URL = "ConstraintLayoutStates";
  private SparseArray<ConstraintSet> mConstraintSetMap = new SparseArray();
  private ConstraintsChangedListener mConstraintsChangedListener = null;
  int mCurrentConstraintNumber = -1;
  int mCurrentStateId = -1;
  ConstraintSet mDefaultConstraintSet;
  int mDefaultState = -1;
  private SparseArray<State> mStateList = new SparseArray();
  
  public StateSet(Context paramContext, XmlPullParser paramXmlPullParser)
  {
    load(paramContext, paramXmlPullParser);
  }
  
  private void load(Context paramContext, XmlPullParser paramXmlPullParser)
  {
    Object localObject1 = paramContext.obtainStyledAttributes(Xml.asAttributeSet(paramXmlPullParser), R.styleable.StateSet);
    int j = ((TypedArray)localObject1).getIndexCount();
    int i = 0;
    while (i < j)
    {
      int k = ((TypedArray)localObject1).getIndex(i);
      if (k == R.styleable.StateSet_defaultState) {
        mDefaultState = ((TypedArray)localObject1).getResourceId(k, mDefaultState);
      }
      i += 1;
    }
    localObject1 = null;
    try
    {
      i = paramXmlPullParser.getEventType();
      for (;;)
      {
        j = 1;
        if (i == 1) {
          break;
        }
        Object localObject2;
        if (i != 0)
        {
          boolean bool;
          switch (i)
          {
          default: 
            localObject2 = localObject1;
            break;
          case 3: 
            bool = "StateSet".equals(paramXmlPullParser.getName());
            localObject2 = localObject1;
            if (!bool) {
              break;
            }
            return;
          case 2: 
            Object localObject3 = paramXmlPullParser.getName();
            i = ((String)localObject3).hashCode();
            if (i != 80204913)
            {
              if (i != 1301459538)
              {
                if (i != 1382829617)
                {
                  if (i == 1901439077)
                  {
                    bool = ((String)localObject3).equals("Variant");
                    if (bool)
                    {
                      i = 3;
                      break label269;
                    }
                  }
                }
                else
                {
                  bool = ((String)localObject3).equals("StateSet");
                  if (bool)
                  {
                    i = j;
                    break label269;
                  }
                }
              }
              else
              {
                bool = ((String)localObject3).equals("LayoutDescription");
                if (bool)
                {
                  i = 0;
                  break label269;
                }
              }
            }
            else
            {
              bool = ((String)localObject3).equals("State");
              if (bool)
              {
                i = 2;
                break label269;
              }
            }
            i = -1;
            label269:
            localObject2 = localObject1;
            switch (i)
            {
            default: 
              break;
            case 3: 
              localObject3 = new Variant(paramContext, paramXmlPullParser);
              localObject2 = localObject1;
              if (localObject1 == null) {
                break label444;
              }
              ((State)localObject1).add((Variant)localObject3);
              localObject2 = localObject1;
              break;
            case 2: 
              localObject2 = new State(paramContext, paramXmlPullParser);
              localObject1 = mStateList;
              i = originalHeight;
              ((SparseArray)localObject1).put(i, localObject2);
              break label444;
              localObject2 = new StringBuilder();
              ((StringBuilder)localObject2).append("unknown tag ");
              ((StringBuilder)localObject2).append((String)localObject3);
              Log.v("ConstraintLayoutStates", ((StringBuilder)localObject2).toString());
              localObject2 = localObject1;
            }
            break;
          }
        }
        else
        {
          paramXmlPullParser.getName();
          localObject2 = localObject1;
        }
        label444:
        i = paramXmlPullParser.next();
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
  
  public boolean needsToChange(int paramInt, float paramFloat1, float paramFloat2)
  {
    if (mCurrentStateId != paramInt) {
      return true;
    }
    if (paramInt == -1) {}
    for (Object localObject = mStateList.valueAt(0);; localObject = mStateList.get(mCurrentStateId))
    {
      localObject = (State)localObject;
      break;
    }
    if ((mCurrentConstraintNumber != -1) && (((Variant)mVariants.get(mCurrentConstraintNumber)).match(paramFloat1, paramFloat2))) {
      return false;
    }
    return mCurrentConstraintNumber != ((State)localObject).findMatch(paramFloat1, paramFloat2);
  }
  
  public void setOnConstraintsChanged(ConstraintsChangedListener paramConstraintsChangedListener)
  {
    mConstraintsChangedListener = paramConstraintsChangedListener;
  }
  
  public int stateGetConstraintID(int paramInt1, int paramInt2, int paramInt3)
  {
    return updateConstraints(-1, paramInt1, paramInt2, paramInt3);
  }
  
  public int updateConstraints(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2)
  {
    if (paramInt1 == paramInt2)
    {
      if (paramInt2 == -1) {
        localState = (State)mStateList.valueAt(0);
      } else {
        localState = (State)mStateList.get(mCurrentStateId);
      }
      if (localState == null) {
        return -1;
      }
      if ((mCurrentConstraintNumber != -1) && (((Variant)mVariants.get(paramInt1)).match(paramFloat1, paramFloat2))) {
        return paramInt1;
      }
      paramInt2 = localState.findMatch(paramFloat1, paramFloat2);
      if (paramInt1 == paramInt2) {
        return paramInt1;
      }
      if (paramInt2 == -1) {
        return mConstraintID;
      }
      return mVariants.get(paramInt2)).mConstraintID;
    }
    State localState = (State)mStateList.get(paramInt2);
    if (localState == null) {
      return -1;
    }
    paramInt1 = localState.findMatch(paramFloat1, paramFloat2);
    if (paramInt1 == -1) {
      return mConstraintID;
    }
    return mVariants.get(paramInt1)).mConstraintID;
  }
  
  static class State
  {
    int mConstraintID = -1;
    boolean mIsLayout;
    ArrayList<StateSet.Variant> mVariants = new ArrayList();
    int originalHeight;
    
    public State(Context paramContext, XmlPullParser paramXmlPullParser)
    {
      int i = 0;
      mIsLayout = false;
      paramXmlPullParser = paramContext.obtainStyledAttributes(Xml.asAttributeSet(paramXmlPullParser), R.styleable.State);
      int j = paramXmlPullParser.getIndexCount();
      while (i < j)
      {
        int k = paramXmlPullParser.getIndex(i);
        if (k == R.styleable.State_android_id)
        {
          originalHeight = paramXmlPullParser.getResourceId(k, originalHeight);
        }
        else if (k == R.styleable.State_constraints)
        {
          mConstraintID = paramXmlPullParser.getResourceId(k, mConstraintID);
          String str = paramContext.getResources().getResourceTypeName(mConstraintID);
          paramContext.getResources().getResourceName(mConstraintID);
          if ("layout".equals(str)) {
            mIsLayout = true;
          }
        }
        i += 1;
      }
      paramXmlPullParser.recycle();
    }
    
    void add(StateSet.Variant paramVariant)
    {
      mVariants.add(paramVariant);
    }
    
    public int findMatch(float paramFloat1, float paramFloat2)
    {
      int i = 0;
      while (i < mVariants.size())
      {
        if (((StateSet.Variant)mVariants.get(i)).match(paramFloat1, paramFloat2)) {
          return i;
        }
        i += 1;
      }
      return -1;
    }
  }
  
  static class Variant
  {
    int TYPE_DIALOG;
    int mConstraintID = -1;
    boolean mIsLayout;
    float mMaxHeight = NaN.0F;
    float mMaxWidth = NaN.0F;
    float mMinHeight = NaN.0F;
    float mMinWidth = NaN.0F;
    
    public Variant(Context paramContext, XmlPullParser paramXmlPullParser)
    {
      int i = 0;
      mIsLayout = false;
      paramXmlPullParser = paramContext.obtainStyledAttributes(Xml.asAttributeSet(paramXmlPullParser), R.styleable.Variant);
      int j = paramXmlPullParser.getIndexCount();
      while (i < j)
      {
        int k = paramXmlPullParser.getIndex(i);
        if (k == R.styleable.Variant_constraints)
        {
          mConstraintID = paramXmlPullParser.getResourceId(k, mConstraintID);
          String str = paramContext.getResources().getResourceTypeName(mConstraintID);
          paramContext.getResources().getResourceName(mConstraintID);
          if ("layout".equals(str)) {
            mIsLayout = true;
          }
        }
        else if (k == R.styleable.Variant_region_heightLessThan)
        {
          mMaxHeight = paramXmlPullParser.getDimension(k, mMaxHeight);
        }
        else if (k == R.styleable.Variant_region_heightMoreThan)
        {
          mMinHeight = paramXmlPullParser.getDimension(k, mMinHeight);
        }
        else if (k == R.styleable.Variant_region_widthLessThan)
        {
          mMaxWidth = paramXmlPullParser.getDimension(k, mMaxWidth);
        }
        else if (k == R.styleable.Variant_region_widthMoreThan)
        {
          mMinWidth = paramXmlPullParser.getDimension(k, mMinWidth);
        }
        else
        {
          Log.v("ConstraintLayoutStates", "Unknown tag");
        }
        i += 1;
      }
      paramXmlPullParser.recycle();
    }
    
    boolean match(float paramFloat1, float paramFloat2)
    {
      if ((!Float.isNaN(mMinWidth)) && (paramFloat1 < mMinWidth)) {
        return false;
      }
      if ((!Float.isNaN(mMinHeight)) && (paramFloat2 < mMinHeight)) {
        return false;
      }
      if ((!Float.isNaN(mMaxWidth)) && (paramFloat1 > mMaxWidth)) {
        return false;
      }
      return (Float.isNaN(mMaxHeight)) || (paramFloat2 <= mMaxHeight);
    }
  }
}
