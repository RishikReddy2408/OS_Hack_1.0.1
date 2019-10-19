package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

public class LinearSmoothScroller
  extends RecyclerView.SmoothScroller
{
  private static final boolean DEBUG = false;
  private static final float MILLISECONDS_PER_INCH = 25.0F;
  public static final int SNAP_TO_ANY = 0;
  public static final int SNAP_TO_END = 1;
  public static final int SNAP_TO_START = -1;
  private static final String TAG = "LinearSmoothScroller";
  private static final float TARGET_SEEK_EXTRA_SCROLL_RATIO = 1.2F;
  private static final int TARGET_SEEK_SCROLL_DISTANCE_PX = 10000;
  private final float MILLISECONDS_PER_PX = calculateSpeedPerPixel(paramContext.getResources().getDisplayMetrics());
  protected final DecelerateInterpolator mDecelerateInterpolator = new DecelerateInterpolator();
  protected int mInterimTargetDx = 0;
  protected int mInterimTargetDy = 0;
  protected final LinearInterpolator mLinearInterpolator = new LinearInterpolator();
  protected PointF mTargetVector;
  
  public LinearSmoothScroller(Context paramContext) {}
  
  private int clampApplyScroll(int paramInt1, int paramInt2)
  {
    paramInt2 = paramInt1 - paramInt2;
    if (paramInt1 * paramInt2 <= 0) {
      return 0;
    }
    return paramInt2;
  }
  
  public int calculateDtToFit(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    switch (paramInt5)
    {
    default: 
      throw new IllegalArgumentException("snap preference should be one of the constants defined in SmoothScroller, starting with SNAP_");
    case 1: 
      return paramInt4 - paramInt2;
    case 0: 
      paramInt1 = paramInt3 - paramInt1;
      if (paramInt1 > 0) {
        return paramInt1;
      }
      paramInt1 = paramInt4 - paramInt2;
      if (paramInt1 < 0) {
        return paramInt1;
      }
      return 0;
    }
    return paramInt3 - paramInt1;
  }
  
  public int calculateDxToMakeVisible(View paramView, int paramInt)
  {
    RecyclerView.LayoutManager localLayoutManager = getLayoutManager();
    if ((localLayoutManager != null) && (localLayoutManager.canScrollHorizontally()))
    {
      RecyclerView.LayoutParams localLayoutParams = (RecyclerView.LayoutParams)paramView.getLayoutParams();
      return calculateDtToFit(localLayoutManager.getDecoratedLeft(paramView) - leftMargin, localLayoutManager.getDecoratedRight(paramView) + rightMargin, localLayoutManager.getPaddingLeft(), localLayoutManager.getWidth() - localLayoutManager.getPaddingRight(), paramInt);
    }
    return 0;
  }
  
  public int calculateDyToMakeVisible(View paramView, int paramInt)
  {
    RecyclerView.LayoutManager localLayoutManager = getLayoutManager();
    if ((localLayoutManager != null) && (localLayoutManager.canScrollVertically()))
    {
      RecyclerView.LayoutParams localLayoutParams = (RecyclerView.LayoutParams)paramView.getLayoutParams();
      return calculateDtToFit(localLayoutManager.getDecoratedTop(paramView) - topMargin, localLayoutManager.getDecoratedBottom(paramView) + bottomMargin, localLayoutManager.getPaddingTop(), localLayoutManager.getHeight() - localLayoutManager.getPaddingBottom(), paramInt);
    }
    return 0;
  }
  
  protected float calculateSpeedPerPixel(DisplayMetrics paramDisplayMetrics)
  {
    return 25.0F / densityDpi;
  }
  
  protected int calculateTimeForDeceleration(int paramInt)
  {
    double d = calculateTimeForScrolling(paramInt);
    Double.isNaN(d);
    return (int)Math.ceil(d / 0.3356D);
  }
  
  protected int calculateTimeForScrolling(int paramInt)
  {
    return (int)Math.ceil(Math.abs(paramInt) * MILLISECONDS_PER_PX);
  }
  
  public PointF computeScrollVectorForPosition(int paramInt)
  {
    Object localObject = getLayoutManager();
    if ((localObject instanceof RecyclerView.SmoothScroller.ScrollVectorProvider)) {
      return ((RecyclerView.SmoothScroller.ScrollVectorProvider)localObject).computeScrollVectorForPosition(paramInt);
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("You should override computeScrollVectorForPosition when the LayoutManager does not implement ");
    ((StringBuilder)localObject).append(RecyclerView.SmoothScroller.ScrollVectorProvider.class.getCanonicalName());
    Log.w("LinearSmoothScroller", ((StringBuilder)localObject).toString());
    return null;
  }
  
  protected int getHorizontalSnapPreference()
  {
    if ((mTargetVector != null) && (mTargetVector.x != 0.0F))
    {
      if (mTargetVector.x > 0.0F) {
        return 1;
      }
      return -1;
    }
    return 0;
  }
  
  protected int getVerticalSnapPreference()
  {
    if ((mTargetVector != null) && (mTargetVector.y != 0.0F))
    {
      if (mTargetVector.y > 0.0F) {
        return 1;
      }
      return -1;
    }
    return 0;
  }
  
  protected void onSeekTargetStep(int paramInt1, int paramInt2, RecyclerView.State paramState, RecyclerView.SmoothScroller.Action paramAction)
  {
    if (getChildCount() == 0)
    {
      stop();
      return;
    }
    mInterimTargetDx = clampApplyScroll(mInterimTargetDx, paramInt1);
    mInterimTargetDy = clampApplyScroll(mInterimTargetDy, paramInt2);
    if ((mInterimTargetDx == 0) && (mInterimTargetDy == 0)) {
      updateActionForInterimTarget(paramAction);
    }
  }
  
  protected void onStart() {}
  
  protected void onStop()
  {
    mInterimTargetDy = 0;
    mInterimTargetDx = 0;
    mTargetVector = null;
  }
  
  protected void onTargetFound(View paramView, RecyclerView.State paramState, RecyclerView.SmoothScroller.Action paramAction)
  {
    int i = calculateDxToMakeVisible(paramView, getHorizontalSnapPreference());
    int j = calculateDyToMakeVisible(paramView, getVerticalSnapPreference());
    int k = calculateTimeForDeceleration((int)Math.sqrt(i * i + j * j));
    if (k > 0) {
      paramAction.update(-i, -j, k, mDecelerateInterpolator);
    }
  }
  
  protected void updateActionForInterimTarget(RecyclerView.SmoothScroller.Action paramAction)
  {
    PointF localPointF = computeScrollVectorForPosition(getTargetPosition());
    if ((localPointF != null) && ((x != 0.0F) || (y != 0.0F)))
    {
      normalize(localPointF);
      mTargetVector = localPointF;
      mInterimTargetDx = ((int)(x * 10000.0F));
      mInterimTargetDy = ((int)(y * 10000.0F));
      int i = calculateTimeForScrolling(10000);
      paramAction.update((int)(mInterimTargetDx * 1.2F), (int)(mInterimTargetDy * 1.2F), (int)(i * 1.2F), mLinearInterpolator);
      return;
    }
    paramAction.jumpTo(getTargetPosition());
    stop();
  }
}
