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
import android.widget.TextView;
import android.widget.Toast;

public class UserMedinoticeActivity extends Activity {
	//用户列表购买
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_medinotice);
		
		Intent intent1 = this.getIntent(); 
		final String n = intent1.getStringExtra("n");
		
		final String username1=intent1.getStringExtra("UserName1");
		
		
		
		final TextView MNumber = (TextView)findViewById(R.id.MNumber2);
		final TextView MName = (TextView)findViewById(R.id.MName2);
		final TextView MFrom = (TextView)findViewById(R.id.MFrom2);
		final TextView MPrice = (TextView)findViewById(R.id.MPrice2);
		final TextView MCount = (TextView)findViewById(R.id.MCount2);
		final Button btnBack = (Button)findViewById(R.id.btnBack2);
		
		
		final Button btnCancel = (Button)findViewById(R.id.btnCancel3);
		final Button btnBuy = (Button)findViewById(R.id.btnBuy);
		final TextView txtMoney = (TextView)findViewById(R.id.txtMoney);
		final EditText buyNumber = (EditText)findViewById(R.id.buyNumber);
		
		final SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString()+"/kusuri.db3", null);
		
		Cursor cursor=db.rawQuery("select * from Medicine where MName='"+n+"'", null);
		cursor.moveToNext();
		MNumber.setText(cursor.getString(0));
		MName.setText(cursor.getString(1));
		MFrom.setText(cursor.getString(2));
		MPrice.setText(cursor.getString(4));
		MCount.setText(cursor.getString(5));
		
		
		
	btnCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String No=buyNumber.getText().toString();
				int _No=Integer.parseInt(No);
				String Mp=MPrice.getText().toString();
				int _Mp=Integer.parseInt(Mp);
				int sum=_No*_Mp;
				String Ssum=Integer.toString(sum);
				txtMoney.setText("您需要支付"+Ssum+"元");
			}
		});
	btnBuy.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			//Toast.makeText(UserMedinoticeActivity.this, ".............！", Toast.LENGTH_LONG).show();
			String No=buyNumber.getText().toString();
			int _No=Integer.parseInt(No);//购买量
			String Mp=MPrice.getText().toString();
			int _Mp=Integer.parseInt(Mp);//定价
			String Mc=MCount.getText().toString();
			int _Mc=Integer.parseInt(Mc);//库存量
			int sum=_No*_Mp;//总价
			
			String Ssum=Integer.toString(sum);
			if(_Mc>0&&_Mc>=_No)
			{
				int rest=_Mc-_No;//剩余数量
				String Srest=Integer.toString(rest);
				String sql = "update Medicine set Mcount='"+Srest+"' where MName='"+MName.getText().toString()+"'";
				db.execSQL(sql);
				MCount.setText(Srest);
				int x=(int)((Math.random()*9+1)*1000000);
				
				String a,b,c,d,e,f;
				Cursor cursor1=db.rawQuery("select * from UserInfo where UserName='"+username1+"'", null);
				cursor1.moveToNext();
				a=username1;
				//b=cursor1.getString(2);
				b="x";
				c=MNumber.getText().toString();
				d=MName.getText().toString();
				e=buyNumber.getText().toString();
				f=Ssum;
				
				
				db.execSQL("insert into Consumlist2 values(?,?,?,?,?,?)", new String[]{a, b, c, d, e, f});
				
				
				Toast.makeText(UserMedinoticeActivity.this, "购买成功", Toast.LENGTH_LONG).show();
				Intent it = new Intent(UserMedinoticeActivity.this,UserMediActivity.class);  
                it.putExtras(it);
                it.putExtra("UserName1",username1);  
                startActivity(it);
			}
			else
			{
				
				Toast.makeText(UserMedinoticeActivity.this, "库存不足！", Toast.LENGTH_LONG).show();
			}
			
			
		}
	});
		
		btnBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent it = new Intent(UserMedinoticeActivity.this,UserMediActivity.class);  
                it.putExtras(it); 
                it.putExtra("UserName1",username1);  
                startActivity(it);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_medinotice, menu);
		return true;
	}

}
