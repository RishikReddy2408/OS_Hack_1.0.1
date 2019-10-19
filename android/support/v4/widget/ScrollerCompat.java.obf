package android.support.v4.widget;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.OverScroller;

@Deprecated
public final class ScrollerCompat
{
  OverScroller mScroller;
  
  ScrollerCompat(Context paramContext, Interpolator paramInterpolator)
  {
    if (paramInterpolator != null) {
      paramContext = new OverScroller(paramContext, paramInterpolator);
    } else {
      paramContext = new OverScroller(paramContext);
    }
    mScroller = paramContext;
  }
  
  @Deprecated
  public static ScrollerCompat create(Context paramContext)
  {
    return create(paramContext, null);
  }
  
  @Deprecated
  public static ScrollerCompat create(Context paramContext, Interpolator paramInterpolator)
  {
    return new ScrollerCompat(paramContext, paramInterpolator);
  }
  
  @Deprecated
  public void abortAnimation()
  {
    mScroller.abortAnimation();
  }
  
  @Deprecated
  public boolean computeScrollOffset()
  {
    return mScroller.computeScrollOffset();
  }
  
  @Deprecated
  public void fling(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8)
  {
    mScroller.fling(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8);
  }
  
  @Deprecated
  public void fling(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10)
  {
    mScroller.fling(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8, paramInt9, paramInt10);
  }
  
  @Deprecated
  public float getCurrVelocity()
  {
    return mScroller.getCurrVelocity();
  }
  
  @Deprecated
  public int getCurrX()
  {
    return mScroller.getCurrX();
  }
  
  @Deprecated
  public int getCurrY()
  {
    return mScroller.getCurrY();
  }
  
  @Deprecated
  public int getFinalX()
  {
    return mScroller.getFinalX();
  }
  
  @Deprecated
  public int getFinalY()
  {
    return mScroller.getFinalY();
  }
  
  @Deprecated
  public boolean isFinished()
  {
    return mScroller.isFinished();
  }
  
  @Deprecated
  public boolean isOverScrolled()
  {
    return mScroller.isOverScrolled();
  }
  
  @Deprecated
  public void notifyHorizontalEdgeReached(int paramInt1, int paramInt2, int paramInt3)
  {
    mScroller.notifyHorizontalEdgeReached(paramInt1, paramInt2, paramInt3);
  }
  
  @Deprecated
  public void notifyVerticalEdgeReached(int paramInt1, int paramInt2, int paramInt3)
  {
    mScroller.notifyVerticalEdgeReached(paramInt1, paramInt2, paramInt3);
  }
  
  @Deprecated
  public boolean springBack(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    return mScroller.springBack(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6);
  }
  
  @Deprecated
  public void startScroll(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    mScroller.startScroll(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  @Deprecated
  public void startScroll(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    mScroller.startScroll(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
  }
}
