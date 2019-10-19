package com.google.firebase.messaging;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.SimpleArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.android.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.android.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@SafeParcelable.Class(creator="RemoteMessageCreator")
@SafeParcelable.Reserved({1})
public final class RemoteMessage
  extends AbstractSafeParcelable
{
  public static final Parcelable.Creator<RemoteMessage> CREATOR = new VerticalProgressBar.SavedState.1();
  public static final int PRIORITY_HIGH = 1;
  public static final int PRIORITY_NORMAL = 2;
  public static final int PRIORITY_UNKNOWN = 0;
  @SafeParcelable.Field(id=2)
  Bundle zzds;
  private Map<String, String> zzdt;
  private Notification zzdu;
  
  public RemoteMessage(Bundle paramBundle)
  {
    zzds = paramBundle;
  }
  
  private static int getInt(String paramString)
  {
    if ("high".equals(paramString)) {
      return 1;
    }
    if ("normal".equals(paramString)) {
      return 2;
    }
    return 0;
  }
  
  public final String getCollapseKey()
  {
    return zzds.getString("collapse_key");
  }
  
  public final Map getData()
  {
    if (zzdt == null)
    {
      Bundle localBundle = zzds;
      ArrayMap localArrayMap = new ArrayMap();
      Iterator localIterator = localBundle.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        Object localObject = localBundle.get(str);
        if ((localObject instanceof String))
        {
          localObject = (String)localObject;
          if ((!str.startsWith("google.")) && (!str.startsWith("gcm.")) && (!str.equals("from")) && (!str.equals("message_type")) && (!str.equals("collapse_key"))) {
            localArrayMap.put(str, localObject);
          }
        }
      }
      zzdt = localArrayMap;
    }
    return zzdt;
  }
  
  public final String getFrom()
  {
    return zzds.getString("from");
  }
  
  public final String getMessageId()
  {
    String str2 = zzds.getString("google.message_id");
    String str1 = str2;
    if (str2 == null) {
      str1 = zzds.getString("message_id");
    }
    return str1;
  }
  
  public final String getMessageType()
  {
    return zzds.getString("message_type");
  }
  
  public final Notification getNotification()
  {
    if ((zzdu == null) && (Model.load(zzds))) {
      zzdu = new Notification(zzds, null);
    }
    return zzdu;
  }
  
  public final int getOriginalPriority()
  {
    String str2 = zzds.getString("google.original_priority");
    String str1 = str2;
    if (str2 == null) {
      str1 = zzds.getString("google.priority");
    }
    return getInt(str1);
  }
  
  public final int getPriority()
  {
    String str2 = zzds.getString("google.delivered_priority");
    String str1 = str2;
    if (str2 == null)
    {
      if ("1".equals(zzds.getString("google.priority_reduced"))) {
        return 2;
      }
      str1 = zzds.getString("google.priority");
    }
    return getInt(str1);
  }
  
  public final long getSentTime()
  {
    Object localObject1 = zzds.get("google.sent_time");
    if ((localObject1 instanceof Long)) {
      return ((Long)localObject1).longValue();
    }
    if ((localObject1 instanceof String)) {
      localObject2 = (String)localObject1;
    }
    try
    {
      long l = Long.parseLong((String)localObject2);
      return l;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      for (;;) {}
    }
    localObject1 = String.valueOf(localObject1);
    Object localObject2 = new StringBuilder(String.valueOf(localObject1).length() + 19);
    ((StringBuilder)localObject2).append("Invalid sent time: ");
    ((StringBuilder)localObject2).append((String)localObject1);
    Log.w("FirebaseMessaging", ((StringBuilder)localObject2).toString());
    return 0L;
  }
  
  public final String getTo()
  {
    return zzds.getString("google.to");
  }
  
  public final int getTtl()
  {
    Object localObject1 = zzds.get("google.ttl");
    if ((localObject1 instanceof Integer)) {
      return ((Integer)localObject1).intValue();
    }
    if ((localObject1 instanceof String)) {
      localObject2 = (String)localObject1;
    }
    try
    {
      int i = Integer.parseInt((String)localObject2);
      return i;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      for (;;) {}
    }
    localObject1 = String.valueOf(localObject1);
    Object localObject2 = new StringBuilder(String.valueOf(localObject1).length() + 13);
    ((StringBuilder)localObject2).append("Invalid TTL: ");
    ((StringBuilder)localObject2).append((String)localObject1);
    Log.w("FirebaseMessaging", ((StringBuilder)localObject2).toString());
    return 0;
  }
  
  public final Intent toIntent()
  {
    Intent localIntent = new Intent();
    localIntent.putExtras(zzds);
    return localIntent;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeBundle(paramParcel, 2, zzds, false);
    SafeParcelWriter.finishObjectHeader(paramParcel, paramInt);
  }
  
  public static class Builder
  {
    private final Bundle zzds = new Bundle();
    private final Map<String, String> zzdt = new ArrayMap();
    
    public Builder(String paramString)
    {
      if (TextUtils.isEmpty(paramString))
      {
        paramString = String.valueOf(paramString);
        if (paramString.length() != 0) {
          paramString = "Invalid to: ".concat(paramString);
        } else {
          paramString = new String("Invalid to: ");
        }
        throw new IllegalArgumentException(paramString);
      }
      zzds.putString("google.to", paramString);
    }
    
    public Builder addData(String paramString1, String paramString2)
    {
      zzdt.put(paramString1, paramString2);
      return this;
    }
    
    public RemoteMessage build()
    {
      Bundle localBundle = new Bundle();
      Iterator localIterator = zzdt.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        localBundle.putString((String)localEntry.getKey(), (String)localEntry.getValue());
      }
      localBundle.putAll(zzds);
      zzds.remove("from");
      return new RemoteMessage(localBundle);
    }
    
    public Builder clearData()
    {
      zzdt.clear();
      return this;
    }
    
    public Builder setCollapseKey(String paramString)
    {
      zzds.putString("collapse_key", paramString);
      return this;
    }
    
    public Builder setData(Map paramMap)
    {
      zzdt.clear();
      zzdt.putAll(paramMap);
      return this;
    }
    
    public Builder setMessageId(String paramString)
    {
      zzds.putString("google.message_id", paramString);
      return this;
    }
    
    public Builder setMessageType(String paramString)
    {
      zzds.putString("message_type", paramString);
      return this;
    }
    
    public Builder setTtl(int paramInt)
    {
      zzds.putString("google.ttl", String.valueOf(paramInt));
      return this;
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface MessagePriority {}
  
  public static class Notification
  {
    private final String elementName;
    private final String zzdv;
    private final String zzdw;
    private final String[] zzdx;
    private final String zzdy;
    private final String zzdz;
    private final String[] zzea;
    private final String zzeb;
    private final String zzec;
    private final String zzed;
    private final String zzee;
    private final Uri zzef;
    
    private Notification(Bundle paramBundle)
    {
      zzdv = Model.getString(paramBundle, "gcm.n.title");
      zzdw = Model.getData(paramBundle, "gcm.n.title");
      zzdx = create(paramBundle, "gcm.n.title");
      zzdy = Model.getString(paramBundle, "gcm.n.body");
      zzdz = Model.getData(paramBundle, "gcm.n.body");
      zzea = create(paramBundle, "gcm.n.body");
      zzeb = Model.getString(paramBundle, "gcm.n.icon");
      zzec = Model.getName(paramBundle);
      elementName = Model.getString(paramBundle, "gcm.n.tag");
      zzed = Model.getString(paramBundle, "gcm.n.color");
      zzee = Model.getString(paramBundle, "gcm.n.click_action");
      zzef = Model.getUrl(paramBundle);
    }
    
    private static String[] create(Bundle paramBundle, String paramString)
    {
      paramBundle = Model.getValue(paramBundle, paramString);
      if (paramBundle == null) {
        return null;
      }
      paramString = new String[paramBundle.length];
      int i = 0;
      while (i < paramBundle.length)
      {
        paramString[i] = String.valueOf(paramBundle[i]);
        i += 1;
      }
      return paramString;
    }
    
    public String getBody()
    {
      return zzdy;
    }
    
    public String[] getBodyLocalizationArgs()
    {
      return zzea;
    }
    
    public String getBodyLocalizationKey()
    {
      return zzdz;
    }
    
    public String getClickAction()
    {
      return zzee;
    }
    
    public String getColor()
    {
      return zzed;
    }
    
    public String getIcon()
    {
      return zzeb;
    }
    
    public Uri getLink()
    {
      return zzef;
    }
    
    public String getSound()
    {
      return zzec;
    }
    
    public String getTag()
    {
      return elementName;
    }
    
    public String getTitle()
    {
      return zzdv;
    }
    
    public String[] getTitleLocalizationArgs()
    {
      return zzdx;
    }
    
    public String getTitleLocalizationKey()
    {
      return zzdw;
    }
  }
}
