package cn.fuelteam.watt.star.example.base.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.fuelteam.watt.star.example.base.dao.BaseMapper;
import cn.fuelteam.watt.star.example.base.model.Base;
import cn.fuelteam.watt.star.example.base.service.BaseService;

@Service
public class BaseServiceImpl implements BaseService {

    private static final Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);

    @Autowired
    private BaseMapper baseMapper;

    @Override
    @Transactional("base")
    public void save(String name) {
        Base base = new Base();
        base.setCtime(new Date());
        base.setName(name);
        int num = baseMapper.insertSelective(base);
        if (num == 0) logger.error("save base failed, name:{}", name);
    }

    @Override
    public PageInfo<Base> findAll(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<Base> page = baseMapper.findAll();
        return new PageInfo<Base>(page);
    }
}