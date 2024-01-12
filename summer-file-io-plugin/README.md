# 使用方法

## minio

### 引入依赖
```pom.xml
<!--请覆盖okhttp3的版本-->
    <properties>
        <okhttp3.version>4.8.1</okhttp3.version>
    </properties>

        <dependency>
            <groupId>io.github.coldluna</groupId>
            <artifactId>summer-file-io-plugin</artifactId>
            <version>1.0.0.RELEASE</version>
        </dependency>
```



### 配置yml

```yaml
oss:
  scheme: minio
  bucket-name: xxx
  access-key-id: xxx
  access-key-secret: xxx
  endpoint: http://ip:port/
```

### 注入ossTemplate并使用

```java

@RestController
public class OssController {

    @Autowired
    private OssTemplate ossTemplate;

    @RequestMapping("/list")
    private List<Bucket> listBucket() {
        return this.ossTemplate.listBucket();
    }

}
```
