package com.bom365.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


import com.bom365.crawler.DataCrawler;
import com.bom365.dto.AnimalDto;
import com.bom365.entity.Animal;
import com.bom365.service.CrawlerService;

@Component

public class AppStartConfig implements ApplicationRunner{
	
	@Autowired
	CrawlerService crawlerService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		
		if(crawlerService.findData()) {
			DataCrawler dataCrawler = new DataCrawler();
			
			for(int i=1;i<10;i++) {
				List<AnimalDto> animalDtoList = dataCrawler.start(i);
				List<Animal> animals = new ArrayList<Animal>();
				int size = animalDtoList.size();
				
				for(int j = 0; j<size; j++) {
					AnimalDto animalDto =  animalDtoList.get(j);
					Animal animal = Animal.createAnimalEntity(animalDto);
					
					animals.add(animal);
				}

				crawlerService.saveAll(animals);
			
			}
		}
	}

}
