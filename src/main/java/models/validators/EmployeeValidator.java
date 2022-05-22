package models.validators;

import java.util.ArrayList;
import java.util.List;

import actions.views.EmployeeView;
import constants.MessageConst;
import services.EmployeeService;

/**
 * 従業員インスタンスに設定されている値のバリデーションを行うクラス
 *
 */

public class EmployeeValidator {
    /**
     * 従業員インスタンスの各項目についてバリデーションを行う
     * @param service　呼び出し元Seviceクラスのインスタンス
     * @param ev EmployeeViewのインスタンス
     * @param codeDuplicateCheckFlag　社員番号の重複チェックを実施するかどうか（実施する：true 実施しない：false）
     *@param passwordCheckFlag パスワードの入力チェックを行うかどうか
     *@return　エラーのリスト
     *
     */

    public static List<String> validate(
            EmployeeService service, EmployeeView ev,Boolean codeDuplicateCheckFlag, Boolean passwordCheckFlag){
        List<String>errors = new ArrayList<String>();

        //社員番号のチェック
        String codeError  = validateCode(service, ev.getCode(),codeDuplicateCheckFlag);
        if(!codeError.equals("")) {
            errors.add(codeError);
        }

        //氏名
        String nameError =validateName(ev.getName());
        if(!nameError.equals("")) {
            errors.add(nameError);
        }

        //パスワードチェック
        String passError = validatePassword(ev.getPassword(),passwordCheckFlag);
        if(!passError.equals("")) {
            errors.add(passError);
        }

        return errors;

    }

    /**
     * 社員番号の入力チェックを行い、エラーメッセージを返却
     * @param service EmployeeSeviceインスタンス
     * @param code 社員番号
     * @param codeDuplicateCheckFlag　社員番号の重複チェックを実施するかどうか（実施する：true 実施しない：false）
     * @return エラーメッセージ
     */

    private static String validateCode(EmployeeService service, String code, Boolean codeDuplicateCheckFlag) {
        //入力がなければエラーメッセージを返却
        if(code==null||code.equals("")) {
            return MessageConst.E_NOEMP_CODE.getMessage();
        }

        if(codeDuplicateCheckFlag) {
            //社員番号の重複チェックを実施

            Long employeesCount = isDuplicateEmployee(service,code);

            //同一社員番号がすでに登録されている場合はエラーメッセージを返却
            if(employeesCount > 0) {
                return MessageConst.E_EMP_CODE_EXIST.getMessage();
            }
        }
        //エラーがない場合は空文字を返却。
        return "";
    }

    /**
     * @param service EmployeeServiceのインスタンス
     * @param code　社員番号
     * @return 従業員テーブルに登録されている同一社員番号のデータの件数
     *r
     */

    private static Long isDuplicateEmployee(EmployeeService service, String code) {

        long employeeCount=service.countByCode(code);
        return employeeCount;
    }

    /**
     * 氏名に入力があるかチェックし、入力値がなければエラーメッセージを返却
     * @param name氏名
     * @return エラーメッセージ
     */
    private static String validateName(String name) {

        if(name==null || name.equals("")) {
            return MessageConst.E_NONAME.getMessage();
        }

        return "";
    }

    /**
     * パスワード入力チェック
     * @param password パスワード
     * @param passwordCheckFlag パスワードの入力チェックを実施するかどうか（実施する：true 実施しない：false）
     * @return エラーメッセージ
     */

    private static String validatePassword(String password, Boolean passwordCheckFlag) {
        //入力チェックを実施かつ入力がなければエラーメッセージ返却
        if(passwordCheckFlag && (password==null||password.equals(""))) {
            return MessageConst.E_NOPASSWORD.getMessage();
        }
        //エラーがない場合は空文字を返す
        return "";
    }

}
