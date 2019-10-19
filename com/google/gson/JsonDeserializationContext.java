package com.google.gson;

import java.lang.reflect.Type;

public abstract interface JsonDeserializationContext
{
  public abstract Object deserialize(JsonElement paramJsonElement, Type paramType)
    throws JsonParseException;
}
