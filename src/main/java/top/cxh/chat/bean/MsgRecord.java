package top.cxh.chat.bean;

import java.util.Date;

public class MsgRecord {
    private Integer id;

    private String context;

    private String fromAccount;

    private String toAccount;

    private String msgType;

    private String sendType;

    private Date createDate;

    private Integer msgState;
    
    private UserInfo userInfo;
    
    private GroupChat groupChat;
    
    @Override
	public String toString() {
		return "MsgRecord [id=" + id + ", context=" + context + ", fromAccount=" + fromAccount + ", toAccount="
				+ toAccount + ", msgType=" + msgType + ", sendType=" + sendType + ", createDate=" + createDate
				+ ", msgState=" + msgState + ", userInfo=" + userInfo + ", groupChat=" + groupChat + "]";
	}

	public MsgRecord() {
		super();
	}

	public MsgRecord(String context, String fromAccount, String toAccount, String msgType, String sendType,
			Date createDate) {
		super();
		this.context = context;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.msgType = msgType;
		this.sendType = sendType;
		this.createDate = createDate;
	}
	

	public MsgRecord(Integer id, String context, String fromAccount, String toAccount, String msgType, String sendType,
			Date createDate, Integer msgState) {
		super();
		this.id = id;
		this.context = context;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.msgType = msgType;
		this.sendType = sendType;
		this.createDate = createDate;
		this.msgState = msgState;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context == null ? null : context.trim();
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount == null ? null : fromAccount.trim();
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount == null ? null : toAccount.trim();
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType == null ? null : msgType.trim();
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType == null ? null : sendType.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getMsgState() {
        return msgState;
    }

    public void setMsgState(Integer msgState) {
        this.msgState = msgState;
    }

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public GroupChat getGroupChat() {
		return groupChat;
	}

	public void setGroupChat(GroupChat groupChat) {
		this.groupChat = groupChat;
	}
}