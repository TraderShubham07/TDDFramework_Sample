package pages;

import core.BaseCommands;
import maps.AmazonMaps;

public class AmazonPages extends AmazonMaps {
    private static AmazonPages object;

    public static AmazonPages getInstance() {
        if (object == null) {
            object = new AmazonPages();
        }
        return object;
    }

    public void click() {
        BaseCommands.click(amazonLogo());
    }

    public void searchBox() throws InterruptedException {
        BaseCommands.sendAndClick(searchBox1(),"Remote");
        BaseCommands.click(searchBtn());
        Thread.sleep(2000);
        BaseCommands.click(orderClick());

    }
}
