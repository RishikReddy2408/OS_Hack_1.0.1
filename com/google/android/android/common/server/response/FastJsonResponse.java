package com.google.android.android.common.server.response;

import android.os.Parcel;
import android.util.Log;
import com.google.android.android.common.internal.Objects;
import com.google.android.android.common.internal.Objects.ToStringHelper;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.android.common.server.converter.MapPack;
import com.google.android.android.common.util.Base64Utils;
import com.google.android.android.common.util.JsonUtils;
import com.google.android.android.common.util.MapUtils;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.util.VisibleForTesting;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@KeepForSdk
@ShowFirstParty
public abstract class FastJsonResponse
{
  public FastJsonResponse() {}
  
  private static boolean add(String paramString, Object paramObject)
  {
    if (paramObject == null)
    {
      if (Log.isLoggable("FastJsonResponse", 6))
      {
        paramObject = new StringBuilder(String.valueOf(paramString).length() + 58);
        paramObject.append("Output field (");
        paramObject.append(paramString);
        paramObject.append(") has a null value, but expected a primitive");
        Log.e("FastJsonResponse", paramObject.toString());
      }
      return false;
    }
    return true;
  }
  
  private static void set(StringBuilder paramStringBuilder, Field paramField, Object paramObject)
  {
    if (zapq == 11)
    {
      paramStringBuilder.append(((FastJsonResponse)zapw.cast(paramObject)).toString());
      return;
    }
    if (zapq == 7)
    {
      paramStringBuilder.append("\"");
      paramStringBuilder.append(JsonUtils.escapeString((String)paramObject));
      paramStringBuilder.append("\"");
      return;
    }
    paramStringBuilder.append(paramObject);
  }
  
  protected static Object valueOf(Field paramField, Object paramObject)
  {
    Object localObject = paramObject;
    if (Field.toString(paramField) != null) {
      localObject = paramField.convertBack(paramObject);
    }
    return localObject;
  }
  
  private final void write(Field paramField, Object paramObject)
  {
    String str = zapu;
    paramObject = paramField.convert(paramObject);
    switch (zaps)
    {
    default: 
      break;
    case 3: 
      int i = zaps;
      paramField = new StringBuilder(44);
      paramField.append("Unsupported type for conversion: ");
      paramField.append(i);
      throw new IllegalStateException(paramField.toString());
    case 8: 
    case 9: 
      if (!add(str, paramObject)) {
        return;
      }
      setDecodedBytesInternal(paramField, str, (byte[])paramObject);
      return;
    case 7: 
      setStringInternal(paramField, str, (String)paramObject);
      return;
    case 6: 
      if (!add(str, paramObject)) {
        return;
      }
      setBooleanInternal(paramField, str, ((Boolean)paramObject).booleanValue());
      return;
    case 5: 
      writeValue(paramField, str, (BigDecimal)paramObject);
      return;
    case 4: 
      if (!add(str, paramObject)) {
        return;
      }
      writeValue(paramField, str, ((Double)paramObject).doubleValue());
      return;
    case 2: 
      if (!add(str, paramObject)) {
        return;
      }
      setLongInternal(paramField, str, ((Long)paramObject).longValue());
      return;
    case 1: 
      writeField(paramField, str, (BigInteger)paramObject);
      return;
    }
    if (add(str, paramObject)) {
      setIntegerInternal(paramField, str, ((Integer)paramObject).intValue());
    }
  }
  
  protected void addAll(Field paramField, String paramString, ArrayList paramArrayList)
  {
    throw new UnsupportedOperationException("Float list not supported");
  }
  
  public void addConcreteTypeArrayInternal(Field paramField, String paramString, ArrayList paramArrayList)
  {
    throw new UnsupportedOperationException("Concrete type array not supported");
  }
  
  public void addConcreteTypeInternal(Field paramField, String paramString, FastJsonResponse paramFastJsonResponse)
  {
    throw new UnsupportedOperationException("Concrete type not supported");
  }
  
