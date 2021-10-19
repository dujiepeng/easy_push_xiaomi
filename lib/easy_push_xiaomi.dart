import 'dart:async';

import 'package:flutter/services.dart';

class EasyPushXiaomi {
  static const MethodChannel _channel = MethodChannel('easy_push_xiaomi');

  static Future<void> registerPush(String appId, String appKey) async {
    return await _channel.invokeMethod("registerPush",
        {"appId": appId, "appKey": appKey}.cast<String, String>());
  }

  static Future<void> unregisterPush() async {
    return await _channel.invokeMethod("unregisterPush");
  }

  static Future<void> enablePush() async {
    return await _channel.invokeMethod("enablePush");
  }

  static Future<void> disablePush() async {
    return await _channel.invokeMethod("disablePush");
  }

  static Future<void> setAlias(String alias) async {
    return await _channel.invokeMethod("setAlias", {"alias": alias});
  }

  static Future<void> unsetAlias(String alias) async {
    return await _channel.invokeMethod("unsetAlias", {"alias": alias});
  }

  static Future<void> setUserAccount(String userAccount) async {
    return await _channel
        .invokeMethod("setUserAccount", {"userAccount": userAccount});
  }

  static Future<void> unsetUserAccount(String userAccount) async {
    return await _channel
        .invokeMethod("unsetUserAccount", {"userAccount": userAccount});
  }

  static Future<void> subscribe(String topic) async {
    return await _channel.invokeMethod("subscribe", {"topic": topic});
  }

  static Future<void> unsubscribe(String topic) async {
    return await _channel.invokeMethod("unsubscribe", {"topic": topic});
  }

  static Future<void> pausePush() async {
    return await _channel.invokeMethod("pausePush");
  }

  static Future<void> resumePush() async {
    return await _channel.invokeMethod("resumePush");
  }

  static Future<void> setAcceptTime(
      int startHour, int startMin, int endHour, int endMin) async {
    return await _channel.invokeMethod("setAcceptTime", {
      "startHour": startHour,
      "startMin": startMin,
      "endHour": endHour,
      "endMin": endMin,
    });
  }

  static Future<List<String?>> getAllAlias() async {
    return (await _channel.invokeMethod('getAllAlias')).cast<String>();
  }

  static Future<List<String?>> getAllTopic() async {
    return (await _channel.invokeMethod('getAllTopic')).cast<String>();
  }

  static Future<List<String?>> getAllUserAccount() async {
    return (await _channel.invokeMethod('getAllTopic')).cast<String>();
  }

  static Future<void> reportMessageClicked(String msgId) async {
    return await _channel
        .invokeMethod("reportMessageClicked", {"msgId": msgId});
  }

  static Future<void> clearNotification(String notifyId) async {
    return await _channel
        .invokeMethod("clearNotification", {"notifyId": notifyId});
  }

  static Future<void> clearAllNotification() async {
    return await _channel.invokeMethod("clearAllNotification");
  }

  static Future<void> setLocalNotificationType(int notificationType) async {
    return await _channel.invokeMethod(
        "setLocalNotificationType", {"notifyId": notificationType});
  }

  static Future<void> clearLocalNotificationType() async {
    return await _channel.invokeMethod("clearLocalNotificationType");
  }

  static Future<String> getRegId() async {
    return await _channel.invokeMethod("getRegId");
  }
}
