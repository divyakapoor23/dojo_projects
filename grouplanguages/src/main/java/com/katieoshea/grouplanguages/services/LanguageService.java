package com.katieoshea.grouplanguages.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.katieoshea.grouplanguages.models.Language;

@Service
public class LanguageService {
	
	List<Language> langs = new ArrayList<Language>();
	public LanguageService() {
		langs.add(new Language("Java", "Ryan Gosling", 1.8));
		langs.add(new Language("Python", "Oscar Isaac", 11.3));
		langs.add(new Language("Mean", "Idris Elba", 6.0));
	}

	
	public List<Language> showAll() {
		return langs;
	}

	public Language showLang(int index) {
        if (index < langs.size()) {
            return langs.get(index);
        }
        else {
            return null;
        }	
	}
    public void addLang(Language lang) {
        langs.add(lang);
	}
	public void delete(int index){
		langs.remove(index);
	}
	public void updateLang(int index, Language lang) {
		langs.add(index, lang);
		langs.remove(index+1);
	}
}
