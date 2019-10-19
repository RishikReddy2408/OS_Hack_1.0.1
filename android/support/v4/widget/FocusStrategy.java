package android.support.v4.widget;

import android.graphics.Rect;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class FocusStrategy
{
  FocusStrategy() {}
  
  private static boolean beamBeats(int paramInt, Rect paramRect1, Rect paramRect2, Rect paramRect3)
  {
    boolean bool = beamsOverlap(paramInt, paramRect1, paramRect2);
    if (!beamsOverlap(paramInt, paramRect1, paramRect3))
    {
      if (!bool) {
        return false;
      }
      if (!isToDirectionOf(paramInt, paramRect1, paramRect3)) {
        return true;
      }
      if (paramInt != 17)
      {
        if (paramInt == 66) {
          return true;
        }
        return majorAxisDistance(paramInt, paramRect1, paramRect2) < majorAxisDistanceToFarEdge(paramInt, paramRect1, paramRect3);
      }
      return true;
    }
    return false;
  }
  
  private static boolean beamsOverlap(int paramInt, Rect paramRect1, Rect paramRect2)
  {
    if (paramInt != 17)
    {
      if (paramInt != 33)
      {
        if (paramInt == 66) {
          break label64;
        }
        if (paramInt != 130) {
          throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
        }
      }
      return (right >= left) && (left <= right);
    }
    label64:
    return (bottom >= top) && (top <= bottom);
  }
  
  public static Object findNextFocusInAbsoluteDirection(Object paramObject1, CollectionAdapter paramCollectionAdapter, BoundsAdapter paramBoundsAdapter, Object paramObject2, Rect paramRect, int paramInt)
  {
    Rect localRect1 = new Rect(paramRect);
    int i = 0;
    if (paramInt != 17)
    {
      if (paramInt != 33)
      {
        if (paramInt != 66)
        {
          if (paramInt == 130) {
            localRect1.offset(0, -(paramRect.height() + 1));
          } else {
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
          }
        }
        else {
          localRect1.offset(-(paramRect.width() + 1), 0);
        }
      }
      else {
        localRect1.offset(0, paramRect.height() + 1);
      }
    }
    else {
      localRect1.offset(paramRect.width() + 1, 0);
    }
    Object localObject1 = null;
    int j = paramCollectionAdapter.size(paramObject1);
    Rect localRect2 = new Rect();
    while (i < j)
    {
      Object localObject2 = paramCollectionAdapter.getView(paramObject1, i);
      if (localObject2 != paramObject2)
      {
        paramBoundsAdapter.obtainBounds(localObject2, localRect2);
        if (isBetterCandidate(paramInt, paramRect, localRect2, localRect1))
        {
          localRect1.set(localRect2);
          localObject1 = localObject2;
        }
      }
      i += 1;
    }
    return localObject1;
  }
  
  public static Object findNextFocusInRelativeDirection(Object paramObject1, CollectionAdapter paramCollectionAdapter, BoundsAdapter paramBoundsAdapter, Object paramObject2, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    int j = paramCollectionAdapter.size(paramObject1);
    ArrayList localArrayList = new ArrayList(j);
    int i = 0;
    while (i < j)
    {
      localArrayList.add(paramCollectionAdapter.getView(paramObject1, i));
      i += 1;
    }
    Collections.sort(localArrayList, new SequentialComparator(paramBoolean1, paramBoundsAdapter));
    switch (paramInt)
    {
    default: 
      throw new IllegalArgumentException("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD}.");
    case 2: 
      return getNextFocusable(paramObject2, localArrayList, paramBoolean2);
    }
    return getPreviousFocusable(paramObject2, localArrayList, paramBoolean2);
  }
  
  private static Object getNextFocusable(Object paramObject, ArrayList paramArrayList, boolean paramBoolean)
  {
    int j = paramArrayList.size();
    int i;
    if (paramObject == null) {
      i = -1;
    } else {
      i = paramArrayList.lastIndexOf(paramObject);
    }
    i += 1;
    if (i < j) {
      return paramArrayList.get(i);
    }
    if ((paramBoolean) && (j > 0)) {
      return paramArrayList.get(0);
    }
    return null;
  }
  
  private static Object getPreviousFocusable(Object paramObject, ArrayList paramArrayList, boolean paramBoolean)
  {
    int j = paramArrayList.size();
    int i;
    if (paramObject == null) {
      i = j;
    } else {
      i = paramArrayList.indexOf(paramObject);
    }
    i -= 1;
    if (i >= 0) {
      return paramArrayList.get(i);
    }
    if ((paramBoolean) && (j > 0)) {
      return paramArrayList.get(j - 1);
    }
    return null;
  }
  
  private static int getWeightedDistanceFor(int paramInt1, int paramInt2)
  {
    return paramInt1 * 13 * paramInt1 + paramInt2 * paramInt2;
  }
  
  private static boolean isBetterCandidate(int paramInt, Rect paramRect1, Rect paramRect2, Rect paramRect3)
  {
    if (!isCandidate(paramRect1, paramRect2, paramInt)) {
      return false;
    }
    if (!isCandidate(paramRect1, paramRect3, paramInt)) {
      return true;
    }
    if (beamBeats(paramInt, paramRect1, paramRect2, paramRect3)) {
      return true;
    }
    if (beamBeats(paramInt, paramRect1, paramRect3, paramRect2)) {
      return false;
    }
    return getWeightedDistanceFor(majorAxisDistance(paramInt, paramRect1, paramRect2), minorAxisDistance(paramInt, paramRect1, paramRect2)) < getWeightedDistanceFor(majorAxisDistance(paramInt, paramRect1, paramRect3), minorAxisDistance(paramInt, paramRect1, paramRect3));
  }
  
  private static boolean isCandidate(Rect paramRect1, Rect paramRect2, int paramInt)
  {
    if (paramInt != 17)
    {
      if (paramInt != 33)
      {
        if (paramInt != 66)
        {
          if (paramInt == 130) {
            return ((top < top) || (bottom <= top)) && (bottom < bottom);
          }
          throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
        }
        return ((left < left) || (right <= left)) && (right < right);
      }
      return ((bottom > bottom) || (top >= bottom)) && (top > top);
    }
    return ((right > right) || (left >= right)) && (left > left);
  }
  
  private static boolean isToDirectionOf(int paramInt, Rect paramRect1, Rect paramRect2)
  {
    if (paramInt != 17)
    {
      if (paramInt != 33)
      {
        if (paramInt != 66)
        {
          if (paramInt == 130)
          {
            if (bottom <= top) {
              return true;
            }
          }
          else {
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
          }
        }
        else if (right <= left) {
          return true;
        }
      }
      else if (top >= bottom) {
        return true;
      }
    }
    else if (left >= right) {
      return true;
    }
    return false;
  }
  
  private static int majorAxisDistance(int paramInt, Rect paramRect1, Rect paramRect2)
  {
    return Math.max(0, majorAxisDistanceRaw(paramInt, paramRect1, paramRect2));
  }
  
  private static int majorAxisDistanceRaw(int paramInt, Rect paramRect1, Rect paramRect2)
  {
    if (paramInt != 17)
    {
      if (paramInt != 33)
      {
        if (paramInt != 66)
        {
          if (paramInt == 130) {
            return top - bottom;
          }
          throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
        }
        return left - right;
      }
      return top - bottom;
    }
    return left - right;
  }
  
  private static int majorAxisDistanceToFarEdge(int paramInt, Rect paramRect1, Rect paramRect2)
  {
    return Math.max(1, majorAxisDistanceToFarEdgeRaw(paramInt, paramRect1, paramRect2));
  }
  
  private static int majorAxisDistanceToFarEdgeRaw(int paramInt, Rect paramRect1, Rect paramRect2)
  {
    if (paramInt != 17)
    {
      if (paramInt != 33)
      {
        if (paramInt != 66)
        {
          if (paramInt == 130) {
            return bottom - bottom;
          }
          throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
        }
        return right - right;
      }
      return top - top;
    }
    return left - left;
  }
  
  private static int minorAxisDistance(int paramInt, Rect paramRect1, Rect paramRect2)
  {
    if (paramInt != 17)
    {
      if (paramInt != 33)
      {
        if (paramInt == 66) {
          break label65;
        }
        if (paramInt != 130) {
          throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
        }
      }
      return Math.abs(left + paramRect1.width() / 2 - (left + paramRect2.width() / 2));
    }
    label65:
    return Math.abs(top + paramRect1.height() / 2 - (top + paramRect2.height() / 2));
  }
  
  public static abstract interface BoundsAdapter<T>
  {
    public abstract void obtainBounds(Object paramObject, Rect paramRect);
  }
  
  public static abstract interface CollectionAdapter<T, V>
  {
    public abstract Object getView(Object paramObject, int paramInt);
    
    public abstract int size(Object paramObject);
  }
  
  private static class SequentialComparator<T>
    implements Comparator<T>
  {
    private final FocusStrategy.BoundsAdapter<T> mAdapter;
    private final boolean mIsLayoutRtl;
    private final Rect mTemp1 = new Rect();
    private final Rect mTemp2 = new Rect();
    
    SequentialComparator(boolean paramBoolean, FocusStrategy.BoundsAdapter paramBoundsAdapter)
    {
      mIsLayoutRtl = paramBoolean;
      mAdapter = paramBoundsAdapter;
    }
    
    public int compare(Object paramObject1, Object paramObject2)
    {
      Rect localRect1 = mTemp1;
      Rect localRect2 = mTemp2;
      mAdapter.obtainBounds(paramObject1, localRect1);
      mAdapter.obtainBounds(paramObject2, localRect2);
      if (top < top) {
        return -1;
      }
      if (top > top) {
        return 1;
      }
      if (left < left)
      {
        if (mIsLayoutRtl) {
          return 1;
        }
      }
      else
      {
        if (left > left)
        {
          if (mIsLayoutRtl) {
            return -1;
          }
          return 1;
        }
        if (bottom < bottom) {
          return -1;
        }
        if (bottom > bottom) {
          return 1;
        }
        if (right < right)
        {
          if (mIsLayoutRtl) {
            return 1;
          }
        }
        else
        {
          if (right > right)
          {
            if (mIsLayoutRtl) {
              return -1;
            }
            return 1;
          }
          return 0;
        }
      }
      return -1;
    }
  }
}
