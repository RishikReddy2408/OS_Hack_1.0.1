package android.support.v7.app;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.support.v4.package_7.FragmentTransaction;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.view.ActionMode;
import android.support.v7.view.ActionMode.Callback;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.SpinnerAdapter;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class ActionBar
{
  public static final int DISPLAY_HOME_AS_UP = 4;
  public static final int DISPLAY_SHOW_CUSTOM = 16;
  public static final int DISPLAY_SHOW_HOME = 2;
  public static final int DISPLAY_SHOW_TITLE = 8;
  public static final int DISPLAY_USE_LOGO = 1;
  @Deprecated
  public static final int NAVIGATION_MODE_LIST = 1;
  @Deprecated
  public static final int NAVIGATION_MODE_STANDARD = 0;
  @Deprecated
  public static final int NAVIGATION_MODE_TABS = 2;
  
  public ActionBar() {}
  
  public abstract void addOnMenuVisibilityListener(OnMenuVisibilityListener paramOnMenuVisibilityListener);
  
  public abstract void addTab(Tab paramTab);
  
  public abstract void addTab(Tab paramTab, int paramInt);
  
  public abstract void addTab(Tab paramTab, int paramInt, boolean paramBoolean);
  
  public abstract void addTab(Tab paramTab, boolean paramBoolean);
  
  public boolean closeOptionsMenu()
  {
    return false;
  }
  
  public boolean collapseActionView()
  {
    return false;
  }
  
  public void dispatchMenuVisibilityChanged(boolean paramBoolean) {}
  
  public abstract View getCustomView();
  
  public abstract int getDisplayOptions();
  
  public float getElevation()
  {
    return 0.0F;
  }
  
  public abstract int getHeight();
  
  public int getHideOffset()
  {
    return 0;
  }
  
  public abstract int getNavigationItemCount();
  
  public abstract int getNavigationMode();
  
  public abstract int getSelectedNavigationIndex();
  
  public abstract Tab getSelectedTab();
  
  public abstract CharSequence getSubtitle();
  
  public abstract Tab getTabAt(int paramInt);
  
  public abstract int getTabCount();
  
  public Context getThemedContext()
  {
    return null;
  }
  
  public abstract CharSequence getTitle();
  
  public abstract void hide();
  
  public boolean invalidateOptionsMenu()
  {
    return false;
  }
  
  public boolean isHideOnContentScrollEnabled()
  {
    return false;
  }
  
  public abstract boolean isShowing();
  
  public boolean isTitleTruncated()
  {
    return false;
  }
  
  public abstract Tab newTab();
  
  public void onConfigurationChanged(Configuration paramConfiguration) {}
  
  void onDestroy() {}
  
  public boolean onKeyShortcut(int paramInt, KeyEvent paramKeyEvent)
  {
    return false;
  }
  
  public boolean onMenuKeyEvent(KeyEvent paramKeyEvent)
  {
    return false;
  }
  
  public boolean openOptionsMenu()
  {
    return false;
  }
  
  public abstract void removeAllTabs();
  
  public abstract void removeOnMenuVisibilityListener(OnMenuVisibilityListener paramOnMenuVisibilityListener);
  
  public abstract void removeTab(Tab paramTab);
  
  public abstract void removeTabAt(int paramInt);
  
  boolean requestFocus()
  {
    return false;
  }
  
  public abstract void selectTab(Tab paramTab);
  
  public abstract void setBackgroundDrawable(Drawable paramDrawable);
  
  public abstract void setCustomView(int paramInt);
  
  public abstract void setCustomView(View paramView);
  
  public abstract void setCustomView(View paramView, LayoutParams paramLayoutParams);
  
  public void setDefaultDisplayHomeAsUpEnabled(boolean paramBoolean) {}
  
  public abstract void setDisplayHomeAsUpEnabled(boolean paramBoolean);
  
  public abstract void setDisplayOptions(int paramInt);
  
  public abstract void setDisplayOptions(int paramInt1, int paramInt2);
  
  public abstract void setDisplayShowCustomEnabled(boolean paramBoolean);
  
  public abstract void setDisplayShowHomeEnabled(boolean paramBoolean);
  
  public abstract void setDisplayShowTitleEnabled(boolean paramBoolean);
  
  public abstract void setDisplayUseLogoEnabled(boolean paramBoolean);
  
  public void setElevation(float paramFloat)
  {
    if (paramFloat == 0.0F) {
      return;
    }
    throw new UnsupportedOperationException("Setting a non-zero elevation is not supported in this action bar configuration.");
  }
  
  public void setHideOffset(int paramInt)
  {
    if (paramInt == 0) {
      return;
    }
    throw new UnsupportedOperationException("Setting an explicit action bar hide offset is not supported in this action bar configuration.");
  }
  
  public void setHideOnContentScrollEnabled(boolean paramBoolean)
  {
    if (!paramBoolean) {
      return;
    }
    throw new UnsupportedOperationException("Hide on content scroll is not supported in this action bar configuration.");
  }
  
  public void setHomeActionContentDescription(int paramInt) {}
  
  public void setHomeActionContentDescription(CharSequence paramCharSequence) {}
  
  public void setHomeAsUpIndicator(int paramInt) {}
  
  public void setHomeAsUpIndicator(Drawable paramDrawable) {}
  
  public void setHomeButtonEnabled(boolean paramBoolean) {}
  
  public abstract void setIcon(int paramInt);
  
  public abstract void setIcon(Drawable paramDrawable);
  
  public abstract void setListNavigationCallbacks(SpinnerAdapter paramSpinnerAdapter, OnNavigationListener paramOnNavigationListener);
  
  public abstract void setLogo(int paramInt);
  
  public abstract void setLogo(Drawable paramDrawable);
  
  public abstract void setNavigationMode(int paramInt);
  
  public abstract void setSelectedNavigationItem(int paramInt);
  
  public void setShowHideAnimationEnabled(boolean paramBoolean) {}
  
  public void setSplitBackgroundDrawable(Drawable paramDrawable) {}
  
  public void setStackedBackgroundDrawable(Drawable paramDrawable) {}
  
  public abstract void setSubtitle(int paramInt);
  
  public abstract void setSubtitle(CharSequence paramCharSequence);
  
  public abstract void setTitle(int paramInt);
  
  public abstract void setTitle(CharSequence paramCharSequence);
  
  public void setWindowTitle(CharSequence paramCharSequence) {}
  
  public abstract void show();
  
  public ActionMode startActionMode(ActionMode.Callback paramCallback)
  {
    return null;
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface DisplayOptions {}
  
  public static class LayoutParams
    extends ViewGroup.MarginLayoutParams
  {
    public int gravity = 0;
    
    public LayoutParams(int paramInt)
    {
      this(-2, -1, paramInt);
    }
    
    public LayoutParams(int paramInt1, int paramInt2)
    {
      super(paramInt2);
      gravity = 8388627;
    }
    
    public LayoutParams(int paramInt1, int paramInt2, int paramInt3)
    {
      super(paramInt2);
      gravity = paramInt3;
    }
    
    public LayoutParams(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
      paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ActionBarLayout);
      gravity = paramContext.getInt(R.styleable.ActionBarLayout_android_layout_gravity, 0);
      paramContext.recycle();
    }
    
    public LayoutParams(LayoutParams paramLayoutParams)
    {
      super();
      gravity = gravity;
    }
    
    public LayoutParams(ViewGroup.LayoutParams paramLayoutParams)
    {
      super();
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface NavigationMode {}
  
  public static abstract interface OnMenuVisibilityListener
  {
    public abstract void onMenuVisibilityChanged(boolean paramBoolean);
  }
  
  @Deprecated
  public static abstract interface OnNavigationListener
  {
    public abstract boolean onNavigationItemSelected(int paramInt, long paramLong);
  }
  
  @Deprecated
  public static abstract class Tab
  {
    public static final int INVALID_POSITION = -1;
    
    public Tab() {}
    
    public abstract CharSequence getContentDescription();
    
    public abstract View getCustomView();
    
    public abstract Drawable getIcon();
    
    public abstract int getPosition();
    
    public abstract Object getTag();
    
    public abstract CharSequence getText();
    
    public abstract void select();
    
    public abstract Tab setContentDescription(int paramInt);
    
    public abstract Tab setContentDescription(CharSequence paramCharSequence);
    
    public abstract Tab setCustomView(int paramInt);
    
    public abstract Tab setCustomView(View paramView);
    
    public abstract Tab setIcon(int paramInt);
    
    public abstract Tab setIcon(Drawable paramDrawable);
    
    public abstract Tab setTabListener(ActionBar.TabListener paramTabListener);
    
    public abstract Tab setTag(Object paramObject);
    
    public abstract Tab setText(int paramInt);
    
    public abstract Tab setText(CharSequence paramCharSequence);
  }
  
  @Deprecated
  public static abstract interface TabListener
  {
    public abstract void onTabReselected(ActionBar.Tab paramTab, FragmentTransaction paramFragmentTransaction);
    
    public abstract void onTabSelected(ActionBar.Tab paramTab, FragmentTransaction paramFragmentTransaction);
    
    public abstract void onTabUnselected(ActionBar.Tab paramTab, FragmentTransaction paramFragmentTransaction);
  }
}
