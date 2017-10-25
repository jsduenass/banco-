/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juan sebastian
 */
public class SegmentTreeTest {
    
    public SegmentTreeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of sumRange method, of class SegmentTree.
     */
    @Test
    public void testSumRange() {
        System.out.println("sumRange");
        int i = 0;
        int j = 0;
        SegmentTree instance = null;
        double expResult = 0.0;
        double result = instance.sumRange(i, j);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of limIzquierdo method, of class SegmentTree.
     */
    @Test
    public void testLimIzquierdo() {
        System.out.println("limIzquierdo");
        int i = 0;
        ST_Node x = null;
        SegmentTree instance = null;
        ST_Node expResult = null;
        ST_Node result = instance.limIzquierdo(i, x);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of limDerecho method, of class SegmentTree.
     */
    @Test
    public void testLimDerecho() {
        System.out.println("limDerecho");
        int j = 0;
        ST_Node x = null;
        SegmentTree instance = null;
        ST_Node expResult = null;
        ST_Node result = instance.limDerecho(j, x);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findMin method, of class SegmentTree.
     */
    @Test
    public void testFindMin_int_int() {
        System.out.println("findMin");
        int i = 0;
        int j = 0;
        SegmentTree instance = null;
        double expResult = 0.0;
        double result = instance.findMin(i, j);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findMin method, of class SegmentTree.
     */
    @Test
    public void testFindMin_double_double() {
        System.out.println("findMin");
        double x1 = 0.0;
        double x2 = 0.0;
        SegmentTree instance = null;
        double expResult = 0.0;
        double result = instance.findMin(x1, x2);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findMax method, of class SegmentTree.
     */
    @Test
    public void testFindMax() {
        System.out.println("findMax");
        double x1 = 0.0;
        double x2 = 0.0;
        SegmentTree instance = null;
        double expResult = 0.0;
        double result = instance.findMax(x1, x2);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of preorden method, of class SegmentTree.
     */
    @Test
    public void testPreorden() {
        System.out.println("preorden");
        ST_Node x = null;
        SegmentTree instance = null;
        instance.preorden(x);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of postorden method, of class SegmentTree.
     */
    @Test
    public void testPostorden() {
        System.out.println("postorden");
        ST_Node x = null;
        SegmentTree instance = null;
        instance.postorden(x);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
