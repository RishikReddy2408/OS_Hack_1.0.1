package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal..Gson.Types;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.JsonReaderInternalAccess;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.internal.Streams;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class MapTypeAdapterFactory
  implements TypeAdapterFactory
{
  final boolean complexMapKeySerialization;
  private final ConstructorConstructor constructorConstructor;
  
  public MapTypeAdapterFactory(ConstructorConstructor paramConstructorConstructor, boolean paramBoolean)
  {
    constructorConstructor = paramConstructorConstructor;
    complexMapKeySerialization = paramBoolean;
  }
  
  private TypeAdapter getKeyAdapter(Gson paramGson, Type paramType)
  {
    if ((paramType != Boolean.TYPE) && (paramType != Boolean.class)) {
      return paramGson.getAdapter(TypeToken.get(paramType));
    }
    return TypeAdapters.BOOLEAN_AS_STRING;
  }
  
  public TypeAdapter create(Gson paramGson, TypeToken paramTypeToken)
  {
    Object localObject = paramTypeToken.getType();
    if (!Map.class.isAssignableFrom(paramTypeToken.getRawType())) {
      return null;
    }
    localObject = .Gson.Types.getMapKeyAndValueTypes((Type)localObject, .Gson.Types.getRawType((Type)localObject));
    TypeAdapter localTypeAdapter1 = getKeyAdapter(paramGson, localObject[0]);
    TypeAdapter localTypeAdapter2 = paramGson.getAdapter(TypeToken.get(localObject[1]));
    paramTypeToken = constructorConstructor.get(paramTypeToken);
    return new Adapter(paramGson, localObject[0], localTypeAdapter1, localObject[1], localTypeAdapter2, paramTypeToken);
  }
  
  private final class Adapter<K, V>
    extends TypeAdapter<Map<K, V>>
  {
    private final ObjectConstructor<? extends Map<K, V>> constructor;
    private final TypeAdapter<K> keyTypeAdapter;
    private final TypeAdapter<V> valueTypeAdapter;
    
    public Adapter(Gson paramGson, Type paramType1, TypeAdapter paramTypeAdapter1, Type paramType2, TypeAdapter paramTypeAdapter2, ObjectConstructor paramObjectConstructor)
    {
      keyTypeAdapter = new TypeAdapterRuntimeTypeWrapper(paramGson, paramTypeAdapter1, paramType1);
      valueTypeAdapter = new TypeAdapterRuntimeTypeWrapper(paramGson, paramTypeAdapter2, paramType2);
      constructor = paramObjectConstructor;
    }
    
    private String keyToString(JsonElement paramJsonElement)
    {
      if (paramJsonElement.isJsonPrimitive())
      {
        paramJsonElement = paramJsonElement.getAsJsonPrimitive();
        if (paramJsonElement.isNumber()) {
          return String.valueOf(paramJsonElement.getAsNumber());
        }
        if (paramJsonElement.isBoolean()) {
          return Boolean.toString(paramJsonElement.getAsBoolean());
        }
        if (paramJsonElement.isString()) {
          return paramJsonElement.getAsString();
        }
        throw new AssertionError();
      }
      if (paramJsonElement.isJsonNull()) {
        return "null";
      }
      throw new AssertionError();
    }
    
    public Map read(JsonReader paramJsonReader)
      throws IOException
    {
      Object localObject = paramJsonReader.peek();
      if (localObject == JsonToken.NULL)
      {
        paramJsonReader.nextNull();
        return null;
      }
      Map localMap = (Map)constructor.construct();
      if (localObject == JsonToken.BEGIN_ARRAY)
      {
        paramJsonReader.beginArray();
        while (paramJsonReader.hasNext())
        {
          paramJsonReader.beginArray();
          localObject = keyTypeAdapter.read(paramJsonReader);
          if (localMap.put(localObject, valueTypeAdapter.read(paramJsonReader)) == null)
          {
            paramJsonReader.endArray();
          }
          else
          {
            paramJsonReader = new StringBuilder();
            paramJsonReader.append("duplicate key: ");
            paramJsonReader.append(localObject);
            throw new JsonSyntaxException(paramJsonReader.toString());
          }
        }
        paramJsonReader.endArray();
        return localMap;
      }
      paramJsonReader.beginObject();
      while (paramJsonReader.hasNext())
      {
        JsonReaderInternalAccess.INSTANCE.promoteNameToValue(paramJsonReader);
        localObject = keyTypeAdapter.read(paramJsonReader);
        if (localMap.put(localObject, valueTypeAdapter.read(paramJsonReader)) != null)
        {
          paramJsonReader = new StringBuilder();
          paramJsonReader.append("duplicate key: ");
          paramJsonReader.append(localObject);
          throw new JsonSyntaxException(paramJsonReader.toString());
        }
      }
      paramJsonReader.endObject();
      return localMap;
    }
    
    public void write(JsonWriter paramJsonWriter, Map paramMap)
      throws IOException
    {
      if (paramMap == null)
      {
        paramJsonWriter.nullValue();
        return;
      }
      if (!complexMapKeySerialization)
      {
        paramJsonWriter.beginObject();
        paramMap = paramMap.entrySet().iterator();
        while (paramMap.hasNext())
        {
          localObject = (Map.Entry)paramMap.next();
          paramJsonWriter.name(String.valueOf(((Map.Entry)localObject).getKey()));
          valueTypeAdapter.write(paramJsonWriter, ((Map.Entry)localObject).getValue());
        }
        paramJsonWriter.endObject();
        return;
      }
      Object localObject = new ArrayList(paramMap.size());
      ArrayList localArrayList = new ArrayList(paramMap.size());
      paramMap = paramMap.entrySet().iterator();
      int m = 0;
      int k = 0;
      int i = 0;
      while (paramMap.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramMap.next();
        JsonElement localJsonElement = keyTypeAdapter.toJsonTree(localEntry.getKey());
        ((List)localObject).add(localJsonElement);
        localArrayList.add(localEntry.getValue());
        if ((!localJsonElement.isJsonArray()) && (!localJsonElement.isJsonObject())) {
          j = 0;
        } else {
          j = 1;
        }
        i |= j;
      }
      if (i != 0)
      {
        paramJsonWriter.beginArray();
        j = ((List)localObject).size();
        i = k;
        while (i < j)
        {
          paramJsonWriter.beginArray();
          Streams.write((JsonElement)((List)localObject).get(i), paramJsonWriter);
          valueTypeAdapter.write(paramJsonWriter, localArrayList.get(i));
          paramJsonWriter.endArray();
          i += 1;
        }
        paramJsonWriter.endArray();
        return;
      }
      paramJsonWriter.beginObject();
      int j = ((List)localObject).size();
      i = m;
      while (i < j)
      {
        paramJsonWriter.name(keyToString((JsonElement)((List)localObject).get(i)));
        valueTypeAdapter.write(paramJsonWriter, localArrayList.get(i));
        i += 1;
      }
      paramJsonWriter.endObject();
    }
  }
}
