package tjs.ax.admin.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserRole {
    private Long id;
    private Long userId;
    private Long roleId;

}
