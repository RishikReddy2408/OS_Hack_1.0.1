package com.google.gson;

import com.google.gson.internal..Gson.Preconditions;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;

public final class FieldAttributes
{
  private final Field field;
  
  public FieldAttributes(Field paramField)
  {
    .Gson.Preconditions.checkNotNull(paramField);
    field = paramField;
  }
  
  Object get(Object paramObject)
    throws IllegalAccessException
  {
    return field.get(paramObject);
  }
  
  public Annotation getAnnotation(Class paramClass)
  {
    return field.getAnnotation(paramClass);
  }
  
  public Collection getAnnotations()
  {
    return Arrays.asList(field.getAnnotations());
  }
  
  public Class getDeclaredClass()
  {
    return field.getType();
  }
  
  public Type getDeclaredType()
  {
    return field.getGenericType();
  }
  
  public Class getDeclaringClass()
  {
    return field.getDeclaringClass();
  }
  
  public String getName()
  {
    return field.getName();
  }
  
  public boolean hasModifier(int paramInt)
  {
    return (paramInt & field.getModifiers()) != 0;
  }
  
  boolean isSynthetic()
  {
    return field.isSynthetic();
  }
}
