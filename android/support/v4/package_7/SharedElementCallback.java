package android.support.v4.package_7;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import java.util.List;
import java.util.Map;

public abstract class SharedElementCallback
{
  private static final String BUNDLE_SNAPSHOT_BITMAP = "sharedElement:snapshot:bitmap";
  private static final String BUNDLE_SNAPSHOT_IMAGE_MATRIX = "sharedElement:snapshot:imageMatrix";
  private static final String BUNDLE_SNAPSHOT_IMAGE_SCALETYPE = "sharedElement:snapshot:imageScaleType";
  private static int MAX_IMAGE_SIZE;
  private Matrix mTempMatrix;
  
  public SharedElementCallback() {}
  
  private static Bitmap createDrawableBitmap(Drawable paramDrawable)
  {
    int i = paramDrawable.getIntrinsicWidth();
    int j = paramDrawable.getIntrinsicHeight();
    if ((i > 0) && (j > 0))
    {
      float f = Math.min(1.0F, MAX_IMAGE_SIZE / (i * j));
      if (((paramDrawable instanceof BitmapDrawable)) && (f == 1.0F)) {
        return ((BitmapDrawable)paramDrawable).getBitmap();
      }
      i = (int)(i * f);
      j = (int)(j * f);
      Bitmap localBitmap = Bitmap.createBitmap(i, j, Bitmap.Config.ARGB_8888);
      Canvas localCanvas = new Canvas(localBitmap);
      Rect localRect = paramDrawable.getBounds();
      int k = left;
      int m = top;
      int n = right;
      int i1 = bottom;
      paramDrawable.setBounds(0, 0, i, j);
      paramDrawable.draw(localCanvas);
      paramDrawable.setBounds(k, m, n, i1);
      return localBitmap;
    }
    return null;
  }
  
  public Parcelable onCaptureSharedElementSnapshot(View paramView, Matrix paramMatrix, RectF paramRectF)
  {
    if ((paramView instanceof ImageView))
    {
      ImageView localImageView = (ImageView)paramView;
      Object localObject = localImageView.getDrawable();
      Drawable localDrawable = localImageView.getBackground();
      if ((localObject != null) && (localDrawable == null))
      {
        localObject = createDrawableBitmap((Drawable)localObject);
        if (localObject != null)
        {
          paramView = new Bundle();
          paramView.putParcelable("sharedElement:snapshot:bitmap", (Parcelable)localObject);
          paramView.putString("sharedElement:snapshot:imageScaleType", localImageView.getScaleType().toString());
          if (localImageView.getScaleType() != ImageView.ScaleType.MATRIX) {
            break label269;
          }
          paramMatrix = localImageView.getImageMatrix();
          paramRectF = new float[9];
          paramMatrix.getValues(paramRectF);
          paramView.putFloatArray("sharedElement:snapshot:imageMatrix", paramRectF);
          return paramView;
        }
      }
    }
    int j = Math.round(paramRectF.width());
    int i = Math.round(paramRectF.height());
    if ((j > 0) && (i > 0))
    {
      float f = Math.min(1.0F, MAX_IMAGE_SIZE / (j * i));
      j = (int)(j * f);
      i = (int)(i * f);
      if (mTempMatrix == null) {
        mTempMatrix = new Matrix();
      }
      mTempMatrix.set(paramMatrix);
      mTempMatrix.postTranslate(-left, -top);
      mTempMatrix.postScale(f, f);
      paramMatrix = Bitmap.createBitmap(j, i, Bitmap.Config.ARGB_8888);
      paramRectF = new Canvas(paramMatrix);
      paramRectF.concat(mTempMatrix);
      paramView.draw(paramRectF);
      return paramMatrix;
      label269:
      return paramView;
    }
    return null;
  }
  
  public View onCreateSnapshotView(Context paramContext, Parcelable paramParcelable)
  {
    if ((paramParcelable instanceof Bundle))
    {
      Object localObject = (Bundle)paramParcelable;
      Bitmap localBitmap = (Bitmap)((Bundle)localObject).getParcelable("sharedElement:snapshot:bitmap");
      if (localBitmap == null) {
        return null;
      }
      paramParcelable = new ImageView(paramContext);
      paramParcelable.setImageBitmap(localBitmap);
      paramParcelable.setScaleType(ImageView.ScaleType.valueOf(((Bundle)localObject).getString("sharedElement:snapshot:imageScaleType")));
      paramContext = paramParcelable;
      if (paramParcelable.getScaleType() == ImageView.ScaleType.MATRIX)
      {
        paramContext = ((Bundle)localObject).getFloatArray("sharedElement:snapshot:imageMatrix");
        localObject = new Matrix();
        ((Matrix)localObject).setValues(paramContext);
        paramParcelable.setImageMatrix((Matrix)localObject);
        return paramParcelable;
      }
    }
    else
    {
      if (!(paramParcelable instanceof Bitmap)) {
        break label125;
      }
      paramParcelable = (Bitmap)paramParcelable;
      paramContext = new ImageView(paramContext);
      paramContext.setImageBitmap(paramParcelable);
    }
    return paramContext;
    label125:
    return null;
  }
  
  public void onMapSharedElements(List paramList, Map paramMap) {}
  
  public void onRejectSharedElements(List paramList) {}
  
  public void onSharedElementEnd(List paramList1, List paramList2, List paramList3) {}
  
  public void onSharedElementStart(List paramList1, List paramList2, List paramList3) {}
  
  public void onSharedElementsArrived(List paramList1, List paramList2, OnSharedElementsReadyListener paramOnSharedElementsReadyListener)
  {
    paramOnSharedElementsReadyListener.onSharedElementsReady();
  }
  
  public abstract interface OnSharedElementsReadyListener
  {
    public abstract void onSharedElementsReady();
  }
}
