CREATE database SCOTT;

USE SCOTT;

CREATE TABLE IF NOT EXISTS `BONUS` (
  `ENAME` varchar(10) DEFAULT NULL,
  `JOB` varchar(9) DEFAULT NULL,
  `SAL` double DEFAULT NULL,
  `COMM` double DEFAULT NULL
);


CREATE TABLE IF NOT EXISTS `DEPT` (
  `DEPTNO` int(11) NOT NULL,
  `DNAME` varchar(14) DEFAULT NULL,
  `LOC` varchar(13) DEFAULT NULL,
  PRIMARY KEY (`DEPTNO`)
);



INSERT INTO `DEPT` (`DEPTNO`, `DNAME`, `LOC`) VALUES
(10, 'ACCOUNTING', 'NEW YORK'),
(20, 'RESEARCH', 'DALLAS'),
(30, 'SALES', 'CHICAGO'),
(40, 'OPERATIONS', 'BOSTON');


CREATE TABLE IF NOT EXISTS `EMP` (
  `EMPNO` int(11) NOT NULL,
  `ENAME` varchar(10) DEFAULT NULL,
  `JOB` varchar(9) DEFAULT NULL,
  `MGR` int(11) DEFAULT NULL,
  `HIREDATE` datetime DEFAULT NULL,
  `SAL` double DEFAULT NULL,
  `COMM` double DEFAULT NULL,
  `DEPTNO` int(11) DEFAULT NULL,
  PRIMARY KEY (`EMPNO`),
  KEY `PK_EMP` (`DEPTNO`)
);


INSERT INTO `EMP` (`EMPNO`, `ENAME`, `JOB`, `MGR`, `HIREDATE`, `SAL`, `COMM`, `DEPTNO`) VALUES
(7369, 'SMITH', 'CLERK', 7902, '1980-12-17 00:00:00', 800, NULL, 20),
(7499, 'ALLEN', 'SALESMAN', 7698, '1981-02-20 00:00:00', 1600, 300, 30),
(7521, 'WARD', 'SALESMAN', 7698, '1981-02-22 00:00:00', 1250, 500, 30),
(7566, 'JONES', 'MANAGER', 7839, '1981-04-02 00:00:00', 2975, NULL, 20),
(7654, 'MARTIN', 'SALESMAN', 7698, '1981-09-28 00:00:00', 1250, 1400, 30),
(7698, 'BLAKE', 'MANAGER', 7839, '1981-05-01 00:00:00', 2850, NULL, 30),
(7782, 'CLARK', 'MANAGER', 7839, '1981-06-09 00:00:00', 2450, NULL, 10),
(7788, 'SCOTT', 'ANALYST', 7566, '1987-04-19 00:00:00', 3000, NULL, 20),
(7839, 'KING', 'PRESIDENT', NULL, '1981-11-17 00:00:00', 5000, NULL, 10),
(7844, 'TURNER', 'SALESMAN', 7698, '1981-09-08 00:00:00', 1500, 0, 30),
(7876, 'ADAMS', 'CLERK', 7788, '1987-05-23 00:00:00', 1100, NULL, 20),
(7900, 'JAMES', 'CLERK', 7698, '1981-12-03 00:00:00', 950, NULL, 30),
(7902, 'FORD', 'ANALYST', 7566, '1981-12-03 00:00:00', 3000, NULL, 20),
(7934, 'MILLER', 'CLERK', 7782, '1982-01-23 00:00:00', 1300, NULL, 10);

CREATE TABLE IF NOT EXISTS `SALGRADE` (
  `GRADE` double DEFAULT NULL,
  `LOSAL` double DEFAULT NULL,
  `HISAL` double DEFAULT NULL
);


INSERT INTO `SALGRADE` (`GRADE`, `LOSAL`, `HISAL`) VALUES
(1, 700, 1200),
(2, 1201, 1400),
(3, 1401, 2000),
(4, 2001, 3000),
(5, 3001, 9999);


ALTER TABLE `EMP`
  ADD CONSTRAINT `PK_EMP` FOREIGN KEY (`DEPTNO`) REFERENCES `DEPT` (`DEPTNO`) ON DELETE SET NULL ON UPDATE CASCADE;


select * from emp;
select * from dept;

