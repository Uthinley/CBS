package bt.cbs.zrr.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;


public class LoggingAspect {

    private static final Logger log = Logger.getLogger(LoggingAspect.class);

    public Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        String className = proceedingJoinPoint.getTarget().getClass().getName();
        String methodName = proceedingJoinPoint.getSignature().getName();
        String fullName = className + ":" + methodName;

        //  System.out.println("=============start *****");
        log.info("Going into " + fullName);

        long startTime = System.currentTimeMillis();
        Object object = proceedingJoinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        log.info("Left out from " + fullName + " and Time taken to execute: " + executionTime + " ms");
        //   System.out.println("=============end *****");

        long maximumExecutionTIme = 20000;  // 20 milliseconds
        if (maximumExecutionTIme < executionTime) {
            log.info("Take a look " + fullName + "Time taken to more than 20 milliSeconds");

        }

        return object;
    }

  /*  Workable and tested code.Don't Delete*/
    /*public void logBefore(JoinPoint joinPoint){
        System.out.println("Before............Start......."+joinPoint.getSignature().getName());
    }

    public void logAfter(JoinPoint joinPoint){
        System.out.println("After...............End...."+joinPoint.getSignature().getName());
    }


    public void logAfterReturning(JoinPoint joinPoint){
        System.out.println("return result......."+joinPoint.getSignature().getName());
    }

    public void addCustomerThrowException(JoinPoint joinPoint,Throwable error){
        System.out.println("===========================THis is error==================================");
        System.out.println("Exception : " + error.getCause());
        System.out.println("===========================THis is error==================================" + error.getMessage());
        System.out.println("Test-----------------------------------start");
         error.printStackTrace();
        System.out.println("Test-----------------------------------end");
    }*/

     /* End  Workable and tested code.Don't Delete*/

}
