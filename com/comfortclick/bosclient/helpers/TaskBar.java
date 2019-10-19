package com.comfortclick.bosclient.helpers;

import android.content.Context;
import android.os.Build.VERSION;
import android.util.Log;
import android.widget.Toast;

public class TaskBar
{
  public TaskBar() {}
  
  public static void KillTaskBar(Context paramContext)
  {
    String str = "79";
    if (Build.VERSION.SDK_INT >= 14) {
      str = "42";
    }
    try
    {
      Runtime localRuntime = Runtime.getRuntime();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("service call activity ");
      localStringBuilder.append(str);
      localStringBuilder.append(" s16 com.android.systemui");
      str = localStringBuilder.toString();
      localRuntime.exec(new String[] { "su", "-c", str }).waitFor();
      return;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    Toast.makeText(paramContext, "Root access not available.", 0).show();
  }
  
  public static void ShowTaskBar()
  {
    try
    {
      Runtime localRuntime = Runtime.getRuntime();
      localRuntime.exec(new String[] { "su", "-c", "LD_LIBRARY_PATH=/vendor/lib:/system/lib am startservice -n com.android.systemui/.SystemUIService" }).waitFor();
      return;
    }
    catch (Exception localException)
    {
      Log.d(localException.getMessage(), localException.toString());
    }
  }
}
