package com.demo.Index;

import com.demo.common.model.Keyboard;
import com.demo.common.model.Rov;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import java.util.*;

public class IndexService {
    public static final IndexService me = new IndexService();
    private static final Rov rovDao = new Rov().dao();
    private static final Keyboard keyboardDao = new Keyboard().dao();

    public List<Record> findForwardorBackward() {
        return Db.find(keyboardDao.getSqlPara("echarts.findForwardorBackward"));
    }

    public List<Record> findClimborDive() {
        return Db.find(keyboardDao.getSqlPara("echarts.findClimborDive"));
    }

    public List<Record> findRobotYaw() {
        return Db.find(rovDao.getSqlPara("echarts.findRobotYaw"));
    }

    public List<Record> findRobotDepth() {
        return Db.find(rovDao.getSqlPara("echarts.findRobotDepth"));
    }

    public List<Record> findLeftorRight() {
        return Db.find(keyboardDao.getSqlPara("echarts.findLeftorRight"));
    }

    public List<Record> findUporDown() {
        return Db.find(keyboardDao.getSqlPara("echarts.findUporDown"));
    }
}
