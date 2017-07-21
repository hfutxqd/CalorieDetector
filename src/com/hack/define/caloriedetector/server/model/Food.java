package com.hack.define.caloriedetector.server.model;

/**
 * Created by IMXQD on 2017/7/21.
 */
public class Food {

    public Table componentTab;

    public String advantages;
    public String disadvantages;
    public String walk;
    public String running;
    public String skipping;
    public String aerobics;

    public Food() {
        componentTab = new Table();
    }

    @Override
    public String toString() {
        return "DetectResult{" +
                "componentTab=" + componentTab +
                ", advantages='" + advantages + '\'' +
                ", disadvantages='" + disadvantages + '\'' +
                ", walk='" + walk + '\'' +
                ", running='" + running + '\'' +
                ", skipping='" + skipping + '\'' +
                ", aerobics='" + aerobics + '\'' +
                '}';
    }
}
