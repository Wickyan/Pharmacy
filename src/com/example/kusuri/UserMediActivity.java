package com.example.kusuri;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class UserMediActivity extends Activity {
//用户主界面
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_medi);
		
		
		
		final ListView listview = (ListView)findViewById(R.id.listview1);
		final Button userbuy = (Button)findViewById(R.id.userbuy);
		final Button btnut = (Button)findViewById(R.id.btnut);
		final Button btnCheck = (Button)findViewById(R.id.btnCheck);
		final EditText Mname = (EditText)findViewById(R.id.Mname);
		final Button btnSee = (Button)findViewById(R.id.butSee);
		
		final SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString()+"/kusuri.db3", null);
		
		Cursor cursor=db.rawQuery("select * from Medicine", null);
		final String[] med = new String[cursor.getCount()];
		int i = 0;
		while(true){
			if(cursor.moveToNext()==false)
				break;
			String n = cursor.getString(1);
			med[i++] = n;
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(UserMediActivity.this, android.R.layout.simple_list_item_1, med);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				Intent it1=getIntent();
				String username=it1.getStringExtra("UserName");
				String n = med[arg2];
				Intent it = new Intent(UserMediActivity.this,UserMedinoticeActivity.class);  
                it.putExtra("n", n); 
                it.putExtra("UserName1", username);
                startActivity(it);
			}
		});
		
		userbuy.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent it1=getIntent();
				String username=it1.getStringExtra("UserName");
				Intent it = new Intent(UserMediActivity.this,UserVipActivity.class);  
                it.putExtras(it); 
                it.putExtra("UserName1", username);
                startActivity(it);
			}
		});
		
			 
		
       btnut.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent it = new Intent(UserMediActivity.this,IndexActivity.class);  
                it.putExtras(it); 
                startActivity(it);
			}
		});
       btnSee.setOnClickListener(new OnClickListener() {//查看购买记录
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent it1=getIntent();
				String username=it1.getStringExtra("UserName");
				Intent it = new Intent(UserMediActivity.this,ConlistActivity.class);  
               it.putExtras(it); 
               it.putExtra("UserName1", username);
               startActivity(it);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_medi, menu);
		return true;
	}

}
