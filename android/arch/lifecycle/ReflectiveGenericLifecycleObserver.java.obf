package android.arch.lifecycle;

class ReflectiveGenericLifecycleObserver
  implements GenericLifecycleObserver
{
  private final ClassesInfoCache.CallbackInfo mInfo;
  private final Object mWrapped;
  
  ReflectiveGenericLifecycleObserver(Object paramObject)
  {
    mWrapped = paramObject;
    mInfo = ClassesInfoCache.sInstance.getInfo(mWrapped.getClass());
  }
  
  public void onStateChanged(LifecycleOwner paramLifecycleOwner, Lifecycle.Event paramEvent)
  {
    mInfo.invokeCallbacks(paramLifecycleOwner, paramEvent, mWrapped);
  }
}
