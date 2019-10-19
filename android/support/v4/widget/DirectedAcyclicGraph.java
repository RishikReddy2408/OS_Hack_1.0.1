package android.support.v4.widget;

import android.support.annotation.RestrictTo;
import android.support.v4.util.Pools.Pool;
import android.support.v4.util.Pools.SimplePool;
import android.support.v4.util.SimpleArrayMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
public final class DirectedAcyclicGraph<T>
{
  private final SimpleArrayMap<T, ArrayList<T>> mGraph = new SimpleArrayMap();
  private final Pools.Pool<ArrayList<T>> mListPool = new Pools.SimplePool(10);
  private final ArrayList<T> mSortResult = new ArrayList();
  private final HashSet<T> mSortTmpMarked = new HashSet();
  
  public DirectedAcyclicGraph() {}
  
  private ArrayList getEmptyList()
  {
    ArrayList localArrayList2 = (ArrayList)mListPool.acquire();
    ArrayList localArrayList1 = localArrayList2;
    if (localArrayList2 == null) {
      localArrayList1 = new ArrayList();
    }
    return localArrayList1;
  }
  
  private void poolList(ArrayList paramArrayList)
  {
    paramArrayList.clear();
    mListPool.release(paramArrayList);
  }
  
  private void put(Object paramObject, ArrayList paramArrayList, HashSet paramHashSet)
  {
    if (paramArrayList.contains(paramObject)) {
      return;
    }
    if (!paramHashSet.contains(paramObject))
    {
      paramHashSet.add(paramObject);
      ArrayList localArrayList = (ArrayList)mGraph.get(paramObject);
      if (localArrayList != null)
      {
        int i = 0;
        int j = localArrayList.size();
        while (i < j)
        {
          put(localArrayList.get(i), paramArrayList, paramHashSet);
          i += 1;
        }
      }
      paramHashSet.remove(paramObject);
      paramArrayList.add(paramObject);
      return;
    }
    throw new RuntimeException("This graph contains cyclic dependencies");
  }
  
  public void addEdge(Object paramObject1, Object paramObject2)
  {
    if ((mGraph.containsKey(paramObject1)) && (mGraph.containsKey(paramObject2)))
    {
      ArrayList localArrayList2 = (ArrayList)mGraph.get(paramObject1);
      ArrayList localArrayList1 = localArrayList2;
      if (localArrayList2 == null)
      {
        localArrayList2 = getEmptyList();
        localArrayList1 = localArrayList2;
        mGraph.put(paramObject1, localArrayList2);
      }
      localArrayList1.add(paramObject2);
      return;
    }
    throw new IllegalArgumentException("All nodes must be present in the graph before being added as an edge");
  }
  
  public void addNode(Object paramObject)
  {
    if (!mGraph.containsKey(paramObject)) {
      mGraph.put(paramObject, null);
    }
  }
  
  public void clear()
  {
    int j = mGraph.size();
    int i = 0;
    while (i < j)
    {
      ArrayList localArrayList = (ArrayList)mGraph.valueAt(i);
      if (localArrayList != null) {
        poolList(localArrayList);
      }
      i += 1;
    }
    mGraph.clear();
  }
  
  public boolean contains(Object paramObject)
  {
    return mGraph.containsKey(paramObject);
  }
  
  public List getIncomingEdges(Object paramObject)
  {
    return (List)mGraph.get(paramObject);
  }
  
  public List getOutgoingEdges(Object paramObject)
  {
    int j = mGraph.size();
    Object localObject1 = null;
    int i = 0;
    while (i < j)
    {
      ArrayList localArrayList = (ArrayList)mGraph.valueAt(i);
      Object localObject2 = localObject1;
      if (localArrayList != null)
      {
        localObject2 = localObject1;
        if (localArrayList.contains(paramObject))
        {
          localObject2 = localObject1;
          if (localObject1 == null) {
            localObject2 = new ArrayList();
          }
          ((ArrayList)localObject2).add(mGraph.keyAt(i));
        }
      }
      i += 1;
      localObject1 = localObject2;
    }
    return localObject1;
  }
  
  public ArrayList getSortedList()
  {
    mSortResult.clear();
    mSortTmpMarked.clear();
    int j = mGraph.size();
    int i = 0;
    while (i < j)
    {
      put(mGraph.keyAt(i), mSortResult, mSortTmpMarked);
      i += 1;
    }
    return mSortResult;
  }
  
  public boolean hasOutgoingEdges(Object paramObject)
  {
    int j = mGraph.size();
    int i = 0;
    while (i < j)
    {
      ArrayList localArrayList = (ArrayList)mGraph.valueAt(i);
      if ((localArrayList != null) && (localArrayList.contains(paramObject))) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  int size()
  {
    return mGraph.size();
  }
}
