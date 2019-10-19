package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R.styleable;
import android.util.AttributeSet;
import android.widget.SeekBar;

class AppCompatSeekBarHelper
  extends AppCompatProgressBarHelper
{
  private boolean mHasTickMarkTint = false;
  private boolean mHasTickMarkTintMode = false;
  private Drawable mTickMark;
  private ColorStateList mTickMarkTintList = null;
  private PorterDuff.Mode mTickMarkTintMode = null;
  private final SeekBar mView;
  
  AppCompatSeekBarHelper(SeekBar paramSeekBar)
  {
    super(paramSeekBar);
    mView = paramSeekBar;
  }
  
  private void applyTickMarkTint()
  {
    if ((mTickMark != null) && ((mHasTickMarkTint) || (mHasTickMarkTintMode)))
    {
      mTickMark = DrawableCompat.wrap(mTickMark.mutate());
      if (mHasTickMarkTint) {
        DrawableCompat.setTintList(mTickMark, mTickMarkTintList);
      }
      if (mHasTickMarkTintMode) {
        DrawableCompat.setTintMode(mTickMark, mTickMarkTintMode);
      }
      if (mTickMark.isStateful()) {
        mTickMark.setState(mView.getDrawableState());
      }
    }
  }
  
  void drawTickMarks(Canvas paramCanvas)
  {
    if (mTickMark != null)
    {
      int k = mView.getMax();
      int j = 1;
      if (k > 1)
      {
        int i = mTickMark.getIntrinsicWidth();
        int m = mTickMark.getIntrinsicHeight();
        if (i >= 0) {
          i /= 2;
        } else {
          i = 1;
        }
        if (m >= 0) {
          j = m / 2;
        }
        mTickMark.setBounds(-i, -j, i, j);
        float f = (mView.getWidth() - mView.getPaddingLeft() - mView.getPaddingRight()) / k;
        j = paramCanvas.save();
        paramCanvas.translate(mView.getPaddingLeft(), mView.getHeight() / 2);
        i = 0;
        while (i <= k)
        {
          mTickMark.draw(paramCanvas);
          paramCanvas.translate(f, 0.0F);
          i += 1;
        }
        paramCanvas.restoreToCount(j);
      }
    }
  }
  
  void drawableStateChanged()
  {
    Drawable localDrawable = mTickMark;
    if ((localDrawable != null) && (localDrawable.isStateful()) && (localDrawable.setState(mView.getDrawableState()))) {
      mView.invalidateDrawable(localDrawable);
    }
  }
  
  @Nullable
  Drawable getTickMark()
  {
    return mTickMark;
  }
  
  @Nullable
  ColorStateList getTickMarkTintList()
  {
    return mTickMarkTintList;
  }
  
  @Nullable
  PorterDuff.Mode getTickMarkTintMode()
  {
    return mTickMarkTintMode;
  }
  
  @RequiresApi(11)
  void jumpDrawablesToCurrentState()
  {
    if (mTickMark != null) {
      mTickMark.jumpToCurrentState();
    }
  }
  
  void loadFromAttributes(AttributeSet paramAttributeSet, int paramInt)
  {
    super.loadFromAttributes(paramAttributeSet, paramInt);
    paramAttributeSet = TintTypedArray.obtainStyledAttributes(mView.getContext(), paramAttributeSet, R.styleable.AppCompatSeekBar, paramInt, 0);
    Drawable localDrawable = paramAttributeSet.getDrawableIfKnown(R.styleable.AppCompatSeekBar_android_thumb);
    if (localDrawable != null) {
      mView.setThumb(localDrawable);
    }
    setTickMark(paramAttributeSet.getDrawable(R.styleable.AppCompatSeekBar_tickMark));
    if (paramAttributeSet.hasValue(R.styleable.AppCompatSeekBar_tickMarkTintMode))
    {
      mTickMarkTintMode = DrawableUtils.parseTintMode(paramAttributeSet.getInt(R.styleable.AppCompatSeekBar_tickMarkTintMode, -1), mTickMarkTintMode);
      mHasTickMarkTintMode = true;
    }
    if (paramAttributeSet.hasValue(R.styleable.AppCompatSeekBar_tickMarkTint))
    {
      mTickMarkTintList = paramAttributeSet.getColorStateList(R.styleable.AppCompatSeekBar_tickMarkTint);
      mHasTickMarkTint = true;
    }
    paramAttributeSet.recycle();
    applyTickMarkTint();
  }
  
  void setTickMark(@Nullable Drawable paramDrawable)
  {
    if (mTickMark != null) {
      mTickMark.setCallback(null);
    }
    mTickMark = paramDrawable;
    if (paramDrawable != null)
    {
      paramDrawable.setCallback(mView);
      DrawableCompat.setLayoutDirection(paramDrawable, ViewCompat.getLayoutDirection(mView));
      if (paramDrawable.isStateful()) {
        paramDrawable.setState(mView.getDrawableState());
      }
      applyTickMarkTint();
    }
    mView.invalidate();
  }
  
  void setTickMarkTintList(@Nullable ColorStateList paramColorStateList)
  {
    mTickMarkTintList = paramColorStateList;
    mHasTickMarkTint = true;
    applyTickMarkTint();
  }
  
  void setTickMarkTintMode(@Nullable PorterDuff.Mode paramMode)
  {
    mTickMarkTintMode = paramMode;
    mHasTickMarkTintMode = true;
    applyTickMarkTint();
  }
}
