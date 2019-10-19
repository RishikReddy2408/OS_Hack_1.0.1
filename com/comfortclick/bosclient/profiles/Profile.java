package com.comfortclick.bosclient.profiles;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URI;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.regex.Pattern;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.json.JSONObject;

public class Profile
{
  public String accessID;
  public String gateadd;
  public long lt;
  public String la;
  public boolean manual;
  public String pwd;
  public String profilename;
  public String ;
  public boolean remeberme;
  public String sa;
  public String user;
  
  public Profile(Controller paramController)
  {
    manual = false;
    accessID = AccessID;
    profilename = ServerName;
    la = la;
    sa = PublicAddress;
    gateadd = gateadd;
    publickey = publickey;
    user = "";
    pwd = "";
    lt = new Date().getTime();
    remeberme = true;
  }
  
  public Profile(Profile paramProfile)
  {
    manual = manual;
    accessID = accessID;
    profilename = profilename;
    la = la;
    sa = sa;
    gateadd = gateadd;
    publickey = publickey;
    user = user;
    pwd = pwd;
    lt = lt;
    remeberme = remeberme;
  }
  
  public Profile(Boolean paramBoolean, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    manual = paramBoolean.booleanValue();
    accessID = "";
    profilename = paramString1;
    sa = paramString2;
    user = paramString3;
    pwd = paramString4;
    lt = new Date().getTime();
    remeberme = true;
  }
  
  public Profile(String paramString1, String paramString2, String paramString3)
  {
    manual = false;
    accessID = paramString1;
    user = paramString2;
    pwd = paramString3;
    lt = new Date().getTime();
    remeberme = true;
  }
  
  private ArrayList getDeviceIPAddresses()
  {
    localArrayList = new ArrayList();
    try
    {
      Enumeration localEnumeration = NetworkInterface.getNetworkInterfaces();
      boolean bool = localEnumeration.hasMoreElements();
      if (bool)
      {
        Object localObject1 = localEnumeration.nextElement();
        localObject1 = (NetworkInterface)localObject1;
        localObject1 = ((NetworkInterface)localObject1).getInetAddresses();
        for (;;)
        {
          bool = ((Enumeration)localObject1).hasMoreElements();
          if (!bool) {
            break;
          }
          Object localObject2 = ((Enumeration)localObject1).nextElement();
          localObject2 = (InetAddress)localObject2;
          bool = ((InetAddress)localObject2).isLoopbackAddress();
          if (!bool)
          {
            bool = ((InetAddress)localObject2).isLinkla();
            if (!bool)
            {
              bool = ((InetAddress)localObject2).isSitela();
              if (bool) {
                localArrayList.add(((InetAddress)localObject2).getAddress());
              }
            }
          }
        }
      }
      return localArrayList;
    }
    catch (SocketException localSocketException)
    {
      localSocketException.printStackTrace();
    }
  }
  
