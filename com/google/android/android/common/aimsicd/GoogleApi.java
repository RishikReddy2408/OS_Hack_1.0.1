package com.google.android.android.common.aimsicd;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Handler;
import android.os.Looper;
import com.google.android.android.auth.params.signin.GoogleSignInAccount;
import com.google.android.android.common.aimsicd.internal.ApiExceptionMapper;
import com.google.android.android.common.aimsicd.internal.BaseImplementation.ApiMethodImpl;
import com.google.android.android.common.aimsicd.internal.BasePendingResult;
import com.google.android.android.common.aimsicd.internal.GoogleApiManager;
import com.google.android.android.common.aimsicd.internal.GoogleApiManager.zaa;
import com.google.android.android.common.aimsicd.internal.ListenerHolder;
import com.google.android.android.common.aimsicd.internal.ListenerHolder.ListenerKey;
import com.google.android.android.common.aimsicd.internal.ListenerHolders;
import com.google.android.android.common.aimsicd.internal.Msg;
import com.google.android.android.common.aimsicd.internal.RegisterListenerMethod;
import com.google.android.android.common.aimsicd.internal.RegistrationMethods;
import com.google.android.android.common.aimsicd.internal.StatusExceptionMapper;
import com.google.android.android.common.aimsicd.internal.TaskApiCall;
import com.google.android.android.common.aimsicd.internal.UnregisterListenerMethod;
import com.google.android.android.common.aimsicd.internal.zaae;
import com.google.android.android.common.aimsicd.internal.zabp;
import com.google.android.android.common.aimsicd.internal.zace;
import com.google.android.android.common.internal.ClientSettings;
import com.google.android.android.common.internal.ClientSettings.Builder;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.tasks.Task;
import com.google.android.android.tasks.TaskCompletionSource;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.zai;
import java.util.Collection;
import java.util.Collections;

@KeepForSdk
public class GoogleApi<O extends com.google.android.gms.common.api.Api.ApiOptions>
{
  private final Api<O> mApi;
  private final Context mContext;
  private final int mInstanceId;
  private final O zabh;
  private final zai<O> zabi;
  private final Looper zabj;
  private final GoogleApiClient zabk;
  private final StatusExceptionMapper zabl;
  protected final GoogleApiManager zabm;
  
