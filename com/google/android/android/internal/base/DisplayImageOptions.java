package com.google.android.android.internal.base;

import android.graphics.drawable.Drawable;
import android.support.v4.util.LruCache;

public final class DisplayImageOptions
  extends LruCache<Object, Drawable>
{
  public DisplayImageOptions()
  {
    super(10);
  }
}
