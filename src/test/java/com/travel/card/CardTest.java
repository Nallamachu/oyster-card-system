package com.travel.card;

import com.travel.card.entity.Card;
import org.junit.jupiter.api.Test;

class CardTest {
    @Test
    void testFareValidateException() {
        Card card = new Card(30f);
        card.validateFare(31);
    }

    @Test
    void testOutException() {
        Card card = new Card(30f);
        card.out(31);
    }
}
