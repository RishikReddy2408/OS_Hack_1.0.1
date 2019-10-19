package android.support.v7.widget;

import android.content.Context;
import android.support.annotation.RestrictTo;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.id;
import android.support.v7.appcompat.R.layout;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.view.ActionMode;
import android.support.v7.view.menu.MenuBuilder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityRecord;
import android.widget.LinearLayout;
import android.widget.TextView;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class ActionBarContextView
  extends AbsActionBarView
{
  private static final String TAG = "ActionBarContextView";
  private View mClose;
  private int mCloseItemLayout;
  private View mCustomView;
  private CharSequence mSubtitle;
  private int mSubtitleStyleRes;
  private TextView mSubtitleView;
  private CharSequence mTitle;
  private LinearLayout mTitleLayout;
  private boolean mTitleOptional;
  private int mTitleStyleRes;
  private TextView mTitleView;
  
  public ActionBarContextView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public ActionBarContextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.actionModeStyle);
  }
  
  public ActionBarContextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    paramContext = TintTypedArray.obtainStyledAttributes(paramContext, paramAttributeSet, R.styleable.ActionMode, paramInt, 0);
    ViewCompat.setBackground(this, paramContext.getDrawable(R.styleable.ActionMode_background));
    mTitleStyleRes = paramContext.getResourceId(R.styleable.ActionMode_titleTextStyle, 0);
    mSubtitleStyleRes = paramContext.getResourceId(R.styleable.ActionMode_subtitleTextStyle, 0);
    mContentHeight = paramContext.getLayoutDimension(R.styleable.ActionMode_height, 0);
    mCloseItemLayout = paramContext.getResourceId(R.styleable.ActionMode_closeItemLayout, R.layout.abc_action_mode_close_item_material);
    paramContext.recycle();
  }
  
  private void initTitle()
  {
    if (mTitleLayout == null)
    {
      LayoutInflater.from(getContext()).inflate(R.layout.abc_action_bar_title_item, this);
      mTitleLayout = ((LinearLayout)getChildAt(getChildCount() - 1));
      mTitleView = ((TextView)mTitleLayout.findViewById(R.id.action_bar_title));
      mSubtitleView = ((TextView)mTitleLayout.findViewById(R.id.action_bar_subtitle));
      if (mTitleStyleRes != 0) {
        mTitleView.setTextAppearance(getContext(), mTitleStyleRes);
      }
      if (mSubtitleStyleRes != 0) {
        mSubtitleView.setTextAppearance(getContext(), mSubtitleStyleRes);
      }
    }
    mTitleView.setText(mTitle);
    mSubtitleView.setText(mSubtitle);
    boolean bool2 = TextUtils.isEmpty(mTitle);
    boolean bool1 = TextUtils.isEmpty(mSubtitle) ^ true;
    Object localObject = mSubtitleView;
    int j = 8;
    int i;
    if (bool1) {
      i = 0;
    } else {
      i = 8;
    }
    ((View)localObject).setVisibility(i);
    localObject = mTitleLayout;
    if (!(bool2 ^ true))
    {
      i = j;
      if (!bool1) {}
    }
    else
    {
      i = 0;
    }
    ((View)localObject).setVisibility(i);
    if (mTitleLayout.getParent() == null) {
      addView(mTitleLayout);
    }
  }
  
  public void closeMode()
  {
    if (mClose == null) {
      killMode();
    }
  }
  
  protected ViewGroup.LayoutParams generateDefaultLayoutParams()
  {
    return new ViewGroup.MarginLayoutParams(-1, -2);
  }
  
  public ViewGroup.LayoutParams generateLayoutParams(AttributeSet paramAttributeSet)
  {
    return new ViewGroup.MarginLayoutParams(getContext(), paramAttributeSet);
  }
  
  public CharSequence getSubtitle()
  {
    return mSubtitle;
  }
  
  public CharSequence getTitle()
  {
    return mTitle;
  }
  
  public boolean hideOverflowMenu()
  {
    if (mActionMenuPresenter != null) {
      return mActionMenuPresenter.hideOverflowMenu();
    }
    return false;
  }
  
  public void initForMode(final ActionMode paramActionMode)
  {
    if (mClose == null)
    {
      mClose = LayoutInflater.from(getContext()).inflate(mCloseItemLayout, this, false);
      addView(mClose);
    }
    else if (mClose.getParent() == null)
    {
      addView(mClose);
    }
    mClose.findViewById(R.id.action_mode_close_button).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramActionMode.finish();
      }
    });
    paramActionMode = (MenuBuilder)paramActionMode.getMenu();
    if (mActionMenuPresenter != null) {
      mActionMenuPresenter.dismissPopupMenus();
    }
    mActionMenuPresenter = new ActionMenuPresenter(getContext());
    mActionMenuPresenter.setReserveOverflow(true);
    ViewGroup.LayoutParams localLayoutParams = new ViewGroup.LayoutParams(-2, -1);
    paramActionMode.addMenuPresenter(mActionMenuPresenter, mPopupContext);
    mMenuView = ((ActionMenuView)mActionMenuPresenter.getMenuView(this));
    ViewCompat.setBackground(mMenuView, null);
    addView(mMenuView, localLayoutParams);
  }
  
  public boolean isOverflowMenuShowing()
  {
    if (mActionMenuPresenter != null) {
      return mActionMenuPresenter.isOverflowMenuShowing();
    }
    return false;
  }
  
  public boolean isTitleOptional()
  {
    return mTitleOptional;
  }
  
  public void killMode()
  {
    removeAllViews();
    mCustomView = null;
    mMenuView = null;
  }
  
  public void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    if (mActionMenuPresenter != null)
    {
      mActionMenuPresenter.hideOverflowMenu();
      mActionMenuPresenter.hideSubMenus();
    }
  }
  
  public void onInitializeAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    if (paramAccessibilityEvent.getEventType() == 32)
    {
      paramAccessibilityEvent.setSource(this);
      paramAccessibilityEvent.setClassName(getClass().getName());
      paramAccessibilityEvent.setPackageName(getContext().getPackageName());
      paramAccessibilityEvent.setContentDescription(mTitle);
      return;
    }
    super.onInitializeAccessibilityEvent(paramAccessibilityEvent);
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramBoolean = ViewUtils.isLayoutRtl(this);
    int i;
    if (paramBoolean) {
      i = paramInt3 - paramInt1 - getPaddingRight();
    } else {
      i = getPaddingLeft();
    }
    int j = getPaddingTop();
    int k = paramInt4 - paramInt2 - getPaddingTop() - getPaddingBottom();
    if ((mClose != null) && (mClose.getVisibility() != 8))
    {
      ViewGroup.MarginLayoutParams localMarginLayoutParams = (ViewGroup.MarginLayoutParams)mClose.getLayoutParams();
      if (paramBoolean) {
        paramInt2 = rightMargin;
      } else {
        paramInt2 = leftMargin;
      }
      if (paramBoolean) {
        paramInt4 = leftMargin;
      } else {
        paramInt4 = rightMargin;
      }
      paramInt2 = AbsActionBarView.next(i, paramInt2, paramBoolean);
      paramInt2 = AbsActionBarView.next(paramInt2 + positionChild(mClose, paramInt2, j, k, paramBoolean), paramInt4, paramBoolean);
    }
    else
    {
      paramInt2 = i;
    }
    paramInt4 = paramInt2;
    if (mTitleLayout != null)
    {
      paramInt4 = paramInt2;
      if (mCustomView == null)
      {
        paramInt4 = paramInt2;
        if (mTitleLayout.getVisibility() != 8) {
          paramInt4 = paramInt2 + positionChild(mTitleLayout, paramInt2, j, k, paramBoolean);
        }
      }
    }
    if (mCustomView != null) {
      positionChild(mCustomView, paramInt4, j, k, paramBoolean);
    }
    if (paramBoolean) {
      paramInt1 = getPaddingLeft();
    } else {
      paramInt1 = paramInt3 - paramInt1 - getPaddingRight();
    }
    if (mMenuView != null) {
      positionChild(mMenuView, paramInt1, j, k, paramBoolean ^ true);
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = View.MeasureSpec.getMode(paramInt1);
    int k = 1073741824;
    if (i == 1073741824)
    {
      if (View.MeasureSpec.getMode(paramInt2) != 0)
      {
        int i1 = View.MeasureSpec.getSize(paramInt1);
        if (mContentHeight > 0) {
          i = mContentHeight;
        } else {
          i = View.MeasureSpec.getSize(paramInt2);
        }
        int i2 = getPaddingTop() + getPaddingBottom();
        paramInt1 = i1 - getPaddingLeft() - getPaddingRight();
        int n = i - i2;
        int j = View.MeasureSpec.makeMeasureSpec(n, Integer.MIN_VALUE);
        localObject = mClose;
        int m = 0;
        paramInt2 = paramInt1;
        if (localObject != null)
        {
          paramInt1 = measureChildView(mClose, paramInt1, j, 0);
          localObject = (ViewGroup.MarginLayoutParams)mClose.getLayoutParams();
          paramInt2 = paramInt1 - (leftMargin + rightMargin);
        }
        paramInt1 = paramInt2;
        if (mMenuView != null)
        {
          paramInt1 = paramInt2;
          if (mMenuView.getParent() == this) {
            paramInt1 = measureChildView(mMenuView, paramInt2, j, 0);
          }
        }
        paramInt2 = paramInt1;
        if (mTitleLayout != null)
        {
          paramInt2 = paramInt1;
          if (mCustomView == null) {
            if (mTitleOptional)
            {
              paramInt2 = View.MeasureSpec.makeMeasureSpec(0, 0);
              mTitleLayout.measure(paramInt2, j);
              int i3 = mTitleLayout.getMeasuredWidth();
              if (i3 <= paramInt1) {
                j = 1;
              } else {
                j = 0;
              }
              paramInt2 = paramInt1;
              if (j != 0) {
                paramInt2 = paramInt1 - i3;
              }
              localObject = mTitleLayout;
              if (j != 0) {
                paramInt1 = 0;
              } else {
                paramInt1 = 8;
              }
              ((View)localObject).setVisibility(paramInt1);
            }
            else
            {
              paramInt2 = measureChildView(mTitleLayout, paramInt1, j, 0);
            }
          }
        }
        if (mCustomView != null)
        {
          localObject = mCustomView.getLayoutParams();
          if (width != -2) {
            paramInt1 = 1073741824;
          } else {
            paramInt1 = Integer.MIN_VALUE;
          }
          j = paramInt2;
          if (width >= 0) {
            j = Math.min(width, paramInt2);
          }
          if (height != -2) {
            paramInt2 = k;
          } else {
            paramInt2 = Integer.MIN_VALUE;
          }
          k = n;
          if (height >= 0) {
            k = Math.min(height, n);
          }
          mCustomView.measure(View.MeasureSpec.makeMeasureSpec(j, paramInt1), View.MeasureSpec.makeMeasureSpec(k, paramInt2));
        }
        if (mContentHeight <= 0)
        {
          k = getChildCount();
          paramInt2 = 0;
          paramInt1 = m;
          while (paramInt1 < k)
          {
            j = getChildAt(paramInt1).getMeasuredHeight() + i2;
            i = paramInt2;
            if (j > paramInt2) {
              i = j;
            }
            paramInt1 += 1;
            paramInt2 = i;
          }
          setMeasuredDimension(i1, paramInt2);
          return;
        }
        setMeasuredDimension(i1, i);
        return;
      }
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(getClass().getSimpleName());
      ((StringBuilder)localObject).append(" can only be used ");
      ((StringBuilder)localObject).append("with android:layout_height=\"wrap_content\"");
      throw new IllegalStateException(((StringBuilder)localObject).toString());
    }
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(getClass().getSimpleName());
    ((StringBuilder)localObject).append(" can only be used ");
    ((StringBuilder)localObject).append("with android:layout_width=\"match_parent\" (or fill_parent)");
    throw new IllegalStateException(((StringBuilder)localObject).toString());
  }
  
  public void setContentHeight(int paramInt)
  {
    mContentHeight = paramInt;
  }
  
  public void setCustomView(View paramView)
  {
    if (mCustomView != null) {
      removeView(mCustomView);
    }
    mCustomView = paramView;
    if ((paramView != null) && (mTitleLayout != null))
    {
      removeView(mTitleLayout);
      mTitleLayout = null;
    }
    if (paramView != null) {
      addView(paramView);
    }
    requestLayout();
  }
  
  public void setSubtitle(CharSequence paramCharSequence)
  {
    mSubtitle = paramCharSequence;
    initTitle();
  }
  
  public void setTitle(CharSequence paramCharSequence)
  {
    mTitle = paramCharSequence;
    initTitle();
  }
  
  public void setTitleOptional(boolean paramBoolean)
  {
    if (paramBoolean != mTitleOptional) {
      requestLayout();
    }
    mTitleOptional = paramBoolean;
  }
  
  public boolean shouldDelayChildPressedState()
  {
    return false;
  }
  
  public boolean showOverflowMenu()
  {
    if (mActionMenuPresenter != null) {
      return mActionMenuPresenter.showOverflowMenu();
    }
    return false;
  }
}
