package com.djp.easy_push_xiaomi;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;

import com.djp.easy_push_xiaomi.util.Utils;
import com.xiaomi.mipush.sdk.MiPushClient;

import java.lang.reflect.Method;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

/** EasyPushXiaomiPlugin */
public class EasyPushXiaomiPlugin implements FlutterPlugin, MethodCallHandler, ActivityAware {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private MethodChannel channel;
  private Context context;
  private Activity activity;

  @Override
  public void onAttachedToEngine(FlutterPluginBinding flutterPluginBinding) {
    context = flutterPluginBinding.getApplicationContext();
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "easy_push_xiaomi");
    channel.setMethodCallHandler(this);
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
    try {
      Method method = this.getClass().getDeclaredMethod(call.method, MethodCall.class, Result.class);
      method.invoke(this, call, result);
    } catch (Exception e) {
      result.notImplemented();
      e.printStackTrace();
    }
  }

  @Override
  public void onDetachedFromEngine(FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }

  @Override
  public void onAttachedToActivity(ActivityPluginBinding binding) {
    this.activity = binding.getActivity();
    if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
      if (ActivityCompat.checkSelfPermission(this.context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(this.activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE}, 1);
      }
    }
  }

  @Override
  public void onDetachedFromActivityForConfigChanges() {

  }

  @Override
  public void onReattachedToActivityForConfigChanges(ActivityPluginBinding binding) {

  }

  @Override
  public void onDetachedFromActivity() {

  }


  void registerPush(MethodCall call, Result result) {
    String appId = Utils.getParam(call, result, "appId");
    String appKey = Utils.getParam(call, result, "appKey");
    MiPushClient.registerPush(context, appId, appKey);
    result.success(null);
  }

  void unregisterPush(MethodCall call, Result result) {
    MiPushClient.unregisterPush(context);
    result.success(null);
  }

  void enablePush(MethodCall call, Result result) {
    MiPushClient.enablePush(context);
    result.success(null);
  }

  void disablePush(MethodCall call, Result result) {
    MiPushClient.disablePush(context);
    result.success(null);
  }

  void setAlias(MethodCall call, Result result) {
    String alias = Utils.getParam(call, result, "alias");
    String category = Utils.getParam(call, result, "category");
    MiPushClient.setAlias(context, alias, category);
    result.success(null);
  }

  void unsetAlias(MethodCall call, Result result) {
    String alias = Utils.getParam(call, result, "alias");
    String category = Utils.getParam(call, result, "category");
    MiPushClient.unsetAlias(context, alias, category);
    result.success(null);
  }

  void setUserAccount(MethodCall call, Result result) {
    String userAccount = Utils.getParam(call, result, "userAccount");
    String category = Utils.getParam(call, result, "category");
    MiPushClient.setUserAccount(context, userAccount, category);
    result.success(null);
  }

  void unsetUserAccount(MethodCall call, Result result) {
    String userAccount = Utils.getParam(call, result, "userAccount");
    String category = Utils.getParam(call, result, "category");
    MiPushClient.unsetUserAccount(context, userAccount, category);
    result.success(null);
  }

  void subscribe(MethodCall call, Result result) {
    String topic = Utils.getParam(call, result, "topic");
    String category = Utils.getParam(call, result, "category");
    MiPushClient.subscribe(context, topic, category);
    result.success(null);
  }

  void unsubscribe(MethodCall call, Result result) {
    String topic = Utils.getParam(call, result, "topic");
    String category = Utils.getParam(call, result, "category");
    MiPushClient.unsubscribe(context, topic, category);
    result.success(null);
  }

  void pausePush(MethodCall call, Result result) {
    String category = Utils.getParam(call, result, "category");
    MiPushClient.pausePush(context, category);
    result.success(null);
  }

  void resumePush(MethodCall call, Result result) {
    String category = Utils.getParam(call, result, "category");
    MiPushClient.resumePush(context, category);
    result.success(null);
  }

  void setAcceptTime(MethodCall call, Result result) {
    int startHour = Utils.getParam(call, result, "startHour");
    int startMin = Utils.getParam(call, result, "startMin");
    int endHour = Utils.getParam(call, result, "endHour");
    int endMin = Utils.getParam(call, result, "endMin");
    String category = Utils.getParam(call, result, "category");
    MiPushClient.setAcceptTime(context, startHour, startMin, endHour, endMin, category);
    result.success(null);
  }

  void getAllAlias(MethodCall call, Result result) {
    result.success(MiPushClient.getAllAlias(context));
  }

  void getAllTopic(MethodCall call, Result result) {
    result.success(MiPushClient.getAllTopic(context));
  }

  void getAllUserAccount(MethodCall call, Result result) {
    result.success(MiPushClient.getAllUserAccount(context));
  }

  void reportMessageClicked(MethodCall call, Result result) {
    String msgId = Utils.getParam(call, result, "msgId");
    result.success(null);
  }

  void clearNotification(MethodCall call, Result result) {
    int notifyId = Utils.getParam(call, result, "notifyId");
    MiPushClient.clearNotification(context, notifyId);
    result.success(null);
  }

  void clearAllNotification(MethodCall call, Result result) {
    MiPushClient.clearNotification(context);
    result.success(null);
  }

  void setLocalNotificationType(MethodCall call, Result result) {
    int notificationType = Utils.getParam(call, result, "notificationType");
    MiPushClient.setLocalNotificationType(context, notificationType);
    result.success(null);
  }

  void clearLocalNotificationType(MethodCall call, Result result) {
    MiPushClient.clearLocalNotificationType(context);
    result.success(null);
  }

  void getRegId(MethodCall call, Result result) {
    result.success(MiPushClient.getRegId(context));
  }
}
