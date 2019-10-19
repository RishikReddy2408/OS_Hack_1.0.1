package com.google.gson;

import com.google.gson.reflect.TypeToken;

public abstract interface TypeAdapterFactory
{
  public abstract TypeAdapter create(Gson paramGson, TypeToken paramTypeToken);
}
