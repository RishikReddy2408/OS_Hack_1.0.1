package android.support.v4.view.accessibility;

import android.os.Build.VERSION;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.accessibility.AccessibilityRecord;
import java.util.List;

public class AccessibilityRecordCompat
{
  private final AccessibilityRecord mRecord;
  
  @Deprecated
  public AccessibilityRecordCompat(Object paramObject)
  {
    mRecord = ((AccessibilityRecord)paramObject);
  }
  
  public static int getMaxScrollX(AccessibilityRecord paramAccessibilityRecord)
  {
    if (Build.VERSION.SDK_INT >= 15) {
      return paramAccessibilityRecord.getMaxScrollX();
    }
    return 0;
  }
  
  public static int getMaxScrollY(AccessibilityRecord paramAccessibilityRecord)
  {
    if (Build.VERSION.SDK_INT >= 15) {
      return paramAccessibilityRecord.getMaxScrollY();
    }
    return 0;
  }
  
  @Deprecated
  public static AccessibilityRecordCompat obtain()
  {
    return new AccessibilityRecordCompat(AccessibilityRecord.obtain());
  }
  
  @Deprecated
  public static AccessibilityRecordCompat obtain(AccessibilityRecordCompat paramAccessibilityRecordCompat)
  {
    return new AccessibilityRecordCompat(AccessibilityRecord.obtain(mRecord));
  }
  
