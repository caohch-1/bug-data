package other.init;

import other.domain.Order;
import other.domain.OrderStatus;
import other.service.OrderOtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.UUID;

@Component
public class InitData implements CommandLineRunner {
    @Autowired
    OrderOtherService service;

    public void run(String... args)throws Exception{
        Order order = new Order();
        order.setId(UUID.fromString("5ad7750b-a68b-49c0-a8c0-32776b067703"));
        order.setBoughtDate(new Date());
        order.setTravelDate(new Date("Sat Jul 29 00:00:00 GMT+0800 2017"));
        order.setTravelTime(new Date("Mon May 04 09:02:00 GMT+0800 2013"));
        order.setAccountId(UUID.fromString("4d2a46c7-71cb-4cf1-b5bb-b68406d9da6f"));
        order.setContactsName("Contacts_One");
        order.setDocumentType(1);
        order.setContactsDocumentNumber("DocumentNumber_One");
        order.setTrainNumber("Z1234");
        order.setCoachNumber(5);
        order.setSeatClass(2);
        order.setSeatNumber("30");
        order.setFrom("shanghai");
        order.setTo("nanjing");
        order.setStatus(OrderStatus.PAID.getCode());
        order.setPrice("1300.0");
        service.initOrder(order);

        order.setId(UUID.fromString("5ad4750b-a68b-49c0-a8c0-32216b067406"));
        order.setFrom("shanghai");
        order.setTo("nanjing");
        order.setStatus(OrderStatus.PAID.getCode());
        order.setPrice("350.0");
        service.initOrder(order);

        order.setId(UUID.fromString("5ad4750b-a68b-62c0-a8c0-32216b061706"));
        order.setFrom("shanghai");
        order.setTo("nanjing");
        order.setStatus(OrderStatus.PAID.getCode());
        order.setPrice("350.0");
        service.initOrder(order);

        order.setId(UUID.fromString("5ad4750b-a18b-49c0-a8c0-33216b062706"));
        order.setFrom("shanghai");
        order.setTo("nanjing");
        order.setStatus(OrderStatus.PAID.getCode());
        order.setPrice("350.0");
        service.initOrder(order);
    }
//<td class="all_order_id noshow_component">a1674800-1cbb-49e5-ac1a-f193bde8a728</td>
//Order{"id":"5ad7750b-a68b-49c0-a8c0-32776b067703",
// "boughtDate":"Jun 21, 2017 11:52:22 AM",
// "travelDate":"Jun 23, 2017 12:00:00 AM",
// "travelTime":"May 4, 2013 2:51:52 PM",
// "accountId":"4d2a46c7-71cb-4cf1-b5bb-b68406d9da6f",
// "contactsName":"Contacts_One",
// "documentType":1,
// "contactsDocumentNumber":"DocumentNumber_One",
// "trainNumber":"Z1234",
// "coachNumber":5,
// "seatClass":2,
// "seatNumber":"FirstClass-30",
// "from":"Shang Hai",
// "to":"Tai Yuan",
// "status":0,
// "price":100.0}

}