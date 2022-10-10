package com.bom365.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;

import static com.bom365.entity.QMember.*;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bom365.entity.Member;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

class MemberDtoTest {
	
	@Autowired
	EntityManager entityManager;
	
	JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
	

	@Test
	public void dynamicQuery_BooleanBuilder() {
		
		String usernameParam = "member1";
		Integer ageParam = 10;
		
		//List<Member> result = searchMember1(usernameParam,ageParam);
		//assertThat(result.size()).isEqualTo(1);
		
	}
/*
	@Test
	public void sqlFunction() {
		String result = queryFactory
				 .select(Expressions.stringTemplate("function('replace', {0}, {1}, {2})", member.username, "member", "M"))
				 .from(member)
				 .fetchFirst();
				.select(member.username)
				.from(member)
				.where(member.username.eq(Expressions.stringTemplate("function('lower', {0})",member.username)))
	}

	@Test
	public void sqlFunction2() {
		String result = queryFactory
				.select(member.username)
				.from(member)
				.where(
						member.username.eq(
								Expressions.stringTemplate("function('lower', {0})",member.username))
				.fetch();

	}
	
	@Test
	public void bulkUpdate() {
		
		long count = queryFactory
				.update(member)
				.set(member.username, "비회원")
				.where(member.age.lt(28))
				.execute();
		long count = queryFactory
				 .delete(member)
				 .where(member.age.gt(18))
				 .execute();
	}
*/	
/*
	@Test
	public void dynamicQuery_WhereParam() {
		String usernameParam = "member1";
		Integer ageParam = 10;
		
		List<Member> result = searchMember2(usernameParam, ageParam);
		assertThat(result.size()).isEqualTo(1);
		
		
	}
	
	
	private List<Member> searchMember2(String usernameCond, Integer ageCond) {
		return queryFactory
					.select(member)
					.where(usernameEq(usernameCond),ageEq(ageCond))
					.fetch();
	}
	
	
	

	private Predicate ageEq(Integer ageCond) {
		if(ageCond != null) {
			return member.age.eq(ageCond);
		}
		return null;
	}

	private Predicate usernameEq(String usernameCond) {
		if(usernameCond != null) {
			return member.username.eq(usernameCond);
		}
		
		return null;
	}
*/
/*
	private List<Member> searchMember1(String usernameCond, Integer ageCond) {
		BooleanBuilder builder = new BooleanBuilder();
		
		if(usernameCond != null) {
			builder.and(member.supporterName.eq(usernameCond));
		}
		
		if(ageCond != null) {
			builder.and(member.supporterId.eq(ageCond));
		}
		
		return queryFactory
					.selectFrom(member)
					.where(builder)
					.fetch();
	}
*/
}
