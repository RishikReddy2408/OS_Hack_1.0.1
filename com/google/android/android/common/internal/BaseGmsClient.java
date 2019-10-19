package com.google.android.android.common.internal;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.android.common.ConnectionResult;
import com.google.android.android.common.Feature;
import com.google.android.android.common.GoogleApiAvailabilityLight;
import com.google.android.android.common.aimsicd.CommonStatusCodes;
import com.google.android.android.common.aimsicd.Scope;
import com.google.android.android.internal.common.Timer;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.concurrent.GuardedBy;

@KeepForSdk
public abstract class BaseGmsClient<T extends IInterface>
{
  @KeepForSdk
  public static final int CONNECT_STATE_CONNECTED = 4;
  @KeepForSdk
  public static final int CONNECT_STATE_DISCONNECTED = 1;
  @KeepForSdk
  public static final int CONNECT_STATE_DISCONNECTING = 5;
  @KeepForSdk
  public static final String DEFAULT_ACCOUNT = "<<default account>>";
  @KeepForSdk
  public static final String[] GOOGLE_PLUS_REQUIRED_FEATURES = { "service_esmobile", "service_googleme" };
  @KeepForSdk
  public static final String KEY_PENDING_INTENT = "pendingIntent";
  private static final Feature[] zzbs = new Feature[0];
  private final Context mContext;
  final Handler mHandler;
  private final Object mLock = new Object();
  private int zzbt;
  private long zzbu;
  private long zzbv;
  private int zzbw;
  private long zzbx;
  @VisibleForTesting
  private ProcessUtil zzby;
  private final Looper zzbz;
  private final GmsClientSupervisor zzca;
  private final GoogleApiAvailabilityLight zzcb;
  private final Object zzcc = new Object();
  @GuardedBy("mServiceBrokerLock")
  private IGmsServiceBroker zzcd;
  @VisibleForTesting
  protected ConnectionProgressReportCallbacks zzce;
  @GuardedBy("mLock")
  private T zzcf;
  private final ArrayList<com.google.android.gms.common.internal.BaseGmsClient.zzc<?>> zzcg = new ArrayList();
  @GuardedBy("mLock")
  private com.google.android.gms.common.internal.BaseGmsClient.zze zzch;
  @GuardedBy("mLock")
  private int zzci = 1;
  private final BaseConnectionCallbacks zzcj;
  private final BaseOnConnectionFailedListener zzck;
  private final int zzcl;
  private final String zzcm;
  private ConnectionResult zzcn = null;
  private boolean zzco = false;
  private volatile Entry zzcp = null;
  @VisibleForTesting
  protected AtomicInteger zzcq = new AtomicInteger(0);
  
  protected BaseGmsClient(Context paramContext, Handler paramHandler, GmsClientSupervisor paramGmsClientSupervisor, GoogleApiAvailabilityLight paramGoogleApiAvailabilityLight, int paramInt, BaseConnectionCallbacks paramBaseConnectionCallbacks, BaseOnConnectionFailedListener paramBaseOnConnectionFailedListener)
  {
    mContext = ((Context)Preconditions.checkNotNull(paramContext, "Context must not be null"));
    mHandler = ((Handler)Preconditions.checkNotNull(paramHandler, "Handler must not be null"));
    zzbz = paramHandler.getLooper();
    zzca = ((GmsClientSupervisor)Preconditions.checkNotNull(paramGmsClientSupervisor, "Supervisor must not be null"));
    zzcb = ((GoogleApiAvailabilityLight)Preconditions.checkNotNull(paramGoogleApiAvailabilityLight, "API availability must not be null"));
    zzcl = paramInt;
    zzcj = paramBaseConnectionCallbacks;
    zzck = paramBaseOnConnectionFailedListener;
    zzcm = null;
  }
  
  protected BaseGmsClient(Context paramContext, Looper paramLooper, int paramInt, BaseConnectionCallbacks paramBaseConnectionCallbacks, BaseOnConnectionFailedListener paramBaseOnConnectionFailedListener, String paramString)
  {
    this(paramContext, paramLooper, GmsClientSupervisor.getInstance(paramContext), GoogleApiAvailabilityLight.getInstance(), paramInt, (BaseConnectionCallbacks)Preconditions.checkNotNull(paramBaseConnectionCallbacks), (BaseOnConnectionFailedListener)Preconditions.checkNotNull(paramBaseOnConnectionFailedListener), paramString);
  }
  
