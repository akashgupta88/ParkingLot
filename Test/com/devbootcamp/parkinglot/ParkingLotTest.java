package com.devbootcamp.parkinglot;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by akashgupta on 6/28/16.
 */
public class ParkingLotTest {

    @Test
    public void shouldParkCarIfAvailable() throws ParkingLotFullException {
        ParkingLot lot = new ParkingLot(2);
        ParkingToken issuedToken = lot.parkCar(new Object());
        assertNotNull(issuedToken);
    }

    @Test (expected = ParkingLotFullException.class)
    public void shouldNotParkCarIfUnAvailable() throws ParkingLotFullException {
        ParkingLot lot = new ParkingLot(1);
        ParkingToken validToken = lot.parkCar(new Object());
        ParkingToken invalidToken = lot.parkCar(new Object());
        assertNotNull(validToken);
        assertNull(invalidToken);
    }

    @Test
    public void shouldUnParkCar() throws ParkingLotFullException {
        ParkingLot lot = new ParkingLot(2);
        ParkingToken token = lot.parkCar(new Object());
        assertTrue(lot.unParkCar(token));
    }

    @Test
    public void shouldShowParkingAvailableAfterCarUnPark() throws ParkingLotFullException {
        ParkingLot lot = new ParkingLot(2);
        ParkingToken token = lot.parkCar(new Object());
        lot.parkCar(new Object());

        boolean expectedExceptionThrown = false;
        try {
            lot.parkCar(new Object());
        } catch (ParkingLotFullException e) {
            expectedExceptionThrown = true;
        } catch (Exception ignored) {

        }
        lot.unParkCar(token);
        assertTrue(expectedExceptionThrown);
        assertNotNull(lot.parkCar(new Object()));
    }

    @Test
    public void shouldNotUnParkCarForTokenThatIsNotIssued() throws ParkingLotFullException {
        ParkingLot lot = new ParkingLot(10);
        lot.parkCar(new Object());
        ParkingToken nonIssuedToken = new ParkingToken();
        assertFalse(lot.unParkCar(nonIssuedToken));
    }

    @Test
    public void shouldNotifyOwnerWhenParkingIsFull() throws ParkingLotFullException {
        ParkingLotObserver owner = mock(ParkingLotObserver.class);
        ParkingLot lot = new ParkingLot(1);
        lot.addObserver(owner);

        lot.parkCar(new Object());

        verify(owner, atLeast(1)).receiveParkingLotFullNotification();
    }

    @Test
    public void shouldNotifyObserversWhenParkingLotIsFull() throws ParkingLotFullException {
        ParkingLotObserver owner = mock(ParkingLotObserver.class);
        ParkingLotObserver securityPersonal = mock(ParkingLotObserver.class);
        ParkingLot lot = new ParkingLot(1);
        lot.addObserver(owner);
        lot.addObserver(securityPersonal);

        lot.parkCar(new Object());

        verify(owner, atLeast(1)).receiveParkingLotFullNotification();
        verify(securityPersonal, atLeast(1)).receiveParkingLotFullNotification();
    }
}


