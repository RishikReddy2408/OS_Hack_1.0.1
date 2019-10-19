package android.support.v4.package_7;

import android.app.AppOpsManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.provider.Settings.Secure;
import android.support.annotation.GuardedBy;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class NotificationManagerCompat
{
  public static final String ACTION_BIND_SIDE_CHANNEL = "android.support.BIND_NOTIFICATION_SIDE_CHANNEL";
  private static final String CHECK_OP_NO_THROW = "checkOpNoThrow";
  public static final String EXTRA_USE_SIDE_CHANNEL = "android.support.useSideChannel";
  public static final int IMPORTANCE_DEFAULT = 3;
  public static final int IMPORTANCE_HIGH = 4;
  public static final int IMPORTANCE_LOW = 2;
  public static final int IMPORTANCE_MAX = 5;
  public static final int IMPORTANCE_MIN = 1;
  public static final int IMPORTANCE_NONE = 0;
  public static final int IMPORTANCE_UNSPECIFIED = -1000;
  static final int MAX_SIDE_CHANNEL_SDK_VERSION = 19;
  private static final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";
  private static final String SETTING_ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";
  private static final int SIDE_CHANNEL_RETRY_BASE_INTERVAL_MS = 1000;
  private static final int SIDE_CHANNEL_RETRY_MAX_COUNT = 6;
  private static final String TAG = "NotifManCompat";
  @GuardedBy("sEnabledNotificationListenersLock")
  private static Set<String> sEnabledNotificationListenerPackages = new HashSet();
  @GuardedBy("sEnabledNotificationListenersLock")
  private static String sEnabledNotificationListeners;
  private static final Object sEnabledNotificationListenersLock = new Object();
  private static final Object sLock = new Object();
  @GuardedBy("sLock")
  private static SideChannelManager sSideChannelManager;
  private final Context mContext;
  private final NotificationManager mNotificationManager;
  
  private NotificationManagerCompat(Context paramContext)
  {
    mContext = paramContext;
    mNotificationManager = ((NotificationManager)mContext.getSystemService("notification"));
  }
  
  public static NotificationManagerCompat from(Context paramContext)
  {
    return new NotificationManagerCompat(paramContext);
  }
  
  public static Set getEnabledListenerPackages(Context paramContext)
  {
    Object localObject = Settings.Secure.getString(paramContext.getContentResolver(), "enabled_notification_listeners");
    paramContext = sEnabledNotificationListenersLock;
    if (localObject != null) {}
    for (;;)
    {
      try
      {
        if (!((String)localObject).equals(sEnabledNotificationListeners))
        {
          String[] arrayOfString = ((String)localObject).split(":");
          HashSet localHashSet = new HashSet(arrayOfString.length);
          int j = arrayOfString.length;
          i = 0;
          if (i < j)
          {
            ComponentName localComponentName = ComponentName.unflattenFromString(arrayOfString[i]);
            if (localComponentName == null) {
              continue;
            }
            localHashSet.add(localComponentName.getPackageName());
            continue;
          }
          sEnabledNotificationListenerPackages = localHashSet;
          sEnabledNotificationListeners = (String)localObject;
        }
        localObject = sEnabledNotificationListenerPackages;
        return localObject;
      }
      catch (Throwable localThrowable)
      {
        int i;
        continue;
      }
      throw ((Throwable)localObject);
      i += 1;
    }
  }
  
  private void pushSideChannelQueue(Task paramTask)
  {
    Object localObject = sLock;
    try
    {
      if (sSideChannelManager == null) {
        sSideChannelManager = new SideChannelManager(mContext.getApplicationContext());
      }
      sSideChannelManager.queueTask(paramTask);
      return;
    }
    catch (Throwable paramTask)
    {
      throw paramTask;
    }
  }
  
  private static boolean useSideChannelForNotification(Notification paramNotification)
  {
    paramNotification = NotificationCompat.getExtras(paramNotification);
    return (paramNotification != null) && (paramNotification.getBoolean("android.support.useSideChannel"));
  }
  
  public boolean areNotificationsEnabled()
  {
    if (Build.VERSION.SDK_INT >= 24) {
      return mNotificationManager.areNotificationsEnabled();
    }
    Object localObject1;
    Object localObject2;
    String str;
    int i;
    if (Build.VERSION.SDK_INT >= 19)
    {
      localObject1 = (AppOpsManager)mContext.getSystemService("appops");
      localObject2 = mContext.getApplicationInfo();
      str = mContext.getApplicationContext().getPackageName();
      i = uid;
    }
    try
    {
      localObject2 = Class.forName(AppOpsManager.class.getName());
      Object localObject3 = Integer.TYPE;
      Class localClass = Integer.TYPE;
      localObject3 = ((Class)localObject2).getMethod("checkOpNoThrow", new Class[] { localObject3, localClass, String.class });
      localObject2 = ((Class)localObject2).getDeclaredField("OP_POST_NOTIFICATION").get(Integer.class);
      localObject2 = (Integer)localObject2;
      int j = ((Integer)localObject2).intValue();
      localObject1 = ((Method)localObject3).invoke(localObject1, new Object[] { Integer.valueOf(j), Integer.valueOf(i), str });
      localObject1 = (Integer)localObject1;
      i = ((Integer)localObject1).intValue();
      return i == 0;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      return true;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      return true;
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      return true;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      return true;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      return true;
    }
    catch (RuntimeException localRuntimeException) {}
    return true;
    return true;
  }
  
  public void cancel(int paramInt)
  {
    cancel(null, paramInt);
  }
  
  public void cancel(String paramString, int paramInt)
  {
    mNotificationManager.cancel(paramString, paramInt);
    if (Build.VERSION.SDK_INT <= 19) {
      pushSideChannelQueue(new CancelTask(mContext.getPackageName(), paramInt, paramString));
    }
  }
  
  public void cancelAll()
  {
    mNotificationManager.cancelAll();
    if (Build.VERSION.SDK_INT <= 19) {
      pushSideChannelQueue(new CancelTask(mContext.getPackageName()));
    }
  }
  
  public int getImportance()
  {
    if (Build.VERSION.SDK_INT >= 24) {
      return mNotificationManager.getImportance();
    }
    return 64536;
  }
  
  public void notify(int paramInt, Notification paramNotification)
  {
    notify(null, paramInt, paramNotification);
  }
  
  public void notify(String paramString, int paramInt, Notification paramNotification)
  {
    if (useSideChannelForNotification(paramNotification))
    {
      pushSideChannelQueue(new NotifyTask(mContext.getPackageName(), paramInt, paramString, paramNotification));
      mNotificationManager.cancel(paramString, paramInt);
      return;
    }
    mNotificationManager.notify(paramString, paramInt, paramNotification);
  }
  
  class CancelTask
    implements NotificationManagerCompat.Task
  {
    final boolean all;
    final int id;
    final String tag;
    
    CancelTask()
    {
      id = 0;
      tag = null;
      all = true;
    }
    
    CancelTask(int paramInt, String paramString)
    {
      id = paramInt;
      tag = paramString;
      all = false;
    }
    
    public void send(INotificationSideChannel paramINotificationSideChannel)
      throws RemoteException
    {
      if (all)
      {
        paramINotificationSideChannel.cancelAll(NotificationManagerCompat.this);
        return;
      }
      paramINotificationSideChannel.cancel(NotificationManagerCompat.this, id, tag);
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder("CancelTask[");
      localStringBuilder.append("packageName:");
      localStringBuilder.append(NotificationManagerCompat.this);
      localStringBuilder.append(", id:");
      localStringBuilder.append(id);
      localStringBuilder.append(", tag:");
      localStringBuilder.append(tag);
      localStringBuilder.append(", all:");
      localStringBuilder.append(all);
      localStringBuilder.append("]");
      return localStringBuilder.toString();
    }
  }
  
  class NotifyTask
    implements NotificationManagerCompat.Task
  {
    final int id;
    final Notification notif;
    final String tag;
    
    NotifyTask(int paramInt, String paramString, Notification paramNotification)
    {
      id = paramInt;
      tag = paramString;
      notif = paramNotification;
    }
    
    public void send(INotificationSideChannel paramINotificationSideChannel)
      throws RemoteException
    {
      paramINotificationSideChannel.notify(NotificationManagerCompat.this, id, tag, notif);
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder("NotifyTask[");
      localStringBuilder.append("packageName:");
      localStringBuilder.append(NotificationManagerCompat.this);
      localStringBuilder.append(", id:");
      localStringBuilder.append(id);
      localStringBuilder.append(", tag:");
      localStringBuilder.append(tag);
      localStringBuilder.append("]");
      return localStringBuilder.toString();
    }
  }
  
  class ServiceConnectedEvent
  {
    final IBinder iBinder;
    
    ServiceConnectedEvent(IBinder paramIBinder)
    {
      iBinder = paramIBinder;
    }
  }
  
  class SideChannelManager
    implements Handler.Callback, ServiceConnection
  {
    private static final int MSG_QUEUE_TASK = 0;
    private static final int MSG_RETRY_LISTENER_QUEUE = 3;
    private static final int MSG_SERVICE_CONNECTED = 1;
    private static final int MSG_SERVICE_DISCONNECTED = 2;
    private Set<String> mCachedEnabledPackages = new HashSet();
    private final Handler mHandler;
    private final HandlerThread mHandlerThread = new HandlerThread("NotificationManagerCompat");
    private final Map<ComponentName, android.support.v4.app.NotificationManagerCompat.SideChannelManager.ListenerRecord> mRecordMap = new HashMap();
    
    SideChannelManager()
    {
      mHandlerThread.start();
      mHandler = new Handler(mHandlerThread.getLooper(), this);
    }
    
    private boolean ensureServiceBound(ListenerRecord paramListenerRecord)
    {
      if (bound) {
        return true;
      }
      Object localObject = new Intent("android.support.BIND_NOTIFICATION_SIDE_CHANNEL").setComponent(componentName);
      bound = bindService((Intent)localObject, this, 33);
      if (bound)
      {
        retryCount = 0;
      }
      else
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Unable to bind to listener ");
        ((StringBuilder)localObject).append(componentName);
        Log.w("NotifManCompat", ((StringBuilder)localObject).toString());
        unbindService(this);
      }
      return bound;
    }
    
    private void ensureServiceUnbound(ListenerRecord paramListenerRecord)
    {
      if (bound)
      {
        unbindService(this);
        bound = false;
      }
      service = null;
    }
    
    private void handleQueueTask(NotificationManagerCompat.Task paramTask)
    {
      updateListenerMap();
      Iterator localIterator = mRecordMap.values().iterator();
      while (localIterator.hasNext())
      {
        ListenerRecord localListenerRecord = (ListenerRecord)localIterator.next();
        taskQueue.add(paramTask);
        processListenerQueue(localListenerRecord);
      }
    }
    
    private void handleRetryListenerQueue(ComponentName paramComponentName)
    {
      paramComponentName = (ListenerRecord)mRecordMap.get(paramComponentName);
      if (paramComponentName != null) {
        processListenerQueue(paramComponentName);
      }
    }
    
    private void handleServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      paramComponentName = (ListenerRecord)mRecordMap.get(paramComponentName);
      if (paramComponentName != null)
      {
        service = INotificationSideChannel.Stub.asInterface(paramIBinder);
        retryCount = 0;
        processListenerQueue(paramComponentName);
      }
    }
    
    private void handleServiceDisconnected(ComponentName paramComponentName)
    {
      paramComponentName = (ListenerRecord)mRecordMap.get(paramComponentName);
      if (paramComponentName != null) {
        ensureServiceUnbound(paramComponentName);
      }
    }
    
    private void processListenerQueue(ListenerRecord paramListenerRecord)
    {
      Object localObject1;
      if (Log.isLoggable("NotifManCompat", 3))
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("Processing component ");
        ((StringBuilder)localObject1).append(componentName);
        ((StringBuilder)localObject1).append(", ");
        ((StringBuilder)localObject1).append(taskQueue.size());
        ((StringBuilder)localObject1).append(" queued tasks");
        Log.d("NotifManCompat", ((StringBuilder)localObject1).toString());
      }
      if (taskQueue.isEmpty()) {
        return;
      }
      if ((ensureServiceBound(paramListenerRecord)) && (service != null)) {}
      for (;;)
      {
        localObject1 = (NotificationManagerCompat.Task)taskQueue.peek();
        if (localObject1 != null) {}
        try
        {
          boolean bool = Log.isLoggable("NotifManCompat", 3);
          if (bool)
          {
            localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append("Sending task ");
            ((StringBuilder)localObject2).append(localObject1);
            Log.d("NotifManCompat", ((StringBuilder)localObject2).toString());
          }
          localObject2 = service;
          ((NotificationManagerCompat.Task)localObject1).send((INotificationSideChannel)localObject2);
          localObject1 = taskQueue;
          ((ArrayDeque)localObject1).remove();
        }
        catch (RemoteException localRemoteException)
        {
          Object localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append("RemoteException communicating with ");
          ((StringBuilder)localObject2).append(componentName);
          Log.w("NotifManCompat", ((StringBuilder)localObject2).toString(), localRemoteException);
          break label275;
          if (Log.isLoggable("NotifManCompat", 3))
          {
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("Remote service has died: ");
            localStringBuilder.append(componentName);
            Log.d("NotifManCompat", localStringBuilder.toString());
          }
          if (!taskQueue.isEmpty())
          {
            scheduleListenerRetry(paramListenerRecord);
            return;
            scheduleListenerRetry(paramListenerRecord);
            return;
          }
        }
        catch (DeadObjectException localDeadObjectException)
        {
          label275:
          for (;;) {}
        }
      }
    }
    
    private void scheduleListenerRetry(ListenerRecord paramListenerRecord)
    {
      if (mHandler.hasMessages(3, componentName)) {
        return;
      }
      retryCount += 1;
      StringBuilder localStringBuilder;
      if (retryCount > 6)
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("Giving up on delivering ");
        localStringBuilder.append(taskQueue.size());
        localStringBuilder.append(" tasks to ");
        localStringBuilder.append(componentName);
        localStringBuilder.append(" after ");
        localStringBuilder.append(retryCount);
        localStringBuilder.append(" retries");
        Log.w("NotifManCompat", localStringBuilder.toString());
        taskQueue.clear();
        return;
      }
      int i = (1 << retryCount - 1) * 1000;
      if (Log.isLoggable("NotifManCompat", 3))
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("Scheduling retry for ");
        localStringBuilder.append(i);
        localStringBuilder.append(" ms");
        Log.d("NotifManCompat", localStringBuilder.toString());
      }
      paramListenerRecord = mHandler.obtainMessage(3, componentName);
      mHandler.sendMessageDelayed(paramListenerRecord, i);
    }
    
    private void updateListenerMap()
    {
      Object localObject1 = NotificationManagerCompat.getEnabledListenerPackages(NotificationManagerCompat.this);
      if (((Set)localObject1).equals(mCachedEnabledPackages)) {
        return;
      }
      mCachedEnabledPackages = ((Set)localObject1);
      Object localObject2 = getPackageManager().queryIntentServices(new Intent().setAction("android.support.BIND_NOTIFICATION_SIDE_CHANNEL"), 0);
      HashSet localHashSet = new HashSet();
      localObject2 = ((List)localObject2).iterator();
      Object localObject3;
      while (((Iterator)localObject2).hasNext())
      {
        Object localObject4 = (ResolveInfo)((Iterator)localObject2).next();
        if (((Set)localObject1).contains(serviceInfo.packageName))
        {
          localObject3 = new ComponentName(serviceInfo.packageName, serviceInfo.name);
          if (serviceInfo.permission != null)
          {
            localObject4 = new StringBuilder();
            ((StringBuilder)localObject4).append("Permission present on component ");
            ((StringBuilder)localObject4).append(localObject3);
            ((StringBuilder)localObject4).append(", not adding listener record.");
            Log.w("NotifManCompat", ((StringBuilder)localObject4).toString());
          }
          else
          {
            localHashSet.add(localObject3);
          }
        }
      }
      localObject1 = localHashSet.iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (ComponentName)((Iterator)localObject1).next();
        if (!mRecordMap.containsKey(localObject2))
        {
          if (Log.isLoggable("NotifManCompat", 3))
          {
            localObject3 = new StringBuilder();
            ((StringBuilder)localObject3).append("Adding listener record for ");
            ((StringBuilder)localObject3).append(localObject2);
            Log.d("NotifManCompat", ((StringBuilder)localObject3).toString());
          }
          mRecordMap.put(localObject2, new ListenerRecord((ComponentName)localObject2));
        }
      }
      localObject1 = mRecordMap.entrySet().iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (Map.Entry)((Iterator)localObject1).next();
        if (!localHashSet.contains(((Map.Entry)localObject2).getKey()))
        {
          if (Log.isLoggable("NotifManCompat", 3))
          {
            localObject3 = new StringBuilder();
            ((StringBuilder)localObject3).append("Removing listener record for ");
            ((StringBuilder)localObject3).append(((Map.Entry)localObject2).getKey());
            Log.d("NotifManCompat", ((StringBuilder)localObject3).toString());
          }
          ensureServiceUnbound((ListenerRecord)((Map.Entry)localObject2).getValue());
          ((Iterator)localObject1).remove();
        }
      }
    }
    
    public boolean handleMessage(Message paramMessage)
    {
      switch (what)
      {
      default: 
        return false;
      case 3: 
        handleRetryListenerQueue((ComponentName)obj);
        return true;
      case 2: 
        handleServiceDisconnected((ComponentName)obj);
        return true;
      case 1: 
        paramMessage = (NotificationManagerCompat.ServiceConnectedEvent)obj;
        handleServiceConnected(componentName, iBinder);
        return true;
      }
      handleQueueTask((NotificationManagerCompat.Task)obj);
      return true;
    }
    
    public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      if (Log.isLoggable("NotifManCompat", 3))
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Connected to service ");
        localStringBuilder.append(paramComponentName);
        Log.d("NotifManCompat", localStringBuilder.toString());
      }
      mHandler.obtainMessage(1, new NotificationManagerCompat.ServiceConnectedEvent(paramComponentName, paramIBinder)).sendToTarget();
    }
    
    public void onServiceDisconnected(ComponentName paramComponentName)
    {
      if (Log.isLoggable("NotifManCompat", 3))
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Disconnected from service ");
        localStringBuilder.append(paramComponentName);
        Log.d("NotifManCompat", localStringBuilder.toString());
      }
      mHandler.obtainMessage(2, paramComponentName).sendToTarget();
    }
    
    public void queueTask(NotificationManagerCompat.Task paramTask)
    {
      mHandler.obtainMessage(0, paramTask).sendToTarget();
    }
    
    class ListenerRecord
    {
      boolean bound = false;
      int retryCount = 0;
      INotificationSideChannel service;
      ArrayDeque<android.support.v4.app.NotificationManagerCompat.Task> taskQueue = new ArrayDeque();
      
      ListenerRecord() {}
    }
  }
  
  abstract interface Task
  {
    public abstract void send(INotificationSideChannel paramINotificationSideChannel)
      throws RemoteException;
  }
}
