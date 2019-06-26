package com.example.kusuri;

import android.os.Bundle;
import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ManaMedinoticeActivity extends Activity {
//管理员药品详情
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mana_medinotice);
		Intent intent1 = this.getIntent(); 
		final String n = intent1.getStringExtra("n");
		final EditText MNumber = (EditText)findViewById(R.id.MNumber1);
		final EditText MName = (EditText)findViewById(R.id.MName1);
		final EditText MFrom = (EditText)findViewById(R.id.MFrom1);
		final EditText BPrice = (EditText)findViewById(R.id.BPrice1);
		final EditText MPrice = (EditText)findViewById(R.id.MPrice1);
		final EditText MCount = (EditText)findViewById(R.id.MCount1);
		final Button btnUpdate = (Button)findViewById(R.id.btnUpdate);
		final Button btnBack = (Button)findViewById(R.id.btnBack);
		final Button btnDelete = (Button)findViewById(R.id.btnDelete);
		final SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString()+"/kusuri.db3", null);
		
		Cursor cursor=db.rawQuery("select * from Medicine where MName='"+n+"'", null);
		cursor.moveToNext();
		MNumber.setText(cursor.getString(0));
		MName.setText(cursor.getString(1));
		MFrom.setText(cursor.getString(2));
		BPrice.setText(cursor.getString(3));
		MPrice.setText(cursor.getString(4));
		MCount.setText(cursor.getString(5));
		
		btnUpdate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String a, b, c, d, e,f;
				int _count, _price,_price1;
				a = MNumber.getText().toString();
				b = MName.getText().toString();
				c = MFrom.getText().toString();
				d = MPrice.getText().toString();
				e = MCount.getText().toString();
				f =BPrice.getText().toString();
				_price  = Integer.parseInt(d);
				_price1  = Integer.parseInt(f);
				_count = Integer.parseInt(e);
				if(_count < 0 || _price < 0||_price1<0) 
				{
					Toast.makeText(ManaMedinoticeActivity.this, "错误", Toast.LENGTH_LONG).show();
				}
				
				else
				{if(_count<10)
				{
					Toast.makeText(ManaMedinoticeActivity.this, "库存不足请尽快补货！！！", Toast.LENGTH_LONG).show();
				}
					String sql = "update Medicine set MNumber='"+a+"',MName='"+b+"',MFrom='"+c+"',BPrice='"+f+"',MPrice='"+d+"',MCount='"+e+"' where MName='"+n+"'";
					db.execSQL(sql);
					Toast.makeText(ManaMedinoticeActivity.this, "修改成功", Toast.LENGTH_LONG).show();
				}
				
			}
		});
		
		btnBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent it = new Intent(ManaMedinoticeActivity.this,ManaMediActivity.class);  
                it.putExtras(it);
                startActivity(it);
			}
		});
       btnDelete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String a = MNumber.getText().toString();
				String sql = "delete from Medicine where MNumber='"+a+"'";
				db.execSQL(sql);
				Toast.makeText(ManaMedinoticeActivity.this, "删除成功", Toast.LENGTH_LONG).show();
				Intent it = new Intent(ManaMedinoticeActivity.this,ManaMediActivity.class);  
                it.putExtras(it);
                startActivity(it);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mana_medinotice, menu);
		return true;
	}

}
