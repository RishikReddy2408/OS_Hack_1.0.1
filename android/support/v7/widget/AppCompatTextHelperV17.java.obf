package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.RequiresApi;
import android.support.v7.appcompat.R.styleable;
import android.util.AttributeSet;
import android.widget.TextView;

@RequiresApi(17)
class AppCompatTextHelperV17
  extends AppCompatTextHelper
{
  private TintInfo mDrawableEndTint;
  private TintInfo mDrawableStartTint;
  
  AppCompatTextHelperV17(TextView paramTextView)
  {
    super(paramTextView);
  }
  
  void applyCompoundDrawablesTints()
  {
    super.applyCompoundDrawablesTints();
    if ((mDrawableStartTint != null) || (mDrawableEndTint != null))
    {
      Drawable[] arrayOfDrawable = mView.getCompoundDrawablesRelative();
      applyCompoundDrawableTint(arrayOfDrawable[0], mDrawableStartTint);
      applyCompoundDrawableTint(arrayOfDrawable[2], mDrawableEndTint);
    }
  }
  
  void loadFromAttributes(AttributeSet paramAttributeSet, int paramInt)
  {
    super.loadFromAttributes(paramAttributeSet, paramInt);
    Context localContext = mView.getContext();
    AppCompatDrawableManager localAppCompatDrawableManager = AppCompatDrawableManager.get();
    paramAttributeSet = localContext.obtainStyledAttributes(paramAttributeSet, R.styleable.AppCompatTextHelper, paramInt, 0);
    if (paramAttributeSet.hasValue(R.styleable.AppCompatTextHelper_android_drawableStart)) {
      mDrawableStartTint = createTintInfo(localContext, localAppCompatDrawableManager, paramAttributeSet.getResourceId(R.styleable.AppCompatTextHelper_android_drawableStart, 0));
    }
    if (paramAttributeSet.hasValue(R.styleable.AppCompatTextHelper_android_drawableEnd)) {
      mDrawableEndTint = createTintInfo(localContext, localAppCompatDrawableManager, paramAttributeSet.getResourceId(R.styleable.AppCompatTextHelper_android_drawableEnd, 0));
    }
    paramAttributeSet.recycle();
  }
}
