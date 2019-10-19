package android.support.v4.graphics;

import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

public final class BitmapCompat
{
  static final BitmapCompatBaseImpl IMPL = new BitmapCompatBaseImpl();
  
  static
  {
    if (Build.VERSION.SDK_INT >= 19)
    {
      IMPL = new BitmapCompatApi19Impl();
      return;
    }
    if (Build.VERSION.SDK_INT >= 18)
    {
      IMPL = new BitmapCompatApi18Impl();
      return;
    }
  }
  
  private BitmapCompat() {}
  
  public static int getAllocationByteCount(@NonNull Bitmap paramBitmap)
  {
    return IMPL.getAllocationByteCount(paramBitmap);
  }
  
  public static boolean hasMipMap(@NonNull Bitmap paramBitmap)
  {
    return IMPL.hasMipMap(paramBitmap);
  }
  
  public static void setHasMipMap(@NonNull Bitmap paramBitmap, boolean paramBoolean)
  {
    IMPL.setHasMipMap(paramBitmap, paramBoolean);
  }
  
  @RequiresApi(18)
  static class BitmapCompatApi18Impl
    extends BitmapCompat.BitmapCompatBaseImpl
  {
    BitmapCompatApi18Impl() {}
    
    public boolean hasMipMap(Bitmap paramBitmap)
    {
      return paramBitmap.hasMipMap();
    }
    
    public void setHasMipMap(Bitmap paramBitmap, boolean paramBoolean)
    {
      paramBitmap.setHasMipMap(paramBoolean);
    }
  }
  
  @RequiresApi(19)
  static class BitmapCompatApi19Impl
    extends BitmapCompat.BitmapCompatApi18Impl
  {
    BitmapCompatApi19Impl() {}
    
    public int getAllocationByteCount(Bitmap paramBitmap)
    {
      return paramBitmap.getAllocationByteCount();
    }
  }
  
  static class BitmapCompatBaseImpl
  {
    BitmapCompatBaseImpl() {}
    
    public int getAllocationByteCount(Bitmap paramBitmap)
    {
      return paramBitmap.getByteCount();
    }
    
    public boolean hasMipMap(Bitmap paramBitmap)
    {
      return false;
    }
    
    public void setHasMipMap(Bitmap paramBitmap, boolean paramBoolean) {}
  }
}
