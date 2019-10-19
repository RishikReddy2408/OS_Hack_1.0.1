package android.arch.lifecycle;

class FullLifecycleObserverAdapter
  implements GenericLifecycleObserver
{
  private final FullLifecycleObserver mObserver;
  
  FullLifecycleObserverAdapter(FullLifecycleObserver paramFullLifecycleObserver)
  {
    mObserver = paramFullLifecycleObserver;
  }
  
  public void onStateChanged(LifecycleOwner paramLifecycleOwner, Lifecycle.Event paramEvent)
  {
    switch (1.$SwitchMap$android$arch$lifecycle$Lifecycle$Event[paramEvent.ordinal()])
    {
    default: 
      return;
    case 7: 
      throw new IllegalArgumentException("ON_ANY must not been send by anybody");
    case 6: 
      mObserver.onDestroy(paramLifecycleOwner);
      return;
    case 5: 
      mObserver.onStop(paramLifecycleOwner);
      return;
    case 4: 
      mObserver.onPause(paramLifecycleOwner);
      return;
    case 3: 
      mObserver.onResume(paramLifecycleOwner);
      return;
    case 2: 
      mObserver.onStart(paramLifecycleOwner);
      return;
    }
    mObserver.onCreate(paramLifecycleOwner);
  }
}
