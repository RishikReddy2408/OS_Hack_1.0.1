package android.support.constraint;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.constraint.solver.widgets.ConstraintWidget;
import android.util.AttributeSet;
import android.view.View;

public class Placeholder
  extends View
{
  private View mContent = null;
  private int mContentId = -1;
  private int mEmptyVisibility = 4;
  
  public Placeholder(Context paramContext)
  {
    super(paramContext);
    init(null);
  }
  
  public Placeholder(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramAttributeSet);
  }
  
  public Placeholder(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramAttributeSet);
  }
  
  public Placeholder(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    super(paramContext, paramAttributeSet, paramInt1);
    init(paramAttributeSet);
  }
  
  private void init(AttributeSet paramAttributeSet)
  {
    super.setVisibility(mEmptyVisibility);
    mContentId = -1;
    if (paramAttributeSet != null)
    {
      paramAttributeSet = getContext().obtainStyledAttributes(paramAttributeSet, R.styleable.ConstraintLayout_placeholder);
      int j = paramAttributeSet.getIndexCount();
      int i = 0;
      while (i < j)
      {
        int k = paramAttributeSet.getIndex(i);
        if (k == R.styleable.ConstraintLayout_placeholder_content) {
          mContentId = paramAttributeSet.getResourceId(k, mContentId);
        } else if (k == R.styleable.ConstraintLayout_placeholder_emptyVisibility) {
          mEmptyVisibility = paramAttributeSet.getInt(k, mEmptyVisibility);
        }
        i += 1;
      }
    }
  }
  
  public View getContent()
  {
    return mContent;
  }
  
  public int getEmptyVisibility()
  {
    return mEmptyVisibility;
  }
  
  public void onDraw(Canvas paramCanvas)
  {
    if (isInEditMode())
    {
      paramCanvas.drawRGB(223, 223, 223);
      Paint localPaint = new Paint();
      localPaint.setARGB(255, 210, 210, 210);
      localPaint.setTextAlign(Paint.Align.CENTER);
      localPaint.setTypeface(Typeface.create(Typeface.DEFAULT, 0));
      Rect localRect = new Rect();
      paramCanvas.getClipBounds(localRect);
      localPaint.setTextSize(localRect.height());
      int i = localRect.height();
      int j = localRect.width();
      localPaint.setTextAlign(Paint.Align.LEFT);
      localPaint.getTextBounds("?", 0, "?".length(), localRect);
      paramCanvas.drawText("?", j / 2.0F - localRect.width() / 2.0F - left, i / 2.0F + localRect.height() / 2.0F - bottom, localPaint);
    }
  }
  
  public void setContentId(int paramInt)
  {
    if (mContentId == paramInt) {
      return;
    }
    if (mContent != null)
    {
      mContent.setVisibility(0);
      mContent.getLayoutParams()).isInPlaceholder = false;
      mContent = null;
    }
    mContentId = paramInt;
    if (paramInt != -1)
    {
      View localView = ((View)getParent()).findViewById(paramInt);
      if (localView != null) {
        localView.setVisibility(8);
      }
    }
  }
  
  public void setEmptyVisibility(int paramInt)
  {
    mEmptyVisibility = paramInt;
  }
  
  public void updatePostMeasure(ConstraintLayout paramConstraintLayout)
  {
    if (mContent == null) {
      return;
    }
    paramConstraintLayout = (ConstraintLayout.LayoutParams)getLayoutParams();
    ConstraintLayout.LayoutParams localLayoutParams = (ConstraintLayout.LayoutParams)mContent.getLayoutParams();
    widget.setVisibility(0);
    widget.setWidth(widget.getWidth());
    widget.setHeight(widget.getHeight());
    widget.setVisibility(8);
  }
  
  public void updatePreLayout(ConstraintLayout paramConstraintLayout)
  {
    if ((mContentId == -1) && (!isInEditMode())) {
      setVisibility(mEmptyVisibility);
    }
    mContent = paramConstraintLayout.findViewById(mContentId);
    if (mContent != null)
    {
      mContent.getLayoutParams()).isInPlaceholder = true;
      mContent.setVisibility(0);
      setVisibility(0);
    }
  }
}
