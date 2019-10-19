package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.support.v7.appcompat.R.styleable;
import android.text.Layout;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.StaticLayout.Builder;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

class AppCompatTextViewAutoSizeHelper
{
  private static final int DEFAULT_AUTO_SIZE_GRANULARITY_IN_PX = 1;
  private static final int DEFAULT_AUTO_SIZE_MAX_TEXT_SIZE_IN_SP = 112;
  private static final int DEFAULT_AUTO_SIZE_MIN_TEXT_SIZE_IN_SP = 12;
  private static final String PAGE_KEY = "ACTVAutoSizeHelper";
  private static final RectF TEMP_RECTF = new RectF();
  static final float UNSET_AUTO_SIZE_UNIFORM_CONFIGURATION_VALUE = -1.0F;
  private static final int VERY_WIDE = 1048576;
  private static ConcurrentHashMap<String, Method> sTextViewMethodByNameCache = new ConcurrentHashMap();
  private float mAutoSizeMaxTextSizeInPx = -1.0F;
  private float mAutoSizeMinTextSizeInPx = -1.0F;
  private float mAutoSizeStepGranularityInPx = -1.0F;
  private int[] mAutoSizeTextSizesInPx = new int[0];
  private int mAutoSizeTextType = 0;
  private final Context mContext;
  private boolean mHasPresetAutoSizeValues = false;
  private boolean mNeedsAutoSizeText = false;
  private TextPaint mTempTextPaint;
  private final TextView mTextView;
  
  AppCompatTextViewAutoSizeHelper(TextView paramTextView)
  {
    mTextView = paramTextView;
    mContext = mTextView.getContext();
  }
  
  private int[] cleanupAutoSizePresetSizes(int[] paramArrayOfInt)
  {
    int k = paramArrayOfInt.length;
    if (k == 0) {
      return paramArrayOfInt;
    }
    Arrays.sort(paramArrayOfInt);
    ArrayList localArrayList = new ArrayList();
    int j = 0;
    int i = 0;
    while (i < k)
    {
      int m = paramArrayOfInt[i];
      if ((m > 0) && (Collections.binarySearch(localArrayList, Integer.valueOf(m)) < 0)) {
        localArrayList.add(Integer.valueOf(m));
      }
      i += 1;
    }
    if (k == localArrayList.size()) {
      return paramArrayOfInt;
    }
    k = localArrayList.size();
    paramArrayOfInt = new int[k];
    i = j;
    while (i < k)
    {
      paramArrayOfInt[i] = ((Integer)localArrayList.get(i)).intValue();
      i += 1;
    }
    return paramArrayOfInt;
  }
  
  private void clearAutoSizeConfiguration()
  {
    mAutoSizeTextType = 0;
    mAutoSizeMinTextSizeInPx = -1.0F;
    mAutoSizeMaxTextSizeInPx = -1.0F;
    mAutoSizeStepGranularityInPx = -1.0F;
    mAutoSizeTextSizesInPx = new int[0];
    mNeedsAutoSizeText = false;
  }
  
  private StaticLayout createStaticLayoutForMeasuring(CharSequence paramCharSequence, Layout.Alignment paramAlignment, int paramInt1, int paramInt2)
  {
    TextDirectionHeuristic localTextDirectionHeuristic = (TextDirectionHeuristic)invokeAndReturnWithDefault(mTextView, "getTextDirectionHeuristic", TextDirectionHeuristics.FIRSTSTRONG_LTR);
    paramCharSequence = StaticLayout.Builder.obtain(paramCharSequence, 0, paramCharSequence.length(), mTempTextPaint, paramInt1).setAlignment(paramAlignment).setLineSpacing(mTextView.getLineSpacingExtra(), mTextView.getLineSpacingMultiplier()).setIncludePad(mTextView.getIncludeFontPadding()).setBreakStrategy(mTextView.getBreakStrategy()).setHyphenationFrequency(mTextView.getHyphenationFrequency());
    paramInt1 = paramInt2;
    if (paramInt2 == -1) {
      paramInt1 = Integer.MAX_VALUE;
    }
    return paramCharSequence.setMaxLines(paramInt1).setTextDirection(localTextDirectionHeuristic).build();
  }
  
