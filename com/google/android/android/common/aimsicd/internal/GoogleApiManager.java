package com.google.android.android.common.aimsicd.internal;

import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.ArraySet;
import android.util.Log;
import com.google.android.android.common.ConnectionResult;
import com.google.android.android.common.Feature;
import com.google.android.android.common.GoogleApiAvailability;
import com.google.android.android.common.aimsicd.Api.AnyClient;
import com.google.android.android.common.aimsicd.Api.Client;
import com.google.android.android.common.aimsicd.GoogleApi;
import com.google.android.android.common.aimsicd.Sample;
import com.google.android.android.common.aimsicd.Status;
import com.google.android.android.common.aimsicd.UnsupportedApiCallException;
import com.google.android.android.common.internal.BaseGmsClient.ConnectionProgressReportCallbacks;
import com.google.android.android.common.internal.GoogleApiAvailabilityCache;
import com.google.android.android.common.internal.IAccountAccessor;
import com.google.android.android.common.internal.Objects;
import com.google.android.android.common.internal.Objects.ToStringHelper;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.common.internal.SimpleClientAdapter;
import com.google.android.android.common.util.ArrayUtils;
import com.google.android.android.common.util.PlatformVersion;
import com.google.android.android.internal.base.Credentials;
import com.google.android.android.signin.Client;
import com.google.android.android.tasks.Task;
import com.google.android.android.tasks.TaskCompletionSource;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.internal.zab;
import com.google.android.gms.common.api.internal.zai;
import com.google.android.gms.common.api.internal.zak;
import com.google.android.gms.common.api.internal.zar;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.concurrent.GuardedBy;

