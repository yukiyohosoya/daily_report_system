package models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 従業員データのDTOモデル
 *
 */

@Table(name= JpaConst.TABLE_EMP)
@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_EMP_GET_ALL,
            query= JpaConst.Q_EMP_GET_ALL_DEF),
    @NamedQuery(
            name = JpaConst.Q_EMP_COUNT,
            query= JpaConst.Q_EMP_COUNT_DEF),
    @NamedQuery(
            name = JpaConst.Q_EMP_COUNT_RESISTERED_BY_CODE,
            query= JpaConst.Q_EMP_COUNT_RESISTERED_BY_CODE_DEF),
    @NamedQuery(
            name = JpaConst.Q_EMP_GET_BY_CODE_AND_PASS,
            query= JpaConst.Q_EMP_GET_BY_CODE_AND_PASS_DEF)
})

@Getter // すべてのクラスフィールドに好いてgetterを自動生成する（Lombok）
@Setter // すべてのクラスフィールドに好いてsetterを自動生成する（Lombok）
@NoArgsConstructor //引数なしコンストラクタを自動作成する
@AllArgsConstructor //すべてのクラスフィールドを引数の持つ引数ありコンストラクタを自動作成する（Lombok）
@Entity


public class Employee {
    /**
     * id
     */

    @Id
    @Column( name = JpaConst.EMP_COL_ID)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    /**
     * 社員番号
     */

    @Column( name = JpaConst.EMP_COL_CODE , nullable= false,unique=true)
    private String code;
    /**
     * 社員名
     */

    @Column( name = JpaConst.EMP_COL_NAME , nullable= false)
    private String name;

    /**
     * パスワード
     */

    @Column( name = JpaConst.EMP_COL_PASS ,length=64, nullable= false)
    private String password;

    /**
     * 管理者権限があるかどうか（一般：0、管理者:1）
     */

    @Column( name = JpaConst.EMP_COL_ADMIN_FLAG ,nullable= false)
    private String adminFlag;

    /**
     * 登録日時
     */

    @Column( name = JpaConst.EMP_COL_CREATED_AT , nullable= false)
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */

    @Column( name = JpaConst.EMP_COL_UPDATED_AT , nullable= false)
    private LocalDateTime updatedAt;

    /**
     * 削除された従業員かどうか（現役：0、削除済み：1）
     */

    @Column( name = JpaConst.EMP_COL_DELETE_FLAG, nullable= false)
    private Integer deleteFlag;


}
