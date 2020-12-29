package com.example.back;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import java.lang.Math;

@RestController
@CrossOrigin
public class CalculatorController {
   @GetMapping("/add")
   public String add(@RequestParam String input1, @RequestParam String input2){
       if(input2.equals("-")) return String.valueOf('E');
       double num1 = Double.parseDouble(input1);
       double num2 = Double.parseDouble(input2);
       return String.valueOf(num1 + num2);
   } 
   @GetMapping("/subtract")
   public String subtract(@RequestParam String input1, @RequestParam String input2){
       if(input2.equals("-")) return String.valueOf('E');
       double num1 = Double.parseDouble(input1);
       double num2 = Double.parseDouble(input2);
       return String.valueOf(num1 - num2);
   }
   @GetMapping("/multiply")
   public String multiply(@RequestParam String input1, @RequestParam String input2){
       if(input2.equals("-")) return String.valueOf('E');
       double num1 = Double.parseDouble(input1);
       double num2 = Double.parseDouble(input2);
       return String.valueOf(num1 * num2);
   }
   @GetMapping("/divide")
   public String divide(@RequestParam String input1, @RequestParam String input2){
       if(input2.equals("-")) return String.valueOf('E');
       double num1 = Double.parseDouble(input1);
       double num2 = Double.parseDouble(input2);
       if(num2 == 0) return String.valueOf('E');
       return String.valueOf(num1 / num2);
   }
   @GetMapping("/one_over_x")
   public String one_over_x(@RequestParam String input1){
       if(input1.equals("-")) return String.valueOf('E');
       if(input1.equals("E")) return String.valueOf('0'); 
       double num1 = Double.parseDouble(input1);
       if(num1 == 0) return String.valueOf('E');
       return String.valueOf(1 / num1);
   }
   @GetMapping("/square")
   public String square(@RequestParam String input1){
       if(input1.equals("-")) return String.valueOf('E');
       if(input1.equals("E")) return String.valueOf('0'); 
       double num1 = Double.parseDouble(input1);
       return String.valueOf(num1 * num1);
   }
   @GetMapping("/root")
   public String root(@RequestParam String input1){
       if(input1.equals("-")) return String.valueOf('E');
       if(input1.equals("E")) return String.valueOf('0');
       double num1 = Double.parseDouble(input1);
       if(num1 < 0) return String.valueOf('E');
       return String.valueOf(Math.sqrt(num1));
   }
   @GetMapping("/percent")
   public String percent(@RequestParam String input1){
       if(input1.equals("-")) return String.valueOf('E');
       if(input1.equals("E")) return String.valueOf('0');
       double num1 = Double.parseDouble(input1);
       if(num1 < 0) return String.valueOf('E');
       return String.valueOf(num1 / 100);
   }
}
