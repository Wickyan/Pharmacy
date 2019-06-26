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
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ManaVipActivity extends Activity {
//管理员查看药品信息
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mana_vip);
		final EditText edtNumber = (EditText)findViewById(R.id.edtNumber);
		final Button btnOkk = (Button)findViewById(R.id.btnOkk);
		final EditText MNumber = (EditText)findViewById(R.id.MNumber2);
		final EditText MName = (EditText)findViewById(R.id.MName2);
		final EditText MFrom = (EditText)findViewById(R.id.MFrom2);
		final EditText BPrice = (EditText)findViewById(R.id.BPrice2);
		final EditText MPrice = (EditText)findViewById(R.id.MPrice2);
		final EditText MCount = (EditText)findViewById(R.id.MCount2);
		final Button btnUpdate = (Button)findViewById(R.id.btnUpdate2);
		final Button btnBack = (Button)findViewById(R.id.btnBack2);
		final Button btnDelete = (Button)findViewById(R.id.btnDelete2);
		final SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString()+"/kusuri.db3", null);
		
		btnOkk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				
				
				
				
				String num=edtNumber.getText().toString();
				
				
				Cursor cursor=db.rawQuery("select * from Medicine where MNumber='"+num+"'", null);
				
				cursor.moveToNext();
				
				MNumber.setText(cursor.getString(0));
				MName.setText(cursor.getString(1));
				MFrom.setText(cursor.getString(2));
				BPrice.setText(cursor.getString(3));
				MPrice.setText(cursor.getString(4));
				MCount.setText(cursor.getString(5));
				

			}
		});
        btnUpdate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String a, b, c, d, e,f;
				String n=edtNumber.getText().toString();
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
					Toast.makeText(ManaVipActivity.this, "错误", Toast.LENGTH_LONG).show();
				}
				
				else
				{if(_count<10)
				{
					Toast.makeText(ManaVipActivity.this, "库存不足请尽快补货！！！", Toast.LENGTH_LONG).show();
				}
					String sql = "update Medicine set MNumber='"+a+"',MName='"+b+"',MFrom='"+c+"',BPrice='"+f+"',MPrice='"+d+"',MCount='"+e+"' where MNumber='"+n+"'";
					db.execSQL(sql);
					Toast.makeText(ManaVipActivity.this, "修改成功", Toast.LENGTH_LONG).show();
					MNumber.setText("");
					MName.setText("");
					MFrom.setText("");
					BPrice.setText("");
					MPrice.setText("");
					MCount.setText("");
				}
				
			}
		});


        btnBack.setOnClickListener(new OnClickListener() {
	
	@Override
	    public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent it = new Intent(ManaVipActivity.this,ManaMediActivity.class);  
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
				Toast.makeText(ManaVipActivity.this, "删除成功", Toast.LENGTH_LONG).show();
				Intent it = new Intent(ManaVipActivity.this,ManaMediActivity.class);  
                it.putExtras(it);
                startActivity(it);
			}
		});
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mana_vip, menu);
		return true;
	}

}
