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

import java.util.Collections;

public class OrderedArrayListTester {

	public static void main(String[] args) {
		boolean failure = false;

		failure = constructorTester() || failure;
		failure = nullTester() || failure;
		failure = addTester(1000) || failure;
		failure = addAtIndexTester(1000) || failure;
		failure = failure || setTester(1000);

		TesterMethods.overall(failure);
	}

	public static boolean constructorTester() {
		TesterMethods.tester("constructorTester");
		boolean fail = false;

		try {
			OrderedArrayList<Integer> def = new OrderedArrayList<Integer>();
			//TesterMethods.passMessage("default tester");
		} catch (Exception e) {
			e.printStackTrace();
			fail = true;
			TesterMethods.errorMessage("default tester");
		}

		try {
			int randSize = TesterMethods.randInt(8);
			OrderedArrayList<Integer> rand = new OrderedArrayList<Integer>(randSize);
			//TesterMethods.passMessage("randSize tester");
		} catch (Exception e) {
			e.printStackTrace();
			fail = true;
			TesterMethods.errorMessage("randSize tester");
		}

		try {
			NoNullArrayList<Integer> def = new OrderedArrayList<Integer>();
			//TesterMethods.passMessage("default tester");
		} catch (Exception e) {
			e.printStackTrace();
			fail = true;
			TesterMethods.errorMessage("Improper class extension.");
		}

		TesterMethods.methodMessage("constructorTester", fail);
		return fail;
	}

	public static boolean nullTester() {
		TesterMethods.tester("nullTester");
		boolean fail = false;

		OrderedArrayList<Integer> test = new OrderedArrayList<Integer>();

		for (int n = 0; n < 10; n++) {
			test.add(n);
		}

		try {
			test.add(null);
			fail = true;
			TesterMethods.errorMessage("add(value) added a null");
		} catch (IllegalArgumentException e) {
			//TesterMethods.passMessage("add(value) didn't add a null");
		}

		try {
			for (int i = 0; i < 100; i++) {
				test.add(TesterMethods.randInt(10), null);
				fail = true;
				TesterMethods.errorMessage(i, "to not be here", "add(index, value) added a null");
			}
		} catch (IllegalArgumentException e) {
			//TesterMethods.passMessage("add(index, value) didn't add a null");
		}

		try {
			for (int i = 0; i < 100; i++) {
				test.set(TesterMethods.randInt(10), null);
				fail = true;
				TesterMethods.errorMessage(i, "to not be here", "set(index, value) added a null");
			}
		} catch (IllegalArgumentException e) {
			//TesterMethods.passMessage("set(index, value) didn't add a null");
		}

		TesterMethods.methodMessage("nullTester", fail);
		return fail;
	}

	public static boolean addTester(int tests) {
		TesterMethods.tester("addTester");
		boolean fail = false;
		ArrayList<Integer> expected = new ArrayList<Integer>();
		OrderedArrayList<Integer> subject = new OrderedArrayList<Integer>();


		for (int test = 0; test < tests; test++) {
			Integer value = TesterMethods.randInt((int)-1e6, (int)1e6);
			if (TesterMethods.randInt(10) == 0) {
				value = null;
				try {
					fail = subject.add(value);
					fail = true;
					TesterMethods.errorMessage(test, "not to be here", "added null");
				} catch (IllegalArgumentException e) {
					//TesterMethods.passMessage(test);
				}
			} else {
				expected.add(value);
				boolean output = subject.add(value);
				if (output) {
					//TesterMethods.passMessage(test);
				} else {
					fail = true;
					TesterMethods.errorMessage(test, "true", Boolean.toString(output));
				}
			}
			//System.out.println(subject.toString());
		}

		Collections.sort(expected);

		if (expected.equals(subject)) {
			//TesterMethods.passMessage("sorting algo");
		} else {
			fail = true;
			TesterMethods.errorMessage("sorting algo", expected.toString(), subject.toString());
		}

		TesterMethods.methodMessage("addTester", fail);
		return fail;
	}

