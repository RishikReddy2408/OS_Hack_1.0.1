package android.support.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import android.support.v4.content.hack.ResourcesCompat;
import android.support.v4.content.hack.TypedArrayUtils;
import android.support.v4.graphics.PathParser;
import android.support.v4.graphics.PathParser.PathDataNode;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.SimpleArrayMap;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class VectorDrawableCompat
  extends VectorDrawableCommon
{
  private static final boolean DBG_VECTOR_DRAWABLE = false;
  static final PorterDuff.Mode DEFAULT_TINT_MODE = PorterDuff.Mode.SRC_IN;
  private static final int LINECAP_BUTT = 0;
  private static final int LINECAP_ROUND = 1;
  private static final int LINECAP_SQUARE = 2;
  private static final int LINEJOIN_BEVEL = 2;
  private static final int LINEJOIN_MITER = 0;
  private static final int LINEJOIN_ROUND = 1;
  static final String LOGTAG = "VectorDrawableCompat";
  private static final int MAX_CACHED_BITMAP_SIZE = 2048;
  private static final String SHAPE_CLIP_PATH = "clip-path";
  private static final String SHAPE_GROUP = "group";
  private static final String SHAPE_PATH = "path";
  private static final String SHAPE_VECTOR = "vector";
  private boolean mAllowCaching = true;
  private Drawable.ConstantState mCachedConstantStateDelegate;
  private ColorFilter mColorFilter;
  private boolean mMutated;
  private PorterDuffColorFilter mTintFilter;
  private final Rect mTmpBounds = new Rect();
  private final float[] mTmpFloats = new float[9];
  private final Matrix mTmpMatrix = new Matrix();
  private VectorDrawableCompatState mVectorState;
  
  VectorDrawableCompat()
  {
    mVectorState = new VectorDrawableCompatState();
  }
  
  VectorDrawableCompat(VectorDrawableCompatState paramVectorDrawableCompatState)
  {
    mVectorState = paramVectorDrawableCompatState;
    mTintFilter = updateTintFilter(mTintFilter, mTint, mTintMode);
  }
  
  static int applyAlpha(int paramInt, float paramFloat)
  {
    return paramInt & 0xFFFFFF | (int)(Color.alpha(paramInt) * paramFloat) << 24;
  }
  
  public static VectorDrawableCompat create(Resources paramResources, int paramInt, Resources.Theme paramTheme)
  {
    Object localObject;
    if (Build.VERSION.SDK_INT >= 24)
    {
      localObject = new VectorDrawableCompat();
      mDelegateDrawable = ResourcesCompat.getDrawable(paramResources, paramInt, paramTheme);
      mCachedConstantStateDelegate = new VectorDrawableDelegateState(mDelegateDrawable.getConstantState());
      return localObject;
    }
    try
    {
      localObject = paramResources.getXml(paramInt);
      AttributeSet localAttributeSet = Xml.asAttributeSet((XmlPullParser)localObject);
      do
      {
        paramInt = ((XmlPullParser)localObject).next();
      } while ((paramInt != 2) && (paramInt != 1));
      if (paramInt == 2)
      {
        paramResources = createFromXmlInner(paramResources, (XmlPullParser)localObject, localAttributeSet, paramTheme);
        return paramResources;
      }
      paramResources = new XmlPullParserException("No start tag found");
      throw paramResources;
    }
    catch (IOException paramResources)
    {
      Log.e("VectorDrawableCompat", "parser error", paramResources);
    }
    catch (XmlPullParserException paramResources)
    {
      Log.e("VectorDrawableCompat", "parser error", paramResources);
    }
    return null;
  }
  
  public static VectorDrawableCompat createFromXmlInner(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme)
    throws XmlPullParserException, IOException
  {
    VectorDrawableCompat localVectorDrawableCompat = new VectorDrawableCompat();
    localVectorDrawableCompat.inflate(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
    return localVectorDrawableCompat;
  }
  
  private void inflateInternal(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme)
    throws XmlPullParserException, IOException
  {
    VectorDrawableCompatState localVectorDrawableCompatState = mVectorState;
    VPathRenderer localVPathRenderer = mVPathRenderer;
    ArrayDeque localArrayDeque = new ArrayDeque();
    localArrayDeque.push(mRootGroup);
    int k = paramXmlPullParser.getEventType();
    int m = paramXmlPullParser.getDepth();
    int j;
    for (int i = 1; (k != 1) && ((paramXmlPullParser.getDepth() >= m + 1) || (k != 3)); i = j)
    {
      if (k == 2)
      {
        Object localObject = paramXmlPullParser.getName();
        VGroup localVGroup = (VGroup)localArrayDeque.peek();
        if ("path".equals(localObject))
        {
          localObject = new VFullPath();
          ((VFullPath)localObject).inflate(paramResources, paramAttributeSet, paramTheme, paramXmlPullParser);
          mChildren.add(localObject);
          if (((VPath)localObject).getPathName() != null) {
            mVGTargetsMap.put(((VPath)localObject).getPathName(), localObject);
          }
          j = 0;
          i = mChangingConfigurations;
          mChangingConfigurations = (mChangingConfigurations | i);
        }
        else if ("clip-path".equals(localObject))
        {
          localObject = new VClipPath();
          ((VClipPath)localObject).inflate(paramResources, paramAttributeSet, paramTheme, paramXmlPullParser);
          mChildren.add(localObject);
          if (((VPath)localObject).getPathName() != null) {
            mVGTargetsMap.put(((VPath)localObject).getPathName(), localObject);
          }
          j = mChangingConfigurations;
          mChangingConfigurations = (mChangingConfigurations | j);
          j = i;
        }
        else
        {
          j = i;
          if ("group".equals(localObject))
          {
            localObject = new VGroup();
            ((VGroup)localObject).inflate(paramResources, paramAttributeSet, paramTheme, paramXmlPullParser);
            mChildren.add(localObject);
            localArrayDeque.push(localObject);
            if (((VGroup)localObject).getGroupName() != null) {
              mVGTargetsMap.put(((VGroup)localObject).getGroupName(), localObject);
            }
            j = mChangingConfigurations;
            mChangingConfigurations = (mChangingConfigurations | j);
            j = i;
          }
        }
      }
      else
      {
        j = i;
        if (k == 3)
        {
          j = i;
          if ("group".equals(paramXmlPullParser.getName()))
          {
            localArrayDeque.pop();
            j = i;
          }
        }
      }
      k = paramXmlPullParser.next();
    }
    if (i == 0) {
      return;
    }
    throw new XmlPullParserException("no path defined");
  }
  
  private boolean needMirroring()
  {
    return (Build.VERSION.SDK_INT >= 17) && (isAutoMirrored()) && (DrawableCompat.getLayoutDirection(this) == 1);
  }
  
  private static PorterDuff.Mode parseTintModeCompat(int paramInt, PorterDuff.Mode paramMode)
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
  
  private void printGroupTree(VGroup paramVGroup, int paramInt)
  {
    int j = 0;
    Object localObject = "";
    int i = 0;
    while (i < paramInt)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append((String)localObject);
      localStringBuilder.append("    ");
      localObject = localStringBuilder.toString();
      i += 1;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append((String)localObject);
    localStringBuilder.append("current group is :");
    localStringBuilder.append(paramVGroup.getGroupName());
    localStringBuilder.append(" rotation is ");
    localStringBuilder.append(mRotate);
    Log.v("VectorDrawableCompat", localStringBuilder.toString());
    localStringBuilder = new StringBuilder();
    localStringBuilder.append((String)localObject);
    localStringBuilder.append("matrix is :");
    localStringBuilder.append(paramVGroup.getLocalMatrix().toString());
    Log.v("VectorDrawableCompat", localStringBuilder.toString());
    i = j;
    while (i < mChildren.size())
    {
      localObject = mChildren.get(i);
      if ((localObject instanceof VGroup)) {
        printGroupTree((VGroup)localObject, paramInt + 1);
      } else {
        ((VPath)localObject).printVPath(paramInt + 1);
      }
      i += 1;
    }
  }
  
  private void updateStateFromTypedArray(TypedArray paramTypedArray, XmlPullParser paramXmlPullParser)
    throws XmlPullParserException
  {
    VectorDrawableCompatState localVectorDrawableCompatState = mVectorState;
    VPathRenderer localVPathRenderer = mVPathRenderer;
    mTintMode = parseTintModeCompat(TypedArrayUtils.getNamedInt(paramTypedArray, paramXmlPullParser, "tintMode", 6, -1), PorterDuff.Mode.SRC_IN);
    ColorStateList localColorStateList = paramTypedArray.getColorStateList(1);
    if (localColorStateList != null) {
      mTint = localColorStateList;
    }
    mAutoMirrored = TypedArrayUtils.getNamedBoolean(paramTypedArray, paramXmlPullParser, "autoMirrored", 5, mAutoMirrored);
    mViewportWidth = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "viewportWidth", 7, mViewportWidth);
    mViewportHeight = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "viewportHeight", 8, mViewportHeight);
    if (mViewportWidth > 0.0F)
    {
      if (mViewportHeight > 0.0F)
      {
        mBaseWidth = paramTypedArray.getDimension(3, mBaseWidth);
        mBaseHeight = paramTypedArray.getDimension(2, mBaseHeight);
        if (mBaseWidth > 0.0F)
        {
          if (mBaseHeight > 0.0F)
          {
            localVPathRenderer.setAlpha(TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "alpha", 4, localVPathRenderer.getAlpha()));
            paramTypedArray = paramTypedArray.getString(0);
            if (paramTypedArray != null)
            {
              mRootName = paramTypedArray;
              mVGTargetsMap.put(paramTypedArray, localVPathRenderer);
            }
          }
          else
          {
            paramXmlPullParser = new StringBuilder();
            paramXmlPullParser.append(paramTypedArray.getPositionDescription());
            paramXmlPullParser.append("<vector> tag requires height > 0");
            throw new XmlPullParserException(paramXmlPullParser.toString());
          }
        }
        else
        {
          paramXmlPullParser = new StringBuilder();
          paramXmlPullParser.append(paramTypedArray.getPositionDescription());
          paramXmlPullParser.append("<vector> tag requires width > 0");
          throw new XmlPullParserException(paramXmlPullParser.toString());
        }
      }
      else
      {
        paramXmlPullParser = new StringBuilder();
        paramXmlPullParser.append(paramTypedArray.getPositionDescription());
        paramXmlPullParser.append("<vector> tag requires viewportHeight > 0");
        throw new XmlPullParserException(paramXmlPullParser.toString());
      }
    }
    else
    {
      paramXmlPullParser = new StringBuilder();
      paramXmlPullParser.append(paramTypedArray.getPositionDescription());
      paramXmlPullParser.append("<vector> tag requires viewportWidth > 0");
      throw new XmlPullParserException(paramXmlPullParser.toString());
    }
  }
  
  public boolean canApplyTheme()
  {
    if (mDelegateDrawable != null) {
      DrawableCompat.canApplyTheme(mDelegateDrawable);
    }
    return false;
  }
  
  public void draw(Canvas paramCanvas)
  {
    if (mDelegateDrawable != null)
    {
      mDelegateDrawable.draw(paramCanvas);
      return;
    }
    copyBounds(mTmpBounds);
    if (mTmpBounds.width() > 0)
    {
      if (mTmpBounds.height() <= 0) {
        return;
      }
      Object localObject;
      if (mColorFilter == null) {
        localObject = mTintFilter;
      } else {
        localObject = mColorFilter;
      }
      paramCanvas.getMatrix(mTmpMatrix);
      mTmpMatrix.getValues(mTmpFloats);
      float f1 = Math.abs(mTmpFloats[0]);
      float f2 = Math.abs(mTmpFloats[4]);
      float f4 = Math.abs(mTmpFloats[1]);
      float f3 = Math.abs(mTmpFloats[3]);
      if ((f4 != 0.0F) || (f3 != 0.0F))
      {
        f1 = 1.0F;
        f2 = 1.0F;
      }
      int i = (int)(mTmpBounds.width() * f1);
      int j = (int)(mTmpBounds.height() * f2);
      i = Math.min(2048, i);
      j = Math.min(2048, j);
      if (i > 0)
      {
        if (j <= 0) {
          return;
        }
        int k = paramCanvas.save();
        paramCanvas.translate(mTmpBounds.left, mTmpBounds.top);
        if (needMirroring())
        {
          paramCanvas.translate(mTmpBounds.width(), 0.0F);
          paramCanvas.scale(-1.0F, 1.0F);
        }
        mTmpBounds.offsetTo(0, 0);
        mVectorState.createCachedBitmapIfNeeded(i, j);
        if (!mAllowCaching)
        {
          mVectorState.updateCachedBitmap(i, j);
        }
        else if (!mVectorState.canReuseCache())
        {
          mVectorState.updateCachedBitmap(i, j);
          mVectorState.updateCacheStates();
        }
        mVectorState.drawCachedBitmapWithRootAlpha(paramCanvas, (ColorFilter)localObject, mTmpBounds);
        paramCanvas.restoreToCount(k);
      }
    }
  }
  
  public int getAlpha()
  {
    if (mDelegateDrawable != null) {
      return DrawableCompat.getAlpha(mDelegateDrawable);
    }
    return mVectorState.mVPathRenderer.getRootAlpha();
  }
  
  public int getChangingConfigurations()
  {
    if (mDelegateDrawable != null) {
      return mDelegateDrawable.getChangingConfigurations();
    }
    return super.getChangingConfigurations() | mVectorState.getChangingConfigurations();
  }
  
  public Drawable.ConstantState getConstantState()
  {
    if ((mDelegateDrawable != null) && (Build.VERSION.SDK_INT >= 24)) {
      return new VectorDrawableDelegateState(mDelegateDrawable.getConstantState());
    }
    mVectorState.mChangingConfigurations = getChangingConfigurations();
    return mVectorState;
  }
  
  public int getIntrinsicHeight()
  {
    if (mDelegateDrawable != null) {
      return mDelegateDrawable.getIntrinsicHeight();
    }
    return (int)mVectorState.mVPathRenderer.mBaseHeight;
  }
  
  public int getIntrinsicWidth()
  {
    if (mDelegateDrawable != null) {
      return mDelegateDrawable.getIntrinsicWidth();
    }
    return (int)mVectorState.mVPathRenderer.mBaseWidth;
  }
  
  public int getOpacity()
  {
    if (mDelegateDrawable != null) {
      return mDelegateDrawable.getOpacity();
    }
    return -3;
  }
  
  public float getPixelSize()
  {
    if ((mVectorState != null) && (mVectorState.mVPathRenderer != null) && (mVectorState.mVPathRenderer.mBaseWidth != 0.0F) && (mVectorState.mVPathRenderer.mBaseHeight != 0.0F) && (mVectorState.mVPathRenderer.mViewportHeight != 0.0F) && (mVectorState.mVPathRenderer.mViewportWidth != 0.0F))
    {
      float f1 = mVectorState.mVPathRenderer.mBaseWidth;
      float f2 = mVectorState.mVPathRenderer.mBaseHeight;
      float f3 = mVectorState.mVPathRenderer.mViewportWidth;
      float f4 = mVectorState.mVPathRenderer.mViewportHeight;
      return Math.min(f3 / f1, f4 / f2);
    }
    return 1.0F;
  }
  
  Object getTargetByName(String paramString)
  {
    return mVectorState.mVPathRenderer.mVGTargetsMap.get(paramString);
  }
  
  public void inflate(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet)
    throws XmlPullParserException, IOException
  {
    if (mDelegateDrawable != null)
    {
      mDelegateDrawable.inflate(paramResources, paramXmlPullParser, paramAttributeSet);
      return;
    }
    inflate(paramResources, paramXmlPullParser, paramAttributeSet, null);
  }
  
  public void inflate(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme)
    throws XmlPullParserException, IOException
  {
    if (mDelegateDrawable != null)
    {
      DrawableCompat.inflate(mDelegateDrawable, paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
      return;
    }
    VectorDrawableCompatState localVectorDrawableCompatState = mVectorState;
    mVPathRenderer = new VPathRenderer();
    TypedArray localTypedArray = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, AndroidResources.STYLEABLE_VECTOR_DRAWABLE_TYPE_ARRAY);
    updateStateFromTypedArray(localTypedArray, paramXmlPullParser);
    localTypedArray.recycle();
    mChangingConfigurations = getChangingConfigurations();
    mCacheDirty = true;
    inflateInternal(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
    mTintFilter = updateTintFilter(mTintFilter, mTint, mTintMode);
  }
  
  public void invalidateSelf()
  {
    if (mDelegateDrawable != null)
    {
      mDelegateDrawable.invalidateSelf();
      return;
    }
    super.invalidateSelf();
  }
  
  public boolean isAutoMirrored()
  {
    if (mDelegateDrawable != null) {
      return DrawableCompat.isAutoMirrored(mDelegateDrawable);
    }
    return mVectorState.mAutoMirrored;
  }
  
  public boolean isStateful()
  {
    if (mDelegateDrawable != null) {
      return mDelegateDrawable.isStateful();
    }
    return (super.isStateful()) || ((mVectorState != null) && (mVectorState.mTint != null) && (mVectorState.mTint.isStateful()));
  }
  
  public Drawable mutate()
  {
    if (mDelegateDrawable != null)
    {
      mDelegateDrawable.mutate();
      return this;
    }
    if ((!mMutated) && (super.mutate() == this))
    {
      mVectorState = new VectorDrawableCompatState(mVectorState);
      mMutated = true;
    }
    return this;
  }
  
  protected void onBoundsChange(Rect paramRect)
  {
    if (mDelegateDrawable != null) {
      mDelegateDrawable.setBounds(paramRect);
    }
  }
  
  protected boolean onStateChange(int[] paramArrayOfInt)
  {
    if (mDelegateDrawable != null) {
      return mDelegateDrawable.setState(paramArrayOfInt);
    }
    paramArrayOfInt = mVectorState;
    if ((mTint != null) && (mTintMode != null))
    {
      mTintFilter = updateTintFilter(mTintFilter, mTint, mTintMode);
      invalidateSelf();
      return true;
    }
    return false;
  }
  
  public void scheduleSelf(Runnable paramRunnable, long paramLong)
  {
    if (mDelegateDrawable != null)
    {
      mDelegateDrawable.scheduleSelf(paramRunnable, paramLong);
      return;
    }
    super.scheduleSelf(paramRunnable, paramLong);
  }
  
  void setAllowCaching(boolean paramBoolean)
  {
    mAllowCaching = paramBoolean;
  }
  
  public void setAlpha(int paramInt)
  {
    if (mDelegateDrawable != null)
    {
      mDelegateDrawable.setAlpha(paramInt);
      return;
    }
    if (mVectorState.mVPathRenderer.getRootAlpha() != paramInt)
    {
      mVectorState.mVPathRenderer.setRootAlpha(paramInt);
      invalidateSelf();
    }
  }
  
  public void setAutoMirrored(boolean paramBoolean)
  {
    if (mDelegateDrawable != null)
    {
      DrawableCompat.setAutoMirrored(mDelegateDrawable, paramBoolean);
      return;
    }
    mVectorState.mAutoMirrored = paramBoolean;
  }
  
  public void setColorFilter(ColorFilter paramColorFilter)
  {
    if (mDelegateDrawable != null)
    {
      mDelegateDrawable.setColorFilter(paramColorFilter);
      return;
    }
    mColorFilter = paramColorFilter;
    invalidateSelf();
  }
  
  public void setTint(int paramInt)
  {
    if (mDelegateDrawable != null)
    {
      DrawableCompat.setTint(mDelegateDrawable, paramInt);
      return;
    }
    setTintList(ColorStateList.valueOf(paramInt));
  }
  
  public void setTintList(ColorStateList paramColorStateList)
  {
    if (mDelegateDrawable != null)
    {
      DrawableCompat.setTintList(mDelegateDrawable, paramColorStateList);
      return;
    }
    VectorDrawableCompatState localVectorDrawableCompatState = mVectorState;
    if (mTint != paramColorStateList)
    {
      mTint = paramColorStateList;
      mTintFilter = updateTintFilter(mTintFilter, paramColorStateList, mTintMode);
      invalidateSelf();
    }
  }
  
  public void setTintMode(PorterDuff.Mode paramMode)
  {
    if (mDelegateDrawable != null)
    {
      DrawableCompat.setTintMode(mDelegateDrawable, paramMode);
      return;
    }
    VectorDrawableCompatState localVectorDrawableCompatState = mVectorState;
    if (mTintMode != paramMode)
    {
      mTintMode = paramMode;
      mTintFilter = updateTintFilter(mTintFilter, mTint, paramMode);
      invalidateSelf();
    }
  }
  
  public boolean setVisible(boolean paramBoolean1, boolean paramBoolean2)
  {
    if (mDelegateDrawable != null) {
      return mDelegateDrawable.setVisible(paramBoolean1, paramBoolean2);
    }
    return super.setVisible(paramBoolean1, paramBoolean2);
  }
  
  public void unscheduleSelf(Runnable paramRunnable)
  {
    if (mDelegateDrawable != null)
    {
      mDelegateDrawable.unscheduleSelf(paramRunnable);
      return;
    }
    super.unscheduleSelf(paramRunnable);
  }
  
  PorterDuffColorFilter updateTintFilter(PorterDuffColorFilter paramPorterDuffColorFilter, ColorStateList paramColorStateList, PorterDuff.Mode paramMode)
  {
    if ((paramColorStateList != null) && (paramMode != null)) {
      return new PorterDuffColorFilter(paramColorStateList.getColorForState(getState(), 0), paramMode);
    }
    return null;
  }
  
  private static class VClipPath
    extends VectorDrawableCompat.VPath
  {
    public VClipPath() {}
    
    public VClipPath(VClipPath paramVClipPath)
    {
      super();
    }
    
    private void updateStateFromTypedArray(TypedArray paramTypedArray)
    {
      String str = paramTypedArray.getString(0);
      if (str != null) {
        mPathName = str;
      }
      paramTypedArray = paramTypedArray.getString(1);
      if (paramTypedArray != null) {
        mNodes = PathParser.createNodesFromPathData(paramTypedArray);
      }
    }
    
    public void inflate(Resources paramResources, AttributeSet paramAttributeSet, Resources.Theme paramTheme, XmlPullParser paramXmlPullParser)
    {
      if (!TypedArrayUtils.hasAttribute(paramXmlPullParser, "pathData")) {
        return;
      }
      paramResources = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, AndroidResources.STYLEABLE_VECTOR_DRAWABLE_CLIP_PATH);
      updateStateFromTypedArray(paramResources);
      paramResources.recycle();
    }
    
    public boolean isClipPath()
    {
      return true;
    }
  }
  
  private static class VFullPath
    extends VectorDrawableCompat.VPath
  {
    private static final int FILL_TYPE_WINDING = 0;
    float mFillAlpha = 1.0F;
    int mFillColor = 0;
    int mFillRule = 0;
    float mStrokeAlpha = 1.0F;
    int mStrokeColor = 0;
    Paint.Cap mStrokeLineCap = Paint.Cap.BUTT;
    Paint.Join mStrokeLineJoin = Paint.Join.MITER;
    float mStrokeMiterlimit = 4.0F;
    float mStrokeWidth = 0.0F;
    private int[] mThemeAttrs;
    float mTrimPathEnd = 1.0F;
    float mTrimPathOffset = 0.0F;
    float mTrimPathStart = 0.0F;
    
    public VFullPath() {}
    
    public VFullPath(VFullPath paramVFullPath)
    {
      super();
      mThemeAttrs = mThemeAttrs;
      mStrokeColor = mStrokeColor;
      mStrokeWidth = mStrokeWidth;
      mStrokeAlpha = mStrokeAlpha;
      mFillColor = mFillColor;
      mFillRule = mFillRule;
      mFillAlpha = mFillAlpha;
      mTrimPathStart = mTrimPathStart;
      mTrimPathEnd = mTrimPathEnd;
      mTrimPathOffset = mTrimPathOffset;
      mStrokeLineCap = mStrokeLineCap;
      mStrokeLineJoin = mStrokeLineJoin;
      mStrokeMiterlimit = mStrokeMiterlimit;
    }
    
    private Paint.Cap getStrokeLineCap(int paramInt, Paint.Cap paramCap)
    {
      switch (paramInt)
      {
      default: 
        return paramCap;
      case 2: 
        return Paint.Cap.SQUARE;
      case 1: 
        return Paint.Cap.ROUND;
      }
      return Paint.Cap.BUTT;
    }
    
    private Paint.Join getStrokeLineJoin(int paramInt, Paint.Join paramJoin)
    {
      switch (paramInt)
      {
      default: 
        return paramJoin;
      case 2: 
        return Paint.Join.BEVEL;
      case 1: 
        return Paint.Join.ROUND;
      }
      return Paint.Join.MITER;
    }
    
    private void updateStateFromTypedArray(TypedArray paramTypedArray, XmlPullParser paramXmlPullParser)
    {
      mThemeAttrs = null;
      if (!TypedArrayUtils.hasAttribute(paramXmlPullParser, "pathData")) {
        return;
      }
      String str = paramTypedArray.getString(0);
      if (str != null) {
        mPathName = str;
      }
      str = paramTypedArray.getString(2);
      if (str != null) {
        mNodes = PathParser.createNodesFromPathData(str);
      }
      mFillColor = TypedArrayUtils.getNamedColor(paramTypedArray, paramXmlPullParser, "fillColor", 1, mFillColor);
      mFillAlpha = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "fillAlpha", 12, mFillAlpha);
      mStrokeLineCap = getStrokeLineCap(TypedArrayUtils.getNamedInt(paramTypedArray, paramXmlPullParser, "strokeLineCap", 8, -1), mStrokeLineCap);
      mStrokeLineJoin = getStrokeLineJoin(TypedArrayUtils.getNamedInt(paramTypedArray, paramXmlPullParser, "strokeLineJoin", 9, -1), mStrokeLineJoin);
      mStrokeMiterlimit = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "strokeMiterLimit", 10, mStrokeMiterlimit);
      mStrokeColor = TypedArrayUtils.getNamedColor(paramTypedArray, paramXmlPullParser, "strokeColor", 3, mStrokeColor);
      mStrokeAlpha = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "strokeAlpha", 11, mStrokeAlpha);
      mStrokeWidth = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "strokeWidth", 4, mStrokeWidth);
      mTrimPathEnd = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "trimPathEnd", 6, mTrimPathEnd);
      mTrimPathOffset = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "trimPathOffset", 7, mTrimPathOffset);
      mTrimPathStart = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "trimPathStart", 5, mTrimPathStart);
      mFillRule = TypedArrayUtils.getNamedInt(paramTypedArray, paramXmlPullParser, "fillType", 13, mFillRule);
    }
    
    public void applyTheme(Resources.Theme paramTheme)
    {
      if (mThemeAttrs == null) {}
    }
    
    public boolean canApplyTheme()
    {
      return mThemeAttrs != null;
    }
    
    float getFillAlpha()
    {
      return mFillAlpha;
    }
    
    int getFillColor()
    {
      return mFillColor;
    }
    
    float getStrokeAlpha()
    {
      return mStrokeAlpha;
    }
    
    int getStrokeColor()
    {
      return mStrokeColor;
    }
    
    float getStrokeWidth()
    {
      return mStrokeWidth;
    }
    
    float getTrimPathEnd()
    {
      return mTrimPathEnd;
    }
    
    float getTrimPathOffset()
    {
      return mTrimPathOffset;
    }
    
    float getTrimPathStart()
    {
      return mTrimPathStart;
    }
    
    public void inflate(Resources paramResources, AttributeSet paramAttributeSet, Resources.Theme paramTheme, XmlPullParser paramXmlPullParser)
    {
      paramResources = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, AndroidResources.STYLEABLE_VECTOR_DRAWABLE_PATH);
      updateStateFromTypedArray(paramResources, paramXmlPullParser);
      paramResources.recycle();
    }
    
    void setFillAlpha(float paramFloat)
    {
      mFillAlpha = paramFloat;
    }
    
    void setFillColor(int paramInt)
    {
      mFillColor = paramInt;
    }
    
    void setStrokeAlpha(float paramFloat)
    {
      mStrokeAlpha = paramFloat;
    }
    
    void setStrokeColor(int paramInt)
    {
      mStrokeColor = paramInt;
    }
    
    void setStrokeWidth(float paramFloat)
    {
      mStrokeWidth = paramFloat;
    }
    
    void setTrimPathEnd(float paramFloat)
    {
      mTrimPathEnd = paramFloat;
    }
    
    void setTrimPathOffset(float paramFloat)
    {
      mTrimPathOffset = paramFloat;
    }
    
    void setTrimPathStart(float paramFloat)
    {
      mTrimPathStart = paramFloat;
    }
  }
  
  private static class VGroup
  {
    int mChangingConfigurations;
    final ArrayList<Object> mChildren = new ArrayList();
    private String mGroupName = null;
    private final Matrix mLocalMatrix = new Matrix();
    private float mPivotX = 0.0F;
    private float mPivotY = 0.0F;
    float mRotate = 0.0F;
    private float mScaleX = 1.0F;
    private float mScaleY = 1.0F;
    private final Matrix mStackedMatrix = new Matrix();
    private int[] mThemeAttrs;
    private float mTranslateX = 0.0F;
    private float mTranslateY = 0.0F;
    
    public VGroup() {}
    
    public VGroup(VGroup paramVGroup, ArrayMap paramArrayMap) {}
    
    private void updateLocalMatrix()
    {
      mLocalMatrix.reset();
      mLocalMatrix.postTranslate(-mPivotX, -mPivotY);
      mLocalMatrix.postScale(mScaleX, mScaleY);
      mLocalMatrix.postRotate(mRotate, 0.0F, 0.0F);
      mLocalMatrix.postTranslate(mTranslateX + mPivotX, mTranslateY + mPivotY);
    }
    
    private void updateStateFromTypedArray(TypedArray paramTypedArray, XmlPullParser paramXmlPullParser)
    {
      mThemeAttrs = null;
      mRotate = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "rotation", 5, mRotate);
      mPivotX = paramTypedArray.getFloat(1, mPivotX);
      mPivotY = paramTypedArray.getFloat(2, mPivotY);
      mScaleX = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "scaleX", 3, mScaleX);
      mScaleY = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "scaleY", 4, mScaleY);
      mTranslateX = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "translateX", 6, mTranslateX);
      mTranslateY = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "translateY", 7, mTranslateY);
      paramTypedArray = paramTypedArray.getString(0);
      if (paramTypedArray != null) {
        mGroupName = paramTypedArray;
      }
      updateLocalMatrix();
    }
    
    public String getGroupName()
    {
      return mGroupName;
    }
    
    public Matrix getLocalMatrix()
    {
      return mLocalMatrix;
    }
    
    public float getPivotX()
    {
      return mPivotX;
    }
    
    public float getPivotY()
    {
      return mPivotY;
    }
    
    public float getRotation()
    {
      return mRotate;
    }
    
    public float getScaleX()
    {
      return mScaleX;
    }
    
    public float getScaleY()
    {
      return mScaleY;
    }
    
    public float getTranslateX()
    {
      return mTranslateX;
    }
    
    public float getTranslateY()
    {
      return mTranslateY;
    }
    
    public void inflate(Resources paramResources, AttributeSet paramAttributeSet, Resources.Theme paramTheme, XmlPullParser paramXmlPullParser)
    {
      paramResources = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, AndroidResources.STYLEABLE_VECTOR_DRAWABLE_GROUP);
      updateStateFromTypedArray(paramResources, paramXmlPullParser);
      paramResources.recycle();
    }
    
    public void setPivotX(float paramFloat)
    {
      if (paramFloat != mPivotX)
      {
        mPivotX = paramFloat;
        updateLocalMatrix();
      }
    }
    
    public void setPivotY(float paramFloat)
    {
      if (paramFloat != mPivotY)
      {
        mPivotY = paramFloat;
        updateLocalMatrix();
      }
    }
    
    public void setRotation(float paramFloat)
    {
      if (paramFloat != mRotate)
      {
        mRotate = paramFloat;
        updateLocalMatrix();
      }
    }
    
    public void setScaleX(float paramFloat)
    {
      if (paramFloat != mScaleX)
      {
        mScaleX = paramFloat;
        updateLocalMatrix();
      }
    }
    
    public void setScaleY(float paramFloat)
    {
      if (paramFloat != mScaleY)
      {
        mScaleY = paramFloat;
        updateLocalMatrix();
      }
    }
    
    public void setTranslateX(float paramFloat)
    {
      if (paramFloat != mTranslateX)
      {
        mTranslateX = paramFloat;
        updateLocalMatrix();
      }
    }
    
    public void setTranslateY(float paramFloat)
    {
      if (paramFloat != mTranslateY)
      {
        mTranslateY = paramFloat;
        updateLocalMatrix();
      }
    }
  }
  
  private static class VPath
  {
    int mChangingConfigurations;
    protected PathParser.PathDataNode[] mNodes = null;
    String mPathName;
    
    public VPath() {}
    
    public VPath(VPath paramVPath)
    {
      mPathName = mPathName;
      mChangingConfigurations = mChangingConfigurations;
      mNodes = PathParser.deepCopyNodes(mNodes);
    }
    
    public void applyTheme(Resources.Theme paramTheme) {}
    
    public boolean canApplyTheme()
    {
      return false;
    }
    
    public PathParser.PathDataNode[] getPathData()
    {
      return mNodes;
    }
    
    public String getPathName()
    {
      return mPathName;
    }
    
    public boolean isClipPath()
    {
      return false;
    }
    
    public String nodesToString(PathParser.PathDataNode[] paramArrayOfPathDataNode)
    {
      String str = " ";
      int i = 0;
      while (i < paramArrayOfPathDataNode.length)
      {
        Object localObject = new StringBuilder();
        ((StringBuilder)localObject).append(str);
        ((StringBuilder)localObject).append(mType);
        ((StringBuilder)localObject).append(":");
        str = ((StringBuilder)localObject).toString();
        localObject = mParams;
        int j = 0;
        while (j < localObject.length)
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append(str);
          localStringBuilder.append(localObject[j]);
          localStringBuilder.append(",");
          str = localStringBuilder.toString();
          j += 1;
        }
        i += 1;
      }
      return str;
    }
    
    public void printVPath(int paramInt)
    {
      String str = "";
      int i = 0;
      while (i < paramInt)
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(str);
        localStringBuilder.append("    ");
        str = localStringBuilder.toString();
        i += 1;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(str);
      localStringBuilder.append("current path is :");
      localStringBuilder.append(mPathName);
      localStringBuilder.append(" pathData is ");
      localStringBuilder.append(nodesToString(mNodes));
      Log.v("VectorDrawableCompat", localStringBuilder.toString());
    }
    
    public void setPathData(PathParser.PathDataNode[] paramArrayOfPathDataNode)
    {
      if (!PathParser.canMorph(mNodes, paramArrayOfPathDataNode))
      {
        mNodes = PathParser.deepCopyNodes(paramArrayOfPathDataNode);
        return;
      }
      PathParser.updateNodes(mNodes, paramArrayOfPathDataNode);
    }
    
    public void toPath(Path paramPath)
    {
      paramPath.reset();
      if (mNodes != null) {
        PathParser.PathDataNode.nodesToPath(mNodes, paramPath);
      }
    }
  }
  
  private static class VPathRenderer
  {
    private static final Matrix IDENTITY_MATRIX = new Matrix();
    float mBaseHeight = 0.0F;
    float mBaseWidth = 0.0F;
    private int mChangingConfigurations;
    private Paint mFillPaint;
    private final Matrix mFinalPathMatrix = new Matrix();
    private final Path mPath;
    private PathMeasure mPathMeasure;
    private final Path mRenderPath;
    int mRootAlpha = 255;
    final VectorDrawableCompat.VGroup mRootGroup;
    String mRootName = null;
    private Paint mStrokePaint;
    final ArrayMap<String, Object> mVGTargetsMap = new ArrayMap();
    float mViewportHeight = 0.0F;
    float mViewportWidth = 0.0F;
    
    public VPathRenderer()
    {
      mRootGroup = new VectorDrawableCompat.VGroup();
      mPath = new Path();
      mRenderPath = new Path();
    }
    
    public VPathRenderer(VPathRenderer paramVPathRenderer)
    {
      mRootGroup = new VectorDrawableCompat.VGroup(mRootGroup, mVGTargetsMap);
      mPath = new Path(mPath);
      mRenderPath = new Path(mRenderPath);
      mBaseWidth = mBaseWidth;
      mBaseHeight = mBaseHeight;
      mViewportWidth = mViewportWidth;
      mViewportHeight = mViewportHeight;
      mChangingConfigurations = mChangingConfigurations;
      mRootAlpha = mRootAlpha;
      mRootName = mRootName;
      if (mRootName != null) {
        mVGTargetsMap.put(mRootName, this);
      }
    }
    
    private static float cross(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
    {
      return paramFloat1 * paramFloat4 - paramFloat2 * paramFloat3;
    }
    
    private void drawGroupTree(VectorDrawableCompat.VGroup paramVGroup, Matrix paramMatrix, Canvas paramCanvas, int paramInt1, int paramInt2, ColorFilter paramColorFilter)
    {
      mStackedMatrix.set(paramMatrix);
      mStackedMatrix.preConcat(mLocalMatrix);
      paramCanvas.save();
      int i = 0;
      while (i < mChildren.size())
      {
        paramMatrix = mChildren.get(i);
        if ((paramMatrix instanceof VectorDrawableCompat.VGroup)) {
          drawGroupTree((VectorDrawableCompat.VGroup)paramMatrix, mStackedMatrix, paramCanvas, paramInt1, paramInt2, paramColorFilter);
        } else if ((paramMatrix instanceof VectorDrawableCompat.VPath)) {
          drawPath(paramVGroup, (VectorDrawableCompat.VPath)paramMatrix, paramCanvas, paramInt1, paramInt2, paramColorFilter);
        }
        i += 1;
      }
      paramCanvas.restore();
    }
    
    private void drawPath(VectorDrawableCompat.VGroup paramVGroup, VectorDrawableCompat.VPath paramVPath, Canvas paramCanvas, int paramInt1, int paramInt2, ColorFilter paramColorFilter)
    {
      float f2 = paramInt1 / mViewportWidth;
      float f3 = paramInt2 / mViewportHeight;
      float f1 = Math.min(f2, f3);
      paramVGroup = mStackedMatrix;
      mFinalPathMatrix.set(paramVGroup);
      mFinalPathMatrix.postScale(f2, f3);
      f2 = getMatrixScale(paramVGroup);
      if (f2 == 0.0F) {
        return;
      }
      paramVPath.toPath(mPath);
      paramVGroup = mPath;
      mRenderPath.reset();
      if (paramVPath.isClipPath())
      {
        mRenderPath.addPath(paramVGroup, mFinalPathMatrix);
        paramCanvas.clipPath(mRenderPath);
        return;
      }
      paramVPath = (VectorDrawableCompat.VFullPath)paramVPath;
      if ((mTrimPathStart != 0.0F) || (mTrimPathEnd != 1.0F))
      {
        float f6 = mTrimPathStart;
        float f7 = mTrimPathOffset;
        float f4 = mTrimPathEnd;
        float f5 = mTrimPathOffset;
        if (mPathMeasure == null) {
          mPathMeasure = new PathMeasure();
        }
        mPathMeasure.setPath(mPath, false);
        f3 = mPathMeasure.getLength();
        f6 = (f6 + f7) % 1.0F * f3;
        f4 = (f4 + f5) % 1.0F * f3;
        paramVGroup.reset();
        if (f6 > f4)
        {
          mPathMeasure.getSegment(f6, f3, paramVGroup, true);
          mPathMeasure.getSegment(0.0F, f4, paramVGroup, true);
        }
        else
        {
          mPathMeasure.getSegment(f6, f4, paramVGroup, true);
        }
        paramVGroup.rLineTo(0.0F, 0.0F);
      }
      mRenderPath.addPath(paramVGroup, mFinalPathMatrix);
      if (mFillColor != 0)
      {
        if (mFillPaint == null)
        {
          mFillPaint = new Paint();
          mFillPaint.setStyle(Paint.Style.FILL);
          mFillPaint.setAntiAlias(true);
        }
        Paint localPaint = mFillPaint;
        localPaint.setColor(VectorDrawableCompat.applyAlpha(mFillColor, mFillAlpha));
        localPaint.setColorFilter(paramColorFilter);
        Path localPath = mRenderPath;
        if (mFillRule == 0) {
          paramVGroup = Path.FillType.WINDING;
        } else {
          paramVGroup = Path.FillType.EVEN_ODD;
        }
        localPath.setFillType(paramVGroup);
        paramCanvas.drawPath(mRenderPath, localPaint);
      }
      if (mStrokeColor != 0)
      {
        if (mStrokePaint == null)
        {
          mStrokePaint = new Paint();
          mStrokePaint.setStyle(Paint.Style.STROKE);
          mStrokePaint.setAntiAlias(true);
        }
        paramVGroup = mStrokePaint;
        if (mStrokeLineJoin != null) {
          paramVGroup.setStrokeJoin(mStrokeLineJoin);
        }
        if (mStrokeLineCap != null) {
          paramVGroup.setStrokeCap(mStrokeLineCap);
        }
        paramVGroup.setStrokeMiter(mStrokeMiterlimit);
        paramVGroup.setColor(VectorDrawableCompat.applyAlpha(mStrokeColor, mStrokeAlpha));
        paramVGroup.setColorFilter(paramColorFilter);
        paramVGroup.setStrokeWidth(mStrokeWidth * (f1 * f2));
        paramCanvas.drawPath(mRenderPath, paramVGroup);
      }
    }
    
    private float getMatrixScale(Matrix paramMatrix)
    {
      float[] arrayOfFloat = new float[4];
      arrayOfFloat[0] = 0.0F;
      arrayOfFloat[1] = 1.0F;
      arrayOfFloat[2] = 1.0F;
      arrayOfFloat[3] = 0.0F;
      paramMatrix.mapVectors(arrayOfFloat);
      float f2 = (float)Math.hypot(arrayOfFloat[0], arrayOfFloat[1]);
      float f3 = (float)Math.hypot(arrayOfFloat[2], arrayOfFloat[3]);
      float f1 = cross(arrayOfFloat[0], arrayOfFloat[1], arrayOfFloat[2], arrayOfFloat[3]);
      f2 = Math.max(f2, f3);
      if (f2 > 0.0F) {
        return Math.abs(f1) / f2;
      }
      return 0.0F;
    }
    
    public void draw(Canvas paramCanvas, int paramInt1, int paramInt2, ColorFilter paramColorFilter)
    {
      drawGroupTree(mRootGroup, IDENTITY_MATRIX, paramCanvas, paramInt1, paramInt2, paramColorFilter);
    }
    
    public float getAlpha()
    {
      return getRootAlpha() / 255.0F;
    }
    
    public int getRootAlpha()
    {
      return mRootAlpha;
    }
    
    public void setAlpha(float paramFloat)
    {
      setRootAlpha((int)(paramFloat * 255.0F));
    }
    
    public void setRootAlpha(int paramInt)
    {
      mRootAlpha = paramInt;
    }
  }
  
  private static class VectorDrawableCompatState
    extends Drawable.ConstantState
  {
    boolean mAutoMirrored;
    boolean mCacheDirty;
    boolean mCachedAutoMirrored;
    Bitmap mCachedBitmap;
    int mCachedRootAlpha;
    int[] mCachedThemeAttrs;
    ColorStateList mCachedTint;
    PorterDuff.Mode mCachedTintMode;
    int mChangingConfigurations;
    Paint mTempPaint;
    ColorStateList mTint = null;
    PorterDuff.Mode mTintMode = VectorDrawableCompat.DEFAULT_TINT_MODE;
    VectorDrawableCompat.VPathRenderer mVPathRenderer;
    
    public VectorDrawableCompatState()
    {
      mVPathRenderer = new VectorDrawableCompat.VPathRenderer();
    }
    
    public VectorDrawableCompatState(VectorDrawableCompatState paramVectorDrawableCompatState)
    {
      if (paramVectorDrawableCompatState != null)
      {
        mChangingConfigurations = mChangingConfigurations;
        mVPathRenderer = new VectorDrawableCompat.VPathRenderer(mVPathRenderer);
        if (mVPathRenderer.mFillPaint != null) {
          VectorDrawableCompat.VPathRenderer.access$002(mVPathRenderer, new Paint(mVPathRenderer.mFillPaint));
        }
        if (mVPathRenderer.mStrokePaint != null) {
          VectorDrawableCompat.VPathRenderer.access$102(mVPathRenderer, new Paint(mVPathRenderer.mStrokePaint));
        }
        mTint = mTint;
        mTintMode = mTintMode;
        mAutoMirrored = mAutoMirrored;
      }
    }
    
    public boolean canReuseBitmap(int paramInt1, int paramInt2)
    {
      return (paramInt1 == mCachedBitmap.getWidth()) && (paramInt2 == mCachedBitmap.getHeight());
    }
    
    public boolean canReuseCache()
    {
      return (!mCacheDirty) && (mCachedTint == mTint) && (mCachedTintMode == mTintMode) && (mCachedAutoMirrored == mAutoMirrored) && (mCachedRootAlpha == mVPathRenderer.getRootAlpha());
    }
    
    public void createCachedBitmapIfNeeded(int paramInt1, int paramInt2)
    {
      if ((mCachedBitmap == null) || (!canReuseBitmap(paramInt1, paramInt2)))
      {
        mCachedBitmap = Bitmap.createBitmap(paramInt1, paramInt2, Bitmap.Config.ARGB_8888);
        mCacheDirty = true;
      }
    }
    
    public void drawCachedBitmapWithRootAlpha(Canvas paramCanvas, ColorFilter paramColorFilter, Rect paramRect)
    {
      paramColorFilter = getPaint(paramColorFilter);
      paramCanvas.drawBitmap(mCachedBitmap, null, paramRect, paramColorFilter);
    }
    
    public int getChangingConfigurations()
    {
      return mChangingConfigurations;
    }
    
    public Paint getPaint(ColorFilter paramColorFilter)
    {
      if ((!hasTranslucentRoot()) && (paramColorFilter == null)) {
        return null;
      }
      if (mTempPaint == null)
      {
        mTempPaint = new Paint();
        mTempPaint.setFilterBitmap(true);
      }
      mTempPaint.setAlpha(mVPathRenderer.getRootAlpha());
      mTempPaint.setColorFilter(paramColorFilter);
      return mTempPaint;
    }
    
    public boolean hasTranslucentRoot()
    {
      return mVPathRenderer.getRootAlpha() < 255;
    }
    
    public Drawable newDrawable()
    {
      return new VectorDrawableCompat(this);
    }
    
    public Drawable newDrawable(Resources paramResources)
    {
      return new VectorDrawableCompat(this);
    }
    
    public void updateCacheStates()
    {
      mCachedTint = mTint;
      mCachedTintMode = mTintMode;
      mCachedRootAlpha = mVPathRenderer.getRootAlpha();
      mCachedAutoMirrored = mAutoMirrored;
      mCacheDirty = false;
    }
    
    public void updateCachedBitmap(int paramInt1, int paramInt2)
    {
      mCachedBitmap.eraseColor(0);
      Canvas localCanvas = new Canvas(mCachedBitmap);
      mVPathRenderer.draw(localCanvas, paramInt1, paramInt2, null);
    }
  }
  
  @RequiresApi(24)
  private static class VectorDrawableDelegateState
    extends Drawable.ConstantState
  {
    private final Drawable.ConstantState mDelegateState;
    
    public VectorDrawableDelegateState(Drawable.ConstantState paramConstantState)
    {
      mDelegateState = paramConstantState;
    }
    
    public boolean canApplyTheme()
    {
      return mDelegateState.canApplyTheme();
    }
    
    public int getChangingConfigurations()
    {
      return mDelegateState.getChangingConfigurations();
    }
    
    public Drawable newDrawable()
    {
      VectorDrawableCompat localVectorDrawableCompat = new VectorDrawableCompat();
      mDelegateDrawable = ((Drawable)mDelegateState.newDrawable());
      return localVectorDrawableCompat;
    }
    
    public Drawable newDrawable(Resources paramResources)
    {
      VectorDrawableCompat localVectorDrawableCompat = new VectorDrawableCompat();
      mDelegateDrawable = ((Drawable)mDelegateState.newDrawable(paramResources));
      return localVectorDrawableCompat;
    }
    
    public Drawable newDrawable(Resources paramResources, Resources.Theme paramTheme)
    {
      VectorDrawableCompat localVectorDrawableCompat = new VectorDrawableCompat();
      mDelegateDrawable = ((Drawable)mDelegateState.newDrawable(paramResources, paramTheme));
      return localVectorDrawableCompat;
    }
  }
}
