package com.google.android.android.internal.base;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;

final class DrawableWrapperDonut
  extends Drawable
{
  private static final DrawableWrapperDonut zant = new DrawableWrapperDonut();
  private static final DrawableWrapperDonut.DrawableWrapperState zanu = new DrawableWrapperDonut.DrawableWrapperState(null);
  
  private DrawableWrapperDonut() {}
  
  public final void draw(Canvas paramCanvas) {}
  
  public final Drawable.ConstantState getConstantState()
  {
    return zanu;
  }
  
  public final int getOpacity()
  {
    return -2;
  }
  
  public final void setAlpha(int paramInt) {}
  
  public final void setColorFilter(ColorFilter paramColorFilter) {}
}
