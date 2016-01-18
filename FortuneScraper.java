package WebDriverDemo.test;

import java.io.IOException;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.AjaxController;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class FortuneScraper {
	public static void main(String[] args) throws IOException {

		java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);
		java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.OFF);

		Logger log = LogManager.getLogger(FortuneScraper.class.getName());

		try {
			final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_38);
			
			// WebClient parameters to avoid JS problems with specific web page 
			
			webClient.getOptions().setJavaScriptEnabled(true);
			webClient.getOptions().setCssEnabled(false);
			webClient.setAjaxController(new NicelyResynchronizingAjaxController());
			webClient.setAjaxController(new AjaxController());
			webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			webClient.getCookieManager().setCookiesEnabled(false);

			HtmlPage page = webClient.getPage("http://fortune.com/fortune500/");

			webClient.waitForBackgroundJavaScript(7000);

			log.trace("Web page was successfully reached.");

			HtmlElement button = page
					.getFirstByXPath("//*[@id='bottom-panel-drop-down-container']/div/select/option[102]");
			page = (HtmlPage) button.click();

			webClient.waitForBackgroundJavaScript(7000);

			Document doc = Jsoup.parse(page.asXml()); 

			Elements companyName = doc.getElementsByClass("company-name");
			Elements companyMrktData = doc.getElementsByClass("company-list-mkt-data");					

			if (!companyName.isEmpty() || !companyMrktData.isEmpty()) {
				log.trace("Data was extracted successfully.");
			}			

			// Checks the output.
			System.out.println(companyName.get(1).text());
			System.out.println(companyName.size());

			System.out.println(companyMrktData.get(1).text());
			System.out.println(companyMrktData.size());			
			
			webClient.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void AddToDb (Elements companyName, Elements companyMrktData) {
	}
}