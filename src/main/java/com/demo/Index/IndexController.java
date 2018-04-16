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
        switch (choose) {
            case "roll":
                List<Record> records = srv.findRoll();
                renderJson(records);
                break;
            case "yaw":
                records = srv.findYaw();
                renderJson(records);
                break;
            case "pitch":
                records = srv.findPitch();
                renderJson(records);
                break;
            case "depth":
                records = srv.findDepth();
                renderJson(records);
                break;
            case "speed":
                records = srv.findSpeed();
                renderJson(records);
                break;
            case "status":
                records = srv.findStatus();
                renderJson(records);
                break;
            default:
                records = srv.findRoll();
                renderJson(records);
                break;
        }
    }

}