  private StaticLayout createStaticLayoutForMeasuringPre23(CharSequence paramCharSequence, Layout.Alignment paramAlignment, int paramInt)
  {
    float f1;
    float f2;
    if (Build.VERSION.SDK_INT >= 16)
    {
      f1 = mTextView.getLineSpacingMultiplier();
      f2 = mTextView.getLineSpacingExtra();
    }
    for (boolean bool = mTextView.getIncludeFontPadding();; bool = ((Boolean)invokeAndReturnWithDefault(mTextView, "getIncludeFontPadding", Boolean.valueOf(true))).booleanValue())
    {
      break;
      f1 = ((Float)invokeAndReturnWithDefault(mTextView, "getLineSpacingMultiplier", Float.valueOf(1.0F))).floatValue();
      f2 = ((Float)invokeAndReturnWithDefault(mTextView, "getLineSpacingExtra", Float.valueOf(0.0F))).floatValue();
    }
    return new StaticLayout(paramCharSequence, mTempTextPaint, paramInt, paramAlignment, f1, f2, bool);
  }
  
  private int findLargestTextSizeWhichFits(RectF paramRectF)
  {
    int i = mAutoSizeTextSizesInPx.length;
    if (i != 0)
    {
      int k = i - 1;
      i = 1;
      int j = 0;
      while (i <= k)
      {
        int m = (i + k) / 2;
        if (suggestedSizeFitsInSpace(mAutoSizeTextSizesInPx[m], paramRectF))
        {
          j = i;
          i = m + 1;
        }
        else
        {
          j = m - 1;
          k = j;
        }
      }
      return mAutoSizeTextSizesInPx[j];
    }
    throw new IllegalStateException("No available text sizes to choose from.");
  }
  
  private Method getTextViewMethod(String paramString)
  {
    Object localObject1 = sTextViewMethodByNameCache;
    try
    {
      localObject1 = ((ConcurrentHashMap)localObject1).get(paramString);
      localObject2 = (Method)localObject1;
      localObject1 = localObject2;
      if (localObject2 == null)
      {
        localObject2 = TextView.class.getDeclaredMethod(paramString, new Class[0]);
        localObject1 = localObject2;
        if (localObject2 != null)
        {
          ((Method)localObject2).setAccessible(true);
          localObject1 = sTextViewMethodByNameCache;
          ((ConcurrentHashMap)localObject1).put(paramString, localObject2);
          return localObject2;
        }
      }
    }
    catch (Exception localException)
    {
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("Failed to retrieve TextView#");
      ((StringBuilder)localObject2).append(paramString);
      ((StringBuilder)localObject2).append("() method");
      Log.w("ACTVAutoSizeHelper", ((StringBuilder)localObject2).toString(), localException);
      return null;
    }
    return localException;
  }
  
