package bench.cpu;

import bench.IBenchmark;

public class CPUFixedVsFloatingPoint implements IBenchmark {

	private int result;
	private int size;

	@Override
	public void initialize(Object ... params) {
		this.size = (Integer)params[0];	
	}

	/* explanation
- Integer division (i / 256)

Both operands are int.

CPU uses its integer ALU.

Computes only the whole‐number quotient (⌊i÷256⌋), dropping any fraction.

Very fast, but no decimals.

- Floating-point division ((double)i / 256.0)

At least one operand is double.

CPU uses its floating-point unit (FPU).

Computes the full decimal result (e.g. 1.171875).

Slower than integer division, but preserves fractions.*/
	@Override
	public void warmUp() {
		// We run both the fixed‐ and floating‐point versions
		// so the JIT has a chance to compile both loops before timing.
		for (int i = 0; i < size; ++i) {
			// fixed‐point warm-up
			result = i / 256;
			// floating‐point warm-up (cast back to int since result is an int)
			result = (int)((double)i / 256.0);
			//shift based fixed point
			result = i >> 8;
		}
	}


	@Override
	public void run() {

	}

	@Override
	public void run(Object ...options) {
		result = 0;

		switch ((NumberRepresentation) options[0]) {
			case FLOATING:
				for (int i = 0; i < size; ++i) {
					// do the floating‐point division, then cast to int
					result += (int)((double)i / 256.0);
				}
				break;

//			case FIXED:
//				for (int i = 0; i < size; ++i) {
//					// integer division
//					result += i / 256;
//				}
			case FIXED: //WITH RIGHT SHIFT INSTEAD OF /
				for (int i = 0; i < size; ++i){
					result += i >>8;
				}

				break;

			default:
				throw new IllegalArgumentException("Unknown mode: " + options[0]);
		}
	}


	@Override
	public void cancel() {
		
	}

	@Override
	public void clean() {
	}

	@Override
	public String getResult() {
		return String.valueOf(result);
	}

}