  protected BaseGmsClient(Context paramContext, Looper paramLooper, GmsClientSupervisor paramGmsClientSupervisor, GoogleApiAvailabilityLight paramGoogleApiAvailabilityLight, int paramInt, BaseConnectionCallbacks paramBaseConnectionCallbacks, BaseOnConnectionFailedListener paramBaseOnConnectionFailedListener, String paramString)
  {
    mContext = ((Context)Preconditions.checkNotNull(paramContext, "Context must not be null"));
    zzbz = ((Looper)Preconditions.checkNotNull(paramLooper, "Looper must not be null"));
    zzca = ((GmsClientSupervisor)Preconditions.checkNotNull(paramGmsClientSupervisor, "Supervisor must not be null"));
    zzcb = ((GoogleApiAvailabilityLight)Preconditions.checkNotNull(paramGoogleApiAvailabilityLight, "API availability must not be null"));
    mHandler = new zzb(paramLooper);
    zzcl = paramInt;
    zzcj = paramBaseConnectionCallbacks;
    zzck = paramBaseOnConnectionFailedListener;
    zzcm = paramString;
  }
  
  private final boolean addAll(int paramInt1, int paramInt2, IInterface paramIInterface)
  {
    Object localObject = mLock;
    try
    {
      if (zzci != paramInt1) {
        return false;
      }
      create(paramInt2, paramIInterface);
      return true;
    }
    catch (Throwable paramIInterface)
    {
      throw paramIInterface;
    }
  }
  
  private final void addResult(Entry paramEntry)
  {
    zzcp = paramEntry;
  }
  
