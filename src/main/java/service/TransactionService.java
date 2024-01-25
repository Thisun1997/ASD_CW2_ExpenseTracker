package service;

import model.Category;
import repository.TransactionRepository;

public class TransactionService {

    public static int getTransactionCount(Category category){
        return TransactionRepository.getTransactionCount(category);
    }
    public static void removeTransactions(Category category){
        TransactionRepository.removeTransactions(category);
    }
}
