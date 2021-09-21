package com.group1.sports_rental.Payment;

import com.group1.sports_rental.Utils.ApplicationConfiguration;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Refund;
import org.springframework.beans.factory.annotation.Autowired;
import java.lang.*;
import java.util.HashMap;

public class Payment
{
    private String paymentId;
    private Float amount;
    private String email;
    private String currency;
    private Integer renterId = 0;
    private Integer rentalId = 0;
    private Long userId = 0L;
    private String ticketId = "";
    private String paymentMode = "credit_card";
    public enum PaymentStatus
    {
        topUp("top-up complete"),
        refund("refund"),
        pending("pending"),
        complete("complete"),
        rent("true"),
        payComplete("Payment complete.");

        public final String status;
        PaymentStatus(String status)
        {
            this.status = status;
        }
    }

    @Autowired
    public Payment()
    {
        ApplicationConfiguration applicationConfiguration = ApplicationConfiguration.instance();
        Stripe.apiKey = applicationConfiguration.getSTRIPE_PRIVATEKEY();
    }

    public String getPaymentMode()
    {
        return paymentMode;
    }

    public String getPaymentId()
    {
        return paymentId;
    }

    public void setPaymentId(String paymentId)
    {
        this.paymentId = paymentId;
    }

    public String getTicketId()
    {
        return ticketId;
    }

    public void setTicketId(String ticketId)
    {
        this.ticketId = ticketId;
    }

    public Float getAmount()
    {
        return amount;
    }

    public void setAmount(Float amount)
    {
        this.amount = amount;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getCurrency()
    {
        return currency;
    }

    public void setCurrency(String currency)
    {
        this.currency = currency;
    }

    public Integer getRenterId()
    {
        return renterId;
    }

    public void setRenterId(Integer renterId)
    {
        this.renterId = renterId;
    }

    public Integer getRentalId()
    {
        return rentalId;
    }

    public void setRentalId(Integer rentalId)
    {
        this.rentalId = rentalId;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    // Referred Stripe Payment API documents and created method like so
    // https://stripe.com/docs/api/charges/create
    public HashMap<String, Object> chargeRentalAmount(String token, Float amount) throws StripeException
    {
        HashMap<String, Object> transactionParameter = new HashMap<>();
        Charge charge = null;
        try
        {
            transactionParameter.put("amount", Math.round(amount));
            transactionParameter.put("source", token);
            transactionParameter.put("currency", "INR");
            charge = Charge.create(transactionParameter);
            transactionParameter.put("transactionId",charge.getId());
            transactionParameter.put("paymentStatus",charge.getOutcome().getSellerMessage());
            transactionParameter.put("paymentMode",charge.getPaymentMethod());
            transactionParameter.put("email",charge.getBillingDetails().getEmail());
        }
        catch (StripeException stripeException)
        {
            transactionParameter.put("paymentStatus",stripeException.getCode());
            transactionParameter.put("failureMessage",stripeException.getMessage());
            stripeException.printStackTrace();

        }
        return transactionParameter;
    }

    // Referred Stripe Payment API documents and created a refund method
    // https://stripe.com/docs/api/refunds/create
    public HashMap<String, Object> refundAmount(String transactionId) throws StripeException
    {
        HashMap<String, Object> refundMap = new HashMap<>();
        try
        {
            refundMap.put("charge", transactionId);
            Refund refund = Refund.create(refundMap);
            refundMap.put("amount",refund.getAmount());
            refundMap.put("currency",refund.getCurrency());
            refundMap.put("refundTransactionId",refund.getId());
            refundMap.put("paymentStatus",refund.getObject());
        }
        catch (StripeException stripeException)
        {
            refundMap.put("paymentStatus",stripeException.getCode());
            refundMap.put("failureMessage",stripeException.getMessage());
        }
        return refundMap;
    }

}

