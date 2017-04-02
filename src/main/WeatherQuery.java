package main;

import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WeatherQuery
 */
@WebServlet({ "/WeatherQuery", "/weatherquery" })
public class WeatherQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WeatherQuery() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String myAppid = "acb4be18d51ea8b92e5d750d8af72adb";
		URL myUrl = new URL(
				"http://api.openweathermap.org/data/2.5/weather?q=beijing&appid=acb4be18d51ea8b92e5d750d8af72adb");
		Weather myWeather = JsonUtil.getWeather(myUrl);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
