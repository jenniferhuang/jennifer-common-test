package com.jennifer.multithreads;

import io.socket.client.Manager;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.testng.annotations.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.Callable;

/**
 * Created by IntelliJ IDEA.
 * User: com.jennifer.huang
 * Date: 3/10/2017
 */
public class ApiClientTask implements Callable<Boolean> {



    @Test
    public void connectionTest() throws URISyntaxException {
        Manager manager = new Manager(new URI("http://localhost:8080"));
        final Socket socket = manager.socket("/58c2739f8a31355208ca2727");

        final Boolean[] b = new Boolean[1];
//        socket.connect();
        socket.on("connection", new Emitter.Listener() {
            public void call(Object... objects) {
                System.out.println("connection");
                socket.on("accepted", new Emitter.Listener() {
                    public void call(Object... objects) {
                        b[0] =true;

                        System.out.println("terminated");
                    }
                });

            }
        });
    }

    public Boolean call() throws Exception {
        System.out.println("Api client task start to work");
        int sum =0;

        int times = 20;
        for(int i=0;i<20;i++){ //Execute 1 minute.
            Thread.sleep(1000);
            sum ++;
        }

        if(sum==times){
            System.out.println("Api client task execute finished, not be canceled");
            return true;
        }else{
            System.out.println("Api client task is still in progress");
            return false;
        }

    }
}
