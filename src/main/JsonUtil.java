package main;

import java.net.URL;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class JsonUtil {

	/**
	 * 
	 * @param url
	 * @return
	 * 解析得到的json数据并创建一个weather对象
	 */
	public static Weather getWeather(URL url) {
		Weather weather = new Weather();
		JsonFetcher myJsonFetcher = new JsonFetcher();
		String jsonText = myJsonFetcher.getJSONText(url);
		jsonText = jsonText.replace("[", "");
		jsonText = jsonText.replace("]", "");
		while (jsonText == null) {
			jsonText = myJsonFetcher.getJSONText(url);
		}
		if (jsonText.charAt(0) != '{') {
			jsonText = myJsonFetcher.getJSONText(url);
			jsonText = jsonText.replace("[", "");
			jsonText = jsonText.replace("]", "");
		}
		System.out.println(jsonText);

		try {
			JSONObject weatherJSONObject = JSONObject.fromObject(jsonText);
			JSONObject coordJSONObject = weatherJSONObject.getJSONObject("coord");
			JSONObject sysJSONObject = weatherJSONObject.getJSONObject("sys");
			JSONObject subWeatherJSONObject = weatherJSONObject.getJSONObject("weather");
			JSONObject mainJSONObject = weatherJSONObject.getJSONObject("main");
			JSONObject windJSONObject = weatherJSONObject.getJSONObject("wind");

			weather.lon = coordJSONObject.getDouble("lon");
			weather.lat = coordJSONObject.getDouble("lat");
			weather.main = subWeatherJSONObject.getString("main");
			weather.description = subWeatherJSONObject.getString("description");
			weather.city = weatherJSONObject.getString("name");
			weather.country = sysJSONObject.getString("country");
			weather.temp = mainJSONObject.getDouble("temp");
			weather.temp = weather.temp - 273.15;
			weather.temp_min = mainJSONObject.getDouble("temp_min");
			weather.temp_max = mainJSONObject.getDouble("temp_max");
			weather.humidity = mainJSONObject.getInt("humidity");// 湿度
			weather.pressure = mainJSONObject.getInt("pressure");// 气压
			weather.wind_spd = windJSONObject.getInt("speed");

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return weather;
	}
}
