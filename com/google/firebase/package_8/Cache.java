package com.google.firebase.package_8;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.util.Properties;

final class Cache
{
  Cache() {}
  
  private final Buffer create(Context paramContext, String paramString)
    throws zzaa
  {
    try
    {
      Buffer localBuffer1 = load(paramContext, paramString);
      if (localBuffer1 != null)
      {
        put(paramContext, paramString, localBuffer1);
        return localBuffer1;
      }
      localBuffer1 = null;
    }
    catch (zzaa localZzaa1) {}
    try
    {
      Buffer localBuffer2 = get(paramContext.getSharedPreferences("com.google.android.gms.appid", 0), paramString);
      if (localBuffer2 != null)
      {
        read(paramContext, paramString, localBuffer2, false);
        return localBuffer2;
      }
    }
    catch (zzaa localZzaa2)
    {
      if (localZzaa2 == null) {
        return null;
      }
      throw localZzaa2;
    }
  }
  
  private static Buffer get(SharedPreferences paramSharedPreferences, String paramString)
    throws zzaa
  {
    String str1 = paramSharedPreferences.getString(zzaw.getName(paramString, "|P|"), null);
    String str2 = paramSharedPreferences.getString(zzaw.getName(paramString, "|K|"), null);
    if (str1 != null)
    {
      if (str2 == null) {
        return null;
      }
      return new Buffer(load(str1, str2), getList(paramSharedPreferences, paramString));
    }
    return null;
  }
  
  private static Buffer get(FileChannel paramFileChannel)
    throws zzaa, IOException
  {
    Properties localProperties = new Properties();
    localProperties.load(Channels.newInputStream(paramFileChannel));
    paramFileChannel = localProperties.getProperty("pub");
    String str = localProperties.getProperty("pri");
    if ((paramFileChannel != null) && (str != null))
    {
      paramFileChannel = load(paramFileChannel, str);
      try
      {
        long l = Long.parseLong(localProperties.getProperty("cre"));
        return new Buffer(paramFileChannel, l);
      }
      catch (NumberFormatException paramFileChannel)
      {
        throw new zzaa(paramFileChannel);
      }
    }
    throw new zzaa("Invalid properties file");
  }
  
  private static File get(Context paramContext)
  {
    File localFile = ContextCompat.getNoBackupFilesDir(paramContext);
    if ((localFile != null) && (localFile.isDirectory())) {
      return localFile;
    }
    Log.w("FirebaseInstanceId", "noBackupFilesDir doesn't exist, using regular files directory instead");
    return paramContext.getFilesDir();
  }
  
  private static long getList(SharedPreferences paramSharedPreferences, String paramString)
  {
    paramSharedPreferences = paramSharedPreferences.getString(zzaw.getName(paramString, "cre"), null);
    if (paramSharedPreferences != null) {}
    try
    {
      long l = Long.parseLong(paramSharedPreferences);
      return l;
    }
    catch (NumberFormatException paramSharedPreferences)
    {
      for (;;) {}
    }
    return 0L;
  }
  
  static void initialize(Context paramContext)
  {
    paramContext = get(paramContext).listFiles();
    int j = paramContext.length;
    int i = 0;
    while (i < j)
    {
      Object localObject = paramContext[i];
      if (localObject.getName().startsWith("com.google.InstanceId")) {
        localObject.delete();
      }
      i += 1;
    }
  }
  
