package com.travel.card;

import com.travel.card.constants.Transport;
import com.travel.card.constants.Zone;
import com.travel.card.entity.Card;
import com.travel.card.entity.Fare;
import com.travel.card.entity.Journey;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FareTest {

    @Test
    void testValidateBusException() {
        Card card = new Card(Fare.BUS_FARE - 1f);
        Fare.validate(Transport.BUS, card);
    }

    @Test
    void testValidateTubeFareException() {
        Card card = new Card(Fare.BASIC_TUBE_FARE - 1f);
        Fare.validate(Transport.TUBE, card);
    }

    @Test
    void testChargeMaxTube() {
        Card card = new Card(Fare.BASIC_TUBE_FARE);
        Fare.chargeMax(Transport.TUBE, card);
        assertEquals(0f, card.getBalance(), 0.0f);
    }

    @Test
    void testChargeMaxBus() {
        Card card = new Card(Fare.BUS_FARE);
        Fare.chargeMax(Transport.BUS, card);
        assertEquals(0f, card.getBalance(), 0.0f);
    }

    @Test
    void testChargeBus() {
        Card card = new Card(Fare.BUS_FARE);
        Journey journeyBusEarlToChelsea = new Journey();
        journeyBusEarlToChelsea.setStartPoint(Transport.BUS, null, card);
        journeyBusEarlToChelsea.setEndPoint(null);
        Fare.charge(Transport.BUS,journeyBusEarlToChelsea, card);
        assertEquals(0f, card.getBalance(), 0.0f);
    }

    @Test
    void testChargeTubZoneOne() {
        Card card = new Card(Fare.BASIC_TUBE_FARE);
        Journey journeyBusEarlToChelsea = new Journey();
        journeyBusEarlToChelsea.setStartPoint(Transport.TUBE, Zone.HOLBORN, card);
        journeyBusEarlToChelsea.setEndPoint(Zone.EARLS_COURT);
        assertEquals(Fare.BASIC_TUBE_FARE - Fare.ZONE_ONE_FARE, card.getBalance(), 0.001f);
    }

    @Test
    void testChargeTubeZoneOne() {
        Card card = new Card(Fare.BASIC_TUBE_FARE);
        Journey journeyBusEarlToChelsea = new Journey();
        journeyBusEarlToChelsea.setStartPoint(Transport.TUBE, Zone.ALDGATE, card);
        journeyBusEarlToChelsea.setEndPoint(Zone.EARLS_COURT);
        assertEquals(Fare.BASIC_TUBE_FARE - Fare.ZONE_ONE_FARE, card.getBalance(), 0.001f);
    }

    @Test
    void testChargeTubAnyZoneOutSideZoneOne() {
        Card card = new Card(Fare.BASIC_TUBE_FARE);
        Journey joruneyBusEarlToChelsea = new Journey();
        joruneyBusEarlToChelsea.setStartPoint(Transport.TUBE, Zone.HAMMERSMITH, card);
        joruneyBusEarlToChelsea.setEndPoint(Zone.EARLS_COURT);
        assertEquals(Fare.BASIC_TUBE_FARE - Fare.ANY_ZONE_OUTSIDE_ZONE_ONE_FARE, card.getBalance(), 0.001f);
    }

    @Test
    void testChargeTubTwoInZoneOne() {
        Card card = new Card(Fare.BASIC_TUBE_FARE);
        Journey journeyBusEarlToChelsea = new Journey();
        journeyBusEarlToChelsea.setStartPoint(Transport.TUBE, Zone.HAMMERSMITH, card);
        journeyBusEarlToChelsea.setEndPoint(Zone.HOLBORN);
        assertEquals(Fare.BASIC_TUBE_FARE - Fare.ANY_TWO_ZONES_INC_ZONE_ONE_FARE, card.getBalance(), 0.001f);
    }

    @Test
    void testChargeTubeTwoInZoneOne() {
        Card card = new Card(Fare.BASIC_TUBE_FARE);
        Journey journeyBusEarlToChelsea = new Journey();
        journeyBusEarlToChelsea.setStartPoint(Transport.TUBE, Zone.HAMMERSMITH, card);
        journeyBusEarlToChelsea.setEndPoint(Zone.ARSENAL);
        assertEquals(Fare.BASIC_TUBE_FARE - Fare.ANY_TWO_ZONES_INC_ZONE_ONE_FARE, card.getBalance(), 0.001f);
    }

    @Test
    void testChargeTubTwoExcZoneOne() {
        Card card = new Card(Fare.BASIC_TUBE_FARE);
        Journey journeyBusEarlToChelsea = new Journey();
        journeyBusEarlToChelsea.setStartPoint(Transport.TUBE, Zone.ARSENAL, card);
        journeyBusEarlToChelsea.setEndPoint(Zone.WIMBLEDON);
        assertEquals(Fare.BASIC_TUBE_FARE - Fare.ANY_TWO_ZONES_EXC_ZONE_ONE_FARE, card.getBalance(), 0.001f);
    }

    @Test
    void testChargeTubThreeZones() {
        Card card = new Card(Fare.BASIC_TUBE_FARE);
        Journey journeyBusEarlToChelsea = new Journey();
        journeyBusEarlToChelsea.setStartPoint(Transport.TUBE, Zone.HOLBORN, card);
        journeyBusEarlToChelsea.setEndPoint(Zone.WIMBLEDON);
        assertEquals(Fare.BASIC_TUBE_FARE - Fare.THREE_PLUS_ZONES_FAIR, card.getBalance(), 0.001f);
    }
}
