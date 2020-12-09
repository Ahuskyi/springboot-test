package com.waipin.service.Impl;

import com.waipin.mapper.JiaoshiMapper;
import com.waipin.model.Jiaoshi;
import com.waipin.service.JiaoshiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JiaoshiServiceImpl implements JiaoshiService {
    @Autowired
    JiaoshiMapper jiaoshiMapper;
    @Override
    public Jiaoshi chazhaoid(int id) {
        return jiaoshiMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateinfo(Jiaoshi jiaoshi) {
        int i = jiaoshiMapper.updateByPrimaryKey(jiaoshi);
        if (i!=0){
            return true;
        }else {
            return false;
        }

    }
}
