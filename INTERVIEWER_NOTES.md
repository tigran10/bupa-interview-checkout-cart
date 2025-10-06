
# Interviewer crib sheet (simple version)

**Signals**
- Clean handling of `Integer` null vs `int`.
- BigDecimal correctness; no doubles.
- Simple, readable subtotal / total code.
- `DiscountRule` as an interface (could be lambda-friendly).
- Edge-case tests added by candidate.

**Discussion**
- Why functional interface rules vs class inheritance?
- Rule ordering, exclusivity, and stacking.
- Where to keep invariants and validate inputs.
- How to evolve to currencies/rounding/persistence without over-engineering.
