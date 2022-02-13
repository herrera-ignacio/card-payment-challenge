function solution(A, D) {
    // write your code in JavaScript (Node.js 8.9.4)
    const balancePerMonth = [0,0,0,0,0,0,0,0,0,0,0,0];
    const expensesPerMonth = [0,0,0,0,0,0,0,0,0,0,0,0];
    const paymentsPerMonth = [0,0,0,0,0,0,0,0,0,0,0,0];
    
    D.forEach((date, idx) => {
        const month = Number(date.match(/([0-9]{4})-([0-9]{2})/)[2]);
        const transactionValue = A[idx];

        balancePerMonth[month - 1] += transactionValue;
    
        if (transactionValue < 0) {
            expensesPerMonth[month - 1] += transactionValue;
            paymentsPerMonth[month - 1] += 1;
        } 
    });

    let yearBalance = 0;
    
    balancePerMonth.forEach((balance, month) => {
        yearBalance += balance;
        if (shouldApplyFee(expensesPerMonth[month], paymentsPerMonth[month])) {
            yearBalance -= 5;
        }
    });

    return yearBalance;
}

function shouldApplyFee(monthlyExpenses, monthlyPayments) {
    return monthlyExpenses > -100 || monthlyPayments < 3;
}
