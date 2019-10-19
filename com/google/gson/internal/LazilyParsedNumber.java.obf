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
    boolean bool = true;
    if (this == paramObject) {
      return true;
    }
    if ((paramObject instanceof LazilyParsedNumber))
    {
      paramObject = (LazilyParsedNumber)paramObject;
      if (value != value)
      {
        if (value.equals(value)) {
          return true;
        }
        bool = false;
      }
      return bool;
    }
    return false;
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
    try
    {
      int i = Integer.parseInt(value);
      return i;
    }
    catch (NumberFormatException localNumberFormatException1)
    {
      long l;
      label21:
      for (;;) {}
    }
    try
    {
      l = Long.parseLong(value);
      return (int)l;
    }
    catch (NumberFormatException localNumberFormatException2)
    {
      break label21;
    }
    return new BigDecimal(value).intValue();
  }
  
  public long longValue()
  {
    try
    {
      long l = Long.parseLong(value);
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
