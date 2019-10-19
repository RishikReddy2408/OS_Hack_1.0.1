package android.support.v4.os;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.UserManager;

public class UserManagerCompat
{
  private UserManagerCompat() {}
  
  public static boolean isUserUnlocked(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 24) {
      return ((UserManager)paramContext.getSystemService(UserManager.class)).isUserUnlocked();
    }
    return true;
  }
}
