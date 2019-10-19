package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleableRes;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.content.res.ResourcesCompat.FontCallback;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.util.TypedValue;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class TintTypedArray
{
  private final Context mContext;
  private TypedValue mTypedValue;
  private final TypedArray mWrapped;
  
  private TintTypedArray(Context paramContext, TypedArray paramTypedArray)
  {
    mContext = paramContext;
    mWrapped = paramTypedArray;
  }
  
  public static TintTypedArray obtainStyledAttributes(Context paramContext, int paramInt, int[] paramArrayOfInt)
  {
    return new TintTypedArray(paramContext, paramContext.obtainStyledAttributes(paramInt, paramArrayOfInt));
  }
  
  public static TintTypedArray obtainStyledAttributes(Context paramContext, AttributeSet paramAttributeSet, int[] paramArrayOfInt)
  {
    return new TintTypedArray(paramContext, paramContext.obtainStyledAttributes(paramAttributeSet, paramArrayOfInt));
  }
  
  public static TintTypedArray obtainStyledAttributes(Context paramContext, AttributeSet paramAttributeSet, int[] paramArrayOfInt, int paramInt1, int paramInt2)
  {
    return new TintTypedArray(paramContext, paramContext.obtainStyledAttributes(paramAttributeSet, paramArrayOfInt, paramInt1, paramInt2));
  }
  
  public boolean getBoolean(int paramInt, boolean paramBoolean)
  {
    return mWrapped.getBoolean(paramInt, paramBoolean);
  }
  
  @RequiresApi(21)
  public int getChangingConfigurations()
  {
    return mWrapped.getChangingConfigurations();
  }
  
  public int getColor(int paramInt1, int paramInt2)
  {
    return mWrapped.getColor(paramInt1, paramInt2);
  }
  
  public ColorStateList getColorStateList(int paramInt)
  {
    if (mWrapped.hasValue(paramInt))
    {
      int i = mWrapped.getResourceId(paramInt, 0);
      if (i != 0)
      {
        ColorStateList localColorStateList = AppCompatResources.getColorStateList(mContext, i);
        if (localColorStateList != null) {
          return localColorStateList;
        }
      }
    }
    return mWrapped.getColorStateList(paramInt);
  }
  
  public float getDimension(int paramInt, float paramFloat)
  {
    return mWrapped.getDimension(paramInt, paramFloat);
  }
  
  public int getDimensionPixelOffset(int paramInt1, int paramInt2)
  {
    return mWrapped.getDimensionPixelOffset(paramInt1, paramInt2);
  }
  
  public int getDimensionPixelSize(int paramInt1, int paramInt2)
  {
    return mWrapped.getDimensionPixelSize(paramInt1, paramInt2);
  }
  
  public Drawable getDrawable(int paramInt)
  {
    if (mWrapped.hasValue(paramInt))
    {
      int i = mWrapped.getResourceId(paramInt, 0);
      if (i != 0) {
        return AppCompatResources.getDrawable(mContext, i);
      }
    }
    return mWrapped.getDrawable(paramInt);
  }
  
  public Drawable getDrawableIfKnown(int paramInt)
  {
    if (mWrapped.hasValue(paramInt))
    {
      paramInt = mWrapped.getResourceId(paramInt, 0);
      if (paramInt != 0) {
        return AppCompatDrawableManager.get().getDrawable(mContext, paramInt, true);
      }
    }
    return null;
  }
  
  public float getFloat(int paramInt, float paramFloat)
  {
    return mWrapped.getFloat(paramInt, paramFloat);
  }
  
  @Nullable
  public Typeface getFont(@StyleableRes int paramInt1, int paramInt2, @Nullable ResourcesCompat.FontCallback paramFontCallback)
  {
    paramInt1 = mWrapped.getResourceId(paramInt1, 0);
    if (paramInt1 == 0) {
      return null;
    }
    if (mTypedValue == null) {
      mTypedValue = new TypedValue();
    }
    return ResourcesCompat.getFont(mContext, paramInt1, mTypedValue, paramInt2, paramFontCallback);
  }
  
  public float getFraction(int paramInt1, int paramInt2, int paramInt3, float paramFloat)
  {
    return mWrapped.getFraction(paramInt1, paramInt2, paramInt3, paramFloat);
  }
  
  public int getIndex(int paramInt)
  {
    return mWrapped.getIndex(paramInt);
  }
  
  public int getIndexCount()
  {
    return mWrapped.getIndexCount();
  }
  
  public int getInt(int paramInt1, int paramInt2)
  {
    return mWrapped.getInt(paramInt1, paramInt2);
  }
  
  public int getInteger(int paramInt1, int paramInt2)
  {
    return mWrapped.getInteger(paramInt1, paramInt2);
  }
  
  public int getLayoutDimension(int paramInt1, int paramInt2)
  {
    return mWrapped.getLayoutDimension(paramInt1, paramInt2);
  }
  
  public int getLayoutDimension(int paramInt, String paramString)
  {
    return mWrapped.getLayoutDimension(paramInt, paramString);
  }
  
  public String getNonResourceString(int paramInt)
  {
    return mWrapped.getNonResourceString(paramInt);
  }
  
  public String getPositionDescription()
  {
    return mWrapped.getPositionDescription();
  }
  
  public int getResourceId(int paramInt1, int paramInt2)
  {
    return mWrapped.getResourceId(paramInt1, paramInt2);
  }
  
  public Resources getResources()
  {
    return mWrapped.getResources();
  }
  
  public String getString(int paramInt)
  {
    return mWrapped.getString(paramInt);
  }
  
  public CharSequence getText(int paramInt)
  {
    return mWrapped.getText(paramInt);
  }
  
  public CharSequence[] getTextArray(int paramInt)
  {
    return mWrapped.getTextArray(paramInt);
  }
  
  public int getType(int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return mWrapped.getType(paramInt);
    }
    if (mTypedValue == null) {
      mTypedValue = new TypedValue();
    }
    mWrapped.getValue(paramInt, mTypedValue);
    return mTypedValue.type;
  }
  
  public boolean getValue(int paramInt, TypedValue paramTypedValue)
  {
    return mWrapped.getValue(paramInt, paramTypedValue);
  }
  
  public boolean hasValue(int paramInt)
  {
    return mWrapped.hasValue(paramInt);
  }
  
  public int length()
  {
    return mWrapped.length();
  }
  
  public TypedValue peekValue(int paramInt)
  {
    return mWrapped.peekValue(paramInt);
  }
  
  public void recycle()
  {
    mWrapped.recycle();
  }
}
