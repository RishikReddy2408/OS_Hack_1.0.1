package com.google.gson.internal.bind;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal..Gson.Types;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.internal.Primitives;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class ReflectiveTypeAdapterFactory
  implements TypeAdapterFactory
{
  private final ConstructorConstructor constructorConstructor;
  private final Excluder excluder;
  private final FieldNamingStrategy fieldNamingPolicy;
  private final JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory;
  
  public ReflectiveTypeAdapterFactory(ConstructorConstructor paramConstructorConstructor, FieldNamingStrategy paramFieldNamingStrategy, Excluder paramExcluder, JsonAdapterAnnotationTypeAdapterFactory paramJsonAdapterAnnotationTypeAdapterFactory)
  {
    constructorConstructor = paramConstructorConstructor;
    fieldNamingPolicy = paramFieldNamingStrategy;
    excluder = paramExcluder;
    jsonAdapterFactory = paramJsonAdapterAnnotationTypeAdapterFactory;
  }
  
  private BoundField createBoundField(final Gson paramGson, final Field paramField, String paramString, final TypeToken paramTypeToken, boolean paramBoolean1, boolean paramBoolean2)
  {
    final boolean bool2 = Primitives.isPrimitive(paramTypeToken.getRawType());
    Object localObject1 = (JsonAdapter)paramField.getAnnotation(JsonAdapter.class);
    if (localObject1 != null) {
      localObject1 = jsonAdapterFactory.getTypeAdapter(constructorConstructor, paramGson, paramTypeToken, (JsonAdapter)localObject1);
    } else {
      localObject1 = null;
    }
    final boolean bool1;
    if (localObject1 != null) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    Object localObject2 = localObject1;
    if (localObject1 == null) {
      localObject2 = paramGson.getAdapter(paramTypeToken);
    }
    new BoundField(paramString, paramBoolean1, paramBoolean2)
    {
      void read(JsonReader paramAnonymousJsonReader, Object paramAnonymousObject)
        throws IOException, IllegalAccessException
      {
        paramAnonymousJsonReader = val$typeAdapter.read(paramAnonymousJsonReader);
        if ((paramAnonymousJsonReader != null) || (!bool2)) {
          paramField.set(paramAnonymousObject, paramAnonymousJsonReader);
        }
      }
      
      void write(JsonWriter paramAnonymousJsonWriter, Object paramAnonymousObject)
        throws IOException, IllegalAccessException
      {
        throw new Runtime("d2j fail translate: java.lang.RuntimeException: fail exe a6 = a5\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:92)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.dfs(Cfg.java:255)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze0(BaseAnalyze.java:75)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze(BaseAnalyze.java:69)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer.transform(UnSSATransformer.java:274)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:163)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\nCaused by: java.lang.NullPointerException\n");
      }
      
      public boolean writeField(Object paramAnonymousObject)
        throws IOException, IllegalAccessException
      {
        if (!serialized) {
          return false;
        }
        return paramField.get(paramAnonymousObject) != paramAnonymousObject;
      }
    };
  }
  
  static boolean excludeField(Field paramField, boolean paramBoolean, Excluder paramExcluder)
  {
    return (!paramExcluder.excludeClass(paramField.getType(), paramBoolean)) && (!paramExcluder.excludeField(paramField, paramBoolean));
  }
  
  private Map getBoundFields(Gson paramGson, TypeToken paramTypeToken, Class paramClass)
  {
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    if (paramClass.isInterface()) {
      return localLinkedHashMap;
    }
    Type localType1 = paramTypeToken.getType();
    while (paramClass != Object.class)
    {
      Field[] arrayOfField = paramClass.getDeclaredFields();
      int k = arrayOfField.length;
      int i = 0;
      while (i < k)
      {
        Field localField = arrayOfField[i];
        boolean bool2 = excludeField(localField, true);
        boolean bool1 = bool2;
        boolean bool3 = excludeField(localField, false);
        Object localObject1;
        if ((bool2) || (bool3))
        {
          localField.setAccessible(true);
          Type localType2 = .Gson.Types.resolve(paramTypeToken.getType(), paramClass, localField.getGenericType());
          localObject1 = getFieldNames(localField);
          Object localObject2 = localObject1;
          int m = ((List)localObject1).size();
          localObject1 = null;
          int j = 0;
          while (j < m)
          {
            Object localObject3 = (String)localObject2.get(j);
            if (j != 0) {
              bool1 = false;
            }
            Object localObject4 = localLinkedHashMap.put(localObject3, createBoundField(paramGson, localField, (String)localObject3, TypeToken.get(localType2), bool1, bool3));
            localObject3 = localObject1;
            if (localObject1 == null) {
              localObject3 = (BoundField)localObject4;
            }
            j += 1;
            localObject1 = localObject3;
          }
          if (localObject1 != null) {}
        }
        else
        {
          i += 1;
          continue;
        }
        paramGson = new StringBuilder();
        paramGson.append(localType1);
        paramGson.append(" declares multiple JSON fields named ");
        paramGson.append(name);
        throw new IllegalArgumentException(paramGson.toString());
      }
      paramClass = TypeToken.get(.Gson.Types.resolve(paramTypeToken.getType(), paramClass, paramClass.getGenericSuperclass()));
      paramTypeToken = paramClass;
      paramClass = paramClass.getRawType();
    }
    return localLinkedHashMap;
  }
  
  private List getFieldNames(Field paramField)
  {
    Object localObject = (SerializedName)paramField.getAnnotation(SerializedName.class);
    if (localObject == null) {
      return Collections.singletonList(fieldNamingPolicy.translateName(paramField));
    }
    paramField = ((SerializedName)localObject).value();
    localObject = ((SerializedName)localObject).alternate();
    if (localObject.length == 0) {
      return Collections.singletonList(paramField);
    }
    ArrayList localArrayList = new ArrayList(localObject.length + 1);
    localArrayList.add(paramField);
    int j = localObject.length;
    int i = 0;
    while (i < j)
    {
      localArrayList.add(localObject[i]);
      i += 1;
    }
    return localArrayList;
  }
  
  public TypeAdapter create(Gson paramGson, TypeToken paramTypeToken)
  {
    Class localClass = paramTypeToken.getRawType();
    if (!Object.class.isAssignableFrom(localClass)) {
      return null;
    }
    return new Adapter(constructorConstructor.get(paramTypeToken), getBoundFields(paramGson, paramTypeToken, localClass));
  }
  
  public boolean excludeField(Field paramField, boolean paramBoolean)
  {
    return excludeField(paramField, paramBoolean, excluder);
  }
  
  public static final class Adapter<T>
    extends TypeAdapter<T>
  {
    private final Map<String, ReflectiveTypeAdapterFactory.BoundField> boundFields;
    private final ObjectConstructor<T> constructor;
    
    Adapter(ObjectConstructor paramObjectConstructor, Map paramMap)
    {
      constructor = paramObjectConstructor;
      boundFields = paramMap;
    }
    
    public Object read(JsonReader paramJsonReader)
      throws IOException
    {
      if (paramJsonReader.peek() == JsonToken.NULL)
      {
        paramJsonReader.nextNull();
        return null;
      }
      Object localObject1 = constructor.construct();
      try
      {
        paramJsonReader.beginObject();
        for (;;)
        {
          boolean bool = paramJsonReader.hasNext();
          if (!bool) {
            break;
          }
          Object localObject2 = paramJsonReader.nextName();
          Map localMap = boundFields;
          localObject2 = localMap.get(localObject2);
          localObject2 = (ReflectiveTypeAdapterFactory.BoundField)localObject2;
          if ((localObject2 != null) && (deserialized)) {
            ((ReflectiveTypeAdapterFactory.BoundField)localObject2).read(paramJsonReader, localObject1);
          } else {
            paramJsonReader.skipValue();
          }
        }
        paramJsonReader.endObject();
        return localObject1;
      }
      catch (IllegalAccessException paramJsonReader)
      {
        throw new AssertionError(paramJsonReader);
      }
      catch (IllegalStateException paramJsonReader)
      {
        throw new JsonSyntaxException(paramJsonReader);
      }
    }
    
    public void write(JsonWriter paramJsonWriter, Object paramObject)
      throws IOException
    {
      if (paramObject == null)
      {
        paramJsonWriter.nullValue();
        return;
      }
      paramJsonWriter.beginObject();
      Object localObject1 = boundFields;
      try
      {
        localObject1 = ((Map)localObject1).values().iterator();
        for (;;)
        {
          boolean bool = ((Iterator)localObject1).hasNext();
          if (!bool) {
            break;
          }
          Object localObject2 = ((Iterator)localObject1).next();
          localObject2 = (ReflectiveTypeAdapterFactory.BoundField)localObject2;
          bool = ((ReflectiveTypeAdapterFactory.BoundField)localObject2).writeField(paramObject);
          if (bool)
          {
            String str = name;
            paramJsonWriter.name(str);
            ((ReflectiveTypeAdapterFactory.BoundField)localObject2).write(paramJsonWriter, paramObject);
          }
        }
        paramJsonWriter.endObject();
        return;
      }
      catch (IllegalAccessException paramJsonWriter)
      {
        throw new AssertionError(paramJsonWriter);
      }
    }
  }
  
  static abstract class BoundField
  {
    final boolean deserialized;
    final String name;
    final boolean serialized;
    
    protected BoundField(String paramString, boolean paramBoolean1, boolean paramBoolean2)
    {
      name = paramString;
      serialized = paramBoolean1;
      deserialized = paramBoolean2;
    }
    
    abstract void read(JsonReader paramJsonReader, Object paramObject)
      throws IOException, IllegalAccessException;
    
    abstract void write(JsonWriter paramJsonWriter, Object paramObject)
      throws IOException, IllegalAccessException;
    
    abstract boolean writeField(Object paramObject)
      throws IOException, IllegalAccessException;
  }
}
