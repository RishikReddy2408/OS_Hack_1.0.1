package android.support.constraint.solver;

import java.util.ArrayList;

public class Metrics
{
  public long additionalMeasures;
  public long barrierConnectionResolved;
  public long centerConnectionResolved;
  public long chainConnectionResolved;
  public long constraints;
  public long errors;
  public long extravariables;
  public long fsync;
  public long fullySolved;
  public long graphOptimizer;
  public long iterations;
  public long lastTableSize;
  public long matchConnectionResolved;
  public long maxRows;
  public long maxTableSize;
  public long maxVariables;
  public long measures;
  public long minimize;
  public long minimizeGoal;
  public long nonresolvedWidgets;
  public long oldresolvedWidgets;
  public long optimize;
  public long pivots;
  public ArrayList<String> problematicLayouts = new ArrayList();
  public long resolutions;
  public long resolvedWidgets;
  public long simpleconstraints;
  public long slackvariables;
  public long tableSizeIncrease;
  public long variables;
  
  public Metrics() {}
  
  public void reset()
  {
    measures = 0L;
    additionalMeasures = 0L;
    resolutions = 0L;
    tableSizeIncrease = 0L;
    maxTableSize = 0L;
    lastTableSize = 0L;
    maxVariables = 0L;
    maxRows = 0L;
    minimize = 0L;
    minimizeGoal = 0L;
    constraints = 0L;
    simpleconstraints = 0L;
    optimize = 0L;
    iterations = 0L;
    pivots = 0L;
    fsync = 0L;
    variables = 0L;
    errors = 0L;
    slackvariables = 0L;
    extravariables = 0L;
    fullySolved = 0L;
    graphOptimizer = 0L;
    resolvedWidgets = 0L;
    oldresolvedWidgets = 0L;
    nonresolvedWidgets = 0L;
    centerConnectionResolved = 0L;
    matchConnectionResolved = 0L;
    chainConnectionResolved = 0L;
    barrierConnectionResolved = 0L;
    problematicLayouts.clear();
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("\n*** Metrics ***\nmeasures: ");
    localStringBuilder.append(measures);
    localStringBuilder.append("\nadditionalMeasures: ");
    localStringBuilder.append(additionalMeasures);
    localStringBuilder.append("\nresolutions passes: ");
    localStringBuilder.append(resolutions);
    localStringBuilder.append("\ntable increases: ");
    localStringBuilder.append(tableSizeIncrease);
    localStringBuilder.append("\nmaxTableSize: ");
    localStringBuilder.append(maxTableSize);
    localStringBuilder.append("\nmaxVariables: ");
    localStringBuilder.append(maxVariables);
    localStringBuilder.append("\nmaxRows: ");
    localStringBuilder.append(maxRows);
    localStringBuilder.append("\n\nminimize: ");
    localStringBuilder.append(minimize);
    localStringBuilder.append("\nminimizeGoal: ");
    localStringBuilder.append(minimizeGoal);
    localStringBuilder.append("\nconstraints: ");
    localStringBuilder.append(constraints);
    localStringBuilder.append("\nsimpleconstraints: ");
    localStringBuilder.append(simpleconstraints);
    localStringBuilder.append("\noptimize: ");
    localStringBuilder.append(optimize);
    localStringBuilder.append("\niterations: ");
    localStringBuilder.append(iterations);
    localStringBuilder.append("\npivots: ");
    localStringBuilder.append(pivots);
    localStringBuilder.append("\nbfs: ");
    localStringBuilder.append(fsync);
    localStringBuilder.append("\nvariables: ");
    localStringBuilder.append(variables);
    localStringBuilder.append("\nerrors: ");
    localStringBuilder.append(errors);
    localStringBuilder.append("\nslackvariables: ");
    localStringBuilder.append(slackvariables);
    localStringBuilder.append("\nextravariables: ");
    localStringBuilder.append(extravariables);
    localStringBuilder.append("\nfullySolved: ");
    localStringBuilder.append(fullySolved);
    localStringBuilder.append("\ngraphOptimizer: ");
    localStringBuilder.append(graphOptimizer);
    localStringBuilder.append("\nresolvedWidgets: ");
    localStringBuilder.append(resolvedWidgets);
    localStringBuilder.append("\noldresolvedWidgets: ");
    localStringBuilder.append(oldresolvedWidgets);
    localStringBuilder.append("\nnonresolvedWidgets: ");
    localStringBuilder.append(nonresolvedWidgets);
    localStringBuilder.append("\ncenterConnectionResolved: ");
    localStringBuilder.append(centerConnectionResolved);
    localStringBuilder.append("\nmatchConnectionResolved: ");
    localStringBuilder.append(matchConnectionResolved);
    localStringBuilder.append("\nchainConnectionResolved: ");
    localStringBuilder.append(chainConnectionResolved);
    localStringBuilder.append("\nbarrierConnectionResolved: ");
    localStringBuilder.append(barrierConnectionResolved);
    localStringBuilder.append("\nproblematicsLayouts: ");
    localStringBuilder.append(problematicLayouts);
    localStringBuilder.append("\n");
    return localStringBuilder.toString();
  }
}
