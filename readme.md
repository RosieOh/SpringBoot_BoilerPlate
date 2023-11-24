# test01 : [Spring Boot Gradle + WAR + JSP + MariaDB + Mybatis + Lombok](#01)

### Maven VS Gradle

### Spring boot 3.0.x VS 2.7.x

### MyBatis VS Data JPA

### application.properties VS application.yml

### 암호화 및 보안 - Session / Intercepter / Spring Security / JWT / OAuth

### 뷰 템플릿(view template) - Jsp / Thymeleaf / FreeMarker / Groovy / Mustache 

### Spring Boot Annotation

### application.properties

<br>

# test02 : [Spring Boot Maven + WAR + JSP + MariaDB + Mybatis + Lombok](#02)

### application.yml

### logback.xml

<br><hr><br>

# test03 : [Spring Boot Gradle + WAR + JSP/JSTL + MariaDB + Mybatis + Lombok](#03)

### DI/IoC

### Table, mapper.xml, Mapper, Service, Controller, View(JSP)

### Session

<br>

# test04 : [Spring Boot Gradle + WAR + JSP/JSTL + MariaDB + Mybatis + Lombok](#04)

### Restful API / LOGBACKUP / Exception

<br>

# test05 : [Spring Boot 3.0.12 Gradle + WAR + JSP/JSTL + MariaDB + Mybatis + Lombok + Spring Security 6.0.8](#05)

### Spring Security 6.0.8

<br>

# test06 : [Spring Boot 2.7.17 Gradle + WAR + JSP/JSTL + MariaDB + Mybatis + Lombok + Spring Security 5.7.11](#06)

### JSP/JSTL

<br>

# test07 : [Spring Boot 2.7.17 Gradle + WAR + Thymeleaf + MariaDB + Mybatis + Lombok + Spring Security 5.7.11](#07)

### Thymeleaf

<br>

# test08 :  [Spring Boot 2.7.17 Maven + WAR + FreeMarker + MariaDB + Jpa + Lombok] (#08)

### FreeMarker

<br>

# test09 : [Spring Boot 2.7.17 Maven + WAR + Mustache + MariaDB + JPA + Lombok + webjars](#09)

### Mustache

<br>

# test10 : [Spring Boot 2.7.17 Gradle + WAR + Thymeleaf + MariaDB + JPA + Lombok + Spring Security 5.7.11](#10)

### Thymeleaf, Spring Data JPA, RestfulAPI, Entity Relation Mapping, Axios, File Upload, Spring Security, WebSocket Chatting

<br>

# test11 : [Spring Boot 3.1.1 Gradle + JAR + Thymeleaf + MariaDB + Calendar + Batch + Quartz](#11)

### Calendar, Batch, Quartz, CBT

<br>

# test12 : [Spring Boot 3.1.1 Gradle + JAR + Thymeleaf + MariaDB + Editor](#12)

### Spelling Checker, Monaco Editor, HTML Editor, Formula, PDF Generator

<br>

# test13 : [Spring Boot 3.1.1 Gradle + JAR + Thymeleaf + MariaDB + Blog](#13)

### 

<br>

# test14 : [Spring Boot 3.1.1 Gradle + JAR + Thymeleaf + MariaDB + Share System](#14)

### 

<br>

# test15 : [Spring Boot 3.1.1 Gradle + JAR + Thymeleaf + MariaDB + Admin LTE](#15)

### 

<br>

# test16 : [Spring Boot 3.1.1 Gradle + React + UI/UX](#16)

### Backend : Spring Boot 3.1.1 Gradle, Thymeleaf, MariaDB

### Frontend : React, React DOM, Axios

<br>

<br><br><hr><br><br>

<div id="01"></div>

# 01. Spring Boot Gradle + WAR + JSP + MariaDB + Mybatis + Lombok - test01

## 01-01. Maven VS Gradle

- Build Tools

프레임워크를 활용한다는 것은 개발자 자신이 작성한 코드만 활용하는 것이 아니라 미리 정해진 틀에 개발자가 코드를 작성하게 되는데 이 때 미리 정해진 틀에는 수 많은 라이브러리들이 존재하며, 제공되는 라이브러리 중에서 사용할 라이브러리를 정하게 되는데, 이 때 라이브러리 패키지그룹명, 패키지명, 버전 등을 같이 코딩하여 두면, 현재 프로젝트에 네트워크를 통하여 그에 해당하는 라이브러리를 다운로드하여 활용할 수 있게 해주게 되며, 이를 통하여 필요한 프로젝트를 구현, 테스트, 배포할 수 있도록 해주는 도구를 말합니다.