-- 1. emp 와 dept Table 을 JOIN 하여 이름 , 급여 , 부서명을 검색하세요
select e.ename, e.sal, d.dname
from emp e, dept d
where e.deptno=d.deptno;

-- 2. 이름이 KING’ 인 사원의 부서명을 검색하세요
select e.ename, d.dname
from emp e, dept d
where e.ename='KING' and e.deptno=d.deptno;

-- 3. dept Table 에 있는 모든 부서를 출력하고 , emp Table 에 있는 DATA 와 JOIN 하여 모든 사원의 이름 , 부서번호 , 부서명 , 급여를 출력 하라
select e.ename, e.empno, d.deptno, d.dname, e.sal
from emp e right outer join dept d
on e.deptno=d.deptno;

-- 4. emp Table 에 있는 empno 와 mgr 을 이용하여 서로의 관계를 다음과 같이 출력되도록 쿼리를 작성하세요 . ‘SCOTT 의 매니저는 JONES 이다’
select concat(e.ename, '의 매니저는 ', m.ename, '이다.')
from emp e, emp m
where e.mgr = m.empno;

-- 5. 'SCOTT' 의 직무와 같은 사람의 이름 , 부서명 , 급여 , 직무를 검색하세요
select e.ename, d.dname, e.sal, e.job
from emp e, dept d
where e.job=(
	select job
    from emp
    where ename='SCOTT'
) and e.deptno=d.deptno;

-- 6.'SCOTT' 가 속해 있는 부서의 모든 사람의 사원번호 , 이름 , 입사일 , 급여를 검색하세요.
select empno, ename, hiredate, sal
from emp
where deptno = (
	select deptno
    from emp
    where ename='SCOTT'
);

-- 7. 전체 사원의 평균급여보다 급여가 많은 사원의 사원번호 , 이름 부서명 , 입사일 , 지역 , 급여를 검색하세요.
select e.empno, d.dname, e.hiredate, d.loc, e.sal
from emp e, dept d
where e.deptno=d.deptno and e.sal > (
	select avg(sal)
    from emp
);

-- 8. 30 번 부서와 같은 일을 하는 사원의 사원번호 , 이름 , 부서명 지역 , 급여를 급여가 많은 순으로 검색하세요.
select e.empno, e.ename, d.dname, d.loc, e.sal
from emp e, dept d
where e.deptno=d.deptno and e.job in (
	select distinct job
    from emp
    where deptno=30
)
order by e.sal desc;

-- 9. 10 번 부서 중에서 30 번 부서에는 없는 업무를 하는 사원의 사원번호 , 이름 , 부서명 , 입사일 , 지역을 검색하세요.
select e.empno, e.ename, d.dname, e.hiredate, d.loc
from emp e, dept d
where e.deptno=10 and e.deptno=d.deptno and e.job not in (
	select distinct job
    from emp
    where deptno=30
);

-- select * from emp where deptno=30;

-- 10. 'KING’ 이나 'JAMES' 의 급여와 같은 사원의 사원번호 , 이름 급여를 검색하세요.
select empno, ename, sal
from emp
where sal in (
	select sal
    from emp
    where ename='KING' or ename='JAMES'
);

-- 11. 급여가 30 번 부서의 최고 급여보다 높은 사원의 사원번호 이름 , 급여를 검색하세요.
select empno, ename, sal
from emp
where sal > all(
	select sal
    from emp
    where deptno=30
);

-- 12. 이름 검색을 보다 빠르게 수행하기 위해 emp 테이블 ename 에 인덱스를 생성하시오.
alter table emp add index nameindex(ename);
desc emp;

-- 13. 이름이 'ALLEN' 인 사원의 입사연도가 같은 사원들의 이름과 급여를 출력하세요. 
select ename, sal
from emp
where year(hiredate)=(
	select year(hiredate)
    from emp
    where ename='ALLEN'
);

-- 14. 부서별 급여의 합계를 출력하는 View 를 작성하세요.
create view salsum as
select deptno, sum(sal) 급여총합
from emp
group by deptno;

select * from salsum;

-- 15. 14 번에서 작성된 View 를 이용하여 부서별 급여의 합계가 큰 1~3 순위를 출력하세요.
select *
from salsum
order by 급여총합 desc;

use scott;
select * from emp;
desc emp;

