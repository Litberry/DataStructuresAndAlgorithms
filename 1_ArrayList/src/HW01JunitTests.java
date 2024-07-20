import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;

public class HW01JunitTests {

    private ArrayList<String> list;
    private ArrayList<String> partialList;
    private ArrayList<String> filledList;

    @Before
    public void setUp() {
        list = new ArrayList<>();

        // The setup relies on your addToBack() method being reliable!
        partialList = new ArrayList<>();
        partialList.addToBack("Monkey");
        partialList.addToBack("Cat");
        partialList.addToBack("Luna");
        partialList.addToBack("Crunch");

        // The setup relies on your addToBack() method being reliable!
        filledList = new ArrayList<>();
        filledList.addToBack("Spartan");
        filledList.addToBack("Homestead");
        filledList.addToBack("Poof");
        filledList.addToBack("Citrus");
        filledList.addToBack("Acropolis");
        filledList.addToBack("Aragon");
        filledList.addToBack("Bread");
        filledList.addToBack("Madtown");
        filledList.addToBack("Grey");
    }






    // -------- addToBack() tests --------
    // Preconditions:
    // - List can be empty, partially filled, or filled
    //
    // Exceptions:
    // - Data can be valid or invalid (non-null or null)
    //
    // Postconditions:
    // - Resulting array must have all items in the correct order
    // - Size must be updated
    // - Resizing must occur if necessary (and multiply length by 2!)

