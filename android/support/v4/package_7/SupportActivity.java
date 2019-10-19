package android.support.v4.package_7;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Lifecycle.State;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.ReportFragment;
import android.os.Bundle;
import android.support.annotation.RestrictTo;
import android.support.v4.util.SimpleArrayMap;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class SupportActivity
  extends Activity
  implements LifecycleOwner
{
  private SimpleArrayMap<Class<? extends android.support.v4.app.SupportActivity.ExtraData>, android.support.v4.app.SupportActivity.ExtraData> mExtraDataMap = new SimpleArrayMap();
  private LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);
  
  public SupportActivity() {}
  
  public ExtraData getExtraData(Class paramClass)
  {
    return (ExtraData)mExtraDataMap.get(paramClass);
  }
  
  public Lifecycle getLifecycle()
  {
    return mLifecycleRegistry;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    ReportFragment.injectIfNeededIn(this);
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    mLifecycleRegistry.markState(Lifecycle.State.CREATED);
    super.onSaveInstanceState(paramBundle);
  }
  
  public void putExtraData(ExtraData paramExtraData)
  {
    mExtraDataMap.put(paramExtraData.getClass(), paramExtraData);
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public class ExtraData
  {
    public ExtraData() {}
  }
}
