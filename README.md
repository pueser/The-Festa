# The Festa
- 팬데믹 종식 후 축제를 즐기고 싶어지면서, 전국 축제 정보를 한눈에 볼 수 있는 서비스를 기획했습니다. 공공데이터 API를 활용해 일정, 위치, 맛집 등을 쉽게 확인하고, 축제 경험을 공유하고 소통할 수 있는 공간을 제공합니다.

## 팀원 및 역할
- **황혜진(관리자)**
  
  controller 코드경로 : The-Festa/thefesta/src/main/java/kr/co/thefesta/admin/

   mappers 코드경로 : The-Festa/thefesta/src/main/resources/mappers/AdminMapper.xml
  
- 박미리(좋아요 기능 및 주변 맛집 추전)
- 박지현(맵)
- 송다인(회원)
- 정찬호(스케쥴러)
- 류성일(게시글 및 댓글)

## 개발 기간 및 작업 관리
개발기간
- 전체 개발 기간 : 2023-11-01 ~ 2023-12-27
- 기획 및 서버구축 : 2023-11-01 ~ 2023-11-31
- UI 구현 : 2023-12-01 ~ 2023-12-19
- 통합 테스트: 2022-12-20 ~ 2022-12-27

작업 관리
- GitHub Projects와 Issues를 사용하여 진행 상황을 공유했습니다.
- 주간회의를 진행하며 작업 순서와 방향성에 대한 고민을 나누고 Jira에 일정 및 결과물을 공유하였습니다.


## 기능 구현

### [로그인]
- 관리자 로그인시 헤더 변경

<img width="48%" src="https://github.com/pueser/The-Festa/assets/117990884/82f76e49-2a27-4c9a-8ff4-d9f25a269ee8"/> <img width="48%" src="https://github.com/pueser/The-Festa/assets/117990884/3d92ef2c-e3a5-4d5c-9ca7-d6388acdd644"/>

### [회원]
- 회원 신고내용 확인 및 회원 상태 변경
- 회원 신고누적 4회 초과 시 자동 강퇴처리

<img width="48%" src="https://github.com/pueser/The-Festa/assets/117990884/636c1659-b671-4eeb-8d65-bf9286a94f36"/> <img width="48%" src="https://github.com/pueser/The-Festa/assets/117990884/ee64ca8d-c1de-48bc-b50b-8d3a9ac4482d"/>

### [문의사항]
- 문의사항 확인 및 문의글의 댓글 작성

<img width="48%" src="https://github.com/pueser/The-Festa/assets/117990884/f240f970-7b25-4bc6-8eec-d3a57112bb4b"/> <img width="48%" src="https://github.com/pueser/The-Festa/assets/117990884/fe762175-a470-4e3a-bfff-6202c0906065"/>

### [축제]
- 축제 삭제 및 축제 건의 내용 확인

<img width="48%" src="https://github.com/pueser/The-Festa/assets/117990884/4c73b1c1-d588-4f54-a62e-fe64024bdb45"/> <img width="48%" src="https://github.com/pueser/The-Festa/assets/117990884/391cf020-d1fe-4c20-9241-275cf4bd2731"/>

### [게시판]
- 게시글 및 게시글 삭제처리

<img width="48%" src="https://github.com/pueser/The-Festa/assets/117990884/da6ce547-15a5-4e08-bfc8-832713436d84"/> <img width="48%" src="https://github.com/pueser/The-Festa/assets/117990884/e6d74d7a-0958-4d29-9718-64dbef11baf9"/>


<img width="48%" src="https://github.com/pueser/The-Festa/assets/117990884/c8f36ed4-f794-4b70-a216-d56432d5f5ef"/>
