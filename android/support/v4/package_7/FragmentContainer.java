package android.support.v4.package_7;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

public abstract class FragmentContainer
{
  public FragmentContainer() {}
  
  public Fragment instantiate(Context paramContext, String paramString, Bundle paramBundle)
  {
    return Fragment.instantiate(paramContext, paramString, paramBundle);
  }
  
  public abstract View onFindViewById(int paramInt);
  
  public abstract boolean onHasView();
}
