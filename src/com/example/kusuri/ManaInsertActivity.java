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
import android.widget.TextView;
import android.widget.Toast;

public class ManaInsertActivity extends Activity {
//管理员添加药品
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mana_insert);
		final EditText MNumber = (EditText)findViewById(R.id.MNumber);
		final EditText MName = (EditText)findViewById(R.id.MName);
		final EditText MFrom = (EditText)findViewById(R.id.MFrom);
		final EditText BPrice = (EditText)findViewById(R.id.BPrice);
		final EditText MPrice = (EditText)findViewById(R.id.MPrice);
		final EditText MCount = (EditText)findViewById(R.id.MCount);
		final Button btnInsert = (Button)findViewById(R.id.btnMInsert);
		final Button btnBack = (Button)findViewById(R.id.btnBack1);
		final SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString()+"/kusuri.db3", null);
		
		btnInsert.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				String num = MNumber.getText().toString();
				String name = MName.getText().toString();
				String from = MFrom.getText().toString();
				String bprice = BPrice.getText().toString();
				String price = MPrice.getText().toString();
				String count = MCount.getText().toString();
				int _price  = Integer.parseInt(price);
				int _count = Integer.parseInt(count);
				int _price1=Integer.parseInt(bprice);
				if(num.equals("")||name.equals("")||from.equals("")||bprice.equals("")||price.equals("")||count.equals(""))
				{
					Toast.makeText(ManaInsertActivity.this, "条件不足，无法添加！", Toast.LENGTH_LONG).show();
				}
				else if(_count < 0 || _price < 0||_price1<0) 
				{
					Toast.makeText(ManaInsertActivity.this, "错误", Toast.LENGTH_LONG).show();
				}
				
				else{
				db.execSQL("insert into Medicine values(?,?,?,?,?,?)", new String[]{num, name, from,bprice, price, count});
				Toast.makeText(ManaInsertActivity.this, "添加成功！", Toast.LENGTH_LONG).show();
				if(_count<10)
				{
					Toast.makeText(ManaInsertActivity.this, "库存量过低请尽快补货！！！", Toast.LENGTH_LONG).show();
				}
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
				Intent it = new Intent(ManaInsertActivity.this,ManaMediActivity.class);  
                it.putExtras(it);
                startActivity(it);
			}
		});
		/*EditText list1 = (EditText)findViewById(R.id.list1);
		Cursor cursor=db.rawQuery("select * from Medicine", null);
		while(true){
			if(cursor.moveToNext()==false)
				break;
			String i=cursor.getString(0);
			String n=cursor.getString(1);
			String s=cursor.getString(2);
			String tmp=list1.getText().toString();
			list1.setText(tmp+"\n"+i+"   "+n+"    "+s);
			//Button bt = new Button(null);
			
		}*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mana_insert, menu);
		return true;
	}

}
