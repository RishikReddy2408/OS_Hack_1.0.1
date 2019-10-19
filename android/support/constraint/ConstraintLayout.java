package android.support.constraint;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.constraint.solver.Metrics;
import android.support.constraint.solver.widgets.Analyzer;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.constraint.solver.widgets.ConstraintAnchor.Strength;
import android.support.constraint.solver.widgets.ConstraintAnchor.Type;
import android.support.constraint.solver.widgets.ConstraintWidget;
import android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour;
import android.support.constraint.solver.widgets.ConstraintWidgetContainer;
import android.support.constraint.solver.widgets.ResolutionAnchor;
import android.support.constraint.solver.widgets.ResolutionDimension;
import android.support.constraint.solver.widgets.ResolutionNode;
import android.support.constraint.solver.widgets.WidgetContainer;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import java.util.ArrayList;
import java.util.HashMap;

public class ConstraintLayout
  extends ViewGroup
{
  static final boolean ALLOWS_EMBEDDED = false;
  private static final boolean CACHE_MEASURED_DIMENSION = false;
  private static final boolean DEBUG = false;
  public static final int DESIGN_INFO_ID = 0;
  private static final String PAGE_KEY = "ConstraintLayout";
  private static final boolean SUPPORTS_ID_EDITOR = false;
  private static final boolean USE_CONSTRAINTS_HELPER = true;
  public static final String VERSION = "ConstraintLayout-2.0-alpha2";
  SparseArray<View> mChildrenByIds = new SparseArray();
  private ArrayList<ConstraintHelper> mConstraintHelpers = new ArrayList(4);
  protected ConstraintLayoutStates mConstraintLayoutSpec = null;
  private ConstraintSet mConstraintSet = null;
  private int mConstraintSetId = -1;
  private ConstraintsChangedListener mConstraintsChangedListener;
  private HashMap<String, Integer> mDesignIds = new HashMap();
  private boolean mDirtyHierarchy = true;
  Handler mHandler = new Handler(Looper.getMainLooper())
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      setState(mConstraintSetId, arg1, arg2);
    }
  };
  private int mLastMeasureHeight = -1;
  int mLastMeasureHeightMode = 0;
  int mLastMeasureHeightSize = -1;
  private int mLastMeasureWidth = -1;
  int mLastMeasureWidthMode = 0;
  int mLastMeasureWidthSize = -1;
  ConstraintWidgetContainer mLayoutWidget = new ConstraintWidgetContainer();
  private int mMaxHeight = Integer.MAX_VALUE;
  private int mMaxWidth = Integer.MAX_VALUE;
  private Metrics mMetrics;
  private int mMinHeight = 0;
  private int mMinWidth = 0;
  private int mOptimizationLevel = 3;
  private final ArrayList<ConstraintWidget> mVariableDimensionsWidgets = new ArrayList(100);
  
  public ConstraintLayout(Context paramContext)
  {
    super(paramContext);
    init(null);
  }
  
  public ConstraintLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramAttributeSet);
  }
  
  public ConstraintLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramAttributeSet);
  }
  
  private final ConstraintWidget getTargetWidget(int paramInt)
  {
    if (paramInt == 0) {
      return mLayoutWidget;
    }
    View localView = (View)mChildrenByIds.get(paramInt);
    if (localView == this) {
      return mLayoutWidget;
    }
    if (localView == null) {
      return null;
    }
    return getLayoutParamswidget;
  }
  
  private void init(AttributeSet paramAttributeSet)
  {
    mLayoutWidget.setCompanionWidget(this);
    mChildrenByIds.put(getId(), this);
    mConstraintSet = null;
    if (paramAttributeSet != null)
    {
      paramAttributeSet = getContext().obtainStyledAttributes(paramAttributeSet, R.styleable.ConstraintLayout_Layout);
      int j = paramAttributeSet.getIndexCount();
      int i = 0;
      while (i < j)
      {
        int k = paramAttributeSet.getIndex(i);
        if (k == R.styleable.ConstraintLayout_Layout_android_minWidth)
        {
          mMinWidth = paramAttributeSet.getDimensionPixelOffset(k, mMinWidth);
        }
        else if (k == R.styleable.ConstraintLayout_Layout_android_minHeight)
        {
          mMinHeight = paramAttributeSet.getDimensionPixelOffset(k, mMinHeight);
        }
        else if (k == R.styleable.ConstraintLayout_Layout_android_maxWidth)
        {
          mMaxWidth = paramAttributeSet.getDimensionPixelOffset(k, mMaxWidth);
        }
        else if (k == R.styleable.ConstraintLayout_Layout_android_maxHeight)
        {
          mMaxHeight = paramAttributeSet.getDimensionPixelOffset(k, mMaxHeight);
        }
        else if (k == R.styleable.ConstraintLayout_Layout_layout_optimizationLevel)
        {
          mOptimizationLevel = paramAttributeSet.getInt(k, mOptimizationLevel);
        }
        else if (k == R.styleable.ConstraintLayout_Layout_layoutDescription)
        {
          k = paramAttributeSet.getResourceId(k, 0);
          if (k == 0) {
            break label287;
          }
        }
        try
        {
          parseLayoutDescription(k);
        }
        catch (Resources.NotFoundException localNotFoundException1)
        {
          for (;;) {}
        }
        mConstraintLayoutSpec = null;
        break label287;
        if (k == R.styleable.ConstraintLayout_Layout_constraintSet) {
          k = paramAttributeSet.getResourceId(k, 0);
        }
        try
        {
          ConstraintSet localConstraintSet = new ConstraintSet();
          mConstraintSet = localConstraintSet;
          localConstraintSet = mConstraintSet;
          localConstraintSet.load(getContext(), k);
        }
        catch (Resources.NotFoundException localNotFoundException2)
        {
          for (;;) {}
        }
        mConstraintSet = null;
        mConstraintSetId = k;
        label287:
        i += 1;
      }
      paramAttributeSet.recycle();
    }
    mLayoutWidget.setOptimizationLevel(mOptimizationLevel);
  }
  
  private void internalMeasureChildren(int paramInt1, int paramInt2)
  {
    int i3 = getPaddingTop() + getPaddingBottom();
    int i4 = getPaddingLeft() + getPaddingRight();
    int i5 = getChildCount();
    int n = 0;
    while (n < i5)
    {
      View localView = getChildAt(n);
      if (localView.getVisibility() != 8)
      {
        LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
        ConstraintWidget localConstraintWidget = widget;
        if ((!isGuideline) && ((!isHelper) || (isVirtualGroup)))
        {
          localConstraintWidget.setVisibility(localView.getVisibility());
          if (isVirtualGroup) {
            localConstraintWidget.setVisibility(0);
          }
          int i1 = width;
          int i2 = height;
          int i;
          if ((!horizontalDimensionFixed) && (!verticalDimensionFixed) && ((horizontalDimensionFixed) || (matchConstraintDefaultWidth != 1)) && (width != -1) && ((verticalDimensionFixed) || ((matchConstraintDefaultHeight != 1) && (height != -1)))) {
            i = 0;
          } else {
            i = 1;
          }
          int k;
          int m;
          int j;
          if (i != 0)
          {
            if (i1 == 0)
            {
              k = ViewGroup.getChildMeasureSpec(paramInt1, i4, -2);
              i = 1;
            }
            else if (i1 == -1)
            {
              k = ViewGroup.getChildMeasureSpec(paramInt1, i4, -1);
              i = 0;
            }
            else
            {
              if (i1 == -2) {
                i = 1;
              } else {
                i = 0;
              }
              k = ViewGroup.getChildMeasureSpec(paramInt1, i4, i1);
            }
            if (i2 == 0)
            {
              m = ViewGroup.getChildMeasureSpec(paramInt2, i3, -2);
              j = 1;
            }
            else if (i2 == -1)
            {
              m = ViewGroup.getChildMeasureSpec(paramInt2, i3, -1);
              j = 0;
            }
            else
            {
              if (i2 == -2) {
                j = 1;
              } else {
                j = 0;
              }
              m = ViewGroup.getChildMeasureSpec(paramInt2, i3, i2);
            }
            localView.measure(k, m);
            if (mMetrics != null)
            {
              Metrics localMetrics = mMetrics;
              measures += 1L;
            }
            boolean bool;
            if (i1 == -2) {
              bool = true;
            } else {
              bool = false;
            }
            localConstraintWidget.setWidthWrapContent(bool);
            if (i2 == -2) {
              bool = true;
            } else {
              bool = false;
            }
            localConstraintWidget.setHeightWrapContent(bool);
            k = localView.getMeasuredWidth();
            m = localView.getMeasuredHeight();
          }
          else
          {
            i = 0;
            j = 0;
            m = i2;
            k = i1;
          }
          localConstraintWidget.setWidth(k);
          localConstraintWidget.setHeight(m);
          if (i != 0) {
            localConstraintWidget.setWrapWidth(k);
          }
          if (j != 0) {
            localConstraintWidget.setWrapHeight(m);
          }
          if (needsBaseline)
          {
            i = localView.getBaseline();
            if (i != -1) {
              localConstraintWidget.setBaselineDistance(i);
            }
          }
        }
      }
      n += 1;
    }
  }
  
  private void internalMeasureDimensions(int paramInt1, int paramInt2)
  {
    int i = getPaddingTop() + getPaddingBottom();
    int i7 = getPaddingLeft() + getPaddingRight();
    int i8 = getChildCount();
    int j = 0;
    View localView;
    LayoutParams localLayoutParams;
    ConstraintWidget localConstraintWidget;
    int n;
    int k;
    int m;
    Object localObject;
    boolean bool;
    while (j < i8)
    {
      localView = getChildAt(j);
      if (localView.getVisibility() != 8)
      {
        do
        {
          localLayoutParams = (LayoutParams)localView.getLayoutParams();
          localConstraintWidget = widget;
        } while ((isGuideline) || (isHelper));
        localConstraintWidget.setVisibility(localView.getVisibility());
        n = width;
        i1 = height;
        if ((n != 0) && (i1 != 0))
        {
          if (n == -2) {
            k = 1;
          } else {
            k = 0;
          }
          i2 = ViewGroup.getChildMeasureSpec(paramInt1, i7, n);
          if (i1 == -2) {
            m = 1;
          } else {
            m = 0;
          }
          localView.measure(i2, ViewGroup.getChildMeasureSpec(paramInt2, i, i1));
          if (mMetrics != null)
          {
            localObject = mMetrics;
            measures += 1L;
          }
          if (n == -2) {
            bool = true;
          } else {
            bool = false;
          }
          localConstraintWidget.setWidthWrapContent(bool);
          if (i1 == -2) {
            bool = true;
          } else {
            bool = false;
          }
          localConstraintWidget.setHeightWrapContent(bool);
          n = localView.getMeasuredWidth();
          i1 = localView.getMeasuredHeight();
          localConstraintWidget.setWidth(n);
          localConstraintWidget.setHeight(i1);
          if (k != 0) {
            localConstraintWidget.setWrapWidth(n);
          }
          if (m != 0) {
            localConstraintWidget.setWrapHeight(i1);
          }
          if (needsBaseline)
          {
            k = localView.getBaseline();
            if (k != -1) {
              localConstraintWidget.setBaselineDistance(k);
            }
          }
          if ((horizontalDimensionFixed) && (verticalDimensionFixed))
          {
            localConstraintWidget.getResolutionWidth().resolve(n);
            localConstraintWidget.getResolutionHeight().resolve(i1);
          }
        }
        else
        {
          localConstraintWidget.getResolutionWidth().invalidate();
          localConstraintWidget.getResolutionHeight().invalidate();
        }
      }
      j += 1;
    }
    mLayoutWidget.solveGraph();
    int i2 = 0;
    int i1 = i;
    while (i2 < i8)
    {
      localView = getChildAt(i2);
      if (localView.getVisibility() != 8)
      {
        int i5;
        int i3;
        do
        {
          do
          {
            localLayoutParams = (LayoutParams)localView.getLayoutParams();
            localConstraintWidget = widget;
          } while ((isGuideline) || (isHelper));
          localConstraintWidget.setVisibility(localView.getVisibility());
          i5 = width;
          i3 = height;
        } while ((i5 != 0) && (i3 != 0));
        localObject = localConstraintWidget.getAnchor(ConstraintAnchor.Type.LEFT).getResolutionNode();
        ResolutionAnchor localResolutionAnchor1 = localConstraintWidget.getAnchor(ConstraintAnchor.Type.RIGHT).getResolutionNode();
        if ((localConstraintWidget.getAnchor(ConstraintAnchor.Type.LEFT).getTarget() != null) && (localConstraintWidget.getAnchor(ConstraintAnchor.Type.RIGHT).getTarget() != null)) {
          j = 1;
        } else {
          j = 0;
        }
        ResolutionAnchor localResolutionAnchor2 = localConstraintWidget.getAnchor(ConstraintAnchor.Type.MIDDLE).getResolutionNode();
        ResolutionAnchor localResolutionAnchor3 = localConstraintWidget.getAnchor(ConstraintAnchor.Type.BOTTOM).getResolutionNode();
        if ((localConstraintWidget.getAnchor(ConstraintAnchor.Type.MIDDLE).getTarget() != null) && (localConstraintWidget.getAnchor(ConstraintAnchor.Type.BOTTOM).getTarget() != null)) {
          n = 1;
        } else {
          n = 0;
        }
        if ((i5 != 0) || (i3 != 0) || (j == 0) || (n == 0))
        {
          if (mLayoutWidget.getHorizontalDimensionBehaviour() != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
            m = 1;
          } else {
            m = 0;
          }
          if (mLayoutWidget.getVerticalDimensionBehaviour() != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
            i = 1;
          } else {
            i = 0;
          }
          if (m == 0) {
            localConstraintWidget.getResolutionWidth().invalidate();
          }
          if (i == 0) {
            localConstraintWidget.getResolutionHeight().invalidate();
          }
          if (i5 == 0)
          {
            if ((m != 0) && (localConstraintWidget.isSpreadWidth()) && (j != 0) && (((ResolutionNode)localObject).isResolved()) && (localResolutionAnchor1.isResolved()))
            {
              j = (int)(localResolutionAnchor1.getResolvedValue() - ((ResolutionAnchor)localObject).getResolvedValue());
              localConstraintWidget.getResolutionWidth().resolve(j);
              k = ViewGroup.getChildMeasureSpec(paramInt1, i7, j);
            }
            else
            {
              k = ViewGroup.getChildMeasureSpec(paramInt1, i7, -2);
              i4 = 0;
              j = 1;
              break label897;
            }
          }
          else
          {
            if (i5 != -1) {
              break label867;
            }
            k = ViewGroup.getChildMeasureSpec(paramInt1, i7, -1);
            j = i5;
          }
          int i6 = 0;
          i5 = j;
          int i4 = m;
          j = i6;
          break label897;
          label867:
          if (i5 == -2) {
            j = 1;
          } else {
            j = 0;
          }
          k = ViewGroup.getChildMeasureSpec(paramInt1, i7, i5);
          i4 = m;
          label897:
          if (i3 == 0)
          {
            if ((i != 0) && (localConstraintWidget.isSpreadHeight()) && (n != 0) && (localResolutionAnchor2.isResolved()) && (localResolutionAnchor3.isResolved()))
            {
              n = (int)(localResolutionAnchor3.getResolvedValue() - localResolutionAnchor2.getResolvedValue());
              localConstraintWidget.getResolutionHeight().resolve(n);
              m = ViewGroup.getChildMeasureSpec(paramInt2, i1, n);
            }
            else
            {
              m = ViewGroup.getChildMeasureSpec(paramInt2, i1, -2);
              i = 0;
              n = 1;
              break label1049;
            }
          }
          else
          {
            if (i3 != -1) {
              break label1023;
            }
            m = ViewGroup.getChildMeasureSpec(paramInt2, i1, -1);
            n = i3;
          }
          i6 = 0;
          i3 = n;
          n = i6;
          break label1049;
          label1023:
          if (i3 == -2) {
            n = 1;
          } else {
            n = 0;
          }
          m = ViewGroup.getChildMeasureSpec(paramInt2, i1, i3);
          label1049:
          localView.measure(k, m);
          if (mMetrics != null)
          {
            localObject = mMetrics;
            measures += 1L;
          }
          if (i5 == -2) {
            bool = true;
          } else {
            bool = false;
          }
          localConstraintWidget.setWidthWrapContent(bool);
          if (i3 == -2) {
            bool = true;
          } else {
            bool = false;
          }
          localConstraintWidget.setHeightWrapContent(bool);
          k = localView.getMeasuredWidth();
          m = localView.getMeasuredHeight();
          localConstraintWidget.setWidth(k);
          localConstraintWidget.setHeight(m);
          if (j != 0) {
            localConstraintWidget.setWrapWidth(k);
          }
          if (n != 0) {
            localConstraintWidget.setWrapHeight(m);
          }
          if (i4 != 0) {
            localConstraintWidget.getResolutionWidth().resolve(k);
          } else {
            localConstraintWidget.getResolutionWidth().remove();
          }
          if (i != 0) {
            localConstraintWidget.getResolutionHeight().resolve(m);
          }
          for (;;)
          {
            break;
            localConstraintWidget.getResolutionHeight().remove();
          }
          if (needsBaseline)
          {
            i = localView.getBaseline();
            if (i != -1) {
              localConstraintWidget.setBaselineDistance(i);
            }
          }
        }
      }
      i2 += 1;
    }
  }
  
  private void setChildrenConstraints()
  {
    boolean bool = isInEditMode();
    int i5 = getChildCount();
    int i = 0;
    Object localObject1;
    while (i < i5)
    {
      localObject1 = getViewWidget(getChildAt(i));
      if (localObject1 != null) {
        ((ConstraintWidget)localObject1).reset();
      }
      i += 1;
    }
    if (mConstraintSetId != -1)
    {
      i = 0;
      while (i < i5)
      {
        localObject1 = getChildAt(i);
        if ((((View)localObject1).getId() == mConstraintSetId) && ((localObject1 instanceof Constraints))) {
          mConstraintSet = ((Constraints)localObject1).getConstraintSet();
        }
        i += 1;
      }
    }
    if (mConstraintSet != null) {
      mConstraintSet.applyToInternal(this);
    }
    mLayoutWidget.removeAllChildren();
    int j = mConstraintHelpers.size();
    if (j > 0)
    {
      i = 0;
      while (i < j)
      {
        ((ConstraintHelper)mConstraintHelpers.get(i)).updatePreLayout(this);
        i += 1;
      }
    }
    i = 0;
    while (i < i5)
    {
      localObject1 = getChildAt(i);
      if ((localObject1 instanceof Placeholder)) {
        ((Placeholder)localObject1).updatePreLayout(this);
      }
      i += 1;
    }
    int n = 0;
    while (n < i5)
    {
      Object localObject3 = getChildAt(n);
      Object localObject2 = getViewWidget((View)localObject3);
      if (localObject2 != null)
      {
        localObject1 = (LayoutParams)((View)localObject3).getLayoutParams();
        ((LayoutParams)localObject1).validate();
        if (helped) {
          helped = false;
        }
        ((ConstraintWidget)localObject2).setVisibility(((View)localObject3).getVisibility());
        if (isInPlaceholder) {
          ((ConstraintWidget)localObject2).setVisibility(8);
        }
        ((ConstraintWidget)localObject2).setCompanionWidget(localObject3);
        mLayoutWidget.multiply((ConstraintWidget)localObject2);
        if ((!verticalDimensionFixed) || (!horizontalDimensionFixed)) {
          mVariableDimensionsWidgets.add(localObject2);
        }
        float f;
        if (isGuideline)
        {
          localObject2 = (android.support.constraint.solver.widgets.Guideline)localObject2;
          i = resolvedGuideBegin;
          j = resolvedGuideEnd;
          f = resolvedGuidePercent;
          if (Build.VERSION.SDK_INT < 17)
          {
            i = guideBegin;
            j = guideEnd;
            f = guidePercent;
          }
          if (f != -1.0F) {
            ((android.support.constraint.solver.widgets.Guideline)localObject2).setGuidePercent(f);
          } else if (i != -1) {
            ((android.support.constraint.solver.widgets.Guideline)localObject2).setGuideBegin(i);
          } else if (j != -1) {
            ((android.support.constraint.solver.widgets.Guideline)localObject2).setGuideEnd(j);
          }
        }
        else if ((leftToLeft != -1) || (leftToRight != -1) || (rightToLeft != -1) || (rightToRight != -1) || (startToStart != -1) || (startToEnd != -1) || (endToStart != -1) || (endToEnd != -1) || (topToTop != -1) || (topToBottom != -1) || (bottomToTop != -1) || (bottomToBottom != -1) || (baselineToBaseline != -1) || (editorAbsoluteX != -1) || (editorAbsoluteY != -1) || (circleConstraint != -1) || (width == -1) || (height == -1))
        {
          j = resolvedLeftToLeft;
          int k = resolvedRightToLeft;
          int m = resolvedRightToRight;
          f = resolvedHorizontalBias;
          int i1;
          int i2;
          if (Build.VERSION.SDK_INT < 17)
          {
            k = leftToLeft;
            m = leftToRight;
            int i3 = rightToLeft;
            int i4 = rightToRight;
            i1 = goneLeftMargin;
            i2 = goneRightMargin;
            f = horizontalBias;
            j = k;
            i = m;
            if (k == -1)
            {
              j = k;
              i = m;
              if (m == -1) {
                if (startToStart != -1)
                {
                  j = startToStart;
                  i = m;
                }
                else
                {
                  j = k;
                  i = m;
                  if (startToEnd != -1)
                  {
                    i = startToEnd;
                    j = k;
                  }
                }
              }
            }
            m = i4;
            k = i3;
            if (i3 == -1)
            {
              m = i4;
              k = i3;
              if (i4 == -1) {
                if (endToStart != -1)
                {
                  k = endToStart;
                  m = i4;
                }
                else
                {
                  m = i4;
                  k = i3;
                  if (endToEnd != -1)
                  {
                    m = endToEnd;
                    k = i3;
                  }
                }
              }
            }
          }
          else
          {
            i = resolvedLeftToRight;
            i2 = resolveGoneRightMargin;
            i1 = resolveGoneLeftMargin;
          }
          if (circleConstraint != -1)
          {
            localObject3 = getTargetWidget(circleConstraint);
            if (localObject3 != null) {
              ((ConstraintWidget)localObject2).connectCircularConstraint((ConstraintWidget)localObject3, circleAngle, circleRadius);
            }
          }
          else
          {
            if (j != -1)
            {
              localObject3 = getTargetWidget(j);
              if (localObject3 != null) {
                ((ConstraintWidget)localObject2).immediateConnect(ConstraintAnchor.Type.LEFT, (ConstraintWidget)localObject3, ConstraintAnchor.Type.LEFT, leftMargin, i1);
              }
            }
            else if (i != -1)
            {
              localObject3 = getTargetWidget(i);
              if (localObject3 != null) {
                ((ConstraintWidget)localObject2).immediateConnect(ConstraintAnchor.Type.LEFT, (ConstraintWidget)localObject3, ConstraintAnchor.Type.RIGHT, leftMargin, i1);
              }
            }
            if (k != -1)
            {
              localObject3 = getTargetWidget(k);
              if (localObject3 != null) {
                ((ConstraintWidget)localObject2).immediateConnect(ConstraintAnchor.Type.RIGHT, (ConstraintWidget)localObject3, ConstraintAnchor.Type.LEFT, rightMargin, i2);
              }
            }
            else if (m != -1)
            {
              localObject3 = getTargetWidget(m);
              if (localObject3 != null) {
                ((ConstraintWidget)localObject2).immediateConnect(ConstraintAnchor.Type.RIGHT, (ConstraintWidget)localObject3, ConstraintAnchor.Type.RIGHT, rightMargin, i2);
              }
            }
            if (topToTop != -1)
            {
              localObject3 = getTargetWidget(topToTop);
              if (localObject3 != null) {
                ((ConstraintWidget)localObject2).immediateConnect(ConstraintAnchor.Type.MIDDLE, (ConstraintWidget)localObject3, ConstraintAnchor.Type.MIDDLE, topMargin, goneTopMargin);
              }
            }
            else if (topToBottom != -1)
            {
              localObject3 = getTargetWidget(topToBottom);
              if (localObject3 != null) {
                ((ConstraintWidget)localObject2).immediateConnect(ConstraintAnchor.Type.MIDDLE, (ConstraintWidget)localObject3, ConstraintAnchor.Type.BOTTOM, topMargin, goneTopMargin);
              }
            }
            if (bottomToTop != -1)
            {
              localObject3 = getTargetWidget(bottomToTop);
              if (localObject3 != null) {
                ((ConstraintWidget)localObject2).immediateConnect(ConstraintAnchor.Type.BOTTOM, (ConstraintWidget)localObject3, ConstraintAnchor.Type.MIDDLE, bottomMargin, goneBottomMargin);
              }
            }
            else if (bottomToBottom != -1)
            {
              localObject3 = getTargetWidget(bottomToBottom);
              if (localObject3 != null) {
                ((ConstraintWidget)localObject2).immediateConnect(ConstraintAnchor.Type.BOTTOM, (ConstraintWidget)localObject3, ConstraintAnchor.Type.BOTTOM, bottomMargin, goneBottomMargin);
              }
            }
            if (baselineToBaseline != -1)
            {
              Object localObject4 = (View)mChildrenByIds.get(baselineToBaseline);
              localObject3 = getTargetWidget(baselineToBaseline);
              if ((localObject3 != null) && (localObject4 != null) && ((((View)localObject4).getLayoutParams() instanceof LayoutParams)))
              {
                localObject4 = (LayoutParams)((View)localObject4).getLayoutParams();
                needsBaseline = true;
                needsBaseline = true;
                ((ConstraintWidget)localObject2).getAnchor(ConstraintAnchor.Type.BASELINE).connect(((ConstraintWidget)localObject3).getAnchor(ConstraintAnchor.Type.BASELINE), 0, -1, ConstraintAnchor.Strength.STRONG, 0, true);
                ((ConstraintWidget)localObject2).getAnchor(ConstraintAnchor.Type.MIDDLE).reset();
                ((ConstraintWidget)localObject2).getAnchor(ConstraintAnchor.Type.BOTTOM).reset();
              }
            }
            if ((f >= 0.0F) && (f != 0.5F)) {
              ((ConstraintWidget)localObject2).setHorizontalBiasPercent(f);
            }
            if ((verticalBias >= 0.0F) && (verticalBias != 0.5F)) {
              ((ConstraintWidget)localObject2).setVerticalBiasPercent(verticalBias);
            }
          }
          if ((bool) && ((editorAbsoluteX != -1) || (editorAbsoluteY != -1))) {
            ((ConstraintWidget)localObject2).setOrigin(editorAbsoluteX, editorAbsoluteY);
          }
          if (!horizontalDimensionFixed)
          {
            if (width == -1)
            {
              ((ConstraintWidget)localObject2).setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_PARENT);
              getAnchorLEFTmMargin = leftMargin;
              getAnchorRIGHTmMargin = rightMargin;
            }
            else
            {
              ((ConstraintWidget)localObject2).setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
              ((ConstraintWidget)localObject2).setWidth(0);
            }
          }
          else
          {
            ((ConstraintWidget)localObject2).setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
            ((ConstraintWidget)localObject2).setWidth(width);
          }
          if (!verticalDimensionFixed)
          {
            if (height == -1)
            {
              ((ConstraintWidget)localObject2).setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_PARENT);
              getAnchorMIDDLEmMargin = topMargin;
              getAnchorBOTTOMmMargin = bottomMargin;
            }
            else
            {
              ((ConstraintWidget)localObject2).setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
              ((ConstraintWidget)localObject2).setHeight(0);
            }
          }
          else
          {
            ((ConstraintWidget)localObject2).setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
            ((ConstraintWidget)localObject2).setHeight(height);
          }
          if (dimensionRatio != null) {
            ((ConstraintWidget)localObject2).setDimensionRatio(dimensionRatio);
          }
          ((ConstraintWidget)localObject2).setHorizontalWeight(horizontalWeight);
          ((ConstraintWidget)localObject2).setVerticalWeight(verticalWeight);
          ((ConstraintWidget)localObject2).setHorizontalChainStyle(horizontalChainStyle);
          ((ConstraintWidget)localObject2).setVerticalChainStyle(verticalChainStyle);
          ((ConstraintWidget)localObject2).setHorizontalMatchStyle(matchConstraintDefaultWidth, matchConstraintMinWidth, matchConstraintMaxWidth, matchConstraintPercentWidth);
          ((ConstraintWidget)localObject2).setVerticalMatchStyle(matchConstraintDefaultHeight, matchConstraintMinHeight, matchConstraintMaxHeight, matchConstraintPercentHeight);
        }
      }
      n += 1;
    }
  }
  
  private void setSelfDimensionBehaviour(int paramInt1, int paramInt2)
  {
    int i1 = View.MeasureSpec.getMode(paramInt1);
    int j = View.MeasureSpec.getSize(paramInt1);
    paramInt1 = j;
    int k = View.MeasureSpec.getMode(paramInt2);
    int i = View.MeasureSpec.getSize(paramInt2);
    paramInt2 = i;
    int m = getPaddingTop();
    int n = getPaddingBottom();
    int i2 = getPaddingLeft();
    int i3 = getPaddingRight();
    ConstraintWidget.DimensionBehaviour localDimensionBehaviour1 = ConstraintWidget.DimensionBehaviour.FIXED;
    ConstraintWidget.DimensionBehaviour localDimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.FIXED;
    getLayoutParams();
    if (i1 != Integer.MIN_VALUE)
    {
      if (i1 != 0) {
        if (i1 == 1073741824) {}
      }
      for (;;)
      {
        paramInt1 = 0;
        break;
        paramInt1 = Math.min(mMaxWidth, j) - (i2 + i3);
        break;
        localDimensionBehaviour1 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
      }
    }
    localDimensionBehaviour1 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
    if (k != Integer.MIN_VALUE)
    {
      if (k != 0) {
        if (k == 1073741824) {}
      }
      for (;;)
      {
        paramInt2 = 0;
        break;
        paramInt2 = Math.min(mMaxHeight, i) - (m + n);
        break;
        localDimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
      }
    }
    localDimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
    mLayoutWidget.setMinWidth(0);
    mLayoutWidget.setMinHeight(0);
    mLayoutWidget.setHorizontalDimensionBehaviour(localDimensionBehaviour1);
    mLayoutWidget.setWidth(paramInt1);
    mLayoutWidget.setVerticalDimensionBehaviour(localDimensionBehaviour2);
    mLayoutWidget.setHeight(paramInt2);
    mLayoutWidget.setMinWidth(mMinWidth - getPaddingLeft() - getPaddingRight());
    mLayoutWidget.setMinHeight(mMinHeight - getPaddingTop() - getPaddingBottom());
  }
  
  private void updateHierarchy()
  {
    int m = getChildCount();
    int k = 0;
    int i = 0;
    int j;
    for (;;)
    {
      j = k;
      if (i >= m) {
        break;
      }
      if (getChildAt(i).isLayoutRequested())
      {
        j = 1;
        break;
      }
      i += 1;
    }
    if (j != 0)
    {
      mVariableDimensionsWidgets.clear();
      setChildrenConstraints();
    }
  }
  
  private void updatePostMeasures()
  {
    int k = getChildCount();
    int j = 0;
    int i = 0;
    while (i < k)
    {
      View localView = getChildAt(i);
      if ((localView instanceof Placeholder)) {
        ((Placeholder)localView).updatePostMeasure(this);
      }
      i += 1;
    }
    k = mConstraintHelpers.size();
    if (k > 0)
    {
      i = j;
      while (i < k)
      {
        ((ConstraintHelper)mConstraintHelpers.get(i)).updatePostMeasure(this);
        i += 1;
      }
    }
  }
  
  public void addView(View paramView, int paramInt, ViewGroup.LayoutParams paramLayoutParams)
  {
    super.addView(paramView, paramInt, paramLayoutParams);
    if (Build.VERSION.SDK_INT < 14) {
      onViewAdded(paramView);
    }
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return paramLayoutParams instanceof LayoutParams;
  }
  
  public void dispatchDraw(Canvas paramCanvas)
  {
    super.dispatchDraw(paramCanvas);
    if (isInEditMode())
    {
      int j = getChildCount();
      float f1 = getWidth();
      float f2 = getHeight();
      int i = 0;
      while (i < j)
      {
        Object localObject = getChildAt(i);
        if (((View)localObject).getVisibility() != 8)
        {
          localObject = ((View)localObject).getTag();
          if ((localObject != null) && ((localObject instanceof String)))
          {
            localObject = ((String)localObject).split(",");
            if (localObject.length == 4)
            {
              int m = Integer.parseInt(localObject[0]);
              int i1 = Integer.parseInt(localObject[1]);
              int n = Integer.parseInt(localObject[2]);
              int k = Integer.parseInt(localObject[3]);
              m = (int)(m / 1080.0F * f1);
              i1 = (int)(i1 / 1920.0F * f2);
              n = (int)(n / 1080.0F * f1);
              k = (int)(k / 1920.0F * f2);
              localObject = new Paint();
              ((Paint)localObject).setColor(-65536);
              float f3 = m;
              float f4 = i1;
              float f5 = m + n;
              paramCanvas.drawLine(f3, f4, f5, f4, (Paint)localObject);
              float f6 = i1 + k;
              paramCanvas.drawLine(f5, f4, f5, f6, (Paint)localObject);
              paramCanvas.drawLine(f5, f6, f3, f6, (Paint)localObject);
              paramCanvas.drawLine(f3, f6, f3, f4, (Paint)localObject);
              ((Paint)localObject).setColor(-16711936);
              paramCanvas.drawLine(f3, f4, f5, f6, (Paint)localObject);
              paramCanvas.drawLine(f3, f6, f5, f4, (Paint)localObject);
            }
          }
        }
        i += 1;
      }
    }
  }
  
  public void fillMetrics(Metrics paramMetrics)
  {
    mMetrics = paramMetrics;
    mLayoutWidget.fillMetrics(paramMetrics);
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
    return new LayoutParams(paramLayoutParams);
  }
  
  public Object getDesignInformation(int paramInt, Object paramObject)
  {
    if ((paramInt == 0) && ((paramObject instanceof String)))
    {
      paramObject = (String)paramObject;
      if ((mDesignIds != null) && (mDesignIds.containsKey(paramObject))) {
        return mDesignIds.get(paramObject);
      }
    }
    return null;
  }
  
  public int getLayoutDirection()
  {
    throw new Error("Unresolved compilation error: Method <android.support.constraint.ConstraintLayout: int getLayoutDirection()> does not exist!");
  }
  
  public int getMaxHeight()
  {
    return mMaxHeight;
  }
  
  public int getMaxWidth()
  {
    return mMaxWidth;
  }
  
  public int getMinHeight()
  {
    return mMinHeight;
  }
  
  public int getMinWidth()
  {
    return mMinWidth;
  }
  
  public int getOptimizationLevel()
  {
    return mLayoutWidget.getOptimizationLevel();
  }
  
  public View getViewById(int paramInt)
  {
    return (View)mChildrenByIds.get(paramInt);
  }
  
  public final ConstraintWidget getViewWidget(View paramView)
  {
    if (paramView == this) {
      return mLayoutWidget;
    }
    if (paramView == null) {
      return null;
    }
    return getLayoutParamswidget;
  }
  
  public void loadLayoutDescription(int paramInt)
  {
    if (paramInt != 0) {}
    try
    {
      ConstraintLayoutStates localConstraintLayoutStates = new ConstraintLayoutStates(getContext(), this, paramInt);
      mConstraintLayoutSpec = localConstraintLayoutStates;
      return;
    }
    catch (Resources.NotFoundException localNotFoundException)
    {
      for (;;) {}
    }
    mConstraintLayoutSpec = null;
    return;
    mConstraintLayoutSpec = null;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramInt3 = getChildCount();
    paramBoolean = isInEditMode();
    paramInt2 = 0;
    paramInt1 = 0;
    while (paramInt1 < paramInt3)
    {
      View localView = getChildAt(paramInt1);
      LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
      ConstraintWidget localConstraintWidget = widget;
      if (((localView.getVisibility() != 8) || (isGuideline) || (isHelper) || (isVirtualGroup) || (paramBoolean)) && (!isInPlaceholder))
      {
        paramInt4 = localConstraintWidget.getDrawX();
        int i = localConstraintWidget.getDrawY();
        int j = localConstraintWidget.getWidth() + paramInt4;
        int k = localConstraintWidget.getHeight() + i;
        localView.layout(paramInt4, i, j, k);
        if ((localView instanceof Placeholder))
        {
          localView = ((Placeholder)localView).getContent();
          if (localView != null)
          {
            localView.setVisibility(0);
            localView.layout(paramInt4, i, j, k);
          }
        }
      }
      paramInt1 += 1;
    }
    paramInt3 = mConstraintHelpers.size();
    if (paramInt3 > 0)
    {
      paramInt1 = paramInt2;
      while (paramInt1 < paramInt3)
      {
        ((ConstraintHelper)mConstraintHelpers.get(paramInt1)).updatePostLayout(this);
        paramInt1 += 1;
      }
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    System.currentTimeMillis();
    int m = View.MeasureSpec.getMode(paramInt1);
    int n = View.MeasureSpec.getSize(paramInt1);
    int i2 = View.MeasureSpec.getMode(paramInt2);
    int i3 = View.MeasureSpec.getSize(paramInt2);
    int j = getPaddingLeft();
    int k = getPaddingTop();
    mLayoutWidget.setX(j);
    mLayoutWidget.setY(k);
    mLayoutWidget.setMaxWidth(mMaxWidth);
    mLayoutWidget.setMaxHeight(mMaxHeight);
    Object localObject;
    if (Build.VERSION.SDK_INT >= 17)
    {
      localObject = mLayoutWidget;
      boolean bool;
      if (getLayoutDirection() == 1) {
        bool = true;
      } else {
        bool = false;
      }
      ((ConstraintWidgetContainer)localObject).setRtl(bool);
    }
    setSelfDimensionBehaviour(paramInt1, paramInt2);
    int i7 = mLayoutWidget.getWidth();
    int i8 = mLayoutWidget.getHeight();
    if (mDirtyHierarchy)
    {
      mDirtyHierarchy = false;
      updateHierarchy();
      i = 1;
    }
    else
    {
      i = 0;
    }
    int i1;
    if ((mOptimizationLevel & 0x8) == 8) {
      i1 = 1;
    } else {
      i1 = 0;
    }
    if (i1 != 0)
    {
      mLayoutWidget.preOptimize();
      mLayoutWidget.optimizeForDimensions(i7, i8);
      internalMeasureDimensions(paramInt1, paramInt2);
    }
    else
    {
      internalMeasureChildren(paramInt1, paramInt2);
    }
    updatePostMeasures();
    if ((getChildCount() > 0) && (i != 0)) {
      Analyzer.determineGroups(mLayoutWidget);
    }
    if (mLayoutWidget.mGroupsWrapOptimized)
    {
      if ((mLayoutWidget.mHorizontalWrapOptimized) && (m == Integer.MIN_VALUE))
      {
        if (mLayoutWidget.mWrapFixedWidth < n) {
          mLayoutWidget.setWidth(mLayoutWidget.mWrapFixedWidth);
        }
        mLayoutWidget.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
      }
      if ((mLayoutWidget.mVerticalWrapOptimized) && (i2 == Integer.MIN_VALUE))
      {
        if (mLayoutWidget.mWrapFixedHeight < i3) {
          mLayoutWidget.setHeight(mLayoutWidget.mWrapFixedHeight);
        }
        mLayoutWidget.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
      }
    }
    if (mLayoutWidget.mSkipSolver) {
      if ((m == 1073741824) && (i2 == 1073741824))
      {
        if (mLastMeasureWidth != n) {
          Analyzer.setPosition(mLayoutWidget.mWidgetGroups, 0, n);
        }
        if (mLastMeasureHeight != i3) {
          Analyzer.setPosition(mLayoutWidget.mWidgetGroups, 1, i3);
        }
      }
      else if ((mLayoutWidget.mHorizontalWrapOptimized) && (mLayoutWidget.mVerticalWrapOptimized))
      {
        if (mLayoutWidget.mWrapFixedWidth > n) {
          Analyzer.setPosition(mLayoutWidget.mWidgetGroups, 0, n);
        }
        if (mLayoutWidget.mWrapFixedHeight > i3) {
          Analyzer.setPosition(mLayoutWidget.mWidgetGroups, 1, i3);
        }
      }
    }
    if (getChildCount() > 0) {
      solveLinearSystem("First pass");
    }
    int i9 = mVariableDimensionsWidgets.size();
    int i6 = k + getPaddingBottom();
    int i10 = j + getPaddingRight();
    if (i9 > 0)
    {
      if (mLayoutWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
        i2 = 1;
      } else {
        i2 = 0;
      }
      if (mLayoutWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
        i3 = 1;
      } else {
        i3 = 0;
      }
      j = Math.max(mLayoutWidget.getWidth(), mMinWidth);
      i = Math.max(mLayoutWidget.getHeight(), mMinHeight);
      int i4 = 0;
      m = 0;
      k = 0;
      View localView;
      while (i4 < i9)
      {
        localObject = (ConstraintWidget)mVariableDimensionsWidgets.get(i4);
        localView = (View)((ConstraintWidget)localObject).getCompanionWidget();
        int i5;
        LayoutParams localLayoutParams;
        if (localView == null)
        {
          i5 = i;
        }
        else
        {
          localLayoutParams = (LayoutParams)localView.getLayoutParams();
          if ((isHelper) && (!isVirtualGroup)) {}
          while (isGuideline)
          {
            i5 = i;
            break;
          }
          if (localView.getVisibility() == 8)
          {
            i5 = i;
          }
          else
          {
            if ((i1 == 0) || (!((ConstraintWidget)localObject).getResolutionWidth().isResolved()) || (!((ConstraintWidget)localObject).getResolutionHeight().isResolved())) {
              break label777;
            }
            i5 = i;
          }
        }
        for (;;)
        {
          break;
          label777:
          if ((width == -2) && (horizontalDimensionFixed)) {
            n = ViewGroup.getChildMeasureSpec(paramInt1, i10, width);
          } else {
            n = View.MeasureSpec.makeMeasureSpec(((ConstraintWidget)localObject).getWidth(), 1073741824);
          }
          if ((height == -2) && (verticalDimensionFixed)) {
            i5 = ViewGroup.getChildMeasureSpec(paramInt2, i6, height);
          } else {
            i5 = View.MeasureSpec.makeMeasureSpec(((ConstraintWidget)localObject).getHeight(), 1073741824);
          }
          localView.measure(n, i5);
          if (mMetrics != null)
          {
            Metrics localMetrics = mMetrics;
            additionalMeasures += 1L;
          }
          int i11 = localView.getMeasuredWidth();
          i5 = localView.getMeasuredHeight();
          n = j;
          if (i11 != ((ConstraintWidget)localObject).getWidth())
          {
            ((ConstraintWidget)localObject).setWidth(i11);
            if (i1 != 0) {
              ((ConstraintWidget)localObject).getResolutionWidth().resolve(i11);
            }
            n = j;
            if (i2 != 0)
            {
              n = j;
              if (((ConstraintWidget)localObject).getRight() > j) {
                n = Math.max(j, ((ConstraintWidget)localObject).getRight() + ((ConstraintWidget)localObject).getAnchor(ConstraintAnchor.Type.RIGHT).getMargin());
              }
            }
            m = 1;
          }
          j = i;
          if (i5 != ((ConstraintWidget)localObject).getHeight())
          {
            ((ConstraintWidget)localObject).setHeight(i5);
            if (i1 != 0) {
              ((ConstraintWidget)localObject).getResolutionHeight().resolve(i5);
            }
            j = i;
            if (i3 != 0)
            {
              j = i;
              if (((ConstraintWidget)localObject).getBottom() > i) {
                j = Math.max(i, ((ConstraintWidget)localObject).getBottom() + ((ConstraintWidget)localObject).getAnchor(ConstraintAnchor.Type.BOTTOM).getMargin());
              }
            }
            m = 1;
          }
          i = m;
          if (needsBaseline)
          {
            i5 = localView.getBaseline();
            i = m;
            if (i5 != -1)
            {
              i = m;
              if (i5 != ((ConstraintWidget)localObject).getBaselineDistance())
              {
                ((ConstraintWidget)localObject).setBaselineDistance(i5);
                i = 1;
              }
            }
          }
          if (Build.VERSION.SDK_INT >= 11)
          {
            k = View.combineMeasuredStates(k, localView.getMeasuredState());
            i5 = j;
            j = n;
            m = i;
          }
          else
          {
            i5 = j;
            j = n;
            m = i;
          }
        }
        i4 += 1;
        i = i5;
      }
      if (m != 0)
      {
        mLayoutWidget.setWidth(i7);
        mLayoutWidget.setHeight(i8);
        if (i1 != 0) {
          mLayoutWidget.solveGraph();
        }
        solveLinearSystem("2nd pass");
        if (mLayoutWidget.getWidth() < j)
        {
          mLayoutWidget.setWidth(j);
          j = 1;
        }
        else
        {
          j = 0;
        }
        if (mLayoutWidget.getHeight() < i)
        {
          mLayoutWidget.setHeight(i);
          j = 1;
        }
        if (j != 0) {
          solveLinearSystem("3rd pass");
        }
      }
      j = 0;
      for (;;)
      {
        i = k;
        if (j >= i9) {
          break;
        }
        localObject = (ConstraintWidget)mVariableDimensionsWidgets.get(j);
        localView = (View)((ConstraintWidget)localObject).getCompanionWidget();
        if (localView != null)
        {
          while (((localView.getMeasuredWidth() == ((ConstraintWidget)localObject).getWidth()) && (localView.getMeasuredHeight() == ((ConstraintWidget)localObject).getHeight())) || (((ConstraintWidget)localObject).getVisibility() == 8)) {}
          localView.measure(View.MeasureSpec.makeMeasureSpec(((ConstraintWidget)localObject).getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(((ConstraintWidget)localObject).getHeight(), 1073741824));
          if (mMetrics != null)
          {
            localObject = mMetrics;
            additionalMeasures += 1L;
          }
        }
        j += 1;
      }
    }
    int i = 0;
    j = mLayoutWidget.getWidth() + i10;
    k = mLayoutWidget.getHeight() + i6;
    if (Build.VERSION.SDK_INT >= 11)
    {
      paramInt1 = View.resolveSizeAndState(j, paramInt1, i);
      i = View.resolveSizeAndState(k, paramInt2, i << 16);
      j = Math.min(mMaxWidth, paramInt1 & 0xFFFFFF);
      paramInt2 = j;
      i = Math.min(mMaxHeight, i & 0xFFFFFF);
      paramInt1 = i;
      if (mLayoutWidget.isWidthMeasuredTooSmall()) {
        paramInt2 = j | 0x1000000;
      }
      if (mLayoutWidget.isHeightMeasuredTooSmall()) {
        paramInt1 = i | 0x1000000;
      }
      setMeasuredDimension(paramInt2, paramInt1);
      mLastMeasureWidth = paramInt2;
      mLastMeasureHeight = paramInt1;
      return;
    }
    setMeasuredDimension(j, k);
    mLastMeasureWidth = j;
    mLastMeasureHeight = k;
  }
  
  public void onViewAdded(View paramView)
  {
    if (Build.VERSION.SDK_INT >= 14) {
      super.onViewAdded(paramView);
    }
    Object localObject = getViewWidget(paramView);
    if (((paramView instanceof Guideline)) && (!(localObject instanceof android.support.constraint.solver.widgets.Guideline)))
    {
      localObject = (LayoutParams)paramView.getLayoutParams();
      widget = new android.support.constraint.solver.widgets.Guideline();
      isGuideline = true;
      ((android.support.constraint.solver.widgets.Guideline)widget).setOrientation(orientation);
    }
    if ((paramView instanceof ConstraintHelper))
    {
      localObject = (ConstraintHelper)paramView;
      ((ConstraintHelper)localObject).validateParams();
      getLayoutParamsisHelper = true;
      if (!mConstraintHelpers.contains(localObject)) {
        mConstraintHelpers.add(localObject);
      }
    }
    mChildrenByIds.put(paramView.getId(), paramView);
    mDirtyHierarchy = true;
  }
  
  public void onViewRemoved(View paramView)
  {
    if (Build.VERSION.SDK_INT >= 14) {
      super.onViewRemoved(paramView);
    }
    mChildrenByIds.remove(paramView.getId());
    ConstraintWidget localConstraintWidget = getViewWidget(paramView);
    mLayoutWidget.remove(localConstraintWidget);
    mConstraintHelpers.remove(paramView);
    mVariableDimensionsWidgets.remove(localConstraintWidget);
    mDirtyHierarchy = true;
  }
  
  protected void parseLayoutDescription(int paramInt)
  {
    mConstraintLayoutSpec = new ConstraintLayoutStates(getContext(), this, paramInt);
  }
  
  public void removeView(View paramView)
  {
    super.removeView(paramView);
    if (Build.VERSION.SDK_INT < 14) {
      onViewRemoved(paramView);
    }
  }
  
  public void requestLayout()
  {
    super.requestLayout();
    mDirtyHierarchy = true;
    mLastMeasureWidth = -1;
    mLastMeasureHeight = -1;
    mLastMeasureWidthSize = -1;
    mLastMeasureHeightSize = -1;
    mLastMeasureWidthMode = 0;
    mLastMeasureHeightMode = 0;
  }
  
  public void setConstraintSet(ConstraintSet paramConstraintSet)
  {
    mConstraintSet = paramConstraintSet;
  }
  
  public void setDesignInformation(int paramInt, Object paramObject1, Object paramObject2)
  {
    if ((paramInt == 0) && ((paramObject1 instanceof String)) && ((paramObject2 instanceof Integer)))
    {
      if (mDesignIds == null) {
        mDesignIds = new HashMap();
      }
      String str = (String)paramObject1;
      paramInt = str.indexOf("/");
      paramObject1 = str;
      if (paramInt != -1) {
        paramObject1 = str.substring(paramInt + 1);
      }
      paramInt = ((Integer)paramObject2).intValue();
      mDesignIds.put(paramObject1, Integer.valueOf(paramInt));
    }
  }
  
  public void setId(int paramInt)
  {
    mChildrenByIds.remove(getId());
    super.setId(paramInt);
    mChildrenByIds.put(getId(), this);
  }
  
  public void setMaxHeight(int paramInt)
  {
    if (paramInt == mMaxHeight) {
      return;
    }
    mMaxHeight = paramInt;
    requestLayout();
  }
  
  public void setMaxWidth(int paramInt)
  {
    if (paramInt == mMaxWidth) {
      return;
    }
    mMaxWidth = paramInt;
    requestLayout();
  }
  
  public void setMinHeight(int paramInt)
  {
    if (paramInt == mMinHeight) {
      return;
    }
    mMinHeight = paramInt;
    requestLayout();
  }
  
  public void setMinWidth(int paramInt)
  {
    if (paramInt == mMinWidth) {
      return;
    }
    mMinWidth = paramInt;
    requestLayout();
  }
  
  public void setOnConstraintsChanged(ConstraintsChangedListener paramConstraintsChangedListener)
  {
    mConstraintsChangedListener = paramConstraintsChangedListener;
    if (mConstraintLayoutSpec != null) {
      mConstraintLayoutSpec.setOnConstraintsChanged(paramConstraintsChangedListener);
    }
  }
  
  public void setOptimizationLevel(int paramInt)
  {
    mLayoutWidget.setOptimizationLevel(paramInt);
  }
  
  public void setState(int paramInt1, int paramInt2, int paramInt3)
  {
    if (mConstraintLayoutSpec != null) {
      mConstraintLayoutSpec.updateConstraints(paramInt1, paramInt2, paramInt3);
    }
  }
  
  public boolean shouldDelayChildPressedState()
  {
    return false;
  }
  
  protected void solveLinearSystem(String paramString)
  {
    if (mMetrics != null)
    {
      paramString = mMetrics;
      resolutions += 1L;
    }
    mLayoutWidget.layout();
  }
  
  public static class LayoutParams
    extends ViewGroup.MarginLayoutParams
  {
    public static final int BASELINE = 5;
    public static final int BOTTOM = 4;
    public static final int CHAIN_PACKED = 2;
    public static final int CHAIN_SPREAD = 0;
    public static final int CHAIN_SPREAD_INSIDE = 1;
    public static final int HORIZONTAL = 0;
    public static final int LEFT = 1;
    public static final int MATCH_CONSTRAINT = 0;
    public static final int MATCH_CONSTRAINT_PERCENT = 2;
    public static final int MATCH_CONSTRAINT_SPREAD = 0;
    public static final int MATCH_CONSTRAINT_WRAP = 1;
    public static final int PARENT_ID = 0;
    public static final int PRIORITY_MIDHIGH = 7;
    public static final int RIGHT = 2;
    public static final int SORT_MENU_ITEM = 3;
    public static final int START = 6;
    public static final int UNSET = -1;
    public static final int VERTICAL = 1;
    public int baselineToBaseline = -1;
    public int bottomToBottom = -1;
    public int bottomToTop = -1;
    public float circleAngle = 0.0F;
    public int circleConstraint = -1;
    public int circleRadius = 0;
    public boolean constrainedHeight = false;
    public boolean constrainedWidth = false;
    public String dimensionRatio = null;
    int dimensionRatioSide = 1;
    float dimensionRatioValue = 0.0F;
    public int editorAbsoluteX = -1;
    public int editorAbsoluteY = -1;
    public int endToEnd = -1;
    public int endToStart = -1;
    public int goneBottomMargin = -1;
    public int goneEndMargin = -1;
    public int goneLeftMargin = -1;
    public int goneRightMargin = -1;
    public int goneStartMargin = -1;
    public int goneTopMargin = -1;
    public int guideBegin = -1;
    public int guideEnd = -1;
    public float guidePercent = -1.0F;
    public boolean helped = false;
    public float horizontalBias = 0.5F;
    public int horizontalChainStyle = 0;
    boolean horizontalDimensionFixed = true;
    public float horizontalWeight = -1.0F;
    boolean isGuideline = false;
    boolean isHelper = false;
    boolean isInPlaceholder = false;
    boolean isVirtualGroup = false;
    public int leftToLeft = -1;
    public int leftToRight = -1;
    public int matchConstraintDefaultHeight = 0;
    public int matchConstraintDefaultWidth = 0;
    public int matchConstraintMaxHeight = 0;
    public int matchConstraintMaxWidth = 0;
    public int matchConstraintMinHeight = 0;
    public int matchConstraintMinWidth = 0;
    public float matchConstraintPercentHeight = 1.0F;
    public float matchConstraintPercentWidth = 1.0F;
    boolean needsBaseline = false;
    public int orientation = -1;
    int resolveGoneLeftMargin = -1;
    int resolveGoneRightMargin = -1;
    int resolvedGuideBegin;
    int resolvedGuideEnd;
    float resolvedGuidePercent;
    float resolvedHorizontalBias = 0.5F;
    int resolvedLeftToLeft = -1;
    int resolvedLeftToRight = -1;
    int resolvedRightToLeft = -1;
    int resolvedRightToRight = -1;
    public int rightToLeft = -1;
    public int rightToRight = -1;
    public int startToEnd = -1;
    public int startToStart = -1;
    public int topToBottom = -1;
    public int topToTop = -1;
    public float verticalBias = 0.5F;
    public int verticalChainStyle = 0;
    boolean verticalDimensionFixed = true;
    public float verticalWeight = -1.0F;
    ConstraintWidget widget = new ConstraintWidget();
    
    public LayoutParams(int paramInt1, int paramInt2)
    {
      super(paramInt2);
    }
    
    public LayoutParams(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
      paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ConstraintLayout_Layout);
      int k = paramContext.getIndexCount();
      int i = 0;
      while (i < k)
      {
        int j = paramContext.getIndex(i);
        String str;
        switch (Table.subdomains.get(j))
        {
        default: 
          break;
        case 43: 
          break;
        case 50: 
          editorAbsoluteY = paramContext.getDimensionPixelOffset(j, editorAbsoluteY);
          break;
        case 49: 
          editorAbsoluteX = paramContext.getDimensionPixelOffset(j, editorAbsoluteX);
          break;
        case 48: 
          verticalChainStyle = paramContext.getInt(j, 0);
          break;
        case 47: 
          horizontalChainStyle = paramContext.getInt(j, 0);
          break;
        case 46: 
          verticalWeight = paramContext.getFloat(j, verticalWeight);
          break;
        case 45: 
          horizontalWeight = paramContext.getFloat(j, horizontalWeight);
          break;
        case 44: 
          dimensionRatio = paramContext.getString(j);
          dimensionRatioValue = NaN.0F;
          dimensionRatioSide = -1;
          if (dimensionRatio != null)
          {
            m = dimensionRatio.length();
            j = dimensionRatio.indexOf(',');
            if ((j > 0) && (j < m - 1))
            {
              paramAttributeSet = dimensionRatio.substring(0, j);
              if (paramAttributeSet.equalsIgnoreCase("W")) {
                dimensionRatioSide = 0;
              } else if (paramAttributeSet.equalsIgnoreCase("H")) {
                dimensionRatioSide = 1;
              }
              j += 1;
            }
            else
            {
              j = 0;
            }
            int n = dimensionRatio.indexOf(':');
            if ((n >= 0) && (n < m - 1))
            {
              paramAttributeSet = dimensionRatio.substring(j, n);
              str = dimensionRatio.substring(n + 1);
              if ((paramAttributeSet.length() <= 0) || (str.length() <= 0)) {
                break;
              }
            }
          }
          break;
        }
        try
        {
          f1 = Float.parseFloat(paramAttributeSet);
          float f2 = Float.parseFloat(str);
          if ((f1 <= 0.0F) || (f2 <= 0.0F)) {
            break label2281;
          }
          if (dimensionRatioSide == 1)
          {
            f1 = f2 / f1;
            f1 = Math.abs(f1);
            dimensionRatioValue = f1;
          }
          else
          {
            f1 /= f2;
            f1 = Math.abs(f1);
            dimensionRatioValue = f1;
          }
        }
        catch (NumberFormatException paramAttributeSet)
        {
          float f1;
          for (;;) {}
        }
        paramAttributeSet = dimensionRatio.substring(j);
        if (paramAttributeSet.length() > 0) {}
        try
        {
          f1 = Float.parseFloat(paramAttributeSet);
          dimensionRatioValue = f1;
        }
        catch (NumberFormatException paramAttributeSet)
        {
          for (;;) {}
        }
        matchConstraintPercentHeight = Math.max(0.0F, paramContext.getFloat(j, matchConstraintPercentHeight));
        break label2281;
        int m = matchConstraintMaxHeight;
        try
        {
          m = paramContext.getDimensionPixelSize(j, m);
          matchConstraintMaxHeight = m;
        }
        catch (Exception paramAttributeSet)
        {
          for (;;) {}
        }
        if (paramContext.getInt(j, matchConstraintMaxHeight) == -2)
        {
          matchConstraintMaxHeight = -2;
          break label2281;
          m = matchConstraintMinHeight;
        }
        try
        {
          m = paramContext.getDimensionPixelSize(j, m);
          matchConstraintMinHeight = m;
        }
        catch (Exception paramAttributeSet)
        {
          for (;;) {}
        }
        if (paramContext.getInt(j, matchConstraintMinHeight) == -2)
        {
          matchConstraintMinHeight = -2;
          break label2281;
          matchConstraintPercentWidth = Math.max(0.0F, paramContext.getFloat(j, matchConstraintPercentWidth));
          break label2281;
          m = matchConstraintMaxWidth;
        }
        try
        {
          m = paramContext.getDimensionPixelSize(j, m);
          matchConstraintMaxWidth = m;
        }
        catch (Exception paramAttributeSet)
        {
          label2281:
          for (;;) {}
        }
        if (paramContext.getInt(j, matchConstraintMaxWidth) == -2)
        {
          matchConstraintMaxWidth = -2;
          break label2281;
          m = matchConstraintMinWidth;
        }
        try
        {
          m = paramContext.getDimensionPixelSize(j, m);
          matchConstraintMinWidth = m;
        }
        catch (Exception paramAttributeSet)
        {
          for (;;) {}
        }
        if (paramContext.getInt(j, matchConstraintMinWidth) == -2)
        {
          matchConstraintMinWidth = -2;
          break label2281;
          matchConstraintDefaultHeight = paramContext.getInt(j, 0);
          if (matchConstraintDefaultHeight == 1)
          {
            Log.e("ConstraintLayout", "layout_constraintHeight_default=\"wrap\" is deprecated.\nUse layout_height=\"WRAP_CONTENT\" and layout_constrainedHeight=\"true\" instead.");
            break label2281;
            matchConstraintDefaultWidth = paramContext.getInt(j, 0);
            if (matchConstraintDefaultWidth == 1)
            {
              Log.e("ConstraintLayout", "layout_constraintWidth_default=\"wrap\" is deprecated.\nUse layout_width=\"WRAP_CONTENT\" and layout_constrainedWidth=\"true\" instead.");
              break label2281;
              verticalBias = paramContext.getFloat(j, verticalBias);
              break label2281;
              horizontalBias = paramContext.getFloat(j, horizontalBias);
              break label2281;
              constrainedHeight = paramContext.getBoolean(j, constrainedHeight);
              break label2281;
              constrainedWidth = paramContext.getBoolean(j, constrainedWidth);
              break label2281;
              goneEndMargin = paramContext.getDimensionPixelSize(j, goneEndMargin);
              break label2281;
              goneStartMargin = paramContext.getDimensionPixelSize(j, goneStartMargin);
              break label2281;
              goneBottomMargin = paramContext.getDimensionPixelSize(j, goneBottomMargin);
              break label2281;
              goneRightMargin = paramContext.getDimensionPixelSize(j, goneRightMargin);
              break label2281;
              goneTopMargin = paramContext.getDimensionPixelSize(j, goneTopMargin);
              break label2281;
              goneLeftMargin = paramContext.getDimensionPixelSize(j, goneLeftMargin);
              break label2281;
              endToEnd = paramContext.getResourceId(j, endToEnd);
              if (endToEnd == -1)
              {
                endToEnd = paramContext.getInt(j, -1);
                break label2281;
                endToStart = paramContext.getResourceId(j, endToStart);
                if (endToStart == -1)
                {
                  endToStart = paramContext.getInt(j, -1);
                  break label2281;
                  startToStart = paramContext.getResourceId(j, startToStart);
                  if (startToStart == -1)
                  {
                    startToStart = paramContext.getInt(j, -1);
                    break label2281;
                    startToEnd = paramContext.getResourceId(j, startToEnd);
                    if (startToEnd == -1)
                    {
                      startToEnd = paramContext.getInt(j, -1);
                      break label2281;
                      baselineToBaseline = paramContext.getResourceId(j, baselineToBaseline);
                      if (baselineToBaseline == -1)
                      {
                        baselineToBaseline = paramContext.getInt(j, -1);
                        break label2281;
                        bottomToBottom = paramContext.getResourceId(j, bottomToBottom);
                        if (bottomToBottom == -1)
                        {
                          bottomToBottom = paramContext.getInt(j, -1);
                          break label2281;
                          bottomToTop = paramContext.getResourceId(j, bottomToTop);
                          if (bottomToTop == -1)
                          {
                            bottomToTop = paramContext.getInt(j, -1);
                            break label2281;
                            topToBottom = paramContext.getResourceId(j, topToBottom);
                            if (topToBottom == -1)
                            {
                              topToBottom = paramContext.getInt(j, -1);
                              break label2281;
                              topToTop = paramContext.getResourceId(j, topToTop);
                              if (topToTop == -1)
                              {
                                topToTop = paramContext.getInt(j, -1);
                                break label2281;
                                rightToRight = paramContext.getResourceId(j, rightToRight);
                                if (rightToRight == -1)
                                {
                                  rightToRight = paramContext.getInt(j, -1);
                                  break label2281;
                                  rightToLeft = paramContext.getResourceId(j, rightToLeft);
                                  if (rightToLeft == -1)
                                  {
                                    rightToLeft = paramContext.getInt(j, -1);
                                    break label2281;
                                    leftToRight = paramContext.getResourceId(j, leftToRight);
                                    if (leftToRight == -1)
                                    {
                                      leftToRight = paramContext.getInt(j, -1);
                                      break label2281;
                                      leftToLeft = paramContext.getResourceId(j, leftToLeft);
                                      if (leftToLeft == -1)
                                      {
                                        leftToLeft = paramContext.getInt(j, -1);
                                        break label2281;
                                        guidePercent = paramContext.getFloat(j, guidePercent);
                                        break label2281;
                                        guideEnd = paramContext.getDimensionPixelOffset(j, guideEnd);
                                        break label2281;
                                        guideBegin = paramContext.getDimensionPixelOffset(j, guideBegin);
                                        break label2281;
                                        circleAngle = (paramContext.getFloat(j, circleAngle) % 360.0F);
                                        if (circleAngle < 0.0F)
                                        {
                                          circleAngle = ((360.0F - circleAngle) % 360.0F);
                                          break label2281;
                                          circleRadius = paramContext.getDimensionPixelSize(j, circleRadius);
                                          break label2281;
                                          circleConstraint = paramContext.getResourceId(j, circleConstraint);
                                          if (circleConstraint == -1)
                                          {
                                            circleConstraint = paramContext.getInt(j, -1);
                                            break label2281;
                                            orientation = paramContext.getInt(j, orientation);
                                          }
                                        }
                                      }
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
        i += 1;
      }
      paramContext.recycle();
      validate();
    }
    
    public LayoutParams(LayoutParams paramLayoutParams)
    {
      super();
      guideBegin = guideBegin;
      guideEnd = guideEnd;
      guidePercent = guidePercent;
      leftToLeft = leftToLeft;
      leftToRight = leftToRight;
      rightToLeft = rightToLeft;
      rightToRight = rightToRight;
      topToTop = topToTop;
      topToBottom = topToBottom;
      bottomToTop = bottomToTop;
      bottomToBottom = bottomToBottom;
      baselineToBaseline = baselineToBaseline;
      circleConstraint = circleConstraint;
      circleRadius = circleRadius;
      circleAngle = circleAngle;
      startToEnd = startToEnd;
      startToStart = startToStart;
      endToStart = endToStart;
      endToEnd = endToEnd;
      goneLeftMargin = goneLeftMargin;
      goneTopMargin = goneTopMargin;
      goneRightMargin = goneRightMargin;
      goneBottomMargin = goneBottomMargin;
      goneStartMargin = goneStartMargin;
      goneEndMargin = goneEndMargin;
      horizontalBias = horizontalBias;
      verticalBias = verticalBias;
      dimensionRatio = dimensionRatio;
      dimensionRatioValue = dimensionRatioValue;
      dimensionRatioSide = dimensionRatioSide;
      horizontalWeight = horizontalWeight;
      verticalWeight = verticalWeight;
      horizontalChainStyle = horizontalChainStyle;
      verticalChainStyle = verticalChainStyle;
      constrainedWidth = constrainedWidth;
      constrainedHeight = constrainedHeight;
      matchConstraintDefaultWidth = matchConstraintDefaultWidth;
      matchConstraintDefaultHeight = matchConstraintDefaultHeight;
      matchConstraintMinWidth = matchConstraintMinWidth;
      matchConstraintMaxWidth = matchConstraintMaxWidth;
      matchConstraintMinHeight = matchConstraintMinHeight;
      matchConstraintMaxHeight = matchConstraintMaxHeight;
      matchConstraintPercentWidth = matchConstraintPercentWidth;
      matchConstraintPercentHeight = matchConstraintPercentHeight;
      editorAbsoluteX = editorAbsoluteX;
      editorAbsoluteY = editorAbsoluteY;
      orientation = orientation;
      horizontalDimensionFixed = horizontalDimensionFixed;
      verticalDimensionFixed = verticalDimensionFixed;
      needsBaseline = needsBaseline;
      isGuideline = isGuideline;
      resolvedLeftToLeft = resolvedLeftToLeft;
      resolvedLeftToRight = resolvedLeftToRight;
      resolvedRightToLeft = resolvedRightToLeft;
      resolvedRightToRight = resolvedRightToRight;
      resolveGoneLeftMargin = resolveGoneLeftMargin;
      resolveGoneRightMargin = resolveGoneRightMargin;
      resolvedHorizontalBias = resolvedHorizontalBias;
      widget = widget;
    }
    
    public LayoutParams(ViewGroup.LayoutParams paramLayoutParams)
    {
      super();
    }
    
    public ConstraintWidget getConstraintWidget()
    {
      return widget;
    }
    
    public int getLayoutDirection()
    {
      throw new Error("Unresolved compilation error: Method <android.support.constraint.ConstraintLayout$LayoutParams: int getLayoutDirection()> does not exist!");
    }
    
    public int getMarginEnd()
    {
      throw new Error("Unresolved compilation error: Method <android.support.constraint.ConstraintLayout$LayoutParams: int getMarginEnd()> does not exist!");
    }
    
    public int getMarginStart()
    {
      throw new Error("Unresolved compilation error: Method <android.support.constraint.ConstraintLayout$LayoutParams: int getMarginStart()> does not exist!");
    }
    
    public void reset()
    {
      if (widget != null) {
        widget.reset();
      }
    }
    
    public void resolveLayoutDirection(int paramInt)
    {
      int j = leftMargin;
      int k = rightMargin;
      super.resolveLayoutDirection(paramInt);
      resolvedRightToLeft = -1;
      resolvedRightToRight = -1;
      resolvedLeftToLeft = -1;
      resolvedLeftToRight = -1;
      resolveGoneLeftMargin = -1;
      resolveGoneRightMargin = -1;
      resolveGoneLeftMargin = goneLeftMargin;
      resolveGoneRightMargin = goneRightMargin;
      resolvedHorizontalBias = horizontalBias;
      resolvedGuideBegin = guideBegin;
      resolvedGuideEnd = guideEnd;
      resolvedGuidePercent = guidePercent;
      paramInt = getLayoutDirection();
      int i = 0;
      if (1 == paramInt) {
        paramInt = 1;
      } else {
        paramInt = 0;
      }
      if (paramInt != 0)
      {
        if (startToEnd != -1) {
          resolvedRightToLeft = startToEnd;
        }
        for (;;)
        {
          paramInt = 1;
          break;
          paramInt = i;
          if (startToStart == -1) {
            break;
          }
          resolvedRightToRight = startToStart;
        }
        if (endToStart != -1)
        {
          resolvedLeftToRight = endToStart;
          paramInt = 1;
        }
        if (endToEnd != -1)
        {
          resolvedLeftToLeft = endToEnd;
          paramInt = 1;
        }
        if (goneStartMargin != -1) {
          resolveGoneRightMargin = goneStartMargin;
        }
        if (goneEndMargin != -1) {
          resolveGoneLeftMargin = goneEndMargin;
        }
        if (paramInt != 0) {
          resolvedHorizontalBias = (1.0F - horizontalBias);
        }
        if ((isGuideline) && (orientation == 1)) {
          if (guidePercent != -1.0F)
          {
            resolvedGuidePercent = (1.0F - guidePercent);
            resolvedGuideBegin = -1;
            resolvedGuideEnd = -1;
          }
          else if (guideBegin != -1)
          {
            resolvedGuideEnd = guideBegin;
            resolvedGuideBegin = -1;
            resolvedGuidePercent = -1.0F;
          }
          else if (guideEnd != -1)
          {
            resolvedGuideBegin = guideEnd;
            resolvedGuideEnd = -1;
            resolvedGuidePercent = -1.0F;
          }
        }
      }
      else
      {
        if (startToEnd != -1) {
          resolvedLeftToRight = startToEnd;
        }
        if (startToStart != -1) {
          resolvedLeftToLeft = startToStart;
        }
        if (endToStart != -1) {
          resolvedRightToLeft = endToStart;
        }
        if (endToEnd != -1) {
          resolvedRightToRight = endToEnd;
        }
        if (goneStartMargin != -1) {
          resolveGoneLeftMargin = goneStartMargin;
        }
        if (goneEndMargin != -1) {
          resolveGoneRightMargin = goneEndMargin;
        }
      }
      if ((endToStart == -1) && (endToEnd == -1) && (startToStart == -1) && (startToEnd == -1))
      {
        if (rightToLeft != -1)
        {
          resolvedRightToLeft = rightToLeft;
          if ((rightMargin <= 0) && (k > 0)) {
            rightMargin = k;
          }
        }
        else if (rightToRight != -1)
        {
          resolvedRightToRight = rightToRight;
          if ((rightMargin <= 0) && (k > 0)) {
            rightMargin = k;
          }
        }
        if (leftToLeft != -1)
        {
          resolvedLeftToLeft = leftToLeft;
          if ((leftMargin <= 0) && (j > 0)) {
            leftMargin = j;
          }
        }
        else if (leftToRight != -1)
        {
          resolvedLeftToRight = leftToRight;
          if ((leftMargin <= 0) && (j > 0)) {
            leftMargin = j;
          }
        }
      }
    }
    
    public void setMarginEnd(int paramInt)
    {
      throw new Error("Unresolved compilation error: Method <android.support.constraint.ConstraintLayout$LayoutParams: void setMarginEnd(int)> does not exist!");
    }
    
    public void setMarginStart(int paramInt)
    {
      throw new Error("Unresolved compilation error: Method <android.support.constraint.ConstraintLayout$LayoutParams: void setMarginStart(int)> does not exist!");
    }
    
    public void validate()
    {
      isGuideline = false;
      horizontalDimensionFixed = true;
      verticalDimensionFixed = true;
      if ((width == -2) && (constrainedWidth))
      {
        horizontalDimensionFixed = false;
        matchConstraintDefaultWidth = 1;
      }
      if ((height == -2) && (constrainedHeight))
      {
        verticalDimensionFixed = false;
        matchConstraintDefaultHeight = 1;
      }
      if ((width == 0) || (width == -1))
      {
        horizontalDimensionFixed = false;
        if ((width == 0) && (matchConstraintDefaultWidth == 1))
        {
          width = -2;
          constrainedWidth = true;
        }
      }
      if ((height == 0) || (height == -1))
      {
        verticalDimensionFixed = false;
        if ((height == 0) && (matchConstraintDefaultHeight == 1))
        {
          height = -2;
          constrainedHeight = true;
        }
      }
      if ((guidePercent != -1.0F) || (guideBegin != -1) || (guideEnd != -1))
      {
        isGuideline = true;
        horizontalDimensionFixed = true;
        verticalDimensionFixed = true;
        if (!(widget instanceof android.support.constraint.solver.widgets.Guideline)) {
          widget = new android.support.constraint.solver.widgets.Guideline();
        }
        ((android.support.constraint.solver.widgets.Guideline)widget).setOrientation(orientation);
      }
    }
    
    private static class Table
    {
      public static final int ANDROID_ORIENTATION = 1;
      public static final int LAYOUT_CONSTRAINED_HEIGHT = 28;
      public static final int LAYOUT_CONSTRAINED_WIDTH = 27;
      public static final int LAYOUT_CONSTRAINT_BASELINE_CREATOR = 43;
      public static final int LAYOUT_CONSTRAINT_BASELINE_TO_BASELINE_OF = 16;
      public static final int LAYOUT_CONSTRAINT_BOTTOM_CREATOR = 42;
      public static final int LAYOUT_CONSTRAINT_BOTTOM_TO_BOTTOM_OF = 15;
      public static final int LAYOUT_CONSTRAINT_BOTTOM_TO_TOP_OF = 14;
      public static final int LAYOUT_CONSTRAINT_CIRCLE = 2;
      public static final int LAYOUT_CONSTRAINT_CIRCLE_ANGLE = 4;
      public static final int LAYOUT_CONSTRAINT_CIRCLE_RADIUS = 3;
      public static final int LAYOUT_CONSTRAINT_DIMENSION_RATIO = 44;
      public static final int LAYOUT_CONSTRAINT_END_TO_END_OF = 20;
      public static final int LAYOUT_CONSTRAINT_END_TO_START_OF = 19;
      public static final int LAYOUT_CONSTRAINT_GUIDE_BEGIN = 5;
      public static final int LAYOUT_CONSTRAINT_GUIDE_END = 6;
      public static final int LAYOUT_CONSTRAINT_GUIDE_PERCENT = 7;
      public static final int LAYOUT_CONSTRAINT_HEIGHT_DEFAULT = 32;
      public static final int LAYOUT_CONSTRAINT_HEIGHT_MAX = 37;
      public static final int LAYOUT_CONSTRAINT_HEIGHT_MIN = 36;
      public static final int LAYOUT_CONSTRAINT_HEIGHT_PERCENT = 38;
      public static final int LAYOUT_CONSTRAINT_HORIZONTAL_BIAS = 29;
      public static final int LAYOUT_CONSTRAINT_HORIZONTAL_CHAINSTYLE = 47;
      public static final int LAYOUT_CONSTRAINT_HORIZONTAL_WEIGHT = 45;
      public static final int LAYOUT_CONSTRAINT_LEFT_CREATOR = 39;
      public static final int LAYOUT_CONSTRAINT_LEFT_TO_LEFT_OF = 8;
      public static final int LAYOUT_CONSTRAINT_LEFT_TO_RIGHT_OF = 9;
      public static final int LAYOUT_CONSTRAINT_RIGHT_CREATOR = 41;
      public static final int LAYOUT_CONSTRAINT_RIGHT_TO_LEFT_OF = 10;
      public static final int LAYOUT_CONSTRAINT_RIGHT_TO_RIGHT_OF = 11;
      public static final int LAYOUT_CONSTRAINT_START_TO_END_OF = 17;
      public static final int LAYOUT_CONSTRAINT_START_TO_START_OF = 18;
      public static final int LAYOUT_CONSTRAINT_TOP_CREATOR = 40;
      public static final int LAYOUT_CONSTRAINT_TOP_TO_BOTTOM_OF = 13;
      public static final int LAYOUT_CONSTRAINT_TOP_TO_TOP_OF = 12;
      public static final int LAYOUT_CONSTRAINT_VERTICAL_BIAS = 30;
      public static final int LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE = 48;
      public static final int LAYOUT_CONSTRAINT_VERTICAL_WEIGHT = 46;
      public static final int LAYOUT_CONSTRAINT_WIDTH_DEFAULT = 31;
      public static final int LAYOUT_CONSTRAINT_WIDTH_MAX = 34;
      public static final int LAYOUT_CONSTRAINT_WIDTH_MIN = 33;
      public static final int LAYOUT_CONSTRAINT_WIDTH_PERCENT = 35;
      public static final int LAYOUT_EDITOR_ABSOLUTEX = 49;
      public static final int LAYOUT_EDITOR_ABSOLUTEY = 50;
      public static final int LAYOUT_GONE_MARGIN_BOTTOM = 24;
      public static final int LAYOUT_GONE_MARGIN_END = 26;
      public static final int LAYOUT_GONE_MARGIN_LEFT = 21;
      public static final int LAYOUT_GONE_MARGIN_RIGHT = 23;
      public static final int LAYOUT_GONE_MARGIN_START = 25;
      public static final int LAYOUT_GONE_MARGIN_TOP = 22;
      public static final int UNUSED = 0;
      public static final SparseIntArray subdomains = new SparseIntArray();
      
      static
      {
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_constraintLeft_toLeftOf, 8);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_constraintLeft_toRightOf, 9);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_constraintRight_toLeftOf, 10);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_constraintRight_toRightOf, 11);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_constraintTop_toTopOf, 12);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_constraintTop_toBottomOf, 13);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_constraintBottom_toTopOf, 14);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_constraintBottom_toBottomOf, 15);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_constraintBaseline_toBaselineOf, 16);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_constraintCircle, 2);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_constraintCircleRadius, 3);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_constraintCircleAngle, 4);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_editor_absoluteX, 49);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_editor_absoluteY, 50);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_constraintGuide_begin, 5);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_constraintGuide_end, 6);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_constraintGuide_percent, 7);
        subdomains.append(R.styleable.ConstraintLayout_Layout_android_orientation, 1);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_constraintStart_toEndOf, 17);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_constraintStart_toStartOf, 18);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_constraintEnd_toStartOf, 19);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_constraintEnd_toEndOf, 20);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginLeft, 21);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginTop, 22);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginRight, 23);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginBottom, 24);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginStart, 25);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginEnd, 26);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_bias, 29);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_constraintVertical_bias, 30);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_constraintDimensionRatio, 44);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_weight, 45);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_constraintVertical_weight, 46);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_chainStyle, 47);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_constraintVertical_chainStyle, 48);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_constrainedWidth, 27);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_constrainedHeight, 28);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_default, 31);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_default, 32);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_min, 33);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_max, 34);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_percent, 35);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_min, 36);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_max, 37);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_percent, 38);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_constraintLeft_creator, 39);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_constraintTop_creator, 40);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_constraintRight_creator, 41);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_constraintBottom_creator, 42);
        subdomains.append(R.styleable.ConstraintLayout_Layout_layout_constraintBaseline_creator, 43);
      }
      
      private Table() {}
    }
  }
}
