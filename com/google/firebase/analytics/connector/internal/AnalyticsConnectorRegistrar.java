package com.google.firebase.analytics.connector.internal;

import android.content.Context;
import android.support.annotation.Keep;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.components.Component;
import com.google.firebase.components.Component.Builder;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.events.Subscriber;
import java.util.Collections;
import java.util.List;

@Keep
@KeepForSdk
public class AnalyticsConnectorRegistrar
  implements ComponentRegistrar
{
  public AnalyticsConnectorRegistrar() {}
  
  public List getComponents()
  {
    return Collections.singletonList(Component.builder(AnalyticsConnector.class).get(Dependency.required(FirebaseApp.class)).get(Dependency.required(Context.class)).get(Dependency.required(Subscriber.class)).factory(Filter.zzbsl).eagerInDefaultApp().build());
  }
}
