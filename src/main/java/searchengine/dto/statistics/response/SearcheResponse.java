package searchengine.dto.statistics.response;

import lombok.Getter;
import lombok.Setter;
import searchengine.dto.SearchDto;

import java.util.List;

@Getter
@Setter
public class SearcheResponse {
	private boolean result;
	private int count;
	private List<SearchDto> data;

	public SearcheResponse(boolean result) {
		this.result = result;
	}

	public SearcheResponse(boolean result, int count, List<SearchDto> data) {
		this.result = result;
		this.count = count;
		this.data = data;
	}
}
