package com.google.android.android.measurement.internal;

import android.content.Context;
import android.net.Uri;
import com.google.android.android.common.GoogleApiAvailabilityLight;
import com.google.android.android.internal.measurement.zzsv;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@VisibleForTesting
public final class zzaf
{
  private static volatile zzbt zzadj;
  static MultiMap zzaiq;
  static List<com.google.android.gms.measurement.internal.zzaf.zza<Integer>> zzair = new ArrayList();
  static List<com.google.android.gms.measurement.internal.zzaf.zza<Long>> zzais = new ArrayList();
  static List<com.google.android.gms.measurement.internal.zzaf.zza<Boolean>> zzait = new ArrayList();
  static List<com.google.android.gms.measurement.internal.zzaf.zza<String>> zzaiu = new ArrayList();
  static List<com.google.android.gms.measurement.internal.zzaf.zza<Double>> zzaiv = new ArrayList();
  private static final zzsv zzaiw;
  @VisibleForTesting
  private static Boolean zzaix;
  private static com.google.android.gms.measurement.internal.zzaf.zza<Boolean> zzaiy = zza.getFragment("measurement.log_third_party_store_events_enabled", false, false);
  private static com.google.android.gms.measurement.internal.zzaf.zza<Boolean> zzaiz = zza.getFragment("measurement.log_installs_enabled", false, false);
  private static com.google.android.gms.measurement.internal.zzaf.zza<Boolean> zzaja = zza.getFragment("measurement.log_upgrades_enabled", false, false);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Boolean> zzajb = zza.getFragment("measurement.log_androidId_enabled", false, false);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Boolean> zzajc = zza.getFragment("measurement.upload_dsid_enabled", false, false);
  public static com.google.android.gms.measurement.internal.zzaf.zza<String> zzajd = zza.register("measurement.log_tag", "FA", "FA-SVC");
  public static com.google.android.gms.measurement.internal.zzaf.zza<Long> zzaje = zza.start("measurement.ad_id_cache_time", 10000L, 10000L);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Long> zzajf = zza.start("measurement.monitoring.sample_period_millis", 86400000L, 86400000L);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Long> zzajg = zza.start("measurement.config.cache_time", 86400000L, 3600000L);
  public static com.google.android.gms.measurement.internal.zzaf.zza<String> zzajh = zza.register("measurement.config.url_scheme", "https", "https");
  public static com.google.android.gms.measurement.internal.zzaf.zza<String> zzaji = zza.register("measurement.config.url_authority", "app-measurement.com", "app-measurement.com");
  public static com.google.android.gms.measurement.internal.zzaf.zza<Integer> zzajj = zza.multiply("measurement.upload.max_bundles", 100, 100);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Integer> zzajk = zza.multiply("measurement.upload.max_batch_size", 65536, 65536);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Integer> zzajl = zza.multiply("measurement.upload.max_bundle_size", 65536, 65536);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Integer> zzajm = zza.multiply("measurement.upload.max_events_per_bundle", 1000, 1000);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Integer> zzajn = zza.multiply("measurement.upload.max_events_per_day", 100000, 100000);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Integer> zzajo = zza.multiply("measurement.upload.max_error_events_per_day", 1000, 1000);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Integer> zzajp = zza.multiply("measurement.upload.max_public_events_per_day", 50000, 50000);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Integer> zzajq = zza.multiply("measurement.upload.max_conversions_per_day", 500, 500);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Integer> zzajr = zza.multiply("measurement.upload.max_realtime_events_per_day", 10, 10);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Integer> zzajs = zza.multiply("measurement.store.max_stored_events_per_app", 100000, 100000);
  public static com.google.android.gms.measurement.internal.zzaf.zza<String> zzajt = zza.register("measurement.upload.url", "https://app-measurement.com/a", "https://app-measurement.com/a");
  public static com.google.android.gms.measurement.internal.zzaf.zza<Long> zzaju = zza.start("measurement.upload.backoff_period", 43200000L, 43200000L);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Long> zzajv = zza.start("measurement.upload.window_interval", 3600000L, 3600000L);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Long> zzajw = zza.start("measurement.upload.interval", 3600000L, 3600000L);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Long> zzajx = zza.start("measurement.upload.realtime_upload_interval", 10000L, 10000L);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Long> zzajy = zza.start("measurement.upload.debug_upload_interval", 1000L, 1000L);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Long> zzajz = zza.start("measurement.upload.minimum_delay", 500L, 500L);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Long> zzaka = zza.start("measurement.alarm_manager.minimum_interval", 60000L, 60000L);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Long> zzakb = zza.start("measurement.upload.stale_data_deletion_interval", 86400000L, 86400000L);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Long> zzakc = zza.start("measurement.upload.refresh_blacklisted_config_interval", 604800000L, 604800000L);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Long> zzakd = zza.start("measurement.upload.initial_upload_delay_time", 15000L, 15000L);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Long> zzake = zza.start("measurement.upload.retry_time", 1800000L, 1800000L);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Integer> zzakf = zza.multiply("measurement.upload.retry_count", 6, 6);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Long> zzakg = zza.start("measurement.upload.max_queue_time", 2419200000L, 2419200000L);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Integer> zzakh = zza.multiply("measurement.lifetimevalue.max_currency_tracked", 4, 4);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Integer> zzaki = zza.multiply("measurement.audience.filter_result_max_count", 200, 200);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Long> zzakj = zza.start("measurement.service_client.idle_disconnect_millis", 5000L, 5000L);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Boolean> zzakk = zza.getFragment("measurement.test.boolean_flag", false, false);
  public static com.google.android.gms.measurement.internal.zzaf.zza<String> zzakl = zza.register("measurement.test.string_flag", "---", "---");
  public static com.google.android.gms.measurement.internal.zzaf.zza<Long> zzakm = zza.start("measurement.test.long_flag", -1L, -1L);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Integer> zzakn = zza.multiply("measurement.test.int_flag", -2, -2);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Double> zzako = zza.query("measurement.test.double_flag", -3.0D, -3.0D);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Boolean> zzakp = zza.getFragment("measurement.lifetimevalue.user_engagement_tracking_enabled", false, false);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Boolean> zzakq = zza.getFragment("measurement.audience.complex_param_evaluation", false, false);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Boolean> zzakr = zza.getFragment("measurement.validation.internal_limits_internal_event_params", false, false);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Boolean> zzaks = zza.getFragment("measurement.quality.unsuccessful_update_retry_counter", false, false);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Boolean> zzakt = zza.getFragment("measurement.iid.disable_on_collection_disabled", true, true);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Boolean> zzaku = zza.getFragment("measurement.app_launch.call_only_when_enabled", true, true);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Boolean> zzakv = zza.getFragment("measurement.run_on_worker_inline", true, false);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Boolean> zzakw = zza.getFragment("measurement.audience.dynamic_filters", false, false);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Boolean> zzakx = zza.getFragment("measurement.reset_analytics.persist_time", false, false);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Boolean> zzaky = zza.getFragment("measurement.validation.value_and_currency_params", false, false);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Boolean> zzakz = zza.getFragment("measurement.sampling.time_zone_offset_enabled", false, false);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Boolean> zzala = zza.getFragment("measurement.referrer.enable_logging_install_referrer_cmp_from_apk", false, false);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Boolean> zzalb = zza.getFragment("measurement.disconnect_from_remote_service", false, false);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Boolean> zzalc = zza.getFragment("measurement.clear_local_database", false, false);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Boolean> zzald = zza.getFragment("measurement.fetch_config_with_admob_app_id", false, false);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Boolean> zzale = zza.getFragment("measurement.sessions.session_id_enabled", false, false);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Boolean> zzalf = zza.getFragment("measurement.sessions.immediate_start_enabled", false, false);
  private static com.google.android.gms.measurement.internal.zzaf.zza<Boolean> zzalg = zza.getFragment("measurement.sessions.background_sessions_enabled", false, false);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Boolean> zzalh = zza.getFragment("measurement.collection.firebase_global_collection_flag_enabled", true, true);
  private static com.google.android.gms.measurement.internal.zzaf.zza<Boolean> zzali = zza.getFragment("measurement.collection.efficient_engagement_reporting_enabled", false, false);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Boolean> zzalj = zza.getFragment("measurement.personalized_ads_feature_enabled", false, false);
  public static com.google.android.gms.measurement.internal.zzaf.zza<Boolean> zzalk = zza.getFragment("measurement.remove_app_instance_id_cache_enabled", true, true);
  
