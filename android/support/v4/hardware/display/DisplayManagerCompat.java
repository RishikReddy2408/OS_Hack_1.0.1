package android.support.v4.hardware.display;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.support.annotation.RequiresApi;
import android.view.Display;
import android.view.WindowManager;
import java.util.WeakHashMap;

public abstract class DisplayManagerCompat
{
  public static final String DISPLAY_CATEGORY_PRESENTATION = "android.hardware.display.category.PRESENTATION";
  private static final WeakHashMap<Context, DisplayManagerCompat> sInstances = new WeakHashMap();
  
  DisplayManagerCompat() {}
  
  public static DisplayManagerCompat getInstance(Context paramContext)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: fail exe a4 = a3\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:92)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.dfs(Cfg.java:255)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze0(BaseAnalyze.java:75)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze(BaseAnalyze.java:69)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer.transform(UnSSATransformer.java:274)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:163)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\nCaused by: java.lang.NullPointerException\n");
  }
  
  public abstract Display getDisplay(int paramInt);
  
  public abstract Display[] getDisplays();
  
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
