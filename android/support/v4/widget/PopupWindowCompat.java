package android.support.v4.widget;

import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.widget.PopupWindow;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public final class PopupWindowCompat
{
  static final PopupWindowCompatBaseImpl IMPL = new PopupWindowCompatBaseImpl();
  
  static
  {
    if (Build.VERSION.SDK_INT >= 23)
    {
      IMPL = new PopupWindowCompatApi23Impl();
      return;
    }
    if (Build.VERSION.SDK_INT >= 21)
    {
      IMPL = new PopupWindowCompatApi21Impl();
      return;
    }
    if (Build.VERSION.SDK_INT >= 19)
    {
      IMPL = new PopupWindowCompatApi19Impl();
      return;
    }
  }
  
  private PopupWindowCompat() {}
  
  public static boolean getOverlapAnchor(PopupWindow paramPopupWindow)
  {
    return IMPL.getOverlapAnchor(paramPopupWindow);
  }
  
  public static int getWindowLayoutType(PopupWindow paramPopupWindow)
  {
    return IMPL.getWindowLayoutType(paramPopupWindow);
  }
  
  public static void setOverlapAnchor(PopupWindow paramPopupWindow, boolean paramBoolean)
  {
    IMPL.setOverlapAnchor(paramPopupWindow, paramBoolean);
  }
  
  public static void setWindowLayoutType(PopupWindow paramPopupWindow, int paramInt)
  {
    IMPL.setWindowLayoutType(paramPopupWindow, paramInt);
  }
  
  public static void showAsDropDown(PopupWindow paramPopupWindow, View paramView, int paramInt1, int paramInt2, int paramInt3)
  {
    IMPL.showAsDropDown(paramPopupWindow, paramView, paramInt1, paramInt2, paramInt3);
  }
  
  @RequiresApi(19)
  static class PopupWindowCompatApi19Impl
    extends PopupWindowCompat.PopupWindowCompatBaseImpl
  {
    PopupWindowCompatApi19Impl() {}
    
    public void showAsDropDown(PopupWindow paramPopupWindow, View paramView, int paramInt1, int paramInt2, int paramInt3)
    {
      paramPopupWindow.showAsDropDown(paramView, paramInt1, paramInt2, paramInt3);
    }
  }
  
  @RequiresApi(21)
  static class PopupWindowCompatApi21Impl
    extends PopupWindowCompat.PopupWindowCompatApi19Impl
  {
    private static final String TAG = "PopupWindowCompatApi21";
    private static Field sOverlapAnchorField;
    
    static
    {
      try
      {
        Field localField = PopupWindow.class.getDeclaredField("mOverlapAnchor");
        sOverlapAnchorField = localField;
        localField = sOverlapAnchorField;
        localField.setAccessible(true);
        return;
      }
      catch (NoSuchFieldException localNoSuchFieldException)
      {
        Log.i("PopupWindowCompatApi21", "Could not fetch mOverlapAnchor field from PopupWindow", localNoSuchFieldException);
      }
    }
    
    PopupWindowCompatApi21Impl() {}
    
    public boolean getOverlapAnchor(PopupWindow paramPopupWindow)
    {
      if (sOverlapAnchorField != null)
      {
        Field localField = sOverlapAnchorField;
        try
        {
          paramPopupWindow = localField.get(paramPopupWindow);
          paramPopupWindow = (Boolean)paramPopupWindow;
          boolean bool = paramPopupWindow.booleanValue();
          return bool;
        }
        catch (IllegalAccessException paramPopupWindow)
        {
          Log.i("PopupWindowCompatApi21", "Could not get overlap anchor field in PopupWindow", paramPopupWindow);
        }
      }
      return false;
    }
    
    public void setOverlapAnchor(PopupWindow paramPopupWindow, boolean paramBoolean)
    {
      if (sOverlapAnchorField != null)
      {
        Field localField = sOverlapAnchorField;
        try
        {
          localField.set(paramPopupWindow, Boolean.valueOf(paramBoolean));
          return;
        }
        catch (IllegalAccessException paramPopupWindow)
        {
          Log.i("PopupWindowCompatApi21", "Could not set overlap anchor field in PopupWindow", paramPopupWindow);
        }
      }
    }
  }
  
  @RequiresApi(23)
  static class PopupWindowCompatApi23Impl
    extends PopupWindowCompat.PopupWindowCompatApi21Impl
  {
    PopupWindowCompatApi23Impl() {}
    
    public boolean getOverlapAnchor(PopupWindow paramPopupWindow)
    {
      return paramPopupWindow.getOverlapAnchor();
    }
    
    public int getWindowLayoutType(PopupWindow paramPopupWindow)
    {
      return paramPopupWindow.getWindowLayoutType();
    }
    
    public void setOverlapAnchor(PopupWindow paramPopupWindow, boolean paramBoolean)
    {
      paramPopupWindow.setOverlapAnchor(paramBoolean);
    }
    
    public void setWindowLayoutType(PopupWindow paramPopupWindow, int paramInt)
    {
      paramPopupWindow.setWindowLayoutType(paramInt);
    }
  }
  
  static class PopupWindowCompatBaseImpl
  {
    private static Method sGetWindowLayoutTypeMethod;
    private static boolean sGetWindowLayoutTypeMethodAttempted;
    private static Method sSetWindowLayoutTypeMethod;
    private static boolean sSetWindowLayoutTypeMethodAttempted;
    
    PopupWindowCompatBaseImpl() {}
    
    public boolean getOverlapAnchor(PopupWindow paramPopupWindow)
    {
      return false;
    }
    
    public int getWindowLayoutType(PopupWindow paramPopupWindow)
    {
      if (!sGetWindowLayoutTypeMethodAttempted) {}
      try
      {
        localMethod = PopupWindow.class.getDeclaredMethod("getWindowLayoutType", new Class[0]);
        sGetWindowLayoutTypeMethod = localMethod;
        localMethod = sGetWindowLayoutTypeMethod;
        localMethod.setAccessible(true);
      }
      catch (Exception localException)
      {
        Method localMethod;
        for (;;) {}
      }
      sGetWindowLayoutTypeMethodAttempted = true;
      if (sGetWindowLayoutTypeMethod != null) {
        localMethod = sGetWindowLayoutTypeMethod;
      }
      try
      {
        paramPopupWindow = localMethod.invoke(paramPopupWindow, new Object[0]);
        paramPopupWindow = (Integer)paramPopupWindow;
        int i = paramPopupWindow.intValue();
        return i;
      }
      catch (Exception paramPopupWindow) {}
      return 0;
      return 0;
    }
    
    public void setOverlapAnchor(PopupWindow paramPopupWindow, boolean paramBoolean) {}
    
    public void setWindowLayoutType(PopupWindow paramPopupWindow, int paramInt)
    {
      Object localObject;
      if (!sSetWindowLayoutTypeMethodAttempted) {
        localObject = Integer.TYPE;
      }
      try
      {
        localObject = PopupWindow.class.getDeclaredMethod("setWindowLayoutType", new Class[] { localObject });
        sSetWindowLayoutTypeMethod = (Method)localObject;
        localObject = sSetWindowLayoutTypeMethod;
        ((Method)localObject).setAccessible(true);
      }
      catch (Exception localException)
      {
        for (;;)
        {
          try
          {
            ((Method)localObject).invoke(paramPopupWindow, new Object[] { Integer.valueOf(paramInt) });
            return;
          }
          catch (Exception paramPopupWindow) {}
          localException = localException;
        }
      }
      sSetWindowLayoutTypeMethodAttempted = true;
      if (sSetWindowLayoutTypeMethod != null) {
        localObject = sSetWindowLayoutTypeMethod;
      }
    }
    
    public void showAsDropDown(PopupWindow paramPopupWindow, View paramView, int paramInt1, int paramInt2, int paramInt3)
    {
      int i = paramInt1;
      if ((GravityCompat.getAbsoluteGravity(paramInt3, ViewCompat.getLayoutDirection(paramView)) & 0x7) == 5) {
        i = paramInt1 - (paramPopupWindow.getWidth() - paramView.getWidth());
      }
      paramPopupWindow.showAsDropDown(paramView, i, paramInt2);
    }
  }
}
