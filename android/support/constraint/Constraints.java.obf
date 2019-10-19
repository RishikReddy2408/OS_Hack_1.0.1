package android.support.constraint;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

public class Constraints
  extends ViewGroup
{
  public static final String TAG = "Constraints";
  ConstraintSet myConstraintSet;
  
  public Constraints(Context paramContext)
  {
    super(paramContext);
    super.setVisibility(8);
  }
  
  public Constraints(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramAttributeSet);
    super.setVisibility(8);
  }
  
  public Constraints(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramAttributeSet);
    super.setVisibility(8);
  }
  
  private void init(AttributeSet paramAttributeSet)
  {
    Log.v("Constraints", " ################# init");
  }
  
  protected LayoutParams generateDefaultLayoutParams()
  {
    return new LayoutParams(-2, -2);
  }
  
  public LayoutParams generateLayoutParams(AttributeSet paramAttributeSet)
  {
    return new LayoutParams(getContext(), paramAttributeSet);
  }
  
  protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return new ConstraintLayout.LayoutParams(paramLayoutParams);
  }
  
  public ConstraintSet getConstraintSet()
  {
    if (myConstraintSet == null) {
      myConstraintSet = new ConstraintSet();
    }
    myConstraintSet.clone(this);
    return myConstraintSet;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {}
  
  public static class LayoutParams
    extends ConstraintLayout.LayoutParams
  {
    public float alpha = 1.0F;
    public boolean applyElevation;
    public float elevation;
    public float rotation;
    public float rotationX;
    public float rotationY;
    public float scaleX;
    public float scaleY;
    public float transformPivotX;
    public float transformPivotY;
    public float translationX;
    public float translationY;
    public float translationZ;
    
    public LayoutParams(int paramInt1, int paramInt2)
    {
      super(paramInt2);
      applyElevation = false;
      elevation = 0.0F;
      rotation = 0.0F;
      rotationX = 0.0F;
      rotationY = 0.0F;
      scaleX = 1.0F;
      scaleY = 1.0F;
      transformPivotX = 0.0F;
      transformPivotY = 0.0F;
      translationX = 0.0F;
      translationY = 0.0F;
      translationZ = 0.0F;
    }
    
    public LayoutParams(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
      int i = 0;
      applyElevation = false;
      elevation = 0.0F;
      rotation = 0.0F;
      rotationX = 0.0F;
      rotationY = 0.0F;
      scaleX = 1.0F;
      scaleY = 1.0F;
      transformPivotX = 0.0F;
      transformPivotY = 0.0F;
      translationX = 0.0F;
      translationY = 0.0F;
      translationZ = 0.0F;
      paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ConstraintSet);
      int j = paramContext.getIndexCount();
      while (i < j)
      {
        int k = paramContext.getIndex(i);
        if (k == R.styleable.ConstraintSet_android_alpha)
        {
          alpha = paramContext.getFloat(k, alpha);
        }
        else if (k == R.styleable.ConstraintSet_android_elevation)
        {
          elevation = paramContext.getFloat(k, elevation);
          applyElevation = true;
        }
        else if (k == R.styleable.ConstraintSet_android_rotationX)
        {
          rotationX = paramContext.getFloat(k, rotationX);
        }
        else if (k == R.styleable.ConstraintSet_android_rotationY)
        {
          rotationY = paramContext.getFloat(k, rotationY);
        }
        else if (k == R.styleable.ConstraintSet_android_rotation)
        {
          rotation = paramContext.getFloat(k, rotation);
        }
        else if (k == R.styleable.ConstraintSet_android_scaleX)
        {
          scaleX = paramContext.getFloat(k, scaleX);
        }
        else if (k == R.styleable.ConstraintSet_android_scaleY)
        {
          scaleY = paramContext.getFloat(k, scaleY);
        }
        else if (k == R.styleable.ConstraintSet_android_transformPivotX)
        {
          transformPivotX = paramContext.getFloat(k, transformPivotX);
        }
        else if (k == R.styleable.ConstraintSet_android_transformPivotY)
        {
          transformPivotY = paramContext.getFloat(k, transformPivotY);
        }
        else if (k == R.styleable.ConstraintSet_android_translationX)
        {
          translationX = paramContext.getFloat(k, translationX);
        }
        else if (k == R.styleable.ConstraintSet_android_translationY)
        {
          translationY = paramContext.getFloat(k, translationY);
        }
        else if (k == R.styleable.ConstraintSet_android_translationZ)
        {
          translationX = paramContext.getFloat(k, translationZ);
        }
        i += 1;
      }
    }
    
    public LayoutParams(LayoutParams paramLayoutParams)
    {
      super();
      applyElevation = false;
      elevation = 0.0F;
      rotation = 0.0F;
      rotationX = 0.0F;
      rotationY = 0.0F;
      scaleX = 1.0F;
      scaleY = 1.0F;
      transformPivotX = 0.0F;
      transformPivotY = 0.0F;
      translationX = 0.0F;
      translationY = 0.0F;
      translationZ = 0.0F;
    }
  }
}
