package models.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.Time;


import actions.views.ReportView;
import constants.MessageConst;

/**
 * 日報インスタンスに設定されている値のバリデーションを行うクラス
 *
 */
public class ReportValidator {
    /**
     * 日報インスタンスの各項目についてバリデーションを行う
     * @param rv 日報インスタンス
     * @return エラーのリスト
     */

    public static List<String> Validate(ReportView rv){
        List<String> errors =new ArrayList<String>();

        //タイトルのチェック
        String titleError = validateTitle(rv.getTitle());
        if(!titleError.equals("")) {
            errors.add(titleError);
        }

        //内容のチェック
        String contentError = validateContent(rv.getContent());
        if(!contentError.equals("")) {
            errors.add(contentError);
        }

        //出勤時間が入っているかのチェック
        String att_tError = validateatt_T(rv.getAtt_T());
        if(!att_tError.equals("")) {
            errors.add(att_tError);
        }

        //退勤時間が入っているかのチェック
        String lea_tError = validatelea_T(rv.getLea_T());
        if(!lea_tError.equals("")) {
            errors.add(lea_tError);
        }


        //出退勤時間の時間形式のチェック。まず空じゃないのをチェック
        if(lea_tError.equals("") && att_tError.equals("")) {
            String timeError = validatTime(rv.getAtt_T(),rv.getLea_T());
            if(!timeError.equals("")) {
                errors.add(timeError);
            }

        }
        //出退勤時間の矛盾のチェック。まず空じゃないのをチェック
        if(lea_tError.equals("") && att_tError.equals("")) {
            String afterError = validateafter(rv.getAtt_T(),rv.getLea_T());
            if(!afterError.equals("")) {
                errors.add(afterError);
            }

        }


        return errors;

    }

    /**
     * タイトルに入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param title タイトル
     * @return エラーメッセージ
     */
    private static String validateTitle(String title) {
        if(title==null||title.equals("")) {
            return MessageConst.E_NOTITLE.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }

    /**
     * 内容に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param content 内容
     * @return エラーメッセージ
     */
    private static String validateContent(String content) {
        if(content==null||content.equals("")) {
            return MessageConst.E_NOCONTENT.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }

    /**
     * 出勤時間に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param att_T 内容
     * @return エラーメッセージ
     */
    private static String validateatt_T(String  att_T) {
        if(att_T==null||att_T.equals("")) {
            return MessageConst.E_NOATT.getMessage();
        }
        //入力値がある場合は空文字を返却
        return "";
    }

    /**
     * 退勤時間に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param lea_T 内容
     * @return エラーメッセージ
     */
    private static String validatelea_T(String  lea_T) {
        if(lea_T==null||lea_T.equals("")) {
            return MessageConst.E_NOLET.getMessage();
        }
        //入力値がある場合は空文字を返却
        return "";
    }

    /**
     * 時間表現をチェックし、入力値がなければエラーメッセージを返却
     * @param att_T,lea_T 内容
     * @return エラーメッセージ
     */

    private static String validatTime(String  att_T,String  lea_T) {
        Pattern p = Pattern.compile("^([0-1][0-9]|[2][0-3]):[0-5][0-9]$");
        Matcher ma = p.matcher(att_T);
        Matcher ml = p.matcher(lea_T);
        if(!ma.find()||!ml.find()) {
            return MessageConst.E_NOTIME.getMessage();
        }
        //入力値がある場合は空文字を返却
        return "";
    }

    /**
     * 時間形式の入力値があるかをチェックし、正しくなければエラーメッセージを返却
     * @param lea_T 内容
     * @return エラーメッセージ
     */
    private static String validateafter(String  att_T,String  lea_T) {
             Time at = Time.valueOf(att_T + ":00");
             Time lt = Time.valueOf(lea_T + ":00");
             if (at.after(lt)) {
                 return MessageConst.E_NOTIMEMATCH.getMessage();
             } else {
                 return "";
             }


    }


}
