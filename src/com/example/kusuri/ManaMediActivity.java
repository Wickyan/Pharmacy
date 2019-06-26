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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ManaMediActivity extends Activity {
//管理员主界面
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mana_medi);
		final ListView listview = (ListView)findViewById(R.id.listview);
		final Button btnInsert = (Button)findViewById(R.id.btnInsert);
		final Button btnmt = (Button)findViewById(R.id.btnmt);
		final Button btnMLook = (Button)findViewById(R.id.btnMLook);
		final Button btnchakan = (Button)findViewById(R.id.btnchakan);
		final SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString()+"/kusuri.db3", null);
		try{
			Cursor cursor=db.rawQuery("select * from Medicine", null);
			final String[] med = new String[cursor.getCount()];
			int i = 0;
			while(true){
				if(cursor.moveToNext()==false)
					break;
				String n = cursor.getString(1);
				med[i++] = n;
			}

			ArrayAdapter<String> adapter = new ArrayAdapter<String>(ManaMediActivity.this, android.R.layout.simple_list_item_1, med);
			listview.setAdapter(adapter);
			listview.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
					// TODO Auto-generated method stub
					String n = med[arg2];
					//Toast.makeText(ManaMediActivity.this, n, Toast.LENGTH_LONG).show();
					Intent it = new Intent(ManaMediActivity.this,ManaMedinoticeActivity.class);
					it.putExtra("n", n);
					startActivity(it);
				}
			});
		}catch (Exception e){
			e.printStackTrace();
		}

		
		btnInsert.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent it = new Intent(ManaMediActivity.this,ManaInsertActivity.class);  
                it.putExtras(it); 
                startActivity(it);
			}
		});
		
		btnMLook.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent it = new Intent(ManaMediActivity.this,ManaVipActivity.class);  
                it.putExtras(it); 
                startActivity(it);
			}
		});
        btnmt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent it = new Intent(ManaMediActivity.this,IndexActivity.class);  
                it.putExtras(it); 
                startActivity(it);
			}
		});
        btnchakan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent it = new Intent(ManaMediActivity.this,MConlistActivity.class);  
                //it.putExtras(it); 
                startActivity(it);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mana_medi, menu);
		return true;
	}

}
