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
              color: Theme.of(context).primaryColor,
              child: Text("Do it, Michał"),
              onPressed: () {
                notificationsChannel.invokeMethod("startReminders");
              },
            ),
            RaisedButton(
              color: Colors.red,
              child: Text("Stop this madness"),
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
