package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.TintableCompoundButton;
import android.support.v7.appcompat.R.attr;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

public class AppCompatRadioButton
  extends RadioButton
  implements TintableCompoundButton
{
  private final AppCompatCompoundButtonHelper mCompoundButtonHelper = new AppCompatCompoundButtonHelper(this);
  private final AppCompatTextHelper mTextHelper;
  
  public AppCompatRadioButton(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public AppCompatRadioButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.radioButtonStyle);
  }
  
  public AppCompatRadioButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(TintContextWrapper.wrap(paramContext), paramAttributeSet, paramInt);
    mCompoundButtonHelper.loadFromAttributes(paramAttributeSet, paramInt);
    mTextHelper = new AppCompatTextHelper(this);
    mTextHelper.loadFromAttributes(paramAttributeSet, paramInt);
  }
  
  public int getCompoundPaddingLeft()
  {
    int j = super.getCompoundPaddingLeft();
    int i = j;
    if (mCompoundButtonHelper != null) {
      i = mCompoundButtonHelper.getCompoundPaddingLeft(j);
    }
    return i;
  }
  
  public ColorStateList getSupportButtonTintList()
  {
    if (mCompoundButtonHelper != null) {
      return mCompoundButtonHelper.getSupportButtonTintList();
    }
    return null;
  }
  
  public PorterDuff.Mode getSupportButtonTintMode()
  {
    if (mCompoundButtonHelper != null) {
      return mCompoundButtonHelper.getSupportButtonTintMode();
    }
    return null;
  }
  
  public void setButtonDrawable(int paramInt)
  {
    setButtonDrawable(AppCompatResources.getDrawable(getContext(), paramInt));
  }
  
  public void setButtonDrawable(Drawable paramDrawable)
  {
    super.setButtonDrawable(paramDrawable);
    if (mCompoundButtonHelper != null) {
      mCompoundButtonHelper.onSetButtonDrawable();
    }
  }
  
  public void setSupportButtonTintList(ColorStateList paramColorStateList)
  {
    if (mCompoundButtonHelper != null) {
      mCompoundButtonHelper.setSupportButtonTintList(paramColorStateList);
    }
  }
  
  public void setSupportButtonTintMode(PorterDuff.Mode paramMode)
  {
    if (mCompoundButtonHelper != null) {
      mCompoundButtonHelper.setSupportButtonTintMode(paramMode);
    }
  }
}
