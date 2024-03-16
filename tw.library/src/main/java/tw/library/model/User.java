package tw.library.model;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Users") // 將表名稱對應到資料庫中的Users表
@Component
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 使用資料庫生成主鍵
    @Column(name = "user_id") // 指定對應的資料庫欄位名稱
    private int id; // 對應資料庫中的user_id欄位
    
    @Column(name = "phone_number")
    private String phone;
    
    private String password;
    
    @Column(name = "user_name")
    private String name;
    
    @Column(name = "registration_time")
    private LocalDateTime registrationTime;
    
    @Column(name = "last_login_time")
    private LocalDateTime lastLoginTime;


    // 空的建構子
    public User() {
    }

    // 帶參數的建構子
    @Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", phone=" + phone
				+ ", registrationTime=" + registrationTime + ", lastLoginTime=" + lastLoginTime + "]";
	}

    // Getter和Setter方法
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

	public LocalDateTime getRegistrationTime() {
		return registrationTime;
	}

	public void setRegistrationTime(LocalDateTime registrationTime) {
		this.registrationTime = registrationTime;
	}

	public LocalDateTime getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(LocalDateTime lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}


}

