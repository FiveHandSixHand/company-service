📦 Daitda Product Service
다잇다 물류 시스템의 핵심 상품 도메인을 관리하는 마이크로서비스입니다.
단순한 CRUD를 넘어, 서비스 간 독립성을 보장하는 ID 기반 참조 설계를 지향합니다.

🛠️ Tech Stack
Framework: Spring Boot
Database: PostgreSQL
Communication: Spring Cloud FeignClient (Internal API 통신)
Library: QueryDSL, Lombok, Validation
🏗️ Architecture: Aggregate Design
본 서비스는 유지보수성과 장애 격리를 위해 업체(Company)와 상품(Product) 애그리거트를 물리적으로 분리하여 설계되었습니다.

1. 느슨한 결합 (Loose Coupling)
Product 엔티티는 Company 객체를 직접 참조하지 않고, **companyId (UUID)**만을 가집니다.
JPA 연관관계 매핑(@ManyToOne)을 제거하여 DB 수준의 의존성을 완전히 해제했습니다.
2. FeignClient를 통한 데이터 통합
상품 상세 정보 조회 시, 부족한 업체 데이터는 company-service의 Internal API를 통해 실시간으로 가져옵니다.
🚀 Key Features
✅ 상품 관리 (CRUD): 상품 등록, 수정, 삭제 및 상태 관리.
✅ 인터널 통신 (Feign): CompanyClient를 통한 안전한 내부 데이터 조회.
✅ 조건별 검색 (QueryDSL):다양한 필터 조건에 따른 동적 쿼리 지원.
⚙️ DB Schema Highlights
Table: p_company
Core Columns: id(UUID), region_id, name, business_number, address, is_active 등
Audit Fields: 모든 테이블에 created_at, created_by, updated_at, updated_by 필드 포함
🔗 API Endpoints (Main)
Method	Path	Description
POST	/api/v1/products/{productId}	상품 수정 (업체 정보 포함)
GET	/api/v1/products/{productId}	상품 상세 조회 (업체 정보 포함)
GET	/api/v1/products/{productId}	상품 수정 (업체 정보 포함)
GET	/api/v1/products/{productId}	상품 삭제업체 (정보 포함)
GET	/api/v1/products/{productId}	상품 리스트 조회
