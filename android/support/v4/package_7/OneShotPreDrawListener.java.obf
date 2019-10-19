package android.support.v4.app;

import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;

class OneShotPreDrawListener
  implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener
{
  private final Runnable mRunnable;
  private final View mView;
  private ViewTreeObserver mViewTreeObserver;
  
  private OneShotPreDrawListener(View paramView, Runnable paramRunnable)
  {
    mView = paramView;
    mViewTreeObserver = paramView.getViewTreeObserver();
    mRunnable = paramRunnable;
  }
  
  public static OneShotPreDrawListener add(View paramView, Runnable paramRunnable)
  {
    paramRunnable = new OneShotPreDrawListener(paramView, paramRunnable);
    paramView.getViewTreeObserver().addOnPreDrawListener(paramRunnable);
    paramView.addOnAttachStateChangeListener(paramRunnable);
    return paramRunnable;
  }
  
  public boolean onPreDraw()
  {
    removeListener();
    mRunnable.run();
    return true;
  }
  
  public void onViewAttachedToWindow(View paramView)
  {
    mViewTreeObserver = paramView.getViewTreeObserver();
  }
  
  public void onViewDetachedFromWindow(View paramView)
  {
    removeListener();
  }
  
  public void removeListener()
  {
    if (mViewTreeObserver.isAlive()) {
      mViewTreeObserver.removeOnPreDrawListener(this);
    } else {
      mView.getViewTreeObserver().removeOnPreDrawListener(this);
    }
    mView.removeOnAttachStateChangeListener(this);
  }
}
