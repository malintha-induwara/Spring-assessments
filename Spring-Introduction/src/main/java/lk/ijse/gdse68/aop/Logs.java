package lk.ijse.gdse68.aop;


import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import lk.ijse.gdse68.aop.Transaction;


@Component
@Aspect
@EnableAspectJAutoProxy
public class Logs {

    @After("execution(public void startTransaction())")
    public void logForStartTransaction() {
        System.out.println("Log Transaction Started");
    }

    @Before("execution(public void endTransaction())")
    public void logsForEndTransaction() {
        System.out.println("Log Transaction Ended");
    }

}

