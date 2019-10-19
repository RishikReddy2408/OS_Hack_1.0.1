package android.support.v4.widget;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.support.v4.util.Preconditions;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.DisplayMetrics;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class CircularProgressDrawable
  extends Drawable
  implements Animatable
{
  private static final int ANIMATION_DURATION = 1332;
  private static final int ARROW_HEIGHT = 5;
  private static final int ARROW_HEIGHT_LARGE = 6;
  private static final int ARROW_WIDTH = 10;
  private static final int ARROW_WIDTH_LARGE = 12;
  private static final float CENTER_RADIUS = 7.5F;
  private static final float CENTER_RADIUS_LARGE = 11.0F;
  private static final int[] COLORS = { -16777216 };
  private static final float COLOR_CHANGE_OFFSET = 0.75F;
  public static final int DEFAULT = 1;
  private static final float GROUP_FULL_ROTATION = 216.0F;
  public static final int LARGE = 0;
  private static final Interpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
  private static final Interpolator MATERIAL_INTERPOLATOR = new FastOutSlowInInterpolator();
  private static final float MAX_PROGRESS_ARC = 0.8F;
  private static final float MIN_PROGRESS_ARC = 0.01F;
  private static final float RING_ROTATION = 0.20999998F;
  private static final float SHRINK_OFFSET = 0.5F;
  private static final float STROKE_WIDTH = 2.5F;
  private static final float STROKE_WIDTH_LARGE = 3.0F;
  private Animator mAnimator;
  private boolean mFinishing;
  private Resources mResources;
  private final Ring mRing;
  private float mRotation;
  private float mRotationCount;
  
  public CircularProgressDrawable(Context paramContext)
  {
    mResources = ((Context)Preconditions.checkNotNull(paramContext)).getResources();
    mRing = new Ring();
    mRing.setColors(COLORS);
    setStrokeWidth(2.5F);
    setupAnimators();
  }
  
  private void applyFinishTranslation(float paramFloat, Ring paramRing)
  {
    updateRingColor(paramFloat, paramRing);
    float f = (float)(Math.floor(paramRing.getStartingRotation() / 0.8F) + 1.0D);
    paramRing.setStartTrim(paramRing.getStartingStartTrim() + (paramRing.getStartingEndTrim() - 0.01F - paramRing.getStartingStartTrim()) * paramFloat);
    paramRing.setEndTrim(paramRing.getStartingEndTrim());
    paramRing.setRotation(paramRing.getStartingRotation() + (f - paramRing.getStartingRotation()) * paramFloat);
  }
  
  private void applyTransformation(float paramFloat, Ring paramRing, boolean paramBoolean)
  {
    if (mFinishing)
    {
      applyFinishTranslation(paramFloat, paramRing);
      return;
    }
    if ((paramFloat != 1.0F) || (paramBoolean))
    {
      float f3 = paramRing.getStartingRotation();
      float f1;
      float f2;
      if (paramFloat < 0.5F)
      {
        f1 = paramFloat / 0.5F;
        f2 = paramRing.getStartingStartTrim();
        f1 = MATERIAL_INTERPOLATOR.getInterpolation(f1) * 0.79F + 0.01F + f2;
      }
      else
      {
        f2 = (paramFloat - 0.5F) / 0.5F;
        f1 = paramRing.getStartingStartTrim() + 0.79F;
        f2 = f1 - ((1.0F - MATERIAL_INTERPOLATOR.getInterpolation(f2)) * 0.79F + 0.01F);
      }
      float f4 = mRotationCount;
      paramRing.setStartTrim(f2);
      paramRing.setEndTrim(f1);
      paramRing.setRotation(f3 + 0.20999998F * paramFloat);
      setRotation((paramFloat + f4) * 216.0F);
    }
  }
  
  private int evaluateColorChange(float paramFloat, int paramInt1, int paramInt2)
  {
    int i = paramInt1 >> 24 & 0xFF;
    int j = paramInt1 >> 16 & 0xFF;
    int k = paramInt1 >> 8 & 0xFF;
    paramInt1 &= 0xFF;
    return i + (int)(((paramInt2 >> 24 & 0xFF) - i) * paramFloat) << 24 | j + (int)(((paramInt2 >> 16 & 0xFF) - j) * paramFloat) << 16 | k + (int)(((paramInt2 >> 8 & 0xFF) - k) * paramFloat) << 8 | paramInt1 + (int)(paramFloat * ((paramInt2 & 0xFF) - paramInt1));
  }
  
  private float getRotation()
  {
    return mRotation;
  }
  
  private void setRotation(float paramFloat)
  {
    mRotation = paramFloat;
  }
  
  private void setSizeParameters(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    Ring localRing = mRing;
    float f = mResources.getDisplayMetrics().density;
    localRing.setStrokeWidth(paramFloat2 * f);
    localRing.setCenterRadius(paramFloat1 * f);
    localRing.setColorIndex(0);
    localRing.setArrowDimensions(paramFloat3 * f, paramFloat4 * f);
  }
  
  private void setupAnimators()
  {
    final Ring localRing = mRing;
    ValueAnimator localValueAnimator = ValueAnimator.ofFloat(new float[] { 0.0F, 1.0F });
    localValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
    {
      public void onAnimationUpdate(ValueAnimator paramAnonymousValueAnimator)
      {
        float f = ((Float)paramAnonymousValueAnimator.getAnimatedValue()).floatValue();
        CircularProgressDrawable.this.updateRingColor(f, localRing);
        CircularProgressDrawable.this.applyTransformation(f, localRing, false);
        invalidateSelf();
      }
    });
    localValueAnimator.setRepeatCount(-1);
    localValueAnimator.setRepeatMode(1);
    localValueAnimator.setInterpolator(LINEAR_INTERPOLATOR);
    localValueAnimator.addListener(new Animator.AnimatorListener()
    {
      public void onAnimationCancel(Animator paramAnonymousAnimator) {}
      
      public void onAnimationEnd(Animator paramAnonymousAnimator) {}
      
      public void onAnimationRepeat(Animator paramAnonymousAnimator)
      {
        CircularProgressDrawable.this.applyTransformation(1.0F, localRing, true);
        localRing.storeOriginals();
        localRing.goToNextColor();
        if (mFinishing)
        {
          CircularProgressDrawable.access$302(CircularProgressDrawable.this, false);
          paramAnonymousAnimator.cancel();
          paramAnonymousAnimator.setDuration(1332L);
          paramAnonymousAnimator.start();
          localRing.setShowArrow(false);
          return;
        }
        CircularProgressDrawable.access$202(CircularProgressDrawable.this, mRotationCount + 1.0F);
      }
      
      public void onAnimationStart(Animator paramAnonymousAnimator)
      {
        CircularProgressDrawable.access$202(CircularProgressDrawable.this, 0.0F);
      }
    });
    mAnimator = localValueAnimator;
  }
  
  private void updateRingColor(float paramFloat, Ring paramRing)
  {
    if (paramFloat > 0.75F)
    {
      paramRing.setColor(evaluateColorChange((paramFloat - 0.75F) / 0.25F, paramRing.getStartingColor(), paramRing.getNextColor()));
      return;
    }
    paramRing.setColor(paramRing.getStartingColor());
  }
  
  public void draw(Canvas paramCanvas)
  {
    Rect localRect = getBounds();
    paramCanvas.save();
    paramCanvas.rotate(mRotation, localRect.exactCenterX(), localRect.exactCenterY());
    mRing.draw(paramCanvas, localRect);
    paramCanvas.restore();
  }
  
  public int getAlpha()
  {
    return mRing.getAlpha();
  }
  
  public boolean getArrowEnabled()
  {
    return mRing.getShowArrow();
  }
  
  public float getArrowHeight()
  {
    return mRing.getArrowHeight();
  }
  
  public float getArrowScale()
  {
    return mRing.getArrowScale();
  }
  
  public float getArrowWidth()
  {
    return mRing.getArrowWidth();
  }
  
  public int getBackgroundColor()
  {
    return mRing.getBackgroundColor();
  }
  
  public float getCenterRadius()
  {
    return mRing.getCenterRadius();
  }
  
  public int[] getColorSchemeColors()
  {
    return mRing.getColors();
  }
  
  public float getEndTrim()
  {
    return mRing.getEndTrim();
  }
  
  public int getOpacity()
  {
    return -3;
  }
  
  public float getProgressRotation()
  {
    return mRing.getRotation();
  }
  
  public float getStartTrim()
  {
    return mRing.getStartTrim();
  }
  
  public Paint.Cap getStrokeCap()
  {
    return mRing.getStrokeCap();
  }
  
  public float getStrokeWidth()
  {
    return mRing.getStrokeWidth();
  }
  
  public boolean isRunning()
  {
    return mAnimator.isRunning();
  }
  
  public void setAlpha(int paramInt)
  {
    mRing.setAlpha(paramInt);
    invalidateSelf();
  }
  
  public void setArrowDimensions(float paramFloat1, float paramFloat2)
  {
    mRing.setArrowDimensions(paramFloat1, paramFloat2);
    invalidateSelf();
  }
  
  public void setArrowEnabled(boolean paramBoolean)
  {
    mRing.setShowArrow(paramBoolean);
    invalidateSelf();
  }
  
  public void setArrowScale(float paramFloat)
  {
    mRing.setArrowScale(paramFloat);
    invalidateSelf();
  }
  
  public void setBackgroundColor(int paramInt)
  {
    mRing.setBackgroundColor(paramInt);
    invalidateSelf();
  }
  
  public void setCenterRadius(float paramFloat)
  {
    mRing.setCenterRadius(paramFloat);
    invalidateSelf();
  }
  
  public void setColorFilter(ColorFilter paramColorFilter)
  {
    mRing.setColorFilter(paramColorFilter);
    invalidateSelf();
  }
  
  public void setColorSchemeColors(int... paramVarArgs)
  {
    mRing.setColors(paramVarArgs);
    mRing.setColorIndex(0);
    invalidateSelf();
  }
  
  public void setProgressRotation(float paramFloat)
  {
    mRing.setRotation(paramFloat);
    invalidateSelf();
  }
  
  public void setStartEndTrim(float paramFloat1, float paramFloat2)
  {
    mRing.setStartTrim(paramFloat1);
    mRing.setEndTrim(paramFloat2);
    invalidateSelf();
  }
  
  public void setStrokeCap(Paint.Cap paramCap)
  {
    mRing.setStrokeCap(paramCap);
    invalidateSelf();
  }
  
  public void setStrokeWidth(float paramFloat)
  {
    mRing.setStrokeWidth(paramFloat);
    invalidateSelf();
  }
  
  public void setStyle(int paramInt)
  {
    if (paramInt == 0) {
      setSizeParameters(11.0F, 3.0F, 12.0F, 6.0F);
    } else {
      setSizeParameters(7.5F, 2.5F, 10.0F, 5.0F);
    }
    invalidateSelf();
  }
  
  public void start()
  {
    mAnimator.cancel();
    mRing.storeOriginals();
    if (mRing.getEndTrim() != mRing.getStartTrim())
    {
      mFinishing = true;
      mAnimator.setDuration(666L);
      mAnimator.start();
      return;
    }
    mRing.setColorIndex(0);
    mRing.resetOriginals();
    mAnimator.setDuration(1332L);
    mAnimator.start();
  }
  
  public void stop()
  {
    mAnimator.cancel();
    setRotation(0.0F);
    mRing.setShowArrow(false);
    mRing.setColorIndex(0);
    mRing.resetOriginals();
    invalidateSelf();
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface ProgressDrawableSize {}
  
  private static class Ring
  {
    int mAlpha = 255;
    Path mArrow;
    int mArrowHeight;
    final Paint mArrowPaint = new Paint();
    float mArrowScale = 1.0F;
    int mArrowWidth;
    final Paint mCirclePaint = new Paint();
    int mColorIndex;
    int[] mColors;
    int mCurrentColor;
    float mEndTrim = 0.0F;
    final Paint mPaint = new Paint();
    float mRingCenterRadius;
    float mRotation = 0.0F;
    boolean mShowArrow;
    float mStartTrim = 0.0F;
    float mStartingEndTrim;
    float mStartingRotation;
    float mStartingStartTrim;
    float mStrokeWidth = 5.0F;
    final RectF mTempBounds = new RectF();
    
    Ring()
    {
      mPaint.setStrokeCap(Paint.Cap.SQUARE);
      mPaint.setAntiAlias(true);
      mPaint.setStyle(Paint.Style.STROKE);
      mArrowPaint.setStyle(Paint.Style.FILL);
      mArrowPaint.setAntiAlias(true);
      mCirclePaint.setColor(0);
    }
    
    void draw(Canvas paramCanvas, Rect paramRect)
    {
      RectF localRectF = mTempBounds;
      float f1 = mRingCenterRadius + mStrokeWidth / 2.0F;
      if (mRingCenterRadius <= 0.0F) {
        f1 = Math.min(paramRect.width(), paramRect.height()) / 2.0F - Math.max(mArrowWidth * mArrowScale / 2.0F, mStrokeWidth / 2.0F);
      }
      localRectF.set(paramRect.centerX() - f1, paramRect.centerY() - f1, paramRect.centerX() + f1, paramRect.centerY() + f1);
      f1 = (mStartTrim + mRotation) * 360.0F;
      float f2 = (mEndTrim + mRotation) * 360.0F - f1;
      mPaint.setColor(mCurrentColor);
      mPaint.setAlpha(mAlpha);
      float f3 = mStrokeWidth / 2.0F;
      localRectF.inset(f3, f3);
      paramCanvas.drawCircle(localRectF.centerX(), localRectF.centerY(), localRectF.width() / 2.0F, mCirclePaint);
      f3 = -f3;
      localRectF.inset(f3, f3);
      paramCanvas.drawArc(localRectF, f1, f2, false, mPaint);
      drawTriangle(paramCanvas, f1, f2, localRectF);
    }
    
    void drawTriangle(Canvas paramCanvas, float paramFloat1, float paramFloat2, RectF paramRectF)
    {
      if (mShowArrow)
      {
        if (mArrow == null)
        {
          mArrow = new Path();
          mArrow.setFillType(Path.FillType.EVEN_ODD);
        }
        else
        {
          mArrow.reset();
        }
        float f1 = Math.min(paramRectF.width(), paramRectF.height()) / 2.0F;
        float f2 = mArrowWidth * mArrowScale / 2.0F;
        mArrow.moveTo(0.0F, 0.0F);
        mArrow.lineTo(mArrowWidth * mArrowScale, 0.0F);
        mArrow.lineTo(mArrowWidth * mArrowScale / 2.0F, mArrowHeight * mArrowScale);
        mArrow.offset(f1 + paramRectF.centerX() - f2, paramRectF.centerY() + mStrokeWidth / 2.0F);
        mArrow.close();
        mArrowPaint.setColor(mCurrentColor);
        mArrowPaint.setAlpha(mAlpha);
        paramCanvas.save();
        paramCanvas.rotate(paramFloat1 + paramFloat2, paramRectF.centerX(), paramRectF.centerY());
        paramCanvas.drawPath(mArrow, mArrowPaint);
        paramCanvas.restore();
      }
    }
    
    int getAlpha()
    {
      return mAlpha;
    }
    
    float getArrowHeight()
    {
      return mArrowHeight;
    }
    
    float getArrowScale()
    {
      return mArrowScale;
    }
    
    float getArrowWidth()
    {
      return mArrowWidth;
    }
    
    int getBackgroundColor()
    {
      return mCirclePaint.getColor();
    }
    
    float getCenterRadius()
    {
      return mRingCenterRadius;
    }
    
    int[] getColors()
    {
      return mColors;
    }
    
    float getEndTrim()
    {
      return mEndTrim;
    }
    
    int getNextColor()
    {
      return mColors[getNextColorIndex()];
    }
    
    int getNextColorIndex()
    {
      return (mColorIndex + 1) % mColors.length;
    }
    
    float getRotation()
    {
      return mRotation;
    }
    
    boolean getShowArrow()
    {
      return mShowArrow;
    }
    
    float getStartTrim()
    {
      return mStartTrim;
    }
    
    int getStartingColor()
    {
      return mColors[mColorIndex];
    }
    
    float getStartingEndTrim()
    {
      return mStartingEndTrim;
    }
    
    float getStartingRotation()
    {
      return mStartingRotation;
    }
    
    float getStartingStartTrim()
    {
      return mStartingStartTrim;
    }
    
    Paint.Cap getStrokeCap()
    {
      return mPaint.getStrokeCap();
    }
    
    float getStrokeWidth()
    {
      return mStrokeWidth;
    }
    
    void goToNextColor()
    {
      setColorIndex(getNextColorIndex());
    }
    
    void resetOriginals()
    {
      mStartingStartTrim = 0.0F;
      mStartingEndTrim = 0.0F;
      mStartingRotation = 0.0F;
      setStartTrim(0.0F);
      setEndTrim(0.0F);
      setRotation(0.0F);
    }
    
    void setAlpha(int paramInt)
    {
      mAlpha = paramInt;
    }
    
    void setArrowDimensions(float paramFloat1, float paramFloat2)
    {
      mArrowWidth = ((int)paramFloat1);
      mArrowHeight = ((int)paramFloat2);
    }
    
    void setArrowScale(float paramFloat)
    {
      if (paramFloat != mArrowScale) {
        mArrowScale = paramFloat;
      }
    }
    
    void setBackgroundColor(int paramInt)
    {
      mCirclePaint.setColor(paramInt);
    }
    
    void setCenterRadius(float paramFloat)
    {
      mRingCenterRadius = paramFloat;
    }
    
    void setColor(int paramInt)
    {
      mCurrentColor = paramInt;
    }
    
    void setColorFilter(ColorFilter paramColorFilter)
    {
      mPaint.setColorFilter(paramColorFilter);
    }
    
    void setColorIndex(int paramInt)
    {
      mColorIndex = paramInt;
      mCurrentColor = mColors[mColorIndex];
    }
    
    void setColors(int[] paramArrayOfInt)
    {
      mColors = paramArrayOfInt;
      setColorIndex(0);
    }
    
    void setEndTrim(float paramFloat)
    {
      mEndTrim = paramFloat;
    }
    
    void setRotation(float paramFloat)
    {
      mRotation = paramFloat;
    }
    
    void setShowArrow(boolean paramBoolean)
    {
      if (mShowArrow != paramBoolean) {
        mShowArrow = paramBoolean;
      }
    }
    
    void setStartTrim(float paramFloat)
    {
      mStartTrim = paramFloat;
    }
    
    void setStrokeCap(Paint.Cap paramCap)
    {
      mPaint.setStrokeCap(paramCap);
    }
    
    void setStrokeWidth(float paramFloat)
    {
      mStrokeWidth = paramFloat;
      mPaint.setStrokeWidth(paramFloat);
    }
    
    void storeOriginals()
    {
      mStartingStartTrim = mStartTrim;
      mStartingEndTrim = mEndTrim;
      mStartingRotation = mRotation;
    }
  }
}