  public static void setMaxScrollX(AccessibilityRecord paramAccessibilityRecord, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 15) {
      paramAccessibilityRecord.setMaxScrollX(paramInt);
    }
  }
  
  public static void setMaxScrollY(AccessibilityRecord paramAccessibilityRecord, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 15) {
      paramAccessibilityRecord.setMaxScrollY(paramInt);
    }
  }
  
  public static void setSource(@NonNull AccessibilityRecord paramAccessibilityRecord, View paramView, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 16) {
      paramAccessibilityRecord.setSource(paramView, paramInt);
    }
  }
  
  @Deprecated
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
    paramObject = (AccessibilityRecordCompat)paramObject;
    if (mRecord == null)
    {
      if (mRecord != null) {
        return false;
      }
    }
    else if (!mRecord.equals(mRecord)) {
      return false;
    }
    return true;
  }
  
  @Deprecated
  public int getAddedCount()
  {
    return mRecord.getAddedCount();
  }
  
  @Deprecated
  public CharSequence getBeforeText()
  {
    return mRecord.getBeforeText();
  }
  
  @Deprecated
  public CharSequence getClassName()
  {
    return mRecord.getClassName();
  }
  
  @Deprecated
  public CharSequence getContentDescription()
  {
    return mRecord.getContentDescription();
  }
  
  @Deprecated
  public int getCurrentItemIndex()
  {
    return mRecord.getCurrentItemIndex();
  }
  
  @Deprecated
  public int getFromIndex()
  {
    return mRecord.getFromIndex();
  }
  
  @Deprecated
  public Object getImpl()
  {
    return mRecord;
  }
  
  @Deprecated
  public int getItemCount()
  {
    return mRecord.getItemCount();
  }
  
  @Deprecated
  public int getMaxScrollX()
  {
    return getMaxScrollX(mRecord);
  }
  
  @Deprecated
  public int getMaxScrollY()
  {
    return getMaxScrollY(mRecord);
  }
  
  @Deprecated
  public Parcelable getParcelableData()
  {
    return mRecord.getParcelableData();
  }
  
  @Deprecated
  public int getRemovedCount()
  {
    return mRecord.getRemovedCount();
  }
  
  @Deprecated
  public int getScrollX()
  {
    return mRecord.getScrollX();
  }
  
  @Deprecated
  public int getScrollY()
  {
    return mRecord.getScrollY();
  }
  
  @Deprecated
  public AccessibilityNodeInfoCompat getSource()
  {
    return AccessibilityNodeInfoCompat.wrapNonNullInstance(mRecord.getSource());
  }
  
  @Deprecated
  public List<CharSequence> getText()
  {
    return mRecord.getText();
  }
  
  @Deprecated
  public int getToIndex()
  {
    return mRecord.getToIndex();
  }
  
  @Deprecated
  public int getWindowId()
  {
    return mRecord.getWindowId();
  }
  
  @Deprecated
  public int hashCode()
  {
    if (mRecord == null) {
      return 0;
    }
    return mRecord.hashCode();
  }
  
  @Deprecated
  public boolean isChecked()
  {
    return mRecord.isChecked();
  }
  
  @Deprecated
  public boolean isEnabled()
  {
    return mRecord.isEnabled();
  }
  
  @Deprecated
  public boolean isFullScreen()
  {
    return mRecord.isFullScreen();
  }
  
  @Deprecated
  public boolean isPassword()
  {
    return mRecord.isPassword();
  }
  
  @Deprecated
  public boolean isScrollable()
  {
    return mRecord.isScrollable();
  }
  
  @Deprecated
  public void recycle()
  {
    mRecord.recycle();
  }
  
  @Deprecated
  public void setAddedCount(int paramInt)
  {
    mRecord.setAddedCount(paramInt);
  }
  
  @Deprecated
  public void setBeforeText(CharSequence paramCharSequence)
  {
    mRecord.setBeforeText(paramCharSequence);
  }
  
  @Deprecated
  public void setChecked(boolean paramBoolean)
  {
    mRecord.setChecked(paramBoolean);
  }
  
  @Deprecated
  public void setClassName(CharSequence paramCharSequence)
  {
    mRecord.setClassName(paramCharSequence);
  }
  
  @Deprecated
  public void setContentDescription(CharSequence paramCharSequence)
  {
    mRecord.setContentDescription(paramCharSequence);
  }
  
  @Deprecated
  public void setCurrentItemIndex(int paramInt)
  {
    mRecord.setCurrentItemIndex(paramInt);
  }
  
  @Deprecated
  public void setEnabled(boolean paramBoolean)
  {
    mRecord.setEnabled(paramBoolean);
  }
  
  @Deprecated
  public void setFromIndex(int paramInt)
  {
    mRecord.setFromIndex(paramInt);
  }
  
  @Deprecated
  public void setFullScreen(boolean paramBoolean)
  {
    mRecord.setFullScreen(paramBoolean);
  }
  
  @Deprecated
  public void setItemCount(int paramInt)
  {
    mRecord.setItemCount(paramInt);
  }
  
  @Deprecated
  public void setMaxScrollX(int paramInt)
  {
    setMaxScrollX(mRecord, paramInt);
  }
  
  @Deprecated
  public void setMaxScrollY(int paramInt)
  {
    setMaxScrollY(mRecord, paramInt);
  }
  
  @Deprecated
  public void setParcelableData(Parcelable paramParcelable)
  {
    mRecord.setParcelableData(paramParcelable);
  }
  
  @Deprecated
  public void setPassword(boolean paramBoolean)
  {
    mRecord.setPassword(paramBoolean);
  }
  
  @Deprecated
  public void setRemovedCount(int paramInt)
  {
    mRecord.setRemovedCount(paramInt);
  }
  
  @Deprecated
  public void setScrollX(int paramInt)
  {
    mRecord.setScrollX(paramInt);
  }
  
  @Deprecated
  public void setScrollY(int paramInt)
  {
    mRecord.setScrollY(paramInt);
  }
  
  @Deprecated
  public void setScrollable(boolean paramBoolean)
  {
    mRecord.setScrollable(paramBoolean);
  }
  
  @Deprecated
  public void setSource(View paramView)
  {
    mRecord.setSource(paramView);
  }
  
  @Deprecated
  public void setSource(View paramView, int paramInt)
  {
    setSource(mRecord, paramView, paramInt);
  }
  
  @Deprecated
  public void setToIndex(int paramInt)
  {
    mRecord.setToIndex(paramInt);
  }
}
