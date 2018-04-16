package com.demo.Index;

import com.demo.common.model.Rov;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import java.util.*;

public class IndexService {
    private static final Rov rovDao = new Rov().dao();;

    public List<Record> findRoll() {
        return Db.find(rovDao.getSqlPara("echarts.findRoll"));
    }

    public List<Record> findYaw() {
        return Db.find(rovDao.getSqlPara("echarts.findYaw"));
    }

    public List<Record> findPitch() {
        return Db.find(rovDao.getSqlPara("echarts.findPitch"));
    }

    public List<Record> findDepth() {
        return Db.find(rovDao.getSqlPara("echarts.findDepth"));
    }

    public List<Record> findSpeed() {
        return Db.find(rovDao.getSqlPara("echarts.findSpeed"));
    }

    public List<Record> findStatus() {
        return Db.find(rovDao.getSqlPara("echarts.findStatus"));
    }

}
