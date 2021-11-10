package bt.cbs.zrr.global.common;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "common_delete_history")
public class DeleteHistory {
    /*
    SELECT 	`delete_history_id`,
	`table_name`,
	`deleted_id`,
	`deleted_by`,
	`deleted_on`

	FROM
	`cbs_db`.`common_delete_history` */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delete_history_id")
    private Integer delete_history_id;

    @Column(name = "table_name")
    private String table_name;

    @Column(name = "deleted_id")
    private String deleted_id;
    @Column(name = "deleted_by")
    private String deleted_by;
    @Column(name = "deleted_on")
    private Date deleted_on;

    public Integer getDelete_history_id() {
        return delete_history_id;
    }

    public void setDelete_history_id(Integer delete_history_id) {
        this.delete_history_id = delete_history_id;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public String getDeleted_id() {
        return deleted_id;
    }

    public void setDeleted_id(String deleted_id) {
        this.deleted_id = deleted_id;
    }

    public String getDeleted_by() {
        return deleted_by;
    }

    public void setDeleted_by(String deleted_by) {
        this.deleted_by = deleted_by;
    }

    public Date getDeleted_on() {
        return deleted_on;
    }

    public void setDeleted_on(Date deleted_on) {
        this.deleted_on = deleted_on;
    }
}
