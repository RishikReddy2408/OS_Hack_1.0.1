package com.google.gson.internal.bind.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class ISO8601Utils
{
  private static final TimeZone TIMEZONE_UTC = TimeZone.getTimeZone("UTC");
  private static final String UTC_ID = "UTC";
  
  public ISO8601Utils() {}
  
  private static boolean checkOffset(String paramString, int paramInt, char paramChar)
  {
    return (paramInt < paramString.length()) && (paramString.charAt(paramInt) == paramChar);
  }
  
  public static String format(Date paramDate)
  {
    return format(paramDate, false, TIMEZONE_UTC);
  }
  
  public static String format(Date paramDate, boolean paramBoolean)
  {
    return format(paramDate, paramBoolean, TIMEZONE_UTC);
  }
  
  public static String format(Date paramDate, boolean paramBoolean, TimeZone paramTimeZone)
  {
    GregorianCalendar localGregorianCalendar = new GregorianCalendar(paramTimeZone, Locale.US);
    localGregorianCalendar.setTime(paramDate);
    int j = "yyyy-MM-ddThh:mm:ss".length();
    if (paramBoolean) {
      i = ".sss".length();
    } else {
      i = 0;
    }
    if (paramTimeZone.getRawOffset() == 0) {}
    int k;
    for (paramDate = "Z";; paramDate = "+hh:mm")
    {
      k = paramDate.length();
      break;
    }
    paramDate = new StringBuilder(j + i + k);
    padInt(paramDate, localGregorianCalendar.get(1), "yyyy".length());
    char c = '-';
    paramDate.append('-');
    padInt(paramDate, localGregorianCalendar.get(2) + 1, "MM".length());
    paramDate.append('-');
    padInt(paramDate, localGregorianCalendar.get(5), "dd".length());
    paramDate.append('T');
    padInt(paramDate, localGregorianCalendar.get(11), "hh".length());
    paramDate.append(':');
    padInt(paramDate, localGregorianCalendar.get(12), "mm".length());
    paramDate.append(':');
    padInt(paramDate, localGregorianCalendar.get(13), "ss".length());
    if (paramBoolean)
    {
      paramDate.append('.');
      padInt(paramDate, localGregorianCalendar.get(14), "sss".length());
    }
    int i = paramTimeZone.getOffset(localGregorianCalendar.getTimeInMillis());
    if (i != 0)
    {
      k = i / 60000;
      j = Math.abs(k / 60);
      k = Math.abs(k % 60);
      if (i >= 0) {
        c = '+';
      }
      paramDate.append(c);
      padInt(paramDate, j, "hh".length());
      paramDate.append(':');
      padInt(paramDate, k, "mm".length());
    }
    else
    {
      paramDate.append('Z');
    }
    return paramDate.toString();
  }
  
  private static int indexOfNonDigit(String paramString, int paramInt)
  {
    while (paramInt < paramString.length())
    {
      int i = paramString.charAt(paramInt);
      if (i >= 48)
      {
        if (i > 57) {
          return paramInt;
        }
        paramInt += 1;
      }
      else
      {
        return paramInt;
      }
    }
    return paramString.length();
  }
  
  private static void padInt(StringBuilder paramStringBuilder, int paramInt1, int paramInt2)
  {
    String str = Integer.toString(paramInt1);
    paramInt1 = paramInt2 - str.length();
    while (paramInt1 > 0)
    {
      paramStringBuilder.append('0');
      paramInt1 -= 1;
    }
    paramStringBuilder.append(str);
  }
  
  public static Date parse(String paramString, ParsePosition paramParsePosition)
    throws ParseException
  {
    try
    {
      int i = paramParsePosition.getIndex();
      int j = i + 4;
      int i2 = parseInt(paramString, i, j);
      boolean bool = checkOffset(paramString, j, '-');
      i = j;
      if (bool) {
        i = j + 1;
      }
      j = i + 2;
      int i3 = parseInt(paramString, i, j);
      bool = checkOffset(paramString, j, '-');
      i = j;
      if (bool) {
        i = j + 1;
      }
      int m = i + 2;
      int i4 = parseInt(paramString, i, m);
      bool = checkOffset(paramString, m, 'T');
      if (!bool)
      {
        i = paramString.length();
        if (i <= m)
        {
          localObject1 = new GregorianCalendar(i2, i3 - 1, i4);
          paramParsePosition.setIndex(m);
          localObject1 = ((Calendar)localObject1).getTime();
          return localObject1;
        }
      }
      int k;
      if (bool)
      {
        i = m + 1;
        j = i + 2;
        n = parseInt(paramString, i, j);
        bool = checkOffset(paramString, j, ':');
        i = j;
        if (bool) {
          i = j + 1;
        }
        j = i + 2;
        i1 = parseInt(paramString, i, j);
        bool = checkOffset(paramString, j, ':');
        i = j;
        if (bool) {
          i = j + 1;
        }
        int i5 = paramString.length();
        j = n;
        k = i1;
        m = i;
        if (i5 > i)
        {
          i5 = paramString.charAt(i);
          j = n;
          k = i1;
          m = i;
          if (i5 != 90)
          {
            j = n;
            k = i1;
            m = i;
            if (i5 != 43)
            {
              j = n;
              k = i1;
              m = i;
              if (i5 != 45)
              {
                m = i + 2;
                j = parseInt(paramString, i, m);
                i = 59;
                if ((j > 59) && (j < 63)) {
                  j = i;
                }
                bool = checkOffset(paramString, m, '.');
                if (bool)
                {
                  i5 = m + 1;
                  i = indexOfNonDigit(paramString, i5 + 1);
                  m = i;
                  int i6 = Math.min(i, i5 + 3);
                  k = parseInt(paramString, i5, i6);
                  i = k;
                  switch (i6 - i5)
                  {
                  default: 
                    break;
                  case 2: 
                    i = k * 10;
                    break;
                  case 1: 
                    i = k * 100;
                  }
                  k = i1;
                  i1 = j;
                  break label504;
                }
                i = 0;
                k = i1;
                i1 = j;
                break label504;
              }
            }
          }
        }
      }
      else
      {
        j = 0;
        k = 0;
      }
      i = 0;
      int i1 = 0;
      int n = j;
      label504:
      j = paramString.length();
      if (j > m)
      {
        char c = paramString.charAt(m);
        if (c == 'Z')
        {
          localObject1 = TIMEZONE_UTC;
          j = m + 1;
        }
        else
        {
          if ((c != '+') && (c != '-'))
          {
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append("Invalid time zone indicator '");
            ((StringBuilder)localObject1).append(c);
            ((StringBuilder)localObject1).append("'");
            throw new IndexOutOfBoundsException(((StringBuilder)localObject1).toString());
          }
          localObject2 = paramString.substring(m);
          localObject1 = localObject2;
          j = ((String)localObject2).length();
          if (j < 5)
          {
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append((String)localObject2);
            ((StringBuilder)localObject1).append("00");
            localObject1 = ((StringBuilder)localObject1).toString();
          }
          j = ((String)localObject1).length();
          j = m + j;
          bool = "+0000".equals(localObject1);
          if (!bool)
          {
            bool = "+00:00".equals(localObject1);
            if (!bool)
            {
              localObject2 = new StringBuilder();
              ((StringBuilder)localObject2).append("GMT");
              ((StringBuilder)localObject2).append((String)localObject1);
              localObject2 = ((StringBuilder)localObject2).toString();
              localObject1 = TimeZone.getTimeZone((String)localObject2);
              localObject3 = ((TimeZone)localObject1).getID();
              bool = ((String)localObject3).equals(localObject2);
              if (!bool)
              {
                bool = ((String)localObject3).replace(":", "").equals(localObject2);
                if (!bool)
                {
                  localObject3 = new StringBuilder();
                  ((StringBuilder)localObject3).append("Mismatching time zone indicator: ");
                  ((StringBuilder)localObject3).append((String)localObject2);
                  ((StringBuilder)localObject3).append(" given, resolves to ");
                  ((StringBuilder)localObject3).append(((TimeZone)localObject1).getID());
                  localObject1 = new IndexOutOfBoundsException(((StringBuilder)localObject3).toString());
                  throw ((Throwable)localObject1);
                }
              }
              break label862;
            }
          }
          localObject1 = TIMEZONE_UTC;
        }
        label862:
        localObject1 = new GregorianCalendar((TimeZone)localObject1);
        ((Calendar)localObject1).setLenient(false);
        ((Calendar)localObject1).set(1, i2);
        ((Calendar)localObject1).set(2, i3 - 1);
        ((Calendar)localObject1).set(5, i4);
        ((Calendar)localObject1).set(11, n);
        ((Calendar)localObject1).set(12, k);
        ((Calendar)localObject1).set(13, i1);
        ((Calendar)localObject1).set(14, i);
        paramParsePosition.setIndex(j);
        localObject1 = ((Calendar)localObject1).getTime();
        return localObject1;
      }
      Object localObject1 = new IllegalArgumentException("No time zone indicator");
      throw ((Throwable)localObject1);
    }
    catch (IllegalArgumentException localIllegalArgumentException) {}catch (NumberFormatException localNumberFormatException) {}catch (IndexOutOfBoundsException localIndexOutOfBoundsException) {}
    if (paramString == null)
    {
      paramString = null;
    }
    else
    {
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append('"');
      ((StringBuilder)localObject2).append(paramString);
      ((StringBuilder)localObject2).append("'");
      paramString = ((StringBuilder)localObject2).toString();
    }
    Object localObject3 = ((Exception)localIndexOutOfBoundsException).getMessage();
    Object localObject2 = localObject3;
    if ((localObject3 == null) || (((String)localObject3).isEmpty()))
    {
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("(");
      ((StringBuilder)localObject2).append(localIndexOutOfBoundsException.getClass().getName());
      ((StringBuilder)localObject2).append(")");
      localObject2 = ((StringBuilder)localObject2).toString();
    }
    localObject3 = new StringBuilder();
    ((StringBuilder)localObject3).append("Failed to parse date [");
    ((StringBuilder)localObject3).append(paramString);
    ((StringBuilder)localObject3).append("]: ");
    ((StringBuilder)localObject3).append((String)localObject2);
    paramString = new ParseException(((StringBuilder)localObject3).toString(), paramParsePosition.getIndex());
    paramString.initCause((Throwable)localIndexOutOfBoundsException);
    throw paramString;
  }
  
  private static int parseInt(String paramString, int paramInt1, int paramInt2)
    throws NumberFormatException
  {
    if ((paramInt1 >= 0) && (paramInt2 <= paramString.length()) && (paramInt1 <= paramInt2))
    {
      int i;
      int j;
      StringBuilder localStringBuilder;
      if (paramInt1 < paramInt2)
      {
        i = paramInt1 + 1;
        j = Character.digit(paramString.charAt(paramInt1), 10);
        if (j >= 0)
        {
          j = -j;
        }
        else
        {
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("Invalid number: ");
          localStringBuilder.append(paramString.substring(paramInt1, paramInt2));
          throw new NumberFormatException(localStringBuilder.toString());
        }
      }
      else
      {
        i = paramInt1;
        j = 0;
      }
      while (i < paramInt2)
      {
        int k = Character.digit(paramString.charAt(i), 10);
        if (k >= 0)
        {
          j = j * 10 - k;
          i += 1;
        }
        else
        {
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("Invalid number: ");
          localStringBuilder.append(paramString.substring(paramInt1, paramInt2));
          throw new NumberFormatException(localStringBuilder.toString());
        }
      }
      return -j;
    }
    throw new NumberFormatException(paramString);
  }
}
