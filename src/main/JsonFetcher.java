package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonFetcher {

	private String jsonText = "";
	/**
	 * 
	 * @param url
	 * @return 
	 * 通过此函数可得到openWeather返回的天气数据（String类型）
	 */

	public String getJSONText(final URL url) {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				InputStream is = null;
				BufferedReader in = null;
				try {
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setReadTimeout(10000);
					conn.setConnectTimeout(15000);
					conn.setRequestMethod("GET");
					conn.setDoInput(true);
					conn.connect();

					is = conn.getInputStream();
					in = new BufferedReader(new InputStreamReader(is));
					String line = "";
					while ((line = in.readLine()) != null) {
						jsonText += line;
					}

				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						in.close();
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}

				}

			}

		});
		thread.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return jsonText;
	}

}