### 01-01-1. Maven

프로젝트의 전체적인 라이프사이클을 관리하는 도구로서 여러 단계의 라이프사이클을 자동 혹은 수동으로 진행하게 되는데 이러한 단계를 Phase 라고 부르며, Phase 는 다음과 같습니다.

- Clean : 이전 빌드 작업에서 생성된 생성물을 삭제하는 단계 (mvn clean)
- Validate : 프로젝트가 올바르게 작성되었는지 확인하는 단계 
- Compile : 프로젝트의 소스코드를 컴파일하는 단계 (mvn complile) 
- Test : 단위 테스트를 수행하는 단계 (mvn test)
- Package : 실제 컴파일된 소스 코드와 리소스를 jar 또는 war 등의 하나의 압축 파일 형태로 묶는 단계 (mvn package)
- Verify : 테스트 결과에 대한 검사를 실행하여 품질 기준이 충족한지 확인하는 단계
- Install : 하나의 압축 파일 형태인 패키지를 로컬 저장소에 설치하는 단계 (mvn install)
- Site : 프로젝트 문서를 생성하는 단계
- Deploy : 만들어진 Package를 원격 저장소에 Push하는 단계 (mvn deploy)

위와 같은 9단계는 마치 전통적인 폭포수 모델(Waterfall Model)과 같은 개발방법론과 잘 맞아 다른 빌드보다 많이 사용되고 있습니다.

#### 1) POM(Project Object Model) : pom.xml

POM은 이름 그대로 프로젝트 정보로 해당 프로젝트 디렉토리의 처음 위치에 pom.xml로 저장되어 관리되며, 이 파일은 프로젝트의 버전, 빌드 환경, 자바 버전, 로그, 의존성 라이브러리 등록 및 관리, 프로젝트에 필요한 실행 환경과 플러그인 등을 정보 등을 설정하고, Maven 방식으로 빌드하게 되면, 설정한 정보로 프로젝트를 설정하고, 필요한 라이브러리(jar) 등을 불러오고, 개발자가 프로그래밍한 내용들을 번들링(bundling)하여 하나의 애플리케이션으로 묶어 실행될 수 있도록 구성하는 역할을 한다. 파일 작성 문법은 xml 문법에 따르도록 하고 있다.


#### 2) mvn 명령어

- mvn clean : 메이븐 빌드를 통하여 생성된 이전 결과물을 모두 제거한다.
- mvn compile : 컴파일 수행, 컴파일 된 결과는 target/classes에 생성된다.
- mvn test : 테스트 코드를 컴파일한 뒤 테스트 코드를 실행하며, 테스트 클래스들은 target/test-classes 디렉터리에 생성되고, 테스트 결과 리포트는 target/surefire-reports에 생성된다.
- mvn package : 컴파일, 테스트, 빌드를 한 꺼번에 순서대로 수행하여 패키징 파일(jar/war)이 build 또는 out 디렉토리에 생성된다.
- mvn install : 패키징한 파일을 지정한 로컬 저장소에 배포
- mvn deploy : 패키징한 파일을 지정한 원격 저장소에 배포
- mvn의 자주 사용하는 옵션
1) -Dmaven.test.skip=true
 . 테스트를 건너뛴다.
 . 예) mvn package -Dmaven.test.skip=true : 테스트를 건너뛰고 패키징 수행
2) mvn -P[profile명] [goals]
 . 환경에 따라 다르게 설정을 관리할 수 있는 기능아다. profile(프로파일)이라고 부름.
 . pom에서 설정할수 있다.
 . 예) mvn -Pdevelopment -Dmaven.test.skip=true package
     : development라고 정의한 profile 설정으로 테스트를 건너뛰고 패키징 수행



### 01-01-2. Gradle

