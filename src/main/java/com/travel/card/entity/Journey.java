package com.travel.card.entity;

import com.travel.card.constants.Transport;
import com.travel.card.constants.Zone;
import com.travel.card.exception.CustomException;

public class Journey {
    private Transport transport;
    private Card card;
    private Zone startPoint;
    private Zone endPoint;

    public Zone getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Transport transport, Zone startPoint, Card card) {
        try {
            Fare.validate(transport, card);
            Fare.chargeMax(transport, card);
        } catch (CustomException e) {
            System.out.println(e.getMessage());
        }
        this.transport = transport;
        this.card = card;
        this.startPoint = startPoint;
    }

    public Zone getEndPoint() {
        return endPoint;
    }

    public void setEndPoint( Zone endPoint) throws CustomException {
        this.endPoint = endPoint;
        Fare.charge(transport,this, card);
    }
}

