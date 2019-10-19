package android.support.v7.widget;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.annotation.RestrictTo;
import android.support.v7.appcompat.R.dimen;
import android.support.v7.appcompat.R.id;
import android.support.v7.appcompat.R.layout;
import android.support.v7.appcompat.R.style;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
class TooltipPopup
{
  private static final String TAG = "TooltipPopup";
  private final View mContentView;
  private final Context mContext;
  private final WindowManager.LayoutParams mLayoutParams = new WindowManager.LayoutParams();
  private final TextView mMessageView;
  private final int[] mTmpAnchorPos = new int[2];
  private final int[] mTmpAppPos = new int[2];
  private final Rect mTmpDisplayFrame = new Rect();
  
  TooltipPopup(Context paramContext)
  {
    mContext = paramContext;
    mContentView = LayoutInflater.from(mContext).inflate(R.layout.abc_tooltip, null);
    mMessageView = ((TextView)mContentView.findViewById(R.id.message));
    mLayoutParams.setTitle(getClass().getSimpleName());
    mLayoutParams.packageName = mContext.getPackageName();
    mLayoutParams.type = 1002;
    mLayoutParams.width = -2;
    mLayoutParams.height = -2;
    mLayoutParams.format = -3;
    mLayoutParams.windowAnimations = R.style.Animation_AppCompat_Tooltip;
    mLayoutParams.flags = 24;
  }
  
  private void computePosition(View paramView, int paramInt1, int paramInt2, boolean paramBoolean, WindowManager.LayoutParams paramLayoutParams)
  {
    token = paramView.getApplicationWindowToken();
    int i = mContext.getResources().getDimensionPixelOffset(R.dimen.tooltip_precise_anchor_threshold);
    if (paramView.getWidth() < i) {
      paramInt1 = paramView.getWidth() / 2;
    }
    int j;
    if (paramView.getHeight() >= i)
    {
      j = mContext.getResources().getDimensionPixelOffset(R.dimen.tooltip_precise_anchor_extra_offset);
      i = paramInt2 + j;
      j = paramInt2 - j;
      paramInt2 = i;
      i = j;
    }
    else
    {
      paramInt2 = paramView.getHeight();
      i = 0;
    }
    gravity = 49;
    Object localObject1 = mContext.getResources();
    if (paramBoolean) {
      j = R.dimen.tooltip_y_offset_touch;
    } else {
      j = R.dimen.tooltip_y_offset_non_touch;
    }
    int k = ((Resources)localObject1).getDimensionPixelOffset(j);
    localObject1 = getAppRootView(paramView);
    if (localObject1 == null)
    {
      Log.e("TooltipPopup", "Cannot find app view");
      return;
    }
    ((View)localObject1).getWindowVisibleDisplayFrame(mTmpDisplayFrame);
    if ((mTmpDisplayFrame.left < 0) && (mTmpDisplayFrame.top < 0))
    {
      Object localObject2 = mContext.getResources();
      j = ((Resources)localObject2).getIdentifier("status_bar_height", "dimen", "android");
      if (j != 0) {
        j = ((Resources)localObject2).getDimensionPixelSize(j);
      } else {
        j = 0;
      }
      localObject2 = ((Resources)localObject2).getDisplayMetrics();
      mTmpDisplayFrame.set(0, j, widthPixels, heightPixels);
    }
    ((View)localObject1).getLocationOnScreen(mTmpAppPos);
    paramView.getLocationOnScreen(mTmpAnchorPos);
    paramView = mTmpAnchorPos;
    paramView[0] -= mTmpAppPos[0];
    paramView = mTmpAnchorPos;
    paramView[1] -= mTmpAppPos[1];
    x = (mTmpAnchorPos[0] + paramInt1 - ((View)localObject1).getWidth() / 2);
    paramInt1 = View.MeasureSpec.makeMeasureSpec(0, 0);
    mContentView.measure(paramInt1, paramInt1);
    paramInt1 = mContentView.getMeasuredHeight();
    i = mTmpAnchorPos[1] + i - k - paramInt1;
    paramInt2 = mTmpAnchorPos[1] + paramInt2 + k;
    if (paramBoolean)
    {
      if (i >= 0)
      {
        y = i;
        return;
      }
      y = paramInt2;
      return;
    }
    if (paramInt1 + paramInt2 <= mTmpDisplayFrame.height())
    {
      y = paramInt2;
      return;
    }
    y = i;
  }
  
  private static View getAppRootView(View paramView)
  {
    View localView = paramView.getRootView();
    ViewGroup.LayoutParams localLayoutParams = localView.getLayoutParams();
    if (((localLayoutParams instanceof WindowManager.LayoutParams)) && (type == 2)) {
      return localView;
    }
    for (paramView = paramView.getContext(); (paramView instanceof ContextWrapper); paramView = ((ContextWrapper)paramView).getBaseContext()) {
      if ((paramView instanceof Activity)) {
        return ((Activity)paramView).getWindow().getDecorView();
      }
    }
    return localView;
  }
  
  void hide()
  {
    if (!isShowing()) {
      return;
    }
    ((WindowManager)mContext.getSystemService("window")).removeView(mContentView);
  }
  
  boolean isShowing()
  {
    return mContentView.getParent() != null;
  }
  
  void show(View paramView, int paramInt1, int paramInt2, boolean paramBoolean, CharSequence paramCharSequence)
  {
    if (isShowing()) {
      hide();
    }
    mMessageView.setText(paramCharSequence);
    computePosition(paramView, paramInt1, paramInt2, paramBoolean, mLayoutParams);
    ((WindowManager)mContext.getSystemService("window")).addView(mContentView, mLayoutParams);
  }
}
