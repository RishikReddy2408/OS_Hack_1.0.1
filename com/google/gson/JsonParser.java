package com.google.gson;

import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.MalformedJsonException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public final class JsonParser
{
  public JsonParser() {}
  
  public JsonElement parse(JsonReader paramJsonReader)
    throws JsonIOException, JsonSyntaxException
  {
    boolean bool = paramJsonReader.isLenient();
    paramJsonReader.setLenient(true);
    try
    {
      JsonElement localJsonElement = Streams.parse(paramJsonReader);
      paramJsonReader.setLenient(bool);
      return localJsonElement;
    }
    catch (Throwable localThrowable) {}catch (OutOfMemoryError localOutOfMemoryError)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Failed parsing JSON source: ");
      localStringBuilder.append(paramJsonReader);
      localStringBuilder.append(" to Json");
      throw new JsonParseException(localStringBuilder.toString(), localOutOfMemoryError);
    }
    catch (StackOverflowError localStackOverflowError)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Failed parsing JSON source: ");
      localStringBuilder.append(paramJsonReader);
      localStringBuilder.append(" to Json");
      throw new JsonParseException(localStringBuilder.toString(), localStackOverflowError);
    }
    paramJsonReader.setLenient(bool);
    throw localStackOverflowError;
  }
  
  public JsonElement parse(Reader paramReader)
    throws JsonIOException, JsonSyntaxException
  {
    try
    {
      Object localObject = new JsonReader(paramReader);
      paramReader = parse((JsonReader)localObject);
      boolean bool = paramReader.isJsonNull();
      if (!bool)
      {
        localObject = ((JsonReader)localObject).peek();
        if (localObject == JsonToken.END_DOCUMENT) {
          return paramReader;
        }
        paramReader = new JsonSyntaxException("Did not consume the entire document.");
        throw paramReader;
      }
    }
    catch (NumberFormatException paramReader)
    {
      throw new JsonSyntaxException(paramReader);
    }
    catch (IOException paramReader)
    {
      throw new JsonIOException(paramReader);
    }
    catch (MalformedJsonException paramReader)
    {
      throw new JsonSyntaxException(paramReader);
    }
    return paramReader;
  }
  
  public JsonElement parse(String paramString)
    throws JsonSyntaxException
  {
    return parse(new StringReader(paramString));
  }
}
