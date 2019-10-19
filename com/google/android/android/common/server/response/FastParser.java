package com.google.android.android.common.server.response;

import android.util.Log;
import com.google.android.android.common.util.Base64Utils;
import com.google.android.android.common.util.JsonUtils;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

@KeepForSdk
@ShowFirstParty
public class FastParser<T extends com.google.android.gms.common.server.response.FastJsonResponse>
{
  private static final char[] zaqf = { 117, 108, 108 };
  private static final char[] zaqg = { 114, 117, 101 };
  private static final char[] zaqh = { 114, 117, 101, 34 };
  private static final char[] zaqi = { 97, 108, 115, 101 };
  private static final char[] zaqj = { 97, 108, 115, 101, 34 };
  private static final char[] zaqk = { '\n' };
  private static final com.google.android.gms.common.server.response.FastParser.zaa<Integer> zaqm = new UserConfig.1();
  private static final com.google.android.gms.common.server.response.FastParser.zaa<Long> zaqn = new WorkingTreeOptions.1();
  private static final com.google.android.gms.common.server.response.FastParser.zaa<Float> zaqo = new Money();
  private static final com.google.android.gms.common.server.response.FastParser.zaa<Double> zaqp = new Deserializers();
  private static final com.google.android.gms.common.server.response.FastParser.zaa<Boolean> zaqq = new BaseReceivePack.ReceiveConfig.1();
  private static final com.google.android.gms.common.server.response.FastParser.zaa<String> zaqr = new Statistics();
  private static final com.google.android.gms.common.server.response.FastParser.zaa<BigInteger> zaqs = new TransferConfig.1();
  private static final com.google.android.gms.common.server.response.FastParser.zaa<BigDecimal> zaqt = new Macro();
  private final char[] zaqa = new char[1];
  private final char[] zaqb = new char[32];
  private final char[] zaqc = new char['?'];
  private final StringBuilder zaqd = new StringBuilder(32);
  private final StringBuilder zaqe = new StringBuilder(1024);
  private final Stack<Integer> zaql = new Stack();
  
  public FastParser() {}
  
  private final double doubleValue(BufferedReader paramBufferedReader)
    throws FastParser.ParseException, IOException
  {
    int i = parse(paramBufferedReader, zaqc);
    if (i == 0) {
      return 0.0D;
    }
    return Double.parseDouble(new String(zaqc, 0, i));
  }
  
  private final boolean get(BufferedReader paramBufferedReader, boolean paramBoolean)
    throws FastParser.ParseException, IOException
  {
    for (;;)
    {
      char c = next(paramBufferedReader);
      if (c != '"')
      {
        char[] arrayOfChar;
        if (c != 'f')
        {
          if (c != 'n')
          {
            if (c == 't')
            {
              if (paramBoolean) {
                arrayOfChar = zaqh;
              } else {
                arrayOfChar = zaqg;
              }
              read(paramBufferedReader, arrayOfChar);
              return true;
            }
            paramBufferedReader = new StringBuilder(19);
            paramBufferedReader.append("Unexpected token: ");
            paramBufferedReader.append(c);
            throw new ParseException(paramBufferedReader.toString());
          }
          read(paramBufferedReader, zaqf);
          return false;
        }
        if (paramBoolean) {
          arrayOfChar = zaqj;
        } else {
          arrayOfChar = zaqi;
        }
        read(paramBufferedReader, arrayOfChar);
        return false;
      }
      if (paramBoolean) {
        break;
      }
      paramBoolean = true;
    }
    throw new ParseException("No boolean value found in string");
  }
  
  private final float getFloatValue(BufferedReader paramBufferedReader)
    throws FastParser.ParseException, IOException
  {
    int i = parse(paramBufferedReader, zaqc);
    if (i == 0) {
      return 0.0F;
    }
    return Float.parseFloat(new String(zaqc, 0, i));
  }
  
  private final String getName(BufferedReader paramBufferedReader)
    throws FastParser.ParseException, IOException
  {
    return trim(paramBufferedReader, zaqb, zaqd, null);
  }
  
