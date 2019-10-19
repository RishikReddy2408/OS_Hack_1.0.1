package com.google.gson;

import com.google.gson.stream.JsonReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.Iterator;

public final class JsonStreamParser
  implements Iterator<JsonElement>
{
  private final Object lock;
  private final JsonReader parser;
  
  public JsonStreamParser(Reader paramReader)
  {
    parser = new JsonReader(paramReader);
    parser.setLenient(true);
    lock = new Object();
  }
  
  public JsonStreamParser(String paramString)
  {
    this(new StringReader(paramString));
  }
  
  /* Error */
  public boolean hasNext()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 28	com/google/gson/JsonStreamParser:lock	Ljava/lang/Object;
    //   4: astore_2
    //   5: aload_2
    //   6: monitorenter
    //   7: aload_0
    //   8: getfield 22	com/google/gson/JsonStreamParser:parser	Lcom/google/gson/stream/JsonReader;
    //   11: astore_3
    //   12: aload_3
    //   13: invokevirtual 47	com/google/gson/stream/JsonReader:peek	()Lcom/google/gson/stream/JsonToken;
    //   16: astore_3
    //   17: getstatic 53	com/google/gson/stream/JsonToken:END_DOCUMENT	Lcom/google/gson/stream/JsonToken;
    //   20: astore 4
    //   22: aload_3
    //   23: aload 4
    //   25: if_acmpeq +8 -> 33
    //   28: iconst_1
    //   29: istore_1
    //   30: goto +5 -> 35
    //   33: iconst_0
    //   34: istore_1
    //   35: aload_2
    //   36: monitorexit
    //   37: iload_1
    //   38: ireturn
    //   39: astore_3
    //   40: goto +23 -> 63
    //   43: astore_3
    //   44: new 55	com/google/gson/JsonIOException
    //   47: dup
    //   48: aload_3
    //   49: invokespecial 58	com/google/gson/JsonIOException:<init>	(Ljava/lang/Throwable;)V
    //   52: athrow
    //   53: astore_3
    //   54: new 60	com/google/gson/JsonSyntaxException
    //   57: dup
    //   58: aload_3
    //   59: invokespecial 61	com/google/gson/JsonSyntaxException:<init>	(Ljava/lang/Throwable;)V
    //   62: athrow
    //   63: aload_2
    //   64: monitorexit
    //   65: aload_3
    //   66: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	67	0	this	JsonStreamParser
    //   29	9	1	bool	boolean
    //   4	60	2	localObject1	Object
    //   11	12	3	localObject2	Object
    //   39	1	3	localThrowable	Throwable
    //   43	6	3	localIOException	java.io.IOException
    //   53	13	3	localMalformedJsonException	com.google.gson.stream.MalformedJsonException
    //   20	4	4	localJsonToken	com.google.gson.stream.JsonToken
    // Exception table:
    //   from	to	target	type
    //   7	12	39	java/lang/Throwable
    //   12	17	39	java/lang/Throwable
    //   17	22	39	java/lang/Throwable
    //   35	37	39	java/lang/Throwable
    //   44	53	39	java/lang/Throwable
    //   54	63	39	java/lang/Throwable
    //   63	65	39	java/lang/Throwable
    //   12	17	43	java/io/IOException
    //   12	17	53	com/google/gson/stream/MalformedJsonException
  }
  
  public JsonElement next()
    throws JsonParseException
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: fail exe a5 = a4\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:92)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.dfs(Cfg.java:255)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze0(BaseAnalyze.java:75)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze(BaseAnalyze.java:69)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer.transform(UnSSATransformer.java:274)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:163)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\nCaused by: java.lang.NullPointerException\n");
  }
  
  public void remove()
  {
    throw new UnsupportedOperationException();
  }
}
