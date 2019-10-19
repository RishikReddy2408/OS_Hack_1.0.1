package android.arch.lifecycle;

import android.arch.core.internal.FastSafeIterableMap;
import android.arch.core.internal.SafeIterableMap.IteratorWithAdditions;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

public class LifecycleRegistry
  extends Lifecycle
{
  private static final String LOG_TAG = "LifecycleRegistry";
  private int mAddingObserverCounter = 0;
  private boolean mHandlingEvent = false;
  private final WeakReference<LifecycleOwner> mLifecycleOwner;
  private boolean mNewEventOccurred = false;
  private FastSafeIterableMap<LifecycleObserver, ObserverWithState> mObserverMap = new FastSafeIterableMap();
  private ArrayList<Lifecycle.State> mParentStates = new ArrayList();
  private Lifecycle.State mState;
  
  public LifecycleRegistry(@NonNull LifecycleOwner paramLifecycleOwner)
  {
    mLifecycleOwner = new WeakReference(paramLifecycleOwner);
    mState = Lifecycle.State.INITIALIZED;
  }
  
  private void backwardPass(LifecycleOwner paramLifecycleOwner)
  {
    Iterator localIterator = mObserverMap.descendingIterator();
    while ((localIterator.hasNext()) && (!mNewEventOccurred))
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      ObserverWithState localObserverWithState = (ObserverWithState)localEntry.getValue();
      while ((mState.compareTo(mState) > 0) && (!mNewEventOccurred) && (mObserverMap.contains(localEntry.getKey())))
      {
        Lifecycle.Event localEvent = downEvent(mState);
        pushParentState(getStateAfter(localEvent));
        localObserverWithState.dispatchEvent(paramLifecycleOwner, localEvent);
        popParentState();
      }
    }
  }
  
  private Lifecycle.State calculateTargetState(LifecycleObserver paramLifecycleObserver)
  {
    paramLifecycleObserver = mObserverMap.ceil(paramLifecycleObserver);
    Lifecycle.State localState = null;
    if (paramLifecycleObserver != null) {
      paramLifecycleObserver = getValuemState;
    } else {
      paramLifecycleObserver = null;
    }
    if (!mParentStates.isEmpty()) {
      localState = (Lifecycle.State)mParentStates.get(mParentStates.size() - 1);
    }
    return min(min(mState, paramLifecycleObserver), localState);
  }
  
  private static Lifecycle.Event downEvent(Lifecycle.State paramState)
  {
    switch (1.$SwitchMap$android$arch$lifecycle$Lifecycle$State[paramState.ordinal()])
    {
    default: 
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Unexpected state value ");
      localStringBuilder.append(paramState);
      throw new IllegalArgumentException(localStringBuilder.toString());
    case 5: 
      throw new IllegalArgumentException();
    case 4: 
      return Lifecycle.Event.ON_PAUSE;
    case 3: 
      return Lifecycle.Event.ON_STOP;
    case 2: 
      return Lifecycle.Event.ON_DESTROY;
    }
    throw new IllegalArgumentException();
  }
  
  private void forwardPass(LifecycleOwner paramLifecycleOwner)
  {
    SafeIterableMap.IteratorWithAdditions localIteratorWithAdditions = mObserverMap.iteratorWithAdditions();
    while ((localIteratorWithAdditions.hasNext()) && (!mNewEventOccurred))
    {
      Map.Entry localEntry = (Map.Entry)localIteratorWithAdditions.next();
      ObserverWithState localObserverWithState = (ObserverWithState)localEntry.getValue();
      while ((mState.compareTo(mState) < 0) && (!mNewEventOccurred) && (mObserverMap.contains(localEntry.getKey())))
      {
        pushParentState(mState);
        localObserverWithState.dispatchEvent(paramLifecycleOwner, upEvent(mState));
        popParentState();
      }
    }
  }
  
  static Lifecycle.State getStateAfter(Lifecycle.Event paramEvent)
  {
    switch (1.$SwitchMap$android$arch$lifecycle$Lifecycle$Event[paramEvent.ordinal()])
    {
    default: 
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Unexpected event value ");
      localStringBuilder.append(paramEvent);
      throw new IllegalArgumentException(localStringBuilder.toString());
    case 6: 
      return Lifecycle.State.DESTROYED;
    case 5: 
      return Lifecycle.State.RESUMED;
    case 3: 
    case 4: 
      return Lifecycle.State.STARTED;
    }
    return Lifecycle.State.CREATED;
  }
  
  private boolean isSynced()
  {
    if (mObserverMap.size() == 0) {
      return true;
    }
    Lifecycle.State localState1 = mObserverMap.eldest().getValue()).mState;
    Lifecycle.State localState2 = mObserverMap.newest().getValue()).mState;
    return (localState1 == localState2) && (mState == localState2);
  }
  
  static Lifecycle.State min(@NonNull Lifecycle.State paramState1, @Nullable Lifecycle.State paramState2)
  {
    Lifecycle.State localState = paramState1;
    if (paramState2 != null)
    {
      localState = paramState1;
      if (paramState2.compareTo(paramState1) < 0) {
        localState = paramState2;
      }
    }
    return localState;
  }
  
  private void moveToState(Lifecycle.State paramState)
  {
    if (mState == paramState) {
      return;
    }
    mState = paramState;
    if ((!mHandlingEvent) && (mAddingObserverCounter == 0))
    {
      mHandlingEvent = true;
      sync();
      mHandlingEvent = false;
      return;
    }
    mNewEventOccurred = true;
  }
  
  private void popParentState()
  {
    mParentStates.remove(mParentStates.size() - 1);
  }
  
  private void pushParentState(Lifecycle.State paramState)
  {
    mParentStates.add(paramState);
  }
  
  private void sync()
  {
    LifecycleOwner localLifecycleOwner = (LifecycleOwner)mLifecycleOwner.get();
    if (localLifecycleOwner == null)
    {
      Log.w("LifecycleRegistry", "LifecycleOwner is garbage collected, you shouldn't try dispatch new events from it.");
      return;
    }
    while (!isSynced())
    {
      mNewEventOccurred = false;
      if (mState.compareTo(mObserverMap.eldest().getValue()).mState) < 0) {
        backwardPass(localLifecycleOwner);
      }
      Map.Entry localEntry = mObserverMap.newest();
      if ((!mNewEventOccurred) && (localEntry != null) && (mState.compareTo(getValuemState) > 0)) {
        forwardPass(localLifecycleOwner);
      }
    }
    mNewEventOccurred = false;
  }
  
  private static Lifecycle.Event upEvent(Lifecycle.State paramState)
  {
    switch (1.$SwitchMap$android$arch$lifecycle$Lifecycle$State[paramState.ordinal()])
    {
    default: 
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Unexpected state value ");
      localStringBuilder.append(paramState);
      throw new IllegalArgumentException(localStringBuilder.toString());
    case 4: 
      throw new IllegalArgumentException();
    case 3: 
      return Lifecycle.Event.ON_RESUME;
    case 2: 
      return Lifecycle.Event.ON_START;
    }
    return Lifecycle.Event.ON_CREATE;
  }
  
  public void addObserver(@NonNull LifecycleObserver paramLifecycleObserver)
  {
    if (mState == Lifecycle.State.DESTROYED) {
      localState = Lifecycle.State.DESTROYED;
    } else {
      localState = Lifecycle.State.INITIALIZED;
    }
    ObserverWithState localObserverWithState = new ObserverWithState(paramLifecycleObserver, localState);
    if ((ObserverWithState)mObserverMap.putIfAbsent(paramLifecycleObserver, localObserverWithState) != null) {
      return;
    }
    LifecycleOwner localLifecycleOwner = (LifecycleOwner)mLifecycleOwner.get();
    if (localLifecycleOwner == null) {
      return;
    }
    int i;
    if ((mAddingObserverCounter == 0) && (!mHandlingEvent)) {
      i = 0;
    } else {
      i = 1;
    }
    Lifecycle.State localState = calculateTargetState(paramLifecycleObserver);
    mAddingObserverCounter += 1;
    while ((mState.compareTo(localState) < 0) && (mObserverMap.contains(paramLifecycleObserver)))
    {
      pushParentState(mState);
      localObserverWithState.dispatchEvent(localLifecycleOwner, upEvent(mState));
      popParentState();
      localState = calculateTargetState(paramLifecycleObserver);
    }
    if (i == 0) {
      sync();
    }
    mAddingObserverCounter -= 1;
  }
  
  @NonNull
  public Lifecycle.State getCurrentState()
  {
    return mState;
  }
  
  public int getObserverCount()
  {
    return mObserverMap.size();
  }
  
  public void handleLifecycleEvent(@NonNull Lifecycle.Event paramEvent)
  {
    moveToState(getStateAfter(paramEvent));
  }
  
  @MainThread
  public void markState(@NonNull Lifecycle.State paramState)
  {
    moveToState(paramState);
  }
  
  public void removeObserver(@NonNull LifecycleObserver paramLifecycleObserver)
  {
    mObserverMap.remove(paramLifecycleObserver);
  }
  
  static class ObserverWithState
  {
    GenericLifecycleObserver mLifecycleObserver;
    Lifecycle.State mState;
    
    ObserverWithState(LifecycleObserver paramLifecycleObserver, Lifecycle.State paramState)
    {
      mLifecycleObserver = Lifecycling.getCallback(paramLifecycleObserver);
      mState = paramState;
    }
    
    void dispatchEvent(LifecycleOwner paramLifecycleOwner, Lifecycle.Event paramEvent)
    {
      Lifecycle.State localState = LifecycleRegistry.getStateAfter(paramEvent);
      mState = LifecycleRegistry.min(mState, localState);
      mLifecycleObserver.onStateChanged(paramLifecycleOwner, paramEvent);
      mState = localState;
    }
  }
}
