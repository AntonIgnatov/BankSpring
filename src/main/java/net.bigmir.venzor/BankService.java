package net.bigmir.venzor;

import net.bigmir.venzor.enteties.Account;
import net.bigmir.venzor.enteties.Curensy;
import net.bigmir.venzor.enteties.Transactions;
import net.bigmir.venzor.enteties.User;
import net.bigmir.venzor.json.CurensyJson;
import net.bigmir.venzor.repositories.AccountRepository;
import net.bigmir.venzor.repositories.CurensyRepository;
import net.bigmir.venzor.repositories.TransactionsRepository;
import net.bigmir.venzor.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class BankService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CurensyRepository curensyRepository;
    @Autowired
    private TransactionsRepository transactionsRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void fillCurensy() {
        List<Curensy> curensyList = CurensyJson.initCurensy();
        curensyRepository.save(curensyList);
    }


    public List<Transactions> transactionsList() {
        return transactionsRepository.findAll();
    }


    public List<Transactions> transactionsByUser(String name) {
        return transactionsRepository.allByUser(name);
    }


    public List<Transactions> transactionsByCurensy(String name) {
        return transactionsRepository.allByCurensy(name);
    }


    public List<User> userList() {
        return userRepository.findAll();
    }

    public String userById(long usrId){
        return userRepository.findOne(usrId).getName();
    }

    public List<Curensy> curensyList() {
        return curensyRepository.findAll();
    }


    public Curensy curensyById(long id) {
        return curensyRepository.findOne(id);
    }

    @Transactional
    public void addUser(User user, double uah, double usd, double eur) {
        user.fillAccounts(uah, usd, eur);
        userRepository.save(user);
    }


    @Transactional
    public void addTransactions(long userFrom, long userTo, int curensyFrom, int curensyTo, double amount) {
        List<Curensy> curensyList = curensyRepository.findAll();
        transactionsRepository.save(new Transactions(userRepository.findOne(userFrom).getName(),
                userRepository.findOne(userTo).getName(),
                curensyList.get(curensyFrom).getName(),
                curensyList.get(curensyTo).getName(),
                amount));
    }

    @Transactional
    synchronized public void addMoney(long userId, int curensyId, double amount) {
        User userTemp = userRepository.findOne(userId);
        userTemp.getAccounts().get(curensyId).setAmount(userTemp.getAccounts().get(curensyId).getAmount() + amount);
        userRepository.save(userTemp);
    }

    @Transactional
    synchronized public void removeMoney(long userId, int curensyId, double amount) {
        User userTemp = userRepository.findOne(userId);
        double amontTemp = userTemp.getAccounts().get(curensyId).getAmount();
        if (amontTemp < amount) {
            throw new IllegalArgumentException();
        }
        userTemp.getAccounts().get(curensyId).setAmount(amontTemp - amount);
        userRepository.save(userTemp);
    }

    @Transactional
    synchronized public void transfer(long userFrom, long userTo, int curensyId, double amount) {
        try {
            removeMoney(userFrom, curensyId, amount * 1.01);
        } catch (IllegalArgumentException e) {
            throw e;
        }
        comission(userFrom, curensyId, amount);
        addMoney(userTo, curensyId, amount);
        addTransactions(userFrom, userTo, curensyId, curensyId, amount);
    }

    @Transactional
    public void incAmount(long userId, int curensyId, double amount) {
        addMoney(userId, curensyId, amount * 0.99);
        comission(userId, curensyId, amount);
        addTransactions(userId, userId, curensyId, curensyId, amount*0.99);

    }

    @Transactional
    public void comission(long userFrom, int curensyId, double amount) {
        long bankId = userRepository.findOne(1l).getId();
        addMoney(bankId, curensyId, amount*0.01);
        addTransactions(userFrom, bankId, curensyId, curensyId, amount*0.01);
    }

    @Transactional
    public void exchange(long userId, int curensyFrom, int curensyTo, double amount) {
        try {
            removeMoney(userId, curensyFrom, amount*1.01);
        } catch (IllegalArgumentException e){
            throw e;
        }
        comission(userId, curensyFrom, amount);
        List<Curensy> curensyList = curensyRepository.findAll();
        double amountEx = amount*(curensyList.get(curensyTo).getRate()/curensyList.get(curensyFrom).getRate());
        addMoney(userId, curensyTo, amountEx);
        addTransactions(userId, userId, curensyFrom, curensyTo, amount);
    }

    public List getAllMoney(long userId){
        List resault = new ArrayList();
        User user = userRepository.findOne(userId);
        resault.add(user.getName());
        List<Curensy> curensyList = curensyRepository.findAll();
        double count = 0;
        for(int i = 0; i < curensyList.size(); i++){
            count += user.getAccounts().get(i).getAmount()/curensyList.get(i).getRate();
        }
        resault.add(count);
        return resault;
    }

    @Transactional
    public void init(){

        addUser(new User("bank"), 0, 0, 0);
        fillCurensy();
        for (int i = 0; i < 10; i++ ){
            addUser(new User("name"+i), 100, 100, 100);
        }

    }


}
