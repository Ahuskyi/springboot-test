package com.waipin.service.Impl;

import com.waipin.mapper.ZhuanjiaMapper;
import com.waipin.model.Zhuanjia;

import com.waipin.service.ZhuanjiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZhuanjiaServiceImpl implements ZhuanjiaService {
    @Autowired
    ZhuanjiaMapper zhuanjiaMapper;
    @Override
    public Zhuanjia chazhaoid(int id) {
        return zhuanjiaMapper.selectByPrimaryKey(id);
    }
    @Override
    public boolean updateinfo(Zhuanjia zhuanjia) {
        int i = zhuanjiaMapper.updateByPrimaryKey(zhuanjia);
        if (i!=0){
            return true;
        }else {
            return false;
        }
    }
}
