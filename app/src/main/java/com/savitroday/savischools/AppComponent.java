package com.savitroday.savischools;


import com.savitroday.savischools.adapter.NotificationAdapter;
import com.savitroday.savischools.api.RestHelper;
import com.savitroday.savischools.di.module.AppModule;
import com.savitroday.savischools.di.module.ManagerModule;
import com.savitroday.savischools.di.module.NonAuthModule;
import com.savitroday.savischools.di.module.UserRestModule;
import com.savitroday.savischools.manager.AssignmentManager;
import com.savitroday.savischools.manager.DashboardManager;
import com.savitroday.savischools.manager.InvoiceManager;
import com.savitroday.savischools.manager.MyProfileManager;
import com.savitroday.savischools.manager.NotificationManager;
import com.savitroday.savischools.view.activity.LoginActivity;
import com.savitroday.savischools.view.activity.MainActivity;
import com.savitroday.savischools.view.fragment.DashboardFragment;
import com.savitroday.savischools.view.fragment.invoice.HistoryInvoiceFragment;
import com.savitroday.savischools.view.fragment.invoice.MakePaymentFragment;
import com.savitroday.savischools.view.fragment.messageNotification.MessageOpenViewFragment;
import com.savitroday.savischools.view.fragment.messageNotification.MessagesFragment;
import com.savitroday.savischools.view.fragment.messageNotification.NotificationDetailFragment;
import com.savitroday.savischools.view.fragment.messageNotification.NotificationFragment;

import com.savitroday.savischools.view.fragment.messageNotification.NotificationMessageTabFragment;
import com.savitroday.savischools.view.fragment.ParentProfileFragment;
import com.savitroday.savischools.view.fragment.invoice.PendingInvoiceFragment;
import com.savitroday.savischools.view.fragment.ProfileFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component( //contains all other modules/dependencies
        modules = {
                          AppModule.class,
                          ManagerModule.class,
                          NonAuthModule.class,
                          UserRestModule.class
        }
)

public interface AppComponent {
    
    void inject(MyApplication application);
    void inject(NotificationMessageTabFragment notificationMessageTabFragment);
    void inject(NotificationFragment notificationFragment);
    void inject(MakePaymentFragment makePaymentFragment);
    void inject(RestHelper restHelper);
    void inject(ParentProfileFragment parentProfileFragment);
    void inject(MainActivity mainActivity);
    void inject(MessageOpenViewFragment messageOpenViewFragment);
    void inject(NotificationDetailFragment notificationDetailFragment);
    void inject(LoginActivity loginActivity);
    void inject(HistoryInvoiceFragment historyInvoiceFragment);
    void inject(NotificationAdapter notificationAdapter);
    void inject(DashboardFragment dashboardFragment);
    void inject(ProfileFragment profileFragment);
    void inject(PendingInvoiceFragment pendingInvoiceFragment);
    void inject(MessagesFragment messagesFragment);
    DashboardManager dashboardManager();
    MyProfileManager profileManager();
    
    InvoiceManager invoiceManager();
    
    AssignmentManager assignmentManager();
    
    NotificationManager notificationManager();
    
}