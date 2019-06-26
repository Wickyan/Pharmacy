package com.example.kusuri;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity {
//多余
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final EditText id = (EditText)findViewById(R.id.id);
		final EditText name = (EditText)findViewById(R.id.name);
		final EditText score = (EditText)findViewById(R.id.score);
		final Button save = (Button)findViewById(R.id.save);
		final Button show = (Button)findViewById(R.id.show);
		final EditText list = (EditText)findViewById(R.id.list);
		final SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString()+"/stu.db3", null);
		try {
			db.execSQL("create table student(id integer primary key, name varchar(50), score integer)");
		}catch(Exception e) {
			e.printStackTrace();
		}
		save.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String i = id.getText().toString();
				String n = name.getText().toString();
				String s = score.getText().toString();
				db.execSQL("insert into student values(?,?,?)", new String[]{i, n, s});
				Toast.makeText(MainActivity.this, "插入成功！", Toast.LENGTH_LONG).show();
			}
		});
		/*show.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				list.setText("");
				Cursor cursor=db.rawQuery("select * from student", null);
				while(true){
					if(cursor.moveToNext()==false)
						break;
					int i=cursor.getInt(0);
					String n=cursor.getString(1);
					int s=cursor.getInt(2);
					//String tmp=list.getText().toString();
					//list.setText(tmp+"\n"+i+"   "+n+"    "+s);
					//Button bt = new Button(null);
					LinearLayout lay = new LinearLayout(null);
					lay.setOrientation(LinearLayout.HORIZONTAL);
					EditText ed = new EditText(null);
					ed.setText(i+" "+n+""+s);
					Button bt = new Button(null);
					bt.setText("fuck");
				}
			}
		});*/
		list.setText("");
		Cursor cursor=db.rawQuery("select * from student", null);
		while(true){
			if(cursor.moveToNext()==false)
				break;
			int i=cursor.getInt(0);
			String n=cursor.getString(1);
			int s=cursor.getInt(2);
			//String tmp=list.getText().toString();
			//list.setText(tmp+"\n"+i+"   "+n+"    "+s);
			//Button bt = new Button(null);
			LinearLayout lay = new LinearLayout(this);
			lay.setOrientation(LinearLayout.HORIZONTAL);
			EditText ed = new EditText(this);
			ed.setText(i+" "+n+""+s);
			Button bt = new Button(this);
			bt.setText("fuck");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
