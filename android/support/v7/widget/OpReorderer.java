package android.support.v7.widget;

import java.util.List;

class OpReorderer
{
  final Callback mCallback;
  
  OpReorderer(Callback paramCallback)
  {
    mCallback = paramCallback;
  }
  
  private int getLastMoveOutOfOrder(List paramList)
  {
    int i = paramList.size() - 1;
    int k;
    for (int j = 0; i >= 0; j = k)
    {
      if (getcmd == 8)
      {
        k = j;
        if (j != 0) {
          return i;
        }
      }
      else
      {
        k = 1;
      }
      i -= 1;
    }
    return -1;
  }
  
  private void swapMoveAdd(List paramList, int paramInt1, AdapterHelper.UpdateOp paramUpdateOp1, int paramInt2, AdapterHelper.UpdateOp paramUpdateOp2)
  {
    int i;
    if (itemCount < positionStart) {
      i = -1;
    } else {
      i = 0;
    }
    int j = i;
    if (positionStart < positionStart) {
      j = i + 1;
    }
    if (positionStart <= positionStart) {
      positionStart += itemCount;
    }
    if (positionStart <= itemCount) {
      itemCount += itemCount;
    }
    positionStart += j;
    paramList.set(paramInt1, paramUpdateOp2);
    paramList.set(paramInt2, paramUpdateOp1);
  }
  
  private void swapMoveOp(List paramList, int paramInt1, int paramInt2)
  {
    AdapterHelper.UpdateOp localUpdateOp1 = (AdapterHelper.UpdateOp)paramList.get(paramInt1);
    AdapterHelper.UpdateOp localUpdateOp2 = (AdapterHelper.UpdateOp)paramList.get(paramInt2);
    int i = cmd;
    if (i != 4)
    {
      switch (i)
      {
      default: 
        return;
      case 2: 
        swapMoveRemove(paramList, paramInt1, localUpdateOp1, paramInt2, localUpdateOp2);
        return;
      }
      swapMoveAdd(paramList, paramInt1, localUpdateOp1, paramInt2, localUpdateOp2);
      return;
    }
    swapMoveUpdate(paramList, paramInt1, localUpdateOp1, paramInt2, localUpdateOp2);
  }
  
  void reorderOps(List paramList)
  {
    for (;;)
    {
      int i = getLastMoveOutOfOrder(paramList);
      if (i == -1) {
        break;
      }
      swapMoveOp(paramList, i, i + 1);
    }
  }
  
  void swapMoveRemove(List paramList, int paramInt1, AdapterHelper.UpdateOp paramUpdateOp1, int paramInt2, AdapterHelper.UpdateOp paramUpdateOp2)
  {
    int i = positionStart;
    int k = itemCount;
    int j = 0;
    if (i < k) {
      if ((positionStart != positionStart) || (itemCount != itemCount - positionStart)) {}
    }
    for (i = 0;; i = 1)
    {
      j = 1;
      break label106;
      i = 0;
      break label106;
      if ((positionStart != itemCount + 1) || (itemCount != positionStart - itemCount)) {
        break;
      }
    }
    i = 1;
    label106:
    if (itemCount < positionStart)
    {
      positionStart -= 1;
    }
    else if (itemCount < positionStart + itemCount)
    {
      itemCount -= 1;
      cmd = 2;
      itemCount = 1;
      if (itemCount != 0) {
        return;
      }
      paramList.remove(paramInt2);
      mCallback.recycleUpdateOp(paramUpdateOp2);
      return;
    }
    k = positionStart;
    int m = positionStart;
    AdapterHelper.UpdateOp localUpdateOp = null;
    if (k <= m)
    {
      positionStart += 1;
    }
    else if (positionStart < positionStart + itemCount)
    {
      k = positionStart;
      m = itemCount;
      int n = positionStart;
      localUpdateOp = mCallback.obtainUpdateOp(2, positionStart + 1, k + m - n, null);
      itemCount = (positionStart - positionStart);
    }
    if (j != 0)
    {
      paramList.set(paramInt1, paramUpdateOp2);
      paramList.remove(paramInt2);
      mCallback.recycleUpdateOp(paramUpdateOp1);
      return;
    }
    if (i != 0)
    {
      if (localUpdateOp != null)
      {
        if (positionStart > positionStart) {
          positionStart -= itemCount;
        }
        if (itemCount > positionStart) {
          itemCount -= itemCount;
        }
      }
      if (positionStart > positionStart) {
        positionStart -= itemCount;
      }
      if (itemCount > positionStart) {
        itemCount -= itemCount;
      }
    }
    else
    {
      if (localUpdateOp != null)
      {
        if (positionStart >= positionStart) {
          positionStart -= itemCount;
        }
        if (itemCount >= positionStart) {
          itemCount -= itemCount;
        }
      }
      if (positionStart >= positionStart) {
        positionStart -= itemCount;
      }
      if (itemCount >= positionStart) {
        itemCount -= itemCount;
      }
    }
    paramList.set(paramInt1, paramUpdateOp2);
    if (positionStart != itemCount) {
      paramList.set(paramInt2, paramUpdateOp1);
    } else {
      paramList.remove(paramInt2);
    }
    if (localUpdateOp != null) {
      paramList.add(paramInt1, localUpdateOp);
    }
  }
  
  void swapMoveUpdate(List paramList, int paramInt1, AdapterHelper.UpdateOp paramUpdateOp1, int paramInt2, AdapterHelper.UpdateOp paramUpdateOp2)
  {
    int i = itemCount;
    int j = positionStart;
    AdapterHelper.UpdateOp localUpdateOp2 = null;
    if (i < j)
    {
      positionStart -= 1;
    }
    else if (itemCount < positionStart + itemCount)
    {
      itemCount -= 1;
      localUpdateOp1 = mCallback.obtainUpdateOp(4, positionStart, 1, payload);
      break label96;
    }
    AdapterHelper.UpdateOp localUpdateOp1 = null;
    label96:
    if (positionStart <= positionStart)
    {
      positionStart += 1;
    }
    else if (positionStart < positionStart + itemCount)
    {
      i = positionStart + itemCount - positionStart;
      localUpdateOp2 = mCallback.obtainUpdateOp(4, positionStart + 1, i, payload);
      itemCount -= i;
    }
    paramList.set(paramInt2, paramUpdateOp1);
    if (itemCount > 0)
    {
      paramList.set(paramInt1, paramUpdateOp2);
    }
    else
    {
      paramList.remove(paramInt1);
      mCallback.recycleUpdateOp(paramUpdateOp2);
    }
    if (localUpdateOp1 != null) {
      paramList.add(paramInt1, localUpdateOp1);
    }
    if (localUpdateOp2 != null) {
      paramList.add(paramInt1, localUpdateOp2);
    }
  }
  
  static abstract interface Callback
  {
    public abstract AdapterHelper.UpdateOp obtainUpdateOp(int paramInt1, int paramInt2, int paramInt3, Object paramObject);
    
    public abstract void recycleUpdateOp(AdapterHelper.UpdateOp paramUpdateOp);
  }
}
