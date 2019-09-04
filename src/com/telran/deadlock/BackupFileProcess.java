package com.telran.deadlock;

import java.io.File;

public class BackupFileProcess extends Thread {
    private File mainFile;
    private File backupFile;

    public BackupFileProcess(File mainFile, File backupFile) {
        super("Backup Thread");
        this.mainFile = mainFile;
        this.backupFile = backupFile;
    }

    @Override
    public void run() {
        synchronized (mainFile) {
            synchronized (backupFile) {
                //read from backup file
                System.out.println("Backup file is locked by -> " + super.getName());

                String content = FileUtils.getContent(backupFile);


                System.out.println("Main file is locked by -> " + super.getName());
                FileUtils.rewrite(mainFile, content);
            }
        }
    }
}
