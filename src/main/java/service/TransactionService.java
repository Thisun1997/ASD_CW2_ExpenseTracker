package service;

import model.*;
import repository.TransactionRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;

public class TransactionService {
    public static void addTransaction(YearMonth yearMonth, String date, BigDecimal amount, String note, Category category, boolean isRecurring, boolean isExpense) {
        Transaction transaction = null;
        if(isExpense){
            transaction = new Expense.ExpenseBuilder(amount).setCategory((ExpenseCategory) category).build();
        }
        transaction = new Income(date, amount, note, isRecurring, (IncomeCategory) category);
        TransactionRepository.addTransaction(yearMonth, transaction);
    }
}
