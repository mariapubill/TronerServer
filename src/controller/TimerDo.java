package controller;


import network.DedicatedServerUser;


import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class TimerDo {


    private boolean finishCD = false;



    int counter = 10;
    Boolean isIt = false;

    public TimerDo(){

        this.finishCD = finishCD;
        this.counter = counter;
        this.isIt = isIt;



    }
    //3,2,1,0

    public void startCountDown(LinkedList<DedicatedServerUser> dedicatedServe) {
        Timer timer = new Timer();
        counter = 4;
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                int i = counter--;
                dedicatedServe.get(0).enviaProgressCountDown(counter,dedicatedServe);
                if (counter == -1) {
                    //nService.sayStartGame();
                    timer.cancel();

                    finishCD = true;
                } else if (isIt) {
                    timer.cancel();
                    isIt = false;
                }

            }
        };
        timer.scheduleAtFixedRate(task, 1000, 1000);
    }

    public int getCounter() {
        return counter;
    }

    public Boolean getIsIt() {
        return isIt;
    }

    public boolean getFinishCD() {
        return finishCD;
    }
}

