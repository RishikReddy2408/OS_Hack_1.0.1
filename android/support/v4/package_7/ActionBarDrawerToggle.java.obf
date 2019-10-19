package android.support.v4.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.InsetDrawable;
import android.os.Build.VERSION;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import java.lang.reflect.Method;

@Deprecated
public class ActionBarDrawerToggle
  implements DrawerLayout.DrawerListener
{
  private static final int ID_HOME = 16908332;
  private static final String TAG = "ActionBarDrawerToggle";
  private static final int[] THEME_ATTRS = { 16843531 };
  private static final float TOGGLE_DRAWABLE_OFFSET = 0.33333334F;
  final Activity mActivity;
  private final Delegate mActivityImpl;
  private final int mCloseDrawerContentDescRes;
  private Drawable mDrawerImage;
  private final int mDrawerImageResource;
  private boolean mDrawerIndicatorEnabled = true;
  private final DrawerLayout mDrawerLayout;
  private boolean mHasCustomUpIndicator;
  private Drawable mHomeAsUpIndicator;
  private final int mOpenDrawerContentDescRes;
  private SetIndicatorInfo mSetIndicatorInfo;
  private SlideDrawable mSlider;
  
  public ActionBarDrawerToggle(Activity paramActivity, DrawerLayout paramDrawerLayout, @DrawableRes int paramInt1, @StringRes int paramInt2, @StringRes int paramInt3)
  {
    this(paramActivity, paramDrawerLayout, assumeMaterial(paramActivity) ^ true, paramInt1, paramInt2, paramInt3);
  }
  
  public ActionBarDrawerToggle(Activity paramActivity, DrawerLayout paramDrawerLayout, boolean paramBoolean, @DrawableRes int paramInt1, @StringRes int paramInt2, @StringRes int paramInt3)
  {
    mActivity = paramActivity;
    if ((paramActivity instanceof DelegateProvider)) {
      mActivityImpl = ((DelegateProvider)paramActivity).getDrawerToggleDelegate();
    } else {
      mActivityImpl = null;
    }
    mDrawerLayout = paramDrawerLayout;
    mDrawerImageResource = paramInt1;
    mOpenDrawerContentDescRes = paramInt2;
    mCloseDrawerContentDescRes = paramInt3;
    mHomeAsUpIndicator = getThemeUpIndicator();
    mDrawerImage = ContextCompat.getDrawable(paramActivity, paramInt1);
    mSlider = new SlideDrawable(mDrawerImage);
    paramActivity = mSlider;
    float f;
    if (paramBoolean) {
      f = 0.33333334F;
    } else {
      f = 0.0F;
    }
    paramActivity.setOffset(f);
  }
  
  private static boolean assumeMaterial(Context paramContext)
  {
    return (getApplicationInfotargetSdkVersion >= 21) && (Build.VERSION.SDK_INT >= 21);
  }
  
  private Drawable getThemeUpIndicator()
  {
    if (mActivityImpl != null) {
      return mActivityImpl.getThemeUpIndicator();
    }
    if (Build.VERSION.SDK_INT >= 18)
    {
      localObject = mActivity.getActionBar();
      if (localObject != null) {
        localObject = ((ActionBar)localObject).getThemedContext();
      } else {
        localObject = mActivity;
      }
      localObject = ((Context)localObject).obtainStyledAttributes(null, THEME_ATTRS, 16843470, 0);
      localDrawable = ((TypedArray)localObject).getDrawable(0);
      ((TypedArray)localObject).recycle();
      return localDrawable;
    }
    Object localObject = mActivity.obtainStyledAttributes(THEME_ATTRS);
    Drawable localDrawable = ((TypedArray)localObject).getDrawable(0);
    ((TypedArray)localObject).recycle();
    return localDrawable;
  }
  
  private void setActionBarDescription(int paramInt)
  {
    if (mActivityImpl != null)
    {
      mActivityImpl.setActionBarDescription(paramInt);
      return;
    }
    ActionBar localActionBar;
    if (Build.VERSION.SDK_INT >= 18)
    {
      localActionBar = mActivity.getActionBar();
      if (localActionBar != null) {
        localActionBar.setHomeActionContentDescription(paramInt);
      }
    }
    else
    {
      if (mSetIndicatorInfo == null) {
        mSetIndicatorInfo = new SetIndicatorInfo(mActivity);
      }
      if (mSetIndicatorInfo.mSetHomeAsUpIndicator != null) {
        try
        {
          localActionBar = mActivity.getActionBar();
          mSetIndicatorInfo.mSetHomeActionContentDescription.invoke(localActionBar, new Object[] { Integer.valueOf(paramInt) });
          localActionBar.setSubtitle(localActionBar.getSubtitle());
          return;
        }
        catch (Exception localException)
        {
          Log.w("ActionBarDrawerToggle", "Couldn't set content description via JB-MR2 API", localException);
        }
      }
    }
  }
  
  private void setActionBarUpIndicator(Drawable paramDrawable, int paramInt)
  {
    if (mActivityImpl != null)
    {
      mActivityImpl.setActionBarUpIndicator(paramDrawable, paramInt);
      return;
    }
    ActionBar localActionBar;
    if (Build.VERSION.SDK_INT >= 18)
    {
      localActionBar = mActivity.getActionBar();
      if (localActionBar != null)
      {
        localActionBar.setHomeAsUpIndicator(paramDrawable);
        localActionBar.setHomeActionContentDescription(paramInt);
      }
    }
    else
    {
      if (mSetIndicatorInfo == null) {
        mSetIndicatorInfo = new SetIndicatorInfo(mActivity);
      }
      if (mSetIndicatorInfo.mSetHomeAsUpIndicator != null) {
        try
        {
          localActionBar = mActivity.getActionBar();
          mSetIndicatorInfo.mSetHomeAsUpIndicator.invoke(localActionBar, new Object[] { paramDrawable });
          mSetIndicatorInfo.mSetHomeActionContentDescription.invoke(localActionBar, new Object[] { Integer.valueOf(paramInt) });
          return;
        }
        catch (Exception paramDrawable)
        {
          Log.w("ActionBarDrawerToggle", "Couldn't set home-as-up indicator via JB-MR2 API", paramDrawable);
          return;
        }
      }
      if (mSetIndicatorInfo.mUpIndicatorView != null)
      {
        mSetIndicatorInfo.mUpIndicatorView.setImageDrawable(paramDrawable);
        return;
      }
      Log.w("ActionBarDrawerToggle", "Couldn't set home-as-up indicator");
    }
  }
  
  public boolean isDrawerIndicatorEnabled()
  {
    return mDrawerIndicatorEnabled;
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    if (!mHasCustomUpIndicator) {
      mHomeAsUpIndicator = getThemeUpIndicator();
    }
    mDrawerImage = ContextCompat.getDrawable(mActivity, mDrawerImageResource);
    syncState();
  }
  
  public void onDrawerClosed(View paramView)
  {
    mSlider.setPosition(0.0F);
    if (mDrawerIndicatorEnabled) {
      setActionBarDescription(mOpenDrawerContentDescRes);
    }
  }
  
  public void onDrawerOpened(View paramView)
  {
    mSlider.setPosition(1.0F);
    if (mDrawerIndicatorEnabled) {
      setActionBarDescription(mCloseDrawerContentDescRes);
    }
  }
  
  public void onDrawerSlide(View paramView, float paramFloat)
  {
    float f = mSlider.getPosition();
    if (paramFloat > 0.5F) {
      paramFloat = Math.max(f, Math.max(0.0F, paramFloat - 0.5F) * 2.0F);
    } else {
      paramFloat = Math.min(f, paramFloat * 2.0F);
    }
    mSlider.setPosition(paramFloat);
  }
  
  public void onDrawerStateChanged(int paramInt) {}
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if ((paramMenuItem != null) && (paramMenuItem.getItemId() == 16908332) && (mDrawerIndicatorEnabled))
    {
      if (mDrawerLayout.isDrawerVisible(8388611)) {
        mDrawerLayout.closeDrawer(8388611);
      } else {
        mDrawerLayout.openDrawer(8388611);
      }
      return true;
    }
    return false;
  }
  
  public void setDrawerIndicatorEnabled(boolean paramBoolean)
  {
    if (paramBoolean != mDrawerIndicatorEnabled)
    {
      if (paramBoolean)
      {
        SlideDrawable localSlideDrawable = mSlider;
        int i;
        if (mDrawerLayout.isDrawerOpen(8388611)) {
          i = mCloseDrawerContentDescRes;
        } else {
          i = mOpenDrawerContentDescRes;
        }
        setActionBarUpIndicator(localSlideDrawable, i);
      }
      else
      {
        setActionBarUpIndicator(mHomeAsUpIndicator, 0);
      }
      mDrawerIndicatorEnabled = paramBoolean;
    }
  }
  
  public void setHomeAsUpIndicator(int paramInt)
  {
    Drawable localDrawable;
    if (paramInt != 0) {
      localDrawable = ContextCompat.getDrawable(mActivity, paramInt);
    } else {
      localDrawable = null;
    }
    setHomeAsUpIndicator(localDrawable);
  }
  
  public void setHomeAsUpIndicator(Drawable paramDrawable)
  {
    if (paramDrawable == null)
    {
      mHomeAsUpIndicator = getThemeUpIndicator();
      mHasCustomUpIndicator = false;
    }
    else
    {
      mHomeAsUpIndicator = paramDrawable;
      mHasCustomUpIndicator = true;
    }
    if (!mDrawerIndicatorEnabled) {
      setActionBarUpIndicator(mHomeAsUpIndicator, 0);
    }
  }
  
  public void syncState()
  {
    if (mDrawerLayout.isDrawerOpen(8388611)) {
      mSlider.setPosition(1.0F);
    } else {
      mSlider.setPosition(0.0F);
    }
    if (mDrawerIndicatorEnabled)
    {
      SlideDrawable localSlideDrawable = mSlider;
      int i;
      if (mDrawerLayout.isDrawerOpen(8388611)) {
        i = mCloseDrawerContentDescRes;
      } else {
        i = mOpenDrawerContentDescRes;
      }
      setActionBarUpIndicator(localSlideDrawable, i);
    }
  }
  
  @Deprecated
  public static abstract interface Delegate
  {
    @Nullable
    public abstract Drawable getThemeUpIndicator();
    
    public abstract void setActionBarDescription(@StringRes int paramInt);
    
    public abstract void setActionBarUpIndicator(Drawable paramDrawable, @StringRes int paramInt);
  }
  
  @Deprecated
  public static abstract interface DelegateProvider
  {
    @Nullable
    public abstract ActionBarDrawerToggle.Delegate getDrawerToggleDelegate();
  }
  
  private static class SetIndicatorInfo
  {
    Method mSetHomeActionContentDescription;
    Method mSetHomeAsUpIndicator;
    ImageView mUpIndicatorView;
    
    SetIndicatorInfo(Activity paramActivity)
    {
      try
      {
        mSetHomeAsUpIndicator = ActionBar.class.getDeclaredMethod("setHomeAsUpIndicator", new Class[] { Drawable.class });
        mSetHomeActionContentDescription = ActionBar.class.getDeclaredMethod("setHomeActionContentDescription", new Class[] { Integer.TYPE });
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
      if ((paramActivity instanceof ImageView)) {
        mUpIndicatorView = ((ImageView)paramActivity);
      }
    }
  }
  
  private class SlideDrawable
    extends InsetDrawable
    implements Drawable.Callback
  {
    private final boolean mHasMirroring;
    private float mOffset;
    private float mPosition;
    private final Rect mTmpRect;
    
    SlideDrawable(Drawable paramDrawable)
    {
      super(0);
      if (Build.VERSION.SDK_INT > 18) {
        bool = true;
      }
      mHasMirroring = bool;
      mTmpRect = new Rect();
    }
    
    public void draw(@NonNull Canvas paramCanvas)
    {
      copyBounds(mTmpRect);
      paramCanvas.save();
      int i = ViewCompat.getLayoutDirection(mActivity.getWindow().getDecorView());
      int j = 1;
      if (i == 1) {
        i = 1;
      } else {
        i = 0;
      }
      if (i != 0) {
        j = -1;
      }
      int k = mTmpRect.width();
      float f1 = -mOffset;
      float f2 = k;
      paramCanvas.translate(f1 * f2 * mPosition * j, 0.0F);
      if ((i != 0) && (!mHasMirroring))
      {
        paramCanvas.translate(f2, 0.0F);
        paramCanvas.scale(-1.0F, 1.0F);
      }
      super.draw(paramCanvas);
      paramCanvas.restore();
    }
    
    public float getPosition()
    {
      return mPosition;
    }
    
    public void setOffset(float paramFloat)
    {
      mOffset = paramFloat;
      invalidateSelf();
    }
    
    public void setPosition(float paramFloat)
    {
      mPosition = paramFloat;
      invalidateSelf();
    }
  }
}
