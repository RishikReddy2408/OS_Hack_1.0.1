package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public final class SqlDateTypeAdapter
  extends TypeAdapter<Date>
{
  public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory()
  {
    public TypeAdapter create(Gson paramAnonymousGson, TypeToken paramAnonymousTypeToken)
    {
      if (paramAnonymousTypeToken.getRawType() == Date.class) {
        return new SqlDateTypeAdapter();
      }
      return null;
    }
  };
  private final DateFormat format = new SimpleDateFormat("MMM d, yyyy");
  
  public SqlDateTypeAdapter() {}
  
  /* Error */
  public Date read(com.google.gson.stream.JsonReader paramJsonReader)
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
    //   21: getfield 29	com/google/gson/internal/bind/SqlDateTypeAdapter:format	Ljava/text/DateFormat;
    //   24: astore 4
    //   26: aload 4
    //   28: aload_1
    //   29: invokevirtual 60	com/google/gson/stream/JsonReader:nextString	()Ljava/lang/String;
    //   32: invokevirtual 66	java/text/DateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
    //   35: invokevirtual 72	java/util/Date:getTime	()J
    //   38: lstore_2
    //   39: new 74	java/sql/Date
    //   42: dup
    //   43: lload_2
    //   44: invokespecial 77	java/sql/Date:<init>	(J)V
    //   47: astore_1
    //   48: aload_0
    //   49: monitorexit
    //   50: aload_1
    //   51: areturn
    //   52: astore_1
    //   53: new 79	com/google/gson/JsonSyntaxException
    //   56: dup
    //   57: aload_1
    //   58: invokespecial 82	com/google/gson/JsonSyntaxException:<init>	(Ljava/lang/Throwable;)V
    //   61: athrow
    //   62: astore_1
    //   63: aload_0
    //   64: monitorexit
    //   65: aload_1
    //   66: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	67	0	this	SqlDateTypeAdapter
    //   0	67	1	paramJsonReader	com.google.gson.stream.JsonReader
    //   38	6	2	l	long
    //   24	3	4	localDateFormat	DateFormat
    // Exception table:
    //   from	to	target	type
    //   26	39	52	java/text/ParseException
    //   39	48	52	java/text/ParseException
    //   2	16	62	java/lang/Throwable
    //   20	26	62	java/lang/Throwable
    //   26	39	62	java/lang/Throwable
    //   39	48	62	java/lang/Throwable
    //   53	62	62	java/lang/Throwable
  }
  
  public void write(JsonWriter paramJsonWriter, Date paramDate)
    throws IOException
  {
    if (paramDate == null) {
      paramDate = null;
    }
    try
    {
      paramDate = format.format(paramDate);
      paramJsonWriter.value(paramDate);
      return;
    }
    catch (Throwable paramJsonWriter)
    {
      throw paramJsonWriter;
    }
  }
}
