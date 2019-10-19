package android.support.constraint.solver.widgets;

public class ResolutionDimension
  extends ResolutionNode
{
  float value = 0.0F;
  
  public ResolutionDimension() {}
  
  public void remove()
  {
    state = 2;
  }
  
  public void reset()
  {
    super.reset();
    value = 0.0F;
  }
  
  public void resolve(int paramInt)
  {
    if ((state == 0) || (value != paramInt))
    {
      value = paramInt;
      if (state == 1) {
        invalidate();
      }
      didResolve();
    }
  }
}
