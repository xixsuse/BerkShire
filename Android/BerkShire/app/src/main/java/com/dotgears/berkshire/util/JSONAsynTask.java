package com.dotgears.berkshire.util;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JSONAsynTask extends AsyncTask<String, Void, String> {

	@Override
	protected String doInBackground(String... urls) {
		String url = urls[0].replace(" ", "%20");
		return GET(url);
	}

	private String GET(String url) {
		InputStream is = null;
		String result = "";
		try {
			HttpClient hc = new DefaultHttpClient();
			HttpResponse hr = hc.execute(new HttpGet(url));
			is = hr.getEntity().getContent();
			if (is != null) {
				result = convertInputStreamToString(is);
			} else {
				result = "Url did not work";
			}
		} catch (Exception e) {
			Log.d("InputStream", e.getLocalizedMessage());
		}
		return result;
	}

	private static String convertInputStreamToString(InputStream is)
			throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line = "";
		String result = "";
		while ((line = br.readLine()) != null) {
			result += line;

		}
		is.close();
		return result;
	}
}
