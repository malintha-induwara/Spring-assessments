package lk.ijse.gdse68.config;

import lk.ijse.gdse68.aop.Transaction;
import lk.ijse.gdse68.beans.Customer;
import lk.ijse.gdse68.beans.TestBeans;
import org.apache.commons.logging.Log;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {Transaction.class, Log.class})
public class Config {
    //if there are special configuration you can put them in here

}

