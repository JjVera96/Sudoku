/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelS;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Juan Vera
 */
public class CellTest {
    
    public CellTest() {
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
     * Test of isSolved method, of class Cell.
     */
    @Test
    public void testIsSolved() {
        System.out.println("\n-----------------------");
        System.out.println("isSolved");
        Cell instance = new Cell(0,0);
        instance.setValue(1);
        boolean result = instance.isSolved();
        if(result) System.out.println("Correcto");
        else System.out.println("Incorrecto");
        System.out.println("-----------------------\n");    
    }

    /**
     * Test of isCandidate method, of class Cell.
     */
    @Test
    public void testIsCandidate() {
        System.out.println("\n-----------------------");
        System.out.println("isCandidate");
        int n = 2;
        Cell instance = new Cell(0,0);
        boolean result = instance.isCandidate(n);
        if(result) System.out.println("Correcto");
        else System.out.println("Incorrecto");
        instance.setValue(4);
        result = instance.isCandidate(n);
        if(result) System.out.println("Incorrecto");
        else System.out.println("Correcto");
        System.out.println("-----------------------\n");
    }

    /**
     * Test of addObserver method, of class Cell.
     */
    /*
    @Test
    public void testAddObserver() {
        System.out.println("addObserver");
        Cell[][] cells = null;
        int[][] forma = null;
        Cell instance = null;
        instance.addObserver(cells, forma);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
    /**
     * Test of setValue method, of class Cell.
     */
    @Test
    public void testSetValue() {
        System.out.println("\n-----------------------");
        System.out.println("setValue");
        int value = 3;
        Cell instance = new Cell(0,0);
        System.out.println(instance.getValue());
        System.out.println(value);
        instance.setValue(value);
        System.out.println(instance.getValue());
        System.out.println("-----------------------\n");
    }

    /**
     * Test of update method, of class Cell.
     */
    /*
    @Test
    public void testUpdate() {
        System.out.println("update");
        Observable o = null;
        Object arg = null;
        Cell instance = null;
        instance.update(o, arg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
    /**
     * Test of iterator method, of class Cell.
     */
    /*
    @Test
    public void testIterator() {
        System.out.println("iterator");
        Cell instance = null;
        Iterator<Integer> expResult = null;
        Iterator<Integer> result = instance.iterator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
    /**
     * Test of getValue method, of class Cell.
     */
    @Test
    public void testGetValue() {
        System.out.println("\n-----------------------");
        System.out.println("getValue");
        Cell instance = new Cell(0,0);
        int result = instance.getValue();
        if(0 == result) System.out.println("Correcto");
        else System.out.println("Incorrecto");
        instance.setValue(3);
        System.out.println("Nuevo valor de la celda 3");
        System.out.println(instance.getValue());
        System.out.println("-----------------------\n");
    }

    /**
     * Test of isCan_modified method, of class Cell.
     */
    @Test
    public void testIsCan_modified() {
        System.out.println("\n-----------------------");
        System.out.println("isCan_modified");
        Cell instance = new Cell(0,0);        
        boolean result = instance.isCan_modified();
        if(result) System.out.println("Correcto");
        else System.out.println("Incorrecto");
        System.out.println("-----------------------\n");
    }

    /**
     * Test of setCan_modified method, of class Cell.
     */
    @Test
    public void testSetCan_modified() {
        System.out.println("\n-----------------------");
        System.out.println("setCan_modified");
        Cell instance = new Cell(0,0);
        instance.setCan_modified(true);
        if(instance.isCan_modified()) System.out.println("Correcto");
        else System.out.println("Incorrecto");
        System.out.println("-----------------------\n");
    }

    /**
     * Test of candidates method, of class Cell.
     */
    @Test
    public void testCandidates() {
        System.out.println("\n-----------------------");
        System.out.println("candidates");
        System.out.println("En celda vacia");
        Cell instance = new Cell(0,0);
        List<Integer> expResult = new ArrayList<> ();
        for(int i = 1; i <= 9; i++) expResult.add(i);
        List<Integer> result = instance.candidates();
        for(int i = 0; i < 9; i++) System.out.print(expResult.get(i) + " ");
        System.out.println("\n-----------------------\n");
    }
    
}
