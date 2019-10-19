package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Outline;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.lang.reflect.Method;

@RequiresApi(21)
class WrappedDrawableApi21
  extends WrappedDrawableApi19
{
  private static final String PAGE_KEY = "WrappedDrawableApi21";
  private static Method sIsProjectedDrawableMethod;
  
  WrappedDrawableApi21(Drawable paramDrawable)
  {
    super(paramDrawable);
    findAndCacheIsProjectedDrawableMethod();
  }
  
  WrappedDrawableApi21(WrappedDrawableApi14.DrawableWrapperState paramDrawableWrapperState, Resources paramResources)
  {
    super(paramDrawableWrapperState, paramResources);
    findAndCacheIsProjectedDrawableMethod();
  }
  
  private void findAndCacheIsProjectedDrawableMethod()
  {
    if (sIsProjectedDrawableMethod == null) {
      try
      {
        Method localMethod = Drawable.class.getDeclaredMethod("isProjected", new Class[0]);
        sIsProjectedDrawableMethod = localMethod;
        return;
      }
      catch (Exception localException)
      {
        Log.w("WrappedDrawableApi21", "Failed to retrieve Drawable#isProjected() method", localException);
      }
    }
  }
  
  public Rect getDirtyBounds()
  {
    return mDrawable.getDirtyBounds();
  }
  
  public void getOutline(Outline paramOutline)
  {
    mDrawable.getOutline(paramOutline);
  }
  
  protected boolean isCompatTintEnabled()
  {
    if (Build.VERSION.SDK_INT == 21)
    {
      Drawable localDrawable = mDrawable;
      if (((localDrawable instanceof GradientDrawable)) || ((localDrawable instanceof DrawableContainer)) || ((localDrawable instanceof InsetDrawable)) || ((localDrawable instanceof RippleDrawable))) {
        return true;
      }
    }
    return false;
  }
  
  public boolean isProjected()
  {
    if ((mDrawable != null) && (sIsProjectedDrawableMethod != null))
    {
      Object localObject = sIsProjectedDrawableMethod;
      Drawable localDrawable = mDrawable;
      try
      {
        localObject = ((Method)localObject).invoke(localDrawable, new Object[0]);
        localObject = (Boolean)localObject;
        boolean bool = ((Boolean)localObject).booleanValue();
        return bool;
      }
      catch (Exception localException)
      {
        Log.w("WrappedDrawableApi21", "Error calling Drawable#isProjected() method", localException);
      }
    }
    return false;
  }
  
  WrappedDrawableApi14.DrawableWrapperState mutateConstantState()
  {
    return new DrawableWrapperStateLollipop(mState, null);
  }
  
  public void setHotspot(float paramFloat1, float paramFloat2)
  {
    mDrawable.setHotspot(paramFloat1, paramFloat2);
  }
  
  public void setHotspotBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    mDrawable.setHotspotBounds(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public boolean setState(int[] paramArrayOfInt)
  {
    if (super.setState(paramArrayOfInt))
    {
      invalidateSelf();
      return true;
    }
    return false;
  }
  
  public void setTint(int paramInt)
  {
    if (isCompatTintEnabled())
    {
      super.setTint(paramInt);
      return;
    }
    mDrawable.setTint(paramInt);
  }
  
  public void setTintList(ColorStateList paramColorStateList)
  {
    if (isCompatTintEnabled())
    {
      super.setTintList(paramColorStateList);
      return;
    }
    mDrawable.setTintList(paramColorStateList);
  }
  
  public void setTintMode(PorterDuff.Mode paramMode)
  {
    if (isCompatTintEnabled())
    {
      super.setTintMode(paramMode);
      return;
    }
    mDrawable.setTintMode(paramMode);
  }
  
  private static class DrawableWrapperStateLollipop
    extends WrappedDrawableApi14.DrawableWrapperState
  {
    DrawableWrapperStateLollipop(WrappedDrawableApi14.DrawableWrapperState paramDrawableWrapperState, Resources paramResources)
    {
      super(paramResources);
    }
    
    public Drawable newDrawable(Resources paramResources)
    {
      return new WrappedDrawableApi21(this, paramResources);
    }
  }
}
