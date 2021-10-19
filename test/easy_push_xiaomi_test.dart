import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:easy_push_xiaomi/easy_push_xiaomi.dart';

void main() {
  const MethodChannel channel = MethodChannel('easy_push_xiaomi');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await EasyPushXiaomi.platformVersion, '42');
  });
}