  private final BigDecimal getValue(BufferedReader paramBufferedReader)
    throws FastParser.ParseException, IOException
  {
    int i = parse(paramBufferedReader, zaqc);
    if (i == 0) {
      return null;
    }
    return new BigDecimal(new String(zaqc, 0, i));
  }
  
  private final char next(BufferedReader paramBufferedReader)
    throws FastParser.ParseException, IOException
  {
    if (paramBufferedReader.read(zaqa) == -1) {
      return '\000';
    }
    while (Character.isWhitespace(zaqa[0])) {
      if (paramBufferedReader.read(zaqa) == -1) {
        return '\000';
      }
    }
    return zaqa[0];
  }
  
  private final void next(int paramInt)
    throws FastParser.ParseException
  {
    if (!zaql.isEmpty())
    {
      int i = ((Integer)zaql.pop()).intValue();
      if (i == paramInt) {
        return;
      }
      localStringBuilder = new StringBuilder(46);
      localStringBuilder.append("Expected state ");
      localStringBuilder.append(paramInt);
      localStringBuilder.append(" but had ");
      localStringBuilder.append(i);
      throw new ParseException(localStringBuilder.toString());
    }
    StringBuilder localStringBuilder = new StringBuilder(46);
    localStringBuilder.append("Expected state ");
    localStringBuilder.append(paramInt);
    localStringBuilder.append(" but had empty stack");
    throw new ParseException(localStringBuilder.toString());
  }
  
  private final int parse(BufferedReader paramBufferedReader, char[] paramArrayOfChar)
    throws FastParser.ParseException, IOException
  {
    char c = next(paramBufferedReader);
    if (c != 0)
    {
      if (c != ',')
      {
        if (c == 'n')
        {
          read(paramBufferedReader, zaqf);
          return 0;
        }
        paramBufferedReader.mark(1024);
        int k;
        if (c == '"')
        {
          i = 0;
          int j = 0;
          for (;;)
          {
            k = i;
            if (i >= paramArrayOfChar.length) {
              break label261;
            }
            k = i;
            if (paramBufferedReader.read(paramArrayOfChar, i, 1) == -1) {
              break label261;
            }
            c = paramArrayOfChar[i];
            if (Character.isISOControl(c)) {
              break;
            }
            if ((c == '"') && (j == 0))
            {
              paramBufferedReader.reset();
              paramBufferedReader.skip(i + 1);
              return i;
            }
            if (c == '\\') {
              j ^= 0x1;
            } else {
              j = 0;
            }
            i += 1;
          }
          throw new ParseException("Unexpected control character while reading string");
        }
        paramArrayOfChar[0] = c;
        int i = 1;
        for (;;)
        {
          k = i;
          if (i >= paramArrayOfChar.length) {
            break label261;
          }
          k = i;
          if (paramBufferedReader.read(paramArrayOfChar, i, 1) == -1) {
            break label261;
          }
          if ((paramArrayOfChar[i] == '}') || (paramArrayOfChar[i] == ',') || (Character.isWhitespace(paramArrayOfChar[i])) || (paramArrayOfChar[i] == ']')) {
            break;
          }
          i += 1;
        }
        paramBufferedReader.reset();
        paramBufferedReader.skip(i - 1);
        paramArrayOfChar[i] = '\000';
        return i;
        label261:
        if (k == paramArrayOfChar.length) {
          throw new ParseException("Absurdly long value");
        }
        throw new ParseException("Unexpected EOF");
      }
      throw new ParseException("Missing value");
    }
    throw new ParseException("Unexpected EOF");
  }
  
