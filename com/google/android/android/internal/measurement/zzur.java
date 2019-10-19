package com.google.android.android.internal.measurement;

import java.io.IOException;
import java.util.List;
import java.util.Map;

final class zzur
  implements zzxi
{
  private int 0;
  private final zzuo zzbur;
  private int zzbus;
  private int zzbut = 0;
  
  private zzur(zzuo paramZzuo)
  {
    zzbur = ((zzuo)zzvo.attribute(paramZzuo, "input"));
    zzbur.zzbuk = this;
  }
  
  private final Object add(zzxj paramZzxj, zzuz paramZzuz)
    throws IOException
  {
    int i = zzbus;
    zzbus = (0 >>> 3 << 3 | 0x4);
    try
    {
      Object localObject = paramZzxj.newInstance();
      paramZzxj.toFile(localObject, this, paramZzuz);
      paramZzxj.multiply(localObject);
      int j = 0;
      int k = zzbus;
      if (j == k)
      {
        zzbus = i;
        return localObject;
      }
      throw zzvt.zzwq();
    }
    catch (Throwable paramZzxj)
    {
      zzbus = i;
      throw paramZzxj;
    }
  }
  
  private final Object getAttributeValue(zzxj paramZzxj, zzuz paramZzuz)
    throws IOException
  {
    int i = zzbur.zzup();
    if (zzbur.zzbuh < zzbur.zzbui)
    {
      i = zzbur.zzaq(i);
      Object localObject = paramZzxj.newInstance();
      zzuo localZzuo = zzbur;
      zzbuh += 1;
      paramZzxj.toFile(localObject, this, paramZzuz);
      paramZzxj.multiply(localObject);
      zzbur.zzan(0);
      paramZzxj = zzbur;
      zzbuh -= 1;
      zzbur.zzar(i);
      return localObject;
    }
    throw zzvt.zzwp();
  }
  
  private final void negotiate(List paramList, boolean paramBoolean)
    throws IOException
  {
    if ((0 & 0x7) == 2)
    {
      int i;
      if (((paramList instanceof zzwc)) && (!paramBoolean))
      {
        paramList = (zzwc)paramList;
        do
        {
          paramList.add(zzuo());
          if (zzbur.zzuw()) {
            return;
          }
          i = zzbur.zzug();
        } while (i == 0);
        zzbut = i;
        return;
      }
      do
      {
        String str;
        if (paramBoolean) {
          str = zzun();
        } else {
          str = readString();
        }
        paramList.add(str);
        if (zzbur.zzuw()) {
          return;
        }
        i = zzbur.zzug();
      } while (i == 0);
      zzbut = i;
      return;
    }
    throw zzvt.zzwo();
  }
  
  private final Object readValue(zzyq paramZzyq, Class paramClass, zzuz paramZzuz)
    throws IOException
  {
    switch (zzus.zzbuu[paramZzyq.ordinal()])
    {
    default: 
      throw new RuntimeException("unsupported field type.");
    case 17: 
      return Long.valueOf(zzuh());
    case 16: 
      return Integer.valueOf(zzup());
    case 15: 
      return zzun();
    case 14: 
      return Long.valueOf(zzuu());
    case 13: 
      return Integer.valueOf(zzut());
    case 12: 
      return Long.valueOf(zzus());
    case 11: 
      return Integer.valueOf(zzur());
    case 10: 
      zzat(2);
      return getAttributeValue(zzxf.zzxn().getAttributeValue(paramClass), paramZzuz);
    case 9: 
      return Long.valueOf(zzui());
    case 8: 
      return Integer.valueOf(zzuj());
    case 7: 
      return Float.valueOf(readFloat());
    case 6: 
      return Long.valueOf(zzuk());
    case 5: 
      return Integer.valueOf(zzul());
    case 4: 
      return Integer.valueOf(zzuq());
    case 3: 
      return Double.valueOf(readDouble());
    case 2: 
      return zzuo();
    }
    return Boolean.valueOf(zzum());
  }
  
  public static zzur subtract(zzuo paramZzuo)
  {
    if (zzbuk != null) {
      return zzbuk;
    }
    return new zzur(paramZzuo);
  }
  
  private final void zzat(int paramInt)
    throws IOException
  {
    if ((0 & 0x7) == paramInt) {
      return;
    }
    throw zzvt.zzwo();
  }
  
  private static void zzau(int paramInt)
    throws IOException
  {
    if ((paramInt & 0x7) == 0) {
      return;
    }
    throw zzvt.zzwq();
  }
  
  private static void zzav(int paramInt)
    throws IOException
  {
    if ((paramInt & 0x3) == 0) {
      return;
    }
    throw zzvt.zzwq();
  }
  
  private final void zzaw(int paramInt)
    throws IOException
  {
    if (zzbur.zzux() == paramInt) {
      return;
    }
    throw zzvt.zzwk();
  }
  
  public final void add(List paramList)
    throws IOException
  {
    negotiate(paramList, true);
  }
  
  public final void addSection(List paramList)
    throws IOException
  {
    if ((paramList instanceof zzub))
    {
      paramList = (zzub)paramList;
      i = 0 & 0x7;
      if (i != 0)
      {
        if (i == 2)
        {
          i = zzbur.zzup();
          i = zzbur.zzux() + i;
          do
          {
            paramList.addBoolean(zzbur.zzum());
          } while (zzbur.zzux() < i);
          zzaw(i);
          return;
        }
        throw zzvt.zzwo();
      }
      do
      {
        paramList.addBoolean(zzbur.zzum());
        if (zzbur.zzuw()) {
          return;
        }
        i = zzbur.zzug();
      } while (i == 0);
      zzbut = i;
      return;
    }
    int i = 0 & 0x7;
    if (i != 0)
    {
      if (i == 2)
      {
        i = zzbur.zzup();
        i = zzbur.zzux() + i;
        do
        {
          paramList.add(Boolean.valueOf(zzbur.zzum()));
        } while (zzbur.zzux() < i);
        zzaw(i);
        return;
      }
      throw zzvt.zzwo();
    }
    do
    {
      paramList.add(Boolean.valueOf(zzbur.zzum()));
      if (zzbur.zzuw()) {
        return;
      }
      i = zzbur.zzug();
    } while (i == 0);
    zzbut = i;
  }
  
  public final void blur(List paramList)
    throws IOException
  {
    if ((0 & 0x7) == 2)
    {
      int i;
      do
      {
        paramList.add(zzuo());
        if (zzbur.zzuw()) {
          return;
        }
        i = zzbur.zzug();
      } while (i == 0);
      zzbut = i;
      return;
    }
    throw zzvt.zzwo();
  }
  
  public final void copyTo(List paramList)
    throws IOException
  {
    if ((paramList instanceof zzwh))
    {
      paramList = (zzwh)paramList;
      i = 0 & 0x7;
      if (i != 0)
      {
        if (i == 2)
        {
          i = zzbur.zzup();
          i = zzbur.zzux() + i;
          do
          {
            paramList.zzbg(zzbur.zzuh());
          } while (zzbur.zzux() < i);
          zzaw(i);
          return;
        }
        throw zzvt.zzwo();
      }
      do
      {
        paramList.zzbg(zzbur.zzuh());
        if (zzbur.zzuw()) {
          return;
        }
        i = zzbur.zzug();
      } while (i == 0);
      zzbut = i;
      return;
    }
    int i = 0 & 0x7;
    if (i != 0)
    {
      if (i == 2)
      {
        i = zzbur.zzup();
        i = zzbur.zzux() + i;
        do
        {
          paramList.add(Long.valueOf(zzbur.zzuh()));
        } while (zzbur.zzux() < i);
        zzaw(i);
        return;
      }
      throw zzvt.zzwo();
    }
    do
    {
      paramList.add(Long.valueOf(zzbur.zzuh()));
      if (zzbur.zzuw()) {
        return;
      }
      i = zzbur.zzug();
    } while (i == 0);
    zzbut = i;
  }
  
  public final Object get(zzxj paramZzxj, zzuz paramZzuz)
    throws IOException
  {
    zzat(2);
    return getAttributeValue(paramZzxj, paramZzuz);
  }
  
  public final void getBytes(List paramList)
    throws IOException
  {
    if ((paramList instanceof zzvn))
    {
      paramList = (zzvn)paramList;
      i = 0 & 0x7;
      if (i != 2)
      {
        if (i == 5)
        {
          do
          {
            paramList.zzbm(zzbur.zzul());
            if (zzbur.zzuw()) {
              return;
            }
            i = zzbur.zzug();
          } while (i == 0);
          zzbut = i;
          return;
        }
        throw zzvt.zzwo();
      }
      i = zzbur.zzup();
      zzav(i);
      j = zzbur.zzux();
      do
      {
        paramList.zzbm(zzbur.zzul());
      } while (zzbur.zzux() < j + i);
      return;
    }
    int i = 0 & 0x7;
    if (i != 2)
    {
      if (i == 5)
      {
        do
        {
          paramList.add(Integer.valueOf(zzbur.zzul()));
          if (zzbur.zzuw()) {
            return;
          }
          i = zzbur.zzug();
        } while (i == 0);
        zzbut = i;
        return;
      }
      throw zzvt.zzwo();
    }
    i = zzbur.zzup();
    zzav(i);
    int j = zzbur.zzux();
    do
    {
      paramList.add(Integer.valueOf(zzbur.zzul()));
    } while (zzbur.zzux() < j + i);
  }
  
  public final void getNumber(List paramList)
    throws IOException
  {
    if ((paramList instanceof zzvn))
    {
      paramList = (zzvn)paramList;
      i = 0 & 0x7;
      if (i != 2)
      {
        if (i == 5)
        {
          do
          {
            paramList.zzbm(zzbur.zzur());
            if (zzbur.zzuw()) {
              return;
            }
            i = zzbur.zzug();
          } while (i == 0);
          zzbut = i;
          return;
        }
        throw zzvt.zzwo();
      }
      i = zzbur.zzup();
      zzav(i);
      j = zzbur.zzux();
      do
      {
        paramList.zzbm(zzbur.zzur());
      } while (zzbur.zzux() < j + i);
      return;
    }
    int i = 0 & 0x7;
    if (i != 2)
    {
      if (i == 5)
      {
        do
        {
          paramList.add(Integer.valueOf(zzbur.zzur()));
          if (zzbur.zzuw()) {
            return;
          }
          i = zzbur.zzug();
        } while (i == 0);
        zzbut = i;
        return;
      }
      throw zzvt.zzwo();
    }
    i = zzbur.zzup();
    zzav(i);
    int j = zzbur.zzux();
    do
    {
      paramList.add(Integer.valueOf(zzbur.zzur()));
    } while (zzbur.zzux() < j + i);
  }
  
  public final int getTag()
  {
    return 0;
  }
  
  public final void initBook(List paramList)
    throws IOException
  {
    if ((paramList instanceof zzvn))
    {
      paramList = (zzvn)paramList;
      i = 0 & 0x7;
      if (i != 0)
      {
        if (i == 2)
        {
          i = zzbur.zzup();
          i = zzbur.zzux() + i;
          do
          {
            paramList.zzbm(zzbur.zzuq());
          } while (zzbur.zzux() < i);
          zzaw(i);
          return;
        }
        throw zzvt.zzwo();
      }
      do
      {
        paramList.zzbm(zzbur.zzuq());
        if (zzbur.zzuw()) {
          return;
        }
        i = zzbur.zzug();
      } while (i == 0);
      zzbut = i;
      return;
    }
    int i = 0 & 0x7;
    if (i != 0)
    {
      if (i == 2)
      {
        i = zzbur.zzup();
        i = zzbur.zzux() + i;
        do
        {
          paramList.add(Integer.valueOf(zzbur.zzuq()));
        } while (zzbur.zzux() < i);
        zzaw(i);
        return;
      }
      throw zzvt.zzwo();
    }
    do
    {
      paramList.add(Integer.valueOf(zzbur.zzuq()));
      if (zzbur.zzuw()) {
        return;
      }
      i = zzbur.zzug();
    } while (i == 0);
    zzbut = i;
  }
  
  public final void intersect(List paramList)
    throws IOException
  {
    if ((paramList instanceof zzvn))
    {
      paramList = (zzvn)paramList;
      i = 0 & 0x7;
      if (i != 0)
      {
        if (i == 2)
        {
          i = zzbur.zzup();
          i = zzbur.zzux() + i;
          do
          {
            paramList.zzbm(zzbur.zzuj());
          } while (zzbur.zzux() < i);
          zzaw(i);
          return;
        }
        throw zzvt.zzwo();
      }
      do
      {
        paramList.zzbm(zzbur.zzuj());
        if (zzbur.zzuw()) {
          return;
        }
        i = zzbur.zzug();
      } while (i == 0);
      zzbut = i;
      return;
    }
    int i = 0 & 0x7;
    if (i != 0)
    {
      if (i == 2)
      {
        i = zzbur.zzup();
        i = zzbur.zzux() + i;
        do
        {
          paramList.add(Integer.valueOf(zzbur.zzuj()));
        } while (zzbur.zzux() < i);
        zzaw(i);
        return;
      }
      throw zzvt.zzwo();
    }
    do
    {
      paramList.add(Integer.valueOf(zzbur.zzuj()));
      if (zzbur.zzuw()) {
        return;
      }
      i = zzbur.zzug();
    } while (i == 0);
    zzbut = i;
  }
  
  public final void offset(List paramList)
    throws IOException
  {
    int i;
    int j;
    if ((paramList instanceof zzwh))
    {
      paramList = (zzwh)paramList;
      switch (0 & 0x7)
      {
      default: 
        throw zzvt.zzwo();
      case 2: 
        i = zzbur.zzup();
        zzau(i);
        j = zzbur.zzux();
        do
        {
          paramList.zzbg(zzbur.zzus());
        } while (zzbur.zzux() < j + i);
        return;
      }
      do
      {
        paramList.zzbg(zzbur.zzus());
        if (zzbur.zzuw()) {
          return;
        }
        i = zzbur.zzug();
      } while (i == 0);
      zzbut = i;
      return;
    }
    switch (0 & 0x7)
    {
    default: 
      throw zzvt.zzwo();
    case 2: 
      i = zzbur.zzup();
      zzau(i);
      j = zzbur.zzux();
      do
      {
        paramList.add(Long.valueOf(zzbur.zzus()));
      } while (zzbur.zzux() < j + i);
      return;
    }
    do
    {
      paramList.add(Long.valueOf(zzbur.zzus()));
      if (zzbur.zzuw()) {
        return;
      }
      i = zzbur.zzug();
    } while (i == 0);
    zzbut = i;
  }
  
  public final void processWays(List paramList)
    throws IOException
  {
    if ((paramList instanceof zzwh))
    {
      paramList = (zzwh)paramList;
      i = 0 & 0x7;
      if (i != 0)
      {
        if (i == 2)
        {
          i = zzbur.zzup();
          i = zzbur.zzux() + i;
          do
          {
            paramList.zzbg(zzbur.zzui());
          } while (zzbur.zzux() < i);
          zzaw(i);
          return;
        }
        throw zzvt.zzwo();
      }
      do
      {
        paramList.zzbg(zzbur.zzui());
        if (zzbur.zzuw()) {
          return;
        }
        i = zzbur.zzug();
      } while (i == 0);
      zzbut = i;
      return;
    }
    int i = 0 & 0x7;
    if (i != 0)
    {
      if (i == 2)
      {
        i = zzbur.zzup();
        i = zzbur.zzux() + i;
        do
        {
          paramList.add(Long.valueOf(zzbur.zzui()));
        } while (zzbur.zzux() < i);
        zzaw(i);
        return;
      }
      throw zzvt.zzwo();
    }
    do
    {
      paramList.add(Long.valueOf(zzbur.zzui()));
      if (zzbur.zzuw()) {
        return;
      }
      i = zzbur.zzug();
    } while (i == 0);
    zzbut = i;
  }
  
  public final Object read(zzxj paramZzxj, zzuz paramZzuz)
    throws IOException
  {
    zzat(3);
    return add(paramZzxj, paramZzuz);
  }
  
  public final void read(List paramList)
    throws IOException
  {
    if ((paramList instanceof zzvj))
    {
      paramList = (zzvj)paramList;
      i = 0 & 0x7;
      if (i != 2)
      {
        if (i == 5)
        {
          do
          {
            paramList.add(zzbur.readFloat());
            if (zzbur.zzuw()) {
              return;
            }
            i = zzbur.zzug();
          } while (i == 0);
          zzbut = i;
          return;
        }
        throw zzvt.zzwo();
      }
      i = zzbur.zzup();
      zzav(i);
      j = zzbur.zzux();
      do
      {
        paramList.add(zzbur.readFloat());
      } while (zzbur.zzux() < j + i);
      return;
    }
    int i = 0 & 0x7;
    if (i != 2)
    {
      if (i == 5)
      {
        do
        {
          paramList.add(Float.valueOf(zzbur.readFloat()));
          if (zzbur.zzuw()) {
            return;
          }
          i = zzbur.zzug();
        } while (i == 0);
        zzbut = i;
        return;
      }
      throw zzvt.zzwo();
    }
    i = zzbur.zzup();
    zzav(i);
    int j = zzbur.zzux();
    do
    {
      paramList.add(Float.valueOf(zzbur.readFloat()));
    } while (zzbur.zzux() < j + i);
  }
  
  public final void readDocument(List paramList, zzxj paramZzxj, zzuz paramZzuz)
    throws IOException
  {
    if ((0 & 0x7) == 2)
    {
      int i = 0;
      int j;
      do
      {
        paramList.add(getAttributeValue(paramZzxj, paramZzuz));
        if (zzbur.zzuw()) {
          break;
        }
        if (zzbut != 0) {
          return;
        }
        j = zzbur.zzug();
      } while (j == i);
      zzbut = j;
      return;
    }
    throw zzvt.zzwo();
  }
  
  public final double readDouble()
    throws IOException
  {
    zzat(1);
    return zzbur.readDouble();
  }
  
  public final float readFloat()
    throws IOException
  {
    zzat(5);
    return zzbur.readFloat();
  }
  
  public final void readFromParcel(List paramList)
    throws IOException
  {
    int i;
    int j;
    if ((paramList instanceof zzuw))
    {
      paramList = (zzuw)paramList;
      switch (0 & 0x7)
      {
      default: 
        throw zzvt.zzwo();
      case 2: 
        i = zzbur.zzup();
        zzau(i);
        j = zzbur.zzux();
        do
        {
          paramList.add(zzbur.readDouble());
        } while (zzbur.zzux() < j + i);
        return;
      }
      do
      {
        paramList.add(zzbur.readDouble());
        if (zzbur.zzuw()) {
          return;
        }
        i = zzbur.zzug();
      } while (i == 0);
      zzbut = i;
      return;
    }
    switch (0 & 0x7)
    {
    default: 
      throw zzvt.zzwo();
    case 2: 
      i = zzbur.zzup();
      zzau(i);
      j = zzbur.zzux();
      do
      {
        paramList.add(Double.valueOf(zzbur.readDouble()));
      } while (zzbur.zzux() < j + i);
      return;
    }
    do
    {
      paramList.add(Double.valueOf(zzbur.readDouble()));
      if (zzbur.zzuw()) {
        return;
      }
      i = zzbur.zzug();
    } while (i == 0);
    zzbut = i;
  }
  
  public final String readString()
    throws IOException
  {
    zzat(2);
    return zzbur.readString();
  }
  
  public final void readStringList(List paramList)
    throws IOException
  {
    negotiate(paramList, false);
  }
  
  public final void replace(List paramList)
    throws IOException
  {
    if ((paramList instanceof zzvn))
    {
      paramList = (zzvn)paramList;
      i = 0 & 0x7;
      if (i != 0)
      {
        if (i == 2)
        {
          i = zzbur.zzup();
          i = zzbur.zzux() + i;
          do
          {
            paramList.zzbm(zzbur.zzup());
          } while (zzbur.zzux() < i);
          zzaw(i);
          return;
        }
        throw zzvt.zzwo();
      }
      do
      {
        paramList.zzbm(zzbur.zzup());
        if (zzbur.zzuw()) {
          return;
        }
        i = zzbur.zzug();
      } while (i == 0);
      zzbut = i;
      return;
    }
    int i = 0 & 0x7;
    if (i != 0)
    {
      if (i == 2)
      {
        i = zzbur.zzup();
        i = zzbur.zzux() + i;
        do
        {
          paramList.add(Integer.valueOf(zzbur.zzup()));
        } while (zzbur.zzux() < i);
        zzaw(i);
        return;
      }
      throw zzvt.zzwo();
    }
    do
    {
      paramList.add(Integer.valueOf(zzbur.zzup()));
      if (zzbur.zzuw()) {
        return;
      }
      i = zzbur.zzug();
    } while (i == 0);
    zzbut = i;
  }
  
  public final void replaceAll(List paramList)
    throws IOException
  {
    int i;
    int j;
    if ((paramList instanceof zzwh))
    {
      paramList = (zzwh)paramList;
      switch (0 & 0x7)
      {
      default: 
        throw zzvt.zzwo();
      case 2: 
        i = zzbur.zzup();
        zzau(i);
        j = zzbur.zzux();
        do
        {
          paramList.zzbg(zzbur.zzuk());
        } while (zzbur.zzux() < j + i);
        return;
      }
      do
      {
        paramList.zzbg(zzbur.zzuk());
        if (zzbur.zzuw()) {
          return;
        }
        i = zzbur.zzug();
      } while (i == 0);
      zzbut = i;
      return;
    }
    switch (0 & 0x7)
    {
    default: 
      throw zzvt.zzwo();
    case 2: 
      i = zzbur.zzup();
      zzau(i);
      j = zzbur.zzux();
      do
      {
        paramList.add(Long.valueOf(zzbur.zzuk()));
      } while (zzbur.zzux() < j + i);
      return;
    }
    do
    {
      paramList.add(Long.valueOf(zzbur.zzuk()));
      if (zzbur.zzuw()) {
        return;
      }
      i = zzbur.zzug();
    } while (i == 0);
    zzbut = i;
  }
  
  public final void toFile(List paramList)
    throws IOException
  {
    if ((paramList instanceof zzvn))
    {
      paramList = (zzvn)paramList;
      i = 0 & 0x7;
      if (i != 0)
      {
        if (i == 2)
        {
          i = zzbur.zzup();
          i = zzbur.zzux() + i;
          do
          {
            paramList.zzbm(zzbur.zzut());
          } while (zzbur.zzux() < i);
          zzaw(i);
          return;
        }
        throw zzvt.zzwo();
      }
      do
      {
        paramList.zzbm(zzbur.zzut());
        if (zzbur.zzuw()) {
          return;
        }
        i = zzbur.zzug();
      } while (i == 0);
      zzbut = i;
      return;
    }
    int i = 0 & 0x7;
    if (i != 0)
    {
      if (i == 2)
      {
        i = zzbur.zzup();
        i = zzbur.zzux() + i;
        do
        {
          paramList.add(Integer.valueOf(zzbur.zzut()));
        } while (zzbur.zzux() < i);
        zzaw(i);
        return;
      }
      throw zzvt.zzwo();
    }
    do
    {
      paramList.add(Integer.valueOf(zzbur.zzut()));
      if (zzbur.zzuw()) {
        return;
      }
      i = zzbur.zzug();
    } while (i == 0);
    zzbut = i;
  }
  
  public final void toFile(List paramList, zzxj paramZzxj, zzuz paramZzuz)
    throws IOException
  {
    if ((0 & 0x7) == 3)
    {
      int i = 0;
      int j;
      do
      {
        paramList.add(add(paramZzxj, paramZzuz));
        if (zzbur.zzuw()) {
          break;
        }
        if (zzbut != 0) {
          return;
        }
        j = zzbur.zzug();
      } while (j == i);
      zzbut = j;
      return;
    }
    throw zzvt.zzwo();
  }
  
  public final void upgradeDatabase(List paramList)
    throws IOException
  {
    if ((paramList instanceof zzwh))
    {
      paramList = (zzwh)paramList;
      i = 0 & 0x7;
      if (i != 0)
      {
        if (i == 2)
        {
          i = zzbur.zzup();
          i = zzbur.zzux() + i;
          do
          {
            paramList.zzbg(zzbur.zzuu());
          } while (zzbur.zzux() < i);
          zzaw(i);
          return;
        }
        throw zzvt.zzwo();
      }
      do
      {
        paramList.zzbg(zzbur.zzuu());
        if (zzbur.zzuw()) {
          return;
        }
        i = zzbur.zzug();
      } while (i == 0);
      zzbut = i;
      return;
    }
    int i = 0 & 0x7;
    if (i != 0)
    {
      if (i == 2)
      {
        i = zzbur.zzup();
        i = zzbur.zzux() + i;
        do
        {
          paramList.add(Long.valueOf(zzbur.zzuu()));
        } while (zzbur.zzux() < i);
        zzaw(i);
        return;
      }
      throw zzvt.zzwo();
    }
    do
    {
      paramList.add(Long.valueOf(zzbur.zzuu()));
      if (zzbur.zzuw()) {
        return;
      }
      i = zzbur.zzug();
    } while (i == 0);
    zzbut = i;
  }
  
  public final void writeValue(Map paramMap, zzwm paramZzwm, zzuz paramZzuz)
    throws IOException
  {
    zzat(2);
    i = zzbur.zzup();
    i = zzbur.zzaq(i);
    Object localObject1 = zzcas;
    Object localObject2 = zzbre;
    do
    {
      for (;;)
      {
        try
        {
          int j = zzve();
          if (j != Integer.MAX_VALUE)
          {
            bool = zzbur.zzuw();
            if (!bool) {
              switch (j)
              {
              }
            }
          }
        }
        catch (Throwable paramMap)
        {
          boolean bool;
          Object localObject3;
          Object localObject4;
          zzbur.zzar(i);
          throw paramMap;
        }
        try
        {
          bool = zzvf();
          continue;
          localObject3 = zzcat;
          localObject4 = zzbre;
          localObject3 = readValue((zzyq)localObject3, localObject4.getClass(), paramZzuz);
          localObject2 = localObject3;
          continue;
          localObject3 = zzcar;
          localObject3 = readValue((zzyq)localObject3, null, null);
          localObject1 = localObject3;
          continue;
          if (!bool) {
            throw new zzvt("Unable to parse map entry.");
          }
        }
        catch (zzvu localZzvu) {}
      }
      bool = zzvf();
    } while (bool);
    throw new zzvt("Unable to parse map entry.");
    paramMap.put(localObject1, localObject2);
    zzbur.zzar(i);
  }
  
  public final long zzuh()
    throws IOException
  {
    zzat(0);
    return zzbur.zzuh();
  }
  
  public final long zzui()
    throws IOException
  {
    zzat(0);
    return zzbur.zzui();
  }
  
  public final int zzuj()
    throws IOException
  {
    zzat(0);
    return zzbur.zzuj();
  }
  
  public final long zzuk()
    throws IOException
  {
    zzat(1);
    return zzbur.zzuk();
  }
  
  public final int zzul()
    throws IOException
  {
    zzat(5);
    return zzbur.zzul();
  }
  
  public final boolean zzum()
    throws IOException
  {
    zzat(0);
    return zzbur.zzum();
  }
  
  public final String zzun()
    throws IOException
  {
    zzat(2);
    return zzbur.zzun();
  }
  
  public final zzud zzuo()
    throws IOException
  {
    zzat(2);
    return zzbur.zzuo();
  }
  
  public final int zzup()
    throws IOException
  {
    zzat(0);
    return zzbur.zzup();
  }
  
  public final int zzuq()
    throws IOException
  {
    zzat(0);
    return zzbur.zzuq();
  }
  
  public final int zzur()
    throws IOException
  {
    zzat(5);
    return zzbur.zzur();
  }
  
  public final long zzus()
    throws IOException
  {
    zzat(1);
    return zzbur.zzus();
  }
  
  public final int zzut()
    throws IOException
  {
    zzat(0);
    return zzbur.zzut();
  }
  
  public final long zzuu()
    throws IOException
  {
    zzat(0);
    return zzbur.zzuu();
  }
  
  public final int zzve()
    throws IOException
  {
    if (zzbut != 0)
    {
      0 = zzbut;
      zzbut = 0;
    }
    else
    {
      0 = zzbur.zzug();
    }
    if ((0 != 0) && (0 != zzbus)) {
      return 0 >>> 3;
    }
    return Integer.MAX_VALUE;
  }
  
  public final boolean zzvf()
    throws IOException
  {
    if ((!zzbur.zzuw()) && (0 != zzbus)) {
      return zzbur.zzao(0);
    }
    return false;
  }
}
