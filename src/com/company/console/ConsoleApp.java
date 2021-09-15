package com.company.console;

import com.company.controllers.Controller;

public class ConsoleApp implements ExecutionControlListener {

    private Controller controller;
    private boolean executing;

    public ConsoleApp() {
        this.controller = new Controller(this);
        this.executing = true;
    }

    public void execute() {
        loadData();
        startMainLoop();
        saveData();
    }

    private void loadData() {
        controller.loadData();
    }

    private void startMainLoop() {
        while(executing) {
            controller.execute();
        }
    }

    private void saveData() {
        controller.saveData();
    }

    @Override
    public void stopExecution() {
        executing = false;
    }
}
