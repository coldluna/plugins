### 示例代码

```java
@Slf4j
@Component
@ServerEndpoint("/websocket/{userId}")
@Data
public class WebsocketServer {

    private static final CopyOnWriteArraySet<WebsocketServer> SERVERS = new CopyOnWriteArraySet<>();

    private Session session;

    private String userId;

    private AtomicLong counter = new AtomicLong(0);

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        SERVERS.add(this);
        this.session = session;
        this.userId = userId;
        log.info("客户端{}连接成功", session.getId());
    }

    @OnClose
    public void onClose(Session session) {
        SERVERS.remove(this);
        log.info("客户端{}断开连接", session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("接收到的信息为{}", message);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        log.error("发生异常", throwable);
    }

    /**
     * 发送消息
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }
}
```
