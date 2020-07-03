/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.lab08.Jetty.repository;

import edu.ucr.rp.lab08.Jetty.domain.Message;
import java.util.List;
import java.util.Vector;

public class MessageRepository {

    private List<Message> messages;

    private static MessageRepository instance;

    private MessageRepository() {
        messages = new Vector<>();
    }

    public static MessageRepository getInstance() {
        if (instance == null) instance = new MessageRepository();
        return instance;
    }


    public synchronized void add(Message message) {
        messages.add(message);
    }

    public List<Message> all() {
        return messages;
    }
}
