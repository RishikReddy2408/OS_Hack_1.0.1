package android.support.v7.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class TintContextWrapper
  extends ContextWrapper
{
  private static final Object CACHE_LOCK = new Object();
  private static ArrayList<WeakReference<TintContextWrapper>> sCache;
  private final Resources mResources;
  private final Resources.Theme mTheme;
  
  private TintContextWrapper(@NonNull Context paramContext)
  {
    super(paramContext);
    if (VectorEnabledTintResources.shouldBeUsed())
    {
      mResources = new VectorEnabledTintResources(this, paramContext.getResources());
      mTheme = mResources.newTheme();
      mTheme.setTo(paramContext.getTheme());
      return;
    }
    mResources = new TintResources(this, paramContext.getResources());
    mTheme = null;
  }
  
  private static boolean shouldWrap(@NonNull Context paramContext)
  {
    boolean bool2 = paramContext instanceof TintContextWrapper;
    boolean bool1 = false;
    if ((!bool2) && (!(paramContext.getResources() instanceof TintResources)))
    {
      if ((paramContext.getResources() instanceof VectorEnabledTintResources)) {
        return false;
      }
      if ((Build.VERSION.SDK_INT < 21) || (VectorEnabledTintResources.shouldBeUsed())) {
        bool1 = true;
      }
      return bool1;
    }
    return false;
  }
  
  public static Context wrap(@NonNull Context paramContext)
  {
    if (shouldWrap(paramContext)) {}
    for (;;)
    {
      int i;
      synchronized (CACHE_LOCK)
      {
        if (sCache == null)
        {
          sCache = new ArrayList();
        }
        else
        {
          i = sCache.size() - 1;
          if (i >= 0)
          {
            localObject1 = (WeakReference)sCache.get(i);
            if ((localObject1 != null) && (((WeakReference)localObject1).get() != null)) {
              break label168;
            }
            sCache.remove(i);
            break label168;
          }
          i = sCache.size() - 1;
          if (i >= 0)
          {
            localObject1 = (WeakReference)sCache.get(i);
            if (localObject1 == null) {
              break label175;
            }
            localObject1 = (TintContextWrapper)((WeakReference)localObject1).get();
            if ((localObject1 == null) || (((TintContextWrapper)localObject1).getBaseContext() != paramContext)) {
              break label180;
            }
            return localObject1;
          }
        }
        paramContext = new TintContextWrapper(paramContext);
        sCache.add(new WeakReference(paramContext));
        return paramContext;
      }
      return paramContext;
      label168:
      i -= 1;
      continue;
      label175:
      Object localObject1 = null;
      continue;
      label180:
      i -= 1;
    }
  }
  
  public AssetManager getAssets()
  {
    return mResources.getAssets();
  }
  
  public Resources getResources()
  {
    return mResources;
  }
  
  public Resources.Theme getTheme()
  {
    if (mTheme == null) {
      return super.getTheme();
    }
    return mTheme;
  }
  
  public void setTheme(int paramInt)
  {
    if (mTheme == null)
    {
      super.setTheme(paramInt);
      return;
    }
    mTheme.applyStyle(paramInt, true);
  }
}
