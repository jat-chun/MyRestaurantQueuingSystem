package com.example.myrestaurantqueuingsystem;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.example.myrestaurantqueuingsystem.util.HttpUtil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

	private MainTab02 mTab02;
	private MainTab01 mTab01;
	private MainTab03 mTab03;
	private MainTab04 mTab04;

	/**
	 * 搴曢儴鍥涗釜鎸夐挳
	 */
	private LinearLayout mTabBtnWeixin;
	private LinearLayout mTabBtnFrd;
	private LinearLayout mTabBtnAddress;
	private LinearLayout mTabBtnSettings;
	/**
	 * 鐢ㄤ簬瀵笷ragment杩涜绠＄悊
	 */
	private FragmentManager fragmentManager;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initViews();
		MyTask task = new MyTask();
		task.execute();
		fragmentManager = getFragmentManager();
		setTabSelection(0);
	}
	
	class MyTask extends AsyncTask<String, Void, String>{

		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			String result = null;
			User user = new User();
			user.setUserName("陈春杰7");
			user.setAge(23);
			user.setCreateTime(new Date(System.currentTimeMillis()));
			user.setPhone("15088132300");
			user.setPwd("123456");
			user.setEmail("919700667@qq.com");
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			NameValuePair nameValuePairs = new BasicNameValuePair("user", user.toString());
			list.add(nameValuePairs);
			try {
				result = HttpUtil.httpPost(HttpUtil.BaseUrl+"user/login.do", list);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Toast.makeText(MainActivity.this, result, 1).show();
			
			return result;
		}
		
	}
	private void initViews()
	{

		mTabBtnWeixin = (LinearLayout) findViewById(R.id.id_tab_bottom_weixin);
		mTabBtnFrd = (LinearLayout) findViewById(R.id.id_tab_bottom_friend);
		mTabBtnAddress = (LinearLayout) findViewById(R.id.id_tab_bottom_contact);
		mTabBtnSettings = (LinearLayout) findViewById(R.id.id_tab_bottom_setting);

		mTabBtnWeixin.setOnClickListener(this);
		mTabBtnFrd.setOnClickListener(this);
		mTabBtnAddress.setOnClickListener(this);
		mTabBtnSettings.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.id_tab_bottom_weixin:
			setTabSelection(0);
			break;
		case R.id.id_tab_bottom_friend:
			setTabSelection(1);
			break;
		case R.id.id_tab_bottom_contact:
			setTabSelection(2);
			break;
		case R.id.id_tab_bottom_setting:
			setTabSelection(3);
			break;

		default:
			break;
		}
	}

	/**
	 * 鏍规嵁浼犲叆鐨刬ndex鍙傛暟鏉ヨ缃�涓殑tab椤点�
	 * 
	 */
	@SuppressLint("NewApi")
	private void setTabSelection(int index)
	{
		// 閲嶇疆鎸夐挳
		resetBtn();
		// 寮�惎涓�釜Fragment浜嬪姟
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		// 鍏堥殣钘忔帀鎵�湁鐨凢ragment锛屼互闃叉鏈夊涓狥ragment鏄剧ず鍦ㄧ晫闈笂鐨勬儏鍐�
		hideFragments(transaction);
		switch (index)
		{
		case 0:
			// 褰撶偣鍑讳簡娑堟伅tab鏃讹紝鏀瑰彉鎺т欢鐨勫浘鐗囧拰鏂囧瓧棰滆壊
			((ImageButton) mTabBtnWeixin.findViewById(R.id.btn_tab_bottom_weixin))
					.setImageResource(R.drawable.tab_weixin_pressed);
			if (mTab01 == null)
			{
				// 濡傛灉MessageFragment涓虹┖锛屽垯鍒涘缓涓�釜骞舵坊鍔犲埌鐣岄潰涓�
				mTab01 = new MainTab01();
				transaction.add(R.id.id_content, mTab01);
			} else
			{
				// 濡傛灉MessageFragment涓嶄负绌猴紝鍒欑洿鎺ュ皢瀹冩樉绀哄嚭鏉�
				transaction.show(mTab01);
			}
			break;
		case 1:
			// 褰撶偣鍑讳簡娑堟伅tab鏃讹紝鏀瑰彉鎺т欢鐨勫浘鐗囧拰鏂囧瓧棰滆壊
			((ImageButton) mTabBtnFrd.findViewById(R.id.btn_tab_bottom_friend))
					.setImageResource(R.drawable.tab_find_frd_pressed);
			if (mTab02 == null)
			{
				// 濡傛灉MessageFragment涓虹┖锛屽垯鍒涘缓涓�釜骞舵坊鍔犲埌鐣岄潰涓�
				mTab02 = new MainTab02();
				transaction.add(R.id.id_content, mTab02);
			} else
			{
				// 濡傛灉MessageFragment涓嶄负绌猴紝鍒欑洿鎺ュ皢瀹冩樉绀哄嚭鏉�
				transaction.show(mTab02);
			}
			break;
		case 2:
			// 褰撶偣鍑讳簡鍔ㄦ�tab鏃讹紝鏀瑰彉鎺т欢鐨勫浘鐗囧拰鏂囧瓧棰滆壊
			((ImageButton) mTabBtnAddress.findViewById(R.id.btn_tab_bottom_contact))
					.setImageResource(R.drawable.tab_address_pressed);
			if (mTab03 == null)
			{
				// 濡傛灉NewsFragment涓虹┖锛屽垯鍒涘缓涓�釜骞舵坊鍔犲埌鐣岄潰涓�
				mTab03 = new MainTab03();
				transaction.add(R.id.id_content, mTab03);
			} else
			{
				// 濡傛灉NewsFragment涓嶄负绌猴紝鍒欑洿鎺ュ皢瀹冩樉绀哄嚭鏉�
				transaction.show(mTab03);
			}
			break;
		case 3:
			// 褰撶偣鍑讳簡璁剧疆tab鏃讹紝鏀瑰彉鎺т欢鐨勫浘鐗囧拰鏂囧瓧棰滆壊
			((ImageButton) mTabBtnSettings.findViewById(R.id.btn_tab_bottom_setting))
					.setImageResource(R.drawable.tab_settings_pressed);
			if (mTab04 == null)
			{
				// 濡傛灉SettingFragment涓虹┖锛屽垯鍒涘缓涓�釜骞舵坊鍔犲埌鐣岄潰涓�
				mTab04 = new MainTab04();
				transaction.add(R.id.id_content, mTab04);
			} else
			{
				// 濡傛灉SettingFragment涓嶄负绌猴紝鍒欑洿鎺ュ皢瀹冩樉绀哄嚭鏉�
				transaction.show(mTab04);
			}
			break;
		}
		transaction.commit();
	}

	/**
	 * 娓呴櫎鎺夋墍鏈夌殑閫変腑鐘舵�銆�
	 */
	private void resetBtn()
	{
		((ImageButton) mTabBtnWeixin.findViewById(R.id.btn_tab_bottom_weixin))
				.setImageResource(R.drawable.tab_weixin_normal);
		((ImageButton) mTabBtnFrd.findViewById(R.id.btn_tab_bottom_friend))
				.setImageResource(R.drawable.tab_find_frd_normal);
		((ImageButton) mTabBtnAddress.findViewById(R.id.btn_tab_bottom_contact))
				.setImageResource(R.drawable.tab_address_normal);
		((ImageButton) mTabBtnSettings.findViewById(R.id.btn_tab_bottom_setting))
				.setImageResource(R.drawable.tab_settings_normal);
	}

	/**
	 * 灏嗘墍鏈夌殑Fragment閮界疆涓洪殣钘忕姸鎬併�
	 * 
	 * @param transaction
	 *            鐢ㄤ簬瀵笷ragment鎵ц鎿嶄綔鐨勪簨鍔�
	 */
	@SuppressLint("NewApi")
	private void hideFragments(FragmentTransaction transaction)
	{
		if (mTab01 != null)
		{
			transaction.hide(mTab01);
		}
		if (mTab02 != null)
		{
			transaction.hide(mTab02);
		}
		if (mTab03 != null)
		{
			transaction.hide(mTab03);
		}
		if (mTab04 != null)
		{
			transaction.hide(mTab04);
		}
		
	}
}
