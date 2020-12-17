import java.util.ArrayList;
import java.util.Arrays;

public class NoNullArrayListTester {

	public static void main(String[] args) {
		boolean failure = false;

		failure = constructorTester() || failure;
		failure = addTester(1000) || failure;
		failure = addAtIndexTester(1000) || failure;
		failure = setTester(1000) || failure;

		TesterMethods.overall(failure);
	}

	public static boolean constructorTester() {
		TesterMethods.tester("constructorTester");
		boolean fail = false;

		try {
			NoNullArrayList<Object> def = new NoNullArrayList<Object>();
			//TesterMethods.passMessage("default tester");
		} catch (Exception e) {
			e.printStackTrace();
			fail = true;
			TesterMethods.errorMessage("default tester");
		}

		try {
			int randSize = TesterMethods.randInt(8);
			NoNullArrayList<Object> rand = new NoNullArrayList<Object>(randSize);
			//TesterMethods.passMessage("randSize tester");
		} catch (Exception e) {
			e.printStackTrace();
			fail = true;
			TesterMethods.errorMessage("randSize tester");
		}

		TesterMethods.methodMessage("constructorTester", fail);
		return fail;
	}

	public static boolean addTester(int tests) {
		TesterMethods.tester("addTester");
		boolean fail = false;
		NoNullArrayList<Integer> noNulls = new NoNullArrayList<Integer>();

		int nulls = 0;
		ArrayList<Integer> all = new ArrayList<Integer>();

		for (int test = 0; test < tests; test++) {
			if (TesterMethods.randInt(10) == 0) {
				nulls++;
				all.add(null);
				try {
					noNulls.add(null);
					fail = true;
					TesterMethods.errorMessage("Should not be able to insert null.");
				} catch (IllegalArgumentException e) {
					//TesterMethods.passMessage(test);
				}
			} else {
				Integer randval = TesterMethods.randInt(-10, 10);
				all.add(randval);
				fail = fail || !noNulls.add(randval);
				//TesterMethods.passMessage(test);
			}
		}

		int nullOffset = 0;

		for (int index = 0; index < all.size(); index++) {
			if (all.get(index) == null) {
				nullOffset += 1;
			} else if (all.get(index) == noNulls.get(index - nullOffset)) {
				//TesterMethods.passMessage(index);
			} else {
				fail = true;
				TesterMethods.errorMessage(Integer.toString(index), Integer.toString(all.get(index)), Integer.toString(noNulls.get(index - nullOffset)));
			}
		}

		if ((tests - nulls) == noNulls.size()) {
			//TesterMethods.passMessage("Amount of elements");
		} else {
			fail  = true;
			TesterMethods.errorMessage("Amount of elements does not line up",  Integer.toString(tests - nulls), Integer.toString(noNulls.size()));
		}



		TesterMethods.methodMessage("addTester", fail);
		return fail;
	}

	public static boolean addAtIndexTester(int tests) {
		TesterMethods.tester("addAtIndexTester");
		boolean fail = false;
		NoNullArrayList<Integer> noNulls = new NoNullArrayList<Integer>();

		int nulls = 0;
		ArrayList<Integer> all = new ArrayList<Integer>();

		for (int test = 0; test < tests; test++) {
			if (TesterMethods.randInt(10) == 0) {
				nulls++;
				all.add(0, null);
				try {
					noNulls.add(0, null);
					fail = true;
					TesterMethods.errorMessage("Should not be able to insert null.");
				} catch (IllegalArgumentException e) {
					//TesterMethods.passMessage(test);
				}
			} else {
				Integer randval = TesterMethods.randInt(-10, 10);
				all.add(0, randval);
				noNulls.add(0,randval);
				//TesterMethods.passMessage(test);
			}
		}

		int nullOffset = 0;

		for (int index = 0; index < all.size(); index++) {
			if (all.get(index) == null) {
				nullOffset += 1;
			} else if (all.get(index) == noNulls.get(index - nullOffset)) {
				//TesterMethods.passMessage(index);
			} else {
				fail = true;
				TesterMethods.errorMessage(Integer.toString(index), Integer.toString(all.get(index)), Integer.toString(noNulls.get(index - nullOffset)));
			}
		}

		if ((tests - nulls) == noNulls.size()) {
			//TesterMethods.passMessage("Amount of elements");
		} else {
			fail  = true;
			TesterMethods.errorMessage("Amount of elements does not line up",  Integer.toString(tests - nulls), Integer.toString(noNulls.size()));
		}



		TesterMethods.methodMessage("addAtIndexTester", fail);
		return fail;
	}

	public static boolean setTester(int tests) {
		TesterMethods.tester("setTester");
		boolean fail = false;

		NoNullArrayList<Integer> noNulls = new NoNullArrayList<Integer>();
		ArrayList<Integer> all = new ArrayList<Integer>();

		for (int val = 0; val < tests; val++) {
			int n = TesterMethods.randInt(-val, val);
			noNulls.add(n);
			all.add(n);
		}

		ArrayList<Integer> original = new ArrayList<>(all);

		for (int test = 0; test < tests; test++) {
			if (TesterMethods.randInt(10) == 0) {
				try {
					all.set(test, null);
					noNulls.set(test, null);
					TesterMethods.errorMessage("Should not be able to set null");
				} catch (IllegalArgumentException e) {
					//TesterMethods.passMessage(test);
				}
			} else {
				int randval = TesterMethods.randInt(-1000000, 1000000);
				int expectedReturn = all.set(test, randval);
				int returned = noNulls.set(test, randval);
				if (expectedReturn == returned) {
					//TesterMethods.passMessage(test);
				} else {
					TesterMethods.errorMessage(test, Integer.toString(expectedReturn), Integer.toString(returned));
				}
				//TesterMethods.passMessage(test);
			}
		}

		for (int test = 0; test < tests; test++) {
			Integer noNullsVal = noNulls.get(test);
			Integer expectedVal = all.get(test);
			if (expectedVal == null) {
				expectedVal = original.get(test);
			}

			if (noNullsVal.equals(expectedVal)) {
				//TesterMethods.passMessage(test);
			} else {
				fail = true;
				TesterMethods.errorMessage(test, Integer.toString(expectedVal), Integer.toString(noNullsVal));
			}
		}

		TesterMethods.methodMessage("setTester", fail);
		return fail;
	}

}
