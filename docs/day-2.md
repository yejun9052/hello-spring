# Spring Boot 기초 강의 2일차

## 테스트
- 테스트 코드는 직관적으로 한국어로 표현해도 상관없음
  - 단 영어권 사람들과 일하는 경우는 당연하게도 제외
- 테스트 시에는 항상 1개의 메서드를 실행하고 나서는 초기화를 해줘야함 (자세한건 MemoryMemberRepository와 MemoryMemberRepository)
  - 위 사항을 참고 안할 시 값이 초기화되지 않아 테스트 실패 가능성이 커짐
- 테스트를 사용하는 이유는 한 기능을 만들고 수행하려면 서버를 키고 api를 호출한다거나 여러 과정이 필요하지만 서비스로 예시를 들면 한 메시드에 값만 넣고 테스트를 만들어 실행시키면 바로 실행이 되니까, 기능을 구현했을때 추가했을 때 귀찮다는 이유로 테스트를 건너뛸 수 없게 만든다.
  - 사실 위 사항으로는 테스트를 만들어야하는 이유를 모르겠어서 나중에 다시 공부가 필요한 부분이다.


## 어노테이션
  - @BeforeEach
    - 테스트 실행 전 수행하는 메서드 지정
    - 대표적으로 테스트 수행 전 값 동기화에 사용
    - JPA 사용시 별도에 선언 없이 @Autowired로 선언만 하면 됨
  - @AfterEach
    - 테스트 수행 수 수행하는 메서드 지정
    - 대표적으로 테스트 수행 후 값 초기화 시 사용
      - @Autowired
        - 한마디로 필요한 걸 알아서 갖다줘 라고 요청하는 주문서 개념이다.
        - 이미 완성된 걸 받기만 하기 위해서 원래라면 아래를 참고
          ``` 
          @Controller
          public class MemberController {
            private final MemberSerivce memberService;
        
            public MemberController() {
              MemberRepository repository = new MemoryMemberRepository();
              this.memberSerivce = new MemberService(repository);
            }
          }
        - 위와 같이 해야 하지만
          ```
          Controller
          public class MemberController {
    
              private final MemberService memberService;
    
              @Autowired
              public MemberController(MemberService memberService) {
                  this.memberService = memberService;   // 이미 완성된 걸 받기만 함
              }
          }
        - 위 처럼 애노테이션을 쓴다면 간결하게 가능하다.(물런 당연하게도 아래 나오는 내용인 스프링 빈 등록이 필요하다.)


## 컴포넌트 스캔과 자동 의존관계 설정
  - @Repository, @Service, @Controller 어노테이션을 까보면 다 @Component가 들어가 있음
  - @SpringBootApplication 어노테이션, Application 클래스에 들어가있는 클래스에 달려있는 어노테이션이다.
    - 위 어노테이션은 스프링을 시작할때 컴포넌트 즉 Repository, Service, Controller 같은 걸 자동으로 찾아준다.
    - 위 설명을 좀 더 직관적으로 알고싶다면 cmd + 클릭으로 어노테이션을 까보면 컴포넌트스켄이라는 어노테이션이 들어가 있다. 
  - [@Controller 참고 예제](../src/main/java/hello/hello_spring/controller/HelloController.java)
  - [@SpringBootApplication 참고 예제](../src/main/java/hello/hello_spring/HelloSpringApplication.java)

## 직접 자바 코드로 스프링 빈 설정하기
  - 위 주제에서 자동 의존관계 설정을 다뤘는데 여기서는 스프링 빈을 자바 코드로 직접 등록하는 방법을 보고있다.
  - [자세한 코드는 여기를 참고](../src/main/java/hello/hello_spring/SpringConfig.java)
  - 위 경우 필드 주입, setter주입, 생성자 주입이 있는데 현 코드에서 사용한게 생성자 주입이다. (거의 생성자 주입만 사용)
    - 필드 주입의 경우 잘 모르겠지만 setter 주입의 경우 set 메서드로 다른곳에서 호출이 가능하기 때문에 (아무나 호출할 수 있어지기 떄문에) 안좋은 방식이다.

## 2일차 배운점
  - 애노테이션은 새로운 문법이 아닌 코드를 대신 써주는 장치에 불구하다.
    - 예를 들어 [MemberService](../src/main/java/hello/hello_spring/service/MemberService.java) 만든 생성자도 코드 주석을 보면 자세히 알 수 있다.
  - @Autowired 또한 생성자가 1개뿐이면 생략이 가능하다. (스프링 4.3부터 생성자가 하나뿐이라면 @Autowired를 생햑해도 자동으로 스프링이 자동으로 주입해준다.)
  - 2일차에 배운 자동설정이나, 직접 설정 같은 경우 어느 경우도 설정을 안하면 Service나 Controller같은 클래스를 인식하지 못한다. 서비스나 컨트롤러가 스프링에 등록이 되고 스프링이 관리를 해야지 @AutoWired같은 애노테이션도 먹히는 것이다.

