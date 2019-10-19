package android.support.v4.view.accessibility;

import android.os.Build.VERSION;
import android.os.Parcelable;
import android.view.View;
import android.view.accessibility.AccessibilityRecord;
import java.util.List;

public class AccessibilityRecordCompat
{
  private final AccessibilityRecord mRecord;
  
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
  
  public static AccessibilityRecordCompat obtain()
  {
    return new AccessibilityRecordCompat(AccessibilityRecord.obtain());
  }
  
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
  
  public static void setSource(AccessibilityRecord paramAccessibilityRecord, View paramView, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 16) {
      paramAccessibilityRecord.setSource(paramView, paramInt);
    }
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
  
  public int getAddedCount()
  {
    return mRecord.getAddedCount();
  }
  
  public CharSequence getBeforeText()
  {
    return mRecord.getBeforeText();
  }
  
  public CharSequence getClassName()
  {
    return mRecord.getClassName();
  }
  
  public CharSequence getContentDescription()
  {
    return mRecord.getContentDescription();
  }
  
  public int getCurrentItemIndex()
  {
    return mRecord.getCurrentItemIndex();
  }
  
  public int getFromIndex()
  {
    return mRecord.getFromIndex();
  }
  
  public Object getImpl()
  {
    return mRecord;
  }
  
  public int getItemCount()
  {
    return mRecord.getItemCount();
  }
  
  public int getMaxScrollX()
  {
    return getMaxScrollX(mRecord);
  }
  
  public int getMaxScrollY()
  {
    return getMaxScrollY(mRecord);
  }
  
  public Parcelable getParcelableData()
  {
    return mRecord.getParcelableData();
  }
  
  public int getRemovedCount()
  {
    return mRecord.getRemovedCount();
  }
  
  public int getScrollX()
  {
    return mRecord.getScrollX();
  }
  
  public int getScrollY()
  {
    return mRecord.getScrollY();
  }
  
  public AccessibilityNodeInfoCompat getSource()
  {
    return AccessibilityNodeInfoCompat.wrapNonNullInstance(mRecord.getSource());
  }
  
  public List getText()
  {
    return mRecord.getText();
  }
  
  public int getToIndex()
  {
    return mRecord.getToIndex();
  }
  
  public int getWindowId()
  {
    return mRecord.getWindowId();
  }
  
  public int hashCode()
  {
    if (mRecord == null) {
      return 0;
    }
    return mRecord.hashCode();
  }
  
  public boolean isChecked()
  {
    return mRecord.isChecked();
  }
  
  public boolean isEnabled()
  {
    return mRecord.isEnabled();
  }
  
  public boolean isFullScreen()
  {
    return mRecord.isFullScreen();
  }
  
  public boolean isPassword()
  {
    return mRecord.isPassword();
  }
  
  public boolean isScrollable()
  {
    return mRecord.isScrollable();
  }
  
  public void recycle()
  {
    mRecord.recycle();
  }
  
  public void setAddedCount(int paramInt)
  {
    mRecord.setAddedCount(paramInt);
  }
  
  public void setBeforeText(CharSequence paramCharSequence)
  {
    mRecord.setBeforeText(paramCharSequence);
  }
  
  public void setChecked(boolean paramBoolean)
  {
    mRecord.setChecked(paramBoolean);
  }
  
  public void setClassName(CharSequence paramCharSequence)
  {
    mRecord.setClassName(paramCharSequence);
  }
  
  public void setContentDescription(CharSequence paramCharSequence)
  {
    mRecord.setContentDescription(paramCharSequence);
  }
  
  public void setCurrentItemIndex(int paramInt)
  {
    mRecord.setCurrentItemIndex(paramInt);
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    mRecord.setEnabled(paramBoolean);
  }
  
  public void setFromIndex(int paramInt)
  {
    mRecord.setFromIndex(paramInt);
  }
  
  public void setFullScreen(boolean paramBoolean)
  {
    mRecord.setFullScreen(paramBoolean);
  }
  
  public void setItemCount(int paramInt)
  {
    mRecord.setItemCount(paramInt);
  }
  
  public void setMaxScrollX(int paramInt)
  {
    setMaxScrollX(mRecord, paramInt);
  }
  
  public void setMaxScrollY(int paramInt)
  {
    setMaxScrollY(mRecord, paramInt);
  }
  
  public void setParcelableData(Parcelable paramParcelable)
  {
    mRecord.setParcelableData(paramParcelable);
  }
  
  public void setPassword(boolean paramBoolean)
  {
    mRecord.setPassword(paramBoolean);
  }
  
  public void setRemovedCount(int paramInt)
  {
    mRecord.setRemovedCount(paramInt);
  }
  
  public void setScrollX(int paramInt)
  {
    mRecord.setScrollX(paramInt);
  }
  
  public void setScrollY(int paramInt)
  {
    mRecord.setScrollY(paramInt);
  }
  
  public void setScrollable(boolean paramBoolean)
  {
    mRecord.setScrollable(paramBoolean);
  }
  
  public void setSource(View paramView)
  {
    mRecord.setSource(paramView);
  }
  
  public void setSource(View paramView, int paramInt)
  {
    setSource(mRecord, paramView, paramInt);
  }
  
  public void setToIndex(int paramInt)
  {
    mRecord.setToIndex(paramInt);
  }
}
