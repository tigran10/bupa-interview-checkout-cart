
package com.bupa.simple;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class SimpleCheckoutTest {

    @Test
    void add_null_quantity_means_one_and_subtotals() {
        Cart cart = new Cart();
        cart.add("A", Money.of("50.00"), null); // null -> 1
        cart.add("B", Money.of("30.00"), 2);
        assertThatThrownBy(cart::subtotal).isInstanceOf(UnsupportedOperationException.class);
        // After implementation:
        // assertThat(cart.subtotal().toString()).isEqualTo("110.00");
    }

    @Test
    void reject_non_positive_quantity() {
        Cart cart = new Cart();
        assertThatThrownBy(() -> cart.add("A", Money.of("10.00"), 0)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> cart.add("A", Money.of("10.00"), -5)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void merges_lines_by_sku() {
        Cart cart = new Cart();
        cart.add("A", Money.of("50.00"), 1);
        cart.add("A", Money.of("50.00"), 2); // merges -> qty 3

        assertThatThrownBy(cart::subtotal).isInstanceOf(UnsupportedOperationException.class);
        // After implementation: 3 * 50 = 150.00
        // assertThat(cart.subtotal().toString()).isEqualTo("150.00");
    }

    @Test
    void multiBuy_3for2_on_A() {
        Cart cart = new Cart();
        cart.add("A", Money.of("50.00"), 3);

        Checkout.DiscountRule threeForTwo = Checkout.multiBuy("A", 3, 2);
        Checkout checkout = new Checkout(List.of(threeForTwo));

        assertThatThrownBy(() -> threeForTwo.discount(cart)).isInstanceOf(UnsupportedOperationException.class);
        assertThatThrownBy(() -> checkout.total(cart)).isInstanceOf(UnsupportedOperationException.class);

        // After implementation:
        // discount = 1 * 50.00
        // total = 150.00 - 50.00 = 100.00
        // assertThat(threeForTwo.discount(cart).toString()).isEqualTo("50.00");
        // assertThat(checkout.total(cart).toString()).isEqualTo("100.00");
    }

    @Test
    void threshold_10_percent_when_subtotal_at_least_100() {
        Cart cart = new Cart();
        cart.add("A", Money.of("50.00"), 2); // 100
        cart.add("B", Money.of("30.00"), 1); // 130

        Checkout.DiscountRule tenPercentOver100 = Checkout.thresholdPercent(Money.of("100.00"), 10);
        Checkout checkout = new Checkout(List.of(tenPercentOver100));

        assertThatThrownBy(() -> tenPercentOver100.discount(cart)).isInstanceOf(UnsupportedOperationException.class);
        assertThatThrownBy(() -> checkout.total(cart)).isInstanceOf(UnsupportedOperationException.class);

        // After implementation:
        // discount = 13.00; total = 117.00
        // assertThat(tenPercentOver100.discount(cart).toString()).isEqualTo("13.00");
        // assertThat(checkout.total(cart).toString()).isEqualTo("117.00");
    }

    @Test
    void rules_compose() {
        Cart cart = new Cart();
        cart.add("A", Money.of("50.00"), 3); // 150

        Checkout.DiscountRule threeForTwo = Checkout.multiBuy("A", 3, 2); // 50 off
        Checkout.DiscountRule tenPercentOver100 = Checkout.thresholdPercent(Money.of("100.00"), 10); // 15 off

        Checkout checkout = new Checkout(List.of(threeForTwo, tenPercentOver100));

        assertThatThrownBy(() -> checkout.total(cart)).isInstanceOf(UnsupportedOperationException.class);

        // After implementation: 150 - 50 - 15 = 85
        // assertThat(checkout.total(cart).toString()).isEqualTo("85.00");
    }
}
