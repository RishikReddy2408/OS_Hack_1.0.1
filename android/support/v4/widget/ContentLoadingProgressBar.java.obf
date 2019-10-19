package android.support.v4.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ProgressBar;

public class ContentLoadingProgressBar
  extends ProgressBar
{
  private static final int MIN_DELAY = 500;
  private static final int MIN_SHOW_TIME = 500;
  private final Runnable mDelayedHide = new Runnable()
  {
    public void run()
    {
      mPostedHide = false;
      mStartTime = -1L;
      setVisibility(8);
    }
  };
  private final Runnable mDelayedShow = new Runnable()
  {
    public void run()
    {
      mPostedShow = false;
      if (!mDismissed)
      {
        mStartTime = System.currentTimeMillis();
        setVisibility(0);
      }
    }
  };
  boolean mDismissed = false;
  boolean mPostedHide = false;
  boolean mPostedShow = false;
  long mStartTime = -1L;
  
  public ContentLoadingProgressBar(@NonNull Context paramContext)
  {
    this(paramContext, null);
  }
  
  public ContentLoadingProgressBar(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet, 0);
  }
  
  private void removeCallbacks()
  {
    removeCallbacks(mDelayedHide);
    removeCallbacks(mDelayedShow);
  }
  
  public void hide()
  {
    try
    {
      mDismissed = true;
      removeCallbacks(mDelayedShow);
      mPostedShow = false;
      long l = System.currentTimeMillis() - mStartTime;
      if ((l < 500L) && (mStartTime != -1L))
      {
        if (!mPostedHide)
        {
          postDelayed(mDelayedHide, 500L - l);
          mPostedHide = true;
        }
      }
      else {
        setVisibility(8);
      }
      return;
    }
    finally {}
  }
  
  public void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    removeCallbacks();
  }
  
  public void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    removeCallbacks();
  }
  
  public void show()
  {
    try
    {
      mStartTime = -1L;
      mDismissed = false;
      removeCallbacks(mDelayedHide);
      mPostedHide = false;
      if (!mPostedShow)
      {
        postDelayed(mDelayedShow, 500L);
        mPostedShow = true;
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
}
