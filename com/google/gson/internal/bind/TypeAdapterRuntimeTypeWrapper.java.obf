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
  
  TypeAdapterRuntimeTypeWrapper(Gson paramGson, TypeAdapter<T> paramTypeAdapter, Type paramType)
  {
    context = paramGson;
    delegate = paramTypeAdapter;
    type = paramType;
  }
  
  private Type getRuntimeTypeIfMoreSpecific(Type paramType, Object paramObject)
  {
    Object localObject = paramType;
    if (paramObject != null) {
      if ((paramType != Object.class) && (!(paramType instanceof TypeVariable)))
      {
        localObject = paramType;
        if (!(paramType instanceof Class)) {}
      }
      else
      {
        localObject = paramObject.getClass();
      }
    }
    return localObject;
  }
  
  public T read(JsonReader paramJsonReader)
    throws IOException
  {
    return delegate.read(paramJsonReader);
  }
  
  public void write(JsonWriter paramJsonWriter, T paramT)
    throws IOException
  {
    TypeAdapter localTypeAdapter = delegate;
    Type localType = getRuntimeTypeIfMoreSpecific(type, paramT);
    if (localType != type)
    {
      localTypeAdapter = context.getAdapter(TypeToken.get(localType));
      if (((localTypeAdapter instanceof ReflectiveTypeAdapterFactory.Adapter)) && (!(delegate instanceof ReflectiveTypeAdapterFactory.Adapter))) {
        localTypeAdapter = delegate;
      }
    }
    localTypeAdapter.write(paramJsonWriter, paramT);
  }
}
