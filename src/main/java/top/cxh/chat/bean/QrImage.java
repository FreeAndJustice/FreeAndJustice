package top.cxh.chat.bean;

public class QrImage {
    private Integer id;

    private String account;

    private String qrName;
    
    public QrImage() {
		super();
	}

	public QrImage(String account, String qrName) {
		super();
		this.account = account;
		this.qrName = qrName;
	}

	public QrImage(Integer id, String account, String qrName) {
		super();
		this.id = id;
		this.account = account;
		this.qrName = qrName;
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

    public String getQrName() {
        return qrName;
    }

    public void setQrName(String qrName) {
        this.qrName = qrName == null ? null : qrName.trim();
    }
}