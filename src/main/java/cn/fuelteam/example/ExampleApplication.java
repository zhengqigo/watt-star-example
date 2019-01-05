package cn.fuelteam.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fuelteam.watt.star.core.Proxy;
import org.fuelteam.watt.star.core.Proxy.SwitchExecute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.Ordered;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import cn.fuelteam.example.user.model.Message;
import cn.fuelteam.example.user.model.User;
import cn.fuelteam.example.user.service.UserMessageService;
import cn.fuelteam.example.user.service.UserService;

@EnableTransactionManagement(proxyTargetClass = true, order = Ordered.HIGHEST_PRECEDENCE)
@SpringBootApplication(scanBasePackages = { "cn.fuelteam.example.user" })
public class ExampleApplication implements CommandLineRunner {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(ExampleApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Autowired
    private UserMessageService userMessageService;

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        // this.testUser();
        this.testUserMessage();
    }

    private void testUserMessage() throws Exception {
        User user = new User();
        user.setName("name1");
        user.setDescription("description1");

        Message message = new Message();
        message.setTitle("title1");
        message.setContext("message1");
        userMessageService.save(user, message);
    }

    @SuppressWarnings("unused")
    private void testUser() {
        userService.delAll();
        for (int i = 0; i < 5; i++) {
            final String name = "test-name-" + i;
            final String description = "test-description-" + i;
            Proxy.master(new SwitchExecute<Void>() {
                @Override
                public Void run() throws Throwable {
                    logger.info(userService.findFirst());
                    userService.save(name, description);
                    return null;
                }
            });
        }

        // 默认在主库查询
        logger.info(userService.findFirst());
        // 注解到从库查询
        logger.info(userService.findFirstBySlave());
        // 切换到从库查询
        Proxy.slave(new SwitchExecute<Void>() {
            @Override
            public Void run() throws Throwable {
                logger.info(userService.findFirst());
                return null;
            }
        });
    }
}
