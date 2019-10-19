package android.support.v4.hardware.display;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.Display;
import android.view.WindowManager;
import java.util.WeakHashMap;

public abstract class DisplayManagerCompat
{
  public static final String DISPLAY_CATEGORY_PRESENTATION = "android.hardware.display.category.PRESENTATION";
  private static final WeakHashMap<Context, DisplayManagerCompat> sInstances = new WeakHashMap();
  
  DisplayManagerCompat() {}
  
  @NonNull
  public static DisplayManagerCompat getInstance(@NonNull Context paramContext)
  {
    synchronized (sInstances)
    {
      DisplayManagerCompat localDisplayManagerCompat = (DisplayManagerCompat)sInstances.get(paramContext);
      Object localObject = localDisplayManagerCompat;
      if (localDisplayManagerCompat == null)
      {
        if (Build.VERSION.SDK_INT >= 17) {
          localObject = new DisplayManagerCompatApi17Impl(paramContext);
        } else {
          localObject = new DisplayManagerCompatApi14Impl(paramContext);
        }
        sInstances.put(paramContext, localObject);
      }
      return localObject;
    }
  }
  
  @Nullable
  public abstract Display getDisplay(int paramInt);
  
  @NonNull
  public abstract Display[] getDisplays();
  
  @NonNull
  public abstract Display[] getDisplays(String paramString);
  
  private static class DisplayManagerCompatApi14Impl
    extends DisplayManagerCompat
  {
    private final WindowManager mWindowManager;
    
    DisplayManagerCompatApi14Impl(Context paramContext)
    {
      mWindowManager = ((WindowManager)paramContext.getSystemService("window"));
    }
    
    public Display getDisplay(int paramInt)
    {
      Display localDisplay = mWindowManager.getDefaultDisplay();
      if (localDisplay.getDisplayId() == paramInt) {
        return localDisplay;
      }
      return null;
    }
    
    public Display[] getDisplays()
    {
      return new Display[] { mWindowManager.getDefaultDisplay() };
    }
    
    public Display[] getDisplays(String paramString)
    {
      if (paramString == null) {
        return getDisplays();
      }
      return new Display[0];
    }
  }
  
  @RequiresApi(17)
  private static class DisplayManagerCompatApi17Impl
    extends DisplayManagerCompat
  {
    private final DisplayManager mDisplayManager;
    
    DisplayManagerCompatApi17Impl(Context paramContext)
    {
      mDisplayManager = ((DisplayManager)paramContext.getSystemService("display"));
    }
    
    public Display getDisplay(int paramInt)
    {
      return mDisplayManager.getDisplay(paramInt);
    }
    
    public Display[] getDisplays()
    {
      return mDisplayManager.getDisplays();
    }
    
    public Display[] getDisplays(String paramString)
    {
      return mDisplayManager.getDisplays(paramString);
    }
  }
}
