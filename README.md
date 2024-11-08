# java-convenience-store-precourse

## 기능 목록


- [X] 상품 목록 초기화
    - 상품 정보와 행사 정보를 파일에서 읽어와 시스템에 로드합니다.
    - 재고, 가격, 행사 등 각 상품의 정보를 관리합니다.


- [ ] 구매 상품 입력 받기
    - 사용자로부터 구매할 상품과 수량을 입력받습니다.
    - 입력된 형식이 올바른지 확인하고, 올바르지 않다면 오류 메시지를 출력합니다.


- [ ] 결제 가능 여부 확인
    - 입력된 상품이 존재하는지, 구매하려는 수량이 재고를 초과하지 않는지 확인합니다.
    - 오류가 발생할 경우 사용자에게 알리고 다시 입력을 받습니다.


- [ ] 프로모션 할인 적용
    - 프로모션이 적용 가능한 경우, 조건에 맞게 할인 혜택을 계산하여 적용합니다.
    - 프로모션 재고가 부족한 경우 남은 수량에 대해 정가로 결제할지 여부를 묻습니다.


- [ ] 멤버십 할인 적용
    - 멤버십 회원일 경우, 프로모션 적용 후 남은 금액의 30%를 할인합니다.
    - 멤버십 할인 금액의 최대 한도를 설정하고, 이를 초과하지 않도록 제한합니다.


- [ ] 최종 결제 금액 계산
    - 각 상품별 가격과 수량을 곱하여 총 구매액을 계산합니다.
    - 프로모션 할인 및 멤버십 할인을 반영하여 최종 결제 금액을 산출합니다.


- [ ] 영수증 출력
    - 구매한 상품, 수량, 가격, 프로모션 혜택, 멤버십 할인, 최종 결제 금액을 포함하여 영수증을 출력합니다.
    - 영수증을 보기 쉽게 정렬하여 고객이 쉽게 확인할 수 있도록 합니다.


- [ ] 추가 구매 여부 확인
    - 영수증 출력 후 추가 구매를 원하는지 확인합니다.
    - 추가 구매를 선택하면, 재고가 업데이트된 상품 목록을 다시 보여줍니다.


- [ ] 재고 업데이트
    - 결제가 완료되면 각 상품의 재고에서 결제된 수량을 차감하여 최신 재고 정보를 유지합니다.


- [ ] 에러 처리
    - 입력 오류, 존재하지 않는 상품, 재고 초과 등 다양한 예외 상황을 처리하고 오류 메시지를 출력합니다.
    - "[ERROR]" 형식으로 시작하는 메시지를 출력하여 사용자에게 알립니다.