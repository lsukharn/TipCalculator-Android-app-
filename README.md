TipCalculator-Android-app-
==========================
Author: Leonid Sukharnikov

This is an Android application that calculates tips. Target API=19

It calculates tips for 10% and 15% by default and custom tips (using SeekBar View).
Custom seek bar is conveniently located below the Views that display calculated tips.
User cannot enter anything but digits, which precludes possibility of an error.
This app can be used for Russian locale as well (see translated strings.xml in res/values-ru). In this case the tips are calculated in Rubles. 

The app utilizes the following classes and interfaces:
java.text.NumberFormat; //class for currency formatting
android.app.Activity; //base class for activities
android.os.Bundle; //to save state information when app gets back to foreground
android.text.Editable; //for EditText event handling
android.text.TextWatcher; //EditText Listener that 'watches' whether text is changed
android.widget.EditText; //to get user input in form of double number
android.widget.SeekBar; //used to assign a custom tip 
android.widget.SeekBar.OnSeekBarChangeListener; //SeekBar listener for SeekBar even handling
android.widget.TextView; //for various input and output views 
