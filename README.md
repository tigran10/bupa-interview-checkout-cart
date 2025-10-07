
# Simple Checkout Kata

**Goal:** tiny, clean, 45‑min pairing focused on OOP choices, clean code, testing.

Production files:
- `Money` (value object)
- `Cart` (holds lines)
- `Checkout` (applies discount rules via a tiny functional interface)

## Scenario
Cart:
You scan items by SKU (`String`) with a unit price. The cart computes a subtotal.

Checkout:
`Checkout` then applies a list of `DiscountRule`s to compute a final total.

## Your Tasks
1. Implement the TODOs in `Cart` and `Checkout`:
   - `Cart.add(String sku, Money unitPrice, Integer quantity)` (treat `null` as 1; reject non‑positive)
   - `Cart.subtotal()`
   - `Checkout.total()`
2. Implement two discounts as static factories in `Checkout` (no extra classes!):
   - `multiBuy(String sku, int n, int m)` — “3 for 2” style discount
   - `thresholdPercent(Money threshold, int percent)` — e.g., 10% off when subtotal ≥ £100
3. Make the tests pass and add **one extra** edge‑case test of your own.


## Run
```bash
mvn -q test
```
