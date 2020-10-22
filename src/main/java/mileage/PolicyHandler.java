package mileage;

import mileage.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class PolicyHandler{

    @Autowired
    ManagerMessageRepository ManagerMessageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverDormantMemberChanged_SendManagerMsg(@Payload DormantMemberChanged dormantMemberChanged){

        if(dormantMemberChanged.isMe()){
            System.out.println("##### listener SendManagerMsg : " + dormantMemberChanged.toJson());
            ManagerMessage managerMessage = new ManagerMessage();
            managerMessage.setMemberId(dormantMemberChanged.getMemberId());
            managerMessage.setPhoneNo(dormantMemberChanged.getPhoneNo());
            managerMessage.setMessageContents("DORMANT Message Send~!");
            managerMessage.setMessageStatus("");

            ManagerMessageRepository.save(managerMessage);
        }
    }


}
