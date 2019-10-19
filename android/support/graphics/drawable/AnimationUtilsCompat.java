package android.support.graphics.drawable;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;
import android.content.res.XmlResourceParser;
import android.os.Build.VERSION;
import android.support.annotation.RestrictTo;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class AnimationUtilsCompat
{
  public AnimationUtilsCompat() {}
  
  private static Interpolator createInterpolatorFromXml(Context paramContext, Resources paramResources, Resources.Theme paramTheme, XmlPullParser paramXmlPullParser)
    throws XmlPullParserException, IOException
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: fail exe a11 = a10\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:92)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.dfs(Cfg.java:255)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze0(BaseAnalyze.java:75)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze(BaseAnalyze.java:69)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer.transform(UnSSATransformer.java:274)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:163)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\nCaused by: java.lang.NullPointerException\n");
  }
  
  public static Interpolator loadInterpolator(Context paramContext, int paramInt)
    throws Resources.NotFoundException
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return AnimationUtils.loadInterpolator(paramContext, paramInt);
    }
    Object localObject3 = null;
    Object localObject4 = null;
    Object localObject1 = null;
    Object localObject2;
    if (paramInt == 17563663)
    {
      try
      {
        paramContext = new FastOutLinearInInterpolator();
        return paramContext;
      }
      catch (Throwable paramContext)
      {
        break label263;
      }
      catch (IOException paramContext)
      {
        localObject2 = localObject3;
        break label137;
      }
      catch (XmlPullParserException paramContext)
      {
        localObject2 = localObject4;
      }
    }
    else
    {
      if (paramInt == 17563661)
      {
        paramContext = new FastOutSlowInInterpolator();
        return paramContext;
      }
      if (paramInt == 17563662)
      {
        paramContext = new LinearOutSlowInInterpolator();
        return paramContext;
      }
      localObject2 = paramContext.getResources().getAnimation(paramInt);
      try
      {
        paramContext = createInterpolatorFromXml(paramContext, paramContext.getResources(), paramContext.getTheme(), (XmlPullParser)localObject2);
        if (localObject2 == null) {
          return paramContext;
        }
        ((XmlResourceParser)localObject2).close();
        return paramContext;
      }
      catch (Throwable paramContext)
      {
        localObject1 = localObject2;
        break label263;
      }
      catch (IOException paramContext) {}catch (XmlPullParserException paramContext) {}
    }
    label137:
    localObject1 = localObject2;
    localObject3 = new StringBuilder();
    localObject1 = localObject2;
    ((StringBuilder)localObject3).append("Can't load animation resource ID #0x");
    localObject1 = localObject2;
    ((StringBuilder)localObject3).append(Integer.toHexString(paramInt));
    localObject1 = localObject2;
    localObject3 = new Resources.NotFoundException(((StringBuilder)localObject3).toString());
    localObject1 = localObject2;
    ((Exception)localObject3).initCause(paramContext);
    localObject1 = localObject2;
    throw ((Throwable)localObject3);
    localObject1 = localObject2;
    localObject3 = new StringBuilder();
    localObject1 = localObject2;
    ((StringBuilder)localObject3).append("Can't load animation resource ID #0x");
    localObject1 = localObject2;
    ((StringBuilder)localObject3).append(Integer.toHexString(paramInt));
    localObject1 = localObject2;
    localObject3 = new Resources.NotFoundException(((StringBuilder)localObject3).toString());
    localObject1 = localObject2;
    ((Exception)localObject3).initCause(paramContext);
    localObject1 = localObject2;
    throw ((Throwable)localObject3);
    label263:
    if (localObject1 != null) {
      localObject1.close();
    }
    throw paramContext;
    return paramContext;
  }
}
