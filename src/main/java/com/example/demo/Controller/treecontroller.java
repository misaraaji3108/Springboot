package com.example.demo.Controller;


import com.example.demo.dto.treedto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tree")
public class treecontroller {
    @GetMapping("/{gettree}")
    public treedto gettree(@PathVariable (name = "gettree") long treeid){
        return new treedto(treeid,"Banana Tree","Baniyan Tree");
    }
}
