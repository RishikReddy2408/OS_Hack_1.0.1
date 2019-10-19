package com.google.firebase;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.android.common.aimsicd.internal.BackgroundDetector;
import com.google.android.android.common.aimsicd.internal.BackgroundDetector.BackgroundStateChangeListener;
import com.google.android.android.common.internal.Objects;
import com.google.android.android.common.internal.Objects.ToStringHelper;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.common.util.Base64Utils;
import com.google.android.android.common.util.ProcessUtils;
import com.google.android.android.tasks.Task;
import com.google.android.android.tasks.Tasks;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.firebase.annotations.PublicApi;
import com.google.firebase.components.Component;
import com.google.firebase.components.Component.1;
import com.google.firebase.components.DOMParser;
import com.google.firebase.events.Event;
import com.google.firebase.events.Publisher;
import com.google.firebase.internal.InternalTokenProvider;
import com.google.firebase.internal.InternalTokenResult;
import com.google.firebase.internal.RealmObject;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.concurrent.GuardedBy;

@PublicApi
public class FirebaseApp
{
  private static final Object data = new Object();
  private static final Executor description = new zzb((byte)0);
  private static final List<String> mail;
  @GuardedBy("LOCK")
  static final Map<String, FirebaseApp> names = new ArrayMap();
  private static final Set<String> out;
  private static final List<String> props = Arrays.asList(new String[] { "com.google.firebase.auth.FirebaseAuth", "com.google.firebase.iid.FirebaseInstanceId" });
  private static final List<String> today;
  private static final List<String> types;
  private IdTokenListenersCountChangedListener busy;
  private final FirebaseOptions code;
  private final Context context;
  private InternalTokenProvider mAccount;
  private final Publisher mDirectory;
  private final SharedPreferences mPreferences;
  private final List<FirebaseAppLifecycleListener> metas = new CopyOnWriteArrayList();
  private final String name;
  private final AtomicBoolean packList;
  private final AtomicBoolean shutdown = new AtomicBoolean(false);
  private final AtomicBoolean state = new AtomicBoolean();
  private final List<BackgroundStateChangeListener> streams = new CopyOnWriteArrayList();
  private final List<IdTokenListener> texts = new CopyOnWriteArrayList();
  private final DOMParser this$0;
  
  static
  {
    mail = Collections.singletonList("com.google.firebase.crash.FirebaseCrash");
    today = Arrays.asList(new String[] { "com.google.android.gms.measurement.AppMeasurement" });
    types = Arrays.asList(new String[0]);
    out = Collections.emptySet();
  }
  
  private FirebaseApp(Context paramContext, String paramString, FirebaseOptions paramFirebaseOptions)
  {
    context = ((Context)Preconditions.checkNotNull(paramContext));
    name = Preconditions.checkNotEmpty(paramString);
    code = ((FirebaseOptions)Preconditions.checkNotNull(paramFirebaseOptions));
    busy = new RealmObject();
    mPreferences = paramContext.getSharedPreferences("com.google.firebase.common.prefs", 0);
    packList = new AtomicBoolean(get());
    paramString = Component.1.get(paramContext).get();
    this$0 = new DOMParser(description, paramString, new Component[] { Component.start(paramContext, Context.class, new Class[0]), Component.start(this, FirebaseApp.class, new Class[0]), Component.start(paramFirebaseOptions, FirebaseOptions.class, new Class[0]) });
    mDirectory = ((Publisher)this$0.get(Publisher.class));
  }
  
