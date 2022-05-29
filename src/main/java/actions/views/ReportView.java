package actions.views;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * 日報情報について画面の入力値・出力値を扱うViewモデル
 *
 */

@Getter // すべてのクラスフィールドに好いてgetterを自動生成する（Lombok）
@Setter // すべてのクラスフィールドに好いてsetterを自動生成する（Lombok）
@NoArgsConstructor //引数なしコンストラクタを自動作成する
@AllArgsConstructor //すべてのクラスフィールドを引数の持つ引数ありコンストラクタを自動作成する（Lombok）


public class ReportView {

    /**
     * id
     */
    private Integer id;

    /**
     * 日報を登録した従業員
     */
    private EmployeeView employee;

    /**
     * いつの日報かを示す日付
     */
    private LocalDate reportDate;

    /**
     * 日報のタイトル
     */
    private String title;

    /**
     * 日報の内容
     */
    private String content;

    /**
     * 登録日時
     */
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    private LocalDateTime updatedAt;

    //↓追記
    /**
     * 出勤時間
     */

    private LocalTime att_T;

    /**
     * 出勤時間
     */

    private LocalTime lea_T;

}
