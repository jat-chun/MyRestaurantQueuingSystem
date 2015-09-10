package com.example.myrestaurantqueuingsystem.util;

import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class HttpUtil {
	// 正式环境 115.28.168.64
	//public final static String BaseUrl = "http://115.28.168.64:8080/wcXphServer";
	public final static String BaseUrl = "http://192.168.0.101:8081/restaurant_queuing_system/";
	//public final static String BaseUrl = "http://10.0.0.8:8080/wcXphServer";
	public final static String Apply = BaseUrl + "user/register.do";// 报名咨询

	// 自己要维护sessionid
	private static String sessionId;
	

	public static String httpGet(String url) throws Exception {

		HttpGet httpGet = new HttpGet(url);
		// 建立客户端
		DefaultHttpClient client = HttpClientSingle.getHttpClient();
		if (sessionId != null) {
			httpGet.setHeader("Cookie", "JSESSIONID=" + sessionId);
		}
		HttpResponse response = client.execute(httpGet);
		// 判断返回是否正确
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			// 服务器返回的数据
			String result = EntityUtils.toString(response.getEntity(), "UTF-8")
					.trim();
			// 获得session服务器的id
			CookieStore cookieStore = client.getCookieStore();
			List<Cookie> cookies = cookieStore.getCookies();
			for (int i = 0; i < cookies.size(); i++) {
				Cookie cookie = cookies.get(i);
				if (cookie.getName().equals("JSESSIONID")) {
					sessionId = cookie.getValue();
				}
			}
			return result;
		}
		return null;
	}

	public static String httpPost(String url, List<NameValuePair> nameValuePairs)
			throws Exception {
		HttpPost post = new HttpPost(url);
		HttpEntity entity = new UrlEncodedFormEntity(nameValuePairs, "UTF-8");
		post.setEntity(entity);

		// 建立客户端
		DefaultHttpClient client = HttpClientSingle.getHttpClient();
		if (sessionId != null) {
			post.setHeader("Cookie", "JSESSIONID=" + sessionId);
		}
		HttpResponse response = client.execute(post);
		// 判断返回是否正确
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			// 服务器返回的数据
			String result = EntityUtils.toString(response.getEntity(), "UTF-8")
					.trim();

			// 获得session服务器的id
			CookieStore cookieStore = client.getCookieStore();
			List<Cookie> cookies = cookieStore.getCookies();
			for (int i = 0; i < cookies.size(); i++) {
				Cookie cookie = cookies.get(i);
				if (cookie.getName().equals("JSESSIONID")) {
					sessionId = cookie.getValue();
				}
			}
			return result;
		}
		return null;
	}

	public static Bitmap down(String urladdress) {
		try {
			URL url = new URL(urladdress);
			URLConnection connection = url.openConnection();
			BufferedInputStream bis = new BufferedInputStream(
					connection.getInputStream());
			Bitmap bitmap = BitmapFactory.decodeStream(bis);
			return bitmap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