  private final boolean addTask()
  {
    Object localObject = mLock;
    for (;;)
    {
      try
      {
        if (zzci == 3)
        {
          bool = true;
          return bool;
        }
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
      boolean bool = false;
    }
  }
  
  private final void create(int paramInt, IInterface paramIInterface)
  {
    int i;
    if (paramInt == 4) {
      i = 1;
    } else {
      i = 0;
    }
    int j;
    if (paramIInterface != null) {
      j = 1;
    } else {
      j = 0;
    }
    boolean bool;
    if (i == j) {
      bool = true;
    } else {
      bool = false;
    }
    Preconditions.checkArgument(bool);
    Object localObject1 = mLock;
    for (;;)
    {
      try
      {
        zzci = paramInt;
        zzcf = paramIInterface;
        onSetConnectState(paramInt, paramIInterface);
        switch (paramInt)
        {
        case 4: 
          onConnectedLocked(paramIInterface);
          break;
        case 2: 
        case 3: 
          if ((zzch != null) && (zzby != null))
          {
            paramIInterface = zzby.getResultPath();
            str1 = zzby.getPackageName();
            localObject2 = new StringBuilder(String.valueOf(paramIInterface).length() + 70 + String.valueOf(str1).length());
            ((StringBuilder)localObject2).append("Calling connect() while still connected, missing disconnect() for ");
            ((StringBuilder)localObject2).append(paramIInterface);
            ((StringBuilder)localObject2).append(" on ");
            ((StringBuilder)localObject2).append(str1);
            Log.e("GmsClient", ((StringBuilder)localObject2).toString());
            zzca.put(zzby.getResultPath(), zzby.getPackageName(), zzby.getInstance(), zzch, getDefault());
            zzcq.incrementAndGet();
          }
          zzch = new zze(zzcq.get());
          if ((zzci == 3) && (getLocalStartServiceAction() != null)) {
            paramIInterface = new ProcessUtil(getContext().getPackageName(), getLocalStartServiceAction(), true, 129);
          } else {
            paramIInterface = new ProcessUtil(getStartServicePackage(), getStartServiceAction(), false, 129);
          }
          zzby = paramIInterface;
          paramIInterface = zzca;
          String str1 = zzby.getResultPath();
          Object localObject2 = zzby.getPackageName();
          paramInt = zzby.getInstance();
          zze localZze = zzch;
          String str2 = getDefault();
          if (!paramIInterface.start(new GmsClientSupervisor.zza(str1, (String)localObject2, paramInt), localZze, str2))
          {
            paramIInterface = zzby.getResultPath();
            str1 = zzby.getPackageName();
            localObject2 = new StringBuilder(String.valueOf(paramIInterface).length() + 34 + String.valueOf(str1).length());
            ((StringBuilder)localObject2).append("unable to connect to service: ");
            ((StringBuilder)localObject2).append(paramIInterface);
            ((StringBuilder)localObject2).append(" on ");
            ((StringBuilder)localObject2).append(str1);
            Log.e("GmsClient", ((StringBuilder)localObject2).toString());
            showTab(16, null, zzcq.get());
          }
          break;
        case 1: 
          if (zzch != null)
          {
            zzca.put(getStartServiceAction(), getStartServicePackage(), 129, zzch, getDefault());
            zzch = null;
          }
          return;
        }
      }
      catch (Throwable paramIInterface)
      {
        throw paramIInterface;
      }
    }
  }
  
  private final boolean f()
  {
    if (zzco) {
      return false;
    }
    if (TextUtils.isEmpty(getServiceDescriptor())) {
      return false;
    }
    if (TextUtils.isEmpty(getLocalStartServiceAction())) {
      return false;
    }
    try
    {
      Class.forName(getServiceDescriptor());
      return true;
    }
    catch (ClassNotFoundException localClassNotFoundException) {}
    return false;
  }
  
  private final String getDefault()
  {
    if (zzcm == null) {
      return mContext.getClass().getName();
    }
    return zzcm;
  }
  
  private final void sendMessage(int paramInt)
  {
    if (addTask())
    {
      paramInt = 5;
      zzco = true;
    }
    else
    {
      paramInt = 4;
    }
    mHandler.sendMessage(mHandler.obtainMessage(paramInt, zzcq.get(), 16));
  }
  
  public void checkAvailabilityAndConnect()
  {
    int i = zzcb.isGooglePlayServicesAvailable(mContext, getMinApkVersion());
    if (i != 0)
    {
      create(1, null);
      triggerNotAvailable(new LegacyClientCallbackAdapter(), i, null);
      return;
    }
    connect(new LegacyClientCallbackAdapter());
  }
  
  protected final void checkConnected()
  {
    if (isConnected()) {
      return;
    }
    throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
  }
  
  public void connect(ConnectionProgressReportCallbacks paramConnectionProgressReportCallbacks)
  {
    zzce = ((ConnectionProgressReportCallbacks)Preconditions.checkNotNull(paramConnectionProgressReportCallbacks, "Connection progress callbacks cannot be null."));
    create(2, null);
  }
  
  protected abstract IInterface createServiceInterface(IBinder paramIBinder);
  
  /* Error */
  public void disconnect()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 144	com/google/android/android/common/internal/BaseGmsClient:zzcq	Ljava/util/concurrent/atomic/AtomicInteger;
    //   4: invokevirtual 301	java/util/concurrent/atomic/AtomicInteger:incrementAndGet	()I
    //   7: pop
    //   8: aload_0
    //   9: getfield 129	com/google/android/android/common/internal/BaseGmsClient:zzcg	Ljava/util/ArrayList;
    //   12: astore_3
    //   13: aload_3
    //   14: monitorenter
    //   15: aload_0
    //   16: getfield 129	com/google/android/android/common/internal/BaseGmsClient:zzcg	Ljava/util/ArrayList;
    //   19: invokevirtual 434	java/util/ArrayList:size	()I
    //   22: istore_2
    //   23: iconst_0
    //   24: istore_1
    //   25: iload_1
    //   26: iload_2
    //   27: if_icmpge +24 -> 51
    //   30: aload_0
    //   31: getfield 129	com/google/android/android/common/internal/BaseGmsClient:zzcg	Ljava/util/ArrayList;
    //   34: iload_1
    //   35: invokevirtual 437	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   38: checkcast 21	com/google/android/android/common/internal/BaseGmsClient$zzc
    //   41: invokevirtual 440	com/google/android/android/common/internal/BaseGmsClient$zzc:removeListener	()V
    //   44: iload_1
    //   45: iconst_1
    //   46: iadd
    //   47: istore_1
    //   48: goto -23 -> 25
    //   51: aload_0
    //   52: getfield 129	com/google/android/android/common/internal/BaseGmsClient:zzcg	Ljava/util/ArrayList;
    //   55: invokevirtual 443	java/util/ArrayList:clear	()V
    //   58: aload_3
    //   59: monitorexit
    //   60: aload_0
    //   61: getfield 124	com/google/android/android/common/internal/BaseGmsClient:zzcc	Ljava/lang/Object;
    //   64: astore_3
    //   65: aload_3
    //   66: monitorenter
    //   67: aload_0
    //   68: aconst_null
    //   69: putfield 215	com/google/android/android/common/internal/BaseGmsClient:zzcd	Lcom/google/android/android/common/internal/IGmsServiceBroker;
    //   72: aload_3
    //   73: monitorexit
    //   74: aload_0
    //   75: iconst_1
    //   76: aconst_null
    //   77: invokespecial 223	com/google/android/android/common/internal/BaseGmsClient:create	(ILandroid/os/IInterface;)V
    //   80: return
    //   81: astore 4
    //   83: aload_3
    //   84: monitorexit
    //   85: aload 4
    //   87: athrow
    //   88: astore 4
    //   90: aload_3
    //   91: monitorexit
    //   92: aload 4
    //   94: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	95	0	this	BaseGmsClient
    //   24	24	1	i	int
    //   22	6	2	j	int
    //   12	79	3	localObject	Object
    //   81	5	4	localThrowable1	Throwable
    //   88	5	4	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   67	74	81	java/lang/Throwable
    //   83	85	81	java/lang/Throwable
    //   15	23	88	java/lang/Throwable
    //   30	44	88	java/lang/Throwable
    //   51	60	88	java/lang/Throwable
    //   90	92	88	java/lang/Throwable
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    paramArrayOfString = mLock;
    try
    {
      int i = zzci;
      paramFileDescriptor = zzcf;
      paramArrayOfString = zzcc;
      try
      {
        Object localObject = zzcd;
        paramPrintWriter.append(paramString).append("mConnectState=");
        switch (i)
        {
        default: 
          paramPrintWriter.print("UNKNOWN");
          break;
        case 5: 
          paramPrintWriter.print("DISCONNECTING");
          break;
        case 4: 
          paramPrintWriter.print("CONNECTED");
          break;
        case 3: 
          paramPrintWriter.print("LOCAL_CONNECTING");
          break;
        case 2: 
          paramPrintWriter.print("REMOTE_CONNECTING");
          break;
        case 1: 
          paramPrintWriter.print("DISCONNECTED");
        }
        paramPrintWriter.append(" mService=");
        if (paramFileDescriptor == null) {
          paramPrintWriter.append("null");
        } else {
          paramPrintWriter.append(getServiceDescriptor()).append("@").append(Integer.toHexString(System.identityHashCode(paramFileDescriptor.asBinder())));
        }
        paramPrintWriter.append(" mServiceBroker=");
        if (localObject == null) {
          paramPrintWriter.println("null");
        } else {
          paramPrintWriter.append("IGmsServiceBroker@").println(Integer.toHexString(System.identityHashCode(((IInterface)localObject).asBinder())));
        }
        paramFileDescriptor = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
        long l;
        StringBuilder localStringBuilder;
        if (zzbv > 0L)
        {
          paramArrayOfString = paramPrintWriter.append(paramString).append("lastConnectedTime=");
          l = zzbv;
          localObject = paramFileDescriptor.format(new Date(zzbv));
          localStringBuilder = new StringBuilder(String.valueOf(localObject).length() + 21);
          localStringBuilder.append(l);
          localStringBuilder.append(" ");
          localStringBuilder.append((String)localObject);
          paramArrayOfString.println(localStringBuilder.toString());
        }
        if (zzbu > 0L)
        {
          paramPrintWriter.append(paramString).append("lastSuspendedCause=");
          switch (zzbt)
          {
          default: 
            paramPrintWriter.append(String.valueOf(zzbt));
            break;
          case 2: 
            paramPrintWriter.append("CAUSE_NETWORK_LOST");
            break;
          case 1: 
            paramPrintWriter.append("CAUSE_SERVICE_DISCONNECTED");
          }
          paramArrayOfString = paramPrintWriter.append(" lastSuspendedTime=");
          l = zzbu;
          localObject = paramFileDescriptor.format(new Date(zzbu));
          localStringBuilder = new StringBuilder(String.valueOf(localObject).length() + 21);
          localStringBuilder.append(l);
          localStringBuilder.append(" ");
          localStringBuilder.append((String)localObject);
          paramArrayOfString.println(localStringBuilder.toString());
        }
        if (zzbx > 0L)
        {
          paramPrintWriter.append(paramString).append("lastFailedStatus=").append(CommonStatusCodes.getStatusCodeString(zzbw));
          paramString = paramPrintWriter.append(" lastFailedTime=");
          l = zzbx;
          paramFileDescriptor = paramFileDescriptor.format(new Date(zzbx));
          paramPrintWriter = new StringBuilder(String.valueOf(paramFileDescriptor).length() + 21);
          paramPrintWriter.append(l);
          paramPrintWriter.append(" ");
          paramPrintWriter.append(paramFileDescriptor);
          paramString.println(paramPrintWriter.toString());
          return;
        }
      }
      catch (Throwable paramString)
      {
        throw paramString;
      }
      return;
    }
    catch (Throwable paramString)
    {
      throw paramString;
    }
  }
  
