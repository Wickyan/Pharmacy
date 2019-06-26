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

public class ManaIndexActivity extends Activity {
//管理员登录
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mana_index);
		final EditText UserName = (EditText)findViewById(R.id.UserName);
		final EditText Password = (EditText)findViewById(R.id.Password);
		final EditText NPassword = (EditText)findViewById(R.id.NPassword);
		final Button btnOK = (Button)findViewById(R.id.btnOK);
		final Button btnCancel = (Button)findViewById(R.id.btnCancel);
		final Button btnRegist = (Button)findViewById(R.id.btnRegist);
		final Button btnbm = (Button)findViewById(R.id.btnbm);
		final SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString()+"/kusuri.db3", null);
		try {
			db.execSQL("create table VIPUser(Phone varchar(50) primary key, score integer)");
		}catch(Exception e) {
			e.printStackTrace();
		}
		btnRegist.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent it = new Intent(ManaIndexActivity.this,ManaRegistActivity.class);  
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
				NPassword.setText("");
			}
		});
		btnOK.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String u, p,n;
				u = UserName.getText().toString();
				p = Password.getText().toString();
				n = NPassword.getText().toString();
				Cursor cursor=db.rawQuery("select * from Manager where UserName='"+u+"' and Password='"+p+"'", null);
				if((cursor.getCount() > 0)&&(n.equals("123456"))) {
					Intent it = new Intent(ManaIndexActivity.this,ManaMediActivity.class);  
	                it.putExtras(it); 
	                startActivity(it);
				} else {
					Toast.makeText(ManaIndexActivity.this, "用户名或密码或内部码错误", Toast.LENGTH_LONG).show();
				}
			}
		});
         btnbm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent it = new Intent(ManaIndexActivity.this,IndexActivity.class);  
                it.putExtras(it); 
                startActivity(it);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mana_index, menu);
		return true;
	}

}
