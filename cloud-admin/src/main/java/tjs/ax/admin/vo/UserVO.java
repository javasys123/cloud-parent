package tjs.ax.admin.vo;


import lombok.Data;
import lombok.ToString;
import tjs.ax.admin.domain.User;


@Data
@ToString
public class UserVO {
    /**
     * 更新的用户对象
     */
    private User user = new User();
    /**
     * 旧密码
     */
    private String pwdOld;
    /**
     * 新密码
     */
    private String pwdNew;

}
