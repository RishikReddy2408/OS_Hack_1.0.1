package com.google.gson.internal;

import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.MalformedJsonException;
import java.io.EOFException;
import java.io.IOException;
import java.io.Writer;

public final class Streams
{
  private Streams()
  {
    throw new UnsupportedOperationException();
  }
  
  public static JsonElement parse(JsonReader paramJsonReader)
    throws JsonParseException
  {
    try
    {
      paramJsonReader.peek();
      int i = 0;
      try
      {
        paramJsonReader = (JsonElement)TypeAdapters.JSON_ELEMENT.read(paramJsonReader);
        return paramJsonReader;
      }
      catch (EOFException paramJsonReader) {}
      if (i == 0) {
        break label65;
      }
    }
    catch (NumberFormatException paramJsonReader)
    {
      throw new JsonSyntaxException(paramJsonReader);
    }
    catch (IOException paramJsonReader)
    {
      throw new JsonIOException(paramJsonReader);
    }
    catch (MalformedJsonException paramJsonReader)
    {
      throw new JsonSyntaxException(paramJsonReader);
    }
    catch (EOFException paramJsonReader)
    {
      i = 1;
    }
    return JsonNull.INSTANCE;
    label65:
    throw new JsonSyntaxException(paramJsonReader);
  }
  
  public static void write(JsonElement paramJsonElement, JsonWriter paramJsonWriter)
    throws IOException
  {
    TypeAdapters.JSON_ELEMENT.write(paramJsonWriter, paramJsonElement);
  }
  
  public static Writer writerForAppendable(Appendable paramAppendable)
  {
    if ((paramAppendable instanceof Writer)) {
      return (Writer)paramAppendable;
    }
    return new AppendableWriter(paramAppendable);
  }
  
  private static final class AppendableWriter
    extends Writer
  {
    private final Appendable appendable;
    private final CurrentWrite currentWrite = new CurrentWrite();
    
    AppendableWriter(Appendable paramAppendable)
    {
      appendable = paramAppendable;
    }
    
    public void close() {}
    
    public void flush() {}
    
    public void write(int paramInt)
      throws IOException
    {
      appendable.append((char)paramInt);
    }
    
    public void write(char[] paramArrayOfChar, int paramInt1, int paramInt2)
      throws IOException
    {
      currentWrite.chars = paramArrayOfChar;
      appendable.append(currentWrite, paramInt1, paramInt2 + paramInt1);
    }
    
    static class CurrentWrite
      implements CharSequence
    {
      char[] chars;
      
      CurrentWrite() {}
      
      public char charAt(int paramInt)
      {
        return chars[paramInt];
      }
      
      public int length()
      {
        return chars.length;
      }
      
      public CharSequence subSequence(int paramInt1, int paramInt2)
      {
        return new String(chars, paramInt1, paramInt2 - paramInt1);
      }
    }
  }
}
