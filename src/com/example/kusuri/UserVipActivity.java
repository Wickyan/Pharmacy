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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class UserVipActivity extends Activity {
	//¹ºÂòÖ¸¶¨Ò©Æ·
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_vip);
		Intent it=getIntent();
		
		final String un=it.getStringExtra("UserName1");
		
		
		final ListView listview = (ListView)findViewById(R.id.listView2);
		final Button btnCheck = (Button)findViewById(R.id.btnCheck);
		final EditText Mname = (EditText)findViewById(R.id.Mname);
		
		final SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString()+"/kusuri.db3", null);
		
		 btnCheck.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String na=Mname.getText().toString();
				Cursor cursor=db.rawQuery("select * from Medicine where MName='"+na+"'", null);
				final String[] med = new String[cursor.getCount()];
				int i = 0;
				while(true){
					if(cursor.moveToNext()==false)
						break;
					String n = cursor.getString(1);
					med[i++] = n;
				}
				
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(UserVipActivity.this, android.R.layout.simple_list_item_1, med);
				listview.setAdapter(adapter);
				listview.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
						// TODO Auto-generated method stub
						String n = med[arg2];
						Intent it = new Intent(UserVipActivity.this,UserMedinotice2Activity.class);  
		                it.putExtra("n", n); 
		                it.putExtra("UserName1", un);
		                startActivity(it);
					}
				});
			}
			 
		 });
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_vip, menu);
		return true;
	}

}
