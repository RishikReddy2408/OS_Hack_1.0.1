package android.support.v4.text.util;

import android.os.Build.VERSION;
import android.support.annotation.RestrictTo;
import android.support.v4.util.PatternsCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.text.util.Linkify.MatchFilter;
import android.text.util.Linkify.TransformFilter;
import android.widget.TextView;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class LinkifyCompat
{
  private static final Comparator<LinkSpec> COMPARATOR = new Comparator()
  {
    public int compare(LinkifyCompat.LinkSpec paramAnonymousLinkSpec1, LinkifyCompat.LinkSpec paramAnonymousLinkSpec2)
    {
      if (start < start) {
        return -1;
      }
      if (start > start) {
        return 1;
      }
      if (end < end) {
        return 1;
      }
      if (end > end) {
        return -1;
      }
      return 0;
    }
  };
  private static final String[] EMPTY_STRING = new String[0];
  
  private LinkifyCompat() {}
  
  private static void addLinkMovementMethod(TextView paramTextView)
  {
    MovementMethod localMovementMethod = paramTextView.getMovementMethod();
    if (((localMovementMethod == null) || (!(localMovementMethod instanceof LinkMovementMethod))) && (paramTextView.getLinksClickable())) {
      paramTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }
  }
  
  public static void addLinks(TextView paramTextView, Pattern paramPattern, String paramString)
  {
    if (Build.VERSION.SDK_INT >= 26)
    {
      Linkify.addLinks(paramTextView, paramPattern, paramString);
      return;
    }
    addLinks(paramTextView, paramPattern, paramString, null, null, null);
  }
  
  public static void addLinks(TextView paramTextView, Pattern paramPattern, String paramString, Linkify.MatchFilter paramMatchFilter, Linkify.TransformFilter paramTransformFilter)
  {
    if (Build.VERSION.SDK_INT >= 26)
    {
      Linkify.addLinks(paramTextView, paramPattern, paramString, paramMatchFilter, paramTransformFilter);
      return;
    }
    addLinks(paramTextView, paramPattern, paramString, null, paramMatchFilter, paramTransformFilter);
  }
  
  public static void addLinks(TextView paramTextView, Pattern paramPattern, String paramString, String[] paramArrayOfString, Linkify.MatchFilter paramMatchFilter, Linkify.TransformFilter paramTransformFilter)
  {
    if (Build.VERSION.SDK_INT >= 26)
    {
      Linkify.addLinks(paramTextView, paramPattern, paramString, paramArrayOfString, paramMatchFilter, paramTransformFilter);
      return;
    }
    SpannableString localSpannableString = SpannableString.valueOf(paramTextView.getText());
    if (addLinks(localSpannableString, paramPattern, paramString, paramArrayOfString, paramMatchFilter, paramTransformFilter))
    {
      paramTextView.setText(localSpannableString);
      addLinkMovementMethod(paramTextView);
    }
  }
  
  public static boolean addLinks(Spannable paramSpannable, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 27) {
      return Linkify.addLinks(paramSpannable, paramInt);
    }
    if (paramInt == 0) {
      return false;
    }
    Object localObject1 = (URLSpan[])paramSpannable.getSpans(0, paramSpannable.length(), URLSpan.class);
    int i = localObject1.length - 1;
    while (i >= 0)
    {
      paramSpannable.removeSpan(localObject1[i]);
      i -= 1;
    }
    if ((paramInt & 0x4) != 0) {
      Linkify.addLinks(paramSpannable, 4);
    }
    localObject1 = new ArrayList();
    Object localObject2;
    if ((paramInt & 0x1) != 0)
    {
      localObject2 = PatternsCompat.AUTOLINK_WEB_URL;
      Linkify.MatchFilter localMatchFilter = Linkify.sUrlMatchFilter;
      gatherLinks((ArrayList)localObject1, paramSpannable, (Pattern)localObject2, new String[] { "http://", "https://", "rtsp://" }, localMatchFilter, null);
    }
    if ((paramInt & 0x2) != 0) {
      gatherLinks((ArrayList)localObject1, paramSpannable, PatternsCompat.AUTOLINK_EMAIL_ADDRESS, new String[] { "mailto:" }, null, null);
    }
    if ((paramInt & 0x8) != 0) {
      gatherMapLinks((ArrayList)localObject1, paramSpannable);
    }
    pruneOverlaps((ArrayList)localObject1, paramSpannable);
    if (((ArrayList)localObject1).size() == 0) {
      return false;
    }
    localObject1 = ((ArrayList)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (LinkSpec)((Iterator)localObject1).next();
      if (frameworkAddedSpan == null) {
        applyLink(url, start, end, paramSpannable);
      }
    }
    return true;
  }
  
  public static boolean addLinks(Spannable paramSpannable, Pattern paramPattern, String paramString)
  {
    if (Build.VERSION.SDK_INT >= 26) {
      return Linkify.addLinks(paramSpannable, paramPattern, paramString);
    }
    return addLinks(paramSpannable, paramPattern, paramString, null, null, null);
  }
  
  public static boolean addLinks(Spannable paramSpannable, Pattern paramPattern, String paramString, Linkify.MatchFilter paramMatchFilter, Linkify.TransformFilter paramTransformFilter)
  {
    if (Build.VERSION.SDK_INT >= 26) {
      return Linkify.addLinks(paramSpannable, paramPattern, paramString, paramMatchFilter, paramTransformFilter);
    }
    return addLinks(paramSpannable, paramPattern, paramString, null, paramMatchFilter, paramTransformFilter);
  }
  
  public static boolean addLinks(Spannable paramSpannable, Pattern paramPattern, String paramString, String[] paramArrayOfString, Linkify.MatchFilter paramMatchFilter, Linkify.TransformFilter paramTransformFilter)
  {
    if (Build.VERSION.SDK_INT >= 26) {
      return Linkify.addLinks(paramSpannable, paramPattern, paramString, paramArrayOfString, paramMatchFilter, paramTransformFilter);
    }
    String str = paramString;
    if (paramString == null) {
      str = "";
    }
    if (paramArrayOfString != null)
    {
      paramString = paramArrayOfString;
      if (paramArrayOfString.length >= 1) {}
    }
    else
    {
      paramString = EMPTY_STRING;
    }
    String[] arrayOfString = new String[paramString.length + 1];
    arrayOfString[0] = str.toLowerCase(Locale.ROOT);
    int i = 0;
    while (i < paramString.length)
    {
      paramArrayOfString = paramString[i];
      i += 1;
      if (paramArrayOfString == null) {
        paramArrayOfString = "";
      } else {
        paramArrayOfString = paramArrayOfString.toLowerCase(Locale.ROOT);
      }
      arrayOfString[i] = paramArrayOfString;
    }
    paramPattern = paramPattern.matcher(paramSpannable);
    boolean bool1 = false;
    while (paramPattern.find())
    {
      i = paramPattern.start();
      int j = paramPattern.end();
      boolean bool2;
      if (paramMatchFilter != null) {
        bool2 = paramMatchFilter.acceptMatch(paramSpannable, i, j);
      } else {
        bool2 = true;
      }
      if (bool2)
      {
        applyLink(makeUrl(paramPattern.group(0), arrayOfString, paramPattern, paramTransformFilter), i, j, paramSpannable);
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public static boolean addLinks(TextView paramTextView, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 26) {
      return Linkify.addLinks(paramTextView, paramInt);
    }
    if (paramInt == 0) {
      return false;
    }
    Object localObject = paramTextView.getText();
    if ((localObject instanceof Spannable))
    {
      if (addLinks((Spannable)localObject, paramInt))
      {
        addLinkMovementMethod(paramTextView);
        return true;
      }
      return false;
    }
    localObject = SpannableString.valueOf((CharSequence)localObject);
    if (addLinks((Spannable)localObject, paramInt))
    {
      addLinkMovementMethod(paramTextView);
      paramTextView.setText((CharSequence)localObject);
      return true;
    }
    return false;
  }
  
  private static void applyLink(String paramString, int paramInt1, int paramInt2, Spannable paramSpannable)
  {
    paramSpannable.setSpan(new URLSpan(paramString), paramInt1, paramInt2, 33);
  }
  
  private static void gatherLinks(ArrayList paramArrayList, Spannable paramSpannable, Pattern paramPattern, String[] paramArrayOfString, Linkify.MatchFilter paramMatchFilter, Linkify.TransformFilter paramTransformFilter)
  {
    paramPattern = paramPattern.matcher(paramSpannable);
    while (paramPattern.find())
    {
      int i = paramPattern.start();
      int j = paramPattern.end();
      if ((paramMatchFilter == null) || (paramMatchFilter.acceptMatch(paramSpannable, i, j)))
      {
        LinkSpec localLinkSpec = new LinkSpec();
        url = makeUrl(paramPattern.group(0), paramArrayOfString, paramPattern, paramTransformFilter);
        start = i;
        end = j;
        paramArrayList.add(localLinkSpec);
      }
    }
  }
  
  /* Error */
  private static void gatherMapLinks(ArrayList paramArrayList, Spannable paramSpannable)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 255	java/lang/Object:toString	()Ljava/lang/String;
    //   4: astore_1
    //   5: iconst_0
    //   6: istore_2
    //   7: aload_1
    //   8: invokestatic 261	android/webkit/WebView:findAddress	(Ljava/lang/String;)Ljava/lang/String;
    //   11: astore 6
    //   13: aload 6
    //   15: ifnull +123 -> 138
    //   18: aload_1
    //   19: aload 6
    //   21: invokevirtual 265	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   24: istore_3
    //   25: iload_3
    //   26: ifge +4 -> 30
    //   29: return
    //   30: new 8	android/support/v4/text/util/LinkifyCompat$LinkSpec
    //   33: dup
    //   34: invokespecial 243	android/support/v4/text/util/LinkifyCompat$LinkSpec:<init>	()V
    //   37: astore 5
    //   39: aload 6
    //   41: invokevirtual 266	java/lang/String:length	()I
    //   44: istore 4
    //   46: iload 4
    //   48: iload_3
    //   49: iadd
    //   50: istore 4
    //   52: aload 5
    //   54: iload_3
    //   55: iload_2
    //   56: iadd
    //   57: putfield 176	android/support/v4/text/util/LinkifyCompat$LinkSpec:start	I
    //   60: iload_2
    //   61: iload 4
    //   63: iadd
    //   64: istore_2
    //   65: aload 5
    //   67: iload_2
    //   68: putfield 179	android/support/v4/text/util/LinkifyCompat$LinkSpec:end	I
    //   71: aload_1
    //   72: iload 4
    //   74: invokevirtual 269	java/lang/String:substring	(I)Ljava/lang/String;
    //   77: astore_1
    //   78: aload 6
    //   80: ldc_w 271
    //   83: invokestatic 277	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   86: astore 6
    //   88: new 279	java/lang/StringBuilder
    //   91: dup
    //   92: invokespecial 280	java/lang/StringBuilder:<init>	()V
    //   95: astore 7
    //   97: aload 7
    //   99: ldc_w 282
    //   102: invokevirtual 286	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   105: pop
    //   106: aload 7
    //   108: aload 6
    //   110: invokevirtual 286	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   113: pop
    //   114: aload 7
    //   116: invokevirtual 287	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   119: astore 6
    //   121: aload 5
    //   123: aload 6
    //   125: putfield 173	android/support/v4/text/util/LinkifyCompat$LinkSpec:url	Ljava/lang/String;
    //   128: aload_0
    //   129: aload 5
    //   131: invokevirtual 247	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   134: pop
    //   135: goto -128 -> 7
    //   138: return
    //   139: astore_0
    //   140: return
    //   141: astore 5
    //   143: goto -136 -> 7
    //   146: astore_0
    //   147: return
    //   148: astore_0
    //   149: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	150	0	paramArrayList	ArrayList
    //   0	150	1	paramSpannable	Spannable
    //   6	62	2	i	int
    //   24	33	3	j	int
    //   44	29	4	k	int
    //   37	93	5	localLinkSpec	LinkSpec
    //   141	1	5	localUnsupportedEncodingException	java.io.UnsupportedEncodingException
    //   11	113	6	str	String
    //   95	20	7	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   7	13	139	java/lang/UnsupportedOperationException
    //   18	25	139	java/lang/UnsupportedOperationException
    //   30	46	139	java/lang/UnsupportedOperationException
    //   71	78	139	java/lang/UnsupportedOperationException
    //   78	88	141	java/io/UnsupportedEncodingException
    //   78	88	146	java/lang/UnsupportedOperationException
    //   88	121	148	java/lang/UnsupportedOperationException
    //   128	135	148	java/lang/UnsupportedOperationException
  }
  
  private static String makeUrl(String paramString, String[] paramArrayOfString, Matcher paramMatcher, Linkify.TransformFilter paramTransformFilter)
  {
    String str = paramString;
    if (paramTransformFilter != null) {
      str = paramTransformFilter.transformUrl(paramMatcher, paramString);
    }
    int i = 0;
    for (;;)
    {
      j = paramArrayOfString.length;
      int k = 1;
      if (i >= j) {
        break;
      }
      if (str.regionMatches(true, 0, paramArrayOfString[i], 0, paramArrayOfString[i].length()))
      {
        j = k;
        paramString = str;
        if (str.regionMatches(false, 0, paramArrayOfString[i], 0, paramArrayOfString[i].length())) {
          break label146;
        }
        paramString = new StringBuilder();
        paramString.append(paramArrayOfString[i]);
        paramString.append(str.substring(paramArrayOfString[i].length()));
        paramString = paramString.toString();
        j = k;
        break label146;
      }
      i += 1;
    }
    int j = 0;
    paramString = str;
    label146:
    paramMatcher = paramString;
    if (j == 0)
    {
      paramMatcher = paramString;
      if (paramArrayOfString.length > 0)
      {
        paramMatcher = new StringBuilder();
        paramMatcher.append(paramArrayOfString[0]);
        paramMatcher.append(paramString);
        paramMatcher = paramMatcher.toString();
      }
    }
    return paramMatcher;
  }
  
  private static void pruneOverlaps(ArrayList paramArrayList, Spannable paramSpannable)
  {
    int i = paramSpannable.length();
    int j = 0;
    Object localObject = (URLSpan[])paramSpannable.getSpans(0, i, URLSpan.class);
    i = 0;
    LinkSpec localLinkSpec;
    while (i < localObject.length)
    {
      localLinkSpec = new LinkSpec();
      frameworkAddedSpan = localObject[i];
      start = paramSpannable.getSpanStart(localObject[i]);
      end = paramSpannable.getSpanEnd(localObject[i]);
      paramArrayList.add(localLinkSpec);
      i += 1;
    }
    Collections.sort(paramArrayList, COMPARATOR);
    int k = paramArrayList.size();
    i = j;
    while (i < k - 1)
    {
      localObject = (LinkSpec)paramArrayList.get(i);
      int m = i + 1;
      localLinkSpec = (LinkSpec)paramArrayList.get(m);
      if ((start <= start) && (end > start))
      {
        if (end <= end) {}
        while (end - start > end - start)
        {
          j = m;
          break;
        }
        if (end - start < end - start) {
          j = i;
        } else {
          j = -1;
        }
        if (j != -1)
        {
          localObject = getframeworkAddedSpan;
          if (localObject != null) {
            paramSpannable.removeSpan(localObject);
          }
          paramArrayList.remove(j);
          k -= 1;
          continue;
        }
      }
      i = m;
    }
  }
  
  private static class LinkSpec
  {
    int end;
    URLSpan frameworkAddedSpan;
    int start;
    String url;
    
    LinkSpec() {}
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface LinkifyMask {}
}