  private final String parse(BufferedReader paramBufferedReader)
    throws FastParser.ParseException, IOException
  {
    paramBufferedReader.mark(1024);
    int j = next(paramBufferedReader);
    int k;
    int i;
    if (j != 34)
    {
      if (j != 44)
      {
        k = 1;
        if (j != 91)
        {
          if (j != 123)
          {
            paramBufferedReader.reset();
            parse(paramBufferedReader, zaqc);
          }
          else
          {
            zaql.push(Integer.valueOf(1));
            paramBufferedReader.mark(32);
            i = next(paramBufferedReader);
            if (i == 125)
            {
              next(1);
            }
            else if (i == 34)
            {
              paramBufferedReader.reset();
              toString(paramBufferedReader);
              while (parse(paramBufferedReader) != null) {}
              next(1);
            }
            else
            {
              paramBufferedReader = new StringBuilder(18);
              paramBufferedReader.append("Unexpected token ");
              paramBufferedReader.append(i);
              throw new ParseException(paramBufferedReader.toString());
            }
          }
        }
        else
        {
          zaql.push(Integer.valueOf(5));
          paramBufferedReader.mark(32);
          if (next(paramBufferedReader) == ']')
          {
            next(5);
          }
          else
          {
            paramBufferedReader.reset();
            j = 0;
            int m = 0;
            while (k > 0)
            {
              i = next(paramBufferedReader);
              if (i != 0)
              {
                if (!Character.isISOControl(i))
                {
                  int n = m;
                  if (i == 34)
                  {
                    n = m;
                    if (j == 0) {
                      n = m ^ 0x1;
                    }
                  }
                  m = k;
                  if (i == 91)
                  {
                    m = k;
                    if (n == 0) {
                      m = k + 1;
                    }
                  }
                  k = m;
                  if (i == 93)
                  {
                    k = m;
                    if (n == 0) {
                      k = m - 1;
                    }
                  }
                  if ((i == 92) && (n != 0))
                  {
                    j ^= 0x1;
                    m = n;
                  }
                  else
                  {
                    j = 0;
                    m = n;
                  }
                }
                else
                {
                  throw new ParseException("Unexpected control character while reading array");
                }
              }
              else {
                throw new ParseException("Unexpected EOF while parsing array");
              }
            }
            next(5);
          }
        }
      }
      else
      {
        throw new ParseException("Missing value");
      }
    }
    else
    {
      if (paramBufferedReader.read(zaqa) == -1) {
        break label554;
      }
      k = zaqa[0];
      j = 0;
    }
    for (;;)
    {
      if ((k == 34) && (j == 0))
      {
        i = next(paramBufferedReader);
        if (i != 44)
        {
          if (i == 125)
          {
            next(2);
            return null;
          }
          paramBufferedReader = new StringBuilder(18);
          paramBufferedReader.append("Unexpected token ");
          paramBufferedReader.append(i);
          throw new ParseException(paramBufferedReader.toString());
        }
        next(2);
        return toString(paramBufferedReader);
      }
      if (k == 92) {
        j ^= 0x1;
      } else {
        j = 0;
      }
      if (paramBufferedReader.read(zaqa) == -1) {
        break label543;
      }
      i = zaqa[0];
      if (Character.isISOControl(i)) {
        break;
      }
      k = i;
    }
    throw new ParseException("Unexpected control character while reading string");
    label543:
    throw new ParseException("Unexpected EOF while parsing string");
    label554:
    throw new ParseException("Unexpected EOF while parsing string");
  }
  
  private final int parseInt(BufferedReader paramBufferedReader)
    throws FastParser.ParseException, IOException
  {
    int i1 = parse(paramBufferedReader, zaqc);
    if (i1 == 0) {
      return 0;
    }
    paramBufferedReader = zaqc;
    if (i1 > 0)
    {
      int i;
      int k;
      int m;
      if (paramBufferedReader[0] == '-')
      {
        i = 1;
        k = 1;
        m = Integer.MIN_VALUE;
      }
      else
      {
        i = 0;
        k = 0;
        m = -2147483647;
      }
      int j;
      int n;
      if (i < i1)
      {
        j = i + 1;
        i = Character.digit(paramBufferedReader[i], 10);
        if (i >= 0)
        {
          n = -i;
          i = j;
          j = n;
        }
        else
        {
          throw new ParseException("Unexpected non-digit character");
        }
      }
      else
      {
        j = 0;
      }
      while (i < i1)
      {
        n = Character.digit(paramBufferedReader[i], 10);
        if (n >= 0)
        {
          if (j >= -214748364)
          {
            j *= 10;
            if (j >= m + n)
            {
              j -= n;
              i += 1;
            }
            else
            {
              throw new ParseException("Number too large");
            }
          }
          else
          {
            throw new ParseException("Number too large");
          }
        }
        else {
          throw new ParseException("Unexpected non-digit character");
        }
      }
      if (k != 0)
      {
        if (i > 1) {
          return j;
        }
        throw new ParseException("No digits to parse");
      }
      return -j;
    }
    throw new ParseException("No number to parse");
  }
  
