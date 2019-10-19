package android.support.constraint;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.Log;
import android.util.SparseArray;
import android.util.Xml;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ConstraintLayoutStates
{
  private static final boolean DEBUG = false;
  public static final String PAGE_KEY = "ConstraintLayoutStates";
  private final ConstraintLayout mConstraintLayout;
  private SparseArray<ConstraintSet> mConstraintSetMap = new SparseArray();
  private ConstraintsChangedListener mConstraintsChangedListener = null;
  int mCurrentConstraintNumber = -1;
  int mCurrentStateId = -1;
  ConstraintSet mDefaultConstraintSet;
  private SparseArray<State> mStateList = new SparseArray();
  
  ConstraintLayoutStates(Context paramContext, ConstraintLayout paramConstraintLayout, int paramInt)
  {
    mConstraintLayout = paramConstraintLayout;
    load(paramContext, paramInt);
  }
  
  private void load(Context paramContext, int paramInt)
  {
    XmlResourceParser localXmlResourceParser = paramContext.getResources().getXml(paramInt);
    Object localObject1 = null;
    try
    {
      paramInt = localXmlResourceParser.getEventType();
      for (;;)
      {
        int i = 1;
        if (paramInt == 1) {
          break;
        }
        Object localObject2;
        if (paramInt != 0) {
          localObject2 = localObject1;
        }
        switch (paramInt)
        {
        default: 
          localObject2 = localObject1;
          break;
        case 2: 
          Object localObject3 = localXmlResourceParser.getName();
          paramInt = ((String)localObject3).hashCode();
          boolean bool;
          switch (paramInt)
          {
          default: 
            break;
          case 1901439077: 
            bool = ((String)localObject3).equals("Variant");
            if (bool) {
              paramInt = 3;
            }
            break;
          case 1657696882: 
            bool = ((String)localObject3).equals("layoutDescription");
            if (bool) {
              paramInt = 0;
            }
            break;
          case 1382829617: 
            bool = ((String)localObject3).equals("StateSet");
            if (bool) {
              paramInt = i;
            }
            break;
          case 80204913: 
            bool = ((String)localObject3).equals("State");
            if (bool) {
              paramInt = 2;
            }
            break;
          case -1349929691: 
            bool = ((String)localObject3).equals("ConstraintSet");
            if (bool) {
              paramInt = 4;
            }
            break;
          }
          paramInt = -1;
          localObject2 = localObject1;
          switch (paramInt)
          {
          default: 
            break;
          case 4: 
            parseConstraintSet(paramContext, localXmlResourceParser);
            localObject2 = localObject1;
            break;
          case 3: 
            localObject3 = new Variant(paramContext, localXmlResourceParser);
            localObject2 = localObject1;
            if (localObject1 != null)
            {
              ((State)localObject1).add((Variant)localObject3);
              localObject2 = localObject1;
            }
            break;
          case 2: 
            localObject2 = new State(paramContext, localXmlResourceParser);
            localObject1 = mStateList;
            paramInt = originalHeight;
            ((SparseArray)localObject1).put(paramInt, localObject2);
            break label445;
            localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append("unknown tag ");
            ((StringBuilder)localObject2).append((String)localObject3);
            Log.v("ConstraintLayoutStates", ((StringBuilder)localObject2).toString());
            localObject2 = localObject1;
            break label445;
            localXmlResourceParser.getName();
            localObject2 = localObject1;
          }
        }
        label445:
        paramInt = localXmlResourceParser.next();
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
      if ("mId".equals(paramXmlPullParser.getAttributeName(i)))
      {
        String str1 = paramXmlPullParser.getAttributeValue(i);
        if (str1.contains("/"))
        {
          String str2 = str1.substring(str1.indexOf('/') + 1);
          i = paramContext.getResources().getIdentifier(str2, "mId", null);
        }
        else
        {
          i = -1;
        }
        j = i;
        if (i == -1) {
          if ((str1 != null) && (str1.length() > 1))
          {
            j = Integer.parseInt(str1.substring(1));
          }
          else
          {
            Log.e("ConstraintLayoutStates", "error in parsing mId");
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
  
  public void updateConstraints(int paramInt, float paramFloat1, float paramFloat2)
  {
    Object localObject1;
    int i;
    Object localObject2;
    if (mCurrentStateId == paramInt)
    {
      if (paramInt == -1) {
        localObject1 = (State)mStateList.valueAt(0);
      } else {
        localObject1 = (State)mStateList.get(mCurrentStateId);
      }
      if ((mCurrentConstraintNumber != -1) && (((Variant)mVariants.get(mCurrentConstraintNumber)).match(paramFloat1, paramFloat2))) {
        return;
      }
      i = ((State)localObject1).findMatch(paramFloat1, paramFloat2);
      if (mCurrentConstraintNumber == i) {
        return;
      }
      if (i == -1) {
        localObject2 = mDefaultConstraintSet;
      } else {
        localObject2 = mVariants.get(i)).mConstraintSet;
      }
      if (i == -1) {
        paramInt = mConstraintID;
      } else {
        paramInt = mVariants.get(i)).mConstraintID;
      }
      if (localObject2 == null) {
        return;
      }
      mCurrentConstraintNumber = i;
      if (mConstraintsChangedListener != null) {
        mConstraintsChangedListener.preLayoutChange(-1, paramInt);
      }
      ((ConstraintSet)localObject2).applyTo(mConstraintLayout);
      if (mConstraintsChangedListener != null) {
        mConstraintsChangedListener.postLayoutChange(-1, paramInt);
      }
    }
    else
    {
      mCurrentStateId = paramInt;
      localObject2 = (State)mStateList.get(mCurrentStateId);
      int j = ((State)localObject2).findMatch(paramFloat1, paramFloat2);
      if (j == -1) {
        localObject1 = mConstraintSet;
      } else {
        localObject1 = mVariants.get(j)).mConstraintSet;
      }
      if (j == -1) {
        i = mConstraintID;
      } else {
        i = mVariants.get(j)).mConstraintID;
      }
      if (localObject1 == null)
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("NO Constraint set found ! id=");
        ((StringBuilder)localObject1).append(paramInt);
        ((StringBuilder)localObject1).append(", dim =");
        ((StringBuilder)localObject1).append(paramFloat1);
        ((StringBuilder)localObject1).append(", ");
        ((StringBuilder)localObject1).append(paramFloat2);
        Log.v("ConstraintLayoutStates", ((StringBuilder)localObject1).toString());
        return;
      }
      mCurrentConstraintNumber = j;
      if (mConstraintsChangedListener != null) {
        mConstraintsChangedListener.preLayoutChange(paramInt, i);
      }
      ((ConstraintSet)localObject1).applyTo(mConstraintLayout);
      if (mConstraintsChangedListener != null) {
        mConstraintsChangedListener.postLayoutChange(paramInt, i);
      }
    }
  }
  
  static class State
  {
    int mConstraintID = -1;
    ConstraintSet mConstraintSet;
    ArrayList<ConstraintLayoutStates.Variant> mVariants = new ArrayList();
    int originalHeight;
    
    public State(Context paramContext, XmlPullParser paramXmlPullParser)
    {
      paramXmlPullParser = paramContext.obtainStyledAttributes(Xml.asAttributeSet(paramXmlPullParser), R.styleable.State);
      int j = paramXmlPullParser.getIndexCount();
      int i = 0;
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
          if ("layout".equals(str))
          {
            mConstraintSet = new ConstraintSet();
            mConstraintSet.clone(paramContext, mConstraintID);
          }
        }
        i += 1;
      }
      paramXmlPullParser.recycle();
    }
    
    void add(ConstraintLayoutStates.Variant paramVariant)
    {
      mVariants.add(paramVariant);
    }
    
    public int findMatch(float paramFloat1, float paramFloat2)
    {
      int i = 0;
      while (i < mVariants.size())
      {
        if (((ConstraintLayoutStates.Variant)mVariants.get(i)).match(paramFloat1, paramFloat2)) {
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
    ConstraintSet mConstraintSet;
    float mMaxHeight = NaN.0F;
    float mMaxWidth = NaN.0F;
    float mMinHeight = NaN.0F;
    float mMinWidth = NaN.0F;
    
    public Variant(Context paramContext, XmlPullParser paramXmlPullParser)
    {
      paramXmlPullParser = paramContext.obtainStyledAttributes(Xml.asAttributeSet(paramXmlPullParser), R.styleable.Variant);
      int j = paramXmlPullParser.getIndexCount();
      int i = 0;
      while (i < j)
      {
        int k = paramXmlPullParser.getIndex(i);
        if (k == R.styleable.Variant_constraints)
        {
          mConstraintID = paramXmlPullParser.getResourceId(k, mConstraintID);
          String str = paramContext.getResources().getResourceTypeName(mConstraintID);
          paramContext.getResources().getResourceName(mConstraintID);
          if ("layout".equals(str))
          {
            mConstraintSet = new ConstraintSet();
            mConstraintSet.clone(paramContext, mConstraintID);
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
