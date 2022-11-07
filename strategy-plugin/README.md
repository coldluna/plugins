## 使用方法

### 引入依赖
略

### 策略类上使用@Strategy注解，并实现Actuator接口

```java
@Strategy(group = "area", trait = "district")
public class AreaHandler implements Actuator<AreaEntity, Void> {

    @Override
    public Void execute(AreaEntity areaEntity) {
        return null;
    }

}
```
### 注入StrategySelector，并获取策略类
```java
@RestController
public class AreaController {

    @Autowired
    private StrategySelector strategySelector;
    
    @RequestMapping("/area/list")
    public void test() {
        Actuator<AreaEntity, Void> actuator = this.strategySelector.selectStrategy("area", "district");
        Void ignore = actuator.execute(new AreaEntity());
    }

}
```
