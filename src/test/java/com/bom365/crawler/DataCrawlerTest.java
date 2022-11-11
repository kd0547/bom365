package com.bom365.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.bom365.constant.Gender;
import com.bom365.dto.AnimalDto;
import com.bom365.dto.Page;
import com.bom365.dto.PageDto;

@SpringBootTest
public class DataCrawlerTest {
	
	@Test
	void CrawlerTest() {
		List<AnimalDto> data = start();
		
		System.out.println(data.size());
		
	}
	/*
	 * 
	 * ps. 이 코드는 값이 밀리면 수정이 어려움 -> 즉 값이 다 있을 경우를 가정하고 짠 코드 
	 * 	 나중에 수정할 예정 
	 * 
	 */
	public List<AnimalDto> start() {
		DataCrawler crawler = new DataCrawler();
		List<AnimalDto> animalDtoList = null;
		for(int i = 1; i < 10;i++) {
			String URL = crawler.getURL(i);
			
			crawler.takeSiteConnection(URL);
			
			try {
				Document document = crawler.takeDocumentConnection();
				Element element = document.body();
				//Elements element2 = document.body().getElementsByClass("col-sm-6 col-md-3 mb-7");
				
				Elements imgElements = takeImgClass(element);
				Elements nameElements = takeNameClass(element);
				Elements etc = takeAgeWeightEtcText(element);
				
				
				
				
				animalDtoList = AnimalArrayToDtoList(searchAgeWeightEtc(etc),SearchAnimalhName(nameElements),SearchAnimalhImg(imgElements));
				
				
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		return animalDtoList;
	}
	
	public List<AnimalDto> AnimalArrayToDtoList(ArrayList<ArrayList<String>> ageWeightEtcList,ArrayList<String> animalNameList, ArrayList<String> imgElements) {
		int size = ageWeightEtcList.size();
		List<AnimalDto> animalDTOs = new ArrayList<AnimalDto>();
		
		 
		for(int i = 0; i < size; i++) {
			ArrayList<String> ageWeightEtc = ageWeightEtcList.get(i);
			AnimalDto animalDto = new AnimalDto();
			
			//System.out.println(ageWeightEtc.toString());
			//System.out.println(AgeConverter(ageWeightEtc.get(2)));
			animalDto.setAnimalType(ageWeightEtc.get(0));
			
			animalDto.setAnimalSpecies(ageWeightEtc.get(1));
			animalDto.setAnimalAge(AgeConverter(ageWeightEtc.get(2)));
			
			animalDto.setAnimalImage(imgElements.get(i));
			animalDto.setAnimalName(animalNameList.get(i));
			
			
			
			animalDto.setAnimalGender(genderType(ageWeightEtc.get(3)));
			
			animalDto.setNeutering(animalNameList.get(4));
			animalDto.setAnimalWeight(Double.parseDouble(ageWeightEtc.get(5)));
			
			animalDTOs.add(animalDto);
		}
		
		//System.out.println(animalDTOs.toString());
			 
			 
	
		
		return animalDTOs;
	}
	
	
	
	private Gender genderType(String gender ) {
		
		if(gender.equals("수컷")) {
			return Gender.Male;
		} else if(gender.equals("암컷")) {
			return Gender.Female;
		} else {
			return null;
		}
		
	}
	private double AgeConverter(String age) {
		String[] yearMonth = age.split(" ");
		int size = yearMonth.length;
		
		double d_year = 0;
		double d_Month = 0;
		String year = "";
		String month = "";
		
		for(int i=0;i < size;i++) {
			
			if(isAgeYear(yearMonth[i])) {
				year = yearMonth[i].replaceAll("살", "");
				
				if(!year.equals(null) || !year.equals("")){
					d_year = Double.parseDouble(year);
				}
			}
			
			if(isAgeMonth(yearMonth[i])) {
				month = yearMonth[i].replaceAll("개월", "");
				
				if(!month.equals(null) || !month.equals("")) {
					d_Month = Double.parseDouble(month)*0.01;
				}
			}
			
		}
		
		return d_year+d_Month;
	}
	public String SearchAnimalhName(Element animal) {
		return searchName(animal);
	}
	
	public ArrayList<String> SearchAnimalhName(Elements nameElements) {
		int size = nameElements.size();
		
		ArrayList<String> animalName = new ArrayList<String>(size);
		
		
		for(Element animal : nameElements) {
			animalName.add(searchName(animal));
		}
		
		return animalName;
		
	}
	
	public ArrayList<String> SearchAnimalhImg(Elements imgElements) {
		int size = imgElements.size();
		
		ArrayList<String> animalimg = new ArrayList<String>(size);
		
		
		for(Element animal : imgElements) {
			animalimg.add(searchImgSrc(animal));
		}
		
		//System.out.println(animalimg.toString());
		
		return animalimg;
		
	}
	
	
	
	
	public ArrayList<ArrayList<String>> searchAgeWeightEtc(Elements elements) {
		List<String> list = elements.eachText();	
		ArrayList<ArrayList<String>> returnAniamlResult = new ArrayList<ArrayList<String>>(list.size());
		
		for(String str : list) {
			String[] src = str.toString().split(" ");
			
			System.out.println();
			
			returnAniamlResult.add(AgeWeightEtc(src));
			
		}
		return returnAniamlResult;
	}
	
	
	public ArrayList<String> AgeWeightEtc(String[] src) {
		int size = src.length;
		ArrayList<String> SearchAnimalResult = new ArrayList<String>(size);
		String _type = null, _species = null, _gender = null, _neutering = null, _year = "", _month = "", _weight = null;
		
		for(int i = 0; i < size; i++) {
			
			if(isAnimalType(src[i])) {
				_type = searchAnimalType(src[i]);
				_species = searchSpecies(src[i]);
				
				
			}
			
			if(isAnimalGender(src[i])) {
				_gender = searchGender(src[i]);
				_neutering = searchNeutering(src[++i]);
				
			}
			
			if(isAgeYear(src[i]) || isAgeMonth(src[i])) {
				String year = isAgeYear(src[i]) ? src[i] : "0";
				String month = isAgeMonth(src[i])? src[i] : "0";
				
				if(!year.equals("0")) {
					_year = year;
					
				}
				
				if(!month.equals("0")) {
					_month = month;
					
				}
				
			}
			
			if(isWeight(src[i])) {
				_weight = searchWeight(src[i]);
				
			}
		}
		
		SearchAnimalResult.add(_type);
		SearchAnimalResult.add(_species);
		SearchAnimalResult.add(_year+" "+_month);
		SearchAnimalResult.add(_gender);
		SearchAnimalResult.add(_neutering);
		SearchAnimalResult.add(_weight);
		
		
		return SearchAnimalResult;
		
		
	}
	
	
	
	
	
	
	private boolean isWeight(String string) {
		return string.contains("kg");
	}
	
	
	private boolean isAgeYear(String year) {
		
		return year.contains("살") ;
	}
	private boolean isAgeMonth(String month) {
		
		return month.contains("개월") ;
	}
	
	public boolean isAnimalType(String type) {
		return type.contains("고양이(") || type.contains("개(") || type.contains("기타(");
	}
	
	
	public boolean isAnimalGender(String gender) {
		return gender.contains("암컷") || gender.contains("수컷");
	}
	
	public String searchWeight(String weight) {
		return weight.replaceAll("kg", "");
	}
	
	
	
	
	public Elements takeImgClass(Element element) {
		return element.getElementsByClass("card-img-top");
	}
	
	public Elements takeNameClass(Element element) {
		return element.getElementsByClass("h5 font-weight-bold");
	}
	
	public Elements takeAgeWeightEtcText(Element element) {
		return element.getElementsByClass("text-secondary mb-0");
	}
	
	
	public String searchName(Element element) {
		return element.text();
	}
	public String searchImgSrc (Element element) {
		return element.attr("src");
	}
	public String searchAnimalType(String src) {
		return src.replaceAll("[^개|고양이|기타]"," ").trim();
	}
	public String searchGender(String gender) {
		return gender.replaceAll("[^암컷|수컷]"," ").trim();
	}
	
	public String searchSpecies(String Species) {
		
		return Species.replaceAll("[고양이|개|기타]+", "").replaceAll("[( | )]+","").trim();
	}
	
	public String searchNeutering(String neutering) {
		return neutering.replaceAll("[( )]", "").trim();
	}
}
