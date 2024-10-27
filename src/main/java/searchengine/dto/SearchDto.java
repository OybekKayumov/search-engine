package searchengine.dto;

import lombok.Data;

@Data
public class SearchDto {

	private String site;
	private String siteName;
	private String uri;
	private String title;
	private String snippet;
	private Float relevance;

}
