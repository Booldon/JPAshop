# JPAshop
Spring Boot, JPA를 이용한 간단한 Commerce 웹 어플리케이션 입니다.

Member 생성 / Item 생성, 수정 및 삭제 / Item 주문, 장바구니 기능 및 주문목록 확인의 기능이 있습니다.

뷰 템플릿은 thymeleaf를 사용하였으며 H2dataBase로 간단하게 구현하였습니다.

PPT파일에 Entity ERD 다이어그램을 만들었으니 참고하시면 감사하겠습니다.

상당 부분 김영한님의 "실전스프림부트JPA활용" 강의를 참고하여 개발하였습니다.

새로 추가된 부분은 예외처리, 장바구니 기능이며 장바구니는 DTO, DAO를 활용해서 개발해보았습니다.

![홈 화면](https://github.com/Booldon/JPAshop/assets/99729203/4a4e40a7-90f6-47ac-954a-584d0a819977)
홈 화면

![맴버입력폼](https://user-images.githubusercontent.com/99729203/258285896-a2e468d2-008e-4bbd-bd61-6396f653723f.JPG)
맴버 입력
![맴버생성](https://user-images.githubusercontent.com/99729203/258285921-a4b43abf-6a65-496f-aaa7-365556b3cff3.JPG)
데이터 베이스에 저장된 모습


![아이템입력폼](https://user-images.githubusercontent.com/99729203/258286009-c238ff53-f7c6-4046-a930-80f7f16f1f0a.JPG)
아이템 입력
![아이템생성](https://user-images.githubusercontent.com/99729203/258286024-2b69a58a-41e5-4254-b713-28aacd8f6bcd.JPG)
데이터 베이스에 저장된 모습
![아이템리스트](https://user-images.githubusercontent.com/99729203/258286050-65fb6f32-4e4b-457a-b778-99fb6d39abc9.JPG)
아이템 리스트

![아이템수정폼](https://user-images.githubusercontent.com/99729203/258286093-c9c3433c-6b1f-4fe9-8ca4-376e28533291.JPG)
아이템 수정
![아이템수정](https://user-images.githubusercontent.com/99729203/258286116-1d38bc29-c7a9-404a-ab80-d4bf3f18aaf6.JPG)
데이터 베이스에 저장된 모습
![아이템수정리스트](https://user-images.githubusercontent.com/99729203/258286066-4dce2014-b7dc-401c-8252-3163e2f3268c.JPG)
아이템 수정 후 리스트








