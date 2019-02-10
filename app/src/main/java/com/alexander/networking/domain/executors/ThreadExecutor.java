package com.alexander.networking.domain.executors;


import com.alexander.networking.domain.interactors.AbstractInteractor;


public class ThreadExecutor implements Executor {

    private static volatile ThreadExecutor threadExecutor;

    private ThreadExecutor(){

    }

    @Override
    public void execute(final AbstractInteractor interactor){
        new Thread(new Runnable() {
            @Override
            public void run() {
                interactor.run();
                interactor.onFinished();
            }
        }).start();
    }

    public static Executor getInstance(){
        if (threadExecutor == null)
            threadExecutor = new ThreadExecutor();
        return threadExecutor;
    }
}
