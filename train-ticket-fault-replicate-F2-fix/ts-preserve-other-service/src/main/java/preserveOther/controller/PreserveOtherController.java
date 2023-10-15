package preserveOther.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import preserveOther.domain.OrderTicketsInfo;
import preserveOther.domain.OrderTicketsResult;
import preserveOther.domain.ReserveQueueInformation;
import preserveOther.service.PreserveOtherService;
import preserveOther.service.PreserveOtherServiceImpl;
import java.util.ArrayList;

@RestController
public class PreserveOtherController {

    @Autowired
    private PreserveOtherService preserveService;

    @Autowired
    private PreserveOtherServiceImpl preserveOtherServiceImpl;


    @RequestMapping(value = "/reserve/queue", method = RequestMethod.POST)
    public ReserveQueueInformation reserveQueue(@RequestBody OrderTicketsInfo oti, @CookieValue String loginId, @CookieValue String loginToken){

        return null;
    }

    @RequestMapping(value = "/reserve/getResult", method = RequestMethod.POST)
    public OrderTicketsResult reserveGetResult(@RequestBody ReserveQueueInformation reserveQueueInformation){

        OrderTicketsResult result = null;

        ArrayList<ReserveQueueInformation> list = preserveOtherServiceImpl.arrayList;
        for(ReserveQueueInformation info : list){
            if (info.getUserId().equals(reserveQueueInformation.getUserId()) && info.getRequestId().equals(reserveQueueInformation.getRequestId())){
                result = info.getResult();
                list.remove(info);
                break;
            }
        }

        return result;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value="/preserveOther", method = RequestMethod.POST)
    public OrderTicketsResult preserve(@RequestBody OrderTicketsInfo oti,@CookieValue String loginId,@CookieValue String loginToken){
        System.out.println("[Preserve Other Service][Preserve] Account " + loginId + " order from " +
                oti.getFrom() + " -----> " + oti.getTo() + " at " + oti.getDate());
        return preserveService.preserve(oti,loginId,loginToken);
    }
}
