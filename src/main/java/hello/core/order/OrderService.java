package hello.core.order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice); // 클라이언트가 주문하는 부분, 저것들 다 받고, 최종 오더 결과 반환
}
