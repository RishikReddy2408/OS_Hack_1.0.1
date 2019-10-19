package com.google.android.android.common.internal.safeparcel;

import android.os.Parcelable;
import java.lang.annotation.Annotation;

public abstract interface SafeParcelable
  extends Parcelable
{
  public static final String NULL = "SAFE_PARCELABLE_NULL_STRING";
  
  public @interface Class
  {
    String creator();
    
    boolean validate();
  }
  
  public @interface Constructor {}
  
  public @interface Field
  {
    int access$getMMinValue();
    
    String defaultValue();
    
    String defaultValueUnchecked();
    
    String getter();
    
    String type();
  }
  
  public @interface Indicator
  {
    String getter();
  }
  
  public @interface Param
  {
    int access$getMMinValue();
  }
  
  public @interface Reserved
  {
    int[] value();
  }
  
  public @interface VersionField
  {
    int access$getMMinValue();
    
    String getter();
    
    String type();
  }
}
