package com.multiverse.tipcalculator;

import java.text.NumberFormat; //class for currency formatting

import android.app.Activity; //base class for activities
import android.os.Bundle; //to save state information when app gets back to foreground
import android.text.Editable; //for EditText event handling
import android.text.TextWatcher; //EditText Listener that 'watches' whether text is changed
import android.widget.EditText; //to get user input in form of double number
import android.widget.SeekBar; //used to assign a custom tip 
import android.widget.SeekBar.OnSeekBarChangeListener; //SeekBar listener for SeekBar even handling
import android.widget.TextView; //for various input and output views

//This is Activity executable component (sort of analog of MainClass for a typical Java program)
//other types of executable components are services, content providers and broadcast receivers(no user here)
public class MainActivity extends Activity {
	//declare objects and variables
	private static final NumberFormat currencyFormat = 
			NumberFormat.getCurrencyInstance(); //to get the currency according to the user's locale
	private static final NumberFormat percentFormat = 
			NumberFormat.getPercentInstance(); //to display updated percent in text view 
	private double billAmount = 0.0; //bill amount entered by the user
	private double customPercent = 0.18; //Initial custom tip percentage
	private TextView amountDisplayTextView; //displays formatted bill amount
	private TextView percentCustomTextView; //displays percent selected by user
	private TextView tip10TextView; //to display 10% tip
	private TextView total10TextView; //to display 10% total
	private TextView tip15TextView; //to display 15% tip
	private TextView total15TextView; //to display 15% total
	private TextView tipCustomTextView; //to display custom tip (tip selected by user)
	private TextView totalCustomTextView; //to display custom total 
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); //we need to execute code in superclass in addition to the code written in the method
		setContentView(R.layout.activity_main); //inflate GUI with widgets
		//use method findViewById to get references to the GUI components 
		//that are defined as subclasses of R class in gen/com*/R.java
		//these subclasses are declared static so they can be accesses as 
		//R.<class name>.<variable name>, i.e., R.id.tip10PercentTextView
		amountDisplayTextView = 
				(TextView) findViewById(R.id.amountDisplayTextView);
		percentCustomTextView = 
				(TextView) findViewById(R.id.percentCustomTextView);
		tip10TextView = (TextView)findViewById(R.id.tip10TextView);
		total10TextView = (TextView)findViewById(R.id.total10TextView);
		tip15TextView = (TextView) findViewById(R.id.tip15TextView);
		total15TextView = (TextView) findViewById(R.id.total15TextView);
		tipCustomTextView = (TextView) findViewById(R.id.tipCustomTextView);
		totalCustomTextView = (TextView) findViewById(R.id.totalCustomTextView);
		//to display the entered via Edit view check amount 
		amountDisplayTextView.setText(currencyFormat.format(billAmount));
		updateTen();
		updateStandard();
		updateCustom();
		
		//set a listener TextChangedListener to handle events that occur when user
		//changes text in EditText View
		EditText amountEditText = (EditText) findViewById(R.id.amountEditText);
		amountEditText.addTextChangedListener(amountEditTextWatcher);
		//set  a listener OnSeekBarListener to handle events of seek bar changes 
		//and to record the changed amount
		SeekBar customTipSeekBar = 
				(SeekBar) findViewById(R.id.customTipSeekBar);
		customTipSeekBar.setOnSeekBarChangeListener(customSeekBarListener);
		

	}
	//method that updates 10% tip 
	private void updateTen(){
		//calculate 10% tip 
		double tenPercentTip = billAmount*0.10;
		//display 10% tip and total amount in the text views
		tip10TextView.setText(currencyFormat.format(tenPercentTip));
		total10TextView.setText(currencyFormat.format(billAmount+tenPercentTip));
	}
	//method that updates 15% tip TextView values
	private void updateStandard()
	{
		//calculate 15% tip and total
		double fifteenPercentTip = billAmount * 0.15;
		double fifteenPercentTotal = billAmount + fifteenPercentTip;
		//display 15% tip and total formatted as currency
		tip15TextView.setText(currencyFormat.format(fifteenPercentTip));
		total15TextView.setText(currencyFormat.format(fifteenPercentTotal));
		}
	//method that updates 15% tip TextView values
	private void updateCustom()
	{
		//show custom percent in the TextView
		percentCustomTextView.setText(percentFormat.format(customPercent));
		//calculate the custom tip and total
		double customTip = billAmount * customPercent;
		double customTotal = billAmount + customTip;
		//display custom tip and total formatted as currency
		tipCustomTextView.setText(currencyFormat.format(customTip));
		totalCustomTextView.setText(currencyFormat.format(customTotal));
		}
	//anonymous-inner-class that implements interface OnSeekBarChangeListener
	//to perform 'interface contract' we include empty methods (the methods that are 
	// useless for this app)
	private OnSeekBarChangeListener customSeekBarListener = 
			new OnSeekBarChangeListener() {
				
				@Override
				public void onStopTrackingTouch(SeekBar seekBar) {	

				}
				
				@Override
				public void onStartTrackingTouch(SeekBar seekBar) {	
				}
				
				@Override
				public void onProgressChanged(SeekBar seekBar, int progress,
						boolean fromUser) {
					customPercent = progress/100.0;
					updateCustom();
					
				}
			};
	//anonymous-inner-class that implements interface TextWatcher
	//to perform 'interface contract' we include empty methods (the methods that are 
	// useless for this app)
		private TextWatcher amountEditTextWatcher = 
				new TextWatcher() {
					//called when a user enters amount of money
					@Override
					public void onTextChanged(CharSequence s, int start, int before, int count) {
						//convert amount in editText to a double
						try{
							billAmount = Double.parseDouble(s.toString())/100.0;
						}catch(NumberFormatException e){
							billAmount = 0.0; //set t default if exception occurs
							//very useful to use try{}catch{} statement because when the app starts 
							//billAmount is not assigned
						}
						//display currency formatted bill amount
					amountDisplayTextView.setText(currencyFormat.format(billAmount));
					updateTen();
					updateCustom();
					updateStandard();
					}
					
					@Override
					public void beforeTextChanged(CharSequence s, int start, int count,
							int after) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void afterTextChanged(Editable s) {
						// TODO Auto-generated method stub
						
					}
				};
	
}
