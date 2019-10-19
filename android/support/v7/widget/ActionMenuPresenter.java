package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.ActionProvider.SubUiVisibilityListener;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.layout;
import android.support.v7.view.ActionBarPolicy;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.view.menu.ActionMenuItemView.PopupCallback;
import android.support.v7.view.menu.BaseMenuPresenter;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.view.menu.MenuPresenter.Callback;
import android.support.v7.view.menu.MenuView;
import android.support.v7.view.menu.MenuView.ItemView;
import android.support.v7.view.menu.ShowableListMenu;
import android.support.v7.view.menu.SubMenuBuilder;
import android.util.DisplayMetrics;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.ArrayList;

class ActionMenuPresenter
  extends BaseMenuPresenter
  implements ActionProvider.SubUiVisibilityListener
{
  private static final String TAG = "ActionMenuPresenter";
  private final SparseBooleanArray mActionButtonGroups = new SparseBooleanArray();
  ActionButtonSubmenu mActionButtonPopup;
  private int mActionItemWidthLimit;
  private boolean mExpandedActionViewsExclusive;
  private int mMaxItems;
  private boolean mMaxItemsSet;
  private int mMinCellSize;
  int mOpenSubMenuId;
  OverflowMenuButton mOverflowButton;
  OverflowPopup mOverflowPopup;
  private Drawable mPendingOverflowIcon;
  private boolean mPendingOverflowIconSet;
  private ActionMenuPopupCallback mPopupCallback;
  final PopupPresenterCallback mPopupPresenterCallback = new PopupPresenterCallback();
  OpenOverflowRunnable mPostedOpenRunnable;
  private boolean mReserveOverflow;
  private boolean mReserveOverflowSet;
  private View mScrapActionButtonView;
  private boolean mStrictWidthLimit;
  private int mWidthLimit;
  private boolean mWidthLimitSet;
  
  public ActionMenuPresenter(Context paramContext)
  {
    super(paramContext, R.layout.abc_action_menu_layout, R.layout.abc_action_menu_item_layout);
  }
  
  private View findViewForItem(MenuItem paramMenuItem)
  {
    ViewGroup localViewGroup = (ViewGroup)mMenuView;
    if (localViewGroup == null) {
      return null;
    }
    int j = localViewGroup.getChildCount();
    int i = 0;
    while (i < j)
    {
      View localView = localViewGroup.getChildAt(i);
      if (((localView instanceof MenuView.ItemView)) && (((MenuView.ItemView)localView).getItemData() == paramMenuItem)) {
        return localView;
      }
      i += 1;
    }
    return null;
  }
  
  public void bindItemView(MenuItemImpl paramMenuItemImpl, MenuView.ItemView paramItemView)
  {
    paramItemView.initialize(paramMenuItemImpl, 0);
    paramMenuItemImpl = (ActionMenuView)mMenuView;
    paramItemView = (ActionMenuItemView)paramItemView;
    paramItemView.setItemInvoker(paramMenuItemImpl);
    if (mPopupCallback == null) {
      mPopupCallback = new ActionMenuPopupCallback();
    }
    paramItemView.setPopupCallback(mPopupCallback);
  }
  
  public boolean dismissPopupMenus()
  {
    return hideOverflowMenu() | hideSubMenus();
  }
  
  public boolean filterLeftoverView(ViewGroup paramViewGroup, int paramInt)
  {
    if (paramViewGroup.getChildAt(paramInt) == mOverflowButton) {
      return false;
    }
    return super.filterLeftoverView(paramViewGroup, paramInt);
  }
  
  public boolean flagActionItems()
  {
    Object localObject2;
    Object localObject1;
    int i3;
    if (mMenu != null)
    {
      localObject2 = mMenu.getVisibleItems();
      localObject1 = localObject2;
      i3 = ((ArrayList)localObject2).size();
    }
    else
    {
      localObject1 = null;
      i3 = 0;
    }
    int i = mMaxItems;
    int i2 = mActionItemWidthLimit;
    int i6 = View.MeasureSpec.makeMeasureSpec(0, 0);
    ViewGroup localViewGroup = (ViewGroup)mMenuView;
    int j = 0;
    int m = 0;
    int n = 0;
    int k = 0;
    int i1;
    while (j < i3)
    {
      localObject2 = (MenuItemImpl)localObject1.get(j);
      if (((MenuItemImpl)localObject2).requiresActionButton()) {
        m += 1;
      } else if (((MenuItemImpl)localObject2).requestsActionButton()) {
        k += 1;
      } else {
        n = 1;
      }
      i1 = i;
      if (mExpandedActionViewsExclusive)
      {
        i1 = i;
        if (((MenuItemImpl)localObject2).isActionViewExpanded()) {
          i1 = 0;
        }
      }
      j += 1;
      i = i1;
    }
    j = i;
    if (mReserveOverflow) {
      if (n == 0)
      {
        j = i;
        if (k + m <= i) {}
      }
      else
      {
        j = i - 1;
      }
    }
    i = j - m;
    SparseBooleanArray localSparseBooleanArray = mActionButtonGroups;
    localSparseBooleanArray.clear();
    int i4;
    if (mStrictWidthLimit)
    {
      k = i2 / mMinCellSize;
      j = mMinCellSize;
      m = mMinCellSize;
      i4 = i2 % j / k + m;
    }
    else
    {
      k = 0;
      i4 = 0;
    }
    int i5 = 0;
    j = 0;
    for (;;)
    {
      localObject2 = this;
      if (i5 >= i3) {
        break;
      }
      MenuItemImpl localMenuItemImpl = (MenuItemImpl)localObject1.get(i5);
      View localView;
      if (localMenuItemImpl.requiresActionButton())
      {
        localView = ((ActionMenuPresenter)localObject2).getItemView(localMenuItemImpl, mScrapActionButtonView, localViewGroup);
        if (mScrapActionButtonView == null) {
          mScrapActionButtonView = localView;
        }
        if (mStrictWidthLimit) {
          k -= ActionMenuView.measureChildForCells(localView, i4, k, i6, 0);
        } else {
          localView.measure(i6, i6);
        }
        n = localView.getMeasuredWidth();
        m = n;
        n = i2 - n;
        if (j == 0) {
          j = m;
        }
        m = localMenuItemImpl.getGroupId();
        if (m != 0) {
          localSparseBooleanArray.put(m, true);
        }
        localMenuItemImpl.setIsActionButton(true);
        i1 = j;
        m = n;
      }
      for (;;)
      {
        j = i1;
        break label798;
        if (!localMenuItemImpl.requestsActionButton()) {
          break;
        }
        int i7 = localMenuItemImpl.getGroupId();
        boolean bool = localSparseBooleanArray.get(i7);
        int i8;
        if (((i > 0) || (bool)) && (i2 > 0) && ((!mStrictWidthLimit) || (k > 0))) {
          i8 = 1;
        } else {
          i8 = 0;
        }
        m = i2;
        n = k;
        int i9 = i8;
        i1 = j;
        if (i8 != 0)
        {
          localView = ((ActionMenuPresenter)localObject2).getItemView(localMenuItemImpl, mScrapActionButtonView, localViewGroup);
          if (mScrapActionButtonView == null) {
            mScrapActionButtonView = localView;
          }
          if (mStrictWidthLimit)
          {
            n = ActionMenuView.measureChildForCells(localView, i4, k, i6, 0);
            m = k - n;
            k = m;
            if (n == 0)
            {
              i8 = 0;
              k = m;
            }
          }
          else
          {
            localView.measure(i6, i6);
          }
          n = localView.getMeasuredWidth();
          m = i2 - n;
          i1 = j;
          if (j == 0) {
            i1 = n;
          }
          if (mStrictWidthLimit)
          {
            if (m >= 0) {
              j = 1;
            } else {
              j = 0;
            }
            i9 = i8 & j;
            n = k;
          }
          else
          {
            if (m + i1 > 0) {
              j = 1;
            } else {
              j = 0;
            }
            i9 = i8 & j;
            n = k;
          }
        }
        if ((i9 != 0) && (i7 != 0))
        {
          localSparseBooleanArray.put(i7, true);
          j = i;
        }
        else
        {
          j = i;
          if (bool)
          {
            localSparseBooleanArray.put(i7, false);
            k = 0;
            for (;;)
            {
              j = i;
              if (k >= i5) {
                break;
              }
              localObject2 = (MenuItemImpl)localObject1.get(k);
              j = i;
              if (((MenuItemImpl)localObject2).getGroupId() == i7)
              {
                j = i;
                if (((MenuItemImpl)localObject2).isActionButton()) {
                  j = i + 1;
                }
                ((MenuItemImpl)localObject2).setIsActionButton(false);
              }
              k += 1;
              i = j;
            }
          }
        }
        i = j;
        if (i9 != 0) {
          i = j - 1;
        }
        localMenuItemImpl.setIsActionButton(i9);
        k = n;
      }
      localMenuItemImpl.setIsActionButton(false);
      m = i2;
      label798:
      i5 += 1;
      i2 = m;
    }
    return true;
  }
  
  public View getItemView(MenuItemImpl paramMenuItemImpl, View paramView, ViewGroup paramViewGroup)
  {
    View localView2 = paramMenuItemImpl.getActionView();
    View localView1 = localView2;
    if ((localView2 == null) || (paramMenuItemImpl.hasCollapsibleActionView())) {
      localView1 = super.getItemView(paramMenuItemImpl, paramView, paramViewGroup);
    }
    int i;
    if (paramMenuItemImpl.isActionViewExpanded()) {
      i = 8;
    } else {
      i = 0;
    }
    localView1.setVisibility(i);
    paramMenuItemImpl = (ActionMenuView)paramViewGroup;
    paramView = localView1.getLayoutParams();
    if (!paramMenuItemImpl.checkLayoutParams(paramView)) {
      localView1.setLayoutParams(paramMenuItemImpl.generateLayoutParams(paramView));
    }
    return localView1;
  }
  
  public MenuView getMenuView(ViewGroup paramViewGroup)
  {
    MenuView localMenuView = mMenuView;
    paramViewGroup = super.getMenuView(paramViewGroup);
    if (localMenuView != paramViewGroup) {
      ((ActionMenuView)paramViewGroup).setPresenter(this);
    }
    return paramViewGroup;
  }
  
  public Drawable getOverflowIcon()
  {
    if (mOverflowButton != null) {
      return mOverflowButton.getDrawable();
    }
    if (mPendingOverflowIconSet) {
      return mPendingOverflowIcon;
    }
    return null;
  }
  
  public boolean hideOverflowMenu()
  {
    if ((mPostedOpenRunnable != null) && (mMenuView != null))
    {
      ((View)mMenuView).removeCallbacks(mPostedOpenRunnable);
      mPostedOpenRunnable = null;
      return true;
    }
    OverflowPopup localOverflowPopup = mOverflowPopup;
    if (localOverflowPopup != null)
    {
      localOverflowPopup.dismiss();
      return true;
    }
    return false;
  }
  
  public boolean hideSubMenus()
  {
    if (mActionButtonPopup != null)
    {
      mActionButtonPopup.dismiss();
      return true;
    }
    return false;
  }
  
  public void initForMenu(Context paramContext, MenuBuilder paramMenuBuilder)
  {
    super.initForMenu(paramContext, paramMenuBuilder);
    paramMenuBuilder = paramContext.getResources();
    paramContext = ActionBarPolicy.get(paramContext);
    if (!mReserveOverflowSet) {
      mReserveOverflow = paramContext.showsOverflowMenuButton();
    }
    if (!mWidthLimitSet) {
      mWidthLimit = paramContext.getEmbeddedMenuWidthLimit();
    }
    if (!mMaxItemsSet) {
      mMaxItems = paramContext.getMaxActionButtons();
    }
    int i = mWidthLimit;
    if (mReserveOverflow)
    {
      if (mOverflowButton == null)
      {
        mOverflowButton = new OverflowMenuButton(mSystemContext);
        if (mPendingOverflowIconSet)
        {
          mOverflowButton.setImageDrawable(mPendingOverflowIcon);
          mPendingOverflowIcon = null;
          mPendingOverflowIconSet = false;
        }
        int j = View.MeasureSpec.makeMeasureSpec(0, 0);
        mOverflowButton.measure(j, j);
      }
      i -= mOverflowButton.getMeasuredWidth();
    }
    else
    {
      mOverflowButton = null;
    }
    mActionItemWidthLimit = i;
    mMinCellSize = ((int)(getDisplayMetricsdensity * 56.0F));
    mScrapActionButtonView = null;
  }
  
  public boolean isOverflowMenuShowPending()
  {
    return (mPostedOpenRunnable != null) || (isOverflowMenuShowing());
  }
  
  public boolean isOverflowMenuShowing()
  {
    return (mOverflowPopup != null) && (mOverflowPopup.isShowing());
  }
  
  public boolean isOverflowReserved()
  {
    return mReserveOverflow;
  }
  
  public void onCloseMenu(MenuBuilder paramMenuBuilder, boolean paramBoolean)
  {
    dismissPopupMenus();
    super.onCloseMenu(paramMenuBuilder, paramBoolean);
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    if (!mMaxItemsSet) {
      mMaxItems = ActionBarPolicy.get(mContext).getMaxActionButtons();
    }
    if (mMenu != null) {
      mMenu.onItemsChanged(true);
    }
  }
  
  public void onRestoreInstanceState(Parcelable paramParcelable)
  {
    if (!(paramParcelable instanceof SavedState)) {
      return;
    }
    paramParcelable = (SavedState)paramParcelable;
    if (openSubMenuId > 0)
    {
      paramParcelable = mMenu.findItem(openSubMenuId);
      if (paramParcelable != null) {
        onSubMenuSelected((SubMenuBuilder)paramParcelable.getSubMenu());
      }
    }
  }
  
  public Parcelable onSaveInstanceState()
  {
    SavedState localSavedState = new SavedState();
    openSubMenuId = mOpenSubMenuId;
    return localSavedState;
  }
  
  public boolean onSubMenuSelected(SubMenuBuilder paramSubMenuBuilder)
  {
    boolean bool1 = paramSubMenuBuilder.hasVisibleItems();
    boolean bool2 = false;
    if (!bool1) {
      return false;
    }
    for (Object localObject = paramSubMenuBuilder; ((SubMenuBuilder)localObject).getParentMenu() != mMenu; localObject = (SubMenuBuilder)((SubMenuBuilder)localObject).getParentMenu()) {}
    localObject = findViewForItem(((SubMenuBuilder)localObject).getItem());
    if (localObject == null) {
      return false;
    }
    mOpenSubMenuId = paramSubMenuBuilder.getItem().getItemId();
    int j = paramSubMenuBuilder.size();
    int i = 0;
    for (;;)
    {
      bool1 = bool2;
      if (i >= j) {
        break;
      }
      MenuItem localMenuItem = paramSubMenuBuilder.getItem(i);
      if ((localMenuItem.isVisible()) && (localMenuItem.getIcon() != null))
      {
        bool1 = true;
        break;
      }
      i += 1;
    }
    mActionButtonPopup = new ActionButtonSubmenu(mContext, paramSubMenuBuilder, (View)localObject);
    mActionButtonPopup.setForceShowIcon(bool1);
    mActionButtonPopup.show();
    super.onSubMenuSelected(paramSubMenuBuilder);
    return true;
  }
  
  public void onSubUiVisibilityChanged(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      super.onSubMenuSelected(null);
      return;
    }
    if (mMenu != null) {
      mMenu.close(false);
    }
  }
  
  public void setExpandedActionViewsExclusive(boolean paramBoolean)
  {
    mExpandedActionViewsExclusive = paramBoolean;
  }
  
  public void setItemLimit(int paramInt)
  {
    mMaxItems = paramInt;
    mMaxItemsSet = true;
  }
  
  public void setMenuView(ActionMenuView paramActionMenuView)
  {
    mMenuView = paramActionMenuView;
    paramActionMenuView.initialize(mMenu);
  }
  
  public void setOverflowIcon(Drawable paramDrawable)
  {
    if (mOverflowButton != null)
    {
      mOverflowButton.setImageDrawable(paramDrawable);
      return;
    }
    mPendingOverflowIconSet = true;
    mPendingOverflowIcon = paramDrawable;
  }
  
  public void setReserveOverflow(boolean paramBoolean)
  {
    mReserveOverflow = paramBoolean;
    mReserveOverflowSet = true;
  }
  
  public void setWidthLimit(int paramInt, boolean paramBoolean)
  {
    mWidthLimit = paramInt;
    mStrictWidthLimit = paramBoolean;
    mWidthLimitSet = true;
  }
  
  public boolean shouldIncludeItem(int paramInt, MenuItemImpl paramMenuItemImpl)
  {
    return paramMenuItemImpl.isActionButton();
  }
  
  public boolean showOverflowMenu()
  {
    if ((mReserveOverflow) && (!isOverflowMenuShowing()) && (mMenu != null) && (mMenuView != null) && (mPostedOpenRunnable == null) && (!mMenu.getNonActionItems().isEmpty()))
    {
      mPostedOpenRunnable = new OpenOverflowRunnable(new OverflowPopup(mContext, mMenu, mOverflowButton, true));
      ((View)mMenuView).post(mPostedOpenRunnable);
      super.onSubMenuSelected(null);
      return true;
    }
    return false;
  }
  
  public void updateMenuView(boolean paramBoolean)
  {
    super.updateMenuView(paramBoolean);
    ((View)mMenuView).requestLayout();
    Object localObject = mMenu;
    int j = 0;
    int k;
    if (localObject != null)
    {
      localObject = mMenu.getActionItems();
      k = ((ArrayList)localObject).size();
      i = 0;
      while (i < k)
      {
        ActionProvider localActionProvider = ((MenuItemImpl)((ArrayList)localObject).get(i)).getSupportActionProvider();
        if (localActionProvider != null) {
          localActionProvider.setSubUiVisibilityListener(this);
        }
        i += 1;
      }
    }
    if (mMenu != null) {
      localObject = mMenu.getNonActionItems();
    } else {
      localObject = null;
    }
    int i = j;
    boolean bool;
    if (mReserveOverflow)
    {
      i = j;
      if (localObject != null)
      {
        k = ((ArrayList)localObject).size();
        if (k == 1)
        {
          bool = ((MenuItemImpl)((ArrayList)localObject).get(0)).isActionViewExpanded() ^ true;
        }
        else
        {
          bool = j;
          if (k > 0) {
            bool = true;
          }
        }
      }
    }
    if (bool)
    {
      if (mOverflowButton == null) {
        mOverflowButton = new OverflowMenuButton(mSystemContext);
      }
      localObject = (ViewGroup)mOverflowButton.getParent();
      if (localObject != mMenuView)
      {
        if (localObject != null) {
          ((ViewGroup)localObject).removeView(mOverflowButton);
        }
        localObject = (ActionMenuView)mMenuView;
        ((ViewGroup)localObject).addView(mOverflowButton, ((ActionMenuView)localObject).generateOverflowButtonLayoutParams());
      }
    }
    else if ((mOverflowButton != null) && (mOverflowButton.getParent() == mMenuView))
    {
      ((ViewGroup)mMenuView).removeView(mOverflowButton);
    }
    ((ActionMenuView)mMenuView).setOverflowReserved(mReserveOverflow);
  }
  
  private class ActionButtonSubmenu
    extends MenuPopupHelper
  {
    public ActionButtonSubmenu(Context paramContext, SubMenuBuilder paramSubMenuBuilder, View paramView)
    {
      super(paramSubMenuBuilder, paramView, false, R.attr.actionOverflowMenuStyle);
      if (!((MenuItemImpl)paramSubMenuBuilder.getItem()).isActionButton())
      {
        if (mOverflowButton == null) {
          paramContext = (View)ActionMenuPresenter.access$200(ActionMenuPresenter.this);
        } else {
          paramContext = mOverflowButton;
        }
        setAnchorView(paramContext);
      }
      setPresenterCallback(mPopupPresenterCallback);
    }
    
    protected void onDismiss()
    {
      mActionButtonPopup = null;
      mOpenSubMenuId = 0;
      super.onDismiss();
    }
  }
  
  private class ActionMenuPopupCallback
    extends ActionMenuItemView.PopupCallback
  {
    ActionMenuPopupCallback() {}
    
    public ShowableListMenu getPopup()
    {
      if (mActionButtonPopup != null) {
        return mActionButtonPopup.getPopup();
      }
      return null;
    }
  }
  
  private class OpenOverflowRunnable
    implements Runnable
  {
    private ActionMenuPresenter.OverflowPopup mPopup;
    
    public OpenOverflowRunnable(ActionMenuPresenter.OverflowPopup paramOverflowPopup)
    {
      mPopup = paramOverflowPopup;
    }
    
    public void run()
    {
      if (ActionMenuPresenter.access$300(ActionMenuPresenter.this) != null) {
        ActionMenuPresenter.access$400(ActionMenuPresenter.this).changeMenuMode();
      }
      View localView = (View)ActionMenuPresenter.access$500(ActionMenuPresenter.this);
      if ((localView != null) && (localView.getWindowToken() != null) && (mPopup.tryShow())) {
        mOverflowPopup = mPopup;
      }
      mPostedOpenRunnable = null;
    }
  }
  
  private class OverflowMenuButton
    extends AppCompatImageView
    implements ActionMenuView.ActionMenuChildView
  {
    private final float[] mTempPts = new float[2];
    
    public OverflowMenuButton(Context paramContext)
    {
      super(null, R.attr.actionOverflowButtonStyle);
      setClickable(true);
      setFocusable(true);
      setVisibility(0);
      setEnabled(true);
      TooltipCompat.setTooltipText(this, getContentDescription());
      setOnTouchListener(new ForwardingListener(this)
      {
        public ShowableListMenu getPopup()
        {
          if (mOverflowPopup == null) {
            return null;
          }
          return mOverflowPopup.getPopup();
        }
        
        public boolean onForwardingStarted()
        {
          showOverflowMenu();
          return true;
        }
        
        public boolean onForwardingStopped()
        {
          if (mPostedOpenRunnable != null) {
            return false;
          }
          hideOverflowMenu();
          return true;
        }
      });
    }
    
    public boolean needsDividerAfter()
    {
      return false;
    }
    
    public boolean needsDividerBefore()
    {
      return false;
    }
    
    public boolean performClick()
    {
      if (super.performClick()) {
        return true;
      }
      playSoundEffect(0);
      showOverflowMenu();
      return true;
    }
    
    protected boolean setFrame(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      boolean bool = super.setFrame(paramInt1, paramInt2, paramInt3, paramInt4);
      Drawable localDrawable1 = getDrawable();
      Drawable localDrawable2 = getBackground();
      if ((localDrawable1 != null) && (localDrawable2 != null))
      {
        int i = getWidth();
        paramInt2 = getHeight();
        paramInt1 = Math.max(i, paramInt2) / 2;
        int j = getPaddingLeft();
        int k = getPaddingRight();
        paramInt3 = getPaddingTop();
        paramInt4 = getPaddingBottom();
        i = (i + (j - k)) / 2;
        paramInt2 = (paramInt2 + (paramInt3 - paramInt4)) / 2;
        DrawableCompat.setHotspotBounds(localDrawable2, i - paramInt1, paramInt2 - paramInt1, i + paramInt1, paramInt2 + paramInt1);
      }
      return bool;
    }
  }
  
  private class OverflowPopup
    extends MenuPopupHelper
  {
    public OverflowPopup(Context paramContext, MenuBuilder paramMenuBuilder, View paramView, boolean paramBoolean)
    {
      super(paramMenuBuilder, paramView, paramBoolean, R.attr.actionOverflowMenuStyle);
      setGravity(8388613);
      setPresenterCallback(mPopupPresenterCallback);
    }
    
    protected void onDismiss()
    {
      if (ActionMenuPresenter.access$000(ActionMenuPresenter.this) != null) {
        ActionMenuPresenter.access$100(ActionMenuPresenter.this).close();
      }
      mOverflowPopup = null;
      super.onDismiss();
    }
  }
  
  private class PopupPresenterCallback
    implements MenuPresenter.Callback
  {
    PopupPresenterCallback() {}
    
    public void onCloseMenu(MenuBuilder paramMenuBuilder, boolean paramBoolean)
    {
      if ((paramMenuBuilder instanceof SubMenuBuilder)) {
        paramMenuBuilder.getRootMenu().close(false);
      }
      MenuPresenter.Callback localCallback = getCallback();
      if (localCallback != null) {
        localCallback.onCloseMenu(paramMenuBuilder, paramBoolean);
      }
    }
    
    public boolean onOpenSubMenu(MenuBuilder paramMenuBuilder)
    {
      if (paramMenuBuilder == null) {
        return false;
      }
      mOpenSubMenuId = ((SubMenuBuilder)paramMenuBuilder).getItem().getItemId();
      MenuPresenter.Callback localCallback = getCallback();
      if (localCallback != null) {
        return localCallback.onOpenSubMenu(paramMenuBuilder);
      }
      return false;
    }
  }
  
  private static class SavedState
    implements Parcelable
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator()
    {
      public ActionMenuPresenter.SavedState createFromParcel(Parcel paramAnonymousParcel)
      {
        return new ActionMenuPresenter.SavedState(paramAnonymousParcel);
      }
      
      public ActionMenuPresenter.SavedState[] newArray(int paramAnonymousInt)
      {
        return new ActionMenuPresenter.SavedState[paramAnonymousInt];
      }
    };
    public int openSubMenuId;
    
    SavedState() {}
    
    SavedState(Parcel paramParcel)
    {
      openSubMenuId = paramParcel.readInt();
    }
    
    public int describeContents()
    {
      return 0;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      paramParcel.writeInt(openSubMenuId);
    }
  }
}
