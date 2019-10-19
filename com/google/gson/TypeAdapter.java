package com.google.gson;

import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.internal.bind.JsonTreeWriter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

public abstract class TypeAdapter<T>
{
  public TypeAdapter() {}
  
  public final Object fromJson(Reader paramReader)
    throws IOException
  {
    return read(new JsonReader(paramReader));
  }
  
  public final Object fromJson(String paramString)
    throws IOException
  {
    return fromJson(new StringReader(paramString));
  }
  
  public final Object fromJsonTree(JsonElement paramJsonElement)
  {
    try
    {
      paramJsonElement = read(new JsonTreeReader(paramJsonElement));
      return paramJsonElement;
    }
    catch (IOException paramJsonElement)
    {
      throw new JsonIOException(paramJsonElement);
    }
  }
  
  public final TypeAdapter nullSafe()
  {
    new TypeAdapter()
    {
      public Object read(JsonReader paramAnonymousJsonReader)
        throws IOException
      {
        if (paramAnonymousJsonReader.peek() == JsonToken.NULL)
        {
          paramAnonymousJsonReader.nextNull();
          return null;
        }
        return TypeAdapter.this.read(paramAnonymousJsonReader);
      }
      
      public void write(JsonWriter paramAnonymousJsonWriter, Object paramAnonymousObject)
        throws IOException
      {
        if (paramAnonymousObject == null)
        {
          paramAnonymousJsonWriter.nullValue();
          return;
        }
        TypeAdapter.this.write(paramAnonymousJsonWriter, paramAnonymousObject);
      }
    };
  }
  
  public abstract Object read(JsonReader paramJsonReader)
    throws IOException;
  
  public final String toJson(Object paramObject)
  {
    StringWriter localStringWriter = new StringWriter();
    try
    {
      toJson(localStringWriter, paramObject);
      return localStringWriter.toString();
    }
    catch (IOException paramObject)
    {
      throw new AssertionError(paramObject);
    }
  }
  
  public final void toJson(Writer paramWriter, Object paramObject)
    throws IOException
  {
    write(new JsonWriter(paramWriter), paramObject);
  }
  
  public final JsonElement toJsonTree(Object paramObject)
  {
    try
    {
      JsonTreeWriter localJsonTreeWriter = new JsonTreeWriter();
      write(localJsonTreeWriter, paramObject);
      paramObject = localJsonTreeWriter.get();
      return paramObject;
    }
    catch (IOException paramObject)
    {
      throw new JsonIOException(paramObject);
    }
  }
  
  public abstract void write(JsonWriter paramJsonWriter, Object paramObject)
    throws IOException;
}
