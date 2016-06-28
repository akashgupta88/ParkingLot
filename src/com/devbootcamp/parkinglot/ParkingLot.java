package com.devbootcamp.parkinglot;

import java.util.LinkedList;

/**
 * Created by akashgupta on 6/28/16.
 */
public class ParkingLot {
    private int numberOfBlocks = 0;
    private LinkedList<ParkingToken> issuedTokens;

    ParkingLot(int numberOfBlocks) {
        this.numberOfBlocks = numberOfBlocks;
        issuedTokens = new LinkedList<>();
    }

    public ParkingToken parkCar() {
        if (isParkingAvailable()) {
            ParkingToken token = new ParkingToken();
            issuedTokens.add(token);
            return token;
        }
        return null;
    }

    public boolean unParkCar(ParkingToken token) {
        return issuedTokens.remove(token);
    }

    private boolean isParkingAvailable() {
        return issuedTokens.size() < numberOfBlocks;
    }
}
