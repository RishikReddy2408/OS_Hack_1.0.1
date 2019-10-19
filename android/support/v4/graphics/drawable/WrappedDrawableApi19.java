package android.support.v4.graphics.drawable;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.RequiresApi;

@RequiresApi(19)
class WrappedDrawableApi19
  extends WrappedDrawableApi14
{
  WrappedDrawableApi19(Drawable paramDrawable)
  {
    super(paramDrawable);
  }
  
  WrappedDrawableApi19(WrappedDrawableApi14.DrawableWrapperState paramDrawableWrapperState, Resources paramResources)
  {
    super(paramDrawableWrapperState, paramResources);
  }
  
  public boolean isAutoMirrored()
  {
    return mDrawable.isAutoMirrored();
  }
  
  WrappedDrawableApi14.DrawableWrapperState mutateConstantState()
  {
    return new DrawableWrapperStateKitKat(mState, null);
  }
  
  public void setAutoMirrored(boolean paramBoolean)
  {
    mDrawable.setAutoMirrored(paramBoolean);
  }
  
  private static class DrawableWrapperStateKitKat
    extends WrappedDrawableApi14.DrawableWrapperState
  {
    DrawableWrapperStateKitKat(WrappedDrawableApi14.DrawableWrapperState paramDrawableWrapperState, Resources paramResources)
    {
      super(paramResources);
    }
    
    public Drawable newDrawable(Resources paramResources)
    {
      return new WrappedDrawableApi19(this, paramResources);
    }
  }
}