@KeepForSdk
public class GoogleApiManager
  implements Handler.Callback
{
  private static final Object lock = new Object();
  public static final Status zahw = new Status(4, "Sign-out occurred while this API call was in progress.");
  private static final Status zahx = new Status(4, "The user must be signed in to make this API call.");
  @GuardedBy("lock")
  private static GoogleApiManager zaib;
  private final Handler handler;
  private long zahy = 5000L;
  private long zahz = 120000L;
  private long zaia = 10000L;
  private final Context zaic;
  private final GoogleApiAvailability zaid;
  private final GoogleApiAvailabilityCache zaie;
  private final AtomicInteger zaif = new AtomicInteger(1);
  private final AtomicInteger zaig = new AtomicInteger(0);
  private final Map<zai<?>, com.google.android.gms.common.api.internal.GoogleApiManager.zaa<?>> zaih = new ConcurrentHashMap(5, 0.75F, 1);
  @GuardedBy("lock")
  private zaae zaii = null;
  @GuardedBy("lock")
  private final Set<zai<?>> zaij = new ArraySet();
  private final Set<zai<?>> zaik = new ArraySet();
  
  private GoogleApiManager(Context paramContext, Looper paramLooper, GoogleApiAvailability paramGoogleApiAvailability)
  {
    zaic = paramContext;
    handler = new Credentials(paramLooper, this);
    zaid = paramGoogleApiAvailability;
    zaie = new GoogleApiAvailabilityCache(paramGoogleApiAvailability);
    handler.sendMessage(handler.obtainMessage(6));
  }
  
  private final void getSources(GoogleApi paramGoogleApi)
  {
    Msg localMsg = paramGoogleApi.get();
    zaa localZaa2 = (zaa)zaih.get(localMsg);
    zaa localZaa1 = localZaa2;
    if (localZaa2 == null)
    {
      localZaa1 = new zaa(paramGoogleApi);
      zaih.put(localMsg, localZaa1);
    }
    if (localZaa1.requiresSignIn()) {
      zaik.add(localMsg);
    }
    localZaa1.connect();
  }
  
  public static GoogleApiManager open(Context paramContext)
  {
    Object localObject1 = lock;
    try
    {
      if (zaib == null)
      {
        Object localObject2 = new HandlerThread("GoogleApiHandler", 9);
        ((Thread)localObject2).start();
        localObject2 = ((HandlerThread)localObject2).getLooper();
        zaib = new GoogleApiManager(paramContext.getApplicationContext(), (Looper)localObject2, GoogleApiAvailability.getInstance());
      }
      paramContext = zaib;
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      throw paramContext;
    }
  }
  
  public static void reportSignOut()
  {
    Object localObject = lock;
    try
    {
      if (zaib != null)
      {
        GoogleApiManager localGoogleApiManager = zaib;
        zaig.incrementAndGet();
        handler.sendMessageAtFrontOfQueue(handler.obtainMessage(10));
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public static GoogleApiManager zabc()
  {
    Object localObject = lock;
    try
    {
      Preconditions.checkNotNull(zaib, "Must guarantee manager is non-null before using getInstance");
      GoogleApiManager localGoogleApiManager = zaib;
      return localGoogleApiManager;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public final Task call(Iterable paramIterable)
  {
    paramIterable = new LogItem(paramIterable);
    handler.sendMessage(handler.obtainMessage(2, paramIterable));
    return paramIterable.getTask();
  }
  
  public final void clear(GoogleApi paramGoogleApi, int paramInt, TaskApiCall paramTaskApiCall, TaskCompletionSource paramTaskCompletionSource, StatusExceptionMapper paramStatusExceptionMapper)
  {
    paramTaskApiCall = new ByteArrayBuffer(paramInt, paramTaskApiCall, paramTaskCompletionSource, paramStatusExceptionMapper);
    handler.sendMessage(handler.obtainMessage(4, new zabv(paramTaskApiCall, zaig.get(), paramGoogleApi)));
  }
  
  public final void close()
  {
    handler.sendMessage(handler.obtainMessage(3));
  }
  
  public final void close(ConnectionResult paramConnectionResult, int paramInt)
  {
    if (!ignore(paramConnectionResult, paramInt)) {
      handler.sendMessage(handler.obtainMessage(5, paramInt, 0, paramConnectionResult));
    }
  }
  
  final PendingIntent getIntent(Msg paramMsg, int paramInt)
  {
    paramMsg = (zaa)zaih.get(paramMsg);
    if (paramMsg == null) {
      return null;
    }
    paramMsg = paramMsg.zabq();
    if (paramMsg == null) {
      return null;
    }
    return PendingIntent.getActivity(zaic, paramInt, paramMsg.getSignInIntent(), 134217728);
  }
  
  public boolean handleMessage(Message paramMessage)
  {
    int i = what;
    long l = 300000L;
    Object localObject1;
    Object localObject2;
    label675:
    Object localObject3;
    switch (i)
    {
    default: 
      i = what;
      paramMessage = new StringBuilder(31);
      paramMessage.append("Unknown message id: ");
      paramMessage.append(i);
      Log.w("GoogleApiManager", paramMessage.toString());
      return false;
    case 16: 
      paramMessage = (zab)obj;
      if (zaih.containsKey(zab.getCacheKey(paramMessage)))
      {
        zaa.trackChanged((zaa)zaih.get(zab.getCacheKey(paramMessage)), paramMessage);
        return true;
      }
      break;
    case 15: 
      paramMessage = (zab)obj;
      if (zaih.containsKey(zab.getCacheKey(paramMessage)))
      {
        zaa.access$getShowHint((zaa)zaih.get(zab.getCacheKey(paramMessage)), paramMessage);
        return true;
      }
      break;
    case 14: 
      paramMessage = (zaaf)obj;
      localObject1 = paramMessage.getCacheKey();
      if (!zaih.containsKey(localObject1))
      {
        paramMessage.zaal().setResult(Boolean.valueOf(false));
        return true;
      }
      boolean bool = zaa.refresh((zaa)zaih.get(localObject1), false);
      paramMessage.zaal().setResult(Boolean.valueOf(bool));
      return true;
    case 12: 
      if (zaih.containsKey(obj))
      {
        ((zaa)zaih.get(obj)).zabp();
        return true;
      }
      break;
    case 11: 
      if (zaih.containsKey(obj))
      {
        ((zaa)zaih.get(obj)).zaav();
        return true;
      }
      break;
    case 10: 
      paramMessage = zaik.iterator();
      while (paramMessage.hasNext())
      {
        localObject1 = (Msg)paramMessage.next();
        ((zaa)zaih.remove(localObject1)).zabj();
      }
      zaik.clear();
      return true;
    case 9: 
      if (zaih.containsKey(obj))
      {
        ((zaa)zaih.get(obj)).resume();
        return true;
      }
      break;
    case 7: 
      getSources((GoogleApi)obj);
      return true;
    case 6: 
      if ((PlatformVersion.isAtLeastIceCreamSandwich()) && ((zaic.getApplicationContext() instanceof Application)))
      {
        BackgroundDetector.initialize((Application)zaic.getApplicationContext());
        BackgroundDetector.getInstance().addListener(new zabi(this));
        if (!BackgroundDetector.getInstance().readCurrentStateIfPossible(true))
        {
          zaia = 300000L;
          return true;
        }
      }
      break;
    case 5: 
      i = arg1;
      localObject1 = (ConnectionResult)obj;
      localObject2 = zaih.values().iterator();
      while (((Iterator)localObject2).hasNext())
      {
        paramMessage = (zaa)((Iterator)localObject2).next();
        if (paramMessage.getInstanceId() == i) {
          break label675;
        }
      }
      paramMessage = null;
      if (paramMessage != null)
      {
        localObject2 = zaid.getErrorString(((ConnectionResult)localObject1).getErrorCode());
        localObject1 = ((ConnectionResult)localObject1).getErrorMessage();
        localObject3 = new StringBuilder(String.valueOf(localObject2).length() + 69 + String.valueOf(localObject1).length());
        ((StringBuilder)localObject3).append("Error resolution was canceled by the user, original error message: ");
        ((StringBuilder)localObject3).append((String)localObject2);
        ((StringBuilder)localObject3).append(": ");
        ((StringBuilder)localObject3).append((String)localObject1);
        paramMessage.execute(new Status(17, ((StringBuilder)localObject3).toString()));
        return true;
      }
      paramMessage = new StringBuilder(76);
      paramMessage.append("Could not find API instance ");
      paramMessage.append(i);
      paramMessage.append(" while trying to fail enqueued calls.");
      Log.wtf("GoogleApiManager", paramMessage.toString(), new Exception());
      return true;
    case 4: 
    case 8: 
    case 13: 
      localObject2 = (zabv)obj;
      localObject1 = (zaa)zaih.get(zajs.get());
      paramMessage = (Message)localObject1;
      if (localObject1 == null)
      {
        getSources(zajs);
        paramMessage = (zaa)zaih.get(zajs.get());
      }
      if ((paramMessage.requiresSignIn()) && (zaig.get() != zajr))
      {
        zajq.toString(zahw);
        paramMessage.zabj();
        return true;
      }
      paramMessage.ls(zajq);
      return true;
    case 3: 
      paramMessage = zaih.values().iterator();
    case 2: 
    case 1: 
      while (paramMessage.hasNext())
      {
        localObject1 = (zaa)paramMessage.next();
        ((zaa)localObject1).zabl();
        ((zaa)localObject1).connect();
        continue;
        paramMessage = (LogItem)obj;
        localObject1 = paramMessage.getTypes().iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (Msg)((Iterator)localObject1).next();
          localObject3 = (zaa)zaih.get(localObject2);
          if (localObject3 == null)
          {
            paramMessage.setTimestamp((Msg)localObject2, new ConnectionResult(13), null);
            return true;
          }
          if (((zaa)localObject3).isConnected())
          {
            paramMessage.setTimestamp((Msg)localObject2, ConnectionResult.RESULT_SUCCESS, ((zaa)localObject3).zaab().getEndpointPackageName());
          }
          else if (((zaa)localObject3).zabm() != null)
          {
            paramMessage.setTimestamp((Msg)localObject2, ((zaa)localObject3).zabm(), null);
          }
          else
          {
            ((zaa)localObject3).setTags(paramMessage);
            ((zaa)localObject3).connect();
            continue;
            if (((Boolean)obj).booleanValue()) {
              l = 10000L;
            }
            zaia = l;
            handler.removeMessages(12);
            paramMessage = zaih.keySet().iterator();
            while (paramMessage.hasNext())
            {
              localObject1 = (Msg)paramMessage.next();
              handler.sendMessageDelayed(handler.obtainMessage(12, localObject1), zaia);
            }
          }
        }
      }
    }
    return true;
  }
  
  final boolean ignore(ConnectionResult paramConnectionResult, int paramInt)
  {
    return zaid.setCurrentTheme(zaic, paramConnectionResult, paramInt);
  }
  
  final void maybeSignOut()
  {
    zaig.incrementAndGet();
    handler.sendMessage(handler.obtainMessage(10));
  }
  
  public final void read(zaae paramZaae)
  {
    Object localObject = lock;
    try
    {
      if (zaii != paramZaae)
      {
        zaii = paramZaae;
        zaij.clear();
      }
      zaij.addAll(paramZaae.zaaj());
      return;
    }
    catch (Throwable paramZaae)
    {
      throw paramZaae;
    }
  }
  
  public final void respondWith(GoogleApi paramGoogleApi)
  {
    handler.sendMessage(handler.obtainMessage(7, paramGoogleApi));
  }
  
  final void setDisplayMode(zaae paramZaae)
  {
    Object localObject = lock;
    try
    {
      if (zaii == paramZaae)
      {
        zaii = null;
        zaij.clear();
      }
      return;
    }
    catch (Throwable paramZaae)
    {
      throw paramZaae;
    }
  }
  
  public final Task then(GoogleApi paramGoogleApi)
  {
    paramGoogleApi = new zaaf(paramGoogleApi.get());
    handler.sendMessage(handler.obtainMessage(14, paramGoogleApi));
    return paramGoogleApi.zaal().getTask();
  }
  
  public final Task then(GoogleApi paramGoogleApi, ListenerHolder.ListenerKey paramListenerKey)
  {
    TaskCompletionSource localTaskCompletionSource = new TaskCompletionSource();
    paramListenerKey = new TCharFloatHashMap.TValueView(paramListenerKey, localTaskCompletionSource);
    handler.sendMessage(handler.obtainMessage(13, new zabv(paramListenerKey, zaig.get(), paramGoogleApi)));
    return localTaskCompletionSource.getTask();
  }
  
  public final Task then(GoogleApi paramGoogleApi, RegisterListenerMethod paramRegisterListenerMethod, UnregisterListenerMethod paramUnregisterListenerMethod)
  {
    TaskCompletionSource localTaskCompletionSource = new TaskCompletionSource();
    paramRegisterListenerMethod = new TSynchronizedShortCollection(new zabw(paramRegisterListenerMethod, paramUnregisterListenerMethod), localTaskCompletionSource);
    handler.sendMessage(handler.obtainMessage(8, new zabv(paramRegisterListenerMethod, zaig.get(), paramGoogleApi)));
    return localTaskCompletionSource.getTask();
  }
  
  public final void write(GoogleApi paramGoogleApi, int paramInt, BaseImplementation.ApiMethodImpl paramApiMethodImpl)
  {
    paramApiMethodImpl = new Frame(paramInt, paramApiMethodImpl);
    handler.sendMessage(handler.obtainMessage(4, new zabv(paramApiMethodImpl, zaig.get(), paramGoogleApi)));
  }
  
  public final int zabd()
  {
    return zaif.getAndIncrement();
  }
  
  public final class zaa<O extends Api.ApiOptions>
    implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, zar
  {
    private final zai<O> zafp;
    private final Queue<zab> zaim = new LinkedList();
    private final Api.Client zain;
    private final Api.AnyClient zaio;
    private final zaab zaip;
    private final Set<zak> zaiq = new HashSet();
    private final Map<com.google.android.gms.common.api.internal.ListenerHolder.ListenerKey<?>, com.google.android.gms.common.api.internal.zabw> zair = new HashMap();
    private final int zais;
    private final zace zait;
    private boolean zaiu;
    private final List<com.google.android.gms.common.api.internal.GoogleApiManager.zab> zaiv = new ArrayList();
    private ConnectionResult zaiw = null;
    
    public zaa(GoogleApi paramGoogleApi)
    {
      zain = paramGoogleApi.showToast(GoogleApiManager.access$getHandler(GoogleApiManager.this).getLooper(), this);
      if ((zain instanceof SimpleClientAdapter)) {
        zaio = ((SimpleClientAdapter)zain).getClient();
      } else {
        zaio = zain;
      }
      zafp = paramGoogleApi.get();
      zaip = new zaab();
      zais = paramGoogleApi.getInstanceId();
      if (zain.requiresSignIn())
      {
        zait = paramGoogleApi.showToast(GoogleApiManager.resolve(GoogleApiManager.this), GoogleApiManager.access$getHandler(GoogleApiManager.this));
        return;
      }
      zait = null;
    }
    
    private final void doTransform(ConnectionResult paramConnectionResult)
    {
      Iterator localIterator = zaiq.iterator();
      while (localIterator.hasNext())
      {
        LogItem localLogItem = (LogItem)localIterator.next();
        String str = null;
        if (Objects.equal(paramConnectionResult, ConnectionResult.RESULT_SUCCESS)) {
          str = zain.getEndpointPackageName();
        }
        localLogItem.setTimestamp(zafp, paramConnectionResult, str);
      }
      zaiq.clear();
    }
    
    private final void execute(GoogleApiManager.zab paramZab)
    {
      if (zaiv.remove(paramZab))
      {
        GoogleApiManager.access$getHandler(GoogleApiManager.this).removeMessages(15, paramZab);
        GoogleApiManager.access$getHandler(GoogleApiManager.this).removeMessages(16, paramZab);
        paramZab = GoogleApiManager.zab.mapFileName(paramZab);
        ArrayList localArrayList = new ArrayList(zaim.size());
        Object localObject = zaim.iterator();
        while (((Iterator)localObject).hasNext())
        {
          Location localLocation = (Location)((Iterator)localObject).next();
          if ((localLocation instanceof Loader))
          {
            Feature[] arrayOfFeature = ((Loader)localLocation).toString(this);
            if ((arrayOfFeature != null) && (ArrayUtils.contains(arrayOfFeature, paramZab))) {
              localArrayList.add(localLocation);
            }
          }
        }
        localArrayList = (ArrayList)localArrayList;
        int j = localArrayList.size();
        int i = 0;
        while (i < j)
        {
          localObject = localArrayList.get(i);
          i += 1;
          localObject = (Location)localObject;
          zaim.remove(localObject);
          ((Location)localObject).toString(new UnsupportedApiCallException(paramZab));
        }
      }
    }
    
    private final boolean execute(Location paramLocation)
    {
      if (!(paramLocation instanceof Loader))
      {
        readFrom(paramLocation);
        return true;
      }
      Loader localLoader = (Loader)paramLocation;
      Feature localFeature = parse(localLoader.toString(this));
      if (localFeature == null)
      {
        readFrom(paramLocation);
        return true;
      }
      if (localLoader.isEmpty(this))
      {
        paramLocation = new GoogleApiManager.zab(zafp, localFeature, null);
        int i = zaiv.indexOf(paramLocation);
        if (i >= 0)
        {
          paramLocation = (GoogleApiManager.zab)zaiv.get(i);
          GoogleApiManager.access$getHandler(GoogleApiManager.this).removeMessages(15, paramLocation);
          GoogleApiManager.access$getHandler(GoogleApiManager.this).sendMessageDelayed(Message.obtain(GoogleApiManager.access$getHandler(GoogleApiManager.this), 15, paramLocation), GoogleApiManager.unwrap(GoogleApiManager.this));
        }
        else
        {
          zaiv.add(paramLocation);
          GoogleApiManager.access$getHandler(GoogleApiManager.this).sendMessageDelayed(Message.obtain(GoogleApiManager.access$getHandler(GoogleApiManager.this), 15, paramLocation), GoogleApiManager.unwrap(GoogleApiManager.this));
          GoogleApiManager.access$getHandler(GoogleApiManager.this).sendMessageDelayed(Message.obtain(GoogleApiManager.access$getHandler(GoogleApiManager.this), 16, paramLocation), GoogleApiManager.getPlainText(GoogleApiManager.this));
          paramLocation = new ConnectionResult(2, null);
          if (!putAll(paramLocation)) {
            ignore(paramLocation, zais);
          }
        }
      }
      else
      {
        localLoader.toString(new UnsupportedApiCallException(localFeature));
      }
      return false;
    }
    
    private final void getSources(GoogleApiManager.zab paramZab)
    {
      if (!zaiv.contains(paramZab)) {
        return;
      }
      if (!zaiu)
      {
        if (!zain.isConnected())
        {
          connect();
          return;
        }
        zabi();
      }
    }
    
    private final Feature parse(Feature[] paramArrayOfFeature)
    {
      if (paramArrayOfFeature != null)
      {
        if (paramArrayOfFeature.length == 0) {
          return null;
        }
        Object localObject2 = zain.getAvailableFeatures();
        Object localObject1 = localObject2;
        int j = 0;
        if (localObject2 == null) {
          localObject1 = new Feature[0];
        }
        localObject2 = new ArrayMap(localObject1.length);
        int k = localObject1.length;
        int i = 0;
        while (i < k)
        {
          Object localObject3 = localObject1[i];
          ((Map)localObject2).put(localObject3.getName(), Long.valueOf(localObject3.getVersion()));
          i += 1;
        }
        k = paramArrayOfFeature.length;
        i = j;
        while (i < k)
        {
          localObject1 = paramArrayOfFeature[i];
          if (((Map)localObject2).containsKey(((Feature)localObject1).getName()))
          {
            if (((Long)((Map)localObject2).get(((Feature)localObject1).getName())).longValue() < ((Feature)localObject1).getVersion()) {
              return localObject1;
            }
            i += 1;
          }
          else
          {
            return localObject1;
          }
        }
      }
      return null;
    }
    
    private final boolean putAll(ConnectionResult paramConnectionResult)
    {
      Object localObject = GoogleApiManager.zabe();
      try
      {
        if ((GoogleApiManager.cast(GoogleApiManager.this) != null) && (GoogleApiManager.access$getMProducts(GoogleApiManager.this).contains(zafp)))
        {
          GoogleApiManager.cast(GoogleApiManager.this).next(paramConnectionResult, zais);
          return true;
        }
        return false;
      }
      catch (Throwable paramConnectionResult)
      {
        throw paramConnectionResult;
      }
    }
    
    private final void readFrom(Location paramLocation)
    {
      paramLocation.readFrom(zaip, requiresSignIn());
      try
      {
        paramLocation.readFrom(this);
        return;
      }
      catch (DeadObjectException paramLocation)
      {
        for (;;) {}
      }
      onConnectionSuspended(1);
      zain.disconnect();
    }
    
    private final boolean updateConnection(boolean paramBoolean)
    {
      Preconditions.checkHandlerThread(GoogleApiManager.access$getHandler(GoogleApiManager.this));
      if ((zain.isConnected()) && (zair.size() == 0)) {
        if (zaip.zaag())
        {
          if (paramBoolean)
          {
            zabo();
            return false;
          }
        }
        else
        {
          zain.disconnect();
          return true;
        }
      }
      return false;
    }
    
    private final void zabg()
    {
      zabl();
      doTransform(ConnectionResult.RESULT_SUCCESS);
      zabn();
      Iterator localIterator = zair.values().iterator();
      while (localIterator.hasNext())
      {
        Object localObject = (zabw)localIterator.next();
        Api.AnyClient localAnyClient;
        if (parse(zajw.getRequiredFeatures()) != null)
        {
          localIterator.remove();
        }
        else
        {
          localObject = zajw;
          localAnyClient = zaio;
        }
        try
        {
          ((RegisterListenerMethod)localObject).registerListener(localAnyClient, new TaskCompletionSource());
        }
        catch (DeadObjectException localDeadObjectException)
        {
          for (;;) {}
        }
        catch (RemoteException localRemoteException)
        {
          for (;;) {}
        }
        localIterator.remove();
        continue;
        onConnectionSuspended(1);
        zain.disconnect();
      }
      zabi();
      zabo();
    }
    
    private final void zabh()
    {
      zabl();
      zaiu = true;
      zaip.zaai();
      GoogleApiManager.access$getHandler(GoogleApiManager.this).sendMessageDelayed(Message.obtain(GoogleApiManager.access$getHandler(GoogleApiManager.this), 9, zafp), GoogleApiManager.unwrap(GoogleApiManager.this));
      GoogleApiManager.access$getHandler(GoogleApiManager.this).sendMessageDelayed(Message.obtain(GoogleApiManager.access$getHandler(GoogleApiManager.this), 11, zafp), GoogleApiManager.getPlainText(GoogleApiManager.this));
      GoogleApiManager.getOutput(GoogleApiManager.this).flush();
    }
    
    private final void zabi()
    {
      ArrayList localArrayList = (ArrayList)new ArrayList(zaim);
      int k = localArrayList.size();
      int i = 0;
      while (i < k)
      {
        Object localObject = localArrayList.get(i);
        int j = i + 1;
        localObject = (Location)localObject;
        if (!zain.isConnected()) {
          break;
        }
        i = j;
        if (execute((Location)localObject))
        {
          zaim.remove(localObject);
          i = j;
        }
      }
    }
    
    private final void zabn()
    {
      if (zaiu)
      {
        GoogleApiManager.access$getHandler(GoogleApiManager.this).removeMessages(11, zafp);
        GoogleApiManager.access$getHandler(GoogleApiManager.this).removeMessages(9, zafp);
        zaiu = false;
      }
    }
    
    private final void zabo()
    {
      GoogleApiManager.access$getHandler(GoogleApiManager.this).removeMessages(12, zafp);
      GoogleApiManager.access$getHandler(GoogleApiManager.this).sendMessageDelayed(GoogleApiManager.access$getHandler(GoogleApiManager.this).obtainMessage(12, zafp), GoogleApiManager.getMetadataId(GoogleApiManager.this));
    }
    
    public final void connect()
    {
      Preconditions.checkHandlerThread(GoogleApiManager.access$getHandler(GoogleApiManager.this));
      if (!zain.isConnected())
      {
        if (zain.isConnecting()) {
          return;
        }
        int i = GoogleApiManager.getOutput(GoogleApiManager.this).getClientAvailability(GoogleApiManager.resolve(GoogleApiManager.this), zain);
        if (i != 0)
        {
          onConnectionFailed(new ConnectionResult(i, null));
          return;
        }
        GoogleApiManager.zac localZac = new GoogleApiManager.zac(GoogleApiManager.this, zain, zafp);
        if (zain.requiresSignIn()) {
          zait.stop(localZac);
        }
        zain.connect(localZac);
      }
    }
    
    public final void execute(Status paramStatus)
    {
      Preconditions.checkHandlerThread(GoogleApiManager.access$getHandler(GoogleApiManager.this));
      Iterator localIterator = zaim.iterator();
      while (localIterator.hasNext()) {
        ((Location)localIterator.next()).toString(paramStatus);
      }
      zaim.clear();
    }
    
    public final int getInstanceId()
    {
      return zais;
    }
    
    final boolean isConnected()
    {
      return zain.isConnected();
    }
    
    public final void ls(Location paramLocation)
    {
      Preconditions.checkHandlerThread(GoogleApiManager.access$getHandler(GoogleApiManager.this));
      if (zain.isConnected())
      {
        if (execute(paramLocation))
        {
          zabo();
          return;
        }
        zaim.add(paramLocation);
        return;
      }
      zaim.add(paramLocation);
      if ((zaiw != null) && (zaiw.hasResolution()))
      {
        onConnectionFailed(zaiw);
        return;
      }
      connect();
    }
    
    public final void onConnected(Bundle paramBundle)
    {
      if (Looper.myLooper() == GoogleApiManager.access$getHandler(GoogleApiManager.this).getLooper())
      {
        zabg();
        return;
      }
      GoogleApiManager.access$getHandler(GoogleApiManager.this).post(new zabj(this));
    }
    
    public final void onConnectionFailed(ConnectionResult paramConnectionResult)
    {
      Preconditions.checkHandlerThread(GoogleApiManager.access$getHandler(GoogleApiManager.this));
      if (zait != null) {
        zait.zabs();
      }
      zabl();
      GoogleApiManager.getOutput(GoogleApiManager.this).flush();
      doTransform(paramConnectionResult);
      if (paramConnectionResult.getErrorCode() == 4)
      {
        execute(GoogleApiManager.zabf());
        return;
      }
      if (zaim.isEmpty())
      {
        zaiw = paramConnectionResult;
        return;
      }
      if (putAll(paramConnectionResult)) {
        return;
      }
      if (!ignore(paramConnectionResult, zais))
      {
        if (paramConnectionResult.getErrorCode() == 18) {
          zaiu = true;
        }
        if (zaiu)
        {
          GoogleApiManager.access$getHandler(GoogleApiManager.this).sendMessageDelayed(Message.obtain(GoogleApiManager.access$getHandler(GoogleApiManager.this), 9, zafp), GoogleApiManager.unwrap(GoogleApiManager.this));
          return;
        }
        paramConnectionResult = zafp.get();
        StringBuilder localStringBuilder = new StringBuilder(String.valueOf(paramConnectionResult).length() + 38);
        localStringBuilder.append("API: ");
        localStringBuilder.append(paramConnectionResult);
        localStringBuilder.append(" is not available on this device.");
        execute(new Status(17, localStringBuilder.toString()));
      }
    }
    
    public final void onConnectionSuspended(int paramInt)
    {
      if (Looper.myLooper() == GoogleApiManager.access$getHandler(GoogleApiManager.this).getLooper())
      {
        zabh();
        return;
      }
      GoogleApiManager.access$getHandler(GoogleApiManager.this).post(new zabk(this));
    }
    
    public final void post(ConnectionResult paramConnectionResult, Sample paramSample, boolean paramBoolean)
    {
      if (Looper.myLooper() == GoogleApiManager.access$getHandler(GoogleApiManager.this).getLooper())
      {
        onConnectionFailed(paramConnectionResult);
        return;
      }
      GoogleApiManager.access$getHandler(GoogleApiManager.this).post(new zabl(this, paramConnectionResult));
    }
    
    public final void putChannel(ConnectionResult paramConnectionResult)
    {
      Preconditions.checkHandlerThread(GoogleApiManager.access$getHandler(GoogleApiManager.this));
      zain.disconnect();
      onConnectionFailed(paramConnectionResult);
    }
    
    public final boolean requiresSignIn()
    {
      return zain.requiresSignIn();
    }
    
    public final void resume()
    {
      Preconditions.checkHandlerThread(GoogleApiManager.access$getHandler(GoogleApiManager.this));
      if (zaiu) {
        connect();
      }
    }
    
    public final void setTags(LogItem paramLogItem)
    {
      Preconditions.checkHandlerThread(GoogleApiManager.access$getHandler(GoogleApiManager.this));
      zaiq.add(paramLogItem);
    }
    
    public final Api.Client zaab()
    {
      return zain;
    }
    
    public final void zaav()
    {
      Preconditions.checkHandlerThread(GoogleApiManager.access$getHandler(GoogleApiManager.this));
      if (zaiu)
      {
        zabn();
        Status localStatus;
        if (GoogleApiManager.get0(GoogleApiManager.this).isGooglePlayServicesAvailable(GoogleApiManager.resolve(GoogleApiManager.this)) == 18) {
          localStatus = new Status(8, "Connection timed out while waiting for Google Play services update to complete.");
        } else {
          localStatus = new Status(8, "API failed to connect while resuming due to an unknown error.");
        }
        execute(localStatus);
        zain.disconnect();
      }
    }
    
    public final void zabj()
    {
      Preconditions.checkHandlerThread(GoogleApiManager.access$getHandler(GoogleApiManager.this));
      execute(GoogleApiManager.zahw);
      zaip.zaah();
      ListenerHolder.ListenerKey[] arrayOfListenerKey = (ListenerHolder.ListenerKey[])zair.keySet().toArray(new ListenerHolder.ListenerKey[zair.size()]);
      int j = arrayOfListenerKey.length;
      int i = 0;
      while (i < j)
      {
        ls(new TCharFloatHashMap.TValueView(arrayOfListenerKey[i], new TaskCompletionSource()));
        i += 1;
      }
      doTransform(new ConnectionResult(4));
      if (zain.isConnected()) {
        zain.onUserSignOut(new zabm(this));
      }
    }
    
    public final Map zabk()
    {
      return zair;
    }
    
    public final void zabl()
    {
      Preconditions.checkHandlerThread(GoogleApiManager.access$getHandler(GoogleApiManager.this));
      zaiw = null;
    }
    
    public final ConnectionResult zabm()
    {
      Preconditions.checkHandlerThread(GoogleApiManager.access$getHandler(GoogleApiManager.this));
      return zaiw;
    }
    
    public final boolean zabp()
    {
      return updateConnection(true);
    }
    
    final Client zabq()
    {
      if (zait == null) {
        return null;
      }
      return zait.zabq();
    }
  }
  
  final class zab
  {
    private final Feature zajb;
    
    private zab(Feature paramFeature)
    {
      zajb = paramFeature;
    }
    
    public final boolean equals(Object paramObject)
    {
      if ((paramObject != null) && ((paramObject instanceof zab)))
      {
        paramObject = (zab)paramObject;
        if ((Objects.equal(GoogleApiManager.this, zaja)) && (Objects.equal(zajb, zajb))) {
          return true;
        }
      }
      return false;
    }
    
    public final int hashCode()
    {
      return Objects.hashCode(new Object[] { GoogleApiManager.this, zajb });
    }
    
    public final String toString()
    {
      return Objects.toStringHelper(this).add("key", GoogleApiManager.this).add("feature", zajb).toString();
    }
  }
  
  final class zac
    implements zach, BaseGmsClient.ConnectionProgressReportCallbacks
  {
    private final zai<?> zafp;
    private final Api.Client zain;
    private IAccountAccessor zajc = null;
    private Set<Scope> zajd = null;
    private boolean zaje = false;
    
    public zac(Api.Client paramClient, Msg paramMsg)
    {
      zain = paramClient;
      zafp = paramMsg;
    }
    
    private final void zabr()
    {
      if ((zaje) && (zajc != null)) {
        zain.getRemoteService(zajc, zajd);
      }
    }
    
    public final void ignore(ConnectionResult paramConnectionResult)
    {
      ((GoogleApiManager.zaa)GoogleApiManager.isIgnored(GoogleApiManager.this).get(zafp)).putChannel(paramConnectionResult);
    }
    
    public final void onReportServiceBinding(ConnectionResult paramConnectionResult)
    {
      GoogleApiManager.access$getHandler(GoogleApiManager.this).post(new zabo(this, paramConnectionResult));
    }
    
    public final void startSession(IAccountAccessor paramIAccountAccessor, Set paramSet)
    {
      if ((paramIAccountAccessor != null) && (paramSet != null))
      {
        zajc = paramIAccountAccessor;
        zajd = paramSet;
        zabr();
        return;
      }
      Log.wtf("GoogleApiManager", "Received null response from onSignInSuccess", new Exception());
      ignore(new ConnectionResult(4));
    }
  }
}
