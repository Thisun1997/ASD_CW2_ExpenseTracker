package repository;

import model.Category;
import model.Transaction;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionRepository {

    private static final Map<YearMonth,List<Transaction>> transactions = new HashMap<>();

    public static int getTransactionCount(Category category){
        int transactionCount = 0;
        for(YearMonth yearMonth:transactions.keySet()){
            for(Transaction transaction:transactions.get(yearMonth)){
                if(transaction.getCategory().equals(category)){
                    transactionCount++;
                }
            }
        }
        return transactionCount;
    }

    public static void removeTransactions(Category category){
        for(YearMonth yearMonth:transactions.keySet()){
            transactions.get(yearMonth).removeIf(transaction -> transaction.getCategory().equals(category));
        }
    }
}
