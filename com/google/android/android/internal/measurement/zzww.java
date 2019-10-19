package com.google.android.android.internal.measurement;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

final class zzww
{
  static final void create(StringBuilder paramStringBuilder, int paramInt, String paramString, Object paramObject)
  {
    if ((paramObject instanceof List))
    {
      paramObject = ((List)paramObject).iterator();
      while (paramObject.hasNext()) {
        create(paramStringBuilder, paramInt, paramString, paramObject.next());
      }
      return;
    }
    if ((paramObject instanceof Map))
    {
      paramObject = ((Map)paramObject).entrySet().iterator();
      while (paramObject.hasNext()) {
        create(paramStringBuilder, paramInt, paramString, (Map.Entry)paramObject.next());
      }
      return;
    }
    paramStringBuilder.append('\n');
    int j = 0;
    int k = 0;
    int i = 0;
    while (i < paramInt)
    {
      paramStringBuilder.append(' ');
      i += 1;
    }
    paramStringBuilder.append(paramString);
    if ((paramObject instanceof String))
    {
      paramStringBuilder.append(": \"");
      paramStringBuilder.append(zzxx.toString(zzud.zzfv((String)paramObject)));
      paramStringBuilder.append('"');
      return;
    }
    if ((paramObject instanceof zzud))
    {
      paramStringBuilder.append(": \"");
      paramStringBuilder.append(zzxx.toString((zzud)paramObject));
      paramStringBuilder.append('"');
      return;
    }
    if ((paramObject instanceof zzvm))
    {
      paramStringBuilder.append(" {");
      parse((zzvm)paramObject, paramStringBuilder, paramInt + 2);
      paramStringBuilder.append("\n");
      i = k;
      while (i < paramInt)
      {
        paramStringBuilder.append(' ');
        i += 1;
      }
      paramStringBuilder.append("}");
      return;
    }
    if ((paramObject instanceof Map.Entry))
    {
      paramStringBuilder.append(" {");
      paramString = (Map.Entry)paramObject;
      i = paramInt + 2;
      create(paramStringBuilder, i, "key", paramString.getKey());
      create(paramStringBuilder, i, "value", paramString.getValue());
      paramStringBuilder.append("\n");
      i = j;
      while (i < paramInt)
      {
        paramStringBuilder.append(' ');
        i += 1;
      }
      paramStringBuilder.append("}");
      return;
    }
    paramStringBuilder.append(": ");
    paramStringBuilder.append(paramObject.toString());
  }
  
