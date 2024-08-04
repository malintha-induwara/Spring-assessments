package lk.ijse.gdse68.user;

import jakarta.annotation.PostConstruct;
import lk.ijse.gdse68.dep.GoodGirl;
import lk.ijse.gdse68.dep.Wow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class User {
    @Autowired
    @Qualifier("Long hair")
    @Wow
    private GoodGirl goodGirl;

    @PostConstruct
    public void init(){
        //System.out.println(goodGirl);
        goodGirl.sayHello();
    }
}

