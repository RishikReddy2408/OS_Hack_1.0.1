package android.support.v4.content.hack;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.graphics.TypefaceCompat;
import android.support.v4.util.Preconditions;
import android.util.Log;
import android.util.TypedValue;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

public final class ResourcesCompat
{
  private static final String TAG = "ResourcesCompat";
  
  private ResourcesCompat() {}
  
  public static int getColor(Resources paramResources, int paramInt, Resources.Theme paramTheme)
    throws Resources.NotFoundException
  {
    if (Build.VERSION.SDK_INT >= 23) {
      return paramResources.getColor(paramInt, paramTheme);
    }
    return paramResources.getColor(paramInt);
  }
  
  public static ColorStateList getColorStateList(Resources paramResources, int paramInt, Resources.Theme paramTheme)
    throws Resources.NotFoundException
  {
    if (Build.VERSION.SDK_INT >= 23) {
      return paramResources.getColorStateList(paramInt, paramTheme);
    }
    return paramResources.getColorStateList(paramInt);
  }
  
  public static Drawable getDrawable(Resources paramResources, int paramInt, Resources.Theme paramTheme)
    throws Resources.NotFoundException
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return paramResources.getDrawable(paramInt, paramTheme);
    }
    return paramResources.getDrawable(paramInt);
  }
  
  public static Drawable getDrawableForDensity(Resources paramResources, int paramInt1, int paramInt2, Resources.Theme paramTheme)
    throws Resources.NotFoundException
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return paramResources.getDrawableForDensity(paramInt1, paramInt2, paramTheme);
    }
    if (Build.VERSION.SDK_INT >= 15) {
      return paramResources.getDrawableForDensity(paramInt1, paramInt2);
    }
    return paramResources.getDrawable(paramInt1);
  }
  
  public static Typeface getFont(Context paramContext, int paramInt)
    throws Resources.NotFoundException
  {
    if (paramContext.isRestricted()) {
      return null;
    }
    return loadFont(paramContext, paramInt, new TypedValue(), 0, null, null, false);
  }
  
  public static Typeface getFont(Context paramContext, int paramInt1, TypedValue paramTypedValue, int paramInt2, FontCallback paramFontCallback)
    throws Resources.NotFoundException
  {
    if (paramContext.isRestricted()) {
      return null;
    }
    return loadFont(paramContext, paramInt1, paramTypedValue, paramInt2, paramFontCallback, null, true);
  }
  
  public static void getFont(Context paramContext, int paramInt, FontCallback paramFontCallback, Handler paramHandler)
    throws Resources.NotFoundException
  {
    Preconditions.checkNotNull(paramFontCallback);
    if (paramContext.isRestricted())
    {
      paramFontCallback.callbackFailAsync(-4, paramHandler);
      return;
    }
    loadFont(paramContext, paramInt, new TypedValue(), 0, paramFontCallback, paramHandler, false);
  }
  
  private static Typeface loadFont(Context paramContext, int paramInt1, TypedValue paramTypedValue, int paramInt2, FontCallback paramFontCallback, Handler paramHandler, boolean paramBoolean)
  {
    Resources localResources = paramContext.getResources();
    localResources.getValue(paramInt1, paramTypedValue, true);
    paramContext = loadFont(paramContext, localResources, paramTypedValue, paramInt1, paramInt2, paramFontCallback, paramHandler, paramBoolean);
    if (paramContext == null)
    {
      if (paramFontCallback != null) {
        return paramContext;
      }
      paramContext = new StringBuilder();
      paramContext.append("Font resource ID #0x");
      paramContext.append(Integer.toHexString(paramInt1));
      paramContext.append(" could not be retrieved.");
      throw new Resources.NotFoundException(paramContext.toString());
    }
    return paramContext;
  }
  
  private static Typeface loadFont(Context paramContext, Resources paramResources, TypedValue paramTypedValue, int paramInt1, int paramInt2, FontCallback paramFontCallback, Handler paramHandler, boolean paramBoolean)
  {
    Object localObject;
    if (string != null)
    {
      paramTypedValue = string.toString();
      if (!paramTypedValue.startsWith("res/"))
      {
        if (paramFontCallback != null)
        {
          paramFontCallback.callbackFailAsync(-3, paramHandler);
          return null;
        }
      }
      else
      {
        localObject = TypefaceCompat.findFromCache(paramResources, paramInt1, paramInt2);
        if (localObject != null)
        {
          if (paramFontCallback == null) {
            break label341;
          }
          paramFontCallback.callbackSuccessAsync((Typeface)localObject, paramHandler);
          return localObject;
        }
        try
        {
          boolean bool = paramTypedValue.toLowerCase().endsWith(".xml");
          if (bool)
          {
            localObject = FontResourcesParserCompat.parse(paramResources.getXml(paramInt1), paramResources);
            if (localObject == null)
            {
              Log.e("ResourcesCompat", "Failed to find font-family tag");
              if (paramFontCallback == null) {
                break label344;
              }
              paramFontCallback.callbackFailAsync(-3, paramHandler);
              return null;
            }
            paramContext = TypefaceCompat.createFromResourcesFamilyXml(paramContext, (FontResourcesParserCompat.FamilyResourceEntry)localObject, paramResources, paramInt1, paramInt2, paramFontCallback, paramHandler, paramBoolean);
            return paramContext;
          }
          paramContext = TypefaceCompat.createFromResourcesFontFile(paramContext, paramResources, paramInt1, paramTypedValue, paramInt2);
          if (paramFontCallback == null) {
            break label346;
          }
          if (paramContext != null)
          {
            paramFontCallback.callbackSuccessAsync(paramContext, paramHandler);
            return paramContext;
          }
          paramFontCallback.callbackFailAsync(-3, paramHandler);
          return paramContext;
        }
        catch (IOException paramContext)
        {
          paramResources = new StringBuilder();
          paramResources.append("Failed to read xml resource ");
          paramResources.append(paramTypedValue);
          Log.e("ResourcesCompat", paramResources.toString(), paramContext);
        }
        catch (XmlPullParserException paramContext)
        {
          paramResources = new StringBuilder();
          paramResources.append("Failed to parse xml resource ");
          paramResources.append(paramTypedValue);
          Log.e("ResourcesCompat", paramResources.toString(), paramContext);
        }
        if (paramFontCallback == null) {
          break label348;
        }
        paramFontCallback.callbackFailAsync(-3, paramHandler);
        return null;
      }
    }
    else
    {
      paramContext = new StringBuilder();
      paramContext.append("Resource \"");
      paramContext.append(paramResources.getResourceName(paramInt1));
      paramContext.append("\" (");
      paramContext.append(Integer.toHexString(paramInt1));
      paramContext.append(") is not a Font: ");
      paramContext.append(paramTypedValue);
      throw new Resources.NotFoundException(paramContext.toString());
    }
    return null;
    label341:
    return localObject;
    label344:
    return null;
    label346:
    return paramContext;
    label348:
    return null;
  }
  
  public abstract class FontCallback
  {
    public FontCallback() {}
    
    public final void callbackFailAsync(int paramInt, Handler paramHandler)
    {
      Handler localHandler = paramHandler;
      if (paramHandler == null) {
        localHandler = new Handler(Looper.getMainLooper());
      }
      localHandler.post(new ResourcesCompat.FontCallback.2(this, paramInt));
    }
    
    public final void callbackSuccessAsync(Typeface paramTypeface, Handler paramHandler)
    {
      Handler localHandler = paramHandler;
      if (paramHandler == null) {
        localHandler = new Handler(Looper.getMainLooper());
      }
      localHandler.post(new ResourcesCompat.FontCallback.1(this, paramTypeface));
    }
    
    public abstract void onFontRetrievalFailed(int paramInt);
    
    public abstract void onFontRetrieved(Typeface paramTypeface);
  }
}
