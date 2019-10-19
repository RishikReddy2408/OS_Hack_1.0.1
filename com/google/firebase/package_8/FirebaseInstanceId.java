package com.google.firebase.package_8;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.android.common.util.concurrent.NamedThreadFactory;
import com.google.android.android.tasks.TaskCompletionSource;
import com.google.android.android.tasks.Tasks;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.firebase.DataCollectionDefaultChange;
import com.google.firebase.FirebaseApp;
import com.google.firebase.events.EventHandler;
import com.google.firebase.events.Subscriber;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.concurrent.GuardedBy;

public class FirebaseInstanceId
{
  private static final long zzai = TimeUnit.HOURS.toSeconds(8L);
  private static zzaw zzaj;
  @VisibleForTesting
  @GuardedBy("FirebaseInstanceId.class")
  private static ScheduledThreadPoolExecutor zzak;
  private final Executor zzal;
  private final FirebaseApp zzam;
  private final zzan zzan;
  private MessagingChannel zzao;
  private final zzaq zzap;
  private final zzba zzaq;
  @GuardedBy("this")
  private boolean zzar = false;
  private final zza zzas;
  
  FirebaseInstanceId(FirebaseApp paramFirebaseApp, Subscriber paramSubscriber)
  {
    this(paramFirebaseApp, new zzan(paramFirebaseApp.getApplicationContext()), Task.getExecutor(), Task.getExecutor(), paramSubscriber);
  }
  
  private FirebaseInstanceId(FirebaseApp paramFirebaseApp, zzan paramZzan, Executor paramExecutor1, Executor paramExecutor2, Subscriber paramSubscriber)
  {
    if (zzan.get(paramFirebaseApp) != null) {
      try
      {
        if (zzaj == null) {
          zzaj = new zzaw(paramFirebaseApp.getApplicationContext());
        }
        zzam = paramFirebaseApp;
        zzan = paramZzan;
        if (zzao == null)
        {
          MessagingChannel localMessagingChannel = (MessagingChannel)paramFirebaseApp.get(com.google.firebase.iid.MessagingChannel.class);
          if ((localMessagingChannel != null) && (localMessagingChannel.isAvailable())) {
            zzao = localMessagingChannel;
          } else {
            zzao = new Task.3(paramFirebaseApp, paramZzan, paramExecutor1);
          }
        }
        zzao = zzao;
        zzal = paramExecutor2;
        zzaq = new zzba(zzaj);
        zzas = new zza(paramSubscriber);
        zzap = new zzaq(paramExecutor1);
        if (!zzas.isEnabled()) {
          return;
        }
        formatString();
        return;
      }
      catch (Throwable paramFirebaseApp)
      {
        throw paramFirebaseApp;
      }
    }
    throw new IllegalStateException("FirebaseInstanceId failed to initialize, FirebaseApp is missing project ID");
  }
  
  private final com.google.android.android.tasks.Task call(String paramString1, String paramString2)
  {
    String str = getCode(paramString2);
    TaskCompletionSource localTaskCompletionSource = new TaskCompletionSource();
    zzal.execute(new IonBitmapRequestBuilder.1(this, paramString1, paramString2, localTaskCompletionSource, str));
    return localTaskCompletionSource.getTask();
  }
  
  private final void formatString()
  {
    zzax localZzax = getString();
    if ((!rm()) || (localZzax == null) || (localZzax.get(zzan.zzad())) || (zzaq.zzaq())) {
      startSync();
    }
  }
  
  private final Object get(com.google.android.android.tasks.Task paramTask)
    throws IOException
  {
    localObject = TimeUnit.MILLISECONDS;
    try
    {
      paramTask = Tasks.await(paramTask, 30000L, (TimeUnit)localObject);
      return paramTask;
    }
    catch (ExecutionException paramTask)
    {
      localObject = paramTask.getCause();
      if (!(localObject instanceof IOException)) {
        break label59;
      }
      if (!"INSTANCE_ID_RESET".equals(((Throwable)localObject).getMessage())) {
        break label54;
      }
      getFullPath();
      throw ((IOException)localObject);
      if (!(localObject instanceof RuntimeException)) {
        break label71;
      }
      throw ((RuntimeException)localObject);
      throw new IOException(paramTask);
    }
    catch (InterruptedException paramTask)
    {
      for (;;) {}
    }
    catch (TimeoutException paramTask)
    {
      for (;;) {}
    }
    throw new IOException("SERVICE_NOT_AVAILABLE");
  }
  
