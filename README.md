# 🔐 Auth Service

> **인증 서비스** - JWT 기반 사용자 인증 및 SMS 인증 서비스

## 📋 목차

- [프로젝트 개요](#-프로젝트-개요)
- [기술 스택](#-기술-스택)
- [주요 기능](#-주요-기능)
- [환경 설정](#-환경-설정)
- [실행 방법](#-실행-방법)
- [API 문서](#-api-문서)
- [팀원 정보](#-팀원-정보)

## 🎯 프로젝트 개요

Auth Service는 **JWT 기반 인증 시스템**과 **SMS 인증**을 제공하는 **인증 서비스**입니다.

### 🌟 주요 특징

- **JWT 인증**: 무상태 토큰 기반 인증 시스템
- **SMS 인증**: CoolSMS API를 통한 휴대폰 번호 인증
- **이메일 인증**: 이메일 기반 회원가입 및 로그인
- **비밀번호 관리**: 안전한 비밀번호 암호화 및 관리
- **토큰 관리**: Access Token과 Refresh Token 관리

### 🏆 핵심 역할

- **사용자 인증**: 로그인/로그아웃 처리
- **회원 관리**: 회원가입, 정보 수정, 탈퇴
- **토큰 발급**: JWT 토큰 생성 및 검증
- **SMS 인증**: 휴대폰 번호 인증 코드 발송 및 검증

## 🛠️ 기술 스택

### Backend
- **Java 17** - OpenJDK LTS 버전
- **Spring Boot 3.5.0** - 최신 Spring Boot 프레임워크
- **Spring Security 6.0** - 보안 프레임워크
- **Spring Data JPA** - ORM 및 데이터 접근 계층
- **QueryDSL** - 타입 안전한 쿼리 작성

### Database
- **MySQL 8.0** - 메인 데이터베이스
- **HikariCP** - 커넥션 풀 관리

### Authentication & Security
- **JWT (JSON Web Token)** - 토큰 기반 인증
- **BCrypt** - 비밀번호 해싱
- **CoolSMS API** - SMS 인증

### Service Discovery & Documentation
- **Netflix Eureka Client** - 서비스 디스커버리
- **SpringDoc OpenAPI 3** - API 문서화
- **Swagger UI** - API 테스트 인터페이스

### Infrastructure
- **Docker** - 컨테이너화
- **AWS EC2** - 클라우드 배포

## 🚀 주요 기능

### 1. 회원가입
- **이메일 중복 검사**: 중복 이메일 방지
- **비밀번호 암호화**: BCrypt를 통한 안전한 해싱
- **사용자 등록**: 새 사용자 계정 생성

### 2. 로그인 및 JWT 토큰 발급
- **사용자 인증**: 이메일과 비밀번호 검증
- **JWT 토큰 생성**: Access Token과 Refresh Token 발급
- **토큰 응답**: 클라이언트에 토큰 정보 반환

### 3. SMS 인증
- **인증 코드 생성**: 6자리 무작위 숫자 생성
- **SMS 발송**: CoolSMS API를 통한 문자 전송
- **인증 코드 저장**: 5분 유효시간으로 저장
- **코드 검증**: 입력된 코드와 저장된 코드 비교

### 4. 토큰 검증
- **Bearer 토큰 추출**: Authorization 헤더에서 토큰 추출
- **토큰 검증**: JWT 서명 및 만료시간 확인
- **사용자 정보 반환**: 토큰에서 사용자 정보 추출

## ⚙️ 환경 설정

### 필수 요구사항
- Java 17 이상
- MySQL 8.0 이상
- CoolSMS API 키
- Docker (선택사항)

### 환경 변수
- **DATABASE_URL**: MySQL 데이터베이스 연결 정보
- **JWT_SECRET**: JWT 토큰 서명 키
- **COOLSMS_API_KEY**: CoolSMS API 키
- **COOLSMS_API_SECRET**: CoolSMS API 시크릿

## 🚀 실행 방법

### 1. 저장소 클론
프로젝트 저장소를 로컬에 클론합니다.

### 2. 데이터베이스 설정
MySQL 데이터베이스를 생성하고 사용자를 설정합니다.

### 3. 환경 변수 설정
필요한 환경 변수를 설정합니다.

### 4. 애플리케이션 실행
Gradle을 통해 애플리케이션을 실행합니다.

### 5. 접속 확인
- API 엔드포인트: http://localhost:8081
- Swagger UI: http://localhost:8081/swagger-ui/index.html

## 📚 API 문서

### Swagger UI 접속
API 문서는 Swagger UI를 통해 확인할 수 있습니다.

### 주요 API 엔드포인트

#### 인증 관련
- **회원가입**: 새 사용자 계정 생성
- **로그인**: 사용자 인증 및 토큰 발급
- **로그아웃**: 사용자 로그아웃 처리
- **토큰 검증**: JWT 토큰 유효성 검증
- **토큰 갱신**: Refresh Token을 통한 토큰 갱신

#### SMS 인증
- **SMS 인증 코드 발송**: 휴대폰 번호로 인증 코드 전송
- **SMS 인증 코드 검증**: 입력된 코드와 발송된 코드 비교

#### 비밀번호 관리
- **비밀번호 찾기**: 이메일을 통한 비밀번호 찾기
- **비밀번호 재설정**: 새 비밀번호로 변경
- **비밀번호 변경**: 기존 비밀번호 확인 후 변경

#### 사용자 정보
- **사용자 프로필 조회**: 현재 사용자 정보 조회
- **사용자 프로필 수정**: 사용자 정보 업데이트
- **회원 탈퇴**: 사용자 계정 삭제

## 👥 팀원 정보

**Piece of Cake 팀**
- **팀장**: 이수진 (Backend & Leader)
- **팀원**: 정동섭 (Backend), 이영인 (Backend), 오은서 (Backend), 정진우 (Frontend)

**Auth Service 담당**
- **정동섭**: 계정 관리, 인증 시스템, SMS 인증

---

## 📞 연락처

- **프로젝트 홈페이지**: https://mobile.pieceofcake.site/
- **개발 기간**: 2025년 4월 30일 ~ 7월 10일

---

*"Investment is Easy and Fun" - Piece of Cake 프로젝트* 
