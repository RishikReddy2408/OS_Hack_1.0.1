package android.support.constraint.solver.widgets;

import java.util.ArrayList;

public class Snapshot
{
  private ArrayList<Connection> mConnections = new ArrayList();
  private int mHeight;
  private int mWidth;
  private int mX;
  private int mY;
  
  public Snapshot(ConstraintWidget paramConstraintWidget)
  {
    mX = paramConstraintWidget.getX();
    mY = paramConstraintWidget.getY();
    mWidth = paramConstraintWidget.getWidth();
    mHeight = paramConstraintWidget.getHeight();
    paramConstraintWidget = paramConstraintWidget.getAnchors();
    int j = paramConstraintWidget.size();
    int i = 0;
    while (i < j)
    {
      ConstraintAnchor localConstraintAnchor = (ConstraintAnchor)paramConstraintWidget.get(i);
      mConnections.add(new Connection(localConstraintAnchor));
      i += 1;
    }
  }
  
  public void applyTo(ConstraintWidget paramConstraintWidget)
  {
    paramConstraintWidget.setX(mX);
    paramConstraintWidget.setY(mY);
    paramConstraintWidget.setWidth(mWidth);
    paramConstraintWidget.setHeight(mHeight);
    int j = mConnections.size();
    int i = 0;
    while (i < j)
    {
      ((Connection)mConnections.get(i)).applyTo(paramConstraintWidget);
      i += 1;
    }
  }
  
  public void updateFrom(ConstraintWidget paramConstraintWidget)
  {
    mX = paramConstraintWidget.getX();
    mY = paramConstraintWidget.getY();
    mWidth = paramConstraintWidget.getWidth();
    mHeight = paramConstraintWidget.getHeight();
    int j = mConnections.size();
    int i = 0;
    while (i < j)
    {
      ((Connection)mConnections.get(i)).updateFrom(paramConstraintWidget);
      i += 1;
    }
  }
  
  static class Connection
  {
    private ConstraintAnchor mAnchor;
    private int mCreator;
    private int mMargin;
    private ConstraintAnchor.Strength mStrengh;
    private ConstraintAnchor mTarget;
    
    public Connection(ConstraintAnchor paramConstraintAnchor)
    {
      mAnchor = paramConstraintAnchor;
      mTarget = paramConstraintAnchor.getTarget();
      mMargin = paramConstraintAnchor.getMargin();
      mStrengh = paramConstraintAnchor.getStrength();
      mCreator = paramConstraintAnchor.getConnectionCreator();
    }
    
    public void applyTo(ConstraintWidget paramConstraintWidget)
    {
      paramConstraintWidget.getAnchor(mAnchor.getType()).connect(mTarget, mMargin, mStrengh, mCreator);
    }
    
    public void updateFrom(ConstraintWidget paramConstraintWidget)
    {
      mAnchor = paramConstraintWidget.getAnchor(mAnchor.getType());
      if (mAnchor != null)
      {
        mTarget = mAnchor.getTarget();
        mMargin = mAnchor.getMargin();
        mStrengh = mAnchor.getStrength();
        mCreator = mAnchor.getConnectionCreator();
        return;
      }
      mTarget = null;
      mMargin = 0;
      mStrengh = ConstraintAnchor.Strength.STRONG;
      mCreator = 0;
    }
  }
}
