package android.support.v4.package_7;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.AbsSavedState;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import java.util.ArrayList;

public class FragmentTabHost
  extends TabHost
  implements TabHost.OnTabChangeListener
{
  private boolean mAttached;
  private int mContainerId;
  private Context mContext;
  private FragmentManager mFragmentManager;
  private TabInfo mLastTab;
  private TabHost.OnTabChangeListener mOnTabChangeListener;
  private FrameLayout mRealTabContent;
  private final ArrayList<android.support.v4.app.FragmentTabHost.TabInfo> mTabs = new ArrayList();
  
  public FragmentTabHost(Context paramContext)
  {
    super(paramContext, null);
    initFragmentTabHost(paramContext, null);
  }
  
  public FragmentTabHost(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initFragmentTabHost(paramContext, paramAttributeSet);
  }
  
  private FragmentTransaction doTabChanged(String paramString, FragmentTransaction paramFragmentTransaction)
  {
    TabInfo localTabInfo = getTabInfoForTag(paramString);
    paramString = paramFragmentTransaction;
    if (mLastTab != localTabInfo)
    {
      paramString = paramFragmentTransaction;
      if (paramFragmentTransaction == null) {
        paramString = mFragmentManager.beginTransaction();
      }
      if ((mLastTab != null) && (mLastTab.fragment != null)) {
        paramString.detach(mLastTab.fragment);
      }
      if (localTabInfo != null) {
        if (fragment == null)
        {
          fragment = Fragment.instantiate(mContext, clss.getName(), args);
          paramString.add(mContainerId, fragment, tag);
        }
        else
        {
          paramString.attach(fragment);
        }
      }
      mLastTab = localTabInfo;
    }
    return paramString;
  }
  
  private void ensureContent()
  {
    if (mRealTabContent == null)
    {
      mRealTabContent = ((FrameLayout)findViewById(mContainerId));
      if (mRealTabContent != null) {
        return;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("No tab content FrameLayout found for id ");
      localStringBuilder.append(mContainerId);
      throw new IllegalStateException(localStringBuilder.toString());
    }
  }
  
  private void ensureHierarchy(Context paramContext)
  {
    if (findViewById(16908307) == null)
    {
      LinearLayout localLinearLayout = new LinearLayout(paramContext);
      localLinearLayout.setOrientation(1);
      addView(localLinearLayout, new FrameLayout.LayoutParams(-1, -1));
      Object localObject = new TabWidget(paramContext);
      ((View)localObject).setId(16908307);
      ((LinearLayout)localObject).setOrientation(0);
      localLinearLayout.addView((View)localObject, new LinearLayout.LayoutParams(-1, -2, 0.0F));
      localObject = new FrameLayout(paramContext);
      ((View)localObject).setId(16908305);
      localLinearLayout.addView((View)localObject, new LinearLayout.LayoutParams(0, 0, 0.0F));
      paramContext = new FrameLayout(paramContext);
      mRealTabContent = paramContext;
      mRealTabContent.setId(mContainerId);
      localLinearLayout.addView(paramContext, new LinearLayout.LayoutParams(-1, 0, 1.0F));
    }
  }
  
  private TabInfo getTabInfoForTag(String paramString)
  {
    int j = mTabs.size();
    int i = 0;
    while (i < j)
    {
      TabInfo localTabInfo = (TabInfo)mTabs.get(i);
      if (tag.equals(paramString)) {
        return localTabInfo;
      }
      i += 1;
    }
    return null;
  }
  
  private void initFragmentTabHost(Context paramContext, AttributeSet paramAttributeSet)
  {
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, new int[] { 16842995 }, 0, 0);
    mContainerId = paramContext.getResourceId(0, 0);
    paramContext.recycle();
    super.setOnTabChangedListener(this);
  }
  
  public void addTab(TabHost.TabSpec paramTabSpec, Class paramClass, Bundle paramBundle)
  {
    paramTabSpec.setContent(new DummyTabFactory(mContext));
    String str = paramTabSpec.getTag();
    paramClass = new TabInfo(str, paramClass, paramBundle);
    if (mAttached)
    {
      fragment = mFragmentManager.findFragmentByTag(str);
      if ((fragment != null) && (!fragment.isDetached()))
      {
        paramBundle = mFragmentManager.beginTransaction();
        paramBundle.detach(fragment);
        paramBundle.commit();
      }
    }
    mTabs.add(paramClass);
    addTab(paramTabSpec);
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    String str = getCurrentTabTag();
    int j = mTabs.size();
    Object localObject1 = null;
    int i = 0;
    while (i < j)
    {
      TabInfo localTabInfo = (TabInfo)mTabs.get(i);
      fragment = mFragmentManager.findFragmentByTag(tag);
      Object localObject2 = localObject1;
      if (fragment != null)
      {
        localObject2 = localObject1;
        if (!fragment.isDetached()) {
          if (tag.equals(str))
          {
            mLastTab = localTabInfo;
            localObject2 = localObject1;
          }
          else
          {
            localObject2 = localObject1;
            if (localObject1 == null) {
              localObject2 = mFragmentManager.beginTransaction();
            }
            ((FragmentTransaction)localObject2).detach(fragment);
          }
        }
      }
      i += 1;
      localObject1 = localObject2;
    }
    mAttached = true;
    localObject1 = doTabChanged(str, (FragmentTransaction)localObject1);
    if (localObject1 != null)
    {
      ((FragmentTransaction)localObject1).commit();
      mFragmentManager.executePendingTransactions();
    }
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    mAttached = false;
  }
  
  protected void onRestoreInstanceState(Parcelable paramParcelable)
  {
    if (!(paramParcelable instanceof SavedState))
    {
      super.onRestoreInstanceState(paramParcelable);
      return;
    }
    paramParcelable = (SavedState)paramParcelable;
    super.onRestoreInstanceState(paramParcelable.getSuperState());
    setCurrentTabByTag(curTab);
  }
  
  protected Parcelable onSaveInstanceState()
  {
    SavedState localSavedState = new SavedState(super.onSaveInstanceState());
    curTab = getCurrentTabTag();
    return localSavedState;
  }
  
  public void onTabChanged(String paramString)
  {
    if (mAttached)
    {
      FragmentTransaction localFragmentTransaction = doTabChanged(paramString, null);
      if (localFragmentTransaction != null) {
        localFragmentTransaction.commit();
      }
    }
    if (mOnTabChangeListener != null) {
      mOnTabChangeListener.onTabChanged(paramString);
    }
  }
  
  public void setOnTabChangedListener(TabHost.OnTabChangeListener paramOnTabChangeListener)
  {
    mOnTabChangeListener = paramOnTabChangeListener;
  }
  
  public void setup()
  {
    throw new IllegalStateException("Must call setup() that takes a Context and FragmentManager");
  }
  
  public void setup(Context paramContext, FragmentManager paramFragmentManager)
  {
    ensureHierarchy(paramContext);
    super.setup();
    mContext = paramContext;
    mFragmentManager = paramFragmentManager;
    ensureContent();
  }
  
  public void setup(Context paramContext, FragmentManager paramFragmentManager, int paramInt)
  {
    ensureHierarchy(paramContext);
    super.setup();
    mContext = paramContext;
    mFragmentManager = paramFragmentManager;
    mContainerId = paramInt;
    ensureContent();
    mRealTabContent.setId(paramInt);
    if (getId() == -1) {
      setId(16908306);
    }
  }
  
  class DummyTabFactory
    implements TabHost.TabContentFactory
  {
    public DummyTabFactory() {}
    
    public View createTabContent(String paramString)
    {
      paramString = new View(FragmentTabHost.this);
      paramString.setMinimumWidth(0);
      paramString.setMinimumHeight(0);
      return paramString;
    }
  }
  
  class SavedState
    extends View.BaseSavedState
  {
    public static final Parcelable.Creator<android.support.v4.app.FragmentTabHost.SavedState> CREATOR = new Parcelable.Creator()
    {
      public FragmentTabHost.SavedState createFromParcel(Parcel paramAnonymousParcel)
      {
        return new FragmentTabHost.SavedState(paramAnonymousParcel);
      }
      
      public FragmentTabHost.SavedState[] newArray(int paramAnonymousInt)
      {
        return new FragmentTabHost.SavedState[paramAnonymousInt];
      }
    };
    String curTab;
    
    SavedState()
    {
      super();
      curTab = this$1.readString();
    }
    
    SavedState()
    {
      super();
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("FragmentTabHost.SavedState{");
      localStringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
      localStringBuilder.append(" curTab=");
      localStringBuilder.append(curTab);
      localStringBuilder.append("}");
      return localStringBuilder.toString();
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeString(curTab);
    }
  }
  
  final class TabInfo
  {
    @Nullable
    final Bundle args;
    @NonNull
    final Class<?> clss;
    Fragment fragment;
    
    TabInfo(Class paramClass, Bundle paramBundle)
    {
      clss = paramClass;
      args = paramBundle;
    }
  }
}