  private Boolean isLocalIpAddress(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return Boolean.valueOf(false);
    }
    try
    {
      paramString = new URI(paramString);
      Object localObject1 = paramString.getHost();
      Object localObject2 = Patterns.IP_ADDRESS;
      boolean bool = ((String)localObject1).matches(((Pattern)localObject2).toString());
      if (!bool) {
        return Boolean.valueOf(false);
      }
      paramString = InetAddress.getByName(paramString.getHost()).getAddress();
      localObject1 = getDeviceIPAddresses();
      int i = 0;
      for (;;)
      {
        int j = ((ArrayList)localObject1).size();
        if (i >= j) {
          break;
        }
        localObject2 = ((ArrayList)localObject1).get(i);
        localObject2 = (byte[])localObject2;
        int[] arrayOfInt1 = new int[localObject2.length];
        int[] arrayOfInt2 = new int[paramString.length];
        j = 0;
        while (j < localObject2.length)
        {
          localObject2[j] &= 0xFF;
          j += 1;
        }
        j = 0;
        while (j < arrayOfInt2.length)
        {
          paramString[j] &= 0xFF;
          j += 1;
        }
        j = arrayOfInt1[0];
        if (j <= 127)
        {
          if (arrayOfInt2[0] != arrayOfInt1[0]) {}
        }
        else {
          while (j <= 191 ? (arrayOfInt2[0] == arrayOfInt1[0]) || (arrayOfInt2[1] == arrayOfInt1[1]) : (j <= 223) && (arrayOfInt2[0] == arrayOfInt1[0]) && (arrayOfInt2[1] == arrayOfInt1[1]) && (arrayOfInt2[2] == arrayOfInt1[2]))
          {
            j = 1;
            break;
          }
        }
        j = 0;
        if (j != 0) {
          return Boolean.valueOf(true);
        }
        i += 1;
      }
      return Boolean.valueOf(false);
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public void connect(IProfileCallback paramIProfileCallback)
  {
    if (manual)
    {
      if (isLocalIpAddress(la).booleanValue())
      {
        paramIProfileCallback.connectToAddress(la);
        return;
      }
      if (!TextUtils.isEmpty(sa)) {
        paramIProfileCallback.connectToAddress(sa);
      }
    }
    else
    {
      GetsaTask localGetsaTask = new GetsaTask(null);
      Receiver = paramIProfileCallback;
      Profile = this;
      localGetsaTask.execute(new String[] { "" });
    }
  }
  
  public String getInfoText()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Object localObject;
    if (manual)
    {
      if (!TextUtils.isEmpty(sa)) {
        localObject = sa;
      } else {
        localObject = la;
      }
    }
    else {
      localObject = accessID;
    }
    localStringBuilder.append((String)localObject);
    if (!TextUtils.isEmpty(user))
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(" (");
      ((StringBuilder)localObject).append(user);
      ((StringBuilder)localObject).append(")");
      localObject = ((StringBuilder)localObject).toString();
    }
    else
    {
      localObject = "";
    }
    localStringBuilder.append((String)localObject);
    return localStringBuilder.toString();
  }
  
  public void update(Controller paramController)
  {
    profilename = ServerName;
    la = la;
    sa = PublicAddress;
    gateadd = gateadd;
    publickey = publickey;
    lt = new Date().getTime();
  }
  
  public void updatePushToken(String paramString1, String paramString2)
  {
    GetsaTask localGetsaTask = new GetsaTask(null);
    UpdatePush = true;
    OldToken = paramString1;
    NewToken = paramString2;
    localGetsaTask.execute(new String[] { "" });
  }
  
  private class GetsaTask
    extends AsyncTask<String, Void, String>
  {
    String NewToken;
    String OldToken;
    Profile Profile;
    IProfileCallback Receiver;
    boolean UpdatePush;
    
    private GetsaTask() {}
    
    private Boolean CheckAccessID(String paramString)
    {
      if (manual) {
        return Boolean.valueOf(true);
      }
      try
      {
        Object localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append(paramString);
        ((StringBuilder)localObject1).append("GetAccessID");
        Object localObject2 = new URL(((StringBuilder)localObject1).toString());
        paramString = new HostnameVerifier()
        {
          public boolean verify(String paramAnonymousString, SSLSession paramAnonymousSSLSession)
          {
            return paramAnonymousString.equals(val$url.getHost());
          }
        };
        localObject1 = SSLContext.getInstance("TLS");
        Object localObject3 = new X509TrustManager()
        {
          public void checkClientTrusted(X509Certificate[] paramAnonymousArrayOfX509Certificate, String paramAnonymousString)
            throws CertificateException
          {}
          
          public void checkServerTrusted(X509Certificate[] paramAnonymousArrayOfX509Certificate, String paramAnonymousString)
            throws CertificateException
          {}
          
          public X509Certificate[] getAcceptedIssuers()
          {
            return new X509Certificate[0];
          }
        };
        SecureRandom localSecureRandom = new SecureRandom();
        localObject3 = (TrustManager[])new X509TrustManager[] { localObject3 };
        ((SSLContext)localObject1).init(null, (TrustManager[])localObject3, localSecureRandom);
        localObject2 = ((URL)localObject2).openConnection();
        localObject2 = (HttpsURLConnection)localObject2;
        ((HttpsURLConnection)localObject2).setHostnameVerifier(paramString);
        ((HttpsURLConnection)localObject2).setSSLSocketFactory(((SSLContext)localObject1).getSocketFactory());
        ((HttpsURLConnection)localObject2).setReadTimeout(3000);
        ((HttpsURLConnection)localObject2).setConnectTimeout(3000);
        ((HttpsURLConnection)localObject2).setRequestMethod("GET");
        int i = ((HttpsURLConnection)localObject2).getResponseCode();
        if (i == 200)
        {
          paramString = ((HttpsURLConnection)localObject2).getInputStream();
          paramString = new BufferedReader(new InputStreamReader(paramString)).readLine();
          paramString = new Gson().fromJson(paramString, String.class);
          paramString = (String)paramString;
          localObject1 = accessID;
          boolean bool = paramString.equals(localObject1);
          if (bool) {
            return Boolean.valueOf(true);
          }
        }
      }
      catch (Exception paramString)
      {
        Log.d(paramString.getMessage(), paramString.toString());
      }
      return Boolean.valueOf(false);
    }
    
    protected String doInBackground(String... paramVarArgs)
    {
      try
      {
        paramVarArgs = new StringBuilder();
        Object localObject1 = Receiver;
        paramVarArgs.append(((IProfileCallback)localObject1).getCloudUrl());
        paramVarArgs.append("WebService/BOSService.asmx/GetsaesJson");
        paramVarArgs = paramVarArgs.toString();
        paramVarArgs = new URL(paramVarArgs).openConnection();
        paramVarArgs = (HttpURLConnection)paramVarArgs;
        paramVarArgs.setReadTimeout(5000);
        paramVarArgs.setConnectTimeout(5000);
        paramVarArgs.setRequestMethod("POST");
        paramVarArgs.setDoInput(true);
        paramVarArgs.setDoOutput(true);
        localObject1 = paramVarArgs.getOutputStream();
        Object localObject2 = new BufferedWriter(new OutputStreamWriter((OutputStream)localObject1, "UTF-8"));
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("accessID=");
        String str = accessID;
        localStringBuilder.append(str);
        ((BufferedWriter)localObject2).write(localStringBuilder.toString());
        ((BufferedWriter)localObject2).flush();
        ((BufferedWriter)localObject2).close();
        ((OutputStream)localObject1).close();
        localObject1 = new StringBuilder();
        paramVarArgs = new BufferedReader(new InputStreamReader(paramVarArgs.getInputStream()));
        for (;;)
        {
          localObject2 = paramVarArgs.readLine();
          if (localObject2 == null) {
            break;
          }
          ((StringBuilder)localObject1).append((String)localObject2);
        }
        paramVarArgs = new Gson();
        localObject2 = new TypeToken() {}.getType();
        paramVarArgs = paramVarArgs.fromJson(((StringBuilder)localObject1).toString(), (Type)localObject2);
        paramVarArgs = (Profile.saes)paramVarArgs;
        sa = sa;
        la = la;
        gateadd = gateadd;
        publickey = publickey;
        paramVarArgs = Receiver;
        localObject1 = Profile;
        paramVarArgs.updateProfile((Profile)localObject1);
      }
      catch (Exception paramVarArgs)
      {
        for (;;) {}
      }
      if ((Profile.this.isLocalIpAddress(la).booleanValue()) && (CheckAccessID(la).booleanValue())) {
        return la;
      }
      if (!TextUtils.isEmpty(sa)) {
        return sa;
      }
      if (!TextUtils.isEmpty(gateadd)) {
        return gateadd;
      }
      return "";
    }
    
    protected void onPostExecute(String paramString)
    {
      if (!paramString.isEmpty())
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(paramString);
        if (paramString.endsWith("/")) {
          paramString = "";
        } else {
          paramString = "/";
        }
        localStringBuilder.append(paramString);
        paramString = localStringBuilder.toString();
        if (Receiver != null)
        {
          Receiver.connectToAddress(paramString);
          return;
        }
        if (UpdatePush) {
          updatePushToken(paramString, OldToken, NewToken);
        }
      }
      else if (Receiver != null)
      {
        Receiver.disconnect();
      }
    }
    
    void updatePushToken(final String paramString1, final String paramString2, final String paramString3)
    {
      new Thread(new Runnable()
      {
        public void run()
        {
          try
          {
            Object localObject1 = new StringBuilder();
            Object localObject2 = paramString1;
            ((StringBuilder)localObject1).append((String)localObject2);
            ((StringBuilder)localObject1).append("UpdateAndroidPushToken");
            localObject1 = new URL(((StringBuilder)localObject1).toString()).openConnection();
            localObject1 = (HttpURLConnection)localObject1;
            ((HttpURLConnection)localObject1).setReadTimeout(15000);
            ((HttpURLConnection)localObject1).setConnectTimeout(15000);
            ((HttpURLConnection)localObject1).setRequestMethod("POST");
            ((HttpURLConnection)localObject1).setRequestProperty("Content-Type", "application/json; charset=utf-8");
            ((HttpURLConnection)localObject1).setDoInput(true);
            localObject1 = ((HttpURLConnection)localObject1).getOutputStream();
            localObject2 = new BufferedWriter(new OutputStreamWriter((OutputStream)localObject1, "UTF-8"));
            JSONObject localJSONObject = new JSONObject();
            String str = paramString2;
            localJSONObject.put("OldToken", str);
            str = paramString3;
            localJSONObject.put("NewToken", str);
            ((BufferedWriter)localObject2).write(localJSONObject.toString());
            ((BufferedWriter)localObject2).flush();
            ((BufferedWriter)localObject2).close();
            ((OutputStream)localObject1).close();
            return;
          }
          catch (Exception localException)
          {
            Log.d(localException.getMessage(), localException.toString());
          }
        }
      }).start();
    }
  }
  
  class saes
  {
    public String gateadd;
    public String la;
    public String publickey;
    public String sa;
    
    saes() {}
  }
}
