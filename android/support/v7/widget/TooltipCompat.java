package android.support.v7.widget;

import android.os.Build.VERSION;
import android.view.View;

public class TooltipCompat
{
  private TooltipCompat() {}
  
  public static void setTooltipText(View paramView, CharSequence paramCharSequence)
  {
    if (Build.VERSION.SDK_INT >= 26)
    {
      paramView.setTooltipText(paramCharSequence);
      return;
    }
    TooltipCompatHandler.setTooltipText(paramView, paramCharSequence);
  }
}
