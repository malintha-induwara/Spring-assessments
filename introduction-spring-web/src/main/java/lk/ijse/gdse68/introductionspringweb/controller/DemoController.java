package lk.ijse.gdse68.introductionspringweb.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/api/v1/demo")
@RestController
public class DemoController {

    @GetMapping(value = "/health")
    public String healthCheck(){
        System.out.println("GG");
        return "DemoController run perfectly";
    }


    @GetMapping(value = "/pattern/{name}")
    public String pathVariable(@PathVariable ("name") String name) {
        return "DemoController run perfectly with :"+name;
    }
    @GetMapping(value = "/patternRegex/{id:C\\d{3}}")
    public String pathvariableWithRegex(@PathVariable("id") String id) {
        return "DemoController run perfectly with :"+id;
    }

    @GetMapping(value = "/patternRegex/{id:C\\d{3}}",headers = "X-number")
    public String pathvariableWithRegexAndHeader(@PathVariable("id") String id,@RequestHeader("X-number") int num) {
        return "Path variable is :"+id + " and Header is :"+num;
    }

    @GetMapping(params = "test=all")
    public String params() {
        return "All are tested";
    }

    @PostMapping(params = {"name","quantity"})
    public String paramData(@RequestParam("name") String name,@RequestParam String quantity) {
        return "Name : "+name+" Quantity : "+quantity;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public String saveJSON(){
        return "JSON saved";
    }


    @PostMapping("/dynamic/{value:\\d{2}}")
    public ResponseEntity<String> returnDynamicData(@PathVariable ("value") int incomingValue){
        if(incomingValue %2 == 0) {
            return ResponseEntity.ok("Even Number");
        }
        return ResponseEntity.ok("Odd Number");
    }


    @PostMapping(value = "/mapparams",params = {"id","desc"})
    public String handleMaps(@RequestParam("id") String id, @RequestParam("desc") String desc, @RequestParam Map<String, String> params) {
        System.out.println(params);
        return "ID : "+id+" Desc : "+desc+" Params : "+params;
    }


}

