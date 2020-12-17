import java.util.ArrayList;
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
