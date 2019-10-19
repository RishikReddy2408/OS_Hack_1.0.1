package android.support.v4.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.os.BuildCompat;
import android.text.Editable;
import android.util.Log;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class TextViewCompat
{
  public static final int AUTO_SIZE_TEXT_TYPE_NONE = 0;
  public static final int AUTO_SIZE_TEXT_TYPE_UNIFORM = 1;
  static final TextViewCompatBaseImpl IMPL = new TextViewCompatBaseImpl();
  
  static
  {
    if (BuildCompat.isAtLeastOMR1())
    {
      IMPL = new TextViewCompatApi27Impl();
      return;
    }
    if (Build.VERSION.SDK_INT >= 26)
    {
      IMPL = new TextViewCompatApi26Impl();
      return;
    }
    if (Build.VERSION.SDK_INT >= 23)
    {
      IMPL = new TextViewCompatApi23Impl();
      return;
    }
    if (Build.VERSION.SDK_INT >= 18)
    {
      IMPL = new TextViewCompatApi18Impl();
      return;
    }
    if (Build.VERSION.SDK_INT >= 17)
    {
      IMPL = new TextViewCompatApi17Impl();
      return;
    }
    if (Build.VERSION.SDK_INT >= 16)
    {
      IMPL = new TextViewCompatApi16Impl();
      return;
    }
  }
  
  private TextViewCompat() {}
  
  public static int getAutoSizeMaxTextSize(TextView paramTextView)
  {
    return IMPL.getAutoSizeMaxTextSize(paramTextView);
  }
  
  public static int getAutoSizeMinTextSize(TextView paramTextView)
  {
    return IMPL.getAutoSizeMinTextSize(paramTextView);
  }
  
  public static int getAutoSizeStepGranularity(TextView paramTextView)
  {
    return IMPL.getAutoSizeStepGranularity(paramTextView);
  }
  
  public static int[] getAutoSizeTextAvailableSizes(TextView paramTextView)
  {
    return IMPL.getAutoSizeTextAvailableSizes(paramTextView);
  }
  
  public static int getAutoSizeTextType(TextView paramTextView)
  {
    return IMPL.getAutoSizeTextType(paramTextView);
  }
  
  public static Drawable[] getCompoundDrawablesRelative(TextView paramTextView)
  {
    return IMPL.getCompoundDrawablesRelative(paramTextView);
  }
  
  public static int getMaxLines(TextView paramTextView)
  {
    return IMPL.getMaxLines(paramTextView);
  }
  
  public static int getMinLines(TextView paramTextView)
  {
    return IMPL.getMinLines(paramTextView);
  }
  
  public static void setAutoSizeTextTypeUniformWithConfiguration(TextView paramTextView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    throws IllegalArgumentException
  {
    IMPL.setAutoSizeTextTypeUniformWithConfiguration(paramTextView, paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public static void setAutoSizeTextTypeUniformWithPresetSizes(TextView paramTextView, int[] paramArrayOfInt, int paramInt)
    throws IllegalArgumentException
  {
    IMPL.setAutoSizeTextTypeUniformWithPresetSizes(paramTextView, paramArrayOfInt, paramInt);
  }
  
  public static void setAutoSizeTextTypeWithDefaults(TextView paramTextView, int paramInt)
  {
    IMPL.setAutoSizeTextTypeWithDefaults(paramTextView, paramInt);
  }
  
  public static void setCompoundDrawablesRelative(TextView paramTextView, Drawable paramDrawable1, Drawable paramDrawable2, Drawable paramDrawable3, Drawable paramDrawable4)
  {
    IMPL.setCompoundDrawablesRelative(paramTextView, paramDrawable1, paramDrawable2, paramDrawable3, paramDrawable4);
  }
  
  public static void setCompoundDrawablesRelativeWithIntrinsicBounds(TextView paramTextView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    IMPL.setCompoundDrawablesRelativeWithIntrinsicBounds(paramTextView, paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public static void setCompoundDrawablesRelativeWithIntrinsicBounds(TextView paramTextView, Drawable paramDrawable1, Drawable paramDrawable2, Drawable paramDrawable3, Drawable paramDrawable4)
  {
    IMPL.setCompoundDrawablesRelativeWithIntrinsicBounds(paramTextView, paramDrawable1, paramDrawable2, paramDrawable3, paramDrawable4);
  }
  
  public static void setCustomSelectionActionModeCallback(TextView paramTextView, ActionMode.Callback paramCallback)
  {
    IMPL.setCustomSelectionActionModeCallback(paramTextView, paramCallback);
  }
  
  public static void setTextAppearance(TextView paramTextView, int paramInt)
  {
    IMPL.setTextAppearance(paramTextView, paramInt);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface AutoSizeTextType {}
  
  @RequiresApi(16)
  static class TextViewCompatApi16Impl
    extends TextViewCompat.TextViewCompatBaseImpl
  {
    TextViewCompatApi16Impl() {}
    
    public int getMaxLines(TextView paramTextView)
    {
      return paramTextView.getMaxLines();
    }
    
    public int getMinLines(TextView paramTextView)
    {
      return paramTextView.getMinLines();
    }
  }
  
  @RequiresApi(17)
  static class TextViewCompatApi17Impl
    extends TextViewCompat.TextViewCompatApi16Impl
  {
    TextViewCompatApi17Impl() {}
    
    public Drawable[] getCompoundDrawablesRelative(TextView paramTextView)
    {
      int j = paramTextView.getLayoutDirection();
      int i = 1;
      if (j != 1) {
        i = 0;
      }
      paramTextView = paramTextView.getCompoundDrawables();
      if (i != 0)
      {
        Object localObject1 = paramTextView[2];
        Object localObject2 = paramTextView[0];
        paramTextView[0] = localObject1;
        paramTextView[2] = localObject2;
      }
      return paramTextView;
    }
    
    public void setCompoundDrawablesRelative(TextView paramTextView, Drawable paramDrawable1, Drawable paramDrawable2, Drawable paramDrawable3, Drawable paramDrawable4)
    {
      int j = paramTextView.getLayoutDirection();
      int i = 1;
      if (j != 1) {
        i = 0;
      }
      Drawable localDrawable;
      if (i != 0) {
        localDrawable = paramDrawable3;
      } else {
        localDrawable = paramDrawable1;
      }
      if (i == 0) {
        paramDrawable1 = paramDrawable3;
      }
      paramTextView.setCompoundDrawables(localDrawable, paramDrawable2, paramDrawable1, paramDrawable4);
    }
    
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(TextView paramTextView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      int j = paramTextView.getLayoutDirection();
      int i = 1;
      if (j != 1) {
        i = 0;
      }
      if (i != 0) {
        j = paramInt3;
      } else {
        j = paramInt1;
      }
      if (i == 0) {
        paramInt1 = paramInt3;
      }
      paramTextView.setCompoundDrawablesWithIntrinsicBounds(j, paramInt2, paramInt1, paramInt4);
    }
    
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(TextView paramTextView, Drawable paramDrawable1, Drawable paramDrawable2, Drawable paramDrawable3, Drawable paramDrawable4)
    {
      int j = paramTextView.getLayoutDirection();
      int i = 1;
      if (j != 1) {
        i = 0;
      }
      Drawable localDrawable;
      if (i != 0) {
        localDrawable = paramDrawable3;
      } else {
        localDrawable = paramDrawable1;
      }
      if (i == 0) {
        paramDrawable1 = paramDrawable3;
      }
      paramTextView.setCompoundDrawablesWithIntrinsicBounds(localDrawable, paramDrawable2, paramDrawable1, paramDrawable4);
    }
  }
  
  @RequiresApi(18)
  static class TextViewCompatApi18Impl
    extends TextViewCompat.TextViewCompatApi17Impl
  {
    TextViewCompatApi18Impl() {}
    
    public Drawable[] getCompoundDrawablesRelative(TextView paramTextView)
    {
      return paramTextView.getCompoundDrawablesRelative();
    }
    
    public void setCompoundDrawablesRelative(TextView paramTextView, Drawable paramDrawable1, Drawable paramDrawable2, Drawable paramDrawable3, Drawable paramDrawable4)
    {
      paramTextView.setCompoundDrawablesRelative(paramDrawable1, paramDrawable2, paramDrawable3, paramDrawable4);
    }
    
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(TextView paramTextView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      paramTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(paramInt1, paramInt2, paramInt3, paramInt4);
    }
    
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(TextView paramTextView, Drawable paramDrawable1, Drawable paramDrawable2, Drawable paramDrawable3, Drawable paramDrawable4)
    {
      paramTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(paramDrawable1, paramDrawable2, paramDrawable3, paramDrawable4);
    }
  }
  
  @RequiresApi(23)
  static class TextViewCompatApi23Impl
    extends TextViewCompat.TextViewCompatApi18Impl
  {
    TextViewCompatApi23Impl() {}
    
    public void setTextAppearance(TextView paramTextView, int paramInt)
    {
      paramTextView.setTextAppearance(paramInt);
    }
  }
  
  @RequiresApi(26)
  static class TextViewCompatApi26Impl
    extends TextViewCompat.TextViewCompatApi23Impl
  {
    TextViewCompatApi26Impl() {}
    
    public void setCustomSelectionActionModeCallback(final TextView paramTextView, final ActionMode.Callback paramCallback)
    {
      if ((Build.VERSION.SDK_INT != 26) && (Build.VERSION.SDK_INT != 27))
      {
        super.setCustomSelectionActionModeCallback(paramTextView, paramCallback);
        return;
      }
      paramTextView.setCustomSelectionActionModeCallback(new ActionMode.Callback()
      {
        private static final int MENU_ITEM_ORDER_PROCESS_TEXT_INTENT_ACTIONS_START = 100;
        private boolean mCanUseMenuBuilderReferences;
        private boolean mInitializedMenuBuilderReferences = false;
        private Class mMenuBuilderClass;
        private Method mMenuBuilderRemoveItemAtMethod;
        
        private Intent createProcessTextIntent()
        {
          return new Intent().setAction("android.intent.action.PROCESS_TEXT").setType("text/plain");
        }
        
        private Intent createProcessTextIntentForResolveInfo(ResolveInfo paramAnonymousResolveInfo, TextView paramAnonymousTextView)
        {
          return createProcessTextIntent().putExtra("android.intent.extra.PROCESS_TEXT_READONLY", isEditable(paramAnonymousTextView) ^ true).setClassName(activityInfo.packageName, activityInfo.name);
        }
        
        private List getSupportedActivities(Context paramAnonymousContext, PackageManager paramAnonymousPackageManager)
        {
          ArrayList localArrayList = new ArrayList();
          if (!(paramAnonymousContext instanceof Activity)) {
            return localArrayList;
          }
          paramAnonymousPackageManager = paramAnonymousPackageManager.queryIntentActivities(createProcessTextIntent(), 0).iterator();
          while (paramAnonymousPackageManager.hasNext())
          {
            ResolveInfo localResolveInfo = (ResolveInfo)paramAnonymousPackageManager.next();
            if (isSupportedActivity(localResolveInfo, paramAnonymousContext)) {
              localArrayList.add(localResolveInfo);
            }
          }
          return localArrayList;
        }
        
        private boolean isEditable(TextView paramAnonymousTextView)
        {
          return ((paramAnonymousTextView instanceof Editable)) && (paramAnonymousTextView.onCheckIsTextEditor()) && (paramAnonymousTextView.isEnabled());
        }
        
        private boolean isSupportedActivity(ResolveInfo paramAnonymousResolveInfo, Context paramAnonymousContext)
        {
          if (paramAnonymousContext.getPackageName().equals(activityInfo.packageName)) {
            return true;
          }
          if (!activityInfo.exported) {
            return false;
          }
          if (activityInfo.permission != null) {
            return paramAnonymousContext.checkSelfPermission(activityInfo.permission) == 0;
          }
          return true;
        }
        
        private void recomputeProcessTextMenuItems(Menu paramAnonymousMenu)
        {
          Object localObject2 = paramTextView.getContext();
          PackageManager localPackageManager = ((Context)localObject2).getPackageManager();
          if (!mInitializedMenuBuilderReferences) {
            mInitializedMenuBuilderReferences = true;
          }
          try
          {
            localObject1 = Class.forName("com.android.internal.view.menu.MenuBuilder");
            mMenuBuilderClass = ((Class)localObject1);
            localObject1 = mMenuBuilderClass;
            localObject3 = Integer.TYPE;
            localObject1 = ((Class)localObject1).getDeclaredMethod("removeItemAt", new Class[] { localObject3 });
            mMenuBuilderRemoveItemAtMethod = ((Method)localObject1);
            mCanUseMenuBuilderReferences = true;
          }
          catch (ClassNotFoundException localClassNotFoundException)
          {
            for (;;)
            {
              try
              {
                Object localObject3;
                boolean bool = ((Class)localObject1).isInstance(paramAnonymousMenu);
                if (bool)
                {
                  localObject1 = mMenuBuilderRemoveItemAtMethod;
                }
                else
                {
                  localObject1 = paramAnonymousMenu.getClass();
                  localObject3 = Integer.TYPE;
                  localObject1 = ((Class)localObject1).getDeclaredMethod("removeItemAt", new Class[] { localObject3 });
                }
                int i = paramAnonymousMenu.size();
                i -= 1;
                if (i >= 0)
                {
                  localObject3 = paramAnonymousMenu.getItem(i);
                  Intent localIntent = ((MenuItem)localObject3).getIntent();
                  if (localIntent != null)
                  {
                    bool = "android.intent.action.PROCESS_TEXT".equals(((MenuItem)localObject3).getIntent().getAction());
                    if (bool) {
                      ((Method)localObject1).invoke(paramAnonymousMenu, new Object[] { Integer.valueOf(i) });
                    }
                  }
                  i -= 1;
                  continue;
                }
                Object localObject1 = getSupportedActivities((Context)localObject2, localPackageManager);
                i = 0;
                if (i < ((List)localObject1).size())
                {
                  localObject2 = (ResolveInfo)((List)localObject1).get(i);
                  paramAnonymousMenu.add(0, 0, i + 100, ((ResolveInfo)localObject2).loadLabel(localPackageManager)).setIntent(createProcessTextIntentForResolveInfo((ResolveInfo)localObject2, paramTextView)).setShowAsAction(1);
                  i += 1;
                  continue;
                }
                return;
              }
              catch (NoSuchMethodException paramAnonymousMenu)
              {
                return;
              }
              catch (IllegalAccessException paramAnonymousMenu)
              {
                return;
              }
              catch (InvocationTargetException paramAnonymousMenu) {}
              localClassNotFoundException = localClassNotFoundException;
            }
          }
          catch (NoSuchMethodException localNoSuchMethodException)
          {
            for (;;) {}
          }
          mMenuBuilderClass = null;
          mMenuBuilderRemoveItemAtMethod = null;
          mCanUseMenuBuilderReferences = false;
          if (mCanUseMenuBuilderReferences) {
            localObject1 = mMenuBuilderClass;
          }
        }
        
        public boolean onActionItemClicked(ActionMode paramAnonymousActionMode, MenuItem paramAnonymousMenuItem)
        {
          return paramCallback.onActionItemClicked(paramAnonymousActionMode, paramAnonymousMenuItem);
        }
        
        public boolean onCreateActionMode(ActionMode paramAnonymousActionMode, Menu paramAnonymousMenu)
        {
          return paramCallback.onCreateActionMode(paramAnonymousActionMode, paramAnonymousMenu);
        }
        
        public void onDestroyActionMode(ActionMode paramAnonymousActionMode)
        {
          paramCallback.onDestroyActionMode(paramAnonymousActionMode);
        }
        
        public boolean onPrepareActionMode(ActionMode paramAnonymousActionMode, Menu paramAnonymousMenu)
        {
          recomputeProcessTextMenuItems(paramAnonymousMenu);
          return paramCallback.onPrepareActionMode(paramAnonymousActionMode, paramAnonymousMenu);
        }
      });
    }
  }
  
  @RequiresApi(27)
  static class TextViewCompatApi27Impl
    extends TextViewCompat.TextViewCompatApi26Impl
  {
    TextViewCompatApi27Impl() {}
    
    public int getAutoSizeMaxTextSize(TextView paramTextView)
    {
      return paramTextView.getAutoSizeMaxTextSize();
    }
    
    public int getAutoSizeMinTextSize(TextView paramTextView)
    {
      return paramTextView.getAutoSizeMinTextSize();
    }
    
    public int getAutoSizeStepGranularity(TextView paramTextView)
    {
      return paramTextView.getAutoSizeStepGranularity();
    }
    
    public int[] getAutoSizeTextAvailableSizes(TextView paramTextView)
    {
      return paramTextView.getAutoSizeTextAvailableSizes();
    }
    
    public int getAutoSizeTextType(TextView paramTextView)
    {
      return paramTextView.getAutoSizeTextType();
    }
    
    public void setAutoSizeTextTypeUniformWithConfiguration(TextView paramTextView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
      throws IllegalArgumentException
    {
      paramTextView.setAutoSizeTextTypeUniformWithConfiguration(paramInt1, paramInt2, paramInt3, paramInt4);
    }
    
    public void setAutoSizeTextTypeUniformWithPresetSizes(TextView paramTextView, int[] paramArrayOfInt, int paramInt)
      throws IllegalArgumentException
    {
      paramTextView.setAutoSizeTextTypeUniformWithPresetSizes(paramArrayOfInt, paramInt);
    }
    
    public void setAutoSizeTextTypeWithDefaults(TextView paramTextView, int paramInt)
    {
      paramTextView.setAutoSizeTextTypeWithDefaults(paramInt);
    }
  }
  
  static class TextViewCompatBaseImpl
  {
    private static final int LINES = 1;
    private static final String LOG_TAG = "TextViewCompatBase";
    private static Field sMaxModeField;
    private static boolean sMaxModeFieldFetched;
    private static Field sMaximumField;
    private static boolean sMaximumFieldFetched;
    private static Field sMinModeField;
    private static boolean sMinModeFieldFetched;
    private static Field sMinimumField;
    private static boolean sMinimumFieldFetched;
    
    TextViewCompatBaseImpl() {}
    
    private static Field retrieveField(String paramString)
    {
      try
      {
        localObject2 = TextView.class.getDeclaredField(paramString);
        localObject1 = localObject2;
      }
      catch (NoSuchFieldException localNoSuchFieldException1)
      {
        Object localObject2;
        Object localObject1;
        label18:
        for (;;) {}
      }
      try
      {
        ((Field)localObject2).setAccessible(true);
        return localObject2;
      }
      catch (NoSuchFieldException localNoSuchFieldException2)
      {
        break label18;
      }
      localObject1 = null;
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("Could not retrieve ");
      ((StringBuilder)localObject2).append(paramString);
      ((StringBuilder)localObject2).append(" field.");
      Log.e("TextViewCompatBase", ((StringBuilder)localObject2).toString());
      return localObject1;
    }
    
    private static int retrieveIntFromField(Field paramField, TextView paramTextView)
    {
      try
      {
        int i = paramField.getInt(paramTextView);
        return i;
      }
      catch (IllegalAccessException paramTextView)
      {
        for (;;) {}
      }
      paramTextView = new StringBuilder();
      paramTextView.append("Could not retrieve value of ");
      paramTextView.append(paramField.getName());
      paramTextView.append(" field.");
      Log.d("TextViewCompatBase", paramTextView.toString());
      return -1;
    }
    
    public int getAutoSizeMaxTextSize(TextView paramTextView)
    {
      if ((paramTextView instanceof AutoSizeableTextView)) {
        return ((AutoSizeableTextView)paramTextView).getAutoSizeMaxTextSize();
      }
      return -1;
    }
    
    public int getAutoSizeMinTextSize(TextView paramTextView)
    {
      if ((paramTextView instanceof AutoSizeableTextView)) {
        return ((AutoSizeableTextView)paramTextView).getAutoSizeMinTextSize();
      }
      return -1;
    }
    
    public int getAutoSizeStepGranularity(TextView paramTextView)
    {
      if ((paramTextView instanceof AutoSizeableTextView)) {
        return ((AutoSizeableTextView)paramTextView).getAutoSizeStepGranularity();
      }
      return -1;
    }
    
    public int[] getAutoSizeTextAvailableSizes(TextView paramTextView)
    {
      if ((paramTextView instanceof AutoSizeableTextView)) {
        return ((AutoSizeableTextView)paramTextView).getAutoSizeTextAvailableSizes();
      }
      return new int[0];
    }
    
    public int getAutoSizeTextType(TextView paramTextView)
    {
      if ((paramTextView instanceof AutoSizeableTextView)) {
        return ((AutoSizeableTextView)paramTextView).getAutoSizeTextType();
      }
      return 0;
    }
    
    public Drawable[] getCompoundDrawablesRelative(TextView paramTextView)
    {
      return paramTextView.getCompoundDrawables();
    }
    
    public int getMaxLines(TextView paramTextView)
    {
      if (!sMaxModeFieldFetched)
      {
        sMaxModeField = retrieveField("mMaxMode");
        sMaxModeFieldFetched = true;
      }
      if ((sMaxModeField != null) && (retrieveIntFromField(sMaxModeField, paramTextView) == 1))
      {
        if (!sMaximumFieldFetched)
        {
          sMaximumField = retrieveField("mMaximum");
          sMaximumFieldFetched = true;
        }
        if (sMaximumField != null) {
          return retrieveIntFromField(sMaximumField, paramTextView);
        }
      }
      return -1;
    }
    
    public int getMinLines(TextView paramTextView)
    {
      if (!sMinModeFieldFetched)
      {
        sMinModeField = retrieveField("mMinMode");
        sMinModeFieldFetched = true;
      }
      if ((sMinModeField != null) && (retrieveIntFromField(sMinModeField, paramTextView) == 1))
      {
        if (!sMinimumFieldFetched)
        {
          sMinimumField = retrieveField("mMinimum");
          sMinimumFieldFetched = true;
        }
        if (sMinimumField != null) {
          return retrieveIntFromField(sMinimumField, paramTextView);
        }
      }
      return -1;
    }
    
    public void setAutoSizeTextTypeUniformWithConfiguration(TextView paramTextView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
      throws IllegalArgumentException
    {
      if ((paramTextView instanceof AutoSizeableTextView)) {
        ((AutoSizeableTextView)paramTextView).setAutoSizeTextTypeUniformWithConfiguration(paramInt1, paramInt2, paramInt3, paramInt4);
      }
    }
    
    public void setAutoSizeTextTypeUniformWithPresetSizes(TextView paramTextView, int[] paramArrayOfInt, int paramInt)
      throws IllegalArgumentException
    {
      if ((paramTextView instanceof AutoSizeableTextView)) {
        ((AutoSizeableTextView)paramTextView).setAutoSizeTextTypeUniformWithPresetSizes(paramArrayOfInt, paramInt);
      }
    }
    
    public void setAutoSizeTextTypeWithDefaults(TextView paramTextView, int paramInt)
    {
      if ((paramTextView instanceof AutoSizeableTextView)) {
        ((AutoSizeableTextView)paramTextView).setAutoSizeTextTypeWithDefaults(paramInt);
      }
    }
    
    public void setCompoundDrawablesRelative(TextView paramTextView, Drawable paramDrawable1, Drawable paramDrawable2, Drawable paramDrawable3, Drawable paramDrawable4)
    {
      paramTextView.setCompoundDrawables(paramDrawable1, paramDrawable2, paramDrawable3, paramDrawable4);
    }
    
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(TextView paramTextView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      paramTextView.setCompoundDrawablesWithIntrinsicBounds(paramInt1, paramInt2, paramInt3, paramInt4);
    }
    
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(TextView paramTextView, Drawable paramDrawable1, Drawable paramDrawable2, Drawable paramDrawable3, Drawable paramDrawable4)
    {
      paramTextView.setCompoundDrawablesWithIntrinsicBounds(paramDrawable1, paramDrawable2, paramDrawable3, paramDrawable4);
    }
    
    public void setCustomSelectionActionModeCallback(TextView paramTextView, ActionMode.Callback paramCallback)
    {
      paramTextView.setCustomSelectionActionModeCallback(paramCallback);
    }
    
    public void setTextAppearance(TextView paramTextView, int paramInt)
    {
      paramTextView.setTextAppearance(paramTextView.getContext(), paramInt);
    }
  }
}
