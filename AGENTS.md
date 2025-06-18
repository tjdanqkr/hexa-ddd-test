# AGENTS.md – Hexagonal Architecture + DDD 기반 Spring Boot Order 예제 프로젝트

본 문서는 본 프로젝트의 에이전트 워크플로우, 도메인 계층 구조, 기술 스택, 구성 전략을 설명합니다.  
아키텍처는 **DDD 원칙**과 **헥사고날 아키텍처 (Hexagonal Architecture)** 를 기반으로 설계되었습니다.

---

## 🏗 프로젝트 구조

```
src/.../domainname
├── domain/                         # 순수 비즈니스 로직 계층 (핵심 모델)
│   ├── model/                     # VO, Entity 등 순수 도메인 객체
│   ├── service/                   # 도메인 서비스 (도메인 로직만 보유)
│   └── repository/                # Repository 인터페이스 (Port)

├── application/                   # 유스케이스 / 서비스 계층
│   ├── service/                   # 유스케이스 서비스 (비즈니스 로직 orchestrator)
│   └── port/                      # 내부 Port (inbound), 외부 Port (outbound)

├── infrastructure/                # 어댑터 구현 (DB, 외부 API, 메시징 등)
│   ├── config/                    # Bean 설정, 환경 구성
│   ├── adapter/                  
│   │   ├── persistence/           # JPA, Redis 등 DB 연동 구현체 (Repository adapter)
│   │   ├── ai/                    # OpenAI, Azure AI, LLM 클라이언트
│   │   └── search/                # RAG용 Vector Search (Postgres/pgvector 등)

├── interfaces/                    # API 계층 (Controller, Web Adapter)
│   ├── rest/                      # REST API 엔드포인트
│   └── dto/                       # Request / Response DTO

├── test/                          # 단위 및 통합 테스트
│   ├── domain/
│   ├── application/
│   └── interfaces/
```

---

## ⚙ 기술 스택 및 라이브러리

- **Spring Boot 3.2+**
- **Java 17+**
- **Gradle (Groovy DSL, Java 기반 프로젝트)**
- **Spring Data JPA + QueryDSL**
- **PostgreSQL**
- **H2 DB (개발용)**
- **Spring Validation**
- **Lombok**
- **Spring Web MVC**

---

## 📦 핵심 의존성 (`build.gradle`)

```groovy
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-validation'
    implementation 'org.springframework.boot:spring-boot-starter-web'

    compileOnly 'org.projectlombok:lombok'
    annotationProcessor 'org.projectlombok:lombok'

    implementation 'com.querydsl:querydsl-jpa'
    annotationProcessor "com.querydsl:querydsl-apt"
    annotationProcessor "jakarta.annotation:jakarta.annotation-api"
    annotationProcessor "jakarta.persistence:jakarta.persistence-api"
    
    runtimeOnly 'org.postgresql:postgresql'
    
    testRuntimeOnly 'com.h2database:h2'

    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
}
```

> ✅ 추가 고려 의존성 (선택)
> - `spring-boot-starter-actuator` – 운영 상태 확인용
> - `spring-boot-devtools` – 핫리로드 지원
> - `mapstruct` – DTO ↔ Entity 매핑 자동화

---

## 🧠 에이전트 워크플로우

### 🧩 ChainWorkflow
- 순차적으로 여러 LLM Task 실행 (e.g. 요약 → 번역 → 정제)

### ⚡ ParallelWorkflow
- 병렬로 실행 후 결과 합산 (다수 기사 요약 시 유용)

### 🧭 RoutingWorkflow
- 분기 처리 (질문 분류 후 분기 응답)

### 📚 RAG (Retrieval-Augmented Generation)
- 검색 + 생성 조합 (문맥 기반 응답 생성)

---

## 🧪 테스트 전략

| 계층 | 테스트 대상 | 전략 |
|------|-------------|------|
| Domain | 도메인 서비스, VO | 순수 단위 테스트 |
| Application | 유스케이스, Port 호출 | Mock 기반 테스트 |
| Infrastructure | 어댑터 구현 | 통합 테스트 |
| Interface | Controller, DTO | @WebMvcTest, e2e |

---

## 📈 실행 설정

```yaml
spring:
  datasource:
    url: jdbc:h2:mem:testdb
    driver-class-name: org.h2.Driver
    username: sa
    password:
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

---

## 📝 변경 로그

| 날짜 | 버전 | 설명 |
|------|------|------|
| 2025-06-18 | v1.0 | 초기작성 – DDD + Hexagonal 설계 기반 |