  private Object invokeAndReturnWithDefault(Object paramObject1, String paramString, Object paramObject2)
  {
    try
    {
      localObject = getTextViewMethod(paramString);
      paramObject1 = ((Method)localObject).invoke(paramObject1, new Object[0]);
      return paramObject1;
    }
    catch (Throwable paramObject1) {}catch (Exception paramObject1)
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Failed to invoke TextView#");
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append("() method");
      Log.w("ACTVAutoSizeHelper", ((StringBuilder)localObject).toString(), paramObject1);
      return paramObject2;
    }
    throw paramObject1;
  }
  
  private void setRawTextSize(float paramFloat)
  {
    if (paramFloat != mTextView.getPaint().getTextSize())
    {
      mTextView.getPaint().setTextSize(paramFloat);
      boolean bool;
      if (Build.VERSION.SDK_INT >= 18) {
        bool = mTextView.isInLayout();
      } else {
        bool = false;
      }
      if (mTextView.getLayout() != null)
      {
        mNeedsAutoSizeText = false;
        try
        {
          Method localMethod = getTextViewMethod("nullLayouts");
          if (localMethod != null)
          {
            TextView localTextView = mTextView;
            localMethod.invoke(localTextView, new Object[0]);
          }
        }
        catch (Exception localException)
        {
          Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#nullLayouts() method", localException);
        }
        if (!bool) {
          mTextView.requestLayout();
        } else {
          mTextView.forceLayout();
        }
        mTextView.invalidate();
      }
    }
  }
  
  private boolean setupAutoSizeText()
  {
    boolean bool = supportsAutoSizeText();
    int j = 0;
    if ((bool) && (mAutoSizeTextType == 1))
    {
      if ((!mHasPresetAutoSizeValues) || (mAutoSizeTextSizesInPx.length == 0))
      {
        float f = Math.round(mAutoSizeMinTextSizeInPx);
        int i = 1;
        while (Math.round(mAutoSizeStepGranularityInPx + f) <= Math.round(mAutoSizeMaxTextSizeInPx))
        {
          i += 1;
          f += mAutoSizeStepGranularityInPx;
        }
        int[] arrayOfInt = new int[i];
        f = mAutoSizeMinTextSizeInPx;
        while (j < i)
        {
          arrayOfInt[j] = Math.round(f);
          f += mAutoSizeStepGranularityInPx;
          j += 1;
        }
        mAutoSizeTextSizesInPx = cleanupAutoSizePresetSizes(arrayOfInt);
      }
      mNeedsAutoSizeText = true;
    }
    else
    {
      mNeedsAutoSizeText = false;
    }
    return mNeedsAutoSizeText;
  }
  
  private void setupAutoSizeUniformPresetSizes(TypedArray paramTypedArray)
  {
    int j = paramTypedArray.length();
    int[] arrayOfInt = new int[j];
    if (j > 0)
    {
      int i = 0;
      while (i < j)
      {
        arrayOfInt[i] = paramTypedArray.getDimensionPixelSize(i, -1);
        i += 1;
      }
      mAutoSizeTextSizesInPx = cleanupAutoSizePresetSizes(arrayOfInt);
      setupAutoSizeUniformPresetSizesConfiguration();
    }
  }
  
  private boolean setupAutoSizeUniformPresetSizesConfiguration()
  {
    int i = mAutoSizeTextSizesInPx.length;
    boolean bool;
    if (i > 0) {
      bool = true;
    } else {
      bool = false;
    }
    mHasPresetAutoSizeValues = bool;
    if (mHasPresetAutoSizeValues)
    {
      mAutoSizeTextType = 1;
      mAutoSizeMinTextSizeInPx = mAutoSizeTextSizesInPx[0];
      mAutoSizeMaxTextSizeInPx = mAutoSizeTextSizesInPx[(i - 1)];
      mAutoSizeStepGranularityInPx = -1.0F;
    }
    return mHasPresetAutoSizeValues;
  }
  
  private boolean suggestedSizeFitsInSpace(int paramInt, RectF paramRectF)
  {
    CharSequence localCharSequence = mTextView.getText();
    Object localObject2 = localCharSequence;
    TransformationMethod localTransformationMethod = mTextView.getTransformationMethod();
    Object localObject1 = localObject2;
    if (localTransformationMethod != null)
    {
      localCharSequence = localTransformationMethod.getTransformation(localCharSequence, mTextView);
      localObject1 = localObject2;
      if (localCharSequence != null) {
        localObject1 = localCharSequence;
      }
    }
    int i;
    if (Build.VERSION.SDK_INT >= 16) {
      i = mTextView.getMaxLines();
    } else {
      i = -1;
    }
    if (mTempTextPaint == null) {
      mTempTextPaint = new TextPaint();
    } else {
      mTempTextPaint.reset();
    }
    mTempTextPaint.set(mTextView.getPaint());
    mTempTextPaint.setTextSize(paramInt);
    localObject2 = (Layout.Alignment)invokeAndReturnWithDefault(mTextView, "getLayoutAlignment", Layout.Alignment.ALIGN_NORMAL);
    if (Build.VERSION.SDK_INT >= 23) {
      localObject2 = createStaticLayoutForMeasuring((CharSequence)localObject1, (Layout.Alignment)localObject2, Math.round(right), i);
    } else {
      localObject2 = createStaticLayoutForMeasuringPre23((CharSequence)localObject1, (Layout.Alignment)localObject2, Math.round(right));
    }
    if (i != -1)
    {
      if (((StaticLayout)localObject2).getLineCount() > i) {
        break label252;
      }
      if (((Layout)localObject2).getLineEnd(((StaticLayout)localObject2).getLineCount() - 1) != ((CharSequence)localObject1).length()) {
        return false;
      }
    }
    return ((Layout)localObject2).getHeight() <= bottom;
    label252:
    return false;
  }
  
  private boolean supportsAutoSizeText()
  {
    return !(mTextView instanceof AppCompatEditText);
  }
  
  private void validateAndSetAutoSizeTextTypeUniformConfiguration(float paramFloat1, float paramFloat2, float paramFloat3)
    throws IllegalArgumentException
  {
    if (paramFloat1 > 0.0F)
    {
      if (paramFloat2 > paramFloat1)
      {
        if (paramFloat3 > 0.0F)
        {
          mAutoSizeTextType = 1;
          mAutoSizeMinTextSizeInPx = paramFloat1;
          mAutoSizeMaxTextSizeInPx = paramFloat2;
          mAutoSizeStepGranularityInPx = paramFloat3;
          mHasPresetAutoSizeValues = false;
          return;
        }
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("The auto-size step granularity (");
        localStringBuilder.append(paramFloat3);
        localStringBuilder.append("px) is less or equal to (0px)");
        throw new IllegalArgumentException(localStringBuilder.toString());
      }
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Maximum auto-size text size (");
      localStringBuilder.append(paramFloat2);
      localStringBuilder.append("px) is less or equal to minimum auto-size ");
      localStringBuilder.append("text size (");
      localStringBuilder.append(paramFloat1);
      localStringBuilder.append("px)");
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Minimum auto-size text size (");
    localStringBuilder.append(paramFloat1);
    localStringBuilder.append("px) is less or equal to (0px)");
    throw new IllegalArgumentException(localStringBuilder.toString());
  }
  
  void autoSizeText()
  {
    if (!isAutoSizeEnabled()) {
      return;
    }
    if (mNeedsAutoSizeText) {
      if (mTextView.getMeasuredHeight() > 0)
      {
        if (mTextView.getMeasuredWidth() <= 0) {
          return;
        }
        int i;
        if (((Boolean)invokeAndReturnWithDefault(mTextView, "getHorizontallyScrolling", Boolean.valueOf(false))).booleanValue()) {
          i = 1048576;
        } else {
          i = mTextView.getMeasuredWidth() - mTextView.getTotalPaddingLeft() - mTextView.getTotalPaddingRight();
        }
        int j = mTextView.getHeight() - mTextView.getCompoundPaddingBottom() - mTextView.getCompoundPaddingTop();
        if (i <= 0) {
          return;
        }
        if (j <= 0) {
          return;
        }
        RectF localRectF = TEMP_RECTF;
        try
        {
          TEMP_RECTF.setEmpty();
          TEMP_RECTFright = i;
          TEMP_RECTFbottom = j;
          float f = findLargestTextSizeWhichFits(TEMP_RECTF);
          if (f != mTextView.getTextSize()) {
            setTextSizeInternal(0, f);
          }
        }
        catch (Throwable localThrowable)
        {
          throw localThrowable;
        }
      }
      else
      {
        return;
      }
    }
    mNeedsAutoSizeText = true;
  }
  
  int getAutoSizeMaxTextSize()
  {
    return Math.round(mAutoSizeMaxTextSizeInPx);
  }
  
  int getAutoSizeMinTextSize()
  {
    return Math.round(mAutoSizeMinTextSizeInPx);
  }
  
  int getAutoSizeStepGranularity()
  {
    return Math.round(mAutoSizeStepGranularityInPx);
  }
  
  int[] getAutoSizeTextAvailableSizes()
  {
    return mAutoSizeTextSizesInPx;
  }
  
  int getAutoSizeTextType()
  {
    return mAutoSizeTextType;
  }
  
  boolean isAutoSizeEnabled()
  {
    return (supportsAutoSizeText()) && (mAutoSizeTextType != 0);
  }
  
  void loadFromAttributes(AttributeSet paramAttributeSet, int paramInt)
  {
    paramAttributeSet = mContext.obtainStyledAttributes(paramAttributeSet, R.styleable.AppCompatTextView, paramInt, 0);
    if (paramAttributeSet.hasValue(R.styleable.AppCompatTextView_autoSizeTextType)) {
      mAutoSizeTextType = paramAttributeSet.getInt(R.styleable.AppCompatTextView_autoSizeTextType, 0);
    }
    float f1;
    if (paramAttributeSet.hasValue(R.styleable.AppCompatTextView_autoSizeStepGranularity)) {
      f1 = paramAttributeSet.getDimension(R.styleable.AppCompatTextView_autoSizeStepGranularity, -1.0F);
    } else {
      f1 = -1.0F;
    }
    float f2;
    if (paramAttributeSet.hasValue(R.styleable.AppCompatTextView_autoSizeMinTextSize)) {
      f2 = paramAttributeSet.getDimension(R.styleable.AppCompatTextView_autoSizeMinTextSize, -1.0F);
    } else {
      f2 = -1.0F;
    }
    float f3;
    if (paramAttributeSet.hasValue(R.styleable.AppCompatTextView_autoSizeMaxTextSize)) {
      f3 = paramAttributeSet.getDimension(R.styleable.AppCompatTextView_autoSizeMaxTextSize, -1.0F);
    } else {
      f3 = -1.0F;
    }
    if (paramAttributeSet.hasValue(R.styleable.AppCompatTextView_autoSizePresetSizes))
    {
      paramInt = paramAttributeSet.getResourceId(R.styleable.AppCompatTextView_autoSizePresetSizes, 0);
      if (paramInt > 0)
      {
        TypedArray localTypedArray = paramAttributeSet.getResources().obtainTypedArray(paramInt);
        setupAutoSizeUniformPresetSizes(localTypedArray);
        localTypedArray.recycle();
      }
    }
    paramAttributeSet.recycle();
    if (supportsAutoSizeText())
    {
      if (mAutoSizeTextType == 1)
      {
        if (!mHasPresetAutoSizeValues)
        {
          paramAttributeSet = mContext.getResources().getDisplayMetrics();
          float f4 = f2;
          if (f2 == -1.0F) {
            f4 = TypedValue.applyDimension(2, 12.0F, paramAttributeSet);
          }
          f2 = f3;
          if (f3 == -1.0F) {
            f2 = TypedValue.applyDimension(2, 112.0F, paramAttributeSet);
          }
          f3 = f1;
          if (f1 == -1.0F) {
            f3 = 1.0F;
          }
          validateAndSetAutoSizeTextTypeUniformConfiguration(f4, f2, f3);
        }
        setupAutoSizeText();
      }
    }
    else {
      mAutoSizeTextType = 0;
    }
  }
  
  void setAutoSizeTextTypeUniformWithConfiguration(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    throws IllegalArgumentException
  {
    if (supportsAutoSizeText())
    {
      DisplayMetrics localDisplayMetrics = mContext.getResources().getDisplayMetrics();
      validateAndSetAutoSizeTextTypeUniformConfiguration(TypedValue.applyDimension(paramInt4, paramInt1, localDisplayMetrics), TypedValue.applyDimension(paramInt4, paramInt2, localDisplayMetrics), TypedValue.applyDimension(paramInt4, paramInt3, localDisplayMetrics));
      if (setupAutoSizeText()) {
        autoSizeText();
      }
    }
  }
  
  void setAutoSizeTextTypeUniformWithPresetSizes(int[] paramArrayOfInt, int paramInt)
    throws IllegalArgumentException
  {
    if (supportsAutoSizeText())
    {
      int j = paramArrayOfInt.length;
      int i = 0;
      if (j > 0)
      {
        int[] arrayOfInt = new int[j];
        Object localObject;
        if (paramInt == 0)
        {
          localObject = Arrays.copyOf(paramArrayOfInt, j);
        }
        else
        {
          DisplayMetrics localDisplayMetrics = mContext.getResources().getDisplayMetrics();
          for (;;)
          {
            localObject = arrayOfInt;
            if (i >= j) {
              break;
            }
            arrayOfInt[i] = Math.round(TypedValue.applyDimension(paramInt, paramArrayOfInt[i], localDisplayMetrics));
            i += 1;
          }
        }
        mAutoSizeTextSizesInPx = cleanupAutoSizePresetSizes((int[])localObject);
        if (!setupAutoSizeUniformPresetSizesConfiguration())
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("None of the preset sizes is valid: ");
          ((StringBuilder)localObject).append(Arrays.toString(paramArrayOfInt));
          throw new IllegalArgumentException(((StringBuilder)localObject).toString());
        }
      }
      else
      {
        mHasPresetAutoSizeValues = false;
      }
      if (setupAutoSizeText()) {
        autoSizeText();
      }
    }
  }
  
  void setAutoSizeTextTypeWithDefaults(int paramInt)
  {
    if (supportsAutoSizeText())
    {
      Object localObject;
      switch (paramInt)
      {
      default: 
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Unknown auto-size text type: ");
        ((StringBuilder)localObject).append(paramInt);
        throw new IllegalArgumentException(((StringBuilder)localObject).toString());
      case 1: 
        localObject = mContext.getResources().getDisplayMetrics();
        validateAndSetAutoSizeTextTypeUniformConfiguration(TypedValue.applyDimension(2, 12.0F, (DisplayMetrics)localObject), TypedValue.applyDimension(2, 112.0F, (DisplayMetrics)localObject), 1.0F);
        if (setupAutoSizeText())
        {
          autoSizeText();
          return;
        }
        break;
      case 0: 
        clearAutoSizeConfiguration();
      }
    }
  }
  
  void setTextSizeInternal(int paramInt, float paramFloat)
  {
    Resources localResources;
    if (mContext == null) {
      localResources = Resources.getSystem();
    } else {
      localResources = mContext.getResources();
    }
    setRawTextSize(TypedValue.applyDimension(paramInt, paramFloat, localResources.getDisplayMetrics()));
  }
}
