package com.demo.Index;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;

import java.io.IOException;
import java.util.List;

public class IndexController extends Controller {
    private static final IndexService srv = new IndexService();

    public void index() throws IOException {
        render("index.html");
    }

    public void choose() {
        String choose = getPara("choose");
        System.out.println(choose);
        switch (choose) {
            case "ForwardorBackward":
                List<Record> records = srv.findForwardorBackward();
                renderJson(records);
                break;
            case "ClimborDive":
                records = srv.findClimborDive();
                renderJson(records);
                break;
            case "RobotYaw":
                records = srv.findRobotYaw();
                renderJson(records);
                break;
            case "RobotDepth":
                records = srv.findRobotDepth();
                renderJson(records);
                break;
            case "LeftorRight":
                records = srv.findLeftorRight();
                renderJson(records);
                break;
            case "UporDown":
                records = srv.findUporDown();
                renderJson(records);
                break;
            default:
                records = srv.findUporDown();
                renderJson(records);
                break;
        }
    }
}
