package android.support.v4.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.os.Build.VERSION;
import android.util.Log;
import android.util.TypedValue;
import android.view.ViewConfiguration;
import java.lang.reflect.Method;

public final class ViewConfigurationCompat
{
  private static final String EVENTLOG_URL = "ViewConfigCompat";
  private static Method sGetScaledScrollFactorMethod;
  
  static
  {
    if (Build.VERSION.SDK_INT == 25)
    {
      try
      {
        Method localMethod = ViewConfiguration.class.getDeclaredMethod("getScaledScrollFactor", new Class[0]);
        sGetScaledScrollFactorMethod = localMethod;
        return;
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
      Log.i("ViewConfigCompat", "Could not find method getScaledScrollFactor() on ViewConfiguration");
      return;
    }
  }
  
  private ViewConfigurationCompat() {}
  
  private static float getLegacyScrollFactor(ViewConfiguration paramViewConfiguration, Context paramContext)
  {
    Method localMethod;
    if ((Build.VERSION.SDK_INT >= 25) && (sGetScaledScrollFactorMethod != null)) {
      localMethod = sGetScaledScrollFactorMethod;
    }
    try
    {
      paramViewConfiguration = localMethod.invoke(paramViewConfiguration, new Object[0]);
      paramViewConfiguration = (Integer)paramViewConfiguration;
      int i = paramViewConfiguration.intValue();
      return i;
    }
    catch (Exception paramViewConfiguration)
    {
      for (;;) {}
    }
    Log.i("ViewConfigCompat", "Could not find method getScaledScrollFactor() on ViewConfiguration");
    paramViewConfiguration = new TypedValue();
    if (paramContext.getTheme().resolveAttribute(16842829, paramViewConfiguration, true)) {
      return paramViewConfiguration.getDimension(paramContext.getResources().getDisplayMetrics());
    }
    return 0.0F;
  }
  
  public static float getScaledHorizontalScrollFactor(ViewConfiguration paramViewConfiguration, Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 26) {
      return paramViewConfiguration.getScaledHorizontalScrollFactor();
    }
    return getLegacyScrollFactor(paramViewConfiguration, paramContext);
  }
  
  public static int getScaledPagingTouchSlop(ViewConfiguration paramViewConfiguration)
  {
    return paramViewConfiguration.getScaledPagingTouchSlop();
  }
  
  public static float getScaledVerticalScrollFactor(ViewConfiguration paramViewConfiguration, Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 26) {
      return paramViewConfiguration.getScaledVerticalScrollFactor();
    }
    return getLegacyScrollFactor(paramViewConfiguration, paramContext);
  }
  
  public static boolean hasPermanentMenuKey(ViewConfiguration paramViewConfiguration)
  {
    return paramViewConfiguration.hasPermanentMenuKey();
  }
}
