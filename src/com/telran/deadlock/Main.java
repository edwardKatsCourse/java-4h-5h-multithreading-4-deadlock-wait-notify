package com.telran.deadlock;

import java.io.File;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        File mainFile = new File("main_file.txt");
        //Jira, Confluence

        File backupFile = new File("backup_file.txt");

        MainFileProcess mainFileProcess = new MainFileProcess(mainFile, backupFile);
        mainFileProcess.start();
        Thread.sleep(10_000);


        //by the legend, something went wrong here


        BackupFileProcess backupFileProcess = new BackupFileProcess(mainFile, backupFile);
        backupFileProcess.start();

        //mainFileProcess.start();
        //backupFileProcess.start();

//        FileUtils.write(mainStatusFile, "abc");

//        System.out.println(FileUtils.getContent(mainStatusFile));

//        FileUtils.rewrite(backup, FileUtils.getContent(mainStatusFile));
    }
}


