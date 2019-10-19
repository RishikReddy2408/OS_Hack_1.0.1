package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal..Gson.Preconditions;
import com.google.gson.internal.Streams;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;

public final class TreeTypeAdapter<T>
  extends TypeAdapter<T>
{
  private final TreeTypeAdapter<T>.GsonContextImpl context = new GsonContextImpl(null);
  private TypeAdapter<T> delegate;
  private final JsonDeserializer<T> deserializer;
  final Gson gson;
  private final JsonSerializer<T> serializer;
  private final TypeAdapterFactory skipPast;
  private final TypeToken<T> typeToken;
  
  public TreeTypeAdapter(JsonSerializer<T> paramJsonSerializer, JsonDeserializer<T> paramJsonDeserializer, Gson paramGson, TypeToken<T> paramTypeToken, TypeAdapterFactory paramTypeAdapterFactory)
  {
    serializer = paramJsonSerializer;
    deserializer = paramJsonDeserializer;
    gson = paramGson;
    typeToken = paramTypeToken;
    skipPast = paramTypeAdapterFactory;
  }
  
  private TypeAdapter<T> delegate()
  {
    TypeAdapter localTypeAdapter = delegate;
    if (localTypeAdapter != null) {
      return localTypeAdapter;
    }
    localTypeAdapter = gson.getDelegateAdapter(skipPast, typeToken);
    delegate = localTypeAdapter;
    return localTypeAdapter;
  }
  
  public static TypeAdapterFactory newFactory(TypeToken<?> paramTypeToken, Object paramObject)
  {
    return new SingleTypeFactory(paramObject, paramTypeToken, false, null);
  }
  
  public static TypeAdapterFactory newFactoryWithMatchRawType(TypeToken<?> paramTypeToken, Object paramObject)
  {
    boolean bool;
    if (paramTypeToken.getType() == paramTypeToken.getRawType()) {
      bool = true;
    } else {
      bool = false;
    }
    return new SingleTypeFactory(paramObject, paramTypeToken, bool, null);
  }
  
  public static TypeAdapterFactory newTypeHierarchyFactory(Class<?> paramClass, Object paramObject)
  {
    return new SingleTypeFactory(paramObject, null, false, paramClass);
  }
  
  public T read(JsonReader paramJsonReader)
    throws IOException
  {
    if (deserializer == null) {
      return delegate().read(paramJsonReader);
    }
    paramJsonReader = Streams.parse(paramJsonReader);
    if (paramJsonReader.isJsonNull()) {
      return null;
    }
    return deserializer.deserialize(paramJsonReader, typeToken.getType(), context);
  }
  
  public void write(JsonWriter paramJsonWriter, T paramT)
    throws IOException
  {
    if (serializer == null)
    {
      delegate().write(paramJsonWriter, paramT);
      return;
    }
    if (paramT == null)
    {
      paramJsonWriter.nullValue();
      return;
    }
    Streams.write(serializer.serialize(paramT, typeToken.getType(), context), paramJsonWriter);
  }
  
  private final class GsonContextImpl
    implements JsonSerializationContext, JsonDeserializationContext
  {
    private GsonContextImpl() {}
    
    public <R> R deserialize(JsonElement paramJsonElement, Type paramType)
      throws JsonParseException
    {
      return gson.fromJson(paramJsonElement, paramType);
    }
    
    public JsonElement serialize(Object paramObject)
    {
      return gson.toJsonTree(paramObject);
    }
    
    public JsonElement serialize(Object paramObject, Type paramType)
    {
      return gson.toJsonTree(paramObject, paramType);
    }
  }
  
  private static final class SingleTypeFactory
    implements TypeAdapterFactory
  {
    private final JsonDeserializer<?> deserializer;
    private final TypeToken<?> exactType;
    private final Class<?> hierarchyType;
    private final boolean matchRawType;
    private final JsonSerializer<?> serializer;
    
    SingleTypeFactory(Object paramObject, TypeToken<?> paramTypeToken, boolean paramBoolean, Class<?> paramClass)
    {
      boolean bool = paramObject instanceof JsonSerializer;
      Object localObject2 = null;
      if (bool) {
        localObject1 = (JsonSerializer)paramObject;
      } else {
        localObject1 = null;
      }
      serializer = ((JsonSerializer)localObject1);
      Object localObject1 = localObject2;
      if ((paramObject instanceof JsonDeserializer)) {
        localObject1 = (JsonDeserializer)paramObject;
      }
      deserializer = ((JsonDeserializer)localObject1);
      if ((serializer == null) && (deserializer == null)) {
        bool = false;
      } else {
        bool = true;
      }
      .Gson.Preconditions.checkArgument(bool);
      exactType = paramTypeToken;
      matchRawType = paramBoolean;
      hierarchyType = paramClass;
    }
    
    public <T> TypeAdapter<T> create(Gson paramGson, TypeToken<T> paramTypeToken)
    {
      boolean bool;
      if (exactType != null)
      {
        if ((!exactType.equals(paramTypeToken)) && ((!matchRawType) || (exactType.getType() != paramTypeToken.getRawType()))) {
          bool = false;
        } else {
          bool = true;
        }
      }
      else {
        bool = hierarchyType.isAssignableFrom(paramTypeToken.getRawType());
      }
      if (bool) {
        return new TreeTypeAdapter(serializer, deserializer, paramGson, paramTypeToken, this);
      }
      return null;
    }
  }
}
