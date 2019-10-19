package android.support.v4.package_7;

import android.animation.Animator;
import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Lifecycle.Event;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.ViewModelStore;
import android.arch.lifecycle.ViewModelStoreOwner;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.IntentSender.SendIntentException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.support.v4.util.DebugUtils;
import android.support.v4.util.SimpleArrayMap;
import android.support.v4.view.LayoutInflaterCompat;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Fragment
  implements ComponentCallbacks, View.OnCreateContextMenuListener, LifecycleOwner, ViewModelStoreOwner
{
  static final int ACTIVITY_CREATED = 2;
  static final int CREATED = 1;
  static final int INITIALIZING = 0;
  static final int RESUMED = 5;
  static final int STARTED = 4;
  static final int STOPPED = 3;
  static final Object USE_DEFAULT_TRANSITION = new Object();
  private static final SimpleArrayMap<String, Class<?>> sClassMap = new SimpleArrayMap();
  boolean mAdded;
  AnimationInfo mAnimationInfo;
  Bundle mArguments;
  int mBackStackNesting;
  boolean mCalled;
  FragmentManagerImpl mChildFragmentManager;
  FragmentManagerNonConfig mChildNonConfig;
  ViewGroup mContainer;
  int mContainerId;
  boolean mDeferStart;
  boolean mDetached;
  int mFragmentId;
  FragmentManagerImpl mFragmentManager;
  boolean mFromLayout;
  boolean mHasMenu;
  boolean mHidden;
  boolean mHiddenChanged;
  FragmentHostCallback mHost;
  boolean mInLayout;
  int mIndex = -1;
  View mInnerView;
  boolean mIsCreated;
  boolean mIsNewlyAdded;
  LayoutInflater mLayoutInflater;
  LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);
  LoaderManagerImpl mLoaderManager;
  boolean mMenuVisible = true;
  Fragment mParentFragment;
  boolean mPerformedCreateView;
  float mPostponedAlpha;
  boolean mRemoving;
  boolean mRestored;
  boolean mRetainInstance;
  boolean mRetaining;
  Bundle mSavedFragmentState;
  @Nullable
  Boolean mSavedUserVisibleHint;
  SparseArray<Parcelable> mSavedViewState;
  int mState = 0;
  String mTag;
  Fragment mTarget;
  int mTargetIndex = -1;
  int mTargetRequestCode;
  boolean mUserVisibleHint = true;
  View mView;
  ViewModelStore mViewModelStore;
  String mWho;
  
  public Fragment() {}
  
  private void callStartTransitionListener()
  {
    OnStartEnterTransitionListener localOnStartEnterTransitionListener;
    if (mAnimationInfo == null)
    {
      localOnStartEnterTransitionListener = null;
    }
    else
    {
      mAnimationInfo.mEnterTransitionPostponed = false;
      localOnStartEnterTransitionListener = mAnimationInfo.mStartEnterTransitionListener;
      mAnimationInfo.mStartEnterTransitionListener = null;
    }
    if (localOnStartEnterTransitionListener != null) {
      localOnStartEnterTransitionListener.onStartEnterTransition();
    }
  }
  
  private AnimationInfo ensureAnimationInfo()
  {
    if (mAnimationInfo == null) {
      mAnimationInfo = new AnimationInfo();
    }
    return mAnimationInfo;
  }
  
  public static Fragment instantiate(Context paramContext, String paramString)
  {
    return instantiate(paramContext, paramString, null);
  }
  
  public static Fragment instantiate(Context paramContext, String paramString, Bundle paramBundle)
  {
    Object localObject1 = sClassMap;
    try
    {
      localObject1 = ((SimpleArrayMap)localObject1).get(paramString);
      Object localObject2 = (Class)localObject1;
      localObject1 = localObject2;
      if (localObject2 == null)
      {
        localObject1 = paramContext.getClassLoader().loadClass(paramString);
        paramContext = (Context)localObject1;
        localObject2 = sClassMap;
        ((SimpleArrayMap)localObject2).put(paramString, localObject1);
        localObject1 = paramContext;
      }
      paramContext = ((Class)localObject1).getConstructor(new Class[0]);
      paramContext = paramContext.newInstance(new Object[0]);
      paramContext = (Fragment)paramContext;
      if (paramBundle != null)
      {
        paramBundle.setClassLoader(paramContext.getClass().getClassLoader());
        paramContext.setArguments(paramBundle);
        return paramContext;
      }
    }
    catch (InvocationTargetException paramContext)
    {
      paramBundle = new StringBuilder();
      paramBundle.append("Unable to instantiate fragment ");
      paramBundle.append(paramString);
      paramBundle.append(": calling Fragment constructor caused an exception");
      throw new InstantiationException(paramBundle.toString(), paramContext);
    }
    catch (NoSuchMethodException paramContext)
    {
      paramBundle = new StringBuilder();
      paramBundle.append("Unable to instantiate fragment ");
      paramBundle.append(paramString);
      paramBundle.append(": could not find Fragment constructor");
      throw new InstantiationException(paramBundle.toString(), paramContext);
    }
    catch (IllegalAccessException paramContext)
    {
      paramBundle = new StringBuilder();
      paramBundle.append("Unable to instantiate fragment ");
      paramBundle.append(paramString);
      paramBundle.append(": make sure class name exists, is public, and has an");
      paramBundle.append(" empty constructor that is public");
      throw new InstantiationException(paramBundle.toString(), paramContext);
    }
    catch (InstantiationException paramContext)
    {
      paramBundle = new StringBuilder();
      paramBundle.append("Unable to instantiate fragment ");
      paramBundle.append(paramString);
      paramBundle.append(": make sure class name exists, is public, and has an");
      paramBundle.append(" empty constructor that is public");
      throw new InstantiationException(paramBundle.toString(), paramContext);
    }
    catch (ClassNotFoundException paramContext)
    {
      paramBundle = new StringBuilder();
      paramBundle.append("Unable to instantiate fragment ");
      paramBundle.append(paramString);
      paramBundle.append(": make sure class name exists, is public, and has an");
      paramBundle.append(" empty constructor that is public");
      throw new InstantiationException(paramBundle.toString(), paramContext);
    }
    return paramContext;
  }
  
  static boolean isSupportFragmentClass(Context paramContext, String paramString)
  {
    Object localObject1 = sClassMap;
    try
    {
      localObject1 = ((SimpleArrayMap)localObject1).get(paramString);
      Object localObject2 = (Class)localObject1;
      localObject1 = localObject2;
      if (localObject2 == null)
      {
        localObject1 = paramContext.getClassLoader().loadClass(paramString);
        paramContext = (Context)localObject1;
        localObject2 = sClassMap;
        ((SimpleArrayMap)localObject2).put(paramString, localObject1);
        localObject1 = paramContext;
      }
      boolean bool = android.support.v4.app.Fragment.class.isAssignableFrom((Class)localObject1);
      return bool;
    }
    catch (ClassNotFoundException paramContext)
    {
      for (;;) {}
    }
    return false;
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mFragmentId=#");
    paramPrintWriter.print(Integer.toHexString(mFragmentId));
    paramPrintWriter.print(" mContainerId=#");
    paramPrintWriter.print(Integer.toHexString(mContainerId));
    paramPrintWriter.print(" mTag=");
    paramPrintWriter.println(mTag);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mState=");
    paramPrintWriter.print(mState);
    paramPrintWriter.print(" mIndex=");
    paramPrintWriter.print(mIndex);
    paramPrintWriter.print(" mWho=");
    paramPrintWriter.print(mWho);
    paramPrintWriter.print(" mBackStackNesting=");
    paramPrintWriter.println(mBackStackNesting);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mAdded=");
    paramPrintWriter.print(mAdded);
    paramPrintWriter.print(" mRemoving=");
    paramPrintWriter.print(mRemoving);
    paramPrintWriter.print(" mFromLayout=");
    paramPrintWriter.print(mFromLayout);
    paramPrintWriter.print(" mInLayout=");
    paramPrintWriter.println(mInLayout);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mHidden=");
    paramPrintWriter.print(mHidden);
    paramPrintWriter.print(" mDetached=");
    paramPrintWriter.print(mDetached);
    paramPrintWriter.print(" mMenuVisible=");
    paramPrintWriter.print(mMenuVisible);
    paramPrintWriter.print(" mHasMenu=");
    paramPrintWriter.println(mHasMenu);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mRetainInstance=");
    paramPrintWriter.print(mRetainInstance);
    paramPrintWriter.print(" mRetaining=");
    paramPrintWriter.print(mRetaining);
    paramPrintWriter.print(" mUserVisibleHint=");
    paramPrintWriter.println(mUserVisibleHint);
    if (mFragmentManager != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mFragmentManager=");
      paramPrintWriter.println(mFragmentManager);
    }
    if (mHost != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mHost=");
      paramPrintWriter.println(mHost);
    }
    if (mParentFragment != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mParentFragment=");
      paramPrintWriter.println(mParentFragment);
    }
    if (mArguments != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mArguments=");
      paramPrintWriter.println(mArguments);
    }
    if (mSavedFragmentState != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mSavedFragmentState=");
      paramPrintWriter.println(mSavedFragmentState);
    }
    if (mSavedViewState != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mSavedViewState=");
      paramPrintWriter.println(mSavedViewState);
    }
    if (mTarget != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mTarget=");
      paramPrintWriter.print(mTarget);
      paramPrintWriter.print(" mTargetRequestCode=");
      paramPrintWriter.println(mTargetRequestCode);
    }
    if (getNextAnim() != 0)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mNextAnim=");
      paramPrintWriter.println(getNextAnim());
    }
    if (mContainer != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mContainer=");
      paramPrintWriter.println(mContainer);
    }
    if (mView != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mView=");
      paramPrintWriter.println(mView);
    }
    if (mInnerView != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mInnerView=");
      paramPrintWriter.println(mView);
    }
    if (getAnimatingAway() != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mAnimatingAway=");
      paramPrintWriter.println(getAnimatingAway());
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mStateAfterAnimating=");
      paramPrintWriter.println(getStateAfterAnimating());
    }
    Object localObject;
    StringBuilder localStringBuilder;
    if (mLoaderManager != null)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.println("Loader Manager:");
      localObject = mLoaderManager;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString);
      localStringBuilder.append("  ");
      ((LoaderManagerImpl)localObject).dump(localStringBuilder.toString(), paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    }
    if (mChildFragmentManager != null)
    {
      paramPrintWriter.print(paramString);
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Child ");
      ((StringBuilder)localObject).append(mChildFragmentManager);
      ((StringBuilder)localObject).append(":");
      paramPrintWriter.println(((StringBuilder)localObject).toString());
      localObject = mChildFragmentManager;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString);
      localStringBuilder.append("  ");
      ((FragmentManagerImpl)localObject).dump(localStringBuilder.toString(), paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    }
  }
  
  public final boolean equals(Object paramObject)
  {
    return super.equals(paramObject);
  }
  
  Fragment findFragmentByWho(String paramString)
  {
    if (paramString.equals(mWho)) {
      return this;
    }
    if (mChildFragmentManager != null) {
      return mChildFragmentManager.findFragmentByWho(paramString);
    }
    return null;
  }
  
  public final FragmentActivity getActivity()
  {
    if (mHost == null) {
      return null;
    }
    return (FragmentActivity)mHost.getActivity();
  }
  
  public boolean getAllowEnterTransitionOverlap()
  {
    if ((mAnimationInfo != null) && (mAnimationInfo.mAllowEnterTransitionOverlap != null)) {
      return mAnimationInfo.mAllowEnterTransitionOverlap.booleanValue();
    }
    return true;
  }
  
  public boolean getAllowReturnTransitionOverlap()
  {
    if ((mAnimationInfo != null) && (mAnimationInfo.mAllowReturnTransitionOverlap != null)) {
      return mAnimationInfo.mAllowReturnTransitionOverlap.booleanValue();
    }
    return true;
  }
  
  View getAnimatingAway()
  {
    if (mAnimationInfo == null) {
      return null;
    }
    return mAnimationInfo.mAnimatingAway;
  }
  
  Animator getAnimator()
  {
    if (mAnimationInfo == null) {
      return null;
    }
    return mAnimationInfo.mAnimator;
  }
  
  public final Bundle getArguments()
  {
    return mArguments;
  }
  
  public final FragmentManager getChildFragmentManager()
  {
    if (mChildFragmentManager == null)
    {
      instantiateChildFragmentManager();
      if (mState >= 5) {
        mChildFragmentManager.dispatchResume();
      } else if (mState >= 4) {
        mChildFragmentManager.dispatchStart();
      } else if (mState >= 2) {
        mChildFragmentManager.dispatchActivityCreated();
      } else if (mState >= 1) {
        mChildFragmentManager.dispatchCreate();
      }
    }
    return mChildFragmentManager;
  }
  
  public Context getContext()
  {
    if (mHost == null) {
      return null;
    }
    return mHost.getContext();
  }
  
  public Object getEnterTransition()
  {
    if (mAnimationInfo == null) {
      return null;
    }
    return mAnimationInfo.mEnterTransition;
  }
  
  SharedElementCallback getEnterTransitionCallback()
  {
    if (mAnimationInfo == null) {
      return null;
    }
    return mAnimationInfo.mEnterTransitionCallback;
  }
  
  public Object getExitTransition()
  {
    if (mAnimationInfo == null) {
      return null;
    }
    return mAnimationInfo.mExitTransition;
  }
  
  SharedElementCallback getExitTransitionCallback()
  {
    if (mAnimationInfo == null) {
      return null;
    }
    return mAnimationInfo.mExitTransitionCallback;
  }
  
  public final FragmentManager getFragmentManager()
  {
    return mFragmentManager;
  }
  
  public final Object getHost()
  {
    if (mHost == null) {
      return null;
    }
    return mHost.onGetHost();
  }
  
  public final int getId()
  {
    return mFragmentId;
  }
  
  public final LayoutInflater getLayoutInflater()
  {
    if (mLayoutInflater == null) {
      return performGetLayoutInflater(null);
    }
    return mLayoutInflater;
  }
  
  public LayoutInflater getLayoutInflater(Bundle paramBundle)
  {
    if (mHost != null)
    {
      paramBundle = mHost.onGetLayoutInflater();
      getChildFragmentManager();
      LayoutInflaterCompat.setFactory2(paramBundle, mChildFragmentManager.getLayoutInflaterFactory());
      return paramBundle;
    }
    throw new IllegalStateException("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager.");
  }
  
  public Lifecycle getLifecycle()
  {
    return mLifecycleRegistry;
  }
  
  public LoaderManager getLoaderManager()
  {
    if (mLoaderManager != null) {
      return mLoaderManager;
    }
    mLoaderManager = new LoaderManagerImpl(this, getViewModelStore());
    return mLoaderManager;
  }
  
  int getNextAnim()
  {
    if (mAnimationInfo == null) {
      return 0;
    }
    return mAnimationInfo.mNextAnim;
  }
  
  int getNextTransition()
  {
    if (mAnimationInfo == null) {
      return 0;
    }
    return mAnimationInfo.mNextTransition;
  }
  
  int getNextTransitionStyle()
  {
    if (mAnimationInfo == null) {
      return 0;
    }
    return mAnimationInfo.mNextTransitionStyle;
  }
  
  public final Fragment getParentFragment()
  {
    return mParentFragment;
  }
  
  public Object getReenterTransition()
  {
    if (mAnimationInfo == null) {
      return null;
    }
    if (mAnimationInfo.mReenterTransition == USE_DEFAULT_TRANSITION) {
      return getExitTransition();
    }
    return mAnimationInfo.mReenterTransition;
  }
  
  public final Resources getResources()
  {
    return requireContext().getResources();
  }
  
  public final boolean getRetainInstance()
  {
    return mRetainInstance;
  }
  
  public Object getReturnTransition()
  {
    if (mAnimationInfo == null) {
      return null;
    }
    if (mAnimationInfo.mReturnTransition == USE_DEFAULT_TRANSITION) {
      return getEnterTransition();
    }
    return mAnimationInfo.mReturnTransition;
  }
  
  public Object getSharedElementEnterTransition()
  {
    if (mAnimationInfo == null) {
      return null;
    }
    return mAnimationInfo.mSharedElementEnterTransition;
  }
  
  public Object getSharedElementReturnTransition()
  {
    if (mAnimationInfo == null) {
      return null;
    }
    if (mAnimationInfo.mSharedElementReturnTransition == USE_DEFAULT_TRANSITION) {
      return getSharedElementEnterTransition();
    }
    return mAnimationInfo.mSharedElementReturnTransition;
  }
  
  int getStateAfterAnimating()
  {
    if (mAnimationInfo == null) {
      return 0;
    }
    return mAnimationInfo.mStateAfterAnimating;
  }
  
  public final String getString(int paramInt)
  {
    return getResources().getString(paramInt);
  }
  
  public final String getString(int paramInt, Object... paramVarArgs)
  {
    return getResources().getString(paramInt, paramVarArgs);
  }
  
  public final String getTag()
  {
    return mTag;
  }
  
  public final Fragment getTargetFragment()
  {
    return mTarget;
  }
  
  public final int getTargetRequestCode()
  {
    return mTargetRequestCode;
  }
  
  public final CharSequence getText(int paramInt)
  {
    return getResources().getText(paramInt);
  }
  
  public boolean getUserVisibleHint()
  {
    return mUserVisibleHint;
  }
  
  public View getView()
  {
    return mView;
  }
  
  public ViewModelStore getViewModelStore()
  {
    if (getContext() != null)
    {
      if (mViewModelStore == null) {
        mViewModelStore = new ViewModelStore();
      }
      return mViewModelStore;
    }
    throw new IllegalStateException("Can't access ViewModels from detached fragment");
  }
  
  public final boolean hasOptionsMenu()
  {
    return mHasMenu;
  }
  
  public final int hashCode()
  {
    return super.hashCode();
  }
  
  void initState()
  {
    mIndex = -1;
    mWho = null;
    mAdded = false;
    mRemoving = false;
    mFromLayout = false;
    mInLayout = false;
    mRestored = false;
    mBackStackNesting = 0;
    mFragmentManager = null;
    mChildFragmentManager = null;
    mHost = null;
    mFragmentId = 0;
    mContainerId = 0;
    mTag = null;
    mHidden = false;
    mDetached = false;
    mRetaining = false;
  }
  
  void instantiateChildFragmentManager()
  {
    if (mHost != null)
    {
      mChildFragmentManager = new FragmentManagerImpl();
      mChildFragmentManager.attachController(mHost, new Fragment.2(this), this);
      return;
    }
    throw new IllegalStateException("Fragment has not been attached yet.");
  }
  
  public final boolean isAdded()
  {
    return (mHost != null) && (mAdded);
  }
  
  public final boolean isDetached()
  {
    return mDetached;
  }
  
  public final boolean isHidden()
  {
    return mHidden;
  }
  
  boolean isHideReplaced()
  {
    if (mAnimationInfo == null) {
      return false;
    }
    return mAnimationInfo.mIsHideReplaced;
  }
  
  final boolean isInBackStack()
  {
    return mBackStackNesting > 0;
  }
  
  public final boolean isInLayout()
  {
    return mInLayout;
  }
  
  public final boolean isMenuVisible()
  {
    return mMenuVisible;
  }
  
  boolean isPostponed()
  {
    if (mAnimationInfo == null) {
      return false;
    }
    return mAnimationInfo.mEnterTransitionPostponed;
  }
  
  public final boolean isRemoving()
  {
    return mRemoving;
  }
  
  public final boolean isResumed()
  {
    return mState >= 5;
  }
  
  public final boolean isStateSaved()
  {
    if (mFragmentManager == null) {
      return false;
    }
    return mFragmentManager.isStateSaved();
  }
  
  public final boolean isVisible()
  {
    return (isAdded()) && (!isHidden()) && (mView != null) && (mView.getWindowToken() != null) && (mView.getVisibility() == 0);
  }
  
  void noteStateNotSaved()
  {
    if (mChildFragmentManager != null) {
      mChildFragmentManager.noteStateNotSaved();
    }
  }
  
  public void onActivityCreated(Bundle paramBundle)
  {
    mCalled = true;
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {}
  
  public void onAttach(Activity paramActivity)
  {
    mCalled = true;
  }
  
  public void onAttach(Context paramContext)
  {
    mCalled = true;
    if (mHost == null) {
      paramContext = null;
    } else {
      paramContext = mHost.getActivity();
    }
    if (paramContext != null)
    {
      mCalled = false;
      onAttach(paramContext);
    }
  }
  
  public void onAttachFragment(Fragment paramFragment) {}
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    mCalled = true;
  }
  
  public boolean onContextItemSelected(MenuItem paramMenuItem)
  {
    return false;
  }
  
  public void onCreate(Bundle paramBundle)
  {
    mCalled = true;
    restoreChildFragmentState(paramBundle);
    if ((mChildFragmentManager != null) && (!mChildFragmentManager.isStateAtLeast(1))) {
      mChildFragmentManager.dispatchCreate();
    }
  }
  
  public Animation onCreateAnimation(int paramInt1, boolean paramBoolean, int paramInt2)
  {
    return null;
  }
  
  public Animator onCreateAnimator(int paramInt1, boolean paramBoolean, int paramInt2)
  {
    return null;
  }
  
  public void onCreateContextMenu(ContextMenu paramContextMenu, View paramView, ContextMenu.ContextMenuInfo paramContextMenuInfo)
  {
    getActivity().onCreateContextMenu(paramContextMenu, paramView, paramContextMenuInfo);
  }
  
  public void onCreateOptionsMenu(Menu paramMenu, MenuInflater paramMenuInflater) {}
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return null;
  }
  
  public void onDestroy()
  {
    mCalled = true;
    if ((mViewModelStore != null) && (!mHost.mFragmentManager.mStateSaved)) {
      mViewModelStore.clear();
    }
  }
  
  public void onDestroyOptionsMenu() {}
  
  public void onDestroyView()
  {
    mCalled = true;
  }
  
  public void onDetach()
  {
    mCalled = true;
  }
  
  public LayoutInflater onGetLayoutInflater(Bundle paramBundle)
  {
    return getLayoutInflater(paramBundle);
  }
  
  public void onHiddenChanged(boolean paramBoolean) {}
  
  public void onInflate(Activity paramActivity, AttributeSet paramAttributeSet, Bundle paramBundle)
  {
    mCalled = true;
  }
  
  public void onInflate(Context paramContext, AttributeSet paramAttributeSet, Bundle paramBundle)
  {
    mCalled = true;
    if (mHost == null) {
      paramContext = null;
    } else {
      paramContext = mHost.getActivity();
    }
    if (paramContext != null)
    {
      mCalled = false;
      onInflate(paramContext, paramAttributeSet, paramBundle);
    }
  }
  
  public void onLowMemory()
  {
    mCalled = true;
  }
  
  public void onMultiWindowModeChanged(boolean paramBoolean) {}
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    return false;
  }
  
  public void onOptionsMenuClosed(Menu paramMenu) {}
  
  public void onPause()
  {
    mCalled = true;
  }
  
  public void onPictureInPictureModeChanged(boolean paramBoolean) {}
  
  public void onPrepareOptionsMenu(Menu paramMenu) {}
  
  public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt) {}
  
  public void onResume()
  {
    mCalled = true;
  }
  
  public void onSaveInstanceState(Bundle paramBundle) {}
  
  public void onStart()
  {
    mCalled = true;
  }
  
  public void onStop()
  {
    mCalled = true;
  }
  
  public void onViewCreated(View paramView, Bundle paramBundle) {}
  
  public void onViewStateRestored(Bundle paramBundle)
  {
    mCalled = true;
  }
  
  FragmentManager peekChildFragmentManager()
  {
    return mChildFragmentManager;
  }
  
  void performActivityCreated(Bundle paramBundle)
  {
    if (mChildFragmentManager != null) {
      mChildFragmentManager.noteStateNotSaved();
    }
    mState = 2;
    mCalled = false;
    onActivityCreated(paramBundle);
    if (mCalled)
    {
      if (mChildFragmentManager != null) {
        mChildFragmentManager.dispatchActivityCreated();
      }
    }
    else
    {
      paramBundle = new StringBuilder();
      paramBundle.append("Fragment ");
      paramBundle.append(this);
      paramBundle.append(" did not call through to super.onActivityCreated()");
      throw new SuperNotCalledException(paramBundle.toString());
    }
  }
  
  void performConfigurationChanged(Configuration paramConfiguration)
  {
    onConfigurationChanged(paramConfiguration);
    if (mChildFragmentManager != null) {
      mChildFragmentManager.dispatchConfigurationChanged(paramConfiguration);
    }
  }
  
  boolean performContextItemSelected(MenuItem paramMenuItem)
  {
    if (!mHidden)
    {
      if (onContextItemSelected(paramMenuItem)) {
        return true;
      }
      if ((mChildFragmentManager != null) && (mChildFragmentManager.dispatchContextItemSelected(paramMenuItem))) {
        return true;
      }
    }
    return false;
  }
  
  void performCreate(Bundle paramBundle)
  {
    if (mChildFragmentManager != null) {
      mChildFragmentManager.noteStateNotSaved();
    }
    mState = 1;
    mCalled = false;
    onCreate(paramBundle);
    mIsCreated = true;
    if (mCalled)
    {
      mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
      return;
    }
    paramBundle = new StringBuilder();
    paramBundle.append("Fragment ");
    paramBundle.append(this);
    paramBundle.append(" did not call through to super.onCreate()");
    throw new SuperNotCalledException(paramBundle.toString());
  }
  
  boolean performCreateOptionsMenu(Menu paramMenu, MenuInflater paramMenuInflater)
  {
    boolean bool1 = mHidden;
    boolean bool2 = false;
    if (!bool1)
    {
      bool1 = bool2;
      if (mHasMenu)
      {
        bool1 = bool2;
        if (mMenuVisible)
        {
          bool1 = true;
          onCreateOptionsMenu(paramMenu, paramMenuInflater);
        }
      }
      if (mChildFragmentManager != null) {
        return bool1 | mChildFragmentManager.dispatchCreateOptionsMenu(paramMenu, paramMenuInflater);
      }
    }
    else
    {
      return false;
    }
    return bool1;
  }
  
  View performCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    if (mChildFragmentManager != null) {
      mChildFragmentManager.noteStateNotSaved();
    }
    mPerformedCreateView = true;
    return onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
  }
  
  void performDestroy()
  {
    mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
    if (mChildFragmentManager != null) {
      mChildFragmentManager.dispatchDestroy();
    }
    mState = 0;
    mCalled = false;
    mIsCreated = false;
    onDestroy();
    if (mCalled)
    {
      mChildFragmentManager = null;
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Fragment ");
    localStringBuilder.append(this);
    localStringBuilder.append(" did not call through to super.onDestroy()");
    throw new SuperNotCalledException(localStringBuilder.toString());
  }
  
  void performDestroyView()
  {
    if (mChildFragmentManager != null) {
      mChildFragmentManager.dispatchDestroyView();
    }
    mState = 1;
    mCalled = false;
    onDestroyView();
    if (mCalled)
    {
      if (mLoaderManager != null) {
        mLoaderManager.markForRedelivery();
      }
      mPerformedCreateView = false;
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Fragment ");
    localStringBuilder.append(this);
    localStringBuilder.append(" did not call through to super.onDestroyView()");
    throw new SuperNotCalledException(localStringBuilder.toString());
  }
  
  void performDetach()
  {
    mCalled = false;
    onDetach();
    mLayoutInflater = null;
    StringBuilder localStringBuilder;
    if (mCalled)
    {
      if (mChildFragmentManager != null)
      {
        if (mRetaining)
        {
          mChildFragmentManager.dispatchDestroy();
          mChildFragmentManager = null;
          return;
        }
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("Child FragmentManager of ");
        localStringBuilder.append(this);
        localStringBuilder.append(" was not ");
        localStringBuilder.append(" destroyed and this fragment is not retaining instance");
        throw new IllegalStateException(localStringBuilder.toString());
      }
    }
    else
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Fragment ");
      localStringBuilder.append(this);
      localStringBuilder.append(" did not call through to super.onDetach()");
      throw new SuperNotCalledException(localStringBuilder.toString());
    }
  }
  
  LayoutInflater performGetLayoutInflater(Bundle paramBundle)
  {
    mLayoutInflater = onGetLayoutInflater(paramBundle);
    return mLayoutInflater;
  }
  
  void performLowMemory()
  {
    onLowMemory();
    if (mChildFragmentManager != null) {
      mChildFragmentManager.dispatchLowMemory();
    }
  }
  
  void performMultiWindowModeChanged(boolean paramBoolean)
  {
    onMultiWindowModeChanged(paramBoolean);
    if (mChildFragmentManager != null) {
      mChildFragmentManager.dispatchMultiWindowModeChanged(paramBoolean);
    }
  }
  
  boolean performOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (!mHidden)
    {
      if ((mHasMenu) && (mMenuVisible) && (onOptionsItemSelected(paramMenuItem))) {
        return true;
      }
      if ((mChildFragmentManager != null) && (mChildFragmentManager.dispatchOptionsItemSelected(paramMenuItem))) {
        return true;
      }
    }
    return false;
  }
  
  void performOptionsMenuClosed(Menu paramMenu)
  {
    if (!mHidden)
    {
      if ((mHasMenu) && (mMenuVisible)) {
        onOptionsMenuClosed(paramMenu);
      }
      if (mChildFragmentManager != null) {
        mChildFragmentManager.dispatchOptionsMenuClosed(paramMenu);
      }
    }
  }
  
  void performPause()
  {
    mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
    if (mChildFragmentManager != null) {
      mChildFragmentManager.dispatchPause();
    }
    mState = 4;
    mCalled = false;
    onPause();
    if (mCalled) {
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Fragment ");
    localStringBuilder.append(this);
    localStringBuilder.append(" did not call through to super.onPause()");
    throw new SuperNotCalledException(localStringBuilder.toString());
  }
  
  void performPictureInPictureModeChanged(boolean paramBoolean)
  {
    onPictureInPictureModeChanged(paramBoolean);
    if (mChildFragmentManager != null) {
      mChildFragmentManager.dispatchPictureInPictureModeChanged(paramBoolean);
    }
  }
  
  boolean performPrepareOptionsMenu(Menu paramMenu)
  {
    boolean bool1 = mHidden;
    boolean bool2 = false;
    if (!bool1)
    {
      bool1 = bool2;
      if (mHasMenu)
      {
        bool1 = bool2;
        if (mMenuVisible)
        {
          bool1 = true;
          onPrepareOptionsMenu(paramMenu);
        }
      }
      if (mChildFragmentManager != null) {
        return bool1 | mChildFragmentManager.dispatchPrepareOptionsMenu(paramMenu);
      }
    }
    else
    {
      return false;
    }
    return bool1;
  }
  
  void performReallyStop()
  {
    if (mChildFragmentManager != null) {
      mChildFragmentManager.dispatchReallyStop();
    }
    mState = 2;
  }
  
  void performResume()
  {
    if (mChildFragmentManager != null)
    {
      mChildFragmentManager.noteStateNotSaved();
      mChildFragmentManager.execPendingActions();
    }
    mState = 5;
    mCalled = false;
    onResume();
    if (mCalled)
    {
      if (mChildFragmentManager != null)
      {
        mChildFragmentManager.dispatchResume();
        mChildFragmentManager.execPendingActions();
      }
      mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Fragment ");
    localStringBuilder.append(this);
    localStringBuilder.append(" did not call through to super.onResume()");
    throw new SuperNotCalledException(localStringBuilder.toString());
  }
  
  void performSaveInstanceState(Bundle paramBundle)
  {
    onSaveInstanceState(paramBundle);
    if (mChildFragmentManager != null)
    {
      Parcelable localParcelable = mChildFragmentManager.saveAllState();
      if (localParcelable != null) {
        paramBundle.putParcelable("android:support:fragments", localParcelable);
      }
    }
  }
  
  void performStart()
  {
    if (mChildFragmentManager != null)
    {
      mChildFragmentManager.noteStateNotSaved();
      mChildFragmentManager.execPendingActions();
    }
    mState = 4;
    mCalled = false;
    onStart();
    if (mCalled)
    {
      if (mChildFragmentManager != null) {
        mChildFragmentManager.dispatchStart();
      }
      mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START);
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Fragment ");
    localStringBuilder.append(this);
    localStringBuilder.append(" did not call through to super.onStart()");
    throw new SuperNotCalledException(localStringBuilder.toString());
  }
  
  void performStop()
  {
    mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
    if (mChildFragmentManager != null) {
      mChildFragmentManager.dispatchStop();
    }
    mState = 3;
    mCalled = false;
    onStop();
    if (mCalled) {
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Fragment ");
    localStringBuilder.append(this);
    localStringBuilder.append(" did not call through to super.onStop()");
    throw new SuperNotCalledException(localStringBuilder.toString());
  }
  
  public void postponeEnterTransition()
  {
    ensureAnimationInfomEnterTransitionPostponed = true;
  }
  
  public void registerForContextMenu(View paramView)
  {
    paramView.setOnCreateContextMenuListener(this);
  }
  
  public final void requestPermissions(String[] paramArrayOfString, int paramInt)
  {
    if (mHost != null)
    {
      mHost.onRequestPermissionsFromFragment(this, paramArrayOfString, paramInt);
      return;
    }
    paramArrayOfString = new StringBuilder();
    paramArrayOfString.append("Fragment ");
    paramArrayOfString.append(this);
    paramArrayOfString.append(" not attached to Activity");
    throw new IllegalStateException(paramArrayOfString.toString());
  }
  
  public final FragmentActivity requireActivity()
  {
    Object localObject = getActivity();
    if (localObject != null) {
      return localObject;
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Fragment ");
    ((StringBuilder)localObject).append(this);
    ((StringBuilder)localObject).append(" not attached to an activity.");
    throw new IllegalStateException(((StringBuilder)localObject).toString());
  }
  
  public final Context requireContext()
  {
    Object localObject = getContext();
    if (localObject != null) {
      return localObject;
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Fragment ");
    ((StringBuilder)localObject).append(this);
    ((StringBuilder)localObject).append(" not attached to a context.");
    throw new IllegalStateException(((StringBuilder)localObject).toString());
  }
  
  public final FragmentManager requireFragmentManager()
  {
    Object localObject = getFragmentManager();
    if (localObject != null) {
      return localObject;
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Fragment ");
    ((StringBuilder)localObject).append(this);
    ((StringBuilder)localObject).append(" not associated with a fragment manager.");
    throw new IllegalStateException(((StringBuilder)localObject).toString());
  }
  
  public final Object requireHost()
  {
    Object localObject = getHost();
    if (localObject != null) {
      return localObject;
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Fragment ");
    ((StringBuilder)localObject).append(this);
    ((StringBuilder)localObject).append(" not attached to a host.");
    throw new IllegalStateException(((StringBuilder)localObject).toString());
  }
  
  void restoreChildFragmentState(Bundle paramBundle)
  {
    if (paramBundle != null)
    {
      paramBundle = paramBundle.getParcelable("android:support:fragments");
      if (paramBundle != null)
      {
        if (mChildFragmentManager == null) {
          instantiateChildFragmentManager();
        }
        mChildFragmentManager.restoreAllState(paramBundle, mChildNonConfig);
        mChildNonConfig = null;
        mChildFragmentManager.dispatchCreate();
      }
    }
  }
  
  final void restoreViewState(Bundle paramBundle)
  {
    if (mSavedViewState != null)
    {
      mInnerView.restoreHierarchyState(mSavedViewState);
      mSavedViewState = null;
    }
    mCalled = false;
    onViewStateRestored(paramBundle);
    if (mCalled) {
      return;
    }
    paramBundle = new StringBuilder();
    paramBundle.append("Fragment ");
    paramBundle.append(this);
    paramBundle.append(" did not call through to super.onViewStateRestored()");
    throw new SuperNotCalledException(paramBundle.toString());
  }
  
  public void setAllowEnterTransitionOverlap(boolean paramBoolean)
  {
    AnimationInfo.access$602(ensureAnimationInfo(), Boolean.valueOf(paramBoolean));
  }
  
  public void setAllowReturnTransitionOverlap(boolean paramBoolean)
  {
    AnimationInfo.access$702(ensureAnimationInfo(), Boolean.valueOf(paramBoolean));
  }
  
  void setAnimatingAway(View paramView)
  {
    ensureAnimationInfomAnimatingAway = paramView;
  }
  
  void setAnimator(Animator paramAnimator)
  {
    ensureAnimationInfomAnimator = paramAnimator;
  }
  
  public void setArguments(Bundle paramBundle)
  {
    if ((mIndex >= 0) && (isStateSaved())) {
      throw new IllegalStateException("Fragment already active and state has been saved");
    }
    mArguments = paramBundle;
  }
  
  public void setEnterSharedElementCallback(SharedElementCallback paramSharedElementCallback)
  {
    ensureAnimationInfomEnterTransitionCallback = paramSharedElementCallback;
  }
  
  public void setEnterTransition(Object paramObject)
  {
    AnimationInfo.access$002(ensureAnimationInfo(), paramObject);
  }
  
  public void setExitSharedElementCallback(SharedElementCallback paramSharedElementCallback)
  {
    ensureAnimationInfomExitTransitionCallback = paramSharedElementCallback;
  }
  
  public void setExitTransition(Object paramObject)
  {
    AnimationInfo.access$202(ensureAnimationInfo(), paramObject);
  }
  
  public void setHasOptionsMenu(boolean paramBoolean)
  {
    if (mHasMenu != paramBoolean)
    {
      mHasMenu = paramBoolean;
      if ((isAdded()) && (!isHidden())) {
        mHost.onSupportInvalidateOptionsMenu();
      }
    }
  }
  
  void setHideReplaced(boolean paramBoolean)
  {
    ensureAnimationInfomIsHideReplaced = paramBoolean;
  }
  
  final void setIndex(int paramInt, Fragment paramFragment)
  {
    mIndex = paramInt;
    if (paramFragment != null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(mWho);
      localStringBuilder.append(":");
      localStringBuilder.append(mIndex);
      mWho = localStringBuilder.toString();
      return;
    }
    paramFragment = new StringBuilder();
    paramFragment.append("android:fragment:");
    paramFragment.append(mIndex);
    mWho = paramFragment.toString();
  }
  
  public void setInitialSavedState(SavedState paramSavedState)
  {
    if (mIndex < 0)
    {
      if ((paramSavedState != null) && (mState != null)) {
        paramSavedState = mState;
      } else {
        paramSavedState = null;
      }
      mSavedFragmentState = paramSavedState;
      return;
    }
    throw new IllegalStateException("Fragment already active");
  }
  
  public void setMenuVisibility(boolean paramBoolean)
  {
    if (mMenuVisible != paramBoolean)
    {
      mMenuVisible = paramBoolean;
      if ((mHasMenu) && (isAdded()) && (!isHidden())) {
        mHost.onSupportInvalidateOptionsMenu();
      }
    }
  }
  
  void setNextAnim(int paramInt)
  {
    if ((mAnimationInfo == null) && (paramInt == 0)) {
      return;
    }
    ensureAnimationInfomNextAnim = paramInt;
  }
  
  void setNextTransition(int paramInt1, int paramInt2)
  {
    if ((mAnimationInfo == null) && (paramInt1 == 0) && (paramInt2 == 0)) {
      return;
    }
    ensureAnimationInfo();
    mAnimationInfo.mNextTransition = paramInt1;
    mAnimationInfo.mNextTransitionStyle = paramInt2;
  }
  
  void setOnStartEnterTransitionListener(OnStartEnterTransitionListener paramOnStartEnterTransitionListener)
  {
    ensureAnimationInfo();
    if (paramOnStartEnterTransitionListener == mAnimationInfo.mStartEnterTransitionListener) {
      return;
    }
    if ((paramOnStartEnterTransitionListener != null) && (mAnimationInfo.mStartEnterTransitionListener != null))
    {
      paramOnStartEnterTransitionListener = new StringBuilder();
      paramOnStartEnterTransitionListener.append("Trying to set a replacement startPostponedEnterTransition on ");
      paramOnStartEnterTransitionListener.append(this);
      throw new IllegalStateException(paramOnStartEnterTransitionListener.toString());
    }
    if (mAnimationInfo.mEnterTransitionPostponed) {
      mAnimationInfo.mStartEnterTransitionListener = paramOnStartEnterTransitionListener;
    }
    if (paramOnStartEnterTransitionListener != null) {
      paramOnStartEnterTransitionListener.startListening();
    }
  }
  
  public void setReenterTransition(Object paramObject)
  {
    AnimationInfo.access$302(ensureAnimationInfo(), paramObject);
  }
  
  public void setRetainInstance(boolean paramBoolean)
  {
    mRetainInstance = paramBoolean;
  }
  
  public void setReturnTransition(Object paramObject)
  {
    AnimationInfo.access$102(ensureAnimationInfo(), paramObject);
  }
  
  public void setSharedElementEnterTransition(Object paramObject)
  {
    AnimationInfo.access$402(ensureAnimationInfo(), paramObject);
  }
  
  public void setSharedElementReturnTransition(Object paramObject)
  {
    AnimationInfo.access$502(ensureAnimationInfo(), paramObject);
  }
  
  void setStateAfterAnimating(int paramInt)
  {
    ensureAnimationInfomStateAfterAnimating = paramInt;
  }
  
  public void setTargetFragment(Fragment paramFragment, int paramInt)
  {
    FragmentManager localFragmentManager = getFragmentManager();
    if (paramFragment != null) {
      localObject = paramFragment.getFragmentManager();
    } else {
      localObject = null;
    }
    if ((localFragmentManager != null) && (localObject != null) && (localFragmentManager != localObject))
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Fragment ");
      ((StringBuilder)localObject).append(paramFragment);
      ((StringBuilder)localObject).append(" must share the same FragmentManager to be set as a target fragment");
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    }
    Object localObject = paramFragment;
    while (localObject != null) {
      if (localObject != this)
      {
        localObject = ((Fragment)localObject).getTargetFragment();
      }
      else
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Setting ");
        ((StringBuilder)localObject).append(paramFragment);
        ((StringBuilder)localObject).append(" as the target of ");
        ((StringBuilder)localObject).append(this);
        ((StringBuilder)localObject).append(" would create a target cycle");
        throw new IllegalArgumentException(((StringBuilder)localObject).toString());
      }
    }
    mTarget = paramFragment;
    mTargetRequestCode = paramInt;
  }
  
  public void setUserVisibleHint(boolean paramBoolean)
  {
    if ((!mUserVisibleHint) && (paramBoolean) && (mState < 4) && (mFragmentManager != null) && (isAdded())) {
      mFragmentManager.performPendingDeferredStart(this);
    }
    mUserVisibleHint = paramBoolean;
    if ((mState < 4) && (!paramBoolean)) {
      paramBoolean = true;
    } else {
      paramBoolean = false;
    }
    mDeferStart = paramBoolean;
    if (mSavedFragmentState != null) {
      mSavedUserVisibleHint = Boolean.valueOf(mUserVisibleHint);
    }
  }
  
  public boolean shouldShowRequestPermissionRationale(String paramString)
  {
    if (mHost != null) {
      return mHost.onShouldShowRequestPermissionRationale(paramString);
    }
    return false;
  }
  
  public void startActivity(Intent paramIntent)
  {
    startActivity(paramIntent, null);
  }
  
  public void startActivity(Intent paramIntent, Bundle paramBundle)
  {
    if (mHost != null)
    {
      mHost.onStartActivityFromFragment(this, paramIntent, -1, paramBundle);
      return;
    }
    paramIntent = new StringBuilder();
    paramIntent.append("Fragment ");
    paramIntent.append(this);
    paramIntent.append(" not attached to Activity");
    throw new IllegalStateException(paramIntent.toString());
  }
  
  public void startActivityForResult(Intent paramIntent, int paramInt)
  {
    startActivityForResult(paramIntent, paramInt, null);
  }
  
  public void startActivityForResult(Intent paramIntent, int paramInt, Bundle paramBundle)
  {
    if (mHost != null)
    {
      mHost.onStartActivityFromFragment(this, paramIntent, paramInt, paramBundle);
      return;
    }
    paramIntent = new StringBuilder();
    paramIntent.append("Fragment ");
    paramIntent.append(this);
    paramIntent.append(" not attached to Activity");
    throw new IllegalStateException(paramIntent.toString());
  }
  
  public void startIntentSenderForResult(IntentSender paramIntentSender, int paramInt1, Intent paramIntent, int paramInt2, int paramInt3, int paramInt4, Bundle paramBundle)
    throws IntentSender.SendIntentException
  {
    if (mHost != null)
    {
      mHost.onStartIntentSenderFromFragment(this, paramIntentSender, paramInt1, paramIntent, paramInt2, paramInt3, paramInt4, paramBundle);
      return;
    }
    paramIntentSender = new StringBuilder();
    paramIntentSender.append("Fragment ");
    paramIntentSender.append(this);
    paramIntentSender.append(" not attached to Activity");
    throw new IllegalStateException(paramIntentSender.toString());
  }
  
  public void startPostponedEnterTransition()
  {
    if ((mFragmentManager != null) && (mFragmentManager.mHost != null))
    {
      if (Looper.myLooper() != mFragmentManager.mHost.getHandler().getLooper())
      {
        mFragmentManager.mHost.getHandler().postAtFrontOfQueue(new Fragment.1(this));
        return;
      }
      callStartTransitionListener();
      return;
    }
    ensureAnimationInfomEnterTransitionPostponed = false;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(128);
    DebugUtils.buildShortClassTag(this, localStringBuilder);
    if (mIndex >= 0)
    {
      localStringBuilder.append(" #");
      localStringBuilder.append(mIndex);
    }
    if (mFragmentId != 0)
    {
      localStringBuilder.append(" id=0x");
      localStringBuilder.append(Integer.toHexString(mFragmentId));
    }
    if (mTag != null)
    {
      localStringBuilder.append(" ");
      localStringBuilder.append(mTag);
    }
    localStringBuilder.append('}');
    return localStringBuilder.toString();
  }
  
  public void unregisterForContextMenu(View paramView)
  {
    paramView.setOnCreateContextMenuListener(null);
  }
  
  class AnimationInfo
  {
    private Boolean mAllowEnterTransitionOverlap;
    private Boolean mAllowReturnTransitionOverlap;
    View mAnimatingAway;
    Animator mAnimator;
    private Object mEnterTransition = null;
    SharedElementCallback mEnterTransitionCallback = null;
    boolean mEnterTransitionPostponed;
    private Object mExitTransition = null;
    SharedElementCallback mExitTransitionCallback = null;
    boolean mIsHideReplaced;
    int mNextAnim;
    int mNextTransition;
    int mNextTransitionStyle;
    private Object mReenterTransition = Fragment.USE_DEFAULT_TRANSITION;
    private Object mReturnTransition = Fragment.USE_DEFAULT_TRANSITION;
    private Object mSharedElementEnterTransition = null;
    private Object mSharedElementReturnTransition = Fragment.USE_DEFAULT_TRANSITION;
    Fragment.OnStartEnterTransitionListener mStartEnterTransitionListener;
    int mStateAfterAnimating;
    
    AnimationInfo() {}
  }
  
  public class InstantiationException
    extends RuntimeException
  {
    public InstantiationException(Exception paramException)
    {
      super(paramException);
    }
  }
  
  abstract interface OnStartEnterTransitionListener
  {
    public abstract void onStartEnterTransition();
    
    public abstract void startListening();
  }
  
  public class SavedState
    implements Parcelable
  {
    public static final Parcelable.Creator<android.support.v4.app.Fragment.SavedState> CREATOR = new Parcelable.Creator()
    {
      public Fragment.SavedState createFromParcel(Parcel paramAnonymousParcel)
      {
        return new Fragment.SavedState(paramAnonymousParcel, null);
      }
      
      public Fragment.SavedState[] newArray(int paramAnonymousInt)
      {
        return new Fragment.SavedState[paramAnonymousInt];
      }
    };
    final Bundle mState;
    
    SavedState()
    {
      mState = this$1;
    }
    
    SavedState(ClassLoader paramClassLoader)
    {
      mState = this$1.readBundle();
      if ((paramClassLoader != null) && (mState != null)) {
        mState.setClassLoader(paramClassLoader);
      }
    }
    
    public int describeContents()
    {
      return 0;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      paramParcel.writeBundle(mState);
    }
  }
}
