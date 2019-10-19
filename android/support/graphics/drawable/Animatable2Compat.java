package android.support.graphics.drawable;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Animatable2.AnimationCallback;
import android.graphics.drawable.Drawable;

public abstract interface Animatable2Compat
  extends Animatable
{
  public abstract void clearAnimationCallbacks();
  
  public abstract void registerAnimationCallback(AnimationCallback paramAnimationCallback);
  
  public abstract boolean unregisterAnimationCallback(AnimationCallback paramAnimationCallback);
  
  public static abstract class AnimationCallback
  {
    Animatable2.AnimationCallback mPlatformCallback;
    
    public AnimationCallback() {}
    
    Animatable2.AnimationCallback getPlatformCallback()
    {
      if (mPlatformCallback == null) {
        mPlatformCallback = new Animatable2.AnimationCallback()
        {
          public void onAnimationEnd(Drawable paramAnonymousDrawable)
          {
            Animatable2Compat.AnimationCallback.this.onAnimationEnd(paramAnonymousDrawable);
          }
          
          public void onAnimationStart(Drawable paramAnonymousDrawable)
          {
            Animatable2Compat.AnimationCallback.this.onAnimationStart(paramAnonymousDrawable);
          }
        };
      }
      return mPlatformCallback;
    }
    
    public void onAnimationEnd(Drawable paramDrawable) {}
    
    public void onAnimationStart(Drawable paramDrawable) {}
  }
}
