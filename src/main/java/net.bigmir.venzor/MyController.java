package net.bigmir.venzor;

import net.bigmir.venzor.enteties.Curensy;
import net.bigmir.venzor.enteties.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MyController {
    static final String[] MSG = {"Молодец)))", "Что-то пошло не так...(((", "Хозяин, нужно больше золота..."};

    @Autowired
    private BankService bankService;

    @RequestMapping("/")
    public String index(Model model) {
        List<User> userList = bankService.userList();
        userList.remove(0);
        model.addAttribute("users", userList);
        return "index";
    }

    @RequestMapping("/add")
    public String addMoney(@RequestParam("user_single") long userId,
                           @RequestParam("curensy_single") int curensyId,
                           @RequestParam("amount") String amountInString, Model model) {
        double amount;
        try {
            amount = toDouble(amountInString);
        } catch (NumberFormatException e) {
            model.addAttribute("msg", MSG[1]);
            return "result";
        }

        bankService.incAmount(userId, curensyId, amount);
        model.addAttribute("msg", MSG[0]);
        return "result";
    }

    @RequestMapping("/transfer")
    public String transfer(@RequestParam("user_from") long userFrom,
                           @RequestParam("user_to") long userTo,
                           @RequestParam("curensy_single") int curensyId,
                           @RequestParam("amount") String amountInString, Model model) {
        double amount;
        try {
            amount = toDouble(amountInString);
        } catch (NumberFormatException e) {
            model.addAttribute("msg", MSG[1]);
            return "result";
        }
        try {
            bankService.transfer(userFrom, userTo, curensyId, amount);
        } catch (IllegalArgumentException e) {
            model.addAttribute("msg", MSG[2]);
            return "result";
        }
        model.addAttribute("msg", MSG[0]);
        return "result";
    }

    @RequestMapping("/exchange")
    public String exchange(@RequestParam("user_id") long userId,
                           @RequestParam("curensy_from") int curensyFrom,
                           @RequestParam("curensy_to") int curensyTo,
                           @RequestParam("amount") String amountInString, Model model) {
        double amount;
        try {
            amount = toDouble(amountInString);
        } catch (NumberFormatException e) {
            model.addAttribute("msg", MSG[1]);
            return "result";
        }
        try {
            bankService.exchange(userId, curensyFrom, curensyTo, amount);
        } catch (IllegalArgumentException e) {
            model.addAttribute("msg", MSG[2]);
            return "result";
        }
        model.addAttribute("msg", MSG[0]);
        return "result";
    }

    @RequestMapping("/all_in_uah")
    public String allInUah(@RequestParam("user_id") long userId, Model model){
        List resault = bankService.getAllMoney(userId);
        String msg = "У пользователя "+resault.get(0)+ " "+resault.get(1)+" украинских золотых дублонов))";
        model.addAttribute("msg", msg);
        return "result";
    }

    public static double toDouble(String string){
        double amount;
        try {
            amount = Double.parseDouble(string);
            if(amount < 0){
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            throw e;
        }
        return amount;
    }

    @RequestMapping("/transh/user")
    public String transactionsByUser(@RequestParam("user_id") long userId, Model model){
        if (userId == -1){
            model.addAttribute("list", bankService.transactionsList());
        }else{
            String name = bankService.userById(userId);
            model.addAttribute("list", bankService.transactionsByUser(name));
        }
        return "list";
    }

    @RequestMapping("/transh/curensy")
    public String transactionsByCurensy(@RequestParam("curensy_name") String name, Model model){
        if (name.equals("ALL")){
            model.addAttribute("list", bankService.transactionsList());
        }else{
            model.addAttribute("list", bankService.transactionsByCurensy(name));
        }
        return "list";
    }

}
