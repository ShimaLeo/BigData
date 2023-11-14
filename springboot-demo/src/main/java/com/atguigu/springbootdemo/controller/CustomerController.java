package com.atguigu.springbootdemo.controller;


/*
* 控制层组件
*职责：
    *   接受请求 和 请求参数
    *   调用业务层
    *   给客户端响应结果
*
* 组件：Spring容器来说。未来 Spring容器会帮助我们管理controller ，service ，mapper
        *的类，负责具体对象的创建，管理，销毁等工作
        * 而一个具体的类被spring管理后，可以称之为一个组件
*
*
*
* @RestController:标识为控制层的组件，意味着未来Spring会帮我们管理该类对象的创建，装配，销毁等
*  */

import com.alibaba.fastjson.JSON;
import com.atguigu.springbootdemo.bean.Customer;
import org.springframework.web.bind.annotation.*;


@RestController
public class CustomerController {

    public static final String SUCCESS = "success";

    /**
     *  动态参数
     *  localhost:8080/dynamicparam?username=zhangsan&age=33&addr=beijing
     */
    @GetMapping("dynamicparam")
    public String dynamicparam(
            @RequestParam(value = "username",required = false,defaultValue = "Unknown") String username,
            @RequestParam(value = "age",required = false,defaultValue = "18") Integer age,
            @RequestParam(value = "addr",required = false,defaultValue = "China") String addr){



        System.out.println("username :" + username);
        System.out.println("age :" + age);
        System.out.println("addr :" + addr);

        return SUCCESS;
    }

    /**
     *  请求方式：
     *      GET  : 用于读。 限制参数传递的最大大小
     *
     *      POST : 用于写。 理论上参数传递的大小没有上限
     * @return
     */
    //@RequestMapping(value = "/method", method = RequestMethod.GET)
    @GetMapping("method")
    //@PostMapping("method")
    public String method(){
        return SUCCESS;
    }




    /**
     * 响应结果的格式：
     *      目前，前后段分离开发，基本上都是按照约定好的格式进行数据交换，而常用的格式就是JSON
     * @return
     */
    /*
    * 请求参数：
    *         1. 键值对参数
    *               @RequestParam: 将请求中的键值对参数映射到请求处理方法的形参上
    *         2. 请求路径中的而参数
    *               客户端的请求：
    *                   http://localhost:8080/param2/zhangsan/25
    *               @PathVariable:
    *         3. 请求体参数
    *
    */
    @RequestMapping(value = "result")
    public String result(@RequestParam(value = "username") String username, @RequestParam(value = "age") Integer age){
        //return "result : username = " username + ", age = " + age;
        Customer customer = new Customer("1234",username,age);

        return JSON.toJSONString(customer);

    }


    @RequestMapping(value = "/param3")
    public String param3( @RequestBody Customer customer){
        System.out.printf("customer" + customer);
        return SUCCESS;
    }



    @RequestMapping(value="/param2/{username}/{age}")
    public String param2(@PathVariable(value="username") String username , @PathVariable(value="age") Integer age){
        System.out.printf("username = "+ username + ", age = " + age);
        return SUCCESS;

    }




    @RequestMapping(value="param1")
    public String param1(@RequestParam(value="username") String username ,  @RequestParam(value = "age") Integer age){
        return SUCCESS;
    }






    /*
    * 客户端的请求：http://localhost:8080/hello
    *
    * 一个请求对应一个请求处理方法
    *
    * @RequestMapping:将请求和请求处理方法进行映射，表示通过哪个方法处理对应的请求
    *
    * */
    @RequestMapping(value = "hello")
    public String hello(){
        return "success";
    }
}
