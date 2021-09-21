package com.group1.sports_rental.CustomerPage;

public class CreditTopup
{

    private String creditAmount;

    public void setCreditAmount(String creditAmount)
    {
        this.creditAmount = creditAmount;
    }

    public String getCreditAmount()
    {
        return this.creditAmount;
    }

    public Boolean validateCreditAmount()
    {
        if(this.creditAmount == null || this.creditAmount.isEmpty())
        {
            return true;
        }
        else {
            return false;
        }
    }

    public void topUpCredits(Long userId, ICustomerPageDao customerPageDao)
    {
        try
        {
            customerPageDao.topUpCredits(userId, this.creditAmount);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }
}
