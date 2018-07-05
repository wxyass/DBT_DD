package dd.tsingtaopad.dd.ddzs.zsinvoicing.zsinvocingtz.domain;

import java.util.List;

import dd.tsingtaopad.db.table.MitValaddaccountMTemp;
import dd.tsingtaopad.db.table.MitValaddaccountproMTemp;

/**
 * Created by yangwenmin on 2018/5/17.
 */

public class ZsTzItemIndex extends MitValaddaccountMTemp {
    // 具体
    private List<MitValaddaccountproMTemp> indexValueLst;

    public List<MitValaddaccountproMTemp> getIndexValueLst() {
        return indexValueLst;
    }

    public void setIndexValueLst(List<MitValaddaccountproMTemp> indexValueLst) {
        this.indexValueLst = indexValueLst;
    }
}