  static
  {
    String str = String.valueOf(Uri.encode("com.google.android.gms.measurement"));
    if (str.length() != 0) {
      str = "content://com.google.android.gms.phenotype/".concat(str);
    } else {
      str = new String("content://com.google.android.gms.phenotype/");
    }
    zzaiw = new zzsv(Uri.parse(str));
  }
  
  static void getDefaultValue(Exception paramException)
  {
    if (zzadj == null) {
      return;
    }
    Context localContext = zzadj.getContext();
    if (zzaix == null)
    {
      boolean bool;
      if (GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(localContext, 12451000) == 0) {
        bool = true;
      } else {
        bool = false;
      }
      zzaix = Boolean.valueOf(bool);
    }
    if (zzaix.booleanValue()) {
      zzadj.zzgo().zzjd().append("Got Exception on PhenotypeFlag.get on Play device", paramException);
    }
  }
  
  static void parseChildren(zzbt paramZzbt)
  {
    zzadj = paramZzbt;
  }
  
  static void setHeaders(MultiMap paramMultiMap)
  {
    zzaiq = paramMultiMap;
    zza.zziy();
  }
  
  @VisibleForTesting
  public final class zza<V>
  {
    private final V zzaan;
    private com.google.android.gms.internal.measurement.zzsl<V> zzall;
    private final V zzalm;
    private volatile V zzaln;
    
