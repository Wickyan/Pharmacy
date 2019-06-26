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

public class ManaRegistActivity extends Activity {
//管理员注册
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mana_regist);
		final EditText UserName1 = (EditText)findViewById(R.id.UserName1);
		final EditText Name1 = (EditText)findViewById(R.id.Name1);
		final EditText Tele1 = (EditText)findViewById(R.id.Tele1);
		final EditText Password1 = (EditText)findViewById(R.id.Password1);
		final EditText RePassword1 = (EditText)findViewById(R.id.RePassword1);
		final Button btnRegist1 = (Button)findViewById(R.id.btnRegist1);
		final Button btnCancel1 = (Button)findViewById(R.id.btnCancel1);
		final Button btnBack = (Button)findViewById(R.id.btnBack);
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
					Toast.makeText(ManaRegistActivity.this, "信息不足，无法注册", Toast.LENGTH_LONG).show();
				}else{
				if(b.equals(e)) {
					db.execSQL("insert into Manager values(?,?,?,?)", new String[]{a, b, c, d});
					Toast.makeText(ManaRegistActivity.this, "注册成功", Toast.LENGTH_LONG).show();
				} 
				 
				
				else {
					Toast.makeText(ManaRegistActivity.this, "密码不统一", Toast.LENGTH_LONG).show();
				}
			}
				}
		});
           btnBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent it = new Intent(ManaRegistActivity.this,ManaIndexActivity.class);  
                it.putExtras(it); 
                startActivity(it);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mana_regist, menu);
		return true;
	}

}
