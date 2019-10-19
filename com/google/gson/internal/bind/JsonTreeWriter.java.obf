package com.google.gson.internal.bind;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public final class JsonTreeWriter
  extends JsonWriter
{
  private static final JsonPrimitive SENTINEL_CLOSED = new JsonPrimitive("closed");
  private static final Writer UNWRITABLE_WRITER = new Writer()
  {
    public void close()
      throws IOException
    {
      throw new AssertionError();
    }
    
    public void flush()
      throws IOException
    {
      throw new AssertionError();
    }
    
    public void write(char[] paramAnonymousArrayOfChar, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      throw new AssertionError();
    }
  };
  private String pendingName;
  private JsonElement product = JsonNull.INSTANCE;
  private final List<JsonElement> stack = new ArrayList();
  
  public JsonTreeWriter()
  {
    super(UNWRITABLE_WRITER);
  }
  
  private JsonElement peek()
  {
    return (JsonElement)stack.get(stack.size() - 1);
  }
  
  private void put(JsonElement paramJsonElement)
  {
    if (pendingName != null)
    {
      if ((!paramJsonElement.isJsonNull()) || (getSerializeNulls())) {
        ((JsonObject)peek()).add(pendingName, paramJsonElement);
      }
      pendingName = null;
      return;
    }
    if (stack.isEmpty())
    {
      product = paramJsonElement;
      return;
    }
    JsonElement localJsonElement = peek();
    if ((localJsonElement instanceof JsonArray))
    {
      ((JsonArray)localJsonElement).add(paramJsonElement);
      return;
    }
    throw new IllegalStateException();
  }
  
  public JsonWriter beginArray()
    throws IOException
  {
    JsonArray localJsonArray = new JsonArray();
    put(localJsonArray);
    stack.add(localJsonArray);
    return this;
  }
  
  public JsonWriter beginObject()
    throws IOException
  {
    JsonObject localJsonObject = new JsonObject();
    put(localJsonObject);
    stack.add(localJsonObject);
    return this;
  }
  
  public void close()
    throws IOException
  {
    if (stack.isEmpty())
    {
      stack.add(SENTINEL_CLOSED);
      return;
    }
    throw new IOException("Incomplete document");
  }
  
  public JsonWriter endArray()
    throws IOException
  {
    if ((!stack.isEmpty()) && (pendingName == null))
    {
      if ((peek() instanceof JsonArray))
      {
        stack.remove(stack.size() - 1);
        return this;
      }
      throw new IllegalStateException();
    }
    throw new IllegalStateException();
  }
  
  public JsonWriter endObject()
    throws IOException
  {
    if ((!stack.isEmpty()) && (pendingName == null))
    {
      if ((peek() instanceof JsonObject))
      {
        stack.remove(stack.size() - 1);
        return this;
      }
      throw new IllegalStateException();
    }
    throw new IllegalStateException();
  }
  
  public void flush()
    throws IOException
  {}
  
  public JsonElement get()
  {
    if (stack.isEmpty()) {
      return product;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Expected one JSON element but was ");
    localStringBuilder.append(stack);
    throw new IllegalStateException(localStringBuilder.toString());
  }
  
  public JsonWriter name(String paramString)
    throws IOException
  {
    if ((!stack.isEmpty()) && (pendingName == null))
    {
      if ((peek() instanceof JsonObject))
      {
        pendingName = paramString;
        return this;
      }
      throw new IllegalStateException();
    }
    throw new IllegalStateException();
  }
  
  public JsonWriter nullValue()
    throws IOException
  {
    put(JsonNull.INSTANCE);
    return this;
  }
  
  public JsonWriter value(double paramDouble)
    throws IOException
  {
    if ((!isLenient()) && ((Double.isNaN(paramDouble)) || (Double.isInfinite(paramDouble))))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("JSON forbids NaN and infinities: ");
      localStringBuilder.append(paramDouble);
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    put(new JsonPrimitive(Double.valueOf(paramDouble)));
    return this;
  }
  
  public JsonWriter value(long paramLong)
    throws IOException
  {
    put(new JsonPrimitive(Long.valueOf(paramLong)));
    return this;
  }
  
  public JsonWriter value(Boolean paramBoolean)
    throws IOException
  {
    if (paramBoolean == null) {
      return nullValue();
    }
    put(new JsonPrimitive(paramBoolean));
    return this;
  }
  
  public JsonWriter value(Number paramNumber)
    throws IOException
  {
    if (paramNumber == null) {
      return nullValue();
    }
    if (!isLenient())
    {
      double d = paramNumber.doubleValue();
      if ((Double.isNaN(d)) || (Double.isInfinite(d)))
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("JSON forbids NaN and infinities: ");
        localStringBuilder.append(paramNumber);
        throw new IllegalArgumentException(localStringBuilder.toString());
      }
    }
    put(new JsonPrimitive(paramNumber));
    return this;
  }
  
  public JsonWriter value(String paramString)
    throws IOException
  {
    if (paramString == null) {
      return nullValue();
    }
    put(new JsonPrimitive(paramString));
    return this;
  }
  
  public JsonWriter value(boolean paramBoolean)
    throws IOException
  {
    put(new JsonPrimitive(Boolean.valueOf(paramBoolean)));
    return this;
  }
}
