package com.google.gson;

public enum LongSerializationPolicy
{
  DEFAULT,  STRING;
  
  public abstract JsonElement serialize(Long paramLong);
}
