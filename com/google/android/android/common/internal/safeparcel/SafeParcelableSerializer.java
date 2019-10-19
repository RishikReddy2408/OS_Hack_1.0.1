package com.google.android.android.common.internal.safeparcel;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.android.common.internal.Preconditions;
import com.google.android.android.common.util.Base64Utils;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Iterator;

@KeepForSdk
@VisibleForTesting
public final class SafeParcelableSerializer
{
  public SafeParcelableSerializer() {}
  
  public static SafeParcelable deserializeFromBytes(byte[] paramArrayOfByte, Parcelable.Creator paramCreator)
  {
    Preconditions.checkNotNull(paramCreator);
    Parcel localParcel = Parcel.obtain();
    localParcel.unmarshall(paramArrayOfByte, 0, paramArrayOfByte.length);
    localParcel.setDataPosition(0);
    paramArrayOfByte = (SafeParcelable)paramCreator.createFromParcel(localParcel);
    localParcel.recycle();
    return paramArrayOfByte;
  }
  
  public static SafeParcelable deserializeFromIntentExtra(Intent paramIntent, String paramString, Parcelable.Creator paramCreator)
  {
    paramIntent = paramIntent.getByteArrayExtra(paramString);
    if (paramIntent == null) {
      return null;
    }
    return deserializeFromBytes(paramIntent, paramCreator);
  }
  
  public static SafeParcelable deserializeFromString(String paramString, Parcelable.Creator paramCreator)
  {
    return deserializeFromBytes(Base64Utils.decodeUrlSafe(paramString), paramCreator);
  }
  
  public static ArrayList deserializeIterableFromBundle(Bundle paramBundle, String paramString, Parcelable.Creator paramCreator)
  {
    paramString = (ArrayList)paramBundle.getSerializable(paramString);
    if (paramString == null) {
      return null;
    }
    paramBundle = new ArrayList(paramString.size());
    paramString = (ArrayList)paramString;
    int j = paramString.size();
    int i = 0;
    while (i < j)
    {
      Object localObject = paramString.get(i);
      i += 1;
      paramBundle.add(deserializeFromBytes((byte[])localObject, paramCreator));
    }
    return paramBundle;
  }
  
  public static ArrayList deserializeIterableFromIntentExtra(Intent paramIntent, String paramString, Parcelable.Creator paramCreator)
  {
    paramString = (ArrayList)paramIntent.getSerializableExtra(paramString);
    if (paramString == null) {
      return null;
    }
    paramIntent = new ArrayList(paramString.size());
    paramString = (ArrayList)paramString;
    int j = paramString.size();
    int i = 0;
    while (i < j)
    {
      Object localObject = paramString.get(i);
      i += 1;
      paramIntent.add(deserializeFromBytes((byte[])localObject, paramCreator));
    }
    return paramIntent;
  }
  
  public static void serializeIterableToBundle(Iterable paramIterable, Bundle paramBundle, String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    paramIterable = paramIterable.iterator();
    while (paramIterable.hasNext()) {
      localArrayList.add(serializeToBytes((SafeParcelable)paramIterable.next()));
    }
    paramBundle.putSerializable(paramString, localArrayList);
  }
  
  public static void serializeIterableToIntentExtra(Iterable paramIterable, Intent paramIntent, String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    paramIterable = paramIterable.iterator();
    while (paramIterable.hasNext()) {
      localArrayList.add(serializeToBytes((SafeParcelable)paramIterable.next()));
    }
    paramIntent.putExtra(paramString, localArrayList);
  }
  
  public static byte[] serializeToBytes(SafeParcelable paramSafeParcelable)
  {
    Parcel localParcel = Parcel.obtain();
    paramSafeParcelable.writeToParcel(localParcel, 0);
    paramSafeParcelable = localParcel.marshall();
    localParcel.recycle();
    return paramSafeParcelable;
  }
  
  public static void serializeToIntentExtra(SafeParcelable paramSafeParcelable, Intent paramIntent, String paramString)
  {
    paramIntent.putExtra(paramString, serializeToBytes(paramSafeParcelable));
  }
  
  public static String serializeToString(SafeParcelable paramSafeParcelable)
  {
    return Base64Utils.encodeUrlSafe(serializeToBytes(paramSafeParcelable));
  }
}
