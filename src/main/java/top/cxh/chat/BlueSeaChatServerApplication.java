package top.cxh.chat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import top.cxh.chat.config.DruidConfig;
import top.cxh.chat.controller.ChatServer;

@SpringBootApplication
@MapperScan("top.cxh.chat.mapper")
public class BlueSeaChatServerApplication {

	public static void main(String[] args) {
		new DruidConfig();
		new Thread() {
			public void run() {
				try {
					System.out.println("聊天服务启动端口：7000");
					new ChatServer(7000).run();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
		}.start();
		SpringApplication.run(BlueSeaChatServerApplication.class, args); 
	}

}