  public Account getAccount()
  {
    return null;
  }
  
  public Feature[] getApiFeatures()
  {
    return zzbs;
  }
  
  public final Feature[] getAvailableFeatures()
  {
    Entry localEntry = zzcp;
    if (localEntry == null) {
      return null;
    }
    return zzda;
  }
  
  public Bundle getConnectionHint()
  {
    return null;
  }
  
  public final Context getContext()
  {
    return mContext;
  }
  
  public String getEndpointPackageName()
  {
    if ((isConnected()) && (zzby != null)) {
      return zzby.getPackageName();
    }
    throw new RuntimeException("Failed to connect when checking package");
  }
  
  protected Bundle getGetServiceRequestExtraArgs()
  {
    return new Bundle();
  }
  
  protected String getLocalStartServiceAction()
  {
    return null;
  }
  
  public final Looper getLooper()
  {
    return zzbz;
  }
  
  public int getMinApkVersion()
  {
    return GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
  }
  
  public void getRemoteService(IAccountAccessor paramIAccountAccessor, Set paramSet)
  {
    Bundle localBundle = getGetServiceRequestExtraArgs();
    GetServiceRequest localGetServiceRequest = new GetServiceRequest(zzcl);
    zzdh = mContext.getPackageName();
    zzdk = localBundle;
    if (paramSet != null) {
      zzdj = ((Scope[])paramSet.toArray(new Scope[paramSet.size()]));
    }
    if (requiresSignIn())
    {
      if (getAccount() != null) {
        paramSet = getAccount();
      } else {
        paramSet = new Account("<<default account>>", "com.google");
      }
      zzdl = paramSet;
      if (paramIAccountAccessor != null) {
        zzdi = paramIAccountAccessor.asBinder();
      }
    }
    else if (requiresAccount())
    {
      zzdl = getAccount();
    }
    zzdm = zzbs;
    zzdn = getApiFeatures();
    try
    {
      paramIAccountAccessor = zzcc;
      try
      {
        if (zzcd != null) {
          zzcd.getService(new zzd(zzcq.get()), localGetServiceRequest);
        } else {
          Log.w("GmsClient", "mServiceBroker is null, client disconnected");
        }
        return;
      }
      catch (Throwable paramSet) {}
      return;
    }
    catch (RuntimeException|RemoteException paramIAccountAccessor)
    {
      try
      {
        throw paramSet;
      }
      catch (SecurityException paramIAccountAccessor)
      {
        throw paramIAccountAccessor;
      }
      catch (DeadObjectException paramIAccountAccessor)
      {
        Log.w("GmsClient", "IGmsServiceBroker.getService failed", paramIAccountAccessor);
        triggerConnectionSuspended(1);
      }
      paramIAccountAccessor = paramIAccountAccessor;
      Log.w("GmsClient", "IGmsServiceBroker.getService failed", paramIAccountAccessor);
      onPostInitHandler(8, null, null, zzcq.get());
      return;
    }
  }
  
