package com.mzbloc.springboot;

import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

/**
 * Created by tanxw on 2018/12/25.
 */
public class DubboHolderListener implements ApplicationListener {
    private static Thread holdThread;
    private static Boolean running;

    public DubboHolderListener() {
    }

    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof ApplicationPreparedEvent) {
            if(running == Boolean.FALSE) {
                running = Boolean.TRUE;
            }

            if(holdThread == null) {
                holdThread = new Thread(new Runnable() {
                    public void run() {
                        System.out.println(Thread.currentThread().getName());

                        while(DubboHolderListener.running.booleanValue() && !Thread.currentThread().isInterrupted()) {
                            try {
                                Thread.sleep(2000L);
                            } catch (InterruptedException var2) {
                                ;
                            }
                        }

                    }
                }, "Dubbo-Holder");
                holdThread.setDaemon(false);
                holdThread.start();
            }
        }

        if(event instanceof ContextClosedEvent) {
            running = Boolean.FALSE;
            if(null != holdThread) {
                holdThread.interrupt();
                holdThread = null;
            }
        }

    }

    static {
        running = Boolean.FALSE;
    }
}
