package android.support.v7.widget;

import android.view.View;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

class ViewBoundsCheck
{
  static final int CVE_PVE_POS = 12;
  static final int CVE_PVS_POS = 8;
  static final int CVS_PVE_POS = 4;
  static final int CVS_PVS_POS = 0;
  static final int EQ = 2;
  static final int FLAG_CVE_EQ_PVE = 8192;
  static final int FLAG_CVE_EQ_PVS = 512;
  static final int FLAG_CVE_GT_PVE = 4096;
  static final int FLAG_CVE_GT_PVS = 256;
  static final int FLAG_CVE_LT_PVE = 16384;
  static final int FLAG_CVE_LT_PVS = 1024;
  static final int FLAG_CVS_EQ_PVE = 32;
  static final int FLAG_CVS_EQ_PVS = 2;
  static final int FLAG_CVS_GT_PVE = 16;
  static final int FLAG_CVS_GT_PVS = 1;
  static final int FLAG_CVS_LT_PVE = 64;
  static final int FLAG_CVS_LT_PVS = 4;
  static final int GT = 1;
  static final int LT = 4;
  static final int MASK = 7;
  BoundFlags mBoundFlags;
  final Callback mCallback;
  
  ViewBoundsCheck(Callback paramCallback)
  {
    mCallback = paramCallback;
    mBoundFlags = new BoundFlags();
  }
  
  View findOneViewWithinBoundFlags(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int j = mCallback.getParentStart();
    int k = mCallback.getParentEnd();
    int i;
    if (paramInt2 > paramInt1) {
      i = 1;
    } else {
      i = -1;
    }
    Object localObject2;
    for (Object localObject1 = null; paramInt1 != paramInt2; localObject1 = localObject2)
    {
      View localView = mCallback.getChildAt(paramInt1);
      int m = mCallback.getChildStart(localView);
      int n = mCallback.getChildEnd(localView);
      mBoundFlags.setBounds(j, k, m, n);
      if (paramInt3 != 0)
      {
        mBoundFlags.resetFlags();
        mBoundFlags.addFlags(paramInt3);
        if (mBoundFlags.boundsMatch()) {
          return localView;
        }
      }
      localObject2 = localObject1;
      if (paramInt4 != 0)
      {
        mBoundFlags.resetFlags();
        mBoundFlags.addFlags(paramInt4);
        localObject2 = localObject1;
        if (mBoundFlags.boundsMatch()) {
          localObject2 = localView;
        }
      }
      paramInt1 += i;
    }
    return localObject1;
  }
  
  boolean isViewWithinBoundFlags(View paramView, int paramInt)
  {
    mBoundFlags.setBounds(mCallback.getParentStart(), mCallback.getParentEnd(), mCallback.getChildStart(paramView), mCallback.getChildEnd(paramView));
    if (paramInt != 0)
    {
      mBoundFlags.resetFlags();
      mBoundFlags.addFlags(paramInt);
      return mBoundFlags.boundsMatch();
    }
    return false;
  }
  
  static class BoundFlags
  {
    int mBoundFlags = 0;
    int mChildEnd;
    int mChildStart;
    int mRvEnd;
    int mRvStart;
    
    BoundFlags() {}
    
    void addFlags(int paramInt)
    {
      mBoundFlags = (paramInt | mBoundFlags);
    }
    
    boolean boundsMatch()
    {
      if (((mBoundFlags & 0x7) != 0) && ((mBoundFlags & compare(mChildStart, mRvStart) << 0) == 0)) {
        return false;
      }
      if (((mBoundFlags & 0x70) != 0) && ((mBoundFlags & compare(mChildStart, mRvEnd) << 4) == 0)) {
        return false;
      }
      if (((mBoundFlags & 0x700) != 0) && ((mBoundFlags & compare(mChildEnd, mRvStart) << 8) == 0)) {
        return false;
      }
      return ((mBoundFlags & 0x7000) == 0) || ((mBoundFlags & compare(mChildEnd, mRvEnd) << 12) != 0);
    }
    
    int compare(int paramInt1, int paramInt2)
    {
      if (paramInt1 > paramInt2) {
        return 1;
      }
      if (paramInt1 == paramInt2) {
        return 2;
      }
      return 4;
    }
    
    void resetFlags()
    {
      mBoundFlags = 0;
    }
    
    void setBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      mRvStart = paramInt1;
      mRvEnd = paramInt2;
      mChildStart = paramInt3;
      mChildEnd = paramInt4;
    }
    
    void setFlags(int paramInt1, int paramInt2)
    {
      mBoundFlags = (paramInt1 & paramInt2 | mBoundFlags & (paramInt2 ^ 0xFFFFFFFF));
    }
  }
  
  static abstract interface Callback
  {
    public abstract View getChildAt(int paramInt);
    
    public abstract int getChildCount();
    
    public abstract int getChildEnd(View paramView);
    
    public abstract int getChildStart(View paramView);
    
    public abstract View getParent();
    
    public abstract int getParentEnd();
    
    public abstract int getParentStart();
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface ViewBounds {}
}
