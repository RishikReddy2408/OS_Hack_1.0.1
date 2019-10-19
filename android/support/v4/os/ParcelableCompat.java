package android.support.v4.os;

import android.os.Parcel;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;

@Deprecated
public final class ParcelableCompat
{
  private ParcelableCompat() {}
  
  public static Parcelable.Creator newCreator(ParcelableCompatCreatorCallbacks paramParcelableCompatCreatorCallbacks)
  {
    return new ParcelableCompatCreatorHoneycombMR2(paramParcelableCompatCreatorCallbacks);
  }
  
  static class ParcelableCompatCreatorHoneycombMR2<T>
    implements Parcelable.ClassLoaderCreator<T>
  {
    private final ParcelableCompatCreatorCallbacks<T> mCallbacks;
    
    ParcelableCompatCreatorHoneycombMR2(ParcelableCompatCreatorCallbacks paramParcelableCompatCreatorCallbacks)
    {
      mCallbacks = paramParcelableCompatCreatorCallbacks;
    }
    
    public Object createFromParcel(Parcel paramParcel)
    {
      return mCallbacks.createFromParcel(paramParcel, null);
    }
    
    public Object createFromParcel(Parcel paramParcel, ClassLoader paramClassLoader)
    {
      return mCallbacks.createFromParcel(paramParcel, paramClassLoader);
    }
    
    public Object[] newArray(int paramInt)
    {
      return mCallbacks.newArray(paramInt);
    }
  }
}
