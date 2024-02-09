package hello.core.singleton;

public class StatefulService {

    private int price; // 상태를 유지하는 필드 10000 -> 20000
    public void order(String name, int price){
        System.out.println("name = " + name + " price = " + price);
        this.price = price; // 여기가 문제! 1만원 넣으면 price가 1만원 되거, 이후 2만원 넣으면 price가 2만원 됨.
    }

    public int getPrice(){
        return price;
    }
}
