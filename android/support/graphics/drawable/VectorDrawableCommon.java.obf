package android.support.graphics.drawable;

import android.content.res.Resources.Theme;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.graphics.drawable.TintAwareDrawable;

abstract class VectorDrawableCommon
  extends Drawable
  implements TintAwareDrawable
{
  Drawable mDelegateDrawable;
  
  VectorDrawableCommon() {}
  
  public void applyTheme(Resources.Theme paramTheme)
  {
    if (mDelegateDrawable != null)
    {
      DrawableCompat.applyTheme(mDelegateDrawable, paramTheme);
      return;
    }
  }
  
  public void clearColorFilter()
  {
    if (mDelegateDrawable != null)
    {
      mDelegateDrawable.clearColorFilter();
      return;
    }
    super.clearColorFilter();
  }
  
  public ColorFilter getColorFilter()
  {
    if (mDelegateDrawable != null) {
      return DrawableCompat.getColorFilter(mDelegateDrawable);
    }
    return null;
  }
  
  public Drawable getCurrent()
  {
    if (mDelegateDrawable != null) {
      return mDelegateDrawable.getCurrent();
    }
    return super.getCurrent();
  }
  
  public int getMinimumHeight()
  {
    if (mDelegateDrawable != null) {
      return mDelegateDrawable.getMinimumHeight();
    }
    return super.getMinimumHeight();
  }
  
  public int getMinimumWidth()
  {
    if (mDelegateDrawable != null) {
      return mDelegateDrawable.getMinimumWidth();
    }
    return super.getMinimumWidth();
  }
  
  public boolean getPadding(Rect paramRect)
  {
    if (mDelegateDrawable != null) {
      return mDelegateDrawable.getPadding(paramRect);
    }
    return super.getPadding(paramRect);
  }
  
  public int[] getState()
  {
    if (mDelegateDrawable != null) {
      return mDelegateDrawable.getState();
    }
    return super.getState();
  }
  
  public Region getTransparentRegion()
  {
    if (mDelegateDrawable != null) {
      return mDelegateDrawable.getTransparentRegion();
    }
    return super.getTransparentRegion();
  }
  
  public void jumpToCurrentState()
  {
    if (mDelegateDrawable != null)
    {
      DrawableCompat.jumpToCurrentState(mDelegateDrawable);
      return;
    }
  }
  
  protected void onBoundsChange(Rect paramRect)
  {
    if (mDelegateDrawable != null)
    {
      mDelegateDrawable.setBounds(paramRect);
      return;
    }
    super.onBoundsChange(paramRect);
  }
  
  protected boolean onLevelChange(int paramInt)
  {
    if (mDelegateDrawable != null) {
      return mDelegateDrawable.setLevel(paramInt);
    }
    return super.onLevelChange(paramInt);
  }
  
  public void setChangingConfigurations(int paramInt)
  {
    if (mDelegateDrawable != null)
    {
      mDelegateDrawable.setChangingConfigurations(paramInt);
      return;
    }
    super.setChangingConfigurations(paramInt);
  }
  
  public void setColorFilter(int paramInt, PorterDuff.Mode paramMode)
  {
    if (mDelegateDrawable != null)
    {
      mDelegateDrawable.setColorFilter(paramInt, paramMode);
      return;
    }
    super.setColorFilter(paramInt, paramMode);
  }
  
  public void setFilterBitmap(boolean paramBoolean)
  {
    if (mDelegateDrawable != null)
    {
      mDelegateDrawable.setFilterBitmap(paramBoolean);
      return;
    }
  }
  
  public void setHotspot(float paramFloat1, float paramFloat2)
  {
    if (mDelegateDrawable != null) {
      DrawableCompat.setHotspot(mDelegateDrawable, paramFloat1, paramFloat2);
    }
  }
  
  public void setHotspotBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (mDelegateDrawable != null)
    {
      DrawableCompat.setHotspotBounds(mDelegateDrawable, paramInt1, paramInt2, paramInt3, paramInt4);
      return;
    }
  }
  
  public boolean setState(int[] paramArrayOfInt)
  {
    if (mDelegateDrawable != null) {
      return mDelegateDrawable.setState(paramArrayOfInt);
    }
    return super.setState(paramArrayOfInt);
  }
}
