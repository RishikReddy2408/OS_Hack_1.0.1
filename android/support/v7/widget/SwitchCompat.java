package android.support.v7.widget;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.Region.Op;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.text.AllCapsTransformationMethod;
import android.text.Layout;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Property;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityRecord;
import android.widget.CompoundButton;
import android.widget.TextView;
import java.util.List;

public class SwitchCompat
  extends CompoundButton
{
  private static final String ACCESSIBILITY_EVENT_CLASS_NAME = "android.widget.Switch";
  private static final int[] CHECKED_STATE_SET = { 16842912 };
  private static final int MONOSPACE = 3;
  private static final int SANS = 1;
  private static final int SERIF = 2;
  private static final int THUMB_ANIMATION_DURATION = 250;
  private static final Property<SwitchCompat, Float> THUMB_POS = new Property(Float.class, "thumbPos")
  {
    public Float get(SwitchCompat paramAnonymousSwitchCompat)
    {
      return Float.valueOf(mThumbPosition);
    }
    
    public void put(SwitchCompat paramAnonymousSwitchCompat, Float paramAnonymousFloat)
    {
      paramAnonymousSwitchCompat.setThumbPosition(paramAnonymousFloat.floatValue());
    }
  };
  private static final int TOUCH_MODE_DOWN = 1;
  private static final int TOUCH_MODE_DRAGGING = 2;
  private static final int TOUCH_MODE_IDLE = 0;
  private boolean mHasThumbTint = false;
  private boolean mHasThumbTintMode = false;
  private boolean mHasTrackTint = false;
  private boolean mHasTrackTintMode = false;
  private int mMinFlingVelocity;
  private Layout mOffLayout;
  private Layout mOnLayout;
  ObjectAnimator mPositionAnimator;
  private boolean mShowText;
  private boolean mSplitTrack;
  private int mSwitchBottom;
  private int mSwitchHeight;
  private int mSwitchLeft;
  private int mSwitchMinWidth;
  private int mSwitchPadding;
  private int mSwitchRight;
  private int mSwitchTop;
  private TransformationMethod mSwitchTransformationMethod;
  private int mSwitchWidth;
  private final Rect mTempRect = new Rect();
  private ColorStateList mTextColors;
  private CharSequence mTextOff;
  private CharSequence mTextOn;
  private final TextPaint mTextPaint = new TextPaint(1);
  private Drawable mThumbDrawable;
  private float mThumbPosition;
  private int mThumbTextPadding;
  private ColorStateList mThumbTintList = null;
  private PorterDuff.Mode mThumbTintMode = null;
  private int mThumbWidth;
  private int mTouchMode;
  private int mTouchSlop;
  private float mTouchX;
  private float mTouchY;
  private Drawable mTrackDrawable;
  private ColorStateList mTrackTintList = null;
  private PorterDuff.Mode mTrackTintMode = null;
  private VelocityTracker mVelocityTracker = VelocityTracker.obtain();
  
  public SwitchCompat(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public SwitchCompat(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.switchStyle);
  }
  
  public SwitchCompat(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    Object localObject = getResources();
    mTextPaint.density = getDisplayMetricsdensity;
    paramAttributeSet = TintTypedArray.obtainStyledAttributes(paramContext, paramAttributeSet, R.styleable.SwitchCompat, paramInt, 0);
    mThumbDrawable = paramAttributeSet.getDrawable(R.styleable.SwitchCompat_android_thumb);
    if (mThumbDrawable != null) {
      mThumbDrawable.setCallback(this);
    }
    mTrackDrawable = paramAttributeSet.getDrawable(R.styleable.SwitchCompat_track);
    if (mTrackDrawable != null) {
      mTrackDrawable.setCallback(this);
    }
    mTextOn = paramAttributeSet.getText(R.styleable.SwitchCompat_android_textOn);
    mTextOff = paramAttributeSet.getText(R.styleable.SwitchCompat_android_textOff);
    mShowText = paramAttributeSet.getBoolean(R.styleable.SwitchCompat_showText, true);
    mThumbTextPadding = paramAttributeSet.getDimensionPixelSize(R.styleable.SwitchCompat_thumbTextPadding, 0);
    mSwitchMinWidth = paramAttributeSet.getDimensionPixelSize(R.styleable.SwitchCompat_switchMinWidth, 0);
    mSwitchPadding = paramAttributeSet.getDimensionPixelSize(R.styleable.SwitchCompat_switchPadding, 0);
    mSplitTrack = paramAttributeSet.getBoolean(R.styleable.SwitchCompat_splitTrack, false);
    localObject = paramAttributeSet.getColorStateList(R.styleable.SwitchCompat_thumbTint);
    if (localObject != null)
    {
      mThumbTintList = ((ColorStateList)localObject);
      mHasThumbTint = true;
    }
    localObject = DrawableUtils.parseTintMode(paramAttributeSet.getInt(R.styleable.SwitchCompat_thumbTintMode, -1), null);
    if (mThumbTintMode != localObject)
    {
      mThumbTintMode = ((PorterDuff.Mode)localObject);
      mHasThumbTintMode = true;
    }
    if ((mHasThumbTint) || (mHasThumbTintMode)) {
      applyThumbTint();
    }
    localObject = paramAttributeSet.getColorStateList(R.styleable.SwitchCompat_trackTint);
    if (localObject != null)
    {
      mTrackTintList = ((ColorStateList)localObject);
      mHasTrackTint = true;
    }
    localObject = DrawableUtils.parseTintMode(paramAttributeSet.getInt(R.styleable.SwitchCompat_trackTintMode, -1), null);
    if (mTrackTintMode != localObject)
    {
      mTrackTintMode = ((PorterDuff.Mode)localObject);
      mHasTrackTintMode = true;
    }
    if ((mHasTrackTint) || (mHasTrackTintMode)) {
      applyTrackTint();
    }
    paramInt = paramAttributeSet.getResourceId(R.styleable.SwitchCompat_switchTextAppearance, 0);
    if (paramInt != 0) {
      setSwitchTextAppearance(paramContext, paramInt);
    }
    paramAttributeSet.recycle();
    paramContext = ViewConfiguration.get(paramContext);
    mTouchSlop = paramContext.getScaledTouchSlop();
    mMinFlingVelocity = paramContext.getScaledMinimumFlingVelocity();
    refreshDrawableState();
    setChecked(isChecked());
  }
  
  private void animateThumbToCheckedState(boolean paramBoolean)
  {
    float f;
    if (paramBoolean) {
      f = 1.0F;
    } else {
      f = 0.0F;
    }
    mPositionAnimator = ObjectAnimator.ofFloat(this, THUMB_POS, new float[] { f });
    mPositionAnimator.setDuration(250L);
    if (Build.VERSION.SDK_INT >= 18) {
      mPositionAnimator.setAutoCancel(true);
    }
    mPositionAnimator.start();
  }
  
  private void applyThumbTint()
  {
    if ((mThumbDrawable != null) && ((mHasThumbTint) || (mHasThumbTintMode)))
    {
      mThumbDrawable = mThumbDrawable.mutate();
      if (mHasThumbTint) {
        DrawableCompat.setTintList(mThumbDrawable, mThumbTintList);
      }
      if (mHasThumbTintMode) {
        DrawableCompat.setTintMode(mThumbDrawable, mThumbTintMode);
      }
      if (mThumbDrawable.isStateful()) {
        mThumbDrawable.setState(getDrawableState());
      }
    }
  }
  
  private void applyTrackTint()
  {
    if ((mTrackDrawable != null) && ((mHasTrackTint) || (mHasTrackTintMode)))
    {
      mTrackDrawable = mTrackDrawable.mutate();
      if (mHasTrackTint) {
        DrawableCompat.setTintList(mTrackDrawable, mTrackTintList);
      }
      if (mHasTrackTintMode) {
        DrawableCompat.setTintMode(mTrackDrawable, mTrackTintMode);
      }
      if (mTrackDrawable.isStateful()) {
        mTrackDrawable.setState(getDrawableState());
      }
    }
  }
  
  private void cancelPositionAnimator()
  {
    if (mPositionAnimator != null) {
      mPositionAnimator.cancel();
    }
  }
  
  private void cancelSuperTouch(MotionEvent paramMotionEvent)
  {
    paramMotionEvent = MotionEvent.obtain(paramMotionEvent);
    paramMotionEvent.setAction(3);
    super.onTouchEvent(paramMotionEvent);
    paramMotionEvent.recycle();
  }
  
  private static float constrain(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    if (paramFloat1 < paramFloat2) {
      return paramFloat2;
    }
    if (paramFloat1 > paramFloat3) {
      return paramFloat3;
    }
    return paramFloat1;
  }
  
  private boolean getTargetCheckedState()
  {
    return mThumbPosition > 0.5F;
  }
  
  private int getThumbOffset()
  {
    float f;
    if (ViewUtils.isLayoutRtl(this)) {
      f = 1.0F - mThumbPosition;
    } else {
      f = mThumbPosition;
    }
    return (int)(f * getThumbScrollRange() + 0.5F);
  }
  
  private int getThumbScrollRange()
  {
    if (mTrackDrawable != null)
    {
      Rect localRect2 = mTempRect;
      mTrackDrawable.getPadding(localRect2);
      Rect localRect1;
      if (mThumbDrawable != null) {
        localRect1 = DrawableUtils.getOpticalBounds(mThumbDrawable);
      } else {
        localRect1 = DrawableUtils.INSETS_NONE;
      }
      return mSwitchWidth - mThumbWidth - left - right - left - right;
    }
    return 0;
  }
  
  private boolean hitThumb(float paramFloat1, float paramFloat2)
  {
    if (mThumbDrawable == null) {
      return false;
    }
    int k = getThumbOffset();
    mThumbDrawable.getPadding(mTempRect);
    int i = mSwitchTop;
    int j = mTouchSlop;
    k = mSwitchLeft + k - mTouchSlop;
    int m = mThumbWidth;
    int n = mTempRect.left;
    int i1 = mTempRect.right;
    int i2 = mTouchSlop;
    int i3 = mSwitchBottom;
    int i4 = mTouchSlop;
    return (paramFloat1 > k) && (paramFloat1 < m + k + n + i1 + i2) && (paramFloat2 > i - j) && (paramFloat2 < i3 + i4);
  }
  
  private Layout makeLayout(CharSequence paramCharSequence)
  {
    CharSequence localCharSequence = paramCharSequence;
    if (mSwitchTransformationMethod != null) {
      localCharSequence = mSwitchTransformationMethod.getTransformation(paramCharSequence, this);
    }
    paramCharSequence = mTextPaint;
    int i;
    if (localCharSequence != null) {
      i = (int)Math.ceil(Layout.getDesiredWidth(localCharSequence, mTextPaint));
    } else {
      i = 0;
    }
    return new StaticLayout(localCharSequence, paramCharSequence, i, Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
  }
  
  private void setSwitchTypefaceByIndex(int paramInt1, int paramInt2)
  {
    Typeface localTypeface;
    switch (paramInt1)
    {
    default: 
      localTypeface = null;
      break;
    case 3: 
      localTypeface = Typeface.MONOSPACE;
      break;
    case 2: 
      localTypeface = Typeface.SERIF;
      break;
    case 1: 
      localTypeface = Typeface.SANS_SERIF;
    }
    setSwitchTypeface(localTypeface, paramInt2);
  }
  
  private void stopDrag(MotionEvent paramMotionEvent)
  {
    mTouchMode = 0;
    int i = paramMotionEvent.getAction();
    boolean bool1 = true;
    if ((i == 1) && (isEnabled())) {
      i = 1;
    } else {
      i = 0;
    }
    boolean bool2 = isChecked();
    if (i != 0)
    {
      mVelocityTracker.computeCurrentVelocity(1000);
      float f = mVelocityTracker.getXVelocity();
      if (Math.abs(f) > mMinFlingVelocity)
      {
        if (ViewUtils.isLayoutRtl(this))
        {
          if (f < 0.0F) {}
        }
        else {
          while (f <= 0.0F)
          {
            bool1 = false;
            break;
          }
        }
      }
      else {
        bool1 = getTargetCheckedState();
      }
    }
    else
    {
      bool1 = bool2;
    }
    if (bool1 != bool2) {
      playSoundEffect(0);
    }
    setChecked(bool1);
    cancelSuperTouch(paramMotionEvent);
  }
  
  public void draw(Canvas paramCanvas)
  {
    Rect localRect = mTempRect;
    int m = mSwitchLeft;
    int i2 = mSwitchTop;
    int n = mSwitchRight;
    int i4 = mSwitchBottom;
    int j = getThumbOffset() + m;
    Object localObject;
    if (mThumbDrawable != null) {
      localObject = DrawableUtils.getOpticalBounds(mThumbDrawable);
    } else {
      localObject = DrawableUtils.INSETS_NONE;
    }
    int i = j;
    if (mTrackDrawable != null)
    {
      mTrackDrawable.getPadding(localRect);
      int i5 = j + left;
      int i1;
      if (localObject != null)
      {
        i = m;
        if (left > left) {
          i = m + (left - left);
        }
        if (top > top) {
          j = top - top + i2;
        } else {
          j = i2;
        }
        k = n;
        if (right > right) {
          k = n - (right - right);
        }
        m = i;
        n = k;
        i1 = j;
        if (bottom > bottom)
        {
          i3 = i4 - (bottom - bottom);
          break label253;
        }
      }
      else
      {
        i1 = i2;
      }
      int i3 = i4;
      j = i1;
      int k = n;
      i = m;
      label253:
      mTrackDrawable.setBounds(i, j, k, i3);
      i = i5;
    }
    if (mThumbDrawable != null)
    {
      mThumbDrawable.getPadding(localRect);
      j = i - left;
      i = i + mThumbWidth + right;
      mThumbDrawable.setBounds(j, i2, i, i4);
      localObject = getBackground();
      if (localObject != null) {
        DrawableCompat.setHotspotBounds((Drawable)localObject, j, i2, i, i4);
      }
    }
    super.draw(paramCanvas);
  }
  
  public void drawableHotspotChanged(float paramFloat1, float paramFloat2)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      super.drawableHotspotChanged(paramFloat1, paramFloat2);
    }
    if (mThumbDrawable != null) {
      DrawableCompat.setHotspot(mThumbDrawable, paramFloat1, paramFloat2);
    }
    if (mTrackDrawable != null) {
      DrawableCompat.setHotspot(mTrackDrawable, paramFloat1, paramFloat2);
    }
  }
  
  protected void drawableStateChanged()
  {
    super.drawableStateChanged();
    int[] arrayOfInt = getDrawableState();
    Drawable localDrawable = mThumbDrawable;
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (localDrawable != null)
    {
      bool1 = bool2;
      if (localDrawable.isStateful()) {
        bool1 = false | localDrawable.setState(arrayOfInt);
      }
    }
    localDrawable = mTrackDrawable;
    bool2 = bool1;
    if (localDrawable != null)
    {
      bool2 = bool1;
      if (localDrawable.isStateful()) {
        bool2 = bool1 | localDrawable.setState(arrayOfInt);
      }
    }
    if (bool2) {
      invalidate();
    }
  }
  
  public int getCompoundPaddingLeft()
  {
    if (!ViewUtils.isLayoutRtl(this)) {
      return super.getCompoundPaddingLeft();
    }
    int j = super.getCompoundPaddingLeft() + mSwitchWidth;
    int i = j;
    if (!TextUtils.isEmpty(getText())) {
      i = j + mSwitchPadding;
    }
    return i;
  }
  
  public int getCompoundPaddingRight()
  {
    if (ViewUtils.isLayoutRtl(this)) {
      return super.getCompoundPaddingRight();
    }
    int j = super.getCompoundPaddingRight() + mSwitchWidth;
    int i = j;
    if (!TextUtils.isEmpty(getText())) {
      i = j + mSwitchPadding;
    }
    return i;
  }
  
  public boolean getShowText()
  {
    return mShowText;
  }
  
  public boolean getSplitTrack()
  {
    return mSplitTrack;
  }
  
  public int getSwitchMinWidth()
  {
    return mSwitchMinWidth;
  }
  
  public int getSwitchPadding()
  {
    return mSwitchPadding;
  }
  
  public CharSequence getTextOff()
  {
    return mTextOff;
  }
  
  public CharSequence getTextOn()
  {
    return mTextOn;
  }
  
  public Drawable getThumbDrawable()
  {
    return mThumbDrawable;
  }
  
  public int getThumbTextPadding()
  {
    return mThumbTextPadding;
  }
  
  public ColorStateList getThumbTintList()
  {
    return mThumbTintList;
  }
  
  public PorterDuff.Mode getThumbTintMode()
  {
    return mThumbTintMode;
  }
  
  public Drawable getTrackDrawable()
  {
    return mTrackDrawable;
  }
  
  public ColorStateList getTrackTintList()
  {
    return mTrackTintList;
  }
  
  public PorterDuff.Mode getTrackTintMode()
  {
    return mTrackTintMode;
  }
  
  public void jumpDrawablesToCurrentState()
  {
    super.jumpDrawablesToCurrentState();
    if (mThumbDrawable != null) {
      mThumbDrawable.jumpToCurrentState();
    }
    if (mTrackDrawable != null) {
      mTrackDrawable.jumpToCurrentState();
    }
    if ((mPositionAnimator != null) && (mPositionAnimator.isStarted()))
    {
      mPositionAnimator.end();
      mPositionAnimator = null;
    }
  }
  
  protected int[] onCreateDrawableState(int paramInt)
  {
    int[] arrayOfInt = super.onCreateDrawableState(paramInt + 1);
    if (isChecked()) {
      View.mergeDrawableStates(arrayOfInt, CHECKED_STATE_SET);
    }
    return arrayOfInt;
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    Object localObject1 = mTempRect;
    Object localObject3 = mTrackDrawable;
    if (localObject3 != null) {
      ((Drawable)localObject3).getPadding((Rect)localObject1);
    } else {
      ((Rect)localObject1).setEmpty();
    }
    int k = mSwitchTop;
    int m = mSwitchBottom;
    int n = top;
    int i1 = bottom;
    Object localObject2 = mThumbDrawable;
    int i;
    if (localObject3 != null) {
      if ((mSplitTrack) && (localObject2 != null))
      {
        Rect localRect = DrawableUtils.getOpticalBounds((Drawable)localObject2);
        ((Drawable)localObject2).copyBounds((Rect)localObject1);
        left += left;
        right -= right;
        i = paramCanvas.save();
        paramCanvas.clipRect((Rect)localObject1, Region.Op.DIFFERENCE);
        ((Drawable)localObject3).draw(paramCanvas);
        paramCanvas.restoreToCount(i);
      }
      else
      {
        ((Drawable)localObject3).draw(paramCanvas);
      }
    }
    int j = paramCanvas.save();
    if (localObject2 != null) {
      ((Drawable)localObject2).draw(paramCanvas);
    }
    if (getTargetCheckedState()) {
      localObject1 = mOnLayout;
    } else {
      localObject1 = mOffLayout;
    }
    if (localObject1 != null)
    {
      localObject3 = getDrawableState();
      if (mTextColors != null) {
        mTextPaint.setColor(mTextColors.getColorForState((int[])localObject3, 0));
      }
      mTextPaint.drawableState = ((int[])localObject3);
      if (localObject2 != null)
      {
        localObject2 = ((Drawable)localObject2).getBounds();
        i = left + right;
      }
      else
      {
        i = getWidth();
      }
      i /= 2;
      int i2 = ((Layout)localObject1).getWidth() / 2;
      k = (k + n + (m - i1)) / 2;
      m = ((Layout)localObject1).getHeight() / 2;
      paramCanvas.translate(i - i2, k - m);
      ((Layout)localObject1).draw(paramCanvas);
    }
    paramCanvas.restoreToCount(j);
  }
  
  public void onInitializeAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    super.onInitializeAccessibilityEvent(paramAccessibilityEvent);
    paramAccessibilityEvent.setClassName("android.widget.Switch");
  }
  
  public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo paramAccessibilityNodeInfo)
  {
    super.onInitializeAccessibilityNodeInfo(paramAccessibilityNodeInfo);
    paramAccessibilityNodeInfo.setClassName("android.widget.Switch");
    CharSequence localCharSequence1;
    if (isChecked()) {
      localCharSequence1 = mTextOn;
    } else {
      localCharSequence1 = mTextOff;
    }
    if (!TextUtils.isEmpty(localCharSequence1))
    {
      CharSequence localCharSequence2 = paramAccessibilityNodeInfo.getText();
      if (TextUtils.isEmpty(localCharSequence2))
      {
        paramAccessibilityNodeInfo.setText(localCharSequence1);
        return;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(localCharSequence2);
      localStringBuilder.append(' ');
      localStringBuilder.append(localCharSequence1);
      paramAccessibilityNodeInfo.setText(localStringBuilder);
    }
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    Object localObject = mThumbDrawable;
    paramInt2 = 0;
    if (localObject != null)
    {
      localObject = mTempRect;
      if (mTrackDrawable != null) {
        mTrackDrawable.getPadding((Rect)localObject);
      } else {
        ((Rect)localObject).setEmpty();
      }
      Rect localRect = DrawableUtils.getOpticalBounds(mThumbDrawable);
      paramInt2 = Math.max(0, left - left);
      paramInt1 = Math.max(0, right - right);
    }
    else
    {
      paramInt1 = 0;
    }
    if (ViewUtils.isLayoutRtl(this))
    {
      paramInt3 = getPaddingLeft() + paramInt2;
      paramInt4 = mSwitchWidth + paramInt3 - paramInt2 - paramInt1;
    }
    else
    {
      paramInt4 = getWidth() - getPaddingRight() - paramInt1;
      paramInt3 = paramInt4 - mSwitchWidth + paramInt2 + paramInt1;
    }
    paramInt1 = getGravity() & 0x70;
    if (paramInt1 != 16)
    {
      if (paramInt1 != 80)
      {
        paramInt2 = getPaddingTop();
        paramInt1 = paramInt2;
        paramInt2 = mSwitchHeight + paramInt2;
      }
      else
      {
        paramInt2 = getHeight() - getPaddingBottom();
        paramInt1 = paramInt2 - mSwitchHeight;
      }
    }
    else
    {
      paramInt1 = (getPaddingTop() + getHeight() - getPaddingBottom()) / 2 - mSwitchHeight / 2;
      paramInt2 = mSwitchHeight + paramInt1;
    }
    mSwitchLeft = paramInt3;
    mSwitchTop = paramInt1;
    mSwitchBottom = paramInt2;
    mSwitchRight = paramInt4;
  }
  
  public void onMeasure(int paramInt1, int paramInt2)
  {
    if (mShowText)
    {
      if (mOnLayout == null) {
        mOnLayout = makeLayout(mTextOn);
      }
      if (mOffLayout == null) {
        mOffLayout = makeLayout(mTextOff);
      }
    }
    Rect localRect = mTempRect;
    Drawable localDrawable = mThumbDrawable;
    int m = 0;
    int j;
    if (localDrawable != null)
    {
      mThumbDrawable.getPadding(localRect);
      j = mThumbDrawable.getIntrinsicWidth() - left - right;
      i = mThumbDrawable.getIntrinsicHeight();
    }
    else
    {
      j = 0;
      i = 0;
    }
    if (mShowText) {
      k = Math.max(mOnLayout.getWidth(), mOffLayout.getWidth()) + mThumbTextPadding * 2;
    } else {
      k = 0;
    }
    mThumbWidth = Math.max(k, j);
    if (mTrackDrawable != null)
    {
      mTrackDrawable.getPadding(localRect);
      j = mTrackDrawable.getIntrinsicHeight();
    }
    else
    {
      localRect.setEmpty();
      j = m;
    }
    int i1 = left;
    int n = right;
    m = n;
    int k = i1;
    if (mThumbDrawable != null)
    {
      localRect = DrawableUtils.getOpticalBounds(mThumbDrawable);
      k = Math.max(i1, left);
      m = Math.max(n, right);
    }
    k = Math.max(mSwitchMinWidth, mThumbWidth * 2 + k + m);
    int i = Math.max(j, i);
    mSwitchWidth = k;
    mSwitchHeight = i;
    super.onMeasure(paramInt1, paramInt2);
    if (getMeasuredHeight() < i) {
      setMeasuredDimension(getMeasuredWidthAndState(), i);
    }
  }
  
  public void onPopulateAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    super.onPopulateAccessibilityEvent(paramAccessibilityEvent);
    CharSequence localCharSequence;
    if (isChecked()) {
      localCharSequence = mTextOn;
    } else {
      localCharSequence = mTextOff;
    }
    if (localCharSequence != null) {
      paramAccessibilityEvent.getText().add(localCharSequence);
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    mVelocityTracker.addMovement(paramMotionEvent);
    float f1;
    float f2;
    switch (paramMotionEvent.getActionMasked())
    {
    default: 
      break;
    case 2: 
      switch (mTouchMode)
      {
      default: 
        break;
      case 2: 
        float f3 = paramMotionEvent.getX();
        int i = getThumbScrollRange();
        f1 = f3 - mTouchX;
        if (i != 0) {
          f1 /= i;
        } else if (f1 > 0.0F) {
          f1 = 1.0F;
        } else {
          f1 = -1.0F;
        }
        f2 = f1;
        if (ViewUtils.isLayoutRtl(this)) {
          f2 = -f1;
        }
        f1 = constrain(mThumbPosition + f2, 0.0F, 1.0F);
        if (f1 == mThumbPosition) {
          break label357;
        }
        mTouchX = f3;
        setThumbPosition(f1);
        return true;
      case 1: 
        f1 = paramMotionEvent.getX();
        f2 = paramMotionEvent.getY();
        if ((Math.abs(f1 - mTouchX) > mTouchSlop) || (Math.abs(f2 - mTouchY) > mTouchSlop))
        {
          mTouchMode = 2;
          getParent().requestDisallowInterceptTouchEvent(true);
          mTouchX = f1;
          mTouchY = f2;
          return true;
        }
        break;
      }
      break;
    case 1: 
    case 3: 
      if (mTouchMode == 2)
      {
        stopDrag(paramMotionEvent);
        super.onTouchEvent(paramMotionEvent);
        return true;
      }
      mTouchMode = 0;
      mVelocityTracker.clear();
      break;
    case 0: 
      f1 = paramMotionEvent.getX();
      f2 = paramMotionEvent.getY();
      if ((isEnabled()) && (hitThumb(f1, f2)))
      {
        mTouchMode = 1;
        mTouchX = f1;
        mTouchY = f2;
      }
      break;
    }
    return super.onTouchEvent(paramMotionEvent);
    label357:
    return true;
  }
  
  public void setChecked(boolean paramBoolean)
  {
    super.setChecked(paramBoolean);
    paramBoolean = isChecked();
    if ((getWindowToken() != null) && (ViewCompat.isLaidOut(this)))
    {
      animateThumbToCheckedState(paramBoolean);
      return;
    }
    cancelPositionAnimator();
    float f;
    if (paramBoolean) {
      f = 1.0F;
    } else {
      f = 0.0F;
    }
    setThumbPosition(f);
  }
  
  public void setShowText(boolean paramBoolean)
  {
    if (mShowText != paramBoolean)
    {
      mShowText = paramBoolean;
      requestLayout();
    }
  }
  
  public void setSplitTrack(boolean paramBoolean)
  {
    mSplitTrack = paramBoolean;
    invalidate();
  }
  
  public void setSwitchMinWidth(int paramInt)
  {
    mSwitchMinWidth = paramInt;
    requestLayout();
  }
  
  public void setSwitchPadding(int paramInt)
  {
    mSwitchPadding = paramInt;
    requestLayout();
  }
  
  public void setSwitchTextAppearance(Context paramContext, int paramInt)
  {
    paramContext = TintTypedArray.obtainStyledAttributes(paramContext, paramInt, R.styleable.TextAppearance);
    ColorStateList localColorStateList = paramContext.getColorStateList(R.styleable.TextAppearance_android_textColor);
    if (localColorStateList != null) {
      mTextColors = localColorStateList;
    } else {
      mTextColors = getTextColors();
    }
    paramInt = paramContext.getDimensionPixelSize(R.styleable.TextAppearance_android_textSize, 0);
    if (paramInt != 0)
    {
      float f = paramInt;
      if (f != mTextPaint.getTextSize())
      {
        mTextPaint.setTextSize(f);
        requestLayout();
      }
    }
    setSwitchTypefaceByIndex(paramContext.getInt(R.styleable.TextAppearance_android_typeface, -1), paramContext.getInt(R.styleable.TextAppearance_android_textStyle, -1));
    if (paramContext.getBoolean(R.styleable.TextAppearance_textAllCaps, false)) {
      mSwitchTransformationMethod = new AllCapsTransformationMethod(getContext());
    } else {
      mSwitchTransformationMethod = null;
    }
    paramContext.recycle();
  }
  
  public void setSwitchTypeface(Typeface paramTypeface)
  {
    if (((mTextPaint.getTypeface() != null) && (!mTextPaint.getTypeface().equals(paramTypeface))) || ((mTextPaint.getTypeface() == null) && (paramTypeface != null)))
    {
      mTextPaint.setTypeface(paramTypeface);
      requestLayout();
      invalidate();
    }
  }
  
  public void setSwitchTypeface(Typeface paramTypeface, int paramInt)
  {
    float f = 0.0F;
    boolean bool = false;
    if (paramInt > 0)
    {
      if (paramTypeface == null) {
        paramTypeface = Typeface.defaultFromStyle(paramInt);
      } else {
        paramTypeface = Typeface.create(paramTypeface, paramInt);
      }
      setSwitchTypeface(paramTypeface);
      int i;
      if (paramTypeface != null) {
        i = paramTypeface.getStyle();
      } else {
        i = 0;
      }
      paramInt = i & paramInt;
      paramTypeface = mTextPaint;
      if ((paramInt & 0x1) != 0) {
        bool = true;
      }
      paramTypeface.setFakeBoldText(bool);
      paramTypeface = mTextPaint;
      if ((paramInt & 0x2) != 0) {
        f = -0.25F;
      }
      paramTypeface.setTextSkewX(f);
      return;
    }
    mTextPaint.setFakeBoldText(false);
    mTextPaint.setTextSkewX(0.0F);
    setSwitchTypeface(paramTypeface);
  }
  
  public void setTextOff(CharSequence paramCharSequence)
  {
    mTextOff = paramCharSequence;
    requestLayout();
  }
  
  public void setTextOn(CharSequence paramCharSequence)
  {
    mTextOn = paramCharSequence;
    requestLayout();
  }
  
  public void setThumbDrawable(Drawable paramDrawable)
  {
    if (mThumbDrawable != null) {
      mThumbDrawable.setCallback(null);
    }
    mThumbDrawable = paramDrawable;
    if (paramDrawable != null) {
      paramDrawable.setCallback(this);
    }
    requestLayout();
  }
  
  void setThumbPosition(float paramFloat)
  {
    mThumbPosition = paramFloat;
    invalidate();
  }
  
  public void setThumbResource(int paramInt)
  {
    setThumbDrawable(AppCompatResources.getDrawable(getContext(), paramInt));
  }
  
  public void setThumbTextPadding(int paramInt)
  {
    mThumbTextPadding = paramInt;
    requestLayout();
  }
  
  public void setThumbTintList(ColorStateList paramColorStateList)
  {
    mThumbTintList = paramColorStateList;
    mHasThumbTint = true;
    applyThumbTint();
  }
  
  public void setThumbTintMode(PorterDuff.Mode paramMode)
  {
    mThumbTintMode = paramMode;
    mHasThumbTintMode = true;
    applyThumbTint();
  }
  
  public void setTrackDrawable(Drawable paramDrawable)
  {
    if (mTrackDrawable != null) {
      mTrackDrawable.setCallback(null);
    }
    mTrackDrawable = paramDrawable;
    if (paramDrawable != null) {
      paramDrawable.setCallback(this);
    }
    requestLayout();
  }
  
  public void setTrackResource(int paramInt)
  {
    setTrackDrawable(AppCompatResources.getDrawable(getContext(), paramInt));
  }
  
  public void setTrackTintList(ColorStateList paramColorStateList)
  {
    mTrackTintList = paramColorStateList;
    mHasTrackTint = true;
    applyTrackTint();
  }
  
  public void setTrackTintMode(PorterDuff.Mode paramMode)
  {
    mTrackTintMode = paramMode;
    mHasTrackTintMode = true;
    applyTrackTint();
  }
  
  public void toggle()
  {
    setChecked(isChecked() ^ true);
  }
  
  protected boolean verifyDrawable(Drawable paramDrawable)
  {
    return (super.verifyDrawable(paramDrawable)) || (paramDrawable == mThumbDrawable) || (paramDrawable == mTrackDrawable);
  }
}
