import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class HomeScreen extends StatelessWidget {


  static const notificationsChannel = const MethodChannel("notifications");


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Que"),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: <Widget>[
            RaisedButton(
              child: Text("Initialize"),
              onPressed: () {
                notificationsChannel.invokeMethod("startReminders");
              },
            ),
            RaisedButton(
              child: Text("Disable"),
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
