package android.support.v7.view;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff.Mode;
import android.support.annotation.RestrictTo;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuItemWrapperICS;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class SupportMenuInflater
  extends MenuInflater
{
  static final Class<?>[] ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE = ACTION_VIEW_CONSTRUCTOR_SIGNATURE;
  static final Class<?>[] ACTION_VIEW_CONSTRUCTOR_SIGNATURE = { Context.class };
  static final String LOG_TAG = "SupportMenuInflater";
  static final int NO_ID = 0;
  private static final String XML_GROUP = "group";
  private static final String XML_ITEM = "item";
  private static final String XML_MENU = "menu";
  final Object[] mActionProviderConstructorArguments;
  final Object[] mActionViewConstructorArguments;
  Context mContext;
  private Object mRealOwner;
  
  public SupportMenuInflater(Context paramContext)
  {
    super(paramContext);
    mContext = paramContext;
    mActionViewConstructorArguments = new Object[] { paramContext };
    mActionProviderConstructorArguments = mActionViewConstructorArguments;
  }
  
  private Object findRealOwner(Object paramObject)
  {
    if ((paramObject instanceof Activity)) {
      return paramObject;
    }
    Object localObject = paramObject;
    if ((paramObject instanceof ContextWrapper)) {
      localObject = findRealOwner(((ContextWrapper)paramObject).getBaseContext());
    }
    return localObject;
  }
  
  private void parseMenu(XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Menu paramMenu)
    throws XmlPullParserException, IOException
  {
    MenuState localMenuState = new MenuState(paramMenu);
    int j = paramXmlPullParser.getEventType();
    int i;
    do
    {
      if (j == 2)
      {
        paramMenu = paramXmlPullParser.getName();
        if (paramMenu.equals("menu"))
        {
          i = paramXmlPullParser.next();
          break;
        }
        paramXmlPullParser = new StringBuilder();
        paramXmlPullParser.append("Expecting menu, got ");
        paramXmlPullParser.append(paramMenu);
        throw new RuntimeException(paramXmlPullParser.toString());
      }
      k = paramXmlPullParser.next();
      i = k;
      j = i;
    } while (k != 1);
    Object localObject = null;
    j = 0;
    int k = 0;
    int n = i;
    while (j == 0)
    {
      int m;
      switch (n)
      {
      default: 
        paramMenu = (Menu)localObject;
        m = j;
        i = k;
        break;
      case 3: 
        String str = paramXmlPullParser.getName();
        if ((k != 0) && (str.equals(localObject)))
        {
          paramMenu = null;
          i = 0;
          m = j;
        }
        else if (str.equals("group"))
        {
          localMenuState.resetGroup();
          paramMenu = (Menu)localObject;
          m = j;
          i = k;
        }
        else if (str.equals("item"))
        {
          paramMenu = (Menu)localObject;
          m = j;
          i = k;
          if (!localMenuState.hasAddedItem()) {
            if ((itemActionProvider != null) && (itemActionProvider.hasSubMenu()))
            {
              localMenuState.addSubMenuItem();
              paramMenu = (Menu)localObject;
              m = j;
              i = k;
            }
            else
            {
              localMenuState.addItem();
              paramMenu = (Menu)localObject;
              m = j;
              i = k;
            }
          }
        }
        else
        {
          paramMenu = (Menu)localObject;
          m = j;
          i = k;
          if (str.equals("menu"))
          {
            m = 1;
            paramMenu = (Menu)localObject;
            i = k;
          }
        }
        break;
      case 2: 
        if (k != 0)
        {
          paramMenu = (Menu)localObject;
          m = j;
          i = k;
        }
        else
        {
          paramMenu = paramXmlPullParser.getName();
          if (paramMenu.equals("group"))
          {
            localMenuState.readGroup(paramAttributeSet);
            paramMenu = (Menu)localObject;
            m = j;
            i = k;
          }
          else if (paramMenu.equals("item"))
          {
            localMenuState.readItem(paramAttributeSet);
            paramMenu = (Menu)localObject;
            m = j;
            i = k;
          }
          else if (paramMenu.equals("menu"))
          {
            parseMenu(paramXmlPullParser, paramAttributeSet, localMenuState.addSubMenuItem());
            paramMenu = (Menu)localObject;
            m = j;
            i = k;
          }
          else
          {
            i = 1;
            m = j;
          }
        }
        break;
      case 1: 
        throw new RuntimeException("Unexpected end of document");
      }
      n = paramXmlPullParser.next();
      localObject = paramMenu;
      j = m;
      k = i;
    }
  }
  
  Object getRealOwner()
  {
    if (mRealOwner == null) {
      mRealOwner = findRealOwner(mContext);
    }
    return mRealOwner;
  }
  
  public void inflate(int paramInt, Menu paramMenu)
  {
    if (!(paramMenu instanceof SupportMenu))
    {
      super.inflate(paramInt, paramMenu);
      return;
    }
    Object localObject3 = null;
    Object localObject4 = null;
    Object localObject1 = null;
    Object localObject2 = mContext;
    try
    {
      localObject2 = ((Context)localObject2).getResources().getLayout(paramInt);
      try
      {
        parseMenu((XmlPullParser)localObject2, Xml.asAttributeSet((XmlPullParser)localObject2), paramMenu);
        if (localObject2 == null) {
          return;
        }
        ((XmlResourceParser)localObject2).close();
        return;
      }
      catch (Throwable paramMenu)
      {
        localObject1 = localObject2;
        break label119;
      }
      catch (IOException paramMenu)
      {
        localObject1 = localObject2;
      }
      catch (XmlPullParserException paramMenu)
      {
        localObject1 = localObject2;
      }
      throw new InflateException("Error inflating menu XML", paramMenu);
    }
    catch (Throwable paramMenu) {}catch (IOException paramMenu)
    {
      localObject1 = localObject3;
      throw new InflateException("Error inflating menu XML", paramMenu);
    }
    catch (XmlPullParserException paramMenu)
    {
      localObject1 = localObject4;
    }
    label119:
    if (localObject1 != null) {
      localObject1.close();
    }
    throw paramMenu;
  }
  
  private static class InflatedOnMenuItemClickListener
    implements MenuItem.OnMenuItemClickListener
  {
    private static final Class<?>[] PARAM_TYPES = { MenuItem.class };
    private Method mMethod;
    private Object mRealOwner;
    
    public InflatedOnMenuItemClickListener(Object paramObject, String paramString)
    {
      mRealOwner = paramObject;
      paramObject = paramObject.getClass();
      Object localObject = PARAM_TYPES;
      try
      {
        localObject = paramObject.getMethod(paramString, (Class[])localObject);
        mMethod = ((Method)localObject);
        return;
      }
      catch (Exception localException)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Couldn't resolve menu item onClick handler ");
        localStringBuilder.append(paramString);
        localStringBuilder.append(" in class ");
        localStringBuilder.append(paramObject.getName());
        paramObject = new InflateException(localStringBuilder.toString());
        paramObject.initCause(localException);
        throw paramObject;
      }
    }
    
    public boolean onMenuItemClick(MenuItem paramMenuItem)
    {
      Object localObject1 = mMethod;
      try
      {
        localObject1 = ((Method)localObject1).getReturnType();
        if (localObject1 == Boolean.TYPE)
        {
          localObject1 = mMethod;
          localObject2 = mRealOwner;
          paramMenuItem = ((Method)localObject1).invoke(localObject2, new Object[] { paramMenuItem });
          paramMenuItem = (Boolean)paramMenuItem;
          boolean bool = paramMenuItem.booleanValue();
          return bool;
        }
        localObject1 = mMethod;
        Object localObject2 = mRealOwner;
        ((Method)localObject1).invoke(localObject2, new Object[] { paramMenuItem });
        return true;
      }
      catch (Exception paramMenuItem)
      {
        throw new RuntimeException(paramMenuItem);
      }
    }
  }
  
  private class MenuState
  {
    private static final int defaultGroupId = 0;
    private static final int defaultItemCategory = 0;
    private static final int defaultItemCheckable = 0;
    private static final boolean defaultItemChecked = false;
    private static final boolean defaultItemEnabled = true;
    private static final int defaultItemId = 0;
    private static final int defaultItemOrder = 0;
    private static final boolean defaultItemVisible = true;
    private int groupCategory;
    private int groupCheckable;
    private boolean groupEnabled;
    private int groupId;
    private int groupOrder;
    private boolean groupVisible;
    ActionProvider itemActionProvider;
    private String itemActionProviderClassName;
    private String itemActionViewClassName;
    private int itemActionViewLayout;
    private boolean itemAdded;
    private int itemAlphabeticModifiers;
    private char itemAlphabeticShortcut;
    private int itemCategoryOrder;
    private int itemCheckable;
    private boolean itemChecked;
    private CharSequence itemContentDescription;
    private boolean itemEnabled;
    private int itemIconResId;
    private ColorStateList itemIconTintList = null;
    private PorterDuff.Mode itemIconTintMode = null;
    private int itemId;
    private String itemListenerMethodName;
    private int itemNumericModifiers;
    private char itemNumericShortcut;
    private int itemShowAsAction;
    private CharSequence itemTitle;
    private CharSequence itemTitleCondensed;
    private CharSequence itemTooltipText;
    private boolean itemVisible;
    private Menu menu;
    
    public MenuState(Menu paramMenu)
    {
      menu = paramMenu;
      resetGroup();
    }
    
    private char getShortcut(String paramString)
    {
      if (paramString == null) {
        return '\000';
      }
      return paramString.charAt(0);
    }
    
    private Object newInstance(String paramString, Class[] paramArrayOfClass, Object[] paramArrayOfObject)
    {
      Context localContext = mContext;
      try
      {
        paramArrayOfClass = localContext.getClassLoader().loadClass(paramString).getConstructor(paramArrayOfClass);
        paramArrayOfClass.setAccessible(true);
        paramArrayOfClass = paramArrayOfClass.newInstance(paramArrayOfObject);
        return paramArrayOfClass;
      }
      catch (Exception paramArrayOfClass)
      {
        paramArrayOfObject = new StringBuilder();
        paramArrayOfObject.append("Cannot instantiate class: ");
        paramArrayOfObject.append(paramString);
        Log.w("SupportMenuInflater", paramArrayOfObject.toString(), paramArrayOfClass);
      }
      return null;
    }
    
    private void setItem(MenuItem paramMenuItem)
    {
      Object localObject = paramMenuItem.setChecked(itemChecked).setVisible(itemVisible).setEnabled(itemEnabled);
      int j = itemCheckable;
      int i = 0;
      if (j >= 1) {
        bool = true;
      } else {
        bool = false;
      }
      ((MenuItem)localObject).setCheckable(bool).setTitleCondensed(itemTitleCondensed).setIcon(itemIconResId);
      if (itemShowAsAction >= 0) {
        paramMenuItem.setShowAsAction(itemShowAsAction);
      }
      if (itemListenerMethodName != null) {
        if (!mContext.isRestricted()) {
          paramMenuItem.setOnMenuItemClickListener(new SupportMenuInflater.InflatedOnMenuItemClickListener(getRealOwner(), itemListenerMethodName));
        } else {
          throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
        }
      }
      boolean bool = paramMenuItem instanceof MenuItemImpl;
      if (bool) {
        localObject = (MenuItemImpl)paramMenuItem;
      }
      if (itemCheckable >= 2) {
        if (bool) {
          ((MenuItemImpl)paramMenuItem).setExclusiveCheckable(true);
        } else if ((paramMenuItem instanceof MenuItemWrapperICS)) {
          ((MenuItemWrapperICS)paramMenuItem).setExclusiveCheckable(true);
        }
      }
      if (itemActionViewClassName != null)
      {
        paramMenuItem.setActionView((View)newInstance(itemActionViewClassName, SupportMenuInflater.ACTION_VIEW_CONSTRUCTOR_SIGNATURE, mActionViewConstructorArguments));
        i = 1;
      }
      if (itemActionViewLayout > 0) {
        if (i == 0) {
          paramMenuItem.setActionView(itemActionViewLayout);
        } else {
          Log.w("SupportMenuInflater", "Ignoring attribute 'itemActionViewLayout'. Action view already specified.");
        }
      }
      if (itemActionProvider != null) {
        MenuItemCompat.setActionProvider(paramMenuItem, itemActionProvider);
      }
      MenuItemCompat.setContentDescription(paramMenuItem, itemContentDescription);
      MenuItemCompat.setTooltipText(paramMenuItem, itemTooltipText);
      MenuItemCompat.setAlphabeticShortcut(paramMenuItem, itemAlphabeticShortcut, itemAlphabeticModifiers);
      MenuItemCompat.setNumericShortcut(paramMenuItem, itemNumericShortcut, itemNumericModifiers);
      if (itemIconTintMode != null) {
        MenuItemCompat.setIconTintMode(paramMenuItem, itemIconTintMode);
      }
      if (itemIconTintList != null) {
        MenuItemCompat.setIconTintList(paramMenuItem, itemIconTintList);
      }
    }
    
    public void addItem()
    {
      itemAdded = true;
      setItem(menu.add(groupId, itemId, itemCategoryOrder, itemTitle));
    }
    
    public SubMenu addSubMenuItem()
    {
      itemAdded = true;
      SubMenu localSubMenu = menu.addSubMenu(groupId, itemId, itemCategoryOrder, itemTitle);
      setItem(localSubMenu.getItem());
      return localSubMenu;
    }
    
    public boolean hasAddedItem()
    {
      return itemAdded;
    }
    
    public void readGroup(AttributeSet paramAttributeSet)
    {
      paramAttributeSet = mContext.obtainStyledAttributes(paramAttributeSet, R.styleable.MenuGroup);
      groupId = paramAttributeSet.getResourceId(R.styleable.MenuGroup_android_id, 0);
      groupCategory = paramAttributeSet.getInt(R.styleable.MenuGroup_android_menuCategory, 0);
      groupOrder = paramAttributeSet.getInt(R.styleable.MenuGroup_android_orderInCategory, 0);
      groupCheckable = paramAttributeSet.getInt(R.styleable.MenuGroup_android_checkableBehavior, 0);
      groupVisible = paramAttributeSet.getBoolean(R.styleable.MenuGroup_android_visible, true);
      groupEnabled = paramAttributeSet.getBoolean(R.styleable.MenuGroup_android_enabled, true);
      paramAttributeSet.recycle();
    }
    
    public void readItem(AttributeSet paramAttributeSet)
    {
      throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
    }
    
    public void resetGroup()
    {
      groupId = 0;
      groupCategory = 0;
      groupOrder = 0;
      groupCheckable = 0;
      groupVisible = true;
      groupEnabled = true;
    }
  }
}
