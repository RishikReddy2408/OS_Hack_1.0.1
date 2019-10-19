package android.support.v7.view;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.os.Build.VERSION;
import android.support.v7.appcompat.R.style;
import android.view.LayoutInflater;

public class ContextThemeWrapper
  extends ContextWrapper
{
  private LayoutInflater mInflater;
  private Configuration mOverrideConfiguration;
  private Resources mResources;
  private Resources.Theme mTheme;
  private int mThemeResource;
  
  public ContextThemeWrapper()
  {
    super(null);
  }
  
  public ContextThemeWrapper(Context paramContext, int paramInt)
  {
    super(paramContext);
    mThemeResource = paramInt;
  }
  
  public ContextThemeWrapper(Context paramContext, Resources.Theme paramTheme)
  {
    super(paramContext);
    mTheme = paramTheme;
  }
  
  private Resources getResourcesInternal()
  {
    if (mResources == null) {
      if (mOverrideConfiguration == null) {
        mResources = super.getResources();
      } else if (Build.VERSION.SDK_INT >= 17) {
        mResources = createConfigurationContext(mOverrideConfiguration).getResources();
      }
    }
    return mResources;
  }
  
  private void initializeTheme()
  {
    boolean bool;
    if (mTheme == null) {
      bool = true;
    } else {
      bool = false;
    }
    if (bool)
    {
      mTheme = getResources().newTheme();
      Resources.Theme localTheme = getBaseContext().getTheme();
      if (localTheme != null) {
        mTheme.setTo(localTheme);
      }
    }
    onApplyThemeResource(mTheme, mThemeResource, bool);
  }
  
  public void applyOverrideConfiguration(Configuration paramConfiguration)
  {
    if (mResources == null)
    {
      if (mOverrideConfiguration == null)
      {
        mOverrideConfiguration = new Configuration(paramConfiguration);
        return;
      }
      throw new IllegalStateException("Override configuration has already been set");
    }
    throw new IllegalStateException("getResources() or getAssets() has already been called");
  }
  
  protected void attachBaseContext(Context paramContext)
  {
    super.attachBaseContext(paramContext);
  }
  
  public Context createConfigurationContext(Configuration paramConfiguration)
  {
    throw new Error("Unresolved compilation error: Method <android.support.v7.view.ContextThemeWrapper: android.content.Context createConfigurationContext(android.content.res.Configuration)> does not exist!");
  }
  
  public AssetManager getAssets()
  {
    return getResources().getAssets();
  }
  
  public Resources getResources()
  {
    return getResourcesInternal();
  }
  
  public Object getSystemService(String paramString)
  {
    if ("layout_inflater".equals(paramString))
    {
      if (mInflater == null) {
        mInflater = LayoutInflater.from(getBaseContext()).cloneInContext(this);
      }
      return mInflater;
    }
    return getBaseContext().getSystemService(paramString);
  }
  
  public Resources.Theme getTheme()
  {
    if (mTheme != null) {
      return mTheme;
    }
    if (mThemeResource == 0) {
      mThemeResource = R.style.Theme_AppCompat_Light;
    }
    initializeTheme();
    return mTheme;
  }
  
  public int getThemeResId()
  {
    return mThemeResource;
  }
  
  protected void onApplyThemeResource(Resources.Theme paramTheme, int paramInt, boolean paramBoolean)
  {
    paramTheme.applyStyle(paramInt, true);
  }
  
  public void setTheme(int paramInt)
  {
    if (mThemeResource != paramInt)
    {
      mThemeResource = paramInt;
      initializeTheme();
    }
  }
}
