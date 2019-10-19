package android.support.v4.package_7;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import java.util.ArrayList;

final class BackStackState
  implements Parcelable
{
  public static final Parcelable.Creator<android.support.v4.app.BackStackState> CREATOR = new Parcelable.Creator()
  {
    public BackStackState createFromParcel(Parcel paramAnonymousParcel)
    {
      return new BackStackState(paramAnonymousParcel);
    }
    
    public BackStackState[] newArray(int paramAnonymousInt)
    {
      return new BackStackState[paramAnonymousInt];
    }
  };
  final int mBreadCrumbShortTitleRes;
  final CharSequence mBreadCrumbShortTitleText;
  final int mBreadCrumbTitleRes;
  final CharSequence mBreadCrumbTitleText;
  final int mIndex;
  final String mName;
  final int[] mOps;
  final boolean mReorderingAllowed;
  final ArrayList<String> mSharedElementSourceNames;
  final ArrayList<String> mSharedElementTargetNames;
  final int mTransition;
  final int mTransitionStyle;
  
  public BackStackState(Parcel paramParcel)
  {
    mOps = paramParcel.createIntArray();
    mTransition = paramParcel.readInt();
    mTransitionStyle = paramParcel.readInt();
    mName = paramParcel.readString();
    mIndex = paramParcel.readInt();
    mBreadCrumbTitleRes = paramParcel.readInt();
    mBreadCrumbTitleText = ((CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(paramParcel));
    mBreadCrumbShortTitleRes = paramParcel.readInt();
    mBreadCrumbShortTitleText = ((CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(paramParcel));
    mSharedElementSourceNames = paramParcel.createStringArrayList();
    mSharedElementTargetNames = paramParcel.createStringArrayList();
    boolean bool;
    if (paramParcel.readInt() != 0) {
      bool = true;
    } else {
      bool = false;
    }
    mReorderingAllowed = bool;
  }
  
  public BackStackState(BackStackRecord paramBackStackRecord)
  {
    Object localObject = mOps;
    int k = ((ArrayList)localObject).size();
    mOps = new int[k * 6];
    if (mAddToBackStack)
    {
      int i = 0;
      int j = 0;
      while (i < k)
      {
        localObject = mOps;
        localObject = (BackStackRecord.Op)((ArrayList)localObject).get(i);
        int[] arrayOfInt = mOps;
        int m = j + 1;
        arrayOfInt[j] = removed;
        arrayOfInt = mOps;
        int n = m + 1;
        if (fragment != null) {
          j = fragment.mIndex;
        } else {
          j = -1;
        }
        arrayOfInt[m] = j;
        arrayOfInt = mOps;
        j = n + 1;
        arrayOfInt[n] = enterAnim;
        arrayOfInt = mOps;
        m = j + 1;
        arrayOfInt[j] = exitAnim;
        arrayOfInt = mOps;
        j = m + 1;
        arrayOfInt[m] = popEnterAnim;
        mOps[j] = popExitAnim;
        i += 1;
        j += 1;
      }
      mTransition = mTransition;
      mTransitionStyle = mTransitionStyle;
      mName = mName;
      mIndex = mIndex;
      mBreadCrumbTitleRes = mBreadCrumbTitleRes;
      mBreadCrumbTitleText = mBreadCrumbTitleText;
      mBreadCrumbShortTitleRes = mBreadCrumbShortTitleRes;
      mBreadCrumbShortTitleText = mBreadCrumbShortTitleText;
      mSharedElementSourceNames = mSharedElementSourceNames;
      mSharedElementTargetNames = mSharedElementTargetNames;
      mReorderingAllowed = mReorderingAllowed;
      return;
    }
    throw new IllegalStateException("Not on back stack");
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public BackStackRecord instantiate(FragmentManagerImpl paramFragmentManagerImpl)
  {
    BackStackRecord localBackStackRecord = new BackStackRecord(paramFragmentManagerImpl);
    int j = 0;
    int i = 0;
    while (j < mOps.length)
    {
      BackStackRecord.Op localOp = new BackStackRecord.Op();
      Object localObject = mOps;
      int k = j + 1;
      removed = localObject[j];
      if (FragmentManagerImpl.DEBUG)
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Instantiate ");
        ((StringBuilder)localObject).append(localBackStackRecord);
        ((StringBuilder)localObject).append(" op #");
        ((StringBuilder)localObject).append(i);
        ((StringBuilder)localObject).append(" base fragment #");
        ((StringBuilder)localObject).append(mOps[k]);
        Log.v("FragmentManager", ((StringBuilder)localObject).toString());
      }
      localObject = mOps;
      j = k + 1;
      k = localObject[k];
      if (k >= 0) {
        fragment = ((Fragment)mActive.get(k));
      } else {
        fragment = null;
      }
      localObject = mOps;
      k = j + 1;
      enterAnim = localObject[j];
      localObject = mOps;
      j = k + 1;
      exitAnim = localObject[k];
      localObject = mOps;
      k = j + 1;
      popEnterAnim = localObject[j];
      popExitAnim = mOps[k];
      mEnterAnim = enterAnim;
      mExitAnim = exitAnim;
      mPopEnterAnim = popEnterAnim;
      mPopExitAnim = popExitAnim;
      localBackStackRecord.addOp(localOp);
      i += 1;
      j = k + 1;
    }
    mTransition = mTransition;
    mTransitionStyle = mTransitionStyle;
    mName = mName;
    mIndex = mIndex;
    mAddToBackStack = true;
    mBreadCrumbTitleRes = mBreadCrumbTitleRes;
    mBreadCrumbTitleText = mBreadCrumbTitleText;
    mBreadCrumbShortTitleRes = mBreadCrumbShortTitleRes;
    mBreadCrumbShortTitleText = mBreadCrumbShortTitleText;
    mSharedElementSourceNames = mSharedElementSourceNames;
    mSharedElementTargetNames = mSharedElementTargetNames;
    mReorderingAllowed = mReorderingAllowed;
    localBackStackRecord.bumpBackStackNesting(1);
    return localBackStackRecord;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e1expr(TypeTransformer.java:496)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:713)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:698)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
  }
}
