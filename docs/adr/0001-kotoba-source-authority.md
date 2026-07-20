# ADR 0001: Kotoba is the AHLA catalog source authority

- Status: Accepted
- Date: 2026-07-21

## Decision

`src/association_facts.kotoba` is the sole production source. The 5-Star
Promise retains complete establishment and revision dates; the profile retains
year-only `1910` and no fabricated revision. Indexed access preserves the
ordered worker-safety and sexual-harassment-prevention topics plus governance.
Unknown values and indexes return zero or typed option-none; no effects are
declared.

CI executes reference semantics, restricted JavaScript, instantiated typed
WebAssembly, and production source-authority checks. Clojure and the JVM are
compiler/test hosts only.

## Consequences

- Full, year-only, and absent date states remain distinct.
- Multi-topic entries remain complete without host sets.
