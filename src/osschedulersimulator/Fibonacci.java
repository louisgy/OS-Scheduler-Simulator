/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package osschedulersimulator;

/**
 *
 * @author georgy
 */
public class Fibonacci implements Bound {
    private long fibonacciResult;
    private long number;
    
    public Fibonacci(long number){
        fibonacciResult= 0;
        this.number=number;
    }
    public  long findFibonacci(long n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return findFibonacci(n-1) + findFibonacci(n-2);
    }

    @Override
    public void work() {
        fibonacciResult = findFibonacci(number) ;

    }

    public long getFibonacciResult() {
        return fibonacciResult;
    }

    public void setFibonacciResult(long fibonacciResult) {
        this.fibonacciResult = fibonacciResult;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }
    
    
    
}
