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

-- 1. 부서위치가 CHICAGO 인 모든 사원에 대해 이름 업무 급여 출력하는 SQL을 작성하세요
select ename, job, sal
from emp e, dept d
where e.deptno=d.deptno and d.loc='CHICAGO';

-- 2. 부하직원이 없는 사원의 사원번호 이름 업무 부서번호 출력하는 SQL 을 작성하세요
select empno, ename, job, deptno
from emp
where empno not in (
	select distinct mgr
    from emp
    where mgr is not null
);

-- 3. BLAKE 와 같은 상사를 가진 사원의 이름 업무 상사번호 출력하는 SQL 을 작성하세요
select ename, job, mgr
from emp
where mgr = (
	select mgr
    from emp
    where ename='BLAKE'
);

-- 3. BLAKE 와 같은 상사를 가진 사원의 이름 업무 상사번호 출력하는 SQL 을 작성하세요
-- BLAKE 제외
select ename, job, mgr
from emp
where ename != 'BLAKE' and mgr = (
	select mgr
    from emp
    where ename='BLAKE'
);

-- 4. 입사일이 가장 오래된 사람 5 명을 검색하세요.
select ename, hiredate
from emp
order by hiredate 
limit 5;

-- 5. JONES 의 부하 직원의 이름 , 업무 , 부서명을 검색하세요.
select e.ename, e.job, d.dname
from emp e, dept d
where e.mgr=(
	select empno
    from emp
    where ename='JONES'
) and e.deptno=d.deptno;