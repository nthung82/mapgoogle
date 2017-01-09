package app.hungnt.test.com.newmymap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hungnt on 1/9/17.
 */

public class Callwebservice {
    public static List<MapInfo> getListInfo(){
        List<MapInfo> list = new ArrayList<>();
        MapInfo obj1 = new MapInfo();

        obj1.setLatitude(21.036770);
        obj1.setLongitude(105.801144);
        obj1.setInfo("Ha noi");
        list.add(obj1);

        MapInfo obj2 = new MapInfo();
        obj2.setLatitude(21.035280);
        obj2.setLongitude(105.809216);
        obj2.setInfo("Ha noi1");
        list.add(obj2);

        MapInfo obj3 = new MapInfo();

        obj3.setLatitude(21.035388);
        obj3.setLongitude(105.819272);
        obj3.setInfo("Ha noi2");

        list.add(obj3);
        return list;

    }
}
