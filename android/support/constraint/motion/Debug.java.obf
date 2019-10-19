package android.support.constraint.motion;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;

public class Debug
{
  public Debug() {}
  
  public static String getName(Context paramContext, int paramInt)
  {
    try
    {
      paramContext = paramContext.getResources().getResourceEntryName(paramInt);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return "UNKNOWN";
  }
  
  public static String getName(View paramView)
  {
    try
    {
      paramView = paramView.getContext().getResources().getResourceEntryName(paramView.getId());
      return paramView;
    }
    catch (Exception paramView)
    {
      for (;;) {}
    }
    return "UNKNOWN";
  }
  
  public static void logStack(String paramString1, String paramString2, int paramInt)
  {
    Object localObject = new Throwable().getStackTrace();
    String str = " ";
    int i = 1;
    while (i <= paramInt)
    {
      StringBuilder localStringBuilder = localObject[i];
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(str);
      localStringBuilder.append(localObject[i].getFileName());
      localStringBuilder.append(":");
      localStringBuilder.append(localObject[i].getLineNumber());
      localStringBuilder.append(" ");
      str = localStringBuilder.toString();
      i += 1;
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append(paramString2);
    ((StringBuilder)localObject).append(str);
    Log.v(paramString1, ((StringBuilder)localObject).toString());
  }
}
