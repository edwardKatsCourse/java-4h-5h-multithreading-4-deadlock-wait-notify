package com.telran.deadlock;

import java.io.File;

public class MainFileProcess extends Thread {
    private File mainFile;
    private File backupFile;

    public MainFileProcess(File mainFile, File backupFile) {
        super("Main Thread");
        this.mainFile = mainFile;
        this.backupFile = backupFile;
    }

    @Override
    public void run() {
        while (true) {

            synchronized (mainFile) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                String content = "My content";

                System.out.println("Main   file is locked by -> " + super.getName());

                FileUtils.write(mainFile, content);
                //Write to MAIN file
                synchronized (backupFile) {
                    System.out.println("Backup file is locked by -> " + super.getName());
                    //write to backup

                    FileUtils.write(backupFile, content);
                }
            }
        }
    }
}
