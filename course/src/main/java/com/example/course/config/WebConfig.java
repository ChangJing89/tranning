package com.example.course.config;

import java.nio.charset.Charset;
import java.util.List;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.google.common.collect.Lists;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    //定义一个convert 转换消息的对象
    FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
    // 2 添加fastjson 的配置信息 比如 是否要格式化 返回的json数据
    FastJsonConfig fastJsonConfig = new FastJsonConfig();
    fastJsonConfig.setSerializerFeatures(
        SerializerFeature.PrettyFormat
    );
    fastConverter.setFastJsonConfig(fastJsonConfig);
    // 解决乱码的问题
    List<MediaType> fastMediaTypes = Lists.newArrayList();
    fastMediaTypes.add(new MediaType("application","json",Charset.forName("UTF-8")));
    fastConverter.setSupportedMediaTypes(fastMediaTypes);
    converters.add(fastConverter);
  }

}
