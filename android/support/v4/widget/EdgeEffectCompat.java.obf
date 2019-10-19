package android.support.v4.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.widget.EdgeEffect;

public final class EdgeEffectCompat
{
  private static final EdgeEffectBaseImpl IMPL = new EdgeEffectBaseImpl();
  private EdgeEffect mEdgeEffect;
  
  static
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      IMPL = new EdgeEffectApi21Impl();
      return;
    }
  }
  
  @Deprecated
  public EdgeEffectCompat(Context paramContext)
  {
    mEdgeEffect = new EdgeEffect(paramContext);
  }
  
  public static void onPull(@NonNull EdgeEffect paramEdgeEffect, float paramFloat1, float paramFloat2)
  {
    IMPL.onPull(paramEdgeEffect, paramFloat1, paramFloat2);
  }
  
  @Deprecated
  public boolean draw(Canvas paramCanvas)
  {
    return mEdgeEffect.draw(paramCanvas);
  }
  
  @Deprecated
  public void finish()
  {
    mEdgeEffect.finish();
  }
  
  @Deprecated
  public boolean isFinished()
  {
    return mEdgeEffect.isFinished();
  }
  
  @Deprecated
  public boolean onAbsorb(int paramInt)
  {
    mEdgeEffect.onAbsorb(paramInt);
    return true;
  }
  
  @Deprecated
  public boolean onPull(float paramFloat)
  {
    mEdgeEffect.onPull(paramFloat);
    return true;
  }
  
  @Deprecated
  public boolean onPull(float paramFloat1, float paramFloat2)
  {
    IMPL.onPull(mEdgeEffect, paramFloat1, paramFloat2);
    return true;
  }
  
  @Deprecated
  public boolean onRelease()
  {
    mEdgeEffect.onRelease();
    return mEdgeEffect.isFinished();
  }
  
  @Deprecated
  public void setSize(int paramInt1, int paramInt2)
  {
    mEdgeEffect.setSize(paramInt1, paramInt2);
  }
  
  @RequiresApi(21)
  static class EdgeEffectApi21Impl
    extends EdgeEffectCompat.EdgeEffectBaseImpl
  {
    EdgeEffectApi21Impl() {}
    
    public void onPull(EdgeEffect paramEdgeEffect, float paramFloat1, float paramFloat2)
    {
      paramEdgeEffect.onPull(paramFloat1, paramFloat2);
    }
  }
  
  static class EdgeEffectBaseImpl
  {
    EdgeEffectBaseImpl() {}
    
    public void onPull(EdgeEffect paramEdgeEffect, float paramFloat1, float paramFloat2)
    {
      paramEdgeEffect.onPull(paramFloat1);
    }
  }
}
