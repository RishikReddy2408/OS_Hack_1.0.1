package android.support.v7.view.menu;

class BaseWrapper<T>
{
  final T mWrappedObject;
  
  BaseWrapper(Object paramObject)
  {
    if (paramObject != null)
    {
      mWrappedObject = paramObject;
      return;
    }
    throw new IllegalArgumentException("Wrapped Object can not be null.");
  }
  
  public Object getWrappedObject()
  {
    return mWrappedObject;
  }
}
