package android.support.v7.widget;

import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.DrawableContainer.DrawableContainerState;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.Build.VERSION;
import android.support.annotation.RestrictTo;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.graphics.drawable.WrappedDrawable;
import android.support.v7.graphics.drawable.DrawableWrapper;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class DrawableUtils
{
  public static final Rect INSETS_NONE = new Rect();
  private static final String TAG = "DrawableUtils";
  private static final String VECTOR_DRAWABLE_CLAZZ_NAME = "android.graphics.drawable.VectorDrawable";
  private static Class<?> sInsetsClazz;
  
  static
  {
    if (Build.VERSION.SDK_INT >= 18) {
      try
      {
        Class localClass = Class.forName("android.graphics.Insets");
        sInsetsClazz = localClass;
        return;
      }
      catch (ClassNotFoundException localClassNotFoundException) {}
    }
  }
  
  private DrawableUtils() {}
  
  public static boolean canSafelyMutateDrawable(Drawable paramDrawable)
  {
    if ((Build.VERSION.SDK_INT < 15) && ((paramDrawable instanceof InsetDrawable))) {
      return false;
    }
    if ((Build.VERSION.SDK_INT < 15) && ((paramDrawable instanceof GradientDrawable))) {
      return false;
    }
    if ((Build.VERSION.SDK_INT < 17) && ((paramDrawable instanceof LayerDrawable))) {
      return false;
    }
    if ((paramDrawable instanceof DrawableContainer))
    {
      paramDrawable = paramDrawable.getConstantState();
      if ((paramDrawable instanceof DrawableContainer.DrawableContainerState))
      {
        paramDrawable = ((DrawableContainer.DrawableContainerState)paramDrawable).getChildren();
        int j = paramDrawable.length;
        int i = 0;
        while (i < j)
        {
          if (!canSafelyMutateDrawable(paramDrawable[i])) {
            return false;
          }
          i += 1;
        }
      }
    }
    else
    {
      if ((paramDrawable instanceof WrappedDrawable)) {
        return canSafelyMutateDrawable(((WrappedDrawable)paramDrawable).getWrappedDrawable());
      }
      if ((paramDrawable instanceof DrawableWrapper)) {
        return canSafelyMutateDrawable(((DrawableWrapper)paramDrawable).getWrappedDrawable());
      }
      if ((paramDrawable instanceof ScaleDrawable)) {
        return canSafelyMutateDrawable(((ScaleDrawable)paramDrawable).getDrawable());
      }
    }
    return true;
  }
  
  static void fixDrawable(Drawable paramDrawable)
  {
    if ((Build.VERSION.SDK_INT == 21) && ("android.graphics.drawable.VectorDrawable".equals(paramDrawable.getClass().getName()))) {
      fixVectorDrawableTinting(paramDrawable);
    }
  }
  
  private static void fixVectorDrawableTinting(Drawable paramDrawable)
  {
    int[] arrayOfInt = paramDrawable.getState();
    if ((arrayOfInt != null) && (arrayOfInt.length != 0)) {
      paramDrawable.setState(ThemeUtils.EMPTY_STATE_SET);
    } else {
      paramDrawable.setState(ThemeUtils.CHECKED_STATE_SET);
    }
    paramDrawable.setState(arrayOfInt);
  }
  
  public static Rect getOpticalBounds(Drawable paramDrawable)
  {
    if (sInsetsClazz != null) {}
    try
    {
      paramDrawable = DrawableCompat.unwrap(paramDrawable);
      Object localObject1 = paramDrawable.getClass();
      localObject1 = ((Class)localObject1).getMethod("getOpticalInsets", new Class[0]);
      paramDrawable = ((Method)localObject1).invoke(paramDrawable, new Object[0]);
      if (paramDrawable == null) {
        break label329;
      }
      localObject1 = new Rect();
      Object localObject2 = sInsetsClazz;
      localObject2 = ((Class)localObject2).getFields();
      int k = localObject2.length;
      int j = 0;
      while (j < k)
      {
        Object localObject3 = localObject2[j];
        String str = localObject3.getName();
        int i = str.hashCode();
        boolean bool;
        if (i != -1383228885)
        {
          if (i != 115029)
          {
            if (i != 3317767)
            {
              if (i == 108511772)
              {
                bool = str.equals("right");
                if (bool)
                {
                  i = 2;
                  break label201;
                }
              }
            }
            else
            {
              bool = str.equals("left");
              if (bool)
              {
                i = 0;
                break label201;
              }
            }
          }
          else
          {
            bool = str.equals("top");
            if (bool)
            {
              i = 1;
              break label201;
            }
          }
        }
        else
        {
          bool = str.equals("bottom");
          if (bool)
          {
            i = 3;
            break label201;
          }
        }
        i = -1;
        switch (i)
        {
        default: 
          break;
        case 3: 
          i = localObject3.getInt(paramDrawable);
          bottom = i;
          break;
        case 2: 
          i = localObject3.getInt(paramDrawable);
          right = i;
          break;
        case 1: 
          i = localObject3.getInt(paramDrawable);
          top = i;
          break;
        case 0: 
          label201:
          i = localObject3.getInt(paramDrawable);
          left = i;
        }
        j += 1;
      }
      return localObject1;
    }
    catch (Exception paramDrawable)
    {
      label329:
      for (;;) {}
    }
    Log.e("DrawableUtils", "Couldn't obtain the optical insets. Ignoring.");
    return INSETS_NONE;
  }
  
  public static PorterDuff.Mode parseTintMode(int paramInt, PorterDuff.Mode paramMode)
  {
    if (paramInt != 3)
    {
      if (paramInt != 5)
      {
        if (paramInt != 9)
        {
          switch (paramInt)
          {
          default: 
            return paramMode;
          case 16: 
            return PorterDuff.Mode.ADD;
          case 15: 
            return PorterDuff.Mode.SCREEN;
          }
          return PorterDuff.Mode.MULTIPLY;
        }
        return PorterDuff.Mode.SRC_ATOP;
      }
      return PorterDuff.Mode.SRC_IN;
    }
    return PorterDuff.Mode.SRC_OVER;
  }
}
