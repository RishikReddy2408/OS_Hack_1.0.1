package com.google.gson.internal;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class Excluder
  implements TypeAdapterFactory, Cloneable
{
  public static final Excluder DEFAULT = new Excluder();
  private static final double IGNORE_VERSIONS = -1.0D;
  private List<ExclusionStrategy> deserializationStrategies = Collections.emptyList();
  private int modifiers = 136;
  private boolean requireExpose;
  private List<ExclusionStrategy> serializationStrategies = Collections.emptyList();
  private boolean serializeInnerClasses = true;
  private double version = -1.0D;
  
  public Excluder() {}
  
  private boolean isAnonymousOrLocal(Class<?> paramClass)
  {
    return (!Enum.class.isAssignableFrom(paramClass)) && ((paramClass.isAnonymousClass()) || (paramClass.isLocalClass()));
  }
  
  private boolean isInnerClass(Class<?> paramClass)
  {
    return (paramClass.isMemberClass()) && (!isStatic(paramClass));
  }
  
  private boolean isStatic(Class<?> paramClass)
  {
    return (paramClass.getModifiers() & 0x8) != 0;
  }
  
  private boolean isValidSince(Since paramSince)
  {
    return (paramSince == null) || (paramSince.value() <= version);
  }
  
  private boolean isValidUntil(Until paramUntil)
  {
    return (paramUntil == null) || (paramUntil.value() > version);
  }
  
  private boolean isValidVersion(Since paramSince, Until paramUntil)
  {
    return (isValidSince(paramSince)) && (isValidUntil(paramUntil));
  }
  
  protected Excluder clone()
  {
    try
    {
      Excluder localExcluder = (Excluder)super.clone();
      return localExcluder;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      throw new AssertionError(localCloneNotSupportedException);
    }
  }
  
  public <T> TypeAdapter<T> create(final Gson paramGson, final TypeToken<T> paramTypeToken)
  {
    Class localClass = paramTypeToken.getRawType();
    final boolean bool1 = excludeClass(localClass, true);
    final boolean bool2 = excludeClass(localClass, false);
    if ((!bool1) && (!bool2)) {
      return null;
    }
    new TypeAdapter()
    {
      private TypeAdapter<T> delegate;
      
      private TypeAdapter<T> delegate()
      {
        TypeAdapter localTypeAdapter = delegate;
        if (localTypeAdapter != null) {
          return localTypeAdapter;
        }
        localTypeAdapter = paramGson.getDelegateAdapter(Excluder.this, paramTypeToken);
        delegate = localTypeAdapter;
        return localTypeAdapter;
      }
      
      public T read(JsonReader paramAnonymousJsonReader)
        throws IOException
      {
        if (bool2)
        {
          paramAnonymousJsonReader.skipValue();
          return null;
        }
        return delegate().read(paramAnonymousJsonReader);
      }
      
      public void write(JsonWriter paramAnonymousJsonWriter, T paramAnonymousT)
        throws IOException
      {
        if (bool1)
        {
          paramAnonymousJsonWriter.nullValue();
          return;
        }
        delegate().write(paramAnonymousJsonWriter, paramAnonymousT);
      }
    };
  }
  
  public Excluder disableInnerClassSerialization()
  {
    Excluder localExcluder = clone();
    serializeInnerClasses = false;
    return localExcluder;
  }
  
  public boolean excludeClass(Class<?> paramClass, boolean paramBoolean)
  {
    if ((version != -1.0D) && (!isValidVersion((Since)paramClass.getAnnotation(Since.class), (Until)paramClass.getAnnotation(Until.class)))) {
      return true;
    }
    if ((!serializeInnerClasses) && (isInnerClass(paramClass))) {
      return true;
    }
    if (isAnonymousOrLocal(paramClass)) {
      return true;
    }
    if (paramBoolean) {
      localObject = serializationStrategies;
    } else {
      localObject = deserializationStrategies;
    }
    Object localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      if (((ExclusionStrategy)((Iterator)localObject).next()).shouldSkipClass(paramClass)) {
        return true;
      }
    }
    return false;
  }
  
  public boolean excludeField(Field paramField, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 39	com/google/gson/internal/Excluder:modifiers	I
    //   4: aload_1
    //   5: invokevirtual 166	java/lang/reflect/Field:getModifiers	()I
    //   8: iand
    //   9: ifeq +5 -> 14
    //   12: iconst_1
    //   13: ireturn
    //   14: aload_0
    //   15: getfield 37	com/google/gson/internal/Excluder:version	D
    //   18: ldc2_w 15
    //   21: dcmpl
    //   22: ifeq +30 -> 52
    //   25: aload_0
    //   26: aload_1
    //   27: ldc 84
    //   29: invokevirtual 167	java/lang/reflect/Field:getAnnotation	(Ljava/lang/Class;)Ljava/lang/annotation/Annotation;
    //   32: checkcast 84	com/google/gson/annotations/Since
    //   35: aload_1
    //   36: ldc 92
    //   38: invokevirtual 167	java/lang/reflect/Field:getAnnotation	(Ljava/lang/Class;)Ljava/lang/annotation/Annotation;
    //   41: checkcast 92	com/google/gson/annotations/Until
    //   44: invokespecial 137	com/google/gson/internal/Excluder:isValidVersion	(Lcom/google/gson/annotations/Since;Lcom/google/gson/annotations/Until;)Z
    //   47: ifne +5 -> 52
    //   50: iconst_1
    //   51: ireturn
    //   52: aload_1
    //   53: invokevirtual 170	java/lang/reflect/Field:isSynthetic	()Z
    //   56: ifeq +5 -> 61
    //   59: iconst_1
    //   60: ireturn
    //   61: aload_0
    //   62: getfield 172	com/google/gson/internal/Excluder:requireExpose	Z
    //   65: ifeq +43 -> 108
    //   68: aload_1
    //   69: ldc -82
    //   71: invokevirtual 167	java/lang/reflect/Field:getAnnotation	(Ljava/lang/Class;)Ljava/lang/annotation/Annotation;
    //   74: checkcast 174	com/google/gson/annotations/Expose
    //   77: astore_3
    //   78: aload_3
    //   79: ifnull +27 -> 106
    //   82: iload_2
    //   83: ifeq +14 -> 97
    //   86: aload_3
    //   87: invokeinterface 177 1 0
    //   92: ifne +16 -> 108
    //   95: iconst_1
    //   96: ireturn
    //   97: aload_3
    //   98: invokeinterface 180 1 0
    //   103: ifne +5 -> 108
    //   106: iconst_1
    //   107: ireturn
    //   108: aload_0
    //   109: getfield 41	com/google/gson/internal/Excluder:serializeInnerClasses	Z
    //   112: ifne +16 -> 128
    //   115: aload_0
    //   116: aload_1
    //   117: invokevirtual 183	java/lang/reflect/Field:getType	()Ljava/lang/Class;
    //   120: invokespecial 139	com/google/gson/internal/Excluder:isInnerClass	(Ljava/lang/Class;)Z
    //   123: ifeq +5 -> 128
    //   126: iconst_1
    //   127: ireturn
    //   128: aload_0
    //   129: aload_1
    //   130: invokevirtual 183	java/lang/reflect/Field:getType	()Ljava/lang/Class;
    //   133: invokespecial 141	com/google/gson/internal/Excluder:isAnonymousOrLocal	(Ljava/lang/Class;)Z
    //   136: ifeq +5 -> 141
    //   139: iconst_1
    //   140: ireturn
    //   141: iload_2
    //   142: ifeq +11 -> 153
    //   145: aload_0
    //   146: getfield 49	com/google/gson/internal/Excluder:serializationStrategies	Ljava/util/List;
    //   149: astore_3
    //   150: goto +8 -> 158
    //   153: aload_0
    //   154: getfield 51	com/google/gson/internal/Excluder:deserializationStrategies	Ljava/util/List;
    //   157: astore_3
    //   158: aload_3
    //   159: invokeinterface 186 1 0
    //   164: ifne +48 -> 212
    //   167: new 188	com/google/gson/FieldAttributes
    //   170: dup
    //   171: aload_1
    //   172: invokespecial 191	com/google/gson/FieldAttributes:<init>	(Ljava/lang/reflect/Field;)V
    //   175: astore_1
    //   176: aload_3
    //   177: invokeinterface 147 1 0
    //   182: astore_3
    //   183: aload_3
    //   184: invokeinterface 152 1 0
    //   189: ifeq +23 -> 212
    //   192: aload_3
    //   193: invokeinterface 155 1 0
    //   198: checkcast 157	com/google/gson/ExclusionStrategy
    //   201: aload_1
    //   202: invokeinterface 195 2 0
    //   207: ifeq -24 -> 183
    //   210: iconst_1
    //   211: ireturn
    //   212: iconst_0
    //   213: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	214	0	this	Excluder
    //   0	214	1	paramField	Field
    //   0	214	2	paramBoolean	boolean
    //   77	116	3	localObject	Object
  }
  
  public Excluder excludeFieldsWithoutExposeAnnotation()
  {
    Excluder localExcluder = clone();
    requireExpose = true;
    return localExcluder;
  }
  
  public Excluder withExclusionStrategy(ExclusionStrategy paramExclusionStrategy, boolean paramBoolean1, boolean paramBoolean2)
  {
    Excluder localExcluder = clone();
    if (paramBoolean1)
    {
      serializationStrategies = new ArrayList(serializationStrategies);
      serializationStrategies.add(paramExclusionStrategy);
    }
    if (paramBoolean2)
    {
      deserializationStrategies = new ArrayList(deserializationStrategies);
      deserializationStrategies.add(paramExclusionStrategy);
    }
    return localExcluder;
  }
  
  public Excluder withModifiers(int... paramVarArgs)
  {
    Excluder localExcluder = clone();
    int i = 0;
    modifiers = 0;
    int j = paramVarArgs.length;
    while (i < j)
    {
      modifiers = (paramVarArgs[i] | modifiers);
      i += 1;
    }
    return localExcluder;
  }
  
  public Excluder withVersion(double paramDouble)
  {
    Excluder localExcluder = clone();
    version = paramDouble;
    return localExcluder;
  }
}
