package com.google.gson;

import java.lang.reflect.Type;

public abstract interface JsonSerializer<T>
{
  public abstract JsonElement serialize(Object paramObject, Type paramType, JsonSerializationContext paramJsonSerializationContext);
}
