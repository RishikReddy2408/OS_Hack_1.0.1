package android.support.v4.widget;

import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

public final class ListViewCompat
{
  private ListViewCompat() {}
  
  public static boolean canScrollList(ListView paramListView, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 19) {
      return paramListView.canScrollList(paramInt);
    }
    int j = paramListView.getChildCount();
    if (j == 0) {
      return false;
    }
    int i = paramListView.getFirstVisiblePosition();
    if (paramInt > 0)
    {
      paramInt = paramListView.getChildAt(j - 1).getBottom();
      if ((i + j < paramListView.getCount()) || (paramInt > paramListView.getHeight() - paramListView.getListPaddingBottom())) {
        return true;
      }
    }
    else
    {
      paramInt = paramListView.getChildAt(0).getTop();
      if ((i > 0) || (paramInt < paramListView.getListPaddingTop())) {
        return true;
      }
    }
    return false;
  }
  
  public static void scrollListBy(ListView paramListView, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 19)
    {
      paramListView.scrollListBy(paramInt);
      return;
    }
    int i = paramListView.getFirstVisiblePosition();
    if (i == -1) {
      return;
    }
    View localView = paramListView.getChildAt(0);
    if (localView == null) {
      return;
    }
    paramListView.setSelectionFromTop(i, localView.getTop() - paramInt);
  }
}
