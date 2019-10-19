package com.google.android.android.auth.params.signin;

import android.accounts.Account;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.common.internal.ReflectedParcelable;
import com.google.android.android.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.android.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.android.common.util.Clock;
import com.google.android.android.common.util.DefaultClock;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SafeParcelable.Class(creator="GoogleSignInAccountCreator")
public class GoogleSignInAccount
  extends AbstractSafeParcelable
  implements ReflectedParcelable
{
  public static final Parcelable.Creator<com.google.android.gms.auth.api.signin.GoogleSignInAccount> CREATOR = new VerticalProgressBar.SavedState.1();
  @VisibleForTesting
  private static Clock log = DefaultClock.getInstance();
  private Set<com.google.android.gms.common.api.Scope> adapterData = new HashSet();
  @SafeParcelable.Field(getter="getGivenName", id=11)
  private String givenName;
  @SafeParcelable.Field(getter="getExpirationTimeSecs", id=8)
  private long id;
  @SafeParcelable.Field(getter="getServerAuthCode", id=7)
  private String invalid;
  @SafeParcelable.Field(getter="getDisplayName", id=5)
  private String mDisplayName;
  @SafeParcelable.Field(getter="getEmail", id=4)
  private String mEmail;
  @SafeParcelable.Field(getter="getIdToken", id=3)
  private String mFullName;
  @SafeParcelable.Field(getter="getPhotoUrl", id=6)
  private Uri mImageSource;
  @SafeParcelable.Field(getter="getId", id=2)
  private String mUserId;
  @SafeParcelable.Field(getter="getFamilyName", id=12)
  private String middleName;
  @SafeParcelable.Field(getter="getObfuscatedIdentifier", id=9)
  private String source;
  @SafeParcelable.Field(id=10)
  private List<com.google.android.gms.common.api.Scope> tasks;
  @SafeParcelable.VersionField(id=1)
  private final int versionCode;
  
  GoogleSignInAccount(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, Uri paramUri, String paramString5, long paramLong, String paramString6, List paramList, String paramString7, String paramString8)
  {
    versionCode = paramInt;
    mUserId = paramString1;
    mFullName = paramString2;
    mEmail = paramString3;
    mDisplayName = paramString4;
    mImageSource = paramUri;
    invalid = paramString5;
    id = paramLong;
    source = paramString6;
    tasks = paramList;
    givenName = paramString7;
    middleName = paramString8;
  }
  
  private static GoogleSignInAccount create(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, Uri paramUri, Long paramLong, String paramString7, Set paramSet)
  {
    Long localLong = paramLong;
    if (paramLong == null) {
      localLong = Long.valueOf(log.currentTimeMillis() / 1000L);
    }
    return new GoogleSignInAccount(3, paramString1, paramString2, paramString3, paramString4, paramUri, null, localLong.longValue(), Preconditions.checkNotEmpty(paramString7), new ArrayList((Collection)Preconditions.checkNotNull(paramSet)), paramString5, paramString6);
  }
  
  public static GoogleSignInAccount createDefault()
  {
    Account localAccount = new Account("<<default account>>", "com.google");
    HashSet localHashSet = new HashSet();
    return create(null, null, name, null, null, null, null, Long.valueOf(0L), name, localHashSet);
  }
  
  private final JSONObject doInBackground()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      Object localObject1 = getId();
      if (localObject1 != null) {
        localJSONObject.put("id", getId());
      }
      localObject1 = getIdToken();
      if (localObject1 != null) {
        localJSONObject.put("tokenId", getIdToken());
      }
      localObject1 = getEmail();
      if (localObject1 != null) {
        localJSONObject.put("email", getEmail());
      }
      localObject1 = getDisplayName();
      if (localObject1 != null) {
        localJSONObject.put("displayName", getDisplayName());
      }
      localObject1 = getGivenName();
      if (localObject1 != null) {
        localJSONObject.put("givenName", getGivenName());
      }
      localObject1 = getFamilyName();
      if (localObject1 != null) {
        localJSONObject.put("familyName", getFamilyName());
      }
      localObject1 = getPhotoUrl();
      if (localObject1 != null) {
        localJSONObject.put("photoUrl", getPhotoUrl().toString());
      }
      localObject1 = getServerAuthCode();
      if (localObject1 != null) {
        localJSONObject.put("serverAuthCode", getServerAuthCode());
      }
      long l = id;
      localJSONObject.put("expirationTime", l);
      localObject1 = source;
      localJSONObject.put("obfuscatedIdentifier", localObject1);
      localObject1 = new JSONArray();
      Object localObject2 = tasks;
      Object localObject3 = tasks;
      int i = ((List)localObject3).size();
      localObject3 = new com.google.android.android.common.aimsicd.Scope[i];
      localObject2 = ((List)localObject2).toArray((Object[])localObject3);
      localObject2 = (com.google.android.android.common.aimsicd.Scope[])localObject2;
      localObject3 = LabelPlacement.ReferencePositionYComparator.INSTANCE;
      Arrays.sort((Object[])localObject2, (Comparator)localObject3);
      int j = localObject2.length;
      i = 0;
      while (i < j)
      {
        localObject3 = localObject2[i];
        ((JSONArray)localObject1).put(((com.google.android.android.common.aimsicd.Scope)localObject3).getScopeUri());
        i += 1;
      }
      localJSONObject.put("grantedScopes", localObject1);
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      throw new RuntimeException(localJSONException);
    }
  }
  
  public static GoogleSignInAccount search(String paramString)
    throws JSONException
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    JSONObject localJSONObject = new JSONObject(paramString);
    paramString = localJSONObject.optString("photoUrl", null);
    if (!TextUtils.isEmpty(paramString)) {
      paramString = Uri.parse(paramString);
    } else {
      paramString = null;
    }
    long l = Long.parseLong(localJSONObject.getString("expirationTime"));
    HashSet localHashSet = new HashSet();
    JSONArray localJSONArray = localJSONObject.getJSONArray("grantedScopes");
    int j = localJSONArray.length();
    int i = 0;
    while (i < j)
    {
      localHashSet.add(new com.google.android.android.common.aimsicd.Scope(localJSONArray.getString(i)));
      i += 1;
    }
    paramString = create(localJSONObject.optString("id"), localJSONObject.optString("tokenId", null), localJSONObject.optString("email", null), localJSONObject.optString("displayName", null), localJSONObject.optString("givenName", null), localJSONObject.optString("familyName", null), paramString, Long.valueOf(l), localJSONObject.getString("obfuscatedIdentifier"), localHashSet);
    invalid = localJSONObject.optString("serverAuthCode", null);
    return paramString;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof GoogleSignInAccount)) {
      return false;
    }
    paramObject = (GoogleSignInAccount)paramObject;
    return (source.equals(source)) && (paramObject.getRequestedScopes().equals(getRequestedScopes()));
  }
  
  public Account getAccount()
  {
    if (mEmail == null) {
      return null;
    }
    return new Account(mEmail, "com.google");
  }
  
  public String getDisplayName()
  {
    return mDisplayName;
  }
  
  public String getEmail()
  {
    return mEmail;
  }
  
  public String getFamilyName()
  {
    return middleName;
  }
  
  public String getGivenName()
  {
    return givenName;
  }
  
  public Set getGrantedScopes()
  {
    return new HashSet(tasks);
  }
  
  public String getId()
  {
    return mUserId;
  }
  
  public String getIdToken()
  {
    return mFullName;
  }
  
  public Uri getPhotoUrl()
  {
    return mImageSource;
  }
  
  public Set getRequestedScopes()
  {
    HashSet localHashSet = new HashSet(tasks);
    localHashSet.addAll(adapterData);
    return localHashSet;
  }
  
  public String getServerAuthCode()
  {
    return invalid;
  }
  
  public int hashCode()
  {
    return (source.hashCode() + 527) * 31 + getRequestedScopes().hashCode();
  }
  
  public boolean isExpired()
  {
    return log.currentTimeMillis() / 1000L >= id - 300L;
  }
  
  public final String json()
  {
    return source;
  }
  
  public GoogleSignInAccount requestExtraScopes(com.google.android.android.common.aimsicd.Scope... paramVarArgs)
  {
    if (paramVarArgs != null) {
      Collections.addAll(adapterData, paramVarArgs);
    }
    return this;
  }
  
  public final String toJsonString()
  {
    JSONObject localJSONObject = doInBackground();
    localJSONObject.remove("serverAuthCode");
    return localJSONObject.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 1, versionCode);
    SafeParcelWriter.writeString(paramParcel, 2, getId(), false);
    SafeParcelWriter.writeString(paramParcel, 3, getIdToken(), false);
    SafeParcelWriter.writeString(paramParcel, 4, getEmail(), false);
    SafeParcelWriter.writeString(paramParcel, 5, getDisplayName(), false);
    SafeParcelWriter.writeParcelable(paramParcel, 6, getPhotoUrl(), paramInt, false);
    SafeParcelWriter.writeString(paramParcel, 7, getServerAuthCode(), false);
    SafeParcelWriter.writeLong(paramParcel, 8, id);
    SafeParcelWriter.writeString(paramParcel, 9, source, false);
    SafeParcelWriter.writeTypedList(paramParcel, 10, tasks, false);
    SafeParcelWriter.writeString(paramParcel, 11, getGivenName(), false);
    SafeParcelWriter.writeString(paramParcel, 12, getFamilyName(), false);
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
  }
}
