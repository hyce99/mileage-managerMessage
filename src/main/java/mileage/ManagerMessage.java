package mileage;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Entity
@Table(name="ManagerMessage_table")
public class ManagerMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long memberId;
    private String phoneNo;
    private String messageContents;
    private String messageStatus;
    private String memberStatus;


    @PostPersist
    public void onPostPersist() {
        ManagerMsgSent managerMsgSent = new ManagerMsgSent();
        BeanUtils.copyProperties(this, managerMsgSent);

        if (phoneNo.length() == 11) {
            managerMsgSent.setMessageStatus("SUCCESS"); // SUCCESS / FAIL
            managerMsgSent.setMessageContents("MESSAGE SEND SUCCESS");
        } else {
            managerMsgSent.setMessageStatus("FAIL");
            managerMsgSent.setMessageContents("MESSAGE SEND FAIL");

            managerMsgSent.setMemberStatus("DESTRUCTION");
        }

        managerMsgSent.publishAfterCommit();

        System.out.println("##### Manager MESSAGE SENT");


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getMessageContents() {
        return messageContents;
    }

    public void setMessageContents(String messageContents) {
        this.messageContents = messageContents;
    }

    public String getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
    }

    public String getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(String memberStatus) {
        this.memberStatus = memberStatus;
    }
}
