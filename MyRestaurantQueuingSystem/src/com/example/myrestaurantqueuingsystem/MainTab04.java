package com.example.myrestaurantqueuingsystem;

import com.example.myrestaurantqueuingsystem.util.HttpUtil;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

@SuppressLint("NewApi")
public class MainTab04 extends Fragment{

	private EditText et_login_user_name,et_login_password,et_login_phone,et_login_email;
	
	private RadioGroup rg_login_group;
	
	private RadioButton rb_login_man,rb_login_woman;
	
	private String user_name,password,phone,email,sex;
	
	private Button bt_login;
	
	private Handler myHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			
		};
	};
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View newsLayout = inflater.inflate(R.layout.main_tab_04, container, false);
		init(newsLayout);
		return newsLayout;
	}
	
	public void init(View view){
		et_login_user_name = (EditText) view.findViewById(R.id.et_login_user_name);
		et_login_password = (EditText) view.findViewById(R.id.et_login_password);
		et_login_phone = (EditText) view.findViewById(R.id.et_login_phone);
		et_login_email = (EditText) view.findViewById(R.id.et_login_email);
		rg_login_group = (RadioGroup) view.findViewById(R.id.rg_login_group);
		rb_login_man = (RadioButton) view.findViewById(R.id.rb_login_man);
		rb_login_woman = (RadioButton) view.findViewById(R.id.rb_login_woman);
		bt_login = (Button) view.findViewById(R.id.bt_login);
		bt_login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				user_name = et_login_user_name.getText().toString().trim();
				password = et_login_password.getText().toString().trim();
				phone = et_login_phone.getText().toString().trim();
				email = et_login_email.getText().toString().trim();
			}
		});
		rg_login_group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				// TODO Auto-generated method stub
				if (arg1==rb_login_man.getId()) {
					sex = "男";
				}else if (arg1==rb_login_woman.getId()) {
					sex = "女";
				}
			}
		});
	}

	class RegisterAsyncTask extends AsyncTask<String, Void, String>{

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}
		
		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			String result = null;
			try {
				if (email=="") {
					email="null";
				}
				result = HttpUtil.httpGet(HttpUtil.Apply+"userName="+user_name+"?password="+password+"?phone="+phone+"?email="+email+"?sex="+sex);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}
		
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			Toast.makeText(getActivity(), result, 1).show();
		}
		
	}
}
