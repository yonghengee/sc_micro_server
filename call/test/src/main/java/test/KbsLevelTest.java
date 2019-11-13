package test;/**
 * Created by wyh in 2019/6/24 9:29
 **/

import com.tx.txcall.Application;
import com.tx.txcall.modules.kbs.api.service.KbsLevelService;
import com.tx.txcall.modules.kbs.api.vo.request.KbsMoveLevelRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program:
 * @description:
 * @author: forever-wang
 * @create: 2019-06-24 09:29
 **/
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class KbsLevelTest {

    @Autowired
    private KbsLevelService kbsLevelService;

    @Test
    public void test(){
        KbsMoveLevelRequest kbsMoveLevelRequest = new KbsMoveLevelRequest();
        kbsMoveLevelRequest.setLevelId(2);
        kbsMoveLevelRequest.setPLevelId(3);
//        kbsMoveLevelRequest.setLevelId(4);
//        kbsMoveLevelRequest.setPLevelId(5);
        kbsLevelService.UpLevel(kbsMoveLevelRequest);
    }

    @Test
    public void tt() {

    }
}
