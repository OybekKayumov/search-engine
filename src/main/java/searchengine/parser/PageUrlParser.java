package searchengine.parser;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import searchengine.dto.PageDto;
import searchengine.utils.RandomUserAgent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

@Slf4j
public class PageUrlParser extends RecursiveTask<List<PageDto>> {

	private final String url;
	private final List<String> urlList;
	private final List<PageDto> pageDtoList;

	public PageUrlParser(String url, List<PageDto> pageDtoList,
	                     List<String> urlList) {
		this.url = url;
		this.pageDtoList = pageDtoList;
		this.urlList = urlList;
	}

	@Override
	protected List<PageDto> compute() {
		try {

			Thread.sleep(150);
			Document doc = getConnect(url);
			String html = doc.outerHtml();
			Connection.Response response = doc.connection().response();
			int status = response.statusCode();
			PageDto pageDto = new PageDto(url, html, status);
			pageDtoList.add(pageDto);
			Elements elements = doc.select("body").select("a");
			List<PageUrlParser> taskList = new ArrayList<>();
			for (Element el : elements) {
				String link = el.attr("abs:href");
				if (link.startsWith(el.baseUri()) && !link.equals(el.baseUri())
								&& !link.contains("#") && !link.contains(".pdf")
								&& !link.contains(".jpg") && !link.contains(".JPG")
								&& !link.contains(".png") && !urlList.contains(link)) {

					urlList.add(link);
					PageUrlParser task = new PageUrlParser(link, pageDtoList, urlList);
					task.fork();
					taskList.add(task);
				}
			}

			taskList.forEach(ForkJoinTask::join);

		} catch (Exception e) {
			log.debug("Ошибка парсинга: " + url);
			PageDto pageDto = new PageDto(url, "", 500);
			pageDtoList.add(pageDto);
		}

		return pageDtoList;
	}

	public Document getConnect(String url) {

		Document doc = null;

		try {

			Thread.sleep(150);
			doc = Jsoup.connect(url).userAgent(RandomUserAgent.getRandomUserAgen())
							.referrer("http://www.google.com").get();

		} catch (Exception e) {
			log.debug("Не удалось установить подключение с " + url);
		}

		return doc;
	}
}
