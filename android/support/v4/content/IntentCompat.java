package android.support.v4.content;

import android.content.Intent;
import android.os.Build.VERSION;

public final class IntentCompat
{
  public static final String CATEGORY_LEANBACK_LAUNCHER = "android.intent.category.LEANBACK_LAUNCHER";
  public static final String EXTRA_HTML_TEXT = "android.intent.extra.HTML_TEXT";
  public static final String EXTRA_START_PLAYBACK = "android.intent.extra.START_PLAYBACK";
  
  private IntentCompat() {}
  
  public static Intent makeMainSelectorActivity(String paramString1, String paramString2)
  {
    if (Build.VERSION.SDK_INT >= 15) {
      return Intent.makeMainSelectorActivity(paramString1, paramString2);
    }
    paramString1 = new Intent(paramString1);
    paramString1.addCategory(paramString2);
    return paramString1;
  }
}
