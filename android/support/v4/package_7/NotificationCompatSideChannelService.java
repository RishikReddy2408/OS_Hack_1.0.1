package android.support.v4.package_7;

import android.app.Notification;
import android.app.Service;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.os.RemoteException;

public abstract class NotificationCompatSideChannelService
  extends Service
{
  public NotificationCompatSideChannelService() {}
  
  public abstract void cancel(String paramString1, int paramInt, String paramString2);
  
  public abstract void cancelAll(String paramString);
  
  void checkPermission(int paramInt, String paramString)
  {
    Object localObject = getPackageManager().getPackagesForUid(paramInt);
    int j = localObject.length;
    int i = 0;
    while (i < j)
    {
      if (localObject[i].equals(paramString)) {
        return;
      }
      i += 1;
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("NotificationSideChannelService: Uid ");
    ((StringBuilder)localObject).append(paramInt);
    ((StringBuilder)localObject).append(" is not authorized for package ");
    ((StringBuilder)localObject).append(paramString);
    throw new SecurityException(((StringBuilder)localObject).toString());
  }
  
  public abstract void notify(String paramString1, int paramInt, String paramString2, Notification paramNotification);
  
  public IBinder onBind(Intent paramIntent)
  {
    if (paramIntent.getAction().equals("android.support.BIND_NOTIFICATION_SIDE_CHANNEL"))
    {
      if (Build.VERSION.SDK_INT > 19) {
        return null;
      }
      return new NotificationSideChannelStub();
    }
    return null;
  }
  
  class NotificationSideChannelStub
    extends INotificationSideChannel.Stub
  {
    NotificationSideChannelStub() {}
    
    public void cancel(String paramString1, int paramInt, String paramString2)
      throws RemoteException
    {
      checkPermission(Binder.getCallingUid(), paramString1);
      long l = Binder.clearCallingIdentity();
      try
      {
        NotificationCompatSideChannelService.this.cancel(paramString1, paramInt, paramString2);
        Binder.restoreCallingIdentity(l);
        return;
      }
      catch (Throwable paramString1)
      {
        Binder.restoreCallingIdentity(l);
        throw paramString1;
      }
    }
    
    public void cancelAll(String paramString)
    {
      checkPermission(Binder.getCallingUid(), paramString);
      long l = Binder.clearCallingIdentity();
      try
      {
        NotificationCompatSideChannelService.this.cancelAll(paramString);
        Binder.restoreCallingIdentity(l);
        return;
      }
      catch (Throwable paramString)
      {
        Binder.restoreCallingIdentity(l);
        throw paramString;
      }
    }
    
    public void notify(String paramString1, int paramInt, String paramString2, Notification paramNotification)
      throws RemoteException
    {
      checkPermission(Binder.getCallingUid(), paramString1);
      long l = Binder.clearCallingIdentity();
      try
      {
        NotificationCompatSideChannelService.this.notify(paramString1, paramInt, paramString2, paramNotification);
        Binder.restoreCallingIdentity(l);
        return;
      }
      catch (Throwable paramString1)
      {
        Binder.restoreCallingIdentity(l);
        throw paramString1;
      }
    }
  }
}
