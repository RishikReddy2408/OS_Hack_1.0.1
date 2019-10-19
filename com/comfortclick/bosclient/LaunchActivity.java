package com.comfortclick.bosclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class LaunchActivity
  extends Activity
{
  public LaunchActivity() {}
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    startActivity(new Intent(this, MainActivity.class));
    finish();
  }
}
