package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public final class TimeTypeAdapter
  extends TypeAdapter<Time>
{
  public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory()
  {
    public TypeAdapter create(Gson paramAnonymousGson, TypeToken paramAnonymousTypeToken)
    {
      if (paramAnonymousTypeToken.getRawType() == Time.class) {
        return new TimeTypeAdapter();
      }
      return null;
    }
  };
  private final DateFormat format = new SimpleDateFormat("hh:mm:ss a");
  
  public TimeTypeAdapter() {}
  
  /* Error */
  public Time read(com.google.gson.stream.JsonReader paramJsonReader)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: invokevirtual 47	com/google/gson/stream/JsonReader:peek	()Lcom/google/gson/stream/JsonToken;
    //   6: getstatic 53	com/google/gson/stream/JsonToken:NULL	Lcom/google/gson/stream/JsonToken;
    //   9: if_acmpne +11 -> 20
    //   12: aload_1
    //   13: invokevirtual 56	com/google/gson/stream/JsonReader:nextNull	()V
    //   16: aload_0
    //   17: monitorexit
    //   18: aconst_null
    //   19: areturn
    //   20: aload_0
    //   21: getfield 29	com/google/gson/internal/bind/TimeTypeAdapter:format	Ljava/text/DateFormat;
    //   24: astore_2
    //   25: aload_2
    //   26: aload_1
    //   27: invokevirtual 60	com/google/gson/stream/JsonReader:nextString	()Ljava/lang/String;
    //   30: invokevirtual 66	java/text/DateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
    //   33: astore_1
    //   34: new 68	java/sql/Time
    //   37: dup
    //   38: aload_1
    //   39: invokevirtual 74	java/util/Date:getTime	()J
    //   42: invokespecial 77	java/sql/Time:<init>	(J)V
    //   45: astore_1
    //   46: aload_0
    //   47: monitorexit
    //   48: aload_1
    //   49: areturn
    //   50: astore_1
    //   51: new 79	com/google/gson/JsonSyntaxException
    //   54: dup
    //   55: aload_1
    //   56: invokespecial 82	com/google/gson/JsonSyntaxException:<init>	(Ljava/lang/Throwable;)V
    //   59: athrow
    //   60: astore_1
    //   61: aload_0
    //   62: monitorexit
    //   63: aload_1
    //   64: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	65	0	this	TimeTypeAdapter
    //   0	65	1	paramJsonReader	com.google.gson.stream.JsonReader
    //   24	2	2	localDateFormat	DateFormat
    // Exception table:
    //   from	to	target	type
    //   25	34	50	java/text/ParseException
    //   34	46	50	java/text/ParseException
    //   2	16	60	java/lang/Throwable
    //   20	25	60	java/lang/Throwable
    //   25	34	60	java/lang/Throwable
    //   34	46	60	java/lang/Throwable
    //   51	60	60	java/lang/Throwable
  }
  
  public void write(JsonWriter paramJsonWriter, Time paramTime)
    throws IOException
  {
    if (paramTime == null) {
      paramTime = null;
    }
    try
    {
      paramTime = format.format(paramTime);
      paramJsonWriter.value(paramTime);
      return;
    }
    catch (Throwable paramJsonWriter)
    {
      throw paramJsonWriter;
    }
  }
}
