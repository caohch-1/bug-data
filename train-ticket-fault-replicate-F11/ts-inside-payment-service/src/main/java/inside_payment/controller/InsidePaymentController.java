package inside_payment.controller;

import inside_payment.domain.*;
import inside_payment.service.InsidePaymentService;
import inside_payment.service.InsidePaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2017/6/20.
 */
@RestController
public class InsidePaymentController {

    @Autowired
    public InsidePaymentService service;

    @Autowired
    public InsidePaymentServiceImpl serviceImpl;

    @RequestMapping(value="/inside_payment/pay", method = RequestMethod.POST)
    public boolean pay(@RequestBody PaymentInfo info, HttpServletRequest request){
        System.out.println("[Inside Payment Service][Pay] Pay for:" + info.getOrderId());
        return service.pay(info, request);
    }

    @RequestMapping(value="/inside_payment/openRecheck", method = RequestMethod.GET)
    public boolean openRecheck(){
        serviceImpl.enableAutoCheck = true;
        return true;
    }

    @RequestMapping(value="/inside_payment/closeRecheck", method = RequestMethod.GET)
    public boolean closeRecheck(){
        serviceImpl.enableAutoCheck = false;
        return true;
    }

    @RequestMapping(value="/inside_payment/createAccount", method = RequestMethod.POST)
    public boolean createAccount(@RequestBody CreateAccountInfo info){
        return service.createAccount(info);
    }

    @RequestMapping(value="/inside_payment/addMoney", method = RequestMethod.POST)
    public boolean addMoney(@RequestBody AddMoneyInfo info){
        return service.addMoney(info);
    }

    @RequestMapping(value="/inside_payment/queryPayment", method = RequestMethod.GET)
    public List<Payment> queryPayment(){
        return service.queryPayment();
    }

    @RequestMapping(value="/inside_payment/queryAccount", method = RequestMethod.GET)
    public List<Balance> queryAccount(){
        return service.queryAccount();
    }

    @RequestMapping(value="/inside_payment/drawBack", method = RequestMethod.POST)
    public boolean drawBack(@RequestBody DrawBackInfo info){
        return service.drawBack(info);
    }

    @RequestMapping(value="/inside_payment/payDifference", method = RequestMethod.POST)
    public boolean payDifference(@RequestBody PaymentDifferenceInfo info, HttpServletRequest request){
        return service.payDifference(info, request);
    }

    @RequestMapping(value="/inside_payment/queryAddMoney", method = RequestMethod.GET)
    public List<AddMoney> queryAddMoney(){
        return service.queryAddMoney();
    }

    @RequestMapping("/hello1_callback")
    public String hello1_callback(@RequestParam(value="result", defaultValue="satan") String cal_back) {

        System.out.println("Call Back Result:" + cal_back);
        System.out.println("-------------external call back-------------");
        return "-------call back end-------";

    }

    @RequestMapping(value="/inside_payment/check", method = RequestMethod.GET)
    public boolean check(){
        return service.check();
    }

    @RequestMapping(value="/inside_payment/equal", method = RequestMethod.GET)
    public boolean equal(){
        return service.equal();
    }
}
