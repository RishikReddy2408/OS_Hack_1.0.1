package android.arch.lifecycle;

@Deprecated
public abstract interface LifecycleRegistryOwner
  extends LifecycleOwner
{
  public abstract LifecycleRegistry getLifecycle();
}
