package com.google.android.android.common.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.view.View;
import com.google.android.android.dynamic.IObjectWrapper;
import com.google.android.android.dynamic.ObjectWrapper;
import com.google.android.android.dynamic.RemoteCreator.RemoteCreatorException;

public final class SignInButtonCreator
  extends com.google.android.gms.dynamic.RemoteCreator<com.google.android.gms.common.internal.ISignInButtonCreator>
{
  private static final SignInButtonCreator zape = new SignInButtonCreator();
  
  private SignInButtonCreator()
  {
    super("com.google.android.gms.common.ui.SignInButtonCreatorImpl");
  }
  
  public static View createView(Context paramContext, int paramInt1, int paramInt2)
    throws RemoteCreator.RemoteCreatorException
  {
    return zape.get(paramContext, paramInt1, paramInt2);
  }
  
  private final View get(Context paramContext, int paramInt1, int paramInt2)
    throws RemoteCreator.RemoteCreatorException
  {
    try
    {
      localObject = new SignInButtonConfig(paramInt1, paramInt2, null);
      IObjectWrapper localIObjectWrapper = ObjectWrapper.wrap(paramContext);
      paramContext = getRemoteCreatorInstance(paramContext);
      paramContext = (ISignInButtonCreator)paramContext;
      paramContext = ObjectWrapper.unwrap(paramContext.newSignInButtonFromConfig(localIObjectWrapper, (SignInButtonConfig)localObject));
      return (View)paramContext;
    }
    catch (Exception paramContext)
    {
      Object localObject = new StringBuilder(64);
      ((StringBuilder)localObject).append("Could not get button with size ");
      ((StringBuilder)localObject).append(paramInt1);
      ((StringBuilder)localObject).append(" and color ");
      ((StringBuilder)localObject).append(paramInt2);
      throw new RemoteCreator.RemoteCreatorException(((StringBuilder)localObject).toString(), paramContext);
    }
  }
  
  public final ISignInButtonCreator getRemoteCreator(IBinder paramIBinder)
  {
    if (paramIBinder == null) {
      return null;
    }
    IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.common.internal.ISignInButtonCreator");
    if ((localIInterface instanceof ISignInButtonCreator)) {
      return (ISignInButtonCreator)localIInterface;
    }
    return new RealmObject(paramIBinder);
  }
}
