# 🏢 Daitda Company Service

> **다잇다 물류 시스템**의 파트너(업체) 도메인을 관리하는 마이크로서비스입니다.  
> 전사 서비스에 신뢰할 수 있는 업체 마스터 데이터를 제공하며, MSA 환경에서의 **데이터 무결성**을 책임집니다.

---

## 🛠️ Tech Stack
* **Framework:** Spring Boot 
* **Database:** PostgreSQL
* **Security:** Spring Security (Role-based Access Control)
* **Communication:** REST API (External / Internal Feign)
* **Library:** Lombok, Jakarta Validation, Spring Data JPA

---

## 🏗️ Architecture: Provider Role
`company-service`는 시스템 내에서 **엔티티 공급자(Entity Provider)** 역할을 수행하며, 타 서비스와의 느슨한 결합을 지향합니다.

### 1. 전사 공통 식별자 (UUID)
* 비즈니스 로직의 유연성과 보안을 위해 모든 업체 식별자로 **`UUID`**를 사용합니다.
* 이는 타 서비스(Product, Order 등)에서 객체 직접 참조 대신 **ID 기반 참조(Indirect Reference)**를 수행할 때 데이터 고유성을 보장합니다.

### 2. 내부 전용 통신 창구 (Internal API) 🚀
* 서비스 간 통신 보안을 위해 외부 사용자에게 노출되지 않는 별도의 **`/api/v1/internal/**`** 경로를 운영합니다.
* `product-service` 등에서 상품 정보를 완성하기 위해 필요한 업체 정보를 **FeignClient** 요청에 따라 안전하게 제공합니다.

---

## 🚀 Key Features

* ✅ **업체 마스터 관리**: 업체 등록, 정보 수정 및 휴/폐업 상태(is_active) 관리.
* ✅ **권한 제어**: Master/Manager 권한에 따른 차등화된 업체 관리 및 접근 제어.
* ✅ **데이터 감사(Auditing)**: `BaseEntity` 상속을 통해 생성/수정 주체와 시간을 자동 기록하여 데이터 추적성 확보.
* ✅ **지역별 물류 거점 연동**: `p_region` 정보를 기반으로 업체의 담당 물류 허브 위치 관리.

---

## 🔗 API Endpoints

### 1. Public API (사용자/관리자용)
| Method | Path | Description |
| :--- | :--- | :--- |
| `POST` | `/api/v1/companies` | 신규 업체 등록 (Admin 권한) |
| `GET` | `/api/v1/companies` | 업체 목록 조회 (필터 및 페이징) |
| `GET` | `/api/v1/companies/{companyId}` | 특정 업체 상세 정보 조회 |
| `PUT` | `/api/v1/companies/{companyId}` | 업체 정보 수정 |

### 2. Internal API (서비스 간 통신용 - Feign 전용)
| Method | Path | Description |
| :--- | :--- | :--- |
| **`GET`** | `/api/v1/internal/companies/{companyId}` | 단건 조회 (ID 검증 및 데이터 반환) |
| **`POST`** | `/api/v1/internal/companies/names-by-ids` | 리스트 조회 (여러 ID에 대한 업체명 일괄 반환) |

---

## ⚙️ DB Schema Highlights
* **Table**: `p_company`
* **Core Columns**: `id(UUID)`, `region_id`, `name`, `business_number`, `address`, `is_active` 등
* **Audit Fields**: 모든 테이블에 `created_at`, `created_by`, `updated_at`, `updated_by` 필드 포함

---

## 🚦 Getting Started
1. `infrastructure` 서비스(Eureka, Gateway) 실행 확인.
2. `application.yml` 내 PostgreSQL 연결 정보 설정.
3.  `게이트 웨이 8080 ` (Default)를 통해 서비스 기동.