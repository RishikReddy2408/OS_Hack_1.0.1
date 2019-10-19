package com.google.android.android.common.images;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.google.android.android.common.internal.Asserts;
import com.google.android.android.internal.base.DisplayImageOptions;

public abstract class ImageLoader
{
  final Job zamu;
  private int zamv = 0;
  protected int zamw = 0;
  private boolean zamx = false;
  private boolean zamy = true;
  private boolean zamz = false;
  private boolean zana = true;
  
  public ImageLoader(Uri paramUri, int paramInt)
  {
    zamu = new Job(paramUri);
    zamw = paramInt;
  }
  
  protected final boolean get(boolean paramBoolean1, boolean paramBoolean2)
  {
    return (zamy) && (!paramBoolean2) && (!paramBoolean1);
  }
  
  final void load(Context paramContext, Bitmap paramBitmap, boolean paramBoolean)
  {
    Asserts.checkNotNull(paramBitmap);
    setImage(new BitmapDrawable(paramContext.getResources(), paramBitmap), paramBoolean, false, true);
  }
  
  final void loadImage(Context paramContext, DisplayImageOptions paramDisplayImageOptions)
  {
    if (zana) {
      setImage(null, false, true, false);
    }
  }
  
  final void loadImage(Context paramContext, DisplayImageOptions paramDisplayImageOptions, boolean paramBoolean)
  {
    if (zamw != 0)
    {
      int i = zamw;
      paramContext = paramContext.getResources().getDrawable(i);
    }
    else
    {
      paramContext = null;
    }
    setImage(paramContext, paramBoolean, false, false);
  }
  
  protected abstract void setImage(Drawable paramDrawable, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3);
}
