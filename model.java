package test;

public class model {
	private int id;
	private String time, ip, msg;
	public model(int id, String time, String ip, String msg) {
		super();
		this.id = id;
		this.time = time;
		this.ip = ip;
		this.msg = msg;
	}
	public model() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
