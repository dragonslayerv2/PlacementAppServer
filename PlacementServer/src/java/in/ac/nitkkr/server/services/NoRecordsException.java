/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.ac.nitkkr.server.services;

/**
 *
 * @author Shobhit Saxena
 */
public class NoRecordsException extends Exception {

    private final String message;

    public NoRecordsException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return getClass().getName() + " " + message;
    }
}
