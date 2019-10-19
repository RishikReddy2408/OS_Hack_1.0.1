package android.support.v4.package_7;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Parcelable;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.ActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ShareActionProvider;
import java.util.ArrayList;

public final class ShareCompat
{
  public static final String EXTRA_CALLING_ACTIVITY = "android.support.v4.app.EXTRA_CALLING_ACTIVITY";
  public static final String EXTRA_CALLING_PACKAGE = "android.support.v4.app.EXTRA_CALLING_PACKAGE";
  private static final String HISTORY_FILENAME_PREFIX = ".sharecompat_";
  
  private ShareCompat() {}
  
  public static void configureMenuItem(Menu paramMenu, int paramInt, IntentBuilder paramIntentBuilder)
  {
    paramMenu = paramMenu.findItem(paramInt);
    if (paramMenu != null)
    {
      configureMenuItem(paramMenu, paramIntentBuilder);
      return;
    }
    paramMenu = new StringBuilder();
    paramMenu.append("Could not find menu item with id ");
    paramMenu.append(paramInt);
    paramMenu.append(" in the supplied menu");
    throw new IllegalArgumentException(paramMenu.toString());
  }
  
  public static void configureMenuItem(MenuItem paramMenuItem, IntentBuilder paramIntentBuilder)
  {
    Object localObject = paramMenuItem.getActionProvider();
    if (!(localObject instanceof ShareActionProvider)) {
      localObject = new ShareActionProvider(paramIntentBuilder.getActivity());
    } else {
      localObject = (ShareActionProvider)localObject;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(".sharecompat_");
    localStringBuilder.append(paramIntentBuilder.getActivity().getClass().getName());
    ((ShareActionProvider)localObject).setShareHistoryFileName(localStringBuilder.toString());
    ((ShareActionProvider)localObject).setShareIntent(paramIntentBuilder.getIntent());
    paramMenuItem.setActionProvider((ActionProvider)localObject);
    if ((Build.VERSION.SDK_INT < 16) && (!paramMenuItem.hasSubMenu())) {
      paramMenuItem.setIntent(paramIntentBuilder.createChooserIntent());
    }
  }
  
  public static ComponentName getCallingActivity(Activity paramActivity)
  {
    ComponentName localComponentName2 = paramActivity.getCallingActivity();
    ComponentName localComponentName1 = localComponentName2;
    if (localComponentName2 == null) {
      localComponentName1 = (ComponentName)paramActivity.getIntent().getParcelableExtra("android.support.v4.app.EXTRA_CALLING_ACTIVITY");
    }
    return localComponentName1;
  }
  
  public static String getCallingPackage(Activity paramActivity)
  {
    String str2 = paramActivity.getCallingPackage();
    String str1 = str2;
    if (str2 == null) {
      str1 = paramActivity.getIntent().getStringExtra("android.support.v4.app.EXTRA_CALLING_PACKAGE");
    }
    return str1;
  }
  
  public class IntentBuilder
  {
    private ArrayList<String> mBccAddresses;
    private ArrayList<String> mCcAddresses;
    private CharSequence mChooserTitle;
    private Intent mIntent = new Intent().setAction("android.intent.action.SEND");
    private ArrayList<Uri> mStreams;
    private ArrayList<String> mToAddresses;
    
    private IntentBuilder()
    {
      mIntent.putExtra("android.support.v4.app.EXTRA_CALLING_PACKAGE", getPackageName());
      mIntent.putExtra("android.support.v4.app.EXTRA_CALLING_ACTIVITY", getComponentName());
      mIntent.addFlags(524288);
    }
    
    private void combineArrayExtra(String paramString, ArrayList paramArrayList)
    {
      String[] arrayOfString1 = mIntent.getStringArrayExtra(paramString);
      int i;
      if (arrayOfString1 != null) {
        i = arrayOfString1.length;
      } else {
        i = 0;
      }
      String[] arrayOfString2 = new String[paramArrayList.size() + i];
      paramArrayList.toArray(arrayOfString2);
      if (arrayOfString1 != null) {
        System.arraycopy(arrayOfString1, 0, arrayOfString2, paramArrayList.size(), i);
      }
      mIntent.putExtra(paramString, arrayOfString2);
    }
    
    private void combineArrayExtra(String paramString, String[] paramArrayOfString)
    {
      Intent localIntent = getIntent();
      String[] arrayOfString1 = localIntent.getStringArrayExtra(paramString);
      int i;
      if (arrayOfString1 != null) {
        i = arrayOfString1.length;
      } else {
        i = 0;
      }
      String[] arrayOfString2 = new String[paramArrayOfString.length + i];
      if (arrayOfString1 != null) {
        System.arraycopy(arrayOfString1, 0, arrayOfString2, 0, i);
      }
      System.arraycopy(paramArrayOfString, 0, arrayOfString2, i, paramArrayOfString.length);
      localIntent.putExtra(paramString, arrayOfString2);
    }
    
    public static IntentBuilder from(Activity paramActivity)
    {
      return new IntentBuilder(paramActivity);
    }
    
    public IntentBuilder addEmailBcc(String paramString)
    {
      if (mBccAddresses == null) {
        mBccAddresses = new ArrayList();
      }
      mBccAddresses.add(paramString);
      return this;
    }
    
    public IntentBuilder addEmailBcc(String[] paramArrayOfString)
    {
      combineArrayExtra("android.intent.extra.BCC", paramArrayOfString);
      return this;
    }
    
    public IntentBuilder addEmailCc(String paramString)
    {
      if (mCcAddresses == null) {
        mCcAddresses = new ArrayList();
      }
      mCcAddresses.add(paramString);
      return this;
    }
    
    public IntentBuilder addEmailCc(String[] paramArrayOfString)
    {
      combineArrayExtra("android.intent.extra.CC", paramArrayOfString);
      return this;
    }
    
    public IntentBuilder addEmailTo(String paramString)
    {
      if (mToAddresses == null) {
        mToAddresses = new ArrayList();
      }
      mToAddresses.add(paramString);
      return this;
    }
    
    public IntentBuilder addEmailTo(String[] paramArrayOfString)
    {
      combineArrayExtra("android.intent.extra.EMAIL", paramArrayOfString);
      return this;
    }
    
    public IntentBuilder addStream(Uri paramUri)
    {
      Uri localUri = (Uri)mIntent.getParcelableExtra("android.intent.extra.STREAM");
      if ((mStreams == null) && (localUri == null)) {
        return setStream(paramUri);
      }
      if (mStreams == null) {
        mStreams = new ArrayList();
      }
      if (localUri != null)
      {
        mIntent.removeExtra("android.intent.extra.STREAM");
        mStreams.add(localUri);
      }
      mStreams.add(paramUri);
      return this;
    }
    
    public Intent createChooserIntent()
    {
      return Intent.createChooser(getIntent(), mChooserTitle);
    }
    
    Activity getActivity()
    {
      return ShareCompat.this;
    }
    
    public Intent getIntent()
    {
      if (mToAddresses != null)
      {
        combineArrayExtra("android.intent.extra.EMAIL", mToAddresses);
        mToAddresses = null;
      }
      if (mCcAddresses != null)
      {
        combineArrayExtra("android.intent.extra.CC", mCcAddresses);
        mCcAddresses = null;
      }
      if (mBccAddresses != null)
      {
        combineArrayExtra("android.intent.extra.BCC", mBccAddresses);
        mBccAddresses = null;
      }
      ArrayList localArrayList = mStreams;
      int i = 1;
      if ((localArrayList == null) || (mStreams.size() <= 1)) {
        i = 0;
      }
      boolean bool = mIntent.getAction().equals("android.intent.action.SEND_MULTIPLE");
      if ((i == 0) && (bool))
      {
        mIntent.setAction("android.intent.action.SEND");
        if ((mStreams != null) && (!mStreams.isEmpty())) {
          mIntent.putExtra("android.intent.extra.STREAM", (Parcelable)mStreams.get(0));
        } else {
          mIntent.removeExtra("android.intent.extra.STREAM");
        }
        mStreams = null;
      }
      if ((i != 0) && (!bool))
      {
        mIntent.setAction("android.intent.action.SEND_MULTIPLE");
        if ((mStreams != null) && (!mStreams.isEmpty())) {
          mIntent.putParcelableArrayListExtra("android.intent.extra.STREAM", mStreams);
        } else {
          mIntent.removeExtra("android.intent.extra.STREAM");
        }
      }
      return mIntent;
    }
    
    public IntentBuilder setChooserTitle(int paramInt)
    {
      return setChooserTitle(getText(paramInt));
    }
    
    public IntentBuilder setChooserTitle(CharSequence paramCharSequence)
    {
      mChooserTitle = paramCharSequence;
      return this;
    }
    
    public IntentBuilder setEmailBcc(String[] paramArrayOfString)
    {
      mIntent.putExtra("android.intent.extra.BCC", paramArrayOfString);
      return this;
    }
    
    public IntentBuilder setEmailCc(String[] paramArrayOfString)
    {
      mIntent.putExtra("android.intent.extra.CC", paramArrayOfString);
      return this;
    }
    
    public IntentBuilder setEmailTo(String[] paramArrayOfString)
    {
      if (mToAddresses != null) {
        mToAddresses = null;
      }
      mIntent.putExtra("android.intent.extra.EMAIL", paramArrayOfString);
      return this;
    }
    
    public IntentBuilder setHtmlText(String paramString)
    {
      mIntent.putExtra("android.intent.extra.HTML_TEXT", paramString);
      if (!mIntent.hasExtra("android.intent.extra.TEXT")) {
        setText(Html.fromHtml(paramString));
      }
      return this;
    }
    
    public IntentBuilder setStream(Uri paramUri)
    {
      if (!mIntent.getAction().equals("android.intent.action.SEND")) {
        mIntent.setAction("android.intent.action.SEND");
      }
      mStreams = null;
      mIntent.putExtra("android.intent.extra.STREAM", paramUri);
      return this;
    }
    
    public IntentBuilder setSubject(String paramString)
    {
      mIntent.putExtra("android.intent.extra.SUBJECT", paramString);
      return this;
    }
    
    public IntentBuilder setText(CharSequence paramCharSequence)
    {
      mIntent.putExtra("android.intent.extra.TEXT", paramCharSequence);
      return this;
    }
    
    public IntentBuilder setType(String paramString)
    {
      mIntent.setType(paramString);
      return this;
    }
    
    public void startChooser()
    {
      startActivity(createChooserIntent());
    }
  }
  
  public class IntentReader
  {
    private static final String TAG = "IntentReader";
    private ComponentName mCallingActivity = ShareCompat.getCallingActivity(ShareCompat.this);
    private String mCallingPackage = ShareCompat.getCallingPackage(ShareCompat.this);
    private Intent mIntent = getIntent();
    private ArrayList<Uri> mStreams;
    
    private IntentReader() {}
    
    public static IntentReader from(Activity paramActivity)
    {
      return new IntentReader(paramActivity);
    }
    
    private static void withinStyle(StringBuilder paramStringBuilder, CharSequence paramCharSequence, int paramInt1, int paramInt2)
    {
      while (paramInt1 < paramInt2)
      {
        char c = paramCharSequence.charAt(paramInt1);
        if (c == '<')
        {
          paramStringBuilder.append("&lt;");
        }
        else if (c == '>')
        {
          paramStringBuilder.append("&gt;");
        }
        else if (c == '&')
        {
          paramStringBuilder.append("&amp;");
        }
        else if ((c <= '~') && (c >= ' '))
        {
          if (c == ' ')
          {
            for (;;)
            {
              int i = paramInt1 + 1;
              if ((i >= paramInt2) || (paramCharSequence.charAt(i) != ' ')) {
                break;
              }
              paramStringBuilder.append("&nbsp;");
              paramInt1 = i;
            }
            paramStringBuilder.append(' ');
          }
          else
          {
            paramStringBuilder.append(c);
          }
        }
        else
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("&#");
          localStringBuilder.append(c);
          localStringBuilder.append(";");
          paramStringBuilder.append(localStringBuilder.toString());
        }
        paramInt1 += 1;
      }
    }
    
    public ComponentName getCallingActivity()
    {
      return mCallingActivity;
    }
    
    public Drawable getCallingActivityIcon()
    {
      if (mCallingActivity == null) {
        return null;
      }
      Object localObject = getPackageManager();
      ComponentName localComponentName = mCallingActivity;
      try
      {
        localObject = ((PackageManager)localObject).getActivityIcon(localComponentName);
        return localObject;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        Log.e("IntentReader", "Could not retrieve icon for calling activity", localNameNotFoundException);
      }
      return null;
    }
    
    public Drawable getCallingApplicationIcon()
    {
      if (mCallingPackage == null) {
        return null;
      }
      Object localObject = getPackageManager();
      String str = mCallingPackage;
      try
      {
        localObject = ((PackageManager)localObject).getApplicationIcon(str);
        return localObject;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        Log.e("IntentReader", "Could not retrieve icon for calling application", localNameNotFoundException);
      }
      return null;
    }
    
    public CharSequence getCallingApplicationLabel()
    {
      if (mCallingPackage == null) {
        return null;
      }
      Object localObject = getPackageManager();
      String str = mCallingPackage;
      try
      {
        localObject = ((PackageManager)localObject).getApplicationLabel(((PackageManager)localObject).getApplicationInfo(str, 0));
        return localObject;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        Log.e("IntentReader", "Could not retrieve label for calling application", localNameNotFoundException);
      }
      return null;
    }
    
    public String getCallingPackage()
    {
      return mCallingPackage;
    }
    
    public String[] getEmailBcc()
    {
      return mIntent.getStringArrayExtra("android.intent.extra.BCC");
    }
    
    public String[] getEmailCc()
    {
      return mIntent.getStringArrayExtra("android.intent.extra.CC");
    }
    
    public String[] getEmailTo()
    {
      return mIntent.getStringArrayExtra("android.intent.extra.EMAIL");
    }
    
    public String getHtmlText()
    {
      String str = mIntent.getStringExtra("android.intent.extra.HTML_TEXT");
      Object localObject = str;
      if (str == null)
      {
        CharSequence localCharSequence = getText();
        if ((localCharSequence instanceof Spanned)) {
          return Html.toHtml((Spanned)localCharSequence);
        }
        localObject = str;
        if (localCharSequence != null)
        {
          if (Build.VERSION.SDK_INT >= 16) {
            return Html.escapeHtml(localCharSequence);
          }
          localObject = new StringBuilder();
          withinStyle((StringBuilder)localObject, localCharSequence, 0, localCharSequence.length());
          localObject = ((StringBuilder)localObject).toString();
        }
      }
      return localObject;
    }
    
    public Uri getStream()
    {
      return (Uri)mIntent.getParcelableExtra("android.intent.extra.STREAM");
    }
    
    public Uri getStream(int paramInt)
    {
      if ((mStreams == null) && (isMultipleShare())) {
        mStreams = mIntent.getParcelableArrayListExtra("android.intent.extra.STREAM");
      }
      if (mStreams != null) {
        return (Uri)mStreams.get(paramInt);
      }
      if (paramInt == 0) {
        return (Uri)mIntent.getParcelableExtra("android.intent.extra.STREAM");
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Stream items available: ");
      localStringBuilder.append(getStreamCount());
      localStringBuilder.append(" index requested: ");
      localStringBuilder.append(paramInt);
      throw new IndexOutOfBoundsException(localStringBuilder.toString());
    }
    
    public int getStreamCount()
    {
      throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:659)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
    }
    
    public String getSubject()
    {
      return mIntent.getStringExtra("android.intent.extra.SUBJECT");
    }
    
    public CharSequence getText()
    {
      return mIntent.getCharSequenceExtra("android.intent.extra.TEXT");
    }
    
    public String getType()
    {
      return mIntent.getType();
    }
    
    public boolean isMultipleShare()
    {
      return "android.intent.action.SEND_MULTIPLE".equals(mIntent.getAction());
    }
    
    public boolean isShareIntent()
    {
      String str = mIntent.getAction();
      return ("android.intent.action.SEND".equals(str)) || ("android.intent.action.SEND_MULTIPLE".equals(str));
    }
    
    public boolean isSingleShare()
    {
      return "android.intent.action.SEND".equals(mIntent.getAction());
    }
  }
}
