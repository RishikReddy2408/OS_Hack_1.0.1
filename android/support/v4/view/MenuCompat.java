package android.support.v4.view;

import android.view.MenuItem;

public final class MenuCompat
{
  private MenuCompat() {}
  
  public static void setShowAsAction(MenuItem paramMenuItem, int paramInt)
  {
    paramMenuItem.setShowAsAction(paramInt);
  }
}
