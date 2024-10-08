package com.example.demo.Controller;

import com.example.demo.dto.calldto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/call")

public class call{
    @GetMapping("/{getcall}")

    public calldto Attendcall(@PathVariable(name="getcall") long callid){
        return new calldto(callid,"kushal","Kanpur");
    }
}