    @Test(expected = IllegalArgumentException.class)
    public void testAddToBackEmptyListInvalidData() {
        // This extra wrapping is required so that I ensure the backing array
        // and size were NOT modified.
        IllegalArgumentException exception = null;
        try {
            list.addToBack(null);
        } catch (IllegalArgumentException e) {
            exception = e;
        } finally {
            String[] expected = new String[9];
            assertArrayEquals(expected, list.getBackingArray());
            assertEquals(0, list.size());
        }

        if (exception != null) {
            throw exception;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddToBackPartiallyFilledListInvalidData() {
        // This extra wrapping is required so that I ensure the backing array
        // and size were NOT modified.
        IllegalArgumentException exception = null;
        try {
            partialList.addToBack(null);
        } catch (IllegalArgumentException e) {
            exception = e;
        } finally {
            String[] expected = new String[] {"Monkey", "Cat", "Luna", "Crunch", null, null, null, null, null};
            assertArrayEquals(expected, partialList.getBackingArray());
            assertEquals(4, partialList.size());
        }

        if (exception != null) {
            throw exception;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddToBackFilledListInvalidData() {
        // This extra wrapping is required so that I ensure the backing array
        // and size were NOT modified.
        IllegalArgumentException exception = null;
        try {
            filledList.addToBack(null);
        } catch (IllegalArgumentException e) {
            exception = e;
        } finally {
            String[] expected = new String[]{"Spartan", "Homestead", "Poof", "Citrus", "Acropolis",
                "Aragon", "Bread", "Madtown", "Grey"};
            assertArrayEquals(expected, filledList.getBackingArray());
            assertEquals(9, filledList.size());
        }

        if (exception != null) {
            throw exception;
        }
    }

    @Test
    public void testAddToBackEmptyListValidData() {
        list.addToBack("Tremor");

        String[] expected = new String[9];
        expected[0] = "Tremor";
        assertArrayEquals(expected, list.getBackingArray());
        assertEquals(1, list.size());
    }

    @Test()
    public void testAddToBackPartiallyFilledListValidData() {
        partialList.addToBack("Orbit");
        String[] expected = new String[] {"Monkey", "Cat", "Luna", "Crunch", "Orbit", null, null, null, null};
        assertArrayEquals(expected, partialList.getBackingArray());
        assertEquals(5, partialList.size());
    }

    @Test()
    public void testAddToBackFilledListVData() {
        filledList.addToBack("Orbit");
        String[] expected = new String[]{"Spartan", "Homestead", "Poof", "Citrus", "Acropolis",
            "Aragon", "Bread", "Madtown", "Grey", "Orbit", null, null, null, null, null, null, null, null};
        assertArrayEquals(expected, filledList.getBackingArray());
        assertEquals(10, filledList.size());
    }






    // -------- addToFront() tests --------
    // Preconditions:
    // - List can be empty, partially filled, or filled
    //
    // Exceptions:
    // - Data can be valid or invalid (non-null or null)
    //
    // Postconditions:
    // - Resulting array must have all items in the correct order
    // - Size must be updated
    // - Resizing must occur if necessary (and multiply length by 2!)

    @Test(expected = IllegalArgumentException.class)
    public void testAddToFrontEmptyListInvalidData() {
        // This extra wrapping is required so that I ensure the backing array
        // and size were NOT modified.
        IllegalArgumentException exception = null;
        try {
            list.addToFront(null);
        } catch (IllegalArgumentException e) {
            exception = e;
        } finally {
            String[] expected = new String[9];
            assertArrayEquals(expected, list.getBackingArray());
            assertEquals(0, list.size());
        }

        if (exception != null) {
            throw exception;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddToFrontPartiallyFilledListInvalidData() {
        // This extra wrapping is required so that I ensure the backing array
        // and size were NOT modified.
        IllegalArgumentException exception = null;
        try {
            partialList.addToFront(null);
        } catch (IllegalArgumentException e) {
            exception = e;
        } finally {
            String[] expected = new String[] {"Monkey", "Cat", "Luna", "Crunch", null, null, null, null, null};
            assertArrayEquals(expected, partialList.getBackingArray());
            assertEquals(4, partialList.size());
        }

        if (exception != null) {
            throw exception;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddToFrontFilledListInvalidData() {
        // This extra wrapping is required so that I ensure the backing array
        // and size were NOT modified.
        IllegalArgumentException exception = null;
        try {
            filledList.addToFront(null);
        } catch (IllegalArgumentException e) {
            exception = e;
        } finally {
            String[] expected = new String[]{"Spartan", "Homestead", "Poof", "Citrus", "Acropolis",
                "Aragon", "Bread", "Madtown", "Grey"};
            assertArrayEquals(expected, filledList.getBackingArray());
            assertEquals(9, filledList.size());
        }

        if (exception != null) {
            throw exception;
        }
    }

    @Test
    public void testAddToFrontEmptyListValidData() {
        list.addToFront("Tremor");

        String[] expected = new String[9];
        expected[0] = "Tremor";
        assertArrayEquals(expected, list.getBackingArray());
        assertEquals(1, list.size());
    }

    @Test()
    public void testAddToFrontPartiallyFilledListValidData() {
        partialList.addToFront("Orbit");
        String[] expected = new String[] {"Orbit", "Monkey", "Cat", "Luna", "Crunch", null, null, null, null};
        assertArrayEquals(expected, partialList.getBackingArray());
        assertEquals(5, partialList.size());
    }

    @Test()
    public void testAddToFrontFilledListVData() {
        filledList.addToFront("Orbit");
        String[] expected = new String[]{"Orbit", "Spartan", "Homestead", "Poof", "Citrus", "Acropolis",
            "Aragon", "Bread", "Madtown", "Grey", null, null, null, null, null, null, null, null};
        assertArrayEquals(expected, filledList.getBackingArray());
        assertEquals(10, filledList.size());
    }






    // ---------- addAtIndex() tests ----------
    // Preconditions:
    // - List can be empty, partially filled, or filled
    //
    // Exceptions:
    // - Data can be valid or invalid (null or non-null)
    // - Index can be valid or invalid ( -1, 0, 1, size-1, size, size+1 )
    //   This checks the min and max, and one on either side of each
    //
    // Postconditions:
    // - Resulting array needs to have all items in the same order
    // - Size must be updated
    // - Resizing must occur (and multiply length by 2!)

    // EMPTY LIST TESTS
    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddAtIndexEmptyListIndexTooSmallValidData() {
        // This extra wrapping is required so that I ensure the backing array
        // and size were NOT modified.
        IndexOutOfBoundsException exception = null;
        try {
            list.addAtIndex(-1, "Tremor");
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        } finally {
            String[] expected = new String[9];
            assertArrayEquals(expected, list.getBackingArray());
            assertEquals(0, list.size());
        }

        if (exception != null) {
            throw exception;
        }
    }

    @Test
    public void testAddAtIndexEmptyListValidIndexValidData() {
        list.addAtIndex(0, "Tremor");

        String[] expected = new String[9];
        expected[0] = "Tremor";
        assertArrayEquals(expected, list.getBackingArray());
        assertEquals(1, list.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddAtIndexEmptyListIndexTooLargeValidData() {
        // This extra wrapping is required so that I ensure the backing array
        // and size were NOT modified.
        IndexOutOfBoundsException exception = null;
        try {
            list.addAtIndex(1, "Tremor");
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        } finally {
            String[] expected = new String[9];
            assertArrayEquals(expected, list.getBackingArray());
            assertEquals(0, list.size());
        }

        if (exception != null) {
            throw exception;
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddAtIndexEmptyListValidIndexInvalidData() {
        // This extra wrapping is required so that I ensure the backing array
        // and size were NOT modified.
        IllegalArgumentException exception = null;
        try {
            list.addAtIndex(-1, "Tremor");
        } catch (IllegalArgumentException e) {
            exception = e;
        } finally {
            String[] expected = new String[9];
            assertArrayEquals(expected, list.getBackingArray());
            assertEquals(0, list.size());
        }

        if (exception != null) {
            throw exception;
        }
    }

    // PARTIALLY FILLED LIST TESTS
    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddAtIndexPartiallyFilledListIndexTooSmallValidData() {
        // This extra wrapping is required so that I ensure the backing array
        // and size were NOT modified.
        IndexOutOfBoundsException exception = null;
        try {
            partialList.addAtIndex(-1, "Orbit");
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        } finally {
            String[] expected = new String[] {"Monkey", "Cat", "Luna", "Crunch", null, null, null, null, null};
            assertArrayEquals(expected, partialList.getBackingArray());
            assertEquals(4, partialList.size());
        }

        if (exception != null) {
            throw exception;
        }
    }

    @Test()
    public void testAddAtIndexPartiallyFilledListIndexZeroValidData() {
        partialList.addAtIndex(0, "Orbit");
        String[] expected = new String[] {"Orbit", "Monkey", "Cat", "Luna", "Crunch", null, null, null, null};
        assertArrayEquals(expected, partialList.getBackingArray());
        assertEquals(5, partialList.size());
    }

    @Test()
    public void testAddAtIndexPartiallyFilledListIndexOneValidData() {
        partialList.addAtIndex(1, "Orbit");
        String[] expected = new String[] {"Monkey", "Orbit", "Cat", "Luna", "Crunch", null, null, null, null};
        assertArrayEquals(expected, partialList.getBackingArray());
        assertEquals(5, partialList.size());
    }

    @Test()
    public void testAddAtIndexPartiallyFilledListIndexThreeValidData() {
        partialList.addAtIndex(3, "Orbit");
        String[] expected = new String[] {"Monkey", "Cat", "Luna", "Orbit", "Crunch", null, null, null, null};
        assertArrayEquals(expected, partialList.getBackingArray());
        assertEquals(5, partialList.size());
    }

    @Test()
    public void testAddAtIndexPartiallyFilledListIndexFourValidData() {
        partialList.addAtIndex(4, "Orbit");
        String[] expected = new String[] {"Monkey", "Cat", "Luna", "Crunch", "Orbit", null, null, null, null};
        assertArrayEquals(expected, partialList.getBackingArray());
        assertEquals(5, partialList.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddAtIndexPartiallyFilledListIndexFiveValidData() {
        // This extra wrapping is required so that I ensure the backing array
        // and size were NOT modified.
        IndexOutOfBoundsException exception = null;
        try {
            partialList.addAtIndex(5, "Orbit");
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        } finally {
            String[] expected = new String[] {"Monkey", "Cat", "Luna", "Crunch", null, null, null, null, null};
            assertArrayEquals(expected, partialList.getBackingArray());
            assertEquals(4, partialList.size());
        }

        if (exception != null) {
            throw exception;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddAtIndexPartiallyFilledListValidIndexInvalidData() {
        // This extra wrapping is required so that I ensure the backing array
        // and size were NOT modified.
        IllegalArgumentException exception = null;
        try {
            partialList.addAtIndex(3, null);
        } catch (IllegalArgumentException e) {
            exception = e;
        } finally {
            String[] expected = new String[] {"Monkey", "Cat", "Luna", "Crunch", null, null, null, null, null};
            assertArrayEquals(expected, partialList.getBackingArray());
            assertEquals(4, partialList.size());
        }

        if (exception != null) {
            throw exception;
        }
    }

    // FILLED LIST TESTS
    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddAtIndexFilledListIndexTooSmallValidData() {
        // This extra wrapping is required so that I ensure the backing array
        // and size were NOT modified.
        IndexOutOfBoundsException exception = null;
        try {
            filledList.addAtIndex(-1, "Orbit");
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        } finally {
            String[] expected = new String[]{"Spartan", "Homestead", "Poof", "Citrus", "Acropolis",
                "Aragon", "Bread", "Madtown", "Grey"};
            assertArrayEquals(expected, filledList.getBackingArray());
            assertEquals(9, filledList.size());
        }

        if (exception != null) {
            throw exception;
        }
    }

    @Test()
    public void testAddAtIndexFilledListIndexZeroValidData() {
        filledList.addAtIndex(0, "Orbit");
        String[] expected = new String[] {"Orbit", "Spartan", "Homestead", "Poof", "Citrus", "Acropolis",
            "Aragon", "Bread", "Madtown", "Grey", null, null, null, null, null, null, null, null};
        assertArrayEquals(expected, filledList.getBackingArray());
        assertEquals(10, filledList.size());
    }

    @Test()
    public void testAddAtIndexFilledListIndexOneValidData() {
        filledList.addAtIndex(1, "Orbit");
        String[] expected = new String[]{"Spartan", "Orbit", "Homestead", "Poof", "Citrus", "Acropolis",
            "Aragon", "Bread", "Madtown", "Grey", null, null, null, null, null, null, null, null};
        assertArrayEquals(expected, filledList.getBackingArray());
        assertEquals(10, filledList.size());
    }

    @Test()
    public void testAddAtIndexFilledListIndexEightValidData() {
        filledList.addAtIndex(8, "Orbit");
        String[] expected = new String[]{"Spartan", "Homestead", "Poof", "Citrus", "Acropolis",
            "Aragon", "Bread", "Madtown", "Orbit", "Grey", null, null, null, null, null, null, null, null};
        assertArrayEquals(expected, filledList.getBackingArray());
        assertEquals(10, filledList.size());
    }

    @Test()
    public void testAddAtIndexFilledListIndexNineValidData() {
        filledList.addAtIndex(9, "Orbit");
        String[] expected = new String[]{"Spartan", "Homestead", "Poof", "Citrus", "Acropolis",
            "Aragon", "Bread", "Madtown", "Grey", "Orbit", null, null, null, null, null, null, null, null};
        assertArrayEquals(expected, filledList.getBackingArray());
        assertEquals(10, filledList.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddAtIndexFilledListIndexTenValidData() {
        // This extra wrapping is required so that I ensure the backing array
        // and size were NOT modified.
        IndexOutOfBoundsException exception = null;
        try {
            filledList.addAtIndex(10, "Orbit");
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        } finally {
            String[] expected = new String[]{"Spartan", "Homestead", "Poof", "Citrus", "Acropolis",
                "Aragon", "Bread", "Madtown", "Grey"};
            assertArrayEquals(expected, filledList.getBackingArray());
            assertEquals(9, filledList.size());
        }

        if (exception != null) {
            throw exception;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddAtIndexFilledListValidIndexInvalidData() {
        // This extra wrapping is required so that I ensure the backing array
        // and size were NOT modified.
        IllegalArgumentException exception = null;
        try {
            filledList.addAtIndex(3, null);
        } catch (IllegalArgumentException e) {
            exception = e;
        } finally {
            String[] expected = new String[]{"Spartan", "Homestead", "Poof", "Citrus", "Acropolis",
                "Aragon", "Bread", "Madtown", "Grey"};
            assertArrayEquals(expected, filledList.getBackingArray());
            assertEquals(9, filledList.size());
        }

        if (exception != null) {
            throw exception;
        }
    }

    @Test()
    public void testAddAtIndexTwoResizes() {
        for (int i = 0; i < 10; i++) {
            filledList.addAtIndex(9 + i, "NewData" + i);
        }

        String[] expected = new String[]{"Spartan", "Homestead", "Poof", "Citrus", "Acropolis",
            "Aragon", "Bread", "Madtown", "Grey", "NewData0", "NewData1", "NewData2", "NewData3", "NewData4",
            "NewData5", "NewData6", "NewData7", "NewData8", "NewData9", null, null, null, null, null, null, null,
            null, null, null, null, null, null, null, null, null, null};
        assertArrayEquals(expected, filledList.getBackingArray());
        assertEquals(19, filledList.size());
    }






    // ---------- removeAtIndex() tests ----------
    // Preconditions:
    // - List can be empty, partially filled, or filled
    //
    // Exceptions:
    // - Index can be valid or invalid ( -1, 0, 1, ..., size - 2, size - 1, size)
    //   This checks the min and max, plus one on either side of each.
    //
    // Postconditions:
    // - Resulting array needs to shift subsequent items left
    // - Method must return the correct value
    // - Ending item should be set to null
    // - Size must be updated

    // EMPTY LIST TESTS
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAtIndexEmptyListIndexTooSmall() {
        // This extra wrapping is required so that I ensure the backing array
        // and size were NOT modified.
        IndexOutOfBoundsException exception = null;
        String returnValue = null;
        try {
            returnValue = list.removeAtIndex(-1);
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        } finally {
            String[] expected = new String[9];
            assertArrayEquals(expected, list.getBackingArray());
            assertEquals(0, list.size());
            assertNull(returnValue);
        }

        if (exception != null) {
            throw exception;
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAtIndexEmptyListIndexZero() {
        // This extra wrapping is required so that I ensure the backing array
        // and size were NOT modified.
        IndexOutOfBoundsException exception = null;
        String returnValue = null;
        try {
            returnValue = list.removeAtIndex(0);
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        } finally {
            String[] expected = new String[9];
            assertArrayEquals(expected, list.getBackingArray());
            assertEquals(0, list.size());
            assertNull(returnValue);
        }

        if (exception != null) {
            throw exception;
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAtIndexEmptyListIndexTooLarge() {
        // This extra wrapping is required so that I ensure the backing array
        // and size were NOT modified.
        IndexOutOfBoundsException exception = null;
        String returnValue = null;
        try {
            returnValue = list.removeAtIndex(1);
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        } finally {
            String[] expected = new String[9];
            assertArrayEquals(expected, list.getBackingArray());
            assertEquals(0, list.size());
            assertNull(returnValue);
        }

        if (exception != null) {
            throw exception;
        }
    }

    // PARTIALLY FILLED LIST TESTS
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAtIndexPartiallyFilledListIndexTooSmall() {
        // This extra wrapping is required so that I ensure the backing array
        // and size were NOT modified.
        IndexOutOfBoundsException exception = null;
        String returned = null;
        try {
            returned = partialList.removeAtIndex(-1);
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        } finally {
            String[] expected = new String[] {"Monkey", "Cat", "Luna", "Crunch", null, null, null, null, null};
            assertArrayEquals(expected, partialList.getBackingArray());
            assertEquals(4, partialList.size());
            assertNull(returned);
        }

        if (exception != null) {
            throw exception;
        }
    }

    @Test()
    public void testRemoveAtIndexPartiallyFilledListIndexZero() {
        // This extra wrapping is required so that I ensure the backing array
        // and size were NOT modified.
        IndexOutOfBoundsException exception = null;
        String returned = null;
        try {
            returned = partialList.removeAtIndex(0);
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        } finally {
            String[] expected = new String[]{"Cat", "Luna", "Crunch", null, null, null, null, null, null};
            assertArrayEquals(expected, partialList.getBackingArray());
            assertEquals(3, partialList.size());
            assertEquals("Monkey", returned);
        }

        if (exception != null) {
            throw exception;
        }
    }

    @Test()
    public void testRemoveAtIndexPartiallyFilledListIndexOne() {
        // This extra wrapping is required so that I ensure the backing array
        // and size were NOT modified.
        IndexOutOfBoundsException exception = null;
        String returned = null;
        try {
            returned = partialList.removeAtIndex(1);
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        } finally {
            String[] expected = new String[]{"Monkey", "Luna", "Crunch", null, null, null, null, null, null};
            assertArrayEquals(expected, partialList.getBackingArray());
            assertEquals(3, partialList.size());
            assertEquals("Cat", returned);
        }

        if (exception != null) {
            throw exception;
        }
    }

    @Test()
    public void testRemoveAtIndexPartiallyFilledListIndexTwo() {
        // This extra wrapping is required so that I ensure the backing array
        // and size were NOT modified.
        IndexOutOfBoundsException exception = null;
        String returned = null;
        try {
            returned = partialList.removeAtIndex(2);
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        } finally {
            String[] expected = new String[]{"Monkey", "Cat", "Crunch", null, null, null, null, null, null};
            assertArrayEquals(expected, partialList.getBackingArray());
            assertEquals(3, partialList.size());
            assertEquals("Luna", returned);
        }

        if (exception != null) {
            throw exception;
        }
    }

    @Test()
    public void testRemoveAtIndexPartiallyFilledListIndexThree() {
        // This extra wrapping is required so that I ensure the backing array
        // and size were NOT modified.
        IndexOutOfBoundsException exception = null;
        String returned = null;
        try {
            returned = partialList.removeAtIndex(3);
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        } finally {
            String[] expected = new String[]{"Monkey", "Cat", "Luna", null, null, null, null, null, null};
            assertArrayEquals(expected, partialList.getBackingArray());
            assertEquals(3, partialList.size());
            assertEquals("Crunch", returned);
        }

        if (exception != null) {
            throw exception;
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAtIndexPartiallyFilledListIndexFour() {
        // This extra wrapping is required so that I ensure the backing array
        // and size were NOT modified.
        IndexOutOfBoundsException exception = null;
        String returned = null;
        try {
            returned = partialList.removeAtIndex(4);
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        } finally {
            String[] expected = new String[]{"Monkey", "Cat", "Luna", "Crunch", null, null, null, null, null};
            assertArrayEquals(expected, partialList.getBackingArray());
            assertEquals(4, partialList.size());
            assertNull(returned);
        }

        if (exception != null) {
            throw exception;
        }
    }

    // FILLED LIST TESTS
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAtIndexFilledListIndexTooSmall() {
        // This extra wrapping is required so that I ensure the backing array
        // and size were NOT modified.
        IndexOutOfBoundsException exception = null;
        String returned = null;
        try {
            returned = filledList.removeAtIndex(-1);
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        } finally {
            String[] expected = new String[]{"Spartan", "Homestead", "Poof", "Citrus", "Acropolis",
                "Aragon", "Bread", "Madtown", "Grey"};
            assertArrayEquals(expected, filledList.getBackingArray());
            assertEquals(9, filledList.size());
            assertNull(returned);
        }

        if (exception != null) {
            throw exception;
        }
    }

    @Test()
    public void testRemoveAtIndexFilledListIndexZero() {
        // This extra wrapping is required so that I ensure the backing array
        // and size were NOT modified.
        IndexOutOfBoundsException exception = null;
        String returned = null;
        try {
            returned = filledList.removeAtIndex(0);
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        } finally {
            String[] expected = new String[]{"Homestead", "Poof", "Citrus", "Acropolis",
                "Aragon", "Bread", "Madtown", "Grey", null};
            assertArrayEquals(expected, filledList.getBackingArray());
            assertEquals(8, filledList.size());
            assertEquals("Spartan", returned);
        }

        if (exception != null) {
            throw exception;
        }
    }

    @Test()
    public void testRemoveAtIndexFilledListIndexOne() {
        // This extra wrapping is required so that I ensure the backing array
        // and size were NOT modified.
        IndexOutOfBoundsException exception = null;
        String returned = null;
        try {
            returned = filledList.removeAtIndex(1);
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        } finally {
            String[] expected = new String[]{"Spartan", "Poof", "Citrus", "Acropolis",
                "Aragon", "Bread", "Madtown", "Grey", null};
            assertArrayEquals(expected, filledList.getBackingArray());
            assertEquals(8, filledList.size());
            assertEquals("Homestead", returned);
        }

        if (exception != null) {
            throw exception;
        }
    }

    @Test()
    public void testRemoveAtIndexFilledListIndexSeven() {
        // This extra wrapping is required so that I ensure the backing array
        // and size were NOT modified.
        IndexOutOfBoundsException exception = null;
        String returned = null;
        try {
            returned = filledList.removeAtIndex(7);
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        } finally {
            String[] expected = new String[]{"Spartan", "Homestead", "Poof", "Citrus", "Acropolis",
                "Aragon", "Bread", "Grey", null};
            assertArrayEquals(expected, filledList.getBackingArray());
            assertEquals(8, filledList.size());
            assertEquals("Madtown", returned);
        }

        if (exception != null) {
            throw exception;
        }
    }

    @Test()
    public void testRemoveAtIndexFilledListIndexEight() {
        // This extra wrapping is required so that I ensure the backing array
        // and size were NOT modified.
        IndexOutOfBoundsException exception = null;
        String returned = null;
        try {
            returned = filledList.removeAtIndex(8);
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        } finally {
            String[] expected = new String[]{"Spartan", "Homestead", "Poof", "Citrus", "Acropolis",
                "Aragon", "Bread", "Madtown", null};
            assertArrayEquals(expected, filledList.getBackingArray());
            assertEquals(8, filledList.size());
            assertEquals("Grey", returned);
        }

        if (exception != null) {
            throw exception;
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAtIndexFilledListIndexTooLarge() {
        // This extra wrapping is required so that I ensure the backing array
        // and size were NOT modified.
        IndexOutOfBoundsException exception = null;
        String returned = null;
        try {
            returned = filledList.removeAtIndex(9);
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        } finally {
            String[] expected = new String[]{"Spartan", "Homestead", "Poof", "Citrus", "Acropolis",
                "Aragon", "Bread", "Madtown", "Grey"};
            assertArrayEquals(expected, filledList.getBackingArray());
            assertEquals(9, filledList.size());
            assertNull(returned);
        }

        if (exception != null) {
            throw exception;
        }
    }






    // ---------- removeFromFront() tests ----------
    // Preconditions:
    // - List can be empty, partially filled, or filled
    //
    // Exceptions:
    // - NoSuchElementException if list is empty
    //
    // Postconditions:
    // - Resulting array needs to shift all items left
    // - Method must return the first item in the array
    // - Ending item should be set to null
    // - Size must be updated

    @Test(expected = NoSuchElementException.class)
    public void testRemoveFromFrontEmptyList() {
        // This extra wrapping is required so that I ensure the backing array
        // and size were NOT modified.
        NoSuchElementException exception = null;
        String returnValue = null;
        try {
            returnValue = list.removeFromFront();
        } catch (NoSuchElementException e) {
            exception = e;
        } finally {
            String[] expected = new String[9];
            assertArrayEquals(expected, list.getBackingArray());
            assertEquals(0, list.size());
            assertNull(returnValue);
        }

        if (exception != null) {
            throw exception;
        }
    }

    @Test()
    public void testRemoveFromFrontPartiallyFilledList() {
        // This extra wrapping is required so that I ensure the backing array
        // and size were NOT modified.
        IndexOutOfBoundsException exception = null;
        String returned = null;
        try {
            returned = partialList.removeFromFront();
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        } finally {
            String[] expected = new String[]{"Cat", "Luna", "Crunch", null, null, null, null, null, null};
            assertArrayEquals(expected, partialList.getBackingArray());
            assertEquals(3, partialList.size());
            assertEquals("Monkey", returned);
        }

        if (exception != null) {
            throw exception;
        }
    }

    @Test()
    public void testRemoveFromFrontFilledList() {
        // This extra wrapping is required so that I ensure the backing array
        // and size were NOT modified.
        IndexOutOfBoundsException exception = null;
        String returned = null;
        try {
            returned = filledList.removeFromFront();
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        } finally {
            String[] expected = new String[]{"Homestead", "Poof", "Citrus", "Acropolis",
                "Aragon", "Bread", "Madtown", "Grey", null};
            assertArrayEquals(expected, filledList.getBackingArray());
            assertEquals(8, filledList.size());
            assertEquals("Spartan", returned);
        }

        if (exception != null) {
            throw exception;
        }
    }






    // ---------- removeFromBack() tests ----------
    // Preconditions:
    // - List can be empty, partially filled, or filled
    //
    // Exceptions:
    // - NoSuchElementException if list is empty
    //
    // Postconditions:
    // - Resulting array needs to shift no items left
    // - Method must return the last item in the list
    // - Ending item should be set to null
    // - Size must be updated

    @Test(expected = NoSuchElementException.class)
    public void testRemoveFromBackEmptyList() {
        // This extra wrapping is required so that I ensure the backing array
        // and size were NOT modified.
        NoSuchElementException exception = null;
        String returnValue = null;
        try {
            returnValue = list.removeFromBack();
        } catch (NoSuchElementException e) {
            exception = e;
        } finally {
            String[] expected = new String[9];
            assertArrayEquals(expected, list.getBackingArray());
            assertEquals(0, list.size());
            assertNull(returnValue);
        }

        if (exception != null) {
            throw exception;
        }
    }

    @Test()
    public void testRemoveFromBackPartiallyFilledList() {
        // This extra wrapping is required so that I ensure the backing array
        // and size were NOT modified.
        IndexOutOfBoundsException exception = null;
        String returned = null;
        try {
            returned = partialList.removeFromBack();
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        } finally {
            String[] expected = new String[]{"Monkey", "Cat", "Luna", null, null, null, null, null, null};
            assertArrayEquals(expected, partialList.getBackingArray());
            assertEquals(3, partialList.size());
            assertEquals("Crunch", returned);
        }

        if (exception != null) {
            throw exception;
        }
    }

    @Test()
    public void testRemoveFromBackFilledList() {
        // This extra wrapping is required so that I ensure the backing array
        // and size were NOT modified.
        IndexOutOfBoundsException exception = null;
        String returned = null;
        try {
            returned = filledList.removeFromBack();
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        } finally {
            String[] expected = new String[]{"Spartan", "Homestead", "Poof", "Citrus", "Acropolis",
                "Aragon", "Bread", "Madtown", null};
            assertArrayEquals(expected, filledList.getBackingArray());
            assertEquals(8, filledList.size());
            assertEquals("Grey", returned);
        }

        if (exception != null) {
            throw exception;
        }
    }






    // ---------- get() tests ----------
    // - List can be empty, partially filled, or filled
    //
    // Exceptions:
    // - IndexOutOfBoundsException if index < 0 or index >= size
    //
    // Postconditions:
    // - Resulting array should not change

    // EMPTY LIST TESTS
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetEmptyListIndexTooSmall() {
        // This extra wrapping is required so that I ensure the backing array
        // and size were NOT modified.
        IndexOutOfBoundsException exception = null;
        String returnValue = null;
        try {
            returnValue = list.get(-1);
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        } finally {
            String[] expected = new String[9];
            assertArrayEquals(expected, list.getBackingArray());
            assertEquals(0, list.size());
            assertNull(returnValue);
        }

        if (exception != null) {
            throw exception;
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetEmptyListIndexZero() {
        // This extra wrapping is required so that I ensure the backing array
        // and size were NOT modified.
        IndexOutOfBoundsException exception = null;
        String returnValue = null;
        try {
            returnValue = list.get(0);
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        } finally {
            String[] expected = new String[9];
            assertArrayEquals(expected, list.getBackingArray());
            assertEquals(0, list.size());
            assertNull(returnValue);
        }

        if (exception != null) {
            throw exception;
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetEmptyListIndexTooLarge() {
        // This extra wrapping is required so that I ensure the backing array
        // and size were NOT modified.
        IndexOutOfBoundsException exception = null;
        String returnValue = null;
        try {
            returnValue = list.get(1);
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        } finally {
            String[] expected = new String[9];
            assertArrayEquals(expected, list.getBackingArray());
            assertEquals(0, list.size());
            assertNull(returnValue);
        }

        if (exception != null) {
            throw exception;
        }
    }

    // PARTIALLY FILLED LIST TESTS
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetPartiallyFilledListIndexTooSmall() {
        // This extra wrapping is required so that I ensure the backing array
        // and size were NOT modified.
        IndexOutOfBoundsException exception = null;
        String returned = null;
        try {
            returned = partialList.get(-1);
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        } finally {
            String[] expected = new String[] {"Monkey", "Cat", "Luna", "Crunch", null, null, null, null, null};
            assertArrayEquals(expected, partialList.getBackingArray());
            assertEquals(4, partialList.size());
            assertNull(returned);
        }

        if (exception != null) {
            throw exception;
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetPartiallyFilledListIndexTooLarge() {
        // This extra wrapping is required so that I ensure the backing array
        // and size were NOT modified.
        IndexOutOfBoundsException exception = null;
        String returned = null;
        try {
            returned = partialList.get(4);
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        } finally {
            String[] expected = new String[] {"Monkey", "Cat", "Luna", "Crunch", null, null, null, null, null};
            assertArrayEquals(expected, partialList.getBackingArray());
            assertEquals(4, partialList.size());
            assertNull(returned);
        }

        if (exception != null) {
            throw exception;
        }
    }

    @Test()
    public void testGetPartiallyFilledListValidIndices() {
        String[] expected = new String[] {"Monkey", "Cat", "Luna", "Crunch", null, null, null, null, null};
        String[] expectedOutputs = new String[] {"Monkey", "Cat", "Luna", "Crunch"};
        String[] actual = new String[4];
        for (int i = 0; i < 4; i++) {
            actual[i] = partialList.get(i);
        }
        assertArrayEquals(expectedOutputs, actual);
        assertArrayEquals(expected, partialList.getBackingArray());
        assertEquals(4, partialList.size());
    }

    // PARTIALLY FILLED LIST TESTS
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetFilledListIndexTooSmall() {
        // This extra wrapping is required so that I ensure the backing array
        // and size were NOT modified.
        IndexOutOfBoundsException exception = null;
        String returned = null;
        try {
            returned = filledList.get(-1);
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        } finally {
            String[] expected = new String[] {"Spartan", "Homestead", "Poof", "Citrus", "Acropolis",
                "Aragon", "Bread", "Madtown", "Grey"};
            assertArrayEquals(expected, filledList.getBackingArray());
            assertEquals(9, filledList.size());
            assertNull(returned);
        }

        if (exception != null) {
            throw exception;
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetFilledListIndexTooLarge() {
        // This extra wrapping is required so that I ensure the backing array
        // and size were NOT modified.
        IndexOutOfBoundsException exception = null;
        String returned = null;
        try {
            returned = filledList.get(9);
        } catch (IndexOutOfBoundsException e) {
            exception = e;
        } finally {
            String[] expected = new String[] {"Spartan", "Homestead", "Poof", "Citrus", "Acropolis",
                "Aragon", "Bread", "Madtown", "Grey"};
            assertArrayEquals(expected, filledList.getBackingArray());
            assertEquals(9, filledList.size());
            assertNull(returned);
        }

        if (exception != null) {
            throw exception;
        }
    }

    @Test()
    public void testGetFilledListValidIndices() {
        String[] expected = new String[] {"Spartan", "Homestead", "Poof", "Citrus", "Acropolis",
            "Aragon", "Bread", "Madtown", "Grey"};
        String[] actual = new String[9];
        for (int i = 0; i < 9; i++) {
            actual[i] = filledList.get(i);
        }
        assertArrayEquals(expected, actual);
        assertArrayEquals(expected, filledList.getBackingArray());
        assertEquals(9, filledList.size());
    }






    // ----------- isEmpty() ----------
    @Test
    public void testIsEmptyEmptyList() {
        assertTrue(list.isEmpty());
    }

    @Test
    public void testIsEmptyPartiallyFilledList() {
        assertFalse(partialList.isEmpty());
    }

    @Test
    public void testIsEmptyFilledList() {
        assertFalse(filledList.isEmpty());
    }

    // ----------- clear() ----------
    @Test
    public void testClearEmptyList() {
        list.clear();
        String[] expected = new String[9];
        assertArrayEquals(expected, list.getBackingArray());
        assertEquals(0, list.size());
    }

    @Test
    public void testClearPartiallyFilledList() {
        partialList.clear();
        String[] expected = new String[9];
        assertArrayEquals(expected, list.getBackingArray());
        assertEquals(0, list.size());
    }

    @Test
    public void testClearFilledList() {
        filledList.clear();
        String[] expected = new String[9];
        assertArrayEquals(expected, list.getBackingArray());
        assertEquals(0, list.size());
    }

}