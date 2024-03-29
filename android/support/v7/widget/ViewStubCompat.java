package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.annotation.RestrictTo;
import android.support.v7.appcompat.R.styleable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import java.lang.ref.WeakReference;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public final class ViewStubCompat
  extends View
{
  private OnInflateListener mInflateListener;
  private int mInflatedId;
  private WeakReference<View> mInflatedViewRef;
  private LayoutInflater mInflater;
  private int mLayoutResource = 0;
  
  public ViewStubCompat(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public ViewStubCompat(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ViewStubCompat, paramInt, 0);
    mInflatedId = paramContext.getResourceId(R.styleable.ViewStubCompat_android_inflatedId, -1);
    mLayoutResource = paramContext.getResourceId(R.styleable.ViewStubCompat_android_layout, 0);
    setId(paramContext.getResourceId(R.styleable.ViewStubCompat_android_id, -1));
    paramContext.recycle();
    setVisibility(8);
    setWillNotDraw(true);
  }
  
  protected void dispatchDraw(Canvas paramCanvas) {}
  
  public void draw(Canvas paramCanvas) {}
  
  public int getInflatedId()
  {
    return mInflatedId;
  }
  
  public LayoutInflater getLayoutInflater()
  {
    return mInflater;
  }
  
  public int getLayoutResource()
  {
    return mLayoutResource;
  }
  
  public View inflate()
  {
    Object localObject = getParent();
    if ((localObject != null) && ((localObject instanceof ViewGroup)))
    {
      if (mLayoutResource != 0)
      {
        ViewGroup localViewGroup = (ViewGroup)localObject;
        if (mInflater != null) {
          localObject = mInflater;
        } else {
          localObject = LayoutInflater.from(getContext());
        }
        localObject = ((LayoutInflater)localObject).inflate(mLayoutResource, localViewGroup, false);
        if (mInflatedId != -1) {
          ((View)localObject).setId(mInflatedId);
        }
        int i = localViewGroup.indexOfChild(this);
        localViewGroup.removeViewInLayout(this);
        ViewGroup.LayoutParams localLayoutParams = getLayoutParams();
        if (localLayoutParams != null) {
          localViewGroup.addView((View)localObject, i, localLayoutParams);
        } else {
          localViewGroup.addView((View)localObject, i);
        }
        mInflatedViewRef = new WeakReference(localObject);
        if (mInflateListener != null)
        {
          mInflateListener.onInflate(this, (View)localObject);
          return localObject;
        }
      }
      else
      {
        throw new IllegalArgumentException("ViewStub must have a valid layoutResource");
      }
    }
    else {
      throw new IllegalStateException("ViewStub must have a non-null ViewGroup viewParent");
    }
    return localObject;
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    setMeasuredDimension(0, 0);
  }
  
  public void setInflatedId(int paramInt)
  {
    mInflatedId = paramInt;
  }
  
  public void setLayoutInflater(LayoutInflater paramLayoutInflater)
  {
    mInflater = paramLayoutInflater;
  }
  
  public void setLayoutResource(int paramInt)
  {
    mLayoutResource = paramInt;
  }
  
  public void setOnInflateListener(OnInflateListener paramOnInflateListener)
  {
    mInflateListener = paramOnInflateListener;
  }
  
  public void setVisibility(int paramInt)
  {
    if (mInflatedViewRef != null)
    {
      View localView = (View)mInflatedViewRef.get();
      if (localView != null)
      {
        localView.setVisibility(paramInt);
        return;
      }
      throw new IllegalStateException("setVisibility called on un-referenced view");
    }
    super.setVisibility(paramInt);
    if ((paramInt == 0) || (paramInt == 4)) {
      inflate();
    }
  }
  
  public static abstract interface OnInflateListener
  {
    public abstract void onInflate(ViewStubCompat paramViewStubCompat, View paramView);
  }
}