  private final long parseLong(BufferedReader paramBufferedReader)
    throws FastParser.ParseException, IOException
  {
    int m = parse(paramBufferedReader, zaqc);
    if (m == 0) {
      return 0L;
    }
    paramBufferedReader = zaqc;
    if (m > 0)
    {
      int i = 0;
      long l2;
      int j;
      if (paramBufferedReader[0] == '-')
      {
        l2 = Long.MIN_VALUE;
        i = 1;
        j = 1;
      }
      else
      {
        l2 = -9223372036854775807L;
        j = 0;
      }
      int k;
      long l1;
      if (i < m)
      {
        k = i + 1;
        i = Character.digit(paramBufferedReader[i], 10);
        if (i >= 0)
        {
          l1 = -i;
          i = k;
        }
        else
        {
          throw new ParseException("Unexpected non-digit character");
        }
      }
      else
      {
        l1 = 0L;
      }
      while (i < m)
      {
        k = Character.digit(paramBufferedReader[i], 10);
        if (k >= 0)
        {
          if (l1 >= -922337203685477580L)
          {
            l1 *= 10L;
            long l3 = k;
            if (l1 >= l2 + l3)
            {
              l1 -= l3;
              i += 1;
            }
            else
            {
              throw new ParseException("Number too large");
            }
          }
          else
          {
            throw new ParseException("Number too large");
          }
        }
        else {
          throw new ParseException("Unexpected non-digit character");
        }
      }
      if (j != 0)
      {
        if (i > 1) {
          return l1;
        }
        throw new ParseException("No digits to parse");
      }
      return -l1;
    }
    throw new ParseException("No number to parse");
  }
  
  private final BigInteger read(BufferedReader paramBufferedReader)
    throws FastParser.ParseException, IOException
  {
    int i = parse(paramBufferedReader, zaqc);
    if (i == 0) {
      return null;
    }
    return new BigInteger(new String(zaqc, 0, i));
  }
  
  private final ArrayList read(BufferedReader paramBufferedReader, zaa paramZaa)
    throws FastParser.ParseException, IOException
  {
    int i = next(paramBufferedReader);
    if (i == 110)
    {
      read(paramBufferedReader, zaqf);
      return null;
    }
    if (i == 91)
    {
      zaql.push(Integer.valueOf(5));
      ArrayList localArrayList = new ArrayList();
      for (;;)
      {
        paramBufferedReader.mark(1024);
        i = next(paramBufferedReader);
        if (i == 0) {
          break label107;
        }
        if (i != 44)
        {
          if (i == 93) {
            break;
          }
          paramBufferedReader.reset();
          localArrayList.add(paramZaa.parse(this, paramBufferedReader));
        }
      }
      next(5);
      return localArrayList;
      label107:
      throw new ParseException("Unexpected EOF");
    }
    throw new ParseException("Expected start of array");
  }
  
  private final void read(BufferedReader paramBufferedReader, char[] paramArrayOfChar)
    throws FastParser.ParseException, IOException
  {
    int i = 0;
    while (i < paramArrayOfChar.length)
    {
      int k = paramBufferedReader.read(zaqb, 0, paramArrayOfChar.length - i);
      if (k != -1)
      {
        int j = 0;
        while (j < k) {
          if (paramArrayOfChar[(j + i)] == zaqb[j]) {
            j += 1;
          } else {
            throw new ParseException("Unexpected character");
          }
        }
        i += k;
      }
      else
      {
        throw new ParseException("Unexpected EOF");
      }
    }
  }
  
