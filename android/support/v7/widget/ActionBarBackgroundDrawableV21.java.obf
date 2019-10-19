package android.support.v7.widget;

import android.graphics.Outline;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

@RequiresApi(21)
class ActionBarBackgroundDrawableV21
  extends ActionBarBackgroundDrawable
{
  public ActionBarBackgroundDrawableV21(ActionBarContainer paramActionBarContainer)
  {
    super(paramActionBarContainer);
  }
  
  public void getOutline(@NonNull Outline paramOutline)
  {
    if (mContainer.mIsSplit)
    {
      if (mContainer.mSplitBackground != null) {
        mContainer.mSplitBackground.getOutline(paramOutline);
      }
    }
    else if (mContainer.mBackground != null) {
      mContainer.mBackground.getOutline(paramOutline);
    }
  }
}
