package android.support.v7.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.lang.reflect.Method;

@RequiresApi(11)
class ActionBarDrawerToggleHoneycomb
{
  private static final String TAG = "ActionBarDrawerToggleHC";
  private static final int[] THEME_ATTRS = { 16843531 };
  
  ActionBarDrawerToggleHoneycomb() {}
  
  public static Drawable getThemeUpIndicator(Activity paramActivity)
  {
    paramActivity = paramActivity.obtainStyledAttributes(THEME_ATTRS);
    Drawable localDrawable = paramActivity.getDrawable(0);
    paramActivity.recycle();
    return localDrawable;
  }
  
  public static SetIndicatorInfo setActionBarDescription(SetIndicatorInfo paramSetIndicatorInfo, Activity paramActivity, int paramInt)
  {
    SetIndicatorInfo localSetIndicatorInfo = paramSetIndicatorInfo;
    if (paramSetIndicatorInfo == null) {
      localSetIndicatorInfo = new SetIndicatorInfo(paramActivity);
    }
    if (setHomeAsUpIndicator != null) {
      try
      {
        paramSetIndicatorInfo = paramActivity.getActionBar();
        paramActivity = setHomeActionContentDescription;
        paramActivity.invoke(paramSetIndicatorInfo, new Object[] { Integer.valueOf(paramInt) });
        if (Build.VERSION.SDK_INT <= 19)
        {
          paramSetIndicatorInfo.setSubtitle(paramSetIndicatorInfo.getSubtitle());
          return localSetIndicatorInfo;
        }
      }
      catch (Exception paramSetIndicatorInfo)
      {
        Log.w("ActionBarDrawerToggleHC", "Couldn't set content description via JB-MR2 API", paramSetIndicatorInfo);
      }
    }
    return localSetIndicatorInfo;
  }
  
  public static SetIndicatorInfo setActionBarUpIndicator(SetIndicatorInfo paramSetIndicatorInfo, Activity paramActivity, Drawable paramDrawable, int paramInt)
  {
    paramSetIndicatorInfo = new SetIndicatorInfo(paramActivity);
    if (setHomeAsUpIndicator != null) {
      try
      {
        paramActivity = paramActivity.getActionBar();
        Method localMethod = setHomeAsUpIndicator;
        localMethod.invoke(paramActivity, new Object[] { paramDrawable });
        paramDrawable = setHomeActionContentDescription;
        paramDrawable.invoke(paramActivity, new Object[] { Integer.valueOf(paramInt) });
        return paramSetIndicatorInfo;
      }
      catch (Exception paramActivity)
      {
        Log.w("ActionBarDrawerToggleHC", "Couldn't set home-as-up indicator via JB-MR2 API", paramActivity);
        return paramSetIndicatorInfo;
      }
    }
    if (upIndicatorView != null)
    {
      upIndicatorView.setImageDrawable(paramDrawable);
      return paramSetIndicatorInfo;
    }
    Log.w("ActionBarDrawerToggleHC", "Couldn't set home-as-up indicator");
    return paramSetIndicatorInfo;
  }
  
  static class SetIndicatorInfo
  {
    public Method setHomeActionContentDescription;
    public Method setHomeAsUpIndicator;
    public ImageView upIndicatorView;
    
    SetIndicatorInfo(Activity paramActivity)
    {
      try
      {
        localObject = ActionBar.class.getDeclaredMethod("setHomeAsUpIndicator", new Class[] { Drawable.class });
        setHomeAsUpIndicator = ((Method)localObject);
        localObject = Integer.TYPE;
        localObject = ActionBar.class.getDeclaredMethod("setHomeActionContentDescription", new Class[] { localObject });
        setHomeActionContentDescription = ((Method)localObject);
        return;
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        Object localObject;
        for (;;) {}
      }
      paramActivity = paramActivity.findViewById(16908332);
      if (paramActivity == null) {
        return;
      }
      localObject = (ViewGroup)paramActivity.getParent();
      if (((ViewGroup)localObject).getChildCount() != 2) {
        return;
      }
      paramActivity = ((ViewGroup)localObject).getChildAt(0);
      localObject = ((ViewGroup)localObject).getChildAt(1);
      if (paramActivity.getId() == 16908332) {
        paramActivity = (Activity)localObject;
      }
      if ((paramActivity instanceof ImageView))
      {
        upIndicatorView = ((ImageView)paramActivity);
        return;
      }
    }
  }
}
