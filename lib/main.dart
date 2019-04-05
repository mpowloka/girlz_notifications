import 'package:flutter/material.dart';
import 'package:girlz_notifications/home.dart';

void main() => runApp(MaterialApp(
      title: "Gentle reminder",
      theme: ThemeData(
        primaryColorDark: Color.fromARGB(255, 53, 180, 142),
        primaryColor: Color.fromARGB(255, 98, 174, 116),
      ),
      home: HomeScreen(),
    ));
