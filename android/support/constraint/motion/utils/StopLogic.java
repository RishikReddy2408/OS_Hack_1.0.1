package android.support.constraint.motion.utils;

import android.view.animation.Interpolator;

public class StopLogic
  implements Interpolator
{
  private boolean mBackwards = false;
  private int mNumberOfStages;
  private float mStage1Duration;
  private float mStage1EndPosition;
  private float mStage1Velocity;
  private float mStage2Duration;
  private float mStage2EndPosition;
  private float mStage2Velocity;
  private float mStage3Duration;
  private float mStage3EndPosition;
  private float mStage3Velocity;
  private float mStartPosition;
  
  public StopLogic() {}
  
  private float calcY(float paramFloat)
  {
    if (paramFloat <= mStage1Duration) {
      return mStage1Velocity * paramFloat + (mStage2Velocity - mStage1Velocity) * paramFloat * paramFloat / (mStage1Duration * 2.0F);
    }
    if (mNumberOfStages == 1) {
      return mStage1EndPosition;
    }
    paramFloat -= mStage1Duration;
    if (paramFloat < mStage2Duration) {
      return mStage1EndPosition + mStage2Velocity * paramFloat + (mStage3Velocity - mStage2Velocity) * paramFloat * paramFloat / (mStage2Duration * 2.0F);
    }
    if (mNumberOfStages == 2) {
      return mStage2EndPosition;
    }
    paramFloat -= mStage2Duration;
    if (paramFloat < mStage3Duration) {
      return mStage2EndPosition + mStage3Velocity * paramFloat - mStage3Velocity * paramFloat * paramFloat / (mStage3Duration * 2.0F);
    }
    return mStage3EndPosition;
  }
  
  private void setup(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5)
  {
    mStage1Velocity = paramFloat1;
    float f1 = paramFloat1 / paramFloat3;
    float f2 = f1 * paramFloat1 / 2.0F;
    if (paramFloat1 < 0.0F)
    {
      paramFloat5 = (float)Math.sqrt((paramFloat2 - -paramFloat1 / paramFloat3 * paramFloat1 / 2.0F) * paramFloat3);
      if (paramFloat5 < paramFloat4)
      {
        mNumberOfStages = 2;
        mStage1Velocity = paramFloat1;
        mStage2Velocity = paramFloat5;
        mStage3Velocity = 0.0F;
        mStage1Duration = ((paramFloat5 - paramFloat1) / paramFloat3);
        mStage2Duration = (paramFloat5 / paramFloat3);
        mStage1EndPosition = ((paramFloat1 + paramFloat5) * mStage1Duration / 2.0F);
        mStage2EndPosition = paramFloat2;
        mStage3EndPosition = paramFloat2;
        return;
      }
      mNumberOfStages = 3;
      mStage1Velocity = paramFloat1;
      mStage2Velocity = paramFloat4;
      mStage3Velocity = paramFloat4;
      mStage1Duration = ((paramFloat4 - paramFloat1) / paramFloat3);
      mStage3Duration = (paramFloat4 / paramFloat3);
      paramFloat1 = (paramFloat1 + paramFloat4) * mStage1Duration / 2.0F;
      paramFloat3 = mStage3Duration * paramFloat4 / 2.0F;
      mStage2Duration = ((paramFloat2 - paramFloat1 - paramFloat3) / paramFloat4);
      mStage1EndPosition = paramFloat1;
      mStage2EndPosition = (paramFloat2 - paramFloat3);
      mStage3EndPosition = paramFloat2;
      return;
    }
    if (f2 >= paramFloat2)
    {
      paramFloat3 = 2.0F * paramFloat2 / paramFloat1;
      mNumberOfStages = 1;
      mStage1Velocity = paramFloat1;
      mStage2Velocity = 0.0F;
      mStage1EndPosition = paramFloat2;
      mStage1Duration = paramFloat3;
      return;
    }
    f2 = paramFloat2 - f2;
    float f3 = f2 / paramFloat1;
    if (f3 + f1 < paramFloat5)
    {
      mNumberOfStages = 2;
      mStage1Velocity = paramFloat1;
      mStage2Velocity = paramFloat1;
      mStage3Velocity = 0.0F;
      mStage1EndPosition = f2;
      mStage2EndPosition = paramFloat2;
      mStage1Duration = f3;
      mStage2Duration = f1;
      return;
    }
    paramFloat5 = (float)Math.sqrt(paramFloat3 * paramFloat2 + paramFloat1 * paramFloat1 / 2.0F);
    f1 = (paramFloat5 - paramFloat1) / paramFloat3;
    mStage1Duration = f1;
    f2 = paramFloat5 / paramFloat3;
    mStage2Duration = f2;
    if (paramFloat5 < paramFloat4)
    {
      mNumberOfStages = 2;
      mStage1Velocity = paramFloat1;
      mStage2Velocity = paramFloat5;
      mStage3Velocity = 0.0F;
      mStage1Duration = f1;
      mStage2Duration = f2;
      mStage1EndPosition = ((paramFloat1 + paramFloat5) * mStage1Duration / 2.0F);
      mStage2EndPosition = paramFloat2;
      return;
    }
    mNumberOfStages = 3;
    mStage1Velocity = paramFloat1;
    mStage2Velocity = paramFloat4;
    mStage3Velocity = paramFloat4;
    mStage1Duration = ((paramFloat4 - paramFloat1) / paramFloat3);
    mStage3Duration = (paramFloat4 / paramFloat3);
    paramFloat1 = (paramFloat1 + paramFloat4) * mStage1Duration / 2.0F;
    paramFloat3 = mStage3Duration * paramFloat4 / 2.0F;
    mStage2Duration = ((paramFloat2 - paramFloat1 - paramFloat3) / paramFloat4);
    mStage1EndPosition = paramFloat1;
    mStage2EndPosition = (paramFloat2 - paramFloat3);
    mStage3EndPosition = paramFloat2;
  }
  
  public void config(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6)
  {
    mStartPosition = paramFloat1;
    boolean bool;
    if (paramFloat1 > paramFloat2) {
      bool = true;
    } else {
      bool = false;
    }
    mBackwards = bool;
    if (mBackwards)
    {
      setup(-paramFloat3, paramFloat1 - paramFloat2, paramFloat5, paramFloat6, paramFloat4);
      return;
    }
    setup(paramFloat3, paramFloat2 - paramFloat1, paramFloat5, paramFloat6, paramFloat4);
  }
  
  public float getInterpolation(float paramFloat)
  {
    paramFloat = calcY(paramFloat);
    if (mBackwards) {
      return mStartPosition - paramFloat;
    }
    return mStartPosition + paramFloat;
  }
  
  public float getVelocity(float paramFloat)
  {
    if (paramFloat <= mStage1Duration) {
      return mStage1Velocity + (mStage2Velocity - mStage1Velocity) * paramFloat / mStage1Duration;
    }
    if (mNumberOfStages == 1) {
      return 0.0F;
    }
    paramFloat -= mStage1Duration;
    if (paramFloat < mStage2Duration) {
      return mStage2Velocity + (mStage3Velocity - mStage2Velocity) * paramFloat / mStage2Duration;
    }
    if (mNumberOfStages == 2) {
      return mStage2EndPosition;
    }
    paramFloat -= mStage2Duration;
    if (paramFloat < mStage3Duration) {
      return mStage3Velocity - mStage3Velocity * paramFloat / mStage3Duration;
    }
    return mStage3EndPosition;
  }
}
