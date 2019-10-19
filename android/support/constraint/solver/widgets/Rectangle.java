package android.support.constraint.solver.widgets;

public class Rectangle
{
  public int height;
  public int width;
  public int x;
  public int y;
  
  public Rectangle() {}
  
  public boolean contains(int paramInt1, int paramInt2)
  {
    return (paramInt1 >= x) && (paramInt1 < x + width) && (paramInt2 >= y) && (paramInt2 < y + height);
  }
  
  public int getCenterX()
  {
    return (x + width) / 2;
  }
  
  public int getCenterY()
  {
    return (y + height) / 2;
  }
  
  void grow(int paramInt1, int paramInt2)
  {
    x -= paramInt1;
    y -= paramInt2;
    width += paramInt1 * 2;
    height += paramInt2 * 2;
  }
  
  boolean intersects(Rectangle paramRectangle)
  {
    return (x >= x) && (x < x + width) && (y >= y) && (y < y + height);
  }
  
  public void setBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    x = paramInt1;
    y = paramInt2;
    width = paramInt3;
    height = paramInt4;
  }
}
