package com.app.pictolike;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import android.provider.Settings.Secure;

import com.app.pictolike.mysql.MySQLCommand;
import com.app.pictolike.mysql.MySQLConnect;

public class SignUpActivity extends Activity  {



	EditText m_edtUserName;
	EditText m_edtEmail;
	EditText m_edtPassword;
	EditText m_edtBirthday;
	
	String sel_gen;
 
	
	private MySQLCommand.OnCompleteListener m_oSqlListener = new MySQLCommand.OnCompleteListener() {

		@Override
		public void OnComplete(Object result) {
			Boolean b = (Boolean) result;
			if (b.booleanValue()) {
				Toast.makeText(SignUpActivity.this, "Sign up successfully",
						Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(SignUpActivity.this,
						SignInActivity.class);
				//intent.putExtra("username", m_edtUserName.getText().toString()+"");
				startActivity(intent);
				finish();
			} else {
				Toast.makeText(SignUpActivity.this, "Sign up failed",
						Toast.LENGTH_SHORT).show();
			}

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		/** Code to change Action Bar Color */
		ActionBar bar = getActionBar();
		bar.hide();
		//ColorDrawable cd = new ColorDrawable(0xFFFBAC00);
		//bar.setBackgroundDrawable(cd);

		setContentView(R.layout.activty_signup);



		 m_edtUserName = (EditText) findViewById(R.id.reg_email_edittext);
		 m_edtEmail =    (EditText) findViewById(R.id.reg_email_edittext);
		 m_edtPassword = (EditText) findViewById(R.id.reg_password_edittext);
		 m_edtBirthday= (EditText) findViewById(R.id. reg_birthday_edittext);

		
		findViewById(R.id.sign_up_btn).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						onSignUp();
					}
				});
		
		
		findViewById(R.id.reg_email_edittext).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						//onSignUp();
						Log.d("Email Button", "Inside Email Button");
					}
				});
		
		

		findViewById(R.id.reg_password_edittext).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						//onSignUp();
						Log.d("Password Button", "Inside password Button");
					}
				});
		
		findViewById(R.id.image_gen_male).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						//onSignUp();
						Log.d("male gender Button", "select status");
						sel_gen= "male";
					}
				});
		
		findViewById(R.id.image_gen_female).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						//onSignUp();
						Log.d("female gender Button", "select status");
						sel_gen= "female";
					}
				});
		
		findViewById(R.id.sign_up_btn).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						onSignUp();
						Log.d("Login Button", "select status");
					}
				});
		
	 
	}


	public void onSignUp() {
		
		String email   = m_edtEmail.getText().toString();
		String name    =  m_edtUserName.getText().toString();
		String password= m_edtPassword.getText().toString();
		String birthday= m_edtBirthday.getText().toString();
	


		MySQLConnect.signup(name, email, password, birthday, sel_gen,
		 m_oSqlListener);
		 
		String deviceId = userPhoneIDExport();
		
		 
	}

	public String userPhoneIDExport() {
		String deviceId = Secure.getString(this.getContentResolver(),
				Secure.ANDROID_ID);
		return deviceId;
	}
}