  private final Buffer load(Context paramContext, String paramString)
    throws zzaa
  {
    paramContext = read(paramContext, paramString);
    if (!paramContext.exists()) {
      return null;
    }
    try
    {
      paramString = read(paramContext);
      return paramString;
    }
    catch (zzaa|IOException paramString)
    {
      StringBuilder localStringBuilder;
      if (Log.isLoggable("FirebaseInstanceId", 3))
      {
        paramString = String.valueOf(paramString);
        localStringBuilder = new StringBuilder(String.valueOf(paramString).length() + 40);
        localStringBuilder.append("Failed to read key from file, retrying: ");
        localStringBuilder.append(paramString);
        Log.d("FirebaseInstanceId", localStringBuilder.toString());
      }
      try
      {
        paramContext = read(paramContext);
        return paramContext;
      }
      catch (IOException paramContext)
      {
        paramString = String.valueOf(paramContext);
        localStringBuilder = new StringBuilder(String.valueOf(paramString).length() + 45);
        localStringBuilder.append("IID file exists, but failed to read from it: ");
        localStringBuilder.append(paramString);
        Log.w("FirebaseInstanceId", localStringBuilder.toString());
        throw new zzaa(paramContext);
      }
    }
  }
  
  /* Error */
  private static java.security.KeyPair load(String paramString1, String paramString2)
    throws zzaa
  {
    // Byte code:
    //   0: aload_0
    //   1: bipush 8
    //   3: invokestatic 219	android/util/Base64:decode	(Ljava/lang/String;I)[B
    //   6: astore_0
    //   7: aload_1
    //   8: bipush 8
    //   10: invokestatic 219	android/util/Base64:decode	(Ljava/lang/String;I)[B
    //   13: astore_1
    //   14: ldc -35
    //   16: invokestatic 227	java/security/KeyFactory:getInstance	(Ljava/lang/String;)Ljava/security/KeyFactory;
    //   19: astore_2
    //   20: aload_2
    //   21: new 229	java/security/spec/X509EncodedKeySpec
    //   24: dup
    //   25: aload_0
    //   26: invokespecial 232	java/security/spec/X509EncodedKeySpec:<init>	([B)V
    //   29: invokevirtual 236	java/security/KeyFactory:generatePublic	(Ljava/security/spec/KeySpec;)Ljava/security/PublicKey;
    //   32: astore_0
    //   33: aload_2
    //   34: new 238	java/security/spec/PKCS8EncodedKeySpec
    //   37: dup
    //   38: aload_1
    //   39: invokespecial 239	java/security/spec/PKCS8EncodedKeySpec:<init>	([B)V
    //   42: invokevirtual 243	java/security/KeyFactory:generatePrivate	(Ljava/security/spec/KeySpec;)Ljava/security/PrivateKey;
    //   45: astore_1
    //   46: new 245	java/security/KeyPair
    //   49: dup
    //   50: aload_0
    //   51: aload_1
    //   52: invokespecial 248	java/security/KeyPair:<init>	(Ljava/security/PublicKey;Ljava/security/PrivateKey;)V
    //   55: astore_0
    //   56: aload_0
    //   57: areturn
    //   58: astore_0
    //   59: aload_0
    //   60: invokestatic 184	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   63: astore_1
    //   64: new 186	java/lang/StringBuilder
    //   67: dup
    //   68: aload_1
    //   69: invokestatic 184	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   72: invokevirtual 190	java/lang/String:length	()I
    //   75: bipush 19
    //   77: iadd
    //   78: invokespecial 193	java/lang/StringBuilder:<init>	(I)V
    //   81: astore_2
    //   82: aload_2
    //   83: ldc -6
    //   85: invokevirtual 199	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   88: pop
    //   89: aload_2
    //   90: aload_1
    //   91: invokevirtual 199	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   94: pop
    //   95: ldc -123
    //   97: aload_2
    //   98: invokevirtual 202	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   101: invokestatic 141	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   104: pop
    //   105: new 27	com/google/firebase/package_8/zzaa
    //   108: dup
    //   109: aload_0
    //   110: checkcast 252	java/lang/Exception
    //   113: invokespecial 114	com/google/firebase/package_8/zzaa:<init>	(Ljava/lang/Exception;)V
    //   116: athrow
    //   117: astore_0
    //   118: new 27	com/google/firebase/package_8/zzaa
    //   121: dup
    //   122: aload_0
    //   123: invokespecial 114	com/google/firebase/package_8/zzaa:<init>	(Ljava/lang/Exception;)V
    //   126: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	127	0	paramString1	String
    //   0	127	1	paramString2	String
    //   19	79	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   14	20	58	java/security/spec/InvalidKeySpecException
    //   14	20	58	java/security/NoSuchAlgorithmException
    //   20	33	58	java/security/spec/InvalidKeySpecException
    //   20	33	58	java/security/NoSuchAlgorithmException
    //   33	46	58	java/security/spec/InvalidKeySpecException
    //   33	46	58	java/security/NoSuchAlgorithmException
    //   46	56	58	java/security/spec/InvalidKeySpecException
    //   46	56	58	java/security/NoSuchAlgorithmException
    //   0	14	117	java/lang/IllegalArgumentException
  }
  
