package searchengine.morphology;

import java.util.List;

public class TestMorph {

	public static void main(String[] args) {
		LuceneMorphology luceneMorphology = new RussianLuceneMorphology();
		List<String> wordBaseForms = luceneMorphology.getNormalForms("леса");
		wordBaseForms.forEach(System.out::println);
	}

}
