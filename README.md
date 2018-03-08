## Spring Security 학습

baeldung 사이트의 [learn-spring-security](https://github.com/eugenp/learn-spring-security) 소스 코드를 참고하여 step by step으로 학습.

### Step7. Spring Security Configuration
- step7-1 : h2 기반 프로젝트 셋팅 / h2-console
- step7-2 : [Run-As 인증](http://www.baeldung.com/spring-security-run-as-auth)
    - 사용자는 일부 다른 로직을 다른 권한을 가진 다른 주체로 실행할 수 있다.
    1. GlobalMethodSecurity를 설정하고 RunAsManager를 주입해야한다.
    2. 임시 인증 객체에 여분의 권한을 제공해야 한다.
    3. 임시 Authentication 객체는 tryRunAS() 메서드 호출 기간동안 만 기존 Authentication 객체를 대체한다.
    [참조](http://thatjavathing.blogspot.kr/2014/07/spring-security-run-as-example-using.html)
