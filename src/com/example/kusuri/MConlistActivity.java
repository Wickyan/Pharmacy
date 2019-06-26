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

public class MConlistActivity extends Activity {
//管理员-》用户购买记录
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mconlist);
		
        Intent it=this.getIntent();
       // final String n = it.getStringExtra("n");
		//final String un=it.getStringExtra("UserName1");
		final ListView listview4 = (ListView)findViewById(R.id.listView4);
		
		
		final SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString()+"/kusuri.db3", null);
		
		
		
		Cursor cursor=db.rawQuery("select * from Consumlist2 ", null);
		final String[] med = new String[cursor.getCount()];
		int i = 0;
		while(true){
			if(cursor.moveToNext()==false)
				break;
			String n = cursor.getString(3);
			med[i++] = n;
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(MConlistActivity.this, android.R.layout.simple_list_item_1, med);
		listview4.setAdapter(adapter);
		listview4.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				String n = med[arg2];
				Intent it = new Intent(MConlistActivity.this,MConlistInfoActivity.class);  
                it.putExtra("n", n); 
                startActivity(it);
			}
		});
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mconlist, menu);
		return true;
	}

}
