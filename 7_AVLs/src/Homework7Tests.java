 import org.junit.experimental.runners.Enclosed;
 import org.junit.runner.RunWith;
 import org.junit.Test;
 import java.util.NoSuchElementException;
 import static org.junit.Assert.*;
 import java.util.HashSet;
 import java.util.Set;
 import static org.junit.Assert.assertEquals;
 import org.junit.Assert;
 import java.util.ArrayList;
 import static org.junit.Assert.assertNotSame;
 import java.util.Arrays;
 import java.lang.reflect.Field;
 import java.lang.reflect.InvocationTargetException;
 import java.lang.reflect.Method;
 import java.io.PrintStream;
 import com.sun.source.tree.Tree;
 import java.util.Comparator;
 import java.util.List;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;
 import java.io.*;
 import java.nio.charset.StandardCharsets;
 import java.util.*;
 import java.util.zip.GZIPInputStream;
 @RunWith(Enclosed.class)
 public class Homework7Tests {
     
     
     
     
     public static class ContainsTests {
     
         @Test
         public void emptyAVL() {
             AVL<Integer> avl = new AVL<>();
             assertFalse(avl.contains(-10));
             assertFalse(avl.contains(0));
             assertFalse(avl.contains(1));
         }
     
         @Test
         public void dataIsInTree() {
     
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 3, 1, 5, null, 2, 4, 6, null, null, null, null, null, null, null, 7});
     
             TreeTraversalHelper.printAVL(avl);
     
             // Should all be in tree
             for (int i = 1; i <= 7; ++i) {
                 assertTrue(avl.contains(i));
             }
     
         }
     
         @Test
         public void searchForNull() {
     
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 3, 1, 5, null, 2, 4, 6, null, null, null, null, null, null, null, 7});
     
             ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () -> avl.contains(null));
     
         }
     
         @Test
         public void useValueEquality() {
     
             AVL<String> avl = AVLWrapper.constructAVL(new String[] {null, "Midknight", null, null});
             String differentRef = new String("Midknight");
     
             assertTrue(avl.contains(differentRef));
     
         }
     
         @Test
         public void dataNotInTree() {
     
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 3, 1, 5, null, 2, 4, 6, null, null, null, null, null, null, null, 7});
     
             TreeTraversalHelper.printAVL(avl);
     
             assertFalse(avl.contains(-1));
             assertFalse(avl.contains(0));
             assertFalse(avl.contains(8));
             assertFalse(avl.contains(9));
     
             AssertUtils.assertAVLEquals(avl, new Integer[] {null, 3, 1, 5, null, 2, 4, 6, null, null, null, null, null, null, null, 7});
     
         }
     
     }
 
     
     
     
     
     public static class ElementsWithinDistanceTests {
     
         @Test
         public void dataNull() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 10, 5, 15});
             TreeTraversalHelper.printAVL(avl);
             ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () -> avl.elementsWithinDistance(null, 3));
         }
     
         @Test
         public void negativeDistance() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 10, 5, 15});
             TreeTraversalHelper.printAVL(avl);
             ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () -> avl.elementsWithinDistance(5, -1));
         }
     
         @Test
         public void noSuchElement() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 10, 5, 15});
             TreeTraversalHelper.printAVL(avl);
             ExceptionUtils.assertExceptionIsCorrect(NoSuchElementException.class, () -> avl.elementsWithinDistance(670, 3));
         }
     
         @Test
         public void leafDistanceZero() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 32, 16, 48, 8, 24, 40, 56, 4, 12, 20, 28, 36, 44, 52, 60, 2, 6, 10, 14, 18, 22, 26, 30, 34, 38, 42, 46, 50, 54, 58, 62, 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39, 41, 43, 45, 47, 49, 51, 53, 55, 57, 59, 61, 63});
             TreeTraversalHelper.printAVL(avl);
             assertEquals(setOf(11), avl.elementsWithinDistance(11, 0));
             assertEquals(setOf(21), avl.elementsWithinDistance(21, 0));
             assertEquals(setOf(31), avl.elementsWithinDistance(31, 0));
             assertEquals(setOf(41), avl.elementsWithinDistance(41, 0));
             assertEquals(setOf(51), avl.elementsWithinDistance(51, 0));
             assertEquals(setOf(61), avl.elementsWithinDistance(61, 0));
         }
     
         @Test
         public void leafDistanceOne() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 32, 16, 48, 8, 24, 40, 56, 4, 12, 20, 28, 36, 44, 52, 60, 2, 6, 10, 14, 18, 22, 26, 30, 34, 38, 42, 46, 50, 54, 58, 62, 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39, 41, 43, 45, 47, 49, 51, 53, 55, 57, 59, 61, 63});
             TreeTraversalHelper.printAVL(avl);
             assertEquals(setOf(11, 10), avl.elementsWithinDistance(11, 1));
             assertEquals(setOf(21, 22), avl.elementsWithinDistance(21, 1));
             assertEquals(setOf(31, 30), avl.elementsWithinDistance(31, 1));
         }
     
         @Test
         public void internalNodeDistanceOne() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 32, 16, 48, 8, 24, 40, 56, 4, 12, 20, 28, 36, 44, 52, 60, 2, 6, 10, 14, 18, 22, 26, 30, 34, 38, 42, 46, 50, 54, 58, 62, 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39, 41, 43, 45, 47, 49, 51, 53, 55, 57, 59, 61, 63});
             TreeTraversalHelper.printAVL(avl);
             assertEquals(setOf(12, 9, 11, 10), avl.elementsWithinDistance(10, 1));
         }
     
         @Test
         public void internalNodeDistanceTwo() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 32, 16, 48, 8, 24, 40, 56, 4, 12, 20, 28, 36, 44, 52, 60, 2, 6, 10, 14, 18, 22, 26, 30, 34, 38, 42, 46, 50, 54, 58, 62, 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39, 41, 43, 45, 47, 49, 51, 53, 55, 57, 59, 61, 63});
             TreeTraversalHelper.printAVL(avl);
             assertEquals(setOf(10, 9, 11, 12, 14, 8), avl.elementsWithinDistance(10, 2));
         }
     
         @Test
         public void internalNodeDistanceThree() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 32, 16, 48, 8, 24, 40, 56, 4, 12, 20, 28, 36, 44, 52, 60, 2, 6, 10, 14, 18, 22, 26, 30, 34, 38, 42, 46, 50, 54, 58, 62, 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39, 41, 43, 45, 47, 49, 51, 53, 55, 57, 59, 61, 63});
             TreeTraversalHelper.printAVL(avl);
             assertEquals(setOf(10, 9, 11, 12, 14, 13, 15, 8, 4, 16), avl.elementsWithinDistance(10, 3));
             assertEquals(setOf(9, 10, 11, 12, 13, 14, 15, 8, 4, 2, 6, 16, 32, 24), avl.elementsWithinDistance(12, 3));
         }
     
         @Test
         public void highUpNodeDistanceThree() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 32, 16, 48, 8, 24, 40, 56, 4, 12, 20, 28, 36, 44, 52, 60, 2, 6, 10, 14, 18, 22, 26, 30, 34, 38, 42, 46, 50, 54, 58, 62, 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39, 41, 43, 45, 47, 49, 51, 53, 55, 57, 59, 61, 63});
             TreeTraversalHelper.printAVL(avl);
             assertEquals(setOf(16, 2, 4, 6, 8, 10, 12, 14, 18, 20, 22, 24, 26, 28, 30, 32, 48, 40, 56), avl.elementsWithinDistance(16, 3));
         }
     
         @Test
         public void rootNodeDistanceZero() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 32, 16, 48, 8, 24, 40, 56, 4, 12, 20, 28, 36, 44, 52, 60, 2, 6, 10, 14, 18, 22, 26, 30, 34, 38, 42, 46, 50, 54, 58, 62, 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39, 41, 43, 45, 47, 49, 51, 53, 55, 57, 59, 61, 63});
             TreeTraversalHelper.printAVL(avl);
             assertEquals(setOf(32), avl.elementsWithinDistance(32, 0));
         }
     
         @Test
         public void rootNodeDistanceOne() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 32, 16, 48, 8, 24, 40, 56, 4, 12, 20, 28, 36, 44, 52, 60, 2, 6, 10, 14, 18, 22, 26, 30, 34, 38, 42, 46, 50, 54, 58, 62, 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39, 41, 43, 45, 47, 49, 51, 53, 55, 57, 59, 61, 63});
             TreeTraversalHelper.printAVL(avl);
             assertEquals(setOf(32, 16, 48), avl.elementsWithinDistance(32, 1));
         }
     
         @Test
         public void leafNodeLargeDistance() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 32, 16, 48, 8, 24, 40, 56, 4, 12, 20, 28, 36, 44, 52, 60, 2, 6, 10, 14, 18, 22, 26, 30, 34, 38, 42, 46, 50, 54, 58, 62, 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39, 41, 43, 45, 47, 49, 51, 53, 55, 57, 59, 61, 63});
             TreeTraversalHelper.printAVL(avl);
             assertEquals(setOf(2, 4, 6, 8, 10, 12, 14, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 48, 40, 56), avl.elementsWithinDistance(31, 7));
         }
     
         private static Set<Integer> setOf(Integer... arr) {
             Set<Integer> set = new HashSet<>();
             for (Integer item : arr) {
                 set.add(item);
             }
             return set;
         }
     
     }
 
     
     
     
     /**
      * Tests for your Constructors. Also tangentially tests add().
      * @author Justin
      */
     public static class ConstructorTests {
     
         @Test
         public void testNoArgConstructor() {
             AVL<Integer> avl = new AVL<>();
             AssertUtils.assertAVLEquals(avl, new Integer[2]);
         }
     
         @Test
         public void testOneArgConstructor() {
             ArrayList<Integer> data = new ArrayList<>();
             data.add(3);
             data.add(5);
             data.add(1);
             data.add(2);
             data.add(4);
             data.add(6);
             data.add(7);
             AVL<Integer> avl = new AVL<>(data);
             AssertUtils.assertAVLEquals(avl, new Integer[] {null, 3, 1, 5, null, 2, 4, 6, null, null, null, null, null, null, null, 7});
         }
     
     }
 
     
     
     
     
     public static class RemoveTests {
     
         @Test
         public void removeNullData() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 10, 5});
             TreeTraversalHelper.printAVL(avl);
             ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () -> avl.remove(null));
             AssertUtils.assertAVLEquals(avl, new Integer[] {null, 10, 5});
         }
     
         @Test
         public void noSuchElement() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 10, 5});
             TreeTraversalHelper.printAVL(avl);
             ExceptionUtils.assertExceptionIsCorrect(NoSuchElementException.class, () -> avl.remove(6897));
             AssertUtils.assertAVLEquals(avl, new Integer[] {null, 10, 5});
         }
     
         @Test
         public void removeFinalElement() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 1923});
             TreeTraversalHelper.printAVL(avl);
             int removed = avl.remove(1923);
             AssertUtils.assertAVLEquals(avl, new Integer[] {null, null});
             assertEquals(1923, removed);
         }
     
         @Test
         public void removeLeaf() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 20, 10, 30});
             TreeTraversalHelper.printAVL(avl);
             int removed = avl.remove(10);
             AssertUtils.assertAVLEquals(avl, new Integer[] {null, 20, null, 30});
             assertEquals(10, removed);
         }
     
         @Test
         public void removeLeafTwo() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 20, null, 30});
             TreeTraversalHelper.printAVL(avl);
             int removed = avl.remove(30);
             AssertUtils.assertAVLEquals(avl, new Integer[] {null, 20});
             assertEquals(30, removed);
         }
     
         @Test
         public void removeLeafLargeTree() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 20, 10, 30, 5, 15, 25, 35, 2, 7, 12, 17, 22, 27, 32, 37});
             TreeTraversalHelper.printAVL(avl);
             int removed = avl.remove(12);
             AssertUtils.assertAVLEquals(avl, new Integer[] {null, 20, 10, 30, 5, 15, 25, 35, 2, 7, null, 17, 22, 27, 32, 37});
             assertEquals(12, removed);
         }
     
         @Test
         public void removeInternalNodeOneChild() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 20, 10, 30, 5, 15, 25, 35, 2, 7, null, 17, 22, 27, 32, 37});
             TreeTraversalHelper.printAVL(avl);
             int removed = avl.remove(15);
             AssertUtils.assertAVLEquals(avl, new Integer[] {null, 20, 10, 30, 5, 17, 25, 35, 2, 7, null, null, 22, 27, 32, 37});
             assertEquals(15, removed);
         }
     
         @Test
         public void removeLeafRightRotation() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 20, 10, 30, 5, 17, 25, 35, 2, 7, null, null, 22, 27, 32, 37});
             TreeTraversalHelper.printAVL(avl);
             int removed = avl.remove(17);
             AssertUtils.assertAVLEquals(avl, new Integer[] {null, 20, 5, 30, 2, 10, 25, 35, null, null, 7, null, 22, 27, 32, 37});
             assertEquals(17, removed);
         }
     
         @Test
         public void removeLeafLeftRightRotation() {
         AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 20, 10, 30, 5, 17, 25, 35, null, 7, null, null, 22, 27, 32, 37, null, null, null});
         TreeTraversalHelper.printAVL(avl);
         int removed = avl.remove(17);
         AssertUtils.assertAVLEquals(avl, new Integer[] {null, 20, 7, 30, 5, 10, 25, 35, null, null, null, null, 22, 27, 32, 37});
         assertEquals(17, removed);
         }
     
         @Test
         public void removeLeafLeftRotation() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 20, 10, 30, 5, 17, 25, 35, 2, 7, 12, 17, null, null, 32, 37});
             TreeTraversalHelper.printAVL(avl);
             int removed = avl.remove(25);
             AssertUtils.assertAVLEquals(avl, new Integer[] {null, 20, 10, 35, 5, 17, 30, 37, 2, 7, 12, 17, null, 32, null, null});
             assertEquals(25, removed);
         }
     
         @Test
         public void removeLeafRightLeftRotation() {
         AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 20, 10, 30, 5, 17, 25, 35, 2, 7, 12, 17, null, null, 32, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null});
         TreeTraversalHelper.printAVL(avl);
         int removed = avl.remove(25);
         AssertUtils.assertAVLEquals(avl, new Integer[] {null, 20, 10, 32, 5, 17, 30, 35, 2, 7, 12, 17, null, null});
         assertEquals(25, removed);
         }
     
         @Test
         public void removeInternalNodeTwoChildrenRightRotation() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 20, 10, 32, 5, 17, 30, 35, 2, 7, 12, 17, 27, 31});
             TreeTraversalHelper.printAVL(avl);
             int removed = avl.remove(32);
             AssertUtils.assertAVLEquals(avl, new Integer[] {null, 20, 10, 30, 5, 17, 27, 35, 2, 7, 12, 17, null, null, 31, null});
             assertEquals(32,  removed);
         }
     
         @Test
         public void removeInternalNodeTwoChildrenLeftRightRotation() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 20, 10, 32, 5, 17, 30, 35, 2, 7, 12, 17, null, 31});
             TreeTraversalHelper.printAVL(avl);
             int removed = avl.remove(32);
             AssertUtils.assertAVLEquals(avl, new Integer[] {null, 20, 10, 31, 5, 17, 30, 35, 2, 7, 12, 17, null, null, null, null});
             assertEquals(32,  removed);
         }
     
         @Test
         public void removeRootNoRotation() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 20, 10, 30, 5, 15, 25, 35, 2, 7, 12, 17, 22, 27, 32, 37});
             TreeTraversalHelper.printAVL(avl);
             int removed = avl.remove(20);
             AssertUtils.assertAVLEquals(avl, new Integer[] {null, 22, 10, 30, 5, 15, 25, 35, 2, 7, 12, 17, null, 27, 32, 37});
             assertEquals(20,  removed);
         }
     
         @Test
         public void removeRootNoRotationTwo() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 22, 10, 30, 5, 15, 25, 35, 2, 7, 12, 17, null, 27, 32, 37});
             TreeTraversalHelper.printAVL(avl);
             int removed = avl.remove(22);
             AssertUtils.assertAVLEquals(avl, new Integer[] {null, 25, 10, 30, 5, 15, 27, 35, 2, 7, 12, 17, null, null, 32, 37});
             assertEquals(22,  removed);
         }
     
         @Test
         public void removeRootLeftRotation() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 25, 10, 30, 5, 15, 27, 35, 2, 7, 12, 17, null, null, 32, 37});
             TreeTraversalHelper.printAVL(avl);
             int removed = avl.remove(25);
             AssertUtils.assertAVLEquals(avl, new Integer[] {null, 27, 10, 35, 5, 15, 30, 37, 2, 7, 12, 17, null, 32});
             assertEquals(25,  removed);
         }
     
         @Test
         public void removeRootRightRotation() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 27, 10, 35, 5, 15, 30, 37, 2, 7, 12, 17, null, 32, null, null, 1});
             TreeTraversalHelper.printAVL(avl);
             int removed = avl.remove(27);
             AssertUtils.assertAVLEquals(avl, new Integer[] {null, 10, 5, 30, 2, 7, 15, 35, 1, null, null, null, 12, 17, 32, 37});
             assertEquals(27,  removed);
         }
     
         @Test
         public void removeInternalNodeRightRotation() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 10, 5, 30, 2, 7, 15, 35, 1, null, null, null, 12, 17, 32, 37});
             TreeTraversalHelper.printAVL(avl);
             int removed = avl.remove(5);
             AssertUtils.assertAVLEquals(avl, new Integer[] {null, 10, 2, 30, 1, 7, 15, 35, null, null, null, null, 12, 17, 32, 37});
             assertEquals(5,  removed);
         }
     
         @Test
         public void removeLeafMultipleRotations() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 10, 2, 30, 1, 7, 15, 35, null, null, null, null, 12, 17, 32, 37, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 39});
             TreeTraversalHelper.printAVL(avl);
             int removed = avl.remove(32);
             AssertUtils.assertAVLEquals(avl, new Integer[] {null, 10, 2, 30, 1, 7, 15, 37, null, null, null, null, 12, 17, 35, 39});
             assertEquals(32,  removed);
         }
     
         @Test
         public void removeSeveralRotations() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 21, 13, 29, 8, 18, 26, 32, 5, 11, 16, 20, 24, 28, 31, 33, 3, 7, 10, 12, 15, 17, 19, null, 23, 35, 27, null, 30, null, null, null, 2, 4, 6, null, 9, null, null, null, 14, null, null, null, null, null, null, null, 22, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 1});
             TreeTraversalHelper.printAVL(avl);
             int removed = avl.remove(32);
             AssertUtils.assertAVLEquals(avl, new Integer[] {null, 13, 8, 21, 5, 11, 18, 26, 3, 7, 10, 12, 16, 20, 24, 29, 2, 4, 6, null, 9, null, null, null, 15, 17, 19, null, 23, 35, 28, 31, 1, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 14, null, null, null, null, null, null, null, 22, null, null, null, 27, null, 30, 33});
             assertEquals(32,  removed);
         }
     
     }
 
     
     
     
     public static class HeightTests {
     
         @Test
         public void emptyAVL() {
             AVL<Integer> empty = new AVL<>();
             assertEquals(-1, empty.height());
         }
     
         @Test
         public void heightZero() {
             AVL<Integer> empty = new AVL<>();
             empty.add(1);
             assertEquals(0, empty.height());
     
     
             empty = AVLWrapper.constructAVL(new Integer[] {null, 3});
             assertEquals(0, empty.height());
         }
     
         @Test
         public void nonEmptyTree() {
             AVL<Integer> empty = AVLWrapper.constructAVL(new Integer[] {null, 3, 1, 5, null, 2, 4, 6, null, null, null, null, null, null, null, 7});
             assertEquals(3, empty.height());
         }
     
     }
 
     
     
     public static class ClearTests {
         @Test
         public void emptyAVL() {
             AVL<Integer> empty = new AVL<>();
             empty.clear();
             AssertUtils.assertAVLEquals(empty, new Integer[] {null, null});
         }
     
         @Test
         public void nonEmpty() {
             AVL<Integer> empty = new AVL<>();
             empty.add(1);
             empty.add(2);
             empty.add(3);
             empty.add(4);
             empty.add(5);
             empty.clear();
             AssertUtils.assertAVLEquals(empty, new Integer[] {null, null});
         }
     
         @Test
         public void nonEmptyTwo() {
             AVL<Integer> empty = AVLWrapper.constructAVL(new Integer[] {null, 4, 1, 3});
             empty.clear();
             AssertUtils.assertAVLEquals(empty, new Integer[] {null, null});
         }
     }
 
     
     
     
     public static class AddTests {
     
         @Test
         public void addNullToEmpty () {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, null});
             ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () -> avl.add(null));
         }
     
         @Test
         public void addNullToNonEmpty () {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 3, 1, 2});
             ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () -> avl.add(null));
         }
     
         @Test
         public void addToEmpty() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, null});
             TreeTraversalHelper.printAVL(avl);
             avl.add(100);
             AssertUtils.assertAVLEquals(avl, new Integer[] {null, 100});
         }
     
         @Test
         public void addRight() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 10});
             TreeTraversalHelper.printAVL(avl);
             avl.add(15);
             AssertUtils.assertAVLEquals(avl, new Integer[] {null, 10, null, 15});
         }
     
         @Test
         public void addLeft() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 10});
             TreeTraversalHelper.printAVL(avl);
             avl.add(5);
             AssertUtils.assertAVLEquals(avl, new Integer[] {null, 10, 5, null});
         }
     
         @Test
         public void addLeftRotation() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 10, null, 15});
             TreeTraversalHelper.printAVL(avl);
             avl.add(20);
             AssertUtils.assertAVLEquals(avl, new Integer[] {null, 15, 10, 20});
         }
     
         @Test
         public void addRightRotation() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 10, 5});
             TreeTraversalHelper.printAVL(avl);
             avl.add(0);
             AssertUtils.assertAVLEquals(avl, new Integer[] {null, 5, 0, 10});
         }
     
         @Test
         public void addRightLeftRotation() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 10, null, 20});
             TreeTraversalHelper.printAVL(avl);
             avl.add(15);
             AssertUtils.assertAVLEquals(avl, new Integer[] {null, 15, 10, 20});
         }
     
         @Test
         public void addLeftRightRotation() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 10, 0, null});
             TreeTraversalHelper.printAVL(avl);
             avl.add(5);
             AssertUtils.assertAVLEquals(avl, new Integer[] {null, 5, 0, 10});
         }
     
         @Test
         public void addLeftRotationWithChild() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 10, 0, 20, null, null, 15, 25});
             TreeTraversalHelper.printAVL(avl);
             avl.add(30);
             AssertUtils.assertAVLEquals(avl, new Integer[] {null, 20, 10, 25, 0, 15, null, 30});
         }
     
         @Test
         public void addRightRotationWithChild() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 20, 10, 30, 5, 15, null, null});
             TreeTraversalHelper.printAVL(avl);
             avl.add(0);
             AssertUtils.assertAVLEquals(avl, new Integer[] {null, 10, 5, 20, 0, null, 15, 30});
         }
     
         @Test
         public void addLeftRightRotationWithChild() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 20, 10, 30, 5, 15, null, null});
             TreeTraversalHelper.printAVL(avl);
             avl.add(17);
             AssertUtils.assertAVLEquals(avl, new Integer[] {null, 15, 10, 20, 5, null, 17, 30});
         }
     
         @Test
         public void addRightLeftRotationWithChild() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 20, 10, 30, null, null, 25, 35});
             TreeTraversalHelper.printAVL(avl);
             avl.add(22);
             AssertUtils.assertAVLEquals(avl, new Integer[] {null, 25, 20, 30, 10, 22, null, 35});
         }
     
         @Test
         public void addToLargeTreeRightRotation() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 20, 10, 30, 5, 15, 25, 35, 3, 7, 12, 17, 22, 27, 32, 37, 1});
             TreeTraversalHelper.printAVL(avl);
             avl.add(0);
             AssertUtils.assertAVLEquals(avl, new Integer[] {null, 20, 10, 30, 5, 15, 25, 35, 1, 7, 12, 17, 22, 27, 32, 37, 0, 3});
         }
     
         @Test
         public void addToLargeTreeLeftRightRotation() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 20, 10, 30, 5, 15, 25, 35, 3, 7, 12, 17, 22, 27, 32, 37, 1});
             TreeTraversalHelper.printAVL(avl);
             avl.add(2);
             AssertUtils.assertAVLEquals(avl, new Integer[] {null, 20, 10, 30, 5, 15, 25, 35, 2, 7, 12, 17, 22, 27, 32, 37, 1, 3});
         }
     
         @Test
         public void addToLargeTreeLeftRotation() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 20, 10, 30, 5, 15, 25, 35, 2, 6, 12, 17, 22, 27, 32, 37, null, null, null, 8});
             TreeTraversalHelper.printAVL(avl);
             avl.add(9);
             AssertUtils.assertAVLEquals(avl, new Integer[] {null, 20, 10, 30, 5, 15, 25, 35, 2, 8, 12, 17, 22, 27, 32, 37, null, null, 6, 9});
         }
     
         @Test
         public void addToLargeTreeRightLeftRotation() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 20, 10, 30, 5, 15, 25, 35, 2, 6, 12, 17, 22, 27, 32, 37, null, null, null, 8});
             TreeTraversalHelper.printAVL(avl);
             avl.add(7);
             AssertUtils.assertAVLEquals(avl, new Integer[] {null, 20, 10, 30, 5, 15, 25, 35, 2, 7, 12, 17, 22, 27, 32, 37, null, null, 6, 8});
         }
     
         @Test
         public void rightRotationNeededHigherUp() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 20, 10, 30, 5, 15, 25, 35, 2, 6, null, null, 22, 27, 32, 37});
             TreeTraversalHelper.printAVL(avl);
             avl.add(1);
             AssertUtils.assertAVLEquals(avl, new Integer[] {null, 20, 5, 30, 2, 10, 25, 35, 1, null, 6, 15, 22, 27, 32, 37});
         }
     
         @Test
         public void leftRightRotationNeededHigherUp() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 20, 10, 30, 5, 15, 25, 35, 2, 6, null, null, 22, 27, 32, 37});
             TreeTraversalHelper.printAVL(avl);
             avl.add(7);
             AssertUtils.assertAVLEquals(avl, new Integer[] {null, 20, 6, 30, 5, 10, 25, 35, 2, null, 7, 15, 22, 27, 32, 37});
         }
     
         @Test
         public void leftRotationNeededHigherUp() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 20, 10, 30, 5, 15, 25, 35, null, null, 12, 17, 22, 27, 32, 37});
             TreeTraversalHelper.printAVL(avl);
             avl.add(19);
             AssertUtils.assertAVLEquals(avl, new Integer[] {null, 20, 15, 30, 10, 17, 25, 35, 5, 12, null, 19, 22, 27, 32, 37});
         }
     
         @Test
         public void rightLeftRotationNeededHigherUp() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 20, 10, 30, 5, 15, 25, 35, null, null, 12, 17, 22, 27, 32, 37});
             TreeTraversalHelper.printAVL(avl);
             avl.add(11);
             AssertUtils.assertAVLEquals(avl, new Integer[] {null, 20, 12, 30, 10, 15, 25, 35, 5, 11, null, 17, 22, 27, 32, 37});
         }
     
         @Test
         public void leftRotationNeededAtRoot() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 20, 10, 30, 5, 15, 25, 35, null, null, null, null, 22, 27, 32, 37});
             TreeTraversalHelper.printAVL(avl);
             avl.add(40);
             AssertUtils.assertAVLEquals(avl, new Integer[] {null, 30, 20, 35, 10, 25, 32, 37, 5, 15, 22, 27, null, null, null, 40});
         }
     
         @Test
         public void rightLeftRotationNeededAtRoot() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 20, 10, 30, 5, 15, 25, 35, null, null, null, null, 22, 27, 32, 37});
             TreeTraversalHelper.printAVL(avl);
             avl.add(28);
             AssertUtils.assertAVLEquals(avl, new Integer[] {null, 25, 20, 30, 10, 22, 27, 35, 5, 15, null, null, null, 28, 32, 37});
         }
     
         @Test
         public void rightRotationNeededAtRoot() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 20, 10, 30, 5, 15, 25, 35, 2, 7, 12, 17});
             TreeTraversalHelper.printAVL(avl);
             avl.add(0);
             AssertUtils.assertAVLEquals(avl, new Integer[] {null, 10, 5, 20, 2, 7, 15, 30, 0, null, null, null, 12, 17, 25, 35});
         }
     
         @Test
         public void leftRightRotationNeededAtRoot() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 20, 10, 30, 5, 15, 25, 35, 2, 7, 12, 17});
             TreeTraversalHelper.printAVL(avl);
             avl.add(19);
             AssertUtils.assertAVLEquals(avl, new Integer[] {null, 15, 10, 20, 5, 12, 17, 30, 2, 7, null, null, null, 19, 25, 35});
         }
     
         @Test
         public void addDuplicateData() {
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 4, 2, 6, 1, 3, 5, 7});
             TreeTraversalHelper.printAVL(avl);
             for (int i = 1; i <= 7; ++i) {
                 avl.add(i);
             }
             AssertUtils.assertAVLEquals(avl, new Integer[] {null, 4, 2, 6, 1, 3 ,5, 7});
         }
     
         @Test
         public void usesValueEquality() {
             AVL<String> avl = AVLWrapper.constructAVL(new String[] {null, "a", "Str", "aea"});
             TreeTraversalHelper.printAVL(avl);
     
             String differentReference = new String("a");
             AssertUtils.assertAVLEquals(avl, new String[] {null, "a", "Str", "aea"});
         }
     
     }
 
     
     
     
     
     public static class GetTests {
     
         @Test
         public void emptyAVL() {
             AVL<Integer> avl = new AVL<>();
             ExceptionUtils.assertExceptionIsCorrect(NoSuchElementException.class, () -> avl.get(3));
         }
     
         @Test
         public void dataIsInTree() {
     
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 3, 1, 5, null, 2, 4, 6, null, null, null, null, null, null, null, 7});
     
             TreeTraversalHelper.printAVL(avl);
     
             // Should all be in tree
             for (int i = 1; i <= 7; ++i) {
                 assertEquals(i, (int) avl.get(i));
             }
     
         }
     
         @Test
         public void searchForNull() {
     
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 3, 1, 5, null, 2, 4, 6, null, null, null, null, null, null, null, 7});
     
             ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () -> avl.get(null));
     
         }
     
         @Test
         public void useValueEquality() {
     
             AVL<String> avl = AVLWrapper.constructAVL(new String[] {null, "Midknight", null, null});
             String differentRef = new String("Midknight");
     
             assertEquals("Midknight", avl.get(differentRef));
             assertNotSame(differentRef, avl.get(differentRef));
     
         }
     
         @Test
         public void dataNotInTree() {
     
             AVL<Integer> avl = AVLWrapper.constructAVL(new Integer[] {null, 3, 1, 5, null, 2, 4, 6, null, null, null, null, null, null, null, 7});
     
             TreeTraversalHelper.printAVL(avl);
     
             ExceptionUtils.assertExceptionIsCorrect(NoSuchElementException.class, () -> avl.get(-1));
             ExceptionUtils.assertExceptionIsCorrect(NoSuchElementException.class, () -> avl.get(0));
             ExceptionUtils.assertExceptionIsCorrect(NoSuchElementException.class, () -> avl.get(8));
             ExceptionUtils.assertExceptionIsCorrect(NoSuchElementException.class, () -> avl.get(9));
     
             AssertUtils.assertAVLEquals(avl, new Integer[] {null, 3, 1, 5, null, 2, 4, 6, null, null, null, null, null, null, null, 7});
     
         }
     
     }
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 }
 
 
 
 class TreeTraversalHelper {
     public static <T extends Comparable<? super T>> String[] generateTree (AVL<T> avl) {
 
         AVLNode<T>[] array = traverseAVL(avl);
 
         int nonNullCount = 0;
 
         T[] data = (T[]) new Comparable[array.length];
 
         for (int i = 0; i < data.length; ++i) {
             if (array[i] == null) {
                 data[i] = null;
             } else {
                 nonNullCount++;
                 data[i] = array[i].getData();
             }
         }
 
         if (nonNullCount == 0) {
             // Special case: empty trees
             return new String[] { "", "", "          <EMPTY TREE>", "", "" } ;
         }
 
         return TreeDrawingTool.generateTree(AssertUtils.convertToStringArray(data), true);
 
     }
 
     public static <T extends Comparable<? super T>> AVLNode<T>[] traverseAVL (AVL<T> avl) {
 
         ArrayList<AVLNode<T>> list = new ArrayList<>();
         list.add(null); // Index 0 should be empty.
 
         ArrayList<AVLNode<T>> queue = new ArrayList<>();
 
         AVLNode<T> root = avl.getRoot();
         queue.add(root);
 
         int lastNonNull = 1;
         int index = 1;
         while (lastNonNull >= index / 2) {
 
             AVLNode<T> removed = queue.remove(0);
 
             if (removed == null) {
                 queue.add(null);
                 queue.add(null);
             } else {
                 queue.add(removed.getLeft());
                 queue.add(removed.getRight());
                 lastNonNull = index;
             }
 
             list.add(removed);
 
             index++;
 
         }
 
         AVLNode<T>[] array = new AVLNode[list.size() / 2];
         for (int i = 0; i < array.length; ++i) {
             array[i] = list.get(i);
         }
 
         return array;
 
     }
 
     public static <T extends Comparable<? super T>> void printAVL (AVL<T> avl) {
         System.out.println("--------------------------------------------------\n");
         System.out.println(ColorUtils.formatColorString(AsciiColorCode.BRIGHT_WHITE_BACKGROUND, AsciiColorCode.BLACK_FOREGROUND, "Here is the initial tree:"));
         TreeDrawingTool.printCanvas(generateTree(avl));
     }
 }
 
 
 /**
  * @param <T> The generic used by this AVLNodeWrapper
  */
 class AVLNodeWrapper<T extends Comparable<? super T>> {
     private final ReflectionWrapper wrapper;
 
     public AVLNodeWrapper(AVLNode<T> node) {
         this.wrapper = new ReflectionWrapper(node);
     }
 
     public T getData() {
         try {
             return (T) wrapper.getFieldValue("data");
         } catch (Exception e) {
             throw new RuntimeException(e);
         }
     }
 
 }
 
 
 /**
  * @param <T> The generic used by the AVL as a key.
  */
 class AVLWrapper<T extends Comparable<? super T>> {
 
     private final ReflectionWrapper wrapper;
 
     public AVLWrapper(AVL<T> avl) {
         this.wrapper = new ReflectionWrapper(avl);
     }
 
     /**
      * All the data to be added to the AVL, in level-order traversal. The first index must be left blank.
      * @param data Data to add.
      * @return New AVL constructed according to the data.
      * @param <T> The type of the AVL.
      */
     public static <T extends Comparable<? super T>> AVL<T> constructAVL(AVLNode<T>[] data) {
 
         AVL<T> avl = new AVL<>();
         AVLWrapper<T> wrapper = new AVLWrapper<>(avl);
 
         if (data[1] != null)
             wrapper.forceSetRoot(data[1]);
 
         int size = 0;
         for (int i = data.length - 1; i > 0; --i) {
 
             if (data[i] != null) {
                 if (i * 2 < data.length)
                     data[i].setLeft(data[i * 2]);
                 if (i * 2 + 1 < data.length)
                     data[i].setRight(data[i * 2 + 1]);
 
                 ++size;
             }
 
             updateNode(data[i]);
 
         }
 
         wrapper.forceSetSize(size);
 
         return (AVL<T>) wrapper.wrapper.getInternalReference();
 
     }
 
     public static <T extends Comparable<? super T>> AVL<T> constructAVL(T[] data) {
         AVLNode<T>[] nodes = new AVLNode[data.length];
         for (int i = 0; i < data.length; ++i) {
             nodes[i] = data[i] == null ? null : new AVLNode<>(data[i]);
         }
         return constructAVL(nodes);
     }
 
     /**
      * Updates the height & balance factor of the given node.
      * @param node Node to update.
      * @param <T> Type of the Node.
      */
     private static <T extends Comparable<? super T>> void updateNode(AVLNode<T> node) {
 
         if (node == null)
             return;
 
         int leftHeight = node.getLeft() == null ? -1 : node.getLeft().getHeight();
         int rightHeight = node.getRight() == null ? -1 : node.getRight().getHeight();
         node.setHeight(Math.max(leftHeight, rightHeight) + 1);
         node.setBalanceFactor(leftHeight - rightHeight);
 
     }
 
     public void forceSetSize(int size) {
         try {
             wrapper.setValue("size", size);
         } catch (Exception e) {
             throw new RuntimeException(e);
         }
     }
 
     public void forceSetRoot(AVLNode<T> root) {
         try {
             wrapper.setValue("root", root);
         } catch (Exception e) {
             throw new RuntimeException(e);
         }
     }
 
 
 }
 
 /**
  * The main wrapper to encapsulate any object where Reflection is needed.
  * <p>
  * Without any boilerplate code Reflection can become exponentially more tedious
  * as the requirements scale, and this class aims to largely solve that issue.
  * <p>
  */
 class ReflectionWrapper {
     /**
      * The internal reference to the object the Reflection API is accessing
      */
     private final Object internalReference;
     Method[] methods;
 
     /**
      * Constructs a new ReflectionWrapper around a given object.
      * @param internalReference The object to wrap around
      */
     public ReflectionWrapper(Object internalReference) {
         this.internalReference = internalReference;
     }
 
     /**
      * Retrives a method from the internalObject, regardless of whether it's private or not.
      * @param method The method to retrieve
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
      * Retrives a field from the internalObject, regardless of whether it's private or not.
      * @param field The field to retrieve
      * @return The given field in the target class, with forced visibility
      * @throws NoSuchFieldException If the method was unable to be found
      */
     public Field getField(String field) throws NoSuchFieldException {
         Field f = internalReference.getClass().getDeclaredField(field);
         f.setAccessible(true);
         return f;
     }
 
     /**
      * Retrieves and invokes a method from the internalObject, regardless of whether it's prviate or not.
      * @param method The method to retrieve
      * @param input The inputs to pull
      * @return The return value of the invoked method
      */
     public Object invokeMethod(String method, Object... input)  {
         try {
             Class<?>[] classes = new Class<?>[input.length];
 
             for (int i = 0; i < classes.length; i++) {
                 classes[i] = input[i].getClass();
             }
 
             return getMethod(method, classes).invoke(internalReference, input);
 
         } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
             throw new RuntimeException(e);
         }
     }
 
     /**
      * Sets a field, regardless of its visibility, to a given value.
      * @param fieldName The name of the field
      * @param value The value to set the field to
      * @throws NoSuchFieldException If the field was unable to be found
      * @throws IllegalAccessException If the field has some sort of protection (should in theory never occur)
      */
     public void setValue(String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
         Field field = internalReference.getClass().getDeclaredField(fieldName);
 
         field.setAccessible(true);
 
 //        Field modifiersField = Field.class.getDeclaredField("modifiers");
 //        modifiersField.setAccessible(true);
 //        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
 
         // Necessary because primitive fields cannot be set to their wrapper classes
         if (value.getClass().equals(Integer.class)) {
             field.setInt(internalReference, (int) value);
         } else {
             field.set(internalReference, value);
         }
 
     }
 
     /**
      * Gets a field's value, regardless of its visibility.
      * @param fieldName The name of the field
      * @return The value in the field, as an object
      * @throws NoSuchFieldException If the given field does not exist
      * @throws IllegalAccessException If the field has extra protection (should in theory never occur)
      */
     public Object getFieldValue(String fieldName) throws NoSuchFieldException {
         try {
             return getField(fieldName).get(internalReference);
         } catch (IllegalAccessException e) {
             throw new RuntimeException(e.getMessage());
         }
     }
 
     /**
      * Retrieves the internal object of the wrapper.
      * @return The internal reference to the object under the wrapper.
      */
     public Object getInternalReference() {
         return internalReference;
     }
 }
 
 interface Drawable {
     String draw();
 }
 
 
 
 class ArrayDrawingBuilder {
     private static final int ARROW_SIZE = 1;
 
     private static final int LABEL_WHITESPACE = 10;
 
     private final Object[] objects;
 
     private String finalOutput = "";
 
     private String label;
 
     private int[] highlightedIndices;
 
     private int padding;
 
     public ArrayDrawingBuilder(Object[] objects, int padding) {
         this.objects = Arrays.stream(objects).map(o -> o == null ? "null" : o).toArray();
         this.padding = padding;
     }
 
     public ArrayDrawingBuilder withArrayVisualization() {
         finalOutput += getArrayString(objects);
         return this;
     }
 
     public ArrayDrawingBuilder withPointerAt(int index, String message) {
         // Replace with linkedlist system in next iteration (this was purely for sake of time)
         int pointerIndex = index >= objects.length ? finalOutput.split("\n")[1].length() + 5 : getMiddleIndex(index) + (label != null ? 10 : 0);
 
         for (int i = 0; i < ARROW_SIZE + 1; i++) {
             String symbol = i == 0 ? "^" : "|";
 
             finalOutput += " ".repeat(pointerIndex);
             finalOutput += symbol;
 
             if (i == ARROW_SIZE) {
                 finalOutput += " " + message;
 
             }
 
             finalOutput += "\n";
 
         }
 
 
         return this;
     }
 
     public ArrayDrawingBuilder withHighlightedIndices(int... indices) {
         this.highlightedIndices = indices;
 
         return this;
     }
 
     public ArrayDrawingBuilder withLabel(String label) {
         this.label = label;
 
         String[] lines = finalOutput.split("\n");;
         int lineCount = lines.length;
 
         int insertIndex = 2;
 
         StringBuilder newOutput = new StringBuilder();
 
         String space = " ".repeat(LABEL_WHITESPACE);
 
         for (int i = 0; i < lineCount; i++) {
             if (i == insertIndex) {
                 newOutput.append(label).append(" ".repeat(LABEL_WHITESPACE - label.length()));
             } else {
                 newOutput.append(space);
             }
 
             newOutput.append(lines[i]).append("\n");
         }
 
         finalOutput = newOutput.toString();
 
         return this;
     }
 
     public String build() {
         return finalOutput.stripTrailing();
     }
 
     private int getMiddleIndex(int elementIndex) {
         return elementIndex * padding + (padding / 2);
     }
 
     public String getArrayString(Object[] arr) {
         StringBuilder start = new StringBuilder("\n");
 
         int topLength = arr.length;
 
         for (Object o : arr) {
             topLength += padding;
         }
 
         topLength -= 1;
 
 //        start.append(createLine(topLength));
 
 
         StringBuilder middle = new StringBuilder();
 
         for (int i = 0; i < arr.length; i++) {
             middle.append("|");
 
             boolean highlighted = false;
 
             if (highlightedIndices != null) {
                 for (int highlightedIndex: highlightedIndices) {
                     if (i == highlightedIndex) {
                         highlighted = true;
                         break;
                     }
                 }
             }
 
             int addedPadding = (padding - arr[i].toString().length()) / 2;
 
             middle.append(" ".repeat(addedPadding));
 
 
             if (highlighted) {
                 middle.append(ColorUtils.formatColorString(AsciiColorCode.RED_BACKGROUND, AsciiColorCode.BLACK_FOREGROUND, arr[i].toString()));
             } else {
                 middle.append(arr[i].toString());
             }
 
             middle.append(" ".repeat(addedPadding));
 
         }
 
         middle.append("|");
 
         start.append(createLine(getRawStringLength(middle)));
         start.append(middle).append("\n");
         start.append(createLine(getRawStringLength(middle)));
 
         if (highlightedIndices != null) {
 
             if (highlightedIndices.length != 0) {
                 // start backwards to avoid duplicate
                 for (int index = highlightedIndices.length - 1; index >= 0; index--) {
                     if (highlightedIndices[index] == objects.length) {
                         start.append(ColorUtils.formatColorString(AsciiColorCode.RED_BACKGROUND, AsciiColorCode.BLACK_FOREGROUND, " MISSING "));
                         break;
                     }
                 }
             }
         }
 
         start.append("\n");
 
 //        start.append(createLine(topLength));
 
 
         return start.toString();
     }
 
     private static String createLine(int topLength) {
 
         return "+" + "-".repeat(Math.max(0, topLength - 2)) +
                 "+" +
                 "\n";
     }
 
     private static int getRawStringLength(StringBuilder str) {
         return str.toString().replaceAll("(\\x9B|\\x1B\\[)[0-?]*[ -/]*[@-~]", "").length();
     }
 }
 
 class ArrayElement  implements Drawable {
     private Object element;
 
     public ArrayElement(Object element) {
         this.element = element;
     }
 
     @Override
     public String draw() {
 
 
 
         return null;
     }
 }
 
 
 class TreeDrawingTool {
     
     private static char[][] canvas;
     private static int[][] coords;
 
     /**
      * Generates tree, ignoring the leading null
      */
     public static String[] generateTree(String[] args, boolean ignoreLeadingItem) {
         if (!ignoreLeadingItem)
             return generateTree(args);
 
         String[] withoutLeadingNull = new String[args.length];
         for (int i = 0; i < args.length - 1; ++i) {
             withoutLeadingNull[i] = args[i + 1];
         }
         return generateTree(withoutLeadingNull);
     }
 
     /**
      * Each arg should be length 3
      */
     public static String[] generateTree(String[] args) {
 
         if (args.length == 0) {
             return new String[] {
                     "(No tree drawn, since there were no items in the tree!"
             };
         }
 
         int lastNonNullIndex = args.length - 1;
         while (args[lastNonNullIndex] == null && lastNonNullIndex > 1) {
             --lastNonNullIndex;
         }
 
         String[] noTrailingNulls = new String[lastNonNullIndex + 1];
         for (int i = 0; i < noTrailingNulls.length; ++i) {
             noTrailingNulls[i] = args[i];
         }
 
         args = noTrailingNulls;
 
 
         // Format the args
         for (int i = 0; i < args.length; i++) {
             if (!stringIsValid(args[i])) {
                 args[i] = "no";
             } else if (args[i].length() > 3) {
                 args[i] = args[i].substring(0, 3);
             } else if (args[i].length() == 0) {
                 args[i] = "---";
             } else if (args[i].length() == 1) {
                 args[i] = args[i];
             } else if (args[i].length() == 2) {
                 args[i] = args[i] + ' ';
             }
         }
 
         // calculate size of canvas
         int height = (int) (Math.log(args.length) / Math.log(2)) + 1; // number of rows of data
         int canvasHeight = (height * 1) + (height) * 3; // Each box is 3 tall. Each gap between rows is 3 tall.
         int canvasWidth = (1 << (height-1)) * 8 - 1;
 
         //initialize canvas
         canvas = new char[canvasHeight][];
         for (int r = 0; r < canvasHeight; ++r) {
             char[] row = new char[canvasWidth];
             for (int c = 0; c < canvasWidth; ++c) {
                 row[c] = ' ';
             }
             canvas[r] = row;
         }
 
         // Initialize list of coords
         coords = new int[args.length][];
 
         // Draws all items in canvas
         int currentRow = 1;
         for (int i = 0; i < args.length; i++) {
             if (((i+1) & i) == 0 && i > 0) { //checks if number is a power of 2
                 currentRow += 4;
             }
             int itemsOnThisRow = 1 << ((currentRow - 1) / 4);
             int widthPerItem = (canvasWidth / itemsOnThisRow) + 1;
             int col = (int) (((i + 1 - itemsOnThisRow) + 0.5) * widthPerItem) - 1;
 //            System.out.println("Drawing item " + args[i] + " at " + currentRow + ", " + col);
 //            System.out.printf("There are %d items on this row. widthPerItem is %d\n", itemsOnThisRow, widthPerItem);
             drawItemAt(currentRow, col, args[i]);
             coords[i] = new int[] {currentRow, col, stringIsValid(args[i]) ? 1 : 0};
         }
 
         // Draws all arrows
         for (int i = 0; i <= args.length/2 - 1; ++i) {
             int childOneIndex = (i << 1) + 1;
             int childTwoIndex = (i + 1) << 1;
 
             if (childOneIndex < coords.length)
                 drawArrow(coords[i], coords[childOneIndex]);
             if (childTwoIndex < coords.length)
                 drawArrow(coords[i], coords[childTwoIndex]);
         }
 
         // Returns the canvas
         return getCanvas();
     }
 
     private static void drawArrow(int[] start, int[] end) {
         if (start[2] != 0 && end[2] != 0) { // If it starts at invalid location, don't draw arrow
             setCharAt(start[0] + 1, start[1] + (start[1] > end[1] ? -1 : 1), start[1] > end[1] ? '/' : '\\');
             setCharAt(end[0] - 1, end[1] + (start[1] > end[1] ? 1 : -1), start[1] > end[1] ? '/' : '\\');
 
             if (Math.abs(start[1] - end[1]) == 4) {
                 setCharAt(start[0] + 2, Math.min(start[1], end[1]) + 2, start[1] < end[1] ? '\\' : '/');
             }
             for (int c = Math.min(start[1], end[1]) + 3; c < Math.max(end[1], start[1]) - 2; ++c) {
                 setCharAt(start[0] + 2, c, '-');
             }
         }
     }
     
     private static String[] getCanvas() {
         String[] rowStrings = new String[canvas.length];
 
         int skipColumns = 0;
 
         outerLoop:
         while(skipColumns < canvas[0].length) {
             for (int r = 0; r < canvas.length; ++r) {
                 if (canvas[r][skipColumns] != ' ') {
                     break outerLoop;
                 }
             }
             ++skipColumns;
         }
 
         for (int r = 0; r < rowStrings.length; ++r) {
             StringBuilder row = new StringBuilder();
             for (int c = skipColumns; c < canvas[r].length; ++c) {
                 row.append(canvas[r][c]);
             }
 
             rowStrings[r] = row.toString();
         }
 
         return rowStrings;
     }
 
     public static void printCanvas(String[] rows) {
         for (String row : rows) {
             System.out.println(row);
         }
     }
     
 
     /**
      * Row and col denote the CENTER of each box.
      * Item must be exactly 3 characters wide!
      */
     private static void drawItemAt(int row, int col, String item) {
 
         if (!stringIsValid(item)) return;
         
 //        drawLine(row - 1, col - 1, row - 1, col + 1);
 //        drawLine(row - 1, col - 1, row + 1, col - 1);
 //        drawLine(row + 1, col - 1, row + 1, col + 1);
 //        drawLine(row - 1, col + 1, row + 1, col + 1);
         for (int i = 0; i < item.length(); i++) {
             setCharAt(row, col + i, item.charAt(i));
         }
     }
 
     /**
      * Draws a line at the given two points.
      */
     private static void drawLine(int r1, int c1, int r2, int c2) {
         if (r1 == r2) {
             for (int c = Math.min(c1, c2); c < Math.max(c1, c2); ++c) {
                 setCharAt(r1, c, '-');
             }
         } else {
             for (int r = Math.min(r1, r2); r < Math.max(r1, r2); ++r) {
                 setCharAt(r, c1, '|');
             }
         }
         setCharAt(r1, c1, '+');
         setCharAt(r2, c2, '+');
     }
     
 
     /**
      * Sets the canvas's char at that location to be the given char. If
      * the given coordinates are invalid, then this method does nothing.
      */
     private static void setCharAt(int row, int col, char character) {
         if (row >= 0 && row < canvas.length && col >= 0 && col < canvas[0].length) {
             canvas[row][col] = character;
         }
     }
 
     private static boolean stringIsValid(String str) {
         return !(str == null || str.equalsIgnoreCase("no") || str.equalsIgnoreCase("null"));
     }
     
 }
 
 final class TestConfiguration {
 
     /**
      * Set this boolean to TRUE if you want the TREE visualizations.
      * Set this to FALSE if you want the ARRAY visualizations.
      */
     public static final boolean USE_TREE_DRAWINGS = true;
 
     /**
      * Set this to the desired timeout (in milliseconds).
      */
     public static final int TIMEOUT = 1000;
 }
 
 class ColorUtils {
     /**
      * Formats a string to have an ASCII background in terminal.
      *
      * @param background The ASCII representation of the background color, pulled
      *                   from AsciiColorCode
      * @param s          The string to color
      * @return The colored string
      */
     public static String formatBackgroundColorString(String background, String s) {
         return background + s + AsciiColorCode.RESET_COLOR;
     }
 
     /**
      * Formats a string to have an ASCII foreground (text color) in terminal.
      *
      * @param foreground The ASCII representation of the foreground color, pulled
      *                   from AsciiColorCode
      * @param s          The string to color
      * @return The colored string
      */
     public static String formatForegroundColorString(String foreground, String s) {
         return foreground + s + AsciiColorCode.RESET_COLOR;
 
     }
 
     /**
      * Formats a string to have both an ASCII foreground and background in terminal
      *
      * @param background The ASCII representation of the background color, pulled
      *                   from AsciiColorCode
      * @param foreground The ASCII representation of the foreground color, pulled
      *                   from AsciiColorCode
      * @param s          The string to color
      * @return The colored string
      */
     public static String formatColorString(String background, String foreground, String s) {
         return foreground + background + s.replace("\n", AsciiColorCode.RESET_COLOR + "\n" + foreground + background) + AsciiColorCode.RESET_COLOR;
     }
 }
 final class AsciiColorCode {
 
     public static final String RESET_COLOR = "\033[0m";
 
     public static final String BLACK_FOREGROUND = "\033[30m";
     public static final String BLACK_BACKGROUND = "\033[40m";
 
     public static final String RED_FOREGROUND = "\033[31m";
     public static final String RED_BACKGROUND = "\033[41m";
 
     public static final String GREEN_FOREGROUND = "\033[32m";
     public static final String GREEN_BACKGROUND = "\033[42m";
 
     public static final String YELLOW_FOREGROUND = "\033[33m";
     public static final String YELLOW_BACKGROUND = "\033[43m";
 
     public static final String BLUE_FOREGROUND = "\033[34m";
     public static final String BLUE_BACKGROUND = "\033[44m";
 
     public static final String MAGENTA_FOREGROUND = "\033[35m";
     public static final String MAGENTA_BACKGROUND = "\033[45m";
 
     public static final String CYAN_FOREGROUND = "\033[36m";
     public static final String CYAN_BACKGROUND = "\033[46m";
 
     public static final String WHITE_FOREGROUND = "\033[37m";
     public static final String WHITE_BACKGROUND = "\033[47m";
 
     public static final String BRIGHT_BLACK_FOREGROUND = "\033[90m";
     public static final String BRIGHT_BLACK_BACKGROUND = "\033[100m";
 
     public static final String BRIGHT_RED_FOREGROUND = "\033[91m";
     public static final String BRIGHT_RED_BACKGROUND = "\033[101m";
 
     public static final String BRIGHT_GREEN_FOREGROUND = "\033[92m";
     public static final String BRIGHT_GREEN_BACKGROUND = "\033[102m";
 
     public static final String BRIGHT_YELLOW_FOREGROUND = "\033[93m";
     public static final String BRIGHT_YELLOW_BACKGROUND = "\033[103m";
 
     public static final String BRIGHT_BLUE_FOREGROUND = "\033[94m";
     public static final String BRIGHT_BLUE_BACKGROUND = "\033[104m";
 
     public static final String BRIGHT_MAGENTA_FOREGROUND = "\033[95m";
     public static final String BRIGHT_MAGENTA_BACKGROUND = "\033[105m";
 
     public static final String BRIGHT_CYAN_FOREGROUND = "\033[96m";
     public static final String BRIGHT_CYAN_BACKGROUND = "\033[106m";
 
     public static final String BRIGHT_WHITE_FOREGROUND = "\033[97m";
     public static final String BRIGHT_WHITE_BACKGROUND = "\033[107m";
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
  * A set of utilities to remove redundant assertion boilerplate.
  */
 class AssertUtils {
 
     public static void assertValidException(Exception e) {
         assertTrue(ExceptionUtils.isDescriptiveException(e));
     }
 
     public static void assertArrayEquals(Object[] expected, Object[] actual) {
         try {
             Assert.assertArrayEquals(expected, actual);
         } catch (AssertionError error) {
             try {
                 int max = Math.round((float) (Arrays.stream(expected).map(Object::toString).mapToInt(String::length).max().orElse(5) + 2) / 2) * 2;
 
                 String expectedString = new ArrayDrawingBuilder(expected, max)
                         .withArrayVisualization()
                         .withLabel("Expected")
                         .build();
 
                 ArrayDrawingBuilder actualBuilder = new ArrayDrawingBuilder(actual, max);
 
 
                 String errorMessage = error.getMessage();
 
                 Matcher matcher = Pattern.compile("\\[([0-9]+)\\]").matcher(errorMessage);
 
                 if (matcher.find()) {
                     int index = Integer.parseInt(matcher.group(1));
 
                     actualBuilder = actualBuilder.withHighlightedIndices(index)
                             .withArrayVisualization()
                             .withPointerAt(index, "Element differed")
                             .withLabel("Actual");
 
 
                 } else {
                     actualBuilder = actualBuilder.withArrayVisualization().withLabel("Actual");
                 }
 
                 String actualString = actualBuilder.build();
 
                 String message = expectedString + actualString + "\n" + error.getMessage();
 
                 fail(message);
             } catch (Exception e) {
                 // Catch issues like the inputted array being null when it shouldn't be,
                 // defaulting it to the normal JUnit exception message
                 fail(error.getMessage());
             }
         }
     }
 
     public static <T> void assertListsEqual(List<T> expected, List<T> actual) {
         StringBuilder failMessage = new StringBuilder("Failed! ");
 
         if (expected.size() != actual.size()) {
             failMessage.append("Expected length " + expected.size() + " but recevived length " + actual.size() + ".");
         }
 
         for (int i = 0; i < expected.size(); i++) {
             if (expected.get(i) != actual.get(i)) {
                 failMessage.append(String.format("\nLists differed at index %d. Expected %s, received %s.", i, expected.get(i).toString(), actual.get(i).toString()));
             }
         }
         if (!failMessage.toString().equals("Failed! ")) {
             fail(failMessage.toString());
         }
     }
 
     public static <T extends Comparable<? super T>> void assertAVLEquals(AVL<T> avl, T[] data) {
 
         AVLNode<T>[] nodes = new AVLNode[data.length];
         for (int i = 0; i < data.length; ++i) {
             nodes[i] = data[i] == null ? null : new AVLNode<>(data[i]);
         }
 
         int size = 0;
         for (AVLNode<T> item : nodes) {
             if (item != null) {
                 ++size;
             }
         }
 
         AVLNode<T>[] expectedTree = TreeTraversalHelper.traverseAVL(AVLWrapper.constructAVL(nodes));
         AVLNode<T>[] receivedTree = TreeTraversalHelper.traverseAVL(avl);
 
         // If we want to ALWAYS print out the tree...
         System.out.println(ColorUtils.formatColorString(AsciiColorCode.BRIGHT_WHITE_BACKGROUND, AsciiColorCode.BRIGHT_BLACK_FOREGROUND, "Here is the expected tree:"));
         TreeDrawingTool.printCanvas(TreeTraversalHelper.generateTree(AVLWrapper.constructAVL(nodes)));
         System.out.println(ColorUtils.formatColorString(AsciiColorCode.BRIGHT_WHITE_BACKGROUND, AsciiColorCode.BRIGHT_BLACK_FOREGROUND, "\nAnd here is the received tree:"));
         TreeDrawingTool.printCanvas(TreeTraversalHelper.generateTree(avl));
 
         if (size != avl.size()) {
             fail("Sizes differed! Expected " + size + " but received " + avl.size());
         }
 
         for (int i = 0; i < expectedTree.length; ++i) {
 
             try {
                 // If one is null, then both are
                 if (receivedTree[i] == null && expectedTree[i] == null) {
                     continue;
                 }
 
                 // Checks if only one is null
                 if (receivedTree[i] == null || expectedTree[i] == null) {
                     fail();
                 }
 
                 if (!receivedTree[i].getData().equals(expectedTree[i].getData())) {
                     fail();
                 }
 
                 if (receivedTree[i].getHeight() != expectedTree[i].getHeight()) {
                     fail();
                 }
 
                 if (receivedTree[i].getBalanceFactor() != expectedTree[i].getBalanceFactor()) {
                     fail();
                 }
             } catch (Throwable e) {
                 fail("Expected element " + nodeString(expectedTree[i]) + " but received " + nodeString(receivedTree[i]));
             }
 
         }
 
 
     }
 
     private static <T extends Comparable<? super T>> String nodeString(AVLNode<T> node) {
         if (node == null) {
             return "null";
         }
         return String.format("(%s, height = %d, balance = %d)", node.getData(), node.getHeight(), node.getBalanceFactor());
     }
 
 
     /**
      * Helper method to turn any type array into an array of Strings by
      * calling the toString() method of each item in the array.
      * @param input The array (of any reference type) to convert to a String array.
      * @return The same items in the given array, in the same order, but as their String representations.
      * @param <T> The type of the array being passed in.
      */
     public static <T> String[] convertToStringArray (T[] input) {
         String[] arr = new String[input.length];
         for (int i = 0 ; i < input.length; ++i) {
             arr[i] = input[i] == null ? null : input[i].toString();
         }
         return arr;
     }
 }
 
 
 /**
  * A set of utilities for handling test cases regarding exceptions.
  */
 class ExceptionUtils {
 
     /**
      * Asserts a snippet of code throws the required exception, checking if it's descriptive along the way.
      * @param expected The expected exception to throw
      * @param codeThatMayThrowException A runnable containing the code intended to throw an exception
      * @param requiredSubstring A required substring for the message to be considered descriptive.
      *                          If this is null, then no substring is required.
      */
     public static void assertExceptionIsCorrect(Class<? extends Exception> expected, Runnable codeThatMayThrowException, String requiredSubstring) {
         try {
             codeThatMayThrowException.run();
             // If this line runs, then your code was supposed to throw an Exception but did not.
             fail("Encountered a test that did not throw the expected expected of " + expected.getName());
 
         } catch (Exception e) {
             assertSame("checking if the thrown exception is what we expected", expected, e.getClass()); // If this line fails, the code threw the wrong exception (or threw no exception at all)
             assertTrue(e.getClass().getName() + " exception thrown needs to be descriptive " + (requiredSubstring != null ? "\" and must contain the message \"" + requiredSubstring : "\""), isDescriptiveException(e, requiredSubstring)); // If this line fails, then the message was insufficiently descriptive
         }
     }
 
     public static void assertExceptionIsCorrect(Class<? extends Exception> expected, Runnable codeThatMayThrowException) {
         assertExceptionIsCorrect(expected, codeThatMayThrowException, null);
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
      * @param e Exception to check.
      * @return Same as isDescriptiveException(Exception e, String requiredSubstring).
      */
     public static boolean isDescriptiveException(Exception e) {
         return isDescriptiveException(e, null);
     }
 }
 
 
 class CompressionUtils {
 
     public static String serializeCharList(Collection<?> charList) {
         int currentN = 0;
 
         StringBuilder builder = new StringBuilder();
 
         for (Object c : charList) {
             if (c == null) {
                 currentN++;
             } else {
                 if (currentN != 0) {
                     builder.append(currentN);
                     currentN = 0;
                 }
 
                 builder.append(c);
             }
         }
 
         if (currentN != 0) {
             builder.append(currentN);
         }
 
         return builder.toString();
     }
 
     /**
      * Turns a generic array into a Collection of Character that the method
      * serializeCharList(Collection) can accept.
      * @param charArray The array to turn into a Character array.
      * @return The serialized list.
      */
     public static String serializeCharList(Object[] charArray) {
         ArrayList<Character> collection = new ArrayList<>();
         for (Object item : charArray) {
             collection.add(item == null ? null : item.toString().charAt(0));
         }
         return serializeCharList(collection);
     }
 
     public static String decompressString(String str) throws IOException {
         byte[] byteArr = Base64.getDecoder().decode(str);
 
         GZIPInputStream bais = new GZIPInputStream(new ByteArrayInputStream(byteArr));
 
         BufferedReader br = new BufferedReader(new InputStreamReader(bais, StandardCharsets.UTF_8));
 
         String line;
 
         StringBuilder output = new StringBuilder();
 
         while ((line = br.readLine()) != null) {
             output.append(line).append("\n");
         }
 
         return output.toString();
     }
 
 
     public static ArrayList<Character> compressedStringToCharList(String str) {
         StringBuilder numBuilder = new StringBuilder();
 
         ArrayList<Character> charList = new ArrayList<>();
 
         for (char c: str.toCharArray()) {
             if (Character.isLetter(c)) {
                 if (numBuilder.length() > 0) {
                     int nullElements = Integer.parseInt(numBuilder.toString());
 
                     for (int i = 0; i < nullElements; i++) {
                         charList.add(null);
                     }
 
                     numBuilder.setLength(0);
                 }
 
                 charList.add(c);
             } else if (Character.isDigit(c)) {
                 numBuilder.append(c);
             }
         }
 
         if (numBuilder.length() != 0) {
             int nullElements = Integer.parseInt(numBuilder.toString());
 
             for (int j = 0; j < nullElements; j++) {
                 charList.add(null);
             }
         }
 
 
         return charList;
     }
 }