  public final void addProperty(Field paramField, String paramString)
  {
    if (Field.toString(paramField) != null)
    {
      write(paramField, paramString);
      return;
    }
    setStringInternal(paramField, zapu, paramString);
  }
  
  public final void addProperty(Field paramField, ArrayList paramArrayList)
  {
    if (Field.toString(paramField) != null)
    {
      write(paramField, paramArrayList);
      return;
    }
    copyAll(paramField, zapu, paramArrayList);
  }
  
  protected void copyAll(Field paramField, String paramString, ArrayList paramArrayList)
  {
    throw new UnsupportedOperationException("Boolean list not supported");
  }
  
  protected void execute(Field paramField, String paramString, ArrayList paramArrayList)
  {
    throw new UnsupportedOperationException("Long list not supported");
  }
  
  public abstract Map getFieldMappings();
  
  protected Object getFieldValue(Field paramField)
  {
    Object localObject = zapu;
    if (zapw != null)
    {
      if (getValueObject(zapu) == null) {
        bool = true;
      } else {
        bool = false;
      }
      Preconditions.checkState(bool, "Concrete field shouldn't be value object: %s", new Object[] { zapu });
      boolean bool = zapt;
      try
      {
        char c = Character.toUpperCase(((String)localObject).charAt(0));
        paramField = ((String)localObject).substring(1);
        int i = String.valueOf(paramField).length();
        localObject = new StringBuilder(i + 4);
        ((StringBuilder)localObject).append("get");
        ((StringBuilder)localObject).append(c);
        ((StringBuilder)localObject).append(paramField);
        paramField = ((StringBuilder)localObject).toString();
        localObject = getClass();
        paramField = ((Class)localObject).getMethod(paramField, new Class[0]);
        paramField = paramField.invoke(this, new Object[0]);
        return paramField;
      }
      catch (Exception paramField)
      {
        throw new RuntimeException(paramField);
      }
    }
    return getValueObject(zapu);
  }
  
  protected abstract Object getValueObject(String paramString);
  
  protected boolean isFieldSet(Field paramField)
  {
    if (zaps == 11)
    {
      if (zapt)
      {
        paramField = zapu;
        throw new UnsupportedOperationException("Concrete type arrays not supported");
      }
      paramField = zapu;
      throw new UnsupportedOperationException("Concrete types not supported");
    }
    return isPrimitiveFieldSet(zapu);
  }
  
  protected abstract boolean isPrimitiveFieldSet(String paramString);
  
  protected void setBooleanInternal(Field paramField, String paramString, boolean paramBoolean)
  {
    throw new UnsupportedOperationException("Boolean not supported");
  }
  
  protected void setDecodedBytesInternal(Field paramField, String paramString, byte[] paramArrayOfByte)
  {
    throw new UnsupportedOperationException("byte[] not supported");
  }
  
  protected void setIntegerInternal(Field paramField, String paramString, int paramInt)
  {
    throw new UnsupportedOperationException("Integer not supported");
  }
  
  protected void setLongInternal(Field paramField, String paramString, long paramLong)
  {
    throw new UnsupportedOperationException("Long not supported");
  }
  
  protected void setStringInternal(Field paramField, String paramString1, String paramString2)
  {
    throw new UnsupportedOperationException("String not supported");
  }
  
  protected void setStringsInternal(Field paramField, String paramString, ArrayList paramArrayList)
  {
    throw new UnsupportedOperationException("String list not supported");
  }
  
  protected void subtract(Field paramField, String paramString, ArrayList paramArrayList)
  {
    throw new UnsupportedOperationException("Double list not supported");
  }
  
