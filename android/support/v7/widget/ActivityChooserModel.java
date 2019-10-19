package android.support.v7.widget;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.DataSetObservable;
import android.os.AsyncTask;
import android.text.TextUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ActivityChooserModel
  extends DataSetObservable
{
  static final String ATTRIBUTE_ACTIVITY = "activity";
  static final String ATTRIBUTE_TIME = "time";
  static final String ATTRIBUTE_WEIGHT = "weight";
  static final boolean DEBUG = false;
  private static final int DEFAULT_ACTIVITY_INFLATION = 5;
  private static final float DEFAULT_HISTORICAL_RECORD_WEIGHT = 1.0F;
  public static final String DEFAULT_HISTORY_FILE_NAME = "activity_choser_model_history.xml";
  public static final int DEFAULT_HISTORY_MAX_LENGTH = 50;
  private static final String HISTORY_FILE_EXTENSION = ".xml";
  private static final int INVALID_INDEX = -1;
  static final String LOG_TAG = "ActivityChooserModel";
  static final String TAG_HISTORICAL_RECORD = "historical-record";
  static final String TAG_HISTORICAL_RECORDS = "historical-records";
  private static final Map<String, ActivityChooserModel> sDataModelRegistry = new HashMap();
  private static final Object sRegistryLock = new Object();
  private final List<ActivityResolveInfo> mActivities = new ArrayList();
  private OnChooseActivityListener mActivityChoserModelPolicy;
  private ActivitySorter mActivitySorter = new DefaultSorter();
  boolean mCanReadHistoricalData = true;
  final Context mContext;
  private final List<HistoricalRecord> mHistoricalRecords = new ArrayList();
  private boolean mHistoricalRecordsChanged = true;
  final String mHistoryFileName;
  private int mHistoryMaxSize = 50;
  private final Object mInstanceLock = new Object();
  private Intent mIntent;
  private boolean mReadShareHistoryCalled = false;
  private boolean mReloadActivities = false;
  
  private ActivityChooserModel(Context paramContext, String paramString)
  {
    mContext = paramContext.getApplicationContext();
    if ((!TextUtils.isEmpty(paramString)) && (!paramString.endsWith(".xml")))
    {
      paramContext = new StringBuilder();
      paramContext.append(paramString);
      paramContext.append(".xml");
      mHistoryFileName = paramContext.toString();
      return;
    }
    mHistoryFileName = paramString;
  }
  
  private boolean addHistoricalRecord(HistoricalRecord paramHistoricalRecord)
  {
    boolean bool = mHistoricalRecords.add(paramHistoricalRecord);
    if (bool)
    {
      mHistoricalRecordsChanged = true;
      pruneExcessiveHistoricalRecordsIfNeeded();
      persistHistoricalDataIfNeeded();
      sortActivitiesIfNeeded();
      notifyChanged();
    }
    return bool;
  }
  
  private void ensureConsistentState()
  {
    boolean bool1 = loadActivitiesIfNeeded();
    boolean bool2 = readHistoricalDataIfNeeded();
    pruneExcessiveHistoricalRecordsIfNeeded();
    if ((bool1 | bool2))
    {
      sortActivitiesIfNeeded();
      notifyChanged();
    }
  }
  
  public static ActivityChooserModel get(Context paramContext, String paramString)
  {
    Object localObject = sRegistryLock;
    try
    {
      ActivityChooserModel localActivityChooserModel2 = (ActivityChooserModel)sDataModelRegistry.get(paramString);
      ActivityChooserModel localActivityChooserModel1 = localActivityChooserModel2;
      if (localActivityChooserModel2 == null)
      {
        localActivityChooserModel1 = new ActivityChooserModel(paramContext, paramString);
        sDataModelRegistry.put(paramString, localActivityChooserModel1);
      }
      return localActivityChooserModel1;
    }
    catch (Throwable paramContext)
    {
      throw paramContext;
    }
  }
  
  private boolean loadActivitiesIfNeeded()
  {
    boolean bool = mReloadActivities;
    int i = 0;
    if ((bool) && (mIntent != null))
    {
      mReloadActivities = false;
      mActivities.clear();
      List localList = mContext.getPackageManager().queryIntentActivities(mIntent, 0);
      int j = localList.size();
      while (i < j)
      {
        ResolveInfo localResolveInfo = (ResolveInfo)localList.get(i);
        mActivities.add(new ActivityResolveInfo(localResolveInfo));
        i += 1;
      }
      return true;
    }
    return false;
  }
  
  private void persistHistoricalDataIfNeeded()
  {
    if (mReadShareHistoryCalled)
    {
      if (!mHistoricalRecordsChanged) {
        return;
      }
      mHistoricalRecordsChanged = false;
      if (!TextUtils.isEmpty(mHistoryFileName)) {
        new PersistHistoryAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[] { new ArrayList(mHistoricalRecords), mHistoryFileName });
      }
    }
    else
    {
      throw new IllegalStateException("No preceding call to #readHistoricalData");
    }
  }
  
  private void pruneExcessiveHistoricalRecordsIfNeeded()
  {
    int j = mHistoricalRecords.size() - mHistoryMaxSize;
    if (j <= 0) {
      return;
    }
    mHistoricalRecordsChanged = true;
    int i = 0;
    while (i < j)
    {
      HistoricalRecord localHistoricalRecord = (HistoricalRecord)mHistoricalRecords.remove(0);
      i += 1;
    }
  }
  
  private boolean readHistoricalDataIfNeeded()
  {
    if ((mCanReadHistoricalData) && (mHistoricalRecordsChanged) && (!TextUtils.isEmpty(mHistoryFileName)))
    {
      mCanReadHistoricalData = false;
      mReadShareHistoryCalled = true;
      readHistoricalDataImpl();
      return true;
    }
    return false;
  }
  
  /* Error */
  private void readHistoricalDataImpl()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 135	android/support/v7/widget/ActivityChooserModel:mContext	Landroid/content/Context;
    //   4: astore 6
    //   6: aload_0
    //   7: getfield 160	android/support/v7/widget/ActivityChooserModel:mHistoryFileName	Ljava/lang/String;
    //   10: astore 7
    //   12: aload 6
    //   14: aload 7
    //   16: invokevirtual 269	android/content/Context:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
    //   19: astore 6
    //   21: invokestatic 275	android/util/Xml:newPullParser	()Lorg/xmlpull/v1/XmlPullParser;
    //   24: astore 7
    //   26: aload 7
    //   28: aload 6
    //   30: ldc_w 277
    //   33: invokeinterface 283 3 0
    //   38: iconst_0
    //   39: istore_2
    //   40: iload_2
    //   41: iconst_1
    //   42: if_icmpeq +19 -> 61
    //   45: iload_2
    //   46: iconst_2
    //   47: if_icmpeq +14 -> 61
    //   50: aload 7
    //   52: invokeinterface 286 1 0
    //   57: istore_2
    //   58: goto -18 -> 40
    //   61: ldc 63
    //   63: aload 7
    //   65: invokeinterface 289 1 0
    //   70: invokevirtual 292	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   73: istore_3
    //   74: iload_3
    //   75: ifeq +153 -> 228
    //   78: aload_0
    //   79: getfield 114	android/support/v7/widget/ActivityChooserModel:mHistoricalRecords	Ljava/util/List;
    //   82: astore 8
    //   84: aload 8
    //   86: invokeinterface 208 1 0
    //   91: aload 7
    //   93: invokeinterface 286 1 0
    //   98: istore_2
    //   99: iload_2
    //   100: iconst_1
    //   101: if_icmpne +14 -> 115
    //   104: aload 6
    //   106: ifnull +254 -> 360
    //   109: aload 6
    //   111: invokevirtual 297	java/io/FileInputStream:close	()V
    //   114: return
    //   115: iload_2
    //   116: iconst_3
    //   117: if_icmpeq -26 -> 91
    //   120: iload_2
    //   121: iconst_4
    //   122: if_icmpne +6 -> 128
    //   125: goto -34 -> 91
    //   128: aload 7
    //   130: invokeinterface 289 1 0
    //   135: astore 9
    //   137: ldc 60
    //   139: aload 9
    //   141: invokevirtual 292	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   144: istore_3
    //   145: iload_3
    //   146: ifeq +67 -> 213
    //   149: aload 7
    //   151: aconst_null
    //   152: ldc 29
    //   154: invokeinterface 301 3 0
    //   159: astore 9
    //   161: aload 7
    //   163: aconst_null
    //   164: ldc 32
    //   166: invokeinterface 301 3 0
    //   171: invokestatic 307	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   174: lstore 4
    //   176: aload 7
    //   178: aconst_null
    //   179: ldc 35
    //   181: invokeinterface 301 3 0
    //   186: invokestatic 313	java/lang/Float:parseFloat	(Ljava/lang/String;)F
    //   189: fstore_1
    //   190: aload 8
    //   192: new 18	android/support/v7/widget/ActivityChooserModel$HistoricalRecord
    //   195: dup
    //   196: aload 9
    //   198: lload 4
    //   200: fload_1
    //   201: invokespecial 316	android/support/v7/widget/ActivityChooserModel$HistoricalRecord:<init>	(Ljava/lang/String;JF)V
    //   204: invokeinterface 168 2 0
    //   209: pop
    //   210: goto -119 -> 91
    //   213: new 263	org/xmlpull/v1/XmlPullParserException
    //   216: dup
    //   217: ldc_w 318
    //   220: invokespecial 319	org/xmlpull/v1/XmlPullParserException:<init>	(Ljava/lang/String;)V
    //   223: astore 7
    //   225: aload 7
    //   227: athrow
    //   228: new 263	org/xmlpull/v1/XmlPullParserException
    //   231: dup
    //   232: ldc_w 321
    //   235: invokespecial 319	org/xmlpull/v1/XmlPullParserException:<init>	(Ljava/lang/String;)V
    //   238: astore 7
    //   240: aload 7
    //   242: athrow
    //   243: astore 7
    //   245: goto +116 -> 361
    //   248: astore 7
    //   250: getstatic 323	android/support/v7/widget/ActivityChooserModel:LOG_TAG	Ljava/lang/String;
    //   253: astore 8
    //   255: new 149	java/lang/StringBuilder
    //   258: dup
    //   259: invokespecial 150	java/lang/StringBuilder:<init>	()V
    //   262: astore 9
    //   264: aload 9
    //   266: ldc_w 325
    //   269: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   272: pop
    //   273: aload 9
    //   275: aload_0
    //   276: getfield 160	android/support/v7/widget/ActivityChooserModel:mHistoryFileName	Ljava/lang/String;
    //   279: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   282: pop
    //   283: aload 8
    //   285: aload 9
    //   287: invokevirtual 158	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   290: aload 7
    //   292: invokestatic 331	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   295: pop
    //   296: aload 6
    //   298: ifnull +87 -> 385
    //   301: goto -192 -> 109
    //   304: astore 7
    //   306: getstatic 323	android/support/v7/widget/ActivityChooserModel:LOG_TAG	Ljava/lang/String;
    //   309: astore 8
    //   311: new 149	java/lang/StringBuilder
    //   314: dup
    //   315: invokespecial 150	java/lang/StringBuilder:<init>	()V
    //   318: astore 9
    //   320: aload 9
    //   322: ldc_w 325
    //   325: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   328: pop
    //   329: aload 9
    //   331: aload_0
    //   332: getfield 160	android/support/v7/widget/ActivityChooserModel:mHistoryFileName	Ljava/lang/String;
    //   335: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   338: pop
    //   339: aload 8
    //   341: aload 9
    //   343: invokevirtual 158	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   346: aload 7
    //   348: invokestatic 331	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   351: pop
    //   352: aload 6
    //   354: ifnull +31 -> 385
    //   357: goto -248 -> 109
    //   360: return
    //   361: aload 6
    //   363: ifnull +8 -> 371
    //   366: aload 6
    //   368: invokevirtual 297	java/io/FileInputStream:close	()V
    //   371: aload 7
    //   373: athrow
    //   374: astore 6
    //   376: return
    //   377: astore 6
    //   379: return
    //   380: astore 6
    //   382: goto -11 -> 371
    //   385: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	386	0	this	ActivityChooserModel
    //   189	12	1	f	float
    //   39	84	2	i	int
    //   73	73	3	bool	boolean
    //   174	25	4	l	long
    //   4	363	6	localObject1	Object
    //   374	1	6	localFileNotFoundException	java.io.FileNotFoundException
    //   377	1	6	localIOException1	java.io.IOException
    //   380	1	6	localIOException2	java.io.IOException
    //   10	231	7	localObject2	Object
    //   243	1	7	localThrowable	Throwable
    //   248	43	7	localIOException3	java.io.IOException
    //   304	68	7	localXmlPullParserException	org.xmlpull.v1.XmlPullParserException
    //   82	258	8	localObject3	Object
    //   135	207	9	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   21	38	243	java/lang/Throwable
    //   50	58	243	java/lang/Throwable
    //   61	74	243	java/lang/Throwable
    //   84	91	243	java/lang/Throwable
    //   91	99	243	java/lang/Throwable
    //   128	137	243	java/lang/Throwable
    //   137	145	243	java/lang/Throwable
    //   149	190	243	java/lang/Throwable
    //   190	210	243	java/lang/Throwable
    //   213	225	243	java/lang/Throwable
    //   225	228	243	java/lang/Throwable
    //   228	240	243	java/lang/Throwable
    //   240	243	243	java/lang/Throwable
    //   250	296	243	java/lang/Throwable
    //   306	352	243	java/lang/Throwable
    //   21	38	248	java/io/IOException
    //   50	58	248	java/io/IOException
    //   61	74	248	java/io/IOException
    //   84	91	248	java/io/IOException
    //   91	99	248	java/io/IOException
    //   128	137	248	java/io/IOException
    //   137	145	248	java/io/IOException
    //   149	190	248	java/io/IOException
    //   190	210	248	java/io/IOException
    //   213	225	248	java/io/IOException
    //   228	240	248	java/io/IOException
    //   21	38	304	org/xmlpull/v1/XmlPullParserException
    //   50	58	304	org/xmlpull/v1/XmlPullParserException
    //   61	74	304	org/xmlpull/v1/XmlPullParserException
    //   84	91	304	org/xmlpull/v1/XmlPullParserException
    //   91	99	304	org/xmlpull/v1/XmlPullParserException
    //   128	137	304	org/xmlpull/v1/XmlPullParserException
    //   137	145	304	org/xmlpull/v1/XmlPullParserException
    //   149	190	304	org/xmlpull/v1/XmlPullParserException
    //   190	210	304	org/xmlpull/v1/XmlPullParserException
    //   213	225	304	org/xmlpull/v1/XmlPullParserException
    //   225	228	304	org/xmlpull/v1/XmlPullParserException
    //   228	240	304	org/xmlpull/v1/XmlPullParserException
    //   240	243	304	org/xmlpull/v1/XmlPullParserException
    //   12	21	374	java/io/FileNotFoundException
    //   109	114	377	java/io/IOException
    //   366	371	380	java/io/IOException
  }
  
  private boolean sortActivitiesIfNeeded()
  {
    if ((mActivitySorter != null) && (mIntent != null) && (!mActivities.isEmpty()) && (!mHistoricalRecords.isEmpty()))
    {
      mActivitySorter.sort(mIntent, mActivities, Collections.unmodifiableList(mHistoricalRecords));
      return true;
    }
    return false;
  }
  
  public Intent chooseActivity(int paramInt)
  {
    Object localObject1 = mInstanceLock;
    try
    {
      if (mIntent == null) {
        return null;
      }
      ensureConsistentState();
      Object localObject2 = (ActivityResolveInfo)mActivities.get(paramInt);
      localObject2 = new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
      Intent localIntent1 = new Intent(mIntent);
      localIntent1.setComponent((ComponentName)localObject2);
      if (mActivityChoserModelPolicy != null)
      {
        Intent localIntent2 = new Intent(localIntent1);
        if (mActivityChoserModelPolicy.onChooseActivity(this, localIntent2)) {
          return null;
        }
      }
      addHistoricalRecord(new HistoricalRecord((ComponentName)localObject2, System.currentTimeMillis(), 1.0F));
      return localIntent1;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public ResolveInfo getActivity(int paramInt)
  {
    Object localObject = mInstanceLock;
    try
    {
      ensureConsistentState();
      ResolveInfo localResolveInfo = mActivities.get(paramInt)).resolveInfo;
      return localResolveInfo;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public int getActivityCount()
  {
    Object localObject = mInstanceLock;
    try
    {
      ensureConsistentState();
      int i = mActivities.size();
      return i;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public int getActivityIndex(ResolveInfo paramResolveInfo)
  {
    Object localObject = mInstanceLock;
    try
    {
      ensureConsistentState();
      List localList = mActivities;
      int j = localList.size();
      int i = 0;
      while (i < j)
      {
        if (getresolveInfo == paramResolveInfo) {
          return i;
        }
        i += 1;
      }
      return -1;
    }
    catch (Throwable paramResolveInfo)
    {
      throw paramResolveInfo;
    }
  }
  
  public ResolveInfo getDefaultActivity()
  {
    Object localObject = mInstanceLock;
    try
    {
      ensureConsistentState();
      if (!mActivities.isEmpty())
      {
        ResolveInfo localResolveInfo = mActivities.get(0)).resolveInfo;
        return localResolveInfo;
      }
      return null;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public int getHistoryMaxSize()
  {
    Object localObject = mInstanceLock;
    try
    {
      int i = mHistoryMaxSize;
      return i;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public int getHistorySize()
  {
    Object localObject = mInstanceLock;
    try
    {
      ensureConsistentState();
      int i = mHistoricalRecords.size();
      return i;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public Intent getIntent()
  {
    Object localObject = mInstanceLock;
    try
    {
      Intent localIntent = mIntent;
      return localIntent;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public void setActivitySorter(ActivitySorter paramActivitySorter)
  {
    Object localObject = mInstanceLock;
    try
    {
      if (mActivitySorter == paramActivitySorter) {
        return;
      }
      mActivitySorter = paramActivitySorter;
      if (sortActivitiesIfNeeded()) {
        notifyChanged();
      }
      return;
    }
    catch (Throwable paramActivitySorter)
    {
      throw paramActivitySorter;
    }
  }
  
  public void setDefaultActivity(int paramInt)
  {
    Object localObject = mInstanceLock;
    for (;;)
    {
      try
      {
        ensureConsistentState();
        ActivityResolveInfo localActivityResolveInfo1 = (ActivityResolveInfo)mActivities.get(paramInt);
        ActivityResolveInfo localActivityResolveInfo2 = (ActivityResolveInfo)mActivities.get(0);
        if (localActivityResolveInfo2 != null)
        {
          f = weight - weight + 5.0F;
          addHistoricalRecord(new HistoricalRecord(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name), System.currentTimeMillis(), f));
          return;
        }
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
      float f = 1.0F;
    }
  }
  
  public void setHistoryMaxSize(int paramInt)
  {
    Object localObject = mInstanceLock;
    try
    {
      if (mHistoryMaxSize == paramInt) {
        return;
      }
      mHistoryMaxSize = paramInt;
      pruneExcessiveHistoricalRecordsIfNeeded();
      if (sortActivitiesIfNeeded()) {
        notifyChanged();
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public void setIntent(Intent paramIntent)
  {
    Object localObject = mInstanceLock;
    try
    {
      if (mIntent == paramIntent) {
        return;
      }
      mIntent = paramIntent;
      mReloadActivities = true;
      ensureConsistentState();
      return;
    }
    catch (Throwable paramIntent)
    {
      throw paramIntent;
    }
  }
  
  public void setOnChooseActivityListener(OnChooseActivityListener paramOnChooseActivityListener)
  {
    Object localObject = mInstanceLock;
    try
    {
      mActivityChoserModelPolicy = paramOnChooseActivityListener;
      return;
    }
    catch (Throwable paramOnChooseActivityListener)
    {
      throw paramOnChooseActivityListener;
    }
  }
  
  public static abstract interface ActivityChooserModelClient
  {
    public abstract void setActivityChooserModel(ActivityChooserModel paramActivityChooserModel);
  }
  
  public static final class ActivityResolveInfo
    implements Comparable<ActivityResolveInfo>
  {
    public final ResolveInfo resolveInfo;
    public float weight;
    
    public ActivityResolveInfo(ResolveInfo paramResolveInfo)
    {
      resolveInfo = paramResolveInfo;
    }
    
    public int compareTo(ActivityResolveInfo paramActivityResolveInfo)
    {
      return Float.floatToIntBits(weight) - Float.floatToIntBits(weight);
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
      paramObject = (ActivityResolveInfo)paramObject;
      return Float.floatToIntBits(weight) == Float.floatToIntBits(weight);
    }
    
    public int hashCode()
    {
      return Float.floatToIntBits(weight) + 31;
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("[");
      localStringBuilder.append("resolveInfo:");
      localStringBuilder.append(resolveInfo.toString());
      localStringBuilder.append("; weight:");
      localStringBuilder.append(new BigDecimal(weight));
      localStringBuilder.append("]");
      return localStringBuilder.toString();
    }
  }
  
  public static abstract interface ActivitySorter
  {
    public abstract void sort(Intent paramIntent, List paramList1, List paramList2);
  }
  
  private static final class DefaultSorter
    implements ActivityChooserModel.ActivitySorter
  {
    private static final float WEIGHT_DECAY_COEFFICIENT = 0.95F;
    private final Map<ComponentName, ActivityChooserModel.ActivityResolveInfo> mPackageNameToActivityMap = new HashMap();
    
    DefaultSorter() {}
    
    public void sort(Intent paramIntent, List paramList1, List paramList2)
    {
      paramIntent = mPackageNameToActivityMap;
      paramIntent.clear();
      int j = paramList1.size();
      int i = 0;
      Object localObject;
      while (i < j)
      {
        localObject = (ActivityChooserModel.ActivityResolveInfo)paramList1.get(i);
        weight = 0.0F;
        paramIntent.put(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name), localObject);
        i += 1;
      }
      i = paramList2.size() - 1;
      float f2;
      for (float f1 = 1.0F; i >= 0; f1 = f2)
      {
        localObject = (ActivityChooserModel.HistoricalRecord)paramList2.get(i);
        ActivityChooserModel.ActivityResolveInfo localActivityResolveInfo = (ActivityChooserModel.ActivityResolveInfo)paramIntent.get(activity);
        f2 = f1;
        if (localActivityResolveInfo != null)
        {
          weight += weight * f1;
          f2 = f1 * 0.95F;
        }
        i -= 1;
      }
      Collections.sort(paramList1);
    }
  }
  
  public static final class HistoricalRecord
  {
    public final ComponentName activity;
    public final long time;
    public final float weight;
    
    public HistoricalRecord(ComponentName paramComponentName, long paramLong, float paramFloat)
    {
      activity = paramComponentName;
      time = paramLong;
      weight = paramFloat;
    }
    
    public HistoricalRecord(String paramString, long paramLong, float paramFloat)
    {
      this(ComponentName.unflattenFromString(paramString), paramLong, paramFloat);
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
      paramObject = (HistoricalRecord)paramObject;
      if (activity == null)
      {
        if (activity != null) {
          return false;
        }
      }
      else if (!activity.equals(activity)) {
        return false;
      }
      if (time != time) {
        return false;
      }
      return Float.floatToIntBits(weight) == Float.floatToIntBits(weight);
    }
    
    public int hashCode()
    {
      int i;
      if (activity == null) {
        i = 0;
      } else {
        i = activity.hashCode();
      }
      return ((i + 31) * 31 + (int)(time ^ time >>> 32)) * 31 + Float.floatToIntBits(weight);
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("[");
      localStringBuilder.append("; activity:");
      localStringBuilder.append(activity);
      localStringBuilder.append("; time:");
      localStringBuilder.append(time);
      localStringBuilder.append("; weight:");
      localStringBuilder.append(new BigDecimal(weight));
      localStringBuilder.append("]");
      return localStringBuilder.toString();
    }
  }
  
  public static abstract interface OnChooseActivityListener
  {
    public abstract boolean onChooseActivity(ActivityChooserModel paramActivityChooserModel, Intent paramIntent);
  }
  
  private final class PersistHistoryAsyncTask
    extends AsyncTask<Object, Void, Void>
  {
    PersistHistoryAsyncTask() {}
    
    /* Error */
    public Void doInBackground(Object... paramVarArgs)
    {
      // Byte code:
      //   0: aload_1
      //   1: iconst_0
      //   2: aaload
      //   3: checkcast 35	java/util/List
      //   6: astore 7
      //   8: aload_1
      //   9: iconst_1
      //   10: aaload
      //   11: checkcast 37	java/lang/String
      //   14: astore 8
      //   16: aload_0
      //   17: getfield 14	android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask:this$0	Landroid/support/v7/widget/ActivityChooserModel;
      //   20: getfield 41	android/support/v7/widget/ActivityChooserModel:mContext	Landroid/content/Context;
      //   23: astore_1
      //   24: aload_1
      //   25: aload 8
      //   27: iconst_0
      //   28: invokevirtual 47	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
      //   31: astore_1
      //   32: invokestatic 53	android/util/Xml:newSerializer	()Lorg/xmlpull/v1/XmlSerializer;
      //   35: astore 8
      //   37: aload 8
      //   39: aload_1
      //   40: aconst_null
      //   41: invokeinterface 59 3 0
      //   46: aload 8
      //   48: ldc 61
      //   50: iconst_1
      //   51: invokestatic 67	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   54: invokeinterface 71 3 0
      //   59: aload 8
      //   61: aconst_null
      //   62: ldc 73
      //   64: invokeinterface 77 3 0
      //   69: pop
      //   70: aload 7
      //   72: invokeinterface 81 1 0
      //   77: istore 4
      //   79: iconst_0
      //   80: istore_3
      //   81: iload_3
      //   82: iload 4
      //   84: if_icmpge +116 -> 200
      //   87: aload 7
      //   89: iconst_0
      //   90: invokeinterface 85 2 0
      //   95: astore 9
      //   97: aload 9
      //   99: checkcast 87	android/support/v7/widget/ActivityChooserModel$HistoricalRecord
      //   102: astore 9
      //   104: aload 8
      //   106: aconst_null
      //   107: ldc 89
      //   109: invokeinterface 77 3 0
      //   114: pop
      //   115: aload 9
      //   117: getfield 93	android/support/v7/widget/ActivityChooserModel$HistoricalRecord:activity	Landroid/content/ComponentName;
      //   120: astore 10
      //   122: aload 8
      //   124: aconst_null
      //   125: ldc 94
      //   127: aload 10
      //   129: invokevirtual 100	android/content/ComponentName:flattenToString	()Ljava/lang/String;
      //   132: invokeinterface 104 4 0
      //   137: pop
      //   138: aload 9
      //   140: getfield 108	android/support/v7/widget/ActivityChooserModel$HistoricalRecord:time	J
      //   143: lstore 5
      //   145: aload 8
      //   147: aconst_null
      //   148: ldc 109
      //   150: lload 5
      //   152: invokestatic 112	java/lang/String:valueOf	(J)Ljava/lang/String;
      //   155: invokeinterface 104 4 0
      //   160: pop
      //   161: aload 9
      //   163: getfield 116	android/support/v7/widget/ActivityChooserModel$HistoricalRecord:weight	F
      //   166: fstore_2
      //   167: aload 8
      //   169: aconst_null
      //   170: ldc 117
      //   172: fload_2
      //   173: invokestatic 120	java/lang/String:valueOf	(F)Ljava/lang/String;
      //   176: invokeinterface 104 4 0
      //   181: pop
      //   182: aload 8
      //   184: aconst_null
      //   185: ldc 89
      //   187: invokeinterface 123 3 0
      //   192: pop
      //   193: iload_3
      //   194: iconst_1
      //   195: iadd
      //   196: istore_3
      //   197: goto -116 -> 81
      //   200: aload 8
      //   202: aconst_null
      //   203: ldc 73
      //   205: invokeinterface 123 3 0
      //   210: pop
      //   211: aload 8
      //   213: invokeinterface 126 1 0
      //   218: aload_0
      //   219: getfield 14	android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask:this$0	Landroid/support/v7/widget/ActivityChooserModel;
      //   222: iconst_1
      //   223: putfield 130	android/support/v7/widget/ActivityChooserModel:mCanReadHistoricalData	Z
      //   226: aload_1
      //   227: ifnull +209 -> 436
      //   230: aload_1
      //   231: invokevirtual 135	java/io/FileOutputStream:close	()V
      //   234: aconst_null
      //   235: areturn
      //   236: astore 7
      //   238: goto +200 -> 438
      //   241: astore 7
      //   243: getstatic 139	android/support/v7/widget/ActivityChooserModel:LOG_TAG	Ljava/lang/String;
      //   246: astore 8
      //   248: new 141	java/lang/StringBuilder
      //   251: dup
      //   252: invokespecial 142	java/lang/StringBuilder:<init>	()V
      //   255: astore 9
      //   257: aload 9
      //   259: ldc -112
      //   261: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   264: pop
      //   265: aload 9
      //   267: aload_0
      //   268: getfield 14	android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask:this$0	Landroid/support/v7/widget/ActivityChooserModel;
      //   271: getfield 151	android/support/v7/widget/ActivityChooserModel:mHistoryFileName	Ljava/lang/String;
      //   274: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   277: pop
      //   278: aload 8
      //   280: aload 9
      //   282: invokevirtual 154	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   285: aload 7
      //   287: invokestatic 160	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   290: pop
      //   291: aload_0
      //   292: getfield 14	android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask:this$0	Landroid/support/v7/widget/ActivityChooserModel;
      //   295: iconst_1
      //   296: putfield 130	android/support/v7/widget/ActivityChooserModel:mCanReadHistoricalData	Z
      //   299: aload_1
      //   300: ifnull +209 -> 509
      //   303: goto -73 -> 230
      //   306: astore 7
      //   308: getstatic 139	android/support/v7/widget/ActivityChooserModel:LOG_TAG	Ljava/lang/String;
      //   311: astore 8
      //   313: new 141	java/lang/StringBuilder
      //   316: dup
      //   317: invokespecial 142	java/lang/StringBuilder:<init>	()V
      //   320: astore 9
      //   322: aload 9
      //   324: ldc -112
      //   326: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   329: pop
      //   330: aload 9
      //   332: aload_0
      //   333: getfield 14	android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask:this$0	Landroid/support/v7/widget/ActivityChooserModel;
      //   336: getfield 151	android/support/v7/widget/ActivityChooserModel:mHistoryFileName	Ljava/lang/String;
      //   339: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   342: pop
      //   343: aload 8
      //   345: aload 9
      //   347: invokevirtual 154	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   350: aload 7
      //   352: invokestatic 160	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   355: pop
      //   356: aload_0
      //   357: getfield 14	android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask:this$0	Landroid/support/v7/widget/ActivityChooserModel;
      //   360: iconst_1
      //   361: putfield 130	android/support/v7/widget/ActivityChooserModel:mCanReadHistoricalData	Z
      //   364: aload_1
      //   365: ifnull +144 -> 509
      //   368: goto -138 -> 230
      //   371: astore 7
      //   373: getstatic 139	android/support/v7/widget/ActivityChooserModel:LOG_TAG	Ljava/lang/String;
      //   376: astore 8
      //   378: new 141	java/lang/StringBuilder
      //   381: dup
      //   382: invokespecial 142	java/lang/StringBuilder:<init>	()V
      //   385: astore 9
      //   387: aload 9
      //   389: ldc -112
      //   391: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   394: pop
      //   395: aload 9
      //   397: aload_0
      //   398: getfield 14	android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask:this$0	Landroid/support/v7/widget/ActivityChooserModel;
      //   401: getfield 151	android/support/v7/widget/ActivityChooserModel:mHistoryFileName	Ljava/lang/String;
      //   404: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   407: pop
      //   408: aload 8
      //   410: aload 9
      //   412: invokevirtual 154	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   415: aload 7
      //   417: invokestatic 160	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   420: pop
      //   421: aload_0
      //   422: getfield 14	android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask:this$0	Landroid/support/v7/widget/ActivityChooserModel;
      //   425: iconst_1
      //   426: putfield 130	android/support/v7/widget/ActivityChooserModel:mCanReadHistoricalData	Z
      //   429: aload_1
      //   430: ifnull +79 -> 509
      //   433: goto -203 -> 230
      //   436: aconst_null
      //   437: areturn
      //   438: aload_0
      //   439: getfield 14	android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask:this$0	Landroid/support/v7/widget/ActivityChooserModel;
      //   442: iconst_1
      //   443: putfield 130	android/support/v7/widget/ActivityChooserModel:mCanReadHistoricalData	Z
      //   446: aload_1
      //   447: ifnull +7 -> 454
      //   450: aload_1
      //   451: invokevirtual 135	java/io/FileOutputStream:close	()V
      //   454: aload 7
      //   456: athrow
      //   457: astore_1
      //   458: getstatic 139	android/support/v7/widget/ActivityChooserModel:LOG_TAG	Ljava/lang/String;
      //   461: astore 7
      //   463: new 141	java/lang/StringBuilder
      //   466: dup
      //   467: invokespecial 142	java/lang/StringBuilder:<init>	()V
      //   470: astore 9
      //   472: aload 9
      //   474: ldc -112
      //   476: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   479: pop
      //   480: aload 9
      //   482: aload 8
      //   484: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   487: pop
      //   488: aload 7
      //   490: aload 9
      //   492: invokevirtual 154	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   495: aload_1
      //   496: invokestatic 160	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   499: pop
      //   500: aconst_null
      //   501: areturn
      //   502: astore_1
      //   503: aconst_null
      //   504: areturn
      //   505: astore_1
      //   506: goto -52 -> 454
      //   509: aconst_null
      //   510: areturn
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	511	0	this	PersistHistoryAsyncTask
      //   0	511	1	paramVarArgs	Object[]
      //   166	7	2	f	float
      //   80	117	3	i	int
      //   77	8	4	j	int
      //   143	8	5	l	long
      //   6	82	7	localList	List
      //   236	1	7	localThrowable	Throwable
      //   241	45	7	localIOException	java.io.IOException
      //   306	45	7	localIllegalStateException	IllegalStateException
      //   371	84	7	localIllegalArgumentException	IllegalArgumentException
      //   461	28	7	str	String
      //   14	469	8	localObject1	Object
      //   95	396	9	localObject2	Object
      //   120	8	10	localComponentName	ComponentName
      // Exception table:
      //   from	to	target	type
      //   37	79	236	java/lang/Throwable
      //   87	97	236	java/lang/Throwable
      //   97	104	236	java/lang/Throwable
      //   104	115	236	java/lang/Throwable
      //   115	122	236	java/lang/Throwable
      //   122	138	236	java/lang/Throwable
      //   138	145	236	java/lang/Throwable
      //   145	161	236	java/lang/Throwable
      //   167	193	236	java/lang/Throwable
      //   200	218	236	java/lang/Throwable
      //   243	291	236	java/lang/Throwable
      //   308	356	236	java/lang/Throwable
      //   373	421	236	java/lang/Throwable
      //   37	79	241	java/io/IOException
      //   87	97	241	java/io/IOException
      //   104	115	241	java/io/IOException
      //   122	138	241	java/io/IOException
      //   145	161	241	java/io/IOException
      //   167	193	241	java/io/IOException
      //   200	218	241	java/io/IOException
      //   37	79	306	java/lang/IllegalStateException
      //   87	97	306	java/lang/IllegalStateException
      //   104	115	306	java/lang/IllegalStateException
      //   122	138	306	java/lang/IllegalStateException
      //   145	161	306	java/lang/IllegalStateException
      //   167	193	306	java/lang/IllegalStateException
      //   200	218	306	java/lang/IllegalStateException
      //   37	79	371	java/lang/IllegalArgumentException
      //   87	97	371	java/lang/IllegalArgumentException
      //   104	115	371	java/lang/IllegalArgumentException
      //   122	138	371	java/lang/IllegalArgumentException
      //   145	161	371	java/lang/IllegalArgumentException
      //   167	193	371	java/lang/IllegalArgumentException
      //   200	218	371	java/lang/IllegalArgumentException
      //   24	32	457	java/io/FileNotFoundException
      //   230	234	502	java/io/IOException
      //   450	454	505	java/io/IOException
    }
  }
}
