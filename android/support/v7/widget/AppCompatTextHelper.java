package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources.NotFoundException;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.hack.ResourcesCompat.FontCallback;
import android.support.v4.widget.AutoSizeableTextView;
import android.support.v7.appcompat.R.styleable;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import java.lang.ref.WeakReference;

@RequiresApi(9)
class AppCompatTextHelper
{
  private static final int MONOSPACE = 3;
  private static final int SANS = 1;
  private static final int SERIF = 2;
  private boolean mAsyncFontPending;
  @NonNull
  private final AppCompatTextViewAutoSizeHelper mAutoSizeTextHelper;
  private TintInfo mDrawableBottomTint;
  private TintInfo mDrawableLeftTint;
  private TintInfo mDrawableRightTint;
  private TintInfo mDrawableTopTint;
  private Typeface mFontTypeface;
  private int mStyle = 0;
  final TextView mView;
  
  AppCompatTextHelper(TextView paramTextView)
  {
    mView = paramTextView;
    mAutoSizeTextHelper = new AppCompatTextViewAutoSizeHelper(mView);
  }
  
  static AppCompatTextHelper create(TextView paramTextView)
  {
    if (Build.VERSION.SDK_INT >= 17) {
      return new AppCompatTextHelperV17(paramTextView);
    }
    return new AppCompatTextHelper(paramTextView);
  }
  
  protected static TintInfo createTintInfo(Context paramContext, AppCompatDrawableManager paramAppCompatDrawableManager, int paramInt)
  {
    paramContext = paramAppCompatDrawableManager.getTintList(paramContext, paramInt);
    if (paramContext != null)
    {
      paramAppCompatDrawableManager = new TintInfo();
      mHasTintList = true;
      mTintList = paramContext;
      return paramAppCompatDrawableManager;
    }
    return null;
  }
  
  private void onAsyncTypefaceReceived(WeakReference paramWeakReference, Typeface paramTypeface)
  {
    if (mAsyncFontPending)
    {
      mFontTypeface = paramTypeface;
      paramWeakReference = (TextView)paramWeakReference.get();
      if (paramWeakReference != null) {
        paramWeakReference.setTypeface(paramTypeface, mStyle);
      }
    }
  }
  
  private void setTextSizeInternal(int paramInt, float paramFloat)
  {
    mAutoSizeTextHelper.setTextSizeInternal(paramInt, paramFloat);
  }
  
  private void updateTypefaceAndStyle(Context paramContext, TintTypedArray paramTintTypedArray)
  {
    mStyle = paramTintTypedArray.getInt(R.styleable.TextAppearance_android_textStyle, mStyle);
    boolean bool2 = paramTintTypedArray.hasValue(R.styleable.TextAppearance_android_fontFamily);
    boolean bool1 = true;
    if ((!bool2) && (!paramTintTypedArray.hasValue(R.styleable.TextAppearance_fontFamily)))
    {
      if (paramTintTypedArray.hasValue(R.styleable.TextAppearance_android_typeface))
      {
        mAsyncFontPending = false;
        switch (paramTintTypedArray.getInt(R.styleable.TextAppearance_android_typeface, 1))
        {
        default: 
          return;
        case 3: 
          mFontTypeface = Typeface.MONOSPACE;
          return;
        case 2: 
          mFontTypeface = Typeface.SERIF;
          return;
        }
        mFontTypeface = Typeface.SANS_SERIF;
      }
    }
    else
    {
      mFontTypeface = null;
      int i;
      if (paramTintTypedArray.hasValue(R.styleable.TextAppearance_fontFamily)) {
        i = R.styleable.TextAppearance_fontFamily;
      } else {
        i = R.styleable.TextAppearance_android_fontFamily;
      }
      int j;
      if (!paramContext.isRestricted())
      {
        paramContext = new ResourcesCompat.FontCallback()
        {
          public void onFontRetrievalFailed(int paramAnonymousInt) {}
          
          public void onFontRetrieved(Typeface paramAnonymousTypeface)
          {
            AppCompatTextHelper.this.onAsyncTypefaceReceived(val$textViewWeak, paramAnonymousTypeface);
          }
        };
        j = mStyle;
      }
      try
      {
        paramContext = paramTintTypedArray.getFont(i, j, paramContext);
        mFontTypeface = paramContext;
        if (mFontTypeface != null) {
          bool1 = false;
        }
        mAsyncFontPending = bool1;
      }
      catch (UnsupportedOperationException paramContext)
      {
        for (;;) {}
      }
      catch (Resources.NotFoundException paramContext)
      {
        for (;;) {}
      }
      if (mFontTypeface == null)
      {
        paramContext = paramTintTypedArray.getString(i);
        if (paramContext != null)
        {
          mFontTypeface = Typeface.create(paramContext, mStyle);
          return;
        }
      }
    }
  }
  