Gradle은 기본적인 빌드 도구(Build Tool)로서 처음에는 안드로이드 앱의 공식 빌드시스템이기도 하였으며 JAVA, C/C++, Python 등을 지원한다.
메이븐(Maven) 방식과 달리 xml이 아닌 groovy 언어나 kotlin 언어를 선택하여 빌드 설정 파일을 작성할 수 있으며, 조건문(is, else)이나 반복문(for) 등의 제어문도 사용할 수 있어 효과적으로 프로젝트에 대한 설정도 간단해지며, 빌드 속도도 훨씬 빠르다.

#### 1) Gradle : build.gradle

build.gradle 파일은 Gradle 빌더를 활용할 경우 groove 또는 kotlin 스크립트 언어로 해당 프로젝트에 대한 정보와 의존 라이브러리를 설정하도록 하는 파일이다.

- buildscript : gradle로 task 실행 시 사용되는 설정으로 ext(전역변수), repositories(저장소), dependencies(해당 라이브러리) 등을 설정하도록 한다.
- classpath : 빌드에서 실행까지 의존하는 라이브러리를 설정한다.
- plugin : 빌드에서 실행까지 활용할 플러그인을 추가한다.
- dependencies : 
    - compile(3.0 미만), api(3.0 이상) : 모듈 수정시 해당 카테고리에 등록된 모듈은 컴파일(compile) 또는 빌드(build)에 활용하는 라이브러리일 경우 지정
    - implementation : 모듈 수정 시, 해당 모듈을 직접 의존하는 모듈만 빌드해야 하는 라이브러리일 경우 지정
    - testImplementation : 테스트시에만 빌드해야 하는 라이브러리일 경우 지정
    - annotationProcessor : 어노테이션 기반 라이브러리일 경우 지정
    - compileOnly : 컴파일(compile)에만 필요하고, 실행(runtime) 시에는 필요없는 라이브러리일 경우 지정


#### 2) gradle 명령어

- init : 프로젝트를 초기화하면서 생성한다. ( gradle init [--type 타입명] )
- tasks : 현재 진행되고 있는 태스크의 목록을 확인한다. (gradle tasks)
- build : groovy 스크립트나 kotlin 스크립트로 구성한 설정파일의 설정 내용에 따라 빌드를 한다. (gradle build)
- run : 컴파일 후에 메인 클래스를 실행한다. (gradle run 또는 gradle bootRun)
- jar : 완료된 프로젝트를 jar 단위로 패키징한다. (gradle jar)
- clean : 빌드된 결과물이 build 디렉토리에 존재하게 되는데 이를 제거하여 빌드의 이전 상태로 되돌린다. (gradle clean)


## 01-02. Spring boot 3.0.x VS 2.7.x


### 01-02-01. spring security 메소드 변화

- 스프링시큐리티 설정 관리자(spring boot 3.x = spring framework 6.x)

| 항목 | 변경 후 | 변경 후 |
|-----------|---------------------------------|---------------------------------------|
| 반환타입 | SecurityFilterChain | configure |
| 어노테이션 | @Configuration | @Configuration과 @EnableWebSecurity |
| 설정 | 한 곳에서 | configure를 오버로딩하여 각 각 설정 |
| 자원접근 | SecurityFilterChain | WebSecurity 매개변수 |
| 인터셉터 | SecurityFilterChain | HttpSecurity 매개변수 |
| 인증관리 | 별도 AuthenticationProvider 인터페이스 상속 | AuthenticationManagerBuilder 매개변수 |
| 상위클래스 | 없음 | WebSecurityConfigurerAdapter 클래스를 상속받아 설정 |

※ SecurityFilterChain 는 spring security 5.7.x 부터 활용 권장하며, 6.x.x 는 기본 사용임.
※ SecurityFilterChain 는 spring security 5.x.x 는  .antMatchers()로 6.x.x는 .requestMatchers()와 AntPathRequestMatcher 클래스로 퍼미션 설정

<br>

- 스프링시큐리티 관련 메소드

