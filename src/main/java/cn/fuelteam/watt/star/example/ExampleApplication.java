package cn.fuelteam.watt.star.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.Ordered;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.github.pagehelper.PageInfo;

import cn.fuelteam.watt.star.example.base.model.Base;
import cn.fuelteam.watt.star.example.base.service.BaseService;
import cn.fuelteam.watt.star.example.message.model.Message;
import cn.fuelteam.watt.star.example.message.service.MessageService;
import cn.fuelteam.watt.star.example.user.service.UserService;

@EnableTransactionManagement(proxyTargetClass = true, order = Ordered.HIGHEST_PRECEDENCE)
@SpringBootApplication(scanBasePackages = { "cn.fuelteam.watt.star.example.user", "cn.fuelteam.watt.star.example.base",
        "cn.fuelteam.watt.star.example.message" })
public class ExampleApplication implements CommandLineRunner {

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(ExampleApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args).close();
    }

    private static final Logger logger = LoggerFactory.getLogger(ExampleApplication.class);

    @Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private BaseService baseService;

    @Override
    public void run(String... args) throws Exception {
        this.testUser();

        this.testBase();
        this.testMessage();

        this.testUser();
        this.testBase();
        this.testMessage();
    }

    private void testBase() {
        String name = "test-name-";
        for (int i = 0; i < 10; i++) {
            baseService.save(name + i);
        }
        PageInfo<Base> page = baseService.findAll(1, 5);
        logger.info("{}", page);
    }

    private void testMessage() {
        messageService.createIfNotExists();
        String title = "test-title-";
        String context = "test-context-";
        for (int i = 0; i < 10; i++) {
            messageService.save(title + i, context + i);
        }
        messageService.save(title, context);

        List<Message> messages = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            messages.add(new Message(i * 1000L, title + i, context + i, new Date()));
        }
        PageInfo<Message> page = messageService.findAll(1, 5);
        logger.info("out {}", page);
    }

    private void testUser() {
        userService.delAll();
        for (int i = 0; i < 5; i++) {
            final String name = "data-name-" + i;
            final String description = "Hello data source master slave route!";
            logger.info("{}", userService.findFirst());
            userService.save(name, description);
        }
        userService.saves("batch6", "batch5", "batch4", "batch3", "batch2", "batch1");
        logger.info("out {}", userService.findFirst());
        logger.info("out {}", userService.findFirstBySlave());
        logger.info("out {}", userService.findFirst());
        logger.info("out {}", userService.findAll());
    }
}