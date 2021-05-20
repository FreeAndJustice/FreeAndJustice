package top.cxh.chat.bean;

import java.util.Date;
import java.util.List;

public class Friends {
    private Integer id;

    private String myAccount;

    private String friendAccount;

    private String friendName;

    private Integer friendGroup;

    private Date createDate;
    
    private UserInfo myFriends;
    
    private List<Circle> circles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMyAccount() {
        return myAccount;
    }

    public void setMyAccount(String myAccount) {
        this.myAccount = myAccount == null ? null : myAccount.trim();
    }

    public String getFriendAccount() {
        return friendAccount;
    }

    public void setFriendAccount(String friendAccount) {
        this.friendAccount = friendAccount == null ? null : friendAccount.trim();
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName == null ? null : friendName.trim();
    }

    public Integer getFriendGroup() {
        return friendGroup;
    }

    public void setFriendGroup(Integer friendGroup) {
        this.friendGroup = friendGroup;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

	public UserInfo getMyFriends() {
		return myFriends;
	}

	public void setMyFriends(UserInfo myFriends) {
		this.myFriends = myFriends;
	}

	public List<Circle> getCircles() {
		return circles;
	}

	public void setCircles(List<Circle> circles) {
		this.circles = circles;
	}
}