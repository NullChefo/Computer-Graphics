package main;

import views.MainView;

import java.awt.*;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        MainView window = new MainView();

        window.getBrightView().setLocation(new Point((int) (window.getX() + window.getWidth() - window.getBrightView().getWidth()), (int) (window.getY())));

        window.getTextView().setLocation(new Point((int) (window.getX() + window.getWidth() - window.getTextView().getWidth()), (int) (window.getY())));

        window.getLaysView().setLocation(new Point(window.getX() + window.getWidth() + 10, window.getY()));

        window.getRotView().setLocation(new Point((int) (window.getX() + window.getWidth() - window.getRotView().getWidth()), (int) (window.getY())));

        window.getResizeView().setLocation(new Point((int) (window.getX() + window.getWidth() - window.getResizeView().getWidth()), (int) (window.getY())));

        window.getColorBalanceView().setLocation(new Point((int) (window.getX() + window.getWidth() - window.getColorBalanceView().getWidth()), (int) (window.getY())));

        window.getToolsView().setLocation(new Point(window.getX() - window.getToolsView().getWidth() - 10, window.getY()));
    }
}
