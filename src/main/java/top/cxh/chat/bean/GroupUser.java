package top.cxh.chat.bean;

public class GroupUser {
    private Integer id;

    private String account;

    private Integer groupRole;

    private String groupId;
    
    private GroupChat groupChat;
    
    private UserInfo userInfo;
    

    @Override
	public String toString() {
		return "GroupUser [id=" + id + ", account=" + account + ", groupRole=" + groupRole + ", groupId=" + groupId
				+ ", groupChat=" + groupChat + ", userInfo=" + userInfo + "]";
	}

	public GroupUser() {
		super();
	}
    
	public GroupUser(String account, Integer groupRole, String groupId) {
		super();
		this.account = account;
		this.groupRole = groupRole;
		this.groupId = groupId;
	}



	public GroupUser(Integer id, String account, Integer groupRole, String groupId) {
		super();
		this.id = id;
		this.account = account;
		this.groupRole = groupRole;
		this.groupId = groupId;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public Integer getGroupRole() {
        return groupRole;
    }

    public void setGroupRole(Integer groupRole) {
        this.groupRole = groupRole;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

	public GroupChat getGroupChat() {
		return groupChat;
	}

	public void setGroupChat(GroupChat groupChat) {
		this.groupChat = groupChat;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
}