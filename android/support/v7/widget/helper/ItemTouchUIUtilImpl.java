package android.support.v7.widget.helper;

import android.graphics.Canvas;
import android.support.v4.view.ViewCompat;
import android.support.v7.recyclerview.R.id;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

class ItemTouchUIUtilImpl
{
  ItemTouchUIUtilImpl() {}
  
  static class Api21Impl
    extends ItemTouchUIUtilImpl.BaseImpl
  {
    Api21Impl() {}
    
    private float findMaxElevation(RecyclerView paramRecyclerView, View paramView)
    {
      int j = paramRecyclerView.getChildCount();
      float f1 = 0.0F;
      int i = 0;
      while (i < j)
      {
        View localView = paramRecyclerView.getChildAt(i);
        float f2;
        if (localView == paramView)
        {
          f2 = f1;
        }
        else
        {
          float f3 = ViewCompat.getElevation(localView);
          f2 = f1;
          if (f3 > f1) {
            f2 = f3;
          }
        }
        i += 1;
        f1 = f2;
      }
      return f1;
    }
    
    public void clearView(View paramView)
    {
      Object localObject = paramView.getTag(R.id.item_touch_helper_previous_elevation);
      if ((localObject != null) && ((localObject instanceof Float))) {
        ViewCompat.setElevation(paramView, ((Float)localObject).floatValue());
      }
      paramView.setTag(R.id.item_touch_helper_previous_elevation, null);
      super.clearView(paramView);
    }
    
    public void onDraw(Canvas paramCanvas, RecyclerView paramRecyclerView, View paramView, float paramFloat1, float paramFloat2, int paramInt, boolean paramBoolean)
    {
      if ((paramBoolean) && (paramView.getTag(R.id.item_touch_helper_previous_elevation) == null))
      {
        float f = ViewCompat.getElevation(paramView);
        ViewCompat.setElevation(paramView, findMaxElevation(paramRecyclerView, paramView) + 1.0F);
        paramView.setTag(R.id.item_touch_helper_previous_elevation, Float.valueOf(f));
      }
      super.onDraw(paramCanvas, paramRecyclerView, paramView, paramFloat1, paramFloat2, paramInt, paramBoolean);
    }
  }
  
  static class BaseImpl
    implements ItemTouchUIUtil
  {
    BaseImpl() {}
    
    public void clearView(View paramView)
    {
      paramView.setTranslationX(0.0F);
      paramView.setTranslationY(0.0F);
    }
    
    public void onDraw(Canvas paramCanvas, RecyclerView paramRecyclerView, View paramView, float paramFloat1, float paramFloat2, int paramInt, boolean paramBoolean)
    {
      paramView.setTranslationX(paramFloat1);
      paramView.setTranslationY(paramFloat2);
    }
    
    public void onDrawOver(Canvas paramCanvas, RecyclerView paramRecyclerView, View paramView, float paramFloat1, float paramFloat2, int paramInt, boolean paramBoolean) {}
    
    public void onSelected(View paramView) {}
  }
}
