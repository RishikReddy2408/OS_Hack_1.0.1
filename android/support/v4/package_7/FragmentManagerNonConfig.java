package android.support.v4.package_7;

import android.arch.lifecycle.ViewModelStore;
import android.support.v4.app.Fragment;
import java.util.List;

public class FragmentManagerNonConfig
{
  private final List<android.support.v4.app.FragmentManagerNonConfig> mChildNonConfigs;
  private final List<Fragment> mFragments;
  private final List<ViewModelStore> mViewModelStores;
  
  FragmentManagerNonConfig(List paramList1, List paramList2, List paramList3)
  {
    mFragments = paramList1;
    mChildNonConfigs = paramList2;
    mViewModelStores = paramList3;
  }
  
  List getChildNonConfigs()
  {
    return mChildNonConfigs;
  }
  
  List getFragments()
  {
    return mFragments;
  }
  
  List getViewModelStores()
  {
    return mViewModelStores;
  }
}
