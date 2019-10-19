package com.google.android.android.internal.measurement;

import com.google.android.gms.internal.measurement.zzut;
import java.util.logging.Logger;

abstract class zzvk<T extends com.google.android.gms.internal.measurement.zzuz>
{
  private static final Logger logger = Logger.getLogger(zzut.class.getName());
  private static String zzbyk = "com.google.protobuf.BlazeGeneratedExtensionRegistryLiteLoader";
  
  zzvk() {}
  
  /* Error */
  static zzuz newInstance(Class paramClass)
  {
    // Byte code:
    //   0: ldc 51
    //   2: invokevirtual 55	java/lang/Class:getClassLoader	()Ljava/lang/ClassLoader;
    //   5: astore_2
    //   6: aload_0
    //   7: ldc 57
    //   9: invokevirtual 61	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   12: ifeq +10 -> 22
    //   15: getstatic 31	com/google/android/android/internal/measurement/zzvk:zzbyk	Ljava/lang/String;
    //   18: astore_1
    //   19: goto +45 -> 64
    //   22: aload_0
    //   23: invokevirtual 65	java/lang/Class:getPackage	()Ljava/lang/Package;
    //   26: ldc 51
    //   28: invokevirtual 65	java/lang/Class:getPackage	()Ljava/lang/Package;
    //   31: invokevirtual 61	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   34: ifeq +339 -> 373
    //   37: ldc 67
    //   39: iconst_2
    //   40: anewarray 5	java/lang/Object
    //   43: dup
    //   44: iconst_0
    //   45: aload_0
    //   46: invokevirtual 65	java/lang/Class:getPackage	()Ljava/lang/Package;
    //   49: invokevirtual 70	java/lang/Package:getName	()Ljava/lang/String;
    //   52: aastore
    //   53: dup
    //   54: iconst_1
    //   55: aload_0
    //   56: invokevirtual 73	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   59: aastore
    //   60: invokestatic 79	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   63: astore_1
    //   64: aload_1
    //   65: iconst_1
    //   66: aload_2
    //   67: invokestatic 83	java/lang/Class:forName	(Ljava/lang/String;ZLjava/lang/ClassLoader;)Ljava/lang/Class;
    //   70: astore_1
    //   71: aload_1
    //   72: iconst_0
    //   73: anewarray 15	java/lang/Class
    //   76: invokevirtual 87	java/lang/Class:getConstructor	([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;
    //   79: astore_1
    //   80: aload_1
    //   81: iconst_0
    //   82: anewarray 5	java/lang/Object
    //   85: invokevirtual 92	java/lang/reflect/Constructor:newInstance	([Ljava/lang/Object;)Ljava/lang/Object;
    //   88: astore_1
    //   89: aload_1
    //   90: checkcast 2	com/google/android/android/internal/measurement/zzvk
    //   93: astore_1
    //   94: aload_0
    //   95: aload_1
    //   96: invokevirtual 96	com/google/android/android/internal/measurement/zzvk:zzwa	()Lcom/google/android/android/internal/measurement/zzuz;
    //   99: invokevirtual 100	java/lang/Class:cast	(Ljava/lang/Object;)Ljava/lang/Object;
    //   102: astore_1
    //   103: aload_1
    //   104: checkcast 102	com/google/android/android/internal/measurement/zzuz
    //   107: areturn
    //   108: astore_1
    //   109: new 104	java/lang/IllegalStateException
    //   112: dup
    //   113: aload_1
    //   114: invokespecial 107	java/lang/IllegalStateException:<init>	(Ljava/lang/Throwable;)V
    //   117: astore_1
    //   118: aload_1
    //   119: athrow
    //   120: astore_1
    //   121: new 104	java/lang/IllegalStateException
    //   124: dup
    //   125: aload_1
    //   126: invokespecial 107	java/lang/IllegalStateException:<init>	(Ljava/lang/Throwable;)V
    //   129: astore_1
    //   130: aload_1
    //   131: athrow
    //   132: astore_1
    //   133: new 104	java/lang/IllegalStateException
    //   136: dup
    //   137: aload_1
    //   138: invokespecial 107	java/lang/IllegalStateException:<init>	(Ljava/lang/Throwable;)V
    //   141: astore_1
    //   142: aload_1
    //   143: athrow
    //   144: astore_1
    //   145: new 104	java/lang/IllegalStateException
    //   148: dup
    //   149: aload_1
    //   150: invokespecial 107	java/lang/IllegalStateException:<init>	(Ljava/lang/Throwable;)V
    //   153: astore_1
    //   154: aload_1
    //   155: athrow
    //   156: ldc 51
    //   158: aload_2
    //   159: invokestatic 113	java/util/ServiceLoader:load	(Ljava/lang/Class;Ljava/lang/ClassLoader;)Ljava/util/ServiceLoader;
    //   162: invokevirtual 117	java/util/ServiceLoader:iterator	()Ljava/util/Iterator;
    //   165: astore_3
    //   166: new 119	java/util/ArrayList
    //   169: dup
    //   170: invokespecial 120	java/util/ArrayList:<init>	()V
    //   173: astore_2
    //   174: aload_3
    //   175: invokeinterface 126 1 0
    //   180: ifeq +102 -> 282
    //   183: aload_3
    //   184: invokeinterface 130 1 0
    //   189: astore_1
    //   190: aload_1
    //   191: checkcast 2	com/google/android/android/internal/measurement/zzvk
    //   194: astore_1
    //   195: aload_0
    //   196: aload_1
    //   197: invokevirtual 96	com/google/android/android/internal/measurement/zzvk:zzwa	()Lcom/google/android/android/internal/measurement/zzuz;
    //   200: invokevirtual 100	java/lang/Class:cast	(Ljava/lang/Object;)Ljava/lang/Object;
    //   203: astore_1
    //   204: aload_1
    //   205: checkcast 102	com/google/android/android/internal/measurement/zzuz
    //   208: astore_1
    //   209: aload_2
    //   210: aload_1
    //   211: invokevirtual 133	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   214: pop
    //   215: goto -41 -> 174
    //   218: astore 4
    //   220: getstatic 27	com/google/android/android/internal/measurement/zzvk:logger	Ljava/util/logging/Logger;
    //   223: astore 5
    //   225: getstatic 139	java/util/logging/Level:SEVERE	Ljava/util/logging/Level;
    //   228: astore 6
    //   230: aload_0
    //   231: invokevirtual 73	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   234: invokestatic 143	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   237: astore_1
    //   238: aload_1
    //   239: invokevirtual 147	java/lang/String:length	()I
    //   242: ifeq +13 -> 255
    //   245: ldc -107
    //   247: aload_1
    //   248: invokevirtual 153	java/lang/String:concat	(Ljava/lang/String;)Ljava/lang/String;
    //   251: astore_1
    //   252: goto +13 -> 265
    //   255: new 75	java/lang/String
    //   258: dup
    //   259: ldc -107
    //   261: invokespecial 156	java/lang/String:<init>	(Ljava/lang/String;)V
    //   264: astore_1
    //   265: aload 5
    //   267: aload 6
    //   269: ldc -98
    //   271: ldc -97
    //   273: aload_1
    //   274: aload 4
    //   276: invokevirtual 163	java/util/logging/Logger:logp	(Ljava/util/logging/Level;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   279: goto -105 -> 174
    //   282: aload_2
    //   283: invokevirtual 166	java/util/ArrayList:size	()I
    //   286: iconst_1
    //   287: if_icmpne +12 -> 299
    //   290: aload_2
    //   291: iconst_0
    //   292: invokevirtual 170	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   295: checkcast 102	com/google/android/android/internal/measurement/zzuz
    //   298: areturn
    //   299: aload_2
    //   300: invokevirtual 166	java/util/ArrayList:size	()I
    //   303: ifne +5 -> 308
    //   306: aconst_null
    //   307: areturn
    //   308: aload_0
    //   309: ldc -84
    //   311: iconst_1
    //   312: anewarray 15	java/lang/Class
    //   315: dup
    //   316: iconst_0
    //   317: ldc -82
    //   319: aastore
    //   320: invokevirtual 178	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   323: astore_0
    //   324: aload_0
    //   325: aconst_null
    //   326: iconst_1
    //   327: anewarray 5	java/lang/Object
    //   330: dup
    //   331: iconst_0
    //   332: aload_2
    //   333: aastore
    //   334: invokevirtual 184	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   337: astore_0
    //   338: aload_0
    //   339: checkcast 102	com/google/android/android/internal/measurement/zzuz
    //   342: areturn
    //   343: astore_0
    //   344: new 104	java/lang/IllegalStateException
    //   347: dup
    //   348: aload_0
    //   349: invokespecial 107	java/lang/IllegalStateException:<init>	(Ljava/lang/Throwable;)V
    //   352: athrow
    //   353: astore_0
    //   354: new 104	java/lang/IllegalStateException
    //   357: dup
    //   358: aload_0
    //   359: invokespecial 107	java/lang/IllegalStateException:<init>	(Ljava/lang/Throwable;)V
    //   362: athrow
    //   363: astore_0
    //   364: new 104	java/lang/IllegalStateException
    //   367: dup
    //   368: aload_0
    //   369: invokespecial 107	java/lang/IllegalStateException:<init>	(Ljava/lang/Throwable;)V
    //   372: athrow
    //   373: new 186	java/lang/IllegalArgumentException
    //   376: dup
    //   377: aload_0
    //   378: invokevirtual 19	java/lang/Class:getName	()Ljava/lang/String;
    //   381: invokespecial 187	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   384: athrow
    //   385: astore_1
    //   386: goto -230 -> 156
    //   389: astore_1
    //   390: goto -234 -> 156
    //   393: astore_1
    //   394: goto -238 -> 156
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	397	0	paramClass	Class
    //   18	86	1	localObject1	Object
    //   108	6	1	localInvocationTargetException	java.lang.reflect.InvocationTargetException
    //   117	2	1	localIllegalStateException1	IllegalStateException
    //   120	6	1	localIllegalAccessException	IllegalAccessException
    //   129	2	1	localIllegalStateException2	IllegalStateException
    //   132	6	1	localInstantiationException	InstantiationException
    //   141	2	1	localIllegalStateException3	IllegalStateException
    //   144	6	1	localNoSuchMethodException	NoSuchMethodException
    //   153	121	1	localObject2	Object
    //   385	1	1	localClassNotFoundException1	ClassNotFoundException
    //   389	1	1	localClassNotFoundException2	ClassNotFoundException
    //   393	1	1	localClassNotFoundException3	ClassNotFoundException
    //   5	328	2	localObject3	Object
    //   165	19	3	localIterator	java.util.Iterator
    //   218	57	4	localServiceConfigurationError	java.util.ServiceConfigurationError
    //   223	43	5	localLogger	Logger
    //   228	40	6	localLevel	java.util.logging.Level
    // Exception table:
    //   from	to	target	type
    //   71	80	108	java/lang/reflect/InvocationTargetException
    //   80	89	108	java/lang/reflect/InvocationTargetException
    //   71	80	120	java/lang/IllegalAccessException
    //   80	89	120	java/lang/IllegalAccessException
    //   71	80	132	java/lang/InstantiationException
    //   80	89	132	java/lang/InstantiationException
    //   71	80	144	java/lang/NoSuchMethodException
    //   80	89	144	java/lang/NoSuchMethodException
    //   183	190	218	java/util/ServiceConfigurationError
    //   195	204	218	java/util/ServiceConfigurationError
    //   209	215	218	java/util/ServiceConfigurationError
    //   308	324	343	java/lang/reflect/InvocationTargetException
    //   324	338	343	java/lang/reflect/InvocationTargetException
    //   308	324	353	java/lang/IllegalAccessException
    //   324	338	353	java/lang/IllegalAccessException
    //   308	324	363	java/lang/NoSuchMethodException
    //   324	338	363	java/lang/NoSuchMethodException
    //   64	71	385	java/lang/ClassNotFoundException
    //   71	80	389	java/lang/ClassNotFoundException
    //   80	89	389	java/lang/ClassNotFoundException
    //   94	103	393	java/lang/ClassNotFoundException
    //   109	118	393	java/lang/ClassNotFoundException
    //   121	130	393	java/lang/ClassNotFoundException
    //   133	142	393	java/lang/ClassNotFoundException
    //   145	154	393	java/lang/ClassNotFoundException
  }
  
  protected abstract zzuz zzwa();
}