  private final void put(Context paramContext, String paramString, Buffer paramBuffer)
  {
    paramContext = paramContext.getSharedPreferences("com.google.android.gms.appid", 0);
    try
    {
      boolean bool = paramBuffer.equals(get(paramContext, paramString));
      if (bool) {
        return;
      }
    }
    catch (zzaa localZzaa)
    {
      for (;;) {}
    }
    if (Log.isLoggable("FirebaseInstanceId", 3)) {
      Log.d("FirebaseInstanceId", "Writing key to shared preferences");
    }
    paramContext = paramContext.edit();
    paramContext.putString(zzaw.getName(paramString, "|P|"), Buffer.encode(paramBuffer));
    paramContext.putString(zzaw.getName(paramString, "|K|"), Buffer.toString(paramBuffer));
    paramContext.putString(zzaw.getName(paramString, "cre"), String.valueOf(Buffer.copyTo(paramBuffer)));
    paramContext.commit();
  }
  
  /* Error */
  private final Buffer read(Context paramContext, String paramString, Buffer paramBuffer, boolean paramBoolean)
  {
    // Byte code:
    //   0: ldc -123
    //   2: iconst_3
    //   3: invokestatic 180	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   6: ifeq +12 -> 18
    //   9: ldc -123
    //   11: ldc_w 290
    //   14: invokestatic 205	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   17: pop
    //   18: new 85	java/util/Properties
    //   21: dup
    //   22: invokespecial 86	java/util/Properties:<init>	()V
    //   25: astore 8
    //   27: aload 8
    //   29: ldc 97
    //   31: aload_3
    //   32: invokestatic 270	com/google/firebase/package_8/Buffer:encode	(Lcom/google/firebase/package_8/Buffer;)Ljava/lang/String;
    //   35: invokevirtual 294	java/util/Properties:setProperty	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;
    //   38: pop
    //   39: aload 8
    //   41: ldc 103
    //   43: aload_3
    //   44: invokestatic 278	com/google/firebase/package_8/Buffer:toString	(Lcom/google/firebase/package_8/Buffer;)Ljava/lang/String;
    //   47: invokevirtual 294	java/util/Properties:setProperty	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;
    //   50: pop
    //   51: aload 8
    //   53: ldc 105
    //   55: aload_3
    //   56: invokestatic 282	com/google/firebase/package_8/Buffer:copyTo	(Lcom/google/firebase/package_8/Buffer;)J
    //   59: invokestatic 285	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   62: invokevirtual 294	java/util/Properties:setProperty	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;
    //   65: pop
    //   66: aload_1
    //   67: aload_2
    //   68: invokestatic 170	com/google/firebase/package_8/Cache:read	(Landroid/content/Context;Ljava/lang/String;)Ljava/io/File;
    //   71: astore_1
    //   72: aload_1
    //   73: invokevirtual 297	java/io/File:createNewFile	()Z
    //   76: pop
    //   77: new 299	java/io/RandomAccessFile
    //   80: dup
    //   81: aload_1
    //   82: ldc_w 301
    //   85: invokespecial 304	java/io/RandomAccessFile:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   88: astore_1
    //   89: aload_1
    //   90: invokevirtual 308	java/io/RandomAccessFile:getChannel	()Ljava/nio/channels/FileChannel;
    //   93: astore_2
    //   94: aload_2
    //   95: invokevirtual 312	java/nio/channels/FileChannel:lock	()Ljava/nio/channels/FileLock;
    //   98: pop
    //   99: iload 4
    //   101: ifeq +119 -> 220
    //   104: aload_2
    //   105: invokevirtual 316	java/nio/channels/FileChannel:size	()J
    //   108: lstore 6
    //   110: lload 6
    //   112: lconst_0
    //   113: lcmp
    //   114: ifle +106 -> 220
    //   117: aload_2
    //   118: lconst_0
    //   119: invokevirtual 320	java/nio/channels/FileChannel:position	(J)Ljava/nio/channels/FileChannel;
    //   122: pop
    //   123: aload_2
    //   124: invokestatic 322	com/google/firebase/package_8/Cache:get	(Ljava/nio/channels/FileChannel;)Lcom/google/firebase/package_8/Buffer;
    //   127: astore 9
    //   129: aload_2
    //   130: ifnull +8 -> 138
    //   133: aconst_null
    //   134: aload_2
    //   135: invokestatic 324	com/google/firebase/package_8/Cache:close	(Ljava/lang/Throwable;Ljava/nio/channels/FileChannel;)V
    //   138: aconst_null
    //   139: aload_1
    //   140: invokestatic 328	com/google/firebase/package_8/Cache:write	(Ljava/lang/Throwable;Ljava/io/RandomAccessFile;)V
    //   143: aload 9
    //   145: areturn
    //   146: astore 9
    //   148: ldc -123
    //   150: iconst_3
    //   151: invokestatic 180	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   154: istore 4
    //   156: iload 4
    //   158: ifeq +62 -> 220
    //   161: aload 9
    //   163: invokestatic 184	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   166: astore 9
    //   168: aload 9
    //   170: invokestatic 184	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   173: invokevirtual 190	java/lang/String:length	()I
    //   176: istore 5
    //   178: new 186	java/lang/StringBuilder
    //   181: dup
    //   182: iload 5
    //   184: bipush 64
    //   186: iadd
    //   187: invokespecial 193	java/lang/StringBuilder:<init>	(I)V
    //   190: astore 10
    //   192: aload 10
    //   194: ldc_w 330
    //   197: invokevirtual 199	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   200: pop
    //   201: aload 10
    //   203: aload 9
    //   205: invokevirtual 199	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   208: pop
    //   209: ldc -123
    //   211: aload 10
    //   213: invokevirtual 202	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   216: invokestatic 205	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   219: pop
    //   220: aload_2
    //   221: lconst_0
    //   222: invokevirtual 320	java/nio/channels/FileChannel:position	(J)Ljava/nio/channels/FileChannel;
    //   225: pop
    //   226: aload 8
    //   228: aload_2
    //   229: invokestatic 334	java/nio/channels/Channels:newOutputStream	(Ljava/nio/channels/WritableByteChannel;)Ljava/io/OutputStream;
    //   232: aconst_null
    //   233: invokevirtual 338	java/util/Properties:store	(Ljava/io/OutputStream;Ljava/lang/String;)V
    //   236: aload_2
    //   237: ifnull +8 -> 245
    //   240: aconst_null
    //   241: aload_2
    //   242: invokestatic 324	com/google/firebase/package_8/Cache:close	(Ljava/lang/Throwable;Ljava/nio/channels/FileChannel;)V
    //   245: aconst_null
    //   246: aload_1
    //   247: invokestatic 328	com/google/firebase/package_8/Cache:write	(Ljava/lang/Throwable;Ljava/io/RandomAccessFile;)V
    //   250: aload_3
    //   251: areturn
    //   252: astore_3
    //   253: aload_3
    //   254: athrow
    //   255: astore 8
    //   257: aload_2
    //   258: ifnull +8 -> 266
    //   261: aload_3
    //   262: aload_2
    //   263: invokestatic 324	com/google/firebase/package_8/Cache:close	(Ljava/lang/Throwable;Ljava/nio/channels/FileChannel;)V
    //   266: aload 8
    //   268: athrow
    //   269: astore_2
    //   270: aload_2
    //   271: athrow
    //   272: astore_3
    //   273: aload_2
    //   274: aload_1
    //   275: invokestatic 328	com/google/firebase/package_8/Cache:write	(Ljava/lang/Throwable;Ljava/io/RandomAccessFile;)V
    //   278: aload_3
    //   279: athrow
    //   280: astore_1
    //   281: aload_1
    //   282: invokestatic 184	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   285: astore_1
    //   286: new 186	java/lang/StringBuilder
    //   289: dup
    //   290: aload_1
    //   291: invokestatic 184	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   294: invokevirtual 190	java/lang/String:length	()I
    //   297: bipush 21
    //   299: iadd
    //   300: invokespecial 193	java/lang/StringBuilder:<init>	(I)V
    //   303: astore_2
    //   304: aload_2
    //   305: ldc_w 340
    //   308: invokevirtual 199	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   311: pop
    //   312: aload_2
    //   313: aload_1
    //   314: invokevirtual 199	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   317: pop
    //   318: ldc -123
    //   320: aload_2
    //   321: invokevirtual 202	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   324: invokestatic 141	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   327: pop
    //   328: aconst_null
    //   329: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	330	0	this	Cache
    //   0	330	1	paramContext	Context
    //   0	330	2	paramString	String
    //   0	330	3	paramBuffer	Buffer
    //   0	330	4	paramBoolean	boolean
    //   176	11	5	i	int
    //   108	3	6	l	long
    //   25	202	8	localProperties	Properties
    //   255	12	8	localThrowable	Throwable
    //   127	17	9	localBuffer	Buffer
    //   146	16	9	localIOException	IOException
    //   166	38	9	str	String
    //   190	22	10	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   117	129	146	java/io/IOException
    //   117	129	146	com/google/firebase/package_8/zzaa
    //   94	99	252	java/lang/Throwable
    //   104	110	252	java/lang/Throwable
    //   117	129	252	java/lang/Throwable
    //   148	156	252	java/lang/Throwable
    //   161	178	252	java/lang/Throwable
    //   178	220	252	java/lang/Throwable
    //   220	236	252	java/lang/Throwable
    //   253	255	255	java/lang/Throwable
    //   89	94	269	java/lang/Throwable
    //   133	138	269	java/lang/Throwable
    //   240	245	269	java/lang/Throwable
    //   261	266	269	java/lang/Throwable
    //   266	269	269	java/lang/Throwable
    //   270	272	272	java/lang/Throwable
    //   72	77	280	java/io/IOException
    //   77	89	280	java/io/IOException
    //   138	143	280	java/io/IOException
    //   245	250	280	java/io/IOException
    //   273	280	280	java/io/IOException
  }
  
