package com.google.android.android.measurement.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.common.util.Clock;
import com.google.android.android.internal.measurement.zzfu;
import com.google.android.android.internal.measurement.zzfv;
import com.google.android.android.internal.measurement.zzfy;
import com.google.android.android.internal.measurement.zzgf;
import com.google.android.android.internal.measurement.zzgi;
import com.google.android.android.internal.measurement.zzyx;
import com.google.android.android.internal.measurement.zzyy;
import com.google.android.android.internal.measurement.zzzg;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

final class StringBuilder
  extends zzez
{
  private static final String[] zzahi = { "last_bundled_timestamp", "ALTER TABLE events ADD COLUMN last_bundled_timestamp INTEGER;", "last_bundled_day", "ALTER TABLE events ADD COLUMN last_bundled_day INTEGER;", "last_sampled_complex_event_id", "ALTER TABLE events ADD COLUMN last_sampled_complex_event_id INTEGER;", "last_sampling_rate", "ALTER TABLE events ADD COLUMN last_sampling_rate INTEGER;", "last_exempt_from_sampling", "ALTER TABLE events ADD COLUMN last_exempt_from_sampling INTEGER;" };
  private static final String[] zzahj = { "origin", "ALTER TABLE user_attributes ADD COLUMN origin TEXT;" };
  private static final String[] zzahk = { "app_version", "ALTER TABLE apps ADD COLUMN app_version TEXT;", "app_store", "ALTER TABLE apps ADD COLUMN app_store TEXT;", "gmp_version", "ALTER TABLE apps ADD COLUMN gmp_version INTEGER;", "dev_cert_hash", "ALTER TABLE apps ADD COLUMN dev_cert_hash INTEGER;", "measurement_enabled", "ALTER TABLE apps ADD COLUMN measurement_enabled INTEGER;", "last_bundle_start_timestamp", "ALTER TABLE apps ADD COLUMN last_bundle_start_timestamp INTEGER;", "day", "ALTER TABLE apps ADD COLUMN day INTEGER;", "daily_public_events_count", "ALTER TABLE apps ADD COLUMN daily_public_events_count INTEGER;", "daily_events_count", "ALTER TABLE apps ADD COLUMN daily_events_count INTEGER;", "daily_conversions_count", "ALTER TABLE apps ADD COLUMN daily_conversions_count INTEGER;", "remote_config", "ALTER TABLE apps ADD COLUMN remote_config BLOB;", "config_fetched_time", "ALTER TABLE apps ADD COLUMN config_fetched_time INTEGER;", "failed_config_fetch_time", "ALTER TABLE apps ADD COLUMN failed_config_fetch_time INTEGER;", "app_version_int", "ALTER TABLE apps ADD COLUMN app_version_int INTEGER;", "firebase_instance_id", "ALTER TABLE apps ADD COLUMN firebase_instance_id TEXT;", "daily_error_events_count", "ALTER TABLE apps ADD COLUMN daily_error_events_count INTEGER;", "daily_realtime_events_count", "ALTER TABLE apps ADD COLUMN daily_realtime_events_count INTEGER;", "health_monitor_sample", "ALTER TABLE apps ADD COLUMN health_monitor_sample TEXT;", "android_id", "ALTER TABLE apps ADD COLUMN android_id INTEGER;", "adid_reporting_enabled", "ALTER TABLE apps ADD COLUMN adid_reporting_enabled INTEGER;", "ssaid_reporting_enabled", "ALTER TABLE apps ADD COLUMN ssaid_reporting_enabled INTEGER;", "admob_app_id", "ALTER TABLE apps ADD COLUMN admob_app_id TEXT;", "linked_admob_app_id", "ALTER TABLE apps ADD COLUMN linked_admob_app_id TEXT;" };
  private static final String[] zzahl = { "realtime", "ALTER TABLE raw_events ADD COLUMN realtime INTEGER;" };
  private static final String[] zzahm = { "has_realtime", "ALTER TABLE queue ADD COLUMN has_realtime INTEGER;", "retry_count", "ALTER TABLE queue ADD COLUMN retry_count INTEGER;" };
  private static final String[] zzahn = { "previous_install_count", "ALTER TABLE app2 ADD COLUMN previous_install_count INTEGER;" };
  private final DatabaseHelper zzaho = new DatabaseHelper(this, getContext(), "google_app_measurement.db");
  private final zzev zzahp = new zzev(zzbx());
  
  StringBuilder(zzfa paramZzfa)
  {
    super(paramZzfa);
  }
  
  private final boolean add(String paramString, int paramInt, zzfv paramZzfv)
  {
    zzcl();
    zzaf();
    Preconditions.checkNotEmpty(paramString);
    Preconditions.checkNotNull(paramZzfv);
    if (TextUtils.isEmpty(zzavf))
    {
      zzgo().zzjg().append("Event filter had no event name. Audience definition ignored. appId, audienceId, filterId", zzap.zzbv(paramString), Integer.valueOf(paramInt), String.valueOf(zzave));
      return false;
    }
    try
    {
      int i = paramZzfv.zzvu();
      byte[] arrayOfByte = new byte[i];
      i = arrayOfByte.length;
      Object localObject = zzyy.toString(arrayOfByte, 0, i);
      paramZzfv.multiply((zzyy)localObject);
      ((zzyy)localObject).zzyt();
      localObject = new ContentValues();
      ((ContentValues)localObject).put("app_id", paramString);
      ((ContentValues)localObject).put("audience_id", Integer.valueOf(paramInt));
      ((ContentValues)localObject).put("filter_id", zzave);
      ((ContentValues)localObject).put("event_name", zzavf);
      ((ContentValues)localObject).put("data", arrayOfByte);
      try
      {
        long l = getWritableDatabase().insertWithOnConflict("event_filters", null, (ContentValues)localObject, 5);
        if (l == -1L) {
          zzgo().zzjd().append("Failed to insert event filter (got -1). appId", zzap.zzbv(paramString));
        }
        return true;
      }
      catch (SQLiteException paramZzfv)
      {
        zzgo().zzjd().append("Error storing event filter. appId", zzap.zzbv(paramString), paramZzfv);
        return false;
      }
      return false;
    }
    catch (IOException paramZzfv)
    {
      zzgo().zzjd().append("Configuration loss. Failed to serialize event filter. appId", zzap.zzbv(paramString), paramZzfv);
    }
  }
  
  private final boolean add(String paramString, int paramInt, zzfy paramZzfy)
  {
    zzcl();
    zzaf();
    Preconditions.checkNotEmpty(paramString);
    Preconditions.checkNotNull(paramZzfy);
    if (TextUtils.isEmpty(zzavu))
    {
      zzgo().zzjg().append("Property filter had no property name. Audience definition ignored. appId, audienceId, filterId", zzap.zzbv(paramString), Integer.valueOf(paramInt), String.valueOf(zzave));
      return false;
    }
    try
    {
      int i = paramZzfy.zzvu();
      byte[] arrayOfByte = new byte[i];
      i = arrayOfByte.length;
      Object localObject = zzyy.toString(arrayOfByte, 0, i);
      paramZzfy.multiply((zzyy)localObject);
      ((zzyy)localObject).zzyt();
      localObject = new ContentValues();
      ((ContentValues)localObject).put("app_id", paramString);
      ((ContentValues)localObject).put("audience_id", Integer.valueOf(paramInt));
      ((ContentValues)localObject).put("filter_id", zzave);
      ((ContentValues)localObject).put("property_name", zzavu);
      ((ContentValues)localObject).put("data", arrayOfByte);
      try
      {
        long l = getWritableDatabase().insertWithOnConflict("property_filters", null, (ContentValues)localObject, 5);
        if (l == -1L)
        {
          zzgo().zzjd().append("Failed to insert property filter (got -1). appId", zzap.zzbv(paramString));
          return false;
        }
        return true;
      }
      catch (SQLiteException paramZzfy)
      {
        zzgo().zzjd().append("Error storing property filter. appId", zzap.zzbv(paramString), paramZzfy);
        return false;
      }
      return false;
    }
    catch (IOException paramZzfy)
    {
      zzgo().zzjd().append("Configuration loss. Failed to serialize property filter. appId", zzap.zzbv(paramString), paramZzfy);
    }
  }
  
  private final long count(String paramString, String[] paramArrayOfString)
  {
    SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
    Object localObject = null;
    String[] arrayOfString = null;
    long l;
    try
    {
      paramArrayOfString = localSQLiteDatabase.rawQuery(paramString, paramArrayOfString);
      arrayOfString = paramArrayOfString;
      try
      {
        boolean bool = paramArrayOfString.moveToFirst();
        if (bool)
        {
          l = paramArrayOfString.getLong(0);
          if (paramArrayOfString == null) {
            break label124;
          }
          paramArrayOfString.close();
          return l;
        }
        throw new SQLiteException("Database returned empty set");
      }
      catch (Throwable paramString)
      {
        break label110;
      }
      catch (SQLiteException localSQLiteException1) {}
      arrayOfString = paramArrayOfString;
    }
    catch (Throwable paramString) {}catch (SQLiteException localSQLiteException2)
    {
      paramArrayOfString = localObject;
    }
    zzgo().zzjd().append("Database error", paramString, localSQLiteException2);
    arrayOfString = paramArrayOfString;
    throw localSQLiteException2;
    label110:
    if (arrayOfString != null) {
      arrayOfString.close();
    }
    throw paramString;
    label124:
    return l;
  }
  
  private final long count(String paramString, String[] paramArrayOfString, long paramLong)
  {
    SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
    Object localObject = null;
    String[] arrayOfString = null;
    long l;
    try
    {
      paramArrayOfString = localSQLiteDatabase.rawQuery(paramString, paramArrayOfString);
      try
      {
        boolean bool = paramArrayOfString.moveToFirst();
        if (bool)
        {
          paramLong = paramArrayOfString.getLong(0);
          l = paramLong;
          if (paramArrayOfString == null) {
            break label129;
          }
          paramArrayOfString.close();
          return paramLong;
        }
        l = paramLong;
        if (paramArrayOfString == null) {
          break label129;
        }
        paramArrayOfString.close();
        return paramLong;
      }
      catch (Throwable paramString)
      {
        break label117;
      }
      catch (SQLiteException localSQLiteException1) {}
      arrayOfString = paramArrayOfString;
    }
    catch (Throwable paramString)
    {
      paramArrayOfString = arrayOfString;
    }
    catch (SQLiteException localSQLiteException2)
    {
      paramArrayOfString = localObject;
    }
    zzgo().zzjd().append("Database error", paramString, localSQLiteException2);
    arrayOfString = paramArrayOfString;
    throw localSQLiteException2;
    label117:
    if (paramArrayOfString != null) {
      paramArrayOfString.close();
    }
    throw paramString;
    label129:
    return l;
  }
  
  private final Object get(Cursor paramCursor, int paramInt)
  {
    int i = paramCursor.getType(paramInt);
    switch (i)
    {
    default: 
      zzgo().zzjd().append("Loaded invalid unknown value type, ignoring it", Integer.valueOf(i));
      return null;
    case 4: 
      zzgo().zzjd().zzbx("Loaded invalid blob type value, ignoring it");
      return null;
    case 3: 
      return paramCursor.getString(paramInt);
    case 2: 
      return Double.valueOf(paramCursor.getDouble(paramInt));
    case 1: 
      return Long.valueOf(paramCursor.getLong(paramInt));
    }
    zzgo().zzjd().zzbx("Loaded invalid null value from database");
    return null;
  }
  
  private static void put(ContentValues paramContentValues, String paramString, Object paramObject)
  {
    Preconditions.checkNotEmpty(paramString);
    Preconditions.checkNotNull(paramObject);
    if ((paramObject instanceof String))
    {
      paramContentValues.put(paramString, (String)paramObject);
      return;
    }
    if ((paramObject instanceof Long))
    {
      paramContentValues.put(paramString, (Long)paramObject);
      return;
    }
    if ((paramObject instanceof Double))
    {
      paramContentValues.put(paramString, (Double)paramObject);
      return;
    }
    throw new IllegalArgumentException("Invalid value type");
  }
  
  private final boolean remove(String paramString, List paramList)
  {
    Preconditions.checkNotEmpty(paramString);
    zzcl();
    zzaf();
    SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
    try
    {
      long l = count("select count(1) from audience_filter_values where app_id=?", new String[] { paramString });
      int j = Math.max(0, Math.min(2000, zzgq().next(paramString, zzaf.zzaki)));
      if (l <= j) {
        return false;
      }
      Object localObject = new ArrayList();
      int i = 0;
      while (i < paramList.size())
      {
        Integer localInteger = (Integer)paramList.get(i);
        if (localInteger != null)
        {
          if (!(localInteger instanceof Integer)) {
            return false;
          }
          ((List)localObject).add(Integer.toString(localInteger.intValue()));
          i += 1;
        }
        else
        {
          return false;
        }
      }
      paramList = TextUtils.join(",", (Iterable)localObject);
      localObject = new java.lang.StringBuilder(String.valueOf(paramList).length() + 2);
      ((java.lang.StringBuilder)localObject).append("(");
      ((java.lang.StringBuilder)localObject).append(paramList);
      ((java.lang.StringBuilder)localObject).append(")");
      paramList = ((java.lang.StringBuilder)localObject).toString();
      localObject = new java.lang.StringBuilder(String.valueOf(paramList).length() + 140);
      ((java.lang.StringBuilder)localObject).append("audience_id in (select audience_id from audience_filter_values where app_id=? and audience_id not in ");
      ((java.lang.StringBuilder)localObject).append(paramList);
      ((java.lang.StringBuilder)localObject).append(" order by rowid desc limit -1 offset ?)");
      return localSQLiteDatabase.delete("audience_filter_values", ((java.lang.StringBuilder)localObject).toString(), new String[] { paramString, Integer.toString(j) }) > 0;
    }
    catch (SQLiteException paramList)
    {
      zzgo().zzjd().append("Database error querying filters. appId", zzap.zzbv(paramString), paramList);
    }
    return false;
  }
  
  private final boolean zzil()
  {
    return getContext().getDatabasePath("google_app_measurement.db").exists();
  }
  
  protected final long add(String paramString1, String paramString2)
  {
    Preconditions.checkNotEmpty(paramString1);
    Preconditions.checkNotEmpty(paramString2);
    zzaf();
    zzcl();
    SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
    localSQLiteDatabase.beginTransaction();
    long l1;
    try
    {
      int i = String.valueOf(paramString2).length();
      Object localObject = new java.lang.StringBuilder(i + 32);
      ((java.lang.StringBuilder)localObject).append("select ");
      ((java.lang.StringBuilder)localObject).append(paramString2);
      ((java.lang.StringBuilder)localObject).append(" from app2 where app_id=?");
      localObject = ((java.lang.StringBuilder)localObject).toString();
      long l2 = count((String)localObject, new String[] { paramString1 }, -1L);
      l1 = l2;
      if (l2 == -1L)
      {
        localObject = new ContentValues();
        ((ContentValues)localObject).put("app_id", paramString1);
        ((ContentValues)localObject).put("first_open_count", Integer.valueOf(0));
        ((ContentValues)localObject).put("previous_install_count", Integer.valueOf(0));
        l1 = localSQLiteDatabase.insertWithOnConflict("app2", null, (ContentValues)localObject, 5);
        if (l1 == -1L)
        {
          zzgo().zzjd().append("Failed to insert column (got -1). appId", zzap.zzbv(paramString1), paramString2);
          localSQLiteDatabase.endTransaction();
          return -1L;
        }
        l1 = 0L;
      }
      try
      {
        localObject = new ContentValues();
        ((ContentValues)localObject).put("app_id", paramString1);
        ((ContentValues)localObject).put(paramString2, Long.valueOf(1L + l1));
        i = localSQLiteDatabase.update("app2", (ContentValues)localObject, "app_id = ?", new String[] { paramString1 });
        if (i == 0L)
        {
          zzgo().zzjd().append("Failed to update column (got 0). appId", zzap.zzbv(paramString1), paramString2);
          localSQLiteDatabase.endTransaction();
          return -1L;
        }
        localSQLiteDatabase.setTransactionSuccessful();
        localSQLiteDatabase.endTransaction();
        return l1;
      }
      catch (SQLiteException localSQLiteException1) {}
      zzgo().zzjd().append("Error inserting column. appId", zzap.zzbv(paramString1), paramString2, localSQLiteException2);
    }
    catch (Throwable paramString1) {}catch (SQLiteException localSQLiteException2)
    {
      l1 = 0L;
    }
    localSQLiteDatabase.endTransaction();
    return l1;
    localSQLiteDatabase.endTransaction();
    throw paramString1;
  }
  
  public final void add(EWAHCompressedBitmap paramEWAHCompressedBitmap)
  {
    Preconditions.checkNotNull(paramEWAHCompressedBitmap);
    zzaf();
    zzcl();
    Object localObject2 = new ContentValues();
    ((ContentValues)localObject2).put("app_id", zztt);
    ((ContentValues)localObject2).put("name", name);
    ((ContentValues)localObject2).put("lifetime_count", Long.valueOf(zzaie));
    ((ContentValues)localObject2).put("current_bundle_count", Long.valueOf(zzaif));
    ((ContentValues)localObject2).put("last_fire_timestamp", Long.valueOf(zzaig));
    ((ContentValues)localObject2).put("last_bundled_timestamp", Long.valueOf(zzaih));
    ((ContentValues)localObject2).put("last_bundled_day", zzaii);
    ((ContentValues)localObject2).put("last_sampled_complex_event_id", zzaij);
    ((ContentValues)localObject2).put("last_sampling_rate", zzaik);
    Object localObject1;
    if ((zzail != null) && (zzail.booleanValue())) {
      localObject1 = Long.valueOf(1L);
    } else {
      localObject1 = null;
    }
    ((ContentValues)localObject2).put("last_exempt_from_sampling", (Long)localObject1);
    try
    {
      long l = getWritableDatabase().insertWithOnConflict("events", null, (ContentValues)localObject2, 5);
      if (l == -1L)
      {
        localObject1 = zzgo().zzjd();
        localObject2 = zztt;
        ((zzar)localObject1).append("Failed to insert/update event aggregates (got -1). appId", zzap.zzbv((String)localObject2));
        return;
      }
    }
    catch (SQLiteException localSQLiteException)
    {
      zzgo().zzjd().append("Error storing event aggregates. appId", zzap.zzbv(zztt), localSQLiteException);
    }
  }
  
  final void add(List paramList)
  {
    zzaf();
    zzcl();
    Preconditions.checkNotNull(paramList);
    Preconditions.checkNotZero(paramList.size());
    if (!zzil()) {
      return;
    }
    paramList = TextUtils.join(",", paramList);
    Object localObject = new java.lang.StringBuilder(String.valueOf(paramList).length() + 2);
    ((java.lang.StringBuilder)localObject).append("(");
    ((java.lang.StringBuilder)localObject).append(paramList);
    ((java.lang.StringBuilder)localObject).append(")");
    paramList = ((java.lang.StringBuilder)localObject).toString();
    localObject = new java.lang.StringBuilder(String.valueOf(paramList).length() + 80);
    ((java.lang.StringBuilder)localObject).append("SELECT COUNT(1) FROM queue WHERE rowid IN ");
    ((java.lang.StringBuilder)localObject).append(paramList);
    ((java.lang.StringBuilder)localObject).append(" AND retry_count =  2147483647 LIMIT 1");
    if (count(((java.lang.StringBuilder)localObject).toString(), null) > 0L) {
      zzgo().zzjg().zzbx("The number of upload retries exceeds the limit. Will remain unchanged.");
    }
    try
    {
      localObject = getWritableDatabase();
      int i = String.valueOf(paramList).length();
      java.lang.StringBuilder localStringBuilder = new java.lang.StringBuilder(i + 127);
      localStringBuilder.append("UPDATE queue SET retry_count = IFNULL(retry_count, 0) + 1 WHERE rowid IN ");
      localStringBuilder.append(paramList);
      localStringBuilder.append(" AND (retry_count IS NULL OR retry_count < 2147483647)");
      ((SQLiteDatabase)localObject).execSQL(localStringBuilder.toString());
      return;
    }
    catch (SQLiteException paramList)
    {
      zzgo().zzjd().append("Error incrementing retry count. error", paramList);
    }
  }
  
  public final boolean add(ComponentInfo paramComponentInfo)
  {
    Preconditions.checkNotNull(paramComponentInfo);
    zzaf();
    zzcl();
    if (get(packageName, zzahb.name) == null) {
      if (count("SELECT COUNT(1) FROM conditional_properties WHERE app_id=?", new String[] { packageName }) >= 1000L) {
        return false;
      }
    }
    Object localObject = new ContentValues();
    ((ContentValues)localObject).put("app_id", packageName);
    ((ContentValues)localObject).put("origin", origin);
    ((ContentValues)localObject).put("name", zzahb.name);
    put((ContentValues)localObject, "value", zzahb.getValue());
    ((ContentValues)localObject).put("active", Boolean.valueOf(active));
    ((ContentValues)localObject).put("trigger_event_name", triggerEventName);
    ((ContentValues)localObject).put("trigger_timeout", Long.valueOf(triggerTimeout));
    zzgm();
    ((ContentValues)localObject).put("timed_out_event", zzfk.marshall(zzahc));
    ((ContentValues)localObject).put("creation_timestamp", Long.valueOf(creationTimestamp));
    zzgm();
    ((ContentValues)localObject).put("triggered_event", zzfk.marshall(zzahd));
    ((ContentValues)localObject).put("triggered_timestamp", Long.valueOf(zzahb.zzaue));
    ((ContentValues)localObject).put("time_to_live", Long.valueOf(timeToLive));
    zzgm();
    ((ContentValues)localObject).put("expired_event", zzfk.marshall(zzahe));
    try
    {
      long l = getWritableDatabase().insertWithOnConflict("conditional_properties", null, (ContentValues)localObject, 5);
      if (l == -1L)
      {
        localObject = zzgo().zzjd();
        String str = packageName;
        ((zzar)localObject).append("Failed to insert/update conditional user property (got -1)", zzap.zzbv(str));
        return true;
      }
    }
    catch (SQLiteException localSQLiteException)
    {
      zzgo().zzjd().append("Error storing conditional user property", zzap.zzbv(packageName), localSQLiteException);
    }
    return true;
  }
  
  public final boolean add(zzfj paramZzfj)
  {
    Preconditions.checkNotNull(paramZzfj);
    zzaf();
    zzcl();
    long l;
    if (get(zztt, name) == null) {
      if (zzfk.zzcq(name))
      {
        if (count("select count(1) from user_attributes where app_id=? and name not like '!_%' escape '!'", new String[] { zztt }) >= 25L) {
          return false;
        }
      }
      else
      {
        l = count("select count(1) from user_attributes where app_id=? and origin=? AND name like '!_%' escape '!'", new String[] { zztt, origin });
        if (zzgq().attribute(zztt, zzaf.zzalj))
        {
          if ((!"_ap".equals(name)) && (l >= 25L)) {
            return false;
          }
        }
        else if (l >= 25L) {
          return false;
        }
      }
    }
    Object localObject = new ContentValues();
    ((ContentValues)localObject).put("app_id", zztt);
    ((ContentValues)localObject).put("origin", origin);
    ((ContentValues)localObject).put("name", name);
    ((ContentValues)localObject).put("set_timestamp", Long.valueOf(zzaue));
    put((ContentValues)localObject, "value", value);
    try
    {
      l = getWritableDatabase().insertWithOnConflict("user_attributes", null, (ContentValues)localObject, 5);
      if (l == -1L)
      {
        localObject = zzgo().zzjd();
        String str = zztt;
        ((zzar)localObject).append("Failed to insert/update user property (got -1). appId", zzap.zzbv(str));
        return true;
      }
    }
    catch (SQLiteException localSQLiteException)
    {
      zzgo().zzjd().append("Error storing user property. appId", zzap.zzbv(zztt), localSQLiteException);
    }
    return true;
  }
  
  public final boolean add(String paramString, Long paramLong, long paramLong1, zzgf paramZzgf)
  {
    zzaf();
    zzcl();
    Preconditions.checkNotNull(paramZzgf);
    Preconditions.checkNotEmpty(paramString);
    Preconditions.checkNotNull(paramLong);
    try
    {
      int i = paramZzgf.zzvu();
      byte[] arrayOfByte = new byte[i];
      i = arrayOfByte.length;
      zzyy localZzyy = zzyy.toString(arrayOfByte, 0, i);
      paramZzgf.multiply(localZzyy);
      localZzyy.zzyt();
      zzgo().zzjl().append("Saving complex main event, appId, data size", zzgl().zzbs(paramString), Integer.valueOf(arrayOfByte.length));
      paramZzgf = new ContentValues();
      paramZzgf.put("app_id", paramString);
      paramZzgf.put("event_id", paramLong);
      paramZzgf.put("children_to_process", Long.valueOf(paramLong1));
      paramZzgf.put("main_event", arrayOfByte);
      try
      {
        paramLong1 = getWritableDatabase().insertWithOnConflict("main_event_params", null, paramZzgf, 5);
        if (paramLong1 == -1L)
        {
          zzgo().zzjd().append("Failed to insert complex main event (got -1). appId", zzap.zzbv(paramString));
          return false;
        }
        return true;
      }
      catch (SQLiteException paramLong)
      {
        zzgo().zzjd().append("Error storing complex main event. appId", zzap.zzbv(paramString), paramLong);
        return false;
      }
      return false;
    }
    catch (IOException paramZzgf)
    {
      zzgo().zzjd().append("Data loss. Failed to serialize event params/data. appId, eventId", zzap.zzbv(paramString), paramLong, paramZzgf);
    }
  }
  
  public final void beginTransaction()
  {
    zzcl();
    getWritableDatabase().beginTransaction();
  }
  
  public final List create(String paramString1, String paramString2, String paramString3)
  {
    Preconditions.checkNotEmpty(paramString1);
    zzaf();
    zzcl();
    ArrayList localArrayList = new ArrayList(3);
    localArrayList.add(paramString1);
    paramString1 = new java.lang.StringBuilder("app_id=?");
    if (!TextUtils.isEmpty(paramString2))
    {
      localArrayList.add(paramString2);
      paramString1.append(" and origin=?");
    }
    if (!TextUtils.isEmpty(paramString3))
    {
      localArrayList.add(String.valueOf(paramString3).concat("*"));
      paramString1.append(" and name glob ?");
    }
    paramString2 = (String[])localArrayList.toArray(new String[localArrayList.size()]);
    return load(paramString1.toString(), paramString2);
  }
  
  /* Error */
  public final List doInBackground(String paramString1, String paramString2, String paramString3)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 212	com/google/android/android/common/internal/Preconditions:checkNotEmpty	(Ljava/lang/String;)Ljava/lang/String;
    //   4: pop
    //   5: aload_0
    //   6: invokevirtual 206	com/google/android/android/measurement/internal/zzco:zzaf	()V
    //   9: aload_0
    //   10: invokevirtual 203	com/google/android/android/measurement/internal/zzez:zzcl	()V
    //   13: new 462	java/util/ArrayList
    //   16: dup
    //   17: invokespecial 463	java/util/ArrayList:<init>	()V
    //   20: astore 12
    //   22: aconst_null
    //   23: astore 9
    //   25: new 462	java/util/ArrayList
    //   28: dup
    //   29: iconst_3
    //   30: invokespecial 811	java/util/ArrayList:<init>	(I)V
    //   33: astore 11
    //   35: aload 11
    //   37: aload_1
    //   38: invokeinterface 479 2 0
    //   43: pop
    //   44: new 487	java/lang/StringBuilder
    //   47: dup
    //   48: ldc_w 813
    //   51: invokespecial 814	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   54: astore 10
    //   56: aload_2
    //   57: invokestatic 228	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   60: istore 5
    //   62: iload 5
    //   64: ifne +28 -> 92
    //   67: aload 11
    //   69: aload_2
    //   70: invokeinterface 479 2 0
    //   75: pop
    //   76: aload 10
    //   78: ldc_w 816
    //   81: invokevirtual 498	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   84: pop
    //   85: goto +7 -> 92
    //   88: astore_3
    //   89: goto +442 -> 531
    //   92: aload_2
    //   93: astore 8
    //   95: aload_3
    //   96: invokestatic 228	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   99: istore 5
    //   101: iload 5
    //   103: ifne +30 -> 133
    //   106: aload 11
    //   108: aload_3
    //   109: invokestatic 257	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   112: ldc_w 818
    //   115: invokevirtual 821	java/lang/String:concat	(Ljava/lang/String;)Ljava/lang/String;
    //   118: invokeinterface 479 2 0
    //   123: pop
    //   124: aload 10
    //   126: ldc_w 823
    //   129: invokevirtual 498	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   132: pop
    //   133: aload 11
    //   135: invokeinterface 468 1 0
    //   140: istore 4
    //   142: iload 4
    //   144: anewarray 19	java/lang/String
    //   147: astore 13
    //   149: aload 11
    //   151: aload 13
    //   153: invokeinterface 827 2 0
    //   158: astore 11
    //   160: aload 11
    //   162: checkcast 828	[Ljava/lang/String;
    //   165: astore 11
    //   167: aload_0
    //   168: invokevirtual 310	com/google/android/android/measurement/internal/StringBuilder:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   171: astore 13
    //   173: aload 10
    //   175: invokevirtual 503	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   178: astore 10
    //   180: aload 13
    //   182: ldc_w 775
    //   185: iconst_4
    //   186: anewarray 19	java/lang/String
    //   189: dup
    //   190: iconst_0
    //   191: ldc_w 574
    //   194: aastore
    //   195: dup
    //   196: iconst_1
    //   197: ldc_w 769
    //   200: aastore
    //   201: dup
    //   202: iconst_2
    //   203: ldc_w 665
    //   206: aastore
    //   207: dup
    //   208: iconst_3
    //   209: ldc 43
    //   211: aastore
    //   212: aload 10
    //   214: aload 11
    //   216: aconst_null
    //   217: aconst_null
    //   218: ldc_w 835
    //   221: ldc_w 837
    //   224: invokevirtual 841	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   227: astore 11
    //   229: aload 11
    //   231: astore_2
    //   232: aload 8
    //   234: astore 9
    //   236: aload 11
    //   238: invokeinterface 368 1 0
    //   243: istore 5
    //   245: iload 5
    //   247: ifne +18 -> 265
    //   250: aload 11
    //   252: ifnull +335 -> 587
    //   255: aload 11
    //   257: invokeinterface 375 1 0
    //   262: aload 12
    //   264: areturn
    //   265: aload 8
    //   267: astore 9
    //   269: aload 12
    //   271: invokeinterface 468 1 0
    //   276: istore 4
    //   278: iload 4
    //   280: sipush 1000
    //   283: if_icmplt +29 -> 312
    //   286: aload 8
    //   288: astore 9
    //   290: aload_0
    //   291: invokevirtual 232	com/google/android/android/measurement/internal/zzco:zzgo	()Lcom/google/android/android/measurement/internal/zzap;
    //   294: invokevirtual 323	com/google/android/android/measurement/internal/zzap:zzjd	()Lcom/google/android/android/measurement/internal/zzar;
    //   297: ldc_w 843
    //   300: sipush 1000
    //   303: invokestatic 250	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   306: invokevirtual 328	com/google/android/android/measurement/internal/zzar:append	(Ljava/lang/String;Ljava/lang/Object;)V
    //   309: goto +145 -> 454
    //   312: aload 8
    //   314: astore 9
    //   316: aload 11
    //   318: iconst_0
    //   319: invokeinterface 399 2 0
    //   324: astore 13
    //   326: aload 8
    //   328: astore 9
    //   330: aload 11
    //   332: iconst_1
    //   333: invokeinterface 372 2 0
    //   338: lstore 6
    //   340: aload_2
    //   341: astore 9
    //   343: aload_0
    //   344: aload 11
    //   346: iconst_2
    //   347: invokespecial 845	com/google/android/android/measurement/internal/StringBuilder:get	(Landroid/database/Cursor;I)Ljava/lang/Object;
    //   350: astore 14
    //   352: aload_2
    //   353: astore 9
    //   355: aload 11
    //   357: iconst_3
    //   358: invokeinterface 399 2 0
    //   363: astore 10
    //   365: aload 10
    //   367: astore 8
    //   369: aload 14
    //   371: ifnonnull +39 -> 410
    //   374: aload_2
    //   375: astore 9
    //   377: aload_0
    //   378: invokevirtual 232	com/google/android/android/measurement/internal/zzco:zzgo	()Lcom/google/android/android/measurement/internal/zzap;
    //   381: invokevirtual 323	com/google/android/android/measurement/internal/zzap:zzjd	()Lcom/google/android/android/measurement/internal/zzar;
    //   384: ldc_w 847
    //   387: aload_1
    //   388: invokestatic 244	com/google/android/android/measurement/internal/zzap:zzbv	(Ljava/lang/String;)Ljava/lang/Object;
    //   391: aload 8
    //   393: aload_3
    //   394: invokevirtual 263	com/google/android/android/measurement/internal/zzar:append	(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V
    //   397: goto +40 -> 437
    //   400: astore_3
    //   401: aload_2
    //   402: astore 10
    //   404: aload 8
    //   406: astore_2
    //   407: goto +127 -> 534
    //   410: aload_2
    //   411: astore 9
    //   413: aload 12
    //   415: new 742	com/google/android/android/measurement/internal/zzfj
    //   418: dup
    //   419: aload_1
    //   420: aload 8
    //   422: aload 13
    //   424: lload 6
    //   426: aload 14
    //   428: invokespecial 850	com/google/android/android/measurement/internal/zzfj:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/Object;)V
    //   431: invokeinterface 479 2 0
    //   436: pop
    //   437: aload_2
    //   438: astore 9
    //   440: aload 11
    //   442: invokeinterface 853 1 0
    //   447: istore 5
    //   449: iload 5
    //   451: ifne +18 -> 469
    //   454: aload 11
    //   456: ifnull +131 -> 587
    //   459: aload 11
    //   461: invokeinterface 375 1 0
    //   466: aload 12
    //   468: areturn
    //   469: aload 10
    //   471: astore 8
    //   473: goto -208 -> 265
    //   476: astore 8
    //   478: goto +3 -> 481
    //   481: aload 10
    //   483: astore_3
    //   484: aload_2
    //   485: astore 10
    //   487: aload_3
    //   488: astore_2
    //   489: aload 8
    //   491: astore_3
    //   492: goto +42 -> 534
    //   495: astore_3
    //   496: aload_2
    //   497: astore 10
    //   499: aload 8
    //   501: astore_2
    //   502: goto +32 -> 534
    //   505: astore_1
    //   506: goto +69 -> 575
    //   509: astore_3
    //   510: aload_2
    //   511: astore 10
    //   513: aload 9
    //   515: astore_2
    //   516: goto +18 -> 534
    //   519: astore_3
    //   520: goto +11 -> 531
    //   523: astore_1
    //   524: aload 9
    //   526: astore_2
    //   527: goto +48 -> 575
    //   530: astore_3
    //   531: aconst_null
    //   532: astore 10
    //   534: aload 10
    //   536: astore 9
    //   538: aload_0
    //   539: invokevirtual 232	com/google/android/android/measurement/internal/zzco:zzgo	()Lcom/google/android/android/measurement/internal/zzap;
    //   542: invokevirtual 323	com/google/android/android/measurement/internal/zzap:zzjd	()Lcom/google/android/android/measurement/internal/zzar;
    //   545: ldc_w 855
    //   548: aload_1
    //   549: invokestatic 244	com/google/android/android/measurement/internal/zzap:zzbv	(Ljava/lang/String;)Ljava/lang/Object;
    //   552: aload_2
    //   553: aload_3
    //   554: invokevirtual 263	com/google/android/android/measurement/internal/zzar:append	(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V
    //   557: aload 10
    //   559: ifnull +31 -> 590
    //   562: aload 10
    //   564: invokeinterface 375 1 0
    //   569: aconst_null
    //   570: areturn
    //   571: astore_1
    //   572: aload 9
    //   574: astore_2
    //   575: aload_2
    //   576: ifnull +9 -> 585
    //   579: aload_2
    //   580: invokeinterface 375 1 0
    //   585: aload_1
    //   586: athrow
    //   587: aload 12
    //   589: areturn
    //   590: aconst_null
    //   591: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	592	0	this	StringBuilder
    //   0	592	1	paramString1	String
    //   0	592	2	paramString2	String
    //   0	592	3	paramString3	String
    //   140	144	4	i	int
    //   60	390	5	bool	boolean
    //   338	87	6	l	long
    //   93	379	8	localObject1	Object
    //   476	24	8	localSQLiteException	SQLiteException
    //   23	550	9	localObject2	Object
    //   54	509	10	localObject3	Object
    //   33	427	11	localObject4	Object
    //   20	568	12	localArrayList	ArrayList
    //   147	276	13	localObject5	Object
    //   350	77	14	localObject6	Object
    // Exception table:
    //   from	to	target	type
    //   67	85	88	android/database/sqlite/SQLiteException
    //   95	101	88	android/database/sqlite/SQLiteException
    //   106	133	88	android/database/sqlite/SQLiteException
    //   133	142	88	android/database/sqlite/SQLiteException
    //   149	160	88	android/database/sqlite/SQLiteException
    //   167	173	88	android/database/sqlite/SQLiteException
    //   173	229	88	android/database/sqlite/SQLiteException
    //   377	397	400	android/database/sqlite/SQLiteException
    //   413	437	476	android/database/sqlite/SQLiteException
    //   440	449	476	android/database/sqlite/SQLiteException
    //   343	352	495	android/database/sqlite/SQLiteException
    //   355	365	495	android/database/sqlite/SQLiteException
    //   236	245	505	java/lang/Throwable
    //   269	278	505	java/lang/Throwable
    //   290	309	505	java/lang/Throwable
    //   316	326	505	java/lang/Throwable
    //   330	340	505	java/lang/Throwable
    //   236	245	509	android/database/sqlite/SQLiteException
    //   269	278	509	android/database/sqlite/SQLiteException
    //   290	309	509	android/database/sqlite/SQLiteException
    //   316	326	509	android/database/sqlite/SQLiteException
    //   330	340	509	android/database/sqlite/SQLiteException
    //   35	44	519	android/database/sqlite/SQLiteException
    //   44	62	519	android/database/sqlite/SQLiteException
    //   25	35	523	java/lang/Throwable
    //   35	44	523	java/lang/Throwable
    //   44	62	523	java/lang/Throwable
    //   67	85	523	java/lang/Throwable
    //   95	101	523	java/lang/Throwable
    //   106	133	523	java/lang/Throwable
    //   133	142	523	java/lang/Throwable
    //   142	149	523	java/lang/Throwable
    //   149	160	523	java/lang/Throwable
    //   160	167	523	java/lang/Throwable
    //   167	173	523	java/lang/Throwable
    //   173	229	523	java/lang/Throwable
    //   25	35	530	android/database/sqlite/SQLiteException
    //   343	352	571	java/lang/Throwable
    //   355	365	571	java/lang/Throwable
    //   377	397	571	java/lang/Throwable
    //   413	437	571	java/lang/Throwable
    //   440	449	571	java/lang/Throwable
    //   538	557	571	java/lang/Throwable
  }
  
  public final void endTransaction()
  {
    zzcl();
    getWritableDatabase().endTransaction();
  }
  
  /* Error */
  public final Pair get(String paramString, Long paramLong)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 206	com/google/android/android/measurement/internal/zzco:zzaf	()V
    //   4: aload_0
    //   5: invokevirtual 203	com/google/android/android/measurement/internal/zzez:zzcl	()V
    //   8: aload_0
    //   9: invokevirtual 310	com/google/android/android/measurement/internal/StringBuilder:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   12: astore 7
    //   14: aload_2
    //   15: invokestatic 257	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   18: astore 8
    //   20: aload 7
    //   22: ldc_w 858
    //   25: iconst_2
    //   26: anewarray 19	java/lang/String
    //   29: dup
    //   30: iconst_0
    //   31: aload_1
    //   32: aastore
    //   33: dup
    //   34: iconst_1
    //   35: aload 8
    //   37: aastore
    //   38: invokevirtual 362	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   41: astore 9
    //   43: aload 9
    //   45: astore 8
    //   47: aload 8
    //   49: astore 7
    //   51: aload 9
    //   53: invokeinterface 368 1 0
    //   58: istore 4
    //   60: iload 4
    //   62: ifne +34 -> 96
    //   65: aload 8
    //   67: astore 7
    //   69: aload_0
    //   70: invokevirtual 232	com/google/android/android/measurement/internal/zzco:zzgo	()Lcom/google/android/android/measurement/internal/zzap;
    //   73: invokevirtual 783	com/google/android/android/measurement/internal/zzap:zzjl	()Lcom/google/android/android/measurement/internal/zzar;
    //   76: ldc_w 860
    //   79: invokevirtual 395	com/google/android/android/measurement/internal/zzar:zzbx	(Ljava/lang/String;)V
    //   82: aload 9
    //   84: ifnull +217 -> 301
    //   87: aload 9
    //   89: invokeinterface 375 1 0
    //   94: aconst_null
    //   95: areturn
    //   96: aload 8
    //   98: astore 7
    //   100: aload 9
    //   102: iconst_0
    //   103: invokeinterface 864 2 0
    //   108: astore 10
    //   110: aload 8
    //   112: astore 7
    //   114: aload 9
    //   116: iconst_1
    //   117: invokeinterface 372 2 0
    //   122: lstore 5
    //   124: aload 8
    //   126: astore 7
    //   128: aload 10
    //   130: arraylength
    //   131: istore_3
    //   132: aload 8
    //   134: astore 7
    //   136: aload 10
    //   138: iconst_0
    //   139: iload_3
    //   140: invokestatic 869	com/google/android/android/internal/measurement/zzyx:get	([BII)Lcom/google/android/android/internal/measurement/zzyx;
    //   143: astore 10
    //   145: aload 8
    //   147: astore 7
    //   149: new 871	com/google/android/android/internal/measurement/zzgf
    //   152: dup
    //   153: invokespecial 872	com/google/android/android/internal/measurement/zzgf:<init>	()V
    //   156: astore 11
    //   158: aload 8
    //   160: astore 7
    //   162: aload 11
    //   164: aload 10
    //   166: invokevirtual 876	com/google/android/android/internal/measurement/zzzg:digest	(Lcom/google/android/android/internal/measurement/zzyx;)Lcom/google/android/android/internal/measurement/zzzg;
    //   169: pop
    //   170: aload 8
    //   172: astore 7
    //   174: aload 11
    //   176: lload 5
    //   178: invokestatic 413	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   181: invokestatic 881	android/util/Pair:create	(Ljava/lang/Object;Ljava/lang/Object;)Landroid/util/Pair;
    //   184: astore_1
    //   185: aload 9
    //   187: ifnull +116 -> 303
    //   190: aload 9
    //   192: invokeinterface 375 1 0
    //   197: aload_1
    //   198: areturn
    //   199: astore 10
    //   201: aload 8
    //   203: astore 7
    //   205: aload_0
    //   206: invokevirtual 232	com/google/android/android/measurement/internal/zzco:zzgo	()Lcom/google/android/android/measurement/internal/zzap;
    //   209: invokevirtual 323	com/google/android/android/measurement/internal/zzap:zzjd	()Lcom/google/android/android/measurement/internal/zzar;
    //   212: ldc_w 883
    //   215: aload_1
    //   216: invokestatic 244	com/google/android/android/measurement/internal/zzap:zzbv	(Ljava/lang/String;)Ljava/lang/Object;
    //   219: aload_2
    //   220: aload 10
    //   222: invokevirtual 263	com/google/android/android/measurement/internal/zzar:append	(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V
    //   225: aload 9
    //   227: ifnull +78 -> 305
    //   230: aload 9
    //   232: invokeinterface 375 1 0
    //   237: aconst_null
    //   238: areturn
    //   239: astore_1
    //   240: goto +14 -> 254
    //   243: astore_1
    //   244: aconst_null
    //   245: astore 7
    //   247: goto +40 -> 287
    //   250: astore_1
    //   251: aconst_null
    //   252: astore 8
    //   254: aload 8
    //   256: astore 7
    //   258: aload_0
    //   259: invokevirtual 232	com/google/android/android/measurement/internal/zzco:zzgo	()Lcom/google/android/android/measurement/internal/zzap;
    //   262: invokevirtual 323	com/google/android/android/measurement/internal/zzap:zzjd	()Lcom/google/android/android/measurement/internal/zzar;
    //   265: ldc_w 885
    //   268: aload_1
    //   269: invokevirtual 328	com/google/android/android/measurement/internal/zzar:append	(Ljava/lang/String;Ljava/lang/Object;)V
    //   272: aload 8
    //   274: ifnull +31 -> 305
    //   277: aload 8
    //   279: invokeinterface 375 1 0
    //   284: aconst_null
    //   285: areturn
    //   286: astore_1
    //   287: aload 7
    //   289: ifnull +10 -> 299
    //   292: aload 7
    //   294: invokeinterface 375 1 0
    //   299: aload_1
    //   300: athrow
    //   301: aconst_null
    //   302: areturn
    //   303: aload_1
    //   304: areturn
    //   305: aconst_null
    //   306: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	307	0	this	StringBuilder
    //   0	307	1	paramString	String
    //   0	307	2	paramLong	Long
    //   131	9	3	i	int
    //   58	3	4	bool	boolean
    //   122	55	5	l	long
    //   12	281	7	localObject1	Object
    //   18	260	8	localObject2	Object
    //   41	190	9	localCursor	Cursor
    //   108	57	10	localObject3	Object
    //   199	22	10	localIOException	IOException
    //   156	19	11	localZzgf	zzgf
    // Exception table:
    //   from	to	target	type
    //   162	170	199	java/io/IOException
    //   51	60	239	android/database/sqlite/SQLiteException
    //   69	82	239	android/database/sqlite/SQLiteException
    //   100	110	239	android/database/sqlite/SQLiteException
    //   114	124	239	android/database/sqlite/SQLiteException
    //   136	145	239	android/database/sqlite/SQLiteException
    //   149	158	239	android/database/sqlite/SQLiteException
    //   162	170	239	android/database/sqlite/SQLiteException
    //   174	185	239	android/database/sqlite/SQLiteException
    //   205	225	239	android/database/sqlite/SQLiteException
    //   8	14	243	java/lang/Throwable
    //   14	20	243	java/lang/Throwable
    //   20	43	243	java/lang/Throwable
    //   8	14	250	android/database/sqlite/SQLiteException
    //   14	20	250	android/database/sqlite/SQLiteException
    //   20	43	250	android/database/sqlite/SQLiteException
    //   51	60	286	java/lang/Throwable
    //   69	82	286	java/lang/Throwable
    //   100	110	286	java/lang/Throwable
    //   114	124	286	java/lang/Throwable
    //   128	132	286	java/lang/Throwable
    //   136	145	286	java/lang/Throwable
    //   149	158	286	java/lang/Throwable
    //   162	170	286	java/lang/Throwable
    //   174	185	286	java/lang/Throwable
    //   205	225	286	java/lang/Throwable
    //   258	272	286	java/lang/Throwable
  }
  
  /* Error */
  public final zzfj get(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 212	com/google/android/android/common/internal/Preconditions:checkNotEmpty	(Ljava/lang/String;)Ljava/lang/String;
    //   4: pop
    //   5: aload_2
    //   6: invokestatic 212	com/google/android/android/common/internal/Preconditions:checkNotEmpty	(Ljava/lang/String;)Ljava/lang/String;
    //   9: pop
    //   10: aload_0
    //   11: invokevirtual 206	com/google/android/android/measurement/internal/zzco:zzaf	()V
    //   14: aload_0
    //   15: invokevirtual 203	com/google/android/android/measurement/internal/zzez:zzcl	()V
    //   18: aload_0
    //   19: invokevirtual 310	com/google/android/android/measurement/internal/StringBuilder:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   22: astore 6
    //   24: aload 6
    //   26: ldc_w 775
    //   29: iconst_3
    //   30: anewarray 19	java/lang/String
    //   33: dup
    //   34: iconst_0
    //   35: ldc_w 769
    //   38: aastore
    //   39: dup
    //   40: iconst_1
    //   41: ldc_w 665
    //   44: aastore
    //   45: dup
    //   46: iconst_2
    //   47: ldc 43
    //   49: aastore
    //   50: ldc_w 887
    //   53: iconst_2
    //   54: anewarray 19	java/lang/String
    //   57: dup
    //   58: iconst_0
    //   59: aload_1
    //   60: aastore
    //   61: dup
    //   62: iconst_1
    //   63: aload_2
    //   64: aastore
    //   65: aconst_null
    //   66: aconst_null
    //   67: aconst_null
    //   68: invokevirtual 890	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   71: astore 8
    //   73: aload 8
    //   75: astore 6
    //   77: aload 8
    //   79: invokeinterface 368 1 0
    //   84: istore_3
    //   85: iload_3
    //   86: ifne +17 -> 103
    //   89: aload 8
    //   91: ifnull +212 -> 303
    //   94: aload 8
    //   96: invokeinterface 375 1 0
    //   101: aconst_null
    //   102: areturn
    //   103: aload 8
    //   105: iconst_0
    //   106: invokeinterface 372 2 0
    //   111: lstore 4
    //   113: aload 6
    //   115: astore 7
    //   117: aload_0
    //   118: aload 8
    //   120: iconst_1
    //   121: invokespecial 845	com/google/android/android/measurement/internal/StringBuilder:get	(Landroid/database/Cursor;I)Ljava/lang/Object;
    //   124: astore 9
    //   126: aload 6
    //   128: astore 7
    //   130: aload 8
    //   132: iconst_2
    //   133: invokeinterface 399 2 0
    //   138: astore 10
    //   140: aload 6
    //   142: astore 7
    //   144: new 742	com/google/android/android/measurement/internal/zzfj
    //   147: dup
    //   148: aload_1
    //   149: aload 10
    //   151: aload_2
    //   152: lload 4
    //   154: aload 9
    //   156: invokespecial 850	com/google/android/android/measurement/internal/zzfj:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/Object;)V
    //   159: astore 9
    //   161: aload 6
    //   163: astore 7
    //   165: aload 8
    //   167: invokeinterface 853 1 0
    //   172: istore_3
    //   173: iload_3
    //   174: ifeq +24 -> 198
    //   177: aload 6
    //   179: astore 7
    //   181: aload_0
    //   182: invokevirtual 232	com/google/android/android/measurement/internal/zzco:zzgo	()Lcom/google/android/android/measurement/internal/zzap;
    //   185: invokevirtual 323	com/google/android/android/measurement/internal/zzap:zzjd	()Lcom/google/android/android/measurement/internal/zzar;
    //   188: ldc_w 892
    //   191: aload_1
    //   192: invokestatic 244	com/google/android/android/measurement/internal/zzap:zzbv	(Ljava/lang/String;)Ljava/lang/Object;
    //   195: invokevirtual 328	com/google/android/android/measurement/internal/zzar:append	(Ljava/lang/String;Ljava/lang/Object;)V
    //   198: aload 8
    //   200: ifnull +105 -> 305
    //   203: aload 8
    //   205: invokeinterface 375 1 0
    //   210: aload 9
    //   212: areturn
    //   213: astore 8
    //   215: goto +24 -> 239
    //   218: astore_1
    //   219: goto +70 -> 289
    //   222: astore 8
    //   224: goto +15 -> 239
    //   227: astore_1
    //   228: aconst_null
    //   229: astore 6
    //   231: goto +58 -> 289
    //   234: astore 8
    //   236: aconst_null
    //   237: astore 6
    //   239: aload 6
    //   241: astore 7
    //   243: aload_0
    //   244: invokevirtual 232	com/google/android/android/measurement/internal/zzco:zzgo	()Lcom/google/android/android/measurement/internal/zzap;
    //   247: invokevirtual 323	com/google/android/android/measurement/internal/zzap:zzjd	()Lcom/google/android/android/measurement/internal/zzar;
    //   250: ldc_w 894
    //   253: aload_1
    //   254: invokestatic 244	com/google/android/android/measurement/internal/zzap:zzbv	(Ljava/lang/String;)Ljava/lang/Object;
    //   257: aload_0
    //   258: invokevirtual 789	com/google/android/android/measurement/internal/zzco:zzgl	()Lcom/google/android/android/measurement/internal/zzan;
    //   261: aload_2
    //   262: invokevirtual 897	com/google/android/android/measurement/internal/zzan:zzbu	(Ljava/lang/String;)Ljava/lang/String;
    //   265: aload 8
    //   267: invokevirtual 263	com/google/android/android/measurement/internal/zzar:append	(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V
    //   270: aload 6
    //   272: ifnull +36 -> 308
    //   275: aload 6
    //   277: invokeinterface 375 1 0
    //   282: aconst_null
    //   283: areturn
    //   284: astore_1
    //   285: aload 7
    //   287: astore 6
    //   289: aload 6
    //   291: ifnull +10 -> 301
    //   294: aload 6
    //   296: invokeinterface 375 1 0
    //   301: aload_1
    //   302: athrow
    //   303: aconst_null
    //   304: areturn
    //   305: aload 9
    //   307: areturn
    //   308: aconst_null
    //   309: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	310	0	this	StringBuilder
    //   0	310	1	paramString1	String
    //   0	310	2	paramString2	String
    //   84	90	3	bool	boolean
    //   111	42	4	l	long
    //   22	273	6	localObject1	Object
    //   115	171	7	localObject2	Object
    //   71	133	8	localCursor	Cursor
    //   213	1	8	localSQLiteException1	SQLiteException
    //   222	1	8	localSQLiteException2	SQLiteException
    //   234	32	8	localSQLiteException3	SQLiteException
    //   124	182	9	localObject3	Object
    //   138	12	10	str	String
    // Exception table:
    //   from	to	target	type
    //   117	126	213	android/database/sqlite/SQLiteException
    //   130	140	213	android/database/sqlite/SQLiteException
    //   144	161	213	android/database/sqlite/SQLiteException
    //   165	173	213	android/database/sqlite/SQLiteException
    //   181	198	213	android/database/sqlite/SQLiteException
    //   77	85	218	java/lang/Throwable
    //   103	113	218	java/lang/Throwable
    //   77	85	222	android/database/sqlite/SQLiteException
    //   103	113	222	android/database/sqlite/SQLiteException
    //   18	24	227	java/lang/Throwable
    //   24	73	227	java/lang/Throwable
    //   18	24	234	android/database/sqlite/SQLiteException
    //   24	73	234	android/database/sqlite/SQLiteException
    //   117	126	284	java/lang/Throwable
    //   130	140	284	java/lang/Throwable
    //   144	161	284	java/lang/Throwable
    //   165	173	284	java/lang/Throwable
    //   181	198	284	java/lang/Throwable
    //   243	270	284	java/lang/Throwable
  }
  
  public final List getAll(String paramString, int paramInt1, int paramInt2)
  {
    zzaf();
    zzcl();
    boolean bool;
    if (paramInt1 > 0) {
      bool = true;
    } else {
      bool = false;
    }
    Preconditions.checkArgument(bool);
    if (paramInt2 > 0) {
      bool = true;
    } else {
      bool = false;
    }
    Preconditions.checkArgument(bool);
    Preconditions.checkNotEmpty(paramString);
    byte[] arrayOfByte = null;
    Object localObject2 = null;
    Object localObject1 = localObject2;
    try
    {
      Object localObject3 = getWritableDatabase();
      localObject1 = localObject2;
      localObject2 = ((SQLiteDatabase)localObject3).query("queue", new String[] { "rowid", "data", "retry_count" }, "app_id=?", new String[] { paramString }, null, null, "rowid", String.valueOf(paramInt1));
      localObject1 = localObject2;
      try
      {
        bool = ((Cursor)localObject2).moveToFirst();
        if (!bool)
        {
          localObject3 = Collections.emptyList();
          if (localObject2 == null) {
            break label493;
          }
          ((Cursor)localObject2).close();
          return localObject3;
        }
        localObject3 = new ArrayList();
        paramInt1 = 0;
        int i;
        do
        {
          long l = ((Cursor)localObject2).getLong(0);
          try
          {
            arrayOfByte = ((Cursor)localObject2).getBlob(1);
            arrayOfByte = zzjo().decompress(arrayOfByte);
            bool = ((List)localObject3).isEmpty();
            if (!bool)
            {
              i = arrayOfByte.length;
              if (i + paramInt1 > paramInt2) {
                break;
              }
            }
            i = arrayOfByte.length;
            zzyx localZzyx = zzyx.get(arrayOfByte, 0, i);
            zzgi localZzgi = new zzgi();
            try
            {
              localZzgi.digest(localZzyx);
              bool = ((Cursor)localObject2).isNull(2);
              if (!bool)
              {
                i = ((Cursor)localObject2).getInt(2);
                zzayc = Integer.valueOf(i);
              }
              i = arrayOfByte.length;
              i = paramInt1 + i;
              ((List)localObject3).add(Pair.create(localZzgi, Long.valueOf(l)));
            }
            catch (IOException localIOException1)
            {
              zzgo().zzjd().append("Failed to merge queued bundle. appId", zzap.zzbv(paramString), localIOException1);
              i = paramInt1;
            }
            bool = ((Cursor)localObject2).moveToNext();
          }
          catch (IOException localIOException2)
          {
            zzgo().zzjd().append("Failed to unzip queued bundle. appId", zzap.zzbv(paramString), localIOException2);
            i = paramInt1;
          }
          if (!bool) {
            break;
          }
          paramInt1 = i;
        } while (i <= paramInt2);
        if (localObject2 == null) {
          break label496;
        }
        ((Cursor)localObject2).close();
        return localObject3;
      }
      catch (Throwable paramString)
      {
        break label479;
      }
      catch (SQLiteException localSQLiteException1) {}
      localObject1 = localObject2;
    }
    catch (Throwable paramString) {}catch (SQLiteException localSQLiteException2)
    {
      localObject2 = localIOException2;
    }
    zzgo().zzjd().append("Error querying bundles. appId", zzap.zzbv(paramString), localSQLiteException2);
    localObject1 = localObject2;
    paramString = Collections.emptyList();
    if (localObject2 != null)
    {
      ((Cursor)localObject2).close();
      return paramString;
      label479:
      if (localObject1 != null) {
        localObject1.close();
      }
      throw paramString;
      label493:
      return localSQLiteException2;
      label496:
      return localSQLiteException2;
    }
    return paramString;
  }
  
  final Map getAll(String paramString1, String paramString2)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: fail exe a17 = a16\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:92)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.dfs(Cfg.java:255)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze0(BaseAnalyze.java:75)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze(BaseAnalyze.java:69)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer.transform(UnSSATransformer.java:274)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:163)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\nCaused by: java.lang.NullPointerException\n");
  }
  
  public final int getIdentifier(String paramString1, String paramString2)
  {
    Preconditions.checkNotEmpty(paramString1);
    Preconditions.checkNotEmpty(paramString2);
    zzaf();
    zzcl();
    try
    {
      SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
      int i = localSQLiteDatabase.delete("conditional_properties", "app_id=? and name=?", new String[] { paramString1, paramString2 });
      return i;
    }
    catch (SQLiteException localSQLiteException)
    {
      zzgo().zzjd().append("Error deleting conditional property", zzap.zzbv(paramString1), zzgl().zzbu(paramString2), localSQLiteException);
    }
    return 0;
  }
  
  /* Error */
  public final ComponentInfo getMedia(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 212	com/google/android/android/common/internal/Preconditions:checkNotEmpty	(Ljava/lang/String;)Ljava/lang/String;
    //   4: pop
    //   5: aload_2
    //   6: invokestatic 212	com/google/android/android/common/internal/Preconditions:checkNotEmpty	(Ljava/lang/String;)Ljava/lang/String;
    //   9: pop
    //   10: aload_0
    //   11: invokevirtual 206	com/google/android/android/measurement/internal/zzco:zzaf	()V
    //   14: aload_0
    //   15: invokevirtual 203	com/google/android/android/measurement/internal/zzez:zzcl	()V
    //   18: aload_0
    //   19: invokevirtual 310	com/google/android/android/measurement/internal/StringBuilder:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   22: astore 13
    //   24: aload 13
    //   26: ldc_w 735
    //   29: bipush 11
    //   31: anewarray 19	java/lang/String
    //   34: dup
    //   35: iconst_0
    //   36: ldc 43
    //   38: aastore
    //   39: dup
    //   40: iconst_1
    //   41: ldc_w 665
    //   44: aastore
    //   45: dup
    //   46: iconst_2
    //   47: ldc_w 673
    //   50: aastore
    //   51: dup
    //   52: iconst_3
    //   53: ldc_w 684
    //   56: aastore
    //   57: dup
    //   58: iconst_4
    //   59: ldc_w 689
    //   62: aastore
    //   63: dup
    //   64: iconst_5
    //   65: ldc_w 698
    //   68: aastore
    //   69: dup
    //   70: bipush 6
    //   72: ldc_w 710
    //   75: aastore
    //   76: dup
    //   77: bipush 7
    //   79: ldc_w 715
    //   82: aastore
    //   83: dup
    //   84: bipush 8
    //   86: ldc_w 720
    //   89: aastore
    //   90: dup
    //   91: bipush 9
    //   93: ldc_w 725
    //   96: aastore
    //   97: dup
    //   98: bipush 10
    //   100: ldc_w 730
    //   103: aastore
    //   104: ldc_w 887
    //   107: iconst_2
    //   108: anewarray 19	java/lang/String
    //   111: dup
    //   112: iconst_0
    //   113: aload_1
    //   114: aastore
    //   115: dup
    //   116: iconst_1
    //   117: aload_2
    //   118: aastore
    //   119: aconst_null
    //   120: aconst_null
    //   121: aconst_null
    //   122: invokevirtual 890	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   125: astore 15
    //   127: aload 15
    //   129: astore 13
    //   131: aload 15
    //   133: invokeinterface 368 1 0
    //   138: istore 4
    //   140: iload 4
    //   142: ifne +17 -> 159
    //   145: aload 15
    //   147: ifnull +519 -> 666
    //   150: aload 15
    //   152: invokeinterface 375 1 0
    //   157: aconst_null
    //   158: areturn
    //   159: aload 15
    //   161: iconst_0
    //   162: invokeinterface 399 2 0
    //   167: astore 16
    //   169: aload 13
    //   171: astore 14
    //   173: aload_0
    //   174: aload 15
    //   176: iconst_1
    //   177: invokespecial 845	com/google/android/android/measurement/internal/StringBuilder:get	(Landroid/database/Cursor;I)Ljava/lang/Object;
    //   180: astore 18
    //   182: aload 13
    //   184: astore 14
    //   186: aload 15
    //   188: iconst_2
    //   189: invokeinterface 937 2 0
    //   194: istore_3
    //   195: iload_3
    //   196: ifeq +9 -> 205
    //   199: iconst_1
    //   200: istore 4
    //   202: goto +6 -> 208
    //   205: iconst_0
    //   206: istore 4
    //   208: aload 13
    //   210: astore 14
    //   212: aload 15
    //   214: iconst_3
    //   215: invokeinterface 399 2 0
    //   220: astore 17
    //   222: aload 13
    //   224: astore 14
    //   226: aload 15
    //   228: iconst_4
    //   229: invokeinterface 372 2 0
    //   234: lstore 5
    //   236: aload 13
    //   238: astore 14
    //   240: aload_0
    //   241: invokevirtual 919	com/google/android/android/measurement/internal/zzey:zzjo	()Lcom/google/android/android/measurement/internal/zzfg;
    //   244: astore 19
    //   246: aload 13
    //   248: astore 14
    //   250: aload 15
    //   252: iconst_5
    //   253: invokeinterface 864 2 0
    //   258: astore 20
    //   260: aload 13
    //   262: astore 14
    //   264: getstatic 966	com/google/android/android/measurement/internal/zzad:CREATOR	Landroid/os/Parcelable$Creator;
    //   267: astore 21
    //   269: aload 13
    //   271: astore 14
    //   273: aload 19
    //   275: aload 20
    //   277: aload 21
    //   279: invokevirtual 970	com/google/android/android/measurement/internal/zzfg:unmarshall	([BLandroid/os/Parcelable$Creator;)Landroid/os/Parcelable;
    //   282: astore 19
    //   284: aload 13
    //   286: astore 14
    //   288: aload 19
    //   290: checkcast 962	com/google/android/android/measurement/internal/zzad
    //   293: astore 19
    //   295: aload 13
    //   297: astore 14
    //   299: aload 15
    //   301: bipush 6
    //   303: invokeinterface 372 2 0
    //   308: lstore 7
    //   310: aload 13
    //   312: astore 14
    //   314: aload_0
    //   315: invokevirtual 919	com/google/android/android/measurement/internal/zzey:zzjo	()Lcom/google/android/android/measurement/internal/zzfg;
    //   318: astore 20
    //   320: aload 13
    //   322: astore 14
    //   324: aload 15
    //   326: bipush 7
    //   328: invokeinterface 864 2 0
    //   333: astore 21
    //   335: aload 13
    //   337: astore 14
    //   339: getstatic 966	com/google/android/android/measurement/internal/zzad:CREATOR	Landroid/os/Parcelable$Creator;
    //   342: astore 22
    //   344: aload 13
    //   346: astore 14
    //   348: aload 20
    //   350: aload 21
    //   352: aload 22
    //   354: invokevirtual 970	com/google/android/android/measurement/internal/zzfg:unmarshall	([BLandroid/os/Parcelable$Creator;)Landroid/os/Parcelable;
    //   357: astore 20
    //   359: aload 13
    //   361: astore 14
    //   363: aload 20
    //   365: checkcast 962	com/google/android/android/measurement/internal/zzad
    //   368: astore 20
    //   370: aload 13
    //   372: astore 14
    //   374: aload 15
    //   376: bipush 8
    //   378: invokeinterface 372 2 0
    //   383: lstore 9
    //   385: aload 13
    //   387: astore 14
    //   389: aload 15
    //   391: bipush 9
    //   393: invokeinterface 372 2 0
    //   398: lstore 11
    //   400: aload 13
    //   402: astore 14
    //   404: aload_0
    //   405: invokevirtual 919	com/google/android/android/measurement/internal/zzey:zzjo	()Lcom/google/android/android/measurement/internal/zzfg;
    //   408: astore 21
    //   410: aload 13
    //   412: astore 14
    //   414: aload 15
    //   416: bipush 10
    //   418: invokeinterface 864 2 0
    //   423: astore 22
    //   425: aload 13
    //   427: astore 14
    //   429: getstatic 966	com/google/android/android/measurement/internal/zzad:CREATOR	Landroid/os/Parcelable$Creator;
    //   432: astore 23
    //   434: aload 13
    //   436: astore 14
    //   438: aload 21
    //   440: aload 22
    //   442: aload 23
    //   444: invokevirtual 970	com/google/android/android/measurement/internal/zzfg:unmarshall	([BLandroid/os/Parcelable$Creator;)Landroid/os/Parcelable;
    //   447: astore 21
    //   449: aload 13
    //   451: astore 14
    //   453: aload 21
    //   455: checkcast 962	com/google/android/android/measurement/internal/zzad
    //   458: astore 21
    //   460: aload 13
    //   462: astore 14
    //   464: new 653	com/google/android/android/measurement/internal/zzfh
    //   467: dup
    //   468: aload_2
    //   469: lload 9
    //   471: aload 18
    //   473: aload 16
    //   475: invokespecial 973	com/google/android/android/measurement/internal/zzfh:<init>	(Ljava/lang/String;JLjava/lang/Object;Ljava/lang/String;)V
    //   478: astore 18
    //   480: aload 13
    //   482: astore 14
    //   484: new 644	com/google/android/android/measurement/internal/ComponentInfo
    //   487: dup
    //   488: aload_1
    //   489: aload 16
    //   491: aload 18
    //   493: lload 7
    //   495: iload 4
    //   497: aload 17
    //   499: aload 19
    //   501: lload 5
    //   503: aload 20
    //   505: lload 11
    //   507: aload 21
    //   509: invokespecial 976	com/google/android/android/measurement/internal/ComponentInfo:<init>	(Ljava/lang/String;Ljava/lang/String;Lcom/google/android/android/measurement/internal/zzfh;JZLjava/lang/String;Lcom/google/android/android/measurement/internal/zzad;JLcom/google/android/android/measurement/internal/zzad;JLcom/google/android/android/measurement/internal/zzad;)V
    //   512: astore 16
    //   514: aload 13
    //   516: astore 14
    //   518: aload 15
    //   520: invokeinterface 853 1 0
    //   525: istore 4
    //   527: iload 4
    //   529: ifeq +32 -> 561
    //   532: aload 13
    //   534: astore 14
    //   536: aload_0
    //   537: invokevirtual 232	com/google/android/android/measurement/internal/zzco:zzgo	()Lcom/google/android/android/measurement/internal/zzap;
    //   540: invokevirtual 323	com/google/android/android/measurement/internal/zzap:zzjd	()Lcom/google/android/android/measurement/internal/zzar;
    //   543: ldc_w 978
    //   546: aload_1
    //   547: invokestatic 244	com/google/android/android/measurement/internal/zzap:zzbv	(Ljava/lang/String;)Ljava/lang/Object;
    //   550: aload_0
    //   551: invokevirtual 789	com/google/android/android/measurement/internal/zzco:zzgl	()Lcom/google/android/android/measurement/internal/zzan;
    //   554: aload_2
    //   555: invokevirtual 897	com/google/android/android/measurement/internal/zzan:zzbu	(Ljava/lang/String;)Ljava/lang/String;
    //   558: invokevirtual 333	com/google/android/android/measurement/internal/zzar:append	(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    //   561: aload 15
    //   563: ifnull +105 -> 668
    //   566: aload 15
    //   568: invokeinterface 375 1 0
    //   573: aload 16
    //   575: areturn
    //   576: astore 15
    //   578: goto +24 -> 602
    //   581: astore_1
    //   582: goto +70 -> 652
    //   585: astore 15
    //   587: goto +15 -> 602
    //   590: astore_1
    //   591: aconst_null
    //   592: astore 13
    //   594: goto +58 -> 652
    //   597: astore 15
    //   599: aconst_null
    //   600: astore 13
    //   602: aload 13
    //   604: astore 14
    //   606: aload_0
    //   607: invokevirtual 232	com/google/android/android/measurement/internal/zzco:zzgo	()Lcom/google/android/android/measurement/internal/zzap;
    //   610: invokevirtual 323	com/google/android/android/measurement/internal/zzap:zzjd	()Lcom/google/android/android/measurement/internal/zzar;
    //   613: ldc_w 980
    //   616: aload_1
    //   617: invokestatic 244	com/google/android/android/measurement/internal/zzap:zzbv	(Ljava/lang/String;)Ljava/lang/Object;
    //   620: aload_0
    //   621: invokevirtual 789	com/google/android/android/measurement/internal/zzco:zzgl	()Lcom/google/android/android/measurement/internal/zzan;
    //   624: aload_2
    //   625: invokevirtual 897	com/google/android/android/measurement/internal/zzan:zzbu	(Ljava/lang/String;)Ljava/lang/String;
    //   628: aload 15
    //   630: invokevirtual 263	com/google/android/android/measurement/internal/zzar:append	(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V
    //   633: aload 13
    //   635: ifnull +36 -> 671
    //   638: aload 13
    //   640: invokeinterface 375 1 0
    //   645: aconst_null
    //   646: areturn
    //   647: astore_1
    //   648: aload 14
    //   650: astore 13
    //   652: aload 13
    //   654: ifnull +10 -> 664
    //   657: aload 13
    //   659: invokeinterface 375 1 0
    //   664: aload_1
    //   665: athrow
    //   666: aconst_null
    //   667: areturn
    //   668: aload 16
    //   670: areturn
    //   671: aconst_null
    //   672: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	673	0	this	StringBuilder
    //   0	673	1	paramString1	String
    //   0	673	2	paramString2	String
    //   194	2	3	i	int
    //   138	390	4	bool	boolean
    //   234	268	5	l1	long
    //   308	186	7	l2	long
    //   383	87	9	l3	long
    //   398	108	11	l4	long
    //   22	636	13	localObject1	Object
    //   171	478	14	localObject2	Object
    //   125	442	15	localCursor	Cursor
    //   576	1	15	localSQLiteException1	SQLiteException
    //   585	1	15	localSQLiteException2	SQLiteException
    //   597	32	15	localSQLiteException3	SQLiteException
    //   167	502	16	localObject3	Object
    //   220	278	17	str	String
    //   180	312	18	localObject4	Object
    //   244	256	19	localObject5	Object
    //   258	246	20	localObject6	Object
    //   267	241	21	localObject7	Object
    //   342	99	22	localObject8	Object
    //   432	11	23	localCreator	android.os.Parcelable.Creator
    // Exception table:
    //   from	to	target	type
    //   173	182	576	android/database/sqlite/SQLiteException
    //   186	195	576	android/database/sqlite/SQLiteException
    //   212	222	576	android/database/sqlite/SQLiteException
    //   226	236	576	android/database/sqlite/SQLiteException
    //   240	246	576	android/database/sqlite/SQLiteException
    //   250	260	576	android/database/sqlite/SQLiteException
    //   273	284	576	android/database/sqlite/SQLiteException
    //   299	310	576	android/database/sqlite/SQLiteException
    //   314	320	576	android/database/sqlite/SQLiteException
    //   324	335	576	android/database/sqlite/SQLiteException
    //   348	359	576	android/database/sqlite/SQLiteException
    //   374	385	576	android/database/sqlite/SQLiteException
    //   389	400	576	android/database/sqlite/SQLiteException
    //   404	410	576	android/database/sqlite/SQLiteException
    //   414	425	576	android/database/sqlite/SQLiteException
    //   438	449	576	android/database/sqlite/SQLiteException
    //   464	480	576	android/database/sqlite/SQLiteException
    //   484	514	576	android/database/sqlite/SQLiteException
    //   518	527	576	android/database/sqlite/SQLiteException
    //   536	561	576	android/database/sqlite/SQLiteException
    //   131	140	581	java/lang/Throwable
    //   159	169	581	java/lang/Throwable
    //   131	140	585	android/database/sqlite/SQLiteException
    //   159	169	585	android/database/sqlite/SQLiteException
    //   18	24	590	java/lang/Throwable
    //   24	127	590	java/lang/Throwable
    //   18	24	597	android/database/sqlite/SQLiteException
    //   24	127	597	android/database/sqlite/SQLiteException
    //   173	182	647	java/lang/Throwable
    //   186	195	647	java/lang/Throwable
    //   212	222	647	java/lang/Throwable
    //   226	236	647	java/lang/Throwable
    //   240	246	647	java/lang/Throwable
    //   250	260	647	java/lang/Throwable
    //   264	269	647	java/lang/Throwable
    //   273	284	647	java/lang/Throwable
    //   288	295	647	java/lang/Throwable
    //   299	310	647	java/lang/Throwable
    //   314	320	647	java/lang/Throwable
    //   324	335	647	java/lang/Throwable
    //   339	344	647	java/lang/Throwable
    //   348	359	647	java/lang/Throwable
    //   363	370	647	java/lang/Throwable
    //   374	385	647	java/lang/Throwable
    //   389	400	647	java/lang/Throwable
    //   404	410	647	java/lang/Throwable
    //   414	425	647	java/lang/Throwable
    //   429	434	647	java/lang/Throwable
    //   438	449	647	java/lang/Throwable
    //   453	460	647	java/lang/Throwable
    //   464	480	647	java/lang/Throwable
    //   484	514	647	java/lang/Throwable
    //   518	527	647	java/lang/Throwable
    //   536	561	647	java/lang/Throwable
    //   606	633	647	java/lang/Throwable
  }
  
  final Map getMessages(String paramString1, String paramString2)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: fail exe a17 = a16\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:92)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.dfs(Cfg.java:255)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze0(BaseAnalyze.java:75)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze(BaseAnalyze.java:69)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer.transform(UnSSATransformer.java:274)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:163)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\nCaused by: java.lang.NullPointerException\n");
  }
  
  final SQLiteDatabase getWritableDatabase()
  {
    zzaf();
    Object localObject = zzaho;
    try
    {
      localObject = ((DatabaseHelper)localObject).getWritableDatabase();
      return localObject;
    }
    catch (SQLiteException localSQLiteException)
    {
      zzgo().zzjg().append("Error opening database", localSQLiteException);
      throw localSQLiteException;
    }
  }
  
  /* Error */
  public final long insertMessage(zzgi paramZzgi)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 206	com/google/android/android/measurement/internal/zzco:zzaf	()V
    //   4: aload_0
    //   5: invokevirtual 203	com/google/android/android/measurement/internal/zzez:zzcl	()V
    //   8: aload_1
    //   9: invokestatic 216	com/google/android/android/common/internal/Preconditions:checkNotNull	(Ljava/lang/Object;)Ljava/lang/Object;
    //   12: pop
    //   13: aload_1
    //   14: getfield 987	com/google/android/android/internal/measurement/zzgi:zztt	Ljava/lang/String;
    //   17: invokestatic 212	com/google/android/android/common/internal/Preconditions:checkNotEmpty	(Ljava/lang/String;)Ljava/lang/String;
    //   20: pop
    //   21: aload_1
    //   22: invokevirtual 269	com/google/android/android/internal/measurement/zzzg:zzvu	()I
    //   25: istore_2
    //   26: iload_2
    //   27: newarray byte
    //   29: astore 5
    //   31: aload 5
    //   33: arraylength
    //   34: istore_2
    //   35: aload 5
    //   37: iconst_0
    //   38: iload_2
    //   39: invokestatic 275	com/google/android/android/internal/measurement/zzyy:toString	([BII)Lcom/google/android/android/internal/measurement/zzyy;
    //   42: astore 6
    //   44: aload_1
    //   45: aload 6
    //   47: invokevirtual 279	com/google/android/android/internal/measurement/zzzg:multiply	(Lcom/google/android/android/internal/measurement/zzyy;)V
    //   50: aload 6
    //   52: invokevirtual 282	com/google/android/android/internal/measurement/zzyy:zzyt	()V
    //   55: aload_0
    //   56: invokevirtual 919	com/google/android/android/measurement/internal/zzey:zzjo	()Lcom/google/android/android/measurement/internal/zzfg;
    //   59: astore 6
    //   61: aload 5
    //   63: invokestatic 216	com/google/android/android/common/internal/Preconditions:checkNotNull	(Ljava/lang/Object;)Ljava/lang/Object;
    //   66: pop
    //   67: aload 6
    //   69: invokevirtual 696	com/google/android/android/measurement/internal/zzco:zzgm	()Lcom/google/android/android/measurement/internal/zzfk;
    //   72: invokevirtual 206	com/google/android/android/measurement/internal/zzco:zzaf	()V
    //   75: invokestatic 991	com/google/android/android/measurement/internal/zzfk:getMessageDigest	()Ljava/security/MessageDigest;
    //   78: astore 7
    //   80: aload 7
    //   82: ifnonnull +22 -> 104
    //   85: aload 6
    //   87: invokevirtual 232	com/google/android/android/measurement/internal/zzco:zzgo	()Lcom/google/android/android/measurement/internal/zzap;
    //   90: invokevirtual 323	com/google/android/android/measurement/internal/zzap:zzjd	()Lcom/google/android/android/measurement/internal/zzar;
    //   93: ldc_w 993
    //   96: invokevirtual 395	com/google/android/android/measurement/internal/zzar:zzbx	(Ljava/lang/String;)V
    //   99: lconst_0
    //   100: lstore_3
    //   101: goto +14 -> 115
    //   104: aload 7
    //   106: aload 5
    //   108: invokevirtual 997	java/security/MessageDigest:digest	([B)[B
    //   111: invokestatic 1001	com/google/android/android/measurement/internal/zzfk:encode	([B)J
    //   114: lstore_3
    //   115: new 284	android/content/ContentValues
    //   118: dup
    //   119: invokespecial 286	android/content/ContentValues:<init>	()V
    //   122: astore 6
    //   124: aload 6
    //   126: ldc_w 288
    //   129: aload_1
    //   130: getfield 987	com/google/android/android/internal/measurement/zzgi:zztt	Ljava/lang/String;
    //   133: invokevirtual 292	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   136: aload 6
    //   138: ldc_w 1003
    //   141: lload_3
    //   142: invokestatic 413	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   145: invokevirtual 421	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Long;)V
    //   148: aload 6
    //   150: ldc_w 1005
    //   153: aload 5
    //   155: invokevirtual 306	android/content/ContentValues:put	(Ljava/lang/String;[B)V
    //   158: aload_0
    //   159: invokevirtual 310	com/google/android/android/measurement/internal/StringBuilder:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   162: ldc_w 1007
    //   165: aconst_null
    //   166: aload 6
    //   168: iconst_4
    //   169: invokevirtual 318	android/database/sqlite/SQLiteDatabase:insertWithOnConflict	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;I)J
    //   172: pop2
    //   173: lload_3
    //   174: lreturn
    //   175: astore 5
    //   177: aload_0
    //   178: invokevirtual 232	com/google/android/android/measurement/internal/zzco:zzgo	()Lcom/google/android/android/measurement/internal/zzap;
    //   181: invokevirtual 323	com/google/android/android/measurement/internal/zzap:zzjd	()Lcom/google/android/android/measurement/internal/zzar;
    //   184: ldc_w 1009
    //   187: aload_1
    //   188: getfield 987	com/google/android/android/internal/measurement/zzgi:zztt	Ljava/lang/String;
    //   191: invokestatic 244	com/google/android/android/measurement/internal/zzap:zzbv	(Ljava/lang/String;)Ljava/lang/Object;
    //   194: aload 5
    //   196: invokevirtual 333	com/google/android/android/measurement/internal/zzar:append	(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    //   199: aload 5
    //   201: athrow
    //   202: astore 5
    //   204: aload_0
    //   205: invokevirtual 232	com/google/android/android/measurement/internal/zzco:zzgo	()Lcom/google/android/android/measurement/internal/zzap;
    //   208: invokevirtual 323	com/google/android/android/measurement/internal/zzap:zzjd	()Lcom/google/android/android/measurement/internal/zzar;
    //   211: ldc_w 1011
    //   214: aload_1
    //   215: getfield 987	com/google/android/android/internal/measurement/zzgi:zztt	Ljava/lang/String;
    //   218: invokestatic 244	com/google/android/android/measurement/internal/zzap:zzbv	(Ljava/lang/String;)Ljava/lang/Object;
    //   221: aload 5
    //   223: invokevirtual 333	com/google/android/android/measurement/internal/zzar:append	(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    //   226: aload 5
    //   228: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	229	0	this	StringBuilder
    //   0	229	1	paramZzgi	zzgi
    //   25	14	2	i	int
    //   100	74	3	l	long
    //   29	125	5	arrayOfByte	byte[]
    //   175	25	5	localSQLiteException	SQLiteException
    //   202	25	5	localIOException	IOException
    //   42	125	6	localObject	Object
    //   78	27	7	localMessageDigest	java.security.MessageDigest
    // Exception table:
    //   from	to	target	type
    //   158	173	175	android/database/sqlite/SQLiteException
    //   21	26	202	java/io/IOException
    //   35	55	202	java/io/IOException
  }
  
  public final void insertMessage(class_4 paramClass_4)
  {
    Preconditions.checkNotNull(paramClass_4);
    zzaf();
    zzcl();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("app_id", paramClass_4.zzal());
    localContentValues.put("app_instance_id", paramClass_4.getAppInstanceId());
    localContentValues.put("gmp_app_id", paramClass_4.getGmpAppId());
    localContentValues.put("resettable_device_id_hash", paramClass_4.zzgx());
    localContentValues.put("last_bundle_index", Long.valueOf(paramClass_4.zzhe()));
    localContentValues.put("last_bundle_start_timestamp", Long.valueOf(paramClass_4.zzgy()));
    localContentValues.put("last_bundle_end_timestamp", Long.valueOf(paramClass_4.zzgz()));
    localContentValues.put("app_version", paramClass_4.zzak());
    localContentValues.put("app_store", paramClass_4.zzhb());
    localContentValues.put("gmp_version", Long.valueOf(paramClass_4.zzhc()));
    localContentValues.put("dev_cert_hash", Long.valueOf(paramClass_4.zzhd()));
    localContentValues.put("measurement_enabled", Boolean.valueOf(paramClass_4.isMeasurementEnabled()));
    localContentValues.put("day", Long.valueOf(paramClass_4.zzhi()));
    localContentValues.put("daily_public_events_count", Long.valueOf(paramClass_4.zzhj()));
    localContentValues.put("daily_events_count", Long.valueOf(paramClass_4.zzhk()));
    localContentValues.put("daily_conversions_count", Long.valueOf(paramClass_4.zzhl()));
    localContentValues.put("config_fetched_time", Long.valueOf(paramClass_4.zzhf()));
    localContentValues.put("failed_config_fetch_time", Long.valueOf(paramClass_4.zzhg()));
    localContentValues.put("app_version_int", Long.valueOf(paramClass_4.zzha()));
    localContentValues.put("firebase_instance_id", paramClass_4.getFirebaseInstanceId());
    localContentValues.put("daily_error_events_count", Long.valueOf(paramClass_4.zzhn()));
    localContentValues.put("daily_realtime_events_count", Long.valueOf(paramClass_4.zzhm()));
    localContentValues.put("health_monitor_sample", paramClass_4.zzho());
    localContentValues.put("android_id", Long.valueOf(paramClass_4.zzhq()));
    localContentValues.put("adid_reporting_enabled", Boolean.valueOf(paramClass_4.zzhr()));
    localContentValues.put("ssaid_reporting_enabled", Boolean.valueOf(paramClass_4.zzhs()));
    localContentValues.put("admob_app_id", paramClass_4.zzgw());
    try
    {
      SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
      String str = paramClass_4.zzal();
      int i = localSQLiteDatabase.update("apps", localContentValues, "app_id = ?", new String[] { str });
      if (i == 0L)
      {
        long l = localSQLiteDatabase.insertWithOnConflict("apps", null, localContentValues, 5);
        if (l == -1L)
        {
          zzgo().zzjd().append("Failed to insert/update app (got -1). appId", zzap.zzbv(paramClass_4.zzal()));
          return;
        }
      }
    }
    catch (SQLiteException localSQLiteException)
    {
      zzgo().zzjd().append("Error storing app. appId", zzap.zzbv(paramClass_4.zzal()), localSQLiteException);
    }
  }
  
  /* Error */
  public final List load(String paramString, String[] paramArrayOfString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 206	com/google/android/android/measurement/internal/zzco:zzaf	()V
    //   4: aload_0
    //   5: invokevirtual 203	com/google/android/android/measurement/internal/zzez:zzcl	()V
    //   8: new 462	java/util/ArrayList
    //   11: dup
    //   12: invokespecial 463	java/util/ArrayList:<init>	()V
    //   15: astore 13
    //   17: aload_0
    //   18: invokevirtual 310	com/google/android/android/measurement/internal/StringBuilder:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   21: astore 14
    //   23: aload 14
    //   25: ldc_w 735
    //   28: bipush 13
    //   30: anewarray 19	java/lang/String
    //   33: dup
    //   34: iconst_0
    //   35: ldc_w 288
    //   38: aastore
    //   39: dup
    //   40: iconst_1
    //   41: ldc 43
    //   43: aastore
    //   44: dup
    //   45: iconst_2
    //   46: ldc_w 574
    //   49: aastore
    //   50: dup
    //   51: iconst_3
    //   52: ldc_w 665
    //   55: aastore
    //   56: dup
    //   57: iconst_4
    //   58: ldc_w 673
    //   61: aastore
    //   62: dup
    //   63: iconst_5
    //   64: ldc_w 684
    //   67: aastore
    //   68: dup
    //   69: bipush 6
    //   71: ldc_w 689
    //   74: aastore
    //   75: dup
    //   76: bipush 7
    //   78: ldc_w 698
    //   81: aastore
    //   82: dup
    //   83: bipush 8
    //   85: ldc_w 710
    //   88: aastore
    //   89: dup
    //   90: bipush 9
    //   92: ldc_w 715
    //   95: aastore
    //   96: dup
    //   97: bipush 10
    //   99: ldc_w 720
    //   102: aastore
    //   103: dup
    //   104: bipush 11
    //   106: ldc_w 725
    //   109: aastore
    //   110: dup
    //   111: bipush 12
    //   113: ldc_w 730
    //   116: aastore
    //   117: aload_1
    //   118: aload_2
    //   119: aconst_null
    //   120: aconst_null
    //   121: ldc_w 835
    //   124: ldc_w 837
    //   127: invokevirtual 841	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   130: astore_1
    //   131: aload_1
    //   132: astore_2
    //   133: aload_1
    //   134: invokeinterface 368 1 0
    //   139: istore 4
    //   141: iload 4
    //   143: ifne +16 -> 159
    //   146: aload_1
    //   147: ifnull +420 -> 567
    //   150: aload_1
    //   151: invokeinterface 375 1 0
    //   156: aload 13
    //   158: areturn
    //   159: aload 13
    //   161: invokeinterface 468 1 0
    //   166: istore_3
    //   167: iload_3
    //   168: sipush 1000
    //   171: if_icmplt +25 -> 196
    //   174: aload_0
    //   175: invokevirtual 232	com/google/android/android/measurement/internal/zzco:zzgo	()Lcom/google/android/android/measurement/internal/zzap;
    //   178: invokevirtual 323	com/google/android/android/measurement/internal/zzap:zzjd	()Lcom/google/android/android/measurement/internal/zzar;
    //   181: ldc_w 1115
    //   184: sipush 1000
    //   187: invokestatic 250	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   190: invokevirtual 328	com/google/android/android/measurement/internal/zzar:append	(Ljava/lang/String;Ljava/lang/Object;)V
    //   193: goto +292 -> 485
    //   196: aload_1
    //   197: iconst_0
    //   198: invokeinterface 399 2 0
    //   203: astore 14
    //   205: aload_1
    //   206: iconst_1
    //   207: invokeinterface 399 2 0
    //   212: astore 15
    //   214: aload_1
    //   215: iconst_2
    //   216: invokeinterface 399 2 0
    //   221: astore 17
    //   223: aload_0
    //   224: aload_1
    //   225: iconst_3
    //   226: invokespecial 845	com/google/android/android/measurement/internal/StringBuilder:get	(Landroid/database/Cursor;I)Ljava/lang/Object;
    //   229: astore 18
    //   231: aload_1
    //   232: iconst_4
    //   233: invokeinterface 937 2 0
    //   238: istore_3
    //   239: iload_3
    //   240: ifeq +9 -> 249
    //   243: iconst_1
    //   244: istore 4
    //   246: goto +6 -> 252
    //   249: iconst_0
    //   250: istore 4
    //   252: aload_1
    //   253: iconst_5
    //   254: invokeinterface 399 2 0
    //   259: astore 16
    //   261: aload_1
    //   262: bipush 6
    //   264: invokeinterface 372 2 0
    //   269: lstore 5
    //   271: aload_0
    //   272: invokevirtual 919	com/google/android/android/measurement/internal/zzey:zzjo	()Lcom/google/android/android/measurement/internal/zzfg;
    //   275: astore 19
    //   277: aload_1
    //   278: bipush 7
    //   280: invokeinterface 864 2 0
    //   285: astore 20
    //   287: getstatic 966	com/google/android/android/measurement/internal/zzad:CREATOR	Landroid/os/Parcelable$Creator;
    //   290: astore 21
    //   292: aload 19
    //   294: aload 20
    //   296: aload 21
    //   298: invokevirtual 970	com/google/android/android/measurement/internal/zzfg:unmarshall	([BLandroid/os/Parcelable$Creator;)Landroid/os/Parcelable;
    //   301: astore 19
    //   303: aload 19
    //   305: checkcast 962	com/google/android/android/measurement/internal/zzad
    //   308: astore 19
    //   310: aload_1
    //   311: bipush 8
    //   313: invokeinterface 372 2 0
    //   318: lstore 7
    //   320: aload_0
    //   321: invokevirtual 919	com/google/android/android/measurement/internal/zzey:zzjo	()Lcom/google/android/android/measurement/internal/zzfg;
    //   324: astore 20
    //   326: aload_1
    //   327: bipush 9
    //   329: invokeinterface 864 2 0
    //   334: astore 21
    //   336: getstatic 966	com/google/android/android/measurement/internal/zzad:CREATOR	Landroid/os/Parcelable$Creator;
    //   339: astore 22
    //   341: aload 20
    //   343: aload 21
    //   345: aload 22
    //   347: invokevirtual 970	com/google/android/android/measurement/internal/zzfg:unmarshall	([BLandroid/os/Parcelable$Creator;)Landroid/os/Parcelable;
    //   350: astore 20
    //   352: aload 20
    //   354: checkcast 962	com/google/android/android/measurement/internal/zzad
    //   357: astore 20
    //   359: aload_1
    //   360: bipush 10
    //   362: invokeinterface 372 2 0
    //   367: lstore 9
    //   369: aload_1
    //   370: bipush 11
    //   372: invokeinterface 372 2 0
    //   377: lstore 11
    //   379: aload_0
    //   380: invokevirtual 919	com/google/android/android/measurement/internal/zzey:zzjo	()Lcom/google/android/android/measurement/internal/zzfg;
    //   383: astore 21
    //   385: aload_1
    //   386: bipush 12
    //   388: invokeinterface 864 2 0
    //   393: astore 22
    //   395: getstatic 966	com/google/android/android/measurement/internal/zzad:CREATOR	Landroid/os/Parcelable$Creator;
    //   398: astore 23
    //   400: aload 21
    //   402: aload 22
    //   404: aload 23
    //   406: invokevirtual 970	com/google/android/android/measurement/internal/zzfg:unmarshall	([BLandroid/os/Parcelable$Creator;)Landroid/os/Parcelable;
    //   409: astore 21
    //   411: aload 21
    //   413: checkcast 962	com/google/android/android/measurement/internal/zzad
    //   416: astore 21
    //   418: new 653	com/google/android/android/measurement/internal/zzfh
    //   421: dup
    //   422: aload 17
    //   424: lload 9
    //   426: aload 18
    //   428: aload 15
    //   430: invokespecial 973	com/google/android/android/measurement/internal/zzfh:<init>	(Ljava/lang/String;JLjava/lang/Object;Ljava/lang/String;)V
    //   433: astore 17
    //   435: aload 13
    //   437: new 644	com/google/android/android/measurement/internal/ComponentInfo
    //   440: dup
    //   441: aload 14
    //   443: aload 15
    //   445: aload 17
    //   447: lload 7
    //   449: iload 4
    //   451: aload 16
    //   453: aload 19
    //   455: lload 5
    //   457: aload 20
    //   459: lload 11
    //   461: aload 21
    //   463: invokespecial 976	com/google/android/android/measurement/internal/ComponentInfo:<init>	(Ljava/lang/String;Ljava/lang/String;Lcom/google/android/android/measurement/internal/zzfh;JZLjava/lang/String;Lcom/google/android/android/measurement/internal/zzad;JLcom/google/android/android/measurement/internal/zzad;JLcom/google/android/android/measurement/internal/zzad;)V
    //   466: invokeinterface 479 2 0
    //   471: pop
    //   472: aload_1
    //   473: invokeinterface 853 1 0
    //   478: istore 4
    //   480: iload 4
    //   482: ifne +16 -> 498
    //   485: aload_1
    //   486: ifnull +81 -> 567
    //   489: aload_1
    //   490: invokeinterface 375 1 0
    //   495: aload 13
    //   497: areturn
    //   498: goto -339 -> 159
    //   501: astore 13
    //   503: aload_2
    //   504: astore_1
    //   505: aload 13
    //   507: astore_2
    //   508: goto +47 -> 555
    //   511: astore_2
    //   512: goto +12 -> 524
    //   515: astore_2
    //   516: aconst_null
    //   517: astore_1
    //   518: goto +37 -> 555
    //   521: astore_2
    //   522: aconst_null
    //   523: astore_1
    //   524: aload_0
    //   525: invokevirtual 232	com/google/android/android/measurement/internal/zzco:zzgo	()Lcom/google/android/android/measurement/internal/zzap;
    //   528: invokevirtual 323	com/google/android/android/measurement/internal/zzap:zzjd	()Lcom/google/android/android/measurement/internal/zzar;
    //   531: ldc_w 1117
    //   534: aload_2
    //   535: invokevirtual 328	com/google/android/android/measurement/internal/zzar:append	(Ljava/lang/String;Ljava/lang/Object;)V
    //   538: invokestatic 913	java/util/Collections:emptyList	()Ljava/util/List;
    //   541: astore_2
    //   542: aload_1
    //   543: ifnull +27 -> 570
    //   546: aload_1
    //   547: invokeinterface 375 1 0
    //   552: aload_2
    //   553: areturn
    //   554: astore_2
    //   555: aload_1
    //   556: ifnull +9 -> 565
    //   559: aload_1
    //   560: invokeinterface 375 1 0
    //   565: aload_2
    //   566: athrow
    //   567: aload 13
    //   569: areturn
    //   570: aload_2
    //   571: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	572	0	this	StringBuilder
    //   0	572	1	paramString	String
    //   0	572	2	paramArrayOfString	String[]
    //   166	74	3	i	int
    //   139	342	4	bool	boolean
    //   269	187	5	l1	long
    //   318	130	7	l2	long
    //   367	58	9	l3	long
    //   377	83	11	l4	long
    //   15	481	13	localArrayList	ArrayList
    //   501	67	13	localThrowable	Throwable
    //   21	421	14	localObject1	Object
    //   212	232	15	str1	String
    //   259	193	16	str2	String
    //   221	225	17	localObject2	Object
    //   229	198	18	localObject3	Object
    //   275	179	19	localObject4	Object
    //   285	173	20	localObject5	Object
    //   290	172	21	localObject6	Object
    //   339	64	22	localObject7	Object
    //   398	7	23	localCreator	android.os.Parcelable.Creator
    // Exception table:
    //   from	to	target	type
    //   133	141	501	java/lang/Throwable
    //   159	167	501	java/lang/Throwable
    //   174	193	501	java/lang/Throwable
    //   196	239	501	java/lang/Throwable
    //   252	287	501	java/lang/Throwable
    //   287	292	501	java/lang/Throwable
    //   292	303	501	java/lang/Throwable
    //   303	310	501	java/lang/Throwable
    //   310	336	501	java/lang/Throwable
    //   336	341	501	java/lang/Throwable
    //   341	352	501	java/lang/Throwable
    //   352	359	501	java/lang/Throwable
    //   359	395	501	java/lang/Throwable
    //   395	400	501	java/lang/Throwable
    //   400	411	501	java/lang/Throwable
    //   411	418	501	java/lang/Throwable
    //   418	435	501	java/lang/Throwable
    //   435	480	501	java/lang/Throwable
    //   133	141	511	android/database/sqlite/SQLiteException
    //   159	167	511	android/database/sqlite/SQLiteException
    //   174	193	511	android/database/sqlite/SQLiteException
    //   196	239	511	android/database/sqlite/SQLiteException
    //   252	287	511	android/database/sqlite/SQLiteException
    //   292	303	511	android/database/sqlite/SQLiteException
    //   310	336	511	android/database/sqlite/SQLiteException
    //   341	352	511	android/database/sqlite/SQLiteException
    //   359	395	511	android/database/sqlite/SQLiteException
    //   400	411	511	android/database/sqlite/SQLiteException
    //   418	435	511	android/database/sqlite/SQLiteException
    //   435	480	511	android/database/sqlite/SQLiteException
    //   17	23	515	java/lang/Throwable
    //   23	131	515	java/lang/Throwable
    //   17	23	521	android/database/sqlite/SQLiteException
    //   23	131	521	android/database/sqlite/SQLiteException
    //   524	542	554	java/lang/Throwable
  }
  
  /* Error */
  public final EWAHCompressedBitmap next(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 212	com/google/android/android/common/internal/Preconditions:checkNotEmpty	(Ljava/lang/String;)Ljava/lang/String;
    //   4: pop
    //   5: aload_2
    //   6: invokestatic 212	com/google/android/android/common/internal/Preconditions:checkNotEmpty	(Ljava/lang/String;)Ljava/lang/String;
    //   9: pop
    //   10: aload_0
    //   11: invokevirtual 206	com/google/android/android/measurement/internal/zzco:zzaf	()V
    //   14: aload_0
    //   15: invokevirtual 203	com/google/android/android/measurement/internal/zzez:zzcl	()V
    //   18: aload_0
    //   19: invokevirtual 310	com/google/android/android/measurement/internal/StringBuilder:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   22: astore 15
    //   24: iconst_0
    //   25: istore_3
    //   26: aload 15
    //   28: ldc_w 616
    //   31: bipush 8
    //   33: anewarray 19	java/lang/String
    //   36: dup
    //   37: iconst_0
    //   38: ldc_w 578
    //   41: aastore
    //   42: dup
    //   43: iconst_1
    //   44: ldc_w 584
    //   47: aastore
    //   48: dup
    //   49: iconst_2
    //   50: ldc_w 589
    //   53: aastore
    //   54: dup
    //   55: iconst_3
    //   56: ldc 21
    //   58: aastore
    //   59: dup
    //   60: iconst_4
    //   61: ldc 25
    //   63: aastore
    //   64: dup
    //   65: iconst_5
    //   66: ldc 29
    //   68: aastore
    //   69: dup
    //   70: bipush 6
    //   72: ldc 33
    //   74: aastore
    //   75: dup
    //   76: bipush 7
    //   78: ldc 37
    //   80: aastore
    //   81: ldc_w 887
    //   84: iconst_2
    //   85: anewarray 19	java/lang/String
    //   88: dup
    //   89: iconst_0
    //   90: aload_1
    //   91: aastore
    //   92: dup
    //   93: iconst_1
    //   94: aload_2
    //   95: aastore
    //   96: aconst_null
    //   97: aconst_null
    //   98: aconst_null
    //   99: invokevirtual 890	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   102: astore 15
    //   104: aload 15
    //   106: invokeinterface 368 1 0
    //   111: istore 4
    //   113: iload 4
    //   115: ifne +17 -> 132
    //   118: aload 15
    //   120: ifnull +416 -> 536
    //   123: aload 15
    //   125: invokeinterface 375 1 0
    //   130: aconst_null
    //   131: areturn
    //   132: aload 15
    //   134: iconst_0
    //   135: invokeinterface 372 2 0
    //   140: lstore 7
    //   142: aload 15
    //   144: iconst_1
    //   145: invokeinterface 372 2 0
    //   150: lstore 9
    //   152: aload 15
    //   154: iconst_2
    //   155: invokeinterface 372 2 0
    //   160: lstore 11
    //   162: aload 15
    //   164: iconst_3
    //   165: invokeinterface 934 2 0
    //   170: istore 4
    //   172: iload 4
    //   174: ifeq +9 -> 183
    //   177: lconst_0
    //   178: lstore 5
    //   180: goto +16 -> 196
    //   183: aload 15
    //   185: iconst_3
    //   186: invokeinterface 372 2 0
    //   191: lstore 5
    //   193: goto -13 -> 180
    //   196: aload 15
    //   198: iconst_4
    //   199: invokeinterface 934 2 0
    //   204: istore 4
    //   206: iload 4
    //   208: ifeq +9 -> 217
    //   211: aconst_null
    //   212: astore 18
    //   214: goto +16 -> 230
    //   217: aload 15
    //   219: iconst_4
    //   220: invokeinterface 372 2 0
    //   225: invokestatic 413	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   228: astore 18
    //   230: aload 15
    //   232: iconst_5
    //   233: invokeinterface 934 2 0
    //   238: istore 4
    //   240: iload 4
    //   242: ifeq +9 -> 251
    //   245: aconst_null
    //   246: astore 19
    //   248: goto +16 -> 264
    //   251: aload 15
    //   253: iconst_5
    //   254: invokeinterface 372 2 0
    //   259: invokestatic 413	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   262: astore 19
    //   264: aload 15
    //   266: bipush 6
    //   268: invokeinterface 934 2 0
    //   273: istore 4
    //   275: iload 4
    //   277: ifeq +9 -> 286
    //   280: aconst_null
    //   281: astore 20
    //   283: goto +20 -> 303
    //   286: aload 15
    //   288: bipush 6
    //   290: invokeinterface 372 2 0
    //   295: invokestatic 413	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   298: astore 20
    //   300: goto -17 -> 283
    //   303: aload 15
    //   305: bipush 7
    //   307: invokeinterface 934 2 0
    //   312: istore 4
    //   314: iload 4
    //   316: ifne +32 -> 348
    //   319: aload 15
    //   321: bipush 7
    //   323: invokeinterface 372 2 0
    //   328: lstore 13
    //   330: lload 13
    //   332: lconst_1
    //   333: lcmp
    //   334: ifne +5 -> 339
    //   337: iconst_1
    //   338: istore_3
    //   339: iload_3
    //   340: invokestatic 679	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   343: astore 21
    //   345: goto +6 -> 351
    //   348: aconst_null
    //   349: astore 21
    //   351: aload 15
    //   353: astore 17
    //   355: aload 17
    //   357: astore 16
    //   359: new 569	com/google/android/android/measurement/internal/EWAHCompressedBitmap
    //   362: dup
    //   363: aload_1
    //   364: aload_2
    //   365: lload 7
    //   367: lload 9
    //   369: lload 11
    //   371: lload 5
    //   373: aload 18
    //   375: aload 19
    //   377: aload 20
    //   379: aload 21
    //   381: invokespecial 1121	com/google/android/android/measurement/internal/EWAHCompressedBitmap:<init>	(Ljava/lang/String;Ljava/lang/String;JJJJLjava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Boolean;)V
    //   384: astore 18
    //   386: aload 17
    //   388: astore 16
    //   390: aload 15
    //   392: invokeinterface 853 1 0
    //   397: istore_3
    //   398: iload_3
    //   399: ifeq +24 -> 423
    //   402: aload 17
    //   404: astore 16
    //   406: aload_0
    //   407: invokevirtual 232	com/google/android/android/measurement/internal/zzco:zzgo	()Lcom/google/android/android/measurement/internal/zzap;
    //   410: invokevirtual 323	com/google/android/android/measurement/internal/zzap:zzjd	()Lcom/google/android/android/measurement/internal/zzar;
    //   413: ldc_w 1123
    //   416: aload_1
    //   417: invokestatic 244	com/google/android/android/measurement/internal/zzap:zzbv	(Ljava/lang/String;)Ljava/lang/Object;
    //   420: invokevirtual 328	com/google/android/android/measurement/internal/zzar:append	(Ljava/lang/String;Ljava/lang/Object;)V
    //   423: aload 15
    //   425: ifnull +113 -> 538
    //   428: aload 15
    //   430: invokeinterface 375 1 0
    //   435: aload 18
    //   437: areturn
    //   438: astore 16
    //   440: aload 17
    //   442: astore 15
    //   444: aload 16
    //   446: astore 17
    //   448: goto +28 -> 476
    //   451: astore_1
    //   452: aload 15
    //   454: astore 16
    //   456: goto +66 -> 522
    //   459: astore 17
    //   461: goto +15 -> 476
    //   464: astore_1
    //   465: aconst_null
    //   466: astore 16
    //   468: goto +54 -> 522
    //   471: astore 17
    //   473: aconst_null
    //   474: astore 15
    //   476: aload 15
    //   478: astore 16
    //   480: aload_0
    //   481: invokevirtual 232	com/google/android/android/measurement/internal/zzco:zzgo	()Lcom/google/android/android/measurement/internal/zzap;
    //   484: invokevirtual 323	com/google/android/android/measurement/internal/zzap:zzjd	()Lcom/google/android/android/measurement/internal/zzar;
    //   487: ldc_w 1125
    //   490: aload_1
    //   491: invokestatic 244	com/google/android/android/measurement/internal/zzap:zzbv	(Ljava/lang/String;)Ljava/lang/Object;
    //   494: aload_0
    //   495: invokevirtual 789	com/google/android/android/measurement/internal/zzco:zzgl	()Lcom/google/android/android/measurement/internal/zzan;
    //   498: aload_2
    //   499: invokevirtual 794	com/google/android/android/measurement/internal/zzan:zzbs	(Ljava/lang/String;)Ljava/lang/String;
    //   502: aload 17
    //   504: invokevirtual 263	com/google/android/android/measurement/internal/zzar:append	(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V
    //   507: aload 15
    //   509: ifnull +32 -> 541
    //   512: aload 15
    //   514: invokeinterface 375 1 0
    //   519: aconst_null
    //   520: areturn
    //   521: astore_1
    //   522: aload 16
    //   524: ifnull +10 -> 534
    //   527: aload 16
    //   529: invokeinterface 375 1 0
    //   534: aload_1
    //   535: athrow
    //   536: aconst_null
    //   537: areturn
    //   538: aload 18
    //   540: areturn
    //   541: aconst_null
    //   542: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	543	0	this	StringBuilder
    //   0	543	1	paramString1	String
    //   0	543	2	paramString2	String
    //   25	374	3	bool1	boolean
    //   111	204	4	bool2	boolean
    //   178	194	5	l1	long
    //   140	226	7	l2	long
    //   150	218	9	l3	long
    //   160	210	11	l4	long
    //   328	3	13	l5	long
    //   22	491	15	localObject1	Object
    //   357	48	16	localObject2	Object
    //   438	7	16	localSQLiteException1	SQLiteException
    //   454	74	16	localObject3	Object
    //   353	94	17	localObject4	Object
    //   459	1	17	localSQLiteException2	SQLiteException
    //   471	32	17	localSQLiteException3	SQLiteException
    //   212	327	18	localObject5	Object
    //   246	130	19	localLong1	Long
    //   281	97	20	localLong2	Long
    //   343	37	21	localBoolean	Boolean
    // Exception table:
    //   from	to	target	type
    //   359	386	438	android/database/sqlite/SQLiteException
    //   390	398	438	android/database/sqlite/SQLiteException
    //   406	423	438	android/database/sqlite/SQLiteException
    //   104	113	451	java/lang/Throwable
    //   132	172	451	java/lang/Throwable
    //   183	193	451	java/lang/Throwable
    //   196	206	451	java/lang/Throwable
    //   217	230	451	java/lang/Throwable
    //   230	240	451	java/lang/Throwable
    //   251	264	451	java/lang/Throwable
    //   264	275	451	java/lang/Throwable
    //   286	300	451	java/lang/Throwable
    //   303	314	451	java/lang/Throwable
    //   319	330	451	java/lang/Throwable
    //   339	345	451	java/lang/Throwable
    //   104	113	459	android/database/sqlite/SQLiteException
    //   132	172	459	android/database/sqlite/SQLiteException
    //   183	193	459	android/database/sqlite/SQLiteException
    //   196	206	459	android/database/sqlite/SQLiteException
    //   217	230	459	android/database/sqlite/SQLiteException
    //   230	240	459	android/database/sqlite/SQLiteException
    //   251	264	459	android/database/sqlite/SQLiteException
    //   264	275	459	android/database/sqlite/SQLiteException
    //   286	300	459	android/database/sqlite/SQLiteException
    //   303	314	459	android/database/sqlite/SQLiteException
    //   319	330	459	android/database/sqlite/SQLiteException
    //   339	345	459	android/database/sqlite/SQLiteException
    //   18	24	464	java/lang/Throwable
    //   26	104	464	java/lang/Throwable
    //   18	24	471	android/database/sqlite/SQLiteException
    //   26	104	471	android/database/sqlite/SQLiteException
    //   359	386	521	java/lang/Throwable
    //   390	398	521	java/lang/Throwable
    //   406	423	521	java/lang/Throwable
    //   480	507	521	java/lang/Throwable
  }
  
  public final boolean saveToFile(zzgi paramZzgi, boolean paramBoolean)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
  }
  
  public final void setTag(String paramString1, String paramString2)
  {
    Preconditions.checkNotEmpty(paramString1);
    Preconditions.checkNotEmpty(paramString2);
    zzaf();
    zzcl();
    try
    {
      SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
      int i = localSQLiteDatabase.delete("user_attributes", "app_id=? and name=?", new String[] { paramString1, paramString2 });
      zzgo().zzjl().append("Deleted user attribute rows", Integer.valueOf(i));
      return;
    }
    catch (SQLiteException localSQLiteException)
    {
      zzgo().zzjd().append("Error deleting user attribute. appId", zzap.zzbv(paramString1), zzgl().zzbu(paramString2), localSQLiteException);
    }
  }
  
  public final void setTransactionSuccessful()
  {
    zzcl();
    getWritableDatabase().setTransactionSuccessful();
  }
  
  public final boolean store(Resource paramResource, long paramLong, boolean paramBoolean)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
  }
  
  public final Data trim(long paramLong, String paramString, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5)
  {
    Preconditions.checkNotEmpty(paramString);
    zzaf();
    zzcl();
    Data localData = new Data();
    Object localObject;
    try
    {
      SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
      Cursor localCursor1 = localSQLiteDatabase.query("apps", new String[] { "day", "daily_events_count", "daily_public_events_count", "daily_conversions_count", "daily_error_events_count", "daily_realtime_events_count" }, "app_id=?", new String[] { paramString }, null, null, null);
      Cursor localCursor2 = localCursor1;
      try
      {
        boolean bool = localCursor1.moveToFirst();
        if (!bool)
        {
          zzgo().zzjg().append("Not updating daily counts, app is not known. appId", zzap.zzbv(paramString));
          if (localCursor1 == null) {
            break label567;
          }
          localCursor1.close();
          return localData;
        }
        long l = localCursor1.getLong(0);
        if (l == paramLong)
        {
          l = localCursor1.getLong(1);
          zzahr = l;
          l = localCursor1.getLong(2);
          zzahq = l;
          l = localCursor1.getLong(3);
          zzahs = l;
          l = localCursor1.getLong(4);
          zzaht = l;
          l = localCursor1.getLong(5);
          zzahu = l;
        }
        if (paramBoolean1)
        {
          l = zzahr;
          zzahr = (l + 1L);
        }
        if (paramBoolean2)
        {
          l = zzahq;
          zzahq = (l + 1L);
        }
        if (paramBoolean3)
        {
          l = zzahs;
          zzahs = (l + 1L);
        }
        if (paramBoolean4)
        {
          l = zzaht;
          zzaht = (l + 1L);
        }
        if (paramBoolean5)
        {
          l = zzahu;
          zzahu = (l + 1L);
        }
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("day", Long.valueOf(paramLong));
        paramLong = zzahq;
        localContentValues.put("daily_public_events_count", Long.valueOf(paramLong));
        paramLong = zzahr;
        localContentValues.put("daily_events_count", Long.valueOf(paramLong));
        paramLong = zzahs;
        localContentValues.put("daily_conversions_count", Long.valueOf(paramLong));
        paramLong = zzaht;
        localContentValues.put("daily_error_events_count", Long.valueOf(paramLong));
        paramLong = zzahu;
        localContentValues.put("daily_realtime_events_count", Long.valueOf(paramLong));
        localSQLiteDatabase.update("apps", localContentValues, "app_id=?", new String[] { paramString });
        if (localCursor1 == null) {
          break label567;
        }
        localCursor1.close();
        return localData;
      }
      catch (Throwable localThrowable1)
      {
        paramString = localCursor2;
        break label554;
      }
      catch (SQLiteException localSQLiteException1) {}
      try
      {
        zzgo().zzjd().append("Error updating daily counts. appId", zzap.zzbv(paramString), localSQLiteException2);
        if (localObject == null) {
          break label567;
        }
        localObject.close();
        return localData;
      }
      catch (Throwable localThrowable3)
      {
        paramString = localObject;
        localObject = localThrowable3;
      }
    }
    catch (Throwable localThrowable2)
    {
      paramString = null;
    }
    catch (SQLiteException localSQLiteException2)
    {
      localObject = null;
    }
    label554:
    if (paramString != null) {
      paramString.close();
    }
    throw localObject;
    label567:
    return localData;
  }
  
  final void trim(String paramString, zzfu[] paramArrayOfZzfu)
  {
    zzcl();
    zzaf();
    Preconditions.checkNotEmpty(paramString);
    Preconditions.checkNotNull(paramArrayOfZzfu);
    SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
    localSQLiteDatabase.beginTransaction();
    try
    {
      zzcl();
      zzaf();
      Preconditions.checkNotEmpty(paramString);
      Object localObject1 = getWritableDatabase();
      int n = 0;
      ((SQLiteDatabase)localObject1).delete("property_filters", "app_id=?", new String[] { paramString });
      ((SQLiteDatabase)localObject1).delete("event_filters", "app_id=?", new String[] { paramString });
      int i1 = paramArrayOfZzfu.length;
      int j = 0;
      while (j < i1)
      {
        localObject1 = paramArrayOfZzfu[j];
        zzcl();
        zzaf();
        Preconditions.checkNotEmpty(paramString);
        Preconditions.checkNotNull(localObject1);
        Preconditions.checkNotNull(zzava);
        Preconditions.checkNotNull(zzauz);
        Object localObject2 = zzauy;
        if (localObject2 == null)
        {
          zzgo().zzjg().append("Audience with no ID. appId", zzap.zzbv(paramString));
        }
        else
        {
          int i2 = zzauy.intValue();
          localObject2 = zzava;
          int k = localObject2.length;
          i = 0;
          Integer localInteger;
          while (i < k)
          {
            localInteger = zzave;
            if (localInteger == null)
            {
              zzgo().zzjg().append("Event filter with no ID. Audience definition ignored. appId, audienceId", zzap.zzbv(paramString), zzauy);
              break label520;
            }
            i += 1;
          }
          localObject2 = zzauz;
          k = localObject2.length;
          i = 0;
          while (i < k)
          {
            localInteger = zzave;
            if (localInteger == null)
            {
              zzgo().zzjg().append("Property filter with no ID. Audience definition ignored. appId, audienceId", zzap.zzbv(paramString), zzauy);
              break label520;
            }
            i += 1;
          }
          localObject2 = zzava;
          k = localObject2.length;
          i = 0;
          boolean bool;
          while (i < k)
          {
            bool = add(paramString, i2, localObject2[i]);
            if (!bool)
            {
              i = 0;
              break label374;
            }
            i += 1;
          }
          i = 1;
          label374:
          int m = i;
          if (i != 0)
          {
            localObject1 = zzauz;
            int i3 = localObject1.length;
            k = 0;
            for (;;)
            {
              m = i;
              if (k >= i3) {
                break;
              }
              bool = add(paramString, i2, localObject1[k]);
              if (!bool)
              {
                m = 0;
                break;
              }
              k += 1;
            }
          }
          if (m == 0)
          {
            zzcl();
            zzaf();
            Preconditions.checkNotEmpty(paramString);
            localObject1 = getWritableDatabase();
            ((SQLiteDatabase)localObject1).delete("property_filters", "app_id=? and audience_id=?", new String[] { paramString, String.valueOf(i2) });
            ((SQLiteDatabase)localObject1).delete("event_filters", "app_id=? and audience_id=?", new String[] { paramString, String.valueOf(i2) });
          }
        }
        label520:
        j += 1;
      }
      localObject1 = new ArrayList();
      j = paramArrayOfZzfu.length;
      int i = n;
      while (i < j)
      {
        ((List)localObject1).add(zzauy);
        i += 1;
      }
      remove(paramString, (List)localObject1);
      localSQLiteDatabase.setTransactionSuccessful();
      localSQLiteDatabase.endTransaction();
      return;
    }
    catch (Throwable paramString)
    {
      localSQLiteDatabase.endTransaction();
      throw paramString;
    }
  }
  
  /* Error */
  public final String zzah(long paramLong)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 206	com/google/android/android/measurement/internal/zzco:zzaf	()V
    //   4: aload_0
    //   5: invokevirtual 203	com/google/android/android/measurement/internal/zzez:zzcl	()V
    //   8: aload_0
    //   9: invokevirtual 310	com/google/android/android/measurement/internal/StringBuilder:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   12: astore 4
    //   14: aload 4
    //   16: ldc_w 1192
    //   19: iconst_1
    //   20: anewarray 19	java/lang/String
    //   23: dup
    //   24: iconst_0
    //   25: lload_1
    //   26: invokestatic 1194	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   29: aastore
    //   30: invokevirtual 362	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   33: astore 6
    //   35: aload 6
    //   37: astore 5
    //   39: aload 5
    //   41: astore 4
    //   43: aload 6
    //   45: invokeinterface 368 1 0
    //   50: istore_3
    //   51: iload_3
    //   52: ifne +34 -> 86
    //   55: aload 5
    //   57: astore 4
    //   59: aload_0
    //   60: invokevirtual 232	com/google/android/android/measurement/internal/zzco:zzgo	()Lcom/google/android/android/measurement/internal/zzap;
    //   63: invokevirtual 783	com/google/android/android/measurement/internal/zzap:zzjl	()Lcom/google/android/android/measurement/internal/zzar;
    //   66: ldc_w 1196
    //   69: invokevirtual 395	com/google/android/android/measurement/internal/zzar:zzbx	(Ljava/lang/String;)V
    //   72: aload 6
    //   74: ifnull +109 -> 183
    //   77: aload 6
    //   79: invokeinterface 375 1 0
    //   84: aconst_null
    //   85: areturn
    //   86: aload 5
    //   88: astore 4
    //   90: aload 6
    //   92: iconst_0
    //   93: invokeinterface 399 2 0
    //   98: astore 7
    //   100: aload 6
    //   102: ifnull +83 -> 185
    //   105: aload 6
    //   107: invokeinterface 375 1 0
    //   112: aload 7
    //   114: areturn
    //   115: astore 6
    //   117: goto +16 -> 133
    //   120: astore 5
    //   122: aconst_null
    //   123: astore 4
    //   125: goto +43 -> 168
    //   128: astore 6
    //   130: aconst_null
    //   131: astore 5
    //   133: aload 5
    //   135: astore 4
    //   137: aload_0
    //   138: invokevirtual 232	com/google/android/android/measurement/internal/zzco:zzgo	()Lcom/google/android/android/measurement/internal/zzap;
    //   141: invokevirtual 323	com/google/android/android/measurement/internal/zzap:zzjd	()Lcom/google/android/android/measurement/internal/zzar;
    //   144: ldc_w 1198
    //   147: aload 6
    //   149: invokevirtual 328	com/google/android/android/measurement/internal/zzar:append	(Ljava/lang/String;Ljava/lang/Object;)V
    //   152: aload 5
    //   154: ifnull +34 -> 188
    //   157: aload 5
    //   159: invokeinterface 375 1 0
    //   164: aconst_null
    //   165: areturn
    //   166: astore 5
    //   168: aload 4
    //   170: ifnull +10 -> 180
    //   173: aload 4
    //   175: invokeinterface 375 1 0
    //   180: aload 5
    //   182: athrow
    //   183: aconst_null
    //   184: areturn
    //   185: aload 7
    //   187: areturn
    //   188: aconst_null
    //   189: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	190	0	this	StringBuilder
    //   0	190	1	paramLong	long
    //   50	2	3	bool	boolean
    //   12	162	4	localObject1	Object
    //   37	50	5	localCursor1	Cursor
    //   120	1	5	localThrowable1	Throwable
    //   131	27	5	localObject2	Object
    //   166	15	5	localThrowable2	Throwable
    //   33	73	6	localCursor2	Cursor
    //   115	1	6	localSQLiteException1	SQLiteException
    //   128	20	6	localSQLiteException2	SQLiteException
    //   98	88	7	str	String
    // Exception table:
    //   from	to	target	type
    //   43	51	115	android/database/sqlite/SQLiteException
    //   59	72	115	android/database/sqlite/SQLiteException
    //   90	100	115	android/database/sqlite/SQLiteException
    //   8	14	120	java/lang/Throwable
    //   14	35	120	java/lang/Throwable
    //   8	14	128	android/database/sqlite/SQLiteException
    //   14	35	128	android/database/sqlite/SQLiteException
    //   43	51	166	java/lang/Throwable
    //   59	72	166	java/lang/Throwable
    //   90	100	166	java/lang/Throwable
    //   137	152	166	java/lang/Throwable
  }
  
  /* Error */
  public final List zzbk(String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 212	com/google/android/android/common/internal/Preconditions:checkNotEmpty	(Ljava/lang/String;)Ljava/lang/String;
    //   4: pop
    //   5: aload_0
    //   6: invokevirtual 206	com/google/android/android/measurement/internal/zzco:zzaf	()V
    //   9: aload_0
    //   10: invokevirtual 203	com/google/android/android/measurement/internal/zzez:zzcl	()V
    //   13: new 462	java/util/ArrayList
    //   16: dup
    //   17: invokespecial 463	java/util/ArrayList:<init>	()V
    //   20: astore 9
    //   22: aload_0
    //   23: invokevirtual 310	com/google/android/android/measurement/internal/StringBuilder:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   26: astore 5
    //   28: aload 5
    //   30: ldc_w 775
    //   33: iconst_4
    //   34: anewarray 19	java/lang/String
    //   37: dup
    //   38: iconst_0
    //   39: ldc_w 574
    //   42: aastore
    //   43: dup
    //   44: iconst_1
    //   45: ldc 43
    //   47: aastore
    //   48: dup
    //   49: iconst_2
    //   50: ldc_w 769
    //   53: aastore
    //   54: dup
    //   55: iconst_3
    //   56: ldc_w 665
    //   59: aastore
    //   60: ldc_w 813
    //   63: iconst_1
    //   64: anewarray 19	java/lang/String
    //   67: dup
    //   68: iconst_0
    //   69: aload_1
    //   70: aastore
    //   71: aconst_null
    //   72: aconst_null
    //   73: ldc_w 835
    //   76: ldc_w 1202
    //   79: invokevirtual 841	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   82: astore 8
    //   84: aload 8
    //   86: astore 5
    //   88: aload 8
    //   90: invokeinterface 368 1 0
    //   95: istore_2
    //   96: iload_2
    //   97: ifne +18 -> 115
    //   100: aload 8
    //   102: ifnull +238 -> 340
    //   105: aload 8
    //   107: invokeinterface 375 1 0
    //   112: aload 9
    //   114: areturn
    //   115: aload 8
    //   117: iconst_0
    //   118: invokeinterface 399 2 0
    //   123: astore 10
    //   125: aload 8
    //   127: iconst_1
    //   128: invokeinterface 399 2 0
    //   133: astore 6
    //   135: aload 6
    //   137: astore 7
    //   139: aload 6
    //   141: ifnonnull +8 -> 149
    //   144: ldc_w 1204
    //   147: astore 7
    //   149: aload 8
    //   151: iconst_2
    //   152: invokeinterface 372 2 0
    //   157: lstore_3
    //   158: aload 5
    //   160: astore 6
    //   162: aload_0
    //   163: aload 8
    //   165: iconst_3
    //   166: invokespecial 845	com/google/android/android/measurement/internal/StringBuilder:get	(Landroid/database/Cursor;I)Ljava/lang/Object;
    //   169: astore 11
    //   171: aload 11
    //   173: ifnonnull +27 -> 200
    //   176: aload 5
    //   178: astore 6
    //   180: aload_0
    //   181: invokevirtual 232	com/google/android/android/measurement/internal/zzco:zzgo	()Lcom/google/android/android/measurement/internal/zzap;
    //   184: invokevirtual 323	com/google/android/android/measurement/internal/zzap:zzjd	()Lcom/google/android/android/measurement/internal/zzar;
    //   187: ldc_w 1206
    //   190: aload_1
    //   191: invokestatic 244	com/google/android/android/measurement/internal/zzap:zzbv	(Ljava/lang/String;)Ljava/lang/Object;
    //   194: invokevirtual 328	com/google/android/android/measurement/internal/zzar:append	(Ljava/lang/String;Ljava/lang/Object;)V
    //   197: goto +30 -> 227
    //   200: aload 5
    //   202: astore 6
    //   204: aload 9
    //   206: new 742	com/google/android/android/measurement/internal/zzfj
    //   209: dup
    //   210: aload_1
    //   211: aload 7
    //   213: aload 10
    //   215: lload_3
    //   216: aload 11
    //   218: invokespecial 850	com/google/android/android/measurement/internal/zzfj:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/Object;)V
    //   221: invokeinterface 479 2 0
    //   226: pop
    //   227: aload 5
    //   229: astore 6
    //   231: aload 8
    //   233: invokeinterface 853 1 0
    //   238: istore_2
    //   239: iload_2
    //   240: ifne -125 -> 115
    //   243: aload 8
    //   245: ifnull +95 -> 340
    //   248: aload 8
    //   250: invokeinterface 375 1 0
    //   255: aload 9
    //   257: areturn
    //   258: astore 7
    //   260: goto +24 -> 284
    //   263: astore_1
    //   264: goto +62 -> 326
    //   267: astore 7
    //   269: goto +15 -> 284
    //   272: astore_1
    //   273: aconst_null
    //   274: astore 5
    //   276: goto +50 -> 326
    //   279: astore 7
    //   281: aconst_null
    //   282: astore 5
    //   284: aload 5
    //   286: astore 6
    //   288: aload_0
    //   289: invokevirtual 232	com/google/android/android/measurement/internal/zzco:zzgo	()Lcom/google/android/android/measurement/internal/zzap;
    //   292: invokevirtual 323	com/google/android/android/measurement/internal/zzap:zzjd	()Lcom/google/android/android/measurement/internal/zzar;
    //   295: ldc_w 1208
    //   298: aload_1
    //   299: invokestatic 244	com/google/android/android/measurement/internal/zzap:zzbv	(Ljava/lang/String;)Ljava/lang/Object;
    //   302: aload 7
    //   304: invokevirtual 333	com/google/android/android/measurement/internal/zzar:append	(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    //   307: aload 5
    //   309: ifnull +34 -> 343
    //   312: aload 5
    //   314: invokeinterface 375 1 0
    //   319: aconst_null
    //   320: areturn
    //   321: astore_1
    //   322: aload 6
    //   324: astore 5
    //   326: aload 5
    //   328: ifnull +10 -> 338
    //   331: aload 5
    //   333: invokeinterface 375 1 0
    //   338: aload_1
    //   339: athrow
    //   340: aload 9
    //   342: areturn
    //   343: aconst_null
    //   344: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	345	0	this	StringBuilder
    //   0	345	1	paramString	String
    //   95	145	2	bool	boolean
    //   157	59	3	l	long
    //   26	306	5	localObject1	Object
    //   133	190	6	localObject2	Object
    //   137	75	7	localObject3	Object
    //   258	1	7	localSQLiteException1	SQLiteException
    //   267	1	7	localSQLiteException2	SQLiteException
    //   279	24	7	localSQLiteException3	SQLiteException
    //   82	167	8	localCursor	Cursor
    //   20	321	9	localArrayList	ArrayList
    //   123	91	10	str	String
    //   169	48	11	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   162	171	258	android/database/sqlite/SQLiteException
    //   180	197	258	android/database/sqlite/SQLiteException
    //   204	227	258	android/database/sqlite/SQLiteException
    //   231	239	258	android/database/sqlite/SQLiteException
    //   88	96	263	java/lang/Throwable
    //   115	135	263	java/lang/Throwable
    //   149	158	263	java/lang/Throwable
    //   88	96	267	android/database/sqlite/SQLiteException
    //   115	135	267	android/database/sqlite/SQLiteException
    //   149	158	267	android/database/sqlite/SQLiteException
    //   22	28	272	java/lang/Throwable
    //   28	84	272	java/lang/Throwable
    //   22	28	279	android/database/sqlite/SQLiteException
    //   28	84	279	android/database/sqlite/SQLiteException
    //   162	171	321	java/lang/Throwable
    //   180	197	321	java/lang/Throwable
    //   204	227	321	java/lang/Throwable
    //   231	239	321	java/lang/Throwable
    //   288	307	321	java/lang/Throwable
  }
  
  /* Error */
  public final class_4 zzbl(String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 212	com/google/android/android/common/internal/Preconditions:checkNotEmpty	(Ljava/lang/String;)Ljava/lang/String;
    //   4: pop
    //   5: aload_0
    //   6: invokevirtual 206	com/google/android/android/measurement/internal/zzco:zzaf	()V
    //   9: aload_0
    //   10: invokevirtual 203	com/google/android/android/measurement/internal/zzez:zzcl	()V
    //   13: aload_0
    //   14: invokevirtual 310	com/google/android/android/measurement/internal/StringBuilder:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   17: astore 7
    //   19: iconst_0
    //   20: istore 4
    //   22: aload 7
    //   24: ldc_w 1109
    //   27: bipush 26
    //   29: anewarray 19	java/lang/String
    //   32: dup
    //   33: iconst_0
    //   34: ldc_w 1020
    //   37: aastore
    //   38: dup
    //   39: iconst_1
    //   40: ldc_w 1025
    //   43: aastore
    //   44: dup
    //   45: iconst_2
    //   46: ldc_w 1030
    //   49: aastore
    //   50: dup
    //   51: iconst_3
    //   52: ldc_w 1035
    //   55: aastore
    //   56: dup
    //   57: iconst_4
    //   58: ldc 69
    //   60: aastore
    //   61: dup
    //   62: iconst_5
    //   63: ldc_w 1044
    //   66: aastore
    //   67: dup
    //   68: bipush 6
    //   70: ldc 49
    //   72: aastore
    //   73: dup
    //   74: bipush 7
    //   76: ldc 53
    //   78: aastore
    //   79: dup
    //   80: bipush 8
    //   82: ldc 57
    //   84: aastore
    //   85: dup
    //   86: bipush 9
    //   88: ldc 61
    //   90: aastore
    //   91: dup
    //   92: bipush 10
    //   94: ldc 65
    //   96: aastore
    //   97: dup
    //   98: bipush 11
    //   100: ldc 73
    //   102: aastore
    //   103: dup
    //   104: bipush 12
    //   106: ldc 77
    //   108: aastore
    //   109: dup
    //   110: bipush 13
    //   112: ldc 81
    //   114: aastore
    //   115: dup
    //   116: bipush 14
    //   118: ldc 85
    //   120: aastore
    //   121: dup
    //   122: bipush 15
    //   124: ldc 93
    //   126: aastore
    //   127: dup
    //   128: bipush 16
    //   130: ldc 97
    //   132: aastore
    //   133: dup
    //   134: bipush 17
    //   136: ldc 101
    //   138: aastore
    //   139: dup
    //   140: bipush 18
    //   142: ldc 105
    //   144: aastore
    //   145: dup
    //   146: bipush 19
    //   148: ldc 109
    //   150: aastore
    //   151: dup
    //   152: bipush 20
    //   154: ldc 113
    //   156: aastore
    //   157: dup
    //   158: bipush 21
    //   160: ldc 117
    //   162: aastore
    //   163: dup
    //   164: bipush 22
    //   166: ldc 121
    //   168: aastore
    //   169: dup
    //   170: bipush 23
    //   172: ldc 125
    //   174: aastore
    //   175: dup
    //   176: bipush 24
    //   178: ldc -127
    //   180: aastore
    //   181: dup
    //   182: bipush 25
    //   184: ldc -123
    //   186: aastore
    //   187: ldc_w 813
    //   190: iconst_1
    //   191: anewarray 19	java/lang/String
    //   194: dup
    //   195: iconst_0
    //   196: aload_1
    //   197: aastore
    //   198: aconst_null
    //   199: aconst_null
    //   200: aconst_null
    //   201: invokevirtual 890	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   204: astore 9
    //   206: aload 9
    //   208: astore 7
    //   210: aload 9
    //   212: invokeinterface 368 1 0
    //   217: istore_3
    //   218: iload_3
    //   219: ifne +17 -> 236
    //   222: aload 9
    //   224: ifnull +820 -> 1044
    //   227: aload 9
    //   229: invokeinterface 375 1 0
    //   234: aconst_null
    //   235: areturn
    //   236: aload_0
    //   237: getfield 1214	com/google/android/android/measurement/internal/zzey:zzamz	Lcom/google/android/android/measurement/internal/zzfa;
    //   240: astore 10
    //   242: aload 7
    //   244: astore 8
    //   246: new 1015	com/google/android/android/measurement/internal/class_4
    //   249: dup
    //   250: aload 10
    //   252: invokevirtual 1220	com/google/android/android/measurement/internal/zzfa:zzmb	()Lcom/google/android/android/measurement/internal/zzbt;
    //   255: aload_1
    //   256: invokespecial 1223	com/google/android/android/measurement/internal/class_4:<init>	(Lcom/google/android/android/measurement/internal/zzbt;Ljava/lang/String;)V
    //   259: astore 10
    //   261: aload 7
    //   263: astore 8
    //   265: aload 10
    //   267: aload 9
    //   269: iconst_0
    //   270: invokeinterface 399 2 0
    //   275: invokevirtual 1226	com/google/android/android/measurement/internal/class_4:zzam	(Ljava/lang/String;)V
    //   278: aload 7
    //   280: astore 8
    //   282: aload 10
    //   284: aload 9
    //   286: iconst_1
    //   287: invokeinterface 399 2 0
    //   292: invokevirtual 1229	com/google/android/android/measurement/internal/class_4:zzan	(Ljava/lang/String;)V
    //   295: aload 7
    //   297: astore 8
    //   299: aload 10
    //   301: aload 9
    //   303: iconst_2
    //   304: invokeinterface 399 2 0
    //   309: invokevirtual 1232	com/google/android/android/measurement/internal/class_4:zzap	(Ljava/lang/String;)V
    //   312: aload 7
    //   314: astore 8
    //   316: aload 10
    //   318: aload 9
    //   320: iconst_3
    //   321: invokeinterface 372 2 0
    //   326: invokevirtual 1235	com/google/android/android/measurement/internal/class_4:trim	(J)V
    //   329: aload 7
    //   331: astore 8
    //   333: aload 10
    //   335: aload 9
    //   337: iconst_4
    //   338: invokeinterface 372 2 0
    //   343: invokevirtual 1238	com/google/android/android/measurement/internal/class_4:findAll	(J)V
    //   346: aload 7
    //   348: astore 8
    //   350: aload 10
    //   352: aload 9
    //   354: iconst_5
    //   355: invokeinterface 372 2 0
    //   360: invokevirtual 1241	com/google/android/android/measurement/internal/class_4:removeOldest	(J)V
    //   363: aload 7
    //   365: astore 8
    //   367: aload 10
    //   369: aload 9
    //   371: bipush 6
    //   373: invokeinterface 399 2 0
    //   378: invokevirtual 1244	com/google/android/android/measurement/internal/class_4:setAppVersion	(Ljava/lang/String;)V
    //   381: aload 7
    //   383: astore 8
    //   385: aload 10
    //   387: aload 9
    //   389: bipush 7
    //   391: invokeinterface 399 2 0
    //   396: invokevirtual 1247	com/google/android/android/measurement/internal/class_4:zzar	(Ljava/lang/String;)V
    //   399: aload 7
    //   401: astore 8
    //   403: aload 10
    //   405: aload 9
    //   407: bipush 8
    //   409: invokeinterface 372 2 0
    //   414: invokevirtual 1250	com/google/android/android/measurement/internal/class_4:checkForConnection	(J)V
    //   417: aload 7
    //   419: astore 8
    //   421: aload 10
    //   423: aload 9
    //   425: bipush 9
    //   427: invokeinterface 372 2 0
    //   432: invokevirtual 1253	com/google/android/android/measurement/internal/class_4:tryEmit	(J)V
    //   435: aload 7
    //   437: astore 8
    //   439: aload 9
    //   441: bipush 10
    //   443: invokeinterface 934 2 0
    //   448: istore_3
    //   449: iload_3
    //   450: ifne +29 -> 479
    //   453: aload 7
    //   455: astore 8
    //   457: aload 9
    //   459: bipush 10
    //   461: invokeinterface 937 2 0
    //   466: istore_2
    //   467: iload_2
    //   468: ifeq +6 -> 474
    //   471: goto +8 -> 479
    //   474: iconst_0
    //   475: istore_3
    //   476: goto +5 -> 481
    //   479: iconst_1
    //   480: istore_3
    //   481: aload 7
    //   483: astore 8
    //   485: aload 10
    //   487: iload_3
    //   488: invokevirtual 1256	com/google/android/android/measurement/internal/class_4:setMeasurementEnabled	(Z)V
    //   491: aload 7
    //   493: astore 8
    //   495: aload 10
    //   497: aload 9
    //   499: bipush 11
    //   501: invokeinterface 372 2 0
    //   506: invokevirtual 1259	com/google/android/android/measurement/internal/class_4:zzaa	(J)V
    //   509: aload 7
    //   511: astore 8
    //   513: aload 10
    //   515: aload 9
    //   517: bipush 12
    //   519: invokeinterface 372 2 0
    //   524: invokevirtual 1262	com/google/android/android/measurement/internal/class_4:zzab	(J)V
    //   527: aload 7
    //   529: astore 8
    //   531: aload 10
    //   533: aload 9
    //   535: bipush 13
    //   537: invokeinterface 372 2 0
    //   542: invokevirtual 1265	com/google/android/android/measurement/internal/class_4:zzac	(J)V
    //   545: aload 7
    //   547: astore 8
    //   549: aload 10
    //   551: aload 9
    //   553: bipush 14
    //   555: invokeinterface 372 2 0
    //   560: invokevirtual 1268	com/google/android/android/measurement/internal/class_4:zzad	(J)V
    //   563: aload 7
    //   565: astore 8
    //   567: aload 10
    //   569: aload 9
    //   571: bipush 15
    //   573: invokeinterface 372 2 0
    //   578: invokevirtual 1271	com/google/android/android/measurement/internal/class_4:writeLong	(J)V
    //   581: aload 7
    //   583: astore 8
    //   585: aload 10
    //   587: aload 9
    //   589: bipush 16
    //   591: invokeinterface 372 2 0
    //   596: invokevirtual 1274	com/google/android/android/measurement/internal/class_4:refreshScreen	(J)V
    //   599: aload 7
    //   601: astore 8
    //   603: aload 9
    //   605: bipush 17
    //   607: invokeinterface 934 2 0
    //   612: istore_3
    //   613: iload_3
    //   614: ifeq +11 -> 625
    //   617: ldc2_w 1275
    //   620: lstore 5
    //   622: goto +21 -> 643
    //   625: aload 7
    //   627: astore 8
    //   629: aload 9
    //   631: bipush 17
    //   633: invokeinterface 937 2 0
    //   638: istore_2
    //   639: iload_2
    //   640: i2l
    //   641: lstore 5
    //   643: aload 7
    //   645: astore 8
    //   647: aload 10
    //   649: lload 5
    //   651: invokevirtual 1279	com/google/android/android/measurement/internal/class_4:addSuffix	(J)V
    //   654: aload 7
    //   656: astore 8
    //   658: aload 10
    //   660: aload 9
    //   662: bipush 18
    //   664: invokeinterface 399 2 0
    //   669: invokevirtual 1282	com/google/android/android/measurement/internal/class_4:zzaq	(Ljava/lang/String;)V
    //   672: aload 7
    //   674: astore 8
    //   676: aload 10
    //   678: aload 9
    //   680: bipush 19
    //   682: invokeinterface 372 2 0
    //   687: invokevirtual 1284	com/google/android/android/measurement/internal/class_4:zzaf	(J)V
    //   690: aload 7
    //   692: astore 8
    //   694: aload 10
    //   696: aload 9
    //   698: bipush 20
    //   700: invokeinterface 372 2 0
    //   705: invokevirtual 1287	com/google/android/android/measurement/internal/class_4:zzae	(J)V
    //   708: aload 7
    //   710: astore 8
    //   712: aload 10
    //   714: aload 9
    //   716: bipush 21
    //   718: invokeinterface 399 2 0
    //   723: invokevirtual 1290	com/google/android/android/measurement/internal/class_4:zzas	(Ljava/lang/String;)V
    //   726: aload 7
    //   728: astore 8
    //   730: aload 9
    //   732: bipush 22
    //   734: invokeinterface 934 2 0
    //   739: istore_3
    //   740: iload_3
    //   741: ifeq +9 -> 750
    //   744: lconst_0
    //   745: lstore 5
    //   747: goto +18 -> 765
    //   750: aload 7
    //   752: astore 8
    //   754: aload 9
    //   756: bipush 22
    //   758: invokeinterface 372 2 0
    //   763: lstore 5
    //   765: aload 7
    //   767: astore 8
    //   769: aload 10
    //   771: lload 5
    //   773: invokevirtual 1293	com/google/android/android/measurement/internal/class_4:zzag	(J)V
    //   776: aload 7
    //   778: astore 8
    //   780: aload 9
    //   782: bipush 23
    //   784: invokeinterface 934 2 0
    //   789: istore_3
    //   790: iload_3
    //   791: ifne +29 -> 820
    //   794: aload 7
    //   796: astore 8
    //   798: aload 9
    //   800: bipush 23
    //   802: invokeinterface 937 2 0
    //   807: istore_2
    //   808: iload_2
    //   809: ifeq +6 -> 815
    //   812: goto +8 -> 820
    //   815: iconst_0
    //   816: istore_3
    //   817: goto +5 -> 822
    //   820: iconst_1
    //   821: istore_3
    //   822: aload 7
    //   824: astore 8
    //   826: aload 10
    //   828: iload_3
    //   829: invokevirtual 1296	com/google/android/android/measurement/internal/class_4:updateButton	(Z)V
    //   832: aload 7
    //   834: astore 8
    //   836: aload 9
    //   838: bipush 24
    //   840: invokeinterface 934 2 0
    //   845: istore_3
    //   846: iload_3
    //   847: ifne +24 -> 871
    //   850: aload 7
    //   852: astore 8
    //   854: aload 9
    //   856: bipush 24
    //   858: invokeinterface 937 2 0
    //   863: istore_2
    //   864: iload 4
    //   866: istore_3
    //   867: iload_2
    //   868: ifeq +5 -> 873
    //   871: iconst_1
    //   872: istore_3
    //   873: aload 7
    //   875: astore 8
    //   877: aload 10
    //   879: iload_3
    //   880: invokevirtual 1299	com/google/android/android/measurement/internal/class_4:blur	(Z)V
    //   883: aload 7
    //   885: astore 8
    //   887: aload 10
    //   889: aload 9
    //   891: bipush 25
    //   893: invokeinterface 399 2 0
    //   898: invokevirtual 1302	com/google/android/android/measurement/internal/class_4:zzao	(Ljava/lang/String;)V
    //   901: aload 7
    //   903: astore 8
    //   905: aload 10
    //   907: invokevirtual 1305	com/google/android/android/measurement/internal/class_4:zzgv	()V
    //   910: aload 7
    //   912: astore 8
    //   914: aload 9
    //   916: invokeinterface 853 1 0
    //   921: istore_3
    //   922: iload_3
    //   923: ifeq +24 -> 947
    //   926: aload 7
    //   928: astore 8
    //   930: aload_0
    //   931: invokevirtual 232	com/google/android/android/measurement/internal/zzco:zzgo	()Lcom/google/android/android/measurement/internal/zzap;
    //   934: invokevirtual 323	com/google/android/android/measurement/internal/zzap:zzjd	()Lcom/google/android/android/measurement/internal/zzar;
    //   937: ldc_w 1307
    //   940: aload_1
    //   941: invokestatic 244	com/google/android/android/measurement/internal/zzap:zzbv	(Ljava/lang/String;)Ljava/lang/Object;
    //   944: invokevirtual 328	com/google/android/android/measurement/internal/zzar:append	(Ljava/lang/String;Ljava/lang/Object;)V
    //   947: aload 9
    //   949: ifnull +97 -> 1046
    //   952: aload 9
    //   954: invokeinterface 375 1 0
    //   959: aload 10
    //   961: areturn
    //   962: astore 9
    //   964: goto +24 -> 988
    //   967: astore_1
    //   968: goto +62 -> 1030
    //   971: astore 9
    //   973: goto +15 -> 988
    //   976: astore_1
    //   977: aconst_null
    //   978: astore 7
    //   980: goto +50 -> 1030
    //   983: astore 9
    //   985: aconst_null
    //   986: astore 7
    //   988: aload 7
    //   990: astore 8
    //   992: aload_0
    //   993: invokevirtual 232	com/google/android/android/measurement/internal/zzco:zzgo	()Lcom/google/android/android/measurement/internal/zzap;
    //   996: invokevirtual 323	com/google/android/android/measurement/internal/zzap:zzjd	()Lcom/google/android/android/measurement/internal/zzar;
    //   999: ldc_w 1309
    //   1002: aload_1
    //   1003: invokestatic 244	com/google/android/android/measurement/internal/zzap:zzbv	(Ljava/lang/String;)Ljava/lang/Object;
    //   1006: aload 9
    //   1008: invokevirtual 333	com/google/android/android/measurement/internal/zzar:append	(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    //   1011: aload 7
    //   1013: ifnull +36 -> 1049
    //   1016: aload 7
    //   1018: invokeinterface 375 1 0
    //   1023: aconst_null
    //   1024: areturn
    //   1025: astore_1
    //   1026: aload 8
    //   1028: astore 7
    //   1030: aload 7
    //   1032: ifnull +10 -> 1042
    //   1035: aload 7
    //   1037: invokeinterface 375 1 0
    //   1042: aload_1
    //   1043: athrow
    //   1044: aconst_null
    //   1045: areturn
    //   1046: aload 10
    //   1048: areturn
    //   1049: aconst_null
    //   1050: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1051	0	this	StringBuilder
    //   0	1051	1	paramString	String
    //   466	402	2	i	int
    //   217	706	3	bool1	boolean
    //   20	845	4	bool2	boolean
    //   620	152	5	l	long
    //   17	1019	7	localObject1	Object
    //   244	783	8	localObject2	Object
    //   204	749	9	localCursor	Cursor
    //   962	1	9	localSQLiteException1	SQLiteException
    //   971	1	9	localSQLiteException2	SQLiteException
    //   983	24	9	localSQLiteException3	SQLiteException
    //   240	807	10	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   246	261	962	android/database/sqlite/SQLiteException
    //   265	278	962	android/database/sqlite/SQLiteException
    //   282	295	962	android/database/sqlite/SQLiteException
    //   299	312	962	android/database/sqlite/SQLiteException
    //   316	329	962	android/database/sqlite/SQLiteException
    //   333	346	962	android/database/sqlite/SQLiteException
    //   350	363	962	android/database/sqlite/SQLiteException
    //   367	381	962	android/database/sqlite/SQLiteException
    //   385	399	962	android/database/sqlite/SQLiteException
    //   403	417	962	android/database/sqlite/SQLiteException
    //   421	435	962	android/database/sqlite/SQLiteException
    //   439	449	962	android/database/sqlite/SQLiteException
    //   457	467	962	android/database/sqlite/SQLiteException
    //   485	491	962	android/database/sqlite/SQLiteException
    //   495	509	962	android/database/sqlite/SQLiteException
    //   513	527	962	android/database/sqlite/SQLiteException
    //   531	545	962	android/database/sqlite/SQLiteException
    //   549	563	962	android/database/sqlite/SQLiteException
    //   567	581	962	android/database/sqlite/SQLiteException
    //   585	599	962	android/database/sqlite/SQLiteException
    //   603	613	962	android/database/sqlite/SQLiteException
    //   629	639	962	android/database/sqlite/SQLiteException
    //   647	654	962	android/database/sqlite/SQLiteException
    //   658	672	962	android/database/sqlite/SQLiteException
    //   676	690	962	android/database/sqlite/SQLiteException
    //   694	708	962	android/database/sqlite/SQLiteException
    //   712	726	962	android/database/sqlite/SQLiteException
    //   730	740	962	android/database/sqlite/SQLiteException
    //   754	765	962	android/database/sqlite/SQLiteException
    //   769	776	962	android/database/sqlite/SQLiteException
    //   780	790	962	android/database/sqlite/SQLiteException
    //   798	808	962	android/database/sqlite/SQLiteException
    //   826	832	962	android/database/sqlite/SQLiteException
    //   836	846	962	android/database/sqlite/SQLiteException
    //   854	864	962	android/database/sqlite/SQLiteException
    //   877	883	962	android/database/sqlite/SQLiteException
    //   887	901	962	android/database/sqlite/SQLiteException
    //   905	910	962	android/database/sqlite/SQLiteException
    //   914	922	962	android/database/sqlite/SQLiteException
    //   930	947	962	android/database/sqlite/SQLiteException
    //   210	218	967	java/lang/Throwable
    //   210	218	971	android/database/sqlite/SQLiteException
    //   13	19	976	java/lang/Throwable
    //   22	206	976	java/lang/Throwable
    //   13	19	983	android/database/sqlite/SQLiteException
    //   22	206	983	android/database/sqlite/SQLiteException
    //   246	261	1025	java/lang/Throwable
    //   265	278	1025	java/lang/Throwable
    //   282	295	1025	java/lang/Throwable
    //   299	312	1025	java/lang/Throwable
    //   316	329	1025	java/lang/Throwable
    //   333	346	1025	java/lang/Throwable
    //   350	363	1025	java/lang/Throwable
    //   367	381	1025	java/lang/Throwable
    //   385	399	1025	java/lang/Throwable
    //   403	417	1025	java/lang/Throwable
    //   421	435	1025	java/lang/Throwable
    //   439	449	1025	java/lang/Throwable
    //   457	467	1025	java/lang/Throwable
    //   485	491	1025	java/lang/Throwable
    //   495	509	1025	java/lang/Throwable
    //   513	527	1025	java/lang/Throwable
    //   531	545	1025	java/lang/Throwable
    //   549	563	1025	java/lang/Throwable
    //   567	581	1025	java/lang/Throwable
    //   585	599	1025	java/lang/Throwable
    //   603	613	1025	java/lang/Throwable
    //   629	639	1025	java/lang/Throwable
    //   647	654	1025	java/lang/Throwable
    //   658	672	1025	java/lang/Throwable
    //   676	690	1025	java/lang/Throwable
    //   694	708	1025	java/lang/Throwable
    //   712	726	1025	java/lang/Throwable
    //   730	740	1025	java/lang/Throwable
    //   754	765	1025	java/lang/Throwable
    //   769	776	1025	java/lang/Throwable
    //   780	790	1025	java/lang/Throwable
    //   798	808	1025	java/lang/Throwable
    //   826	832	1025	java/lang/Throwable
    //   836	846	1025	java/lang/Throwable
    //   854	864	1025	java/lang/Throwable
    //   877	883	1025	java/lang/Throwable
    //   887	901	1025	java/lang/Throwable
    //   905	910	1025	java/lang/Throwable
    //   914	922	1025	java/lang/Throwable
    //   930	947	1025	java/lang/Throwable
    //   992	1011	1025	java/lang/Throwable
  }
  
  public final long zzbm(String paramString)
  {
    Preconditions.checkNotEmpty(paramString);
    zzaf();
    zzcl();
    try
    {
      SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
      class_3 localClass_3 = zzgq();
      zzaf.zza localZza = zzaf.zzajs;
      int i = Math.max(0, Math.min(1000000, localClass_3.next(paramString, localZza)));
      i = localSQLiteDatabase.delete("raw_events", "rowid in (select rowid from raw_events where app_id=? order by rowid desc limit -1 offset ?)", new String[] { paramString, String.valueOf(i) });
      return i;
    }
    catch (SQLiteException localSQLiteException)
    {
      zzgo().zzjd().append("Error deleting over the limit events. appId", zzap.zzbv(paramString), localSQLiteException);
    }
    return 0L;
  }
  
  /* Error */
  public final byte[] zzbn(String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 212	com/google/android/android/common/internal/Preconditions:checkNotEmpty	(Ljava/lang/String;)Ljava/lang/String;
    //   4: pop
    //   5: aload_0
    //   6: invokevirtual 206	com/google/android/android/measurement/internal/zzco:zzaf	()V
    //   9: aload_0
    //   10: invokevirtual 203	com/google/android/android/measurement/internal/zzez:zzcl	()V
    //   13: aload_0
    //   14: invokevirtual 310	com/google/android/android/measurement/internal/StringBuilder:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   17: astore_3
    //   18: aload_3
    //   19: ldc_w 1109
    //   22: iconst_1
    //   23: anewarray 19	java/lang/String
    //   26: dup
    //   27: iconst_0
    //   28: ldc 89
    //   30: aastore
    //   31: ldc_w 813
    //   34: iconst_1
    //   35: anewarray 19	java/lang/String
    //   38: dup
    //   39: iconst_0
    //   40: aload_1
    //   41: aastore
    //   42: aconst_null
    //   43: aconst_null
    //   44: aconst_null
    //   45: invokevirtual 890	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   48: astore 5
    //   50: aload 5
    //   52: astore 4
    //   54: aload 4
    //   56: astore_3
    //   57: aload 5
    //   59: invokeinterface 368 1 0
    //   64: istore_2
    //   65: iload_2
    //   66: ifne +17 -> 83
    //   69: aload 5
    //   71: ifnull +140 -> 211
    //   74: aload 5
    //   76: invokeinterface 375 1 0
    //   81: aconst_null
    //   82: areturn
    //   83: aload 4
    //   85: astore_3
    //   86: aload 5
    //   88: iconst_0
    //   89: invokeinterface 864 2 0
    //   94: astore 6
    //   96: aload 4
    //   98: astore_3
    //   99: aload 5
    //   101: invokeinterface 853 1 0
    //   106: istore_2
    //   107: iload_2
    //   108: ifeq +23 -> 131
    //   111: aload 4
    //   113: astore_3
    //   114: aload_0
    //   115: invokevirtual 232	com/google/android/android/measurement/internal/zzco:zzgo	()Lcom/google/android/android/measurement/internal/zzap;
    //   118: invokevirtual 323	com/google/android/android/measurement/internal/zzap:zzjd	()Lcom/google/android/android/measurement/internal/zzar;
    //   121: ldc_w 1325
    //   124: aload_1
    //   125: invokestatic 244	com/google/android/android/measurement/internal/zzap:zzbv	(Ljava/lang/String;)Ljava/lang/Object;
    //   128: invokevirtual 328	com/google/android/android/measurement/internal/zzar:append	(Ljava/lang/String;Ljava/lang/Object;)V
    //   131: aload 5
    //   133: ifnull +80 -> 213
    //   136: aload 5
    //   138: invokeinterface 375 1 0
    //   143: aload 6
    //   145: areturn
    //   146: astore 5
    //   148: goto +14 -> 162
    //   151: astore_1
    //   152: aconst_null
    //   153: astore_3
    //   154: goto +45 -> 199
    //   157: astore 5
    //   159: aconst_null
    //   160: astore 4
    //   162: aload 4
    //   164: astore_3
    //   165: aload_0
    //   166: invokevirtual 232	com/google/android/android/measurement/internal/zzco:zzgo	()Lcom/google/android/android/measurement/internal/zzap;
    //   169: invokevirtual 323	com/google/android/android/measurement/internal/zzap:zzjd	()Lcom/google/android/android/measurement/internal/zzar;
    //   172: ldc_w 1327
    //   175: aload_1
    //   176: invokestatic 244	com/google/android/android/measurement/internal/zzap:zzbv	(Ljava/lang/String;)Ljava/lang/Object;
    //   179: aload 5
    //   181: invokevirtual 333	com/google/android/android/measurement/internal/zzar:append	(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    //   184: aload 4
    //   186: ifnull +30 -> 216
    //   189: aload 4
    //   191: invokeinterface 375 1 0
    //   196: aconst_null
    //   197: areturn
    //   198: astore_1
    //   199: aload_3
    //   200: ifnull +9 -> 209
    //   203: aload_3
    //   204: invokeinterface 375 1 0
    //   209: aload_1
    //   210: athrow
    //   211: aconst_null
    //   212: areturn
    //   213: aload 6
    //   215: areturn
    //   216: aconst_null
    //   217: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	218	0	this	StringBuilder
    //   0	218	1	paramString	String
    //   64	44	2	bool	boolean
    //   17	187	3	localObject	Object
    //   52	138	4	localCursor1	Cursor
    //   48	89	5	localCursor2	Cursor
    //   146	1	5	localSQLiteException1	SQLiteException
    //   157	23	5	localSQLiteException2	SQLiteException
    //   94	120	6	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   57	65	146	android/database/sqlite/SQLiteException
    //   86	96	146	android/database/sqlite/SQLiteException
    //   99	107	146	android/database/sqlite/SQLiteException
    //   114	131	146	android/database/sqlite/SQLiteException
    //   13	18	151	java/lang/Throwable
    //   18	50	151	java/lang/Throwable
    //   13	18	157	android/database/sqlite/SQLiteException
    //   18	50	157	android/database/sqlite/SQLiteException
    //   57	65	198	java/lang/Throwable
    //   86	96	198	java/lang/Throwable
    //   99	107	198	java/lang/Throwable
    //   114	131	198	java/lang/Throwable
    //   165	184	198	java/lang/Throwable
  }
  
  /* Error */
  final Map zzbo(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 203	com/google/android/android/measurement/internal/zzez:zzcl	()V
    //   4: aload_0
    //   5: invokevirtual 206	com/google/android/android/measurement/internal/zzco:zzaf	()V
    //   8: aload_1
    //   9: invokestatic 212	com/google/android/android/common/internal/Preconditions:checkNotEmpty	(Ljava/lang/String;)Ljava/lang/String;
    //   12: pop
    //   13: aload_0
    //   14: invokevirtual 310	com/google/android/android/measurement/internal/StringBuilder:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   17: astore 5
    //   19: aload 5
    //   21: ldc_w 509
    //   24: iconst_2
    //   25: anewarray 19	java/lang/String
    //   28: dup
    //   29: iconst_0
    //   30: ldc_w 294
    //   33: aastore
    //   34: dup
    //   35: iconst_1
    //   36: ldc_w 1331
    //   39: aastore
    //   40: ldc_w 813
    //   43: iconst_1
    //   44: anewarray 19	java/lang/String
    //   47: dup
    //   48: iconst_0
    //   49: aload_1
    //   50: aastore
    //   51: aconst_null
    //   52: aconst_null
    //   53: aconst_null
    //   54: invokevirtual 890	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   57: astore 7
    //   59: aload 7
    //   61: astore 6
    //   63: aload 6
    //   65: astore 5
    //   67: aload 7
    //   69: invokeinterface 368 1 0
    //   74: istore 4
    //   76: iload 4
    //   78: ifne +17 -> 95
    //   81: aload 7
    //   83: ifnull +250 -> 333
    //   86: aload 7
    //   88: invokeinterface 375 1 0
    //   93: aconst_null
    //   94: areturn
    //   95: aload 6
    //   97: astore 5
    //   99: new 1333	android/support/v4/util/ArrayMap
    //   102: dup
    //   103: invokespecial 1334	android/support/v4/util/ArrayMap:<init>	()V
    //   106: astore 8
    //   108: aload 6
    //   110: astore 5
    //   112: aload 7
    //   114: iconst_0
    //   115: invokeinterface 937 2 0
    //   120: istore_2
    //   121: aload 6
    //   123: astore 5
    //   125: aload 7
    //   127: iconst_1
    //   128: invokeinterface 864 2 0
    //   133: astore 9
    //   135: aload 6
    //   137: astore 5
    //   139: aload 9
    //   141: arraylength
    //   142: istore_3
    //   143: aload 6
    //   145: astore 5
    //   147: aload 9
    //   149: iconst_0
    //   150: iload_3
    //   151: invokestatic 869	com/google/android/android/internal/measurement/zzyx:get	([BII)Lcom/google/android/android/internal/measurement/zzyx;
    //   154: astore 9
    //   156: aload 6
    //   158: astore 5
    //   160: new 1336	com/google/android/android/internal/measurement/zzgj
    //   163: dup
    //   164: invokespecial 1337	com/google/android/android/internal/measurement/zzgj:<init>	()V
    //   167: astore 10
    //   169: aload 6
    //   171: astore 5
    //   173: aload 10
    //   175: aload 9
    //   177: invokevirtual 876	com/google/android/android/internal/measurement/zzzg:digest	(Lcom/google/android/android/internal/measurement/zzyx;)Lcom/google/android/android/internal/measurement/zzzg;
    //   180: pop
    //   181: aload 6
    //   183: astore 5
    //   185: aload 8
    //   187: iload_2
    //   188: invokestatic 250	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   191: aload 10
    //   193: invokeinterface 1342 3 0
    //   198: pop
    //   199: goto +32 -> 231
    //   202: astore 9
    //   204: aload 6
    //   206: astore 5
    //   208: aload_0
    //   209: invokevirtual 232	com/google/android/android/measurement/internal/zzco:zzgo	()Lcom/google/android/android/measurement/internal/zzap;
    //   212: invokevirtual 323	com/google/android/android/measurement/internal/zzap:zzjd	()Lcom/google/android/android/measurement/internal/zzar;
    //   215: ldc_w 1344
    //   218: aload_1
    //   219: invokestatic 244	com/google/android/android/measurement/internal/zzap:zzbv	(Ljava/lang/String;)Ljava/lang/Object;
    //   222: iload_2
    //   223: invokestatic 250	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   226: aload 9
    //   228: invokevirtual 263	com/google/android/android/measurement/internal/zzar:append	(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V
    //   231: aload 6
    //   233: astore 5
    //   235: aload 7
    //   237: invokeinterface 853 1 0
    //   242: istore 4
    //   244: iload 4
    //   246: ifne -138 -> 108
    //   249: aload 7
    //   251: ifnull +84 -> 335
    //   254: aload 7
    //   256: invokeinterface 375 1 0
    //   261: aload 8
    //   263: areturn
    //   264: astore 7
    //   266: goto +15 -> 281
    //   269: astore_1
    //   270: aconst_null
    //   271: astore 5
    //   273: goto +46 -> 319
    //   276: astore 7
    //   278: aconst_null
    //   279: astore 6
    //   281: aload 6
    //   283: astore 5
    //   285: aload_0
    //   286: invokevirtual 232	com/google/android/android/measurement/internal/zzco:zzgo	()Lcom/google/android/android/measurement/internal/zzap;
    //   289: invokevirtual 323	com/google/android/android/measurement/internal/zzap:zzjd	()Lcom/google/android/android/measurement/internal/zzar;
    //   292: ldc_w 1346
    //   295: aload_1
    //   296: invokestatic 244	com/google/android/android/measurement/internal/zzap:zzbv	(Ljava/lang/String;)Ljava/lang/Object;
    //   299: aload 7
    //   301: invokevirtual 333	com/google/android/android/measurement/internal/zzar:append	(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    //   304: aload 6
    //   306: ifnull +32 -> 338
    //   309: aload 6
    //   311: invokeinterface 375 1 0
    //   316: aconst_null
    //   317: areturn
    //   318: astore_1
    //   319: aload 5
    //   321: ifnull +10 -> 331
    //   324: aload 5
    //   326: invokeinterface 375 1 0
    //   331: aload_1
    //   332: athrow
    //   333: aconst_null
    //   334: areturn
    //   335: aload 8
    //   337: areturn
    //   338: aconst_null
    //   339: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	340	0	this	StringBuilder
    //   0	340	1	paramString	String
    //   120	103	2	i	int
    //   142	9	3	j	int
    //   74	171	4	bool	boolean
    //   17	308	5	localObject1	Object
    //   61	249	6	localCursor1	Cursor
    //   57	198	7	localCursor2	Cursor
    //   264	1	7	localSQLiteException1	SQLiteException
    //   276	24	7	localSQLiteException2	SQLiteException
    //   106	230	8	localArrayMap	android.support.v4.util.ArrayMap
    //   133	43	9	localObject2	Object
    //   202	25	9	localIOException	IOException
    //   167	25	10	localZzgj	com.google.android.android.internal.measurement.zzgj
    // Exception table:
    //   from	to	target	type
    //   173	181	202	java/io/IOException
    //   67	76	264	android/database/sqlite/SQLiteException
    //   99	108	264	android/database/sqlite/SQLiteException
    //   112	121	264	android/database/sqlite/SQLiteException
    //   125	135	264	android/database/sqlite/SQLiteException
    //   147	156	264	android/database/sqlite/SQLiteException
    //   160	169	264	android/database/sqlite/SQLiteException
    //   173	181	264	android/database/sqlite/SQLiteException
    //   185	199	264	android/database/sqlite/SQLiteException
    //   208	231	264	android/database/sqlite/SQLiteException
    //   235	244	264	android/database/sqlite/SQLiteException
    //   19	59	269	java/lang/Throwable
    //   19	59	276	android/database/sqlite/SQLiteException
    //   67	76	318	java/lang/Throwable
    //   99	108	318	java/lang/Throwable
    //   112	121	318	java/lang/Throwable
    //   125	135	318	java/lang/Throwable
    //   139	143	318	java/lang/Throwable
    //   147	156	318	java/lang/Throwable
    //   160	169	318	java/lang/Throwable
    //   173	181	318	java/lang/Throwable
    //   185	199	318	java/lang/Throwable
    //   208	231	318	java/lang/Throwable
    //   235	244	318	java/lang/Throwable
    //   285	304	318	java/lang/Throwable
  }
  
  public final long zzbp(String paramString)
  {
    Preconditions.checkNotEmpty(paramString);
    return count("select count(1) from events where app_id=? and name not like '!_%' escape '!'", new String[] { paramString }, 0L);
  }
  
  protected final boolean zzgt()
  {
    return false;
  }
  
  /* Error */
  public final String zzid()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 310	com/google/android/android/measurement/internal/StringBuilder:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   4: astore_2
    //   5: aload_2
    //   6: ldc_w 1353
    //   9: aconst_null
    //   10: invokevirtual 362	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   13: astore 4
    //   15: aload 4
    //   17: astore_3
    //   18: aload_3
    //   19: astore_2
    //   20: aload 4
    //   22: invokeinterface 368 1 0
    //   27: istore_1
    //   28: iload_1
    //   29: ifeq +30 -> 59
    //   32: aload_3
    //   33: astore_2
    //   34: aload 4
    //   36: iconst_0
    //   37: invokeinterface 399 2 0
    //   42: astore 5
    //   44: aload 4
    //   46: ifnull +91 -> 137
    //   49: aload 4
    //   51: invokeinterface 375 1 0
    //   56: aload 5
    //   58: areturn
    //   59: aload 4
    //   61: ifnull +79 -> 140
    //   64: aload 4
    //   66: invokeinterface 375 1 0
    //   71: aconst_null
    //   72: areturn
    //   73: astore 4
    //   75: goto +16 -> 91
    //   78: astore_2
    //   79: aconst_null
    //   80: astore_3
    //   81: aload_2
    //   82: astore 4
    //   84: goto +40 -> 124
    //   87: astore 4
    //   89: aconst_null
    //   90: astore_3
    //   91: aload_3
    //   92: astore_2
    //   93: aload_0
    //   94: invokevirtual 232	com/google/android/android/measurement/internal/zzco:zzgo	()Lcom/google/android/android/measurement/internal/zzap;
    //   97: invokevirtual 323	com/google/android/android/measurement/internal/zzap:zzjd	()Lcom/google/android/android/measurement/internal/zzar;
    //   100: ldc_w 1355
    //   103: aload 4
    //   105: invokevirtual 328	com/google/android/android/measurement/internal/zzar:append	(Ljava/lang/String;Ljava/lang/Object;)V
    //   108: aload_3
    //   109: ifnull +31 -> 140
    //   112: aload_3
    //   113: invokeinterface 375 1 0
    //   118: aconst_null
    //   119: areturn
    //   120: astore 4
    //   122: aload_2
    //   123: astore_3
    //   124: aload_3
    //   125: ifnull +9 -> 134
    //   128: aload_3
    //   129: invokeinterface 375 1 0
    //   134: aload 4
    //   136: athrow
    //   137: aload 5
    //   139: areturn
    //   140: aconst_null
    //   141: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	142	0	this	StringBuilder
    //   27	2	1	bool	boolean
    //   4	30	2	localObject1	Object
    //   78	4	2	localThrowable1	Throwable
    //   92	31	2	localObject2	Object
    //   17	112	3	localObject3	Object
    //   13	52	4	localCursor	Cursor
    //   73	1	4	localSQLiteException1	SQLiteException
    //   82	1	4	localThrowable2	Throwable
    //   87	17	4	localSQLiteException2	SQLiteException
    //   120	15	4	localThrowable3	Throwable
    //   42	96	5	str	String
    // Exception table:
    //   from	to	target	type
    //   20	28	73	android/database/sqlite/SQLiteException
    //   34	44	73	android/database/sqlite/SQLiteException
    //   5	15	78	java/lang/Throwable
    //   5	15	87	android/database/sqlite/SQLiteException
    //   20	28	120	java/lang/Throwable
    //   34	44	120	java/lang/Throwable
    //   93	108	120	java/lang/Throwable
  }
  
  public final boolean zzie()
  {
    return count("select count(1) > 0 from queue where has_realtime = 1", null) != 0L;
  }
  
  final void zzif()
  {
    zzaf();
    zzcl();
    if (!zzil()) {
      return;
    }
    long l1 = zzgpzzanh.readLong();
    long l2 = zzbx().elapsedRealtime();
    if (Math.abs(l2 - l1) > ((Long)zzaf.zzakb.getDefaultValue()).longValue())
    {
      zzgpzzanh.getFolder(l2);
      zzaf();
      zzcl();
      if (zzil())
      {
        int i = getWritableDatabase().delete("queue", "abs(bundle_end_timestamp - ?) > cast(? as integer)", new String[] { String.valueOf(zzbx().currentTimeMillis()), String.valueOf(class_3.zzhw()) });
        if (i > 0) {
          zzgo().zzjl().append("Deleted stale rows. rowsDeleted", Integer.valueOf(i));
        }
      }
    }
  }
  
  public final long zzig()
  {
    return count("select max(bundle_end_timestamp) from queue", null, 0L);
  }
  
  public final long zzih()
  {
    return count("select max(timestamp) from raw_events", null, 0L);
  }
  
  public final boolean zzii()
  {
    return count("select count(1) > 0 from raw_events", null) != 0L;
  }
  
  public final boolean zzij()
  {
    return count("select count(1) > 0 from raw_events where realtime = 1", null) != 0L;
  }
  
  public final long zzik()
  {
    Object localObject4 = null;
    Object localObject1 = null;
    long l;
    Object localObject3;
    try
    {
      Object localObject2 = getWritableDatabase().rawQuery("select rowid from raw_events order by rowid desc limit 1;", null);
      try
      {
        boolean bool = ((Cursor)localObject2).moveToFirst();
        if (!bool)
        {
          if (localObject2 == null) {
            break label149;
          }
          ((Cursor)localObject2).close();
          return -1L;
        }
        l = ((Cursor)localObject2).getLong(0);
        if (localObject2 == null) {
          break label153;
        }
        ((Cursor)localObject2).close();
        return l;
      }
      catch (Throwable localThrowable2)
      {
        localObject1 = localObject2;
        localObject2 = localThrowable2;
        break label134;
      }
      catch (SQLiteException localSQLiteException1) {}
      localObject1 = localObject3;
    }
    catch (Throwable localThrowable1) {}catch (SQLiteException localSQLiteException2)
    {
      localObject3 = localObject4;
    }
    zzgo().zzjd().append("Error querying raw events", localSQLiteException2);
    if (localObject3 != null)
    {
      localObject3.close();
      return -1L;
      label134:
      if (localObject1 != null) {
        localObject1.close();
      }
      throw localObject3;
      label149:
      return -1L;
      label153:
      return l;
    }
    return -1L;
  }
}
