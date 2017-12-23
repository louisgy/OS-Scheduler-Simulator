/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package osschedulersimulator;

import javax.swing.Timer;

/**
 *
 * @author georgy
 */
public class Data {

    public static String prefix[] = {"were", "dewq", "heff", "afdv", "oia"};

    public static String word[] = {"were", "cdsdfg", "fgwer", "afff", "qwfg"};
    
    public static TimeLine timeline = new TimeLine();

    public static long value[] = {49, 48, 50, 47, 46};

    public static javax.swing.JButton btnTrigger;

    public static ThreadFactory threadfactory;

    public static Timer timer;


    public static int indexOfRunningThread = 0;

    public static int currentListSize = 5;

  

}
