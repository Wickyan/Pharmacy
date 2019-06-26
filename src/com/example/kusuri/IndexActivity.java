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
import android.widget.LinearLayout;

public class IndexActivity extends Activity {
	//≥ı º“≥√Ê
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		final Button btnMana = (Button)findViewById(R.id.btnMana);
		final Button btnUser = (Button)findViewById(R.id.btnUser);
		final SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString()+"/kusuri.db3", null);
		try {
			db.execSQL("create table Manager (UserName varchar(50)primary key , Password varchar(50), Name varchar(50) , Tele varchar(50))");
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
			db.execSQL("create table UserInfo(UserName varchar(50)primary key , Password varchar(50), Name varchar(50), Tele varchar(50))");
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
			db.execSQL("create table Medicine(MNumber varchar(50)primary key, MName varchar(50), MFrom varchar(50),BPrice varchar(50), MPrice varchar(50), MCount varchar(50))");
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
			db.execSQL("create table Consumlist (ConUserName varchar(50) primary key , ConName varchar(50), ConMNumber varchar(50), ConMName varchar(50),ConCount varchar(50),Consum varchar(50))");
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
			db.execSQL("create table Consumlist2 (ConUserName varchar(50) , ConName varchar(50), ConMNumber varchar(50), ConMName varchar(50),ConCount varchar(50),Consum varchar(50))");
		}catch(Exception e) {
			e.printStackTrace();
		}
		btnMana.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent it = new Intent(IndexActivity.this,ManaIndexActivity.class);  
                it.putExtras(it); 
                startActivity(it);
			}
		});
		btnUser.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent it = new Intent(IndexActivity.this,UserIndexActivity.class);  
                it.putExtras(it); 
                startActivity(it);
			}
		});
		
		/*final EditText list = (EditText)findViewById(R.id.list);
		list.setText("");
		Cursor cursor=db.rawQuery("select * from Manager", null);
		while(true){
			if(cursor.moveToNext()==false)
				break;
			String i=cursor.getString(0);
			String n=cursor.getString(1);
			String s=cursor.getString(2);
			String w=cursor.getString(3);
			String tmp=list.getText().toString();
			list.setText(tmp+"\n"+i+"   "+n+"    "+s+"    "+w);
			//Button bt = new Button(null);
			
		}*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.index, menu);
		return true;
	}

}
