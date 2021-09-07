use world;

desc countrylanguage;
select * from country;
select * from countrylanguage;
select * from city;

-- 1. 도시명 kabul이 속한 국가의 이름은?
select co.code, co.name
from country co, city ci
where co.code=ci.CountryCode and ci.name='kabul';

-- 2. 국가의 공식 언어 사용율이 100%인 국가의 정보를 출력하시오. 국가 이름으로 오름차순 정렬한다.(8건)
select c.name, l.language, l.percentage
from country c, countrylanguage l
where c.code=l.CountryCode and l.Percentage=100 and l.IsOfficial='T'
order by c.name;

-- 3. 도시명 amsterdam에서 사용되는 주요 언어와 amsterdam이 속한 국가는?
select ci.name, cl.language, co.name
from country co, countrylanguage cl, city ci
where ci.name='Amsterdam' and co.code=cl.CountryCode and co.code=ci.CountryCode and cl.IsOfficial='T';

-- 4. 국가명이 united로 시작하는 국가의 정보와 수도의 이름, 인구를 함께 출력하시오. 단 수도 정보가 없다면 출력하지 않는다. (3건)
-- select * from city where countrycode='KOR';
select co.name, co.capital, ci.name "수도", ci.population "수도인구"
from country co 
join city ci
on co.Capital=ci.id
where co.name like 'United%';

-- 5. 국가명이 united로 시작하는 국가의 정보와 수도의 이름, 인구를 함께 출력하시오. 단 수도 정보가 없다면 수도 없음이라고 출력한다. (4건)
select co.name, co.capital, ci.name "수도", ci.population "수도인구"
from country co 
left outer join city ci
on co.Capital=ci.id
where co.name like 'United%';

-- 6. 국가 코드 che의 공식 언어 중 가장 사용률이 높은 언어보다 사용율이 높은 공식언어를 사용하는 국가는 몇 곳인가?
select count(co.name) "국가수"
from country co, countrylanguage cl
where cl.IsOfficial='T' and co.code=cl.countrycode and cl.Percentage > all (
	select Percentage
    from countrylanguage
    where CountryCode='CHE' and IsOfficial='T'
);

-- 7. 국가명 south korea의 공식 언어는?
select cl.language
from country co, countrylanguage cl
where co.code = cl.CountryCode and co.code='KOR' and cl.IsOfficial='T';

-- 8. 국가 이름이 bo로 시작하는 국가에 속한 도시의 개수를 출력하시오. (3건)
select co.name, co.code, count(ci.CountryCode) 도시개수
from country co, city ci
where co.code = ci.CountryCode
group by co.name 
having co.name like 'bo%';

-- 9. 국가 이름이 bo로 시작하는 국가에 속한 도시의 개수를 출력하시오. 도시가 없을 경우는 단독 이라고 표시한다.(4건)
select co.name, co.code, 
	case when count(ci.CountryCode)!=0 then count(ci.CountryCode)
    else '단독'
    end 도시개수
from country co 
left outer JOIN city ci
on co.code = ci.CountryCode
group by co.name 
having co.name like 'bo%';

-- 10. 인구가 가장 많은 도시는 어디인가?
select CountryCode, name, population
from city
where Population = (
	select max(population)
    from city
);

-- 11. 가장 인구가 적은 도시의 이름, 인구수, 국가를 출력하시오.
select co.name, co.code, ci.name, ci.population
from country co, city ci
where co.code = ci.CountryCode and ci.Population = (
	select min(Population)
    from city
);

-- 12. KOR의 seoul보다 인구가 많은 도시들을 출력하시오.
select countrycode, name, population
from city
where population>(
	select Population
    from city
    where name='Seoul'
);

-- 13. San Miguel 이라는 도시에 사는 사람들이 사용하는 공식 언어는?
select ci.countrycode, cl.language
from city ci, countrylanguage cl
where ci.CountryCode = cl.CountryCode and ci.name='San Miguel' and cl.IsOfficial='T';

-- 14. 국가 코드와 해당 국가의 최대 인구수를 출력하시오. 국가 코드로 정렬한다.(232건)
select countrycode, max(population) "최대인구"
from city
group by CountryCode; 

-- 15. 국가별로 가장 인구가 많은 도시의 정보를 출력하시오. 국가 코드로 정렬한다.(232건)
select countrycode, name, max(population) "최대인구"
from city
group by CountryCode
order by CountryCode;

--  16. 국가 이름과 함께 국가별로 가장 인구가 많은 도시의 정보를 출력하시오.(239건)
select co.code, co.name, ci.name, max(ci.population) "최대인구"
from country co
left outer join city ci
on ci.countrycode=co.code
group by co.code
order by co.code;

-- 17. 위 쿼리의 내용이 자주 사용된다. 재사용을 위해 위 쿼리의 내용을 summary라는 이름의 view로 생성하시오.
create table summary
select co.code, co.name co_name, ci.name ci_name, max(ci.population) "population"
from country co
left outer join city ci
on ci.countrycode=co.code
group by co.code
order by co.code;

-- 18. summary에서 KOR의 대표도시를 조회하시오.
select * from summary
where code='KOR';
