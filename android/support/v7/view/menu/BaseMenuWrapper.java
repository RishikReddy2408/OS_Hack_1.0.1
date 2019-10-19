package android.support.v7.view.menu;

import android.content.Context;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.internal.view.SupportSubMenu;
import android.support.v4.util.ArrayMap;
import android.view.MenuItem;
import android.view.SubMenu;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

abstract class BaseMenuWrapper<T>
  extends BaseWrapper<T>
{
  final Context mContext;
  private Map<SupportMenuItem, MenuItem> mMenuItems;
  private Map<SupportSubMenu, SubMenu> mSubMenus;
  
  BaseMenuWrapper(Context paramContext, Object paramObject)
  {
    super(paramObject);
    mContext = paramContext;
  }
  
  final MenuItem getMenuItemWrapper(MenuItem paramMenuItem)
  {
    MenuItem localMenuItem = paramMenuItem;
    if ((paramMenuItem instanceof SupportMenuItem))
    {
      SupportMenuItem localSupportMenuItem = (SupportMenuItem)paramMenuItem;
      if (mMenuItems == null) {
        mMenuItems = new ArrayMap();
      }
      paramMenuItem = (MenuItem)mMenuItems.get(paramMenuItem);
      localMenuItem = paramMenuItem;
      if (paramMenuItem == null)
      {
        localMenuItem = MenuWrapperFactory.wrapSupportMenuItem(mContext, localSupportMenuItem);
        mMenuItems.put(localSupportMenuItem, localMenuItem);
      }
    }
    return localMenuItem;
  }
  
  final SubMenu getSubMenuWrapper(SubMenu paramSubMenu)
  {
    SubMenu localSubMenu = paramSubMenu;
    if ((paramSubMenu instanceof SupportSubMenu))
    {
      SupportSubMenu localSupportSubMenu = (SupportSubMenu)paramSubMenu;
      if (mSubMenus == null) {
        mSubMenus = new ArrayMap();
      }
      paramSubMenu = (SubMenu)mSubMenus.get(localSupportSubMenu);
      localSubMenu = paramSubMenu;
      if (paramSubMenu == null)
      {
        localSubMenu = MenuWrapperFactory.wrapSupportSubMenu(mContext, localSupportSubMenu);
        mSubMenus.put(localSupportSubMenu, localSubMenu);
      }
    }
    return localSubMenu;
  }
  
  final void internalClear()
  {
    if (mMenuItems != null) {
      mMenuItems.clear();
    }
    if (mSubMenus != null) {
      mSubMenus.clear();
    }
  }
  
  final void internalRemoveGroup(int paramInt)
  {
    if (mMenuItems == null) {
      return;
    }
    Iterator localIterator = mMenuItems.keySet().iterator();
    while (localIterator.hasNext()) {
      if (paramInt == ((MenuItem)localIterator.next()).getGroupId()) {
        localIterator.remove();
      }
    }
  }
  
  final void internalRemoveItem(int paramInt)
  {
    if (mMenuItems == null) {
      return;
    }
    Iterator localIterator = mMenuItems.keySet().iterator();
    while (localIterator.hasNext()) {
      if (paramInt == ((MenuItem)localIterator.next()).getItemId()) {
        localIterator.remove();
      }
    }
  }
}