  /* Error */
  private final Buffer read(File paramFile)
    throws zzaa, IOException
  {
    // Byte code:
    //   0: new 255	java/io/FileInputStream
    //   3: dup
    //   4: aload_1
    //   5: invokespecial 343	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   8: astore_1
    //   9: aload_1
    //   10: invokevirtual 344	java/io/FileInputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   13: astore_2
    //   14: aload_2
    //   15: lconst_0
    //   16: ldc2_w 345
    //   19: iconst_1
    //   20: invokevirtual 349	java/nio/channels/FileChannel:lock	(JJZ)Ljava/nio/channels/FileLock;
    //   23: pop
    //   24: aload_2
    //   25: invokestatic 322	com/google/firebase/package_8/Cache:get	(Ljava/nio/channels/FileChannel;)Lcom/google/firebase/package_8/Buffer;
    //   28: astore_3
    //   29: aload_2
    //   30: ifnull +8 -> 38
    //   33: aconst_null
    //   34: aload_2
    //   35: invokestatic 324	com/google/firebase/package_8/Cache:close	(Ljava/lang/Throwable;Ljava/nio/channels/FileChannel;)V
    //   38: aconst_null
    //   39: aload_1
    //   40: invokestatic 351	com/google/firebase/package_8/Cache:load	(Ljava/lang/Throwable;Ljava/io/FileInputStream;)V
    //   43: aload_3
    //   44: areturn
    //   45: astore_3
    //   46: aload_3
    //   47: athrow
    //   48: astore 4
    //   50: aload_2
    //   51: ifnull +8 -> 59
    //   54: aload_3
    //   55: aload_2
    //   56: invokestatic 324	com/google/firebase/package_8/Cache:close	(Ljava/lang/Throwable;Ljava/nio/channels/FileChannel;)V
    //   59: aload 4
    //   61: athrow
    //   62: astore_2
    //   63: goto +6 -> 69
    //   66: astore_3
    //   67: aload_3
    //   68: athrow
    //   69: aload_3
    //   70: aload_1
    //   71: invokestatic 351	com/google/firebase/package_8/Cache:load	(Ljava/lang/Throwable;Ljava/io/FileInputStream;)V
    //   74: aload_2
    //   75: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	76	0	this	Cache
    //   0	76	1	paramFile	File
    //   13	43	2	localFileChannel	FileChannel
    //   62	13	2	localThrowable1	Throwable
    //   28	16	3	localBuffer	Buffer
    //   45	10	3	localThrowable2	Throwable
    //   66	4	3	localThrowable3	Throwable
    //   48	12	4	localThrowable4	Throwable
    // Exception table:
    //   from	to	target	type
    //   14	29	45	java/lang/Throwable
    //   46	48	48	java/lang/Throwable
    //   67	69	62	java/lang/Throwable
    //   9	14	66	java/lang/Throwable
    //   33	38	66	java/lang/Throwable
    //   54	59	66	java/lang/Throwable
    //   59	62	66	java/lang/Throwable
  }
  
