package android.arch.lifecycle;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class ViewModelStore
{
  private final HashMap<String, ViewModel> mMap = new HashMap();
  
  public ViewModelStore() {}
  
  final void addChild(String paramString, ViewModel paramViewModel)
  {
    ViewModel localViewModel = (ViewModel)mMap.get(paramString);
    if (localViewModel != null) {
      localViewModel.onCleared();
    }
    mMap.put(paramString, paramViewModel);
  }
  
  public final void clear()
  {
    Iterator localIterator = mMap.values().iterator();
    while (localIterator.hasNext()) {
      ((ViewModel)localIterator.next()).onCleared();
    }
    mMap.clear();
  }
  
  final ViewModel getObject(String paramString)
  {
    return (ViewModel)mMap.get(paramString);
  }
}
