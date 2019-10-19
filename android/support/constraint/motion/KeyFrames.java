package android.support.constraint.motion;

import android.content.Context;
import android.content.res.Resources;
import android.support.constraint.ConstraintAttribute;
import android.util.Log;
import android.util.Xml;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class KeyFrames
{
  private static final String PAGE_KEY = "KeyFrames";
  public static final int UNSET = -1;
  static HashMap<String, Constructor<? extends Key>> sKeyMakers = new HashMap();
  private HashMap<Integer, ArrayList<Key>> mFramesMap = new HashMap();
  
  static
  {
    HashMap localHashMap = sKeyMakers;
    try
    {
      localHashMap.put("KeyAttribute", KeyAttributes.class.getConstructor(new Class[0]));
      localHashMap = sKeyMakers;
      localHashMap.put("KeyPosition", KeyPosition.class.getConstructor(new Class[0]));
      localHashMap = sKeyMakers;
      localHashMap.put("KeyCycle", KeyCycle.class.getConstructor(new Class[0]));
      localHashMap = sKeyMakers;
      localHashMap.put("KeyTimeCycle", KeyTimeCycle.class.getConstructor(new Class[0]));
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      Log.e("KeyFrames", "unable to load", localNoSuchMethodException);
    }
  }
  
  public KeyFrames(Context paramContext, XmlPullParser paramXmlPullParser)
  {
    Object localObject1 = null;
    try
    {
      int i = paramXmlPullParser.getEventType();
      while (i != 1)
      {
        Object localObject2 = localObject1;
        Object localObject3;
        if (i != 0)
        {
          boolean bool;
          switch (i)
          {
          default: 
            localObject2 = localObject1;
            break;
          case 3: 
            bool = "KeyFrameSet".equals(paramXmlPullParser.getName());
            localObject2 = localObject1;
            if (bool) {
              return;
            }
            break;
          case 2: 
            localObject2 = paramXmlPullParser.getName();
            HashMap localHashMap = sKeyMakers;
            bool = localHashMap.containsKey(localObject2);
            if (bool)
            {
              localHashMap = sKeyMakers;
              try
              {
                localObject2 = localHashMap.get(localObject2);
                localObject2 = (Constructor)localObject2;
                localObject2 = ((Constructor)localObject2).newInstance(new Object[0]);
                localObject1 = (FieldInfo)localObject2;
                try
                {
                  ((FieldInfo)localObject1).load(paramContext, Xml.asAttributeSet(paramXmlPullParser));
                  addKey((FieldInfo)localObject1);
                  localObject2 = localObject1;
                }
                catch (Exception localException1) {}
                Log.e("KeyFrames", "unable to create ", localException2);
              }
              catch (Exception localException2) {}
              localObject3 = localObject1;
            }
            else
            {
              bool = ((String)localObject3).equalsIgnoreCase("CustomAttribute");
              localObject3 = localObject1;
              if (bool)
              {
                localObject3 = localObject1;
                if (localObject1 != null)
                {
                  localObject3 = localObject1;
                  if (mCustomConstraints != null)
                  {
                    localObject3 = mCustomConstraints;
                    ConstraintAttribute.parse(paramContext, paramXmlPullParser, (HashMap)localObject3);
                    localObject3 = localObject1;
                  }
                }
              }
            }
            break;
          }
        }
        i = paramXmlPullParser.next();
        localObject1 = localObject3;
      }
      return;
    }
    catch (IOException paramContext)
    {
      ((IOException)paramContext).printStackTrace();
      return;
    }
    catch (XmlPullParserException paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  private void addKey(FieldInfo paramFieldInfo)
  {
    if (!mFramesMap.containsKey(Integer.valueOf(mTargetId))) {
      mFramesMap.put(Integer.valueOf(mTargetId), new ArrayList());
    }
    ((ArrayList)mFramesMap.get(Integer.valueOf(mTargetId))).add(paramFieldInfo);
  }
  
  static String name(int paramInt, Context paramContext)
  {
    return paramContext.getResources().getResourceEntryName(paramInt);
  }
  
  public void addFrames(MotionController paramMotionController)
  {
    ArrayList localArrayList = (ArrayList)mFramesMap.get(Integer.valueOf(hostId));
    if (localArrayList != null) {
      paramMotionController.addKeys(localArrayList);
    }
  }
  
  public Set getKeys()
  {
    return mFramesMap.keySet();
  }
  
  public ArrayList getMap(int paramInt)
  {
    return (ArrayList)mFramesMap.get(Integer.valueOf(paramInt));
  }
}
