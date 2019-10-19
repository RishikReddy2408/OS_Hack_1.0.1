package com.google.android.android.auth.params.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.android.auth.params.signin.internal.HashAccumulator;
import com.google.android.android.common.aimsicd.Api.ApiOptions.Optional;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.common.internal.ReflectedParcelable;
import com.google.android.android.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.android.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SafeParcelable.Class(creator="GoogleSignInOptionsCreator")
public class GoogleSignInOptions
  extends AbstractSafeParcelable
  implements Api.ApiOptions.Optional, ReflectedParcelable
{
  @VisibleForTesting
  public static final com.google.android.android.common.aimsicd.Scope CACHE = new com.google.android.android.common.aimsicd.Scope("profile");
  public static final Parcelable.Creator<com.google.android.gms.auth.api.signin.GoogleSignInOptions> CREATOR = new DiscreteSeekBar.CustomState.1();
  public static final GoogleSignInOptions DEFAULT_GAMES_SIGN_IN;
  public static final GoogleSignInOptions DEFAULT_SIGN_IN;
  @VisibleForTesting
  public static final com.google.android.android.common.aimsicd.Scope EMAIL;
  @VisibleForTesting
  public static final com.google.android.android.common.aimsicd.Scope EXCLUSIVE;
  @VisibleForTesting
  public static final com.google.android.android.common.aimsicd.Scope LASTFM = new com.google.android.android.common.aimsicd.Scope("email");
  @VisibleForTesting
  public static final com.google.android.android.common.aimsicd.Scope LOCAL = new com.google.android.android.common.aimsicd.Scope("openid");
  private static Comparator<com.google.android.gms.common.api.Scope> zaaf = new NamespaceStack.1();
  @SafeParcelable.Field(getter="getAccount", id=3)
  private Account account;
  @SafeParcelable.Field(getter="getScopes", id=2)
  private final ArrayList<com.google.android.gms.common.api.Scope> data;
  @SafeParcelable.Field(getter="isServerAuthCodeRequested", id=5)
  private final boolean set;
  @SafeParcelable.Field(getter="isIdTokenRequested", id=4)
  private boolean type;
  @SafeParcelable.VersionField(id=1)
  private final int versionCode;
  @SafeParcelable.Field(getter="isForceCodeForRefreshToken", id=6)
  private final boolean zaaa;
  @SafeParcelable.Field(getter="getServerClientId", id=7)
  private String zaab;
  @SafeParcelable.Field(getter="getHostedDomain", id=8)
  private String zaac;
  @SafeParcelable.Field(getter="getExtensions", id=9)
  private ArrayList<com.google.android.gms.auth.api.signin.internal.GoogleSignInOptionsExtensionParcelable> zaad;
  private Map<Integer, com.google.android.gms.auth.api.signin.internal.GoogleSignInOptionsExtensionParcelable> zaae;
  
  static
  {
    EXCLUSIVE = new com.google.android.android.common.aimsicd.Scope("https://www.googleapis.com/auth/games_lite");
    EMAIL = new com.google.android.android.common.aimsicd.Scope("https://www.googleapis.com/auth/games");
    DEFAULT_SIGN_IN = new Builder().requestId().requestProfile().build();
    DEFAULT_GAMES_SIGN_IN = new Builder().requestScopes(EXCLUSIVE, new com.google.android.android.common.aimsicd.Scope[0]).build();
  }
  
  GoogleSignInOptions(int paramInt, ArrayList paramArrayList1, Account paramAccount, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, String paramString1, String paramString2, ArrayList paramArrayList2)
  {
    this(paramInt, paramArrayList1, paramAccount, paramBoolean1, paramBoolean2, paramBoolean3, paramString1, paramString2, getAllAlbums(paramArrayList2));
  }
  
  private GoogleSignInOptions(int paramInt, ArrayList paramArrayList, Account paramAccount, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, String paramString1, String paramString2, Map paramMap)
  {
    versionCode = paramInt;
    data = paramArrayList;
    account = paramAccount;
    type = paramBoolean1;
    set = paramBoolean2;
    zaaa = paramBoolean3;
    zaab = paramString1;
    zaac = paramString2;
    zaad = new ArrayList(paramMap.values());
    zaae = paramMap;
  }
  
  private static Map getAllAlbums(List paramList)
  {
    HashMap localHashMap = new HashMap();
    if (paramList == null) {
      return localHashMap;
    }
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      com.google.android.android.auth.params.signin.internal.GoogleSignInOptionsExtensionParcelable localGoogleSignInOptionsExtensionParcelable = (com.google.android.android.auth.params.signin.internal.GoogleSignInOptionsExtensionParcelable)paramList.next();
      localHashMap.put(Integer.valueOf(localGoogleSignInOptionsExtensionParcelable.getType()), localGoogleSignInOptionsExtensionParcelable);
    }
    return localHashMap;
  }
  
  private final JSONObject put()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      Object localObject1 = new JSONArray();
      ArrayList localArrayList = data;
      Object localObject2 = zaaf;
      Collections.sort(localArrayList, (Comparator)localObject2);
      localArrayList = (ArrayList)data;
      int j = localArrayList.size();
      int i = 0;
      while (i < j)
      {
        localObject2 = localArrayList.get(i);
        i += 1;
        localObject2 = (com.google.android.android.common.aimsicd.Scope)localObject2;
        ((JSONArray)localObject1).put(((com.google.android.android.common.aimsicd.Scope)localObject2).getScopeUri());
      }
      localJSONObject.put("scopes", localObject1);
      if (account != null)
      {
        localObject1 = account.name;
        localJSONObject.put("accountName", localObject1);
      }
      boolean bool = type;
      localJSONObject.put("idTokenRequested", bool);
      bool = zaaa;
      localJSONObject.put("forceCodeForRefreshToken", bool);
      bool = set;
      localJSONObject.put("serverAuthRequested", bool);
      localObject1 = zaab;
      bool = TextUtils.isEmpty((CharSequence)localObject1);
      if (!bool)
      {
        localObject1 = zaab;
        localJSONObject.put("serverClientId", localObject1);
      }
      localObject1 = zaac;
      bool = TextUtils.isEmpty((CharSequence)localObject1);
      if (!bool)
      {
        localObject1 = zaac;
        localJSONObject.put("hostedDomain", localObject1);
        return localJSONObject;
      }
    }
    catch (JSONException localJSONException)
    {
      throw new RuntimeException(localJSONException);
    }
    return localJSONException;
  }
  
  public static GoogleSignInOptions update(String paramString)
    throws JSONException
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    JSONObject localJSONObject = new JSONObject(paramString);
    HashSet localHashSet = new HashSet();
    paramString = localJSONObject.getJSONArray("scopes");
    int j = paramString.length();
    int i = 0;
    while (i < j)
    {
      localHashSet.add(new com.google.android.android.common.aimsicd.Scope(paramString.getString(i)));
      i += 1;
    }
    paramString = localJSONObject.optString("accountName", null);
    if (!TextUtils.isEmpty(paramString)) {
      paramString = new Account(paramString, "com.google");
    } else {
      paramString = null;
    }
    return new GoogleSignInOptions(3, new ArrayList(localHashSet), paramString, localJSONObject.getBoolean("idTokenRequested"), localJSONObject.getBoolean("serverAuthRequested"), localJSONObject.getBoolean("forceCodeForRefreshToken"), localJSONObject.optString("serverClientId", null), localJSONObject.optString("hostedDomain", null), new HashMap());
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == null) {
      return false;
    }
    try
    {
      paramObject = (GoogleSignInOptions)paramObject;
      Object localObject = zaad;
      int i = ((ArrayList)localObject).size();
      if (i <= 0)
      {
        localObject = zaad;
        i = ((ArrayList)localObject).size();
        if (i > 0) {
          return false;
        }
        localObject = data;
        i = ((ArrayList)localObject).size();
        int j = paramObject.getScopes().size();
        if (i == j)
        {
          localObject = data;
          boolean bool1 = ((ArrayList)localObject).containsAll(paramObject.getScopes());
          if (!bool1) {
            return false;
          }
          if (account == null)
          {
            localObject = paramObject.getAccount();
            if (localObject != null) {
              break label253;
            }
          }
          else
          {
            localObject = account;
            bool1 = ((Account)localObject).equals(paramObject.getAccount());
            if (!bool1) {
              break label256;
            }
          }
          localObject = zaab;
          bool1 = TextUtils.isEmpty((CharSequence)localObject);
          if (bool1)
          {
            bool1 = TextUtils.isEmpty(paramObject.getServerClientId());
            if (!bool1) {
              break label256;
            }
          }
          else
          {
            localObject = zaab;
            bool1 = ((String)localObject).equals(paramObject.getServerClientId());
            if (!bool1) {
              break label256;
            }
          }
          bool1 = zaaa;
          boolean bool2 = paramObject.isForceCodeForRefreshToken();
          if (bool1 != bool2) {
            break label256;
          }
          bool1 = type;
          bool2 = paramObject.isIdTokenRequested();
          if (bool1 != bool2) {
            break label256;
          }
          bool1 = set;
          bool2 = paramObject.isServerAuthCodeRequested();
          if (bool1 != bool2) {
            break label256;
          }
          return true;
        }
      }
      label253:
      return false;
    }
    catch (ClassCastException paramObject) {}
    label256:
    return false;
  }
  
  public Account getAccount()
  {
    return account;
  }
  
  public ArrayList getExtensions()
  {
    return zaad;
  }
  
  public com.google.android.android.common.aimsicd.Scope[] getScopeArray()
  {
    return (com.google.android.android.common.aimsicd.Scope[])data.toArray(new com.google.android.android.common.aimsicd.Scope[data.size()]);
  }
  
  public ArrayList getScopes()
  {
    return new ArrayList(data);
  }
  
  public String getServerClientId()
  {
    return zaab;
  }
  
  public int hashCode()
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = (ArrayList)data;
    int j = localArrayList2.size();
    int i = 0;
    while (i < j)
    {
      Object localObject = localArrayList2.get(i);
      i += 1;
      localArrayList1.add(((com.google.android.android.common.aimsicd.Scope)localObject).getScopeUri());
    }
    Collections.sort(localArrayList1);
    return new HashAccumulator().addObject(localArrayList1).addObject(account).addObject(zaab).append(zaaa).append(type).append(set).hash();
  }
  
  public boolean isForceCodeForRefreshToken()
  {
    return zaaa;
  }
  
  public boolean isIdTokenRequested()
  {
    return type;
  }
  
  public boolean isServerAuthCodeRequested()
  {
    return set;
  }
  
  public final String toJsonString()
  {
    return put().toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 1, versionCode);
    SafeParcelWriter.writeTypedList(paramParcel, 2, getScopes(), false);
    SafeParcelWriter.writeParcelable(paramParcel, 3, getAccount(), paramInt, false);
    SafeParcelWriter.writeBoolean(paramParcel, 4, isIdTokenRequested());
    SafeParcelWriter.writeBoolean(paramParcel, 5, isServerAuthCodeRequested());
    SafeParcelWriter.writeBoolean(paramParcel, 6, isForceCodeForRefreshToken());
    SafeParcelWriter.writeString(paramParcel, 7, getServerClientId(), false);
    SafeParcelWriter.writeString(paramParcel, 8, zaac, false);
    SafeParcelWriter.writeTypedList(paramParcel, 9, getExtensions(), false);
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
  }
  
  public final class Builder
  {
    private boolean built;
    private Set<com.google.android.gms.common.api.Scope> mScopes = new HashSet();
    private Account mimeType;
    private boolean tempFile;
    private boolean zaaa;
    private String zaab;
    private String zaac;
    private Map<Integer, com.google.android.gms.auth.api.signin.internal.GoogleSignInOptionsExtensionParcelable> zaag = new HashMap();
    
    public Builder() {}
    
    public Builder()
    {
      Preconditions.checkNotNull(this$1);
      mScopes = new HashSet(GoogleSignInOptions.access$getData(this$1));
      built = GoogleSignInOptions.isSet(this$1);
      zaaa = GoogleSignInOptions.s(this$1);
      tempFile = GoogleSignInOptions.getClassName(this$1);
      zaab = GoogleSignInOptions.getSoundPath(this$1);
      mimeType = GoogleSignInOptions.getAccount(this$1);
      zaac = GoogleSignInOptions.getArticleUrl(this$1);
      zaag = GoogleSignInOptions.emptyIfNull(GoogleSignInOptions.access$getMEvents(this$1));
    }
    
    private final String truncate(String paramString)
    {
      Preconditions.checkNotEmpty(paramString);
      boolean bool;
      if ((zaab != null) && (!zaab.equals(paramString))) {
        bool = false;
      } else {
        bool = true;
      }
      Preconditions.checkArgument(bool, "two different server client ids provided");
      return paramString;
    }
    
    public final Builder addExtension(GoogleSignInOptionsExtension paramGoogleSignInOptionsExtension)
    {
      if (!zaag.containsKey(Integer.valueOf(paramGoogleSignInOptionsExtension.getExtensionType())))
      {
        if (paramGoogleSignInOptionsExtension.getImpliedScopes() != null) {
          mScopes.addAll(paramGoogleSignInOptionsExtension.getImpliedScopes());
        }
        zaag.put(Integer.valueOf(paramGoogleSignInOptionsExtension.getExtensionType()), new com.google.android.android.auth.params.signin.internal.GoogleSignInOptionsExtensionParcelable(paramGoogleSignInOptionsExtension));
        return this;
      }
      throw new IllegalStateException("Only one extension per type may be added");
    }
    
    public final GoogleSignInOptions build()
    {
      if ((mScopes.contains(GoogleSignInOptions.EMAIL)) && (mScopes.contains(GoogleSignInOptions.EXCLUSIVE))) {
        mScopes.remove(GoogleSignInOptions.EXCLUSIVE);
      }
      if ((tempFile) && ((mimeType == null) || (!mScopes.isEmpty()))) {
        requestId();
      }
      return new GoogleSignInOptions(3, new ArrayList(mScopes), mimeType, tempFile, built, zaaa, zaab, zaac, zaag, null);
    }
    
    public final Builder requestEmail()
    {
      mScopes.add(GoogleSignInOptions.LASTFM);
      return this;
    }
    
    public final Builder requestId()
    {
      mScopes.add(GoogleSignInOptions.LOCAL);
      return this;
    }
    
    public final Builder requestIdToken(String paramString)
    {
      tempFile = true;
      zaab = truncate(paramString);
      return this;
    }
    
    public final Builder requestProfile()
    {
      mScopes.add(GoogleSignInOptions.CACHE);
      return this;
    }
    
    public final Builder requestScopes(com.google.android.android.common.aimsicd.Scope paramScope, com.google.android.android.common.aimsicd.Scope... paramVarArgs)
    {
      mScopes.add(paramScope);
      mScopes.addAll(Arrays.asList(paramVarArgs));
      return this;
    }
    
    public final Builder requestServerAuthCode(String paramString)
    {
      return requestServerAuthCode(paramString, false);
    }
    
    public final Builder requestServerAuthCode(String paramString, boolean paramBoolean)
    {
      built = true;
      zaab = truncate(paramString);
      zaaa = paramBoolean;
      return this;
    }
    
    public final Builder setAccountName(String paramString)
    {
      mimeType = new Account(Preconditions.checkNotEmpty(paramString), "com.google");
      return this;
    }
    
    public final Builder setHostedDomain(String paramString)
    {
      zaac = Preconditions.checkNotEmpty(paramString);
      return this;
    }
  }
}
