package android.support.graphics.drawable;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.Drawable.ConstantState;
import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import android.support.v4.content.hack.ResourcesCompat;
import android.support.v4.content.hack.TypedArrayUtils;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.SimpleArrayMap;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class AnimatedVectorDrawableCompat
  extends VectorDrawableCommon
  implements Animatable2Compat
{
  private static final String ANIMATED_VECTOR = "animated-vector";
  private static final boolean DBG_ANIMATION_VECTOR_DRAWABLE = false;
  private static final String LOGTAG = "AnimatedVDCompat";
  private static final String TARGET = "target";
  private AnimatedVectorDrawableCompatState mAnimatedVectorState;
  private ArrayList<Animatable2Compat.AnimationCallback> mAnimationCallbacks = null;
  private Animator.AnimatorListener mAnimatorListener = null;
  private ArgbEvaluator mArgbEvaluator = null;
  AnimatedVectorDrawableDelegateState mCachedConstantStateDelegate;
  final Drawable.Callback mCallback = new Drawable.Callback()
  {
    public void invalidateDrawable(Drawable paramAnonymousDrawable)
    {
      invalidateSelf();
    }
    
    public void scheduleDrawable(Drawable paramAnonymousDrawable, Runnable paramAnonymousRunnable, long paramAnonymousLong)
    {
      scheduleSelf(paramAnonymousRunnable, paramAnonymousLong);
    }
    
    public void unscheduleDrawable(Drawable paramAnonymousDrawable, Runnable paramAnonymousRunnable)
    {
      unscheduleSelf(paramAnonymousRunnable);
    }
  };
  private Context mContext;
  
  AnimatedVectorDrawableCompat()
  {
    this(null, null, null);
  }
  
  private AnimatedVectorDrawableCompat(Context paramContext)
  {
    this(paramContext, null, null);
  }
  
  private AnimatedVectorDrawableCompat(Context paramContext, AnimatedVectorDrawableCompatState paramAnimatedVectorDrawableCompatState, Resources paramResources)
  {
    mContext = paramContext;
    if (paramAnimatedVectorDrawableCompatState != null)
    {
      mAnimatedVectorState = paramAnimatedVectorDrawableCompatState;
      return;
    }
    mAnimatedVectorState = new AnimatedVectorDrawableCompatState(paramContext, paramAnimatedVectorDrawableCompatState, mCallback, paramResources);
  }
  
  public static void clearAnimationCallbacks(Drawable paramDrawable)
  {
    if (paramDrawable != null)
    {
      if (!(paramDrawable instanceof Animatable)) {
        return;
      }
      if (Build.VERSION.SDK_INT >= 24)
      {
        ((AnimatedVectorDrawable)paramDrawable).clearAnimationCallbacks();
        return;
      }
      ((AnimatedVectorDrawableCompat)paramDrawable).clearAnimationCallbacks();
    }
  }
  
  public static AnimatedVectorDrawableCompat create(Context paramContext, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 24)
    {
      localObject = new AnimatedVectorDrawableCompat(paramContext);
      mDelegateDrawable = ResourcesCompat.getDrawable(paramContext.getResources(), paramInt, paramContext.getTheme());
      mDelegateDrawable.setCallback(mCallback);
      mCachedConstantStateDelegate = new AnimatedVectorDrawableDelegateState(mDelegateDrawable.getConstantState());
      return localObject;
    }
    Object localObject = paramContext.getResources();
    try
    {
      localObject = ((Resources)localObject).getXml(paramInt);
      AttributeSet localAttributeSet = Xml.asAttributeSet((XmlPullParser)localObject);
      do
      {
        paramInt = ((XmlPullParser)localObject).next();
      } while ((paramInt != 2) && (paramInt != 1));
      if (paramInt == 2)
      {
        paramContext = createFromXmlInner(paramContext, paramContext.getResources(), (XmlPullParser)localObject, localAttributeSet, paramContext.getTheme());
        return paramContext;
      }
      paramContext = new XmlPullParserException("No start tag found");
      throw paramContext;
    }
    catch (IOException paramContext)
    {
      Log.e("AnimatedVDCompat", "parser error", paramContext);
    }
    catch (XmlPullParserException paramContext)
    {
      Log.e("AnimatedVDCompat", "parser error", paramContext);
    }
    return null;
  }
  
  public static AnimatedVectorDrawableCompat createFromXmlInner(Context paramContext, Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Resources.Theme paramTheme)
    throws XmlPullParserException, IOException
  {
    paramContext = new AnimatedVectorDrawableCompat(paramContext);
    paramContext.inflate(paramResources, paramXmlPullParser, paramAttributeSet, paramTheme);
    return paramContext;
  }
  
  public static void registerAnimationCallback(Drawable paramDrawable, Animatable2Compat.AnimationCallback paramAnimationCallback)
  {
    if (paramDrawable != null)
    {
      if (paramAnimationCallback == null) {
        return;
      }
      if (!(paramDrawable instanceof Animatable)) {
        return;
      }
      if (Build.VERSION.SDK_INT >= 24)
      {
        registerPlatformCallback((AnimatedVectorDrawable)paramDrawable, paramAnimationCallback);
        return;
      }
      ((AnimatedVectorDrawableCompat)paramDrawable).registerAnimationCallback(paramAnimationCallback);
    }
  }
  
  private static void registerPlatformCallback(AnimatedVectorDrawable paramAnimatedVectorDrawable, Animatable2Compat.AnimationCallback paramAnimationCallback)
  {
    paramAnimatedVectorDrawable.registerAnimationCallback(paramAnimationCallback.getPlatformCallback());
  }
  
  private void removeAnimatorSetListener()
  {
    if (mAnimatorListener != null)
    {
      mAnimatedVectorState.mAnimatorSet.removeListener(mAnimatorListener);
      mAnimatorListener = null;
    }
  }
  
  private void setupAnimatorsForTarget(String paramString, Animator paramAnimator)
  {
    paramAnimator.setTarget(mAnimatedVectorState.mVectorDrawable.getTargetByName(paramString));
    if (Build.VERSION.SDK_INT < 21) {
      setupColorAnimator(paramAnimator);
    }
    if (mAnimatedVectorState.mAnimators == null)
    {
      AnimatedVectorDrawableCompatState.access$002(mAnimatedVectorState, new ArrayList());
      mAnimatedVectorState.mTargetNameMap = new ArrayMap();
    }
    mAnimatedVectorState.mAnimators.add(paramAnimator);
    mAnimatedVectorState.mTargetNameMap.put(paramAnimator, paramString);
  }
  
  private void setupColorAnimator(Animator paramAnimator)
  {
    Object localObject;
    if ((paramAnimator instanceof AnimatorSet))
    {
      localObject = ((AnimatorSet)paramAnimator).getChildAnimations();
      if (localObject != null)
      {
        int i = 0;
        while (i < ((List)localObject).size())
        {
          setupColorAnimator((Animator)((List)localObject).get(i));
          i += 1;
        }
      }
    }
    if ((paramAnimator instanceof ObjectAnimator))
    {
      paramAnimator = (ObjectAnimator)paramAnimator;
      localObject = paramAnimator.getPropertyName();
      if (("fillColor".equals(localObject)) || ("strokeColor".equals(localObject)))
      {
        if (mArgbEvaluator == null) {
          mArgbEvaluator = new ArgbEvaluator();
        }
        paramAnimator.setEvaluator(mArgbEvaluator);
      }
    }
  }
  
  public static boolean unregisterAnimationCallback(Drawable paramDrawable, Animatable2Compat.AnimationCallback paramAnimationCallback)
  {
    if (paramDrawable != null)
    {
      if (paramAnimationCallback == null) {
        return false;
      }
      if (!(paramDrawable instanceof Animatable)) {
        return false;
      }
      if (Build.VERSION.SDK_INT >= 24) {
        return unregisterPlatformCallback((AnimatedVectorDrawable)paramDrawable, paramAnimationCallback);
      }
      return ((AnimatedVectorDrawableCompat)paramDrawable).unregisterAnimationCallback(paramAnimationCallback);
    }
    return false;
  }
  
  private static boolean unregisterPlatformCallback(AnimatedVectorDrawable paramAnimatedVectorDrawable, Animatable2Compat.AnimationCallback paramAnimationCallback)
  {
    return paramAnimatedVectorDrawable.unregisterAnimationCallback(paramAnimationCallback.getPlatformCallback());
  }
  
  public void applyTheme(Resources.Theme paramTheme)
  {
    if (mDelegateDrawable != null) {
      DrawableCompat.applyTheme(mDelegateDrawable, paramTheme);
    }
  }
  
  public boolean canApplyTheme()
  {
    if (mDelegateDrawable != null) {
      return DrawableCompat.canApplyTheme(mDelegateDrawable);
    }
    return false;
  }
  
  public void clearAnimationCallbacks()
  {
    if (mDelegateDrawable != null)
    {
      ((AnimatedVectorDrawable)mDelegateDrawable).clearAnimationCallbacks();
      return;
    }
    removeAnimatorSetListener();
    if (mAnimationCallbacks == null) {
      return;
    }
    mAnimationCallbacks.clear();
  }
  
  public void draw(Canvas paramCanvas)
  {
    if (mDelegateDrawable != null)
    {
      mDelegateDrawable.draw(paramCanvas);
      return;
    }
    mAnimatedVectorState.mVectorDrawable.draw(paramCanvas);
    if (mAnimatedVectorState.mAnimatorSet.isStarted()) {
      invalidateSelf();
    }
  }
  
  public int getAlpha()
  {
    if (mDelegateDrawable != null) {
      return DrawableCompat.getAlpha(mDelegateDrawable);
    }
    return mAnimatedVectorState.mVectorDrawable.getAlpha();
  }
  
  public int getChangingConfigurations()
  {
    if (mDelegateDrawable != null) {
      return mDelegateDrawable.getChangingConfigurations();
    }
    return super.getChangingConfigurations() | mAnimatedVectorState.mChangingConfigurations;
  }
  
  public Drawable.ConstantState getConstantState()
  {
    if ((mDelegateDrawable != null) && (Build.VERSION.SDK_INT >= 24)) {
      return new AnimatedVectorDrawableDelegateState(mDelegateDrawable.getConstantState());
    }
    return null;
  }
  
  public int getIntrinsicHeight()
  {
    if (mDelegateDrawable != null) {
      return mDelegateDrawable.getIntrinsicHeight();
    }
    return mAnimatedVectorState.mVectorDrawable.getIntrinsicHeight();
  }
  
  public int getIntrinsicWidth()
  {
    if (mDelegateDrawable != null) {
      return mDelegateDrawable.getIntrinsicWidth();
    }
    return mAnimatedVectorState.mVectorDrawable.getIntrinsicWidth();
  }
  
  public int getOpacity()
  {
    if (mDelegateDrawable != null) {
      return mDelegateDrawable.getOpacity();
    }
    return mAnimatedVectorState.mVectorDrawable.getOpacity();
  }
  
  public void inflate(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet)
    throws XmlPullParserException, IOException
  {
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
    int i = paramXmlPullParser.getEventType();
    int j = paramXmlPullParser.getDepth();
    while ((i != 1) && ((paramXmlPullParser.getDepth() >= j + 1) || (i != 3)))
    {
      if (i == 2)
      {
        Object localObject1 = paramXmlPullParser.getName();
        Object localObject2;
        if ("animated-vector".equals(localObject1))
        {
          localObject1 = TypedArrayUtils.obtainAttributes(paramResources, paramTheme, paramAttributeSet, AndroidResources.STYLEABLE_ANIMATED_VECTOR_DRAWABLE);
          i = ((TypedArray)localObject1).getResourceId(0, 0);
          if (i != 0)
          {
            localObject2 = VectorDrawableCompat.create(paramResources, i, paramTheme);
            ((VectorDrawableCompat)localObject2).setAllowCaching(false);
            ((Drawable)localObject2).setCallback(mCallback);
            if (mAnimatedVectorState.mVectorDrawable != null) {
              mAnimatedVectorState.mVectorDrawable.setCallback(null);
            }
            mAnimatedVectorState.mVectorDrawable = ((VectorDrawableCompat)localObject2);
          }
          ((TypedArray)localObject1).recycle();
        }
        else if ("target".equals(localObject1))
        {
          localObject1 = paramResources.obtainAttributes(paramAttributeSet, AndroidResources.STYLEABLE_ANIMATED_VECTOR_DRAWABLE_TARGET);
          localObject2 = ((TypedArray)localObject1).getString(0);
          i = ((TypedArray)localObject1).getResourceId(1, 0);
          if (i != 0) {
            if (mContext != null)
            {
              setupAnimatorsForTarget((String)localObject2, AnimatorInflaterCompat.loadAnimator(mContext, i));
            }
            else
            {
              ((TypedArray)localObject1).recycle();
              throw new IllegalStateException("Context can't be null when inflating animators");
            }
          }
          ((TypedArray)localObject1).recycle();
        }
      }
      i = paramXmlPullParser.next();
    }
    mAnimatedVectorState.setupAnimatorSet();
  }
  
  public boolean isAutoMirrored()
  {
    if (mDelegateDrawable != null) {
      return DrawableCompat.isAutoMirrored(mDelegateDrawable);
    }
    return mAnimatedVectorState.mVectorDrawable.isAutoMirrored();
  }
  
  public boolean isRunning()
  {
    if (mDelegateDrawable != null) {
      return ((AnimatedVectorDrawable)mDelegateDrawable).isRunning();
    }
    return mAnimatedVectorState.mAnimatorSet.isRunning();
  }
  
  public boolean isStateful()
  {
    if (mDelegateDrawable != null) {
      return mDelegateDrawable.isStateful();
    }
    return mAnimatedVectorState.mVectorDrawable.isStateful();
  }
  
  public Drawable mutate()
  {
    if (mDelegateDrawable != null) {
      mDelegateDrawable.mutate();
    }
    return this;
  }
  
  protected void onBoundsChange(Rect paramRect)
  {
    if (mDelegateDrawable != null)
    {
      mDelegateDrawable.setBounds(paramRect);
      return;
    }
    mAnimatedVectorState.mVectorDrawable.setBounds(paramRect);
  }
  
  protected boolean onLevelChange(int paramInt)
  {
    if (mDelegateDrawable != null) {
      return mDelegateDrawable.setLevel(paramInt);
    }
    return mAnimatedVectorState.mVectorDrawable.setLevel(paramInt);
  }
  
  protected boolean onStateChange(int[] paramArrayOfInt)
  {
    if (mDelegateDrawable != null) {
      return mDelegateDrawable.setState(paramArrayOfInt);
    }
    return mAnimatedVectorState.mVectorDrawable.setState(paramArrayOfInt);
  }
  
  public void registerAnimationCallback(Animatable2Compat.AnimationCallback paramAnimationCallback)
  {
    if (mDelegateDrawable != null)
    {
      registerPlatformCallback((AnimatedVectorDrawable)mDelegateDrawable, paramAnimationCallback);
      return;
    }
    if (paramAnimationCallback == null) {
      return;
    }
    if (mAnimationCallbacks == null) {
      mAnimationCallbacks = new ArrayList();
    }
    if (mAnimationCallbacks.contains(paramAnimationCallback)) {
      return;
    }
    mAnimationCallbacks.add(paramAnimationCallback);
    if (mAnimatorListener == null) {
      mAnimatorListener = new AnimatorListenerAdapter()
      {
        public void onAnimationEnd(Animator paramAnonymousAnimator)
        {
          paramAnonymousAnimator = new ArrayList(mAnimationCallbacks);
          int j = paramAnonymousAnimator.size();
          int i = 0;
          while (i < j)
          {
            ((Animatable2Compat.AnimationCallback)paramAnonymousAnimator.get(i)).onAnimationEnd(AnimatedVectorDrawableCompat.this);
            i += 1;
          }
        }
        
        public void onAnimationStart(Animator paramAnonymousAnimator)
        {
          paramAnonymousAnimator = new ArrayList(mAnimationCallbacks);
          int j = paramAnonymousAnimator.size();
          int i = 0;
          while (i < j)
          {
            ((Animatable2Compat.AnimationCallback)paramAnonymousAnimator.get(i)).onAnimationStart(AnimatedVectorDrawableCompat.this);
            i += 1;
          }
        }
      };
    }
    mAnimatedVectorState.mAnimatorSet.addListener(mAnimatorListener);
  }
  
  public void setAlpha(int paramInt)
  {
    if (mDelegateDrawable != null)
    {
      mDelegateDrawable.setAlpha(paramInt);
      return;
    }
    mAnimatedVectorState.mVectorDrawable.setAlpha(paramInt);
  }
  
  public void setAutoMirrored(boolean paramBoolean)
  {
    if (mDelegateDrawable != null)
    {
      DrawableCompat.setAutoMirrored(mDelegateDrawable, paramBoolean);
      return;
    }
    mAnimatedVectorState.mVectorDrawable.setAutoMirrored(paramBoolean);
  }
  
  public void setColorFilter(ColorFilter paramColorFilter)
  {
    if (mDelegateDrawable != null)
    {
      mDelegateDrawable.setColorFilter(paramColorFilter);
      return;
    }
    mAnimatedVectorState.mVectorDrawable.setColorFilter(paramColorFilter);
  }
  
  public void setTint(int paramInt)
  {
    if (mDelegateDrawable != null)
    {
      DrawableCompat.setTint(mDelegateDrawable, paramInt);
      return;
    }
    mAnimatedVectorState.mVectorDrawable.setTint(paramInt);
  }
  
  public void setTintList(ColorStateList paramColorStateList)
  {
    if (mDelegateDrawable != null)
    {
      DrawableCompat.setTintList(mDelegateDrawable, paramColorStateList);
      return;
    }
    mAnimatedVectorState.mVectorDrawable.setTintList(paramColorStateList);
  }
  
  public void setTintMode(PorterDuff.Mode paramMode)
  {
    if (mDelegateDrawable != null)
    {
      DrawableCompat.setTintMode(mDelegateDrawable, paramMode);
      return;
    }
    mAnimatedVectorState.mVectorDrawable.setTintMode(paramMode);
  }
  
  public boolean setVisible(boolean paramBoolean1, boolean paramBoolean2)
  {
    if (mDelegateDrawable != null) {
      return mDelegateDrawable.setVisible(paramBoolean1, paramBoolean2);
    }
    mAnimatedVectorState.mVectorDrawable.setVisible(paramBoolean1, paramBoolean2);
    return super.setVisible(paramBoolean1, paramBoolean2);
  }
  
  public void start()
  {
    if (mDelegateDrawable != null)
    {
      ((AnimatedVectorDrawable)mDelegateDrawable).start();
      return;
    }
    if (mAnimatedVectorState.mAnimatorSet.isStarted()) {
      return;
    }
    mAnimatedVectorState.mAnimatorSet.start();
    invalidateSelf();
  }
  
  public void stop()
  {
    if (mDelegateDrawable != null)
    {
      ((AnimatedVectorDrawable)mDelegateDrawable).stop();
      return;
    }
    mAnimatedVectorState.mAnimatorSet.end();
  }
  
  public boolean unregisterAnimationCallback(Animatable2Compat.AnimationCallback paramAnimationCallback)
  {
    if (mDelegateDrawable != null) {
      unregisterPlatformCallback((AnimatedVectorDrawable)mDelegateDrawable, paramAnimationCallback);
    }
    boolean bool;
    if ((mAnimationCallbacks != null) && (paramAnimationCallback != null))
    {
      bool = mAnimationCallbacks.remove(paramAnimationCallback);
      if (mAnimationCallbacks.size() == 0)
      {
        removeAnimatorSetListener();
        return bool;
      }
    }
    else
    {
      return false;
    }
    return bool;
  }
  
  private static class AnimatedVectorDrawableCompatState
    extends Drawable.ConstantState
  {
    AnimatorSet mAnimatorSet;
    private ArrayList<Animator> mAnimators;
    int mChangingConfigurations;
    ArrayMap<Animator, String> mTargetNameMap;
    VectorDrawableCompat mVectorDrawable;
    
    public AnimatedVectorDrawableCompatState(Context paramContext, AnimatedVectorDrawableCompatState paramAnimatedVectorDrawableCompatState, Drawable.Callback paramCallback, Resources paramResources)
    {
      if (paramAnimatedVectorDrawableCompatState != null)
      {
        mChangingConfigurations = mChangingConfigurations;
        paramContext = mVectorDrawable;
        int i = 0;
        if (paramContext != null)
        {
          paramContext = mVectorDrawable.getConstantState();
          if (paramResources != null) {
            mVectorDrawable = ((VectorDrawableCompat)paramContext.newDrawable(paramResources));
          } else {
            mVectorDrawable = ((VectorDrawableCompat)paramContext.newDrawable());
          }
          mVectorDrawable = ((VectorDrawableCompat)mVectorDrawable.mutate());
          mVectorDrawable.setCallback(paramCallback);
          mVectorDrawable.setBounds(mVectorDrawable.getBounds());
          mVectorDrawable.setAllowCaching(false);
        }
        if (mAnimators != null)
        {
          int j = mAnimators.size();
          mAnimators = new ArrayList(j);
          mTargetNameMap = new ArrayMap(j);
          while (i < j)
          {
            paramCallback = (Animator)mAnimators.get(i);
            paramContext = paramCallback.clone();
            paramCallback = (String)mTargetNameMap.get(paramCallback);
            paramContext.setTarget(mVectorDrawable.getTargetByName(paramCallback));
            mAnimators.add(paramContext);
            mTargetNameMap.put(paramContext, paramCallback);
            i += 1;
          }
          setupAnimatorSet();
        }
      }
    }
    
    public int getChangingConfigurations()
    {
      return mChangingConfigurations;
    }
    
    public Drawable newDrawable()
    {
      throw new IllegalStateException("No constant state support for SDK < 24.");
    }
    
    public Drawable newDrawable(Resources paramResources)
    {
      throw new IllegalStateException("No constant state support for SDK < 24.");
    }
    
    public void setupAnimatorSet()
    {
      if (mAnimatorSet == null) {
        mAnimatorSet = new AnimatorSet();
      }
      mAnimatorSet.playTogether(mAnimators);
    }
  }
  
  @RequiresApi(24)
  private static class AnimatedVectorDrawableDelegateState
    extends Drawable.ConstantState
  {
    private final Drawable.ConstantState mDelegateState;
    
    public AnimatedVectorDrawableDelegateState(Drawable.ConstantState paramConstantState)
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
      AnimatedVectorDrawableCompat localAnimatedVectorDrawableCompat = new AnimatedVectorDrawableCompat();
      mDelegateDrawable = mDelegateState.newDrawable();
      mDelegateDrawable.setCallback(mCallback);
      return localAnimatedVectorDrawableCompat;
    }
    
    public Drawable newDrawable(Resources paramResources)
    {
      AnimatedVectorDrawableCompat localAnimatedVectorDrawableCompat = new AnimatedVectorDrawableCompat();
      mDelegateDrawable = mDelegateState.newDrawable(paramResources);
      mDelegateDrawable.setCallback(mCallback);
      return localAnimatedVectorDrawableCompat;
    }
    
    public Drawable newDrawable(Resources paramResources, Resources.Theme paramTheme)
    {
      AnimatedVectorDrawableCompat localAnimatedVectorDrawableCompat = new AnimatedVectorDrawableCompat();
      mDelegateDrawable = mDelegateState.newDrawable(paramResources, paramTheme);
      mDelegateDrawable.setCallback(mCallback);
      return localAnimatedVectorDrawableCompat;
    }
  }
}
