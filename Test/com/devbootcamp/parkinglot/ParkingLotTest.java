package com.devbootcamp.parkinglot;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by akashgupta on 6/28/16.
 */
public class ParkingLotTest {

    @Test
    public void shouldParkCarIfAvailable() {
        ParkingLot lot = new ParkingLot(2);
        ParkingToken issuedToken = lot.parkCar();
        assertNotNull(issuedToken);
    }

    @Test
    public void shouldNotParkCarIfUnAvailable() {
        ParkingLot lot = new ParkingLot(1);
        ParkingToken validToken = lot.parkCar();
        ParkingToken invalidToken = lot.parkCar();
        assertNotNull(validToken);
        assertNull(invalidToken);
    }

    @Test
    public void shouldUnParkCar() {
        ParkingLot lot = new ParkingLot(2);
        ParkingToken token = lot.parkCar();
        assertTrue(lot.unParkCar(token));
    }

    @Test
    public void shouldShowParkingAvailableAfterCarUnPark() {
        ParkingLot lot = new ParkingLot(2);
        ParkingToken token = lot.parkCar();
        lot.parkCar();
        assertNull(lot.parkCar());
        lot.unParkCar(token);
        assertNotNull(lot.parkCar());
        assertNull(lot.parkCar());
    }

    @Test
    public void shouldNotUnParkCarForTokenThatIsNotIssued() {
        ParkingLot lot = new ParkingLot(10);
        lot.parkCar();
        ParkingToken nonIssuedToken = new ParkingToken();
        assertFalse(lot.unParkCar(nonIssuedToken));
    }

}


