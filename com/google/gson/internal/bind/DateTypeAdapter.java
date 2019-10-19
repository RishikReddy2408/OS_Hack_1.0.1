package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.bind.util.ISO8601Utils;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.Locale;

public final class DateTypeAdapter
  extends TypeAdapter<Date>
{
  public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory()
  {
    public TypeAdapter create(Gson paramAnonymousGson, TypeToken paramAnonymousTypeToken)
    {
      if (paramAnonymousTypeToken.getRawType() == Date.class) {
        return new DateTypeAdapter();
      }
      return null;
    }
  };
  private final DateFormat enUsFormat = DateFormat.getDateTimeInstance(2, 2, Locale.US);
  private final DateFormat localFormat = DateFormat.getDateTimeInstance(2, 2);
  
  public DateTypeAdapter() {}
  
  private Date deserializeToDate(String paramString)
  {
    Object localObject = localFormat;
    try
    {
      localObject = ((DateFormat)localObject).parse(paramString);
      return localObject;
    }
    catch (Throwable paramString)
    {
      break label64;
      localObject = enUsFormat;
      for (;;)
      {
        try
        {
          localObject = ((DateFormat)localObject).parse(paramString);
          return localObject;
        }
        catch (ParseException localParseException3)
        {
          continue;
        }
        try
        {
          localObject = ISO8601Utils.parse(paramString, new ParsePosition(0));
          return localObject;
        }
        catch (ParseException localParseException1)
        {
          throw new JsonSyntaxException(paramString, localParseException1);
        }
      }
      throw paramString;
    }
    catch (ParseException localParseException2)
    {
      label64:
      for (;;) {}
    }
  }
  
  public Date read(JsonReader paramJsonReader)
    throws IOException
  {
    if (paramJsonReader.peek() == JsonToken.NULL)
    {
      paramJsonReader.nextNull();
      return null;
    }
    return deserializeToDate(paramJsonReader.nextString());
  }
  
  public void write(JsonWriter paramJsonWriter, Date paramDate)
    throws IOException
  {
    if (paramDate == null)
    {
      try
      {
        paramJsonWriter.nullValue();
        return;
      }
      catch (Throwable paramJsonWriter) {}
    }
    else
    {
      paramJsonWriter.value(enUsFormat.format(paramDate));
      return;
    }
    throw paramJsonWriter;
  }
}
