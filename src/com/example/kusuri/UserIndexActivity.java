package com.example.kusuri;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserIndexActivity extends Activity {
//用户登录
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_index);
		final EditText UserName = (EditText)findViewById(R.id.UserName);
		final EditText Password = (EditText)findViewById(R.id.Password);
		final Button btnOK = (Button)findViewById(R.id.btnOK);
		final Button btnCancel = (Button)findViewById(R.id.btnCancel);
		final Button btnRegist = (Button)findViewById(R.id.btnRegist);
		final Button btnbu = (Button)findViewById(R.id.btnbu);
		final SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString()+"/kusuri.db3", null);
		
		btnRegist.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent it = new Intent(UserIndexActivity.this,UserRegisitActivity.class);  
                it.putExtras(it); 
                startActivity(it);
			}
		});
		btnCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				UserName.setText("");
				Password.setText("");
			}
		});
		btnOK.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String u, p;
				u = UserName.getText().toString();
				p = Password.getText().toString();
				Cursor cursor=db.rawQuery("select * from UserInfo where UserName='"+u+"' and Password='"+p+"'", null);
				if(cursor.getCount() > 0) {
					Intent it = new Intent(UserIndexActivity.this,UserMediActivity.class);  
					it.putExtra("UserName", u);
	                it.putExtras(it); 
	                startActivity(it);
				} else {
					Toast.makeText(UserIndexActivity.this, "用户名或密码错误", Toast.LENGTH_LONG).show();
				}
			}
		});
        btnbu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent it = new Intent(UserIndexActivity.this,IndexActivity.class);  
                it.putExtras(it); 
                startActivity(it);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_index, menu);
		return true;
	}

}