| 메소드 | 설명 |
|---------------------|----------------------------------------------------------------|
| authorizeRequests() | 인증 모듈을 사용합니다. |
| antMatchers() | version 6.x 는 requestMatchers() 이며, 정확한 특정 리소스 URL에 대해서 권한을 설정합니다. |
| mvcMatchers() | 특정 리소스 URL로 시작하는 리소스에 대해서 권한을 설정합니다. |
| anyRequest() | 앞서 부여된 특정 레벨이나 리소스 내용을 접근 할 수 있으며, 그 지정외의 내용은 접근할 수 없습니다. |
| authenticated() | 앞서 부여된 특정 레벨이나 리소스 내용대로 인증을 합니다. |
| disable() | 앞서 설정된 내용을 모두 비활성화합니다. |
| anonymous() | 인증되지 않은 사용자가 접근할 수 있습니다. |
| authenticated() | 인증된 사용자만 접근할 수 있습니다. |
| fullyAuthenticated() | 전체 권한(ADMIN)이 인증된 사용자만 접근할 수 있습니다. |
| hasRole() | 특정 롤 권한이 접근할 수 있습니다.(하나만 지정) |
| hasAnyRole() | 특정 롤 권한이 접근할 수 있습니다.(여러 개 지정) |
| hasAuthority() | 특정 롤 권한이 있는 인증된 사용자만 접근할 수 있습니다. |
| hasAnyAuthority() | 특정 롤 권한이 있는 인증된 사용자만 접근할 수 있습니다. |
| hasIpAddress() | 특정 IP 주소를 가지는 사용자만 접근할 수 있습니다. |
| access() | SpringEL(SpEL) 의 표현식에 만족하는 경우만 접근할 수 있습니다. |
| not() | 접근 제한 기능을 해제합니다. |
| permitAll() | 접근을 전부 허용합니다. |
| denyAll() | 접근을 전부 제한합니다. |
| rememberMe() | 리멤버 기능을 통하여 로그인한 사용자만 접근할 수 있습니다. |
| and() | 하나의 http 객체에 메소드 체이닝 방법으로 특정 레벨, 리소스, 로그인, 로그아웃 등을 연결합니다. |
| csrf() | csrf 의 기능을 사용합니다. |
| cors() | cors 의 기능을 사용합니다. |
| formLogin() | 로그인 모듈을 사용합니다. |
| loginPage() | 로그인할 페이지를 지정합니다. |
| loginProcessingUrl() | 로그인 요청시 인증 처리 주소를 지정합니다. |
| failureUrl() | 로그인 실패시 연결할 주소를 지정합니다. |
| defaultSuccessUrl() | 정상적으로 인증이 성공할 경우 이동하는 주소를 지정합니다. |
| usernameParameter() | 로그인 창에 입력되는 아이디에 해당하는 필드의 이름을 지정합니다. |	
| passwordParameter() | 로그인 창에 입력되는 비밀번호에 해당하는 필드의 이름을 지정합니다. |
| logout() | 로그아웃 모듈을 사용합니다. |
| logoutRequestMatcher() | 로그아웃 처리할 주소를 지정하되 AntPathRequestMatcher 클래스를 사용해야 합니다. |
| logoutSuccessUrl() | 로그아웃 성공시 이동하는 주소를 지정합니다. |
| logoutUrl() | 로그아웃 처리할 주소를 지정합니다. |
| exceptionHandling() | 예외처리할 클래스를 지정합니다. |
| addFilterBefore() | UsernamePasswordAuthenticationFilter 보다 먼저 실행할 커스텀 필터를 추가합니다. |
| addFilterAfter() | UsernamePasswordAuthenticationFilter 보다 나중에 실행할 커스텀 필터를 추가합니다. |
| addFilterAt() | 지정한 순서에 실행할 커스컴 필터를 추가합니다. |
| accessDeniedPage() | 접근을 거부할 페이지를 지정합니다. |

<br>

## 01-03. MyBatis VS Data JPA

## 01-04. application.properties VS application.yml

## 01-05. 암호화 및 보안 - Session / Intercepter / Spring Security / JWT / OAuth

### 01-05-1. 인증(Authentication)과 인가(Authorization)

인증(Authentication) : 토큰과 같은 임의 발생 키를 활용 -> JWT

인가(Authorization) : 타사 계정 로그인과 같이 토큰을 외부에서 제공하여 인증해주는 방법 -> OAuth


## 01-06. 뷰 템플릿(view template) : jsp, thymeleaf, freeMarker, Mustache, Groovy

## 01-07. Spring Boot Annotation

### 01-07-01. Spring boot Annotation 정리

