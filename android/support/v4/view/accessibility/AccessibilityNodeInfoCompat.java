package android.support.v4.view.accessibility;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.RestrictTo;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction;
import android.view.accessibility.AccessibilityNodeInfo.CollectionInfo;
import android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo;
import android.view.accessibility.AccessibilityNodeInfo.RangeInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class AccessibilityNodeInfoCompat
{
  public static final int ACTION_ACCESSIBILITY_FOCUS = 64;
  public static final String ACTION_ARGUMENT_COLUMN_INT = "android.view.accessibility.action.ARGUMENT_COLUMN_INT";
  public static final String ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN = "ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN";
  public static final String ACTION_ARGUMENT_HTML_ELEMENT_STRING = "ACTION_ARGUMENT_HTML_ELEMENT_STRING";
  public static final String ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT = "ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT";
  public static final String ACTION_ARGUMENT_PROGRESS_VALUE = "android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE";
  public static final String ACTION_ARGUMENT_ROW_INT = "android.view.accessibility.action.ARGUMENT_ROW_INT";
  public static final String ACTION_ARGUMENT_SELECTION_END_INT = "ACTION_ARGUMENT_SELECTION_END_INT";
  public static final String ACTION_ARGUMENT_SELECTION_START_INT = "ACTION_ARGUMENT_SELECTION_START_INT";
  public static final String ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE = "ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE";
  public static final int ACTION_CLEAR_ACCESSIBILITY_FOCUS = 128;
  public static final int ACTION_CLEAR_FOCUS = 2;
  public static final int ACTION_CLEAR_SELECTION = 8;
  public static final int ACTION_CLICK = 16;
  public static final int ACTION_COLLAPSE = 524288;
  public static final int ACTION_COPY = 16384;
  public static final int ACTION_CUT = 65536;
  public static final int ACTION_DISMISS = 1048576;
  public static final int ACTION_EXPAND = 262144;
  public static final int ACTION_FOCUS = 1;
  public static final int ACTION_LONG_CLICK = 32;
  public static final int ACTION_NEXT_AT_MOVEMENT_GRANULARITY = 256;
  public static final int ACTION_NEXT_HTML_ELEMENT = 1024;
  public static final int ACTION_PASTE = 32768;
  public static final int ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY = 512;
  public static final int ACTION_PREVIOUS_HTML_ELEMENT = 2048;
  public static final int ACTION_SCROLL_BACKWARD = 8192;
  public static final int ACTION_SCROLL_FORWARD = 4096;
  public static final int ACTION_SELECT = 4;
  public static final int ACTION_SET_SELECTION = 131072;
  public static final int ACTION_SET_TEXT = 2097152;
  public static final int FOCUS_ACCESSIBILITY = 2;
  public static final int FOCUS_INPUT = 1;
  public static final int MOVEMENT_GRANULARITY_CHARACTER = 1;
  public static final int MOVEMENT_GRANULARITY_LINE = 4;
  public static final int MOVEMENT_GRANULARITY_PAGE = 16;
  public static final int MOVEMENT_GRANULARITY_PARAGRAPH = 8;
  public static final int MOVEMENT_GRANULARITY_WORD = 2;
  private static final String ROLE_DESCRIPTION_KEY = "AccessibilityNodeInfo.roleDescription";
  private final AccessibilityNodeInfo mInfo;
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public int mParentVirtualDescendantId = -1;
  
  private AccessibilityNodeInfoCompat(AccessibilityNodeInfo paramAccessibilityNodeInfo)
  {
    mInfo = paramAccessibilityNodeInfo;
  }
  
  public AccessibilityNodeInfoCompat(Object paramObject)
  {
    mInfo = ((AccessibilityNodeInfo)paramObject);
  }
  
  private static String getActionSymbolicName(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "ACTION_UNKNOWN";
    case 131072: 
      return "ACTION_SET_SELECTION";
    case 65536: 
      return "ACTION_CUT";
    case 32768: 
      return "ACTION_PASTE";
    case 16384: 
      return "ACTION_COPY";
    case 8192: 
      return "ACTION_SCROLL_BACKWARD";
    case 4096: 
      return "ACTION_SCROLL_FORWARD";
    case 2048: 
      return "ACTION_PREVIOUS_HTML_ELEMENT";
    case 1024: 
      return "ACTION_NEXT_HTML_ELEMENT";
    case 512: 
      return "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";
    case 256: 
      return "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";
    case 128: 
      return "ACTION_CLEAR_ACCESSIBILITY_FOCUS";
    case 64: 
      return "ACTION_ACCESSIBILITY_FOCUS";
    case 32: 
      return "ACTION_LONG_CLICK";
    case 16: 
      return "ACTION_CLICK";
    case 8: 
      return "ACTION_CLEAR_SELECTION";
    case 4: 
      return "ACTION_SELECT";
    case 2: 
      return "ACTION_CLEAR_FOCUS";
    }
    return "ACTION_FOCUS";
  }
  
  public static AccessibilityNodeInfoCompat obtain()
  {
    return wrap(AccessibilityNodeInfo.obtain());
  }
  
  public static AccessibilityNodeInfoCompat obtain(AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat)
  {
    return wrap(AccessibilityNodeInfo.obtain(mInfo));
  }
  
  public static AccessibilityNodeInfoCompat obtain(View paramView)
  {
    return wrap(AccessibilityNodeInfo.obtain(paramView));
  }
  
  public static AccessibilityNodeInfoCompat obtain(View paramView, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 16) {
      return wrapNonNullInstance(AccessibilityNodeInfo.obtain(paramView, paramInt));
    }
    return null;
  }
  
  public static AccessibilityNodeInfoCompat wrap(AccessibilityNodeInfo paramAccessibilityNodeInfo)
  {
    return new AccessibilityNodeInfoCompat(paramAccessibilityNodeInfo);
  }
  
  static AccessibilityNodeInfoCompat wrapNonNullInstance(Object paramObject)
  {
    if (paramObject != null) {
      return new AccessibilityNodeInfoCompat(paramObject);
    }
    return null;
  }
  
  public void addAction(int paramInt)
  {
    mInfo.addAction(paramInt);
  }
  
  public void addAction(AccessibilityActionCompat paramAccessibilityActionCompat)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      mInfo.addAction((AccessibilityNodeInfo.AccessibilityAction)mAction);
    }
  }
  
  public void addChild(View paramView)
  {
    mInfo.addChild(paramView);
  }
  
  public void addChild(View paramView, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 16) {
      mInfo.addChild(paramView, paramInt);
    }
  }
  
  public boolean canOpenPopup()
  {
    if (Build.VERSION.SDK_INT >= 19) {
      return mInfo.canOpenPopup();
    }
    return false;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (paramObject == null) {
      return false;
    }
    if (getClass() != paramObject.getClass()) {
      return false;
    }
    paramObject = (AccessibilityNodeInfoCompat)paramObject;
    if (mInfo == null)
    {
      if (mInfo != null) {
        return false;
      }
    }
    else if (!mInfo.equals(mInfo)) {
      return false;
    }
    return true;
  }
  
  public List findAccessibilityNodeInfosByText(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    paramString = mInfo.findAccessibilityNodeInfosByText(paramString);
    int j = paramString.size();
    int i = 0;
    while (i < j)
    {
      localArrayList.add(wrap((AccessibilityNodeInfo)paramString.get(i)));
      i += 1;
    }
    return localArrayList;
  }
  
  public List findAccessibilityNodeInfosByViewId(String paramString)
  {
    if (Build.VERSION.SDK_INT >= 18)
    {
      Object localObject = mInfo.findAccessibilityNodeInfosByViewId(paramString);
      paramString = new ArrayList();
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        paramString.add(wrap((AccessibilityNodeInfo)((Iterator)localObject).next()));
      }
      return paramString;
    }
    return Collections.emptyList();
  }
  
  public AccessibilityNodeInfoCompat findFocus(int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 16) {
      return wrapNonNullInstance(mInfo.findFocus(paramInt));
    }
    return null;
  }
  
  public AccessibilityNodeInfoCompat focusSearch(int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 16) {
      return wrapNonNullInstance(mInfo.focusSearch(paramInt));
    }
    return null;
  }
  
  public List getActionList()
  {
    List localList;
    if (Build.VERSION.SDK_INT >= 21) {
      localList = mInfo.getActionList();
    } else {
      localList = null;
    }
    if (localList != null)
    {
      ArrayList localArrayList = new ArrayList();
      int j = localList.size();
      int i = 0;
      while (i < j)
      {
        localArrayList.add(new AccessibilityActionCompat(localList.get(i)));
        i += 1;
      }
      return localArrayList;
    }
    return Collections.emptyList();
  }
  
  public int getActions()
  {
    return mInfo.getActions();
  }
  
  public void getBoundsInParent(Rect paramRect)
  {
    mInfo.getBoundsInParent(paramRect);
  }
  
  public void getBoundsInScreen(Rect paramRect)
  {
    mInfo.getBoundsInScreen(paramRect);
  }
  
  public AccessibilityNodeInfoCompat getChild(int paramInt)
  {
    return wrapNonNullInstance(mInfo.getChild(paramInt));
  }
  
  public int getChildCount()
  {
    return mInfo.getChildCount();
  }
  
  public CharSequence getClassName()
  {
    return mInfo.getClassName();
  }
  
  public CollectionInfoCompat getCollectionInfo()
  {
    if (Build.VERSION.SDK_INT >= 19)
    {
      AccessibilityNodeInfo.CollectionInfo localCollectionInfo = mInfo.getCollectionInfo();
      if (localCollectionInfo != null) {
        return new CollectionInfoCompat(localCollectionInfo);
      }
    }
    return null;
  }
  
  public CollectionItemInfoCompat getCollectionItemInfo()
  {
    if (Build.VERSION.SDK_INT >= 19)
    {
      AccessibilityNodeInfo.CollectionItemInfo localCollectionItemInfo = mInfo.getCollectionItemInfo();
      if (localCollectionItemInfo != null) {
        return new CollectionItemInfoCompat(localCollectionItemInfo);
      }
    }
    return null;
  }
  
  public CharSequence getContentDescription()
  {
    return mInfo.getContentDescription();
  }
  
  public int getDrawingOrder()
  {
    if (Build.VERSION.SDK_INT >= 24) {
      return mInfo.getDrawingOrder();
    }
    return 0;
  }
  
  public CharSequence getError()
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return mInfo.getError();
    }
    return null;
  }
  
  public Bundle getExtras()
  {
    if (Build.VERSION.SDK_INT >= 19) {
      return mInfo.getExtras();
    }
    return new Bundle();
  }
  
  public Object getInfo()
  {
    return mInfo;
  }
  
  public int getInputType()
  {
    if (Build.VERSION.SDK_INT >= 19) {
      return mInfo.getInputType();
    }
    return 0;
  }
  
  public AccessibilityNodeInfoCompat getLabelFor()
  {
    if (Build.VERSION.SDK_INT >= 17) {
      return wrapNonNullInstance(mInfo.getLabelFor());
    }
    return null;
  }
  
  public AccessibilityNodeInfoCompat getLabeledBy()
  {
    if (Build.VERSION.SDK_INT >= 17) {
      return wrapNonNullInstance(mInfo.getLabeledBy());
    }
    return null;
  }
  
  public int getLiveRegion()
  {
    if (Build.VERSION.SDK_INT >= 19) {
      return mInfo.getLiveRegion();
    }
    return 0;
  }
  
  public int getMaxTextLength()
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return mInfo.getMaxTextLength();
    }
    return -1;
  }
  
  public int getMovementGranularities()
  {
    if (Build.VERSION.SDK_INT >= 16) {
      return mInfo.getMovementGranularities();
    }
    return 0;
  }
  
  public CharSequence getPackageName()
  {
    return mInfo.getPackageName();
  }
  
  public AccessibilityNodeInfoCompat getParent()
  {
    return wrapNonNullInstance(mInfo.getParent());
  }
  
  public RangeInfoCompat getRangeInfo()
  {
    if (Build.VERSION.SDK_INT >= 19)
    {
      AccessibilityNodeInfo.RangeInfo localRangeInfo = mInfo.getRangeInfo();
      if (localRangeInfo != null) {
        return new RangeInfoCompat(localRangeInfo);
      }
    }
    return null;
  }
  
  public CharSequence getRoleDescription()
  {
    if (Build.VERSION.SDK_INT >= 19) {
      return mInfo.getExtras().getCharSequence("AccessibilityNodeInfo.roleDescription");
    }
    return null;
  }
  
  public CharSequence getText()
  {
    return mInfo.getText();
  }
  
  public int getTextSelectionEnd()
  {
    if (Build.VERSION.SDK_INT >= 18) {
      return mInfo.getTextSelectionEnd();
    }
    return -1;
  }
  
  public int getTextSelectionStart()
  {
    if (Build.VERSION.SDK_INT >= 18) {
      return mInfo.getTextSelectionStart();
    }
    return -1;
  }
  
  public AccessibilityNodeInfoCompat getTraversalAfter()
  {
    if (Build.VERSION.SDK_INT >= 22) {
      return wrapNonNullInstance(mInfo.getTraversalAfter());
    }
    return null;
  }
  
  public AccessibilityNodeInfoCompat getTraversalBefore()
  {
    if (Build.VERSION.SDK_INT >= 22) {
      return wrapNonNullInstance(mInfo.getTraversalBefore());
    }
    return null;
  }
  
  public String getViewIdResourceName()
  {
    if (Build.VERSION.SDK_INT >= 18) {
      return mInfo.getViewIdResourceName();
    }
    return null;
  }
  
  public AccessibilityWindowInfoCompat getWindow()
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return AccessibilityWindowInfoCompat.wrapNonNullInstance(mInfo.getWindow());
    }
    return null;
  }
  
  public int getWindowId()
  {
    return mInfo.getWindowId();
  }
  
  public int hashCode()
  {
    if (mInfo == null) {
      return 0;
    }
    return mInfo.hashCode();
  }
  
  public boolean isAccessibilityFocused()
  {
    if (Build.VERSION.SDK_INT >= 16) {
      return mInfo.isAccessibilityFocused();
    }
    return false;
  }
  
  public boolean isCheckable()
  {
    return mInfo.isCheckable();
  }
  
  public boolean isChecked()
  {
    return mInfo.isChecked();
  }
  
  public boolean isClickable()
  {
    return mInfo.isClickable();
  }
  
  public boolean isContentInvalid()
  {
    if (Build.VERSION.SDK_INT >= 19) {
      return mInfo.isContentInvalid();
    }
    return false;
  }
  
  public boolean isContextClickable()
  {
    if (Build.VERSION.SDK_INT >= 23) {
      return mInfo.isContextClickable();
    }
    return false;
  }
  
  public boolean isDismissable()
  {
    if (Build.VERSION.SDK_INT >= 19) {
      return mInfo.isDismissable();
    }
    return false;
  }
  
  public boolean isEditable()
  {
    if (Build.VERSION.SDK_INT >= 18) {
      return mInfo.isEditable();
    }
    return false;
  }
  
  public boolean isEnabled()
  {
    return mInfo.isEnabled();
  }
  
  public boolean isFocusable()
  {
    return mInfo.isFocusable();
  }
  
  public boolean isFocused()
  {
    return mInfo.isFocused();
  }
  
  public boolean isImportantForAccessibility()
  {
    if (Build.VERSION.SDK_INT >= 24) {
      return mInfo.isImportantForAccessibility();
    }
    return true;
  }
  
  public boolean isLongClickable()
  {
    return mInfo.isLongClickable();
  }
  
  public boolean isMultiLine()
  {
    if (Build.VERSION.SDK_INT >= 19) {
      return mInfo.isMultiLine();
    }
    return false;
  }
  
  public boolean isPassword()
  {
    return mInfo.isPassword();
  }
  
  public boolean isScrollable()
  {
    return mInfo.isScrollable();
  }
  
  public boolean isSelected()
  {
    return mInfo.isSelected();
  }
  
  public boolean isVisibleToUser()
  {
    if (Build.VERSION.SDK_INT >= 16) {
      return mInfo.isVisibleToUser();
    }
    return false;
  }
  
  public boolean performAction(int paramInt)
  {
    return mInfo.performAction(paramInt);
  }
  
  public boolean performAction(int paramInt, Bundle paramBundle)
  {
    if (Build.VERSION.SDK_INT >= 16) {
      return mInfo.performAction(paramInt, paramBundle);
    }
    return false;
  }
  
  public void recycle()
  {
    mInfo.recycle();
  }
  
  public boolean refresh()
  {
    if (Build.VERSION.SDK_INT >= 18) {
      return mInfo.refresh();
    }
    return false;
  }
  
  public boolean removeAction(AccessibilityActionCompat paramAccessibilityActionCompat)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return mInfo.removeAction((AccessibilityNodeInfo.AccessibilityAction)mAction);
    }
    return false;
  }
  
  public boolean removeChild(View paramView)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return mInfo.removeChild(paramView);
    }
    return false;
  }
  
  public boolean removeChild(View paramView, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return mInfo.removeChild(paramView, paramInt);
    }
    return false;
  }
  
  public void setAccessibilityFocused(boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT >= 16) {
      mInfo.setAccessibilityFocused(paramBoolean);
    }
  }
  
  public void setBoundsInParent(Rect paramRect)
  {
    mInfo.setBoundsInParent(paramRect);
  }
  
  public void setBoundsInScreen(Rect paramRect)
  {
    mInfo.setBoundsInScreen(paramRect);
  }
  
  public void setCanOpenPopup(boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT >= 19) {
      mInfo.setCanOpenPopup(paramBoolean);
    }
  }
  
  public void setCheckable(boolean paramBoolean)
  {
    mInfo.setCheckable(paramBoolean);
  }
  
  public void setChecked(boolean paramBoolean)
  {
    mInfo.setChecked(paramBoolean);
  }
  
  public void setClassName(CharSequence paramCharSequence)
  {
    mInfo.setClassName(paramCharSequence);
  }
  
  public void setClickable(boolean paramBoolean)
  {
    mInfo.setClickable(paramBoolean);
  }
  
  public void setCollectionInfo(Object paramObject)
  {
    if (Build.VERSION.SDK_INT >= 19) {
      mInfo.setCollectionInfo((AccessibilityNodeInfo.CollectionInfo)mInfo);
    }
  }
  
  public void setCollectionItemInfo(Object paramObject)
  {
    if (Build.VERSION.SDK_INT >= 19) {
      mInfo.setCollectionItemInfo((AccessibilityNodeInfo.CollectionItemInfo)mInfo);
    }
  }
  
  public void setContentDescription(CharSequence paramCharSequence)
  {
    mInfo.setContentDescription(paramCharSequence);
  }
  
  public void setContentInvalid(boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT >= 19) {
      mInfo.setContentInvalid(paramBoolean);
    }
  }
  
  public void setContextClickable(boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT >= 23) {
      mInfo.setContextClickable(paramBoolean);
    }
  }
  
  public void setDismissable(boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT >= 19) {
      mInfo.setDismissable(paramBoolean);
    }
  }
  
  public void setDrawingOrder(int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 24) {
      mInfo.setDrawingOrder(paramInt);
    }
  }
  
  public void setEditable(boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT >= 18) {
      mInfo.setEditable(paramBoolean);
    }
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    mInfo.setEnabled(paramBoolean);
  }
  
  public void setError(CharSequence paramCharSequence)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      mInfo.setError(paramCharSequence);
    }
  }
  
  public void setFocusable(boolean paramBoolean)
  {
    mInfo.setFocusable(paramBoolean);
  }
  
  public void setFocused(boolean paramBoolean)
  {
    mInfo.setFocused(paramBoolean);
  }
  
  public void setImportantForAccessibility(boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT >= 24) {
      mInfo.setImportantForAccessibility(paramBoolean);
    }
  }
  
  public void setInputType(int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 19) {
      mInfo.setInputType(paramInt);
    }
  }
  
  public void setLabelFor(View paramView)
  {
    if (Build.VERSION.SDK_INT >= 17) {
      mInfo.setLabelFor(paramView);
    }
  }
  
  public void setLabelFor(View paramView, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 17) {
      mInfo.setLabelFor(paramView, paramInt);
    }
  }
  
  public void setLabeledBy(View paramView)
  {
    if (Build.VERSION.SDK_INT >= 17) {
      mInfo.setLabeledBy(paramView);
    }
  }
  
  public void setLabeledBy(View paramView, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 17) {
      mInfo.setLabeledBy(paramView, paramInt);
    }
  }
  
  public void setLiveRegion(int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 19) {
      mInfo.setLiveRegion(paramInt);
    }
  }
  
  public void setLongClickable(boolean paramBoolean)
  {
    mInfo.setLongClickable(paramBoolean);
  }
  
  public void setMaxTextLength(int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      mInfo.setMaxTextLength(paramInt);
    }
  }
  
  public void setMovementGranularities(int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 16) {
      mInfo.setMovementGranularities(paramInt);
    }
  }
  
  public void setMultiLine(boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT >= 19) {
      mInfo.setMultiLine(paramBoolean);
    }
  }
  
  public void setPackageName(CharSequence paramCharSequence)
  {
    mInfo.setPackageName(paramCharSequence);
  }
  
  public void setParent(View paramView)
  {
    mInfo.setParent(paramView);
  }
  
  public void setParent(View paramView, int paramInt)
  {
    mParentVirtualDescendantId = paramInt;
    if (Build.VERSION.SDK_INT >= 16) {
      mInfo.setParent(paramView, paramInt);
    }
  }
  
  public void setPassword(boolean paramBoolean)
  {
    mInfo.setPassword(paramBoolean);
  }
  
  public void setRangeInfo(RangeInfoCompat paramRangeInfoCompat)
  {
    if (Build.VERSION.SDK_INT >= 19) {
      mInfo.setRangeInfo((AccessibilityNodeInfo.RangeInfo)mInfo);
    }
  }
  
  public void setRoleDescription(CharSequence paramCharSequence)
  {
    if (Build.VERSION.SDK_INT >= 19) {
      mInfo.getExtras().putCharSequence("AccessibilityNodeInfo.roleDescription", paramCharSequence);
    }
  }
  
  public void setScrollable(boolean paramBoolean)
  {
    mInfo.setScrollable(paramBoolean);
  }
  
  public void setSelected(boolean paramBoolean)
  {
    mInfo.setSelected(paramBoolean);
  }
  
  public void setSource(View paramView)
  {
    mInfo.setSource(paramView);
  }
  
  public void setSource(View paramView, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 16) {
      mInfo.setSource(paramView, paramInt);
    }
  }
  
  public void setText(CharSequence paramCharSequence)
  {
    mInfo.setText(paramCharSequence);
  }
  
  public void setTextSelection(int paramInt1, int paramInt2)
  {
    if (Build.VERSION.SDK_INT >= 18) {
      mInfo.setTextSelection(paramInt1, paramInt2);
    }
  }
  
  public void setTraversalAfter(View paramView)
  {
    if (Build.VERSION.SDK_INT >= 22) {
      mInfo.setTraversalAfter(paramView);
    }
  }
  
  public void setTraversalAfter(View paramView, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 22) {
      mInfo.setTraversalAfter(paramView, paramInt);
    }
  }
  
  public void setTraversalBefore(View paramView)
  {
    if (Build.VERSION.SDK_INT >= 22) {
      mInfo.setTraversalBefore(paramView);
    }
  }
  
  public void setTraversalBefore(View paramView, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 22) {
      mInfo.setTraversalBefore(paramView, paramInt);
    }
  }
  
  public void setViewIdResourceName(String paramString)
  {
    if (Build.VERSION.SDK_INT >= 18) {
      mInfo.setViewIdResourceName(paramString);
    }
  }
  
  public void setVisibleToUser(boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT >= 16) {
      mInfo.setVisibleToUser(paramBoolean);
    }
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append(super.toString());
    Object localObject = new Rect();
    getBoundsInParent((Rect)localObject);
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("; boundsInParent: ");
    localStringBuilder2.append(localObject);
    localStringBuilder1.append(localStringBuilder2.toString());
    getBoundsInScreen((Rect)localObject);
    localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("; boundsInScreen: ");
    localStringBuilder2.append(localObject);
    localStringBuilder1.append(localStringBuilder2.toString());
    localStringBuilder1.append("; packageName: ");
    localStringBuilder1.append(getPackageName());
    localStringBuilder1.append("; className: ");
    localStringBuilder1.append(getClassName());
    localStringBuilder1.append("; text: ");
    localStringBuilder1.append(getText());
    localStringBuilder1.append("; contentDescription: ");
    localStringBuilder1.append(getContentDescription());
    localStringBuilder1.append("; viewId: ");
    localStringBuilder1.append(getViewIdResourceName());
    localStringBuilder1.append("; checkable: ");
    localStringBuilder1.append(isCheckable());
    localStringBuilder1.append("; checked: ");
    localStringBuilder1.append(isChecked());
    localStringBuilder1.append("; focusable: ");
    localStringBuilder1.append(isFocusable());
    localStringBuilder1.append("; focused: ");
    localStringBuilder1.append(isFocused());
    localStringBuilder1.append("; selected: ");
    localStringBuilder1.append(isSelected());
    localStringBuilder1.append("; clickable: ");
    localStringBuilder1.append(isClickable());
    localStringBuilder1.append("; longClickable: ");
    localStringBuilder1.append(isLongClickable());
    localStringBuilder1.append("; enabled: ");
    localStringBuilder1.append(isEnabled());
    localStringBuilder1.append("; password: ");
    localStringBuilder1.append(isPassword());
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("; scrollable: ");
    ((StringBuilder)localObject).append(isScrollable());
    localStringBuilder1.append(((StringBuilder)localObject).toString());
    localStringBuilder1.append("; [");
    int i = getActions();
    while (i != 0)
    {
      int k = 1 << Integer.numberOfTrailingZeros(i);
      int j = i & k;
      localStringBuilder1.append(getActionSymbolicName(k));
      i = j;
      if (j != 0)
      {
        localStringBuilder1.append(", ");
        i = j;
      }
    }
    localStringBuilder1.append("]");
    return localStringBuilder1.toString();
  }
  
  public AccessibilityNodeInfo unwrap()
  {
    return mInfo;
  }
  
  public static class AccessibilityActionCompat
  {
    public static final AccessibilityActionCompat ACTION_ACCESSIBILITY_FOCUS;
    public static final AccessibilityActionCompat ACTION_CLEAR_ACCESSIBILITY_FOCUS;
    public static final AccessibilityActionCompat ACTION_CLEAR_FOCUS;
    public static final AccessibilityActionCompat ACTION_CLEAR_SELECTION;
    public static final AccessibilityActionCompat ACTION_CLICK;
    public static final AccessibilityActionCompat ACTION_COLLAPSE;
    public static final AccessibilityActionCompat ACTION_CONTEXT_CLICK;
    public static final AccessibilityActionCompat ACTION_COPY;
    public static final AccessibilityActionCompat ACTION_CUT;
    public static final AccessibilityActionCompat ACTION_DISMISS;
    public static final AccessibilityActionCompat ACTION_EXPAND;
    public static final AccessibilityActionCompat ACTION_FOCUS;
    public static final AccessibilityActionCompat ACTION_LONG_CLICK;
    public static final AccessibilityActionCompat ACTION_NEXT_AT_MOVEMENT_GRANULARITY;
    public static final AccessibilityActionCompat ACTION_NEXT_HTML_ELEMENT;
    public static final AccessibilityActionCompat ACTION_PASTE;
    public static final AccessibilityActionCompat ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY;
    public static final AccessibilityActionCompat ACTION_PREVIOUS_HTML_ELEMENT;
    public static final AccessibilityActionCompat ACTION_SCROLL_BACKWARD;
    public static final AccessibilityActionCompat ACTION_SCROLL_DOWN;
    public static final AccessibilityActionCompat ACTION_SCROLL_FORWARD;
    public static final AccessibilityActionCompat ACTION_SCROLL_LEFT;
    public static final AccessibilityActionCompat ACTION_SCROLL_RIGHT;
    public static final AccessibilityActionCompat ACTION_SCROLL_TO_POSITION;
    public static final AccessibilityActionCompat ACTION_SCROLL_UP;
    public static final AccessibilityActionCompat ACTION_SELECT;
    public static final AccessibilityActionCompat ACTION_SET_PROGRESS;
    public static final AccessibilityActionCompat ACTION_SET_SELECTION;
    public static final AccessibilityActionCompat ACTION_SET_TEXT;
    public static final AccessibilityActionCompat ACTION_SHOW_ON_SCREEN;
    final Object mAction;
    
    static
    {
      Object localObject2 = null;
      ACTION_FOCUS = new AccessibilityActionCompat(1, null);
      ACTION_CLEAR_FOCUS = new AccessibilityActionCompat(2, null);
      ACTION_SELECT = new AccessibilityActionCompat(4, null);
      ACTION_CLEAR_SELECTION = new AccessibilityActionCompat(8, null);
      ACTION_CLICK = new AccessibilityActionCompat(16, null);
      ACTION_LONG_CLICK = new AccessibilityActionCompat(32, null);
      ACTION_ACCESSIBILITY_FOCUS = new AccessibilityActionCompat(64, null);
      ACTION_CLEAR_ACCESSIBILITY_FOCUS = new AccessibilityActionCompat(128, null);
      ACTION_NEXT_AT_MOVEMENT_GRANULARITY = new AccessibilityActionCompat(256, null);
      ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY = new AccessibilityActionCompat(512, null);
      ACTION_NEXT_HTML_ELEMENT = new AccessibilityActionCompat(1024, null);
      ACTION_PREVIOUS_HTML_ELEMENT = new AccessibilityActionCompat(2048, null);
      ACTION_SCROLL_FORWARD = new AccessibilityActionCompat(4096, null);
      ACTION_SCROLL_BACKWARD = new AccessibilityActionCompat(8192, null);
      ACTION_COPY = new AccessibilityActionCompat(16384, null);
      ACTION_PASTE = new AccessibilityActionCompat(32768, null);
      ACTION_CUT = new AccessibilityActionCompat(65536, null);
      ACTION_SET_SELECTION = new AccessibilityActionCompat(131072, null);
      ACTION_EXPAND = new AccessibilityActionCompat(262144, null);
      ACTION_COLLAPSE = new AccessibilityActionCompat(524288, null);
      ACTION_DISMISS = new AccessibilityActionCompat(1048576, null);
      ACTION_SET_TEXT = new AccessibilityActionCompat(2097152, null);
      if (Build.VERSION.SDK_INT >= 23) {
        localObject1 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_ON_SCREEN;
      } else {
        localObject1 = null;
      }
      ACTION_SHOW_ON_SCREEN = new AccessibilityActionCompat(localObject1);
      if (Build.VERSION.SDK_INT >= 23) {
        localObject1 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_TO_POSITION;
      } else {
        localObject1 = null;
      }
      ACTION_SCROLL_TO_POSITION = new AccessibilityActionCompat(localObject1);
      if (Build.VERSION.SDK_INT >= 23) {
        localObject1 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP;
      } else {
        localObject1 = null;
      }
      ACTION_SCROLL_UP = new AccessibilityActionCompat(localObject1);
      if (Build.VERSION.SDK_INT >= 23) {
        localObject1 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT;
      } else {
        localObject1 = null;
      }
      ACTION_SCROLL_LEFT = new AccessibilityActionCompat(localObject1);
      if (Build.VERSION.SDK_INT >= 23) {
        localObject1 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN;
      } else {
        localObject1 = null;
      }
      ACTION_SCROLL_DOWN = new AccessibilityActionCompat(localObject1);
      if (Build.VERSION.SDK_INT >= 23) {
        localObject1 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT;
      } else {
        localObject1 = null;
      }
      ACTION_SCROLL_RIGHT = new AccessibilityActionCompat(localObject1);
      if (Build.VERSION.SDK_INT >= 23) {
        localObject1 = AccessibilityNodeInfo.AccessibilityAction.ACTION_CONTEXT_CLICK;
      } else {
        localObject1 = null;
      }
      ACTION_CONTEXT_CLICK = new AccessibilityActionCompat(localObject1);
      Object localObject1 = localObject2;
      if (Build.VERSION.SDK_INT >= 24) {
        localObject1 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS;
      }
      ACTION_SET_PROGRESS = new AccessibilityActionCompat(localObject1);
    }
    
    public AccessibilityActionCompat(int paramInt, CharSequence paramCharSequence)
    {
      this(paramCharSequence);
    }
    
    AccessibilityActionCompat(Object paramObject)
    {
      mAction = paramObject;
    }
    
    public int getId()
    {
      if (Build.VERSION.SDK_INT >= 21) {
        return ((AccessibilityNodeInfo.AccessibilityAction)mAction).getId();
      }
      return 0;
    }
    
    public CharSequence getLabel()
    {
      if (Build.VERSION.SDK_INT >= 21) {
        return ((AccessibilityNodeInfo.AccessibilityAction)mAction).getLabel();
      }
      return null;
    }
  }
  
  public static class CollectionInfoCompat
  {
    public static final int SELECTION_MODE_MULTIPLE = 2;
    public static final int SELECTION_MODE_NONE = 0;
    public static final int SELECTION_MODE_SINGLE = 1;
    final Object mInfo;
    
    CollectionInfoCompat(Object paramObject)
    {
      mInfo = paramObject;
    }
    
    public static CollectionInfoCompat obtain(int paramInt1, int paramInt2, boolean paramBoolean)
    {
      if (Build.VERSION.SDK_INT >= 19) {
        return new CollectionInfoCompat(AccessibilityNodeInfo.CollectionInfo.obtain(paramInt1, paramInt2, paramBoolean));
      }
      return new CollectionInfoCompat(null);
    }
    
    public static CollectionInfoCompat obtain(int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3)
    {
      if (Build.VERSION.SDK_INT >= 21) {
        return new CollectionInfoCompat(AccessibilityNodeInfo.CollectionInfo.obtain(paramInt1, paramInt2, paramBoolean, paramInt3));
      }
      if (Build.VERSION.SDK_INT >= 19) {
        return new CollectionInfoCompat(AccessibilityNodeInfo.CollectionInfo.obtain(paramInt1, paramInt2, paramBoolean));
      }
      return new CollectionInfoCompat(null);
    }
    
    public int getColumnCount()
    {
      if (Build.VERSION.SDK_INT >= 19) {
        return ((AccessibilityNodeInfo.CollectionInfo)mInfo).getColumnCount();
      }
      return 0;
    }
    
    public int getRowCount()
    {
      if (Build.VERSION.SDK_INT >= 19) {
        return ((AccessibilityNodeInfo.CollectionInfo)mInfo).getRowCount();
      }
      return 0;
    }
    
    public int getSelectionMode()
    {
      if (Build.VERSION.SDK_INT >= 21) {
        return ((AccessibilityNodeInfo.CollectionInfo)mInfo).getSelectionMode();
      }
      return 0;
    }
    
    public boolean isHierarchical()
    {
      if (Build.VERSION.SDK_INT >= 19) {
        return ((AccessibilityNodeInfo.CollectionInfo)mInfo).isHierarchical();
      }
      return false;
    }
  }
  
  public static class CollectionItemInfoCompat
  {
    final Object mInfo;
    
    CollectionItemInfoCompat(Object paramObject)
    {
      mInfo = paramObject;
    }
    
    public static CollectionItemInfoCompat obtain(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean)
    {
      if (Build.VERSION.SDK_INT >= 19) {
        return new CollectionItemInfoCompat(AccessibilityNodeInfo.CollectionItemInfo.obtain(paramInt1, paramInt2, paramInt3, paramInt4, paramBoolean));
      }
      return new CollectionItemInfoCompat(null);
    }
    
    public static CollectionItemInfoCompat obtain(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean1, boolean paramBoolean2)
    {
      if (Build.VERSION.SDK_INT >= 21) {
        return new CollectionItemInfoCompat(AccessibilityNodeInfo.CollectionItemInfo.obtain(paramInt1, paramInt2, paramInt3, paramInt4, paramBoolean1, paramBoolean2));
      }
      if (Build.VERSION.SDK_INT >= 19) {
        return new CollectionItemInfoCompat(AccessibilityNodeInfo.CollectionItemInfo.obtain(paramInt1, paramInt2, paramInt3, paramInt4, paramBoolean1));
      }
      return new CollectionItemInfoCompat(null);
    }
    
    public int getColumnIndex()
    {
      if (Build.VERSION.SDK_INT >= 19) {
        return ((AccessibilityNodeInfo.CollectionItemInfo)mInfo).getColumnIndex();
      }
      return 0;
    }
    
    public int getColumnSpan()
    {
      if (Build.VERSION.SDK_INT >= 19) {
        return ((AccessibilityNodeInfo.CollectionItemInfo)mInfo).getColumnSpan();
      }
      return 0;
    }
    
    public int getRowIndex()
    {
      if (Build.VERSION.SDK_INT >= 19) {
        return ((AccessibilityNodeInfo.CollectionItemInfo)mInfo).getRowIndex();
      }
      return 0;
    }
    
    public int getRowSpan()
    {
      if (Build.VERSION.SDK_INT >= 19) {
        return ((AccessibilityNodeInfo.CollectionItemInfo)mInfo).getRowSpan();
      }
      return 0;
    }
    
    public boolean isHeading()
    {
      if (Build.VERSION.SDK_INT >= 19) {
        return ((AccessibilityNodeInfo.CollectionItemInfo)mInfo).isHeading();
      }
      return false;
    }
    
    public boolean isSelected()
    {
      if (Build.VERSION.SDK_INT >= 21) {
        return ((AccessibilityNodeInfo.CollectionItemInfo)mInfo).isSelected();
      }
      return false;
    }
  }
  
  public static class RangeInfoCompat
  {
    public static final int RANGE_TYPE_FLOAT = 1;
    public static final int RANGE_TYPE_INT = 0;
    public static final int RANGE_TYPE_PERCENT = 2;
    final Object mInfo;
    
    RangeInfoCompat(Object paramObject)
    {
      mInfo = paramObject;
    }
    
    public static RangeInfoCompat obtain(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3)
    {
      if (Build.VERSION.SDK_INT >= 19) {
        return new RangeInfoCompat(AccessibilityNodeInfo.RangeInfo.obtain(paramInt, paramFloat1, paramFloat2, paramFloat3));
      }
      return new RangeInfoCompat(null);
    }
    
    public float getCurrent()
    {
      if (Build.VERSION.SDK_INT >= 19) {
        return ((AccessibilityNodeInfo.RangeInfo)mInfo).getCurrent();
      }
      return 0.0F;
    }
    
    public float getMax()
    {
      if (Build.VERSION.SDK_INT >= 19) {
        return ((AccessibilityNodeInfo.RangeInfo)mInfo).getMax();
      }
      return 0.0F;
    }
    
    public float getMin()
    {
      if (Build.VERSION.SDK_INT >= 19) {
        return ((AccessibilityNodeInfo.RangeInfo)mInfo).getMin();
      }
      return 0.0F;
    }
    
    public int getType()
    {
      if (Build.VERSION.SDK_INT >= 19) {
        return ((AccessibilityNodeInfo.RangeInfo)mInfo).getType();
      }
      return 0;
    }
  }
}
