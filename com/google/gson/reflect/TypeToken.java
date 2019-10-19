package com.google.gson.reflect;

import com.google.gson.internal..Gson.Preconditions;
import com.google.gson.internal..Gson.Types;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;

public class TypeToken<T>
{
  final int hashCode;
  final Class<? super T> rawType;
  final Type type;
  
  protected TypeToken()
  {
    type = getSuperclassTypeParameter(getClass());
    rawType = .Gson.Types.getRawType(type);
    hashCode = type.hashCode();
  }
  
  TypeToken(Type paramType)
  {
    type = .Gson.Types.canonicalize((Type).Gson.Preconditions.checkNotNull(paramType));
    rawType = .Gson.Types.getRawType(type);
    hashCode = type.hashCode();
  }
  
  private static AssertionError buildUnexpectedTypeError(Type paramType, Class... paramVarArgs)
  {
    StringBuilder localStringBuilder = new StringBuilder("Unexpected type. Expected one of: ");
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      localStringBuilder.append(paramVarArgs[i].getName());
      localStringBuilder.append(", ");
      i += 1;
    }
    localStringBuilder.append("but got: ");
    localStringBuilder.append(paramType.getClass().getName());
    localStringBuilder.append(", for type token: ");
    localStringBuilder.append(paramType.toString());
    localStringBuilder.append('.');
    return new AssertionError(localStringBuilder.toString());
  }
  
  public static TypeToken get(Class paramClass)
  {
    return new TypeToken(paramClass);
  }
  
  public static TypeToken get(Type paramType)
  {
    return new TypeToken(paramType);
  }
  
  public static TypeToken getArray(Type paramType)
  {
    return new TypeToken(.Gson.Types.arrayOf(paramType));
  }
  
  public static TypeToken getParameterized(Type paramType, Type... paramVarArgs)
  {
    return new TypeToken(.Gson.Types.newParameterizedTypeWithOwner(null, paramType, paramVarArgs));
  }
  
  static Type getSuperclassTypeParameter(Class paramClass)
  {
    paramClass = paramClass.getGenericSuperclass();
    if (!(paramClass instanceof Class)) {
      return .Gson.Types.canonicalize(((ParameterizedType)paramClass).getActualTypeArguments()[0]);
    }
    throw new RuntimeException("Missing type parameter.");
  }
  
  private static boolean isAssignableFrom(Type paramType, GenericArrayType paramGenericArrayType)
  {
    Object localObject = paramGenericArrayType.getGenericComponentType();
    if ((localObject instanceof ParameterizedType))
    {
      if ((paramType instanceof GenericArrayType))
      {
        paramGenericArrayType = ((GenericArrayType)paramType).getGenericComponentType();
      }
      else
      {
        paramGenericArrayType = paramType;
        if ((paramType instanceof Class)) {
          for (paramType = (Class)paramType;; paramType = ((Class)paramType).getComponentType())
          {
            paramGenericArrayType = paramType;
            if (!((Class)paramType).isArray()) {
              break;
            }
          }
        }
      }
      paramType = (ParameterizedType)localObject;
      localObject = new HashMap();
      return isAssignableFrom((Type)paramGenericArrayType, paramType, (Map)localObject);
    }
    return true;
  }
  
  private static boolean isAssignableFrom(Type paramType, ParameterizedType paramParameterizedType, Map paramMap)
  {
    int j = 0;
    if (paramType == null) {
      return false;
    }
    if (paramParameterizedType.equals(paramType)) {
      return true;
    }
    Class localClass = .Gson.Types.getRawType(paramType);
    ParameterizedType localParameterizedType = null;
    if ((paramType instanceof ParameterizedType)) {
      localParameterizedType = (ParameterizedType)paramType;
    }
    if (localParameterizedType != null)
    {
      Type[] arrayOfType = localParameterizedType.getActualTypeArguments();
      TypeVariable[] arrayOfTypeVariable = localClass.getTypeParameters();
      i = 0;
      while (i < arrayOfType.length)
      {
        paramType = arrayOfType[i];
        TypeVariable localTypeVariable = arrayOfTypeVariable[i];
        while ((paramType instanceof TypeVariable)) {
          paramType = (Type)paramMap.get(((TypeVariable)paramType).getName());
        }
        paramMap.put(localTypeVariable.getName(), paramType);
        i += 1;
      }
      if (typeEquals(localParameterizedType, paramParameterizedType, paramMap)) {
        return true;
      }
    }
    paramType = localClass.getGenericInterfaces();
    int k = paramType.length;
    int i = j;
    while (i < k)
    {
      if (isAssignableFrom(paramType[i], paramParameterizedType, new HashMap(paramMap))) {
        return true;
      }
      i += 1;
    }
    return isAssignableFrom(localClass.getGenericSuperclass(), paramParameterizedType, new HashMap(paramMap));
  }
  
  private static boolean matches(Type paramType1, Type paramType2, Map paramMap)
  {
    return (paramType2.equals(paramType1)) || (((paramType1 instanceof TypeVariable)) && (paramType2.equals(paramMap.get(((TypeVariable)paramType1).getName()))));
  }
  
  private static boolean typeEquals(ParameterizedType paramParameterizedType1, ParameterizedType paramParameterizedType2, Map paramMap)
  {
    if (paramParameterizedType1.getRawType().equals(paramParameterizedType2.getRawType()))
    {
      paramParameterizedType1 = paramParameterizedType1.getActualTypeArguments();
      paramParameterizedType2 = paramParameterizedType2.getActualTypeArguments();
      int i = 0;
      while (i < paramParameterizedType1.length)
      {
        if (!matches(paramParameterizedType1[i], paramParameterizedType2[i], paramMap)) {
          return false;
        }
        i += 1;
      }
      return true;
    }
    return false;
  }
  
  public final boolean equals(Object paramObject)
  {
    return ((paramObject instanceof TypeToken)) && (.Gson.Types.equals(type, type));
  }
  
  public final Class getRawType()
  {
    return rawType;
  }
  
  public final Type getType()
  {
    return type;
  }
  
  public final int hashCode()
  {
    return hashCode;
  }
  
  public boolean isAssignableFrom(TypeToken paramTypeToken)
  {
    return isAssignableFrom(paramTypeToken.getType());
  }
  
  public boolean isAssignableFrom(Class paramClass)
  {
    return isAssignableFrom(paramClass);
  }
  
  public boolean isAssignableFrom(Type paramType)
  {
    if (paramType == null) {
      return false;
    }
    if (type.equals(paramType)) {
      return true;
    }
    if ((type instanceof Class)) {
      return rawType.isAssignableFrom(.Gson.Types.getRawType(paramType));
    }
    if ((type instanceof ParameterizedType)) {
      return isAssignableFrom(paramType, (ParameterizedType)type, new HashMap());
    }
    if ((type instanceof GenericArrayType))
    {
      if ((rawType.isAssignableFrom(.Gson.Types.getRawType(paramType))) && (isAssignableFrom(paramType, (GenericArrayType)type))) {
        return true;
      }
    }
    else {
      throw buildUnexpectedTypeError(type, new Class[] { Class.class, ParameterizedType.class, GenericArrayType.class });
    }
    return false;
  }
  
  public final String toString()
  {
    return .Gson.Types.typeToString(type);
  }
}
