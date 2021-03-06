package com.blog.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class OssConfig {
	private String endpoint = "https://oss-cn-beijing.aliyuncs.com";
	private String accessKeyId = "";
	private String secretAccessKey = "";
	private String bucketName = "mhw-oss";
	private String urlPrefix = "https://mhw-oss.oss-cn-beijing.aliyuncs.com/";

	@Bean
	public OSS oSSClient() {
		return new OSSClientBuilder().build(endpoint, accessKeyId, secretAccessKey);
	}

}