  final void applyCompoundDrawableTint(Drawable paramDrawable, TintInfo paramTintInfo)
  {
    if ((paramDrawable != null) && (paramTintInfo != null)) {
      AppCompatDrawableManager.tintDrawable(paramDrawable, paramTintInfo, mView.getDrawableState());
    }
  }
  
  void applyCompoundDrawablesTints()
  {
    if ((mDrawableLeftTint != null) || (mDrawableTopTint != null) || (mDrawableRightTint != null) || (mDrawableBottomTint != null))
    {
      Drawable[] arrayOfDrawable = mView.getCompoundDrawables();
      applyCompoundDrawableTint(arrayOfDrawable[0], mDrawableLeftTint);
      applyCompoundDrawableTint(arrayOfDrawable[1], mDrawableTopTint);
      applyCompoundDrawableTint(arrayOfDrawable[2], mDrawableRightTint);
      applyCompoundDrawableTint(arrayOfDrawable[3], mDrawableBottomTint);
    }
  }
  
  void autoSizeText()
  {
    mAutoSizeTextHelper.autoSizeText();
  }
  
  int getAutoSizeMaxTextSize()
  {
    return mAutoSizeTextHelper.getAutoSizeMaxTextSize();
  }
  
  int getAutoSizeMinTextSize()
  {
    return mAutoSizeTextHelper.getAutoSizeMinTextSize();
  }
  
  int getAutoSizeStepGranularity()
  {
    return mAutoSizeTextHelper.getAutoSizeStepGranularity();
  }
  
  int[] getAutoSizeTextAvailableSizes()
  {
    return mAutoSizeTextHelper.getAutoSizeTextAvailableSizes();
  }
  
  int getAutoSizeTextType()
  {
    return mAutoSizeTextHelper.getAutoSizeTextType();
  }
  
  boolean isAutoSizeEnabled()
  {
    return mAutoSizeTextHelper.isAutoSizeEnabled();
  }
  
