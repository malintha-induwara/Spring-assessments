package lk.ijse.gdse68.user;

import jakarta.annotation.PostConstruct;
import lk.ijse.gdse68.dep.GoodGirl;
import lk.ijse.gdse68.dep.Wow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class User {

    @Qualifier("Long hair")
    private GoodGirl goodGirl;

    //Constructor Injection
    public User(GoodGirl goodGirl) {
        this.goodGirl = goodGirl;
    }

    public User() {
        //cant use goodGirl here because it is not initialized yet
        System.out.println(goodGirl);
    }


    //PostConstruct is used to execute a method after the bean is initialized
    @PostConstruct
    public void init(){
        //System.out.println(goodGirl);
        goodGirl.sayHello();
    }

    @Autowired
    //(required = false)

    //you can use @Autowired to call a method when initializing the bean
    public void customMethod(@Nullable String name){
        System.out.println(name);
    }


    //Setter Injection
    @Autowired
    @Wow
    public void setGoodGirl(GoodGirl goodGirl) {
        this.goodGirl = goodGirl;
    }

    @Autowired
    public void myMethod(@Nullable String name){
        System.out.println("Hello null");
    }
}