  private static File read(Context paramContext, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      paramString = "com.google.InstanceId.properties";
    }
    try
    {
      paramString = Base64.encodeToString(paramString.getBytes("UTF-8"), 11);
      int i = String.valueOf(paramString).length();
      StringBuilder localStringBuilder = new StringBuilder(i + 33);
      localStringBuilder.append("com.google.InstanceId_");
      localStringBuilder.append(paramString);
      localStringBuilder.append(".properties");
      paramString = localStringBuilder.toString();
      return new File(get(paramContext), paramString);
    }
    catch (UnsupportedEncodingException paramContext)
    {
      throw new AssertionError(paramContext);
    }
  }
  
  final Buffer get(Context paramContext, String paramString)
  {
    Buffer localBuffer1 = new Buffer(LocationServices.decode(), System.currentTimeMillis());
    Buffer localBuffer2 = read(paramContext, paramString, localBuffer1, true);
    if ((localBuffer2 != null) && (!localBuffer2.equals(localBuffer1)))
    {
      if (Log.isLoggable("FirebaseInstanceId", 3))
      {
        Log.d("FirebaseInstanceId", "Loaded key after generating new one, using loaded one");
        return localBuffer2;
      }
    }
    else
    {
      if (Log.isLoggable("FirebaseInstanceId", 3)) {
        Log.d("FirebaseInstanceId", "Generated new key");
      }
      put(paramContext, paramString, localBuffer1);
      return localBuffer1;
    }
    return localBuffer2;
  }
  
  final Buffer lookup(Context paramContext, String paramString)
    throws zzaa
  {
    Buffer localBuffer = create(paramContext, paramString);
    if (localBuffer != null) {
      return localBuffer;
    }
    return get(paramContext, paramString);
  }
}