  static boolean get()
  {
    return (Log.isLoggable("FirebaseInstanceId", 3)) || ((Build.VERSION.SDK_INT == 23) && (Log.isLoggable("FirebaseInstanceId", 3)));
  }
  
  private static String getCode(String paramString)
  {
    if ((!paramString.isEmpty()) && (!paramString.equalsIgnoreCase("fcm")) && (!paramString.equalsIgnoreCase("gcm"))) {
      return paramString;
    }
    return "*";
  }
  
  public static FirebaseInstanceId getInstance()
  {
    return getInstance(FirebaseApp.getInstance());
  }
  
  public static FirebaseInstanceId getInstance(FirebaseApp paramFirebaseApp)
  {
    return (FirebaseInstanceId)paramFirebaseApp.get(com.google.firebase.iid.FirebaseInstanceId.class);
  }
  
  static void schedule(Runnable paramRunnable, long paramLong)
  {
    try
    {
      if (zzak == null) {
        zzak = new ScheduledThreadPoolExecutor(1, new NamedThreadFactory("FirebaseInstanceId"));
      }
      zzak.schedule(paramRunnable, paramLong, TimeUnit.SECONDS);
      return;
    }
    catch (Throwable paramRunnable)
    {
      throw paramRunnable;
    }
  }
  
  private static String sign()
  {
    return zzan.digest(zzaj.lookup("").getKeyPair());
  }
  
