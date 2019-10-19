package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

final class TypeAdapterRuntimeTypeWrapper<T>
  extends TypeAdapter<T>
{
  private final Gson context;
  private final TypeAdapter<T> delegate;
  private final Type type;
  
  TypeAdapterRuntimeTypeWrapper(Gson paramGson, TypeAdapter paramTypeAdapter, Type paramType)
  {
    context = paramGson;
    delegate = paramTypeAdapter;
    type = paramType;
  }
  
  private Type getRuntimeTypeIfMoreSpecific(Type paramType, Object paramObject)
  {
    if ((paramObject != null) && ((paramType == Object.class) || ((paramType instanceof TypeVariable)) || ((paramType instanceof Class)))) {
      return paramObject.getClass();
    }
    return paramType;
  }
  
  public Object read(JsonReader paramJsonReader)
    throws IOException
  {
    return delegate.read(paramJsonReader);
  }
  
  public void write(JsonWriter paramJsonWriter, Object paramObject)
    throws IOException
  {
    Object localObject1 = delegate;
    Object localObject2 = getRuntimeTypeIfMoreSpecific(type, paramObject);
    if (localObject2 != type)
    {
      localObject2 = context.getAdapter(TypeToken.get((Type)localObject2));
      localObject1 = localObject2;
      if (((localObject2 instanceof ReflectiveTypeAdapterFactory.Adapter)) && (!(delegate instanceof ReflectiveTypeAdapterFactory.Adapter))) {
        localObject1 = delegate;
      }
    }
    ((TypeAdapter)localObject1).write(paramJsonWriter, paramObject);
  }
}