  private final ArrayList serialize(BufferedReader paramBufferedReader, FastJsonResponse.Field paramField)
    throws FastParser.ParseException, IOException
  {
    ArrayList localArrayList = new ArrayList();
    char c = next(paramBufferedReader);
    if (c != ']')
    {
      if (c != 'n')
      {
        if (c == '{')
        {
          zaql.push(Integer.valueOf(1));
          try
          {
            for (;;)
            {
              FastJsonResponse localFastJsonResponse = paramField.zacp();
              boolean bool = serialize(paramBufferedReader, localFastJsonResponse);
              if (!bool) {
                break label251;
              }
              localArrayList.add(localFastJsonResponse);
              c = next(paramBufferedReader);
              if (c != ',')
              {
                if (c == ']')
                {
                  next(5);
                  return localArrayList;
                }
                paramBufferedReader = new StringBuilder(19);
                paramBufferedReader.append("Unexpected token: ");
                paramBufferedReader.append(c);
                throw new ParseException(paramBufferedReader.toString());
              }
              if (next(paramBufferedReader) != '{') {
                break;
              }
              zaql.push(Integer.valueOf(1));
            }
            throw new ParseException("Expected start of next object in array");
          }
          catch (IllegalAccessException paramBufferedReader)
          {
            throw new ParseException("Error instantiating inner object", paramBufferedReader);
          }
          catch (InstantiationException paramBufferedReader)
          {
            throw new ParseException("Error instantiating inner object", paramBufferedReader);
          }
        }
        paramBufferedReader = new StringBuilder(19);
        paramBufferedReader.append("Unexpected token: ");
        paramBufferedReader.append(c);
        throw new ParseException(paramBufferedReader.toString());
      }
      read(paramBufferedReader, zaqf);
      next(5);
      return null;
    }
    next(5);
    label251:
    return localArrayList;
  }
  
