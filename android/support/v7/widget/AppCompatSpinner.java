package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.database.DataSetObserver;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.view.TintableBackgroundView;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R.attr;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.view.menu.ShowableListMenu;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AbsListView;
import android.widget.AbsSpinner;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class AppCompatSpinner
  extends Spinner
  implements TintableBackgroundView
{
  private static final int[] ATTRS_ANDROID_SPINNERMODE = { 16843505 };
  private static final int MAX_ITEMS_MEASURED = 15;
  private static final int MODE_DIALOG = 0;
  private static final int MODE_DROPDOWN = 1;
  private static final int MODE_THEME = -1;
  private static final String TAG = "AppCompatSpinner";
  private final AppCompatBackgroundHelper mBackgroundTintHelper;
  private int mDropDownWidth;
  private ForwardingListener mForwardingListener;
  private DropdownPopup mPopup;
  private final Context mPopupContext;
  private final boolean mPopupSet;
  private SpinnerAdapter mTempAdapter;
  private final Rect mTempRect;
  
  public AppCompatSpinner(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public AppCompatSpinner(Context paramContext, int paramInt)
  {
    this(paramContext, null, R.attr.spinnerStyle, paramInt);
  }
  
  public AppCompatSpinner(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.spinnerStyle);
  }
  
  public AppCompatSpinner(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    this(paramContext, paramAttributeSet, paramInt, -1);
  }
  
  public AppCompatSpinner(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    this(paramContext, paramAttributeSet, paramInt1, paramInt2, null);
  }
  
  /* Error */
  public AppCompatSpinner(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2, final Resources.Theme paramTheme)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: aload_2
    //   3: iload_3
    //   4: invokespecial 81	android/widget/Spinner:<init>	(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    //   7: aload_0
    //   8: new 83	android/graphics/Rect
    //   11: dup
    //   12: invokespecial 85	android/graphics/Rect:<init>	()V
    //   15: putfield 87	android/support/v7/widget/AppCompatSpinner:mTempRect	Landroid/graphics/Rect;
    //   18: aload_1
    //   19: aload_2
    //   20: getstatic 92	android/support/v7/appcompat/R$styleable:Spinner	[I
    //   23: iload_3
    //   24: iconst_0
    //   25: invokestatic 98	android/support/v7/widget/TintTypedArray:obtainStyledAttributes	(Landroid/content/Context;Landroid/util/AttributeSet;[III)Landroid/support/v7/widget/TintTypedArray;
    //   28: astore 11
    //   30: aload_0
    //   31: new 100	android/support/v7/widget/AppCompatBackgroundHelper
    //   34: dup
    //   35: aload_0
    //   36: invokespecial 103	android/support/v7/widget/AppCompatBackgroundHelper:<init>	(Landroid/view/View;)V
    //   39: putfield 105	android/support/v7/widget/AppCompatSpinner:mBackgroundTintHelper	Landroid/support/v7/widget/AppCompatBackgroundHelper;
    //   42: aload 5
    //   44: ifnull +20 -> 64
    //   47: aload_0
    //   48: new 107	android/support/v7/view/ContextThemeWrapper
    //   51: dup
    //   52: aload_1
    //   53: aload 5
    //   55: invokespecial 110	android/support/v7/view/ContextThemeWrapper:<init>	(Landroid/content/Context;Landroid/content/res/Resources$Theme;)V
    //   58: putfield 112	android/support/v7/widget/AppCompatSpinner:mPopupContext	Landroid/content/Context;
    //   61: goto +59 -> 120
    //   64: aload 11
    //   66: getstatic 115	android/support/v7/appcompat/R$styleable:Spinner_popupTheme	I
    //   69: iconst_0
    //   70: invokevirtual 119	android/support/v7/widget/TintTypedArray:getResourceId	(II)I
    //   73: istore 6
    //   75: iload 6
    //   77: ifeq +20 -> 97
    //   80: aload_0
    //   81: new 107	android/support/v7/view/ContextThemeWrapper
    //   84: dup
    //   85: aload_1
    //   86: iload 6
    //   88: invokespecial 121	android/support/v7/view/ContextThemeWrapper:<init>	(Landroid/content/Context;I)V
    //   91: putfield 112	android/support/v7/widget/AppCompatSpinner:mPopupContext	Landroid/content/Context;
    //   94: goto +26 -> 120
    //   97: getstatic 126	android/os/Build$VERSION:SDK_INT	I
    //   100: bipush 23
    //   102: if_icmpge +9 -> 111
    //   105: aload_1
    //   106: astore 5
    //   108: goto +6 -> 114
    //   111: aconst_null
    //   112: astore 5
    //   114: aload_0
    //   115: aload 5
    //   117: putfield 112	android/support/v7/widget/AppCompatSpinner:mPopupContext	Landroid/content/Context;
    //   120: aload_0
    //   121: getfield 112	android/support/v7/widget/AppCompatSpinner:mPopupContext	Landroid/content/Context;
    //   124: ifnull +251 -> 375
    //   127: iload 4
    //   129: istore 7
    //   131: iload 4
    //   133: iconst_m1
    //   134: if_icmpne +138 -> 272
    //   137: getstatic 55	android/support/v7/widget/AppCompatSpinner:ATTRS_ANDROID_SPINNERMODE	[I
    //   140: astore 5
    //   142: aload_1
    //   143: aload_2
    //   144: aload 5
    //   146: iload_3
    //   147: iconst_0
    //   148: invokevirtual 131	android/content/Context:obtainStyledAttributes	(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;
    //   151: astore 10
    //   153: aload 10
    //   155: astore 5
    //   157: aload 5
    //   159: astore 9
    //   161: aload 10
    //   163: iconst_0
    //   164: invokevirtual 137	android/content/res/TypedArray:hasValue	(I)Z
    //   167: istore 8
    //   169: iload 4
    //   171: istore 6
    //   173: iload 8
    //   175: ifeq +16 -> 191
    //   178: aload 5
    //   180: astore 9
    //   182: aload 10
    //   184: iconst_0
    //   185: iconst_0
    //   186: invokevirtual 140	android/content/res/TypedArray:getInt	(II)I
    //   189: istore 6
    //   191: iload 6
    //   193: istore 7
    //   195: aload 10
    //   197: ifnull +75 -> 272
    //   200: iload 6
    //   202: istore 4
    //   204: aload 5
    //   206: invokevirtual 143	android/content/res/TypedArray:recycle	()V
    //   209: iload 4
    //   211: istore 7
    //   213: goto +59 -> 272
    //   216: astore 10
    //   218: goto +15 -> 233
    //   221: astore_1
    //   222: aconst_null
    //   223: astore 9
    //   225: goto +35 -> 260
    //   228: astore 10
    //   230: aconst_null
    //   231: astore 5
    //   233: aload 5
    //   235: astore 9
    //   237: ldc 35
    //   239: ldc -111
    //   241: aload 10
    //   243: invokestatic 151	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   246: pop
    //   247: iload 4
    //   249: istore 7
    //   251: aload 5
    //   253: ifnull +19 -> 272
    //   256: goto -52 -> 204
    //   259: astore_1
    //   260: aload 9
    //   262: ifnull +8 -> 270
    //   265: aload 9
    //   267: invokevirtual 143	android/content/res/TypedArray:recycle	()V
    //   270: aload_1
    //   271: athrow
    //   272: iload 7
    //   274: iconst_1
    //   275: if_icmpne +100 -> 375
    //   278: new 13	android/support/v7/widget/AppCompatSpinner$DropdownPopup
    //   281: dup
    //   282: aload_0
    //   283: aload_0
    //   284: getfield 112	android/support/v7/widget/AppCompatSpinner:mPopupContext	Landroid/content/Context;
    //   287: aload_2
    //   288: iload_3
    //   289: invokespecial 154	android/support/v7/widget/AppCompatSpinner$DropdownPopup:<init>	(Landroid/support/v7/widget/AppCompatSpinner;Landroid/content/Context;Landroid/util/AttributeSet;I)V
    //   292: astore 5
    //   294: aload_0
    //   295: getfield 112	android/support/v7/widget/AppCompatSpinner:mPopupContext	Landroid/content/Context;
    //   298: aload_2
    //   299: getstatic 92	android/support/v7/appcompat/R$styleable:Spinner	[I
    //   302: iload_3
    //   303: iconst_0
    //   304: invokestatic 98	android/support/v7/widget/TintTypedArray:obtainStyledAttributes	(Landroid/content/Context;Landroid/util/AttributeSet;[III)Landroid/support/v7/widget/TintTypedArray;
    //   307: astore 9
    //   309: aload_0
    //   310: aload 9
    //   312: getstatic 157	android/support/v7/appcompat/R$styleable:Spinner_android_dropDownWidth	I
    //   315: bipush -2
    //   317: invokevirtual 160	android/support/v7/widget/TintTypedArray:getLayoutDimension	(II)I
    //   320: putfield 162	android/support/v7/widget/AppCompatSpinner:mDropDownWidth	I
    //   323: aload 5
    //   325: aload 9
    //   327: getstatic 165	android/support/v7/appcompat/R$styleable:Spinner_android_popupBackground	I
    //   330: invokevirtual 169	android/support/v7/widget/TintTypedArray:getDrawable	(I)Landroid/graphics/drawable/Drawable;
    //   333: invokevirtual 175	android/support/v7/widget/ListPopupWindow:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   336: aload 5
    //   338: aload 11
    //   340: getstatic 178	android/support/v7/appcompat/R$styleable:Spinner_android_prompt	I
    //   343: invokevirtual 182	android/support/v7/widget/TintTypedArray:getString	(I)Ljava/lang/String;
    //   346: invokevirtual 186	android/support/v7/widget/AppCompatSpinner$DropdownPopup:setPromptText	(Ljava/lang/CharSequence;)V
    //   349: aload 9
    //   351: invokevirtual 187	android/support/v7/widget/TintTypedArray:recycle	()V
    //   354: aload_0
    //   355: aload 5
    //   357: putfield 189	android/support/v7/widget/AppCompatSpinner:mPopup	Landroid/support/v7/widget/AppCompatSpinner$DropdownPopup;
    //   360: aload_0
    //   361: new 8	android/support/v7/widget/AppCompatSpinner$1
    //   364: dup
    //   365: aload_0
    //   366: aload_0
    //   367: aload 5
    //   369: invokespecial 192	android/support/v7/widget/AppCompatSpinner$1:<init>	(Landroid/support/v7/widget/AppCompatSpinner;Landroid/view/View;Landroid/support/v7/widget/AppCompatSpinner$DropdownPopup;)V
    //   372: putfield 194	android/support/v7/widget/AppCompatSpinner:mForwardingListener	Landroid/support/v7/widget/ForwardingListener;
    //   375: aload 11
    //   377: getstatic 197	android/support/v7/appcompat/R$styleable:Spinner_android_entries	I
    //   380: invokevirtual 201	android/support/v7/widget/TintTypedArray:getTextArray	(I)[Ljava/lang/CharSequence;
    //   383: astore 5
    //   385: aload 5
    //   387: ifnull +28 -> 415
    //   390: new 203	android/widget/ArrayAdapter
    //   393: dup
    //   394: aload_1
    //   395: ldc -52
    //   397: aload 5
    //   399: invokespecial 207	android/widget/ArrayAdapter:<init>	(Landroid/content/Context;I[Ljava/lang/Object;)V
    //   402: astore_1
    //   403: aload_1
    //   404: getstatic 212	android/support/v7/appcompat/R$layout:support_simple_spinner_dropdown_item	I
    //   407: invokevirtual 216	android/widget/ArrayAdapter:setDropDownViewResource	(I)V
    //   410: aload_0
    //   411: aload_1
    //   412: invokevirtual 220	android/support/v7/widget/AppCompatSpinner:setAdapter	(Landroid/widget/SpinnerAdapter;)V
    //   415: aload 11
    //   417: invokevirtual 187	android/support/v7/widget/TintTypedArray:recycle	()V
    //   420: aload_0
    //   421: iconst_1
    //   422: putfield 222	android/support/v7/widget/AppCompatSpinner:mPopupSet	Z
    //   425: aload_0
    //   426: getfield 224	android/support/v7/widget/AppCompatSpinner:mTempAdapter	Landroid/widget/SpinnerAdapter;
    //   429: ifnull +16 -> 445
    //   432: aload_0
    //   433: aload_0
    //   434: getfield 224	android/support/v7/widget/AppCompatSpinner:mTempAdapter	Landroid/widget/SpinnerAdapter;
    //   437: invokevirtual 220	android/support/v7/widget/AppCompatSpinner:setAdapter	(Landroid/widget/SpinnerAdapter;)V
    //   440: aload_0
    //   441: aconst_null
    //   442: putfield 224	android/support/v7/widget/AppCompatSpinner:mTempAdapter	Landroid/widget/SpinnerAdapter;
    //   445: aload_0
    //   446: getfield 105	android/support/v7/widget/AppCompatSpinner:mBackgroundTintHelper	Landroid/support/v7/widget/AppCompatBackgroundHelper;
    //   449: aload_2
    //   450: iload_3
    //   451: invokevirtual 228	android/support/v7/widget/AppCompatBackgroundHelper:loadFromAttributes	(Landroid/util/AttributeSet;I)V
    //   454: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	455	0	this	AppCompatSpinner
    //   0	455	1	paramContext	Context
    //   0	455	2	paramAttributeSet	AttributeSet
    //   0	455	3	paramInt1	int
    //   0	455	4	paramInt2	int
    //   0	455	5	paramTheme	Resources.Theme
    //   73	128	6	i	int
    //   129	147	7	j	int
    //   167	7	8	bool	boolean
    //   159	191	9	localObject	Object
    //   151	45	10	localTypedArray	android.content.res.TypedArray
    //   216	1	10	localException1	Exception
    //   228	14	10	localException2	Exception
    //   28	388	11	localTintTypedArray	TintTypedArray
    // Exception table:
    //   from	to	target	type
    //   161	169	216	java/lang/Exception
    //   182	191	216	java/lang/Exception
    //   142	153	221	java/lang/Throwable
    //   142	153	228	java/lang/Exception
    //   161	169	259	java/lang/Throwable
    //   182	191	259	java/lang/Throwable
    //   237	247	259	java/lang/Throwable
  }
  
  int compatMeasureContentWidth(SpinnerAdapter paramSpinnerAdapter, Drawable paramDrawable)
  {
    int k = 0;
    if (paramSpinnerAdapter == null) {
      return 0;
    }
    int i1 = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
    int i2 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
    int i = Math.max(0, getSelectedItemPosition());
    int i3 = Math.min(paramSpinnerAdapter.getCount(), i + 15);
    i = Math.max(0, i - (15 - (i3 - i)));
    Object localObject = null;
    int j = 0;
    while (i < i3)
    {
      int n = paramSpinnerAdapter.getItemViewType(i);
      int m = k;
      if (n != k)
      {
        localObject = null;
        m = n;
      }
      View localView = paramSpinnerAdapter.getView(i, (View)localObject, this);
      localObject = localView;
      if (localView.getLayoutParams() == null) {
        localView.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
      }
      localView.measure(i1, i2);
      j = Math.max(j, localView.getMeasuredWidth());
      i += 1;
      k = m;
    }
    if (paramDrawable != null)
    {
      paramDrawable.getPadding(mTempRect);
      return j + (mTempRect.left + mTempRect.right);
    }
    return j;
  }
  
  protected void drawableStateChanged()
  {
    super.drawableStateChanged();
    if (mBackgroundTintHelper != null) {
      mBackgroundTintHelper.applySupportBackgroundTint();
    }
  }
  
  public int getDropDownHorizontalOffset()
  {
    if (mPopup != null) {
      return mPopup.getHorizontalOffset();
    }
    if (Build.VERSION.SDK_INT >= 16) {
      return super.getDropDownHorizontalOffset();
    }
    return 0;
  }
  
  public int getDropDownVerticalOffset()
  {
    if (mPopup != null) {
      return mPopup.getVerticalOffset();
    }
    if (Build.VERSION.SDK_INT >= 16) {
      return super.getDropDownVerticalOffset();
    }
    return 0;
  }
  
  public int getDropDownWidth()
  {
    if (mPopup != null) {
      return mDropDownWidth;
    }
    if (Build.VERSION.SDK_INT >= 16) {
      return super.getDropDownWidth();
    }
    return 0;
  }
  
  public Drawable getPopupBackground()
  {
    if (mPopup != null) {
      return mPopup.getBackground();
    }
    if (Build.VERSION.SDK_INT >= 16) {
      return super.getPopupBackground();
    }
    return null;
  }
  
  public Context getPopupContext()
  {
    if (mPopup != null) {
      return mPopupContext;
    }
    if (Build.VERSION.SDK_INT >= 23) {
      return super.getPopupContext();
    }
    return null;
  }
  
  public CharSequence getPrompt()
  {
    if (mPopup != null) {
      return mPopup.getHintText();
    }
    return super.getPrompt();
  }
  
  public ColorStateList getSupportBackgroundTintList()
  {
    if (mBackgroundTintHelper != null) {
      return mBackgroundTintHelper.getSupportBackgroundTintList();
    }
    return null;
  }
  
  public PorterDuff.Mode getSupportBackgroundTintMode()
  {
    if (mBackgroundTintHelper != null) {
      return mBackgroundTintHelper.getSupportBackgroundTintMode();
    }
    return null;
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    if ((mPopup != null) && (mPopup.isShowing())) {
      mPopup.dismiss();
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    if ((mPopup != null) && (View.MeasureSpec.getMode(paramInt1) == Integer.MIN_VALUE)) {
      setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), compatMeasureContentWidth(getAdapter(), getBackground())), View.MeasureSpec.getSize(paramInt1)), getMeasuredHeight());
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((mForwardingListener != null) && (mForwardingListener.onTouch(this, paramMotionEvent))) {
      return true;
    }
    return super.onTouchEvent(paramMotionEvent);
  }
  
  public boolean performClick()
  {
    if (mPopup != null)
    {
      if (!mPopup.isShowing()) {
        mPopup.show();
      }
      return true;
    }
    return super.performClick();
  }
  
  public void setAdapter(SpinnerAdapter paramSpinnerAdapter)
  {
    if (!mPopupSet)
    {
      mTempAdapter = paramSpinnerAdapter;
      return;
    }
    super.setAdapter(paramSpinnerAdapter);
    if (mPopup != null)
    {
      Context localContext;
      if (mPopupContext == null) {
        localContext = getContext();
      } else {
        localContext = mPopupContext;
      }
      mPopup.setAdapter(new DropDownAdapter(paramSpinnerAdapter, localContext.getTheme()));
    }
  }
  
  public void setBackgroundDrawable(Drawable paramDrawable)
  {
    super.setBackgroundDrawable(paramDrawable);
    if (mBackgroundTintHelper != null) {
      mBackgroundTintHelper.onSetBackgroundDrawable(paramDrawable);
    }
  }
  
  public void setBackgroundResource(int paramInt)
  {
    super.setBackgroundResource(paramInt);
    if (mBackgroundTintHelper != null) {
      mBackgroundTintHelper.onSetBackgroundResource(paramInt);
    }
  }
  
  public void setDropDownHorizontalOffset(int paramInt)
  {
    if (mPopup != null)
    {
      mPopup.setHorizontalOffset(paramInt);
      return;
    }
    if (Build.VERSION.SDK_INT >= 16) {
      super.setDropDownHorizontalOffset(paramInt);
    }
  }
  
  public void setDropDownVerticalOffset(int paramInt)
  {
    if (mPopup != null)
    {
      mPopup.setVerticalOffset(paramInt);
      return;
    }
    if (Build.VERSION.SDK_INT >= 16) {
      super.setDropDownVerticalOffset(paramInt);
    }
  }
  
  public void setDropDownWidth(int paramInt)
  {
    if (mPopup != null)
    {
      mDropDownWidth = paramInt;
      return;
    }
    if (Build.VERSION.SDK_INT >= 16) {
      super.setDropDownWidth(paramInt);
    }
  }
  
  public void setPopupBackgroundDrawable(Drawable paramDrawable)
  {
    if (mPopup != null)
    {
      mPopup.setBackgroundDrawable(paramDrawable);
      return;
    }
    if (Build.VERSION.SDK_INT >= 16) {
      super.setPopupBackgroundDrawable(paramDrawable);
    }
  }
  
  public void setPopupBackgroundResource(int paramInt)
  {
    setPopupBackgroundDrawable(AppCompatResources.getDrawable(getPopupContext(), paramInt));
  }
  
  public void setPrompt(CharSequence paramCharSequence)
  {
    if (mPopup != null)
    {
      mPopup.setPromptText(paramCharSequence);
      return;
    }
    super.setPrompt(paramCharSequence);
  }
  
  public void setSupportBackgroundTintList(ColorStateList paramColorStateList)
  {
    if (mBackgroundTintHelper != null) {
      mBackgroundTintHelper.setSupportBackgroundTintList(paramColorStateList);
    }
  }
  
  public void setSupportBackgroundTintMode(PorterDuff.Mode paramMode)
  {
    if (mBackgroundTintHelper != null) {
      mBackgroundTintHelper.setSupportBackgroundTintMode(paramMode);
    }
  }
  
  private static class DropDownAdapter
    implements ListAdapter, SpinnerAdapter
  {
    private SpinnerAdapter mAdapter;
    private ListAdapter mListAdapter;
    
    public DropDownAdapter(SpinnerAdapter paramSpinnerAdapter, Resources.Theme paramTheme)
    {
      mAdapter = paramSpinnerAdapter;
      if ((paramSpinnerAdapter instanceof ListAdapter)) {
        mListAdapter = ((ListAdapter)paramSpinnerAdapter);
      }
      if (paramTheme != null) {
        if ((Build.VERSION.SDK_INT >= 23) && ((paramSpinnerAdapter instanceof android.widget.ThemedSpinnerAdapter)))
        {
          paramSpinnerAdapter = (android.widget.ThemedSpinnerAdapter)paramSpinnerAdapter;
          if (paramSpinnerAdapter.getDropDownViewTheme() != paramTheme) {
            paramSpinnerAdapter.setDropDownViewTheme(paramTheme);
          }
        }
        else if ((paramSpinnerAdapter instanceof ThemedSpinnerAdapter))
        {
          paramSpinnerAdapter = (ThemedSpinnerAdapter)paramSpinnerAdapter;
          if (paramSpinnerAdapter.getDropDownViewTheme() == null) {
            paramSpinnerAdapter.setDropDownViewTheme(paramTheme);
          }
        }
      }
    }
    
    public boolean areAllItemsEnabled()
    {
      ListAdapter localListAdapter = mListAdapter;
      if (localListAdapter != null) {
        return localListAdapter.areAllItemsEnabled();
      }
      return true;
    }
    
    public int getCount()
    {
      if (mAdapter == null) {
        return 0;
      }
      return mAdapter.getCount();
    }
    
    public View getDropDownView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if (mAdapter == null) {
        return null;
      }
      return mAdapter.getDropDownView(paramInt, paramView, paramViewGroup);
    }
    
    public Object getItem(int paramInt)
    {
      if (mAdapter == null) {
        return null;
      }
      return mAdapter.getItem(paramInt);
    }
    
    public long getItemId(int paramInt)
    {
      if (mAdapter == null) {
        return -1L;
      }
      return mAdapter.getItemId(paramInt);
    }
    
    public int getItemViewType(int paramInt)
    {
      return 0;
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      return getDropDownView(paramInt, paramView, paramViewGroup);
    }
    
    public int getViewTypeCount()
    {
      return 1;
    }
    
    public boolean hasStableIds()
    {
      return (mAdapter != null) && (mAdapter.hasStableIds());
    }
    
    public boolean isEmpty()
    {
      return getCount() == 0;
    }
    
    public boolean isEnabled(int paramInt)
    {
      ListAdapter localListAdapter = mListAdapter;
      if (localListAdapter != null) {
        return localListAdapter.isEnabled(paramInt);
      }
      return true;
    }
    
    public void registerDataSetObserver(DataSetObserver paramDataSetObserver)
    {
      if (mAdapter != null) {
        mAdapter.registerDataSetObserver(paramDataSetObserver);
      }
    }
    
    public void unregisterDataSetObserver(DataSetObserver paramDataSetObserver)
    {
      if (mAdapter != null) {
        mAdapter.unregisterDataSetObserver(paramDataSetObserver);
      }
    }
  }
  
  private class DropdownPopup
    extends ListPopupWindow
  {
    ListAdapter mAdapter;
    private CharSequence mHintText;
    private final Rect mVisibleRect = new Rect();
    
    public DropdownPopup(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
    {
      super(paramAttributeSet, paramInt);
      setAnchorView(AppCompatSpinner.this);
      setModal(true);
      setPromptPosition(0);
      setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          setSelection(paramAnonymousInt);
          if (getOnItemClickListener() != null) {
            performItemClick(paramAnonymousView, paramAnonymousInt, mAdapter.getItemId(paramAnonymousInt));
          }
          dismiss();
        }
      });
    }
    
    void computeContentWidth()
    {
      Object localObject = getBackground();
      int i = 0;
      if (localObject != null)
      {
        ((Drawable)localObject).getPadding(mTempRect);
        if (ViewUtils.isLayoutRtl(AppCompatSpinner.this)) {}
        for (i = mTempRect.right;; i = -mTempRect.left) {
          break;
        }
      }
      localObject = mTempRect;
      mTempRect.right = 0;
      left = 0;
      int n = getPaddingLeft();
      int i1 = getPaddingRight();
      int i2 = getWidth();
      if (mDropDownWidth == -2)
      {
        int k = compatMeasureContentWidth((SpinnerAdapter)mAdapter, getBackground());
        int j = k;
        int m = getContext().getResources().getDisplayMetrics().widthPixels - mTempRect.left - mTempRect.right;
        if (k > m) {
          j = m;
        }
        setContentWidth(Math.max(j, i2 - n - i1));
      }
      else if (mDropDownWidth == -1)
      {
        setContentWidth(i2 - n - i1);
      }
      else
      {
        setContentWidth(mDropDownWidth);
      }
      if (ViewUtils.isLayoutRtl(AppCompatSpinner.this)) {
        i += i2 - i1 - getWidth();
      } else {
        i += n;
      }
      setHorizontalOffset(i);
    }
    
    public CharSequence getHintText()
    {
      return mHintText;
    }
    
    boolean isVisibleToUser(View paramView)
    {
      return (ViewCompat.isAttachedToWindow(paramView)) && (paramView.getGlobalVisibleRect(mVisibleRect));
    }
    
    public void setAdapter(ListAdapter paramListAdapter)
    {
      super.setAdapter(paramListAdapter);
      mAdapter = paramListAdapter;
    }
    
    public void setPromptText(CharSequence paramCharSequence)
    {
      mHintText = paramCharSequence;
    }
    
    public void show()
    {
      boolean bool = isShowing();
      computeContentWidth();
      setInputMethodMode(2);
      super.show();
      getListView().setChoiceMode(1);
      setSelection(getSelectedItemPosition());
      if (bool) {
        return;
      }
      ViewTreeObserver localViewTreeObserver = getViewTreeObserver();
      if (localViewTreeObserver != null)
      {
        final ViewTreeObserver.OnGlobalLayoutListener local2 = new ViewTreeObserver.OnGlobalLayoutListener()
        {
          public void onGlobalLayout()
          {
            if (!isVisibleToUser(AppCompatSpinner.this))
            {
              dismiss();
              return;
            }
            computeContentWidth();
            AppCompatSpinner.DropdownPopup.this.show();
          }
        };
        localViewTreeObserver.addOnGlobalLayoutListener(local2);
        setOnDismissListener(new PopupWindow.OnDismissListener()
        {
          public void onDismiss()
          {
            ViewTreeObserver localViewTreeObserver = getViewTreeObserver();
            if (localViewTreeObserver != null) {
              localViewTreeObserver.removeGlobalOnLayoutListener(local2);
            }
          }
        });
      }
    }
  }
}