  private static void create(Class paramClass, Object paramObject, Iterable paramIterable, boolean paramBoolean)
  {
    Iterator localIterator = paramIterable.iterator();
    while (localIterator.hasNext())
    {
      paramIterable = (String)localIterator.next();
      List localList;
      if (paramBoolean) {
        localList = types;
      }
      try
      {
        try
        {
          bool = localList.contains(paramIterable);
          if (!bool) {
            continue;
          }
        }
        catch (IllegalAccessException localIllegalAccessException)
        {
          break label140;
        }
        catch (InvocationTargetException paramIterable)
        {
          break label176;
        }
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        boolean bool;
        Object localObject;
        int i;
        StringBuilder localStringBuilder;
        for (;;) {}
      }
      catch (NoSuchMethodException paramClass)
      {
        for (;;) {}
      }
      localObject = Class.forName(paramIterable);
      localObject = ((Class)localObject).getMethod("getInstance", new Class[] { paramClass });
      i = ((Method)localObject).getModifiers();
      bool = Modifier.isPublic(i);
      if (bool)
      {
        bool = Modifier.isStatic(i);
        if (bool)
        {
          ((Method)localObject).invoke(null, new Object[] { paramObject });
          continue;
          label140:
          localStringBuilder = new StringBuilder("Failed to initialize ");
          localStringBuilder.append(paramIterable);
          Log.wtf("FirebaseApp", localStringBuilder.toString(), (Throwable)localObject);
          continue;
          label176:
          Log.wtf("FirebaseApp", "Firebase API initialization failure.", paramIterable);
          continue;
          paramClass = new StringBuilder();
          paramClass.append(paramIterable);
          paramClass.append("#getInstance has been removed by Proguard. Add keep rule to prevent it.");
          throw new IllegalStateException(paramClass.toString());
          if (!out.contains(paramIterable))
          {
            localObject = new StringBuilder();
            ((StringBuilder)localObject).append(paramIterable);
            ((StringBuilder)localObject).append(" is not linked. Skipping initialization.");
            Log.d("FirebaseApp", ((StringBuilder)localObject).toString());
          }
          else
          {
            paramClass = new StringBuilder();
            paramClass.append(paramIterable);
            paramClass.append(" is missing, but is required. Check if it has been removed by Proguard.");
            throw new IllegalStateException(paramClass.toString());
          }
        }
      }
    }
  }
  
  private void doInBackground()
  {
    boolean bool = ContextCompat.isDeviceProtectedStorage(context);
    if (bool) {
      zzc.initialize(context);
    } else {
      this$0.validate(isDefaultApp());
    }
    create(FirebaseApp.class, this, props, bool);
    if (isDefaultApp())
    {
      create(FirebaseApp.class, this, mail, bool);
      create(Context.class, context, today, bool);
    }
  }
  
