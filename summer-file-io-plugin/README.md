# 使用方法

## minio

### 引入依赖

略

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