  void loadFromAttributes(AttributeSet paramAttributeSet, int paramInt)
  {
    Context localContext = mView.getContext();
    Object localObject1 = AppCompatDrawableManager.get();
    Object localObject2 = TintTypedArray.obtainStyledAttributes(localContext, paramAttributeSet, R.styleable.AppCompatTextHelper, paramInt, 0);
    int i = ((TintTypedArray)localObject2).getResourceId(R.styleable.AppCompatTextHelper_android_textAppearance, -1);
    if (((TintTypedArray)localObject2).hasValue(R.styleable.AppCompatTextHelper_android_drawableLeft)) {
      mDrawableLeftTint = createTintInfo(localContext, (AppCompatDrawableManager)localObject1, ((TintTypedArray)localObject2).getResourceId(R.styleable.AppCompatTextHelper_android_drawableLeft, 0));
    }
    if (((TintTypedArray)localObject2).hasValue(R.styleable.AppCompatTextHelper_android_drawableTop)) {
      mDrawableTopTint = createTintInfo(localContext, (AppCompatDrawableManager)localObject1, ((TintTypedArray)localObject2).getResourceId(R.styleable.AppCompatTextHelper_android_drawableTop, 0));
    }
    if (((TintTypedArray)localObject2).hasValue(R.styleable.AppCompatTextHelper_android_drawableRight)) {
      mDrawableRightTint = createTintInfo(localContext, (AppCompatDrawableManager)localObject1, ((TintTypedArray)localObject2).getResourceId(R.styleable.AppCompatTextHelper_android_drawableRight, 0));
    }
    if (((TintTypedArray)localObject2).hasValue(R.styleable.AppCompatTextHelper_android_drawableBottom)) {
      mDrawableBottomTint = createTintInfo(localContext, (AppCompatDrawableManager)localObject1, ((TintTypedArray)localObject2).getResourceId(R.styleable.AppCompatTextHelper_android_drawableBottom, 0));
    }
    ((TintTypedArray)localObject2).recycle();
    boolean bool2 = mView.getTransformationMethod() instanceof PasswordTransformationMethod;
    int j = 1;
    Object localObject3 = null;
    localObject2 = null;
    ColorStateList localColorStateList1 = null;
    boolean bool1;
    if (i != -1)
    {
      localObject4 = TintTypedArray.obtainStyledAttributes(localContext, i, R.styleable.TextAppearance);
      if ((!bool2) && (((TintTypedArray)localObject4).hasValue(R.styleable.TextAppearance_textAllCaps)))
      {
        bool1 = ((TintTypedArray)localObject4).getBoolean(R.styleable.TextAppearance_textAllCaps, false);
        i = 1;
      }
      else
      {
        i = 0;
        bool1 = false;
      }
      updateTypefaceAndStyle(localContext, (TintTypedArray)localObject4);
      if (Build.VERSION.SDK_INT < 23)
      {
        if (((TintTypedArray)localObject4).hasValue(R.styleable.TextAppearance_android_textColor)) {
          localObject1 = ((TintTypedArray)localObject4).getColorStateList(R.styleable.TextAppearance_android_textColor);
        } else {
          localObject1 = null;
        }
        if (((TintTypedArray)localObject4).hasValue(R.styleable.TextAppearance_android_textColorHint)) {
          localObject2 = ((TintTypedArray)localObject4).getColorStateList(R.styleable.TextAppearance_android_textColorHint);
        } else {
          localObject2 = null;
        }
        if (((TintTypedArray)localObject4).hasValue(R.styleable.TextAppearance_android_textColorLink)) {
          localColorStateList1 = ((TintTypedArray)localObject4).getColorStateList(R.styleable.TextAppearance_android_textColorLink);
        }
        localObject3 = localObject1;
        localObject1 = localObject2;
      }
      else
      {
        localColorStateList1 = null;
        localObject1 = null;
      }
      ((TintTypedArray)localObject4).recycle();
      localObject2 = localObject3;
    }
    else
    {
      localColorStateList1 = null;
      localObject1 = null;
      i = 0;
      bool1 = false;
    }
    TintTypedArray localTintTypedArray = TintTypedArray.obtainStyledAttributes(localContext, paramAttributeSet, R.styleable.TextAppearance, paramInt, 0);
    if ((!bool2) && (localTintTypedArray.hasValue(R.styleable.TextAppearance_textAllCaps)))
    {
      bool1 = localTintTypedArray.getBoolean(R.styleable.TextAppearance_textAllCaps, false);
      i = j;
    }
    localObject3 = localObject2;
    Object localObject4 = localObject1;
    ColorStateList localColorStateList2 = localColorStateList1;
    if (Build.VERSION.SDK_INT < 23)
    {
      if (localTintTypedArray.hasValue(R.styleable.TextAppearance_android_textColor)) {
        localObject2 = localTintTypedArray.getColorStateList(R.styleable.TextAppearance_android_textColor);
      }
      if (localTintTypedArray.hasValue(R.styleable.TextAppearance_android_textColorHint)) {
        localObject1 = localTintTypedArray.getColorStateList(R.styleable.TextAppearance_android_textColorHint);
      }
      localObject3 = localObject2;
      localObject4 = localObject1;
      localColorStateList2 = localColorStateList1;
      if (localTintTypedArray.hasValue(R.styleable.TextAppearance_android_textColorLink))
      {
        localColorStateList2 = localTintTypedArray.getColorStateList(R.styleable.TextAppearance_android_textColorLink);
        localObject4 = localObject1;
        localObject3 = localObject2;
      }
    }
    updateTypefaceAndStyle(localContext, localTintTypedArray);
    localTintTypedArray.recycle();
    if (localObject3 != null) {
      mView.setTextColor(localObject3);
    }
    if (localObject4 != null) {
      mView.setHintTextColor((ColorStateList)localObject4);
    }
    if (localColorStateList2 != null) {
      mView.setLinkTextColor(localColorStateList2);
    }
    if ((!bool2) && (i != 0)) {
      setAllCaps(bool1);
    }
    if (mFontTypeface != null) {
      mView.setTypeface(mFontTypeface, mStyle);
    }
    mAutoSizeTextHelper.loadFromAttributes(paramAttributeSet, paramInt);
    if ((AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE) && (mAutoSizeTextHelper.getAutoSizeTextType() != 0))
    {
      paramAttributeSet = mAutoSizeTextHelper.getAutoSizeTextAvailableSizes();
      if (paramAttributeSet.length > 0)
      {
        if (mView.getAutoSizeStepGranularity() != -1.0F)
        {
          mView.setAutoSizeTextTypeUniformWithConfiguration(mAutoSizeTextHelper.getAutoSizeMinTextSize(), mAutoSizeTextHelper.getAutoSizeMaxTextSize(), mAutoSizeTextHelper.getAutoSizeStepGranularity(), 0);
          return;
        }
        mView.setAutoSizeTextTypeUniformWithPresetSizes(paramAttributeSet, 0);
      }
    }
  }
  
