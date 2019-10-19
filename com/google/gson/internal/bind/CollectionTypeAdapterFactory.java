package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal..Gson.Types;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;

public final class CollectionTypeAdapterFactory
  implements TypeAdapterFactory
{
  private final ConstructorConstructor constructorConstructor;
  
  public CollectionTypeAdapterFactory(ConstructorConstructor paramConstructorConstructor)
  {
    constructorConstructor = paramConstructorConstructor;
  }
  
  public TypeAdapter create(Gson paramGson, TypeToken paramTypeToken)
  {
    Type localType = paramTypeToken.getType();
    Class localClass = paramTypeToken.getRawType();
    if (!Collection.class.isAssignableFrom(localClass)) {
      return null;
    }
    localType = .Gson.Types.getCollectionElementType(localType, localClass);
    return new Adapter(paramGson, localType, paramGson.getAdapter(TypeToken.get(localType)), constructorConstructor.get(paramTypeToken));
  }
  
  private static final class Adapter<E>
    extends TypeAdapter<Collection<E>>
  {
    private final ObjectConstructor<? extends Collection<E>> constructor;
    private final TypeAdapter<E> elementTypeAdapter;
    
    public Adapter(Gson paramGson, Type paramType, TypeAdapter paramTypeAdapter, ObjectConstructor paramObjectConstructor)
    {
      elementTypeAdapter = new TypeAdapterRuntimeTypeWrapper(paramGson, paramTypeAdapter, paramType);
      constructor = paramObjectConstructor;
    }
    
    public Collection read(JsonReader paramJsonReader)
      throws IOException
    {
      if (paramJsonReader.peek() == JsonToken.NULL)
      {
        paramJsonReader.nextNull();
        return null;
      }
      Collection localCollection = (Collection)constructor.construct();
      paramJsonReader.beginArray();
      while (paramJsonReader.hasNext()) {
        localCollection.add(elementTypeAdapter.read(paramJsonReader));
      }
      paramJsonReader.endArray();
      return localCollection;
    }
    
    public void write(JsonWriter paramJsonWriter, Collection paramCollection)
      throws IOException
    {
      if (paramCollection == null)
      {
        paramJsonWriter.nullValue();
        return;
      }
      paramJsonWriter.beginArray();
      paramCollection = paramCollection.iterator();
      while (paramCollection.hasNext())
      {
        Object localObject = paramCollection.next();
        elementTypeAdapter.write(paramJsonWriter, localObject);
      }
      paramJsonWriter.endArray();
    }
  }
}
