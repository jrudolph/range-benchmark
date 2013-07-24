Current results: (old = 2.10.2 Range)

```
Test: up
First: 'paulp' Second: 'old'

Benchmark comparison (in 4.231 s)
Significantly different (p ~= 0)
  Time ratio:    1.46922   95% CI 1.44513 - 1.49330   (n=20)
    First     435.1 ns   95% CI 429.2 ns - 440.9 ns
    Second    639.2 ns   95% CI 633.3 ns - 645.1 ns

Test: up
First: 'paulp' Second: 'while'

Benchmark comparison (in 3.413 s)
Significantly different (p ~= 0.0250)
  Time ratio:    1.00909   95% CI 1.00122 - 1.01696   (n=20)
    First     431.4 ns   95% CI 429.0 ns - 433.8 ns
    Second    435.4 ns   95% CI 433.0 ns - 437.7 ns

Test: up
First: 'old' Second: 'while'

Benchmark comparison (in 4.121 s)
Significantly different (p ~= 0)
  Time ratio:    0.70508   95% CI 0.69977 - 0.71038   (n=20)
    First     613.7 ns   95% CI 611.0 ns - 616.4 ns
    Second    432.7 ns   95% CI 430.0 ns - 435.4 ns

Test: up
First: 'sum' Second: 'while'

Benchmark comparison (in 4.074 s)
Significantly different (p ~= 0)
  Time ratio:    0.02406   95% CI 0.02359 - 0.02454   (n=20)
    First     18.58 us   95% CI 18.36 us - 18.81 us
    Second    447.2 ns   95% CI 440.2 ns - 454.1 ns

Test: up
First: 'paulp sum' Second: 'while'

Benchmark comparison (in 3.746 s)
Significantly different (p ~= 0)
  Time ratio:    0.02745   95% CI 0.02649 - 0.02841   (n=20)
    First     16.16 us   95% CI 15.79 us - 16.53 us
    Second    443.7 ns   95% CI 432.1 ns - 455.3 ns

Test: up nested
First: 'old' Second: 'while'

Benchmark comparison (in 4.447 s)
Significantly different (p ~= 0)
  Time ratio:    0.48740   95% CI 0.48402 - 0.49078   (n=20)
    First     9.108 us   95% CI 9.064 us - 9.152 us
    Second    4.439 us   95% CI 4.417 us - 4.461 us

Test: up nested
First: 'paulp' Second: 'while'

Benchmark comparison (in 5.142 s)
Significantly different (p ~= 0)
  Time ratio:    0.51662   95% CI 0.50234 - 0.53091   (n=20)
    First     8.644 us   95% CI 8.472 us - 8.816 us
    Second    4.466 us   95% CI 4.380 us - 4.552 us

Test: up nested
First: 'sum' Second: 'while'

Benchmark comparison (in 4.196 s)
Significantly different (p ~= 0)
  Time ratio:    0.01701   95% CI 0.01685 - 0.01716   (n=20)
    First     260.3 us   95% CI 258.5 us - 262.0 us
    Second    4.426 us   95% CI 4.399 us - 4.453 us
```
