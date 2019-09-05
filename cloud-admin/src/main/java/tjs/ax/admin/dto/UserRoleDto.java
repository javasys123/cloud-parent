package tjs.ax.admin.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserRoleDto {

    private Long id;
    private String name;
    private boolean checked;

}
