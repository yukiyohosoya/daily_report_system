package actions.views;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 従業員情報について画面の入力・出力値を扱うViewモデル
 *
 */
@Getter // すべてのクラスフィールドに好いてgetterを自動生成する（Lombok）
@Setter // すべてのクラスフィールドに好いてsetterを自動生成する（Lombok）
@NoArgsConstructor //引数なしコンストラクタを自動作成する
@AllArgsConstructor //すべてのクラスフィールドを引数の持つ引数ありコンストラクタを自動作成する（Lombok）

public class EmployeeView {
    /**
     * id
     */
    private Integer id;

    /**
     * 社員番号
     */
    private String code;

    /**
     * 社員名
     */

    private String name;

    /**
     * パスワード
     */

    private String password;

    /**
     * 管理者権限があるかどうか（一般：0、管理者:1）
     */

    private Integer  adminFlag;

    /**
     * 登録日時
     */

    private LocalDateTime createdAt;

    /**
     * 更新日時
     */

    private LocalDateTime updatedAt;

    /**
     * 削除された従業員かどうか（現役：0、削除済み：1）
     */
    private Integer deleteFlag;

}
