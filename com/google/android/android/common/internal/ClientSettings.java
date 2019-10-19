package com.google.android.android.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.support.v4.util.ArraySet;
import android.view.View;
import com.google.android.android.common.aimsicd.GoogleApiClient.Builder;
import com.google.android.android.common.aimsicd.Sample;
import com.google.android.android.signin.SignInOptions;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@KeepForSdk
@VisibleForTesting
public final class ClientSettings
{
  public static final String KEY_CLIENT_SESSION_ID = "com.google.android.gms.common.internal.ClientSettings.sessionId";
  private final Account account;
  private final Set<com.google.android.gms.common.api.Scope> zabr;
  private final int zabt;
  private final View zabu;
  private final String zabv;
  private final String zabw;
  private final Set<com.google.android.gms.common.api.Scope> zaoa;
  private final Map<Api<?>, com.google.android.gms.common.internal.ClientSettings.OptionalApiSettings> zaob;
  private final SignInOptions zaoc;
  private Integer zaod;
  
  public ClientSettings(Account paramAccount, Set paramSet, Map paramMap, int paramInt, View paramView, String paramString1, String paramString2, SignInOptions paramSignInOptions)
  {
    account = paramAccount;
    if (paramSet == null) {
      paramAccount = Collections.EMPTY_SET;
    } else {
      paramAccount = Collections.unmodifiableSet(paramSet);
    }
    zabr = paramAccount;
    paramAccount = paramMap;
    if (paramMap == null) {
      paramAccount = Collections.EMPTY_MAP;
    }
    zaob = paramAccount;
    zabu = paramView;
    zabt = paramInt;
    zabv = paramString1;
    zabw = paramString2;
    zaoc = paramSignInOptions;
    paramAccount = new HashSet(zabr);
    paramSet = zaob.values().iterator();
    while (paramSet.hasNext()) {
      paramAccount.addAll(nextmScopes);
    }
    zaoa = Collections.unmodifiableSet(paramAccount);
  }
  
  public static ClientSettings createDefault(Context paramContext)
  {
    return new GoogleApiClient.Builder(paramContext).buildClientSettings();
  }
  
  public final Account getAccount()
  {
    return account;
  }
  
  public final String getAccountName()
  {
    if (account != null) {
      return account.name;
    }
    return null;
  }
  
  public final Account getAccountOrDefault()
  {
    if (account != null) {
      return account;
    }
    return new Account("<<default account>>", "com.google");
  }
  
  public final Set getAllRequestedScopes()
  {
    return zaoa;
  }
  
  public final Set getApplicableScopes(Sample paramSample)
  {
    paramSample = (OptionalApiSettings)zaob.get(paramSample);
    if ((paramSample != null) && (!mScopes.isEmpty()))
    {
      HashSet localHashSet = new HashSet(zabr);
      localHashSet.addAll(mScopes);
      return localHashSet;
    }
    return zabr;
  }
  
  public final Integer getClientSessionId()
  {
    return zaod;
  }
  
  public final int getGravityForPopups()
  {
    return zabt;
  }
  
  public final Map getOptionalApiSettings()
  {
    return zaob;
  }
  
  public final String getRealClientClassName()
  {
    return zabw;
  }
  
  public final String getRealClientPackageName()
  {
    return zabv;
  }
  
  public final Set getRequiredScopes()
  {
    return zabr;
  }
  
  public final SignInOptions getSignInOptions()
  {
    return zaoc;
  }
  
  public final View getViewForPopups()
  {
    return zabu;
  }
  
  public final void setClientSessionId(Integer paramInteger)
  {
    zaod = paramInteger;
  }
  
  @KeepForSdk
  public final class Builder
  {
    private Account mCurrentAccount;
    private int zabt = 0;
    private View zabu;
    private String zabv;
    private String zabw;
    private Map<Api<?>, com.google.android.gms.common.internal.ClientSettings.OptionalApiSettings> zaob;
    private SignInOptions zaoc = SignInOptions.DEFAULT;
    private ArraySet<com.google.android.gms.common.api.Scope> zaoe;
    
    public Builder() {}
    
    public final Builder addAllRequiredScopes(Collection paramCollection)
    {
      if (zaoe == null) {
        zaoe = new ArraySet();
      }
      zaoe.addAll(paramCollection);
      return this;
    }
    
    public final Builder addRequiredScope(com.google.android.android.common.aimsicd.Scope paramScope)
    {
      if (zaoe == null) {
        zaoe = new ArraySet();
      }
      zaoe.add(paramScope);
      return this;
    }
    
    public final ClientSettings build()
    {
      return new ClientSettings(mCurrentAccount, zaoe, zaob, zabt, zabu, zabv, zabw, zaoc);
    }
    
    public final Builder setAccount(Account paramAccount)
    {
      mCurrentAccount = paramAccount;
      return this;
    }
    
    public final Builder setGravityForPopups(int paramInt)
    {
      zabt = paramInt;
      return this;
    }
    
    public final Builder setOptionalApiSettingsMap(Map paramMap)
    {
      zaob = paramMap;
      return this;
    }
    
    public final Builder setRealClientClassName(String paramString)
    {
      zabw = paramString;
      return this;
    }
    
    public final Builder setRealClientPackageName(String paramString)
    {
      zabv = paramString;
      return this;
    }
    
    public final Builder setSignInOptions(SignInOptions paramSignInOptions)
    {
      zaoc = paramSignInOptions;
      return this;
    }
    
    public final Builder setViewForPopups(View paramView)
    {
      zabu = paramView;
      return this;
    }
  }
  
  public final class OptionalApiSettings
  {
    public final Set<com.google.android.gms.common.api.Scope> mScopes;
    
    public OptionalApiSettings()
    {
      Preconditions.checkNotNull(this$1);
      mScopes = Collections.unmodifiableSet(this$1);
    }
  }
}