| 어노테이션 | 설명 |
|---------------------|---------------------------------------------------------|
| @Component | 개발자가 직접 작성한 Class를 Bean으로 등록하기 위한 어노테이션 |
| @ComponentScan | @Component, @Service, @Repository, @Controller, @Configuration이<br> 붙은 클래스들을 찾아서 빈(bean)등록을 해주는 어노테이션 |
| @Bean | 개발자가 직접 제어가 불가능한 외부 라이브러리등을 Bean으로 등록하기 위한 어노테이션 |
| @Autowired | Spring 에서 제공하는 주입 방식으로 Type에 따라 자동으로 의존성을 주입하는 어노테이션 |
| @Inject | Java 에서 제공하는 주입 방식으로 수동으로 의존성을 주입하는 어노테이션 |
| @Controller | Controller에 해당하는 클래스에 선언하는 어노테이션 |
| @RestController | Controller의 기능을 하지만, 뷰(View)로 <br>포워드(Forward) 하지 않고 JSON으로 응답하기 위한 어노테이션 |
| @Service | Service Logic에 해당하는 클래스에 선언하는 어노테이션 |
| @Repository | DAO(Data Access Object)에 해당하는 클래스에 선언하는 어노테이션 |
| @EnableAutoConfiguration | Spring Application Context가 생성될 때 자동으로 설정해주는 어노테이션 |
| @Configuration | 웹 설정, 권한 설정, 서블릿 설정과 여러 의존성 라이브러리에 대한 설정하려고 할 때 활용하는 어노테이션 |
| @Required | 빈(Bean) 생성 시에 필수 속성(Property) 임을 지정하는 어노테이션 |
| @Qualifier | @Autowired와 같이 사용하며, 타입에 따라 해당 타입을 갖는 빈(Bean)을 선택적으로 주입하는 어노테이션 |
| @Resource | Name에 따라 자동으로 의존성을 주입하는 어노테이션 |
| @PostConstruct | 해당 객체를 생성 한 후에 진행해야 하는 일을 지정하는 어노테이션 |
| @PreConstruct | 해당 객체를 생성하기 전에 진행해야 하는 일을 지정하는 어노테이션 |
| @PreDestroy | 해당 객체를 제거 또는 소멸 전에 진행해야 하는 일을 지정하는 어노테이션 |
| @PropertySource | 환경 설정된 .properties 파일을 지정하는 어노테이션 |
| @ConfigurationProperties | 환경 설정 파일인 .yml, .properties 파일이나 접두사 등을 지정하는 어노테이션 |
| @Lazy | 해당 빈(Bean)을 등록할 때 생성하여 로딩하는 것이 아니라<br> 실제로 사용될 때 로딩이 이루어지게 하는 어노테이션 |
| @Value | .properties 파일에서 설정한 값을 로딩하려고 할 경우 사용하는 어노테이션 |
| @SpringBootApplication | @Configuration, @EnableAutoConfiguration, <br> @ComponentScan 어노테이션의 기능을 모두 하는 어노테이션 |
| @RequestMapping | 클라이언트의 요청 URL을 어떤 메소드(method)가 처리할지 매핑(mapping) 해주는 어노테이션 |
| @CookieValue | 쿠키(Cookie) 값을 Parameter로 전달 받을 수 있는 어노테이션 |
| @CrossOrigin | CORS 보안상의 문제를 설정하도록 하는 어노테이션 |
| @ModelAttribute | 뷰(View) 에서 전달한 파라미터(Parameter)를<br> 전달객체(VO/DTO)의 멤버로 바인딩(Binding) 해주는 어노테이션 |
| @GetMapping | 클라이언트의 GET 방식 요청을 처리할 메소드를 지정하는 어노테이션 |
| @PostMapping | 클라이언트의 POST 방식 요청을 처리할 메소드를 지정하는 어노테이션 |
| @PutMapping | 클라이언트의 Put 방식 요청을 처리할 메소드를 지정하는 어노테이션 |
| @PatchMapping | 클라이언트의 Patch 방식 요청을 처리할 메소드를 지정하는 어노테이션 |
| @DeleteMapping | 클라이언트의 Delete 방식 요청을 처리할 메소드를 지정하는 어노테이션 |
| @SessionAttributes | 세션(Session)에 특정 키나 속성에 대한 값을 저장하는 어노테이션 |
| @Valid | 유효성 검증이 필요한 객체에 지정하는 어노테이션 |
| @InitBinder | 파라미터(Parameter)로 @Valid가 지정된 메소드 위에 선언하며,<br> 객체의 값을 가져오기 전에 유효성 검증을 하는 어노테이션 |
| @RequestAttribute | 클라이언트의 요청으로 들어온 속성 값을 Request 방식으로 가져올 경우 사용하는 어노테이션 |
| @RequestBody | 클라이언트로 요청된 데이터를 JSON 방식으로 가져올 경우 사용하는 어노테이션 |
| @RequestHeader | 클라이언트로 요청된 헤더 정보를 가져올 경우 사용하는 어노테이션  |
| @RequestParam | 클라이언트의 요청 데이터를 하나의 파라미터로만 연결하여 가져오는 어노테이션 |
| @RequestPart | 클라이언트의 멀티파트 요청을 멀티파트 변수로 바인딩하는 어노테이션 |
| @ResponseBody | 클라이언트에게 응답을 뷰(View)가 아닌 JSON의 형태로 응답하는 어노테이션 |
| @PathVariable | 클라이언트의 요청 데이터를 파라미터(Parameter)가 아닌<br> 경로(Path)와 같은 요청 URL로 받는 경우에 사용하는 어노테이션 |
| @ExceptionHandler | 예외 처리할 클래스를 지정할 때 사용하는 어노테이션 |
| @ControllerAdvice | 예외 처리해야 하는 Controller의 상단에 지정하는 어노테이션 |
| @ResponseStatus | 응답 상태 또는 코드를 반환하는 어노테이션 |
| @Transactional | 여러 메소드를 동시에 처리해야 하는 트랜잭션을 설정해야 하는 경우에 지정하는 어노테이션 |
| @EnableWebMvc | Spring Web MVC 패턴 구조를 구성할 경우 필요한 빈(Bean)을 자동으로 설정하는 어노테이션 |
| @Scheduled | 정해진 시간에 지정한 메소드를 실행하게 하는 어노테이션 |
| @Slf4j | Logger 객체 생성 없이 로그(Log)를 출력하는 어노테이션 |
| @Log4j2 | Logger 객체 생성 없이 로그(Log)를 출력하는 어노테이션 |

