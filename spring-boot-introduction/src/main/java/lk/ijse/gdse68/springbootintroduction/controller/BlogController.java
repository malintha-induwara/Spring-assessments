package lk.ijse.gdse68.springbootintroduction.controller;

import lk.ijse.gdse68.springbootintroduction.entity.Blog;
import lk.ijse.gdse68.springbootintroduction.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog")
@CrossOrigin
public class BlogController {

    @Autowired
    public BlogRepository blogRepository ;

    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }

    @PostMapping("number")
    public int getNumber(){
        return 1;
    }

    @GetMapping("/number/{number}")
    public String getNumber(@PathVariable String number) {
        return number;
    }

    @GetMapping("/object")
    public Object getObject() {
        Blog blog = new Blog();
        return blog;
    }

    @PostMapping("/savePost")
    public void savePost(@RequestBody Blog blog){
        blogRepository.save(blog);
    }
    @GetMapping("/getAllpost")
    public List<Blog> getAllPost( ){
        return blogRepository.findAll();
    }
//
//    @GetMapping("/getOnePost/{id}")
//    public String getOnePost(@PathVariable String id){
//        return blogRepository.findById(id).get().getTitle();
//    }


    @PutMapping("/updatePost")
    public void updatePost(@RequestBody Blog blog){
        blogRepository.save(blog);
    }

    @DeleteMapping("/deletePost/{id}")
    public void deletePost(@PathVariable int id){
        blogRepository.deleteById(id);
    }

}