  private final boolean serialize(BufferedReader paramBufferedReader, FastJsonResponse paramFastJsonResponse)
    throws FastParser.ParseException, IOException
  {
    Map localMap = paramFastJsonResponse.getFieldMappings();
    Object localObject2 = toString(paramBufferedReader);
    Object localObject1 = localObject2;
    if (localObject2 == null)
    {
      next(1);
      return false;
    }
    while (localObject1 != null)
    {
      localObject2 = (FastJsonResponse.Field)localMap.get(localObject1);
      if (localObject2 == null)
      {
        localObject1 = parse(paramBufferedReader);
      }
      else
      {
        zaql.push(Integer.valueOf(4));
        int i;
        String str;
        switch (zapq)
        {
        default: 
          i = zapq;
          paramBufferedReader = new StringBuilder(30);
          paramBufferedReader.append("Invalid field type ");
          paramBufferedReader.append(i);
          throw new ParseException(paramBufferedReader.toString());
        case 11: 
          if (zapr)
          {
            i = next(paramBufferedReader);
            if (i == 110)
            {
              read(paramBufferedReader, zaqf);
              paramFastJsonResponse.addConcreteTypeArrayInternal((FastJsonResponse.Field)localObject2, zapu, null);
            }
            else
            {
              zaql.push(Integer.valueOf(5));
              if (i == 91) {
                paramFastJsonResponse.addConcreteTypeArrayInternal((FastJsonResponse.Field)localObject2, zapu, serialize(paramBufferedReader, (FastJsonResponse.Field)localObject2));
              } else {
                throw new ParseException("Expected array start");
              }
            }
          }
          else
          {
            i = next(paramBufferedReader);
            if (i == 110)
            {
              read(paramBufferedReader, zaqf);
              paramFastJsonResponse.addConcreteTypeInternal((FastJsonResponse.Field)localObject2, zapu, null);
            }
            else
            {
              zaql.push(Integer.valueOf(1));
              if (i == 123) {
                try
                {
                  localObject1 = ((FastJsonResponse.Field)localObject2).zacp();
                  serialize(paramBufferedReader, (FastJsonResponse)localObject1);
                  str = zapu;
                  paramFastJsonResponse.addConcreteTypeInternal((FastJsonResponse.Field)localObject2, str, (FastJsonResponse)localObject1);
                }
                catch (IllegalAccessException paramBufferedReader)
                {
                  throw new ParseException("Error instantiating inner object", paramBufferedReader);
                }
                catch (InstantiationException paramBufferedReader)
                {
                  throw new ParseException("Error instantiating inner object", paramBufferedReader);
                }
              } else {
                throw new ParseException("Expected start of object");
              }
            }
          }
          break;
        case 10: 
          i = next(paramBufferedReader);
          if (i == 110)
          {
            read(paramBufferedReader, zaqf);
            localObject1 = null;
          }
          else
          {
            if (i != 123) {
              break label777;
            }
            zaql.push(Integer.valueOf(1));
            localObject1 = new HashMap();
            do
            {
              do
              {
                i = next(paramBufferedReader);
                if (i == 0) {
                  break label766;
                }
                if (i == 34) {
                  break;
                }
              } while (i != 125);
              next(1);
              break;
              str = toString(paramBufferedReader, zaqb, zaqd, null);
              if (next(paramBufferedReader) != ':')
              {
                paramBufferedReader = String.valueOf(str);
                if (paramBufferedReader.length() != 0) {
                  paramBufferedReader = "No map value found for key ".concat(paramBufferedReader);
                } else {
                  paramBufferedReader = new String("No map value found for key ");
                }
                throw new ParseException(paramBufferedReader);
              }
              if (next(paramBufferedReader) != '"')
              {
                paramBufferedReader = String.valueOf(str);
                if (paramBufferedReader.length() != 0) {
                  paramBufferedReader = "Expected String value for key ".concat(paramBufferedReader);
                } else {
                  paramBufferedReader = new String("Expected String value for key ");
                }
                throw new ParseException(paramBufferedReader);
              }
              ((HashMap)localObject1).put(str, toString(paramBufferedReader, zaqb, zaqd, null));
              c = next(paramBufferedReader);
            } while (c == ',');
            if (c != '}') {
              break label730;
            }
            next(1);
          }
          paramFastJsonResponse.write((FastJsonResponse.Field)localObject2, (Map)localObject1);
          break;
          paramBufferedReader = new StringBuilder(48);
          paramBufferedReader.append("Unexpected character while parsing string map: ");
          paramBufferedReader.append(c);
          throw new ParseException(paramBufferedReader.toString());
          throw new ParseException("Unexpected EOF");
          throw new ParseException("Expected start of a map object");
        case 9: 
          paramFastJsonResponse.wtf((FastJsonResponse.Field)localObject2, Base64Utils.decodeUrlSafe(trim(paramBufferedReader, zaqc, zaqe, zaqk)));
          break;
        case 8: 
          paramFastJsonResponse.wtf((FastJsonResponse.Field)localObject2, Base64Utils.decode(trim(paramBufferedReader, zaqc, zaqe, zaqk)));
          break;
        case 7: 
          if (zapr) {
            paramFastJsonResponse.wtf((FastJsonResponse.Field)localObject2, read(paramBufferedReader, zaqr));
          } else {
            paramFastJsonResponse.addProperty((FastJsonResponse.Field)localObject2, getName(paramBufferedReader));
          }
          break;
        case 6: 
          if (zapr) {
            paramFastJsonResponse.addProperty((FastJsonResponse.Field)localObject2, read(paramBufferedReader, zaqq));
          } else {
            paramFastJsonResponse.writeValue((FastJsonResponse.Field)localObject2, get(paramBufferedReader, false));
          }
          break;
        case 5: 
          if (zapr) {
            paramFastJsonResponse.writeEndObject((FastJsonResponse.Field)localObject2, read(paramBufferedReader, zaqt));
          } else {
            paramFastJsonResponse.write((FastJsonResponse.Field)localObject2, getValue(paramBufferedReader));
          }
          break;
        case 4: 
          if (zapr) {
            paramFastJsonResponse.writeNumber((FastJsonResponse.Field)localObject2, read(paramBufferedReader, zaqp));
          } else {
            paramFastJsonResponse.writeNumber((FastJsonResponse.Field)localObject2, doubleValue(paramBufferedReader));
          }
          break;
        case 3: 
          if (zapr) {
            paramFastJsonResponse.writeStartArray((FastJsonResponse.Field)localObject2, read(paramBufferedReader, zaqo));
          } else {
            paramFastJsonResponse.writeNumber((FastJsonResponse.Field)localObject2, getFloatValue(paramBufferedReader));
          }
          break;
        case 2: 
          if (zapr) {
            paramFastJsonResponse.write((FastJsonResponse.Field)localObject2, read(paramBufferedReader, zaqn));
          } else {
            paramFastJsonResponse.writeNumber((FastJsonResponse.Field)localObject2, parseLong(paramBufferedReader));
          }
          break;
        case 1: 
          if (zapr) {
            paramFastJsonResponse.writeElement((FastJsonResponse.Field)localObject2, read(paramBufferedReader, zaqs));
          } else {
            paramFastJsonResponse.write((FastJsonResponse.Field)localObject2, read(paramBufferedReader));
          }
          break;
        case 0: 
          label730:
          label766:
          label777:
          if (zapr) {
            paramFastJsonResponse.writeEndArray((FastJsonResponse.Field)localObject2, read(paramBufferedReader, zaqm));
          } else {
            paramFastJsonResponse.writeNumber((FastJsonResponse.Field)localObject2, parseInt(paramBufferedReader));
          }
          break;
        }
        next(4);
        next(2);
        char c = next(paramBufferedReader);
        if (c != ',')
        {
          if (c == '}')
          {
            localObject1 = null;
          }
          else
          {
            paramBufferedReader = new StringBuilder(55);
            paramBufferedReader.append("Expected end of object or field separator, but found: ");
            paramBufferedReader.append(c);
            throw new ParseException(paramBufferedReader.toString());
          }
        }
        else {
          localObject1 = toString(paramBufferedReader);
        }
      }
    }
    next(1);
    return true;
  }
  
