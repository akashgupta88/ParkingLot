package com.devbootcamp.parkinglot;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by akashgupta on 6/28/16.
 */
public class ParkingLot {
    private int numberOfBlocks = 0;
    private LinkedList<ParkingToken> issuedTokens;
    private List<ParkingLotObserver> parkingLotObserverList = new ArrayList<>();

    ParkingLot(int numberOfBlocks) {
        this.numberOfBlocks = numberOfBlocks;
        issuedTokens = new LinkedList<>();
    }

    public ParkingToken parkCar(Object car) throws ParkingLotFullException {
        if (isParkingAvailable()) {
            ParkingToken token = new ParkingToken();
            issuedTokens.add(token);

            if (!isParkingAvailable()) {
                notifyParkingLotObservers();
            }

            return token;
        } else {
            throw new ParkingLotFullException();
        }
    }

    public boolean unParkCar(ParkingToken token) {
        return issuedTokens.remove(token);
    }

    public boolean isParkingAvailable() {
        return issuedTokens.size() < numberOfBlocks;
    }

    private void notifyParkingLotObservers() {
        parkingLotObserverList.forEach(ParkingLotObserver::receiveParkingLotFullNotification);
    }

    public void addObserver(ParkingLotObserver parkingLotObserver) {
        parkingLotObserverList.add(parkingLotObserver);
    }
}


