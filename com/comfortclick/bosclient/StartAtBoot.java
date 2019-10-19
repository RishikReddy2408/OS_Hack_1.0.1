package com.comfortclick.bosclient;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class StartAtBoot
  extends BroadcastReceiver
{
  public StartAtBoot() {}
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    try
    {
      boolean bool = PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("pref_enable_panel_mode_key", false);
      if (bool)
      {
        paramIntent = new Intent(paramContext, MainActivity.class);
        paramIntent.addFlags(268435456);
        paramContext.startActivity(paramIntent);
        return;
      }
    }
    catch (Exception paramContext)
    {
      Log.e("cctest", paramContext.toString());
    }
  }
}
