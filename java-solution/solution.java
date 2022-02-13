// you can also use imports, for example:
import java.util.*;
import java.util.regex.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
    public int solution(int[] A, String[] D) {
        int[] balancePerMonth = new int[12];
        int[] expensesPerMonth = new int[12];
        int[] paymentsPerMonth = new int[12];

        // This should be separated to another function such as "getTransactionBalancePerMonth"
        for (int i = 0; i < D.length; i++) {
            String date = D[i];
            int month = getMonth(date);

            int transactionValue = A[i];

            balancePerMonth[month-1] += transactionValue;
            
            if (transactionValue < 0) {
                expensesPerMonth[month - 1] += transactionValue;
                paymentsPerMonth[month - 1] += 1;
            }
        }

        int yearBalance = 0;

        // This should go be separated to another function such as "applyFees"
        for (int month = 0; month < 12; month++) {
            int monthlyBalance = balancePerMonth[month];
            yearBalance += monthlyBalance;
            if (shouldApplyFee(expensesPerMonth[month], paymentsPerMonth[month])) {
                yearBalance -= 5;
            }
        }

        return yearBalance;
    }

    private static int getMonth(String date) {
        Pattern r = Pattern.compile("([0-9]{4})-([0-9]{2})");
        Matcher m = r.matcher(date);
        if(m.find()) {
            return Integer.parseInt(m.group(2));
        } else {
            // Should handle error
            return 0;
        }
        
    }

    private static boolean shouldApplyFee(int monthlyExpenses, int monthlyPayments) {
        return monthlyExpenses > - 100 || monthlyPayments < 3;
    }
}
