package actions.views;

import java.util.ArrayList;
import java.util.List;

import constants.AttributeConst;
import constants.JpaConst;
import models.Employee;

/**従業員データのDTOモデル⇔viewモデルの変換を行うクラス
 *
 */

public class EmployeeConverter {
    /**
     * viewモデルのインスタンスからDTOも出うrのインスタンスを作成
     * @param ev EmployeeViewのインスタンス
     * @return Employeeのインスタンス
     */
    public static Employee toModel(EmployeeView ev) {

        return new Employee(
                ev.getId(),
                ev.getCode(),
                ev.getName(),
                ev.getPassword(),
                ev.getAdminFlag() == null
                    ? null
                    : ev.getAdminFlag() == AttributeConst.ROLE_ADMIN.getIntegerValue()
                        ? JpaConst.ROLE_ADMIN
                        : JpaConst.ROLE_GENERAL,
                ev.getCreatedAt(),
                ev.getUpdatedAt(),
                ev.getDeleteFlag()==null
                    ?null
                    :ev.getDeleteFlag()==AttributeConst.DEL_FLAG_TRUE.getIntegerValue()
                        ?JpaConst.EMP_DEL_TRUE
                        :JpaConst.EMP_DEL_FALSE);
    }

    /**
     * DTOモデルのインスタンスからviewモデルのインスタンスを作成する
     * @param Employeeのインスタンス
     * @return EmployeeViewのインスタンス
     */

    public static EmployeeView toView(Employee e) {
        if(e==null) {
            return null;
        }

        return new EmployeeView(
                e.getId(),
                e.getCode(),
                e.getName(),
                e.getName(),
                e.getPassword(),
                e.getAdminFlag() == null
                    ? null
                    :e.getAdminFlag()== JpaConst.ROLE_ADMIN
                        ?AttributeConst.ROLE_ADMIN.getIntegerValue()
                        :AttributeConst.ROLE_GENERAL.getIntegerValue(),
                e.getCreatedAt(),
                e.getUpdatedAt(),
                e.getDeleteFlag()==null
                    ?null
                    :e.getDeleteFlag()==JpaConst.EMP_DEL_TRUE
                        ?AttributeConst.DEL_FLAG_TRUE.getIntegerValue()
                        :AttributeConst.DEL_FLAG_FALSE.getIntegerValue());
    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param List DTOモデルのリスト
     * @return Viewモデルのリスト
     */

    public static List<EmployeeView> toViewList(List<Employee> list){
        List<EmployeeView> evs = new ArrayList<>();

        for (Employee e : list) {
            evs.add(toView(e));
        }
        return evs;

    }
    /**
     * Viewモデルの全フィールドの内容をDTPモデルのフィールドにコピーする
     *@param e DTOモデル（コピー先）
     *@param ev Viewモデル（コピー元）
     */

    public static void copyViewToModel(Employee e,EmployeeView ev) {
        e.setId(ev.getId());
        e.setCode(ev.getCode());
        e.setName(ev.getName());
        e.setPassword(ev.getPassword());
        e.setAdminFlag(ev.getAdminFlag());
        e.setCreatedAt(ev.getCreatedAt());
        e.setUpdatedAt(ev.getUpdatedAt());
        e.setDeleteFlag(ev.getDeleteFlag());
    }

}