  private final String toString(BufferedReader paramBufferedReader)
    throws FastParser.ParseException, IOException
  {
    zaql.push(Integer.valueOf(2));
    char c = next(paramBufferedReader);
    if (c != '"')
    {
      if (c != ']')
      {
        if (c == '}')
        {
          next(2);
          return null;
        }
        paramBufferedReader = new StringBuilder(19);
        paramBufferedReader.append("Unexpected token: ");
        paramBufferedReader.append(c);
        throw new ParseException(paramBufferedReader.toString());
      }
      next(2);
      next(1);
      next(5);
      return null;
    }
    zaql.push(Integer.valueOf(3));
    String str = toString(paramBufferedReader, zaqb, zaqd, null);
    next(3);
    if (next(paramBufferedReader) == ':') {
      return str;
    }
    throw new ParseException("Expected key/value separator");
  }
  
  private static String toString(BufferedReader paramBufferedReader, char[] paramArrayOfChar1, StringBuilder paramStringBuilder, char[] paramArrayOfChar2)
    throws FastParser.ParseException, IOException
  {
    paramStringBuilder.setLength(0);
    paramBufferedReader.mark(paramArrayOfChar1.length);
    int k = 0;
    int i = 0;
    for (;;)
    {
      int n = paramBufferedReader.read(paramArrayOfChar1);
      if (n == -1) {
        break;
      }
      int j = 0;
      while (j < n)
      {
        char c = paramArrayOfChar1[j];
        int m;
        if (Character.isISOControl(c))
        {
          if (paramArrayOfChar2 != null)
          {
            m = 0;
            while (m < paramArrayOfChar2.length)
            {
              if (paramArrayOfChar2[m] == c)
              {
                m = 1;
                break label95;
              }
              m += 1;
            }
          }
          m = 0;
          label95:
          if (m == 0) {
            throw new ParseException("Unexpected control character while reading string");
          }
        }
        if ((c == '"') && (k == 0))
        {
          paramStringBuilder.append(paramArrayOfChar1, 0, j);
          paramBufferedReader.reset();
          paramBufferedReader.skip(j + 1);
          if (i != 0) {
            return JsonUtils.unescapeString(paramStringBuilder.toString());
          }
          return paramStringBuilder.toString();
        }
        if (c == '\\')
        {
          i = k ^ 0x1;
          m = 1;
        }
        else
        {
          k = 0;
          m = i;
          i = k;
        }
        j += 1;
        k = i;
        i = m;
      }
      paramStringBuilder.append(paramArrayOfChar1, 0, n);
      paramBufferedReader.mark(paramArrayOfChar1.length);
    }
    throw new ParseException("Unexpected EOF while parsing string");
  }
  
