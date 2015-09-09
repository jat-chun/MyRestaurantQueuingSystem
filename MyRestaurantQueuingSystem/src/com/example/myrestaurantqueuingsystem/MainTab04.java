package com.example.myrestaurantqueuingsystem;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

@SuppressLint("NewApi")
public class MainTab04 extends Fragment{

	private EditText et_login_user_name,et_login_password,et_login_phone,et_login_email;
	
	private RadioGroup rg_login_group;
	
	private RadioButton rb_login_man,rb_login_woman;
	
	private String user_name,password,phone,emailString,sex;
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

}
