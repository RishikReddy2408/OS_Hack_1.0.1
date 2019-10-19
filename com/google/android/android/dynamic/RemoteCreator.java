package com.google.android.android.dynamic;

import android.content.Context;
import android.os.IBinder;
import com.google.android.android.common.GooglePlayServicesUtilLight;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public abstract class RemoteCreator<T>
{
  private final String zzia;
  private T zzib;
  
  protected RemoteCreator(String paramString)
  {
    zzia = paramString;
  }
  
  protected abstract Object getRemoteCreator(IBinder paramIBinder);
  
  protected final Object getRemoteCreatorInstance(Context paramContext)
    throws RemoteCreator.RemoteCreatorException
  {
    if (zzib == null)
    {
      Preconditions.checkNotNull(paramContext);
      paramContext = GooglePlayServicesUtilLight.getRemoteContext(paramContext);
      if (paramContext != null)
      {
        paramContext = paramContext.getClassLoader();
        String str = zzia;
        try
        {
          paramContext = paramContext.loadClass(str).newInstance();
          paramContext = (IBinder)paramContext;
          paramContext = getRemoteCreator(paramContext);
          zzib = paramContext;
        }
        catch (IllegalAccessException paramContext)
        {
          throw new RemoteCreatorException("Could not access creator.", paramContext);
        }
        catch (InstantiationException paramContext)
        {
          throw new RemoteCreatorException("Could not instantiate creator.", paramContext);
        }
        catch (ClassNotFoundException paramContext)
        {
          throw new RemoteCreatorException("Could not load creator class.", paramContext);
        }
      }
      else
      {
        throw new RemoteCreatorException("Could not get remote context.");
      }
    }
    return zzib;
  }
  
  @KeepForSdk
  public class RemoteCreatorException
    extends Exception
  {
    public RemoteCreatorException()
    {
      super();
    }
    
    public RemoteCreatorException(Throwable paramThrowable)
    {
      super(paramThrowable);
    }
  }
}
