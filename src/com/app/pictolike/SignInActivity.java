package com.app.pictolike;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.app.pictolike.data.MyPeople;
import com.app.pictolike.mysql.MySQLCommand;
import com.app.pictolike.mysql.MySQLConnect;

public class SignInActivity extends Activity  {
	ImageView SignIn,Join;
	EditText  userName,userPassword;
	static String name;
	private MySQLCommand.OnCompleteListener m_oSqlListener = new MySQLCommand.OnCompleteListener() {
		
		@Override
		public void OnComplete(Object result) {
			MyPeople people = (MyPeople) result;
			if (people == null) {
				Toast.makeText(SignInActivity.this, "Sign in failed username", Toast.LENGTH_SHORT).show();
				return;
			}
			
			

			
			Intent intent = new Intent(SignInActivity.this, SettingsScreenActivity.class);
			startActivity(intent);
			//SignInActivity.this.startActivity(intent);
			
			finish();
			//SignInActivity.this.finishActivity();
		}
	};
	

    LinearLayout signInActivity;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	
		/** Code to change Action Bar Color */
		ActionBar bar = getActionBar();
		bar.hide();
		//ColorDrawable cd = new ColorDrawable(0xFFFBAC00);
		//bar.setBackgroundDrawable(cd);
		
		setContentView(R.layout.actvity_signin);
		
		Intent intent = getIntent();
		String username = intent.getStringExtra("username");
		
//		SignIn=(ImageView)findViewById(R.id.Login);
//		
//		signInActivity=(LinearLayout)findViewById(R.id.signInAcitivty);


//        
//		Join=(ImageView)findViewById(R.id.JoinUs);
		userName=(EditText)findViewById(R.id.email_edittext);
		userPassword=(EditText)findViewById(R.id.password_edittext);
//		SignIn.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				// Toast.makeText(getApplicationContext(),"Email Sent to "+ userName.getText(), Toast.LENGTH_SHORT).show();
//				OnSignIn();
//			}
//		});
//		
/*		Join.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			Intent joinUsActivity=new Intent(SignInActivity.this,SignUpActivity.class);
			startActivity(joinUsActivity);
			
			}
		});*/
	
		 
		findViewById(R.id.button_comein).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						OnSignIn();
					}
				});
		
		
		findViewById(R.id.btn_forgot_password).setOnClickListener(
				new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent i = new Intent(SignInActivity.this, ForgotPasswordScreenActivity.class);
					SignInActivity.this.startActivity(i);
				}
			
			});
		
	
	}
	
	protected void finishActivity() {
		// TODO Auto-generated method stub
		
	}

	private void OnSignIn() {
		String name = userName.getText().toString();
		Log.d("UserName:",userName.getText().toString());
		 
	 
		 
		String password = userPassword.getText().toString();
		MySQLConnect.signin(name, password, m_oSqlListener);
		
		if (name == null) {
			Toast.makeText(SignInActivity.this, "Please put in the username", Toast.LENGTH_SHORT).show();
			return;
		}
		
	}
	
	
}
