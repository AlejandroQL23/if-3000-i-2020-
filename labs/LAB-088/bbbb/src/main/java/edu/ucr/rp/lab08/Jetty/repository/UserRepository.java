/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.lab08.Jetty.repository;

import edu.ucr.rp.lab08.Jetty.domain.User;
import java.util.HashSet;
import java.util.Set;

public class UserRepository {

    private Set<User> users;

    private static UserRepository instance;

    private UserRepository() {
        users = new HashSet<>();
    }

    public static UserRepository getInstance() {
        if (instance == null) instance = new UserRepository();
        return instance;
    }


    public synchronized void add(User user) {
        users.add(user);
    }

    public Set<User> all() {
        return users;
    }

    public User findByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }
}