    private zza(Object paramObject1, Object paramObject2)
    {
      zzaan = paramObject1;
      zzalm = paramObject2;
    }
    
    static zza getFragment(String paramString, boolean paramBoolean1, boolean paramBoolean2)
    {
      paramString = new zza(paramString, Boolean.valueOf(paramBoolean1), Boolean.valueOf(paramBoolean2));
      zzaf.zzait.add(paramString);
      return paramString;
    }
    
    static zza multiply(String paramString, int paramInt1, int paramInt2)
    {
      paramString = new zza(paramString, Integer.valueOf(paramInt1), Integer.valueOf(paramInt2));
      zzaf.zzair.add(paramString);
      return paramString;
    }
    
    private static void putAll()
    {
      try
      {
        Iterator localIterator = zzaf.zzait.iterator();
        zza localZza;
        zzsv localZzsv;
        String str;
        MultiMap localMultiMap;
        while (localIterator.hasNext())
        {
          localZza = (zza)localIterator.next();
          localZzsv = zzaf.zziw();
          str = zzoj;
          localMultiMap = zzaf.zzaiq;
          zzall = localZzsv.value(str, ((Boolean)zzaan).booleanValue());
        }
        localIterator = zzaf.zzaiu.iterator();
        while (localIterator.hasNext())
        {
          localZza = (zza)localIterator.next();
          localZzsv = zzaf.zziw();
          str = zzoj;
          localMultiMap = zzaf.zzaiq;
          zzall = localZzsv.insertOrThrow(str, (String)zzaan);
        }
        localIterator = zzaf.zzais.iterator();
        while (localIterator.hasNext())
        {
          localZza = (zza)localIterator.next();
          localZzsv = zzaf.zziw();
          str = zzoj;
          localMultiMap = zzaf.zzaiq;
          zzall = localZzsv.set(str, ((Long)zzaan).longValue());
        }
        localIterator = zzaf.zzair.iterator();
        while (localIterator.hasNext())
        {
          localZza = (zza)localIterator.next();
          localZzsv = zzaf.zziw();
          str = zzoj;
          localMultiMap = zzaf.zzaiq;
          zzall = localZzsv.cast(str, ((Integer)zzaan).intValue());
        }
        localIterator = zzaf.zzaiv.iterator();
        while (localIterator.hasNext())
        {
          localZza = (zza)localIterator.next();
          localZzsv = zzaf.zziw();
          str = zzoj;
          localMultiMap = zzaf.zzaiq;
          zzall = localZzsv.apply(str, ((Double)zzaan).doubleValue());
        }
        return;
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
    
    static zza query(String paramString, double paramDouble1, double paramDouble2)
    {
      paramString = new zza(paramString, Double.valueOf(-3.0D), Double.valueOf(-3.0D));
      zzaf.zzaiv.add(paramString);
      return paramString;
    }
    
    static zza register(String paramString1, String paramString2, String paramString3)
    {
      paramString1 = new zza(paramString1, paramString2, paramString3);
      zzaf.zzaiu.add(paramString1);
      return paramString1;
    }
    
    static zza start(String paramString, long paramLong1, long paramLong2)
    {
      paramString = new zza(paramString, Long.valueOf(paramLong1), Long.valueOf(paramLong2));
      zzaf.zzais.add(paramString);
      return paramString;
    }
    
    /* Error */
    private static void zzix()
    {
      // Byte code:
      //   0: ldc 65
      //   2: monitorenter
      //   3: invokestatic 163	com/google/android/android/measurement/internal/MultiMap:isMainThread	()Z
      //   6: ifne +276 -> 282
      //   9: getstatic 87	com/google/android/android/measurement/internal/zzaf:zzaiq	Lcom/google/android/android/measurement/internal/MultiMap;
      //   12: astore_1
      //   13: getstatic 44	com/google/android/android/measurement/internal/zzaf:zzait	Ljava/util/List;
      //   16: astore_1
      //   17: aload_1
      //   18: invokeinterface 69 1 0
      //   23: astore_1
      //   24: aload_1
      //   25: invokeinterface 75 1 0
      //   30: istore_0
      //   31: iload_0
      //   32: ifeq +33 -> 65
      //   35: aload_1
      //   36: invokeinterface 79 1 0
      //   41: astore_2
      //   42: aload_2
      //   43: checkcast 2	com/google/android/android/measurement/internal/zzaf$zza
      //   46: astore_2
      //   47: aload_2
      //   48: getfield 98	com/google/android/android/measurement/internal/zzaf$zza:zzall	Lcom/google/android/android/internal/measurement/zzsl;
      //   51: astore_3
      //   52: aload_3
      //   53: invokevirtual 168	com/google/android/android/internal/measurement/zzsl:unwrap	()Ljava/lang/Object;
      //   56: astore_3
      //   57: aload_2
      //   58: aload_3
      //   59: putfield 170	com/google/android/android/measurement/internal/zzaf$zza:zzaln	Ljava/lang/Object;
      //   62: goto -38 -> 24
      //   65: getstatic 101	com/google/android/android/measurement/internal/zzaf:zzaiu	Ljava/util/List;
      //   68: astore_1
      //   69: aload_1
      //   70: invokeinterface 69 1 0
      //   75: astore_1
      //   76: aload_1
      //   77: invokeinterface 75 1 0
      //   82: istore_0
      //   83: iload_0
      //   84: ifeq +33 -> 117
      //   87: aload_1
      //   88: invokeinterface 79 1 0
      //   93: astore_2
      //   94: aload_2
      //   95: checkcast 2	com/google/android/android/measurement/internal/zzaf$zza
      //   98: astore_2
      //   99: aload_2
      //   100: getfield 98	com/google/android/android/measurement/internal/zzaf$zza:zzall	Lcom/google/android/android/internal/measurement/zzsl;
      //   103: astore_3
      //   104: aload_3
      //   105: invokevirtual 168	com/google/android/android/internal/measurement/zzsl:unwrap	()Ljava/lang/Object;
      //   108: astore_3
      //   109: aload_2
      //   110: aload_3
      //   111: putfield 170	com/google/android/android/measurement/internal/zzaf$zza:zzaln	Ljava/lang/Object;
      //   114: goto -38 -> 76
      //   117: getstatic 110	com/google/android/android/measurement/internal/zzaf:zzais	Ljava/util/List;
      //   120: astore_1
      //   121: aload_1
      //   122: invokeinterface 69 1 0
      //   127: astore_1
      //   128: aload_1
      //   129: invokeinterface 75 1 0
      //   134: istore_0
      //   135: iload_0
      //   136: ifeq +33 -> 169
      //   139: aload_1
      //   140: invokeinterface 79 1 0
      //   145: astore_2
      //   146: aload_2
      //   147: checkcast 2	com/google/android/android/measurement/internal/zzaf$zza
      //   150: astore_2
      //   151: aload_2
      //   152: getfield 98	com/google/android/android/measurement/internal/zzaf$zza:zzall	Lcom/google/android/android/internal/measurement/zzsl;
      //   155: astore_3
      //   156: aload_3
      //   157: invokevirtual 168	com/google/android/android/internal/measurement/zzsl:unwrap	()Ljava/lang/Object;
      //   160: astore_3
      //   161: aload_2
      //   162: aload_3
      //   163: putfield 170	com/google/android/android/measurement/internal/zzaf$zza:zzaln	Ljava/lang/Object;
      //   166: goto -38 -> 128
      //   169: getstatic 60	com/google/android/android/measurement/internal/zzaf:zzair	Ljava/util/List;
      //   172: astore_1
      //   173: aload_1
      //   174: invokeinterface 69 1 0
      //   179: astore_1
      //   180: aload_1
      //   181: invokeinterface 75 1 0
      //   186: istore_0
      //   187: iload_0
      //   188: ifeq +33 -> 221
      //   191: aload_1
      //   192: invokeinterface 79 1 0
      //   197: astore_2
      //   198: aload_2
      //   199: checkcast 2	com/google/android/android/measurement/internal/zzaf$zza
      //   202: astore_2
      //   203: aload_2
      //   204: getfield 98	com/google/android/android/measurement/internal/zzaf$zza:zzall	Lcom/google/android/android/internal/measurement/zzsl;
      //   207: astore_3
      //   208: aload_3
      //   209: invokevirtual 168	com/google/android/android/internal/measurement/zzsl:unwrap	()Ljava/lang/Object;
      //   212: astore_3
      //   213: aload_2
      //   214: aload_3
      //   215: putfield 170	com/google/android/android/measurement/internal/zzaf$zza:zzaln	Ljava/lang/Object;
      //   218: goto -38 -> 180
      //   221: getstatic 131	com/google/android/android/measurement/internal/zzaf:zzaiv	Ljava/util/List;
      //   224: astore_1
      //   225: aload_1
      //   226: invokeinterface 69 1 0
      //   231: astore_1
      //   232: aload_1
      //   233: invokeinterface 75 1 0
      //   238: istore_0
      //   239: iload_0
      //   240: ifeq +38 -> 278
      //   243: aload_1
      //   244: invokeinterface 79 1 0
      //   249: astore_2
      //   250: aload_2
      //   251: checkcast 2	com/google/android/android/measurement/internal/zzaf$zza
      //   254: astore_2
      //   255: aload_2
      //   256: getfield 98	com/google/android/android/measurement/internal/zzaf$zza:zzall	Lcom/google/android/android/internal/measurement/zzsl;
      //   259: astore_3
      //   260: aload_3
      //   261: invokevirtual 168	com/google/android/android/internal/measurement/zzsl:unwrap	()Ljava/lang/Object;
      //   264: astore_3
      //   265: aload_2
      //   266: aload_3
      //   267: putfield 170	com/google/android/android/measurement/internal/zzaf$zza:zzaln	Ljava/lang/Object;
      //   270: goto -38 -> 232
      //   273: astore_1
      //   274: aload_1
      //   275: invokestatic 174	com/google/android/android/measurement/internal/zzaf:getDefaultValue	(Ljava/lang/Exception;)V
      //   278: ldc 65
      //   280: monitorexit
      //   281: return
      //   282: new 176	java/lang/IllegalStateException
      //   285: dup
      //   286: ldc -78
      //   288: invokespecial 181	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
      //   291: athrow
      //   292: astore_1
      //   293: ldc 65
      //   295: monitorexit
      //   296: aload_1
      //   297: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   30	210	0	bool	boolean
      //   12	232	1	localObject1	Object
      //   273	2	1	localSecurityException	SecurityException
      //   292	5	1	localThrowable	Throwable
      //   41	225	2	localObject2	Object
      //   51	216	3	localObject3	Object
      // Exception table:
      //   from	to	target	type
      //   17	24	273	java/lang/SecurityException
      //   24	31	273	java/lang/SecurityException
      //   35	42	273	java/lang/SecurityException
      //   52	57	273	java/lang/SecurityException
      //   69	76	273	java/lang/SecurityException
      //   76	83	273	java/lang/SecurityException
      //   87	94	273	java/lang/SecurityException
      //   104	109	273	java/lang/SecurityException
      //   121	128	273	java/lang/SecurityException
      //   128	135	273	java/lang/SecurityException
      //   139	146	273	java/lang/SecurityException
      //   156	161	273	java/lang/SecurityException
      //   173	180	273	java/lang/SecurityException
      //   180	187	273	java/lang/SecurityException
      //   191	198	273	java/lang/SecurityException
      //   208	213	273	java/lang/SecurityException
      //   225	232	273	java/lang/SecurityException
      //   232	239	273	java/lang/SecurityException
      //   243	250	273	java/lang/SecurityException
      //   260	265	273	java/lang/SecurityException
      //   3	17	292	java/lang/Throwable
      //   17	24	292	java/lang/Throwable
      //   24	31	292	java/lang/Throwable
      //   35	42	292	java/lang/Throwable
      //   42	52	292	java/lang/Throwable
      //   52	57	292	java/lang/Throwable
      //   57	62	292	java/lang/Throwable
      //   65	69	292	java/lang/Throwable
      //   69	76	292	java/lang/Throwable
      //   76	83	292	java/lang/Throwable
      //   87	94	292	java/lang/Throwable
      //   94	104	292	java/lang/Throwable
      //   104	109	292	java/lang/Throwable
      //   109	114	292	java/lang/Throwable
      //   117	121	292	java/lang/Throwable
      //   121	128	292	java/lang/Throwable
      //   128	135	292	java/lang/Throwable
      //   139	146	292	java/lang/Throwable
      //   146	156	292	java/lang/Throwable
      //   156	161	292	java/lang/Throwable
      //   161	166	292	java/lang/Throwable
      //   169	173	292	java/lang/Throwable
      //   173	180	292	java/lang/Throwable
      //   180	187	292	java/lang/Throwable
      //   191	198	292	java/lang/Throwable
      //   198	208	292	java/lang/Throwable
      //   208	213	292	java/lang/Throwable
      //   213	218	292	java/lang/Throwable
      //   221	225	292	java/lang/Throwable
      //   225	232	292	java/lang/Throwable
      //   232	239	292	java/lang/Throwable
      //   243	250	292	java/lang/Throwable
      //   250	260	292	java/lang/Throwable
      //   260	265	292	java/lang/Throwable
      //   265	270	292	java/lang/Throwable
      //   274	278	292	java/lang/Throwable
      //   278	281	292	java/lang/Throwable
      //   282	292	292	java/lang/Throwable
      //   293	296	292	java/lang/Throwable
    }
    
    public final Object get(Object paramObject)
    {
      if (paramObject != null) {
        return paramObject;
      }
      if (zzaf.zzaiq == null) {
        return zzaan;
      }
      paramObject = zzaf.zzaiq;
      if (MultiMap.isMainThread())
      {
        if (zzaln == null) {
          return zzaan;
        }
        return zzaln;
      }
      zzix();
      paramObject = zzall;
      try
      {
        paramObject = paramObject.unwrap();
        return paramObject;
      }
      catch (SecurityException paramObject)
      {
        zzaf.getDefaultValue(paramObject);
      }
      return zzall.getDefaultValue();
    }
    
    public final Object getDefaultValue()
    {
      if (zzaf.zzaiq == null) {
        return zzaan;
      }
      Object localObject = zzaf.zzaiq;
      if (MultiMap.isMainThread())
      {
        if (zzaln == null) {
          return zzaan;
        }
        return zzaln;
      }
      zzix();
      localObject = zzall;
      try
      {
        localObject = ((com.google.android.android.internal.measurement.zzsl)localObject).unwrap();
        return localObject;
      }
      catch (SecurityException localSecurityException)
      {
        zzaf.getDefaultValue(localSecurityException);
      }
      return zzall.getDefaultValue();
    }
    
    public final String getKey()
    {
      return zzaf.this;
    }
  }
}