  private final void startSync()
  {
    try
    {
      if (!zzar) {
        blur(0L);
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  private static zzax toString(String paramString1, String paramString2)
  {
    return zzaj.get("", paramString1, paramString2);
  }
  
  final void blur(long paramLong)
  {
    try
    {
      long l = Math.min(Math.max(30L, paramLong << 1), zzai);
      schedule(new zzay(this, zzan, zzaq, l), paramLong);
      zzar = true;
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  final void blur(boolean paramBoolean)
  {
    try
    {
      zzar = paramBoolean;
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  final void createNote(String paramString)
    throws IOException
  {
    zzax localZzax = getString();
    if ((localZzax != null) && (!localZzax.get(zzan.zzad())))
    {
      String str = sign();
      get(zzao.unsubscribeFromTopic(str, zzbq, paramString));
      return;
    }
    throw new IOException("token not available");
  }
  
  public void deleteInstanceId()
    throws IOException
  {
    if (Looper.getMainLooper() != Looper.myLooper())
    {
      String str = sign();
      get(zzao.deleteInstanceId(str));
      getFullPath();
      return;
    }
    throw new IOException("MAIN_THREAD");
  }
  
  public void deleteToken(String paramString1, String paramString2)
    throws IOException
  {
    if (Looper.getMainLooper() != Looper.myLooper())
    {
      paramString2 = getCode(paramString2);
      String str1 = sign();
      String str2 = zzax.sign(toString(paramString1, paramString2));
      get(zzao.deleteToken(str1, str2, paramString1, paramString2));
      zzaj.write("", paramString1, paramString2);
      return;
    }
    throw new IOException("MAIN_THREAD");
  }
  
  final void deleteWorkout()
  {
    zzaj.delete("");
    startSync();
  }
  
  public final com.google.android.android.tasks.Task exchange(String paramString)
  {
    try
    {
      paramString = zzaq.execute(paramString);
      startSync();
      return paramString;
    }
    catch (Throwable paramString)
    {
      throw paramString;
    }
  }
  
  public long getCreationTime()
  {
    return zzaj.lookup("").getCreationTime();
  }
  
  final void getFullPath()
  {
    try
    {
      zzaj.zzal();
      if (zzas.isEnabled()) {
        startSync();
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public String getId()
  {
    formatString();
    return sign();
  }
  
  public com.google.android.android.tasks.Task getInstanceId()
  {
    return call(zzan.get(zzam), "*");
  }
  
  final FirebaseApp getMainActivity()
  {
    return zzam;
  }
  
  final zzax getString()
  {
    return toString(zzan.get(zzam), "*");
  }
  
  public String getToken()
  {
    zzax localZzax = getString();
    if ((localZzax == null) || (localZzax.get(zzan.zzad()))) {
      startSync();
    }
    if (localZzax != null) {
      return zzbq;
    }
    return null;
  }
  
  public String getToken(String paramString1, String paramString2)
    throws IOException
  {
    if (Looper.getMainLooper() != Looper.myLooper()) {
      return ((InstanceIdResult)get(call(paramString1, paramString2))).getToken();
    }
    throw new IOException("MAIN_THREAD");
  }
  
  public final boolean isAuthenticated()
  {
    return zzas.isEnabled();
  }
  
  final boolean isNetworkAvailable()
  {
    return zzao.isAvailable();
  }
  
  final String parseToken()
    throws IOException
  {
    return getToken(zzan.get(zzam), "*");
  }
  
  final void requestData()
    throws IOException
  {
    String str1 = sign();
    String str2 = zzax.sign(getString());
    get(zzao.buildChannel(str1, str2));
  }
  
  final boolean rm()
  {
    return zzao.isChannelBuilt();
  }
  
  public final void setSorting(boolean paramBoolean)
  {
    zzas.setEnabled(paramBoolean);
  }
  
  final void sign(String paramString)
    throws IOException
  {
    Object localObject = getString();
    if ((localObject != null) && (!((zzax)localObject).get(zzan.zzad())))
    {
      String str = sign();
      localObject = zzbq;
      get(zzao.subscribeToTopic(str, (String)localObject, paramString));
      return;
    }
    throw new IOException("token not available");
  }
  
  final class zza
  {
    private final boolean zzaz;
    private final Subscriber zzba;
    @Nullable
    @GuardedBy("this")
    private EventHandler<DataCollectionDefaultChange> zzbb;
    @Nullable
    @GuardedBy("this")
    private Boolean zzbc;
    
    zza(Subscriber paramSubscriber)
    {
      zzba = paramSubscriber;
      zzaz = start();
      zzbc = parse();
      if ((zzbc == null) && (zzaz))
      {
        zzbb = new ErrorHandler(this);
        paramSubscriber.subscribe(DataCollectionDefaultChange.class, zzbb);
      }
    }
    
    private final Boolean parse()
    {
      Object localObject1 = FirebaseInstanceId.elementAt(FirebaseInstanceId.this).getApplicationContext();
      Object localObject2 = ((Context)localObject1).getSharedPreferences("com.google.firebase.messaging", 0);
      if (((SharedPreferences)localObject2).contains("auto_init")) {
        return Boolean.valueOf(((SharedPreferences)localObject2).getBoolean("auto_init", false));
      }
      try
      {
        localObject2 = ((Context)localObject1).getPackageManager();
        if (localObject2 != null)
        {
          localObject1 = ((PackageManager)localObject2).getApplicationInfo(((Context)localObject1).getPackageName(), 128);
          if ((localObject1 != null) && (metaData != null))
          {
            localObject2 = metaData;
            boolean bool = ((Bundle)localObject2).containsKey("firebase_messaging_auto_init_enabled");
            if (bool)
            {
              localObject1 = metaData;
              bool = ((Bundle)localObject1).getBoolean("firebase_messaging_auto_init_enabled");
              return Boolean.valueOf(bool);
            }
          }
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        for (;;) {}
      }
      return null;
    }
    
    private final boolean start()
    {
      try
      {
        Class.forName("com.google.firebase.messaging.FirebaseMessaging");
        return true;
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        Object localObject;
        Intent localIntent;
        for (;;) {}
      }
      localObject = FirebaseInstanceId.elementAt(FirebaseInstanceId.this).getApplicationContext();
      localIntent = new Intent("com.google.firebase.MESSAGING_EVENT");
      localIntent.setPackage(((Context)localObject).getPackageName());
      localObject = ((Context)localObject).getPackageManager().resolveService(localIntent, 0);
      if (localObject != null)
      {
        if (serviceInfo != null) {
          return true;
        }
      }
      else {
        return false;
      }
      return false;
    }
    
    final boolean isEnabled()
    {
      try
      {
        boolean bool;
        if (zzbc != null)
        {
          bool = zzbc.booleanValue();
          return bool;
        }
        if (zzaz)
        {
          bool = FirebaseInstanceId.elementAt(FirebaseInstanceId.this).isDataCollectionDefaultEnabled();
          if (bool) {
            return true;
          }
        }
        return false;
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
    
    final void setEnabled(boolean paramBoolean)
    {
      try
      {
        if (zzbb != null)
        {
          zzba.unsubscribe(DataCollectionDefaultChange.class, zzbb);
          zzbb = null;
        }
        SharedPreferences.Editor localEditor = FirebaseInstanceId.elementAt(FirebaseInstanceId.this).getApplicationContext().getSharedPreferences("com.google.firebase.messaging", 0).edit();
        localEditor.putBoolean("auto_init", paramBoolean);
        localEditor.apply();
        if (paramBoolean) {
          FirebaseInstanceId.w(FirebaseInstanceId.this);
        }
        zzbc = Boolean.valueOf(paramBoolean);
        return;
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
  }
}