### 01-07-02. Lombok 관련 어노테이션

| 어노테이션 | 설명 |
|---------------------|---------------------------------------------------------|
| @NoArgsConstructor | 기본생성자를 자동으로 추가하는 Lombok 어노테이션 |
| @AllArgsConstructor | 모든 필드 값을 파라미터로 받는 생성자를 추가하는 Lombok 어노테이션 |
| @RequiredArgsConstructor | final이나 @NonNull인 필드 값만 파라미터로 받는 생성자를 추가하는 Lombok 어노테이션 |
| @Getter | 모든 필드의 Getter 메소드를 생성하는 Lombok 어노테이션 |
| @Setter | 모든 필드의 Setter 메소드를 생성하는 Lombok 어노테이션 |
| @ToString | 모든 필드의 정보를 출력하는 toString 메소드를 생성하는 Lombok 어노테이션 |
| @EqualsAndHashCode | equals와 hashCode 메소드를 오버라이딩하는 Lombok 어노테이션 |
| @Builder | 어느 필드에 어떤 값을 채워야 할지 명확하게 정하여 생성 시점에 값을 채워주는 Lombok 어노테이션 |
| @Data | @Getter @Setter @EqualsAndHashCode @AllArgsConstructor을 포함하는 Lombok 어노테이션 |


### 01-07-03. Swagger 관련 어노테이션

| 어노테이션 | 설명 |
|---------------------|---------------------------------------------------------|
| @Tag | API의 그룹을 설정하기 위한 Swagger 어노테이션 |
| @Operation | API 상세정보를 설정하기 위한 Swagger 어노테이션 |
| @Parameter | API에서 전달받는 Parameter에 대한 정보를 설정하기 위한 Swagger 어노테이션 |
| @ApiResponse | API에서 응답하는 Response에 대한 정보를 설정하기 위한 Swagger 어노테이션 |
| @Schema | Spring Swagger 3.0 이후의 DTO 클래스 내 멤버변수에 선언하는 Swagger 어노테이션
| @ApiModelProperty | Spring Swagger 3.0 전의 DTO 클래스 내 멤버변수에 선언하는 Swagger 어노테이션 |


