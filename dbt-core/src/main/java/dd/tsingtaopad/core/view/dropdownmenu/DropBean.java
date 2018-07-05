package dd.tsingtaopad.core.view.dropdownmenu;

/**
 * Created by cui on 2016/12/13.
 */

public class DropBean {
    private String name;
    private String key;
    private boolean choiced = false;
    public DropBean(String name){
        this.name = name;
    }
    public DropBean(String name,String key){
        this.name = name;
        this.key = key;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isChoiced() {
        return choiced;
    }
    public void setChoiced(boolean choiced) {
        this.choiced = choiced;
    }
}
