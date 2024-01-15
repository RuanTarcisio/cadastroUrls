package ssp.ba.gov.br.cadastrolinks.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class FaviconUtils {

	public static String returnFavicon(String baseUrl) throws IOException, MalformedURLException, HttpStatusException {

		String pattern = "<link.*?rel=\\\"(?:shortcut )?icon\\\".*?href=\\\"\n?(.*?)\\\".*?>";
		Pattern r = Pattern.compile(pattern);
		String favicon = null;
		String faviconDefault = "https://secure.webtoolhub.com/static/resources/icons/set28/a50b8178.png";

		try {
			Document doc = Jsoup.connect(baseUrl).timeout(1500).userAgent("PostmanRuntime/7.35.0").get();
			Elements body = doc.select("head").select("link");
			Matcher m = r.matcher(body.toString());

			while (m.find()) {
				favicon = (String) m.group(1);
				break;
			}
		} catch (HttpStatusException e) {
			return faviconDefault;
		} catch (IOException e) {
			return faviconDefault;
		}

		if (favicon == null)
			return faviconDefault;

		if (!favicon.substring(0, 8).equalsIgnoreCase("https://")
				&& !favicon.substring(0, 7).equalsIgnoreCase("http://")) {

			favicon = concatenaFavicon(baseUrl, favicon);
		}

		if (!(faviconStatus(favicon) >= 200 && faviconStatus(favicon) < 210)) {
			return faviconDefault;
		}
		return favicon;
	}

	public static String concatenaFavicon(String baseUrl, String favicon) {

		return removeBarra(baseUrl) + "/" + removeBarra(favicon);
	}

	private static String removeBarra(String valor) {

		int tamanho;

		if (valor.startsWith("/")) {
			valor = valor.substring(1);
		}
		if (valor.endsWith("/")) {
			tamanho = valor.length();
			valor = valor.substring(0, tamanho - 1);
		}
		return valor;
	}

	public static Integer faviconStatus(String baseUrl) throws IOException {
		HttpURLConnection endereco = (HttpURLConnection) new URL(baseUrl).openConnection();

		return endereco.getResponseCode();
	}

}