### 01-07-04. Validation 관련 어노테이션

| 어노테이션 | 설명 |
|---------------------|---------------------------------------------------------|
| @NotBlank | null이 아니고 최소한 한 개 이상의 공백아닌 문자를 포함하는지 검사하는 검증 어노테이션 |
| @Null | 값이 null인지 검사하는 검증 어노테이션 |
| @NotNull | null이 아닌지 검사하는 검증 어노테이션 |
| @NotEmpty | 문자열나 배열의 경우 null이 아니고 길이가 0이 아닌지 검사하는 검증 어노테이션 |
| @AssertTrue | 값이 true인지 검사하는 검증 어노테이션 |
| @AssertFalse | 값이 false인지 검사하는 검증 어노테이션 |
| @Pattern | 값이 정규표현식에 일치하는지 검사하는 검증 어노테이션 |
| @Email | 이메일 주소가 유효한지 검사하는 검증 어노테이션 |
| @Max | 지정한 값보다 큰지 검사하는 검증 어노테이션 |
| @Min | 지정한 값보다 작은지 검사하는 검증 어노테이션 |
| @Digits | 자릿수가 지정한 크기를 넘지 않는지 검사하는 검증 어노테이션 |
| @Positive | 양수인지 검사하는 검증 어노테이션 |
| @PositiveOrZero | 양수 또는 0인지 검사하는 검증 어노테이션 |
| @Negative | 음수인지 검사하는 검증 어노테이션 |
| @NegativeOrZero | 음수 또는 0인지 검사하는 검증 어노테이션 |
| @Future | 지정한 시간보다 미래인지 검사하는 검증 어노테이션 |
| @FutureOrPresent | 지정한 시간보다 현재 또는 미래인지 검사하는 검증 어노테이션 |
| @Past | 지정한 시간보다 과거인지 검사하는 검증 어노테이션 |
| @PastOrPresent | 지정한 시간보다 현재 또는 과거인지 검사하는 검증 어노테이션 |


### 01-07-05. Entity 관련 어노테이션

| 어노테이션 | 설명 |
|---------------------|---------------------------------------------------------|
| @Entity | 데이터베이스의 테이블과 연결되어 매칭되는 클래스임을 명시하는 어노테이션 |
| @Table | Entity 에 해당하는 클래스에 해당 테이블 이름을 지정하는 어노테이션 |
| @Id | 해당 필드(속성)이 기본키로 지정하는 어노테이션 |
| @GeneratedValue | 해당 필드(속성)은 자동순번이 부여되도록 하는 어노테이션 |
| @Column | 해당 필드(속성)을 테이블의 특정 컬럼으로 지정하는 어노테이션  |
| @CreatedDate | 해당 필드(속성)의 값은 튜플 생성시의 날짜로 값이 자동으로 부여되는 어노테이션 |
| @LastModifiedDate | 해당 필드(속성)의 값은 튜플 수정시에 날짜로 값이 자동으로 변경되는 어노테이션 |
| @OneToOne | 현재 Entity와 다른 Entity를 1:1로 연관 관계를 지정하는 어노테이션 |
| @ManyToOne | 현재 Entity의 여러 튜플이 다른 Entity의 하나의 튜플과 연관성을 갖는 n:1로 연관 관계를 지정하는 어노테이션 |
| @OneToMany | 현재 Entity의 하나의 튜플이 다른 Entity의 여러 튜플과 연관성을 갖는 1:n로 연관 관계를 지정하는 어노테이션 |
| @JoinColumn | 참조하고자 하는 Entity의 기본키에 해당하는 외래키를 지정하는 어노테이션 |
| @Inheritance | 상속 관계 등을 JOINED, SINGLE_TABLE, TABLE_PER_CLASS 등으로 매핑을 명시하는 어노테이션 |
| @DiscriminatorColumn | 상속 매핑 받는 테이블의 구분할 구분자 컬럼을 지정하는 어노테이션 |
| @DiscriminatorValue | 상속 받는 매핑 테이블의 이름을 명시하는 어노테이션 |
| @TableGenerator | 지정한 옵션으로 해당 테이블을 생성하도록 하는 어노테이션 |