  void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (!AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE) {
      autoSizeText();
    }
  }
  
  void onSetTextAppearance(Context paramContext, int paramInt)
  {
    TintTypedArray localTintTypedArray = TintTypedArray.obtainStyledAttributes(paramContext, paramInt, R.styleable.TextAppearance);
    if (localTintTypedArray.hasValue(R.styleable.TextAppearance_textAllCaps)) {
      setAllCaps(localTintTypedArray.getBoolean(R.styleable.TextAppearance_textAllCaps, false));
    }
    if ((Build.VERSION.SDK_INT < 23) && (localTintTypedArray.hasValue(R.styleable.TextAppearance_android_textColor)))
    {
      ColorStateList localColorStateList = localTintTypedArray.getColorStateList(R.styleable.TextAppearance_android_textColor);
      if (localColorStateList != null) {
        mView.setTextColor(localColorStateList);
      }
    }
    updateTypefaceAndStyle(paramContext, localTintTypedArray);
    localTintTypedArray.recycle();
    if (mFontTypeface != null) {
      mView.setTypeface(mFontTypeface, mStyle);
    }
  }
  
  void setAllCaps(boolean paramBoolean)
  {
    mView.setAllCaps(paramBoolean);
  }
  
  void setAutoSizeTextTypeUniformWithConfiguration(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    throws IllegalArgumentException
  {
    mAutoSizeTextHelper.setAutoSizeTextTypeUniformWithConfiguration(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  void setAutoSizeTextTypeUniformWithPresetSizes(int[] paramArrayOfInt, int paramInt)
    throws IllegalArgumentException
  {
    mAutoSizeTextHelper.setAutoSizeTextTypeUniformWithPresetSizes(paramArrayOfInt, paramInt);
  }
  
  void setAutoSizeTextTypeWithDefaults(int paramInt)
  {
    mAutoSizeTextHelper.setAutoSizeTextTypeWithDefaults(paramInt);
  }
  
  void setTextSize(int paramInt, float paramFloat)
  {
    if ((!AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE) && (!isAutoSizeEnabled())) {
      setTextSizeInternal(paramInt, paramFloat);
    }
  }
}
