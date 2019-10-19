package com.google.firebase;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.android.common.internal.Objects;
import com.google.android.android.common.internal.Objects.ToStringHelper;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.common.internal.StringResourceValueReader;
import com.google.android.android.common.util.Strings;
import com.google.firebase.annotations.PublicApi;

@PublicApi
public final class FirebaseOptions
{
  private final String defaultValue;
  private final String destination;
  private final String id;
  private final String name;
  private final String oid;
  private final String source;
  private final String userId;
  
  private FirebaseOptions(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7)
  {
    Preconditions.checkState(Strings.isEmptyOrWhitespace(paramString1) ^ true, "ApplicationId must be set.");
    destination = paramString1;
    source = paramString2;
    name = paramString3;
    defaultValue = paramString4;
    id = paramString5;
    userId = paramString6;
    oid = paramString7;
  }
  
  public static FirebaseOptions fromResource(Context paramContext)
  {
    paramContext = new StringResourceValueReader(paramContext);
    String str = paramContext.getString("google_app_id");
    if (TextUtils.isEmpty(str)) {
      return null;
    }
    return new FirebaseOptions(str, paramContext.getString("google_api_key"), paramContext.getString("firebase_database_url"), paramContext.getString("ga_trackingId"), paramContext.getString("gcm_defaultSenderId"), paramContext.getString("google_storage_bucket"), paramContext.getString("project_id"));
  }
  
  public final boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof FirebaseOptions)) {
      return false;
    }
    paramObject = (FirebaseOptions)paramObject;
    return (Objects.equal(destination, destination)) && (Objects.equal(source, source)) && (Objects.equal(name, name)) && (Objects.equal(defaultValue, defaultValue)) && (Objects.equal(id, id)) && (Objects.equal(userId, userId)) && (Objects.equal(oid, oid));
  }
  
  public final String getApiKey()
  {
    return source;
  }
  
  public final String getApplicationId()
  {
    return destination;
  }
  
  public final String getDatabaseUrl()
  {
    return name;
  }
  
  public final String getGaTrackingId()
  {
    return defaultValue;
  }
  
  public final String getGcmSenderId()
  {
    return id;
  }
  
  public final String getProjectId()
  {
    return oid;
  }
  
  public final String getStorageBucket()
  {
    return userId;
  }
  
  public final int hashCode()
  {
    return Objects.hashCode(new Object[] { destination, source, name, defaultValue, id, userId, oid });
  }
  
  public final String toString()
  {
    return Objects.toStringHelper(this).add("applicationId", destination).add("apiKey", source).add("databaseUrl", name).add("gcmSenderId", id).add("storageBucket", userId).add("projectId", oid).toString();
  }
  
  @PublicApi
  public static final class Builder
  {
    private String baseUrl;
    private String method;
    private String params;
    private String restUrl;
    private String state;
    private String text;
    private String to;
    
    public Builder() {}
    
    public Builder(FirebaseOptions paramFirebaseOptions)
    {
      method = FirebaseOptions.access$getMethod(paramFirebaseOptions);
      baseUrl = FirebaseOptions.access$getSource(paramFirebaseOptions);
      restUrl = FirebaseOptions.getAttributeName(paramFirebaseOptions);
      params = FirebaseOptions.get(paramFirebaseOptions);
      state = FirebaseOptions.id(paramFirebaseOptions);
      text = FirebaseOptions.access$getUserId(paramFirebaseOptions);
      to = FirebaseOptions.getOID(paramFirebaseOptions);
    }
    
    public final FirebaseOptions build()
    {
      return new FirebaseOptions(method, baseUrl, restUrl, params, state, text, to, (byte)0);
    }
    
    public final Builder setApiKey(String paramString)
    {
      baseUrl = Preconditions.checkNotEmpty(paramString, "ApiKey must be set.");
      return this;
    }
    
    public final Builder setApplicationId(String paramString)
    {
      method = Preconditions.checkNotEmpty(paramString, "ApplicationId must be set.");
      return this;
    }
    
    public final Builder setDatabaseUrl(String paramString)
    {
      restUrl = paramString;
      return this;
    }
    
    public final Builder setGaTrackingId(String paramString)
    {
      params = paramString;
      return this;
    }
    
    public final Builder setGcmSenderId(String paramString)
    {
      state = paramString;
      return this;
    }
    
    public final Builder setProjectId(String paramString)
    {
      to = paramString;
      return this;
    }
    
    public final Builder setStorageBucket(String paramString)
    {
      text = paramString;
      return this;
    }
  }
}