  private final String trim(BufferedReader paramBufferedReader, char[] paramArrayOfChar1, StringBuilder paramStringBuilder, char[] paramArrayOfChar2)
    throws FastParser.ParseException, IOException
  {
    int i = next(paramBufferedReader);
    if (i != 34)
    {
      if (i == 110)
      {
        read(paramBufferedReader, zaqf);
        return null;
      }
      throw new ParseException("Expected string");
    }
    return toString(paramBufferedReader, paramArrayOfChar1, paramStringBuilder, paramArrayOfChar2);
  }
  
  public void parse(InputStream paramInputStream, FastJsonResponse paramFastJsonResponse)
    throws FastParser.ParseException
  {
    paramInputStream = new BufferedReader(new InputStreamReader(paramInputStream), 1024);
    Object localObject = zaql;
    try
    {
      ((Stack)localObject).push(Integer.valueOf(0));
      char c = next(paramInputStream);
      if (c != 0)
      {
        if (c != '[')
        {
          if (c == '{')
          {
            localObject = zaql;
            ((Stack)localObject).push(Integer.valueOf(1));
            serialize(paramInputStream, paramFastJsonResponse);
          }
          else
          {
            paramFastJsonResponse = new StringBuilder(19);
            paramFastJsonResponse.append("Unexpected token: ");
            paramFastJsonResponse.append(c);
            paramFastJsonResponse = new ParseException(paramFastJsonResponse.toString());
            throw paramFastJsonResponse;
          }
        }
        else
        {
          localObject = zaql;
          ((Stack)localObject).push(Integer.valueOf(5));
          localObject = paramFastJsonResponse.getFieldMappings();
          int i = ((Map)localObject).size();
          if (i != 1) {
            break label246;
          }
          localObject = ((Map)localObject).entrySet().iterator().next();
          localObject = (Map.Entry)localObject;
          localObject = ((Map.Entry)localObject).getValue();
          localObject = (FastJsonResponse.Field)localObject;
          ArrayList localArrayList = serialize(paramInputStream, (FastJsonResponse.Field)localObject);
          String str = zapu;
          paramFastJsonResponse.addConcreteTypeArrayInternal((FastJsonResponse.Field)localObject, str, localArrayList);
        }
        next(0);
      }
    }
    catch (Throwable paramFastJsonResponse) {}catch (IOException paramFastJsonResponse)
    {
      label235:
      throw new ParseException(paramFastJsonResponse);
    }
    try
    {
      paramInputStream.close();
      return;
    }
    catch (IOException paramInputStream)
    {
      break label235;
    }
    Log.w("FastParser", "Failed to close reader while parsing.");
    return;
    label246:
    paramFastJsonResponse = new ParseException("Object array response class must have a single Field");
    throw paramFastJsonResponse;
    paramFastJsonResponse = new ParseException("No data to parse");
    throw paramFastJsonResponse;
    try
    {
      paramInputStream.close();
    }
    catch (IOException paramInputStream)
    {
      for (;;) {}
    }
    Log.w("FastParser", "Failed to close reader while parsing.");
    throw paramFastJsonResponse;
  }
  
  @KeepForSdk
  @ShowFirstParty
  public class ParseException
    extends Exception
  {
    public ParseException()
    {
      super();
    }
    
    public ParseException(Throwable paramThrowable)
    {
      super(paramThrowable);
    }
    
    public ParseException()
    {
      super();
    }
  }
  
  abstract interface zaa<O>
  {
    public abstract Object parse(FastParser paramFastParser, BufferedReader paramBufferedReader)
      throws FastParser.ParseException, IOException;
  }
}
