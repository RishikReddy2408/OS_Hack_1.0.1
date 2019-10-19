package android.support.constraint.solver.widgets;

import java.util.HashSet;
import java.util.Iterator;

public class ResolutionNode
{
  public static final int REMOVED = 2;
  public static final int RESOLVED = 1;
  public static final int UNRESOLVED = 0;
  HashSet<ResolutionNode> dependents = new HashSet(2);
  int state = 0;
  
  public ResolutionNode() {}
  
  public void addDependent(ResolutionNode paramResolutionNode)
  {
    dependents.add(paramResolutionNode);
  }
  
  public void didResolve()
  {
    state = 1;
    Iterator localIterator = dependents.iterator();
    while (localIterator.hasNext()) {
      ((ResolutionNode)localIterator.next()).resolve();
    }
  }
  
  public void invalidate()
  {
    state = 0;
    Iterator localIterator = dependents.iterator();
    while (localIterator.hasNext()) {
      ((ResolutionNode)localIterator.next()).invalidate();
    }
  }
  
  public void invalidateAnchors()
  {
    if ((this instanceof ResolutionAnchor)) {
      state = 0;
    }
    Iterator localIterator = dependents.iterator();
    while (localIterator.hasNext()) {
      ((ResolutionNode)localIterator.next()).invalidateAnchors();
    }
  }
  
  public boolean isResolved()
  {
    return state == 1;
  }
  
  public void remove(ResolutionDimension paramResolutionDimension) {}
  
  public void reset()
  {
    state = 0;
    dependents.clear();
  }
  
  public void resolve() {}
}
