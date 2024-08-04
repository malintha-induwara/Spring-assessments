package lk.ijse.gdse68;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        //Following line is similar to a Bucket
        //Other lines are used to describe of the bucket should work
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(HelloWorld.class); //Put the class into the Bucket
        context.refresh(); //Refresh (shake) the bucket to make the class visible to the spring.
        context.close();
    }
}