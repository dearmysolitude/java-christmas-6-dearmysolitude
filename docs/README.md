### 구현 기능
- [x] InputView
  - 식당 방문 날짜 입력
  - 메뉴 선택 
    - 메뉴는 20개 이하
    - 음료만 주문 불가
- [x] OutputView
  - 안내 멘트
  - 주문 메뉴
  - 할인 전 총 금액
  - 증정 메뉴
  - 혜택 내역
  - 총 혜택 금액
  - 할인 후 예상 결제 금액
  - 부여된 이벤트 배지
- [x] 주문 상품(Enum: 가격(할인 전 금액), 이름, 종류(디저트/메인))
- 메뉴 관리 클래스
  - [x] 할인 전 총액 산출
    - [x] 10000원 이상이 아니면 이벤트 적용 X.
    - [x] 12 만원 이상이면 샴페인 1개 추가 여부
  - [x] 할인 항목 별 적용 여부 관리
    - [x] 디데이 할인
    - [x] 평일 할인
    - [x] 주말 할인
    - [x] 특별 할인
  - [x] 배지 부여
  - [x] 할인 후 가격 정산

### 이하 요구사항 정리

#### 이벤트
1. 크리스마스 디데이 할인
   - 12 월 1 일~ 25 일
   - 1000원으로 시작하여 크리스마스가 다가올수록 할인 금액이 100원씩 증가
   - 총 주문 금액에서 해당 금액만큼 할인
2. 평일 할인(일 ~ 목): 디저트 메뉴 1개 당 -2023
3. 주말 할인(금, 토): 메인 메뉴 1개 당 -2023
4. 특별 할인: 달력에 별이 있다면 총 금액에서 -1000
5. 증정 이벤트: 할인 전 총 금액 12만원 이상일 때 샴페인 1개 증정
6. 디데이 할인을 제외하면 12월 전일 적용

#### 배지 부여
- 총 혜택 금액에 따라 배지 부여
  - 5000원 이상: 별
  - 10000원 이상: 트리
  - 20000원 이상: 산타

#### 주의 사항
- 총 주문 금액 10000원 이상부터 적용
- 음료만 주문 시 주문 불가
- 메뉴는 한 번에 최대 20 개만(음료 포함)

#### 요청사항
식당 방문 날짜, 메뉴 미리 선택하면 "이벤트 플래너"가 주문 메뉴, 할인 전 총 주문 금액, 증정 메뉴, 혜택 내역, 총 혜택 금액, 할인 후 예상 결제 금액, 12월 이벤트 배지 내용 보여줌

