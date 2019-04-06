import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class HomeScreen extends StatelessWidget {


  static const notificationsChannel = const MethodChannel("notifications");


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Gentle reminder"),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: <Widget>[
            RaisedButton(
              child: Text("Włącz powiadomienia"),
              onPressed: () {
                notificationsChannel.invokeMethod("startReminders");
              },
            ),
            RaisedButton(
              child: Text("Wyłącz powiaomienia"),
              onPressed: () {
                notificationsChannel.invokeMethod("stopReminders");
              },
            )
          ],
        ),
      ),
    );
  }
}
