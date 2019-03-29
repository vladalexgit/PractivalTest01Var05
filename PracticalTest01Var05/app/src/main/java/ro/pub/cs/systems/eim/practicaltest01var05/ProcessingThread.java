package ro.pub.cs.systems.eim.practicaltest01var05;


import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.util.Log;

import java.util.Date;
import java.util.Random;

public class ProcessingThread extends Thread {

    private Context context = null;
    private boolean isRunning = true;

    private Random random = new Random();

    private int score;

    public ProcessingThread(Context context, int score) {
        this.context = context;
        this.score = score;
    }

    @Override
    public void run() {
        Log.d("[VICTORY]", "Thread has started! PID: " + Process.myPid() + " TID: " + Process.myTid());
        while (isRunning) {
            sendMessage();
            sleep();
        }
        Log.d("[VICTORY]", "Thread has stopped!");
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction("actiune");
        intent.putExtra("score",
                new Date(System.currentTimeMillis()) + " " + this.score);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}