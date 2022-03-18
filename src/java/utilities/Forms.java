package utilities;

import java.util.List;
import javax.faces.model.SelectItem;

public class Forms {

    public static SelectItem[] addObject(List<?> objectlist, String txtFirstOption) {
        int index, size;
        SelectItem[] itemlist;
        index = 0;
        size = objectlist.size();
        if (txtFirstOption != null && !txtFirstOption.isEmpty()) {
            index++;
            size++;
            itemlist = new SelectItem[size];
            itemlist[0] = new SelectItem("", txtFirstOption);
        } else {
            itemlist = new SelectItem[size];
        }
        for (Object obj:objectlist){
            itemlist[index] = new SelectItem(obj,obj.toString());
            index++;
        }
        return itemlist;
    }

}
