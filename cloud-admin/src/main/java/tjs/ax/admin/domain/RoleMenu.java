package tjs.ax.admin.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RoleMenu {
	private Long id;
	private Long  roleId;
	private Long menuId;
}
