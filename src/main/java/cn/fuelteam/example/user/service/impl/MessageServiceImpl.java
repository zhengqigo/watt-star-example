package cn.fuelteam.example.user.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.fuelteam.example.user.dao.MessageMapper;
import cn.fuelteam.example.user.model.Message;
import cn.fuelteam.example.user.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

    private static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    @Autowired
    private MessageMapper messageMapper;

    @Override
    @Transactional("dsUser")
    public void save(String title, String context) {
        Message message = new Message();
        message.setContext(context);
        message.setTitle(title);
        message.setCtime(new Date());
        int num = messageMapper.insertSelective(message);
        if (num == 0) logger.error("保存消息失败   title:{}, context:{}", title, context);
    }

    @Override
    public PageInfo<Message> findAll(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<Message> page = messageMapper.selectAll();
        return new PageInfo<Message>(page);
    }
}