  public String toString()
  {
    Map localMap = getFieldMappings();
    StringBuilder localStringBuilder = new StringBuilder(100);
    Iterator localIterator = localMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject1 = (String)localIterator.next();
      Field localField = (Field)localMap.get(localObject1);
      if (isFieldSet(localField))
      {
        Object localObject2 = valueOf(localField, getFieldValue(localField));
        if (localStringBuilder.length() == 0) {
          localStringBuilder.append("{");
        } else {
          localStringBuilder.append(",");
        }
        localStringBuilder.append("\"");
        localStringBuilder.append((String)localObject1);
        localStringBuilder.append("\":");
        if (localObject2 == null)
        {
          localStringBuilder.append("null");
        }
        else
        {
          int i;
          int j;
          switch (zaps)
          {
          default: 
            if (zapr)
            {
              localObject1 = (ArrayList)localObject2;
              localStringBuilder.append("[");
              i = 0;
              j = ((ArrayList)localObject1).size();
            }
            break;
          case 10: 
            MapUtils.writeStringMapToJson(localStringBuilder, (HashMap)localObject2);
            break;
          case 9: 
            localStringBuilder.append("\"");
            localStringBuilder.append(Base64Utils.encodeUrlSafe((byte[])localObject2));
            localStringBuilder.append("\"");
            break;
          case 8: 
            localStringBuilder.append("\"");
            localStringBuilder.append(Base64Utils.encode((byte[])localObject2));
            localStringBuilder.append("\"");
            continue;
            while (i < j)
            {
              if (i > 0) {
                localStringBuilder.append(",");
              }
              localObject2 = ((ArrayList)localObject1).get(i);
              if (localObject2 != null) {
                set(localStringBuilder, localField, localObject2);
              }
              i += 1;
            }
            localStringBuilder.append("]");
            continue;
            set(localStringBuilder, localField, localObject2);
          }
        }
      }
    }
    if (localStringBuilder.length() > 0) {
      localStringBuilder.append("}");
    } else {
      localStringBuilder.append("{}");
    }
    return localStringBuilder.toString();
  }
  
  protected void trim(Field paramField, String paramString, ArrayList paramArrayList)
  {
    throw new UnsupportedOperationException("BigInteger list not supported");
  }
  
  protected void write(Field paramField, String paramString, ArrayList paramArrayList)
  {
    throw new UnsupportedOperationException("Integer list not supported");
  }
  
  public final void write(Field paramField, BigDecimal paramBigDecimal)
  {
    if (Field.toString(paramField) != null)
    {
      write(paramField, paramBigDecimal);
      return;
    }
    writeValue(paramField, zapu, paramBigDecimal);
  }
  
  public final void write(Field paramField, BigInteger paramBigInteger)
  {
    if (Field.toString(paramField) != null)
    {
      write(paramField, paramBigInteger);
      return;
    }
    writeField(paramField, zapu, paramBigInteger);
  }
  
  public final void write(Field paramField, ArrayList paramArrayList)
  {
    if (Field.toString(paramField) != null)
    {
      write(paramField, paramArrayList);
      return;
    }
    execute(paramField, zapu, paramArrayList);
  }
  
  public final void write(Field paramField, Map paramMap)
  {
    if (Field.toString(paramField) != null)
    {
      write(paramField, paramMap);
      return;
    }
    writeValue(paramField, zapu, paramMap);
  }
  
  public final void writeElement(Field paramField, ArrayList paramArrayList)
  {
    if (Field.toString(paramField) != null)
    {
      write(paramField, paramArrayList);
      return;
    }
    trim(paramField, zapu, paramArrayList);
  }
  
  public final void writeEndArray(Field paramField, ArrayList paramArrayList)
  {
    if (Field.toString(paramField) != null)
    {
      write(paramField, paramArrayList);
      return;
    }
    write(paramField, zapu, paramArrayList);
  }
  
  public final void writeEndObject(Field paramField, ArrayList paramArrayList)
  {
    if (Field.toString(paramField) != null)
    {
      write(paramField, paramArrayList);
      return;
    }
    writeValue(paramField, zapu, paramArrayList);
  }
  
  protected void writeField(Field paramField, String paramString, BigInteger paramBigInteger)
  {
    throw new UnsupportedOperationException("BigInteger not supported");
  }
  
  public final void writeNumber(Field paramField, double paramDouble)
  {
    if (Field.toString(paramField) != null)
    {
      write(paramField, Double.valueOf(paramDouble));
      return;
    }
    writeValue(paramField, zapu, paramDouble);
  }
  
  public final void writeNumber(Field paramField, float paramFloat)
  {
    if (Field.toString(paramField) != null)
    {
      write(paramField, Float.valueOf(paramFloat));
      return;
    }
    writeValue(paramField, zapu, paramFloat);
  }
  
  public final void writeNumber(Field paramField, int paramInt)
  {
    if (Field.toString(paramField) != null)
    {
      write(paramField, Integer.valueOf(paramInt));
      return;
    }
    setIntegerInternal(paramField, zapu, paramInt);
  }
  
  public final void writeNumber(Field paramField, long paramLong)
  {
    if (Field.toString(paramField) != null)
    {
      write(paramField, Long.valueOf(paramLong));
      return;
    }
    setLongInternal(paramField, zapu, paramLong);
  }
  
  public final void writeNumber(Field paramField, ArrayList paramArrayList)
  {
    if (Field.toString(paramField) != null)
    {
      write(paramField, paramArrayList);
      return;
    }
    subtract(paramField, zapu, paramArrayList);
  }
  
  public final void writeStartArray(Field paramField, ArrayList paramArrayList)
  {
    if (Field.toString(paramField) != null)
    {
      write(paramField, paramArrayList);
      return;
    }
    addAll(paramField, zapu, paramArrayList);
  }
  
  protected void writeValue(Field paramField, String paramString, double paramDouble)
  {
    throw new UnsupportedOperationException("Double not supported");
  }
  
  protected void writeValue(Field paramField, String paramString, float paramFloat)
  {
    throw new UnsupportedOperationException("Float not supported");
  }
  
  protected void writeValue(Field paramField, String paramString, BigDecimal paramBigDecimal)
  {
    throw new UnsupportedOperationException("BigDecimal not supported");
  }
  
  protected void writeValue(Field paramField, String paramString, ArrayList paramArrayList)
  {
    throw new UnsupportedOperationException("BigDecimal list not supported");
  }
  
  protected void writeValue(Field paramField, String paramString, Map paramMap)
  {
    throw new UnsupportedOperationException("String map not supported");
  }
  
  public final void writeValue(Field paramField, boolean paramBoolean)
  {
    if (Field.toString(paramField) != null)
    {
      write(paramField, Boolean.valueOf(paramBoolean));
      return;
    }
    setBooleanInternal(paramField, zapu, paramBoolean);
  }
  
  public final void wtf(Field paramField, ArrayList paramArrayList)
  {
    if (Field.toString(paramField) != null)
    {
      write(paramField, paramArrayList);
      return;
    }
    setStringsInternal(paramField, zapu, paramArrayList);
  }
  
  public final void wtf(Field paramField, byte[] paramArrayOfByte)
  {
    if (Field.toString(paramField) != null)
    {
      write(paramField, paramArrayOfByte);
      return;
    }
    setDecodedBytesInternal(paramField, zapu, paramArrayOfByte);
  }
  
  @KeepForSdk
  @ShowFirstParty
  @SafeParcelable.Class(creator="FieldCreator")
  @VisibleForTesting
  public class Field<I, O>
    extends AbstractSafeParcelable
  {
    public static final VerticalProgressBar.SavedState.1 CREATOR = new VerticalProgressBar.SavedState.1();
    @SafeParcelable.VersionField(getter="getVersionCode", id=1)
    private final int zale;
    @SafeParcelable.Field(getter="getTypeIn", id=2)
    protected final int zapq;
    @SafeParcelable.Field(getter="isTypeInArray", id=3)
    protected final boolean zapr;
    @SafeParcelable.Field(getter="getTypeOut", id=4)
    protected final int zaps;
    @SafeParcelable.Field(getter="isTypeOutArray", id=5)
    protected final boolean zapt;
    @SafeParcelable.Field(getter="getOutputFieldName", id=6)
    protected final String zapu;
    @SafeParcelable.Field(getter="getSafeParcelableFieldId", id=7)
    protected final int zapv;
    protected final Class<? extends com.google.android.gms.common.server.response.FastJsonResponse> zapw;
    @SafeParcelable.Field(getter="getConcreteTypeName", id=8)
    private final String zapx;
    private OperationResult zapy;
    @SafeParcelable.Field(getter="getWrappedConverter", id=9, type="com.google.android.gms.common.server.converter.ConverterWrapper")
    private com.google.android.gms.common.server.response.FastJsonResponse.FieldConverter<I, O> zapz;
    
    Field(int paramInt1, boolean paramBoolean1, int paramInt2, boolean paramBoolean2, String paramString1, int paramInt3, String paramString2, MapPack paramMapPack)
    {
      zale = this$1;
      zapq = paramInt1;
      zapr = paramBoolean1;
      zaps = paramInt2;
      zapt = paramBoolean2;
      zapu = paramString1;
      zapv = paramInt3;
      if (paramString2 == null)
      {
        zapw = null;
        zapx = null;
      }
      else
      {
        zapw = com.google.android.gms.common.server.response.SafeParcelResponse.class;
        zapx = paramString2;
      }
      if (paramMapPack == null)
      {
        zapz = null;
        return;
      }
      zapz = paramMapPack.zaci();
    }
    
    private Field(boolean paramBoolean1, int paramInt1, boolean paramBoolean2, String paramString, int paramInt2, Class paramClass, FastJsonResponse.FieldConverter paramFieldConverter)
    {
      zale = 1;
      zapq = this$1;
      zapr = paramBoolean1;
      zaps = paramInt1;
      zapt = paramBoolean2;
      zapu = paramString;
      zapv = paramInt2;
      zapw = paramClass;
      if (paramClass == null) {
        zapx = null;
      } else {
        zapx = paramClass.getCanonicalName();
      }
      zapz = paramFieldConverter;
    }
    
    public static Field forBase64(String paramString, int paramInt)
    {
      return new Field(8, false, 8, false, paramString, paramInt, null, null);
    }
    
    public static Field forBoolean(String paramString, int paramInt)
    {
      return new Field(6, false, 6, false, paramString, paramInt, null, null);
    }
    
    public static Field forConcreteType(String paramString, int paramInt, Class paramClass)
    {
      return new Field(11, false, 11, false, paramString, paramInt, paramClass, null);
    }
    
    public static Field forConcreteTypeArray(String paramString, int paramInt, Class paramClass)
    {
      return new Field(11, true, 11, true, paramString, paramInt, paramClass, null);
    }
    
    public static Field forDouble(String paramString, int paramInt)
    {
      return new Field(4, false, 4, false, paramString, paramInt, null, null);
    }
    
    public static Field forFloat(String paramString, int paramInt)
    {
      return new Field(3, false, 3, false, paramString, paramInt, null, null);
    }
    
    public static Field forInteger(String paramString, int paramInt)
    {
      return new Field(0, false, 0, false, paramString, paramInt, null, null);
    }
    
    public static Field forLong(String paramString, int paramInt)
    {
      return new Field(2, false, 2, false, paramString, paramInt, null, null);
    }
    
    public static Field forString(String paramString, int paramInt)
    {
      return new Field(7, false, 7, false, paramString, paramInt, null, null);
    }
    
    public static Field forStrings(String paramString, int paramInt)
    {
      return new Field(7, true, 7, true, paramString, paramInt, null, null);
    }
    
    public static Field withConverter(String paramString, int paramInt, FastJsonResponse.FieldConverter paramFieldConverter, boolean paramBoolean)
    {
      return new Field(paramFieldConverter.zacj(), paramBoolean, paramFieldConverter.zack(), false, paramString, paramInt, null, paramFieldConverter);
    }
    
    private final String zacm()
    {
      if (zapx == null) {
        return null;
      }
      return zapx;
    }
    
    private final MapPack zaco()
    {
      if (zapz == null) {
        return null;
      }
      return MapPack.getSize(zapz);
    }
    
    public final void addTo(OperationResult paramOperationResult)
    {
      zapy = paramOperationResult;
    }
    
    public final Object convert(Object paramObject)
    {
      return zapz.convert(paramObject);
    }
    
    public final Object convertBack(Object paramObject)
    {
      return zapz.convertBack(paramObject);
    }
    
    public int getSafeParcelableFieldId()
    {
      return zapv;
    }
    
    public String toString()
    {
      Objects.ToStringHelper localToStringHelper = Objects.toStringHelper(this).add("versionCode", Integer.valueOf(zale)).add("typeIn", Integer.valueOf(zapq)).add("typeInArray", Boolean.valueOf(zapr)).add("typeOut", Integer.valueOf(zaps)).add("typeOutArray", Boolean.valueOf(zapt)).add("outputFieldName", zapu).add("safeParcelFieldId", Integer.valueOf(zapv)).add("concreteTypeName", zacm());
      Class localClass = zapw;
      if (localClass != null) {
        localToStringHelper.add("concreteType.class", localClass.getCanonicalName());
      }
      if (zapz != null) {
        localToStringHelper.add("converterName", zapz.getClass().getCanonicalName());
      }
      return localToStringHelper.toString();
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      int i = SafeParcelWriter.beginObjectHeader(paramParcel);
      SafeParcelWriter.writeInt(paramParcel, 1, zale);
      SafeParcelWriter.writeInt(paramParcel, 2, zapq);
      SafeParcelWriter.writeBoolean(paramParcel, 3, zapr);
      SafeParcelWriter.writeInt(paramParcel, 4, zaps);
      SafeParcelWriter.writeBoolean(paramParcel, 5, zapt);
      SafeParcelWriter.writeString(paramParcel, 6, zapu, false);
      SafeParcelWriter.writeInt(paramParcel, 7, getSafeParcelableFieldId());
      SafeParcelWriter.writeString(paramParcel, 8, zacm(), false);
      SafeParcelWriter.writeParcelable(paramParcel, 9, zaco(), paramInt, false);
      SafeParcelWriter.finishObjectHeader(paramParcel, i);
    }
    
    public final Field zacl()
    {
      return new Field(zale, zapq, zapr, zaps, zapt, zapu, zapv, zapx, zaco());
    }
    
    public final boolean zacn()
    {
      return zapz != null;
    }
    
    public final FastJsonResponse zacp()
      throws InstantiationException, IllegalAccessException
    {
      if (zapw == com.google.android.gms.common.server.response.SafeParcelResponse.class)
      {
        Preconditions.checkNotNull(zapy, "The field mapping dictionary must be set if the concrete type is a SafeParcelResponse object.");
        return new SafeParcelResponse(zapy, zapx);
      }
      return (FastJsonResponse)zapw.newInstance();
    }
    
    public final Map zacq()
    {
      Preconditions.checkNotNull(zapx);
      Preconditions.checkNotNull(zapy);
      return zapy.getMessages(zapx);
    }
  }
  
  @ShowFirstParty
  public abstract interface FieldConverter<I, O>
  {
    public abstract Object convert(Object paramObject);
    
    public abstract Object convertBack(Object paramObject);
    
    public abstract int zacj();
    
    public abstract int zack();
  }
}
