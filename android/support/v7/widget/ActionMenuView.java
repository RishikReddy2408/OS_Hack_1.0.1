package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuBuilder.Callback;
import android.support.v7.view.menu.MenuBuilder.ItemInvoker;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuPresenter.Callback;
import android.support.v7.view.menu.MenuView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewDebug.ExportedProperty;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;

public class ActionMenuView
  extends LinearLayoutCompat
  implements MenuBuilder.ItemInvoker, MenuView
{
  static final int GENERATED_ITEM_PADDING = 4;
  static final int MIN_CELL_SIZE = 56;
  private static final String TAG = "ActionMenuView";
  private MenuPresenter.Callback mActionMenuPresenterCallback;
  private boolean mFormatItems;
  private int mFormatItemsWidth;
  private int mGeneratedItemPadding;
  private MenuBuilder mMenu;
  MenuBuilder.Callback mMenuBuilderCallback;
  private int mMinCellSize;
  OnMenuItemClickListener mOnMenuItemClickListener;
  private Context mPopupContext;
  private int mPopupTheme;
  private ActionMenuPresenter mPresenter;
  private boolean mReserveOverflow;
  
  public ActionMenuView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public ActionMenuView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setBaselineAligned(false);
    float f = getResourcesgetDisplayMetricsdensity;
    mMinCellSize = ((int)(56.0F * f));
    mGeneratedItemPadding = ((int)(f * 4.0F));
    mPopupContext = paramContext;
    mPopupTheme = 0;
  }
  
  static int measureChildForCells(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    int j = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(paramInt3) - paramInt4, View.MeasureSpec.getMode(paramInt3));
    ActionMenuItemView localActionMenuItemView;
    if ((paramView instanceof ActionMenuItemView)) {
      localActionMenuItemView = (ActionMenuItemView)paramView;
    } else {
      localActionMenuItemView = null;
    }
    boolean bool = true;
    if ((localActionMenuItemView != null) && (localActionMenuItemView.hasText())) {
      paramInt3 = 1;
    } else {
      paramInt3 = 0;
    }
    paramInt4 = 2;
    if ((paramInt2 > 0) && ((paramInt3 == 0) || (paramInt2 >= 2)))
    {
      paramView.measure(View.MeasureSpec.makeMeasureSpec(paramInt2 * paramInt1, Integer.MIN_VALUE), j);
      int k = paramView.getMeasuredWidth();
      int i = k / paramInt1;
      paramInt2 = i;
      if (k % paramInt1 != 0) {
        paramInt2 = i + 1;
      }
      if ((paramInt3 != 0) && (paramInt2 < 2)) {
        paramInt2 = paramInt4;
      }
    }
    else
    {
      paramInt2 = 0;
    }
    if ((isOverflowButton) || (paramInt3 == 0)) {
      bool = false;
    }
    expandable = bool;
    cellsUsed = paramInt2;
    paramView.measure(View.MeasureSpec.makeMeasureSpec(paramInt1 * paramInt2, 1073741824), j);
    return paramInt2;
  }
  
  private void onMeasureExactFormat(int paramInt1, int paramInt2)
  {
    int i10 = View.MeasureSpec.getMode(paramInt2);
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    int i8 = View.MeasureSpec.getSize(paramInt2);
    int i = getPaddingLeft();
    int j = getPaddingRight();
    int i14 = getPaddingTop() + getPaddingBottom();
    int i4 = ViewGroup.getChildMeasureSpec(paramInt2, i14, -2);
    int m = i4;
    int i11 = paramInt1 - (i + j);
    paramInt1 = i11 / mMinCellSize;
    paramInt2 = mMinCellSize;
    if (paramInt1 == 0)
    {
      setMeasuredDimension(i11, 0);
      return;
    }
    int i12 = mMinCellSize + i11 % paramInt2 / paramInt1;
    int i13 = getChildCount();
    int i2 = 0;
    paramInt2 = 0;
    i = 0;
    int i1 = 0;
    int n = 0;
    j = 0;
    Object localObject;
    long l2;
    int i5;
    LayoutParams localLayoutParams;
    int i6;
    int i7;
    for (long l1 = 0L; i2 < i13; l1 = l2)
    {
      localObject = getChildAt(i2);
      if (((View)localObject).getVisibility() == 8)
      {
        i3 = i;
        l2 = l1;
      }
      else
      {
        boolean bool = localObject instanceof ActionMenuItemView;
        i5 = i1 + 1;
        if (bool) {
          ((View)localObject).setPadding(mGeneratedItemPadding, 0, mGeneratedItemPadding, 0);
        }
        localLayoutParams = (LayoutParams)((View)localObject).getLayoutParams();
        expanded = false;
        extraPixels = 0;
        cellsUsed = 0;
        expandable = false;
        leftMargin = 0;
        rightMargin = 0;
        if ((bool) && (((ActionMenuItemView)localObject).hasText())) {
          bool = true;
        } else {
          bool = false;
        }
        preventEdgeOffset = bool;
        if (isOverflowButton) {
          k = 1;
        } else {
          k = paramInt1;
        }
        int i15 = measureChildForCells((View)localObject, i12, k, i4, i14);
        i6 = Math.max(n, i15);
        k = j;
        if (expandable) {
          k = j + 1;
        }
        if (isOverflowButton) {
          i = 1;
        }
        i7 = paramInt1 - i15;
        int i9 = Math.max(paramInt2, ((View)localObject).getMeasuredHeight());
        j = k;
        paramInt2 = i9;
        i3 = i;
        i1 = i5;
        n = i6;
        l2 = l1;
        paramInt1 = i7;
        if (i15 == 1)
        {
          l2 = l1 | 1 << i2;
          j = k;
          paramInt2 = i9;
          i3 = i;
          i1 = i5;
          n = i6;
          paramInt1 = i7;
        }
      }
      i2 += 1;
      i = i3;
    }
    if ((i != 0) && (i1 == 2)) {
      i2 = 1;
    } else {
      i2 = 0;
    }
    int k = 0;
    int i3 = paramInt1;
    paramInt1 = k;
    long l3;
    if ((j > 0) && (i3 > 0))
    {
      i5 = 0;
      i7 = 0;
      i4 = Integer.MAX_VALUE;
      for (l3 = 0L; i5 < i13; l3 = l2)
      {
        localObject = (LayoutParams)getChildAt(i5).getLayoutParams();
        if (!expandable)
        {
          k = i7;
          i6 = i4;
          l2 = l3;
        }
        else if (cellsUsed < i4)
        {
          l2 = 1L << i5;
          i6 = cellsUsed;
          k = 1;
        }
        else
        {
          k = i7;
          i6 = i4;
          l2 = l3;
          if (cellsUsed == i4)
          {
            l2 = l3 | 1L << i5;
            k = i7 + 1;
            i6 = i4;
          }
        }
        i5 += 1;
        i7 = k;
        i4 = i6;
      }
      l1 |= l3;
      if (i7 > i3) {}
    }
    else
    {
      for (;;)
      {
        paramInt1 = 0;
        while (paramInt1 < i13)
        {
          localObject = getChildAt(paramInt1);
          localLayoutParams = (LayoutParams)((View)localObject).getLayoutParams();
          long l4 = 1 << paramInt1;
          if ((l3 & l4) == 0L)
          {
            l2 = l1;
            k = i3;
            if (cellsUsed == i4 + 1)
            {
              l2 = l1 | l4;
              k = i3;
            }
          }
          else
          {
            if ((i2 != 0) && (preventEdgeOffset) && (i3 == 1)) {
              ((View)localObject).setPadding(mGeneratedItemPadding + i12, 0, mGeneratedItemPadding, 0);
            }
            cellsUsed += 1;
            expanded = true;
            k = i3 - 1;
            l2 = l1;
          }
          paramInt1 += 1;
          l1 = l2;
          i3 = k;
        }
        paramInt1 = 1;
        break;
      }
    }
    if ((i == 0) && (i1 == 1)) {
      i = 1;
    } else {
      i = 0;
    }
    if ((i3 > 0) && (l1 != 0L) && ((i3 < i1 - 1) || (i != 0) || (n > 1)))
    {
      float f2 = Long.bitCount(l1);
      if (i == 0)
      {
        float f1;
        if ((l1 & 1L) != 0L)
        {
          f1 = f2;
          if (!getChildAt0getLayoutParamspreventEdgeOffset) {
            f1 = f2 - 0.5F;
          }
        }
        else
        {
          f1 = f2;
        }
        i = i13 - 1;
        f2 = f1;
        if ((l1 & 1 << i) != 0L)
        {
          f2 = f1;
          if (!getChildAtgetLayoutParamspreventEdgeOffset) {
            f2 = f1 - 0.5F;
          }
        }
      }
      if (f2 > 0.0F) {
        i = (int)(i3 * i12 / f2);
      } else {
        i = 0;
      }
      j = 0;
      while (j < i13)
      {
        if ((l1 & 1 << j) == 0L)
        {
          k = paramInt1;
        }
        else
        {
          localObject = getChildAt(j);
          localLayoutParams = (LayoutParams)((View)localObject).getLayoutParams();
          if ((localObject instanceof ActionMenuItemView))
          {
            extraPixels = i;
            expanded = true;
            if ((j == 0) && (!preventEdgeOffset)) {
              leftMargin = (-i / 2);
            }
          }
          for (;;)
          {
            k = 1;
            break label1173;
            if (!isOverflowButton) {
              break;
            }
            extraPixels = i;
            expanded = true;
            rightMargin = (-i / 2);
          }
          if (j != 0) {
            leftMargin = (i / 2);
          }
          k = paramInt1;
          if (j != i13 - 1)
          {
            rightMargin = (i / 2);
            k = paramInt1;
          }
        }
        label1173:
        j += 1;
        paramInt1 = k;
      }
    }
    i = 0;
    if (paramInt1 != 0)
    {
      paramInt1 = i;
      while (paramInt1 < i13)
      {
        localObject = getChildAt(paramInt1);
        localLayoutParams = (LayoutParams)((View)localObject).getLayoutParams();
        if (expanded) {
          ((View)localObject).measure(View.MeasureSpec.makeMeasureSpec(cellsUsed * i12 + extraPixels, 1073741824), m);
        }
        paramInt1 += 1;
      }
    }
    if (i10 != 1073741824) {
      paramInt1 = paramInt2;
    } else {
      paramInt1 = i8;
    }
    setMeasuredDimension(i11, paramInt1);
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return (paramLayoutParams != null) && ((paramLayoutParams instanceof LayoutParams));
  }
  
  public void dismissPopupMenus()
  {
    if (mPresenter != null) {
      mPresenter.dismissPopupMenus();
    }
  }
  
  public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    return false;
  }
  
  protected LayoutParams generateDefaultLayoutParams()
  {
    LayoutParams localLayoutParams = new LayoutParams(-2, -2);
    gravity = 16;
    return localLayoutParams;
  }
  
  public LayoutParams generateLayoutParams(AttributeSet paramAttributeSet)
  {
    return new LayoutParams(getContext(), paramAttributeSet);
  }
  
  protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    Object localObject;
    if (paramLayoutParams != null)
    {
      if ((paramLayoutParams instanceof LayoutParams)) {
        paramLayoutParams = new LayoutParams((LayoutParams)paramLayoutParams);
      } else {
        paramLayoutParams = new LayoutParams(paramLayoutParams);
      }
      localObject = paramLayoutParams;
      if (gravity <= 0)
      {
        gravity = 16;
        return paramLayoutParams;
      }
    }
    else
    {
      localObject = generateDefaultLayoutParams();
    }
    return localObject;
  }
  
  public LayoutParams generateOverflowButtonLayoutParams()
  {
    LayoutParams localLayoutParams = generateDefaultLayoutParams();
    isOverflowButton = true;
    return localLayoutParams;
  }
  
  public Menu getMenu()
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: fail exe a5 = a4\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:92)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.dfs(Cfg.java:255)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze0(BaseAnalyze.java:75)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze(BaseAnalyze.java:69)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer.transform(UnSSATransformer.java:274)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:163)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\nCaused by: java.lang.NullPointerException\n");
  }
  
  public Drawable getOverflowIcon()
  {
    getMenu();
    return mPresenter.getOverflowIcon();
  }
  
  public int getPopupTheme()
  {
    return mPopupTheme;
  }
  
  public int getWindowAnimations()
  {
    return 0;
  }
  
  protected boolean hasSupportDividerBeforeChildAt(int paramInt)
  {
    boolean bool2 = false;
    if (paramInt == 0) {
      return false;
    }
    View localView1 = getChildAt(paramInt - 1);
    View localView2 = getChildAt(paramInt);
    boolean bool1 = bool2;
    if (paramInt < getChildCount())
    {
      bool1 = bool2;
      if ((localView1 instanceof ActionMenuChildView)) {
        bool1 = false | ((ActionMenuChildView)localView1).needsDividerAfter();
      }
    }
    bool2 = bool1;
    if (paramInt > 0)
    {
      bool2 = bool1;
      if ((localView2 instanceof ActionMenuChildView)) {
        bool2 = bool1 | ((ActionMenuChildView)localView2).needsDividerBefore();
      }
    }
    return bool2;
  }
  
  public boolean hideOverflowMenu()
  {
    return (mPresenter != null) && (mPresenter.hideOverflowMenu());
  }
  
  public void initialize(MenuBuilder paramMenuBuilder)
  {
    mMenu = paramMenuBuilder;
  }
  
  public boolean invokeItem(MenuItemImpl paramMenuItemImpl)
  {
    return mMenu.performItemAction(paramMenuItemImpl, 0);
  }
  
  public boolean isOverflowMenuShowPending()
  {
    return (mPresenter != null) && (mPresenter.isOverflowMenuShowPending());
  }
  
  public boolean isOverflowMenuShowing()
  {
    return (mPresenter != null) && (mPresenter.isOverflowMenuShowing());
  }
  
  public boolean isOverflowReserved()
  {
    return mReserveOverflow;
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    if (mPresenter != null)
    {
      mPresenter.updateMenuView(false);
      if (mPresenter.isOverflowMenuShowing())
      {
        mPresenter.hideOverflowMenu();
        mPresenter.showOverflowMenu();
      }
    }
  }
  
  public void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    dismissPopupMenus();
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (!mFormatItems)
    {
      super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
      return;
    }
    int m = getChildCount();
    int k = (paramInt4 - paramInt2) / 2;
    int n = getDividerWidth();
    int i1 = paramInt3 - paramInt1;
    paramInt1 = getPaddingRight();
    paramInt2 = getPaddingLeft();
    paramBoolean = ViewUtils.isLayoutRtl(this);
    paramInt1 = i1 - paramInt1 - paramInt2;
    paramInt2 = 0;
    paramInt4 = 0;
    paramInt3 = 0;
    View localView;
    LayoutParams localLayoutParams;
    int j;
    while (paramInt2 < m)
    {
      localView = getChildAt(paramInt2);
      if (localView.getVisibility() != 8)
      {
        localLayoutParams = (LayoutParams)localView.getLayoutParams();
        if (isOverflowButton)
        {
          i = localView.getMeasuredWidth();
          paramInt4 = i;
          if (hasSupportDividerBeforeChildAt(paramInt2)) {
            paramInt4 = i + n;
          }
          int i2 = localView.getMeasuredHeight();
          if (paramBoolean)
          {
            i = getPaddingLeft() + leftMargin;
            j = i + paramInt4;
          }
          else
          {
            j = getWidth() - getPaddingRight() - rightMargin;
            i = j - paramInt4;
          }
          int i3 = k - i2 / 2;
          localView.layout(i, i3, j, i2 + i3);
          paramInt1 -= paramInt4;
          paramInt4 = 1;
        }
        else
        {
          paramInt1 -= localView.getMeasuredWidth() + leftMargin + rightMargin;
          hasSupportDividerBeforeChildAt(paramInt2);
          paramInt3 += 1;
        }
      }
      paramInt2 += 1;
    }
    if ((m == 1) && (paramInt4 == 0))
    {
      localView = getChildAt(0);
      paramInt1 = localView.getMeasuredWidth();
      paramInt2 = localView.getMeasuredHeight();
      paramInt3 = i1 / 2 - paramInt1 / 2;
      paramInt4 = k - paramInt2 / 2;
      localView.layout(paramInt3, paramInt4, paramInt1 + paramInt3, paramInt2 + paramInt4);
      return;
    }
    paramInt2 = paramInt3 - (paramInt4 ^ 0x1);
    if (paramInt2 > 0) {
      paramInt1 /= paramInt2;
    } else {
      paramInt1 = 0;
    }
    paramInt4 = 0;
    paramInt3 = 0;
    int i = Math.max(0, paramInt1);
    if (paramBoolean)
    {
      paramInt2 = getWidth() - getPaddingRight();
      paramInt1 = paramInt3;
      while (paramInt1 < m)
      {
        localView = getChildAt(paramInt1);
        localLayoutParams = (LayoutParams)localView.getLayoutParams();
        paramInt3 = paramInt2;
        if (localView.getVisibility() != 8) {
          if (isOverflowButton)
          {
            paramInt3 = paramInt2;
          }
          else
          {
            paramInt2 -= rightMargin;
            paramInt3 = localView.getMeasuredWidth();
            paramInt4 = localView.getMeasuredHeight();
            j = k - paramInt4 / 2;
            localView.layout(paramInt2 - paramInt3, j, paramInt2, paramInt4 + j);
            paramInt3 = paramInt2 - (paramInt3 + leftMargin + i);
          }
        }
        paramInt1 += 1;
        paramInt2 = paramInt3;
      }
    }
    paramInt2 = getPaddingLeft();
    paramInt1 = paramInt4;
    while (paramInt1 < m)
    {
      localView = getChildAt(paramInt1);
      localLayoutParams = (LayoutParams)localView.getLayoutParams();
      paramInt3 = paramInt2;
      if (localView.getVisibility() != 8) {
        if (isOverflowButton)
        {
          paramInt3 = paramInt2;
        }
        else
        {
          paramInt2 += leftMargin;
          paramInt3 = localView.getMeasuredWidth();
          paramInt4 = localView.getMeasuredHeight();
          j = k - paramInt4 / 2;
          localView.layout(paramInt2, j, paramInt2 + paramInt3, paramInt4 + j);
          paramInt3 = paramInt2 + (paramInt3 + rightMargin + i);
        }
      }
      paramInt1 += 1;
      paramInt2 = paramInt3;
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    boolean bool2 = mFormatItems;
    boolean bool1;
    if (View.MeasureSpec.getMode(paramInt1) == 1073741824) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    mFormatItems = bool1;
    if (bool2 != mFormatItems) {
      mFormatItemsWidth = 0;
    }
    int i = View.MeasureSpec.getSize(paramInt1);
    if ((mFormatItems) && (mMenu != null) && (i != mFormatItemsWidth))
    {
      mFormatItemsWidth = i;
      mMenu.onItemsChanged(true);
    }
    int j = getChildCount();
    if ((mFormatItems) && (j > 0))
    {
      onMeasureExactFormat(paramInt1, paramInt2);
      return;
    }
    i = 0;
    while (i < j)
    {
      LayoutParams localLayoutParams = (LayoutParams)getChildAt(i).getLayoutParams();
      rightMargin = 0;
      leftMargin = 0;
      i += 1;
    }
    super.onMeasure(paramInt1, paramInt2);
  }
  
  public MenuBuilder peekMenu()
  {
    return mMenu;
  }
  
  public void setExpandedActionViewsExclusive(boolean paramBoolean)
  {
    mPresenter.setExpandedActionViewsExclusive(paramBoolean);
  }
  
  public void setMenuCallbacks(MenuPresenter.Callback paramCallback, MenuBuilder.Callback paramCallback1)
  {
    mActionMenuPresenterCallback = paramCallback;
    mMenuBuilderCallback = paramCallback1;
  }
  
  public void setOnMenuItemClickListener(OnMenuItemClickListener paramOnMenuItemClickListener)
  {
    mOnMenuItemClickListener = paramOnMenuItemClickListener;
  }
  
  public void setOverflowIcon(Drawable paramDrawable)
  {
    getMenu();
    mPresenter.setOverflowIcon(paramDrawable);
  }
  
  public void setOverflowReserved(boolean paramBoolean)
  {
    mReserveOverflow = paramBoolean;
  }
  
  public void setPopupTheme(int paramInt)
  {
    if (mPopupTheme != paramInt)
    {
      mPopupTheme = paramInt;
      if (paramInt == 0)
      {
        mPopupContext = getContext();
        return;
      }
      mPopupContext = new ContextThemeWrapper(getContext(), paramInt);
    }
  }
  
  public void setPresenter(ActionMenuPresenter paramActionMenuPresenter)
  {
    mPresenter = paramActionMenuPresenter;
    mPresenter.setMenuView(this);
  }
  
  public boolean showOverflowMenu()
  {
    return (mPresenter != null) && (mPresenter.showOverflowMenu());
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static abstract interface ActionMenuChildView
  {
    public abstract boolean needsDividerAfter();
    
    public abstract boolean needsDividerBefore();
  }
  
  private static class ActionMenuPresenterCallback
    implements MenuPresenter.Callback
  {
    ActionMenuPresenterCallback() {}
    
    public void onCloseMenu(MenuBuilder paramMenuBuilder, boolean paramBoolean) {}
    
    public boolean onOpenSubMenu(MenuBuilder paramMenuBuilder)
    {
      return false;
    }
  }
  
  public static class LayoutParams
    extends LinearLayoutCompat.LayoutParams
  {
    @ViewDebug.ExportedProperty
    public int cellsUsed;
    @ViewDebug.ExportedProperty
    public boolean expandable;
    boolean expanded;
    @ViewDebug.ExportedProperty
    public int extraPixels;
    @ViewDebug.ExportedProperty
    public boolean isOverflowButton;
    @ViewDebug.ExportedProperty
    public boolean preventEdgeOffset;
    
    public LayoutParams(int paramInt1, int paramInt2)
    {
      super(paramInt2);
      isOverflowButton = false;
    }
    
    LayoutParams(int paramInt1, int paramInt2, boolean paramBoolean)
    {
      super(paramInt2);
      isOverflowButton = paramBoolean;
    }
    
    public LayoutParams(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
    }
    
    public LayoutParams(LayoutParams paramLayoutParams)
    {
      super();
      isOverflowButton = isOverflowButton;
    }
    
    public LayoutParams(ViewGroup.LayoutParams paramLayoutParams)
    {
      super();
    }
  }
  
  private class MenuBuilderCallback
    implements MenuBuilder.Callback
  {
    MenuBuilderCallback() {}
    
    public boolean onMenuItemSelected(MenuBuilder paramMenuBuilder, MenuItem paramMenuItem)
    {
      return (mOnMenuItemClickListener != null) && (mOnMenuItemClickListener.onMenuItemClick(paramMenuItem));
    }
    
    public void onMenuModeChange(MenuBuilder paramMenuBuilder)
    {
      if (mMenuBuilderCallback != null) {
        mMenuBuilderCallback.onMenuModeChange(paramMenuBuilder);
      }
    }
  }
  
  public static abstract interface OnMenuItemClickListener
  {
    public abstract boolean onMenuItemClick(MenuItem paramMenuItem);
  }
}