### 01-07-06. AOP 관련 어노테이션

| 어노테이션 | 설명 |
|---------------------|---------------------------------------------------------|
| @Aspect | AOP를 정의하는 클래스에 할당하는 어노테이션 |
| @Before | 해당 메소드를 실행하기 이전에 실행할 함수를 지정하는 어노테이션 |
| @After | 해당 메소드를 실행한 후에 실행할 함수를 지정하는 어노테이션 |
| @Around | 예외와 관계없이 메소드 실행하기 이전과 이후에 실행할 함수를 지정하는 어노테이션 |
| @Pointcut | AOP를 적용시킬 지점을 설정해주는 함수를 설정하는 어노테이션 |
| @AfterReturning | 메소드 호출 성공 시에 실행할 함수를 지정하는 어노테이션 |
| @AfterThrowing | 메소드 호출 실패 시에 실행할 함수를 지정하는 어노테이션 |



<br><hr><br>

<div id="02"></div>

# 02. Spring Boot Maven + WAR + JSP + MariaDB + Mybatis + Lombok - test02

## 02-01. application.yml

## 

<br><hr><br>

<div id="03"></div>

# 03. Spring Boot Gradle + WAR + JSP/JSTL + MariaDB + Mybatis + Lombok + Session - test03

## 03-01. DI/IoC

## 03-02. Table, mapper.xml, Mapper, Service, Controller, View(JSP)

## 03-03. Session

<br><hr><br>

<div id="04"></div>

# 04. Spring Boot Gradle + WAR + JSP/JSTL + MariaDB + Mybatis + Lombok - test04

## 04-01. Restful API

## 04-02. LOGBACKUP

## 04-03. Exception

<br><hr><br>

<div id="05"></div>

# 05. Spring Boot 3.0.12 Gradle + WAR + JSP/JSTL + MariaDB + Mybatis + Lombok + Spring Security 6.0.8 - test05

## 

<br><hr><br>

<div id="06"></div>

# 06. Spring Boot 2.7.17 Gradle + WAR + JSP/JSTL + MariaDB + Mybatis + Lombok + Spring Security 5.7.11 - test06

## 06-01.

<br><hr><br>

<div id="07"></div>

# 07. Spring Boot 2.7.17 Gradle + WAR + Thymeleaf + MariaDB + Mybatis + Lombok + Spring Security 5.7.11 - test07

## 07-01. 

<br><hr><br>

<div id="08"></div>

# 08. Spring Boot 2.7.17 Maven + WAR + FreeMarker + MariaDB + JPA + Lombok - test08

<br><hr><br>

<div id="09"></div>

# 09. Spring Boot 2.7.17 Maven + WAR + Mustache + MariaDB + JPA + Lombok + webjars - test09

## 09-01. 

<br><hr><br>

<div id="10"></div>

# 10. Spring Boot 2.7.17 Gradle + WAR + Thymeleaf + MariaDB + JPA + Lombok + Spring Security 5.7.11 - test10

<br>

## 10-01. Spring Boot 2.7.17 Gradle + WAR + Thymeleaf + MariaDB + JPA + Board

<br>

## 10-02. Spring Boot 2.7.17 Gradle + WAR + Thymeleaf + MariaDB + JPA + RestfulAPI(Swagger) + Entity Relation Mapping + Axios Reply Board

<br>

## 10-03. Spring Boot 2.7.17 Gradle + WAR + Thymeleaf + MariaDB + JPA + FileUpload Reply Board

<br>

## 10-04. Spring Boot 2.7.17 Gradle + WAR + Thymeleaf + MariaDB + JPA + JPQL/QueryDSL + Spring Security

<br>

## 10-05. Spring Boot 2.7.17 Gradle + WAR + Thymeleaf + MariaDB  + JPA + Product/Item + Admin

<br>

## 10-06. Spring Boot 2.7.17 Gradle + WAR + Thymeleaf + MariaDB + JPA + WebSocket + Chatting Room


<br><hr><br>

<div id="11"></div>

# 11. 

##

<div id="12"></div>

# 12. 

##

<br><hr><br>