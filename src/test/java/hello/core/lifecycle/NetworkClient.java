package hello.core.lifecycle;


public class NetworkClient{

    private String url;

    public NetworkClient(){
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작 시 호출
    public void connect(){
        System.out.println("connect: " + url);
    }

    // 서비스 종료 시 호출
    public void call(String message){
        System.out.println("call: " + url + " message = " + message);
    }

    public void disconnect(){
        System.out.println("close: " + url);
    }

    public void init() throws Exception { // properties 세팅이 끝나면 (의존관계 주입이 끝나면) 호출해주겠다는 뜻
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    public void close() { // 빈이 종료될 때 호출
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
