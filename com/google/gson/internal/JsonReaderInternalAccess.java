package com.google.gson.internal;

import com.google.gson.stream.JsonReader;
import java.io.IOException;

public abstract class JsonReaderInternalAccess
{
  public static JsonReaderInternalAccess INSTANCE;
  
  public JsonReaderInternalAccess() {}
  
  public abstract void promoteNameToValue(JsonReader paramJsonReader)
    throws IOException;
}
