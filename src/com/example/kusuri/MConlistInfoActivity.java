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
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MConlistInfoActivity extends Activity {
	//管理员-》购买详细信息
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mconlist_info);
		Intent intent1 = this.getIntent(); 
		final String n = intent1.getStringExtra("n");
		final TextView UserName = (TextView)findViewById(R.id.UserName);
		//final TextView Name = (TextView)findViewById(R.id.Name);
		final TextView MNumber = (TextView)findViewById(R.id.MNumber);
		final TextView MName = (TextView)findViewById(R.id.MName);
		final TextView Count = (TextView)findViewById(R.id.Count);
		final TextView Consume = (TextView)findViewById(R.id.Consume);
final SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString()+"/kusuri.db3", null);
		
		Cursor cursor=db.rawQuery("select * from Consumlist2 where ConMName='"+n+"'", null);
		cursor.moveToNext();
		UserName.setText(cursor.getString(0));
		//Name.setText(cursor.getString(1));
		MNumber.setText(cursor.getString(2));
		MName.setText(cursor.getString(3));
		Count.setText(cursor.getString(4));
		Consume.setText(cursor.getString(5));
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mconlist_info, menu);
		return true;
	}

}
