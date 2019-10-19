package com.google.android.android.common.server.response;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.SparseArray;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.common.internal.safeparcel.SafeParcelReader;
import com.google.android.android.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.android.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.android.common.internal.safeparcel.SafeParcelable;
import com.google.android.android.common.util.ArrayUtils;
import com.google.android.android.common.util.Base64Utils;
import com.google.android.android.common.util.JsonUtils;
import com.google.android.android.common.util.MapUtils;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.util.VisibleForTesting;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@KeepForSdk
@SafeParcelable.Class(creator="SafeParcelResponseCreator")
@VisibleForTesting
public class SafeParcelResponse
  extends FastSafeParcelableJsonResponse
{
  @KeepForSdk
  public static final Parcelable.Creator<com.google.android.gms.common.server.response.SafeParcelResponse> CREATOR = new Point.1();
  private final String mClassName;
  @SafeParcelable.VersionField(getter="getVersionCode", id=1)
  private final int zale;
  @SafeParcelable.Field(getter="getFieldMappingDictionary", id=3)
  private final OperationResult zapy;
  @SafeParcelable.Field(getter="getParcel", id=2)
  private final Parcel zara;
  private final int zarb;
  private int zarc;
  private int zard;
  
  SafeParcelResponse(int paramInt, Parcel paramParcel, OperationResult paramOperationResult)
  {
    zale = paramInt;
    zara = ((Parcel)Preconditions.checkNotNull(paramParcel));
    zarb = 2;
    zapy = paramOperationResult;
    if (zapy == null) {
      mClassName = null;
    } else {
      mClassName = zapy.zact();
    }
    zarc = 2;
  }
  
  private SafeParcelResponse(SafeParcelable paramSafeParcelable, OperationResult paramOperationResult, String paramString)
  {
    zale = 1;
    zara = Parcel.obtain();
    paramSafeParcelable.writeToParcel(zara, 0);
    zarb = 1;
    zapy = ((OperationResult)Preconditions.checkNotNull(paramOperationResult));
    mClassName = ((String)Preconditions.checkNotNull(paramString));
    zarc = 2;
  }
  
  public SafeParcelResponse(OperationResult paramOperationResult, String paramString)
  {
    zale = 1;
    zara = Parcel.obtain();
    zarb = 0;
    zapy = ((OperationResult)Preconditions.checkNotNull(paramOperationResult));
    mClassName = ((String)Preconditions.checkNotNull(paramString));
    zarc = 0;
  }
  
  private static void execute(OperationResult paramOperationResult, FastJsonResponse paramFastJsonResponse)
  {
    Object localObject1 = paramFastJsonResponse.getClass();
    if (!paramOperationResult.add((Class)localObject1))
    {
      Map localMap = paramFastJsonResponse.getFieldMappings();
      paramOperationResult.add((Class)localObject1, localMap);
      localObject1 = localMap.keySet().iterator();
      while (((Iterator)localObject1).hasNext())
      {
        paramFastJsonResponse = (FastJsonResponse.Field)localMap.get((String)((Iterator)localObject1).next());
        Object localObject2 = zapw;
        if (localObject2 != null) {
          try
          {
            localObject2 = ((Class)localObject2).newInstance();
            localObject2 = (FastJsonResponse)localObject2;
            execute(paramOperationResult, (FastJsonResponse)localObject2);
          }
          catch (IllegalAccessException localIllegalAccessException)
          {
            paramOperationResult = String.valueOf(zapw.getCanonicalName());
            if (paramOperationResult.length() != 0) {
              paramOperationResult = "Could not access object of type ".concat(paramOperationResult);
            } else {
              paramOperationResult = new String("Could not access object of type ");
            }
            throw new IllegalStateException(paramOperationResult, localIllegalAccessException);
          }
          catch (InstantiationException localInstantiationException)
          {
            paramOperationResult = String.valueOf(zapw.getCanonicalName());
            if (paramOperationResult.length() != 0) {
              paramOperationResult = "Could not instantiate an object of type ".concat(paramOperationResult);
            } else {
              paramOperationResult = new String("Could not instantiate an object of type ");
            }
            throw new IllegalStateException(paramOperationResult, localInstantiationException);
          }
        }
      }
    }
  }
  
  public static SafeParcelResponse from(FastJsonResponse paramFastJsonResponse)
  {
    String str = paramFastJsonResponse.getClass().getCanonicalName();
    OperationResult localOperationResult = new OperationResult(paramFastJsonResponse.getClass());
    execute(localOperationResult, paramFastJsonResponse);
    localOperationResult.zacs();
    localOperationResult.zacr();
    return new SafeParcelResponse((SafeParcelable)paramFastJsonResponse, localOperationResult, str);
  }
  
  private final void processOptions(StringBuilder paramStringBuilder, FastJsonResponse.Field paramField, Object paramObject)
  {
    if (zapr)
    {
      paramObject = (ArrayList)paramObject;
      paramStringBuilder.append("[");
      int j = paramObject.size();
      int i = 0;
      while (i < j)
      {
        if (i != 0) {
          paramStringBuilder.append(",");
        }
        set(paramStringBuilder, zapq, paramObject.get(i));
        i += 1;
      }
      paramStringBuilder.append("]");
      return;
    }
    set(paramStringBuilder, zapq, paramObject);
  }
  
  private static void set(StringBuilder paramStringBuilder, int paramInt, Object paramObject)
  {
    switch (paramInt)
    {
    default: 
      paramStringBuilder = new StringBuilder(26);
      paramStringBuilder.append("Unknown type = ");
      paramStringBuilder.append(paramInt);
      throw new IllegalArgumentException(paramStringBuilder.toString());
    case 11: 
      throw new IllegalArgumentException("Method does not accept concrete type.");
    case 10: 
      MapUtils.writeStringMapToJson(paramStringBuilder, (HashMap)paramObject);
      return;
    case 9: 
      paramStringBuilder.append("\"");
      paramStringBuilder.append(Base64Utils.encodeUrlSafe((byte[])paramObject));
      paramStringBuilder.append("\"");
      return;
    case 8: 
      paramStringBuilder.append("\"");
      paramStringBuilder.append(Base64Utils.encode((byte[])paramObject));
      paramStringBuilder.append("\"");
      return;
    case 7: 
      paramStringBuilder.append("\"");
      paramStringBuilder.append(JsonUtils.escapeString(paramObject.toString()));
      paramStringBuilder.append("\"");
      return;
    }
    paramStringBuilder.append(paramObject);
  }
  
  private final void write(StringBuilder paramStringBuilder, Map paramMap, Parcel paramParcel)
  {
    SparseArray localSparseArray = new SparseArray();
    paramMap = paramMap.entrySet().iterator();
    Object localObject1;
    while (paramMap.hasNext())
    {
      localObject1 = (Map.Entry)paramMap.next();
      localSparseArray.put(((FastJsonResponse.Field)((Map.Entry)localObject1).getValue()).getSafeParcelableFieldId(), localObject1);
    }
    paramStringBuilder.append('{');
    int j = SafeParcelReader.validateObjectHeader(paramParcel);
    int i = 0;
    while (paramParcel.dataPosition() < j)
    {
      int k = SafeParcelReader.readHeader(paramParcel);
      paramMap = (Map.Entry)localSparseArray.get(SafeParcelReader.getFieldId(k));
      if (paramMap != null)
      {
        if (i != 0) {
          paramStringBuilder.append(",");
        }
        localObject1 = (String)paramMap.getKey();
        paramMap = (FastJsonResponse.Field)paramMap.getValue();
        paramStringBuilder.append("\"");
        paramStringBuilder.append((String)localObject1);
        paramStringBuilder.append("\":");
        Object localObject2;
        if (paramMap.zacn())
        {
          switch (zaps)
          {
          default: 
            i = zaps;
            paramStringBuilder = new StringBuilder(36);
            paramStringBuilder.append("Unknown field out type = ");
            paramStringBuilder.append(i);
            throw new IllegalArgumentException(paramStringBuilder.toString());
          case 11: 
            throw new IllegalArgumentException("Method does not accept concrete type.");
          case 10: 
            localObject1 = SafeParcelReader.createBundle(paramParcel, k);
            localObject2 = new HashMap();
            Iterator localIterator = ((Bundle)localObject1).keySet().iterator();
            while (localIterator.hasNext())
            {
              String str = (String)localIterator.next();
              ((HashMap)localObject2).put(str, ((Bundle)localObject1).getString(str));
            }
            processOptions(paramStringBuilder, paramMap, FastJsonResponse.valueOf(paramMap, localObject2));
            break;
          case 8: 
          case 9: 
            processOptions(paramStringBuilder, paramMap, FastJsonResponse.valueOf(paramMap, SafeParcelReader.createByteArray(paramParcel, k)));
            break;
          case 7: 
            processOptions(paramStringBuilder, paramMap, FastJsonResponse.valueOf(paramMap, SafeParcelReader.createString(paramParcel, k)));
            break;
          case 6: 
            processOptions(paramStringBuilder, paramMap, FastJsonResponse.valueOf(paramMap, Boolean.valueOf(SafeParcelReader.readBoolean(paramParcel, k))));
            break;
          case 5: 
            processOptions(paramStringBuilder, paramMap, FastJsonResponse.valueOf(paramMap, SafeParcelReader.createBigDecimal(paramParcel, k)));
            break;
          case 4: 
            processOptions(paramStringBuilder, paramMap, FastJsonResponse.valueOf(paramMap, Double.valueOf(SafeParcelReader.readDouble(paramParcel, k))));
            break;
          case 3: 
            processOptions(paramStringBuilder, paramMap, FastJsonResponse.valueOf(paramMap, Float.valueOf(SafeParcelReader.readFloat(paramParcel, k))));
            break;
          case 2: 
            processOptions(paramStringBuilder, paramMap, FastJsonResponse.valueOf(paramMap, Long.valueOf(SafeParcelReader.readLong(paramParcel, k))));
            break;
          case 1: 
            processOptions(paramStringBuilder, paramMap, FastJsonResponse.valueOf(paramMap, SafeParcelReader.createBigInteger(paramParcel, k)));
            break;
          case 0: 
            processOptions(paramStringBuilder, paramMap, FastJsonResponse.valueOf(paramMap, Integer.valueOf(SafeParcelReader.readInt(paramParcel, k))));
            break;
          }
        }
        else if (zapt)
        {
          paramStringBuilder.append("[");
          switch (zaps)
          {
          default: 
            throw new IllegalStateException("Unknown field type out.");
          case 11: 
            localObject1 = SafeParcelReader.createParcelArray(paramParcel, k);
            k = localObject1.length;
            i = 0;
          }
          while (i < k)
          {
            if (i > 0) {
              paramStringBuilder.append(",");
            }
            localObject1[i].setDataPosition(0);
            write(paramStringBuilder, paramMap.zacq(), localObject1[i]);
            i += 1;
            continue;
            throw new UnsupportedOperationException("List of type BASE64, BASE64_URL_SAFE, or STRING_MAP is not supported");
            ArrayUtils.writeStringArray(paramStringBuilder, SafeParcelReader.createStringArray(paramParcel, k));
            break;
            ArrayUtils.writeArray(paramStringBuilder, SafeParcelReader.createBooleanArray(paramParcel, k));
            break;
            ArrayUtils.writeArray(paramStringBuilder, SafeParcelReader.createBigDecimalArray(paramParcel, k));
            break;
            ArrayUtils.writeArray(paramStringBuilder, SafeParcelReader.createDoubleArray(paramParcel, k));
            break;
            ArrayUtils.writeArray(paramStringBuilder, SafeParcelReader.createFloatArray(paramParcel, k));
            break;
            ArrayUtils.writeArray(paramStringBuilder, SafeParcelReader.createLongArray(paramParcel, k));
            break;
            ArrayUtils.writeArray(paramStringBuilder, SafeParcelReader.createBigIntegerArray(paramParcel, k));
            break;
            ArrayUtils.writeArray(paramStringBuilder, SafeParcelReader.createIntArray(paramParcel, k));
          }
          paramStringBuilder.append("]");
        }
        else
        {
          switch (zaps)
          {
          default: 
            throw new IllegalStateException("Unknown field type out");
          case 11: 
            localObject1 = SafeParcelReader.createParcel(paramParcel, k);
            ((Parcel)localObject1).setDataPosition(0);
            write(paramStringBuilder, paramMap.zacq(), (Parcel)localObject1);
            break;
          case 10: 
            paramMap = SafeParcelReader.createBundle(paramParcel, k);
            localObject1 = paramMap.keySet();
            ((Set)localObject1).size();
            paramStringBuilder.append("{");
            localObject1 = ((Set)localObject1).iterator();
            for (i = 1; ((Iterator)localObject1).hasNext(); i = 0)
            {
              localObject2 = (String)((Iterator)localObject1).next();
              if (i == 0) {
                paramStringBuilder.append(",");
              }
              paramStringBuilder.append("\"");
              paramStringBuilder.append((String)localObject2);
              paramStringBuilder.append("\"");
              paramStringBuilder.append(":");
              paramStringBuilder.append("\"");
              paramStringBuilder.append(JsonUtils.escapeString(paramMap.getString((String)localObject2)));
              paramStringBuilder.append("\"");
            }
            paramStringBuilder.append("}");
            break;
          case 9: 
            paramMap = SafeParcelReader.createByteArray(paramParcel, k);
            paramStringBuilder.append("\"");
            paramStringBuilder.append(Base64Utils.encodeUrlSafe(paramMap));
            paramStringBuilder.append("\"");
            break;
          case 8: 
            paramMap = SafeParcelReader.createByteArray(paramParcel, k);
            paramStringBuilder.append("\"");
            paramStringBuilder.append(Base64Utils.encode(paramMap));
            paramStringBuilder.append("\"");
            break;
          case 7: 
            paramMap = SafeParcelReader.createString(paramParcel, k);
            paramStringBuilder.append("\"");
            paramStringBuilder.append(JsonUtils.escapeString(paramMap));
            paramStringBuilder.append("\"");
            break;
          case 6: 
            paramStringBuilder.append(SafeParcelReader.readBoolean(paramParcel, k));
            break;
          case 5: 
            paramStringBuilder.append(SafeParcelReader.createBigDecimal(paramParcel, k));
            break;
          case 4: 
            paramStringBuilder.append(SafeParcelReader.readDouble(paramParcel, k));
            break;
          case 3: 
            paramStringBuilder.append(SafeParcelReader.readFloat(paramParcel, k));
            break;
          case 2: 
            paramStringBuilder.append(SafeParcelReader.readLong(paramParcel, k));
            break;
          case 1: 
            paramStringBuilder.append(SafeParcelReader.createBigInteger(paramParcel, k));
            break;
          case 0: 
            paramStringBuilder.append(SafeParcelReader.readInt(paramParcel, k));
          }
        }
        i = 1;
      }
    }
    if (paramParcel.dataPosition() == j)
    {
      paramStringBuilder.append('}');
      return;
    }
    paramStringBuilder = new StringBuilder(37);
    paramStringBuilder.append("Overread allowed size end=");
    paramStringBuilder.append(j);
    throw new SafeParcelReader.ParseException(paramStringBuilder.toString(), paramParcel);
  }
  
  private final void writeTag(FastJsonResponse.Field paramField)
  {
    int i;
    if (zapv != -1) {
      i = 1;
    } else {
      i = 0;
    }
    if (i != 0)
    {
      if (zara != null)
      {
        switch (zarc)
        {
        default: 
          throw new IllegalStateException("Unknown parse state in SafeParcelResponse.");
        case 2: 
          throw new IllegalStateException("Attempted to parse JSON with a SafeParcelResponse object that is already filled with data.");
        case 1: 
          return;
        }
        zard = SafeParcelWriter.beginObjectHeader(zara);
        zarc = 1;
        return;
      }
      throw new IllegalStateException("Internal Parcel object is null.");
    }
    throw new IllegalStateException("Field does not have a valid safe parcelable field id.");
  }
  
  private final Parcel zacu()
  {
    switch (zarc)
    {
    default: 
      break;
    case 0: 
      zard = SafeParcelWriter.beginObjectHeader(zara);
    case 1: 
      SafeParcelWriter.finishObjectHeader(zara, zard);
      zarc = 2;
    }
    return zara;
  }
  
  protected final void addAll(FastJsonResponse.Field paramField, String paramString, ArrayList paramArrayList)
  {
    writeTag(paramField);
    int j = paramArrayList.size();
    paramString = new float[j];
    int i = 0;
    while (i < j)
    {
      paramString[i] = ((Float)paramArrayList.get(i)).floatValue();
      i += 1;
    }
    SafeParcelWriter.writeFloatArray(zara, paramField.getSafeParcelableFieldId(), paramString, true);
  }
  
  public void addConcreteTypeArrayInternal(FastJsonResponse.Field paramField, String paramString, ArrayList paramArrayList)
  {
    writeTag(paramField);
    paramString = new ArrayList();
    paramArrayList.size();
    paramArrayList = (ArrayList)paramArrayList;
    int j = paramArrayList.size();
    int i = 0;
    while (i < j)
    {
      Object localObject = paramArrayList.get(i);
      i += 1;
      paramString.add(((SafeParcelResponse)localObject).zacu());
    }
    SafeParcelWriter.writeParcelList(zara, paramField.getSafeParcelableFieldId(), paramString, true);
  }
  
  public void addConcreteTypeInternal(FastJsonResponse.Field paramField, String paramString, FastJsonResponse paramFastJsonResponse)
  {
    writeTag(paramField);
    paramString = ((SafeParcelResponse)paramFastJsonResponse).zacu();
    SafeParcelWriter.writeParcel(zara, paramField.getSafeParcelableFieldId(), paramString, true);
  }
  
  protected final void copyAll(FastJsonResponse.Field paramField, String paramString, ArrayList paramArrayList)
  {
    writeTag(paramField);
    int j = paramArrayList.size();
    paramString = new boolean[j];
    int i = 0;
    while (i < j)
    {
      paramString[i] = ((Boolean)paramArrayList.get(i)).booleanValue();
      i += 1;
    }
    SafeParcelWriter.writeBooleanArray(zara, paramField.getSafeParcelableFieldId(), paramString, true);
  }
  
  protected final void execute(FastJsonResponse.Field paramField, String paramString, ArrayList paramArrayList)
  {
    writeTag(paramField);
    int j = paramArrayList.size();
    paramString = new long[j];
    int i = 0;
    while (i < j)
    {
      paramString[i] = ((Long)paramArrayList.get(i)).longValue();
      i += 1;
    }
    SafeParcelWriter.writeLongArray(zara, paramField.getSafeParcelableFieldId(), paramString, true);
  }
  
  public Map getFieldMappings()
  {
    if (zapy == null) {
      return null;
    }
    return zapy.getMessages(mClassName);
  }
  
  public Object getValueObject(String paramString)
  {
    throw new UnsupportedOperationException("Converting to JSON does not require this method.");
  }
  
  public boolean isPrimitiveFieldSet(String paramString)
  {
    throw new UnsupportedOperationException("Converting to JSON does not require this method.");
  }
  
  protected void setBooleanInternal(FastJsonResponse.Field paramField, String paramString, boolean paramBoolean)
  {
    writeTag(paramField);
    SafeParcelWriter.writeBoolean(zara, paramField.getSafeParcelableFieldId(), paramBoolean);
  }
  
  protected void setDecodedBytesInternal(FastJsonResponse.Field paramField, String paramString, byte[] paramArrayOfByte)
  {
    writeTag(paramField);
    SafeParcelWriter.writeByteArray(zara, paramField.getSafeParcelableFieldId(), paramArrayOfByte, true);
  }
  
  protected void setIntegerInternal(FastJsonResponse.Field paramField, String paramString, int paramInt)
  {
    writeTag(paramField);
    SafeParcelWriter.writeInt(zara, paramField.getSafeParcelableFieldId(), paramInt);
  }
  
  protected void setLongInternal(FastJsonResponse.Field paramField, String paramString, long paramLong)
  {
    writeTag(paramField);
    SafeParcelWriter.writeLong(zara, paramField.getSafeParcelableFieldId(), paramLong);
  }
  
  protected void setStringInternal(FastJsonResponse.Field paramField, String paramString1, String paramString2)
  {
    writeTag(paramField);
    SafeParcelWriter.writeString(zara, paramField.getSafeParcelableFieldId(), paramString2, true);
  }
  
  protected void setStringsInternal(FastJsonResponse.Field paramField, String paramString, ArrayList paramArrayList)
  {
    writeTag(paramField);
    int j = paramArrayList.size();
    paramString = new String[j];
    int i = 0;
    while (i < j)
    {
      paramString[i] = ((String)paramArrayList.get(i));
      i += 1;
    }
    SafeParcelWriter.writeStringArray(zara, paramField.getSafeParcelableFieldId(), paramString, true);
  }
  
  protected final void subtract(FastJsonResponse.Field paramField, String paramString, ArrayList paramArrayList)
  {
    writeTag(paramField);
    int j = paramArrayList.size();
    paramString = new double[j];
    int i = 0;
    while (i < j)
    {
      paramString[i] = ((Double)paramArrayList.get(i)).doubleValue();
      i += 1;
    }
    SafeParcelWriter.writeDoubleArray(zara, paramField.getSafeParcelableFieldId(), paramString, true);
  }
  
  public String toString()
  {
    Preconditions.checkNotNull(zapy, "Cannot convert to JSON on client side.");
    Parcel localParcel = zacu();
    localParcel.setDataPosition(0);
    StringBuilder localStringBuilder = new StringBuilder(100);
    write(localStringBuilder, zapy.getMessages(mClassName), localParcel);
    return localStringBuilder.toString();
  }
  
  protected final void trim(FastJsonResponse.Field paramField, String paramString, ArrayList paramArrayList)
  {
    writeTag(paramField);
    int j = paramArrayList.size();
    paramString = new BigInteger[j];
    int i = 0;
    while (i < j)
    {
      paramString[i] = ((BigInteger)paramArrayList.get(i));
      i += 1;
    }
    SafeParcelWriter.writeBigIntegerArray(zara, paramField.getSafeParcelableFieldId(), paramString, true);
  }
  
  protected final void write(FastJsonResponse.Field paramField, String paramString, ArrayList paramArrayList)
  {
    writeTag(paramField);
    int j = paramArrayList.size();
    paramString = new int[j];
    int i = 0;
    while (i < j)
    {
      paramString[i] = ((Integer)paramArrayList.get(i)).intValue();
      i += 1;
    }
    SafeParcelWriter.writeIntArray(zara, paramField.getSafeParcelableFieldId(), paramString, true);
  }
  
  protected final void writeField(FastJsonResponse.Field paramField, String paramString, BigInteger paramBigInteger)
  {
    writeTag(paramField);
    SafeParcelWriter.writeBigInteger(zara, paramField.getSafeParcelableFieldId(), paramBigInteger, true);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 1, zale);
    SafeParcelWriter.writeParcel(paramParcel, 2, zacu(), false);
    OperationResult localOperationResult;
    switch (zarb)
    {
    default: 
      paramInt = zarb;
      paramParcel = new StringBuilder(34);
      paramParcel.append("Invalid creation type: ");
      paramParcel.append(paramInt);
      throw new IllegalStateException(paramParcel.toString());
    case 2: 
      localOperationResult = zapy;
      break;
    case 1: 
      localOperationResult = zapy;
      break;
    case 0: 
      localOperationResult = null;
    }
    SafeParcelWriter.writeParcelable(paramParcel, 3, localOperationResult, paramInt, false);
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
  }
  
  protected final void writeValue(FastJsonResponse.Field paramField, String paramString, double paramDouble)
  {
    writeTag(paramField);
    SafeParcelWriter.writeDouble(zara, paramField.getSafeParcelableFieldId(), paramDouble);
  }
  
  protected final void writeValue(FastJsonResponse.Field paramField, String paramString, float paramFloat)
  {
    writeTag(paramField);
    SafeParcelWriter.writeFloat(zara, paramField.getSafeParcelableFieldId(), paramFloat);
  }
  
  protected final void writeValue(FastJsonResponse.Field paramField, String paramString, BigDecimal paramBigDecimal)
  {
    writeTag(paramField);
    SafeParcelWriter.writeBigDecimal(zara, paramField.getSafeParcelableFieldId(), paramBigDecimal, true);
  }
  
  protected final void writeValue(FastJsonResponse.Field paramField, String paramString, ArrayList paramArrayList)
  {
    writeTag(paramField);
    int j = paramArrayList.size();
    paramString = new BigDecimal[j];
    int i = 0;
    while (i < j)
    {
      paramString[i] = ((BigDecimal)paramArrayList.get(i));
      i += 1;
    }
    SafeParcelWriter.writeBigDecimalArray(zara, paramField.getSafeParcelableFieldId(), paramString, true);
  }
  
  protected final void writeValue(FastJsonResponse.Field paramField, String paramString, Map paramMap)
  {
    writeTag(paramField);
    paramString = new Bundle();
    Iterator localIterator = paramMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      paramString.putString(str, (String)paramMap.get(str));
    }
    SafeParcelWriter.writeBundle(zara, paramField.getSafeParcelableFieldId(), paramString, true);
  }
}