  protected Set getScopes()
  {
    return Collections.EMPTY_SET;
  }
  
  public final IInterface getService()
    throws DeadObjectException
  {
    Object localObject = mLock;
    for (;;)
    {
      try
      {
        if (zzci != 5)
        {
          checkConnected();
          if (zzcf != null)
          {
            bool = true;
            Preconditions.checkState(bool, "Client is connected but service is null");
            IInterface localIInterface = zzcf;
            return localIInterface;
          }
        }
        else
        {
          throw new DeadObjectException();
        }
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
      boolean bool = false;
    }
  }
  
  public IBinder getServiceBrokerBinder()
  {
    Object localObject = zzcc;
    try
    {
      if (zzcd == null) {
        return null;
      }
      IBinder localIBinder = zzcd.asBinder();
      return localIBinder;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  protected abstract String getServiceDescriptor();
  
  public Intent getSignInIntent()
  {
    throw new UnsupportedOperationException("Not a sign in API");
  }
  
  protected abstract String getStartServiceAction();
  
  protected String getStartServicePackage()
  {
    return "com.google.android.gms";
  }
  
  public boolean isConnected()
  {
    Object localObject = mLock;
    for (;;)
    {
      try
      {
        if (zzci == 4)
        {
          bool = true;
          return bool;
        }
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
      boolean bool = false;
    }
  }
  
  public boolean isConnecting()
  {
    Object localObject = mLock;
    for (;;)
    {
      try
      {
        if (zzci == 2) {
          break label40;
        }
        if (zzci != 3) {
          break label35;
        }
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
      return bool;
      label35:
      boolean bool = false;
      continue;
      label40:
      bool = true;
    }
  }
  
  protected void onConnectedLocked(IInterface paramIInterface)
  {
    zzbv = System.currentTimeMillis();
  }
  
  protected void onConnectionFailed(ConnectionResult paramConnectionResult)
  {
    zzbw = paramConnectionResult.getErrorCode();
    zzbx = System.currentTimeMillis();
  }
  
  protected void onConnectionSuspended(int paramInt)
  {
    zzbt = paramInt;
    zzbu = System.currentTimeMillis();
  }
  
  protected void onPostInitHandler(int paramInt1, IBinder paramIBinder, Bundle paramBundle, int paramInt2)
  {
    mHandler.sendMessage(mHandler.obtainMessage(1, paramInt2, -1, new zzf(paramInt1, paramIBinder, paramBundle)));
  }
  
  void onSetConnectState(int paramInt, IInterface paramIInterface) {}
  
  public void onUserSignOut(SignOutCallbacks paramSignOutCallbacks)
  {
    paramSignOutCallbacks.onSignOutComplete();
  }
  
  public boolean providesSignIn()
  {
    return false;
  }
  
  public boolean requiresAccount()
  {
    return false;
  }
  
  public boolean requiresGooglePlayServices()
  {
    return true;
  }
  
  public boolean requiresSignIn()
  {
    return false;
  }
  
  protected final void showTab(int paramInt1, Bundle paramBundle, int paramInt2)
  {
    mHandler.sendMessage(mHandler.obtainMessage(7, paramInt2, -1, new zzg(paramInt1, null)));
  }
  
  public void triggerConnectionSuspended(int paramInt)
  {
    mHandler.sendMessage(mHandler.obtainMessage(6, zzcq.get(), paramInt));
  }
  
  protected void triggerNotAvailable(ConnectionProgressReportCallbacks paramConnectionProgressReportCallbacks, int paramInt, PendingIntent paramPendingIntent)
  {
    zzce = ((ConnectionProgressReportCallbacks)Preconditions.checkNotNull(paramConnectionProgressReportCallbacks, "Connection progress callbacks cannot be null."));
    mHandler.sendMessage(mHandler.obtainMessage(3, zzcq.get(), paramInt, paramPendingIntent));
  }
  
  @KeepForSdk
  public abstract interface BaseConnectionCallbacks
  {
    public abstract void onConnected(Bundle paramBundle);
    
    public abstract void onConnectionSuspended(int paramInt);
  }
  
  @KeepForSdk
  public abstract interface BaseOnConnectionFailedListener
  {
    public abstract void onConnectionFailed(ConnectionResult paramConnectionResult);
  }
  
  @KeepForSdk
  public abstract interface ConnectionProgressReportCallbacks
  {
    public abstract void onReportServiceBinding(ConnectionResult paramConnectionResult);
  }
  
  public class LegacyClientCallbackAdapter
    implements BaseGmsClient.ConnectionProgressReportCallbacks
  {
    public LegacyClientCallbackAdapter() {}
    
    public void onReportServiceBinding(ConnectionResult paramConnectionResult)
    {
      if (paramConnectionResult.isSuccess())
      {
        getRemoteService(null, getScopes());
        return;
      }
      if (BaseGmsClient.redrawView(BaseGmsClient.this) != null) {
        BaseGmsClient.redrawView(BaseGmsClient.this).onConnectionFailed(paramConnectionResult);
      }
    }
  }
  
  @KeepForSdk
  public abstract interface SignOutCallbacks
  {
    public abstract void onSignOutComplete();
  }
  
  abstract class zza
    extends com.google.android.gms.common.internal.BaseGmsClient.zzc<Boolean>
  {
    private final int statusCode;
    private final Bundle zzcr;
    
    protected zza(int paramInt, Bundle paramBundle)
    {
      super(Boolean.valueOf(true));
      statusCode = paramInt;
      zzcr = paramBundle;
    }
    
    protected abstract boolean connect();
    
    protected final void printStackTrace() {}
    
    protected abstract void redrawView(ConnectionResult paramConnectionResult);
  }
  
  final class zzb
    extends Timer
  {
    public zzb(Looper paramLooper)
    {
      super();
    }
    
    private static void processMessage(Message paramMessage)
    {
      paramMessage = (BaseGmsClient.zzc)obj;
      paramMessage.printStackTrace();
      paramMessage.unregister();
    }
    
    private static boolean update(Message paramMessage)
    {
      if ((what != 2) && (what != 1)) {
        return what == 7;
      }
      return true;
    }
    
    public final void handleMessage(Message paramMessage)
    {
      if (zzcq.get() != arg1)
      {
        if (update(paramMessage)) {
          processMessage(paramMessage);
        }
      }
      else
      {
        if (((what == 1) || (what == 7) || (what == 4) || (what == 5)) && (!isConnecting()))
        {
          processMessage(paramMessage);
          return;
        }
        int i = what;
        PendingIntent localPendingIntent = null;
        if (i == 4)
        {
          BaseGmsClient.getWordRangeAtCursor(BaseGmsClient.this, new ConnectionResult(arg2));
          if ((BaseGmsClient.next(BaseGmsClient.this)) && (!BaseGmsClient.deleteData(BaseGmsClient.this)))
          {
            BaseGmsClient.switchState(BaseGmsClient.this, 3, null);
            return;
          }
          if (BaseGmsClient.loadView(BaseGmsClient.this) != null) {
            paramMessage = BaseGmsClient.loadView(BaseGmsClient.this);
          } else {
            paramMessage = new ConnectionResult(8);
          }
          zzce.onReportServiceBinding(paramMessage);
          onConnectionFailed(paramMessage);
          return;
        }
        if (what == 5)
        {
          if (BaseGmsClient.loadView(BaseGmsClient.this) != null) {
            paramMessage = BaseGmsClient.loadView(BaseGmsClient.this);
          } else {
            paramMessage = new ConnectionResult(8);
          }
          zzce.onReportServiceBinding(paramMessage);
          onConnectionFailed(paramMessage);
          return;
        }
        if (what == 3)
        {
          if ((obj instanceof PendingIntent)) {
            localPendingIntent = (PendingIntent)obj;
          }
          paramMessage = new ConnectionResult(arg2, localPendingIntent);
          zzce.onReportServiceBinding(paramMessage);
          onConnectionFailed(paramMessage);
          return;
        }
        if (what == 6)
        {
          BaseGmsClient.switchState(BaseGmsClient.this, 5, null);
          if (BaseGmsClient.onConnecting(BaseGmsClient.this) != null) {
            BaseGmsClient.onConnecting(BaseGmsClient.this).onConnectionSuspended(arg2);
          }
          onConnectionSuspended(arg2);
          BaseGmsClient.addAll(BaseGmsClient.this, 5, 1, null);
          return;
        }
        if ((what == 2) && (!isConnected()))
        {
          processMessage(paramMessage);
          return;
        }
        if (update(paramMessage))
        {
          ((BaseGmsClient.zzc)obj).loadCache();
          return;
        }
        i = what;
        paramMessage = new StringBuilder(45);
        paramMessage.append("Don't know how to handle message: ");
        paramMessage.append(i);
        Log.wtf("GmsClient", paramMessage.toString(), new Exception());
      }
    }
  }
  
  public abstract class zzc<TListener>
  {
    private TListener zzct;
    private boolean zzcu;
    
    public zzc(Object paramObject)
    {
      zzct = paramObject;
      zzcu = false;
    }
    
    /* Error */
    public final void loadCache()
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield 23	com/google/android/android/common/internal/BaseGmsClient$zzc:zzct	Ljava/lang/Object;
      //   6: astore_1
      //   7: aload_0
      //   8: getfield 25	com/google/android/android/common/internal/BaseGmsClient$zzc:zzcu	Z
      //   11: ifeq +56 -> 67
      //   14: aload_0
      //   15: invokestatic 37	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
      //   18: astore_2
      //   19: new 39	java/lang/StringBuilder
      //   22: dup
      //   23: aload_2
      //   24: invokestatic 37	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
      //   27: invokevirtual 43	java/lang/String:length	()I
      //   30: bipush 47
      //   32: iadd
      //   33: invokespecial 46	java/lang/StringBuilder:<init>	(I)V
      //   36: astore_3
      //   37: aload_3
      //   38: ldc 48
      //   40: invokevirtual 52	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   43: pop
      //   44: aload_3
      //   45: aload_2
      //   46: invokevirtual 52	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   49: pop
      //   50: aload_3
      //   51: ldc 54
      //   53: invokevirtual 52	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   56: pop
      //   57: ldc 56
      //   59: aload_3
      //   60: invokevirtual 60	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   63: invokestatic 66	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
      //   66: pop
      //   67: aload_0
      //   68: monitorexit
      //   69: aload_1
      //   70: ifnull +18 -> 88
      //   73: aload_0
      //   74: aload_1
      //   75: invokevirtual 70	com/google/android/android/common/internal/BaseGmsClient$zzc:onPostExecute	(Ljava/lang/Object;)V
      //   78: goto +14 -> 92
      //   81: astore_1
      //   82: aload_0
      //   83: invokevirtual 73	com/google/android/android/common/internal/BaseGmsClient$zzc:printStackTrace	()V
      //   86: aload_1
      //   87: athrow
      //   88: aload_0
      //   89: invokevirtual 73	com/google/android/android/common/internal/BaseGmsClient$zzc:printStackTrace	()V
      //   92: aload_0
      //   93: monitorenter
      //   94: aload_0
      //   95: iconst_1
      //   96: putfield 25	com/google/android/android/common/internal/BaseGmsClient$zzc:zzcu	Z
      //   99: aload_0
      //   100: monitorexit
      //   101: aload_0
      //   102: invokevirtual 76	com/google/android/android/common/internal/BaseGmsClient$zzc:unregister	()V
      //   105: return
      //   106: astore_1
      //   107: aload_0
      //   108: monitorexit
      //   109: aload_1
      //   110: athrow
      //   111: astore_1
      //   112: aload_0
      //   113: monitorexit
      //   114: aload_1
      //   115: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	116	0	this	zzc
      //   6	69	1	localObject	Object
      //   81	6	1	localRuntimeException	RuntimeException
      //   106	4	1	localThrowable1	Throwable
      //   111	4	1	localThrowable2	Throwable
      //   18	28	2	str	String
      //   36	24	3	localStringBuilder	StringBuilder
      // Exception table:
      //   from	to	target	type
      //   73	78	81	java/lang/RuntimeException
      //   94	101	106	java/lang/Throwable
      //   107	109	106	java/lang/Throwable
      //   2	67	111	java/lang/Throwable
      //   67	69	111	java/lang/Throwable
      //   112	114	111	java/lang/Throwable
    }
    
    protected abstract void onPostExecute(Object paramObject);
    
    protected abstract void printStackTrace();
    
    public final void removeListener()
    {
      try
      {
        zzct = null;
        return;
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
    
    public final void unregister()
    {
      removeListener();
      ArrayList localArrayList = BaseGmsClient.items(BaseGmsClient.this);
      try
      {
        BaseGmsClient.items(BaseGmsClient.this).remove(this);
        return;
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
  }
  
  @VisibleForTesting
  public final class zzd
    extends IGmsCallbacks.zza
  {
    private final int zzcw;
    
    public zzd(int paramInt)
    {
      zzcw = paramInt;
    }
    
    public final void init(int paramInt, Bundle paramBundle)
    {
      Log.wtf("GmsClient", "received deprecated onAccountValidationComplete callback, ignoring", new Exception());
    }
    
    public final void onPostInitComplete(int paramInt, IBinder paramIBinder, Bundle paramBundle)
    {
      Preconditions.checkNotNull(BaseGmsClient.this, "onPostInitComplete can be called only once per call to getRemoteService");
      onPostInitHandler(paramInt, paramIBinder, paramBundle, zzcw);
      zzcv = null;
    }
    
    public final void write(int paramInt, IBinder paramIBinder, Entry paramEntry)
    {
      Preconditions.checkNotNull(BaseGmsClient.this, "onPostInitCompleteWithConnectionInfo can be called only once per call togetRemoteService");
      Preconditions.checkNotNull(paramEntry);
      BaseGmsClient.addResult(BaseGmsClient.this, paramEntry);
      onPostInitComplete(paramInt, paramIBinder, zzcz);
    }
  }
  
  @VisibleForTesting
  public final class zze
    implements ServiceConnection
  {
    private final int zzcw;
    
    public zze(int paramInt)
    {
      zzcw = paramInt;
    }
    
    public final void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      if (paramIBinder == null)
      {
        BaseGmsClient.toHtml(BaseGmsClient.this, 16);
        return;
      }
      Object localObject = BaseGmsClient.access$getRunning(BaseGmsClient.this);
      try
      {
        BaseGmsClient localBaseGmsClient = BaseGmsClient.this;
        if (paramIBinder == null)
        {
          paramComponentName = null;
        }
        else
        {
          paramComponentName = paramIBinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
          if ((paramComponentName != null) && ((paramComponentName instanceof IGmsServiceBroker))) {
            paramComponentName = (IGmsServiceBroker)paramComponentName;
          } else {
            paramComponentName = new IGmsServiceBroker.Stub.zza(paramIBinder);
          }
        }
        BaseGmsClient.access$setDataService(localBaseGmsClient, paramComponentName);
        showTab(0, null, zzcw);
        return;
      }
      catch (Throwable paramComponentName)
      {
        throw paramComponentName;
      }
    }
    
    public final void onServiceDisconnected(ComponentName paramComponentName)
    {
      paramComponentName = BaseGmsClient.access$getRunning(BaseGmsClient.this);
      try
      {
        BaseGmsClient.access$setDataService(BaseGmsClient.this, null);
        mHandler.sendMessage(mHandler.obtainMessage(6, zzcw, 1));
        return;
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
  }
  
  public final class zzf
    extends com.google.android.gms.common.internal.BaseGmsClient.zza
  {
    private final IBinder zzcx;
    
    public zzf(int paramInt, IBinder paramIBinder, Bundle paramBundle)
    {
      super(paramInt, paramBundle);
      zzcx = paramIBinder;
    }
    
    protected final boolean connect()
    {
      Object localObject = zzcx;
      try
      {
        localObject = ((IBinder)localObject).getInterfaceDescriptor();
        if (!getServiceDescriptor().equals(localObject))
        {
          String str = getServiceDescriptor();
          StringBuilder localStringBuilder = new StringBuilder(String.valueOf(str).length() + 34 + String.valueOf(localObject).length());
          localStringBuilder.append("service descriptor mismatch: ");
          localStringBuilder.append(str);
          localStringBuilder.append(" vs. ");
          localStringBuilder.append((String)localObject);
          Log.e("GmsClient", localStringBuilder.toString());
          return false;
        }
        localObject = createServiceInterface(zzcx);
        if (localObject != null)
        {
          if ((!BaseGmsClient.addAll(BaseGmsClient.this, 2, 4, (IInterface)localObject)) && (!BaseGmsClient.addAll(BaseGmsClient.this, 3, 4, (IInterface)localObject))) {
            break label198;
          }
          BaseGmsClient.getWordRangeAtCursor(BaseGmsClient.this, null);
          localObject = getConnectionHint();
          if (BaseGmsClient.onConnecting(BaseGmsClient.this) != null) {
            BaseGmsClient.onConnecting(BaseGmsClient.this).onConnected((Bundle)localObject);
          }
          return true;
        }
        return false;
      }
      catch (RemoteException localRemoteException)
      {
        for (;;) {}
      }
      Log.w("GmsClient", "service probably died");
      return false;
      label198:
      return false;
    }
    
    protected final void redrawView(ConnectionResult paramConnectionResult)
    {
      if (BaseGmsClient.redrawView(BaseGmsClient.this) != null) {
        BaseGmsClient.redrawView(BaseGmsClient.this).onConnectionFailed(paramConnectionResult);
      }
      onConnectionFailed(paramConnectionResult);
    }
  }
  
  public final class zzg
    extends com.google.android.gms.common.internal.BaseGmsClient.zza
  {
    public zzg(int paramInt, Bundle paramBundle)
    {
      super(paramInt, null);
    }
    
    protected final boolean connect()
    {
      zzce.onReportServiceBinding(ConnectionResult.RESULT_SUCCESS);
      return true;
    }
    
    protected final void redrawView(ConnectionResult paramConnectionResult)
    {
      zzce.onReportServiceBinding(paramConnectionResult);
      onConnectionFailed(paramConnectionResult);
    }
  }
}
