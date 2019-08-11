# Personal Libraries

This repository contains my personal libraries which is useful for development.


## 1. HeaderAuthInterceptor

It provides humble authentication method for personal products.
Check the only thing that header(`x-custom-header`) content is same as you specified.

You can also change header name if you want.


### Usage

In Java

```java
@Configuration
class CalendarWebMvcConfigure implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    // get content from environment variable
    String content = System.getenv("HEADER_CONTENT");

    // you can specify content of header
    registry.addInterceptor(HeaderAuthInterceptor(content));
    // Or, you can change the name  of header
    registry.addInterceptor(HeaderAuthInterceptor(content, "my-custom-header"));
    }

}
```

In Kotlin

```kotlin
@Configuration
class CalendarWebMvcConfigure() : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        // get content from environment variable
        val content = System.getenv("HEADER_CONTENT")

        // you can specify content of header
        registry.addInterceptor(HeaderAuthInterceptor(content))
        // Or, you can change the name  of header
        registry.addInterceptor(HeaderAuthInterceptor(content, "my-custom-header"))
    }
}
```

### Response


### Response

Return 401 when wrong header or content was set as follows.

```bash
# No header
 curl -v localhost:8080/ping
*   Trying 127.0.0.1...
* TCP_NODELAY set
* Connected to localhost (127.0.0.1) port 8080 (#0)
> GET /ping HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.54.0
> Accept: */*
>
< HTTP/1.1 401
< Content-Length: 0
<
* Connection #0 to host localhost left intact

# Wrong header
curl -v  -H "x-custom-header: pong" localhost:8080/ping
*   Trying 127.0.0.1...
* TCP_NODELAY set
* Connected to localhost (127.0.0.1) port 8080 (#0)
> GET /ping HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.54.0
> Accept: */*
> x-custom-header: pong
>
< HTTP/1.1 401
< Content-Length: 0
<
* Connection #0 to host localhost left intact
```

If Correct header was set, proceed application as follows.

```bash
# Correct header and Content
curl -v  -H "x-custom-header: correctcontent" localhost:8080/ping
*   Trying 127.0.0.1...
* TCP_NODELAY set
* Connected to localhost (127.0.0.1) port 8080 (#0)
> GET /ping HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.54.0
> Accept: */*
> x-custom-header: correctcontent
>
< HTTP/1.1 200
< Content-Type: text/plain;charset=UTF-8
< Content-Length: 10
<
* Connection #0 to host localhost left intact
Connected.⏎
```



License: This software is released under the MIT License, see LICENSE.txt.
