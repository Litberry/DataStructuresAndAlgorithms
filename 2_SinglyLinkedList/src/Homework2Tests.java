 import org.junit.After;
 import org.junit.Before;
 import org.junit.Test;
 import org.junit.experimental.runners.Enclosed;
 import org.junit.runner.RunWith;
 
 import java.io.PrintStream;
 import java.lang.reflect.Constructor;
 import java.lang.reflect.Field;
 import java.lang.reflect.InvocationTargetException;
 import java.lang.reflect.Method;
 import java.util.ArrayList;
 import java.util.NoSuchElementException;
 
 import static junit.framework.TestCase.assertTrue;
 import static org.junit.Assert.*;
 
 @RunWith(Enclosed.class)
 public class Homework2Tests {
     private static final int TIMEOUT = 200;
 
 
     /**
      * All tests related to adding elements at indices within a LinkedList.
      */
     public static class LinkedListAddAtIndexTests {
         private CircularSinglyLinkedList<String> emptyList;
         private CircularSinglyLinkedList<String> sizeOneList;
         private CircularSinglyLinkedList<String> sizeFourList;
 
         @Before
         public void initializeLinkedListTests() {
             IORedirector.getInstance().beginRedirecting();
         }
 
         @After
         public void finalizeLinkedListTests() {
             assertFalse("Make sure not to have any debug print statements left in your code! Points WILL be deducted", IORedirector.getInstance().hasReceivedMessage());
         }
 
         @Before
         public void setupAddTests() {
             emptyList = new CircularSinglyLinkedList<>();
 
             sizeOneList = new CircularSinglyLinkedList<String>();
 
             CircularSinglyLinkedListWrapper<String> sizeOneListWrapper = new CircularSinglyLinkedListWrapper<String>(sizeOneList);
 
             CircularSinglyLinkedListNode<String> firstHeadNode = new CircularSinglyLinkedListNode<>("Subliminal Text");
 
             firstHeadNode.setNext(firstHeadNode);
 
             sizeOneListWrapper.forceSetHeadNode(firstHeadNode, 1);
 
             sizeFourList = new CircularSinglyLinkedList<>();
 
             CircularSinglyLinkedListNode<String> headNode = new CircularSinglyLinkedListNode<>("Vim");
             CircularSinglyLinkedListNode<String> secondNode = new CircularSinglyLinkedListNode<>("Emac");
             CircularSinglyLinkedListNode<String> thirdNode = new CircularSinglyLinkedListNode<>("Microsoft Word");
             CircularSinglyLinkedListNode<String> fourthNode = new CircularSinglyLinkedListNode<>("++Notepad");
 
             CircularSinglyLinkedListWrapper<String> listWrapper = new CircularSinglyLinkedListWrapper<>(sizeFourList);
 
             headNode.setNext(secondNode);
             secondNode.setNext(thirdNode);
             thirdNode.setNext(fourthNode);
             fourthNode.setNext(headNode);
 
             listWrapper.forceSetHeadNode(headNode, 4);
 
 
         }
 
         @Test(timeout = TIMEOUT)
         public void addAtIndexEmptyIndexTooSmall() {
             ExceptionUtils.assertExceptionIsCorrect(IndexOutOfBoundsException.class, () -> {
                 emptyList.addAtIndex(-1, "Landry");
             }, "-1"); // Make sure you have a descriptive error message!
             assertEquals(0, emptyList.size());
             assertArrayEquals(new String[0], LinkedListTraversalHelper.retrieveData(emptyList));
             assertNull(emptyList.getHead());
         }
 
         @Test(timeout = TIMEOUT)
         public void addAtIndexEmptyIndexZero() {
             emptyList.addAtIndex(0, "Landry");
             assertEquals(1, emptyList.size());
             assertArrayEquals(new String[]{"Landry"}, LinkedListTraversalHelper.retrieveData(emptyList));
             assertEquals("Landry", emptyList.getHead().getData());
         }
 
         @Test(timeout = TIMEOUT)
         public void addAtIndexEmptyIndexTooLarge() {
             ExceptionUtils.assertExceptionIsCorrect(IndexOutOfBoundsException.class, () -> {
                 emptyList.addAtIndex(1, "Landry");
             }, "1"); // Make sure you have a descriptive error message!
             assertEquals(0, emptyList.size());
             assertArrayEquals(new String[0], LinkedListTraversalHelper.retrieveData(emptyList));
             assertNull(emptyList.getHead());
         }
 
         @Test(timeout = TIMEOUT)
         public void addAtIndexEmptyInvalidData() {
             ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () -> {
                 emptyList.addAtIndex(0, null);
             }, "null"); // Make sure you have a descriptive error message!
             assertEquals(0, emptyList.size());
             assertArrayEquals(new String[0], LinkedListTraversalHelper.retrieveData(emptyList));
             assertNull(emptyList.getHead());
         }
 
         @Test(timeout = TIMEOUT)
         public void addAtIndexSizeOneIndexTooSmall() {
             ExceptionUtils.assertExceptionIsCorrect(IndexOutOfBoundsException.class, () -> {
                 sizeOneList.addAtIndex(-1, "Landry");
             }, "-1"); // Make sure you have a descriptive error message!
             assertEquals(1, sizeOneList.size());
             assertArrayEquals(new String[]{"Subliminal Text"}, LinkedListTraversalHelper.retrieveData(sizeOneList));
             assertEquals("Subliminal Text", sizeOneList.getHead().getData());
         }
 
         @Test(timeout = TIMEOUT)
         public void addAtIndexSizeOneIndexZero() {
             sizeOneList.addAtIndex(0, "Landry");
             assertEquals(2, sizeOneList.size());
             assertArrayEquals(new String[]{"Landry", "Subliminal Text"}, LinkedListTraversalHelper.retrieveData(sizeOneList));
             assertEquals("Landry", sizeOneList.getHead().getData());
         }
 
         @Test(timeout = TIMEOUT)
         public void addAtIndexSizeOneIndexOne() {
             sizeOneList.addAtIndex(1, "Landry");
             assertEquals(2, sizeOneList.size());
             assertArrayEquals(new String[]{"Subliminal Text", "Landry"}, LinkedListTraversalHelper.retrieveData(sizeOneList));
             assertEquals("Subliminal Text", sizeOneList.getHead().getData());
         }
 
         @Test(timeout = TIMEOUT)
         public void addAtIndexSizeOneIndexTooLarge() {
             ExceptionUtils.assertExceptionIsCorrect(IndexOutOfBoundsException.class, () -> {
                 sizeOneList.addAtIndex(2, "Landry");
             }, "2"); // Make sure you have a descriptive error message!
             assertEquals(1, sizeOneList.size());
             assertArrayEquals(new String[]{"Subliminal Text"}, LinkedListTraversalHelper.retrieveData(sizeOneList));
             assertEquals("Subliminal Text", sizeOneList.getHead().getData());
         }
 
         @Test(timeout = TIMEOUT)
         public void addAtIndexSizeOneDataInvalid() {
             ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () -> {
                 sizeOneList.addAtIndex(0, null);
             }, "null"); // Make sure you have a descriptive error message!
             assertEquals(1, sizeOneList.size());
             assertArrayEquals(new String[]{"Subliminal Text"}, LinkedListTraversalHelper.retrieveData(sizeOneList));
             assertEquals("Subliminal Text", sizeOneList.getHead().getData());
         }
 
         @Test(timeout = TIMEOUT)
         public void addAtIndexSizeFourIndexTooSmall() {
             ExceptionUtils.assertExceptionIsCorrect(IndexOutOfBoundsException.class, () -> {
                 sizeFourList.addAtIndex(-1, "Subliminal Text");
             }, "-1"); // Make sure you have a descriptive error message!
             assertEquals(4, sizeFourList.size());
             assertArrayEquals(new String[]{"Vim", "Emac", "Microsoft Word", "++Notepad"}, LinkedListTraversalHelper.retrieveData(sizeFourList));
             assertEquals("Vim", sizeFourList.getHead().getData());
         }
 
         @Test(timeout = TIMEOUT)
         public void addAtIndexSizeFourIndexZero() {
             sizeFourList.addAtIndex(0, "Subliminal Text");
             assertEquals(5, sizeFourList.size());
             assertArrayEquals(new String[]{"Subliminal Text", "Vim", "Emac", "Microsoft Word", "++Notepad"}, LinkedListTraversalHelper.retrieveData(sizeFourList));
             assertEquals("Subliminal Text", sizeFourList.getHead().getData());
         }
 
         @Test(timeout = TIMEOUT)
         public void addAtIndexSizeFourIndexOne() {
             sizeFourList.addAtIndex(1, "Subliminal Text");
             assertEquals(5, sizeFourList.size());
             assertArrayEquals(new String[]{"Vim", "Subliminal Text", "Emac", "Microsoft Word", "++Notepad"}, LinkedListTraversalHelper.retrieveData(sizeFourList));
             assertEquals("Vim", sizeFourList.getHead().getData());
         }
 
         @Test(timeout = TIMEOUT)
         public void addAtIndexSizeFourIndexThree() {
             sizeFourList.addAtIndex(3, "Subliminal Text");
             assertEquals(5, sizeFourList.size());
             assertArrayEquals(new String[]{"Vim", "Emac", "Microsoft Word", "Subliminal Text", "++Notepad"}, LinkedListTraversalHelper.retrieveData(sizeFourList));
             assertEquals("Vim", sizeFourList.getHead().getData());
         }
 
         @Test(timeout = TIMEOUT)
         public void addAtIndexSizeFourIndexFour() {
             sizeFourList.addAtIndex(4, "Subliminal Text");
             assertEquals(5, sizeFourList.size());
             assertArrayEquals(new String[]{"Vim", "Emac", "Microsoft Word", "++Notepad", "Subliminal Text"}, LinkedListTraversalHelper.retrieveData(sizeFourList));
             assertEquals("Vim", sizeFourList.getHead().getData());
         }
 
         @Test(timeout = TIMEOUT)
         public void addAtIndexSizeFourIndexFive() {
             ExceptionUtils.assertExceptionIsCorrect(IndexOutOfBoundsException.class, () -> {
                 sizeFourList.addAtIndex(5, "Subliminal Text");
             }, "5"); // Make sure you have a descriptive error message!
             assertEquals(4, sizeFourList.size());
             assertArrayEquals(new String[]{"Vim", "Emac", "Microsoft Word", "++Notepad"}, LinkedListTraversalHelper.retrieveData(sizeFourList));
             assertEquals("Vim", sizeFourList.getHead().getData());
         }
 
         @Test(timeout = TIMEOUT)
         public void addAtIndexSizeFourDataInvalid() {
             ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () -> {
                 sizeFourList.addAtIndex(1, null);
             }, "null"); // Make sure you have a descriptive error message!
             assertEquals(4, sizeFourList.size());
             assertArrayEquals(new String[]{"Vim", "Emac", "Microsoft Word", "++Notepad"}, LinkedListTraversalHelper.retrieveData(sizeFourList));
             assertEquals("Vim", sizeFourList.getHead().getData());
         }
     }
 
 
     /**
      * All tests related to adding elements to the back of a LinkedList
      */
     public static class LinkedListAddToBackTests {
         private CircularSinglyLinkedList<String> emptyList;
         private CircularSinglyLinkedList<String> sizeOneList;
         private CircularSinglyLinkedList<String> sizeFourList;
 
         @Before
         public void initializeLinkedListTests() {
             IORedirector.getInstance().beginRedirecting();
         }
 
         @After
         public void finalizeLinkedListTests() {
             assertFalse("no print debug statements in code", IORedirector.getInstance().hasReceivedMessage());
         }
 
         @Before
         public void setupAddTests() {
             emptyList = new CircularSinglyLinkedList<>();
 
             sizeOneList = new CircularSinglyLinkedList<>();
 
             CircularSinglyLinkedListWrapper<String> sizeOneListWrapper = new CircularSinglyLinkedListWrapper<>(sizeOneList);
 
             CircularSinglyLinkedListNode<String> firstHeadNode = new CircularSinglyLinkedListNode<>("Ricky");
 
             firstHeadNode.setNext(firstHeadNode);
 
             sizeOneListWrapper.forceSetHeadNode(firstHeadNode, 1);
 
             sizeFourList = new CircularSinglyLinkedList<>();
 
             CircularSinglyLinkedListNode<String> headNode = new CircularSinglyLinkedListNode<>("Albert");
             CircularSinglyLinkedListNode<String> secondNode = new CircularSinglyLinkedListNode<>("Bill");
             CircularSinglyLinkedListNode<String> thirdNode = new CircularSinglyLinkedListNode<>("Charlie");
             CircularSinglyLinkedListNode<String> fourthNode = new CircularSinglyLinkedListNode<>("Dan");
 
             CircularSinglyLinkedListWrapper<String> listWrapper = new CircularSinglyLinkedListWrapper<>(sizeFourList);
 
             headNode.setNext(secondNode);
             secondNode.setNext(thirdNode);
             thirdNode.setNext(fourthNode);
             fourthNode.setNext(headNode);
 
             listWrapper.forceSetHeadNode(headNode, 4);
         }
 
         @Test(timeout = TIMEOUT)
         public void addToBackEmptyListInvalidData() {
             ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () -> {
                 emptyList.addToBack(null);
             }, "null"); // Make sure you have a descriptive error message!
             assertEquals(0, emptyList.size());
             assertArrayEquals(new String[0], LinkedListTraversalHelper.retrieveData(emptyList));
             assertNull(emptyList.getHead());
         }
 
         @Test(timeout = TIMEOUT)
         public void addToBackEmptyListValidData() {
             emptyList.addToBack("Landry");
             assertEquals(1, emptyList.size());
             assertArrayEquals(new String[]{"Landry"}, LinkedListTraversalHelper.retrieveData(emptyList));
             assertEquals("Landry", emptyList.getHead().getData());
         }
 
         @Test(timeout = TIMEOUT)
         public void addToBackSizeOneListInvalidData() {
             ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () -> {
                 sizeOneList.addToBack(null);
             }, "null"); // Make sure you have a descriptive error message!
             assertEquals(1, sizeOneList.size());
             assertArrayEquals(new String[]{"Ricky"}, LinkedListTraversalHelper.retrieveData(sizeOneList));
             assertEquals("Ricky", sizeOneList.getHead().getData());
         }
 
         @Test(timeout = TIMEOUT)
         public void addToBackSizeOneListValidData() {
             sizeOneList.addToBack("Landry");
             assertEquals(2, sizeOneList.size());
             assertArrayEquals(new String[]{"Ricky", "Landry"}, LinkedListTraversalHelper.retrieveData(sizeOneList));
             assertEquals("Ricky", sizeOneList.getHead().getData());
         }
 
         @Test(timeout = TIMEOUT)
         public void addToBackSizeFourListInvalidData() {
             ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () -> {
                 sizeFourList.addToBack(null);
             }, "null"); // Make sure you have a descriptive error message!
             assertEquals(4, sizeFourList.size());
             assertArrayEquals(new String[]{"Albert", "Bill", "Charlie", "Dan"}, LinkedListTraversalHelper.retrieveData(sizeFourList));
             assertEquals("Albert", sizeFourList.getHead().getData());
         }
 
         @Test(timeout = TIMEOUT)
         public void addToBackSizeFourListValidData() {
             sizeFourList.addToBack("Ricky");
             assertEquals(5, sizeFourList.size());
             assertArrayEquals(new String[]{"Albert", "Bill", "Charlie", "Dan", "Ricky"}, LinkedListTraversalHelper.retrieveData(sizeFourList));
             assertEquals("Albert", sizeFourList.getHead().getData());
         }
 
     }
 
 
     /**
      * All tests related to adding to the front of a LinkedList.
      */
     public static class LinkedListAddToFrontTests {
         private CircularSinglyLinkedList<String> emptyList;
         private CircularSinglyLinkedList<String> sizeOneList;
         private CircularSinglyLinkedList<String> sizeFourList;
 
         @Before
         public void initializeLinkedListTests() {
             IORedirector.getInstance().beginRedirecting();
         }
 
         @After
         public void finalizeLinkedListTests() {
             assertFalse("no print debug statements in code", IORedirector.getInstance().hasReceivedMessage());
         }
 
         @Before
         public void setupAddTests() {
             emptyList = new CircularSinglyLinkedList<>();
 
             sizeOneList = new CircularSinglyLinkedList<>();
 
             CircularSinglyLinkedListWrapper<String> sizeOneListWrapper = new CircularSinglyLinkedListWrapper<>(sizeOneList);
 
             CircularSinglyLinkedListNode<String> firstHeadNode = new CircularSinglyLinkedListNode<>("Ricky");
 
             firstHeadNode.setNext(firstHeadNode);
 
             sizeOneListWrapper.forceSetHeadNode(firstHeadNode, 1);
 
             CircularSinglyLinkedListNode<String> headNode = new CircularSinglyLinkedListNode<>("Rocking Chair");
             CircularSinglyLinkedListNode<String> secondNode = new CircularSinglyLinkedListNode<>("Rolly Chair");
             CircularSinglyLinkedListNode<String> thirdNode = new CircularSinglyLinkedListNode<>("Circle Chair");
             CircularSinglyLinkedListNode<String> fourthNode = new CircularSinglyLinkedListNode<>("Cube Chair");
 
             sizeFourList = new CircularSinglyLinkedList<>();
 
             CircularSinglyLinkedListWrapper<String> listWrapper = new CircularSinglyLinkedListWrapper<>(sizeFourList);
 
 
             headNode.setNext(secondNode);
             secondNode.setNext(thirdNode);
             thirdNode.setNext(fourthNode);
             fourthNode.setNext(headNode);
 
             listWrapper.forceSetHeadNode(headNode, 4);
         }
 
         @Test(timeout = TIMEOUT)
         public void addToFrontEmptyListInvalidData() {
             ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () -> {
                 emptyList.addToFront(null);
             }, "null"); // Make sure you have a descriptive error message!
             assertEquals(0, emptyList.size());
             assertArrayEquals(new String[0], LinkedListTraversalHelper.retrieveData(emptyList));
             assertNull(emptyList.getHead());
         }
 
         @Test(timeout = TIMEOUT)
         public void addToFrontEmptyListValidData() {
             emptyList.addToFront("Landry");
             assertEquals(1, emptyList.size());
             assertArrayEquals(new String[]{"Landry"}, LinkedListTraversalHelper.retrieveData(emptyList));
             assertEquals("Landry", emptyList.getHead().getData());
         }
 
         @Test(timeout = TIMEOUT)
         public void addToFrontSizeOneListInvalidData() {
             ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () -> {
                 sizeOneList.addToFront(null);
             }, "null"); // Make sure you have a descriptive error message!
             assertEquals(1, sizeOneList.size());
             assertArrayEquals(new String[]{"Ricky"}, LinkedListTraversalHelper.retrieveData(sizeOneList));
             assertEquals("Ricky", sizeOneList.getHead().getData());
         }
 
         @Test(timeout = TIMEOUT)
         public void addToFrontSizeOneListValidData() {
             sizeOneList.addToFront("Landry");
             assertEquals(2, sizeOneList.size());
             assertArrayEquals(new String[]{"Landry", "Ricky"}, LinkedListTraversalHelper.retrieveData(sizeOneList));
             assertEquals("Landry", sizeOneList.getHead().getData());
         }
 
         @Test(timeout = TIMEOUT)
         public void addToFrontSizeFourListInvalidData() {
             ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () -> {
                 sizeFourList.addToFront(null);
             }, "null"); // Make sure you have a descriptive error message!
             assertEquals(4, sizeFourList.size());
             assertArrayEquals(new String[]{"Rocking Chair", "Rolly Chair", "Circle Chair", "Cube Chair"}, LinkedListTraversalHelper.retrieveData(sizeFourList));
             assertEquals("Rocking Chair", sizeFourList.getHead().getData());
         }
 
         @Test(timeout = TIMEOUT)
         public void addToFrontSizeFourListValidData() {
             sizeFourList.addToFront("Ricky");
             assertEquals(5, sizeFourList.size());
             assertArrayEquals(new String[]{"Ricky", "Rocking Chair", "Rolly Chair", "Circle Chair", "Cube Chair"}, LinkedListTraversalHelper.retrieveData(sizeFourList));
             assertEquals("Ricky", sizeFourList.getHead().getData());
         }
 
     }
 
 
     /**
      * All tests relating to clearing a LinkedList.
      */
     public static class LinkedListClearTests {
         private CircularSinglyLinkedList<String> emptyList;
         private CircularSinglyLinkedList<String> sizeOneList;
         private CircularSinglyLinkedList<String> sizeFourList;
 
         @Before
         public void initializeLinkedListTests() {
             IORedirector.getInstance().beginRedirecting();
         }
 
         @After
         public void finalizeLinkedListTests() {
             assertFalse("no print debug statements in code", IORedirector.getInstance().hasReceivedMessage());
         }
 
         @Before
         public void setupClearTests() {
             emptyList = new CircularSinglyLinkedList<>();
 
             sizeOneList = new CircularSinglyLinkedList<>();
 
             CircularSinglyLinkedListWrapper<String> sizeOneListWrapper = new CircularSinglyLinkedListWrapper<>(sizeOneList);
 
             CircularSinglyLinkedListNode<String> firstHeadNode = new CircularSinglyLinkedListNode<>("Ricky");
 
             firstHeadNode.setNext(firstHeadNode);
 
             sizeOneListWrapper.forceSetHeadNode(firstHeadNode, 1);
 
             sizeFourList = new CircularSinglyLinkedList<>();
 
             CircularSinglyLinkedListNode<String> headNode = new CircularSinglyLinkedListNode<>("Windows");
             CircularSinglyLinkedListNode<String> secondNode = new CircularSinglyLinkedListNode<>("MacOS");
             CircularSinglyLinkedListNode<String> thirdNode = new CircularSinglyLinkedListNode<>("Ubuntu");
             CircularSinglyLinkedListNode<String> fourthNode = new CircularSinglyLinkedListNode<>("Gentoo");
 
             CircularSinglyLinkedListWrapper<String> listWrapper = new CircularSinglyLinkedListWrapper<>(sizeFourList);
 
             headNode.setNext(secondNode);
             secondNode.setNext(thirdNode);
             thirdNode.setNext(fourthNode);
             fourthNode.setNext(headNode);
 
             listWrapper.forceSetHeadNode(headNode, 4);
         }
 
         @Test(timeout = TIMEOUT)
         public void clearEmptyList() {
             emptyList.clear();
             assertEquals(0, emptyList.size());
             assertArrayEquals(new String[0], LinkedListTraversalHelper.retrieveData(emptyList));
             assertNull(emptyList.getHead());
         }
 
         @Test(timeout = TIMEOUT)
         public void clearSizeOneList() {
             sizeOneList.clear();
             assertEquals(0, sizeOneList.size());
             assertArrayEquals(new String[0], LinkedListTraversalHelper.retrieveData(sizeOneList));
             assertNull(sizeOneList.getHead());
         }
 
         @Test(timeout = TIMEOUT)
         public void clearSizeFourList() {
             sizeFourList.clear();
             assertEquals(0, sizeFourList.size());
             assertArrayEquals(new String[0], LinkedListTraversalHelper.retrieveData(sizeFourList));
             assertNull(sizeFourList.getHead());
         }
     }
 
 
     /**
      * All tests related to getting elements from a LinkedList.
      */
     public static class LinkedListGetTests {
         private CircularSinglyLinkedList<String> emptyList;
         private CircularSinglyLinkedList<String> sizeOneList;
         private CircularSinglyLinkedList<String> sizeFourList;
 
         @Before
         public void initializeLinkedListTests() {
             IORedirector.getInstance().beginRedirecting();
         }
 
         @After
         public void finalizeLinkedListTests() {
             assertFalse("no print debug statements in code", IORedirector.getInstance().hasReceivedMessage());
         }
 
         @Before
         public void setupGetTests() {
             emptyList = new CircularSinglyLinkedList<>();
 
 
             sizeOneList = new CircularSinglyLinkedList<>();
 
             CircularSinglyLinkedListWrapper<String> sizeOneListWrapper = new CircularSinglyLinkedListWrapper<>(sizeOneList);
 
             CircularSinglyLinkedListNode<String> firstHeadNode = new CircularSinglyLinkedListNode<>("Ricky");
 
             firstHeadNode.setNext(firstHeadNode);
 
             sizeOneListWrapper.forceSetHeadNode(firstHeadNode, 1);
 
             sizeFourList = new CircularSinglyLinkedList<>();
 
             CircularSinglyLinkedListNode<String> headNode = new CircularSinglyLinkedListNode<>("Obsidian");
             CircularSinglyLinkedListNode<String> secondNode = new CircularSinglyLinkedListNode<>("Notion");
             CircularSinglyLinkedListNode<String> thirdNode = new CircularSinglyLinkedListNode<>("Google Docs");
             CircularSinglyLinkedListNode<String> fourthNode = new CircularSinglyLinkedListNode<>("Notepad");
 
             CircularSinglyLinkedListWrapper<String> listWrapper = new CircularSinglyLinkedListWrapper<>(sizeFourList);
 
             headNode.setNext(secondNode);
             secondNode.setNext(thirdNode);
             thirdNode.setNext(fourthNode);
             fourthNode.setNext(headNode);
 
             listWrapper.forceSetHeadNode(headNode, 4);
         }
 
         @Test(timeout = TIMEOUT)
         public void getEmptyListIndexTooSmall() {
             ExceptionUtils.assertExceptionIsCorrect(IndexOutOfBoundsException.class, () -> {
                 emptyList.get(-1);
             }, "-1"); // Make sure you have a descriptive error message!
             assertEquals(0, emptyList.size());
             assertArrayEquals(new String[0], LinkedListTraversalHelper.retrieveData(emptyList));
             assertNull(emptyList.getHead());
         }
 
         @Test(timeout = TIMEOUT)
         public void getEmptyListIndexZero() {
             ExceptionUtils.assertExceptionIsCorrect(IndexOutOfBoundsException.class, () -> {
                 emptyList.get(0);
             }, "0"); // Make sure you have a descriptive error message!
             assertEquals(0, emptyList.size());
             assertArrayEquals(new String[0], LinkedListTraversalHelper.retrieveData(emptyList));
             assertNull(emptyList.getHead());
         }
 
         @Test(timeout = TIMEOUT)
         public void getEmptyListIndexOne() {
             ExceptionUtils.assertExceptionIsCorrect(IndexOutOfBoundsException.class, () -> {
                 emptyList.get(1);
             }, "1"); // Make sure you have a descriptive error message!
             assertEquals(0, emptyList.size());
             assertArrayEquals(new String[0], LinkedListTraversalHelper.retrieveData(emptyList));
             assertNull(emptyList.getHead());
         }
 
         @Test(timeout = TIMEOUT)
         public void getSizeOneListIndexTooSmall() {
             ExceptionUtils.assertExceptionIsCorrect(IndexOutOfBoundsException.class, () -> {
                 sizeOneList.get(-1);
             }, "-1"); // Make sure you have a descriptive error message!
             assertEquals(1, sizeOneList.size());
             assertArrayEquals(new String[]{"Ricky"}, LinkedListTraversalHelper.retrieveData(sizeOneList));
             assertEquals("Ricky", sizeOneList.getHead().getData());
         }
 
         @Test(timeout = TIMEOUT)
         public void getSizeOneListIndexZero() {
             assertEquals("Ricky", sizeOneList.get(0));
             assertEquals(1, sizeOneList.size());
             assertArrayEquals(new String[]{"Ricky"}, LinkedListTraversalHelper.retrieveData(sizeOneList));
             assertEquals("Ricky", sizeOneList.getHead().getData());
         }
 
         @Test(timeout = TIMEOUT)
         public void getSizeOneListIndexOne() {
             ExceptionUtils.assertExceptionIsCorrect(IndexOutOfBoundsException.class, () -> {
                 sizeOneList.get(1);
             }, "1"); // Make sure you have a descriptive error message!
             assertEquals(1, sizeOneList.size());
             assertArrayEquals(new String[]{"Ricky"}, LinkedListTraversalHelper.retrieveData(sizeOneList));
             assertEquals("Ricky", sizeOneList.getHead().getData());
         }
 
         @Test(timeout = TIMEOUT)
         public void getSizeFourIndexTooSmall() {
             ExceptionUtils.assertExceptionIsCorrect(IndexOutOfBoundsException.class, () -> {
                 sizeFourList.get(-1);
             }, "-1"); // Make sure you have a descriptive error message!
             assertEquals(4, sizeFourList.size());
             assertArrayEquals(new String[]{"Obsidian", "Notion", "Google Docs", "Notepad"}, LinkedListTraversalHelper.retrieveData(sizeFourList));
             assertEquals("Obsidian", sizeFourList.getHead().getData());
         }
 
         @Test(timeout = TIMEOUT)
         public void getSizeFourIndexZero() {
             assertEquals("Obsidian", sizeFourList.get(0));
             assertEquals(4, sizeFourList.size());
             assertArrayEquals(new String[]{"Obsidian", "Notion", "Google Docs", "Notepad"}, LinkedListTraversalHelper.retrieveData(sizeFourList));
             assertEquals("Obsidian", sizeFourList.getHead().getData());
         }
 
         @Test(timeout = TIMEOUT)
         public void getSizeFourIndexOne() {
             assertEquals("Notion", sizeFourList.get(1));
             assertEquals(4, sizeFourList.size());
             assertArrayEquals(new String[]{"Obsidian", "Notion", "Google Docs", "Notepad"}, LinkedListTraversalHelper.retrieveData(sizeFourList));
             assertEquals("Obsidian", sizeFourList.getHead().getData());
         }
 
         @Test(timeout = TIMEOUT)
         public void getSizeFourIndexTwo() {
             assertEquals("Google Docs", sizeFourList.get(2));
             assertEquals(4, sizeFourList.size());
             assertArrayEquals(new String[]{"Obsidian", "Notion", "Google Docs", "Notepad"}, LinkedListTraversalHelper.retrieveData(sizeFourList));
             assertEquals("Obsidian", sizeFourList.getHead().getData());
         }
 
         @Test(timeout = TIMEOUT)
         public void getSizeFourIndexThree() {
             assertEquals("Notepad", sizeFourList.get(3));
             assertEquals(4, sizeFourList.size());
             assertArrayEquals(new String[]{"Obsidian", "Notion", "Google Docs", "Notepad"}, LinkedListTraversalHelper.retrieveData(sizeFourList));
             assertEquals("Obsidian", sizeFourList.getHead().getData());
         }
 
         @Test(timeout = TIMEOUT)
         public void getSizeFourIndexFour() {
             ExceptionUtils.assertExceptionIsCorrect(IndexOutOfBoundsException.class, () -> {
                 sizeFourList.get(4);
             }, "4"); // Make sure you have a descriptive error message!
             assertEquals(4, sizeFourList.size());
             assertArrayEquals(new String[]{"Obsidian", "Notion", "Google Docs", "Notepad"}, LinkedListTraversalHelper.retrieveData(sizeFourList));
             assertEquals("Obsidian", sizeFourList.getHead().getData());
         }
 
     }
 
 
     /**
      * All tests related to removing elements at indices within a LinkedList.
      */
     public static class LinkedListRemoveAtIndexTests {
         private CircularSinglyLinkedList<String> emptyList;
         private CircularSinglyLinkedList<String> sizeOneList;
         private CircularSinglyLinkedList<String> sizeFourList;
 
         @Before
         public void initializeLinkedListTests() {
             IORedirector.getInstance().beginRedirecting();
         }
 
         @After
         public void finalizeLinkedListTests() {
             assertFalse("no print debug statements in code", IORedirector.getInstance().hasReceivedMessage());
         }
 
         @Before
         public void setupRemoveTests() {
             emptyList = new CircularSinglyLinkedList<>();
 
 
             sizeOneList = new CircularSinglyLinkedList<>();
 
             CircularSinglyLinkedListWrapper<String> sizeOneListWrapper = new CircularSinglyLinkedListWrapper<>(sizeOneList);
 
             CircularSinglyLinkedListNode<String> firstHeadNode = new CircularSinglyLinkedListNode<>("Ricky");
 
             firstHeadNode.setNext(firstHeadNode);
 
             sizeOneListWrapper.forceSetHeadNode(firstHeadNode, 1);
 
             sizeFourList = new CircularSinglyLinkedList<>();
 
             CircularSinglyLinkedListNode<String> headNode = new CircularSinglyLinkedListNode<>("Amazon Prime");
             CircularSinglyLinkedListNode<String> secondNode = new CircularSinglyLinkedListNode<>("Disney+");
             CircularSinglyLinkedListNode<String> thirdNode = new CircularSinglyLinkedListNode<>("Hulu");
             CircularSinglyLinkedListNode<String> fourthNode = new CircularSinglyLinkedListNode<>("HBO");
 
             CircularSinglyLinkedListWrapper<String> listWrapper = new CircularSinglyLinkedListWrapper<>(sizeFourList);
 
             headNode.setNext(secondNode);
             secondNode.setNext(thirdNode);
             thirdNode.setNext(fourthNode);
             fourthNode.setNext(headNode);
 
             listWrapper.forceSetHeadNode(headNode, 4);
         }
 
         @Test(timeout = TIMEOUT)
         public void removeAtIndexEmptyListIndexTooSmall() {
             ExceptionUtils.assertExceptionIsCorrect(IndexOutOfBoundsException.class, () -> {
                 emptyList.removeAtIndex(-1);
             }, "-1"); // Make sure you have a descriptive error message!
             assertEquals(0, emptyList.size());
             assertArrayEquals(new String[0], LinkedListTraversalHelper.retrieveData(emptyList));
             assertNull(emptyList.getHead());
         }
 
         @Test(timeout = TIMEOUT)
         public void removeAtIndexEmptyListIndexZero() {
             ExceptionUtils.assertExceptionIsCorrect(IndexOutOfBoundsException.class, () -> {
                 emptyList.removeAtIndex(0);
             }, "0"); // Make sure you have a descriptive error message!
             assertEquals(0, emptyList.size());
             assertArrayEquals(new String[0], LinkedListTraversalHelper.retrieveData(emptyList));
             assertNull(emptyList.getHead());
         }
 
         @Test(timeout = TIMEOUT)
         public void removeAtIndexEmptyListIndexOne() {
             ExceptionUtils.assertExceptionIsCorrect(IndexOutOfBoundsException.class, () -> {
                 emptyList.removeAtIndex(1);
             }, "1"); // Make sure you have a descriptive error message!
             assertEquals(0, emptyList.size());
             assertArrayEquals(new String[0], LinkedListTraversalHelper.retrieveData(emptyList));
             assertNull(emptyList.getHead());
         }
 
         @Test(timeout = TIMEOUT)
         public void removeAtIndexSizeOneIndexTooSmall() {
             ExceptionUtils.assertExceptionIsCorrect(IndexOutOfBoundsException.class, () -> {
                 sizeOneList.removeAtIndex(-1);
             }, "-1"); // Make sure you have a descriptive error message!
             assertEquals(1, sizeOneList.size());
             assertArrayEquals(new String[]{"Ricky"}, LinkedListTraversalHelper.retrieveData(sizeOneList));
             assertEquals("Ricky", sizeOneList.getHead().getData());
         }
 
         @Test(timeout = TIMEOUT)
         public void removeAtIndexSizeOneIndexZero() {
             assertEquals("Ricky", sizeOneList.removeAtIndex(0));
             assertEquals(0, sizeOneList.size());
             assertArrayEquals(new String[0], LinkedListTraversalHelper.retrieveData(sizeOneList));
             assertNull(sizeOneList.getHead());
         }
 
         @Test(timeout = TIMEOUT)
         public void removeAtIndexSizeOneIndexOne() {
             ExceptionUtils.assertExceptionIsCorrect(IndexOutOfBoundsException.class, () -> {
                 sizeOneList.removeAtIndex(1);
             }, "1"); // Make sure you have a descriptive error message!
             assertEquals(1, sizeOneList.size());
             assertArrayEquals(new String[]{"Ricky"}, LinkedListTraversalHelper.retrieveData(sizeOneList));
             assertEquals("Ricky", sizeOneList.getHead().getData());
         }
 
         @Test(timeout = TIMEOUT)
         public void removeAtIndexSizeFourIndexTooSmall() {
             ExceptionUtils.assertExceptionIsCorrect(IndexOutOfBoundsException.class, () -> {
                 sizeFourList.removeAtIndex(-1);
             }, "-1"); // Make sure you have a descriptive error message!
             assertEquals(4, sizeFourList.size());
             assertArrayEquals(new String[]{"Amazon Prime", "Disney+", "Hulu", "HBO"}, LinkedListTraversalHelper.retrieveData(sizeFourList));
             assertEquals("Amazon Prime", sizeFourList.getHead().getData());
         }
 
         @Test(timeout = TIMEOUT)
         public void removeAtIndexSizeFourIndexZero() {
             assertEquals("Amazon Prime", sizeFourList.removeAtIndex(0));
             assertEquals(3, sizeFourList.size());
             assertArrayEquals(new String[]{"Disney+", "Hulu", "HBO"}, LinkedListTraversalHelper.retrieveData(sizeFourList));
             assertEquals("Disney+", sizeFourList.getHead().getData());
         }
 
         @Test(timeout = TIMEOUT)
         public void removeAtIndexSizeFourIndexOne() {
             assertEquals("Disney+", sizeFourList.removeAtIndex(1));
             assertEquals(3, sizeFourList.size());
             assertArrayEquals(new String[]{"Amazon Prime", "Hulu", "HBO"}, LinkedListTraversalHelper.retrieveData(sizeFourList));
             assertEquals("Amazon Prime", sizeFourList.getHead().getData());
         }
 
         @Test(timeout = TIMEOUT)
         public void removeAtIndexSizeFourIndexTwo() {
             assertEquals("Hulu", sizeFourList.removeAtIndex(2));
             assertEquals(3, sizeFourList.size());
             assertArrayEquals(new String[]{"Amazon Prime", "Disney+", "HBO"}, LinkedListTraversalHelper.retrieveData(sizeFourList));
             assertEquals("Amazon Prime", sizeFourList.getHead().getData());
         }
 
         @Test(timeout = TIMEOUT)
         public void removeAtIndexSizeFourIndexThree() {
             assertEquals("HBO", sizeFourList.removeAtIndex(3));
             assertEquals(3, sizeFourList.size());
             assertArrayEquals(new String[]{"Amazon Prime", "Disney+", "Hulu"}, LinkedListTraversalHelper.retrieveData(sizeFourList));
             assertEquals("Amazon Prime", sizeFourList.getHead().getData());
         }
 
         @Test(timeout = TIMEOUT)
         public void removeAtIndexSizeFourIndexFour() {
             ExceptionUtils.assertExceptionIsCorrect(IndexOutOfBoundsException.class, () -> {
                 sizeFourList.removeAtIndex(4);
             }, "4"); // Make sure you have a descriptive error message!
             assertEquals(4, sizeFourList.size());
             assertArrayEquals(new String[]{"Amazon Prime", "Disney+", "Hulu", "HBO"}, LinkedListTraversalHelper.retrieveData(sizeFourList));
             assertEquals("Amazon Prime", sizeFourList.getHead().getData());
         }
 
     }
 
 
     /**
      * All tests related to removing from the back of a test
      */
     public static class LinkedListRemoveFromBackTests {
         private CircularSinglyLinkedList<String> emptyList;
         private CircularSinglyLinkedList<String> sizeOneList;
         private CircularSinglyLinkedList<String> sizeFourList;
 
         @Before
         public void initializeLinkedListTests() {
             IORedirector.getInstance().beginRedirecting();
         }
 
         @After
         public void finalizeLinkedListTests() {
             assertFalse("no print debug statements in code", IORedirector.getInstance().hasReceivedMessage());
         }
 
         @Before
         public void setupRemoveTests() {
             emptyList = new CircularSinglyLinkedList<>();
 
             sizeOneList = new CircularSinglyLinkedList<>();
 
             CircularSinglyLinkedListWrapper<String> sizeOneListWrapper = new CircularSinglyLinkedListWrapper<>(sizeOneList);
 
             CircularSinglyLinkedListNode<String> firstHeadNode = new CircularSinglyLinkedListNode<>("Kevin");
 
             firstHeadNode.setNext(firstHeadNode);
 
             sizeOneListWrapper.forceSetHeadNode(firstHeadNode, 1);
 
             sizeFourList = new CircularSinglyLinkedList<>();
 
             CircularSinglyLinkedListNode<String> headNode = new CircularSinglyLinkedListNode<>("Walmart");
             CircularSinglyLinkedListNode<String> secondNode = new CircularSinglyLinkedListNode<>("Cheers");
             CircularSinglyLinkedListNode<String> thirdNode = new CircularSinglyLinkedListNode<>("Yippie");
             CircularSinglyLinkedListNode<String> fourthNode = new CircularSinglyLinkedListNode<>("Hoorah");
 
             CircularSinglyLinkedListWrapper<String> listWrapper = new CircularSinglyLinkedListWrapper<>(sizeFourList);
 
             headNode.setNext(secondNode);
             secondNode.setNext(thirdNode);
             thirdNode.setNext(fourthNode);
             fourthNode.setNext(headNode);
 
             listWrapper.forceSetHeadNode(headNode, 4);
         }
 
         @Test(timeout = TIMEOUT)
         public void removeFromBackEmptyList() {
             ExceptionUtils.assertExceptionIsCorrect(NoSuchElementException.class, () -> {
                 emptyList.removeFromBack();
             }, "empty"); // Make sure you have a descriptive error message!
             assertEquals(0, emptyList.size());
             assertArrayEquals(new String[0], LinkedListTraversalHelper.retrieveData(emptyList));
             assertNull(emptyList.getHead());
         }
 
         @Test(timeout = TIMEOUT)
         public void removeFromBackSizeOneList() {
             assertEquals("Kevin", sizeOneList.removeFromBack());
             assertEquals(0, sizeOneList.size());
             assertArrayEquals(new String[0], LinkedListTraversalHelper.retrieveData(sizeOneList));
             assertNull(sizeOneList.getHead());
         }
 
         @Test(timeout = TIMEOUT)
         public void removeFromBackSizeFourList() {
             assertEquals("Hoorah", sizeFourList.removeFromBack());
             assertEquals(3, sizeFourList.size());
             assertArrayEquals(new String[]{"Walmart", "Cheers", "Yippie"}, LinkedListTraversalHelper.retrieveData(sizeFourList));
             assertEquals("Walmart", sizeFourList.getHead().getData());
         }
 
     }
 
 
     /**
      * All tests related to removing from the front of a LinkedList.
      */
     public static class LinkedListRemoveFromFrontTests {
         private CircularSinglyLinkedList<String> emptyList;
         private CircularSinglyLinkedList<String> sizeOneList;
         private CircularSinglyLinkedList<String> sizeFourList;
 
         @Before
         public void initializeLinkedListTests() {
             IORedirector.getInstance().beginRedirecting();
         }
 
         @After
         public void finalizeLinkedListTests() {
             assertFalse("no print debug statements in code", IORedirector.getInstance().hasReceivedMessage());
         }
 
         @Before
         public void setupRemoveTests() {
             emptyList = new CircularSinglyLinkedList<>();
 
             sizeOneList = new CircularSinglyLinkedList<>();
 
             CircularSinglyLinkedListWrapper<String> sizeOneListWrapper = new CircularSinglyLinkedListWrapper<>(sizeOneList);
 
             CircularSinglyLinkedListNode<String> firstHeadNode = new CircularSinglyLinkedListNode<>("Ricky");
 
             firstHeadNode.setNext(firstHeadNode);
 
             sizeOneListWrapper.forceSetHeadNode(firstHeadNode, 1);
 
             sizeFourList = new CircularSinglyLinkedList<>();
 
             CircularSinglyLinkedListNode<String> headNode = new CircularSinglyLinkedListNode<>("ControlC");
             CircularSinglyLinkedListNode<String> secondNode = new CircularSinglyLinkedListNode<>("ControlV");
             CircularSinglyLinkedListNode<String> thirdNode = new CircularSinglyLinkedListNode<>("ControlZ");
             CircularSinglyLinkedListNode<String> fourthNode = new CircularSinglyLinkedListNode<>("ControlY");
 
             CircularSinglyLinkedListWrapper<String> listWrapper = new CircularSinglyLinkedListWrapper<>(sizeFourList);
 
             headNode.setNext(secondNode);
             secondNode.setNext(thirdNode);
             thirdNode.setNext(fourthNode);
             fourthNode.setNext(headNode);
 
             listWrapper.forceSetHeadNode(headNode, 4);
         }
 
         @Test(timeout = TIMEOUT)
         public void removeFromFrontEmptyList() {
             ExceptionUtils.assertExceptionIsCorrect(NoSuchElementException.class, () -> {
                 emptyList.removeFromFront();
             }, "empty"); // Make sure you have a descriptive error message!
             assertEquals(0, emptyList.size());
             assertArrayEquals(new String[0], LinkedListTraversalHelper.retrieveData(emptyList));
             assertNull(emptyList.getHead());
         }
 
         @Test(timeout = TIMEOUT)
         public void removeFromFrontSizeOneList() {
             assertEquals("Ricky", sizeOneList.removeFromFront());
             assertEquals(0, sizeOneList.size());
             assertArrayEquals(new String[0], LinkedListTraversalHelper.retrieveData(sizeOneList));
             assertNull(sizeOneList.getHead());
         }
 
         @Test(timeout = TIMEOUT)
         public void removeFromFrontSizeFourList() {
             assertEquals("ControlC", sizeFourList.removeFromFront());
             assertEquals(3, sizeFourList.size());
 
             assertArrayEquals(new String[]{"ControlV", "ControlZ", "ControlY"}, LinkedListTraversalHelper.retrieveData(sizeFourList));
             assertEquals("ControlV", sizeFourList.getHead().getData());
         }
 
     }
 
 
     /**
      * All tests related to removing the last occurrence of an element from a LinkedList
      */
     public static class LinkedListRemoveLastOccurrenceTests {
         private CircularSinglyLinkedList<String> emptyList;
 
         private CircularSinglyLinkedList<String> noOccurrences;
         private CircularSinglyLinkedList<String> manyOccurrences;
 
         private String target = "bug";
 
         // This field exists to test for exact (address) equivalence and
         // regular equivalence (.equals()) separately
         private String notTarget = new String("bug");
 
         @Before
         public void initializeLinkedListTests() {
             IORedirector.getInstance().beginRedirecting();
         }
 
         @After
         public void finalizeLinkedListTests() {
             assertFalse("no print debug statements in code", IORedirector.getInstance().hasReceivedMessage());
         }
 
         @Before
         public void setupLinkedListRemoveLastOccurrenceTests() {
             emptyList = new CircularSinglyLinkedList<>();
 
             noOccurrences = new CircularSinglyLinkedList<>();
 
             CircularSinglyLinkedListNode<String> headNode = new CircularSinglyLinkedListNode<>("Dog");
             CircularSinglyLinkedListNode<String> secondNode = new CircularSinglyLinkedListNode<>("Cat");
             CircularSinglyLinkedListNode<String> thirdNode = new CircularSinglyLinkedListNode<>("Rabbit");
             CircularSinglyLinkedListNode<String> fourthNode = new CircularSinglyLinkedListNode<>("Dragon");
             CircularSinglyLinkedListNode<String> fifthNode = new CircularSinglyLinkedListNode<>("Tiger");
 
             CircularSinglyLinkedListWrapper<String> listWrapper = new CircularSinglyLinkedListWrapper<>(noOccurrences);
 
             headNode.setNext(secondNode);
             secondNode.setNext(thirdNode);
             thirdNode.setNext(fourthNode);
             fourthNode.setNext(fifthNode);
             fifthNode.setNext(headNode);
 
             listWrapper.forceSetHeadNode(headNode, 5);
 
             manyOccurrences = new CircularSinglyLinkedList<>();
 
             CircularSinglyLinkedListNode<String> mheadNode = new CircularSinglyLinkedListNode<>("Dog");
             CircularSinglyLinkedListNode<String> msecondNode = new CircularSinglyLinkedListNode<>("Cat");
             CircularSinglyLinkedListNode<String> mthirdNode = new CircularSinglyLinkedListNode<>(notTarget);
             CircularSinglyLinkedListNode<String> mfourthNode = new CircularSinglyLinkedListNode<>("Rabbit");
             CircularSinglyLinkedListNode<String> mfifthNode = new CircularSinglyLinkedListNode<>("Dragon");
             CircularSinglyLinkedListNode<String> msixthNode = new CircularSinglyLinkedListNode<>("Tiger");
             CircularSinglyLinkedListNode<String> mseventhNode = new CircularSinglyLinkedListNode<>(notTarget);
             CircularSinglyLinkedListNode<String> meigthNode = new CircularSinglyLinkedListNode<>(notTarget);
             CircularSinglyLinkedListNode<String> mninthNode = new CircularSinglyLinkedListNode<>("Creeper");
             CircularSinglyLinkedListNode<String> mtenthNode = new CircularSinglyLinkedListNode<>("Zombie");
             CircularSinglyLinkedListNode<String> mtwelthNode = new CircularSinglyLinkedListNode<>("Skeleton");
             CircularSinglyLinkedListNode<String> mthirteenthNode = new CircularSinglyLinkedListNode<>("Pig");
             CircularSinglyLinkedListNode<String> mfourteenthNode = new CircularSinglyLinkedListNode<>("Snake");
 
             CircularSinglyLinkedListWrapper<String> mlistWrapper = new CircularSinglyLinkedListWrapper<>(manyOccurrences);
 
             mheadNode.setNext(msecondNode);
             msecondNode.setNext(mthirdNode);
             mthirdNode.setNext(mfourthNode);
             mfourthNode.setNext(mfifthNode);
             mfifthNode.setNext(msixthNode);
             msixthNode.setNext(mseventhNode);
             mseventhNode.setNext(meigthNode);
             meigthNode.setNext(mninthNode);
             mninthNode.setNext(mtenthNode);
             mtenthNode.setNext(mtwelthNode);
             mtwelthNode.setNext(mthirteenthNode);
             mthirteenthNode.setNext(mfourteenthNode);
             mfourteenthNode.setNext(mheadNode);
 
             mlistWrapper.forceSetHeadNode(mheadNode, 13);
         }
 
         @Test(timeout = TIMEOUT)
         public void removeLastEmptyListNullData() {
             ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () -> {
                 emptyList.removeLastOccurrence(null);
             }, "null"); // Make sure you have a descriptive error message!
             assertEquals(0, emptyList.size());
             assertArrayEquals(new String[0], LinkedListTraversalHelper.retrieveData(emptyList));
             assertNull(emptyList.getHead());
         }
 
         @Test(timeout = TIMEOUT)
         public void removeLastEmptyListNoOccurrences() {
             ExceptionUtils.assertExceptionIsCorrect(NoSuchElementException.class, () -> {
                 emptyList.removeLastOccurrence(target);
             }, target); // Make sure you have a descriptive error message!
             assertEquals(0, emptyList.size());
             assertArrayEquals(new String[0], LinkedListTraversalHelper.retrieveData(emptyList));
             assertNull(emptyList.getHead());
         }
 
         @Test(timeout = TIMEOUT)
         public void removeLastNoOccurrencesInvalidData() {
             ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () -> {
                 noOccurrences.removeLastOccurrence(null);
             }, "null"); // Make sure you have a descriptive error message!
             assertEquals(5, noOccurrences.size());
             assertArrayEquals(new String[]{"Dog", "Cat", "Rabbit", "Dragon", "Tiger"}, LinkedListTraversalHelper.retrieveData(noOccurrences));
             assertEquals("Dog", noOccurrences.getHead().getData());
         }
 
         @Test(timeout = TIMEOUT)
         public void removeLastNoOccurrences() {
             ExceptionUtils.assertExceptionIsCorrect(NoSuchElementException.class, () -> {
                 noOccurrences.removeLastOccurrence(target);
             }, target); // Make sure you have a descriptive error message!
             assertEquals(5, noOccurrences.size());
             assertArrayEquals(new String[]{"Dog", "Cat", "Rabbit", "Dragon", "Tiger"}, LinkedListTraversalHelper.retrieveData(noOccurrences));
             assertEquals("Dog", noOccurrences.getHead().getData());
         }
 
         @Test(timeout = TIMEOUT)
         public void removeLastSizeOneListNullData() {
             ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () -> {
                 emptyList.removeLastOccurrence(null);
             }, "null"); // Make sure you have a descriptive error message!
             assertEquals(0, emptyList.size());
             assertArrayEquals(new String[0], LinkedListTraversalHelper.retrieveData(emptyList));
             assertNull(emptyList.getHead());
         }
 
         @Test(timeout = TIMEOUT)
         public void removeLastSizeOneListNoOccurrences() {
             emptyList.addToFront("Feature 1");
             ExceptionUtils.assertExceptionIsCorrect(NoSuchElementException.class, () -> {
                 emptyList.removeLastOccurrence(target);
             }, target); // Make sure you have a descriptive error message!
             assertEquals(1, emptyList.size());
             assertArrayEquals(new String[] {"Feature 1"}, LinkedListTraversalHelper.retrieveData(emptyList));
             assertEquals("Feature 1", emptyList.getHead().getData());
         }
 
         @Test(timeout = TIMEOUT)
         public void removeLastSizeOneListOneOccurrence() {
             emptyList.addToFront(target);
             String removed = emptyList.removeLastOccurrence(notTarget);
             assertSame(target, removed);
             assertNotSame(notTarget, removed);
             assertEquals(0, emptyList.size());
             assertArrayEquals(new String[0], LinkedListTraversalHelper.retrieveData(emptyList));
             assertNull(emptyList.getHead());
         }
 
         @Test(timeout = TIMEOUT)
         public void removeLastOneOccurrenceFront() {
             noOccurrences.addToFront(target);
             assertArrayEquals(new String[]{"bug", "Dog", "Cat", "Rabbit", "Dragon", "Tiger"}, LinkedListTraversalHelper.retrieveData(noOccurrences));
             String returned = noOccurrences.removeLastOccurrence(target);
             assertEquals(target, returned);
             assertSame(target, returned); //Ensures you're returning the exact item
             assertEquals(5, noOccurrences.size());
             assertArrayEquals(new String[]{"Dog", "Cat", "Rabbit", "Dragon", "Tiger"}, LinkedListTraversalHelper.retrieveData(noOccurrences));
             assertEquals("Dog", noOccurrences.getHead().getData());
         }
 
         @Test(timeout = TIMEOUT)
         public void removeLastOneOccurrenceMiddle() {
             noOccurrences.addAtIndex(3, target);
             assertArrayEquals(new String[]{"Dog", "Cat", "Rabbit", "bug", "Dragon", "Tiger"}, LinkedListTraversalHelper.retrieveData(noOccurrences));
             String returned = noOccurrences.removeLastOccurrence(target);
             assertEquals(target, returned);
             assertSame(target, returned); //Ensures you're returning the exact item
             assertEquals(5, noOccurrences.size());
             assertArrayEquals(new String[]{"Dog", "Cat", "Rabbit", "Dragon", "Tiger"}, LinkedListTraversalHelper.retrieveData(noOccurrences));
             assertEquals("Dog", noOccurrences.getHead().getData());
         }
 
         @Test(timeout = TIMEOUT)
         public void removeLastOneOccurrenceBack() {
             noOccurrences.addToBack(target);
             assertArrayEquals(new String[]{"Dog", "Cat", "Rabbit", "Dragon", "Tiger", "bug"}, LinkedListTraversalHelper.retrieveData(noOccurrences));
             String returned = noOccurrences.removeLastOccurrence(target);
             assertEquals(target, returned);
             assertSame(target, returned); //Ensures you're returning the exact item
             assertEquals(5, noOccurrences.size());
             assertArrayEquals(new String[]{"Dog", "Cat", "Rabbit", "Dragon", "Tiger"}, LinkedListTraversalHelper.retrieveData(noOccurrences));
             assertEquals("Dog", noOccurrences.getHead().getData());
         }
 
         @Test(timeout = TIMEOUT)
         public void removeLastManyOccurrencesMiddle() {
             manyOccurrences.addAtIndex(8, target);
             assertArrayEquals(new String[]{"Dog", "Cat", "bug", "Rabbit", "Dragon", "Tiger", "bug", "bug", "bug", "Creeper", "Zombie", "Skeleton", "Pig", "Snake"}, LinkedListTraversalHelper.retrieveData(manyOccurrences));
             String returned = manyOccurrences.removeLastOccurrence(target);
             assertEquals(target, returned);
             assertSame(target, returned); //Ensures you're returning the exact item
             assertNotSame(notTarget, returned); // Ensures you are not returning the wrong copy
             assertEquals(13, manyOccurrences.size());
             assertArrayEquals(new String[]{"Dog", "Cat", "bug", "Rabbit", "Dragon", "Tiger", "bug", "bug", "Creeper", "Zombie", "Skeleton", "Pig", "Snake"}, LinkedListTraversalHelper.retrieveData(manyOccurrences));
             assertEquals("Dog", manyOccurrences.getHead().getData());
         }
 
         @Test(timeout = TIMEOUT)
         public void removeLastManyOccurrencesBack() {
             manyOccurrences.addToBack(target);
             assertArrayEquals(new String[]{"Dog", "Cat", "bug", "Rabbit", "Dragon", "Tiger", "bug", "bug", "Creeper", "Zombie", "Skeleton", "Pig", "Snake", "bug"}, LinkedListTraversalHelper.retrieveData(manyOccurrences));
             String returned = manyOccurrences.removeLastOccurrence(target);
             assertEquals(target, returned);
             assertSame(target, returned); //Ensures you're returning the exact item
             assertNotSame(notTarget, returned); // Ensures you are not returning the wrong copy
             assertEquals(13, manyOccurrences.size());
             assertArrayEquals(new String[]{"Dog", "Cat", "bug", "Rabbit", "Dragon", "Tiger", "bug", "bug", "Creeper", "Zombie", "Skeleton", "Pig", "Snake"}, LinkedListTraversalHelper.retrieveData(manyOccurrences));
             assertEquals("Dog", manyOccurrences.getHead().getData());
         }
 
     }
 
 
     public static class LinkedListToArrayTests {
         private CircularSinglyLinkedList<String> emptyList;
         private CircularSinglyLinkedList<String> sizeOneList;
         private CircularSinglyLinkedList<String> sizeFourList;
 
         @Before
         public void initializeLinkedListTests() {
             IORedirector.getInstance().beginRedirecting();
         }
 
         @After
         public void finalizeLinkedListTests() {
             assertFalse("no print debug statements in code", IORedirector.getInstance().hasReceivedMessage());
         }
 
         @Before
         public void setupToArrayTests() {
             emptyList = new CircularSinglyLinkedList<>();
 
             sizeOneList = new CircularSinglyLinkedList<>();
             sizeOneList.addAtIndex(0, "Ricky");
 
             sizeFourList = new CircularSinglyLinkedList<>();
             CircularSinglyLinkedListNode<String> headNode = new CircularSinglyLinkedListNode<>("Albert");
             CircularSinglyLinkedListNode<String> secondNode = new CircularSinglyLinkedListNode<>("Bill");
             CircularSinglyLinkedListNode<String> thirdNode = new CircularSinglyLinkedListNode<>("Charlie");
             CircularSinglyLinkedListNode<String> fourthNode = new CircularSinglyLinkedListNode<>("Dan");
 
             CircularSinglyLinkedListWrapper<String> listWrapper = new CircularSinglyLinkedListWrapper<>(sizeFourList);
 
             headNode.setNext(secondNode);
             secondNode.setNext(thirdNode);
             thirdNode.setNext(fourthNode);
             fourthNode.setNext(headNode);
 
             listWrapper.forceSetHeadNode(headNode, 4);
         }
 
         @Test(timeout = TIMEOUT)
         public void toArrayEmptyList() {
             assertEquals(0, emptyList.size());
             assertArrayEquals(new String[0], emptyList.toArray());
             assertArrayEquals(new String[0], LinkedListTraversalHelper.retrieveData(emptyList));
             assertNull(emptyList.getHead());
         }
 
         @Test(timeout = TIMEOUT)
         public void toArraySizeOneList() {
             assertEquals(1, sizeOneList.size());
             assertArrayEquals(new String[]{"Ricky"}, sizeOneList.toArray());
             assertArrayEquals(new String[]{"Ricky"}, LinkedListTraversalHelper.retrieveData(sizeOneList));
             assertEquals("Ricky", sizeOneList.getHead().getData());
         }
 
         @Test(timeout = TIMEOUT)
         public void toArraySizeFourList() {
             assertEquals(4, sizeFourList.size());
             assertArrayEquals(new String[]{"Albert", "Bill", "Charlie", "Dan"}, sizeFourList.toArray());
             assertArrayEquals(new String[]{"Albert", "Bill", "Charlie", "Dan"}, LinkedListTraversalHelper.retrieveData(sizeFourList));
             assertEquals("Albert", sizeFourList.getHead().getData());
         }
     }
 
 
 }
 
 
 /**
  * A set of utilities to remove redundant assertion boilerplate.
  */
 class AssertUtils {
 
     public static void assertValidException(Exception e) {
         assertTrue(ExceptionUtils.isDescriptiveException(e));
     }
 }
 
 
 /**
  * A wrapper utility class for accessing package-protected or private classes located within CircularSinglyLinkedListNodeWrapper
  *
  * @param <T> The generic used by the wrapper node
  */
 class CircularSinglyLinkedListNodeWrapper<T> {
     private final ReflectionWrapper wrapper;
 
     public CircularSinglyLinkedListNodeWrapper(CircularSinglyLinkedListNode<T> node) {
         this.wrapper = new ReflectionWrapper(node);
     }
 
     public T getData() {
         return forceCast(wrapper.invokeMethod("getData"));
     }
 
     public void setData(T data) {
         wrapper.invokeMethod("setData", data);
     }
 
     public void setNext(CircularSinglyLinkedListNode<T> next) {
         wrapper.invokeMethod("setNext", next);
     }
 
     public CircularSinglyLinkedListNode<T> getNext() {
         return (CircularSinglyLinkedListNode<T>) wrapper.invokeMethod("getNext");
     }
 
     public CircularSinglyLinkedListNode<T> getNode() {
         return (CircularSinglyLinkedListNode<T>) wrapper.getInternalReference();
     }
 
     private T forceCast(Object o) {
         //noinspection unchecked
         return (T) o;
     }
 
 }
 
 
 /**
  * A wrapper for CircularSinglyLinkedList allowing previously hidden elements to be accessed.
  * <p>
  * Primarily uses {@link ReflectionWrapper} to modify fields.
  *
  * @param <T> The data type of the CircularSinglyLinkedList
  */
 class CircularSinglyLinkedListWrapper<T> {
     private final ReflectionWrapper wrapper;
 
     /**
      * Constructs a new CircularSinglyLinkedListWrapper using an existing CircularSinglyLinkedList.
      *
      * @param linkedList The linked list to wrap around
      */
     public CircularSinglyLinkedListWrapper(CircularSinglyLinkedList<T> linkedList) {
         this.wrapper = new ReflectionWrapper(linkedList);
     }
 
     /**
      * Uses reflection to forcibly construct a new LinkedListNode
      * <p>
      * This method was required due to complications of the methods being marked as package-private,
      * while the files for these JUnit tests are initally in separate packages.
      *
      * @param data The data to inject into the node
      * @return A new CircularSinglyLinkedListNode<T> containing the specified data
      * @throws NoSuchMethodException     if the constructor was unable to be found
      * @throws InvocationTargetException N/A
      * @throws InstantiationException    N/A
      * @throws IllegalAccessException    N/A
      */
     private CircularSinglyLinkedListNode<T> forceCreateLinkedListNode(T data) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
         Class<?> nodeClass = CircularSinglyLinkedListNode.class;
 
         @SuppressWarnings("unchecked") Constructor<CircularSinglyLinkedListNode<T>> constructor = (Constructor<CircularSinglyLinkedListNode<T>>) nodeClass.getDeclaredConstructor(Object.class, CircularSinglyLinkedListNode.class);
 
         return constructor.newInstance();
     }
 
     /**
      * Helper method for forcibly setting the data of a LinkedList's head.
      * <p>
      * This is completely from the main LinkedList implementation, as if the
      * user's addToIndex methods are incorrect all tests using it to initialize
      * would fail. Instead, reflection is used here to forcibly change it without
      * any interaction with the user's internal code.
      *
      * @param data the new data to set
      */
     public void forceSetHeadData(T data) {
         try {
             CircularSinglyLinkedListNode<T> node = forceCreateLinkedListNode(data);
 
             wrapper.setValue("head", node);
         } catch (Exception e) {
             throw new RuntimeException(e);
         }
     }
 
     /**
      * Helper method for forcibly setting the node of a LinkedList's head.
      * <p>
      * This is completely from the main LinkedList implementation, as if the
      * user's addToIndex methods are incorrect all tests using it to initialize
      * would fail. Instead, reflection is used here to forcibly change it without
      * any interaction with the user's internal code.
      *
      * @param newHeadNode the new head node to set
      */
     public void forceSetHeadNode(CircularSinglyLinkedListNode<T> newHeadNode, int size) {
         try {
             wrapper.setValue("head", newHeadNode);
             wrapper.setValue("size", size);
         } catch (Exception e) {
             throw new RuntimeException(e);
         }
     }
 }
 
 
 /**
  * A set of utilities for handling test cases regarding exceptions.
  */
 class ExceptionUtils {
 
     /**
      * Asserts a snippet of code throws the required exception, checking if it's descriptive along the way.
      *
      * @param expected                  The expected exception to throw
      * @param codeThatMayThrowException A runnable containing the code intended to throw an exception
      * @param requiredSubstring         A required substring for the message to be considered descriptive.
      *                                  If this is null, then no substring is required.
      */
     public static void assertExceptionIsCorrect(Class<? extends Exception> expected, Runnable codeThatMayThrowException, String requiredSubstring) {
         try {
             codeThatMayThrowException.run();
             // If this line runs, then your code was supposed to throw an Exception but did not.
             fail("Encountered a test that did not throw the expected expected of " + expected.getName());
 
         } catch (Exception e) {
             assertSame("checking if the thrown exception is what we expected", e.getClass(), expected); // If this line fails, the code threw the wrong exception (or threw no exception at all)
             //assertTrue(e.getClass().getName() + " exception thrown needs to be descriptive " + (requiredSubstring != null ? "\" and must contain the message \"" + requiredSubstring : "\". Note that this might be the toString representation of the object, e.g. if you can't find an element this test forces you to include the toString of data to show a descriptive message."), isDescriptiveException(e, requiredSubstring)); // If this line fails, then the message was insufficiently descriptive
         }
     }
 
     /**
      * Determines whether a message is considered "descriptive" by the rubric.
      * This isn't a perfect check, but it at the very least ensures all exceptions
      * contain some sort of error message.
      *
      * @param e                 The exception to check
      * @param requiredSubstring The substring that most be contained in the exception message.
      *                          If this is null, then no substring is required.
      * @return true if the exception is considered descriptive
      */
     public static boolean isDescriptiveException(Exception e, String requiredSubstring) {
         if (requiredSubstring == null) {
             return !(e.getMessage() == null || e.getMessage().isBlank());
         } else {
             return e.getMessage().contains(requiredSubstring);
         }
     }
 
     /**
      * Convenience feature for isDescriptiveException(Exception e, String requiredSubstring).
      *
      * @param e Exception to check.
      * @return Same as isDescriptiveException(Exception e, String requiredSubstring).
      */
     public static boolean isDescriptiveException(Exception e) {
         return isDescriptiveException(e, null);
     }
 }
 
 
 /**
  * A utility for redirecting the standard output for use in test cases.
  * <p>
  * Primary, this is being used to ensure "nothing should be printed" when the code is run.
  * The IORedirector will not on its own fail the tests, but is used before and after
  * to record all messages sent.
  */
 class IORedirector {
     private static IORedirector instance;
 
     private PrintStream originalStream;
     private PrintStream redirectedStream;
 
     private String log = "";
 
     /**
      * Private singleton construction method, initially grabbing the standard output for later use.
      */
     private IORedirector() {
         this.originalStream = System.out;
     }
 
     /**
      * Begin redirecting the standard output into the custom PrintStream, used before a test is run.
      */
     public void beginRedirecting() {
         log = "";
 
         if (redirectedStream == null) {
             redirectedStream = getRedirectorStream();
         }
 
         System.setOut(redirectedStream);
     }
 
     /**
      * Standard Singleton-pattern instance system to avoid IORedirectors fighting over the same stream.
      *
      * @return The IORedirector instance
      */
     public static IORedirector getInstance() {
         if (instance == null) {
             instance = new IORedirector();
         }
 
         return instance;
     }
 
     /**
      * All messages received from the standard output system are directed to this message via
      * the redirector stream. This is responsible for attaching those messages to the log.
      *
      * @param s The input to handle
      */
     private void handleMessage(String s) {
         log += s.replaceAll("\r", "");
     }
 
     public boolean hasReceivedMessage() {
         return !log.isEmpty();
     }
 
     private PrintStream getRedirectorStream() {
         return new PrintStream(System.out, true) {
             @Override
             public void print(String s) {
                 IORedirector.getInstance().handleMessage(s);
             }
 
             @Override
             public PrintStream printf(String message, Object... args) {
                 IORedirector.getInstance().handleMessage(String.format(message, args));
                 return this;
             }
 
             @Override
             public void println(String s) {
                 IORedirector.getInstance().handleMessage(s + "\n");
             }
         };
     }
 }
 
 
 /**
  * A helper class used for traversing through a LinkedList without relying on the
  * user's methods. As the homework obviously can't be written here, it instead uses
  * reflection to brute force testing in a much more reliable manner.
  */
 class LinkedListTraversalHelper {
     /**
      * Retrieves all data from a CircularSinglyLinkedList using reflection
      *
      * @param list The list to pull from
      * @param <T>  The data type of the LinkedList
      * @return An array of T representing the contents of the LinkedList, used for checking in test cases.
      */
     public static <T> T[] retrieveData(CircularSinglyLinkedList<T> list) {
 
         ArrayList<CircularSinglyLinkedListNodeWrapper<T>> arrayList = new ArrayList<>();
         CircularSinglyLinkedListNode<T> head = list.getHead();
 
         if (head == null) {
             return (T[]) new Object[0];
         }
 
         CircularSinglyLinkedListNodeWrapper<T> headWrapper = new CircularSinglyLinkedListNodeWrapper<>(head);
         CircularSinglyLinkedListNodeWrapper<T> currWrapper = headWrapper;
         while (!(currWrapper.getNext() == null || currWrapper.getNext() == head)) {
             arrayList.add(currWrapper);
             currWrapper = new CircularSinglyLinkedListNodeWrapper<>(currWrapper.getNext());
         }
         arrayList.add(currWrapper);
 
         if (currWrapper.getNext() == null) {
             fail("One of your nodes points to null!");
         }
 
         T[] data = (T[]) new Object[arrayList.size()];
         for (int i = 0; i < data.length; i++) {
             data[i] = arrayList.get(i).getData();
         }
 
         return data;
     }
 }
 
 
 /**
  * The main wrapper to encapsulate any object where Reflection is needed.
  * <p>
  * Without any boilerplate code Reflection can become exponentially more tedious
  * as the requirements scale, and this class aims to largely solve that issue.
  * <p>
  * An example for these is seen in the {@link CircularSinglyLinkedListNodeWrapper}.
  */
 class ReflectionWrapper {
     /**
      * The internal reference to the object the Reflection API is accessing
      */
     private final Object internalReference;
     Method[] methods;
 
     /**
      * Constructs a new ReflectionWrapper around a given object.
      *
      * @param internalReference The object to wrap around
      */
     public ReflectionWrapper(Object internalReference) {
         this.internalReference = internalReference;
     }
 
     /**
      * Retrives a method from the internalObject, regardless of whether it's private or not.
      *
      * @param method       The method to retrieve
      * @param methodInputs The inputs to pull
      * @return The given method in the target class
      * @throws NoSuchMethodException If the method was unable to be found
      */
     public Method getMethod(String method, Class<?>... methodInputs) throws NoSuchMethodException {
         Method m = internalReference.getClass().getDeclaredMethod(method, methodInputs);
         m.setAccessible(true);
         return m;
     }
 
     /**
      * Retrieves and invokes a method from the internalObject, regardless of whether it's prviate or not.
      *
      * @param method The method to retrieve
      * @param input  The inputs to pull
      * @return The return value of the invoked method
      */
     public Object invokeMethod(String method, Object... input) {
         try {
             Class<?>[] classes = new Class<?>[input.length];
 
             for (int i = 0; i < classes.length; i++) {
                 classes[i] = input.getClass();
             }
 
             return getMethod(method, classes).invoke(internalReference, input);
 
         } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
             throw new RuntimeException(e);
         }
     }
 
     /**
      * Sets a field, regardless of its visibility, to a given value.
      *
      * @param fieldName The name of the field
      * @param value     The value to set the field to
      * @throws NoSuchFieldException   If the field was unable to be found
      * @throws IllegalAccessException If the field has some sort of protection (should in theory never occur)
      */
     public void setValue(String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
         Field field = internalReference.getClass().getDeclaredField(fieldName);
 
         field.setAccessible(true);
 
         field.set(internalReference, value);
     }
 
     /**
      * Retrieves the internal object of the wrapper.
      *
      * @return The internal reference to the object under the wrapper.
      */
     public Object getInternalReference() {
         return internalReference;
     }
 }