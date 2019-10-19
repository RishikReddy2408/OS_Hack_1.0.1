package android.arch.lifecycle;

import android.app.Application;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ViewModelProvider
{
  private static final String DEFAULT_KEY = "android.arch.lifecycle.ViewModelProvider.DefaultKey";
  private final Factory mFactory;
  private final ViewModelStore mViewModelStore;
  
  public ViewModelProvider(ViewModelStore paramViewModelStore, Factory paramFactory)
  {
    mFactory = paramFactory;
    mViewModelStore = paramViewModelStore;
  }
  
  public ViewModelProvider(ViewModelStoreOwner paramViewModelStoreOwner, Factory paramFactory)
  {
    this(paramViewModelStoreOwner.getViewModelStore(), paramFactory);
  }
  
  public ViewModel get(String paramString, Class paramClass)
  {
    ViewModel localViewModel = mViewModelStore.getObject(paramString);
    if (paramClass.isInstance(localViewModel)) {
      return localViewModel;
    }
    paramClass = mFactory.create(paramClass);
    mViewModelStore.addChild(paramString, paramClass);
    return paramClass;
  }
  
  public ViewModel getTag(Class paramClass)
  {
    String str = paramClass.getCanonicalName();
    if (str != null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("android.arch.lifecycle.ViewModelProvider.DefaultKey:");
      localStringBuilder.append(str);
      return get(localStringBuilder.toString(), paramClass);
    }
    throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
  }
  
  public static class AndroidViewModelFactory
    extends ViewModelProvider.NewInstanceFactory
  {
    private static AndroidViewModelFactory sInstance;
    private Application mApplication;
    
    public AndroidViewModelFactory(Application paramApplication)
    {
      mApplication = paramApplication;
    }
    
    public static AndroidViewModelFactory getInstance(Application paramApplication)
    {
      if (sInstance == null) {
        sInstance = new AndroidViewModelFactory(paramApplication);
      }
      return sInstance;
    }
    
    public ViewModel create(Class paramClass)
    {
      if (AndroidViewModel.class.isAssignableFrom(paramClass)) {
        try
        {
          Object localObject1 = paramClass.getConstructor(new Class[] { Application.class });
          localObject2 = mApplication;
          localObject1 = ((Constructor)localObject1).newInstance(new Object[] { localObject2 });
          return (ViewModel)localObject1;
        }
        catch (InvocationTargetException localInvocationTargetException)
        {
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append("Cannot create an instance of ");
          ((StringBuilder)localObject2).append(paramClass);
          throw new RuntimeException(((StringBuilder)localObject2).toString(), localInvocationTargetException);
        }
        catch (InstantiationException localInstantiationException)
        {
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append("Cannot create an instance of ");
          ((StringBuilder)localObject2).append(paramClass);
          throw new RuntimeException(((StringBuilder)localObject2).toString(), localInstantiationException);
        }
        catch (IllegalAccessException localIllegalAccessException)
        {
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append("Cannot create an instance of ");
          ((StringBuilder)localObject2).append(paramClass);
          throw new RuntimeException(((StringBuilder)localObject2).toString(), localIllegalAccessException);
        }
        catch (NoSuchMethodException localNoSuchMethodException)
        {
          Object localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append("Cannot create an instance of ");
          ((StringBuilder)localObject2).append(paramClass);
          throw new RuntimeException(((StringBuilder)localObject2).toString(), localNoSuchMethodException);
        }
      }
      return super.create(paramClass);
    }
  }
  
  public static abstract interface Factory
  {
    public abstract ViewModel create(Class paramClass);
  }
  
  public static class NewInstanceFactory
    implements ViewModelProvider.Factory
  {
    public NewInstanceFactory() {}
    
    public ViewModel create(Class paramClass)
    {
      try
      {
        Object localObject = paramClass.newInstance();
        return (ViewModel)localObject;
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("Cannot create an instance of ");
        localStringBuilder.append(paramClass);
        throw new RuntimeException(localStringBuilder.toString(), localIllegalAccessException);
      }
      catch (InstantiationException localInstantiationException)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Cannot create an instance of ");
        localStringBuilder.append(paramClass);
        throw new RuntimeException(localStringBuilder.toString(), localInstantiationException);
      }
    }
  }
}