	public static boolean addAtIndexTester(int tests) {
		TesterMethods.tester("addAtIndexTester");
		boolean fail = false;
		ArrayList<Integer> expected = new ArrayList<Integer>();
		OrderedArrayList<Integer> subject = new OrderedArrayList<Integer>();


		for (int test = 0; test < tests; test++) {
			Integer value = TesterMethods.randInt((int)-1e6, (int)1e6);
			int index = TesterMethods.randInt(subject.size());
			if (TesterMethods.randInt(10) == 0) {
				value = null;
				try {
					subject.add(index, value);
					fail = true;
					TesterMethods.errorMessage(test, "not to be here", "added null");
				} catch (IllegalArgumentException e) {
					//TesterMethods.passMessage(test);
				}
			} else {
				expected.add(index, value);
				subject.add(index, value);
			}
			//System.out.println(subject.toString());
		}

		Collections.sort(expected);

		if (expected.equals(subject)) {
			//TesterMethods.passMessage("sorting algo");
		} else {
			fail = true;
			TesterMethods.errorMessage("sorting algo", expected.toString(), subject.toString());
		}

		TesterMethods.methodMessage("addAtIndexTester", fail);
		return fail;
	}

	public static boolean setTester(int tests) {
		TesterMethods.tester("setTester");
		boolean fail = false;
		ArrayList<Integer> expected = new ArrayList<Integer>();
		OrderedArrayList<Integer> subject = new OrderedArrayList<Integer>();

		for (int test = 0; test < tests; test++) {
			Integer value = TesterMethods.randInt((int)-1e6, (int)1e6);
			expected.add(value);
			subject.add(value);
		}

		Collections.sort(expected);

		if (!expected.equals(subject)) {
			throw new IllegalStateException("Expected and subject unequal before mutations.");
		}

		for (int test = 0; test < tests; test++) {
			Integer value = TesterMethods.randInt((int)-1e6, (int)1e6);
			int index = TesterMethods.randInt(subject.size());
			if (TesterMethods.randInt(10) == 0) {
				value = null;
				try {
					subject.set(index, value);
					fail = true;
					TesterMethods.errorMessage(test, "not to be here", "set null");
				} catch (IllegalArgumentException e) {
					//TesterMethods.passMessage(test);
				}
			} else {
				Collections.sort(expected);
				int expectedReturn = expected.set(index, value);
				int returned = subject.set(index, value);
				if (returned == expectedReturn) {
					//TesterMethods.passMessage(test);
				} else {
					fail = true;
					TesterMethods.errorMessage(test, Integer.toString(expectedReturn), Integer.toString(returned));
				}
			}
			//System.out.println(subject.toString());
		}

		Collections.sort(expected);

		if (expected.equals(subject)) {
			//TesterMethods.passMessage("sorting algo");
		} else {
			fail = true;
			TesterMethods.errorMessage("sorting algo", expected.toString(), subject.toString());
		}

		TesterMethods.methodMessage("setTester", fail);
		return fail;
	}

}

public class TesterMethods {

	public static void overall(boolean failure) {
		System.out.println("\n ~~~ Overall Result ~~~");
		if (failure) {
			System.out.println("Coal for you!");
		} else {
			System.out.println("Happy Holidays!");
		}
	}

	public static void tester(String test) {
		System.out.println("\n ~~~ " + test + " ~~~\n");
	}

	public static int randInt(int end) {
		return randInt(0, end);
	}

	public static int randInt(int start, int end) {
		return (int)(Math.random()* (end - start)) + start;
	}

	public static void passMessage(int testCase) {
		System.out.println("Test case " + testCase + " passed.");
	}

	public static void passMessage(String testCase) {
		System.out.println("Test case " + testCase + " passed.");
	}

	public static void errorMessage(int testCase) {
		System.out.println("Test case " + testCase + " failed.");
	}

	public static void errorMessage(String testCase) {
		System.out.println("Test case " + testCase + " failed.");
	}

	public static void errorMessage(String testCase, String expected, String actual) {
		errorMessage(testCase);
		System.out.println("Expected: " + expected);
		System.out.println("Actual:" + actual);
	}

	public static void errorMessage(int testCase, String expected, String actual) {
		errorMessage(Integer.toString(testCase), expected, actual);
	}

	public static void methodMessage(String method, boolean fail) {
		if (fail) {
			System.out.println("\nAt least one test case failed for " + method);
		} else {
			System.out.println(method + " PASSED");
		}
	}

	public static int[] integerToIntArray(Integer[] array) {
		int[] newArray = new int[array.length];

		for (int i = 0; i < array.length; i++) {
			newArray[i] = array[i];
		}

		return newArray;
	}

}