  private static void parse(zzwt paramZzwt, StringBuilder paramStringBuilder, int paramInt)
  {
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    Object localObject1 = new TreeSet();
    Object localObject2 = paramZzwt.getClass().getDeclaredMethods();
    int j = localObject2.length;
    int i = 0;
    while (i < j)
    {
      localObject3 = localObject2[i];
      localHashMap2.put(((Method)localObject3).getName(), localObject3);
      if (((Method)localObject3).getParameterTypes().length == 0)
      {
        localHashMap1.put(((Method)localObject3).getName(), localObject3);
        if (((Method)localObject3).getName().startsWith("get")) {
          ((Set)localObject1).add(((Method)localObject3).getName());
        }
      }
      i += 1;
    }
    Object localObject3 = ((Set)localObject1).iterator();
    while (((Iterator)localObject3).hasNext())
    {
      localObject2 = (String)((Iterator)localObject3).next();
      Object localObject4 = ((String)localObject2).replaceFirst("get", "");
      Object localObject5;
      if ((((String)localObject4).endsWith("List")) && (!((String)localObject4).endsWith("OrBuilderList")) && (!((String)localObject4).equals("List")))
      {
        localObject1 = String.valueOf(((String)localObject4).substring(0, 1).toLowerCase());
        localObject5 = String.valueOf(((String)localObject4).substring(1, ((String)localObject4).length() - 4));
        if (((String)localObject5).length() != 0) {
          localObject1 = ((String)localObject1).concat((String)localObject5);
        } else {
          localObject1 = new String((String)localObject1);
        }
        localObject5 = (Method)localHashMap1.get(localObject2);
        if ((localObject5 != null) && (((Method)localObject5).getReturnType().equals(List.class)))
        {
          create(paramStringBuilder, paramInt, zzga((String)localObject1), zzvm.get((Method)localObject5, paramZzwt, new Object[0]));
          continue;
        }
      }
      if ((((String)localObject4).endsWith("Map")) && (!((String)localObject4).equals("Map")))
      {
        localObject1 = String.valueOf(((String)localObject4).substring(0, 1).toLowerCase());
        localObject5 = String.valueOf(((String)localObject4).substring(1, ((String)localObject4).length() - 3));
        if (((String)localObject5).length() != 0) {
          localObject1 = ((String)localObject1).concat((String)localObject5);
        } else {
          localObject1 = new String((String)localObject1);
        }
        localObject2 = (Method)localHashMap1.get(localObject2);
        if ((localObject2 != null) && (((Method)localObject2).getReturnType().equals(Map.class)) && (!((Method)localObject2).isAnnotationPresent(Deprecated.class)) && (Modifier.isPublic(((Method)localObject2).getModifiers())))
        {
          create(paramStringBuilder, paramInt, zzga((String)localObject1), zzvm.get((Method)localObject2, paramZzwt, new Object[0]));
          continue;
        }
      }
      localObject1 = String.valueOf(localObject4);
      if (((String)localObject1).length() != 0) {
        localObject1 = "set".concat((String)localObject1);
      } else {
        localObject1 = new String("set");
      }
      if ((Method)localHashMap2.get(localObject1) != null) {
        if (((String)localObject4).endsWith("Bytes"))
        {
          localObject1 = String.valueOf(((String)localObject4).substring(0, ((String)localObject4).length() - 5));
          if (((String)localObject1).length() != 0) {
            localObject1 = "get".concat((String)localObject1);
          } else {
            localObject1 = new String("get");
          }
          if (localHashMap1.containsKey(localObject1)) {}
        }
        else
        {
          localObject1 = String.valueOf(((String)localObject4).substring(0, 1).toLowerCase());
          localObject2 = String.valueOf(((String)localObject4).substring(1));
          if (((String)localObject2).length() != 0) {
            localObject1 = ((String)localObject1).concat((String)localObject2);
          } else {
            localObject1 = new String((String)localObject1);
          }
          localObject2 = String.valueOf(localObject4);
          if (((String)localObject2).length() != 0) {
            localObject2 = "get".concat((String)localObject2);
          } else {
            localObject2 = new String("get");
          }
          localObject5 = (Method)localHashMap1.get(localObject2);
          localObject2 = String.valueOf(localObject4);
          if (((String)localObject2).length() != 0) {
            localObject2 = "has".concat((String)localObject2);
          } else {
            localObject2 = new String("has");
          }
          localObject2 = (Method)localHashMap1.get(localObject2);
          if (localObject5 != null)
          {
            localObject4 = zzvm.get((Method)localObject5, paramZzwt, new Object[0]);
            boolean bool;
            if (localObject2 == null)
            {
              if ((localObject4 instanceof Boolean)) {
                if (((Boolean)localObject4).booleanValue()) {}
              }
              for (;;)
              {
                bool = true;
                break;
                label862:
                label886:
                label953:
                do
                {
                  do
                  {
                    do
                    {
                      do
                      {
                        do
                        {
                          bool = false;
                          break label975;
                          if (!(localObject4 instanceof Integer)) {
                            break;
                          }
                        } while (((Integer)localObject4).intValue() != 0);
                        break;
                        if (!(localObject4 instanceof Float)) {
                          break label862;
                        }
                      } while (((Float)localObject4).floatValue() != 0.0F);
                      break;
                      if (!(localObject4 instanceof Double)) {
                        break label886;
                      }
                    } while (((Double)localObject4).doubleValue() != 0.0D);
                    break;
                    if ((localObject4 instanceof String))
                    {
                      bool = localObject4.equals("");
                      break label975;
                    }
                    if ((localObject4 instanceof zzud))
                    {
                      bool = localObject4.equals(zzud.zzbtz);
                      break label975;
                    }
                    if (!(localObject4 instanceof zzwt)) {
                      break label953;
                    }
                  } while (localObject4 != ((zzwt)localObject4).zzwf());
                  break;
                } while ((!(localObject4 instanceof Enum)) || (((Enum)localObject4).ordinal() != 0));
              }
              label975:
              if (!bool) {
                bool = true;
              } else {
                bool = false;
              }
            }
            else
            {
              bool = ((Boolean)zzvm.get((Method)localObject2, paramZzwt, new Object[0])).booleanValue();
            }
            if (bool) {
              create(paramStringBuilder, paramInt, zzga((String)localObject1), localObject4);
            }
          }
        }
      }
    }
    if ((paramZzwt instanceof zzvm.zzc))
    {
      localObject1 = zzbys.iterator();
      if (((Iterator)localObject1).hasNext())
      {
        ((Map.Entry)((Iterator)localObject1).next()).getKey();
        throw new NoSuchMethodError();
      }
    }
    paramZzwt = (zzvm)paramZzwt;
    if (zzbym != null) {
      zzbym.index(paramStringBuilder, paramInt);
    }
  }
  
  static String safeString(zzwt paramZzwt, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("# ");
    localStringBuilder.append(paramString);
    parse(paramZzwt, localStringBuilder, 0);
    return localStringBuilder.toString();
  }
  
  private static final String zzga(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (i < paramString.length())
    {
      char c = paramString.charAt(i);
      if (Character.isUpperCase(c)) {
        localStringBuilder.append("_");
      }
      localStringBuilder.append(Character.toLowerCase(c));
      i += 1;
    }
    return localStringBuilder.toString();
  }
}