  public GoogleApi(Activity paramActivity, Sample paramSample, Api.ApiOptions paramApiOptions, Settings paramSettings)
  {
    Preconditions.checkNotNull(paramActivity, "Null activity is not permitted.");
    Preconditions.checkNotNull(paramSample, "Api must not be null.");
    Preconditions.checkNotNull(paramSettings, "Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
    mContext = paramActivity.getApplicationContext();
    mApi = paramSample;
    zabh = paramApiOptions;
    zabj = zabo;
    zabi = Msg.readMessage(mApi, zabh);
    zabk = new zabp(this);
    zabm = GoogleApiManager.open(mContext);
    mInstanceId = zabm.zabd();
    zabl = zabn;
    if (!(paramActivity instanceof GoogleApiActivity)) {
      zaae.doIt(paramActivity, zabm, zabi);
    }
    zabm.respondWith(this);
  }
  
  public GoogleApi(Activity paramActivity, Sample paramSample, Api.ApiOptions paramApiOptions, StatusExceptionMapper paramStatusExceptionMapper)
  {
    this(paramActivity, paramSample, paramApiOptions, new GoogleApi.Settings.Builder().setMapper(paramStatusExceptionMapper).setLooper(paramActivity.getMainLooper()).build());
  }
  
  protected GoogleApi(Context paramContext, Sample paramSample, Looper paramLooper)
  {
    Preconditions.checkNotNull(paramContext, "Null context is not permitted.");
    Preconditions.checkNotNull(paramSample, "Api must not be null.");
    Preconditions.checkNotNull(paramLooper, "Looper must not be null.");
    mContext = paramContext.getApplicationContext();
    mApi = paramSample;
    zabh = null;
    zabj = paramLooper;
    zabi = Msg.readMessage(paramSample);
    zabk = new zabp(this);
    zabm = GoogleApiManager.open(mContext);
    mInstanceId = zabm.zabd();
    zabl = new ApiExceptionMapper();
  }
  
  public GoogleApi(Context paramContext, Sample paramSample, Api.ApiOptions paramApiOptions, Looper paramLooper, StatusExceptionMapper paramStatusExceptionMapper)
  {
    this(paramContext, paramSample, paramApiOptions, new GoogleApi.Settings.Builder().setLooper(paramLooper).setMapper(paramStatusExceptionMapper).build());
  }
  
  public GoogleApi(Context paramContext, Sample paramSample, Api.ApiOptions paramApiOptions, Settings paramSettings)
  {
    Preconditions.checkNotNull(paramContext, "Null context is not permitted.");
    Preconditions.checkNotNull(paramSample, "Api must not be null.");
    Preconditions.checkNotNull(paramSettings, "Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
    mContext = paramContext.getApplicationContext();
    mApi = paramSample;
    zabh = paramApiOptions;
    zabj = zabo;
    zabi = Msg.readMessage(mApi, zabh);
    zabk = new zabp(this);
    zabm = GoogleApiManager.open(mContext);
    mInstanceId = zabm.zabd();
    zabl = zabn;
    zabm.respondWith(this);
  }
  
  public GoogleApi(Context paramContext, Sample paramSample, Api.ApiOptions paramApiOptions, StatusExceptionMapper paramStatusExceptionMapper)
  {
    this(paramContext, paramSample, paramApiOptions, new GoogleApi.Settings.Builder().setMapper(paramStatusExceptionMapper).build());
  }
  
  private final BaseImplementation.ApiMethodImpl putBoolean(int paramInt, BaseImplementation.ApiMethodImpl paramApiMethodImpl)
  {
    paramApiMethodImpl.put();
    zabm.write(this, paramInt, paramApiMethodImpl);
    return paramApiMethodImpl;
  }
  
  private final Task putInt(int paramInt, TaskApiCall paramTaskApiCall)
  {
    TaskCompletionSource localTaskCompletionSource = new TaskCompletionSource();
    zabm.clear(this, paramInt, paramTaskApiCall, localTaskCompletionSource, zabl);
    return localTaskCompletionSource.getTask();
  }
  
  public GoogleApiClient asGoogleApiClient()
  {
    return zabk;
  }
  
  protected ClientSettings.Builder createClientSettingsBuilder()
  {
    ClientSettings.Builder localBuilder = new ClientSettings.Builder();
    if ((zabh instanceof Api.ApiOptions.HasGoogleSignInAccountOptions))
    {
      localObject = ((Api.ApiOptions.HasGoogleSignInAccountOptions)zabh).getGoogleSignInAccount();
      if (localObject != null)
      {
        localObject = ((GoogleSignInAccount)localObject).getAccount();
        break label71;
      }
    }
    if ((zabh instanceof Api.ApiOptions.HasAccountOptions)) {
      localObject = ((Api.ApiOptions.HasAccountOptions)zabh).getAccount();
    } else {
      localObject = null;
    }
    label71:
    localBuilder = localBuilder.setAccount((Account)localObject);
    if ((zabh instanceof Api.ApiOptions.HasGoogleSignInAccountOptions))
    {
      localObject = ((Api.ApiOptions.HasGoogleSignInAccountOptions)zabh).getGoogleSignInAccount();
      if (localObject != null)
      {
        localObject = ((GoogleSignInAccount)localObject).getRequestedScopes();
        break label116;
      }
    }
    Object localObject = Collections.emptySet();
    label116:
    return localBuilder.addAllRequiredScopes((Collection)localObject).setRealClientClassName(mContext.getClass().getName()).setRealClientPackageName(mContext.getPackageName());
  }
  
  protected Task disconnectService()
  {
    return zabm.then(this);
  }
  
  public BaseImplementation.ApiMethodImpl doBestEffortWrite(BaseImplementation.ApiMethodImpl paramApiMethodImpl)
  {
    return putBoolean(2, paramApiMethodImpl);
  }
  
  public Task doBestEffortWrite(TaskApiCall paramTaskApiCall)
  {
    return putInt(2, paramTaskApiCall);
  }
  
  public BaseImplementation.ApiMethodImpl doRead(BaseImplementation.ApiMethodImpl paramApiMethodImpl)
  {
    return putBoolean(0, paramApiMethodImpl);
  }
  
  public Task doRead(TaskApiCall paramTaskApiCall)
  {
    return putInt(0, paramTaskApiCall);
  }
  
  public Task doRegisterEventListener(RegisterListenerMethod paramRegisterListenerMethod, UnregisterListenerMethod paramUnregisterListenerMethod)
  {
    Preconditions.checkNotNull(paramRegisterListenerMethod);
    Preconditions.checkNotNull(paramUnregisterListenerMethod);
    Preconditions.checkNotNull(paramRegisterListenerMethod.getListenerKey(), "Listener has already been released.");
    Preconditions.checkNotNull(paramUnregisterListenerMethod.getListenerKey(), "Listener has already been released.");
    Preconditions.checkArgument(paramRegisterListenerMethod.getListenerKey().equals(paramUnregisterListenerMethod.getListenerKey()), "Listener registration and unregistration methods must be constructed with the same ListenerHolder.");
    return zabm.then(this, paramRegisterListenerMethod, paramUnregisterListenerMethod);
  }
  
  public Task doRegisterEventListener(RegistrationMethods paramRegistrationMethods)
  {
    Preconditions.checkNotNull(paramRegistrationMethods);
    Preconditions.checkNotNull(zajy.getListenerKey(), "Listener has already been released.");
    Preconditions.checkNotNull(zajz.getListenerKey(), "Listener has already been released.");
    return zabm.then(this, zajy, zajz);
  }
  
  public Task doUnregisterEventListener(ListenerHolder.ListenerKey paramListenerKey)
  {
    Preconditions.checkNotNull(paramListenerKey, "Listener key cannot be null.");
    return zabm.then(this, paramListenerKey);
  }
  
  public BaseImplementation.ApiMethodImpl doWrite(BaseImplementation.ApiMethodImpl paramApiMethodImpl)
  {
    return putBoolean(1, paramApiMethodImpl);
  }
  
  public Task doWrite(TaskApiCall paramTaskApiCall)
  {
    return putInt(1, paramTaskApiCall);
  }
  
  public final Msg get()
  {
    return zabi;
  }
  
  public final Sample getApi()
  {
    return mApi;
  }
  
  public Api.ApiOptions getApiOptions()
  {
    return zabh;
  }
  
  public Context getApplicationContext()
  {
    return mContext;
  }
  
  public final int getInstanceId()
  {
    return mInstanceId;
  }
  
  public Looper getLooper()
  {
    return zabj;
  }
  
  public ListenerHolder registerListener(Object paramObject, String paramString)
  {
    return ListenerHolders.createListenerHolder(paramObject, zabj, paramString);
  }
  
  public Api.Client showToast(Looper paramLooper, GoogleApiManager.zaa paramZaa)
  {
    ClientSettings localClientSettings = createClientSettingsBuilder().build();
    return mApi.start().buildClient(mContext, paramLooper, localClientSettings, zabh, paramZaa, paramZaa);
  }
  
  public zace showToast(Context paramContext, Handler paramHandler)
  {
    return new zace(paramContext, paramHandler, createClientSettingsBuilder().build());
  }
  
  @KeepForSdk
  public class Settings
  {
    @KeepForSdk
    public static final Settings DEFAULT_SETTINGS = new Builder().build();
    public final Looper zabo;
    
    private Settings(Account paramAccount, Looper paramLooper)
    {
      zabo = paramLooper;
    }
    
    @KeepForSdk
    public class Builder
    {
      private Looper zabj;
      private StatusExceptionMapper zabl;
      
      public Builder() {}
      
      public GoogleApi.Settings build()
      {
        if (zabl == null) {
          zabl = new ApiExceptionMapper();
        }
        if (zabj == null) {
          zabj = Looper.getMainLooper();
        }
        return new GoogleApi.Settings(zabl, null, zabj, null);
      }
      
      public Builder setLooper(Looper paramLooper)
      {
        Preconditions.checkNotNull(paramLooper, "Looper must not be null.");
        zabj = paramLooper;
        return this;
      }
      
      public Builder setMapper(StatusExceptionMapper paramStatusExceptionMapper)
      {
        Preconditions.checkNotNull(paramStatusExceptionMapper, "StatusExceptionMapper must not be null.");
        zabl = paramStatusExceptionMapper;
        return this;
      }
    }
  }
}
