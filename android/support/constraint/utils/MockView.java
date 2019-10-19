package android.support.constraint.utils;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.constraint.R.styleable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

public class MockView
  extends View
{
  private int mDiagonalsColor = Color.argb(255, 0, 0, 0);
  private boolean mDrawDiagonals = true;
  private boolean mDrawLabel = true;
  private int mMargin = 4;
  private Paint mPaintDiagonals = new Paint();
  private Paint mPaintText = new Paint();
  private Paint mPaintTextBackground = new Paint();
  private String mText = null;
  private int mTextBackgroundColor = Color.argb(255, 50, 50, 50);
  private Rect mTextBounds = new Rect();
  private int mTextColor = Color.argb(255, 200, 200, 200);
  
  public MockView(Context paramContext)
  {
    super(paramContext);
    init(paramContext, null);
  }
  
  public MockView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext, paramAttributeSet);
  }
  
  public MockView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext, paramAttributeSet);
  }
  
  private void init(Context paramContext, AttributeSet paramAttributeSet)
  {
    if (paramAttributeSet != null)
    {
      paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.MockView);
      int j = paramAttributeSet.getIndexCount();
      int i = 0;
      while (i < j)
      {
        int k = paramAttributeSet.getIndex(i);
        if (k == R.styleable.MockView_mock_label) {
          mText = paramAttributeSet.getString(k);
        } else if (k == R.styleable.MockView_mock_showDiagonals) {
          mDrawDiagonals = paramAttributeSet.getBoolean(k, mDrawDiagonals);
        } else if (k == R.styleable.MockView_mock_diagonalsColor) {
          mDiagonalsColor = paramAttributeSet.getColor(k, mDiagonalsColor);
        } else if (k == R.styleable.MockView_mock_labelBackgroundColor) {
          mTextBackgroundColor = paramAttributeSet.getColor(k, mTextBackgroundColor);
        } else if (k == R.styleable.MockView_mock_labelColor) {
          mTextColor = paramAttributeSet.getColor(k, mTextColor);
        } else if (k == R.styleable.MockView_mock_showLabel) {
          mDrawLabel = paramAttributeSet.getBoolean(k, mDrawLabel);
        }
        i += 1;
      }
    }
    if (mText == null) {}
    try
    {
      paramContext = paramContext.getResources().getResourceEntryName(getId());
      mText = paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    mPaintDiagonals.setColor(mDiagonalsColor);
    mPaintDiagonals.setAntiAlias(true);
    mPaintText.setColor(mTextColor);
    mPaintText.setAntiAlias(true);
    mPaintTextBackground.setColor(mTextBackgroundColor);
    mMargin = Math.round(mMargin * (getResourcesgetDisplayMetricsxdpi / 160.0F));
  }
  
  public void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    int m = getWidth();
    int j = m;
    int k = getHeight();
    int i = k;
    float f1;
    float f2;
    if (mDrawDiagonals)
    {
      j = m - 1;
      i = k - 1;
      f1 = j;
      f2 = i;
      paramCanvas.drawLine(0.0F, 0.0F, f1, f2, mPaintDiagonals);
      paramCanvas.drawLine(0.0F, f2, f1, 0.0F, mPaintDiagonals);
      paramCanvas.drawLine(0.0F, 0.0F, f1, 0.0F, mPaintDiagonals);
      paramCanvas.drawLine(f1, 0.0F, f1, f2, mPaintDiagonals);
      paramCanvas.drawLine(f1, f2, 0.0F, f2, mPaintDiagonals);
      paramCanvas.drawLine(0.0F, f2, 0.0F, 0.0F, mPaintDiagonals);
    }
    if ((mText != null) && (mDrawLabel))
    {
      mPaintText.getTextBounds(mText, 0, mText.length(), mTextBounds);
      f1 = (j - mTextBounds.width()) / 2.0F;
      f2 = (i - mTextBounds.height()) / 2.0F + mTextBounds.height();
      mTextBounds.offset((int)f1, (int)f2);
      mTextBounds.set(mTextBounds.left - mMargin, mTextBounds.top - mMargin, mTextBounds.right + mMargin, mTextBounds.bottom + mMargin);
      paramCanvas.drawRect(mTextBounds, mPaintTextBackground);
      paramCanvas.drawText(mText, f1, f2, mPaintText);
    }
  }
}
