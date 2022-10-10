package com.bom365.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;



import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bom365.constant.Gender;
import com.bom365.entity.Animal;
import com.bom365.entity.QAnimal;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@WebAppConfiguration
@SpringBootTest
class AnimalSearchDtoTest {
	
	
	@Autowired
	EntityManager entityManager;
	
	QAnimal animal = QAnimal.animal;
	
	
	@Test
	public void getDtoClass() {
		//AnimalSearchDto animalSearchDto = createAnimalSearchDto();
	
		
		//System.out.println("================");
		
		//animalSearchDto.SearchAnimalQuery(animalSearchDto);
		
		
		
		//System.out.println(AnimalSearchDto.class);
	}
	
	@Test
	public void search() {
		/*
		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
		
		QAnimal qAnimal = new QAnimal("m");
		
		List<Animal> findAnimals = queryFactory.select(qAnimal)
					.from(qAnimal)
					.where(qAnimal.animalSpecies.eq("고양이"))
					.fetch();
		
		//List<Animal> findAnimals =  animal.fetch();
		
		for(Animal a : findAnimals) {
			System.out.println(a.toString());
		}
		*/
		
		
	}
	@Test
	public void search2() {
		/*
		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
		
		QAnimal qAnimal = new QAnimal("m");
		
		List<Animal> findAnimals = queryFactory.select(qAnimal)
					.from(qAnimal)
					.where(qAnimal.animalSpecies.eq("고양이"))
					.fetch();
		
		//List<Animal> findAnimals =  animal.fetch();
		
		for(Animal a : findAnimals) {
			System.out.println(a.toString());
		}
		
		*/
		
	}
	@Test
	public void betweenTest() {
		//AnimalSearchDto animalSearchDto = createAnimalSearchDto();
		
		//System.out.println(animalSearchDto.toString());
			
		
		//List<Animal> animals = dymanicWhereAniamlSearch(animalSearchDto);
		
		//for(Animal a : animals) {
		//	System.out.println(a.toString());
		//}
	}
	
	@Test
	public void paging1() {
		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
		
		 List<Tuple> result = queryFactory
				 .select(animal.count(),
						 animal.animalAge.sum(),
						 animal.animalAge.avg(),
						 animal.animalAge.max(),
						 animal.animalAge.min())
				 .from(animal)
				 .fetch();
		 Tuple tuple = result.get(0);
		 
		 System.out.println(tuple.get(animal.animalAge.sum()));
		 System.out.println(tuple.get(animal.animalAge.avg()));
		 System.out.println(tuple.get(animal.animalAge.max()));
		 System.out.println(tuple.get(animal.animalAge.min()));
		
	}
	/**
	 * 팀의 이름과 각 팀의 평균 연령을 구해라.
	 */
	
	
	/*
	@Test
	public void dynamicQuery_BooleanBuilder() {
		String usernameParam = "member1";
		Integer ageParam = 10;
		
		List<Member> result = searchMember1(usernameParam,ageParam);
		assertThat(result.size()).isEqualTo(1);
		
	}
	
	private List<Member> searchMember1(String usernameCond, Integer ageCond) {
		BooleanBuilder builder = new BooleanBuilder();
		
		if(usernameCond != null) {
			builder.and(member.username.eq(usernameCond));
		}
		
		if(ageCond != null) {
			builder.and(member.age.eq(ageCond));
		}
		
		return queryFactory
					.selectFrom(member)
					.where(builder)
					.fetch();
	}
	*/
	
	
	
	public List<Animal> dymanicWhereAniamlSearch(AnimalSearchDto animalSearchDto) {
		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
		
		
		
		
		return queryFactory.select(animal)
							.from(animal)
							.where( animalNameEq(animalSearchDto.getAnimalName()),
									animalSpeciesEq(animalSearchDto.getAnimalSpecies()),
									animalGenderEq(animalSearchDto.getAnimalGender()),
									animalWeightBetween(animalSearchDto.getAnimalWeight()),
									animalAgeBetween(animalSearchDto.getAnimalAge())
									)
							.fetch();
	}
	
	
	public Predicate animalNameEq(String animalName) {
		
		return animalName == null ? null : animal.animalName.eq(animalName);
	}
	
	public Predicate animalSpeciesEq(String animalSpecies) {
		return animalSpecies == null ? null : animal.animalSpecies.eq(animalSpecies);
	}
	
	public Predicate animalGenderEq(Gender animalGender) {
		return animalGender == null ? null : animal.animalGender.eq(animalGender);
	}
	
	public Predicate animalWeightBetween(String weight) {		
		return weight == null ? null : animal.animalWeight.between(min('~', weight), max('~',weight));
	}
	
	public Predicate animalAgeBetween(String age) {
		return  age == null ? null : animal.animalAge.between(min('~', age), max('~',age));
	}
	
	
	
	public double min(char point,String str) {
		int source = str.indexOf(point);
		int defaultMin = 0;
		
		String str2 = str.substring(defaultMin, source);
		
		return Double.parseDouble(str2);
	}
	
	
	public double max(char point,String str) {
		int source = str.indexOf(point);
		int end = str.length();
		
		String str2 = str.substring(source+1, end);
		
		return Double.parseDouble(str2);
	}
	
	
	public AnimalSearchDto createAnimalSearchDto() {
		AnimalSearchDto animalSearchDto = new AnimalSearchDto();
		
		animalSearchDto.setAnimalAge("0~0.6");
		//animalSearchDto.setAnimal_weight("18.1");
		animalSearchDto.setAnimalSpecies("개");
		//animalSearchDto.setAnimalGender(Gender.Male);
		//animalSearchDto.setAnimalName("");
		
		return animalSearchDto;
	}
	
}
