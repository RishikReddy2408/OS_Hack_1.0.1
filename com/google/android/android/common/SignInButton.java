package com.google.android.android.common;

import android.content.Context;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.android.base.R.styleable;
import com.google.android.android.common.aimsicd.Scope;
import com.google.android.android.common.internal.SignInButtonCreator;
import com.google.android.android.common.internal.SignInButtonImpl;
import com.google.android.android.dynamic.RemoteCreator.RemoteCreatorException;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class SignInButton
  extends FrameLayout
  implements View.OnClickListener
{
  public static final int COLOR_AUTO = 2;
  public static final int COLOR_DARK = 0;
  public static final int COLOR_LIGHT = 1;
  public static final int SIZE_ICON_ONLY = 2;
  public static final int SIZE_STANDARD = 0;
  public static final int SIZE_WIDE = 1;
  private int mColor;
  private int mSize;
  private View zaas;
  private View.OnClickListener zaat = null;
  
  public SignInButton(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public SignInButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public SignInButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    paramContext = paramContext.getTheme().obtainStyledAttributes(paramAttributeSet, R.styleable.SignInButton, 0, 0);
    try
    {
      mSize = paramContext.getInt(R.styleable.SignInButton_buttonSize, 0);
      mColor = paramContext.getInt(R.styleable.SignInButton_colorScheme, 2);
      paramContext.recycle();
      setStyle(mSize, mColor);
      return;
    }
    catch (Throwable paramAttributeSet)
    {
      paramContext.recycle();
      throw paramAttributeSet;
    }
  }
  
  public final void onClick(View paramView)
  {
    if ((zaat != null) && (paramView == zaas)) {
      zaat.onClick(this);
    }
  }
  
  public final void setColorScheme(int paramInt)
  {
    setStyle(mSize, paramInt);
  }
  
  public final void setEnabled(boolean paramBoolean)
  {
    super.setEnabled(paramBoolean);
    zaas.setEnabled(paramBoolean);
  }
  
  public final void setOnClickListener(View.OnClickListener paramOnClickListener)
  {
    zaat = paramOnClickListener;
    if (zaas != null) {
      zaas.setOnClickListener(this);
    }
  }
  
  public final void setScopes(Scope[] paramArrayOfScope)
  {
    setStyle(mSize, mColor);
  }
  
  public final void setSize(int paramInt)
  {
    setStyle(paramInt, mColor);
  }
  
  public final void setStyle(int paramInt1, int paramInt2)
  {
    mSize = paramInt1;
    mColor = paramInt2;
    Context localContext = getContext();
    if (zaas != null) {
      removeView(zaas);
    }
    paramInt1 = mSize;
    paramInt2 = mColor;
    try
    {
      localObject = SignInButtonCreator.createView(localContext, paramInt1, paramInt2);
      zaas = ((View)localObject);
    }
    catch (RemoteCreator.RemoteCreatorException localRemoteCreatorException)
    {
      Object localObject;
      for (;;) {}
    }
    Log.w("SignInButton", "Sign in button not found, using placeholder instead");
    paramInt1 = mSize;
    paramInt2 = mColor;
    localObject = new SignInButtonImpl(localContext);
    ((SignInButtonImpl)localObject).configure(localContext.getResources(), paramInt1, paramInt2);
    zaas = ((View)localObject);
    addView(zaas);
    zaas.setEnabled(isEnabled());
    zaas.setOnClickListener(this);
  }
  
  public final void setStyle(int paramInt1, int paramInt2, Scope[] paramArrayOfScope)
  {
    setStyle(paramInt1, paramInt2);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public @interface ButtonSize {}
  
  @Retention(RetentionPolicy.SOURCE)
  public @interface ColorScheme {}
}
