package top.cxh.chat.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class ImageServer extends WebMvcConfigurationSupport {
	// 获取配置文件中图片的路径
	@Value("${spring.resources.static-locations}")
	private String mImagesPath;

	// 访问图片方法
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if (mImagesPath.equals("") || mImagesPath.equals("${spring.resources.static-locations}")) {
			String imagesPath = ImageServer.class.getClassLoader().getResource("").getPath();
			if (imagesPath.indexOf(".jar") > 0) {
				imagesPath = imagesPath.substring(0, imagesPath.indexOf(".jar"));
			} else if (imagesPath.indexOf("classes") > 0) {
				imagesPath = "file:" + imagesPath.substring(0, imagesPath.indexOf("classes"));
			}
			imagesPath = imagesPath.substring(0, imagesPath.lastIndexOf("/")) + "/images/";
			mImagesPath = imagesPath;
		}
		registry.addResourceHandler("/images/**").addResourceLocations(mImagesPath);
		super.addResourceHandlers(registry);
	}
}
