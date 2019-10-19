package android.support.v4.package_7;

import android.content.Context;
import android.support.v4.util.LogWriter;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

final class BackStackRecord
  extends FragmentTransaction
  implements FragmentManager.BackStackEntry, FragmentManagerImpl.OpGenerator
{
  static final int OP_ADD = 1;
  static final int OP_ATTACH = 7;
  static final int OP_DETACH = 6;
  static final int OP_HIDE = 4;
  static final int OP_NULL = 0;
  static final int OP_REMOVE = 3;
  static final int OP_REPLACE = 2;
  static final int OP_SET_PRIMARY_NAV = 8;
  static final int OP_SHOW = 5;
  static final int OP_UNSET_PRIMARY_NAV = 9;
  static final String TAG = "FragmentManager";
  boolean mAddToBackStack;
  boolean mAllowAddToBackStack = true;
  int mBreadCrumbShortTitleRes;
  CharSequence mBreadCrumbShortTitleText;
  int mBreadCrumbTitleRes;
  CharSequence mBreadCrumbTitleText;
  ArrayList<Runnable> mCommitRunnables;
  boolean mCommitted;
  int mEnterAnim;
  int mExitAnim;
  int mIndex = -1;
  final FragmentManagerImpl mManager;
  String mName;
  ArrayList<android.support.v4.app.BackStackRecord.Op> mOps = new ArrayList();
  int mPopEnterAnim;
  int mPopExitAnim;
  boolean mReorderingAllowed = false;
  ArrayList<String> mSharedElementSourceNames;
  ArrayList<String> mSharedElementTargetNames;
  int mTransition;
  int mTransitionStyle;
  
  public BackStackRecord(FragmentManagerImpl paramFragmentManagerImpl)
  {
    mManager = paramFragmentManagerImpl;
  }
  
  private void doAddOp(int paramInt1, Fragment paramFragment, String paramString, int paramInt2)
  {
    Object localObject = paramFragment.getClass();
    int i = ((Class)localObject).getModifiers();
    if ((!((Class)localObject).isAnonymousClass()) && (Modifier.isPublic(i)) && ((!((Class)localObject).isMemberClass()) || (Modifier.isStatic(i))))
    {
      mFragmentManager = mManager;
      if (paramString != null)
      {
        if ((mTag != null) && (!paramString.equals(mTag)))
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("Can't change tag of fragment ");
          ((StringBuilder)localObject).append(paramFragment);
          ((StringBuilder)localObject).append(": was ");
          ((StringBuilder)localObject).append(mTag);
          ((StringBuilder)localObject).append(" now ");
          ((StringBuilder)localObject).append(paramString);
          throw new IllegalStateException(((StringBuilder)localObject).toString());
        }
        mTag = paramString;
      }
      if (paramInt1 != 0) {
        if (paramInt1 != -1)
        {
          if ((mFragmentId != 0) && (mFragmentId != paramInt1))
          {
            paramString = new StringBuilder();
            paramString.append("Can't change container ID of fragment ");
            paramString.append(paramFragment);
            paramString.append(": was ");
            paramString.append(mFragmentId);
            paramString.append(" now ");
            paramString.append(paramInt1);
            throw new IllegalStateException(paramString.toString());
          }
          mFragmentId = paramInt1;
          mContainerId = paramInt1;
        }
        else
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("Can't add fragment ");
          ((StringBuilder)localObject).append(paramFragment);
          ((StringBuilder)localObject).append(" with tag ");
          ((StringBuilder)localObject).append(paramString);
          ((StringBuilder)localObject).append(" to container view with no id");
          throw new IllegalArgumentException(((StringBuilder)localObject).toString());
        }
      }
      addOp(new Op(paramInt2, paramFragment));
      return;
    }
    paramFragment = new StringBuilder();
    paramFragment.append("Fragment ");
    paramFragment.append(((Class)localObject).getCanonicalName());
    paramFragment.append(" must be a public static class to be  properly recreated from");
    paramFragment.append(" instance state.");
    throw new IllegalStateException(paramFragment.toString());
  }
  
  private static boolean isFragmentPostponed(Op paramOp)
  {
    paramOp = fragment;
    return (paramOp != null) && (mAdded) && (mView != null) && (!mDetached) && (!mHidden) && (paramOp.isPostponed());
  }
  
  public FragmentTransaction add(int paramInt, Fragment paramFragment)
  {
    doAddOp(paramInt, paramFragment, null, 1);
    return this;
  }
  
  public FragmentTransaction add(int paramInt, Fragment paramFragment, String paramString)
  {
    doAddOp(paramInt, paramFragment, paramString, 1);
    return this;
  }
  
  public FragmentTransaction add(Fragment paramFragment, String paramString)
  {
    doAddOp(0, paramFragment, paramString, 1);
    return this;
  }
  
  void addOp(Op paramOp)
  {
    mOps.add(paramOp);
    enterAnim = mEnterAnim;
    exitAnim = mExitAnim;
    popEnterAnim = mPopEnterAnim;
    popExitAnim = mPopExitAnim;
  }
  
  public FragmentTransaction addSharedElement(View paramView, String paramString)
  {
    if (FragmentTransition.supportsTransition())
    {
      paramView = ViewCompat.getTransitionName(paramView);
      if (paramView != null)
      {
        if (mSharedElementSourceNames == null)
        {
          mSharedElementSourceNames = new ArrayList();
          mSharedElementTargetNames = new ArrayList();
        }
        else
        {
          if (mSharedElementTargetNames.contains(paramString)) {
            break label131;
          }
          if (mSharedElementSourceNames.contains(paramView)) {
            break label89;
          }
        }
        mSharedElementSourceNames.add(paramView);
        mSharedElementTargetNames.add(paramString);
        return this;
        label89:
        paramString = new StringBuilder();
        paramString.append("A shared element with the source name '");
        paramString.append(paramView);
        paramString.append(" has already been added to the transaction.");
        throw new IllegalArgumentException(paramString.toString());
        label131:
        paramView = new StringBuilder();
        paramView.append("A shared element with the target name '");
        paramView.append(paramString);
        paramView.append("' has already been added to the transaction.");
        throw new IllegalArgumentException(paramView.toString());
      }
      throw new IllegalArgumentException("Unique transitionNames are required for all sharedElements");
    }
    return this;
  }
  
  public FragmentTransaction addToBackStack(String paramString)
  {
    if (mAllowAddToBackStack)
    {
      mAddToBackStack = true;
      mName = paramString;
      return this;
    }
    throw new IllegalStateException("This FragmentTransaction is not allowed to be added to the back stack.");
  }
  
  public FragmentTransaction attach(Fragment paramFragment)
  {
    addOp(new Op(7, paramFragment));
    return this;
  }
  
  void bumpBackStackNesting(int paramInt)
  {
    if (!mAddToBackStack) {
      return;
    }
    Object localObject1;
    if (FragmentManagerImpl.DEBUG)
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("Bump nesting in ");
      ((StringBuilder)localObject1).append(this);
      ((StringBuilder)localObject1).append(" by ");
      ((StringBuilder)localObject1).append(paramInt);
      Log.v("FragmentManager", ((StringBuilder)localObject1).toString());
    }
    int j = mOps.size();
    int i = 0;
    while (i < j)
    {
      localObject1 = (Op)mOps.get(i);
      if (fragment != null)
      {
        Object localObject2 = fragment;
        mBackStackNesting += paramInt;
        if (FragmentManagerImpl.DEBUG)
        {
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append("Bump nesting of ");
          ((StringBuilder)localObject2).append(fragment);
          ((StringBuilder)localObject2).append(" to ");
          ((StringBuilder)localObject2).append(fragment.mBackStackNesting);
          Log.v("FragmentManager", ((StringBuilder)localObject2).toString());
        }
      }
      i += 1;
    }
  }
  
  public int commit()
  {
    return commitInternal(false);
  }
  
  public int commitAllowingStateLoss()
  {
    return commitInternal(true);
  }
  
  int commitInternal(boolean paramBoolean)
  {
    if (!mCommitted)
    {
      if (FragmentManagerImpl.DEBUG)
      {
        Object localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Commit: ");
        ((StringBuilder)localObject).append(this);
        Log.v("FragmentManager", ((StringBuilder)localObject).toString());
        localObject = new PrintWriter(new LogWriter("FragmentManager"));
        dump("  ", null, (PrintWriter)localObject, null);
        ((PrintWriter)localObject).close();
      }
      mCommitted = true;
      if (mAddToBackStack) {
        mIndex = mManager.allocBackStackIndex(this);
      } else {
        mIndex = -1;
      }
      mManager.enqueueAction(this, paramBoolean);
      return mIndex;
    }
    throw new IllegalStateException("commit already called");
  }
  
  public void commitNow()
  {
    disallowAddToBackStack();
    mManager.execSingleAction(this, false);
  }
  
  public void commitNowAllowingStateLoss()
  {
    disallowAddToBackStack();
    mManager.execSingleAction(this, true);
  }
  
  public FragmentTransaction detach(Fragment paramFragment)
  {
    addOp(new Op(6, paramFragment));
    return this;
  }
  
  public FragmentTransaction disallowAddToBackStack()
  {
    if (!mAddToBackStack)
    {
      mAllowAddToBackStack = false;
      return this;
    }
    throw new IllegalStateException("This transaction is already being added to the back stack");
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    dump(paramString, paramPrintWriter, true);
  }
  
  public void dump(String paramString, PrintWriter paramPrintWriter, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mName=");
      paramPrintWriter.print(mName);
      paramPrintWriter.print(" mIndex=");
      paramPrintWriter.print(mIndex);
      paramPrintWriter.print(" mCommitted=");
      paramPrintWriter.println(mCommitted);
      if (mTransition != 0)
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("mTransition=#");
        paramPrintWriter.print(Integer.toHexString(mTransition));
        paramPrintWriter.print(" mTransitionStyle=#");
        paramPrintWriter.println(Integer.toHexString(mTransitionStyle));
      }
      if ((mEnterAnim != 0) || (mExitAnim != 0))
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("mEnterAnim=#");
        paramPrintWriter.print(Integer.toHexString(mEnterAnim));
        paramPrintWriter.print(" mExitAnim=#");
        paramPrintWriter.println(Integer.toHexString(mExitAnim));
      }
      if ((mPopEnterAnim != 0) || (mPopExitAnim != 0))
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("mPopEnterAnim=#");
        paramPrintWriter.print(Integer.toHexString(mPopEnterAnim));
        paramPrintWriter.print(" mPopExitAnim=#");
        paramPrintWriter.println(Integer.toHexString(mPopExitAnim));
      }
      if ((mBreadCrumbTitleRes != 0) || (mBreadCrumbTitleText != null))
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("mBreadCrumbTitleRes=#");
        paramPrintWriter.print(Integer.toHexString(mBreadCrumbTitleRes));
        paramPrintWriter.print(" mBreadCrumbTitleText=");
        paramPrintWriter.println(mBreadCrumbTitleText);
      }
      if ((mBreadCrumbShortTitleRes != 0) || (mBreadCrumbShortTitleText != null))
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("mBreadCrumbShortTitleRes=#");
        paramPrintWriter.print(Integer.toHexString(mBreadCrumbShortTitleRes));
        paramPrintWriter.print(" mBreadCrumbShortTitleText=");
        paramPrintWriter.println(mBreadCrumbShortTitleText);
      }
    }
    if (!mOps.isEmpty())
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.println("Operations:");
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append("    ");
      ((StringBuilder)localObject).toString();
      int j = mOps.size();
      int i = 0;
      while (i < j)
      {
        Op localOp = (Op)mOps.get(i);
        switch (removed)
        {
        default: 
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("cmd=");
          ((StringBuilder)localObject).append(removed);
          localObject = ((StringBuilder)localObject).toString();
          break;
        case 9: 
          localObject = "UNSET_PRIMARY_NAV";
          break;
        case 8: 
          localObject = "SET_PRIMARY_NAV";
          break;
        case 7: 
          localObject = "ATTACH";
          break;
        case 6: 
          localObject = "DETACH";
          break;
        case 5: 
          localObject = "SHOW";
          break;
        case 4: 
          localObject = "HIDE";
          break;
        case 3: 
          localObject = "REMOVE";
          break;
        case 2: 
          localObject = "REPLACE";
          break;
        case 1: 
          localObject = "ADD";
          break;
        case 0: 
          localObject = "NULL";
        }
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("  Op #");
        paramPrintWriter.print(i);
        paramPrintWriter.print(": ");
        paramPrintWriter.print((String)localObject);
        paramPrintWriter.print(" ");
        paramPrintWriter.println(fragment);
        if (paramBoolean)
        {
          if ((enterAnim != 0) || (exitAnim != 0))
          {
            paramPrintWriter.print(paramString);
            paramPrintWriter.print("enterAnim=#");
            paramPrintWriter.print(Integer.toHexString(enterAnim));
            paramPrintWriter.print(" exitAnim=#");
            paramPrintWriter.println(Integer.toHexString(exitAnim));
          }
          if ((popEnterAnim != 0) || (popExitAnim != 0))
          {
            paramPrintWriter.print(paramString);
            paramPrintWriter.print("popEnterAnim=#");
            paramPrintWriter.print(Integer.toHexString(popEnterAnim));
            paramPrintWriter.print(" popExitAnim=#");
            paramPrintWriter.println(Integer.toHexString(popExitAnim));
          }
        }
        i += 1;
      }
    }
  }
  
  void executeOps()
  {
    Object localObject2 = mOps;
    Object localObject1 = this;
    int j = ((ArrayList)localObject2).size();
    int i = 0;
    while (i < j)
    {
      localObject2 = mOps;
      localObject2 = (Op)((ArrayList)localObject2).get(i);
      Fragment localFragment = fragment;
      if (localFragment != null) {
        localFragment.setNextTransition(mTransition, mTransitionStyle);
      }
      int k = removed;
      if (k != 1)
      {
        switch (k)
        {
        default: 
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append("Unknown cmd: ");
          ((StringBuilder)localObject1).append(removed);
          throw new IllegalArgumentException(((StringBuilder)localObject1).toString());
        case 9: 
          mManager.setPrimaryNavigationFragment(null);
          break;
        case 8: 
          mManager.setPrimaryNavigationFragment(localFragment);
          break;
        case 7: 
          localFragment.setNextAnim(enterAnim);
          mManager.attachFragment(localFragment);
          break;
        case 6: 
          localFragment.setNextAnim(exitAnim);
          mManager.detachFragment(localFragment);
          break;
        case 5: 
          localFragment.setNextAnim(enterAnim);
          mManager.showFragment(localFragment);
          break;
        case 4: 
          localFragment.setNextAnim(exitAnim);
          mManager.hideFragment(localFragment);
          break;
        case 3: 
          localFragment.setNextAnim(exitAnim);
          mManager.removeFragment(localFragment);
          break;
        }
      }
      else
      {
        localFragment.setNextAnim(enterAnim);
        mManager.addFragment(localFragment, false);
      }
      if ((!mReorderingAllowed) && (removed != 1) && (localFragment != null)) {
        mManager.moveFragmentToExpectedState(localFragment);
      }
      i += 1;
    }
    if (!mReorderingAllowed) {
      mManager.moveToState(mManager.mCurState, true);
    }
  }
  
  void executePopOps(boolean paramBoolean)
  {
    Object localObject2 = mOps;
    Object localObject1 = this;
    int i = ((ArrayList)localObject2).size() - 1;
    while (i >= 0)
    {
      localObject2 = mOps;
      localObject2 = (Op)((ArrayList)localObject2).get(i);
      Fragment localFragment = fragment;
      if (localFragment != null) {
        localFragment.setNextTransition(FragmentManagerImpl.reverseTransit(mTransition), mTransitionStyle);
      }
      int j = removed;
      if (j != 1)
      {
        switch (j)
        {
        default: 
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append("Unknown cmd: ");
          ((StringBuilder)localObject1).append(removed);
          throw new IllegalArgumentException(((StringBuilder)localObject1).toString());
        case 9: 
          mManager.setPrimaryNavigationFragment(localFragment);
          break;
        case 8: 
          mManager.setPrimaryNavigationFragment(null);
          break;
        case 7: 
          localFragment.setNextAnim(popExitAnim);
          mManager.detachFragment(localFragment);
          break;
        case 6: 
          localFragment.setNextAnim(popEnterAnim);
          mManager.attachFragment(localFragment);
          break;
        case 5: 
          localFragment.setNextAnim(popExitAnim);
          mManager.hideFragment(localFragment);
          break;
        case 4: 
          localFragment.setNextAnim(popEnterAnim);
          mManager.showFragment(localFragment);
          break;
        case 3: 
          localFragment.setNextAnim(popEnterAnim);
          mManager.addFragment(localFragment, false);
          break;
        }
      }
      else
      {
        localFragment.setNextAnim(popExitAnim);
        mManager.removeFragment(localFragment);
      }
      if ((!mReorderingAllowed) && (removed != 3) && (localFragment != null)) {
        mManager.moveFragmentToExpectedState(localFragment);
      }
      i -= 1;
    }
    if ((!mReorderingAllowed) && (paramBoolean)) {
      mManager.moveToState(mManager.mCurState, true);
    }
  }
  
  Fragment expandOps(ArrayList paramArrayList, Fragment paramFragment)
  {
    int i = 0;
    for (Fragment localFragment1 = paramFragment; i < mOps.size(); localFragment1 = paramFragment)
    {
      Op localOp = (Op)mOps.get(i);
      switch (removed)
      {
      default: 
        break;
      case 4: 
      case 5: 
        j = i;
        paramFragment = localFragment1;
        break;
      case 8: 
        mOps.add(i, new Op(9, localFragment1));
        j = i + 1;
        paramFragment = fragment;
        break;
      case 3: 
      case 6: 
        paramArrayList.remove(fragment);
        j = i;
        paramFragment = localFragment1;
        if (fragment != localFragment1) {
          break label485;
        }
        mOps.add(i, new Op(9, fragment));
        j = i + 1;
        paramFragment = null;
        break;
      case 2: 
        Fragment localFragment2 = fragment;
        int i1 = mContainerId;
        j = paramArrayList.size() - 1;
        int k = 0;
        for (paramFragment = localFragment1; j >= 0; paramFragment = localFragment1)
        {
          Fragment localFragment3 = (Fragment)paramArrayList.get(j);
          int m = i;
          int n = k;
          localFragment1 = paramFragment;
          if (mContainerId == i1) {
            if (localFragment3 == localFragment2)
            {
              n = 1;
              m = i;
              localFragment1 = paramFragment;
            }
            else
            {
              m = i;
              localFragment1 = paramFragment;
              if (localFragment3 == paramFragment)
              {
                mOps.add(i, new Op(9, localFragment3));
                m = i + 1;
                localFragment1 = null;
              }
              paramFragment = new Op(3, localFragment3);
              enterAnim = enterAnim;
              popEnterAnim = popEnterAnim;
              exitAnim = exitAnim;
              popExitAnim = popExitAnim;
              mOps.add(m, paramFragment);
              paramArrayList.remove(localFragment3);
              m += 1;
              n = k;
            }
          }
          j -= 1;
          i = m;
          k = n;
        }
        if (k != 0)
        {
          mOps.remove(i);
          i -= 1;
        }
        for (;;)
        {
          break;
          removed = 1;
          paramArrayList.add(localFragment2);
        }
        j = i;
        break;
      }
      paramArrayList.add(fragment);
      paramFragment = localFragment1;
      int j = i;
      label485:
      i = j + 1;
    }
    return localFragment1;
  }
  
  public boolean generateOps(ArrayList paramArrayList1, ArrayList paramArrayList2)
  {
    if (FragmentManagerImpl.DEBUG)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Run: ");
      localStringBuilder.append(this);
      Log.v("FragmentManager", localStringBuilder.toString());
    }
    paramArrayList1.add(this);
    paramArrayList2.add(Boolean.valueOf(false));
    if (mAddToBackStack) {
      mManager.addBackStackState(this);
    }
    return true;
  }
  
  public CharSequence getBreadCrumbShortTitle()
  {
    if (mBreadCrumbShortTitleRes != 0) {
      return mManager.mHost.getContext().getText(mBreadCrumbShortTitleRes);
    }
    return mBreadCrumbShortTitleText;
  }
  
  public int getBreadCrumbShortTitleRes()
  {
    return mBreadCrumbShortTitleRes;
  }
  
  public CharSequence getBreadCrumbTitle()
  {
    if (mBreadCrumbTitleRes != 0) {
      return mManager.mHost.getContext().getText(mBreadCrumbTitleRes);
    }
    return mBreadCrumbTitleText;
  }
  
  public int getBreadCrumbTitleRes()
  {
    return mBreadCrumbTitleRes;
  }
  
  public int getId()
  {
    return mIndex;
  }
  
  public String getName()
  {
    return mName;
  }
  
  public int getTransition()
  {
    return mTransition;
  }
  
  public int getTransitionStyle()
  {
    return mTransitionStyle;
  }
  
  public FragmentTransaction hide(Fragment paramFragment)
  {
    addOp(new Op(4, paramFragment));
    return this;
  }
  
  boolean interactsWith(int paramInt)
  {
    int k = mOps.size();
    int i = 0;
    while (i < k)
    {
      Op localOp = (Op)mOps.get(i);
      int j;
      if (fragment != null) {
        j = fragment.mContainerId;
      } else {
        j = 0;
      }
      if ((j != 0) && (j == paramInt)) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  boolean interactsWith(ArrayList paramArrayList, int paramInt1, int paramInt2)
  {
    if (paramInt2 == paramInt1) {
      return false;
    }
    int i1 = mOps.size();
    int j = 0;
    int m;
    for (int k = -1; j < i1; k = m)
    {
      Object localObject = (Op)mOps.get(j);
      int i;
      if (fragment != null) {
        i = fragment.mContainerId;
      } else {
        i = 0;
      }
      m = k;
      if (i != 0)
      {
        m = k;
        if (i != k)
        {
          k = paramInt1;
          while (k < paramInt2)
          {
            localObject = (BackStackRecord)paramArrayList.get(k);
            int i2 = mOps.size();
            m = 0;
            while (m < i2)
            {
              Op localOp = (Op)mOps.get(m);
              int n;
              if (fragment != null) {
                n = fragment.mContainerId;
              } else {
                n = 0;
              }
              if (n == i) {
                return true;
              }
              m += 1;
            }
            k += 1;
          }
          m = i;
        }
      }
      j += 1;
    }
    return false;
  }
  
  public boolean isAddToBackStackAllowed()
  {
    return mAllowAddToBackStack;
  }
  
  public boolean isEmpty()
  {
    return mOps.isEmpty();
  }
  
  boolean isPostponed()
  {
    int i = 0;
    while (i < mOps.size())
    {
      if (isFragmentPostponed((Op)mOps.get(i))) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public FragmentTransaction remove(Fragment paramFragment)
  {
    addOp(new Op(3, paramFragment));
    return this;
  }
  
  public FragmentTransaction replace(int paramInt, Fragment paramFragment)
  {
    return replace(paramInt, paramFragment, null);
  }
  
  public FragmentTransaction replace(int paramInt, Fragment paramFragment, String paramString)
  {
    if (paramInt != 0)
    {
      doAddOp(paramInt, paramFragment, paramString, 2);
      return this;
    }
    throw new IllegalArgumentException("Must use non-zero containerViewId");
  }
  
  public FragmentTransaction runOnCommit(Runnable paramRunnable)
  {
    if (paramRunnable != null)
    {
      disallowAddToBackStack();
      if (mCommitRunnables == null) {
        mCommitRunnables = new ArrayList();
      }
      mCommitRunnables.add(paramRunnable);
      return this;
    }
    throw new IllegalArgumentException("runnable cannot be null");
  }
  
  public void runOnCommitRunnables()
  {
    if (mCommitRunnables != null)
    {
      int i = 0;
      int j = mCommitRunnables.size();
      while (i < j)
      {
        ((Runnable)mCommitRunnables.get(i)).run();
        i += 1;
      }
      mCommitRunnables = null;
    }
  }
  
  public FragmentTransaction setAllowOptimization(boolean paramBoolean)
  {
    return setReorderingAllowed(paramBoolean);
  }
  
  public FragmentTransaction setBreadCrumbShortTitle(int paramInt)
  {
    mBreadCrumbShortTitleRes = paramInt;
    mBreadCrumbShortTitleText = null;
    return this;
  }
  
  public FragmentTransaction setBreadCrumbShortTitle(CharSequence paramCharSequence)
  {
    mBreadCrumbShortTitleRes = 0;
    mBreadCrumbShortTitleText = paramCharSequence;
    return this;
  }
  
  public FragmentTransaction setBreadCrumbTitle(int paramInt)
  {
    mBreadCrumbTitleRes = paramInt;
    mBreadCrumbTitleText = null;
    return this;
  }
  
  public FragmentTransaction setBreadCrumbTitle(CharSequence paramCharSequence)
  {
    mBreadCrumbTitleRes = 0;
    mBreadCrumbTitleText = paramCharSequence;
    return this;
  }
  
  public FragmentTransaction setCustomAnimations(int paramInt1, int paramInt2)
  {
    return setCustomAnimations(paramInt1, paramInt2, 0, 0);
  }
  
  public FragmentTransaction setCustomAnimations(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    mEnterAnim = paramInt1;
    mExitAnim = paramInt2;
    mPopEnterAnim = paramInt3;
    mPopExitAnim = paramInt4;
    return this;
  }
  
  void setOnStartPostponedListener(Fragment.OnStartEnterTransitionListener paramOnStartEnterTransitionListener)
  {
    int i = 0;
    while (i < mOps.size())
    {
      Op localOp = (Op)mOps.get(i);
      if (isFragmentPostponed(localOp)) {
        fragment.setOnStartEnterTransitionListener(paramOnStartEnterTransitionListener);
      }
      i += 1;
    }
  }
  
  public FragmentTransaction setPrimaryNavigationFragment(Fragment paramFragment)
  {
    addOp(new Op(8, paramFragment));
    return this;
  }
  
  public FragmentTransaction setReorderingAllowed(boolean paramBoolean)
  {
    mReorderingAllowed = paramBoolean;
    return this;
  }
  
  public FragmentTransaction setTransition(int paramInt)
  {
    mTransition = paramInt;
    return this;
  }
  
  public FragmentTransaction setTransitionStyle(int paramInt)
  {
    mTransitionStyle = paramInt;
    return this;
  }
  
  public FragmentTransaction show(Fragment paramFragment)
  {
    addOp(new Op(5, paramFragment));
    return this;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(128);
    localStringBuilder.append("BackStackEntry{");
    localStringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    if (mIndex >= 0)
    {
      localStringBuilder.append(" #");
      localStringBuilder.append(mIndex);
    }
    if (mName != null)
    {
      localStringBuilder.append(" ");
      localStringBuilder.append(mName);
    }
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }
  
  Fragment trackAddedFragmentsInPop(ArrayList paramArrayList, Fragment paramFragment)
  {
    int i = 0;
    while (i < mOps.size())
    {
      Op localOp = (Op)mOps.get(i);
      int j = removed;
      if (j != 1)
      {
        if (j != 3) {}
        switch (j)
        {
        default: 
          break;
        case 9: 
          paramFragment = fragment;
          break;
        case 8: 
          paramFragment = null;
          break;
        case 6: 
          paramArrayList.add(fragment);
          break;
        }
      }
      else
      {
        paramArrayList.remove(fragment);
      }
      i += 1;
    }
    return paramFragment;
  }
  
  final class Op
  {
    int enterAnim;
    int exitAnim;
    Fragment fragment;
    int popEnterAnim;
    int popExitAnim;
    int removed;
    
    Op() {}
    
    Op(Fragment paramFragment)
    {
      removed = this$1;
      fragment = paramFragment;
    }
  }
}
