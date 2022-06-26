package com.esprow.managers;

import com.esprow.pages.*;

public class PageObjectManager {
    private BasePage basePage;
    private LoginPage loginPage;
    private SubscriptionPage subscriptionPage;
    private CartPage cartPage;
    private ReviewPage reviewPage;
    private MessageSuccessPage messageSuccessPage;
    private ExchangesPage exchangesPage;

    public BasePage getBasePage() {
        return (basePage == null) ? basePage = new BasePage() : basePage;
    }

    public LoginPage getLoginPage() {
        return (loginPage == null) ? loginPage = new LoginPage() : loginPage;
    }

    public SubscriptionPage getSubscriptionPage() {
        return (subscriptionPage == null) ? subscriptionPage = new SubscriptionPage() : subscriptionPage;
    }

    public CartPage getCartPage() {
        return (cartPage == null) ? cartPage = new CartPage() : cartPage;
    }

    public ReviewPage getReviewPage() {
        return (reviewPage == null) ? reviewPage = new ReviewPage() : reviewPage;
    }

    public MessageSuccessPage getMessageSuccessPage() {
        return (messageSuccessPage == null) ? messageSuccessPage = new MessageSuccessPage() : messageSuccessPage;
    }

    public ExchangesPage getExchangesPage() {
        return (exchangesPage == null) ? exchangesPage = new ExchangesPage() : exchangesPage;
    }
}
