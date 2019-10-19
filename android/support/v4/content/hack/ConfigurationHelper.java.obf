package android.support.v4.content.res;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;

public final class ConfigurationHelper
{
  private ConfigurationHelper() {}
  
  public static int getDensityDpi(@NonNull Resources paramResources)
  {
    if (Build.VERSION.SDK_INT >= 17) {
      return getConfigurationdensityDpi;
    }
    return getDisplayMetricsdensityDpi;
  }
}
