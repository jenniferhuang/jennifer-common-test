package com.jennifer.sockettest;

import io.socket.client.Manager;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.testng.annotations.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by IntelliJ IDEA.
 * User: com.jennifer.huang
 * Date: 3/10/2017
 */
public class ConnectionTest {




    @Test
    public void connectionTest() throws URISyntaxException,InterruptedException  {
        final BlockingQueue<Object> values = new LinkedBlockingQueue<Object>(); //******Final to keep the
//        final ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();
        Manager manager = new Manager(new URI("http://localhost:8080"));
        final Socket socket = manager.socket("/58c7af01794b7156d8732d29");

        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            public void call(Object... objects) {
                System.out.println("Connected.....");
                System.out.println("AAAAA: "+Thread.currentThread().getName() +", "+Thread.currentThread().getId()+", "+Thread.currentThread().hashCode());

                socket.on("loginSuccess", new Emitter.Listener() {
                    public void call(Object... objects) {
                        System.out.println("loginSuccess");
                        values.offer("loginSuccess-done");
                    }
                });

                socket.on("accepted", new Emitter.Listener() {
                    public void call(Object... objects) {
                        System.out.println("accepted");
                        values.offer("accepted-done");
                    }
                });

                socket.on("terminated", new Emitter.Listener() {
                    public void call(Object... objects) {
                        System.out.println("terminated");
                        values.offer("terminated-done");
                    }
                });

                socket.on("closed", new Emitter.Listener() {
                    public void call(Object... objects) {
                        System.out.println("closed");
                        System.out.println("BBBBB: "+Thread.currentThread().getName() +", "+Thread.currentThread().getId()+", "+Thread.currentThread().hashCode());
                        values.offer("close-done");
                    }
                });

            }
        });
        socket.connect();
        System.out.println(values.take());
        System.out.println(values.take());
        System.out.println(values.take());
        System.out.println(values.take());
        System.out.println("CCCCC: "+Thread.currentThread().getName() +", "+Thread.currentThread().getId()+", "+Thread.currentThread().hashCode());
        System.out.println("End");
        socket.close();
    }


    @Test
    public void connectionTest2() throws URISyntaxException,InterruptedException  {
        final BlockingQueue<Object> values = new LinkedBlockingQueue<Object>();
//        final ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();
        Manager manager = new Manager(new URI("http://localhost:8080"));
        final Socket socket = manager.socket("/58c60f30938b614b3c520c09");
        socket.connect();
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            public void call(Object... objects) {
                System.out.println("Connected.....");

            }
        });
        socket.on("terminated", new Emitter.Listener() {
            public void call(Object... objects) {
                System.out.println("terminated");
                values.offer("done");
            }
        });

        System.out.println(values.take());
        System.out.println("End");
        socket.close();
    }



}
