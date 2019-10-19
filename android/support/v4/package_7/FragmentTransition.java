package android.support.v4.package_7;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.SimpleArrayMap;
import android.support.v4.view.ViewCompat;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class FragmentTransition
{
  private static final int[] INVERSE_OPS = { 0, 3, 0, 1, 5, 4, 7, 6, 9, 8 };
  private static final FragmentTransitionImpl PLATFORM_IMPL;
  private static final FragmentTransitionImpl SUPPORT_IMPL = resolveSupportImpl();
  
  static
  {
    FragmentTransitionCompat21 localFragmentTransitionCompat21;
    if (Build.VERSION.SDK_INT >= 21) {
      localFragmentTransitionCompat21 = new FragmentTransitionCompat21();
    } else {
      localFragmentTransitionCompat21 = null;
    }
    PLATFORM_IMPL = localFragmentTransitionCompat21;
  }
  
  FragmentTransition() {}
  
  private static void addSharedElementsWithMatchingNames(ArrayList paramArrayList, ArrayMap paramArrayMap, Collection paramCollection)
  {
    int i = paramArrayMap.size() - 1;
    while (i >= 0)
    {
      View localView = (View)paramArrayMap.valueAt(i);
      if (paramCollection.contains(ViewCompat.getTransitionName(localView))) {
        paramArrayList.add(localView);
      }
      i -= 1;
    }
  }
  
  private static void addToFirstInLastOut(BackStackRecord paramBackStackRecord, BackStackRecord.Op paramOp, SparseArray paramSparseArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    Fragment localFragment = fragment;
    if (localFragment == null) {
      return;
    }
    int m = mContainerId;
    if (m == 0) {
      return;
    }
    if (paramBoolean1) {
      i = INVERSE_OPS[removed];
    } else {
      i = removed;
    }
    boolean bool2 = false;
    boolean bool1 = false;
    if (i != 1) {
      switch (i)
      {
      default: 
        break;
      }
    }
    int k;
    int j;
    for (int i = 0;; i = 1)
    {
      k = 0;
      j = 0;
      break;
      if (paramBoolean2)
      {
        if ((!mHiddenChanged) || (mHidden) || (!mAdded)) {
          break label347;
        }
      }
      else
      {
        bool1 = mHidden;
        continue;
        if (paramBoolean2)
        {
          if ((!mHiddenChanged) || (!mAdded) || (!mHidden)) {}
        }
        else {
          for (;;)
          {
            break;
            if ((!mAdded) || (mHidden)) {
              break label268;
            }
            continue;
            if (!paramBoolean2) {
              break label274;
            }
            if ((mAdded) || (mView == null) || (mView.getVisibility() != 0) || (mPostponedAlpha < 0.0F)) {
              break label268;
            }
          }
        }
        for (;;)
        {
          i = 1;
          break;
          label268:
          label274:
          do
          {
            i = 0;
            break;
          } while ((!mAdded) || (mHidden));
        }
        j = i;
        i = 0;
        k = 1;
        bool1 = bool2;
        break;
        if (paramBoolean2)
        {
          bool1 = mIsNewlyAdded;
          continue;
        }
        if ((mAdded) || (mHidden)) {
          break label347;
        }
      }
      bool1 = true;
      continue;
      label347:
      bool1 = false;
    }
    Object localObject = (FragmentContainerTransition)paramSparseArray.get(m);
    paramOp = (BackStackRecord.Op)localObject;
    if (bool1)
    {
      localObject = ensureContainer((FragmentContainerTransition)localObject, paramSparseArray, m);
      paramOp = (BackStackRecord.Op)localObject;
      lastIn = localFragment;
      lastInIsPop = paramBoolean1;
      lastInTransaction = paramBackStackRecord;
    }
    localObject = paramOp;
    if ((!paramBoolean2) && (i != 0))
    {
      if ((paramOp != null) && (firstOut == localFragment)) {
        firstOut = null;
      }
      paramOp = mManager;
      if ((mState < 1) && (mCurState >= 1) && (!mReorderingAllowed))
      {
        paramOp.makeActive(localFragment);
        paramOp.moveToState(localFragment, 1, 0, 0, false);
      }
    }
    paramOp = (BackStackRecord.Op)localObject;
    if (j != 0) {
      if (localObject != null)
      {
        paramOp = (BackStackRecord.Op)localObject;
        if (firstOut != null) {}
      }
      else
      {
        paramSparseArray = ensureContainer((FragmentContainerTransition)localObject, paramSparseArray, m);
        paramOp = paramSparseArray;
        firstOut = localFragment;
        firstOutIsPop = paramBoolean1;
        firstOutTransaction = paramBackStackRecord;
      }
    }
    if ((!paramBoolean2) && (k != 0) && (paramOp != null) && (lastIn == localFragment)) {
      lastIn = null;
    }
  }
  
  public static void calculateFragments(BackStackRecord paramBackStackRecord, SparseArray paramSparseArray, boolean paramBoolean)
  {
    int j = mOps.size();
    int i = 0;
    while (i < j)
    {
      addToFirstInLastOut(paramBackStackRecord, (BackStackRecord.Op)mOps.get(i), paramSparseArray, false, paramBoolean);
      i += 1;
    }
  }
  
  private static ArrayMap calculateNameOverrides(int paramInt1, ArrayList paramArrayList1, ArrayList paramArrayList2, int paramInt2, int paramInt3)
  {
    ArrayMap localArrayMap = new ArrayMap();
    paramInt3 -= 1;
    while (paramInt3 >= paramInt2)
    {
      Object localObject = (BackStackRecord)paramArrayList1.get(paramInt3);
      if (((BackStackRecord)localObject).interactsWith(paramInt1))
      {
        boolean bool = ((Boolean)paramArrayList2.get(paramInt3)).booleanValue();
        if (mSharedElementSourceNames != null)
        {
          int j = mSharedElementSourceNames.size();
          ArrayList localArrayList;
          if (bool)
          {
            localArrayList = mSharedElementSourceNames;
            localObject = mSharedElementTargetNames;
          }
          else
          {
            localArrayList = mSharedElementTargetNames;
            localObject = mSharedElementSourceNames;
          }
          int i = 0;
          while (i < j)
          {
            String str1 = (String)((ArrayList)localObject).get(i);
            String str2 = (String)localArrayList.get(i);
            String str3 = (String)localArrayMap.remove(str2);
            if (str3 != null) {
              localArrayMap.put(str1, str3);
            } else {
              localArrayMap.put(str1, str2);
            }
            i += 1;
          }
        }
      }
      paramInt3 -= 1;
    }
    return localArrayMap;
  }
  
  public static void calculatePopFragments(BackStackRecord paramBackStackRecord, SparseArray paramSparseArray, boolean paramBoolean)
  {
    if (!mManager.mContainer.onHasView()) {
      return;
    }
    int i = mOps.size() - 1;
    while (i >= 0)
    {
      addToFirstInLastOut(paramBackStackRecord, (BackStackRecord.Op)mOps.get(i), paramSparseArray, true, paramBoolean);
      i -= 1;
    }
  }
  
  private static void callSharedElementStartEnd(Fragment paramFragment1, Fragment paramFragment2, boolean paramBoolean1, ArrayMap paramArrayMap, boolean paramBoolean2)
  {
    if (paramBoolean1) {
      paramFragment1 = paramFragment2.getEnterTransitionCallback();
    } else {
      paramFragment1 = paramFragment1.getEnterTransitionCallback();
    }
    if (paramFragment1 != null)
    {
      paramFragment2 = new ArrayList();
      ArrayList localArrayList = new ArrayList();
      int j = 0;
      int i;
      if (paramArrayMap == null) {
        i = 0;
      } else {
        i = paramArrayMap.size();
      }
      while (j < i)
      {
        localArrayList.add(paramArrayMap.keyAt(j));
        paramFragment2.add(paramArrayMap.valueAt(j));
        j += 1;
      }
      if (paramBoolean2)
      {
        paramFragment1.onSharedElementStart(localArrayList, paramFragment2, null);
        return;
      }
      paramFragment1.onSharedElementEnd(localArrayList, paramFragment2, null);
    }
  }
  
  private static boolean canHandleAll(FragmentTransitionImpl paramFragmentTransitionImpl, List paramList)
  {
    int j = paramList.size();
    int i = 0;
    while (i < j)
    {
      if (!paramFragmentTransitionImpl.canHandle(paramList.get(i))) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  private static ArrayMap captureInSharedElements(FragmentTransitionImpl paramFragmentTransitionImpl, ArrayMap paramArrayMap, Object paramObject, FragmentContainerTransition paramFragmentContainerTransition)
  {
    Fragment localFragment = lastIn;
    View localView = localFragment.getView();
    ArrayMap localArrayMap;
    if ((!paramArrayMap.isEmpty()) && (paramObject != null) && (localView != null))
    {
      localArrayMap = new ArrayMap();
      paramFragmentTransitionImpl.findNamedViews(localArrayMap, localView);
      paramFragmentTransitionImpl = lastInTransaction;
      if (lastInIsPop)
      {
        paramObject = localFragment.getExitTransitionCallback();
        paramFragmentTransitionImpl = mSharedElementSourceNames;
      }
      else
      {
        paramObject = localFragment.getEnterTransitionCallback();
        paramFragmentTransitionImpl = mSharedElementTargetNames;
      }
      if (paramFragmentTransitionImpl != null)
      {
        localArrayMap.retainAll(paramFragmentTransitionImpl);
        localArrayMap.retainAll(paramArrayMap.values());
      }
      if (paramObject != null)
      {
        paramObject.onMapSharedElements(paramFragmentTransitionImpl, localArrayMap);
        int i = paramFragmentTransitionImpl.size() - 1;
        while (i >= 0)
        {
          paramFragmentContainerTransition = (String)paramFragmentTransitionImpl.get(i);
          paramObject = (View)localArrayMap.get(paramFragmentContainerTransition);
          if (paramObject == null)
          {
            paramObject = findKeyForValue(paramArrayMap, paramFragmentContainerTransition);
            if (paramObject != null) {
              paramArrayMap.remove(paramObject);
            }
          }
          else if (!paramFragmentContainerTransition.equals(ViewCompat.getTransitionName(paramObject)))
          {
            paramFragmentContainerTransition = findKeyForValue(paramArrayMap, paramFragmentContainerTransition);
            if (paramFragmentContainerTransition != null) {
              paramArrayMap.put(paramFragmentContainerTransition, ViewCompat.getTransitionName(paramObject));
            }
          }
          i -= 1;
        }
      }
      retainValues(paramArrayMap, localArrayMap);
      return localArrayMap;
    }
    paramArrayMap.clear();
    return null;
    return localArrayMap;
  }
  
  private static ArrayMap captureOutSharedElements(FragmentTransitionImpl paramFragmentTransitionImpl, ArrayMap paramArrayMap, Object paramObject, FragmentContainerTransition paramFragmentContainerTransition)
  {
    ArrayMap localArrayMap;
    if ((!paramArrayMap.isEmpty()) && (paramObject != null))
    {
      paramObject = firstOut;
      localArrayMap = new ArrayMap();
      paramFragmentTransitionImpl.findNamedViews(localArrayMap, paramObject.getView());
      paramFragmentTransitionImpl = firstOutTransaction;
      if (firstOutIsPop)
      {
        paramObject = paramObject.getEnterTransitionCallback();
        paramFragmentTransitionImpl = mSharedElementTargetNames;
      }
      else
      {
        paramObject = paramObject.getExitTransitionCallback();
        paramFragmentTransitionImpl = mSharedElementSourceNames;
      }
      localArrayMap.retainAll(paramFragmentTransitionImpl);
      if (paramObject != null)
      {
        paramObject.onMapSharedElements(paramFragmentTransitionImpl, localArrayMap);
        int i = paramFragmentTransitionImpl.size() - 1;
        while (i >= 0)
        {
          paramFragmentContainerTransition = (String)paramFragmentTransitionImpl.get(i);
          paramObject = (View)localArrayMap.get(paramFragmentContainerTransition);
          if (paramObject == null)
          {
            paramArrayMap.remove(paramFragmentContainerTransition);
          }
          else if (!paramFragmentContainerTransition.equals(ViewCompat.getTransitionName(paramObject)))
          {
            paramFragmentContainerTransition = (String)paramArrayMap.remove(paramFragmentContainerTransition);
            paramArrayMap.put(ViewCompat.getTransitionName(paramObject), paramFragmentContainerTransition);
          }
          i -= 1;
        }
      }
      paramArrayMap.retainAll(localArrayMap.keySet());
      return localArrayMap;
    }
    paramArrayMap.clear();
    return null;
    return localArrayMap;
  }
  
  private static FragmentTransitionImpl chooseImpl(Fragment paramFragment1, Fragment paramFragment2)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramFragment1 != null)
    {
      Object localObject = paramFragment1.getExitTransition();
      if (localObject != null) {
        localArrayList.add(localObject);
      }
      localObject = paramFragment1.getReturnTransition();
      if (localObject != null) {
        localArrayList.add(localObject);
      }
      paramFragment1 = paramFragment1.getSharedElementReturnTransition();
      if (paramFragment1 != null) {
        localArrayList.add(paramFragment1);
      }
    }
    if (paramFragment2 != null)
    {
      paramFragment1 = paramFragment2.getEnterTransition();
      if (paramFragment1 != null) {
        localArrayList.add(paramFragment1);
      }
      paramFragment1 = paramFragment2.getReenterTransition();
      if (paramFragment1 != null) {
        localArrayList.add(paramFragment1);
      }
      paramFragment1 = paramFragment2.getSharedElementEnterTransition();
      if (paramFragment1 != null) {
        localArrayList.add(paramFragment1);
      }
    }
    if (localArrayList.isEmpty()) {
      return null;
    }
    if ((PLATFORM_IMPL != null) && (canHandleAll(PLATFORM_IMPL, localArrayList))) {
      return PLATFORM_IMPL;
    }
    if ((SUPPORT_IMPL != null) && (canHandleAll(SUPPORT_IMPL, localArrayList))) {
      return SUPPORT_IMPL;
    }
    if ((PLATFORM_IMPL == null) && (SUPPORT_IMPL == null)) {
      return null;
    }
    throw new IllegalArgumentException("Invalid Transition types");
  }
  
  private static ArrayList configureEnteringExitingViews(FragmentTransitionImpl paramFragmentTransitionImpl, Object paramObject, Fragment paramFragment, ArrayList paramArrayList, View paramView)
  {
    ArrayList localArrayList;
    if (paramObject != null)
    {
      localArrayList = new ArrayList();
      paramFragment = paramFragment.getView();
      if (paramFragment != null) {
        paramFragmentTransitionImpl.captureTransitioningViews(localArrayList, paramFragment);
      }
      if (paramArrayList != null) {
        localArrayList.removeAll(paramArrayList);
      }
      if (!localArrayList.isEmpty())
      {
        localArrayList.add(paramView);
        paramFragmentTransitionImpl.addTargets(paramObject, localArrayList);
        return localArrayList;
      }
    }
    else
    {
      return null;
    }
    return localArrayList;
  }
  
  private static Object configureSharedElementsOrdered(FragmentTransitionImpl paramFragmentTransitionImpl, ViewGroup paramViewGroup, View paramView, ArrayMap paramArrayMap, FragmentContainerTransition paramFragmentContainerTransition, ArrayList paramArrayList1, ArrayList paramArrayList2, Object paramObject1, Object paramObject2)
  {
    Fragment localFragment1 = lastIn;
    Fragment localFragment2 = firstOut;
    if (localFragment1 != null)
    {
      if (localFragment2 == null) {
        return null;
      }
      boolean bool = lastInIsPop;
      Object localObject;
      if (paramArrayMap.isEmpty()) {
        localObject = null;
      } else {
        localObject = getSharedElementTransition(paramFragmentTransitionImpl, localFragment1, localFragment2, bool);
      }
      ArrayMap localArrayMap = captureOutSharedElements(paramFragmentTransitionImpl, paramArrayMap, localObject, paramFragmentContainerTransition);
      if (paramArrayMap.isEmpty()) {
        localObject = null;
      } else {
        paramArrayList1.addAll(localArrayMap.values());
      }
      if ((paramObject1 == null) && (paramObject2 == null) && (localObject == null)) {
        return null;
      }
      callSharedElementStartEnd(localFragment1, localFragment2, bool, localArrayMap, true);
      if (localObject != null)
      {
        Rect localRect = new Rect();
        paramFragmentTransitionImpl.setSharedElementTargets(localObject, paramView, paramArrayList1);
        setOutEpicenter(paramFragmentTransitionImpl, localObject, paramObject2, localArrayMap, firstOutIsPop, firstOutTransaction);
        paramObject2 = localRect;
        if (paramObject1 != null)
        {
          paramFragmentTransitionImpl.setEpicenter(paramObject1, localRect);
          paramObject2 = localRect;
        }
      }
      else
      {
        paramObject2 = null;
      }
      OneShotPreDrawListener.a(paramViewGroup, new FragmentTransition.4(paramFragmentTransitionImpl, paramArrayMap, localObject, paramFragmentContainerTransition, paramArrayList2, paramView, localFragment1, localFragment2, bool, paramArrayList1, paramObject1, paramObject2));
      return localObject;
    }
    return null;
  }
  
  private static Object configureSharedElementsReordered(FragmentTransitionImpl paramFragmentTransitionImpl, ViewGroup paramViewGroup, View paramView, ArrayMap paramArrayMap, FragmentContainerTransition paramFragmentContainerTransition, ArrayList paramArrayList1, ArrayList paramArrayList2, Object paramObject1, Object paramObject2)
  {
    Fragment localFragment1 = lastIn;
    Fragment localFragment2 = firstOut;
    if (localFragment1 != null) {
      localFragment1.getView().setVisibility(0);
    }
    if (localFragment1 != null)
    {
      if (localFragment2 == null) {
        return null;
      }
      boolean bool = lastInIsPop;
      Object localObject;
      if (paramArrayMap.isEmpty()) {
        localObject = null;
      } else {
        localObject = getSharedElementTransition(paramFragmentTransitionImpl, localFragment1, localFragment2, bool);
      }
      ArrayMap localArrayMap2 = captureOutSharedElements(paramFragmentTransitionImpl, paramArrayMap, localObject, paramFragmentContainerTransition);
      ArrayMap localArrayMap1 = captureInSharedElements(paramFragmentTransitionImpl, paramArrayMap, localObject, paramFragmentContainerTransition);
      if (paramArrayMap.isEmpty())
      {
        if (localArrayMap2 != null) {
          localArrayMap2.clear();
        }
        if (localArrayMap1 != null) {
          localArrayMap1.clear();
        }
        localObject = null;
      }
      else
      {
        addSharedElementsWithMatchingNames(paramArrayList1, localArrayMap2, paramArrayMap.keySet());
        addSharedElementsWithMatchingNames(paramArrayList2, localArrayMap1, paramArrayMap.values());
      }
      if ((paramObject1 == null) && (paramObject2 == null) && (localObject == null)) {
        return null;
      }
      callSharedElementStartEnd(localFragment1, localFragment2, bool, localArrayMap2, true);
      if (localObject != null)
      {
        paramArrayList2.add(paramView);
        paramFragmentTransitionImpl.setSharedElementTargets(localObject, paramView, paramArrayList1);
        setOutEpicenter(paramFragmentTransitionImpl, localObject, paramObject2, localArrayMap2, firstOutIsPop, firstOutTransaction);
        paramView = new Rect();
        paramArrayMap = getInEpicenterView(localArrayMap1, paramFragmentContainerTransition, paramObject1, bool);
        if (paramArrayMap != null) {
          paramFragmentTransitionImpl.setEpicenter(paramObject1, paramView);
        }
      }
      else
      {
        paramArrayMap = null;
        paramView = null;
      }
      OneShotPreDrawListener.a(paramViewGroup, new FragmentTransition.3(localFragment1, localFragment2, bool, localArrayMap1, paramArrayMap, paramFragmentTransitionImpl, paramView));
      return localObject;
    }
    return null;
  }
  
  private static void configureTransitionsOrdered(FragmentManagerImpl paramFragmentManagerImpl, int paramInt, FragmentContainerTransition paramFragmentContainerTransition, View paramView, ArrayMap paramArrayMap)
  {
    if (mContainer.onHasView()) {
      paramFragmentManagerImpl = (ViewGroup)mContainer.onFindViewById(paramInt);
    } else {
      paramFragmentManagerImpl = null;
    }
    if (paramFragmentManagerImpl == null) {
      return;
    }
    Fragment localFragment = lastIn;
    Object localObject4 = firstOut;
    FragmentTransitionImpl localFragmentTransitionImpl = chooseImpl((Fragment)localObject4, localFragment);
    if (localFragmentTransitionImpl == null) {
      return;
    }
    boolean bool1 = lastInIsPop;
    boolean bool2 = firstOutIsPop;
    Object localObject2 = getEnterTransition(localFragmentTransitionImpl, localFragment, bool1);
    Object localObject1 = getExitTransition(localFragmentTransitionImpl, (Fragment)localObject4, bool2);
    ArrayList localArrayList2 = new ArrayList();
    ArrayList localArrayList1 = new ArrayList();
    Object localObject3 = configureSharedElementsOrdered(localFragmentTransitionImpl, paramFragmentManagerImpl, paramView, paramArrayMap, paramFragmentContainerTransition, localArrayList2, localArrayList1, localObject2, localObject1);
    if ((localObject2 == null) && (localObject3 == null) && (localObject1 == null)) {
      return;
    }
    localObject4 = configureEnteringExitingViews(localFragmentTransitionImpl, localObject1, (Fragment)localObject4, localArrayList2, paramView);
    if ((localObject4 != null) && (!((ArrayList)localObject4).isEmpty())) {
      break label183;
    }
    localObject1 = null;
    label183:
    localFragmentTransitionImpl.addTarget(localObject2, paramView);
    paramFragmentContainerTransition = mergeTransitions(localFragmentTransitionImpl, localObject2, localObject1, localObject3, localFragment, lastInIsPop);
    if (paramFragmentContainerTransition != null)
    {
      localArrayList2 = new ArrayList();
      localFragmentTransitionImpl.scheduleRemoveTargets(paramFragmentContainerTransition, localObject2, localArrayList2, localObject1, (ArrayList)localObject4, localObject3, localArrayList1);
      scheduleTargetChange(localFragmentTransitionImpl, paramFragmentManagerImpl, localFragment, paramView, localArrayList1, localObject2, localArrayList2, localObject1, (ArrayList)localObject4);
      localFragmentTransitionImpl.setNameOverridesOrdered(paramFragmentManagerImpl, localArrayList1, paramArrayMap);
      localFragmentTransitionImpl.beginDelayedTransition(paramFragmentManagerImpl, paramFragmentContainerTransition);
      localFragmentTransitionImpl.scheduleNameReset(paramFragmentManagerImpl, localArrayList1, paramArrayMap);
    }
  }
  
  private static void configureTransitionsReordered(FragmentManagerImpl paramFragmentManagerImpl, int paramInt, FragmentContainerTransition paramFragmentContainerTransition, View paramView, ArrayMap paramArrayMap)
  {
    if (mContainer.onHasView()) {
      paramFragmentManagerImpl = (ViewGroup)mContainer.onFindViewById(paramInt);
    } else {
      paramFragmentManagerImpl = null;
    }
    if (paramFragmentManagerImpl == null) {
      return;
    }
    Object localObject5 = lastIn;
    Object localObject3 = firstOut;
    FragmentTransitionImpl localFragmentTransitionImpl = chooseImpl((Fragment)localObject3, (Fragment)localObject5);
    if (localFragmentTransitionImpl == null) {
      return;
    }
    boolean bool1 = lastInIsPop;
    boolean bool2 = firstOutIsPop;
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    Object localObject2 = getEnterTransition(localFragmentTransitionImpl, (Fragment)localObject5, bool1);
    Object localObject1 = getExitTransition(localFragmentTransitionImpl, (Fragment)localObject3, bool2);
    Object localObject4 = configureSharedElementsReordered(localFragmentTransitionImpl, paramFragmentManagerImpl, paramView, paramArrayMap, paramFragmentContainerTransition, localArrayList2, localArrayList1, localObject2, localObject1);
    if ((localObject2 == null) && (localObject4 == null) && (localObject1 == null)) {
      return;
    }
    paramFragmentContainerTransition = (FragmentContainerTransition)localObject1;
    localObject1 = configureEnteringExitingViews(localFragmentTransitionImpl, paramFragmentContainerTransition, (Fragment)localObject3, localArrayList2, paramView);
    paramView = configureEnteringExitingViews(localFragmentTransitionImpl, localObject2, (Fragment)localObject5, localArrayList1, paramView);
    setViewVisibility(paramView, 4);
    localObject5 = mergeTransitions(localFragmentTransitionImpl, localObject2, paramFragmentContainerTransition, localObject4, (Fragment)localObject5, bool1);
    if (localObject5 != null)
    {
      replaceHide(localFragmentTransitionImpl, paramFragmentContainerTransition, (Fragment)localObject3, (ArrayList)localObject1);
      localObject3 = localFragmentTransitionImpl.prepareSetNameOverridesReordered(localArrayList1);
      localFragmentTransitionImpl.scheduleRemoveTargets(localObject5, localObject2, paramView, paramFragmentContainerTransition, (ArrayList)localObject1, localObject4, localArrayList1);
      localFragmentTransitionImpl.beginDelayedTransition(paramFragmentManagerImpl, localObject5);
      localFragmentTransitionImpl.setNameOverridesReordered(paramFragmentManagerImpl, localArrayList2, localArrayList1, (ArrayList)localObject3, paramArrayMap);
      setViewVisibility(paramView, 0);
      localFragmentTransitionImpl.swapSharedElementTargets(localObject4, localArrayList2, localArrayList1);
    }
  }
  
  private static FragmentContainerTransition ensureContainer(FragmentContainerTransition paramFragmentContainerTransition, SparseArray paramSparseArray, int paramInt)
  {
    FragmentContainerTransition localFragmentContainerTransition = paramFragmentContainerTransition;
    if (paramFragmentContainerTransition == null)
    {
      localFragmentContainerTransition = new FragmentContainerTransition();
      paramSparseArray.put(paramInt, localFragmentContainerTransition);
    }
    return localFragmentContainerTransition;
  }
  
  private static String findKeyForValue(ArrayMap paramArrayMap, String paramString)
  {
    int j = paramArrayMap.size();
    int i = 0;
    while (i < j)
    {
      if (paramString.equals(paramArrayMap.valueAt(i))) {
        return (String)paramArrayMap.keyAt(i);
      }
      i += 1;
    }
    return null;
  }
  
  private static Object getEnterTransition(FragmentTransitionImpl paramFragmentTransitionImpl, Fragment paramFragment, boolean paramBoolean)
  {
    if (paramFragment == null) {
      return null;
    }
    if (paramBoolean) {
      paramFragment = paramFragment.getReenterTransition();
    } else {
      paramFragment = paramFragment.getEnterTransition();
    }
    return paramFragmentTransitionImpl.cloneTransition(paramFragment);
  }
  
  private static Object getExitTransition(FragmentTransitionImpl paramFragmentTransitionImpl, Fragment paramFragment, boolean paramBoolean)
  {
    if (paramFragment == null) {
      return null;
    }
    if (paramBoolean) {
      paramFragment = paramFragment.getReturnTransition();
    } else {
      paramFragment = paramFragment.getExitTransition();
    }
    return paramFragmentTransitionImpl.cloneTransition(paramFragment);
  }
  
  private static View getInEpicenterView(ArrayMap paramArrayMap, FragmentContainerTransition paramFragmentContainerTransition, Object paramObject, boolean paramBoolean)
  {
    paramFragmentContainerTransition = lastInTransaction;
    if ((paramObject != null) && (paramArrayMap != null) && (mSharedElementSourceNames != null) && (!mSharedElementSourceNames.isEmpty()))
    {
      if (paramBoolean) {
        paramFragmentContainerTransition = (String)mSharedElementSourceNames.get(0);
      } else {
        paramFragmentContainerTransition = (String)mSharedElementTargetNames.get(0);
      }
      return (View)paramArrayMap.get(paramFragmentContainerTransition);
    }
    return null;
  }
  
  private static Object getSharedElementTransition(FragmentTransitionImpl paramFragmentTransitionImpl, Fragment paramFragment1, Fragment paramFragment2, boolean paramBoolean)
  {
    if ((paramFragment1 != null) && (paramFragment2 != null))
    {
      if (paramBoolean) {
        paramFragment1 = paramFragment2.getSharedElementReturnTransition();
      } else {
        paramFragment1 = paramFragment1.getSharedElementEnterTransition();
      }
      return paramFragmentTransitionImpl.wrapTransitionInSet(paramFragmentTransitionImpl.cloneTransition(paramFragment1));
    }
    return null;
  }
  
  private static Object mergeTransitions(FragmentTransitionImpl paramFragmentTransitionImpl, Object paramObject1, Object paramObject2, Object paramObject3, Fragment paramFragment, boolean paramBoolean)
  {
    if ((paramObject1 != null) && (paramObject2 != null) && (paramFragment != null))
    {
      if (paramBoolean) {
        paramBoolean = paramFragment.getAllowReturnTransitionOverlap();
      } else {
        paramBoolean = paramFragment.getAllowEnterTransitionOverlap();
      }
    }
    else {
      paramBoolean = true;
    }
    if (paramBoolean) {
      return paramFragmentTransitionImpl.mergeTransitionsTogether(paramObject2, paramObject1, paramObject3);
    }
    return paramFragmentTransitionImpl.mergeTransitionsInSequence(paramObject2, paramObject1, paramObject3);
  }
  
  private static void replaceHide(FragmentTransitionImpl paramFragmentTransitionImpl, Object paramObject, Fragment paramFragment, ArrayList paramArrayList)
  {
    if ((paramFragment != null) && (paramObject != null) && (mAdded) && (mHidden) && (mHiddenChanged))
    {
      paramFragment.setHideReplaced(true);
      paramFragmentTransitionImpl.scheduleHideFragmentView(paramObject, paramFragment.getView(), paramArrayList);
      OneShotPreDrawListener.a(mContainer, new FragmentTransition.1(paramArrayList));
    }
  }
  
  private static FragmentTransitionImpl resolveSupportImpl()
  {
    try
    {
      Object localObject = Class.forName("android.support.transition.FragmentTransitionSupport");
      localObject = ((Class)localObject).getDeclaredConstructor(new Class[0]);
      localObject = ((Constructor)localObject).newInstance(new Object[0]);
      return (FragmentTransitionImpl)localObject;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return null;
  }
  
  private static void retainValues(ArrayMap paramArrayMap1, ArrayMap paramArrayMap2)
  {
    int i = paramArrayMap1.size() - 1;
    while (i >= 0)
    {
      if (!paramArrayMap2.containsKey((String)paramArrayMap1.valueAt(i))) {
        paramArrayMap1.removeAt(i);
      }
      i -= 1;
    }
  }
  
  private static void scheduleTargetChange(FragmentTransitionImpl paramFragmentTransitionImpl, ViewGroup paramViewGroup, Fragment paramFragment, View paramView, ArrayList paramArrayList1, Object paramObject1, ArrayList paramArrayList2, Object paramObject2, ArrayList paramArrayList3)
  {
    OneShotPreDrawListener.a(paramViewGroup, new FragmentTransition.2(paramObject1, paramFragmentTransitionImpl, paramView, paramFragment, paramArrayList1, paramArrayList2, paramArrayList3, paramObject2));
  }
  
  private static void setOutEpicenter(FragmentTransitionImpl paramFragmentTransitionImpl, Object paramObject1, Object paramObject2, ArrayMap paramArrayMap, boolean paramBoolean, BackStackRecord paramBackStackRecord)
  {
    if ((mSharedElementSourceNames != null) && (!mSharedElementSourceNames.isEmpty()))
    {
      if (paramBoolean) {
        paramBackStackRecord = (String)mSharedElementTargetNames.get(0);
      } else {
        paramBackStackRecord = (String)mSharedElementSourceNames.get(0);
      }
      paramArrayMap = (View)paramArrayMap.get(paramBackStackRecord);
      paramFragmentTransitionImpl.setEpicenter(paramObject1, paramArrayMap);
      if (paramObject2 != null) {
        paramFragmentTransitionImpl.setEpicenter(paramObject2, paramArrayMap);
      }
    }
  }
  
  private static void setViewVisibility(ArrayList paramArrayList, int paramInt)
  {
    if (paramArrayList == null) {
      return;
    }
    int i = paramArrayList.size() - 1;
    while (i >= 0)
    {
      ((View)paramArrayList.get(i)).setVisibility(paramInt);
      i -= 1;
    }
  }
  
  static void startTransitions(FragmentManagerImpl paramFragmentManagerImpl, ArrayList paramArrayList1, ArrayList paramArrayList2, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    if (mCurState < 1) {
      return;
    }
    SparseArray localSparseArray = new SparseArray();
    int i = paramInt1;
    Object localObject;
    while (i < paramInt2)
    {
      localObject = (BackStackRecord)paramArrayList1.get(i);
      if (((Boolean)paramArrayList2.get(i)).booleanValue()) {
        calculatePopFragments((BackStackRecord)localObject, localSparseArray, paramBoolean);
      } else {
        calculateFragments((BackStackRecord)localObject, localSparseArray, paramBoolean);
      }
      i += 1;
    }
    if (localSparseArray.size() != 0)
    {
      localObject = new View(mHost.getContext());
      int j = localSparseArray.size();
      i = 0;
      while (i < j)
      {
        int k = localSparseArray.keyAt(i);
        ArrayMap localArrayMap = calculateNameOverrides(k, paramArrayList1, paramArrayList2, paramInt1, paramInt2);
        FragmentContainerTransition localFragmentContainerTransition = (FragmentContainerTransition)localSparseArray.valueAt(i);
        if (paramBoolean) {
          configureTransitionsReordered(paramFragmentManagerImpl, k, localFragmentContainerTransition, (View)localObject, localArrayMap);
        } else {
          configureTransitionsOrdered(paramFragmentManagerImpl, k, localFragmentContainerTransition, (View)localObject, localArrayMap);
        }
        i += 1;
      }
    }
  }
  
  static boolean supportsTransition()
  {
    return (PLATFORM_IMPL != null) || (SUPPORT_IMPL != null);
  }
  
  class FragmentContainerTransition
  {
    public Fragment firstOut;
    public boolean firstOutIsPop;
    public BackStackRecord firstOutTransaction;
    public Fragment lastIn;
    public boolean lastInIsPop;
    public BackStackRecord lastInTransaction;
    
    FragmentContainerTransition() {}
  }
}