  private boolean get()
  {
    if (mPreferences.contains("firebase_data_collection_default_enabled")) {
      return mPreferences.getBoolean("firebase_data_collection_default_enabled", true);
    }
    Object localObject1 = context;
    try
    {
      localObject1 = ((Context)localObject1).getPackageManager();
      if (localObject1 != null)
      {
        Object localObject2 = context;
        localObject1 = ((PackageManager)localObject1).getApplicationInfo(((Context)localObject2).getPackageName(), 128);
        if ((localObject1 != null) && (metaData != null))
        {
          localObject2 = metaData;
          boolean bool = ((Bundle)localObject2).containsKey("firebase_data_collection_default_enabled");
          if (bool)
          {
            localObject1 = metaData;
            bool = ((Bundle)localObject1).getBoolean("firebase_data_collection_default_enabled");
            return bool;
          }
        }
      }
      else
      {
        return true;
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    return true;
  }
  
  public static List getApps(Context paramContext)
  {
    paramContext = data;
    try
    {
      ArrayList localArrayList = new ArrayList(names.values());
      return localArrayList;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public static FirebaseApp getInstance()
  {
    Object localObject1 = data;
    try
    {
      Object localObject2 = (FirebaseApp)names.get("[DEFAULT]");
      if (localObject2 != null) {
        return localObject2;
      }
      localObject2 = new StringBuilder("Default FirebaseApp is not initialized in this process ");
      ((StringBuilder)localObject2).append(ProcessUtils.getMyProcessName());
      ((StringBuilder)localObject2).append(". Make sure to call FirebaseApp.initializeApp(Context) first.");
      throw new IllegalStateException(((StringBuilder)localObject2).toString());
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public static FirebaseApp getInstance(String paramString)
  {
    Object localObject2 = data;
    try
    {
      Object localObject1 = (FirebaseApp)names.get(paramString.trim());
      if (localObject1 != null) {
        return localObject1;
      }
      localObject1 = getValue();
      if (((List)localObject1).isEmpty())
      {
        localObject1 = "";
      }
      else
      {
        StringBuilder localStringBuilder = new StringBuilder("Available app names: ");
        localStringBuilder.append(TextUtils.join(", ", (Iterable)localObject1));
        localObject1 = localStringBuilder.toString();
      }
      throw new IllegalStateException(String.format("FirebaseApp with name %s doesn't exist. %s", new Object[] { paramString, localObject1 }));
    }
    catch (Throwable paramString)
    {
      throw paramString;
    }
  }
  
  public static String getPersistenceKey(String paramString, FirebaseOptions paramFirebaseOptions)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(Base64Utils.encodeUrlSafeNoPadding(paramString.getBytes(Charset.defaultCharset())));
    localStringBuilder.append("+");
    localStringBuilder.append(Base64Utils.encodeUrlSafeNoPadding(paramFirebaseOptions.getApplicationId().getBytes(Charset.defaultCharset())));
    return localStringBuilder.toString();
  }
  
  private static List getValue()
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = data;
    try
    {
      Iterator localIterator = names.values().iterator();
      while (localIterator.hasNext()) {
        localArrayList.add(((FirebaseApp)localIterator.next()).getName());
      }
      Collections.sort(localArrayList);
      return localArrayList;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public static FirebaseApp initializeApp(Context paramContext)
  {
    Object localObject = data;
    try
    {
      if (names.containsKey("[DEFAULT]"))
      {
        paramContext = getInstance();
        return paramContext;
      }
      FirebaseOptions localFirebaseOptions = FirebaseOptions.fromResource(paramContext);
      if (localFirebaseOptions == null)
      {
        Log.d("FirebaseApp", "Default FirebaseApp failed to initialize because no default options were found. This usually means that com.google.gms:google-services was not applied to your gradle project.");
        return null;
      }
      paramContext = initializeApp(paramContext, localFirebaseOptions);
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      throw paramContext;
    }
  }
  
  public static FirebaseApp initializeApp(Context paramContext, FirebaseOptions paramFirebaseOptions)
  {
    return initializeApp(paramContext, paramFirebaseOptions, "[DEFAULT]");
  }
  
  public static FirebaseApp initializeApp(Context paramContext, FirebaseOptions paramFirebaseOptions, String paramString)
  {
    zza.initialize(paramContext);
    paramString = paramString.trim();
    if (paramContext.getApplicationContext() != null) {
      paramContext = paramContext.getApplicationContext();
    }
    Object localObject = data;
    try
    {
      boolean bool = names.containsKey(paramString);
      StringBuilder localStringBuilder = new StringBuilder("FirebaseApp name ");
      localStringBuilder.append(paramString);
      localStringBuilder.append(" already exists!");
      Preconditions.checkState(bool ^ true, localStringBuilder.toString());
      Preconditions.checkNotNull(paramContext, "Application context cannot be null.");
      paramContext = new FirebaseApp(paramContext, paramString, paramFirebaseOptions);
      names.put(paramString, paramContext);
      paramContext.doInBackground();
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      throw paramContext;
    }
  }
  
  private void setData(boolean paramBoolean)
  {
    Log.d("FirebaseApp", "Notifying background state change listeners.");
    Iterator localIterator = streams.iterator();
    while (localIterator.hasNext()) {
      ((BackgroundStateChangeListener)localIterator.next()).onBackgroundStateChanged(paramBoolean);
    }
  }
  
  private void visit()
  {
    Preconditions.checkState(state.get() ^ true, "FirebaseApp was deleted");
  }
  
  public void addBackgroundStateChangeListener(BackgroundStateChangeListener paramBackgroundStateChangeListener)
  {
    visit();
    if ((shutdown.get()) && (BackgroundDetector.getInstance().isInBackground())) {
      paramBackgroundStateChangeListener.onBackgroundStateChanged(true);
    }
    streams.add(paramBackgroundStateChangeListener);
  }
  
  public void addIdTokenListener(IdTokenListener paramIdTokenListener)
  {
    visit();
    Preconditions.checkNotNull(paramIdTokenListener);
    texts.add(paramIdTokenListener);
    busy.onListenerCountChanged(texts.size());
  }
  
  public void addLifecycleEventListener(FirebaseAppLifecycleListener paramFirebaseAppLifecycleListener)
  {
    visit();
    Preconditions.checkNotNull(paramFirebaseAppLifecycleListener);
    metas.add(paramFirebaseAppLifecycleListener);
  }
  
  public void delete()
  {
    if (!state.compareAndSet(false, true)) {
      return;
    }
    Object localObject = data;
    try
    {
      names.remove(name);
      localObject = metas.iterator();
      while (((Iterator)localObject).hasNext()) {
        ((Iterator)localObject).next();
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof FirebaseApp)) {
      return false;
    }
    return name.equals(((FirebaseApp)paramObject).getName());
  }
  
  public Object get(Class paramClass)
  {
    visit();
    return this$0.get(paramClass);
  }
  
  public Context getApplicationContext()
  {
    visit();
    return context;
  }
  
  public List getListeners()
  {
    visit();
    return texts;
  }
  
  public String getName()
  {
    visit();
    return name;
  }
  
  public FirebaseOptions getOptions()
  {
    visit();
    return code;
  }
  
  public String getPersistenceKey()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(Base64Utils.encodeUrlSafeNoPadding(getName().getBytes(Charset.defaultCharset())));
    localStringBuilder.append("+");
    localStringBuilder.append(Base64Utils.encodeUrlSafeNoPadding(getOptions().getApplicationId().getBytes(Charset.defaultCharset())));
    return localStringBuilder.toString();
  }
  
  public Task getToken(boolean paramBoolean)
  {
    visit();
    if (mAccount == null) {
      return Tasks.forException(new FirebaseApiNotAvailableException("firebase-auth is not linked, please fall back to unauthenticated mode."));
    }
    return mAccount.getAccessToken(paramBoolean);
  }
  
  public String getUid()
    throws FirebaseApiNotAvailableException
  {
    visit();
    if (mAccount != null) {
      return mAccount.getUid();
    }
    throw new FirebaseApiNotAvailableException("firebase-auth is not linked, please fall back to unauthenticated mode.");
  }
  
  public int hashCode()
  {
    return name.hashCode();
  }
  
  public boolean isDataCollectionDefaultEnabled()
  {
    visit();
    return packList.get();
  }
  
  public boolean isDefaultApp()
  {
    return "[DEFAULT]".equals(getName());
  }
  
  public void notifyIdTokenListeners(InternalTokenResult paramInternalTokenResult)
  {
    Log.d("FirebaseApp", "Notifying auth state listeners.");
    Iterator localIterator = texts.iterator();
    int i = 0;
    while (localIterator.hasNext())
    {
      ((IdTokenListener)localIterator.next()).onIdTokenChanged(paramInternalTokenResult);
      i += 1;
    }
    Log.d("FirebaseApp", String.format("Notified %d auth state listeners.", new Object[] { Integer.valueOf(i) }));
  }
  
  public void removeBackgroundStateChangeListener(BackgroundStateChangeListener paramBackgroundStateChangeListener)
  {
    visit();
    streams.remove(paramBackgroundStateChangeListener);
  }
  
  public void removeIdTokenListener(IdTokenListener paramIdTokenListener)
  {
    visit();
    Preconditions.checkNotNull(paramIdTokenListener);
    texts.remove(paramIdTokenListener);
    busy.onListenerCountChanged(texts.size());
  }
  
  public void removeLifecycleEventListener(FirebaseAppLifecycleListener paramFirebaseAppLifecycleListener)
  {
    visit();
    Preconditions.checkNotNull(paramFirebaseAppLifecycleListener);
    metas.remove(paramFirebaseAppLifecycleListener);
  }
  
  public void setAutomaticResourceManagementEnabled(boolean paramBoolean)
  {
    visit();
    if (shutdown.compareAndSet(paramBoolean ^ true, paramBoolean))
    {
      boolean bool = BackgroundDetector.getInstance().isInBackground();
      if ((paramBoolean) && (bool))
      {
        setData(true);
        return;
      }
      if ((!paramBoolean) && (bool)) {
        setData(false);
      }
    }
  }
  
  public void setDataCollectionDefaultEnabled(boolean paramBoolean)
  {
    visit();
    if (packList.compareAndSet(paramBoolean ^ true, paramBoolean))
    {
      mPreferences.edit().putBoolean("firebase_data_collection_default_enabled", paramBoolean).commit();
      mDirectory.publish(new Event(DataCollectionDefaultChange.class, new DataCollectionDefaultChange(paramBoolean)));
    }
  }
  
  public void setIdTokenListenersCountChangedListener(IdTokenListenersCountChangedListener paramIdTokenListenersCountChangedListener)
  {
    busy = ((IdTokenListenersCountChangedListener)Preconditions.checkNotNull(paramIdTokenListenersCountChangedListener));
    busy.onListenerCountChanged(texts.size());
  }
  
  public void setTokenProvider(InternalTokenProvider paramInternalTokenProvider)
  {
    mAccount = ((InternalTokenProvider)Preconditions.checkNotNull(paramInternalTokenProvider));
  }
  
  public String toString()
  {
    return Objects.toStringHelper(this).add("name", name).add("options", code).toString();
  }
  
  @KeepForSdk
  public static abstract interface BackgroundStateChangeListener
  {
    public abstract void onBackgroundStateChanged(boolean paramBoolean);
  }
  
  @Deprecated
  @KeepForSdk
  public static abstract interface IdTokenListener
  {
    public abstract void onIdTokenChanged(InternalTokenResult paramInternalTokenResult);
  }
  
  @Deprecated
  @KeepForSdk
  public static abstract interface IdTokenListenersCountChangedListener
  {
    public abstract void onListenerCountChanged(int paramInt);
  }
  
  @TargetApi(14)
  static final class zza
    implements BackgroundDetector.BackgroundStateChangeListener
  {
    private static AtomicReference<zza> pool = new AtomicReference();
    
    private zza() {}
    
    public final void onBackgroundStateChanged(boolean paramBoolean)
    {
      Object localObject = FirebaseApp.getCommand();
      try
      {
        Iterator localIterator = new ArrayList(FirebaseApp.names.values()).iterator();
        while (localIterator.hasNext())
        {
          FirebaseApp localFirebaseApp = (FirebaseApp)localIterator.next();
          if (FirebaseApp.access$getShutdown(localFirebaseApp).get()) {
            FirebaseApp.showPhoto(localFirebaseApp, paramBoolean);
          }
        }
        return;
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
  }
  
  static final class zzb
    implements Executor
  {
    private static final Handler mainHandler = new Handler(Looper.getMainLooper());
    
    private zzb() {}
    
    public final void execute(Runnable paramRunnable)
    {
      mainHandler.post(paramRunnable);
    }
  }
  
  @TargetApi(24)
  static final class zzc
    extends BroadcastReceiver
  {
    private static AtomicReference<zzc> pool = new AtomicReference();
    private final Context this$0;
    
    private zzc(Context paramContext)
    {
      this$0 = paramContext;
    }
    
    public final void onReceive(Context paramContext, Intent paramIntent)
    {
      paramContext = FirebaseApp.getCommand();
      try
      {
        paramIntent = FirebaseApp.names.values().iterator();
        while (paramIntent.hasNext()) {
          FirebaseApp.onPackageAdded((FirebaseApp)paramIntent.next());
        }
        this$0.unregisterReceiver(this);
        return;
      }
      catch (Throwable paramIntent)
      {
        throw paramIntent;
      }
    }
  }
}
