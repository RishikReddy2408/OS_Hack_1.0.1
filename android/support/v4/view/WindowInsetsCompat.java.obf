package android.support.v4.view;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.view.WindowInsets;

public class WindowInsetsCompat
{
  private final Object mInsets;
  
  public WindowInsetsCompat(WindowInsetsCompat paramWindowInsetsCompat)
  {
    int i = Build.VERSION.SDK_INT;
    Object localObject = null;
    if (i >= 20)
    {
      if (paramWindowInsetsCompat == null) {
        paramWindowInsetsCompat = localObject;
      } else {
        paramWindowInsetsCompat = new WindowInsets((WindowInsets)mInsets);
      }
      mInsets = paramWindowInsetsCompat;
      return;
    }
    mInsets = null;
  }
  
  private WindowInsetsCompat(Object paramObject)
  {
    mInsets = paramObject;
  }
  
  static Object unwrap(WindowInsetsCompat paramWindowInsetsCompat)
  {
    if (paramWindowInsetsCompat == null) {
      return null;
    }
    return mInsets;
  }
  
  static WindowInsetsCompat wrap(Object paramObject)
  {
    if (paramObject == null) {
      return null;
    }
    return new WindowInsetsCompat(paramObject);
  }
  
  public WindowInsetsCompat consumeStableInsets()
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return new WindowInsetsCompat(((WindowInsets)mInsets).consumeStableInsets());
    }
    return null;
  }
  
  public WindowInsetsCompat consumeSystemWindowInsets()
  {
    if (Build.VERSION.SDK_INT >= 20) {
      return new WindowInsetsCompat(((WindowInsets)mInsets).consumeSystemWindowInsets());
    }
    return null;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (paramObject != null)
    {
      if (getClass() != paramObject.getClass()) {
        return false;
      }
      paramObject = (WindowInsetsCompat)paramObject;
      if (mInsets == null) {
        return mInsets == null;
      }
      return mInsets.equals(mInsets);
    }
    return false;
  }
  
  public int getStableInsetBottom()
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return ((WindowInsets)mInsets).getStableInsetBottom();
    }
    return 0;
  }
  
  public int getStableInsetLeft()
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return ((WindowInsets)mInsets).getStableInsetLeft();
    }
    return 0;
  }
  
  public int getStableInsetRight()
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return ((WindowInsets)mInsets).getStableInsetRight();
    }
    return 0;
  }
  
  public int getStableInsetTop()
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return ((WindowInsets)mInsets).getStableInsetTop();
    }
    return 0;
  }
  
  public int getSystemWindowInsetBottom()
  {
    if (Build.VERSION.SDK_INT >= 20) {
      return ((WindowInsets)mInsets).getSystemWindowInsetBottom();
    }
    return 0;
  }
  
  public int getSystemWindowInsetLeft()
  {
    if (Build.VERSION.SDK_INT >= 20) {
      return ((WindowInsets)mInsets).getSystemWindowInsetLeft();
    }
    return 0;
  }
  
  public int getSystemWindowInsetRight()
  {
    if (Build.VERSION.SDK_INT >= 20) {
      return ((WindowInsets)mInsets).getSystemWindowInsetRight();
    }
    return 0;
  }
  
  public int getSystemWindowInsetTop()
  {
    if (Build.VERSION.SDK_INT >= 20) {
      return ((WindowInsets)mInsets).getSystemWindowInsetTop();
    }
    return 0;
  }
  
  public boolean hasInsets()
  {
    if (Build.VERSION.SDK_INT >= 20) {
      return ((WindowInsets)mInsets).hasInsets();
    }
    return false;
  }
  
  public boolean hasStableInsets()
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return ((WindowInsets)mInsets).hasStableInsets();
    }
    return false;
  }
  
  public boolean hasSystemWindowInsets()
  {
    if (Build.VERSION.SDK_INT >= 20) {
      return ((WindowInsets)mInsets).hasSystemWindowInsets();
    }
    return false;
  }
  
  public int hashCode()
  {
    if (mInsets == null) {
      return 0;
    }
    return mInsets.hashCode();
  }
  
  public boolean isConsumed()
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return ((WindowInsets)mInsets).isConsumed();
    }
    return false;
  }
  
  public boolean isRound()
  {
    if (Build.VERSION.SDK_INT >= 20) {
      return ((WindowInsets)mInsets).isRound();
    }
    return false;
  }
  
  public WindowInsetsCompat replaceSystemWindowInsets(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (Build.VERSION.SDK_INT >= 20) {
      return new WindowInsetsCompat(((WindowInsets)mInsets).replaceSystemWindowInsets(paramInt1, paramInt2, paramInt3, paramInt4));
    }
    return null;
  }
  
  public WindowInsetsCompat replaceSystemWindowInsets(Rect paramRect)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return new WindowInsetsCompat(((WindowInsets)mInsets).replaceSystemWindowInsets(paramRect));
    }
    return null;
  }
}
