package android.support.v7.view.menu;

import android.support.annotation.RestrictTo;
import android.support.v7.appcompat.R.layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class MenuAdapter
  extends BaseAdapter
{
  static final int ITEM_LAYOUT = R.layout.abc_popup_menu_item_layout;
  MenuBuilder mAdapterMenu;
  private int mExpandedIndex = -1;
  private boolean mForceShowIcon;
  private final LayoutInflater mInflater;
  private final boolean mOverflowOnly;
  
  public MenuAdapter(MenuBuilder paramMenuBuilder, LayoutInflater paramLayoutInflater, boolean paramBoolean)
  {
    mOverflowOnly = paramBoolean;
    mInflater = paramLayoutInflater;
    mAdapterMenu = paramMenuBuilder;
    findExpandedIndex();
  }
  
  void findExpandedIndex()
  {
    MenuItemImpl localMenuItemImpl = mAdapterMenu.getExpandedItem();
    if (localMenuItemImpl != null)
    {
      ArrayList localArrayList = mAdapterMenu.getNonActionItems();
      int j = localArrayList.size();
      int i = 0;
      while (i < j)
      {
        if ((MenuItemImpl)localArrayList.get(i) == localMenuItemImpl)
        {
          mExpandedIndex = i;
          return;
        }
        i += 1;
      }
    }
    mExpandedIndex = -1;
  }
  
  public MenuBuilder getAdapterMenu()
  {
    return mAdapterMenu;
  }
  
  public int getCount()
  {
    ArrayList localArrayList;
    if (mOverflowOnly) {
      localArrayList = mAdapterMenu.getNonActionItems();
    } else {
      localArrayList = mAdapterMenu.getVisibleItems();
    }
    if (mExpandedIndex < 0) {
      return localArrayList.size();
    }
    return localArrayList.size() - 1;
  }
  
  public boolean getForceShowIcon()
  {
    return mForceShowIcon;
  }
  
  public MenuItemImpl getItem(int paramInt)
  {
    ArrayList localArrayList;
    if (mOverflowOnly) {
      localArrayList = mAdapterMenu.getNonActionItems();
    } else {
      localArrayList = mAdapterMenu.getVisibleItems();
    }
    int i = paramInt;
    if (mExpandedIndex >= 0)
    {
      i = paramInt;
      if (paramInt >= mExpandedIndex) {
        i = paramInt + 1;
      }
    }
    return (MenuItemImpl)localArrayList.get(i);
  }
  
  public long getItemId(int paramInt)
  {
    return paramInt;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    View localView = paramView;
    if (paramView == null) {
      localView = mInflater.inflate(ITEM_LAYOUT, paramViewGroup, false);
    }
    paramView = (MenuView.ItemView)localView;
    if (mForceShowIcon) {
      ((ListMenuItemView)localView).setForceShowIcon(true);
    }
    paramView.initialize(getItem(paramInt), 0);
    return localView;
  }
  
  public void notifyDataSetChanged()
  {
    findExpandedIndex();
    super.notifyDataSetChanged();
  }
  
  public void setForceShowIcon(boolean paramBoolean)
  {
    mForceShowIcon = paramBoolean;
  }
}
