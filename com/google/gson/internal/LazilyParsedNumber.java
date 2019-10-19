package com.google.gson.internal;

import java.io.ObjectStreamException;
import java.math.BigDecimal;

public final class LazilyParsedNumber
  extends Number
{
  private final String value;
  
  public LazilyParsedNumber(String paramString)
  {
    value = paramString;
  }
  
  private Object writeReplace()
    throws ObjectStreamException
  {
    return new BigDecimal(value);
  }
  
  public double doubleValue()
  {
    return Double.parseDouble(value);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if ((paramObject instanceof LazilyParsedNumber))
    {
      paramObject = (LazilyParsedNumber)paramObject;
      if (value != value) {
        return value.equals(value);
      }
    }
    else
    {
      return false;
    }
    return true;
  }
  
  public float floatValue()
  {
    return Float.parseFloat(value);
  }
  
  public int hashCode()
  {
    return value.hashCode();
  }
  
  public int intValue()
  {
    String str = value;
    try
    {
      int i = Integer.parseInt(str);
      return i;
    }
    catch (NumberFormatException localNumberFormatException1)
    {
      for (;;) {}
    }
    str = value;
    try
    {
      long l = Long.parseLong(str);
      return (int)l;
    }
    catch (NumberFormatException localNumberFormatException2)
    {
      for (;;) {}
    }
    return new BigDecimal(value).intValue();
  }
  
  public long longValue()
  {
    String str = value;
    try
    {
      long l = Long.parseLong(str);
      return l;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      for (;;) {}
    }
    return new BigDecimal(value).longValue();
  }
  
  public String toString()
  {
    return value;
  }
}
