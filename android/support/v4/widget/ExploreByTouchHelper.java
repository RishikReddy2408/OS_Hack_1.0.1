package android.support.v4.widget;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewParentCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityRecord;
import java.util.ArrayList;
import java.util.List;

public abstract class ExploreByTouchHelper
  extends AccessibilityDelegateCompat
{
  private static final String DEFAULT_CLASS_NAME = "android.view.View";
  public static final int HOST_ID = -1;
  public static final int INVALID_ID = Integer.MIN_VALUE;
  private static final Rect INVALID_PARENT_BOUNDS = new Rect(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
  private static final FocusStrategy.BoundsAdapter<AccessibilityNodeInfoCompat> NODE_ADAPTER = new FocusStrategy.BoundsAdapter()
  {
    public void obtainBounds(AccessibilityNodeInfoCompat paramAnonymousAccessibilityNodeInfoCompat, Rect paramAnonymousRect)
    {
      paramAnonymousAccessibilityNodeInfoCompat.getBoundsInParent(paramAnonymousRect);
    }
  };
  private static final FocusStrategy.CollectionAdapter<SparseArrayCompat<AccessibilityNodeInfoCompat>, AccessibilityNodeInfoCompat> SPARSE_VALUES_ADAPTER = new FocusStrategy.CollectionAdapter()
  {
    public AccessibilityNodeInfoCompat get(SparseArrayCompat paramAnonymousSparseArrayCompat, int paramAnonymousInt)
    {
      return (AccessibilityNodeInfoCompat)paramAnonymousSparseArrayCompat.valueAt(paramAnonymousInt);
    }
    
    public int size(SparseArrayCompat paramAnonymousSparseArrayCompat)
    {
      return paramAnonymousSparseArrayCompat.size();
    }
  };
  private int mAccessibilityFocusedVirtualViewId = Integer.MIN_VALUE;
  private final View mHost;
  private int mHoveredVirtualViewId = Integer.MIN_VALUE;
  private int mKeyboardFocusedVirtualViewId = Integer.MIN_VALUE;
  private final AccessibilityManager mManager;
  private MyNodeProvider mNodeProvider;
  private final int[] mTempGlobalRect = new int[2];
  private final Rect mTempParentRect = new Rect();
  private final Rect mTempScreenRect = new Rect();
  private final Rect mTempVisibleRect = new Rect();
  
  public ExploreByTouchHelper(View paramView)
  {
    if (paramView != null)
    {
      mHost = paramView;
      mManager = ((AccessibilityManager)paramView.getContext().getSystemService("accessibility"));
      paramView.setFocusable(true);
      if (ViewCompat.getImportantForAccessibility(paramView) == 0) {
        ViewCompat.setImportantForAccessibility(paramView, 1);
      }
    }
    else
    {
      throw new IllegalArgumentException("View may not be null");
    }
  }
  
  private boolean clearAccessibilityFocus(int paramInt)
  {
    if (mAccessibilityFocusedVirtualViewId == paramInt)
    {
      mAccessibilityFocusedVirtualViewId = Integer.MIN_VALUE;
      mHost.invalidate();
      sendEventForVirtualView(paramInt, 65536);
      return true;
    }
    return false;
  }
  
  private boolean clickKeyboardFocusedVirtualView()
  {
    return (mKeyboardFocusedVirtualViewId != Integer.MIN_VALUE) && (onPerformActionForVirtualView(mKeyboardFocusedVirtualViewId, 16, null));
  }
  
  private AccessibilityEvent createEvent(int paramInt1, int paramInt2)
  {
    if (paramInt1 != -1) {
      return createEventForChild(paramInt1, paramInt2);
    }
    return createEventForHost(paramInt2);
  }
  
  private AccessibilityEvent createEventForChild(int paramInt1, int paramInt2)
  {
    AccessibilityEvent localAccessibilityEvent = AccessibilityEvent.obtain(paramInt2);
    AccessibilityNodeInfoCompat localAccessibilityNodeInfoCompat = obtainAccessibilityNodeInfo(paramInt1);
    localAccessibilityEvent.getText().add(localAccessibilityNodeInfoCompat.getText());
    localAccessibilityEvent.setContentDescription(localAccessibilityNodeInfoCompat.getContentDescription());
    localAccessibilityEvent.setScrollable(localAccessibilityNodeInfoCompat.isScrollable());
    localAccessibilityEvent.setPassword(localAccessibilityNodeInfoCompat.isPassword());
    localAccessibilityEvent.setEnabled(localAccessibilityNodeInfoCompat.isEnabled());
    localAccessibilityEvent.setChecked(localAccessibilityNodeInfoCompat.isChecked());
    onPopulateEventForVirtualView(paramInt1, localAccessibilityEvent);
    if ((localAccessibilityEvent.getText().isEmpty()) && (localAccessibilityEvent.getContentDescription() == null)) {
      throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
    }
    localAccessibilityEvent.setClassName(localAccessibilityNodeInfoCompat.getClassName());
    AccessibilityRecordCompat.setSource(localAccessibilityEvent, mHost, paramInt1);
    localAccessibilityEvent.setPackageName(mHost.getContext().getPackageName());
    return localAccessibilityEvent;
  }
  
  private AccessibilityEvent createEventForHost(int paramInt)
  {
    AccessibilityEvent localAccessibilityEvent = AccessibilityEvent.obtain(paramInt);
    mHost.onInitializeAccessibilityEvent(localAccessibilityEvent);
    return localAccessibilityEvent;
  }
  
  private AccessibilityNodeInfoCompat createNodeForChild(int paramInt)
  {
    AccessibilityNodeInfoCompat localAccessibilityNodeInfoCompat1 = AccessibilityNodeInfoCompat.obtain();
    localAccessibilityNodeInfoCompat1.setEnabled(true);
    localAccessibilityNodeInfoCompat1.setFocusable(true);
    localAccessibilityNodeInfoCompat1.setClassName("android.view.View");
    localAccessibilityNodeInfoCompat1.setBoundsInParent(INVALID_PARENT_BOUNDS);
    localAccessibilityNodeInfoCompat1.setBoundsInScreen(INVALID_PARENT_BOUNDS);
    localAccessibilityNodeInfoCompat1.setParent(mHost);
    onPopulateNodeForVirtualView(paramInt, localAccessibilityNodeInfoCompat1);
    if ((localAccessibilityNodeInfoCompat1.getText() == null) && (localAccessibilityNodeInfoCompat1.getContentDescription() == null)) {
      throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
    }
    localAccessibilityNodeInfoCompat1.getBoundsInParent(mTempParentRect);
    if (!mTempParentRect.equals(INVALID_PARENT_BOUNDS))
    {
      int i = localAccessibilityNodeInfoCompat1.getActions();
      if ((i & 0x40) == 0)
      {
        if ((i & 0x80) == 0)
        {
          localAccessibilityNodeInfoCompat1.setPackageName(mHost.getContext().getPackageName());
          localAccessibilityNodeInfoCompat1.setSource(mHost, paramInt);
          if (mAccessibilityFocusedVirtualViewId == paramInt)
          {
            localAccessibilityNodeInfoCompat1.setAccessibilityFocused(true);
            localAccessibilityNodeInfoCompat1.addAction(128);
          }
          else
          {
            localAccessibilityNodeInfoCompat1.setAccessibilityFocused(false);
            localAccessibilityNodeInfoCompat1.addAction(64);
          }
          boolean bool;
          if (mKeyboardFocusedVirtualViewId == paramInt) {
            bool = true;
          } else {
            bool = false;
          }
          if (bool) {
            localAccessibilityNodeInfoCompat1.addAction(2);
          } else if (localAccessibilityNodeInfoCompat1.isFocusable()) {
            localAccessibilityNodeInfoCompat1.addAction(1);
          }
          localAccessibilityNodeInfoCompat1.setFocused(bool);
          mHost.getLocationOnScreen(mTempGlobalRect);
          localAccessibilityNodeInfoCompat1.getBoundsInScreen(mTempScreenRect);
          if (mTempScreenRect.equals(INVALID_PARENT_BOUNDS))
          {
            localAccessibilityNodeInfoCompat1.getBoundsInParent(mTempScreenRect);
            if (mParentVirtualDescendantId != -1)
            {
              AccessibilityNodeInfoCompat localAccessibilityNodeInfoCompat2 = AccessibilityNodeInfoCompat.obtain();
              for (paramInt = mParentVirtualDescendantId; paramInt != -1; paramInt = mParentVirtualDescendantId)
              {
                localAccessibilityNodeInfoCompat2.setParent(mHost, -1);
                localAccessibilityNodeInfoCompat2.setBoundsInParent(INVALID_PARENT_BOUNDS);
                onPopulateNodeForVirtualView(paramInt, localAccessibilityNodeInfoCompat2);
                localAccessibilityNodeInfoCompat2.getBoundsInParent(mTempParentRect);
                mTempScreenRect.offset(mTempParentRect.left, mTempParentRect.top);
              }
              localAccessibilityNodeInfoCompat2.recycle();
            }
            mTempScreenRect.offset(mTempGlobalRect[0] - mHost.getScrollX(), mTempGlobalRect[1] - mHost.getScrollY());
          }
          if (mHost.getLocalVisibleRect(mTempVisibleRect))
          {
            mTempVisibleRect.offset(mTempGlobalRect[0] - mHost.getScrollX(), mTempGlobalRect[1] - mHost.getScrollY());
            if (mTempScreenRect.intersect(mTempVisibleRect))
            {
              localAccessibilityNodeInfoCompat1.setBoundsInScreen(mTempScreenRect);
              if (isVisibleToUser(mTempScreenRect))
              {
                localAccessibilityNodeInfoCompat1.setVisibleToUser(true);
                return localAccessibilityNodeInfoCompat1;
              }
            }
          }
        }
        else
        {
          throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
      }
      else {
        throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
      }
    }
    else
    {
      throw new RuntimeException("Callbacks must set parent bounds in populateNodeForVirtualViewId()");
    }
    return localAccessibilityNodeInfoCompat1;
  }
  
  private AccessibilityNodeInfoCompat createNodeForHost()
  {
    AccessibilityNodeInfoCompat localAccessibilityNodeInfoCompat = AccessibilityNodeInfoCompat.obtain(mHost);
    ViewCompat.onInitializeAccessibilityNodeInfo(mHost, localAccessibilityNodeInfoCompat);
    ArrayList localArrayList = new ArrayList();
    getVisibleVirtualViews(localArrayList);
    if ((localAccessibilityNodeInfoCompat.getChildCount() > 0) && (localArrayList.size() > 0)) {
      throw new RuntimeException("Views cannot have both real and virtual children");
    }
    int i = 0;
    int j = localArrayList.size();
    while (i < j)
    {
      localAccessibilityNodeInfoCompat.addChild(mHost, ((Integer)localArrayList.get(i)).intValue());
      i += 1;
    }
    return localAccessibilityNodeInfoCompat;
  }
  
  private SparseArrayCompat getAllNodes()
  {
    ArrayList localArrayList = new ArrayList();
    getVisibleVirtualViews(localArrayList);
    SparseArrayCompat localSparseArrayCompat = new SparseArrayCompat();
    int i = 0;
    while (i < localArrayList.size())
    {
      localSparseArrayCompat.put(i, createNodeForChild(i));
      i += 1;
    }
    return localSparseArrayCompat;
  }
  
  private void getBoundsInParent(int paramInt, Rect paramRect)
  {
    obtainAccessibilityNodeInfo(paramInt).getBoundsInParent(paramRect);
  }
  
  private static Rect guessPreviouslyFocusedRect(View paramView, int paramInt, Rect paramRect)
  {
    int i = paramView.getWidth();
    int j = paramView.getHeight();
    if (paramInt != 17)
    {
      if (paramInt != 33)
      {
        if (paramInt != 66)
        {
          if (paramInt == 130)
          {
            paramRect.set(0, -1, i, -1);
            return paramRect;
          }
          throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
        }
        paramRect.set(-1, 0, -1, j);
        return paramRect;
      }
      paramRect.set(0, j, i, j);
      return paramRect;
    }
    paramRect.set(i, 0, i, j);
    return paramRect;
  }
  
  private boolean isVisibleToUser(Rect paramRect)
  {
    if (paramRect != null)
    {
      if (paramRect.isEmpty()) {
        return false;
      }
      if (mHost.getWindowVisibility() != 0) {
        return false;
      }
      paramRect = mHost.getParent();
      while ((paramRect instanceof View))
      {
        paramRect = (View)paramRect;
        if (paramRect.getAlpha() > 0.0F)
        {
          if (paramRect.getVisibility() != 0) {
            return false;
          }
          paramRect = paramRect.getParent();
        }
        else
        {
          return false;
        }
      }
      if (paramRect != null) {
        return true;
      }
    }
    return false;
  }
  
  private static int keyToDirection(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      break;
    case 20: 
      return 130;
    case 22: 
      return 66;
    case 21: 
      return 17;
    }
    return 33;
  }
  
  private boolean moveFocus(int paramInt, Rect paramRect)
  {
    SparseArrayCompat localSparseArrayCompat = getAllNodes();
    int j = mKeyboardFocusedVirtualViewId;
    int i = Integer.MIN_VALUE;
    if (j == Integer.MIN_VALUE) {}
    for (AccessibilityNodeInfoCompat localAccessibilityNodeInfoCompat = null;; localAccessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat)localSparseArrayCompat.get(j)) {
      break;
    }
    if ((paramInt != 17) && (paramInt != 33) && (paramInt != 66) && (paramInt != 130))
    {
      switch (paramInt)
      {
      default: 
        throw new IllegalArgumentException("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD, FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
      }
      boolean bool;
      if (ViewCompat.getLayoutDirection(mHost) == 1) {
        bool = true;
      } else {
        bool = false;
      }
      paramRect = (AccessibilityNodeInfoCompat)FocusStrategy.findNextFocusInRelativeDirection(localSparseArrayCompat, SPARSE_VALUES_ADAPTER, NODE_ADAPTER, localAccessibilityNodeInfoCompat, paramInt, bool, false);
    }
    else
    {
      Rect localRect = new Rect();
      if (mKeyboardFocusedVirtualViewId != Integer.MIN_VALUE) {
        getBoundsInParent(mKeyboardFocusedVirtualViewId, localRect);
      } else if (paramRect != null) {
        localRect.set(paramRect);
      } else {
        guessPreviouslyFocusedRect(mHost, paramInt, localRect);
      }
      paramRect = (AccessibilityNodeInfoCompat)FocusStrategy.findNextFocusInAbsoluteDirection(localSparseArrayCompat, SPARSE_VALUES_ADAPTER, NODE_ADAPTER, localAccessibilityNodeInfoCompat, localRect, paramInt);
    }
    if (paramRect == null) {
      paramInt = i;
    } else {
      paramInt = localSparseArrayCompat.keyAt(localSparseArrayCompat.indexOfValue(paramRect));
    }
    return requestKeyboardFocusForVirtualView(paramInt);
  }
  
  private boolean performActionForChild(int paramInt1, int paramInt2, Bundle paramBundle)
  {
    if (paramInt2 != 64)
    {
      if (paramInt2 != 128)
      {
        switch (paramInt2)
        {
        default: 
          return onPerformActionForVirtualView(paramInt1, paramInt2, paramBundle);
        case 2: 
          return clearKeyboardFocusForVirtualView(paramInt1);
        }
        return requestKeyboardFocusForVirtualView(paramInt1);
      }
      return clearAccessibilityFocus(paramInt1);
    }
    return requestAccessibilityFocus(paramInt1);
  }
  
  private boolean performActionForHost(int paramInt, Bundle paramBundle)
  {
    return ViewCompat.performAccessibilityAction(mHost, paramInt, paramBundle);
  }
  
  private boolean requestAccessibilityFocus(int paramInt)
  {
    if (mManager.isEnabled())
    {
      if (!mManager.isTouchExplorationEnabled()) {
        return false;
      }
      if (mAccessibilityFocusedVirtualViewId != paramInt)
      {
        if (mAccessibilityFocusedVirtualViewId != Integer.MIN_VALUE) {
          clearAccessibilityFocus(mAccessibilityFocusedVirtualViewId);
        }
        mAccessibilityFocusedVirtualViewId = paramInt;
        mHost.invalidate();
        sendEventForVirtualView(paramInt, 32768);
        return true;
      }
    }
    return false;
  }
  
  private void updateHoveredVirtualView(int paramInt)
  {
    if (mHoveredVirtualViewId == paramInt) {
      return;
    }
    int i = mHoveredVirtualViewId;
    mHoveredVirtualViewId = paramInt;
    sendEventForVirtualView(paramInt, 128);
    sendEventForVirtualView(i, 256);
  }
  
  public final boolean clearKeyboardFocusForVirtualView(int paramInt)
  {
    if (mKeyboardFocusedVirtualViewId != paramInt) {
      return false;
    }
    mKeyboardFocusedVirtualViewId = Integer.MIN_VALUE;
    onVirtualViewKeyboardFocusChanged(paramInt, false);
    sendEventForVirtualView(paramInt, 8);
    return true;
  }
  
  public final boolean dispatchHoverEvent(MotionEvent paramMotionEvent)
  {
    if (mManager.isEnabled())
    {
      if (!mManager.isTouchExplorationEnabled()) {
        return false;
      }
      int i = paramMotionEvent.getAction();
      if (i != 7) {
        switch (i)
        {
        default: 
          return false;
        case 10: 
          if (mAccessibilityFocusedVirtualViewId != Integer.MIN_VALUE)
          {
            updateHoveredVirtualView(Integer.MIN_VALUE);
            return true;
          }
          return false;
        }
      }
      i = getVirtualViewAt(paramMotionEvent.getX(), paramMotionEvent.getY());
      updateHoveredVirtualView(i);
      if (i != Integer.MIN_VALUE) {
        return true;
      }
    }
    return false;
  }
  
  public final boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    int j = paramKeyEvent.getAction();
    int i = 0;
    if (j != 1)
    {
      j = paramKeyEvent.getKeyCode();
      if (j != 61)
      {
        if (j != 66)
        {
          switch (j)
          {
          default: 
            return false;
          case 19: 
          case 20: 
          case 21: 
          case 22: 
            if (!paramKeyEvent.hasNoModifiers()) {
              break;
            }
            j = keyToDirection(j);
            int k = paramKeyEvent.getRepeatCount();
            for (boolean bool = false; (i < k + 1) && (moveFocus(j, null)); bool = true) {
              i += 1;
            }
            return bool;
          }
        }
        else if ((paramKeyEvent.hasNoModifiers()) && (paramKeyEvent.getRepeatCount() == 0))
        {
          clickKeyboardFocusedVirtualView();
          return true;
        }
      }
      else
      {
        if (paramKeyEvent.hasNoModifiers()) {
          return moveFocus(2, null);
        }
        if (paramKeyEvent.hasModifiers(1)) {
          return moveFocus(1, null);
        }
      }
    }
    return false;
  }
  
  public final int getAccessibilityFocusedVirtualViewId()
  {
    return mAccessibilityFocusedVirtualViewId;
  }
  
  public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View paramView)
  {
    if (mNodeProvider == null) {
      mNodeProvider = new MyNodeProvider();
    }
    return mNodeProvider;
  }
  
  public int getFocusedVirtualView()
  {
    return getAccessibilityFocusedVirtualViewId();
  }
  
  public final int getKeyboardFocusedVirtualViewId()
  {
    return mKeyboardFocusedVirtualViewId;
  }
  
  protected abstract int getVirtualViewAt(float paramFloat1, float paramFloat2);
  
  protected abstract void getVisibleVirtualViews(List paramList);
  
  public final void invalidateRoot()
  {
    invalidateVirtualView(-1, 1);
  }
  
  public final void invalidateVirtualView(int paramInt)
  {
    invalidateVirtualView(paramInt, 0);
  }
  
  public final void invalidateVirtualView(int paramInt1, int paramInt2)
  {
    if ((paramInt1 != Integer.MIN_VALUE) && (mManager.isEnabled()))
    {
      ViewParent localViewParent = mHost.getParent();
      if (localViewParent != null)
      {
        AccessibilityEvent localAccessibilityEvent = createEvent(paramInt1, 2048);
        AccessibilityEventCompat.setContentChangeTypes(localAccessibilityEvent, paramInt2);
        ViewParentCompat.requestSendAccessibilityEvent(localViewParent, mHost, localAccessibilityEvent);
      }
    }
  }
  
  AccessibilityNodeInfoCompat obtainAccessibilityNodeInfo(int paramInt)
  {
    if (paramInt == -1) {
      return createNodeForHost();
    }
    return createNodeForChild(paramInt);
  }
  
  public final void onFocusChanged(boolean paramBoolean, int paramInt, Rect paramRect)
  {
    if (mKeyboardFocusedVirtualViewId != Integer.MIN_VALUE) {
      clearKeyboardFocusForVirtualView(mKeyboardFocusedVirtualViewId);
    }
    if (paramBoolean) {
      moveFocus(paramInt, paramRect);
    }
  }
  
  public void onInitializeAccessibilityEvent(View paramView, AccessibilityEvent paramAccessibilityEvent)
  {
    super.onInitializeAccessibilityEvent(paramView, paramAccessibilityEvent);
    onPopulateEventForHost(paramAccessibilityEvent);
  }
  
  public void onInitializeAccessibilityNodeInfo(View paramView, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat)
  {
    super.onInitializeAccessibilityNodeInfo(paramView, paramAccessibilityNodeInfoCompat);
    onPopulateNodeForHost(paramAccessibilityNodeInfoCompat);
  }
  
  protected abstract boolean onPerformActionForVirtualView(int paramInt1, int paramInt2, Bundle paramBundle);
  
  protected void onPopulateEventForHost(AccessibilityEvent paramAccessibilityEvent) {}
  
  protected void onPopulateEventForVirtualView(int paramInt, AccessibilityEvent paramAccessibilityEvent) {}
  
  protected void onPopulateNodeForHost(AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat) {}
  
  protected abstract void onPopulateNodeForVirtualView(int paramInt, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat);
  
  protected void onVirtualViewKeyboardFocusChanged(int paramInt, boolean paramBoolean) {}
  
  boolean performAction(int paramInt1, int paramInt2, Bundle paramBundle)
  {
    if (paramInt1 != -1) {
      return performActionForChild(paramInt1, paramInt2, paramBundle);
    }
    return performActionForHost(paramInt2, paramBundle);
  }
  
  public final boolean requestKeyboardFocusForVirtualView(int paramInt)
  {
    if ((!mHost.isFocused()) && (!mHost.requestFocus())) {
      return false;
    }
    if (mKeyboardFocusedVirtualViewId == paramInt) {
      return false;
    }
    if (mKeyboardFocusedVirtualViewId != Integer.MIN_VALUE) {
      clearKeyboardFocusForVirtualView(mKeyboardFocusedVirtualViewId);
    }
    mKeyboardFocusedVirtualViewId = paramInt;
    onVirtualViewKeyboardFocusChanged(paramInt, true);
    sendEventForVirtualView(paramInt, 8);
    return true;
  }
  
  public final boolean sendEventForVirtualView(int paramInt1, int paramInt2)
  {
    if (paramInt1 != Integer.MIN_VALUE)
    {
      if (!mManager.isEnabled()) {
        return false;
      }
      ViewParent localViewParent = mHost.getParent();
      if (localViewParent == null) {
        return false;
      }
      AccessibilityEvent localAccessibilityEvent = createEvent(paramInt1, paramInt2);
      return ViewParentCompat.requestSendAccessibilityEvent(localViewParent, mHost, localAccessibilityEvent);
    }
    return false;
  }
  
  private class MyNodeProvider
    extends AccessibilityNodeProviderCompat
  {
    MyNodeProvider() {}
    
    public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int paramInt)
    {
      return AccessibilityNodeInfoCompat.obtain(obtainAccessibilityNodeInfo(paramInt));
    }
    
    public AccessibilityNodeInfoCompat findFocus(int paramInt)
    {
      if (paramInt == 2) {
        paramInt = mAccessibilityFocusedVirtualViewId;
      } else {
        paramInt = mKeyboardFocusedVirtualViewId;
      }
      if (paramInt == Integer.MIN_VALUE) {
        return null;
      }
      return createAccessibilityNodeInfo(paramInt);
    }
    
    public boolean performAction(int paramInt1, int paramInt2, Bundle paramBundle)
    {
      return ExploreByTouchHelper.this.performAction(paramInt1, paramInt2, paramBundle);
    }
  }
}
