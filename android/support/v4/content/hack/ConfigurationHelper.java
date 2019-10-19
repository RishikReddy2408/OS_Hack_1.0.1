package android.support.v4.content.hack;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;

public final class ConfigurationHelper
{
  private ConfigurationHelper() {}
  
  public static int getDensityDpi(Resources paramResources)
  {
    if (Build.VERSION.SDK_INT >= 17) {
      return getConfigurationdensityDpi;
    }
    return getDisplayMetricsdensityDpi;
  }
}
