package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build.VERSION;
import android.support.annotation.RestrictTo;
import android.support.v4.widget.ImageViewCompat;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class AppCompatImageHelper
{
  private TintInfo mImageTint;
  private TintInfo mInternalImageTint;
  private TintInfo mTmpInfo;
  private final ImageView mView;
  
  public AppCompatImageHelper(ImageView paramImageView)
  {
    mView = paramImageView;
  }
  
  private boolean applyFrameworkTintUsingColorFilter(Drawable paramDrawable)
  {
    if (mTmpInfo == null) {
      mTmpInfo = new TintInfo();
    }
    TintInfo localTintInfo = mTmpInfo;
    localTintInfo.clear();
    Object localObject = ImageViewCompat.getImageTintList(mView);
    if (localObject != null)
    {
      mHasTintList = true;
      mTintList = ((ColorStateList)localObject);
    }
    localObject = ImageViewCompat.getImageTintMode(mView);
    if (localObject != null)
    {
      mHasTintMode = true;
      mTintMode = ((PorterDuff.Mode)localObject);
    }
    if ((!mHasTintList) && (!mHasTintMode)) {
      return false;
    }
    AppCompatDrawableManager.tintDrawable(paramDrawable, localTintInfo, mView.getDrawableState());
    return true;
  }
  
  private boolean shouldApplyFrameworkTintUsingColorFilter()
  {
    int i = Build.VERSION.SDK_INT;
    if (i > 21)
    {
      if (mInternalImageTint != null) {
        return true;
      }
    }
    else if (i == 21) {
      return true;
    }
    return false;
  }
  
  void applySupportImageTint()
  {
    Drawable localDrawable = mView.getDrawable();
    if (localDrawable != null) {
      DrawableUtils.fixDrawable(localDrawable);
    }
    if (localDrawable != null)
    {
      if ((shouldApplyFrameworkTintUsingColorFilter()) && (applyFrameworkTintUsingColorFilter(localDrawable))) {
        return;
      }
      if (mImageTint != null)
      {
        AppCompatDrawableManager.tintDrawable(localDrawable, mImageTint, mView.getDrawableState());
        return;
      }
      if (mInternalImageTint != null) {
        AppCompatDrawableManager.tintDrawable(localDrawable, mInternalImageTint, mView.getDrawableState());
      }
    }
  }
  
  ColorStateList getSupportImageTintList()
  {
    if (mImageTint != null) {
      return mImageTint.mTintList;
    }
    return null;
  }
  
  PorterDuff.Mode getSupportImageTintMode()
  {
    if (mImageTint != null) {
      return mImageTint.mTintMode;
    }
    return null;
  }
  
  boolean hasOverlappingRendering()
  {
    Drawable localDrawable = mView.getBackground();
    return (Build.VERSION.SDK_INT < 21) || (!(localDrawable instanceof RippleDrawable));
  }
  
  public void loadFromAttributes(AttributeSet paramAttributeSet, int paramInt)
  {
    TintTypedArray localTintTypedArray = TintTypedArray.obtainStyledAttributes(mView.getContext(), paramAttributeSet, R.styleable.AppCompatImageView, paramInt, 0);
    try
    {
      Drawable localDrawable2 = mView.getDrawable();
      Drawable localDrawable1 = localDrawable2;
      paramAttributeSet = localDrawable1;
      if (localDrawable2 == null)
      {
        paramInt = localTintTypedArray.getResourceId(R.styleable.AppCompatImageView_srcCompat, -1);
        paramAttributeSet = localDrawable1;
        if (paramInt != -1)
        {
          localDrawable2 = AppCompatResources.getDrawable(mView.getContext(), paramInt);
          localDrawable1 = localDrawable2;
          paramAttributeSet = localDrawable1;
          if (localDrawable2 != null)
          {
            mView.setImageDrawable(localDrawable2);
            paramAttributeSet = localDrawable1;
          }
        }
      }
      if (paramAttributeSet != null) {
        DrawableUtils.fixDrawable(paramAttributeSet);
      }
      boolean bool = localTintTypedArray.hasValue(R.styleable.AppCompatImageView_tint);
      if (bool) {
        ImageViewCompat.setImageTintList(mView, localTintTypedArray.getColorStateList(R.styleable.AppCompatImageView_tint));
      }
      bool = localTintTypedArray.hasValue(R.styleable.AppCompatImageView_tintMode);
      if (bool) {
        ImageViewCompat.setImageTintMode(mView, DrawableUtils.parseTintMode(localTintTypedArray.getInt(R.styleable.AppCompatImageView_tintMode, -1), null));
      }
      localTintTypedArray.recycle();
      return;
    }
    catch (Throwable paramAttributeSet)
    {
      localTintTypedArray.recycle();
      throw paramAttributeSet;
    }
  }
  
  public void setImageResource(int paramInt)
  {
    if (paramInt != 0)
    {
      Drawable localDrawable = AppCompatResources.getDrawable(mView.getContext(), paramInt);
      if (localDrawable != null) {
        DrawableUtils.fixDrawable(localDrawable);
      }
      mView.setImageDrawable(localDrawable);
    }
    else
    {
      mView.setImageDrawable(null);
    }
    applySupportImageTint();
  }
  
  void setInternalImageTint(ColorStateList paramColorStateList)
  {
    if (paramColorStateList != null)
    {
      if (mInternalImageTint == null) {
        mInternalImageTint = new TintInfo();
      }
      mInternalImageTint.mTintList = paramColorStateList;
      mInternalImageTint.mHasTintList = true;
    }
    else
    {
      mInternalImageTint = null;
    }
    applySupportImageTint();
  }
  
  void setSupportImageTintList(ColorStateList paramColorStateList)
  {
    if (mImageTint == null) {
      mImageTint = new TintInfo();
    }
    mImageTint.mTintList = paramColorStateList;
    mImageTint.mHasTintList = true;
    applySupportImageTint();
  }
  
  void setSupportImageTintMode(PorterDuff.Mode paramMode)
  {
    if (mImageTint == null) {
      mImageTint = new TintInfo();
    }
    mImageTint.mTintMode = paramMode;
    mImageTint.mHasTintMode = true;
    applySupportImageTint();
  }
}
