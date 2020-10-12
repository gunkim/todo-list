# 스프링 부트와 리액트로 만들어 보는 할 일 리스트

## 개발 환경

> Java : 1.8  
> Gradle : 6.6  
> React : 16.13.1  
> Spring Boot : 2.2.4.RELEASE

## 구현 기능

1. 할 일 작성
2. 할 일 목록 확인
3. 할 일 삭제
4. 로그인

## 돌려보는 방법

### 서버 실행

빌드된 jar파일을 포함시켜놨기 때문에 jar파일만을 실행하면 된다.

> git clone https://github.com/gunkim0318/todo-list.git  
> cd todo-list  
> java -jar todo-list-1.0.jar

### 빌드 방법

> web\my-app에서 "npm i" 명령어를 통해 라이브러리 다운로드  
> 다시 루트 디렉토리에서 "gradle build" 명령어를 통해 gradle 빌드  
> build\libs에서 "java -jar todo-list-1.0.jar" 명령어를 통해 서버 실행

### 확인

> http://localhost:8080 접속

**로그인 정보**

> id : gunkim  
> pw : test

![image](https://user-images.githubusercontent.com/45007556/92085569-fb7ff100-ee03-11ea-843c-fd846c253519.png)

![image](https://user-images.githubusercontent.com/45007556/92085903-6d583a80-ee04-11ea-8cc7-337db3f1676d.png)
