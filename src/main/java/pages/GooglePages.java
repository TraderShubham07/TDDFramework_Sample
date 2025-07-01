package pages;

import core.BaseCommands;
import maps.GoogleMaps;


public class GooglePages extends GoogleMaps {
    private static GooglePages object;

    public static GooglePages getInstance() {
        if (object == null) {
            object = new GooglePages();
        }
        return object;
    }

    public void click() {
//        BaseCommands.sendAndClick(searchBox(), "I am here to Test");
        BaseCommands.click(about());
    }
}
