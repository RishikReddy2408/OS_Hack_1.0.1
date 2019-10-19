package android.support.v7.util;

import android.support.v7.widget.RecyclerView.Adapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class DiffUtil
{
  private static final Comparator<Snake> SNAKE_COMPARATOR = new Comparator()
  {
    public int compare(DiffUtil.Snake paramAnonymousSnake1, DiffUtil.Snake paramAnonymousSnake2)
    {
      int j = filename - filename;
      int i = j;
      if (j == 0) {
        i = pathname - pathname;
      }
      return i;
    }
  };
  
  private DiffUtil() {}
  
  public static DiffResult calculateDiff(Callback paramCallback)
  {
    return calculateDiff(paramCallback, true);
  }
  
  public static DiffResult calculateDiff(Callback paramCallback, boolean paramBoolean)
  {
    int i = paramCallback.getOldListSize();
    int j = paramCallback.getNewListSize();
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    localArrayList2.add(new Range(0, i, 0, j));
    i = Math.abs(i - j) + (i + j);
    j = i * 2;
    int[] arrayOfInt1 = new int[j];
    int[] arrayOfInt2 = new int[j];
    ArrayList localArrayList3 = new ArrayList();
    while (!localArrayList2.isEmpty())
    {
      Range localRange2 = (Range)localArrayList2.remove(localArrayList2.size() - 1);
      Snake localSnake = diffPartial(paramCallback, oldListStart, oldListEnd, newListStart, newListEnd, arrayOfInt1, arrayOfInt2, i);
      if (localSnake != null)
      {
        if (size > 0) {
          localArrayList1.add(localSnake);
        }
        filename += oldListStart;
        pathname += newListStart;
        Range localRange1;
        if (localArrayList3.isEmpty()) {
          localRange1 = new Range();
        } else {
          localRange1 = (Range)localArrayList3.remove(localArrayList3.size() - 1);
        }
        oldListStart = oldListStart;
        newListStart = newListStart;
        if (reverse)
        {
          oldListEnd = filename;
          newListEnd = pathname;
        }
        else if (removal)
        {
          oldListEnd = (filename - 1);
          newListEnd = pathname;
        }
        else
        {
          oldListEnd = filename;
          newListEnd = (pathname - 1);
        }
        localArrayList2.add(localRange1);
        if (reverse)
        {
          if (removal)
          {
            oldListStart = (filename + size + 1);
            newListStart = (pathname + size);
          }
          else
          {
            oldListStart = (filename + size);
            newListStart = (pathname + size + 1);
          }
        }
        else
        {
          oldListStart = (filename + size);
          newListStart = (pathname + size);
        }
        localArrayList2.add(localRange2);
      }
      else
      {
        localArrayList3.add(localRange2);
      }
    }
    Collections.sort(localArrayList1, SNAKE_COMPARATOR);
    return new DiffResult(paramCallback, localArrayList1, arrayOfInt1, arrayOfInt2, paramBoolean);
  }
  
  private static Snake diffPartial(Callback paramCallback, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt5)
  {
    int m = paramInt2 - paramInt1;
    int k = paramInt4 - paramInt3;
    if ((m >= 1) && (k >= 1))
    {
      int i2 = m - k;
      int i3 = (m + k + 1) / 2;
      paramInt2 = paramInt5 - i3 - 1;
      paramInt4 = paramInt5 + i3 + 1;
      Arrays.fill(paramArrayOfInt1, paramInt2, paramInt4, 0);
      Arrays.fill(paramArrayOfInt2, paramInt2 + i2, paramInt4 + i2, m);
      if (i2 % 2 != 0) {
        paramInt4 = 1;
      } else {
        paramInt4 = 0;
      }
      int i = 0;
      while (i <= i3)
      {
        int j = -i;
        int n = j;
        boolean bool;
        label199:
        int i1;
        while (n <= i)
        {
          if (n != j) {
            if (n != i)
            {
              paramInt2 = paramInt5 + n;
              if (paramArrayOfInt1[(paramInt2 - 1)] < paramArrayOfInt1[(paramInt2 + 1)]) {}
            }
            else
            {
              paramInt2 = paramArrayOfInt1[(paramInt5 + n - 1)] + 1;
              bool = true;
              break label199;
            }
          }
          paramInt2 = paramArrayOfInt1[(paramInt5 + n + 1)];
          bool = false;
          i1 = paramInt2 - n;
          while ((paramInt2 < m) && (i1 < k) && (paramCallback.areItemsTheSame(paramInt1 + paramInt2, paramInt3 + i1)))
          {
            paramInt2 += 1;
            i1 += 1;
          }
          i1 = paramInt5 + n;
          paramArrayOfInt1[i1] = paramInt2;
          if ((paramInt4 != 0) && (n >= i2 - i + 1) && (n <= i2 + i - 1) && (paramArrayOfInt1[i1] >= paramArrayOfInt2[i1]))
          {
            paramCallback = new Snake();
            filename = paramArrayOfInt2[i1];
            pathname = (filename - n);
            size = (paramArrayOfInt1[i1] - paramArrayOfInt2[i1]);
            removal = bool;
            reverse = false;
            return paramCallback;
          }
          n += 2;
        }
        n = j;
        while (n <= i)
        {
          int i4 = n + i2;
          if (i4 != i + i2) {
            if (i4 != j + i2)
            {
              paramInt2 = paramInt5 + i4;
              if (paramArrayOfInt2[(paramInt2 - 1)] < paramArrayOfInt2[(paramInt2 + 1)]) {}
            }
            else
            {
              paramInt2 = paramArrayOfInt2[(paramInt5 + i4 + 1)] - 1;
              bool = true;
              break label460;
            }
          }
          paramInt2 = paramArrayOfInt2[(paramInt5 + i4 - 1)];
          bool = false;
          label460:
          i1 = paramInt2 - i4;
          while ((paramInt2 > 0) && (i1 > 0) && (paramCallback.areItemsTheSame(paramInt1 + paramInt2 - 1, paramInt3 + i1 - 1)))
          {
            paramInt2 -= 1;
            i1 -= 1;
          }
          i1 = paramInt5 + i4;
          paramArrayOfInt2[i1] = paramInt2;
          if ((paramInt4 == 0) && (i4 >= j) && (i4 <= i) && (paramArrayOfInt1[i1] >= paramArrayOfInt2[i1]))
          {
            paramCallback = new Snake();
            filename = paramArrayOfInt2[i1];
            pathname = (filename - i4);
            size = (paramArrayOfInt1[i1] - paramArrayOfInt2[i1]);
            removal = bool;
            reverse = true;
            return paramCallback;
          }
          n += 2;
        }
        i += 1;
      }
      throw new IllegalStateException("DiffUtil hit an unexpected case while trying to calculate the optimal path. Please make sure your data is not changing during the diff calculation.");
    }
    return null;
  }
  
  public static abstract class Callback
  {
    public Callback() {}
    
    public abstract boolean areContentsTheSame(int paramInt1, int paramInt2);
    
    public abstract boolean areItemsTheSame(int paramInt1, int paramInt2);
    
    public Object getChangePayload(int paramInt1, int paramInt2)
    {
      return null;
    }
    
    public abstract int getNewListSize();
    
    public abstract int getOldListSize();
  }
  
  public static class DiffResult
  {
    private static final int FLAG_CHANGED = 2;
    private static final int FLAG_IGNORE = 16;
    private static final int FLAG_MASK = 31;
    private static final int FLAG_MOVED_CHANGED = 4;
    private static final int FLAG_MOVED_NOT_CHANGED = 8;
    private static final int FLAG_NOT_CHANGED = 1;
    private static final int FLAG_OFFSET = 5;
    private final DiffUtil.Callback mCallback;
    private final boolean mDetectMoves;
    private final int[] mNewItemStatuses;
    private final int mNewListSize;
    private final int[] mOldItemStatuses;
    private final int mOldListSize;
    private final List<DiffUtil.Snake> mSnakes;
    
    DiffResult(DiffUtil.Callback paramCallback, List paramList, int[] paramArrayOfInt1, int[] paramArrayOfInt2, boolean paramBoolean)
    {
      mSnakes = paramList;
      mOldItemStatuses = paramArrayOfInt1;
      mNewItemStatuses = paramArrayOfInt2;
      Arrays.fill(mOldItemStatuses, 0);
      Arrays.fill(mNewItemStatuses, 0);
      mCallback = paramCallback;
      mOldListSize = paramCallback.getOldListSize();
      mNewListSize = paramCallback.getNewListSize();
      mDetectMoves = paramBoolean;
      addRootSnake();
      findMatchingItems();
    }
    
    private void addRootSnake()
    {
      DiffUtil.Snake localSnake;
      if (mSnakes.isEmpty()) {
        localSnake = null;
      } else {
        localSnake = (DiffUtil.Snake)mSnakes.get(0);
      }
      if ((localSnake == null) || (filename != 0) || (pathname != 0))
      {
        localSnake = new DiffUtil.Snake();
        filename = 0;
        pathname = 0;
        removal = false;
        size = 0;
        reverse = false;
        mSnakes.add(0, localSnake);
      }
    }
    
    private void dispatchAdditions(List paramList, ListUpdateCallback paramListUpdateCallback, int paramInt1, int paramInt2, int paramInt3)
    {
      if (!mDetectMoves)
      {
        paramListUpdateCallback.onInserted(paramInt1, paramInt2);
        return;
      }
      paramInt2 -= 1;
      while (paramInt2 >= 0)
      {
        Object localObject = mNewItemStatuses;
        int i = paramInt3 + paramInt2;
        int j = localObject[i] & 0x1F;
        if (j != 0)
        {
          if ((j != 4) && (j != 8))
          {
            if (j == 16)
            {
              paramList.add(new DiffUtil.PostponedUpdate(i, paramInt1, false));
            }
            else
            {
              paramList = new StringBuilder();
              paramList.append("unknown flag for pos ");
              paramList.append(i);
              paramList.append(" ");
              paramList.append(Long.toBinaryString(j));
              throw new IllegalStateException(paramList.toString());
            }
          }
          else
          {
            int k = mNewItemStatuses[i] >> 5;
            paramListUpdateCallback.onMoved(removePostponedUpdatecurrentPos, paramInt1);
            if (j == 4) {
              paramListUpdateCallback.onChanged(paramInt1, 1, mCallback.getChangePayload(k, i));
            }
          }
        }
        else
        {
          paramListUpdateCallback.onInserted(paramInt1, 1);
          localObject = paramList.iterator();
          while (((Iterator)localObject).hasNext())
          {
            DiffUtil.PostponedUpdate localPostponedUpdate = (DiffUtil.PostponedUpdate)((Iterator)localObject).next();
            currentPos += 1;
          }
        }
        paramInt2 -= 1;
      }
    }
    
    private void dispatchRemovals(List paramList, ListUpdateCallback paramListUpdateCallback, int paramInt1, int paramInt2, int paramInt3)
    {
      if (!mDetectMoves)
      {
        paramListUpdateCallback.onRemoved(paramInt1, paramInt2);
        return;
      }
      paramInt2 -= 1;
      while (paramInt2 >= 0)
      {
        Object localObject = mOldItemStatuses;
        int i = paramInt3 + paramInt2;
        int j = localObject[i] & 0x1F;
        if (j != 0)
        {
          if ((j != 4) && (j != 8))
          {
            if (j == 16)
            {
              paramList.add(new DiffUtil.PostponedUpdate(i, paramInt1 + paramInt2, true));
            }
            else
            {
              paramList = new StringBuilder();
              paramList.append("unknown flag for pos ");
              paramList.append(i);
              paramList.append(" ");
              paramList.append(Long.toBinaryString(j));
              throw new IllegalStateException(paramList.toString());
            }
          }
          else
          {
            int k = mOldItemStatuses[i] >> 5;
            localObject = removePostponedUpdate(paramList, k, false);
            paramListUpdateCallback.onMoved(paramInt1 + paramInt2, currentPos - 1);
            if (j == 4) {
              paramListUpdateCallback.onChanged(currentPos - 1, 1, mCallback.getChangePayload(i, k));
            }
          }
        }
        else
        {
          paramListUpdateCallback.onRemoved(paramInt1 + paramInt2, 1);
          localObject = paramList.iterator();
          while (((Iterator)localObject).hasNext())
          {
            DiffUtil.PostponedUpdate localPostponedUpdate = (DiffUtil.PostponedUpdate)((Iterator)localObject).next();
            currentPos -= 1;
          }
        }
        paramInt2 -= 1;
      }
    }
    
    private void findAddition(int paramInt1, int paramInt2, int paramInt3)
    {
      if (mOldItemStatuses[(paramInt1 - 1)] != 0) {
        return;
      }
      findMatchingItem(paramInt1, paramInt2, paramInt3, false);
    }
    
    private boolean findMatchingItem(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
    {
      int i;
      int j;
      int k;
      if (paramBoolean)
      {
        i = paramInt2 - 1;
        paramInt2 = paramInt1;
        j = i;
      }
      else
      {
        k = paramInt1 - 1;
        j = k;
        i = paramInt2;
        paramInt2 = k;
      }
      while (paramInt3 >= 0)
      {
        Object localObject = (DiffUtil.Snake)mSnakes.get(paramInt3);
        int m = filename;
        int n = size;
        int i1 = pathname;
        int i2 = size;
        k = 4;
        if (paramBoolean)
        {
          paramInt2 -= 1;
          while (paramInt2 >= m + n)
          {
            if (mCallback.areItemsTheSame(paramInt2, j))
            {
              if (mCallback.areContentsTheSame(paramInt2, j)) {
                k = 8;
              }
              mNewItemStatuses[j] = (paramInt2 << 5 | 0x10);
              mOldItemStatuses[paramInt2] = (j << 5 | k);
              return true;
            }
            paramInt2 -= 1;
          }
        }
        paramInt2 = i - 1;
        while (paramInt2 >= i1 + i2)
        {
          if (mCallback.areItemsTheSame(j, paramInt2))
          {
            if (mCallback.areContentsTheSame(j, paramInt2)) {
              k = 8;
            }
            localObject = mOldItemStatuses;
            paramInt1 -= 1;
            localObject[paramInt1] = (paramInt2 << 5 | 0x10);
            mNewItemStatuses[paramInt2] = (paramInt1 << 5 | k);
            return true;
          }
          paramInt2 -= 1;
        }
        paramInt2 = filename;
        i = pathname;
        paramInt3 -= 1;
      }
      return false;
    }
    
    private void findMatchingItems()
    {
      int j = mOldListSize;
      int i = mNewListSize;
      int k = mSnakes.size() - 1;
      while (k >= 0)
      {
        DiffUtil.Snake localSnake = (DiffUtil.Snake)mSnakes.get(k);
        int i2 = filename;
        int i3 = size;
        int n = pathname;
        int i1 = size;
        int m;
        if (mDetectMoves)
        {
          for (;;)
          {
            m = i;
            if (j <= i2 + i3) {
              break;
            }
            findAddition(j, i, k);
            j -= 1;
          }
          while (m > n + i1)
          {
            findRemoval(j, m, k);
            m -= 1;
          }
        }
        i = 0;
        while (i < size)
        {
          m = filename + i;
          n = pathname + i;
          if (mCallback.areContentsTheSame(m, n)) {
            j = 1;
          } else {
            j = 2;
          }
          mOldItemStatuses[m] = (n << 5 | j);
          mNewItemStatuses[n] = (m << 5 | j);
          i += 1;
        }
        j = filename;
        i = pathname;
        k -= 1;
      }
    }
    
    private void findRemoval(int paramInt1, int paramInt2, int paramInt3)
    {
      if (mNewItemStatuses[(paramInt2 - 1)] != 0) {
        return;
      }
      findMatchingItem(paramInt1, paramInt2, paramInt3, true);
    }
    
    private static DiffUtil.PostponedUpdate removePostponedUpdate(List paramList, int paramInt, boolean paramBoolean)
    {
      int i = paramList.size() - 1;
      while (i >= 0)
      {
        DiffUtil.PostponedUpdate localPostponedUpdate1 = (DiffUtil.PostponedUpdate)paramList.get(i);
        if ((posInOwnerList == paramInt) && (removal == paramBoolean))
        {
          paramList.remove(i);
          while (i < paramList.size())
          {
            DiffUtil.PostponedUpdate localPostponedUpdate2 = (DiffUtil.PostponedUpdate)paramList.get(i);
            int j = currentPos;
            if (paramBoolean) {
              paramInt = 1;
            } else {
              paramInt = -1;
            }
            currentPos = (j + paramInt);
            i += 1;
          }
          return localPostponedUpdate1;
        }
        i -= 1;
      }
      return null;
    }
    
    public void dispatchUpdatesTo(ListUpdateCallback paramListUpdateCallback)
    {
      if ((paramListUpdateCallback instanceof BatchingListUpdateCallback)) {
        paramListUpdateCallback = (BatchingListUpdateCallback)paramListUpdateCallback;
      } else {
        paramListUpdateCallback = new BatchingListUpdateCallback(paramListUpdateCallback);
      }
      ArrayList localArrayList = new ArrayList();
      int j = mOldListSize;
      int k = mNewListSize;
      int i = mSnakes.size();
      i -= 1;
      while (i >= 0)
      {
        DiffUtil.Snake localSnake = (DiffUtil.Snake)mSnakes.get(i);
        int m = size;
        int n = filename + m;
        int i1 = pathname + m;
        if (n < j) {
          dispatchRemovals(localArrayList, paramListUpdateCallback, n, j - n, n);
        }
        if (i1 < k) {
          dispatchAdditions(localArrayList, paramListUpdateCallback, n, k - i1, i1);
        }
        j = m - 1;
        while (j >= 0)
        {
          if ((mOldItemStatuses[(filename + j)] & 0x1F) == 2) {
            paramListUpdateCallback.onChanged(filename + j, 1, mCallback.getChangePayload(filename + j, pathname + j));
          }
          j -= 1;
        }
        j = filename;
        k = pathname;
        i -= 1;
      }
      paramListUpdateCallback.dispatchLastEvent();
    }
    
    public void dispatchUpdatesTo(RecyclerView.Adapter paramAdapter)
    {
      dispatchUpdatesTo(new AdapterListUpdateCallback(paramAdapter));
    }
    
    List getSnakes()
    {
      return mSnakes;
    }
  }
  
  public static abstract class ItemCallback<T>
  {
    public ItemCallback() {}
    
    public abstract boolean areContentsTheSame(Object paramObject1, Object paramObject2);
    
    public abstract boolean areItemsTheSame(Object paramObject1, Object paramObject2);
    
    public Object getChangePayload(Object paramObject1, Object paramObject2)
    {
      return null;
    }
  }
  
  private static class PostponedUpdate
  {
    int currentPos;
    int posInOwnerList;
    boolean removal;
    
    public PostponedUpdate(int paramInt1, int paramInt2, boolean paramBoolean)
    {
      posInOwnerList = paramInt1;
      currentPos = paramInt2;
      removal = paramBoolean;
    }
  }
  
  static class Range
  {
    int newListEnd;
    int newListStart;
    int oldListEnd;
    int oldListStart;
    
    public Range() {}
    
    public Range(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      oldListStart = paramInt1;
      oldListEnd = paramInt2;
      newListStart = paramInt3;
      newListEnd = paramInt4;
    }
  }
  
  static class Snake
  {
    int filename;
    int pathname;
    boolean removal;
    boolean reverse;
    int size;
    
    Snake() {}
  }
}
