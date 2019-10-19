package com.google.android.android.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.android.common.GoogleApiAvailabilityLight;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.common.util.Clock;
import com.google.android.android.common.util.CollectionUtils;
import com.google.android.android.common.wrappers.PackageManagerWrapper;
import com.google.android.android.common.wrappers.Wrappers;
import com.google.android.android.measurement.AppMeasurement.Event;
import com.google.android.android.measurement.AppMeasurement.UserProperty;
import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import javax.security.auth.x500.X500Principal;

public final class zzfk
  extends zzcp
{
  private static final String[] zzaui = { "firebase_", "google_", "ga_" };
  private int zzaed;
  private SecureRandom zzauj;
  private final AtomicLong zzauk = new AtomicLong(0L);
  private Integer zzaul = null;
  
  zzfk(zzbt paramZzbt)
  {
    super(paramZzbt);
  }
  
  private static boolean addEvent(Bundle paramBundle, int paramInt)
  {
    if (paramBundle.getLong("_err") == 0L)
    {
      paramBundle.putLong("_err", paramInt);
      return true;
    }
    return false;
  }
  
  private final boolean canonicalize(String paramString1, String paramString2)
  {
    if (paramString2 == null)
    {
      zzgo().zzjd().append("Name is required and can't be null. Type", paramString1);
      return false;
    }
    if (paramString2.length() == 0)
    {
      zzgo().zzjd().append("Name is required and can't be empty. Type", paramString1);
      return false;
    }
    int i = paramString2.codePointAt(0);
    if ((!Character.isLetter(i)) && (i != 95))
    {
      zzgo().zzjd().append("Name must start with a letter or _ (underscore). Type, name", paramString1, paramString2);
      return false;
    }
    int j = paramString2.length();
    i = Character.charCount(i);
    while (i < j)
    {
      int k = paramString2.codePointAt(i);
      if ((k != 95) && (!Character.isLetterOrDigit(k)))
      {
        zzgo().zzjd().append("Name must consist of letters, digits or _ (underscores). Type, name", paramString1, paramString2);
        return false;
      }
      i += Character.charCount(k);
    }
    return true;
  }
  
  public static long contains(long paramLong1, long paramLong2)
  {
    return (paramLong1 + paramLong2 * 60000L) / 86400000L;
  }
  
  private static boolean enable(Context paramContext, String paramString)
  {
    try
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      if (localPackageManager == null) {
        return false;
      }
      paramContext = localPackageManager.getServiceInfo(new ComponentName(paramContext, paramString), 0);
      if (paramContext != null)
      {
        if (enabled) {
          return true;
        }
      }
      else {
        return false;
      }
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  static long encode(byte[] paramArrayOfByte)
  {
    Preconditions.checkNotNull(paramArrayOfByte);
    int i = paramArrayOfByte.length;
    int j = 0;
    boolean bool;
    if (i > 0) {
      bool = true;
    } else {
      bool = false;
    }
    Preconditions.checkState(bool);
    long l = 0L;
    i = paramArrayOfByte.length - 1;
    while ((i >= 0) && (i >= paramArrayOfByte.length - 8))
    {
      l += ((paramArrayOfByte[i] & 0xFF) << j);
      j += 8;
      i -= 1;
    }
    return l;
  }
  
  public static String get(String paramString, int paramInt, boolean paramBoolean)
  {
    if (paramString.codePointCount(0, paramString.length()) > paramInt)
    {
      if (paramBoolean) {
        return String.valueOf(paramString.substring(0, paramString.offsetByCodePoints(0, paramInt))).concat("...");
      }
      return null;
    }
    return paramString;
  }
  
  static Bundle[] get(Object paramObject)
  {
    if ((paramObject instanceof Bundle)) {
      return new Bundle[] { (Bundle)paramObject };
    }
    if ((paramObject instanceof Parcelable[]))
    {
      paramObject = (Parcelable[])paramObject;
      return (Bundle[])Arrays.copyOf(paramObject, paramObject.length, [Landroid.os.Bundle.class);
    }
    if ((paramObject instanceof ArrayList))
    {
      paramObject = (ArrayList)paramObject;
      return (Bundle[])paramObject.toArray(new Bundle[paramObject.size()]);
    }
    return null;
  }
  
  static MessageDigest getMessageDigest()
  {
    int i = 0;
    while (i < 2)
    {
      try
      {
        MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
        if (localMessageDigest != null) {
          return localMessageDigest;
        }
      }
      catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
      {
        for (;;) {}
      }
      i += 1;
    }
    return null;
  }
  
  private final boolean isDebuggable(Context paramContext, String paramString)
  {
    X500Principal localX500Principal = new X500Principal("CN=Android Debug,O=Android,C=US");
    try
    {
      paramContext = Wrappers.packageManager(paramContext).getPackageInfo(paramString, 64);
      if ((paramContext != null) && (signatures != null) && (signatures.length > 0))
      {
        paramContext = signatures[0];
        paramString = CertificateFactory.getInstance("X.509");
        paramContext = paramString.generateCertificate(new ByteArrayInputStream(paramContext.toByteArray()));
        paramContext = (X509Certificate)paramContext;
        boolean bool = paramContext.getSubjectX500Principal().equals(localX500Principal);
        return bool;
      }
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      zzgo().zzjd().append("Package name not found", paramContext);
    }
    catch (CertificateException paramContext)
    {
      zzgo().zzjd().append("Error obtaining certificate", paramContext);
    }
    return true;
  }
  
  static boolean loadData(Intent paramIntent)
  {
    paramIntent = paramIntent.getStringExtra("android.intent.extra.REFERRER_NAME");
    return ("android-app://com.google.android.googlequicksearchbox/https/www.google.com".equals(paramIntent)) || ("https://www.google.com".equals(paramIntent)) || ("android-app://com.google.appcrawler".equals(paramIntent));
  }
  
  static byte[] marshall(Parcelable paramParcelable)
  {
    if (paramParcelable == null) {
      return null;
    }
    Parcel localParcel = Parcel.obtain();
    try
    {
      paramParcelable.writeToParcel(localParcel, 0);
      paramParcelable = localParcel.marshall();
      localParcel.recycle();
      return paramParcelable;
    }
    catch (Throwable paramParcelable)
    {
      localParcel.recycle();
      throw paramParcelable;
    }
  }
  
  static boolean next(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    boolean bool1 = TextUtils.isEmpty(paramString1);
    boolean bool2 = TextUtils.isEmpty(paramString2);
    if ((!bool1) && (!bool2)) {
      return !paramString1.equals(paramString2);
    }
    if ((bool1) && (bool2))
    {
      if ((!TextUtils.isEmpty(paramString3)) && (!TextUtils.isEmpty(paramString4))) {
        return !paramString3.equals(paramString4);
      }
      return !TextUtils.isEmpty(paramString4);
    }
    if ((!bool1) && (bool2))
    {
      if (TextUtils.isEmpty(paramString4)) {
        return false;
      }
      if (!TextUtils.isEmpty(paramString3)) {
        return !paramString3.equals(paramString4);
      }
      return true;
    }
    if (!TextUtils.isEmpty(paramString3)) {
      return !paramString3.equals(paramString4);
    }
    return true;
  }
  
  /* Error */
  public static Object put(Object paramObject)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +5 -> 6
    //   4: aconst_null
    //   5: areturn
    //   6: new 323	java/io/ByteArrayOutputStream
    //   9: dup
    //   10: invokespecial 325	java/io/ByteArrayOutputStream:<init>	()V
    //   13: astore_1
    //   14: new 327	java/io/ObjectOutputStream
    //   17: dup
    //   18: aload_1
    //   19: invokespecial 330	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   22: astore_2
    //   23: aload_2
    //   24: aload_0
    //   25: invokevirtual 334	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
    //   28: aload_2
    //   29: invokevirtual 337	java/io/ObjectOutputStream:flush	()V
    //   32: new 339	java/io/ObjectInputStream
    //   35: dup
    //   36: new 244	java/io/ByteArrayInputStream
    //   39: dup
    //   40: aload_1
    //   41: invokevirtual 340	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   44: invokespecial 253	java/io/ByteArrayInputStream:<init>	([B)V
    //   47: invokespecial 343	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   50: astore_0
    //   51: aload_0
    //   52: invokevirtual 347	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   55: astore_1
    //   56: aload_2
    //   57: invokevirtual 350	java/io/ObjectOutputStream:close	()V
    //   60: aload_0
    //   61: invokevirtual 351	java/io/ObjectInputStream:close	()V
    //   64: aload_1
    //   65: areturn
    //   66: astore_1
    //   67: goto +14 -> 81
    //   70: astore_1
    //   71: aconst_null
    //   72: astore_0
    //   73: goto +8 -> 81
    //   76: astore_1
    //   77: aconst_null
    //   78: astore_0
    //   79: aconst_null
    //   80: astore_2
    //   81: aload_2
    //   82: ifnull +7 -> 89
    //   85: aload_2
    //   86: invokevirtual 350	java/io/ObjectOutputStream:close	()V
    //   89: aload_0
    //   90: ifnull +7 -> 97
    //   93: aload_0
    //   94: invokevirtual 351	java/io/ObjectInputStream:close	()V
    //   97: aload_1
    //   98: athrow
    //   99: astore_0
    //   100: aconst_null
    //   101: areturn
    //   102: astore_0
    //   103: aconst_null
    //   104: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	105	0	paramObject	Object
    //   13	52	1	localObject	Object
    //   66	1	1	localThrowable1	Throwable
    //   70	1	1	localThrowable2	Throwable
    //   76	22	1	localThrowable3	Throwable
    //   22	64	2	localObjectOutputStream	java.io.ObjectOutputStream
    // Exception table:
    //   from	to	target	type
    //   51	56	66	java/lang/Throwable
    //   23	51	70	java/lang/Throwable
    //   6	23	76	java/lang/Throwable
    //   56	64	99	java/io/IOException
    //   85	89	99	java/io/IOException
    //   93	97	99	java/io/IOException
    //   97	99	99	java/io/IOException
    //   56	64	102	java/lang/ClassNotFoundException
    //   85	89	102	java/lang/ClassNotFoundException
    //   93	97	102	java/lang/ClassNotFoundException
    //   97	99	102	java/lang/ClassNotFoundException
  }
  
  private final boolean put(String paramString1, String paramString2, int paramInt, Object paramObject, boolean paramBoolean)
  {
    if (paramObject == null) {
      return true;
    }
    if ((!(paramObject instanceof Long)) && (!(paramObject instanceof Float)) && (!(paramObject instanceof Integer)) && (!(paramObject instanceof Byte)) && (!(paramObject instanceof Short)) && (!(paramObject instanceof Boolean)))
    {
      if ((paramObject instanceof Double)) {
        return true;
      }
      if ((!(paramObject instanceof String)) && (!(paramObject instanceof Character)) && (!(paramObject instanceof CharSequence)))
      {
        if (((paramObject instanceof Bundle)) && (paramBoolean)) {
          return true;
        }
        int i;
        if (((paramObject instanceof Parcelable[])) && (paramBoolean))
        {
          paramString1 = (Parcelable[])paramObject;
          i = paramString1.length;
          paramInt = 0;
          while (paramInt < i)
          {
            paramObject = paramString1[paramInt];
            if (!(paramObject instanceof Bundle))
            {
              zzgo().zzjg().append("All Parcelable[] elements must be of type Bundle. Value type, name", paramObject.getClass(), paramString2);
              return false;
            }
            paramInt += 1;
          }
          return true;
        }
        if ((paramObject instanceof ArrayList))
        {
          if (!paramBoolean) {
            break label307;
          }
          paramString1 = (ArrayList)paramObject;
          i = paramString1.size();
          paramInt = 0;
          while (paramInt < i)
          {
            paramObject = paramString1.get(paramInt);
            paramInt += 1;
            if (!(paramObject instanceof Bundle))
            {
              zzgo().zzjg().append("All ArrayList elements must be of type Bundle. Value type, name", paramObject.getClass(), paramString2);
              return false;
            }
          }
          return true;
        }
        return false;
      }
      paramObject = String.valueOf(paramObject);
      if (paramObject.codePointCount(0, paramObject.length()) > paramInt)
      {
        zzgo().zzjg().append("Value is too long; discarded. Value kind, name, value length", paramString1, paramString2, Integer.valueOf(paramObject.length()));
        return false;
      }
    }
    return true;
    label307:
    return false;
  }
  
  private static Object read(int paramInt, Object paramObject, boolean paramBoolean)
  {
    if (paramObject == null) {
      return null;
    }
    if (!(paramObject instanceof Long))
    {
      if ((paramObject instanceof Double)) {
        return paramObject;
      }
      if ((paramObject instanceof Integer)) {
        return Long.valueOf(((Integer)paramObject).intValue());
      }
      if ((paramObject instanceof Byte)) {
        return Long.valueOf(((Byte)paramObject).byteValue());
      }
      if ((paramObject instanceof Short)) {
        return Long.valueOf(((Short)paramObject).shortValue());
      }
      if ((paramObject instanceof Boolean))
      {
        long l;
        if (((Boolean)paramObject).booleanValue()) {
          l = 1L;
        } else {
          l = 0L;
        }
        return Long.valueOf(l);
      }
      if ((paramObject instanceof Float)) {
        return Double.valueOf(((Float)paramObject).doubleValue());
      }
      if ((!(paramObject instanceof String)) && (!(paramObject instanceof Character)) && (!(paramObject instanceof CharSequence))) {
        return null;
      }
      return get(String.valueOf(paramObject), paramInt, paramBoolean);
    }
    return paramObject;
  }
  
  private static void sendResponse(Bundle paramBundle, Object paramObject)
  {
    Preconditions.checkNotNull(paramBundle);
    if ((paramObject != null) && (((paramObject instanceof String)) || ((paramObject instanceof CharSequence)))) {
      paramBundle.putLong("_el", String.valueOf(paramObject).length());
    }
  }
  
  public static Bundle set(Bundle paramBundle)
  {
    if (paramBundle == null) {
      return new Bundle();
    }
    paramBundle = new Bundle(paramBundle);
    Iterator localIterator = paramBundle.keySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject1 = (String)localIterator.next();
      Object localObject2 = paramBundle.get((String)localObject1);
      if ((localObject2 instanceof Bundle))
      {
        paramBundle.putBundle((String)localObject1, new Bundle((Bundle)localObject2));
      }
      else
      {
        boolean bool = localObject2 instanceof Parcelable[];
        int j = 0;
        int i = 0;
        if (bool)
        {
          localObject1 = (Parcelable[])localObject2;
          while (i < localObject1.length)
          {
            if ((localObject1[i] instanceof Bundle)) {
              localObject1[i] = new Bundle((Bundle)localObject1[i]);
            }
            i += 1;
          }
        }
        else if ((localObject2 instanceof List))
        {
          localObject1 = (List)localObject2;
          i = j;
          while (i < ((List)localObject1).size())
          {
            localObject2 = ((List)localObject1).get(i);
            if ((localObject2 instanceof Bundle)) {
              ((List)localObject1).set(i, new Bundle((Bundle)localObject2));
            }
            i += 1;
          }
        }
      }
    }
    return paramBundle;
  }
  
  static boolean set(Context paramContext, boolean paramBoolean)
  {
    Preconditions.checkNotNull(paramContext);
    if (Build.VERSION.SDK_INT >= 24) {
      return enable(paramContext, "com.google.android.gms.measurement.AppMeasurementJobService");
    }
    return enable(paramContext, "com.google.android.gms.measurement.AppMeasurementService");
  }
  
  public static String verify(String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2)
  {
    Preconditions.checkNotNull(paramArrayOfString1);
    Preconditions.checkNotNull(paramArrayOfString2);
    int j = Math.min(paramArrayOfString1.length, paramArrayOfString2.length);
    int i = 0;
    while (i < j)
    {
      if (verifySignature(paramString, paramArrayOfString1[i])) {
        return paramArrayOfString2[i];
      }
      i += 1;
    }
    return null;
  }
  
  static boolean verifySignature(String paramString1, String paramString2)
  {
    if ((paramString1 == null) && (paramString2 == null)) {
      return true;
    }
    if (paramString1 == null) {
      return false;
    }
    return paramString1.equals(paramString2);
  }
  
  static boolean zzcq(String paramString)
  {
    Preconditions.checkNotEmpty(paramString);
    return (paramString.charAt(0) != '_') || (paramString.equals("_ep"));
  }
  
  private static boolean zzct(String paramString)
  {
    Preconditions.checkNotNull(paramString);
    return paramString.matches("^(1:\\d+:android:[a-f0-9]+|ca-app-pub-.*)$");
  }
  
  private static int zzcu(String paramString)
  {
    if ("_ldl".equals(paramString)) {
      return 2048;
    }
    if ("_id".equals(paramString)) {
      return 256;
    }
    return 36;
  }
  
  static boolean zzcv(String paramString)
  {
    return (!TextUtils.isEmpty(paramString)) && (paramString.startsWith("_"));
  }
  
  final void add(Bundle paramBundle, String paramString, Object paramObject)
  {
    if (paramBundle == null) {
      return;
    }
    if ((paramObject instanceof Long))
    {
      paramBundle.putLong(paramString, ((Long)paramObject).longValue());
      return;
    }
    if ((paramObject instanceof String))
    {
      paramBundle.putString(paramString, String.valueOf(paramObject));
      return;
    }
    if ((paramObject instanceof Double))
    {
      paramBundle.putDouble(paramString, ((Double)paramObject).doubleValue());
      return;
    }
    if (paramString != null)
    {
      if (paramObject != null) {
        paramBundle = paramObject.getClass().getSimpleName();
      } else {
        paramBundle = null;
      }
      zzgo().zzji().append("Not putting event parameter. Invalid value type. name, type", zzgl().zzbt(paramString), paramBundle);
    }
  }
  
  final void add(String paramString1, int paramInt1, String paramString2, String paramString3, int paramInt2)
  {
    paramString1 = new Bundle();
    addEvent(paramString1, paramInt1);
    if (!TextUtils.isEmpty(paramString2)) {
      paramString1.putString(paramString2, paramString3);
    }
    if ((paramInt1 == 6) || (paramInt1 == 7) || (paramInt1 == 2)) {
      paramString1.putLong("_el", paramInt2);
    }
    zzadj.zzgr();
    zzadj.zzge().logEvent("auto", "_err", paramString1);
  }
  
  final boolean add(String paramString1, int paramInt, String paramString2)
  {
    if (paramString2 == null)
    {
      zzgo().zzjd().append("Name is required and can't be null. Type", paramString1);
      return false;
    }
    if (paramString2.codePointCount(0, paramString2.length()) > paramInt)
    {
      zzgo().zzjd().append("Name is too long. Type, maximum supported length, name", paramString1, Integer.valueOf(paramInt), paramString2);
      return false;
    }
    return true;
  }
  
  public final void append(int paramInt1, String paramString1, String paramString2, int paramInt2)
  {
    add(null, paramInt1, paramString1, paramString2, paramInt2);
  }
  
  final boolean checkVersion(String paramString)
  {
    zzaf();
    if (Wrappers.packageManager(getContext()).checkCallingOrSelfPermission(paramString) == 0) {
      return true;
    }
    zzgo().zzjk().append("Permission not granted", paramString);
    return false;
  }
  
  final long create(Context paramContext, String paramString)
  {
    zzaf();
    Preconditions.checkNotNull(paramContext);
    Preconditions.checkNotEmpty(paramString);
    PackageManager localPackageManager = paramContext.getPackageManager();
    MessageDigest localMessageDigest = getMessageDigest();
    if (localMessageDigest == null)
    {
      zzgo().zzjd().zzbx("Could not get MD5 instance");
      return -1L;
    }
    if (localPackageManager != null) {
      try
      {
        boolean bool = isDebuggable(paramContext, paramString);
        if (!bool)
        {
          paramContext = Wrappers.packageManager(paramContext).getPackageInfo(getContext().getPackageName(), 64);
          if ((signatures != null) && (signatures.length > 0))
          {
            paramContext = signatures[0];
            long l = encode(localMessageDigest.digest(paramContext.toByteArray()));
            return l;
          }
          zzgo().zzjg().zzbx("Could not get signatures");
          return -1L;
        }
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        zzgo().zzjd().append("Package name not found", paramContext);
      }
    }
    return 0L;
  }
  
  final boolean decode(String paramString1, String paramString2)
  {
    if (paramString2 == null)
    {
      zzgo().zzjd().append("Name is required and can't be null. Type", paramString1);
      return false;
    }
    if (paramString2.length() == 0)
    {
      zzgo().zzjd().append("Name is required and can't be empty. Type", paramString1);
      return false;
    }
    int i = paramString2.codePointAt(0);
    if (!Character.isLetter(i))
    {
      zzgo().zzjd().append("Name must start with a letter. Type, name", paramString1, paramString2);
      return false;
    }
    int j = paramString2.length();
    i = Character.charCount(i);
    while (i < j)
    {
      int k = paramString2.codePointAt(i);
      if ((k != 95) && (!Character.isLetterOrDigit(k)))
      {
        zzgo().zzjd().append("Name must consist of letters, digits or _ (underscores). Type, name", paramString1, paramString2);
        return false;
      }
      i += Character.charCount(k);
    }
    return true;
  }
  
  final zzad e(String paramString1, String paramString2, Bundle paramBundle, String paramString3, long paramLong, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (TextUtils.isEmpty(paramString2)) {
      return null;
    }
    if (zzcr(paramString2) == 0)
    {
      if (paramBundle != null) {}
      for (paramBundle = new Bundle(paramBundle);; paramBundle = new Bundle()) {
        break;
      }
      paramBundle.putString("_o", paramString3);
      return new zzad(paramString2, new zzaa(newInstance(parse(paramString1, paramString2, paramBundle, CollectionUtils.listOf("_o"), false, false))), paramString3, paramLong);
    }
    zzgo().zzjd().append("Invalid conditional property event name", zzgl().zzbu(paramString2));
    throw new IllegalArgumentException();
  }
  
  final int get(String paramString, Object paramObject)
  {
    boolean bool;
    if ("_ldl".equals(paramString)) {
      bool = put("user property referrer", paramString, zzcu(paramString), paramObject, false);
    } else {
      bool = put("user property", paramString, zzcu(paramString), paramObject, false);
    }
    if (bool) {
      return 0;
    }
    return 7;
  }
  
  final boolean getDisplayTitle(String paramString1, String paramString2)
  {
    if (!TextUtils.isEmpty(paramString1))
    {
      if (!zzct(paramString1))
      {
        if (!zzadj.zzkj()) {
          break label101;
        }
        zzgo().zzjd().append("Invalid google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI. provided id", zzap.zzbv(paramString1));
        return false;
      }
    }
    else
    {
      if (TextUtils.isEmpty(paramString2)) {
        break label78;
      }
      if (!zzct(paramString2))
      {
        zzgo().zzjd().append("Invalid gma_app_id. Analytics disabled.", zzap.zzbv(paramString2));
        return false;
      }
    }
    return true;
    label78:
    if (zzadj.zzkj()) {
      zzgo().zzjd().zzbx("Missing google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI");
    }
    label101:
    return false;
  }
  
  final Bundle newInstance(Bundle paramBundle)
  {
    Bundle localBundle = new Bundle();
    if (paramBundle != null)
    {
      Iterator localIterator = paramBundle.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        Object localObject = read(str, paramBundle.get(str));
        if (localObject == null) {
          zzgo().zzjg().append("Param value can't be null", zzgl().zzbt(str));
        } else {
          add(localBundle, str, localObject);
        }
      }
    }
    return localBundle;
  }
  
  final Bundle parse(Uri paramUri)
  {
    if (paramUri == null) {
      return null;
    }
    Bundle localBundle;
    try
    {
      boolean bool = paramUri.isHierarchical();
      String str2;
      String str3;
      String str4;
      if (bool)
      {
        str1 = paramUri.getQueryParameter("utm_campaign");
        str2 = paramUri.getQueryParameter("utm_source");
        str3 = paramUri.getQueryParameter("utm_medium");
        str4 = paramUri.getQueryParameter("gclid");
      }
      else
      {
        str1 = null;
        str2 = null;
        str3 = null;
        str4 = null;
      }
      if ((TextUtils.isEmpty(str1)) && (TextUtils.isEmpty(str2)) && (TextUtils.isEmpty(str3)) && (TextUtils.isEmpty(str4))) {
        return null;
      }
      localBundle = new Bundle();
      if (!TextUtils.isEmpty(str1)) {
        localBundle.putString("campaign", str1);
      }
      if (!TextUtils.isEmpty(str2)) {
        localBundle.putString("source", str2);
      }
      if (!TextUtils.isEmpty(str3)) {
        localBundle.putString("medium", str3);
      }
      if (!TextUtils.isEmpty(str4)) {
        localBundle.putString("gclid", str4);
      }
      String str1 = paramUri.getQueryParameter("utm_term");
      if (!TextUtils.isEmpty(str1)) {
        localBundle.putString("term", str1);
      }
      str1 = paramUri.getQueryParameter("utm_content");
      if (!TextUtils.isEmpty(str1)) {
        localBundle.putString("content", str1);
      }
      str1 = paramUri.getQueryParameter("aclid");
      if (!TextUtils.isEmpty(str1)) {
        localBundle.putString("aclid", str1);
      }
      str1 = paramUri.getQueryParameter("cp1");
      if (!TextUtils.isEmpty(str1)) {
        localBundle.putString("cp1", str1);
      }
      paramUri = paramUri.getQueryParameter("anid");
      if (!TextUtils.isEmpty(paramUri))
      {
        localBundle.putString("anid", paramUri);
        return localBundle;
      }
    }
    catch (UnsupportedOperationException paramUri)
    {
      zzgo().zzjg().append("Install referrer url isn't a hierarchical URI", paramUri);
      return null;
    }
    return localBundle;
  }
  
  final Bundle parse(String paramString1, String paramString2, Bundle paramBundle, List paramList, boolean paramBoolean1, boolean paramBoolean2)
  {
    Bundle localBundle;
    if (paramBundle != null)
    {
      localBundle = new Bundle(paramBundle);
      Iterator localIterator = paramBundle.keySet().iterator();
      int j = 0;
      if (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        int i;
        if ((paramList != null) && (paramList.contains(str)))
        {
          i = 0;
        }
        else
        {
          int k = 14;
          if (paramBoolean1)
          {
            if (!decode("event param", str)) {}
            do
            {
              i = 3;
              break;
              if (!verify("event param", null, str))
              {
                i = 14;
                break;
              }
            } while (!add("event param", 40, str));
          }
          i = 0;
          if (i == 0)
          {
            if (!canonicalize("event param", str)) {}
            for (;;)
            {
              i = 3;
              break label203;
              if (!verify("event param", null, str))
              {
                i = k;
                break label203;
              }
              if (add("event param", 40, str)) {
                break;
              }
            }
          }
        }
        label203:
        Object localObject;
        if (i != 0)
        {
          if (addEvent(localBundle, i))
          {
            localBundle.putString("_ev", get(str, 40, true));
            if (i == 3) {
              sendResponse(localBundle, str);
            }
          }
          localBundle.remove(str);
          i = j;
        }
        else
        {
          localObject = paramBundle.get(str);
          zzaf();
          if (paramBoolean2)
          {
            if ((localObject instanceof Parcelable[]))
            {
              i = ((Parcelable[])localObject).length;
            }
            else
            {
              if (!(localObject instanceof ArrayList)) {
                break label352;
              }
              i = ((ArrayList)localObject).size();
            }
            if (i > 1000)
            {
              zzgo().zzjg().append("Parameter array is too long; discarded. Value kind, name, array length", "param", str, Integer.valueOf(i));
              i = 0;
            }
            else
            {
              label352:
              i = 1;
            }
            if (i == 0)
            {
              i = 17;
              break label445;
            }
          }
          boolean bool;
          if (((zzgq().zzay(paramString1)) && (zzcv(paramString2))) || (zzcv(str))) {
            bool = put("param", str, 256, localObject, paramBoolean2);
          } else {
            bool = put("param", str, 100, localObject, paramBoolean2);
          }
          if (bool) {
            i = 0;
          } else {
            i = 4;
          }
          label445:
          if ((i == 0) || ("_ev".equals(str))) {
            break label516;
          }
          if (addEvent(localBundle, i))
          {
            localBundle.putString("_ev", get(str, 40, true));
            sendResponse(localBundle, paramBundle.get(str));
          }
          localBundle.remove(str);
          i = j;
        }
        for (;;)
        {
          j = i;
          break;
          label516:
          i = j;
          if (zzcq(str))
          {
            j += 1;
            i = j;
            if (j > 25)
            {
              localObject = new StringBuilder(48);
              ((StringBuilder)localObject).append("Event can't contain more than 25 params");
              localObject = ((StringBuilder)localObject).toString();
              zzgo().zzjd().append((String)localObject, zzgl().zzbs(paramString2), zzgl().toString(paramBundle));
              addEvent(localBundle, 5);
              localBundle.remove(str);
              i = j;
            }
          }
        }
      }
    }
    else
    {
      return null;
    }
    return localBundle;
  }
  
  final Object read(String paramString, Object paramObject)
  {
    boolean bool = "_ev".equals(paramString);
    int i = 256;
    if (bool) {
      return read(256, paramObject, true);
    }
    if (!zzcv(paramString)) {
      i = 100;
    }
    return read(i, paramObject, false);
  }
  
  final Object toString(String paramString, Object paramObject)
  {
    if ("_ldl".equals(paramString)) {
      return read(zzcu(paramString), paramObject, true);
    }
    return read(zzcu(paramString), paramObject, false);
  }
  
  final boolean verify(String paramString1, String[] paramArrayOfString, String paramString2)
  {
    if (paramString2 == null)
    {
      zzgo().zzjd().append("Name is required and can't be null. Type", paramString1);
      return false;
    }
    Preconditions.checkNotNull(paramString2);
    String[] arrayOfString = zzaui;
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      if (paramString2.startsWith(arrayOfString[i]))
      {
        i = 1;
        break label74;
      }
      i += 1;
    }
    i = 0;
    label74:
    if (i != 0)
    {
      zzgo().zzjd().append("Name starts with reserved prefix. Type, name", paramString1, paramString2);
      return false;
    }
    if (paramArrayOfString != null)
    {
      Preconditions.checkNotNull(paramArrayOfString);
      j = paramArrayOfString.length;
      i = 0;
      while (i < j)
      {
        if (verifySignature(paramString2, paramArrayOfString[i]))
        {
          i = 1;
          break label148;
        }
        i += 1;
      }
      i = 0;
      label148:
      if (i != 0)
      {
        zzgo().zzjd().append("Name is reserved. Type, name", paramString1, paramString2);
        return false;
      }
    }
    return true;
  }
  
  final int zzcr(String paramString)
  {
    if (!canonicalize("event", paramString)) {
      return 2;
    }
    if (!verify("event", AppMeasurement.Event.zzadk, paramString)) {
      return 13;
    }
    if (!add("event", 40, paramString)) {
      return 2;
    }
    return 0;
  }
  
  final int zzcs(String paramString)
  {
    if (!canonicalize("user property", paramString)) {
      return 6;
    }
    if (!verify("user property", AppMeasurement.UserProperty.zzado, paramString)) {
      return 15;
    }
    if (!add("user property", 24, paramString)) {
      return 6;
    }
    return 0;
  }
  
  final boolean zzcw(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    String str = zzgq().zzhy();
    zzgr();
    return str.equals(paramString);
  }
  
  protected final boolean zzgt()
  {
    return true;
  }
  
  protected final void zzgu()
  {
    zzaf();
    SecureRandom localSecureRandom = new SecureRandom();
    long l2 = localSecureRandom.nextLong();
    long l1 = l2;
    if (l2 == 0L)
    {
      long l3 = localSecureRandom.nextLong();
      l2 = l3;
      l1 = l2;
      if (l3 == 0L)
      {
        zzgo().zzjg().zzbx("Utils falling back to Random for random id");
        l1 = l2;
      }
    }
    zzauk.set(l1);
  }
  
  public final long zzmc()
  {
    long l1;
    if (zzauk.get() == 0L)
    {
      localAtomicLong = zzauk;
      try
      {
        l1 = new Random(System.nanoTime() ^ zzbx().currentTimeMillis()).nextLong();
        int i = zzaed + 1;
        zzaed = i;
        long l2 = i;
        return l1 + l2;
      }
      catch (Throwable localThrowable1)
      {
        throw localThrowable1;
      }
    }
    AtomicLong localAtomicLong = zzauk;
    try
    {
      zzauk.compareAndSet(-1L, 1L);
      l1 = zzauk.getAndIncrement();
      return l1;
    }
    catch (Throwable localThrowable2)
    {
      throw localThrowable2;
    }
  }
  
  final SecureRandom zzmd()
  {
    zzaf();
    if (zzauj == null) {
      zzauj = new SecureRandom();
    }
    return zzauj;
  }
  
  public final int zzme()
  {
    if (zzaul == null) {
      zzaul = Integer.valueOf(GoogleApiAvailabilityLight.getInstance().getApkVersion(getContext()) / 1000);
    }
    return zzaul.intValue();
  }
  
  final String zzmf()
  {
    byte[] arrayOfByte = new byte[16];
    zzmd().nextBytes(arrayOfByte);
    return String.format(Locale.US, "%032x", new Object[] { new BigInteger(1, arrayOfByte) });
  }
}
