package android.support.v4.view;

import android.os.Build.VERSION;
import android.view.ScaleGestureDetector;

public final class ScaleGestureDetectorCompat
{
  private ScaleGestureDetectorCompat() {}
  
  public static boolean isQuickScaleEnabled(ScaleGestureDetector paramScaleGestureDetector)
  {
    if (Build.VERSION.SDK_INT >= 19) {
      return paramScaleGestureDetector.isQuickScaleEnabled();
    }
    return false;
  }
  
  public static boolean isQuickScaleEnabled(Object paramObject)
  {
    return isQuickScaleEnabled((ScaleGestureDetector)paramObject);
  }
  
  public static void setQuickScaleEnabled(ScaleGestureDetector paramScaleGestureDetector, boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT >= 19) {
      paramScaleGestureDetector.setQuickScaleEnabled(paramBoolean);
    }
  }
  
  public static void setQuickScaleEnabled(Object paramObject, boolean paramBoolean)
  {
    setQuickScaleEnabled((ScaleGestureDetector)paramObject, paramBoolean);
  }
}
