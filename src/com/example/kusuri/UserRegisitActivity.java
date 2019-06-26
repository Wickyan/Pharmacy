package com.example.kusuri;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserRegisitActivity extends Activity {
	//用户注册
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_regisit);
		final EditText UserName1 = (EditText)findViewById(R.id.UserName2);
		final EditText Name1 = (EditText)findViewById(R.id.Name2);
		final EditText Tele1 = (EditText)findViewById(R.id.Tele2);
		final EditText Password1 = (EditText)findViewById(R.id.Password2);
		final EditText RePassword1 = (EditText)findViewById(R.id.RePassword2);
		final Button btnRegist1 = (Button)findViewById(R.id.btnRegist2);
		final Button btnCancel1 = (Button)findViewById(R.id.btnCancel2);
		final Button btnBack3 = (Button)findViewById(R.id.btnBack3);
		final SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString()+"/kusuri.db3", null);
		btnCancel1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				UserName1.setText(""); Name1.setText("");
				Tele1.setText(""); Password1.setText("");
				RePassword1.setText("");
			}
		});
		btnRegist1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String a, b, c, d, e;
				a = UserName1.getText().toString();
				b = Password1.getText().toString();
				c = Name1.getText().toString();
				d = Tele1.getText().toString();
				e = RePassword1.getText().toString();
				if(a.equals("")||b.equals("")||c.equals("")||d.equals("")||e.equals(""))
				{
					Toast.makeText(UserRegisitActivity.this, "信息不足，无法注册", Toast.LENGTH_LONG).show();
				}else{
				if(b.equals(e)) {
					db.execSQL("insert into UserInfo values(?,?,?,?)", new String[]{a, b, c, d});
					Toast.makeText(UserRegisitActivity.this, "注册成功", Toast.LENGTH_LONG).show();
				}
				else {
					Toast.makeText(UserRegisitActivity.this, "密码不统一", Toast.LENGTH_LONG).show();
				}
			}
			}
		});
        btnBack3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent it = new Intent(UserRegisitActivity.this,UserIndexActivity.class);  
                it.putExtras(it); 
                startActivity(it);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_regisit, menu);
		return true;
	}

}
